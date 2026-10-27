package c.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f1284a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public short f1285b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<Byte> f1286c = new ArrayList<>();

    public final void a(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.f1284a = hwBlob.getInt8(j + 0);
        this.f1285b = hwBlob.getInt16(j + 2);
        long j2 = j + 8;
        int int32 = hwBlob.getInt32(8 + j2);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 1, hwBlob.handle(), j2 + 0, true);
        this.f1286c.clear();
        for (int i = 0; i < int32; i++) {
            this.f1286c.add(Byte.valueOf(embeddedBuffer.getInt8(i * 1)));
        }
    }

    public final void b(HwParcel hwParcel) {
        a(hwParcel, hwParcel.readBuffer(24L), 0L);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != c.class) {
            return false;
        }
        c cVar = (c) obj;
        return this.f1284a == cVar.f1284a && this.f1285b == cVar.f1285b && HidlSupport.deepEquals(this.f1286c, cVar.f1286c);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.f1284a))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.f1285b))), Integer.valueOf(HidlSupport.deepHashCode(this.f1286c)));
    }

    public final String toString() {
        return "{.controlType = " + ((int) this.f1284a) + ", .rid = " + ((int) this.f1285b) + ", .optionRecord = " + this.f1286c + "}";
    }
}
