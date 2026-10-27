package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class AccessoryInfo {
    public String accessoryName = new String();
    public String accessoryModel = new String();
    public String manufacturer = new String();
    public String serialNumber = new String();
    public String currentLang = new String();
    public int supportedLangNum = 0;
    public ArrayList<String> supportedLangList = new ArrayList<>();
    public String fwVersion = new String();
    public String hwVersion = new String();
    public byte powerProvidingCapability = 0;
    public short vid = 0;
    public short pid = 0;
    public short availCurrent = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != AccessoryInfo.class) {
            return false;
        }
        AccessoryInfo other = (AccessoryInfo) otherObject;
        if (HidlSupport.deepEquals(this.accessoryName, other.accessoryName) && HidlSupport.deepEquals(this.accessoryModel, other.accessoryModel) && HidlSupport.deepEquals(this.manufacturer, other.manufacturer) && HidlSupport.deepEquals(this.serialNumber, other.serialNumber) && HidlSupport.deepEquals(this.currentLang, other.currentLang) && this.supportedLangNum == other.supportedLangNum && HidlSupport.deepEquals(this.supportedLangList, other.supportedLangList) && HidlSupport.deepEquals(this.fwVersion, other.fwVersion) && HidlSupport.deepEquals(this.hwVersion, other.hwVersion) && this.powerProvidingCapability == other.powerProvidingCapability && this.vid == other.vid && this.pid == other.pid && this.availCurrent == other.availCurrent) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.accessoryName)), Integer.valueOf(HidlSupport.deepHashCode(this.accessoryModel)), Integer.valueOf(HidlSupport.deepHashCode(this.manufacturer)), Integer.valueOf(HidlSupport.deepHashCode(this.serialNumber)), Integer.valueOf(HidlSupport.deepHashCode(this.currentLang)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.supportedLangNum))), Integer.valueOf(HidlSupport.deepHashCode(this.supportedLangList)), Integer.valueOf(HidlSupport.deepHashCode(this.fwVersion)), Integer.valueOf(HidlSupport.deepHashCode(this.hwVersion)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.powerProvidingCapability))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.vid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.pid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.availCurrent))));
    }

    public final String toString() {
        return "{.accessoryName = " + this.accessoryName + ", .accessoryModel = " + this.accessoryModel + ", .manufacturer = " + this.manufacturer + ", .serialNumber = " + this.serialNumber + ", .currentLang = " + this.currentLang + ", .supportedLangNum = " + this.supportedLangNum + ", .supportedLangList = " + this.supportedLangList + ", .fwVersion = " + this.fwVersion + ", .hwVersion = " + this.hwVersion + ", .powerProvidingCapability = " + ((int) this.powerProvidingCapability) + ", .vid = " + ((int) this.vid) + ", .pid = " + ((int) this.pid) + ", .availCurrent = " + ((int) this.availCurrent) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(144L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<AccessoryInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<AccessoryInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 144, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            AccessoryInfo _hidl_vec_element = new AccessoryInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 144);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.accessoryName = _hidl_blob.getString(_hidl_offset + 0);
        parcel.readEmbeddedBuffer(r4.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 0 + 0, false);
        this.accessoryModel = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 16 + 0, false);
        this.manufacturer = _hidl_blob.getString(_hidl_offset + 32);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 32 + 0, false);
        this.serialNumber = _hidl_blob.getString(_hidl_offset + 48);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 48 + 0, false);
        this.currentLang = _hidl_blob.getString(_hidl_offset + 64);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 64 + 0, false);
        this.supportedLangNum = _hidl_blob.getInt32(_hidl_offset + 80);
        int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 88 + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 16, _hidl_blob.handle(), _hidl_offset + 88 + 0, true);
        this.supportedLangList.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            new String();
            String _hidl_vec_element = childBlob.getString(_hidl_index_0 * 16);
            parcel.readEmbeddedBuffer(_hidl_vec_element.getBytes().length + 1, childBlob.handle(), (_hidl_index_0 * 16) + 0, false);
            this.supportedLangList.add(_hidl_vec_element);
        }
        this.fwVersion = _hidl_blob.getString(_hidl_offset + 104);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 104 + 0, false);
        this.hwVersion = _hidl_blob.getString(_hidl_offset + 120);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 120 + 0, false);
        this.powerProvidingCapability = _hidl_blob.getInt8(_hidl_offset + 136);
        this.vid = _hidl_blob.getInt16(_hidl_offset + 138);
        this.pid = _hidl_blob.getInt16(_hidl_offset + 140);
        this.availCurrent = _hidl_blob.getInt16(_hidl_offset + 142);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(144);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<AccessoryInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 144);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 144);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(_hidl_offset + 0, this.accessoryName);
        _hidl_blob.putString(16 + _hidl_offset, this.accessoryModel);
        _hidl_blob.putString(32 + _hidl_offset, this.manufacturer);
        _hidl_blob.putString(48 + _hidl_offset, this.serialNumber);
        _hidl_blob.putString(64 + _hidl_offset, this.currentLang);
        _hidl_blob.putInt32(80 + _hidl_offset, this.supportedLangNum);
        int _hidl_vec_size = this.supportedLangList.size();
        _hidl_blob.putInt32(_hidl_offset + 88 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 88 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 16);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            childBlob.putString(_hidl_index_0 * 16, this.supportedLangList.get(_hidl_index_0));
        }
        _hidl_blob.putBlob(88 + _hidl_offset + 0, childBlob);
        _hidl_blob.putString(104 + _hidl_offset, this.fwVersion);
        _hidl_blob.putString(120 + _hidl_offset, this.hwVersion);
        _hidl_blob.putInt8(136 + _hidl_offset, this.powerProvidingCapability);
        _hidl_blob.putInt16(138 + _hidl_offset, this.vid);
        _hidl_blob.putInt16(140 + _hidl_offset, this.pid);
        _hidl_blob.putInt16(142 + _hidl_offset, this.availCurrent);
    }
}
