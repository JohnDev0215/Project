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

    public void setCONNECTION(boolean z3) {
        this.CONNECTION = z3;
    }

    public void setCUTTING_OUT(boolean z3) {
        this.CUTTING_OUT = z3;
    }

    public void setSHORT_OUT(boolean z3) {
        this.SHORT_OUT = z3;
    }

    public void setSUPPLIER_NAME(String str) {
        this.SUPPLIER_NAME = str;
    }

    public String toString() {
        return "DiagnoseDeviceStatus{SHORT_OUT=" + this.SHORT_OUT + ", CUTTING_OUT=" + this.CUTTING_OUT + ", CONNECTION=" + this.CONNECTION + ", SUPPLIER_NAME='" + this.SUPPLIER_NAME + "'}";
    }
}
