package J5;

import A7.D;
import A7.m;
import F.u;
import Q8.o;
import a.AbstractC0904a;
import c9.C1046g;
import c9.G;
import c9.H;
import c9.w;
import c9.x;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import n7.AbstractC1999n;

/* JADX INFO: loaded from: classes.dex */
public final class a implements x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f3087a = AbstractC1999n.D("/api/login", "/api/profile", "/api/child-items", "/api/page-structure");

    @Override // c9.x
    public final H a(u uVar) {
        H hF = uVar.f((H0.b) uVar.f1964i);
        if (!hF.j()) {
            return hF;
        }
        String strB = ((w) hF.l.f2765m).b();
        Iterator it = f3087a.iterator();
        while (it.hasNext()) {
            if (o.z0(strB, (String) it.next(), false)) {
                return hF;
            }
        }
        TimeUnit timeUnit = TimeUnit.HOURS;
        m.f(timeUnit, "timeUnit");
        long seconds = timeUnit.toSeconds(1);
        C1046g c1046g = new C1046g(false, false, seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds, -1, false, false, false, -1, -1, false, false, false, null);
        G gK = hF.k();
        gK.f12507f.x("Pragma");
        gK.f12507f.x("Cache-Control");
        String str = "public, " + c1046g;
        m.f(str, "value");
        D d = gK.f12507f;
        d.getClass();
        AbstractC0904a.t("Cache-Control");
        AbstractC0904a.v(str, "Cache-Control");
        d.x("Cache-Control");
        d.i("Cache-Control", str);
        return gK.a();
    }
}
