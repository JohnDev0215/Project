package a8;

import B8.l;
import Q7.InterfaceC0650c;
import java.util.Collection;
import java.util.LinkedHashSet;
import r8.m;

/* JADX INFO: renamed from: a8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0930a extends m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f11201b;
    public final /* synthetic */ LinkedHashSet c;
    public final /* synthetic */ boolean d;

    public C0930a(l lVar, LinkedHashSet linkedHashSet, boolean z6) {
        this.f11201b = lVar;
        this.c = linkedHashSet;
        this.d = z6;
    }

    public static /* synthetic */ void a(int i7) {
        Object[] objArr = new Object[3];
        if (i7 == 1) {
            objArr[0] = "fromSuper";
        } else if (i7 == 2) {
            objArr[0] = "fromCurrent";
        } else if (i7 == 3) {
            objArr[0] = "member";
        } else if (i7 != 4) {
            objArr[0] = "fakeOverride";
        } else {
            objArr[0] = "overridden";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1";
        if (i7 == 1 || i7 == 2) {
            objArr[2] = "conflict";
        } else if (i7 == 3 || i7 == 4) {
            objArr[2] = "setOverriddenDescriptors";
        } else {
            objArr[2] = "addFakeOverride";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // r8.m
    public final void b(InterfaceC0650c interfaceC0650c) {
        if (interfaceC0650c == null) {
            a(0);
            throw null;
        }
        r8.l.r(interfaceC0650c, new N7.g(this, 5));
        this.c.add(interfaceC0650c);
    }

    @Override // r8.m
    public final void d(InterfaceC0650c interfaceC0650c, InterfaceC0650c interfaceC0650c2) {
        if (interfaceC0650c2 != null) {
            return;
        }
        a(2);
        throw null;
    }

    @Override // r8.m
    public final void p(InterfaceC0650c interfaceC0650c, Collection collection) {
        if (interfaceC0650c == null) {
            a(3);
            throw null;
        }
        if (!this.d || interfaceC0650c.N() == 2) {
            interfaceC0650c.h0(collection);
        }
    }
}
