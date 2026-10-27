package defpackage;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: Bj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0027Bj extends AbstractC1559tj {
    public final Object a = new Object();
    public final C1889zj b = new C1889zj();
    public boolean c;
    public Object d;
    public Exception e;

    public final AbstractC1559tj a(Executor executor, InterfaceC1504sj interfaceC1504sj) {
        C1889zj c1889zj = this.b;
        AbstractC0045Cj.a(executor);
        C1779xj c1779xj = new C1779xj(executor, interfaceC1504sj);
        synchronized (c1889zj.a) {
            if (c1889zj.b == null) {
                c1889zj.b = new ArrayDeque();
            }
            c1889zj.b.add(c1779xj);
        }
        synchronized (this.a) {
            if (this.c) {
                this.b.a(this);
            }
        }
        return this;
    }
}
