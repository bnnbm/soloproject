package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.ActionForward;
import model.MemberDao;

public class SearchAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   String email = request.getParameter("email");
		   String tel = request.getParameter("tel");
		   String id = request.getParameter("id");
		   if(id == null) {
			   return idSearch(request,tel,email);
		   } else {
			   return pwSearch(request,id,tel,email);
		   }
	}
		   private ActionForward pwSearch(HttpServletRequest request, String id, String tel, String email) {
			   MemberDao dao = new MemberDao();
			   String pw = dao.pwSearch(id,email,tel);
			   if(pw != null) { //비밀번호 찾기 성공
				   request.setAttribute("pass", pw.substring(2,pw.length()));
				   return new ActionForward();
			   } else { //비밀번호 찾기 실패
				   request.setAttribute("msg", "정보에 맞는 비밀번호를 찾을 수 없습니다.");
				   request.setAttribute("url", "pwForm.me");
				   return new ActionForward(false,"../alert.jsp");
			   }
		   }
		   private ActionForward idSearch(HttpServletRequest request, String tel, String email) {
		   String id = new MemberDao().idSearch(email,tel);
		   String msg= null;
		   String url= null;
		   
		   if(id!=null) {
			   id = id.substring(0, id.length()-2);
			   request.setAttribute("id", id);
			   return new ActionForward();
		   } else {
				   msg="정보에 맞는 id를 찾을 수 없습니다.";
				   url="idForm.me";
				   request.setAttribute("msg", msg);
				   request.setAttribute("url", url);
				   return new ActionForward(false,"../alert.jsp");
		   }
	}
}
