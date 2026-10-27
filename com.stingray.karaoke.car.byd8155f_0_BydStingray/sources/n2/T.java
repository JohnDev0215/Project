package n2;

import e2.f0;
import java.util.ArrayList;
import u2.AbstractC2498a;
import u2.C2520x;

/* JADX INFO: loaded from: classes.dex */
public final class T implements L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2520x f19582a;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f19584e;
    public final ArrayList c = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f19583b = new Object();

    public T(AbstractC2498a abstractC2498a, boolean z6) {
        this.f19582a = new C2520x(abstractC2498a, z6);
    }

    @Override // n2.L
    public final Object a() {
        return this.f19583b;
    }

    @Override // n2.L
    public final f0 b() {
        return this.f19582a.o;
    }
}
