package com.byd.music.beans;

import android.text.TextUtils;
import android.text.format.DateFormat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class RadioDBBean {
    private long DAB_SID;
    private long RDS_PI;
    private String RDS_PS;
    private int RDS_PTY;
    private String RDS_RT;
    private int artId;
    private int iconId;
    private long id;
    private int radioFrequency;
    private String radioName;
    private int type;

    public long getDAB_SID() {
        return this.DAB_SID;
    }

    public void setDAB_SID(long j) {
        this.DAB_SID = j;
    }

    public String getRDS_PS() {
        return this.RDS_PS;
    }

    public void setRDS_PS(String str) {
        this.RDS_PS = str;
    }

    public int getRDS_PTY() {
        return this.RDS_PTY;
    }

    public void setRDS_PTY(int i) {
        this.RDS_PTY = i;
    }

    public long getRDS_PI() {
        return this.RDS_PI;
    }

    public void setRDS_PI(long j) {
        this.RDS_PI = j;
    }

    public String getRDS_RT() {
        return this.RDS_RT;
    }

    public void setRDS_RT(String str) {
        this.RDS_RT = str;
    }

    public int getIconId() {
        return this.iconId;
    }

    public void setIconId(int i) {
        this.iconId = i;
    }

    public int getArtId() {
        return this.artId;
    }

    public void setArtId(int i) {
        this.artId = i;
    }

    public RadioDBBean() {
        this.DAB_SID = -1L;
    }

    @Deprecated
    public RadioDBBean(int i, String str) {
        this.radioFrequency = i;
        this.radioName = str;
        this.DAB_SID = -1L;
        this.RDS_PS = "";
    }

    public RadioDBBean(int i, int i2) {
        this.radioFrequency = i;
        this.type = i2;
        this.radioName = "";
        this.DAB_SID = -1L;
        this.RDS_PS = "";
    }

    public int getRadioFrequency() {
        return this.radioFrequency;
    }

    public void setRadioFrequency(int i) {
        this.radioFrequency = i;
    }

    public String getRadioName() {
        return this.radioName;
    }

    public void setRadioName(String str) {
        this.radioName = str;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void reset() {
        this.radioName = "";
        this.RDS_PI = 0L;
        this.RDS_PS = "";
        this.RDS_PTY = 0;
        this.RDS_RT = "";
        this.iconId = 0;
        this.artId = 0;
    }

    public long getUniqueFrequency() {
        if (this.type == 4) {
            return this.DAB_SID;
        }
        return this.radioFrequency;
    }

    public static RadioDBBean deepCopy(RadioDBBean radioDBBean) {
        RadioDBBean radioDBBean2 = new RadioDBBean();
        radioDBBean2.updateFrom(radioDBBean);
        return radioDBBean2;
    }

    public boolean isTheSameRadio(RadioDBBean radioDBBean) {
        return this.type == radioDBBean.type && getUniqueFrequency() == radioDBBean.getUniqueFrequency() && TextUtils.equals(this.radioName, radioDBBean.getRadioName()) && TextUtils.equals(getRDS_PS(), radioDBBean.getRDS_PS()) && this.iconId == radioDBBean.getIconId();
    }

    public boolean isFMRadio() {
        int i = this.type;
        return i == 2 || i == 3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RadioDBBean radioDBBean = (RadioDBBean) obj;
        long j = radioDBBean.DAB_SID;
        if (j > 0) {
            if (this.DAB_SID == j) {
                return true;
            }
        } else if (this.radioFrequency == radioDBBean.radioFrequency) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Object[] objArr = new Object[1];
        long j = this.DAB_SID;
        if (j <= 0) {
            j = this.radioFrequency;
        }
        objArr[0] = Long.valueOf(j);
        return Objects.hash(objArr);
    }

    public String toString() {
        return "FMRadioDBBean{id=" + this.id + ", type=" + this.type + ", radioFrequency=" + this.radioFrequency + ", radioName='" + this.radioName + DateFormat.QUOTE + ", RDS_PTY=" + this.RDS_PTY + ", RDS_PI=" + this.RDS_PI + ", RDS_PS='" + this.RDS_PS + DateFormat.QUOTE + ", DAB_SID=" + this.DAB_SID + ", RDS_RT='" + this.RDS_RT + DateFormat.QUOTE + ", iconId='" + this.iconId + DateFormat.QUOTE + ", artId='" + this.artId + DateFormat.QUOTE + '}';
    }

    public void updateFrom(RadioDBBean radioDBBean) {
        setRadioFrequency(radioDBBean.radioFrequency);
        setRadioName(radioDBBean.radioName);
        setDAB_SID(radioDBBean.DAB_SID);
        setRDS_PI(radioDBBean.RDS_PI);
        setRDS_PTY(radioDBBean.RDS_PTY);
        setRDS_PS(radioDBBean.RDS_PS);
        setRDS_RT(radioDBBean.RDS_RT);
        setType(radioDBBean.type);
        setIconId(radioDBBean.iconId);
        setArtId(radioDBBean.artId);
    }
}
