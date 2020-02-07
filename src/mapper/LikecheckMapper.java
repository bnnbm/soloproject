package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.Likecheck;

public interface LikecheckMapper {

	@Select("select count(*) from likecheck where num=#{num}")
	int likecount(int num);

	@Insert("insert into likecheck (id, num, likenum) values (#{id}, #{num}, #{likenum})")
	int insert(Likecheck likecheck);

	@Select("SELECT num,COUNT(*) FROM likecheck GROUP BY num ORDER BY COUNT(*) DESC")
	List<Likecheck> likelist(Object object);

}
