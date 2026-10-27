package com.byd.vrassistant.hint.bean;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WakeUpFreeWordsSwitch {
    String language;
    List<SwitchStatus> swichList;

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public List<SwitchStatus> getSwichList() {
        return this.swichList;
    }

    public void setSwichList(List<SwitchStatus> list) {
        this.swichList = list;
    }

    public static class SwitchStatus {
        String key;
        boolean status;

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public boolean getStatus() {
            return this.status;
        }

        public void setStatus(boolean z) {
            this.status = z;
        }

        public String toString() {
            return "SwitchStatus{key='" + this.key + "', status=" + this.status + '}';
        }
    }

    public String toString() {
        return "WakeUpFreeWordsSwitch{language='" + this.language + "', swichList=" + this.swichList + '}';
    }
}
