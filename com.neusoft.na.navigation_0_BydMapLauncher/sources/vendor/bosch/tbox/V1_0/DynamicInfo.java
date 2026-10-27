package vendor.bosch.tbox.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class DynamicInfo {
    public byte state = 0;
    public int nettype = 0;
    public byte signal_strength = 0;
    public String external_imsi = new String();
    public String external_iccid = new String();
    public String external_simnumber = new String();
    public String imei = new String();
    public String internal_imsi = new String();
    public String internal_iccid = new String();
    public String internal_simnumber = new String();
    public byte apn2_status = 0;
    public byte updown_status = 0;
    public String mcc_mcn = new String();
    public byte real_apn2_status = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != DynamicInfo.class) {
            return false;
        }
        DynamicInfo dynamicInfo = (DynamicInfo) obj;
        return this.state == dynamicInfo.state && this.nettype == dynamicInfo.nettype && this.signal_strength == dynamicInfo.signal_strength && HidlSupport.deepEquals(this.external_imsi, dynamicInfo.external_imsi) && HidlSupport.deepEquals(this.external_iccid, dynamicInfo.external_iccid) && HidlSupport.deepEquals(this.external_simnumber, dynamicInfo.external_simnumber) && HidlSupport.deepEquals(this.imei, dynamicInfo.imei) && HidlSupport.deepEquals(this.internal_imsi, dynamicInfo.internal_imsi) && HidlSupport.deepEquals(this.internal_iccid, dynamicInfo.internal_iccid) && HidlSupport.deepEquals(this.internal_simnumber, dynamicInfo.internal_simnumber) && this.apn2_status == dynamicInfo.apn2_status && this.updown_status == dynamicInfo.updown_status && HidlSupport.deepEquals(this.mcc_mcn, dynamicInfo.mcc_mcn) && this.real_apn2_status == dynamicInfo.real_apn2_status;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.state))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.nettype))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.signal_strength))), Integer.valueOf(HidlSupport.deepHashCode(this.external_imsi)), Integer.valueOf(HidlSupport.deepHashCode(this.external_iccid)), Integer.valueOf(HidlSupport.deepHashCode(this.external_simnumber)), Integer.valueOf(HidlSupport.deepHashCode(this.imei)), Integer.valueOf(HidlSupport.deepHashCode(this.internal_imsi)), Integer.valueOf(HidlSupport.deepHashCode(this.internal_iccid)), Integer.valueOf(HidlSupport.deepHashCode(this.internal_simnumber)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.apn2_status))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.updown_status))), Integer.valueOf(HidlSupport.deepHashCode(this.mcc_mcn)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.real_apn2_status))));
    }

    public final String toString() {
        return "{.state = " + NetworkState.toString(this.state) + ", .nettype = " + NetworkType.toString(this.nettype) + ", .signal_strength = " + ((int) this.signal_strength) + ", .external_imsi = " + this.external_imsi + ", .external_iccid = " + this.external_iccid + ", .external_simnumber = " + this.external_simnumber + ", .imei = " + this.imei + ", .internal_imsi = " + this.internal_imsi + ", .internal_iccid = " + this.internal_iccid + ", .internal_simnumber = " + this.internal_simnumber + ", .apn2_status = " + Apn2Status.toString(this.apn2_status) + ", .updown_status = " + UpDownStatus.toString(this.updown_status) + ", .mcc_mcn = " + this.mcc_mcn + ", .real_apn2_status = " + Apn2Status.toString(this.real_apn2_status) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(160L), 0L);
    }

    public static final ArrayList<DynamicInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<DynamicInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 160, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            DynamicInfo dynamicInfo = new DynamicInfo();
            dynamicInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 160);
            arrayList.add(dynamicInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.state = hwBlob.getInt8(j + 0);
        this.nettype = hwBlob.getInt32(j + 4);
        this.signal_strength = hwBlob.getInt8(j + 8);
        long j2 = j + 16;
        this.external_imsi = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        long j3 = j + 32;
        this.external_iccid = hwBlob.getString(j3);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
        long j4 = j + 48;
        this.external_simnumber = hwBlob.getString(j4);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j4 + 0, false);
        long j5 = j + 64;
        this.imei = hwBlob.getString(j5);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j5 + 0, false);
        long j6 = j + 80;
        this.internal_imsi = hwBlob.getString(j6);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j6 + 0, false);
        long j7 = j + 96;
        this.internal_iccid = hwBlob.getString(j7);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j7 + 0, false);
        long j8 = j + 112;
        this.internal_simnumber = hwBlob.getString(j8);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j8 + 0, false);
        this.apn2_status = hwBlob.getInt8(j + 128);
        this.updown_status = hwBlob.getInt8(j + 129);
        long j9 = j + 136;
        this.mcc_mcn = hwBlob.getString(j9);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j9 + 0, false);
        this.real_apn2_status = hwBlob.getInt8(j + 152);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(160);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<DynamicInfo> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 160);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 160);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt8(0 + j, this.state);
        hwBlob.putInt32(4 + j, this.nettype);
        hwBlob.putInt8(8 + j, this.signal_strength);
        hwBlob.putString(16 + j, this.external_imsi);
        hwBlob.putString(32 + j, this.external_iccid);
        hwBlob.putString(48 + j, this.external_simnumber);
        hwBlob.putString(64 + j, this.imei);
        hwBlob.putString(80 + j, this.internal_imsi);
        hwBlob.putString(96 + j, this.internal_iccid);
        hwBlob.putString(112 + j, this.internal_simnumber);
        hwBlob.putInt8(128 + j, this.apn2_status);
        hwBlob.putInt8(129 + j, this.updown_status);
        hwBlob.putString(136 + j, this.mcc_mcn);
        hwBlob.putInt8(j + 152, this.real_apn2_status);
    }
}
