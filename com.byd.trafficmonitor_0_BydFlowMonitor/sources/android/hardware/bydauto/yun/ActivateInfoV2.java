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

    public ActivateInfoV2(int i2, int i3, int i4, int i5, long j, long j2, int i6, int i7, String str) {
        this.supplierType = i2;
        this.autoVinResult = i3;
        this.supplierName = i4;
        this.activateState = i5;
        this.activateTime = j;
        this.licenseTime = j2;
        this.activateCountry = i6;
        this.activateRegion = i7;
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

    public void setActivateCountry(int i2) {
        this.activateCountry = i2;
    }

    public void setActivateRegion(int i2) {
        this.activateRegion = i2;
    }

    public void setActivateState(int i2) {
        this.activateState = i2;
    }

    public void setActivateTime(long j) {
        this.activateTime = j;
    }

    public void setAppPkgName(String str) {
        this.appPkgName = str;
    }

    public void setAutoVinResult(int i2) {
        this.autoVinResult = i2;
    }

    public void setLicenseTime(long j) {
        this.licenseTime = j;
    }

    public void setSupplierName(int i2) {
        this.supplierName = i2;
    }

    public void setSupplierType(int i2) {
        this.supplierType = i2;
    }
}
