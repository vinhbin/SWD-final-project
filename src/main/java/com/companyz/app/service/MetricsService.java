package com.companyz.app.service;

import com.companyz.app.dao.AdminMetricsDAO;

public class MetricsService {
    private final AdminMetricsDAO dao = new AdminMetricsDAO();

    public int totalEmployees()         { return dao.totalEmployees();      }
    public double payrollThisMonth()    { return dao.payrollThisMonth();    }
    public int employeesAddedThisMonth(){ return dao.employeesAddedThisMonth(); }
}
