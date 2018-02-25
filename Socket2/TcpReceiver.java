package Socket2;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TcpReceiver {
	private static ServerSocket serverSocket;  

	   private static final int PORT = 1240; 
	  
	   public static void main(String[] args)  
	   {  
	      System.out.println("Opening port");  
	      try  
	      {  
	         serverSocket = new ServerSocket(PORT);   
	      }  
	      catch(IOException ioEx)  
	      {  
	         System.out.println(  
	                         "Unable to attach to port for receiver!");  
	         System.exit(1);  
	      }  
	      
	         handleRouter();    
	   }    
	   private static void handleRouter()  
	   {  
	      Socket link = null;                           
	      try  
	      {  
	         link = serverSocket.accept();             
	         Scanner input =  
	            new Scanner(link.getInputStream());   
	         PrintWriter output =  
	              new PrintWriter(  
	                 link.getOutputStream(),true);     
	         int numMessages = 0;  
	           String message = input.nextLine(); 
	         while (!message.equals("***CLOSE***")) 
	        
	         {  
	        	 output.println("ACK"+numMessages);
	            numMessages++; 
	            System.out.print(numMessages + ":" + message+"\t");
	            String msg;
	            //System.out.println(input.hasNextLine());
	            	message = input.nextLine();
				
	            if ((numMessages%10)==0) {
					System.out.println();
				}
	             
	         }  
	        
	       }  

	       catch(Exception ioEx)  
	       {  
	           //ioEx.printStackTrace();  
	       }    
	       finally  
	       {  
	          try  
	          {  
	             System.out.println(  
	                        "\n* Closing connectionions*");  
	             link.close();                     //Step 5. 
	          }  
	          catch(IOException ioEx)  
	          {  
	              System.out.println(  
	                            "Unable to disconnect!");  
	            System.exit(1);  
	          }  
	       }  
	   }  

}
