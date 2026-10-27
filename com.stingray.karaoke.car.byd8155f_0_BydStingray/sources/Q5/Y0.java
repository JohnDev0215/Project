package Q5;

import T.C0735d;
import T.C0761q;
import x3.AbstractC2673a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Y0 implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6657m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6658n;
    public final /* synthetic */ int o;

    public /* synthetic */ Y0(g0.o oVar, boolean z6, int i7) {
        this.f6657m = oVar;
        this.f6658n = z6;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.intValue();
                AbstractC2673a.h(C0735d.Z(this.o | 1), c0761q, this.f6657m, this.f6658n);
                break;
            default:
                num.getClass();
                com.bumptech.glide.c.y(C0735d.Z(this.o | 1), c0761q, this.f6657m, this.f6658n);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ Y0(boolean z6, g0.o oVar, int i7) {
        this.f6658n = z6;
        this.f6657m = oVar;
        this.o = i7;
    }
}
