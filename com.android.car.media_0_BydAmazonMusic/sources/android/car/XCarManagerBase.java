package android.car;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class XCarManagerBase {
    public static final String TAG_X_CAR_BASE = "XCarManagerBase";
    public final XCar mCar;

    public XCarManagerBase(XCar xCar) {
        this.mCar = xCar;
    }

    public Context getContext() {
        return this.mCar.getContext();
    }

    public Handler getEventHandler() {
        return this.mCar.getEventHandler();
    }

    public <T> T handleExceptionFromCarService(Exception exc, T t) {
        if (exc instanceof RemoteException) {
            return (T) handleRemoteExceptionFromCarService((RemoteException) exc, t);
        }
        if (exc instanceof RuntimeException) {
            Log.w(TAG_X_CAR_BASE, "Car service threw Runtime Exception.", exc);
            return t;
        }
        Log.wtf(TAG_X_CAR_BASE, "Car service threw Exception.", exc);
        return t;
    }

    public <T> T handleRemoteExceptionFromCarService(Exception exc, T t) {
        return (T) this.mCar.handleRemoteExceptionFromCarService(exc, t);
    }

    public abstract void onCarDisconnected();

    public void handleRemoteExceptionFromCarService(Exception exc) {
        this.mCar.handleRemoteExceptionFromCarService(exc);
    }
}
