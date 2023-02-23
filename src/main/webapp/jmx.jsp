<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3//DTD HTML 4.01 Transitonal//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JMX</title>
<%
	String path = request.getContextPath();
	String msg = "";
	if(request.getAttribute("msg") != null) {
		msg = (String) request.getAttribute("msg");
	}
%>
<script type="text/javascript">
function stopTomcat() {
	document.frm.command.value = 'stopTomcat';
	document.frm.action = "<%=path %>/jmxServlet";
	document.frm.submit();
}

function startTomcat() {
	document.frm.command.value = 'startTomcat';
	document.frm.action = "<%=path %>/jmxServlet";
	document.frm.submit();
}

function getDiskCheck() {
	document.frm.command.value = 'getDiskCapacity';
	document.frm.action = "<%=path %>/jmxServlet";
	document.frm.submit();
}

function killProcess() {
	document.frm.command.value = 'killProcess';
	var processName = null;
	if(document.frm.process.value == null || document.frm.process.value == "") {
		alert("kill할 process를 입력해 주세요");
		return false;
	} else {
		processName = document.frm.process.value;
		document.frm.action = "<%=path %>/jmxServlet";
		document.frm.submit();
	}
	
}
</script>
</head>
<body>
<form id="frm" name="frm" method="POST">
<input type="hidden" id="command" name="command" value="" />
<P>주의 : 현재 실행중인 Tomcat이 아닌 다른 Tomcat서버로 테스트하세요</P>
<button onclick="startTomcat()">웹서버쪽 톰캣 시작</button>
<button onclick="stopTomcat()">웹서버쪽 톰캣 종료</button>
<br/>
<button onclick="getDiskCheck()">웹서버 용량 확인</button>
<br/>
프로세스명 : <input type="text" id="process" name="process" />
<button onclick="killProcess()">웹서버쪽 프로세스 죽이기</button>
<br/>
<textarea rows="8" cols="100" name="contents"><%=msg%></textarea>
</form>
</body>
</html>