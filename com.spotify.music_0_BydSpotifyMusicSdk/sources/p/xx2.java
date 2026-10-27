package p;

import com.spotify.concurrency.asyncimpl.NativeTimerManagerThreadImpl;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class xx2 implements Executor {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ xx2(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((yx2) obj).c.post(runnable);
                break;
            case 1:
                ((Executor) obj).execute(new rv(11, runnable));
                break;
            case 2:
                NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = ((u10) ((com.spotify.core.corefullsessionservice.a) obj).a).a;
                v03.i(runnable, "it");
                nativeTimerManagerThreadImpl.post(runnable);
                break;
            default:
                NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl2 = ((u10) ((w00) obj).a).a;
                v03.i(runnable, "it");
                nativeTimerManagerThreadImpl2.post(runnable);
                break;
        }
    }
}
