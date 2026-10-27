package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.Bitmap;
import org.chromium.chrome.browser.customtabs.CustomTabTaskDescriptionIconGenerator;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: CustomTabTaskDescriptionHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class l40 implements hk1, v80 {
    public final Activity e;
    public final a30 f;
    public final o23 g;
    public final oj h;
    public final xd3 i;
    public CustomTabTaskDescriptionIconGenerator j;
    public FaviconHelper k;
    public j40 l;
    public k40 m;
    public int n;
    public String o;
    public Bitmap p;
    public boolean q;
    public Bitmap r;

    public l40(Activity activity, a30 a30Var, o23 o23Var, oj ojVar, q2 q2Var, xd3 xd3Var) {
        this.e = activity;
        this.f = a30Var;
        this.g = o23Var;
        this.h = ojVar;
        this.i = xd3Var;
        q2Var.f(this);
    }

    public static void a(l40 l40Var) {
        Tab tab;
        l40Var.c();
        if (l40Var.p != null || (tab = l40Var.f.b) == null) {
            return;
        }
        GURL url = tab.getUrl();
        FaviconHelper faviconHelper = l40Var.k;
        Profile profileB = Profile.b(tab.c());
        GURL url2 = tab.getUrl();
        i40 i40Var = new i40(l40Var, url);
        faviconHelper.getClass();
        faviconHelper.a(profileB, url2.j(), 0, i40Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001e  */
    @Override // defpackage.hk1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void G0() {
        /*
            r6 = this;
            oj r0 = r6.h
            js3 r1 = r0.M()
            android.app.Activity r2 = r6.e
            r3 = 1
            if (r1 != 0) goto L1e
            android.content.Intent r4 = r2.getIntent()
            int r4 = r4.getFlags()
            r5 = 268959744(0x10080000, float:2.682127E-29)
            r4 = r4 & r5
            r5 = 0
            if (r4 == 0) goto L1b
            r4 = r3
            goto L1c
        L1b:
            r4 = r5
        L1c:
            if (r4 == 0) goto L1f
        L1e:
            r5 = r3
        L1f:
            if (r5 != 0) goto L22
            return
        L22:
            int r4 = org.chromium.chrome.R$color.default_primary_color
            int r4 = r2.getColor(r4)
            r6.n = r4
            if (r1 == 0) goto L54
            cv r4 = r0.m()
            boolean r4 = r4.f()
            if (r4 == 0) goto L40
            cv r4 = r0.m()
            int r4 = r4.d()
            r6.n = r4
        L40:
            ks3 r4 = r1.d
            android.graphics.Bitmap r4 = r4.a()
            r6.p = r4
            java.lang.String r1 = r1.f
            r6.o = r1
            boolean r0 = r0.T()
            if (r0 == 0) goto L54
            r6.q = r3
        L54:
            org.chromium.chrome.browser.customtabs.CustomTabTaskDescriptionIconGenerator r0 = new org.chromium.chrome.browser.customtabs.CustomTabTaskDescriptionIconGenerator
            r0.<init>(r2)
            r6.j = r0
            org.chromium.chrome.browser.ui.favicon.FaviconHelper r0 = new org.chromium.chrome.browser.ui.favicon.FaviconHelper
            r0.<init>()
            r6.k = r0
            j40 r0 = new j40
            r0.<init>(r6)
            r6.l = r0
            o23 r1 = r6.g
            r1.c(r0)
            android.graphics.Bitmap r0 = r6.p
            if (r0 != 0) goto L80
            boolean r0 = r6.q
            if (r0 != 0) goto L80
            k40 r0 = new k40
            r0.<init>(r6)
            r6.m = r0
            r1.c(r0)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l40.G0():void");
    }

    public final void b(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        if (this.r == null || bitmap.getWidth() > this.r.getWidth() || bitmap.getHeight() > this.r.getHeight()) {
            this.r = bitmap;
            c();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            r14 = this;
            android.app.ActivityManager$TaskDescription r0 = new android.app.ActivityManager$TaskDescription
            java.lang.String r1 = r14.o
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 0
            r3 = 0
            a30 r4 = r14.f
            if (r1 != 0) goto L11
            java.lang.String r1 = r14.o
            goto L2f
        L11:
            org.chromium.chrome.browser.tab.Tab r1 = r4.b
            if (r1 != 0) goto L17
            r1 = r2
            goto L2f
        L17:
            java.lang.String r5 = r1.getTitle()
            org.chromium.url.GURL r1 = r1.getUrl()
            java.lang.String r1 = r1.j()
            java.lang.String r1 = defpackage.bk3.b(r1, r3)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 == 0) goto L2e
            goto L2f
        L2e:
            r1 = r5
        L2f:
            boolean r5 = r14.q
            if (r5 == 0) goto L35
            goto L9c
        L35:
            android.graphics.Bitmap r5 = r14.p
            if (r5 == 0) goto L3b
            r2 = r5
            goto L9c
        L3b:
            org.chromium.chrome.browser.tab.Tab r5 = r4.b
            if (r5 != 0) goto L40
            goto L9c
        L40:
            boolean r6 = r5.isIncognito()
            if (r6 != 0) goto L9c
            org.chromium.chrome.browser.customtabs.CustomTabTaskDescriptionIconGenerator r2 = r14.j
            org.chromium.url.GURL r5 = r5.getUrl()
            android.graphics.Bitmap r6 = r14.r
            if (r6 == 0) goto L63
            r2.getClass()
            int r7 = r6.getWidth()
            int r8 = r2.b
            if (r7 < r8) goto L63
            int r7 = r6.getHeight()
            if (r7 < r8) goto L63
            r2 = r6
            goto L9c
        L63:
            org.chromium.url.GURL r6 = r2.c
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L6e
            android.graphics.Bitmap r2 = r2.d
            goto L9c
        L6e:
            uc2 r6 = r2.e
            if (r6 != 0) goto L8a
            uc2 r6 = new uc2
            android.content.Context r7 = r2.a
            android.content.res.Resources r8 = r7.getResources()
            r9 = 64
            r10 = 64
            r11 = 3
            r12 = -13487566(0xffffffffff323232, float:-2.3686321E38)
            r13 = 30
            r7 = r6
            r7.<init>(r8, r9, r10, r11, r12, r13)
            r2.e = r6
        L8a:
            r2.c = r5
            uc2 r6 = r2.e
            r6.getClass()
            java.lang.String r5 = r5.j()
            android.graphics.Bitmap r3 = r6.b(r5, r3)
            r2.d = r3
            r2 = r3
        L9c:
            org.chromium.chrome.browser.tab.Tab r3 = r4.b
            int r4 = r14.n
            xd3 r5 = r14.i
            if (r3 == 0) goto Lac
            boolean r3 = r5.p
            if (r3 == 0) goto La9
            goto Laf
        La9:
            int r4 = r5.e
            goto Laf
        Lac:
            r5.getClass()
        Laf:
            int r3 = defpackage.z7.A(r4)
            r0.<init>(r1, r2, r3)
            android.app.Activity r14 = r14.e
            r14.setTaskDescription(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l40.c():void");
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        FaviconHelper faviconHelper = this.k;
        if (faviconHelper != null) {
            N.Mz5mgjYL(faviconHelper.a);
            faviconHelper.a = 0L;
        }
        j40 j40Var = this.l;
        o23 o23Var = this.g;
        o23Var.e(j40Var);
        o23Var.e(this.m);
    }
}
