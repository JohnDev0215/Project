package K;

import N0.C0411f;
import T0.C0775a;
import a.AbstractC0904a;
import n7.AbstractC1999n;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class D extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f3132m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f3133n;
    public final /* synthetic */ C0331b0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ T0.y f3134p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D(boolean z6, boolean z10, C0331b0 c0331b0, L0.j jVar, T0.y yVar) {
        super(1);
        this.f3132m = z6;
        this.f3133n = z10;
        this.o = c0331b0;
        this.f3134p = yVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C0411f c0411f = (C0411f) obj;
        if (this.f3132m || !this.f3133n) {
            return Boolean.FALSE;
        }
        C0331b0 c0331b0 = this.o;
        T0.E e10 = c0331b0.f3222e;
        C0365x c0365x = c0331b0.f3235t;
        m7.q qVar = null;
        if (e10 != null) {
            T0.y yVarN = c0331b0.d.n(AbstractC1999n.D(new T0.k(), new C0775a(c0411f, 1)));
            e10.a(null, yVarN);
            c0365x.b(yVarN);
            qVar = m7.q.f19267a;
        }
        if (qVar == null) {
            T0.y yVar = this.f3134p;
            String str = yVar.f8481a.l;
            int i7 = N0.K.c;
            long j3 = yVar.f8482b;
            int i10 = (int) (j3 >> 32);
            int i11 = (int) (j3 & 4294967295L);
            A7.m.f(str, "<this>");
            A7.m.f(c0411f, "replacement");
            if (i11 < i10) {
                throw new IndexOutOfBoundsException("End index (" + i11 + ") is less than start index (" + i10 + ").");
            }
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) str, 0, i10);
            sb.append((CharSequence) c0411f);
            sb.append((CharSequence) str, i11, str.length());
            String string = sb.toString();
            int length = c0411f.l.length() + i10;
            c0365x.b(new T0.y(string, AbstractC0904a.q(length, length), 4));
        }
        return Boolean.TRUE;
    }
}
