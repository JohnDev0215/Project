package android.hardware.automotive.vehicle.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class UserIdentificationResponse {
    public int requestId = 0;
    public int numberAssociation = 0;
    public ArrayList<UserIdentificationAssociation> associations = new ArrayList<>();
    public String errorMessage = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != UserIdentificationResponse.class) {
            return false;
        }
        UserIdentificationResponse other = (UserIdentificationResponse) otherObject;
        if (this.requestId == other.requestId && this.numberAssociation == other.numberAssociation && HidlSupport.deepEquals(this.associations, other.associations) && HidlSupport.deepEquals(this.errorMessage, other.errorMessage)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.requestId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberAssociation))), Integer.valueOf(HidlSupport.deepHashCode(this.associations)), Integer.valueOf(HidlSupport.deepHashCode(this.errorMessage)));
    }

    public final String toString() {
        return "{.requestId = " + this.requestId + ", .numberAssociation = " + this.numberAssociation + ", .associations = " + this.associations + ", .errorMessage = " + this.errorMessage + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(40L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<UserIdentificationResponse> readVectorFromParcel(HwParcel parcel) {
        ArrayList<UserIdentificationResponse> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 40, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            UserIdentificationResponse _hidl_vec_element = new UserIdentificationResponse();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 40);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.requestId = _hidl_blob.getInt32(_hidl_offset + 0);
        this.numberAssociation = _hidl_blob.getInt32(_hidl_offset + 4);
        int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 8 + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 8, _hidl_blob.handle(), _hidl_offset + 8 + 0, true);
        this.associations.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            UserIdentificationAssociation _hidl_vec_element = new UserIdentificationAssociation();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 8);
            this.associations.add(_hidl_vec_element);
        }
        this.errorMessage = _hidl_blob.getString(_hidl_offset + 24);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), 0 + _hidl_offset + 24, false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(40);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<UserIdentificationResponse> _hidl_vec) {
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
        _hidl_blob.putInt32(_hidl_offset + 0, this.requestId);
        _hidl_blob.putInt32(4 + _hidl_offset, this.numberAssociation);
        int _hidl_vec_size = this.associations.size();
        _hidl_blob.putInt32(_hidl_offset + 8 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 8 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 8);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.associations.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 8);
        }
        _hidl_blob.putBlob(8 + _hidl_offset + 0, childBlob);
        _hidl_blob.putString(24 + _hidl_offset, this.errorMessage);
    }
}
