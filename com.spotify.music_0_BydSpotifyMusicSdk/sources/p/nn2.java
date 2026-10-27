package p;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class nn2 {
    public final qy a;
    public final pl b;
    public final qy c;
    public final qy d;

    public nn2(Context context, yx2 yx2Var) {
        Context applicationContext = context.getApplicationContext();
        v03.i(applicationContext, "context.applicationContext");
        pl plVar = new pl(applicationContext, yx2Var, 0);
        Context applicationContext2 = context.getApplicationContext();
        v03.i(applicationContext2, "context.applicationContext");
        pl plVar2 = new pl(applicationContext2, yx2Var, 1);
        Context applicationContext3 = context.getApplicationContext();
        v03.i(applicationContext3, "context.applicationContext");
        String str = gn1.a;
        v03.j(yx2Var, "taskExecutor");
        fn1 fn1Var = new fn1(applicationContext3, yx2Var);
        Context applicationContext4 = context.getApplicationContext();
        v03.i(applicationContext4, "context.applicationContext");
        pl plVar3 = new pl(applicationContext4, yx2Var, 2);
        v03.j(yx2Var, "taskExecutor");
        this.a = plVar;
        this.b = plVar2;
        this.c = fn1Var;
        this.d = plVar3;
    }
}
