package defpackage;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: compiled from: DebugInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public int a = 0;
    public long b = 0;
    public int c = 0;

    /* JADX INFO: compiled from: DebugInfo.java */
    public static final class a {
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
        hwBlob.putInt32(0 + j, this.a);
        hwBlob.putInt64(8 + j, this.b);
        hwBlob.putInt32(j + 16, this.c);
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
        if (obj == null || obj.getClass() != c0.class) {
            return false;
        }
        c0 c0Var = (c0) obj;
        return this.a == c0Var.a && this.b == c0Var.b && this.c == c0Var.c;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.a))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.b))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.c))));
    }

    public final String toString() {
        return "{.pid = " + this.a + ", .ptr = " + this.b + ", .arch = " + a.a(this.c) + "}";
    }
}
