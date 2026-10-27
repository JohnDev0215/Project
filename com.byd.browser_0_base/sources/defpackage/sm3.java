package defpackage;

import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ViewEventSinkImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sm3 implements org.chromium.content_public.browser.a, WindowAndroid.b, lk3 {
    public final WebContentsImpl e;
    public Boolean f;
    public boolean g;
    public Boolean h;
    public boolean i;

    /* JADX INFO: compiled from: ViewEventSinkImpl.java */
    public static final class a {
        public static final pd a = new pd(22);
    }

    public sm3(WebContentsImpl webContentsImpl) {
        this.e = webContentsImpl;
    }

    public final void a() {
        Boolean bool = this.f;
        if (bool == null) {
            return;
        }
        boolean z = bool.booleanValue() && !this.g;
        Boolean bool2 = this.h;
        if (bool2 != null && bool2.booleanValue() == z) {
            return;
        }
        this.h = Boolean.valueOf(z);
        WebContentsImpl webContentsImpl = this.e;
        if (webContentsImpl == null) {
            return;
        }
        kt3 kt3VarE = kt3.e(webContentsImpl);
        boolean zBooleanValue = this.h.booleanValue();
        boolean z2 = this.i;
        Iterator<jt3> it = kt3VarE.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                webContentsImpl.E(this.h.booleanValue());
                return;
            }
            ((jt3) aVar.next()).f(zBooleanValue, z2);
        }
    }

    @Override // org.chromium.ui.base.WindowAndroid.b
    public final void b() {
        if (this.g) {
            this.g = false;
            a();
        }
    }

    @Override // org.chromium.ui.base.WindowAndroid.b
    public final void c() {
        if (this.g) {
            return;
        }
        this.g = true;
        a();
    }

    @Override // org.chromium.ui.base.WindowAndroid.b
    public final void e() {
    }
}
