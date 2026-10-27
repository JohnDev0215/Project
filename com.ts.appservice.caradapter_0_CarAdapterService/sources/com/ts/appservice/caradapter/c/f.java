package com.ts.appservice.caradapter.c;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import com.ts.appservice.caradapter.d.B;
import com.ts.appservice.caradapter.d.p;
import com.ts.appservice.caradapter.d.t;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager;
import com.ts.lib.car.hardware.carmode.CarmodeEventChangeValue;
import com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager;
import com.ts.lib.car.hardware.digitalkey.DigitalkeyEventChangeValue;
import com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager;
import com.ts.lib.car.hardware.dipilot.DipilotEventChangeValue;
import com.ts.lib.car.hardware.reminder.ReminderEventChangeValue;
import com.ts.lib.car.hardware.xcall.CarVendorXcallManager;
import com.ts.lib.car.hardware.xcall.XcallEventChangeValue;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.ICarAssistAdapterCallback;
import com.ts.lib.caradapter.assist.ICarAssistAdapterService;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import java.util.Arrays;

/* JADX INFO: compiled from: CarAssistServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ICarAssistAdapterService.Stub implements com.ts.appservice.caradapter.d.f, t, com.ts.appservice.caradapter.d.e, p, B {
    private static final String g1 = f.class.getSimpleName();
    private int A;
    private int A0;
    private int B;
    private int B0;
    private int C;
    private int C0;
    private int D;
    private int D0;
    private int E;
    private int E0;
    private int F;
    private int F0;
    private int G;
    private int G0;
    private int H;
    private int H0;
    private int I;
    private int I0;
    private int J;
    private int J0;
    private int K;
    private int K0;
    private int L;
    private int L0;
    private int M;
    private int M0;
    private int N;
    private int N0;
    private int O;
    private int O0;
    private int P;
    private int P0;
    private int Q;
    private int Q0;
    private int R;
    private int R0;
    private int S;
    private int S0;
    private int T;
    private int T0;
    private int U;
    private int U0;
    private int V;
    private int V0;
    private int W;
    private int W0;
    private int X;
    private int X0;
    private int Y;
    private int Y0;
    private int Z;
    private int Z0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CarVendorDipilotManager f62a;
    private int a0;
    private com.ts.appservice.caradapter.a.a a1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CarVendorDigitalkeyManager f63b;
    private int b0;
    private CarVendorCarmodeManager c;
    private int c0;
    private a c1;
    private CarVendorXcallManager d;
    private int d0;
    private com.ts.appservice.caradapter.a.a e;
    private int e0;
    private int f0;
    private int g;
    private int g0;
    private int h;
    private int h0;
    private int i;
    private int i0;
    private int j;
    private int j0;
    private int k;
    private int k0;
    private int l;
    private int l0;
    private int m;
    private int m0;
    private int n;
    private int n0;
    private int o;
    private int o0;
    private int p;
    private int p0;
    private int q;
    private int q0;
    private int r;
    private int r0;
    private int s;
    private int s0;
    private int t;
    private int t0;
    private int u;
    private int u0;
    private int v;
    private int v0;
    private int w;
    private int w0;
    private int x;
    private int x0;
    private int y;
    private int y0;
    private int z;
    private int z0;
    private boolean f = false;
    private final CarAssistPacket d1 = new CarAssistPacket();
    private final int[] e1 = new int[6];
    private String f1 = "51.1.1.2209270.1";
    private final HandlerThread b1 = com.ts.appservice.caradapter.e.b.a("VEHICLE-ASSIST");

    /* JADX INFO: compiled from: CarAssistServiceImpl.java */
    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 11:
                    f.this.v(message.arg1, message.arg2, (DipilotEventChangeValue) message.obj);
                    break;
                case 12:
                    f.this.w(message.arg1, message.arg2, (ReminderEventChangeValue) message.obj);
                    break;
                case 13:
                    f.this.u(message.arg1, message.arg2, (DigitalkeyEventChangeValue) message.obj);
                    break;
                case 14:
                    f.this.t(message.arg1, message.arg2, (CarmodeEventChangeValue) message.obj);
                    break;
                case 15:
                    f.this.x(message.arg1, message.arg2, (XcallEventChangeValue) message.obj);
                    break;
                default:
                    LogUtil.error(f.g1, "Unexpected message: " + message.what);
                    break;
            }
        }
    }

    public f(Context context) {
        this.a1 = new com.ts.appservice.caradapter.a.a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(int i, int i2, CarmodeEventChangeValue carmodeEventChangeValue) {
        String str = g1;
        try {
            switch (i) {
                case 557871104:
                    int showCarModelTypeR = carmodeEventChangeValue.getShowCarModelTypeR();
                    this.d1.setShowCarModelTypeR(showCarModelTypeR);
                    LogUtil.verbose(str, "ID_SHOW_CAR_MODEL_TYPER:" + showCarModelTypeR);
                    break;
                case 557871105:
                    int carEngineTypeR = carmodeEventChangeValue.getCarEngineTypeR();
                    this.d1.setCarEngineTypeR(carEngineTypeR);
                    LogUtil.verbose(str, "ID_CAR_ENGINE_TYPER:" + carEngineTypeR);
                    break;
                case 557871106:
                    int airCtrlChangeEventR = carmodeEventChangeValue.getAirCtrlChangeEventR();
                    this.d1.setAirCtrlChangeEventR(airCtrlChangeEventR);
                    LogUtil.verbose(str, "AIR_CTRL_CHANGE_EVENT_R:" + airCtrlChangeEventR);
                    break;
                case 557871107:
                    int airCtrlPressEventR = carmodeEventChangeValue.getAirCtrlPressEventR();
                    this.d1.setAirCtrlPressEventR(airCtrlPressEventR);
                    LogUtil.verbose(str, "AIR_CTRL_PRESS_EVENT_R:" + airCtrlPressEventR);
                    break;
                case 557871108:
                    boolean z = carmodeEventChangeValue.getEngineEnableS() == 1;
                    this.d1.setEngineEnableS(z);
                    LogUtil.verbose(str, "ID_ENGINE_ENABLES:" + z);
                    break;
                case 557871109:
                    int showCarGearChangeR = carmodeEventChangeValue.getShowCarGearChangeR();
                    this.d1.setShowCarGearChangeR(showCarGearChangeR);
                    LogUtil.verbose(str, "ID_SHOW_CAR_GEAR_CHANGER:" + showCarGearChangeR);
                    break;
            }
            this.a1.m(i, i2, this.d1);
            LogUtil.verbose(str, "notifyCarModeEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(int i, int i2, DigitalkeyEventChangeValue digitalkeyEventChangeValue) {
        String str = g1;
        try {
            switch (i) {
                case 557846272:
                    int nfcConfig = digitalkeyEventChangeValue.getNfcConfig();
                    this.d1.setNfcConfig(nfcConfig);
                    LogUtil.verbose(str, "ID_NFC_CONFIG:" + nfcConfig);
                    break;
                case 557846273:
                    int i3 = digitalkeyEventChangeValue.getqueryOwnerKeyX96();
                    this.d1.setqueryOwnerKeyX96(i3);
                    LogUtil.verbose(str, "ID_QUERY_OWNER_KEY_X96:" + i3);
                    break;
                case 557846274:
                    int i4 = digitalkeyEventChangeValue.getqueryOwnerKeyX36();
                    this.d1.setqueryOwnerKeyX36(i4);
                    LogUtil.verbose(str, "ID_QUERY_OWNER_KEY_X36:" + i4);
                    break;
                case 557846275:
                    int i5 = digitalkeyEventChangeValue.getdigitalKeyPairingX96();
                    this.d1.setdigitalKeyPairingX96(i5);
                    LogUtil.verbose(str, "ID_DIGITAL_KEY_PAIRING_X96:" + i5);
                    break;
                case 557846276:
                    int i6 = digitalkeyEventChangeValue.getdigitalKeyPairingX36();
                    this.d1.setdigitalKeyPairingX36(i6);
                    LogUtil.verbose(str, "ID_DIGITAL_KEY_PAIRING_X36:" + i6);
                    break;
                case 557846277:
                    int startForNoKeyR = digitalkeyEventChangeValue.getStartForNoKeyR();
                    this.d1.setStartForNoKeyR(startForNoKeyR);
                    LogUtil.verbose(str, "ID_START_FOR_NOKEY_R:" + startForNoKeyR);
                    break;
                case 557846278:
                    int startForNoKeyX96S = digitalkeyEventChangeValue.getStartForNoKeyX96S();
                    this.d1.setStartForNoKeyX96S(startForNoKeyX96S);
                    LogUtil.verbose(str, "ID_START_FOR_NOKEY_X96_S:" + startForNoKeyX96S);
                    break;
                case 557846279:
                    int startForNoKeyX36S = digitalkeyEventChangeValue.getStartForNoKeyX36S();
                    this.d1.setStartForNoKeyX36S(startForNoKeyX36S);
                    LogUtil.verbose(str, "ID_START_FOR_NOKEY_X36_S:" + startForNoKeyX36S);
                    break;
                case 557846280:
                    int queryOwnerKeyResultSingle = digitalkeyEventChangeValue.getQueryOwnerKeyResultSingle();
                    this.d1.setQueryOwnerKeyResultSingle(queryOwnerKeyResultSingle);
                    LogUtil.verbose(str, "ID_QUERY_OWNER_KEY_RESULT_SINGLE:" + queryOwnerKeyResultSingle);
                    break;
                case 557846281:
                    int queryOwnerKeyResultDouble = digitalkeyEventChangeValue.getQueryOwnerKeyResultDouble();
                    this.d1.setQueryOwnerKeyResultDouble(queryOwnerKeyResultDouble);
                    LogUtil.verbose(str, "ID_QUERY_OWNER_KEY_RESULT_DOUBLE:" + queryOwnerKeyResultDouble);
                    break;
                case 557846282:
                    int findKeyResultSingle = digitalkeyEventChangeValue.getFindKeyResultSingle();
                    this.d1.setFindKeyResultSingle(findKeyResultSingle);
                    LogUtil.verbose(str, "ID_FIND_KEY_RESULT_SINGLE:" + findKeyResultSingle);
                    break;
                case 557846283:
                    int findKeyResultDouble = digitalkeyEventChangeValue.getFindKeyResultDouble();
                    this.d1.setFindKeyResultDouble(findKeyResultDouble);
                    LogUtil.verbose(str, "ID_FIND_KEY_RESULT_DOUBLE:" + findKeyResultDouble);
                    break;
                case 557846284:
                    int pairingProcessCallBackSingle = digitalkeyEventChangeValue.getPairingProcessCallBackSingle();
                    this.d1.setPairingProcessCallBackSingle(pairingProcessCallBackSingle);
                    LogUtil.verbose(str, "ID_PAIRING_PROCESS_CALLBACK_SINGLE:" + pairingProcessCallBackSingle);
                    break;
                case 557846285:
                    int pairingProcessCallBackDouble = digitalkeyEventChangeValue.getPairingProcessCallBackDouble();
                    this.d1.setPairingProcessCallBackDouble(pairingProcessCallBackDouble);
                    LogUtil.verbose(str, "ID_PAIRING_PROCESS_CALLBACK_DOUBLE:" + pairingProcessCallBackDouble);
                    break;
                case 557846286:
                    int pairingResultSingle = digitalkeyEventChangeValue.getPairingResultSingle();
                    this.d1.setPairingResultSingle(pairingResultSingle);
                    LogUtil.verbose(str, "ID_PAIRING_RESULT_SINGLE:" + pairingResultSingle);
                    break;
                case 557846287:
                    int pairingResultDouble = digitalkeyEventChangeValue.getPairingResultDouble();
                    this.d1.setPairingResultDouble(pairingResultDouble);
                    LogUtil.verbose(str, "ID_PAIRING_RESULT_DOUBLE:" + pairingResultDouble);
                    break;
                case 557846288:
                    int offlineOperatPairingResult = digitalkeyEventChangeValue.getOfflineOperatPairingResult();
                    this.d1.setOfflineOperatPairingResult(offlineOperatPairingResult);
                    LogUtil.verbose(str, "OFF_LINE_OPERAT_PAIRING_RESULTR:" + offlineOperatPairingResult);
                    break;
                default:
                    switch (i) {
                        case 557846293:
                            int responseFeedbackInformR = digitalkeyEventChangeValue.getResponseFeedbackInformR();
                            this.d1.setResponseFeedbackInformR(responseFeedbackInformR);
                            LogUtil.verbose(str, "ID_RESPONSE_FEED_BACK_INFORM_R:" + responseFeedbackInformR);
                            break;
                        case 557846297:
                            int padOfflineOperationRespR = digitalkeyEventChangeValue.getPadOfflineOperationRespR();
                            this.d1.setPadOfflineOperationRespR(padOfflineOperationRespR);
                            LogUtil.verbose(str, "ID_PAD_OFFLINE_OPERATION_RESP_R:" + padOfflineOperationRespR);
                            break;
                        case 557846309:
                            int nfcKeyUnlockResultR = digitalkeyEventChangeValue.getNfcKeyUnlockResultR();
                            this.d1.setNfcKeyUnlockResultR(nfcKeyUnlockResultR);
                            LogUtil.debug(str, "ID_NFC_KEY_UNLOCK_RESULT_R:" + nfcKeyUnlockResultR);
                            break;
                        case 558960432:
                            byte[] opcodeDataValueR = digitalkeyEventChangeValue.getOpcodeDataValueR();
                            this.d1.setOpcodeDataValueR(opcodeDataValueR);
                            LogUtil.debug(str, "ID_OPCODE_DATA_VALUE_R:" + opcodeDataValueR);
                            break;
                        default:
                            switch (i) {
                                case 557846303:
                                    int bleOperateCommandR = digitalkeyEventChangeValue.getBleOperateCommandR();
                                    this.d1.setBleOperateCommandR(bleOperateCommandR);
                                    LogUtil.verbose(str, "ID_BLE_OPERATE_COMMAND_R:" + bleOperateCommandR);
                                    break;
                                case 557846304:
                                    int bleOperateResultR = digitalkeyEventChangeValue.getBleOperateResultR();
                                    this.d1.setBleOperateResultR(bleOperateResultR);
                                    LogUtil.verbose(str, "ID_BLE_OPERATE_RESULT_R:" + bleOperateResultR);
                                    break;
                                default:
                                    switch (i) {
                                        case 557846313:
                                            int opcodeStartFunctionR = digitalkeyEventChangeValue.getOpcodeStartFunctionR();
                                            this.d1.setOpcodeStartFunctionR(opcodeStartFunctionR);
                                            LogUtil.debug(str, "ID_OPCODE_START_FUNCTION_R:" + opcodeStartFunctionR);
                                            break;
                                        case 557846314:
                                            int padExitPinCodeProcessR = digitalkeyEventChangeValue.getPadExitPinCodeProcessR();
                                            this.d1.setPadExitPinCodeProcessR(padExitPinCodeProcessR);
                                            LogUtil.debug(str, "ID_PAD_EXIT_PIN_CODE_PROCESS_R:" + padExitPinCodeProcessR);
                                            break;
                                        default:
                                            switch (i) {
                                                case 557846316:
                                                    int opcodeDataLengthR = digitalkeyEventChangeValue.getOpcodeDataLengthR();
                                                    this.d1.setOpcodeDataLengthR(opcodeDataLengthR);
                                                    LogUtil.debug(str, "ID_OPCODE_DATA_LENGTH_R:" + opcodeDataLengthR);
                                                    break;
                                                case 557846317:
                                                    int opcodeChecksumValueR = digitalkeyEventChangeValue.getOpcodeChecksumValueR();
                                                    this.d1.setOpcodeChecksumValueR(opcodeChecksumValueR);
                                                    LogUtil.debug(str, "ID_OPCODE_CHECKSUM_VALUE_R:" + opcodeChecksumValueR);
                                                    break;
                                                case 557846318:
                                                    int opcodeCommandR = digitalkeyEventChangeValue.getOpcodeCommandR();
                                                    this.d1.setOpcodeCommandR(opcodeCommandR);
                                                    LogUtil.debug(str, "ID_OPCODE_COMMAND_R:" + opcodeCommandR);
                                                    break;
                                                case 557846319:
                                                    int responseResultOfOpcodeR = digitalkeyEventChangeValue.getResponseResultOfOpcodeR();
                                                    this.d1.setResponseResultOfOpcodeR(responseResultOfOpcodeR);
                                                    LogUtil.debug(str, "ID_RESPONSE_RESULT_OF_OPCODE_R:" + responseResultOfOpcodeR);
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
            this.a1.m(i, i2, this.d1);
            LogUtil.verbose(str, "notifyDigitalKeyEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(int i, int i2, ReminderEventChangeValue reminderEventChangeValue) {
        String str = g1;
        try {
            switch (i) {
                case 557845248:
                    int i3 = reminderEventChangeValue.getonIndChangedstart();
                    this.d1.setMonIndChangedstarta(i3);
                    LogUtil.verbose(str, "ID_IND_CHANGED_START:" + i3);
                    break;
                case 557845249:
                    int i4 = reminderEventChangeValue.getonIndChangedPowerFailure();
                    this.d1.setMonIndChangedPowerFailurea(i4);
                    LogUtil.verbose(str, "ID_IND_CHANGED_POWER_FAILURE:" + i4);
                    break;
                case 557845250:
                    int i5 = reminderEventChangeValue.getonIndChangedCloudServiceSignal();
                    this.d1.setMonIndChangedCloudServiceSignal(i5);
                    LogUtil.verbose(str, "ID_IND_CHANGED_CLOUD_SERVICE_SIGNAL:" + i5);
                    break;
                default:
                    switch (i) {
                        case 557845253:
                            float f = reminderEventChangeValue.getonIndChangedParkingBrakeNotReleasedEpb();
                            this.d1.setMonIndChangedParkingBrakeNotReleasedEpb(f);
                            LogUtil.verbose(str, "ID_IND_CHANGED_PARKING_BRAKE_NOT_RELEASED_EPB:" + f);
                            break;
                        case 557845254:
                            int i6 = reminderEventChangeValue.getonIndChangedDoorNotClosedLeftFront();
                            this.d1.setMonIndChangedDoorNotClosedLeftFronta(i6);
                            LogUtil.verbose(str, "ID_IND_CHANGED_DOOR_NOT_CLOSED_LEFT_FRONT:" + i6);
                            break;
                        case 557845255:
                            int i7 = reminderEventChangeValue.getonIndChangedDoorNotClosedRightFront();
                            this.d1.setMonIndChangedDoorNotClosedRightFronta(i7);
                            LogUtil.verbose(str, "ID_IND_CHANGED_DOOR_NOT_CLOSED_RIGHT_FRONT:" + i7);
                            break;
                        case 557845256:
                            int i8 = reminderEventChangeValue.getonIndChangedDoorNotClosedLeftBack();
                            this.d1.setMonIndChangedDoorNotClosedLeftBacka(i8);
                            LogUtil.verbose(str, "ID_IND_CHANGED_DOOR_NOT_CLOSED_LEFT_BACK:" + i8);
                            break;
                        case 557845257:
                            int i9 = reminderEventChangeValue.getonIndChangedDoorNotClosedRightBack();
                            this.d1.setMonIndChangedDoorNotClosedRightBacka(i9);
                            LogUtil.verbose(str, "ID_IND_CHANGED_DOOR_NOT_CLOSED_RIGHT_BACK:" + i9);
                            break;
                        default:
                            switch (i) {
                                case 557845259:
                                    int i10 = reminderEventChangeValue.getonIndChangedLeaveTheVehicleAtNonPGearINS();
                                    this.d1.setMonIndChangedLeaveTheVehicleAtNonpGearIns(i10);
                                    LogUtil.verbose(str, "ID_IND_CHANGED_LEAVE_THE_VEHICLE_AT_NONP_GEAR_INS:" + i10);
                                    break;
                                case 557845260:
                                    int i11 = reminderEventChangeValue.getonIndChangedLeaveTheVehicleAtNonPGearVTOG();
                                    this.d1.setMonIndChangedLeaveTheVehicleAtNonpGearVtog(i11);
                                    LogUtil.verbose(str, "ID_IND_CHANGED_LEAVE_THE_VEHICLE_AT_NONP_GEAR_VTOG:" + i11);
                                    break;
                                case 557845261:
                                    int i12 = reminderEventChangeValue.getonIndChangedRemoteDrivingSuspension();
                                    this.d1.setMonIndChangedRemoteDrivingSuspensiona(i12);
                                    LogUtil.verbose(str, "ID_IND_CHANGED_REMOTE_DRIVING_SUSPENSI:" + i12);
                                    break;
                                default:
                                    switch (i) {
                                        case 557845263:
                                            int i13 = reminderEventChangeValue.getonIndChangedKeyNotDetected();
                                            this.d1.setMonIndChangedKeyNotDetecteda(i13);
                                            LogUtil.verbose(str, "ID_IND_CHANGED_KEY_NOT_DETECTED:" + i13);
                                            break;
                                        case 557845264:
                                            int i14 = reminderEventChangeValue.getonIndChangedNightRunningLights();
                                            this.d1.setMonIndChangedNightRunningLightsa(i14);
                                            LogUtil.verbose(str, "ID_IND_CHANGED_NIGHT_RUNNING_LIGHTS:" + i14);
                                            break;
                                        case 557845265:
                                            int i15 = reminderEventChangeValue.getonIndChangedInsufficientFuel();
                                            this.d1.setMonIndChangedInsufficientFuela(i15);
                                            LogUtil.verbose(str, "ID_IND_CHANGED_INSUFFICIENT_FUEL:" + i15);
                                            break;
                                        case 557845266:
                                            int i16 = reminderEventChangeValue.getonIndChangedExternalDischargeTermination();
                                            this.d1.setMonIndChangedExternalDischargeTermination(i16);
                                            LogUtil.verbose(str, "ID_IND_CHANGED_EXTERNAL_DISCHARGE_TERMINATION:" + i16);
                                            break;
                                        case 557845267:
                                            int i17 = reminderEventChangeValue.getonIndChangedPowerSystemFault();
                                            this.d1.setMonIndChangedPowerSystemFaulta(i17);
                                            LogUtil.verbose(str, "ID_IND_CHANGED_POWER_SYSTEM_FAULT:" + i17);
                                            break;
                                        default:
                                            switch (i) {
                                                case 557845269:
                                                    int i18 = reminderEventChangeValue.getonIndChangedBeCarefulCarsBackLeft();
                                                    this.d1.setMonIndChangedBeCarefulCarsBackLeft(i18);
                                                    LogUtil.verbose(str, "ID_IND_CHANGED_BECAREFUL_CARSBACK_LEFT:" + i18);
                                                    break;
                                                case 557845270:
                                                    int i19 = reminderEventChangeValue.getonIndChangedBeCarefulCarsBackRight();
                                                    this.d1.setMonIndChangedBeCarefulCarsBackRight(i19);
                                                    LogUtil.verbose(str, "ID_IND_CHANGED_BECAREFUL_CARSBACK_RIGHT:" + i19);
                                                    break;
                                                case 557845271:
                                                    int i20 = reminderEventChangeValue.getonIndChangedAdas0x32D();
                                                    this.d1.setMonIndChangedAdasFour(i20);
                                                    LogUtil.verbose(str, "ID_IND_CHANGED_ADAS0_X32D:" + i20);
                                                    break;
                                                case 557845272:
                                                    int i21 = reminderEventChangeValue.getonIndChangedAdas0x26CFirst();
                                                    this.d1.setMonIndChangedAdas0x26CFirst(i21);
                                                    LogUtil.verbose(str, "ID_IND_CHANGED_ADAS0_X26C_FIRST:" + i21);
                                                    break;
                                                case 557845273:
                                                    int i22 = reminderEventChangeValue.getonIndChangedRmdRapidAccelerationLimit();
                                                    this.d1.setonIndChangedRmdRapidAccelerationLimit(i22);
                                                    LogUtil.verbose(str, "ID_IND_CHANGED_RMD_RAPID_ACCELERATION_LIMIT:" + i22);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 557845275:
                                                            int i23 = reminderEventChangeValue.getonIndChangedRmdRearAirConditioningUnlock();
                                                            this.d1.setonIndChangedRmdRearAirConditioningUnlock(i23);
                                                            LogUtil.verbose(str, "ID_IND_CHANGED_RMD_REAR_AIR_CONDITIONING_UNLOCK:" + i23);
                                                            break;
                                                        case 557845276:
                                                            int rmdTakePhone = reminderEventChangeValue.getRmdTakePhone();
                                                            this.Y0 = rmdTakePhone;
                                                            this.d1.setRmdTakePhone(rmdTakePhone);
                                                            LogUtil.verbose(str, "ID_RMD_TAKE_PHONE:" + this.Y0);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 557846612:
                                                                    int i24 = reminderEventChangeValue.getonIndChangedRmdRearAirConditioningOperateInvalid();
                                                                    this.d1.setonIndChangedRmdRearAirConditioningOperateInvalid(i24);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_RMD_REAR_AIR_CONDITIONING_OPERATE_INVALID:" + i24);
                                                                    break;
                                                                case 557856774:
                                                                    int i25 = reminderEventChangeValue.getonIndChangedAdas0x29CFirst();
                                                                    this.d1.setMonIndChangedAdasFirst(i25);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_ADAS0_X29CZ_FIRST:" + i25);
                                                                    break;
                                                                case 557856776:
                                                                    int i26 = reminderEventChangeValue.getonIndChangedAdas0x316();
                                                                    this.d1.setMonIndChangedAdasThree(i26);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_ADAS0_X316:" + i26);
                                                                    break;
                                                                case 557857284:
                                                                    int i27 = reminderEventChangeValue.getonIndChangedParkingBrakeNotReleasedINS();
                                                                    this.d1.setMonIndChangedParkingBrakeNotReleasedIns(i27);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_PARKING_BRAKE_NOT_RELEASED_INS:" + i27);
                                                                    break;
                                                                case 557859850:
                                                                    int i28 = reminderEventChangeValue.getonIndChangedLowkeyPower();
                                                                    this.d1.setMonIndChangedLowkeyPowera(i28);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_LOWKEY_POWER:" + i28);
                                                                    break;
                                                                case 557860386:
                                                                    int socValueR = reminderEventChangeValue.getSocValueR();
                                                                    this.d1.setSocValueR(socValueR);
                                                                    LogUtil.verbose(str, "ID_SOC_VALUER:" + socValueR);
                                                                    break;
                                                                case 557862927:
                                                                    int otaPowerLevel = reminderEventChangeValue.getOtaPowerLevel();
                                                                    this.d1.setOtaPowerLevel(otaPowerLevel);
                                                                    LogUtil.verbose(str, "ID_OTA_POWER_LEVEL:" + otaPowerLevel);
                                                                    break;
                                                                case 557862953:
                                                                    int leftStrutMorotRunningState = reminderEventChangeValue.getLeftStrutMorotRunningState();
                                                                    this.Z0 = leftStrutMorotRunningState;
                                                                    this.d1.setLeftStrutMorotRunningState(leftStrutMorotRunningState);
                                                                    LogUtil.verbose(str, "ID_LEFT_STRUT_MOROT_RUNNING_STATE:" + this.Z0);
                                                                    break;
                                                                case 557870400:
                                                                    int accTextInfo = reminderEventChangeValue.getAccTextInfo();
                                                                    this.d1.setAccTextInfo(accTextInfo);
                                                                    LogUtil.verbose(str, "ID_ACC_TEXTINFO:" + accTextInfo);
                                                                    break;
                                                                case 557870438:
                                                                    int i29 = reminderEventChangeValue.getonIndChangedAdas0x29CSecond();
                                                                    this.d1.setMonIndChangedAdasSecond(i29);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_ADAS0_X29C_SECOND:" + i29);
                                                                    break;
                                                                case 557870596:
                                                                    int i30 = reminderEventChangeValue.getonIndChangedNotWearingSeatBelt();
                                                                    this.d1.setMonIndChangedNotWearingSeatBelta(i30);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_NOT_WEARING_SEAT_BELT:" + i30);
                                                                    break;
                                                                case 557875227:
                                                                    int i31 = reminderEventChangeValue.getonIndChangedLowBatteryVoltage();
                                                                    this.d1.setMonIndChangedLowBatteryVoltagea(i31);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_LOW_BATTERY_VOLTAGE:" + i31);
                                                                    break;
                                                                case 559957505:
                                                                    float f2 = reminderEventChangeValue.getonIndChangedParkingBrakeNotReleasedEsc();
                                                                    this.d1.setMonIndChangedParkingBrakeNotReleasedEsc(f2);
                                                                    LogUtil.verbose(str, "ID_IND_CHANGED_PARKING_BRAKE_NOT_RELEASED_ESC:" + f2);
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 557862943:
                                                                            int doorStateLeftFront = reminderEventChangeValue.getDoorStateLeftFront();
                                                                            this.d1.setmDoorStateLeftFront(doorStateLeftFront);
                                                                            LogUtil.verbose(str, "ID_DOOR_STATE_LEFT_FRONT:" + doorStateLeftFront);
                                                                            break;
                                                                        case 557862944:
                                                                            int doorStateRightFront = reminderEventChangeValue.getDoorStateRightFront();
                                                                            this.d1.setmDoorStateRightFront(doorStateRightFront);
                                                                            LogUtil.verbose(str, "ID_DOOR_STATE_RIGHT_FRONT:" + doorStateRightFront);
                                                                            break;
                                                                        case 557862945:
                                                                            int doorStateLeftRear = reminderEventChangeValue.getDoorStateLeftRear();
                                                                            this.d1.setmDoorStateLeftRear(doorStateLeftRear);
                                                                            LogUtil.verbose(str, "ID_DOOR_STATE_LEFT_REAR:" + doorStateLeftRear);
                                                                            break;
                                                                        case 557862946:
                                                                            int doorStateRightRear = reminderEventChangeValue.getDoorStateRightRear();
                                                                            this.d1.setmDoorStateRightRear(doorStateRightRear);
                                                                            LogUtil.verbose(str, "DOOR_STATE_RIGHT_REAR:" + doorStateRightRear);
                                                                            break;
                                                                        case 557862947:
                                                                            int doorStateLuggage = reminderEventChangeValue.getDoorStateLuggage();
                                                                            this.X0 = doorStateLuggage;
                                                                            this.d1.setmDoorStateLuggage(doorStateLuggage);
                                                                            LogUtil.verbose(str, "ID_DOOR_STATE_LUGGAGE:" + this.X0);
                                                                            break;
                                                                        case 557862948:
                                                                            int doorStateHood = reminderEventChangeValue.getDoorStateHood();
                                                                            this.W0 = doorStateHood;
                                                                            this.d1.setmDoorStateHood(doorStateHood);
                                                                            LogUtil.verbose(str, "ID_DOOR_STATE_HOOD:" + this.W0);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.a1.m(i, i2, this.d1);
        LogUtil.verbose(str, "notifyReminderEventValue:" + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(int i, int i2, XcallEventChangeValue xcallEventChangeValue) {
        String str = g1;
        try {
            switch (i) {
                case 557872642:
                    int callTypeR = xcallEventChangeValue.getCallTypeR();
                    this.d1.setCallTypeR(callTypeR);
                    LogUtil.verbose(str, "ID_CALL_TYPER:" + callTypeR);
                    break;
                case 557872643:
                    int callStatusR = xcallEventChangeValue.getCallStatusR();
                    this.d1.setCallStatusR(callStatusR);
                    LogUtil.verbose(str, "ID_CALL_STATUSR:" + callStatusR);
                    break;
                case 557872644:
                    int callBackModeR = xcallEventChangeValue.getCallBackModeR();
                    this.d1.setCallBackModeR(callBackModeR);
                    LogUtil.verbose(str, "ID_CALL_BACKMODER:" + callBackModeR);
                    break;
            }
            this.a1.m(i, i2, this.d1);
            LogUtil.verbose(str, "notifyXcallEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void y() {
        LogUtil.info(g1, "quit");
        if (this.b1 != null) {
            a aVar = this.c1;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.b1.quitSafely();
        }
    }

    @Override // com.ts.appservice.caradapter.d.e
    public void a(DigitalkeyEventChangeValue digitalkeyEventChangeValue) {
        String str = g1;
        if (digitalkeyEventChangeValue == null) {
            LogUtil.error(str, "onDigitalChangeEvent:value=null");
            return;
        }
        int propertyId = digitalkeyEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onDigitalChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 13;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = digitalkeyEventChangeValue.getStatus();
        messageObtain.obj = digitalkeyEventChangeValue;
        this.c1.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.t
    public void b(ReminderEventChangeValue reminderEventChangeValue) {
        String str = g1;
        if (reminderEventChangeValue == null) {
            LogUtil.error(str, "onReminderChangeEvent:value=null");
            return;
        }
        int propertyId = reminderEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onReminderChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 12;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = reminderEventChangeValue.getStatus();
        messageObtain.obj = reminderEventChangeValue;
        this.c1.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.B
    public void d(XcallEventChangeValue xcallEventChangeValue) {
        String str = g1;
        if (xcallEventChangeValue == null) {
            LogUtil.error(str, "onXcallChangeEvent:value=null");
            return;
        }
        int propertyId = xcallEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onXcallChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 15;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = xcallEventChangeValue.getStatus();
        messageObtain.obj = xcallEventChangeValue;
        this.c1.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.f
    public void f(DipilotEventChangeValue dipilotEventChangeValue) {
        String str = g1;
        if (dipilotEventChangeValue == null) {
            LogUtil.error(str, "onDipilotChangeEvent:value=null");
            return;
        }
        int propertyId = dipilotEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onDipilotChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 11;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = dipilotEventChangeValue.getStatus();
        messageObtain.obj = dipilotEventChangeValue;
        this.c1.sendMessage(messageObtain);
    }

    public int getADASOnlineState() {
        String str = g1;
        LogUtil.verbose(str, "getADASOnlineState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int blindMonitorStateSecond = carVendorDipilotManager.getBlindMonitorStateSecond();
            LogUtil.verbose(str, "getBlindMonitorStateSecond=" + blindMonitorStateSecond);
            return blindMonitorStateSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAINavigationState() {
        String str = g1;
        LogUtil.verbose(str, "getAINavigationState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856776, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAINavigationState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAbnormalVehicleWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getAbnormalVehicleWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int abnormalVehicleWarningStateR = carVendorDipilotManager.getAbnormalVehicleWarningStateR();
            LogUtil.verbose(str, "getAbnormalVehicleWarningStateR=" + abnormalVehicleWarningStateR);
            return abnormalVehicleWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAccState() {
        int accStateR;
        String str = g1;
        LogUtil.verbose(str, " getAccState.in service");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                accStateR = carVendorDipilotManager.getAccStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                accStateR = -1;
            }
            LogUtil.verbose(str, " getAccState.out service =" + accStateR);
            return accStateR;
        }
        LogUtil.verbose(str, " getAccState.null");
        accStateR = -1;
        LogUtil.verbose(str, " getAccState.out service =" + accStateR);
        return accStateR;
    }

    public byte getAdaptiveFrontLightFault() {
        String str = g1;
        LogUtil.verbose(str, " getAdaptiveFrontLightFault.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856917, 0);
        if (carPropertyValueA == null) {
            return (byte) 0;
        }
        LogUtil.verbose(str, "getAdaptiveFrontLightFault prop value " + carPropertyValueA.getValue());
        return Byte.parseByte(carPropertyValueA.getValue() + "");
    }

    public int getAdasSoftVersion() {
        String str = g1;
        LogUtil.verbose(str, "getAdasSoftVersion is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int adasSoftVersionR = carVendorDipilotManager.getAdasSoftVersionR();
            LogUtil.verbose(str, "getAdasSoftVersionR=" + adasSoftVersionR);
            return adasSoftVersionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getAdativeCruiseControlFault() {
        int adativeCruiseControlFaultR;
        String str = g1;
        LogUtil.verbose(str, " getAdativeCruiseControlFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                adativeCruiseControlFaultR = carVendorDipilotManager.getAdativeCruiseControlFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                adativeCruiseControlFaultR = -1;
            }
            LogUtil.verbose(str, " getAdativeCruiseControlFault.out=" + adativeCruiseControlFaultR + ",return:" + adativeCruiseControlFaultR);
            return Byte.parseByte(Integer.toString(adativeCruiseControlFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        adativeCruiseControlFaultR = -1;
        LogUtil.verbose(str, " getAdativeCruiseControlFault.out=" + adativeCruiseControlFaultR + ",return:" + adativeCruiseControlFaultR);
        return Byte.parseByte(Integer.toString(adativeCruiseControlFaultR));
    }

    public int getAebDecActive() {
        int aEBDecActiveR;
        String str = g1;
        LogUtil.verbose(str, " getAebDecActive.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                aEBDecActiveR = carVendorDipilotManager.getAEBDecActiveR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                aEBDecActiveR = -1;
            }
            LogUtil.verbose(str, " getAebDecActive.out=" + aEBDecActiveR);
            return aEBDecActiveR;
        }
        LogUtil.verbose(str, " getAebDecActive.null == mCarVendorDipilotManager");
        aEBDecActiveR = -1;
        LogUtil.verbose(str, " getAebDecActive.out=" + aEBDecActiveR);
        return aEBDecActiveR;
    }

    public int getAebFaultState() {
        String str = g1;
        LogUtil.verbose(str, "getAebFaultState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int aebFaultStateR = carVendorDipilotManager.getAebFaultStateR();
            LogUtil.verbose(str, "getAebFaultStateR=" + aebFaultStateR);
            return aebFaultStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAiCareState() {
        String str = g1;
        LogUtil.verbose(str, "getAiCareState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int aiCareStateR = carVendorDipilotManager.getAiCareStateR();
            LogUtil.verbose(str, "getAiCareStateR=" + aiCareStateR);
            return aiCareStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAiDistanceLight2B4State() {
        String str = g1;
        LogUtil.verbose(str, "getAiDistanceLight2B4State is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557870472, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAiDistanceLight2B4State prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAiDistanceLight316State() {
        String str = g1;
        LogUtil.verbose(str, "getAiDistanceLight316State is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856804, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAiDistanceLight316State prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAiEmergencyBrakeState() {
        String str = g1;
        LogUtil.verbose(str, "getAiEmergencyBrakeState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int aiEmergencyBrakeStateR = carVendorDipilotManager.getAiEmergencyBrakeStateR();
            LogUtil.verbose(str, "getAiEmergencyBrakeStateR=" + aiEmergencyBrakeStateR);
            return aiEmergencyBrakeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAiMatrixLight3ACState() {
        String str = g1;
        LogUtil.verbose(str, "getAiMatrixLight3ACState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int aiMatrixLightStateFirst = carVendorDipilotManager.getAiMatrixLightStateFirst();
            LogUtil.verbose(str, "getAiMatrixLightStateFirst=" + aiMatrixLightStateFirst);
            return aiMatrixLightStateFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAiMatrixLight3ADState() {
        String str = g1;
        LogUtil.verbose(str, "getAiMatrixLight3ADState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int aiMatrixLightStateSecond = carVendorDipilotManager.getAiMatrixLightStateSecond();
            LogUtil.verbose(str, "getAiMatrixLightStateSecond=" + aiMatrixLightStateSecond);
            return aiMatrixLightStateSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAiMatrixLightState() {
        String str = g1;
        LogUtil.verbose(str, "getAiMatrixLightState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int aiMatrixLightStateFirst = carVendorDipilotManager.getAiMatrixLightStateFirst();
            int aiMatrixLightStateSecond = this.f62a.getAiMatrixLightStateSecond();
            LogUtil.verbose(str, "getAiMatrixLightState=" + aiMatrixLightStateFirst + "," + aiMatrixLightStateSecond);
            return (aiMatrixLightStateFirst == 1 || aiMatrixLightStateSecond == 1) ? 1 : 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAlarmType() {
        String str = g1;
        LogUtil.verbose(str, "getAlarmType");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int alarmTypeR = carVendorDipilotManager.getAlarmTypeR();
            LogUtil.verbose(str, "getAlarmTypeR=" + alarmTypeR);
            return alarmTypeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getAudioBoxVersion() {
        LogUtil.verbose(g1, "Service getAudioBoxVersion");
        return "";
    }

    public byte getAutoEmergencyFault() {
        int autoEmergencyFaultR;
        String str = g1;
        LogUtil.verbose(str, " getAutoEmergencyFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                autoEmergencyFaultR = carVendorDipilotManager.getAutoEmergencyFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                autoEmergencyFaultR = -1;
            }
            LogUtil.verbose(str, " getAutoEmergencyFault.out=" + autoEmergencyFaultR + ",return:" + autoEmergencyFaultR);
            return Byte.parseByte(Integer.toString(autoEmergencyFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        autoEmergencyFaultR = -1;
        LogUtil.verbose(str, " getAutoEmergencyFault.out=" + autoEmergencyFaultR + ",return:" + autoEmergencyFaultR);
        return Byte.parseByte(Integer.toString(autoEmergencyFaultR));
    }

    public int getAutoParkigAssistMode() {
        int autoParkigAssistModeR;
        String str = g1;
        LogUtil.verbose(str, " getAutoParkigAssistMode.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                autoParkigAssistModeR = carVendorDipilotManager.getAutoParkigAssistModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                autoParkigAssistModeR = -1;
            }
            LogUtil.verbose(str, " getAutoParkigAssistMode.out=" + autoParkigAssistModeR);
            return autoParkigAssistModeR;
        }
        LogUtil.verbose(str, " getAutoParkigAssistMode.null == mCarVendorDipilotManager");
        autoParkigAssistModeR = -1;
        LogUtil.verbose(str, " getAutoParkigAssistMode.out=" + autoParkigAssistModeR);
        return autoParkigAssistModeR;
    }

    public int getAutoParkigAssistStatus() {
        int autoParkigAssistStatusR;
        String str = g1;
        LogUtil.verbose(str, " getAutoParkigAssistStatus.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                autoParkigAssistStatusR = carVendorDipilotManager.getAutoParkigAssistStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                autoParkigAssistStatusR = -1;
            }
            LogUtil.verbose(str, " getAutoParkigAssistStatus.out=" + autoParkigAssistStatusR);
            return autoParkigAssistStatusR;
        }
        LogUtil.verbose(str, " getAutoParkigAssistStatus.null == mCarVendorDipilotManager");
        autoParkigAssistStatusR = -1;
        LogUtil.verbose(str, " getAutoParkigAssistStatus.out=" + autoParkigAssistStatusR);
        return autoParkigAssistStatusR;
    }

    public int getAutoParkingState() {
        String str = g1;
        LogUtil.verbose(str, " getAutoParkingState.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856931, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAutoParkingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAutomatedValetParkingStatus() {
        int automatedValetParkingStatusR;
        String str = g1;
        LogUtil.verbose(str, " getAutomatedValetParkingStatus.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                automatedValetParkingStatusR = carVendorDipilotManager.getAutomatedValetParkingStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                automatedValetParkingStatusR = -1;
            }
            LogUtil.verbose(str, " getAutomatedValetParkingStatus.out=" + automatedValetParkingStatusR);
            return automatedValetParkingStatusR;
        }
        LogUtil.verbose(str, " getAutomatedValetParkingStatus.null == mCarVendorDipilotManager");
        automatedValetParkingStatusR = -1;
        LogUtil.verbose(str, " getAutomatedValetParkingStatus.out=" + automatedValetParkingStatusR);
        return automatedValetParkingStatusR;
    }

    public int getAutomaticLaneChangeState() {
        return 0;
    }

    public int getAutomaticParkingBackgroundParkingPush() {
        return 0;
    }

    public int getAutomaticParkingVoicPrompt() {
        return 0;
    }

    public byte getBlindFault() {
        String str = g1;
        LogUtil.verbose(str, " getBlindFault.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856899, 0);
        if (carPropertyValueA == null) {
            return (byte) 0;
        }
        LogUtil.verbose(str, "getBlindFault prop value " + carPropertyValueA.getValue());
        return Byte.parseByte(carPropertyValueA.getValue() + "");
    }

    public int getBlindMonitorState() {
        LogUtil.verbose(g1, "getBlindMonitorState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            return (carVendorDipilotManager.getBlindMonitorStateFirst() == 1 || this.f62a.getBlindMonitorStateSecond() == 1) ? 1 : 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBlindMonitorState1() {
        String str = g1;
        LogUtil.verbose(str, "getBlindMonitorState1 is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856812, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getBlindMonitorState1 prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getBlindMonitorState2() {
        String str = g1;
        LogUtil.verbose(str, "getBlindMonitorState2 is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856813, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getBlindMonitorState1 prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getBluetoothUnlockType() {
        int bluetoothUnlockTypeR;
        String str = g1;
        LogUtil.verbose(str, " getBluetoothUnlockType.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                bluetoothUnlockTypeR = carVendorDigitalkeyManager.getBluetoothUnlockTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                bluetoothUnlockTypeR = -1;
            }
            LogUtil.verbose(str, " getBluetoothUnlockType.out service =" + bluetoothUnlockTypeR);
            return bluetoothUnlockTypeR;
        }
        LogUtil.verbose(str, " getBluetoothUnlockType.null");
        bluetoothUnlockTypeR = -1;
        LogUtil.verbose(str, " getBluetoothUnlockType.out service =" + bluetoothUnlockTypeR);
        return bluetoothUnlockTypeR;
    }

    public int getBsdState() {
        return 0;
    }

    public int getBsisEnableR() {
        String str = g1;
        LogUtil.info(str, "getBsisEnableR");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int bsisEnableR = carVendorDipilotManager.getBsisEnableR();
            LogUtil.verbose(str, "getBsisEnableR=" + bsisEnableR);
            return bsisEnableR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getBsisFault() {
        int bsisFaultR;
        String str = g1;
        LogUtil.verbose(str, " getBsisFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                bsisFaultR = carVendorDipilotManager.getBsisFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                bsisFaultR = -1;
            }
            LogUtil.verbose(str, " getBsisFault.out=" + bsisFaultR + ",return:" + bsisFaultR);
            return Byte.parseByte(Integer.toString(bsisFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        bsisFaultR = -1;
        LogUtil.verbose(str, " getBsisFault.out=" + bsisFaultR + ",return:" + bsisFaultR);
        return Byte.parseByte(Integer.toString(bsisFaultR));
    }

    public int getBsisFuncStatusR() {
        String str = g1;
        LogUtil.info(str, "getBsisFuncStatusR");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int bsisFuncStatusR = carVendorDipilotManager.getBsisFuncStatusR();
            LogUtil.verbose(str, "getBsisFuncStatusR=" + bsisFuncStatusR);
            return bsisFuncStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getBydSystemVersion() {
        String str;
        synchronized (this.f1) {
            this.f1 = SystemProperties.get("ro.product.version.soc.name", "");
            LogUtil.verbose(g1, "getBydSystemVersion:" + this.f1);
            str = this.f1;
        }
        return str;
    }

    public int getCallBackMode() {
        String str = g1;
        LogUtil.verbose(str, "Service getCallBackMode");
        CarVendorXcallManager carVendorXcallManager = this.d;
        if (carVendorXcallManager == null) {
            return 0;
        }
        try {
            int callBackModeR = carVendorXcallManager.getCallBackModeR();
            LogUtil.verbose(str, "getCallStatus=" + callBackModeR);
            return callBackModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCallStatus() {
        String str = g1;
        LogUtil.verbose(str, "Service getCallStatus");
        CarVendorXcallManager carVendorXcallManager = this.d;
        if (carVendorXcallManager == null) {
            return 0;
        }
        try {
            int callStatusR = carVendorXcallManager.getCallStatusR();
            LogUtil.verbose(str, "getCallStatus=" + callStatusR);
            return callStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCallType() {
        String str = g1;
        LogUtil.verbose(str, "Service getCallType");
        CarVendorXcallManager carVendorXcallManager = this.d;
        if (carVendorXcallManager == null) {
            return 0;
        }
        try {
            int callTypeR = carVendorXcallManager.getCallTypeR();
            LogUtil.verbose(str, "getCallType=" + callTypeR);
            return callTypeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCanmsgFlagGwAdas1B() {
        return 0;
    }

    public int getCanmsgFlagGwAdas2Ib() {
        return 0;
    }

    public int getCanmsgFlagGwAdas3Ib() {
        return 0;
    }

    public int getCanmsgFlagGwAdas4Ib() {
        return 0;
    }

    public int getCanmsgFlagGwAdas5Ib() {
        return 0;
    }

    public int getCanmsgFlagGwAfs1B() {
        return 0;
    }

    public int getCanmsgFlagGwBsd1B() {
        return 0;
    }

    public int getCanmsgFlagGwBsdm2B() {
        return 0;
    }

    public int getCanmsgFlagGwEpb1B() {
        return 0;
    }

    public int getCanmsgFlagGwEps1B() {
        return 0;
    }

    public int getCanmsgFlagGwEscl1B() {
        return 0;
    }

    public int getCanmsgFlagGwFrr1() {
        return 0;
    }

    public int getCanmsgFlagGwMrr1B() {
        return 0;
    }

    public int getCanmsgFlagTel3B() {
        return 0;
    }

    public int getCanmsgFlagTel4B() {
        return 0;
    }

    public int getCanmsgFlagTel5B() {
        return 0;
    }

    public int getCanmsgFlagTel6B() {
        return 0;
    }

    public int getCarEngineType() {
        String str = g1;
        LogUtil.verbose(str, "Service getCarEngineType");
        CarVendorCarmodeManager carVendorCarmodeManager = this.c;
        if (carVendorCarmodeManager == null) {
            return 0;
        }
        try {
            int carEngineTypeR = carVendorCarmodeManager.getCarEngineTypeR();
            LogUtil.verbose(str, "getCarEngineType=" + carEngineTypeR);
            return carEngineTypeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCollisionWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getCollisionWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int collisionWarningStateR = carVendorDipilotManager.getCollisionWarningStateR();
            LogUtil.verbose(str, "getCollisionWarningStateR=" + collisionWarningStateR);
            return collisionWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getControlLossWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getControlLossWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int controlLossWarningStateR = carVendorDipilotManager.getControlLossWarningStateR();
            LogUtil.verbose(str, "getControlLossWarningStateR=" + controlLossWarningStateR);
            return controlLossWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCpdDelayStatus() {
        String str = g1;
        LogUtil.verbose(str, "getCpdDelayStatus is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int cpdDelayStatusR = carVendorDipilotManager.getCpdDelayStatusR();
            LogUtil.verbose(str, "getCpdDelayStatusR=" + cpdDelayStatusR);
            return cpdDelayStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCpdStatus() {
        String str = g1;
        LogUtil.verbose(str, "getCpdStatus is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int cpdStatusNewR = carVendorDipilotManager.getCpdStatusNewR();
            LogUtil.verbose(str, "getCpdStatusNewR=" + cpdStatusNewR);
            return cpdStatusNewR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCpdWarningState() {
        int cpdWarningStateR;
        String str = g1;
        LogUtil.verbose(str, " getCpdWarningState.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                cpdWarningStateR = carVendorDipilotManager.getCpdWarningStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                cpdWarningStateR = -1;
            }
            LogUtil.verbose(str, " getCpdWarningState.out=" + cpdWarningStateR);
            return cpdWarningStateR;
        }
        LogUtil.verbose(str, " getCpdWarningState.null == mCarVendorDipilotManager");
        cpdWarningStateR = -1;
        LogUtil.verbose(str, " getCpdWarningState.out=" + cpdWarningStateR);
        return cpdWarningStateR;
    }

    public int getCpdWorkingStatus() {
        int cpdWorkingStatusR;
        String str = g1;
        LogUtil.verbose(str, " getCpdWorkingStatus.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                cpdWorkingStatusR = carVendorDipilotManager.getCpdWorkingStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                cpdWorkingStatusR = -1;
            }
            LogUtil.verbose(str, " getCpdWorkingStatus.out=" + cpdWorkingStatusR);
            return cpdWorkingStatusR;
        }
        LogUtil.verbose(str, " getCpdWorkingStatus.null == mCarVendorDipilotManager");
        cpdWorkingStatusR = -1;
        LogUtil.verbose(str, " getCpdWorkingStatus.out=" + cpdWorkingStatusR);
        return cpdWorkingStatusR;
    }

    public int getCurrentStorePowerMode() {
        return 0;
    }

    public byte getCurveWarnFault() {
        int curveWarnFaultR;
        String str = g1;
        LogUtil.verbose(str, " getCurveWarnFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                curveWarnFaultR = carVendorDipilotManager.getCurveWarnFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                curveWarnFaultR = -1;
            }
            LogUtil.verbose(str, " getCurveWarnFault.out=" + curveWarnFaultR + ",return:" + curveWarnFaultR);
            return Byte.parseByte(Integer.toString(curveWarnFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        curveWarnFaultR = -1;
        LogUtil.verbose(str, " getCurveWarnFault.out=" + curveWarnFaultR + ",return:" + curveWarnFaultR);
        return Byte.parseByte(Integer.toString(curveWarnFaultR));
    }

    public int getDmsSwitchStatus() {
        String str = g1;
        LogUtil.verbose(str, "getDmsSwitchStatus is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int dmsSwitchStatusR = carVendorDipilotManager.getDmsSwitchStatusR();
            LogUtil.verbose(str, "getDmsSwitchStatusR=" + dmsSwitchStatusR);
            return dmsSwitchStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDnpStatus() {
        String str = g1;
        LogUtil.verbose(str, "getDnpStatus is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int dnpStatusR = carVendorDipilotManager.getDnpStatusR();
            LogUtil.verbose(str, "getDnpStatusR=" + dnpStatusR);
            return dnpStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDoNotPassWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getDoNotPassWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int doNotPassWarningStateR = carVendorDipilotManager.getDoNotPassWarningStateR();
            LogUtil.verbose(str, "getDoNotPassWarningStateR=" + doNotPassWarningStateR);
            return doNotPassWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getDoorOpenFault() {
        int doorOpenFaultR;
        String str = g1;
        LogUtil.verbose(str, " getDoorOpenFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                doorOpenFaultR = carVendorDipilotManager.getDoorOpenFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                doorOpenFaultR = -1;
            }
            LogUtil.verbose(str, " getDoorOpenFault.out=" + doorOpenFaultR + ",return:" + doorOpenFaultR);
            return Byte.parseByte(Integer.toString(doorOpenFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        doorOpenFaultR = -1;
        LogUtil.verbose(str, " getDoorOpenFault.out=" + doorOpenFaultR + ",return:" + doorOpenFaultR);
        return Byte.parseByte(Integer.toString(doorOpenFaultR));
    }

    public int getDriverCrashOutputState() {
        return 0;
    }

    public int getDriverStatusWarningState() {
        return 0;
    }

    public int getDrivingPasswordSettingConfig() {
        int drivingPasswordSettingConfigR;
        String str = g1;
        LogUtil.verbose(str, " getDrivingPasswordSettingConfig.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                drivingPasswordSettingConfigR = carVendorDigitalkeyManager.getDrivingPasswordSettingConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                drivingPasswordSettingConfigR = -1;
            }
            LogUtil.verbose(str, " getDrivingPasswordSettingConfig.out service =" + drivingPasswordSettingConfigR);
            return drivingPasswordSettingConfigR;
        }
        LogUtil.verbose(str, " getDrivingPasswordSettingConfig.null");
        drivingPasswordSettingConfigR = -1;
        LogUtil.verbose(str, " getDrivingPasswordSettingConfig.out service =" + drivingPasswordSettingConfigR);
        return drivingPasswordSettingConfigR;
    }

    public int getDrivingTipsType() {
        return 0;
    }

    public int getDrivingWarningState() {
        return 0;
    }

    public String getDspVersion() {
        LogUtil.verbose(g1, "Service getDspVersion");
        return "";
    }

    public int getE2EAIAssistanceState() {
        String str = g1;
        LogUtil.verbose(str, "getE2EAIAssistanceState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int e2EAIAssistanceStateR = carVendorDipilotManager.getE2EAIAssistanceStateR();
            LogUtil.verbose(str, "getE2EAIAssistanceStateR=" + e2EAIAssistanceStateR);
            return e2EAIAssistanceStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getElecCarBodyStablySysState() {
        String str = g1;
        LogUtil.verbose(str, "getElecCarBodyStablySysState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856838, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getElecCarBodyStablySysState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getElkTakeOverState() {
        String str = g1;
        LogUtil.verbose(str, "getElkTakeOverState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int elkTakeOverStateR = carVendorDipilotManager.getElkTakeOverStateR();
            LogUtil.verbose(str, "getElkTakeOverStateR=" + elkTakeOverStateR);
            return elkTakeOverStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEmergencyBrakeWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getEmergencyBrakeWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int emergencyBrakeWarningStateR = carVendorDipilotManager.getEmergencyBrakeWarningStateR();
            LogUtil.verbose(str, "getEmergencyBrakeWarningStateR=" + emergencyBrakeWarningStateR);
            return emergencyBrakeWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEmergencyLaneAssistance2F0State() {
        String str = g1;
        LogUtil.verbose(str, "getEmergencyLaneAssistance2F0State is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int emergencyLaneAssistanceStateSecond = carVendorDipilotManager.getEmergencyLaneAssistanceStateSecond();
            LogUtil.verbose(str, "getEmergencyLaneAssistanceStateSecond=" + emergencyLaneAssistanceStateSecond);
            return emergencyLaneAssistanceStateSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEmergencyLaneAssistance32FState() {
        String str = g1;
        LogUtil.verbose(str, "getEmergencyLaneAssistance32FState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int emergencyLaneAssistanceStateFirst = carVendorDipilotManager.getEmergencyLaneAssistanceStateFirst();
            LogUtil.verbose(str, "getEmergencyLaneAssistanceStateFirst=" + emergencyLaneAssistanceStateFirst);
            return emergencyLaneAssistanceStateFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getEmergencyLaneKeepFault() {
        int emergencyLaneKeepFaultR;
        String str = g1;
        LogUtil.verbose(str, " getEmergencyLaneKeepFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                emergencyLaneKeepFaultR = carVendorDipilotManager.getEmergencyLaneKeepFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                emergencyLaneKeepFaultR = -1;
            }
            LogUtil.verbose(str, " getEmergencyLaneKeepFault.out=" + emergencyLaneKeepFaultR + ",return:" + emergencyLaneKeepFaultR);
            return Byte.parseByte(Integer.toString(emergencyLaneKeepFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        emergencyLaneKeepFaultR = -1;
        LogUtil.verbose(str, " getEmergencyLaneKeepFault.out=" + emergencyLaneKeepFaultR + ",return:" + emergencyLaneKeepFaultR);
        return Byte.parseByte(Integer.toString(emergencyLaneKeepFaultR));
    }

    public int getEmergencyVehicleWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getEmergencyVehicleWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int emergencyVehicleWarningStateR = carVendorDipilotManager.getEmergencyVehicleWarningStateR();
            LogUtil.verbose(str, "getEmergencyVehicleWarningStateR=" + emergencyVehicleWarningStateR);
            return emergencyVehicleWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEspOffKeyStatus() {
        int espOffKeyStatusR;
        String str = g1;
        LogUtil.verbose(str, " getEspOffKeyStatus.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                espOffKeyStatusR = carVendorDipilotManager.getEspOffKeyStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                espOffKeyStatusR = -1;
            }
            LogUtil.verbose(str, " getEspOffKeyStatus.out=" + espOffKeyStatusR);
            return espOffKeyStatusR;
        }
        LogUtil.verbose(str, " getEspOffKeyStatus.null == mCarVendorDipilotManager");
        espOffKeyStatusR = -1;
        LogUtil.verbose(str, " getEspOffKeyStatus.out=" + espOffKeyStatusR);
        return espOffKeyStatusR;
    }

    public int getFcsrFault() {
        int fcsrFaultR;
        String str = g1;
        LogUtil.verbose(str, " getFcsrFault.in service");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                fcsrFaultR = carVendorDipilotManager.getFcsrFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fcsrFaultR = -1;
            }
            LogUtil.verbose(str, " getFcsrFault.out service =" + fcsrFaultR);
            return fcsrFaultR;
        }
        LogUtil.verbose(str, " getFcsrFault.null");
        fcsrFaultR = -1;
        LogUtil.verbose(str, " getFcsrFault.out service =" + fcsrFaultR);
        return fcsrFaultR;
    }

    public int getFcsrState() {
        int fcsrStateR;
        String str = g1;
        LogUtil.verbose(str, " getFcsrState.in service");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                fcsrStateR = carVendorDipilotManager.getFcsrStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fcsrStateR = -1;
            }
            LogUtil.verbose(str, " getFcsrState.out service =" + fcsrStateR);
            return fcsrStateR;
        }
        LogUtil.verbose(str, " getFcsrState.null");
        fcsrStateR = -1;
        LogUtil.verbose(str, " getFcsrState.out service =" + fcsrStateR);
        return fcsrStateR;
    }

    public int getFcwFaultState() {
        String str = g1;
        LogUtil.verbose(str, "getFcwFaultState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int fcwFaultStateR = carVendorDipilotManager.getFcwFaultStateR();
            LogUtil.verbose(str, "getFcwFaultStateR=" + fcwFaultStateR);
            return fcwFaultStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFingerprintCfgState() {
        return 0;
    }

    public int getFingerprintConsole() {
        return 0;
    }

    public int getFingerprintConsoleFail() {
        return 0;
    }

    public int getFingerprintConsoleId() {
        return 0;
    }

    public int getFingerprintConsoleState() {
        return 0;
    }

    public int getFingerprintDeleteReqAckState() {
        return 0;
    }

    public int getFingerprintEnrollPrerecent() {
        return 0;
    }

    public int getFingerprintEnrollProcState() {
        return 0;
    }

    public int getFingerprintEnrollReminderState() {
        return 0;
    }

    public int getFingerprintEnrollReqAckState() {
        return 0;
    }

    public int[] getFingerprintEnrollState() {
        return this.e1;
    }

    public int getFingerprintLearnState() {
        return 0;
    }

    public int getFingerprintSyncState() {
        return 0;
    }

    public byte getForwardCollisionWarnFault() {
        int forwardCollisionWarnFaultR;
        String str = g1;
        LogUtil.verbose(str, " getForwardCollisionWarnFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                forwardCollisionWarnFaultR = carVendorDipilotManager.getForwardCollisionWarnFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                forwardCollisionWarnFaultR = -1;
            }
            LogUtil.verbose(str, " getForwardCollisionWarnFault.out=" + forwardCollisionWarnFaultR + ",return:" + forwardCollisionWarnFaultR);
            return Byte.parseByte(Integer.toString(forwardCollisionWarnFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        forwardCollisionWarnFaultR = -1;
        LogUtil.verbose(str, " getForwardCollisionWarnFault.out=" + forwardCollisionWarnFaultR + ",return:" + forwardCollisionWarnFaultR);
        return Byte.parseByte(Integer.toString(forwardCollisionWarnFaultR));
    }

    public int getForwardCollisionWarning() {
        return 0;
    }

    public byte getFrontCrossAlarkFault() {
        int frontCrossAlarkFaultR;
        String str = g1;
        LogUtil.verbose(str, " getFrontCrossAlarkFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                frontCrossAlarkFaultR = carVendorDipilotManager.getFrontCrossAlarkFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                frontCrossAlarkFaultR = -1;
            }
            LogUtil.verbose(str, " getFrontCrossAlarkFault.out=" + frontCrossAlarkFaultR + ",return:" + frontCrossAlarkFaultR);
            return Byte.parseByte(Integer.toString(frontCrossAlarkFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        frontCrossAlarkFaultR = -1;
        LogUtil.verbose(str, " getFrontCrossAlarkFault.out=" + frontCrossAlarkFaultR + ",return:" + frontCrossAlarkFaultR);
        return Byte.parseByte(Integer.toString(frontCrossAlarkFaultR));
    }

    public byte getFrontCrossBrakeFault() {
        int frontCrossBrakeFaultR;
        String str = g1;
        LogUtil.verbose(str, " getFrontCrossBrakeFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                frontCrossBrakeFaultR = carVendorDipilotManager.getFrontCrossBrakeFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                frontCrossBrakeFaultR = -1;
            }
            LogUtil.verbose(str, " getFrontCrossBrakeFault.out=" + frontCrossBrakeFaultR + ",return:" + frontCrossBrakeFaultR);
            return Byte.parseByte(Integer.toString(frontCrossBrakeFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        frontCrossBrakeFaultR = -1;
        LogUtil.verbose(str, " getFrontCrossBrakeFault.out=" + frontCrossBrakeFaultR + ",return:" + frontCrossBrakeFaultR);
        return Byte.parseByte(Integer.toString(frontCrossBrakeFaultR));
    }

    public int getFrontTrafficBrakeAlertState() {
        String str = g1;
        LogUtil.verbose(str, "getFrontTrafficCrossAlertState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int frontTrafficBrakeAlertStateR = carVendorDipilotManager.getFrontTrafficBrakeAlertStateR();
            LogUtil.verbose(str, "getFrontTrafficBrakeAlertStateR=" + frontTrafficBrakeAlertStateR);
            return frontTrafficBrakeAlertStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFrontTrafficCrossAlertState() {
        String str = g1;
        LogUtil.verbose(str, "getFrontTrafficCrossAlertState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int frontTrafficCrossAlertStateR = carVendorDipilotManager.getFrontTrafficCrossAlertStateR();
            LogUtil.verbose(str, "getFrontTrafficCrossAlertStateR=" + frontTrafficCrossAlertStateR);
            return frontTrafficCrossAlertStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getGreenLightOptimalSpeedAdvisoryState() {
        String str = g1;
        LogUtil.verbose(str, "getGreenLightOptimalSpeedAdvisoryState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int greenLightOptimalSpeedAdvisoryStateR = carVendorDipilotManager.getGreenLightOptimalSpeedAdvisoryStateR();
            LogUtil.verbose(str, "getGreenLightOptimalSpeedAdvisoryStateR=" + greenLightOptimalSpeedAdvisoryStateR);
            return greenLightOptimalSpeedAdvisoryStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getHighNaviFault() {
        int highNaviFaultR;
        String str = g1;
        LogUtil.verbose(str, " getHighNaviFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                highNaviFaultR = carVendorDipilotManager.getHighNaviFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                highNaviFaultR = -1;
            }
            LogUtil.verbose(str, " getHighNaviFault.out=" + highNaviFaultR + ",return:" + highNaviFaultR);
            return Byte.parseByte(Integer.toString(highNaviFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        highNaviFaultR = -1;
        LogUtil.verbose(str, " getHighNaviFault.out=" + highNaviFaultR + ",return:" + highNaviFaultR);
        return Byte.parseByte(Integer.toString(highNaviFaultR));
    }

    public int getHighSpeedAINavigationState() {
        String str = g1;
        LogUtil.verbose(str, "getHighSpeedAINavigationState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int highSpeedAINavigationStateR = carVendorDipilotManager.getHighSpeedAINavigationStateR();
            LogUtil.verbose(str, "getHighSpeedAINavigationStateR=" + highSpeedAINavigationStateR);
            return highSpeedAINavigationStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHomeZoneParkingAssistStatus() {
        int homeZoneParkingAssistStatusR;
        String str = g1;
        LogUtil.verbose(str, " getHomeZoneParkingAssistStatus.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                homeZoneParkingAssistStatusR = carVendorDipilotManager.getHomeZoneParkingAssistStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                homeZoneParkingAssistStatusR = -1;
            }
            LogUtil.verbose(str, " getHomeZoneParkingAssistStatus.out=" + homeZoneParkingAssistStatusR);
            return homeZoneParkingAssistStatusR;
        }
        LogUtil.verbose(str, " getHomeZoneParkingAssistStatus.null == mCarVendorDipilotManager");
        homeZoneParkingAssistStatusR = -1;
        LogUtil.verbose(str, " getHomeZoneParkingAssistStatus.out=" + homeZoneParkingAssistStatusR);
        return homeZoneParkingAssistStatusR;
    }

    public String getHwVersion() {
        LogUtil.verbose(g1, "Service getHwVersion");
        return "";
    }

    public int getIkConfigKeylessTime() {
        String str = g1;
        LogUtil.verbose(str, "Service getIkConfigKeylessTime");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int startForNoKeyR = carVendorDigitalkeyManager.getStartForNoKeyR();
            LogUtil.verbose(str, "getIkConfigKeylessTime=" + startForNoKeyR);
            return startForNoKeyR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIndicatorValue() {
        LogUtil.verbose(g1, "Service getIndicatorValue");
        return 0;
    }

    public String getInstrumentHwVersion() {
        LogUtil.verbose(g1, "Service getInstrumentHwVersion");
        return "";
    }

    public String getInstrumentSwVersion() {
        LogUtil.verbose(g1, "Service getInstrumentSwVersion");
        return "";
    }

    public byte getIntelligentLimitFaultCall() {
        int intelligentLimitFaultCallR;
        String str = g1;
        LogUtil.verbose(str, " getIntelligentLimitFaultCall.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                intelligentLimitFaultCallR = carVendorDipilotManager.getIntelligentLimitFaultCallR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                intelligentLimitFaultCallR = -1;
            }
            LogUtil.verbose(str, " getIntelligentLimitFaultCall.out=" + intelligentLimitFaultCallR + ",return:" + intelligentLimitFaultCallR);
            return Byte.parseByte(Integer.toString(intelligentLimitFaultCallR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        intelligentLimitFaultCallR = -1;
        LogUtil.verbose(str, " getIntelligentLimitFaultCall.out=" + intelligentLimitFaultCallR + ",return:" + intelligentLimitFaultCallR);
        return Byte.parseByte(Integer.toString(intelligentLimitFaultCallR));
    }

    public byte getIntelligentLimitFaultController() {
        int intelligentLimitFaultControllerR;
        String str = g1;
        LogUtil.verbose(str, " getIntelligentLimitFaultController.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                intelligentLimitFaultControllerR = carVendorDipilotManager.getIntelligentLimitFaultControllerR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                intelligentLimitFaultControllerR = -1;
            }
            LogUtil.verbose(str, " getIntelligentLimitFaultController.out=" + intelligentLimitFaultControllerR + ",return:" + intelligentLimitFaultControllerR);
            return Byte.parseByte(Integer.toString(intelligentLimitFaultControllerR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        intelligentLimitFaultControllerR = -1;
        LogUtil.verbose(str, " getIntelligentLimitFaultController.out=" + intelligentLimitFaultControllerR + ",return:" + intelligentLimitFaultControllerR);
        return Byte.parseByte(Integer.toString(intelligentLimitFaultControllerR));
    }

    public int getIntelligentSpeedLimitAlert2B4State() {
        String str = g1;
        LogUtil.verbose(str, "getIntelligentSpeedLimitAlert2B4State is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856790, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getIntelligentSpeedLimitAlert2B4State prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getIntelligentSpeedLimitAlert2D5State() {
        String str = g1;
        LogUtil.verbose(str, "getIntelligentSpeedLimitAlert2D5State is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int intelligentSpeedLimitAlertStateFirst = carVendorDipilotManager.getIntelligentSpeedLimitAlertStateFirst();
            LogUtil.verbose(str, "getIntelligentSpeedLimitAlertStateFirst=" + intelligentSpeedLimitAlertStateFirst);
            return intelligentSpeedLimitAlertStateFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentSpeedLimitControl2D5State() {
        String str = g1;
        LogUtil.verbose(str, "getIntelligentSpeedLimitControl2D5State is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int intelligentSpeedLimitControlStateFirst = carVendorDipilotManager.getIntelligentSpeedLimitControlStateFirst();
            LogUtil.verbose(str, "getIntelligentSpeedLimitControlStateFirst=" + intelligentSpeedLimitControlStateFirst);
            return intelligentSpeedLimitControlStateFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentSpeedLimitControl32FState() {
        String str = g1;
        LogUtil.verbose(str, "getIntelligentSpeedLimitControl32FState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int intelligentSpeedLimitControlStateSecond = carVendorDipilotManager.getIntelligentSpeedLimitControlStateSecond();
            LogUtil.verbose(str, "getIntelligentSpeedLimitControlStateSecond=" + intelligentSpeedLimitControlStateSecond);
            return intelligentSpeedLimitControlStateSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentSpeedLimitControlStateSecond() {
        String str = g1;
        LogUtil.verbose(str, "getIntelligentSpeedLimitControlStateSecond");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int intelligentSpeedLimitControlStateSecond = carVendorDipilotManager.getIntelligentSpeedLimitControlStateSecond();
            LogUtil.verbose(str, "getIntelligentSpeedLimitControlStateSecond=" + intelligentSpeedLimitControlStateSecond);
            return intelligentSpeedLimitControlStateSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInteractiveLaneChangeAssistanceState() {
        String str = g1;
        LogUtil.verbose(str, "getInteractiveLaneChangeAssistanceState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int interactiveLaneChangeAssistanceStateR = carVendorDipilotManager.getInteractiveLaneChangeAssistanceStateR();
            LogUtil.verbose(str, "getInteractiveLaneChangeAssistanceStateR=" + interactiveLaneChangeAssistanceStateR);
            return interactiveLaneChangeAssistanceStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getInteractiveLaneChangeFault() {
        int interactiveLaneChangeFaultR;
        String str = g1;
        LogUtil.verbose(str, " getInteractiveLaneChangeFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                interactiveLaneChangeFaultR = carVendorDipilotManager.getInteractiveLaneChangeFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                interactiveLaneChangeFaultR = -1;
            }
            LogUtil.verbose(str, " getInteractiveLaneChangeFault.out=" + interactiveLaneChangeFaultR + ",return:" + interactiveLaneChangeFaultR);
            return Byte.parseByte(Integer.toString(interactiveLaneChangeFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        interactiveLaneChangeFaultR = -1;
        LogUtil.verbose(str, " getInteractiveLaneChangeFault.out=" + interactiveLaneChangeFaultR + ",return:" + interactiveLaneChangeFaultR);
        return Byte.parseByte(Integer.toString(interactiveLaneChangeFaultR));
    }

    public int getIntersectionAssistMode() {
        return 0;
    }

    public int getIntersectionAssistSwitch() {
        return 0;
    }

    public int getIntersectionCollisionAlertState() {
        String str = g1;
        LogUtil.verbose(str, "getIntersectionCollisionAlertState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int intersectionCollisionAlertStateR = carVendorDipilotManager.getIntersectionCollisionAlertStateR();
            LogUtil.verbose(str, "getIntersectionCollisionAlertStateR=" + intersectionCollisionAlertStateR);
            return intersectionCollisionAlertStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIopState() {
        int iopStateR;
        String str = g1;
        LogUtil.verbose(str, " getIopState.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                iopStateR = carVendorDipilotManager.getIopStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                iopStateR = -1;
            }
            LogUtil.verbose(str, " getIopState.out=" + iopStateR);
            return iopStateR;
        }
        LogUtil.verbose(str, " getIopState.null");
        iopStateR = -1;
        LogUtil.verbose(str, " getIopState.out=" + iopStateR);
        return iopStateR;
    }

    public int getIsliWarningLevelSwitchStatus() {
        String str = g1;
        LogUtil.verbose(str, " getIsliWarningLevelSwitchStatus.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856929, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getISLIWarningLevelSwitchStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getIsliWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getIsliWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int isliWarningStateR = carVendorDipilotManager.getIsliWarningStateR();
            LogUtil.verbose(str, "getIsliWarningStateR=" + isliWarningStateR);
            return isliWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getKeyFindingResults() {
        String str = g1;
        LogUtil.verbose(str, " getKeyFindingResults.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int findKeyResultDouble = carVendorDigitalkeyManager.getFindKeyResultDouble();
            LogUtil.verbose(str, " getKeyFindingResults.out = " + findKeyResultDouble);
            return findKeyResultDouble;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLaneAssistMode() {
        return 0;
    }

    public int getLaneAssistState() {
        return 0;
    }

    public int getLaneAssistType() {
        String str = g1;
        LogUtil.verbose(str, "getLaneAssistType");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856778, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLaneAssistType prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public byte getLaneCenteringControlFault() {
        int laneCenteringControlFaultR;
        String str = g1;
        LogUtil.verbose(str, " getLaneCenteringControlFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                laneCenteringControlFaultR = carVendorDipilotManager.getLaneCenteringControlFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                laneCenteringControlFaultR = -1;
            }
            LogUtil.verbose(str, " getLaneCenteringControlFault.out=" + laneCenteringControlFaultR + ",return:" + laneCenteringControlFaultR);
            return Byte.parseByte(Integer.toString(laneCenteringControlFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        laneCenteringControlFaultR = -1;
        LogUtil.verbose(str, " getLaneCenteringControlFault.out=" + laneCenteringControlFaultR + ",return:" + laneCenteringControlFaultR);
        return Byte.parseByte(Integer.toString(laneCenteringControlFaultR));
    }

    public byte getLaneDeparturePreventionFault() {
        int laneDeparturePreventionFaultR;
        String str = g1;
        LogUtil.verbose(str, " getLaneDeparturePreventionFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                laneDeparturePreventionFaultR = carVendorDipilotManager.getLaneDeparturePreventionFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                laneDeparturePreventionFaultR = -1;
            }
            LogUtil.verbose(str, " getLaneDeparturePreventionFault.out=" + laneDeparturePreventionFaultR + ",return:" + laneDeparturePreventionFaultR);
            return Byte.parseByte(Integer.toString(laneDeparturePreventionFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        laneDeparturePreventionFaultR = -1;
        LogUtil.verbose(str, " getLaneDeparturePreventionFault.out=" + laneDeparturePreventionFaultR + ",return:" + laneDeparturePreventionFaultR);
        return Byte.parseByte(Integer.toString(laneDeparturePreventionFaultR));
    }

    public byte getLaneDepartureWarnFault() {
        int laneDepartureWarnFaultR;
        String str = g1;
        LogUtil.verbose(str, " getLaneDepartureWarnFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                laneDepartureWarnFaultR = carVendorDipilotManager.getLaneDepartureWarnFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                laneDepartureWarnFaultR = -1;
            }
            LogUtil.verbose(str, " getLaneDepartureWarnFault.out=" + laneDepartureWarnFaultR + ",return:" + laneDepartureWarnFaultR);
            return Byte.parseByte(Integer.toString(laneDepartureWarnFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        laneDepartureWarnFaultR = -1;
        LogUtil.verbose(str, " getLaneDepartureWarnFault.out=" + laneDepartureWarnFaultR + ",return:" + laneDepartureWarnFaultR);
        return Byte.parseByte(Integer.toString(laneDepartureWarnFaultR));
    }

    public byte getLaneKeepFault() {
        int laneKeepFaultR;
        String str = g1;
        LogUtil.verbose(str, " getLaneKeepFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                laneKeepFaultR = carVendorDipilotManager.getLaneKeepFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                laneKeepFaultR = -1;
            }
            LogUtil.verbose(str, " getLaneKeepFault.out=" + laneKeepFaultR + ",return:" + laneKeepFaultR);
            return Byte.parseByte(Integer.toString(laneKeepFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        laneKeepFaultR = -1;
        LogUtil.verbose(str, " getLaneKeepFault.out=" + laneKeepFaultR + ",return:" + laneKeepFaultR);
        return Byte.parseByte(Integer.toString(laneKeepFaultR));
    }

    public int getLdwFaultState() {
        String str = g1;
        LogUtil.verbose(str, "getLdwFaultState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int ldwFaultStateR = carVendorDipilotManager.getLdwFaultStateR();
            LogUtil.verbose(str, "getLdwFaultStateR=" + ldwFaultStateR);
            return ldwFaultStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLdwSensitivity() {
        String str = g1;
        LogUtil.verbose(str, " getLdwSensitivity.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int ldwSensitivityR = carVendorDipilotManager.getLdwSensitivityR();
            LogUtil.verbose(str, " getLdwSensitivity.out = " + ldwSensitivityR);
            return ldwSensitivityR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getLimitSpeedChangeFault() {
        int limitSpeedChangeFaultR;
        String str = g1;
        LogUtil.verbose(str, " getLimitSpeedChangeFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                limitSpeedChangeFaultR = carVendorDipilotManager.getLimitSpeedChangeFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                limitSpeedChangeFaultR = -1;
            }
            LogUtil.verbose(str, " getLimitSpeedChangeFault.out=" + limitSpeedChangeFaultR + ",return:" + limitSpeedChangeFaultR);
            return Byte.parseByte(Integer.toString(limitSpeedChangeFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        limitSpeedChangeFaultR = -1;
        LogUtil.verbose(str, " getLimitSpeedChangeFault.out=" + limitSpeedChangeFaultR + ",return:" + limitSpeedChangeFaultR);
        return Byte.parseByte(Integer.toString(limitSpeedChangeFaultR));
    }

    public int getLksFaultState() {
        String str = g1;
        LogUtil.verbose(str, "getLksFaultState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int lksFaultStateR = carVendorDipilotManager.getLksFaultStateR();
            LogUtil.verbose(str, "getLksFaultStateR=" + lksFaultStateR);
            return lksFaultStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLowSpeedEmergencyBrakingState() {
        String str = g1;
        LogUtil.verbose(str, "getLowSpeedEmergencyBrakingState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int lowSpeedEmergencyBrakingStateR = carVendorDipilotManager.getLowSpeedEmergencyBrakingStateR();
            LogUtil.verbose(str, "getLowSpeedEmergencyBrakingStateR=" + lowSpeedEmergencyBrakingStateR);
            return lowSpeedEmergencyBrakingStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getMcuVersion() {
        LogUtil.verbose(g1, "Service getMcuVersion");
        return "";
    }

    public int getMechanismPosition() {
        String str = g1;
        LogUtil.verbose(str, "getMechanismPosition is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int mechanismPositionR = carVendorDipilotManager.getMechanismPositionR();
            LogUtil.verbose(str, "getMechanismPositionR=" + mechanismPositionR);
            return mechanismPositionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMemoryDrivingMode() {
        return 0;
    }

    public int getMistakeGasState() {
        String str = g1;
        LogUtil.verbose(str, "getMistakeGasState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int mistakeGasStateR = carVendorDipilotManager.getMistakeGasStateR();
            LogUtil.verbose(str, "getMistakeGasStateR=" + mistakeGasStateR);
            return mistakeGasStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMoisEnableR() {
        String str = g1;
        LogUtil.info(str, "getMoisEnableR");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int moisEnableR = carVendorDipilotManager.getMoisEnableR();
            LogUtil.verbose(str, "getMoisEnableR=" + moisEnableR);
            return moisEnableR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getMoisFault() {
        int moisFaultR;
        String str = g1;
        LogUtil.verbose(str, " getMoisFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                moisFaultR = carVendorDipilotManager.getMoisFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                moisFaultR = -1;
            }
            LogUtil.verbose(str, " getMoisFault.out=" + moisFaultR + ",return:" + moisFaultR);
            return Byte.parseByte(Integer.toString(moisFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        moisFaultR = -1;
        LogUtil.verbose(str, " getMoisFault.out=" + moisFaultR + ",return:" + moisFaultR);
        return Byte.parseByte(Integer.toString(moisFaultR));
    }

    public int getMoisFuncStateR() {
        String str = g1;
        LogUtil.info(str, "getMoisFuncStateR");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int moisFuncStateR = carVendorDipilotManager.getMoisFuncStateR();
            LogUtil.verbose(str, "getMoisFuncStateR=" + moisFuncStateR);
            return moisFuncStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getNFCConfigurationInformation() {
        String str = g1;
        LogUtil.verbose(str, "Service getNFCConfigurationInformation");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int nfcConfig = carVendorDigitalkeyManager.getNfcConfig();
            LogUtil.verbose(str, "getNFCConfigurationInformation=" + nfcConfig);
            return nfcConfig;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getNavigationPilotFault() {
        int navigationPilotFaultR;
        String str = g1;
        LogUtil.verbose(str, " getNavigationPilotFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                navigationPilotFaultR = carVendorDipilotManager.getNavigationPilotFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                navigationPilotFaultR = -1;
            }
            LogUtil.verbose(str, " getNavigationPilotFault.out=" + navigationPilotFaultR + ",return:" + navigationPilotFaultR);
            return Byte.parseByte(Integer.toString(navigationPilotFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        navigationPilotFaultR = -1;
        LogUtil.verbose(str, " getNavigationPilotFault.out=" + navigationPilotFaultR + ",return:" + navigationPilotFaultR);
        return Byte.parseByte(Integer.toString(navigationPilotFaultR));
    }

    public int getNfcKeyStatus() {
        int nfcKeyStatusR;
        String str = g1;
        LogUtil.verbose(str, " getNfcKeyStatus.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                nfcKeyStatusR = carVendorDigitalkeyManager.getNfcKeyStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                nfcKeyStatusR = -1;
            }
            LogUtil.verbose(str, " getNfcKeyStatus.out service =" + nfcKeyStatusR);
            return nfcKeyStatusR;
        }
        LogUtil.verbose(str, " getNfcKeyStatus.null");
        nfcKeyStatusR = -1;
        LogUtil.verbose(str, " getNfcKeyStatus.out service =" + nfcKeyStatusR);
        return nfcKeyStatusR;
    }

    public int getNfcKeyUnlockResult() {
        int nfcKeyUnlockResultR;
        String str = g1;
        LogUtil.verbose(str, " getNfcKeyUnlockResult.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                nfcKeyUnlockResultR = carVendorDigitalkeyManager.getNfcKeyUnlockResultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                nfcKeyUnlockResultR = -1;
            }
            LogUtil.verbose(str, " getNfcKeyUnlockResult.out service =" + nfcKeyUnlockResultR);
            return nfcKeyUnlockResultR;
        }
        LogUtil.verbose(str, " getNfcKeyUnlockResult.null");
        nfcKeyUnlockResultR = -1;
        LogUtil.verbose(str, " getNfcKeyUnlockResult.out service =" + nfcKeyUnlockResultR);
        return nfcKeyUnlockResultR;
    }

    public int getNfcKeyUnlockType() {
        int nfcKeyUnlockTypeR;
        String str = g1;
        LogUtil.verbose(str, " getNfcKeyUnlockType.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                nfcKeyUnlockTypeR = carVendorDigitalkeyManager.getNfcKeyUnlockTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                nfcKeyUnlockTypeR = -1;
            }
            LogUtil.verbose(str, " getNfcKeyUnlockType.out service =" + nfcKeyUnlockTypeR);
            return nfcKeyUnlockTypeR;
        }
        LogUtil.verbose(str, " getNfcKeyUnlockType.null");
        nfcKeyUnlockTypeR = -1;
        LogUtil.verbose(str, " getNfcKeyUnlockType.out service =" + nfcKeyUnlockTypeR);
        return nfcKeyUnlockTypeR;
    }

    public byte getNrbanNaviFault() {
        int nrbanNaviFaultR;
        String str = g1;
        LogUtil.verbose(str, " getNrbanNaviFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                nrbanNaviFaultR = carVendorDipilotManager.getNrbanNaviFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                nrbanNaviFaultR = -1;
            }
            LogUtil.verbose(str, " getNrbanNaviFault.out=" + nrbanNaviFaultR + ",return:" + nrbanNaviFaultR);
            return Byte.parseByte(Integer.toString(nrbanNaviFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        nrbanNaviFaultR = -1;
        LogUtil.verbose(str, " getNrbanNaviFault.out=" + nrbanNaviFaultR + ",return:" + nrbanNaviFaultR);
        return Byte.parseByte(Integer.toString(nrbanNaviFaultR));
    }

    public int getOpcodeChecksumValue() {
        int opcodeChecksumValueR;
        String str = g1;
        LogUtil.verbose(str, " getOpcodeChecksumValue.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                opcodeChecksumValueR = carVendorDigitalkeyManager.getOpcodeChecksumValueR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                opcodeChecksumValueR = -1;
            }
            LogUtil.verbose(str, " getOpcodeChecksumValue.out service =" + opcodeChecksumValueR);
            return opcodeChecksumValueR;
        }
        LogUtil.verbose(str, " getOpcodeChecksumValue.null");
        opcodeChecksumValueR = -1;
        LogUtil.verbose(str, " getOpcodeChecksumValue.out service =" + opcodeChecksumValueR);
        return opcodeChecksumValueR;
    }

    public int getOpcodeCommand() {
        int opcodeCommandR;
        String str = g1;
        LogUtil.verbose(str, " getOpcodeCommand.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                opcodeCommandR = carVendorDigitalkeyManager.getOpcodeCommandR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                opcodeCommandR = -1;
            }
            LogUtil.verbose(str, " getOpcodeCommand.out service =" + opcodeCommandR);
            return opcodeCommandR;
        }
        LogUtil.verbose(str, " getOpcodeCommand.null");
        opcodeCommandR = -1;
        LogUtil.verbose(str, " getOpcodeCommand.out service =" + opcodeCommandR);
        return opcodeCommandR;
    }

    public int getOpcodeDataLength() {
        int opcodeDataLengthR;
        String str = g1;
        LogUtil.verbose(str, " getOpcodeDataLength.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                opcodeDataLengthR = carVendorDigitalkeyManager.getOpcodeDataLengthR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                opcodeDataLengthR = -1;
            }
            LogUtil.verbose(str, " getOpcodeDataLength.out service =" + opcodeDataLengthR);
            return opcodeDataLengthR;
        }
        LogUtil.verbose(str, " getOpcodeDataLength.null");
        opcodeDataLengthR = -1;
        LogUtil.verbose(str, " getOpcodeDataLength.out service =" + opcodeDataLengthR);
        return opcodeDataLengthR;
    }

    public byte[] getOpcodeDataValue() {
        String str = g1;
        byte[] opcodeDataValueR = new byte[0];
        LogUtil.verbose(str, " getOpcodeDataValue.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                opcodeDataValueR = carVendorDigitalkeyManager.getOpcodeDataValueR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " getOpcodeDataValue.null");
        }
        LogUtil.verbose(str, " getOpcodeDataValue.out service =" + opcodeDataValueR);
        return opcodeDataValueR;
    }

    public int getOpcodeStartFunction() {
        int opcodeStartFunctionR;
        String str = g1;
        LogUtil.verbose(str, " getOpcodeStartFunction.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                opcodeStartFunctionR = carVendorDigitalkeyManager.getOpcodeStartFunctionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                opcodeStartFunctionR = -1;
            }
            LogUtil.verbose(str, " getOpcodeStartFunction.out service =" + opcodeStartFunctionR);
            return opcodeStartFunctionR;
        }
        LogUtil.verbose(str, " getOpcodeStartFunction.null");
        opcodeStartFunctionR = -1;
        LogUtil.verbose(str, " getOpcodeStartFunction.out service =" + opcodeStartFunctionR);
        return opcodeStartFunctionR;
    }

    public int getOpenDoorAlertState() {
        String str = g1;
        LogUtil.verbose(str, "getOpenDoorAlertState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int openDoorAlertStateR = carVendorDipilotManager.getOpenDoorAlertStateR();
            LogUtil.verbose(str, "getOpenDoorAlertStateR=" + openDoorAlertStateR);
            return openDoorAlertStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOperateCmd() {
        String str = g1;
        LogUtil.verbose(str, " getOperateCmd.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int offlineOperatPairingResult = carVendorDigitalkeyManager.getOfflineOperatPairingResult();
            LogUtil.verbose(str, " getOperateCmd.out = " + offlineOperatPairingResult);
            return offlineOperatPairingResult;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOperateResult() {
        String str = g1;
        LogUtil.verbose(str, " getOperateResult.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int pairingResultDouble = carVendorDigitalkeyManager.getPairingResultDouble();
            LogUtil.verbose(str, " getOperateResult.out = " + pairingResultDouble);
            return pairingResultDouble;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOperationCommand() {
        int operationCommandR;
        String str = g1;
        LogUtil.verbose(str, " getOperationCommand.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                operationCommandR = carVendorDigitalkeyManager.getOperationCommandR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                operationCommandR = -1;
            }
            LogUtil.verbose(str, " getOperationCommand.out=" + operationCommandR);
            return operationCommandR;
        }
        LogUtil.verbose(str, " getOperationCommand.null == mCarVendorDigitalkeyManager");
        operationCommandR = -1;
        LogUtil.verbose(str, " getOperationCommand.out=" + operationCommandR);
        return operationCommandR;
    }

    public int getOperationCommand19C33() {
        String str = g1;
        LogUtil.verbose(str, " getOperationCommand19C33.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int operationCommand19C33 = carVendorDigitalkeyManager.getOperationCommand19C33();
            LogUtil.verbose(str, " getOperationCommand19C33.out = " + operationCommand19C33);
            return operationCommand19C33;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOperationResult19C33() {
        String str = g1;
        LogUtil.verbose(str, " getOperationResult19C33.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int operationResult19C33 = carVendorDigitalkeyManager.getOperationResult19C33();
            LogUtil.verbose(str, " getOperationResult19C33.out = " + operationResult19C33);
            return operationResult19C33;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOperationType() {
        int operationTypeR;
        String str = g1;
        LogUtil.verbose(str, " getOperationType.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                operationTypeR = carVendorDigitalkeyManager.getOperationTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                operationTypeR = -1;
            }
            LogUtil.verbose(str, " getOperationType.out=" + operationTypeR);
            return operationTypeR;
        }
        LogUtil.verbose(str, " getOperationType.null == mCarVendorDigitalkeyManager");
        operationTypeR = -1;
        LogUtil.verbose(str, " getOperationType.out=" + operationTypeR);
        return operationTypeR;
    }

    public int getOperationType19C33() {
        String str = g1;
        LogUtil.verbose(str, " getOperationType19C33.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int operationType19C33 = carVendorDigitalkeyManager.getOperationType19C33();
            LogUtil.verbose(str, " getOperationType19C33.out = " + operationType19C33);
            return operationType19C33;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean getOverspeedAlarm() {
        return false;
    }

    public int getOverspeedAlarmValue() {
        return 0;
    }

    public int getOwnerKeyIdentifierResult() {
        String str = g1;
        LogUtil.verbose(str, " getOwnerKeyIdentifierResult.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int queryOwnerKeyResultDouble = carVendorDigitalkeyManager.getQueryOwnerKeyResultDouble();
            LogUtil.verbose(str, " getOwnerKeyIdentifierResult.out = " + queryOwnerKeyResultDouble);
            return queryOwnerKeyResultDouble;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOwnerKeyIdentifierResultSingle() {
        String str = g1;
        LogUtil.verbose(str, " getOwnerKeyIdentifierResultSingle.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int findKeyResultSingle = carVendorDigitalkeyManager.getFindKeyResultSingle();
            LogUtil.verbose(str, " getOwnerKeyIdentifierResultSingle.out = " + findKeyResultSingle);
            return findKeyResultSingle;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPadExitPinCodeProcess() {
        int padExitPinCodeProcessR;
        String str = g1;
        LogUtil.verbose(str, " getPadExitPinCodeProcess.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                padExitPinCodeProcessR = carVendorDigitalkeyManager.getPadExitPinCodeProcessR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                padExitPinCodeProcessR = -1;
            }
            LogUtil.verbose(str, " getPadExitPinCodeProcess.out service =" + padExitPinCodeProcessR);
            return padExitPinCodeProcessR;
        }
        LogUtil.verbose(str, " getPadExitPinCodeProcess.null");
        padExitPinCodeProcessR = -1;
        LogUtil.verbose(str, " getPadExitPinCodeProcess.out service =" + padExitPinCodeProcessR);
        return padExitPinCodeProcessR;
    }

    public int getPadOfflineOperationResp() {
        int padOfflineOperationRespR;
        String str = g1;
        LogUtil.verbose(str, " getPadOfflineOperationResp.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                padOfflineOperationRespR = carVendorDigitalkeyManager.getPadOfflineOperationRespR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                padOfflineOperationRespR = -1;
            }
            LogUtil.verbose(str, " getPadOfflineOperationResp.out=" + padOfflineOperationRespR);
            return padOfflineOperationRespR;
        }
        LogUtil.verbose(str, " getPadOfflineOperationResp.null == mCarVendorDigitalkeyManager");
        padOfflineOperationRespR = -1;
        LogUtil.verbose(str, " getPadOfflineOperationResp.out=" + padOfflineOperationRespR);
        return padOfflineOperationRespR;
    }

    public int getPairingProcess() {
        String str = g1;
        LogUtil.verbose(str, " getPairingProcess.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int pairingProcessCallBackDouble = carVendorDigitalkeyManager.getPairingProcessCallBackDouble();
            LogUtil.verbose(str, " getPairingProcess.out = " + pairingProcessCallBackDouble);
            return pairingProcessCallBackDouble;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPanoramaState() {
        return 0;
    }

    public int getPedestrianWarningSoundSpeed() {
        return 0;
    }

    public int getPedestrianWarningSoundSwitch() {
        return 0;
    }

    public int getPredictCrashAlert138Mode() {
        String str = g1;
        LogUtil.verbose(str, "getPredictCrashAlert138Mode is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int predictCrashAlertModeRFirst = carVendorDipilotManager.getPredictCrashAlertModeRFirst();
            LogUtil.verbose(str, "getPredictCrashAlertModeRFirst=" + predictCrashAlertModeRFirst);
            return predictCrashAlertModeRFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPredictCrashAlert32DMode() {
        String str = g1;
        LogUtil.verbose(str, "getPredictCrashAlert32DMode is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int predictCrashAlertModeRSecond = carVendorDipilotManager.getPredictCrashAlertModeRSecond();
            LogUtil.verbose(str, "getPredictCrashAlertModeRSecond=" + predictCrashAlertModeRSecond);
            return predictCrashAlertModeRSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPredictCrashAlertState() {
        String str = g1;
        LogUtil.verbose(str, "getPredictCrashAlertState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int predictCrashAlertStateR = carVendorDipilotManager.getPredictCrashAlertStateR();
            LogUtil.verbose(str, "getPredictCrashAlertStateR=" + predictCrashAlertStateR);
            return predictCrashAlertStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getQueryOwnerKeyResultSingle() {
        String str = g1;
        LogUtil.verbose(str, " getQueryOwnerKeyResultSingle.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int queryOwnerKeyResultSingle = carVendorDigitalkeyManager.getQueryOwnerKeyResultSingle();
            LogUtil.verbose(str, " getQueryOwnerKeyResultSingle.out = " + queryOwnerKeyResultSingle);
            return queryOwnerKeyResultSingle;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getRearCollisionWarnFault() {
        int rearCollisionWarnFaultR;
        String str = g1;
        LogUtil.verbose(str, " getRearCollisionWarnFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                rearCollisionWarnFaultR = carVendorDipilotManager.getRearCollisionWarnFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearCollisionWarnFaultR = -1;
            }
            LogUtil.verbose(str, " getRearCollisionWarnFault.out=" + rearCollisionWarnFaultR + ",return:" + rearCollisionWarnFaultR);
            return Byte.parseByte(Integer.toString(rearCollisionWarnFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        rearCollisionWarnFaultR = -1;
        LogUtil.verbose(str, " getRearCollisionWarnFault.out=" + rearCollisionWarnFaultR + ",return:" + rearCollisionWarnFaultR);
        return Byte.parseByte(Integer.toString(rearCollisionWarnFaultR));
    }

    public int getRearCrashAlertState() {
        String str = g1;
        LogUtil.verbose(str, "getRearCrashAlertState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int rearCrashAlertStateR = carVendorDipilotManager.getRearCrashAlertStateR();
            LogUtil.verbose(str, "getRearCrashAlertStateR=" + rearCrashAlertStateR);
            return rearCrashAlertStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearCrashAutoBreak() {
        return 0;
    }

    public byte getRearCrossAlartFault() {
        int rearCrossAlartFaultR;
        String str = g1;
        LogUtil.verbose(str, " getRearCrossAlartFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                rearCrossAlartFaultR = carVendorDipilotManager.getRearCrossAlartFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearCrossAlartFaultR = -1;
            }
            LogUtil.verbose(str, " getRearCrossAlartFault.out=" + rearCrossAlartFaultR + ",return:" + rearCrossAlartFaultR);
            return Byte.parseByte(Integer.toString(rearCrossAlartFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        rearCrossAlartFaultR = -1;
        LogUtil.verbose(str, " getRearCrossAlartFault.out=" + rearCrossAlartFaultR + ",return:" + rearCrossAlartFaultR);
        return Byte.parseByte(Integer.toString(rearCrossAlartFaultR));
    }

    public byte getRearCrossBrakeFault() {
        int rearCrossBrakeFaultR;
        String str = g1;
        LogUtil.verbose(str, " getRearCrossBrakeFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                rearCrossBrakeFaultR = carVendorDipilotManager.getRearCrossBrakeFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearCrossBrakeFaultR = -1;
            }
            LogUtil.verbose(str, " getRearCrossBrakeFault.out=" + rearCrossBrakeFaultR + ",return:" + rearCrossBrakeFaultR);
            return Byte.parseByte(Integer.toString(rearCrossBrakeFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        rearCrossBrakeFaultR = -1;
        LogUtil.verbose(str, " getRearCrossBrakeFault.out=" + rearCrossBrakeFaultR + ",return:" + rearCrossBrakeFaultR);
        return Byte.parseByte(Integer.toString(rearCrossBrakeFaultR));
    }

    public int getRearTrafficBrakeAlertState() {
        String str = g1;
        LogUtil.verbose(str, "getRearTrafficBrakeAlertState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int rearTrafficBrakeAlertStateR = carVendorDipilotManager.getRearTrafficBrakeAlertStateR();
            LogUtil.verbose(str, "getRearTrafficBrakeAlertStateR=" + rearTrafficBrakeAlertStateR);
            return rearTrafficBrakeAlertStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearTrafficCrossAlertState() {
        String str = g1;
        LogUtil.verbose(str, "getRearTrafficCrossAlertState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int rearTrafficCrossAlertStateR = carVendorDipilotManager.getRearTrafficCrossAlertStateR();
            LogUtil.verbose(str, "getRearTrafficCrossAlertStateR=" + rearTrafficCrossAlertStateR);
            return rearTrafficCrossAlertStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearTrafficMoveThroughMode() {
        return 0;
    }

    public int getRedLightViolationWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getRedLightViolationWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int redLightViolationWarningStateR = carVendorDipilotManager.getRedLightViolationWarningStateR();
            LogUtil.verbose(str, "getRedLightViolationWarningStateR=" + redLightViolationWarningStateR);
            return redLightViolationWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRemoteControlDriveCloseTops() {
        String str = g1;
        LogUtil.verbose(str, "getRemoteControlDriveCloseTops");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int remoteControlDriveCloseTops = carVendorDipilotManager.getRemoteControlDriveCloseTops();
            LogUtil.verbose(str, "getRemoteControlDriveCloseTops=" + remoteControlDriveCloseTops);
            return remoteControlDriveCloseTops;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getResponseFeedbackInform() {
        String str = g1;
        LogUtil.verbose(str, " getResponseFeedbackInform.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager == null) {
            return 0;
        }
        try {
            int responseFeedbackInformR = carVendorDigitalkeyManager.getResponseFeedbackInformR();
            LogUtil.verbose(str, " getResponseFeedbackInform.out = " + responseFeedbackInformR);
            return responseFeedbackInformR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getResponseResultOfOpcode() {
        int responseResultOfOpcodeR;
        String str = g1;
        LogUtil.verbose(str, " getResponseResultOfOpcode.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                responseResultOfOpcodeR = carVendorDigitalkeyManager.getResponseResultOfOpcodeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                responseResultOfOpcodeR = -1;
            }
            LogUtil.verbose(str, " getResponseResultOfOpcode.out service =" + responseResultOfOpcodeR);
            return responseResultOfOpcodeR;
        }
        LogUtil.verbose(str, " getResponseResultOfOpcode.null");
        responseResultOfOpcodeR = -1;
        LogUtil.verbose(str, " getResponseResultOfOpcode.out service =" + responseResultOfOpcodeR);
        return responseResultOfOpcodeR;
    }

    public int getResultsOfEnforcement() {
        int resultsOfEnforcementR;
        String str = g1;
        LogUtil.verbose(str, " getResultsOfEnforcement.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                resultsOfEnforcementR = carVendorDigitalkeyManager.getResultsOfEnforcementR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                resultsOfEnforcementR = -1;
            }
            LogUtil.verbose(str, " getResultsOfEnforcement.out=" + resultsOfEnforcementR);
            return resultsOfEnforcementR;
        }
        LogUtil.verbose(str, " getResultsOfEnforcement.null == mCarVendorDigitalkeyManager");
        resultsOfEnforcementR = -1;
        LogUtil.verbose(str, " getResultsOfEnforcement.out=" + resultsOfEnforcementR);
        return resultsOfEnforcementR;
    }

    public int getSaocState() {
        int saocStateR;
        String str = g1;
        LogUtil.verbose(str, " getSaocState.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                saocStateR = carVendorDipilotManager.getSaocStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                saocStateR = -1;
            }
            LogUtil.verbose(str, " getSaocState.out=" + saocStateR);
            return saocStateR;
        }
        LogUtil.verbose(str, " getSaocState.null");
        saocStateR = -1;
        LogUtil.verbose(str, " getSaocState.out=" + saocStateR);
        return saocStateR;
    }

    public int getShowCarModelType() {
        String str = g1;
        LogUtil.verbose(str, "Service getShowCarModelType");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557871104, 0);
        if (carPropertyValueA == null) {
            LogUtil.verbose(str, "ID_SHOW_CAR_MODEL_TYPER is null");
            return 0;
        }
        LogUtil.verbose(str, "getShowCarModelType prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSideDistanceWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getSideDistanceWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int sideDistanceWarningStateR = carVendorDipilotManager.getSideDistanceWarningStateR();
            LogUtil.verbose(str, "getSideDistanceWarningStateR=" + sideDistanceWarningStateR);
            return sideDistanceWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSlifState() {
        int slifStateR;
        String str = g1;
        LogUtil.verbose(str, " getSlifState.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                slifStateR = carVendorDipilotManager.getSlifStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                slifStateR = -1;
            }
            LogUtil.verbose(str, " getSlifState.out=" + slifStateR);
            return slifStateR;
        }
        LogUtil.verbose(str, " getSlifState.null");
        slifStateR = -1;
        LogUtil.verbose(str, " getSlifState.out=" + slifStateR);
        return slifStateR;
    }

    public int getSpeedLimitRange2B4() {
        String str = g1;
        LogUtil.verbose(str, "getSpeedLimitRange2B4 is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int speedLimitRangeSecond = carVendorDipilotManager.getSpeedLimitRangeSecond();
            LogUtil.verbose(str, "getSpeedLimitRangeSecond=" + speedLimitRangeSecond);
            return speedLimitRangeSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSpeedLimitRange2D5() {
        String str = g1;
        LogUtil.verbose(str, "getSpeedLimitRange2D5 is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int speedLimitRangeFirst = carVendorDipilotManager.getSpeedLimitRangeFirst();
            LogUtil.verbose(str, "getSpeedLimitRangeFirst=" + speedLimitRangeFirst);
            return speedLimitRangeFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSpeedLimitRangeFirst() {
        String str = g1;
        LogUtil.verbose(str, "getSpeedLimitRangeFirst");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int speedLimitRangeFirst = carVendorDipilotManager.getSpeedLimitRangeFirst();
            LogUtil.verbose(str, "getSpeedLimitRangeFirst=" + speedLimitRangeFirst);
            return speedLimitRangeFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSpeedLimitRangeSecond() {
        String str = g1;
        LogUtil.verbose(str, "getSpeedLimitRangeSecond");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int speedLimitRangeSecond = carVendorDipilotManager.getSpeedLimitRangeSecond();
            LogUtil.verbose(str, "getSpeedLimitRangeSecond=" + speedLimitRangeSecond);
            return speedLimitRangeSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getStatusOfLockSetting() {
        int statusOfLockSettingR;
        String str = g1;
        LogUtil.verbose(str, " getStatusOfLockSetting.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                statusOfLockSettingR = carVendorDigitalkeyManager.getStatusOfLockSettingR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                statusOfLockSettingR = -1;
            }
            LogUtil.verbose(str, " getStatusOfLockSetting.out=" + statusOfLockSettingR);
            return statusOfLockSettingR;
        }
        LogUtil.verbose(str, " getStatusOfLockSetting.null == mCarVendorDigitalkeyManager");
        statusOfLockSettingR = -1;
        LogUtil.verbose(str, " getStatusOfLockSetting.out=" + statusOfLockSettingR);
        return statusOfLockSettingR;
    }

    public int getStatusOfUnlockSetting() {
        int statusOfUnlockSettingR;
        String str = g1;
        LogUtil.verbose(str, " getStatusOfUnlockSetting.in");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                statusOfUnlockSettingR = carVendorDigitalkeyManager.getStatusOfUnlockSettingR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                statusOfUnlockSettingR = -1;
            }
            LogUtil.verbose(str, " getStatusOfUnlockSetting.out=" + statusOfUnlockSettingR);
            return statusOfUnlockSettingR;
        }
        LogUtil.verbose(str, " getStatusOfUnlockSetting.null == mCarVendorDigitalkeyManager");
        statusOfUnlockSettingR = -1;
        LogUtil.verbose(str, " getStatusOfUnlockSetting.out=" + statusOfUnlockSettingR);
        return statusOfUnlockSettingR;
    }

    public int getSteepDescentSlowDownState() {
        String str = g1;
        LogUtil.verbose(str, "getSteepDescentSlowDownState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856835, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSeatVentilatingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSteepDescentSlowDownState4A9R() {
        String str = g1;
        LogUtil.info(str, "getSteepDescentSlowDownState4A9R");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856895, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSteepDescentSlowDownState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public byte getTrafficAttentionFault() {
        int trafficAttentionFaultR;
        String str = g1;
        LogUtil.verbose(str, " getTrafficAttentionFault.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                trafficAttentionFaultR = carVendorDipilotManager.getTrafficAttentionFaultR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trafficAttentionFaultR = -1;
            }
            LogUtil.verbose(str, " getTrafficAttentionFault.out=" + trafficAttentionFaultR + ",return:" + trafficAttentionFaultR);
            return Byte.parseByte(Integer.toString(trafficAttentionFaultR));
        }
        LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        trafficAttentionFaultR = -1;
        LogUtil.verbose(str, " getTrafficAttentionFault.out=" + trafficAttentionFaultR + ",return:" + trafficAttentionFaultR);
        return Byte.parseByte(Integer.toString(trafficAttentionFaultR));
    }

    public int getTrafficIncidentWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficIncidentWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficIncidentWarningStateR = carVendorDipilotManager.getTrafficIncidentWarningStateR();
            LogUtil.verbose(str, "getTrafficIncidentWarningStateR=" + trafficIncidentWarningStateR);
            return trafficIncidentWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrafficJamWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficJamWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficJamWarningStateR = carVendorDipilotManager.getTrafficJamWarningStateR();
            LogUtil.verbose(str, "getTrafficJamWarningStateR=" + trafficJamWarningStateR);
            return trafficJamWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrafficLightInfoPushState() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficLightInfoPushState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficLightInfoPushStateR = carVendorDipilotManager.getTrafficLightInfoPushStateR();
            LogUtil.verbose(str, "getTrafficLightInfoPushStateR=" + trafficLightInfoPushStateR);
            return trafficLightInfoPushStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrafficLightRecognize2B4State() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficLightRecognize2B4State is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficLightRecognizeStateSecond = carVendorDipilotManager.getTrafficLightRecognizeStateSecond();
            LogUtil.verbose(str, "getTrafficLightRecognizeStateSecond=" + trafficLightRecognizeStateSecond);
            return trafficLightRecognizeStateSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrafficLightRecognize2D5State() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficLightRecognize2D5State is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficLightRecognizeStateFirst = carVendorDipilotManager.getTrafficLightRecognizeStateFirst();
            LogUtil.verbose(str, "getTrafficLightRecognizeStateFirst=" + trafficLightRecognizeStateFirst);
            return trafficLightRecognizeStateFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrafficSignRecognize2B4State() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficSignRecognize2B4State is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856783, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTrafficSignRecognize2B4State prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTrafficSignRecognize2D5State() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficSignRecognize2D5State is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856782, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTrafficSignRecognize2D5State prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTrafficSignRecognize316State() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficSignRecognize316State is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856784, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTrafficSignRecognize316State prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTrafficSignRecognizeState() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficSignRecognizeState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficSignRecognizeStateR = carVendorDipilotManager.getTrafficSignRecognizeStateR();
            LogUtil.verbose(str, "getTrafficSignRecognizeStateR=" + trafficSignRecognizeStateR);
            return trafficSignRecognizeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrafficSignRecognizeStateFirst() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficSignRecognizeStateFirst");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficSignRecognizeStateFirst = carVendorDipilotManager.getTrafficSignRecognizeStateFirst();
            LogUtil.verbose(str, "getTrafficSignRecognizeStateFirst=" + trafficSignRecognizeStateFirst);
            return trafficSignRecognizeStateFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrafficSignRecognizeStateSecond() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficSignRecognizeStateSecond");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficSignRecognizeStateSecond = carVendorDipilotManager.getTrafficSignRecognizeStateSecond();
            LogUtil.verbose(str, "getTrafficSignRecognizeStateSecond=" + trafficSignRecognizeStateSecond);
            return trafficSignRecognizeStateSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrafficSignalWarningState() {
        String str = g1;
        LogUtil.verbose(str, "getTrafficSignalWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int trafficSignalWarningStateR = carVendorDipilotManager.getTrafficSignalWarningStateR();
            LogUtil.verbose(str, "getTrafficSignalWarningStateR=" + trafficSignalWarningStateR);
            return trafficSignalWarningStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getTsrFault() {
        String str = g1;
        LogUtil.verbose(str, " getTsrFault.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856919, 0);
        if (carPropertyValueA == null) {
            return (byte) 0;
        }
        LogUtil.verbose(str, "getTsrFault prop value " + carPropertyValueA.getValue());
        return Byte.parseByte(carPropertyValueA.getValue() + "");
    }

    public int getTsrLitSpdChgInformState() {
        String str = g1;
        LogUtil.verbose(str, "getTsrLitSpdChgInformState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int tsrLitSpdChgInformStateR = carVendorDipilotManager.getTsrLitSpdChgInformStateR();
            LogUtil.verbose(str, "getTsrLitSpdChgInformStateR=" + tsrLitSpdChgInformStateR);
            return tsrLitSpdChgInformStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTsrLitSpdChgSwitchState() {
        String str = g1;
        LogUtil.verbose(str, "getTsrLitSpdChgSwitchState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int tsrLitSpdChgSwitchStateR = carVendorDipilotManager.getTsrLitSpdChgSwitchStateR();
            LogUtil.verbose(str, "getTsrLitSpdChgSwitchStateR=" + tsrLitSpdChgSwitchStateR);
            return tsrLitSpdChgSwitchStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTurnMode() {
        return 0;
    }

    public int getUrbanAIAssistanceState() {
        String str = g1;
        LogUtil.verbose(str, "getUrbanAIAssistanceState");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int urbanAIAssistanceStateR = carVendorDipilotManager.getUrbanAIAssistanceStateR();
            LogUtil.verbose(str, "getUrbanAIAssistanceStateR=" + urbanAIAssistanceStateR);
            return urbanAIAssistanceStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getVehicleSN() {
        LogUtil.verbose(g1, "Service getVehicleSN");
        return "";
    }

    public int getVerifyMode() {
        String str = g1;
        LogUtil.verbose(str, "getVerifyMode");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int verifyModeR = carVendorDipilotManager.getVerifyModeR();
            LogUtil.verbose(str, "getVerifyModeR=" + verifyModeR);
            return verifyModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getVruFaultState() {
        String str = g1;
        LogUtil.verbose(str, "getVruFaultState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            int vruFaultStateR = carVendorDipilotManager.getVruFaultStateR();
            LogUtil.verbose(str, "getVruFaultStateR=" + vruFaultStateR);
            return vruFaultStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ts.appservice.caradapter.d.p
    public void i(CarmodeEventChangeValue carmodeEventChangeValue) {
        String str = g1;
        if (carmodeEventChangeValue == null) {
            LogUtil.error(str, "onCarModeChangeEvent:value=null");
            return;
        }
        int propertyId = carmodeEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onCarModeChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 14;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = carmodeEventChangeValue.getStatus();
        messageObtain.obj = carmodeEventChangeValue;
        this.c1.sendMessage(messageObtain);
    }

    public boolean isActiveBrakeState() {
        return false;
    }

    public boolean isEmergencyLaneMaintenance() {
        return false;
    }

    public boolean isForwardCollisionWarning() {
        return false;
    }

    public boolean isIntelSpeed() {
        return false;
    }

    public boolean isOpenDoorWarning() {
        return false;
    }

    public boolean isRearCarApproachesAssist() {
        return false;
    }

    public boolean isRearTrafficMoveThrough() {
        return false;
    }

    public boolean isSpeedLimitSignIndicates() {
        return false;
    }

    public void r() {
        y();
        com.ts.appservice.caradapter.a.a aVar = this.e;
        if (aVar != null) {
            this.f = false;
            aVar.g();
        }
    }

    public void s(CarVendorDipilotManager carVendorDipilotManager, CarVendorDigitalkeyManager carVendorDigitalkeyManager, CarVendorCarmodeManager carVendorCarmodeManager, CarVendorXcallManager carVendorXcallManager) {
        this.f62a = carVendorDipilotManager;
        this.f63b = carVendorDigitalkeyManager;
        this.c = carVendorCarmodeManager;
        this.d = carVendorXcallManager;
        try {
            if (this.c1 == null) {
                this.c1 = new a(this.b1.getLooper());
                LogUtil.info(g1, "initAssistManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.a aVar = this.a1;
        if (aVar != null) {
            this.f = true;
            aVar.l();
        }
    }

    public void sendCurAccountSwitchFlag(int i) {
        String str = g1;
        LogUtil.verbose(str, " sendCurAccountSwitchFlag.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setCurAccountSwitchFlagS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " sendCurAccountSwitchFlag.null");
        }
        LogUtil.verbose(str, " sendCurAccountSwitchFlag.out service");
    }

    public void sendDrivingPasswordSetting(int i) {
        String str = g1;
        LogUtil.verbose(str, " sendDrivingPasswordSetting.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setDrivingPasswordSettingS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " sendDrivingPasswordSetting.null");
        }
        LogUtil.verbose(str, " sendDrivingPasswordSetting.out service");
    }

    public void sendOpcodeDataToSec3(long j, int i, int i2) {
        String str = g1;
        LogUtil.verbose(str, " sendOpcodeDataToSec3.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setOpcodeDataToSecUserIdS(j);
                this.f63b.setOpcodeDataToSecRandomS(i);
                this.f63b.setOpcodeDataToSecNfcS(i2);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " sendOpcodeDataToSec3.null");
        }
        LogUtil.verbose(str, " sendOpcodeDataToSec3.out service");
    }

    public void sendOpcodeDataToSec4(long j, long j2, int i, int i2) {
        String str = g1;
        LogUtil.verbose(str, " sendOpcodeDataToSec4.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setOpcodeDataToSecUserIdS(j);
                this.f63b.setOpcodeDataToSecOpcodeS(j2);
                this.f63b.setOpcodeDataToSecRandomS(i);
                this.f63b.setOpcodeDataToSecNfcS(i2);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " sendOpcodeDataToSec4.null");
        }
        LogUtil.verbose(str, " sendOpcodeDataToSec4.out service");
    }

    public void sendOpcodeFunctionalFailure(int i) {
        String str = g1;
        LogUtil.verbose(str, " sendOpcodeFunctionalFailure.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setOpcodeFunctionalFailureS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " sendOpcodeFunctionalFailure.null");
        }
        LogUtil.verbose(str, " sendOpcodeFunctionalFailure.out service");
    }

    public void sendOpcodeSwitchState(int i) {
        String str = g1;
        LogUtil.verbose(str, " sendOpcodeSwitchState.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setOpcodeSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " sendOpcodeSwitchState.null");
        }
        LogUtil.verbose(str, " sendOpcodeSwitchState.out service");
    }

    public void sendOpcodeSwitchStateSetting(int i) {
        String str = g1;
        LogUtil.verbose(str, " sendOpcodeSwitchStateSetting.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setOpcodeSwitchStateSettingS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " sendOpcodeSwitchStateSetting.null");
        }
        LogUtil.verbose(str, " sendOpcodeSwitchStateSetting.out service");
    }

    public int setAINavigationState(int i) {
        LogUtil.verbose(g1, "setAINavigationState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setAINavigationStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAbnormalVehicleWarningState(int i) {
        LogUtil.verbose(g1, "setAbnormalVehicleWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setAbnormalVehicleWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAccState(int i) {
        int i2;
        String str = g1;
        LogUtil.verbose(str, " setAccState.in service");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            i2 = 1;
            try {
                carVendorDipilotManager.setAccStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setAccState.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setAccState.out service =" + i2);
        return i2;
    }

    public void setActiveBrakeAssist(boolean z) {
    }

    public int setAiCareState(int i) {
        LogUtil.verbose(g1, "setAiCareState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setAiCareStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAiDistanceLightState(int i) {
        LogUtil.verbose(g1, "setAiDistanceLightState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setAiDistanceLightStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAiEmergencyBrakeState(int i) {
        LogUtil.verbose(g1, "setAiEmergencyBrakeState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setAiEmergencyBrakeStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAiMatrixLightState(int i) {
        LogUtil.verbose(g1, "setAiMatrixLightState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setAiMatrixLightStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAlarmType(int i) {
        LogUtil.verbose(g1, "setAlarmType" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setAlarmTypeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setAllowStartTimeAfterUnlockInOut(int i) {
        LogUtil.verbose(g1, "Service setAllowStartTimeAfterUnlockInOut" + i);
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setStartForNoKeyX36S(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAllowStartTimeAfterUnlockOut(int i) {
        LogUtil.verbose(g1, "Service setAllowStartTimeAfterUnlockOut" + i);
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setStartForNoKeyX96S(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAssistDriveAcuApaButton(int i) {
    }

    public int setAutoParkingState(int i) {
        String str = g1;
        LogUtil.verbose(str, "setAutoParkingState.in status is " + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                carVendorDipilotManager.setAutoParkingStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        }
        LogUtil.verbose(str, " setAutoParkingState.out=0");
        return 0;
    }

    public void setAutomaticLaneChangeState(int i) {
    }

    public void setAutomaticParkingBackgroundParkingPush(int i) {
    }

    public void setAutomaticParkingVoicPrompt(int i) {
    }

    public int setBlindMonitorState(int i) {
        LogUtil.verbose(g1, "setBlindMonitorState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setBlindMonitorStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setBsdState(boolean z) {
    }

    public int setBsisCollicionAlarmSwitchS(int i) {
        LogUtil.verbose(g1, "setBsisCollicionAlarmSwitchS" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setBsisCollicionAlarmSwitchS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCallType(int i) {
        LogUtil.verbose(g1, "Service setCallType" + i);
        CarVendorXcallManager carVendorXcallManager = this.d;
        if (carVendorXcallManager == null) {
            return 0;
        }
        try {
            carVendorXcallManager.setCallTypeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCollisionWarningState(int i) {
        LogUtil.verbose(g1, "setCollisionWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setCollisionWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setControlLossWarningState(int i) {
        LogUtil.verbose(g1, "setControlLossWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setControlLossWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCpdStatus(int i) {
        LogUtil.verbose(g1, "setCpdStatus is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setCpdStatusS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setCurrentStorePowerMode(int i) {
    }

    public int setDangerousActionfSwitch(int i) {
        LogUtil.verbose(g1, "setDangerousActionfSwitch is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setDangerousActionfSwitchS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDistractionMonitoringAttention(int i) {
        LogUtil.verbose(g1, "setDistractionMonitoringAttention is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setDistractionMonitoringAttentionS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDmsSelfCheckStatus(int i) {
        LogUtil.verbose(g1, "setDmsSelfCheckStatus is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setDmsSelfCheckStatusS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDmsSwitchStatus(int i) {
        LogUtil.verbose(g1, "setDmsSwitchStatus is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setDmsSwitchStatusS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDoNotPassWarningState(int i) {
        LogUtil.verbose(g1, "setDoNotPassWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setDoNotPassWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setDriverStatusWarningSwitch(int i) {
    }

    public void setDrivingTipsType(int i) {
    }

    public void setDrivingWarningState(int i) {
    }

    public int setE2EAIAssistanceState(int i) {
        LogUtil.verbose(g1, "setE2EAIAssistanceState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setE2EAIAssistanceStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setElecCarBodyStablySysState(int i) {
        LogUtil.verbose(g1, "setElecCarBodyStablySysState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setElecCarBodyStablySysStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setEmergencyBrakeWarningState(int i) {
        LogUtil.verbose(g1, "setEmergencyBrakeWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setEmergencyBrakeWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setEmergencyLaneAssistanceState(int i) {
        LogUtil.verbose(g1, "setEmergencyLaneAssistanceState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setEmergencyLaneAssistanceStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setEmergencyLaneMaintenance(boolean z) {
    }

    public int setEmergencyVehicleWarningState(int i) {
        LogUtil.verbose(g1, "setEmergencyVehicleWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setEmergencyVehicleWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setEngineEnable(boolean z) {
        LogUtil.verbose(g1, "Service setEngineEnable" + z);
        if (this.c != null) {
            try {
                this.c.setEngineEnableS(z ? 1 : 2);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int setFatigueMonitoringStatus(int i) {
        LogUtil.verbose(g1, "setFatigueMonitoringStatus is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setFatigueMonitoringStatusS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFatigueReliefSwitch(int i) {
        LogUtil.verbose(g1, "setFatigueReliefSwitch is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setFatigueReliefSwitchS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFcsrState(int i) {
        int i2;
        String str = g1;
        LogUtil.verbose(str, " setFcsrState.in service");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            i2 = 1;
            try {
                carVendorDipilotManager.setFcsrStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setFcsrState.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setFcsrState.out service =" + i2);
        return i2;
    }

    public void setFingerprintLearningAction(int i) {
    }

    public void setFingerprintLearningReq(int i) {
    }

    public void setForwardCollisionWarning(boolean z) {
    }

    public void setForwardCollisionWarningMode(int i) {
    }

    public int setFrontTrafficBrakeAlertState(int i) {
        LogUtil.verbose(g1, "setFrontTrafficBrakeAlertState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setFrontTrafficBrakeAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFrontTrafficCrossAlertState(int i) {
        LogUtil.verbose(g1, "setFrontTrafficCrossAlertState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setFrontTrafficCrossAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setGreenLightOptimalSpeedAdvisoryState(int i) {
        LogUtil.verbose(g1, "setGreenLightOptimalSpeedAdvisoryState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setGreenLightOptimalSpeedAdvisoryStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHighSpeedAINavigationState(int i) {
        LogUtil.verbose(g1, "setHighSpeedAINavigationState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setHighSpeedAINavigationStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setIntelSpeed(boolean z) {
    }

    public int setIntelligentSpeedLimitAlertState(int i) {
        LogUtil.verbose(g1, "setIntelligentSpeedLimitAlertState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setIntelligentSpeedLimitAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIntelligentSpeedLimitControlState(int i) {
        LogUtil.verbose(g1, "setIntelligentSpeedLimitControlState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setIntelligentSpeedLimitControlStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setInteractiveLaneChangeAssistanceState(int i) {
        LogUtil.verbose(g1, "setInteractiveLaneChangeAssistanceState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setInteractiveLaneChangeAssistanceStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setIntersectionAssistMode(int i) {
    }

    public void setIntersectionAssistSwitch(int i) {
    }

    public int setIntersectionCollisionAlertState(int i) {
        LogUtil.verbose(g1, "setIntersectionCollisionAlertState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setIntersectionCollisionAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIopState(int i) {
        String str = g1;
        LogUtil.verbose(str, " setIopState.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                carVendorDipilotManager.setIopStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setIopState.null");
        }
        LogUtil.verbose(str, " setIopState.out=0");
        return 0;
    }

    public int setIsliWarningLevelSwitchStatus(int i) {
        String str = g1;
        LogUtil.verbose(str, "setIsliWarningLevelSwitchStatus.in status is " + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                carVendorDipilotManager.setIsliWarningLevelSwitchStatusS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, "mCarVendorDipilotManager is null");
        }
        LogUtil.verbose(str, " setIsliWarningLevelSwitchStatus.out=0");
        return 0;
    }

    public int setKeyIdOne(byte[] bArr) {
        String str = g1;
        LogUtil.verbose(str, "setOperationCmdAndKeyType byte[] is " + Arrays.toString(bArr));
        byte[] bArr2 = new byte[bArr.length];
        int length = bArr.length + (-1);
        int i = 0;
        while (length >= 0) {
            bArr2[length] = bArr[i];
            length--;
            i++;
        }
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setKeyIdOne(bArr2);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setKeyIdOne.null == mCarVendorDigitalkeyManager");
        }
        LogUtil.verbose(str, " setKeyIdOne.out=0");
        return 0;
    }

    public int setKeyIdTwo(byte[] bArr) {
        String str = g1;
        LogUtil.verbose(str, "setKeyIdTwo byte[] is " + Arrays.toString(bArr));
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setKeyIdTwo(bArr);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setKeyIdTwo.null == mCarVendorDigitalkeyManager");
        }
        LogUtil.verbose(str, " setKeyIdTwo.out=0");
        return 0;
    }

    public void setLaneAssistMode(int i) {
    }

    public void setLaneAssistState(boolean z) {
    }

    public int setLaneAssistType(int i) {
        LogUtil.verbose(g1, "setLaneAssistType" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setLaneAssistTypeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLdwStaticCalibration(int i) {
        LogUtil.verbose(g1, "setLDWStaticCalibration is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setLdwStaticCalibrationS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLowSpeedEmergencyBrakingState(int i) {
        LogUtil.verbose(g1, "setLowSpeedEmergencyBrakingState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setLowSpeedEmergencyBrakingStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setMemoryDrivingMode(int i) {
    }

    public int setMistakeGasState(int i) {
        LogUtil.verbose(g1, "setMistakeGasState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setMistakeGasStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMoisFunctionSwitchS(int i) {
        LogUtil.verbose(g1, "setMoisFunctionSwitchS" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setMoisFunctionSwitchS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setMultiplexor(int i) {
        String str = g1;
        LogUtil.verbose(str, " setMultiplexor.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setMultiplexorS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setMultiplexor.null");
        }
        LogUtil.verbose(str, " setMultiplexor.out service");
    }

    public int setOpenDoorAlertState(int i) {
        LogUtil.verbose(g1, "setOpenDoorAlertState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setOpenDoorAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setOpenDoorWarning(boolean z) {
    }

    public void setOpenPanorama(int i) {
    }

    public int setOperationCmdAndKeyType(int i, int i2) {
        String str = g1;
        LogUtil.verbose(str, "setOperationCmdAndKeyType cmd is " + i + ",type is " + i2);
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setOperationCmdAndKeyType(new int[]{i, i2});
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setOperationCmdAndKeyType.null == mCarVendorDigitalkeyManager");
        }
        LogUtil.verbose(str, " setOperationCmdAndKeyType.out=0");
        return 0;
    }

    public void setOverspeedAlarm(boolean z) {
    }

    public void setOverspeedAlarmValue(int i) {
    }

    public void setPedestrianWarningSoundSwitch(int i) {
    }

    public int setPredictCrashAlertMode(int i) {
        LogUtil.verbose(g1, "setPredictCrashAlertMode" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setPredictCrashAlertModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPredictCrashAlertState(int i) {
        LogUtil.verbose(g1, "setPredictCrashAlertState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setPredictCrashAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setQueryOwnersKeyInOut(int i) {
        LogUtil.verbose(g1, "Service setQueryOwnersKeyInOut" + i);
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setQueryOwnerKeyX36(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setQueryOwnersKeyOut(int i) {
        LogUtil.verbose(g1, "Service setQueryOwnersKeyOut" + i);
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setQueryOwnerKeyX96(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRearCarApproachesAssistOn(boolean z) {
    }

    public int setRearCrashAlertState(int i) {
        LogUtil.verbose(g1, "setRearCrashAlertState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setRearCrashAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setRearCrashAutoBreak(int i) {
    }

    public int setRearTrafficBrakeAlertState(int i) {
        LogUtil.verbose(g1, "setRearTrafficBrakeAlertState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setRearTrafficBrakeAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearTrafficCrossAlertState(int i) {
        LogUtil.verbose(g1, "setRearTrafficCrossAlertState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setRearTrafficCrossAlertStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setRearTrafficMoveThroughMode(int i) {
    }

    public void setRearTrafficMoveThroughOn(boolean z) {
    }

    public int setRedLightViolationWarningState(int i) {
        LogUtil.verbose(g1, "setRedLightViolationWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setRedLightViolationWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setRegisterKeyStartPairingInOut(int i) {
        LogUtil.verbose(g1, "Service setRegisterKeyStartPairingInOut" + i);
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setDigitalKeyPairingX36(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRegisterKeyStartPairingOut(int i) {
        LogUtil.verbose(g1, "Service setRegisterKeyStartPairingOut" + i);
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                carVendorDigitalkeyManager.setDigitalKeyPairingX96(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int setRequestType(int i) {
        LogUtil.verbose(g1, "Service setRequestType" + i);
        CarVendorXcallManager carVendorXcallManager = this.d;
        if (carVendorXcallManager == null) {
            return 0;
        }
        try {
            carVendorXcallManager.setRequestTypeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSaocState(int i) {
        String str = g1;
        LogUtil.verbose(str, " setSaocState.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                carVendorDipilotManager.setSaocStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setSaocState.null");
        }
        LogUtil.verbose(str, " setSaocState.out=0");
        return 0;
    }

    public void setShowCarModelType(int i) {
        LogUtil.verbose(g1, "Service setShowCarModelType");
    }

    public int setSideDistanceWarningState(int i) {
        LogUtil.verbose(g1, "setSideDistanceWarningState is execute");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setSideDistanceWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSlifState(int i) {
        String str = g1;
        LogUtil.verbose(str, " setSlifState.in");
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager != null) {
            try {
                carVendorDipilotManager.setSlifStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setSlifState.null");
        }
        LogUtil.verbose(str, " setSlifState.out=0");
        return 0;
    }

    public int setSpeedLimitRange(int i) {
        LogUtil.verbose(g1, "setSpeedLimitRange" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setSpeedLimitRangeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setSpeedLimitSignIndicatesOn(boolean z) {
    }

    public int setSteepDescentSlowDownState(int i) {
        LogUtil.verbose(g1, "setSteepDescentSlowDownState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setSteepDescentSlowDownStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTrafficIncidentWarningState(int i) {
        LogUtil.verbose(g1, "setTrafficIncidentWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setTrafficIncidentWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTrafficJamWarningState(int i) {
        LogUtil.verbose(g1, "setTrafficJamWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setTrafficJamWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTrafficLightInfoPushState(int i) {
        LogUtil.verbose(g1, "setTrafficLightInfoPushState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setTrafficLightInfoPushStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTrafficLightRecognizeState(int i) {
        LogUtil.verbose(g1, "setTrafficLightRecognizeState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setTrafficLightRecognizeStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTrafficSignRecognizeState(int i) {
        LogUtil.verbose(g1, "setTrafficSignRecognizeState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setTrafficSignRecognizeStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTrafficSignalWarningState(int i) {
        LogUtil.verbose(g1, "setTrafficSignalWarningState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setTrafficSignalWarningStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTsrLitSpdChgSwitchState(int i) {
        LogUtil.verbose(g1, "setTsrLitSpdChgSwitchState is execute" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setTsrLitSpdChgSwitchStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setTurnMode(int i) {
    }

    public int setUrbanAIAssistanceState(int i) {
        LogUtil.verbose(g1, "setUrbanAIAssistanceState" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setUrbanAIAssistanceStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setVerifyMode(int i) {
        LogUtil.verbose(g1, "setVerifyMode" + i);
        CarVendorDipilotManager carVendorDipilotManager = this.f62a;
        if (carVendorDipilotManager == null) {
            return 0;
        }
        try {
            carVendorDipilotManager.setVerifyModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean subscribeCarAssistCallback(ICarAssistAdapterCallback iCarAssistAdapterCallback) {
        if (this.a1 == null || iCarAssistAdapterCallback == null) {
            return false;
        }
        LogUtil.verbose(g1, "into subscribe: " + iCarAssistAdapterCallback.toString());
        this.a1.h(iCarAssistAdapterCallback);
        if (!this.f) {
            return true;
        }
        this.a1.l();
        return true;
    }

    public int supportOpcodeStartup() {
        int supportOpcodeStartupR;
        String str = g1;
        LogUtil.verbose(str, " supportOpcodeStartup.in service");
        CarVendorDigitalkeyManager carVendorDigitalkeyManager = this.f63b;
        if (carVendorDigitalkeyManager != null) {
            try {
                supportOpcodeStartupR = carVendorDigitalkeyManager.getSupportOpcodeStartupR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportOpcodeStartupR = 0;
            }
            LogUtil.verbose(str, " supportOpcodeStartup.out service =" + supportOpcodeStartupR);
            return supportOpcodeStartupR;
        }
        LogUtil.verbose(str, " supportOpcodeStartup.null");
        supportOpcodeStartupR = 0;
        LogUtil.verbose(str, " supportOpcodeStartup.out service =" + supportOpcodeStartupR);
        return supportOpcodeStartupR;
    }

    public boolean unsubscribeCarAssistCallback(ICarAssistAdapterCallback iCarAssistAdapterCallback) {
        if (this.a1 == null || iCarAssistAdapterCallback == null) {
            return false;
        }
        LogUtil.verbose(g1, "into unsubscribe: " + iCarAssistAdapterCallback.toString());
        this.a1.i(iCarAssistAdapterCallback);
        return true;
    }

    public void v(int i, int i2, DipilotEventChangeValue dipilotEventChangeValue) {
        String str = g1;
        try {
            switch (i) {
                case 557845261:
                    int remoteControlDriveCloseTops = dipilotEventChangeValue.getRemoteControlDriveCloseTops();
                    this.U = remoteControlDriveCloseTops;
                    this.d1.setRemoteControlDriveCloseTops(remoteControlDriveCloseTops);
                    LogUtil.verbose(str, "ID_REMOTE_CONTROL_DRIVE_CLOSE_TOPS:" + this.U);
                    break;
                case 557845271:
                    int lowHODToastR = dipilotEventChangeValue.getLowHODToastR();
                    this.d1.setLowHodToastR(lowHODToastR);
                    LogUtil.debug(str, "ID_LOW_H_O_D_TOAST_CHANGED_R:" + lowHODToastR);
                    break;
                case 557856077:
                    int supportIntelligentNavigationFirst = dipilotEventChangeValue.getSupportIntelligentNavigationFirst();
                    this.g0 = supportIntelligentNavigationFirst;
                    this.d1.setSupportIntelligentNavigationFirst(supportIntelligentNavigationFirst);
                    LogUtil.verbose(str, "ID_SUPPORT_INTELLIGENT_NAVIGATION_FIRST:" + this.g0);
                    break;
                case 557856078:
                    int supportIntelligentNavigationSecond = dipilotEventChangeValue.getSupportIntelligentNavigationSecond();
                    this.h0 = supportIntelligentNavigationSecond;
                    this.d1.setSupportIntelligentNavigationSecond(supportIntelligentNavigationSecond);
                    LogUtil.verbose(str, "ID_SUPPORT_INTELLIGENT_NAVIGATION_SECOND:" + this.h0);
                    break;
                case 557856079:
                    int supportHighwayNavigation = dipilotEventChangeValue.getSupportHighwayNavigation();
                    this.i0 = supportHighwayNavigation;
                    this.d1.setSupportHighwayNavigation(supportHighwayNavigation);
                    LogUtil.verbose(str, "ID_SUPPORT_HIGHWAY_NAVIGATION:" + this.i0);
                    break;
                case 557856080:
                    int supportUrbanNavigation = dipilotEventChangeValue.getSupportUrbanNavigation();
                    this.j0 = supportUrbanNavigation;
                    this.d1.setSupportUrbanNavigation(supportUrbanNavigation);
                    LogUtil.verbose(str, "ID_SUPPORT_URBAN_NAVIGATION:" + this.j0);
                    break;
                case 557856081:
                    int supportEndToEndNavigation = dipilotEventChangeValue.getSupportEndToEndNavigation();
                    this.k0 = supportEndToEndNavigation;
                    this.d1.setSupportEndToEndNavigation(supportEndToEndNavigation);
                    LogUtil.verbose(str, "ID_SUPPORT_END_TO_END_NAVIGATION:" + this.k0);
                    break;
                case 557856082:
                    int supportLaneChangeAssist = dipilotEventChangeValue.getSupportLaneChangeAssist();
                    this.l0 = supportLaneChangeAssist;
                    this.d1.setSupportLaneChangeAssist(supportLaneChangeAssist);
                    LogUtil.verbose(str, "ID_SUPPORT_LANE_CHANGE_ASSIST:" + this.l0);
                    break;
                case 557856083:
                    int supportLaneChangeAssist2 = dipilotEventChangeValue.getSupportLaneChangeAssist();
                    this.l0 = supportLaneChangeAssist2;
                    this.d1.setSupportLaneChangeAssist(supportLaneChangeAssist2);
                    LogUtil.verbose(str, "ID_SUPPORT_LANE_DEPARTURE_WARNING:" + this.l0);
                    break;
                case 557856084:
                    int supportLaneKeep = dipilotEventChangeValue.getSupportLaneKeep();
                    this.m0 = supportLaneKeep;
                    this.d1.setSupportLaneKeep(supportLaneKeep);
                    LogUtil.verbose(str, "ID_SUPPORT_LANE_KEEP:" + this.m0);
                    break;
                case 557856085:
                    int supportLaneDepartureStop = dipilotEventChangeValue.getSupportLaneDepartureStop();
                    this.n0 = supportLaneDepartureStop;
                    this.d1.setSupportLaneDepartureStop(supportLaneDepartureStop);
                    LogUtil.verbose(str, "ID_SUPPORT_LANE_DEPARTURE_STOP:" + this.n0);
                    break;
                case 557856086:
                    int supportEmergencyLaneKeepAssist = dipilotEventChangeValue.getSupportEmergencyLaneKeepAssist();
                    this.o0 = supportEmergencyLaneKeepAssist;
                    this.d1.setSupportEmergencyLaneKeepAssist(supportEmergencyLaneKeepAssist);
                    LogUtil.verbose(str, "ID_SUPPORT_EMERGENCY_LANE_KEEP_ASSIST:" + this.o0);
                    break;
                case 557856087:
                    int supportTrafficSignRecogniton = dipilotEventChangeValue.getSupportTrafficSignRecogniton();
                    this.p0 = supportTrafficSignRecogniton;
                    this.d1.setSupportTrafficSignRecogniton(supportTrafficSignRecogniton);
                    LogUtil.verbose(str, "ID_SUPPORT_TRAFFIC_SIGN_RECOGNITON:" + this.p0);
                    break;
                case 557856088:
                    int supportIntellignetSpeedLimitAlert = dipilotEventChangeValue.getSupportIntellignetSpeedLimitAlert();
                    this.q0 = supportIntellignetSpeedLimitAlert;
                    this.d1.setSupportIntellignetSpeedLimitAlert(supportIntellignetSpeedLimitAlert);
                    LogUtil.verbose(str, "ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_ALERT:" + this.q0);
                    break;
                case 557856089:
                    int supportIntellignetSpeedLimitControl = dipilotEventChangeValue.getSupportIntellignetSpeedLimitControl();
                    this.r0 = supportIntellignetSpeedLimitControl;
                    this.d1.setSupportIntellignetSpeedLimitControl(supportIntellignetSpeedLimitControl);
                    LogUtil.verbose(str, "ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_CONTROL:" + this.r0);
                    break;
                case 557856090:
                    int supportTrafficLightAttention = dipilotEventChangeValue.getSupportTrafficLightAttention();
                    this.s0 = supportTrafficLightAttention;
                    this.d1.setSupportTrafficLightAttention(supportTrafficLightAttention);
                    LogUtil.verbose(str, "ID_SUPPORT_TRAFFIC_LIGHT_ATTENTION:" + this.s0);
                    break;
                case 557856094:
                    int supportFrontCrossingTrafficAlert = dipilotEventChangeValue.getSupportFrontCrossingTrafficAlert();
                    this.t0 = supportFrontCrossingTrafficAlert;
                    this.d1.setSupportFrontCrossingTrafficAlert(supportFrontCrossingTrafficAlert);
                    LogUtil.verbose(str, "ID_SUPPORT_FRONT_CROSSING_TRAFFIC_ALERT:" + this.t0);
                    break;
                case 557856095:
                    int supportFrontCrossingTrafficBrake = dipilotEventChangeValue.getSupportFrontCrossingTrafficBrake();
                    this.u0 = supportFrontCrossingTrafficBrake;
                    this.d1.setSupportFrontCrossingTrafficBrake(supportFrontCrossingTrafficBrake);
                    LogUtil.verbose(str, "ID_SUPPORT_FRONT_CROSSING_TRAFFIC_BRAKE:" + this.u0);
                    break;
                case 557856097:
                    int supportPredictCrashAlertModeFirst = dipilotEventChangeValue.getSupportPredictCrashAlertModeFirst();
                    this.v0 = supportPredictCrashAlertModeFirst;
                    this.d1.setSupportPredictCrashAlertModeFirst(supportPredictCrashAlertModeFirst);
                    LogUtil.verbose(str, "ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_FIRST:" + this.v0);
                    break;
                case 557856098:
                    int supportPredictCrashAlertModeSecond = dipilotEventChangeValue.getSupportPredictCrashAlertModeSecond();
                    this.w0 = supportPredictCrashAlertModeSecond;
                    this.d1.setSupportPredictCrashAlertModeSecond(supportPredictCrashAlertModeSecond);
                    LogUtil.verbose(str, "ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_SECOND:" + this.w0);
                    break;
                case 557856099:
                    int supportAiEmergencyBrake = dipilotEventChangeValue.getSupportAiEmergencyBrake();
                    this.x0 = supportAiEmergencyBrake;
                    this.d1.setSupportAiEmergencyBrake(supportAiEmergencyBrake);
                    LogUtil.verbose(str, "ID_SUPPORT_AI_EMERGENCY_BRAKE:" + this.x0);
                    break;
                case 557856106:
                    int supportAiDistanceLight = dipilotEventChangeValue.getSupportAiDistanceLight();
                    this.y0 = supportAiDistanceLight;
                    this.d1.setSupportAiDistanceLight(supportAiDistanceLight);
                    LogUtil.verbose(str, "ID_SUPPORT_AI_DISTANCE_LIGHT:" + this.y0);
                    break;
                case 557856768:
                    int highSpeedAINavigationStateR = dipilotEventChangeValue.getHighSpeedAINavigationStateR();
                    this.v = highSpeedAINavigationStateR;
                    this.d1.setmHighSpeedAINavigationStateR(highSpeedAINavigationStateR);
                    LogUtil.verbose(str, "ID_HIGH_SPEED_A_I_NAVIGATION_STATE_R:" + this.v);
                    break;
                case 557856770:
                    int urbanAIAssistanceStateR = dipilotEventChangeValue.getUrbanAIAssistanceStateR();
                    this.S = urbanAIAssistanceStateR;
                    this.d1.setmUrbanAIAssistanceStateR(urbanAIAssistanceStateR);
                    LogUtil.verbose(str, "ID_URBAN_A_I_ASSISTANCE_STATE_R:" + this.S);
                    break;
                case 557856772:
                    int e2EAIAssistanceStateR = dipilotEventChangeValue.getE2EAIAssistanceStateR();
                    this.q = e2EAIAssistanceStateR;
                    this.d1.setmE2EAIAssistanceStateR(e2EAIAssistanceStateR);
                    LogUtil.verbose(str, "ID_E_E_A_I_ASSISTANCE_STATE_R:" + this.q);
                    break;
                case 557856774:
                    int interactiveLaneChangeAssistanceStateR = dipilotEventChangeValue.getInteractiveLaneChangeAssistanceStateR();
                    this.A = interactiveLaneChangeAssistanceStateR;
                    this.d1.setmInteractiveLaneChangeAssistanceStateR(interactiveLaneChangeAssistanceStateR);
                    LogUtil.verbose(str, "ID_INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE_R:" + this.A);
                    break;
                case 557856776:
                    int aINavigationStateR = dipilotEventChangeValue.getAINavigationStateR();
                    this.g = aINavigationStateR;
                    this.d1.setmAINavigationStateR(aINavigationStateR);
                    this.d1.setMonIndChangedAdasThree(this.g);
                    LogUtil.verbose(str, "ID_A_I_NAVIGATION_STATE_R:" + this.g);
                    break;
                case 557856778:
                    int laneAssistTypeR = dipilotEventChangeValue.getLaneAssistTypeR();
                    this.B = laneAssistTypeR;
                    this.d1.setmLaneAssistTypeR(laneAssistTypeR);
                    LogUtil.verbose(str, "ID_LANE_ASSIST_TYPE_R:" + this.B);
                    break;
                case 557856780:
                    int alarmTypeR = dipilotEventChangeValue.getAlarmTypeR();
                    this.n = alarmTypeR;
                    this.d1.setmAlarmTypeR(alarmTypeR);
                    LogUtil.verbose(str, "ID_ALARM_TYPE_R:" + this.n);
                    break;
                case 557856782:
                    int trafficSignRecognizeStateFirst = dipilotEventChangeValue.getTrafficSignRecognizeStateFirst();
                    this.P = trafficSignRecognizeStateFirst;
                    this.d1.setmTrafficSignRecognizeStateFirst(trafficSignRecognizeStateFirst);
                    LogUtil.verbose(str, "ID_TRAFFIC_SIGN_RECOGNIZE_STATE_FIRST:" + this.P);
                    break;
                case 557856783:
                    int trafficSignRecognizeStateSecond = dipilotEventChangeValue.getTrafficSignRecognizeStateSecond();
                    this.R = trafficSignRecognizeStateSecond;
                    this.d1.setmTrafficSignRecognizeStateSecond(trafficSignRecognizeStateSecond);
                    LogUtil.verbose(str, "ID_TRAFFIC_SIGN_RECOGNIZE_STATE_SECOND:" + this.R);
                    break;
                case 557856784:
                    int trafficSignRecognizeStateR = dipilotEventChangeValue.getTrafficSignRecognizeStateR();
                    this.Q = trafficSignRecognizeStateR;
                    this.d1.setmTrafficSignRecognizeStateR(trafficSignRecognizeStateR);
                    LogUtil.verbose(str, "ID_TRAFFIC_SIGN_RECOGNIZE_STATE_R:" + this.Q);
                    break;
                case 557856786:
                    int speedLimitRangeFirst = dipilotEventChangeValue.getSpeedLimitRangeFirst();
                    this.K = speedLimitRangeFirst;
                    this.d1.setmSpeedLimitRangeFirst(speedLimitRangeFirst);
                    LogUtil.verbose(str, "ID_SPEED_LIMIT_RANGE_FIRST:" + this.K);
                    break;
                case 557856787:
                    int speedLimitRangeSecond = dipilotEventChangeValue.getSpeedLimitRangeSecond();
                    this.L = speedLimitRangeSecond;
                    this.d1.setmSpeedLimitRangeSecond(speedLimitRangeSecond);
                    LogUtil.verbose(str, "ID_SPEED_LIMIT_RANGE_SECOND:" + this.L);
                    break;
                case 557856789:
                    int intelligentSpeedLimitAlertStateFirst = dipilotEventChangeValue.getIntelligentSpeedLimitAlertStateFirst();
                    this.w = intelligentSpeedLimitAlertStateFirst;
                    this.d1.setmIntelligentSpeedLimitAlertStateFirst(intelligentSpeedLimitAlertStateFirst);
                    LogUtil.verbose(str, "ID_INTELLIGENT_SPEED_LIMIT_ALERT_STATE_FIRST:" + this.w);
                    break;
                case 557856790:
                    int intelligentSpeedLimitAlertStateSecond = dipilotEventChangeValue.getIntelligentSpeedLimitAlertStateSecond();
                    this.x = intelligentSpeedLimitAlertStateSecond;
                    this.d1.setmIntelligentSpeedLimitAlertStateSecond(intelligentSpeedLimitAlertStateSecond);
                    LogUtil.verbose(str, "ID_INTELLIGENT_SPEED_LIMIT_ALERT_STATE_SECOND:" + this.x);
                    break;
                case 557856792:
                    int intelligentSpeedLimitControlStateSecond = dipilotEventChangeValue.getIntelligentSpeedLimitControlStateSecond();
                    this.z = intelligentSpeedLimitControlStateSecond;
                    this.d1.setmIntelligentSpeedLimitControlStateSecond(intelligentSpeedLimitControlStateSecond);
                    LogUtil.verbose(str, "ID_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE_SECOND:" + this.z);
                    break;
                case 557856794:
                    int verifyModeR = dipilotEventChangeValue.getVerifyModeR();
                    this.T = verifyModeR;
                    this.d1.setmVerifyModeR(verifyModeR);
                    LogUtil.verbose(str, "ID_VERIFY_MODE_R:" + this.T);
                    break;
                case 557856796:
                    int aiCareStateR = dipilotEventChangeValue.getAiCareStateR();
                    this.h = aiCareStateR;
                    this.d1.setmAiCareStateR(aiCareStateR);
                    LogUtil.verbose(str, "ID_AI_CARE_STATE_R:" + this.h);
                    break;
                case 557856798:
                    int mistakeGasStateR = dipilotEventChangeValue.getMistakeGasStateR();
                    this.C = mistakeGasStateR;
                    this.d1.setmMistakeGasStateR(mistakeGasStateR);
                    LogUtil.verbose(str, "ID_MISTAKE_GAS_STATE_R:" + this.C);
                    break;
                case 557856800:
                    int aiMatrixLightStateFirst = dipilotEventChangeValue.getAiMatrixLightStateFirst();
                    this.l = aiMatrixLightStateFirst;
                    this.d1.setmAiMatrixLightStateFirst(aiMatrixLightStateFirst);
                    LogUtil.verbose(str, "ID_AI_MATRIX_LIGHT_STATE_FIRST:" + this.l);
                    break;
                case 557856801:
                    int aiMatrixLightStateSecond = dipilotEventChangeValue.getAiMatrixLightStateSecond();
                    this.m = aiMatrixLightStateSecond;
                    this.d1.setmAiMatrixLightStateSecond(aiMatrixLightStateSecond);
                    LogUtil.verbose(str, "ID_AI_MATRIX_LIGHT_STATE_SECOND:" + this.m);
                    break;
                case 557856804:
                    int aiDistanceLightStateSecond = dipilotEventChangeValue.getAiDistanceLightStateSecond();
                    this.j = aiDistanceLightStateSecond;
                    this.d1.setmAiDistanceLightStateSecond(aiDistanceLightStateSecond);
                    LogUtil.verbose(str, "ID_AI_DISTANCE_LIGHT_STATER_SECOND:" + this.j);
                    break;
                case 557856806:
                    int trafficLightRecognizeStateFirst = dipilotEventChangeValue.getTrafficLightRecognizeStateFirst();
                    this.N = trafficLightRecognizeStateFirst;
                    this.d1.setmTrafficLightRecognizeStateFirst(trafficLightRecognizeStateFirst);
                    LogUtil.verbose(str, "ID_TRAFFIC_LIGHT_RECOGNIZE_STATE_FIRST:" + this.N);
                    break;
                case 557856809:
                    int emergencyLaneAssistanceStateFirst = dipilotEventChangeValue.getEmergencyLaneAssistanceStateFirst();
                    this.r = emergencyLaneAssistanceStateFirst;
                    this.d1.setmEmergencyLaneAssistanceStateFirst(emergencyLaneAssistanceStateFirst);
                    LogUtil.verbose(str, "ID_EMERGENCY_LANE_ASSISTANCE_STATE_FIRST:" + this.r);
                    break;
                case 557856810:
                    int emergencyLaneAssistanceStateSecond = dipilotEventChangeValue.getEmergencyLaneAssistanceStateSecond();
                    this.s = emergencyLaneAssistanceStateSecond;
                    this.d1.setmEmergencyLaneAssistanceStateSecond(emergencyLaneAssistanceStateSecond);
                    LogUtil.verbose(str, "ID_EMERGENCY_LANE_ASSISTANCE_STATE_SECOND:" + this.s);
                    break;
                case 557856812:
                    int blindMonitorStateFirst = dipilotEventChangeValue.getBlindMonitorStateFirst();
                    this.o = blindMonitorStateFirst;
                    this.d1.setmBlindMonitorStateFirst(blindMonitorStateFirst);
                    LogUtil.verbose(str, "ID_BLIND_MONITOR_STATE_FIRST:" + this.o);
                    break;
                case 557856813:
                    int blindMonitorStateSecond = dipilotEventChangeValue.getBlindMonitorStateSecond();
                    this.p = blindMonitorStateSecond;
                    this.d1.setmBlindMonitorStateSecond(blindMonitorStateSecond);
                    LogUtil.verbose(str, "ID_BLIND_MONITOR_STATE_SECOND:" + this.p);
                    break;
                case 557856815:
                    int openDoorAlertStateR = dipilotEventChangeValue.getOpenDoorAlertStateR();
                    this.D = openDoorAlertStateR;
                    this.d1.setmOpenDoorAlertStateR(openDoorAlertStateR);
                    LogUtil.verbose(str, "ID_OPEN_DOOR_ALERT_STATE_R:" + this.D);
                    break;
                case 557856817:
                    int rearCrashAlertStateR = dipilotEventChangeValue.getRearCrashAlertStateR();
                    this.H = rearCrashAlertStateR;
                    this.d1.setmRearCrashAlertStateR(rearCrashAlertStateR);
                    LogUtil.verbose(str, "ID_REAR_CRASH_ALERT_STATE_R:" + this.H);
                    break;
                case 557856819:
                    int rearTrafficCrossAlertStateR = dipilotEventChangeValue.getRearTrafficCrossAlertStateR();
                    this.J = rearTrafficCrossAlertStateR;
                    this.d1.setmRearTrafficCrossAlertStateR(rearTrafficCrossAlertStateR);
                    LogUtil.verbose(str, "ID_REAR_TRAFFIC_CROSS_ALERT_STATE_R:" + this.J);
                    break;
                case 557856821:
                    int rearTrafficBrakeAlertStateR = dipilotEventChangeValue.getRearTrafficBrakeAlertStateR();
                    this.I = rearTrafficBrakeAlertStateR;
                    this.d1.setmRearTrafficBrakeAlertStateR(rearTrafficBrakeAlertStateR);
                    LogUtil.verbose(str, "ID_REAR_TRAFFIC_BRAKE_ALERT_STATE_R:" + this.I);
                    break;
                case 557856823:
                    int aiEmergencyBrakeStateR = dipilotEventChangeValue.getAiEmergencyBrakeStateR();
                    this.k = aiEmergencyBrakeStateR;
                    this.d1.setmAiEmergencyBrakeStateR(aiEmergencyBrakeStateR);
                    LogUtil.verbose(str, "ID_AI_EMERGENCY_BRAKE_STATE_R:" + this.k);
                    break;
                case 557856825:
                    int predictCrashAlertStateR = dipilotEventChangeValue.getPredictCrashAlertStateR();
                    this.G = predictCrashAlertStateR;
                    this.d1.setmPredictCrashAlertStateR(predictCrashAlertStateR);
                    LogUtil.verbose(str, "ID_PREDICT_CRASH_ALERT_STATE_R:" + this.G);
                    break;
                case 557856827:
                    int predictCrashAlertModeRFirst = dipilotEventChangeValue.getPredictCrashAlertModeRFirst();
                    this.E = predictCrashAlertModeRFirst;
                    this.d1.setmPredictCrashAlertModeRFirst(predictCrashAlertModeRFirst);
                    LogUtil.verbose(str, "ID_PREDICT_CRASH_ALERT_MODE_FIRST:" + this.E);
                    break;
                case 557856828:
                    int predictCrashAlertModeRSecond = dipilotEventChangeValue.getPredictCrashAlertModeRSecond();
                    this.F = predictCrashAlertModeRSecond;
                    this.d1.setmPredictCrashAlertModeRSecond(predictCrashAlertModeRSecond);
                    LogUtil.verbose(str, "ID_PREDICT_CRASH_ALERT_MODE_SECOND:" + this.F);
                    break;
                case 557856830:
                    int frontTrafficCrossAlertStateR = dipilotEventChangeValue.getFrontTrafficCrossAlertStateR();
                    this.u = frontTrafficCrossAlertStateR;
                    this.d1.setmFrontTrafficCrossAlertStateR(frontTrafficCrossAlertStateR);
                    LogUtil.verbose(str, "ID_FRONT_TRAFFIC_CROSS_ALERT_STATE_R:" + this.u);
                    break;
                case 557856832:
                    int frontTrafficBrakeAlertStateR = dipilotEventChangeValue.getFrontTrafficBrakeAlertStateR();
                    this.t = frontTrafficBrakeAlertStateR;
                    this.d1.setmFrontTrafficBrakeAlertStateR(frontTrafficBrakeAlertStateR);
                    LogUtil.verbose(str, "ID_FRONT_TRAFFIC_BRAKE_ALERT_STATE_R:" + this.t);
                    break;
                case 557856835:
                    int steepDescentSlowDownStateR = dipilotEventChangeValue.getSteepDescentSlowDownStateR();
                    this.M = steepDescentSlowDownStateR;
                    this.d1.setmSteepDescentSlowDownStateR(steepDescentSlowDownStateR);
                    LogUtil.verbose(str, "ID_STEEP_DESCENT_SLOW_DOWN_STATE_R:" + this.M);
                    break;
                case 557856838:
                    int elecCarBodyStablySysStateChanged = dipilotEventChangeValue.getElecCarBodyStablySysStateChanged();
                    this.V = elecCarBodyStablySysStateChanged;
                    this.d1.setElecCarBodyStablySysStateChanged(elecCarBodyStablySysStateChanged);
                    LogUtil.verbose(str, "ID_ELEC_CARBODY_STABLY_SYS_STATE_CHANGED:" + this.V);
                    break;
                case 557856839:
                    int intelligentSpeedLimitControlStateFirst = dipilotEventChangeValue.getIntelligentSpeedLimitControlStateFirst();
                    this.y = intelligentSpeedLimitControlStateFirst;
                    this.d1.setIntelligentSpeedLimitControlStateFirst(intelligentSpeedLimitControlStateFirst);
                    LogUtil.verbose(str, "ID_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE_FIRST:" + this.y);
                    break;
                case 557856840:
                    int cpdStatusR = dipilotEventChangeValue.getCpdStatusR();
                    this.X = cpdStatusR;
                    this.d1.setCpdStatusR(cpdStatusR);
                    LogUtil.verbose(str, "ID_CPD_STATUS_R:" + this.X);
                    break;
                case 557856842:
                    int cpdDelayStatusR = dipilotEventChangeValue.getCpdDelayStatusR();
                    this.W = cpdDelayStatusR;
                    this.d1.setCpdDelayStatusR(cpdDelayStatusR);
                    LogUtil.verbose(str, "ID_CPD_DELAY_STATUS_R:" + this.W);
                    break;
                case 557856843:
                    int dmsSwitchStatusR = dipilotEventChangeValue.getDmsSwitchStatusR();
                    this.a0 = dmsSwitchStatusR;
                    this.d1.setDmsSwitchStatusR(dmsSwitchStatusR);
                    LogUtil.verbose(str, "ID_DMS_SWITCH_STATUS_R:" + this.a0);
                    break;
                case 557856845:
                    int adasSoftVersionR = dipilotEventChangeValue.getAdasSoftVersionR();
                    this.b0 = adasSoftVersionR;
                    this.d1.setmAdasSoftVersionR(adasSoftVersionR);
                    LogUtil.verbose(str, "ID_ADAS_SOFT_VERSION_R:" + this.b0);
                    break;
                case 557856846:
                    int tsrLitSpdChgSwitchStateR = dipilotEventChangeValue.getTsrLitSpdChgSwitchStateR();
                    this.c0 = tsrLitSpdChgSwitchStateR;
                    this.d1.setmTsrLitSpdChgSwitchStateR(tsrLitSpdChgSwitchStateR);
                    LogUtil.verbose(str, "ID_TSR_LIT_SPD_CHG_SWITCH_STATE_R:" + this.b0);
                    break;
                case 557856848:
                    int elkTakeOverStateR = dipilotEventChangeValue.getElkTakeOverStateR();
                    this.d0 = elkTakeOverStateR;
                    this.d1.setmElkTakeOverStateR(elkTakeOverStateR);
                    LogUtil.verbose(str, "ID_ELK_TAKE_OVER_STATE_R:" + this.d0);
                    break;
                case 557856849:
                    int tsrLitSpdChgInformStateR = dipilotEventChangeValue.getTsrLitSpdChgInformStateR();
                    this.e0 = tsrLitSpdChgInformStateR;
                    this.d1.setmTsrLitSpdChgInformStateR(tsrLitSpdChgInformStateR);
                    LogUtil.verbose(str, "ID_TSR_LIT_SPD_CHG_INFORM_STATE_R:" + this.e0);
                    break;
                case 557856850:
                    int isliWarningStateR = dipilotEventChangeValue.getIsliWarningStateR();
                    this.f0 = isliWarningStateR;
                    this.d1.setmIsliWarningStateR(isliWarningStateR);
                    LogUtil.verbose(str, "ID_ISLI_WARNING_STATE_R:" + this.f0);
                    break;
                case 557856851:
                    int lowSpeedEmergencyBrakingStateR = dipilotEventChangeValue.getLowSpeedEmergencyBrakingStateR();
                    this.F0 = lowSpeedEmergencyBrakingStateR;
                    this.d1.setLowSpeedEmergencyBrakingStateR(lowSpeedEmergencyBrakingStateR);
                    LogUtil.verbose(str, "ID_LOW_SPEED_EMERGENCY_BRAKING_STATER:" + this.F0);
                    break;
                case 557856853:
                    int sideDistanceWarningStateR = dipilotEventChangeValue.getSideDistanceWarningStateR();
                    this.G0 = sideDistanceWarningStateR;
                    this.d1.setSideDistanceWarningStateR(sideDistanceWarningStateR);
                    LogUtil.verbose(str, "ID_SIDE_DISTANCE_WARNING_STATER:" + this.G0);
                    break;
                case 557856855:
                    int collisionWarningStateR = dipilotEventChangeValue.getCollisionWarningStateR();
                    this.H0 = collisionWarningStateR;
                    this.d1.setCollisionWarningStateR(collisionWarningStateR);
                    LogUtil.verbose(str, "ID_COLLISION_WARNING_STATER:" + this.H0);
                    break;
                case 557856857:
                    int intersectionCollisionAlertStateR = dipilotEventChangeValue.getIntersectionCollisionAlertStateR();
                    this.I0 = intersectionCollisionAlertStateR;
                    this.d1.setIntersectionCollisionAlertStateR(intersectionCollisionAlertStateR);
                    LogUtil.verbose(str, "ID_INTERSECTION_COLLISION_ALERT_STATER:" + this.I0);
                    break;
                case 557856859:
                    int doNotPassWarningStateR = dipilotEventChangeValue.getDoNotPassWarningStateR();
                    this.J0 = doNotPassWarningStateR;
                    this.d1.setDoNotPassWarningStateR(doNotPassWarningStateR);
                    LogUtil.verbose(str, "ID_DO_NOT_PASS_WARNING_STATER:" + this.J0);
                    break;
                case 557856861:
                    int emergencyBrakeWarningStateR = dipilotEventChangeValue.getEmergencyBrakeWarningStateR();
                    this.K0 = emergencyBrakeWarningStateR;
                    this.d1.setEmergencyBrakeWarningStateR(emergencyBrakeWarningStateR);
                    LogUtil.verbose(str, "ID_EMERGENCY_BRAKE_WARNING_STATER:" + this.K0);
                    break;
                case 557856863:
                    int trafficIncidentWarningStateR = dipilotEventChangeValue.getTrafficIncidentWarningStateR();
                    this.L0 = trafficIncidentWarningStateR;
                    this.d1.setTrafficIncidentWarningStateR(trafficIncidentWarningStateR);
                    LogUtil.verbose(str, "ID_TRAFFIC_INCIDENT_WARNING_STATER:" + this.L0);
                    break;
                case 557856865:
                    int abnormalVehicleWarningStateR = dipilotEventChangeValue.getAbnormalVehicleWarningStateR();
                    this.M0 = abnormalVehicleWarningStateR;
                    this.d1.setAbnormalVehicleWarningStateR(abnormalVehicleWarningStateR);
                    LogUtil.verbose(str, "ID_ABNORMAL_VEHICLE_WARNING_STATER:" + this.M0);
                    break;
                case 557856867:
                    int controlLossWarningStateR = dipilotEventChangeValue.getControlLossWarningStateR();
                    this.N0 = controlLossWarningStateR;
                    this.d1.setControlLossWarningStateR(controlLossWarningStateR);
                    LogUtil.verbose(str, "ID_CONTROL_LOSS_WARNING_STATER:" + this.N0);
                    break;
                case 557856869:
                    int trafficJamWarningStateR = dipilotEventChangeValue.getTrafficJamWarningStateR();
                    this.O0 = trafficJamWarningStateR;
                    this.d1.setTrafficJamWarningStateR(trafficJamWarningStateR);
                    LogUtil.verbose(str, "ID_TRAFFIC_JAM_WARNING_STATER:" + this.O0);
                    break;
                case 557856871:
                    int emergencyVehicleWarningStateR = dipilotEventChangeValue.getEmergencyVehicleWarningStateR();
                    this.P0 = emergencyVehicleWarningStateR;
                    this.d1.setEmergencyVehicleWarningStateR(emergencyVehicleWarningStateR);
                    LogUtil.verbose(str, "ID_EMERGENCY_VEHICLE_WARNING_STATER:" + this.P0);
                    break;
                case 557856873:
                    int trafficSignalWarningStateR = dipilotEventChangeValue.getTrafficSignalWarningStateR();
                    this.Q0 = trafficSignalWarningStateR;
                    this.d1.setTrafficSignalWarningStateR(trafficSignalWarningStateR);
                    LogUtil.verbose(str, "ID_TRAFFIC_SIGNAL_WARNING_STATER:" + this.Q0);
                    break;
                case 557856875:
                    int trafficLightInfoPushStateR = dipilotEventChangeValue.getTrafficLightInfoPushStateR();
                    this.R0 = trafficLightInfoPushStateR;
                    this.d1.setTrafficLightInfoPushStateR(trafficLightInfoPushStateR);
                    LogUtil.verbose(str, "ID_TRAFFIC_LIGHT_INFO_PUSH_STATER:" + this.R0);
                    break;
                case 557856877:
                    int redLightViolationWarningStateR = dipilotEventChangeValue.getRedLightViolationWarningStateR();
                    this.S0 = redLightViolationWarningStateR;
                    this.d1.setRedLightViolationWarningStateR(redLightViolationWarningStateR);
                    LogUtil.verbose(str, "ID_RED_LIGHT_VIOLATION_WARNING_STATER:" + this.S0);
                    break;
                case 557856879:
                    int greenLightOptimalSpeedAdvisoryStateR = dipilotEventChangeValue.getGreenLightOptimalSpeedAdvisoryStateR();
                    this.T0 = greenLightOptimalSpeedAdvisoryStateR;
                    this.d1.setGreenLightOptimalSpeedAdvisoryStateR(greenLightOptimalSpeedAdvisoryStateR);
                    LogUtil.verbose(str, "ID_GREEN_LIGHT_OPTIMAL_SPEED_ADVISORY_STATER:" + this.T0);
                    break;
                case 557856884:
                    int mechanismPositionR = dipilotEventChangeValue.getMechanismPositionR();
                    this.U0 = mechanismPositionR;
                    this.d1.setMechanismPositionR(mechanismPositionR);
                    LogUtil.verbose(str, "ID_MECHANISM_POSITIONR:" + this.U0);
                    break;
                case 557856888:
                    int fatigueMonitorChangedR = dipilotEventChangeValue.getFatigueMonitorChangedR();
                    this.d1.setFatigueMonitorChangedR(fatigueMonitorChangedR);
                    LogUtil.verbose(str, "ID_FATIGUE_MONITOR_CHANGED_R:" + fatigueMonitorChangedR);
                    break;
                case 557856889:
                    int bsisEnableR = dipilotEventChangeValue.getBsisEnableR();
                    this.d1.setBsisEnableR(bsisEnableR);
                    LogUtil.verbose(str, "ID_BSIS_ENABLE_R:" + bsisEnableR);
                    break;
                case 557856891:
                    int bsisFuncStatusR = dipilotEventChangeValue.getBsisFuncStatusR();
                    this.d1.setBsisFuncStatusR(bsisFuncStatusR);
                    LogUtil.verbose(str, "ID_BSIS_FUNC_STATUS_R:" + bsisFuncStatusR);
                    break;
                case 557856892:
                    int moisEnableR = dipilotEventChangeValue.getMoisEnableR();
                    this.d1.setMoisEnableR(moisEnableR);
                    LogUtil.verbose(str, "ID_MOIS_ENABLE_R:" + moisEnableR);
                    break;
                case 557856894:
                    int moisFuncStateR = dipilotEventChangeValue.getMoisFuncStateR();
                    this.d1.setMoisFuncStateR(moisFuncStateR);
                    LogUtil.verbose(str, "ID_MOIS_FUNC_STATUS_R:" + moisFuncStateR);
                    break;
                case 557856896:
                    int cpdStatusNewR = dipilotEventChangeValue.getCpdStatusNewR();
                    this.Y = cpdStatusNewR;
                    this.d1.setCpdStatusNewR(cpdStatusNewR);
                    LogUtil.verbose(str, "ID_CPD_STATUS_NEW_R:" + this.Y);
                    break;
                case 557856897:
                    int adativeCruiseControlFaultR = dipilotEventChangeValue.getAdativeCruiseControlFaultR();
                    this.d1.setAdativeCruiseControlFaultR(adativeCruiseControlFaultR);
                    LogUtil.verbose(str, "ID_ADATIVE_CRUISE_CONTROL_FAULT_R:" + adativeCruiseControlFaultR);
                    break;
                case 557856898:
                    int laneCenteringControlFaultR = dipilotEventChangeValue.getLaneCenteringControlFaultR();
                    this.d1.setLaneCenteringControlFaultR(laneCenteringControlFaultR);
                    LogUtil.verbose(str, "ID_LANE_CENTERING_CONTROL_FAULT_R:" + laneCenteringControlFaultR);
                    break;
                case 557856899:
                    int blindFaultR = dipilotEventChangeValue.getBlindFaultR();
                    this.d1.setBlindFaultR(blindFaultR);
                    LogUtil.verbose(str, "ID_BLIND_FAULT_R:" + blindFaultR);
                    break;
                case 557856900:
                    int doorOpenFaultR = dipilotEventChangeValue.getDoorOpenFaultR();
                    this.d1.setDoorOpenFaultR(doorOpenFaultR);
                    LogUtil.verbose(str, "ID_DOOR_OPEN_FAULT_R:" + doorOpenFaultR);
                    break;
                case 557856901:
                    int rearCrossAlartFaultR = dipilotEventChangeValue.getRearCrossAlartFaultR();
                    this.d1.setRearCrossAlartFaultR(rearCrossAlartFaultR);
                    LogUtil.verbose(str, "ID_REAR_CROSS_ALART_FAULT_R:" + rearCrossAlartFaultR);
                    break;
                case 557856902:
                    int rearCrossBrakeFaultR = dipilotEventChangeValue.getRearCrossBrakeFaultR();
                    this.d1.setRearCrossBrakeFaultR(rearCrossBrakeFaultR);
                    LogUtil.verbose(str, "ID_REAR_CROSS_BRAKE_FAULT_R:" + rearCrossBrakeFaultR);
                    break;
                case 557856903:
                    int rearCollisionWarnFaultR = dipilotEventChangeValue.getRearCollisionWarnFaultR();
                    this.d1.setRearCollisionWarnFaultR(rearCollisionWarnFaultR);
                    LogUtil.verbose(str, "ID_REAR_COLLISION_WARN_FAULT_R:" + rearCollisionWarnFaultR);
                    break;
                case 557856904:
                    int frontCrossAlarkFaultR = dipilotEventChangeValue.getFrontCrossAlarkFaultR();
                    this.d1.setFrontCrossAlarkFaultR(frontCrossAlarkFaultR);
                    LogUtil.verbose(str, "ID_FRONT_CROSS_ALARK_FAULT_R:" + frontCrossAlarkFaultR);
                    break;
                case 557856905:
                    int frontCrossBrakeFaultR = dipilotEventChangeValue.getFrontCrossBrakeFaultR();
                    this.d1.setFrontCrossBrakeFaultR(frontCrossBrakeFaultR);
                    LogUtil.verbose(str, "ID_FRONT_CROSS_BRAKE_FAULT_R:" + frontCrossBrakeFaultR);
                    break;
                case 557856906:
                    int interactiveLaneChangeFaultR = dipilotEventChangeValue.getInteractiveLaneChangeFaultR();
                    this.d1.setInteractiveLaneChangeFaultR(interactiveLaneChangeFaultR);
                    LogUtil.verbose(str, "ID_INTERACTIVE_LANE_CHANGE_FAULT_R:" + interactiveLaneChangeFaultR);
                    break;
                case 557856907:
                    int nrbanNaviFaultR = dipilotEventChangeValue.getNrbanNaviFaultR();
                    this.d1.setNrbanNaviFaultR(nrbanNaviFaultR);
                    LogUtil.verbose(str, "ID_NRBAN_NAVI_FAULT_R:" + nrbanNaviFaultR);
                    break;
                case 557856908:
                    int highNaviFaultR = dipilotEventChangeValue.getHighNaviFaultR();
                    this.d1.setHighNaviFaultR(highNaviFaultR);
                    LogUtil.verbose(str, "ID_HIGH_NAVI_FAULT_R:" + highNaviFaultR);
                    break;
                case 557856909:
                    int navigationPilotFaultR = dipilotEventChangeValue.getNavigationPilotFaultR();
                    this.d1.setNavigationPilotFaultR(navigationPilotFaultR);
                    LogUtil.verbose(str, "ID_NAVIGATION_PILOT_FAULT_R:" + navigationPilotFaultR);
                    break;
                case 557856910:
                    int autoEmergencyFaultR = dipilotEventChangeValue.getAutoEmergencyFaultR();
                    this.d1.setAutoEmergencyFaultR(autoEmergencyFaultR);
                    LogUtil.verbose(str, "ID_AUTO_EMERGENCY_FAULT_R:" + autoEmergencyFaultR);
                    break;
                case 557856911:
                    int emergencyLaneKeepFaultR = dipilotEventChangeValue.getEmergencyLaneKeepFaultR();
                    this.d1.setEmergencyLaneKeepFaultR(emergencyLaneKeepFaultR);
                    LogUtil.verbose(str, "ID_EMERGENCY_LANE_KEEP_FAULT_R:" + emergencyLaneKeepFaultR);
                    break;
                case 557856912:
                    int intelligentLimitFaultControllerR = dipilotEventChangeValue.getIntelligentLimitFaultControllerR();
                    this.d1.setIntelligentLimitFaultControllerR(intelligentLimitFaultControllerR);
                    LogUtil.verbose(str, "ID_INTELLIGENT_LIMIT_FAULT_CONTROLLER_R:" + intelligentLimitFaultControllerR);
                    break;
                case 557856913:
                    int laneDeparturePreventionFaultR = dipilotEventChangeValue.getLaneDeparturePreventionFaultR();
                    this.d1.setLaneDeparturePreventionFaultR(laneDeparturePreventionFaultR);
                    LogUtil.verbose(str, "ID_LANE_DEPARTURE_PREVENTION_FAULT_R:" + laneDeparturePreventionFaultR);
                    break;
                case 557856914:
                    int laneDepartureWarnFaultR = dipilotEventChangeValue.getLaneDepartureWarnFaultR();
                    this.d1.setLaneDepartureWarnFaultR(laneDepartureWarnFaultR);
                    LogUtil.verbose(str, "ID_LANE_DEPARTURE_WARN_FAULT_R:" + laneDepartureWarnFaultR);
                    break;
                case 557856915:
                    int laneKeepFaultR = dipilotEventChangeValue.getLaneKeepFaultR();
                    this.d1.setLaneKeepFaultR(laneKeepFaultR);
                    LogUtil.verbose(str, "ID_LANE_KEEP_FAULT_R:" + laneKeepFaultR);
                    break;
                case 557856916:
                    int forwardCollisionWarnFaultR = dipilotEventChangeValue.getForwardCollisionWarnFaultR();
                    this.d1.setForwardCollisionWarnFaultR(forwardCollisionWarnFaultR);
                    LogUtil.verbose(str, "ID_FORWARD_COLLISION_WARN_FAULT_R:" + forwardCollisionWarnFaultR);
                    break;
                case 557856917:
                    int adaptiveFrontLightFaultR = dipilotEventChangeValue.getAdaptiveFrontLightFaultR();
                    this.d1.setAdaptiveFrontLightFaultR(adaptiveFrontLightFaultR);
                    LogUtil.verbose(str, "ID_ADAPTIVE_FRONT_LIGHT_FAULT_R:" + adaptiveFrontLightFaultR);
                    break;
                case 557856918:
                    int trafficAttentionFaultR = dipilotEventChangeValue.getTrafficAttentionFaultR();
                    this.d1.setTrafficAttentionFaultR(trafficAttentionFaultR);
                    LogUtil.verbose(str, "ID_TRAFFIC_ATTENTION_FAULT_R:" + trafficAttentionFaultR);
                    break;
                case 557856919:
                    int tsrFaultR = dipilotEventChangeValue.getTsrFaultR();
                    this.d1.setTsrFaultR(tsrFaultR);
                    LogUtil.verbose(str, "ID_TSR_FAULT_R:" + tsrFaultR);
                    break;
                case 557856920:
                    int intelligentLimitFaultCallR = dipilotEventChangeValue.getIntelligentLimitFaultCallR();
                    this.d1.setIntelligentLimitFaultCallR(intelligentLimitFaultCallR);
                    LogUtil.verbose(str, "ID_INTELLIGENT_LIMIT_FAULT_CALL_R:" + intelligentLimitFaultCallR);
                    break;
                case 557856921:
                    int limitSpeedChangeFaultR = dipilotEventChangeValue.getLimitSpeedChangeFaultR();
                    this.d1.setLimitSpeedChangeFaultR(limitSpeedChangeFaultR);
                    LogUtil.verbose(str, "ID_LIMIT_SPEED_CHANGE_FAULT_R:" + limitSpeedChangeFaultR);
                    break;
                case 557856922:
                    int curveWarnFaultR = dipilotEventChangeValue.getCurveWarnFaultR();
                    this.d1.setCurveWarnFaultR(curveWarnFaultR);
                    LogUtil.verbose(str, "ID_CURVE_WARN_FAULT_R:" + curveWarnFaultR);
                    break;
                case 557856923:
                    int bsisFaultR = dipilotEventChangeValue.getBsisFaultR();
                    this.d1.setBsisFaultR(bsisFaultR);
                    LogUtil.verbose(str, "ID_BSIS_FAULT_R:" + bsisFaultR);
                    break;
                case 557856924:
                    int moisFaultR = dipilotEventChangeValue.getMoisFaultR();
                    this.d1.setMoisFaultR(moisFaultR);
                    LogUtil.verbose(str, "ID_MOIS_FAULT_R:" + moisFaultR);
                    break;
                case 557856929:
                    int isliWarningLevelSwitchStatusR = dipilotEventChangeValue.getIsliWarningLevelSwitchStatusR();
                    this.d1.setIsliWarningLevelSwitchStatusR(isliWarningLevelSwitchStatusR);
                    LogUtil.verbose(str, "ID_I_S_L_I_WARNING_LEVEL_SWITCH_STATUS_R:" + isliWarningLevelSwitchStatusR);
                    break;
                case 557856931:
                    int autoParkingStateR = dipilotEventChangeValue.getAutoParkingStateR();
                    this.d1.setAutoParkingStateR(autoParkingStateR);
                    LogUtil.verbose(str, "ID_AUTO_PARKING_STATE_R:" + autoParkingStateR);
                    break;
                case 557856933:
                    int cpdWarningStateR = dipilotEventChangeValue.getCpdWarningStateR();
                    this.d1.setCpdWarningStateR(cpdWarningStateR);
                    LogUtil.verbose(str, "ID_CPD_WARNING_STATE_R:" + cpdWarningStateR);
                    break;
                case 557856934:
                    int cpdWorkingStatusR = dipilotEventChangeValue.getCpdWorkingStatusR();
                    this.d1.setCpdWorkingStatusR(cpdWorkingStatusR);
                    LogUtil.verbose(str, "ID_CPD_WORKING_STATUS_R:" + cpdWorkingStatusR);
                    break;
                case 557856935:
                    int espOffKeyStatusR = dipilotEventChangeValue.getEspOffKeyStatusR();
                    this.d1.setEspOffKeyStatusR(espOffKeyStatusR);
                    LogUtil.verbose(str, "ID_ESP_OFF_KEY_STATUS_R:" + espOffKeyStatusR);
                    break;
                case 557856936:
                    int iOPStateR = dipilotEventChangeValue.getIOPStateR();
                    this.d1.setIopStateR(iOPStateR);
                    LogUtil.debug(str, "ID_I_O_P_STATE_R:" + iOPStateR);
                    break;
                case 557856938:
                    int sAOCStateR = dipilotEventChangeValue.getSAOCStateR();
                    this.d1.setSaocStateR(sAOCStateR);
                    LogUtil.debug(str, "ID_S_A_O_C_STATE_R:" + sAOCStateR);
                    break;
                case 557856940:
                    int sLIFStateR = dipilotEventChangeValue.getSLIFStateR();
                    this.d1.setSlifStateR(sLIFStateR);
                    LogUtil.debug(str, "ID_S_L_I_F_STATE_R:" + sLIFStateR);
                    break;
                case 557856944:
                    int lowIOPToastR = dipilotEventChangeValue.getLowIOPToastR();
                    this.d1.setLowIopToastR(lowIOPToastR);
                    LogUtil.debug(str, "ID_LOW_I_O_P_TOAST_CHANGED_R:" + lowIOPToastR);
                    break;
                case 557856945:
                    int highIOPToastR = dipilotEventChangeValue.getHighIOPToastR();
                    this.d1.setHighIopToastR(highIOPToastR);
                    LogUtil.debug(str, "ID_HIGH_I_O_P_TOAST_CHANGED_R:" + highIOPToastR);
                    break;
                case 557856946:
                    int accStateR = dipilotEventChangeValue.getAccStateR();
                    this.d1.setAccStateR(accStateR);
                    LogUtil.debug(str, "ID_A_C_C_STATE_R:" + accStateR);
                    break;
                case 557856948:
                    int fcsrStateR = dipilotEventChangeValue.getFcsrStateR();
                    this.d1.setFcsrStateR(fcsrStateR);
                    LogUtil.debug(str, "ID_+F_C_S_R_STATE_R:" + fcsrStateR);
                    break;
                case 557856950:
                    int fcsrFaultR = dipilotEventChangeValue.getFcsrFaultR();
                    this.d1.setFcsrFaultR(fcsrFaultR);
                    LogUtil.debug(str, "ID_+F_C_S_R_FAULT_R:" + fcsrFaultR);
                    break;
                case 557870336:
                    int drivingVoiceBroadcastSwitchConfigTwoR = dipilotEventChangeValue.getDrivingVoiceBroadcastSwitchConfigTwoR();
                    this.z0 = drivingVoiceBroadcastSwitchConfigTwoR;
                    this.d1.setDrivingVoiceBroadcastSwitchConfigTwoR(drivingVoiceBroadcastSwitchConfigTwoR);
                    LogUtil.verbose(str, "ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_TWOR:" + this.z0);
                    break;
                case 557870400:
                    int highHODToastR = dipilotEventChangeValue.getHighHODToastR();
                    this.d1.setHighHodToastR(highHODToastR);
                    LogUtil.debug(str, "ID_HIGH_H_O_D_TOAST_CHANGED_R:" + highHODToastR);
                    break;
                case 557870413:
                    int aebDecActiveR = dipilotEventChangeValue.getAebDecActiveR();
                    this.d1.setAebDecActiveR(aebDecActiveR);
                    LogUtil.verbose(str, "ID_A_E_B_DEC_ACTIVE_R:" + aebDecActiveR);
                    break;
                case 557870415:
                    int lksFaultStateR = dipilotEventChangeValue.getLksFaultStateR();
                    this.A0 = lksFaultStateR;
                    this.d1.setLksFaultStateR(lksFaultStateR);
                    LogUtil.verbose(str, "ID_LKS_FAULT_STATER:" + this.A0);
                    break;
                case 557870416:
                    int ldwFaultStateR = dipilotEventChangeValue.getLdwFaultStateR();
                    this.B0 = ldwFaultStateR;
                    this.d1.setLdwFaultStateR(ldwFaultStateR);
                    LogUtil.verbose(str, "ID_LDW_FAULT_STATER:" + this.B0);
                    break;
                case 557870437:
                    int dnpStatusR = dipilotEventChangeValue.getDnpStatusR();
                    this.V0 = dnpStatusR;
                    this.d1.setDnpStatusR(dnpStatusR);
                    LogUtil.verbose(str, "ID_DNP_STATUSR:" + this.V0);
                    break;
                case 557870469:
                    int aebFaultStateR = dipilotEventChangeValue.getAebFaultStateR();
                    this.C0 = aebFaultStateR;
                    this.d1.setAebFaultStateR(aebFaultStateR);
                    LogUtil.verbose(str, "ID_AEB_FAULT_STATER:" + this.C0);
                    break;
                case 557870470:
                    int fcwFaultStateR = dipilotEventChangeValue.getFcwFaultStateR();
                    this.D0 = fcwFaultStateR;
                    this.d1.setFcwFaultStateR(fcwFaultStateR);
                    LogUtil.verbose(str, "ID_FCW_FAULT_STATER:" + this.D0);
                    break;
                case 557870471:
                    int vruFaultStateR = dipilotEventChangeValue.getVruFaultStateR();
                    this.E0 = vruFaultStateR;
                    this.d1.setVruFaultStateR(vruFaultStateR);
                    LogUtil.verbose(str, "ID_VRU_FAULT_STATER:" + this.E0);
                    break;
                case 557870472:
                    int aiDistanceLightStateFirst = dipilotEventChangeValue.getAiDistanceLightStateFirst();
                    this.i = aiDistanceLightStateFirst;
                    this.d1.setmAiDistanceLightStateFirst(aiDistanceLightStateFirst);
                    LogUtil.verbose(str, "ID_AI_DISTANCE_LIGHT_STATER_FIRST:" + this.i);
                    break;
                case 557870505:
                    int trafficLightRecognizeStateSecond = dipilotEventChangeValue.getTrafficLightRecognizeStateSecond();
                    this.O = trafficLightRecognizeStateSecond;
                    this.d1.setmTrafficLightRecognizeStateSecond(trafficLightRecognizeStateSecond);
                    LogUtil.verbose(str, "ID_TRAFFIC_LIGHT_RECOGNIZE_STATE_SECOND:" + this.O);
                    break;
                case 557872897:
                    int ldwSensitivityR = dipilotEventChangeValue.getLdwSensitivityR();
                    this.Z = ldwSensitivityR;
                    this.d1.setLdwSensitivityR(ldwSensitivityR);
                    LogUtil.verbose(str, "ID_L_D_W_SENSITIVITY_R:" + this.Z);
                    break;
                default:
                    return;
            }
            this.a1.m(i, i2, this.d1);
            LogUtil.verbose(str, "notifyDipilotEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void z(CarVendorDipilotManager carVendorDipilotManager, CarVendorDigitalkeyManager carVendorDigitalkeyManager, CarVendorCarmodeManager carVendorCarmodeManager, CarVendorXcallManager carVendorXcallManager) {
        this.f62a = carVendorDipilotManager;
        this.f63b = carVendorDigitalkeyManager;
        this.c = carVendorCarmodeManager;
        this.d = carVendorXcallManager;
    }
}
