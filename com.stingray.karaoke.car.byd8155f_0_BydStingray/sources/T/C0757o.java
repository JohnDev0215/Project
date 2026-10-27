package T;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: T.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0757o extends AbstractC0764s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f8282b;
    public final boolean c;
    public HashSet d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashSet f8283e = new LinkedHashSet();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0738e0 f8284f = C0735d.O(b0.e.o, S.o);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ C0761q f8285g;

    public C0757o(C0761q c0761q, int i7, boolean z6, boolean z10, B2.P p10) {
        this.f8285g = c0761q;
        this.f8281a = i7;
        this.f8282b = z6;
        this.c = z10;
    }

    @Override // T.AbstractC0764s
    public final void a(C0768u c0768u, b0.b bVar) {
        this.f8285g.f8306b.a(c0768u, bVar);
    }

    @Override // T.AbstractC0764s
    public final void b() {
        C0761q c0761q = this.f8285g;
        c0761q.f8326z--;
    }

    @Override // T.AbstractC0764s
    public final boolean c() {
        return this.f8285g.f8306b.c();
    }

    @Override // T.AbstractC0764s
    public final boolean d() {
        return this.f8282b;
    }

    @Override // T.AbstractC0764s
    public final boolean e() {
        return this.c;
    }

    @Override // T.AbstractC0764s
    public final InterfaceC0744h0 f() {
        return (InterfaceC0744h0) this.f8284f.getValue();
    }

    @Override // T.AbstractC0764s
    public final int g() {
        return this.f8281a;
    }

    @Override // T.AbstractC0764s
    public final InterfaceC2194h h() {
        return this.f8285g.f8306b.h();
    }

    @Override // T.AbstractC0764s
    public final void i(C0768u c0768u) {
        C0761q c0761q = this.f8285g;
        c0761q.f8306b.i(c0761q.f8309g);
        c0761q.f8306b.i(c0768u);
    }

    @Override // T.AbstractC0764s
    public final void j(Set set) {
        HashSet hashSet = this.d;
        if (hashSet == null) {
            hashSet = new HashSet();
            this.d = hashSet;
        }
        hashSet.add(set);
    }

    @Override // T.AbstractC0764s
    public final void k(C0761q c0761q) {
        this.f8283e.add(c0761q);
    }

    @Override // T.AbstractC0764s
    public final void l(C0768u c0768u) {
        this.f8285g.f8306b.l(c0768u);
    }

    @Override // T.AbstractC0764s
    public final void m() {
        this.f8285g.f8326z++;
    }

    @Override // T.AbstractC0764s
    public final void n(C0761q c0761q) {
        HashSet<Set> hashSet = this.d;
        if (hashSet != null) {
            for (Set set : hashSet) {
                A7.m.d(c0761q, "null cannot be cast to non-null type androidx.compose.runtime.ComposerImpl");
                set.remove(c0761q.c);
            }
        }
        LinkedHashSet linkedHashSet = this.f8283e;
        A7.E.a(linkedHashSet);
        linkedHashSet.remove(c0761q);
    }

    @Override // T.AbstractC0764s
    public final void o(C0768u c0768u) {
        this.f8285g.f8306b.o(c0768u);
    }

    public final void p() {
        LinkedHashSet<C0761q> linkedHashSet = this.f8283e;
        if (linkedHashSet.isEmpty()) {
            return;
        }
        HashSet hashSet = this.d;
        if (hashSet != null) {
            for (C0761q c0761q : linkedHashSet) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    ((Set) it.next()).remove(c0761q.c);
                }
            }
        }
        linkedHashSet.clear();
    }
}
