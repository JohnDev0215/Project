package defpackage;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: dv, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0685dv extends C1187mv implements InterfaceC0630cv {
    public AtomicInteger l;

    public C0685dv(C1242nv c1242nv) {
        super(c1242nv, "SequencedTaskRunnerImpl", 1);
        this.l = new AtomicInteger();
    }

    @Override // defpackage.C1187mv
    public void g() {
        super.g();
        if (this.l.decrementAndGet() > 0) {
            super.h();
        }
    }

    @Override // defpackage.C1187mv
    public void h() {
        if (this.l.getAndIncrement() == 0) {
            super.h();
        }
    }
}
