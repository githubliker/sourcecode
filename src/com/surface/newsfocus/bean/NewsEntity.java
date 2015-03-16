package com.surface.newsfocus.bean;

import java.io.Serializable;
import java.util.List;

public class NewsEntity implements Serializable {
	/** ������� ID */
	private Integer newsCategoryId;
	/** �������� */
	private String newsCategory;
	/** ���״̬�����Ƽ�֮��� */
	private Integer mark;;
	/** �������� */
	private Integer commentNum;
	/** ID */
	private Integer id;
	/** ����ID */
	private Integer newsId;
	/** ���� */
	private String title;
	/** ����Դ */
	private String source;
	/** ����ʱ�� */
	private Long publishTime;
	/** �ܽ� */
	private String summary;
	/** ժҪ */
	private String newsAbstract;
	/** ���� */
	private String comment;
	/** �����ǩ�������ƹ�֮��� ������Ϊ�� */
	private String local;
	/** ͼƬ�б��ַ� */
	private String picListString;
	/** ͼƬ1 URL */
	private String picOne;
	/** ͼƬ2 URL */
	private String picTwo;
	/** ͼƬ3 URL */
	private String picThr;
	/** ͼƬ �б� */
	private List<String> picList;
	/** ͼƬ�����Ƿ�Ϊ��ͼ */
	private Boolean isLarge;
	/** �Ķ�״̬ ������Ļ���ʾ��ɫ���� */
	private Boolean readStatus;
	/** �ղ�״̬ */
	private Boolean collectStatus;
	/** ϲ�� ״̬ */
	private Boolean likeStatus;
	/** ����Ȥ״̬ */
	private Boolean interestedStatus;

	public Integer getNewsCategoryId() {
		return newsCategoryId;
	}

	public void setNewsCategoryId(Integer newsCategoryId) {
		this.newsCategoryId = newsCategoryId;
	}

	public String getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPicListString() {
		return picListString;
	}

	public void setPicListString(String picListString) {
		this.picListString = picListString;
	}

	public String getPicOne() {
		return picOne;
	}

	public void setPicOne(String picOne) {
		this.picOne = picOne;
	}

	public String getPicTwo() {
		return picTwo;
	}

	public void setPicTwo(String picTwo) {
		this.picTwo = picTwo;
	}

	public String getPicThr() {
		return picThr;
	}

	public void setPicThr(String picThr) {
		this.picThr = picThr;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public Boolean getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}

	public Boolean getCollectStatus() {
		return collectStatus;
	}

	public void setCollectStatus(Boolean collectStatus) {
		this.collectStatus = collectStatus;
	}

	public Boolean getLikeStatus() {
		return likeStatus;
	}

	public void setLikeStatus(Boolean likeStatus) {
		this.likeStatus = likeStatus;
	}

	public Boolean getInterestedStatus() {
		return interestedStatus;
	}

	public void setInterestedStatus(Boolean interestedStatus) {
		this.interestedStatus = interestedStatus;
	}

	public String getNewsAbstract() {
		return newsAbstract;
	}

	public void setNewsAbstract(String newsAbstract) {
		this.newsAbstract = newsAbstract;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsLarge() {
		return isLarge;
	}

	public void setIsLarge(Boolean isLarge) {
		this.isLarge = isLarge;
	}

}
