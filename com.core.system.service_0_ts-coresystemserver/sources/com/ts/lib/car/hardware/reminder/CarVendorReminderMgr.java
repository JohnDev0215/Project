package com.ts.lib.car.hardware.reminder;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.reminder.CarVendorReminderManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorReminderMgr extends CarVendorManagerBase implements CarVendorReminderManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static ReminderEventChangeValue sReminderEventChangeValue = new ReminderEventChangeValue();
    private final LinkedList<OnCarReminderChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtReminderIds = {557856774, 557870438, 557860386, 557845271, 557845269, 557845270, 557845250, 557845256, 557845254, 557845257, 557845255, 557845266, 557845265, 557845263, 557845259, 557845260, 557859850, 557875227, 557845264, 557870596, 557845253, 559957505, 557857284, 557845249, 557845267, 557845261, 557845248, 557845272, 557862943, 557862944, 557862945, 557862946, 557845273, 557846612, 557845275, 557870400, 557862927, 557862947, 557862948, 557862953, Integer.valueOf(CarVendorReminderManager.ID_RMD_TAKE_PHONE)};

    public CarVendorReminderMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorReminderManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorReminderManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorReminderManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorReminderManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedAdas0x26CFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845272, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedAdas0x26CFirst property: 0x");
            sb.append(tenToHex(557845272));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getAccTextInfo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870400, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAccTextInfo property: 0x");
            sb.append(tenToHex(557870400));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedADAS0x29CFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856774, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedADAS0x29CFirst property: 0x");
            sb.append(tenToHex(557856774));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedADAS0x29CSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870438, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedADAS0x29CSecond property: 0x");
            sb.append(tenToHex(557870438));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedADAS0x316() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856776, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedADAS0x316 property: 0x");
            sb.append(tenToHex(557856776));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedADAS0x32D() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845271, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedADAS0x32D property: 0x");
            sb.append(tenToHex(557845271));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedBeCarefulCarsBackLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845269, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedBeCarefulCarsBackLeft property: 0x");
            sb.append(tenToHex(557845269));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedBeCarefulCarsBackRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845270, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedBeCarefulCarsBackRight property: 0x");
            sb.append(tenToHex(557845270));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedCloudServiceSignal() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845250, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedCloudServiceSignal property: 0x");
            sb.append(tenToHex(557845250));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedDoorNotClosedLeftBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845256, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedDoorNotClosedLeftBack property: 0x");
            sb.append(tenToHex(557845256));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedDoorNotClosedLeftFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845254, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedDoorNotClosedLeftFront property: 0x");
            sb.append(tenToHex(557845254));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedDoorNotClosedRightBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845257, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedDoorNotClosedRightBack property: 0x");
            sb.append(tenToHex(557845257));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedDoorNotClosedRightFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845255, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedDoorNotClosedRightFront property: 0x");
            sb.append(tenToHex(557845255));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedExternalDischargeTermination() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845266, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedExternalDischargeTermination property: 0x");
            sb.append(tenToHex(557845266));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedInsufficientFuel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845265, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedInsufficientFuel property: 0x");
            sb.append(tenToHex(557845265));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedKeyNotDetected() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845263, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedKeyNotDetected property: 0x");
            sb.append(tenToHex(557845263));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedLeaveTheVehicleAtNonPGearINS() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845259, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedLeaveTheVehicleAtNonPGearINS property: 0x");
            sb.append(tenToHex(557845259));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedLeaveTheVehicleAtNonPGearVTOG() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845260, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedLeaveTheVehicleAtNonPGearVTOG property: 0x");
            sb.append(tenToHex(557845260));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedLowBatteryVoltage() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875227, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedLowBatteryVoltage property: 0x");
            sb.append(tenToHex(557875227));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedLowkeyPower() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859850, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedLowkeyPower property: 0x");
            sb.append(tenToHex(557859850));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedNightRunningLights() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845264, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedNightRunningLights property: 0x");
            sb.append(tenToHex(557845264));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedNotWearingSeatBelt() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870596, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedNotWearingSeatBelt property: 0x");
            sb.append(tenToHex(557870596));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public float getonIndChangedParkingBrakeNotReleasedEpb() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 557845253, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedParkingBrakeNotReleasedEPB property: 0x");
            sb.append(tenToHex(557845253));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public float getonIndChangedParkingBrakeNotReleasedEsc() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559957505, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedParkingBrakeNotReleasedESC property: 0x");
            sb.append(tenToHex(559957505));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedParkingBrakeNotReleasedINS() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857284, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedParkingBrakeNotReleasedINS property: 0x");
            sb.append(tenToHex(557857284));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedPowerFailure() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845249, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedPowerFailure property: 0x");
            sb.append(tenToHex(557845249));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedPowerSystemFault() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845267, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedPowerSystemFault property: 0x");
            sb.append(tenToHex(557845267));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedRemoteDrivingSuspension() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845261, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedRemoteDrivingSuspension property: 0x");
            sb.append(tenToHex(557845261));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public int getonIndChangedstart() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845248, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonIndChangedstart property: 0x");
            sb.append(tenToHex(557845248));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorReminderManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public void addCarReminderEventListener(CarVendorReminderManager.OnCarReminderEventListener listener) throws CarNotConnectedException {
        OnCarReminderChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarReminderChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorReminderManager.TAG, "addCarReminderEventListener propId size: " + this.mExtReminderIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtReminderIds) {
                int propId = num.intValue();
                Log.debug(CarVendorReminderManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.reminder.CarVendorReminderManager
    public void removeCarReminderEventListener(CarVendorReminderManager.OnCarReminderEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarReminderChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorReminderManager.TAG, "removeCarReminderEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ReminderEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sReminderEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorReminderManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557845248:
                sReminderEventChangeValue.setonIndChangedstart(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557845249:
                sReminderEventChangeValue.setonIndChangedPowerFailure(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557845250:
                sReminderEventChangeValue.setonIndChangedCloudServiceSignal(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557845253:
                        sReminderEventChangeValue.setonIndChangedParkingBrakeNotReleasedEpb(CastArrayUtil.toPrimitive((Float) value.getValue()));
                        break;
                    case 557845254:
                        sReminderEventChangeValue.setonIndChangedDoorNotClosedLeftFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557845255:
                        sReminderEventChangeValue.setonIndChangedDoorNotClosedRightFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557845256:
                        sReminderEventChangeValue.setonIndChangedDoorNotClosedLeftBack(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557845257:
                        sReminderEventChangeValue.setonIndChangedDoorNotClosedRightBack(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557845259:
                                sReminderEventChangeValue.setonIndChangedLeaveTheVehicleAtNonPGearINS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557845260:
                                sReminderEventChangeValue.setonIndChangedLeaveTheVehicleAtNonPGearVTOG(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557845261:
                                sReminderEventChangeValue.setonIndChangedRemoteDrivingSuspension(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557845263:
                                        sReminderEventChangeValue.setonIndChangedKeyNotDetected(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557845264:
                                        sReminderEventChangeValue.setonIndChangedNightRunningLights(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557845265:
                                        sReminderEventChangeValue.setonIndChangedInsufficientFuel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557845266:
                                        sReminderEventChangeValue.setonIndChangedExternalDischargeTermination(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557845267:
                                        sReminderEventChangeValue.setonIndChangedPowerSystemFault(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557845269:
                                                sReminderEventChangeValue.setonIndChangedBeCarefulCarsBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557845270:
                                                sReminderEventChangeValue.setonIndChangedBeCarefulCarsBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557845271:
                                                sReminderEventChangeValue.setonIndChangedAdas0x32D(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557845272:
                                                sReminderEventChangeValue.setonIndChangedAdas0x26CFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557845273:
                                                sReminderEventChangeValue.setonIndChangedRmdRapidAccelerationLimit(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case 557845275:
                                                        sReminderEventChangeValue.setonIndChangedRmdRearAirConditioningUnlock(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case CarVendorReminderManager.ID_RMD_TAKE_PHONE /* 557845276 */:
                                                        sReminderEventChangeValue.setRmdTakePhone(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case 557846612:
                                                                sReminderEventChangeValue.setonIndChangedRmdRearAirConditioningOperateInvalid(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856774:
                                                                sReminderEventChangeValue.setonIndChangedAdas0x29CFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856776:
                                                                sReminderEventChangeValue.setonIndChangedAdas0x316(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857284:
                                                                sReminderEventChangeValue.setonIndChangedParkingBrakeNotReleasedINS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557859850:
                                                                sReminderEventChangeValue.setonIndChangedLowkeyPower(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557860386:
                                                                sReminderEventChangeValue.setSocValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557862927:
                                                                sReminderEventChangeValue.setOtaPowerLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557862953:
                                                                sReminderEventChangeValue.setLeftStrutMorotRunningState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557870400:
                                                                sReminderEventChangeValue.setAccTextInfo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557870438:
                                                                sReminderEventChangeValue.setonIndChangedAdas0x29CSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557870596:
                                                                sReminderEventChangeValue.setonIndChangedNotWearingSeatBelt(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557875227:
                                                                sReminderEventChangeValue.setonIndChangedLowBatteryVoltage(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 559957505:
                                                                sReminderEventChangeValue.setonIndChangedParkingBrakeNotReleasedEsc(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case 557862943:
                                                                        sReminderEventChangeValue.setDoorStateLeftFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557862944:
                                                                        sReminderEventChangeValue.setDoorStateRightFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557862945:
                                                                        sReminderEventChangeValue.setDoorStateLeftRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557862946:
                                                                        sReminderEventChangeValue.setDoorStateRightRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557862947:
                                                                        sReminderEventChangeValue.setDoorStateLuggage(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557862948:
                                                                        sReminderEventChangeValue.setDoorStateHood(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        Log.debug(CarVendorReminderManager.TAG, "break other properties ,except reminder");
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
        Log.debug(CarVendorReminderManager.TAG, "convertCarVendorPropertyValue " + sReminderEventChangeValue.toString(), DEBUG_LOGGER);
        return sReminderEventChangeValue;
    }

    private OnCarReminderChangeEventListenerProxy findListenerLocked(CarVendorReminderManager.OnCarReminderEventListener listener) {
        for (OnCarReminderChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarReminderChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorReminderManager.OnCarReminderEventListener listener;

        public OnCarReminderChangeEventListenerProxy(CarVendorReminderManager.OnCarReminderEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorReminderMgr.this.traceCallbackPrepare();
                ReminderEventChangeValue newValue = CarVendorReminderMgr.convertCarVendorProperty(value);
                CarVendorReminderMgr.this.traceCallbackBefore();
                this.listener.onReminderEventChange(newValue);
                CarVendorReminderMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorReminderManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorReminderManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onReminderErrorEvent(propertyId, zone);
        }
    }
}
