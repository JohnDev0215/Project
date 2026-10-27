package O8;

import A7.m;
import A7.o;
import A7.z;
import P7.p;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import java.io.Serializable;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class a extends l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f5187b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Serializable d;

    public /* synthetic */ a(int i7, Serializable serializable, Object obj) {
        this.f5187b = i7;
        this.c = obj;
        this.d = serializable;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [A7.o, z7.k] */
    @Override // O8.l
    public void b(Object obj) {
        switch (this.f5187b) {
            case 2:
                InterfaceC0650c interfaceC0650c = (InterfaceC0650c) obj;
                m.f(interfaceC0650c, "current");
                z zVar = (z) this.c;
                if (zVar.l == null && ((Boolean) ((o) this.d).b(interfaceC0650c)).booleanValue()) {
                    zVar.l = interfaceC0650c;
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, z7.k] */
    @Override // O8.l
    public final boolean c(Object obj) {
        switch (this.f5187b) {
            case 0:
                boolean zBooleanValue = ((Boolean) this.c.b(obj)).booleanValue();
                boolean[] zArr = (boolean[]) this.d;
                if (zBooleanValue) {
                    zArr[0] = true;
                }
                return !zArr[0];
            case 1:
                InterfaceC0652e interfaceC0652e = (InterfaceC0652e) obj;
                m.f(interfaceC0652e, "javaClassDescriptor");
                String strR = h6.e.r(interfaceC0652e, (String) this.c);
                boolean zContains = p.f5455b.contains(strR);
                z zVar = (z) this.d;
                if (zContains) {
                    zVar.l = P7.j.l;
                } else if (p.c.contains(strR)) {
                    zVar.l = P7.j.f5439m;
                } else if (p.f5454a.contains(strR)) {
                    zVar.l = P7.j.o;
                }
                return zVar.l == null;
            default:
                m.f((InterfaceC0650c) obj, "current");
                return ((z) this.c).l == null;
        }
    }

    @Override // O8.l
    public final Object i() {
        switch (this.f5187b) {
            case 0:
                return Boolean.valueOf(((boolean[]) this.d)[0]);
            case 1:
                P7.j jVar = (P7.j) ((z) this.d).l;
                return jVar == null ? P7.j.f5440n : jVar;
            default:
                return (InterfaceC0650c) ((z) this.c).l;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a(z zVar, InterfaceC2782k interfaceC2782k) {
        this.f5187b = 2;
        this.c = zVar;
        this.d = (o) interfaceC2782k;
    }
}
