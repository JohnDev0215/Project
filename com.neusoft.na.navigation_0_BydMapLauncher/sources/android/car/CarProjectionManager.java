package android.car;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothDevice;
import android.car.ICarProjection;
import android.car.ICarProjectionKeyEventHandler;
import android.car.ICarProjectionStatusListener;
import android.car.projection.ProjectionStatus;
import android.content.Intent;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiConfiguration;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.Preconditions;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class CarProjectionManager extends CarManagerBase {
    public static final int KEY_EVENT_CALL_KEY_DOWN = 4;
    public static final int KEY_EVENT_CALL_LONG_PRESS_KEY_DOWN = 6;
    public static final int KEY_EVENT_CALL_LONG_PRESS_KEY_UP = 7;
    public static final int KEY_EVENT_CALL_SHORT_PRESS_KEY_UP = 5;
    public static final int KEY_EVENT_VOICE_SEARCH_KEY_DOWN = 0;
    public static final int KEY_EVENT_VOICE_SEARCH_LONG_PRESS_KEY_DOWN = 2;
    public static final int KEY_EVENT_VOICE_SEARCH_LONG_PRESS_KEY_UP = 3;
    public static final int KEY_EVENT_VOICE_SEARCH_SHORT_PRESS_KEY_UP = 1;
    public static final int NUM_KEY_EVENTS = 8;
    public static final int PROJECTION_AP_FAILED = 2;
    public static final int PROJECTION_AP_STARTED = 0;
    public static final int PROJECTION_AP_STOPPED = 1;

    @Deprecated
    public static final int PROJECTION_LONG_PRESS_VOICE_SEARCH = 2;

    @Deprecated
    public static final int PROJECTION_VOICE_SEARCH = 1;
    private static final String TAG = "CarProjectionManager";
    private static final IBinder mAccessPointProxyToken = new Binder();
    private final ICarProjectionKeyEventHandlerImpl mBinderHandler;
    private CarProjectionStatusListenerImpl mCarProjectionStatusListener;
    private BitSet mHandledEvents;
    private final Executor mHandlerExecutor;
    private final Map<ProjectionKeyEventHandler, KeyEventHandlerRecord> mKeyEventHandlers;
    private final ProjectionKeyEventHandler mLegacyListenerTranslator;
    private CarProjectionListener mListener;
    private final Object mLock;
    private ProjectionAccessPointCallbackProxy mProjectionAccessPointCallbackProxy;
    private final Set<ProjectionStatusListener> mProjectionStatusListeners;
    private final ICarProjection mService;
    private final Binder mToken;
    private int mVoiceSearchFilter;

    public interface CarProjectionListener {
        void onVoiceAssistantRequest(boolean z);
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface KeyEventNum {
    }

    public interface ProjectionKeyEventHandler {
        void onKeyEvent(int i);
    }

    public interface ProjectionStatusListener {
        void onProjectionStatusChanged(int i, String str, List<ProjectionStatus> list);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public CarProjectionManager(Car car, IBinder iBinder) {
        super(car);
        this.mToken = new Binder();
        this.mLock = new Object();
        this.mLegacyListenerTranslator = new ProjectionKeyEventHandler() { // from class: android.car.CarProjectionManager$$ExternalSyntheticLambda0
            @Override // android.car.CarProjectionManager.ProjectionKeyEventHandler
            public final void onKeyEvent(int i) {
                this.f$0.translateKeyEventToLegacyListener(i);
            }
        };
        this.mBinderHandler = new ICarProjectionKeyEventHandlerImpl();
        this.mKeyEventHandlers = new HashMap();
        this.mHandledEvents = new BitSet();
        this.mProjectionStatusListeners = new LinkedHashSet();
        this.mService = ICarProjection.Stub.asInterface(iBinder);
        final Handler eventHandler = getEventHandler();
        Objects.requireNonNull(eventHandler);
        this.mHandlerExecutor = new Executor() { // from class: android.car.CarProjectionManager$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                eventHandler.post(runnable);
            }
        };
    }

    public void regsiterProjectionListener(CarProjectionListener carProjectionListener, int i) {
        registerProjectionListener(carProjectionListener, i);
    }

    public void registerProjectionListener(CarProjectionListener carProjectionListener, int i) {
        Objects.requireNonNull(carProjectionListener, "listener cannot be null");
        synchronized (this.mLock) {
            if (this.mListener == null || this.mVoiceSearchFilter != i) {
                addKeyEventHandler(translateVoiceSearchFilter(i), this.mLegacyListenerTranslator);
            }
            this.mListener = carProjectionListener;
            this.mVoiceSearchFilter = i;
        }
    }

    public void unregsiterProjectionListener() {
        unregisterProjectionListener();
    }

    public void unregisterProjectionListener() {
        synchronized (this.mLock) {
            removeKeyEventHandler(this.mLegacyListenerTranslator);
            this.mListener = null;
            this.mVoiceSearchFilter = 0;
        }
    }

    private static Set<Integer> translateVoiceSearchFilter(int i) {
        ArraySet arraySet = new ArraySet(Integer.bitCount(i));
        if ((i & 1) != 0) {
            arraySet.add(1);
        }
        if ((i & 2) != 0) {
            arraySet.add(2);
        }
        return arraySet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void translateKeyEventToLegacyListener(int i) {
        synchronized (this.mLock) {
            CarProjectionListener carProjectionListener = this.mListener;
            if (carProjectionListener == null) {
                return;
            }
            boolean z = true;
            if (i == 1) {
                z = false;
            } else if (i != 2) {
                Log.e(TAG, "Unexpected key event " + i);
                return;
            }
            Log.d(TAG, "Voice assistant request, long-press = " + z);
            carProjectionListener.onVoiceAssistantRequest(z);
        }
    }

    public void addKeyEventHandler(Set<Integer> set, ProjectionKeyEventHandler projectionKeyEventHandler) {
        addKeyEventHandler(set, null, projectionKeyEventHandler);
    }

    public void addKeyEventHandler(Set<Integer> set, Executor executor, ProjectionKeyEventHandler projectionKeyEventHandler) {
        BitSet bitSet = new BitSet();
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            Preconditions.checkArgument(iIntValue >= 0 && iIntValue < 8, "Invalid key event");
            bitSet.set(iIntValue);
        }
        if (bitSet.isEmpty()) {
            removeKeyEventHandler(projectionKeyEventHandler);
            return;
        }
        if (executor == null) {
            executor = this.mHandlerExecutor;
        }
        synchronized (this.mLock) {
            KeyEventHandlerRecord keyEventHandlerRecord = this.mKeyEventHandlers.get(projectionKeyEventHandler);
            if (keyEventHandlerRecord == null) {
                this.mKeyEventHandlers.put(projectionKeyEventHandler, new KeyEventHandlerRecord(executor, bitSet));
            } else {
                keyEventHandlerRecord.mExecutor = executor;
                keyEventHandlerRecord.mSubscribedEvents = bitSet;
            }
            updateHandledEventsLocked();
        }
    }

    public void removeKeyEventHandler(ProjectionKeyEventHandler projectionKeyEventHandler) {
        synchronized (this.mLock) {
            if (this.mKeyEventHandlers.remove(projectionKeyEventHandler) != null) {
                updateHandledEventsLocked();
            }
        }
    }

    private void updateHandledEventsLocked() {
        BitSet bitSet = new BitSet();
        Iterator<KeyEventHandlerRecord> it = this.mKeyEventHandlers.values().iterator();
        while (it.hasNext()) {
            bitSet.or(it.next().mSubscribedEvents);
        }
        if (bitSet.equals(this.mHandledEvents)) {
            return;
        }
        try {
            if (!bitSet.isEmpty()) {
                Log.d(TAG, "Registering handler with system for " + bitSet);
                this.mService.registerKeyEventHandler(this.mBinderHandler, bitSet.toByteArray());
            } else {
                Log.d(TAG, "Unregistering handler with system");
                this.mService.unregisterKeyEventHandler(this.mBinderHandler);
            }
            this.mHandledEvents = bitSet;
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void registerProjectionRunner(Intent intent) {
        Objects.requireNonNull("serviceIntent cannot be null");
        synchronized (this.mLock) {
            try {
                this.mService.registerProjectionRunner(intent);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public void unregisterProjectionRunner(Intent intent) {
        Objects.requireNonNull("serviceIntent cannot be null");
        synchronized (this.mLock) {
            try {
                this.mService.unregisterProjectionRunner(intent);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public void startProjectionAccessPoint(ProjectionAccessPointCallback projectionAccessPointCallback) {
        Objects.requireNonNull(projectionAccessPointCallback, "callback cannot be null");
        synchronized (this.mLock) {
            ProjectionAccessPointCallbackProxy projectionAccessPointCallbackProxy = new ProjectionAccessPointCallbackProxy(this, getEventHandler().getLooper(), projectionAccessPointCallback);
            try {
                this.mService.startProjectionAccessPoint(projectionAccessPointCallbackProxy.getMessenger(), mAccessPointProxyToken);
                this.mProjectionAccessPointCallbackProxy = projectionAccessPointCallbackProxy;
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public List<Integer> getAvailableWifiChannels(int i) {
        try {
            int[] availableWifiChannels = this.mService.getAvailableWifiChannels(i);
            ArrayList arrayList = new ArrayList(availableWifiChannels.length);
            for (int i2 : availableWifiChannels) {
                arrayList.add(Integer.valueOf(i2));
            }
            return arrayList;
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, Collections.emptyList());
        }
    }

    public void stopProjectionAccessPoint() {
        ProjectionAccessPointCallbackProxy projectionAccessPointCallbackProxy;
        synchronized (this.mLock) {
            projectionAccessPointCallbackProxy = this.mProjectionAccessPointCallbackProxy;
            this.mProjectionAccessPointCallbackProxy = null;
        }
        if (projectionAccessPointCallbackProxy == null) {
            return;
        }
        try {
            this.mService.stopProjectionAccessPoint(mAccessPointProxyToken);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public boolean requestBluetoothProfileInhibit(BluetoothDevice bluetoothDevice, int i) {
        Objects.requireNonNull(bluetoothDevice, "device cannot be null");
        try {
            return this.mService.requestBluetoothProfileInhibit(bluetoothDevice, i, this.mToken);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean releaseBluetoothProfileInhibit(BluetoothDevice bluetoothDevice, int i) {
        Objects.requireNonNull(bluetoothDevice, "device cannot be null");
        try {
            return this.mService.releaseBluetoothProfileInhibit(bluetoothDevice, i, this.mToken);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public void updateProjectionStatus(ProjectionStatus projectionStatus) {
        Objects.requireNonNull(projectionStatus, "status cannot be null");
        try {
            this.mService.updateProjectionStatus(projectionStatus, this.mToken);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void registerProjectionStatusListener(final ProjectionStatusListener projectionStatusListener) {
        Objects.requireNonNull(projectionStatusListener, "listener cannot be null");
        synchronized (this.mLock) {
            this.mProjectionStatusListeners.add(projectionStatusListener);
            if (this.mCarProjectionStatusListener == null) {
                CarProjectionStatusListenerImpl carProjectionStatusListenerImpl = new CarProjectionStatusListenerImpl();
                this.mCarProjectionStatusListener = carProjectionStatusListenerImpl;
                try {
                    this.mService.registerProjectionStatusListener(carProjectionStatusListenerImpl);
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
            } else {
                getEventHandler().post(new Runnable() { // from class: android.car.CarProjectionManager$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m1x93e2ae04(projectionStatusListener);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$registerProjectionStatusListener$0$android-car-CarProjectionManager, reason: not valid java name */
    /* synthetic */ void m1x93e2ae04(ProjectionStatusListener projectionStatusListener) {
        projectionStatusListener.onProjectionStatusChanged(this.mCarProjectionStatusListener.mCurrentState, this.mCarProjectionStatusListener.mCurrentPackageName, this.mCarProjectionStatusListener.mDetails);
    }

    public void unregisterProjectionStatusListener(ProjectionStatusListener projectionStatusListener) {
        Objects.requireNonNull(projectionStatusListener, "listener cannot be null");
        synchronized (this.mLock) {
            if (this.mProjectionStatusListeners.remove(projectionStatusListener) && this.mProjectionStatusListeners.isEmpty()) {
                unregisterProjectionStatusListenerFromCarServiceLocked();
            }
        }
    }

    private void unregisterProjectionStatusListenerFromCarServiceLocked() {
        try {
            this.mService.unregisterProjectionStatusListener(this.mCarProjectionStatusListener);
            this.mCarProjectionStatusListener = null;
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProjectionStatusChanged(int i, String str, List<ProjectionStatus> list) {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mProjectionStatusListeners);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ProjectionStatusListener) it.next()).onProjectionStatusChanged(i, str, list);
        }
    }

    public Bundle getProjectionOptions() {
        try {
            return this.mService.getProjectionOptions();
        } catch (RemoteException e) {
            return (Bundle) handleRemoteExceptionFromCarService(e, Bundle.EMPTY);
        }
    }

    public static abstract class ProjectionAccessPointCallback {
        public static final int ERROR_GENERIC = 2;
        public static final int ERROR_INCOMPATIBLE_MODE = 3;
        public static final int ERROR_NO_CHANNEL = 1;
        public static final int ERROR_TETHERING_DISALLOWED = 4;

        public void onFailed(int i) {
        }

        @Deprecated
        public void onStarted(WifiConfiguration wifiConfiguration) {
        }

        public void onStopped() {
        }

        public void onStarted(SoftApConfiguration softApConfiguration) {
            onStarted(softApConfiguration.toWifiConfiguration());
        }
    }

    private static class ProjectionAccessPointCallbackProxy {
        private static final String LOG_PREFIX = ProjectionAccessPointCallbackProxy.class.getSimpleName() + ": ";
        private final WeakReference<CarProjectionManager> mCarProjectionManagerRef;
        private final Handler mHandler;
        private final Messenger mMessenger;

        ProjectionAccessPointCallbackProxy(CarProjectionManager carProjectionManager, Looper looper, final ProjectionAccessPointCallback projectionAccessPointCallback) {
            this.mCarProjectionManagerRef = new WeakReference<>(carProjectionManager);
            Handler handler = new Handler(looper) { // from class: android.car.CarProjectionManager.ProjectionAccessPointCallbackProxy.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    Log.d(CarProjectionManager.TAG, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "handle message what: " + message.what + " msg: " + message);
                    if (((CarProjectionManager) ProjectionAccessPointCallbackProxy.this.mCarProjectionManagerRef.get()) == null) {
                        Log.w(CarProjectionManager.TAG, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "handle message post GC");
                        return;
                    }
                    int i = message.what;
                    if (i == 0) {
                        SoftApConfiguration softApConfiguration = (SoftApConfiguration) message.obj;
                        if (softApConfiguration == null) {
                            Log.e(CarProjectionManager.TAG, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "config cannot be null.");
                            projectionAccessPointCallback.onFailed(2);
                            return;
                        } else {
                            projectionAccessPointCallback.onStarted(softApConfiguration);
                            return;
                        }
                    }
                    if (i == 1) {
                        Log.i(CarProjectionManager.TAG, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "hotspot stopped");
                        projectionAccessPointCallback.onStopped();
                    } else {
                        if (i != 2) {
                            Log.e(CarProjectionManager.TAG, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "unhandled message.  type: " + message.what);
                            return;
                        }
                        int i2 = message.arg1;
                        Log.w(CarProjectionManager.TAG, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "failed to start.  reason: " + i2);
                        projectionAccessPointCallback.onFailed(i2);
                    }
                }
            };
            this.mHandler = handler;
            this.mMessenger = new Messenger(handler);
        }

        Messenger getMessenger() {
            return this.mMessenger;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ICarProjectionKeyEventHandlerImpl extends ICarProjectionKeyEventHandler.Stub {
        private final WeakReference<CarProjectionManager> mManager;

        private ICarProjectionKeyEventHandlerImpl(CarProjectionManager carProjectionManager) {
            this.mManager = new WeakReference<>(carProjectionManager);
        }

        @Override // android.car.ICarProjectionKeyEventHandler
        public void onKeyEvent(final int i) {
            Log.d(CarProjectionManager.TAG, "Received projection key event " + i);
            CarProjectionManager carProjectionManager = this.mManager.get();
            if (carProjectionManager == null) {
                return;
            }
            ArrayList<Pair> arrayList = new ArrayList();
            synchronized (carProjectionManager.mLock) {
                for (Map.Entry entry : carProjectionManager.mKeyEventHandlers.entrySet()) {
                    if (((KeyEventHandlerRecord) entry.getValue()).mSubscribedEvents.get(i)) {
                        arrayList.add(Pair.create((ProjectionKeyEventHandler) entry.getKey(), ((KeyEventHandlerRecord) entry.getValue()).mExecutor));
                    }
                }
            }
            for (Pair pair : arrayList) {
                final ProjectionKeyEventHandler projectionKeyEventHandler = (ProjectionKeyEventHandler) pair.first;
                ((Executor) pair.second).execute(new Runnable() { // from class: android.car.CarProjectionManager$ICarProjectionKeyEventHandlerImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        projectionKeyEventHandler.onKeyEvent(i);
                    }
                });
            }
        }
    }

    private static class KeyEventHandlerRecord {
        Executor mExecutor;
        BitSet mSubscribedEvents;

        KeyEventHandlerRecord(Executor executor, BitSet bitSet) {
            this.mExecutor = executor;
            this.mSubscribedEvents = bitSet;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CarProjectionStatusListenerImpl extends ICarProjectionStatusListener.Stub {
        private String mCurrentPackageName;
        private int mCurrentState;
        private List<ProjectionStatus> mDetails;
        private final WeakReference<CarProjectionManager> mManagerRef;

        private CarProjectionStatusListenerImpl(CarProjectionManager carProjectionManager) {
            this.mDetails = new ArrayList(0);
            this.mManagerRef = new WeakReference<>(carProjectionManager);
        }

        @Override // android.car.ICarProjectionStatusListener
        public void onProjectionStatusChanged(final int i, final String str, final List<ProjectionStatus> list) {
            final CarProjectionManager carProjectionManager = this.mManagerRef.get();
            if (carProjectionManager != null) {
                carProjectionManager.getEventHandler().post(new Runnable() { // from class: android.car.CarProjectionManager$CarProjectionStatusListenerImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m2x9ebb1bc7(i, str, list, carProjectionManager);
                    }
                });
            }
        }

        /* JADX INFO: renamed from: lambda$onProjectionStatusChanged$0$android-car-CarProjectionManager$CarProjectionStatusListenerImpl, reason: not valid java name */
        /* synthetic */ void m2x9ebb1bc7(int i, String str, List list, CarProjectionManager carProjectionManager) {
            this.mCurrentState = i;
            this.mCurrentPackageName = str;
            List<ProjectionStatus> listUnmodifiableList = Collections.unmodifiableList(list);
            this.mDetails = listUnmodifiableList;
            carProjectionManager.handleProjectionStatusChanged(i, str, listUnmodifiableList);
        }
    }
}
