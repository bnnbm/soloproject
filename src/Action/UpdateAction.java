package Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Action.ActionForward;
import model.Member;
import model.MemberDao;
/*
 * 1. ��� �Ķ���� Member ��ü�� ����
   2. �Էµ� ��й�ȣ��, db�� ����� ��й�ȣ�� ���� ������
      "��й�ȣ�� Ʋ�Ƚ��ϴ�." �޼��� �����, updateForm.jsp �������� �̵�
   3. ��й�ȣ�� ������ 1���� ������ db�� �����ϱ�
      int MemberDao.update(Member)
      ����� 0���� ũ�� �������� �޼��� ���. info.jsp ������ �̵�
      ����� 0 ���ϸ� ���� ����. main.jsp ������ �̵�
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
		   String msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
		   String url = "updateForm.me?id="+m.getId();
		   if(login.equals("admin") || m.getPass().equals(dbmem.getPass())) {
			   if(dao.update(m) > 0) {
				   msg = "�����Ϸ�";
				   url = "info.me?id="+m.getId();
			   } else {
				   msg = "��������";
				   url = "main.me";
			   }
		   }
		    request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return new ActionForward(false,"../alert.jsp");
	}
}
