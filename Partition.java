public class Partition {
	private int startAddress;
	private int endAddress;
   
   private int partition;
   private int fragmentSize;
	private boolean isPartitionStatusFree;
   
	private String processID;
	private int processSize;

	public Partition(int startAddress, int endAddress, int partition) {
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.partition = partition;
		this.fragmentSize = -1;

		this.isPartitionStatusFree = true;
		this.processID = "null";
	}

	/* <========= Setters and Getters =========> */

	public int getStartAddress() {
   return startAddress;
   }

	public void setStartAddress(int startAddress) {
   this.startAddress = startAddress;
   }

	public int getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(int endAddress) {
		this.endAddress = endAddress;
	}

	public int getFragmentSize() {
		return fragmentSize;
	}

	public void setFragmentSize(int fragmentSize) {
		this.fragmentSize = fragmentSize;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public boolean getIsPartitionStatusFree() {
		return isPartitionStatusFree;
	}

	public void setIsPartitionStatusFree(boolean isPartitionStatusFree) {
		this.isPartitionStatusFree = isPartitionStatusFree;
	}

	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}

	public int getProcessSize() {
		return processSize;
	}

	public void setProcessSize(int processSize) {
		this.processSize = processSize;
	}

}