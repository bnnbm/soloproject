package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mapper.MemberMapper;
//Model 기능을 담당하는 클래스
//Dao : Data Access Object
public class MemberDao {
	private Map<String,Object> map = new HashMap<String,Object>();
	private Class<MemberMapper> cls = MemberMapper.class;
	public Member selectOne(String id) {
		//id : 화면에서 입력된 아이디
		SqlSession session = DBConnection.getConnecttion();
		try {
			map.clear();
			map.put("id",id);
			List<Member> list = session.getMapper(cls).select(map);
			return list.get(0);
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			DBConnection.close(session);
		}
		return null; 
	}
	
	public String select(String id) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			return session.getMapper(cls).select2(id);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	
	public int insert(Member m) {
		SqlSession session = DBConnection.getConnecttion();
		try {
			return session.getMapper(cls).insert(m);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0; //insert 실패
	}
	public List<Member> list() {
		SqlSession session = DBConnection.getConnecttion();
		try {
			return session.getMapper(cls).select(null);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public int update(Member m) {
		//m : 사용자가 입력한 회원 정보, 변경될 내용 
		SqlSession session = DBConnection.getConnecttion();
		try {
		return session.getMapper(cls).update(m);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int delete(String id) {
		SqlSession session = DBConnection.getConnecttion();
		try {
		return session.getMapper(cls).delete(id);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int password(String id, String chgpass) {
		  SqlSession session = DBConnection.getConnecttion();
		  try {
		   return session.getMapper(cls).password(id,chgpass);
		  }catch(Exception e) {
		   e.printStackTrace();
		  }finally {
		   DBConnection.close(session);
		  }
		  return 0;
		 }
	public String idSearch(String email, String tel) {
		  SqlSession session = DBConnection.getConnecttion();
		  String sql = "select id from member where email=? and tel=?";
		  try {
					return session.getMapper(cls).idSearch(email,tel);
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					DBConnection.close(session);
				}
				return null;
			}
	public String pwSearch(String id,String email, String tel) {
		  SqlSession session = DBConnection.getConnecttion();
		  String sql = "select pass from member where id=? and email = ? and tel = ? ";
		  try {
		    return session.getMapper(cls).pwSearch(id,email,tel);
		  }catch(Exception e) {
		   e.printStackTrace();
		  }finally {
		   DBConnection.close(session);
		  }
		  return null;
		 }
}
