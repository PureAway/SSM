package com.zcy.ssm.dao;

import com.zcy.ssm.base.dao.BaseDao;
import com.zcy.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao
 * Created by zcy on 2016/9/14.
 */
@Repository
public interface UserDao extends BaseDao {

    List<User> selectAllUser();

    User selectUserById(@Param("UserId") String userId);

    User selectUserByPhone(@Param("UserPhone") String userPhone);

    Long insertUser(User user);

}
