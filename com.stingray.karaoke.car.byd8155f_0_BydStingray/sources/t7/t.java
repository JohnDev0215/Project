package T7;

import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.W;
import Q5.C0606p0;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0648a;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import Q7.InterfaceC0665s;
import Q7.InterfaceC0666t;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import n7.AbstractC1998m;
import o8.C2061f;
import z8.C2793a;
import z8.C2794b;

/* JADX INFO: loaded from: classes.dex */
public abstract class t extends AbstractC0795n implements InterfaceC0666t {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f8884A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f8885C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f8886D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f8887E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f8888F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f8889G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f8890H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f8891I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public Collection f8892J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public volatile C0606p0 f8893K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final InterfaceC0666t f8894L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final int f8895M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public InterfaceC0666t f8896N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public Map f8897O;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f8898q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f8899r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AbstractC0287w f8900s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f8901t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public u f8902u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public u f8903v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f8904w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public C0661n f8905x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f8906y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f8907z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, Q7.N n10, R7.h hVar, C2061f c2061f) {
        super(interfaceC0657j, hVar, c2061f, n10);
        if (interfaceC0657j == null) {
            K0(0);
            throw null;
        }
        if (hVar == null) {
            K0(1);
            throw null;
        }
        if (c2061f == null) {
            K0(2);
            throw null;
        }
        if (i7 == 0) {
            K0(3);
            throw null;
        }
        if (n10 == null) {
            K0(4);
            throw null;
        }
        this.f8905x = AbstractC0662o.f7195i;
        this.f8906y = false;
        this.f8907z = false;
        this.f8884A = false;
        this.B = false;
        this.f8885C = false;
        this.f8886D = false;
        this.f8887E = false;
        this.f8888F = false;
        this.f8889G = false;
        this.f8890H = true;
        this.f8891I = false;
        this.f8892J = null;
        this.f8893K = null;
        this.f8896N = null;
        this.f8897O = null;
        this.f8894L = interfaceC0666t == null ? this : interfaceC0666t;
        this.f8895M = i7;
    }

    public static /* synthetic */ void K0(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 10:
            case 11:
            case 12:
            case 17:
            case 22:
            case 24:
            case 25:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                i10 = 2;
                break;
            case 10:
            case 11:
            case 12:
            case 17:
            case 22:
            case 24:
            case 25:
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 5:
                objArr[0] = "contextReceiverParameters";
                break;
            case 6:
                objArr[0] = "typeParameters";
                break;
            case 7:
            case 28:
            case 30:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 8:
            case 10:
                objArr[0] = "visibility";
                break;
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
            case 11:
                objArr[0] = "unsubstitutedReturnType";
                break;
            case 12:
                objArr[0] = "extensionReceiverParameter";
                break;
            case 17:
                objArr[0] = "overriddenDescriptors";
                break;
            case 22:
                objArr[0] = "originalSubstitutor";
                break;
            case 24:
            case 29:
            case 31:
                objArr[0] = "substitutor";
                break;
            case 25:
                objArr[0] = "configuration";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i7) {
            case 9:
                objArr[1] = "initialize";
                break;
            case 10:
            case 11:
            case 12:
            case 17:
            case 22:
            case 24:
            case 25:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
            case 13:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 14:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 15:
                objArr[1] = "getModality";
                break;
            case 16:
                objArr[1] = "getVisibility";
                break;
            case 18:
                objArr[1] = "getTypeParameters";
                break;
            case 19:
                objArr[1] = "getValueParameters";
                break;
            case 20:
                objArr[1] = "getOriginal";
                break;
            case 21:
                objArr[1] = "getKind";
                break;
            case 23:
                objArr[1] = "newCopyBuilder";
                break;
            case 26:
                objArr[1] = "copy";
                break;
            case 27:
                objArr[1] = "getSourceToUseForCopy";
                break;
        }
        switch (i7) {
            case 5:
            case 6:
            case 7:
            case 8:
                objArr[2] = "initialize";
                break;
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                break;
            case 10:
                objArr[2] = "setVisibility";
                break;
            case 11:
                objArr[2] = "setReturnType";
                break;
            case 12:
                objArr[2] = "setExtensionReceiverParameter";
                break;
            case 17:
                objArr[2] = "setOverriddenDescriptors";
                break;
            case 22:
                objArr[2] = "substitute";
                break;
            case 24:
                objArr[2] = "newCopyBuilder";
                break;
            case 25:
                objArr[2] = "doSubstitute";
                break;
            case 28:
            case 29:
            case 30:
            case 31:
                objArr[2] = "getSubstitutedValueParameters";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                throw new IllegalStateException(str2);
            case 10:
            case 11:
            case 12:
            case 17:
            case 22:
            case 24:
            case 25:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static ArrayList g1(InterfaceC0666t interfaceC0666t, List list, W w10, boolean z6, boolean z10, boolean[] zArr) {
        if (list == null) {
            K0(30);
            throw null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Q q4 = (Q) it.next();
            Q q10 = q4;
            AbstractC0287w abstractC0287wI = w10.i(2, q10.b());
            AbstractC0287w abstractC0287w = q4.f8826v;
            AbstractC0287w abstractC0287wI2 = abstractC0287w == null ? null : w10.i(2, abstractC0287w);
            if (abstractC0287wI == null) {
                return null;
            }
            if ((abstractC0287wI != q10.b() || abstractC0287w != abstractC0287wI2) && zArr != null) {
                zArr[0] = true;
            }
            P5.w wVar = q4 instanceof P ? new P5.w((List) ((P) q4).f8821x.getValue(), 3) : null;
            Q q11 = z6 ? null : q4;
            R7.h hVarK = q4.k();
            C2061f name = q4.getName();
            boolean zD1 = q4.d1();
            Q7.N nG = z10 ? q4.g() : Q7.N.c;
            A7.m.f(hVarK, "annotations");
            A7.m.f(name, "name");
            A7.m.f(nG, BYDAutoMultimediaDevice.PARAM_SOURCE);
            int i7 = q4.f8822r;
            boolean z11 = q4.f8824t;
            boolean z12 = q4.f8825u;
            arrayList.add(wVar == null ? new Q(interfaceC0666t, q11, i7, hVarK, name, abstractC0287wI, zD1, z11, z12, abstractC0287wI2, nG) : new P(interfaceC0666t, q11, i7, hVarK, name, abstractC0287wI, zD1, z11, z12, abstractC0287wI2, nG, wVar));
        }
        return arrayList;
    }

    @Override // Q7.InterfaceC0649b
    public final u B() {
        return this.f8903v;
    }

    @Override // Q7.InterfaceC0649b
    public final List D0() {
        List list = this.f8899r;
        if (list != null) {
            return list;
        }
        K0(19);
        throw null;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean E0() {
        if (this.f8907z) {
            return true;
        }
        Iterator it = b1().q().iterator();
        while (it.hasNext()) {
            if (((InterfaceC0666t) it.next()).E0()) {
                return true;
            }
        }
        return false;
    }

    @Override // Q7.InterfaceC0649b
    public final u K() {
        return this.f8902u;
    }

    @Override // Q7.InterfaceC0650c
    public final int N() {
        int i7 = this.f8895M;
        if (i7 != 0) {
            return i7;
        }
        K0(21);
        throw null;
    }

    public Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.C(this, obj);
    }

    @Override // Q7.InterfaceC0649b
    public boolean T() {
        return this.f8891I;
    }

    @Override // Q7.InterfaceC0649b
    public final List V() {
        List list = this.f8901t;
        if (list != null) {
            return list;
        }
        K0(13);
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [Q7.t] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // T7.AbstractC0795n, T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public InterfaceC0666t b1() {
        InterfaceC0666t interfaceC0666t = this.f8894L;
        ?? B12 = this;
        if (interfaceC0666t != this) {
            B12 = interfaceC0666t.b1();
        }
        if (B12 != 0) {
            return B12;
        }
        K0(20);
        throw null;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return this.f8886D;
    }

    public final InterfaceC0666t c1(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n) {
        InterfaceC0666t interfaceC0666tMo0a = v0().L(interfaceC0652e).F(i7).u(c0661n).t(2).c().mo0a();
        if (interfaceC0666tMo0a != null) {
            return interfaceC0666tMo0a;
        }
        K0(26);
        throw null;
    }

    @Override // Q7.InterfaceC0650c
    /* JADX INFO: renamed from: d1, reason: merged with bridge method [inline-methods] */
    public K a0(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n) {
        return (K) c1(interfaceC0652e, i7, c0661n);
    }

    @Override // Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = this.f8905x;
        if (c0661n != null) {
            return c0661n;
        }
        K0(16);
        throw null;
    }

    public abstract t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, Q7.N n10, R7.h hVar, C2061f c2061f);

    public t f1(C0799s c0799s) {
        u uVar;
        u uVar2;
        AbstractC0287w abstractC0287wI;
        if (c0799s == null) {
            K0(25);
            throw null;
        }
        boolean[] zArr = new boolean[1];
        R7.h hVarT = c0799s.f8865D != null ? com.bumptech.glide.c.T(k(), c0799s.f8865D) : k();
        InterfaceC0657j interfaceC0657j = c0799s.f8871m;
        InterfaceC0666t interfaceC0666t = c0799s.f8873p;
        int i7 = c0799s.f8874q;
        C2061f c2061f = c0799s.f8880w;
        Q7.N nG = c0799s.f8883z ? ((AbstractC0795n) (interfaceC0666t != null ? interfaceC0666t : b1())).g() : Q7.N.c;
        if (nG == null) {
            K0(27);
            throw null;
        }
        t tVarE1 = e1(i7, interfaceC0657j, interfaceC0666t, nG, hVarT, c2061f);
        List listT = c0799s.f8864C;
        if (listT == null) {
            listT = t();
        }
        zArr[0] = zArr[0] | (!listT.isEmpty());
        ArrayList arrayList = new ArrayList(listT.size());
        W wV = AbstractC0268c.v(listT, c0799s.l, tVarE1, arrayList, zArr);
        if (wV == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        if (!c0799s.f8876s.isEmpty()) {
            int i10 = 0;
            for (u uVar3 : c0799s.f8876s) {
                AbstractC0287w abstractC0287wI2 = wV.i(2, uVar3.b());
                if (abstractC0287wI2 == null) {
                    return null;
                }
                int i11 = i10 + 1;
                arrayList2.add(r8.m.e(tVarE1, abstractC0287wI2, ((C2793a) uVar3.c1()).a1(), uVar3.k(), i10));
                zArr[0] = zArr[0] | (abstractC0287wI2 != uVar3.b());
                i10 = i11;
            }
        }
        u uVar4 = c0799s.f8877t;
        if (uVar4 != null) {
            AbstractC0287w abstractC0287wI3 = wV.i(2, uVar4.b());
            if (abstractC0287wI3 == null) {
                return null;
            }
            c0799s.f8877t.c1();
            u uVar5 = new u(tVarE1, new C2794b(tVarE1, abstractC0287wI3), c0799s.f8877t.k());
            zArr[0] = (abstractC0287wI3 != c0799s.f8877t.b()) | zArr[0];
            uVar = uVar5;
        } else {
            uVar = null;
        }
        u uVar6 = c0799s.f8878u;
        if (uVar6 != null) {
            u uVarF = uVar6.f(wV);
            if (uVarF == null) {
                return null;
            }
            zArr[0] = zArr[0] | (uVarF != c0799s.f8878u);
            uVar2 = uVarF;
        } else {
            uVar2 = null;
        }
        ArrayList arrayListG1 = g1(tVarE1, c0799s.f8875r, wV, c0799s.f8863A, c0799s.f8883z, zArr);
        if (arrayListG1 == null || (abstractC0287wI = wV.i(3, c0799s.f8879v)) == null) {
            return null;
        }
        boolean z6 = zArr[0] | (abstractC0287wI != c0799s.f8879v);
        zArr[0] = z6;
        if (!z6 && c0799s.f8869H) {
            return this;
        }
        tVarE1.h1(uVar, uVar2, arrayList2, arrayList, arrayListG1, abstractC0287wI, c0799s.f8872n, c0799s.o);
        tVarE1.f8906y = this.f8906y;
        tVarE1.f8907z = this.f8907z;
        tVarE1.f8884A = this.f8884A;
        tVarE1.B = this.B;
        tVarE1.f8885C = this.f8885C;
        tVarE1.f8889G = this.f8889G;
        tVarE1.f8886D = this.f8886D;
        tVarE1.k1(this.f8890H);
        tVarE1.f8887E = c0799s.B;
        tVarE1.f8888F = c0799s.f8866E;
        Boolean bool = c0799s.f8868G;
        tVarE1.l1(bool != null ? bool.booleanValue() : this.f8891I);
        if (!c0799s.f8867F.isEmpty() || this.f8897O != null) {
            LinkedHashMap linkedHashMap = c0799s.f8867F;
            Map map = this.f8897O;
            if (map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    if (!linkedHashMap.containsKey(entry.getKey())) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            if (linkedHashMap.size() == 1) {
                tVarE1.f8897O = Collections.singletonMap(linkedHashMap.keySet().iterator().next(), linkedHashMap.values().iterator().next());
            } else {
                tVarE1.f8897O = linkedHashMap;
            }
        }
        if (c0799s.f8882y || this.f8896N != null) {
            InterfaceC0666t interfaceC0666t2 = this.f8896N;
            if (interfaceC0666t2 == null) {
                interfaceC0666t2 = this;
            }
            tVarE1.f8896N = interfaceC0666t2.f(wV);
        }
        if (c0799s.f8881x && !b1().q().isEmpty()) {
            if (c0799s.l.e()) {
                C0606p0 c0606p0 = this.f8893K;
                if (c0606p0 != null) {
                    tVarE1.f8893K = c0606p0;
                } else {
                    tVarE1.h0(q());
                }
            } else {
                tVarE1.f8893K = new C0606p0(this, 2, wV);
            }
        }
        return tVarE1;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean g0() {
        return this.f8887E;
    }

    @Override // Q7.InterfaceC0670x
    public final int h() {
        int i7 = this.f8904w;
        if (i7 != 0) {
            return i7;
        }
        K0(15);
        throw null;
    }

    public void h0(Collection collection) {
        if (collection == null) {
            K0(17);
            throw null;
        }
        this.f8892J = collection;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (((InterfaceC0666t) it.next()).s0()) {
                this.f8888F = true;
                return;
            }
        }
    }

    public void h1(u uVar, u uVar2, List list, List list2, List list3, AbstractC0287w abstractC0287w, int i7, C0661n c0661n) {
        if (list == null) {
            K0(5);
            throw null;
        }
        if (list2 == null) {
            K0(6);
            throw null;
        }
        if (list3 == null) {
            K0(7);
            throw null;
        }
        if (c0661n == null) {
            K0(8);
            throw null;
        }
        this.f8898q = AbstractC1998m.z0(list2);
        this.f8899r = AbstractC1998m.z0(list3);
        this.f8900s = abstractC0287w;
        this.f8904w = i7;
        this.f8905x = c0661n;
        this.f8902u = uVar;
        this.f8903v = uVar2;
        this.f8901t = list;
        for (int i10 = 0; i10 < list2.size(); i10++) {
            Q7.Q q4 = (Q7.Q) list2.get(i10);
            if (q4.getIndex() != i10) {
                throw new IllegalStateException(q4 + " index is " + q4.getIndex() + " but position is " + i10);
            }
        }
        for (int i11 = 0; i11 < list3.size(); i11++) {
            Q q10 = (Q) list3.get(i11);
            if (q10.f8822r != i11) {
                throw new IllegalStateException(q10 + "index is " + q10.f8822r + " but position is " + i11);
            }
        }
    }

    public final C0799s i1(W w10) {
        if (w10 != null) {
            return new C0799s(this, w10.f(), o(), h(), e(), N(), D0(), V(), this.f8902u, p());
        }
        K0(24);
        throw null;
    }

    public boolean j0() {
        return this.f8885C;
    }

    public final void j1(InterfaceC0648a interfaceC0648a, Object obj) {
        if (this.f8897O == null) {
            this.f8897O = new LinkedHashMap();
        }
        this.f8897O.put(interfaceC0648a, obj);
    }

    @Override // Q7.InterfaceC0666t
    public final boolean k0() {
        if (this.f8906y) {
            return true;
        }
        Iterator it = b1().q().iterator();
        while (it.hasNext()) {
            if (((InterfaceC0666t) it.next()).k0()) {
                return true;
            }
        }
        return false;
    }

    public void k1(boolean z6) {
        this.f8890H = z6;
    }

    public void l1(boolean z6) {
        this.f8891I = z6;
    }

    public boolean m() {
        return this.f8889G;
    }

    public final void m1(F8.A a10) {
        if (a10 != null) {
            this.f8900s = a10;
        } else {
            K0(11);
            throw null;
        }
    }

    public boolean n() {
        return this.B;
    }

    public AbstractC0287w p() {
        return this.f8900s;
    }

    public Collection q() {
        C0606p0 c0606p0 = this.f8893K;
        if (c0606p0 != null) {
            this.f8892J = (Collection) c0606p0.a();
            this.f8893K = null;
        }
        Collection collectionEmptyList = this.f8892J;
        if (collectionEmptyList == null) {
            collectionEmptyList = Collections.emptyList();
        }
        if (collectionEmptyList != null) {
            return collectionEmptyList;
        }
        K0(14);
        throw null;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean s0() {
        return this.f8888F;
    }

    @Override // Q7.InterfaceC0649b
    public final List t() {
        List list = this.f8898q;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("typeParameters == null for " + this);
    }

    public InterfaceC0665s v0() {
        return i1(W.f2454b);
    }

    public Object w(InterfaceC0648a interfaceC0648a) {
        Map map = this.f8897O;
        if (map == null) {
            return null;
        }
        return map.get(interfaceC0648a);
    }

    @Override // Q7.InterfaceC0666t
    public final InterfaceC0666t x() {
        return this.f8896N;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }

    public boolean z() {
        return this.f8884A;
    }

    @Override // Q7.P
    public InterfaceC0666t f(W w10) {
        if (w10 == null) {
            K0(22);
            throw null;
        }
        if (w10.f2455a.e()) {
            return this;
        }
        C0799s c0799sI1 = i1(w10);
        c0799sI1.f8873p = b1();
        c0799sI1.f8883z = true;
        c0799sI1.f8869H = true;
        return c0799sI1.f8870I.f1(c0799sI1);
    }
}
