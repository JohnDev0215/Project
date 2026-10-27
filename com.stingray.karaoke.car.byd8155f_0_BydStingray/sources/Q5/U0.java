package Q5;

import T.C0761q;
import java.util.List;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class U0 implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f6577m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6578n;

    public /* synthetic */ U0(List list, boolean z6, int i7) {
        this.l = i7;
        this.f6577m = list;
        this.f6578n = z6;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.l) {
            case 0:
                ((Number) obj3).intValue();
                A7.m.f((w.q) obj, "$this$AnimatedVisibility");
                w0.c.o(this.f6577m, true, this.f6578n, (C0761q) obj2, 390);
                break;
            default:
                ((Number) obj3).intValue();
                A7.m.f((w.q) obj, "$this$AnimatedVisibility");
                w0.c.o(this.f6577m, false, this.f6578n, (C0761q) obj2, 390);
                break;
        }
        return m7.q.f19267a;
    }
}
