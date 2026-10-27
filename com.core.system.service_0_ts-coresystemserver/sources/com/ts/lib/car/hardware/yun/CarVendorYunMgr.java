package com.ts.lib.car.hardware.yun;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.yun.CarVendorYunManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorYunMgr extends CarVendorManagerBase implements CarVendorYunManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static YunEventChangeValue sYunEventChangeValue = new YunEventChangeValue();
    private final LinkedList<OnCarYunChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtYunIds = {557846055, 557846058, 557846056, 557846057, 557846051, 557846062, 557846063, 557846053, 557846054, 557846052, 557846050, 557846048, 557846046, 557846061, 557846060, 557846044, 557846049, 557846047, 557846065, 557846064, 557846067, 557846066, 557846059, 560991773, 560991771};

    public CarVendorYunMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorYunManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorYunManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorYunManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorYunManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getACHMCACFailureCause() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846056, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACHMCACFailureCause property: 0x");
            sb.append(tenToHex(557846056));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getACHMCACOpenRsltFdbckInfor() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846055, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACHMCACOpenRsltFdbckInfor property: 0x");
            sb.append(tenToHex(557846055));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getACHMCACShutdownFailReason() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846058, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACHMCACShutdownFailReason property: 0x");
            sb.append(tenToHex(557846058));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getAVCloseResultsFeedback() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846057, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAVCloseResultsFeedback property: 0x");
            sb.append(tenToHex(557846057));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getContrlResultsFBInfo497s16() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846051, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getContrlResultsFBInfo497s16 property: 0x");
            sb.append(tenToHex(557846051));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getCopilotSeatHeatStat4FA() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846063, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCopilotSeatHeatStat4FA property: 0x");
            sb.append(tenToHex(557846063));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getCopilotSeatVtilatStat4FA() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846062, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCopilotSeatVtilatStat4FA property: 0x");
            sb.append(tenToHex(557846062));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getEngStartResultFdbkInfo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846053, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngStartResultFdbkInfo property: 0x");
            sb.append(tenToHex(557846053));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getEngStopResultFdbkInfo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846054, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngStopResultFdbkInfo property: 0x");
            sb.append(tenToHex(557846054));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getFailureReasons497s16() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846052, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFailureReasons497s16 property: 0x");
            sb.append(tenToHex(557846052));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getFunctionNumber497() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846050, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFunctionNumber497 property: 0x");
            sb.append(tenToHex(557846050));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getLeftBackDoor() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846048, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftBackDoor property: 0x");
            sb.append(tenToHex(557846048));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getLeftFrontDoor() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846046, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftFrontDoor property: 0x");
            sb.append(tenToHex(557846046));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getPilotSeatHeatStat() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846061, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPilotSeatHeatStat property: 0x");
            sb.append(tenToHex(557846061));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getPilotSeatVtilatStat() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846060, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPilotSeatVtilatStat property: 0x");
            sb.append(tenToHex(557846060));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getRLSeatHeatStat486() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846065, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRLSeatHeatStat486 property: 0x");
            sb.append(tenToHex(557846065));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getRLSeatVtilatStat486() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846064, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRLSeatVtilatStat486 property: 0x");
            sb.append(tenToHex(557846064));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRLeftSeatHeatStat(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRLeftSeatHeatStat property: 0x" + tenToHex(557846038) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846038, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRLeftSeatVtilatStat(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRLeftSeatVtilatStat property: 0x" + tenToHex(557846037) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846037, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getRRSeatHeatStat486() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846067, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRRSeatHeatStat486 property: 0x");
            sb.append(tenToHex(557846067));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getRRSeatVtilatStat486() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846066, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRRSeatVtilatStat486 property: 0x");
            sb.append(tenToHex(557846066));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRRightSeatHeatStat(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRRightSeatHeatStat property: 0x" + tenToHex(557846040) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846040, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRRightSeatVtilatStat(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRRightSeatVtilatStat property: 0x" + tenToHex(557846039) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846039, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getRemainBattery() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846044, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemainBattery property: 0x");
            sb.append(tenToHex(557846044));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteAcControlCycleMode(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteAcControlCycleMode property: 0x" + tenToHex(557846031) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846031, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteAcControlDriverTemperature(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteAcControlDriverTemperature property: 0x" + tenToHex(557846025) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846025, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteAcControlManner(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteAcControlManner property: 0x" + tenToHex(557846030) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846030, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteAcControlStart(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteAcControlStart property: 0x" + tenToHex(557846024) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846024, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteAcControlStartTime(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteAcControlStartTime property: 0x" + tenToHex(557846026) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846026, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteAcControlSwitch(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteAcControlSwitch property: 0x" + tenToHex(557846029) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846029, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteAcControlWindPattern(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteAcControlWindPattern property: 0x" + tenToHex(557846028) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846028, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteControl33S(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteControl33S property: 0x" + tenToHex(557846041) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846041, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteControlLockout(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteControlLockout property: 0x" + tenToHex(557846018) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846018, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteControlSearchFlashingLight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteControlSearchFlashingLight property: 0x" + tenToHex(557846022) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846022, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteControlSearchFlashingLightsAndWhistle(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteControlSearchFlashingLightsAndWhistle property: 0x" + tenToHex(557846021) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846021, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteControlStartEngine(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteControlStartEngine property: 0x" + tenToHex(557846016) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846016, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteControlStopAc(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteControlStopAc property: 0x" + tenToHex(557846019) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846019, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteControlStopEngine(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteControlStopEngine property: 0x" + tenToHex(557846020) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846020, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteControlUnlockFourDoors(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteControlUnlockFourDoors property: 0x" + tenToHex(557846017) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846017, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteSeatControlCopilotHeat(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteSeatControlCopilotHeat property: 0x" + tenToHex(557846036) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846036, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteSeatControlCopilotVentilate(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteSeatControlCopilotVentilate property: 0x" + tenToHex(557846035) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846035, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteSeatControlDriverHeat(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteSeatControlDriverHeat property: 0x" + tenToHex(557846034) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846034, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteSeatControlDriverVentilate(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteSeatControlDriverVentilate property: 0x" + tenToHex(557846033) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846033, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteSeatControlFrontSeats(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteSeatControlFrontSeats property: 0x" + tenToHex(557846032) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846032, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteUnlockFourDoors(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemoteUnlockFourDoors property: 0x" + tenToHex(557846023) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846023, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setRemoteAcControlAirGear(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setRemtoeAcControlAirGear property: 0x" + tenToHex(557846027) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846027, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getRightBackDoor() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846049, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightBackDoor property: 0x");
            sb.append(tenToHex(557846049));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getRightFrontDoor() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846047, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightFrontDoor property: 0x");
            sb.append(tenToHex(557846047));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void setSteeringWheelHeat(int value) throws CarNotConnectedException {
        Log.debug(CarVendorYunManager.TAG, "setSteeringWheelHeat property: 0x" + tenToHex(557846042) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846042, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public int getSteeringWheelHeatingState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846059, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelHeatingState property: 0x");
            sb.append(tenToHex(557846059));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public byte[] getVehicleSpeed() throws CarNotConnectedException {
        try {
            CarPropertyValue<byte[]> value = this.mCarPropertyManager.getProperty(byte[].class, 560991773, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVehicleSpeed property: 0x");
            sb.append(tenToHex(560991773));
            sb.append(" class: byte[] value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public byte[] getWaterThermometer() throws CarNotConnectedException {
        try {
            CarPropertyValue<byte[]> value = this.mCarPropertyManager.getProperty(byte[].class, 560991771, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWaterThermometer property: 0x");
            sb.append(tenToHex(560991771));
            sb.append(" class: byte[] value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorYunManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void addCarYunEventListener(CarVendorYunManager.OnCarYunEventListener listener) throws CarNotConnectedException {
        OnCarYunChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarYunChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorYunManager.TAG, "addCarYunEventListener propId size: " + this.mExtYunIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtYunIds) {
                int propId = num.intValue();
                Log.debug(CarVendorYunManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.yun.CarVendorYunManager
    public void removeCarYunEventListener(CarVendorYunManager.OnCarYunEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarYunChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorYunManager.TAG, "removeCarYunEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static YunEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sYunEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorYunManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        if (propertyId == 557846044) {
            sYunEventChangeValue.setRemainBattery(CastArrayUtil.toPrimitive((Integer) value.getValue()));
        } else if (propertyId == 560991771) {
            sYunEventChangeValue.setWaterThermometer(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
        } else if (propertyId != 560991773) {
            switch (propertyId) {
                case 557846016:
                    sYunEventChangeValue.setRemoteControlStartEngine(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846017:
                    sYunEventChangeValue.setRemoteControlUnlockFourDoors(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846018:
                    sYunEventChangeValue.setRemoteControlLockout(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846019:
                    sYunEventChangeValue.setRemoteControlStopAc(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846020:
                    sYunEventChangeValue.setRemoteControlStopEngine(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846021:
                    sYunEventChangeValue.setRemoteControlSearchFlashingLightsAndWhistle(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846022:
                    sYunEventChangeValue.setRemoteControlSearchFlashingLight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846023:
                    sYunEventChangeValue.setRemoteUnlockFourDoors(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846024:
                    sYunEventChangeValue.setRemoteAcControlStart(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846025:
                    sYunEventChangeValue.setRemoteAcControlDriverTemperature(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846026:
                    sYunEventChangeValue.setRemoteAcControlStartTime(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846027:
                    sYunEventChangeValue.setRemtoeAcControlAirGear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846028:
                    sYunEventChangeValue.setRemoteAcControlWindPattern(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846029:
                    sYunEventChangeValue.setRemoteAcControlSwitch(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846030:
                    sYunEventChangeValue.setRemoteAcControlManner(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846031:
                    sYunEventChangeValue.setRemoteAcControlCycleMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846032:
                    sYunEventChangeValue.setRemoteSeatControlFrontSeats(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846033:
                    sYunEventChangeValue.setRemoteSeatControlDriverVentilate(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846034:
                    sYunEventChangeValue.setRemoteSeatControlDriverHeat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846035:
                    sYunEventChangeValue.setRemoteSeatControlCopilotVentilate(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846036:
                    sYunEventChangeValue.setRemoteSeatControlCopilotHeat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846037:
                    sYunEventChangeValue.setRLeftSeatVtilatStat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846038:
                    sYunEventChangeValue.setRLeftSeatHeatStat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846039:
                    sYunEventChangeValue.setRRightSeatVtilatStat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846040:
                    sYunEventChangeValue.setRRightSeatHeatStat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846041:
                    sYunEventChangeValue.setRemoteControl33S(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557846042:
                    sYunEventChangeValue.setSteeringWheelHeat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                default:
                    switch (propertyId) {
                        case 557846046:
                            sYunEventChangeValue.setLeftFrontDoor(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846047:
                            sYunEventChangeValue.setRightFrontDoor(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846048:
                            sYunEventChangeValue.setLeftBackDoor(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846049:
                            sYunEventChangeValue.setRightBackDoor(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846050:
                            sYunEventChangeValue.setFunctionNumber497(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846051:
                            sYunEventChangeValue.setContrlResultsFBInfo497s16(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846052:
                            sYunEventChangeValue.setFailureReasons497s16(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846053:
                            sYunEventChangeValue.setEngStartResultFdbkInfo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846054:
                            sYunEventChangeValue.setEngStopResultFdbkInfo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846055:
                            sYunEventChangeValue.setACHMCACOpenRsltFdbckInfor(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846056:
                            sYunEventChangeValue.setACHMCACFailureCause(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846057:
                            sYunEventChangeValue.setAVCloseResultsFeedback(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846058:
                            sYunEventChangeValue.setACHMCACShutdownFailReason(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846059:
                            sYunEventChangeValue.setSteeringWheelHeatingState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846060:
                            sYunEventChangeValue.setPilotSeatVtilatStat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846061:
                            sYunEventChangeValue.setPilotSeatHeatStat(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846062:
                            sYunEventChangeValue.setCopilotSeatVtilatStat4FA(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846063:
                            sYunEventChangeValue.setCopilotSeatHeatStat4FA(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846064:
                            sYunEventChangeValue.setRLSeatVtilatStat486(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846065:
                            sYunEventChangeValue.setRLSeatHeatStat486(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846066:
                            sYunEventChangeValue.setRRSeatVtilatStat486(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557846067:
                            sYunEventChangeValue.setRRSeatHeatStat486(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        default:
                            Log.debug(CarVendorYunManager.TAG, "break other properties ,except yun");
                            break;
                    }
                    break;
            }
        } else {
            sYunEventChangeValue.setVehicleSpeed(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
        }
        Log.debug(CarVendorYunManager.TAG, "convertCarVendorPropertyValue " + sYunEventChangeValue.toString(), DEBUG_LOGGER);
        return sYunEventChangeValue;
    }

    private OnCarYunChangeEventListenerProxy findListenerLocked(CarVendorYunManager.OnCarYunEventListener listener) {
        for (OnCarYunChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarYunChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorYunManager.OnCarYunEventListener listener;

        public OnCarYunChangeEventListenerProxy(CarVendorYunManager.OnCarYunEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorYunMgr.this.traceCallbackPrepare();
                YunEventChangeValue newValue = CarVendorYunMgr.convertCarVendorProperty(value);
                CarVendorYunMgr.this.traceCallbackBefore();
                this.listener.onYunEventChange(newValue);
                CarVendorYunMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorYunManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorYunManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onYunErrorEvent(propertyId, zone);
        }
    }
}
