package com.js.sas.utils;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExportExcelUtils
 * @Description 导出工具类
 * @Author zc
 * @Date 2019/6/11 15:50
 **/
public class ExportExcelUtils {

    /**
     * @param response
     * @param fileName       文件名
     * @param sheetName      sheet页名
     * @param columnNameList 列明List
     * @param dataList       数据List
     * @return
     * @throws IOException
     */
    public Mono<Void> createExcel(ServerHttpResponse response, String fileName, String sheetName, List columnNameList, List<Map<String, String>> dataList)
            throws IOException {
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
        SXSSFSheet sxssfSheet = sxssfWorkbook.createSheet(sheetName);

        if (columnNameList != null) {
            SXSSFRow sxssfColumnNameRow = sxssfSheet.createRow(0);
            for (int index = 0; index < columnNameList.size(); index++) {
                sxssfColumnNameRow.createCell(index).setCellValue(columnNameList.get(index).toString());
            }
        }

        if (dataList != null) {
            for (int index = 0; index < dataList.size(); index++) {
                SXSSFRow sxssfDataRow = sxssfSheet.createRow(index + 1);
                int cIndex = 0;
                for (Map.Entry<String, String> entry : dataList.get(index).entrySet()) {
                    sxssfDataRow.createCell(cIndex++).setCellValue(entry.getValue().toString());
                }
            }
        }

        File file = new File("Download/" + System.currentTimeMillis() + fileName);

        return Mono.fromCallable(() -> {
            sxssfWorkbook.write(new FileOutputStream(file));
            return file;
        }).flatMap(f -> downloadFile(response, file, fileName)).doFinally(f -> file.deleteOnExit());
    }

    private Mono<Void> downloadFile(ServerHttpResponse response, File file, String fileName) {
        ZeroCopyHttpOutputMessage zeroCopyHttpOutputMessage = (ZeroCopyHttpOutputMessage) response;
        try {
            response.getHeaders()
                    .set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=".concat(URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName())));
            response.getHeaders().setContentType(MediaType.APPLICATION_PDF);
            return zeroCopyHttpOutputMessage.writeWith(file, 0, file.length());
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException();
        }
    }

}
