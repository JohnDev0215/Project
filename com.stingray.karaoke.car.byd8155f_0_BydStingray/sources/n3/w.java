package n3;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class w implements Comparable {
    public final y l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Bundle f19908m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f19909n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f19910p;

    public w(y yVar, Bundle bundle, boolean z6, int i7, boolean z10) {
        A7.m.f(yVar, "destination");
        this.l = yVar;
        this.f19908m = bundle;
        this.f19909n = z6;
        this.o = i7;
        this.f19910p = z10;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(w wVar) {
        A7.m.f(wVar, "other");
        boolean z6 = wVar.f19909n;
        boolean z10 = this.f19909n;
        if (z10 && !z6) {
            return 1;
        }
        if (!z10 && z6) {
            return -1;
        }
        int i7 = this.o - wVar.o;
        if (i7 > 0) {
            return 1;
        }
        if (i7 < 0) {
            return -1;
        }
        Bundle bundle = wVar.f19908m;
        Bundle bundle2 = this.f19908m;
        if (bundle2 != null && bundle == null) {
            return 1;
        }
        if (bundle2 == null && bundle != null) {
            return -1;
        }
        if (bundle2 != null) {
            int size = bundle2.size();
            A7.m.c(bundle);
            int size2 = size - bundle.size();
            if (size2 > 0) {
                return 1;
            }
            if (size2 < 0) {
                return -1;
            }
        }
        boolean z11 = wVar.f19910p;
        boolean z12 = this.f19910p;
        if (!z12 || z11) {
            return (z12 || !z11) ? 0 : -1;
        }
        return 1;
    }
}
