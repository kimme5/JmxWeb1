package jmx;

import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class JmxAgent {
	
	private MBeanServer mbs = null;
	
	public JmxAgent() {
		mbs = MBeanServerFactory.createMBeanServer("ServiceDomain");
		
		Jmx jmxBean = new Jmx();
		ObjectName mbeanName = null;
		JMXServiceURL serviceUrl = null;
		
		try {
			LocateRegistry.createRegistry(7777);
			serviceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:7777/server");
			mbeanName = new ObjectName("ServiceDomain:name=JmxMBean");
			JMXConnectorServer connector = JMXConnectorServerFactory.newJMXConnectorServer(serviceUrl, null, mbs);
			mbs.registerMBean(jmxBean, mbeanName);
			connector.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void waitForEnterPressed() {
		try {
			System.out.println("Presss to continue... ");
			System.in.read();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new JmxAgent();
		System.out.println("SimpleAgent is running... ");
		JmxAgent.waitForEnterPressed();
	}

}
