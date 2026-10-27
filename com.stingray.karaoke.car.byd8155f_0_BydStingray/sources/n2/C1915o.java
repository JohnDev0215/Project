package n2;

import android.content.Context;
import android.os.Looper;
import c5.InterfaceC1031h;
import e2.C1195c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: renamed from: n2.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1915o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f19700a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h2.v f19701b;
    public final C1902b c;
    public InterfaceC1031h d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public InterfaceC1031h f19702e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C1902b f19703f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Looper f19704g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f19705h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1195c f19706i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f19707j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f19708k;
    public final b0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f19709m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f19710n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C1908h f19711p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f19712q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final long f19713r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f19714s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f19715t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final String f19716u;

    public C1915o(Context context) {
        C1902b c1902b = new C1902b(context, 1);
        C1902b c1902b2 = new C1902b(context, 2);
        C1902b c1902b3 = new C1902b(context, 3);
        C1902b c1902b4 = new C1902b(context, 4);
        context.getClass();
        this.f19700a = context;
        this.c = c1902b;
        this.d = c1902b2;
        this.f19702e = c1902b3;
        this.f19703f = c1902b4;
        this.f19704g = AbstractC1343A.q();
        this.f19706i = C1195c.f14046g;
        this.f19707j = 1;
        this.f19708k = true;
        this.l = b0.c;
        this.f19709m = 5000L;
        this.f19710n = 15000L;
        this.o = 3000L;
        this.f19711p = new C1908h(AbstractC1343A.H(20L), AbstractC1343A.H(500L));
        this.f19701b = h2.v.f15245a;
        this.f19712q = 500L;
        this.f19713r = 2000L;
        this.f19714s = true;
        this.f19716u = "";
        this.f19705h = -1000;
    }

    public final C1923x a() {
        AbstractC1346c.j(!this.f19715t);
        this.f19715t = true;
        int i7 = AbstractC1343A.f15195a;
        return new C1923x(this);
    }
}
