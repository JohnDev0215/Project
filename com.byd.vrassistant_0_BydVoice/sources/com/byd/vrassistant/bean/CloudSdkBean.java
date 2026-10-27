package com.byd.vrassistant.bean;

import com.google.gson.JsonObject;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CloudSdkBean {
    List<CloudSdkArbitrationBean> arbitration_timeout;
    List<CloudSdkSubBean> rules;
    String update_date;
    String version;

    public static class LanguageRule {
        List<String> languages;
        JsonObject rule;

        public JsonObject getRule() {
            return this.rule;
        }

        public void setRule(JsonObject jsonObject) {
            this.rule = jsonObject;
        }

        public List<String> getLanguages() {
            return this.languages;
        }

        public void setLanguages(List<String> list) {
            this.languages = list;
        }

        public boolean validData() {
            return (getLanguages() == null || getRule() == null) ? false : true;
        }

        public String toString() {
            return "LanguageRule{languages=" + this.languages + ", rule='" + this.rule + "'}";
        }
    }

    public static class CloudSdkSubBean {
        List<String> country_codes;
        List<LanguageRule> language_rules;
        String note;
        List<String> vehicle_codes;

        public String getNote() {
            return this.note;
        }

        public void setNote(String str) {
            this.note = str;
        }

        public List<String> getCountry_codes() {
            return this.country_codes;
        }

        public void setCountry_codes(List<String> list) {
            this.country_codes = list;
        }

        public List<String> getVehicle_codes() {
            return this.vehicle_codes;
        }

        public void setVehicle_codes(List<String> list) {
            this.vehicle_codes = list;
        }

        public List<LanguageRule> getLanguage_rules() {
            return this.language_rules;
        }

        public void setLanguage_rules(List<LanguageRule> list) {
            this.language_rules = list;
        }

        public boolean validData() {
            return (getNote() == null || getLanguage_rules() == null || getCountry_codes() == null || getVehicle_codes() == null) ? false : true;
        }

        public String toString() {
            return "CloudSdkSubBean{note='" + this.note + "', country_codes=" + this.country_codes + ", vehicle_codes=" + this.vehicle_codes + ", language_rules=" + this.language_rules + '}';
        }
    }

    public static class LanguageArbitrationTimeout {
        JsonObject arbitration_timeout;
        List<String> languages;

        public List<String> getLanguages() {
            return this.languages;
        }

        public void setLanguages(List<String> list) {
            this.languages = list;
        }

        public JsonObject getArbitration_timeout() {
            return this.arbitration_timeout;
        }

        public void setArbitration_timeout(JsonObject jsonObject) {
            this.arbitration_timeout = jsonObject;
        }

        public boolean validData() {
            return (getLanguages() == null || getArbitration_timeout() == null) ? false : true;
        }

        public String toString() {
            return "LanguageArbitrationTimeout{languages=" + this.languages + ", arbitration_timeout=" + this.arbitration_timeout + '}';
        }
    }

    public static class CloudSdkArbitrationBean {
        List<String> country_codes;
        List<LanguageArbitrationTimeout> language_timeout;
        String note;
        List<String> vehicle_codes;

        public String getNote() {
            return this.note;
        }

        public void setNote(String str) {
            this.note = str;
        }

        public List<String> getCountry_codes() {
            return this.country_codes;
        }

        public void setCountry_codes(List<String> list) {
            this.country_codes = list;
        }

        public List<String> getVehicle_codes() {
            return this.vehicle_codes;
        }

        public boolean validData() {
            return (getNote() == null || getLanguage_timeout() == null || getCountry_codes() == null || getVehicle_codes() == null) ? false : true;
        }

        public void setVehicle_codes(List<String> list) {
            this.vehicle_codes = list;
        }

        public List<LanguageArbitrationTimeout> getLanguage_timeout() {
            return this.language_timeout;
        }

        public void setLanguage_timeout(List<LanguageArbitrationTimeout> list) {
            this.language_timeout = list;
        }

        public String toString() {
            return "CloudSdkArbitrationBean{note='" + this.note + "', country_codes=" + this.country_codes + ", vehicle_codes=" + this.vehicle_codes + ", language_timeout=" + this.language_timeout + '}';
        }
    }

    public List<CloudSdkSubBean> getRules() {
        return this.rules;
    }

    public void setRules(List<CloudSdkSubBean> list) {
        this.rules = list;
    }

    public List<CloudSdkArbitrationBean> getArbitration_timeout() {
        return this.arbitration_timeout;
    }

    public void setArbitration_timeout(List<CloudSdkArbitrationBean> list) {
        this.arbitration_timeout = list;
    }
}
