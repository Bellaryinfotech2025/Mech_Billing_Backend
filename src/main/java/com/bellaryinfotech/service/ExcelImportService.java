package com.bellaryinfotech.service;
 



import com.bellaryinfotech.model.OrderFabricationImport;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ExcelImportService {
    List<OrderFabricationImport> importExcelToDatabase(MultipartFile file) throws Exception;
}

