package android.hardware.bydauto.tools;

import android.hardware.bydauto.ac.tsimpl.a;

/* JADX INFO: loaded from: classes.dex */
public class DiagnoseDeviceStatus {
    boolean CONNECTION;
    boolean CUTTING_OUT;
    boolean SHORT_OUT;
    String SUPPLIER_NAME;

    public boolean isCONNECTION() {
        return this.CONNECTION;
    }

    public boolean isCUTTING_OUT() {
        return this.CUTTING_OUT;
    }

    public boolean isSHORT_OUT() {
        return this.SHORT_OUT;
    }

    public String isSUPPLIER_NAME() {
        return this.SUPPLIER_NAME;
    }

    public void setCONNECTION(boolean z6) {
        this.CONNECTION = z6;
    }

    public void setCUTTING_OUT(boolean z6) {
        this.CUTTING_OUT = z6;
    }

    public void setSHORT_OUT(boolean z6) {
        this.SHORT_OUT = z6;
    }

    public void setSUPPLIER_NAME(String str) {
        this.SUPPLIER_NAME = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DiagnoseDeviceStatus{SHORT_OUT=");
        sb.append(this.SHORT_OUT);
        sb.append(", CUTTING_OUT=");
        sb.append(this.CUTTING_OUT);
        sb.append(", CONNECTION=");
        sb.append(this.CONNECTION);
        sb.append(", SUPPLIER_NAME='");
        return a.l(sb, this.SUPPLIER_NAME, "'}");
    }
}
