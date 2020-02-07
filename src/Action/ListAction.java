package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.ActionForward;
import model.MemberDao;

public class ListAction extends AdminLoginAction {
	@Override
	protected ActionForward adminExecute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		request.setAttribute("list", dao.list());
		return new ActionForward();
	}
}
