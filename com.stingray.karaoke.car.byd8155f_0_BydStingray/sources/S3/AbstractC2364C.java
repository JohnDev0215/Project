package s3;

import A.s1;
import F1.C0254m;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import k3.C1648i0;

/* JADX INFO: renamed from: s3.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2364C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C2404x f22171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f22172b;
    public long c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f22173e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f22174f;

    public static void b(U u4) {
        RecyclerView recyclerView;
        int i7 = u4.f22226j;
        if (u4.g() || (i7 & 4) != 0 || (recyclerView = u4.f22232r) == null) {
            return;
        }
        recyclerView.D(u4);
    }

    public abstract boolean a(U u4, U u10, C0254m c0254m, C0254m c0254m2);

    public final void c(U u4) {
        C2404x c2404x = this.f22171a;
        if (c2404x != null) {
            boolean z6 = true;
            u4.o(true);
            if (u4.f22224h != null && u4.f22225i == null) {
                u4.f22224h = null;
            }
            u4.f22225i = null;
            if ((u4.f22226j & 16) != 0) {
                return;
            }
            RecyclerView recyclerView = c2404x.f22362a;
            recyclerView.a0();
            h3.y yVar = recyclerView.f12143q;
            C2404x c2404x2 = (C2404x) yVar.f15499m;
            RecyclerView recyclerView2 = c2404x2.f22362a;
            View view = u4.f22219a;
            int iIndexOfChild = recyclerView2.indexOfChild(view);
            if (iIndexOfChild == -1) {
                yVar.P(view);
            } else {
                s1 s1Var = (s1) yVar.f15500n;
                if (s1Var.s(iIndexOfChild)) {
                    s1Var.v(iIndexOfChild);
                    yVar.P(view);
                    c2404x2.h(iIndexOfChild);
                } else {
                    z6 = false;
                }
            }
            if (z6) {
                U uG = RecyclerView.G(view);
                C1648i0 c1648i0 = recyclerView.f12138n;
                c1648i0.l(uG);
                c1648i0.i(uG);
            }
            recyclerView.b0(!z6);
            if (z6 || !u4.k()) {
                return;
            }
            recyclerView.removeDetachedView(view, false);
        }
    }

    public abstract void d(U u4);

    public abstract void e();

    public abstract boolean f();
}
