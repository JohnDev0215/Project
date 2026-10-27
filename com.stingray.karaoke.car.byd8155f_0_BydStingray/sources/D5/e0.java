package d5;

import java.util.AbstractMap;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends I {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f0 f13418n;

    public e0(f0 f0Var) {
        this.f13418n = f0Var;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        f0 f0Var = this.f13418n;
        P8.n.p(i7, f0Var.f13422r);
        int i10 = i7 * 2;
        int i11 = f0Var.f13421q;
        Object[] objArr = f0Var.f13420p;
        Object obj = objArr[i10 + i11];
        Objects.requireNonNull(obj);
        Object obj2 = objArr[i10 + (i11 ^ 1)];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // d5.AbstractC1075D
    public final boolean r() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13418n.f13422r;
    }
}
