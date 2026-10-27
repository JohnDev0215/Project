package com.android.car;

import android.car.drivingstate.CarDrivingStateEvent;
import android.car.drivingstate.ICarDrivingState;
import android.car.drivingstate.ICarDrivingStateChangeListener;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyEvent;
import android.car.hardware.property.ICarPropertyEventListener;
import android.content.Context;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.android.car.Manifest;
import com.android.car.Utils;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CarDrivingStateService extends ICarDrivingState.Stub implements CarServiceBase {
    private static final boolean DBG = false;
    private static final int MAX_TRANSITION_LOG_SIZE = 20;
    private static final int NOT_RECEIVED = -1;
    private static final int PROPERTY_UPDATE_RATE = 5;
    private static final int[] REQUIRED_PROPERTIES = {VehicleProperty.PERF_VEHICLE_SPEED, VehicleProperty.GEAR_SELECTION, VehicleProperty.PARKING_BRAKE_ON};
    private static final String TAG = "CarDrivingState";
    private final Context mContext;
    private int mLastGear;
    private boolean mLastParkingBrakeState;
    private float mLastSpeed;
    private final CarPropertyService mPropertyService;
    private List<Integer> mSupportedGears;
    private final RemoteCallbackList<ICarDrivingStateChangeListener> mDrivingStateClients = new RemoteCallbackList<>();
    private final HandlerThread mClientDispatchThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
    private final Handler mClientDispatchHandler = new Handler(this.mClientDispatchThread.getLooper());
    private final Object mLock = new Object();
    private final LinkedList<Utils.TransitionLog> mTransitionLogs = new LinkedList<>();
    private long mLastGearTimestamp = -1;
    private long mLastSpeedTimestamp = -1;
    private long mLastParkingBrakeTimestamp = -1;
    private final ICarPropertyEventListener mICarPropertyEventListener = new ICarPropertyEventListener.Stub() { // from class: com.android.car.CarDrivingStateService.1
        public void onEvent(List<CarPropertyEvent> events) throws RemoteException {
            synchronized (CarDrivingStateService.this.mLock) {
                for (CarPropertyEvent event : events) {
                    CarDrivingStateService.this.handlePropertyEventLocked(event);
                }
            }
        }
    };
    private CarDrivingStateEvent mCurrentDrivingState = createDrivingStateEvent(-1);

    public CarDrivingStateService(Context context, CarPropertyService propertyService) {
        this.mContext = context;
        this.mPropertyService = propertyService;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        subscribeToProperties();
        synchronized (this.mLock) {
            CarDrivingStateEvent carDrivingStateEventCreateDrivingStateEvent = createDrivingStateEvent(inferDrivingStateLocked());
            this.mCurrentDrivingState = carDrivingStateEventCreateDrivingStateEvent;
            addTransitionLogLocked("CarDrivingState Boot", -1, carDrivingStateEventCreateDrivingStateEvent.eventValue, this.mCurrentDrivingState.timeStamp);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        for (int property : REQUIRED_PROPERTIES) {
            this.mPropertyService.unregisterListener(property, this.mICarPropertyEventListener);
        }
        while (this.mDrivingStateClients.getRegisteredCallbackCount() > 0) {
            for (int i = this.mDrivingStateClients.getRegisteredCallbackCount() - 1; i >= 0; i--) {
                ICarDrivingStateChangeListener client = this.mDrivingStateClients.getRegisteredCallbackItem(i);
                if (client != null) {
                    this.mDrivingStateClients.unregister(client);
                }
            }
        }
        this.mCurrentDrivingState = createDrivingStateEvent(-1);
    }

    private boolean checkPropertySupport() {
        List<CarPropertyConfig> configs = this.mPropertyService.getPropertyList();
        for (int propertyId : REQUIRED_PROPERTIES) {
            boolean found = false;
            Iterator<CarPropertyConfig> it = configs.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                CarPropertyConfig config = it.next();
                if (config.getPropertyId() == propertyId) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Log.e(TAG, "Required property not supported: " + propertyId);
                return false;
            }
        }
        return true;
    }

    private void subscribeToProperties() {
        for (int propertyId : REQUIRED_PROPERTIES) {
            this.mPropertyService.registerListener(propertyId, 5.0f, this.mICarPropertyEventListener);
        }
    }

    public void registerDrivingStateChangeListener(ICarDrivingStateChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        this.mDrivingStateClients.register(listener);
    }

    public void unregisterDrivingStateChangeListener(ICarDrivingStateChangeListener listener) {
        if (listener == null) {
            Log.e(TAG, "unregisterDrivingStateChangeListener(): listener null");
            throw new IllegalArgumentException("Listener is null");
        }
        this.mDrivingStateClients.unregister(listener);
    }

    public CarDrivingStateEvent getCurrentDrivingState() {
        CarDrivingStateEvent carDrivingStateEvent;
        synchronized (this.mLock) {
            carDrivingStateEvent = this.mCurrentDrivingState;
        }
        return carDrivingStateEvent;
    }

    public void injectDrivingState(CarDrivingStateEvent event) {
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CONTROL_APP_BLOCKING);
        dispatchEventToClients(event);
    }

    private void dispatchEventToClients(final CarDrivingStateEvent event) {
        boolean success = this.mClientDispatchHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$CarDrivingStateService$tOkcV8o7jsn0oqor0Ra0HOTL5CI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dispatchEventToClients$0$CarDrivingStateService(event);
            }
        });
        if (!success) {
            Log.e(TAG, String.format("Unable to post (%s) event to dispatch handler", event));
        }
    }

    public /* synthetic */ void lambda$dispatchEventToClients$0$CarDrivingStateService(CarDrivingStateEvent event) {
        int numClients = this.mDrivingStateClients.beginBroadcast();
        for (int i = 0; i < numClients; i++) {
            ICarDrivingStateChangeListener callback = this.mDrivingStateClients.getBroadcastItem(i);
            try {
                callback.onDrivingStateChanged(event);
            } catch (RemoteException e) {
                Log.e(TAG, String.format("Dispatch to listener %s failed for event (%s)", callback, event));
            }
        }
        this.mDrivingStateClients.finishBroadcast();
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarDrivingStateService*");
        this.mDrivingStateClients.dump(writer, "Driving State Clients ");
        writer.println("Driving state change log:");
        synchronized (this.mLock) {
            for (Utils.TransitionLog tLog : this.mTransitionLogs) {
                writer.println(tLog);
            }
            writer.println("Current Driving State: " + this.mCurrentDrivingState.eventValue);
            if (this.mSupportedGears != null) {
                writer.println("Supported gears:");
                for (Integer gear : this.mSupportedGears) {
                    writer.print("Gear:" + gear);
                }
            }
        }
    }

    void handlePropertyEventLocked(CarPropertyEvent event) {
        if (event.getEventType() != 0) {
            return;
        }
        CarPropertyValue value = event.getCarPropertyValue();
        int propId = value.getPropertyId();
        long curTimestamp = value.getTimestamp();
        if (propId == 287310850) {
            boolean curParkingBrake = ((Boolean) value.getValue()).booleanValue();
            if (curTimestamp > this.mLastParkingBrakeTimestamp) {
                this.mLastParkingBrakeTimestamp = curTimestamp;
                this.mLastParkingBrakeState = curParkingBrake;
            }
        } else if (propId == 289408000) {
            if (this.mSupportedGears == null) {
                this.mSupportedGears = getSupportedGears();
            }
            ((Integer) value.getValue()).intValue();
            if (curTimestamp > this.mLastGearTimestamp) {
                this.mLastGearTimestamp = curTimestamp;
                this.mLastGear = ((Integer) value.getValue()).intValue();
            }
        } else if (propId == 291504647) {
            float curSpeed = ((Float) value.getValue()).floatValue();
            if (curTimestamp > this.mLastSpeedTimestamp) {
                this.mLastSpeedTimestamp = curTimestamp;
                this.mLastSpeed = curSpeed;
            }
        } else {
            Log.e(TAG, "Received property event for unhandled propId=" + propId);
        }
        int drivingState = inferDrivingStateLocked();
        if (drivingState != this.mCurrentDrivingState.eventValue) {
            addTransitionLogLocked(TAG, this.mCurrentDrivingState.eventValue, drivingState, System.currentTimeMillis());
            this.mCurrentDrivingState = createDrivingStateEvent(drivingState);
            CarDrivingStateEvent currentDrivingStateEvent = this.mCurrentDrivingState;
            dispatchEventToClients(currentDrivingStateEvent);
        }
    }

    private List<Integer> getSupportedGears() {
        List<CarPropertyConfig> properyList = this.mPropertyService.getPropertyList();
        for (CarPropertyConfig p : properyList) {
            if (p.getPropertyId() == 289408000) {
                return p.getConfigArray();
            }
        }
        return null;
    }

    private void addTransitionLogLocked(String name, int from, int to, long timestamp) {
        if (this.mTransitionLogs.size() >= 20) {
            this.mTransitionLogs.remove();
        }
        Utils.TransitionLog tLog = new Utils.TransitionLog(name, Integer.valueOf(from), Integer.valueOf(to), timestamp);
        this.mTransitionLogs.add(tLog);
    }

    private int inferDrivingStateLocked() {
        updateVehiclePropertiesIfNeededLocked();
        if (isVehicleKnownToBeParkedLocked()) {
            return 0;
        }
        if (this.mLastSpeedTimestamp == -1) {
            return -1;
        }
        float f = this.mLastSpeed;
        if (f < 0.0f) {
            return -1;
        }
        if (f == 0.0f) {
            return 1;
        }
        return 2;
    }

    private boolean isVehicleKnownToBeParkedLocked() {
        if (this.mLastGearTimestamp != -1 && this.mLastGear == 4) {
            return true;
        }
        if (this.mLastParkingBrakeTimestamp != -1 && isCarManualTransmissionTypeLocked()) {
            return this.mLastParkingBrakeState;
        }
        return false;
    }

    private boolean isCarManualTransmissionTypeLocked() {
        List<Integer> list = this.mSupportedGears;
        if (list != null && !list.isEmpty() && !this.mSupportedGears.contains(4)) {
            return true;
        }
        return false;
    }

    private void updateVehiclePropertiesIfNeededLocked() {
        CarPropertyValue propertyValue;
        CarPropertyValue propertyValue2;
        CarPropertyValue propertyValue3;
        if (this.mLastGearTimestamp == -1 && (propertyValue3 = this.mPropertyService.getProperty(VehicleProperty.GEAR_SELECTION, 0)) != null) {
            this.mLastGear = ((Integer) propertyValue3.getValue()).intValue();
            this.mLastGearTimestamp = propertyValue3.getTimestamp();
        }
        if (this.mLastParkingBrakeTimestamp == -1 && (propertyValue2 = this.mPropertyService.getProperty(VehicleProperty.PARKING_BRAKE_ON, 0)) != null) {
            this.mLastParkingBrakeState = ((Boolean) propertyValue2.getValue()).booleanValue();
            this.mLastParkingBrakeTimestamp = propertyValue2.getTimestamp();
        }
        if (this.mLastSpeedTimestamp == -1 && (propertyValue = this.mPropertyService.getProperty(VehicleProperty.PERF_VEHICLE_SPEED, 0)) != null) {
            this.mLastSpeed = ((Float) propertyValue.getValue()).floatValue();
            this.mLastSpeedTimestamp = propertyValue.getTimestamp();
        }
    }

    private static CarDrivingStateEvent createDrivingStateEvent(int eventValue) {
        return new CarDrivingStateEvent(eventValue, SystemClock.elapsedRealtimeNanos());
    }
}
