package c5;

import android.util.Base64;
import e2.p0;
import h5.C1369A;
import h5.ScheduledExecutorServiceC1372D;
import h5.z;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import n2.C1910j;

/* JADX INFO: renamed from: c5.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1033j implements InterfaceC1031h {
    public final /* synthetic */ int l;

    @Override // c5.InterfaceC1031h
    public final Object get() {
        switch (this.l) {
            case 0:
                throw new IllegalStateException();
            case 1:
                ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
                if (executorServiceNewSingleThreadExecutor instanceof z) {
                    return (z) executorServiceNewSingleThreadExecutor;
                }
                return executorServiceNewSingleThreadExecutor instanceof ScheduledExecutorService ? new ScheduledExecutorServiceC1372D((ScheduledExecutorService) executorServiceNewSingleThreadExecutor) : new C1369A(executorServiceNewSingleThreadExecutor);
            case 2:
                return new C1910j();
            case 3:
                byte[] bArr = new byte[12];
                o2.g.f20418i.nextBytes(bArr);
                return Base64.encodeToString(bArr, 10);
            default:
                try {
                    Class<?> cls = Class.forName("androidx.media3.effect.DefaultVideoFrameProcessor$Factory$Builder");
                    Object objInvoke = cls.getMethod("build", null).invoke(cls.getConstructor(null).newInstance(null), null);
                    objInvoke.getClass();
                    return (p0) objInvoke;
                } catch (Exception e10) {
                    throw new IllegalStateException(e10);
                }
        }
    }
}
