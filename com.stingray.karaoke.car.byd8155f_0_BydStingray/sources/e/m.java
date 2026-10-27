package E;

import H7.InterfaceC0317b;
import T.Q0;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m extends A7.u implements H7.r {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f1363s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(int i7, int i10, Class cls, Object obj, String str, String str2) {
        super(obj, cls, str, str2, i7);
        this.f1363s = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        return get();
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0317b f() {
        return A7.A.f443a.g(this);
    }

    @Override // H7.r
    public final Object get() {
        switch (this.f1363s) {
            case 0:
                return ((Q0) this.f455m).getValue();
            case 1:
                return ((Q0) this.f455m).getValue();
            case 2:
                return ((Q0) this.f455m).getValue();
            default:
                return this.f455m.getClass().getSimpleName();
        }
    }

    @Override // H7.w
    public final H7.q c() {
        return ((H7.r) l()).c();
    }
}
