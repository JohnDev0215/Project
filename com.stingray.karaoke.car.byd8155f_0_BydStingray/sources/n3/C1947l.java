package n3;

import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.InterfaceC0986y;
import n7.AbstractC1998m;
import r.C2257p;

/* JADX INFO: renamed from: n3.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1947l implements InterfaceC0986y {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f19877m;

    public /* synthetic */ C1947l(Object obj, int i7) {
        this.l = i7;
        this.f19877m = obj;
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(androidx.lifecycle.A a10, EnumC0980s enumC0980s) {
        switch (this.l) {
            case 0:
                C1930E c1930e = (C1930E) this.f19877m;
                A7.m.f(c1930e, "this$0");
                c1930e.f19813r = enumC0980s.a();
                if (c1930e.c != null) {
                    for (C1945j c1945j : AbstractC1998m.A0(c1930e.f19804g)) {
                        c1945j.getClass();
                        c1945j.o = enumC0980s.a();
                        c1945j.i();
                    }
                }
                break;
            default:
                C2257p c2257p = (C2257p) this.f19877m;
                A7.m.f(c2257p, "this$0");
                if (enumC0980s == EnumC0980s.ON_START) {
                    c2257p.c = true;
                } else if (enumC0980s == EnumC0980s.ON_STOP) {
                    c2257p.c = false;
                }
                break;
        }
    }
}
