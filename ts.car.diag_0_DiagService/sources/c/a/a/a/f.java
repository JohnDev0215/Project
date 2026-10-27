package c.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short f1291a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public short f1292b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<Byte> f1293c = new ArrayList<>();

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f1291a = hwBlob.getInt16(j + 0);
        this.f1292b = hwBlob.getInt16(j + 2);
        long j2 = j + 8;
        int int32 = hwBlob.getInt32(8 + j2);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 1, hwBlob.handle(), j2 + 0, true);
        this.f1293c.clear();
        for (int i = 0; i < int32; i++) {
            this.f1293c.add(Byte.valueOf(embeddedBuffer.getInt8(i * 1)));
        }
    }

    public final void b(HwParcel hwParcel) {
        a(hwParcel, hwParcel.readBuffer(24L), 0L);
    }

    public final void c(HwBlob hwBlob, long j) {
        hwBlob.putInt16(j + 0, this.f1291a);
        hwBlob.putInt16(2 + j, this.f1292b);
        int size = this.f1293c.size();
        long j2 = j + 8;
        hwBlob.putInt32(8 + j2, size);
        hwBlob.putBool(12 + j2, false);
        HwBlob hwBlob2 = new HwBlob(size * 1);
        for (int i = 0; i < size; i++) {
            hwBlob2.putInt8(i * 1, this.f1293c.get(i).byteValue());
        }
        hwBlob.putBlob(j2 + 0, hwBlob2);
    }

    public final void d(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(24);
        c(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != f.class) {
            return false;
        }
        f fVar = (f) obj;
        return this.f1291a == fVar.f1291a && this.f1292b == fVar.f1292b && HidlSupport.deepEquals(this.f1293c, fVar.f1293c);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.f1291a))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.f1292b))), Integer.valueOf(HidlSupport.deepHashCode(this.f1293c)));
    }

    public final String toString() {
        return "{.did = " + ((int) this.f1291a) + ", .dataLength = " + ((int) this.f1292b) + ", .data = " + this.f1293c + "}";
    }
}
