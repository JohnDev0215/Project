package c.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f1287a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public short f1288b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<Byte> f1289c = new ArrayList<>();

    public final void a(HwBlob hwBlob, long j) {
        hwBlob.putInt8(j + 0, this.f1287a);
        hwBlob.putInt16(2 + j, this.f1288b);
        int size = this.f1289c.size();
        long j2 = j + 8;
        hwBlob.putInt32(8 + j2, size);
        hwBlob.putBool(12 + j2, false);
        HwBlob hwBlob2 = new HwBlob(size * 1);
        for (int i = 0; i < size; i++) {
            hwBlob2.putInt8(i * 1, this.f1289c.get(i).byteValue());
        }
        hwBlob.putBlob(j2 + 0, hwBlob2);
    }

    public final void b(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(24);
        a(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != d.class) {
            return false;
        }
        d dVar = (d) obj;
        return this.f1287a == dVar.f1287a && this.f1288b == dVar.f1288b && HidlSupport.deepEquals(this.f1289c, dVar.f1289c);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f1287a))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.f1288b))), Integer.valueOf(HidlSupport.deepHashCode(this.f1289c)));
    }

    public final String toString() {
        return "{.controlType = " + ((int) this.f1287a) + ", .rid = " + ((int) this.f1288b) + ", .statusRecord = " + this.f1289c + "}";
    }
}
