package a.p.e;

import a.p.e.o;
import androidx.recyclerview.widget.RecyclerView;
import b.c.a.f.q;

/* JADX INFO: loaded from: classes.dex */
public class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o.f f1253a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1254b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ o f1255c;

    public p(o oVar, o.f fVar, int i) {
        this.f1255c = oVar;
        this.f1253a = fVar;
        this.f1254b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        RecyclerView recyclerView = this.f1255c.r;
        if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
            return;
        }
        o.f fVar = this.f1253a;
        if (fVar.k || fVar.f1248e.e() == -1) {
            return;
        }
        RecyclerView.j itemAnimator = this.f1255c.r.getItemAnimator();
        if (itemAnimator == null || !itemAnimator.h()) {
            o oVar = this.f1255c;
            int size = oVar.p.size();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                if (!oVar.p.get(i).l) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                o.d dVar = this.f1255c.m;
                RecyclerView.b0 b0Var = this.f1253a.f1248e;
                b.c.a.f.q qVar = (b.c.a.f.q) dVar;
                if (qVar == null) {
                    throw null;
                }
                b0Var.f1629a.setAlpha(1.0f);
                q.a aVar = qVar.f1746f;
                if (aVar != null) {
                    aVar.a(b0Var.e());
                    return;
                }
                return;
            }
        }
        this.f1255c.r.post(this);
    }
}
