package d8;

import A.C0050y0;
import F8.AbstractC0287w;
import Q7.C0661n;
import Q7.InterfaceC0652e;
import T7.C0790i;
import T7.Q;
import a.AbstractC0904a;
import b8.C1007b;
import c8.C1036a;
import c8.C1038c;
import e8.C1236a;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import l3.P;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import w8.C2606a;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: d8.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1125l extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13601m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ N0.q f13602n;
    public final /* synthetic */ C1127n o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1125l(N0.q qVar, C1127n c1127n) {
        super(0);
        this.f13602n = qVar;
        this.o = c1127n;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r14v1, types: [T7.i, T7.t, b8.b] */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r1v21, types: [d8.n] */
    /* JADX WARN: Type inference failed for: r1v27, types: [d8.n] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // z7.InterfaceC2772a
    public final Object a() throws IllegalAccessException, InvocationTargetException {
        C1127n c1127n;
        N0.q qVar;
        List listE;
        String str;
        String str2;
        ?? EmptyList;
        P p10;
        m7.i iVar;
        ?? r14;
        List listD;
        switch (this.f13601m) {
            case 0:
                C1127n c1127n2 = this.o;
                Constructor<?>[] declaredConstructors = c1127n2.o.f9891a.getDeclaredConstructors();
                A7.m.e(declaredConstructors, "klass.declaredConstructors");
                List listI0 = P8.m.i0(P8.m.g0(new P8.g(AbstractC1996k.y(declaredConstructors), false, W7.i.f9886t), W7.j.f9887t));
                ArrayList arrayList = new ArrayList(listI0.size());
                Iterator it = listI0.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    N0.q qVar2 = c1127n2.f13635b;
                    InterfaceC0652e interfaceC0652e = c1127n2.f13605n;
                    if (!zHasNext) {
                        W7.n nVar = c1127n2.o;
                        boolean zF = nVar.f();
                        R7.f fVar = R7.g.f7535a;
                        N0.q qVar3 = this.f13602n;
                        if (zF) {
                            C1007b c1007bT1 = C1007b.t1(interfaceC0652e, fVar, true, ((C1036a) qVar2.f4399m).f12418j.b(nVar));
                            ArrayList<W7.z> arrayListE = nVar.e();
                            c1127n = c1127n2;
                            ArrayList arrayList2 = new ArrayList(arrayListE.size());
                            C1236a c1236aS = AbstractC0904a.S(2, false, false, null, 6);
                            int i7 = 0;
                            for (W7.z zVar : arrayListE) {
                                arrayList2.add(new Q(c1007bT1, null, i7, fVar, zVar.c(), ((P) qVar2.l).G(zVar.f(), c1236aS), false, false, false, null, ((C1036a) qVar2.f4399m).f12418j.b(zVar)));
                                i7++;
                            }
                            c1007bT1.l1(false);
                            C0661n c0661nE = interfaceC0652e.e();
                            A7.m.e(c0661nE, "classDescriptor.visibility");
                            if (c0661nE.equals(Z7.o.f10950b)) {
                                c0661nE = Z7.o.c;
                                A7.m.e(c0661nE, "PROTECTED_AND_PACKAGE");
                            }
                            c1007bT1.q1(arrayList2, c0661nE);
                            c1007bT1.k1(false);
                            c1007bT1.m1(interfaceC0652e.j());
                            int i10 = 2;
                            String strC = h6.f.c(c1007bT1, 2);
                            if (arrayList.isEmpty()) {
                                arrayList.add(c1007bT1);
                                ((C1036a) qVar3.f4399m).f12415g.getClass();
                            } else {
                                Iterator it2 = arrayList.iterator();
                                while (it2.hasNext()) {
                                    if (!h6.f.c((C0790i) it2.next(), i10).equals(strC)) {
                                        i10 = 2;
                                    }
                                }
                                arrayList.add(c1007bT1);
                                ((C1036a) qVar3.f4399m).f12415g.getClass();
                            }
                        } else {
                            c1127n = c1127n2;
                        }
                        ((C2606a) ((C1036a) qVar3.f4399m).f12430x).getClass();
                        A7.m.f(qVar3, "_context_receiver_0");
                        A7.m.f(interfaceC0652e, "thisDescriptor");
                        C1036a c1036a = (C1036a) qVar3.f4399m;
                        if (arrayList.isEmpty()) {
                            Class cls = nVar.f9891a;
                            boolean zIsAnnotation = cls.isAnnotation();
                            cls.isInterface();
                            if (zIsAnnotation) {
                                ?? T12 = C1007b.t1(interfaceC0652e, fVar, true, ((C1036a) qVar2.f4399m).f12418j.b(nVar));
                                if (zIsAnnotation) {
                                    List listD2 = nVar.d();
                                    EmptyList = new ArrayList(listD2.size());
                                    C1236a c1236aS2 = AbstractC0904a.S(2, true, false, null, 6);
                                    ArrayList arrayList3 = new ArrayList();
                                    ArrayList<W7.w> arrayList4 = new ArrayList();
                                    for (Object obj : listD2) {
                                        if (A7.m.a(((W7.w) obj).c(), Z7.x.f10964b)) {
                                            arrayList3.add(obj);
                                        } else {
                                            arrayList4.add(obj);
                                        }
                                    }
                                    arrayList3.size();
                                    W7.w wVar = (W7.w) AbstractC1998m.a0(arrayList3);
                                    P p11 = (P) qVar2.l;
                                    if (wVar != null) {
                                        W7.A aF = wVar.f();
                                        if (aF instanceof W7.h) {
                                            W7.h hVar = (W7.h) aF;
                                            iVar = new m7.i(p11.F(hVar, c1236aS2, true), p11.G(hVar.f9885b, c1236aS2));
                                        } else {
                                            iVar = new m7.i(p11.G(aF, c1236aS2), null);
                                        }
                                        p10 = p11;
                                        str = "classDescriptor.visibility";
                                        qVar = qVar3;
                                        str2 = "PROTECTED_AND_PACKAGE";
                                        c1127n.x(EmptyList, T12, 0, wVar, (AbstractC0287w) iVar.l, (AbstractC0287w) iVar.f19259m);
                                    } else {
                                        qVar = qVar3;
                                        p10 = p11;
                                        str = "classDescriptor.visibility";
                                        str2 = "PROTECTED_AND_PACKAGE";
                                    }
                                    int i11 = wVar != null ? 1 : 0;
                                    int i12 = 0;
                                    for (W7.w wVar2 : arrayList4) {
                                        P p12 = p10;
                                        c1127n.x(EmptyList, T12, i12 + i11, wVar2, p12.G(wVar2.f(), c1236aS2), null);
                                        i12++;
                                        p10 = p12;
                                    }
                                } else {
                                    qVar = qVar3;
                                    str = "classDescriptor.visibility";
                                    str2 = "PROTECTED_AND_PACKAGE";
                                    EmptyList = Collections.emptyList();
                                }
                                T12.l1(false);
                                C0661n c0661nE2 = interfaceC0652e.e();
                                A7.m.e(c0661nE2, str);
                                if (c0661nE2.equals(Z7.o.f10950b)) {
                                    c0661nE2 = Z7.o.c;
                                    A7.m.e(c0661nE2, str2);
                                }
                                T12.q1(EmptyList, c0661nE2);
                                T12.k1(true);
                                T12.m1(interfaceC0652e.j());
                                ((C1036a) qVar2.f4399m).f12415g.getClass();
                                r14 = T12;
                            } else {
                                qVar = qVar3;
                                r14 = 0;
                            }
                            listE = AbstractC1999n.E(r14);
                        } else {
                            qVar = qVar3;
                            listE = arrayList;
                        }
                        return AbstractC1998m.z0(c1036a.f12424r.e(qVar, listE));
                    }
                    W7.q qVar4 = (W7.q) it.next();
                    C1038c c1038cY = l9.l.Y(qVar2, qVar4);
                    C1036a c1036a2 = (C1036a) qVar2.f4399m;
                    C1007b c1007bT12 = C1007b.t1(interfaceC0652e, c1038cY, false, c1036a2.f12418j.b(qVar4));
                    N0.q qVar5 = new N0.q(c1036a2, new W2.j(qVar2, c1007bT12, qVar4, interfaceC0652e.s().size()), qVar2.o);
                    Constructor constructor = qVar4.f9895a;
                    Type[] genericParameterTypes = constructor.getGenericParameterTypes();
                    A7.m.e(genericParameterTypes, "types");
                    if (genericParameterTypes.length == 0) {
                        listD = n7.u.l;
                    } else {
                        Class declaringClass = constructor.getDeclaringClass();
                        if (declaringClass.getDeclaringClass() != null && !Modifier.isStatic(declaringClass.getModifiers())) {
                            genericParameterTypes = (Type[]) AbstractC1996k.I(genericParameterTypes, 1, genericParameterTypes.length);
                        }
                        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                        if (parameterAnnotations.length < genericParameterTypes.length) {
                            throw new IllegalStateException("Illegal generic signature: " + constructor);
                        }
                        if (parameterAnnotations.length > genericParameterTypes.length) {
                            parameterAnnotations = (Annotation[][]) AbstractC1996k.I(parameterAnnotations, parameterAnnotations.length - genericParameterTypes.length, parameterAnnotations.length);
                        }
                        listD = qVar4.d(genericParameterTypes, parameterAnnotations, constructor.isVarArgs());
                    }
                    C0050y0 c0050y0U = z.u(qVar5, c1007bT12, listD);
                    List listS = interfaceC0652e.s();
                    A7.m.e(listS, "classDescriptor.declaredTypeParameters");
                    ArrayList arrayListT = qVar4.t();
                    ArrayList arrayList5 = new ArrayList(AbstractC2000o.J(arrayListT, 10));
                    Iterator it3 = arrayListT.iterator();
                    while (it3.hasNext()) {
                        Q7.Q qB = ((c8.e) qVar5.f4400n).b((W7.B) it3.next());
                        A7.m.c(qB);
                        arrayList5.add(qB);
                    }
                    c1007bT12.r1((List) c0050y0U.f301n, com.bumptech.glide.c.D0(qVar4.e()), AbstractC1998m.n0(listS, arrayList5));
                    c1007bT12.k1(false);
                    c1007bT12.l1(c0050y0U.f300m);
                    c1007bT12.m1(interfaceC0652e.j());
                    ((C1036a) qVar5.f4399m).f12415g.getClass();
                    arrayList.add(c1007bT12);
                }
                break;
            default:
                N0.q qVar6 = this.f13602n;
                C1036a c1036a3 = (C1036a) qVar6.f4399m;
                InterfaceC0652e interfaceC0652e2 = this.o.f13605n;
                ((C2606a) c1036a3.f12430x).getClass();
                A7.m.f(qVar6, "_context_receiver_0");
                A7.m.f(interfaceC0652e2, "thisDescriptor");
                return AbstractC1998m.D0(new ArrayList());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1125l(C1127n c1127n, N0.q qVar) {
        super(0);
        this.o = c1127n;
        this.f13602n = qVar;
    }
}
