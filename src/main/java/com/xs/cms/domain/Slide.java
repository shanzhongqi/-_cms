package com.xs.cms.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: Slide 
 * @Description: ����
 * @author: ���
 * @date: 2020��3��3�� ����6:46:41
 */
public class Slide implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//����
	private String title;//��������˵��
	private String picture;//����ͼƬ��ַ
	private String url;//���������Ĺ������
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
