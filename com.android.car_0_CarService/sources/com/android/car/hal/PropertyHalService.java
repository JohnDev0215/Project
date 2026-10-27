package com.android.car.hal;

import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyEvent;
import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.CarServiceUtils;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
public class PropertyHalService extends HalServiceBase {
    private static final Map<Integer, Integer> PROPERTY_ID_HAL_TO_MANAGER;
    private static final Map<Integer, Integer> PROPERTY_ID_MANAGER_TO_HAL;
    private static final String TAG = "PropertyHalService";
    private PropertyHalListener mListener;
    private final VehicleHal mVehicleHal;
    private final boolean mDbg = true;
    private final LinkedList<CarPropertyEvent> mEventsToDispatch = new LinkedList<>();
    private final Map<Integer, CarPropertyConfig<?>> mMgrPropIdToCarPropConfig = new HashMap();
    private final SparseArray<VehiclePropConfig> mHalPropIdToVehiclePropConfig = new SparseArray<>();
    private final Object mLock = new Object();
    private final PropertyHalServiceIds mPropIds = new PropertyHalServiceIds();
    private Set<Integer> mSubscribedHalPropIds = new HashSet();

    public interface PropertyHalListener {
        void onPropertyChange(List<CarPropertyEvent> list);

        void onPropertySetError(int i, int i2, int i3);
    }

    static {
        Integer numValueOf = Integer.valueOf(VehicleProperty.VEHICLE_SPEED_DISPLAY_UNITS);
        PROPERTY_ID_HAL_TO_MANAGER = Map.of(numValueOf, 289408516);
        PROPERTY_ID_MANAGER_TO_HAL = Map.of(289408516, numValueOf);
    }

    private int managerToHalPropId(int mgrPropId) {
        return PROPERTY_ID_MANAGER_TO_HAL.getOrDefault(Integer.valueOf(mgrPropId), Integer.valueOf(mgrPropId)).intValue();
    }

    private int halToManagerPropId(int halPropId) {
        return PROPERTY_ID_HAL_TO_MANAGER.getOrDefault(Integer.valueOf(halPropId), Integer.valueOf(halPropId)).intValue();
    }

    private boolean isPropertySupportedInVehicle(int halPropId) {
        return this.mHalPropIdToVehiclePropConfig.contains(halPropId);
    }

    public PropertyHalService(VehicleHal vehicleHal) {
        this.mVehicleHal = vehicleHal;
        Log.d(TAG, "started PropertyHalService");
    }

    public void setListener(PropertyHalListener listener) {
        synchronized (this.mLock) {
            this.mListener = listener;
        }
    }

    public Map<Integer, CarPropertyConfig<?>> getPropertyList() {
        Log.d(TAG, "getPropertyList");
        synchronized (this.mLock) {
            if (this.mMgrPropIdToCarPropConfig.size() == 0) {
                for (int i = 0; i < this.mHalPropIdToVehiclePropConfig.size(); i++) {
                    VehiclePropConfig p = this.mHalPropIdToVehiclePropConfig.valueAt(i);
                    int mgrPropId = halToManagerPropId(p.prop);
                    this.mMgrPropIdToCarPropConfig.put(Integer.valueOf(mgrPropId), CarPropertyUtils.toCarPropertyConfig(p, mgrPropId));
                }
            }
        }
        return this.mMgrPropIdToCarPropConfig;
    }

    public CarPropertyValue getProperty(int mgrPropId, int areaId) {
        VehiclePropConfig propConfig;
        int halPropId = managerToHalPropId(mgrPropId);
        if (!isPropertySupportedInVehicle(halPropId)) {
            throw new IllegalArgumentException("Invalid property Id : 0x" + Integer.toHexString(mgrPropId));
        }
        VehiclePropValue value = this.mVehicleHal.get(halPropId, areaId);
        if (isMixedTypeProperty(halPropId)) {
            synchronized (this.mLock) {
                propConfig = this.mHalPropIdToVehiclePropConfig.get(halPropId);
            }
            boolean containBooleanType = propConfig.configArray.get(1).intValue() == 1;
            if (value == null) {
                return null;
            }
            return CarPropertyUtils.toMixedCarPropertyValue(value, mgrPropId, containBooleanType);
        }
        if (value == null) {
            return null;
        }
        return CarPropertyUtils.toCarPropertyValue(value, mgrPropId);
    }

