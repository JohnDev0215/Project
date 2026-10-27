package android.hardware.automotive.vehicle.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class UserIdentificationSetRequest {
    public int requestId = 0;
    public UserInfo userInfo = new UserInfo();
    public int numberAssociations = 0;
    public ArrayList<UserIdentificationSetAssociation> associations = new ArrayList<>();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != UserIdentificationSetRequest.class) {
            return false;
        }
        UserIdentificationSetRequest other = (UserIdentificationSetRequest) otherObject;
        if (this.requestId == other.requestId && HidlSupport.deepEquals(this.userInfo, other.userInfo) && this.numberAssociations == other.numberAssociations && HidlSupport.deepEquals(this.associations, other.associations)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.requestId))), Integer.valueOf(HidlSupport.deepHashCode(this.userInfo)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberAssociations))), Integer.valueOf(HidlSupport.deepHashCode(this.associations)));
    }

    public final String toString() {
        return "{.requestId = " + this.requestId + ", .userInfo = " + this.userInfo + ", .numberAssociations = " + this.numberAssociations + ", .associations = " + this.associations + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(32L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<UserIdentificationSetRequest> readVectorFromParcel(HwParcel parcel) {
        ArrayList<UserIdentificationSetRequest> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            UserIdentificationSetRequest _hidl_vec_element = new UserIdentificationSetRequest();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 32);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.requestId = _hidl_blob.getInt32(_hidl_offset + 0);
        this.userInfo.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 4);
        this.numberAssociations = _hidl_blob.getInt32(_hidl_offset + 12);
        int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 16 + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 8, _hidl_blob.handle(), _hidl_offset + 16 + 0, true);
        this.associations.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            UserIdentificationSetAssociation _hidl_vec_element = new UserIdentificationSetAssociation();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 8);
            this.associations.add(_hidl_vec_element);
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(32);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<UserIdentificationSetRequest> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 32);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(_hidl_offset + 0, this.requestId);
        this.userInfo.writeEmbeddedToBlob(_hidl_blob, 4 + _hidl_offset);
        _hidl_blob.putInt32(_hidl_offset + 12, this.numberAssociations);
        int _hidl_vec_size = this.associations.size();
        _hidl_blob.putInt32(_hidl_offset + 16 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 16 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 8);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.associations.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 8);
        }
        _hidl_blob.putBlob(16 + _hidl_offset + 0, childBlob);
    }
}
