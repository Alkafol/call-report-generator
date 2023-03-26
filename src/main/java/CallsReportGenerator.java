import com.alkafol.model.Call;
import com.alkafol.parser.Parser;
import com.alkafol.report.generator.DefaultTariffReportGenerator;
import com.alkafol.report.generator.PerMinuterTariffReportGenerator;
import com.alkafol.report.generator.ReportGenerator;
import com.alkafol.report.generator.UnlimitedTariffReportGenerator;
import com.alkafol.report.writer.ReportWriterToFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CallsReportGenerator {
    public static void main(String[] args) throws IOException {
        Map<Long, List<Call>> ans = Parser.parse();
        Files.createDirectories(Paths.get("reports"));

        for (Map.Entry<Long, List<Call>> entry : ans.entrySet()){
            long number = entry.getKey();
            List<Call> calls = entry.getValue();
            String tariff = calls.get(0).getTariffType();
            ReportGenerator reportGenerator;

            switch (tariff) {
                case "06":
                    reportGenerator = new UnlimitedTariffReportGenerator();
                    reportGenerator.generateReport(calls, number);
                    break;
                case "11":
                    reportGenerator = new DefaultTariffReportGenerator();
                    reportGenerator.generateReport(calls, number);
                    break;
                case "03":
                    reportGenerator = new PerMinuterTariffReportGenerator();
                    reportGenerator.generateReport(calls, number);
                    break;
            }
        }
    }
}
