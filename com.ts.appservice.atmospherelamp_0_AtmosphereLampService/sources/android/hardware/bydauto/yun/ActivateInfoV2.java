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

    public ActivateInfoV2(int supplierType, int autoVinResult, int supplierName, int activateState, long activateTime, long licenseTime, int activateCountry, int activateRegion, String appPkgName) {
        this.supplierType = supplierType;
        this.autoVinResult = autoVinResult;
        this.supplierName = supplierName;
        this.activateState = activateState;
        this.activateTime = activateTime;
        this.licenseTime = licenseTime;
        this.activateCountry = activateCountry;
        this.activateRegion = activateRegion;
        this.appPkgName = appPkgName;
    }

    public int getSupplierType() {
        return this.supplierType;
    }

    public void setSupplierType(int supplierType) {
        this.supplierType = supplierType;
    }

    public int getAutoVinResult() {
        return this.autoVinResult;
    }

    public void setAutoVinResult(int autoVinResult) {
        this.autoVinResult = autoVinResult;
    }

    public int getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(int supplierName) {
        this.supplierName = supplierName;
    }

    public int getActivateState() {
        return this.activateState;
    }

    public void setActivateState(int activateState) {
        this.activateState = activateState;
    }

    public long getActivateTime() {
        return this.activateTime;
    }

    public void setActivateTime(long activateTime) {
        this.activateTime = activateTime;
    }

    public long getLicenseTime() {
        return this.licenseTime;
    }

    public void setLicenseTime(long licenseTime) {
        this.licenseTime = licenseTime;
    }

    public int getActivateCountry() {
        return this.activateCountry;
    }

    public void setActivateCountry(int activateCountry) {
        this.activateCountry = activateCountry;
    }

    public int getActivateRegion() {
        return this.activateRegion;
    }

    public void setActivateRegion(int activateRegion) {
        this.activateRegion = activateRegion;
    }

    public String getAppPkgName() {
        return this.appPkgName;
    }

    public void setAppPkgName(String appPkgName) {
        this.appPkgName = appPkgName;
    }
}
