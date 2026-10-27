package P8;

import java.util.Iterator;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class p implements Iterable, B7.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f5482m;

    public /* synthetic */ p(Object obj, int i7) {
        this.l = i7;
        this.f5482m = obj;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                return ((k) this.f5482m).iterator();
            case 1:
                return A7.m.h((Object[]) this.f5482m);
            default:
                return new b((Iterator) ((InterfaceC2772a) this.f5482m).a());
        }
    }
}
