package com.android.car.developeroptions.network.telephony;

import android.telephony.CellInfo;
import android.telephony.NetworkScan;
import android.telephony.NetworkScanRequest;
import android.telephony.RadioAccessSpecifier;
import android.telephony.TelephonyManager;
import android.telephony.TelephonyScanManager;
import android.util.Log;
import com.android.internal.telephony.CellNetworkScanResult;
import com.android.internal.telephony.OperatorInfo;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class NetworkScanHelper {
    private static final NetworkScanRequest NETWORK_SCAN_REQUEST = new NetworkScanRequest(0, new RadioAccessSpecifier[]{new RadioAccessSpecifier(1, null, null), new RadioAccessSpecifier(3, null, null), new RadioAccessSpecifier(2, null, null)}, 5, 300, true, 3, null);
    private final Executor mExecutor;
    private final TelephonyScanManager.NetworkScanCallback mInternalNetworkScanCallback = new NetworkScanCallbackImpl();
    private final NetworkScanCallback mNetworkScanCallback;
    private ListenableFuture<List<CellInfo>> mNetworkScanFuture;
    private NetworkScan mNetworkScanRequester;
    private final TelephonyManager mTelephonyManager;

    public interface NetworkScanCallback {
        void onComplete();

        void onError(int i);

        void onResults(List<CellInfo> list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertToScanErrorCode(int i) {
        return i != 2 ? 1 : 10000;
    }

    public NetworkScanHelper(TelephonyManager telephonyManager, NetworkScanCallback networkScanCallback, Executor executor) {
        this.mTelephonyManager = telephonyManager;
        this.mNetworkScanCallback = networkScanCallback;
        this.mExecutor = executor;
    }

    public void startNetworkScan(int i) {
        if (i == 1) {
            SettableFuture settableFutureCreate = SettableFuture.create();
            this.mNetworkScanFuture = settableFutureCreate;
            Futures.addCallback(settableFutureCreate, new FutureCallback<List<CellInfo>>() { // from class: com.android.car.developeroptions.network.telephony.NetworkScanHelper.1
                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(List<CellInfo> list) {
                    NetworkScanHelper.this.onResults(list);
                    NetworkScanHelper.this.onComplete();
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable th) {
                    NetworkScanHelper.this.onError(Integer.parseInt(th.getMessage()));
                }
            }, MoreExecutors.directExecutor());
            this.mExecutor.execute(new NetworkScanSyncTask(this.mTelephonyManager, (SettableFuture) this.mNetworkScanFuture));
            return;
        }
        if (i == 2) {
            Log.d("NetworkScanHelper", "start network scan async");
            this.mNetworkScanRequester = this.mTelephonyManager.requestNetworkScan(NETWORK_SCAN_REQUEST, this.mExecutor, this.mInternalNetworkScanCallback);
        }
    }

    public void stopNetworkQuery() {
        NetworkScan networkScan = this.mNetworkScanRequester;
        if (networkScan != null) {
            networkScan.stopScan();
            this.mNetworkScanFuture = null;
        }
        ListenableFuture<List<CellInfo>> listenableFuture = this.mNetworkScanFuture;
        if (listenableFuture != null) {
            listenableFuture.cancel(true);
            this.mNetworkScanFuture = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResults(List<CellInfo> list) {
        this.mNetworkScanCallback.onResults(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onComplete() {
        this.mNetworkScanCallback.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(int i) {
        this.mNetworkScanCallback.onError(i);
    }

    private final class NetworkScanCallbackImpl extends TelephonyScanManager.NetworkScanCallback {
        private NetworkScanCallbackImpl() {
        }

        @Override // android.telephony.TelephonyScanManager.NetworkScanCallback
        public void onResults(List<CellInfo> list) {
            Log.d("NetworkScanHelper", "async scan onResults() results = " + list);
            NetworkScanHelper.this.onResults(list);
        }

        @Override // android.telephony.TelephonyScanManager.NetworkScanCallback
        public void onComplete() {
            Log.d("NetworkScanHelper", "async scan onComplete()");
            NetworkScanHelper.this.onComplete();
        }

        @Override // android.telephony.TelephonyScanManager.NetworkScanCallback
        public void onError(int i) {
            Log.d("NetworkScanHelper", "async scan onError() errorCode = " + i);
            NetworkScanHelper.this.onError(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class NetworkScanSyncTask implements Runnable {
        private final SettableFuture<List<CellInfo>> mCallback;
        private final TelephonyManager mTelephonyManager;

        NetworkScanSyncTask(TelephonyManager telephonyManager, SettableFuture<List<CellInfo>> settableFuture) {
            this.mTelephonyManager = telephonyManager;
            this.mCallback = settableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.d("NetworkScanHelper", "sync scan start");
            CellNetworkScanResult availableNetworks = this.mTelephonyManager.getAvailableNetworks();
            if (availableNetworks.getStatus() == 1) {
                List<CellInfo> list = (List) availableNetworks.getOperators().stream().map(new Function() { // from class: com.android.car.developeroptions.network.telephony.-$$Lambda$NetworkScanHelper$NetworkScanSyncTask$k4JiL23kJGf9OEq6Zb9GvSEJdoI
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return CellInfoUtil.convertOperatorInfoToCellInfo((OperatorInfo) obj);
                    }
                }).collect(Collectors.toList());
                Log.d("NetworkScanHelper", "sync scan complete");
                this.mCallback.set(list);
                return;
            }
            this.mCallback.setException(new Throwable(Integer.toString(NetworkScanHelper.convertToScanErrorCode(availableNetworks.getStatus()))));
        }
    }
}
