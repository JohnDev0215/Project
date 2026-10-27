package b.a.b.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f10a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f11b = 0;

    public static final ArrayList<g> b(HwParcel hwParcel) {
        ArrayList<g> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 8, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            g gVar = new g();
            gVar.a(hwParcel, embeddedBuffer, i * 8);
            arrayList.add(gVar);
        }
        return arrayList;
    }

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f10a = hwBlob.getInt32(0 + j);
        this.f11b = hwBlob.getInt32(j + 4);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != g.class) {
            return false;
        }
        g gVar = (g) obj;
        return this.f10a == gVar.f10a && this.f11b == gVar.f11b;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f10a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f11b))));
    }

    public final String toString() {
        return "{.module_name = " + h.a(this.f10a) + ", .level = " + f.a(this.f11b) + "}";
    }
}
