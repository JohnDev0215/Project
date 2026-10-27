package g.a.c;

import g.a.a.m;
import g.a.b.j0.x;
import g.a.c.e.c;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static c a(String str) {
        x xVarA = g.a.a.d2.b.a(str);
        if (xVarA == null) {
            try {
                xVarA = g.a.a.d2.b.b(new m(str));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        if (xVarA == null) {
            return null;
        }
        return new c(str, xVarA.a(), xVarA.b(), xVarA.e(), xVarA.c(), xVarA.f());
    }
}
