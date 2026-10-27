package p;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class y60 implements v11, w11 {
    public final yy1 a;
    public final Context b;
    public final yy1 c;
    public final Set d;
    public final Executor e;

    public y60(Context context, String str, Set set, yy1 yy1Var, Executor executor) {
        this.a = new nu0(context, str);
        this.d = set;
        this.e = executor;
        this.c = yy1Var;
        this.b = context;
    }

    public final b33 a() {
        int i = 1;
        if (!zr2.a(this.b)) {
            return ci1.t("");
        }
        return ci1.i(this.e, new x60(this, i));
    }

    public final void b() {
        if (this.d.size() <= 0) {
            ci1.t(null);
        } else if (!zr2.a(this.b)) {
            ci1.t(null);
        } else {
            ci1.i(this.e, new x60(this, 0));
        }
    }
}
