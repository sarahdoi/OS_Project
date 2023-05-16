import java.util.Scanner;

public class main {
    static int memorySize=4;
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in); 


        System.out.println("Please enter the number of partitions : ");



       printing();
    } 

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
        }
}//end main

        public static void report(){
}
        
public static int first(Partiton[] p,int processSize){
for(int i=0; i<Partiton.length;i++){
p pp=p[i];
if(pp[i].getStatus.equals("Free")&&pp.getSize()>=processSize){return i;}
return -1;}//end loop
}

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
return best;}//end method

public static void deallocate(String pid, Partiton[] p){

boolean found=false;
for(Partiton partition: p){
    if(partition.getPid().equals(pid)){
        partition.setPid("Null");
        partition.setStatus("Free");
        partition.setInternalFrag(-1);
        found=true;
    }
}
if(!found)
    System.out.println("Process not found!!!!")

}


