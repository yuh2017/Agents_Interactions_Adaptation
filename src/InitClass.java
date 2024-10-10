import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class InitClass {
	public static double k_0 = 0.23, beta_0 = 0.31, mu_0 = 5.42;
	public static double[] probs  = {0.05, 0.02, 0.01, 0.05, 0.002};
	public static ArrayList<Households> readAgents(String csvFile2) {
		ArrayList<Households> householdALL = new Households().readFile(csvFile2);
		System.out.println("Finished reading residential parcels!");
		return householdALL;
	}
	
	public static ArrayList<Properties> readProperties(String csvFile2) {
		ArrayList<Properties> householdALL = new Properties().readFile(csvFile2);
		System.out.println("Finished reading residential parcels!");
		return householdALL;
	}
	
	public static ArrayList<Census> readCensuses(String csvFile2) {
		ArrayList<Census> householdALL = new Census().readFile(csvFile2);
		System.out.println("Finished reading Census!");
		return householdALL;
	}
	
	public static HashMap<Integer, Census> censusDict(ArrayList<Census> CensusList){
		HashMap<Integer, Census> hash_map = new HashMap<Integer, Census>(); 
		for (Census Censusi : CensusList) {
			hash_map.put(Censusi.CensusID, Censusi);
		}
		return hash_map;
	}
	
	public static ArrayList<Properties> residentialCensus(ArrayList<Properties> residentials, int CID){
		ArrayList<Properties> censusResid = new ArrayList<Properties>();
		for (Properties entity : residentials) {
			if (entity.CensusID == CID) {
				censusResid.add(entity);
		    }
		}
		return censusResid;
	}
	public static ArrayList<Households> HHCensus( ArrayList<Households> households, int CID ){
		ArrayList<Households> censushhs = new ArrayList<Households>();
		for (Households hh : households) {
			if (hh.CensusID == CID) {
				censushhs.add(hh);
		    }
		}
		return censushhs;
	}
	
	public static double InitialSurgeHeight( double cateSH5, double elevation ) {
		double x = cateSH5 + elevation;
		double surgeH;
		Random r = new Random();
		double height;
		if(x < 0) {
			height = mu_0 - 0.05*mu_0 + (mu_0*0.1) * r.nextDouble();
			surgeH = height;
		}else {
			height = x - ( ( Math.pow( 500, k_0 ) - 1 ) * beta_0 / k_0  );
			surgeH = height - 0.05*height + (height*0.1) * r.nextDouble();
		}
		return surgeH ;
	}
	
	public static void AdaptDecision( ArrayList<Properties> ResidBidPrice, ArrayList<Census> CensusList ){
		Iterator<Properties> iter = ResidBidPrice.iterator();
		
		while(iter.hasNext()){
			Properties housei = iter.next();
			int Azone = housei.zone.indexOf('A');
			int Vzone = housei.zone.indexOf('V');
			String zoneAV ;
			if(Azone >= 0 ){
				zoneAV = "A";
			}else if( Vzone >= 0 ){
				zoneAV = "V";
			}else{
				zoneAV = "NA";
			}
			if( housei.elevation < 0 & housei.cate5 >= 0 ){
				housei.elevation = 1;
			}
			housei.initialHeight = InitialSurgeHeight( housei.cate5, housei.elevation );
			housei.initialHeightSLR1 = housei.initialHeight + 0.3969; // 0.3969   0.5638   1.488
			housei.initialHeightSLR2 = housei.initialHeight + 0.5638; // 0.3969   0.5638   1.488
			housei.initialHeightSLR3 = housei.initialHeight + 1.488; // 0.3969   0.5638   1.488
			
			if( zoneAV.equals("A") | zoneAV.equals("V")){
				housei.BFE = ( ( Math.pow( 100, k_0 ) - 1 ) * beta_0 / k_0  ) + housei.initialHeight - 2.8;
			}else{
				housei.BFE = ( ( Math.pow( 100, k_0 ) - 1 ) * beta_0 / k_0  ) + housei.initialHeight - 2.8;
			}
			
			/** in case of some extreme large values in house property*/
			if( housei.elevation < 0 & housei.cate5 < 0 ){
				housei.elevation = housei.BFE + 5;
			}
			
			double[] Rrisk0 	= housei.QuantifyRisk(0 , 0, 1, 0, 0, 0);
			double[] SLR1Rrisk1 = housei.QuantifySLRRisk(0, housei.initialHeightSLR1, 0, 1, 0, 0, 0);
			double[] SLR2Rrisk2 = housei.QuantifySLRRisk(0, housei.initialHeightSLR2, 0, 1, 0, 0, 0);
			double[] SLR3Rrisk3 = housei.QuantifySLRRisk(0, housei.initialHeightSLR3, 0, 1, 0, 0, 0);
			
			housei.FloodRisk1 = Rrisk0[0];
			housei.FrealR1    = Rrisk0[1];
			
			
			housei.SLR1realRisk1     = SLR1Rrisk1[1];
			housei.SLR2realRisk1     = SLR2Rrisk2[1];
			housei.SLR3realRisk1     = SLR3Rrisk3[1];
		}
		Government governer1 = new Government();
		double pfactor = 0.5;
		int[] preserve_list1 = governer1.RiskAssessment(ResidBidPrice, CensusList, 100, pfactor); /**protect number of census */
		double utilityCost1  = governer1.utilityCost;
		Iterator<Properties> iter2 = ResidBidPrice.iterator();
		int N_inst = 0;
		int adoptA = 0, countAV = 0, adoptV = 0, adoptX = 0, adoptPrivate = 0, Nmeasures = 0;
		double adaptRisk = 0, nonAdaptRisk = 0, totalSpending = 0;
		double SLRadaptRisk = 0, SLRnonAdaptRisk = 0;
		while(iter2.hasNext()){
			Properties housei = iter2.next();
			int Azone = housei.zone.indexOf('A');
			int Vzone = housei.zone.indexOf('V');
			String zoneAV ;
			if(Azone >= 0 ){
				zoneAV = "A";
				countAV ++;
			}else if( Vzone >= 0 ){
				zoneAV = "V";
				countAV ++;
			}else{
				zoneAV = "NA";
			}
			housei.houseElevation();
			double rd = Math.random();
			int Force_Label;
			if(zoneAV != "NA"){
				if( rd < 0.30){
					Force_Label = 1;
				}else{
					Force_Label = 0;
				}
			}else{ Force_Label = 0;}
			if( Arrays.toString(preserve_list1).contains( Integer.toString(housei.CensusID)) ){
				housei.AdaptationDecision(  utilityCost1, 1 - pfactor, Force_Label, 0); /**0.75**/
//				System.out.println("Hid "+ housei.FID + " census "+ housei.CensusID );
			}else{
				housei.AdaptationDecision( utilityCost1, 1, Force_Label, 0);
//				System.out.println(Arrays.toString(preserve_list1)+" "+ housei.CensusID );
			}
			
			nonAdaptRisk =  nonAdaptRisk + housei.FrealR1;
			adaptRisk    =  adaptRisk + housei.FrealR2;
			totalSpending = totalSpending + housei.totalSpend;

			
			SLRnonAdaptRisk  =   SLRnonAdaptRisk + housei.SLR1realRisk1;
			SLRadaptRisk	 =   SLRadaptRisk    + housei.SLR1realRisk2;
			
			if(housei.elevationCost > 0){
				Nmeasures ++;
			}
			if(housei.coverage > 0){
				N_inst ++;
				if( zoneAV.equals("A")){
					adoptA ++;
				}else if( zoneAV.equals("V")){
					adoptV++;
				}else{
					adoptX++;
				}
				if(housei.insType == "private" || housei.insType == "Fprivate"){ adoptPrivate ++;}
			}
		}
		double adoptionrate = (double) adoptA / (double) countAV ;
		System.out.println("The percentage of insurance adoption is "+ N_inst +" "+ adoptionrate);
		System.out.println("Adoption is A "+ adoptA +" V "+ adoptV+" X "+adoptX + 
							" private " +adoptPrivate+" adaptation "+Nmeasures);
		System.out.println("initial risk  "+ nonAdaptRisk/ResidBidPrice.size() +
							" adapt Risk "+  adaptRisk/ResidBidPrice.size() +
							" total spending "+ totalSpending / ResidBidPrice.size());
		
		System.out.println("SLR initial risk  "+ SLRnonAdaptRisk/ResidBidPrice.size() +
							" SLR adapt Risk "+ SLRadaptRisk/ResidBidPrice.size() );
	}
	
	public static void GenerateHouseholds( ArrayList<Properties> residentBuildings, ArrayList<Census> CensusList){
		Iterator<Properties> iter = residentBuildings.iterator();
		HashMap<Integer, Census> census_dict = censusDict(CensusList);
		int countA = 0, countV = 0, countX = 0;
		while(iter.hasNext()){
			Properties budlidngi = iter.next();
			Random r = new Random();
			budlidngi.Justvalue = budlidngi.Justvalue; // + budlidngi.Justvalue*0.0 + (budlidngi.Justvalue*0.01) * r.nextDouble();
			budlidngi.elevation = budlidngi.elevation + 1.5+(budlidngi.elevation*1.5) * r.nextDouble();
//			if(budlidngi.NORESUNTS > 0 ){
//				if( budlidngi.NORESUNTS < 4){
//					budlidngi.exposedValue = budlidngi.Justvalue / budlidngi.NORESUNTS;
//				}else{
//					budlidngi.exposedValue  = budlidngi.Justvalue / (int) Math.round( budlidngi.NORESUNTS / Math.log( budlidngi.NORESUNTS ));
//				}
//			}else{
			budlidngi.exposedValue = budlidngi.Justvalue;
//			}
			int censusidi = budlidngi.CensusID;
			Census census_iter = census_dict.get(censusidi);
			RandomCollection<Integer> homeowner_items = new RandomCollection<Integer>();
			homeowner_items.add(census_iter.OWNER +1, 1);
			homeowner_items.add(census_iter.RENTER +1, 0);			 
			
			if( budlidngi.Justvalue < 2000000){
				budlidngi.ownership = homeowner_items.next();
			}else{
				budlidngi.ownership = 1;
			}
			Random rn = new Random();
			if( budlidngi.ownership == 0){
				budlidngi.bidIncome = budlidngi.Justvalue / (22 + rn.nextInt(12 + 1) -6) * 5;
			}else{
				
				if( budlidngi.Justvalue < 150000){
					budlidngi.bidIncome = budlidngi.Justvalue / (2.5 + rn.nextInt(2 + 1) -1.5) ;
				}else if(budlidngi.Justvalue > 150000 & budlidngi.Justvalue < 1000000){
					budlidngi.bidIncome = budlidngi.Justvalue / (3.5 + rn.nextInt(6 + 1) -3) ;
				}else{
					budlidngi.bidIncome = budlidngi.Justvalue / (5 + rn.nextInt(7 + 1) -2) ;
				}
			}
			RandomCollection<Integer> race_items = new RandomCollection<Integer>();
			race_items.add(census_iter.WHITE+1, 0);
			race_items.add(census_iter.TOTALPOP - census_iter.WHITE+1, 1);	
			budlidngi.Race = race_items.next();
			
			RandomCollection<Integer> education_items = new RandomCollection<Integer>();
			education_items.add(census_iter.ED_non+1, 1);
			education_items.add(census_iter.ED_high+1, 2);	
			education_items.add(census_iter.ED_college+1, 3);
			education_items.add(census_iter.ED_bachelor+1, 4);	
			budlidngi.educatL = education_items.next();
			int Azone = budlidngi.zone.indexOf('A');
			int Vzone = budlidngi.zone.indexOf('V');
			String zoneAV ;
			if(Azone >= 0 ){
				zoneAV = "A";
			}else if( Vzone >= 0 ){
				zoneAV = "V";
			}else{
				zoneAV = "NA";
			}
			if( zoneAV.equals("A") ){
				countA ++;
			}else if( zoneAV.equals("V") ){
				countV ++;
			}else{
				countX ++;
			}
		}
		System.out.println( "houses in each zone A "+ countA + " V "+ countV+ " X "+ countX);
	}
	public static ArrayList<Properties> readHouseholds(String csvFile) {
		ArrayList<Properties> householdALL = new Properties().readFile2(csvFile);
		System.out.println("Finished reading residential parcels!");
		return householdALL;
	}
	
	public static double AverageCost(ArrayList<Properties> hosueholds) {
		Iterator<Properties> iter = hosueholds.iterator();
		int count = 0;
		double totalInsurance = 0;
		while(iter.hasNext()){
			Properties housei = iter.next();
			if( housei.insurance > 0){
				totalInsurance += housei.insurance;
				count ++;
			}
		}
		totalInsurance=	totalInsurance / count;
		return totalInsurance;
	}
	
	public static double AverageAdaptCost(ArrayList<Properties> hosueholds) {
		Iterator<Properties> iter = hosueholds.iterator();
		int count = 0;
		double totaleleCost = 0;
		while(iter.hasNext()){
			Properties housei = iter.next();
			if( housei.elevationCost > 0){
				totaleleCost += housei.elePay;
				count ++;
			}
		}
		totaleleCost=	totaleleCost / count;
		return totaleleCost;
	}
	
	public static double AverageAdaptCostBenefit(ArrayList<Properties> hosueholds) {
		Iterator<Properties> iter = hosueholds.iterator();
		int count = 0;
		
		double CBratio = 0, totalAdaptbenefit = 0, totalSpending = 0;
		while(iter.hasNext()){
			Properties housei = iter.next();
			if( housei.insurance > 0 || housei.elePay > 0){
				totalAdaptbenefit += (housei.FrealR1 - housei.FrealR2);
				totalSpending     += housei.totalSpend;
				count ++;
			}
		}
		CBratio =	totalAdaptbenefit / totalSpending;
		return CBratio;
	}
	
	public static int unaffordable(ArrayList<Properties> hosueholds) {
		Iterator<Properties> iter = hosueholds.iterator();
		int count = 0;
		double pfactor = 0.05;
		double affordable = 0.1;
		while(iter.hasNext()){
			Properties housei = iter.next();
			if( housei.totalSpend  > housei.bidIncome *pfactor){
				count ++;
			}
		}
		affordable =	(double)count / hosueholds.size();
		return count;
	}
	public static int Category (double Criteria) {
		int AdaptCategory = 0;
		if( Criteria < 0.6 ){
			AdaptCategory = 1;
		}else if(Criteria >= 0.6  & Criteria < 0.9){
			AdaptCategory = 2;
		}else {
			AdaptCategory = 3;
		}
		return AdaptCategory;
	}
	
	public static void EvaluateSuccess(ArrayList<Properties> hosueholds) {
		Iterator<Properties> iter = hosueholds.iterator();
		double Criteria1 = 0.0, Criteria2 = 0.0, Criteria3 = 0.0, realRD1 = 0,  realRD2 = 0,  realRD3 = 0, PerceivedRD = 0;
		int[] Cates1 = {0, 0, 0};
		int[] Cates2 = { 0, 0, 0};
		int[] Cates3 = { 0, 0, 0};
		while(iter.hasNext()){
			Properties housei = iter.next();
			if( housei.elePay  > 0 ){
				realRD1 		= housei.SLR1realRisk1 - housei.SLR1realRisk2 ;
				realRD2 		= housei.SLR2realRisk1 - housei.SLR2realRisk2 ;
				realRD3 		= housei.SLR3realRisk1 - housei.SLR3realRisk2 ;
				PerceivedRD 	= housei.FloodRisk2; 
				Criteria1 =	(double) realRD1 / ( housei.SLR1realRisk1 + 1 );
				Criteria2 =	(double) realRD2 / ( housei.SLR2realRisk1 + 1 );
				Criteria3 =	(double) realRD3 / ( housei.SLR3realRisk1 + 1 );
//				System.out.println(realRD1 + " "+ realRD2 +" "+ realRD3 + " "+ housei.SLR1realRisk1+ " "+ housei.elePay+" "+ housei.insurance);
				housei.AdaptCategory1 = Category(Criteria1);
				housei.AdaptCategory2 = Category(Criteria2);
				housei.AdaptCategory3 = Category(Criteria3);
				
				Cates1[housei.AdaptCategory1 -1] += 1;
				Cates2[housei.AdaptCategory2 -1] += 1;
				Cates3[housei.AdaptCategory3 -1] += 1;
			}else{
			}
		}
		System.out.println("Low " +Arrays.toString(Cates1));
		System.out.println("medium " +Arrays.toString(Cates2));
		System.out.println("high " +Arrays.toString(Cates3));
	}
	
	public static void main(String[] args) {
		String Censuspth = "C:\\Users\\yuh46\\Desktop\\SocialInteractionFiles\\MiamiDade\\simResults\\MBCensusOD.csv";
		String Residpth  = "C:\\Users\\yuh46\\Desktop\\SocialInteractionFiles\\MiamiDade\\simResults\\MBresidentials.csv";		
		ArrayList<Properties> singleFamilyHouses 	= readProperties(Residpth);
		ArrayList<Census> CensusList 		= readCensuses(Censuspth); 
		GenerateHouseholds(singleFamilyHouses, CensusList);
		AdaptDecision(singleFamilyHouses, CensusList);
		
		double avgInsCost = AverageCost(singleFamilyHouses);
		System.out.println("Average insurance cost "+avgInsCost);
		
		double avgAdaptCost = AverageAdaptCost(singleFamilyHouses);
		System.out.println("Average adaptive cost "+avgAdaptCost);		
		double CB = AverageAdaptCostBenefit(singleFamilyHouses);
		System.out.println("Average cost benefit "+CB);		
		
		System.out.println("Affordability ratio "+ unaffordable(singleFamilyHouses) );
		
		EvaluateSuccess(singleFamilyHouses);
		
//		String bidPth = "C:\\Users\\yuh46\\Desktop\\SocialInteractionFiles\\AdaptParcels3.csv";
//		CsvFileWriter.writeBidPrice(singleFamilyHouses, bidPth);	
	}
}
