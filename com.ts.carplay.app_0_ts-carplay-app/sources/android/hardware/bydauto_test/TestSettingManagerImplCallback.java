package android.hardware.bydauto_test;

import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.lang.reflect.Method;

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

    public void setAbsBYDAutoStatisticListener(AbsBYDAutoSettingListener listener) {
        if (listener == null) {
            return;
        }
        this.mAbsBYDAutoSettingListener = listener;
    }

    public TestSettingManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestSettingManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestSettingManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestSettingManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    public void enqueueMessage(String what, Object... args) {
        if (this.mHandler != null) {
            Bundle bundle = new Bundle();
            bundle.putString("what", what);
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Integer) {
                    bundle.putInt(String.valueOf(i + 1), ((Integer) args[i]).intValue());
                }
                if (args[i] instanceof Float) {
                    bundle.putFloat(String.valueOf(i + 1), ((Float) args[i]).floatValue());
                }
                if (args[i] instanceof int[]) {
                    bundle.putIntArray(String.valueOf(i + 1), (int[]) args[i]);
                }
            }
            Message message = Message.obtain();
            message.obj = bundle;
            this.mHandler.sendMessage(message);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (this.mAbsBYDAutoSettingListener != null) {
            Bundle bundle = (Bundle) msg.obj;
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
            String what = bundle.getString("what");
            Process(bundle, what);
        }
    }

    private void Process(Bundle bundle, String what) {
        try {
            Method[] methods = AbsBYDAutoSettingListener.class.getDeclaredMethods();
            for (Method method : methods) {
                try {
                    if (method.getName().equals(nameChange(what))) {
                        if (method.getName().equals("onRecoverSeatParamsChanged")) {
                            method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(0.0f), 0);
                            return;
                        }
                        if (method.getName().equals("onSetHudParamsEffectChanged")) {
                            method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"));
                            return;
                        }
                        int count = method.getParameterCount();
                        if (count == 0) {
                            method.invoke(this.mAbsBYDAutoSettingListener, new Object[0]);
                            return;
                        }
                        if (count == 1) {
                            method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"));
                            return;
                        }
                        if (count == 2) {
                            method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"));
                            return;
                        }
                        if (count == 3) {
                            method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), bundle.get("3"));
                            return;
                        }
                        if (count == 4) {
                            method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"));
                            return;
                        } else if (count == 5) {
                            method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"));
                            return;
                        } else if (count == 6) {
                            method.invoke(this.mAbsBYDAutoSettingListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"), bundle.get("6"));
                            return;
                        }
                    }
                } catch (Exception e) {
                    e = e;
                    Log.d("TestSettingManagerImplCallback", "errormessage=" + e.getMessage());
                    return;
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:383:0x05e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String nameChange(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 2756
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto_test.TestSettingManagerImplCallback.nameChange(java.lang.String):java.lang.String");
    }
}
