package G;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class C implements Comparator {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ K f2497m;

    public /* synthetic */ C(K k10, int i7) {
        this.l = i7;
        this.f2497m = k10;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                Object key = ((N) obj).getKey();
                K k10 = this.f2497m;
                return h6.f.b(Integer.valueOf(k10.a(key)), Integer.valueOf(k10.a(((N) obj2).getKey())));
            default:
                Object key2 = ((N) obj2).getKey();
                K k11 = this.f2497m;
                return h6.f.b(Integer.valueOf(k11.a(key2)), Integer.valueOf(k11.a(((N) obj).getKey())));
        }
    }
}
