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

    public ActivateInfoV2(int i, int i2, int i3, int i4, long j, long j2, int i5, int i6, String str) {
        this.supplierType = i;
        this.autoVinResult = i2;
        this.supplierName = i3;
        this.activateState = i4;
        this.activateTime = j;
        this.licenseTime = j2;
        this.activateCountry = i5;
        this.activateRegion = i6;
        this.appPkgName = str;
    }

    public int getSupplierType() {
        return this.supplierType;
    }

    public void setSupplierType(int i) {
        this.supplierType = i;
    }

    public int getAutoVinResult() {
        return this.autoVinResult;
    }

    public void setAutoVinResult(int i) {
        this.autoVinResult = i;
    }

    public int getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(int i) {
        this.supplierName = i;
    }

    public int getActivateState() {
        return this.activateState;
    }

    public void setActivateState(int i) {
        this.activateState = i;
    }

    public long getActivateTime() {
        return this.activateTime;
    }

    public void setActivateTime(long j) {
        this.activateTime = j;
    }

    public long getLicenseTime() {
        return this.licenseTime;
    }

    public void setLicenseTime(long j) {
        this.licenseTime = j;
    }

    public int getActivateCountry() {
        return this.activateCountry;
    }

    public void setActivateCountry(int i) {
        this.activateCountry = i;
    }

    public int getActivateRegion() {
        return this.activateRegion;
    }

    public void setActivateRegion(int i) {
        this.activateRegion = i;
    }

    public String getAppPkgName() {
        return this.appPkgName;
    }

    public void setAppPkgName(String str) {
        this.appPkgName = str;
    }
}
