import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Properties {
	
	public static double k_0 = 0.23, beta_0 = 0.31, mu_0 = 5.42;
	public static double[] probs  = {0.05, 0.02, 0.01, 0.005, 0.002};
	
	public double AVE_HH_SZ , Area ;
	public int    idx , FID , Desc , CensusID , DistrictID , EFFYER , HOUSEHOLDS, withIns, Ptype;
	public double IMPROVVAL , Justvalue , LADVAL , MEDHHINC , MEDOOHVAL , DistWater;
	public int    NOBULDNG , NORESUNTS , ownership , TOTALPOP , WHITE , BLACK , HISPANIC;
	public double PCT_POV , HBELOW_POV , cate1 , cate2 , cate3, cate4, cate5;
	public double latitude , longitude;
	public double liveArea , nearWater , elevation;
	public double accesscommercial , accesscenter , accessindustrial;
	public int    bidHHiid , pastFlood, Race, educatL;
	public double HH_PUBA ;
	
	public double bidPrice , bidIncome;
	
	public double cost1, cost2 , cost3 , cost4 , cost5, BFE;
	public String SFHA, zone;
	public double improvePd, compliancePd;
	
	public int AdaptCategory1, AdaptCategory2, AdaptCategory3;
	public double exposedValue;
	public String insType;
	public double coverage;
	public double insurance;
	
	public double SLR1realRisk1, SLR2realRisk1, SLR3realRisk1, SLR1realRisk2, SLR2realRisk2, SLR3realRisk2;
	
	public double FloodRisk1, FrealR1, FloodRisk2, FrealR2, totalCost, totalSpend;
	public double AdaptationCost, CBratio, IP;
	public double AdaptationCost1, AfterRisk;
	public double elevationCost, eleHeight, elePay;
	public double initialHeight, initialHeightSLR1, initialHeightSLR2, initialHeightSLR3;
	
	public double[] elecosts = { 0, 0, 0, 0, 0};
	public double[] eleheights = { 0, 2,  4, 6, 9};
	
	public Properties(){}
	public static void printArray(String[] rows) {
	      for (int i = 0; i < rows.length; i++) {
	         System.out.println(i + " , " +rows[i]);
	         System.out.print('\n');
	      }
	      System.out.println("\nlength of the array is "+ rows.length);
	}
	
	public ArrayList<Properties> readFile(String csvFile)  {
		BufferedReader br = null;
		String dataline = "";
		String cvsSplitBy = ",";
		ArrayList<Properties> householdALL = new ArrayList<Properties>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((dataline = br.readLine()) != null) {
				String[] line = dataline.split(cvsSplitBy);
				if( (int) Float.parseFloat(line[20] ) == 1){
					
//					printArray(line);
					Properties propertyi = new Properties();
					propertyi.AVE_HH_SZ = Double.parseDouble(line[3] );
					propertyi.Area      = Float.parseFloat(line[4]);
//					propertyi.BLACK     = (int) Float.parseFloat(line[5] ) ;
					propertyi.Desc      = (int) Float.parseFloat(line[6]);
					propertyi.DistWater = (int) Float.parseFloat(line[7]);
					propertyi.DistrictID = (int) Float.parseFloat(line[8]);
					propertyi.EFFYER     = (int)Double.parseDouble(line[9] );
					propertyi.FID        = (int)Double.parseDouble(line[10] );
//					propertyi.HBELOW_POV = (int) Float.parseFloat(line[11]);
//					propertyi.HISPANIC   = (int) Float.parseFloat(line[12] );
//					propertyi.HOUSEHOLDS = (int) Float.parseFloat(line[13] );
//					propertyi.IMPROVVAL = Double.parseDouble(line[14] );
					propertyi.Justvalue = Double.parseDouble(line[15] );
					propertyi.LADVAL    = Double.parseDouble(line[16] );
//					propertyi.MEDHHINC  = Double.parseDouble(line[17] );
//					propertyi.MEDOOHVAL = Double.parseDouble(line[18] );
//					propertyi.NOBULDNG = (int) Float.parseFloat(line[19] );
					propertyi.NORESUNTS = (int) Float.parseFloat(line[20] );
//					propertyi.OWNER    = (int) Float.parseFloat(line[21] );
//					propertyi.PCT_POV  = Double.parseDouble(line[22] );
					propertyi.SFHA  = line[23];
//					propertyi.TOTALPOP = (int) Float.parseFloat(line[24] );
//					propertyi.WHITE = (int) Float.parseFloat(line[25] );
					
					propertyi.zone = line[26];
					
					propertyi.cate1     = Double.parseDouble(line[27] );
					propertyi.cate2 = Double.parseDouble(line[28] );
					propertyi.cate3 = Double.parseDouble(line[29] );
					propertyi.cate4 = Double.parseDouble(line[30] );
					propertyi.cate5 = Double.parseDouble(line[31] );
					propertyi.CensusID = (int) Float.parseFloat(line[32] );
					propertyi.elevation = Double.parseDouble(line[33] );
					propertyi.idx       = (int) Float.parseFloat(line[34] );
					propertyi.latitude  = Double.parseDouble(line[35] );
					propertyi.liveArea  = Double.parseDouble(line[36] );
					propertyi.longitude = Double.parseDouble(line[37] );
					propertyi.nearWater = Double.parseDouble(line[38] );
//					propertyi.HH_PUBA = Double.parseDouble(line[39] );
					propertyi.accesscommercial = Double.parseDouble(line[40] );
					propertyi.accesscenter     = Double.parseDouble(line[41] );
					propertyi.accessindustrial = Double.parseDouble(line[42] );
					
					propertyi.improvePd = Double.parseDouble( line[43] );
					propertyi.compliancePd = Double.parseDouble( line[44] );
//					propertyi.sellingYer     = -1;
//					propertyi.displayhousehold();
					householdALL.add(propertyi);
				}
				}
			System.out.println(" Total property " + householdALL.size());
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
	
	public ArrayList<Properties> readFile2(String csvFile)  {
		BufferedReader br = null;
		String dataline = "";
		String cvsSplitBy = ",";
		ArrayList<Properties> householdALL = new ArrayList<Properties>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((dataline = br.readLine()) != null) {
					String[] line = dataline.split(cvsSplitBy);
					printArray(line);
					Properties propertyi = new Properties();
					propertyi.AVE_HH_SZ = Double.parseDouble(line[3] );
					propertyi.Area      = Float.parseFloat(line[4]);
//					propertyi.BLACK     = (int) Float.parseFloat(line[5] ) ;
					propertyi.Desc      = (int) Float.parseFloat(line[6]);
					propertyi.DistWater = (int) Float.parseFloat(line[7]);
					propertyi.DistrictID = (int) Float.parseFloat(line[8]);
					propertyi.EFFYER     = (int)Double.parseDouble(line[9] );
					propertyi.FID        = (int)Double.parseDouble(line[10] );
//					propertyi.HBELOW_POV = (int) Float.parseFloat(line[11]);
//					propertyi.HISPANIC   = (int) Float.parseFloat(line[12] );
//					propertyi.HOUSEHOLDS = (int) Float.parseFloat(line[13] );
//					propertyi.IMPROVVAL = Double.parseDouble(line[14] );
					propertyi.Justvalue = Double.parseDouble(line[15] );
					propertyi.LADVAL    = Double.parseDouble(line[16] );
//					propertyi.MEDHHINC  = Double.parseDouble(line[17] );
//					propertyi.MEDOOHVAL = Double.parseDouble(line[18] );
//					propertyi.NOBULDNG = (int) Float.parseFloat(line[19] );
					propertyi.NORESUNTS = (int) Float.parseFloat(line[20] );
//					propertyi.OWNER    = (int) Float.parseFloat(line[21] );
//					propertyi.PCT_POV  = Double.parseDouble(line[22] );
					
					propertyi.SFHA  = line[23];
					
//					propertyi.TOTALPOP = (int) Float.parseFloat(line[24] );
//					propertyi.WHITE = (int) Float.parseFloat(line[25] );
					
					propertyi.zone = line[26];
					
					propertyi.cate1     = Double.parseDouble(line[27] );
					propertyi.cate2 = Double.parseDouble(line[28] );
					propertyi.cate3 = Double.parseDouble(line[29] );
					propertyi.cate4 = Double.parseDouble(line[30] );
					propertyi.cate5 = Double.parseDouble(line[31] );
					propertyi.CensusID = (int) Float.parseFloat(line[32] );
					propertyi.elevation = Double.parseDouble(line[33] );
					propertyi.idx       = (int) Float.parseFloat(line[34] );
					propertyi.latitude  = Double.parseDouble(line[35] );
					propertyi.liveArea  = Double.parseDouble(line[36] );
					propertyi.longitude = Double.parseDouble(line[37] );
					propertyi.nearWater = Double.parseDouble(line[38] );
//					propertyi.HH_PUBA = Double.parseDouble(line[39] );
					propertyi.accesscommercial = Double.parseDouble(line[40] );
					propertyi.accesscenter     = Double.parseDouble(line[41] );
					propertyi.accessindustrial = Double.parseDouble(line[42] );
					
					propertyi.improvePd = Double.parseDouble( line[43] );
					propertyi.compliancePd = Double.parseDouble( line[44] );
//					propertyi.sellingYer     = -1;
//					propertyi.displayhousehold();
					householdALL.add(propertyi);
	        }
			System.out.println(" Total property " + householdALL.size());
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
	
	public static double elevateCost( double eleH, double EleArea){
		double HardMeasureCost = 0;
		if(eleH <= 2 & eleH >0){
			HardMeasureCost = (47*1 )* EleArea  ;
		}else if(eleH < 8 & eleH >2){
			HardMeasureCost = (47*1 + 0.75*(eleH-2) )* EleArea  ;
		}else{
			HardMeasureCost = (47*1 + 1+ 0.75*(eleH-2) )* EleArea  ;
		}
		return HardMeasureCost;
	}
	
	public static double estimateCost( double eleH, double EleArea){
		double HardMeasureCost, cost1=0, cost2=0;
		if(eleH == 0){
			HardMeasureCost =0;
		}else if( eleH < 2 & eleH > 0 ){
			HardMeasureCost = (19.5*eleH + 46) * Math.sqrt( EleArea )*4  ;
		}else{
			HardMeasureCost = elevateCost( eleH, EleArea)  ;
		}
		return HardMeasureCost;
		
	}
	
	public void houseElevation(){
		double area = this.liveArea;
		if(area <= 0){area = 1;}
		double strPrice = this.exposedValue;
		int i;
		for (i = 0; i < this.eleheights.length; i++) { 
			if( this.eleheights[i] == 10){
				if( this.BFE - this.elevation + 1 > 10){
					this.eleheights[i] = this.BFE - this.elevation + 2;
				}else{
					this.eleheights[i] = 10;
				}
			}else{
				this.eleheights[i] = eleheights[i] ;
			}
			this.elecosts[i] = estimateCost( this.eleheights[i], area);
		}
	}
	
	public static boolean isSmaller(double limit, double[] data){
	      for(int k = 0; k < data.length; k++){
	        if (data[k] < limit)
	          return false;
	      }
	      return true;
	}
	
	
	public double[] VoucherCost( int zoneLabel, double Pfactor, double utilityCost0){
		int Azone = this.zone.indexOf('A');
		int Vzone = this.zone.indexOf('V');
		double[] voucherInfo = {0, 0, 0, 0, 0, 0,0, 0};
		String zoneAV ;
		if(Azone >= 0 ){
			zoneAV = "A";
		}else if( Vzone >= 0 ){
			zoneAV = "V";
		}else{
			zoneAV = "NA";
		}
//		
		double eleHeight = this.BFE - this.elevation + 1;
		if( eleHeight <= 0){ eleHeight = 0;}
		if( eleHeight > 9){ eleHeight = 9;}
		double totalEleCost = estimateCost( eleHeight, this.liveArea);
		double elePay = totalEleCost / 19.6;
		double[] InsuranceList = Insurer.insuranceCost(  this.exposedValue, this.zone , this.elevation, eleHeight, this.BFE, Pfactor, 1);
		double insCost = 0, inscots1 = 0, inscots2 = 0, inscots3 = 0;
		if(zoneLabel == 1){
			if( InsuranceList[5] +elePay> this.bidIncome * 0.05){ InsuranceList[5] = this.bidIncome * 0.05-elePay; }
			if(InsuranceList[5] <0){InsuranceList[5] = 0; elePay = this.bidIncome * 0.05;}
			double[] risk_ins      = QuantifyRisk( InsuranceList[4] , eleHeight, Pfactor, utilityCost0, InsuranceList[5], elePay);
			voucherInfo[0] = InsuranceList[4];
			voucherInfo[1] = InsuranceList[5]; 
			voucherInfo[2] = risk_ins[0];
			voucherInfo[3] = risk_ins[1];
			voucherInfo[4] = risk_ins[0];
			voucherInfo[5] = elePay;
			voucherInfo[6] = totalEleCost;
			voucherInfo[7] = eleHeight;
		}else{
			if( InsuranceList[1] +elePay  > this.bidIncome * 0.05){ InsuranceList[1] = this.bidIncome * 0.05 - elePay; }
			if( InsuranceList[3] +elePay> this.bidIncome * 0.05){ InsuranceList[3] = this.bidIncome * 0.05 - elePay; }
			if( InsuranceList[5] +elePay> this.bidIncome * 0.05){ InsuranceList[5] = this.bidIncome * 0.05 - elePay; }
			if(InsuranceList[1] <0){InsuranceList[1] = 0;}
			if(InsuranceList[3] <0){InsuranceList[3] = 0;}
			if(InsuranceList[5] <0){InsuranceList[5] = 0;}
			
			
			double[] risk_ins1     = QuantifyRisk( InsuranceList[0] , eleHeight, Pfactor, utilityCost0, InsuranceList[1] , elePay);
			double[] risk_ins2     = QuantifyRisk( InsuranceList[2] , eleHeight, Pfactor, utilityCost0,InsuranceList[3] , elePay);
			double[] risk_ins3     = QuantifyRisk( InsuranceList[4] , eleHeight, Pfactor, utilityCost0,InsuranceList[5] , elePay);
			
			double[] compare_list = { risk_ins1[0], risk_ins2[0], risk_ins3[0]};
			if( isSmaller( risk_ins1[0],  compare_list) ){
				voucherInfo[0] = InsuranceList[0];
				voucherInfo[1] = InsuranceList[1]; 
				voucherInfo[2] = risk_ins1[0];
				voucherInfo[3] = risk_ins1[1];
				voucherInfo[4] = risk_ins1[0];
				voucherInfo[5] = elePay;
				voucherInfo[6] = totalEleCost;
				voucherInfo[7] = eleHeight;
			}else if( isSmaller( risk_ins2[0] ,  compare_list) ){
				voucherInfo[0] = InsuranceList[2];
				voucherInfo[1] = InsuranceList[3]; 
				voucherInfo[2] = risk_ins2[0];
				voucherInfo[3] = risk_ins2[1];
				voucherInfo[4] = risk_ins2[0];
				voucherInfo[5] = elePay;
				voucherInfo[6] = totalEleCost;
				voucherInfo[7] = eleHeight;
			}else{
				voucherInfo[0] = InsuranceList[4];
				voucherInfo[1] = InsuranceList[5]; 
				voucherInfo[2] = risk_ins3[0];
				voucherInfo[3] = risk_ins3[1];
				voucherInfo[4] = risk_ins3[0];
				voucherInfo[5] = elePay;
				voucherInfo[6] = totalEleCost;
				voucherInfo[7] = eleHeight;}
		}
		return voucherInfo;
	}
	
	public void AdaptationDecision( double utilityCost, double Pfactor, int zoneLabel, int Voucher){
		double PreFRIM = 1;
		if( this.EFFYER <= 1972 & Voucher != 1){
			PreFRIM = 1- 0.6;
		}else{
			PreFRIM = 1;
		}
		double utilityCost0 = utilityCost * this.liveArea;
		double minTotal = this.Justvalue;
		double strPrice = this.exposedValue;
		double elevationH = 0;
		double annualRate = 0.07358;
		for (int i = 0; i < this.eleheights.length; i++) { 
			if(this.eleheights[i] >= 3){
				elevationH = this.eleheights[i];
			}else{
				elevationH = 0;
			}
			double[] InsuranceList = Insurer.insuranceCost(  strPrice, this.zone , this.elevation, elevationH, this.BFE, Pfactor, PreFRIM );
			double[] PrivateList = Insurer.privateInsurance( strPrice, this.zone , this.elevation, elevationH, this.BFE );
			String insurance_type = "NFIP";
			double[] insurance_list = { 0, 0} ;
			double[] risk_0 = {0, 0};
			double  totalU = this.Justvalue;
			if( zoneLabel == 1){
				double[] risk_ins      = QuantifyRisk( InsuranceList[4] , this.eleheights[i], Pfactor, utilityCost0, InsuranceList[5], this.elecosts[i] *annualRate);
				double[] risk2_priv    = QuantifyRisk( PrivateList[4] , this.eleheights[i], Pfactor, utilityCost0, PrivateList[5], this.elecosts[i] *annualRate);				
				double[] risk3_priv2   = QuantifyRisk( PrivateList[6] , this.eleheights[i], Pfactor, utilityCost0, PrivateList[7], this.elecosts[i] *annualRate);
				
				double[] compare_list = {
				risk_ins[0] , risk2_priv[0] , risk3_priv2[0] 
				};
				if( isSmaller( risk_ins[0] ,  compare_list) ){
					insurance_list[0] = InsuranceList[4];
					insurance_list[1] = InsuranceList[5]; 
					risk_0[0] = risk_ins[0];
					risk_0[1] = risk_ins[1];
					totalU = risk_ins[0] ;
					insurance_type = "FNFIP";
				}else if( isSmaller( risk3_priv2[0] ,  compare_list) ){
					insurance_list[0] = PrivateList[6];
					insurance_list[1] = PrivateList[7]; 
					risk_0[0] = risk3_priv2[0];
					risk_0[1] = risk3_priv2[1];
					totalU = risk3_priv2[0] ;
					insurance_type = "Fprivate";
				}else{
					insurance_list[0] = PrivateList[4];
					insurance_list[1] = PrivateList[5]; 
					risk_0[0] = risk2_priv[0];
					risk_0[1] = risk2_priv[1];
					totalU = risk2_priv[0] ;
					insurance_type = "Fprivate";
					}
			}else{
				double[] risk0 		   = QuantifyRisk( 0 , this.eleheights[i], Pfactor, utilityCost0, 0, this.elecosts[i] *annualRate);
				double[] risk_ins1     = QuantifyRisk( InsuranceList[0] , this.eleheights[i], Pfactor, utilityCost0, InsuranceList[1] , this.elecosts[i] *annualRate);
				double[] risk_ins2     = QuantifyRisk( InsuranceList[2] , this.eleheights[i], Pfactor, utilityCost0,InsuranceList[3] , this.elecosts[i] *annualRate);
				double[] risk_ins3     = QuantifyRisk( InsuranceList[4] , this.eleheights[i], Pfactor, utilityCost0,InsuranceList[5] , this.elecosts[i] *annualRate);
				double[] risk2_priv1   = QuantifyRisk( PrivateList[0]   , this.eleheights[i], Pfactor, utilityCost0,PrivateList[1] , this.elecosts[i] *annualRate);
				double[] risk2_priv2   = QuantifyRisk( PrivateList[2]   , this.eleheights[i], Pfactor, utilityCost0,PrivateList[3] , this.elecosts[i] *annualRate);
				double[] risk2_priv3   = QuantifyRisk( PrivateList[4]   , this.eleheights[i], Pfactor, utilityCost0,PrivateList[5] , this.elecosts[i] *annualRate);
				double[] risk2_priv4   = QuantifyRisk( PrivateList[6]   , this.eleheights[i], Pfactor, utilityCost0,PrivateList[7] , this.elecosts[i] *annualRate);
				double[] compare_list = {risk0[0], risk_ins1[0], risk_ins2[0], 
						risk_ins3[0], risk2_priv1[0], risk2_priv2[0],
						risk2_priv3[0], risk2_priv4[0]
				};
				if( isSmaller( risk_ins1[0],  compare_list) ){
					insurance_list[0] = InsuranceList[0];
					insurance_list[1] = InsuranceList[1]; 
					risk_0[0] = risk_ins1[0];
					risk_0[1] = risk_ins1[1];
					totalU = risk_0[0];
				}else if( isSmaller( risk_ins2[0] ,  compare_list) ){
					insurance_list[0] = InsuranceList[2];
					insurance_list[1] = InsuranceList[3]; 
					risk_0[0] = risk_ins2[0];
					risk_0[1] = risk_ins2[1];
					totalU = risk_0[0];
				}else if( isSmaller( risk_ins3[0] ,  compare_list)){
					insurance_list[0] = InsuranceList[4];
					insurance_list[1] = InsuranceList[5]; 
					risk_0[0] = risk_ins3[0];
					risk_0[1] = risk_ins3[1];
					totalU = risk_0[0];
				}else if( isSmaller( risk2_priv1[0] ,  compare_list) ){
					insurance_list[0] = PrivateList[0];
					insurance_list[1] = PrivateList[1]; 
					risk_0[0] = risk2_priv1[0];
					risk_0[1] = risk2_priv1[1];
					totalU = risk_0[0];
					insurance_type = "private";
				}else if( isSmaller( risk2_priv2[0] ,  compare_list) ){
					insurance_list[0] = PrivateList[2];
					insurance_list[1] = PrivateList[3]; 
					risk_0[0] = risk2_priv2[0];
					risk_0[1] = risk2_priv2[1];
					totalU = risk_0[0];
					insurance_type = "private";
				}else if( isSmaller( risk2_priv3[0] ,  compare_list) ){
					insurance_list[0] = PrivateList[4];
					insurance_list[1] = PrivateList[5]; 
					risk_0[0] = risk2_priv3[0];
					risk_0[1] = risk2_priv3[1];
					totalU = risk_0[0];
					insurance_type = "private";
				}else if( isSmaller( risk2_priv4[0] ,  compare_list) ){
					insurance_list[0] = PrivateList[6];
					insurance_list[1] = PrivateList[7]; 
					risk_0[0] = risk2_priv4[0];
					risk_0[1] = risk2_priv4[1];
					totalU = risk_0[0];
					insurance_type = "private";
				}else{
					insurance_list[0] = 0;
					insurance_list[1] = 0; 
					risk_0[0] = risk0[0];
					risk_0[1] = risk0[1];
					totalU = risk0[0];
					insurance_type = "none";
				}
			}
					
//					double totalCosts = totalU 
			double totalSpend = insurance_list[1] + this.elecosts[i] *annualRate + utilityCost0;	
			if( minTotal > totalU){
				this.FloodRisk2 = risk_0[0];	this.insurance  = insurance_list[1];
				this.eleHeight  = this.eleheights[i];	this.coverage   = insurance_list[0];
				this.elevationCost    = this.elecosts[i];	this.elePay     = (double)this.elecosts[i] *annualRate;
				this.totalCost         = totalU;	this.insType    = insurance_type;
				this.FrealR2    = risk_0[1];	minTotal = totalU; this.totalSpend = totalSpend;
//				System.out.println(this.coverage + " "+ this.eleHeight +" "+ this.insurance + " "+ this.elePay);
				this.SLR1realRisk2 = QuantifySLRRisk( this.coverage , this.initialHeightSLR1, this.eleheights[i], Pfactor , utilityCost0, this.insurance , this.elecosts[i] *annualRate)[1];
				this.SLR2realRisk2 = QuantifySLRRisk( this.coverage , this.initialHeightSLR2, this.eleheights[i], Pfactor , utilityCost0, this.insurance , this.elecosts[i] *annualRate)[1];
				this.SLR3realRisk2 = QuantifySLRRisk( this.coverage , this.initialHeightSLR3, this.eleheights[i], Pfactor , utilityCost0, this.insurance , this.elecosts[i] *annualRate)[1];
			}
		}
		if( Voucher == 1){
			double[] voucherInfos = VoucherCost( zoneLabel, Pfactor, utilityCost0);
			if( voucherInfos[4] < this.totalCost){
					this.FloodRisk2 = voucherInfos[2];	this.insurance  = voucherInfos[1];
					this.eleHeight  = voucherInfos[7];	this.coverage   = voucherInfos[0];
					this.elevationCost    = voucherInfos[6];	this.elePay     = voucherInfos[5];
					this.totalCost         = voucherInfos[4];	this.insType    = "FNFIP";
					this.FrealR2    = voucherInfos[3];	minTotal = voucherInfos[4]; this.totalSpend = this.elePay + this.insurance + utilityCost0;
//					System.out.println(this.coverage + " "+ this.eleHeight +" "+ this.insurance + " "+ this.elePay);
					this.SLR1realRisk2 = QuantifySLRRisk( this.coverage , this.initialHeightSLR1, this.eleHeight, Pfactor , utilityCost0, this.insurance , voucherInfos[5])[1];
					this.SLR2realRisk2 = QuantifySLRRisk( this.coverage , this.initialHeightSLR2, this.eleHeight, Pfactor , utilityCost0, this.insurance , voucherInfos[5])[1];
					this.SLR3realRisk2 = QuantifySLRRisk( this.coverage , this.initialHeightSLR3, this.eleHeight, Pfactor , utilityCost0, this.insurance , voucherInfos[5])[1];
			}
		}
	}
	
	public static double FloodDamageFunc( double inundation) {
		inundation = inundation / 3.28084; //meter
		double damage = 0;
		double tempD, tempS;
		tempD = 0.2391*Math.pow(inundation, 3) - 3.5524*Math.pow(inundation, 2)+ 19.933*inundation +11.623; //residential
		tempS = 0.1037*Math.pow(inundation, 3) - 2.815*Math.pow(inundation, 2)+ 20.898*inundation +14.957; //residential
		if( tempD > 0){
			damage = tempD / 100;
		}else{
			damage = 0;
		}
		return damage;
	}
	public double surgeHeightF(double returnP, double mu){
		double surge;
		surge = ( ( Math.pow(returnP, - k_0 ) - 1 ) * beta_0 / k_0 + mu );
		if(surge < 0) {surge = 0;}
		return surge;
	}
	
	public static double[] BayesProspect( double[] p_ie_list, double income, int educatL, int owner, int raceial, double Pfactor , double gcost){
		double RP_i;
		double sigma = 0.69; /** 0.69 */
		double[] pie_new = {0, 0, 0, 0, 0} ;
//		double para_r = 0.38, para_ed = 0.25, para_income = 0.17, para_owner = 1.0, para_gov = 0.2;
		double para_r = 0.39, para_ed = 0.25, para_income = 0.17, para_owner = 1.0, para_gov = 0.2;
		double incomeLevel, educationLevel, racial_level, owner_level, government_level ;
		if(income < 45000){
			incomeLevel = 0.0;}else if(income >= 45000  & income < 75000){
			incomeLevel = 0.0;}else if(income >= 75000  & income < 125000){
			incomeLevel = 0.4;}else if(income >= 125000 & income < 200000){
			incomeLevel = 0.6;}else{
			incomeLevel = 0.8;}
		if(educatL == 1){
			educationLevel = 0.0;}else if(educatL == 2){
			educationLevel = 0.0;}else if(educatL ==3){
			educationLevel = 0.4;}else {
			educationLevel = 0.8;}
		if( raceial == 1){ racial_level = 0.5;}else{ racial_level = 0.0;}
		if( owner == 1){ owner_level = 0.5;}else { owner_level = 0.0;}
		
		if( Pfactor >=0.95 ){ government_level = 0.5 + gcost/700*0.5 ;
		}else{ government_level = 1  ;}
		if( government_level > 1){government_level = 1;}

		RP_i = ( para_r*racial_level + para_ed*educationLevel + 
				para_income*incomeLevel + para_owner*owner_level  + 
				government_level*para_gov) / ( para_r + para_ed + para_income + para_owner + para_gov) ;
		double p_RT = 0, p_RT2 = 0;
		for(int j = 0; j < p_ie_list.length; j++){
			p_RT = Math.pow( Math.pow( 10 , 2*RP_i - 1)*p_ie_list[j], sigma);
			p_RT2 = Math.pow( 1 - Math.pow( 10 , 2*RP_i - 1)*p_ie_list[j], sigma);
			pie_new[j] = p_RT / Math.pow( (p_RT + p_RT2), 1 /sigma );
			
		}
//		System.out.println(" updated " + pie_new[1] + "  " + p_ie_list[1]+ " "+ RP_i + " "+ p_RT+" "+ p_RT2);
		return pie_new;
	}
	
	
	public double[] ProspectTheory( double DEM, double liveArea, double[] surges, double Justvalue, double Pfactor, double gcost, double insCost, double eleCost, double coverage) {
		double[] risk_list = {0.0, 0.0};
		double TotalRisk , TotalRisk1;
		double[] height  = new double[ probs.length ];
		double riski = 0;
		double[] risk_k  = new double[ probs.length ];
		double[] risk_k2 = new double[ probs.length ];
		double Nostorm = Math.pow(insCost + eleCost, 0.5);
		double P_nostrom = 0.93;
		for( int i = 0; i < surges.length; i ++ ){
			height[i] = surges[i] - DEM;
			if( height[i] <= 0 ){
				height[i] = 0;
				risk_k[i] = 1;
				risk_k2[i] = 1;
			}else{
				riski = Justvalue*FloodDamageFunc(height[i]) * Pfactor;
				
				double damage = riski - coverage;
				if(damage <0){damage = 0;}
				risk_k[i] = Math.pow(damage + insCost + eleCost, 0.5);
				risk_k2[i] = riski;
			}
		}
		double[] updated_p = BayesProspect(probs, this.bidIncome, this.educatL, this.ownership, this.Race, Pfactor, gcost);
		TotalRisk = ( 	P_nostrom*Nostorm
						+ risk_k[0] *(updated_p[0] ) 
						+ risk_k[1] *(updated_p[1] ) 
						+ risk_k[2] *(updated_p[2] ) 
						+ risk_k[3] *(updated_p[3] )
						+ risk_k[4] *(updated_p[4] ) );
		
		TotalRisk1=		( (risk_k2[0] ) *(probs[0]) 
						+ (risk_k2[1] )*(probs[1] ) 
						+ (risk_k2[2] ) *(probs[2]) 
						+ (risk_k2[3] )*(probs[3] )
						+ (risk_k2[4] )*(probs[4] ) ) ;
		
//		TotalRisk1=		( (risk_k2[0] + risk_k2[1]) *(probs[0] - probs[1]) 
//							+ (risk_k2[1] + risk_k2[2])*(probs[1] - probs[2]) 
//							+ (risk_k2[2] + risk_k2[3]) *(probs[2] - probs[3]) 
//							+ (risk_k2[3] + risk_k2[4])*(probs[3] - probs[4]) ) / 2;
		risk_list[0] =  TotalRisk;
		risk_list[1] =	TotalRisk1;
		return risk_list;
	}
	
	
	public double[] FloodRiskDamage( double DEM, double[] surgeHeight, double currentprice, double Pfactor, double gcost, double insCost, double eleCost, double coverage){
		double justvalue = currentprice ;
		double unitArea ;
		unitArea = this.liveArea;
		double[] FloodRisk =  ProspectTheory( DEM, unitArea, surgeHeight, justvalue, Pfactor, gcost, insCost, eleCost, coverage); 
		return FloodRisk;
	}
	
	public double[] QuantifyRisk( double coverage, double eleHeight, double pfactor, double gcost, double insCost, double eleCost){
		double[] Surge  = {0.00, 0.00, 0.00, 0.00, 0.00};
		double mu = this.initialHeight;
		for(int i = 0; i < probs.length; i ++){
			Surge[i] = surgeHeightF(probs[i], mu) ;
		}
		double riskValue = this.exposedValue ; 
		if(riskValue < 0){ riskValue = 0;}
		double new_house_height = eleHeight + this.elevation;
		double[] floodrisk =  FloodRiskDamage(new_house_height, Surge,  riskValue, pfactor, gcost, insCost, eleCost, coverage);
		
		return floodrisk;
	}
	
	
	public double[] QuantifySLRRisk( double coverage, double HeightSLR, double eleHeight, double pfactor, double gcost, double insCost, double eleCost){
		double[] Surge  = {0.00, 0.00, 0.00, 0.00, 0.00};
		double mu = HeightSLR;
		for(int i = 0; i < probs.length; i ++){
			Surge[i] = surgeHeightF(probs[i], mu) ;
		}
		double riskValue = this.exposedValue;
		if(riskValue < 0){ riskValue = 0;}
		double new_house_height = eleHeight + this.elevation;
		double[] floodrisk =  FloodRiskDamage(new_house_height, Surge,  riskValue, pfactor, gcost, insCost, eleCost, coverage);
		return floodrisk;
	}
}
