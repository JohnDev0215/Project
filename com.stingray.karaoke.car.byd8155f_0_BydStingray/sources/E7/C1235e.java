package e7;

import A7.k;
import B8.t;
import c9.InterfaceC1047h;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import l7.AbstractC1782a;
import n7.z;

/* JADX INFO: renamed from: e7.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1235e extends AbstractC1232b {
    public static final Logger B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final boolean f14479C;

    static {
        Logger logger = Logger.getLogger(C1235e.class.getName());
        B = logger;
        f14479C = logger.isLoggable(Level.FINE);
    }

    public final C1234d e1(t tVar) {
        if (tVar == null) {
            tVar = new t(12);
        }
        HashMap map = this.f13546p;
        if (map == null) {
            map = new HashMap();
        }
        String str = this.f13547q ? "https" : "http";
        if (this.f13548r) {
            map.put(this.f13552v, AbstractC1782a.b());
        }
        String strE = z.e(map);
        int i7 = this.f13549s;
        String strE2 = (i7 <= 0 || ((!"https".equals(str) || i7 == 443) && (!"http".equals(str) || i7 == 80))) ? "" : android.hardware.bydauto.ac.tsimpl.a.e(i7, ":");
        if (strE.length() > 0) {
            strE = "?".concat(strE);
        }
        String strI = this.f13551u;
        boolean zContains = strI.contains(":");
        StringBuilder sbL = k.l(str, "://");
        if (zContains) {
            strI = android.hardware.bydauto.ac.tsimpl.a.i("[", strI, "]");
        }
        sbL.append(strI);
        sbL.append(strE2);
        tVar.f808n = android.hardware.bydauto.ac.tsimpl.a.l(sbL, this.f13550t, strE);
        tVar.f807m = this.f13555y;
        C1234d c1234d = new C1234d(5);
        String str2 = (String) tVar.o;
        if (str2 == null) {
            str2 = "GET";
        }
        c1234d.f14475n = str2;
        c1234d.o = (String) tVar.f808n;
        c1234d.f14476p = (String) tVar.f809p;
        c1234d.f14477q = (InterfaceC1047h) tVar.f807m;
        c1234d.T0("requestHeaders", new C1233c(this, 1));
        c1234d.T0("responseHeaders", new C1233c(this, 0));
        return c1234d;
    }
}
