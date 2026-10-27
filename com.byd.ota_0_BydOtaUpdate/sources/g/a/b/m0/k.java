package g.a.b.m0;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, Integer> f5156a;

    static {
        HashMap map = new HashMap();
        map.put("RIPEMD128", g.a.f.d.b(13004));
        map.put("RIPEMD160", g.a.f.d.b(12748));
        map.put("SHA-1", g.a.f.d.b(13260));
        map.put("SHA-224", g.a.f.d.b(14540));
        map.put("SHA-256", g.a.f.d.b(13516));
        map.put("SHA-384", g.a.f.d.b(14028));
        map.put("SHA-512", g.a.f.d.b(13772));
        map.put("SHA-512/224", g.a.f.d.b(14796));
        map.put("SHA-512/256", g.a.f.d.b(15052));
        map.put("Whirlpool", g.a.f.d.b(14284));
        f5156a = Collections.unmodifiableMap(map);
    }

    public static Integer a(g.a.b.n nVar) {
        return f5156a.get(nVar.getAlgorithmName());
    }
}
