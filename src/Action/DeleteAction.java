package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.ActionForward;
import model.Member;
import model.MemberDao;
/*
 * 1. id�� �������� ���
          "�����ڴ� Ż�� �Ұ��� �մϴ�." �޼��� ���. list.me ������ �̵�
   2  Ż������
         �Էµ� ��й�ȣ�� db�� ��й�ȣ�� ����
          �����ڰ� �ƴϸ鼭 ��й�ȣ ����ġ "��й�ȣ�� Ʋ���ϴ�" �޼��� ���, deleteForm.me ������ �̵�          
   3. ������ �α���, �Ǵ� ��й�ȣ ��ġ�ϴ� ��� db���� ȸ������ �����ϱ�
      �������� : �Ϲݻ���� : �α׾ƿ� ��, Ż��޼��� ���. loginForm.me�� ������ �̵�
                     ������ : Ż��޼��� ���. list.me ������ �̵�
      �������� : �Ϲݻ���� : �������� �޼��� ���. info.me ������ �̵�.
                     ������ : �������� �޼��� ���. list.me ������ �̵�
 */
public class DeleteAction extends UserLoginAction {
	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		if(id.equals("admin")) {
			request.setAttribute("msg", "�����ڴ� Ż�� �Ұ��� �մϴ�.");
			request.setAttribute("url", "list.me");
			return new ActionForward(false,"../alert.jsp");
		}
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String login =(String)request.getSession().getAttribute("login");
		MemberDao dao = new MemberDao();
		Member mem = dao.selectOne(id);
		String msg = id + "���� ��й�ȣ�� Ʋ���ϴ�.";
		String url = "deleteForm.me?id=" + id;
		if (login.equals("admin") || pass.equals(mem.getPass())) {
			int result = dao.delete(id);
			if(result > 0) {
				if(login.equals("admin")) {
					msg = id + " ����ڸ� ���� Ż����׽��ϴ�.";
				    url = "list.me";
				} else {
					msg = id +"���� ȸ�� Ż�� ����";
					url = "loginForm.me";
					request.getSession().invalidate();
				} 
			} else {
				msg = id + " ���� Ż��� ���� �߻�.";
			 if(login.equals("admin")) { //�������� ���
			    url = "list.me";
			 } else { //�Ϲݻ������ ���
			    url = "info.me?id=" + id;
			}
		}		
		
	}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
   }
}