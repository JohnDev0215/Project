package com.ts.appservice.caradapter.c;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.appservice.caradapter.d.m;
import com.ts.appservice.caradapter.d.q;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager;
import com.ts.lib.car.hardware.fragrance.FragranceEventChangeValue;
import com.ts.lib.car.hardware.hvac.CarVendorHvacManager;
import com.ts.lib.car.hardware.hvac.HvacEventChangeValue;
import com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager;
import com.ts.lib.car.hardware.pm2p5.Pm2p5EventChangeValue;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.hvac.IHvacAdapterCallback;
import com.ts.lib.caradapter.hvac.IHvacAdapterService;
import java.util.Arrays;

/* JADX INFO: compiled from: HvacServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends IHvacAdapterService.Stub implements m, com.ts.appservice.caradapter.d.j, q {
    private static final String G = k.class.getSimpleName();
    private int A;
    private int B;
    private HvacPacket C = new HvacPacket();
    private boolean D = false;
    private final HandlerThread E = com.ts.appservice.caradapter.e.b.a("VEHICLE-HVAC");
    private a F;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CarVendorHvacManager f77a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CarVendorPm2p5Manager f78b;
    private CarVendorFragranceManager c;
    private com.ts.appservice.caradapter.a.h d;
    private int e;
    private int f;
    private int g;
    private int h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    /* JADX INFO: compiled from: HvacServiceImpl.java */
    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                k.this.q(message.arg1, message.arg2, (HvacEventChangeValue) message.obj);
            } else if (i == 4) {
                k.this.p(message.arg1, message.arg2, (FragranceEventChangeValue) message.obj);
            } else {
                if (i != 5) {
                    return;
                }
                k.this.r(message.arg1, message.arg2, (Pm2p5EventChangeValue) message.obj);
            }
        }
    }

    public k(Context context) {
        this.d = new com.ts.appservice.caradapter.a.h(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(int i, int i2, FragranceEventChangeValue fragranceEventChangeValue) {
        String str = G;
        try {
            switch (i) {
                case 557856028:
                    int supportFragranceTypeOne = fragranceEventChangeValue.getSupportFragranceTypeOne();
                    this.C.setSupportFragranceTypeOne(supportFragranceTypeOne);
                    LogUtil.verbose(str, "ID_SUPPORT_FRAGRANCE_TYPE_ONE-" + supportFragranceTypeOne);
                    break;
                case 557856029:
                    int supportFragranceTypeTwo = fragranceEventChangeValue.getSupportFragranceTypeTwo();
                    this.C.setSupportFragranceTypeTwo(supportFragranceTypeTwo);
                    LogUtil.verbose(str, "ID_SUPPORT_FRAGRANCE_TYPE_TWO-" + supportFragranceTypeTwo);
                    break;
                case 557856030:
                    int supportFragranceTypeThree = fragranceEventChangeValue.getSupportFragranceTypeThree();
                    this.C.setSupportFragranceTypeThree(supportFragranceTypeThree);
                    LogUtil.verbose(str, "ID_SUPPORT_FRAGRANCE_TYPE_THREE-" + supportFragranceTypeThree);
                    break;
                case 557858816:
                    int curFragranceType = fragranceEventChangeValue.getCurFragranceType();
                    this.C.setCurFragranceType(curFragranceType);
                    LogUtil.verbose(str, "ID_CUR_FRAGRANCE_TYPE-" + curFragranceType);
                    break;
                case 557858818:
                    int fragranceResidueOne = fragranceEventChangeValue.getFragranceResidueOne();
                    this.C.setFragranceResidueOne(fragranceResidueOne);
                    LogUtil.verbose(str, "ID_FRAGRANCE_RESIDUE_ONE-" + fragranceResidueOne);
                    break;
                case 557858819:
                    int fragranceConcentrationR = fragranceEventChangeValue.getFragranceConcentrationR();
                    this.C.setFragranceConcentrationR(fragranceConcentrationR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_CONCENTRATIONR-" + fragranceConcentrationR);
                    break;
                case 557858821:
                    int fragranceResidueTwo = fragranceEventChangeValue.getFragranceResidueTwo();
                    this.C.setFragranceResidueTwo(fragranceResidueTwo);
                    LogUtil.verbose(str, "ID_FRAGRANCE_RESIDUE_TWO-" + fragranceResidueTwo);
                    break;
                case 557858822:
                    int fragranceResidueThree = fragranceEventChangeValue.getFragranceResidueThree();
                    this.C.setFragranceResidueThree(fragranceResidueThree);
                    LogUtil.verbose(str, "ID_FRAGRANCE_RESIDUE_THREE-" + fragranceResidueThree);
                    break;
                case 557858823:
                    int fragranceLessAllowance = fragranceEventChangeValue.getFragranceLessAllowance();
                    this.C.setFragranceLessAllowance(fragranceLessAllowance);
                    LogUtil.verbose(str, "ID_FRAGRANCE_LESS_ALLOWANCE-" + fragranceLessAllowance);
                    break;
                case 557858825:
                    int fragranceNameOneAbR = fragranceEventChangeValue.getFragranceNameOneAbR();
                    this.C.setFragranceNameOneAbR(fragranceNameOneAbR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_NAME_ONE_ABR-" + fragranceNameOneAbR);
                    break;
                case 557858827:
                    int fragranceNameOneBbR = fragranceEventChangeValue.getFragranceNameOneBbR();
                    this.C.setFragranceNameOneBbR(fragranceNameOneBbR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_NAME_ONE_BBR-" + fragranceNameOneBbR);
                    break;
                case 557858833:
                    int fragranceNameTwoAbR = fragranceEventChangeValue.getFragranceNameTwoAbR();
                    this.C.setFragranceNameTwoAbR(fragranceNameTwoAbR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_NAME_TWO_ABR-" + fragranceNameTwoAbR);
                    break;
                case 557858835:
                    int fragranceNameTwoBbR = fragranceEventChangeValue.getFragranceNameTwoBbR();
                    this.C.setFragranceNameTwoBbR(fragranceNameTwoBbR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_NAME_TWO_BBR-" + fragranceNameTwoBbR);
                    break;
                case 557858841:
                    int fragranceNameThreeAbR = fragranceEventChangeValue.getFragranceNameThreeAbR();
                    this.C.setFragranceNameThreeAbR(fragranceNameThreeAbR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_NAME_THREE_ABR-" + fragranceNameThreeAbR);
                    break;
                case 557858843:
                    int fragranceNameThreeBbR = fragranceEventChangeValue.getFragranceNameThreeBbR();
                    this.C.setFragranceNameThreeBbR(fragranceNameThreeBbR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_NAME_THREE_BBR-" + fragranceNameThreeBbR);
                    break;
                case 557858848:
                    int fragranceLessAllowanceTwoR = fragranceEventChangeValue.getFragranceLessAllowanceTwoR();
                    this.C.setFragranceLessAllowanceTwoR(fragranceLessAllowanceTwoR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_LESS_ALLOWANCE_TWO_R:" + fragranceLessAllowanceTwoR);
                    break;
                case 557858849:
                    int fragranceLessAllowanceThreeR = fragranceEventChangeValue.getFragranceLessAllowanceThreeR();
                    this.C.setFragranceLessAllowanceThreeR(fragranceLessAllowanceThreeR);
                    LogUtil.verbose(str, "ID_FRAGRANCE_LESS_ALLOWANCE_THREE_R:" + fragranceLessAllowanceThreeR);
                    break;
                default:
                    return;
            }
            this.d.m(i, i2, this.C);
            LogUtil.verbose(str, "notifyTimeoutEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(int i, int i2, HvacEventChangeValue hvacEventChangeValue) {
        String str = G;
        try {
            switch (i) {
                case 557846528:
                    int temperatureUnit = hvacEventChangeValue.getTemperatureUnit();
                    this.C.setTemperatureUnit(temperatureUnit);
                    LogUtil.verbose(str, "ID_TEMPERATURE_UNIT:" + temperatureUnit);
                    break;
                case 557846529:
                    int acType = hvacEventChangeValue.getAcType();
                    this.C.setAcType(acType);
                    LogUtil.verbose(str, "ID_AC_TYPE:" + acType);
                    break;
                case 557846530:
                    int rearAcDet = hvacEventChangeValue.getRearAcDet();
                    this.C.setRearAcDet(rearAcDet);
                    LogUtil.verbose(str, "ID_SET_CAR_REAR_AC_DET:" + rearAcDet);
                    break;
                case 557846531:
                    int acRearAcButton = hvacEventChangeValue.getAcRearAcButton();
                    this.C.setAcRearAcButton(acRearAcButton);
                    LogUtil.verbose(str, "ID_AC_REAR_AC_BUTTON:" + acRearAcButton);
                    break;
                case 557846532:
                    int acRearAcWindLevel = hvacEventChangeValue.getAcRearAcWindLevel();
                    this.C.setAcRearAcWindLevel(acRearAcWindLevel);
                    LogUtil.verbose(str, "ID_AC_REAR_WIND_LEVEL:" + acRearAcWindLevel);
                    break;
                case 557846533:
                    int acRearButtonClick = hvacEventChangeValue.getAcRearButtonClick();
                    this.C.setAcRearButtonClick(acRearButtonClick);
                    LogUtil.verbose(str, "ID_AC_REAR_BUTTON_LOCK:" + acRearButtonClick);
                    break;
                case 557846534:
                    int acRearWindModeAdjust = hvacEventChangeValue.getAcRearWindModeAdjust();
                    this.C.setAcRearWindModeAdjust(acRearWindModeAdjust);
                    LogUtil.verbose(str, "ID_AC_REAR_WIND_MODE_ADJUST:" + acRearWindModeAdjust);
                    break;
                case 557846535:
                    int acRearWindAutoAdjust = hvacEventChangeValue.getAcRearWindAutoAdjust();
                    this.C.setAcRearWindAutoAdjust(hvacEventChangeValue.getAcRearWindAutoAdjust());
                    LogUtil.verbose(str, "ID_AC_REAR_WIND_AUTO_ADJUST:" + acRearWindAutoAdjust);
                    break;
                case 557846536:
                    int acRearAcFunction = hvacEventChangeValue.getAcRearAcFunction();
                    this.C.setAcRearAcFunction(acRearAcFunction);
                    LogUtil.verbose(str, "ID_AC_REAR_AC_FUNCTION:" + acRearAcFunction);
                    break;
                case 557846537:
                    int acRearAcTempAdjust = hvacEventChangeValue.getAcRearAcTempAdjust();
                    this.C.setAcRearAcTempAdjust(acRearAcTempAdjust);
                    LogUtil.verbose(str, "ID_AC_REAR_AC_TEMP_ADJUST:" + acRearAcTempAdjust);
                    break;
                case 557846538:
                    int acRearWindLevelAdjust = hvacEventChangeValue.getAcRearWindLevelAdjust();
                    this.C.setAcRearWindLevelAdjust(acRearWindLevelAdjust);
                    LogUtil.verbose(str, "ID_AC_REAR_WIND_LEVEL_ADJUST:" + acRearWindLevelAdjust);
                    break;
                case 557846539:
                    int acDualChannelPm25Flag = hvacEventChangeValue.getAcDualChannelPm25Flag();
                    this.C.setAcDualChannelPm25Flag(acDualChannelPm25Flag);
                    LogUtil.verbose(str, "ID_AC_DUAL_CHANNEL_PM25_FLAG:" + acDualChannelPm25Flag);
                    break;
                default:
                    switch (i) {
                        case 557846541:
                            int acHighTempSterilizationFlag = hvacEventChangeValue.getAcHighTempSterilizationFlag();
                            this.C.setAcHighTempSterilizationFlag(acHighTempSterilizationFlag);
                            LogUtil.verbose(str, "ID_AC_HIGH_TEMP_STERILIZATION_FALG:" + acHighTempSterilizationFlag);
                            break;
                        case 557846542:
                            int acFaultNumShownState = hvacEventChangeValue.getAcFaultNumShownState();
                            this.C.setAcFaultNumShownState(acFaultNumShownState);
                            LogUtil.verbose(str, "ID_AC_FAULT_NUM_SHOWN_STATE:" + acFaultNumShownState);
                            break;
                        case 557846543:
                            int acTempCtrlSeparateState = hvacEventChangeValue.getAcTempCtrlSeparateState();
                            this.C.setAcTempCtrlSeparateState(acTempCtrlSeparateState);
                            LogUtil.verbose(str, "ID_AC_TEMPCTRL_SEPARATE_STATE:" + acTempCtrlSeparateState);
                            break;
                        default:
                            switch (i) {
                                case 557846545:
                                    int acHasPerfumeConfiguration = hvacEventChangeValue.getAcHasPerfumeConfiguration();
                                    this.C.setAcHasPerfumeConfiguration(acHasPerfumeConfiguration);
                                    LogUtil.verbose(str, "ID_AC_HAS_PERFUME_CONFIGURATION:" + acHasPerfumeConfiguration);
                                    break;
                                case 557846546:
                                    int acAutomaticButtonTurnOff = hvacEventChangeValue.getAcAutomaticButtonTurnOff();
                                    this.C.setAcAutomaticButtonTurnOff(acAutomaticButtonTurnOff);
                                    LogUtil.verbose(str, "ID_AC_AUTOMATIC_BUTTON_TURNED_OFF:" + acAutomaticButtonTurnOff);
                                    break;
                                case 557846547:
                                    int acCtrlModeR = hvacEventChangeValue.getAcCtrlModeR();
                                    this.C.setAcCtrlMode(acCtrlModeR);
                                    LogUtil.verbose(str, "ID_AC_REAR_CTRL_MODE:" + acCtrlModeR);
                                    break;
                                case 557846548:
                                    int acCtrlModeS = hvacEventChangeValue.getAcCtrlModeS();
                                    this.C.setAcCtrlMode(acCtrlModeS);
                                    LogUtil.verbose(str, "ID_AC_FRONT_CTRL_MODE:" + acCtrlModeS);
                                    break;
                                case 557846549:
                                    int acDefrostR = hvacEventChangeValue.getAcDefrostR();
                                    this.C.setAcDefrost(acDefrostR);
                                    LogUtil.verbose(str, "ID_AC_DEFROST:" + acDefrostR);
                                    break;
                                case 557846550:
                                    int acDefrostS = hvacEventChangeValue.getAcDefrostS();
                                    this.C.setAcDefrost(acDefrostS);
                                    LogUtil.verbose(str, "ID_AC_DEFROST_FRONT_STATE:" + acDefrostS);
                                    break;
                                case 557846551:
                                    int acCloseKey = hvacEventChangeValue.getAcCloseKey();
                                    this.C.setAcCloseKey(acCloseKey);
                                    LogUtil.verbose(str, "ID_AC_CLOSE_BUTTON:" + acCloseKey);
                                    break;
                                case 557846552:
                                    int acOpenState = hvacEventChangeValue.getAcOpenState();
                                    this.C.setAcOpenState(acOpenState);
                                    LogUtil.verbose(str, "ID_AC_OPEN_STATE:" + acOpenState);
                                    break;
                                case 557846553:
                                    int acCircleMode = hvacEventChangeValue.getAcCircleMode();
                                    this.C.setAcCircleMode(acCircleMode);
                                    LogUtil.verbose(str, "ID_AC_CIRCLE_MODE:" + acCircleMode);
                                    break;
                                case 557846554:
                                    int acCircleKey = hvacEventChangeValue.getAcCircleKey();
                                    this.C.setAcCircleKey(acCircleKey);
                                    LogUtil.verbose(str, "ID_AC_CIRCLE_BUTTON:" + acCircleKey);
                                    break;
                                case 557846555:
                                    int tempratureOut = hvacEventChangeValue.getTempratureOut();
                                    this.C.setTempratureOut(tempratureOut);
                                    LogUtil.verbose(str, "ID_AC_TEMPRATURE_OUT:" + tempratureOut);
                                    break;
                                case 557846556:
                                    int acTurnOn = hvacEventChangeValue.getAcTurnOn();
                                    this.C.setAcTurnOn(hvacEventChangeValue.getAcTurnOn());
                                    LogUtil.verbose(str, "ID_AC_TURN_ON:" + acTurnOn);
                                    break;
                                case 557846557:
                                    int acHeatModeFlag = hvacEventChangeValue.getAcHeatModeFlag();
                                    this.C.setAcHeatModeFlag(acHeatModeFlag);
                                    LogUtil.verbose(str, "ID_AC_HEAT_MODE_FLAG:" + acHeatModeFlag);
                                    break;
                                case 557846558:
                                    int acHeatPumpCompressorMode = hvacEventChangeValue.getAcHeatPumpCompressorMode();
                                    this.C.setAcHeatPumpCompressorMode(acHeatPumpCompressorMode);
                                    LogUtil.verbose(str, "ID_AC_HEAT_PUMP_COMPRESSOR_MODE:" + acHeatPumpCompressorMode);
                                    break;
                                case 557846559:
                                    int acNaturalWindR = hvacEventChangeValue.getAcNaturalWindR();
                                    this.C.setAcNaturalWind(acNaturalWindR);
                                    LogUtil.verbose(str, "ID_AC_NATURAL_WIND_STATE:" + acNaturalWindR);
                                    break;
                                case 557846560:
                                    int acNaturalWindS = hvacEventChangeValue.getAcNaturalWindS();
                                    this.C.setAcNaturalWind(acNaturalWindS);
                                    LogUtil.verbose(str, "ID_AC_NATURAL_WIND:" + acNaturalWindS);
                                    break;
                                case 557846561:
                                    int acRequestRight = hvacEventChangeValue.getAcRequestRight();
                                    this.C.setAcRequestRight(acRequestRight);
                                    LogUtil.verbose(str, "ID_AC_COMPRESSOR_MODE:" + acRequestRight);
                                    break;
                                case 557846562:
                                    int acDualMode = hvacEventChangeValue.getAcDualMode();
                                    this.C.setAcDualMode(acDualMode);
                                    LogUtil.verbose(str, "ID_AC_DUAL_MODE:" + acDualMode);
                                    break;
                                case 557846563:
                                    int acControllerDriverTempR = hvacEventChangeValue.getAcControllerDriverTempR();
                                    this.C.setAcControllerDriverTemp(acControllerDriverTempR);
                                    LogUtil.verbose(str, "ID_AC_CONTROLLER_DRIVER_TEMP_SET:" + acControllerDriverTempR);
                                    break;
                                case 557846564:
                                    int acControllerDriverTempS = hvacEventChangeValue.getAcControllerDriverTempS();
                                    this.C.setAcControllerDriverTemp(acControllerDriverTempS);
                                    LogUtil.verbose(str, "ID_AC_PAD_DRIVER_TEMP_SET:" + acControllerDriverTempS);
                                    break;
                                case 557846665:
                                    int acHeatingStateR = hvacEventChangeValue.getAcHeatingStateR();
                                    this.B = acHeatingStateR;
                                    this.C.setAcHeatingStateR(acHeatingStateR);
                                    LogUtil.verbose(str, "ID_AC_HEATING_STATE_R:" + this.B);
                                    break;
                                case 557846692:
                                    int acWorkModeR = hvacEventChangeValue.getAcWorkModeR();
                                    this.C.setAcWorkModeR(acWorkModeR);
                                    LogUtil.debug(str, "ID_A_C_WORK_MODE_R:" + acWorkModeR);
                                    break;
                                case 557846693:
                                    int acWorkModeMidLeftR = hvacEventChangeValue.getAcWorkModeMidLeftR();
                                    this.C.setAcWorkModeMidLeftR(acWorkModeMidLeftR);
                                    LogUtil.debug(str, "ID_+A_C_WORK_MODE_MID_LEFT_R:" + acWorkModeMidLeftR);
                                    break;
                                case 557846694:
                                    int acWorkModeMidRightR = hvacEventChangeValue.getAcWorkModeMidRightR();
                                    this.C.setAcWorkModeMidRightR(acWorkModeMidRightR);
                                    LogUtil.debug(str, "ID_+A_C_WORK_MODE_MID_RIGHT_R:" + acWorkModeMidRightR);
                                    break;
                                case 557846695:
                                    int acWorkModeRearR = hvacEventChangeValue.getAcWorkModeRearR();
                                    this.C.setAcWorkModeRearR(acWorkModeRearR);
                                    LogUtil.debug(str, "ID_+A_C_WORK_MODE_REAR_R:" + acWorkModeRearR);
                                    break;
                                case 557846696:
                                    int acTuyereSwitchStateMainLeftR = hvacEventChangeValue.getAcTuyereSwitchStateMainLeftR();
                                    this.C.setAcTuyereSwitchStateMainLeftR(acTuyereSwitchStateMainLeftR);
                                    LogUtil.debug(str, "ID_AC_TUYERE_SWITCH_STATE_MAIN_LEFT_R:" + acTuyereSwitchStateMainLeftR);
                                    break;
                                case 557846697:
                                    int acTuyereSwitchStateMainRightR = hvacEventChangeValue.getAcTuyereSwitchStateMainRightR();
                                    this.C.setAcTuyereSwitchStateMainRightR(acTuyereSwitchStateMainRightR);
                                    LogUtil.debug(str, "ID_AC_TUYERE_SWITCH_STATE_MAIN_RIGHT_R:" + acTuyereSwitchStateMainRightR);
                                    break;
                                case 557846698:
                                    int acTuyereSwitchStateCopilotLeftR = hvacEventChangeValue.getAcTuyereSwitchStateCopilotLeftR();
                                    this.C.setAcTuyereSwitchStateCopilotLeftR(acTuyereSwitchStateCopilotLeftR);
                                    LogUtil.debug(str, "ID_AC_TUYERE_SWITCH_STATE_COPILOT_LEFT_R:" + acTuyereSwitchStateCopilotLeftR);
                                    break;
                                case 557846699:
                                    int acTuyereSwitchStateCopilotRightR = hvacEventChangeValue.getAcTuyereSwitchStateCopilotRightR();
                                    this.C.setAcTuyereSwitchStateCopilotRightR(acTuyereSwitchStateCopilotRightR);
                                    LogUtil.debug(str, "ID_AC_TUYERE_SWITCH_STATE_COPILOT_RIGHT_R:" + acTuyereSwitchStateCopilotRightR);
                                    break;
                                case 557846705:
                                    int acCockpitPurificationStatusR = hvacEventChangeValue.getAcCockpitPurificationStatusR();
                                    this.C.setAcCockpitPurificationStatusR(acCockpitPurificationStatusR);
                                    LogUtil.debug(str, "ID_AC_COCKPIT_PURIFICATION_STATUS_R:" + acCockpitPurificationStatusR);
                                    break;
                                case 557856063:
                                    int acRemoteCtrlTimeR = hvacEventChangeValue.getAcRemoteCtrlTimeR();
                                    this.C.setAcRemoteCtrlTimeR(acRemoteCtrlTimeR);
                                    LogUtil.verbose(str, "ID_AC_REMOTE_CTRL_TIMER:" + acRemoteCtrlTimeR);
                                    break;
                                case 559943766:
                                    float airOutletValueDriverLeftVerticalR = hvacEventChangeValue.getAirOutletValueDriverLeftVerticalR();
                                    this.n = airOutletValueDriverLeftVerticalR;
                                    this.C.setAirOutletValueDriverLeftVerticalR(airOutletValueDriverLeftVerticalR);
                                    LogUtil.verbose(str, "ID_AIR_OUTLET_VALUE_DRIVER_LEFT_VERTICAL_R:" + this.n);
                                    break;
                                case 559943767:
                                    float airOutletValueDriverLeftHorizontalR = hvacEventChangeValue.getAirOutletValueDriverLeftHorizontalR();
                                    this.m = airOutletValueDriverLeftHorizontalR;
                                    this.C.setAirOutletValueDriverLeftHorizontalR(airOutletValueDriverLeftHorizontalR);
                                    LogUtil.verbose(str, "ID_AIR_OUTLET_VALUE_DRIVER_LEFT_HORIZONTAL_R:" + this.m);
                                    break;
                                case 559943768:
                                    float airOutletValueDriverRightVerticalR = hvacEventChangeValue.getAirOutletValueDriverRightVerticalR();
                                    this.p = airOutletValueDriverRightVerticalR;
                                    this.C.setAirOutletValueDriverRightVerticalR(airOutletValueDriverRightVerticalR);
                                    LogUtil.verbose(str, "ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_VERTICAL_R:" + this.p);
                                    break;
                                case 559943769:
                                    float airOutletValueDriverRightHorizontalR = hvacEventChangeValue.getAirOutletValueDriverRightHorizontalR();
                                    this.o = airOutletValueDriverRightHorizontalR;
                                    this.C.setAirOutletValueDriverRightHorizontalR(airOutletValueDriverRightHorizontalR);
                                    LogUtil.verbose(str, "ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_HORIZONTAL_R:" + this.o);
                                    break;
                                case 559943770:
                                    float airOutletValueCopilotLeftVerticalR = hvacEventChangeValue.getAirOutletValueCopilotLeftVerticalR();
                                    this.j = airOutletValueCopilotLeftVerticalR;
                                    this.C.setAirOutletValueCopilotLeftVerticalR(airOutletValueCopilotLeftVerticalR);
                                    LogUtil.verbose(str, "ID_AIR_OUTLET_VALUE_COPILOT_LEFT_VERTICAL_R:" + this.j);
                                    break;
                                case 559943771:
                                    float airOutletValueCopilotLeftHorizontalR = hvacEventChangeValue.getAirOutletValueCopilotLeftHorizontalR();
                                    this.i = airOutletValueCopilotLeftHorizontalR;
                                    this.C.setAirOutletValueCopilotLeftHorizontalR(airOutletValueCopilotLeftHorizontalR);
                                    LogUtil.verbose(str, "ID_AIR_OUTLET_VALUE_COPILOT_LEFT_HORIZONTAL_R:" + this.i);
                                    break;
                                case 559943772:
                                    float airOutletValueCopilotRightVerticalR = hvacEventChangeValue.getAirOutletValueCopilotRightVerticalR();
                                    this.l = airOutletValueCopilotRightVerticalR;
                                    this.C.setAirOutletValueCopilotRightVerticalR(airOutletValueCopilotRightVerticalR);
                                    LogUtil.verbose(str, "ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_VERTICAL_R:" + this.l);
                                    break;
                                case 559943773:
                                    float airOutletValueCopilotRightHorizontalR = hvacEventChangeValue.getAirOutletValueCopilotRightHorizontalR();
                                    this.k = airOutletValueCopilotRightHorizontalR;
                                    this.C.setAirOutletValueCopilotRightHorizontalR(airOutletValueCopilotRightHorizontalR);
                                    LogUtil.verbose(str, "ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_HORIZONTAL_R:" + this.k);
                                    break;
                                default:
                                    switch (i) {
                                        case 557846566:
                                            int acControllerPassengerTempS = hvacEventChangeValue.getAcControllerPassengerTempS();
                                            this.C.setAcControllerPassengerTemp(acControllerPassengerTempS);
                                            LogUtil.verbose(str, "ID_AC_PAD_PASSENGER_TEMP_SET:" + acControllerPassengerTempS);
                                            break;
                                        case 557846567:
                                            int acFrontWindLevelR = hvacEventChangeValue.getAcFrontWindLevelR();
                                            this.C.setAcFrontWindLevelR(acFrontWindLevelR);
                                            LogUtil.verbose(str, "ID_AC_CONTROLLER_WIND_LEVEL:" + acFrontWindLevelR);
                                            break;
                                        case 557846568:
                                            int acFrontWindLevelS = hvacEventChangeValue.getAcFrontWindLevelS();
                                            this.C.setAcFrontWindLevel(acFrontWindLevelS);
                                            LogUtil.verbose(str, "ID_AC_PAD_WIND_LEVEL:" + acFrontWindLevelS);
                                            break;
                                        case 557846569:
                                            int acWindModeR = hvacEventChangeValue.getAcWindModeR();
                                            this.C.setAcWindMode(acWindModeR);
                                            LogUtil.verbose(str, "ID_AC_CONTROLLER_WIND_MODE:" + acWindModeR);
                                            break;
                                        case 557846570:
                                            int acWindModeS = hvacEventChangeValue.getAcWindModeS();
                                            this.C.setAcWindMode(acWindModeS);
                                            LogUtil.verbose(str, "ID_AC_PAD_FRONT_WIND_MODE:" + acWindModeS);
                                            break;
                                        case 557846571:
                                            int acMaxColdR = hvacEventChangeValue.getAcMaxColdR();
                                            this.C.setAcMaxCold(acMaxColdR);
                                            LogUtil.verbose(str, "ID_AC_CONTROLLER_MAX_COLD_STATE:" + acMaxColdR);
                                            break;
                                        default:
                                            switch (i) {
                                                case 557846574:
                                                    int acIonStateS = hvacEventChangeValue.getAcIonStateS();
                                                    this.C.setAcIonState(acIonStateS);
                                                    LogUtil.verbose(str, "ID_AC_MEDIA_ION_STATE:" + acIonStateS);
                                                    break;
                                                case 557846575:
                                                    int acMediaDefrostButton = hvacEventChangeValue.getAcMediaDefrostButton();
                                                    this.C.setAcMediaDefrostButton(acMediaDefrostButton);
                                                    LogUtil.verbose(str, "ID_AC_MEDIA_DEFROST_BUTTON:" + acMediaDefrostButton);
                                                    break;
                                                case 557846576:
                                                    int acRearPanelLockR = hvacEventChangeValue.getAcRearPanelLockR();
                                                    this.C.setAcRearPanelLock(acRearPanelLockR);
                                                    LogUtil.verbose(str, "ID_AC_REAR_PANEL_LOCK:" + acRearPanelLockR);
                                                    break;
                                                case 557846577:
                                                    int acRearPanelLockS = hvacEventChangeValue.getAcRearPanelLockS();
                                                    this.C.setAcRearPanelLock(acRearPanelLockS);
                                                    LogUtil.verbose(str, "ID_AC_MEDIA_REAR_PANEL_LOCK:" + acRearPanelLockS);
                                                    break;
                                                case 557846578:
                                                    int acRearAcCtrlModeR = hvacEventChangeValue.getAcRearAcCtrlModeR();
                                                    this.C.setAcRearAcCtrlMode(acRearAcCtrlModeR);
                                                    LogUtil.verbose(str, "ID_AC_CONTROLLER_REAR_AC_CTRL_MODE:" + acRearAcCtrlModeR);
                                                    break;
                                                case 557846579:
                                                    int acRearAcCtrlModeS = hvacEventChangeValue.getAcRearAcCtrlModeS();
                                                    this.C.setAcRearAcCtrlMode(acRearAcCtrlModeS);
                                                    LogUtil.verbose(str, "ID_AC_PAD_REAR_CTRL_MODE:" + acRearAcCtrlModeS);
                                                    break;
                                                case 557846580:
                                                    int acRearAcState = hvacEventChangeValue.getAcRearAcState();
                                                    this.C.setAcRearAcState(acRearAcState);
                                                    LogUtil.verbose(str, "ID_AC_CONTROLLER_REAR_STATE:" + acRearAcState);
                                                    break;
                                                case 557846581:
                                                    int acRearAcStateS = hvacEventChangeValue.getAcRearAcStateS();
                                                    this.C.setAcRearAcState(acRearAcStateS);
                                                    LogUtil.verbose(str, "ID_AC_PAD_REAR_STATE:" + acRearAcStateS);
                                                    break;
                                                case 557846582:
                                                    int acRearWindModeR = hvacEventChangeValue.getAcRearWindModeR();
                                                    this.C.setAcRearWindMode(acRearWindModeR);
                                                    LogUtil.verbose(str, "ID_AC_CONTROLLER_REAR_WIND_MODE:" + acRearWindModeR);
                                                    break;
                                                case 557846583:
                                                    int acRearWindModeS = hvacEventChangeValue.getAcRearWindModeS();
                                                    this.C.setAcRearWindMode(acRearWindModeS);
                                                    LogUtil.verbose(str, "ID_AC_PAD_REAR_WIND_MODE:" + acRearWindModeS);
                                                    break;
                                                case 557846584:
                                                    int acRearTempR = hvacEventChangeValue.getAcRearTempR();
                                                    this.C.setAcRearTemp(acRearTempR);
                                                    LogUtil.verbose(str, "ID_AC_CONTROLLER_REAR_TEMP:" + acRearTempR);
                                                    break;
                                                case 557846585:
                                                    int acRearTempS = hvacEventChangeValue.getAcRearTempS();
                                                    this.C.setAcRearTemp(acRearTempS);
                                                    LogUtil.verbose(str, "ID_AC_PAD_REAR_TEMP:" + acRearTempS);
                                                    break;
                                                case 557846586:
                                                    int acRearWindLevelR = hvacEventChangeValue.getAcRearWindLevelR();
                                                    this.C.setAcRearWindLevel(acRearWindLevelR);
                                                    LogUtil.verbose(str, "ID_AC_CONTROLLER_REAR_WIND_LEVEL:" + acRearWindLevelR);
                                                    break;
                                                case 557846587:
                                                    int acRearWindLevelS = hvacEventChangeValue.getAcRearWindLevelS();
                                                    this.C.setAcRearWindLevel(acRearWindLevelS);
                                                    LogUtil.verbose(str, "ID_AC_PAD_REAR_WIND_LEVEL:" + acRearWindLevelS);
                                                    break;
                                                case 557846588:
                                                    int acRearLockR = hvacEventChangeValue.getAcRearLockR();
                                                    this.C.setAcRearLock(acRearLockR);
                                                    LogUtil.verbose(str, "ID_AC_CONTROLLER_REAR_LOCK:" + acRearLockR);
                                                    break;
                                                case 557846589:
                                                    int acRearLockS = hvacEventChangeValue.getAcRearLockS();
                                                    this.C.setAcRearLock(acRearLockS);
                                                    LogUtil.verbose(str, "ID_AC_PAD_REAR_LOCK:" + acRearLockS);
                                                    break;
                                                case 557846590:
                                                    int acCompressorManualSign = hvacEventChangeValue.getAcCompressorManualSign();
                                                    this.C.setAcCompressorManualSign(acCompressorManualSign);
                                                    LogUtil.verbose(str, "ID_AC_COMPRESSOR_MANUAL_SIGN:" + acCompressorManualSign);
                                                    break;
                                                case 557846591:
                                                    int acWindLevelManualSign = hvacEventChangeValue.getAcWindLevelManualSign();
                                                    this.C.setAcWindLevelManualSign(acWindLevelManualSign);
                                                    LogUtil.verbose(str, "ID_AC_WIND_LEVEL_MANUAL_SIGN:" + acWindLevelManualSign);
                                                    break;
                                                case 557846592:
                                                    int acWindModeManualSign = hvacEventChangeValue.getAcWindModeManualSign();
                                                    this.C.setAcWindModeManualSign(acWindModeManualSign);
                                                    LogUtil.verbose(str, "ID_AC_WIND_MODE_MANUAL_SIGN:" + acWindModeManualSign);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 557846594:
                                                            int acDefrostConfigLeft = hvacEventChangeValue.getAcDefrostConfigLeft();
                                                            this.C.setAcDefrostConfigLeft(acDefrostConfigLeft);
                                                            LogUtil.verbose(str, "ID_DEFROST_REAR_CONFIG:" + acDefrostConfigLeft);
                                                            break;
                                                        case 557846595:
                                                            int confirmCleanAirState = hvacEventChangeValue.getConfirmCleanAirState();
                                                            this.C.setConfirmCleanAirState(confirmCleanAirState);
                                                            LogUtil.verbose(str, "ID_CONFIRM_CLEAN_AIR_STATE:" + confirmCleanAirState);
                                                            break;
                                                        case 557846596:
                                                            int highTempAntivirusCountDown = hvacEventChangeValue.getHighTempAntivirusCountDown();
                                                            this.C.setHighTempAntivirusCountDown(highTempAntivirusCountDown);
                                                            LogUtil.verbose(str, "ID_HIGH_TEMP_ANTIVIRUS_COUNT_DOWN:" + highTempAntivirusCountDown);
                                                            break;
                                                        case 557846597:
                                                            int highTempAntivirusState = hvacEventChangeValue.getHighTempAntivirusState();
                                                            this.C.setHighTempAntivirusState(highTempAntivirusState);
                                                            LogUtil.verbose(str, "ID_HIGHTEMPANTIVIRUSSTATE:" + highTempAntivirusState);
                                                            break;
                                                        case 557846598:
                                                            int autoCleanAirStateS = hvacEventChangeValue.getAutoCleanAirStateS();
                                                            this.C.setAutoCleanAirState(autoCleanAirStateS);
                                                            LogUtil.verbose(str, "ID_AUTO_CLEAN_AIR_STATES:" + autoCleanAirStateS);
                                                            break;
                                                        case 557846599:
                                                            int acMaxCoolingStateS = hvacEventChangeValue.getAcMaxCoolingStateS();
                                                            this.C.setAcMaxCoolingState(acMaxCoolingStateS);
                                                            LogUtil.verbose(str, "ID_MAX_COOLING_STATES:" + acMaxCoolingStateS);
                                                            break;
                                                        case 557846600:
                                                            int acRemoteCtrlTimeS = hvacEventChangeValue.getAcRemoteCtrlTimeS();
                                                            this.C.setAcRemoteCtrlTime(acRemoteCtrlTimeS);
                                                            LogUtil.verbose(str, "ID_AC_REMOTE_CTRL_TIMES:" + acRemoteCtrlTimeS);
                                                            break;
                                                        case 557846601:
                                                            int acPtcPreheatSignalR = hvacEventChangeValue.getAcPtcPreheatSignalR();
                                                            this.C.setAcPtcPreheatSignal(acPtcPreheatSignalR);
                                                            LogUtil.verbose(str, "ID_AC_PTC_PREHEAT_SIGNALR:" + acPtcPreheatSignalR);
                                                            break;
                                                        case 557846602:
                                                            int acWindModeNumR = hvacEventChangeValue.getAcWindModeNumR();
                                                            this.C.setAcWindModeNum(acWindModeNumR);
                                                            LogUtil.verbose(str, "ID_AC_WINDMODE_NUMR:" + acWindModeNumR);
                                                            break;
                                                        case 557846603:
                                                            int acTempratureDeputy = hvacEventChangeValue.getAcTempratureDeputy();
                                                            this.C.setAcTempratureDeputy(acTempratureDeputy);
                                                            LogUtil.verbose(str, "ID_AC_TEMP_DEPUTY:" + acTempratureDeputy);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 557846605:
                                                                    int quickCleanAirStateS = hvacEventChangeValue.getQuickCleanAirStateS();
                                                                    this.C.setQuickCleanAirState(quickCleanAirStateS);
                                                                    LogUtil.verbose(str, "ID_AC_QUICK_CLEAN_AIR_STATES:" + quickCleanAirStateS);
                                                                    break;
                                                                case 557846606:
                                                                    int quickCleanAirStateR = hvacEventChangeValue.getQuickCleanAirStateR();
                                                                    this.C.setQuickCleanAirStateR(quickCleanAirStateR);
                                                                    LogUtil.verbose(str, "ID_AC_QUICK_CLEAN_AIR_STATER:" + quickCleanAirStateR);
                                                                    break;
                                                                case 557846607:
                                                                    int quickCleanTipR = hvacEventChangeValue.getQuickCleanTipR();
                                                                    this.C.setQuickCleanTipR(quickCleanTipR);
                                                                    LogUtil.verbose(str, "ID_QUICK_CLEAN_TIPR:" + quickCleanTipR);
                                                                    break;
                                                                case 557846608:
                                                                    int autoCleanAirStateR = hvacEventChangeValue.getAutoCleanAirStateR();
                                                                    this.C.setAutoCleanAirStateR(autoCleanAirStateR);
                                                                    LogUtil.verbose(str, "ID_AUTO_CLEAN_AIR_STATER:" + autoCleanAirStateR);
                                                                    break;
                                                                case 557846609:
                                                                    int acAssistModeTips = hvacEventChangeValue.getAcAssistModeTips();
                                                                    this.C.setAcAssistModeTips(acAssistModeTips);
                                                                    LogUtil.verbose(str, "ID_AC_ASSIST_MODE_TIPS:" + acAssistModeTips);
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 557846611:
                                                                            int acbtWindTips = hvacEventChangeValue.getAcbtWindTips();
                                                                            this.C.setAcbtWindTips(acbtWindTips);
                                                                            LogUtil.verbose(str, "ID_ACBT_WIND_TIPS:" + acbtWindTips);
                                                                            break;
                                                                        case 557846612:
                                                                            int acDefrosRearPanelNotAvailableTips = hvacEventChangeValue.getAcDefrosRearPanelNotAvailableTips();
                                                                            this.C.setAcDefrosRearPanelNotAvailableTips(acDefrosRearPanelNotAvailableTips);
                                                                            LogUtil.verbose(str, "ID_AC_DEFROST_REAR_PANEL_NOT_AVAILABLE_TIPS:" + acDefrosRearPanelNotAvailableTips);
                                                                            break;
                                                                        case 557846613:
                                                                            int acDefrostOpenStateLeft = hvacEventChangeValue.getAcDefrostOpenStateLeft();
                                                                            this.C.setAcDefrostOpenStateLeft(acDefrostOpenStateLeft);
                                                                            LogUtil.verbose(str, "ID_AC_DEFROST_OPEN_STATE_LEFT:" + acDefrostOpenStateLeft);
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case 557846622:
                                                                                    int freeWindBtnStateDriverR = hvacEventChangeValue.getFreeWindBtnStateDriverR();
                                                                                    this.v = freeWindBtnStateDriverR;
                                                                                    this.C.setFreeWindBtnStateDriverR(freeWindBtnStateDriverR);
                                                                                    LogUtil.verbose(str, "ID_FREE_WIND_BTN_STATE_DRIVER_R:" + this.v);
                                                                                    break;
                                                                                case 557846623:
                                                                                    int freeWindBtnStateCopilotR = hvacEventChangeValue.getFreeWindBtnStateCopilotR();
                                                                                    this.u = freeWindBtnStateCopilotR;
                                                                                    this.C.setFreeWindBtnStateCopilotR(freeWindBtnStateCopilotR);
                                                                                    LogUtil.verbose(str, "ID_FREE_WIND_BTN_STATE_COPILOT_R:" + this.u);
                                                                                    break;
                                                                                case 557846624:
                                                                                    int blowPeopleStateDriverR = hvacEventChangeValue.getBlowPeopleStateDriverR();
                                                                                    this.t = blowPeopleStateDriverR;
                                                                                    this.C.setBlowPeopleStateDriverR(blowPeopleStateDriverR);
                                                                                    LogUtil.verbose(str, "ID_BLOW_PEOPLE_STATE_DRIVER_R:" + this.t);
                                                                                    break;
                                                                                case 557846625:
                                                                                    int blowPeopleStateCopilotR = hvacEventChangeValue.getBlowPeopleStateCopilotR();
                                                                                    this.s = blowPeopleStateCopilotR;
                                                                                    this.C.setBlowPeopleStateCopilotR(blowPeopleStateCopilotR);
                                                                                    LogUtil.verbose(str, "ID_BLOW_PEOPLE_STATE_COPILOT_R:" + this.s);
                                                                                    break;
                                                                                case 557846626:
                                                                                    int avoidBlowPeopleStateDriverR = hvacEventChangeValue.getAvoidBlowPeopleStateDriverR();
                                                                                    this.r = avoidBlowPeopleStateDriverR;
                                                                                    this.C.setAvoidBlowPeopleStateDriverR(avoidBlowPeopleStateDriverR);
                                                                                    LogUtil.verbose(str, "ID_AVOID_BLOW_PEOPLE_STATE_DRIVER_R:" + this.r);
                                                                                    break;
                                                                                case 557846627:
                                                                                    int avoidBlowPeopleStateCopilotR = hvacEventChangeValue.getAvoidBlowPeopleStateCopilotR();
                                                                                    this.q = avoidBlowPeopleStateCopilotR;
                                                                                    this.C.setAvoidBlowPeopleStateCopilotR(avoidBlowPeopleStateCopilotR);
                                                                                    LogUtil.verbose(str, "ID_AVOID_BLOW_PEOPLE_STATE_COPILOT_R:" + this.q);
                                                                                    break;
                                                                                case 557846628:
                                                                                    int sweepBlowStateDriverR = hvacEventChangeValue.getSweepBlowStateDriverR();
                                                                                    this.z = sweepBlowStateDriverR;
                                                                                    this.C.setSweepBlowStateDriverR(sweepBlowStateDriverR);
                                                                                    LogUtil.verbose(str, "ID_SWEEP_BLOW_STATE_DRIVER_R:" + this.z);
                                                                                    break;
                                                                                case 557846629:
                                                                                    int sweepBlowStateCopilotR = hvacEventChangeValue.getSweepBlowStateCopilotR();
                                                                                    this.y = sweepBlowStateCopilotR;
                                                                                    this.C.setSweepBlowStateCopilotR(sweepBlowStateCopilotR);
                                                                                    LogUtil.verbose(str, "ID_SWEEP_BLOW_STATE_COPILOT_R:" + this.y);
                                                                                    break;
                                                                                case 557846630:
                                                                                    int airOutletStateDriverR = hvacEventChangeValue.getAirOutletStateDriverR();
                                                                                    this.h = airOutletStateDriverR;
                                                                                    this.C.setAirOutletStateDriverR(airOutletStateDriverR);
                                                                                    LogUtil.verbose(str, "ID_AIR_OUTLET_STATE_DRIVER_R:" + this.h);
                                                                                    break;
                                                                                case 557846631:
                                                                                    int airOutletStateCopilotR = hvacEventChangeValue.getAirOutletStateCopilotR();
                                                                                    this.g = airOutletStateCopilotR;
                                                                                    this.C.setAirOutletStateCopilotR(airOutletStateCopilotR);
                                                                                    LogUtil.verbose(str, "ID_AIR_OUTLET_STATE_COPILOT_R:" + this.g);
                                                                                    break;
                                                                                case 557846632:
                                                                                    int intelligentControlStateR = hvacEventChangeValue.getIntelligentControlStateR();
                                                                                    this.x = intelligentControlStateR;
                                                                                    this.C.setIntelligentControlStateR(intelligentControlStateR);
                                                                                    LogUtil.verbose(str, "ID_INTELLIGENT_CONTROL_STATE_R:" + this.x);
                                                                                    break;
                                                                                case 557846633:
                                                                                    int frontRowAirOutletStateR = hvacEventChangeValue.getFrontRowAirOutletStateR();
                                                                                    this.w = frontRowAirOutletStateR;
                                                                                    this.C.setFrontRowAirOutletStateR(frontRowAirOutletStateR);
                                                                                    LogUtil.verbose(str, "ID_FRONT_ROW_AIR_OUTLET_STATE_R:" + this.w);
                                                                                    break;
                                                                                case 557846634:
                                                                                    int acWorkModeDriverR = hvacEventChangeValue.getAcWorkModeDriverR();
                                                                                    this.f = acWorkModeDriverR;
                                                                                    this.C.setAcWorkModeDriverR(acWorkModeDriverR);
                                                                                    LogUtil.verbose(str, "ID_AC_WORK_MODE_DRIVER_R:" + this.f);
                                                                                    break;
                                                                                case 557846635:
                                                                                    int acWorkModeCopilotR = hvacEventChangeValue.getAcWorkModeCopilotR();
                                                                                    this.e = acWorkModeCopilotR;
                                                                                    this.C.setAcWorkModeCopilotR(acWorkModeCopilotR);
                                                                                    LogUtil.verbose(str, "ID_AC_WORK_MODE_COPILOT_R:" + this.e);
                                                                                    break;
                                                                                default:
                                                                                    switch (i) {
                                                                                        case 557846657:
                                                                                            int acPromptBoxShownStateR = hvacEventChangeValue.getAcPromptBoxShownStateR();
                                                                                            this.A = acPromptBoxShownStateR;
                                                                                            this.C.setAcPromptBoxShownStateR(acPromptBoxShownStateR);
                                                                                            LogUtil.verbose(str, "ID_AC_PROMPT_BOX_SHOWN_STATE_R:" + this.A);
                                                                                            break;
                                                                                        case 557846658:
                                                                                            int acKeepEngineRunTIps = hvacEventChangeValue.getAcKeepEngineRunTIps();
                                                                                            this.C.setAcKeepEngineRunTIp(acKeepEngineRunTIps);
                                                                                            LogUtil.verbose(str, "ID_AC_KEEP_ENGINE_RUN_TIPS:" + acKeepEngineRunTIps);
                                                                                            break;
                                                                                        default:
                                                                                            return;
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
            this.d.m(i, i2, this.C);
            LogUtil.verbose(str, "notifyHvacEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(int i, int i2, Pm2p5EventChangeValue pm2p5EventChangeValue) {
        String str = G;
        try {
            this.C.setPropertyId(i);
            switch (i) {
                case 557854720:
                    int pm2p5DualChannelDetectStateR = pm2p5EventChangeValue.getPm2p5DualChannelDetectStateR();
                    this.C.setPm2p5DualChannelDetectStateR(pm2p5DualChannelDetectStateR);
                    LogUtil.verbose(str, "ID_PM2P5_DUAL_CHANNEL_DETECT_STATER-" + pm2p5DualChannelDetectStateR);
                    break;
                case 557854721:
                    int pm2p5DualChannelDetectStateS = pm2p5EventChangeValue.getPm2p5DualChannelDetectStateS();
                    this.C.setPm2p5DualChannelDetectStateS(pm2p5DualChannelDetectStateS);
                    LogUtil.verbose(str, "ID_PM2P5_DUAL_CHANNEL_DETECT_STATES-" + pm2p5DualChannelDetectStateS);
                    break;
                case 557854722:
                    int pm2p5CheckStateIn = pm2p5EventChangeValue.getPm2p5CheckStateIn();
                    this.C.setPm2p5CheckStateIn(pm2p5CheckStateIn);
                    LogUtil.verbose(str, "ID_PM2P5_CHECK_STATE_IN-" + pm2p5CheckStateIn);
                    break;
                case 557854723:
                    int pm2p5CheckStateOut = pm2p5EventChangeValue.getPm2p5CheckStateOut();
                    this.C.setPm2p5CheckStateOut(pm2p5CheckStateOut);
                    LogUtil.verbose(str, "ID_PM2P5_CHECK_STATE_OUT-" + pm2p5CheckStateOut);
                    break;
                case 557854724:
                    int pm2p5CheckState = pm2p5EventChangeValue.getPm2p5CheckState();
                    this.C.setPm2p5CheckState(pm2p5CheckState);
                    LogUtil.verbose(str, "ID_PM2P5_CHECK_STATE-" + pm2p5CheckState);
                    break;
                case 557854725:
                    int pm2p5ValueIn = pm2p5EventChangeValue.getPm2p5ValueIn();
                    this.C.setPm2p5ValueIn(pm2p5ValueIn);
                    LogUtil.verbose(str, "ID_PM2P5_VALUE_IN-" + pm2p5ValueIn);
                    break;
                case 557854726:
                    int pm2p5ValueOut = pm2p5EventChangeValue.getPm2p5ValueOut();
                    this.C.setPm2p5ValueOut(pm2p5ValueOut);
                    LogUtil.verbose(str, "ID_PM2P5_VALUE_OUT-" + pm2p5ValueOut);
                    break;
                case 557854727:
                    int pm2p5AnionState = pm2p5EventChangeValue.getPm2p5AnionState();
                    this.C.setPm2p5AnionState(pm2p5AnionState);
                    LogUtil.verbose(str, "ID_PM2P5_ANION_STATE-" + pm2p5AnionState);
                    break;
                case 557854728:
                    int pm2p5LevelIn = pm2p5EventChangeValue.getPm2p5LevelIn();
                    this.C.setPm2p5LevelIn(pm2p5LevelIn);
                    LogUtil.verbose(str, "ID_PM2P5_LEVEL_IN-" + pm2p5LevelIn);
                    break;
                case 557854729:
                    int pm2p5LevelOut = pm2p5EventChangeValue.getPm2p5LevelOut();
                    this.C.setPm2p5LevelOut(pm2p5LevelOut);
                    LogUtil.verbose(str, "ID_PM2P5_LEVEL_OUT-" + pm2p5LevelOut);
                    break;
                case 557854730:
                    int pm2p5AnionDetectState = pm2p5EventChangeValue.getPm2p5AnionDetectState();
                    this.C.setPm2p5AnionDetectState(pm2p5AnionDetectState);
                    LogUtil.verbose(str, "ID_PM2P5_ANION_DETECT_STATE-" + pm2p5AnionDetectState);
                    break;
                case 557854731:
                    int pm2p5WarningInfo = pm2p5EventChangeValue.getPm2p5WarningInfo();
                    this.C.setPm2p5WarningInfo(pm2p5WarningInfo);
                    LogUtil.verbose(str, "ID_PM2P5_WARNING_INFO-" + pm2p5WarningInfo);
                    break;
                default:
                    return;
            }
            this.d.m(i, i2, this.C);
            LogUtil.verbose(str, "notifyPm2p5EventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void s() {
        if (this.E != null) {
            a aVar = this.F;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.E.quitSafely();
        }
    }

    @Override // com.ts.appservice.caradapter.d.m
    public void a(HvacEventChangeValue hvacEventChangeValue) {
        String str = G;
        if (hvacEventChangeValue == null) {
            LogUtil.error(str, "onHvacChangeEvent:value=null");
            return;
        }
        int propertyId = hvacEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onHvacChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = hvacEventChangeValue.getStatus();
        messageObtain.obj = hvacEventChangeValue;
        this.F.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.q
    public void b(Pm2p5EventChangeValue pm2p5EventChangeValue) {
        String str = G;
        if (pm2p5EventChangeValue == null) {
            LogUtil.error(str, "onPm2p5ChangeEvent:value=null");
            return;
        }
        int propertyId = pm2p5EventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onPm2p5ChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 5;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = pm2p5EventChangeValue.getStatus();
        messageObtain.obj = pm2p5EventChangeValue;
        this.F.sendMessage(messageObtain);
    }

    public int confirmCleanAirState(int i) {
        LogUtil.verbose(G, "confirmCleanAirState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setConfirmCleanAirState(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ts.appservice.caradapter.d.j
    public void d(FragranceEventChangeValue fragranceEventChangeValue) {
        String str = G;
        if (fragranceEventChangeValue == null) {
            LogUtil.error(str, "onFragranceChangeEvent:value=null");
            return;
        }
        int propertyId = fragranceEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onFragranceChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 4;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = fragranceEventChangeValue.getStatus();
        messageObtain.obj = fragranceEventChangeValue;
        this.F.sendMessage(messageObtain);
    }

    public int enablePurificationFunctionPrompt(int i) {
        return 0;
    }

    public int feelColdHot(int i, int i2) {
        return 0;
    }

    public int get3daOnlineState() {
        return 0;
    }

    public int getACPreheatState() {
        return 0;
    }

    public int getACSystemState() {
        return 0;
    }

    public int getAcAqsConfig() {
        int acAqsConfigR;
        String str = G;
        LogUtil.verbose(str, " getAcAqsConfig.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acAqsConfigR = carVendorHvacManager.getAcAqsConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acAqsConfigR = -1;
            }
            LogUtil.verbose(str, " getAcAqsConfig.out service =" + acAqsConfigR);
            return acAqsConfigR;
        }
        LogUtil.verbose(str, " getAcAqsConfig.null");
        acAqsConfigR = -1;
        LogUtil.verbose(str, " getAcAqsConfig.out service =" + acAqsConfigR);
        return acAqsConfigR;
    }

    public int getAcAssistModeTipsState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acAssistModeTips = carVendorHvacManager.getAcAssistModeTips();
            LogUtil.verbose(G, "getAcAssistModeTipsState=" + acAssistModeTips);
            return acAssistModeTips;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcCockpitPurificationStatus() {
        int acCockpitPurificationStatusR;
        String str = G;
        LogUtil.verbose(str, " getAcCockpitPurificationStatus.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acCockpitPurificationStatusR = carVendorHvacManager.getAcCockpitPurificationStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acCockpitPurificationStatusR = -1;
            }
            LogUtil.verbose(str, " getAcCockpitPurificationStatus.out service =" + acCockpitPurificationStatusR);
            return acCockpitPurificationStatusR;
        }
        LogUtil.verbose(str, " getAcCockpitPurificationStatus.null");
        acCockpitPurificationStatusR = -1;
        LogUtil.verbose(str, " getAcCockpitPurificationStatus.out service =" + acCockpitPurificationStatusR);
        return acCockpitPurificationStatusR;
    }

    public int getAcCompressorManualSign() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acCompressorManualSign = carVendorHvacManager.getAcCompressorManualSign();
            LogUtil.verbose(G, "getAcCompressorManualSign=" + acCompressorManualSign);
            return acCompressorManualSign;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcCompressorMode() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acRequestRight = carVendorHvacManager.getAcRequestRight();
            LogUtil.verbose(G, "getAcCompressorMode=" + acRequestRight);
            return acRequestRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcCompressorState() {
        return 0;
    }

    public int getAcControlMode() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acCtrlModeR = carVendorHvacManager.getAcCtrlModeR();
            LogUtil.verbose(G, "getAcControlMode=" + acCtrlModeR);
            return acCtrlModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcCycleMode() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846553, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcCycleMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcDefrostOnlineState() {
        return 0;
    }

    public int getAcDefrostRearPanelNotAvailableTipsState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acDefrosRearPanelNotAvailableTips = carVendorHvacManager.getAcDefrosRearPanelNotAvailableTips();
            LogUtil.verbose(G, "getAcDefrostRearPanelNotAvailableTipsState=" + acDefrosRearPanelNotAvailableTips);
            return acDefrosRearPanelNotAvailableTips;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcDefrostStateFront() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846549, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcDefrostStateFront prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcDefrostStateRear() {
        LogUtil.verbose(G, "getAcDefrostStateRear");
        return 0;
    }

    public int getAcFaultNumShownState() {
        String str = G;
        LogUtil.verbose(str, "getAcFaultNumShownState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846542, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAcFaultNumShownState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcHeatingButtonConfig() {
        String str = G;
        LogUtil.verbose(str, " getAcHeatingButtonConfig.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846664, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAcHeatingButtonConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcHeatingState() {
        int acHeatingStateR;
        String str = G;
        LogUtil.verbose(str, " getAcHeatingState.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acHeatingStateR = carVendorHvacManager.getAcHeatingStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acHeatingStateR = -1;
            }
            LogUtil.verbose(str, " getAcHeatingState.out=" + acHeatingStateR);
            return acHeatingStateR;
        }
        LogUtil.verbose(str, " getAcHeatingState.null == mHvacManager");
        acHeatingStateR = -1;
        LogUtil.verbose(str, " getAcHeatingState.out=" + acHeatingStateR);
        return acHeatingStateR;
    }

    public int getAcKeepEngineRunTipsState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acKeepEngineRunTIps = carVendorHvacManager.getAcKeepEngineRunTIps();
            LogUtil.verbose(G, "getAcKeepEngineRunTipsState=" + acKeepEngineRunTIps);
            return acKeepEngineRunTIps;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcKeyActionState() {
        return 0;
    }

    public int getAcMaxCoolingState() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846571, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcMaxCoolingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcOnlineState() {
        return 0;
    }

    public int getAcPanelLockPromptState() {
        return 0;
    }

    public int getAcPromptBoxShownState() {
        String str = G;
        LogUtil.verbose(str, "getAcPromptBoxShownState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846657, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAcPromptBoxShownState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcPtcPreheatSignal() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acPtcPreheatSignalR = carVendorHvacManager.getAcPtcPreheatSignalR();
            LogUtil.verbose(G, "getAcPtcPreheatSignal=" + acPtcPreheatSignalR);
            return acPtcPreheatSignalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcRearPaneNotAvailableMenuState() {
        return 0;
    }

    public int getAcRearPanelLockState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acRearPanelLockR = carVendorHvacManager.getAcRearPanelLockR();
            LogUtil.verbose(G, "getAcRearPanelLockState=" + acRearPanelLockR);
            return acRearPanelLockR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcRemoteCtrlTime() {
        String str = G;
        LogUtil.verbose(str, "getAcRemoteCtrlTime");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856063, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSeatVentilatingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcStartState() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846552, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcStartState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcSubBatteryTemperature() {
        return 0;
    }

    public int getAcTemperatureControlMode() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846543, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcTemperatureControlMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcTuyereSwitchStateDeputyLeft() {
        int acTuyereSwitchStateCopilotLeftR;
        String str = G;
        LogUtil.verbose(str, " getAcTuyereSwitchStateDeputyLeft.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acTuyereSwitchStateCopilotLeftR = carVendorHvacManager.getAcTuyereSwitchStateCopilotLeftR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acTuyereSwitchStateCopilotLeftR = -1;
            }
            LogUtil.verbose(str, " getAcTuyereSwitchStateDeputyLeft.out service =" + acTuyereSwitchStateCopilotLeftR);
            return acTuyereSwitchStateCopilotLeftR;
        }
        LogUtil.verbose(str, " getAcTuyereSwitchStateDeputyLeft.null");
        acTuyereSwitchStateCopilotLeftR = -1;
        LogUtil.verbose(str, " getAcTuyereSwitchStateDeputyLeft.out service =" + acTuyereSwitchStateCopilotLeftR);
        return acTuyereSwitchStateCopilotLeftR;
    }

    public int getAcTuyereSwitchStateDeputyRight() {
        int acTuyereSwitchStateCopilotRightR;
        String str = G;
        LogUtil.verbose(str, " getAcTuyereSwitchStateDeputyRight.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acTuyereSwitchStateCopilotRightR = carVendorHvacManager.getAcTuyereSwitchStateCopilotRightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acTuyereSwitchStateCopilotRightR = -1;
            }
            LogUtil.verbose(str, " getAcTuyereSwitchStateDeputyRight.out service =" + acTuyereSwitchStateCopilotRightR);
            return acTuyereSwitchStateCopilotRightR;
        }
        LogUtil.verbose(str, " getAcTuyereSwitchStateDeputyRight.null");
        acTuyereSwitchStateCopilotRightR = -1;
        LogUtil.verbose(str, " getAcTuyereSwitchStateDeputyRight.out service =" + acTuyereSwitchStateCopilotRightR);
        return acTuyereSwitchStateCopilotRightR;
    }

    public int getAcTuyereSwitchStateMainLeft() {
        int acTuyereSwitchStateMainLeftR;
        String str = G;
        LogUtil.verbose(str, " getAcTuyereSwitchStateMainLeft.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acTuyereSwitchStateMainLeftR = carVendorHvacManager.getAcTuyereSwitchStateMainLeftR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acTuyereSwitchStateMainLeftR = -1;
            }
            LogUtil.verbose(str, " getAcTuyereSwitchStateMainLeft.out service =" + acTuyereSwitchStateMainLeftR);
            return acTuyereSwitchStateMainLeftR;
        }
        LogUtil.verbose(str, " getAcTuyereSwitchStateMainLeft.null");
        acTuyereSwitchStateMainLeftR = -1;
        LogUtil.verbose(str, " getAcTuyereSwitchStateMainLeft.out service =" + acTuyereSwitchStateMainLeftR);
        return acTuyereSwitchStateMainLeftR;
    }

    public int getAcTuyereSwitchStateMainRight() {
        int acTuyereSwitchStateMainRightR;
        String str = G;
        LogUtil.verbose(str, " getAcTuyereSwitchStateMainRight.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acTuyereSwitchStateMainRightR = carVendorHvacManager.getAcTuyereSwitchStateMainRightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acTuyereSwitchStateMainRightR = -1;
            }
            LogUtil.verbose(str, " getAcTuyereSwitchStateMainRight.out service =" + acTuyereSwitchStateMainRightR);
            return acTuyereSwitchStateMainRightR;
        }
        LogUtil.verbose(str, " getAcTuyereSwitchStateMainRight.null");
        acTuyereSwitchStateMainRightR = -1;
        LogUtil.verbose(str, " getAcTuyereSwitchStateMainRight.out service =" + acTuyereSwitchStateMainRightR);
        return acTuyereSwitchStateMainRightR;
    }

    public int getAcType() {
        String str = G;
        LogUtil.verbose(str, "getAcType");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846529, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAcType prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcVentilationState() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846559, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcVentilationState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcWarmState() {
        return 0;
    }

    public int getAcWarmTypeOnlineState() {
        String str = G;
        LogUtil.verbose(str, "getAcWarmTypeOnlineState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846557, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAcWarmTypeOnlineState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcWindLevel() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846567, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcWindLevel prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcWindLevelManualSign() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acWindLevelManualSign = carVendorHvacManager.getAcWindLevelManualSign();
            LogUtil.verbose(G, "getAcWindLevelManualSign=" + acWindLevelManualSign);
            return acWindLevelManualSign;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcWindMode() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846569, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcWindMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcWindModeManualSign() {
        String str = G;
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                int acWindModeManualSign = carVendorHvacManager.getAcWindModeManualSign();
                LogUtil.verbose(str, "getAcWindModeManualSign=" + acWindModeManualSign);
                return acWindModeManualSign;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.verbose(str, "getAcWindModeManualSign out");
        return 0;
    }

    public int getAcWindModeNum() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846602, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcWindModeNum prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAcWindModeShownState() {
        return 0;
    }

    public int getAcWorkCommand() {
        int acWorkCommandR;
        String str = G;
        LogUtil.verbose(str, " getAcWorkCommand.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acWorkCommandR = carVendorHvacManager.getAcWorkCommandR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acWorkCommandR = -1;
            }
            LogUtil.verbose(str, " getAcWorkCommand.out service =" + acWorkCommandR);
            return acWorkCommandR;
        }
        LogUtil.verbose(str, " getAcWorkCommand.null");
        acWorkCommandR = -1;
        LogUtil.verbose(str, " getAcWorkCommand.out service =" + acWorkCommandR);
        return acWorkCommandR;
    }

    public int getAcWorkModeCopilot() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acWorkModeCopilotR = carVendorHvacManager.getAcWorkModeCopilotR();
            LogUtil.verbose(G, "getAcWorkModeCopilot=" + acWorkModeCopilotR);
            return acWorkModeCopilotR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcWorkModeDriver() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acWorkModeDriverR = carVendorHvacManager.getAcWorkModeDriverR();
            LogUtil.verbose(G, "getAcWorkModeDriver=" + acWorkModeDriverR);
            return acWorkModeDriverR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcWorkModeMiddleLeft() {
        int acWorkModeMidLeftR;
        String str = G;
        LogUtil.verbose(str, " getAcWorkModeMiddleLeft.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acWorkModeMidLeftR = carVendorHvacManager.getAcWorkModeMidLeftR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acWorkModeMidLeftR = -1;
            }
            LogUtil.verbose(str, " getAcWorkModeMiddleLeft.out service =" + acWorkModeMidLeftR);
            return acWorkModeMidLeftR;
        }
        LogUtil.verbose(str, " getAcWorkModeMiddleLeft.null");
        acWorkModeMidLeftR = -1;
        LogUtil.verbose(str, " getAcWorkModeMiddleLeft.out service =" + acWorkModeMidLeftR);
        return acWorkModeMidLeftR;
    }

    public int getAcWorkModeMiddleRight() {
        int acWorkModeMidRightR;
        String str = G;
        LogUtil.verbose(str, " getAcWorkModeMiddleRight.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acWorkModeMidRightR = carVendorHvacManager.getAcWorkModeMidRightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acWorkModeMidRightR = -1;
            }
            LogUtil.verbose(str, " getAcWorkModeMiddleRight.out service =" + acWorkModeMidRightR);
            return acWorkModeMidRightR;
        }
        LogUtil.verbose(str, " getAcWorkModeMiddleRight.null");
        acWorkModeMidRightR = -1;
        LogUtil.verbose(str, " getAcWorkModeMiddleRight.out service =" + acWorkModeMidRightR);
        return acWorkModeMidRightR;
    }

    public int getAcWorkModeRear() {
        int acWorkModeRearR;
        String str = G;
        LogUtil.verbose(str, " getAcWorkModeRear.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                acWorkModeRearR = carVendorHvacManager.getAcWorkModeRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acWorkModeRearR = -1;
            }
            LogUtil.verbose(str, " getAcWorkModeRear.out service =" + acWorkModeRearR);
            return acWorkModeRearR;
        }
        LogUtil.verbose(str, " getAcWorkModeRear.null");
        acWorkModeRearR = -1;
        LogUtil.verbose(str, " getAcWorkModeRear.out service =" + acWorkModeRearR);
        return acWorkModeRearR;
    }

    public int getAcbtWindTipsState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acbtWindTips = carVendorHvacManager.getAcbtWindTips();
            LogUtil.verbose(G, "getAcbtWindTipsState=" + acbtWindTips);
            return acbtWindTips;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAirOutletStateCopilot() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int airOutletStateCopilotR = carVendorHvacManager.getAirOutletStateCopilotR();
            LogUtil.verbose(G, "getAirOutletStateCopilot=" + airOutletStateCopilotR);
            return airOutletStateCopilotR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAirOutletStateDriver() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int airOutletStateDriverR = carVendorHvacManager.getAirOutletStateDriverR();
            LogUtil.verbose(G, "getAirOutletStateDriver=" + airOutletStateDriverR);
            return airOutletStateDriverR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getAirOutletValueCopilotLeftHorizontal() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0.0f;
        }
        try {
            float airOutletValueCopilotLeftHorizontalR = carVendorHvacManager.getAirOutletValueCopilotLeftHorizontalR();
            LogUtil.verbose(G, "getAirOutletValueCopilotLeftHorizontal=" + airOutletValueCopilotLeftHorizontalR);
            return airOutletValueCopilotLeftHorizontalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getAirOutletValueCopilotLeftVertical() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0.0f;
        }
        try {
            float airOutletValueCopilotLeftVerticalR = carVendorHvacManager.getAirOutletValueCopilotLeftVerticalR();
            LogUtil.verbose(G, "getAirOutletValueCopilotLeftVertical=" + airOutletValueCopilotLeftVerticalR);
            return airOutletValueCopilotLeftVerticalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getAirOutletValueCopilotRightHorizontal() {
        String str = G;
        LogUtil.verbose(str, "getAirOutletValueCopilotRightHorizontal is execute");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0.0f;
        }
        try {
            float airOutletValueCopilotRightHorizontalR = carVendorHvacManager.getAirOutletValueCopilotRightHorizontalR();
            LogUtil.verbose(str, "getAirOutletValueCopilotRightHorizontal=" + airOutletValueCopilotRightHorizontalR);
            return airOutletValueCopilotRightHorizontalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getAirOutletValueCopilotRightVertical() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0.0f;
        }
        try {
            float airOutletValueCopilotRightVerticalR = carVendorHvacManager.getAirOutletValueCopilotRightVerticalR();
            LogUtil.verbose(G, "getAirOutletValueCopilotRightVertical=" + airOutletValueCopilotRightVerticalR);
            return airOutletValueCopilotRightVerticalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getAirOutletValueDriverLeftHorizontal() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0.0f;
        }
        try {
            float airOutletValueDriverLeftHorizontalR = carVendorHvacManager.getAirOutletValueDriverLeftHorizontalR();
            LogUtil.verbose(G, "getAirOutletValueDriverLeftHorizontal=" + airOutletValueDriverLeftHorizontalR);
            return airOutletValueDriverLeftHorizontalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getAirOutletValueDriverLeftVertical() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0.0f;
        }
        try {
            float airOutletValueDriverLeftVerticalR = carVendorHvacManager.getAirOutletValueDriverLeftVerticalR();
            LogUtil.verbose(G, "getAirOutletValueDriverLeftVertical=" + airOutletValueDriverLeftVerticalR);
            return airOutletValueDriverLeftVerticalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getAirOutletValueDriverRightHorizontal() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0.0f;
        }
        try {
            float airOutletValueDriverRightHorizontalR = carVendorHvacManager.getAirOutletValueDriverRightHorizontalR();
            LogUtil.verbose(G, "getAirOutletValueDriverRightHorizontal=" + airOutletValueDriverRightHorizontalR);
            return airOutletValueDriverRightHorizontalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getAirOutletValueDriverRightVertical() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0.0f;
        }
        try {
            float airOutletValueDriverRightVerticalR = carVendorHvacManager.getAirOutletValueDriverRightVerticalR();
            LogUtil.verbose(G, "getAirOutletValueDriverRightVertical=" + airOutletValueDriverRightVerticalR);
            return airOutletValueDriverRightVerticalR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getAirQualityCtrlMenuState() {
        return 0;
    }

    public void getAllStatus() {
    }

    public int getAutoCleanAirState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int autoCleanAirStateR = carVendorHvacManager.getAutoCleanAirStateR();
            LogUtil.verbose(G, "getAutoCleanAirState=" + autoCleanAirStateR);
            return autoCleanAirStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAvoidBlowPeopleStateCopilot() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int avoidBlowPeopleStateCopilotR = carVendorHvacManager.getAvoidBlowPeopleStateCopilotR();
            LogUtil.verbose(G, "getAvoidBlowPeopleStateCopilot=" + avoidBlowPeopleStateCopilotR);
            return avoidBlowPeopleStateCopilotR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAvoidBlowPeopleStateDriver() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int avoidBlowPeopleStateDriverR = carVendorHvacManager.getAvoidBlowPeopleStateDriverR();
            LogUtil.verbose(G, "getAvoidBlowPeopleStateDriver=" + avoidBlowPeopleStateDriverR);
            return avoidBlowPeopleStateDriverR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBlowPeopleStateCopilot() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int blowPeopleStateCopilotR = carVendorHvacManager.getBlowPeopleStateCopilotR();
            LogUtil.verbose(G, "getBlowPeopleStateCopilot=" + blowPeopleStateCopilotR);
            return blowPeopleStateCopilotR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBlowPeopleStateDriver() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int blowPeopleStateDriverR = carVendorHvacManager.getBlowPeopleStateDriverR();
            LogUtil.verbose(G, "getBlowPeopleStateDriver=" + blowPeopleStateDriverR);
            return blowPeopleStateDriverR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCurFragranceType() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return -1;
        }
        try {
            int curFragranceType = carVendorFragranceManager.getCurFragranceType();
            LogUtil.verbose(G, "getCurFragranceType=" + curFragranceType);
            return curFragranceType;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDefrostRearConfig() {
        String str = G;
        LogUtil.verbose(str, "getDefrostRearConfig");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846594, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDefrostRearConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDevicetype() {
        return 0;
    }

    public int getDriverSeatHeating2aaConfig() {
        int driverSeatHeating2aaConfigR;
        String str = G;
        LogUtil.verbose(str, " getDriverSeatHeating2aaConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                driverSeatHeating2aaConfigR = carVendorHvacManager.getDriverSeatHeating2aaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driverSeatHeating2aaConfigR = -1;
            }
            LogUtil.verbose(str, " getDriverSeatHeating2aaConfig.out=" + driverSeatHeating2aaConfigR);
            return driverSeatHeating2aaConfigR;
        }
        LogUtil.verbose(str, " getDriverSeatHeating2aaConfig.null");
        driverSeatHeating2aaConfigR = -1;
        LogUtil.verbose(str, " getDriverSeatHeating2aaConfig.out=" + driverSeatHeating2aaConfigR);
        return driverSeatHeating2aaConfigR;
    }

    public int getDriverSeatHeating2e4Config() {
        int driverSeatHeating2e4ConfigR;
        String str = G;
        LogUtil.verbose(str, " getDriverSeatHeating2e4Config.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                driverSeatHeating2e4ConfigR = carVendorHvacManager.getDriverSeatHeating2e4ConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driverSeatHeating2e4ConfigR = -1;
            }
            LogUtil.verbose(str, " getDriverSeatHeating2e4Config.out=" + driverSeatHeating2e4ConfigR);
            return driverSeatHeating2e4ConfigR;
        }
        LogUtil.verbose(str, " getDriverSeatHeating2e4Config.null");
        driverSeatHeating2e4ConfigR = -1;
        LogUtil.verbose(str, " getDriverSeatHeating2e4Config.out=" + driverSeatHeating2e4ConfigR);
        return driverSeatHeating2e4ConfigR;
    }

    public int getDriverSeatVentilating2aaConfig() {
        int driverSeatVentilating2aaConfigR;
        String str = G;
        LogUtil.verbose(str, " getDriverSeatVentilating2aaConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                driverSeatVentilating2aaConfigR = carVendorHvacManager.getDriverSeatVentilating2aaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driverSeatVentilating2aaConfigR = -1;
            }
            LogUtil.verbose(str, " getDriverSeatVentilating2aaConfig.out=" + driverSeatVentilating2aaConfigR);
            return driverSeatVentilating2aaConfigR;
        }
        LogUtil.verbose(str, " getDriverSeatVentilating2aaConfig.null");
        driverSeatVentilating2aaConfigR = -1;
        LogUtil.verbose(str, " getDriverSeatVentilating2aaConfig.out=" + driverSeatVentilating2aaConfigR);
        return driverSeatVentilating2aaConfigR;
    }

    public int getDriverSeatVentilating2e4Config() {
        int driverSeatVentilating2e4ConfigR;
        String str = G;
        LogUtil.verbose(str, " getDriverSeatVentilating2e4Config.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                driverSeatVentilating2e4ConfigR = carVendorHvacManager.getDriverSeatVentilating2e4ConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driverSeatVentilating2e4ConfigR = -1;
            }
            LogUtil.verbose(str, " getDriverSeatVentilating2e4Config.out=" + driverSeatVentilating2e4ConfigR);
            return driverSeatVentilating2e4ConfigR;
        }
        LogUtil.verbose(str, " getDriverSeatVentilating2e4Config.null");
        driverSeatVentilating2e4ConfigR = -1;
        LogUtil.verbose(str, " getDriverSeatVentilating2e4Config.out=" + driverSeatVentilating2e4ConfigR);
        return driverSeatVentilating2e4ConfigR;
    }

    public int getElectricDefrostState() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846613, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getAcDefrostOpenStateLeft prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int[] getFeatrueList() {
        return null;
    }

    public int getFragranceConcentrationTypeOne() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return -1;
        }
        try {
            int fragranceConcentrationR = carVendorFragranceManager.getFragranceConcentrationR();
            LogUtil.verbose(G, "getFragranceConcentrationTypeOne=" + fragranceConcentrationR);
            return fragranceConcentrationR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getFragranceConcentrationTypeThree() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return -1;
        }
        try {
            int fragranceConcentrationR = carVendorFragranceManager.getFragranceConcentrationR();
            LogUtil.verbose(G, "getFragranceConcentrationTypeThree=" + fragranceConcentrationR);
            return fragranceConcentrationR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getFragranceConcentrationTypeTwo() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return -1;
        }
        try {
            int fragranceConcentrationR = carVendorFragranceManager.getFragranceConcentrationR();
            LogUtil.verbose(G, "getFragranceConcentrationTypeTwo=" + fragranceConcentrationR);
            return fragranceConcentrationR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getFragranceInstallState(int i) {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager != null) {
            try {
                int supportFragranceTypeThree = i != 1 ? i != 2 ? i != 3 ? 0 : carVendorFragranceManager.getSupportFragranceTypeThree() : carVendorFragranceManager.getSupportFragranceTypeTwo() : carVendorFragranceManager.getSupportFragranceTypeOne();
                LogUtil.verbose(G, "getSupportFragranceType " + supportFragranceTypeThree);
                return supportFragranceTypeThree;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int getFragranceLessAllowanceStateOne() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceLessAllowance = carVendorFragranceManager.getFragranceLessAllowance();
            LogUtil.verbose(G, "getFragranceLessAllowanceStateOne=" + fragranceLessAllowance);
            return fragranceLessAllowance;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceLessAllowanceStateThree() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceLessAllowanceThreeR = carVendorFragranceManager.getFragranceLessAllowanceThreeR();
            LogUtil.verbose(G, "getFragranceLessAllowanceStateThree=" + fragranceLessAllowanceThreeR);
            return fragranceLessAllowanceThreeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceLessAllowanceStateTwo() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceLessAllowanceTwoR = carVendorFragranceManager.getFragranceLessAllowanceTwoR();
            LogUtil.verbose(G, "getFragranceLessAllowanceStateTwo=" + fragranceLessAllowanceTwoR);
            return fragranceLessAllowanceTwoR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceNameOneAb() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceNameOneAbR = carVendorFragranceManager.getFragranceNameOneAbR();
            LogUtil.verbose(G, "getFragranceNameOneAb=" + fragranceNameOneAbR);
            return fragranceNameOneAbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceNameOneBb() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceNameOneBbR = carVendorFragranceManager.getFragranceNameOneBbR();
            LogUtil.verbose(G, "getFragranceNameOneBb=" + fragranceNameOneBbR);
            return fragranceNameOneBbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceNameThreeAb() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceNameThreeAbR = carVendorFragranceManager.getFragranceNameThreeAbR();
            LogUtil.verbose(G, "getFragranceNameThreeAb=" + fragranceNameThreeAbR);
            return fragranceNameThreeAbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceNameThreeBb() {
        String str = G;
        LogUtil.verbose(str, "Service getFragranceNameThreeBb");
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceNameThreeBbR = carVendorFragranceManager.getFragranceNameThreeBbR();
            LogUtil.verbose(str, "getFragranceNameThreeBb=" + fragranceNameThreeBbR);
            return fragranceNameThreeBbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceNameTwoAb() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceNameTwoAbR = carVendorFragranceManager.getFragranceNameTwoAbR();
            LogUtil.verbose(G, "getFragranceNameTwoAb=" + fragranceNameTwoAbR);
            return fragranceNameTwoAbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceNameTwoBb() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            int fragranceNameTwoBbR = carVendorFragranceManager.getFragranceNameTwoBbR();
            LogUtil.verbose(G, "getFragranceNameTwoBb=" + fragranceNameTwoBbR);
            return fragranceNameTwoBbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFragranceOnlineState() {
        return 0;
    }

    public int getFragranceResidueOne() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return -1;
        }
        try {
            int fragranceResidueOne = carVendorFragranceManager.getFragranceResidueOne();
            LogUtil.verbose(G, "getFragranceResidueOne=" + fragranceResidueOne);
            return fragranceResidueOne;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getFragranceResidueThree() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return -1;
        }
        try {
            int fragranceResidueThree = carVendorFragranceManager.getFragranceResidueThree();
            LogUtil.verbose(G, "getFragranceResidueThree=" + fragranceResidueThree);
            return fragranceResidueThree;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getFragranceResidueTwo() {
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return -1;
        }
        try {
            int fragranceResidueTwo = carVendorFragranceManager.getFragranceResidueTwo();
            LogUtil.verbose(G, "getFragranceResidueTwo=" + fragranceResidueTwo);
            return fragranceResidueTwo;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getFragranceSystemState() {
        return 0;
    }

    public int getFreeWindBtnStateCopilot() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int freeWindBtnStateCopilotR = carVendorHvacManager.getFreeWindBtnStateCopilotR();
            LogUtil.verbose(G, "getFreeWindBtnStateCopilot=" + freeWindBtnStateCopilotR);
            return freeWindBtnStateCopilotR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFreeWindBtnStateDriver() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int freeWindBtnStateDriverR = carVendorHvacManager.getFreeWindBtnStateDriverR();
            LogUtil.verbose(G, "getFreeWindBtnStateDriver=" + freeWindBtnStateDriverR);
            return freeWindBtnStateDriverR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFrontRowAirOutletState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int frontRowAirOutletStateR = carVendorHvacManager.getFrontRowAirOutletStateR();
            LogUtil.verbose(G, "getFrontRowAirOutletState=" + frontRowAirOutletStateR);
            return frontRowAirOutletStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getGetPermission() {
        return "";
    }

    public int getGreenSystemState() {
        return 0;
    }

    public int getHeatPumpCompressorWorkMode() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acHeatPumpCompressorMode = carVendorHvacManager.getAcHeatPumpCompressorMode();
            LogUtil.verbose(G, "getHeatPumpCompressorWorkMode=" + acHeatPumpCompressorMode);
            return acHeatPumpCompressorMode;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHighTempAntivirusCountDown() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int highTempAntivirusCountDown = carVendorHvacManager.getHighTempAntivirusCountDown();
            LogUtil.verbose(G, "getHighTempAntivirusCountDown=" + highTempAntivirusCountDown);
            return highTempAntivirusCountDown;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHighTempAntivirusState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int highTempAntivirusState = carVendorHvacManager.getHighTempAntivirusState();
            LogUtil.verbose(G, "getHighTempAntivirusState=" + highTempAntivirusState);
            return highTempAntivirusState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentControlState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int intelligentControlStateR = carVendorHvacManager.getIntelligentControlStateR();
            LogUtil.verbose(G, "getIntelligentControlState=" + intelligentControlStateR);
            return intelligentControlStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLrSeatHeating2aaConfig() {
        int lrSeatHeating2aaConfigR;
        String str = G;
        LogUtil.verbose(str, " getLrSeatHeating2aaConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                lrSeatHeating2aaConfigR = carVendorHvacManager.getLrSeatHeating2aaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrSeatHeating2aaConfigR = -1;
            }
            LogUtil.verbose(str, " getLrSeatHeating2aaConfig.out=" + lrSeatHeating2aaConfigR);
            return lrSeatHeating2aaConfigR;
        }
        LogUtil.verbose(str, " getLrSeatHeating2aaConfig.null");
        lrSeatHeating2aaConfigR = -1;
        LogUtil.verbose(str, " getLrSeatHeating2aaConfig.out=" + lrSeatHeating2aaConfigR);
        return lrSeatHeating2aaConfigR;
    }

    public int getLrSeatHeating349Config() {
        int lrSeatHeating349ConfigR;
        String str = G;
        LogUtil.verbose(str, " getLrSeatHeating349Config.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                lrSeatHeating349ConfigR = carVendorHvacManager.getLrSeatHeating349ConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrSeatHeating349ConfigR = -1;
            }
            LogUtil.verbose(str, " getLrSeatHeating349Config.out=" + lrSeatHeating349ConfigR);
            return lrSeatHeating349ConfigR;
        }
        LogUtil.verbose(str, " getLrSeatHeating349Config.null");
        lrSeatHeating349ConfigR = -1;
        LogUtil.verbose(str, " getLrSeatHeating349Config.out=" + lrSeatHeating349ConfigR);
        return lrSeatHeating349ConfigR;
    }

    public int getLrSeatHeating35dConfig() {
        int lrSeatHeating35dConfigR;
        String str = G;
        LogUtil.verbose(str, " getLrSeatHeating35dConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                lrSeatHeating35dConfigR = carVendorHvacManager.getLrSeatHeating35dConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrSeatHeating35dConfigR = -1;
            }
            LogUtil.verbose(str, " getLrSeatHeating35dConfig.out=" + lrSeatHeating35dConfigR);
            return lrSeatHeating35dConfigR;
        }
        LogUtil.verbose(str, " getLrSeatHeating35dConfig.null");
        lrSeatHeating35dConfigR = -1;
        LogUtil.verbose(str, " getLrSeatHeating35dConfig.out=" + lrSeatHeating35dConfigR);
        return lrSeatHeating35dConfigR;
    }

    public int getLrSeatVentilating2aaConfig() {
        int lrSeatVentilating2aaConfigR;
        String str = G;
        LogUtil.verbose(str, " getLrSeatVentilating2aaConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                lrSeatVentilating2aaConfigR = carVendorHvacManager.getLrSeatVentilating2aaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrSeatVentilating2aaConfigR = -1;
            }
            LogUtil.verbose(str, " getLrSeatVentilating2aaConfig.out=" + lrSeatVentilating2aaConfigR);
            return lrSeatVentilating2aaConfigR;
        }
        LogUtil.verbose(str, " getLrSeatVentilating2aaConfig.null");
        lrSeatVentilating2aaConfigR = -1;
        LogUtil.verbose(str, " getLrSeatVentilating2aaConfig.out=" + lrSeatVentilating2aaConfigR);
        return lrSeatVentilating2aaConfigR;
    }

    public int getLrSeatVentilating349Config() {
        int lrSeatVentilating349ConfigR;
        String str = G;
        LogUtil.verbose(str, " getLrSeatVentilating349Config.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                lrSeatVentilating349ConfigR = carVendorHvacManager.getLrSeatVentilating349ConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrSeatVentilating349ConfigR = -1;
            }
            LogUtil.verbose(str, " getLrSeatVentilating349Config.out=" + lrSeatVentilating349ConfigR);
            return lrSeatVentilating349ConfigR;
        }
        LogUtil.verbose(str, " getLrSeatVentilating349Config.null");
        lrSeatVentilating349ConfigR = -1;
        LogUtil.verbose(str, " getLrSeatVentilating349Config.out=" + lrSeatVentilating349ConfigR);
        return lrSeatVentilating349ConfigR;
    }

    public int getLrSeatVentilating35dConfig() {
        int lrSeatVentilating35dConfigR;
        String str = G;
        LogUtil.verbose(str, " getLrSeatVentilating35dConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                lrSeatVentilating35dConfigR = carVendorHvacManager.getLrSeatVentilating35dConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrSeatVentilating35dConfigR = -1;
            }
            LogUtil.verbose(str, " getLrSeatVentilating35dConfig.out=" + lrSeatVentilating35dConfigR);
            return lrSeatVentilating35dConfigR;
        }
        LogUtil.verbose(str, " getLrSeatVentilating35dConfig.null");
        lrSeatVentilating35dConfigR = -1;
        LogUtil.verbose(str, " getLrSeatVentilating35dConfig.out=" + lrSeatVentilating35dConfigR);
        return lrSeatVentilating35dConfigR;
    }

    public int getMaxAcState() {
        return 0;
    }

    public int getNegativeIonState() {
        return 0;
    }

    public int getPM2p5AnionState() {
        String str = G;
        LogUtil.verbose(str, "getPM2p5AnionState");
        CarVendorPm2p5Manager carVendorPm2p5Manager = this.f78b;
        if (carVendorPm2p5Manager == null) {
            return 0;
        }
        try {
            int pm2p5AnionState = carVendorPm2p5Manager.getPm2p5AnionState();
            LogUtil.verbose(str, "getPM2p5AnionState=" + pm2p5AnionState);
            return pm2p5AnionState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int[] getPM2p5CheckState() {
        String str = G;
        int[] iArr = new int[2];
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557854722, 0);
        if (carPropertyValueA != null) {
            LogUtil.verbose(str, "getPm2p5CheckStateIn prop value " + carPropertyValueA.getValue());
            iArr[0] = carPropertyValueA != null ? ((Integer) carPropertyValueA.getValue()).intValue() : 0;
        } else {
            iArr[0] = 0;
        }
        CarPropertyValue carPropertyValueA2 = com.ts.appservice.caradapter.e.c.a(557854723, 0);
        if (carPropertyValueA2 != null) {
            LogUtil.verbose(str, "getPm2p5CheckStateOut prop value " + carPropertyValueA2.getValue());
            iArr[1] = carPropertyValueA2 != null ? ((Integer) carPropertyValueA2.getValue()).intValue() : 0;
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    public int getPM2p5DualChannelDetectState() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557854720, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getPM2p5DualChannelDetectState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int[] getPM2p5Level() {
        int[] iArr = new int[2];
        CarVendorPm2p5Manager carVendorPm2p5Manager = this.f78b;
        if (carVendorPm2p5Manager != null) {
            try {
                iArr[0] = carVendorPm2p5Manager.getPm2p5LevelIn();
                iArr[1] = this.f78b.getPm2p5LevelOut();
                LogUtil.verbose(G, "getPM2p5Level=" + Arrays.toString(iArr));
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return iArr;
    }

    public int getPM2p5OnlineState() {
        return 0;
    }

    public int[] getPM2p5Value() {
        String str = G;
        int[] iArr = new int[2];
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557854725, 0);
        if (carPropertyValueA != null) {
            LogUtil.verbose(str, "getPm2p5ValueIn prop value " + carPropertyValueA.getValue());
            iArr[0] = carPropertyValueA != null ? ((Integer) carPropertyValueA.getValue()).intValue() : 0;
        } else {
            iArr[0] = 0;
        }
        CarPropertyValue carPropertyValueA2 = com.ts.appservice.caradapter.e.c.a(557854726, 0);
        if (carPropertyValueA2 != null) {
            LogUtil.verbose(str, "getPm2p5ValueOut prop value " + carPropertyValueA2.getValue());
            iArr[1] = carPropertyValueA2 != null ? ((Integer) carPropertyValueA2.getValue()).intValue() : 0;
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    public int getPM2p5WarningInfo() {
        CarVendorPm2p5Manager carVendorPm2p5Manager = this.f78b;
        if (carVendorPm2p5Manager == null) {
            return 0;
        }
        try {
            int pm2p5WarningInfo = carVendorPm2p5Manager.getPm2p5WarningInfo();
            LogUtil.verbose(G, "getPM2p5WarningInfo=" + pm2p5WarningInfo);
            return pm2p5WarningInfo;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerSeatHeating2aaConfig() {
        int passengerSeatHeating2aaConfigR;
        String str = G;
        LogUtil.verbose(str, " getPassengerSeatHeating2aaConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                passengerSeatHeating2aaConfigR = carVendorHvacManager.getPassengerSeatHeating2aaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerSeatHeating2aaConfigR = -1;
            }
            LogUtil.verbose(str, " getPassengerSeatHeating2aaConfig.out=" + passengerSeatHeating2aaConfigR);
            return passengerSeatHeating2aaConfigR;
        }
        LogUtil.verbose(str, " getPassengerSeatHeating2aaConfig.null");
        passengerSeatHeating2aaConfigR = -1;
        LogUtil.verbose(str, " getPassengerSeatHeating2aaConfig.out=" + passengerSeatHeating2aaConfigR);
        return passengerSeatHeating2aaConfigR;
    }

    public int getPassengerSeatHeating2ffConfig() {
        int passengerSeatHeating2ffConfigR;
        String str = G;
        LogUtil.verbose(str, " getPassengerSeatHeating2ffConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                passengerSeatHeating2ffConfigR = carVendorHvacManager.getPassengerSeatHeating2ffConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerSeatHeating2ffConfigR = -1;
            }
            LogUtil.verbose(str, " getPassengerSeatHeating2ffConfig.out=" + passengerSeatHeating2ffConfigR);
            return passengerSeatHeating2ffConfigR;
        }
        LogUtil.verbose(str, " getPassengerSeatHeating2ffConfig.null");
        passengerSeatHeating2ffConfigR = -1;
        LogUtil.verbose(str, " getPassengerSeatHeating2ffConfig.out=" + passengerSeatHeating2ffConfigR);
        return passengerSeatHeating2ffConfigR;
    }

    public int getPassengerSeatHeating41cConfig() {
        int passengerSeatHeating41cConfigR;
        String str = G;
        LogUtil.verbose(str, " getPassengerSeatHeating41cConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                passengerSeatHeating41cConfigR = carVendorHvacManager.getPassengerSeatHeating41cConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerSeatHeating41cConfigR = -1;
            }
            LogUtil.verbose(str, " getPassengerSeatHeating41cConfig.out=" + passengerSeatHeating41cConfigR);
            return passengerSeatHeating41cConfigR;
        }
        LogUtil.verbose(str, " getPassengerSeatHeating41cConfig.null");
        passengerSeatHeating41cConfigR = -1;
        LogUtil.verbose(str, " getPassengerSeatHeating41cConfig.out=" + passengerSeatHeating41cConfigR);
        return passengerSeatHeating41cConfigR;
    }

    public int getPassengerSeatVentilating2aaConfig() {
        int passengerSeatVentilating2aaConfigR;
        String str = G;
        LogUtil.verbose(str, " getPassengerSeatVentilating2aaConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                passengerSeatVentilating2aaConfigR = carVendorHvacManager.getPassengerSeatVentilating2aaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerSeatVentilating2aaConfigR = -1;
            }
            LogUtil.verbose(str, " getPassengerSeatVentilating2aaConfig.out=" + passengerSeatVentilating2aaConfigR);
            return passengerSeatVentilating2aaConfigR;
        }
        LogUtil.verbose(str, " getPassengerSeatVentilating2aaConfig.null");
        passengerSeatVentilating2aaConfigR = -1;
        LogUtil.verbose(str, " getPassengerSeatVentilating2aaConfig.out=" + passengerSeatVentilating2aaConfigR);
        return passengerSeatVentilating2aaConfigR;
    }

    public int getPassengerSeatVentilating2ffConfig() {
        int passengerSeatVentilating2ffConfigR;
        String str = G;
        LogUtil.verbose(str, " getPassengerSeatVentilating2ffConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                passengerSeatVentilating2ffConfigR = carVendorHvacManager.getPassengerSeatVentilating2ffConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerSeatVentilating2ffConfigR = -1;
            }
            LogUtil.verbose(str, " getPassengerSeatVentilating2ffConfig.out=" + passengerSeatVentilating2ffConfigR);
            return passengerSeatVentilating2ffConfigR;
        }
        LogUtil.verbose(str, " getPassengerSeatVentilating2ffConfig.null");
        passengerSeatVentilating2ffConfigR = -1;
        LogUtil.verbose(str, " getPassengerSeatVentilating2ffConfig.out=" + passengerSeatVentilating2ffConfigR);
        return passengerSeatVentilating2ffConfigR;
    }

    public int getPassengerSeatVentilating41cConfig() {
        int passengerSeatVentilating41cConfigR;
        String str = G;
        LogUtil.verbose(str, " getPassengerSeatVentilating41cConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                passengerSeatVentilating41cConfigR = carVendorHvacManager.getPassengerSeatVentilating41cConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerSeatVentilating41cConfigR = -1;
            }
            LogUtil.verbose(str, " getPassengerSeatVentilating41cConfig.out=" + passengerSeatVentilating41cConfigR);
            return passengerSeatVentilating41cConfigR;
        }
        LogUtil.verbose(str, " getPassengerSeatVentilating41cConfig.null");
        passengerSeatVentilating41cConfigR = -1;
        LogUtil.verbose(str, " getPassengerSeatVentilating41cConfig.out=" + passengerSeatVentilating41cConfigR);
        return passengerSeatVentilating41cConfigR;
    }

    public int getQuickCleanAirState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int quickCleanAirStateR = carVendorHvacManager.getQuickCleanAirStateR();
            LogUtil.verbose(G, "getQuickCleanAirState=" + quickCleanAirStateR);
            return quickCleanAirStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getQuickCleanTip() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int quickCleanTipR = carVendorHvacManager.getQuickCleanTipR();
            LogUtil.verbose(G, "getQuickCleanTip=" + quickCleanTipR);
            return quickCleanTipR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearACkeyLockState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acRearLockR = carVendorHvacManager.getAcRearLockR();
            LogUtil.verbose(G, "getRearACkeyLockState=" + acRearLockR);
            return acRearLockR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearAcControlMode() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acRearAcCtrlModeR = carVendorHvacManager.getAcRearAcCtrlModeR();
            LogUtil.verbose(G, "getRearAcControlMode=" + acRearAcCtrlModeR);
            return acRearAcCtrlModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearAcLockState() {
        return 0;
    }

    public int getRearAcMaxWindLevel() {
        String str = G;
        LogUtil.verbose(str, "getRearAcMaxWindLevel");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846532, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearAcMaxWindLevel prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearAcStartState() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acRearAcState = carVendorHvacManager.getAcRearAcState();
            LogUtil.verbose(G, "getRearAcStartState=" + acRearAcState);
            return acRearAcState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearAcWindLevel() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acRearWindLevelR = carVendorHvacManager.getAcRearWindLevelR();
            LogUtil.verbose(G, "getRearAcWindLevel=" + acRearWindLevelR);
            return acRearWindLevelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearAcWindMode() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int acRearWindModeR = carVendorHvacManager.getAcRearWindModeR();
            LogUtil.verbose(G, "getRearAcWindMode=" + acRearWindModeR);
            return acRearWindModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRrSeatHeating21dConfig() {
        int rrSeatHeating21dConfigR;
        String str = G;
        LogUtil.verbose(str, " getRrSeatHeating21dConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                rrSeatHeating21dConfigR = carVendorHvacManager.getRrSeatHeating21dConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrSeatHeating21dConfigR = -1;
            }
            LogUtil.verbose(str, " getRrSeatHeating21dConfig.out=" + rrSeatHeating21dConfigR);
            return rrSeatHeating21dConfigR;
        }
        LogUtil.verbose(str, " getRrSeatHeating21dConfig.null");
        rrSeatHeating21dConfigR = -1;
        LogUtil.verbose(str, " getRrSeatHeating21dConfig.out=" + rrSeatHeating21dConfigR);
        return rrSeatHeating21dConfigR;
    }

    public int getRrSeatHeating2aaConfig() {
        int rrSeatHeating2aaConfigR;
        String str = G;
        LogUtil.verbose(str, " getRrSeatHeating2aaConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                rrSeatHeating2aaConfigR = carVendorHvacManager.getRrSeatHeating2aaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrSeatHeating2aaConfigR = -1;
            }
            LogUtil.verbose(str, " getRrSeatHeating2aaConfig.out=" + rrSeatHeating2aaConfigR);
            return rrSeatHeating2aaConfigR;
        }
        LogUtil.verbose(str, " getRrSeatHeating2aaConfig.null");
        rrSeatHeating2aaConfigR = -1;
        LogUtil.verbose(str, " getRrSeatHeating2aaConfig.out=" + rrSeatHeating2aaConfigR);
        return rrSeatHeating2aaConfigR;
    }

    public int getRrSeatHeating349Config() {
        int rrSeatHeating349ConfigR;
        String str = G;
        LogUtil.verbose(str, " getRrSeatHeating349Config.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                rrSeatHeating349ConfigR = carVendorHvacManager.getRrSeatHeating349ConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrSeatHeating349ConfigR = -1;
            }
            LogUtil.verbose(str, " getRrSeatHeating349Config.out=" + rrSeatHeating349ConfigR);
            return rrSeatHeating349ConfigR;
        }
        LogUtil.verbose(str, " getRrSeatHeating349Config.null");
        rrSeatHeating349ConfigR = -1;
        LogUtil.verbose(str, " getRrSeatHeating349Config.out=" + rrSeatHeating349ConfigR);
        return rrSeatHeating349ConfigR;
    }

    public int getRrSeatHeating363Config() {
        int rrSeatHeating363ConfigR;
        String str = G;
        LogUtil.verbose(str, " getRrSeatHeating363Config.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                rrSeatHeating363ConfigR = carVendorHvacManager.getRrSeatHeating363ConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrSeatHeating363ConfigR = -1;
            }
            LogUtil.verbose(str, " getRrSeatHeating363Config.out=" + rrSeatHeating363ConfigR);
            return rrSeatHeating363ConfigR;
        }
        LogUtil.verbose(str, " getRrSeatHeating363Config.null");
        rrSeatHeating363ConfigR = -1;
        LogUtil.verbose(str, " getRrSeatHeating363Config.out=" + rrSeatHeating363ConfigR);
        return rrSeatHeating363ConfigR;
    }

    public int getRrSeatVentilating21dConfig() {
        int rrSeatVentilating21dConfigR;
        String str = G;
        LogUtil.verbose(str, " getRrSeatVentilating21dConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                rrSeatVentilating21dConfigR = carVendorHvacManager.getRrSeatVentilating21dConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrSeatVentilating21dConfigR = -1;
            }
            LogUtil.verbose(str, " getRrSeatVentilating21dConfig.out=" + rrSeatVentilating21dConfigR);
            return rrSeatVentilating21dConfigR;
        }
        LogUtil.verbose(str, " getRrSeatVentilating21dConfig.null");
        rrSeatVentilating21dConfigR = -1;
        LogUtil.verbose(str, " getRrSeatVentilating21dConfig.out=" + rrSeatVentilating21dConfigR);
        return rrSeatVentilating21dConfigR;
    }

    public int getRrSeatVentilating2aaConfig() {
        int rrSeatVentilating2aaConfigR;
        String str = G;
        LogUtil.verbose(str, " getRrSeatVentilating2aaConfig.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                rrSeatVentilating2aaConfigR = carVendorHvacManager.getRrSeatVentilating2aaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrSeatVentilating2aaConfigR = -1;
            }
            LogUtil.verbose(str, " getRrSeatVentilating2aaConfig.out=" + rrSeatVentilating2aaConfigR);
            return rrSeatVentilating2aaConfigR;
        }
        LogUtil.verbose(str, " getRrSeatVentilating2aaConfig.null");
        rrSeatVentilating2aaConfigR = -1;
        LogUtil.verbose(str, " getRrSeatVentilating2aaConfig.out=" + rrSeatVentilating2aaConfigR);
        return rrSeatVentilating2aaConfigR;
    }

    public int getRrSeatVentilating349Config() {
        int rrSeatVentilating349ConfigR;
        String str = G;
        LogUtil.verbose(str, " getRrSeatVentilating349Config.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                rrSeatVentilating349ConfigR = carVendorHvacManager.getRrSeatVentilating349ConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrSeatVentilating349ConfigR = -1;
            }
            LogUtil.verbose(str, " getRrSeatVentilating349Config.out=" + rrSeatVentilating349ConfigR);
            return rrSeatVentilating349ConfigR;
        }
        LogUtil.verbose(str, " getRrSeatVentilating349Config.null");
        rrSeatVentilating349ConfigR = -1;
        LogUtil.verbose(str, " getRrSeatVentilating349Config.out=" + rrSeatVentilating349ConfigR);
        return rrSeatVentilating349ConfigR;
    }

    public int getRrSeatVentilating363Config() {
        int rrSeatVentilating363ConfigR;
        String str = G;
        LogUtil.verbose(str, " getRrSeatVentilating363Config.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                rrSeatVentilating363ConfigR = carVendorHvacManager.getRrSeatVentilating363ConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrSeatVentilating363ConfigR = -1;
            }
            LogUtil.verbose(str, " getRrSeatVentilating363Config.out=" + rrSeatVentilating363ConfigR);
            return rrSeatVentilating363ConfigR;
        }
        LogUtil.verbose(str, " getRrSeatVentilating363Config.null");
        rrSeatVentilating363ConfigR = -1;
        LogUtil.verbose(str, " getRrSeatVentilating363Config.out=" + rrSeatVentilating363ConfigR);
        return rrSeatVentilating363ConfigR;
    }

    public String getSetPermission() {
        return "";
    }

    public int getSweepBlowStateCopilot() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int sweepBlowStateCopilotR = carVendorHvacManager.getSweepBlowStateCopilotR();
            LogUtil.verbose(G, "getSweepBlowStateCopilot=" + sweepBlowStateCopilotR);
            return sweepBlowStateCopilotR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSweepBlowStateDriver() {
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            int sweepBlowStateDriverR = carVendorHvacManager.getSweepBlowStateDriverR();
            LogUtil.verbose(G, "getSweepBlowStateDriver=" + sweepBlowStateDriverR);
            return sweepBlowStateDriverR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTempRegulatingAccuracy() {
        String str = G;
        LogUtil.verbose(str, " getTempRegulatingAccuracy.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846667, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTempRegulatingAccuracy prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTemperatureUnit() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846528, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getTemperatureUnit prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTempratureDeputy() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846603, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getTempratureDeputy prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTempratureMain() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846563, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getTempratureMain prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTempratureOut() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846555, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getTempratureOut prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTempratureRear() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846584, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(G, "getTempratureRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getType() {
        return 0;
    }

    public int getVoiceCmdResult() {
        return 0;
    }

    public int hasFeature(String str) {
        return 0;
    }

    public void n() {
        s();
        com.ts.appservice.caradapter.a.h hVar = this.d;
        if (hVar != null) {
            this.D = false;
            hVar.g();
        }
    }

    public void o(CarVendorHvacManager carVendorHvacManager, CarVendorPm2p5Manager carVendorPm2p5Manager, CarVendorFragranceManager carVendorFragranceManager) {
        this.f77a = carVendorHvacManager;
        this.f78b = carVendorPm2p5Manager;
        this.c = carVendorFragranceManager;
        try {
            if (this.F == null) {
                this.F = new a(this.E.getLooper());
                LogUtil.info(G, "initHvacManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.h hVar = this.d;
        if (hVar != null) {
            this.D = true;
            hVar.l();
        }
    }

    public int qurAcAllStatus() {
        return 0;
    }

    public int reset(int i, int i2) {
        return 0;
    }

    public int setAcAntibacterialControlState(int i) {
        LogUtil.verbose(G, "setAcAntibacterialControlState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcAntibacterialControlStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcCompressorMode(int i, int i2) {
        LogUtil.verbose(G, "setAcCompressorMode setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcTurnOn(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcCompressorState(int i) {
        return 0;
    }

    public int setAcControlMode(int i, int i2) {
        LogUtil.verbose(G, "setAcControlMode setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcCtrlModeS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcCycleMode(int i, int i2) {
        LogUtil.verbose(G, "setAcCycleMode setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcCircleKey(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcDefrostStateFront(int i) {
        LogUtil.verbose(G, "setAcDefrostStateFront=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcDefrostS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcDefrostStateRear(int i) {
        LogUtil.verbose(G, "setAcDefrostStateRear=" + i);
        return 0;
    }

    public int setAcHeatingState(int i) {
        int i2;
        String str = G;
        LogUtil.verbose(str, " setAcHeatingState.in");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            try {
                carVendorHvacManager.setAcHeatingStateS(i);
                i2 = 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i2 = 0;
            }
            LogUtil.verbose(str, " setAcHeatingState.out=" + i2);
            return i2;
        }
        LogUtil.verbose(str, " setAcHeatingState.null == mHvacManager");
        i2 = 0;
        LogUtil.verbose(str, " setAcHeatingState.out=" + i2);
        return i2;
    }

    public int setAcMaxCoolingState(int i) {
        LogUtil.verbose(G, "setAcMaxCoolingState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcMaxCoolingStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcRearPanelLockState(int i) {
        LogUtil.verbose(G, "setAcRearPanelLockState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRearPanelLockS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcRemoteCtrlTime(int i) {
        LogUtil.verbose(G, "setAcRemoteCtrlTime=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRemoteCtrlTimeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcTemperatureControlMode(int i, int i2) {
        LogUtil.verbose(G, "setAcTemperatureControlMode setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcDualMode(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcTemperatureDeputy(int i) {
        LogUtil.verbose(G, "setAcTemperatureDeputy=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcControllerPassengerTempS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcTemperatureMain(int i) {
        LogUtil.verbose(G, "setAcTemperatureMain=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcControllerDriverTempS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcTemperatureMainDeputy(int i) {
        LogUtil.verbose(G, "setAcTemperatureMainDeputy=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcControllerDriverTempS(i);
            this.f77a.setAcControllerPassengerTempS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcTemperatureRear(int i) {
        LogUtil.verbose(G, "setAcTemperatureRear=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRearTempS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcTuyereSwitchStateDeputyLeft(int i) {
        int i2;
        String str = G;
        LogUtil.verbose(str, " setAcTuyereSwitchStateDeputyLeft.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            i2 = 1;
            try {
                carVendorHvacManager.setAcTuyereSwitchStateCopilotLeftS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setAcTuyereSwitchStateDeputyLeft.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setAcTuyereSwitchStateDeputyLeft.out service =" + i2);
        return i2;
    }

    public int setAcTuyereSwitchStateDeputyRight(int i) {
        int i2;
        String str = G;
        LogUtil.verbose(str, " setAcTuyereSwitchStateDeputyRight.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            i2 = 1;
            try {
                carVendorHvacManager.setAcTuyereSwitchStateCopilotRightS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setAcTuyereSwitchStateDeputyRight.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setAcTuyereSwitchStateDeputyRight.out service =" + i2);
        return i2;
    }

    public int setAcTuyereSwitchStateMainLeft(int i) {
        int i2;
        String str = G;
        LogUtil.verbose(str, " setAcTuyereSwitchStateMainLeft.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            i2 = 1;
            try {
                carVendorHvacManager.setAcTuyereSwitchStateMainLeftS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setAcTuyereSwitchStateMainLeft.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setAcTuyereSwitchStateMainLeft.out service =" + i2);
        return i2;
    }

    public int setAcTuyereSwitchStateMainRight(int i) {
        int i2;
        String str = G;
        LogUtil.verbose(str, " setAcTuyereSwitchStateMainRight.in service");
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager != null) {
            i2 = 1;
            try {
                carVendorHvacManager.setAcTuyereSwitchStateMainRightS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setAcTuyereSwitchStateMainRight.null");
            i2 = 0;
        }
        LogUtil.verbose(str, " setAcTuyereSwitchStateMainRight.out service =" + i2);
        return i2;
    }

    public int setAcVentilationState(int i, int i2) {
        LogUtil.verbose(G, "setAcVentilationState setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcNaturalWindS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcWarmState(int i) {
        return 0;
    }

    public int setAcWindLevel(int i, int i2) {
        LogUtil.verbose(G, "setAcWindLevel setSource:" + i + "  level:" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcFrontWindLevelS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcWindMode(int i, int i2) {
        LogUtil.verbose(G, "setAcWindMode setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcWindModeS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletStateCopilot(int i) {
        LogUtil.verbose(G, "setAirOutletStateCopilot=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletStateCopilotS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletStateDriver(int i) {
        LogUtil.verbose(G, "setAirOutletStateDriver=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletStateDriverS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletValueCopilotLeftHorizontal(int i) {
        LogUtil.verbose(G, "setAirOutletValueCopilotLeftHorizontal=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletValueCopilotLeftHorizontalS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletValueCopilotLeftVertical(int i) {
        LogUtil.verbose(G, "setAirOutletValueCopilotLeftVertical=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletValueCopilotLeftVerticalS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletValueCopilotRightHorizontal(int i) {
        LogUtil.verbose(G, "setAirOutletValueCopilotRightHorizontal=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletValueCopilotRightHorizontalS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletValueCopilotRightVertical(int i) {
        LogUtil.verbose(G, "setAirOutletValueCopilotRightVertical=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletValueCopilotRightVerticalS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletValueDriverLeftHorizontal(int i) {
        LogUtil.verbose(G, "setAirOutletValueDriverLeftHorizontal=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletValueDriverLeftHorizontalS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletValueDriverLeftVertical(int i) {
        LogUtil.verbose(G, "setAirOutletValueDriverLeftVertical=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletValueDriverLeftVerticalS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletValueDriverRightHorizontal(int i) {
        LogUtil.verbose(G, "setAirOutletValueDriverRightHorizontal=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletValueDriverRightHorizontalS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAirOutletValueDriverRightVertical(int i) {
        LogUtil.verbose(G, "setAirOutletValueDriverRightVertical=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAirOutletValueDriverRightVerticalS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setAllStatus() {
    }

    public int setAutoCleanAirState(int i) {
        LogUtil.verbose(G, "setAutoCleanAirState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAutoCleanAirStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAvoidBlowPeopleStateCopilot(int i) {
        LogUtil.verbose(G, "setAvoidBlowPeopleStateCopilot=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAvoidBlowPeopleStateCopilotS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAvoidBlowPeopleStateDriver(int i) {
        LogUtil.verbose(G, "setAvoidBlowPeopleStateDriver=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAvoidBlowPeopleStateDriverS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBlowPeopleStateCopilot(int i) {
        LogUtil.verbose(G, "setBlowPeopleStateCopilot=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setBlowPeopleStateCopilotS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBlowPeopleStateDriver(int i) {
        LogUtil.verbose(G, "setBlowPeopleStateDriver=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setBlowPeopleStateDriverS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setConstantTemperature(int i) {
        LogUtil.verbose(G, "setConstantTemperature=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setConstantTemperatureS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setElectricDefrostState(int i) {
        LogUtil.verbose(G, "setElectricDefrostState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcMediaDefrostButton(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFragranceConcentration(int i) {
        LogUtil.verbose(G, "setFragranceConcentration=" + i);
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            carVendorFragranceManager.setFragranceConcentrationS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFragranceType(int i) {
        LogUtil.verbose(G, "setFragranceType=" + i);
        CarVendorFragranceManager carVendorFragranceManager = this.c;
        if (carVendorFragranceManager == null) {
            return 0;
        }
        try {
            carVendorFragranceManager.setFragranceTypeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFreeWindBtnStateCopilot(int i) {
        LogUtil.verbose(G, "setFreeWindBtnStateCopilot=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setFreeWindBtnStateCopilotS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFreeWindBtnStateDriver(int i) {
        LogUtil.verbose(G, "setFreeWindBtnStateDriver=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setFreeWindBtnStateDriverS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFrontRowAirOutletState(int i) {
        LogUtil.verbose(G, "setFrontRowAirOutletState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setFrontRowAirOutletStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIntelligentControlState(int i) {
        LogUtil.verbose(G, "setIntelligentControlState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setIntelligentControlStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMaxAcState(int i) {
        return 0;
    }

    public int setNegativeIonState(int i) {
        return 0;
    }

    public int setPM2p5AnionDetectState(int i) {
        LogUtil.verbose(G, "setPM2p5AnionDetectState=" + i);
        CarVendorPm2p5Manager carVendorPm2p5Manager = this.f78b;
        if (carVendorPm2p5Manager == null) {
            return 0;
        }
        try {
            carVendorPm2p5Manager.setPm2p5AnionDetectState(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPM2p5CheckState(int i, int i2) {
        LogUtil.verbose(G, "setPM2p5CheckState area=" + i + ", state=" + i2);
        CarVendorPm2p5Manager carVendorPm2p5Manager = this.f78b;
        if (carVendorPm2p5Manager == null) {
            return 0;
        }
        try {
            carVendorPm2p5Manager.setPm2p5CheckState(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPM2p5DualChannelDetectState(int i) {
        LogUtil.verbose(G, "setPM2p5DualChannelDetectState=" + i);
        CarVendorPm2p5Manager carVendorPm2p5Manager = this.f78b;
        if (carVendorPm2p5Manager == null) {
            return 0;
        }
        try {
            carVendorPm2p5Manager.setPm2p5DualChannelDetectStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setQuickCleanAirState(int i) {
        LogUtil.verbose(G, "setQuickCleanAirState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setQuickCleanAirStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearAcControlMode(int i, int i2) {
        LogUtil.verbose(G, "setRearAcControlMode setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRearAcCtrlModeS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearAcKeyLockState(int i) {
        LogUtil.verbose(G, "setRearAcKeyLockState=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRearLockS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearAcLockState(int i) {
        return 0;
    }

    public int setRearAcWindLevel(int i, int i2) {
        LogUtil.verbose(G, "setRearAcWindLevel setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRearWindLevelS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearAcWindMode(int i, int i2) {
        LogUtil.verbose(G, "setRearAcWindMode setSource=" + i + ", mode=" + i2);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRearWindModeS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSweepBlowStateCopilot(int i) {
        LogUtil.verbose(G, "setSweepBlowStateCopilot=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setSweepBlowStateCopilotS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSweepBlowStateDriver(int i) {
        LogUtil.verbose(G, "setSweepBlowStateDriver=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setSweepBlowStateDriverS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int start(int i) {
        LogUtil.verbose(G, "start=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcCloseKey(1);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int startRearAc(int i) {
        LogUtil.verbose(G, "startRearAc=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRearAcStateS(1);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int stop(int i) {
        LogUtil.verbose(G, "stop=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcCloseKey(2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int stopRearAc(int i) {
        LogUtil.verbose(G, "stopRearAc=" + i);
        CarVendorHvacManager carVendorHvacManager = this.f77a;
        if (carVendorHvacManager == null) {
            return 0;
        }
        try {
            carVendorHvacManager.setAcRearAcStateS(2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean subscribeHvacCallback(IHvacAdapterCallback iHvacAdapterCallback) {
        com.ts.appservice.caradapter.a.h hVar = this.d;
        if (hVar == null || iHvacAdapterCallback == null) {
            return false;
        }
        hVar.h(iHvacAdapterCallback);
        if (!this.D) {
            return true;
        }
        this.d.l();
        return true;
    }

    public void t(CarVendorHvacManager carVendorHvacManager, CarVendorPm2p5Manager carVendorPm2p5Manager, CarVendorFragranceManager carVendorFragranceManager) {
        this.f77a = carVendorHvacManager;
        this.f78b = carVendorPm2p5Manager;
        this.c = carVendorFragranceManager;
    }

    public boolean unsubscribeHvacCallback(IHvacAdapterCallback iHvacAdapterCallback) {
        com.ts.appservice.caradapter.a.h hVar = this.d;
        if (hVar == null || iHvacAdapterCallback == null) {
            return false;
        }
        hVar.i(iHvacAdapterCallback);
        return true;
    }
}
