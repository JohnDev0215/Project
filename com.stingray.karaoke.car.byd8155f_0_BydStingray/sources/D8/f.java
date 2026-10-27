package D8;

import Q7.C0663p;
import Q7.InterfaceC0650c;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class f extends r8.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1225b;
    public final /* synthetic */ AbstractCollection c;

    public /* synthetic */ f(AbstractCollection abstractCollection, int i7) {
        this.f1225b = i7;
        this.c = abstractCollection;
    }

    public static /* synthetic */ void a(int i7) {
        Object[] objArr = new Object[3];
        if (i7 == 1) {
            objArr[0] = "fromSuper";
        } else if (i7 != 2) {
            objArr[0] = "fakeOverride";
        } else {
            objArr[0] = "fromCurrent";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor$EnumEntryScope$4";
        if (i7 == 1 || i7 == 2) {
            objArr[2] = "conflict";
        } else {
            objArr[2] = "addFakeOverride";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // r8.m
    public final void b(InterfaceC0650c interfaceC0650c) {
        switch (this.f1225b) {
            case 0:
                A7.m.f(interfaceC0650c, "fakeOverride");
                r8.l.r(interfaceC0650c, null);
                ((ArrayList) this.c).add(interfaceC0650c);
                return;
            default:
                if (interfaceC0650c == null) {
                    a(0);
                    throw null;
                }
                r8.l.r(interfaceC0650c, null);
                ((LinkedHashSet) this.c).add(interfaceC0650c);
                return;
        }
    }

    @Override // r8.m
    public final void d(InterfaceC0650c interfaceC0650c, InterfaceC0650c interfaceC0650c2) {
        switch (this.f1225b) {
            case 0:
                A7.m.f(interfaceC0650c2, "fromCurrent");
                if (interfaceC0650c2 instanceof T7.t) {
                    ((T7.t) interfaceC0650c2).j1(C0663p.f7200a, interfaceC0650c);
                    return;
                }
                return;
            default:
                if (interfaceC0650c2 != null) {
                    return;
                }
                a(2);
                throw null;
        }
    }
}
