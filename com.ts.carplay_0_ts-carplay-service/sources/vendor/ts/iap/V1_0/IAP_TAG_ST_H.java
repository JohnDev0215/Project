package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IAP_TAG_ST_H {
    public String Title = new String();
    public short TitleSize = 0;
    public String Album = new String();
    public short AlbumSize = 0;
    public String Artist = new String();
    public short ArtistSize = 0;
    public String Genre = new String();
    public short GenreSize = 0;
    public String Composer = new String();
    public short ComposerSize = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != IAP_TAG_ST_H.class) {
            return false;
        }
        IAP_TAG_ST_H other = (IAP_TAG_ST_H) otherObject;
        if (HidlSupport.deepEquals(this.Title, other.Title) && this.TitleSize == other.TitleSize && HidlSupport.deepEquals(this.Album, other.Album) && this.AlbumSize == other.AlbumSize && HidlSupport.deepEquals(this.Artist, other.Artist) && this.ArtistSize == other.ArtistSize && HidlSupport.deepEquals(this.Genre, other.Genre) && this.GenreSize == other.GenreSize && HidlSupport.deepEquals(this.Composer, other.Composer) && this.ComposerSize == other.ComposerSize) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.Title)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.TitleSize))), Integer.valueOf(HidlSupport.deepHashCode(this.Album)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.AlbumSize))), Integer.valueOf(HidlSupport.deepHashCode(this.Artist)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.ArtistSize))), Integer.valueOf(HidlSupport.deepHashCode(this.Genre)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.GenreSize))), Integer.valueOf(HidlSupport.deepHashCode(this.Composer)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.ComposerSize))));
    }

    public final String toString() {
        return "{.Title = " + this.Title + ", .TitleSize = " + ((int) this.TitleSize) + ", .Album = " + this.Album + ", .AlbumSize = " + ((int) this.AlbumSize) + ", .Artist = " + this.Artist + ", .ArtistSize = " + ((int) this.ArtistSize) + ", .Genre = " + this.Genre + ", .GenreSize = " + ((int) this.GenreSize) + ", .Composer = " + this.Composer + ", .ComposerSize = " + ((int) this.ComposerSize) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(120L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<IAP_TAG_ST_H> readVectorFromParcel(HwParcel parcel) {
        ArrayList<IAP_TAG_ST_H> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 120, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            IAP_TAG_ST_H _hidl_vec_element = new IAP_TAG_ST_H();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 120);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.Title = _hidl_blob.getString(_hidl_offset + 0);
        parcel.readEmbeddedBuffer(r4.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 0 + 0, false);
        this.TitleSize = _hidl_blob.getInt16(_hidl_offset + 16);
        this.Album = _hidl_blob.getString(_hidl_offset + 24);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 24 + 0, false);
        this.AlbumSize = _hidl_blob.getInt16(_hidl_offset + 40);
        this.Artist = _hidl_blob.getString(_hidl_offset + 48);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 48 + 0, false);
        this.ArtistSize = _hidl_blob.getInt16(_hidl_offset + 64);
        this.Genre = _hidl_blob.getString(_hidl_offset + 72);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 72 + 0, false);
        this.GenreSize = _hidl_blob.getInt16(_hidl_offset + 88);
        this.Composer = _hidl_blob.getString(_hidl_offset + 96);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 96 + 0, false);
        this.ComposerSize = _hidl_blob.getInt16(_hidl_offset + 112);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(120);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<IAP_TAG_ST_H> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 120);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 120);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.Title);
        _hidl_blob.putInt16(16 + _hidl_offset, this.TitleSize);
        _hidl_blob.putString(24 + _hidl_offset, this.Album);
        _hidl_blob.putInt16(40 + _hidl_offset, this.AlbumSize);
        _hidl_blob.putString(48 + _hidl_offset, this.Artist);
        _hidl_blob.putInt16(64 + _hidl_offset, this.ArtistSize);
        _hidl_blob.putString(72 + _hidl_offset, this.Genre);
        _hidl_blob.putInt16(88 + _hidl_offset, this.GenreSize);
        _hidl_blob.putString(96 + _hidl_offset, this.Composer);
        _hidl_blob.putInt16(112 + _hidl_offset, this.ComposerSize);
    }
}
