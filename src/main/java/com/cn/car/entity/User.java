package com.cn.car.entity;

public class User {
    /**
     * 
     */
    private Integer id;

    /**
     * ����
     */
    private String username;

    /**
     * ����
     */
    private String password;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ����
     * @return username ����
     */
    public String getUsername() {
        return username;
    }

    /**
     * ����
     * @param username ����
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * ����
     * @return password ����
     */
    public String getPassword() {
        return password;
    }

    /**
     * ����
     * @param password ����
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 
     * @return age 
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 
     * @param age 
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}
