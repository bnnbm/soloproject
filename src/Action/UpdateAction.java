package Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Action.ActionForward;
import model.Member;
import model.MemberDao;
/*
 * 1. 모든 파라미터 Member 객체에 저장
   2. 입력된 비밀번호와, db에 저장된 비밀번호가 같지 않으면
      "비밀번호가 틀렸습니다." 메세지 출력후, updateForm.jsp 페이지로 이동
   3. 비밀번호가 맞으면 1번의 내용을 db에 수정하기
      int MemberDao.update(Member)
      결과가 0보다 크면 수정성공 메세지 출력. info.jsp 페이지 이동
      결과가 0 이하면 수정 실패. main.jsp 페이지 이동
 */
public class UpdateAction extends UserLoginAction {
	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		   Member m = new Member();
		   m.setId(request.getParameter("id"));
		   m.setPass(request.getParameter("pass"));
		   m.setName(request.getParameter("name"));
		   m.setGender(Integer.parseInt(request.getParameter("gender")));
		   m.setTel(request.getParameter("tel"));
		   m.setEmail(request.getParameter("email"));
		   m.setPicture(request.getParameter("picture"));
		   MemberDao dao = new MemberDao();
		   Member dbmem = dao.selectOne(m.getId());
		   String msg = "비밀번호가 틀렸습니다.";
		   String url = "updateForm.me?id="+m.getId();
		   if(login.equals("admin") || m.getPass().equals(dbmem.getPass())) {
			   if(dao.update(m) > 0) {
				   msg = "수정완료";
				   url = "info.me?id="+m.getId();
			   } else {
				   msg = "수정실패";
				   url = "main.me";
			   }
		   }
		    request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return new ActionForward(false,"../alert.jsp");
	}
}
