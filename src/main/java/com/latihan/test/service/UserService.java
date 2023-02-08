package com.latihan.test.service;

import com.latihan.test.dto.UserDto;
import com.latihan.test.entity.*;
import com.latihan.test.repository.*;
import com.latihan.test.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserPartyRepository userPartyRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("$(user-data)")
    private String topic;


    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String PASSWORD = "password";
    private static final String USER_NOT_FOUND = "user not found";


    @Transactional
    public User register (Map<String,Object> body) throws ValidationException{
        //VALIDATION
        if (body == null) throw new ValidationException("data request is null");
        if (body.get("name").toString() == null) throw new ValidationException("name is null");
        if (body.get(EMAIL).toString() == null || FormatUtil.isEmailFormat(body.get(EMAIL).toString()))
            throw new ValidationException("email is null or invalid");
        if (body.get(PHONE).toString() == null || FormatUtil.isMobilePhoneNumberFormat(body.get(PHONE).toString()))
            throw new ValidationException("phone is null or invalid");
        if (body.get(PASSWORD).toString() == null || FormatUtil.isPasswordFormat(body.get(PASSWORD).toString()))
            throw new ValidationException("password is null or invallid");
        if (body.get("nik").toString() == null || FormatUtil.isIdCardNumberFormat(body.get("nik").toString()))
            throw new ValidationException("nik is null or invalid");

        // CHECK DATA
        RoleUser checkRole = roleUserRepository.findByName(body.get("role").toString());
        if (checkRole == null){
            throw new ValidationException("role invalid");
        }

        UserType checkType = userTypeRepository.findByName(body.get("type").toString());
        if (checkType == null){
            throw new ValidationException("type invalid");
        }

        User checkUser = userRepository.findUserNew(body.get(EMAIL).toString(), body.get(PHONE).toString(), body.get("nik").toString());
        if (checkUser != null){
            throw new ValidationException("user already exist");
        }

        // INSERT
        User user = new User();
        user.name = body.get("name").toString();
        user.email = body.get(EMAIL).toString();
        user.phone = body.get(PHONE).toString();
        user.password = body.get(PASSWORD).toString();
        user.nik = body.get("nik").toString();
        user.isDeleted = false;
        userRepository.save(user);

        UserParty userParty = new UserParty();
        userParty.userId = user;
        userParty.roleId = checkRole;
        userParty.typeId = checkType;
        userPartyRepository.save(userParty);

        ActivityUser activityUser = new ActivityUser();
        activityUser.userId = user.id;
        activityUser.type = "REGISTER";
        activityUser.desc = "user register";
        activityUser.deviceId = UUID.randomUUID().toString();
        activityRepository.save(activityUser);

        return userRepository.findUser(body.get(EMAIL).toString(), body.get(PHONE).toString(), body.get("nik").toString(), false);
    }

    @Transactional
    public User login (Map<String,Object> body) throws ValidationException{
        // VALIDATION
        if (body == null) throw new ValidationException("data request is null");
        if (body.get(EMAIL).toString() == null) throw new ValidationException("email is null");
        if (body.get(PASSWORD).toString() == null) throw new ValidationException("password is null");

        //CHECK
        User checkUser = userRepository.findByEmailPassword(body.get(EMAIL).toString(), body.get(PASSWORD).toString());
        if (checkUser == null) throw new ValidationException("user not registered");

        UserParty checkParty = userPartyRepository.findByUserId(checkUser.id);
        if (checkParty == null) throw new ValidationException(USER_NOT_FOUND);

        ActivityUser activityUser = new ActivityUser();
        activityUser.userId = checkUser.id;
        activityUser.type = "LOGIN";
        activityUser.desc = "user login";
        activityUser.deviceId = UUID.randomUUID().toString();
        activityRepository.save(activityUser);

        return checkUser;
    }

    @Transactional
    public void logout (Map<String,Object> body) throws ValidationException{
        if (body.get("id").toString() == null) throw new ValidationException("userId is null");

        User user = userRepository.findUserById(body.get("id").toString());
        UserParty userParty = userPartyRepository.findByUserId(body.get("id").toString());
        if (userParty == null || user ==  null) throw new ValidationException(USER_NOT_FOUND);

        ActivityUser activityUser = new ActivityUser();
        activityUser.userId = body.get("id").toString();
        activityUser.type = "LOGOUT";
        activityUser.desc = "user logout";
        activityUser.deviceId = UUID.randomUUID().toString();
        activityRepository.save(activityUser);
    }

    @Transactional
    public User  findByIdUser (String userId) throws ValidationException{
        if (userId==null) throw new ValidationException("userId is null");

        User user = userRepository.findUserById(userId);
        if (user == null) throw new ValidationException(USER_NOT_FOUND);

        return user;
    }

    @Transactional
    public List<UserDto> findAllUser() throws ValidationException{
        List<User> user = userRepository.findAll();
        return user.stream().map(this::getUser).collect(Collectors.toList());
    }

    @Transactional
    public UserDto getUser (User user) {
        UserDto userDto = new UserDto();
        userDto.email = user.email;
        userDto.nik = user.nik;
        userDto.name = user.name;
        userDto.phone = user.phone;
        return userDto;
    }


    @Transactional
    public User updateUser(Map<String,Object> body) throws ValidationException{
        User user = userRepository.findUserById(body.get("id").toString());
        if (user == null) throw new ValidationException(USER_NOT_FOUND);

        user.isDeleted = (Boolean) body.get("isDeleted");
        user.nik = body.get("nik").toString();
        user.email = body.get(EMAIL).toString();
        user.password = body.get(PASSWORD).toString();
        user.name = body.get("name").toString();
        user.phone = body.get(PHONE).toString();
        userRepository.save(user);

        ActivityUser activityUser = new ActivityUser();
        activityUser.userId = body.get("id").toString();
        activityUser.type = "UPDATE";
        activityUser.desc = "user update data";
        activityUser.deviceId = UUID.randomUUID().toString();
        activityRepository.save(activityUser);

        return userRepository.findUserById(body.get("id").toString());
    }

    @Transactional
    public void deleteUser (String userId) throws ValidationException{
        User user = userRepository.findUserById(userId);
        if (user == null) throw new ValidationException(USER_NOT_FOUND);

        UserParty userParty = userPartyRepository.findByUserId(userId);
        if (userParty == null) throw new ValidationException("user Party not found");

        Map<String,String> body = new HashMap<>();
        body.put("userId", user.id);
        body.put("name", user.name);
        body.put("email", user.email);

        template.send(topic, body.toString());
        userRepository.delete(user);
        userPartyRepository.delete(userParty);
    }

    @Transactional
    @KafkaListener(topics = "${user-activity}")
    public void addActivity (@Payload String email) throws ValidationException{
        User user =  userRepository.findUserByEmail(email);
        if (user==null) throw new ValidationException(USER_NOT_FOUND);

        ActivityUser activityUser = new ActivityUser();
        activityUser.userId = user.id;
        activityUser.type = "ADD ACTIVITY";
        activityUser.desc = "user add activity";
        activityUser.deviceId = UUID.randomUUID().toString();
        activityRepository.save(activityUser);
    }

}
