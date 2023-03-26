package com.alkafol.report.generator;

import com.alkafol.model.Call;
import com.alkafol.report.writer.ReportWriter;
import com.alkafol.report.writer.ReportWriterToFile;

import java.io.IOException;
import java.util.List;

public class PerMinuterTariffReportGenerator implements ReportGenerator{
    @Override
    public void generateReport(List<Call> calls, long number) throws IOException {
        ReportWriter reportWriter = new ReportWriterToFile("reports/" + number + ".txt");

        double minutePrice = 1.5;
        double currentPrice = 0;

        reportWriter.createHeader("03", number);

        calls.sort((firstCall, secondCall) -> firstCall.getStartingTime().compareTo(secondCall.getStartingTime()));

        for (Call call : calls){
            double callPrice = call.countMinuteDurability() * minutePrice;;

            reportWriter.createCallLine(call, callPrice);

            currentPrice += callPrice;
        }

        reportWriter.createFooter(currentPrice);
    }
}
