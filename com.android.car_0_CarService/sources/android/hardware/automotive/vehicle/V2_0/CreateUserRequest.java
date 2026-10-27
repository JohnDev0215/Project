package android.hardware.automotive.vehicle.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class CreateUserRequest {
    public int requestId = 0;
    public UserInfo newUserInfo = new UserInfo();
    public String newUserName = new String();
    public UsersInfo usersInfo = new UsersInfo();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != CreateUserRequest.class) {
            return false;
        }
        CreateUserRequest other = (CreateUserRequest) otherObject;
        if (this.requestId == other.requestId && HidlSupport.deepEquals(this.newUserInfo, other.newUserInfo) && HidlSupport.deepEquals(this.newUserName, other.newUserName) && HidlSupport.deepEquals(this.usersInfo, other.usersInfo)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.requestId))), Integer.valueOf(HidlSupport.deepHashCode(this.newUserInfo)), Integer.valueOf(HidlSupport.deepHashCode(this.newUserName)), Integer.valueOf(HidlSupport.deepHashCode(this.usersInfo)));
    }

    public final String toString() {
        return "{.requestId = " + this.requestId + ", .newUserInfo = " + this.newUserInfo + ", .newUserName = " + this.newUserName + ", .usersInfo = " + this.usersInfo + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(64L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<CreateUserRequest> readVectorFromParcel(HwParcel parcel) {
        ArrayList<CreateUserRequest> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 64, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            CreateUserRequest _hidl_vec_element = new CreateUserRequest();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 64);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.requestId = _hidl_blob.getInt32(_hidl_offset + 0);
        this.newUserInfo.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 4);
        this.newUserName = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(r5.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 16 + 0, false);
        this.usersInfo.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 32);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(64);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<CreateUserRequest> _hidl_vec) {
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
        _hidl_blob.putInt32(0 + _hidl_offset, this.requestId);
        this.newUserInfo.writeEmbeddedToBlob(_hidl_blob, 4 + _hidl_offset);
        _hidl_blob.putString(16 + _hidl_offset, this.newUserName);
        this.usersInfo.writeEmbeddedToBlob(_hidl_blob, 32 + _hidl_offset);
    }
}
