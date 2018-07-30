package com.example.springboot.study.language.controller;

import com.example.springboot.config.datasource.DataSourceSlavePrefix;
import com.example.springboot.config.exception.ParameterValidationException;
import com.example.springboot.config.exception.QueryException;
import com.example.springboot.config.exception.SaveException;
import com.example.springboot.config.validator.ScStudentBeanValidator;
import com.example.springboot.config.validator.groups.Insert;
import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.bean.ScStudentEntity;
import com.example.springboot.study.language.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("language")
public class LanguageController {

    @Autowired
    ScStudentBeanValidator scStudentBeanValidator;

    /**
     * 绑定验证器
     * Annotaion JSR-303与spring 的Validator接口不要一起使用
     * @param webDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        // 通过判断类型加载验证器，每次请求都会执行这个方法
        if(scStudentBeanValidator.supports(webDataBinder.getTarget().getClass())){
            webDataBinder.addValidators(scStudentBeanValidator);
        }else{
            // webDataBinder.addValidators(teacherValidator);
        }
    }

    @Autowired
    private DataSourceSlavePrefix dataSourceSlavePrefix;

    @Autowired
    private LanguageService languageService;

    @RequestMapping()
    public String language() {
        // return dataSourceSlavePrefix.getPrefix();
        return "中文";
    }

    @RequestMapping("/postGre/studentById")
    public ScStudentEntity postGreStudentById(int sid) {
        ScStudentEntity scStudentEntity = languageService.postGreStudentById(sid);
        return scStudentEntity;
    }

    @RequestMapping("/postGre/studentAll")
    public List<ScStudentEntity> postGreStudentAll() throws QueryException {
        List<ScStudentEntity> scStudentEntityList = null;
        try {
            scStudentEntityList = languageService.postGreStudentAll();
            int a = 1/0; //抛出异常，测试全局异常处理
        } catch (Exception e) {
            e.printStackTrace();
            throw new QueryException();
        }
        return scStudentEntityList;
    }

    @RequestMapping("/oracle/studentAll")
    public List<ScStudentBean> oracleStudentAll() {
        List<ScStudentBean> scStudentBeanList = languageService.oracleStudentAll();
        return scStudentBeanList;
    }

    /**
     * 使用primary datasource 保存新student
     * @param scStudentEntity
     * @return
     */
    @RequestMapping("/postGre/saveStudent")
    public ScStudentEntity postGreSaveStudent(@Validated({Insert.class})ScStudentEntity scStudentEntity) throws SaveException {
        try {
            ScStudentEntity newScStudentEntity = languageService.postGreSaveStudent(scStudentEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException();
        }
        return scStudentEntity;
    }

    /**
     * 使用slave datasouce 保存新student
     * 测试验证器ScStudentBeanValidator
     * 这是继承spring Validator接口的验证
     * @param scStudentBean
     * @return
     */
    @RequestMapping(value = "/oracle/saveStudent")
    public ScStudentBean oracleSaveStudent(@Validated ScStudentBean scStudentBean, BindingResult bindingResult) throws ParameterValidationException {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = fieldError.getDefaultMessage();
            throw new ParameterValidationException(errorMessage);
        }
        ScStudentBean newScStudentBean = languageService.oracleSaveStudent(scStudentBean);
        return newScStudentBean;
    }

}
