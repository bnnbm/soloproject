package mapper;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Board;

public interface BoardMapper {
	
	@Select("select ifnull(max(num),0) from board")
	int maxnum();

	@Insert("insert into board"
			+ "(num,id,code,name,subject,content,file1,regdate,readcnt,comcnt,ref,reflevel,refstep)"
			+ "values (#{num},#{id},#{code},#{name},#{subject},#{content},#{file1},now(),0,0,#{ref},#{reflevel},#{refstep})")
	int insert(Board board);
	
	@Select({"<script>",
			"select count(*) from board",
			" <if test='code != null'> where code = #{code} </if>",
			" <if test='col1 != null'> and ${col1} like '%${find}%'</if>",
	        " <if test='col2 != null'> OR ${col2} like '%${find}%'</if>",
			"</script>"})
	int boardCount(Map<String, Object> map);
	
	@Update("update board set readcnt = readcnt + 1 where num = #{num}")
	void readcntadd(int num);
	
	@Update("update board set refstep = refstep + 1 where ref = #{ref} and refstep > #{refstep}")
	void refstepadd(Map<String, Object> map);
	
	@Update("update board set id=#{id},subject=#{subject},content=#{content},file1=#{file1} where num = #{num} and code = #{code}")
	int update(Board board);
	
	@Delete("delete from board where num = #{num} ")
	int delete(int num);

	@Update("update board set comcnt=#{comcnt} where num=#{num}")
	int comcnt(Map<String, Object> map);

	
	@Select({"<script>",
        "select * from board",
        " <if test='code != null'> where code = #{code} </if>",
        " <if test='col1 != null'> and ${col1} like '%${find}%'</if>",
        " <if test='col2 != null'> OR ${col2} like '%${find}%'</if>",
        " <choose>",
        " <when test='num != null'>and num = #{num} </when>",
        " <otherwise>"
        + "order by ref desc, refstep asc limit #{start}, #{limit}"
        + "</otherwise>",
        "</choose>",
        "</script>"})
	List<Board> select(Map<String, Object> map);

	@Select("SELECT * FROM board b, (SELECT num,COUNT(*) cnt FROM likecheck GROUP BY num ORDER BY COUNT(*) DESC) a WHERE b.num = a.num AND b.code = #{code} ORDER BY cnt DESC LIMIT 5")
	List<Board> getboard(int code);		
	
}