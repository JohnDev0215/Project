package defpackage;

import java.util.Objects;

/* JADX INFO: renamed from: l2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1084l2 implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ AbstractC1140m2 t;

    public RunnableC1084l2(AbstractC1140m2 abstractC1140m2, int i) {
        this.t = abstractC1140m2;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        Objects.requireNonNull((C0970j0) this.t);
    }
}
