package G;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class D implements Comparator {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ B5.u f2498m;

    public /* synthetic */ D(B5.u uVar, int i7) {
        this.l = i7;
        this.f2498m = uVar;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                Object key = ((N) obj).getKey();
                B5.u uVar = this.f2498m;
                return h6.f.b(Integer.valueOf(uVar.a(key)), Integer.valueOf(uVar.a(((N) obj2).getKey())));
            default:
                Object key2 = ((N) obj2).getKey();
                B5.u uVar2 = this.f2498m;
                return h6.f.b(Integer.valueOf(uVar2.a(key2)), Integer.valueOf(uVar2.a(((N) obj).getKey())));
        }
    }
}
