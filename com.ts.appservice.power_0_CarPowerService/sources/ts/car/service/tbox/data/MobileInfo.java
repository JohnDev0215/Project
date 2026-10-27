package ts.car.service.tbox.data;

import android.os.Bundle;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public class MobileInfo {
    private String csca;
    private String iccid;
    private String imei;
    private String imsi;
    Bundle mExtras;
    private String phoneNo;

    public MobileInfo() {
        this.mExtras = null;
    }

    protected MobileInfo(Parcel in) {
        this.mExtras = null;
        this.imei = in.readString();
        this.imsi = in.readString();
        this.iccid = in.readString();
        this.phoneNo = in.readString();
        this.csca = in.readString();
        this.mExtras = in.readBundle();
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCsca() {
        return this.csca;
    }

    public void setCsca(String csca) {
        this.csca = csca;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public void setExtras(Bundle extras) {
        this.mExtras = extras;
    }

    public String toString() {
        return "MobileInfo [imei=" + this.imei + ", imsi=" + this.imsi + ", iccid=" + this.iccid + ", phoneNo=" + this.phoneNo + ", csca=" + this.csca + "]";
    }
}
