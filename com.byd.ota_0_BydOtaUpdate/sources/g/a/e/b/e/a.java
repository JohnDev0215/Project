package g.a.e.b.e;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class a implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, a> f5608a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5609b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5610c;

    static {
        HashMap map = new HashMap();
        map.put(a("SHA-256", 32, 16, 67, 20, 2), new a(16777217, "XMSSMT_SHA2-256_W16_H20_D2"));
        map.put(a("SHA-256", 32, 16, 67, 20, 4), new a(16777217, "XMSSMT_SHA2-256_W16_H20_D4"));
        map.put(a("SHA-256", 32, 16, 67, 40, 2), new a(16777217, "XMSSMT_SHA2-256_W16_H40_D2"));
        map.put(a("SHA-256", 32, 16, 67, 40, 2), new a(16777217, "XMSSMT_SHA2-256_W16_H40_D4"));
        map.put(a("SHA-256", 32, 16, 67, 40, 4), new a(16777217, "XMSSMT_SHA2-256_W16_H40_D8"));
        map.put(a("SHA-256", 32, 16, 67, 60, 8), new a(16777217, "XMSSMT_SHA2-256_W16_H60_D3"));
        map.put(a("SHA-256", 32, 16, 67, 60, 6), new a(16777217, "XMSSMT_SHA2-256_W16_H60_D6"));
        map.put(a("SHA-256", 32, 16, 67, 60, 12), new a(16777217, "XMSSMT_SHA2-256_W16_H60_D12"));
        map.put(a("SHA2-512", 64, 16, 131, 20, 2), new a(16777217, "XMSSMT_SHA2-512_W16_H20_D2"));
        map.put(a("SHA2-512", 64, 16, 131, 20, 4), new a(16777217, "XMSSMT_SHA2-512_W16_H20_D4"));
        map.put(a("SHA2-512", 64, 16, 131, 40, 2), new a(16777217, "XMSSMT_SHA2-512_W16_H40_D2"));
        map.put(a("SHA2-512", 64, 16, 131, 40, 4), new a(16777217, "XMSSMT_SHA2-512_W16_H40_D4"));
        map.put(a("SHA2-512", 64, 16, 131, 40, 8), new a(16777217, "XMSSMT_SHA2-512_W16_H40_D8"));
        map.put(a("SHA2-512", 64, 16, 131, 60, 3), new a(16777217, "XMSSMT_SHA2-512_W16_H60_D3"));
        map.put(a("SHA2-512", 64, 16, 131, 60, 6), new a(16777217, "XMSSMT_SHA2-512_W16_H60_D6"));
        map.put(a("SHA2-512", 64, 16, 131, 60, 12), new a(16777217, "XMSSMT_SHA2-512_W16_H60_D12"));
        map.put(a("SHAKE128", 32, 16, 67, 20, 2), new a(16777217, "XMSSMT_SHAKE128_W16_H20_D2"));
        map.put(a("SHAKE128", 32, 16, 67, 20, 4), new a(16777217, "XMSSMT_SHAKE128_W16_H20_D4"));
        map.put(a("SHAKE128", 32, 16, 67, 40, 2), new a(16777217, "XMSSMT_SHAKE128_W16_H40_D2"));
        map.put(a("SHAKE128", 32, 16, 67, 40, 4), new a(16777217, "XMSSMT_SHAKE128_W16_H40_D4"));
        map.put(a("SHAKE128", 32, 16, 67, 40, 8), new a(16777217, "XMSSMT_SHAKE128_W16_H40_D8"));
        map.put(a("SHAKE128", 32, 16, 67, 60, 3), new a(16777217, "XMSSMT_SHAKE128_W16_H60_D3"));
        map.put(a("SHAKE128", 32, 16, 67, 60, 6), new a(16777217, "XMSSMT_SHAKE128_W16_H60_D6"));
        map.put(a("SHAKE128", 32, 16, 67, 60, 12), new a(16777217, "XMSSMT_SHAKE128_W16_H60_D12"));
        map.put(a("SHAKE256", 64, 16, 131, 20, 2), new a(16777217, "XMSSMT_SHAKE256_W16_H20_D2"));
        map.put(a("SHAKE256", 64, 16, 131, 20, 4), new a(16777217, "XMSSMT_SHAKE256_W16_H20_D4"));
        map.put(a("SHAKE256", 64, 16, 131, 40, 2), new a(16777217, "XMSSMT_SHAKE256_W16_H40_D2"));
        map.put(a("SHAKE256", 64, 16, 131, 40, 4), new a(16777217, "XMSSMT_SHAKE256_W16_H40_D4"));
        map.put(a("SHAKE256", 64, 16, 131, 40, 8), new a(16777217, "XMSSMT_SHAKE256_W16_H40_D8"));
        map.put(a("SHAKE256", 64, 16, 131, 60, 3), new a(16777217, "XMSSMT_SHAKE256_W16_H60_D3"));
        map.put(a("SHAKE256", 64, 16, 131, 60, 6), new a(16777217, "XMSSMT_SHAKE256_W16_H60_D6"));
        map.put(a("SHAKE256", 64, 16, 131, 60, 12), new a(16777217, "XMSSMT_SHAKE256_W16_H60_D12"));
        f5608a = Collections.unmodifiableMap(map);
    }

    public a(int i2, String str) {
        this.f5609b = i2;
        this.f5610c = str;
    }

    public static String a(String str, int i2, int i3, int i4, int i5, int i6) {
        Objects.requireNonNull(str, "algorithmName == null");
        return str + "-" + i2 + "-" + i3 + "-" + i4 + "-" + i5 + "-" + i6;
    }

    public static a b(String str, int i2, int i3, int i4, int i5, int i6) {
        Objects.requireNonNull(str, "algorithmName == null");
        return f5608a.get(a(str, i2, i3, i4, i5, i6));
    }

    public String toString() {
        return this.f5610c;
    }
}
