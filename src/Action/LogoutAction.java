package Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Action.ActionForward;
public class LogoutAction extends UserLoginAction {

	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		request.setAttribute("msg", login + "´ÔÀÌ ·Î±×¾Æ¿ô µÇ¼Ì½À´Ï´Ù.");
		request.setAttribute("url", "loginForm.me");
		return new ActionForward(false,"../alert.jsp");
	}
}
