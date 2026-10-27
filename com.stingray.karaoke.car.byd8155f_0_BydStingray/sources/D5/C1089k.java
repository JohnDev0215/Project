package d5;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: renamed from: d5.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1089k extends C1081c implements ListIterator {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1090l f13438p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1089k(C1090l c1090l) {
        super(c1090l);
        this.f13438p = c1090l;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        C1090l c1090l = this.f13438p;
        boolean zIsEmpty = c1090l.isEmpty();
        b().add(obj);
        c1090l.f13442q.f13401p++;
        if (zIsEmpty) {
            c1090l.a();
        }
    }

    public final ListIterator b() {
        a();
        return (ListIterator) this.f13406m;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return b().hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return b().nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return b().previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return b().previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        b().set(obj);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1089k(C1090l c1090l, int i7) {
        super(c1090l, ((List) c1090l.f13439m).listIterator(i7));
        this.f13438p = c1090l;
    }
}
