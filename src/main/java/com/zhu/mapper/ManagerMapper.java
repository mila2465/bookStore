package com.zhu.mapper;

import com.zhu.domain.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerMapper {
    public Manager findByNameAndPwd(@Param("name") String name, @Param("password") String password);
}
