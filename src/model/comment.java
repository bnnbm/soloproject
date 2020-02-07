package model;

import java.util.Date;

public class comment {
	private int no;
	private int num;
	private String id;
	private String code;
	private String name;
	private String content;
	private Date regdate;
	private int ref;
	private int reflevel;
	private int refstep;
	
	//getter,setter,toString
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
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
		return "comment [no=" + no + ", num=" + num + ", id=" + id + ", code=" + code + ", name=" + name + ", content="
				+ content + ", regdate=" + regdate + ", ref=" + ref + ", reflevel=" + reflevel + ", refstep=" + refstep
				+ "]";
	}
		
}
