package model;

public class Likecheck {
	private int num;
	private String id;
	private int likenum;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLikenum() {
		return likenum;
	}
	public void setLikenum(int likenum) {
		this.likenum = likenum;
	}
	@Override
	public String toString() {
		return "Likecheck [num=" + num + ", id=" + id + ", likenum=" + likenum + "]";
	}
	
	
}
