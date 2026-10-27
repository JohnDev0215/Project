package c.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short f1294a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1295b = 0;

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f1294a = hwBlob.getInt16(0 + j);
        this.f1295b = hwBlob.getInt32(j + 4);
    }

    public final void b(HwParcel hwParcel) {
        a(hwParcel, hwParcel.readBuffer(8L), 0L);
    }

    public final void c(HwBlob hwBlob, long j) {
        hwBlob.putInt16(0 + j, this.f1294a);
        hwBlob.putInt32(j + 4, this.f1295b);
    }

    public final void d(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(8);
        c(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != g.class) {
            return false;
        }
        g gVar = (g) obj;
        return this.f1294a == gVar.f1294a && this.f1295b == gVar.f1295b;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.f1294a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f1295b))));
    }

    public final String toString() {
        return "{.did = " + ((int) this.f1294a) + ", .res = " + e.a(this.f1295b) + "}";
    }
}
