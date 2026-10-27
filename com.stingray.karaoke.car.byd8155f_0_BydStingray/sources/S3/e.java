package S3;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class e extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7896m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h f7897n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(h hVar, int i7, int i10) {
        super(1);
        this.f7896m = i10;
        this.f7897n = hVar;
        this.o = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f7896m) {
            case 0:
                return Boolean.valueOf(h.b(this.f7897n, this.o, ((Number) obj).longValue()));
            default:
                return Boolean.valueOf(h.b(this.f7897n, this.o, ((Number) obj).longValue()));
        }
    }
}
