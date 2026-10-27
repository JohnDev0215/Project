package i;

import A7.o;
import T.C0761q;
import h6.n;
import m7.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: i.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1394a extends o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f15636m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f15637n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f15638p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1394a(boolean z6, InterfaceC2772a interfaceC2772a, int i7, int i10) {
        super(2);
        this.f15636m = z6;
        this.f15637n = interfaceC2772a;
        this.o = i7;
        this.f15638p = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int i7 = this.o | 1;
        InterfaceC2772a interfaceC2772a = this.f15637n;
        int i10 = this.f15638p;
        n.a(this.f15636m, interfaceC2772a, (C0761q) obj, i7, i10);
        return q.f19267a;
    }
}
