package ts.car.service.tbox.data;

import android.os.Bundle;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public class SignalInfo {
    public Bundle mExtras;
    public int rssi;
    public int strength;

    public SignalInfo() {
        this.mExtras = null;
        this.mExtras = new Bundle();
    }

    protected SignalInfo(Parcel in) {
        this.mExtras = null;
        this.strength = in.readInt();
        this.rssi = in.readInt();
        this.mExtras = in.readBundle();
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getRssi() {
        return this.rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public void setExtras(Bundle extras) {
        this.mExtras = extras;
    }

    public String toString() {
        return "SignalInfo{strength=" + this.strength + ", rssi=" + this.rssi + ", mExtras=" + this.mExtras + '}';
    }

    public static String toString(SignalInfo info) {
        if (info == null) {
            return "Signal info is null";
        }
        return info.toString();
    }
}
