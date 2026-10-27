package defpackage;

import android.os.Handler;
import android.os.Looper;
import defpackage.a00;
import defpackage.kz2;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ReaderModeActionProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e92 implements a00.a {

    /* JADX INFO: compiled from: ReaderModeActionProvider.java */
    public class a implements kz2.a {
        public final /* synthetic */ gm2 a;
        public final /* synthetic */ kz2 b;

        public a(gm2 gm2Var, kz2 kz2Var) {
            this.a = gm2Var;
            this.b = kz2Var;
        }

        @Override // kz2.a
        public final void a(Tab tab, boolean z, boolean z2, boolean z3) {
            e92.this.getClass();
            e92.c(z, z3, tab, this.a);
            this.b.e.c(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(boolean r3, boolean r4, org.chromium.chrome.browser.tab.Tab r5, defpackage.gm2 r6) {
        /*
            r0 = 0
            if (r3 == 0) goto L73
            org.chromium.content_public.browser.WebContents r3 = r5.c()
            r1 = 1
            if (r3 == 0) goto L26
            org.chromium.content_public.browser.WebContents r3 = r5.c()
            org.chromium.content_public.browser.NavigationController r3 = r3.m()
            boolean r3 = r3.l()
            if (r3 == 0) goto L26
            int r3 = defpackage.pb0.a()
            r2 = 4
            if (r3 != r2) goto L21
            r3 = r1
            goto L22
        L21:
            r3 = r0
        L22:
            if (r3 != 0) goto L26
            r3 = r1
            goto L27
        L26:
            r3 = r0
        L27:
            if (r3 == 0) goto L2a
            goto L6d
        L2a:
            sk r3 = defpackage.bq.a
            java.lang.String r3 = "ContextualPageActionReaderMode"
            java.lang.String r2 = "reader_mode_session_rate_limiting"
            boolean r3 = J.N.M6bsIDpc(r3, r2, r1)
            if (r3 == 0) goto L65
            mk3 r3 = r5.B()
            java.util.LinkedHashSet<java.lang.Integer> r2 = defpackage.i92.u
            java.lang.Class<i92> r2 = defpackage.i92.class
            lk3 r3 = r3.b(r2)
            i92 r3 = (defpackage.i92) r3
            boolean r2 = r3.s
            if (r2 != 0) goto L61
            java.util.LinkedHashSet<java.lang.Integer> r2 = defpackage.i92.u
            org.chromium.url.GURL r3 = r3.j
            java.lang.String r3 = r3.e()
            int r3 = r3.hashCode()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            boolean r3 = r2.contains(r3)
            if (r3 == 0) goto L5f
            goto L61
        L5f:
            r3 = r0
            goto L62
        L61:
            r3 = r1
        L62:
            if (r3 == 0) goto L65
            goto L6d
        L65:
            if (r4 == 0) goto L6f
            boolean r3 = defpackage.pb0.b(r5)
            if (r3 == 0) goto L6f
        L6d:
            r3 = r1
            goto L70
        L6f:
            r3 = r0
        L70:
            if (r3 != 0) goto L73
            r0 = r1
        L73:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            r6.b = r3
            r6.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.e92.c(boolean, boolean, org.chromium.chrome.browser.tab.Tab, gm2):void");
    }

    @Override // a00.a
    public final void a(Tab tab, gm2 gm2Var) {
        kz2 kz2Var = (kz2) tab.B().b(kz2.class);
        if (kz2Var.g) {
            c(kz2Var.i, kz2Var.k, tab, gm2Var);
        } else {
            kz2Var.e.a(new a(gm2Var, kz2Var));
        }
    }

    @Override // a00.a
    public final void b(int i, Tab tab) {
        if (tab != null && i == 7) {
            new Handler(Looper.getMainLooper()).postDelayed(new d92(0, tab), 500L);
        }
    }
}
