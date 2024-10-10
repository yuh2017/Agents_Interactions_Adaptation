import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Census {
	public int CensusID;
	public int DistrictID;
	
	public int compliancePUM;
	
	public int ABV_200k, LESS_200k, LESS_125k, LESS_75k, LESS_45k,LESS_25k;
	public int ED_non, ED_high, ED_college, ED_bachelor;
	public int ASIAN, BLACK, WHITE, HISPANIC, OTHER;
	public int OWNER, RENTER;
	
	public double HH_PUBA;
	public int HOUSEHOLDS;
	
	public int TRAN_CAR;
	public int TOTALPOP;
	public String[] data;
	// Each agent is expected to interact (INTERACTIONS / N) * 2 times.  (Each interaction uses 2 agents.)
	// We double the expected length of history for safety.
	public Census( ) { }
	public static void printArray(String[] rows) {
	      for (int i = 0; i < rows.length; i++) {
	         System.out.println(i + " , " +rows[i]);
	         System.out.print('\n');
	      }
	      System.out.println("\nlength of the array is "+ rows.length);
	}
	public ArrayList<Census> readFile(String csvFile)  {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<Census> censusAll = new ArrayList<Census>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
					String[] Dline = line.split(cvsSplitBy);
//					printArray(Dline);
					Census censusi = new Census();
					
					censusi.data = Dline;
					
					censusi.ABV_200k   = (int) Float.parseFloat(Dline[1]);
					censusi.LESS_200k = (int) Float.parseFloat(Dline[16] );
					censusi.LESS_125k   = (int) Float.parseFloat(Dline[17]);
					censusi.LESS_75k   = (int) Float.parseFloat(Dline[18]);
					censusi.LESS_45k = (int) Float.parseFloat(Dline[19] );
					censusi.LESS_25k = (int) Float.parseFloat(Dline[20] );
					
					censusi.CensusID   = (int) Float.parseFloat(Dline[5]);
					censusi.DistrictID = (int) Float.parseFloat(Dline[6]);
					
					censusi.ED_non    = (int) Float.parseFloat(Dline[10]);
					censusi.ED_high 	= (int) Float.parseFloat(Dline[9]);
					censusi.ED_college   = (int) Float.parseFloat(Dline[8] );
					censusi.ED_bachelor   = (int) Float.parseFloat(Dline[7]);
					
					
					censusi.ASIAN    = (int) Float.parseFloat(Dline[2]);
					censusi.BLACK = (int) Float.parseFloat(Dline[4]);
					censusi.WHITE   = (int) Float.parseFloat(Dline[36] );
					censusi.HISPANIC   = (int) Float.parseFloat(Dline[14] );
					censusi.OTHER   = (int) Float.parseFloat(Dline[9]);
					
					censusi.OWNER    = (int) Float.parseFloat(Dline[23]);
					censusi.RENTER    = (int) Float.parseFloat(Dline[24]);
					
					censusi.HOUSEHOLDS = (int) Float.parseFloat(Dline[15]);
					censusi.TOTALPOP   = (int) Float.parseFloat(Dline[25] );					
					
					censusAll.add(censusi);
					
	           }
			System.out.println(" Total households " + censusAll.size());
			return censusAll;
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
		return censusAll;
	}
	public void DisplayCensus() {
		System.out.println("Census id "+this.CensusID +
				" TRAN_CAR "+this.TRAN_CAR + "ImprovePUM");
        System.out.println( Arrays.toString(this.data) );
	}

}
