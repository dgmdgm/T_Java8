package java_net;

import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class ListNets2
{
	private static final Logger LOG = Logger.getLogger(ListNets2.class);



	public static void main(String args[]) throws SocketException
	{
		final Enumeration<NetworkInterface> networkInterfacesEnumeration = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface networkInterface : Collections.list(networkInterfacesEnumeration))
		{
			System.out.println("main: netint=" + String.valueOf(networkInterface));
			LOG.trace(MessageFormat.format("main: netint=%s%n", String.valueOf(networkInterface)));
			displayInterfaceInformation(networkInterface);
		}
	}
	

	private static void displayInterfaceInformation(final NetworkInterface networkInterface) throws SocketException
	{
		LOG.trace(MessageFormat.format("Display name: %s%n", networkInterface.getDisplayName()));
		LOG.trace(MessageFormat.format("Name: %s%n", networkInterface.getName()));
		Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
		for (InetAddress inetAddress : Collections.list(inetAddresses))
		{
			LOG.trace(MessageFormat.format("InetAddress: %s%n", inetAddress));
		}

		LOG.trace(MessageFormat.format("Parent: %s%n", networkInterface.getParent()));
		LOG.trace(MessageFormat.format("Up? %s%n", networkInterface.isUp()));
		LOG.trace(MessageFormat.format("Loopback? %s%n", networkInterface.isLoopback()));
		LOG.trace(MessageFormat.format("PointToPoint? %s%n", networkInterface.isPointToPoint()));
		LOG.trace(MessageFormat.format("Supports multicast? %s%n", networkInterface.isVirtual()));
		LOG.trace(MessageFormat.format("Virtual? %s%n", networkInterface.isVirtual()));
		LOG.trace(MessageFormat.format("Hardware address: %s%n", Arrays.toString(networkInterface.getHardwareAddress())));
		LOG.trace(MessageFormat.format("MTU: %s%n", networkInterface.getMTU()));

		List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
		for (InterfaceAddress addr : interfaceAddresses)
		{
			LOG.trace(MessageFormat.format("InterfaceAddress: %s%n", addr.getAddress()));
		}
		LOG.trace(MessageFormat.format("%n",null));
		Enumeration<NetworkInterface> subInterfaces = networkInterface.getSubInterfaces();
		for (NetworkInterface aNetworkInterface : Collections.list(subInterfaces))
		{
			LOG.trace(MessageFormat.format("%nSubInterface%n", null));
			displayInterfaceInformation(aNetworkInterface);
		}
		LOG.trace(MessageFormat.format("%n", null));
	}

}