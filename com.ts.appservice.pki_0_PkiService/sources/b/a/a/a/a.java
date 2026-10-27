package b.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<Byte> f7b = new ArrayList<>();

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f6a = hwBlob.getInt32(j + 0);
        long j2 = j + 8;
        int int32 = hwBlob.getInt32(8 + j2);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 1, hwBlob.handle(), j2 + 0, true);
        this.f7b.clear();
        for (int i = 0; i < int32; i++) {
            this.f7b.add(Byte.valueOf(embeddedBuffer.getInt8(i * 1)));
        }
    }

    public final void b(HwParcel hwParcel) {
        a(hwParcel, hwParcel.readBuffer(24L), 0L);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != a.class) {
            return false;
        }
        a aVar = (a) obj;
        return this.f6a == aVar.f6a && HidlSupport.deepEquals(this.f7b, aVar.f7b);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f6a))), Integer.valueOf(HidlSupport.deepHashCode(this.f7b)));
    }

    public final String toString() {
        return "{.ret = " + this.f6a + ", .extra_data = " + this.f7b + "}";
    }
}
