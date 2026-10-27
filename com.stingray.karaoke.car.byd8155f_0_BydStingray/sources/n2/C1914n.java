package n2;

import c5.InterfaceC1031h;
import u2.InterfaceC2491B;

/* JADX INFO: renamed from: n2.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1914n implements InterfaceC1031h {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f19699m;

    public /* synthetic */ C1914n(Object obj, int i7) {
        this.l = i7;
        this.f19699m = obj;
    }

    @Override // c5.InterfaceC1031h
    public final Object get() {
        switch (this.l) {
            case 0:
                return (u2.V) this.f19699m;
            case 1:
                return (w2.r) this.f19699m;
            case 2:
                return Boolean.valueOf(((D) this.f19699m).f19499N);
            default:
                try {
                    return (InterfaceC2491B) ((Class) this.f19699m).getConstructor(null).newInstance(null);
                } catch (Exception e10) {
                    throw new IllegalStateException(e10);
                }
        }
    }
}
