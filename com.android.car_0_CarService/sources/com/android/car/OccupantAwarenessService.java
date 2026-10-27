package com.android.car;

import android.car.occupantawareness.IOccupantAwarenessEventCallback;
import android.car.occupantawareness.IOccupantAwarenessManager;
import android.car.occupantawareness.OccupantAwarenessDetection;
import android.car.occupantawareness.SystemStatusEvent;
import android.content.Context;
import android.hardware.automotive.occupant_awareness.IOccupantAwareness;
import android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback;
import android.hardware.automotive.occupant_awareness.OccupantDetection;
import android.hardware.automotive.occupant_awareness.OccupantDetections;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.android.car.Manifest;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class OccupantAwarenessService extends IOccupantAwarenessManager.Stub implements CarServiceBase {
    private static final boolean DBG = false;
    static final String OAS_SERVICE_ID = "android.hardware.automotive.occupant_awareness.IOccupantAwareness/default";
    private static final String TAG = "CAR.OAS";
    private final Context mContext;
    private IOccupantAwareness mOasHal;
    private final Object mLock = new Object();
    private final ChangeListenerToHalService mHalListener = new ChangeListenerToHalService(this);
    private final ChangeCallbackList mListeners = new ChangeCallbackList(this);

    private class ChangeCallbackList extends RemoteCallbackList<IOccupantAwarenessEventCallback> {
        private final WeakReference<OccupantAwarenessService> mOasService;

        ChangeCallbackList(OccupantAwarenessService oasService) {
            this.mOasService = new WeakReference<>(oasService);
        }

        @Override // android.os.RemoteCallbackList
        public void onCallbackDied(IOccupantAwarenessEventCallback listener) {
            Log.i("CAR.OAS", "binderDied: " + listener.asBinder());
            OccupantAwarenessService service = this.mOasService.get();
            if (service != null) {
                service.handleClientDisconnected();
            }
        }
    }

    public OccupantAwarenessService(Context context) {
        this.mContext = context;
    }

    OccupantAwarenessService(Context context, IOccupantAwareness oasInterface) {
        this.mContext = context;
        this.mOasHal = oasInterface;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        logd("Initializing service");
        connectToHalServiceIfNotConnected();
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        logd("Will stop detection and disconnect listeners");
        stopDetectionGraph();
        this.mListeners.kill();
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*OccupantAwarenessService*");
        Object[] objArr = new Object[1];
        objArr[0] = this.mOasHal == null ? "NOT connected" : "Connected";
        writer.println(String.format("%s to HAL service", objArr));
        writer.println(String.format("%d change listeners subscribed.", Integer.valueOf(this.mListeners.getRegisteredCallbackCount())));
    }

    private void connectToHalServiceIfNotConnected() {
        logd("connectToHalServiceIfNotConnected()");
        synchronized (this.mLock) {
            if (this.mOasHal != null) {
                logd("Client is already connected, nothing more to do");
                return;
            }
            logd("Attempting to connect to client at: android.hardware.automotive.occupant_awareness.IOccupantAwareness/default");
            IOccupantAwareness iOccupantAwarenessAsInterface = IOccupantAwareness.Stub.asInterface(ServiceManager.getService(OAS_SERVICE_ID));
            this.mOasHal = iOccupantAwarenessAsInterface;
            if (iOccupantAwarenessAsInterface == null) {
                Log.e("CAR.OAS", "Failed to find OAS hal_service at: [android.hardware.automotive.occupant_awareness.IOccupantAwareness/default]");
                return;
            }
            try {
                iOccupantAwarenessAsInterface.setCallback(this.mHalListener);
                logd("Successfully connected to hal_service at: [android.hardware.automotive.occupant_awareness.IOccupantAwareness/default]");
            } catch (RemoteException e) {
                this.mOasHal = null;
                Log.e("CAR.OAS", "Failed to set callback: " + e);
            }
        }
    }

    private void startDetectionGraph() {
        IOccupantAwareness hal;
        logd("Attempting to start detection graph");
        synchronized (this.mLock) {
            hal = this.mOasHal;
        }
        if (hal != null) {
            try {
                hal.startDetection();
                return;
            } catch (RemoteException e) {
                Log.e("CAR.OAS", "startDetection() HAL invocation failed: " + e, e);
                synchronized (this.mLock) {
                    this.mOasHal = null;
                    return;
                }
            }
        }
        Log.e("CAR.OAS", "No HAL is connected. Cannot request graph start");
    }

    private void stopDetectionGraph() {
        IOccupantAwareness hal;
        logd("Attempting to stop detection graph.");
        synchronized (this.mLock) {
            hal = this.mOasHal;
        }
        if (hal != null) {
            try {
                hal.stopDetection();
                return;
            } catch (RemoteException e) {
                Log.e("CAR.OAS", "stopDetection() HAL invocation failed: " + e, e);
                synchronized (this.mLock) {
                    this.mOasHal = null;
                    return;
                }
            }
        }
        Log.e("CAR.OAS", "No HAL is connected. Cannot request graph stop");
    }

    public int getCapabilityForRole(int role) {
        IOccupantAwareness hal;
        ICarImpl.assertPermission(this.mContext, Manifest.permission.READ_CAR_OCCUPANT_AWARENESS_STATE);
        connectToHalServiceIfNotConnected();
        synchronized (this.mLock) {
            hal = this.mOasHal;
        }
        if (hal != null) {
            try {
                return hal.getCapabilityForRole(role);
            } catch (RemoteException e) {
                Log.e("CAR.OAS", "getCapabilityForRole() HAL invocation failed: " + e, e);
                synchronized (this.mLock) {
                    this.mOasHal = null;
                    return 0;
                }
            }
        }
        Log.e("CAR.OAS", "getCapabilityForRole(): No HAL interface has been provided. Cannot get capabilities");
        return 0;
    }

    public void registerEventListener(IOccupantAwarenessEventCallback listener) {
        ICarImpl.assertPermission(this.mContext, Manifest.permission.READ_CAR_OCCUPANT_AWARENESS_STATE);
        connectToHalServiceIfNotConnected();
        synchronized (this.mLock) {
            if (this.mOasHal == null) {
                Log.e("CAR.OAS", "Attempting to register a listener, but could not connect to HAL.");
                return;
            }
            logd("Registering a new listener");
            this.mListeners.register(listener);
            if (this.mListeners.getRegisteredCallbackCount() == 1) {
                startDetectionGraph();
            }
        }
    }

    public void unregisterEventListener(IOccupantAwarenessEventCallback listener) {
        ICarImpl.assertPermission(this.mContext, Manifest.permission.READ_CAR_OCCUPANT_AWARENESS_STATE);
        connectToHalServiceIfNotConnected();
        synchronized (this.mLock) {
            this.mListeners.unregister(listener);
        }
        handleClientDisconnected();
    }

    void processStatusEvent(SystemStatusEvent statusEvent) {
        int idx = this.mListeners.beginBroadcast();
        while (true) {
            int idx2 = idx - 1;
            if (idx > 0) {
                IOccupantAwarenessEventCallback listener = this.mListeners.getBroadcastItem(idx2);
                try {
                    listener.onStatusChanged(statusEvent);
                } catch (RemoteException e) {
                    Log.e("CAR.OAS", "onStatusChanged() invocation failed: " + e, e);
                }
                idx = idx2;
            } else {
                this.mListeners.finishBroadcast();
                return;
            }
        }
    }

    void processDetectionEvent(OccupantAwarenessDetection detection) {
        int idx = this.mListeners.beginBroadcast();
        while (true) {
            int idx2 = idx - 1;
            if (idx > 0) {
                IOccupantAwarenessEventCallback listener = this.mListeners.getBroadcastItem(idx2);
                try {
                    listener.onDetectionEvent(detection);
                } catch (RemoteException e) {
                    Log.e("CAR.OAS", "onDetectionEvent() invocation failed: " + e, e);
                }
                idx = idx2;
            } else {
                this.mListeners.finishBroadcast();
                return;
            }
        }
    }

    void handleClientDisconnected() {
        synchronized (this.mLock) {
            if (this.mListeners.getRegisteredCallbackCount() == 0) {
                stopDetectionGraph();
            }
        }
    }

    private static void logd(String msg) {
    }

    private static class ChangeListenerToHalService extends IOccupantAwarenessClientCallback.Stub {
        private final WeakReference<OccupantAwarenessService> mOasService;

        ChangeListenerToHalService(OccupantAwarenessService oasService) {
            this.mOasService = new WeakReference<>(oasService);
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
        public void onSystemStatusChanged(int inputDetectionFlags, byte inputStatus) {
            OccupantAwarenessService service = this.mOasService.get();
            if (service != null) {
                service.processStatusEvent(OccupantAwarenessUtils.convertToStatusEvent(inputDetectionFlags, inputStatus));
            }
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
        public void onDetectionEvent(OccupantDetections detections) {
            OccupantAwarenessService service = this.mOasService.get();
            if (service != null) {
                for (OccupantDetection detection : detections.detections) {
                    service.processDetectionEvent(OccupantAwarenessUtils.convertToDetectionEvent(detections.timeStampMillis, detection));
                }
            }
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
        public int getInterfaceVersion() {
            return 1;
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
        public String getInterfaceHash() {
            return "3614b1c47ed7be85c1e77554e7f04966cf35b465";
        }
    }
}
