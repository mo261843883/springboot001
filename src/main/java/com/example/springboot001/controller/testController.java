package com.example.springboot001.controller;

import com.example.springboot001.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Wangjf on 2017-12-01.
 */
@Api(description = "接口类描述")
@RestController
@RequestMapping(value="/test")
public class testController {

    /**
      @ApiOperation(value = "功能名称" ,notes = "接口具体描述")
      @ApiImplicitParams({
      @ApiImplicitParam(name = "参数名1", value = "参数描述", required = true, dataType = "参数类型", paramType = "请求类型【path,query】"),
      @ApiImplicitParam(name = "参数名1", value = "参数描述", required = true, dataType = "参数类型", paramType = "请求类型【path,query】")
      })
     */


    @ApiOperation(value = "获取一个参数" ,notes = "测试")
    @ApiImplicitParam(name = "str",value="输出的字符串" ,required = true,dataType = "String",paramType = "query")
    @RequestMapping(value = "/getParam",method = RequestMethod.GET)
    public String testString(String str){
        return str;
    }

    @ApiOperation(value = "获取多个参数" ,notes = "测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "输出的第一个字符", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "输出的第二个字符", required = true, dataType = "String", paramType = "query")
    })

    @RequestMapping(value = "/getParams",method = RequestMethod.GET)
    public String testString2(String str,String sex){
        return "第一个字符："+str+"    第二个字符："+sex;
    }

    @ApiOperation(value = "获取一个对象" ,notes = "测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "用户性别", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/getPojo",method = RequestMethod.GET)
    public String testPojo(User user){
        return "用户姓名："+user.getName()+"       用户性别"+user.getSex();
    }

    @ApiOperation(value = "获取一个数组数据" ,notes = "测试")
    @RequestMapping(value = "/getArray",method = RequestMethod.GET)
    public String testArray(String[] array){
        String arrays = "数组测试";
        int i=1;
        if(array.length>0){
            for(String str:array){
                arrays += "   第"+i+"个数据:"+str;
                i++;
            }
        }
        return arrays;
    }
}
