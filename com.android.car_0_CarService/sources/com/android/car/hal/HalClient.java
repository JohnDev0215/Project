package com.android.car.hal;

import android.hardware.automotive.vehicle.V2_0.IVehicle;
import android.hardware.automotive.vehicle.V2_0.IVehicleCallback;
import android.hardware.automotive.vehicle.V2_0.StatusCode;
import android.hardware.automotive.vehicle.V2_0.SubscribeOptions;
import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
final class HalClient {
    private static final boolean DEBUG = false;
    private static final int SLEEP_BETWEEN_RETRIABLE_INVOKES_MS = 50;
    private static final String TAG = "CAR.HAL";
    private static final int WAIT_CAP_FOR_RETRIABLE_RESULT_MS = 2000;
    private final IVehicleCallback mInternalCallback;
    private final int mSleepMs;
    private final IVehicle mVehicle;
    private final int mWaitCapMs;

    interface RetriableCallback {
        int action();
    }

    HalClient(IVehicle vehicle, Looper looper, IVehicleCallback callback) {
        this(vehicle, looper, callback, WAIT_CAP_FOR_RETRIABLE_RESULT_MS, 50);
    }

    HalClient(IVehicle vehicle, Looper looper, IVehicleCallback callback, int waitCapMs, int sleepMs) {
        this.mVehicle = vehicle;
        Handler handler = new CallbackHandler(looper, callback);
        this.mInternalCallback = new VehicleCallback(handler);
        this.mWaitCapMs = waitCapMs;
        this.mSleepMs = sleepMs;
    }

    ArrayList<VehiclePropConfig> getAllPropConfigs() throws RemoteException {
        return this.mVehicle.getAllPropConfigs();
    }

    public void subscribe(SubscribeOptions... options) throws RemoteException {
        this.mVehicle.subscribe(this.mInternalCallback, new ArrayList<>(Arrays.asList(options)));
    }

    public void unsubscribe(int prop) throws RemoteException {
        this.mVehicle.unsubscribe(this.mInternalCallback, prop);
    }

    public void setValue(final VehiclePropValue propValue) {
        int status = invokeRetriable(new RetriableCallback() { // from class: com.android.car.hal.-$$Lambda$HalClient$ISrOz3scOegDP8QDVNtMY9DvoPQ
            @Override // com.android.car.hal.HalClient.RetriableCallback
            public final int action() {
                return this.f$0.lambda$setValue$0$HalClient(propValue);
            }
        }, this.mWaitCapMs, this.mSleepMs);
        if (2 == status) {
            throw new IllegalArgumentException(getValueErrorMessage("set", propValue));
        }
        if (status != 0) {
            Log.e("CAR.HAL", getPropertyErrorMessage("set", propValue, status));
            Log.e("CAR.HAL", "Car.Service " + String.format("Failed to set property: 0x%s, areaId: 0x%s, code: %d", Integer.toHexString(propValue.prop), Integer.toHexString(propValue.areaId), Integer.valueOf(status)));
        }
    }

    public /* synthetic */ int lambda$setValue$0$HalClient(VehiclePropValue propValue) {
        try {
            return this.mVehicle.set(propValue);
        } catch (RemoteException e) {
            Log.e("CAR.HAL", getValueErrorMessage("set", propValue), e);
            return 1;
        }
    }

    private String getValueErrorMessage(String action, VehiclePropValue propValue) {
        return String.format("Failed to %s value for: 0x%s, areaId: 0x%s", action, Integer.toHexString(propValue.prop), Integer.toHexString(propValue.areaId));
    }

    private String getPropertyErrorMessage(String action, VehiclePropValue propValue, int status) {
        return String.format("Failed to %s property: 0x%s, areaId: 0x%s, code: %d (%s)", action, Integer.toHexString(propValue.prop), Integer.toHexString(propValue.areaId), Integer.valueOf(status), StatusCode.toString(status));
    }

    VehiclePropValue getValue(final VehiclePropValue requestedPropValue) {
        final ObjectWrapper<VehiclePropValue> valueWrapper = new ObjectWrapper<>();
        int status = invokeRetriable(new RetriableCallback() { // from class: com.android.car.hal.-$$Lambda$HalClient$4tULf787eBeMd7TnWMQdfv1PDA4
            @Override // com.android.car.hal.HalClient.RetriableCallback
            public final int action() {
                return this.f$0.lambda$getValue$1$HalClient(requestedPropValue, valueWrapper);
            }
        }, this.mWaitCapMs, this.mSleepMs);
        if (2 == status) {
            Log.e("CAR.HAL", "Car.Service " + String.format("Failed to get property: 0x%x, areaId: 0x%x, code: %d", Integer.valueOf(requestedPropValue.prop), Integer.valueOf(requestedPropValue.areaId), Integer.valueOf(status)));
        }
        if (status != 0 || valueWrapper.object == null) {
            if (status == 0) {
                status = 3;
            }
            Log.e("CAR.HAL", getPropertyErrorMessage("get", requestedPropValue, status));
            Log.e("CAR.HAL", "Car.Service " + String.format("Failed to get property: 0x%x, areaId: 0x%x, code: %d", Integer.valueOf(requestedPropValue.prop), Integer.valueOf(requestedPropValue.areaId), Integer.valueOf(status)));
        }
        return valueWrapper.object;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.hardware.automotive.vehicle.V2_0.VehiclePropValue] */
    public /* synthetic */ int lambda$getValue$1$HalClient(VehiclePropValue requestedPropValue, ObjectWrapper valueWrapper) {
        ValueResult res = internalGet(requestedPropValue);
        valueWrapper.object = res.propValue;
        return res.status;
    }

