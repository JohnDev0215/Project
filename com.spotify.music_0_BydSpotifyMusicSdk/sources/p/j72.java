package p;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class j72 implements vl0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;

    public /* synthetic */ j72(xy1 xy1Var, xy1 xy1Var2, vl0 vl0Var, xy1 xy1Var3, int i) {
        this.a = i;
        this.b = xy1Var;
        this.c = xy1Var2;
        this.d = vl0Var;
        this.e = xy1Var3;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.e;
        xy1 xy1Var2 = this.d;
        xy1 xy1Var3 = this.c;
        xy1 xy1Var4 = this.b;
        switch (i) {
            case 0:
                Context context = (Context) xy1Var4.get();
                jk0 jk0Var = (jk0) xy1Var3.get();
                ni niVar = (ni) xy1Var2.get();
                return new s61(context, jk0Var, niVar);
            default:
                return new sx2((Executor) xy1Var4.get(), (jk0) xy1Var3.get(), (hy2) xy1Var2.get(), (wh2) xy1Var.get());
        }
    }
}
