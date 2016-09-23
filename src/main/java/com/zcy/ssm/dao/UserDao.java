package com.zcy.ssm.dao;

import com.zcy.ssm.base.dao.BaseDao;
import com.zcy.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * UserDao
 * Created by zcy on 2016/9/14.
 */
@Repository
public interface UserDao extends BaseDao {

    User selectUserById(@Param("userId") String userId) throws DataAccessException;

    User selectUserByPhone(@Param("userPhone") String userPhone) throws DataAccessException;

    Long insertUser(User user) throws DataAccessException;

    void updatePassword(User user) throws DataAccessException;

    void updateUser(User user) throws DataAccessException;

}
