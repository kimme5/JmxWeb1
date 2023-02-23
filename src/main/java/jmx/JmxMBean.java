package jmx;

public interface JmxMBean {
	public void stopTomcat();
	public void startTomcat();
	public String getDiskCheck();
	public void killProcess(String processName);
}
