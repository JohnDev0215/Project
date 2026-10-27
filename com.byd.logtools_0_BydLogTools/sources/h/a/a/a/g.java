package h.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: LogLevelInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4972a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4973b = 0;

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

    public static final void d(HwParcel hwParcel, ArrayList<g> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 8);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).c(hwBlob2, i * 8);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f4972a = hwBlob.getInt32(0 + j);
        this.f4973b = hwBlob.getInt32(j + 4);
    }

    public final void c(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.f4972a);
        hwBlob.putInt32(j + 4, this.f4973b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != g.class) {
            return false;
        }
        g gVar = (g) obj;
        return this.f4972a == gVar.f4972a && this.f4973b == gVar.f4973b;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f4972a))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f4973b))));
    }

    public final String toString() {
        return "{.module_name = " + h.a(this.f4972a) + ", .level = " + f.a(this.f4973b) + "}";
    }
}
