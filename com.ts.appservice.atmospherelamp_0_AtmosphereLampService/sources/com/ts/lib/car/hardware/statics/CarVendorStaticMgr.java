package com.ts.lib.car.hardware.statics;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.statics.CarVendorStaticManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class CarVendorStaticMgr extends CarVendorManagerBase implements CarVendorStaticManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static StaticEventChangeValue sStaticEventChangeValue = new StaticEventChangeValue();
    private final LinkedList<OnCarStaticChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtStaticIds = {557859855, 559956992, 557859841, 559956994, 557859857, 557859843, 557859844, 557859856, 557859850, 559956997, 559957000, 559956998, 559956999, 559957001, 559957010, 557875215, Integer.valueOf(CarVendorStaticManager.ID_BATTERY_PACK_ENERGY_R), 559957024, 559957025, 557859877, 557859878, 559967007, 557869856, 559967010, 557875217, 559972404, 557875259, 557869857, 557875222, 557875258, 557875219};

    public CarVendorStaticMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorStaticManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorStaticManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorStaticManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorStaticManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setResetTravelDateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setResetTravelDateS value = " + value + " property: 0x" + tenToHex(CarVendorStaticManager.ID_RESET_TRAVEL_DATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorStaticManager.ID_RESET_TRAVEL_DATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getCurrentTripElecR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559967007, 0);
            Log.debug(CarVendorStaticManager.TAG, "getCurrentTripElecR value = " + value + " property: 0x" + tenToHex(559967007));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getCurrentTripElecUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557869856, 0);
            Log.debug(CarVendorStaticManager.TAG, "getCurrentTripElecUnitR value = " + value + " property: 0x" + tenToHex(557869856));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getCurrentTripFuelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559967010, 0);
            Log.debug(CarVendorStaticManager.TAG, "getCurrentTripFuelR value = " + value + " property: 0x" + tenToHex(559967010));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getCurrentTripFuelUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875217, 0);
            Log.debug(CarVendorStaticManager.TAG, "getCurrentTripFuelUnitR value = " + value + " property: 0x" + tenToHex(557875217));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getCurrentTripMileageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559972404, 0);
            Log.debug(CarVendorStaticManager.TAG, "getCurrentTripMileageR value = " + value + " property: 0x" + tenToHex(559972404));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getCurrentTripMileageUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875259, 0);
            Log.debug(CarVendorStaticManager.TAG, "getCurrentTripMileageUnitR value = " + value + " property: 0x" + tenToHex(557875259));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getCurrentTripTimeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557869857, 0);
            Log.debug(CarVendorStaticManager.TAG, "getCurrentTripTimeR value = " + value + " property: 0x" + tenToHex(557869857));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getElecConsUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875222, 0);
            Log.debug(CarVendorStaticManager.TAG, "getElecConsUnitR value = " + value + " property: 0x" + tenToHex(557875222));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getElecUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875258, 0);
            Log.debug(CarVendorStaticManager.TAG, "getElecUnitR value = " + value + " property: 0x" + tenToHex(557875258));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getSpeedUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875219, 0);
            Log.debug(CarVendorStaticManager.TAG, "getSpeedUnitR value = " + value + " property: 0x" + tenToHex(557875219));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getDrivingRangeAllR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859877, 0);
            Log.debug(CarVendorStaticManager.TAG, "getDrivingRangeAllR value = " + value + " property: 0x" + tenToHex(557859877));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getDrivingRangeUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859878, 0);
            Log.debug(CarVendorStaticManager.TAG, "getDrivingRangeUnitR value = " + value + " property: 0x" + tenToHex(557859878));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getEvRemainingBatteryPowerR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559957025, 0);
            Log.debug(CarVendorStaticManager.TAG, "getEvRemainingBatteryPowerR value = " + value + " property: 0x" + tenToHex(559957025), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getRemainingBatteryPowerR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559957024, 0);
            Log.debug(CarVendorStaticManager.TAG, "getRemainingBatteryPowerR value = " + value + " property: 0x" + tenToHex(559957024), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getElecDrivingRangeByStandardR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859841, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElecDrivingRangeByStandardR property: 0x");
            sb.append(tenToHex(557859841));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getMileageNumberOneR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559965186, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMileageNumberOneR property: 0x");
            sb.append(tenToHex(559965186));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getMileageNumberTwoR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559965187, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMileageNumberTwoR property: 0x");
            sb.append(tenToHex(559965187));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getDrivingTimeValueTwoR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559957010, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDrivingTimeValueTwoR property: 0x");
            sb.append(tenToHex(559957010));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getElecDrivingRangeValueTwoR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875215, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElecDrivingRangeValueTwoR property: 0x");
            sb.append(tenToHex(557875215));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setAverageSpeedS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setAverageSpeedS property: 0x" + tenToHex(557859854) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557859854, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void resetStatisticDateS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "resetStatisticDateS property: 0x" + tenToHex(CarVendorStaticManager.ID_RESET_STATISTIC_DATE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorStaticManager.ID_RESET_STATISTIC_DATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getAverageSpeedValueS() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859855, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAverageSpeedValueS property: 0x");
            sb.append(tenToHex(557859855));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getDrivingTimeValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559956992, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDrivingTimeValueR property: 0x");
            sb.append(tenToHex(559956992));
            sb.append(" class: Double value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setDrivingTimeValueS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setDrivingTimeValueS property: 0x" + tenToHex(557859853) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557859853, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getEVMileageValueS() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859857, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEVMileageValueS property: 0x");
            sb.append(tenToHex(557859857));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getElecDrivingRangeValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859841, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElecDrivingRangeValueR property: 0x");
            sb.append(tenToHex(557859841));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getElecPercentageValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559956994, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElecPercentageValueR property: 0x");
            sb.append(tenToHex(559956994));
            sb.append(" class: Double value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setExtChargeCapacityS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setExtChargeCapacityS property: 0x" + tenToHex(557859852) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557859852, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getFuelDrivingRangeValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859843, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFuelDrivingRangeValueR property: 0x");
            sb.append(tenToHex(557859843));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getFuelPercentageValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859844, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFuelPercentageValueR property: 0x");
            sb.append(tenToHex(557859844));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getHEVMileageValueS() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859856, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHEVMileageValueS property: 0x");
            sb.append(tenToHex(557859856));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public int getKeyBatteryLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859850, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getKeyBatteryLevelR property: 0x");
            sb.append(tenToHex(557859850));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getTotalElecConPHMValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559956997, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTotalElecConPHMValueR property: 0x");
            sb.append(tenToHex(559956997));
            sb.append(" class: Double value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getTotalElecConValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559957000, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTotalElecConValueR property: 0x");
            sb.append(tenToHex(559957000));
            sb.append(" class: Double value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getTotalFuelConPHMValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559956998, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTotalFuelConPHMValueR property: 0x");
            sb.append(tenToHex(559956998));
            sb.append(" class: Double value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getTotalFuelConValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559956999, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTotalFuelConValueR property: 0x");
            sb.append(tenToHex(559956999));
            sb.append(" class: Double value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setTotalFuelConValueS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setTotalFuelConValueS property: 0x" + tenToHex(557859851) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557859851, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getTotalMileageValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559957001, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTotalMileageValueR property: 0x");
            sb.append(tenToHex(559957001));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public float getBatteryPackEnergyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorStaticManager.ID_BATTERY_PACK_ENERGY_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBatteryPackEnergyR property: 0x");
            sb.append(tenToHex(CarVendorStaticManager.ID_BATTERY_PACK_ENERGY_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorStaticManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setSendIsaData2AdasS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setSendIsaData2AdasS property: 0x" + tenToHex(CarVendorStaticManager.ID_SEND_ISA_DATA_TO_ADAS_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorStaticManager.ID_SEND_ISA_DATA_TO_ADAS_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setRoadAccidentS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setRoadAccidentS value = " + value + " property: 0x" + tenToHex(CarVendorStaticManager.ID_ROAD_ACCIDENT_S), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorStaticManager.ID_ROAD_ACCIDENT_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setRoadCongestedS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setRoadCongestedS value = " + value + " property: 0x" + tenToHex(CarVendorStaticManager.ID_ROAD_CONGESTED_S), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorStaticManager.ID_ROAD_CONGESTED_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setRoadConstructionS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setRoadConstructionS value = " + value + " property: 0x" + tenToHex(CarVendorStaticManager.ID_ROAD_CONSTRUCTION_S), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorStaticManager.ID_ROAD_CONSTRUCTION_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setRoadImpassableS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setRoadImpassableS value = " + value + " property: 0x" + tenToHex(557859877), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557859877, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void setTrafficSignTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorStaticManager.TAG, "setTrafficSignTypeS value = " + value + " property: 0x" + tenToHex(557859878), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557859878, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void addCarStaticEventListener(CarVendorStaticManager.OnCarStaticEventListener listener) throws CarNotConnectedException {
        OnCarStaticChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarStaticChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorStaticManager.TAG, "addCarStaticEventListener propId size: " + this.mExtStaticIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtStaticIds) {
                int propId = num.intValue();
                Log.debug(CarVendorStaticManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.statics.CarVendorStaticManager
    public void removeCarStaticEventListener(CarVendorStaticManager.OnCarStaticEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarStaticChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorStaticManager.TAG, "removeCarStaticEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static StaticEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sStaticEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorStaticManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557859841:
                sStaticEventChangeValue.setElecDrivingRangeByStandardR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859843:
                sStaticEventChangeValue.setFuelDrivingRangeValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859844:
                sStaticEventChangeValue.setFuelPercentageValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859850:
                sStaticEventChangeValue.setKeyBatteryLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859851:
                sStaticEventChangeValue.setTotalFuelConValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859852:
                sStaticEventChangeValue.setExtChargeCapacityS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859853:
                sStaticEventChangeValue.setDrivingTimeValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859854:
                sStaticEventChangeValue.setAverageSpeedS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859855:
                sStaticEventChangeValue.setAverageSpeedValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859856:
                sStaticEventChangeValue.setHEVMileageValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859857:
                sStaticEventChangeValue.setEVMileageValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859877:
                sStaticEventChangeValue.setDrivingRangeAllR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859878:
                sStaticEventChangeValue.setDrivingRangeUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557869856:
                sStaticEventChangeValue.setCurrentTripElecUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557869857:
                sStaticEventChangeValue.setCurrentTripTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557875215:
                sStaticEventChangeValue.setElecDrivingRangeValueTwoR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557875217:
                sStaticEventChangeValue.setCurrentTripFuelUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557875219:
                sStaticEventChangeValue.setSpeedUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557875222:
                sStaticEventChangeValue.setElecConsUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557875258:
                sStaticEventChangeValue.setElecUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557875259:
                sStaticEventChangeValue.setCurrentTripMileageUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 559956992:
                sStaticEventChangeValue.setDrivingTimeValueR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559956994:
                sStaticEventChangeValue.setElecPercentageValueR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559956997:
                sStaticEventChangeValue.setTotalElecConPHMValueR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559956998:
                sStaticEventChangeValue.setTotalFuelConPHMValueR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559956999:
                sStaticEventChangeValue.setTotalFuelConValueR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559957000:
                sStaticEventChangeValue.setTotalElecConValueR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559957001:
                sStaticEventChangeValue.setTotalMileageValueR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559957010:
                sStaticEventChangeValue.setDrivingTimeValueTwoR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559957024:
                sStaticEventChangeValue.setRemainingBatteryPowerR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559957025:
                sStaticEventChangeValue.setEvRemainingBatteryPowerR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559965186:
                sStaticEventChangeValue.setMileageNumberOneR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559965187:
                sStaticEventChangeValue.setMileageNumberTwoR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559967007:
                sStaticEventChangeValue.setCurrentTripElecR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559967010:
                sStaticEventChangeValue.setCurrentTripFuelR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559972404:
                sStaticEventChangeValue.setCurrentTripMileageR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            default:
                Log.debug(CarVendorStaticManager.TAG, "break other properties ,except static");
                break;
        }
        Log.debug(CarVendorStaticManager.TAG, "convertCarVendorPropertyValue " + sStaticEventChangeValue.toString(), DEBUG_LOGGER);
        return sStaticEventChangeValue;
    }

    private OnCarStaticChangeEventListenerProxy findListenerLocked(CarVendorStaticManager.OnCarStaticEventListener listener) {
        for (OnCarStaticChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarStaticChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorStaticManager.OnCarStaticEventListener listener;

        public OnCarStaticChangeEventListenerProxy(CarVendorStaticManager.OnCarStaticEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorStaticMgr.this.traceCallbackPrepare();
                StaticEventChangeValue newValue = CarVendorStaticMgr.convertCarVendorProperty(value);
                CarVendorStaticMgr.this.traceCallbackBefore();
                this.listener.onStaticEventChange(newValue);
                CarVendorStaticMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorStaticManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorStaticManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onStaticErrorEvent(propertyId, zone);
        }
    }
}
