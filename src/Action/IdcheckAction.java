package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDao;

public class IdcheckAction implements Action{	   
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   MemberDao mdao = new MemberDao();
		   String id = request.getParameter("id");
		   String msg= null;
		   String url= null;
		   if(id.equals("") || id==null) {
			   request.setAttribute("msg", "���̵� �Է��ϼ���");
			   request.setAttribute("url", "idcheckform.me");
		   }
		   if(id.equals(mdao.select(id)) && mdao.select(id).equals(id)) {
			   request.setAttribute("msg", "�ߺ��� ���̵� �Դϴ�.");
			   request.setAttribute("url", "idcheckform.me");
		   } else {
			   request.setAttribute("msg", "��� ������ ���̵� �Դϴ�.");
			   request.setAttribute("url", "idcheckform.me");
			   request.setAttribute("id", id);
		   }
		   return new ActionForward(false,"../alert.jsp");
	}
}

