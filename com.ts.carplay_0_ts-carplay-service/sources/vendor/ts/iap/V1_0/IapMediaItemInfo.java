package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IapMediaItemInfo {
    public int index = 0;
    public long id = 0;
    public String name = new String();
    public String sort = new String();
    public String koName = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != IapMediaItemInfo.class) {
            return false;
        }
        IapMediaItemInfo other = (IapMediaItemInfo) otherObject;
        if (this.index == other.index && this.id == other.id && HidlSupport.deepEquals(this.name, other.name) && HidlSupport.deepEquals(this.sort, other.sort) && HidlSupport.deepEquals(this.koName, other.koName)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.index))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.id))), Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(this.sort)), Integer.valueOf(HidlSupport.deepHashCode(this.koName)));
    }

    public final String toString() {
        return "{.index = " + this.index + ", .id = " + this.id + ", .name = " + this.name + ", .sort = " + this.sort + ", .koName = " + this.koName + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(64L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<IapMediaItemInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<IapMediaItemInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 64, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            IapMediaItemInfo _hidl_vec_element = new IapMediaItemInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 64);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.index = _hidl_blob.getInt32(_hidl_offset + 0);
        this.id = _hidl_blob.getInt64(_hidl_offset + 8);
        this.name = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 16 + 0, false);
        this.sort = _hidl_blob.getString(_hidl_offset + 32);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 32 + 0, false);
        this.koName = _hidl_blob.getString(_hidl_offset + 48);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 48 + 0, false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(64);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<IapMediaItemInfo> _hidl_vec) {
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
        _hidl_blob.putInt32(0 + _hidl_offset, this.index);
        _hidl_blob.putInt64(8 + _hidl_offset, this.id);
        _hidl_blob.putString(16 + _hidl_offset, this.name);
        _hidl_blob.putString(32 + _hidl_offset, this.sort);
        _hidl_blob.putString(48 + _hidl_offset, this.koName);
    }
}
