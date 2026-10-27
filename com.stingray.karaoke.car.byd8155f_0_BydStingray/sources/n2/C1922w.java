package n2;

import e2.f0;
import u2.C2520x;

/* JADX INFO: renamed from: n2.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1922w implements L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f19725a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2520x f19726b;
    public f0 c;

    public C1922w(Object obj, C2520x c2520x) {
        this.f19725a = obj;
        this.f19726b = c2520x;
        this.c = c2520x.o;
    }

    @Override // n2.L
    public final Object a() {
        return this.f19725a;
    }

    @Override // n2.L
    public final f0 b() {
        return this.c;
    }
}
