package G8;

import A7.A;
import F8.AbstractC0268c;
import F8.AbstractC0282q;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.C0278m;
import F8.C0286v;
import F8.D;
import F8.H;
import F8.J;
import F8.K;
import F8.M;
import F8.O;
import F8.P;
import F8.W;
import F8.Y;
import F8.a0;
import Q7.C0667u;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.Q;
import g1.AbstractC1274a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2058c;
import q8.C2203g;
import s8.InterfaceC2414b;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final B2.q f2725a = new B2.q("KotlinTypeRefiner", 1);

    public static boolean A(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            return ((K) fVar).m();
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static boolean B(I8.c cVar) {
        A7.m.f(cVar, "$receiver");
        if (cVar instanceof AbstractC0287w) {
            return AbstractC0268c.i((AbstractC0287w) cVar);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(cVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, cVar.getClass(), sb).toString());
    }

    public static boolean C(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            InterfaceC0654g interfaceC0654gN = ((K) fVar).n();
            InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
            return (interfaceC0652e != null ? interfaceC0652e.m0() : null) instanceof C0667u;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static boolean D(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            return fVar instanceof t8.m;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static boolean E(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            return fVar instanceof C0286v;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static boolean F(I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof F8.A) {
            return ((F8.A) dVar).F0();
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static boolean G(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            return N7.i.F((K) fVar, N7.n.f4845b);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static boolean H(I8.c cVar) {
        A7.m.f(cVar, "$receiver");
        if (cVar instanceof AbstractC0287w) {
            return Y.e((AbstractC0287w) cVar);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(cVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, cVar.getClass(), sb).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean I(I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof AbstractC0287w) {
            return N7.i.E((AbstractC0287w) dVar);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static boolean J(I8.b bVar) {
        if (bVar instanceof h) {
            return ((h) bVar).f2730r;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(bVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, bVar.getClass(), sb).toString());
    }

    public static boolean K(O o) {
        A7.m.f(o, "$receiver");
        if (o instanceof O) {
            return o.c();
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(o);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, o.getClass(), sb).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void L(I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof F8.A) {
            boolean z6 = ((AbstractC0287w) dVar) instanceof C0278m;
            return;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void M(I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof F8.A) {
            boolean z6 = ((AbstractC0287w) dVar) instanceof C0278m;
            return;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static F8.A N(AbstractC0282q abstractC0282q) {
        if (abstractC0282q instanceof AbstractC0282q) {
            return abstractC0282q.f2477m;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(abstractC0282q);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, abstractC0282q.getClass(), sb).toString());
    }

    public static a0 O(I8.b bVar) {
        if (bVar instanceof h) {
            return ((h) bVar).o;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(bVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, bVar.getClass(), sb).toString());
    }

    public static a0 P(I8.c cVar) {
        if (cVar instanceof a0) {
            return AbstractC0268c.l((a0) cVar, false);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(cVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, cVar.getClass(), sb).toString());
    }

    public static F8.A Q(C0278m c0278m) {
        if (c0278m instanceof C0278m) {
            return c0278m.f2473m;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(c0278m);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, c0278m.getClass(), sb).toString());
    }

    public static int R(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            return ((K) fVar).p().size();
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static Collection S(b bVar, I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        K kY = bVar.y(dVar);
        if (kY instanceof t8.m) {
            return ((t8.m) kY).f22504a;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static O T(InterfaceC2414b interfaceC2414b) {
        A7.m.f(interfaceC2414b, "$receiver");
        if (interfaceC2414b instanceof i) {
            return ((i) interfaceC2414b).f2731a;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(interfaceC2414b);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, interfaceC2414b.getClass(), sb).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static a U(b bVar, I8.d dVar) {
        if (dVar instanceof F8.A) {
            AbstractC0287w abstractC0287w = (AbstractC0287w) dVar;
            return new a(bVar, new W(M.f2447b.f(abstractC0287w.B0(), abstractC0287w.o0())));
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static Collection V(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            Collection collectionO = ((K) fVar).o();
            A7.m.e(collectionO, "this.supertypes");
            return collectionO;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static K W(I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof F8.A) {
            return ((F8.A) dVar).B0();
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static i X(I8.b bVar) {
        A7.m.f(bVar, "$receiver");
        if (bVar instanceof h) {
            return ((h) bVar).f2727n;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(bVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, bVar.getClass(), sb).toString());
    }

    public static F8.A Y(AbstractC0282q abstractC0282q) {
        if (abstractC0282q instanceof AbstractC0282q) {
            return abstractC0282q.f2478n;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(abstractC0282q);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, abstractC0282q.getClass(), sb).toString());
    }

    public static F8.A Z(I8.d dVar, boolean z6) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof F8.A) {
            return ((F8.A) dVar).L0(z6);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static /* synthetic */ void a(int i7) {
        Object[] objArr = new Object[3];
        switch (i7) {
            case 1:
            case 4:
                objArr[0] = "b";
                break;
            case 2:
            case 7:
                objArr[0] = "typeCheckingProcedure";
                break;
            case 3:
            default:
                objArr[0] = "a";
                break;
            case 5:
            case 10:
                objArr[0] = "subtype";
                break;
            case 6:
            case 11:
                objArr[0] = "supertype";
                break;
            case 8:
                objArr[0] = "type";
                break;
            case 9:
                objArr[0] = "typeProjection";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/types/checker/TypeCheckerProcedureCallbacksImpl";
        switch (i7) {
            case 3:
            case 4:
                objArr[2] = "assertEqualTypeConstructors";
                break;
            case 5:
            case 6:
            case 7:
                objArr[2] = "assertSubtype";
                break;
            case 8:
            case 9:
                objArr[2] = "capture";
                break;
            case 10:
            case 11:
                objArr[2] = "noCorrespondingSupertype";
                break;
            default:
                objArr[2] = "assertEqualTypes";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static I8.c a0(b bVar, I8.c cVar) {
        if (cVar instanceof I8.d) {
            return bVar.n0((I8.d) cVar, true);
        }
        if (!(cVar instanceof AbstractC0282q)) {
            throw new IllegalStateException("sealed");
        }
        AbstractC0282q abstractC0282q = (AbstractC0282q) cVar;
        return bVar.x(bVar.n0(bVar.c(abstractC0282q), true), bVar.n0(bVar.f(abstractC0282q), true));
    }

    public static boolean b(I8.f fVar, I8.f fVar2) {
        A7.m.f(fVar, "c1");
        A7.m.f(fVar2, "c2");
        if (!(fVar instanceof K)) {
            StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb.append(fVar);
            sb.append(", ");
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
        }
        if (fVar2 instanceof K) {
            return fVar.equals(fVar2);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(fVar2);
        sb2.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar2.getClass(), sb2).toString());
    }

    public static int c(I8.c cVar) {
        A7.m.f(cVar, "$receiver");
        if (cVar instanceof AbstractC0287w) {
            return ((AbstractC0287w) cVar).o0().size();
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(cVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, cVar.getClass(), sb).toString());
    }

    public static I8.e d(I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof F8.A) {
            return (I8.e) dVar;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static I8.b e(b bVar, I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof F8.A) {
            if (dVar instanceof D) {
                return bVar.c0(((D) dVar).f2432m);
            }
            if (dVar instanceof h) {
                return (h) dVar;
            }
            return null;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static C0278m f(I8.d dVar) {
        A7.m.f(dVar, "$receiver");
        if (dVar instanceof F8.A) {
            if (dVar instanceof C0278m) {
                return (C0278m) dVar;
            }
            return null;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(dVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
    }

    public static AbstractC0282q g(I8.c cVar) {
        A7.m.f(cVar, "$receiver");
        if (cVar instanceof AbstractC0287w) {
            a0 a0VarK0 = ((AbstractC0287w) cVar).K0();
            if (a0VarK0 instanceof AbstractC0282q) {
                return (AbstractC0282q) a0VarK0;
            }
            return null;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(cVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, cVar.getClass(), sb).toString());
    }

    public static F8.A h(I8.c cVar) {
        A7.m.f(cVar, "$receiver");
        if (cVar instanceof AbstractC0287w) {
            a0 a0VarK0 = ((AbstractC0287w) cVar).K0();
            if (a0VarK0 instanceof F8.A) {
                return (F8.A) a0VarK0;
            }
            return null;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(cVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, cVar.getClass(), sb).toString());
    }

    public static P i(I8.c cVar) {
        A7.m.f(cVar, "$receiver");
        if (cVar instanceof AbstractC0287w) {
            return AbstractC1274a.w((AbstractC0287w) cVar);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(cVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, cVar.getClass(), sb).toString());
    }

    public static F8.A j(I8.d dVar) {
        List listO0;
        ArrayList arrayList;
        e eVar;
        A7.k.p(1, "status");
        if (!(dVar instanceof F8.A)) {
            StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb.append(dVar);
            sb.append(", ");
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
        }
        F8.A a10 = (F8.A) dVar;
        if (a10.o0().size() == a10.B0().p().size() && ((listO0 = a10.o0()) == null || !listO0.isEmpty())) {
            Iterator it = listO0.iterator();
            while (it.hasNext()) {
                if (((O) it.next()).a() != 1) {
                    List listP = a10.B0().p();
                    A7.m.e(listP, "type.constructor.parameters");
                    ArrayList<m7.i> arrayListF0 = AbstractC1998m.F0(listO0, listP);
                    arrayList = new ArrayList(AbstractC2000o.J(arrayListF0, 10));
                    for (m7.i iVar : arrayListF0) {
                        O oW = (O) iVar.l;
                        Q q4 = (Q) iVar.f19259m;
                        if (oW.a() != 1) {
                            a0 a0VarK0 = (oW.c() || oW.a() != 2) ? null : oW.b().K0();
                            A7.m.e(q4, "parameter");
                            oW = AbstractC1274a.w(new h(1, new i(oW, null, null, q4, 6), a0VarK0, (H) null, false, 56));
                        }
                        arrayList.add(oW);
                    }
                    W w10 = new W(M.f2447b.f(a10.B0(), arrayList));
                    int size = listO0.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        O o = (O) listO0.get(i7);
                        O o4 = (O) arrayList.get(i7);
                        if (o.a() != 1) {
                            List upperBounds = ((Q) a10.B0().p().get(i7)).getUpperBounds();
                            A7.m.e(upperBounds, "type.constructor.parameters[index].upperBounds");
                            ArrayList arrayList2 = new ArrayList();
                            Iterator it2 = upperBounds.iterator();
                            while (true) {
                                boolean zHasNext = it2.hasNext();
                                eVar = e.l;
                                if (!zHasNext) {
                                    break;
                                }
                                arrayList2.add(eVar.v(w10.g(1, (AbstractC0287w) it2.next()).K0()));
                            }
                            if (!o.c() && o.a() == 3) {
                                arrayList2.add(eVar.v(o.b().K0()));
                            }
                            AbstractC0287w abstractC0287wB = o4.b();
                            A7.m.d(abstractC0287wB, "null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedType");
                            i iVar2 = ((h) abstractC0287wB).f2727n;
                            iVar2.getClass();
                            iVar2.f2732b = new D8.d(2, arrayList2);
                        }
                    }
                }
            }
            arrayList = null;
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            return C0269d.r(a10.t0(), a10.B0(), arrayList, a10.F0());
        }
        return null;
    }

    public static int k(I8.b bVar) {
        A7.m.f(bVar, "$receiver");
        if (bVar instanceof h) {
            return ((h) bVar).f2726m;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(bVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, bVar.getClass(), sb).toString());
    }

    public static J l(boolean z6, boolean z10, e eVar, e eVar2, f fVar, int i7) {
        if ((i7 & 2) != 0) {
            z10 = true;
        }
        boolean z11 = z10;
        if ((i7 & 4) != 0) {
            eVar = e.f2723m;
        }
        e eVar3 = eVar;
        if ((i7 & 8) != 0) {
            eVar2 = e.l;
        }
        e eVar4 = eVar2;
        if ((i7 & 16) != 0) {
            fVar = f.f2724a;
        }
        f fVar2 = fVar;
        A7.m.f(eVar3, "typeSystemContext");
        A7.m.f(eVar4, "kotlinTypePreparator");
        A7.m.f(fVar2, "kotlinTypeRefiner");
        return new J(z6, z11, eVar3, eVar4, fVar2);
    }

    public static a0 m(b bVar, I8.d dVar, I8.d dVar2) {
        A7.m.f(dVar, "lowerBound");
        A7.m.f(dVar2, "upperBound");
        if (!(dVar instanceof F8.A)) {
            StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb.append(bVar);
            sb.append(", ");
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, bVar.getClass(), sb).toString());
        }
        if (dVar2 instanceof F8.A) {
            return C0269d.j((F8.A) dVar, (F8.A) dVar2);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(bVar);
        sb2.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, bVar.getClass(), sb2).toString());
    }

    public static final String n(K k10) {
        StringBuilder sb = new StringBuilder();
        o("type: " + k10, sb);
        o("hashCode: " + k10.hashCode(), sb);
        o("javaClass: " + k10.getClass().getCanonicalName(), sb);
        for (InterfaceC0657j interfaceC0657jN = k10.n(); interfaceC0657jN != null; interfaceC0657jN = interfaceC0657jN.o()) {
            o("fqName: ".concat(C2203g.c.w(interfaceC0657jN)), sb);
            o("javaClass: " + interfaceC0657jN.getClass().getCanonicalName(), sb);
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final void o(String str, StringBuilder sb) {
        A7.m.f(str, "<this>");
        sb.append(str);
        sb.append('\n');
    }

    public static O p(I8.c cVar, int i7) {
        A7.m.f(cVar, "$receiver");
        if (cVar instanceof AbstractC0287w) {
            return (O) ((AbstractC0287w) cVar).o0().get(i7);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(cVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, cVar.getClass(), sb).toString());
    }

    public static Q q(I8.f fVar, int i7) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            Object obj = ((K) fVar).p().get(i7);
            A7.m.e(obj, "this.parameters[index]");
            return (Q) obj;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static a0 r(O o) {
        A7.m.f(o, "$receiver");
        if (o instanceof O) {
            return o.b().K0();
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(o);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, o.getClass(), sb).toString());
    }

    public static Q s(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            InterfaceC0654g interfaceC0654gN = ((K) fVar).n();
            if (interfaceC0654gN instanceof Q) {
                return (Q) interfaceC0654gN;
            }
            return null;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static int t(O o) {
        A7.m.f(o, "$receiver");
        if (o instanceof O) {
            int iA = o.a();
            A7.k.s(iA, "this.projectionKind");
            return l9.d.K(iA);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(o);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, o.getClass(), sb).toString());
    }

    public static boolean u(AbstractC0287w abstractC0287w, C2058c c2058c) {
        A7.m.f(abstractC0287w, "$receiver");
        A7.m.f(c2058c, "fqName");
        return abstractC0287w.k().k(c2058c);
    }

    public static boolean v(Q q4, I8.f fVar) {
        if (fVar == null ? true : fVar instanceof K) {
            return AbstractC1274a.P(q4, (K) fVar, null);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(q4);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, q4.getClass(), sb).toString());
    }

    public static boolean w(I8.d dVar, I8.d dVar2) {
        A7.m.f(dVar, "a");
        A7.m.f(dVar2, "b");
        if (!(dVar instanceof F8.A)) {
            StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb.append(dVar);
            sb.append(", ");
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar.getClass(), sb).toString());
        }
        if (dVar2 instanceof F8.A) {
            return ((F8.A) dVar).o0() == ((F8.A) dVar2).o0();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar2);
        sb2.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, dVar2.getClass(), sb2).toString());
    }

    public static boolean x(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            return N7.i.F((K) fVar, N7.n.f4843a);
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static boolean y(I8.f fVar) {
        A7.m.f(fVar, "$receiver");
        if (fVar instanceof K) {
            return ((K) fVar).n() instanceof InterfaceC0652e;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }

    public static boolean z(I8.f fVar) {
        if (fVar instanceof K) {
            InterfaceC0654g interfaceC0654gN = ((K) fVar).n();
            InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
            return (interfaceC0652e == null || interfaceC0652e.h() != 1 || interfaceC0652e.E() == 3 || interfaceC0652e.E() == 4 || interfaceC0652e.E() == 5) ? false : true;
        }
        StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb.append(fVar);
        sb.append(", ");
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A.f443a, fVar.getClass(), sb).toString());
    }
}
