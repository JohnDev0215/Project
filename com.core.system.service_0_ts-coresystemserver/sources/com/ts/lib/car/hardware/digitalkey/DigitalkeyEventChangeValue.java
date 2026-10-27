package com.ts.lib.car.hardware.digitalkey;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes.dex */
public class DigitalkeyEventChangeValue extends CarBaseProperty {
    private int mBleOperateCommandR;
    private int mBleOperateResultR;
    private int mBluetoothUnlockTypeR;
    private int mCurAccountSwitchFlagS;
    private int mDrivingPasswordSettingConfigR;
    private int mDrivingPasswordSettingS;
    private int mFindKeyResultDouble;
    private int mFindKeyResultSingle;
    private int mKeyId1;
    private long mKeyId2;
    private int mMultiplexorS;
    private int mNfcConfig;
    private int mNfcKeyStatusR;
    private int mNfcKeyUnlockResultR;
    private int mNfcKeyUnlockTypeR;
    private int mNfcOperationCommand;
    private int mOfflineOperatPairingResult;
    private int mOpcodeChecksumValueR;
    private int mOpcodeCommandR;
    private int mOpcodeDataLengthR;
    private int mOpcodeDataToSecNfcS;
    private long mOpcodeDataToSecOpcodeS;
    private int mOpcodeDataToSecRandomS;
    private long mOpcodeDataToSecUserIdS;
    private byte[] mOpcodeDataValueR;
    private int mOpcodeFunctionalFailureS;
    private int mOpcodeStartFunctionR;
    private int mOpcodeSwitchStateS;
    private int mOpcodeSwitchStateSettingS;
    private int mOperationCmdAndKeyType;
    private int mOperationCommandR;
    private int mOperationTypeR;
    private int mPadExitPinCodeProcessR;
    private int mPadOfflineOperationRespR;
    private int mPadOpcodeConfigR;
    private int mPairingProcessCallBackDouble;
    private int mPairingProcessCallBackSingle;
    private int mPairingResultDouble;
    private int mPairingResultSingle;
    private int mQueryOwnerKeyResultDouble;
    private int mQueryOwnerKeyResultSingle;
    private int mResponseFeedbackInformR;
    private int mResponseResultOfOpcodeR;
    private int mResultsOfEnforcementR;
    private int mStartForNoKeyR;
    private int mStartForNoKeyX36S;
    private int mStartForNoKeyX96S;
    private int mStatusOfLockSettingR;
    private int mStatusOfUnlockSettingR;
    private int mSupportOpcodeStartupR;
    private int mdigitalKeyPairingX36;
    private int mdigitalKeyPairingX96;
    private int mqueryOwnerKeyX36;
    private int mqueryOwnerKeyX96;

    public int getBluetoothUnlockTypeR() {
        return this.mBluetoothUnlockTypeR;
    }

    public void setBluetoothUnlockTypeR(int bluetoothUnlockTypeR) {
        this.mBluetoothUnlockTypeR = bluetoothUnlockTypeR;
    }

    public int getCurAccountSwitchFlagS() {
        return this.mCurAccountSwitchFlagS;
    }

    public void setCurAccountSwitchFlagS(int curAccountSwitchFlagS) {
        this.mCurAccountSwitchFlagS = curAccountSwitchFlagS;
    }

    public int getDrivingPasswordSettingConfigR() {
        return this.mDrivingPasswordSettingConfigR;
    }

    public void setDrivingPasswordSettingConfigR(int drivingPasswordSettingConfigR) {
        this.mDrivingPasswordSettingConfigR = drivingPasswordSettingConfigR;
    }

    public int getDrivingPasswordSettingS() {
        return this.mDrivingPasswordSettingS;
    }

    public void setDrivingPasswordSettingS(int drivingPasswordSettingS) {
        this.mDrivingPasswordSettingS = drivingPasswordSettingS;
    }

    public int getMultiplexorS() {
        return this.mMultiplexorS;
    }

    public void setMultiplexorS(int multiplexorS) {
        this.mMultiplexorS = multiplexorS;
    }

    public int getNfcKeyStatusR() {
        return this.mNfcKeyStatusR;
    }

