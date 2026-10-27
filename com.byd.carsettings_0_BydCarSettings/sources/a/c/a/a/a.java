package a.c.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DebugInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f53a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f54b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f55c = 0;

    /* JADX INFO: renamed from: a.c.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DebugInfo.java */
    public static final class C0001a {
        public static final String a(int i) {
            if (i == 0) {
                return "UNKNOWN";
            }
            if (i == 1) {
                return "IS_64BIT";
            }
            if (i == 2) {
                return "IS_32BIT";
            }
            return "0x" + Integer.toHexString(i);
        }
    }

    public final void a(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.f53a);
        hwBlob.putInt64(8 + j, this.f54b);
        hwBlob.putInt32(j + 16, this.f55c);
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
        return this.f53a == aVar.f53a && this.f54b == aVar.f54b && this.f55c == aVar.f55c;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f53a))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.f54b))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f55c))));
    }

    public final String toString() {
        return "{.pid = " + this.f53a + ", .ptr = " + this.f54b + ", .arch = " + C0001a.a(this.f55c) + "}";
    }
}
