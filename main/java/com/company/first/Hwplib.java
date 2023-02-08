/*
* hwplib hwp document text extraction example
*/
 
import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor;
public class Hwplib {
	
	public void test() {
		HWPFile hwpFile;
		String hwpText;
		try {
		    hwpFile = HWPReader.fromFile("/Users/kimfish/DEV/java_workspace/"+"/test.hwp");
		    hwpText = TextExtractor.extract(hwpFile, TextExtractMethod.InsertControlTextBetweenParagraphText);
		 
		    System.out.println("===== hwp text extractor =====");
		    System.out.println("hwpText = " + hwpText);
		} catch (Exception e) {
		    e.printStackTrace();
		} 
	}

}
