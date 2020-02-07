package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

import mapper.LikecheckMapper;

public class LikecheckDao {
	private Map<String,Object> map = new HashMap<String, Object>();
	private Class <LikecheckMapper> cls = LikecheckMapper.class;
	
	public int likecount(int num) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			return session.getMapper(cls).likecount(num);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 1;
	}
	
	public boolean insert(Likecheck likecheck) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			int cnt = session.getMapper(cls).insert(likecheck);
			if(cnt>0) return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}
	
	public List<Likecheck> likelist() {
		SqlSession session = DBConnection.getConnecttion();
		try {
			return session.getMapper(cls).likelist(null);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
}