    public void setNfcKeyStatusR(int nfcKeyStatusR) {
        this.mNfcKeyStatusR = nfcKeyStatusR;
    }

    public int getNfcKeyUnlockResultR() {
        return this.mNfcKeyUnlockResultR;
    }

    public void setNfcKeyUnlockResultR(int nfcKeyUnlockResultR) {
        this.mNfcKeyUnlockResultR = nfcKeyUnlockResultR;
    }

    public int getNfcKeyUnlockTypeR() {
        return this.mNfcKeyUnlockTypeR;
    }

    public void setNfcKeyUnlockTypeR(int nfcKeyUnlockTypeR) {
        this.mNfcKeyUnlockTypeR = nfcKeyUnlockTypeR;
    }

    public int getOpcodeChecksumValueR() {
        return this.mOpcodeChecksumValueR;
    }

    public void setOpcodeChecksumValueR(int opcodeChecksumValueR) {
        this.mOpcodeChecksumValueR = opcodeChecksumValueR;
    }

    public int getOpcodeCommandR() {
        return this.mOpcodeCommandR;
    }

    public void setOpcodeCommandR(int opcodeCommandR) {
        this.mOpcodeCommandR = opcodeCommandR;
    }

    public int getOpcodeDataLengthR() {
        return this.mOpcodeDataLengthR;
    }

    public void setOpcodeDataLengthR(int opcodeDataLengthR) {
        this.mOpcodeDataLengthR = opcodeDataLengthR;
    }

    public int getOpcodeDataToSecNfcS() {
        return this.mOpcodeDataToSecNfcS;
    }

    public void setOpcodeDataToSecNfcS(int opcodeDataToSecNfcS) {
        this.mOpcodeDataToSecNfcS = opcodeDataToSecNfcS;
    }

    public long getOpcodeDataToSecOpcodeS() {
        return this.mOpcodeDataToSecOpcodeS;
    }

    public void setOpcodeDataToSecOpcodeS(long opcodeDataToSecOpcodeS) {
        this.mOpcodeDataToSecOpcodeS = opcodeDataToSecOpcodeS;
    }

    public int getOpcodeDataToSecRandomS() {
        return this.mOpcodeDataToSecRandomS;
    }

    public void setOpcodeDataToSecRandomS(int opcodeDataToSecRandomS) {
        this.mOpcodeDataToSecRandomS = opcodeDataToSecRandomS;
    }

    public long getOpcodeDataToSecUserIdS() {
        return this.mOpcodeDataToSecUserIdS;
    }

    public void setOpcodeDataToSecUserIdS(long opcodeDataToSecUserIdS) {
        this.mOpcodeDataToSecUserIdS = opcodeDataToSecUserIdS;
    }

    public byte[] getOpcodeDataValueR() {
        return this.mOpcodeDataValueR;
    }

    public void setOpcodeDataValueR(byte[] opcodeDataValueR) {
        this.mOpcodeDataValueR = opcodeDataValueR;
    }

    public int getOpcodeFunctionalFailureS() {
        return this.mOpcodeFunctionalFailureS;
    }

    public void setOpcodeFunctionalFailureS(int opcodeFunctionalFailureS) {
        this.mOpcodeFunctionalFailureS = opcodeFunctionalFailureS;
    }

    public int getOpcodeStartFunctionR() {
        return this.mOpcodeStartFunctionR;
    }

    public void setOpcodeStartFunctionR(int opcodeStartFunctionR) {
        this.mOpcodeStartFunctionR = opcodeStartFunctionR;
    }

    public int getOpcodeSwitchStateS() {
        return this.mOpcodeSwitchStateS;
    }

    public void setOpcodeSwitchStateS(int opcodeSwitchStateS) {
        this.mOpcodeSwitchStateS = opcodeSwitchStateS;
    }

    public int getOpcodeSwitchStateSettingS() {
        return this.mOpcodeSwitchStateSettingS;
    }

    public void setOpcodeSwitchStateSettingS(int opcodeSwitchStateSettingS) {
        this.mOpcodeSwitchStateSettingS = opcodeSwitchStateSettingS;
    }

