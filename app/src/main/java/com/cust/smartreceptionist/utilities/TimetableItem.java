package com.cust.smartreceptionist.utilities;

import java.util.ArrayList;

public class TimetableItem {
   private String date;
   private ArrayList<ConsultancyslotItem> consultancyslotItems;


    public TimetableItem(String date, ArrayList<ConsultancyslotItem> consultancyslotItems) {
        this.date = date;
        this.consultancyslotItems = consultancyslotItems;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<ConsultancyslotItem> getConsultancyslotItems() {
        return consultancyslotItems;
    }
}
