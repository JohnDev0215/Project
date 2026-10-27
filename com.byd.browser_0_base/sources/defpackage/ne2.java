package defpackage;

import java.util.HashMap;
import java.util.Objects;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ScopeChangeController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ne2 {
    public final a a;
    public final HashMap b = new HashMap();

    /* JADX INFO: compiled from: ScopeChangeController.java */
    public interface a {
    }

    /* JADX INFO: compiled from: ScopeChangeController.java */
    public static class b extends rq3 implements c {
        public final a f;
        public final pe2 g;
        public GURL h;

        /* JADX WARN: Illegal instructions before constructor call */
        public b(a aVar, pe2 pe2Var) {
            WebContents webContents = pe2Var.b;
            super(webContents);
            this.f = aVar;
            this.g = pe2Var;
            ((fh1) aVar).d(new ih1(pe2Var, (webContents == null || webContents.r() != 2) ? 1 : 0));
        }

        @Override // defpackage.rq3
        public final void destroy() {
            super.destroy();
            pe2 pe2Var = this.g;
            int i = pe2Var.a;
            ((fh1) this.f).d(new ih1(pe2Var, 2));
        }

        @Override // defpackage.rq3
        public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
            int i = this.g.a;
            if ((i == 2 || i == 3) && !navigationHandle.c && navigationHandle.g && !navigationHandle.r) {
                if (i == 3) {
                    GURL gurl = this.h;
                    if (gurl != null) {
                        GURL gurl2 = navigationHandle.e;
                        if (!(gurl2 != null && Objects.equals(gurl.i(), gurl2.i()) && Objects.equals(gurl.e(), gurl2.e()) && Objects.equals(gurl.h(), gurl2.h()))) {
                            this.h = navigationHandle.e;
                        }
                    }
                    this.h = navigationHandle.e;
                    return;
                }
                destroy();
            }
        }

        @Override // defpackage.rq3
        public final void h(WindowAndroid windowAndroid) {
            destroy();
        }

        @Override // defpackage.rq3
        public final void wasHidden() {
            pe2 pe2Var = this.g;
            int i = pe2Var.a;
            ((fh1) this.f).d(new ih1(pe2Var, 1));
        }

        @Override // defpackage.rq3
        public final void wasShown() {
            pe2 pe2Var = this.g;
            int i = pe2Var.a;
            ((fh1) this.f).d(new ih1(pe2Var, 0));
        }
    }

    /* JADX INFO: compiled from: ScopeChangeController.java */
    public interface c {
        void destroy();
    }

    /* JADX INFO: compiled from: ScopeChangeController.java */
    public static class d implements c, WindowAndroid.b {
        public final a e;
        public final pe2 f;

        public d(a aVar, pe2 pe2Var) {
            this.e = aVar;
            this.f = pe2Var;
            WindowAndroid windowAndroid = pe2Var.c;
            windowAndroid.v.a(this);
            ((fh1) aVar).d(new ih1(pe2Var, windowAndroid.l() == 3 ? 0 : 1));
        }

        @Override // org.chromium.ui.base.WindowAndroid.b
        public final void b() {
            pe2 pe2Var = this.f;
            int i = pe2Var.a;
            ((fh1) this.e).d(new ih1(pe2Var, 0));
        }

        @Override // org.chromium.ui.base.WindowAndroid.b
        public final void c() {
            pe2 pe2Var = this.f;
            int i = pe2Var.a;
            ((fh1) this.e).d(new ih1(pe2Var, 1));
        }

        @Override // ne2.c
        public final void destroy() {
            this.f.c.v.c(this);
        }

        @Override // org.chromium.ui.base.WindowAndroid.b
        public final void e() {
            pe2 pe2Var = this.f;
            int i = pe2Var.a;
            ((fh1) this.e).d(new ih1(pe2Var, 2));
        }
    }

    public ne2(a aVar) {
        this.a = aVar;
    }
}