    public int getPadExitPinCodeProcessR() {
        return this.mPadExitPinCodeProcessR;
    }

    public void setPadExitPinCodeProcessR(int padExitPinCodeProcessR) {
        this.mPadExitPinCodeProcessR = padExitPinCodeProcessR;
    }

    public int getPadOpcodeConfigR() {
        return this.mPadOpcodeConfigR;
    }

    public void setPadOpcodeConfigR(int padOpcodeConfigR) {
        this.mPadOpcodeConfigR = padOpcodeConfigR;
    }

    public int getResponseResultOfOpcodeR() {
        return this.mResponseResultOfOpcodeR;
    }

    public void setResponseResultOfOpcodeR(int responseResultOfOpcodeR) {
        this.mResponseResultOfOpcodeR = responseResultOfOpcodeR;
    }

    public int getSupportOpcodeStartupR() {
        return this.mSupportOpcodeStartupR;
    }

    public void setSupportOpcodeStartupR(int supportOpcodeStartupR) {
        this.mSupportOpcodeStartupR = supportOpcodeStartupR;
    }

    public int getBleOperateCommandR() {
        return this.mBleOperateCommandR;
    }

    public void setBleOperateCommandR(int result) {
        this.mBleOperateCommandR = result;
    }

    public int getBleOperateResultR() {
        return this.mBleOperateResultR;
    }

    public void setBleOperateResultR(int result) {
        this.mBleOperateResultR = result;
    }

    public int getOperationCommandR() {
        return this.mOperationCommandR;
    }

    public void setOperationCommandR(int operationCommandR) {
        this.mOperationCommandR = operationCommandR;
    }

    public int getOperationTypeR() {
        return this.mOperationTypeR;
    }

    public void setOperationTypeR(int operationTypeR) {
        this.mOperationTypeR = operationTypeR;
    }

    public int getResultsOfEnforcementR() {
        return this.mResultsOfEnforcementR;
    }

    public void setResultsOfEnforcementR(int resultsOfEnforcementR) {
        this.mResultsOfEnforcementR = resultsOfEnforcementR;
    }

    public int getStatusOfLockSettingR() {
        return this.mStatusOfLockSettingR;
    }

    public void setStatusOfLockSettingR(int statusOfLockSettingR) {
        this.mStatusOfLockSettingR = statusOfLockSettingR;
    }

    public int getStatusOfUnlockSettingR() {
        return this.mStatusOfUnlockSettingR;
    }

    public void setStatusOfUnlockSettingR(int statusOfUnlockSettingR) {
        this.mStatusOfUnlockSettingR = statusOfUnlockSettingR;
    }

    public int getKeyIdOne() {
        return this.mKeyId1;
    }

    public void setKeyIdOne(int result) {
        this.mKeyId1 = result;
    }

    public long getKeyIdTwo() {
        return this.mKeyId2;
    }

    public void setKeyIdTwo(long result) {
        this.mKeyId2 = result;
    }

    public int getOperationCmdAndKeyType() {
        return this.mOperationCmdAndKeyType;
    }

    public void setOperationCmdAndKeyType(int operationCmdAndKeyType) {
        this.mOperationCmdAndKeyType = operationCmdAndKeyType;
    }

    public int getPadOfflineOperationRespR() {
        return this.mPadOfflineOperationRespR;
    }

    public void setPadOfflineOperationRespR(int padOfflineOperationRespR) {
        this.mPadOfflineOperationRespR = padOfflineOperationRespR;
    }

    public int getResponseFeedbackInformR() {
        return this.mResponseFeedbackInformR;
    }

    public void setResponseFeedbackInformR(int value) {
        this.mResponseFeedbackInformR = value;
    }

    public int getNfcOperationCommand() {
        return this.mNfcOperationCommand;
    }

    public void setNfcOperationCommand(int nfcOperationCommand) {
        this.mNfcOperationCommand = nfcOperationCommand;
    }

    public int getOfflineOperatPairingResult() {
        return this.mOfflineOperatPairingResult;
    }

    public void setOfflineOperatPairingResult(int offlineOperatPairingResult) {
        this.mOfflineOperatPairingResult = offlineOperatPairingResult;
    }

