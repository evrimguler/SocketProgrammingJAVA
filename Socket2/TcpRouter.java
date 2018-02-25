package Socket2;
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
              //host = InetAddress.getLocalHost();
        	  System.out.println("Enter TcpReceiver IP Address:");
        	  Scanner readIP = new Scanner(System.in);
//        	  host = readIP.nextLine();
        	  host = InetAddress.getLocalHost();  
          }  
          catch(Exception uhEx)  
          {  
              System.out.println("Host ID not found!");  
              System.exit(1);  
          }  
            
      }
      try  
      {  
    	  
    	  serverSocket = new ServerSocket(PORT);  
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
        	 System.out.print("message from sender "+message+"\t");
        	 output2.println(message);
        	 
        	 String str=input2.nextLine();
        	 System.out.println("message from receiver: "+str);
        	 output.println(str);
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
                        "\n* Closing connectionions*");  
             link.close(); 
             link2.close();//Step 5. 
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
  
