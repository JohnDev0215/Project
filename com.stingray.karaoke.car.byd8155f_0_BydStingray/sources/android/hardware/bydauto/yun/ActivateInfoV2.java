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

    public ActivateInfoV2(int i7, int i10, int i11, int i12, long j3, long j10, int i13, int i14, String str) {
        this.supplierType = i7;
        this.autoVinResult = i10;
        this.supplierName = i11;
        this.activateState = i12;
        this.activateTime = j3;
        this.licenseTime = j10;
        this.activateCountry = i13;
        this.activateRegion = i14;
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

    public void setActivateCountry(int i7) {
        this.activateCountry = i7;
    }

    public void setActivateRegion(int i7) {
        this.activateRegion = i7;
    }

    public void setActivateState(int i7) {
        this.activateState = i7;
    }

    public void setActivateTime(long j3) {
        this.activateTime = j3;
    }

    public void setAppPkgName(String str) {
        this.appPkgName = str;
    }

    public void setAutoVinResult(int i7) {
        this.autoVinResult = i7;
    }

    public void setLicenseTime(long j3) {
        this.licenseTime = j3;
    }

    public void setSupplierName(int i7) {
        this.supplierName = i7;
    }

    public void setSupplierType(int i7) {
        this.supplierType = i7;
    }
}
