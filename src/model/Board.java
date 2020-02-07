package model;

import java.util.Date;

public class Board {
	private int num;
	private String id;
	private int code;
	private String name;
	private String subject;
	private String content;
	private String file1;
	private Date regdate;
	private int readcnt;
	private int comcnt;
	private int ref;
	private int reflevel;
	private int refstep;
	private int like_it;
	public int setCommentcount;
	
	//getter,setter,toString
	public int getNum() {
		return num;
	}
	public int getLike_it() {
		return like_it;
	}
	public void setLike_it(int like_it) {
		this.like_it = like_it;
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
	public int getCode() {
		return code;
	}
	public void setCode(int i) {
		this.code = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	
	public int getComcnt() {
		return comcnt;
	}
	public void setComcnt(int comcnt) {
		this.comcnt = comcnt;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getReflevel() {
		return reflevel;
	}
	public void setReflevel(int reflevel) {
		this.reflevel = reflevel;
	}
	public int getRefstep() {
		return refstep;
	}
	public void setRefstep(int refstep) {
		this.refstep = refstep;
	}
	@Override
	public String toString() {
		return "Board [num=" + num + ", id=" + id + ", code=" + code + ", name=" + name + ", subject=" + subject
				+ ", content=" + content + ", file1=" + file1 + ", regdate=" + regdate + ", readcnt=" + readcnt
				+ ", comcnt=" + comcnt + ", ref=" + ref + ", reflevel=" + reflevel + ", refstep=" + refstep
				+ ", like_it=" + like_it + ", setCommentcount=" + setCommentcount + "]";
	}

	
	
	
}