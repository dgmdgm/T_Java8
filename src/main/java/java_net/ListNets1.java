package java_net;

import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Enumeration;

public class ListNets1
{
	private static final Logger LOG = Logger.getLogger(ListNets1.class);


	public static void main(String args[]) throws SocketException
	{
		// Enumerate network interfaces
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface netint : Collections.list(nets))
		{
			displayInterfaceInformation(netint);
		}
	}


	private static void displayInterfaceInformation(NetworkInterface netint) throws SocketException
	{
		System.out.printf("Display name: %s%n", netint.getDisplayName());
		System.out.printf("Name: %s%n", netint.getName());
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		for (InetAddress inetAddress : Collections.list(inetAddresses))
		{
			LOG.trace(MessageFormat.format("InetAddress: %s%n", inetAddress));
		}
		System.out.printf("%n");
	}
}
