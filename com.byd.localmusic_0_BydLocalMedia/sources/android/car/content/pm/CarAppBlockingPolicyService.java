package android.car.content.pm;

import android.annotation.SystemApi;
import android.app.Service;
import android.car.Car;
import android.car.content.pm.ICarAppBlockingPolicy;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public abstract class CarAppBlockingPolicyService extends Service {
    public static final String SERVICE_INTERFACE = "android.car.content.pm.CarAppBlockingPolicyService";
    private static final String TAG = CarAppBlockingPolicyService.class.getSimpleName();
    private final ICarAppBlockingPolicyImpl mBinder = new ICarAppBlockingPolicyImpl();
    private Handler mHandler;

    protected abstract CarAppBlockingPolicy getAppBlockingPolicy();

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        stopSelf();
        return false;
    }

    private class ICarAppBlockingPolicyImpl extends ICarAppBlockingPolicy.Stub {
        private ICarAppBlockingPolicyImpl() {
        }

        @Override // android.car.content.pm.ICarAppBlockingPolicy
        public void setAppBlockingPolicySetter(ICarAppBlockingPolicySetter iCarAppBlockingPolicySetter) {
            Log.i(CarAppBlockingPolicyService.TAG, "setAppBlockingPolicySetter will set policy");
            try {
                iCarAppBlockingPolicySetter.setAppBlockingPolicy(CarAppBlockingPolicyService.this.getAppBlockingPolicy());
            } catch (RemoteException e) {
                Car.handleRemoteExceptionFromCarService(CarAppBlockingPolicyService.this, e);
            }
        }
    }
}
