package Z7;

import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0660m;
import Q7.O;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0661n f10949a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0661n f10950b;
    public static final C0661n c;
    public static final HashMap d;

    static {
        U7.a aVar = U7.a.c;
        C0661n c0661n = new C0661n(aVar, 9);
        f10949a = c0661n;
        U7.c cVar = U7.c.c;
        C0661n c0661n2 = new C0661n(cVar, 10);
        f10950b = c0661n2;
        U7.b bVar = U7.b.c;
        C0661n c0661n3 = new C0661n(bVar, 11);
        c = c0661n3;
        HashMap map = new HashMap();
        d = map;
        map.put(aVar, c0661n);
        map.put(cVar, c0661n2);
        map.put(bVar, c0661n3);
    }

    public static /* synthetic */ void a(int i7) {
        String str = (i7 == 5 || i7 == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 5 || i7 == 6) ? 2 : 3];
        switch (i7) {
            case 1:
                objArr[0] = "from";
                break;
            case 2:
                objArr[0] = "first";
                break;
            case 3:
                objArr[0] = "second";
                break;
            case 4:
                objArr[0] = "visibility";
                break;
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
                break;
            default:
                objArr[0] = "what";
                break;
        }
        if (i7 == 5 || i7 == 6) {
            objArr[1] = "toDescriptorVisibility";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
        }
        if (i7 == 2 || i7 == 3) {
            objArr[2] = "areInSamePackage";
        } else if (i7 == 4) {
            objArr[2] = "toDescriptorVisibility";
        } else if (i7 != 5 && i7 != 6) {
            objArr[2] = "isVisibleForProtectedAndPackage";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 5 && i7 != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static boolean b(O o, InterfaceC0660m interfaceC0660m, InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j == null) {
            a(1);
            throw null;
        }
        int i7 = r8.d.f21880a;
        if (c(interfaceC0660m instanceof InterfaceC0650c ? r8.d.t((InterfaceC0650c) interfaceC0660m) : interfaceC0660m, interfaceC0657j)) {
            return true;
        }
        return AbstractC0662o.c.a(o, interfaceC0660m, interfaceC0657j);
    }

    public static boolean c(InterfaceC0660m interfaceC0660m, InterfaceC0657j interfaceC0657j) {
        if (interfaceC0660m == null) {
            a(2);
            throw null;
        }
        if (interfaceC0657j == null) {
            a(3);
            throw null;
        }
        Q7.D d10 = (Q7.D) r8.d.i(interfaceC0660m, Q7.D.class, false);
        Q7.D d11 = (Q7.D) r8.d.i(interfaceC0657j, Q7.D.class, false);
        return (d11 == null || d10 == null || !((T7.B) d10).f8767q.equals(((T7.B) d11).f8767q)) ? false : true;
    }
}
