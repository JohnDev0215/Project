package com.android.car.hal;

import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class HalServiceBase {
    private static final String MY_TAG = HalServiceBase.class.getSimpleName();
    static final int NOT_SUPPORTED_PROPERTY = -1;
    private final ArrayList<VehiclePropValue> mDispatchList = new ArrayList<>(1);

    public abstract void dump(PrintWriter printWriter);

    public abstract int[] getAllSupportedProperties();

    public abstract void init();

    public abstract void onHalEvents(List<VehiclePropValue> list);

    public abstract void release();

    public abstract void takeProperties(Collection<VehiclePropConfig> collection);

    public List<VehiclePropValue> getDispatchList() {
        return this.mDispatchList;
    }

    public boolean isSupportedProperty(int propId) {
        for (int supported : getAllSupportedProperties()) {
            if (propId == supported) {
                return true;
            }
        }
        return false;
    }

    public void onPropertySetError(int property, int area, int errorCode) {
        Log.d(MY_TAG, getClass().getSimpleName() + ".onPropertySetError(): property=" + property + ", area=" + area + " , errorCode = " + errorCode);
    }

    static class ManagerToHalPropIdMap {
        private final BidirectionalSparseIntArray mMap;

        static ManagerToHalPropIdMap create(int... mgrToHalPropIds) {
            return new ManagerToHalPropIdMap(BidirectionalSparseIntArray.create(mgrToHalPropIds));
        }

        private ManagerToHalPropIdMap(BidirectionalSparseIntArray map) {
            this.mMap = map;
        }

        int getHalPropId(int managerPropId) {
            return this.mMap.getValue(managerPropId, -1);
        }

        int getManagerPropId(int halPropId) {
            return this.mMap.getKey(halPropId, -1);
        }
    }
}
