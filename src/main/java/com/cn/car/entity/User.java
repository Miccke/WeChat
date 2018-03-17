package com.cn.car.entity;

public class User {
    /**
     * 
     */
    private Integer id;

    /**
     * ĞÕÃû
     */
    private String username;

    /**
     * ÃÜÂë
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
     * ĞÕÃû
     * @return username ĞÕÃû
     */
    public String getUsername() {
        return username;
    }

    /**
     * ĞÕÃû
     * @param username ĞÕÃû
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * ÃÜÂë
     * @return password ÃÜÂë
     */
    public String getPassword() {
        return password;
    }

    /**
     * ÃÜÂë
     * @param password ÃÜÂë
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
