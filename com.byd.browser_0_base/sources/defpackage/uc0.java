package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.FrameLayout;
import defpackage.ac3;
import defpackage.na0;
import defpackage.qc0;
import defpackage.ue;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.download.home.filter.b;
import org.chromium.chrome.browser.download.home.toolbar.DownloadHomeToolbar;
import org.chromium.chrome.browser.thumbnail.generator.ThumbnailGenerator;
import org.chromium.components.browser_ui.widget.selectable_list.a;

/* JADX INFO: compiled from: DownloadManagerCoordinatorImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class uc0 implements qc0, ac3.b {
    public final b<qc0.a> a = new b<>();
    public final g60 b;
    public final j80 c;
    public final ac3 d;
    public final a<f81> e;
    public final Activity f;
    public final Callback<Context> g;
    public final vb[] h;
    public FrameLayout i;
    public boolean j;

    /* JADX WARN: Type inference failed for: r15v0, types: [sc0] */
    public uc0(Activity activity, wc0 wc0Var, p32 p32Var, m62 m62Var, wp2 wp2Var, bi1 bi1Var, xe3 xe3Var, rk0 rk0Var, org.chromium.components.offline_items_collection.a aVar, na0 na0Var) {
        this.f = activity;
        this.g = m62Var;
        j80 j80Var = new j80(wp2Var);
        this.c = j80Var;
        a<f81> aVar2 = new a<>();
        this.e = aVar2;
        g60 g60Var = new g60(activity, wc0Var, p32Var, aVar, new pq2(10, j80Var), aVar2, new b.a() { // from class: sc0
            @Override // org.chromium.chrome.browser.download.home.filter.b.a
            public final void a(int i) {
                uc0 uc0Var = this.e;
                uc0Var.e.b();
                if (uc0Var.j) {
                    return;
                }
                String strM0 = z7.m0(i);
                Iterator<qc0.a> it = uc0Var.a.iterator();
                while (true) {
                    b.a aVar3 = (b.a) it;
                    if (!aVar3.hasNext()) {
                        return;
                    } else {
                        ((qc0.a) aVar3.next()).b(strM0);
                    }
                }
            }
        }, new tc0(this), bi1Var, rk0Var, na0Var);
        this.b = g60Var;
        ac3 ac3Var = new ac3(activity, this, g60Var, aVar2, wc0Var.b, xe3Var);
        this.d = ac3Var;
        FrameLayout frameLayout = new FrameLayout(activity);
        this.i = frameLayout;
        frameLayout.setBackgroundColor(ii2.a(activity));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, activity.getResources().getDimensionPixelOffset(R$dimen.selectable_list_toolbar_height), 0, 0);
        this.i.addView(g60Var.e, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 48;
        this.i.addView(ac3Var.g, layoutParams2);
        if (wc0Var.g) {
            b(z7.m0(7));
        }
        n3.X0("Android.DownloadManager.Open");
        this.h = new vb[]{g60Var.c, ac3Var};
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void a() {
        j80 j80Var = this.c;
        j80Var.a.a(j80Var.b);
        g60 g60Var = this.b;
        g60Var.b.getClass();
        m60 m60Var = g60Var.c;
        sq1 sq1Var = m60Var.l;
        sq1Var.e.j(sq1Var);
        sq1Var.g.clear();
        sq1Var.f.clear();
        sq1Var.i = true;
        t93 t93Var = m60Var.n;
        t93Var.e = null;
        t93Var.d.clear();
        Object obj = ThreadUtils.a;
        int i = t93Var.b;
        zh3.a.c(t93Var.g / 1024, 1000, 500000, 50, "Android.ThumbnailProvider.BitmapCache.Size.".concat(i != 0 ? i != 1 ? "Other" : "NTPSnippets" : "DownloadHome"));
        r93 r93Var = t93Var.f;
        ThumbnailGenerator thumbnailGenerator = r93Var.a;
        thumbnailGenerator.getClass();
        long j = thumbnailGenerator.a;
        if (j != 0) {
            N.MabdmJU9(j, thumbnailGenerator);
            thumbnailGenerator.a = 0L;
        }
        r93Var.f = true;
        ue ueVar = t93Var.a;
        na0.a<ue.a> aVar = ueVar.b;
        Set<na0.a<?>> set = ueVar.c.a;
        if (set.contains(aVar)) {
            aVar.a = null;
            set.remove(aVar);
        }
        ueVar.b = null;
        wa2 wa2Var = g60Var.d;
        ta2 ta2Var = wa2Var.a;
        bi1 bi1Var = ta2Var.a;
        if (bi1Var != null) {
            bi1Var.b(8, ta2Var.b);
        }
        xa2 xa2Var = wa2Var.b;
        bi1 bi1Var2 = xa2Var.a;
        if (bi1Var2 != null) {
            bi1Var2.b(8, xa2Var.b);
        }
        DownloadHomeToolbar downloadHomeToolbar = this.d.h;
        downloadHomeToolbar.y0 = true;
        a<E> aVar2 = downloadHomeToolbar.c0;
        if (aVar2 != 0) {
            aVar2.g(downloadHomeToolbar);
        }
        EditText editText = downloadHomeToolbar.g0;
        if (editText != null) {
            t41.f.e(editText);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = r5.j
            r1 = 1
            r5.j = r1
            g60 r2 = r5.b     // Catch: java.lang.Throwable -> L28
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L28
            r4 = 0
            if (r3 != 0) goto L2a
            java.lang.String r3 = "csacsacascasc@vsdvdsvdvdv%^*dsvdsvvdsvdsv"
            boolean r3 = r6.startsWith(r3)     // Catch: java.lang.Throwable -> L28
            if (r3 != 0) goto L17
            goto L2a
        L17:
            r3 = 41
            java.lang.String r6 = r6.substring(r3)     // Catch: java.lang.Throwable -> L28 java.lang.NumberFormatException -> L2a
            int r6 = java.lang.Integer.parseInt(r6)     // Catch: java.lang.Throwable -> L28 java.lang.NumberFormatException -> L2a
            if (r6 < 0) goto L2a
            r3 = 8
            if (r6 < r3) goto L2b
            goto L2a
        L28:
            r6 = move-exception
            goto L4b
        L2a:
            r6 = r4
        L2b:
            org.chromium.chrome.browser.download.home.filter.b r2 = r2.b     // Catch: java.lang.Throwable -> L28
            r3 = 7
            if (r6 != r3) goto L3f
            nw2<java.lang.Boolean> r3 = r2.f     // Catch: java.lang.Throwable -> L28
            java.lang.Object r3 = r3.get()     // Catch: java.lang.Throwable -> L28
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch: java.lang.Throwable -> L28
            boolean r3 = r3.booleanValue()     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L3f
            goto L45
        L3f:
            org.chromium.chrome.browser.download.home.filter.a r1 = r2.e     // Catch: java.lang.Throwable -> L28
            r1.g(r6)     // Catch: java.lang.Throwable -> L28
            r1 = r4
        L45:
            r2.b(r1)     // Catch: java.lang.Throwable -> L28
            r5.j = r0
            return
        L4b:
            r5.j = r0     // Catch: java.lang.Throwable -> L4d
        L4d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uc0.b(java.lang.String):void");
    }
}
