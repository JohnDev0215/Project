package n6;

import T.C0732b0;
import T.C0734c0;
import T.C0735d;
import V8.f0;
import com.stingray.karaoke.scoring.NoteResult;
import java.util.ArrayList;
import java.util.Iterator;
import t6.C2445f;

/* JADX INFO: renamed from: n6.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1972e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f20058a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1967H f20059b;
    public final u c;
    public final x d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2445f f20060e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f20061f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f20062g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f20063h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f20064i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f20065j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f20066k;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public y f20067m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbstractC1961B f20068n;
    public C1960A o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f20069p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C1960A f20070q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f20071r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f20072s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f20073t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f20074u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C0734c0 f20075v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0734c0 f20076w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Object f20077x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C0732b0 f20078y;

    public C1972e(z zVar, C1967H c1967h, u uVar) {
        A7.m.f(zVar, "midiManager");
        A7.m.f(c1967h, "scoreManager");
        A7.m.f(uVar, "microphoneManager");
        this.f20058a = zVar;
        this.f20059b = c1967h;
        this.c = uVar;
        x xVar = new x();
        xVar.f20146b = 128;
        xVar.d = new G7.a(xVar.f20145a, 128);
        xVar.f20147e = -1.0f;
        this.d = xVar;
        this.f20060e = new C2445f();
        this.f20069p = -1.0f;
        this.f20072s = -1.0f;
        this.f20075v = C0735d.N(0L);
        this.f20076w = C0735d.N(0L);
        this.f20077x = n7.u.l;
        this.f20078y = C0735d.M(0);
    }

    public final w a() {
        return (w) ((m7.i) ((f0) this.f20058a.f20152b.l).getValue()).f19259m;
    }

    public final void b() {
        ArrayList<C1960A> arrayList;
        C1967H c1967h = this.f20059b;
        f0 f0Var = c1967h.f20009i;
        f0Var.getClass();
        f0Var.m(null, 0);
        f0 f0Var2 = c1967h.o;
        f0Var2.getClass();
        f0Var2.m(null, 0);
        f0 f0Var3 = c1967h.f20015q;
        f0Var3.getClass();
        f0Var3.m(null, 0);
        f0 f0Var4 = c1967h.f20017s;
        f0Var4.getClass();
        f0Var4.m(null, 1);
        f0 f0Var5 = c1967h.f20019u;
        f0Var5.getClass();
        f0Var5.m(null, 0);
        f0 f0Var6 = c1967h.f20012m;
        Float fValueOf = Float.valueOf(0.0f);
        f0Var6.getClass();
        f0Var6.m(null, fValueOf);
        f0 f0Var7 = c1967h.f20023y;
        Boolean bool = Boolean.FALSE;
        f0Var7.getClass();
        f0Var7.m(null, bool);
        c1967h.f20008h.clear();
        f0 f0Var8 = c1967h.f20021w;
        f0Var8.getClass();
        f0Var8.m(null, "");
        f0 f0Var9 = c1967h.f20002A;
        f0Var9.getClass();
        f0Var9.m(null, "");
        w wVarA = a();
        if (wVarA != null && (arrayList = wVarA.f20138a) != null) {
            for (C1960A c1960a : arrayList) {
                c1960a.getClass();
                c1960a.f19981h = NoteResult.UNKNOWN;
                c1960a.f19982i = false;
                ArrayList arrayList2 = new ArrayList();
                Iterator it = c1960a.f19984k.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new m7.i(((m7.i) it.next()).l, NoteResult.UNKNOWN));
                }
                c1960a.f19984k = arrayList2;
            }
        }
        this.f20070q = null;
        this.f20067m = null;
        this.f20068n = null;
        this.o = null;
        this.f20069p = -1.0f;
        this.f20072s = -1.0f;
        this.f20074u = false;
        this.f20075v.setValue(0L);
        this.f20061f = 0.0f;
        C0732b0 c0732b0 = this.f20078y;
        c0732b0.j(c0732b0.i() + 1);
    }
}
