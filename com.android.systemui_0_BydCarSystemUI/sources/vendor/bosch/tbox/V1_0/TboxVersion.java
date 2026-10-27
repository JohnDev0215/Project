package vendor.bosch.tbox.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class TboxVersion {
    public String swVersion = new String();
    public String hwVersion = new String();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != TboxVersion.class) {
            return false;
        }
        TboxVersion tboxVersion = (TboxVersion) obj;
        return HidlSupport.deepEquals(this.swVersion, tboxVersion.swVersion) && HidlSupport.deepEquals(this.hwVersion, tboxVersion.hwVersion);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.swVersion)), Integer.valueOf(HidlSupport.deepHashCode(this.hwVersion)));
    }

    public final String toString() {
        return "{.swVersion = " + this.swVersion + ", .hwVersion = " + this.hwVersion + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(32L), 0L);
    }

    public static final ArrayList<TboxVersion> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<TboxVersion> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 32, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            TboxVersion tboxVersion = new TboxVersion();
            tboxVersion.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 32);
            arrayList.add(tboxVersion);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        long j2 = j + 0;
        this.swVersion = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        long j3 = j + 16;
        this.hwVersion = hwBlob.getString(j3);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(32);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<TboxVersion> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 32);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 32);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putString(0 + j, this.swVersion);
        hwBlob.putString(j + 16, this.hwVersion);
    }
}
