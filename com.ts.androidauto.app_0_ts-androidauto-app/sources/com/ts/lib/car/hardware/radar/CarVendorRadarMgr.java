package com.ts.lib.car.hardware.radar;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.radar.CarVendorRadarManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CarVendorRadarMgr extends CarVendorManagerBase implements CarVendorRadarManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static RadarEventChangeValue sRadarEventChangeValue = new RadarEventChangeValue();
    private final LinkedList<OnCarRadarChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtRadarIds = {557861129, 557861130, 557861131, 557861132, 557861133, 557861134, 557861135, 557861136, 557861120, 557861121, 557861122, 557861123, 557861124, 557861125, 557861126, 557861127, 557861128, 557861137, 557861140};

    public CarVendorRadarMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorRadarManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorRadarManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorRadarManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorRadarManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarObstacleDistanceFL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861129, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarObstacleDistanceFL property: 0x");
            sb.append(tenToHex(557861129));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarObstacleDistanceFML() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861130, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarObstacleDistanceFML property: 0x");
            sb.append(tenToHex(557861130));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarObstacleDistanceFMR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861131, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarObstacleDistanceFMR property: 0x");
            sb.append(tenToHex(557861131));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarObstacleDistanceFR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861132, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarObstacleDistanceFR property: 0x");
            sb.append(tenToHex(557861132));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarObstacleDistanceRL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861133, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarObstacleDistanceRL property: 0x");
            sb.append(tenToHex(557861133));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarObstacleDistanceRML() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861134, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarObstacleDistanceRML property: 0x");
            sb.append(tenToHex(557861134));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarObstacleDistanceRMR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861135, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarObstacleDistanceRMR property: 0x");
            sb.append(tenToHex(557861135));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarObstacleDistanceRR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861136, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarObstacleDistanceRR property: 0x");
            sb.append(tenToHex(557861136));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateFL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861120, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateFL property: 0x");
            sb.append(tenToHex(557861120));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateFML() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861121, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateFML property: 0x");
            sb.append(tenToHex(557861121));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateFMR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861122, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateFMR property: 0x");
            sb.append(tenToHex(557861122));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateFR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861123, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateFR property: 0x");
            sb.append(tenToHex(557861123));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateMR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861124, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateMR property: 0x");
            sb.append(tenToHex(557861124));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateRL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861125, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateRL property: 0x");
            sb.append(tenToHex(557861125));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateRML() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861126, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateRML property: 0x");
            sb.append(tenToHex(557861126));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateRMR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861127, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateRMR property: 0x");
            sb.append(tenToHex(557861127));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getRadarProbeStateRR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861128, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRadarProbeStateRR property: 0x");
            sb.append(tenToHex(557861128));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getReverseRadarSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861137, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReverseRadarSwitchStateR property: 0x");
            sb.append(tenToHex(557861137));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public void setReverseRadarSwitchStateS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorRadarManager.TAG, "setReverseRadarSwitchStateSOne property: 0x" + tenToHex(CarVendorRadarManager.ID_REVERSE_RADAR_SWITCH_STATE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorRadarManager.ID_REVERSE_RADAR_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public int getNewReverseRadarSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861140, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getNewReverseRadarSwitchStateR property: 0x");
            sb.append(tenToHex(557861140));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRadarManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public void addCarRadarEventListener(CarVendorRadarManager.OnCarRadarEventListener listener) throws CarNotConnectedException {
        OnCarRadarChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarRadarChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorRadarManager.TAG, "addCarRadarEventListener propId size: " + this.mExtRadarIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtRadarIds) {
                int propId = num.intValue();
                Log.debug(CarVendorRadarManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.radar.CarVendorRadarManager
    public void removeCarRadarEventListener(CarVendorRadarManager.OnCarRadarEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarRadarChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorRadarManager.TAG, "removeCarRadarEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RadarEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sRadarEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorRadarManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557861120:
                sRadarEventChangeValue.setRadarProbeStateFL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861121:
                sRadarEventChangeValue.setRadarProbeStateFML(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861122:
                sRadarEventChangeValue.setRadarProbeStateFMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861123:
                sRadarEventChangeValue.setRadarProbeStateFR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861124:
                sRadarEventChangeValue.setRadarProbeStateMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861125:
                sRadarEventChangeValue.setRadarProbeStateRL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861126:
                sRadarEventChangeValue.setRadarProbeStateRML(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861127:
                sRadarEventChangeValue.setRadarProbeStateRMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861128:
                sRadarEventChangeValue.setRadarProbeStateRR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861129:
                sRadarEventChangeValue.setRadarObstacleDistanceFL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861130:
                sRadarEventChangeValue.setRadarObstacleDistanceFML(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861131:
                sRadarEventChangeValue.setRadarObstacleDistanceFMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861132:
                sRadarEventChangeValue.setRadarObstacleDistanceFR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861133:
                sRadarEventChangeValue.setRadarObstacleDistanceRL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861134:
                sRadarEventChangeValue.setRadarObstacleDistanceRML(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861135:
                sRadarEventChangeValue.setRadarObstacleDistanceRMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861136:
                sRadarEventChangeValue.setRadarObstacleDistanceRR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861137:
                sRadarEventChangeValue.setReverseRadarSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861138:
            case 557861139:
            default:
                Log.debug(CarVendorRadarManager.TAG, "break other properties ,except radar");
                break;
            case 557861140:
                sRadarEventChangeValue.setNewReverseRadarSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
        }
        Log.debug(CarVendorRadarManager.TAG, "convertCarVendorPropertyValue " + sRadarEventChangeValue.toString(), DEBUG_LOGGER);
        return sRadarEventChangeValue;
    }

    private OnCarRadarChangeEventListenerProxy findListenerLocked(CarVendorRadarManager.OnCarRadarEventListener listener) {
        for (OnCarRadarChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarRadarChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorRadarManager.OnCarRadarEventListener listener;

        public OnCarRadarChangeEventListenerProxy(CarVendorRadarManager.OnCarRadarEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorRadarMgr.this.traceCallbackPrepare();
                RadarEventChangeValue newValue = CarVendorRadarMgr.convertCarVendorProperty(value);
                CarVendorRadarMgr.this.traceCallbackBefore();
                this.listener.onRadarEventChange(newValue);
                CarVendorRadarMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorRadarManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorRadarManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onRadarErrorEvent(propertyId, zone);
        }
    }
}
