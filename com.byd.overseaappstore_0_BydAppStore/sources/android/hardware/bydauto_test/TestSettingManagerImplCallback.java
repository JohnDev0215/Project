package android.hardware.bydauto_test;

import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Method;
import java.util.Objects;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class TestSettingManagerImplCallback {
    public static final String CANCELRECOVERPARMS = "cancelRecoverParms";
    public static final int FIVE = 5;
    public static final int FOUR = 4;
    public static final int ONE = 1;
    public static final String ONHUDRESTARTANDEFFECT = "onHudRestartAndEffect";
    public static final String RECOVERCALIPERSPLINTHEIGHT = "recoverCaliperSplintHeight";
    public static final String RECOVERCUSHIONHEIGHT = "recoverCushionHeight";
    public static final String RESETAUTOEXTERNALREARMIRRORFOLLOWUP = "resetAutoExternalRearMirrorFollowUp";
    public static final String RESETAUTOPOWERONSTATE = "resetAutoPowerOnState";
    public static final String RESETHUDBRIGHTNESS = "resetHudBrightness";
    public static final String RESETHUDDISPLAYCONTENT = "resetHudDisplayContent";
    public static final String RESETHUDHEIGHT = "resetHudHeight";
    public static final String RESETHUDMODE = "resetHudMode";
    public static final String RESETHUDROTATIONANGLE = "resetHudRotationAngle";
    public static final String RESETMOTORPRETENSIONERSEATBELT = "resetMotorPretensionerSeatbelt";
    public static final String SAVESEATPARAMS = "saveSeatParams";
    public static final String SETACAUTOAIR = "setACAutoAir";
    public static final String SETACBTWIND = "setACBTWind";
    public static final String SETACPAUSECYCLE = "setACPauseCycle";
    public static final String SETACTEMPERATUREUNIT = "setAcTemperatureUnit";
    public static final String SETACTUNNELCYCLE = "setACTunnelCycle";
    public static final String SETACWARMMODE = "setAcWarmMode";
    public static final String SETAPPUSELIMITSTATE = "setAppUseLimitState";
    public static final String SETAUTOEXTERNALREARMIRRORFOLLOWUPSWITCH = "setAutoExternalRearMirrorFollowUpSwitch";
    public static final String SETAUTOPOWERONSTATE = "setAutoPowerOnState";
    public static final String SETAUTOPURIFICATION = "setAutoPurification";
    public static final String SETAUTORAINWIPERSTATE = "setAutoRainWiperState";
    public static final String SETBACKDOORELECTRICMODE = "setBackDoorElectricMode";
    public static final String SETBACKDOOROPENEDHEIGHT = "setBackDoorOpenedHeight";
    public static final String SETBRAKEPEDALMODE = "setBrakePedalMode";
    public static final String SETCARTIPSSOUNDTYPE = "setCarTipsSoundType";
    public static final String SETCHARGINGPORT = "setChargingPort";
    public static final String SETCOMFORTABLEPARKINGSTATE = "setComfortableParkingState";
    public static final String SETDISINFSTATE = "setDisinfState";
    public static final String SETDOORKNOBAUTOSHRINKSTATE = "setDoorknobAutoShrinkState";
    public static final String SETDRIVERSEATAUTORETURN = "setDriverSeatAutoReturn";
    public static final String SETDYNAMICSUSPENSIONMODE = "setDynamicSuspensionMode";
    public static final String SETENERGYFEEDBACK = "setEnergyFeedback";
    public static final String SETENGINESOUNDTYPE = "setEngineSoundType";
    public static final String SETGETREPORTVOLUME = "setReportVolume";
    public static final String SETHEADRESTHORIZATION = "setHeadrestHorization";
    public static final String SETHEADRESTTURNHEIGHT = "setHeadrestTurnHeight";
    public static final String SETHUDBRIGHTNESS = "setHudBrightness";
    public static final String SETHUDDISPLAYCONTENT = "setHudDisplayContent";
    public static final String SETHUDHEIGHT = "setHudHeight";
    public static final String SETHUDMODE = "setHudMode";
    public static final String SETHUDPARAMSEFFECT = "setHudParamsEffect";
    public static final String SETHUDROTATIONANGLE = "setHudRotationAngle";
    public static final String SETHUDSTATE = "setHudState";
    public static final String SETINTELLIGENTACAREASTATE = "setIntelligentAcAreaState";
    public static final String SETLANGUAGE = "setLanguage";
    public static final String SETLEFTHOMELIGHTDELAYVALUE = "setLeftHomeLightDelayValue";
    public static final String SETLOCKCARRISEWINDOW = "setLockCarRiseWindow";
    public static final String SETMICROSWITCHLOCKWINDOWSTATE = "setMicroSwitchLockWindowState";
    public static final String SETMICROSWITCHUNLOCKWINDOWSTATE = "setMicroSwitchUnlockWindowState";
    public static final String SETMOTORPRETENSIONERSEATBELTSTATE = "setMotorPretensionerSeatbeltState";
    public static final String SETNAVIGATIONMEDIASTATE = "setNavigationMediaState";
    public static final String SETOVERSPEEDLOCK = "setOverspeedLock";
    public static final String SETPM25POWER = "setPM25Power";
    public static final String SETPM25SWITCHCHECK = "setPM25SwitchCheck";
    public static final String SETPM25TIMECHECK = "setPM25TimeCheck";
    public static final String SETRAINAUTOLOCKWINDOWSTATE = "setRainAutoLockWindowState";
    public static final String SETREARVIEWMIRRORFLIP = "setRearViewMirrorFlip";
    public static final String SETREFRIGERATORMODE = "setRefrigeratorMode";
    public static final String SETREMENBERPOWEROFFSTATE = "setRemenberPowerOffState";
    public static final String SETREMOTECONTROLLOCKWINDOWSTATE = "setRemoteControlLockWindowState";
    public static final String SETREMOTECONTROLUNLOCKWINDOWSTATE = "setRemoteControlUnlockWindowState";
    public static final String SETREMOTEKEYSTARTACTIME = "setRemoteKeyStartAcTime";
    public static final String SETSEATBACKRESTPOSTION = "setSeatbackrestPostion";
    public static final String SETSEATHORIZATION = "setSeatHorization";
    public static final String SETSEATTURNHEIGHT = "setSeatTurnHeight";
    public static final String SETSOCTARGET = "setSOCTarget";
    public static final String SETSPEEDREMINDERVALUE = "setSpeedReminderValue";
    public static final String SETSTEERASSIS = "setSteerAssis";
    public static final String SETSTEERPOSITIONAUTORETURN = "setSteerPositionAutoReturn";
    public static final String SETTRAYSTATE = "setTrayState";
    public static final String SETTURNCALIPERSPLINTHEIGHT = "setTurnCaliperSplintHeight";
    public static final String SETTURNCUSHIONHEIGHT = "setTurnCushionHeight";
    public static final int SIX = 6;
    public static final float TESTVALUE = 0.0f;
    public static final int THREE = 3;
    public static final int TWO = 2;
    public static final int ZERO = 0;
    private AbsBYDAutoSettingListener mAbsBYDAutoSettingListener;
    private Handler mHandler;
    public final String TURNSEATBACKREST = "turnSeatbackrest";
    public final String SETLANDADVANCEATSTATE = "setLandAdvanceAtState";
    public final String SETAUTOPOWEROFFSTATE = "setAutoPowerOffState";
    public final String TURNSEATCUSHION = "turnSeatCushion";
    public final String TURNSEATHEIGHT = "turnSeatHeight";
    public final String TURNSEATHORIZATION = "turnSeatHorization";
    public final String SETBACKHOMELIGHTDELAYVALUE = "setBackHomeLightDelayValue";
    public final String SETLOCKOFF = "setLockOff";
    public final String TURNSEATCALIPERSPLINT = "turnSeatCaliperSplint";
    public final String TURNREARVIEWMIRROR = "turnRearviewMirror";
    public final String SETINTELLIGENTUNLOCKSTATE = "setIntelligentUnlockState";
    public final String SETMAINTAINREMINDSTATE = "setMaintainRemindState";
    public final String SETINTELLIGENTLOCKSTATE = "setIntelligentLockState";
    public final String SETREMOTCONTROLUPWINDOWSTATE = "setRemotControlUpwindowState";
    public final String SETSAFEWARNSTATE = "setSafeWarnState";
    public final String SETREMOTCONTROLDOWNWINDOWSTATE = "setRemotControlDownwindowState";
    public final String TURNSTEERINGWHEEL = "turnSteeringWheel";
    public final String TURNHEADRESTTURNHORIZATION = "turnHeadrestTurnHorization";
    public final String TURNHEADRESTTURNHEIGHT = "turnHeadrestTurnHeight";
    public final String RESETSEATPARAMS = "resetSeatParams";
    public final String RESETHUDSTATE = "resetHudState";
    public final String SETIALBRIGHTNESS = "setIALBrightness";
    public final String SETINSTHEME = "setINSTheme";
    public final String GETRIGHTHEADLAMPLEVEL = "getRightHeadlampLevel";
    public final String SETIALAREA = "setIALArea";
    public final String SETMASSAGELEVEL = "setMassageLevel";
    public final String GETLEFTHEADLAMPLEVEL = "getLeftHeadlampLevel";
    public final String SETSTARTORPOWERIND = "setStartOrPowerInd";
    public final String SETSEATVENTILATINGSTATE = "setSeatVentilatingState";
    public final String SETPARKBRAKEIND = "setParkBrakeInd";
    public final String SETSEATHEATINGSTATE = "setSeatHeatingState";
    public final String SETKEYPOWERLOWIND = "setKeyPowerLowInd";
    public final String SETLEAVECARPIND = "setLeaveCarPInd";
    public final String SETLOWOILIND = "setLowOilInd";
    public final String SETLOWBATTERYIND = "setLowBatteryInd";
    public final String SETREARACPANELAUTOLOCKTIME = "setRearAcPanelAutoLockTime";
    public final String SETSAFEBELTIND = "setSafeBeltInd";
    public final String SETNIGHTDRLIGHTONIND = "setNightDRLightOnInd";
    public final String SETIALCOLOR = "setIALColor";
    public final String SETSTEERINGWHEELHEATINGSTATE = "setSteeringWheelHeatingState";
    public final String SETINSIDELIGHTDOORSTATE = "setInsideLightDoorState";
    public final String SETLANTERNLANGUAGEMODE = "setLanternLanguageMode";
    public final String SETHEADLAMPLEVEL = "setHeadlampLevel";
    public final String SETSMARTWELCOMELIGHTSTATE = "setSmartWelcomeLightState";
    public final String SETDRIVEDOORCLOSEIND = "setDriveDoorCloseInd";
    public final String SETSTOPREMOTECTRLDRIVEIND = "setStopRemoteCtrlDriveInd";
    public final String SETAUTOMATICPARKING = "setAutomaticParking";
    public final String SETMISSKEYIND = "setMissKeyInd";
    public final String SETMASSAGEMODE = "setMassageMode";
    public final String SENDOFFREMINDER = "sendOffReminder";
    public final String SAVESEATPARAMSALL = "saveSeatParamsAll";
    private final String TAG = "TestSettingManagerImplCallback";

    public TestSettingManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestSettingManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestSettingManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestSettingManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    private void Process(Bundle bundle, String str) {
        try {
            for (Method method : AbsBYDAutoSettingListener.class.getDeclaredMethods()) {
                if (method.getName().equals(nameChange(str))) {
                    if (method.getName().equals("onRecoverSeatParamsChanged")) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), 0);
                        return;
                    }
                    if (method.getName().equals("onSetHudParamsEffectChanged")) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"));
                        return;
                    }
                    int parameterCount = method.getParameterCount();
                    if (parameterCount == 0) {
                        method.invoke(this.mAbsBYDAutoSettingListener, new Object[0]);
                        return;
                    }
                    if (parameterCount == 1) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"));
                        return;
                    }
                    if (parameterCount == 2) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D));
                        return;
                    }
                    if (parameterCount == 3) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), bundle.get(ExifInterface.GPS_MEASUREMENT_3D));
                        return;
                    }
                    if (parameterCount == 4) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), bundle.get(ExifInterface.GPS_MEASUREMENT_3D), bundle.get("4"));
                        return;
                    } else if (parameterCount == 5) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), bundle.get(ExifInterface.GPS_MEASUREMENT_3D), bundle.get("4"), bundle.get("5"));
                        return;
                    } else if (parameterCount == 6) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), bundle.get(ExifInterface.GPS_MEASUREMENT_3D), bundle.get("4"), bundle.get("5"), bundle.get("6"));
                        return;
                    }
                }
            }
        } catch (Exception e) {
            StringBuilder sbK = e.k("errormessage=");
            sbK.append(e.getMessage());
            Log.d("TestSettingManagerImplCallback", sbK.toString());
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private String nameChange(String str) {
        Objects.requireNonNull(str);
        byte b = -1;
        switch (str.hashCode()) {
            case -2140457114:
                if (str.equals(SETHUDBRIGHTNESS)) {
                    b = 0;
                }
                break;
            case -2109277245:
                if (str.equals(SETNAVIGATIONMEDIASTATE)) {
                    b = 1;
                }
                break;
            case -2097730068:
                if (str.equals(SETHUDDISPLAYCONTENT)) {
                    b = 2;
                }
                break;
            case -2064402790:
                if (str.equals(SETREMOTEKEYSTARTACTIME)) {
                    b = 3;
                }
                break;
            case -2035130276:
                if (str.equals(SETHUDSTATE)) {
                    b = 4;
                }
                break;
            case -1994873082:
                if (str.equals("setRemotControlUpwindowState")) {
                    b = 5;
                }
                break;
            case -1863711049:
                if (str.equals(SETSTEERPOSITIONAUTORETURN)) {
                    b = 6;
                }
                break;
            case -1854701484:
                if (str.equals(SETBRAKEPEDALMODE)) {
                    b = 7;
                }
                break;
            case -1644991940:
                if (str.equals(SETLEFTHOMELIGHTDELAYVALUE)) {
                    b = 8;
                }
                break;
            case -1582425287:
                if (str.equals("saveSeatParamsAll")) {
                    b = 9;
                }
                break;
            case -1570584957:
                if (str.equals("setRearAcPanelAutoLockTime")) {
                    b = 10;
                }
                break;
            case -1561751469:
                if (str.equals(SETDOORKNOBAUTOSHRINKSTATE)) {
                    b = 11;
                }
                break;
            case -1494705503:
                if (str.equals(SETREMOTECONTROLLOCKWINDOWSTATE)) {
                    b = 12;
                }
                break;
            case -1487673041:
                if (str.equals("getLeftHeadlampLevel")) {
                    b = 13;
                }
                break;
            case -1453086180:
                if (str.equals(SETSTEERASSIS)) {
                    b = 14;
                }
                break;
            case -1417141063:
                if (str.equals(SETTRAYSTATE)) {
                    b = 15;
                }
                break;
            case -1248165571:
                if (str.equals(SETCOMFORTABLEPARKINGSTATE)) {
                    b = 16;
                }
                break;
            case -1244359459:
                if (str.equals("setStartOrPowerInd")) {
                    b = 17;
                }
                break;
            case -1243086520:
                if (str.equals(SAVESEATPARAMS)) {
                    b = 18;
                }
                break;
            case -1219271699:
                if (str.equals("turnSteeringWheel")) {
                    b = 19;
                }
                break;
            case -1184561294:
                if (str.equals(RESETMOTORPRETENSIONERSEATBELT)) {
                    b = 20;
                }
                break;
            case -1173259763:
                if (str.equals(SETINTELLIGENTACAREASTATE)) {
                    b = 21;
                }
                break;
            case -1073217808:
                if (str.equals(SETGETREPORTVOLUME)) {
                    b = 22;
                }
                break;
            case -1031351043:
                if (str.equals("setINSTheme")) {
                    b = 23;
                }
                break;
            case -1004247119:
                if (str.equals(ONHUDRESTARTANDEFFECT)) {
                    b = 24;
                }
                break;
            case -982620798:
                if (str.equals("setLockOff")) {
                    b = 25;
                }
                break;
            case -970881975:
                if (str.equals("resetHudState")) {
                    b = 26;
                }
                break;
            case -961257987:
                if (str.equals(SETDYNAMICSUSPENSIONMODE)) {
                    b = 27;
                }
                break;
            case -928452693:
                if (str.equals("setInsideLightDoorState")) {
                    b = 28;
                }
                break;
            case -818796842:
                if (str.equals("setAutoPowerOffState")) {
                    b = 29;
                }
                break;
            case -762146979:
                if (str.equals("turnSeatbackrest")) {
                    b = 30;
                }
                break;
            case -711394523:
                if (str.equals(RECOVERCALIPERSPLINTHEIGHT)) {
                    b = 31;
                }
                break;
            case -697491969:
                if (str.equals("setLanternLanguageMode")) {
                    b = 32;
                }
                break;
            case -689564097:
                if (str.equals("setIALArea")) {
                    b = 33;
                }
                break;
            case -597262439:
                if (str.equals(RESETHUDBRIGHTNESS)) {
                    b = 34;
                }
                break;
            case -485336290:
                if (str.equals("setMissKeyInd")) {
                    b = 35;
                }
                break;
            case -474850284:
                if (str.equals(SETCHARGINGPORT)) {
                    b = 36;
                }
                break;
            case -447457510:
                if (str.equals(SETSPEEDREMINDERVALUE)) {
                    b = 37;
                }
                break;
            case -379067261:
                if (str.equals("setIALBrightness")) {
                    b = 38;
                }
                break;
            case -361117585:
                if (str.equals(RESETHUDHEIGHT)) {
                    b = 39;
                }
                break;
            case -361023567:
                if (str.equals(SETRAINAUTOLOCKWINDOWSTATE)) {
                    b = 40;
                }
                break;
            case -337664076:
                if (str.equals(SETACPAUSECYCLE)) {
                    b = 41;
                }
                break;
            case -303863755:
                if (str.equals("turnHeadrestTurnHeight")) {
                    b = ExifInterface.START_CODE;
                }
                break;
            case -287353638:
                if (str.equals(SETHEADRESTTURNHEIGHT)) {
                    b = 43;
                }
                break;
            case -246976533:
                if (str.equals(RESETAUTOPOWERONSTATE)) {
                    b = 44;
                }
                break;
            case -229603074:
                if (str.equals(SETAUTOPOWERONSTATE)) {
                    b = 45;
                }
                break;
            case -168928709:
                if (str.equals("setMassageLevel")) {
                    b = 46;
                }
                break;
            case -162518921:
                if (str.equals(SETACAUTOAIR)) {
                    b = 47;
                }
                break;
            case -153113691:
                if (str.equals(SETREFRIGERATORMODE)) {
                    b = 48;
                }
                break;
            case -131164021:
                if (str.equals(SETAUTORAINWIPERSTATE)) {
                    b = 49;
                }
                break;
            case -98601529:
                if (str.equals("turnHeadrestTurnHorization")) {
                    b = 50;
                }
                break;
            case -70181362:
                if (str.equals("setLeaveCarPInd")) {
                    b = 51;
                }
                break;
            case -65832840:
                if (str.equals(SETHUDMODE)) {
                    b = 52;
                }
                break;
            case -56709329:
                if (str.equals(SETENERGYFEEDBACK)) {
                    b = 53;
                }
                break;
            case -16341473:
                if (str.equals(SETAUTOEXTERNALREARMIRRORFOLLOWUPSWITCH)) {
                    b = 54;
                }
                break;
            case 62379872:
                if (str.equals(SETLOCKCARRISEWINDOW)) {
                    b = 55;
                }
                break;
            case 66791903:
                if (str.equals("turnSeatCushion")) {
                    b = 56;
                }
                break;
            case 100114417:
                if (str.equals("setIALColor")) {
                    b = 57;
                }
                break;
            case 112024317:
                if (str.equals("setDriveDoorCloseInd")) {
                    b = 58;
                }
                break;
            case 128152583:
                if (str.equals("setSteeringWheelHeatingState")) {
                    b = 59;
                }
                break;
            case 196869929:
                if (str.equals(SETTURNCUSHIONHEIGHT)) {
                    b = 60;
                }
                break;
            case 198058016:
                if (str.equals(SETMICROSWITCHLOCKWINDOWSTATE)) {
                    b = 61;
                }
                break;
            case 245081329:
                if (str.equals("setIntelligentLockState")) {
                    b = 62;
                }
                break;
            case 302172395:
                if (str.equals(SETSEATTURNHEIGHT)) {
                    b = 63;
                }
                break;
            case 375730650:
                if (str.equals(SETLANGUAGE)) {
                    b = 64;
                }
                break;
            case 386972383:
                if (str.equals("setLowOilInd")) {
                    b = 65;
                }
                break;
            case 389864330:
                if (str.equals("setStopRemoteCtrlDriveInd")) {
                    b = 66;
                }
                break;
            case 434669561:
                if (str.equals(SETPM25TIMECHECK)) {
                    b = 67;
                }
                break;
            case 458432999:
                if (str.equals(SETMICROSWITCHUNLOCKWINDOWSTATE)) {
                    b = 68;
                }
                break;
            case 497047918:
                if (str.equals(SETDRIVERSEATAUTORETURN)) {
                    b = 69;
                }
                break;
            case 546231422:
                if (str.equals(SETACBTWIND)) {
                    b = 70;
                }
                break;
            case 559065093:
                if (str.equals(SETENGINESOUNDTYPE)) {
                    b = 71;
                }
                break;
            case 571578204:
                if (str.equals("setSafeWarnState")) {
                    b = 72;
                }
                break;
            case 588729408:
                if (str.equals("setParkBrakeInd")) {
                    b = 73;
                }
                break;
            case 606054924:
                if (str.equals(SETACWARMMODE)) {
                    b = 74;
                }
                break;
            case 656908986:
                if (str.equals(SETACTUNNELCYCLE)) {
                    b = 75;
                }
                break;
            case 661234411:
                if (str.equals(RESETHUDMODE)) {
                    b = 76;
                }
                break;
            case 727342991:
                if (str.equals("setAutomaticParking")) {
                    b = 77;
                }
                break;
            case 745555395:
                if (str.equals(SETPM25POWER)) {
                    b = 78;
                }
                break;
            case 753349641:
                if (str.equals("setLandAdvanceAtState")) {
                    b = 79;
                }
                break;
            case 753524754:
                if (str.equals(SETMOTORPRETENSIONERSEATBELTSTATE)) {
                    b = 80;
                }
                break;
            case 805768517:
                if (str.equals("turnRearviewMirror")) {
                    b = 81;
                }
                break;
            case 842427320:
                if (str.equals("setIntelligentUnlockState")) {
                    b = 82;
                }
                break;
            case 936265305:
                if (str.equals("sendOffReminder")) {
                    b = 83;
                }
                break;
            case 988835924:
                if (str.equals(SETACTEMPERATUREUNIT)) {
                    b = 84;
                }
                break;
            case 1006923452:
                if (str.equals(SETHUDHEIGHT)) {
                    b = 85;
                }
                break;
            case 1056209720:
                if (str.equals(RESETAUTOEXTERNALREARMIRRORFOLLOWUP)) {
                    b = 86;
                }
                break;
            case 1057555626:
                if (str.equals(SETTURNCALIPERSPLINTHEIGHT)) {
                    b = 87;
                }
                break;
            case 1142324295:
                if (str.equals(SETBACKDOOROPENEDHEIGHT)) {
                    b = 88;
                }
                break;
            case 1150123148:
                if (str.equals(SETHUDPARAMSEFFECT)) {
                    b = 89;
                }
                break;
            case 1203983557:
                if (str.equals("setSafeBeltInd")) {
                    b = 90;
                }
                break;
            case 1217687914:
                if (str.equals("setHeadlampLevel")) {
                    b = 91;
                }
                break;
            case 1238603753:
                if (str.equals("turnSeatHeight")) {
                    b = 92;
                }
                break;
            case 1282049651:
                if (str.equals("setKeyPowerLowInd")) {
                    b = 93;
                }
                break;
            case 1316175503:
                if (str.equals(SETHEADRESTHORIZATION)) {
                    b = 94;
                }
                break;
            case 1399920988:
                if (str.equals("setBackHomeLightDelayValue")) {
                    b = 95;
                }
                break;
            case 1424560151:
                if (str.equals(SETREARVIEWMIRRORFLIP)) {
                    b = 96;
                }
                break;
            case 1452698198:
                if (str.equals("setSmartWelcomeLightState")) {
                    b = 97;
                }
                break;
            case 1455539641:
                if (str.equals(SETREMENBERPOWEROFFSTATE)) {
                    b = 98;
                }
                break;
            case 1469135454:
                if (str.equals(SETAPPUSELIMITSTATE)) {
                    b = 99;
                }
                break;
            case 1505296424:
                if (str.equals(SETREMOTECONTROLUNLOCKWINDOWSTATE)) {
                    b = 100;
                }
                break;
            case 1531788954:
                if (str.equals("resetSeatParams")) {
                    b = 101;
                }
                break;
            case 1564215359:
                if (str.equals("setMaintainRemindState")) {
                    b = 102;
                }
                break;
            case 1642275780:
                if (str.equals("setLowBatteryInd")) {
                    b = 103;
                }
                break;
            case 1658659999:
                if (str.equals(RESETHUDDISPLAYCONTENT)) {
                    b = 104;
                }
                break;
            case 1762677874:
                if (str.equals(SETPM25SWITCHCHECK)) {
                    b = 105;
                }
                break;
            case 1780695490:
                if (str.equals(SETAUTOPURIFICATION)) {
                    b = 106;
                }
                break;
            case 1795704844:
                if (str.equals("setMassageMode")) {
                    b = 107;
                }
                break;
            case 1810682492:
                if (str.equals(SETOVERSPEEDLOCK)) {
                    b = 108;
                }
                break;
            case 1848637158:
                if (str.equals("setSeatHeatingState")) {
                    b = 109;
                }
                break;
            case 1871565308:
                if (str.equals(SETDISINFSTATE)) {
                    b = 110;
                }
                break;
            case 1899572708:
                if (str.equals(RECOVERCUSHIONHEIGHT)) {
                    b = 111;
                }
                break;
            case 1905701536:
                if (str.equals(SETSEATHORIZATION)) {
                    b = 112;
                }
                break;
            case 1914750902:
                if (str.equals(SETSOCTARGET)) {
                    b = 113;
                }
                break;
            case 1953814854:
                if (str.equals(SETSEATBACKRESTPOSTION)) {
                    b = 114;
                }
                break;
            case 1968139238:
                if (str.equals("getRightHeadlampLevel")) {
                    b = 115;
                }
                break;
            case 1998192557:
                if (str.equals(RESETHUDROTATIONANGLE)) {
                    b = 116;
                }
                break;
            case 2006087095:
                if (str.equals(SETBACKDOORELECTRICMODE)) {
                    b = 117;
                }
                break;
            case 2006570655:
                if (str.equals("setRemotControlDownwindowState")) {
                    b = 118;
                }
                break;
            case 2015566016:
                if (str.equals(SETHUDROTATIONANGLE)) {
                    b = 119;
                }
                break;
            case 2080921245:
                if (str.equals(CANCELRECOVERPARMS)) {
                    b = 120;
                }
                break;
            case 2118691707:
                if (str.equals("turnSeatHorization")) {
                    b = 121;
                }
                break;
            case 2123960928:
                if (str.equals("turnSeatCaliperSplint")) {
                    b = 122;
                }
                break;
            case 2135722254:
                if (str.equals("setNightDRLightOnInd")) {
                    b = 123;
                }
                break;
            case 2138414495:
                if (str.equals(SETCARTIPSSOUNDTYPE)) {
                    b = 124;
                }
                break;
            case 2139707713:
                if (str.equals("setSeatVentilatingState")) {
                    b = 125;
                }
                break;
        }
        switch (b) {
            case 0:
                return "onHudBrightnessChanged";
            case 1:
                return "onNavigationMediaStateChanged";
            case 2:
                return "onHudDisplayContentChanged";
            case 3:
                return "onAcKeyStartTimeChanged";
            case 4:
                return "onHudStateChanged";
            case 5:
                return "onRemoteControlUpwindowStateChanged";
            case 6:
                return "onSteerPositionAutoReturnStateChanged";
            case 7:
                return "onBrakePedalModeChanged";
            case 8:
                return "onLeftHomeLightDelayValueChanged";
            case 9:
                return "onSaveSeatParamsAllChanged";
            case 10:
                return "onRearAcPanelAutoLockTimeChanged";
            case 11:
                return "onDoorknobAutoShrinkStateChanged";
            case 12:
                return "onRemoteLockWindowStateChanged";
            case 13:
                return "onLeftHeadlampLevelChanged";
            case 14:
                return "onSteerAssisModeChanged";
            case 15:
                return "onTrayStateChanged";
            case 16:
                return "onComfortableParkingStateChanged";
            case 17:
                return "onStartOrPowerIndSwitchChanged";
            case 18:
                return "onSaveSeatParams";
            case 19:
                return "onTurnSteeringWheelChanged";
            case 20:
                return "onResetMotorPretensionerSeatbelt";
            case 21:
                return "onIntelligentAcAreaStateChanged";
            case 22:
                return "onReportVolumeChanged";
            case 23:
                return "onINSThemeChanged";
            case 24:
                return ONHUDRESTARTANDEFFECT;
            case 25:
                return "onLockOffChanged";
            case 26:
                return "onSetHudParamsEffectChanged";
            case 27:
                return "onDynamicSuspensionModeChanged";
            case 28:
                return "onInsideLightDoorStateChanged";
            case 29:
                return "onAutoPowerOffStateChanged";
            case 30:
                return "onTurnSeatbackrestChanged";
            case 31:
                return "onRecoverCaliperSplintHeight";
            case 32:
                return "onLanternLanguageModeChanged";
            case 33:
                return "onIALAreaChanged";
            case 34:
                return "onResetHudBrightness";
            case 35:
                return "onMissKeyIndSwitchChanged";
            case 36:
                return "onChargingPortSwitchChanged";
            case 37:
                return "onSpeedReminderValueChanged";
            case 38:
                return "onIALBrightnessChanged";
            case 39:
                return "onResetHudHeight";
            case 40:
                return "onRainAutoLockWindowStateChanged";
            case 41:
                return "onACPauseCycleSwitchChanged";
            case 42:
            case 43:
                return "onHeadrestTurnHeightChanged";
            case 44:
                return "onResetAutoPowerOnState";
            case 45:
                return "onAutoPowerOnStateChanged";
            case 46:
                return "onMassageLevelStateChanged";
            case 47:
                return "onACAutoAirModeChanged";
            case 48:
                return "onRefrigeratorModeChanged";
            case 49:
                return "onAutoRainWiperStateChanged";
            case 50:
            case 94:
                return "onHeadrestHorizationChanged";
            case 51:
                return "onLeaveCarPIndSwitchChanged";
            case 52:
                return "onHudModeChanged";
            case 53:
                return "onEnergyFeedbackStrengthChanged";
            case 54:
                return "onAutoExternalRearMirrorFollowUpStateChanged";
            case 55:
                return "onLockCarRiseWindowStateChange";
            case 56:
                return "onTurnSeatCushionChanged";
            case 57:
                return "onIALColorChanged";
            case 58:
                return "onDriveDoorCloseIndSwitchChanged";
            case 59:
                return "onSteeringWheelHeatingStateChanged";
            case 60:
                return "onTurnCushionHeightChanged";
            case 61:
                return "onMicroSwitchLockWindowStateChanged";
            case 62:
                return "onIntelligentLockStateChanged";
            case 63:
                return "onSeatTurnHeight";
            case 64:
                return "onLanguageChanged";
            case 65:
                return "onLowOilIndSwitchChanged";
            case 66:
                return "onStopRemoteCtrlDriveIndChanged";
            case 67:
                return "onPM25TimeCheckChanged";
            case 68:
                return "onMicroSwitchUnlockWindowStateChanged";
            case 69:
                return "onDriverSeatAutoReturnStateChanged";
            case 70:
                return "onACBTWindSwitchChanged";
            case 71:
                return "onEngineSoundTypeChanged";
            case 72:
                return "onSafeWarnStateChanged";
            case 73:
                return "onParkBrakeIndSwitchChanged";
            case 74:
                return "onAcWarmModeChanged";
            case 75:
                return "onACTunnelCycleSwitchChanged";
            case 76:
                return "onResetHudMode";
            case 77:
                return "onAutomaticParkingSwitchChanged";
            case 78:
                return "onPM25PowerSwitchChanged";
            case 79:
                return "onLandAdvanceAtSateChanged";
            case 80:
                return "onMotorPretensionerSeatbeltStateChanged";
            case 81:
                return "onTurnRearviewMirrorChanged";
            case 82:
                return "onIntelligentUnlockStateChanged";
            case 83:
                return "onOffReminderChanged";
            case 84:
                return "onAcTempUnitChanged";
            case 85:
                return "onHudHeightChanged";
            case 86:
                return "onResetAutoExternalRearMirrorFollowUp";
            case 87:
                return "onTurnCaliperSplintHeightChanged";
            case 88:
                return "onBackDoorOpenedHeightChanged";
            case 89:
                return "onHudParamsEffect";
            case 90:
                return "onSafeBeltIndSwitchChanged";
            case 91:
                return "onHeadlampLevelChanged";
            case 92:
                return "onTurnSeatHeightChanged";
            case 93:
                return "onKeyPowerLowIndSwitchChanged";
            case 95:
                return "onBackHomeLightDelayValueChanged";
            case 96:
                return "onRearViewMirrorFlipChanged";
            case 97:
                return "onSmartWelcomeLightStateChanged";
            case 98:
                return "onRemenberPowerOffStateChanged";
            case 99:
                return "onAppUseLimitStateChnaged";
            case 100:
                return "onRemoteUnlockWindowStateChanged";
            case 101:
                return "onRecoverSeatParamsChanged";
            case 102:
                return "onMaintainRemindStateChanged";
            case 103:
                return "onLowBatteryIndSwitchChanged";
            case 104:
                return "onResetHudDisplayContent";
            case 105:
                return "onPM25SwitchCheckChanged";
            case 106:
                return "onAcAutoPurificationStateChanged";
            case 107:
                return "onMassageModeStateChanged";
            case 108:
                return "onOverSpeedLockStateChanged";
            case 109:
                return "onSeatHeatingStateChanged";
            case 110:
                return "onDisinfStateChanged";
            case 111:
                return "onRecoverCushionHeight";
            case 112:
                return "onSeatHorizationChanged";
            case 113:
                return "onSOCTargetRangeChange";
            case 114:
                return "onSeatbackrestPostion";
            case 115:
                return "onRightHeadlampLevelChanged";
            case 116:
                return "onResetHudRotationAngle";
            case 117:
                return "onBackDoorElectricModeChanged";
            case 118:
                return "onRemoteControlSwitchChanged";
            case 119:
                return "onHudRotationAngleChanged";
            case 120:
                return "onCancelRecoverParms";
            case 121:
                return "onTurnSeatHorizationChanged";
            case 122:
                return "onTurnSeatCaliperSplintChanged";
            case 123:
                return "onNightDRLightOnIndSwitchChanged";
            case 124:
                return "onCarTipsSoundTypeChanged";
            case 125:
                return "onSeatVentilatingStateChanged";
            default:
                return "";
        }
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBYDAutoSettingListener != null) {
            Bundle bundle = (Bundle) message.obj;
            if (bundle.get("1") == null) {
                bundle.putInt("1", -1);
            }
            if (bundle.get(ExifInterface.GPS_MEASUREMENT_2D) == null) {
                bundle.putInt(ExifInterface.GPS_MEASUREMENT_2D, -1);
            }
            if (bundle.get(ExifInterface.GPS_MEASUREMENT_3D) == null) {
                bundle.putInt(ExifInterface.GPS_MEASUREMENT_3D, -1);
            }
            if (bundle.get("4") == null) {
                bundle.putInt("4", -1);
            }
            if (bundle.get("5") == null) {
                bundle.putInt("5", -1);
            }
            if (bundle.get("6") == null) {
                bundle.putInt("6", -1);
            }
            Process(bundle, bundle.getString("what"));
        }
    }

    public void enqueueMessage(String str, Object... objArr) {
        if (this.mHandler != null) {
            Bundle bundle = new Bundle();
            bundle.putString("what", str);
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] instanceof Integer) {
                    bundle.putInt(String.valueOf(i + 1), ((Integer) objArr[i]).intValue());
                }
                if (objArr[i] instanceof Float) {
                    bundle.putFloat(String.valueOf(i + 1), ((Float) objArr[i]).floatValue());
                }
                if (objArr[i] instanceof int[]) {
                    bundle.putIntArray(String.valueOf(i + 1), (int[]) objArr[i]);
                }
            }
            Message messageObtain = Message.obtain();
            messageObtain.obj = bundle;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void setAbsBYDAutoStatisticListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        if (absBYDAutoSettingListener == null) {
            return;
        }
        this.mAbsBYDAutoSettingListener = absBYDAutoSettingListener;
    }
}
