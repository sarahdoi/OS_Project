import java.util.Scanner;
import java.io.*;
import java.util.*;

class invalid extends InputMismatchException{
   invalid(){}                              }

public class main {
   public static void main(String[] args) {

      Scanner input = new Scanner(System.in); 
      int sadd,eadd,numOfpartitions=0;
      boolean e1=true,e2=true,e3=true,e4=true;

    //Number of Parition validation(1)
      while(e1){
         try{
            System.out.println("Please enter the number of partitions : ");
            numOfpartitions=input.nextInt();
            if(numOfpartitions<=0)
               throw new invalid();
            e1=false;
         }catch(invalid e){
            String str=input.next();
            System.out.println("invalid number input, Please try again");
         }
         catch(InputMismatchException  e){
            String str=input.next();
            System.out.println("invalid input, Please enter numbers only.");}
      }//end while1
   
      //start Memory array creation
      Partition[] memory = new Partition[numOfpartitions];
      int partitionSize=0;


      for(int i=0;i<numOfpartitions;i++){
        e2=true;
      //PartionSize validation(2)
         while(e2){
         try { 
         System.out.println("Please enter the size of partition "+(i+1)+" in KB :");
         partitionSize=input.nextInt();
         if (partitionSize<=0)
         throw new invalid();
         e2=false;
         if(i==0){ //if it is the 1st parition set start to 0 
            sadd=0;
         }
         else{
            sadd=memory[i-1].getEadd(); //if it's not the first process then it will be stored after the ending address
         }
         eadd=sadd + partitionSize; 
         
         memory[i]=new Partition(partitionSize,sadd,eadd);

         }catch(invalid e){
            System.out.println("invalid size input, Please try again");
         }
         catch(InputMismatchException e){
            input.next();
            System.out.println("invalid size type input, Please enter numbers only.");
         }
      } //end while2
    }//end loop
   
    String approach="";
   //ApproachInput Validation(3)
      while(e3){
         try{
            System.out.println("Please enter the allocation approach:[ First-fit (F), Best-fit(B), or Worst-fit (W) ]");
            approach=input.next();
            approach=approach.toUpperCase();
            if(!(approach.equals("F")||approach.equals("B")||approach.equals("W")))
               throw new invalid();
            e3=false;
         }catch(invalid e){
            System.out.println("invalid selection input, Please try again.");
         }
         catch(InputMismatchException e){
            input.next();
            System.out.println("invalid type input, Please enter characters only.");
         }
      }//end while3
   
      int selection=0;
      String pname;
      int pSize;
      do{
         try{
         System.out.println("Please Select an Option \n[1] Allocate a block of memory\n[2] De-allocate a block of memory \n[3] Report details\n[4] Exit from program");
         selection=input.nextInt();
          switch(selection){

         //allocate a block of memory
         case 1:
             boolean enter=true;
              while(enter){
              try{
              System.out.println("Please enter a process ID in \"PN\" format:");
              pname=input.next();
              if (pname.length()<=1 )
              throw new invalid();
              else{
              if ( !(pname.substring(0,1).equalsIgnoreCase("P") ) )
               throw new invalid();
               for ( int i=1 ; i<pname.length() ;i++)
               if(!(Character.isDigit(pname.charAt(i)) ) )
               throw new invalid();
                        
                        
               pname = pname.toUpperCase();//unify format with P (uppercase)
               System.out.println("Please enter a process size");
               pSize=input.nextInt();
               enter=false;
               allocate(memory,pname,pSize,numOfpartitions,approach);
                } //end else

                }catch(invalid e){
                System.out.println("invalid processID (PN) format input, Please try again.");
              }catch(InputMismatchException e){
              input.next();
              System.out.println("invalid selection input, Please try again.");
          }}
        break;

         //De-allocate a block memory
        case 2:
              boolean enter1=true;
              while(enter1){
               try{
               System.out.println("Please enter a process ID");
               pname=input.next();
              if (pname.length()<=1 )
              throw new invalid();
              else{
              if ( !(pname.substring(0,1).equalsIgnoreCase("P") ) ) 
              throw new invalid();
              for ( int i=1 ; i<pname.length() ;i++)
              if(!(Character.isDigit(pname.charAt(i)) ) )
              throw new invalid();
              pname = pname.toUpperCase();//unify format with P (uppercase)
             deallocate(pname,memory);
              enter1=false;}
              
            }catch(invalid e){
            System.out.println("invalid processID (PN) format input, Please try again.");
            }catch(InputMismatchException e){
            input.next();
            System.out.println("invalid selection input, Please try again.");   }}
        break;


        //display Report details
       case 3:
            report(memory);
       break;


       case 4:
           System.out.print("Thank you");
       break;

      default:
          System.out.println("please select a valid input"); 

      }//end switch
      }catch(invalid e){
       System.out.println("invalid processID (PN) format input, Please try again.");
      }catch(InputMismatchException e){
      input.next();
      System.out.println("invalid selection input, Please try again.");   }
      } while(selection!=4);
    
   } //end main

