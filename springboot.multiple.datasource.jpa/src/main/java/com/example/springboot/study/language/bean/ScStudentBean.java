package com.example.springboot.study.language.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "sc_student", schema = "metadb", catalog = "dcmetadb")
public class ScStudentBean {
    private int sid;
    private String stuName;
    private Integer gid;
    private Integer age;
    private Date birthday;

    @Id
    @GeneratedValue(generator = "scStudentG1")
    @GenericGenerator(name = "scStudentG1", strategy = "increment")
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "stu_name")
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
        ScStudentBean that = (ScStudentBean) o;
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
