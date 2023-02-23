package jmx;

import java.io.File;

public class Jmx implements JmxMBean {

	// Tomcat 실행파일 경로
	static String tomcatPath = "C:\\apache-tomcat-8.5.85\\bin";

	@Override
	public void startTomcat() {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start " + tomcatPath + "/startup.bat run", null, new File(tomcatPath));
			System.out.println("Tomcat Started(Jmx.java) .....");
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("Tomcat Start Error : " + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void stopTomcat() {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start " + tomcatPath + "/shutdown.bat run", null, new File(tomcatPath));
			System.out.println("Tomcat Stopped(Jmx.java) .....");
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("Tomcat Stop Error : " + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public String getDiskCheck() {
		String drive = null;
		double totalSize = 0;
		double freeSize = 0;
		
		File[] roots = File.listRoots();
		StringBuffer sb = new StringBuffer();
		
		for (File root : roots) {
			drive = root.getAbsolutePath();
			totalSize = root.getTotalSpace() / Math.pow(1024, 3);
			freeSize = root.getFreeSpace() / Math.pow(1024, 3);
			sb.append("Drive : " + drive + " / Total Size : " + Math.floor(totalSize) + " / Free Size : " + Math.floor(freeSize) + "\n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	@Override
	public void killProcess(String processName) {
		Runtime rt = Runtime.getRuntime();
		System.out.println("Process Killed(Jmx.java) .....");
		try {
			rt.exec("taskkill /f /im " + processName);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
