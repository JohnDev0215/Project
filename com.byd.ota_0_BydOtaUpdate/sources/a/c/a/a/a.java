package a.c.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DebugInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f52a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f53b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f54c = 0;

    /* JADX INFO: renamed from: a.c.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DebugInfo.java */
    public static final class C0000a {
        public static final String a(int i2) {
            if (i2 == 0) {
                return "UNKNOWN";
            }
            if (i2 == 1) {
                return "IS_64BIT";
            }
            if (i2 == 2) {
                return "IS_32BIT";
            }
            return "0x" + Integer.toHexString(i2);
        }
    }

    public final void a(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.f52a);
        hwBlob.putInt64(8 + j, this.f53b);
        hwBlob.putInt32(j + 16, this.f54c);
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
        return this.f52a == aVar.f52a && this.f53b == aVar.f53b && this.f54c == aVar.f54c;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f52a))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.f53b))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f54c))));
    }

    public final String toString() {
        return "{.pid = " + this.f52a + ", .ptr = " + this.f53b + ", .arch = " + C0000a.a(this.f54c) + "}";
    }
}
