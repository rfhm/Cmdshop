import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class CreateOrder {
    public static String outputFile =System.getProperty("user.dir") + File.separator + "Orders.xls";
    //System.getProperty("user.dir") + File.separator + "Orders.xls"
    public static void createOrder(Order order) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("订单");

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            HSSFRow firstRow = sheet.createRow((short) 0);
            HSSFCell cell01 = firstRow.createCell((short) 0);
            HSSFCell cell02 = firstRow.createCell((short) 1);
            HSSFCell cell03 = firstRow.createCell((short) 2);
            HSSFCell cell04 = firstRow.createCell((short) 3);
            HSSFCell cell05 = firstRow.createCell((short) 4);
            HSSFCell cell06 = firstRow.createCell((short) 5);
            cell01.setCellValue("?用户");
            cell02.setCellValue("商品");
            cell03.setCellValue("购买数量");
            cell04.setCellValue("商品总价");
            cell05.setCellValue("实付款");
            cell06.setCellValue("下单时间");
            //外循环次数和购物车实际长度有关
            for (int i = 0; i < order.getProducts().length; i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                for (int j = 0; j < 6; j++) {
                    HSSFCell cell = row.createCell((short) j);
                    int pId = Integer.parseInt(order.getProducts()[i].getpId());
                    if (j == 0) {
                        cell.setCellValue(order.getUser().getUsername());
                        //cell.setCellValue((Calendar) style);设置背景色
                    } else if (j == 1) {
                        cell.setCellValue(pId);
                    } else if (j == 2) {
                        Map<Integer, Integer> ammount = order.getAmmount();
                        int productNum = ammount.get(pId);
                        cell.setCellValue(productNum);
                    } else if (j == 3) {
                        Map<Integer, Float> totalAmountPerProduct = order.getTotalAmonutProProduct();
                        float productTotalPay = totalAmountPerProduct.get(pId);
                        cell.setCellValue(productTotalPay);
                    }
                }
            }

            FileOutputStream fOut = new FileOutputStream(outputFile);
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
            System.out.println("文件生成");
        } catch (Exception e) {
            System.out.println("已运行xlCreate() : " + e);
        }
    }
}
