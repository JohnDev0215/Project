package com.ts.lib.car.hardware.whud;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.whud.CarVendorWhudManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorWhudMgr extends CarVendorManagerBase implements CarVendorWhudManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static WhudEventChangeValue sWhudEventChangeValue = new WhudEventChangeValue();
    private final LinkedList<OnCarWhudChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtWhudIds = {557872393, 557872394, 557872391, 557872386, 557872389, 557872390, 557872384, 557872387, 557875227, 557872392, 557872388, 557872385};

    public CarVendorWhudMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorWhudManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorWhudManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorWhudManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorWhudManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnit1S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnit1S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT1_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT1_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnit2S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnit2S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT2_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT2_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnit3S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnit3S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT3_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT3_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnit4S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnit4S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT4_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT4_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnit5S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnit5S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT5_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT5_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnit6S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnit6S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT6_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT6_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnit7S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnit7S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT7_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT7_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnit8S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnit8S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT8_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT8_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentDayUnitLenS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentDayUnitLenS value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_DAY_UNIT_LEN_S));
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorWhudManager.ID_CURRENT_DAY_UNIT_LEN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnit1S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnit1S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT1_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT1_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnit2S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnit2S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT2_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT2_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnit3S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnit3S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT3_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT3_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnit4S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnit4S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT4_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT4_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnit5S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnit5S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT5_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT5_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnit6S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnit6S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT6_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT6_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnit7S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnit7S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT7_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT7_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnit8S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnit8S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT8_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT8_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentHourUnitLenS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentHourUnitLenS value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_HOUR_UNIT_LEN_S));
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorWhudManager.ID_CURRENT_HOUR_UNIT_LEN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnit1S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnit1S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT1_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT1_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnit2S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnit2S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT2_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT2_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnit3S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnit3S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT3_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT3_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnit4S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnit4S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT4_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT4_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnit5S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnit5S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT5_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT5_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnit6S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnit6S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT6_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT6_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnit7S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnit7S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT7_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT7_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnit8S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnit8S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT8_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT8_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCurrentMinUnitLenS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCurrentMinUnitLenS value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_CURRENT_MIN_UNIT_LEN_S));
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorWhudManager.ID_CURRENT_MIN_UNIT_LEN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitData1S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitData1S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA1_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA1_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitData2S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitData2S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA2_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA2_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitData3S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitData3S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA3_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA3_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitData4S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitData4S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA4_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA4_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitData5S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitData5S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA5_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA5_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitData6S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitData6S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA6_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA6_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitData7S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitData7S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA7_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA7_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitData8S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitData8S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA8_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA8_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNextRoadDistanceUnitDataLenS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNextRoadDistanceUnitDataLenS value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA_LEN_S));
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorWhudManager.ID_NEXT_ROAD_DISTANCE_UNIT_DATA_LEN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitData1S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitData1S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA1_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA1_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitData2S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitData2S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA2_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA2_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitData3S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitData3S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA3_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA3_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitData4S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitData4S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA4_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA4_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitData5S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitData5S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA5_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA5_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitData6S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitData6S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA6_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA6_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitData7S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitData7S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA7_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA7_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitData8S(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitData8S value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA8_S));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA8_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setTargetDistanceUnitDataLenS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setTargetDistanceUnitDataLenS value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA_LEN_S));
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorWhudManager.ID_TARGET_DISTANCE_UNIT_DATA_LEN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setScreenDisplayStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setScreenDisplayStateS value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_SCREEN_DISPLAY_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorWhudManager.ID_SCREEN_DISPLAY_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getAccCruisingLampColorR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872394, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAccCruisingLampColorR property: 0x");
            sb.append(tenToHex(557872394));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNaviStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNaviStateS value = " + value + " property: 0x" + tenToHex(557872432));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872432, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getAccCruisingLampR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872393, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAccCruisingLampR property: 0x");
            sb.append(tenToHex(557872393));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getAccCruisingSpeedValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872391, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAccCruisingSpeedValueR property: 0x");
            sb.append(tenToHex(557872391));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getAheadVehicleSpaceR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872386, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAheadVehicleSpaceR property: 0x");
            sb.append(tenToHex(557872386));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setArrivalDayEstimatedS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setArrivalDayEstimatedS property: 0x" + tenToHex(557872416) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872416, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setArrivalHourEstimatedS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setArrivalHourEstimatedS property: 0x" + tenToHex(557872417) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872417, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setArrivalMinuteEstimatedS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setArrivalMinuteEstimatedS property: 0x" + tenToHex(557872418) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872418, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setArrivalSecondEstimatedS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setArrivalSecondEstimatedS property: 0x" + tenToHex(557872419) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872419, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCallStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCallStateS property: 0x" + tenToHex(557872402) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872402, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCallTimeHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCallTimeHourS property: 0x" + tenToHex(557872398) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872398, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCallTimeMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCallTimeMinuteS property: 0x" + tenToHex(557872399) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872399, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCallTimeSecondS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCallTimeSecondS property: 0x" + tenToHex(557872400) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872400, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDistanceAheadS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDistanceAheadS property: 0x" + tenToHex(557872404) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872404, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDistanceAheadUnitS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDistanceAheadUnitS property: 0x" + tenToHex(557872405) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872405, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDistanceFrontTargetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDistanceFrontTargetS property: 0x" + tenToHex(557872414) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872414, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDistanceFrontTargetUnitS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDistanceFrontTargetUnitS property: 0x" + tenToHex(557872415) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872415, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDriveRemainingDaysS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDriveRemainingDaysS property: 0x" + tenToHex(557872409) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872409, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDriveRemainingHourFormatS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDriveRemainingHourFormatS property: 0x" + tenToHex(557872412) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872412, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDriveRemainingHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDriveRemainingHourS property: 0x" + tenToHex(557872406) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872406, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDriveRemainingMileageS(long value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDriveRemainingMileageS property: 0x" + tenToHex(CarVendorWhudManager.ID_DRIVE_REMAINING_MILEAGES) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Long.class, CarVendorWhudManager.ID_DRIVE_REMAINING_MILEAGES, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDriveRemainingMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDriveRemainingMinuteS property: 0x" + tenToHex(557872407) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872407, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDriveRemainingSecondS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDriveRemainingSecondS property: 0x" + tenToHex(557872408) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872408, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setDriveRemainingUnitS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setDriveRemainingUnitS property: 0x" + tenToHex(557872411) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872411, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getLeftLineR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872389, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftLineR property: 0x");
            sb.append(tenToHex(557872389));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setMediaSourceS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setMediaSourceS property: 0x" + tenToHex(557872401) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872401, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setOverseasMapSupplierS(int state) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setOverseasMapSupplierS property: 0x" + tenToHex(CarVendorWhudManager.ID_OVERSEAS_MAP_SUPPLIER_S) + " class: int state: " + CastArrayUtil.parseString(state), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorWhudManager.ID_OVERSEAS_MAP_SUPPLIER_S, 0, CastArrayUtil.toWrap(state));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setMusicPlayProgressS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setMusicPlayProgressS property: 0x" + tenToHex(557872397) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872397, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setMusicPlayStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setMusicPlayStateS property: 0x" + tenToHex(557872395) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872395, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNameNextRoadS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNameNextRoadS property: 0x" + tenToHex(557872420) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872420, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNavGuideInfoAdvancedS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNavGuideInfoAdvancedS property: 0x" + tenToHex(557872413) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872413, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNavGuideInfoS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNavGuideInfoS property: 0x" + tenToHex(557872403) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872403, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNaviTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNaviTypeS property: 0x" + tenToHex(557872421) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872421, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setRadioPlayStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setRadioPlayStateS property: 0x" + tenToHex(557872396) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872396, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getRightLineR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872390, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightLineR property: 0x");
            sb.append(tenToHex(557872390));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getShowAdasWorkR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872384, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getShowAdasWorkR property: 0x");
            sb.append(tenToHex(557872384));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getTextHintR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872387, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTextHintR property: 0x");
            sb.append(tenToHex(557872387));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getTextMessageHintR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875227, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTextMessageHintR property: 0x");
            sb.append(tenToHex(557875227));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getTrafficSignRecognitionValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872392, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficSignRecognitionValueR property: 0x");
            sb.append(tenToHex(557872392));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getVehicleBodyPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872388, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVehicleBodyPositionR property: 0x");
            sb.append(tenToHex(557872388));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public int getVehicleSpaceR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872385, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVehicleSpaceR property: 0x");
            sb.append(tenToHex(557872385));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorWhudManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNameNextRoadUnitcode(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNameNextRoadUnitcode property: 0x" + tenToHex(561018150) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, 561018150, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCallNumberLenS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCallNumberLenS property: 0x" + tenToHex(CarVendorWhudManager.ID_CALL_NUMBER_LEN_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorWhudManager.ID_CALL_NUMBER_LEN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setCallNumberS(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setCallNumberS property: 0x" + tenToHex(CarVendorWhudManager.ID_CALL_NUMBER_S) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_CALL_NUMBER_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setMusicCurrentNameLenS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setMusicCurrentNameLenS property: 0x" + tenToHex(CarVendorWhudManager.ID_MUSIC_CURRENT_NAME_LEN_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorWhudManager.ID_MUSIC_CURRENT_NAME_LEN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setMusicCurrentNameS(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setMusicCurrentNameS property: 0x" + tenToHex(CarVendorWhudManager.ID_MUSIC_CURRENT_NAME_S) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_MUSIC_CURRENT_NAME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setEasyNavGuideInfoS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setEasyNavGuideInfoS value = " + value + " property: 0x" + tenToHex(558986545), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Long[].class, 558986545, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void addCarWhudEventListener(CarVendorWhudManager.OnCarWhudEventListener listener) throws CarNotConnectedException {
        OnCarWhudChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarWhudChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorWhudManager.TAG, "addCarWhudEventListener propId size: " + this.mExtWhudIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtWhudIds) {
                int propId = num.intValue();
                Log.debug(CarVendorWhudManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa1(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa1 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA1) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA1, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa2(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa2 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA2) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA2, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa3(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa3 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA3) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA3, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa4(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa4 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA4) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA4, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa5(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa5 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA5) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA5, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa6(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa6 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA6) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA6, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa7(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa7 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA7) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA7, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa8(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa8 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA8) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA8, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSa9(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSa9 property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA9) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA9, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSaA(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSaA property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAA) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAA, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSaB(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSaB property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAB) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAB, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSaC(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSaC property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAC) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAC, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSaD(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSaD property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAD) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAD, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextRoadNameSaE(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setNewNameNextRoadNameSaE property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAE) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAE, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void setNewNameNextLenAndRoadS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorWhudManager.TAG, "setEasyNavGuideInfoS value = " + value + " property: 0x" + tenToHex(CarVendorWhudManager.ID_NEW_NAME_NEXT_LEN_AND_ROAD_S), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorWhudManager.ID_NEW_NAME_NEXT_LEN_AND_ROAD_S, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.whud.CarVendorWhudManager
    public void removeCarWhudEventListener(CarVendorWhudManager.OnCarWhudEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarWhudChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorWhudManager.TAG, "removeCarWhudEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WhudEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sWhudEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorWhudManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        if (propertyId == 557872432) {
            sWhudEventChangeValue.setNaviStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
        } else if (propertyId == 557875227) {
            sWhudEventChangeValue.setTextMessageHintR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
        } else if (propertyId == 558920986) {
            sWhudEventChangeValue.setDriveRemainingMileageS(CastArrayUtil.toPrimitive((Long) value.getValue()));
        } else {
            switch (propertyId) {
                case 557872384:
                    sWhudEventChangeValue.setShowAdasWorkR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872385:
                    sWhudEventChangeValue.setVehicleSpaceR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872386:
                    sWhudEventChangeValue.setAheadVehicleSpaceR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872387:
                    sWhudEventChangeValue.setTextHintR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872388:
                    sWhudEventChangeValue.setVehicleBodyPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872389:
                    sWhudEventChangeValue.setLeftLineR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872390:
                    sWhudEventChangeValue.setRightLineR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872391:
                    sWhudEventChangeValue.setAccCruisingSpeedValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872392:
                    sWhudEventChangeValue.setTrafficSignRecognitionValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872393:
                    sWhudEventChangeValue.setAccCruisingLampR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872394:
                    sWhudEventChangeValue.setAccCruisingLampColorR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872395:
                    sWhudEventChangeValue.setMusicPlayStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872396:
                    sWhudEventChangeValue.setRadioPlayStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872397:
                    sWhudEventChangeValue.setMusicPlayProgressS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872398:
                    sWhudEventChangeValue.setCallTimeHourS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872399:
                    sWhudEventChangeValue.setCallTimeMinuteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872400:
                    sWhudEventChangeValue.setCallTimeSecondS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872401:
                    sWhudEventChangeValue.setMediaSourceS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872402:
                    sWhudEventChangeValue.setCallStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872403:
                    sWhudEventChangeValue.setNavGuideInfoS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872404:
                    sWhudEventChangeValue.setDistanceAheadS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872405:
                    sWhudEventChangeValue.setDistanceAheadUnitS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872406:
                    sWhudEventChangeValue.setDriveRemainingHourS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872407:
                    sWhudEventChangeValue.setDriveRemainingMinuteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872408:
                    sWhudEventChangeValue.setDriveRemainingSecondS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872409:
                    sWhudEventChangeValue.setDriveRemainingDaysS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                default:
                    switch (propertyId) {
                        case 557872411:
                            sWhudEventChangeValue.setDriveRemainingUnitS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872412:
                            sWhudEventChangeValue.setDriveRemainingHourFormatS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872413:
                            sWhudEventChangeValue.setNavGuideInfoAdvancedS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872414:
                            sWhudEventChangeValue.setDistanceFrontTargetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872415:
                            sWhudEventChangeValue.setDistanceFrontTargetUnitS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872416:
                            sWhudEventChangeValue.setArrivalDayEstimatedS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872417:
                            sWhudEventChangeValue.setArrivalHourEstimatedS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872418:
                            sWhudEventChangeValue.setArrivalMinuteEstimatedS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872419:
                            sWhudEventChangeValue.setArrivalSecondEstimatedS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872420:
                            sWhudEventChangeValue.setNameNextRoadS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872421:
                            sWhudEventChangeValue.setNaviTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        default:
                            switch (propertyId) {
                                case 558986545:
                                    sWhudEventChangeValue.setEasyNavGuideInfoS(CastArrayUtil.toPrimitive((Long[]) value.getValue()));
                                    break;
                                case CarVendorWhudManager.ID_NEW_NAME_NEXT_LEN_AND_ROAD_S /* 558986546 */:
                                    sWhudEventChangeValue.setNewNameNextLenAndRoadS(CastArrayUtil.toPrimitive((Long[]) value.getValue()));
                                    break;
                                default:
                                    switch (propertyId) {
                                        case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA1 /* 561018163 */:
                                            sWhudEventChangeValue.setNewNameNextRoadNameSa1(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                            break;
                                        case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA2 /* 561018164 */:
                                            sWhudEventChangeValue.setNewNameNextRoadNameSa2(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                            break;
                                        case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA3 /* 561018165 */:
                                            sWhudEventChangeValue.setNewNameNextRoadNameSa3(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                            break;
                                        case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA4 /* 561018166 */:
                                            sWhudEventChangeValue.setNewNameNextRoadNameSa4(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                            break;
                                        case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA5 /* 561018167 */:
                                            sWhudEventChangeValue.setNewNameNextRoadNameSa5(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                            break;
                                        case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA6 /* 561018168 */:
                                            sWhudEventChangeValue.setNewNameNextRoadNameSa6(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                            break;
                                        case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA7 /* 561018169 */:
                                            sWhudEventChangeValue.setNewNameNextRoadNameSa7(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                            break;
                                        default:
                                            switch (propertyId) {
                                                case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA8 /* 561018176 */:
                                                    sWhudEventChangeValue.setNewNameNextRoadNameSa8(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                                    break;
                                                case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SA9 /* 561018177 */:
                                                    sWhudEventChangeValue.setNewNameNextRoadNameSa9(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                                    break;
                                                case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAA /* 561018178 */:
                                                    sWhudEventChangeValue.setNewNameNextRoadNameSaA(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                                    break;
                                                case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAB /* 561018179 */:
                                                    sWhudEventChangeValue.setNewNameNextRoadNameSaB(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                                    break;
                                                case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAC /* 561018180 */:
                                                    sWhudEventChangeValue.setNewNameNextRoadNameSaC(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                                    break;
                                                case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAD /* 561018181 */:
                                                    sWhudEventChangeValue.setNewNameNextRoadNameSaD(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                                    break;
                                                case CarVendorWhudManager.ID_NEW_NAME_NEXT_ROAD_NAME_SAE /* 561018182 */:
                                                    sWhudEventChangeValue.setNewNameNextRoadNameSaE(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                                                    break;
                                                default:
                                                    Log.debug(CarVendorWhudManager.TAG, "break other properties ,except whud");
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        Log.debug(CarVendorWhudManager.TAG, "convertCarVendorPropertyValue " + sWhudEventChangeValue.toString(), DEBUG_LOGGER);
        return sWhudEventChangeValue;
    }

    private OnCarWhudChangeEventListenerProxy findListenerLocked(CarVendorWhudManager.OnCarWhudEventListener listener) {
        for (OnCarWhudChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarWhudChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorWhudManager.OnCarWhudEventListener listener;

        public OnCarWhudChangeEventListenerProxy(CarVendorWhudManager.OnCarWhudEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorWhudMgr.this.traceCallbackPrepare();
                WhudEventChangeValue newValue = CarVendorWhudMgr.convertCarVendorProperty(value);
                CarVendorWhudMgr.this.traceCallbackBefore();
                this.listener.onWhudEventChange(newValue);
                CarVendorWhudMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorWhudManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorWhudManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onWhudErrorEvent(propertyId, zone);
        }
    }
}
