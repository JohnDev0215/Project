package android.hardware.radio.V1_5;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class CellIdentityTdscdma {
    public android.hardware.radio.V1_2.CellIdentityTdscdma base = new android.hardware.radio.V1_2.CellIdentityTdscdma();
    public ArrayList<String> additionalPlmns = new ArrayList<>();
    public OptionalCsgInfo optionalCsgInfo = new OptionalCsgInfo();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != CellIdentityTdscdma.class) {
            return false;
        }
        CellIdentityTdscdma cellIdentityTdscdma = (CellIdentityTdscdma) obj;
        return HidlSupport.deepEquals(this.base, cellIdentityTdscdma.base) && HidlSupport.deepEquals(this.additionalPlmns, cellIdentityTdscdma.additionalPlmns) && HidlSupport.deepEquals(this.optionalCsgInfo, cellIdentityTdscdma.optionalCsgInfo);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.base)), Integer.valueOf(HidlSupport.deepHashCode(this.additionalPlmns)), Integer.valueOf(HidlSupport.deepHashCode(this.optionalCsgInfo)));
    }

    public final String toString() {
        return "{.base = " + this.base + ", .additionalPlmns = " + this.additionalPlmns + ", .optionalCsgInfo = " + this.optionalCsgInfo + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(144L), 0L);
    }

    public static final ArrayList<CellIdentityTdscdma> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<CellIdentityTdscdma> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 144, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            CellIdentityTdscdma cellIdentityTdscdma = new CellIdentityTdscdma();
            cellIdentityTdscdma.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 144);
            arrayList.add(cellIdentityTdscdma);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.base.readEmbeddedFromParcel(hwParcel, hwBlob, j + 0);
        long j2 = j + 88;
        int int32 = hwBlob.getInt32(8 + j2);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, hwBlob.handle(), j2 + 0, true);
        this.additionalPlmns.clear();
        for (int i = 0; i < int32; i++) {
            new String();
            String string = embeddedBuffer.getString(i * 16);
            hwParcel.readEmbeddedBuffer(string.getBytes().length + 1, embeddedBuffer.handle(), r1 + 0, false);
            this.additionalPlmns.add(string);
        }
        this.optionalCsgInfo.readEmbeddedFromParcel(hwParcel, hwBlob, j + 104);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(144);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<CellIdentityTdscdma> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 144);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 144);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        this.base.writeEmbeddedToBlob(hwBlob, j + 0);
        int size = this.additionalPlmns.size();
        long j2 = 88 + j;
        hwBlob.putInt32(8 + j2, size);
        hwBlob.putBool(12 + j2, false);
        HwBlob hwBlob2 = new HwBlob(size * 16);
        for (int i = 0; i < size; i++) {
            hwBlob2.putString(i * 16, this.additionalPlmns.get(i));
        }
        hwBlob.putBlob(j2 + 0, hwBlob2);
        this.optionalCsgInfo.writeEmbeddedToBlob(hwBlob, j + 104);
    }
}
