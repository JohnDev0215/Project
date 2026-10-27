package d1;

import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1057a extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1057a f13276n = new C1057a(1, 0);
    public static final C1057a o = new C1057a(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1057a f13277p = new C1057a(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1057a f13278q = new C1057a(1, 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13279m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1057a(int i7, int i10) {
        super(i7);
        this.f13279m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f13279m) {
            case 0:
                i iVar = (i) obj;
                iVar.getHandler().post(new A2.l(iVar.f13312y, 11));
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
        return q.f19267a;
    }
}
