package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class I5 implements Runnable {
    public final /* synthetic */ RecyclerView s;

    public I5(RecyclerView recyclerView) {
        this.s = recyclerView;
    }

    @Override // java.lang.Runnable
    public void run() {
        Q5 q5 = this.s.a0;
        if (q5 != null) {
            C1087l5 c1087l5 = (C1087l5) q5;
            boolean z = !c1087l5.h.isEmpty();
            boolean z2 = !c1087l5.j.isEmpty();
            boolean z3 = !c1087l5.k.isEmpty();
            boolean z4 = !c1087l5.i.isEmpty();
            if (z || z2 || z4 || z3) {
                Iterator it = c1087l5.h.iterator();
                if (it.hasNext()) {
                    throw null;
                }
                c1087l5.h.clear();
                if (z2) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(c1087l5.j);
                    c1087l5.m.add(arrayList);
                    c1087l5.j.clear();
                    RunnableC0808g5 runnableC0808g5 = new RunnableC0808g5(c1087l5, arrayList);
                    if (z) {
                        throw null;
                    }
                    runnableC0808g5.run();
                }
                if (z3) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.addAll(c1087l5.k);
                    c1087l5.n.add(arrayList2);
                    c1087l5.k.clear();
                    RunnableC0864h5 runnableC0864h5 = new RunnableC0864h5(c1087l5, arrayList2);
                    if (z) {
                        throw null;
                    }
                    runnableC0864h5.run();
                }
                if (z4) {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.addAll(c1087l5.i);
                    c1087l5.l.add(arrayList3);
                    c1087l5.i.clear();
                    RunnableC0920i5 runnableC0920i5 = new RunnableC0920i5(c1087l5, arrayList3);
                    if (z || z2 || z3) {
                        Math.max(z2 ? c1087l5.e : 0L, z3 ? c1087l5.f : 0L);
                        throw null;
                    }
                    runnableC0920i5.run();
                }
            }
        }
        this.s.w0 = false;
    }
}
