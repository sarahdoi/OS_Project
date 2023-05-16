import java.util.Scanner;
import java.io.*;

public class main {
    //static int memorySize=4;
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in); 

int sadd,eadd,numOfpartitions;

        System.out.println("Please enter the number of partitions : ");
numOfpartitions=input.nextInt();

Partition[] memory = new Partition[numOfpartitions];

for(int i=0;i<numOfpartitions;i++){

 System.out.println("Please enter the size of partition : "+(i+1)+" in KB");
int partitionSize=input.nextInt();

if(i==0){
    sadd=0;
}
else{
    sadd=memory[i-1].getEadd()+1;//if it's not the first process then it will be stored after the ending address
}
eadd=sadd + partitionSize -1;

memory[i]=new Partition(partitionSize,sadd,eadd);
}
 System.out.println("Please enter the allocation approch:[ First-fit (F), Best-fit(B), or Worst-fit (W) ]");
 String approach=input.next();

 int selection;
 do{
     System.out.println("Please Select an Option \n[1] Allocate a block of memory\n[2] De-allocate a block memory \n[3] Report details\n[-1] Exit from program");
     selection=input.nextInt();
     switch(selection){
         case 1: 
         allocate();
         break;
         case 2:
         //deallocate();
         break;
         case 3:
         //report();
         break;
         case -1:
         System.out.print("Thank you");
         break;
         default:
         System.out.print("please select a valid input");
     }
 }
 while(selection!=-1);
	 
    } //end main

    public static void printing(Partition[] p){
        String pname="[";
        for(int j=0;j<memorySize;j++){
        if (p[j].getStatus().equalsIgnoreCase("Free"))
        pname=pname+p[j].getPid()+" | ";
        else
        pname=pname+"H | ";
        }
        pname=pname+"]";
        System.out.println(pname);
        }

//end printing method
public static void allocate(Partition[] p,String processId,int processSize){
    boolean full= true;
    boolean notExist =true;
   for(int i=0;i<memorySize;i++)
        if(p[i].getStatus().equals("Free")){
            full=false;
        }
    if(full)
    System.out.print("The memory is full");
    else{
        for(int j=0;j<memorySize;j++)
            if(p[j].getPid().equals(processId)){
                notExist=false;
            }
        if(notExist){
        switch(approach){
        case 1:
        firstFit( p,processId, processSize);
        break;
        case 2:
        bestFit(p,processId,processSize);
        break;
        case 3:
        worstFit(p,processId, processSize);
        break;
    }
}

    }
}

public static int worstFit(Partition[] p,String processId,int processSize){
    int large =0;
    boolean found=false;
    while(p[large].getStatus().equalsIgnoreCase("allocated")){
    large++;
    }
    for(int k=large+1;k<memorySize-1;k++)
    {
    if(p[k].getStatus().equalsIgnoreCase("Free"))
    if(p[large].getSize()<p[k].getSize()&& p[k].getSize()>=processSize)
    large=k;
    found=true;
    }
    if(found||p[large].getSize()>=processSize){
    p[large].setPid(processId);
    p[large].setProcessSize(processSize);
    }
    else {
        System.out.println("the process size is larger than any free partion in the memory");

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
//for(Partition partition: meomry){
    for(int i=0;i<p.length;i++){
    if(p[i].getPid().equals(pid)){
        p[i].setPid("Null");
        p[i].setStatus("Free");
        p[i].setInternalFrag(-1);
        found=true;
    }
}
if(!found)
    System.out.println("Process not found!!!!");

} //end method deallocate


public static void report(Partition[] p) { 
    //int m=10;
    try {
    BufferedWriter reportFile = new BufferedWriter(new FileWriter("report.txt") );
        
    System.out.println("Your memory partitions details : ");
        
        
     for (int i=0 ; i<p.length ; i++) { //Loop to display each partition details
     System.out.println("Partion " + (i+1) + " :\n");
     reportFile.write("Partion " + (i+1) + " :\n");
     
     
     System.out.println("Status : " + p[i].getStatus() );
     reportFile.write("Status : " + p[i].getStatus()   );
     
     System.out.println("Size : " + p[i].getSize()   );
     reportFile.write("Size : " + p[i].getSize()   );
     
     System.out.println("Starting Address : " +  p[i].getSadd() );
     reportFile.write("Starting Address : " + p[i].getSadd()    );
     
     System.out.println("Ending Address : " +  p[i].getEadd() );
     reportFile.write("Ending Address : " + p[i].getEadd()    );	 
     
     System.out.println("Process ID : " + p[i].getPid() );
     reportFile.write("Process ID : " +  p[i].getPid()  );	 
     
     System.out.println("Internal fragmentation size : " +  p[i].getInternalFrag() );
     reportFile.write("Internal fragmentation size : " +  p[i].getInternalFrag()   );
     
    }//end for
    
       reportFile.flush();
       reportFile.close();
       
       }catch( IOException e ) {
         e.printStackTrace(); 
        }//end catch
        
}//end method report

}//end class


