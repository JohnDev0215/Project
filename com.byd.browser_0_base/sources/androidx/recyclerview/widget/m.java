package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import java.util.ArrayList;

/* JADX INFO: compiled from: ItemTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class m implements Runnable {
    public final /* synthetic */ l.f e;
    public final /* synthetic */ l f;

    public m(l lVar, l.f fVar, int i) {
        this.f = lVar;
        this.e = fVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        l lVar = this.f;
        RecyclerView recyclerView = lVar.r;
        if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
            return;
        }
        l.f fVar = this.e;
        if (fVar.o) {
            return;
        }
        RecyclerView.a0 a0Var = fVar.i;
        if (a0Var.e() != -1) {
            RecyclerView.j itemAnimator = lVar.r.getItemAnimator();
            if (itemAnimator == null || !itemAnimator.k()) {
                ArrayList arrayList = lVar.p;
                int size = arrayList.size();
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    if (!((l.f) arrayList.get(i)).p) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    lVar.m.j(a0Var);
                    return;
                }
            }
            lVar.r.post(this);
        }
    }
}
