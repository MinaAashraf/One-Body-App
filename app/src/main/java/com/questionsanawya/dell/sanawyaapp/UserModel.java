package com.questionsanawya.dell.sanawyaapp;

/**
 * Created by dell on 3/13/2019.
 */

public class UserModel {
    String name,email,password,confirmpassword,code,url,work;

    public UserModel (){};

    public UserModel(String name, String email, String code, String url,String work) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.code = code;
        this.url = url;
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
