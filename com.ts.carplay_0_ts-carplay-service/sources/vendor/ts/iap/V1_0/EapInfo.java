package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class EapInfo {
    public byte eapIdentifier = 0;
    public String eapName = new String();
    public byte eapMatchAction = 0;
    public boolean isNativeTransport = false;
    public short nativeTransportId = 0;
    public boolean isEapCarPlay = false;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != EapInfo.class) {
            return false;
        }
        EapInfo other = (EapInfo) otherObject;
        if (this.eapIdentifier == other.eapIdentifier && HidlSupport.deepEquals(this.eapName, other.eapName) && this.eapMatchAction == other.eapMatchAction && this.isNativeTransport == other.isNativeTransport && this.nativeTransportId == other.nativeTransportId && this.isEapCarPlay == other.isEapCarPlay) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.eapIdentifier))), Integer.valueOf(HidlSupport.deepHashCode(this.eapName)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.eapMatchAction))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isNativeTransport))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.nativeTransportId))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isEapCarPlay))));
    }

    public final String toString() {
        return "{.eapIdentifier = " + ((int) this.eapIdentifier) + ", .eapName = " + this.eapName + ", .eapMatchAction = " + ((int) this.eapMatchAction) + ", .isNativeTransport = " + this.isNativeTransport + ", .nativeTransportId = " + ((int) this.nativeTransportId) + ", .isEapCarPlay = " + this.isEapCarPlay + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(32L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<EapInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<EapInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            EapInfo _hidl_vec_element = new EapInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 32);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.eapIdentifier = _hidl_blob.getInt8(_hidl_offset + 0);
        this.eapName = _hidl_blob.getString(_hidl_offset + 8);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 8 + 0, false);
        this.eapMatchAction = _hidl_blob.getInt8(_hidl_offset + 24);
        this.isNativeTransport = _hidl_blob.getBool(_hidl_offset + 25);
        this.nativeTransportId = _hidl_blob.getInt16(_hidl_offset + 26);
        this.isEapCarPlay = _hidl_blob.getBool(_hidl_offset + 28);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(32);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<EapInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 32);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt8(0 + _hidl_offset, this.eapIdentifier);
        _hidl_blob.putString(8 + _hidl_offset, this.eapName);
        _hidl_blob.putInt8(24 + _hidl_offset, this.eapMatchAction);
        _hidl_blob.putBool(25 + _hidl_offset, this.isNativeTransport);
        _hidl_blob.putInt16(26 + _hidl_offset, this.nativeTransportId);
        _hidl_blob.putBool(28 + _hidl_offset, this.isEapCarPlay);
    }
}
