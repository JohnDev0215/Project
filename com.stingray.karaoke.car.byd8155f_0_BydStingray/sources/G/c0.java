package G;

import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2551m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ d0.k f2552n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c0(d0.k kVar, int i7) {
        super(1);
        this.f2551m = i7;
        this.f2552n = kVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2551m) {
            case 0:
                d0.k kVar = this.f2552n;
                return Boolean.valueOf(kVar != null ? kVar.b(obj) : true);
            default:
                return new e0(this.f2552n, (Map) obj);
        }
    }
}
