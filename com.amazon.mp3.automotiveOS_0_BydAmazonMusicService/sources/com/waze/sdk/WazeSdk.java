package com.waze.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.waze.sdk.ISdkService;
import com.waze.sdk.WazeSdkUtils;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes7.dex */
class WazeSdk {
    static WeakReference<WazeSdk> sInstance;
    private static final SdkUiWidgetList sSdkUiWidgets = new SdkUiWidgetList();
    private final WazeSdkCallback mCallback;
    private final Context mContext;
    private boolean mIsConnected;
    private boolean mIsConnecting;
    private NavigationListener mNavigationListener;
    private Messenger mReceiveFromWazeMessenger;
    private WazeSdkSettings mSdkSettings;
    private String mSecretToken;
    private Messenger mSendToWazeMessenger;
    private boolean mServiceBound;
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.waze.sdk.WazeSdk.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (WazeSdk.this.mReceiveFromWazeMessenger == null) {
                WazeSdk.this.mReceiveFromWazeMessenger = new Messenger(WazeSdk.this.getWazeMessageHandler());
            }
            WazeSdk wazeSdk = WazeSdk.this;
            new InitConnectionTask(wazeSdk, wazeSdk.mSecretToken, WazeSdk.this.mSdkSettings, WazeSdk.this.mReceiveFromWazeMessenger).execute(ISdkService.Stub.asInterface(iBinder));
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            WazeSdk.this.disconnect(4);
        }
    };

    interface NavigationListener {
        void onInstructionDistanceUpdated(String str, int i);

        void onInstructionUpdated(WazeSdkConstants$WazeInstructions wazeSdkConstants$WazeInstructions);

        void onNavigationStatusChanged(boolean z);

        void onRoundaboutExitUpdated(int i);

        void onStreetNameChanged(String str);

        void onTrafficSideUpdated(boolean z);
    }

    interface SdkUiWidget extends WazeSdkCallback, NavigationListener {
    }

    private static class InitConnectionTask extends AsyncTask<ISdkService, Void, Messenger> {
        private final Messenger mReceiveFromWazeMessenger;
        private final WazeSdkSettings mSettings;
        private final String mToken;
        private final WazeSdk mWazeSdk;

        InitConnectionTask(WazeSdk wazeSdk, String str, WazeSdkSettings wazeSdkSettings, Messenger messenger) {
            this.mWazeSdk = wazeSdk;
            this.mToken = str;
            this.mSettings = wazeSdkSettings;
            this.mReceiveFromWazeMessenger = messenger;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Messenger doInBackground(ISdkService... iSdkServiceArr) {
            try {
                return iSdkServiceArr[0].init(this.mToken, this.mSettings.toBundle(), this.mReceiveFromWazeMessenger);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Messenger messenger) {
            if (messenger != null) {
                this.mWazeSdk.onSdkConnected(messenger);
            } else {
                this.mWazeSdk.disconnect(2);
            }
        }
    }

    WazeSdk(Context context, WazeSdkSettings wazeSdkSettings, WazeSdkCallback wazeSdkCallback) {
        sInstance = new WeakReference<>(this);
        this.mContext = context.getApplicationContext();
        this.mSdkSettings = wazeSdkSettings;
        this.mCallback = wazeSdkCallback;
        connect();
    }

    WazeMessageHandler getWazeMessageHandler() {
        return new WazeMessageHandler(this);
    }

    void onSdkConnected(Messenger messenger) {
        Log.d("WazeSdk", "SDK connected.");
        this.mSendToWazeMessenger = messenger;
        this.mIsConnected = true;
        this.mIsConnecting = false;
        updateRequestNavigationData();
        Iterator<SdkUiWidget> it = sSdkUiWidgets.iterator();
        while (it.hasNext()) {
            it.next().onConnected();
        }
        WazeSdkCallback wazeSdkCallback = this.mCallback;
        if (wazeSdkCallback != null) {
            wazeSdkCallback.onConnected();
        }
    }

    void onSdkDisconnected(int i) {
        Log.d("WazeSdk", "SDK disconnected, reason: " + i);
        if (this.mIsConnected) {
            this.mIsConnected = false;
            this.mIsConnecting = false;
            this.mSendToWazeMessenger = null;
            this.mSecretToken = null;
            Iterator<SdkUiWidget> it = sSdkUiWidgets.iterator();
            while (it.hasNext()) {
                it.next().onDisconnected(i);
            }
            WazeSdkCallback wazeSdkCallback = this.mCallback;
            if (wazeSdkCallback != null) {
                wazeSdkCallback.onDisconnected(i);
            }
        }
    }

    public boolean openWaze() {
        Messenger messenger = this.mSendToWazeMessenger;
        if (messenger == null) {
            return false;
        }
        try {
            messenger.send(WazeSdkMessage.openWaze(this.mSecretToken));
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isConnected() {
        return this.mIsConnected;
    }

    public final void connect() {
        if (this.mIsConnected || this.mIsConnecting) {
            return;
        }
        this.mIsConnecting = true;
        this.mSecretToken = WazeSdkUtils.createAESKey();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.waze", "com.waze.sdk.SdkService"));
        this.mContext.bindService(intent, this.mServiceConnection, 1);
        this.mServiceBound = true;
    }

    public void disconnect() {
        disconnect(5);
    }

    void disconnect(int i) {
        if (this.mServiceBound) {
            Messenger messenger = this.mSendToWazeMessenger;
            if (messenger != null) {
                try {
                    messenger.send(WazeSdkMessage.disconnect(this.mSecretToken));
                } catch (RemoteException unused) {
                }
            }
            this.mContext.unbindService(this.mServiceConnection);
            this.mServiceBound = false;
        }
        onSdkDisconnected(i);
    }

    void setNavigationListenerInternal(NavigationListener navigationListener) {
        this.mNavigationListener = navigationListener;
        updateRequestNavigationData();
    }

    static void registerUiWidget(SdkUiWidget sdkUiWidget) {
        sSdkUiWidgets.add(sdkUiWidget);
        WeakReference<WazeSdk> weakReference = sInstance;
        WazeSdk wazeSdk = weakReference == null ? null : weakReference.get();
        if (wazeSdk == null || !wazeSdk.isConnected()) {
            return;
        }
        wazeSdk.updateRequestNavigationData();
    }

    boolean isListeningNavigationData() {
        return this.mNavigationListener != null || sSdkUiWidgets.iterator().hasNext();
    }

    void sendStats(WazeSdkUtils.WazeStats wazeStats) {
        Messenger messenger = this.mSendToWazeMessenger;
        if (messenger != null) {
            try {
                messenger.send(WazeSdkMessage.sendStats(this.mSecretToken, wazeStats.mEventName, wazeStats.mParams));
            } catch (RemoteException unused) {
            }
        }
    }

    private void updateRequestNavigationData() {
        Messenger messenger = this.mSendToWazeMessenger;
        if (messenger != null) {
            try {
                messenger.send(WazeSdkMessage.requestNavigationData(this.mSecretToken, isListeningNavigationData()));
            } catch (RemoteException unused) {
            }
        }
    }

    void onInstructionUpdated(WazeSdkConstants$WazeInstructions wazeSdkConstants$WazeInstructions) {
        Iterator<SdkUiWidget> it = sSdkUiWidgets.iterator();
        while (it.hasNext()) {
            it.next().onInstructionUpdated(wazeSdkConstants$WazeInstructions);
        }
        NavigationListener navigationListener = this.mNavigationListener;
        if (navigationListener != null) {
            navigationListener.onInstructionUpdated(wazeSdkConstants$WazeInstructions);
        }
    }

    void onRoundaboutExitUpdated(int i) {
        Iterator<SdkUiWidget> it = sSdkUiWidgets.iterator();
        while (it.hasNext()) {
            it.next().onRoundaboutExitUpdated(i);
        }
        NavigationListener navigationListener = this.mNavigationListener;
        if (navigationListener != null) {
            navigationListener.onRoundaboutExitUpdated(i);
        }
    }

    void onInstructionDistanceUpdated(String str, int i) {
        Iterator<SdkUiWidget> it = sSdkUiWidgets.iterator();
        while (it.hasNext()) {
            it.next().onInstructionDistanceUpdated(str, i);
        }
        NavigationListener navigationListener = this.mNavigationListener;
        if (navigationListener != null) {
            navigationListener.onInstructionDistanceUpdated(str, i);
        }
    }

    void onStreetNameChanged(String str) {
        Iterator<SdkUiWidget> it = sSdkUiWidgets.iterator();
        while (it.hasNext()) {
            it.next().onStreetNameChanged(str);
        }
        NavigationListener navigationListener = this.mNavigationListener;
        if (navigationListener != null) {
            navigationListener.onStreetNameChanged(str);
        }
    }

    void onTrafficSideUpdated(boolean z) {
        Iterator<SdkUiWidget> it = sSdkUiWidgets.iterator();
        while (it.hasNext()) {
            it.next().onTrafficSideUpdated(z);
        }
        NavigationListener navigationListener = this.mNavigationListener;
        if (navigationListener != null) {
            navigationListener.onTrafficSideUpdated(z);
        }
    }

    void onNavigationStatusChanged(boolean z) {
        Iterator<SdkUiWidget> it = sSdkUiWidgets.iterator();
        while (it.hasNext()) {
            it.next().onNavigationStatusChanged(z);
        }
        NavigationListener navigationListener = this.mNavigationListener;
        if (navigationListener != null) {
            navigationListener.onNavigationStatusChanged(z);
        }
    }

    private static class SdkUiWidgetList implements Iterable<SdkUiWidget> {
        private final Set<WeakReference<SdkUiWidget>> mSdkUiWidgets;

        private SdkUiWidgetList() {
            this.mSdkUiWidgets = new HashSet();
        }

        void add(SdkUiWidget sdkUiWidget) {
            this.mSdkUiWidgets.add(new WeakReference<>(sdkUiWidget));
        }

        @Override // java.lang.Iterable
        public Iterator<SdkUiWidget> iterator() {
            return new Iterator<SdkUiWidget>() { // from class: com.waze.sdk.WazeSdk.SdkUiWidgetList.1
                private Iterator<WeakReference<SdkUiWidget>> mIterator;
                private SdkUiWidget mNextItem;

                {
                    this.mIterator = SdkUiWidgetList.this.mSdkUiWidgets.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    if (this.mNextItem != null) {
                        return true;
                    }
                    while (this.mIterator.hasNext()) {
                        SdkUiWidget sdkUiWidget = this.mIterator.next().get();
                        this.mNextItem = sdkUiWidget;
                        if (sdkUiWidget != null) {
                            return true;
                        }
                        this.mIterator.remove();
                    }
                    return false;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public SdkUiWidget next() {
                    if (this.mNextItem == null && !hasNext()) {
                        return null;
                    }
                    SdkUiWidget sdkUiWidget = this.mNextItem;
                    this.mNextItem = null;
                    return sdkUiWidget;
                }
            };
        }
    }
}
