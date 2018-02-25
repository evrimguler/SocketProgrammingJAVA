package Socket4;
import java.io.*;
import java.net.*;
import java.util.*;

public class TcpSender extends Thread {
	private static InetAddress host;
	private static final int PORT = 1241;

	public static void main(String[] args) {
		try {
			 host = InetAddress.getLocalHost();
			System.out.println("Enter TcpRouter IP Address:");
//			Scanner readIP = new Scanner(System.in);
//			host = readIP.nextLine();
		} catch (Exception uhEx) {
			System.out.println("Host ID not found!");
			System.exit(1);
		}
		accessServer();
	}

	private static void accessServer() {
		Socket link = null;

		try {
			link = new Socket(host, PORT);
			Scanner input = new Scanner(link.getInputStream());

			PrintWriter output = new PrintWriter(link.getOutputStream(), true);

			for (int i = 0; i < 6; i++) {
				
			System.out.println("How many packets? ");
			Scanner userEntry = new Scanner(System.in);
			String message, str2, response;
			int number;

			response = userEntry.nextLine();
			number = Integer.parseInt(response);
			int counter = 0, attempt = 0;
			long startTime = System.nanoTime();
			do {

				message = "PCK";

				output.println(message + counter);
				attempt++;

				link.setSoTimeout(4000); // Setting timer for sender

				String str = input.nextLine();

				str2 = str.substring(0, 3);

				while (!str2.equals("ACK")) {
					System.out.println(message + counter + " Resending...");
					output.println(message + counter);
					attempt++;
					str = input.nextLine();
					str2 = str.substring(0, 3);
				}

				System.out
						.println(str + " received from receiver successfully");

				counter++;

			} while (counter < number);

			long endTime = System.nanoTime();
			System.out.println("Total number of try: " + attempt);
			System.out.println("Time taken to send all packets: "
					+ (endTime - startTime) + " nano seconds.");
			}
			output.println("***CLOSE***");
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			try {
				System.out.println("\n* Closing connections (Sender side)*");
				link.close();
			} catch (IOException ioEx) {
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
