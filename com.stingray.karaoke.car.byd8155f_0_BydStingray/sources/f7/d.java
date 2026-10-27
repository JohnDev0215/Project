package f7;

import b7.l;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f14643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap f14644b;
    public static final b c;

    static {
        l lVar = new l();
        lVar.put("open", 0);
        lVar.put("close", 1);
        lVar.put("ping", 2);
        lVar.put("pong", 3);
        lVar.put("message", 4);
        lVar.put("upgrade", 5);
        lVar.put("noop", 6);
        f14643a = lVar;
        f14644b = new HashMap();
        for (Map.Entry entry : lVar.entrySet()) {
            f14644b.put(entry.getValue(), entry.getKey());
        }
        c = new b("parser error", "error");
    }

    public static b a(String str) {
        int numericValue;
        try {
            numericValue = Character.getNumericValue(str.charAt(0));
        } catch (IndexOutOfBoundsException unused) {
            numericValue = -1;
        }
        if (numericValue >= 0) {
            HashMap map = f14644b;
            if (numericValue < map.size()) {
                if (str.length() <= 1) {
                    return new b(null, (String) map.get(Integer.valueOf(numericValue)));
                }
                return new b(str.substring(1), (String) map.get(Integer.valueOf(numericValue)));
            }
        }
        return c;
    }

    public static void b(b bVar, c cVar) {
        Object obj = bVar.f14642b;
        if (obj instanceof byte[]) {
            cVar.b(obj);
            return;
        }
        String strValueOf = String.valueOf(f14643a.get(bVar.f14641a));
        Object obj2 = bVar.f14642b;
        cVar.b(strValueOf.concat(obj2 != null ? String.valueOf(obj2) : ""));
    }
}
