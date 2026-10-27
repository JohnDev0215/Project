package ts.car.service.tbox.data;

import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public final class TboxUpgradeStateInfo {
    public byte progress;
    public byte state;

    public TboxUpgradeStateInfo() {
    }

    protected TboxUpgradeStateInfo(Parcel in) {
        this.state = in.readByte();
        this.progress = in.readByte();
    }

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != TboxUpgradeStateInfo.class) {
            return false;
        }
        TboxUpgradeStateInfo other = (TboxUpgradeStateInfo) otherObject;
        if (this.state == other.state && this.progress == other.progress) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "{.state = " + TboxUpgradeState.toString(this.state) + ", .progress = " + ((int) this.progress) + "}";
    }
}
