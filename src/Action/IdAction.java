package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.ActionForward;
import model.MemberDao;

public class IdAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   String email = request.getParameter("email");
		   String tel = request.getParameter("tel");
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
