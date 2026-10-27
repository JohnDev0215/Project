package E0;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class h0 implements Comparator {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final h0 f1673m = new h0(0);
    public final /* synthetic */ int l;

    public /* synthetic */ h0(int i7) {
        this.l = i7;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                F f10 = (F) obj;
                F f11 = (F) obj2;
                int iG = A7.m.g(f11.f1489v, f10.f1489v);
                return iG != 0 ? iG : A7.m.g(f10.hashCode(), f11.hashCode());
            default:
                F f12 = (F) obj;
                F f13 = (F) obj2;
                int iG2 = A7.m.g(f12.f1489v, f13.f1489v);
                return iG2 != 0 ? iG2 : A7.m.g(f12.hashCode(), f13.hashCode());
        }
    }
}
