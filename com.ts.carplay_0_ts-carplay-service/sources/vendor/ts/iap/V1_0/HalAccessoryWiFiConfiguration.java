package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HalAccessoryWiFiConfiguration {
    public String ssid = new String();
    public String passphrase = new String();
    public int securityType = 0;
    public int channel = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != HalAccessoryWiFiConfiguration.class) {
            return false;
        }
        HalAccessoryWiFiConfiguration other = (HalAccessoryWiFiConfiguration) otherObject;
        if (HidlSupport.deepEquals(this.ssid, other.ssid) && HidlSupport.deepEquals(this.passphrase, other.passphrase) && this.securityType == other.securityType && this.channel == other.channel) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.ssid)), Integer.valueOf(HidlSupport.deepHashCode(this.passphrase)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.securityType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.channel))));
    }

    public final String toString() {
        return "{.ssid = " + this.ssid + ", .passphrase = " + this.passphrase + ", .securityType = " + this.securityType + ", .channel = " + this.channel + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(40L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HalAccessoryWiFiConfiguration> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HalAccessoryWiFiConfiguration> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 40, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalAccessoryWiFiConfiguration _hidl_vec_element = new HalAccessoryWiFiConfiguration();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 40);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.ssid = _hidl_blob.getString(_hidl_offset + 0);
        parcel.readEmbeddedBuffer(r4.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 0 + 0, false);
        this.passphrase = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 16 + 0, false);
        this.securityType = _hidl_blob.getInt32(_hidl_offset + 32);
        this.channel = _hidl_blob.getInt32(_hidl_offset + 36);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(40);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HalAccessoryWiFiConfiguration> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 40);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 40);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.ssid);
        _hidl_blob.putString(16 + _hidl_offset, this.passphrase);
        _hidl_blob.putInt32(32 + _hidl_offset, this.securityType);
        _hidl_blob.putInt32(36 + _hidl_offset, this.channel);
    }
}
