package com.latihan.test.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity", indexes = {
        @Index(name = "idxActivity",
                columnList = "userId, deviceId, type")
}
)
public class ActivityUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36, nullable = false)
    public String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    public String userId;

    @Column(name = "deviceId", length = 36, nullable = false)
    public String deviceId;

    @Column(name = "type", length = 50, nullable = false)
    public String type;

    @Column(name = "desc", length = 15, nullable = false)
    public String desc;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    public Date createdAt;
}
