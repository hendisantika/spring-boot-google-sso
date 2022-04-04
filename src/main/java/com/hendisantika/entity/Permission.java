package com.hendisantika.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-sso
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/04/22
 * Time: 07.03
 */

@Data
@Entity
@Table(name = "t_permission")
public class Permission {
    @Id
    private String id;
    @Column(name = "permission_label")
    private String label;
    @Column(name = "permission_value")
    private String value;
}
