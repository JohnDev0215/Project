package p;

import android.content.Context;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
public abstract class nn extends qy {
    public final o8 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nn(Context context, yx2 yx2Var) {
        super(context, yx2Var);
        v03.j(yx2Var, "taskExecutor");
        this.f = new o8(1, this);
    }

    @Override // p.qy
    public final void c() {
        vc1 vc1VarC = vc1.c();
        int i = on.a;
        vc1VarC.getClass();
        this.b.registerReceiver(this.f, e());
    }

    @Override // p.qy
    public final void d() {
        vc1 vc1VarC = vc1.c();
        int i = on.a;
        vc1VarC.getClass();
        this.b.unregisterReceiver(this.f);
    }

    public abstract IntentFilter e();
}
