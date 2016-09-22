package com.zcy.ssm.dao;

import com.zcy.ssm.base.dao.BaseDao;
import com.zcy.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * UserDao
 * Created by zcy on 2016/9/14.
 */
@Repository
public interface UserDao extends BaseDao {

    List<User> selectAllUser();

    User selectUserById(@Param("userId") String userId);

    User selectUserByPhone(@Param("userPhone") String userPhone);

    User selectUserByUserName(@Param("userName") String userName);

    Long insertUser(User user);

    void updatePassword(@Param("userId") String userId, @Param("password") String password,
                        @Param("modifyTime") Date modifyTime
    );

    void updateUser(User user);

}
