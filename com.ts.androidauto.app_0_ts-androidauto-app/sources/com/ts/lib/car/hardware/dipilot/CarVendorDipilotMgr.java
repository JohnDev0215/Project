package com.ts.lib.car.hardware.dipilot;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CarVendorDipilotMgr extends CarVendorManagerBase implements CarVendorDipilotManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static DipilotEventChangeValue sDipilotEventChangeValue = new DipilotEventChangeValue();
    private final LinkedList<OnCarDipilotChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtDipilotIds = {557856838, 557845261, 557856796, 557870472, 557856804, 557856823, 557856800, 557856801, 557856780, 557856776, 557856812, 557856813, 557856809, 557856810, 557856772, 557856832, 557856830, 557856768, 557856789, 557856790, 557856839, 557856792, 557856774, 557856778, 557856798, 557856815, 557856827, 557856828, 557856825, 557856817, 557856821, 557856819, 557856786, 557856787, 557856835, 557856806, 557870505, 557856782, 557856784, 557856783, 557856770, 557856794, 557856842, 557856840, 557856843, 557856077, 557856078, 557856079, 557856080, 557856081, 557856082, 557856083, 557856084, 557856085, 557856086, 557856087, 557856088, 557856089, 557856090, 557856094, 557856095, 557856097, 557856098, 557856099, 557856106, 557870336, 557856845, 557856848, 557856850, 557856849, 557856846, 557856865, 557870469, 557856855, 557856867, 557870437, 557856859, 557856861, 557856871, 557870470, 557856879, 557856857, 557870416, 557870415, 557856851, 557856884, 557856877, 557856853, 557856863, 557856869, 557856875, 557856873, 557870471, 557856889, 557856891, 557856892, 557856894, 557856895, 557856896, 557872897, 557856917, 557856897, 557856910, 557856899, 557856923, 557856922, 557856900, 557856911, 557856916, 557856904, 557856905, 557856908, 557856920, 557856912, 557856906, 557856898, 557856913, 557856914, 557856915, 557856921, 557856924, 557856909, 557856907, 557856903, 557856901, 557856902, 557856918, 557856919, Integer.valueOf(CarVendorDipilotManager.ID_AUTO_PARKIG_ASSIST_MODE_R), Integer.valueOf(CarVendorDipilotManager.ID_AUTO_PARKIG_ASSIST_STATUS_R), Integer.valueOf(CarVendorDipilotManager.ID_HOME_ZONE_PARKING_ASSIST_STATUS_R), Integer.valueOf(CarVendorDipilotManager.ID_AUTOMATED_VALET_PARKING_STATUS_R), 557856929, 557856933, 557856934, 557856931, 557856935, 557870413, 557870400, 557856945, 557856936, 557845271, 557856944, 557856938, 557856940, 557856946, 557856950, 557856948};

    public CarVendorDipilotMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorDipilotManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorDipilotManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorDipilotManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorDipilotManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getFcsrFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856950, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getFcsrFaultR value = " + value + " property: 0x" + tenToHex(557856950));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getFcsrStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856948, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getFcsrStateR value = " + value + " property: 0x" + tenToHex(557856948));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setFcsrStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setFcsrStateS value = " + value + " property: 0x" + tenToHex(557856949));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856949, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAccStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856946, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAccStateR value = " + value + " property: 0x" + tenToHex(557856946));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAccStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAccStateS value = " + value + " property: 0x" + tenToHex(CarVendorDipilotManager.ID_A_C_C_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorDipilotManager.ID_A_C_C_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getHighHODToastR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870400, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getHighHODToastR value = " + value + " property: 0x" + tenToHex(557870400));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getHighIOPToastR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856945, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getHighIOPToastR value = " + value + " property: 0x" + tenToHex(557856945));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIopStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856936, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getIopStateR value = " + value + " property: 0x" + tenToHex(557856936));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setIopStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setIopStateS value = " + value + " property: 0x" + tenToHex(CarVendorDipilotManager.ID_I_O_P_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorDipilotManager.ID_I_O_P_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLowHODToastR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845271, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getLowHODToastR value = " + value + " property: 0x" + tenToHex(557845271));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLowIOPToastR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856944, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getLowIOPToastR value = " + value + " property: 0x" + tenToHex(557856944));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getSaocStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856938, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getSaocStateR value = " + value + " property: 0x" + tenToHex(557856938));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setSaocStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setSaocStateS value = " + value + " property: 0x" + tenToHex(CarVendorDipilotManager.ID_S_A_O_C_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorDipilotManager.ID_S_A_O_C_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getSlifStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856940, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getSlifStateR value = " + value + " property: 0x" + tenToHex(557856940));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setSlifStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setSlifStateS value = " + value + " property: 0x" + tenToHex(CarVendorDipilotManager.ID_S_L_I_F_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorDipilotManager.ID_S_L_I_F_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAEBDecActiveR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870413, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAEBDecActiveR value = " + value + " property: 0x" + tenToHex(557870413));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getCpdStatusNewR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856896, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getCpdStatusNewR value = " + value + " property: 0x" + tenToHex(557856896), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getCpdWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856933, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getCpdWarningStateR value = " + value + " property: 0x" + tenToHex(557856933));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getCpdWorkingStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856934, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getCpdWorkingStatusR value = " + value + " property: 0x" + tenToHex(557856934));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getEspOffKeyStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856935, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getEspOffKeyStatusR value = " + value + " property: 0x" + tenToHex(557856935));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setBsisCollicionAlarmSwitchS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setBsisCollicionAlarmSwitchS value = " + value + " property: 0x" + tenToHex(557856890), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856890, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getBsisEnableR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856889, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getBsisEnableR value = " + value + " property: 0x" + tenToHex(557856889), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getBsisFuncStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856891, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getBsisFuncStatusR value = " + value + " property: 0x" + tenToHex(557856891), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getMoisEnableR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856892, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getMoisEnableR value = " + value + " property: 0x" + tenToHex(557856892), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getMoisFuncStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856894, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getMoisFuncStateR value = " + value + " property: 0x" + tenToHex(557856894), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setMoisFunctionSwitchS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setMoisFunctionSwitchS value = " + value + " property: 0x" + tenToHex(557856893), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856893, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getSteepDescentSlowDownState4A9R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856895, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getSteepDescentSlowDownState4A9R value = " + value + " property: 0x" + tenToHex(557856895), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAbnormalVehicleWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856865, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAbnormalVehicleWarningStateR property: 0x");
            sb.append(tenToHex(557856865));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAbnormalVehicleWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAbnormalVehicleWarningStateS property: 0x" + tenToHex(557856866) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856866, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAebFaultStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870469, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAebFaultStateR property: 0x");
            sb.append(tenToHex(557870469));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getCollisionWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856855, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCollisionWarningStateR property: 0x");
            sb.append(tenToHex(557856855));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setCollisionWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setCollisionWarningStateS property: 0x" + tenToHex(557856856) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856856, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getControlLossWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856867, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getControlLossWarningStateR property: 0x");
            sb.append(tenToHex(557856867));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setControlLossWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setControlLossWarningStateS property: 0x" + tenToHex(557856868) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856868, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setDangerousActionfSwitchS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setDangerousActionfSwitchS property: 0x" + tenToHex(557856886) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856886, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setDistractionMonitoringAttentionS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setDistractionMonitoringAttentionS property: 0x" + tenToHex(557856883) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856883, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setDmsSelfCheckStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setDmsSelfCheckStatusS property: 0x" + tenToHex(557856882) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856882, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getDnpStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870437, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDnpStatusR property: 0x");
            sb.append(tenToHex(557870437));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getDoNotPassWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856859, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoNotPassWarningStateR property: 0x");
            sb.append(tenToHex(557856859));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setDoNotPassWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setDoNotPassWarningStateS property: 0x" + tenToHex(557856860) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856860, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getEmergencyBrakeWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856861, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEmergencyBrakeWarningStateR property: 0x");
            sb.append(tenToHex(557856861));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setEmergencyBrakeWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setEmergencyBrakeWarningStateS property: 0x" + tenToHex(557856862) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856862, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getEmergencyVehicleWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856871, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEmergencyVehicleWarningStateR property: 0x");
            sb.append(tenToHex(557856871));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setEmergencyVehicleWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setEmergencyVehicleWarningStateS property: 0x" + tenToHex(557856872) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856872, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setFatigueMonitoringStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setFatigueMonitoringStatusS property: 0x" + tenToHex(557856881) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856881, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setFatigueReliefSwitchS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setFatigueReliefSwitchS property: 0x" + tenToHex(557856885) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856885, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getFcwFaultStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870470, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFcwFaultStateR property: 0x");
            sb.append(tenToHex(557870470));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getGreenLightOptimalSpeedAdvisoryStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856879, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGreenLightOptimalSpeedAdvisoryStateR property: 0x");
            sb.append(tenToHex(557856879));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setGreenLightOptimalSpeedAdvisoryStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setGreenLightOptimalSpeedAdvisoryStateS property: 0x" + tenToHex(557856880) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856880, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIntersectionCollisionAlertStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856857, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntersectionCollisionAlertStateR property: 0x");
            sb.append(tenToHex(557856857));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setIntersectionCollisionAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setIntersectionCollisionAlertStateS property: 0x" + tenToHex(557856858) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856858, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLdwFaultStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870416, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLdwFaultStateR property: 0x");
            sb.append(tenToHex(557870416));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLksFaultStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870415, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLksFaultStateR property: 0x");
            sb.append(tenToHex(557870415));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLowSpeedEmergencyBrakingStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856851, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLowSpeedEmergencyBrakingStateR property: 0x");
            sb.append(tenToHex(557856851));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setLowSpeedEmergencyBrakingStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setLowSpeedEmergencyBrakingStateS property: 0x" + tenToHex(557856852) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856852, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getMechanismPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856884, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMechanismPositionR property: 0x");
            sb.append(tenToHex(557856884));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getRedLightViolationWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856877, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRedLightViolationWarningStateR property: 0x");
            sb.append(tenToHex(557856877));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setRedLightViolationWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setRedLightViolationWarningStateS property: 0x" + tenToHex(557856878) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856878, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getSideDistanceWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856853, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSideDistanceWarningStateR property: 0x");
            sb.append(tenToHex(557856853));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setSideDistanceWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setSideDistanceWarningStateS property: 0x" + tenToHex(557856854) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856854, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficIncidentWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856863, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficIncidentWarningStateR property: 0x");
            sb.append(tenToHex(557856863));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setTrafficIncidentWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setTrafficIncidentWarningStateS property: 0x" + tenToHex(557856864) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856864, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficJamWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856869, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficJamWarningStateR property: 0x");
            sb.append(tenToHex(557856869));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setTrafficJamWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setTrafficJamWarningStateS property: 0x" + tenToHex(557856870) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856870, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficLightInfoPushStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856875, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficLightInfoPushStateR property: 0x");
            sb.append(tenToHex(557856875));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setTrafficLightInfoPushStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setTrafficLightInfoPushStateS property: 0x" + tenToHex(557856876) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856876, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficSignalWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856873, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficSignalWarningStateR property: 0x");
            sb.append(tenToHex(557856873));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setTrafficSignalWarningStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setTrafficSignalWarningStateS property: 0x" + tenToHex(557856874) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856874, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getVruFaultStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870471, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVruFaultStateR property: 0x");
            sb.append(tenToHex(557870471));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAdasSoftVersionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856845, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAdasSoftVersionR property: 0x");
            sb.append(tenToHex(557856845));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getElkTakeOverStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856848, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElkTakeOverStateR property: 0x");
            sb.append(tenToHex(557856848));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIsliWarningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856850, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIsliWarningStateR property: 0x");
            sb.append(tenToHex(557856850));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTsrLitSpdChgInformStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856849, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTsrLitSpdChgInformStateR property: 0x");
            sb.append(tenToHex(557856849));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTsrLitSpdChgSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856846, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTsrLitSpdChgSwitchStateR property: 0x");
            sb.append(tenToHex(557856846));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setTsrLitSpdChgSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setTsrLitSpdChgSwitchStateS property: 0x" + tenToHex(557856847) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856847, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getCpdDelayStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856842, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCpdDelayStatusR property: 0x");
            sb.append(tenToHex(557856842));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getCpdStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856840, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCpdStatusR property: 0x");
            sb.append(tenToHex(557856840));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setCpdStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setCpdStatusS property: 0x" + tenToHex(557856841) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856841, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getDmsSwitchStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856843, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDmsSwitchStatusR property: 0x");
            sb.append(tenToHex(557856843));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setDmsSwitchStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setDmsSwitchStatusS property: 0x" + tenToHex(557856844) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856844, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getElecCarBodyStablySysStateChanged() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856838, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElecCarBodyStablySysStateChanged property: 0x");
            sb.append(tenToHex(557856838));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getRemoteControlDriveCloseTops() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845261, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemoteControlDriveCloseTops property: 0x");
            sb.append(tenToHex(557845261));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAINavigationStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856776, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAINavigationStateR property: 0x");
            sb.append(tenToHex(557856776));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAINavigationStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAINavigationStateS property: 0x" + tenToHex(557856777) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856777, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAiCareStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856796, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAiCareStateR property: 0x");
            sb.append(tenToHex(557856796));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAiCareStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAiCareStateS property: 0x" + tenToHex(557856797) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856797, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAiDistanceLightStateFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870472, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAiDistanceLightStateFirst property: 0x");
            sb.append(tenToHex(557870472));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAiDistanceLightStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAiDistanceLightStateS property: 0x" + tenToHex(557856805) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856805, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAiDistanceLightStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856804, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAiDistanceLightStateSecond property: 0x");
            sb.append(tenToHex(557856804));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAiEmergencyBrakeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856823, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAiEmergencyBrakeStateR property: 0x");
            sb.append(tenToHex(557856823));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAiEmergencyBrakeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAiEmergencyBrakeStateS property: 0x" + tenToHex(557856824) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856824, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAiMatrixLightStateFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856800, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAiMatrixLightStateFirst property: 0x");
            sb.append(tenToHex(557856800));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAiMatrixLightStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAiMatrixLightStateS property: 0x" + tenToHex(557856802) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856802, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAiMatrixLightStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856801, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAiMatrixLightStateSecond property: 0x");
            sb.append(tenToHex(557856801));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAlarmTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856780, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAlarmTypeR property: 0x");
            sb.append(tenToHex(557856780));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAlarmTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAlarmTypeS property: 0x" + tenToHex(557856781) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856781, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getBlindMonitorStateFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856812, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBlindMonitorStateFirst property: 0x");
            sb.append(tenToHex(557856812));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setBlindMonitorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setBlindMonitorStateS property: 0x" + tenToHex(557856814) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856814, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getBlindMonitorStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856813, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBlindMonitorStateSecond property: 0x");
            sb.append(tenToHex(557856813));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getE2EAIAssistanceStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856772, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getE2EAIAssistanceStateR property: 0x");
            sb.append(tenToHex(557856772));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setE2EAIAssistanceStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setE2EAIAssistanceStateS property: 0x" + tenToHex(557856773) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856773, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setElecCarBodyStablySysStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setElecCarBodyStablySysStateS property: 0x" + tenToHex(557856834) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856834, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getEmergencyLaneAssistanceStateFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856809, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEmergencyLaneAssistanceStateFirst property: 0x");
            sb.append(tenToHex(557856809));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setEmergencyLaneAssistanceStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setEmergencyLaneAssistanceStateS property: 0x" + tenToHex(557856811) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856811, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getEmergencyLaneAssistanceStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856810, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEmergencyLaneAssistanceStateSecond property: 0x");
            sb.append(tenToHex(557856810));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getFrontTrafficBrakeAlertStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856832, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFrontTrafficBrakeAlertStateR property: 0x");
            sb.append(tenToHex(557856832));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setFrontTrafficBrakeAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setFrontTrafficBrakeAlertStateS property: 0x" + tenToHex(557856833) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856833, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getFrontTrafficCrossAlertStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856830, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFrontTrafficCrossAlertStateR property: 0x");
            sb.append(tenToHex(557856830));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setFrontTrafficCrossAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setFrontTrafficCrossAlertStateS property: 0x" + tenToHex(557856831) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856831, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getHighSpeedAINavigationStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856768, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHighSpeedAINavigationStateR property: 0x");
            sb.append(tenToHex(557856768));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setHighSpeedAINavigationStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setHighSpeedAINavigationStateS property: 0x" + tenToHex(557856769) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856769, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIntelligentSpeedLimitAlertStateFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856789, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentSpeedLimitAlertStateFirst property: 0x");
            sb.append(tenToHex(557856789));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setIntelligentSpeedLimitAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setIntelligentSpeedLimitAlertStateS property: 0x" + tenToHex(557856791) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856791, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIntelligentSpeedLimitAlertStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856790, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentSpeedLimitAlertStateSecond property: 0x");
            sb.append(tenToHex(557856790));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setIntelligentSpeedLimitControlStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setIntelligentSpeedLimitControlStateS property: 0x" + tenToHex(557856793) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856793, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIntelligentSpeedLimitControlStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856792, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentSpeedLimitControlStateSecond property: 0x");
            sb.append(tenToHex(557856792));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIntelligentSpeedLimitControlStateFirst() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856839, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentSpeedLimitControlStateFirst property: 0x");
            sb.append(tenToHex(557856839));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getInteractiveLaneChangeAssistanceStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856774, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInteractiveLaneChangeAssistanceStateR property: 0x");
            sb.append(tenToHex(557856774));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setInteractiveLaneChangeAssistanceStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setInteractiveLaneChangeAssistanceStateS property: 0x" + tenToHex(557856775) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856775, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLaneAssistTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856778, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLaneAssistTypeR property: 0x");
            sb.append(tenToHex(557856778));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setLaneAssistTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setLaneAssistTypeS property: 0x" + tenToHex(557856779) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856779, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getMistakeGasStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856798, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMistakeGasStateR property: 0x");
            sb.append(tenToHex(557856798));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setMistakeGasStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setMistakeGasStateS property: 0x" + tenToHex(557856799) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856799, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getOpenDoorAlertStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856815, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOpenDoorAlertStateR property: 0x");
            sb.append(tenToHex(557856815));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setOpenDoorAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setOpenDoorAlertStateS property: 0x" + tenToHex(557856816) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856816, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getPredictCrashAlertModeRFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856827, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPredictCrashAlertModeRFirst property: 0x");
            sb.append(tenToHex(557856827));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getPredictCrashAlertModeRSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856828, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPredictCrashAlertModeRSecond property: 0x");
            sb.append(tenToHex(557856828));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setPredictCrashAlertModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setPredictCrashAlertModeS property: 0x" + tenToHex(557856829) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856829, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getPredictCrashAlertStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856825, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPredictCrashAlertStateR property: 0x");
            sb.append(tenToHex(557856825));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setPredictCrashAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setPredictCrashAlertStateS property: 0x" + tenToHex(557856826) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856826, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getRearCrashAlertStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856817, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearCrashAlertStateR property: 0x");
            sb.append(tenToHex(557856817));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setRearCrashAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setRearCrashAlertStateS property: 0x" + tenToHex(557856818) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856818, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getRearTrafficBrakeAlertStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856821, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearTrafficBrakeAlertStateR property: 0x");
            sb.append(tenToHex(557856821));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setRearTrafficBrakeAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setRearTrafficBrakeAlertStateS property: 0x" + tenToHex(557856822) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856822, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getRearTrafficCrossAlertStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856819, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearTrafficCrossAlertStateR property: 0x");
            sb.append(tenToHex(557856819));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setRearTrafficCrossAlertStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setRearTrafficCrossAlertStateS property: 0x" + tenToHex(557856820) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856820, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getSpeedLimitRangeFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856786, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSpeedLimitRangeFirst property: 0x");
            sb.append(tenToHex(557856786));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setSpeedLimitRangeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setSpeedLimitRangeS property: 0x" + tenToHex(557856788) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856788, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getSpeedLimitRangeSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856787, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSpeedLimitRangeSecond property: 0x");
            sb.append(tenToHex(557856787));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getSteepDescentSlowDownStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856835, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteepDescentSlowDownStateR property: 0x");
            sb.append(tenToHex(557856835));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setSteepDescentSlowDownStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setSteepDescentSlowDownStateS property: 0x" + tenToHex(557856836) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856836, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setLdwStaticCalibrationS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setLdwStaticCalibrationS value = " + value + " property: 0x" + tenToHex(557856887));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856887, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficLightRecognizeStateFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856806, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficLightRecognizeStateFirst property: 0x");
            sb.append(tenToHex(557856806));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setTrafficLightRecognizeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setTrafficLightRecognizeStateS property: 0x" + tenToHex(557856808) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856808, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficLightRecognizeStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870505, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficLightRecognizeStateSecond property: 0x");
            sb.append(tenToHex(557870505));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficSignRecognizeStateFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856782, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficSignRecognizeStateFirst property: 0x");
            sb.append(tenToHex(557856782));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficSignRecognizeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856784, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficSignRecognizeStateR property: 0x");
            sb.append(tenToHex(557856784));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setTrafficSignRecognizeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setTrafficSignRecognizeStateS property: 0x" + tenToHex(557856785) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856785, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficSignRecognizeStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856783, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrafficSignRecognizeStateSecond property: 0x");
            sb.append(tenToHex(557856783));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getUrbanAIAssistanceStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856770, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUrbanAIAssistanceStateR property: 0x");
            sb.append(tenToHex(557856770));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setUrbanAIAssistanceStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setUrbanAIAssistanceStateS property: 0x" + tenToHex(557856771) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856771, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getVerifyModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856794, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVerifyModeR property: 0x");
            sb.append(tenToHex(557856794));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setVerifyModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setVerifyModeS property: 0x" + tenToHex(557856795) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856795, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLdwSensitivityR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872897, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLdwSensitivityR property: 0x");
            sb.append(tenToHex(557872897));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDipilotManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAdaptiveFrontLightFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856917, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAdaptiveFrontLightFaultR value = " + value + " property: 0x" + tenToHex(557856917));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAdativeCruiseControlFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856897, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAdativeCruiseControlFaultR value = " + value + " property: 0x" + tenToHex(557856897));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAutoEmergencyFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856910, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAutoEmergencyFaultR value = " + value + " property: 0x" + tenToHex(557856910));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getBsisFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856923, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getBsisFaultR value = " + value + " property: 0x" + tenToHex(557856923));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getBlindFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856899, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getBlindFaultR value = " + value + " property: 0x" + tenToHex(557856899));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getCurveWarnFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856922, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getCurveWarnFaultR value = " + value + " property: 0x" + tenToHex(557856922));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getDoorOpenFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856900, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getDoorOpenFaultR value = " + value + " property: 0x" + tenToHex(557856900));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getEmergencyLaneKeepFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856911, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getEmergencyLaneKeepFaultR value = " + value + " property: 0x" + tenToHex(557856911));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getForwardCollisionWarnFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856916, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getForwardCollisionWarnFaultR value = " + value + " property: 0x" + tenToHex(557856916));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getFrontCrossAlarkFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856904, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getFrontCrossAlarkFaultR value = " + value + " property: 0x" + tenToHex(557856904));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getFrontCrossBrakeFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856905, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getFrontCrossBrakeFaultR value = " + value + " property: 0x" + tenToHex(557856905));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getHighNaviFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856908, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getHighNaviFaultR value = " + value + " property: 0x" + tenToHex(557856908));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIntelligentLimitFaultCallR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856920, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getIntelligentLimitFaultCallR value = " + value + " property: 0x" + tenToHex(557856920));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIntelligentLimitFaultControllerR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856912, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getIntelligentLimitFaultControllerR value = " + value + " property: 0x" + tenToHex(557856912));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getInteractiveLaneChangeFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856906, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getInteractiveLaneChangeFaultR value = " + value + " property: 0x" + tenToHex(557856906));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLaneCenteringControlFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856898, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getLaneCenteringControlFaultR value = " + value + " property: 0x" + tenToHex(557856898));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLaneDeparturePreventionFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856913, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getLaneDeparturePreventionFaultR value = " + value + " property: 0x" + tenToHex(557856913));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLaneDepartureWarnFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856914, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getLaneDepartureWarnFaultR value = " + value + " property: 0x" + tenToHex(557856914));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLaneKeepFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856915, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getLaneKeepFaultR value = " + value + " property: 0x" + tenToHex(557856915));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getLimitSpeedChangeFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856921, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getLimitSpeedChangeFaultR value = " + value + " property: 0x" + tenToHex(557856921));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getMoisFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856924, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getMoisFaultR value = " + value + " property: 0x" + tenToHex(557856924));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getNavigationPilotFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856909, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getNavigationPilotFaultR value = " + value + " property: 0x" + tenToHex(557856909));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getNrbanNaviFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856907, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getNrbanNaviFaultR value = " + value + " property: 0x" + tenToHex(557856907));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getRearCollisionWarnFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856903, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getRearCollisionWarnFaultR value = " + value + " property: 0x" + tenToHex(557856903));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getRearCrossAlartFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856901, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getRearCrossAlartFaultR value = " + value + " property: 0x" + tenToHex(557856901));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getRearCrossBrakeFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856902, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getRearCrossBrakeFaultR value = " + value + " property: 0x" + tenToHex(557856902));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTrafficAttentionFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856918, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getTrafficAttentionFaultR value = " + value + " property: 0x" + tenToHex(557856918));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getTsrFaultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856919, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getTsrFaultR value = " + value + " property: 0x" + tenToHex(557856919));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAutoParkigAssistModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDipilotManager.ID_AUTO_PARKIG_ASSIST_MODE_R, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAutoParkigAssistModeR value = " + value + " property: 0x" + tenToHex(CarVendorDipilotManager.ID_AUTO_PARKIG_ASSIST_MODE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAutoParkigAssistStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDipilotManager.ID_AUTO_PARKIG_ASSIST_STATUS_R, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAutoParkigAssistStatusR value = " + value + " property: 0x" + tenToHex(CarVendorDipilotManager.ID_AUTO_PARKIG_ASSIST_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getHomeZoneParkingAssistStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDipilotManager.ID_HOME_ZONE_PARKING_ASSIST_STATUS_R, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getHomeZoneParkingAssistStatusR value = " + value + " property: 0x" + tenToHex(CarVendorDipilotManager.ID_HOME_ZONE_PARKING_ASSIST_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAutomatedValetParkingStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDipilotManager.ID_AUTOMATED_VALET_PARKING_STATUS_R, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAutomatedValetParkingStatusR value = " + value + " property: 0x" + tenToHex(CarVendorDipilotManager.ID_AUTOMATED_VALET_PARKING_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getIsliWarningLevelSwitchStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856929, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getIsliWarningLevelSwitchStatusR value = " + value + " property: 0x" + tenToHex(557856929));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setIsliWarningLevelSwitchStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setIsliWarningLevelSwitchStatusS property: 0x" + tenToHex(CarVendorDipilotManager.ID_I_S_L_I_WARNING_LEVEL_SWITCH_STATUS_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorDipilotManager.ID_I_S_L_I_WARNING_LEVEL_SWITCH_STATUS_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public int getAutoParkingStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856931, 0);
            Log.debug(CarVendorDipilotManager.TAG, "getAutoParkingStateR value = " + value + " property: 0x" + tenToHex(557856931));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void setAutoParkingStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDipilotManager.TAG, "setAutoParkingStateS property: 0x" + tenToHex(CarVendorDipilotManager.ID_AUTO_PARKING_STATE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorDipilotManager.ID_AUTO_PARKING_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void addCarDipilotEventListener(CarVendorDipilotManager.OnCarDipilotEventListener listener) throws CarNotConnectedException {
        OnCarDipilotChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarDipilotChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorDipilotManager.TAG, "addCarDipilotEventListener propId size: " + this.mExtDipilotIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtDipilotIds) {
                int propId = num.intValue();
                Log.debug(CarVendorDipilotManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager
    public void removeCarDipilotEventListener(CarVendorDipilotManager.OnCarDipilotEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarDipilotChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorDipilotManager.TAG, "removeCarDipilotEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DipilotEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sDipilotEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorDipilotManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557845261:
                sDipilotEventChangeValue.setRemoteControlDriveCloseTops(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557845271:
                sDipilotEventChangeValue.setLowHODToastR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856106:
                sDipilotEventChangeValue.setSupportAiDistanceLight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856838:
                sDipilotEventChangeValue.setElecCarBodyStablySysStateChanged(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856839:
                sDipilotEventChangeValue.setIntelligentSpeedLimitControlStateFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856840:
                sDipilotEventChangeValue.setCpdStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856841:
                sDipilotEventChangeValue.setCpdStatusS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856842:
                sDipilotEventChangeValue.setCpdDelayStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856843:
                sDipilotEventChangeValue.setDmsSwitchStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856844:
                sDipilotEventChangeValue.setDmsSwitchStatusS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856845:
                sDipilotEventChangeValue.setAdasSoftVersionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856846:
                sDipilotEventChangeValue.setTsrLitSpdChgSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856847:
                sDipilotEventChangeValue.setTsrLitSpdChgSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856848:
                sDipilotEventChangeValue.setElkTakeOverStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856849:
                sDipilotEventChangeValue.setTsrLitSpdChgInformStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856850:
                sDipilotEventChangeValue.setIsliWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856851:
                sDipilotEventChangeValue.setLowSpeedEmergencyBrakingStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856852:
                sDipilotEventChangeValue.setLowSpeedEmergencyBrakingStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856853:
                sDipilotEventChangeValue.setSideDistanceWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856854:
                sDipilotEventChangeValue.setSideDistanceWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856855:
                sDipilotEventChangeValue.setCollisionWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856856:
                sDipilotEventChangeValue.setCollisionWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856857:
                sDipilotEventChangeValue.setIntersectionCollisionAlertStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856858:
                sDipilotEventChangeValue.setIntersectionCollisionAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856859:
                sDipilotEventChangeValue.setDoNotPassWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856860:
                sDipilotEventChangeValue.setDoNotPassWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856861:
                sDipilotEventChangeValue.setEmergencyBrakeWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856862:
                sDipilotEventChangeValue.setEmergencyBrakeWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856863:
                sDipilotEventChangeValue.setTrafficIncidentWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856864:
                sDipilotEventChangeValue.setTrafficIncidentWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856865:
                sDipilotEventChangeValue.setAbnormalVehicleWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856866:
                sDipilotEventChangeValue.setAbnormalVehicleWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856867:
                sDipilotEventChangeValue.setControlLossWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856868:
                sDipilotEventChangeValue.setControlLossWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856869:
                sDipilotEventChangeValue.setTrafficJamWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856870:
                sDipilotEventChangeValue.setTrafficJamWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856871:
                sDipilotEventChangeValue.setEmergencyVehicleWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856872:
                sDipilotEventChangeValue.setEmergencyVehicleWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856873:
                sDipilotEventChangeValue.setTrafficSignalWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856874:
                sDipilotEventChangeValue.setTrafficSignalWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856875:
                sDipilotEventChangeValue.setTrafficLightInfoPushStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856876:
                sDipilotEventChangeValue.setTrafficLightInfoPushStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856877:
                sDipilotEventChangeValue.setRedLightViolationWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856878:
                sDipilotEventChangeValue.setRedLightViolationWarningStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856879:
                sDipilotEventChangeValue.setGreenLightOptimalSpeedAdvisoryStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856880:
                sDipilotEventChangeValue.setGreenLightOptimalSpeedAdvisoryStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856881:
                sDipilotEventChangeValue.setFatigueMonitoringStatusS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856882:
                sDipilotEventChangeValue.setDmsSelfCheckStatusS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856883:
                sDipilotEventChangeValue.setDistractionMonitoringAttentionS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856884:
                sDipilotEventChangeValue.setMechanismPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856885:
                sDipilotEventChangeValue.setFatigueReliefSwitchS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856886:
                sDipilotEventChangeValue.setDangerousActionfSwitchS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856887:
                sDipilotEventChangeValue.setLdwStaticCalibrationS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856888:
                sDipilotEventChangeValue.setFatigueMonitorChangedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856889:
                sDipilotEventChangeValue.setBsisEnableR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856890:
                sDipilotEventChangeValue.setBsisCollicionAlarmSwitchS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856891:
                sDipilotEventChangeValue.setBsisFuncStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856892:
                sDipilotEventChangeValue.setMoisEnableR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856893:
                sDipilotEventChangeValue.setMoisFunctionSwitchS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856894:
                sDipilotEventChangeValue.setMoisFuncStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856895:
                sDipilotEventChangeValue.setSteepDescentSlowDownState4A9R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856896:
                sDipilotEventChangeValue.setCpdStatusNewR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856897:
                sDipilotEventChangeValue.setAdativeCruiseControlFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856898:
                sDipilotEventChangeValue.setLaneCenteringControlFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856899:
                sDipilotEventChangeValue.setBlindFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856900:
                sDipilotEventChangeValue.setDoorOpenFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856901:
                sDipilotEventChangeValue.setRearCrossAlartFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856902:
                sDipilotEventChangeValue.setRearCrossBrakeFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856903:
                sDipilotEventChangeValue.setRearCollisionWarnFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856904:
                sDipilotEventChangeValue.setFrontCrossAlarkFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856905:
                sDipilotEventChangeValue.setFrontCrossBrakeFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856906:
                sDipilotEventChangeValue.setInteractiveLaneChangeFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856907:
                sDipilotEventChangeValue.setNrbanNaviFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856908:
                sDipilotEventChangeValue.setHighNaviFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856909:
                sDipilotEventChangeValue.setNavigationPilotFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856910:
                sDipilotEventChangeValue.setAutoEmergencyFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856911:
                sDipilotEventChangeValue.setEmergencyLaneKeepFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856912:
                sDipilotEventChangeValue.setIntelligentLimitFaultControllerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856913:
                sDipilotEventChangeValue.setLaneDeparturePreventionFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856914:
                sDipilotEventChangeValue.setLaneDepartureWarnFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856915:
                sDipilotEventChangeValue.setLaneKeepFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856916:
                sDipilotEventChangeValue.setForwardCollisionWarnFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856917:
                sDipilotEventChangeValue.setAdaptiveFrontLightFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856918:
                sDipilotEventChangeValue.setTrafficAttentionFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856919:
                sDipilotEventChangeValue.setTsrFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856920:
                sDipilotEventChangeValue.setIntelligentLimitFaultCallR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856921:
                sDipilotEventChangeValue.setLimitSpeedChangeFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856922:
                sDipilotEventChangeValue.setCurveWarnFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856923:
                sDipilotEventChangeValue.setBsisFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856924:
                sDipilotEventChangeValue.setMoisFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorDipilotManager.ID_AUTO_PARKIG_ASSIST_MODE_R /* 557856925 */:
                sDipilotEventChangeValue.setAutoParkigAssistModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorDipilotManager.ID_AUTO_PARKIG_ASSIST_STATUS_R /* 557856926 */:
                sDipilotEventChangeValue.setAutoParkigAssistStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorDipilotManager.ID_HOME_ZONE_PARKING_ASSIST_STATUS_R /* 557856927 */:
                sDipilotEventChangeValue.setHomeZoneParkingAssistStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorDipilotManager.ID_AUTOMATED_VALET_PARKING_STATUS_R /* 557856928 */:
                sDipilotEventChangeValue.setAutomatedValetParkingStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856929:
                sDipilotEventChangeValue.setIsliWarningLevelSwitchStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856931:
                sDipilotEventChangeValue.setAutoParkingStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870336:
                sDipilotEventChangeValue.setDrivingVoiceBroadcastSwitchConfigTwoR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870400:
                sDipilotEventChangeValue.setHighHODToastR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870413:
                sDipilotEventChangeValue.setAebDecActiveR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870415:
                sDipilotEventChangeValue.setLksFaultStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870416:
                sDipilotEventChangeValue.setLdwFaultStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870437:
                sDipilotEventChangeValue.setDnpStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870469:
                sDipilotEventChangeValue.setAebFaultStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870470:
                sDipilotEventChangeValue.setFcwFaultStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870471:
                sDipilotEventChangeValue.setVruFaultStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870472:
                sDipilotEventChangeValue.setAiDistanceLightStateFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870505:
                sDipilotEventChangeValue.setTrafficLightRecognizeStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557872897:
                sDipilotEventChangeValue.setLdwSensitivityR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557856077:
                        sDipilotEventChangeValue.setSupportIntelligentNavigationFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856078:
                        sDipilotEventChangeValue.setSupportIntelligentNavigationSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856079:
                        sDipilotEventChangeValue.setSupportHighwayNavigation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856080:
                        sDipilotEventChangeValue.setSupportUrbanNavigation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856081:
                        sDipilotEventChangeValue.setSupportEndToEndNavigation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856082:
                        sDipilotEventChangeValue.setSupportLaneChangeAssist(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856083:
                        sDipilotEventChangeValue.setSupportLaneDepartureWarning(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856084:
                        sDipilotEventChangeValue.setSupportLaneKeep(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856085:
                        sDipilotEventChangeValue.setSupportLaneDepartureStop(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856086:
                        sDipilotEventChangeValue.setSupportEmergencyLaneKeepAssist(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856087:
                        sDipilotEventChangeValue.setSupportTrafficSignRecogniton(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856088:
                        sDipilotEventChangeValue.setSupportIntellignetSpeedLimitAlert(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856089:
                        sDipilotEventChangeValue.setSupportIntellignetSpeedLimitControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856090:
                        sDipilotEventChangeValue.setSupportTrafficLightAttention(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557856094:
                                sDipilotEventChangeValue.setSupportFrontCrossingTrafficAlert(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856095:
                                sDipilotEventChangeValue.setSupportFrontCrossingTrafficBrake(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557856097:
                                        sDipilotEventChangeValue.setSupportPredictCrashAlertModeFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856098:
                                        sDipilotEventChangeValue.setSupportPredictCrashAlertModeSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856099:
                                        sDipilotEventChangeValue.setSupportAiEmergencyBrake(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557856768:
                                                sDipilotEventChangeValue.setHighSpeedAINavigationStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856769:
                                                sDipilotEventChangeValue.setHighSpeedAINavigationStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856770:
                                                sDipilotEventChangeValue.setUrbanAIAssistanceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856771:
                                                sDipilotEventChangeValue.setUrbanAIAssistanceStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856772:
                                                sDipilotEventChangeValue.setE2EAIAssistanceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856773:
                                                sDipilotEventChangeValue.setE2EAIAssistanceStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856774:
                                                sDipilotEventChangeValue.setInteractiveLaneChangeAssistanceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856775:
                                                sDipilotEventChangeValue.setInteractiveLaneChangeAssistanceStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856776:
                                                sDipilotEventChangeValue.setAINavigationStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856777:
                                                sDipilotEventChangeValue.setAINavigationStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856778:
                                                sDipilotEventChangeValue.setLaneAssistTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856779:
                                                sDipilotEventChangeValue.setLaneAssistTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856780:
                                                sDipilotEventChangeValue.setAlarmTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856781:
                                                sDipilotEventChangeValue.setAlarmTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856782:
                                                sDipilotEventChangeValue.setTrafficSignRecognizeStateFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856783:
                                                sDipilotEventChangeValue.setTrafficSignRecognizeStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856784:
                                                sDipilotEventChangeValue.setTrafficSignRecognizeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856785:
                                                sDipilotEventChangeValue.setTrafficSignRecognizeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856786:
                                                sDipilotEventChangeValue.setSpeedLimitRangeFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856787:
                                                sDipilotEventChangeValue.setSpeedLimitRangeSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856788:
                                                sDipilotEventChangeValue.setSpeedLimitRangeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856789:
                                                sDipilotEventChangeValue.setIntelligentSpeedLimitAlertStateFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856790:
                                                sDipilotEventChangeValue.setIntelligentSpeedLimitAlertStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856791:
                                                sDipilotEventChangeValue.setIntelligentSpeedLimitAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856792:
                                                sDipilotEventChangeValue.setIntelligentSpeedLimitControlStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856793:
                                                sDipilotEventChangeValue.setIntelligentSpeedLimitControlStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856794:
                                                sDipilotEventChangeValue.setVerifyModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856795:
                                                sDipilotEventChangeValue.setVerifyModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856796:
                                                sDipilotEventChangeValue.setAiCareStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856797:
                                                sDipilotEventChangeValue.setAiCareStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856798:
                                                sDipilotEventChangeValue.setMistakeGasStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856799:
                                                sDipilotEventChangeValue.setMistakeGasStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856800:
                                                sDipilotEventChangeValue.setAiMatrixLightStateFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856801:
                                                sDipilotEventChangeValue.setAiMatrixLightStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856802:
                                                sDipilotEventChangeValue.setAiMatrixLightStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case 557856804:
                                                        sDipilotEventChangeValue.setAiDistanceLightStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856805:
                                                        sDipilotEventChangeValue.setAiDistanceLightStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856806:
                                                        sDipilotEventChangeValue.setTrafficLightRecognizeStateFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case 557856808:
                                                                sDipilotEventChangeValue.setTrafficLightRecognizeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856809:
                                                                sDipilotEventChangeValue.setEmergencyLaneAssistanceStateFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856810:
                                                                sDipilotEventChangeValue.setEmergencyLaneAssistanceStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856811:
                                                                sDipilotEventChangeValue.setEmergencyLaneAssistanceStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856812:
                                                                sDipilotEventChangeValue.setBlindMonitorStateFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856813:
                                                                sDipilotEventChangeValue.setBlindMonitorStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856814:
                                                                sDipilotEventChangeValue.setBlindMonitorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856815:
                                                                sDipilotEventChangeValue.setOpenDoorAlertStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856816:
                                                                sDipilotEventChangeValue.setOpenDoorAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856817:
                                                                sDipilotEventChangeValue.setRearCrashAlertStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856818:
                                                                sDipilotEventChangeValue.setRearCrashAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856819:
                                                                sDipilotEventChangeValue.setRearTrafficCrossAlertStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856820:
                                                                sDipilotEventChangeValue.setRearTrafficCrossAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856821:
                                                                sDipilotEventChangeValue.setRearTrafficBrakeAlertStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856822:
                                                                sDipilotEventChangeValue.setRearTrafficBrakeAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856823:
                                                                sDipilotEventChangeValue.setAiEmergencyBrakeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856824:
                                                                sDipilotEventChangeValue.setAiEmergencyBrakeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856825:
                                                                sDipilotEventChangeValue.setPredictCrashAlertStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856826:
                                                                sDipilotEventChangeValue.setPredictCrashAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856827:
                                                                sDipilotEventChangeValue.setPredictCrashAlertModeRFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856828:
                                                                sDipilotEventChangeValue.setPredictCrashAlertModeRSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856829:
                                                                sDipilotEventChangeValue.setPredictCrashAlertModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856830:
                                                                sDipilotEventChangeValue.setFrontTrafficCrossAlertStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856831:
                                                                sDipilotEventChangeValue.setFrontTrafficCrossAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856832:
                                                                sDipilotEventChangeValue.setFrontTrafficBrakeAlertStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856833:
                                                                sDipilotEventChangeValue.setFrontTrafficBrakeAlertStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856834:
                                                                sDipilotEventChangeValue.setElecCarBodyStablySysStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856835:
                                                                sDipilotEventChangeValue.setSteepDescentSlowDownStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856836:
                                                                sDipilotEventChangeValue.setSteepDescentSlowDownStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case 557856933:
                                                                        sDipilotEventChangeValue.setCpdWarningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856934:
                                                                        sDipilotEventChangeValue.setCpdWorkingStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856935:
                                                                        sDipilotEventChangeValue.setEspOffKeyStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856936:
                                                                        sDipilotEventChangeValue.setIOPStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case CarVendorDipilotManager.ID_I_O_P_STATE_S /* 557856937 */:
                                                                        sDipilotEventChangeValue.setIOPStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856938:
                                                                        sDipilotEventChangeValue.setSAOCStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case CarVendorDipilotManager.ID_S_A_O_C_STATE_S /* 557856939 */:
                                                                        sDipilotEventChangeValue.setSAOCStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856940:
                                                                        sDipilotEventChangeValue.setSLIFStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case CarVendorDipilotManager.ID_S_L_I_F_STATE_S /* 557856941 */:
                                                                        sDipilotEventChangeValue.setSLIFStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        switch (propertyId) {
                                                                            case 557856944:
                                                                                sDipilotEventChangeValue.setLowIOPToastR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557856945:
                                                                                sDipilotEventChangeValue.setHighIOPToastR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557856946:
                                                                                sDipilotEventChangeValue.setAccStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            default:
                                                                                switch (propertyId) {
                                                                                    case 557856948:
                                                                                        sDipilotEventChangeValue.setFcsrStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557856949:
                                                                                        sDipilotEventChangeValue.setFcsrStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557856950:
                                                                                        sDipilotEventChangeValue.setFcsrFaultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    default:
                                                                                        Log.debug(CarVendorDipilotManager.TAG, "break other properties ,except dipilot");
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
        Log.debug(CarVendorDipilotManager.TAG, "convertCarVendorPropertyValue " + sDipilotEventChangeValue.toString(), DEBUG_LOGGER);
        return sDipilotEventChangeValue;
    }

    private OnCarDipilotChangeEventListenerProxy findListenerLocked(CarVendorDipilotManager.OnCarDipilotEventListener listener) {
        for (OnCarDipilotChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarDipilotChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorDipilotManager.OnCarDipilotEventListener listener;

        public OnCarDipilotChangeEventListenerProxy(CarVendorDipilotManager.OnCarDipilotEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorDipilotMgr.this.traceCallbackPrepare();
                DipilotEventChangeValue newValue = CarVendorDipilotMgr.convertCarVendorProperty(value);
                CarVendorDipilotMgr.this.traceCallbackBefore();
                this.listener.onDipilotEventChange(newValue);
                CarVendorDipilotMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorDipilotManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorDipilotManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onDipilotErrorEvent(propertyId, zone);
        }
    }
}
