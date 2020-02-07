package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.ActionForward;

public abstract class UserLoginAction implements Action {
	protected String login; //�α��� ����
	protected String id; //�Ķ���� ����
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		login = (String)request.getSession().getAttribute("login");
		id = request.getParameter("id");
		
		return doExecute(request,response);
	}
	protected abstract ActionForward doExecute
	   (HttpServletRequest request, HttpServletResponse response);
}
