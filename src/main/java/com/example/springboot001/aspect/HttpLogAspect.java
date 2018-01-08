package com.example.springboot001.aspect;


import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * AOP请求日志切面类
 */
@Aspect
@Component
public class HttpLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);


    /**
     * http请求日志切点
     */
    @Pointcut("execution(* com.example.springboot001.controller..*(..))")
    public void log(){

    }

    /**
     * 请求之前记录日志
     * @param joinPoint
     */
    @Before("log()")
    public void doBeforeAdvice(JoinPoint joinPoint) throws ClassNotFoundException, NotFoundException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String methodName = joinPoint.getSignature().getName();

        //url
        logger.info("url={}", request.getRequestURL());

        //method
        logger.info("method={}", request.getMethod());

        //ip
        logger.info("ip={}", request.getRemoteAddr());

        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + methodName);

        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        Map<String,Object > paramsNameAndArgs = getFieldsName(this.getClass(), clazzName, methodName,joinPoint.getArgs());
        //参数和参数值
        logger.info("args={}", paramsNameAndArgs);
    }

    /**
     * 请求之后记录日志
     */
    @After("log()")
    public void doAfter() {
        logger.info("success");
    }

    /**
     * 返回的数据
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object!=null?object.toString():null);
    }

    private Map<String,Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map<String,Object > map=new HashMap<String,Object>();

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        // String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++){
            //paramNames即参数名
            map.put( attr.variableName(i + pos),args[i]);
        }

        //Map<>
        return map;
    }
}
