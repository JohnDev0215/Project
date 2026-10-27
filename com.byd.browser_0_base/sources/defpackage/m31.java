package defpackage;

import defpackage.m00;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: Job.kt */
/* JADX INFO: loaded from: classes.dex */
public interface m31 extends m00.b {

    /* JADX INFO: compiled from: Job.kt */
    public static final class a implements m00.c<m31> {
        public static final /* synthetic */ a e = new a();
    }

    gb0 C(boolean z, boolean z2, q31 q31Var);

    void Q0(CancellationException cancellationException);

    CancellationException R();

    boolean isActive();

    boolean start();

    hn t(r31 r31Var);
}
