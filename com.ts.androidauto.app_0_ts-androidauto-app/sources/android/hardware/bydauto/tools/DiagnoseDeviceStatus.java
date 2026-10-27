package android.hardware.bydauto.tools;

/* JADX INFO: loaded from: classes.dex */
public class DiagnoseDeviceStatus {
    boolean CONNECTION;
    boolean CUTTING_OUT;
    boolean SHORT_OUT;
    String SUPPLIER_NAME;

    public boolean isSHORT_OUT() {
        return this.SHORT_OUT;
    }

    public void setSHORT_OUT(boolean SHORT_OUT) {
        this.SHORT_OUT = SHORT_OUT;
    }

    public boolean isCUTTING_OUT() {
        return this.CUTTING_OUT;
    }

    public void setCUTTING_OUT(boolean CUTTING_OUT) {
        this.CUTTING_OUT = CUTTING_OUT;
    }

    public boolean isCONNECTION() {
        return this.CONNECTION;
    }

    public void setCONNECTION(boolean CONNECTION) {
        this.CONNECTION = CONNECTION;
    }

    public String isSUPPLIER_NAME() {
        return this.SUPPLIER_NAME;
    }

    public void setSUPPLIER_NAME(String SUPPLIER_NAME) {
        this.SUPPLIER_NAME = SUPPLIER_NAME;
    }

    public String toString() {
        return "DiagnoseDeviceStatus{SHORT_OUT=" + this.SHORT_OUT + ", CUTTING_OUT=" + this.CUTTING_OUT + ", CONNECTION=" + this.CONNECTION + ", SUPPLIER_NAME='" + this.SUPPLIER_NAME + "'}";
    }
}
