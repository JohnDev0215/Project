package defpackage;

import android.content.Context;
import defpackage.uo0;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: FontRequestWorker.java */
/* JADX INFO: loaded from: classes.dex */
public final class so0 implements Callable<uo0.a> {
    public final /* synthetic */ String a;
    public final /* synthetic */ Context b;
    public final /* synthetic */ po0 c;
    public final /* synthetic */ int d;

    public so0(String str, Context context, po0 po0Var, int i) {
        this.a = str;
        this.b = context;
        this.c = po0Var;
        this.d = i;
    }

    @Override // java.util.concurrent.Callable
    public final uo0.a call() {
        try {
            return uo0.a(this.a, this.b, this.c, this.d);
        } catch (Throwable unused) {
            return new uo0.a(-3);
        }
    }
}
