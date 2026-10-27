package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IapMediaListInfo {
    public int size = 0;
    public int totalnum = 0;
    public int start = 0;
    public int end = 0;
    public int level = 0;
    public int subcontents = 0;
    public ArrayList<IapMediaItemInfo> elements = new ArrayList<>();
    public int categorytype = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != IapMediaListInfo.class) {
            return false;
        }
        IapMediaListInfo other = (IapMediaListInfo) otherObject;
        if (this.size == other.size && this.totalnum == other.totalnum && this.start == other.start && this.end == other.end && this.level == other.level && this.subcontents == other.subcontents && HidlSupport.deepEquals(this.elements, other.elements) && this.categorytype == other.categorytype) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.size))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.totalnum))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.start))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.end))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.level))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.subcontents))), Integer.valueOf(HidlSupport.deepHashCode(this.elements)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.categorytype))));
    }

    public final String toString() {
        return "{.size = " + this.size + ", .totalnum = " + this.totalnum + ", .start = " + this.start + ", .end = " + this.end + ", .level = " + this.level + ", .subcontents = " + this.subcontents + ", .elements = " + this.elements + ", .categorytype = " + this.categorytype + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(48L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<IapMediaListInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<IapMediaListInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 48, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            IapMediaListInfo _hidl_vec_element = new IapMediaListInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 48);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.size = _hidl_blob.getInt32(_hidl_offset + 0);
        this.totalnum = _hidl_blob.getInt32(_hidl_offset + 4);
        this.start = _hidl_blob.getInt32(_hidl_offset + 8);
        this.end = _hidl_blob.getInt32(_hidl_offset + 12);
        this.level = _hidl_blob.getInt32(_hidl_offset + 16);
        this.subcontents = _hidl_blob.getInt32(_hidl_offset + 20);
        int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 24 + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 64, _hidl_blob.handle(), _hidl_offset + 24 + 0, true);
        this.elements.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            IapMediaItemInfo _hidl_vec_element = new IapMediaItemInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 64);
            this.elements.add(_hidl_vec_element);
        }
        this.categorytype = _hidl_blob.getInt32(_hidl_offset + 40);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(48);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<IapMediaListInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 48);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 48);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(_hidl_offset + 0, this.size);
        _hidl_blob.putInt32(4 + _hidl_offset, this.totalnum);
        _hidl_blob.putInt32(_hidl_offset + 8, this.start);
        _hidl_blob.putInt32(_hidl_offset + 12, this.end);
        _hidl_blob.putInt32(16 + _hidl_offset, this.level);
        _hidl_blob.putInt32(20 + _hidl_offset, this.subcontents);
        int _hidl_vec_size = this.elements.size();
        _hidl_blob.putInt32(_hidl_offset + 24 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 24 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 64);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.elements.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 64);
        }
        _hidl_blob.putBlob(24 + _hidl_offset + 0, childBlob);
        _hidl_blob.putInt32(40 + _hidl_offset, this.categorytype);
    }
}
