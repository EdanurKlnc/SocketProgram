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
	         serverSocket = new ServerSocket(serverPORT);   // serversocket  de�i�kenine giren t�m bilgileri server g�rebilecek
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
	      Socket link = null;       // link de�i�kenini kendimiz olu�turuyoruz                     
	      try  
	      {  // socketi ba�lad���nda serversoket icindeki her�eyi soket okuyabilecek
	         link = serverSocket.accept();             
	         Scanner input =  
	            new Scanner(link.getInputStream());   //okuyor
	         PrintWriter output =  
	              new PrintWriter(  
	                 link.getOutputStream(),true);     //yazd�r�yor
	         int numMessages = 0;  
	           String message = input.nextLine(); 
	         while (!message.equals("***CLOSE***")) // sonsuz d�ng� olu�turabilmek i�in
	        
	         {  
	        	 output.println("ACK"+numMessages);// gidilecek paketi g�nderiyor
	            numMessages++; 
	            System.out.print(numMessages + ":" + message+"\t"); // her paket artt�r�ld���nda nummesaj� artt�r�yor
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
	              System.out.println(  //�al��m�yor ise program� kapat
	                            "Unable to disconnect!");  
	            System.exit(1);  
	          }  
	       }  
	   }  

}
