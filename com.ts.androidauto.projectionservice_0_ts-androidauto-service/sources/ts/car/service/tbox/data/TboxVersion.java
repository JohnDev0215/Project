package ts.car.service.tbox.data;

import android.os.Bundle;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public final class TboxVersion {
    public String hwVersion;
    public Bundle mExtras;
    public String productModel;
    public String swVersion;

    protected TboxVersion(Parcel in) {
        this.swVersion = new String();
        this.hwVersion = new String();
        this.productModel = new String();
        this.mExtras = null;
        this.swVersion = in.readString();
        this.hwVersion = in.readString();
        this.productModel = in.readString();
        this.mExtras = in.readBundle();
    }

    public String getSwVersion() {
        return this.swVersion;
    }

    public void setSwVersion(String swVersion) {
        this.swVersion = swVersion;
    }

    public String getHwVersion() {
        return this.hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    public String getProductModel() {
        return this.productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public void setExtras(Bundle extras) {
        this.mExtras = extras;
    }

    public TboxVersion() {
        this.swVersion = new String();
        this.hwVersion = new String();
        this.productModel = new String();
        this.mExtras = null;
        this.mExtras = new Bundle();
    }

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != TboxVersion.class) {
            return false;
        }
        TboxVersion other = (TboxVersion) otherObject;
        if (!this.swVersion.equals(other.swVersion) || !this.hwVersion.equals(other.hwVersion)) {
            return false;
        }
        return this.productModel.equals(other.productModel);
    }

    public final String toString() {
        return "{.swVersion = " + this.swVersion + ", .hwVersion = " + this.hwVersion + ", .productModel = " + this.productModel + ", .mExtras = " + this.mExtras + "}";
    }
}
