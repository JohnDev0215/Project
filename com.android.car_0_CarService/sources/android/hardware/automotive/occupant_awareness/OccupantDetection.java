package android.hardware.automotive.occupant_awareness;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class OccupantDetection implements Parcelable {
    public static final Parcelable.Creator<OccupantDetection> CREATOR = new Parcelable.Creator<OccupantDetection>() { // from class: android.hardware.automotive.occupant_awareness.OccupantDetection.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OccupantDetection createFromParcel(Parcel _aidl_source) {
            OccupantDetection _aidl_out = new OccupantDetection();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OccupantDetection[] newArray(int _aidl_size) {
            return new OccupantDetection[_aidl_size];
        }
    };
    public DriverMonitoringDetection[] attentionData;
    public GazeDetection[] gazeData;
    public PresenceDetection[] presenceData;
    public int role;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeInt(this.role);
        _aidl_parcel.writeTypedArray(this.presenceData, 0);
        _aidl_parcel.writeTypedArray(this.gazeData, 0);
        _aidl_parcel.writeTypedArray(this.attentionData, 0);
        int _aidl_end_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.setDataPosition(_aidl_start_pos);
        _aidl_parcel.writeInt(_aidl_end_pos - _aidl_start_pos);
        _aidl_parcel.setDataPosition(_aidl_end_pos);
    }

    public final void readFromParcel(Parcel _aidl_parcel) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        int _aidl_parcelable_size = _aidl_parcel.readInt();
        if (_aidl_parcelable_size < 0) {
            return;
        }
        try {
            this.role = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.presenceData = (PresenceDetection[]) _aidl_parcel.createTypedArray(PresenceDetection.CREATOR);
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.gazeData = (GazeDetection[]) _aidl_parcel.createTypedArray(GazeDetection.CREATOR);
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.attentionData = (DriverMonitoringDetection[]) _aidl_parcel.createTypedArray(DriverMonitoringDetection.CREATOR);
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
