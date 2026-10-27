package c.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short f1281a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1282b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public short f1283c = 0;
    public ArrayList<Byte> d = new ArrayList<>();

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f1281a = hwBlob.getInt16(j + 0);
        this.f1282b = hwBlob.getInt32(j + 4);
        this.f1283c = hwBlob.getInt16(j + 8);
        long j2 = j + 16;
        int int32 = hwBlob.getInt32(8 + j2);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 1, hwBlob.handle(), j2 + 0, true);
        this.d.clear();
        for (int i = 0; i < int32; i++) {
            this.d.add(Byte.valueOf(embeddedBuffer.getInt8(i * 1)));
        }
    }

    public final void b(HwParcel hwParcel) {
        a(hwParcel, hwParcel.readBuffer(32L), 0L);
    }

    public final void c(HwBlob hwBlob, long j) {
        hwBlob.putInt16(j + 0, this.f1281a);
        hwBlob.putInt32(4 + j, this.f1282b);
        hwBlob.putInt16(j + 8, this.f1283c);
        int size = this.d.size();
        long j2 = j + 16;
        hwBlob.putInt32(8 + j2, size);
        hwBlob.putBool(12 + j2, false);
        HwBlob hwBlob2 = new HwBlob(size * 1);
        for (int i = 0; i < size; i++) {
            hwBlob2.putInt8(i * 1, this.d.get(i).byteValue());
        }
        hwBlob.putBlob(j2 + 0, hwBlob2);
    }

    public final void d(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(32);
        c(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != b.class) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1281a == bVar.f1281a && this.f1282b == bVar.f1282b && this.f1283c == bVar.f1283c && HidlSupport.deepEquals(this.d, bVar.d);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.f1281a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f1282b))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.f1283c))), Integer.valueOf(HidlSupport.deepHashCode(this.d)));
    }

    public final String toString() {
        return "{.did = " + ((int) this.f1281a) + ", .res = " + e.a(this.f1282b) + ", .dataLength = " + ((int) this.f1283c) + ", .data = " + this.d + "}";
    }
}
