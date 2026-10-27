package android.hardware.automotive.occupant_awareness;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DriverMonitoringDetection implements Parcelable {
    public static final Parcelable.Creator<DriverMonitoringDetection> CREATOR = new Parcelable.Creator<DriverMonitoringDetection>() { // from class: android.hardware.automotive.occupant_awareness.DriverMonitoringDetection.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DriverMonitoringDetection createFromParcel(Parcel _aidl_source) {
            DriverMonitoringDetection _aidl_out = new DriverMonitoringDetection();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DriverMonitoringDetection[] newArray(int _aidl_size) {
            return new DriverMonitoringDetection[_aidl_size];
        }
    };
    public byte confidenceScore;
    public long gazeDurationMillis;
    public boolean isLookingOnRoad;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeByte(this.confidenceScore);
        parcel.writeInt(this.isLookingOnRoad ? 1 : 0);
        parcel.writeLong(this.gazeDurationMillis);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel _aidl_parcel) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        int _aidl_parcelable_size = _aidl_parcel.readInt();
        if (_aidl_parcelable_size < 0) {
            return;
        }
        try {
            this.confidenceScore = _aidl_parcel.readByte();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.isLookingOnRoad = _aidl_parcel.readInt() != 0;
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.gazeDurationMillis = _aidl_parcel.readLong();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
            }
        } finally {
            _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
