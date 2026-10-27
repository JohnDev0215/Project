package android.hardware.bydauto.yun;

/* JADX INFO: loaded from: classes.dex */
public class ActivateInfoV2 {
    private int activateCountry;
    private int activateRegion;
    private int activateState;
    private long activateTime;
    private String appPkgName;
    private int autoVinResult;
    private long licenseTime;
    private int supplierName;
    private int supplierType;

    public ActivateInfoV2(int i3, int i4, int i5, int i6, long j3, long j4, int i7, int i8, String str) {
        this.supplierType = i3;
        this.autoVinResult = i4;
        this.supplierName = i5;
        this.activateState = i6;
        this.activateTime = j3;
        this.licenseTime = j4;
        this.activateCountry = i7;
        this.activateRegion = i8;
        this.appPkgName = str;
    }

    public int getActivateCountry() {
        return this.activateCountry;
    }

    public int getActivateRegion() {
        return this.activateRegion;
    }

    public int getActivateState() {
        return this.activateState;
    }

    public long getActivateTime() {
        return this.activateTime;
    }

    public String getAppPkgName() {
        return this.appPkgName;
    }

    public int getAutoVinResult() {
        return this.autoVinResult;
    }

    public long getLicenseTime() {
        return this.licenseTime;
    }

    public int getSupplierName() {
        return this.supplierName;
    }

    public int getSupplierType() {
        return this.supplierType;
    }

    public void setActivateCountry(int i3) {
        this.activateCountry = i3;
    }

    public void setActivateRegion(int i3) {
        this.activateRegion = i3;
    }

    public void setActivateState(int i3) {
        this.activateState = i3;
    }

    public void setActivateTime(long j3) {
        this.activateTime = j3;
    }

    public void setAppPkgName(String str) {
        this.appPkgName = str;
    }

    public void setAutoVinResult(int i3) {
        this.autoVinResult = i3;
    }

    public void setLicenseTime(long j3) {
        this.licenseTime = j3;
    }

    public void setSupplierName(int i3) {
        this.supplierName = i3;
    }

    public void setSupplierType(int i3) {
        this.supplierType = i3;
    }
}
