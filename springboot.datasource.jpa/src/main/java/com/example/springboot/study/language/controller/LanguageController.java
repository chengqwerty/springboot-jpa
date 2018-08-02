package com.example.springboot.study.language.controller;

import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @RequestMapping()
    public String language() {

        return "This is language!";
    }

    /**
     * JPA查询所有student
     * @return
     */
    @RequestMapping("/studentAll")
    public List<ScStudentBean> studentAll() {
        List<ScStudentBean> scStudentBeans = languageService.studentAll();
        return scStudentBeans;
    }

    /**
     * JPA查询student数量（根据stuName)
     * @param stuName
     * @return
     */
    @RequestMapping("/countByStuName")
    public Long countByStuName(@RequestParam String stuName) {
        Long stuNum = languageService.countByStuName(stuName);
        return stuNum;
    }

    /**
     * JPA根据gid 和 age查询student
     * @param gid
     * @param age
     * @return
     */
    @RequestMapping("/findByGidAndAge")
    public List<ScStudentBean> findByGidAndAge(@RequestParam Integer gid, @RequestParam Integer age) {
        List<ScStudentBean> scStudentBeanList = languageService.findByGidAndAge(gid, age);
        return scStudentBeanList;
    }

    /**
     * JPQL查询所有student
     * @return
     */
    @RequestMapping("/queryStudentAll")
    public List<ScStudentBean> queryStudentAll() {
        List<ScStudentBean> scStudentBeanList = languageService.queryStudentAll();
        return scStudentBeanList;
    }

    /**
     * JPA批量添加数据(用于测试)
     * @return
     */
    @RequestMapping("/saveManyStudent")
    public String saveManyStudent() {
        languageService.saveManyStudent();
        return "Student 添加完毕";
    }

    /**
     * JPA添加student
     * @param scStudentBean
     * @return 返回添加的student
     */
    @RequestMapping("/saveStudent")
    public ScStudentBean saveStudent(ScStudentBean scStudentBean) {
        ScStudentBean saveScStudentBean = languageService.saveStudent(scStudentBean);
        return saveScStudentBean;
    }

    /**
     * JPA update student
     * @param scStudentBean
     * @return 返回update后的student
     */
    @RequestMapping("/updateStudent")
    public ScStudentBean updateStudent(ScStudentBean scStudentBean) {
        ScStudentBean updateScStudentBean = languageService.updateStudent(scStudentBean);
        return updateScStudentBean;
    }

    /**
     * JPA delete student
     * JpaRepository接口的delete方法是根据主键delete的。
     * @param scStudentBean
     * @return
     */
    @RequestMapping("/deleteStudent")
    public ScStudentBean deleteStudent(ScStudentBean scStudentBean) {
        ScStudentBean deleteScStudentBean = languageService.deleteStudent(scStudentBean);
        return deleteScStudentBean;
    }

    /**
     * criteria查询所有student
     * @return
     */
    @RequestMapping("/criteriaQueryStudentAll")
    public List<ScStudentBean> criteriaQueryStudentAll() {
        List<ScStudentBean> scStudentBeanList = languageService.criteriaQueryStudentAll();
        return scStudentBeanList;
    }
}
