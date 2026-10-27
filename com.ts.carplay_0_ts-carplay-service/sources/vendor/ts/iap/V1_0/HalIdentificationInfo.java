package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HalIdentificationInfo {
    public String name = new String();
    public String modelIdentifier = new String();
    public String manufacturer = new String();
    public String serialNumber = new String();
    public String firmwareVersion = new String();
    public String hardwareVersion = new String();
    public String currentLanguage = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != HalIdentificationInfo.class) {
            return false;
        }
        HalIdentificationInfo other = (HalIdentificationInfo) otherObject;
        if (HidlSupport.deepEquals(this.name, other.name) && HidlSupport.deepEquals(this.modelIdentifier, other.modelIdentifier) && HidlSupport.deepEquals(this.manufacturer, other.manufacturer) && HidlSupport.deepEquals(this.serialNumber, other.serialNumber) && HidlSupport.deepEquals(this.firmwareVersion, other.firmwareVersion) && HidlSupport.deepEquals(this.hardwareVersion, other.hardwareVersion) && HidlSupport.deepEquals(this.currentLanguage, other.currentLanguage)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(this.modelIdentifier)), Integer.valueOf(HidlSupport.deepHashCode(this.manufacturer)), Integer.valueOf(HidlSupport.deepHashCode(this.serialNumber)), Integer.valueOf(HidlSupport.deepHashCode(this.firmwareVersion)), Integer.valueOf(HidlSupport.deepHashCode(this.hardwareVersion)), Integer.valueOf(HidlSupport.deepHashCode(this.currentLanguage)));
    }

    public final String toString() {
        return "{.name = " + this.name + ", .modelIdentifier = " + this.modelIdentifier + ", .manufacturer = " + this.manufacturer + ", .serialNumber = " + this.serialNumber + ", .firmwareVersion = " + this.firmwareVersion + ", .hardwareVersion = " + this.hardwareVersion + ", .currentLanguage = " + this.currentLanguage + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(112L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HalIdentificationInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HalIdentificationInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 112, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalIdentificationInfo _hidl_vec_element = new HalIdentificationInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 112);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.name = _hidl_blob.getString(_hidl_offset + 0);
        parcel.readEmbeddedBuffer(r4.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 0 + 0, false);
        this.modelIdentifier = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 16 + 0, false);
        this.manufacturer = _hidl_blob.getString(_hidl_offset + 32);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 32 + 0, false);
        this.serialNumber = _hidl_blob.getString(_hidl_offset + 48);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 48 + 0, false);
        this.firmwareVersion = _hidl_blob.getString(_hidl_offset + 64);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 64 + 0, false);
        this.hardwareVersion = _hidl_blob.getString(_hidl_offset + 80);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 80 + 0, false);
        this.currentLanguage = _hidl_blob.getString(_hidl_offset + 96);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 96 + 0, false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(112);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HalIdentificationInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 112);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 112);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.name);
        _hidl_blob.putString(16 + _hidl_offset, this.modelIdentifier);
        _hidl_blob.putString(32 + _hidl_offset, this.manufacturer);
        _hidl_blob.putString(48 + _hidl_offset, this.serialNumber);
        _hidl_blob.putString(64 + _hidl_offset, this.firmwareVersion);
        _hidl_blob.putString(80 + _hidl_offset, this.hardwareVersion);
        _hidl_blob.putString(96 + _hidl_offset, this.currentLanguage);
    }
}
