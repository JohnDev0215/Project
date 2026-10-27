package android.hardware.automotive.vehicle.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class InitialUserInfoResponse {
    public int requestId = 0;
    public int action = 0;
    public UserInfo userToSwitchOrCreate = new UserInfo();
    public String userLocales = new String();
    public String userNameToCreate = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != InitialUserInfoResponse.class) {
            return false;
        }
        InitialUserInfoResponse other = (InitialUserInfoResponse) otherObject;
        if (this.requestId == other.requestId && this.action == other.action && HidlSupport.deepEquals(this.userToSwitchOrCreate, other.userToSwitchOrCreate) && HidlSupport.deepEquals(this.userLocales, other.userLocales) && HidlSupport.deepEquals(this.userNameToCreate, other.userNameToCreate)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.requestId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.action))), Integer.valueOf(HidlSupport.deepHashCode(this.userToSwitchOrCreate)), Integer.valueOf(HidlSupport.deepHashCode(this.userLocales)), Integer.valueOf(HidlSupport.deepHashCode(this.userNameToCreate)));
    }

    public final String toString() {
        return "{.requestId = " + this.requestId + ", .action = " + InitialUserInfoResponseAction.toString(this.action) + ", .userToSwitchOrCreate = " + this.userToSwitchOrCreate + ", .userLocales = " + this.userLocales + ", .userNameToCreate = " + this.userNameToCreate + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(48L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<InitialUserInfoResponse> readVectorFromParcel(HwParcel parcel) {
        ArrayList<InitialUserInfoResponse> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 48, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            InitialUserInfoResponse _hidl_vec_element = new InitialUserInfoResponse();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 48);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.requestId = _hidl_blob.getInt32(_hidl_offset + 0);
        this.action = _hidl_blob.getInt32(_hidl_offset + 4);
        this.userToSwitchOrCreate.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 8);
        this.userLocales = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 16 + 0, false);
        this.userNameToCreate = _hidl_blob.getString(_hidl_offset + 32);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 32 + 0, false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(48);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<InitialUserInfoResponse> _hidl_vec) {
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
        _hidl_blob.putInt32(0 + _hidl_offset, this.requestId);
        _hidl_blob.putInt32(4 + _hidl_offset, this.action);
        this.userToSwitchOrCreate.writeEmbeddedToBlob(_hidl_blob, 8 + _hidl_offset);
        _hidl_blob.putString(16 + _hidl_offset, this.userLocales);
        _hidl_blob.putString(32 + _hidl_offset, this.userNameToCreate);
    }
}
