package com.cust.smartreceptionist.utilities;

public class AppointmentHistoryItem {
    private String tv_itemtitle;
    private String tv2_value;
    private String tv1_value;
    private String tv3_value;

    public AppointmentHistoryItem(String tv_itemtitle, String tv2_value, String patientname, String tv3_value) {
        this.tv_itemtitle = tv_itemtitle;
        this.tv2_value = tv2_value;
        this.tv1_value = patientname;
        this.tv3_value = tv3_value;
    }

    public String getTv_itemtitle() {
        return tv_itemtitle;
    }

    public String getTv2_value() {
        return tv2_value;
    }

    public String getTv1_value() {
        return tv1_value;
    }

    public String getTv3_value() {
        return tv3_value;
    }
}
