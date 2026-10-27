package B8;

import B5.K;
import K7.o0;
import K7.r0;
import K7.t0;
import Q7.InterfaceC0657j;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import p8.AbstractC2136k;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class o extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f791m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f792n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f793p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(s sVar, AbstractC2136k abstractC2136k, int i7, int i10) {
        super(0);
        this.f791m = i10;
        this.f792n = sVar;
        this.o = abstractC2136k;
        this.f793p = i7;
    }

    /* JADX WARN: Type inference failed for: r6v13, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f791m) {
            case 0:
                s sVar = (s) this.f792n;
                K kA = sVar.a((InterfaceC0657j) sVar.f805a.c);
                List listZ0 = kA != null ? AbstractC1998m.z0(((i) sVar.f805a.f779a).f764e.b(kA, (AbstractC2136k) this.o, this.f793p)) : null;
                return listZ0 == null ? n7.u.l : listZ0;
            case 1:
                s sVar2 = (s) this.f792n;
                K kA2 = sVar2.a((InterfaceC0657j) sVar2.f805a.c);
                List listO = kA2 != null ? ((i) sVar2.f805a.f779a).f764e.o(kA2, (AbstractC2136k) this.o, this.f793p) : null;
                return listO == null ? n7.u.l : listO;
            default:
                o0 o0Var = (o0) this.f792n;
                t0 t0Var = o0Var.f3825b;
                Type type = t0Var != null ? (Type) t0Var.a() : null;
                if (type instanceof Class) {
                    Class cls = (Class) type;
                    Class componentType = cls.isArray() ? cls.getComponentType() : Object.class;
                    A7.m.e(componentType, "{\n                      …                        }");
                    return componentType;
                }
                boolean z6 = type instanceof GenericArrayType;
                int i7 = this.f793p;
                if (z6) {
                    if (i7 == 0) {
                        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                        A7.m.e(genericComponentType, "{\n                      …                        }");
                        return genericComponentType;
                    }
                    throw new r0("Array type has been queried for a non-0th argument: " + o0Var);
                }
                if (!(type instanceof ParameterizedType)) {
                    throw new r0("Non-generic type has been queried for arguments: " + o0Var);
                }
                Type type2 = (Type) ((List) this.o.getValue()).get(i7);
                if (type2 instanceof WildcardType) {
                    WildcardType wildcardType = (WildcardType) type2;
                    Type[] lowerBounds = wildcardType.getLowerBounds();
                    A7.m.e(lowerBounds, "argument.lowerBounds");
                    Type type3 = lowerBounds.length != 0 ? lowerBounds[0] : null;
                    if (type3 == null) {
                        Type[] upperBounds = wildcardType.getUpperBounds();
                        A7.m.e(upperBounds, "argument.upperBounds");
                        type2 = (Type) AbstractC1996k.N(upperBounds);
                    } else {
                        type2 = type3;
                    }
                }
                A7.m.e(type2, "{\n                      …                        }");
                return type2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(o0 o0Var, int i7, m7.f fVar) {
        super(0);
        this.f791m = 2;
        this.f792n = o0Var;
        this.f793p = i7;
        this.o = fVar;
    }
}
