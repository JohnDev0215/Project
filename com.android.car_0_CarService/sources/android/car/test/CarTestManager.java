package android.car.test;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.test.ICarTest;
import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class CarTestManager extends CarManagerBase {
    private final ICarTest mService;

    public CarTestManager(Car car, IBinder carServiceBinder) {
        super(car);
        this.mService = ICarTest.Stub.asInterface(carServiceBinder);
    }

    public void onCarDisconnected() {
    }

    public void stopCarService(IBinder token) {
        try {
            this.mService.stopCarService(token);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void startCarService(IBinder token) {
        try {
            this.mService.startCarService(token);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }
}
