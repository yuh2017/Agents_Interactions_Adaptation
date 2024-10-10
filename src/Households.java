import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Households {
	public double AveHH;
	public int CensusID;
	public int DistrictID;
	public int HHID;
	public int educatL;
	public int incomeL;
	public double lati, longi;
	public int ownership;
	public int racial;
	public int nvehicle;
	public String[] data0 = new String[10];
	// Each agent is expected to interact (INTERACTIONS / N) * 2 times.  (Each interaction uses 2 agents.)
	// We double the expected length of history for safety.
	public Households( ) {
		//this.opinion = new double[(RAModel.INTERACTIONS / RAModel.N) * 4];
	} // Agent
	public static void printArray(String[] rows) {
	      for (int i = 0; i < rows.length; i++) {
	         System.out.println(i + " , " +rows[i]);
	         System.out.print('\n');
	      }
	      System.out.println("\nlength of the array is "+ rows.length);
	}
	
	public ArrayList<Households> readFile(String csvFile)  {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<Households> householdALL = new ArrayList<Households>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
					String[] Dline = line.split(cvsSplitBy);
				//	printArray(Dline);
					Households propertyi = new Households();
					
					this.AveHH      =  Float.parseFloat(Dline[1]);
					this.CensusID   = (int) Float.parseFloat(Dline[2]);
					this.DistrictID = (int) Float.parseFloat(Dline[3]);
					this.HHID  		= (int) Float.parseFloat(Dline[4]);
					this.educatL	= (int) Float.parseFloat(Dline[5]);
					this.incomeL 	= (int) Float.parseFloat(Dline[6]);
					this.lati 		=  (double)Float.parseFloat(Dline[7]);
					this.longi 		=  (double)Float.parseFloat(Dline[8]);
					this.nvehicle 	= (int) Float.parseFloat(Dline[9]);
					this.ownership 	= (int) Float.parseFloat(Dline[10]);
					this.racial 	= (int) Float.parseFloat(Dline[11]);
					
					householdALL.add(propertyi);
					
	           }
			System.out.println(" Total households " + householdALL.size());
			return householdALL;
			}catch (FileNotFoundException e){
				e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		return householdALL;
	}	
	
	
}
