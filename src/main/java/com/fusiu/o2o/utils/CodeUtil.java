package com.fusiu.o2o.utils;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        /**
         * 获取正确的验证码
         */
        String verifyCodeExpected = (String)request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY
        );
        /**
         * 获取输入的验证码 verifyCodeActual
         */
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if (verifyCodeActual == null || (!verifyCodeExpected.equals(verifyCodeActual))){
            return false;
        }
        return true;
    }
}
