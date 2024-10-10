import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CsvFileWriter {
	private static String fileName = "C:\\Users\\yuh46\\Desktop\\MiamiDade\\simResults\\BidPrices.csv";
	private static final String FILE_HEADER = "RFID,HHiid,bidPrice,JV,CensusID,Income,latitude,longitude";
//	private static String fileName2 = "C:\\Users\\yuh46\\Desktop\\MiamiDade\\simResults\\floodRisk2.csv";
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
//	public static void writeCsvFile(ArrayList<BidPrice> BidHouseholds) {
//		FileWriter fileWriter = null;
//			try{
//				fileWriter = new FileWriter(fileName);
//				fileWriter.append(FILE_HEADER.toString());
//				fileWriter.append(NEW_LINE_SEPARATOR);
//				
//				for (BidPrice housei : BidHouseholds) {
//					fileWriter.append( String.valueOf(housei.RFID) );
//					fileWriter.append(COMMA_DELIMITER);
//					fileWriter.append( String.valueOf(housei.bidHHiid) );
//					fileWriter.append(COMMA_DELIMITER);
//					fileWriter.append( String.valueOf(housei.maxbid) );
//					fileWriter.append(COMMA_DELIMITER);
//					fileWriter.append( String.valueOf(housei.JV) );
//					fileWriter.append(COMMA_DELIMITER);
//				
//					fileWriter.append( String.valueOf(housei.CensusID) );
//					fileWriter.append(COMMA_DELIMITER);
//					fileWriter.append( String.valueOf(housei.bidIncome) );
//					fileWriter.append(COMMA_DELIMITER);
//					fileWriter.append( String.valueOf(housei.latitude) );
//					fileWriter.append(COMMA_DELIMITER);
//					fileWriter.append( String.valueOf(housei.longitude) );
//					fileWriter.append(NEW_LINE_SEPARATOR);
//				}
//				System.out.println("BidPrices.CSV file was created successfully !!!");
//			}catch (Exception e) {
//				System.out.println("Error in CsvFileWriter !!!");
//				e.printStackTrace();
//			}finally {
//				try {
//					fileWriter.flush();
//					fileWriter.close();
//				} catch (IOException e) {
//					System.out.println("Error while flushing/closing fileWriter !!!");
//					e.printStackTrace();
//					}
//				}
//			}
	public static void writeFloodRisk(Map <Integer, ArrayList<Double>> HouseRisk, String fileName2) {
		FileWriter fileWriter = null;
		String FILE_HEADER2 =  	"RFID,BeforeRisk,PostRisk,willPay,ICost,"
								+ "InsurCost,PremiumCost,eleHeight,elevationCost,"
								+ "bidPrice,Justvalue,bidIncome,Area,UtilityCost,AdaptCost,CBratio,IncomeP,latitude,longitude";
		try{
			fileWriter = new FileWriter(fileName2);
			fileWriter.append(FILE_HEADER2.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			for (Integer rid : HouseRisk.keySet())  {
				
				ArrayList<Double> HouseProperty = HouseRisk.get( rid );
				fileWriter.append( String.valueOf(rid) );
				fileWriter.append( COMMA_DELIMITER );
				for (int i = 0; i < HouseProperty.size() - 1 ; i++) {
					fileWriter.append( String.valueOf(HouseProperty.get(i)) );
					fileWriter.append( COMMA_DELIMITER);
				}
				fileWriter.append( String.valueOf(HouseProperty.get(HouseProperty.size() - 1)) );
				fileWriter.append( NEW_LINE_SEPARATOR);
			}
			System.out.println("floodrisk.CSV file was created successfully !!!");
			}catch (Exception e) {
				System.out.println("Error in flood risk CsvFileWriter !!!");
				e.printStackTrace();
				}finally {
					try {
						fileWriter.flush();
						fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing flood risk fileWriter !!!");
					e.printStackTrace();
					}
			}
	}
	public static void writeBidPrice(ArrayList<Properties> BidParcels, String filepath) {
		String FILE_HEADER =  	"FID,CensusID,Area,EFFYER,bidIncome,"
								+ "race,education,ownership,"
								+ "bidPrice,Justvalue,"
								+ "LADVAL,NORESUNTS,SFHA,zone,liveArea,"
								+ "cate1,cate2,cate3,cate4,cate5,"
								+ "elevation,initialWL,BFE,nearWater,"
								+ "DistWater,eleCost,elePay,insCost,"
								+ "Coverage,InsType,eleHeight,"
								+ "Frisk0,Freal0,Frisk1,Freal1,Tcost,"
								+ "TSpend,AdaptCate1,AdaptCate2,AdaptCate3,latitude,longitude";
		FileWriter fileWriter = null;
		try{
			fileWriter = new FileWriter(filepath);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			for (Properties housei : BidParcels) {
				fileWriter.append( String.valueOf(housei.FID) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.CensusID) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.Area) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.EFFYER) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.bidIncome) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.Race) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.educatL) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.ownership) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.bidPrice) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.Justvalue) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.LADVAL) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.NORESUNTS) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.SFHA) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.zone) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.liveArea) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.cate1) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.cate2) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.cate3) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.cate4) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.cate5) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.elevation) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.initialHeight) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.BFE) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.nearWater) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.DistWater) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.elevationCost) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.elePay) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.insurance) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.coverage) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.insType) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.eleHeight) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.FloodRisk1) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.FrealR1) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.FloodRisk2) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.FrealR2) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.totalCost) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.totalSpend) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.SLR1realRisk2) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.SLR2realRisk2) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.SLR3realRisk2) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.latitude) );
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append( String.valueOf(housei.longitude) );
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			System.out.println("writeBidPrice.CSV file was created successfully !!!");
		}catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
				}
			}
		}
	}
