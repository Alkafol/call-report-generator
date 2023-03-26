package com.alkafol.report.generator;

import com.alkafol.model.Call;
import com.alkafol.report.writer.ReportWriter;
import com.alkafol.report.writer.ReportWriterToFile;

import java.io.IOException;
import java.util.List;

public class UnlimitedTariffReportGenerator implements ReportGenerator{
    @Override
    public void generateReport(List<Call> calls, long number) throws IOException {
        ReportWriter reportWriter = new ReportWriterToFile("reports/" + number + ".txt");
        reportWriter.createHeader("06", number);

        int spendMinutes = 0;
        double currentPrice = 100;
        double minutePrice = 1.0;
        calls.sort((firstCall, secondCall) -> firstCall.getStartingTime().compareTo(secondCall.getStartingTime()));

        for (Call call : calls){
            int callDurability = call.countMinuteDurability();
            double callPrice = 0;

            if (spendMinutes > 300){
                callPrice = callDurability * minutePrice;
            }
            else if (spendMinutes + callDurability > 300){
                callPrice = (spendMinutes + callDurability - 300) * minutePrice;
            }

            reportWriter.createCallLine(call, callPrice);

            currentPrice += callPrice;
            spendMinutes += callDurability;
        }

        reportWriter.createFooter(currentPrice);
    }
}
