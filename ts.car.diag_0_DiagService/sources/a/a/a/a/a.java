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
    public int f1a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f2b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3c = 0;

    /* JADX INFO: renamed from: a.a.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static final class C0000a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final HashMap<Integer, String> f4a;

        static {
            HashMap<Integer, String> map = new HashMap<>();
            f4a = map;
            map.put(0, "UNKNOWN");
            f4a.put(1, "IS_64BIT");
            f4a.put(2, "IS_32BIT");
        }

        public static final String a(int i) {
            if (f4a.containsKey(Integer.valueOf(i))) {
                return f4a.get(Integer.valueOf(i));
            }
            return "0x" + Integer.toHexString(i);
        }
    }

    public final void a(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.f1a);
        hwBlob.putInt64(8 + j, this.f2b);
        hwBlob.putInt32(j + 16, this.f3c);
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
        return this.f1a == aVar.f1a && this.f2b == aVar.f2b && this.f3c == aVar.f3c;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f1a))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.f2b))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.f3c))));
    }

    public final String toString() {
        return "{.pid = " + this.f1a + ", .ptr = " + this.f2b + ", .arch = " + C0000a.a(this.f3c) + "}";
    }
}
