package com.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.UserMapper;
import com.web.entity.User;
import com.web.entity.UserExample;
import com.web.entity.UserExample.Criteria;
import com.web.pojo.UserAndPartment;
import com.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> selectAll(User user) {

		List<User> list = userMapper.queryAll(user);

		return list;
	}

	@Override
	public User login(String userName, String password) {

		UserExample example = new UserExample();

		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		criteria.andUserpwdEqualTo(password);

		List<User> list = userMapper.selectByExample(example);

		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<UserAndPartment> getUserAndPart(Integer id) {

		List<UserAndPartment> list = userMapper.selectByUserId(id);

		return list;
	}

	@Override
	public int updateUserAndDepartment(UserAndPartment uap) {

		int i = userMapper.updateUserAndDepart(uap);

		return i;
	}

	@Override
	public User getUserById(Integer id) {

		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer updateUserById(User user) {

		//这里只对用户做出修改的信息做出更新，不修改的信息不更新
		return userMapper.updateByPrimaryKeySelective(user);
	}

}
