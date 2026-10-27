package T;

import S8.InterfaceC0725w;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: T.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0748j0 implements X, InterfaceC0725w {
    public final InterfaceC2194h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ X f8264m;

    public C0748j0(X x8, InterfaceC2194h interfaceC2194h) {
        this.l = interfaceC2194h;
        this.f8264m = x8;
    }

    @Override // S8.InterfaceC0725w
    public final InterfaceC2194h getCoroutineContext() {
        return this.l;
    }

    @Override // T.Q0
    public final Object getValue() {
        return this.f8264m.getValue();
    }

    @Override // T.X
    public final void setValue(Object obj) {
        this.f8264m.setValue(obj);
    }
}
