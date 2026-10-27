package android.net.wifi.nl80211;

import android.annotation.SystemApi;
import android.app.AlarmManager;
import android.content.Context;
import android.net.wifi.nl80211.IApInterfaceEventCallback;
import android.net.wifi.nl80211.IPnoScanEvent;
import android.net.wifi.nl80211.IScanEvent;
import android.net.wifi.nl80211.ISendMgmtFrameEvent;
import android.net.wifi.nl80211.IWificond;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class WifiNl80211Manager {
    public static final int SCAN_TYPE_PNO_SCAN = 1;
    public static final int SCAN_TYPE_SINGLE_SCAN = 0;
    public static final int SEND_MGMT_FRAME_ERROR_ALREADY_STARTED = 5;
    public static final int SEND_MGMT_FRAME_ERROR_MCS_UNSUPPORTED = 2;
    public static final int SEND_MGMT_FRAME_ERROR_NO_ACK = 3;
    public static final int SEND_MGMT_FRAME_ERROR_TIMEOUT = 4;
    public static final int SEND_MGMT_FRAME_ERROR_UNKNOWN = 1;
    private static final int SEND_MGMT_FRAME_TIMEOUT_MS = 1000;
    private static final String TAG = "WifiNl80211Manager";
    private static final String TIMEOUT_ALARM_TAG = "WifiNl80211Manager Send Management Frame Timeout";
    private AlarmManager mAlarmManager;
    private HashMap<String, IApInterfaceEventCallback> mApInterfaceListeners;
    private HashMap<String, IApInterface> mApInterfaces;
    private HashMap<String, IClientInterface> mClientInterfaces;
    private Runnable mDeathEventHandler;
    private Handler mEventHandler;
    private HashMap<String, IPnoScanEvent> mPnoScanEventHandlers;
    private HashMap<String, IScanEvent> mScanEventHandlers;
    private AtomicBoolean mSendMgmtFrameInProgress;
    private boolean mVerboseLoggingEnabled;
    private IWificond mWificond;
    private HashMap<String, IWifiScannerImpl> mWificondScanners;

    public interface PnoScanRequestCallback {
        void onPnoRequestFailed();

        void onPnoRequestSucceeded();
    }

    public interface ScanEventCallback {
        void onScanFailed();

        void onScanResultReady();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScanResultType {
    }

    public interface SendMgmtFrameCallback {
        void onAck(int i);

        void onFailure(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SendMgmtFrameError {
    }

    public interface SoftApCallback {
        void onConnectedClientsChanged(NativeWifiClient nativeWifiClient, boolean z);

        void onFailure();

        void onSoftApChannelSwitched(int i, int i2);
    }

    public static OemSecurityType parseOemSecurityTypeElement(int i, int i2, byte[] bArr) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ScanEventHandler extends IScanEvent.Stub {
        private ScanEventCallback mCallback;
        private Executor mExecutor;

        ScanEventHandler(Executor executor, ScanEventCallback scanEventCallback) {
            this.mExecutor = executor;
            this.mCallback = scanEventCallback;
        }

        @Override // android.net.wifi.nl80211.IScanEvent
        public void OnScanResultReady() {
            Log.d(WifiNl80211Manager.TAG, "Scan result ready event");
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$ScanEventHandler$XjI5kFSu0f9cphx_qo_5NAvCh5I
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnScanResultReady$0$WifiNl80211Manager$ScanEventHandler();
                }
            });
        }

        public /* synthetic */ void lambda$OnScanResultReady$0$WifiNl80211Manager$ScanEventHandler() {
            this.mCallback.onScanResultReady();
        }

        @Override // android.net.wifi.nl80211.IScanEvent
        public void OnScanFailed() {
            Log.d(WifiNl80211Manager.TAG, "Scan failed event");
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$ScanEventHandler$JhvqssvPGZY7hdAZwipalm6BXM0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnScanFailed$1$WifiNl80211Manager$ScanEventHandler();
                }
            });
        }

        public /* synthetic */ void lambda$OnScanFailed$1$WifiNl80211Manager$ScanEventHandler() {
            this.mCallback.onScanFailed();
        }
    }

    public static class SignalPollResult {
        public final int associationFrequencyMHz;
        public final int currentRssiDbm;
        public final int rxBitrateMbps;
        public final int txBitrateMbps;

        public SignalPollResult(int i, int i2, int i3, int i4) {
            this.currentRssiDbm = i;
            this.txBitrateMbps = i2;
            this.rxBitrateMbps = i3;
            this.associationFrequencyMHz = i4;
        }
    }

    public static class TxPacketCounters {
        public final int txPacketFailed;
        public final int txPacketSucceeded;

        public TxPacketCounters(int i, int i2) {
            this.txPacketSucceeded = i;
            this.txPacketFailed = i2;
        }
    }

    public WifiNl80211Manager(Context context) {
        this.mVerboseLoggingEnabled = false;
        this.mClientInterfaces = new HashMap<>();
        this.mApInterfaces = new HashMap<>();
        this.mWificondScanners = new HashMap<>();
        this.mScanEventHandlers = new HashMap<>();
        this.mPnoScanEventHandlers = new HashMap<>();
        this.mApInterfaceListeners = new HashMap<>();
        this.mSendMgmtFrameInProgress = new AtomicBoolean(false);
        this.mAlarmManager = (AlarmManager) context.getSystemService(AlarmManager.class);
        this.mEventHandler = new Handler(context.getMainLooper());
    }

    public WifiNl80211Manager(Context context, IWificond iWificond) {
        this(context);
        this.mWificond = iWificond;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class PnoScanEventHandler extends IPnoScanEvent.Stub {
        private ScanEventCallback mCallback;
        private Executor mExecutor;

        PnoScanEventHandler(Executor executor, ScanEventCallback scanEventCallback) {
            this.mExecutor = executor;
            this.mCallback = scanEventCallback;
        }

        @Override // android.net.wifi.nl80211.IPnoScanEvent
        public void OnPnoNetworkFound() {
            Log.d(WifiNl80211Manager.TAG, "Pno scan result event");
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$PnoScanEventHandler$yYSsPsPATilbeLB7fwmasf4d9II
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnPnoNetworkFound$0$WifiNl80211Manager$PnoScanEventHandler();
                }
            });
        }

        public /* synthetic */ void lambda$OnPnoNetworkFound$0$WifiNl80211Manager$PnoScanEventHandler() {
            this.mCallback.onScanResultReady();
        }

        @Override // android.net.wifi.nl80211.IPnoScanEvent
        public void OnPnoScanFailed() {
            Log.d(WifiNl80211Manager.TAG, "Pno Scan failed event");
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$PnoScanEventHandler$oC8WJxgsPPFjKdQbqbGyPpsRuIQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnPnoScanFailed$1$WifiNl80211Manager$PnoScanEventHandler();
                }
            });
        }

        public /* synthetic */ void lambda$OnPnoScanFailed$1$WifiNl80211Manager$PnoScanEventHandler() {
            this.mCallback.onScanFailed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ApInterfaceEventCallback extends IApInterfaceEventCallback.Stub {
        private Executor mExecutor;
        private SoftApCallback mSoftApListener;

        private int toFrameworkBandwidth(int i) {
            switch (i) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                    return 6;
                default:
                    return 0;
            }
        }

        ApInterfaceEventCallback(Executor executor, SoftApCallback softApCallback) {
            this.mExecutor = executor;
            this.mSoftApListener = softApCallback;
        }

        @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
        public void onConnectedClientsChanged(final NativeWifiClient nativeWifiClient, final boolean z) {
            if (WifiNl80211Manager.this.mVerboseLoggingEnabled) {
                Log.d(WifiNl80211Manager.TAG, "onConnectedClientsChanged called with " + nativeWifiClient.getMacAddress() + " isConnected: " + z);
            }
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$ApInterfaceEventCallback$zuIZfwyoTzyDkXzsmkaKM1lhmGY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onConnectedClientsChanged$0$WifiNl80211Manager$ApInterfaceEventCallback(nativeWifiClient, z);
                }
            });
        }

        public /* synthetic */ void lambda$onConnectedClientsChanged$0$WifiNl80211Manager$ApInterfaceEventCallback(NativeWifiClient nativeWifiClient, boolean z) {
            this.mSoftApListener.onConnectedClientsChanged(nativeWifiClient, z);
        }

        @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
        public void onSoftApChannelSwitched(final int i, final int i2) {
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$ApInterfaceEventCallback$20esFPHe8jPziHIFM07VkYdHW4g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSoftApChannelSwitched$1$WifiNl80211Manager$ApInterfaceEventCallback(i, i2);
                }
            });
        }

        public /* synthetic */ void lambda$onSoftApChannelSwitched$1$WifiNl80211Manager$ApInterfaceEventCallback(int i, int i2) {
            this.mSoftApListener.onSoftApChannelSwitched(i, toFrameworkBandwidth(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class SendMgmtFrameEvent extends ISendMgmtFrameEvent.Stub {
        private SendMgmtFrameCallback mCallback;
        private Executor mExecutor;
        private AlarmManager.OnAlarmListener mTimeoutCallback = new AlarmManager.OnAlarmListener() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$om0NftZUGrqsz3A_5FK5rjlHcxQ
            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                this.f$0.lambda$new$2$WifiNl80211Manager$SendMgmtFrameEvent();
            }
        };
        private boolean mWasCalled = false;

        private void runIfFirstCall(Runnable runnable) {
            if (this.mWasCalled) {
                return;
            }
            this.mWasCalled = true;
            WifiNl80211Manager.this.mSendMgmtFrameInProgress.set(false);
            runnable.run();
        }

        SendMgmtFrameEvent(Executor executor, SendMgmtFrameCallback sendMgmtFrameCallback) {
            this.mExecutor = executor;
            this.mCallback = sendMgmtFrameCallback;
            WifiNl80211Manager.this.mAlarmManager.set(2, SystemClock.elapsedRealtime() + 1000, WifiNl80211Manager.TIMEOUT_ALARM_TAG, this.mTimeoutCallback, WifiNl80211Manager.this.mEventHandler);
        }

        public /* synthetic */ void lambda$new$2$WifiNl80211Manager$SendMgmtFrameEvent() {
            runIfFirstCall(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$8iM8BXWNf5vbe87gyAXgPEB0GY8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$1$WifiNl80211Manager$SendMgmtFrameEvent();
                }
            });
        }

        public /* synthetic */ void lambda$new$1$WifiNl80211Manager$SendMgmtFrameEvent() {
            if (WifiNl80211Manager.this.mVerboseLoggingEnabled) {
                Log.e(WifiNl80211Manager.TAG, "Timed out waiting for ACK");
            }
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$WTpNWgu59B-YEvoDOuXwd5QK4k8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$0$WifiNl80211Manager$SendMgmtFrameEvent();
                }
            });
        }

        public /* synthetic */ void lambda$new$0$WifiNl80211Manager$SendMgmtFrameEvent() {
            this.mCallback.onFailure(4);
        }

        @Override // android.net.wifi.nl80211.ISendMgmtFrameEvent
        public void OnAck(final int i) {
            WifiNl80211Manager.this.mEventHandler.post(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$lqXW7dvgP4SiZa9axpAVyijTCkM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnAck$5$WifiNl80211Manager$SendMgmtFrameEvent(i);
                }
            });
        }

        public /* synthetic */ void lambda$OnAck$5$WifiNl80211Manager$SendMgmtFrameEvent(final int i) {
            runIfFirstCall(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$htBehqSd9shFaL7yeEodIlg564Y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnAck$4$WifiNl80211Manager$SendMgmtFrameEvent(i);
                }
            });
        }

        public /* synthetic */ void lambda$OnAck$4$WifiNl80211Manager$SendMgmtFrameEvent(final int i) {
            WifiNl80211Manager.this.mAlarmManager.cancel(this.mTimeoutCallback);
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$MGjeo4tfBDJYD0HHYHbK5zEbv3I
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnAck$3$WifiNl80211Manager$SendMgmtFrameEvent(i);
                }
            });
        }

        public /* synthetic */ void lambda$OnAck$3$WifiNl80211Manager$SendMgmtFrameEvent(int i) {
            this.mCallback.onAck(i);
        }

        @Override // android.net.wifi.nl80211.ISendMgmtFrameEvent
        public void OnFailure(final int i) {
            WifiNl80211Manager.this.mEventHandler.post(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$9KLACn0Uhup4kuzEujGfoeBXDdM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnFailure$8$WifiNl80211Manager$SendMgmtFrameEvent(i);
                }
            });
        }

        public /* synthetic */ void lambda$OnFailure$8$WifiNl80211Manager$SendMgmtFrameEvent(final int i) {
            runIfFirstCall(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$BsHKV1MLyLWRn4FZ-puX7XRDaLE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnFailure$7$WifiNl80211Manager$SendMgmtFrameEvent(i);
                }
            });
        }

        public /* synthetic */ void lambda$OnFailure$7$WifiNl80211Manager$SendMgmtFrameEvent(final int i) {
            WifiNl80211Manager.this.mAlarmManager.cancel(this.mTimeoutCallback);
            Binder.clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$SendMgmtFrameEvent$I6ehTYIN8TTuVasJUTqGu7YSYLA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$OnFailure$6$WifiNl80211Manager$SendMgmtFrameEvent(i);
                }
            });
        }

        public /* synthetic */ void lambda$OnFailure$6$WifiNl80211Manager$SendMgmtFrameEvent(int i) {
            this.mCallback.onFailure(i);
        }
    }

    /* JADX INFO: renamed from: binderDied, reason: merged with bridge method [inline-methods] */
    public void lambda$retrieveWificondAndRegisterForDeath$1$WifiNl80211Manager() {
        this.mEventHandler.post(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$ShMFa3boc_GR969SdMRhe9INA5A
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$binderDied$0$WifiNl80211Manager();
            }
        });
    }

    public /* synthetic */ void lambda$binderDied$0$WifiNl80211Manager() {
        Log.e(TAG, "Wificond died!");
        clearState();
        this.mWificond = null;
        Runnable runnable = this.mDeathEventHandler;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void enableVerboseLogging(boolean z) {
        this.mVerboseLoggingEnabled = z;
    }

    public void setOnServiceDeadCallback(Runnable runnable) {
        if (this.mDeathEventHandler != null) {
            Log.e(TAG, "Death handler already present");
        }
        this.mDeathEventHandler = runnable;
    }

    private boolean retrieveWificondAndRegisterForDeath() {
        if (this.mWificond != null) {
            if (this.mVerboseLoggingEnabled) {
                Log.d(TAG, "Wificond handle already retrieved");
            }
            return true;
        }
        IWificond iWificondAsInterface = IWificond.Stub.asInterface(ServiceManager.getService(Context.WIFI_NL80211_SERVICE));
        this.mWificond = iWificondAsInterface;
        if (iWificondAsInterface == null) {
            Log.e(TAG, "Failed to get reference to wificond");
            return false;
        }
        try {
            iWificondAsInterface.asBinder().linkToDeath(new IBinder.DeathRecipient() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$gj_UWkRFMZyZJomZvcvMZmTFj-E
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    this.f$0.lambda$retrieveWificondAndRegisterForDeath$1$WifiNl80211Manager();
                }
            }, 0);
            return true;
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to register death notification for wificond");
            return false;
        }
    }

    public boolean setupInterfaceForClientMode(String str, Executor executor, ScanEventCallback scanEventCallback, ScanEventCallback scanEventCallback2) {
        Log.d(TAG, "Setting up interface for client mode");
        if (!retrieveWificondAndRegisterForDeath()) {
            return false;
        }
        if (scanEventCallback == null || scanEventCallback2 == null || executor == null) {
            Log.e(TAG, "setupInterfaceForClientMode invoked with null callbacks");
            return false;
        }
        try {
            IClientInterface iClientInterfaceCreateClientInterface = this.mWificond.createClientInterface(str);
            if (iClientInterfaceCreateClientInterface == null) {
                Log.e(TAG, "Could not get IClientInterface instance from wificond");
                return false;
            }
            Binder.allowBlocking(iClientInterfaceCreateClientInterface.asBinder());
            this.mClientInterfaces.put(str, iClientInterfaceCreateClientInterface);
            try {
                IWifiScannerImpl wifiScannerImpl = iClientInterfaceCreateClientInterface.getWifiScannerImpl();
                if (wifiScannerImpl == null) {
                    Log.e(TAG, "Failed to get WificondScannerImpl");
                    return false;
                }
                this.mWificondScanners.put(str, wifiScannerImpl);
                Binder.allowBlocking(wifiScannerImpl.asBinder());
                ScanEventHandler scanEventHandler = new ScanEventHandler(executor, scanEventCallback);
                this.mScanEventHandlers.put(str, scanEventHandler);
                wifiScannerImpl.subscribeScanEvents(scanEventHandler);
                PnoScanEventHandler pnoScanEventHandler = new PnoScanEventHandler(executor, scanEventCallback2);
                this.mPnoScanEventHandlers.put(str, pnoScanEventHandler);
                wifiScannerImpl.subscribePnoScanEvents(pnoScanEventHandler);
                return true;
            } catch (RemoteException unused) {
                Log.e(TAG, "Failed to refresh wificond scanner due to remote exception");
                return true;
            }
        } catch (RemoteException unused2) {
            Log.e(TAG, "Failed to get IClientInterface due to remote exception");
            return false;
        }
    }

    public boolean tearDownClientInterface(String str) {
        if (getClientInterface(str) == null) {
            Log.e(TAG, "No valid wificond client interface handler");
            return false;
        }
        try {
            IWifiScannerImpl iWifiScannerImpl = this.mWificondScanners.get(str);
            if (iWifiScannerImpl != null) {
                iWifiScannerImpl.unsubscribeScanEvents();
                iWifiScannerImpl.unsubscribePnoScanEvents();
            }
            IWificond iWificond = this.mWificond;
            if (iWificond == null) {
                Log.e(TAG, "Reference to wifiCond is null");
                return false;
            }
            try {
                if (!iWificond.tearDownClientInterface(str)) {
                    Log.e(TAG, "Failed to teardown client interface");
                    return false;
                }
                this.mClientInterfaces.remove(str);
                this.mWificondScanners.remove(str);
                this.mScanEventHandlers.remove(str);
                this.mPnoScanEventHandlers.remove(str);
                return true;
            } catch (RemoteException unused) {
                Log.e(TAG, "Failed to teardown client interface due to remote exception");
                return false;
            }
        } catch (RemoteException unused2) {
            Log.e(TAG, "Failed to unsubscribe wificond scanner due to remote exception");
            return false;
        }
    }

    public boolean setupInterfaceForSoftApMode(String str) {
        Log.d(TAG, "Setting up interface for soft ap mode");
        if (!retrieveWificondAndRegisterForDeath()) {
            return false;
        }
        try {
            IApInterface iApInterfaceCreateApInterface = this.mWificond.createApInterface(str);
            if (iApInterfaceCreateApInterface == null) {
                Log.e(TAG, "Could not get IApInterface instance from wificond");
                return false;
            }
            Binder.allowBlocking(iApInterfaceCreateApInterface.asBinder());
            this.mApInterfaces.put(str, iApInterfaceCreateApInterface);
            return true;
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to get IApInterface due to remote exception");
            return false;
        }
    }

    public boolean tearDownSoftApInterface(String str) {
        if (getApInterface(str) == null) {
            Log.e(TAG, "No valid wificond ap interface handler");
            return false;
        }
        IWificond iWificond = this.mWificond;
        if (iWificond == null) {
            Log.e(TAG, "Reference to wifiCond is null");
            return false;
        }
        try {
            if (!iWificond.tearDownApInterface(str)) {
                Log.e(TAG, "Failed to teardown AP interface");
                return false;
            }
            this.mApInterfaces.remove(str);
            this.mApInterfaceListeners.remove(str);
            return true;
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to teardown AP interface due to remote exception");
            return false;
        }
    }

    public boolean tearDownInterfaces() {
        Log.d(TAG, "tearing down interfaces in wificond");
        if (!retrieveWificondAndRegisterForDeath()) {
            return false;
        }
        try {
            for (Map.Entry<String, IWifiScannerImpl> entry : this.mWificondScanners.entrySet()) {
                entry.getValue().unsubscribeScanEvents();
                entry.getValue().unsubscribePnoScanEvents();
            }
            this.mWificond.tearDownInterfaces();
            clearState();
            return true;
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to tear down interfaces due to remote exception");
            return false;
        }
    }

    private IClientInterface getClientInterface(String str) {
        return this.mClientInterfaces.get(str);
    }

    public SignalPollResult signalPoll(String str) {
        IClientInterface clientInterface = getClientInterface(str);
        if (clientInterface == null) {
            Log.e(TAG, "No valid wificond client interface handler");
            return null;
        }
        try {
            int[] iArrSignalPoll = clientInterface.signalPoll();
            if (iArrSignalPoll == null || iArrSignalPoll.length != 4) {
                Log.e(TAG, "Invalid signal poll result from wificond");
                return null;
            }
            return new SignalPollResult(iArrSignalPoll[0], iArrSignalPoll[1], iArrSignalPoll[3], iArrSignalPoll[2]);
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to do signal polling due to remote exception");
            return null;
        }
    }

    public TxPacketCounters getTxPacketCounters(String str) {
        IClientInterface clientInterface = getClientInterface(str);
        if (clientInterface == null) {
            Log.e(TAG, "No valid wificond client interface handler");
            return null;
        }
        try {
            int[] packetCounters = clientInterface.getPacketCounters();
            if (packetCounters == null || packetCounters.length != 2) {
                Log.e(TAG, "Invalid signal poll result from wificond");
                return null;
            }
            return new TxPacketCounters(packetCounters[0], packetCounters[1]);
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to do signal polling due to remote exception");
            return null;
        }
    }

    private IWifiScannerImpl getScannerImpl(String str) {
        return this.mWificondScanners.get(str);
    }

    public List<NativeScanResult> getScanResults(String str, int i) {
        List<NativeScanResult> listAsList;
        IWifiScannerImpl scannerImpl = getScannerImpl(str);
        if (scannerImpl == null) {
            Log.e(TAG, "No valid wificond scanner interface handler");
            return new ArrayList();
        }
        List<NativeScanResult> arrayList = null;
        try {
            if (i == 0) {
                listAsList = Arrays.asList(scannerImpl.getScanResults());
            } else {
                listAsList = Arrays.asList(scannerImpl.getPnoScanResults());
            }
            arrayList = listAsList;
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to create ScanDetail ArrayList");
        }
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        if (this.mVerboseLoggingEnabled) {
            Log.d(TAG, "get " + arrayList.size() + " scan results from wificond");
        }
        return arrayList;
    }

    private static int getScanType(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        throw new IllegalArgumentException("Invalid scan type " + i);
    }

    public boolean startScan(String str, int i, Set<Integer> set, List<byte[]> list) {
        IWifiScannerImpl scannerImpl = getScannerImpl(str);
        if (scannerImpl == null) {
            Log.e(TAG, "No valid wificond scanner interface handler");
            return false;
        }
        SingleScanSettings singleScanSettings = new SingleScanSettings();
        try {
            singleScanSettings.scanType = getScanType(i);
            singleScanSettings.channelSettings = new ArrayList<>();
            singleScanSettings.hiddenNetworks = new ArrayList<>();
            if (set != null) {
                for (Integer num : set) {
                    ChannelSettings channelSettings = new ChannelSettings();
                    channelSettings.frequency = num.intValue();
                    singleScanSettings.channelSettings.add(channelSettings);
                }
            }
            if (list != null) {
                for (byte[] bArr : list) {
                    HiddenNetwork hiddenNetwork = new HiddenNetwork();
                    hiddenNetwork.ssid = bArr;
                    if (!singleScanSettings.hiddenNetworks.contains(hiddenNetwork)) {
                        singleScanSettings.hiddenNetworks.add(hiddenNetwork);
                    }
                }
            }
            try {
                return scannerImpl.scan(singleScanSettings);
            } catch (RemoteException unused) {
                Log.e(TAG, "Failed to request scan due to remote exception");
                return false;
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Invalid scan type ", e);
            return false;
        }
    }

    public boolean startPnoScan(String str, PnoSettings pnoSettings, Executor executor, final PnoScanRequestCallback pnoScanRequestCallback) {
        IWifiScannerImpl scannerImpl = getScannerImpl(str);
        if (scannerImpl == null) {
            Log.e(TAG, "No valid wificond scanner interface handler");
            return false;
        }
        if (pnoScanRequestCallback == null || executor == null) {
            Log.e(TAG, "startPnoScan called with a null callback");
            return false;
        }
        try {
            boolean zStartPnoScan = scannerImpl.startPnoScan(pnoSettings);
            if (zStartPnoScan) {
                Objects.requireNonNull(pnoScanRequestCallback);
                executor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$ZG2hKx497SbMnsFFcQwIH8UGwrM
                    @Override // java.lang.Runnable
                    public final void run() {
                        pnoScanRequestCallback.onPnoRequestSucceeded();
                    }
                });
            } else {
                Objects.requireNonNull(pnoScanRequestCallback);
                executor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$eTziUIMwBgXfmQ_0cHdT_qNhkIU
                    @Override // java.lang.Runnable
                    public final void run() {
                        pnoScanRequestCallback.onPnoRequestFailed();
                    }
                });
            }
            return zStartPnoScan;
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to start pno scan due to remote exception");
            return false;
        }
    }

    public boolean stopPnoScan(String str) {
        IWifiScannerImpl scannerImpl = getScannerImpl(str);
        if (scannerImpl == null) {
            Log.e(TAG, "No valid wificond scanner interface handler");
            return false;
        }
        try {
            return scannerImpl.stopPnoScan();
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to stop pno scan due to remote exception");
            return false;
        }
    }

    public void abortScan(String str) {
        IWifiScannerImpl scannerImpl = getScannerImpl(str);
        if (scannerImpl == null) {
            Log.e(TAG, "No valid wificond scanner interface handler");
            return;
        }
        try {
            scannerImpl.abortScan();
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to request abortScan due to remote exception");
        }
    }

    public int[] getChannelsMhzForBand(int i) {
        int[] available2gChannels;
        IWificond iWificond = this.mWificond;
        if (iWificond == null) {
            Log.e(TAG, "No valid wificond scanner interface handler");
            return new int[0];
        }
        int[] iArr = null;
        try {
            if (i == 1) {
                available2gChannels = iWificond.getAvailable2gChannels();
            } else if (i == 2) {
                available2gChannels = iWificond.getAvailable5gNonDFSChannels();
            } else if (i == 4) {
                available2gChannels = iWificond.getAvailableDFSChannels();
            } else if (i == 8) {
                available2gChannels = iWificond.getAvailable6gChannels();
            } else {
                throw new IllegalArgumentException("unsupported band " + i);
            }
            iArr = available2gChannels;
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to request getChannelsForBand due to remote exception");
        }
        return iArr == null ? new int[0] : iArr;
    }

    private IApInterface getApInterface(String str) {
        return this.mApInterfaces.get(str);
    }

    public DeviceWiphyCapabilities getDeviceWiphyCapabilities(String str) {
        IWificond iWificond = this.mWificond;
        if (iWificond == null) {
            Log.e(TAG, "Can not query for device wiphy capabilities at this time");
            return null;
        }
        try {
            return iWificond.getDeviceWiphyCapabilities(str);
        } catch (RemoteException unused) {
            return null;
        }
    }

    public boolean registerApCallback(String str, Executor executor, SoftApCallback softApCallback) {
        IApInterface apInterface = getApInterface(str);
        if (apInterface == null) {
            Log.e(TAG, "No valid ap interface handler");
            return false;
        }
        if (softApCallback == null || executor == null) {
            Log.e(TAG, "registerApCallback called with a null callback");
            return false;
        }
        try {
            ApInterfaceEventCallback apInterfaceEventCallback = new ApInterfaceEventCallback(executor, softApCallback);
            this.mApInterfaceListeners.put(str, apInterfaceEventCallback);
            if (apInterface.registerCallback(apInterfaceEventCallback)) {
                return true;
            }
            Log.e(TAG, "Failed to register ap callback.");
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Exception in registering AP callback: " + e);
            return false;
        }
    }

    public void sendMgmtFrame(String str, byte[] bArr, int i, Executor executor, final SendMgmtFrameCallback sendMgmtFrameCallback) {
        if (sendMgmtFrameCallback == null || executor == null) {
            Log.e(TAG, "callback cannot be null!");
            return;
        }
        if (bArr == null) {
            Log.e(TAG, "frame cannot be null!");
            executor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$px5c9lsbhVJXt3KKfBEFiQ-k20E
                @Override // java.lang.Runnable
                public final void run() {
                    sendMgmtFrameCallback.onFailure(1);
                }
            });
            return;
        }
        IClientInterface clientInterface = getClientInterface(str);
        if (clientInterface == null) {
            Log.e(TAG, "No valid wificond client interface handler");
            executor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$jQofYBr8nr0eWIuyBBzw2O7pcw8
                @Override // java.lang.Runnable
                public final void run() {
                    sendMgmtFrameCallback.onFailure(1);
                }
            });
            return;
        }
        if (!this.mSendMgmtFrameInProgress.compareAndSet(false, true)) {
            Log.e(TAG, "An existing management frame transmission is in progress!");
            executor.execute(new Runnable() { // from class: android.net.wifi.nl80211.-$$Lambda$WifiNl80211Manager$_84sQMp93x3MR5HbAeuP427nm0g
                @Override // java.lang.Runnable
                public final void run() {
                    sendMgmtFrameCallback.onFailure(5);
                }
            });
            return;
        }
        SendMgmtFrameEvent sendMgmtFrameEvent = new SendMgmtFrameEvent(executor, sendMgmtFrameCallback);
        try {
            clientInterface.SendMgmtFrame(bArr, sendMgmtFrameEvent, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while starting link probe: " + e);
            sendMgmtFrameEvent.OnFailure(1);
        }
    }

    private void clearState() {
        this.mClientInterfaces.clear();
        this.mWificondScanners.clear();
        this.mPnoScanEventHandlers.clear();
        this.mScanEventHandlers.clear();
        this.mApInterfaces.clear();
        this.mApInterfaceListeners.clear();
        this.mSendMgmtFrameInProgress.set(false);
    }

    public static class OemSecurityType {
        public final int groupCipher;
        public final List<Integer> keyManagement;
        public final List<Integer> pairwiseCipher;
        public final int protocol;

        public OemSecurityType(int i, List<Integer> list, List<Integer> list2, int i2) {
            this.protocol = i;
            this.keyManagement = list == null ? new ArrayList<>() : list;
            this.pairwiseCipher = list2 == null ? new ArrayList<>() : list2;
            this.groupCipher = i2;
        }
    }
}
