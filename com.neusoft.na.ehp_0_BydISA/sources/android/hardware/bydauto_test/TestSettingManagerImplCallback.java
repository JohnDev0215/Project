package android.hardware.bydauto_test;

import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.lang.reflect.Method;
import org.java_websocket.extensions.ExtensionRequestData;

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

    public void setAbsBYDAutoStatisticListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        if (absBYDAutoSettingListener == null) {
            return;
        }
        this.mAbsBYDAutoSettingListener = absBYDAutoSettingListener;
    }

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

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBYDAutoSettingListener != null) {
            Bundle bundle = (Bundle) message.obj;
            if (bundle.get("1") == null) {
                bundle.putInt("1", -1);
            }
            if (bundle.get("2") == null) {
                bundle.putInt("2", -1);
            }
            if (bundle.get("3") == null) {
                bundle.putInt("3", -1);
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

    private void Process(Bundle bundle, String str) {
        try {
            for (Method method : AbsBYDAutoSettingListener.class.getDeclaredMethods()) {
                if (method.getName().equals(nameChange(str))) {
                    if (method.getName().equals("onRecoverSeatParamsChanged")) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), 0);
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
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"));
                        return;
                    }
                    if (parameterCount == 3) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), bundle.get("3"));
                        return;
                    }
                    if (parameterCount == 4) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"));
                        return;
                    } else if (parameterCount == 5) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"));
                        return;
                    } else if (parameterCount == 6) {
                        method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"), bundle.get("6"));
                        return;
                    }
                }
            }
        } catch (Exception e) {
            Log.d("TestSettingManagerImplCallback", "errormessage=" + e.getMessage());
        }
    }

    private String nameChange(String str) {
        str.hashCode();
        switch (str) {
            case "setHudBrightness":
                return "onHudBrightnessChanged";
            case "setNavigationMediaState":
                return "onNavigationMediaStateChanged";
            case "setHudDisplayContent":
                return "onHudDisplayContentChanged";
            case "setRemoteKeyStartAcTime":
                return "onAcKeyStartTimeChanged";
            case "setHudState":
                return "onHudStateChanged";
            case "setRemotControlUpwindowState":
                return "onRemoteControlUpwindowStateChanged";
            case "setSteerPositionAutoReturn":
                return "onSteerPositionAutoReturnStateChanged";
            case "setBrakePedalMode":
                return "onBrakePedalModeChanged";
            case "setLeftHomeLightDelayValue":
                return "onLeftHomeLightDelayValueChanged";
            case "saveSeatParamsAll":
                return "onSaveSeatParamsAllChanged";
            case "setRearAcPanelAutoLockTime":
                return "onRearAcPanelAutoLockTimeChanged";
            case "setDoorknobAutoShrinkState":
                return "onDoorknobAutoShrinkStateChanged";
            case "setRemoteControlLockWindowState":
                return "onRemoteLockWindowStateChanged";
            case "getLeftHeadlampLevel":
                return "onLeftHeadlampLevelChanged";
            case "setSteerAssis":
                return "onSteerAssisModeChanged";
            case "setTrayState":
                return "onTrayStateChanged";
            case "setComfortableParkingState":
                return "onComfortableParkingStateChanged";
            case "setStartOrPowerInd":
                return "onStartOrPowerIndSwitchChanged";
            case "saveSeatParams":
                return "onSaveSeatParams";
            case "turnSteeringWheel":
                return "onTurnSteeringWheelChanged";
            case "resetMotorPretensionerSeatbelt":
                return "onResetMotorPretensionerSeatbelt";
            case "setIntelligentAcAreaState":
                return "onIntelligentAcAreaStateChanged";
            case "setReportVolume":
                return "onReportVolumeChanged";
            case "setINSTheme":
                return "onINSThemeChanged";
            case "onHudRestartAndEffect":
                return ONHUDRESTARTANDEFFECT;
            case "setLockOff":
                return "onLockOffChanged";
            case "resetHudState":
                return "onSetHudParamsEffectChanged";
            case "setDynamicSuspensionMode":
                return "onDynamicSuspensionModeChanged";
            case "setInsideLightDoorState":
                return "onInsideLightDoorStateChanged";
            case "setAutoPowerOffState":
                return "onAutoPowerOffStateChanged";
            case "turnSeatbackrest":
                return "onTurnSeatbackrestChanged";
            case "recoverCaliperSplintHeight":
                return "onRecoverCaliperSplintHeight";
            case "setLanternLanguageMode":
                return "onLanternLanguageModeChanged";
            case "setIALArea":
                return "onIALAreaChanged";
            case "resetHudBrightness":
                return "onResetHudBrightness";
            case "setMissKeyInd":
                return "onMissKeyIndSwitchChanged";
            case "setChargingPort":
                return "onChargingPortSwitchChanged";
            case "setSpeedReminderValue":
                return "onSpeedReminderValueChanged";
            case "setIALBrightness":
                return "onIALBrightnessChanged";
            case "resetHudHeight":
                return "onResetHudHeight";
            case "setRainAutoLockWindowState":
                return "onRainAutoLockWindowStateChanged";
            case "setACPauseCycle":
                return "onACPauseCycleSwitchChanged";
            case "turnHeadrestTurnHeight":
            case "setHeadrestTurnHeight":
                return "onHeadrestTurnHeightChanged";
            case "resetAutoPowerOnState":
                return "onResetAutoPowerOnState";
            case "setAutoPowerOnState":
                return "onAutoPowerOnStateChanged";
            case "setMassageLevel":
                return "onMassageLevelStateChanged";
            case "setACAutoAir":
                return "onACAutoAirModeChanged";
            case "setRefrigeratorMode":
                return "onRefrigeratorModeChanged";
            case "setAutoRainWiperState":
                return "onAutoRainWiperStateChanged";
            case "turnHeadrestTurnHorization":
            case "setHeadrestHorization":
                return "onHeadrestHorizationChanged";
            case "setLeaveCarPInd":
                return "onLeaveCarPIndSwitchChanged";
            case "setHudMode":
                return "onHudModeChanged";
            case "setEnergyFeedback":
                return "onEnergyFeedbackStrengthChanged";
            case "setAutoExternalRearMirrorFollowUpSwitch":
                return "onAutoExternalRearMirrorFollowUpStateChanged";
            case "setLockCarRiseWindow":
                return "onLockCarRiseWindowStateChange";
            case "turnSeatCushion":
                return "onTurnSeatCushionChanged";
            case "setIALColor":
                return "onIALColorChanged";
            case "setDriveDoorCloseInd":
                return "onDriveDoorCloseIndSwitchChanged";
            case "setSteeringWheelHeatingState":
                return "onSteeringWheelHeatingStateChanged";
            case "setTurnCushionHeight":
                return "onTurnCushionHeightChanged";
            case "setMicroSwitchLockWindowState":
                return "onMicroSwitchLockWindowStateChanged";
            case "setIntelligentLockState":
                return "onIntelligentLockStateChanged";
            case "setSeatTurnHeight":
                return "onSeatTurnHeight";
            case "setLanguage":
                return "onLanguageChanged";
            case "setLowOilInd":
                return "onLowOilIndSwitchChanged";
            case "setStopRemoteCtrlDriveInd":
                return "onStopRemoteCtrlDriveIndChanged";
            case "setPM25TimeCheck":
                return "onPM25TimeCheckChanged";
            case "setMicroSwitchUnlockWindowState":
                return "onMicroSwitchUnlockWindowStateChanged";
            case "setDriverSeatAutoReturn":
                return "onDriverSeatAutoReturnStateChanged";
            case "setACBTWind":
                return "onACBTWindSwitchChanged";
            case "setEngineSoundType":
                return "onEngineSoundTypeChanged";
            case "setSafeWarnState":
                return "onSafeWarnStateChanged";
            case "setParkBrakeInd":
                return "onParkBrakeIndSwitchChanged";
            case "setAcWarmMode":
                return "onAcWarmModeChanged";
            case "setACTunnelCycle":
                return "onACTunnelCycleSwitchChanged";
            case "resetHudMode":
                return "onResetHudMode";
            case "setAutomaticParking":
                return "onAutomaticParkingSwitchChanged";
            case "setPM25Power":
                return "onPM25PowerSwitchChanged";
            case "setLandAdvanceAtState":
                return "onLandAdvanceAtSateChanged";
            case "setMotorPretensionerSeatbeltState":
                return "onMotorPretensionerSeatbeltStateChanged";
            case "turnRearviewMirror":
                return "onTurnRearviewMirrorChanged";
            case "setIntelligentUnlockState":
                return "onIntelligentUnlockStateChanged";
            case "sendOffReminder":
                return "onOffReminderChanged";
            case "setAcTemperatureUnit":
                return "onAcTempUnitChanged";
            case "setHudHeight":
                return "onHudHeightChanged";
            case "resetAutoExternalRearMirrorFollowUp":
                return "onResetAutoExternalRearMirrorFollowUp";
            case "setTurnCaliperSplintHeight":
                return "onTurnCaliperSplintHeightChanged";
            case "setBackDoorOpenedHeight":
                return "onBackDoorOpenedHeightChanged";
            case "setHudParamsEffect":
                return "onHudParamsEffect";
            case "setSafeBeltInd":
                return "onSafeBeltIndSwitchChanged";
            case "setHeadlampLevel":
                return "onHeadlampLevelChanged";
            case "turnSeatHeight":
                return "onTurnSeatHeightChanged";
            case "setKeyPowerLowInd":
                return "onKeyPowerLowIndSwitchChanged";
            case "setBackHomeLightDelayValue":
                return "onBackHomeLightDelayValueChanged";
            case "setRearViewMirrorFlip":
                return "onRearViewMirrorFlipChanged";
            case "setSmartWelcomeLightState":
                return "onSmartWelcomeLightStateChanged";
            case "setRemenberPowerOffState":
                return "onRemenberPowerOffStateChanged";
            case "setAppUseLimitState":
                return "onAppUseLimitStateChnaged";
            case "setRemoteControlUnlockWindowState":
                return "onRemoteUnlockWindowStateChanged";
            case "resetSeatParams":
                return "onRecoverSeatParamsChanged";
            case "setMaintainRemindState":
                return "onMaintainRemindStateChanged";
            case "setLowBatteryInd":
                return "onLowBatteryIndSwitchChanged";
            case "resetHudDisplayContent":
                return "onResetHudDisplayContent";
            case "setPM25SwitchCheck":
                return "onPM25SwitchCheckChanged";
            case "setAutoPurification":
                return "onAcAutoPurificationStateChanged";
            case "setMassageMode":
                return "onMassageModeStateChanged";
            case "setOverspeedLock":
                return "onOverSpeedLockStateChanged";
            case "setSeatHeatingState":
                return "onSeatHeatingStateChanged";
            case "setDisinfState":
                return "onDisinfStateChanged";
            case "recoverCushionHeight":
                return "onRecoverCushionHeight";
            case "setSeatHorization":
                return "onSeatHorizationChanged";
            case "setSOCTarget":
                return "onSOCTargetRangeChange";
            case "setSeatbackrestPostion":
                return "onSeatbackrestPostion";
            case "getRightHeadlampLevel":
                return "onRightHeadlampLevelChanged";
            case "resetHudRotationAngle":
                return "onResetHudRotationAngle";
            case "setBackDoorElectricMode":
                return "onBackDoorElectricModeChanged";
            case "setRemotControlDownwindowState":
                return "onRemoteControlSwitchChanged";
            case "setHudRotationAngle":
                return "onHudRotationAngleChanged";
            case "cancelRecoverParms":
                return "onCancelRecoverParms";
            case "turnSeatHorization":
                return "onTurnSeatHorizationChanged";
            case "turnSeatCaliperSplint":
                return "onTurnSeatCaliperSplintChanged";
            case "setNightDRLightOnInd":
                return "onNightDRLightOnIndSwitchChanged";
            case "setCarTipsSoundType":
                return "onCarTipsSoundTypeChanged";
            case "setSeatVentilatingState":
                return "onSeatVentilatingStateChanged";
            default:
                return ExtensionRequestData.EMPTY_VALUE;
        }
    }
}
