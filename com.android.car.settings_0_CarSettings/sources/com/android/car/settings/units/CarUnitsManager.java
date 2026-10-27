package com.android.car.settings.units;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.property.CarPropertyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.ArraySet;
import com.android.car.settings.common.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CarUnitsManager {
    private static final int AREA_ID = 0;
    private static final Logger LOG = new Logger((Class<?>) CarUnitsManager.class);
    private Car mCar;
    private CarPropertyManager mCarPropertyManager;
    private OnCarServiceListener mCarServiceListener;
    private Context mContext;
    private final ServiceConnection mServiceConnection;

    public interface OnCarServiceListener {
        void handleServiceConnected(CarPropertyManager carPropertyManager);

        void handleServiceDisconnected();
    }

    public CarUnitsManager(Context context) {
        ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.android.car.settings.units.CarUnitsManager.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    CarUnitsManager.this.mCarPropertyManager = (CarPropertyManager) CarUnitsManager.this.mCar.getCarManager("property");
                    CarUnitsManager.this.mCarServiceListener.handleServiceConnected(CarUnitsManager.this.mCarPropertyManager);
                } catch (CarNotConnectedException e) {
                    CarUnitsManager.LOG.e("Car is not connected!", e);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                CarUnitsManager.this.mCarServiceListener.handleServiceDisconnected();
            }
        };
        this.mServiceConnection = serviceConnection;
        this.mContext = context;
        this.mCar = Car.createCar(context, serviceConnection);
    }

    public void registerCarServiceListener(OnCarServiceListener listener) {
        this.mCarServiceListener = listener;
    }

    public void unregisterCarServiceListener() {
        this.mCarServiceListener = null;
    }

    protected void connect() {
        this.mCar.connect();
    }

    protected void disconnect() {
        this.mCar.disconnect();
    }

    protected boolean isPropertyAvailable(int propertyId) {
        Integer intProperty = null;
        try {
            intProperty = Integer.valueOf(this.mCarPropertyManager.getIntProperty(propertyId, 0));
        } catch (CarNotConnectedException e) {
            LOG.e("Property is unavailable because Car is not connected.");
        }
        return (intProperty == null || intProperty.intValue() == 0) ? false : true;
    }

    protected Unit[] getUnitsSupportedByProperty(int propertyId) {
        ArraySet<Integer> propertyIdSet = new ArraySet<>();
        propertyIdSet.add(Integer.valueOf(propertyId));
        List<CarPropertyConfig> configs = this.mCarPropertyManager.getPropertyList(propertyIdSet);
        new ArrayList();
        List<Unit> units = new ArrayList<>();
        if (configs == null || configs.size() < 1 || configs.get(0) == null) {
            return null;
        }
        List<Integer> availableUnitsId = configs.get(0).getConfigArray();
        Unit[] result = new Unit[availableUnitsId.size()];
        Iterator<Integer> it = availableUnitsId.iterator();
        while (it.hasNext()) {
            int unitId = it.next().intValue();
            if (UnitsMap.MAP.get(Integer.valueOf(unitId)) != null) {
                Unit unit = UnitsMap.MAP.get(Integer.valueOf(unitId));
                units.add(unit);
            }
        }
        for (int i = 0; i < result.length; i++) {
            int unitId2 = availableUnitsId.get(i).intValue();
            if (UnitsMap.MAP.get(Integer.valueOf(unitId2)) != null) {
                Unit unit2 = UnitsMap.MAP.get(Integer.valueOf(unitId2));
                result[i] = unit2;
            }
        }
        return result;
    }

    protected Unit getUnitUsedByProperty(int propertyId) {
        try {
            int unitId = this.mCarPropertyManager.getIntProperty(propertyId, 0);
            if (UnitsMap.MAP.get(Integer.valueOf(unitId)) == null) {
                return null;
            }
            return UnitsMap.MAP.get(Integer.valueOf(unitId));
        } catch (CarNotConnectedException e) {
            LOG.e("CarPropertyManager cannot get property because Car is not connected.");
            return null;
        }
    }

    protected void setUnitUsedByProperty(int propertyId, int unitId) {
        try {
            this.mCarPropertyManager.setIntProperty(propertyId, 0, unitId);
        } catch (CarNotConnectedException e) {
            LOG.e("CarPropertyManager cannot set property because Car is not connected.");
        }
    }

    protected boolean isDistanceOverVolume() {
        try {
            return this.mCarPropertyManager.getBooleanProperty(287311364, 0);
        } catch (CarNotConnectedException e) {
            return true;
        }
    }
}
