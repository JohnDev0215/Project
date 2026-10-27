package ts.car.service.tbox.data;

import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class TboxLogExportState {
    public static final byte EXPORT_LOG_STATE_EXPORTING = 2;
    public static final byte EXPORT_LOG_STATE_FAILED = 1;
    public static final byte EXPORT_LOG_STATE_SUCCESS = 0;
    public byte progess;
    public byte status;

    public TboxLogExportState() {
    }

    protected TboxLogExportState(Parcel in) {
        this.status = in.readByte();
        this.progess = in.readByte();
    }

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != TboxLogExportState.class) {
            return false;
        }
        TboxLogExportState other = (TboxLogExportState) otherObject;
        if (this.status == other.status && this.progess == other.progess) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "{.status = " + ((int) this.status) + ", .progess = " + ((int) this.progess) + "}";
    }
}
