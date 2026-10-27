package O8;

import A7.m;
import h8.AbstractC1387d;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import n7.u;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f5197a = new j();

    public static final void a(AbstractCollection abstractCollection, Object obj) {
        if (obj != null) {
            abstractCollection.add(obj);
        }
    }

    public static final List d(ArrayList arrayList) {
        m.f(arrayList, "<this>");
        int size = arrayList.size();
        if (size == 0) {
            return u.l;
        }
        if (size == 1) {
            return AbstractC1387d.j(AbstractC1998m.Y(arrayList));
        }
        arrayList.trimToSize();
        return arrayList;
    }

    public static Object e(List list, b bVar, l lVar) {
        O3.l lVar2 = new O3.l(1);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            f(it.next(), bVar, lVar2, lVar);
        }
        return lVar.i();
    }

    public static void f(Object obj, b bVar, O3.l lVar, l lVar2) {
        if (obj != null) {
            if (lVar.f5132a.add(obj) && lVar2.c(obj)) {
                Iterator it = bVar.b(obj).iterator();
                while (it.hasNext()) {
                    f(it.next(), bVar, lVar, lVar2);
                }
                lVar2.b(obj);
                return;
            }
            return;
        }
        Object[] objArr = new Object[3];
        switch (22) {
            case 1:
            case 5:
            case 8:
            case 11:
            case 15:
            case 18:
            case 21:
            case 23:
                objArr[0] = "neighbors";
                break;
            case 2:
            case 12:
            case 16:
            case 19:
            case 24:
                objArr[0] = "visited";
                break;
            case 3:
            case 6:
            case 13:
            case 25:
                objArr[0] = "handler";
                break;
            case 4:
            case 7:
            case 17:
            case 20:
            default:
                objArr[0] = "nodes";
                break;
            case 9:
                objArr[0] = "predicate";
                break;
            case 10:
            case 14:
                objArr[0] = "node";
                break;
            case 22:
                objArr[0] = "current";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/DFS";
        switch (22) {
            case 7:
            case 8:
            case 9:
                objArr[2] = "ifAny";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                objArr[2] = "dfsFromNode";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                objArr[2] = "topologicalOrder";
                break;
            case 22:
            case 23:
            case 24:
            case 25:
                objArr[2] = "doDfs";
                break;
            default:
                objArr[2] = "dfs";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean[], java.io.Serializable] */
    public static Boolean g(List list, b bVar, InterfaceC2782k interfaceC2782k) {
        return (Boolean) e(list, bVar, new a(0, new boolean[1], interfaceC2782k));
    }

    public static final boolean h(Throwable th) {
        Class<?> superclass = th.getClass();
        while (!m.a(superclass.getCanonicalName(), "com.intellij.openapi.progress.ProcessCanceledException")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                return false;
            }
        }
        return true;
    }

    public static void j(Object obj) throws Throwable {
        if (obj instanceof k) {
            throw ((k) obj).f5196a;
        }
    }

    public void b(Object obj) {
    }

    public abstract boolean c(Object obj);

    public abstract Object i();
}
