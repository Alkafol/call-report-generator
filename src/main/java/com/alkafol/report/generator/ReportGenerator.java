package com.alkafol.report.generator;

import com.alkafol.model.Call;

import java.io.IOException;
import java.util.List;

public interface ReportGenerator {
    void generateReport(List<Call> calls, long number) throws IOException;
}
