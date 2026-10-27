package defpackage;

import java.util.HashSet;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* JADX INFO: compiled from: MediaSessionHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fe1 extends ke1 {
    public final /* synthetic */ de1 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fe1(de1 de1Var, MediaSessionImpl mediaSessionImpl) {
        super(mediaSessionImpl);
        this.b = de1Var;
    }

    @Override // defpackage.ke1
    public final void a(HashSet hashSet) {
        de1 de1Var = this.b;
        de1Var.p = hashSet;
        if (de1Var.g()) {
            return;
        }
        de1Var.k.n = de1Var.p;
        de1Var.i();
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00d5  */
    @Override // defpackage.ke1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(java.util.List<org.chromium.services.media_session.MediaImage> r17) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fe1.b(java.util.List):void");
    }

    @Override // defpackage.ke1
    public final void c() {
        de1 de1Var = this.b;
        if (de1Var.c != null) {
            Runnable runnable = de1Var.s;
            if (runnable != null) {
                de1Var.r.removeCallbacks(runnable);
                de1Var.s = null;
            }
            de1Var.f();
            de1Var.k = null;
        }
        de1Var.b();
    }

    @Override // defpackage.ke1
    public final void d(MediaMetadata mediaMetadata) {
        de1 de1Var = this.b;
        de1Var.n = mediaMetadata;
        de1.a(de1Var);
    }

    @Override // defpackage.ke1
    public final void e(MediaPosition mediaPosition) {
        de1 de1Var = this.b;
        de1Var.q = mediaPosition;
        if (de1Var.g()) {
            return;
        }
        de1Var.k.o = de1Var.q;
        de1Var.i();
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0130  */
    @Override // defpackage.ke1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(boolean r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fe1.f(boolean, boolean):void");
    }
}
