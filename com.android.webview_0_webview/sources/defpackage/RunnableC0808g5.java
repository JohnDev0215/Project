package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: renamed from: g5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0808g5 implements Runnable {
    public final /* synthetic */ ArrayList s;
    public final /* synthetic */ C1087l5 t;

    public RunnableC0808g5(C1087l5 c1087l5, ArrayList arrayList) {
        this.t = c1087l5;
        this.s = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        Iterator it = this.s.iterator();
        if (!it.hasNext()) {
            this.s.clear();
            this.t.m.remove(this.s);
        } else {
            AbstractC1031k5 abstractC1031k5 = (AbstractC1031k5) it.next();
            C1087l5 c1087l5 = this.t;
            Objects.requireNonNull(abstractC1031k5);
            Objects.requireNonNull(c1087l5);
            throw null;
        }
    }
}
