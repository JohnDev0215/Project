package T;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class A0 implements Iterable, B7.a {
    public final z0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f8154m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f8155n;

    public A0(z0 z0Var, int i7, int i10) {
        this.l = z0Var;
        this.f8154m = i7;
        this.f8155n = i10;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int i7;
        ArrayList arrayList;
        int iU;
        z0 z0Var = this.l;
        if (z0Var.f8404r != this.f8155n) {
            throw new ConcurrentModificationException();
        }
        HashMap map = z0Var.f8406t;
        C0733c c0733c = null;
        int i10 = this.f8154m;
        if (map != null) {
            if (z0Var.f8403q) {
                C0735d.y("use active SlotWriter to crate an anchor for location instead");
                throw null;
            }
            if (i10 >= 0 && i10 < (i7 = z0Var.f8400m) && (iU = C0735d.U((arrayList = z0Var.f8405s), i10, i7)) >= 0) {
                c0733c = (C0733c) arrayList.get(iU);
            }
            if (c0733c != null) {
            }
        }
        return new J(z0Var, i10 + 1, z0Var.l[(i10 * 5) + 3] + i10);
    }
}
