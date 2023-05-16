import java.util.Scanner;
import java.io.*;

public class main {
    static int memorySize=4;
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in); 


        System.out.println("Please enter the number of partitions : ");



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
            
		System.out.println("Your memory partitions detailes : ");
			
			
	     for (int i=0 ; i<m ; i++) { //Loop display each partition deatiles
         System.out.println("Partion" + (i+1) + " :\n");
		 reportFile.write("Partion" + (i+1) + " :\n");
		 
		 System.out.println("Status : " + getStatus() );
		 reportFile.write("Status : " + getStatus());
		 
		 System.out.println("Size : " + getSize()   );
		 reportFile.write("Status : " + getSize());
		 
		 System.out.println("Starting Address : " + getSadd() );
		 reportFile.write("Status : " + getSadd());
		 
		 System.out.println("Ending Address : " + getEadd() );
		 reportFile.write("Status : " + getEadd() );	 
		 
		 System.out.println("Process ID : " + getPid() );
		 reportFile.write("Status : " + getPid() );	 
		 
		 System.out.println("Internal fragmentation size : " + getInternalFrag() );
		 reportFile.write("Status : " + getInternalFrag());
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


