package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HalWirelessAttribute {
    public boolean exsits = false;
    public String ipAddress = new String();
    public String wifiSsid = new String();
    public String passphrase = new String();
    public short channel = 0;
    public byte securityType = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != HalWirelessAttribute.class) {
            return false;
        }
        HalWirelessAttribute other = (HalWirelessAttribute) otherObject;
        if (this.exsits == other.exsits && HidlSupport.deepEquals(this.ipAddress, other.ipAddress) && HidlSupport.deepEquals(this.wifiSsid, other.wifiSsid) && HidlSupport.deepEquals(this.passphrase, other.passphrase) && this.channel == other.channel && this.securityType == other.securityType) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.exsits))), Integer.valueOf(HidlSupport.deepHashCode(this.ipAddress)), Integer.valueOf(HidlSupport.deepHashCode(this.wifiSsid)), Integer.valueOf(HidlSupport.deepHashCode(this.passphrase)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.channel))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.securityType))));
    }

    public final String toString() {
        return "{.exsits = " + this.exsits + ", .ipAddress = " + this.ipAddress + ", .wifiSsid = " + this.wifiSsid + ", .passphrase = " + this.passphrase + ", .channel = " + ((int) this.channel) + ", .securityType = " + ((int) this.securityType) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(64L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HalWirelessAttribute> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HalWirelessAttribute> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 64, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalWirelessAttribute _hidl_vec_element = new HalWirelessAttribute();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 64);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.exsits = _hidl_blob.getBool(_hidl_offset + 0);
        this.ipAddress = _hidl_blob.getString(_hidl_offset + 8);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 8 + 0, false);
        this.wifiSsid = _hidl_blob.getString(_hidl_offset + 24);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 24 + 0, false);
        this.passphrase = _hidl_blob.getString(_hidl_offset + 40);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 40 + 0, false);
        this.channel = _hidl_blob.getInt16(_hidl_offset + 56);
        this.securityType = _hidl_blob.getInt8(_hidl_offset + 58);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(64);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HalWirelessAttribute> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 64);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 64);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putBool(0 + _hidl_offset, this.exsits);
        _hidl_blob.putString(8 + _hidl_offset, this.ipAddress);
        _hidl_blob.putString(24 + _hidl_offset, this.wifiSsid);
        _hidl_blob.putString(40 + _hidl_offset, this.passphrase);
        _hidl_blob.putInt16(56 + _hidl_offset, this.channel);
        _hidl_blob.putInt8(58 + _hidl_offset, this.securityType);
    }
}
