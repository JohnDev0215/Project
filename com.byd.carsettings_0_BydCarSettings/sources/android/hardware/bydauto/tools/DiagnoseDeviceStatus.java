package android.hardware.bydauto.tools;

/* JADX INFO: loaded from: classes.dex */
public class DiagnoseDeviceStatus {
    public boolean CONNECTION;
    public boolean CUTTING_OUT;
    public boolean SHORT_OUT;
    public String SUPPLIER_NAME;

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

    public void setCONNECTION(boolean z) {
        this.CONNECTION = z;
    }

    public void setCUTTING_OUT(boolean z) {
        this.CUTTING_OUT = z;
    }

    public void setSHORT_OUT(boolean z) {
        this.SHORT_OUT = z;
    }

    public void setSUPPLIER_NAME(String str) {
        this.SUPPLIER_NAME = str;
    }

    public String toString() {
        return "DiagnoseDeviceStatus{SHORT_OUT=" + this.SHORT_OUT + ", CUTTING_OUT=" + this.CUTTING_OUT + ", CONNECTION=" + this.CONNECTION + ", SUPPLIER_NAME='" + this.SUPPLIER_NAME + "'}";
    }
}
