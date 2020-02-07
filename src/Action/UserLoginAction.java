package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.ActionForward;

public abstract class UserLoginAction implements Action {
	protected String login; //로그인 정보
	protected String id; //파라미터 정보
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		login = (String)request.getSession().getAttribute("login");
		id = request.getParameter("id");
		
		return doExecute(request,response);
	}
	protected abstract ActionForward doExecute
	   (HttpServletRequest request, HttpServletResponse response);
}
