package b.a.b.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6b = 0;

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f5a = hwBlob.getInt32(0 + j);
        this.f6b = hwBlob.getInt32(j + 4);
    }

    public final void b(HwParcel hwParcel) {
        a(hwParcel, hwParcel.readBuffer(8L), 0L);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != b.class) {
            return false;
        }
        b bVar = (b) obj;
        return this.f5a == bVar.f5a && this.f6b == bVar.f6b;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f5a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f6b))));
    }

    public final String toString() {
        return "{.domain_name = " + a.a(this.f5a) + ", .stat = " + c.a(this.f6b) + "}";
    }
}
