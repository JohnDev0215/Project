package T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import u.C2476p;

/* JADX INFO: loaded from: classes.dex */
public final class z0 implements Iterable, B7.a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8400m;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f8402p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8403q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f8404r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public HashMap f8406t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C2476p f8407u;
    public int[] l = new int[0];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object[] f8401n = new Object[0];

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList f8405s = new ArrayList();

    public final int d(C0733c c0733c) {
        if (this.f8403q) {
            C0735d.y("Use active SlotWriter to determine anchor location instead");
            throw null;
        }
        if (c0733c.a()) {
            return c0733c.f8235a;
        }
        C0735d.X("Anchor refers to a group that was removed");
        throw null;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new J(this, 0, this.f8400m);
    }

    public final void l() {
        this.f8406t = new HashMap();
    }

    public final y0 q() {
        if (this.f8403q) {
            throw new IllegalStateException("Cannot read while a writer is pending");
        }
        this.f8402p++;
        return new y0(this);
    }

    public final B0 r() {
        if (this.f8403q) {
            C0735d.y("Cannot start a writer when another writer is pending");
            throw null;
        }
        if (this.f8402p > 0) {
            C0735d.y("Cannot start a writer when a reader is pending");
            throw null;
        }
        this.f8403q = true;
        this.f8404r++;
        return new B0(this);
    }
}
