package defpackage;

import defpackage.sm3;
import defpackage.ya0;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: WindowEventObserverManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class kt3 implements ya0.a, lk3 {
    public final b<jt3> e = new b<>();
    public WindowAndroid f;
    public final sm3 g;
    public boolean h;
    public int i;
    public float j;

    /* JADX INFO: compiled from: WindowEventObserverManager.java */
    public static final class a {
        public static final i83 a = new i83(22);
    }

    public kt3(WebContentsImpl webContentsImpl) {
        this.g = (sm3) webContentsImpl.n0(sm3.class, sm3.a.a);
        WindowAndroid windowAndroidV = webContentsImpl.V();
        if (windowAndroidV != null) {
            a(windowAndroidV);
        }
        b(webContentsImpl);
    }

    public static kt3 e(WebContents webContents) {
        return (kt3) ((WebContentsImpl) webContents).n0(kt3.class, a.a);
    }

    public final void a(WindowAndroid windowAndroid) {
        WindowAndroid windowAndroid2;
        WindowAndroid windowAndroid3 = this.f;
        if (windowAndroid == windowAndroid3) {
            return;
        }
        if (windowAndroid3 != null) {
            windowAndroid3.h.a.remove(this);
        }
        if (this.h && (windowAndroid2 = this.f) != null) {
            windowAndroid2.v.c(this.g);
        }
        this.f = windowAndroid;
        d();
        Iterator<jt3> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((jt3) aVar.next()).a(windowAndroid);
            }
        }
    }

    public final void b(jt3 jt3Var) {
        this.e.a(jt3Var);
        if (this.h) {
            jt3Var.onAttachedToWindow();
        }
    }

    public final void d() {
        WindowAndroid windowAndroid;
        WindowAndroid windowAndroid2;
        if (this.h && (windowAndroid2 = this.f) != null) {
            ya0 ya0Var = windowAndroid2.h;
            ya0Var.a.put(this, null);
            w(ya0Var.i);
            t(ya0Var.d);
        }
        if (!this.h || (windowAndroid = this.f) == null) {
            return;
        }
        b<WindowAndroid.b> bVar = windowAndroid.v;
        sm3 sm3Var = this.g;
        bVar.a(sm3Var);
        if (this.f.l() == 3) {
            sm3Var.b();
        }
    }

    @Override // ya0.a
    public final void t(float f) {
        if (this.j == f) {
            return;
        }
        this.j = f;
        Iterator<jt3> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((jt3) aVar.next()).t(f);
            }
        }
    }

    @Override // ya0.a
    public final void w(int i) {
        if (this.i == i) {
            return;
        }
        this.i = i;
        Iterator<jt3> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((jt3) aVar.next()).w(i);
            }
        }
    }
}
