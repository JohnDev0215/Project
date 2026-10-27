package A9;

import A3.v;
import A7.m;
import c9.B;
import c9.C;
import h6.r;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashMap f474b = new LinkedHashMap();
    public static final LinkedHashMap c = new LinkedHashMap();
    public static final B d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C f475a;

    static {
        r.o(new v(1));
        d = new B();
    }

    public a(C c10) {
        m.f(c10, "client");
        this.f475a = c10;
    }

    public static final void a(a aVar, f fVar) {
        aVar.getClass();
        LinkedHashMap linkedHashMap = fVar.c;
        CharSequence charSequence = (CharSequence) linkedHashMap.get("KARAOKE-API-KEY");
        if (charSequence == null || charSequence.length() == 0) {
            LinkedHashMap linkedHashMap2 = f474b;
            if (linkedHashMap2.get("KARAOKE-API-KEY") != null) {
                LinkedHashMap linkedHashMap3 = c;
                if (linkedHashMap3.get("KARAOKE-API-KEY") != null) {
                    Object obj = linkedHashMap3.get("KARAOKE-API-KEY");
                    m.c(obj);
                    Object obj2 = linkedHashMap2.get("KARAOKE-API-KEY");
                    m.c(obj2);
                    linkedHashMap.put("KARAOKE-API-KEY", obj + " " + obj2);
                } else {
                    Object obj3 = linkedHashMap2.get("KARAOKE-API-KEY");
                    m.c(obj3);
                    linkedHashMap.put("KARAOKE-API-KEY", obj3);
                }
            }
        }
        CharSequence charSequence2 = (CharSequence) linkedHashMap.get("Authorization");
        if (charSequence2 != null) {
            charSequence2.length();
        }
    }
}
