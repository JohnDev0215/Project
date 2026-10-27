package com.ts.lib.car.hardware.digitalkey;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorDigitalkeyManager {
    public static final int ID_BLE_OPERATE_COMMAND_R = 557846303;
    public static final int ID_BLE_OPERATE_RESULT_R = 557846304;
    public static final int ID_BLUETOOTH_UNLOCK_TYPE_R = 557846323;
    public static final int ID_CUR_ACCOUNT_SWITCH_FLAG_S = 557846311;
    public static final int ID_DIGITAL_KEY_PAIRING_X36 = 557846276;
    public static final int ID_DIGITAL_KEY_PAIRING_X96 = 557846275;
    public static final int ID_DRIVING_PASSWORD_SETTING_CONFIG_R = 557846321;
    public static final int ID_DRIVING_PASSWORD_SETTING_S = 557846305;
    public static final int ID_FIND_KEY_RESULT_DOUBLE = 557846283;
    public static final int ID_FIND_KEY_RESULT_SINGLE = 557846282;
    public static final int ID_KEY_ID_1 = 560992023;
    public static final int ID_KEY_ID_2 = 560992024;
    public static final int ID_MULTIPLEXOR_S = 557846324;
    public static final int ID_NFC_CONFIG = 557846272;
    public static final int ID_NFC_KEY_STATUS_R = 557846307;
    public static final int ID_NFC_KEY_UNLOCK_RESULT_R = 557846309;
    public static final int ID_NFC_KEY_UNLOCK_TYPE_R = 557846308;
    public static final int ID_NFC_OPERATION_COMMAND = 557846289;
    public static final int ID_OFF_LINE_OPERAT_PAIRING_RESULTR = 557846288;
    public static final int ID_OPCODE_CHECKSUM_VALUE_R = 557846317;
    public static final int ID_OPCODE_COMMAND_R = 557846318;
    public static final int ID_OPCODE_DATA_LENGTH_R = 557846316;
    public static final int ID_OPCODE_DATA_TO_SEC_NFC_S = 557846328;
    public static final int ID_OPCODE_DATA_TO_SEC_OPCODE_S = 560992054;
    public static final int ID_OPCODE_DATA_TO_SEC_RANDOM_S = 557846327;
    public static final int ID_OPCODE_DATA_TO_SEC_USER_ID_S = 560992053;
    public static final int ID_OPCODE_DATA_VALUE_R = 558960432;
    public static final int ID_OPCODE_FUNCTIONAL_FAILURE_S = 557846315;
    public static final int ID_OPCODE_START_FUNCTION_R = 557846313;
    public static final int ID_OPCODE_SWITCH_STATE_S = 557846310;
    public static final int ID_OPCODE_SWITCH_STATE_SETTING_S = 557846312;
    public static final int ID_OPERATION_CMD_AND_KEY_TYPE = 557911830;
    public static final int ID_OPERATION_COMMAND403_R = 557846299;
    public static final int ID_OPERATION_COMMAND_R = 557846291;
    public static final int ID_OPERATION_RESULT_R = 557846292;
    public static final int ID_OPERATION_TYPE403_R = 557846298;
    public static final int ID_OPERATION_TYPE_R = 557846290;
    public static final int ID_PAD_EXIT_PIN_CODE_PROCESS_R = 557846314;
    public static final int ID_PAD_OFFLINE_OPERATION_RESP_R = 557846297;
    public static final int ID_PAD_OPCODE_CONFIG_R = 557846322;
    public static final int ID_PAIRING_PROCESS_CALLBACK_DOUBLE = 557846285;
    public static final int ID_PAIRING_PROCESS_CALLBACK_SINGLE = 557846284;
    public static final int ID_PAIRING_RESULT_DOUBLE = 557846287;
    public static final int ID_PAIRING_RESULT_SINGLE = 557846286;
    public static final int ID_PORT_OPCODE_STARTUP_R = 557846306;
    public static final int ID_QUERY_OWNER_KEY_RESULT_DOUBLE = 557846281;
    public static final int ID_QUERY_OWNER_KEY_RESULT_SINGLE = 557846280;
    public static final int ID_QUERY_OWNER_KEY_X36 = 557846274;
    public static final int ID_QUERY_OWNER_KEY_X96 = 557846273;
    public static final int ID_RESPONSE_FEED_BACK_INFORM_R = 557846293;
    public static final int ID_RESPONSE_RESULT_OF_OPCODE_R = 557846319;
    public static final int ID_RESULTS_OF_ENFORCEMENT_R = 557846300;
    public static final int ID_START_FOR_NOKEY_R = 557846277;
    public static final int ID_START_FOR_NOKEY_X36_S = 557846279;
    public static final int ID_START_FOR_NOKEY_X96_S = 557846278;
    public static final int ID_STATUS_OF_LOCK_SETTING_R = 557846302;
    public static final int ID_STATUS_OF_UNLOCK_SETTING_R = 557846301;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.digitalkey";
    public static final String TAG = "CAR.VENDOR.DIGITALKEY";

    public interface OnCarDigitalkeyEventListener {
        void onDigitalkeyErrorEvent(int i, int i2);

        void onDigitalkeyEventChange(DigitalkeyEventChangeValue digitalkeyEventChangeValue);
    }

    void addCarDigitalkeyEventListener(OnCarDigitalkeyEventListener onCarDigitalkeyEventListener) throws CarNotConnectedException;

    int getBleOperateCommandR() throws CarNotConnectedException;

    int getBleOperateResultR() throws CarNotConnectedException;

    int getBluetoothUnlockTypeR() throws CarNotConnectedException;

    int getDrivingPasswordSettingConfigR() throws CarNotConnectedException;

    int getFindKeyResultDouble() throws CarNotConnectedException;

    int getFindKeyResultSingle() throws CarNotConnectedException;

    int getNfcConfig() throws CarNotConnectedException;

    int getNfcKeyStatusR() throws CarNotConnectedException;

    int getNfcKeyUnlockResultR() throws CarNotConnectedException;

    int getNfcKeyUnlockTypeR() throws CarNotConnectedException;

    int getNfcOperationCommand() throws CarNotConnectedException;

    int getOfflineOperatPairingResult() throws CarNotConnectedException;

    int getOpcodeChecksumValueR() throws CarNotConnectedException;

    int getOpcodeCommandR() throws CarNotConnectedException;

    int getOpcodeDataLengthR() throws CarNotConnectedException;

    byte[] getOpcodeDataValueR() throws CarNotConnectedException;

    int getOpcodeStartFunctionR() throws CarNotConnectedException;

    int getOperationCommand19C33() throws CarNotConnectedException;

    int getOperationCommandR() throws CarNotConnectedException;

    int getOperationResult19C33() throws CarNotConnectedException;

    int getOperationType19C33() throws CarNotConnectedException;

    int getOperationTypeR() throws CarNotConnectedException;

    int getPadExitPinCodeProcessR() throws CarNotConnectedException;

    int getPadOfflineOperationRespR() throws CarNotConnectedException;

    int getPadOpcodeConfigR() throws CarNotConnectedException;

    int getPairingProcessCallBackDouble() throws CarNotConnectedException;

    int getPairingProcessCallBackSingle() throws CarNotConnectedException;

    int getPairingResultDouble() throws CarNotConnectedException;

    int getPairingResultSingle() throws CarNotConnectedException;

    int getQueryOwnerKeyResultDouble() throws CarNotConnectedException;

    int getQueryOwnerKeyResultSingle() throws CarNotConnectedException;

    int getResponseFeedbackInformR() throws CarNotConnectedException;

    int getResponseResultOfOpcodeR() throws CarNotConnectedException;

    int getResultsOfEnforcementR() throws CarNotConnectedException;

    int getStartForNoKeyR() throws CarNotConnectedException;

    int getStatusOfLockSettingR() throws CarNotConnectedException;

    int getStatusOfUnlockSettingR() throws CarNotConnectedException;

    int getSupportOpcodeStartupR() throws CarNotConnectedException;

    void removeCarDigitalkeyEventListener(OnCarDigitalkeyEventListener onCarDigitalkeyEventListener) throws CarNotConnectedException;

    void setCurAccountSwitchFlagS(int i) throws CarNotConnectedException;

    void setDigitalKeyPairingX36(int i) throws CarNotConnectedException;

    void setDigitalKeyPairingX96(int i) throws CarNotConnectedException;

    void setDrivingPasswordSettingS(int i) throws CarNotConnectedException;

    void setKeyIdOne(byte[] bArr) throws CarNotConnectedException;

    void setKeyIdTwo(byte[] bArr) throws CarNotConnectedException;

    void setMultiplexorS(int i) throws CarNotConnectedException;

    void setOpcodeDataToSecNfcS(int i) throws CarNotConnectedException;

    void setOpcodeDataToSecOpcodeS(long j) throws CarNotConnectedException;

    void setOpcodeDataToSecRandomS(int i) throws CarNotConnectedException;

    void setOpcodeDataToSecUserIdS(long j) throws CarNotConnectedException;

    void setOpcodeFunctionalFailureS(int i) throws CarNotConnectedException;

    void setOpcodeSwitchStateS(int i) throws CarNotConnectedException;

    void setOpcodeSwitchStateSettingS(int i) throws CarNotConnectedException;

    void setOperationCmdAndKeyType(int[] iArr) throws CarNotConnectedException;

    void setQueryOwnerKeyX36(int i) throws CarNotConnectedException;

    void setQueryOwnerKeyX96(int i) throws CarNotConnectedException;

    void setStartForNoKeyX36S(int i) throws CarNotConnectedException;

    void setStartForNoKeyX96S(int i) throws CarNotConnectedException;
}
