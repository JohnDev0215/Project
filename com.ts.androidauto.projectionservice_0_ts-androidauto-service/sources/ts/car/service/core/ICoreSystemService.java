package ts.car.service.core;

import android.os.IBinder;
import android.util.Log;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface ICoreSystemService extends IPlatformService {
    IBinder getService(String str);

    boolean isServiceReady(String str);

    boolean isServiceSupport(String str);

    boolean registerServiceStateListener(IServiceStateListener iServiceStateListener);

    boolean registerSingleServiceStateListener(String str, IServiceStateListener iServiceStateListener);

    boolean unregisterServiceStateListener(IServiceStateListener iServiceStateListener);

    boolean unregisterSingleServiceStateListener(String str, IServiceStateListener iServiceStateListener);

    default void checkAndReconnect() {
        Log.d("ICoreSystemService", "checkAndReconnect");
    }
}
