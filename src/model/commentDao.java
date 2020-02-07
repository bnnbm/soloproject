package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mapper.BoardMapper;
import mapper.CommentMapper;

public class commentDao {
	private Class<CommentMapper> cls = CommentMapper.class;
	Map<String,Object> map = new HashMap<String,Object>();
	
	public int maxno() {
		SqlSession session = DBConnection.getConnecttion();
		try {
			return session.getMapper(cls).maxno();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return -1;
	}
	public boolean insert(comment comment) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			return session.getMapper(cls).insert(comment);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}
	public int commentCount(String column , String find, int num, int code) {
		SqlSession session = DBConnection.getConnecttion();
		String col1 = column;
		String col2 = null;
		try {
			if(column != null) {
				 String[] cols = column.split(",");
				 col1 = cols[0];
				 if(cols.length == 2) {
					 col2 = cols[1];
				 }
			}
			map.clear();
			map.put("column", col1);
			map.put("find", find);
			map.put("col2", col2);
			map.put("num", num);
			map.put("code", code);
			return session.getMapper(cls).commentCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public List<comment> list(int pageNum,int limit,String column, String find, int num,int code) {
		SqlSession session = DBConnection.getConnecttion();
		String col1 = column;
		String col2 = null;
		try {
			if(column != null) {
				 String[] cols = column.split(",");
				 col1 = cols[0];
				 if(cols.length == 2) {
					 col2 = cols[1];
				 }
			}
			map.clear();
			map.put("start", (pageNum-1)*limit);
			map.put("limit", limit);
			map.put("column", col1);
			map.put("find", find);
			map.put("col2", col2);
			map.put("num", num);
			map.put("code", code);
			return session.getMapper(cls).select(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public comment selectOne(int no, int code) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			map.clear();
			map.put("no", no);
			map.put("code", code);
			return session.getMapper(cls).select(map).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public void refstepadd(int ref,int refstep) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			map.clear();
			map.put("ref", ref);
			map.put("refstep", refstep);
			session.getMapper(cls).refstepadd(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
	}
	
	public boolean update(comment comment) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			session.getMapper(CommentMapper.class).update(comment);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}
	public boolean delete(int no) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			session.getMapper(CommentMapper.class).delete(no);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}

}
