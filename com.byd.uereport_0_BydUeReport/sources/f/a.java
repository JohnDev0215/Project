package f;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DebugInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4802a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4803b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4804c = 0;

    /* JADX INFO: renamed from: f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DebugInfo.java */
    public static final class C0035a {
        public static final String a(int i8) {
            if (i8 == 0) {
                return "UNKNOWN";
            }
            if (i8 == 1) {
                return "IS_64BIT";
            }
            if (i8 == 2) {
                return "IS_32BIT";
            }
            return "0x" + Integer.toHexString(i8);
        }
    }

    public final void a(HwBlob hwBlob, long j8) {
        hwBlob.putInt32(0 + j8, this.f4802a);
        hwBlob.putInt64(8 + j8, this.f4803b);
        hwBlob.putInt32(j8 + 16, this.f4804c);
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
        if (obj == null || obj.getClass() != a.class) {
            return false;
        }
        a aVar = (a) obj;
        return this.f4802a == aVar.f4802a && this.f4803b == aVar.f4803b && this.f4804c == aVar.f4804c;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f4802a))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.f4803b))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f4804c))));
    }

    public final String toString() {
        return "{.pid = " + this.f4802a + ", .ptr = " + this.f4803b + ", .arch = " + C0035a.a(this.f4804c) + "}";
    }
}
