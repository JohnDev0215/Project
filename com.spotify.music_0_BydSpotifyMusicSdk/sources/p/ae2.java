package p;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class ae2 extends c22 {
    public boolean a = false;
    public final /* synthetic */ mr1 b;

    public ae2(mr1 mr1Var) {
        this.b = mr1Var;
    }

    @Override // p.c22
    public final void a(RecyclerView recyclerView, int i) {
        if (i == 0 && this.a) {
            this.a = false;
            this.b.f();
        }
    }

    @Override // p.c22
    public final void b(RecyclerView recyclerView, int i, int i2) {
        if (i == 0 && i2 == 0) {
            return;
        }
        this.a = true;
    }
}
