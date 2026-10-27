package p;

import io.reactivex.rxjava3.core.Flowable;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class gp2 {
    public final bt1 a;
    public final n0 b;
    public final String c;

    public gp2(bt1 bt1Var, n0 n0Var, Flowable flowable) {
        this.a = bt1Var;
        this.b = n0Var;
        new AtomicReference();
        this.c = "";
    }

    public final fp2 a() {
        this.a.getClass();
        String string = UUID.randomUUID().toString();
        v03.i(string, "randomUUID().toString()");
        this.b.getClass();
        String lowerCase = "".toLowerCase(Locale.ROOT);
        v03.i(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return new fp2(string, lowerCase, this.c);
    }
}
