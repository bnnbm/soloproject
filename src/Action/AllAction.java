package Action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import Action.ActionForward;
import model.Board;
import model.BoardDao;
import model.Likecheck;
import model.LikecheckDao;
import model.Member;
import model.MemberDao;
import model.comment;
import model.commentDao;

public class AllAction {
	private BoardDao dao = new BoardDao();
	private commentDao cdao = new commentDao();
	private LikecheckDao ldao = new LikecheckDao();
	protected String login;
	public ActionForward hello(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("hello", "Hello, World");
		return new ActionForward();
	}
	
	public ActionForward mainform(HttpServletRequest request, HttpServletResponse respinse) {
		int pageNum = 1;
		int limit = 10;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} catch (NumberFormatException e) {
		}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if (column != null && column.equals(""))
			column = null;
		if (find != null && find.equals(""))
			find = null;
		int boardcount = dao.boardCount(column, find, 1);
		List<Board> list = dao.list(pageNum, limit, column, find, 1);

		int boardcount3 = dao.boardCount(column, find, 3);
		List<Board> list3 = dao.list(pageNum, limit, column, find, 3);
		
		List<Likecheck> list4 = ldao.likelist();
		
		int maxpage = (int) ((double) boardcount / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcount - (pageNum - 1) * limit;
		
		request.setAttribute("comcnt", 0);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardcount", boardcount);
		request.setAttribute("list", list);
		request.setAttribute("boardcount3", boardcount3);
		request.setAttribute("list3", list3);
		request.setAttribute("list4", list4);
		request.setAttribute("boardnum", boardnum);
		return new ActionForward();
	}
	/*
   1. multipart/form-data 타입의 전송이므로 MultipartRequest 객체 생성
   1. 파라미터 값을 model.Board 객체 저장.
   2. 게시물번호 num 현재 등록된 num의 최대값을 조회. 최대값+1 등록된 게시물의 번호.
      db에서 maxnum을 구해서 +1 값으로 num 설정하기
   3. board 내용을 db에 등록하기
          등록성공 : 메세지 출력. list.do 페이지이동
          등록실패 : 메세지 출력. writeForm.do 페이지 이동
	 */
	public ActionForward write(HttpServletRequest request, HttpServletResponse respinse) {
		String path = request.getServletContext().getRealPath("/") + "model2/board/file/";
		MultipartRequest multi = null;
		String id = request.getParameter("id");
		Member mem = new MemberDao().selectOne(id);
		request.setAttribute("mem", mem);
		try {
			File f = new File(path);
			if(!f.exists()) f.mkdirs();
			multi = new MultipartRequest(request, path, 100 * 1024 * 1024, "euc-kr");
			Board b = new Board();
			b.setId(multi.getParameter("id"));
			b.setName(multi.getParameter("name"));
			int code = Integer.parseInt(multi.getParameter("code"));
			b.setCode(code);
			b.setSubject(multi.getParameter("subject"));
			b.setContent(multi.getParameter("content"));
			b.setFile1(multi.getFilesystemName("file1"));
			int num = dao.maxnum();
			b.setNum(++num);
			b.setRef(num);
			if (dao.insert(b)) {
				request.setAttribute("msg", "게시물 등록 성공");
				request.setAttribute("url", "info.do?code=" + code +"&num=" + b.getNum());
			} else {
				request.setAttribute("msg", "게시물 등록 실패");
				request.setAttribute("url", "writeForm.do?code=" + code);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ActionForward(false, "../alert.jsp");
	}

	
	public ActionForward writeCom(HttpServletRequest request, HttpServletResponse respinse) {

		comment c = new comment();
		int num = Integer.parseInt(request.getParameter("num"));
		String code = request.getParameter("code");
		c.setNum(num);
		c.setId(request.getParameter("id"));
		c.setCode(request.getParameter("code"));
		c.setName("홍길동");
		c.setContent(request.getParameter("content"));
		int no = 0;
		no = cdao.maxno();
		c.setNo(++no);
		c.setRef(no);
		if (cdao.insert(c)) {
			request.setAttribute("msg", "댓글 등록 성공");
			request.setAttribute("url", "info.do?code=" + code + "&num=" + num);
		} else {
			request.setAttribute("msg", "댓글 등록 실패");
			request.setAttribute("url", "writeComForm.do?code=" + code + "&num=" + num);
		}
		return new ActionForward(false, "../alert.jsp");
	}

	
	/*
	1. 한페이지당 10건의 게시물을 출력하기.
       pageNum 파라미터값을 지정. => 없는 경우는 1로 설정하기.
    2. 최근 등록된 게시물이 가장 위에 배치함.
    3. 화면에 필요한 정보를 속성으로 등록.  
	 */
	public ActionForward list(HttpServletRequest request, HttpServletResponse respinse) {
		int pageNum = 1;
		int limit = 10;
		int code = Integer.parseInt(request.getParameter("code"));
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} catch (NumberFormatException e) {
		}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if (column != null && column.equals(""))
			column = null;
		if (find != null && find.equals(""))
			find = null;
		int boardcount = dao.boardCount(column, find, code);
		List<Board> list = dao.list(pageNum, limit, column, find, code);

		int maxpage = (int) ((double) boardcount / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcount - (pageNum - 1) * limit;
		request.setAttribute("comcnt", 0);
		request.setAttribute("code", code);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardcount", boardcount);
		request.setAttribute("list", list);
		request.setAttribute("boardnum", boardnum);
		
		
		return new ActionForward();
	}
	/*
   1. num 파라미터 정보를 변수에 저장.
   2. num을 이용하여 db에서 게시물 정보를 조회
      Board BoardDao.selectOne(num)
   3. 조회수 증가시키기
      void BoardDao.readcntadd(num)
   4. 조회된 게시물을 화면에 출력
	 */
	public ActionForward info(HttpServletRequest request, HttpServletResponse respinse) {
		
		int code = Integer.parseInt(request.getParameter("code"));		
		int num = Integer.parseInt(request.getParameter("num"));
		Board b = dao.selectOne(num, code); // 게시물 조회
		if(request.getRequestURI().contains("/board/info.do")) {
			dao.readcntadd(num);
		}
		request.setAttribute("b", b);

		// 댓글기능
		int pageNum = 1;
		int limit = 10;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} catch (NumberFormatException e) {
		}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if (column != null && column.equals(""))
			column = null;
		if (find != null && find.equals(""))
			find = null;

		int commentcount = cdao.commentCount(column, find, num, code);
		List<comment> list = cdao.list(pageNum, limit, column, find, num, code);
		dao.comcnt(num,commentcount);

		int maxpage = (int) ((double) commentcount / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("commentcount", commentcount);
		request.setAttribute("list", list);
		request.setAttribute("commentnum", commentcount - ((pageNum - 1) * limit));
		
		int likecnt = new LikecheckDao().likecount(num);
		request.setAttribute("likecnt", likecnt);

		return new ActionForward();
	}
	
	public ActionForward boardDetail(HttpServletRequest request, HttpServletResponse respinse) {
		int code = Integer.parseInt(request.getParameter("code"));
		int num = Integer.parseInt(request.getParameter("num"));
		Board board = dao.selectOne(num, code);
		request.setAttribute("b", board);
		return new ActionForward();
	}
	
	/*
	 * 
   1. 파라미터 값을 Board 객체에 저장하기
          원글정보 : num, grp, grplevel, grpstep
          답글정보 : name, pass, subject, content => 등록정보
   2. 같은 grp 값을 사용하는 게시물들의 grpstep 값을 1 증가 하기.
      void BoardDao.grpStepAdd(grp,grpstep)
   3. Board 객체를 db에 insert 하기.
      num : maxnum + 1
      grp : 원글과 동일.
      grplevel : 원글 + 1
      grpstep : 원글 + 1
	 */
	public ActionForward reply(HttpServletRequest request, HttpServletResponse respinse) {
		Board board = new Board();
		board.setId(request.getParameter("id"));
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setRef(Integer.parseInt(request.getParameter("ref")));
		board.setReflevel(Integer.parseInt(request.getParameter("reflevel")));
		board.setRefstep(Integer.parseInt(request.getParameter("refstep")));
		board.setName(request.getParameter("name"));
		board.setCode(Integer.parseInt(request.getParameter("code")));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));

		request.setAttribute("msg", "답변등록 실패");
		request.setAttribute("url", "replyForm.do?num=" + board.getNum());

		dao.refstepadd(board.getRef(), board.getRefstep());
		int num = dao.maxnum();
		board.setNum(++num);
		board.setReflevel(board.getReflevel() + 1);
		board.setRefstep(board.getRefstep() + 1);
		if (dao.insert(board)) {
			request.setAttribute("msg", "답변등록 성공");
			request.setAttribute("url", "info.do?num=" + board.getNum());
		}
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward replyCom(HttpServletRequest request, HttpServletResponse respinse) {
		comment c = new comment();
		int num = Integer.parseInt(request.getParameter("num"));
		int no = Integer.parseInt(request.getParameter("no"));
		String code = request.getParameter("code");
		int ref = Integer.parseInt(request.getParameter("ref"));
		int reflevel = Integer.parseInt(request.getParameter("reflevel"));
		int refstep = Integer.parseInt(request.getParameter("refstep"));

		c.setId(request.getParameter("id"));
		c.setNo(no);
		c.setNum(num);
		c.setRef(ref);
		c.setReflevel(reflevel);
		c.setRefstep(refstep);
		c.setCode(request.getParameter("code"));
		c.setName(request.getParameter("name"));
		c.setContent(request.getParameter("content"));

		request.setAttribute("msg", "답 댓글등록 실패");
		request.setAttribute("url", "replyComForm.do?no=" + no + "&num=" + num + "&ref=" + ref + "&reflevel=" + reflevel
				+ "&refstep=" + refstep + "&code=" + code);

		cdao.refstepadd(c.getRef(), c.getRefstep());
		no = cdao.maxno();
		c.setNo(++no);
		c.setReflevel(c.getReflevel() + 1);
		c.setRefstep(c.getRefstep() + 1);
		if (cdao.insert(c)) {
			request.setAttribute("msg", "답 댓글등록 성공");
			request.setAttribute("url", "info.do?code=" + code + "&num=" + num);
		}
		return new ActionForward(false, "../alert.jsp");
	}
	/*
   1. 파라미터 정보들을 Board 객체 저장.
   2. 비밀번호 검증
      비밀번호 일치 하는 경우 수정
         파라미터의 내용으로 해당게시물의 내용을 수정하기
         첨부파일의 변경이 없는 경우 file2 파라미터의 내용을 다시 저장하기
      비밀번호 불일치
         비밀번호 오류 메세지 출력하고, updateForm.do로 페이지 이동
   3. 수정성공 : 수정성공 메시지 출력 후 info.do 페이지 이동
           수정실패 : 수정실패 메시지 출력 후 updateForm.do 페이지 이동
	 */
	public ActionForward update(HttpServletRequest request, HttpServletResponse respinse) {
		Board board = new Board();
		String path = request.getServletContext().getRealPath("/") + "model2/board/file/";
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, path, 100 * 1024 * 1024, "euc-kr");
			board.setNum(Integer.parseInt(multi.getParameter("num")));
			board.setId(multi.getParameter("id"));			
			board.setCode(Integer.parseInt((multi.getParameter("code"))));
			board.setSubject(multi.getParameter("subject"));
			board.setContent(multi.getParameter("content"));
			board.setFile1(multi.getFilesystemName("file1"));
			if (board.getFile1() == null || board.getFile1().equals("")) {
				board.setFile1(multi.getParameter("file2"));
			}
			if (dao.update(board)) {
				request.setAttribute("msg", "게시물 수정 완료");
				request.setAttribute("url", "info.do?code=" + board.getCode() +"&num=" + board.getNum());
			} else {
				request.setAttribute("msg", "게시물 수정 실패");
				request.setAttribute("url", "info.do?code=" + board.getCode() +"&num=" + board.getNum());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward updateCom(HttpServletRequest request, HttpServletResponse respinse) {
		comment comment = new comment();
		int num = Integer.parseInt(request.getParameter("num"));
		String code = request.getParameter("code");
		try {
			comment.setNum(num);
			comment.setNo(Integer.parseInt(request.getParameter("no")));
			comment.setId(request.getParameter("id"));
			comment.setCode(code);
			comment.setName(request.getParameter("name"));
			comment.setContent(request.getParameter("content"));

			// request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			// request.setAttribute("url", "updateComForm.do?num=" + comment.getNum());
			if (cdao.update(comment)) {
				request.setAttribute("msg", "댓글 수정 완료");
				request.setAttribute("url", "info.do?code=" + code + "&num=" + num);
			} else {
				request.setAttribute("msg", "댓글 수정 실패");
				request.setAttribute("url", "updateComForm.do?no=" + num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionForward(false, "../alert.jsp");
	}
	/*
   1. num, pass 파라미터를 변수에 저장
   2. 입력된 비밀번호와 db 비밀번호 검증
           틀린경우 : 비밀번호 오류 메세지 출력, deleteForm.do 페이지 이동
   3. 비밀번호가 맞는 경우 : 게시물 삭제.
           삭제 성공 : 삭제 성공 메세지 출력, list.do 페이지 이동
           삭제 실패 : 삭제 실패 메세지 출력, info.do 페이지 이동
	 */
	public ActionForward delete(HttpServletRequest request, HttpServletResponse respinse) {
		int num = Integer.parseInt(request.getParameter("num"));
		int code = Integer.parseInt(request.getParameter("code"));
		try {
			request.setAttribute("msg", "게시물을 삭제하시겠습니까?");
			request.setAttribute("url", "delete.jsp");
			if (dao.delete(num)) {
				request.setAttribute("msg", "게시물 삭제 완료");
				request.setAttribute("url", "list.do?code=" + code);
			} else {
				request.setAttribute("msg", "게시물 삭제 실패");
				request.setAttribute("url", "info.do?num=" + num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward comment(HttpServletRequest request, HttpServletResponse respinse) {
		comment c = new comment();
		c.setNum(Integer.parseInt(request.getParameter("num")));
		c.setId(request.getParameter("id"));
		c.setName(request.getParameter("name"));
		c.setContent(request.getParameter("content"));
		c.setRef(Integer.parseInt(request.getParameter("ref")));
		c.setReflevel(Integer.parseInt(request.getParameter("reflevel")));
		c.setRefstep(Integer.parseInt(request.getParameter("refstep")));

		request.setAttribute("msg", "답변등록 실패");
		request.setAttribute("url", "replyForm.do?num=" + c.getNum());

		dao.refstepadd(c.getRef(), c.getRefstep());
		int num = dao.maxnum();
		c.setNum(++num);
		c.setReflevel(c.getReflevel() + 1);
		c.setRefstep(c.getRefstep() + 1);
		if (cdao.insert(c)) {
			request.setAttribute("msg", "답변등록 성공");
			request.setAttribute("url", "list.do");
		}
		return new ActionForward(false, "../alert.jsp");
	}

	public ActionForward deleteCom(HttpServletRequest request, HttpServletResponse respinse) {
		int no = Integer.parseInt(request.getParameter("no"));
		int num = Integer.parseInt(request.getParameter("num"));
		int code = Integer.parseInt(request.getParameter("code"));
		try {
			if (cdao.delete(no)) {
				request.setAttribute("msg", "댓글 삭제 완료");
				request.setAttribute("url", "info.do?code=" + code + "&num=" + num);
			} else {
				request.setAttribute("msg", "댓글 삭제 오류");
				request.setAttribute("url", "info.do?code=" + code + "&num=" + num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward imgupload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletContext().getRealPath("/") + "model2/board/imgfile/";
		File f = new File(path);			
		if(!f.exists()) f.mkdirs();
		MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"euc-kr");
		String fileName = multi.getFilesystemName("upload");
		request.setAttribute("fileName", fileName);
		request.setAttribute("CKEditorFuncNum", request.getParameter("CKEditorFuncNum"));
		return new ActionForward(false, "ckeditor.jsp");
	}
	
	public ActionForward likecheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Likecheck likecheck = new Likecheck();
		int num = Integer.parseInt(request.getParameter("num"));
		int likenum = Integer.parseInt(request.getParameter("likenum"));
		int code = Integer.parseInt(request.getParameter("code"));
		likecheck.setId(request.getParameter("id"));
		likecheck.setNum(Integer.parseInt(request.getParameter("num")));
		likecheck.setLikenum(Integer.parseInt(request.getParameter("likenum")));
		if(ldao.insert(likecheck) ) {
			if(likenum == 1) {
				request.setAttribute("msg", "좋아요 하셨습니다.");
				request.setAttribute("url", "info.do?code=" + code + "&num=" + num);
			}
		}else {
			request.setAttribute("msg", "이미 좋아요 하셨습니다.");
			request.setAttribute("url", "info.do?code=" + code + "&num=" + num);
		}
		
		
		return new ActionForward(false, "../alert.jsp");
	}
	public ActionForward likeboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int code = Integer.parseInt(request.getParameter("code"));
		List<Board> board = dao.getboard(code);
		System.out.println(board);
		request.setAttribute("board", board);
		return new ActionForward();		
	}
}
