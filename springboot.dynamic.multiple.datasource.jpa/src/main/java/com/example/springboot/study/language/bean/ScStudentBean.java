package com.example.springboot.study.language.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "SC_STUDENT", schema = "ELE_HB", catalog = "")
public class ScStudentBean {
    private long sId;
    private String stuName;
    private Long gid;
    private Long age;
    private Time birthday;

    @Id
    @GeneratedValue(generator = "scStudentBean")
    @GenericGenerator(name = "scStudentBean", strategy = "increment")
    @Column(name = "S_ID")
    public long getsId() {
        return sId;
    }

    public void setsId(long sId) {
        this.sId = sId;
    }

    @Basic
    @Column(name = "STU_NAME")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Basic
    @Column(name = "GID")
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "AGE")
    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Basic
    @Column(name = "BIRTHDAY")
    public Time getBirthday() {
        return birthday;
    }

    public void setBirthday(Time birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScStudentBean that = (ScStudentBean) o;
        return sId == that.sId &&
                Objects.equals(stuName, that.stuName) &&
                Objects.equals(gid, that.gid) &&
                Objects.equals(age, that.age) &&
                Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sId, stuName, gid, age, birthday);
    }
}
