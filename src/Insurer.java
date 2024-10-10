import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Insurer {
	public static ArrayList<Double> estimateRisk(Map <Integer, Properties> HouseRisk) {
		ArrayList<Double> InsurInfo = new ArrayList<Double>();
		double totalRisk = 0;
		double nBuyers = 0;
		double Revenue = 0;
		for (Entry<Integer, Properties> entry : HouseRisk.entrySet()) {
			int houseID = entry.getKey();
			Properties floodProperty = entry.getValue();
			double Pr = floodProperty.insurance;
			
			totalRisk = totalRisk + floodProperty.FloodRisk1;
			if(Pr > 0.41){
				nBuyers = nBuyers + 1;
				Revenue = Revenue + floodProperty.insurance;
			}
		}
		InsurInfo.add(totalRisk);
		InsurInfo.add(nBuyers);
		InsurInfo.add(Revenue);
		return InsurInfo;
	}
	public static double[] private_parameter_zone( double aboveBFE, String zone){
		double[] paras = {0, 0};
		double a0_p, b0_c=0;
		if(aboveBFE > 4){aboveBFE = 4;}
		if(aboveBFE < -1){aboveBFE = -1;}
		
		
		if(zone.equals("A")){
			a0_p = Math.exp( aboveBFE * -0.5832 + 1.93 );
//			a0_p = Math.exp( aboveBFE * -0.45 + 2.0 );
		}else if(zone.equals("V")){
			a0_p = Math.exp( -0.3115*aboveBFE + 1.5459 );
//			a0_p = Math.exp( -0.3464*aboveBFE + 1.202 );
		}else{
//			a0_p = Math.exp( -0.544*aboveBFE + 0.57);
			a0_p = Math.exp( -0.6979*aboveBFE + 0.6915);
//			if( aboveBFE >= 1 ){a0_p = 1.31;}
//			else{a0_p = 2.2;}
		}
		paras[0] = a0_p;
		paras[1] = b0_c;
		return paras;
	}
	
	public static double[] privateInsurance( double Value,  String zone, double elevation, double eleHeight, double BFE){
		int Azone = zone.indexOf('A');
		int Vzone = zone.indexOf('V');
		double diff = Math.random()*1.10;
		String zoneAV ;
		if(Azone >= 0 ){
			zoneAV = "A";
		}else if( Vzone >= 0 ){
			zoneAV = "V";
		}else{
			zoneAV = "NA";
		}
		
		double new_elevation = elevation + eleHeight;
		double aboveBFE = new_elevation - (BFE - diff);
		
		double a0_p;
		double[] paras = private_parameter_zone( aboveBFE, zoneAV );
		a0_p = paras[0];
//		System.out.println("zone "+ zone+ " "+zoneAV);
		double caver = a0_p;
		double[] PrivateCost = { 0, 0, 0, 0, 0, 0, 0, 0 };
		
		double Coverage1 = 0, Coverage2 = 0, Coverage3 = 0, Coverage4 = 0;
		double Insurance1 = 0, Insurance2 = 0, Insurance3 = 0, Insurance4 = 0;
		
		if( Value < 60000){
			Coverage1 = 60000;
			Coverage2 = 60000;
			Coverage3 = 250000;
			Coverage4 = 250000;
		}else if( Value >= 60000 & Value < 250000 ){
			Coverage1 = 60000;
			Coverage2 = Value;
			Coverage3 = 250000;
			Coverage4 = 250000;
		}else if( Value >= 250000 & Value < 500000 ){
			Coverage1 = 60000;
			Coverage2 = 155000;
			Coverage3 = 250000;
			Coverage4 = 500000;
		}else{
			Coverage1 = 60000;
			Coverage2 = 155000;
			Coverage3 = 250000;
			Coverage4 = 500000;
		}
		Insurance1 = Coverage1/100*caver; 
		Insurance2 = Coverage2/100*caver; 
		Insurance3 = Coverage3/100*caver; 
		Insurance4 = Coverage4/100*caver; 
		PrivateCost[0] = Coverage1;
		PrivateCost[1] = Insurance1;
		PrivateCost[2] = Coverage2;
		PrivateCost[3] = Insurance2;
		PrivateCost[4] = Coverage3;
		PrivateCost[5] = Insurance3;
		PrivateCost[6] = Coverage4;
		PrivateCost[7] = Insurance4;
		
		return PrivateCost;
	}
	
	
	public static double[] parameter_zone( double aboveBFE, String zone){
		double[] paras = {0, 0};
		double a0_p, b0_c=0;
		if(zone.equals("A")){
			
			if(aboveBFE > 3){
				a0_p = 0.24;
			}else if(aboveBFE > 2 & aboveBFE <= 3){
				a0_p = 0.3;
			}else if(aboveBFE > 1 & aboveBFE <= 2){
				a0_p = 0.42;
			}else if( aboveBFE > 0 & aboveBFE <= 1 ){
				a0_p = 0.71;
			}else if( aboveBFE > 0 & aboveBFE <= -1){
				a0_p = 1.78;
			}else{
				a0_p = 4.4;
			}
			
			
//			a0_p = Math.exp( aboveBFE * -0.5832 + 0.5776 );
		}else if(zone.equals("V")){
			
			if(aboveBFE > 3){
				a0_p = 0.7;
			}else if(aboveBFE > 2 & aboveBFE <= 3){
				a0_p = 0.75;
			}else if(aboveBFE > 1 & aboveBFE <= 2){
				a0_p = 1.01;
			}else if( aboveBFE > 0 & aboveBFE <= 1 ){
				a0_p = 1.27;
			}else if( aboveBFE > 0 & aboveBFE <= -1){
				a0_p = 1.75;
			}else if( aboveBFE > -1 & aboveBFE <= -2){
				a0_p = 2.39;
			}else{
				a0_p = 3.41;
			}
			
//			a0_p = Math.exp( -0.279*aboveBFE + 0.6092 );
		}else{
			
			if(aboveBFE > 3){
				a0_p = 0.1;
			}else if(aboveBFE > 2 & aboveBFE <= 3){
				a0_p = 0.14;
			}else if(aboveBFE > 1 & aboveBFE <= 2){
				a0_p = 0.24;
			}else if( aboveBFE > 0 & aboveBFE <= 1 ){
				a0_p = 0.35;
			}else if( aboveBFE > 0 & aboveBFE <= -1){
				a0_p = 0.71;
			}else{
				a0_p = 1.4;
			}
			
//			a0_p = Math.exp( -0.507*aboveBFE -0.3348 );
//			if( aboveBFE >= 1 ){a0_p = 1.41;b0_c = 0.38;}
//			else{a0_p = 1.7;b0_c = 1.15;}
		}
		paras[0] = a0_p * 1;
		paras[1] = b0_c;	
		return paras;
	}
	
	public static double[] insuranceCost( double Value, String SFHA, double elevation, double eleHeight, double BFE, double Pfactor, double PreFRIM){
		
		double discounts = 1.0;
		if(Pfactor >= 0.75 & Pfactor < 1){
			discounts = 0.9 ;
		}else if(Pfactor > 0.25 & Pfactor < 0.75){
			discounts = 0.75 ;
		}else if(Pfactor > 0 & Pfactor <= 0.25){
			discounts = 0.7 ;
		}else{
			discounts = 1 ;
		}
		
		int Azone = SFHA.indexOf('A');
		int Vzone = SFHA.indexOf('V');
		double[] InsCosts = {0,0, 0,0, 0,0};
		double new_elevation = elevation + eleHeight;
		double aboveBFE;
		aboveBFE = new_elevation - BFE;
		
		double Coverage1 = 0, Coverage2 = 0, Coverage3 = 0;
		double Insurance1 = 0, Insurance2 = 0, Insurance3 = 0;
		if( Value < 60000){
			Coverage1 = 60000;
			Coverage2 = 155000;
			Coverage3 = 250000;
			
		}else if( Value >= 60000 & Value < 250000){
			Coverage1 = 60000;
			Coverage2 = Value;
			Coverage3 = 250000;
			
		}else{
			Coverage1 = 60000;
			Coverage2 = 155000;
			Coverage3 = 250000;
		}
		String zoneAV ;
		if(Azone >= 0 ){
			zoneAV = "A";
		}else if( Vzone >= 0 ){
			zoneAV = "V";
		}else{
			zoneAV = "NA";
		}
		double[] paras = parameter_zone(  aboveBFE, zoneAV);
		double a0_p = paras[0];
		double b0_c = paras[1];
		Insurance1 = a0_p * Coverage1/100 * discounts * PreFRIM; // + b0_c*basicCoverage/100*0.25;
		Insurance2 = a0_p * Coverage2/100 * discounts * PreFRIM; // + b0_c*basicCoverage/100*0.25;
		Insurance3 = a0_p * Coverage3/100 * discounts * PreFRIM; // + b0_c*basicCoverage/100*0.25;
		
		
		InsCosts[0] = Coverage1;
		InsCosts[1] = Insurance1;	
		InsCosts[2] = Coverage2;
		InsCosts[3] = Insurance2;	
		InsCosts[4] = Coverage3;
		InsCosts[5] = Insurance3;	
		return InsCosts;
	}
}
