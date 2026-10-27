package com.ts.lib.car.hardware.digitalkey;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class CarVendorDigitalkeyMgr extends CarVendorManagerBase implements CarVendorDigitalkeyManager {
    private static final int VEHICLE_PROPERTY_CHANGE_MODE_REAL_TIME = 3;
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static DigitalkeyEventChangeValue sDigitalkeyEventChangeValue = new DigitalkeyEventChangeValue();
    private final LinkedList<OnCarDigitalkeyChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtDigitalkeyIds = {557846283, 557846282, 557846272, 557846285, 557846284, 557846287, 557846286, 557846281, 557846280, 557846277, 557846288, Integer.valueOf(CarVendorDigitalkeyManager.ID_NFC_OPERATION_COMMAND), Integer.valueOf(CarVendorDigitalkeyManager.ID_OPERATION_TYPE_R), Integer.valueOf(CarVendorDigitalkeyManager.ID_OPERATION_COMMAND_R), Integer.valueOf(CarVendorDigitalkeyManager.ID_OPERATION_RESULT_R), 557846293, 557846297, 557846303, 557846304, Integer.valueOf(CarVendorDigitalkeyManager.ID_OPERATION_COMMAND403_R), Integer.valueOf(CarVendorDigitalkeyManager.ID_OPERATION_TYPE403_R), Integer.valueOf(CarVendorDigitalkeyManager.ID_RESULTS_OF_ENFORCEMENT_R), Integer.valueOf(CarVendorDigitalkeyManager.ID_STATUS_OF_LOCK_SETTING_R), Integer.valueOf(CarVendorDigitalkeyManager.ID_STATUS_OF_UNLOCK_SETTING_R), 557846323, 557846321, 557846307, 557846309, 557846308, 557846317, 557846318, 557846316, Integer.valueOf(CarVendorDigitalkeyManager.ID_OPCODE_DATA_VALUE_R), 557846313, 557846314, 557846322, 557846319};

    public CarVendorDigitalkeyMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorDigitalkeyManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorDigitalkeyManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorDigitalkeyManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorDigitalkeyManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getBluetoothUnlockTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846323, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getBluetoothUnlockTypeR value = " + value + " property: 0x" + tenToHex(557846323));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setCurAccountSwitchFlagS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setCurAccountSwitchFlagS value = " + value + " property: 0x" + tenToHex(557846311));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846311, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getDrivingPasswordSettingConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846321, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getDrivingPasswordSettingConfigR value = " + value + " property: 0x" + tenToHex(557846321));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setDrivingPasswordSettingS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setDrivingPasswordSettingS value = " + value + " property: 0x" + tenToHex(557846305));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846305, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setMultiplexorS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setMultiplexorS value = " + value + " property: 0x" + tenToHex(557846324));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846324, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getNfcKeyStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846307, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getNfcKeyStatusR value = " + value + " property: 0x" + tenToHex(557846307));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getNfcKeyUnlockResultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846309, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getNfcKeyUnlockResultR value = " + value + " property: 0x" + tenToHex(557846309));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getNfcKeyUnlockTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846308, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getNfcKeyUnlockTypeR value = " + value + " property: 0x" + tenToHex(557846308));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOpcodeChecksumValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846317, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOpcodeChecksumValueR value = " + value + " property: 0x" + tenToHex(557846317));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOpcodeCommandR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846318, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOpcodeCommandR value = " + value + " property: 0x" + tenToHex(557846318));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOpcodeDataLengthR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846316, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOpcodeDataLengthR value = " + value + " property: 0x" + tenToHex(557846316));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setOpcodeDataToSecNfcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setOpcodeDataToSecNfcS value = " + value + " property: 0x" + tenToHex(557846328));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846328, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setOpcodeDataToSecOpcodeS(long value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setOpcodeDataToSecOpcodeS value = " + value + " property: 0x" + tenToHex(560992054));
        try {
            this.mCarPropertyManager.setProperty(Byte[].class, 560992054, 0, CastArrayUtil.toWrap(CastArrayUtil.longToBytes(value)));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setOpcodeDataToSecRandomS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setOpcodeDataToSecRandomS value = " + value + " property: 0x" + tenToHex(557846327));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846327, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setOpcodeDataToSecUserIdS(long value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setOpcodeDataToSecUserIdS value = " + value + " property: 0x" + tenToHex(560992053));
        try {
            this.mCarPropertyManager.setProperty(Byte[].class, 560992053, 0, CastArrayUtil.toWrap(CastArrayUtil.longToBytes(value)));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public byte[] getOpcodeDataValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Byte[]> value = this.mCarPropertyManager.getProperty(Byte[].class, CarVendorDigitalkeyManager.ID_OPCODE_DATA_VALUE_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOpcodeDataValueR value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_OPCODE_DATA_VALUE_R));
            return value != null ? CastArrayUtil.toPrimitive(value.getValue()) : new byte[0];
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setOpcodeFunctionalFailureS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setOpcodeFunctionalFailureS value = " + value + " property: 0x" + tenToHex(557846315));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846315, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOpcodeStartFunctionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846313, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOpcodeStartFunctionR value = " + value + " property: 0x" + tenToHex(557846313));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setOpcodeSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setOpcodeSwitchStateS value = " + value + " property: 0x" + tenToHex(557846310));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846310, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setOpcodeSwitchStateSettingS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setOpcodeSwitchStateSettingS value = " + value + " property: 0x" + tenToHex(557846312));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846312, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getPadExitPinCodeProcessR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846314, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getPadExitPinCodeProcessR value = " + value + " property: 0x" + tenToHex(557846314));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getPadOpcodeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846322, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getPadOpcodeConfigR value = " + value + " property: 0x" + tenToHex(557846322));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getResponseResultOfOpcodeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846319, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getResponseResultOfOpcodeR value = " + value + " property: 0x" + tenToHex(557846319));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getSupportOpcodeStartupR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846306, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getSupportOpcodeStartupR value = " + value + " property: 0x" + tenToHex(557846306));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getBleOperateCommandR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846303, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getBleOperateCommandR value = " + value + " property: 0x" + tenToHex(557846303));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getBleOperateResultR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846304, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getBleOperateResultR value = " + value + " property: 0x" + tenToHex(557846304));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOperationCommandR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_OPERATION_COMMAND403_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOperationCommandR value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_OPERATION_COMMAND403_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOperationTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_OPERATION_TYPE403_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOperationTypeR value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_OPERATION_TYPE403_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getResultsOfEnforcementR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_RESULTS_OF_ENFORCEMENT_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getResultsOfEnforcementR value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_RESULTS_OF_ENFORCEMENT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getStatusOfLockSettingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_STATUS_OF_LOCK_SETTING_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getStatusOfLockSettingR value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_STATUS_OF_LOCK_SETTING_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getStatusOfUnlockSettingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_STATUS_OF_UNLOCK_SETTING_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getStatusOfUnlockSettingR value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_STATUS_OF_UNLOCK_SETTING_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOfflineOperatPairingResult() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846288, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOfflineOperatPairingResult property: 0x");
            sb.append(tenToHex(557846288));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getFindKeyResultDouble() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846283, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFindKeyResultDouble property: 0x");
            sb.append(tenToHex(557846283));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getFindKeyResultSingle() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846282, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFindKeyResultSingle property: 0x");
            sb.append(tenToHex(557846282));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getNfcConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846272, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getNfcConfig property: 0x");
            sb.append(tenToHex(557846272));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getPairingProcessCallBackDouble() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846285, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPairingProcessCallBackDouble property: 0x");
            sb.append(tenToHex(557846285));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getPairingProcessCallBackSingle() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846284, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPairingProcessCallBackSingle property: 0x");
            sb.append(tenToHex(557846284));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getPairingResultDouble() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846287, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPairingResultDouble property: 0x");
            sb.append(tenToHex(557846287));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getPairingResultSingle() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846286, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPairingResultSingle property: 0x");
            sb.append(tenToHex(557846286));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getQueryOwnerKeyResultDouble() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846281, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getQueryOwnerKeyResultDouble property: 0x");
            sb.append(tenToHex(557846281));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getQueryOwnerKeyResultSingle() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846280, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getQueryOwnerKeyResultSingle property: 0x");
            sb.append(tenToHex(557846280));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getStartForNoKeyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846277, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getStartForNoKeyR property: 0x");
            sb.append(tenToHex(557846277));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDigitalkeyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getNfcOperationCommand() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_NFC_OPERATION_COMMAND, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getNfcOperationCommand value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_NFC_OPERATION_COMMAND));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOperationType19C33() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_OPERATION_TYPE_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOperationType19C33 value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_OPERATION_TYPE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getResponseFeedbackInformR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846293, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getResponseFeedbackInformR value = " + value + " property: 0x" + tenToHex(557846293));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOperationCommand19C33() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_OPERATION_COMMAND_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOperationCommand19C33 value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_OPERATION_COMMAND_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getOperationResult19C33() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDigitalkeyManager.ID_OPERATION_RESULT_R, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getOperationResult19C33 value = " + value + " property: 0x" + tenToHex(CarVendorDigitalkeyManager.ID_OPERATION_RESULT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setStartForNoKeyX36S(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setStartForNoKeyX36S property: 0x" + tenToHex(557846279) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846279, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setStartForNoKeyX96S(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setStartForNoKeyX96S property: 0x" + tenToHex(557846278) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846278, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setDigitalKeyPairingX36(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setDigitalKeyPairingX36 property: 0x" + tenToHex(557846276) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846276, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setDigitalKeyPairingX96(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setDigitalKeyPairingX96 property: 0x" + tenToHex(557846275) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846275, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setQueryOwnerKeyX36(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setQueryOwnerKeyX36 property: 0x" + tenToHex(557846274) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846274, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setQueryOwnerKeyX96(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setQueryOwnerKeyX96 property: 0x" + tenToHex(557846273) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557846273, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setKeyIdOne(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setKeyIdOne value = " + value + " property: 0x" + tenToHex(560992023));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, 560992023, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setKeyIdTwo(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setKeyIdTwo value = " + value + " property: 0x" + tenToHex(560992024));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, 560992024, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void setOperationCmdAndKeyType(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorDigitalkeyManager.TAG, "setOperationCmdAndKeyType value = " + value + " property: 0x" + tenToHex(557911830));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, 557911830, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public int getPadOfflineOperationRespR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846297, 0);
            Log.debug(CarVendorDigitalkeyManager.TAG, "getPadOfflineOperationRespR value = " + value + " property: 0x" + tenToHex(557846297));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void addCarDigitalkeyEventListener(CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener listener) throws CarNotConnectedException {
        OnCarDigitalkeyChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarDigitalkeyChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorDigitalkeyManager.TAG, "addCarDigitalkeyEventListener propId size: " + this.mExtDigitalkeyIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtDigitalkeyIds) {
                int propId = num.intValue();
                Log.debug(CarVendorDigitalkeyManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 3.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager
    public void removeCarDigitalkeyEventListener(CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarDigitalkeyChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorDigitalkeyManager.TAG, "removeCarDigitalkeyEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DigitalkeyEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sDigitalkeyEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorDigitalkeyManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557846272:
                sDigitalkeyEventChangeValue.setNfcConfig(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846293:
                sDigitalkeyEventChangeValue.setResponseFeedbackInformR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557911830:
                sDigitalkeyEventChangeValue.setOperationCmdAndKeyType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorDigitalkeyManager.ID_OPCODE_DATA_VALUE_R /* 558960432 */:
                sDigitalkeyEventChangeValue.setOpcodeDataValueR(CastArrayUtil.toPrimitive((Byte[]) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557846277:
                        sDigitalkeyEventChangeValue.setStartForNoKeyR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846278:
                        sDigitalkeyEventChangeValue.setStartForNoKeyX96S(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846279:
                        sDigitalkeyEventChangeValue.setStartForNoKeyX36S(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846280:
                        sDigitalkeyEventChangeValue.setQueryOwnerKeyResultSingle(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846281:
                        sDigitalkeyEventChangeValue.setQueryOwnerKeyResultDouble(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846282:
                        sDigitalkeyEventChangeValue.setFindKeyResultSingle(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846283:
                        sDigitalkeyEventChangeValue.setFindKeyResultDouble(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846284:
                        sDigitalkeyEventChangeValue.setPairingProcessCallBackSingle(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846285:
                        sDigitalkeyEventChangeValue.setPairingProcessCallBackDouble(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846286:
                        sDigitalkeyEventChangeValue.setPairingResultSingle(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846287:
                        sDigitalkeyEventChangeValue.setPairingResultDouble(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846288:
                        sDigitalkeyEventChangeValue.setOfflineOperatPairingResult(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorDigitalkeyManager.ID_NFC_OPERATION_COMMAND /* 557846289 */:
                        sDigitalkeyEventChangeValue.setNfcOperationCommand(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557846297:
                                sDigitalkeyEventChangeValue.setPadOfflineOperationRespR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorDigitalkeyManager.ID_OPERATION_TYPE403_R /* 557846298 */:
                                sDigitalkeyEventChangeValue.setOperationTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorDigitalkeyManager.ID_OPERATION_COMMAND403_R /* 557846299 */:
                                sDigitalkeyEventChangeValue.setOperationCommandR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorDigitalkeyManager.ID_RESULTS_OF_ENFORCEMENT_R /* 557846300 */:
                                sDigitalkeyEventChangeValue.setResultsOfEnforcementR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorDigitalkeyManager.ID_STATUS_OF_UNLOCK_SETTING_R /* 557846301 */:
                                sDigitalkeyEventChangeValue.setStatusOfUnlockSettingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorDigitalkeyManager.ID_STATUS_OF_LOCK_SETTING_R /* 557846302 */:
                                sDigitalkeyEventChangeValue.setStatusOfLockSettingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846303:
                                sDigitalkeyEventChangeValue.setBleOperateCommandR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846304:
                                sDigitalkeyEventChangeValue.setBleOperateResultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846305:
                                sDigitalkeyEventChangeValue.setDrivingPasswordSettingS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846306:
                                sDigitalkeyEventChangeValue.setSupportOpcodeStartupR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846307:
                                sDigitalkeyEventChangeValue.setNfcKeyStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846308:
                                sDigitalkeyEventChangeValue.setNfcKeyUnlockTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846309:
                                sDigitalkeyEventChangeValue.setNfcKeyUnlockResultR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846310:
                                sDigitalkeyEventChangeValue.setOpcodeSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846311:
                                sDigitalkeyEventChangeValue.setCurAccountSwitchFlagS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846312:
                                sDigitalkeyEventChangeValue.setOpcodeSwitchStateSettingS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846313:
                                sDigitalkeyEventChangeValue.setOpcodeStartFunctionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846314:
                                sDigitalkeyEventChangeValue.setPadExitPinCodeProcessR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846315:
                                sDigitalkeyEventChangeValue.setOpcodeFunctionalFailureS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846316:
                                sDigitalkeyEventChangeValue.setOpcodeDataLengthR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846317:
                                sDigitalkeyEventChangeValue.setOpcodeChecksumValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846318:
                                sDigitalkeyEventChangeValue.setOpcodeCommandR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846319:
                                sDigitalkeyEventChangeValue.setResponseResultOfOpcodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557846321:
                                        sDigitalkeyEventChangeValue.setDrivingPasswordSettingConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846322:
                                        sDigitalkeyEventChangeValue.setPadOpcodeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846323:
                                        sDigitalkeyEventChangeValue.setBluetoothUnlockTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846324:
                                        sDigitalkeyEventChangeValue.setMultiplexorS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557846327:
                                                sDigitalkeyEventChangeValue.setOpcodeDataToSecRandomS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557846328:
                                                sDigitalkeyEventChangeValue.setOpcodeDataToSecNfcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case 560992023:
                                                        sDigitalkeyEventChangeValue.setKeyIdOne(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 560992024:
                                                        sDigitalkeyEventChangeValue.setKeyIdTwo(CastArrayUtil.toPrimitive((Long) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case 560992053:
                                                                sDigitalkeyEventChangeValue.setOpcodeDataToSecUserIdS(CastArrayUtil.toPrimitive((Long) value.getValue()));
                                                                break;
                                                            case 560992054:
                                                                sDigitalkeyEventChangeValue.setOpcodeDataToSecOpcodeS(CastArrayUtil.toPrimitive((Long) value.getValue()));
                                                                break;
                                                            default:
                                                                Log.debug(CarVendorDigitalkeyManager.TAG, "break other properties ,except digitalkey");
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
        Log.debug(CarVendorDigitalkeyManager.TAG, "convertCarVendorPropertyValue " + sDigitalkeyEventChangeValue.toString(), DEBUG_LOGGER);
        return sDigitalkeyEventChangeValue;
    }

    private OnCarDigitalkeyChangeEventListenerProxy findListenerLocked(CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener listener) {
        for (OnCarDigitalkeyChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarDigitalkeyChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener listener;

        public OnCarDigitalkeyChangeEventListenerProxy(CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorDigitalkeyMgr.this.traceCallbackPrepare();
                DigitalkeyEventChangeValue newValue = CarVendorDigitalkeyMgr.convertCarVendorProperty(value);
                CarVendorDigitalkeyMgr.this.traceCallbackBefore();
                this.listener.onDigitalkeyEventChange(newValue);
                CarVendorDigitalkeyMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorDigitalkeyManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorDigitalkeyManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onDigitalkeyErrorEvent(propertyId, zone);
        }
    }
}
