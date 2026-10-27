package android.car.vms;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.vms.VmsClientManager;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
@Deprecated
public final class VmsSubscriberManager extends CarManagerBase {
    private static final long CLIENT_READY_TIMEOUT_MS = 500;
    private static final byte[] DEFAULT_PUBLISHER_INFO = new byte[0];
    private VmsClient mClient;
    private VmsClientManager.VmsClientCallback mClientCallback;
    private final VmsClientManager mClientManager;
    private final Object mLock;
    private final VmsSubscriptionHelper mSubscriptionHelper;

    public interface VmsSubscriberClientCallback {
        void onLayersAvailabilityChanged(VmsAvailableLayers vmsAvailableLayers);

        void onVmsMessageReceived(VmsLayer vmsLayer, byte[] bArr);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static VmsSubscriberManager wrap(Car car, VmsClientManager vmsClientManager) {
        if (vmsClientManager == null) {
            return null;
        }
        return new VmsSubscriberManager(car, vmsClientManager);
    }

    private VmsSubscriberManager(Car car, VmsClientManager vmsClientManager) {
        super(car);
        this.mLock = new Object();
        this.mSubscriptionHelper = new VmsSubscriptionHelper(new Consumer() { // from class: android.car.vms.-$$Lambda$VmsSubscriberManager$Kh9xFYpfnlxarctdo5xQ4xv7YRc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.setSubscriptions((Set) obj);
            }
        });
        this.mClientManager = vmsClientManager;
    }

    public void setVmsSubscriberClientCallback(Executor executor, VmsSubscriberClientCallback vmsSubscriberClientCallback) {
        CountDownLatch countDownLatch;
        Objects.requireNonNull(vmsSubscriberClientCallback, "clientCallback cannot be null");
        Objects.requireNonNull(executor, "executor cannot be null");
        synchronized (this.mLock) {
            if (this.mClientCallback != null) {
                throw new IllegalStateException("Client callback is already configured.");
            }
            countDownLatch = new CountDownLatch(1);
            SubscriberCallbackWrapper subscriberCallbackWrapper = new SubscriberCallbackWrapper(vmsSubscriberClientCallback, countDownLatch);
            this.mClientCallback = subscriberCallbackWrapper;
            this.mClientManager.registerVmsClientCallback(executor, subscriberCallbackWrapper, true);
        }
        try {
            if (countDownLatch.await(CLIENT_READY_TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                return;
            }
            clearVmsSubscriberClientCallback();
            throw new IllegalStateException("Subscriber client is not ready");
        } catch (InterruptedException e) {
            clearVmsSubscriberClientCallback();
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for subscriber client", e);
        }
    }

    public void clearVmsSubscriberClientCallback() {
        synchronized (this.mLock) {
            this.mClientManager.unregisterVmsClientCallback(this.mClientCallback);
            this.mClient = null;
            this.mClientCallback = null;
        }
    }

    public byte[] getPublisherInfo(int i) {
        byte[] providerDescription = getVmsClient().getProviderDescription(i);
        return providerDescription != null ? providerDescription : DEFAULT_PUBLISHER_INFO;
    }

    public VmsAvailableLayers getAvailableLayers() {
        return getVmsClient().getAvailableLayers();
    }

    public void subscribe(VmsLayer vmsLayer) {
        this.mSubscriptionHelper.subscribe(vmsLayer);
    }

    public void subscribe(VmsLayer vmsLayer, int i) {
        this.mSubscriptionHelper.subscribe(vmsLayer, i);
    }

    public void startMonitoring() {
        getVmsClient().setMonitoringEnabled(true);
    }

    public void unsubscribe(VmsLayer vmsLayer) {
        this.mSubscriptionHelper.unsubscribe(vmsLayer);
    }

    public void unsubscribe(VmsLayer vmsLayer, int i) {
        this.mSubscriptionHelper.unsubscribe(vmsLayer, i);
    }

    public void stopMonitoring() {
        getVmsClient().setMonitoringEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSubscriptions(Set<VmsAssociatedLayer> set) {
        getVmsClient().setSubscriptions(set);
    }

    private VmsClient getVmsClient() {
        VmsClient vmsClient;
        synchronized (this.mLock) {
            if (this.mClient == null) {
                throw new IllegalStateException("VMS client connection is not ready");
            }
            vmsClient = this.mClient;
        }
        return vmsClient;
    }

    private final class SubscriberCallbackWrapper implements VmsClientManager.VmsClientCallback {
        private final VmsSubscriberClientCallback mCallback;
        private final CountDownLatch mClientReady;

        @Override // android.car.vms.VmsClientManager.VmsClientCallback
        public void onSubscriptionStateChanged(VmsSubscriptionState vmsSubscriptionState) {
        }

        SubscriberCallbackWrapper(VmsSubscriberClientCallback vmsSubscriberClientCallback, CountDownLatch countDownLatch) {
            this.mCallback = vmsSubscriberClientCallback;
            this.mClientReady = countDownLatch;
        }

        @Override // android.car.vms.VmsClientManager.VmsClientCallback
        public void onClientConnected(VmsClient vmsClient) {
            synchronized (VmsSubscriberManager.this.mLock) {
                VmsSubscriberManager.this.mClient = vmsClient;
            }
            this.mClientReady.countDown();
        }

        @Override // android.car.vms.VmsClientManager.VmsClientCallback
        public void onLayerAvailabilityChanged(VmsAvailableLayers vmsAvailableLayers) {
            this.mCallback.onLayersAvailabilityChanged(vmsAvailableLayers);
        }

        @Override // android.car.vms.VmsClientManager.VmsClientCallback
        public void onPacketReceived(int i, VmsLayer vmsLayer, byte[] bArr) {
            this.mCallback.onVmsMessageReceived(vmsLayer, bArr);
        }
    }
}
