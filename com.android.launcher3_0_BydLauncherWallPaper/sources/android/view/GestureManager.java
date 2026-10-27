package android.view;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.IGestureCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GestureManager {
    private static final String TAG = "GestureManager";
    private Context mContext;
    private IGestureManager mGestureManagerService;
    private List<GestureChangeListener> mGestureChangeListener = new ArrayList();
    private List<MonitorStateListener> mMonitorStateListener = new ArrayList();
    private final IGestureCallback mGestureCallback = new IGestureCallback.Stub() { // from class: android.view.GestureManager.1
        @Override // android.view.IGestureCallback
        public void onGestureEvent(MotionEvent motionEvent) {
            GestureManager.this.callbackGestureChanged(motionEvent);
        }

        @Override // android.view.IGestureCallback
        public void onMonitorState(int i) {
            GestureManager.this.callbackMonitorStateChanged(i);
        }
    };

    public interface GestureChangeListener {
        void onGestureChanged(MotionEvent motionEvent);
    }

    public interface MonitorStateListener {
        void onMonitorState(int i);
    }

    public GestureManager(Context context, IGestureManager iGestureManager) {
        this.mContext = context;
        this.mGestureManagerService = iGestureManager;
        subscribeGestureCallback();
    }

    @SystemApi
    public void subscribeGestureCallback() {
        IGestureManager iGestureManager = this.mGestureManagerService;
        if (iGestureManager == null) {
            Log.e(TAG, "mGestureManagerService is null");
            return;
        }
        try {
            iGestureManager.subscribeGestureCallback(this.mGestureCallback);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void unsubscribeGestureCallback() {
        IGestureManager iGestureManager = this.mGestureManagerService;
        if (iGestureManager == null) {
            Log.e(TAG, "mGestureManagerService is null");
            return;
        }
        try {
            iGestureManager.unsubscribeGestureCallback(this.mGestureCallback);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void subscribeGestureChangeListener(GestureChangeListener gestureChangeListener) {
        synchronized (this.mGestureChangeListener) {
            if (!this.mGestureChangeListener.contains(gestureChangeListener) && gestureChangeListener != null) {
                this.mGestureChangeListener.add(gestureChangeListener);
                Log.i(TAG, "subscribeGestureChangeListener ok --> listener size: " + this.mGestureChangeListener.size());
            }
        }
    }

    public void unSubscribeGestureChangeListener(GestureChangeListener gestureChangeListener) {
        synchronized (this.mGestureChangeListener) {
            if (gestureChangeListener != null) {
                if (this.mGestureChangeListener.contains(gestureChangeListener)) {
                    this.mGestureChangeListener.remove(gestureChangeListener);
                    Log.i(TAG, "unSubscribeGestureChangeListener ok --> listener size: " + this.mGestureChangeListener.size());
                }
            }
        }
    }

    public void subscribeMonitorStateListener(MonitorStateListener monitorStateListener) {
        synchronized (this.mMonitorStateListener) {
            if (!this.mMonitorStateListener.contains(monitorStateListener) && monitorStateListener != null) {
                this.mMonitorStateListener.add(monitorStateListener);
                Log.i(TAG, "subscribeMonitorStateListener ok --> listener size: " + this.mMonitorStateListener.size());
            }
        }
    }

    public void unSubscribeMonitorStateListener(MonitorStateListener monitorStateListener) {
        synchronized (this.mMonitorStateListener) {
            if (monitorStateListener != null) {
                if (this.mMonitorStateListener.contains(monitorStateListener)) {
                    this.mMonitorStateListener.remove(monitorStateListener);
                    Log.i(TAG, "unSubscribeMonitorStateListener ok --> listener size: " + this.mMonitorStateListener.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SystemApi
    public void callbackGestureChanged(MotionEvent motionEvent) {
        Iterator<GestureChangeListener> it = this.mGestureChangeListener.iterator();
        while (it.hasNext()) {
            it.next().onGestureChanged(motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SystemApi
    public void callbackMonitorStateChanged(int i) {
        Iterator<MonitorStateListener> it = this.mMonitorStateListener.iterator();
        while (it.hasNext()) {
            it.next().onMonitorState(i);
        }
    }

    @SystemApi
    public void sendMotionEventToClients(MotionEvent motionEvent) {
        IGestureManager iGestureManager = this.mGestureManagerService;
        if (iGestureManager == null) {
            Log.e(TAG, "mGestureManagerService is null");
            return;
        }
        try {
            iGestureManager.sendMotionEventToClients(motionEvent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void sendMonitorStateToClients(int i) {
        IGestureManager iGestureManager = this.mGestureManagerService;
        if (iGestureManager == null) {
            Log.e(TAG, "mGestureManagerService is null");
            return;
        }
        try {
            iGestureManager.sendMonitorStateToClients(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setMonitorMode(boolean z) {
        IGestureManager iGestureManager = this.mGestureManagerService;
        if (iGestureManager == null) {
            Log.e(TAG, "mGestureManagerService is null");
            return;
        }
        try {
            iGestureManager.setMonitorMode(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
