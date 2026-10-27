package S3;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class m extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7953m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n f7954n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(n nVar, int i7) {
        super(0);
        this.f7953m = i7;
        this.f7954n = nVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f7953m) {
            case 0:
                n nVar = this.f7954n;
                return Boolean.valueOf((((O3.a) nVar.f7955m.getValue()) == null && ((Throwable) nVar.f7956n.getValue()) == null) ? false : true);
            case 1:
                return Boolean.valueOf(((Throwable) this.f7954n.f7956n.getValue()) != null);
            case 2:
                n nVar2 = this.f7954n;
                return Boolean.valueOf(((O3.a) nVar2.f7955m.getValue()) == null && ((Throwable) nVar2.f7956n.getValue()) == null);
            default:
                return Boolean.valueOf(((O3.a) this.f7954n.f7955m.getValue()) != null);
        }
    }
}
