package b7;

import c7.InterfaceC1035a;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class m implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12305a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ n f12306b;

    public /* synthetic */ m(n nVar, int i7) {
        this.f12305a = i7;
        this.f12306b = nVar;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        switch (this.f12305a) {
            case 0:
                r.a1(this.f12306b.l);
                break;
            case 1:
                r.b1(this.f12306b.l, (j7.d) objArr[0]);
                break;
            case 2:
                if (!this.f12306b.l.f12315n) {
                    super/*A9.b*/.N0("connect_error", objArr[0]);
                }
                break;
            default:
                r rVar = this.f12306b.l;
                String str = objArr.length > 0 ? (String) objArr[0] : null;
                Logger logger = r.f12313x;
                rVar.f1(str);
                break;
        }
    }
}
