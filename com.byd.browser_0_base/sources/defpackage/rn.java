package defpackage;

import java.util.Random;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: ChildProcessConnectionMetrics.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rn {
    public static rn f;
    public boolean a;
    public re b;
    public final q8 c = new q8(0);
    public final Random d = new Random();
    public final i22 e = new i22(7, this);

    public static rn a() {
        if (f == null) {
            rn rnVar = new rn();
            f = rnVar;
            PostTask.c(rh3.a, new an2(8, rnVar));
        }
        return f;
    }
}
