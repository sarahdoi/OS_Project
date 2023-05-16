import java.util.Scanner;
import java.io.*;

public class main {
    //static int memorySize=4;
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in); 

int sadd,eadd,numOfpartitions;

        System.out.println("Please enter the number of partitions : ");
numOfpartitions=input.nextInt();

Partition[] meomry=new Partition[numOfpartitions];

for(int i=0;i<numOfpartitions;i++){

 System.out.println("Please enter the size of partition : "+i+1+" in KB");
int partitionSize=input.nextInt();

if(i==0){
    sadd=0;
}
else{
    sadd=meomry[i-1].getEadd()+1;//if it's not the first process then it will be stored after the ending address
}
eadd=sadd + partitionSize -1;

meomry[i]=new partition(partitionSize,sadd,eadd);
}
 System.out.println("Please enter the allocation approch:
 [ First-fit (F), Best-fit(B), or Worst-fit (W) ]");
 String approch=input.next();

       printing();
	   report();
    } //end main

    public static void printing(){
        String pname="[";
        for(int j=0;j<memorySize;j++){
        // if (memory[j].getStatus().equals("Free"))
        // pname=pname+memory[j].getPID()+" | ";
        // else
        pname=pname+"H | ";
        }
        pname=pname+"]";
        System.out.println(pname);
        }//end printing method


     public static void report() { // wil recieve Partition[] p 
		int m=10;
		try {
        BufferedWriter reportFile = new BufferedWriter(new FileWriter("report.txt") );
            
		System.out.println("Your memory partitions details : ");
			
			
	     for (int i=0 ; i<m ; i++) { //Loop display each partition details
         System.out.println("Partion " + (i+1) + " :\n");
		 reportFile.write("Partion " + (i+1) + " :\n");
		 
		 /*
		 System.out.println("Status : " + partition[i].getStatus() );
		 reportFile.write("Status : " + partition[i].getStatus()   );
		 
		 System.out.println("Size : " + partition[i].getSize()   );
		 reportFile.write("Size : " + partition[i].getSize()   );
		 
		 System.out.println("Starting Address : " +  partition[i].getSadd() );
		 reportFile.write("Starting Address : " + partition[i].getSadd()    );
		 
		 System.out.println("Ending Address : " +  partition[i].getEadd() );
		 reportFile.write("Ending Address : " + partition[i].getEadd()    );	 
		 
		 System.out.println("Process ID : " + partition[i].getPid() );
		 reportFile.write("Process ID : " +  partition[i].getPid()  );	 
		 
		 System.out.println("Internal fragmentation size : " +  partition[i].getInternalFrag() );
		 reportFile.write("Internal fragmentation size : " +  partition[i].getInternalFrag()   );
		 */
        }//end for
		
           reportFile.flush();
           reportFile.close();
		   
           }catch( IOException e ) {
			 e.printStackTrace(); 
			}//end catch
			
	}//end method report

        
public static int first(Partiton[] p,int processSize){
for(int i=0; i<Partiton.length;i++){
p pp=p[i];
if(pp[i].getStatus.equals("Free")&&pp.getSize()>=processSize){return i;}
return -1;}//end loop
}//end method first

        public static int Best(Partiton[] p,int processSize){
int best=-1;
int smallest=10000000;  //or Integer.max();
for(int i=0; i<Partiton.length;i++){
p pp=p[i];
if(pp.getStatus.equals("Free")&&pp.getSize()>=processSize){
int frag=pp.getSize()-processSize;
if(frag<=smallest){
best=i;
smallest=frag;}
}
}//end loop
return best;
}//end method Best

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
    System.out.println("Process not found!!!!")

} //end method deallocate

}//end class


