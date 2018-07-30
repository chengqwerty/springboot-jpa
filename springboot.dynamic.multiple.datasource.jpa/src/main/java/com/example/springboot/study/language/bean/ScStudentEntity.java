package com.example.springboot.study.language.bean;

import com.example.springboot.config.validator.groups.Delete;
import com.example.springboot.config.validator.groups.Insert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "sc_student", schema = "metadb", catalog = "dcmetadb")
public class ScStudentEntity {
    private int sid;
    private String stuName;
    private Integer gid;
    private Integer age;
    private Date birthday;

    @Id
    @GeneratedValue(generator = "scStudentEntity")
    @GenericGenerator(name = "scStudentEntity", strategy = "increment")
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "stu_name")
    @NotNull(message = "名字不能为空", groups = {Insert.class})
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Basic
    @Column(name = "gid")
    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScStudentEntity that = (ScStudentEntity) o;
        return sid == that.sid &&
                Objects.equals(stuName, that.stuName) &&
                Objects.equals(gid, that.gid) &&
                Objects.equals(age, that.age) &&
                Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sid, stuName, gid, age, birthday);
    }
}
