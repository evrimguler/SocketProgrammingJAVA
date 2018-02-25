package Socket4;
import java.io.*;  
import java.net.*;  
import java.util.*;    

public class TcpRouter 
{  
   private static ServerSocket serverSocket; 
   private static InetAddress host;
   private static final int PORT = 1241; 
   private static final int PORT2 = 1240;
   
   private static Socket link2 = null;
   public static void main(String[] args)  
   {  
      System.out.println("Opening port");
      {  
          try  
          {  
              host = InetAddress.getLocalHost();
        	  System.out.println("Enter TcpReceiver IP Address:");
//        	  Scanner readIP = new Scanner(System.in);
//        	  host = readIP.nextLine();
          }  
          catch(Exception uhEx)  
          {  
              System.out.println("Host ID not found!");  
              System.exit(1);  
          }  
           
      }
      try  
      {  
    	  
    	  serverSocket = new ServerSocket(PORT);  //Step 1.
         link2 = new Socket(host,PORT2);
         
      }  
      catch(IOException ioEx)  
      {  
         System.out.println(  
                         "Unable to attach to port for router!");  
         System.exit(1);  
      }  
    
      handleClient();  
      
   }    
   private static String handleClient()  
   {  
      Socket link = null;                        
      String str2 = null;
      try  
      {  
         link = serverSocket.accept();            
         
         Scanner input =  
            new Scanner(link.getInputStream());   
         PrintWriter output =  
              new PrintWriter(  
                 link.getOutputStream(),true);      
         
           String message = input.nextLine(); 
           
           
           Scanner input2 =  
	                new Scanner(link2.getInputStream());      
   
         PrintWriter output2 =  
            new PrintWriter(  
               link2.getOutputStream(),true);       
        
         while (!message.equals("***CLOSE***")){  
        	 System.out.println("message from sender "+message);
        	 
        	 
        	 Random randomGenerator = new Random();
	            int randomInt = randomGenerator.nextInt(100);
	            System.out.println("Generated random number for the packet is: "+randomInt);
	            if (randomInt>19){ //for random probability 20%,each packet has a random number between 0 to 99
     	   
	            	output2.println(message);
	            
        	 String str=input2.nextLine();
        	 System.out.println("message from receiver: "+str);
        	 output.println(str);
	            }
	            else
	            {
	            	output.println(str2);
	            }
        	 message = input.nextLine();
       
        }  
       
         
       }  

       catch(IOException ioEx)  
       {  
           ioEx.printStackTrace();  
       }    
       finally  
       {  
          try  
          {  
             System.out.println(  
                        "\n* Closing connections (Router side)*");  
             link.close();  
             link2.close();
          }  
          catch(IOException ioEx)  
          {  
              System.out.println(  
                            "Unable to disconnect!");  
            System.exit(1);  
          }  
       }
	return null;  
   }  
   
   }
  