    public float getSampleRate(int mgrPropId) {
        int halPropId = managerToHalPropId(mgrPropId);
        if (!isPropertySupportedInVehicle(halPropId)) {
            throw new IllegalArgumentException("Invalid property Id : 0x" + Integer.toHexString(mgrPropId));
        }
        return this.mVehicleHal.getSampleRate(halPropId);
    }

    public String getReadPermission(int mgrPropId) {
        int halPropId = managerToHalPropId(mgrPropId);
        return this.mPropIds.getReadPermission(halPropId);
    }

    public String getWritePermission(int mgrPropId) {
        int halPropId = managerToHalPropId(mgrPropId);
        return this.mPropIds.getWritePermission(halPropId);
    }

    public boolean isDisplayUnitsProperty(int mgrPropId) {
        int halPropId = managerToHalPropId(mgrPropId);
        return this.mPropIds.isPropertyToChangeUnits(halPropId);
    }

    public void setProperty(CarPropertyValue prop) {
        VehiclePropValue halProp;
        VehiclePropConfig propConfig;
        int halPropId = managerToHalPropId(prop.getPropertyId());
        if (!isPropertySupportedInVehicle(halPropId)) {
            throw new IllegalArgumentException("Invalid property Id : 0x" + Integer.toHexString(prop.getPropertyId()));
        }
        if (isMixedTypeProperty(halPropId)) {
            synchronized (this.mLock) {
                propConfig = this.mHalPropIdToVehiclePropConfig.get(prop.getPropertyId());
            }
            int[] configArray = propConfig.configArray.stream().mapToInt(new ToIntFunction() { // from class: com.android.car.hal.-$$Lambda$PropertyHalService$edZOC6Ekm442rMagfsAYSCs7hL0
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((Integer) obj).intValue();
                }
            }).toArray();
            halProp = CarPropertyUtils.toMixedVehiclePropValue(prop, halPropId, configArray);
        } else {
            halProp = CarPropertyUtils.toVehiclePropValue(prop, halPropId);
        }
        this.mVehicleHal.set(halProp);
    }

    public void subscribeProperty(int mgrPropId, float rate) {
        Log.d(TAG, "subscribeProperty propId=0x" + Integer.toHexString(mgrPropId) + ", rate=" + rate);
        int halPropId = managerToHalPropId(mgrPropId);
        if (!isPropertySupportedInVehicle(halPropId)) {
            throw new IllegalArgumentException("Invalid property Id : 0x" + Integer.toHexString(mgrPropId));
        }
        synchronized (this.mLock) {
            VehiclePropConfig cfg = this.mHalPropIdToVehiclePropConfig.get(halPropId);
            if (rate > cfg.maxSampleRate) {
                rate = cfg.maxSampleRate;
            } else if (rate < cfg.minSampleRate) {
                rate = cfg.minSampleRate;
            }
            this.mSubscribedHalPropIds.add(Integer.valueOf(halPropId));
        }
        this.mVehicleHal.subscribeProperty(this, halPropId, rate);
    }

    public void unsubscribeProperty(int mgrPropId) {
        Log.d(TAG, "unsubscribeProperty propId=0x" + Integer.toHexString(mgrPropId));
        int halPropId = managerToHalPropId(mgrPropId);
        if (!isPropertySupportedInVehicle(halPropId)) {
            throw new IllegalArgumentException("Invalid property Id : 0x" + Integer.toHexString(mgrPropId));
        }
        synchronized (this.mLock) {
            if (this.mSubscribedHalPropIds.contains(Integer.valueOf(halPropId))) {
                this.mSubscribedHalPropIds.remove(Integer.valueOf(halPropId));
                this.mVehicleHal.unsubscribeProperty(this, halPropId);
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void init() {
        Log.d(TAG, "init()");
    }

    @Override // com.android.car.hal.HalServiceBase
    public void release() {
        Log.d(TAG, "release()");
        synchronized (this.mLock) {
            for (Integer halProp : this.mSubscribedHalPropIds) {
                this.mVehicleHal.unsubscribeProperty(this, halProp.intValue());
            }
            this.mSubscribedHalPropIds.clear();
            this.mHalPropIdToVehiclePropConfig.clear();
            this.mMgrPropIdToCarPropConfig.clear();
            this.mListener = null;
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public boolean isSupportedProperty(int propId) {
        return this.mPropIds.isSupportedProperty(propId);
    }

    @Override // com.android.car.hal.HalServiceBase
    public int[] getAllSupportedProperties() {
        return CarServiceUtils.EMPTY_INT_ARRAY;
    }

    @Override // com.android.car.hal.HalServiceBase
    public void takeProperties(Collection<VehiclePropConfig> allProperties) {
        VehiclePropConfig customizePermission;
        for (VehiclePropConfig p : allProperties) {
            if (this.mPropIds.isSupportedProperty(p.prop)) {
                synchronized (this.mLock) {
                    this.mHalPropIdToVehiclePropConfig.put(p.prop, p);
                }
                Log.d(TAG, "takeSupportedProperties: " + Integer.toHexString(p.prop));
            }
        }
        Log.d(TAG, "takeSupportedProperties() took " + allProperties.size() + " properties");
        synchronized (this.mLock) {
            customizePermission = this.mHalPropIdToVehiclePropConfig.get(VehicleProperty.SUPPORT_CUSTOMIZE_VENDOR_PERMISSION);
        }
        if (customizePermission != null) {
            this.mPropIds.customizeVendorPermission(customizePermission.configArray);
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void onHalEvents(List<VehiclePropValue> values) {
        PropertyHalListener listener;
        CarPropertyValue<?> propVal;
        VehiclePropConfig propConfig;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            for (VehiclePropValue v : values) {
                if (v != null) {
                    if (!isPropertySupportedInVehicle(v.prop)) {
                        Log.e(TAG, "Property is not supported: 0x" + Integer.toHexString(v.prop));
                    } else if (Build.IS_DEBUGGABLE && !this.mPropIds.checkPayload(v)) {
                        Log.e(TAG, "Drop event for property: " + v + " because it is failed in payload checking.");
                    } else {
                        int mgrPropId = halToManagerPropId(v.prop);
                        if (isMixedTypeProperty(v.prop)) {
                            synchronized (this.mLock) {
                                propConfig = this.mHalPropIdToVehiclePropConfig.get(v.prop);
                            }
                            boolean containBooleanType = propConfig.configArray.get(1).intValue() == 1;
                            propVal = CarPropertyUtils.toMixedCarPropertyValue(v, mgrPropId, containBooleanType);
                        } else {
                            propVal = CarPropertyUtils.toCarPropertyValue(v, mgrPropId);
                        }
                        CarPropertyEvent event = new CarPropertyEvent(0, propVal);
                        this.mEventsToDispatch.add(event);
                    }
                }
            }
            listener.onPropertyChange(this.mEventsToDispatch);
            this.mEventsToDispatch.clear();
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void onPropertySetError(int halPropId, int area, int errorCode) {
        PropertyHalListener listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            int mgrPropId = halToManagerPropId(halPropId);
            listener.onPropertySetError(mgrPropId, area, errorCode);
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void dump(PrintWriter writer) {
        writer.println(TAG);
        writer.println("  Properties available:");
        synchronized (this.mLock) {
            for (int i = 0; i < this.mHalPropIdToVehiclePropConfig.size(); i++) {
                VehiclePropConfig p = this.mHalPropIdToVehiclePropConfig.valueAt(i);
                writer.println("    " + p);
            }
        }
    }

    private static boolean isMixedTypeProperty(int halPropId) {
        return (16711680 & halPropId) == 14680064;
    }
}
