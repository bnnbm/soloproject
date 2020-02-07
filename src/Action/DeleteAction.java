package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.ActionForward;
import model.Member;
import model.MemberDao;
/*
 * 1. id가 관리자인 경우
          "관리자는 탈퇴가 불가능 합니다." 메세지 출력. list.me 페이지 이동
   2  탈퇴조건
         입력된 비밀번호와 db의 비밀번호를 검증
          관리자가 아니면서 비밀번호 불일치 "비밀번호가 틀립니다" 메세지 출력, deleteForm.me 페이지 이동          
   3. 관리자 로그인, 또는 비밀번호 일치하는 경우 db에서 회원정보 삭제하기
      삭제성공 : 일반사용자 : 로그아웃 후, 탈퇴메세지 출력. loginForm.me로 페이지 이동
                     관리자 : 탈퇴메세지 출력. list.me 페이지 이동
      삭제실패 : 일반사용자 : 삭제실패 메세지 출력. info.me 페이지 이동.
                     관리자 : 삭제실패 메세지 출력. list.me 페이지 이동
 */
public class DeleteAction extends UserLoginAction {
	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		if(id.equals("admin")) {
			request.setAttribute("msg", "관리자는 탈퇴가 불가능 합니다.");
			request.setAttribute("url", "list.me");
			return new ActionForward(false,"../alert.jsp");
		}
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String login =(String)request.getSession().getAttribute("login");
		MemberDao dao = new MemberDao();
		Member mem = dao.selectOne(id);
		String msg = id + "님의 비밀번호가 틀립니다.";
		String url = "deleteForm.me?id=" + id;
		if (login.equals("admin") || pass.equals(mem.getPass())) {
			int result = dao.delete(id);
			if(result > 0) {
				if(login.equals("admin")) {
					msg = id + " 사용자를 강제 탈퇴시켰습니다.";
				    url = "list.me";
				} else {
					msg = id +"님의 회원 탈퇴 성공";
					url = "loginForm.me";
					request.getSession().invalidate();
				} 
			} else {
				msg = id + " 님의 탈퇴시 오류 발생.";
			 if(login.equals("admin")) { //관리자인 경우
			    url = "list.me";
			 } else { //일반사용자인 경우
			    url = "info.me?id=" + id;
			}
		}		
		
	}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
   }
}