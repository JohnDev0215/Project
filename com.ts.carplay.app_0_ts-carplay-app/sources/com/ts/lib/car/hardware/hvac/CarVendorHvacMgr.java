package com.ts.lib.car.hardware.hvac;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.hvac.CarVendorHvacManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorHvacMgr extends CarVendorManagerBase implements CarVendorHvacManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static HvacEventChangeValue sHvacEventChangeValue = new HvacEventChangeValue();
    private final LinkedList<OnCarHvacChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtHvacIds = {557846611, 557846546, 557846553, 557846590, 557846561, 557846563, 557846571, 557846578, 557846588, 557846580, 557846584, 557846586, 557846582, 557846569, 557846549, 557846612, 557846539, 557846542, 557846545, 557846557, 557846558, 557846541, 557846658, 557846559, 557846552, 557846601, 557846531, 557846536, 557846537, 557846533, 557846547, 557846576, 557846535, 557846532, 557846538, 557846534, 557846543, 557846555, 557846603, 557846529, 557846602, 557846591, 557846592, 557846594, 557846597, 557846596, 557846530, 557846528, 557846609, 557846567, 557846613, 557846606, 557856063, 557846608, 557846607, 557846635, 557846634, 557846631, 557846630, 559943771, 559943770, 559943773, 559943772, 559943767, 559943766, 559943769, 559943768, 557846627, 557846626, 557846625, 557846624, 557846623, 557846622, 557846633, 557846632, 557846629, 557846628, 557846657, Integer.valueOf(CarVendorHvacManager.ID_AC_HEATING_BUTTON_CONFIG_R), 557846665, Integer.valueOf(CarVendorHvacManager.ID_TEMP_REGULATING_ACCURACY_R), Integer.valueOf(CarVendorHvacManager.ID_DRIVER_SEAT_HEATING2_A_A_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_DRIVER_SEAT_HEATING2_E4_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_DRIVER_SEAT_VENTILATING2_A_A_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_DRIVER_SEAT_VENTILATING2_E4_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_L_R_SEAT_HEATING2_A_A_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_L_R_SEAT_HEATING349_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_L_R_SEAT_HEATING35_D_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_L_R_SEAT_VENTILATING2_A_A_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_L_R_SEAT_VENTILATING349_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_L_R_SEAT_VENTILATING35_D_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING2_A_A_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING2_F_F_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING41_C_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING2_A_A_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING2_F_F_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING41_C_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_R_R_SEAT_HEATING21_D_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_R_R_SEAT_HEATING2_A_A_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_R_R_SEAT_HEATING349_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_R_R_SEAT_HEATING363_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_R_R_SEAT_VENTILATING21_D_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_R_R_SEAT_VENTILATING2_A_A_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_R_R_SEAT_VENTILATING349_CONFIG_R), Integer.valueOf(CarVendorHvacManager.ID_R_R_SEAT_VENTILATING363_CONFIG_R), 557846692, 557846704, 557846705, 557846698, 557846699, 557846696, 557846697, 557846693, 557846694, 557846695};

    public CarVendorHvacMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorHvacManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorHvacManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorHvacManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorHvacManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcAqsConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846704, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcAqsConfigR value = " + value + " property: 0x" + tenToHex(557846704));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcCockpitPurificationStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846705, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcCockpitPurificationStatusR value = " + value + " property: 0x" + tenToHex(557846705));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcTuyereSwitchStateCopilotLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846698, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcTuyereSwitchStateCopilotLeftR value = " + value + " property: 0x" + tenToHex(557846698));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcTuyereSwitchStateCopilotLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcTuyereSwitchStateCopilotLeftS value = " + value + " property: 0x" + tenToHex(557846702));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846702, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcTuyereSwitchStateCopilotRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846699, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcTuyereSwitchStateCopilotRightR value = " + value + " property: 0x" + tenToHex(557846699));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcTuyereSwitchStateCopilotRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcTuyereSwitchStateCopilotRightS value = " + value + " property: 0x" + tenToHex(557846703));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846703, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcTuyereSwitchStateMainLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846696, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcTuyereSwitchStateMainLeftR value = " + value + " property: 0x" + tenToHex(557846696));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcTuyereSwitchStateMainLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcTuyereSwitchStateMainLeftS value = " + value + " property: 0x" + tenToHex(557846700));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846700, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcTuyereSwitchStateMainRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846697, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcTuyereSwitchStateMainRightR value = " + value + " property: 0x" + tenToHex(557846697));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcTuyereSwitchStateMainRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcTuyereSwitchStateMainRightS value = " + value + " property: 0x" + tenToHex(557846701));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846701, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWorkModeMidLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846693, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcWorkModeMidLeftR value = " + value + " property: 0x" + tenToHex(557846693));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWorkModeMidRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846694, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcWorkModeMidRightR value = " + value + " property: 0x" + tenToHex(557846694));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWorkModeRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846695, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcWorkModeRearR value = " + value + " property: 0x" + tenToHex(557846695));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWorkCommandR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846692, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcWorkCommandR value = " + value + " property: 0x" + tenToHex(557846692));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getDriverSeatHeating2aaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_DRIVER_SEAT_HEATING2_A_A_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getDriverSeatHeating2aaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_DRIVER_SEAT_HEATING2_A_A_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getDriverSeatHeating2e4ConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_DRIVER_SEAT_HEATING2_E4_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getDriverSeatHeating2e4ConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_DRIVER_SEAT_HEATING2_E4_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getDriverSeatVentilating2aaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_DRIVER_SEAT_VENTILATING2_A_A_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getDriverSeatVentilating2aaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_DRIVER_SEAT_VENTILATING2_A_A_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getDriverSeatVentilating2e4ConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_DRIVER_SEAT_VENTILATING2_E4_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getDriverSeatVentilating2e4ConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_DRIVER_SEAT_VENTILATING2_E4_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getLrSeatHeating2aaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_L_R_SEAT_HEATING2_A_A_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getLrSeatHeating2aaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_L_R_SEAT_HEATING2_A_A_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getLrSeatHeating349ConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_L_R_SEAT_HEATING349_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getLrSeatHeating349ConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_L_R_SEAT_HEATING349_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getLrSeatHeating35dConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_L_R_SEAT_HEATING35_D_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getLrSeatHeating35dConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_L_R_SEAT_HEATING35_D_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getLrSeatVentilating2aaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_L_R_SEAT_VENTILATING2_A_A_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getLrSeatVentilating2aaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_L_R_SEAT_VENTILATING2_A_A_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getLrSeatVentilating349ConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_L_R_SEAT_VENTILATING349_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getLrSeatVentilating349ConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_L_R_SEAT_VENTILATING349_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getLrSeatVentilating35dConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_L_R_SEAT_VENTILATING35_D_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getLrSeatVentilating35dConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_L_R_SEAT_VENTILATING35_D_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getPassengerSeatHeating2aaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING2_A_A_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getPassengerSeatHeating2aaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING2_A_A_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getPassengerSeatHeating2ffConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING2_F_F_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getPassengerSeatHeating2ffConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING2_F_F_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getPassengerSeatHeating41cConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING41_C_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getPassengerSeatHeating41cConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING41_C_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getPassengerSeatVentilating2aaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING2_A_A_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getPassengerSeatVentilating2aaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING2_A_A_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getPassengerSeatVentilating2ffConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING2_F_F_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getPassengerSeatVentilating2ffConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING2_F_F_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getPassengerSeatVentilating41cConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING41_C_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getPassengerSeatVentilating41cConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING41_C_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRrSeatHeating21dConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_R_R_SEAT_HEATING21_D_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getRrSeatHeating21dConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_R_R_SEAT_HEATING21_D_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRrSeatHeating2aaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_R_R_SEAT_HEATING2_A_A_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getRrSeatHeating2aaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_R_R_SEAT_HEATING2_A_A_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRrSeatHeating349ConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_R_R_SEAT_HEATING349_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getRrSeatHeating349ConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_R_R_SEAT_HEATING349_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRrSeatHeating363ConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_R_R_SEAT_HEATING363_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getRrSeatHeating363ConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_R_R_SEAT_HEATING363_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRrSeatVentilating21dConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_R_R_SEAT_VENTILATING21_D_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getRrSeatVentilating21dConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_R_R_SEAT_VENTILATING21_D_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRrSeatVentilating2aaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_R_R_SEAT_VENTILATING2_A_A_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getRrSeatVentilating2aaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_R_R_SEAT_VENTILATING2_A_A_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRrSeatVentilating349ConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_R_R_SEAT_VENTILATING349_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getRrSeatVentilating349ConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_R_R_SEAT_VENTILATING349_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRrSeatVentilating363ConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_R_R_SEAT_VENTILATING363_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getRrSeatVentilating363ConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_R_R_SEAT_VENTILATING363_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcHeatingButtonConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_AC_HEATING_BUTTON_CONFIG_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcHeatingButtonConfigR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_AC_HEATING_BUTTON_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcHeatingStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846665, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcHeatingStateR value = " + value + " property: 0x" + tenToHex(557846665));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcHeatingStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcHeatingStateS value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_AC_HEATING_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHvacManager.ID_AC_HEATING_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getTempRegulatingAccuracyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHvacManager.ID_TEMP_REGULATING_ACCURACY_R, 0);
            Log.debug(CarVendorHvacManager.TAG, "getTempRegulatingAccuracyR value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_TEMP_REGULATING_ACCURACY_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setConstantTemperatureS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setConstantTemperatureS value = " + value + " property: 0x" + tenToHex(CarVendorHvacManager.ID_CONSTANT_TEMPERATURE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHvacManager.ID_CONSTANT_TEMPERATURE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWorkModeCopilotR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846635, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWorkModeCopilotR property: 0x");
            sb.append(tenToHex(557846635));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWorkModeDriverR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846634, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWorkModeDriverR property: 0x");
            sb.append(tenToHex(557846634));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAirOutletStateCopilotR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846631, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletStateCopilotR property: 0x");
            sb.append(tenToHex(557846631));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletStateCopilotS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletStateCopilotS property: 0x" + tenToHex(557846653) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846653, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAirOutletStateDriverR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846630, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletStateDriverR property: 0x");
            sb.append(tenToHex(557846630));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletStateDriverS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletStateDriverS property: 0x" + tenToHex(557846652) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846652, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public float getAirOutletValueCopilotLeftHorizontalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559943771, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletValueCopilotLeftHorizontalR property: 0x");
            sb.append(tenToHex(559943771));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletValueCopilotLeftHorizontalS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletValueCopilotLeftHorizontalS property: 0x" + tenToHex(557846642) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846642, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public float getAirOutletValueCopilotLeftVerticalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559943770, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletValueCopilotLeftVerticalR property: 0x");
            sb.append(tenToHex(559943770));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletValueCopilotLeftVerticalS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletValueCopilotLeftVerticalS property: 0x" + tenToHex(557846640) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846640, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public float getAirOutletValueCopilotRightHorizontalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559943773, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletValueCopilotRightHorizontalR property: 0x");
            sb.append(tenToHex(559943773));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletValueCopilotRightHorizontalS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletValueCopilotRightHorizontalS property: 0x" + tenToHex(557846643) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846643, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public float getAirOutletValueCopilotRightVerticalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559943772, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletValueCopilotRightVerticalR property: 0x");
            sb.append(tenToHex(559943772));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletValueCopilotRightVerticalS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletValueCopilotRightVerticalS property: 0x" + tenToHex(557846641) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846641, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public float getAirOutletValueDriverLeftHorizontalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559943767, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletValueDriverLeftHorizontalR property: 0x");
            sb.append(tenToHex(559943767));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletValueDriverLeftHorizontalS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletValueDriverLeftHorizontalS property: 0x" + tenToHex(557846638) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846638, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public float getAirOutletValueDriverLeftVerticalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559943766, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletValueDriverLeftVerticalR property: 0x");
            sb.append(tenToHex(559943766));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletValueDriverLeftVerticalS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletValueDriverLeftVerticalS property: 0x" + tenToHex(557846636) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846636, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public float getAirOutletValueDriverRightHorizontalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559943769, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletValueDriverRightHorizontalR property: 0x");
            sb.append(tenToHex(559943769));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletValueDriverRightHorizontalS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletValueDriverRightHorizontalS property: 0x" + tenToHex(557846639) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846639, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public float getAirOutletValueDriverRightVerticalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559943768, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirOutletValueDriverRightVerticalR property: 0x");
            sb.append(tenToHex(559943768));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAirOutletValueDriverRightVerticalS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAirOutletValueDriverRightVerticalS property: 0x" + tenToHex(557846637) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846637, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAvoidBlowPeopleStateCopilotR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846627, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAvoidBlowPeopleStateCopilotR property: 0x");
            sb.append(tenToHex(557846627));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAvoidBlowPeopleStateCopilotS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAvoidBlowPeopleStateCopilotS property: 0x" + tenToHex(557846647) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846647, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAvoidBlowPeopleStateDriverR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846626, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAvoidBlowPeopleStateDriverR property: 0x");
            sb.append(tenToHex(557846626));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAvoidBlowPeopleStateDriverS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAvoidBlowPeopleStateDriverS property: 0x" + tenToHex(557846646) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846646, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getBlowPeopleStateCopilotR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846625, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBlowPeopleStateCopilotR property: 0x");
            sb.append(tenToHex(557846625));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setBlowPeopleStateCopilotS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setBlowPeopleStateCopilotS property: 0x" + tenToHex(557846645) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846645, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getBlowPeopleStateDriverR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846624, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBlowPeopleStateDriverR property: 0x");
            sb.append(tenToHex(557846624));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setBlowPeopleStateDriverS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setBlowPeopleStateDriverS property: 0x" + tenToHex(557846644) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846644, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getFreeWindBtnStateCopilotR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846623, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFreeWindBtnStateCopilotR property: 0x");
            sb.append(tenToHex(557846623));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setFreeWindBtnStateCopilotS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setFreeWindBtnStateCopilotS property: 0x" + tenToHex(557846651) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846651, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getFreeWindBtnStateDriverR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846622, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFreeWindBtnStateDriverR property: 0x");
            sb.append(tenToHex(557846622));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setFreeWindBtnStateDriverS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setFreeWindBtnStateDriverS property: 0x" + tenToHex(557846650) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846650, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getFrontRowAirOutletStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846633, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFrontRowAirOutletStateR property: 0x");
            sb.append(tenToHex(557846633));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setFrontRowAirOutletStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setFrontRowAirOutletStateS property: 0x" + tenToHex(557846655) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846655, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getIntelligentControlStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846632, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentControlStateR property: 0x");
            sb.append(tenToHex(557846632));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setIntelligentControlStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setIntelligentControlStateS property: 0x" + tenToHex(557846654) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846654, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getSweepBlowStateCopilotR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846629, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSweepBlowStateCopilotR property: 0x");
            sb.append(tenToHex(557846629));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setSweepBlowStateCopilotS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setSweepBlowStateCopilotS property: 0x" + tenToHex(557846649) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846649, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getSweepBlowStateDriverR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846628, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSweepBlowStateDriverR property: 0x");
            sb.append(tenToHex(557846628));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setSweepBlowStateDriverS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setSweepBlowStateDriverS property: 0x" + tenToHex(557846648) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846648, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcAssistModeTips() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846609, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcAssistModeTips property: 0x");
            sb.append(tenToHex(557846609));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcDefrostOpenStateLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846613, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcDefrostOpenStateLeft property: 0x");
            sb.append(tenToHex(557846613));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcFrontWindLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846567, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcFrontWindLevelR property: 0x");
            sb.append(tenToHex(557846567));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRemoteCtrlTimeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856063, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRemoteCtrlTimeR property: 0x");
            sb.append(tenToHex(557856063));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAutoCleanAirStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846608, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoCleanAirStateR property: 0x");
            sb.append(tenToHex(557846608));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getQuickCleanAirStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846606, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getQuickCleanAirStateR property: 0x");
            sb.append(tenToHex(557846606));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getQuickCleanTipR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846607, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getQuickCleanTipR property: 0x");
            sb.append(tenToHex(557846607));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcAutomaticButtonTurnOff() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846546, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcAutomaticButtonTurnOff property: 0x");
            sb.append(tenToHex(557846546));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcCircleKey(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcCircleKey property: 0x" + tenToHex(557846554) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846554, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcCircleMode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846553, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcCircleMode property: 0x");
            sb.append(tenToHex(557846553));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcCloseKey(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcCloseKey property: 0x" + tenToHex(557846551) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846551, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcCompressorManualSign() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846590, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcCompressorManualSign property: 0x");
            sb.append(tenToHex(557846590));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcControllerDriverTempR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846563, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcControllerDriverTempR property: 0x");
            sb.append(tenToHex(557846563));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcControllerDriverTempS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcControllerDriverTempS property: 0x" + tenToHex(557846564) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846564, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcControllerPassengerTempS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcControllerPassengerTempS property: 0x" + tenToHex(557846566) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846566, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcCtrlModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846547, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcCtrlModeR property: 0x");
            sb.append(tenToHex(557846547));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcCtrlModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcCtrlModeS property: 0x" + tenToHex(557846548) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846548, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcDefrosRearPanelNotAvailableTips() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846612, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcDefrosRearPanelNotAvailableTips property: 0x");
            sb.append(tenToHex(557846612));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcDefrostConfigLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846594, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcDefrostConfigLeft property: 0x");
            sb.append(tenToHex(557846594));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcDefrostR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846549, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcDefrostR property: 0x");
            sb.append(tenToHex(557846549));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcDefrostS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcDefrostS property: 0x" + tenToHex(557846550) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846550, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcDualChannelPm25Flag() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846539, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcDualChannelPm25Flag property: 0x");
            sb.append(tenToHex(557846539));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcDualMode(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcDualMode property: 0x" + tenToHex(557846562) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846562, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcFaultNumShownState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846542, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcFaultNumShownState property: 0x");
            sb.append(tenToHex(557846542));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcFrontWindLevelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcFrontWindLevelS property: 0x" + tenToHex(557846568) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846568, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcHasPerfumeConfiguration() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846545, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcHasPerfumeConfiguration property: 0x");
            sb.append(tenToHex(557846545));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcHeatModeFlag() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846557, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcHeatModeFlag property: 0x");
            sb.append(tenToHex(557846557));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcHeatPumpCompressorMode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846558, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcHeatPumpCompressorMode property: 0x");
            sb.append(tenToHex(557846558));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcHighTempSterilizationFlag() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846541, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcHighTempSterilizationFlag property: 0x");
            sb.append(tenToHex(557846541));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcIonStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcIonStateS property: 0x" + tenToHex(557846574) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846574, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcKeepEngineRunTIps() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846658, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcKeepEngineRunTIps property: 0x");
            sb.append(tenToHex(557846658));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcMaxColdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846571, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcMaxColdR property: 0x");
            sb.append(tenToHex(557846571));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcMaxCoolingStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcMaxCoolingStateS property: 0x" + tenToHex(557846599) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846599, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcMediaDefrostButton(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcMediaDefrostButton property: 0x" + tenToHex(557846575) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846575, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcNaturalWindR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846559, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcNaturalWindR property: 0x");
            sb.append(tenToHex(557846559));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcNaturalWindS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcNaturalWindS property: 0x" + tenToHex(557846560) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846560, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcOpenState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846552, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcOpenState property: 0x");
            sb.append(tenToHex(557846552));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcPtcPreheatSignalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846601, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcPtcPreheatSignalR property: 0x");
            sb.append(tenToHex(557846601));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearAcButton() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846531, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearAcButton property: 0x");
            sb.append(tenToHex(557846531));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearAcCtrlModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846578, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearAcCtrlModeR property: 0x");
            sb.append(tenToHex(557846578));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcRearAcCtrlModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcRearAcCtrlModeS property: 0x" + tenToHex(557846579) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846579, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearAcFunction() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846536, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearAcFunction property: 0x");
            sb.append(tenToHex(557846536));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearAcState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846580, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearAcState property: 0x");
            sb.append(tenToHex(557846580));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcRearAcStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcRearAcStateS property: 0x" + tenToHex(557846581) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846581, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearAcTempAdjust() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846537, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearAcTempAdjust property: 0x");
            sb.append(tenToHex(557846537));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearAcWindLevel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846532, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearAcWindLevel property: 0x");
            sb.append(tenToHex(557846532));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearButtonClick() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846533, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearButtonClick property: 0x");
            sb.append(tenToHex(557846533));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearLockR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846588, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearLockR property: 0x");
            sb.append(tenToHex(557846588));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcRearLockS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcRearLockS property: 0x" + tenToHex(557846589) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846589, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearPanelLockR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846576, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearPanelLockR property: 0x");
            sb.append(tenToHex(557846576));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcRearPanelLockS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcRearPanelLockS property: 0x" + tenToHex(557846577) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846577, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearTempR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846584, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearTempR property: 0x");
            sb.append(tenToHex(557846584));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcRearTempS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcRearTempS property: 0x" + tenToHex(557846585) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846585, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearWindAutoAdjust() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846535, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearWindAutoAdjust property: 0x");
            sb.append(tenToHex(557846535));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearWindLevelAdjust() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846538, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearWindLevelAdjust property: 0x");
            sb.append(tenToHex(557846538));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearWindLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846586, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearWindLevelR property: 0x");
            sb.append(tenToHex(557846586));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcRearWindLevelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcRearWindLevelS property: 0x" + tenToHex(557846587) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846587, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearWindModeAdjust() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846534, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearWindModeAdjust property: 0x");
            sb.append(tenToHex(557846534));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRearWindModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846582, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRearWindModeR property: 0x");
            sb.append(tenToHex(557846582));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcRearWindModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcRearWindModeS property: 0x" + tenToHex(557846583) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846583, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcRemoteCtrlTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcRemoteCtrlTimeS property: 0x" + tenToHex(557846600) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846600, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcRequestRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846561, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcRequestRight property: 0x");
            sb.append(tenToHex(557846561));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcTempCtrlSeparateState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846543, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcTempCtrlSeparateState property: 0x");
            sb.append(tenToHex(557846543));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcTempratureDeputy() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846603, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcTempratureDeputy property: 0x");
            sb.append(tenToHex(557846603));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcTurnOn(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcTurnOn property: 0x" + tenToHex(557846556) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846556, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846529, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcType property: 0x");
            sb.append(tenToHex(557846529));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWindLevelManualSign() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846591, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWindLevelManualSign property: 0x");
            sb.append(tenToHex(557846591));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWindModeManualSign() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846592, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWindModeManualSign property: 0x");
            sb.append(tenToHex(557846592));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWindModeNumR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846602, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWindModeNumR property: 0x");
            sb.append(tenToHex(557846602));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcWindModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846569, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWindModeR property: 0x");
            sb.append(tenToHex(557846569));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcWindModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcWindModeS property: 0x" + tenToHex(557846570) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846570, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcbtWindTips() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846611, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcbtWindTips property: 0x");
            sb.append(tenToHex(557846611));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAutoCleanAirStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAutoCleanAirStateS property: 0x" + tenToHex(557846598) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846598, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setConfirmCleanAirState(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setConfirmCleanAirState property: 0x" + tenToHex(557846595) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846595, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getHighTempAntivirusCountDown() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846596, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHighTempAntivirusCountDown property: 0x");
            sb.append(tenToHex(557846596));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getHighTempAntivirusState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846597, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHighTempAntivirusState property: 0x");
            sb.append(tenToHex(557846597));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setQuickCleanAirStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setQuickCleanAirStateS property: 0x" + tenToHex(557846605) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846605, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getRearAcDet() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846530, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearAcDet property: 0x");
            sb.append(tenToHex(557846530));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getTemperatureUnit() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846528, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTemperatureUnit property: 0x");
            sb.append(tenToHex(557846528));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getTempratureOut() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846555, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTempratureOut property: 0x");
            sb.append(tenToHex(557846555));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHvacManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void setAcAntibacterialControlStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHvacManager.TAG, "setAcAntibacterialControlStateS property: 0x" + tenToHex(CarVendorHvacManager.ID_AC_ANTIBACTERIAL_CONTROL_STATES) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHvacManager.ID_AC_ANTIBACTERIAL_CONTROL_STATES, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public int getAcPromptBoxShownStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846657, 0);
            Log.debug(CarVendorHvacManager.TAG, "getAcPromptBoxShownStateR value = " + value + " property: 0x" + tenToHex(557846657));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void addCarHvacEventListener(CarVendorHvacManager.OnCarHvacEventListener listener) throws CarNotConnectedException {
        OnCarHvacChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarHvacChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorHvacManager.TAG, "addCarHvacEventListener propId size: " + this.mExtHvacIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtHvacIds) {
                int propId = num.intValue();
                Log.debug(CarVendorHvacManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.hvac.CarVendorHvacManager
    public void removeCarHvacEventListener(CarVendorHvacManager.OnCarHvacEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarHvacChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorHvacManager.TAG, "removeCarHvacEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HvacEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sHvacEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorHvacManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case CarVendorHvacManager.ID_AC_TEMPERATURE_MID_RIGHT_S /* 557842695 */:
                sHvacEventChangeValue.setAcTemperatureMidRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_AC_TEMPERATURE_MID_LEFT_S /* 557846662 */:
                sHvacEventChangeValue.setAcTemperatureMidLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_AC_HEATING_BUTTON_CONFIG_R /* 557846664 */:
                sHvacEventChangeValue.setAcHeatingButtonConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846665:
                sHvacEventChangeValue.setAcHeatingStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_AC_HEATING_STATE_S /* 557846666 */:
                sHvacEventChangeValue.setAcHeatingStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_TEMP_REGULATING_ACCURACY_R /* 557846667 */:
                sHvacEventChangeValue.setTempRegulatingAccuracyR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_DRIVER_SEAT_VENTILATING2_E4_CONFIG_R /* 557846668 */:
                sHvacEventChangeValue.setDriverSeatVentilating2E4ConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_DRIVER_SEAT_VENTILATING2_A_A_CONFIG_R /* 557846669 */:
                sHvacEventChangeValue.setDriverSeatVentilating2AAConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_DRIVER_SEAT_HEATING2_E4_CONFIG_R /* 557846670 */:
                sHvacEventChangeValue.setDriverSeatHeating2E4ConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_DRIVER_SEAT_HEATING2_A_A_CONFIG_R /* 557846671 */:
                sHvacEventChangeValue.setDriverSeatHeating2AAConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING41_C_CONFIG_R /* 557846672 */:
                sHvacEventChangeValue.setPassengerSeatVentilating41CConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING2_A_A_CONFIG_R /* 557846673 */:
                sHvacEventChangeValue.setPassengerSeatVentilating2AAConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_PASSENGER_SEAT_VENTILATING2_F_F_CONFIG_R /* 557846674 */:
                sHvacEventChangeValue.setPassengerSeatVentilating2FFConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING41_C_CONFIG_R /* 557846675 */:
                sHvacEventChangeValue.setPassengerSeatHeating41CConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING2_A_A_CONFIG_R /* 557846676 */:
                sHvacEventChangeValue.setPassengerSeatHeating2AAConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_PASSENGER_SEAT_HEATING2_F_F_CONFIG_R /* 557846677 */:
                sHvacEventChangeValue.setPassengerSeatHeating2FFConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_L_R_SEAT_VENTILATING2_A_A_CONFIG_R /* 557846678 */:
                sHvacEventChangeValue.setLRSeatVentilating2AAConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_L_R_SEAT_VENTILATING35_D_CONFIG_R /* 557846679 */:
                sHvacEventChangeValue.setLRSeatVentilating35DConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_L_R_SEAT_VENTILATING349_CONFIG_R /* 557846680 */:
                sHvacEventChangeValue.setLRSeatVentilating349ConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_L_R_SEAT_HEATING2_A_A_CONFIG_R /* 557846681 */:
                sHvacEventChangeValue.setLRSeatHeating2AAConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_L_R_SEAT_HEATING35_D_CONFIG_R /* 557846682 */:
                sHvacEventChangeValue.setLRSeatHeating35DConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_L_R_SEAT_HEATING349_CONFIG_R /* 557846683 */:
                sHvacEventChangeValue.setLRSeatHeating349ConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_R_R_SEAT_VENTILATING363_CONFIG_R /* 557846684 */:
                sHvacEventChangeValue.setRRSeatVentilating363ConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_R_R_SEAT_VENTILATING2_A_A_CONFIG_R /* 557846685 */:
                sHvacEventChangeValue.setRRSeatVentilating2AAConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_R_R_SEAT_VENTILATING349_CONFIG_R /* 557846686 */:
                sHvacEventChangeValue.setRRSeatVentilating349ConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_R_R_SEAT_VENTILATING21_D_CONFIG_R /* 557846687 */:
                sHvacEventChangeValue.setRRSeatVentilating21DConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_R_R_SEAT_HEATING363_CONFIG_R /* 557846688 */:
                sHvacEventChangeValue.setRRSeatHeating363ConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_R_R_SEAT_HEATING2_A_A_CONFIG_R /* 557846689 */:
                sHvacEventChangeValue.setRRSeatHeating2AAConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_R_R_SEAT_HEATING349_CONFIG_R /* 557846690 */:
                sHvacEventChangeValue.setRRSeatHeating349ConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHvacManager.ID_R_R_SEAT_HEATING21_D_CONFIG_R /* 557846691 */:
                sHvacEventChangeValue.setRRSeatHeating21DConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846692:
                sHvacEventChangeValue.setAcWorkModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846693:
                sHvacEventChangeValue.setAcWorkModeMidLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846694:
                sHvacEventChangeValue.setAcWorkModeMidRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846695:
                sHvacEventChangeValue.setAcWorkModeRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846696:
                sHvacEventChangeValue.setAcTuyereSwitchStateMainLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846697:
                sHvacEventChangeValue.setAcTuyereSwitchStateMainRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846698:
                sHvacEventChangeValue.setAcTuyereSwitchStateCopilotLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846699:
                sHvacEventChangeValue.setAcTuyereSwitchStateCopilotRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846700:
                sHvacEventChangeValue.setAcTuyereSwitchStateMainLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846701:
                sHvacEventChangeValue.setAcTuyereSwitchStateMainRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846702:
                sHvacEventChangeValue.setAcTuyereSwitchStateCopilotLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846703:
                sHvacEventChangeValue.setAcTuyereSwitchStateCopilotRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846704:
                sHvacEventChangeValue.setAcAqsConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846705:
                sHvacEventChangeValue.setAcCockpitPurificationStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856063:
                sHvacEventChangeValue.setAcRemoteCtrlTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 559943766:
                sHvacEventChangeValue.setAirOutletValueDriverLeftVerticalR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559943767:
                sHvacEventChangeValue.setAirOutletValueDriverLeftHorizontalR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559943768:
                sHvacEventChangeValue.setAirOutletValueDriverRightVerticalR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559943769:
                sHvacEventChangeValue.setAirOutletValueDriverRightHorizontalR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559943770:
                sHvacEventChangeValue.setAirOutletValueCopilotLeftVerticalR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559943771:
                sHvacEventChangeValue.setAirOutletValueCopilotLeftHorizontalR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559943772:
                sHvacEventChangeValue.setAirOutletValueCopilotRightVerticalR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559943773:
                sHvacEventChangeValue.setAirOutletValueCopilotRightHorizontalR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557846528:
                        sHvacEventChangeValue.setTemperatureUnit(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846529:
                        sHvacEventChangeValue.setAcType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846530:
                        sHvacEventChangeValue.setRearAcDet(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846531:
                        sHvacEventChangeValue.setAcRearAcButton(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846532:
                        sHvacEventChangeValue.setAcRearAcWindLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846533:
                        sHvacEventChangeValue.setAcRearButtonClick(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846534:
                        sHvacEventChangeValue.setAcRearWindModeAdjust(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846535:
                        sHvacEventChangeValue.setAcRearWindAutoAdjust(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846536:
                        sHvacEventChangeValue.setAcRearAcFunction(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846537:
                        sHvacEventChangeValue.setAcRearAcTempAdjust(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846538:
                        sHvacEventChangeValue.setAcRearWindLevelAdjust(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846539:
                        sHvacEventChangeValue.setAcDualChannelPm25Flag(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557846541:
                                sHvacEventChangeValue.setAcHighTempSterilizationFlag(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846542:
                                sHvacEventChangeValue.setAcFaultNumShownState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846543:
                                sHvacEventChangeValue.setAcTempCtrlSeparateState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557846545:
                                        sHvacEventChangeValue.setAcHasPerfumeConfiguration(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846546:
                                        sHvacEventChangeValue.setAcAutomaticButtonTurnOff(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846547:
                                        sHvacEventChangeValue.setAcCtrlModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846548:
                                        sHvacEventChangeValue.setAcCtrlModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846549:
                                        sHvacEventChangeValue.setAcDefrostR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846550:
                                        sHvacEventChangeValue.setAcDefrostS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846551:
                                        sHvacEventChangeValue.setAcCloseKey(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846552:
                                        sHvacEventChangeValue.setAcOpenState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846553:
                                        sHvacEventChangeValue.setAcCircleMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846554:
                                        sHvacEventChangeValue.setAcCircleKey(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846555:
                                        sHvacEventChangeValue.setTempratureOut(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846556:
                                        sHvacEventChangeValue.setAcTurnOn(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846557:
                                        sHvacEventChangeValue.setAcHeatModeFlag(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846558:
                                        sHvacEventChangeValue.setAcHeatPumpCompressorMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846559:
                                        sHvacEventChangeValue.setAcNaturalWindR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846560:
                                        sHvacEventChangeValue.setAcNaturalWindS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846561:
                                        sHvacEventChangeValue.setAcRequestRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846562:
                                        sHvacEventChangeValue.setAcDualMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846563:
                                        sHvacEventChangeValue.setAcControllerDriverTempR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846564:
                                        sHvacEventChangeValue.setAcControllerDriverTempS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557846566:
                                                sHvacEventChangeValue.setAcControllerPassengerTempS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557846567:
                                                sHvacEventChangeValue.setAcFrontWindLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557846568:
                                                sHvacEventChangeValue.setAcFrontWindLevelS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557846569:
                                                sHvacEventChangeValue.setAcWindModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557846570:
                                                sHvacEventChangeValue.setAcWindModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557846571:
                                                sHvacEventChangeValue.setAcMaxColdR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case 557846574:
                                                        sHvacEventChangeValue.setAcIonStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846575:
                                                        sHvacEventChangeValue.setAcMediaDefrostButton(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846576:
                                                        sHvacEventChangeValue.setAcRearPanelLockR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846577:
                                                        sHvacEventChangeValue.setAcRearPanelLockS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846578:
                                                        sHvacEventChangeValue.setAcRearAcCtrlModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846579:
                                                        sHvacEventChangeValue.setAcRearAcCtrlModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846580:
                                                        sHvacEventChangeValue.setAcRearAcState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846581:
                                                        sHvacEventChangeValue.setAcRearAcStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846582:
                                                        sHvacEventChangeValue.setAcRearWindModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846583:
                                                        sHvacEventChangeValue.setAcRearWindModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846584:
                                                        sHvacEventChangeValue.setAcRearTempR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846585:
                                                        sHvacEventChangeValue.setAcRearTempS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846586:
                                                        sHvacEventChangeValue.setAcRearWindLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846587:
                                                        sHvacEventChangeValue.setAcRearWindLevelS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846588:
                                                        sHvacEventChangeValue.setAcRearLockR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846589:
                                                        sHvacEventChangeValue.setAcRearLockS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846590:
                                                        sHvacEventChangeValue.setAcCompressorManualSign(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846591:
                                                        sHvacEventChangeValue.setAcWindLevelManualSign(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557846592:
                                                        sHvacEventChangeValue.setAcWindModeManualSign(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case 557846594:
                                                                sHvacEventChangeValue.setAcDefrostConfigLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846595:
                                                                sHvacEventChangeValue.setConfirmCleanAirState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846596:
                                                                sHvacEventChangeValue.setHighTempAntivirusCountDown(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846597:
                                                                sHvacEventChangeValue.setHighTempAntivirusState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846598:
                                                                sHvacEventChangeValue.setAutoCleanAirStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846599:
                                                                sHvacEventChangeValue.setAcMaxCoolingStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846600:
                                                                sHvacEventChangeValue.setAcRemoteCtrlTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846601:
                                                                sHvacEventChangeValue.setAcPtcPreheatSignalR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846602:
                                                                sHvacEventChangeValue.setAcWindModeNumR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557846603:
                                                                sHvacEventChangeValue.setAcTempratureDeputy(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case 557846605:
                                                                        sHvacEventChangeValue.setQuickCleanAirStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557846606:
                                                                        sHvacEventChangeValue.setQuickCleanAirStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557846607:
                                                                        sHvacEventChangeValue.setQuickCleanTipR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557846608:
                                                                        sHvacEventChangeValue.setAutoCleanAirStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557846609:
                                                                        sHvacEventChangeValue.setAcAssistModeTips(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        switch (propertyId) {
                                                                            case 557846611:
                                                                                sHvacEventChangeValue.setAcbtWindTips(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557846612:
                                                                                sHvacEventChangeValue.setAcDefrosRearPanelNotAvailableTips(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557846613:
                                                                                sHvacEventChangeValue.setAcDefrostOpenStateLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            default:
                                                                                switch (propertyId) {
                                                                                    case 557846622:
                                                                                        sHvacEventChangeValue.setFreeWindBtnStateDriverR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846623:
                                                                                        sHvacEventChangeValue.setFreeWindBtnStateCopilotR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846624:
                                                                                        sHvacEventChangeValue.setBlowPeopleStateDriverR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846625:
                                                                                        sHvacEventChangeValue.setBlowPeopleStateCopilotR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846626:
                                                                                        sHvacEventChangeValue.setAvoidBlowPeopleStateDriverR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846627:
                                                                                        sHvacEventChangeValue.setAvoidBlowPeopleStateCopilotR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846628:
                                                                                        sHvacEventChangeValue.setSweepBlowStateDriverR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846629:
                                                                                        sHvacEventChangeValue.setSweepBlowStateCopilotR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846630:
                                                                                        sHvacEventChangeValue.setAirOutletStateDriverR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846631:
                                                                                        sHvacEventChangeValue.setAirOutletStateCopilotR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846632:
                                                                                        sHvacEventChangeValue.setIntelligentControlStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846633:
                                                                                        sHvacEventChangeValue.setFrontRowAirOutletStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846634:
                                                                                        sHvacEventChangeValue.setAcWorkModeDriverR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846635:
                                                                                        sHvacEventChangeValue.setAcWorkModeCopilotR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846636:
                                                                                        sHvacEventChangeValue.setAirOutletValueDriverLeftVerticalS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846637:
                                                                                        sHvacEventChangeValue.setAirOutletValueDriverRightVerticalS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846638:
                                                                                        sHvacEventChangeValue.setAirOutletValueDriverLeftHorizontalS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846639:
                                                                                        sHvacEventChangeValue.setAirOutletValueDriverRightHorizontalS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846640:
                                                                                        sHvacEventChangeValue.setAirOutletValueCopilotLeftVerticalS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846641:
                                                                                        sHvacEventChangeValue.setAirOutletValueCopilotRightVerticalS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846642:
                                                                                        sHvacEventChangeValue.setAirOutletValueCopilotLeftHorizontalS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846643:
                                                                                        sHvacEventChangeValue.setAirOutletValueCopilotRightHorizontalS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846644:
                                                                                        sHvacEventChangeValue.setBlowPeopleStateDriverS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846645:
                                                                                        sHvacEventChangeValue.setBlowPeopleStateCopilotS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846646:
                                                                                        sHvacEventChangeValue.setAvoidBlowPeopleStateDriverS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846647:
                                                                                        sHvacEventChangeValue.setAvoidBlowPeopleStateCopilotS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846648:
                                                                                        sHvacEventChangeValue.setSweepBlowStateDriverS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846649:
                                                                                        sHvacEventChangeValue.setSweepBlowStateCopilotS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846650:
                                                                                        sHvacEventChangeValue.setFreeWindBtnStateDriverS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846651:
                                                                                        sHvacEventChangeValue.setFreeWindBtnStateCopilotS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846652:
                                                                                        sHvacEventChangeValue.setAirOutletStateDriverS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846653:
                                                                                        sHvacEventChangeValue.setAirOutletStateCopilotS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846654:
                                                                                        sHvacEventChangeValue.setIntelligentControlStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846655:
                                                                                        sHvacEventChangeValue.setFrontRowAirOutletStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorHvacManager.ID_AC_ANTIBACTERIAL_CONTROL_STATES /* 557846656 */:
                                                                                        sHvacEventChangeValue.setAcAntibacterialControlStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846657:
                                                                                        sHvacEventChangeValue.setAcPromptBoxShownStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557846658:
                                                                                        sHvacEventChangeValue.setAcKeepEngineRunTIps(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    default:
                                                                                        Log.debug(CarVendorHvacManager.TAG, "break other properties ,except hvac");
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
        Log.debug(CarVendorHvacManager.TAG, "convertCarVendorPropertyValue " + sHvacEventChangeValue.toString(), DEBUG_LOGGER);
        return sHvacEventChangeValue;
    }

    private OnCarHvacChangeEventListenerProxy findListenerLocked(CarVendorHvacManager.OnCarHvacEventListener listener) {
        for (OnCarHvacChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarHvacChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorHvacManager.OnCarHvacEventListener listener;

        public OnCarHvacChangeEventListenerProxy(CarVendorHvacManager.OnCarHvacEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorHvacMgr.this.traceCallbackPrepare();
                HvacEventChangeValue newValue = CarVendorHvacMgr.convertCarVendorProperty(value);
                CarVendorHvacMgr.this.traceCallbackBefore();
                this.listener.onHvacEventChange(newValue);
                CarVendorHvacMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorHvacManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorHvacManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onHvacErrorEvent(propertyId, zone);
        }
    }
}
