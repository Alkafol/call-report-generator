package com.alkafol.report.generator;

import com.alkafol.model.Call;
import com.alkafol.report.writer.ReportWriter;
import com.alkafol.report.writer.ReportWriterToFile;

import java.io.IOException;
import java.util.List;

public class DefaultTariffReportGenerator implements ReportGenerator{

    @Override
    public void generateReport(List<Call> calls, long number) throws IOException {
        ReportWriter reportWriter = new ReportWriterToFile("reports/" + number + ".txt");
        reportWriter.createHeader("11", number);

        int spendMinutes = 0;
        double currentPrice = 0;
        calls.sort((firstCall, secondCall) -> firstCall.getStartingTime().compareTo(secondCall.getStartingTime()));

        for (Call call : calls){
            if (call.getCallType().equals("02")){
                reportWriter.createCallLine(call, 0);
                continue;
            }

            int callDurability = call.countMinuteDurability();
            double callPrice = 0;
            if (spendMinutes > 100){
                callPrice += callDurability * 1.5;
            }
            else if (spendMinutes + callDurability > 100){
                callPrice += (100 - spendMinutes) * 0.5;
                callPrice += (spendMinutes + callDurability - 100) * 1.5;
            }
            else {
                callPrice += callDurability * 0.5;
            }

            reportWriter.createCallLine(call, callPrice);

            currentPrice += callPrice;
            spendMinutes += callDurability;
        }

        reportWriter.createFooter(currentPrice);
    }
}
