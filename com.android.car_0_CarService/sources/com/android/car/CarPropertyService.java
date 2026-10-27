package com.android.car;

import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyEvent;
import android.car.hardware.property.ICarProperty;
import android.car.hardware.property.ICarPropertyEventListener;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.android.car.Manifest;
import com.android.car.hal.PropertyHalService;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class CarPropertyService extends ICarProperty.Stub implements CarServiceBase, PropertyHalService.PropertyHalListener {
    private static final boolean DBG = true;
    private static final String TAG = "Property.service";
    private final Context mContext;
    private final PropertyHalService mHal;
    private final Map<IBinder, Client> mClientMap = new ConcurrentHashMap();
    private final Map<Integer, CarPropertyConfig<?>> mConfigs = new HashMap();
    private boolean mListenerIsSet = false;
    private final Map<Integer, List<Client>> mPropIdClientMap = new ConcurrentHashMap();
    private final Object mLock = new Object();
    private final SparseArray<SparseArray<Client>> mSetOperationClientMap = new SparseArray<>();
    private final HandlerThread mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
    private final Handler mHandler = new Handler(this.mHandlerThread.getLooper());

    public CarPropertyService(Context context, PropertyHalService hal) {
        Log.d(TAG, "CarPropertyService started!");
        this.mHal = hal;
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class Client implements IBinder.DeathRecipient {
        private final ICarPropertyEventListener mListener;
        private final IBinder mListenerBinder;
        private final SparseArray<Float> mRateMap = new SparseArray<>();

        Client(ICarPropertyEventListener listener) {
            this.mListener = listener;
            IBinder iBinderAsBinder = listener.asBinder();
            this.mListenerBinder = iBinderAsBinder;
            try {
                iBinderAsBinder.linkToDeath(this, 0);
                CarPropertyService.this.mClientMap.put(this.mListenerBinder, this);
            } catch (RemoteException e) {
                throw new IllegalStateException("Client already dead", e);
            }
        }

        void addProperty(int propId, float rate) {
            this.mRateMap.put(propId, Float.valueOf(rate));
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.d(CarPropertyService.TAG, "binderDied " + this.mListenerBinder);
            for (int i = 0; i < this.mRateMap.size(); i++) {
                int propId = this.mRateMap.keyAt(i);
                CarPropertyService.this.unregisterListenerBinderLocked(propId, this.mListenerBinder);
            }
            release();
        }

        ICarPropertyEventListener getListener() {
            return this.mListener;
        }

        IBinder getListenerBinder() {
            return this.mListenerBinder;
        }

        float getRate(int propId) {
            return this.mRateMap.get(propId, Float.valueOf(0.0f)).floatValue();
        }

        void release() {
            this.mListenerBinder.unlinkToDeath(this, 0);
            CarPropertyService.this.mClientMap.remove(this.mListenerBinder);
        }

        void removeProperty(int propId) {
            this.mRateMap.remove(propId);
            if (this.mRateMap.size() == 0) {
                release();
            }
        }
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        synchronized (this.mLock) {
            this.mConfigs.clear();
            this.mConfigs.putAll(this.mHal.getPropertyList());
        }
        Log.d(TAG, "cache CarPropertyConfigs " + this.mConfigs.size());
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        for (Client c : this.mClientMap.values()) {
            c.release();
        }
        this.mClientMap.clear();
        this.mPropIdClientMap.clear();
        this.mHal.setListener(null);
        this.mListenerIsSet = false;
        synchronized (this.mLock) {
            this.mSetOperationClientMap.clear();
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarPropertyService*");
        synchronized (this.mLock) {
            writer.println("    Listener is set for PropertyHalService: " + this.mListenerIsSet);
            writer.println("    There are " + this.mClientMap.size() + " clients using CarPropertyService.");
            writer.println("    Properties registered: ");
            Iterator<Integer> it = this.mPropIdClientMap.keySet().iterator();
            while (it.hasNext()) {
                int propId = it.next().intValue();
                writer.println("        propId: 0x" + Integer.toHexString(propId) + " is registered by " + this.mPropIdClientMap.get(Integer.valueOf(propId)).size() + " client(s).");
            }
            writer.println("    Properties changed by CarPropertyService: ");
            for (int i = 0; i < this.mSetOperationClientMap.size(); i++) {
                int propId2 = this.mSetOperationClientMap.keyAt(i);
                SparseArray<Client> sparseArrayValueAt = this.mSetOperationClientMap.valueAt(i);
                for (int j = 0; j < sparseArrayValueAt.size(); j++) {
                    int areaId = sparseArrayValueAt.keyAt(j);
                    writer.println("        propId: 0x" + Integer.toHexString(propId2) + " areaId: 0x" + Integer.toHexString(areaId) + " by client: " + sparseArrayValueAt.valueAt(j));
                }
            }
        }
    }

    public void registerListener(int propId, float rate, ICarPropertyEventListener listener) {
        Log.d(TAG, "registerListener: propId=0x" + Integer.toHexString(propId) + " rate=" + rate);
        if (listener == null) {
            Log.e(TAG, "registerListener: Listener is null.");
            throw new IllegalArgumentException("listener cannot be null.");
        }
        IBinder listenerBinder = listener.asBinder();
        synchronized (this.mLock) {
            final CarPropertyConfig<?> carPropertyConfig = this.mConfigs.get(Integer.valueOf(propId));
            if (carPropertyConfig == null) {
                Log.e(TAG, "registerListener:  propId is not in config list: 0x" + Integer.toHexString(propId));
                return;
            }
            ICarImpl.assertPermission(this.mContext, this.mHal.getReadPermission(propId));
            final Client client = this.mClientMap.get(listenerBinder);
            if (client == null) {
                client = new Client(listener);
            }
            client.addProperty(propId, rate);
            List<Client> clients = this.mPropIdClientMap.get(Integer.valueOf(propId));
            if (clients == null) {
                clients = new CopyOnWriteArrayList();
                this.mPropIdClientMap.put(Integer.valueOf(propId), clients);
            }
            if (!clients.contains(client)) {
                clients.add(client);
            }
            if (!this.mListenerIsSet) {
                this.mHal.setListener(this);
            }
            if (rate > this.mHal.getSampleRate(propId)) {
                this.mHal.subscribeProperty(propId, rate);
            }
            this.mHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$CarPropertyService$JD0kqXopwu6hfyMf2mVRslkBpSU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerListener$0$CarPropertyService(carPropertyConfig, client);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAndDispatchPropertyInitValue, reason: merged with bridge method [inline-methods] */
    public void lambda$registerListener$0$CarPropertyService(CarPropertyConfig config, Client client) {
        List<CarPropertyEvent> events = new LinkedList<>();
        int propId = config.getPropertyId();
        if (config.isGlobalProperty()) {
            CarPropertyValue value = this.mHal.getProperty(propId, 0);
            if (value != null) {
                CarPropertyEvent event = new CarPropertyEvent(0, value);
                events.add(event);
            }
        } else {
            for (int areaId : config.getAreaIds()) {
                CarPropertyValue value2 = this.mHal.getProperty(propId, areaId);
                if (value2 != null) {
                    CarPropertyEvent event2 = new CarPropertyEvent(0, value2);
                    events.add(event2);
                }
            }
        }
        try {
            client.getListener().onEvent(events);
        } catch (RemoteException ex) {
            if (config.isGlobalProperty()) {
                CarPropertyValue value3 = this.mHal.getProperty(propId, 0);
                if (value3 != null) {
                    Log.w(TAG, "onEvent calling failed:" + value3.toString());
                }
            } else {
                for (int areaId2 : config.getAreaIds()) {
                    CarPropertyValue value4 = this.mHal.getProperty(propId, areaId2);
                    if (value4 != null) {
                        Log.w(TAG, "onEvent calling failed:" + value4.toString());
                    }
                }
            }
            Log.e(TAG, "onEvent calling failed: " + ex);
        }
    }

    public void unregisterListener(int propId, ICarPropertyEventListener listener) {
        Log.d(TAG, "unregisterListener propId=0x" + Integer.toHexString(propId));
        ICarImpl.assertPermission(this.mContext, this.mHal.getReadPermission(propId));
        if (listener == null) {
            Log.e(TAG, "unregisterListener: Listener is null.");
            throw new IllegalArgumentException("Listener is null");
        }
        IBinder listenerBinder = listener.asBinder();
        synchronized (this.mLock) {
            unregisterListenerBinderLocked(propId, listenerBinder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterListenerBinderLocked(int propId, IBinder listenerBinder) {
        Client client = this.mClientMap.get(listenerBinder);
        List<Client> propertyClients = this.mPropIdClientMap.get(Integer.valueOf(propId));
        synchronized (this.mLock) {
            if (this.mConfigs.get(Integer.valueOf(propId)) == null) {
                Log.e(TAG, "unregisterListener: propId is not in config list:0x" + Integer.toHexString(propId));
                return;
            }
            if (client == null || propertyClients == null) {
                Log.e(TAG, "unregisterListenerBinderLocked: Listener was not previously registered.");
                return;
            }
            if (propertyClients.remove(client)) {
                client.removeProperty(propId);
                clearSetOperationRecorderLocked(propId, client);
            } else {
                Log.e(TAG, "unregisterListenerBinderLocked: Listener was not registered for propId=0x" + Integer.toHexString(propId));
            }
            if (propertyClients.isEmpty()) {
                this.mHal.unsubscribeProperty(propId);
                this.mPropIdClientMap.remove(Integer.valueOf(propId));
                this.mSetOperationClientMap.remove(propId);
                if (this.mPropIdClientMap.isEmpty()) {
                    this.mHal.setListener(null);
                    this.mListenerIsSet = false;
                    return;
                }
                return;
            }
            float maxRate = 0.0f;
            for (Client c : propertyClients) {
                float rate = c.getRate(propId);
                if (rate > maxRate) {
                    maxRate = rate;
                }
            }
            this.mHal.subscribeProperty(propId, maxRate);
        }
    }

    public List<CarPropertyConfig> getPropertyList() {
        Set<CarPropertyConfig> allConfigs;
        List<CarPropertyConfig> returnList = new ArrayList<>();
        synchronized (this.mLock) {
            allConfigs = new HashSet<>(this.mConfigs.values());
        }
        for (CarPropertyConfig c : allConfigs) {
            if (ICarImpl.hasPermission(this.mContext, this.mHal.getReadPermission(c.getPropertyId()))) {
                returnList.add(c);
            }
        }
        Log.d(TAG, "getPropertyList returns " + returnList.size() + " configs");
        return returnList;
    }

    public CarPropertyValue getProperty(int prop, int zone) {
        synchronized (this.mLock) {
            if (this.mConfigs.get(Integer.valueOf(prop)) == null) {
                Log.e(TAG, "getProperty: propId is not in config list:0x" + Integer.toHexString(prop));
                return null;
            }
            ICarImpl.assertPermission(this.mContext, this.mHal.getReadPermission(prop));
            return this.mHal.getProperty(prop, zone);
        }
    }

    public String getReadPermission(int propId) {
        synchronized (this.mLock) {
            if (this.mConfigs.get(Integer.valueOf(propId)) == null) {
                Log.e(TAG, "getReadPermission: propId is not in config list:0x" + Integer.toHexString(propId));
                return null;
            }
            return this.mHal.getReadPermission(propId);
        }
    }

    public String getWritePermission(int propId) {
        synchronized (this.mLock) {
            if (this.mConfigs.get(Integer.valueOf(propId)) == null) {
                Log.e(TAG, "getWritePermission: propId is not in config list:0x" + Integer.toHexString(propId));
                return null;
            }
            return this.mHal.getWritePermission(propId);
        }
    }

    public void setProperty(CarPropertyValue prop, ICarPropertyEventListener listener) {
        int propId = prop.getPropertyId();
        boolean notExist = checkPropertyAccessibility(propId);
        if (notExist) {
            return;
        }
        if (this.mHal.isDisplayUnitsProperty(propId)) {
            ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_VENDOR_EXTENSION);
        }
        this.mHal.setProperty(prop);
        IBinder listenerBinder = listener.asBinder();
        synchronized (this.mLock) {
            Client client = this.mClientMap.get(listenerBinder);
            if (client == null) {
                client = new Client(listener);
            }
            updateSetOperationRecorder(propId, prop.getAreaId(), client);
        }
    }

    private boolean checkPropertyAccessibility(int propId) {
        synchronized (this.mLock) {
            if (this.mConfigs.get(Integer.valueOf(propId)) == null) {
                Log.e(TAG, "checkPropertyAccessibility: Property Id: 0x" + Integer.toHexString(propId) + " does not exist in the vehicle");
                return true;
            }
            String propertyWritePermission = this.mHal.getWritePermission(propId);
            if (propertyWritePermission == null) {
                throw new SecurityException("Platform does not have permission to change value for property Id: 0x" + Integer.toHexString(propId));
            }
            ICarImpl.assertPermission(this.mContext, propertyWritePermission);
            return false;
        }
    }

    private void updateSetOperationRecorder(int propId, int areaId, Client client) {
        if (this.mSetOperationClientMap.get(propId) != null) {
            this.mSetOperationClientMap.get(propId).put(areaId, client);
            return;
        }
        SparseArray<Client> areaIdToClient = new SparseArray<>();
        areaIdToClient.put(areaId, client);
        this.mSetOperationClientMap.put(propId, areaIdToClient);
    }

    private void clearSetOperationRecorderLocked(int propId, Client client) {
        SparseArray<Client> areaIdToClient = this.mSetOperationClientMap.get(propId);
        if (areaIdToClient != null) {
            List<Integer> indexNeedToRemove = new ArrayList<>();
            for (int index = 0; index < areaIdToClient.size(); index++) {
                if (client.equals(areaIdToClient.valueAt(index))) {
                    indexNeedToRemove.add(Integer.valueOf(index));
                }
            }
            Iterator<Integer> it = indexNeedToRemove.iterator();
            while (it.hasNext()) {
                int index2 = it.next().intValue();
                Log.d("ErrorEvent", " Clear propId:0x" + Integer.toHexString(propId) + " areaId: 0x" + Integer.toHexString(areaIdToClient.keyAt(index2)));
                areaIdToClient.removeAt(index2);
            }
        }
    }

    @Override // com.android.car.hal.PropertyHalService.PropertyHalListener
    public void onPropertyChange(List<CarPropertyEvent> events) {
        Map<IBinder, Pair<ICarPropertyEventListener, List<CarPropertyEvent>>> eventsToDispatch = new HashMap<>();
        for (CarPropertyEvent event : events) {
            int propId = event.getCarPropertyValue().getPropertyId();
            List<Client> clients = this.mPropIdClientMap.get(Integer.valueOf(propId));
            if (clients == null) {
                Log.e(TAG, "onPropertyChange: no listener registered for propId=0x" + Integer.toHexString(propId));
            } else {
                for (Client c : clients) {
                    IBinder listenerBinder = c.getListenerBinder();
                    Pair<ICarPropertyEventListener, List<CarPropertyEvent>> p = eventsToDispatch.get(listenerBinder);
                    if (p == null) {
                        p = new Pair<>(c.getListener(), new LinkedList());
                        eventsToDispatch.put(listenerBinder, p);
                    }
                    ((List) p.second).add(event);
                }
            }
        }
        for (Pair<ICarPropertyEventListener, List<CarPropertyEvent>> p2 : eventsToDispatch.values()) {
            try {
                ((ICarPropertyEventListener) p2.first).onEvent((List) p2.second);
            } catch (RemoteException ex) {
                for (CarPropertyEvent carPropertyEvent : (List) p2.second) {
                    CarPropertyValue<?> carPropertyValue = carPropertyEvent.getCarPropertyValue();
                    if (carPropertyValue != null) {
                        Log.w(TAG, "onEvent calling failed: " + carPropertyValue.toString());
                    }
                }
                Log.e(TAG, "onEvent calling failed: " + ex);
            }
        }
    }

    @Override // com.android.car.hal.PropertyHalService.PropertyHalListener
    public void onPropertySetError(int property, int areaId, int errorCode) {
        Client lastOperatedClient = null;
        synchronized (this.mLock) {
            if (this.mSetOperationClientMap.get(property) != null && this.mSetOperationClientMap.get(property).get(areaId) != null) {
                lastOperatedClient = this.mSetOperationClientMap.get(property).get(areaId);
            } else {
                Log.e(TAG, "Can not find the client changed propertyId: 0x" + Integer.toHexString(property) + " in areaId: 0x" + Integer.toHexString(areaId));
            }
        }
        if (lastOperatedClient != null) {
            dispatchToLastClient(property, areaId, errorCode, lastOperatedClient);
        }
    }

    private void dispatchToLastClient(int property, int areaId, int errorCode, Client lastOperatedClient) {
        try {
            List<CarPropertyEvent> eventList = new LinkedList<>();
            eventList.add(CarPropertyEvent.createErrorEventWithErrorCode(property, areaId, errorCode));
            lastOperatedClient.getListener().onEvent(eventList);
        } catch (RemoteException ex) {
            Log.e(TAG, "onEvent calling failed: " + ex);
        }
    }
}
