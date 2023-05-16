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
        public static void report(){


        }


}
