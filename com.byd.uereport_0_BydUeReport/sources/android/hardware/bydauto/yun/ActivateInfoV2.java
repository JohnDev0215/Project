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

    public ActivateInfoV2(int i8, int i9, int i10, int i11, long j8, long j9, int i12, int i13, String str) {
        this.supplierType = i8;
        this.autoVinResult = i9;
        this.supplierName = i10;
        this.activateState = i11;
        this.activateTime = j8;
        this.licenseTime = j9;
        this.activateCountry = i12;
        this.activateRegion = i13;
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

    public void setActivateCountry(int i8) {
        this.activateCountry = i8;
    }

    public void setActivateRegion(int i8) {
        this.activateRegion = i8;
    }

    public void setActivateState(int i8) {
        this.activateState = i8;
    }

    public void setActivateTime(long j8) {
        this.activateTime = j8;
    }

    public void setAppPkgName(String str) {
        this.appPkgName = str;
    }

    public void setAutoVinResult(int i8) {
        this.autoVinResult = i8;
    }

    public void setLicenseTime(long j8) {
        this.licenseTime = j8;
    }

    public void setSupplierName(int i8) {
        this.supplierName = i8;
    }

    public void setSupplierType(int i8) {
        this.supplierType = i8;
    }
}
