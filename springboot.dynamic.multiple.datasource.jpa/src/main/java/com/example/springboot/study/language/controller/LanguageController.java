package com.example.springboot.study.language.controller;

import com.example.springboot.config.datasource.DataSourceSlavePrefix;
import com.example.springboot.config.exception.QueryException;
import com.example.springboot.config.exception.SaveException;
import com.example.springboot.config.exception.ValidationException;
import com.example.springboot.config.validator.ScStudentBeanValidator;
import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.bean.ScStudentEntity;
import com.example.springboot.study.language.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * 绑定验证器
     * @param webDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new ScStudentBeanValidator());
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
     * @param studentName
     * @return
     */
    @RequestMapping("/postGre/saveStudent")
    public ScStudentEntity postGreSaveStudent(String studentName) throws SaveException {
        ScStudentEntity scStudentEntity = null;
        try {
            scStudentEntity = languageService.postGreSaveStudent(studentName);
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
    @RequestMapping(value = "/oracle/saveStudent", produces = "text/html;charset=UTF-8")
    public ScStudentBean oracleSaveStudent(@Validated ScStudentBean scStudentBean, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = fieldError.getDefaultMessage();
            throw new ValidationException(errorMessage);
        }
        ScStudentBean newScStudentBean = languageService.oracleSaveStudent(scStudentBean);
        return newScStudentBean;
    }

}
