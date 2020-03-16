package com.xs.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshan.common.utils.StringUtil;
import com.xs.cms.dao.UserMapper;
import com.xs.cms.domain.User;
import com.xs.cms.service.UserService;
import com.xs.cms.util.CMSException;
import com.xs.cms.util.Md5Util;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper dao;
	
	@Override
	public PageInfo<User> selects(User user, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> list = dao.selects(user);
		
		return new PageInfo<User>(list);
	}

	@Override
	public int update(User user) {
		return dao.update(user);
	}

	@Override
	public int insert(User user) {
		if (!StringUtil.hasText(user.getUsername()))
			throw new CMSException("�û�������Ϊ��");
		if (!(user.getUsername().length() >= 2 && user.getUsername().length() <= 10))
			throw new CMSException("�û����ĳ�����2-10֮��");
		User findUser = this.selectByUsername(user.getUsername());
		if (null != findUser)
			throw new CMSException("�û����Ѿ���ע��.");

		// 2����У��
		if (!StringUtil.hasText(user.getPassword()))
			throw new CMSException("���벻��Ϊ��");
		if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 10))
			throw new CMSException("����ĳ�����6-10֮��");
		// 3ȷ������ ���������������һ��
		if (!StringUtil.hasText(user.getRepassword()))
			throw new CMSException("ȷ�����벻��Ϊ��");
		if (!user.getRepassword().equals(user.getPassword()))
			throw new CMSException("�����������벻һ��");

		// 4 ���û�������м�������
		user.setPassword(Md5Util.encode(user.getPassword()));
		// ��ʼ�û���ע����Ϣ----
		user.setCreated(new Date());//ע��ʱ��
		user.setNickname(user.getUsername());//Ĭ���û��ǳ�Ϊ�û�����
		user.setLocked("0");//Ĭ���û�״̬�ǿ��õ�
		return dao.insert(user);
	}

	@Override
	public User selectByUsername(String username) {
		return dao.selectByUsername(username);
	}

	@Override
	public User login(User user) {
		// 1 У�� �û�������Ϊ��"
				if (!StringUtil.hasText(user.getUsername()))
					throw new CMSException("�û�������Ϊ��");
				// 2 ����û����Ƿ����
				User u = this.selectByUsername(user.getUsername());
				if (null == u) {
					throw new CMSException("���û���������");
				}
				// 3 �Ƚ������Ƿ�һ�� //���ݿ�洢���� ���ܺ������
				// �Ե�¼�������ٽ��м��� �ٺ����ݿ��������бȽ�
				if (!Md5Util.encode(user.getPassword()).equals(u.getPassword()))
					throw new CMSException("���벻��ȷ��������¼��");
				
				if (u.getLocked().equals("1")) {
					throw new CMSException("��ǰ�û������ã����ܵ�¼");
				}
				
				return u;

	}
}
