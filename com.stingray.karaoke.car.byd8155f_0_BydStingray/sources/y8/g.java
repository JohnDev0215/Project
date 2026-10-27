package y8;

import Q7.InterfaceC0650c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class g extends r8.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ArrayList f24204b;
    public final /* synthetic */ h c;

    public g(ArrayList arrayList, h hVar) {
        this.f24204b = arrayList;
        this.c = hVar;
    }

    @Override // r8.m
    public final void b(InterfaceC0650c interfaceC0650c) {
        A7.m.f(interfaceC0650c, "fakeOverride");
        r8.l.r(interfaceC0650c, null);
        this.f24204b.add(interfaceC0650c);
    }

    @Override // r8.m
    public final void d(InterfaceC0650c interfaceC0650c, InterfaceC0650c interfaceC0650c2) {
        A7.m.f(interfaceC0650c2, "fromCurrent");
        throw new IllegalStateException(("Conflict in scope of " + this.c.f24205b + ": " + interfaceC0650c + " vs " + interfaceC0650c2).toString());
    }
}
