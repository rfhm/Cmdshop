import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ReadUserExcel {
    //public User[] readExcel(File file) {
    public User[] readExcel(InputStream in) {
        User[] users=null;
        try {
            //XSSFWorkbook xw = new XSSFWorkbook(new FileInputStream(file));
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            users = new User[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                User user=new User();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if(k==0){
                        user.setUsername(this.getValue(cell));
                    }else if(k==1){
                        user.setPassword(this.getValue(cell));;
                    }else if(k==2){
                        user.setAddress(this.getValue(cell));
                    }else if(k==3){
                        user.setPhone(this.getValue(cell));
                    }
                    users[j-1]=user;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return users;
    }


    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellType();

        DecimalFormat df=new DecimalFormat("#");
        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                value = df.format(cell.getNumericCellValue());
                /*int index=value.lastIndexOf(".");
                value=value.substring(0,iwrfndex);*/
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "????????????";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}
