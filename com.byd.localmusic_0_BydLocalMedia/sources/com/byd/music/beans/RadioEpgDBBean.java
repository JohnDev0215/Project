package com.byd.music.beans;

import android.text.format.DateFormat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class RadioEpgDBBean {
    private long dabSid;
    private String desc;
    private long epgEndTime;
    private long epgStartTime;
    private long id;

    public RadioEpgDBBean() {
    }

    public RadioEpgDBBean(long j, long j2, long j3, String str) {
        this.dabSid = j;
        this.epgStartTime = j2;
        this.epgEndTime = j3;
        this.desc = str;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getDabSid() {
        return this.dabSid;
    }

    public void setDabSid(long j) {
        this.dabSid = j;
    }

    public long getEpgStartTime() {
        return this.epgStartTime;
    }

    public void setEpgStartTime(long j) {
        this.epgStartTime = j;
    }

    public long getEpgEndTime() {
        return this.epgEndTime;
    }

    public void setEpgEndTime(long j) {
        this.epgEndTime = j;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RadioEpgDBBean radioEpgDBBean = (RadioEpgDBBean) obj;
        return this.dabSid == radioEpgDBBean.dabSid && this.epgStartTime == radioEpgDBBean.epgStartTime && this.epgEndTime == radioEpgDBBean.epgEndTime;
    }

    public String toString() {
        return "RadioEpgDBBean{id=" + this.id + ", dabSid=" + this.dabSid + ", epgStartTime=" + this.epgStartTime + ", epgEndTime=" + this.epgEndTime + ", desc='" + this.desc + DateFormat.QUOTE + '}';
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.dabSid), Long.valueOf(this.epgStartTime), Long.valueOf(this.epgEndTime));
    }
}
