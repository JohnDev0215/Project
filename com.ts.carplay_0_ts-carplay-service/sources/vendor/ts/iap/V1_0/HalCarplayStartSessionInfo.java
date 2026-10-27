package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HalCarplayStartSessionInfo {
    public HalWiredAttribute wiredAttribute = new HalWiredAttribute();
    public HalWirelessAttribute wirelessAttribute = new HalWirelessAttribute();
    public int port = 0;
    public String deviceIdentifier = new String();
    public String publicKey = new String();
    public String sourceVersion = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != HalCarplayStartSessionInfo.class) {
            return false;
        }
        HalCarplayStartSessionInfo other = (HalCarplayStartSessionInfo) otherObject;
        if (HidlSupport.deepEquals(this.wiredAttribute, other.wiredAttribute) && HidlSupport.deepEquals(this.wirelessAttribute, other.wirelessAttribute) && this.port == other.port && HidlSupport.deepEquals(this.deviceIdentifier, other.deviceIdentifier) && HidlSupport.deepEquals(this.publicKey, other.publicKey) && HidlSupport.deepEquals(this.sourceVersion, other.sourceVersion)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.wiredAttribute)), Integer.valueOf(HidlSupport.deepHashCode(this.wirelessAttribute)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.port))), Integer.valueOf(HidlSupport.deepHashCode(this.deviceIdentifier)), Integer.valueOf(HidlSupport.deepHashCode(this.publicKey)), Integer.valueOf(HidlSupport.deepHashCode(this.sourceVersion)));
    }

    public final String toString() {
        return "{.wiredAttribute = " + this.wiredAttribute + ", .wirelessAttribute = " + this.wirelessAttribute + ", .port = " + this.port + ", .deviceIdentifier = " + this.deviceIdentifier + ", .publicKey = " + this.publicKey + ", .sourceVersion = " + this.sourceVersion + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(144L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HalCarplayStartSessionInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HalCarplayStartSessionInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 144, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalCarplayStartSessionInfo _hidl_vec_element = new HalCarplayStartSessionInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 144);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.wiredAttribute.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 0);
        this.wirelessAttribute.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 24);
        this.port = _hidl_blob.getInt32(_hidl_offset + 88);
        this.deviceIdentifier = _hidl_blob.getString(_hidl_offset + 96);
        parcel.readEmbeddedBuffer(r3.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 96 + 0, false);
        this.publicKey = _hidl_blob.getString(_hidl_offset + 112);
        parcel.readEmbeddedBuffer(r3.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 112 + 0, false);
        this.sourceVersion = _hidl_blob.getString(_hidl_offset + 128);
        parcel.readEmbeddedBuffer(r3.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 128 + 0, false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(144);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HalCarplayStartSessionInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 144);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 144);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        this.wiredAttribute.writeEmbeddedToBlob(_hidl_blob, 0 + _hidl_offset);
        this.wirelessAttribute.writeEmbeddedToBlob(_hidl_blob, 24 + _hidl_offset);
        _hidl_blob.putInt32(88 + _hidl_offset, this.port);
        _hidl_blob.putString(96 + _hidl_offset, this.deviceIdentifier);
        _hidl_blob.putString(112 + _hidl_offset, this.publicKey);
        _hidl_blob.putString(128 + _hidl_offset, this.sourceVersion);
    }
}
