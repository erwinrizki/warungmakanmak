package com.example.warungmak;

public class RowItem {
	private int imageId;
	private String title;
	private String desc;
	
	public RowItem(int newimageid, String newtitle, String newdesc) {
		this.imageId = newimageid;
		this.title = newtitle;
		this.desc = newdesc;
	}
	
	public int getImageId() {
		return imageId;
	}
	
	public void setImageId(int imageid) {
		this.imageId = imageid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String toString() {
		return title+ "\n" +desc;
		
	}
}
