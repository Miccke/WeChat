package com.cn.car.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.car.entity.Student;

public interface StudentDao {


    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);


    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Student record);

    int updateByExample(@Param("record") Student record);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<Student> selectByFy(Map<String,Object> param);
    public List<Student> findUserList();
}
