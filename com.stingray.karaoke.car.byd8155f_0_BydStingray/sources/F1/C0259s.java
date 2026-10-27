package F1;

import A7.C0054b;
import java.util.ArrayList;
import java.util.Iterator;
import n7.AbstractC1998m;
import n7.AbstractC2004s;

/* JADX INFO: renamed from: F1.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0259s implements Iterator, B7.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Iterator f2346m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f2347n;

    public C0259s(C0054b c0054b) {
        this.l = 0;
        this.f2347n = new ArrayList();
        this.f2346m = c0054b;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
        }
        return this.f2346m.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                Object next = this.f2346m.next();
                Iterator it = (Iterator) I.f2305m.b(next);
                ArrayList arrayList = (ArrayList) this.f2347n;
                if (it == null || !it.hasNext()) {
                    while (!this.f2346m.hasNext() && !arrayList.isEmpty()) {
                        this.f2346m = (Iterator) AbstractC1998m.h0(arrayList);
                        AbstractC2004s.P(arrayList);
                    }
                } else {
                    arrayList.add(this.f2346m);
                    this.f2346m = it;
                }
                return next;
            default:
                return ((P8.r) this.f2347n).f5485b.b(this.f2346m.next());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public C0259s(P8.r rVar) {
        this.l = 1;
        this.f2347n = rVar;
        this.f2346m = rVar.f5484a.iterator();
    }
}
