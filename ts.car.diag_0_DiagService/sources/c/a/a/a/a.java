package c.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f1278a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public short f1279b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<Byte> f1280c = new ArrayList<>();

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f1278a = hwBlob.getInt8(j + 0);
        this.f1279b = hwBlob.getInt16(j + 2);
        long j2 = j + 8;
        int int32 = hwBlob.getInt32(8 + j2);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 1, hwBlob.handle(), j2 + 0, true);
        this.f1280c.clear();
        for (int i = 0; i < int32; i++) {
            this.f1280c.add(Byte.valueOf(embeddedBuffer.getInt8(i * 1)));
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
        return this.f1278a == aVar.f1278a && this.f1279b == aVar.f1279b && HidlSupport.deepEquals(this.f1280c, aVar.f1280c);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f1278a))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.f1279b))), Integer.valueOf(HidlSupport.deepHashCode(this.f1280c)));
    }

    public final String toString() {
        return "{.mask = " + ((int) this.f1278a) + ", .dataLength = " + ((int) this.f1279b) + ", .data = " + this.f1280c + "}";
    }
}
