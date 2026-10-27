package x;

import l3.C1764u;
import w.AbstractC2571b;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: x.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2657x implements InterfaceC2639i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B8.t f23655a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D0 f23656b;
    public final Object c;
    public final AbstractC2652s d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC2652s f23657e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AbstractC2652s f23658f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f23659g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f23660h;

    public C2657x(C2658y c2658y, D0 d02, Object obj, AbstractC2652s abstractC2652s) {
        AbstractC2652s abstractC2652s2 = abstractC2652s;
        B8.t tVar = new B8.t(c2658y.f23670a, 27);
        this.f23655a = tVar;
        this.f23656b = d02;
        this.c = obj;
        AbstractC2652s abstractC2652s3 = (AbstractC2652s) d02.f23394a.b(obj);
        this.d = abstractC2652s3;
        this.f23657e = AbstractC2631e.i(abstractC2652s);
        if (((AbstractC2652s) tVar.f807m) == null) {
            tVar.f807m = abstractC2652s3.c();
        }
        AbstractC2652s abstractC2652s4 = (AbstractC2652s) tVar.f807m;
        String str = "targetVector";
        if (abstractC2652s4 == null) {
            A7.m.j("targetVector");
            throw null;
        }
        int iB = abstractC2652s4.b();
        int i7 = 0;
        while (i7 < iB) {
            AbstractC2652s abstractC2652s5 = (AbstractC2652s) tVar.f807m;
            if (abstractC2652s5 == null) {
                A7.m.j(str);
                throw null;
            }
            float fA = abstractC2652s3.a(i7);
            float fA2 = abstractC2652s2.a(i7);
            w.J j3 = (w.J) ((C1764u) tVar.f808n).f18608m;
            double dB = j3.b(fA2);
            double d = w.K.f23076a;
            float f10 = j3.f23074a * j3.f23075b;
            abstractC2652s5.e((Math.signum(fA2) * ((float) (Math.exp((d / (d - 1.0d)) * dB) * ((double) f10)))) + fA, i7);
            i7++;
            abstractC2652s2 = abstractC2652s;
            abstractC2652s3 = abstractC2652s3;
            str = str;
        }
        String str2 = str;
        AbstractC2652s abstractC2652s6 = (AbstractC2652s) tVar.f807m;
        if (abstractC2652s6 == null) {
            A7.m.j(str2);
            throw null;
        }
        this.f23659g = d02.f23395b.b(abstractC2652s6);
        B8.t tVar2 = this.f23655a;
        AbstractC2652s abstractC2652s7 = this.d;
        if (((AbstractC2652s) tVar2.f809p) == null) {
            tVar2.f809p = abstractC2652s7.c();
        }
        AbstractC2652s abstractC2652s8 = (AbstractC2652s) tVar2.f809p;
        if (abstractC2652s8 == null) {
            A7.m.j("velocityVector");
            throw null;
        }
        int iB2 = abstractC2652s8.b();
        long jMax = 0;
        for (int i10 = 0; i10 < iB2; i10++) {
            abstractC2652s7.getClass();
            jMax = Math.max(jMax, ((long) (Math.exp(((w.J) ((C1764u) tVar2.f808n).f18608m).b(abstractC2652s.a(i10)) / (((double) w.K.f23076a) - 1.0d)) * 1000.0d)) * 1000000);
        }
        this.f23660h = jMax;
        AbstractC2652s abstractC2652sI = AbstractC2631e.i(this.f23655a.Z(jMax, this.d, abstractC2652s));
        this.f23658f = abstractC2652sI;
        int iB3 = abstractC2652sI.b();
        for (int i11 = 0; i11 < iB3; i11++) {
            AbstractC2652s abstractC2652s9 = this.f23658f;
            float fA3 = abstractC2652s9.a(i11);
            this.f23655a.getClass();
            this.f23655a.getClass();
            abstractC2652s9.e(AbstractC2673a.u(fA3, -0.0f, 0.0f), i11);
        }
    }

    @Override // x.InterfaceC2639i
    public final boolean a() {
        return false;
    }

    @Override // x.InterfaceC2639i
    public final Object b(long j3) {
        if (g(j3)) {
            return this.f23659g;
        }
        InterfaceC2782k interfaceC2782k = this.f23656b.f23395b;
        B8.t tVar = this.f23655a;
        AbstractC2652s abstractC2652s = (AbstractC2652s) tVar.o;
        AbstractC2652s abstractC2652s2 = this.d;
        if (abstractC2652s == null) {
            tVar.o = abstractC2652s2.c();
        }
        AbstractC2652s abstractC2652s3 = (AbstractC2652s) tVar.o;
        String str = "valueVector";
        if (abstractC2652s3 == null) {
            A7.m.j("valueVector");
            throw null;
        }
        int iB = abstractC2652s3.b();
        int i7 = 0;
        while (i7 < iB) {
            AbstractC2652s abstractC2652s4 = (AbstractC2652s) tVar.o;
            if (abstractC2652s4 == null) {
                A7.m.j(str);
                throw null;
            }
            float fA = abstractC2652s2.a(i7);
            long j10 = j3 / 1000000;
            w.I iA = ((w.J) ((C1764u) tVar.f808n).f18608m).a(this.f23657e.a(i7));
            String str2 = str;
            long j11 = iA.c;
            abstractC2652s4.e((Math.signum(iA.f23072a) * iA.f23073b * AbstractC2571b.a(j11 > 0 ? j10 / j11 : 1.0f).f23086a) + fA, i7);
            i7++;
            str = str2;
        }
        String str3 = str;
        AbstractC2652s abstractC2652s5 = (AbstractC2652s) tVar.o;
        if (abstractC2652s5 != null) {
            return interfaceC2782k.b(abstractC2652s5);
        }
        A7.m.j(str3);
        throw null;
    }

    @Override // x.InterfaceC2639i
    public final long c() {
        return this.f23660h;
    }

    @Override // x.InterfaceC2639i
    public final D0 d() {
        return this.f23656b;
    }

    @Override // x.InterfaceC2639i
    public final Object e() {
        return this.f23659g;
    }

    @Override // x.InterfaceC2639i
    public final AbstractC2652s f(long j3) {
        if (g(j3)) {
            return this.f23658f;
        }
        return this.f23655a.Z(j3, this.d, this.f23657e);
    }
}
