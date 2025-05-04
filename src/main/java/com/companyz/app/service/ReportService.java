
package com.companyz.app.service;
import com.companyz.app.dao.ReportDAO;
import java.util.*;
public class ReportService{
    private final ReportDAO dao=new ReportDAO();
    public List<String[]> totalByDivision(){return dao.totalByDivision();}
}
