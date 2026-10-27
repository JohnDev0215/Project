package O;

import B2.C0074n;
import K.X;
import a.AbstractC0904a;

/* JADX INFO: renamed from: O.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0483n implements InterfaceC0478i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0483n f4974b = new C0483n(0);
    public static final C0483n c = new C0483n(1);
    public static final C0074n d = new C0074n(10);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0074n f4975e = new C0074n(11);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0074n f4976f = new C0074n(12);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0074n f4977g = new C0074n(13);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4978a;

    public /* synthetic */ C0483n(int i7) {
        this.f4978a = i7;
    }

    @Override // O.InterfaceC0478i
    public long a(E5.z zVar, int i7) {
        switch (this.f4978a) {
            case 0:
                String str = ((N0.H) zVar.f1842e).f4348a.f4340a.l;
                return AbstractC0904a.q(X.r(i7, str), X.q(i7, str));
            default:
                return ((N0.H) zVar.f1842e).l(i7);
        }
    }
}
