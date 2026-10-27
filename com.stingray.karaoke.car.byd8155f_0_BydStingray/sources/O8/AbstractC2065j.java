package o8;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import n7.AbstractC2000o;
import n7.z;

/* JADX INFO: renamed from: o8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2065j {
    static {
        new C2058c("java.lang").c(C2061f.e("annotation"));
    }

    public static final C2057b a(String str) {
        C2058c c2058c = C2064i.f20580a;
        return new C2057b(C2064i.f20580a, C2061f.e(str));
    }

    public static final C2057b b(String str) {
        C2058c c2058c = C2064i.f20580a;
        return new C2057b(C2064i.c, C2061f.e(str));
    }

    public static final void c(LinkedHashMap linkedHashMap) {
        Set<Map.Entry> setEntrySet = linkedHashMap.entrySet();
        int iQ = z.q(AbstractC2000o.J(setEntrySet, 10));
        if (iQ < 16) {
            iQ = 16;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(iQ);
        for (Map.Entry entry : setEntrySet) {
            linkedHashMap2.put(entry.getValue(), entry.getKey());
        }
    }

    public static final C2057b d(C2061f c2061f) {
        C2058c c2058c = C2064i.f20580a;
        C2057b c2057b = C2064i.f20585h;
        return new C2057b(c2057b.g(), C2061f.e(c2061f.c().concat(c2057b.i().c())));
    }

    public static final C2057b e(String str) {
        C2058c c2058c = C2064i.f20580a;
        return new C2057b(C2064i.f20581b, C2061f.e(str));
    }

    public static final C2057b f(C2057b c2057b) {
        C2058c c2058c = C2064i.f20580a;
        return new C2057b(C2064i.f20580a, C2061f.e("U".concat(c2057b.i().c())));
    }
}
