package com.byd.card.floatcard;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CardStatusBean {
    private String mPackage;
    private int showStatus;

    public String getPackage() {
        return this.mPackage;
    }

    public void setPackage(String mPackage) {
        this.mPackage = mPackage;
    }

    public int getShowStatus() {
        return this.showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }

    public void update(CardStatusBean cardShowStatusBean) {
        this.mPackage = cardShowStatusBean.getPackage();
        this.showStatus = cardShowStatusBean.getShowStatus();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && getClass() == o.getClass()) {
            CardStatusBean cardStatusBean = (CardStatusBean) o;
            if (this.showStatus == cardStatusBean.showStatus && Objects.equals(this.mPackage, cardStatusBean.mPackage)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "CardStatusBean{mPackage='" + this.mPackage + "', showStatus=" + this.showStatus + '}';
    }

    public int hashCode() {
        return Objects.hash(this.mPackage, Integer.valueOf(this.showStatus));
    }
}
