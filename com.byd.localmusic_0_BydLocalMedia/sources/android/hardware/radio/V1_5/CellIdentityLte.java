package android.hardware.radio.V1_5;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class CellIdentityLte {
    public android.hardware.radio.V1_2.CellIdentityLte base = new android.hardware.radio.V1_2.CellIdentityLte();
    public ArrayList<String> additionalPlmns = new ArrayList<>();
    public OptionalCsgInfo optionalCsgInfo = new OptionalCsgInfo();
    public ArrayList<Integer> bands = new ArrayList<>();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != CellIdentityLte.class) {
            return false;
        }
        CellIdentityLte cellIdentityLte = (CellIdentityLte) obj;
        return HidlSupport.deepEquals(this.base, cellIdentityLte.base) && HidlSupport.deepEquals(this.additionalPlmns, cellIdentityLte.additionalPlmns) && HidlSupport.deepEquals(this.optionalCsgInfo, cellIdentityLte.optionalCsgInfo) && HidlSupport.deepEquals(this.bands, cellIdentityLte.bands);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.base)), Integer.valueOf(HidlSupport.deepHashCode(this.additionalPlmns)), Integer.valueOf(HidlSupport.deepHashCode(this.optionalCsgInfo)), Integer.valueOf(HidlSupport.deepHashCode(this.bands)));
    }

    public final String toString() {
        return "{.base = " + this.base + ", .additionalPlmns = " + this.additionalPlmns + ", .optionalCsgInfo = " + this.optionalCsgInfo + ", .bands = " + this.bands + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(160L), 0L);
    }

    public static final ArrayList<CellIdentityLte> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<CellIdentityLte> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 160, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            CellIdentityLte cellIdentityLte = new CellIdentityLte();
            cellIdentityLte.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 160);
            arrayList.add(cellIdentityLte);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.base.readEmbeddedFromParcel(hwParcel, hwBlob, j + 0);
        long j2 = j + 88;
        int int32 = hwBlob.getInt32(j2 + 8);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, hwBlob.handle(), j2 + 0, true);
        this.additionalPlmns.clear();
        int i = 0;
        while (i < int32) {
            new String();
            String string = embeddedBuffer.getString(i * 16);
            hwParcel.readEmbeddedBuffer(string.getBytes().length + 1, embeddedBuffer.handle(), r1 + 0, false);
            this.additionalPlmns.add(string);
            i++;
            embeddedBuffer = embeddedBuffer;
        }
        this.optionalCsgInfo.readEmbeddedFromParcel(hwParcel, hwBlob, j + 104);
        long j3 = j + 144;
        int int322 = hwBlob.getInt32(j3 + 8);
        HwBlob embeddedBuffer2 = hwParcel.readEmbeddedBuffer(int322 * 4, hwBlob.handle(), 0 + j3, true);
        this.bands.clear();
        for (int i2 = 0; i2 < int322; i2++) {
            this.bands.add(Integer.valueOf(embeddedBuffer2.getInt32(i2 * 4)));
        }
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(160);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<CellIdentityLte> arrayList) {
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
        this.base.writeEmbeddedToBlob(hwBlob, j + 0);
        int size = this.additionalPlmns.size();
        long j2 = j + 88;
        hwBlob.putInt32(j2 + 8, size);
        hwBlob.putBool(j2 + 12, false);
        HwBlob hwBlob2 = new HwBlob(size * 16);
        for (int i = 0; i < size; i++) {
            hwBlob2.putString(i * 16, this.additionalPlmns.get(i));
        }
        hwBlob.putBlob(j2 + 0, hwBlob2);
        this.optionalCsgInfo.writeEmbeddedToBlob(hwBlob, j + 104);
        int size2 = this.bands.size();
        long j3 = j + 144;
        hwBlob.putInt32(8 + j3, size2);
        hwBlob.putBool(12 + j3, false);
        HwBlob hwBlob3 = new HwBlob(size2 * 4);
        for (int i2 = 0; i2 < size2; i2++) {
            hwBlob3.putInt32(i2 * 4, this.bands.get(i2).intValue());
        }
        hwBlob.putBlob(j3 + 0, hwBlob3);
    }
}
