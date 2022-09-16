package Socket;

import java.io.*;  
import java.net.*;  
import java.util.*;    

public class TcpRouter 
{  
   private static ServerSocket serverSocket; 
   private static InetAddress host;
   private static final int routerPORT = 1241; 
   private static final int serverPORT = 1240;
   private static Socket link2 = null;
   private static int dropPacketSize = 0; 
   
   public static void main(String[] args)  
   {  
      System.out.println("Opening port");
      {  
    	  try  
          {  
		  
			Random roundInteger = new Random();
			dropPacketSize = (int) Integer.valueOf(10)*14/100;
			dropPacketSize += roundInteger.nextInt(2);
			
			int dropPacket[] = new int[dropPacketSize];
			for(int i = 0; i < dropPacket.length; i++)
			{
				if(i==0) 
					dropPacket[i] = roundInteger.nextInt(Integer.valueOf(10)) + 1;
				

			}
			
              host = InetAddress.getLocalHost();  
          }  
          catch(UnknownHostException uhEx)  
          {  
              System.out.println("Host ID not found!");  
              System.exit(1);  
          }  
         

          try // hostu alýyor  
          {  
              
        	  System.out.println("Enter TcpReceiver IP Address:");
        	  Scanner readIP = new Scanner(System.in);
        	  host = InetAddress.getLocalHost();  
          }  
          catch(Exception uhEx)  
          {  
              System.out.println("Host ID not found!");  
              System.exit(1);  
          }  
      }
      
      try  // baðlantýlar yapýlýyor
      {  
    	  
    	  serverSocket = new ServerSocket(routerPORT);  //yazýlan porttaki bu server üzerindeki tüm serversocketleri görsün
         link2 = new Socket(host,serverPORT); //gelen baðlantýyý yazar
         
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
      
      try  // baðlantýyý kabul ediyor.bilgileri cift taraflý yönlendirmesini yazdýrmasý cýkartýrýlmasý
      {  
         link = serverSocket.accept();            
        
         Scanner input =  
            new Scanner(link.getInputStream());  
         PrintWriter output =  
              new PrintWriter(  
                 link.getOutputStream(),true);       
         
           String message = input.nextLine(); 
           
           
           Scanner input2 =  //router içindekileri yazdýrýr .Router içinde okuma yazma yapar
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