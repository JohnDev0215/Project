package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: renamed from: i5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0920i5 implements Runnable {
    public final /* synthetic */ ArrayList s;
    public final /* synthetic */ C1087l5 t;

    public RunnableC0920i5(C1087l5 c1087l5, ArrayList arrayList) {
        this.t = c1087l5;
        this.s = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        Iterator it = this.s.iterator();
        if (it.hasNext()) {
            Objects.requireNonNull(this.t);
            throw null;
        }
        this.s.clear();
        this.t.l.remove(this.s);
    }
}
