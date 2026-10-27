package android.telephony.data;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.data.IQualifiedNetworksService;
import android.util.SparseArray;
import com.android.telephony.Rlog;
import java.util.List;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public abstract class QualifiedNetworksService extends Service {
    private static final int QNS_CREATE_NETWORK_AVAILABILITY_PROVIDER = 1;
    private static final int QNS_REMOVE_ALL_NETWORK_AVAILABILITY_PROVIDERS = 3;
    private static final int QNS_REMOVE_NETWORK_AVAILABILITY_PROVIDER = 2;
    private static final int QNS_UPDATE_QUALIFIED_NETWORKS = 4;
    public static final String QUALIFIED_NETWORKS_SERVICE_INTERFACE = "android.telephony.data.QualifiedNetworksService";
    private static final String TAG = "QualifiedNetworksService";
    private final QualifiedNetworksServiceHandler mHandler;
    private final HandlerThread mHandlerThread;
    private final SparseArray<NetworkAvailabilityProvider> mProviders = new SparseArray<>();
    public final IQualifiedNetworksServiceWrapper mBinder = new IQualifiedNetworksServiceWrapper();

    public abstract NetworkAvailabilityProvider onCreateNetworkAvailabilityProvider(int i);

    public abstract class NetworkAvailabilityProvider implements AutoCloseable {
        private IQualifiedNetworksServiceCallback mCallback;
        private SparseArray<int[]> mQualifiedNetworkTypesList = new SparseArray<>();
        private final int mSlotIndex;

        @Override // java.lang.AutoCloseable
        public abstract void close();

        public NetworkAvailabilityProvider(int i) {
            this.mSlotIndex = i;
        }

        public final int getSlotIndex() {
            return this.mSlotIndex;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void registerForQualifiedNetworkTypesChanged(IQualifiedNetworksServiceCallback iQualifiedNetworksServiceCallback) {
            this.mCallback = iQualifiedNetworksServiceCallback;
            if (iQualifiedNetworksServiceCallback != null) {
                for (int i = 0; i < this.mQualifiedNetworkTypesList.size(); i++) {
                    try {
                        this.mCallback.onQualifiedNetworkTypesChanged(this.mQualifiedNetworkTypesList.keyAt(i), this.mQualifiedNetworkTypesList.valueAt(i));
                    } catch (RemoteException e) {
                        QualifiedNetworksService.this.loge("Failed to call onQualifiedNetworksChanged. " + e);
                    }
                }
            }
        }

        public final void updateQualifiedNetworkTypes(int i, List<Integer> list) {
            QualifiedNetworksService.this.mHandler.obtainMessage(4, this.mSlotIndex, i, list.stream().mapToInt(new ToIntFunction() { // from class: android.telephony.data.-$$Lambda$QualifiedNetworksService$NetworkAvailabilityProvider$ZHUnnujBA2iJiT_HtijZp9DXQUk
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((Integer) obj).intValue();
                }
            }).toArray()).sendToTarget();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onUpdateQualifiedNetworkTypes(int i, int[] iArr) {
            this.mQualifiedNetworkTypesList.put(i, iArr);
            IQualifiedNetworksServiceCallback iQualifiedNetworksServiceCallback = this.mCallback;
            if (iQualifiedNetworksServiceCallback != null) {
                try {
                    iQualifiedNetworksServiceCallback.onQualifiedNetworkTypesChanged(i, iArr);
                } catch (RemoteException e) {
                    QualifiedNetworksService.this.loge("Failed to call onQualifiedNetworksChanged. " + e);
                }
            }
        }
    }

    private class QualifiedNetworksServiceHandler extends Handler {
        QualifiedNetworksServiceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.arg1;
            NetworkAvailabilityProvider networkAvailabilityProvider = (NetworkAvailabilityProvider) QualifiedNetworksService.this.mProviders.get(i);
            int i2 = message.what;
            if (i2 == 1) {
                if (QualifiedNetworksService.this.mProviders.get(i) != null) {
                    QualifiedNetworksService.this.loge("Network availability provider for slot " + i + " already existed.");
                    return;
                }
                NetworkAvailabilityProvider networkAvailabilityProviderOnCreateNetworkAvailabilityProvider = QualifiedNetworksService.this.onCreateNetworkAvailabilityProvider(i);
                if (networkAvailabilityProviderOnCreateNetworkAvailabilityProvider != null) {
                    QualifiedNetworksService.this.mProviders.put(i, networkAvailabilityProviderOnCreateNetworkAvailabilityProvider);
                    networkAvailabilityProviderOnCreateNetworkAvailabilityProvider.registerForQualifiedNetworkTypesChanged((IQualifiedNetworksServiceCallback) message.obj);
                    return;
                } else {
                    QualifiedNetworksService.this.loge("Failed to create network availability provider. slot index = " + i);
                    return;
                }
            }
            if (i2 == 2) {
                if (networkAvailabilityProvider != null) {
                    networkAvailabilityProvider.close();
                    QualifiedNetworksService.this.mProviders.remove(i);
                    return;
                }
                return;
            }
            if (i2 != 3) {
                if (i2 == 4 && networkAvailabilityProvider != null) {
                    networkAvailabilityProvider.onUpdateQualifiedNetworkTypes(message.arg2, (int[]) message.obj);
                    return;
                }
                return;
            }
            for (int i3 = 0; i3 < QualifiedNetworksService.this.mProviders.size(); i3++) {
                NetworkAvailabilityProvider networkAvailabilityProvider2 = (NetworkAvailabilityProvider) QualifiedNetworksService.this.mProviders.get(i3);
                if (networkAvailabilityProvider2 != null) {
                    networkAvailabilityProvider2.close();
                }
            }
            QualifiedNetworksService.this.mProviders.clear();
        }
    }

    public QualifiedNetworksService() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new QualifiedNetworksServiceHandler(handlerThread.getLooper());
        log("Qualified networks service created");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent == null || !QUALIFIED_NETWORKS_SERVICE_INTERFACE.equals(intent.getAction())) {
            loge("Unexpected intent " + intent);
            return null;
        }
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        this.mHandler.obtainMessage(3).sendToTarget();
        return false;
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mHandlerThread.quit();
    }

    private class IQualifiedNetworksServiceWrapper extends IQualifiedNetworksService.Stub {
        private IQualifiedNetworksServiceWrapper() {
        }

        @Override // android.telephony.data.IQualifiedNetworksService
        public void createNetworkAvailabilityProvider(int i, IQualifiedNetworksServiceCallback iQualifiedNetworksServiceCallback) {
            QualifiedNetworksService.this.mHandler.obtainMessage(1, i, 0, iQualifiedNetworksServiceCallback).sendToTarget();
        }

        @Override // android.telephony.data.IQualifiedNetworksService
        public void removeNetworkAvailabilityProvider(int i) {
            QualifiedNetworksService.this.mHandler.obtainMessage(2, i, 0).sendToTarget();
        }
    }

    private void log(String str) {
        Rlog.d(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loge(String str) {
        Rlog.e(TAG, str);
    }
}
