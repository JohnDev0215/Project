package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HalCoordinateParameter {
    public boolean latitudeValid = false;
    public int latitude = 0;
    public boolean longitudeValid = false;
    public int longitude = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != HalCoordinateParameter.class) {
            return false;
        }
        HalCoordinateParameter other = (HalCoordinateParameter) otherObject;
        if (this.latitudeValid == other.latitudeValid && this.latitude == other.latitude && this.longitudeValid == other.longitudeValid && this.longitude == other.longitude) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.latitudeValid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.latitude))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.longitudeValid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.longitude))));
    }

    public final String toString() {
        return "{.latitudeValid = " + this.latitudeValid + ", .latitude = " + this.latitude + ", .longitudeValid = " + this.longitudeValid + ", .longitude = " + this.longitude + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(16L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HalCoordinateParameter> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HalCoordinateParameter> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 16, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalCoordinateParameter _hidl_vec_element = new HalCoordinateParameter();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 16);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.latitudeValid = _hidl_blob.getBool(0 + _hidl_offset);
        this.latitude = _hidl_blob.getInt32(4 + _hidl_offset);
        this.longitudeValid = _hidl_blob.getBool(8 + _hidl_offset);
        this.longitude = _hidl_blob.getInt32(12 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(16);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HalCoordinateParameter> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 16);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 16);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putBool(0 + _hidl_offset, this.latitudeValid);
        _hidl_blob.putInt32(4 + _hidl_offset, this.latitude);
        _hidl_blob.putBool(8 + _hidl_offset, this.longitudeValid);
        _hidl_blob.putInt32(12 + _hidl_offset, this.longitude);
    }
}
