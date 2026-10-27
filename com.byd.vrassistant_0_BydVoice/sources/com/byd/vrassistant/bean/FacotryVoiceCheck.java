package com.byd.vrassistant.bean;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class FacotryVoiceCheck {
    ArrayList<Integer> country_code;
    ArrayList<DpNamever> dp_vers;
    ArrayList<String> ivi_ver;

    public String toString() {
        return "FacotryVoiceCheck{ivi_ver=" + this.ivi_ver + ", country_code=" + this.country_code + ", dp_vers=" + this.dp_vers + '}';
    }

    public static class DpNamever {
        String dp_name;
        String dp_ver;

        public String toString() {
            return "DpNamever{dp_name='" + this.dp_name + "', dp_ver='" + this.dp_ver + "'}";
        }

        public String getDp_name() {
            return this.dp_name;
        }

        public String getDp_ver() {
            return this.dp_ver;
        }
    }

    public ArrayList<String> getIvi_ver() {
        return this.ivi_ver;
    }

    public void setIvi_ver(ArrayList<String> arrayList) {
        this.ivi_ver = arrayList;
    }

    public ArrayList<Integer> getCountry_code() {
        return this.country_code;
    }

    public void setCountry_code(ArrayList<Integer> arrayList) {
        this.country_code = arrayList;
    }

    public ArrayList<DpNamever> getDp_vers() {
        return this.dp_vers;
    }

    public void setDp_vers(ArrayList<DpNamever> arrayList) {
        this.dp_vers = arrayList;
    }
}
