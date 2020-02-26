package cn.allams.hkjforum.util;

import cn.allams.hkjforum.entity.Result;

/**
 * 方便生成Result.java
 * @author Allams
 */
public class ResultUtil {

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    public static Result success(Object obj) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(obj);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(null);
        return result;
    }

}
