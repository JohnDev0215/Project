package T7;

import F8.AbstractC0287w;
import F8.T;
import F8.W;
import Q7.C0661n;
import Q7.InterfaceC0648a;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import Q7.InterfaceC0666t;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import o8.C2061f;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public class H extends S implements Q7.L {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final boolean f8790A;
    public final boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final boolean f8791C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final boolean f8792D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public List f8793E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public u f8794F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public u f8795G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public ArrayList f8796H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public I f8797I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public J f8798J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public r f8799K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public r f8800L;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f8801r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public E8.h f8802s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public A7.o f8803t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f8804u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public C0661n f8805v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Collection f8806w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Q7.L f8807x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f8808y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final boolean f8809z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(InterfaceC0657j interfaceC0657j, Q7.L l, R7.h hVar, int i7, C0661n c0661n, boolean z6, C2061f c2061f, int i10, Q7.N n10, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14) {
        super(interfaceC0657j, hVar, c2061f, null, n10);
        if (interfaceC0657j == null) {
            K0(0);
            throw null;
        }
        if (hVar == null) {
            K0(1);
            throw null;
        }
        if (i7 == 0) {
            K0(2);
            throw null;
        }
        if (c0661n == null) {
            K0(3);
            throw null;
        }
        if (c2061f == null) {
            K0(4);
            throw null;
        }
        if (i10 == 0) {
            K0(5);
            throw null;
        }
        if (n10 == null) {
            K0(6);
            throw null;
        }
        this.f8801r = z6;
        this.f8806w = null;
        this.f8793E = Collections.emptyList();
        this.f8804u = i7;
        this.f8805v = c0661n;
        this.f8807x = l == null ? this : l;
        this.f8808y = i10;
        this.f8809z = z10;
        this.f8790A = z11;
        this.B = z12;
        this.f8791C = z13;
        this.f8792D = z14;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void K0(int r11) {
        /*
            Method dump skipped, instruction units count: 538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T7.H.K0(int):void");
    }

    public static H d1(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n, boolean z6, C2061f c2061f, int i10, Q7.N n10) {
        R7.f fVar = R7.g.f7535a;
        if (interfaceC0652e == null) {
            K0(7);
            throw null;
        }
        if (i7 == 0) {
            K0(9);
            throw null;
        }
        if (c0661n == null) {
            K0(10);
            throw null;
        }
        if (c2061f == null) {
            K0(11);
            throw null;
        }
        if (i10 == 0) {
            K0(12);
            throw null;
        }
        if (n10 != null) {
            return new H(interfaceC0652e, null, fVar, i7, c0661n, z6, c2061f, i10, n10, false, false, false, false, false);
        }
        K0(13);
        throw null;
    }

    public static InterfaceC0666t f1(W w10, Q7.K k10) {
        if (k10 == null) {
            K0(31);
            throw null;
        }
        InterfaceC0666t interfaceC0666t = ((F) k10).f8780x;
        if (interfaceC0666t != null) {
            return interfaceC0666t.f(w10);
        }
        return null;
    }

    @Override // T7.S, Q7.InterfaceC0649b
    public final u B() {
        return this.f8794F;
    }

    @Override // Q7.U
    public boolean G() {
        return this.f8790A;
    }

    @Override // Q7.U
    public final boolean I() {
        return this.f8801r;
    }

    @Override // T7.S, Q7.InterfaceC0649b
    public final u K() {
        return this.f8795G;
    }

    @Override // Q7.L
    public final r M() {
        return this.f8800L;
    }

    @Override // Q7.InterfaceC0650c
    public final int N() {
        int i7 = this.f8808y;
        if (i7 != 0) {
            return i7;
        }
        K0(39);
        throw null;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.M(this, obj);
    }

    @Override // Q7.L
    public final r U() {
        return this.f8799K;
    }

    @Override // Q7.InterfaceC0649b
    public final List V() {
        List list = this.f8793E;
        if (list != null) {
            return list;
        }
        K0(22);
        throw null;
    }

    @Override // Q7.U
    public final boolean Y() {
        return this.f8809z;
    }

    @Override // Q7.L
    public final I c() {
        return this.f8797I;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return this.B;
    }

    @Override // Q7.InterfaceC0650c
    /* JADX INFO: renamed from: c1, reason: merged with bridge method [inline-methods] */
    public final H a0(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n) {
        G g7 = new G(this);
        if (interfaceC0652e == null) {
            G.a(0);
            throw null;
        }
        g7.f8781a = interfaceC0652e;
        g7.d = null;
        if (i7 == 0) {
            G.a(6);
            throw null;
        }
        g7.f8782b = i7;
        if (c0661n == null) {
            G.a(8);
            throw null;
        }
        g7.c = c0661n;
        g7.f8783e = 2;
        g7.f8785g = false;
        H hB = g7.b();
        if (hB != null) {
            return hB;
        }
        K0(42);
        throw null;
    }

    @Override // Q7.L
    public final J d() {
        return this.f8798J;
    }

    @Override // Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = this.f8805v;
        if (c0661n != null) {
            return c0661n;
        }
        K0(25);
        throw null;
    }

    public H e1(InterfaceC0657j interfaceC0657j, int i7, C0661n c0661n, Q7.L l, int i10, C2061f c2061f) {
        Q7.O o = Q7.N.c;
        if (interfaceC0657j == null) {
            K0(32);
            throw null;
        }
        if (i7 == 0) {
            K0(33);
            throw null;
        }
        if (c0661n == null) {
            K0(34);
            throw null;
        }
        if (i10 == 0) {
            K0(35);
            throw null;
        }
        if (c2061f == null) {
            K0(36);
            throw null;
        }
        R7.h hVarK = k();
        boolean zG = G();
        boolean z6 = z();
        return new H(interfaceC0657j, l, hVarK, i7, c0661n, this.f8801r, c2061f, i10, o, this.f8809z, zG, this.B, z6, this.f8792D);
    }

    @Override // Q7.L
    public final boolean f0() {
        return this.f8792D;
    }

    public final void g1(I i7, J j3, r rVar, r rVar2) {
        this.f8797I = i7;
        this.f8798J = j3;
        this.f8799K = rVar;
        this.f8800L = rVar2;
    }

    @Override // Q7.InterfaceC0670x
    public final int h() {
        int i7 = this.f8804u;
        if (i7 != 0) {
            return i7;
        }
        K0(24);
        throw null;
    }

    @Override // Q7.InterfaceC0650c
    public final void h0(Collection collection) {
        if (collection != null) {
            this.f8806w = collection;
        } else {
            K0(40);
            throw null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void h1(E8.h hVar, InterfaceC2772a interfaceC2772a) {
        if (interfaceC2772a == 0) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compileTimeInitializerFactory", "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorWithInitializerImpl", "setCompileTimeInitializer"));
        }
        this.f8803t = (A7.o) interfaceC2772a;
        if (hVar == null) {
            hVar = (E8.h) interfaceC2772a.a();
        }
        this.f8802s = hVar;
    }

    public void i1(AbstractC0287w abstractC0287w) {
    }

    public final void j1(AbstractC0287w abstractC0287w, List list, u uVar, u uVar2, List list2) {
        if (abstractC0287w == null) {
            K0(17);
            throw null;
        }
        if (list == null) {
            K0(18);
            throw null;
        }
        if (list2 == null) {
            K0(19);
            throw null;
        }
        this.f8828q = abstractC0287w;
        this.f8796H = new ArrayList(list);
        this.f8795G = uVar2;
        this.f8794F = uVar;
        this.f8793E = list2;
    }

    @Override // T7.S, Q7.InterfaceC0649b
    public final AbstractC0287w p() {
        AbstractC0287w abstractC0287wB = b();
        if (abstractC0287wB != null) {
            return abstractC0287wB;
        }
        K0(23);
        throw null;
    }

    @Override // Q7.InterfaceC0649b
    public final Collection q() {
        Collection collectionEmptyList = this.f8806w;
        if (collectionEmptyList == null) {
            collectionEmptyList = Collections.emptyList();
        }
        if (collectionEmptyList != null) {
            return collectionEmptyList;
        }
        K0(41);
        throw null;
    }

    @Override // T7.S, Q7.InterfaceC0649b
    public final List t() {
        ArrayList arrayList = this.f8796H;
        if (arrayList != null) {
            return arrayList;
        }
        throw new IllegalStateException("typeParameters == null for ".concat(AbstractC0794m.a1(this)));
    }

    @Override // Q7.L
    public final ArrayList u() {
        ArrayList arrayList = new ArrayList(2);
        I i7 = this.f8797I;
        if (i7 != null) {
            arrayList.add(i7);
        }
        J j3 = this.f8798J;
        if (j3 != null) {
            arrayList.add(j3);
        }
        return arrayList;
    }

    @Override // Q7.InterfaceC0649b
    public Object w(InterfaceC0648a interfaceC0648a) {
        return null;
    }

    @Override // Q7.U
    public final t8.g x0() {
        E8.h hVar = this.f8802s;
        if (hVar != null) {
            return (t8.g) hVar.a();
        }
        return null;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }

    public boolean z() {
        return this.f8791C;
    }

    @Override // Q7.P
    public final Q7.L f(W w10) {
        if (w10 == null) {
            K0(27);
            throw null;
        }
        if (w10.f2455a.e()) {
            return this;
        }
        G g7 = new G(this);
        T tF = w10.f();
        if (tF == null) {
            G.a(15);
            throw null;
        }
        g7.f8784f = tF;
        g7.d = b1();
        return g7.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [Q7.L] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // T7.AbstractC0795n
    /* JADX INFO: renamed from: a */
    public final Q7.L b1() {
        Q7.L l = this.f8807x;
        ?? B12 = this;
        if (l != this) {
            B12 = l.b1();
        }
        if (B12 != 0) {
            return B12;
        }
        K0(38);
        throw null;
    }
}
