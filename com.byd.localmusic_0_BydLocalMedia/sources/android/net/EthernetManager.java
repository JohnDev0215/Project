package android.net;

import android.annotation.SystemApi;
import android.content.Context;
import android.net.IEthernetServiceListener;
import android.net.ITetheredInterfaceCallback;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class EthernetManager {
    private static final int MSG_AVAILABILITY_CHANGED = 1000;
    private static final String TAG = "EthernetManager";
    private final Context mContext;
    private final IEthernetManager mService;
    private final Handler mHandler = new Handler(ConnectivityThread.getInstanceLooper()) { // from class: android.net.EthernetManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1000) {
                boolean z = message.arg1 == 1;
                Iterator it = EthernetManager.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((Listener) it.next()).onAvailabilityChanged((String) message.obj, z);
                }
            }
        }
    };
    private final ArrayList<Listener> mListeners = new ArrayList<>();
    private final IEthernetServiceListener.Stub mServiceListener = new IEthernetServiceListener.Stub() { // from class: android.net.EthernetManager.2
        @Override // android.net.IEthernetServiceListener
        public void onAvailabilityChanged(String str, boolean z) {
            EthernetManager.this.mHandler.obtainMessage(1000, z ? 1 : 0, 0, str).sendToTarget();
        }
    };

    public interface Listener {
        void onAvailabilityChanged(String str, boolean z);
    }

    public interface TetheredInterfaceCallback {
        void onAvailable(String str);

        void onUnavailable();
    }

    public EthernetManager(Context context, IEthernetManager iEthernetManager) {
        this.mContext = context;
        this.mService = iEthernetManager;
    }

    public IpConfiguration getConfiguration(String str) {
        try {
            return this.mService.getConfiguration(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setConfiguration(String str, IpConfiguration ipConfiguration) {
        try {
            this.mService.setConfiguration(str, ipConfiguration);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isAvailable() {
        return getAvailableInterfaces().length > 0;
    }

    public boolean isAvailable(String str) {
        try {
            return this.mService.isAvailable(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addListener(Listener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        this.mListeners.add(listener);
        if (this.mListeners.size() == 1) {
            try {
                this.mService.addListener(this.mServiceListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public String[] getAvailableInterfaces() {
        try {
            return this.mService.getAvailableInterfaces();
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public void removeListener(Listener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        this.mListeners.remove(listener);
        if (this.mListeners.isEmpty()) {
            try {
                this.mService.removeListener(this.mServiceListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void setIncludeTestInterfaces(boolean z) {
        try {
            this.mService.setIncludeTestInterfaces(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static class TetheredInterfaceRequest {
        private final ITetheredInterfaceCallback mCb;
        private final IEthernetManager mService;

        private TetheredInterfaceRequest(IEthernetManager iEthernetManager, ITetheredInterfaceCallback iTetheredInterfaceCallback) {
            this.mService = iEthernetManager;
            this.mCb = iTetheredInterfaceCallback;
        }

        public void release() {
            try {
                this.mService.releaseTetheredInterface(this.mCb);
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
    }

    public TetheredInterfaceRequest requestTetheredInterface(Executor executor, TetheredInterfaceCallback tetheredInterfaceCallback) {
        Objects.requireNonNull(tetheredInterfaceCallback, "Callback must be non-null");
        Objects.requireNonNull(executor, "Executor must be non-null");
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(executor, tetheredInterfaceCallback);
        try {
            this.mService.requestTetheredInterface(anonymousClass3);
            return new TetheredInterfaceRequest(this.mService, anonymousClass3);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.net.EthernetManager$3, reason: invalid class name */
    class AnonymousClass3 extends ITetheredInterfaceCallback.Stub {
        final /* synthetic */ TetheredInterfaceCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass3(Executor executor, TetheredInterfaceCallback tetheredInterfaceCallback) {
            this.val$executor = executor;
            this.val$callback = tetheredInterfaceCallback;
        }

        @Override // android.net.ITetheredInterfaceCallback
        public void onAvailable(final String str) {
            Executor executor = this.val$executor;
            final TetheredInterfaceCallback tetheredInterfaceCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.net.-$$Lambda$EthernetManager$3$wNl0ng6S-4pOA1JjJrcBQ_pxSQU
                @Override // java.lang.Runnable
                public final void run() {
                    tetheredInterfaceCallback.onAvailable(str);
                }
            });
        }

        @Override // android.net.ITetheredInterfaceCallback
        public void onUnavailable() {
            Executor executor = this.val$executor;
            final TetheredInterfaceCallback tetheredInterfaceCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.net.-$$Lambda$EthernetManager$3$7jKVTVFMKx3pBO9-KkSYZmxOBm0
                @Override // java.lang.Runnable
                public final void run() {
                    tetheredInterfaceCallback.onUnavailable();
                }
            });
        }
    }
}
