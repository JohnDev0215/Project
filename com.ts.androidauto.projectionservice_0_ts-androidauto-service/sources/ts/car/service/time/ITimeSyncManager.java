package ts.car.service.time;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface ITimeSyncManager extends IPlatformService {
    void registerTimeUpdate(ITimeUpdateCallBack iTimeUpdateCallBack) throws RemoteException;

    void setTime(long j, String str, int i) throws RemoteException;

    void unregisterTimeUpdate(ITimeUpdateCallBack iTimeUpdateCallBack) throws RemoteException;
}
