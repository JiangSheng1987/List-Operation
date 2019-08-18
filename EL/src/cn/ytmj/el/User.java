package cn.ytmj.el;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rui
 * @create 2019-08-14 21:53
 */
public class User {
    private String name;
    private Integer age;
    private Date date;

    public User(String name, Integer age, Date date) {
        this.name = name;
        this.age = age;
        this.date = date;

    }
    public String getSimDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        return format;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public User(){

    }
}

