package android.car.vms;

import android.annotation.SystemApi;
import android.app.Service;
import android.car.Car;
import android.car.vms.VmsClientManager;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
@Deprecated
public abstract class VmsPublisherClientService extends Service {
    private static final boolean DBG = false;
    private static final String TAG = "VmsPublisherClientService";

    @GuardedBy({"mLock"})
    private Car mCar;

    @GuardedBy({"mLock"})
    private VmsClient mClient;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final VmsClientManager.VmsClientCallback mClientCallback = new PublisherClientCallback();
    private final Object mLock = new Object();

    private class PublisherClientCallback implements VmsClientManager.VmsClientCallback {
        private PublisherClientCallback() {
        }

        @Override // android.car.vms.VmsClientManager.VmsClientCallback
        public void onClientConnected(VmsClient vmsClient) {
            synchronized (VmsPublisherClientService.this.mLock) {
                VmsPublisherClientService.this.mClient = vmsClient;
            }
            VmsPublisherClientService.this.onVmsPublisherServiceReady();
        }

        @Override // android.car.vms.VmsClientManager.VmsClientCallback
        public void onLayerAvailabilityChanged(VmsAvailableLayers vmsAvailableLayers) {
        }

        @Override // android.car.vms.VmsClientManager.VmsClientCallback
        public void onPacketReceived(int i, VmsLayer vmsLayer, byte[] bArr) {
        }

        @Override // android.car.vms.VmsClientManager.VmsClientCallback
        public void onSubscriptionStateChanged(VmsSubscriptionState vmsSubscriptionState) {
            VmsPublisherClientService.this.onVmsSubscriptionChange(vmsSubscriptionState);
        }
    }

    private VmsClient getVmsClient() {
        VmsClient vmsClient;
        synchronized (this.mLock) {
            vmsClient = this.mClient;
            if (vmsClient == null) {
                throw new IllegalStateException("VMS client connection is not ready");
            }
        }
        return vmsClient;
    }

    public final int getPublisherId(byte[] bArr) {
        return getVmsClient().registerProvider(bArr);
    }

    public final VmsSubscriptionState getSubscriptions() {
        return getVmsClient().getSubscriptionState();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @VisibleForTesting
    protected void onCarLifecycleChanged(Car car, boolean z) {
        if (z) {
            VmsClientManager vmsClientManager = (VmsClientManager) car.getCarManager(Car.VEHICLE_MAP_SERVICE);
            if (vmsClientManager == null) {
                Log.e(TAG, "VmsClientManager is not available");
            } else {
                vmsClientManager.registerVmsClientCallback(new HandlerExecutor(this.mHandler), this.mClientCallback, true);
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        synchronized (this.mLock) {
            this.mCar = Car.createCar(this, this.mHandler, 0L, new Car.CarServiceLifecycleListener() { // from class: android.car.vms.a
                @Override // android.car.Car.CarServiceLifecycleListener
                public final void onLifecycleChanged(Car car, boolean z) {
                    this.f258a.onCarLifecycleChanged(car, z);
                }
            });
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        synchronized (this.mLock) {
            Car car = this.mCar;
            if (car != null) {
                car.disconnect();
                this.mCar = null;
            }
        }
    }

    protected abstract void onVmsPublisherServiceReady();

    public abstract void onVmsSubscriptionChange(VmsSubscriptionState vmsSubscriptionState);

    public final void publish(VmsLayer vmsLayer, int i, byte[] bArr) {
        getVmsClient().publishPacket(i, vmsLayer, bArr);
    }

    public final void setLayersOffering(VmsLayersOffering vmsLayersOffering) {
        getVmsClient().setProviderOfferings(vmsLayersOffering.getPublisherId(), vmsLayersOffering.getDependencies());
    }
}
