package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.ActionForward;

@WebServlet(urlPatterns= {"*.me"},
		initParams= {@WebInitParam(name="properties",value="action.properties")})
public class ControllerServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// commandMap : action.properties 파일의 정의된 클래스 객체화 하여 저장
	private Map<String,Action> commandMap = new HashMap<String,Action>();
    
	// init 메서드 : 서블릿 클래스가 객체화 시 한번 호출되는 메서드
	// config : properties = action.properties Map 객체를 저장함.
    @Override
	public void init(ServletConfig config) throws ServletException {
			String props = config.getInitParameter("properties");
			Properties pr = new Properties();
			FileInputStream f = null;
			try {
	// f : /WebContent/WEB-INF/action.properties
				f = new FileInputStream
						(config.getServletContext().getRealPath("/")+"WEB-INF/" + props);
				//pr : /model2/hello.me = action.HelloAction
				pr.load(f);
			} catch(IOException e) {
				throw new ServletException(e);
			} finally {
				try {
					if(f != null)f.close();
				} catch(IOException e) {}
			}
			for(Object k : pr.keySet()) {
				String command = (String)k; // "model2/hello.me"
				//className : action.HelloAction 문자열
				String className = pr.getProperty(command);
				try {
					// className 문자열을 클래스화 시킴. 클래스영역 해당 클래스 로드
					// commandClass : action.HelloAction 클래스 저장
					Class commandClass = Class.forName(className);
					// commandObj : action.HelloAction 클래스의 객체로 생성하여 저장
					Object CommandObj = commandClass.newInstance();
					// "/model2/hello.me",action.HelloAction 객체
					commandMap.put(command,(Action)CommandObj);
				} catch(Exception e) {
					throw new ServletException(e);
				}
			}
    }
	public ControllerServelet() {}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("euc-kr");
    	Action action = null;
    	ActionForward forward = null;
    	String command = null;
    	try {
    		// command = /model2/hello.me
    		command = request.getRequestURI().substring(request.getContextPath().length());
    		// action :  HelloAction 객체 저장
    		action = commandMap.get(command);
    		// forward : new ActionForward(false,"hello.jsp");
    		forward = action.execute(request, response);
    	}catch(NullPointerException e) {
    		forward = new ActionForward(false,null);
    	} catch(Exception e) {
    		throw new ServletException(e);
    	}
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getView());
    		} else {
    			if(forward.getView() == null) {
    				forward.setView(command.replace(".me", ".jsp"));
    			}
    			RequestDispatcher disp = 
    					request.getRequestDispatcher(forward.getView());
    			disp.forward(request, response); // hello.jsp 페이지 호출
    		}
    	} else response.sendRedirect("nopage.jsp");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
