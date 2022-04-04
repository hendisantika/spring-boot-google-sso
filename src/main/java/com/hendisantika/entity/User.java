package com.hendisantika.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-sso
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/04/22
 * Time: 07.05
 */
@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    private String id;
    private String username;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
}
