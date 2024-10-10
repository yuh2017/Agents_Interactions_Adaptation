import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Government {
	public double utilityCost ;
	
	/**
	 * increased premium +2%
	 * monthly utility $ 16
	 */
	
	public Government(){}
	
	public static double TotalSeaWallCosts(double length) {
		double damage;
		damage = 3280840 * length / 1000; 
		return damage;
	}
	
	public static double[] TotalPumpCosts(int N) {
		double[] damage = new double[2];
		damage[0] = 1.937*1000000 * N; 
		damage[1] = 1.937*1000000 * 0.1 * N ;
		return damage;
	}
	
	public static double RiskReduction( double distance  ){
		double ReductionP;
		ReductionP = Math.exp( -8 * distance / 0.03 - 0.98 );
		return ReductionP;
	}
	
	
	public static double RiskReductionSeaWall( double distance  ){
		double ReductionP;
		ReductionP = Math.exp( -14 * distance / 0.03 - 0.48 );
		return ReductionP;
	}
	
	/**
	 * increased premium +5%
	 * monthly utility $8
	 */

	/**
	 * increased premium +8%
	 * monthly utility $4
	 */
	/**
	 * increased premium +15%
	 */
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue( Comparator.reverseOrder() ));
        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
	
	public static double[] residentialCensus(ArrayList<Properties> residentials, int CID){
		double[] value = {0, 0, 0, 0};
		int count = 0;
		ArrayList<Properties> censusResid = new ArrayList<Properties>();
		for (Properties entity : residentials) {
			if (entity.CensusID == CID) {
				censusResid.add(entity);
				
				value[0] += entity.FrealR1;
				value[1] += entity.Justvalue;
				value[2] += entity.liveArea;
				value[3] += entity.elevation;
				count ++;
		    }
		}
		if(count > 0){
			value[3] = value[3] / count;
		}
		return value;
	}
	
	public static double TotalResidentialArea(ArrayList<Properties> residentials){
		Iterator<Properties> iter = residentials.iterator();
		double TotalArea = 0;
		while(iter.hasNext()){
			Properties property = iter.next();
			TotalArea += property.liveArea;
		}
		return TotalArea;
	}
	
	
	public int[] RiskAssessment( ArrayList<Properties> residentials, ArrayList<Census> CensusList, int preserve_N, double p_factor){
		int[] Preserve_censusid = new int[preserve_N];
		double totalArea = TotalResidentialArea(residentials);
		
		if( preserve_N > 0){
			Iterator<Census> iter = CensusList.iterator();
			HashMap<Integer, double[]> hmap_tpvalues = new HashMap<Integer, double[]>();
			HashMap<Integer, Double> hmap_total = new HashMap<Integer, Double>();
			int N_households = residentials.size();
			while(iter.hasNext()){
				Census censusi = iter.next();
				double[] censusi_values = residentialCensus(residentials, censusi.CensusID);
				hmap_tpvalues.put(censusi.CensusID, censusi_values);
				hmap_total.put(censusi.CensusID, censusi_values[0]);
				}
			HashMap<Integer, Double> hmap_tarea_sorted = (HashMap<Integer, Double>) sortByValue(hmap_total);
			int count = 0;
			for(Map.Entry m:hmap_tarea_sorted.entrySet()){  
				System.out.println("Protect "+m.getKey()+" "+m.getValue());  
				this.utilityCost += (double) m.getValue();
				Preserve_censusid[count] = (int) m.getKey();
				count ++;
				if (count >= preserve_N){ break; }
			}
			this.utilityCost = this.utilityCost/ totalArea *p_factor /1.29 ; //1.29
			System.out.println("The utility cost is "+ this.utilityCost);  
		}else{
			this.utilityCost = 0;
			System.out.println("The utility cost is "+ this.utilityCost);  
		}
		return Preserve_censusid;
	}
}
