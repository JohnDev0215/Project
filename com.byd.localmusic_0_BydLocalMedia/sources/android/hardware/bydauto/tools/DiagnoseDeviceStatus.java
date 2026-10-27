package android.hardware.bydauto.tools;

import android.text.format.DateFormat;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnoseDeviceStatus {
    boolean CONNECTION;
    boolean CUTTING_OUT;
    boolean SHORT_OUT;
    String SUPPLIER_NAME;

    public boolean isSHORT_OUT() {
        return this.SHORT_OUT;
    }

    public void setSHORT_OUT(boolean z) {
        this.SHORT_OUT = z;
    }

    public boolean isCUTTING_OUT() {
        return this.CUTTING_OUT;
    }

    public void setCUTTING_OUT(boolean z) {
        this.CUTTING_OUT = z;
    }

    public boolean isCONNECTION() {
        return this.CONNECTION;
    }

    public void setCONNECTION(boolean z) {
        this.CONNECTION = z;
    }

    public String isSUPPLIER_NAME() {
        return this.SUPPLIER_NAME;
    }

    public void setSUPPLIER_NAME(String str) {
        this.SUPPLIER_NAME = str;
    }

    public String toString() {
        return "DiagnoseDeviceStatus{SHORT_OUT=" + this.SHORT_OUT + ", CUTTING_OUT=" + this.CUTTING_OUT + ", CONNECTION=" + this.CONNECTION + ", SUPPLIER_NAME='" + this.SUPPLIER_NAME + DateFormat.QUOTE + '}';
    }
}
