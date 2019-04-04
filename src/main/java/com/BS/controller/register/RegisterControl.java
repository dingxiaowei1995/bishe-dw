package com.BS.controller.register;

import com.BS.model.Password;
import com.BS.model.User;
import com.BS.service.UserService;
import com.BS.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
<<<<<<< HEAD
 * 
=======
 *  
>>>>>>> branch 'master' of https://github.com/dingxiaowei1995/bishe-dw.git
 * @Date: 2018/6/4 11:48
 * Describe: 注册Controller
 */
@Controller
public class RegisterControl {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public String register(User user,
                            HttpServletRequest request){

        //String authCode = request.getParameter("authCode");

        String trueMsgCode = (String) request.getSession().getAttribute("trueMsgCode");

        //检测手机验证码
        /*if(!authCode.equals(trueMsgCode)){
            return "0";
        }*/
        if(userService.usernameIsExit(user.getUsername())){
            return "3";
        }
        Password password = new Password();
        password.setPassword(user.getPassword());
        password.setUsername(user.getUsername());
        //注册时对密码进行MD5加密
      /*  MD5Util md5Util = new MD5Util();*/
        user.setPassword(user.getPassword());
        return userService.insert(user);
    }

}
