package p;

import android.content.Context;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class qy {
    public final yx2 a;
    public final Context b;
    public final Object c;
    public final LinkedHashSet d;
    public Object e;

    public qy(Context context, yx2 yx2Var) {
        v03.j(yx2Var, "taskExecutor");
        this.a = yx2Var;
        Context applicationContext = context.getApplicationContext();
        v03.i(applicationContext, "context.applicationContext");
        this.b = applicationContext;
        this.c = new Object();
        this.d = new LinkedHashSet();
    }

    public abstract Object a();

    public final void b(Object obj) {
        synchronized (this.c) {
            Object obj2 = this.e;
            if (obj2 == null || !v03.b(obj2, obj)) {
                this.e = obj;
                this.a.d.execute(new ua(ou.n0(this.d), 10, this));
            }
        }
    }

    public abstract void c();

    public abstract void d();
}
