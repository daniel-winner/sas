package com.js.sas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SasApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
//            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
//            // 获取第一个表单
//            Sheet first = workbook.getSheetAt(0);
//            for (int i = 0; i < 100000; i++) {
//                Row row = first.createRow(i);
//                for (int j = 0; j < 11; j++) {
//                    if(i == 0) {
//                        // 首行
//                        row.createCell(j).setCellValue("column" + j);
//                    } else {
//                        // 数据
//                        if (j == 0) {
//                            CellUtil.createCell(row, j, String.valueOf(i));
//                        } else
//                            CellUtil.createCell(row, j, String.valueOf(Math.random()));
//                    }
//                }
//            }
//            // 写入文件
//            FileOutputStream out = new FileOutputStream("workbook.xlsx");
//            workbook.write(out);
//            out.close();


    }

}
