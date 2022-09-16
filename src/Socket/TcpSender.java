package Socket;
import java.io.*;  
import java.net.*;  
import java.util.*;

public class TcpSender {
	 private static InetAddress host;  // ýp üzerindeki portlarý görmemizi saðlar
     private static final int router4PORT = 1244;  // en son baðlanacak router portu  
     public static void main(String[] args)  
     {  
         try  
         {  
             
        	 System.out.println("Enter TcpRouter IP Address:");
        	 Scanner readIP = new Scanner(System.in);
        	 host = InetAddress.getLocalHost();  
         }  
         catch(Exception uhEx)  
         {  
             System.out.println("Host ID not found!");  
             System.exit(1);  
         }  
         accessServer();  
     }    
     private static void accessServer()  
     {  
         Socket link = null;                       
         try  
         {  
            link = new Socket(host,router4PORT);    // gelen linki kabul etme yok. baðlamtý oluþturuyor. son reouterla baðlýyorsun 
            Scanner input =  
                new Scanner(link.getInputStream());      
    
            PrintWriter output =  
               new PrintWriter(  
                  link.getOutputStream(),true);
            for (int i = 0; i < 6; i++) {
				
            System.out.println("How many packets? ");
            Scanner userEntry = new Scanner(System.in);    
            String message, response;
            int number;
              response = userEntry.nextLine();
              number = Integer.parseInt(response);
         int  counter = 0, attempt = 0;
         long startTime = System.nanoTime(); // ne kadar sürede program  çalýþýyor
            do  
            {   
                message = "PCK" ;
            	output.println(message+counter);
            	attempt++; // ne kadar istendiði , 
                String str=input.nextLine();
                 if(str!=null) System.out.println("Receiver: "+str+"\t"); //burda yapýlanlar serverda gözükecek
            counter++; // ne kadar whilenýn ne kadar çalýþtýðýný sayar
            if ((counter%5) == 0) {
				System.out.println();
			}
            }while(counter<number);
            long finishTime = System.nanoTime();
            System.out.println("Total number of try: "+attempt); //her seferinde artýrýyor.
            System.out.println("Total time: "+(finishTime-startTime)+" nano seconds."); // eþit olduðunda bitiriyor.
            }
           output.println("***CLOSE***"); 
          }  
          catch(IOException ioEx)  
          {  
             //ioEx.printStackTrace();  
          }    
          finally  
          {  
              try  
              {  
                 System.out.println(  
                           "\n* Closing connections*");  
                 link.close();                    
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