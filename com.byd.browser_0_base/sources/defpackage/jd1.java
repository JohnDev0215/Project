package defpackage;

import android.os.Handler;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class jd1 implements Executor {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ jd1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ((Handler) obj).post(runnable);
                break;
            default:
                ((qn) obj).b.post(runnable);
                break;
        }
    }
}
