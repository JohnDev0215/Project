package C0;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B implements g0 {
    public a1.k l = a1.k.f11038m;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f840m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f841n;
    public final /* synthetic */ G o;

    public B(G g7) {
        this.o = g7;
    }

    @Override // C0.M
    public final L C(int i7, int i10, Map map, InterfaceC2782k interfaceC2782k) {
        if ((i7 & (-16777216)) == 0 && ((-16777216) & i10) == 0) {
            return new A(i7, i10, map, this, this.o, interfaceC2782k);
        }
        l9.d.h0("Size(" + i7 + " x " + i10 + ") is out of range. Each dimension must be between 0 and 16777215.");
        throw null;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // C0.g0
    public final List W(Object obj, InterfaceC2785n interfaceC2785n) {
        E0.F f10;
        G g7 = this.o;
        g7.b();
        E0.F f11 = g7.l;
        int iV = f11.v();
        if (!(iV == 1 || iV == 3 || iV == 2 || iV == 4)) {
            l9.d.h0("subcompose can only be used inside the measure or layout blocks");
            throw null;
        }
        HashMap map = g7.f853r;
        Object obj2 = map.get(obj);
        Object obj3 = obj2;
        if (obj2 == null) {
            E0.F f12 = (E0.F) g7.f856u.remove(obj);
            if (f12 != null) {
                int i7 = g7.f861z;
                if (i7 <= 0) {
                    l9.d.h0("Check failed.");
                    throw null;
                }
                g7.f861z = i7 - 1;
                f10 = f12;
            } else {
                E0.F fJ = g7.j(obj);
                if (fJ == null) {
                    int i10 = g7.o;
                    E0.F f13 = new E0.F(2, 0, true);
                    f11.f1490w = true;
                    f11.N(i10, f13);
                    f11.f1490w = false;
                    f10 = f13;
                } else {
                    f10 = fJ;
                }
            }
            map.put(obj, f10);
            obj3 = f10;
        }
        E0.F f14 = (E0.F) obj3;
        if (AbstractC1998m.b0(g7.o, f11.r()) != f14) {
            int iK = ((V.a) f11.r()).l.k(f14);
            int i11 = g7.o;
            if (iK < i11) {
                throw new IllegalArgumentException(("Key \"" + obj + "\" was already used. If you are using LazyColumn/Row please make sure you provide a unique key for each item.").toString());
            }
            if (i11 != iK) {
                f11.f1490w = true;
                f11.g0(iK, i11, 1);
                f11.f1490w = false;
            }
        }
        g7.o++;
        g7.h(f14, obj, interfaceC2785n);
        return (iV == 1 || iV == 3) ? f14.o() : f14.n();
    }

    @Override // a1.b
    public final float getDensity() {
        return this.f840m;
    }

    @Override // C0.InterfaceC0108o
    public final a1.k getLayoutDirection() {
        return this.l;
    }

    @Override // a1.b
    public final float p() {
        return this.f841n;
    }

    @Override // C0.InterfaceC0108o
    public final boolean v() {
        G g7 = this.o;
        return g7.l.v() == 4 || g7.l.v() == 2;
    }
}
