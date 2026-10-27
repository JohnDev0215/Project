package Q5;

import F0.C0223q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class W1 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6617m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6618n;

    public /* synthetic */ W1(int i7, Object obj, boolean z6) {
        this.l = i7;
        this.f6618n = obj;
        this.f6617m = z6;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                new C0223q(1, (d6.j) this.f6618n, d6.j.class, "onLikeTap", "onLikeTap(Lkotlin/jvm/functions/Function0;)V", 0, 9).b(new C0553c(4, this.f6617m));
                break;
            default:
                ((InterfaceC2782k) this.f6618n).b(Boolean.valueOf(!this.f6617m));
                break;
        }
        return m7.q.f19267a;
    }
}
