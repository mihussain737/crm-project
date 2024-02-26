package com.crm.controller;

import com.crm.dto.LeadDto;
import com.crm.dto.LeadResponse;
import com.crm.entity.Lead;
import com.crm.service.LeadService;
import com.crm.service.impl.ExcelHelperService;
import com.crm.util.PdfGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/leads")
@AllArgsConstructor
public class LeadController {

    private LeadService leadService;
    @PostMapping
    public ResponseEntity<LeadDto> saveLead(@RequestBody LeadDto leadDto) {
        LeadDto saveLead = leadService.saveLead(leadDto);
        return new ResponseEntity<>(saveLead, HttpStatus.CREATED);
    }

    @DeleteMapping("/{lid}")
    public ResponseEntity<String> deleteByLid(@PathVariable String lid) {
        leadService.deleteLeadByLid(lid);
        return new ResponseEntity<>("Lead is deleted with id:-" + lid, HttpStatus.OK);
    }

    @GetMapping()
    public LeadResponse getAllLeads(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        LeadResponse allLeads = leadService.getAllLeads(pageNo, pageSize, sortBy, sortDir);
        return allLeads;
    }
    //http://localhost:8080/api/leads/excelReports

    @GetMapping("/excelReports")
    public ResponseEntity<Resource> getLeadsExcelReport() {
        List<Lead> leads = leadService.getLeadsExcelReports();
        ByteArrayInputStream leadsReports = ExcelHelperService.leadsToExcel(leads);
        String fileName = "leadReports.xlsx";
        InputStreamResource file = new InputStreamResource(leadsReports);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + fileName)
                .contentType(MediaType.parseMediaType(("application/vnd.ms-excel")))
                .body(file);
    }

    //http://localhost:8080/api/leads/pdfReport
    @GetMapping(value = "/pdfReport", produces =
            MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport()
            throws IOException {
        List<Lead> leads = leadService.getLeadsExcelReports();

        ByteArrayInputStream pdfReport = PdfGenerator
                .employeePDFReport(leads);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pdfReport.pdf");

        return ResponseEntity.ok().headers(headers).contentType
                        (MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfReport));
    }



}
