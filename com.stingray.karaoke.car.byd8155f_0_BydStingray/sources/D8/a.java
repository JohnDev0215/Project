package D8;

import A7.A;
import A7.B;
import java.util.Iterator;
import java.util.List;
import o8.C2058c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public class a implements R7.h {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f1215m;
    public final E8.i l;

    static {
        B b10 = A.f443a;
        f1215m = new H7.w[]{b10.h(new A7.s(b10.b(a.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    }

    public a(E8.o oVar, InterfaceC2772a interfaceC2772a) {
        A7.m.f(oVar, "storageManager");
        this.l = new E8.i((E8.l) oVar, interfaceC2772a);
    }

    @Override // R7.h
    public final R7.b e(C2058c c2058c) {
        return P8.n.v(this, c2058c);
    }

    @Override // R7.h
    public boolean isEmpty() {
        return ((List) C7.a.N(this.l, f1215m[0])).isEmpty();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return ((List) C7.a.N(this.l, f1215m[0])).iterator();
    }

    @Override // R7.h
    public final boolean k(C2058c c2058c) {
        return P8.n.A(this, c2058c);
    }
}
