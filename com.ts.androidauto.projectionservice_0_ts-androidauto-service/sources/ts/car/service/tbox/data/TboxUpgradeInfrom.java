package ts.car.service.tbox.data;

import android.os.Parcel;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class TboxUpgradeInfrom {
    public byte type;
    public String url;
    public byte urlLen;
    public String verision;

    public TboxUpgradeInfrom() {
        this.verision = new String();
        this.url = new String();
    }

    protected TboxUpgradeInfrom(Parcel in) {
        this.verision = new String();
        this.url = new String();
        this.type = in.readByte();
        this.verision = in.readString();
        this.urlLen = in.readByte();
        this.url = in.readString();
    }

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != TboxUpgradeInfrom.class) {
            return false;
        }
        TboxUpgradeInfrom other = (TboxUpgradeInfrom) otherObject;
        if (this.type != other.type || !this.verision.equals(other.verision) || this.urlLen != other.urlLen) {
            return false;
        }
        return this.url.equals(other.url);
    }

    public final String toString() {
        return "{.type = " + ((int) this.type) + ", .verision = " + this.verision + ", .urlLen = " + ((int) this.urlLen) + ", .url = " + this.url + "}";
    }

    public static final boolean checkInvalidTboxUpgradeInfrom(TboxUpgradeInfrom infrom) {
        String str;
        if (infrom == null || (str = infrom.verision) == null || infrom.url == null || TextUtils.isEmpty(str.trim()) || TextUtils.isEmpty(infrom.url.trim())) {
            return false;
        }
        return true;
    }
}
