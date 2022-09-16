package Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TcpReceiver {
	private static ServerSocket serverSocket;  // 

	   private static final int serverPORT = 1240; 
	  
	   public static void main(String[] args)  
	   {  
	      System.out.println("Opening port");  
	      try  
	      {  
	         serverSocket = new ServerSocket(serverPORT);   // serversocket  deðiþkenine giren tüm bilgileri server görebilecek
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
	      Socket link = null;       // link deðiþkenini kendimiz oluþturuyoruz                     
	      try  
	      {  // socketi baðladýðýnda serversoket icindeki herþeyi soket okuyabilecek
	         link = serverSocket.accept();             
	         Scanner input =  
	            new Scanner(link.getInputStream());   //okuyor
	         PrintWriter output =  
	              new PrintWriter(  
	                 link.getOutputStream(),true);     //yazdýrýyor
	         int numMessages = 0;  
	           String message = input.nextLine(); 
	         while (!message.equals("***CLOSE***")) // sonsuz döngü oluþturabilmek için
	        
	         {  
	        	 output.println("ACK"+numMessages);// gidilecek paketi gönderiyor
	            numMessages++; 
	            System.out.print(numMessages + ":" + message+"\t"); // her paket arttýrýldýðýnda nummesajý arttýrýyor
	            String msg;
	            	message = input.nextLine();
				
	            if ((numMessages%5)==0) { 
					System.out.println();
				}
	             
	         }  
	        
	       }  

	       catch(Exception ioEx)  
	       {  
	           ioEx.printStackTrace();  
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
	              System.out.println(  //çalýþmýyor ise programý kapat
	                            "Unable to disconnect!");  
	            System.exit(1);  
	          }  
	       }  
	   }  

}
