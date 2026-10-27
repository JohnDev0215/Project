package r8;

import B2.q;
import D8.v;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.K;
import F8.P;
import N7.o;
import Q7.AbstractC0662o;
import Q7.AbstractC0669w;
import Q7.C0661n;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.L;
import Q7.N;
import T7.AbstractC0783b;
import T7.H;
import T7.I;
import T7.J;
import T7.Q;
import T7.u;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import n7.AbstractC1998m;
import o8.AbstractC2062g;
import o8.C2061f;
import o8.C2064i;
import q0.C2162a;
import v8.AbstractC2564d;
import y8.n;
import z7.InterfaceC2782k;
import z8.C2793a;
import z8.C2794b;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f21890a = new q("ResolutionAnchorProvider", 1);

    public static /* synthetic */ void a(int i7) {
        String str = (i7 == 12 || i7 == 23 || i7 == 25) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 12 || i7 == 23 || i7 == 25) ? 2 : 3];
        switch (i7) {
            case 1:
            case 4:
            case 8:
            case 14:
            case 16:
            case 18:
            case 31:
            case 33:
            case 35:
                objArr[0] = "annotations";
                break;
            case 2:
            case 5:
            case 9:
                objArr[0] = "parameterAnnotations";
                break;
            case 3:
            case 7:
            case 13:
            case 15:
            case 17:
            default:
                objArr[0] = "propertyDescriptor";
                break;
            case 6:
            case 11:
            case 19:
                objArr[0] = "sourceElement";
                break;
            case 10:
                objArr[0] = "visibility";
                break;
            case 12:
            case 23:
            case 25:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
                break;
            case 20:
                objArr[0] = "containingClass";
                break;
            case 21:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 22:
            case 24:
            case 26:
                objArr[0] = "enumClass";
                break;
            case 27:
            case 28:
            case 29:
                objArr[0] = "descriptor";
                break;
            case 30:
            case 32:
            case 34:
                objArr[0] = "owner";
                break;
        }
        if (i7 == 12) {
            objArr[1] = "createSetter";
        } else if (i7 == 23) {
            objArr[1] = "createEnumValuesMethod";
        } else if (i7 != 25) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
        } else {
            objArr[1] = "createEnumValueOfMethod";
        }
        switch (i7) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "createSetter";
                break;
            case 12:
            case 23:
            case 25:
                break;
            case 13:
            case 14:
                objArr[2] = "createDefaultGetter";
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[2] = "createGetter";
                break;
            case 20:
            case 21:
                objArr[2] = "createPrimaryConstructorForObject";
                break;
            case 22:
                objArr[2] = "createEnumValuesMethod";
                break;
            case 24:
                objArr[2] = "createEnumValueOfMethod";
                break;
            case 26:
                objArr[2] = "createEnumEntriesProperty";
                break;
            case 27:
                objArr[2] = "isEnumValuesMethod";
                break;
            case 28:
                objArr[2] = "isEnumValueOfMethod";
                break;
            case 29:
                objArr[2] = "isEnumSpecialMethod";
                break;
            case 30:
            case 31:
                objArr[2] = "createExtensionReceiverParameterForCallable";
                break;
            case 32:
            case 33:
                objArr[2] = "createContextReceiverParameterForCallable";
                break;
            case 34:
            case 35:
                objArr[2] = "createContextReceiverParameterForClass";
                break;
            default:
                objArr[2] = "createDefaultSetter";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 12 && i7 != 23 && i7 != 25) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static final void c(InterfaceC0652e interfaceC0652e, LinkedHashSet linkedHashSet, n nVar, boolean z6) {
        for (InterfaceC0657j interfaceC0657j : h6.f.i(nVar, y8.f.o, 2)) {
            if (interfaceC0657j instanceof InterfaceC0652e) {
                InterfaceC0652e interfaceC0652eC1 = (InterfaceC0652e) interfaceC0657j;
                if (interfaceC0652eC1.c0()) {
                    C2061f name = interfaceC0652eC1.getName();
                    A7.m.e(name, "descriptor.name");
                    InterfaceC0654g interfaceC0654gC = nVar.c(name, Y7.b.o);
                    interfaceC0652eC1 = interfaceC0654gC instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gC : interfaceC0654gC instanceof v ? ((v) interfaceC0654gC).c1() : null;
                }
                if (interfaceC0652eC1 == null) {
                    continue;
                } else {
                    if (interfaceC0652e == null) {
                        d.a(27);
                        throw null;
                    }
                    int i7 = d.f21880a;
                    Iterator it = interfaceC0652eC1.C().o().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (d.p((AbstractC0287w) it.next(), interfaceC0652e.b1())) {
                                linkedHashSet.add(interfaceC0652eC1);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (z6) {
                        n nVarL0 = interfaceC0652eC1.l0();
                        A7.m.e(nVarL0, "refinedDescriptor.unsubstitutedInnerClassesScope");
                        c(interfaceC0652e, linkedHashSet, nVarL0, z6);
                    }
                }
            }
        }
    }

    public static u e(InterfaceC0649b interfaceC0649b, AbstractC0287w abstractC0287w, C2061f c2061f, R7.h hVar, int i7) {
        if (hVar == null) {
            a(33);
            throw null;
        }
        if (abstractC0287w == null) {
            return null;
        }
        C2793a c2793a = new C2793a(interfaceC0649b, abstractC0287w, c2061f);
        Q8.g gVar = AbstractC2062g.f20574a;
        return new u(interfaceC0649b, c2793a, hVar, C2061f.e("_context_receiver_" + i7));
    }

    public static I f(L l, R7.h hVar) {
        return l(l, hVar, true, l.g());
    }

    public static J g(L l, R7.h hVar) {
        R7.f fVar = R7.g.f7535a;
        N nG = l.g();
        if (nG != null) {
            return m(l, hVar, fVar, true, l.e(), nG);
        }
        a(6);
        throw null;
    }

    public static H h(AbstractC0783b abstractC0783b) {
        if (abstractC0783b == null) {
            a(26);
            throw null;
        }
        InterfaceC0652e interfaceC0652eD = AbstractC0669w.d(d.d(abstractC0783b), C2064i.f20595t);
        if (interfaceC0652eD == null) {
            return null;
        }
        R7.f fVar = R7.g.f7535a;
        C0661n c0661n = AbstractC0662o.f7191e;
        H hD1 = H.d1(abstractC0783b, 1, c0661n, false, o.f4869b, 4, abstractC0783b.g());
        I i7 = new I(hD1, fVar, 1, c0661n, false, false, false, 4, null, abstractC0783b.g());
        hD1.g1(i7, null, null, null);
        F8.H.f2437m.getClass();
        F8.H h10 = F8.H.f2438n;
        K kC = interfaceC0652eD.C();
        List listSingletonList = Collections.singletonList(new P(1, abstractC0783b.j()));
        A7.m.f(h10, "attributes");
        A7.m.f(kC, "constructor");
        A7.m.f(listSingletonList, "arguments");
        hD1.j1(C0269d.r(h10, kC, listSingletonList, false), Collections.emptyList(), null, null, Collections.emptyList());
        i7.f1(hD1.p());
        return hD1;
    }

    public static T7.K i(AbstractC0783b abstractC0783b) {
        if (abstractC0783b == null) {
            a(24);
            throw null;
        }
        R7.f fVar = R7.g.f7535a;
        T7.K kN1 = T7.K.n1(abstractC0783b, o.c, 4, abstractC0783b.g());
        return kN1.h1(null, null, Collections.emptyList(), Collections.emptyList(), Collections.singletonList(new Q(kN1, null, 0, fVar, C2061f.e("value"), AbstractC2564d.e(abstractC0783b).t(), false, false, false, null, abstractC0783b.g())), abstractC0783b.j(), 1, AbstractC0662o.f7191e);
    }

    public static T7.K j(AbstractC0783b abstractC0783b) {
        if (abstractC0783b != null) {
            return T7.K.n1(abstractC0783b, o.f4868a, 4, abstractC0783b.g()).h1(null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), AbstractC2564d.e(abstractC0783b).h(abstractC0783b.j()), 1, AbstractC0662o.f7191e);
        }
        a(22);
        throw null;
    }

    public static u k(InterfaceC0649b interfaceC0649b, AbstractC0287w abstractC0287w, R7.h hVar) {
        if (abstractC0287w == null) {
            return null;
        }
        return new u(interfaceC0649b, new C2794b(interfaceC0649b, abstractC0287w), hVar);
    }

    public static I l(L l, R7.h hVar, boolean z6, N n10) {
        if (hVar == null) {
            a(18);
            throw null;
        }
        if (n10 != null) {
            return new I(l, hVar, l.h(), l.e(), z6, false, false, 1, null, n10);
        }
        a(19);
        throw null;
    }

    public static J m(L l, R7.h hVar, R7.h hVar2, boolean z6, C0661n c0661n, N n10) {
        if (hVar == null) {
            a(8);
            throw null;
        }
        if (hVar2 == null) {
            a(9);
            throw null;
        }
        if (c0661n == null) {
            a(10);
            throw null;
        }
        if (n10 == null) {
            a(11);
            throw null;
        }
        J j3 = new J(l, hVar, l.h(), c0661n, z6, false, false, 1, null, n10);
        j3.f8812y = J.e1(j3, l.b(), hVar2);
        return j3;
    }

    public static boolean n(InterfaceC0666t interfaceC0666t) {
        return interfaceC0666t.N() == 4 && d.n(interfaceC0666t.o(), 3);
    }

    public static final Collection o(Collection collection, InterfaceC2782k interfaceC2782k) {
        A7.m.f(collection, "<this>");
        if (collection.size() <= 1) {
            return collection;
        }
        LinkedList linkedList = new LinkedList(collection);
        O8.i iVar = new O8.i();
        while (!linkedList.isEmpty()) {
            Object objY = AbstractC1998m.Y(linkedList);
            O8.i iVar2 = new O8.i();
            ArrayList arrayListG = l.g(objY, linkedList, interfaceC2782k, new C2162a(iVar2, 1));
            if (arrayListG.size() == 1 && iVar2.isEmpty()) {
                Object objQ0 = AbstractC1998m.q0(arrayListG);
                A7.m.e(objQ0, "overridableGroup.single()");
                iVar.add(objQ0);
            } else {
                Object objS = l.s(arrayListG, interfaceC2782k);
                InterfaceC0649b interfaceC0649b = (InterfaceC0649b) interfaceC2782k.b(objS);
                for (Object obj : arrayListG) {
                    A7.m.e(obj, "it");
                    if (!l.k(interfaceC0649b, (InterfaceC0649b) interfaceC2782k.b(obj))) {
                        iVar2.add(obj);
                    }
                }
                if (!iVar2.isEmpty()) {
                    iVar.addAll(iVar2);
                }
                iVar.add(objS);
            }
        }
        return iVar;
    }

    public abstract void b(InterfaceC0650c interfaceC0650c);

    public abstract void d(InterfaceC0650c interfaceC0650c, InterfaceC0650c interfaceC0650c2);

    public void p(InterfaceC0650c interfaceC0650c, Collection collection) {
        A7.m.f(interfaceC0650c, "member");
        interfaceC0650c.h0(collection);
    }
}
