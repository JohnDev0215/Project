package V3;

import R3.f;
import R3.h;
import R3.l;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f9451n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i7, List list) {
        super(list, 3);
        this.f9451n = i7;
    }

    @Override // V3.e
    public final R3.e t0() {
        switch (this.f9451n) {
            case 0:
                return new f(0, (List) this.f476m);
            case 1:
                return new h(0, (List) this.f476m);
            case 2:
                return new f(2, (List) this.f476m);
            case 3:
                return new h(1, (List) this.f476m);
            case 4:
                return new h(2, (List) this.f476m);
            case 5:
                return new l((List) this.f476m);
            default:
                return new f(3, (List) this.f476m);
        }
    }
}
