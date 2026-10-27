package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HalParameterSuccessfullyUsed {
    public HalCoordinateParameter centerCoordinate = new HalCoordinateParameter();
    public boolean addressValid = false;
    public String address = new String();
    public ArrayList<HalCoordinateParameter> entryPointList = new ArrayList<>();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != HalParameterSuccessfullyUsed.class) {
            return false;
        }
        HalParameterSuccessfullyUsed other = (HalParameterSuccessfullyUsed) otherObject;
        if (HidlSupport.deepEquals(this.centerCoordinate, other.centerCoordinate) && this.addressValid == other.addressValid && HidlSupport.deepEquals(this.address, other.address) && HidlSupport.deepEquals(this.entryPointList, other.entryPointList)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.centerCoordinate)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.addressValid))), Integer.valueOf(HidlSupport.deepHashCode(this.address)), Integer.valueOf(HidlSupport.deepHashCode(this.entryPointList)));
    }

    public final String toString() {
        return "{.centerCoordinate = " + this.centerCoordinate + ", .addressValid = " + this.addressValid + ", .address = " + this.address + ", .entryPointList = " + this.entryPointList + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(56L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HalParameterSuccessfullyUsed> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HalParameterSuccessfullyUsed> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 56, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalParameterSuccessfullyUsed _hidl_vec_element = new HalParameterSuccessfullyUsed();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 56);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.centerCoordinate.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 0);
        this.addressValid = _hidl_blob.getBool(_hidl_offset + 16);
        this.address = _hidl_blob.getString(_hidl_offset + 24);
        parcel.readEmbeddedBuffer(r3.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 24 + 0, false);
        int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 40 + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 16, _hidl_blob.handle(), _hidl_offset + 40 + 0, true);
        this.entryPointList.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalCoordinateParameter _hidl_vec_element = new HalCoordinateParameter();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 16);
            this.entryPointList.add(_hidl_vec_element);
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(56);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HalParameterSuccessfullyUsed> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 56);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 56);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        this.centerCoordinate.writeEmbeddedToBlob(_hidl_blob, _hidl_offset + 0);
        _hidl_blob.putBool(16 + _hidl_offset, this.addressValid);
        _hidl_blob.putString(24 + _hidl_offset, this.address);
        int _hidl_vec_size = this.entryPointList.size();
        _hidl_blob.putInt32(_hidl_offset + 40 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 40 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 16);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.entryPointList.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 16);
        }
        _hidl_blob.putBlob(40 + _hidl_offset + 0, childBlob);
    }
}
