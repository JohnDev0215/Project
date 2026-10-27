package android.hardware.automotive.occupant_awareness;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class GazeDetection implements Parcelable {
    public static final Parcelable.Creator<GazeDetection> CREATOR = new Parcelable.Creator<GazeDetection>() { // from class: android.hardware.automotive.occupant_awareness.GazeDetection.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GazeDetection createFromParcel(Parcel _aidl_source) {
            GazeDetection _aidl_out = new GazeDetection();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GazeDetection[] newArray(int _aidl_size) {
            return new GazeDetection[_aidl_size];
        }
    };
    public String customGazeTarget;
    public double[] gazeAngleUnitVector;
    public byte gazeConfidence;
    public int gazeTarget;
    public double[] headAngleUnitVector;
    public double[] headPosition;
    public long timeOnTargetMillis;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        _aidl_parcel.writeInt(0);
        _aidl_parcel.writeByte(this.gazeConfidence);
        _aidl_parcel.writeDoubleArray(this.headPosition);
        _aidl_parcel.writeDoubleArray(this.headAngleUnitVector);
        _aidl_parcel.writeDoubleArray(this.gazeAngleUnitVector);
        _aidl_parcel.writeInt(this.gazeTarget);
        _aidl_parcel.writeString(this.customGazeTarget);
        _aidl_parcel.writeLong(this.timeOnTargetMillis);
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
            this.gazeConfidence = _aidl_parcel.readByte();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.headPosition = _aidl_parcel.createDoubleArray();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.headAngleUnitVector = _aidl_parcel.createDoubleArray();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.gazeAngleUnitVector = _aidl_parcel.createDoubleArray();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.gazeTarget = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.customGazeTarget = _aidl_parcel.readString();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.timeOnTargetMillis = _aidl_parcel.readLong();
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
