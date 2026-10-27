package ts.car.service.threedmodel;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IThreedModelManager extends IPlatformService {
    void notifySurfaceChanged(int i, int i2) throws RemoteException;

    void notifySurfaceCreated(Surface surface) throws RemoteException;

    void notifySurfaceDestroyed() throws RemoteException;

    void sendScreenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    void setDataBool(String str, boolean z) throws RemoteException;

    void setDataInt(String str, int i) throws RemoteException;

    void setDataReal(String str, float f) throws RemoteException;

    void setDataString(String str, String str2) throws RemoteException;

    void subscribeListener(IThreedModelListener iThreedModelListener);

    void unsubscribeListener(IThreedModelListener iThreedModelListener) throws RemoteException;
}
