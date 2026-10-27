package com.android.car.hal;

import android.content.Context;
import android.hardware.automotive.vehicle.V2_0.IVehicle;
import android.hardware.automotive.vehicle.V2_0.IVehicleCallback;
import android.hardware.automotive.vehicle.V2_0.SubscribeOptions;
import android.hardware.automotive.vehicle.V2_0.VehicleAreaConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.CarLog;
import com.android.car.CarServiceUtils;
import com.google.android.collect.Lists;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public class VehicleHal extends IVehicleCallback.Stub {
    private static final String DATA_DELIMITER = ",";
    private static final boolean DBG = false;
    private static final int NO_AREA = -1;
    private final HashMap<Integer, VehiclePropConfig> mAllProperties;
    private final ArrayList<HalServiceBase> mAllServices;
    private DiagnosticHalService mDiagnosticHal;
    private final HashMap<Integer, VehiclePropertyEventInfo> mEventLog;
    private volatile HalClient mHalClient;
    private final HandlerThread mHandlerThread;
    private final InputHalService mInputHal;
    private final Object mLock;
    private final PowerHalService mPowerHal;
    private final PropertyHalService mPropertyHal;
    private final SparseArray<HalServiceBase> mPropertyHandlers;
    private final ArraySet<HalServiceBase> mServicesToDispatch;
    private final HashMap<Integer, SubscribeOptions> mSubscribedProperties;
    private final UserHalService mUserHal;
    private final VmsHalService mVmsHal;

    public VehicleHal(Context context, IVehicle vehicle) {
        this.mDiagnosticHal = null;
        this.mLock = new Object();
        this.mPropertyHandlers = new SparseArray<>();
        this.mAllServices = new ArrayList<>();
        this.mSubscribedProperties = new HashMap<>();
        this.mAllProperties = new HashMap<>();
        this.mEventLog = new HashMap<>();
        this.mServicesToDispatch = new ArraySet<>();
        this.mHandlerThread = CarServiceUtils.getHandlerThread(VehicleHal.class.getSimpleName());
        this.mPowerHal = new PowerHalService(this);
        this.mPropertyHal = new PropertyHalService(this);
        this.mInputHal = new InputHalService(this);
        this.mVmsHal = new VmsHalService(context, this);
        this.mDiagnosticHal = new DiagnosticHalService(this);
        UserHalService userHalService = new UserHalService(this);
        this.mUserHal = userHalService;
        this.mAllServices.addAll(Arrays.asList(this.mPowerHal, this.mInputHal, this.mDiagnosticHal, this.mVmsHal, userHalService, this.mPropertyHal));
        this.mHalClient = new HalClient(vehicle, this.mHandlerThread.getLooper(), this);
    }

    public VehicleHal(PowerHalService powerHal, DiagnosticHalService diagnosticHal, HalClient halClient, PropertyHalService propertyHal) {
        this.mDiagnosticHal = null;
        this.mLock = new Object();
        this.mPropertyHandlers = new SparseArray<>();
        this.mAllServices = new ArrayList<>();
        this.mSubscribedProperties = new HashMap<>();
        this.mAllProperties = new HashMap<>();
        this.mEventLog = new HashMap<>();
        this.mServicesToDispatch = new ArraySet<>();
        this.mHandlerThread = null;
        this.mPowerHal = powerHal;
        this.mPropertyHal = propertyHal;
        this.mDiagnosticHal = diagnosticHal;
        this.mInputHal = null;
        this.mVmsHal = null;
        this.mHalClient = halClient;
        this.mDiagnosticHal = diagnosticHal;
        this.mUserHal = null;
    }

    public void vehicleHalReconnected(IVehicle vehicle) {
        synchronized (this.mLock) {
            this.mHalClient = new HalClient(vehicle, this.mHandlerThread.getLooper(), this);
            SubscribeOptions[] options = (SubscribeOptions[]) this.mSubscribedProperties.values().toArray(new SubscribeOptions[0]);
            try {
                this.mHalClient.subscribe(options);
            } catch (RemoteException e) {
                throw new RuntimeException("Failed to subscribe: " + Arrays.asList(options), e);
            }
        }
    }

    private void fetchAllPropConfigs() {
        synchronized (this.mLock) {
            if (!this.mAllProperties.isEmpty()) {
                Log.i(CarLog.TAG_HAL, "fetchAllPropConfigs already fetched");
                return;
            }
            try {
                ArrayList<VehiclePropConfig> configs = this.mHalClient.getAllPropConfigs();
                if (configs == null || configs.size() == 0) {
                    Log.e(CarLog.TAG_HAL, "getAllPropConfigs returned empty configs");
                    return;
                }
                synchronized (this.mLock) {
                    for (VehiclePropConfig p : configs) {
                        this.mAllProperties.put(Integer.valueOf(p.prop), p);
                    }
                }
            } catch (RemoteException e) {
                throw new RuntimeException("Unable to retrieve vehicle property configuration", e);
            }
        }
    }

    public void init() {
        fetchAllPropConfigs();
        ArrayList<VehiclePropConfig> configsForService = new ArrayList<>(this.mAllServices.size());
        for (int i = 0; i < this.mAllServices.size(); i++) {
            HalServiceBase service = this.mAllServices.get(i);
            int[] supportedProps = service.getAllSupportedProperties();
            configsForService.clear();
            synchronized (this.mLock) {
                if (supportedProps.length == 0) {
                    for (Integer propId : this.mAllProperties.keySet()) {
                        if (service.isSupportedProperty(propId.intValue())) {
                            VehiclePropConfig config = this.mAllProperties.get(propId);
                            this.mPropertyHandlers.append(propId.intValue(), service);
                            configsForService.add(config);
                        }
                    }
                } else {
                    for (int prop : supportedProps) {
                        VehiclePropConfig config2 = this.mAllProperties.get(Integer.valueOf(prop));
                        if (config2 != null) {
                            this.mPropertyHandlers.append(prop, service);
                            configsForService.add(config2);
                        }
                    }
                }
            }
            service.takeProperties(configsForService);
            service.init();
        }
    }

    public void release() {
        for (int i = this.mAllServices.size() - 1; i >= 0; i--) {
            this.mAllServices.get(i).release();
        }
        synchronized (this.mLock) {
            Iterator<Integer> it = this.mSubscribedProperties.keySet().iterator();
            while (it.hasNext()) {
                int p = it.next().intValue();
                try {
                    this.mHalClient.unsubscribe(p);
                } catch (RemoteException e) {
                    Log.w(CarLog.TAG_HAL, "Failed to unsubscribe", e);
                }
            }
            this.mSubscribedProperties.clear();
            this.mAllProperties.clear();
        }
    }

    public DiagnosticHalService getDiagnosticHal() {
        return this.mDiagnosticHal;
    }

    public PowerHalService getPowerHal() {
        return this.mPowerHal;
    }

    public PropertyHalService getPropertyHal() {
        return this.mPropertyHal;
    }

    public InputHalService getInputHal() {
        return this.mInputHal;
    }

    public UserHalService getUserHal() {
        return this.mUserHal;
    }

    public VmsHalService getVmsHal() {
        return this.mVmsHal;
    }

    private void assertServiceOwnerLocked(HalServiceBase service, int property) {
        if (service != this.mPropertyHandlers.get(property)) {
            throw new IllegalArgumentException("Property 0x" + Integer.toHexString(property) + " is not owned by service: " + service);
        }
    }

    public void subscribeProperty(HalServiceBase service, int property) throws IllegalArgumentException {
        subscribeProperty(service, property, 0.0f, 1);
    }

    public void subscribeProperty(HalServiceBase service, int property, float sampleRateHz) throws IllegalArgumentException {
        subscribeProperty(service, property, sampleRateHz, 1);
    }

    public void subscribeProperty(HalServiceBase service, int property, float samplingRateHz, int flags) throws IllegalArgumentException {
        VehiclePropConfig config;
        synchronized (this.mLock) {
            config = this.mAllProperties.get(Integer.valueOf(property));
        }
        if (config == null) {
            throw new IllegalArgumentException("subscribe error: config is null for property 0x" + Integer.toHexString(property));
        }
        if (isPropertySubscribable(config)) {
            SubscribeOptions opts = new SubscribeOptions();
            opts.propId = property;
            opts.sampleRate = samplingRateHz;
            opts.flags = flags;
            synchronized (this.mLock) {
                assertServiceOwnerLocked(service, property);
                this.mSubscribedProperties.put(Integer.valueOf(property), opts);
            }
            try {
                this.mHalClient.subscribe(opts);
                return;
            } catch (RemoteException e) {
                Log.e(CarLog.TAG_HAL, "Failed to subscribe to property: 0x" + property, e);
                return;
            }
        }
        Log.e(CarLog.TAG_HAL, "Cannot subscribe to property: " + property);
    }

    public void unsubscribeProperty(HalServiceBase service, int property) {
        VehiclePropConfig config;
        synchronized (this.mLock) {
            config = this.mAllProperties.get(Integer.valueOf(property));
        }
        if (config == null) {
            Log.e(CarLog.TAG_HAL, "unsubscribeProperty: property " + property + " does not exist");
            return;
        }
        if (isPropertySubscribable(config)) {
            synchronized (this.mLock) {
                assertServiceOwnerLocked(service, property);
                this.mSubscribedProperties.remove(Integer.valueOf(property));
            }
            try {
                this.mHalClient.unsubscribe(property);
                return;
            } catch (RemoteException e) {
                Log.e(CarLog.TAG_SERVICE, "Failed to unsubscribe from property: 0x" + Integer.toHexString(property), e);
                return;
            }
        }
        Log.e(CarLog.TAG_HAL, "Cannot unsubscribe property: " + property);
    }

    public boolean isPropertySupported(int propertyId) {
        boolean zContainsKey;
        synchronized (this.mLock) {
            zContainsKey = this.mAllProperties.containsKey(Integer.valueOf(propertyId));
        }
        return zContainsKey;
    }

    public VehiclePropValue getIfAvailableOrFail(int propertyId, int numberOfRetries) {
        if (!isPropertySupported(propertyId)) {
            return null;
        }
        for (int i = 0; i < numberOfRetries; i++) {
            try {
                return get(propertyId);
            } catch (ServiceSpecificException e) {
                Log.e(CarLog.TAG_HAL, "Cannot get property:" + propertyId, e);
            }
        }
        throw new IllegalStateException("Cannot get property:" + propertyId + " after " + numberOfRetries + " retries");
    }

    public VehiclePropValue getIfAvailableOrFailForEarlyStage(int propertyId, int numberOfRetries) {
        fetchAllPropConfigs();
        return getIfAvailableOrFail(propertyId, numberOfRetries);
    }

    public VehiclePropValue get(int propertyId) {
        return get(propertyId, -1);
    }

    public VehiclePropValue get(int propertyId, int areaId) {
        VehiclePropValue propValue = new VehiclePropValue();
        propValue.prop = propertyId;
        propValue.areaId = areaId;
        return this.mHalClient.getValue(propValue);
    }

    public <T> T get(Class cls, int i) {
        return (T) get(cls, createPropValue(i, -1));
    }

    public <T> T get(Class cls, int i, int i2) {
        return (T) get(cls, createPropValue(i, i2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T get(Class cls, VehiclePropValue vehiclePropValue) {
        VehiclePropValue value = this.mHalClient.getValue(vehiclePropValue);
        if (cls == Integer.class || cls == Integer.TYPE) {
            return (T) value.value.int32Values.get(0);
        }
        if (cls == Boolean.class || cls == Boolean.TYPE) {
            return (T) Boolean.valueOf(value.value.int32Values.get(0).intValue() == 1);
        }
        if (cls == Float.class || cls == Float.TYPE) {
            return (T) value.value.floatValues.get(0);
        }
        if (cls == Integer[].class) {
            return (T) value.value.int32Values.toArray(new Integer[value.value.int32Values.size()]);
        }
        if (cls == Float[].class) {
            return (T) value.value.floatValues.toArray(new Float[value.value.floatValues.size()]);
        }
        if (cls == int[].class) {
            return (T) CarServiceUtils.toIntArray(value.value.int32Values);
        }
        if (cls == float[].class) {
            return (T) CarServiceUtils.toFloatArray(value.value.floatValues);
        }
        if (cls == byte[].class) {
            return (T) CarServiceUtils.toByteArray(value.value.bytes);
        }
        if (cls == String.class) {
            return (T) value.value.stringValue;
        }
        throw new IllegalArgumentException("Unexpected type: " + cls);
    }

    public VehiclePropValue get(VehiclePropValue requestedPropValue) {
        return this.mHalClient.getValue(requestedPropValue);
    }

    public float getSampleRate(int propId) {
        SubscribeOptions opts = this.mSubscribedProperties.get(Integer.valueOf(propId));
        if (opts == null) {
            return -1.0f;
        }
        return opts.sampleRate;
    }

    protected void set(VehiclePropValue propValue) {
        this.mHalClient.setValue(propValue);
    }

    VehiclePropValueSetter set(int propId) {
        return new VehiclePropValueSetter(this.mHalClient, propId, -1);
    }

    VehiclePropValueSetter set(int propId, int areaId) {
        return new VehiclePropValueSetter(this.mHalClient, propId, areaId);
    }

    static boolean isPropertySubscribable(VehiclePropConfig config) {
        return ((config.access & 1) == 0 || config.changeMode == 0) ? false : true;
    }

    static void dumpProperties(PrintWriter writer, Collection<VehiclePropConfig> configs) {
        for (VehiclePropConfig config : configs) {
            writer.println(String.format("property 0x%x", Integer.valueOf(config.prop)));
        }
    }

    @Override // android.hardware.automotive.vehicle.V2_0.IVehicleCallback
    public void onPropertyEvent(ArrayList<VehiclePropValue> propValues) {
        synchronized (this.mLock) {
            for (VehiclePropValue v : propValues) {
                HalServiceBase service = this.mPropertyHandlers.get(v.prop);
                if (service == null) {
                    Log.e(CarLog.TAG_HAL, "HalService not found for prop: 0x" + Integer.toHexString(v.prop));
                } else {
                    service.getDispatchList().add(v);
                    this.mServicesToDispatch.add(service);
                    VehiclePropertyEventInfo info = this.mEventLog.get(Integer.valueOf(v.prop));
                    if (info == null) {
                        this.mEventLog.put(Integer.valueOf(v.prop), new VehiclePropertyEventInfo(v));
                    } else {
                        info.addNewEvent(v);
                    }
                }
            }
        }
        for (HalServiceBase s : this.mServicesToDispatch) {
            s.onHalEvents(s.getDispatchList());
            s.getDispatchList().clear();
        }
        this.mServicesToDispatch.clear();
    }

    @Override // android.hardware.automotive.vehicle.V2_0.IVehicleCallback
    public void onPropertySet(VehiclePropValue value) {
    }

    @Override // android.hardware.automotive.vehicle.V2_0.IVehicleCallback
    public void onPropertySetError(int errorCode, int propId, int areaId) {
        HalServiceBase service;
        Log.e(CarLog.TAG_HAL, String.format("onPropertySetError, errorCode: %d, prop: 0x%x, area: 0x%x", Integer.valueOf(errorCode), Integer.valueOf(propId), Integer.valueOf(areaId)));
        if (propId != 0 && (service = this.mPropertyHandlers.get(propId)) != null) {
            service.onPropertySetError(propId, areaId, errorCode);
        }
    }

    public void dump(PrintWriter writer) {
        writer.println("**dump HAL services**");
        for (HalServiceBase service : this.mAllServices) {
            service.dump(writer);
        }
        dumpPropertyConfigs(writer, "");
        writer.println(String.format("**All Events, now ns:%d**", Long.valueOf(SystemClock.elapsedRealtimeNanos())));
        for (VehiclePropertyEventInfo info : this.mEventLog.values()) {
            writer.println(String.format("event count:%d, lastEvent:%s", Integer.valueOf(info.eventCount), dumpVehiclePropValue(info.lastEvent)));
        }
        writer.println("**Property handlers**");
        for (int i = 0; i < this.mPropertyHandlers.size(); i++) {
            int propId = this.mPropertyHandlers.keyAt(i);
            HalServiceBase service2 = this.mPropertyHandlers.valueAt(i);
            writer.println(String.format("Prop: 0x%08X, service: %s", Integer.valueOf(propId), service2));
        }
    }

    public void dumpListHals(PrintWriter writer) {
        for (HalServiceBase service : this.mAllServices) {
            writer.println(service.getClass().getName());
        }
    }

    public void dumpSpecificHals(PrintWriter writer, String... halNames) {
        Map<String, HalServiceBase> byName = (Map) this.mAllServices.stream().collect(Collectors.toMap(new Function() { // from class: com.android.car.hal.-$$Lambda$VehicleHal$tjvLOOFtDUtxf5SJYyh1axfVi8U
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((HalServiceBase) obj).getClass().getSimpleName();
            }
        }, new Function() { // from class: com.android.car.hal.-$$Lambda$VehicleHal$34jh6h-U36lq2sbyzCI0oCsWFw0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return VehicleHal.lambda$dumpSpecificHals$1((HalServiceBase) obj);
            }
        }));
        for (String halName : halNames) {
            HalServiceBase service = byName.get(halName);
            if (service == null) {
                writer.printf("No HAL named %s. Valid options are: %s\n", halName, byName.keySet());
            } else {
                service.dump(writer);
            }
        }
    }

    static /* synthetic */ HalServiceBase lambda$dumpSpecificHals$1(HalServiceBase s) {
        return s;
    }

    public void dumpPropertyValueByCommend(PrintWriter writer, String propId, String areaId) {
        if (propId.equals("")) {
            writer.println("**All property values**");
            Iterator<VehiclePropConfig> it = this.mAllProperties.values().iterator();
            while (it.hasNext()) {
                dumpPropertyValueByConfig(writer, it.next());
            }
            return;
        }
        if (areaId.equals("")) {
            VehiclePropConfig config = this.mAllProperties.get(Integer.valueOf(Integer.parseInt(propId, 16)));
            if (config == null) {
                writer.printf("Property 0x%s not supported by HAL\n", propId);
                return;
            } else {
                dumpPropertyValueByConfig(writer, config);
                return;
            }
        }
        int id = Integer.parseInt(propId, 16);
        int area = Integer.parseInt(areaId);
        try {
            VehiclePropValue value = get(id, area);
            writer.println(dumpVehiclePropValue(value));
        } catch (Exception e) {
            writer.println("Can not get property value for propertyId: 0x" + propId + ", areaId: " + area);
        }
    }

    private void dumpPropertyValueByConfig(PrintWriter writer, VehiclePropConfig config) {
        if (config.areaConfigs.isEmpty()) {
            try {
                VehiclePropValue value = get(config.prop);
                writer.println(dumpVehiclePropValue(value));
                return;
            } catch (Exception e) {
                writer.println("Can not get property value for propertyId: 0x" + Integer.toHexString(config.prop) + ", areaId: 0");
                return;
            }
        }
        for (VehicleAreaConfig areaConfig : config.areaConfigs) {
            int area = areaConfig.areaId;
            try {
                VehiclePropValue value2 = get(config.prop, area);
                writer.println(dumpVehiclePropValue(value2));
            } catch (Exception e2) {
                writer.println("Can not get property value for propertyId: 0x" + Integer.toHexString(config.prop) + ", areaId: " + area);
            }
        }
    }

    public void dumpPropertyConfigs(PrintWriter writer, String propId) {
        List<VehiclePropConfig> configList;
        synchronized (this.mLock) {
            configList = new ArrayList<>(this.mAllProperties.values());
        }
        if (propId.equals("")) {
            writer.println("**All properties**");
            Iterator<VehiclePropConfig> it = configList.iterator();
            while (it.hasNext()) {
                writer.println(dumpPropertyConfigsHelp(it.next()));
            }
            return;
        }
        for (VehiclePropConfig config : configList) {
            if (Integer.toHexString(config.prop).equals(propId)) {
                writer.println(dumpPropertyConfigsHelp(config));
                return;
            }
        }
    }

    private static String dumpPropertyConfigsHelp(VehiclePropConfig config) {
        StringBuilder sb = new StringBuilder();
        sb.append("Property:0x");
        sb.append(Integer.toHexString(config.prop));
        sb.append(", Property name:");
        sb.append(VehicleProperty.toString(config.prop));
        sb.append(", access:0x");
        sb.append(Integer.toHexString(config.access));
        sb.append(", changeMode:0x");
        sb.append(Integer.toHexString(config.changeMode));
        sb.append(", config:0x");
        sb.append(Arrays.toString(config.configArray.toArray()));
        sb.append(", fs min:");
        sb.append(config.minSampleRate);
        sb.append(", fs max:");
        StringBuilder builder = sb.append(config.maxSampleRate);
        for (VehicleAreaConfig area : config.areaConfigs) {
            builder.append("\n\tareaId:0x");
            builder.append(Integer.toHexString(area.areaId));
            builder.append(", f min:");
            builder.append(area.minFloatValue);
            builder.append(", f max:");
            builder.append(area.maxFloatValue);
            builder.append(", i min:");
            builder.append(area.minInt32Value);
            builder.append(", i max:");
            builder.append(area.maxInt32Value);
            builder.append(", i64 min:");
            builder.append(area.minInt64Value);
            builder.append(", i64 max:");
            builder.append(area.maxInt64Value);
        }
        return builder.toString();
    }

    public void injectVhalEvent(String str, String str2, String str3, String str4) throws NumberFormatException {
        if (str3 == null || str2 == null || str == null) {
            return;
        }
        int iIntValue = Integer.decode(str).intValue();
        int iIntValue2 = Integer.decode(str2).intValue();
        int iIntValue3 = Integer.decode(str4).intValue();
        VehiclePropValue vehiclePropValueCreatePropValue = createPropValue(iIntValue, iIntValue2);
        int i = 16711680 & iIntValue;
        ArrayList arrayList = new ArrayList(Arrays.asList(str3.split(DATA_DELIMITER)));
        if (i == 2097152) {
            vehiclePropValueCreatePropValue.value.int32Values.add(Integer.valueOf(Boolean.valueOf(str3).booleanValue() ? 1 : 0));
        } else if (i == 4194304 || i == 4259840) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                vehiclePropValueCreatePropValue.value.int32Values.add(Integer.decode((String) it.next()));
            }
        } else if (i == 6291456 || i == 6356992) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                vehiclePropValueCreatePropValue.value.floatValues.add(Float.valueOf(Float.parseFloat((String) it2.next())));
            }
        } else {
            Log.e(CarLog.TAG_HAL, "Property type unsupported:" + i);
            return;
        }
        vehiclePropValueCreatePropValue.timestamp = SystemClock.elapsedRealtimeNanos() + TimeUnit.SECONDS.toNanos(iIntValue3);
        onPropertyEvent(Lists.newArrayList(new VehiclePropValue[]{vehiclePropValueCreatePropValue}));
    }

    public void injectOnPropertySetError(String property, String zone, String errorCode) {
        if (zone == null || property == null || errorCode == null) {
            return;
        }
        int propId = Integer.decode(property).intValue();
        int zoneId = Integer.decode(zone).intValue();
        int errorId = Integer.decode(errorCode).intValue();
        onPropertySetError(errorId, propId, zoneId);
    }

    private static class VehiclePropertyEventInfo {
        private int eventCount;
        private VehiclePropValue lastEvent;

        private VehiclePropertyEventInfo(VehiclePropValue event) {
            this.eventCount = 1;
            this.lastEvent = event;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNewEvent(VehiclePropValue event) {
            this.eventCount++;
            this.lastEvent = event;
        }
    }

    final class VehiclePropValueSetter {
        final WeakReference<HalClient> mClient;
        final VehiclePropValue mPropValue;

        private VehiclePropValueSetter(HalClient client, int propId, int areaId) {
            this.mClient = new WeakReference<>(client);
            VehiclePropValue vehiclePropValue = new VehiclePropValue();
            this.mPropValue = vehiclePropValue;
            vehiclePropValue.prop = propId;
            this.mPropValue.areaId = areaId;
        }

        void to(boolean z) {
            to(z ? 1 : 0);
        }

        void to(int value) {
            this.mPropValue.value.int32Values.add(Integer.valueOf(value));
            submit();
        }

        void to(int[] values) {
            for (int value : values) {
                this.mPropValue.value.int32Values.add(Integer.valueOf(value));
            }
            submit();
        }

        void to(Collection<Integer> values) {
            this.mPropValue.value.int32Values.addAll(values);
            submit();
        }

        void submit() {
            HalClient client = this.mClient.get();
            if (client != null) {
                client.setValue(this.mPropValue);
            }
        }
    }

    private static String dumpVehiclePropValue(VehiclePropValue value) {
        StringBuilder sb = new StringBuilder();
        sb.append("Property:0x");
        sb.append(Integer.toHexString(value.prop));
        sb.append(",status: ");
        sb.append(value.status);
        sb.append(",timestamp:");
        sb.append(value.timestamp);
        sb.append(",zone:0x");
        sb.append(Integer.toHexString(value.areaId));
        sb.append(",floatValues: ");
        sb.append(Arrays.toString(value.value.floatValues.toArray()));
        sb.append(",int32Values: ");
        sb.append(Arrays.toString(value.value.int32Values.toArray()));
        sb.append(",int64Values: ");
        StringBuilder sb2 = sb.append(Arrays.toString(value.value.int64Values.toArray()));
        if (value.value.bytes.size() > 20) {
            Object[] bytes = Arrays.copyOf(value.value.bytes.toArray(), 20);
            sb2.append(",bytes: ");
            sb2.append(Arrays.toString(bytes));
        } else {
            sb2.append(",bytes: ");
            sb2.append(Arrays.toString(value.value.bytes.toArray()));
        }
        sb2.append(",string: ");
        sb2.append(value.value.stringValue);
        return sb2.toString();
    }

    private static VehiclePropValue createPropValue(int propId, int areaId) {
        VehiclePropValue propValue = new VehiclePropValue();
        propValue.prop = propId;
        propValue.areaId = areaId;
        return propValue;
    }
}