   public static void printing(Partition[] p){
      String pname="[";
      for(int j=0;j<p.length;j++){
         if (p[j].getStatus().equalsIgnoreCase("allocated"))
            pname=pname+p[j].getPid()+"|";
         else
            pname=pname+"H|";
      }
     
      pname = pname.substring(0,pname.length()-1);
      pname = pname + "]";
      System.out.println(pname);

   } //end printing method


   public static void allocate(Partition[] p,String processId,int processSize,int numOfpartitions,String approach){
      boolean full= true;
      boolean notExist =true;
      int partitionIndex=-1;
      for(int i=0;i<numOfpartitions;i++)
         if(p[i].getStatus().equals("Free")){
            full=false;
         }
      if(full)
         System.out.println("The memory is full");
      else{
         for(int j=0;j<numOfpartitions;j++)
            if(p[j].getPid().equals(processId)){
               System.out.println("This process is already exist");
               notExist=false;
            }
         if(notExist){
            switch(approach){
               case "F":
                  partitionIndex=firstFit(p,processId, processSize);
                  break;
               case "B":
                  partitionIndex=bestFit(p,processId,processSize);
                  break;
               case "W":
                  partitionIndex=worstFit(p,processId, processSize);
                  break;
            }
            if(partitionIndex!=-1){
               p[partitionIndex].setPid(processId);
               p[partitionIndex].setStatus("Allocated");
               p[partitionIndex].setProcessSize(processSize);
               p[partitionIndex].setInternalFrag(p[partitionIndex].getSize()-p[partitionIndex].getProcessSize());
            
            
            }
            else{
               System.out.println("the process size is larger than any free partition in the memory");
            }
            printing(p);
         }
      
      }
   }

   public static int worstFit(Partition[] p,String processId,int processSize){
    int k =0;
    int large =0;
  boolean found=false;
  while(p[large].getStatus().equalsIgnoreCase("allocated")){
  large++;
  }
  for( k=large+1;k<p.length;k++)
  {
  if(p[k].getStatus().equalsIgnoreCase("Free"))
  if(p[large].getSize()<p[k].getSize()&& p[k].getSize()>=processSize){
  large=k;
  found=true;}
  }

   if((p[large].getSize()>=processSize&&p[large].getStatus().equalsIgnoreCase("Free"))||found){
  return large;
  }
  else {
      return -1;

  }
}



        
   public static int firstFit(Partition[] p,String processId,int processSize){
      for(int i=0; i<p.length;i++){
         Partition pp=p[i];
         if(p[i].getStatus().equals("Free")&&pp.getSize()>=processSize){
            return i;}
      }//end loop
      return -1;
   }//end method first


   public static int bestFit(Partition[] p,String processId,int processSize){
      int best=-1;
      int smallest=10000000;  //or Integer.max();
      for(int i=0; i<p.length;i++){
         Partition pp=p[i];//p pp=p[i];//
         if(pp.getStatus().equals("Free")&&pp.getSize()>=processSize){
            int frag=pp.getSize()-processSize;
            if(frag<=smallest){
               best=i;
               smallest=frag;}
         }
      }//end loop
   return best;}//end method Best

  

   public static void deallocate(String pid, Partition[] p){
      boolean found=false;
      for(int i=0;i<p.length;i++){
         if(p[i].getPid().equals(pid)){
            p[i].setPid("Null");
            p[i].setStatus("Free");
            p[i].setInternalFrag(-1);
            found=true;
         }
      }
      if(!found)
         System.out.println("Process not found!");
      printing(p);
   } //end method deallocate


   public static void report(Partition[] p) { 
      try {
         BufferedWriter reportFile = new BufferedWriter(new FileWriter("report.txt") );
        
         System.out.println("Memory partitions details : ");
         reportFile.write("Memory partitions details : " + "\n");

         System.out.println("-------------------------------");
         reportFile.write("-------------------------------" );
        
         for (int i=0 ; i<p.length ; i++) { //Loop to display each partition details
            System.out.println("(Partion " + (i+1) + ")");
            reportFile.write("\n(Partion " + (i+1) + ")\n");
         
         
            System.out.println("Status : " + p[i].getStatus() );
            reportFile.write("Status : " + p[i].getStatus() + "\n"  );
         
            System.out.println("Size : " + p[i].getSize()   );
            reportFile.write("Size : " + p[i].getSize() + "\n" );
         
            System.out.println("Starting Address : " +  p[i].getSadd() );
            reportFile.write("Starting Address : " + p[i].getSadd()  + "\n"  );
         
            System.out.println("Ending Address : " +  p[i].getEadd() );
            reportFile.write("Ending Address : " + p[i].getEadd()  + "\n"  );	 
         
            System.out.println("Process ID : " + p[i].getPid() );
            reportFile.write("Process ID : " +  p[i].getPid() + "\n" );	 
         
            System.out.println("Internal fragmentation size : " +  p[i].getInternalFrag() );
            reportFile.write("Internal fragmentation size : " +  p[i].getInternalFrag() + "\n"  );
        
            System.out.println("-------------------------------");
            reportFile.write("-------------------------------");

         
         }//end for
      
         reportFile.flush();
         reportFile.close();
       
      }catch( IOException e ) {
         e.printStackTrace(); 
      }//end catch
        
   }//end method report

}//end class

