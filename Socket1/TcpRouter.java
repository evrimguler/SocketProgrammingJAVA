package Socket1;
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
   private static int counter = 0;
   private static int dropPacketSize = 0;
   
   public static void main(String[] args)  
   {  
      System.out.println("Opening port");
      {  
          try  
          {  
		  
			Random roundInteger = new Random();
//			dropPacketSize = (int) Integer.valueOf(args[0])*14/100;
			dropPacketSize = (int) Integer.valueOf(10)*14/100;
			dropPacketSize += roundInteger.nextInt(2);
			
			int dropPacket[] = new int[dropPacketSize];
			for(int i = 0; i < dropPacket.length; i++)
			{
				if(i==0)
					dropPacket[i] = roundInteger.nextInt(Integer.valueOf(10)) + 1;
//					dropPacket[i] = roundInteger.nextInt(Integer.valueOf(args[0])) + 1;
//				else
					/*chooseNumber = roundInteger.nextInt(Integer.valueOf(args[0])) + 1;
					for( int j=0; j<i ; j++)
					{
						if dropPacket[]
					}*/   //For this part, I need to think about it.
			}
			
              host = InetAddress.getLocalHost();  
          }  
          catch(UnknownHostException uhEx)  
          {  
              System.out.println("Host ID not found!");  
              System.exit(1);  
          }  
          //accessServer();  
      }
      try  
      {  
    	  
    	  serverSocket = new ServerSocket(PORT);  //Step 1.
         link2 = new Socket(host,PORT2);
         // link2 = new Socket(host, PORT2);
      }  
      catch(IOException ioEx)  
      {  
         System.out.println(  
                         "Unable to attach to port for router!");  
         System.exit(1);  
      }  
      //do  
      //  
      //	String  message = 
      handleClient();  
      //   if(message != null) {
       // 	 AccessReceiver(message);
         
        // }
     // }while (true);  
   }    
   private static String handleClient()  
   {  
      Socket link = null;                        //Step 2.  
      
      try  
      {  
         link = serverSocket.accept();           //Step 2. 
         
         Scanner input =  
            new Scanner(link.getInputStream());  //Step 3. 
         PrintWriter output =  
              new PrintWriter(  
                 link.getOutputStream(),true);   //Step 3.    
        // int numMessages = 0;  
           String message = input.nextLine(); //Step 4.
           // output.println("Message ...." );
           
           Scanner input2 =  
	                new Scanner(link2.getInputStream());      
//Step 2.    
         PrintWriter output2 =  
            new PrintWriter(  
               link2.getOutputStream(),true);     //Step 2.  
         while (!message.equals("***CLOSE***")){  
        	 System.out.println("message from sender"+message);
        	 output2.println(message);
        	 
        	 String str=input2.nextLine();
        	 System.out.println("message from receiver: "+str);
        	 output.println(str);
        	 message = input.nextLine();
       //  do {  
           // System.out.println("Message received.");  
           // numMessages++;  
           // output.println("Message " + numMessages  
            //               + ": " + message);   //Step 4. 
          //  message = input.nextLine(); 
           
          //  AccessReceiver(message);
        	 //return message;
        }  
       //  output.println(numMessages  
        //             + " messages received.");  //Step 4. 
         
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
                        "\n* Closing connection *");  
             link.close();                     //Step 5. 
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
   /*
   private static void AccessReceiver(String message) {
	   try  
       {  
              
		   Scanner input =  
	                new Scanner(link.getInputStream());      
//Step 2.    
          PrintWriter output =  
             new PrintWriter(  
                link.getOutputStream(),true);     //Step 2.  
          output.println(message);
          //Set up stream for keyboard entry� 
         // Scanner userEntry = new Scanner(System.in);    
         // String message, response;  
          do  
         {  
            //  System.out.print("Enter message: ");  
             // message = userEntry.nextLine();  
            //   output.println(message);        //Step 3. 
            //  response = input.nextLine();    //Step 3. 
             //   System.out.println("\nSERVER> "+response);  
          }while (!message.equals("***CLOSE***"));  
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
                         "\n* Closing connection�*");  
               link.close();                   //Step 4. 
            }  
            catch(IOException ioEx)  
            {  
               System.out.println(  
                           "Unable to disconnect!");  
               System.exit(1);  
            }  
        }  
    }
   */
   }
  
