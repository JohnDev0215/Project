package K7;

import F8.AbstractC0287w;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0666t;
import o8.C2061f;
import q8.C2203g;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class W implements H7.n {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f3754e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f3755a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3756b;
    public final H7.m c;
    public final t0 d;

    static {
        A7.B b10 = A7.A.f443a;
        f3754e = new H7.w[]{b10.h(new A7.s(b10.b(W.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;")), b10.h(new A7.s(b10.b(W.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    }

    public W(r rVar, int i7, H7.m mVar, InterfaceC2772a interfaceC2772a) {
        A7.m.f(rVar, "callable");
        this.f3755a = rVar;
        this.f3756b = i7;
        this.c = mVar;
        this.d = v0.n(null, interfaceC2772a);
        v0.n(null, new V(this, 0));
    }

    public final Q7.J a() {
        H7.w wVar = f3754e[0];
        Object objA = this.d.a();
        A7.m.e(objA, "<get-descriptor>(...)");
        return (Q7.J) objA;
    }

    public final String b() {
        Q7.J jA = a();
        T7.Q q4 = jA instanceof T7.Q ? (T7.Q) jA : null;
        if (q4 == null || q4.o().T()) {
            return null;
        }
        C2061f name = q4.getName();
        A7.m.e(name, "valueParameter.name");
        if (name.f20573m) {
            return null;
        }
        return name.b();
    }

    public final o0 c() {
        AbstractC0287w abstractC0287wB = a().b();
        A7.m.e(abstractC0287wB, "descriptor.type");
        return new o0(abstractC0287wB, new V(this, 1));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof W) {
            W w10 = (W) obj;
            if (A7.m.a(this.f3755a, w10.f3755a)) {
                if (this.f3756b == w10.f3756b) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3756b) + (this.f3755a.hashCode() * 31);
    }

    public final String toString() {
        String strB;
        C2203g c2203g = x0.f3852a;
        StringBuilder sb = new StringBuilder();
        int iOrdinal = this.c.ordinal();
        if (iOrdinal == 0) {
            sb.append("instance parameter");
        } else if (iOrdinal == 1) {
            sb.append("extension receiver parameter");
        } else if (iOrdinal == 2) {
            sb.append("parameter #" + this.f3756b + ' ' + b());
        }
        sb.append(" of ");
        InterfaceC0650c interfaceC0650cN = this.f3755a.n();
        if (interfaceC0650cN instanceof Q7.L) {
            strB = x0.c((Q7.L) interfaceC0650cN);
        } else {
            if (!(interfaceC0650cN instanceof InterfaceC0666t)) {
                throw new IllegalStateException(("Illegal callable: " + interfaceC0650cN).toString());
            }
            strB = x0.b((InterfaceC0666t) interfaceC0650cN);
        }
        sb.append(strB);
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
