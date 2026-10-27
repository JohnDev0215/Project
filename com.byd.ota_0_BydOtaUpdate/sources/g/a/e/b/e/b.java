package g.a.e.b.e;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class b implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, b> f5611a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5612b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5613c;

    static {
        HashMap map = new HashMap();
        map.put(a("SHA-256", 32, 16, 67, 10), new b(16777217, "XMSS_SHA2-256_W16_H10"));
        map.put(a("SHA-256", 32, 16, 67, 16), new b(33554434, "XMSS_SHA2-256_W16_H16"));
        map.put(a("SHA-256", 32, 16, 67, 20), new b(50331651, "XMSS_SHA2-256_W16_H20"));
        map.put(a("SHA-512", 64, 16, 131, 10), new b(67108868, "XMSS_SHA2-512_W16_H10"));
        map.put(a("SHA-512", 64, 16, 131, 16), new b(83886085, "XMSS_SHA2-512_W16_H16"));
        map.put(a("SHA-512", 64, 16, 131, 20), new b(100663302, "XMSS_SHA2-512_W16_H20"));
        map.put(a("SHAKE128", 32, 16, 67, 10), new b(117440519, "XMSS_SHAKE128_W16_H10"));
        map.put(a("SHAKE128", 32, 16, 67, 16), new b(134217736, "XMSS_SHAKE128_W16_H16"));
        map.put(a("SHAKE128", 32, 16, 67, 20), new b(150994953, "XMSS_SHAKE128_W16_H20"));
        map.put(a("SHAKE256", 64, 16, 131, 10), new b(167772170, "XMSS_SHAKE256_W16_H10"));
        map.put(a("SHAKE256", 64, 16, 131, 16), new b(184549387, "XMSS_SHAKE256_W16_H16"));
        map.put(a("SHAKE256", 64, 16, 131, 20), new b(201326604, "XMSS_SHAKE256_W16_H20"));
        f5611a = Collections.unmodifiableMap(map);
    }

    public b(int i2, String str) {
        this.f5612b = i2;
        this.f5613c = str;
    }

    public static String a(String str, int i2, int i3, int i4, int i5) {
        Objects.requireNonNull(str, "algorithmName == null");
        return str + "-" + i2 + "-" + i3 + "-" + i4 + "-" + i5;
    }

    public static b b(String str, int i2, int i3, int i4, int i5) {
        Objects.requireNonNull(str, "algorithmName == null");
        return f5611a.get(a(str, i2, i3, i4, i5));
    }

    public String toString() {
        return this.f5613c;
    }
}