    private ValueResult internalGet(VehiclePropValue requestedPropValue) {
        final ValueResult result = new ValueResult();
        try {
            Log.d("CAR.HAL", "Car.Service call hal get. 0x" + String.format("%x", Integer.valueOf(requestedPropValue.prop)));
            this.mVehicle.get(requestedPropValue, new IVehicle.getCallback() { // from class: com.android.car.hal.-$$Lambda$HalClient$Tk2P2ZkQluWkrdwxcsV2ZCxY2h8
                @Override // android.hardware.automotive.vehicle.V2_0.IVehicle.getCallback
                public final void onValues(int i, VehiclePropValue vehiclePropValue) {
                    HalClient.lambda$internalGet$2(result, i, vehiclePropValue);
                }
            });
        } catch (RemoteException e) {
            Log.e("CAR.HAL", getValueErrorMessage("get", requestedPropValue), e);
            result.status = 1;
        }
        return result;
    }

    static /* synthetic */ void lambda$internalGet$2(ValueResult result, int status, VehiclePropValue propValue) {
        result.status = status;
        result.propValue = propValue;
    }

    private static int invokeRetriable(RetriableCallback callback, long timeoutMs, long sleepMs) {
        int status = callback.action();
        long startTime = SystemClock.elapsedRealtime();
        while (1 == status && SystemClock.elapsedRealtime() - startTime < timeoutMs) {
            try {
                Thread.sleep(sleepMs);
                status = callback.action();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Log.e("CAR.HAL", "Thread was interrupted while waiting for vehicle HAL.", e);
            }
        }
        return status;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class ObjectWrapper<T> {
        T object;

        private ObjectWrapper() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class ValueResult {
        VehiclePropValue propValue;
        int status;

        private ValueResult() {
        }
    }

    private static final class PropertySetError {
        final int areaId;
        final int errorCode;
        final int propId;

        PropertySetError(int errorCode, int propId, int areaId) {
            this.errorCode = errorCode;
            this.propId = propId;
            this.areaId = areaId;
        }
    }

    private static final class CallbackHandler extends Handler {
        private static final int MSG_ON_PROPERTY_EVENT = 2;
        private static final int MSG_ON_PROPERTY_SET = 1;
        private static final int MSG_ON_SET_ERROR = 3;
        private final WeakReference<IVehicleCallback> mCallback;

        CallbackHandler(Looper looper, IVehicleCallback callback) {
            super(looper);
            this.mCallback = new WeakReference<>(callback);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            IVehicleCallback callback = this.mCallback.get();
            if (callback == null) {
                Log.i("CAR.HAL", "handleMessage null callback");
                return;
            }
            try {
                int i = msg.what;
                if (i == 1) {
                    callback.onPropertySet((VehiclePropValue) msg.obj);
                } else if (i == 2) {
                    callback.onPropertyEvent((ArrayList) msg.obj);
                } else if (i == 3) {
                    PropertySetError obj = (PropertySetError) msg.obj;
                    callback.onPropertySetError(obj.errorCode, obj.propId, obj.areaId);
                } else {
                    Log.e("CAR.HAL", "Unexpected message: " + msg.what);
                }
            } catch (RemoteException e) {
                Log.e("CAR.HAL", "Message failed: " + msg.what);
            }
        }
    }

    private static final class VehicleCallback extends IVehicleCallback.Stub {
        private final Handler mHandler;

        VehicleCallback(Handler handler) {
            this.mHandler = handler;
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicleCallback
        public void onPropertyEvent(ArrayList<VehiclePropValue> propValues) {
            Handler handler = this.mHandler;
            handler.sendMessage(Message.obtain(handler, 2, propValues));
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicleCallback
        public void onPropertySet(VehiclePropValue propValue) {
            Handler handler = this.mHandler;
            handler.sendMessage(Message.obtain(handler, 1, propValue));
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicleCallback
        public void onPropertySetError(int errorCode, int propId, int areaId) {
            Handler handler = this.mHandler;
            handler.sendMessage(Message.obtain(handler, 3, new PropertySetError(errorCode, propId, areaId)));
        }
    }
}
