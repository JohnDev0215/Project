package defpackage;

import J.N;
import android.util.Log;
import com.google.protobuf.d0;
import defpackage.yl2;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ShoppingPersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public class xl2 extends l02 {
    public static final int t;
    public static final long u;
    public static final int v;
    public static final du1 w;
    public static final ArrayDeque x;
    public static boolean y;
    public long n;
    public d o;
    public t32 p;
    public final HashMap q;
    public final aq1<Boolean> r;
    public wl2 s;

    /* JADX INFO: compiled from: ShoppingPersistedTabData.java */
    public static class a {
        public static final vs1 a;

        static {
            boolean zR = xl2.r();
            ou0 ou0Var = ou0.SHOPPING_PAGE_PREDICTOR;
            a = new vs1(zR ? Arrays.asList(ou0Var, ou0.PRICE_TRACKING) : Arrays.asList(ou0Var));
        }
    }

    /* JADX INFO: compiled from: ShoppingPersistedTabData.java */
    public static class b {
        public final long a;
        public final long b;
        public final long c;

        public b(xl2 xl2Var) {
            d dVar = xl2Var.o;
            this.a = dVar.a;
            this.b = dVar.b;
            this.c = xl2Var.n;
        }
    }

    /* JADX INFO: compiled from: ShoppingPersistedTabData.java */
    public static class c {
        public final String a;
        public final String b;

        public c(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.a.equals(cVar.a) && this.b.equals(cVar.b);
        }

        public final int hashCode() {
            String str = this.a;
            int iHashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.b;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: ShoppingPersistedTabData.java */
    public static class d {
        public long a = -1;
        public long b = -1;
        public String c;
        public String d;
        public GURL e;
        public boolean f;
    }

    /* JADX INFO: compiled from: ShoppingPersistedTabData.java */
    public static class e {
        public final Tab a;
        public final Callback<xl2> b;

        public e(Callback callback, Tab tab) {
            this.a = tab;
            this.b = callback;
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.DAYS;
        t = (int) timeUnit.toMillis(7L);
        u = TimeUnit.HOURS.toMillis(1L);
        v = (int) timeUnit.toSeconds(90L);
        w = new du1();
        x = new ArrayDeque();
        l02.m.add(xl2.class);
    }

    public xl2(Tab tab) {
        super(tab, m02.a(xl2.class, tab.isIncognito()).f(), m02.a(xl2.class, tab.isIncognito()).e);
        this.n = -1L;
        this.o = new d();
        this.q = new HashMap();
        aq1<Boolean> aq1Var = new aq1<>();
        this.r = aq1Var;
        aq1Var.r(Boolean.FALSE);
        this.i = aq1Var;
        jb1 jb1Var = new jb1(6, this);
        this.j = jb1Var;
        aq1Var.p(jb1Var);
        wl2 wl2Var = new wl2(this);
        this.s = wl2Var;
        tab.x(wl2Var);
        this.p = new t32(this);
    }

    public static xl2 n(Tab tab) {
        xl2 xl2Var = (xl2) l02.d(tab, xl2.class);
        return xl2Var == null ? (xl2) tab.B().d(xl2.class, new xl2(tab)) : xl2Var;
    }

    public static void o(Callback callback, Tab tab) {
        if (tab == null || tab.isDestroyed()) {
            PostTask.d(rh3.a, new l70(callback, 1));
            return;
        }
        if (y) {
            p(callback, tab);
            return;
        }
        boolean z = false;
        if (a8.P()) {
            sk skVar = bq.a;
            if (N.M6bsIDpc("CommercePriceTracking", "return_empty_price_drops_until_init", false)) {
                z = true;
            }
        }
        if (z) {
            PostTask.c(rh3.a, new x00(callback, 1));
        } else {
            if (z) {
                return;
            }
            x.add(new e(callback, tab));
        }
    }

    public static void p(Callback callback, Tab tab) {
        if (tab.isIncognito() || tab.isCustomTab()) {
            PostTask.c(rh3.a, new th0(callback, 3));
        } else {
            l02.e(tab, new gx(20, tab), new x10(1, tab), xl2.class, callback);
        }
    }

    public static boolean r() {
        if (!a8.P()) {
            return false;
        }
        sk skVar = bq.a;
        return N.M6bsIDpc("CommercePriceTracking", "price_tracking_with_optimization_guide", false);
    }

    public static void t() {
        ArrayDeque arrayDeque = x;
        if (arrayDeque.isEmpty()) {
            y = true;
            return;
        }
        e eVar = (e) arrayDeque.poll();
        if (!eVar.a.isDestroyed()) {
            p(new jb1(7, eVar), eVar.a);
        } else {
            PostTask.c(rh3.a, new d92(8, eVar));
            t();
        }
    }

    @Override // defpackage.l02
    public final boolean b(ByteBuffer byteBuffer) {
        if (byteBuffer != null && byteBuffer.hasRemaining()) {
            try {
                yl2 yl2VarH0 = yl2.h0(byteBuffer);
                this.o.a = yl2VarH0.e0();
                this.o.b = yl2VarH0.c0();
                this.h = yl2VarH0.a0();
                this.n = yl2VarH0.Z();
                this.o.d = yl2VarH0.b0();
                this.o.c = yl2VarH0.d0();
                this.o.e = GURL.a(yl2VarH0.f0());
                this.o.f = yl2VarH0.Y();
                return true;
            } catch (d0 e2) {
                Log.e("cr_SPTD", String.format(Locale.US, "There was a problem deserializing ShoppingPersistedTabData. Details: %s", e2.getMessage()));
            }
        }
        return false;
    }

    @Override // defpackage.l02, defpackage.lk3
    public final void destroy() {
        this.e.t(this.s);
        HashMap map = this.q;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            ((g20) it.next()).a();
        }
        map.clear();
        this.p.a = null;
        this.p = null;
        super.destroy();
    }

    @Override // defpackage.l02
    public final vi2<ByteBuffer> g() {
        yl2.a aVarG0 = yl2.g0();
        long j = this.o.a;
        aVarG0.y();
        yl2.V((yl2) aVarG0.f, j);
        long j2 = this.o.b;
        aVarG0.y();
        yl2.T((yl2) aVarG0.f, j2);
        long j3 = this.h;
        aVarG0.y();
        yl2.Q((yl2) aVarG0.f, j3);
        long j4 = this.n;
        aVarG0.y();
        yl2.P((yl2) aVarG0.f, j4);
        boolean z = this.o.f;
        aVarG0.y();
        yl2.O((yl2) aVarG0.f, z);
        String str = this.o.d;
        if (str != null) {
            aVarG0.y();
            yl2.S((yl2) aVarG0.f, str);
        }
        String str2 = this.o.c;
        if (str2 != null) {
            aVarG0.y();
            yl2.U((yl2) aVarG0.f, str2);
        }
        GURL gurl = this.o.e;
        if (gurl != null) {
            String strM = gurl.m();
            aVarG0.y();
            yl2.W((yl2) aVarG0.f, strM);
        }
        return new c01(21, aVarG0);
    }

    @Override // defpackage.l02
    public final long h() {
        boolean zP = a8.P();
        long j = u;
        if (!zP) {
            return (int) j;
        }
        int i = (int) j;
        sk skVar = bq.a;
        return N.M37SqSAy("CommercePriceTracking", "price_tracking_time_to_live_ms", i);
    }

    @Override // defpackage.l02
    public final String i() {
        return "SPTD";
    }

    @Override // defpackage.l02
    public final boolean j() {
        if (this.o.e == null || this.e.getUrl().equals(this.o.e)) {
            return super.j();
        }
        return true;
    }

    public final String m(long j) {
        String str = this.o.c;
        if (str == null) {
            return "";
        }
        HashMap map = this.q;
        if (map.get(str) == null) {
            map.put(str, new g20(this.o.c, Locale.getDefault()));
        }
        return n3.N(j, (g20) map.get(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final xl2.c q() {
        /*
            r10 = this;
            xl2$d r0 = r10.o
            long r1 = r0.a
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L18
            long r8 = r0.b
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 == 0) goto L18
            int r0 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r0 >= 0) goto L18
            r0 = r6
            goto L19
        L18:
            r0 = r7
        L19:
            r1 = 0
            if (r0 == 0) goto L6f
            long r8 = r10.n
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 == 0) goto L41
            long r2 = java.lang.System.currentTimeMillis()
            long r4 = r10.n
            long r2 = r2 - r4
            boolean r0 = defpackage.a8.P()
            int r4 = defpackage.xl2.t
            if (r0 == 0) goto L3b
            sk r0 = defpackage.bq.a
            java.lang.String r0 = "CommercePriceTracking"
            java.lang.String r5 = "price_tracking_display_time_ms"
            int r4 = J.N.M37SqSAy(r0, r5, r4)
        L3b:
            long r4 = (long) r4
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L41
            goto L42
        L41:
            r6 = r7
        L42:
            if (r6 != 0) goto L6f
            org.chromium.chrome.browser.tab.Tab r0 = r10.e
            org.chromium.url.GURL r0 = r0.getUrl()
            xl2$d r2 = r10.o
            org.chromium.url.GURL r2 = r2.e
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L55
            goto L6f
        L55:
            xl2$d r0 = r10.o
            long r2 = r0.a
            long r4 = r0.b
            java.lang.String r0 = r10.m(r2)
            java.lang.String r10 = r10.m(r4)
            boolean r2 = r0.equals(r10)
            if (r2 == 0) goto L6a
            goto L6f
        L6a:
            xl2$c r1 = new xl2$c
            r1.<init>(r0, r10)
        L6f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xl2.q():xl2$c");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s(org.chromium.chrome.browser.tab.Tab r11, defpackage.d42 r12, xl2.b r13) {
        /*
            Method dump skipped, instruction units count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xl2.s(org.chromium.chrome.browser.tab.Tab, d42, xl2$b):void");
    }

    public final void u() {
        a();
        this.o = new d();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(long r6, xl2.b r8) {
        /*
            r5 = this;
            xl2$d r0 = r5.o
            r0.a = r6
            if (r8 == 0) goto L1f
            r1 = -1
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 == 0) goto L1f
            long r3 = r8.a
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 == 0) goto L1f
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 == 0) goto L1f
            r0.b = r3
            long r6 = java.lang.System.currentTimeMillis()
            r5.n = r6
            goto L29
        L1f:
            if (r8 == 0) goto L29
            long r6 = r8.b
            r0.b = r6
            long r6 = r8.c
            r5.n = r6
        L29:
            r5.l()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xl2.v(long, xl2$b):void");
    }
}
