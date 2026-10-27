package p;

import android.os.StrictMode;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fw implements yy1 {
    public final /* synthetic */ int a;

    @Override // p.yy1
    public final Object get() {
        switch (this.a) {
            case 0:
                return Collections.emptySet();
            case 1:
                return null;
            case 2:
                w81 w81Var = ExecutorsRegistrar.a;
                StrictMode.ThreadPolicy.Builder builderDetectNetwork = new StrictMode.ThreadPolicy.Builder().detectNetwork();
                builderDetectNetwork.detectResourceMismatches();
                builderDetectNetwork.detectUnbufferedIo();
                return new x80(Executors.newFixedThreadPool(4, new a50("Firebase Background", 10, builderDetectNetwork.penaltyLog().build())), (ScheduledExecutorService) ExecutorsRegistrar.d.get());
            case 3:
                w81 w81Var2 = ExecutorsRegistrar.a;
                return new x80(Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors()), new a50("Firebase Lite", 0, new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())), (ScheduledExecutorService) ExecutorsRegistrar.d.get());
            case 4:
                w81 w81Var3 = ExecutorsRegistrar.a;
                return new x80(Executors.newCachedThreadPool(new a50("Firebase Blocking", 11, null)), (ScheduledExecutorService) ExecutorsRegistrar.d.get());
            default:
                w81 w81Var4 = ExecutorsRegistrar.a;
                return Executors.newSingleThreadScheduledExecutor(new a50("Firebase Scheduler", 0, null));
        }
    }
}
