package androidx.datastore.preferences.protobuf;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class f0 implements Iterator {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f11841m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f11842n;
    public Iterator o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ AbstractMap f11843p;

    public /* synthetic */ f0(AbstractMap abstractMap, int i7) {
        this.l = i7;
        this.f11843p = abstractMap;
    }

    public final Iterator a() {
        switch (this.l) {
            case 0:
                if (this.o == null) {
                    this.o = ((C0938a0) this.f11843p).f11827n.entrySet().iterator();
                }
                break;
            default:
                if (this.o == null) {
                    this.o = ((p8.z) this.f11843p).f21004n.entrySet().iterator();
                }
                break;
        }
        return this.o;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                int i7 = this.f11841m + 1;
                C0938a0 c0938a0 = (C0938a0) this.f11843p;
                if (i7 >= c0938a0.f11826m.size()) {
                    if (c0938a0.f11827n.isEmpty() || !a().hasNext()) {
                    }
                }
                break;
            default:
                if (this.f11841m + 1 < ((p8.z) this.f11843p).f21003m.size() || a().hasNext()) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                this.f11842n = true;
                int i7 = this.f11841m + 1;
                this.f11841m = i7;
                C0938a0 c0938a0 = (C0938a0) this.f11843p;
                if (i7 >= c0938a0.f11826m.size()) {
                }
                break;
            default:
                this.f11842n = true;
                int i10 = this.f11841m + 1;
                this.f11841m = i10;
                p8.z zVar = (p8.z) this.f11843p;
                if (i10 >= zVar.f21003m.size()) {
                }
                break;
        }
        return (Map.Entry) a().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        AbstractMap abstractMap = this.f11843p;
        switch (this.l) {
            case 0:
                if (!this.f11842n) {
                    throw new IllegalStateException("remove() was called before next()");
                }
                this.f11842n = false;
                int i7 = C0938a0.f11825r;
                C0938a0 c0938a0 = (C0938a0) abstractMap;
                c0938a0.c();
                if (this.f11841m >= c0938a0.f11826m.size()) {
                    a().remove();
                    return;
                }
                int i10 = this.f11841m;
                this.f11841m = i10 - 1;
                c0938a0.h(i10);
                return;
            default:
                if (!this.f11842n) {
                    throw new IllegalStateException("remove() was called before next()");
                }
                this.f11842n = false;
                int i11 = p8.z.f21002q;
                p8.z zVar = (p8.z) abstractMap;
                zVar.c();
                if (this.f11841m >= zVar.f21003m.size()) {
                    a().remove();
                    return;
                }
                int i12 = this.f11841m;
                this.f11841m = i12 - 1;
                zVar.g(i12);
                return;
        }
    }
}
