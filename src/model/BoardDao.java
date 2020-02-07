package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mapper.BoardMapper;

public class BoardDao {
	private Class<BoardMapper> cls = BoardMapper.class;
	Map<String,Object> map = new HashMap<String,Object>();
	
	public int maxnum() {
		SqlSession session = DBConnection.getConnecttion();
		try {
			return session.getMapper(cls).maxnum();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return -1;
	}
	public boolean insert(Board board) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			int cnt = session.getMapper(cls).insert(board);
			if(cnt > 0) return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}
	public int boardCount(String column , String find, int code) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			map.clear();
			if(column != null) {
				String[] col = column.split(",");
				map.put("col1",col[0]);
				if(col.length == 2) {
					map.put("col2",col[1]);
				}
				map.put("find",find);
			}
			map.clear();
			map.put("code", code);
			return session.getMapper(cls).boardCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}

	public List<Board> list(int pageNum,int limit,String column, String find, int code) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			map.clear();
			map.put("start", (pageNum-1)*limit);
			map.put("limit", limit);
			if(column != null) {
				String[] col = column.split(",");
				map.put("col1",col[0]);
				if(col.length == 2) {
					map.put("col2",col[1]);
				}
				map.put("find",find);
			}
			map.put("code", code);
			return session.getMapper(cls).select(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
		
	public Board selectOne(int num, int code) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			map.clear();
			map.put("num", num);
			map.put("code", code);
			return session.getMapper(cls).select(map).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public void readcntadd(int num) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			session.getMapper(cls).readcntadd(num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
	}

	public void refstepadd(int ref,int refstep) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			map.clear();
			map.put("ref", ref);
			map.put("refstep", refstep);
			session.getMapper(BoardMapper.class).refstepadd(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
	}
	
	public boolean update(Board board) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			int cnt = session.getMapper(cls).update(board);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}
	public boolean delete(int num) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			int cnt = session.getMapper(cls).delete(num);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}
	public int comcnt(int num, int comcnt) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			map.clear();
			map.put("num", num);
			map.put("comcnt", comcnt);
			return session.getMapper(BoardMapper.class).comcnt(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public List<Board> getboard(int code) {
		SqlSession session = DBConnection.getConnecttion();
		return session.getMapper(cls).getboard(code);
	}
	


}