package B8;

import F8.AbstractC0287w;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import j8.EnumC1555A;
import j8.Q;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j implements m, l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f778b = new j();
    public static final j c = new j();
    public static final j d = new j();

    public static /* synthetic */ void d(int i7) {
        Object[] objArr = new Object[3];
        if (i7 != 1) {
            objArr[0] = "descriptor";
        } else {
            objArr[0] = "unresolvedSuperClasses";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/serialization/deserialization/ErrorReporter$1";
        if (i7 != 2) {
            objArr[2] = "reportIncompleteHierarchy";
        } else {
            objArr[2] = "reportCannotInferVisibility";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static int e(EnumC1555A enumC1555A) {
        int i7 = enumC1555A == null ? -1 : w.f816a[enumC1555A.ordinal()];
        if (i7 == 1) {
            return 1;
        }
        if (i7 == 2) {
            return 3;
        }
        if (i7 != 3) {
            return i7 != 4 ? 1 : 2;
        }
        return 4;
    }

    @Override // B8.l
    public void a(InterfaceC0652e interfaceC0652e, ArrayList arrayList) {
        if (interfaceC0652e != null) {
            return;
        }
        d(0);
        throw null;
    }

    @Override // B8.m
    public AbstractC0287w b(Q q4, String str, F8.A a10, F8.A a11) {
        A7.m.f(q4, "proto");
        A7.m.f(str, "flexibleId");
        A7.m.f(a10, "lowerBound");
        A7.m.f(a11, "upperBound");
        throw new IllegalArgumentException("This method should not be used.");
    }

    @Override // B8.l
    public void c(InterfaceC0650c interfaceC0650c) {
        if (interfaceC0650c != null) {
            return;
        }
        d(2);
        throw null;
    }
}