    public int getFindKeyResultDouble() {
        return this.mFindKeyResultDouble;
    }

    public void setFindKeyResultDouble(int findKeyResultDouble) {
        this.mFindKeyResultDouble = findKeyResultDouble;
    }

    public int getFindKeyResultSingle() {
        return this.mFindKeyResultSingle;
    }

    public void setFindKeyResultSingle(int findKeyResultSingle) {
        this.mFindKeyResultSingle = findKeyResultSingle;
    }

    public int getNfcConfig() {
        return this.mNfcConfig;
    }

    public void setNfcConfig(int nfcConfig) {
        this.mNfcConfig = nfcConfig;
    }

    public int getPairingProcessCallBackDouble() {
        return this.mPairingProcessCallBackDouble;
    }

    public void setPairingProcessCallBackDouble(int pairingProcessCallBackDouble) {
        this.mPairingProcessCallBackDouble = pairingProcessCallBackDouble;
    }

    public int getPairingProcessCallBackSingle() {
        return this.mPairingProcessCallBackSingle;
    }

    public void setPairingProcessCallBackSingle(int pairingProcessCallBackSingle) {
        this.mPairingProcessCallBackSingle = pairingProcessCallBackSingle;
    }

    public int getPairingResultDouble() {
        return this.mPairingResultDouble;
    }

    public void setPairingResultDouble(int pairingResultDouble) {
        this.mPairingResultDouble = pairingResultDouble;
    }

    public int getPairingResultSingle() {
        return this.mPairingResultSingle;
    }

    public void setPairingResultSingle(int pairingResultSingle) {
        this.mPairingResultSingle = pairingResultSingle;
    }

    public int getQueryOwnerKeyResultDouble() {
        return this.mQueryOwnerKeyResultDouble;
    }

    public void setQueryOwnerKeyResultDouble(int queryOwnerKeyResultDouble) {
        this.mQueryOwnerKeyResultDouble = queryOwnerKeyResultDouble;
    }

    public int getQueryOwnerKeyResultSingle() {
        return this.mQueryOwnerKeyResultSingle;
    }

    public void setQueryOwnerKeyResultSingle(int queryOwnerKeyResultSingle) {
        this.mQueryOwnerKeyResultSingle = queryOwnerKeyResultSingle;
    }

    public int getStartForNoKeyR() {
        return this.mStartForNoKeyR;
    }

    public void setStartForNoKeyR(int startForNoKeyR) {
        this.mStartForNoKeyR = startForNoKeyR;
    }

    public int getStartForNoKeyX36S() {
        return this.mStartForNoKeyX36S;
    }

    public void setStartForNoKeyX36S(int startForNoKeyX36S) {
        this.mStartForNoKeyX36S = startForNoKeyX36S;
    }

    public int getStartForNoKeyX96S() {
        return this.mStartForNoKeyX96S;
    }

    public void setStartForNoKeyX96S(int startForNoKeyX96S) {
        this.mStartForNoKeyX96S = startForNoKeyX96S;
    }

    public int getdigitalKeyPairingX36() {
        return this.mdigitalKeyPairingX36;
    }

    public void setdigitalKeyPairingX36(int digitalKeyPairingX36) {
        this.mdigitalKeyPairingX36 = digitalKeyPairingX36;
    }

    public int getdigitalKeyPairingX96() {
        return this.mdigitalKeyPairingX96;
    }

    public void setdigitalKeyPairingX96(int digitalKeyPairingX96) {
        this.mdigitalKeyPairingX96 = digitalKeyPairingX96;
    }

    public int getqueryOwnerKeyX36() {
        return this.mqueryOwnerKeyX36;
    }

    public void setqueryOwnerKeyX36(int queryOwnerKeyX36) {
        this.mqueryOwnerKeyX36 = queryOwnerKeyX36;
    }

    public int getqueryOwnerKeyX96() {
        return this.mqueryOwnerKeyX96;
    }

    public void setqueryOwnerKeyX96(int queryOwnerKeyX96) {
        this.mqueryOwnerKeyX96 = queryOwnerKeyX96;
    }
}
