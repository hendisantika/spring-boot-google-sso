package com.hendisantika.repository;

import com.hendisantika.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-sso
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/04/22
 * Time: 07.06
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findByUsername(String username);
}
