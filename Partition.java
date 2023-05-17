
public class Partition{

private int Size, Sadd, Eadd,InternalFrag,processSize;
private String Pid,Status;

public Partition(int size, int sadd, int eadd){

Size=size;
Sadd=sadd;

Eadd=eadd;
InternalFrag=-1;//intilize since it won't be given by the user
Pid="NULL";//intilize since it won't be given by the user
Status="Free";//intilize since it won't be given by the user
}

public Partition(){}

//=========Getters==================
public int getSize(){ return Size;}

public int getSadd(){ return Sadd;}

public int getEadd(){ return Eadd;}

public String getPid(){ return Pid;}

public String getStatus(){ return Status;}

public int getInternalFrag(){ return InternalFrag;}

public int getProcessSize(){ return processSize;}


//================== Setters=============

public void setSize(int s){Size=s;}

public void setSadd(int s){Sadd=s;}

public void setEadd(int e){Eadd=e;}

public void setPid(String id){Pid=id;}

public void setStatus(String status){Status=status;}

public void setInternalFrag(int internalfrag){InternalFrag=internalfrag;}

public void setProcessSize(int processSize){this.processSize=processSize;}
}//end of class