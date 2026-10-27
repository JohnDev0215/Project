package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IapTrackInfo {
    public String Title = new String();
    public String Album = new String();
    public String Artist = new String();
    public String Genre = new String();
    public String Composer = new String();
    public boolean mIsChangePositionAvaible = false;
    public String mPid = new String();
    public int mMediaType = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != IapTrackInfo.class) {
            return false;
        }
        IapTrackInfo other = (IapTrackInfo) otherObject;
        if (HidlSupport.deepEquals(this.Title, other.Title) && HidlSupport.deepEquals(this.Album, other.Album) && HidlSupport.deepEquals(this.Artist, other.Artist) && HidlSupport.deepEquals(this.Genre, other.Genre) && HidlSupport.deepEquals(this.Composer, other.Composer) && this.mIsChangePositionAvaible == other.mIsChangePositionAvaible && HidlSupport.deepEquals(this.mPid, other.mPid) && this.mMediaType == other.mMediaType) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.Title)), Integer.valueOf(HidlSupport.deepHashCode(this.Album)), Integer.valueOf(HidlSupport.deepHashCode(this.Artist)), Integer.valueOf(HidlSupport.deepHashCode(this.Genre)), Integer.valueOf(HidlSupport.deepHashCode(this.Composer)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.mIsChangePositionAvaible))), Integer.valueOf(HidlSupport.deepHashCode(this.mPid)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.mMediaType))));
    }

    public final String toString() {
        return "{.Title = " + this.Title + ", .Album = " + this.Album + ", .Artist = " + this.Artist + ", .Genre = " + this.Genre + ", .Composer = " + this.Composer + ", .mIsChangePositionAvaible = " + this.mIsChangePositionAvaible + ", .mPid = " + this.mPid + ", .mMediaType = " + this.mMediaType + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(112L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<IapTrackInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<IapTrackInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 112, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            IapTrackInfo _hidl_vec_element = new IapTrackInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 112);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.Title = _hidl_blob.getString(_hidl_offset + 0);
        parcel.readEmbeddedBuffer(r4.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 0 + 0, false);
        this.Album = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 16 + 0, false);
        this.Artist = _hidl_blob.getString(_hidl_offset + 32);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 32 + 0, false);
        this.Genre = _hidl_blob.getString(_hidl_offset + 48);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 48 + 0, false);
        this.Composer = _hidl_blob.getString(_hidl_offset + 64);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 64 + 0, false);
        this.mIsChangePositionAvaible = _hidl_blob.getBool(_hidl_offset + 80);
        this.mPid = _hidl_blob.getString(_hidl_offset + 88);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 88 + 0, false);
        this.mMediaType = _hidl_blob.getInt32(_hidl_offset + 104);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(112);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<IapTrackInfo> _hidl_vec) {
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
        _hidl_blob.putString(0 + _hidl_offset, this.Title);
        _hidl_blob.putString(16 + _hidl_offset, this.Album);
        _hidl_blob.putString(32 + _hidl_offset, this.Artist);
        _hidl_blob.putString(48 + _hidl_offset, this.Genre);
        _hidl_blob.putString(64 + _hidl_offset, this.Composer);
        _hidl_blob.putBool(80 + _hidl_offset, this.mIsChangePositionAvaible);
        _hidl_blob.putString(88 + _hidl_offset, this.mPid);
        _hidl_blob.putInt32(104 + _hidl_offset, this.mMediaType);
    }
}
