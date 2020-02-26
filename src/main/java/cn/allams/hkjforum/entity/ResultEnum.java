package cn.allams.hkjforum.entity;

/**
 * 错误码和错误信息的封装
 */
public enum ResultEnum {
    UNKNOWN_ERROR(500,"未知错误，请联系管理员"),
    SUCCESS(200,"操作成功"),
    USER_NOT_FROUND_ERROR(111,"未找到该用户"),
    PASSWORD_ERROR(112,"密码错误"),
    POST_NOT_FOUND(404,"未找到该帖子，帖子不存在或者已经被删除"),
    PAGE_NOT_FOUND(404, "帖子页数错误");


    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
