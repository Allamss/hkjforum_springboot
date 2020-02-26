package cn.allams.hkjforum.exception;

/**
 * 自定义异常类，多一个异常码
 * @author Allams
 */
public class MyException extends RuntimeException{
    private int code;

    public MyException(int code, String message){
        super(message);
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public void setCode(){
        this.code = code;
    }
}
