package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class M5 implements InterfaceC0473a5 {
    public final /* synthetic */ RecyclerView a;

    public M5(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public void a(C0529b5 c0529b5) {
        int i = c0529b5.a;
        if (i == 1) {
            RecyclerView recyclerView = this.a;
            recyclerView.B.K(recyclerView, c0529b5.b, c0529b5.d);
            return;
        }
        if (i == 2) {
            RecyclerView recyclerView2 = this.a;
            recyclerView2.B.M(recyclerView2, c0529b5.b, c0529b5.d);
        } else if (i == 4) {
            RecyclerView recyclerView3 = this.a;
            recyclerView3.B.O(recyclerView3, c0529b5.b, c0529b5.d, c0529b5.c);
        } else {
            if (i != 8) {
                return;
            }
            RecyclerView recyclerView4 = this.a;
            recyclerView4.B.L(recyclerView4, c0529b5.b, c0529b5.d, 1);
        }
    }

    public void b(int i, int i2, Object obj) {
        RecyclerView recyclerView = this.a;
        int iD = recyclerView.v.d();
        for (int i3 = 0; i3 < iD; i3++) {
            RecyclerView.p(recyclerView.v.c(i3));
        }
        C0474a6 c0474a6 = recyclerView.s;
        int size = c0474a6.c.size();
        do {
            size--;
            if (size < 0) {
                this.a.u0 = true;
                return;
            }
        } while (((AbstractC0697e6) c0474a6.c.get(size)) == null);
        throw null;
    }

    public void c(int i, int i2) {
        RecyclerView recyclerView = this.a;
        int iD = recyclerView.v.d();
        for (int i3 = 0; i3 < iD; i3++) {
            RecyclerView.p(recyclerView.v.c(i3));
        }
        C0474a6 c0474a6 = recyclerView.s;
        int size = c0474a6.c.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (((AbstractC0697e6) c0474a6.c.get(i4)) != null) {
                throw null;
            }
        }
        recyclerView.requestLayout();
        this.a.t0 = true;
    }

    public void d(int i, int i2) {
        RecyclerView recyclerView = this.a;
        int iD = recyclerView.v.d();
        for (int i3 = 0; i3 < iD; i3++) {
            RecyclerView.p(recyclerView.v.c(i3));
        }
        C0474a6 c0474a6 = recyclerView.s;
        int size = c0474a6.c.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (((AbstractC0697e6) c0474a6.c.get(i4)) != null) {
                throw null;
            }
        }
        recyclerView.requestLayout();
        this.a.t0 = true;
    }
}
