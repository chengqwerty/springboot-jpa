package com.example.springboot.config.validator;

import com.example.springboot.study.language.bean.ScStudentBean;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ScStudentBeanValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return ScStudentBean.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "stuName", "name.empty");
        ScStudentBean scStudentBean = (ScStudentBean) obj;
        if (scStudentBean.getAge() < 0) {
            errors.rejectValue("age", "negativevalue", "无效的年龄.");
        } else if (scStudentBean.getAge() > 110) {
            errors.rejectValue("age", "too.darn.old", "年龄不符合实际要求.");
        }
    }
}
