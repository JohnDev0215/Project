package android.hardware.bydauto.tools;

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
        return "DiagnoseDeviceStatus{SHORT_OUT=" + this.SHORT_OUT + ", CUTTING_OUT=" + this.CUTTING_OUT + ", CONNECTION=" + this.CONNECTION + ", SUPPLIER_NAME='" + this.SUPPLIER_NAME + "'}";
    }
}
