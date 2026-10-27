package defpackage;

import android.content.Context;
import defpackage.uo0;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: FontRequestWorker.java */
/* JADX INFO: loaded from: classes.dex */
public final class qo0 implements Callable<uo0.a> {
    public final /* synthetic */ String a;
    public final /* synthetic */ Context b;
    public final /* synthetic */ po0 c;
    public final /* synthetic */ int d;

    public qo0(String str, Context context, po0 po0Var, int i) {
        this.a = str;
        this.b = context;
        this.c = po0Var;
        this.d = i;
    }

    @Override // java.util.concurrent.Callable
    public final uo0.a call() {
        return uo0.a(this.a, this.b, this.c, this.d);
    }
}
