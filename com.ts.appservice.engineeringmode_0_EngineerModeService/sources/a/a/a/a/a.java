package a.a.a.a;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.HashMap;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f0a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1b = 0;
    public int c = 0;

    /* JADX INFO: renamed from: a.a.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static final class C0000a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final HashMap<Integer, String> f2a;

        static {
            HashMap<Integer, String> map = new HashMap<>();
            f2a = map;
            map.put(0, "UNKNOWN");
            f2a.put(1, "IS_64BIT");
            f2a.put(2, "IS_32BIT");
        }

        public static final String a(int i) {
            if (f2a.containsKey(Integer.valueOf(i))) {
                return f2a.get(Integer.valueOf(i));
            }
            return "0x" + Integer.toHexString(i);
        }
    }

    public final void a(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.f0a);
        hwBlob.putInt64(8 + j, this.f1b);
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
        if (obj == null || obj.getClass() != a.class) {
            return false;
        }
        a aVar = (a) obj;
        return this.f0a == aVar.f0a && this.f1b == aVar.f1b && this.c == aVar.c;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f0a))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.f1b))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.c))));
    }

    public final String toString() {
        return "{.pid = " + this.f0a + ", .ptr = " + this.f1b + ", .arch = " + C0000a.a(this.c) + "}";
    }
}
