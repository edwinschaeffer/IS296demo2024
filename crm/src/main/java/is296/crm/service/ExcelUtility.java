package is296.crm.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import is296.crm.vo.AuditTrail;

@Service
public class ExcelUtility {
	
	public List<AuditTrail> transformExceltoAuditTrail(MultipartFile file) throws EncryptedDocumentException, IOException, ParseException {
		InputStream is = file.getInputStream();
		Workbook wb = WorkbookFactory.create(is);
		Sheet s = wb.getSheetAt(0);
		List<AuditTrail> atList = new ArrayList<AuditTrail>();
		DataFormatter fmt = new DataFormatter();
		
		for (int i = 1; i < s.getLastRowNum(); i++) {
			Row r = s.getRow(i);
			AuditTrail at = new AuditTrail();
			// 0 is the first column - 3 is the fourth (and final) column
			for(int j = 0; j < 4; j++) {
				Cell cell = r.getCell(j);
				String cellValue = fmt.formatCellValue(cell).trim();
				if(cellValue == null) {
					continue;
				}
				switch(j) {
					case 0:
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss a z");
						Date parsedDate = sdf.parse(cellValue);
						Timestamp ts = new Timestamp(parsedDate.getTime());
						at.setDate(ts);
					break;
					case 1:
						at.setUser(cellValue);
					break;
					case 2:
						at.setAction(cellValue);
					break;
					case 3:
						at.setSection(cellValue);
					break;
				}
			}
			atList.add(at);
		}
		is.close();
		System.out.println(atList.get(0).toString());
		return atList;
	}

}
