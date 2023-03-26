package com.alkafol.report.writer;

import com.alkafol.model.Call;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReportWriterToFile implements ReportWriter {
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final PrintWriter printWriter;

    public ReportWriterToFile(String filename) throws IOException {
        File file = new File(filename);
        printWriter = new PrintWriter(filename, StandardCharsets.UTF_8);
    }

    public void createHeader(String tariffIndex, long number){
        printWriter.println("Tariff index: " + tariffIndex);
        printWriter.println("----------------------------------------------------------------------------");
        printWriter.println("Report for phone number " + number + ":");
        printWriter.println("----------------------------------------------------------------------------");
        printWriter.println("| Call Type |   Start Time        |     End Time        | Duration | Cost  |");
        printWriter.println("----------------------------------------------------------------------------");
    }

    public void createCallLine(Call call, double callPrice){
        DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        printWriter.println(
                "|" + formatToCenter(call.getCallType(), 11) + "|"
                        + formatToCenter(call.getStartingTime().format(dateTimeformatter), 21) + "|"
                        + formatToCenter(call.getEndingTime().format(dateTimeformatter), 21) + "|"
                        + formatToCenter(LocalTime.ofSecondOfDay(call.countSecondDurability()).format(timeFormatter), 10) + "|"
                        + formatToCenter(String.valueOf(callPrice), 7) + "|"
        );
    }

    public void createFooter(double cost){
        printWriter.println("----------------------------------------------------------------------------");
        printWriter.println("|                                           Total Cost: |" + String.format("%10s", df.format(cost)).replace(',', '.') + " rubles |");
        printWriter.println("----------------------------------------------------------------------------");
        printWriter.close();
    }

    private String formatToCenter(String str, int width){
        int padding = width - str.length();
        int leftPad = padding / 2 + str.length();
        if (padding % 2 == 1){
            leftPad += 1;
        }
        return String.format("%-" + width + "s", String.format("%" + leftPad + "s", str));
    }
}
