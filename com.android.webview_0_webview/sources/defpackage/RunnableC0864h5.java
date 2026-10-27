package defpackage;

import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: renamed from: h5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0864h5 implements Runnable {
    public final /* synthetic */ ArrayList s;
    public final /* synthetic */ C1087l5 t;

    public RunnableC0864h5(C1087l5 c1087l5, ArrayList arrayList) {
        this.t = c1087l5;
        this.s = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (AbstractC0975j5 abstractC0975j5 : this.s) {
            Objects.requireNonNull(this.t);
        }
        this.s.clear();
        this.t.n.remove(this.s);
    }
}
