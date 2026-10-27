package K7;

import A.C0024l;
import F8.AbstractC0287w;
import Q7.InterfaceC0652e;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC2000o;
import o8.C2057b;
import o8.C2061f;
import v8.AbstractC2564d;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: K7.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0396u extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3839m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0398w f3840n;
    public final /* synthetic */ C0401z o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0396u(C0398w c0398w, C0401z c0401z, int i7) {
        super(0);
        this.f3839m = i7;
        this.f3840n = c0398w;
        this.o = c0401z;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        C0401z c0401z = this.o;
        C0398w c0398w = this.f3840n;
        switch (this.f3839m) {
            case 0:
                if (c0401z.f3855m.isAnonymousClass()) {
                    return null;
                }
                C2057b c2057bS = c0401z.s();
                if (!c2057bS.c) {
                    String strB = c2057bS.i().b();
                    A7.m.e(strB, "classId.shortClassName.asString()");
                    return strB;
                }
                c0398w.getClass();
                Class cls = c0401z.f3855m;
                String simpleName = cls.getSimpleName();
                Method enclosingMethod = cls.getEnclosingMethod();
                if (enclosingMethod != null) {
                    return Q8.h.Y0(simpleName, enclosingMethod.getName() + '$', simpleName);
                }
                Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
                if (enclosingConstructor == null) {
                    return Q8.h.Z0(simpleName);
                }
                return Q8.h.Y0(simpleName, enclosingConstructor.getName() + '$', simpleName);
            case 1:
                Collection<AbstractC0287w> collectionO = c0398w.a().C().o();
                A7.m.e(collectionO, "descriptor.typeConstructor.supertypes");
                ArrayList arrayList = new ArrayList(collectionO.size());
                for (AbstractC0287w abstractC0287w : collectionO) {
                    A7.m.e(abstractC0287w, "kotlinType");
                    arrayList.add(new o0(abstractC0287w, new C0024l(abstractC0287w, c0398w, c0401z, 8)));
                }
                InterfaceC0652e interfaceC0652eA = c0398w.a();
                C2061f c2061f = N7.i.f4796e;
                if (!N7.i.b(interfaceC0652eA, N7.n.f4843a) && !N7.i.b(interfaceC0652eA, N7.n.f4845b)) {
                    if (arrayList.isEmpty()) {
                        arrayList.add(new o0(AbstractC2564d.e(c0398w.a()).e(), C0397v.f3843m));
                    } else {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            int iE = r8.d.c(((o0) it.next()).f3824a).E();
                            A7.k.s(iE, "getClassDescriptorForType(it.type).kind");
                            if (iE == 2 || iE == 5) {
                            }
                        }
                        arrayList.add(new o0(AbstractC2564d.e(c0398w.a()).e(), C0397v.f3843m));
                    }
                }
                return O8.l.d(arrayList);
            default:
                List<Q7.Q> listS = c0398w.a().s();
                A7.m.e(listS, "descriptor.declaredTypeParameters");
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listS, 10));
                for (Q7.Q q4 : listS) {
                    A7.m.e(q4, "descriptor");
                    arrayList2.add(new p0(c0401z, q4));
                }
                return arrayList2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0396u(C0401z c0401z, C0398w c0398w) {
        super(0);
        this.f3839m = 0;
        this.o = c0401z;
        this.f3840n = c0398w;
    }
}
