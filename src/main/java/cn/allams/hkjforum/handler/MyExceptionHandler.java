package cn.allams.hkjforum.handler;

import cn.allams.hkjforum.entity.Result;
import cn.allams.hkjforum.entity.ResultEnum;
import cn.allams.hkjforum.exception.MyException;
import cn.allams.hkjforum.util.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Allams
 */
@ControllerAdvice
public class MyExceptionHandler {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ModelAndView handle(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof MyException){
            MyException myException = (MyException)e;
            modelAndView.addObject("result",ResultUtil.error(myException.getCode(),myException.getMessage())) ;
        }else{
            LOGGER.error("系统异常咯",e);
            modelAndView.addObject("result",ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMsg()));
        }
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
