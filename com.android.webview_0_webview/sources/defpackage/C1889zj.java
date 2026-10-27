package defpackage;

import java.util.Queue;

/* JADX INFO: renamed from: zj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1889zj {
    public final Object a = new Object();
    public Queue b;
    public boolean c;

    public final void a(AbstractC1559tj abstractC1559tj) {
        InterfaceC0009Aj interfaceC0009Aj;
        synchronized (this.a) {
            if (this.b != null && !this.c) {
                this.c = true;
                while (true) {
                    synchronized (this.a) {
                        interfaceC0009Aj = (InterfaceC0009Aj) this.b.poll();
                        if (interfaceC0009Aj == null) {
                            this.c = false;
                            return;
                        }
                    }
                    C1779xj c1779xj = (C1779xj) interfaceC0009Aj;
                    synchronized (c1779xj.b) {
                        if (c1779xj.c != null) {
                            c1779xj.a.execute(new RunnableC1834yj(c1779xj, abstractC1559tj));
                        }
                    }
                }
            }
        }
    }
}
