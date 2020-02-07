package mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.comment;

public interface CommentMapper {
	
	@Select("select ifnull(max(no),0) from comment")
	int maxno();

	@Insert("insert into comment"
			+ "(no,num,id,code,name,content,regdate,ref,reflevel,refstep)"
			+ "values (#{no},#{num},#{id},#{code},#{name},#{content},now(),#{ref},#{reflevel},#{refstep})")
	boolean insert(comment comment);
	
	@Select("select count(*) from comment where num=#{num}")
	int commentCount(Map<String, Object> map);
	
	@Update("update comment set refstep = refstep + 1 where ref = #{ref} and refstep > #{refstep}")
	void refstepadd(Map<String, Object> map);
	
	@Update("update comment set name=#{name},content=#{content} where no = #{no}")
	boolean update(comment comment);
	
	@Delete("delete from comment where no = #{no} ")
	boolean delete(int no);
	
	@Select("select * from comment where code=#{code} and num=#{num}")
	void selectCode(int code, int num);

	@Select({"<script>",
        "select * from comment",
        " <if test='col1 != null'>where ${col1} like '%${find}%'</if>",
        " <if test='col2 != null'>OR ${col2} like '%${find}%'</if>",
        "  where 1=1 and code=#{code}",
        " <choose>",
        " <when test='num != null'>and num = #{num} </when>",
        " <otherwise>"
        + "order by ref desc, refstep asc limit #{start}, #{limit}"
        + "</otherwise>",
        "</choose>",
        "</script>"})
	List<comment> select(Map<String, Object> map);

}
