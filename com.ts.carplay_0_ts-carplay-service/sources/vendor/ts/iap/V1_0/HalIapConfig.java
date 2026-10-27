package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HalIapConfig {
    public String deviceId = new String();
    public GenericInfo genericInfo = new GenericInfo();
    public AccessoryInfo accessoryInfo = new AccessoryInfo();
    public VehicleInfo vehicleInfo = new VehicleInfo();
    public VehicleStatus vehicleStatus = new VehicleStatus();
    public ArrayList<RouteGuidanceInfo> routeGuidanceInfoList = new ArrayList<>();
    public int eapInfoNum = 0;
    public ArrayList<EapInfo> eapInfoList = new ArrayList<>();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != HalIapConfig.class) {
            return false;
        }
        HalIapConfig other = (HalIapConfig) otherObject;
        if (HidlSupport.deepEquals(this.deviceId, other.deviceId) && HidlSupport.deepEquals(this.genericInfo, other.genericInfo) && HidlSupport.deepEquals(this.accessoryInfo, other.accessoryInfo) && HidlSupport.deepEquals(this.vehicleInfo, other.vehicleInfo) && HidlSupport.deepEquals(this.vehicleStatus, other.vehicleStatus) && HidlSupport.deepEquals(this.routeGuidanceInfoList, other.routeGuidanceInfoList) && this.eapInfoNum == other.eapInfoNum && HidlSupport.deepEquals(this.eapInfoList, other.eapInfoList)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.deviceId)), Integer.valueOf(HidlSupport.deepHashCode(this.genericInfo)), Integer.valueOf(HidlSupport.deepHashCode(this.accessoryInfo)), Integer.valueOf(HidlSupport.deepHashCode(this.vehicleInfo)), Integer.valueOf(HidlSupport.deepHashCode(this.vehicleStatus)), Integer.valueOf(HidlSupport.deepHashCode(this.routeGuidanceInfoList)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.eapInfoNum))), Integer.valueOf(HidlSupport.deepHashCode(this.eapInfoList)));
    }

    public final String toString() {
        return "{.deviceId = " + this.deviceId + ", .genericInfo = " + this.genericInfo + ", .accessoryInfo = " + this.accessoryInfo + ", .vehicleInfo = " + this.vehicleInfo + ", .vehicleStatus = " + this.vehicleStatus + ", .routeGuidanceInfoList = " + this.routeGuidanceInfoList + ", .eapInfoNum = " + this.eapInfoNum + ", .eapInfoList = " + this.eapInfoList + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(400L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HalIapConfig> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HalIapConfig> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 400, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalIapConfig _hidl_vec_element = new HalIapConfig();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 400);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.deviceId = _hidl_blob.getString(_hidl_offset + 0);
        parcel.readEmbeddedBuffer(r1.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 0 + 0, false);
        this.genericInfo.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 16);
        this.accessoryInfo.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 112);
        this.vehicleInfo.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 256);
        this.vehicleStatus.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 336);
        int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 360 + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 40, _hidl_blob.handle(), _hidl_offset + 360 + 0, true);
        this.routeGuidanceInfoList.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            RouteGuidanceInfo _hidl_vec_element = new RouteGuidanceInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 40);
            this.routeGuidanceInfoList.add(_hidl_vec_element);
        }
        this.eapInfoNum = _hidl_blob.getInt32(_hidl_offset + 376);
        int _hidl_vec_size2 = _hidl_blob.getInt32(_hidl_offset + 384 + 8);
        HwBlob childBlob2 = parcel.readEmbeddedBuffer(_hidl_vec_size2 * 32, _hidl_blob.handle(), _hidl_offset + 384 + 0, true);
        this.eapInfoList.clear();
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            EapInfo _hidl_vec_element2 = new EapInfo();
            _hidl_vec_element2.readEmbeddedFromParcel(parcel, childBlob2, _hidl_index_02 * 32);
            this.eapInfoList.add(_hidl_vec_element2);
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(400);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HalIapConfig> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 400);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 400);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(_hidl_offset + 0, this.deviceId);
        this.genericInfo.writeEmbeddedToBlob(_hidl_blob, _hidl_offset + 16);
        this.accessoryInfo.writeEmbeddedToBlob(_hidl_blob, _hidl_offset + 112);
        this.vehicleInfo.writeEmbeddedToBlob(_hidl_blob, _hidl_offset + 256);
        this.vehicleStatus.writeEmbeddedToBlob(_hidl_blob, _hidl_offset + 336);
        int _hidl_vec_size = this.routeGuidanceInfoList.size();
        _hidl_blob.putInt32(_hidl_offset + 360 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 360 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 40);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.routeGuidanceInfoList.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 40);
        }
        _hidl_blob.putBlob(_hidl_offset + 360 + 0, childBlob);
        _hidl_blob.putInt32(_hidl_offset + 376, this.eapInfoNum);
        int _hidl_vec_size2 = this.eapInfoList.size();
        _hidl_blob.putInt32(_hidl_offset + 384 + 8, _hidl_vec_size2);
        _hidl_blob.putBool(_hidl_offset + 384 + 12, false);
        HwBlob childBlob2 = new HwBlob(_hidl_vec_size2 * 32);
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            this.eapInfoList.get(_hidl_index_02).writeEmbeddedToBlob(childBlob2, _hidl_index_02 * 32);
        }
        _hidl_blob.putBlob(_hidl_offset + 384 + 0, childBlob2);
    }
}
