package ts.car.service.tbox.data;

import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class TboxUpgradeTrasportMode {
    private static final String TAG = "TboxUpgradeTrasportMode";
    public byte mode;
    public String passwd;
    public String url;
    public String user;

    public TboxUpgradeTrasportMode() {
        this.url = new String();
        this.user = new String();
        this.passwd = new String();
    }

    protected TboxUpgradeTrasportMode(Parcel in) {
        this.url = new String();
        this.user = new String();
        this.passwd = new String();
        this.mode = in.readByte();
        this.url = in.readString();
        this.user = in.readString();
        this.passwd = in.readString();
    }

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != TboxUpgradeTrasportMode.class) {
            return false;
        }
        TboxUpgradeTrasportMode other = (TboxUpgradeTrasportMode) otherObject;
        if (this.mode != other.mode || equalsString(this.url, other.url) || equalsString(this.user, other.user)) {
            return false;
        }
        return equalsString(this.passwd, other.passwd);
    }

    public final String toString() {
        return "{.mode = " + TrasportMode.toString(this.mode) + ", .url = " + this.url + ", .user = " + this.user + ", .passwd = " + this.passwd + "}";
    }

    public static boolean checkInvalidTboxUpgradeTrasportMode(TboxUpgradeTrasportMode mode) {
        if (mode == null) {
            Log.e(TAG, "checkInvalidTboxUpgradeTrasportMode mode is null");
            return false;
        }
        String str = mode.url;
        if (str == null || mode.user == null || mode.passwd == null) {
            Log.e(TAG, "checkInvalidTboxUpgradeTrasportMode mode url:" + mode.url + " mode.user:" + mode.user + " mode.passwd:" + mode.passwd);
            return false;
        }
        if (TextUtils.isEmpty(str.trim()) || TextUtils.isEmpty(mode.user) || TextUtils.isEmpty(mode.passwd)) {
            Log.e(TAG, "checkInvalidTboxUpgradeTrasportMode mode url:" + mode.url + " mode.user:" + mode.user + " mode.passwd:" + mode.passwd);
            return false;
        }
        return true;
    }

    private boolean equalsString(String left, String right) {
        if (left == null && right != null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }
        return left.equals(right);
    }
}
