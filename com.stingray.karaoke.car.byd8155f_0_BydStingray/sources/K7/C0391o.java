package K7;

import F8.AbstractC0287w;
import Q7.InterfaceC0650c;
import b8.InterfaceC1006a;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import n7.AbstractC2003r;
import o8.C2058c;
import q7.InterfaceC2189c;
import v8.AbstractC2564d;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: K7.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0391o extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3821m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r f3822n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0391o(r rVar, int i7) {
        super(0);
        this.f3821m = i7;
        this.f3822n = rVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        int i7;
        Type[] lowerBounds;
        Type type = null;
        int i10 = 0;
        r rVar = this.f3822n;
        switch (this.f3821m) {
            case 0:
                int size = (rVar.m() ? 1 : 0) + rVar.p().size();
                int size2 = (rVar.p().size() + 31) / 32;
                Object[] objArr = new Object[size + size2 + 1];
                Iterator it = rVar.p().iterator();
                while (it.hasNext()) {
                    W w10 = (W) ((H7.n) it.next());
                    Q7.J jA = w10.a();
                    T7.Q q4 = jA instanceof T7.Q ? (T7.Q) jA : null;
                    boolean zA = q4 != null ? AbstractC2564d.a(q4) : false;
                    int i11 = w10.f3756b;
                    if (zA) {
                        o0 o0VarC = w10.c();
                        C2058c c2058c = A0.f3718a;
                        AbstractC0287w abstractC0287w = o0VarC.f3824a;
                        if (abstractC0287w == null || !r8.h.c(abstractC0287w)) {
                            o0 o0VarC2 = w10.c();
                            t0 t0Var = o0VarC2.f3825b;
                            Type typeR = t0Var != null ? (Type) t0Var.a() : null;
                            if (typeR == null) {
                                Type type2 = t0Var != null ? (Type) t0Var.a() : null;
                                typeR = type2 != null ? type2 : H7.H.r(o0VarC2, false);
                            }
                            objArr[i11] = A0.e(typeR);
                        }
                    }
                    Q7.J jA2 = w10.a();
                    if ((jA2 instanceof T7.Q) && ((T7.Q) jA2).f8826v != null) {
                        Class clsI = n7.z.i(com.bumptech.glide.c.g0(w10.c()));
                        if (!clsI.isArray()) {
                            throw new r0("Cannot instantiate the default empty array of type " + clsI.getSimpleName() + ", because it is not an array type");
                        }
                        Object objNewInstance = Array.newInstance(clsI.getComponentType(), 0);
                        A7.m.e(objNewInstance, "type.jvmErasure.java.run…\"\n            )\n        }");
                        objArr[i11] = objNewInstance;
                    }
                }
                for (int i12 = 0; i12 < size2; i12++) {
                    objArr[size + i12] = 0;
                }
                return objArr;
            case 1:
                return A0.d(rVar.n());
            case 2:
                InterfaceC0650c interfaceC0650cN = rVar.n();
                ArrayList arrayList = new ArrayList();
                if (rVar.r()) {
                    i7 = 0;
                } else {
                    T7.u uVarG = A0.g(interfaceC0650cN);
                    if (uVarG != null) {
                        arrayList.add(new W(rVar, 0, H7.m.l, new C0392p(uVarG, 0)));
                        i7 = 1;
                    } else {
                        i7 = 0;
                    }
                    T7.u uVarK = interfaceC0650cN.K();
                    if (uVarK != null) {
                        arrayList.add(new W(rVar, i7, H7.m.f2852m, new C0392p(uVarK, 1)));
                        i7++;
                    }
                }
                int size3 = interfaceC0650cN.D0().size();
                int i13 = 0;
                while (i13 < size3) {
                    arrayList.add(new W(rVar, i7, H7.m.f2853n, new C0393q(i13, i10, interfaceC0650cN)));
                    i13++;
                    i7++;
                }
                if (rVar.q() && (interfaceC0650cN instanceof InterfaceC1006a) && arrayList.size() > 1) {
                    AbstractC2003r.K(arrayList, new E5.y(2));
                }
                arrayList.trimToSize();
                return arrayList;
            case 3:
                if (rVar.m()) {
                    Object objI0 = AbstractC1998m.i0(rVar.i().r());
                    ParameterizedType parameterizedType = objI0 instanceof ParameterizedType ? (ParameterizedType) objI0 : null;
                    if (A7.m.a(parameterizedType != null ? parameterizedType.getRawType() : null, InterfaceC2189c.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        A7.m.e(actualTypeArguments, "continuationType.actualTypeArguments");
                        Object objY = AbstractC1996k.Y(actualTypeArguments);
                        WildcardType wildcardType = objY instanceof WildcardType ? (WildcardType) objY : null;
                        if (wildcardType != null && (lowerBounds = wildcardType.getLowerBounds()) != null) {
                            type = (Type) AbstractC1996k.N(lowerBounds);
                        }
                    }
                }
                return type == null ? rVar.i().p() : type;
            case 4:
                AbstractC0287w abstractC0287wP = rVar.n().p();
                A7.m.c(abstractC0287wP);
                return new o0(abstractC0287wP, new C0391o(rVar, 3));
            default:
                List<Q7.Q> listT = rVar.n().t();
                A7.m.e(listT, "descriptor.typeParameters");
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listT, 10));
                for (Q7.Q q10 : listT) {
                    A7.m.e(q10, "descriptor");
                    arrayList2.add(new p0(rVar, q10));
                }
                return arrayList2;
        }
    }
}
