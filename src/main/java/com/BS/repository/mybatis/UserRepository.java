package com.BS.repository.mybatis;

import com.BS.model.User;
import org.springframework.stereotype.Repository;

/**
<<<<<<< HEAD
 * 
 * @Date: 2018/6/5 19:37
=======
>>>>>>> branch 'master' of https://github.com/dingxiaowei1995/bishe-dw.git
 * Describe:xml配置User表操作
 */
@Repository
public interface UserRepository {

    /**
     *  通过手机号查找用户
     * @param phone 手机号
     * @return 用户
     */
    User findByPhone(String phone);

}
