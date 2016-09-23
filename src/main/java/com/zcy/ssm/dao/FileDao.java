package com.zcy.ssm.dao;

import com.zcy.ssm.base.dao.BaseDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by zcy on 2016/9/23.
 */
@Repository
public interface FileDao extends BaseDao {

    void updateUserHeadPhoto(Map<String, Object> paramsMap) throws DataAccessException;

}
