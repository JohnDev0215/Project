package p;

import android.content.Context;
import android.net.ConnectivityManager;

/* JADX INFO: loaded from: classes.dex */
public final class fn1 extends qy {
    public final ConnectivityManager f;
    public final en1 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fn1(Context context, yx2 yx2Var) {
        super(context, yx2Var);
        v03.j(yx2Var, "taskExecutor");
        Object systemService = this.b.getSystemService("connectivity");
        v03.g(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.f = (ConnectivityManager) systemService;
        this.g = new en1(0, this);
    }

    @Override // p.qy
    public final Object a() {
        return gn1.a(this.f);
    }

    @Override // p.qy
    public final void c() {
        try {
            vc1 vc1VarC = vc1.c();
            String str = gn1.a;
            vc1VarC.getClass();
            vm1.a(this.f, this.g);
        } catch (IllegalArgumentException e) {
            vc1.c().b(gn1.a, "Received exception while registering network callback", e);
        } catch (SecurityException e2) {
            vc1.c().b(gn1.a, "Received exception while registering network callback", e2);
        }
    }

    @Override // p.qy
    public final void d() {
        try {
            vc1 vc1VarC = vc1.c();
            String str = gn1.a;
            vc1VarC.getClass();
            tm1.c(this.f, this.g);
        } catch (IllegalArgumentException e) {
            vc1.c().b(gn1.a, "Received exception while unregistering network callback", e);
        } catch (SecurityException e2) {
            vc1.c().b(gn1.a, "Received exception while unregistering network callback", e2);
        }
    }
}
