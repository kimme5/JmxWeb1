package jmx;

import java.io.IOException;

import javax.management.MalformedObjectNameException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Jmx.jsp 웹페이지에서 요청을 받는 서블릿
 */
public class JmxServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String process = req.getParameter("process");
		String command = req.getParameter("command");

		JmxClient jmxClient = null;
		try {
			jmxClient = new JmxClient();
		} catch (MalformedObjectNameException | IOException e) {
			e.printStackTrace();
		}

		System.out.println(">>>>>>>> [JMXServlet POST] command ::: " + command);
		if (command.equals("startTomcat")) {
			jmxClient.startTomcat();
			req.setAttribute("msg", "Tomcat started... ");
		} else if (command.equals("stopTomcat")) {
			jmxClient.stopTomcat();
			req.setAttribute("msg", "Tomcat Stopped... ");
		} else if (command.equals("getDiskCapacity")) {
			String content = jmxClient.getDiskCheck();
			req.setAttribute("msg", content);
		} else if (command.equals("killProcess")) {
			jmxClient.killProcess(process);
			req.setAttribute("msg", "process killed... ");
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jmx.jsp");
		rd.forward(req, resp);
	}
}
