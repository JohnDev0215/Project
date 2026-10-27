package l1;

import g1.q;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class g extends C1731b implements m1.d {

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final q f18431k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final int f18432l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final ArrayList f18433m0;

    public g(q qVar, int i7) {
        super(qVar);
        this.f18433m0 = new ArrayList();
        this.f18431k0 = qVar;
        this.f18432l0 = i7;
    }

    @Override // l1.C1731b, l1.h
    public void a() {
    }

    @Override // l1.C1731b, l1.h
    public final o1.d b() {
        return t();
    }

    public final void r(Object... objArr) {
        Collections.addAll(this.f18433m0, objArr);
    }

    public final void s() {
        super.a();
    }

    public o1.i t() {
        return null;
    }
}
