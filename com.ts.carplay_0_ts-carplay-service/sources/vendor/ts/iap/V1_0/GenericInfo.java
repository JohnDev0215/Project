package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class GenericInfo {
    public String ppUuid = new String();
    public int featureMask = 0;
    public int featureOption = 0;
    public int playbackHidMask = 0;
    public int nmeaSentenceMask = 0;
    public int transportType = 0;
    public String deviceNode = new String();
    public int iapAppMode = 0;
    public int ncmInterfaceNum = 0;
    public boolean isWirelessCarPlayActive = false;
    public String BtMacAddress = new String();
    public boolean playbackQueueListAvailable = false;
    public boolean isVehicleInformationComponentDeleted = false;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != GenericInfo.class) {
            return false;
        }
        GenericInfo other = (GenericInfo) otherObject;
        if (HidlSupport.deepEquals(this.ppUuid, other.ppUuid) && this.featureMask == other.featureMask && this.featureOption == other.featureOption && this.playbackHidMask == other.playbackHidMask && this.nmeaSentenceMask == other.nmeaSentenceMask && this.transportType == other.transportType && HidlSupport.deepEquals(this.deviceNode, other.deviceNode) && this.iapAppMode == other.iapAppMode && this.ncmInterfaceNum == other.ncmInterfaceNum && this.isWirelessCarPlayActive == other.isWirelessCarPlayActive && HidlSupport.deepEquals(this.BtMacAddress, other.BtMacAddress) && this.playbackQueueListAvailable == other.playbackQueueListAvailable && this.isVehicleInformationComponentDeleted == other.isVehicleInformationComponentDeleted) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.ppUuid)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.featureMask))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.featureOption))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.playbackHidMask))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.nmeaSentenceMask))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.transportType))), Integer.valueOf(HidlSupport.deepHashCode(this.deviceNode)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.iapAppMode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ncmInterfaceNum))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isWirelessCarPlayActive))), Integer.valueOf(HidlSupport.deepHashCode(this.BtMacAddress)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.playbackQueueListAvailable))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isVehicleInformationComponentDeleted))));
    }

    public final String toString() {
        return "{.ppUuid = " + this.ppUuid + ", .featureMask = " + this.featureMask + ", .featureOption = " + this.featureOption + ", .playbackHidMask = " + this.playbackHidMask + ", .nmeaSentenceMask = " + this.nmeaSentenceMask + ", .transportType = " + this.transportType + ", .deviceNode = " + this.deviceNode + ", .iapAppMode = " + this.iapAppMode + ", .ncmInterfaceNum = " + this.ncmInterfaceNum + ", .isWirelessCarPlayActive = " + this.isWirelessCarPlayActive + ", .BtMacAddress = " + this.BtMacAddress + ", .playbackQueueListAvailable = " + this.playbackQueueListAvailable + ", .isVehicleInformationComponentDeleted = " + this.isVehicleInformationComponentDeleted + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(96L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<GenericInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<GenericInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 96, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            GenericInfo _hidl_vec_element = new GenericInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 96);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.ppUuid = _hidl_blob.getString(_hidl_offset + 0);
        parcel.readEmbeddedBuffer(r4.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 0 + 0, false);
        this.featureMask = _hidl_blob.getInt32(_hidl_offset + 16);
        this.featureOption = _hidl_blob.getInt32(_hidl_offset + 20);
        this.playbackHidMask = _hidl_blob.getInt32(_hidl_offset + 24);
        this.nmeaSentenceMask = _hidl_blob.getInt32(_hidl_offset + 28);
        this.transportType = _hidl_blob.getInt32(_hidl_offset + 32);
        this.deviceNode = _hidl_blob.getString(_hidl_offset + 40);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 40 + 0, false);
        this.iapAppMode = _hidl_blob.getInt32(_hidl_offset + 56);
        this.ncmInterfaceNum = _hidl_blob.getInt32(_hidl_offset + 60);
        this.isWirelessCarPlayActive = _hidl_blob.getBool(_hidl_offset + 64);
        this.BtMacAddress = _hidl_blob.getString(_hidl_offset + 72);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 72 + 0, false);
        this.playbackQueueListAvailable = _hidl_blob.getBool(_hidl_offset + 88);
        this.isVehicleInformationComponentDeleted = _hidl_blob.getBool(_hidl_offset + 89);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(96);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<GenericInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 96);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 96);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.ppUuid);
        _hidl_blob.putInt32(16 + _hidl_offset, this.featureMask);
        _hidl_blob.putInt32(20 + _hidl_offset, this.featureOption);
        _hidl_blob.putInt32(24 + _hidl_offset, this.playbackHidMask);
        _hidl_blob.putInt32(28 + _hidl_offset, this.nmeaSentenceMask);
        _hidl_blob.putInt32(32 + _hidl_offset, this.transportType);
        _hidl_blob.putString(40 + _hidl_offset, this.deviceNode);
        _hidl_blob.putInt32(56 + _hidl_offset, this.iapAppMode);
        _hidl_blob.putInt32(60 + _hidl_offset, this.ncmInterfaceNum);
        _hidl_blob.putBool(64 + _hidl_offset, this.isWirelessCarPlayActive);
        _hidl_blob.putString(72 + _hidl_offset, this.BtMacAddress);
        _hidl_blob.putBool(88 + _hidl_offset, this.playbackQueueListAvailable);
        _hidl_blob.putBool(89 + _hidl_offset, this.isVehicleInformationComponentDeleted);
    }
}
