package jmx;

import java.io.IOException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxClient {
	
	JMXServiceURL url = null;
	JMXConnector jmxc = null;
	MBeanServerConnection mbsc = null;
	ObjectName mbeanName = null;
	JmxMBean jmx = null;
	
	public JmxClient() throws IOException, MalformedObjectNameException {
		url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:7777/server");
		// JmxServiceURL�� ����
		jmxc = JMXConnectorFactory.connect(url, null);
		// MBeanServer�� ����
		mbsc = jmxc.getMBeanServerConnection();
		mbeanName = new ObjectName("ServiceDomain:name=JmxMBean");
		// MBeanServer, mbeanName�� ���ڷ� ���� ����(MBean����) ���
		jmx = JMX.newMBeanProxy(mbsc, mbeanName, JmxMBean.class, true);
	}
	
	public void startTomcat() {
		jmx.startTomcat();
	}
	
	public void stopTomcat() {
		jmx.stopTomcat();
	}
	
	public String getDiskCheck() {
		return jmx.getDiskCheck();
	}
	
	public void killProcess(String processName) {
		jmx.killProcess(processName);
	}
	
	public static void main(String[] args) throws MalformedObjectNameException, IOException {
		new JmxClient();
	}

}
