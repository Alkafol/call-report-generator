package com.alkafol.report.writer;

import com.alkafol.model.Call;

public interface ReportWriter {
    void createHeader(String tariffIndex, long number);
    void createCallLine(Call call, double callPrice);
    void createFooter(double cost);
}
