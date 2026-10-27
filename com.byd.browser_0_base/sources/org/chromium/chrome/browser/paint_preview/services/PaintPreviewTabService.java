package org.chromium.chrome.browser.paint_preview.services;

import J.N;
import defpackage.a9;
import defpackage.bk3;
import defpackage.gp;
import defpackage.j23;
import defpackage.pk1;
import defpackage.qo;
import defpackage.y13;
import defpackage.za2;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* JADX INFO: loaded from: classes2.dex */
public class PaintPreviewTabService implements pk1 {
    public a9 a;
    public long b;
    public long c;

    public class a extends j23 implements ApplicationStatus.d {
        public int i;

        public a(y13 y13Var) {
            super(y13Var);
            ApplicationStatus.e(this);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void T0(int i, Tab tab) {
            if (this.i == 3) {
                if ((!PaintPreviewTabService.c(tab) || tab.c() == null || tab.k()) ? false : true) {
                    PaintPreviewTabService.this.b(new gp(this, 17, tab), tab);
                }
            }
        }

        @Override // org.chromium.base.ApplicationStatus.d
        public final void v(int i) {
            b<ApplicationStatus.d> bVar;
            this.i = i;
            if (i != 4 || (bVar = ApplicationStatus.f) == null) {
                return;
            }
            bVar.c(this);
        }

        @Override // defpackage.j23
        public final void z1(Tab tab) {
            long j = PaintPreviewTabService.this.c;
            if (j == 0) {
                return;
            }
            N.MO7GqHLu(j, tab.getId());
        }
    }

    @CalledByNative
    public PaintPreviewTabService(long j, long j2) {
        this.c = j;
        this.b = j2;
    }

    public static boolean c(Tab tab) {
        return (tab.isIncognito() || tab.isNativePage() || tab.m() || !bk3.i(tab.getUrl()) || N.M$l72hrq(tab.getUrl().j())) ? false : true;
    }

    @Override // defpackage.pk1
    public final long a() {
        return this.b;
    }

    public final void b(Callback callback, Tab tab) {
        if (this.c == 0) {
            callback.onResult(Boolean.FALSE);
            return;
        }
        boolean zC = qo.f().c();
        za2 za2Var = ((WebContentsImpl) tab.c()).l;
        N.MV$XyJvN(this.c, tab.getId(), tab.c(), zC, za2Var.g, (int) Math.floor(za2Var.a(za2Var.a)), za2Var.b(), callback);
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.c = 0L;
        this.b = 0L;
    }
}
