package com.alkafol.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Call {
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;
    private String tariffType;
    private String callType;

    public Call(LocalDateTime startingTime, LocalDateTime endingTime, String tariffType, String callType) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.tariffType = tariffType;
        this.callType = callType;
    }

    public int countMinuteDurability(){
        return (int) ChronoUnit.MINUTES.between(startingTime, endingTime) + 1;
    }

    public int countSecondDurability(){ return (int) ChronoUnit.SECONDS.between(startingTime, endingTime); }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public String getTariffType() {
        return tariffType;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }
}
