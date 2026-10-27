package android.telephony;

import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.telephony.TelephonyScanManager;
import android.util.SparseArray;
import com.android.internal.telephony.ITelephony;
import com.android.internal.util.Preconditions;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class TelephonyScanManager {
    public static final int CALLBACK_RESTRICTED_SCAN_RESULTS = 4;
    public static final int CALLBACK_SCAN_COMPLETE = 3;
    public static final int CALLBACK_SCAN_ERROR = 2;
    public static final int CALLBACK_SCAN_RESULTS = 1;
    public static final int INVALID_SCAN_ID = -1;
    public static final String SCAN_RESULT_KEY = "scanResult";
    private static final String TAG = "TelephonyScanManager";
    private final Looper mLooper;
    private final Messenger mMessenger;
    private final SparseArray<NetworkScanInfo> mScanInfo = new SparseArray<>();

    public static abstract class NetworkScanCallback {
        public void onComplete() {
        }

        public void onError(int i) {
        }

        public void onResults(List<CellInfo> list) {
        }
    }

    private static class NetworkScanInfo {
        private final NetworkScanCallback mCallback;
        private final Executor mExecutor;
        private final NetworkScanRequest mRequest;

        NetworkScanInfo(NetworkScanRequest networkScanRequest, Executor executor, NetworkScanCallback networkScanCallback) {
            this.mRequest = networkScanRequest;
            this.mExecutor = executor;
            this.mCallback = networkScanCallback;
        }
    }

    public TelephonyScanManager() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mLooper = handlerThread.getLooper();
        this.mMessenger = new Messenger(new AnonymousClass1(this.mLooper));
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyScanManager$1, reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            NetworkScanInfo networkScanInfo;
            Preconditions.checkNotNull(message, "message cannot be null");
            synchronized (TelephonyScanManager.this.mScanInfo) {
                networkScanInfo = (NetworkScanInfo) TelephonyScanManager.this.mScanInfo.get(message.arg2);
            }
            if (networkScanInfo != null) {
                final NetworkScanCallback networkScanCallback = networkScanInfo.mCallback;
                Executor executor = networkScanInfo.mExecutor;
                if (networkScanCallback == null) {
                    throw new RuntimeException("Failed to find NetworkScanCallback with id " + message.arg2);
                }
                if (executor == null) {
                    throw new RuntimeException("Failed to find Executor with id " + message.arg2);
                }
                int i = message.what;
                if (i != 1) {
                    if (i == 2) {
                        try {
                            final int i2 = message.arg1;
                            executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyScanManager$1$X9SMshZoHjJ6SzCbmgVMwQip2Q0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TelephonyScanManager.AnonymousClass1.lambda$handleMessage$1(i2, networkScanCallback);
                                }
                            });
                            return;
                        } catch (Exception e) {
                            com.android.telephony.Rlog.e(TelephonyScanManager.TAG, "Exception in networkscan callback onError", e);
                            return;
                        }
                    }
                    if (i == 3) {
                        try {
                            executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyScanManager$1$tGSpVQaVhc4GKIxjcECV-jCGYw4
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TelephonyScanManager.AnonymousClass1.lambda$handleMessage$2(networkScanCallback);
                                }
                            });
                            TelephonyScanManager.this.mScanInfo.remove(message.arg2);
                            return;
                        } catch (Exception e2) {
                            com.android.telephony.Rlog.e(TelephonyScanManager.TAG, "Exception in networkscan callback onComplete", e2);
                            return;
                        }
                    }
                    if (i != 4) {
                        com.android.telephony.Rlog.e(TelephonyScanManager.TAG, "Unhandled message " + Integer.toHexString(message.what));
                        return;
                    }
                }
                try {
                    Parcelable[] parcelableArray = message.getData().getParcelableArray(TelephonyScanManager.SCAN_RESULT_KEY);
                    final CellInfo[] cellInfoArr = new CellInfo[parcelableArray.length];
                    for (int i3 = 0; i3 < parcelableArray.length; i3++) {
                        cellInfoArr[i3] = (CellInfo) parcelableArray[i3];
                    }
                    executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyScanManager$1$jmXulbd8FzO5Qb8_Hi-Z6s_nJWI
                        @Override // java.lang.Runnable
                        public final void run() {
                            TelephonyScanManager.AnonymousClass1.lambda$handleMessage$0(cellInfoArr, networkScanCallback);
                        }
                    });
                    return;
                } catch (Exception e3) {
                    com.android.telephony.Rlog.e(TelephonyScanManager.TAG, "Exception in networkscan callback onResults", e3);
                    return;
                }
            }
            throw new RuntimeException("Failed to find NetworkScanInfo with id " + message.arg2);
        }

        static /* synthetic */ void lambda$handleMessage$0(CellInfo[] cellInfoArr, NetworkScanCallback networkScanCallback) {
            com.android.telephony.Rlog.d(TelephonyScanManager.TAG, "onResults: " + cellInfoArr.toString());
            networkScanCallback.onResults(Arrays.asList(cellInfoArr));
        }

        static /* synthetic */ void lambda$handleMessage$1(int i, NetworkScanCallback networkScanCallback) {
            com.android.telephony.Rlog.d(TelephonyScanManager.TAG, "onError: " + i);
            networkScanCallback.onError(i);
        }

        static /* synthetic */ void lambda$handleMessage$2(NetworkScanCallback networkScanCallback) {
            com.android.telephony.Rlog.d(TelephonyScanManager.TAG, "onComplete");
            networkScanCallback.onComplete();
        }
    }

    public NetworkScan requestNetworkScan(int i, NetworkScanRequest networkScanRequest, Executor executor, NetworkScanCallback networkScanCallback, String str, String str2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                synchronized (this.mScanInfo) {
                    int iRequestNetworkScan = iTelephony.requestNetworkScan(i, networkScanRequest, this.mMessenger, new Binder(), str, str2);
                    if (iRequestNetworkScan == -1) {
                        com.android.telephony.Rlog.e(TAG, "Failed to initiate network scan");
                        return null;
                    }
                    saveScanInfo(iRequestNetworkScan, networkScanRequest, executor, networkScanCallback);
                    return new NetworkScan(iRequestNetworkScan, i);
                }
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "requestNetworkScan RemoteException", e);
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "requestNetworkScan NPE", e2);
        }
        return null;
    }

    private void saveScanInfo(int i, NetworkScanRequest networkScanRequest, Executor executor, NetworkScanCallback networkScanCallback) {
        this.mScanInfo.put(i, new NetworkScanInfo(networkScanRequest, executor, networkScanCallback));
    }

    private ITelephony getITelephony() {
        return ITelephony.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyServiceRegisterer().get());
    }
}
