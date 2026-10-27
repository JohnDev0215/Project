package P8;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class o implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5481b;

    public /* synthetic */ o(Object obj, int i7) {
        this.f5480a = i7;
        this.f5481b = obj;
    }

    @Override // P8.k
    public final Iterator iterator() {
        switch (this.f5480a) {
            case 0:
                return (Iterator) this.f5481b;
            case 1:
                return A7.m.h((Object[]) this.f5481b);
            case 2:
                return ((Iterable) this.f5481b).iterator();
            default:
                return new x7.b(this);
        }
    }
}
