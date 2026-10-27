package g.a.e.b.e;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class h implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, h> f5637a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5638b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5639c;

    static {
        HashMap map = new HashMap();
        map.put(a("SHA-256", 32, 16, 67), new h(16777217, "WOTSP_SHA2-256_W16"));
        map.put(a("SHA-512", 64, 16, 131), new h(33554434, "WOTSP_SHA2-512_W16"));
        map.put(a("SHAKE128", 32, 16, 67), new h(50331651, "WOTSP_SHAKE128_W16"));
        map.put(a("SHAKE256", 64, 16, 131), new h(67108868, "WOTSP_SHAKE256_W16"));
        f5637a = Collections.unmodifiableMap(map);
    }

    public h(int i2, String str) {
        this.f5638b = i2;
        this.f5639c = str;
    }

    public static String a(String str, int i2, int i3, int i4) {
        Objects.requireNonNull(str, "algorithmName == null");
        return str + "-" + i2 + "-" + i3 + "-" + i4;
    }

    public static h b(String str, int i2, int i3, int i4) {
        Objects.requireNonNull(str, "algorithmName == null");
        return f5637a.get(a(str, i2, i3, i4));
    }

    public String toString() {
        return this.f5639c;
    }
}
