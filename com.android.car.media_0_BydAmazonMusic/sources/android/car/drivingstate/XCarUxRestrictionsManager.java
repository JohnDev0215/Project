package android.car.drivingstate;

import android.car.XCar;
import android.car.XCarManagerBase;
import android.car.XCarRoute;
import android.car.drivingstate.XCarUxRestrictions;
import android.car.utils.Logs;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.alibaba.android.arouter.launcher.ARouter;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class XCarUxRestrictionsManager extends XCarManagerBase {
    private static final boolean DBG = true;
    private static final int MSG_HANDLE_UX_RESTRICTIONS_CHANGE = 0;
    private static final String TAG = "XCarUxRManager";
    public static final String UX_RESTRICTION_MODE_BASELINE = "baseline";
    private final EventCallbackHandler mEventCallbackHandler;
    private IXCarUxRestrictionsChangeListener mListenerToService;
    private final Object mLock;
    private OnUxRestrictionsChangedListener mUxRListener;
    private final IXCarUxRestrictionsManager mUxRService;

    public static final class EventCallbackHandler extends Handler {
        private final WeakReference<XCarUxRestrictionsManager> mUxRestrictionsManager;

        public EventCallbackHandler(XCarUxRestrictionsManager xCarUxRestrictionsManager, Looper looper) {
            super(looper);
            this.mUxRestrictionsManager = new WeakReference<>(xCarUxRestrictionsManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            XCarUxRestrictionsManager xCarUxRestrictionsManager = this.mUxRestrictionsManager.get();
            if (xCarUxRestrictionsManager != null) {
                xCarUxRestrictionsManager.dispatchUxRChangeToClient((XCarUxRestrictions) message.obj);
            }
        }
    }

    public interface OnUxRestrictionsChangedListener {
        void onUxRestrictionsChanged(XCarUxRestrictions xCarUxRestrictions);
    }

    public XCarUxRestrictionsManager(XCar xCar, IBinder iBinder) {
        super(xCar);
        this.mEventCallbackHandler = new EventCallbackHandler(this, getEventHandler().getLooper());
        this.mLock = new Object();
        this.mUxRService = (IXCarUxRestrictionsManager) ARouter.getInstance().build(XCarRoute.CAR_UXR_SERVICE).navigation();
        this.mListenerToService = new IXCarUxRestrictionsChangeListener() { // from class: spi.ed
            @Override // android.car.drivingstate.IXCarUxRestrictionsChangeListener
            public final void onUxRestrictionsChanged(XCarUxRestrictions xCarUxRestrictions) {
                this.a.handleUxRestrictionsChanged(xCarUxRestrictions);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchUxRChangeToClient(XCarUxRestrictions xCarUxRestrictions) {
        OnUxRestrictionsChangedListener onUxRestrictionsChangedListener;
        if (xCarUxRestrictions != null) {
            synchronized (this.mLock) {
                onUxRestrictionsChangedListener = this.mUxRListener;
            }
            if (onUxRestrictionsChangedListener != null) {
                onUxRestrictionsChangedListener.onUxRestrictionsChanged(xCarUxRestrictions);
            }
        }
    }

    private int getDisplayId() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUxRestrictionsChanged(XCarUxRestrictions xCarUxRestrictions) {
        EventCallbackHandler eventCallbackHandler = this.mEventCallbackHandler;
        eventCallbackHandler.sendMessage(eventCallbackHandler.obtainMessage(0, xCarUxRestrictions));
    }

    public XCarUxRestrictions getCurrentCarUxRestrictions() {
        return getCurrentCarUxRestrictions(getDisplayId());
    }

    public String getRestrictionMode() {
        try {
            return this.mUxRService.getRestrictionMode();
        } catch (Exception e) {
            return (String) this.handleRemoteExceptionFromCarService(e, null);
        }
    }

    @Override // android.car.XCarManagerBase
    public void onCarDisconnected() {
        synchronized (this.mLock) {
            this.mListenerToService = null;
            this.mUxRListener = null;
            try {
                this.mUxRService.onCarDisconnected();
            } catch (Exception e) {
                Logs.e(TAG, e.getMessage());
            }
        }
    }

    public void registerListener(OnUxRestrictionsChangedListener onUxRestrictionsChangedListener) {
        registerListener(onUxRestrictionsChangedListener, getDisplayId());
    }

    public void setListener(int i, OnUxRestrictionsChangedListener onUxRestrictionsChangedListener) {
        synchronized (this.mLock) {
            if (this.mUxRListener != null) {
                Logs.d(TAG, "Listener already registered listener");
                return;
            }
            this.mUxRListener = onUxRestrictionsChangedListener;
            try {
                this.mUxRService.registerUxRestrictionsChangeListener(this.mListenerToService, i);
            } catch (Exception e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public boolean setRestrictionMode(String str) {
        Objects.requireNonNull(str, "mode must not be null");
        try {
            return this.mUxRService.setRestrictionMode(str);
        } catch (Exception e) {
            return ((Boolean) this.handleRemoteExceptionFromCarService(e, Boolean.FALSE)).booleanValue();
        }
    }

    public void unregisterListener() {
        synchronized (this.mLock) {
            if (this.mUxRListener == null) {
                Logs.d(TAG, "Listener was not previously registered");
                return;
            }
            this.mUxRListener = null;
            try {
                this.mUxRService.unregisterUxRestrictionsChangeListener(this.mListenerToService);
            } catch (Exception e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public XCarUxRestrictions getCurrentCarUxRestrictions(int i) {
        try {
            return this.mUxRService.getCurrentUxRestrictions(i);
        } catch (Exception e) {
            return (XCarUxRestrictions) this.handleRemoteExceptionFromCarService(e, null);
        }
    }

    public void registerListener(OnUxRestrictionsChangedListener onUxRestrictionsChangedListener, int i) {
        setListener(i, onUxRestrictionsChangedListener);
    }
}
