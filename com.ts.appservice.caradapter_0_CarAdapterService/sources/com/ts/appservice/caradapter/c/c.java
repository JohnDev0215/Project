package com.ts.appservice.caradapter.c;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import com.ts.appservice.caradapter.d.w;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.assist.CarVendorAssistManager;
import com.ts.lib.car.hardware.setting.CarVendorSettingManager;
import com.ts.lib.car.hardware.setting.SettingEventChangeValue;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.setting.ISettingAdapterCallback;
import com.ts.lib.caradapter.setting.ISettingService;
import com.ts.lib.caradapter.setting.SettingPacket;
import java.util.Arrays;

/* JADX INFO: compiled from: AutoSettingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends ISettingService.Stub implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ts.appservice.caradapter.a.j f22a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f23b;
    private CarVendorSettingManager e;
    private CarVendorAssistManager f;
    private a g;
    private boolean d = false;
    private SettingPacket h = new SettingPacket();
    private HandlerThread c = com.ts.appservice.caradapter.e.b.a("VEHICLE-SETTING");

    /* JADX INFO: compiled from: AutoSettingImpl.java */
    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                c.this.i(message.arg1, message.arg2, (SettingEventChangeValue) message.obj);
                return;
            }
            LogUtil.error("AutoSettingImpl", "Unexpected message: " + message.what);
        }
    }

    public c(Context context) {
        this.f22a = new com.ts.appservice.caradapter.a.j(context);
        this.f23b = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i, int i2, SettingEventChangeValue settingEventChangeValue) {
        int autoPurificationState = -1;
        try {
            switch (i) {
                case 557846608:
                    autoPurificationState = settingEventChangeValue.getAutoPurificationState();
                    this.h.setAutoPurificationState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AUTO_PURIFICATION_STATE:" + autoPurificationState);
                    break;
                case 557847553:
                    autoPurificationState = settingEventChangeValue.getGoalRoadModeR();
                    this.h.setGoalRoadModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_GOAL_ROAD_MODE_R:" + autoPurificationState);
                    break;
                case 557856058:
                    autoPurificationState = settingEventChangeValue.getMotorPretensionerSeatbeltStateR();
                    this.h.setMotorPretensionerSeatbeltState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MOTOR_PRETENSIONER_SEATBELT_STATE_S :" + autoPurificationState);
                    break;
                case 557856063:
                    autoPurificationState = settingEventChangeValue.getRemoteKeyStartAcTimeR();
                    this.h.setRemoteKeyStartAcTime(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REMOTE_KEY_START_AC_TIME_R:" + autoPurificationState);
                    break;
                case 557856073:
                    autoPurificationState = settingEventChangeValue.getRearAcPanelAutoLockTimeR();
                    this.h.setRearAcPanelAutoLockTime(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_AC_PANEL_AUTO_LOCK_TIME_R :" + autoPurificationState);
                    break;
                case 557856076:
                    autoPurificationState = settingEventChangeValue.getDoorHeightSupport();
                    this.h.setDoorHeightSupport(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DOOR_HEIGHT_SUPPORT:" + autoPurificationState);
                    break;
                case 557856146:
                    autoPurificationState = settingEventChangeValue.getSteerPositionAutoReturnR();
                    this.h.setSteerPositionAutoReturn(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_STEER_POSITION_AUTO_RETURNS :" + autoPurificationState);
                    break;
                case 557856184:
                    autoPurificationState = settingEventChangeValue.getAmbientLightColorSupport();
                    this.h.setAmbientLightColorSupport(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AMBIENT_LIGHT_COLOR_SUPPORT:" + autoPurificationState);
                    break;
                case 557856185:
                    autoPurificationState = settingEventChangeValue.getAmbientLightSupport();
                    this.h.setAmbientLightSupport(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AMBIENT_LIGHT_SUPPORT:" + autoPurificationState);
                    break;
                case 557856186:
                    autoPurificationState = settingEventChangeValue.getAmbientAreaSupport();
                    this.h.setAmbientAreaSupport(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AMBIENT_AREA_SUPPORT:" + autoPurificationState);
                    break;
                case 557856889:
                    autoPurificationState = settingEventChangeValue.getBsisCollisionStateR();
                    this.h.setBsisCollisionStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BSIS_COLLISION_STATE_R:" + autoPurificationState);
                    break;
                case 557856891:
                    autoPurificationState = settingEventChangeValue.getBsisFuncStatusR();
                    this.h.setBsisFuncStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BSIS_FUNC_STATUS_R:" + autoPurificationState);
                    break;
                case 557856892:
                    autoPurificationState = settingEventChangeValue.getMoisAssembleStateR();
                    this.h.setMoisAssembleStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MOIS_ASSEMBLE_STATE_R:" + autoPurificationState);
                    break;
                case 557856894:
                    autoPurificationState = settingEventChangeValue.getMoisFuncStatusR();
                    this.h.setMoisFuncStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MOIS_FUNC_STATUS_R:" + autoPurificationState);
                    break;
                case 557857536:
                    autoPurificationState = settingEventChangeValue.getACBTWindR();
                    this.h.setACBTWind(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ACBT_WIND_R:" + autoPurificationState);
                    break;
                case 557857540:
                    autoPurificationState = settingEventChangeValue.getACPauseCycleR();
                    this.h.setACPauseCycle(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AC_PAUSE_CYCLE_R:" + autoPurificationState);
                    break;
                case 557857543:
                    autoPurificationState = settingEventChangeValue.getACAutoAirR();
                    this.h.setACAutoAir(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AC_AUTO_AIR_R:" + autoPurificationState);
                    break;
                case 557857549:
                    autoPurificationState = settingEventChangeValue.getPM25PowerR();
                    this.h.setPM25Power(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PM25_POWER_R:" + autoPurificationState);
                    break;
                case 557857551:
                    autoPurificationState = settingEventChangeValue.getPM25SwitchCheckR();
                    this.h.setPM25SwitchCheck(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PM25_SWITCH_CHECK_R:" + autoPurificationState);
                    break;
                case 557857553:
                    autoPurificationState = settingEventChangeValue.getPM25TimeCheckR();
                    this.h.setPM25TimeCheck(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PM25_TIMECHECK_R:" + autoPurificationState);
                    break;
                case 557857555:
                    autoPurificationState = settingEventChangeValue.getACTunnelCycleR();
                    this.h.setACTunnelCycle(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AC_TUNNEL_CYCLE_R:" + autoPurificationState);
                    break;
                case 557857557:
                    autoPurificationState = settingEventChangeValue.getAcWarmModeR();
                    this.h.setAcWarmMode(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AC_WARM_MODE_R:" + autoPurificationState);
                    break;
                case 557857560:
                    autoPurificationState = settingEventChangeValue.getIntelligentAcAreaStateS();
                    this.h.setIntelligentAcAreaState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_INTELLIGENT_AC_AREASTATE_S:" + autoPurificationState);
                    break;
                case 557857561:
                    autoPurificationState = settingEventChangeValue.getBackDoorOpenedHeightR();
                    this.h.setBackDoorOpenedHeight(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BACK_DOOR_OPENED_HEIGHT_R:" + autoPurificationState);
                    break;
                case 557857563:
                    autoPurificationState = settingEventChangeValue.getRainAutoLockWindowStateR();
                    this.h.setRainAutoLockWindowState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RAIN_AUTO_LOCK_WINDOW_STATE_R:" + autoPurificationState);
                    break;
                case 557857565:
                    autoPurificationState = settingEventChangeValue.getDoorknobAutoShrinkStateR();
                    this.h.setDoorknobAutoShrinkState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DOOR_KNOB_AUTO_SHRINK_STATE_R:" + autoPurificationState);
                    break;
                case 557857567:
                    autoPurificationState = settingEventChangeValue.getIntelligentUnlockStateR();
                    this.h.setIntelligentUnlockState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_INTELLIGENT_UNLOCK_STATE_R:" + autoPurificationState);
                    break;
                case 557857569:
                    autoPurificationState = settingEventChangeValue.getIntelligentLockStateR();
                    this.h.setIntelligentLockState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_INTELLIGENT_LOCK_STATE_R:" + autoPurificationState);
                    break;
                case 557857571:
                    autoPurificationState = settingEventChangeValue.getHudStateR();
                    this.h.setHudState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_STATE_R :" + autoPurificationState);
                    break;
                case 557857574:
                    autoPurificationState = settingEventChangeValue.getHudHeightR();
                    this.h.setHudHeight(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_HEIGHT_R :" + autoPurificationState);
                    break;
                case 557857576:
                    autoPurificationState = settingEventChangeValue.getHudBrightnessR();
                    this.h.setHudBrightness(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_BRIGHTNESS_R :" + autoPurificationState);
                    break;
                case 557857578:
                    autoPurificationState = settingEventChangeValue.getHudRotationAngleR();
                    this.h.setHudRotationAngle(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_ROTATION_ANGLE_R :" + autoPurificationState);
                    break;
                case 557857580:
                    autoPurificationState = settingEventChangeValue.getHudModeR();
                    this.h.setHudMode(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_MODE_R :" + autoPurificationState);
                    break;
                case 557857582:
                    autoPurificationState = settingEventChangeValue.getHudDisplayContentRDrive();
                    this.h.setHudDisplayContentRDrive(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_DISPLAY_CONTENT_R_DRIVE :" + autoPurificationState);
                    break;
                case 557857583:
                    autoPurificationState = settingEventChangeValue.getHudDisplayContentRNavigation();
                    this.h.setHudDisplayContentRNavigation(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_DISPLAY_CONTENT_R_NAVIGATION :" + autoPurificationState);
                    break;
                case 557857584:
                    autoPurificationState = settingEventChangeValue.getHudDisplayContentSDrive();
                    this.h.setHudDisplayContentSDrive(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_DISPLAY_CONTENT_S_DRIVE :" + autoPurificationState);
                    break;
                case 557857585:
                    autoPurificationState = settingEventChangeValue.getHudDisplayContentSNavigation();
                    this.h.setHudDisplayContentSNavigation(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_DISPLAY_CONTENT_S_NAVIGATION :" + autoPurificationState);
                    break;
                case 557857587:
                    autoPurificationState = settingEventChangeValue.getEngineSoundTypeR();
                    this.h.setEngineSoundType(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ENGINE_SOUND_TYPE_R :" + autoPurificationState);
                    break;
                case 557857589:
                    autoPurificationState = settingEventChangeValue.getSpeedReminderValueR();
                    this.h.setSpeedReminderValue(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SPEED_REMINDER_VALUE_R :" + autoPurificationState);
                    break;
                case 557857591:
                    autoPurificationState = settingEventChangeValue.getRearViewMirrorFlipR();
                    this.h.setRearViewMirrorFlip(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_VIEW_MIRROR_FLIP_R :" + autoPurificationState);
                    break;
                case 557857592:
                    autoPurificationState = settingEventChangeValue.getRearViewMirrorFlipSetOneR();
                    this.h.setRearViewMirrorFlipSet(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_VIEW_MIRROR_FLIP_S_WITH_MEMORY :" + autoPurificationState);
                    break;
                case 557857595:
                    autoPurificationState = settingEventChangeValue.getAutoPowerOnStateR();
                    this.h.setAutoPowerOnState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AUTO_POWER_ON_STATE_S :" + autoPurificationState);
                    break;
                case 557857597:
                    autoPurificationState = settingEventChangeValue.getAutoPowerOffStateR();
                    this.h.setAutoPowerOffState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AUTO_POWER_OFF_STATE_S :" + autoPurificationState);
                    break;
                case 557857599:
                    autoPurificationState = settingEventChangeValue.getDriverSeatAutoReturnR();
                    this.h.setDriverSeatAutoReturn(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRIVER_SEAT_AUTO_RETURNS :" + autoPurificationState);
                    break;
                case 557857603:
                    autoPurificationState = settingEventChangeValue.getBrakePedalModeR();
                    this.h.setBrakePedalMode(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BRAKE_PEDAL_MODE_S :" + autoPurificationState);
                    break;
                case 557857605:
                    autoPurificationState = settingEventChangeValue.getComfortableParkingStateR();
                    this.h.setComfortableParkingState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_COMFORTABLE_PARKING_STATE_S :" + autoPurificationState);
                    break;
                case 557857609:
                    autoPurificationState = settingEventChangeValue.getresetMotorPretensionerSeatbelt();
                    this.h.setresetMotorPretensionerSeatbelt(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AUTO_RAIN_WIPER_STATE_S :" + autoPurificationState);
                    break;
                case 557857611:
                    autoPurificationState = settingEventChangeValue.getDynamicSuspensionModeR();
                    this.h.setDynamicSuspensionMode(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DYNAMIC_SUSPENSION_MODE_S :" + autoPurificationState);
                    break;
                case 557857613:
                    autoPurificationState = settingEventChangeValue.getRemoteControlUpwindowStateR();
                    this.h.setRemoteControlUpwindowState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REMOTE_CONTROL_UPWINDOW_STATE_S :" + autoPurificationState);
                    break;
                case 557857615:
                    autoPurificationState = settingEventChangeValue.getRemoteControlDownwindowStateR();
                    this.h.setRemoteControlDownwindowState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REMOTE_CONTROL_DOWNWINDOW_STATE_S_48E :" + autoPurificationState);
                    break;
                case 557857617:
                    autoPurificationState = settingEventChangeValue.getMicroSwitchLockWindowStateR();
                    this.h.setMicroSwitchLockWindowState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MICRO_SWITCH_LOCK_WINDOW_STATE_R:" + autoPurificationState);
                    break;
                case 557857619:
                    autoPurificationState = settingEventChangeValue.getMicroSwitchUnlockWindowStateR();
                    this.h.setMicroSwitchUnlockWindowState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MICRO_SWITCH_UNLOCK_WINDOW_STATE_R:" + autoPurificationState);
                    break;
                case 557857621:
                    autoPurificationState = settingEventChangeValue.getLockCarRiseWindowR48E();
                    this.h.setLockCarRiseWindow(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LOCK_CAR_RISE_WINDOW_R48E:" + autoPurificationState);
                    break;
                case 557857624:
                    autoPurificationState = settingEventChangeValue.getLeftHomeLightDelayValueR();
                    this.h.setLeftHomeLightDelayValue(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LEFT_HOME_LIGHT_DELAY_VALUE_R :" + autoPurificationState);
                    break;
                case 557857626:
                    autoPurificationState = settingEventChangeValue.getBackHomeLightDelayValueR();
                    this.h.setBackHomeLightDelayValue(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BACK_HOME_LIGHT_DELAY_VALUE_R :" + autoPurificationState);
                    break;
                case 557857664:
                    autoPurificationState = settingEventChangeValue.getSeatHorizationDriver();
                    this.h.setSeatHorizationDriver(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HORIZATION_DRIVER :" + autoPurificationState);
                    break;
                case 557857665:
                    autoPurificationState = settingEventChangeValue.getSeatHorizationCopilot();
                    this.h.setSeatHorizationCopilot(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HORIZATION_COPILOT :" + autoPurificationState);
                    break;
                case 557857666:
                    autoPurificationState = settingEventChangeValue.getSeatHorizationBackLeft();
                    this.h.setSeatHorizationBackLeft(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HORIZATION_BACK_LEFT :" + autoPurificationState);
                    break;
                case 557857667:
                    autoPurificationState = settingEventChangeValue.getSeatHorizationBackRight();
                    this.h.setSeatHorizationBackRight(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HORIZATION_BACK_RIGHT :" + autoPurificationState);
                    break;
                case 557857688:
                    autoPurificationState = settingEventChangeValue.getHeadrestHorizationDriver();
                    this.h.setHeadrestHorizationDriver(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HEADREST_HORIZATION_DRIVER :" + autoPurificationState);
                    break;
                case 557857689:
                    autoPurificationState = settingEventChangeValue.getHeadrestHorizationCopilot();
                    this.h.setHeadrestHorizationCopilot(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HEADREST_HORIZATION_COPILOT :" + autoPurificationState);
                    break;
                case 557857690:
                    autoPurificationState = settingEventChangeValue.getHeadrestHorizationBackLeft();
                    this.h.setHeadrestHorizationBackLeft(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HEADREST_HORIZATION_BACK_LEFT :" + autoPurificationState);
                    break;
                case 557857691:
                    autoPurificationState = settingEventChangeValue.getHeadrestHorizationBackRight();
                    this.h.setHeadrestHorizationBackRight(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HEADREST_HORIZATION_BACK_RIGHT :" + autoPurificationState);
                    break;
                case 557857699:
                    autoPurificationState = settingEventChangeValue.getRefrigeratorModeR();
                    this.h.setRefrigeratorMode(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REFRIGERATOR_MODE_R :" + autoPurificationState);
                    break;
                case 557857701:
                    autoPurificationState = settingEventChangeValue.getRemenberPowerOffStateR();
                    this.h.setRemenberPowerOffState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REMENBER_POWER_OFF_STATER :" + autoPurificationState);
                    break;
                case 557857705:
                    autoPurificationState = settingEventChangeValue.getDisinfStateR();
                    this.h.setDisinfState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DISINF_STATE_R :" + autoPurificationState);
                    break;
                case 557857706:
                    autoPurificationState = settingEventChangeValue.getTrayStateR();
                    this.h.setTrayState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TRAY_STATE_R :" + autoPurificationState);
                    break;
                case 557857708:
                    autoPurificationState = settingEventChangeValue.getRefrigeratorState();
                    this.h.setRefrigeratorState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REFRIGERATOR_STATE :" + autoPurificationState);
                    break;
                case 557857710:
                    autoPurificationState = settingEventChangeValue.getIALColorStarRingR();
                    this.h.setIALColorStarRing(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_COLOR_STAR_RING_R :" + autoPurificationState);
                    break;
                case 557857711:
                    autoPurificationState = settingEventChangeValue.getIALColorStarrySkyR();
                    this.h.setIALColorStarrySky(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_COLOR_STARRY_SKY_R :" + autoPurificationState);
                    break;
                case 557857712:
                    autoPurificationState = settingEventChangeValue.getIALColorFrontR();
                    this.h.setIALColorFront(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_COLOR_FRONT_R :" + autoPurificationState);
                    break;
                case 557857713:
                    autoPurificationState = settingEventChangeValue.getIALColorBackR();
                    this.h.setIALColorBack(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_COLOR_BACK_R :" + autoPurificationState);
                    break;
                case 557857716:
                    autoPurificationState = settingEventChangeValue.getIALBrightnessStarRingR();
                    this.h.setIALBrightnessStarRing(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_BRIGHTNESS_STAR_RING_R :" + autoPurificationState);
                    break;
                case 557857717:
                    autoPurificationState = settingEventChangeValue.getIALBrightnessStarrySkyR();
                    this.h.setIALBrightnessStarrySky(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_BRIGHTNESS_STARRY_SKY_R :" + autoPurificationState);
                    break;
                case 557857718:
                    autoPurificationState = settingEventChangeValue.getIALBrightnessBackR();
                    this.h.setIALBrightnessBack(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_BRIGHTNESS_BACK_R :" + autoPurificationState);
                    break;
                case 557857719:
                    autoPurificationState = settingEventChangeValue.getIALBrightnessFrontR();
                    this.h.setIALBrightnessFront(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_BRIGHTNESS_FRONT_R :" + autoPurificationState);
                    break;
                case 557857721:
                    autoPurificationState = settingEventChangeValue.getIALAreaR();
                    this.h.setIALArea(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IAL_AREA_R :" + autoPurificationState);
                    break;
                case 557857723:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingStateDriverDomainControl();
                    this.h.setSeatHeatingStateDriverDomainControl(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE_DRIVER_DOMAIN_CONTROL :" + autoPurificationState);
                    break;
                case 557857725:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingStateCopilotECU();
                    this.h.setSeatHeatingStateCopilotECU(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE_COPILOT_ECU :" + autoPurificationState);
                    break;
                case 557857726:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingStateBackLeftECU();
                    this.h.setSeatHeatingStateBackLeftECU(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE_BACKLEFT_ECU :" + autoPurificationState);
                    break;
                case 557857727:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingStateBackRightECU();
                    this.h.setSeatHeatingStateBackRightECU(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE_BACKRIGHT_ECU :" + autoPurificationState);
                    break;
                case 557857728:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingStateCopilotL1P();
                    this.h.setSeatHeatingStateCopilotL1P(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE_COPILOT_L1P :" + autoPurificationState);
                    break;
                case 557857729:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingStateCopilotNonL1P();
                    this.h.setSeatHeatingStateCopilotNonL1P(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE_COPILOTNON_L1P :" + autoPurificationState);
                    break;
                case 557857730:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingStateBackLeftDomainControlR();
                    this.h.setSeatHeatingStateBackLeftDomainControl(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE_BACK_LEFT_DOMAIN_CONTROL_R:" + autoPurificationState);
                    break;
                case 557857731:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingStateBackRightDomainControlR();
                    this.h.setSeatHeatingStateBackRightDomainControl(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE_BACK_LEFT_DOMAIN_CONTROL_R:" + autoPurificationState);
                    break;
                case 557857733:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingStateDriverDomainControlR();
                    this.h.setSeatVentilatingStateDriverDomainControl(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_STATE_DRIVER_DOMAIN_CONTROL_R :" + autoPurificationState);
                    break;
                case 557857737:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingStateBackRightECU();
                    this.h.setSeatVentilatingStateBackRightECU(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_STATE_BACK_RIGHT_ECU :" + autoPurificationState);
                    break;
                case 557857738:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingStateCopilotL1P();
                    this.h.setSeatVentilatingStateCopilotL1P(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_STATE_COPILOT_L1P :" + autoPurificationState);
                    break;
                case 557857739:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingStateCopilotNonL1P();
                    this.h.setSeatVentilatingStateCopilotNonL1P(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_STATE_COPILOTNON_L1P :" + autoPurificationState);
                    break;
                case 557857740:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingStateBackLeftDomainControlR();
                    this.h.setSeatVentilatingStateBackLeftDomainControl(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_STATE_BACK_LEFT_DOMAIN_CONTROLR :" + autoPurificationState);
                    break;
                case 557857741:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingStateBackRightDomainControlR();
                    this.h.setSeatVentilatingStateBackRightDomainControl(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_STATE_BACK_RIGHT_DOMAIN_CONTROLR :" + autoPurificationState);
                    break;
                case 557857744:
                    autoPurificationState = settingEventChangeValue.getLanternLanguageModeR();
                    this.h.setLanternLanguageMode(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LANTERN_LANGUAGE_MODE_R:" + autoPurificationState);
                    break;
                case 557857745:
                    autoPurificationState = settingEventChangeValue.getINSThemeR();
                    this.h.setINSTheme(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_INS_THEME_R :" + autoPurificationState);
                    break;
                case 557857747:
                    autoPurificationState = settingEventChangeValue.getHeadlampLevelR();
                    this.h.setHeadlampLevel(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HEAD_LAMP_LEVEL_R:" + autoPurificationState);
                    break;
                case 557857749:
                    autoPurificationState = settingEventChangeValue.getLeftHeadlampLevel();
                    this.h.setLeftHeadlampLevel(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LEFT_HEAD_LAMP_LEVEL :" + autoPurificationState);
                    break;
                case 557857750:
                    autoPurificationState = settingEventChangeValue.getRightHeadlampLevel();
                    this.h.setRightHeadlampLevel(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RIGHT_HEAD_LAMP_LEVEL :" + autoPurificationState);
                    break;
                case 557857751:
                    autoPurificationState = settingEventChangeValue.getHeadLampConfig();
                    this.h.setHeadLampConfig(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HEAD_LAMP_CONFIG :" + autoPurificationState);
                    break;
                case 557857752:
                    autoPurificationState = settingEventChangeValue.getSmartWelcomeLightStateR();
                    this.h.setSmartWelcomeLightState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SMART_WELCOME_LIGHT_STATE_R :" + autoPurificationState);
                    break;
                case 557857764:
                    autoPurificationState = settingEventChangeValue.getMassageLevelLeftFront();
                    this.h.setMassageLevelLeftFront(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_LEVEL_LEFT_FRONT :" + autoPurificationState);
                    break;
                case 557857766:
                    autoPurificationState = settingEventChangeValue.getMassageLevelRightFront();
                    this.h.setMassageLevelRightFront(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_LEVEL_RIGHT_FRONT :" + autoPurificationState);
                    break;
                case 557857768:
                    autoPurificationState = settingEventChangeValue.getMassageLevelLeftBack();
                    this.h.setMassageLevelLeftBack(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_LEVEL_LEFT_BACK :" + autoPurificationState);
                    break;
                case 557857769:
                    autoPurificationState = settingEventChangeValue.getMassageLevelLeftBackECU();
                    this.h.setMassageLevelLeftBackECU(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_LEVEL_LEFT_BACK_ECU :" + autoPurificationState);
                    break;
                case 557857770:
                    autoPurificationState = settingEventChangeValue.getMassageLevelRightBackECU();
                    this.h.setMassageLevelRightBackECU(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_LEVEL_RIGHT_BACK_ECU :" + autoPurificationState);
                    break;
                case 557857771:
                    autoPurificationState = settingEventChangeValue.getMassageLevelRightBack();
                    this.h.setMassageLevelRightBack(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_LEVEL_RIGHT_BACK :" + autoPurificationState);
                    break;
                case 557857776:
                    autoPurificationState = settingEventChangeValue.getMassageModeR();
                    this.h.setMassageMode(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_MODE_R:" + autoPurificationState);
                    break;
                case 557857778:
                    autoPurificationState = settingEventChangeValue.getMassageModeR4FA();
                    this.h.setMassageModeR4Fa(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_MODE_R_4FA:" + autoPurificationState);
                    break;
                case 557857780:
                    autoPurificationState = settingEventChangeValue.getMassageModeR4FALeftBack();
                    this.h.setMassageModeR4FaLeftBack(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_MODE_R_4FA_LEFT_BACK :" + autoPurificationState);
                    break;
                case 557857783:
                    autoPurificationState = settingEventChangeValue.getMassageModeR4FALeftFront();
                    this.h.setMassageModeR4FaLeftFront(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_MODE_R_4FA_RIGHT_BACK :" + autoPurificationState);
                    break;
                case 557857788:
                    autoPurificationState = settingEventChangeValue.getEnergyFeedbackR341();
                    this.h.setEnergyFeedbackR341(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ENERGY_FEEDBACK_R_341 :" + autoPurificationState);
                    break;
                case 557857790:
                    autoPurificationState = settingEventChangeValue.getSOCTargetR();
                    this.h.setSOCTarget(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SOC_TARGET_R :" + autoPurificationState);
                    break;
                case 557857792:
                    autoPurificationState = settingEventChangeValue.getChargingPortR27C();
                    this.h.setChargingPort(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CHARGING_PORT_R_27C:" + autoPurificationState);
                    break;
                case 557857793:
                    autoPurificationState = settingEventChangeValue.getChargingPortR475();
                    this.h.setChargingPort(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CHARGING_PORT_R_475:" + autoPurificationState);
                    break;
                case 557857796:
                    autoPurificationState = settingEventChangeValue.getAutoExternalRearMirrorFollowUpSwitchR();
                    this.h.setAutoExternalRearMirrorFollowUpSwitch(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AUTO_EXTERNAL_REAR_MIRROR_FOLLOW_UP_SWITCH_R :" + autoPurificationState);
                    break;
                case 557857799:
                    autoPurificationState = settingEventChangeValue.getOverspeedLockR();
                    this.h.setOverspeedLock(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ACBT_WIND_R:" + autoPurificationState);
                    break;
                case 557857800:
                    autoPurificationState = settingEventChangeValue.getSteerAssisR();
                    this.h.setSteerAssis(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_STEER_ASSIS_R :" + autoPurificationState);
                    break;
                case 557857802:
                    autoPurificationState = settingEventChangeValue.getSteerAssisSupported();
                    this.h.setSteerAssisSupportedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_STEER_ASSIS_SUPPORTED :" + autoPurificationState);
                    break;
                case 557857804:
                    autoPurificationState = settingEventChangeValue.getonTrayStateChanged();
                    this.h.setonTrayStateChanged(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ON_TRAY_STATE_CHANGED :" + autoPurificationState);
                    break;
                case 557857805:
                    autoPurificationState = settingEventChangeValue.getonSaveSeatParamsAllChangedPlace5();
                    this.h.setonSaveSeatParamsAllChangedPlace5(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_PLACE5:" + autoPurificationState);
                    break;
                case 557857806:
                    autoPurificationState = settingEventChangeValue.getonSaveSeatParamsAllChangedPlace7();
                    this.h.setonSaveSeatParamsAllChangedPlace7(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_PLACE7:" + autoPurificationState);
                    break;
                case 557857807:
                    autoPurificationState = settingEventChangeValue.getonSaveSeatParamsAllChanged3CA();
                    this.h.setonSaveSeatParamsAllChanged3Ca(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_3CA:" + autoPurificationState);
                    break;
                case 557857808:
                    autoPurificationState = settingEventChangeValue.getonSaveSeatParamsAllChanged402();
                    this.h.setonSaveSeatParamsAllChanged402(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_402:" + autoPurificationState);
                    break;
                case 557857809:
                    autoPurificationState = settingEventChangeValue.getonSaveSeatParamsAllChanged42F();
                    this.h.setonSaveSeatParamsAllChanged42F(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_42F:" + autoPurificationState);
                    break;
                case 557857813:
                    autoPurificationState = settingEventChangeValue.getSteeringWheelHeatingStateR();
                    this.h.setSteeringWheelHeatingState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MASSAGE_MODE_R:" + autoPurificationState);
                    break;
                case 557857814:
                    autoPurificationState = settingEventChangeValue.getLockOffR();
                    this.h.setLockOff(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ACBT_WIND_R:" + autoPurificationState);
                    break;
                case 557857833:
                    autoPurificationState = settingEventChangeValue.getComSpeedDownStateR();
                    this.h.setComSpeedDownState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_COM_SPEED_DOWN_STATER :" + autoPurificationState);
                    break;
                case 557857835:
                    autoPurificationState = settingEventChangeValue.getHSpeedAutoCloseStateR();
                    this.h.setHSpeedAutoCloseState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_H_SPEED_AUTO_CLOSE_STATER :" + autoPurificationState);
                    break;
                case 557857838:
                    autoPurificationState = settingEventChangeValue.getComSpeedUpStateR();
                    this.h.setComSpeedUpState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_COM_SPEED_UP_STATER :" + autoPurificationState);
                    break;
                case 557857840:
                    autoPurificationState = settingEventChangeValue.getPassengerStateR();
                    this.h.setPassengerStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAFETY_BELT_PASSENGER_COMMAND_FRONT_ROW_SEAT_RIGHT:" + autoPurificationState);
                    break;
                case 557857841:
                    autoPurificationState = settingEventChangeValue.getPassengerRearLeftStateR();
                    this.h.setPassengerRearLeftStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAFETY_BELT_PASSENGER_COMMAND_SECOND_ROW_SEAT_LEFT:" + autoPurificationState);
                    break;
                case 557857842:
                    autoPurificationState = settingEventChangeValue.getPassengerRearRightStateR();
                    this.h.setPassengerRearRightStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAFETY_BELT_PASSENGER_COMMAND_SECOND_ROW_SEAT_MID:" + autoPurificationState);
                    break;
                case 557857843:
                    autoPurificationState = settingEventChangeValue.getPassengerRearMiddleStateR();
                    this.h.setPassengerRearMiddleStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAFETY_BELT_PASSENGER_COMMAND_SECOND_ROW_SEAT_RIGHT:" + autoPurificationState);
                    break;
                case 557857844:
                    autoPurificationState = settingEventChangeValue.getPassengerLastLeftStateR();
                    this.h.setPassengerLastLeftStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SETTING_THIRD_ROW_LEFT_CHAIR:" + autoPurificationState);
                    break;
                case 557857845:
                    autoPurificationState = settingEventChangeValue.getPassengerLastRightStateR();
                    this.h.setPassengerLastRightStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SETTING_THIRD_ROW_MIDDLE_CHAIR:" + autoPurificationState);
                    break;
                case 557857846:
                    autoPurificationState = settingEventChangeValue.getPassengerLastMiddleStateR();
                    this.h.setPassengerLastMiddleStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SETTING_THIRD_ROW_RIGHT_CHAIR:" + autoPurificationState);
                    break;
                case 557857847:
                    autoPurificationState = settingEventChangeValue.getRecoverOrSaveParamsChanged2();
                    this.h.setRecoverOrSaveParamsChanged2(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RECOVERORSAVEPARAMSCHANGED2:" + autoPurificationState);
                    break;
                case 557857848:
                    autoPurificationState = settingEventChangeValue.getRecoverOrSaveParamsChanged3();
                    this.h.setRecoverOrSaveParamsChanged3(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RECOVERORSAVEPARAMSCHANGED3:" + autoPurificationState);
                    break;
                case 557857849:
                    autoPurificationState = settingEventChangeValue.getRecoverOrSaveParamsChanged4();
                    this.h.setRecoverOrSaveParamsChanged4(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RECOVERORSAVEPARAMSCHANGED4:" + autoPurificationState);
                    break;
                case 557857850:
                    autoPurificationState = settingEventChangeValue.getRecoverOrSaveParamsChanged5();
                    this.h.setRecoverOrSaveParamsChanged5(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RECOVERORSAVEPARAMSCHANGED5:" + autoPurificationState);
                    break;
                case 557857851:
                    autoPurificationState = settingEventChangeValue.getRecoverOrSaveParamsChanged6();
                    this.h.setRecoverOrSaveParamsChanged6(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RECOVERORSAVEPARAMSCHANGED6:" + autoPurificationState);
                    break;
                case 557857852:
                    autoPurificationState = settingEventChangeValue.getRecoverOrSaveParamsChanged7();
                    this.h.setRecoverOrSaveParamsChanged7(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RECOVERORSAVEPARAMSCHANGED7:" + autoPurificationState);
                    break;
                case 557857853:
                    autoPurificationState = settingEventChangeValue.getHudPatternR();
                    this.h.setHudPatternR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_PATTERN_R :" + autoPurificationState);
                    break;
                case 557857855:
                    autoPurificationState = settingEventChangeValue.getHudSafeDriveStateR();
                    this.h.setHudSafeDriveStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_SAFE_DRIVE_STATE_R :" + autoPurificationState);
                    break;
                case 557857857:
                    autoPurificationState = settingEventChangeValue.getHudNavigationStateR();
                    this.h.setHudNavigationStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_NAVIGATION_STATE_R :" + autoPurificationState);
                    break;
                case 557857859:
                    autoPurificationState = settingEventChangeValue.getHudScreenProjectionStateR();
                    this.h.setHudScreenProjectionStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HUD_SCREEN_PROJECTION_STATE_R :" + autoPurificationState);
                    break;
                case 557857862:
                    autoPurificationState = settingEventChangeValue.getRuningCloseDoorStateR();
                    this.h.setRuningCloseDoorStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RUNING_CLOSE_DOOR_STATE_R :" + autoPurificationState);
                    break;
                case 557857864:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingState2BackLeftR();
                    this.h.setSeatHeatingState2BackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE2_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857865:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingState2BackRightR();
                    this.h.setSeatHeatingState2BackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_STATE2_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857866:
                    autoPurificationState = settingEventChangeValue.getSeatHeatingNotL1PStateR();
                    this.h.setSeatHeatingNotL1PStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_HEATING_NOT_L1_P_STATE_R :" + autoPurificationState);
                    break;
                case 557857867:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingState2BackLeftR();
                    this.h.setSeatVentilatingState2BackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_STATE2_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857868:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingState2BackRightR();
                    this.h.setSeatVentilatingState2BackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_STATE2_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857869:
                    autoPurificationState = settingEventChangeValue.getSeatVentilatingNotL1PStateR();
                    this.h.setSeatVentilatingNotL1PStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_VENTILATING_NOT_L1_P_STATE_R :" + autoPurificationState);
                    break;
                case 557857870:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingStateDomainR();
                    this.h.set5LevelSeatVentilatingStateDomainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_STATE_DOMAIN_R :" + autoPurificationState);
                    break;
                case 557857871:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingStatePassengerR();
                    this.h.set5LevelSeatVentilatingStatePassengerR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_STATE_PASSENGER_R :" + autoPurificationState);
                    break;
                case 557857872:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingStateBackLeftR();
                    this.h.set5LevelSeatVentilatingStateBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857873:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingStateBackRightR();
                    this.h.set5LevelSeatVentilatingStateBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857878:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingStateDomainR();
                    this.h.set5LevelSeatHeatingStateDomainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_STATE_DOMAIN_R :" + autoPurificationState);
                    break;
                case 557857879:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingStatePassengerR();
                    this.h.set5LevelSeatHeatingStatePassengerR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_STATE_PASSENGER_R :" + autoPurificationState);
                    break;
                case 557857880:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingStateBackLeftR();
                    this.h.set5LevelSeatHeatingStateBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_STATE_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857881:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingStateBackRightR();
                    this.h.set5LevelSeatHeatingStateBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_STATE_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857886:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingModeDomainR();
                    this.h.set5LevelSeatVentilatingModeDomainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_MODE_DOMAIN_R :" + autoPurificationState);
                    break;
                case 557857887:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingModePassengerR();
                    this.h.set5LevelSeatVentilatingModePassengerR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_MODE_PASSENGER_R :" + autoPurificationState);
                    break;
                case 557857888:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingModeBackLeftR();
                    this.h.set5LevelSeatVentilatingModeBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857889:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingModeBackRightR();
                    this.h.set5LevelSeatVentilatingModeBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857894:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingModeDomainR();
                    this.h.set5LevelSeatHeatingModeDomainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_MODE_DOMAIN_R :" + autoPurificationState);
                    break;
                case 557857895:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingModePassengerR();
                    this.h.set5LevelSeatHeatingModePassengerR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_MODE_PASSENGER_R :" + autoPurificationState);
                    break;
                case 557857896:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingModeBackLeftR();
                    this.h.set5LevelSeatHeatingModeBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_MODE_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857897:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingModeBackRightR();
                    this.h.set5LevelSeatHeatingModeBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_MODE_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857902:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingLevelDomainR();
                    this.h.set5LevelSeatVentilatingLevelDomainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_LEVEL_DOMAIN_R :" + autoPurificationState);
                    break;
                case 557857903:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingLevelPassengerR();
                    this.h.set5LevelSeatVentilatingLevelPassengerR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_LEVEL_PASSENGER_R :" + autoPurificationState);
                    break;
                case 557857904:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingLevelBackLeftR();
                    this.h.set5LevelSeatVentilatingLevelBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857905:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatVentilatingLevelBackRightR();
                    this.h.set5LevelSeatVentilatingLevelBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857910:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingLevelDomainR();
                    this.h.set5LevelSeatHeatingLevelDomainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_LEVEL_DOMAIN_R :" + autoPurificationState);
                    break;
                case 557857911:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingLevelPassengerR();
                    this.h.set5LevelSeatHeatingLevelPassengerR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_LEVEL_PASSENGER_R :" + autoPurificationState);
                    break;
                case 557857912:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingLevelBackLeftR();
                    this.h.set5LevelSeatHeatingLevelBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857913:
                    autoPurificationState = settingEventChangeValue.get5LevelSeatHeatingLevelBackRightR();
                    this.h.set5LevelSeatHeatingLevelBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857919:
                    autoPurificationState = settingEventChangeValue.getSteeringWheelHeatingGearR();
                    this.h.setSteeringWheelHeatingGearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_STEERING_WHEEL_HEATING_GEAR_R :" + autoPurificationState);
                    break;
                case 557857921:
                    autoPurificationState = settingEventChangeValue.getSteeringWheelHeatingAutoStateR();
                    this.h.setSteeringWheelHeatingAutoStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_STEERING_WHEEL_HEATING_AUTO_STATE_R :" + autoPurificationState);
                    break;
                case 557857922:
                    autoPurificationState = settingEventChangeValue.getEcuMassageLevelBackLeftR();
                    this.h.setEcuMassageLevelBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ECU_MASSAGE_LEVEL_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857923:
                    autoPurificationState = settingEventChangeValue.getEcuMassageLevelBackRightR();
                    this.h.setEcuMassageLevelBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ECU_MASSAGE_LEVEL_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857928:
                    autoPurificationState = settingEventChangeValue.getEcuMassageModeBackLeftR();
                    this.h.setEcuMassageModeBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ECU_MASSAGE_MODE_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557857929:
                    autoPurificationState = settingEventChangeValue.getEcuMassageModeBackRightR();
                    this.h.setEcuMassageModeBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ECU_MASSAGE_MODE_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557857938:
                    autoPurificationState = settingEventChangeValue.getHsMassageStateDomainR();
                    this.h.setHsMassageStateDomainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HS_MASSAGE_STATE_DOMAIN_R :" + autoPurificationState);
                    break;
                case 557857944:
                    autoPurificationState = settingEventChangeValue.getHevChargingPortR();
                    this.h.setHevChargingPortR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HEV_CHARGING_PORT_R :" + autoPurificationState);
                    break;
                case 557857945:
                    autoPurificationState = settingEventChangeValue.getKeyVibratorLevelR();
                    this.h.setKeyVibratorLevelR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_KEY_VIBRATOR_LEVEL_R :" + autoPurificationState);
                    break;
                case 557857947:
                    autoPurificationState = settingEventChangeValue.getIviProfileSettingsR();
                    this.h.setIviProfileSettingsR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_IVI_PROFILE_SETTINGS_R:" + autoPurificationState);
                    break;
                case 557857951:
                    autoPurificationState = settingEventChangeValue.getFdComSpeedUpStateR();
                    this.h.setFdComSpeedUpStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_COM_SPEED_UP_STATE_R:" + autoPurificationState);
                    break;
                case 557857957:
                    autoPurificationState = settingEventChangeValue.getRearviewMirrorIsTurnedStateR();
                    this.h.setRearviewMirrorIsTurnedStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REARVIEW_MIRROR_IS_TURNED_STATE_R:" + autoPurificationState);
                    break;
                case 557857958:
                    autoPurificationState = settingEventChangeValue.getRearviewMirrorIsStateR();
                    this.h.setRearviewMirrorIsStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REARVIEW_MIRROR_IS_STATE_R:" + autoPurificationState);
                    break;
                case 557857959:
                    autoPurificationState = settingEventChangeValue.getRearviewMirrorIsAutoLightStateR();
                    this.h.setRearviewMirrorIsAutoLightStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REARVIEW_MIRROR_IS_TURNED_STATE_AUTO_LIGHT_R:" + autoPurificationState);
                    break;
                case 557857962:
                    autoPurificationState = settingEventChangeValue.getRefrigeratorWorkModeR();
                    this.h.setRefrigeratorWorkModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REFRIGERATOR_WORK_MODE_R:" + autoPurificationState);
                    break;
                case 557857964:
                    autoPurificationState = settingEventChangeValue.getRefrigeratorWorkTemperatureR();
                    this.h.setRefrigeratorWorkTemperatureR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REFRIGERATOR_WORK_TEMPERATURE_R:" + autoPurificationState);
                    break;
                case 557857965:
                    autoPurificationState = settingEventChangeValue.getFridgeWorkColdTemperatureR();
                    this.h.setFridgeWorkColdTemperatureR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FRIDGE_WORK_COLD_TEMPERATURE_R:" + autoPurificationState);
                    break;
                case 557857966:
                    autoPurificationState = settingEventChangeValue.getFridgeWorkHeatTemperatureR();
                    this.h.setFridgeWorkHeatTemperatureR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FRIDGE_WORK_HEAT_TEMPERATURE_R:" + autoPurificationState);
                    break;
                case 557857967:
                    autoPurificationState = settingEventChangeValue.getFridgeWorkSaveTemperatureR();
                    this.h.setFridgeWorkSaveTemperatureR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FRIDGE_WORK_SAVE_TEMPERATURE_R:" + autoPurificationState);
                    break;
                case 557857968:
                    autoPurificationState = settingEventChangeValue.getDelayPowerOffStateR();
                    this.h.setDelayPowerOffStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DELAY_POWER_OFF_STATE_R:" + autoPurificationState);
                    break;
                case 557857970:
                    autoPurificationState = settingEventChangeValue.getDelayPowerOffTimeR();
                    this.h.setDelayPowerOffTimeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DELAY_POWER_OFF_TIME_R:" + autoPurificationState);
                    break;
                case 557857972:
                    autoPurificationState = settingEventChangeValue.getDelayPowerDownTimeR();
                    this.h.setDelayPowerDownTimeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DELAY_POWER_DOWN_TIME_R:" + autoPurificationState);
                    break;
                case 557857974:
                    autoPurificationState = settingEventChangeValue.getRemberPowerOffStateR();
                    this.h.setRemberPowerOffStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REMBER_POWER_OFF_STATE_R:" + autoPurificationState);
                    break;
                case 557857976:
                    autoPurificationState = settingEventChangeValue.getSterilizationStateR();
                    this.h.setSterilizationStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_STERILIZATION_STATE_R:" + autoPurificationState);
                    break;
                case 557857977:
                    autoPurificationState = settingEventChangeValue.getVoiceTipsMessageR();
                    this.h.setVoiceTipsMessageR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_VOICE_TIPS_MESSAGE_R:" + autoPurificationState);
                    break;
                case 557857979:
                    autoPurificationState = settingEventChangeValue.getFridgeDoorStateR();
                    this.h.setFridgeDoorStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FRIDGE_DOOR_STATE_R:" + autoPurificationState);
                    break;
                case 557857980:
                    autoPurificationState = settingEventChangeValue.getRefrigeratorWorkStateR();
                    this.h.setRefrigeratorWorkStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REFRIGERATOR_WORK_STATE_R:" + autoPurificationState);
                    break;
                case 557857981:
                    autoPurificationState = settingEventChangeValue.getRefrigeratorTemperatureR();
                    this.h.setRefrigeratorTemperatureR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REFRIGERATOR_TEMPERATURE_R:" + autoPurificationState);
                    break;
                case 557857982:
                    autoPurificationState = settingEventChangeValue.getDelayDownPowerTimeR();
                    this.h.setDelayDownPowerTimeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DELAY_DOWN_POWER_TIME_R:" + autoPurificationState);
                    break;
                case 557857983:
                    autoPurificationState = settingEventChangeValue.getSterilizationWorkStateR();
                    this.h.setSterilizationWorkStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_STERILIZATION_WORK_STATE_R:" + autoPurificationState);
                    break;
                case 557857984:
                    autoPurificationState = settingEventChangeValue.getDlgMessageCodeR();
                    this.h.setDlgMessageCodeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DLG_MESSAGE_CODE_R:" + autoPurificationState);
                    break;
                case 557857985:
                    autoPurificationState = settingEventChangeValue.getFridgeLocationR();
                    this.h.setFridgeLocationR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FRIDGE_LOCATION_R:" + autoPurificationState);
                    break;
                case 557857990:
                    autoPurificationState = settingEventChangeValue.getControlCallbackStateR();
                    this.h.setControlCallbackStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CONTROL_CALLBACK_STATE_R:" + autoPurificationState);
                    break;
                case 557857991:
                    autoPurificationState = settingEventChangeValue.getWorkStateR();
                    this.h.setWorkStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WORK_STATE_R:" + autoPurificationState);
                    break;
                case 557857992:
                    autoPurificationState = settingEventChangeValue.getDelayPowerOffStatusR();
                    this.h.setDelayPowerOffStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DELAY_POWER_OFF_STATUS_R:" + autoPurificationState);
                    break;
                case 557857993:
                    autoPurificationState = settingEventChangeValue.getScreenCallbackMessageR();
                    this.h.setScreenCallbackMessageR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SCREEN_CALLBACK_MESSAGE_R:" + autoPurificationState);
                    break;
                case 557857994:
                    autoPurificationState = settingEventChangeValue.getRearDoorStateR();
                    this.h.setRearDoorStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_DOOR_STATE_R:" + autoPurificationState);
                    break;
                case 557857995:
                    autoPurificationState = settingEventChangeValue.getRearHeatTempR();
                    this.h.setRearHeatTempR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_HEAT_TEMP_R:" + autoPurificationState);
                    break;
                case 557857996:
                    autoPurificationState = settingEventChangeValue.getRearSaveTempR();
                    this.h.setRearSaveTempR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_SAVE_TEMP_R:" + autoPurificationState);
                    break;
                case 557857997:
                    autoPurificationState = settingEventChangeValue.getPhoneRemindCodeR();
                    this.h.setPhoneRemindCodeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PHONE_REMIND_CODE_R:" + autoPurificationState);
                    break;
                case 557857998:
                    autoPurificationState = settingEventChangeValue.getServiceControlCallbackR();
                    this.h.setServiceControlCallbackR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SERVICE_CONTROL_CALLBACK_R:" + autoPurificationState);
                    break;
                case 557858010:
                    autoPurificationState = settingEventChangeValue.getCanopyAutomaticDimmingR();
                    this.h.setCanopyAutomaticDimmingR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CANOPY_AUTOMATIC_DIMMING_R:" + autoPurificationState);
                    break;
                case 557858011:
                    autoPurificationState = settingEventChangeValue.getSkyCurtainPowerOffAndDimR();
                    this.h.setSkyCurtainPowerOffAndDimR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SKY_CURTAIN_POWER_OFF_AND_DIM_R:" + autoPurificationState);
                    break;
                case 557858016:
                    autoPurificationState = settingEventChangeValue.getCurrentTransmittanceOfSkylightR();
                    this.h.setCurrentTransmittanceOfSkylightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CURRENT_TRANSMITTANCE_OF_SKYLIGHT_R:" + autoPurificationState);
                    break;
                case 557858017:
                    autoPurificationState = settingEventChangeValue.getCurrentStatusOfDimmingSunroofR();
                    this.h.setCurrentStatusOfDimmingSunroofR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CURRENT_STATUS_OF_DIMMING_SUNROOF_R:" + autoPurificationState);
                    break;
                case 557858018:
                    autoPurificationState = settingEventChangeValue.getDimmingCanopyLowCodeR();
                    this.h.setDimmingCanopyLowCodeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DIMMING_CANOPY_LOW_CODE_R:" + autoPurificationState);
                    break;
                case 557858020:
                    autoPurificationState = settingEventChangeValue.getTailInitializationStateR();
                    this.h.setTailInitializationStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TAIL_INITIALIZATION_STATE_R:" + autoPurificationState);
                    break;
                case 557858021:
                    autoPurificationState = settingEventChangeValue.getCurrentStateOfTheTailStateR();
                    this.h.setCurrentStateOfTheTailStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CURRENT_STATE_OF_THE_TAIL_STATE_R:" + autoPurificationState);
                    break;
                case 557858022:
                    autoPurificationState = settingEventChangeValue.getTailPositionPercentageR();
                    this.h.setTailPositionPercentageR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TAIL_POSITION_PERCENTAGE_R:" + autoPurificationState);
                    break;
                case 557858023:
                    autoPurificationState = settingEventChangeValue.getTailMotorThermalProtectionStateR();
                    this.h.setTailMotorThermalProtectionStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TAIL_MOTOR_THERMAL_PROTECTION_STATE_R:" + autoPurificationState);
                    break;
                case 557858024:
                    autoPurificationState = settingEventChangeValue.getTailPositionFeedbackR();
                    this.h.setTailPositionFeedbackR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TAIL_POSITION_FEEDBACK_R:" + autoPurificationState);
                    break;
                case 557858025:
                    autoPurificationState = settingEventChangeValue.getAlarmPromptStateR();
                    this.h.setAlarmPromptStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ALARM_PROMPT_STATE_R:" + autoPurificationState);
                    break;
                case 557858026:
                    autoPurificationState = settingEventChangeValue.getCurrentModeOfTailStateR();
                    this.h.setCurrentModeOfTailStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CURRENT_MODE_OF_TAIL_STATE_R:" + autoPurificationState);
                    break;
                case 557858027:
                    autoPurificationState = settingEventChangeValue.getElectricTailMaintenanceFuntatusStateR();
                    this.h.setElectricTailMaintenanceFuntatusStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ELECTRIC_TAIL_MAINTENANCE_FUNTATUS_STATE_R:" + autoPurificationState);
                    break;
                case 557858058:
                    autoPurificationState = settingEventChangeValue.getLeftElectricPedalR();
                    this.h.setLeftElectricPedalR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LEFT_ELECTRIC_PEDAL_R:" + autoPurificationState);
                    break;
                case 557858060:
                    autoPurificationState = settingEventChangeValue.getRightElectricPedalR();
                    this.h.setRightElectricPedalR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RIGHT_ELECTRIC_PEDAL_R:" + autoPurificationState);
                    break;
                case 557858063:
                    autoPurificationState = settingEventChangeValue.getFridgeChildLockR();
                    this.h.setFridgeChildLockR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FRIDGE_CHILD_LOCK_R:" + autoPurificationState);
                    break;
                case 557858083:
                    autoPurificationState = settingEventChangeValue.getsocTargetRangeR();
                    this.h.setsocTargetRangeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_S_O_C_TARGET_RANGE_R:" + autoPurificationState);
                    break;
                case 557858084:
                    autoPurificationState = settingEventChangeValue.getsocTargetLowLimitR();
                    this.h.setsocTargetLowLimitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_S_O_C_TARGET_LOW_LIMIT_R:" + autoPurificationState);
                    break;
                case 557858089:
                    autoPurificationState = settingEventChangeValue.getCpdImsSwitchStateR();
                    this.h.setCpdImsSwitchStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CPD_IMS_SWITCH_STATE_R:" + autoPurificationState);
                    break;
                case 557858090:
                    autoPurificationState = settingEventChangeValue.getRncStateR();
                    this.h.setRncStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RNC_STATE_R:" + autoPurificationState);
                    break;
                case 557858091:
                    autoPurificationState = settingEventChangeValue.getSwcontrolStateR();
                    this.h.setSwcontrolStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SWCONTROL_STATE_R:" + autoPurificationState);
                    break;
                case 557858093:
                    autoPurificationState = settingEventChangeValue.getPedalSuppressionSystemStateR();
                    this.h.setPedalSuppressionSystemStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PEDAL_SUPPRESSION_SYSTEM_STATE_R:" + autoPurificationState);
                    break;
                case 557858096:
                    autoPurificationState = settingEventChangeValue.getCuraSwitchStateR();
                    this.h.setCuraSwitchStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CURA_SWITCH_STATE_R:" + autoPurificationState);
                    break;
                case 557858102:
                    autoPurificationState = settingEventChangeValue.getRearRightSeatGreetStateR();
                    this.h.setRearRightSeatGreetStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_RIGHT_SEAT_GREET_STATE_R:" + autoPurificationState);
                    break;
                case 557858107:
                    autoPurificationState = settingEventChangeValue.getWadingPatternSocTipsR();
                    this.h.setWadingPatternSocTipsR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WADING_PATTERN_SOC_TIPS_R:" + autoPurificationState);
                    break;
                case 557858108:
                    autoPurificationState = settingEventChangeValue.getWadingPatternSpeedTipsR();
                    this.h.setWadingPatternSpeedTipsR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WADING_PATTERN_SPEED_TIPS_R:" + autoPurificationState);
                    break;
                case 557858109:
                    autoPurificationState = settingEventChangeValue.getWadingPatternTestTipsR();
                    this.h.setWadingPatternTestTipsR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WADING_PATTERN_TEST_TIPS_R:" + autoPurificationState);
                    break;
                case 557858110:
                    autoPurificationState = settingEventChangeValue.getWadingPatternStateTipsR();
                    this.h.setWadingPatternStateTipsR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WADING_PATTERN_STATE_TIPS_R:" + autoPurificationState);
                    break;
                case 557858111:
                    autoPurificationState = settingEventChangeValue.getSeaModeChargingSocTypeR();
                    this.h.setSeaModeChargingSocTypeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SEA_MODE_CHARGING_SOC_TYPE_R:" + autoPurificationState);
                    break;
                case 557858112:
                    autoPurificationState = settingEventChangeValue.getDriveModeR();
                    this.h.setDriveModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRIVE_MODE_R:" + autoPurificationState);
                    break;
                case 557858114:
                    autoPurificationState = settingEventChangeValue.getSandAbsModeStateR();
                    this.h.setSandAbsModeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAND_ABS_MODE_STATE_R:" + autoPurificationState);
                    break;
                case 557858115:
                    autoPurificationState = settingEventChangeValue.getSandAbsTipMessageR();
                    this.h.setSandAbsTipMessageR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAND_ABS_TIP_MESSAGE_R:" + autoPurificationState);
                    break;
                case 557858117:
                    autoPurificationState = settingEventChangeValue.getSaveOwnerStateR();
                    this.h.setSaveOwnerStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAVE_OWNER_STATE_R:" + autoPurificationState);
                    break;
                case 557858119:
                    autoPurificationState = settingEventChangeValue.getRefrigeratorMobileTerminalPushReR();
                    this.h.setRefrigeratorMobileTerminalPushReR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REFRIGERATOR_MOBILE_TERMINAL_PUSH_RE_R:" + autoPurificationState);
                    break;
                case 557858123:
                    autoPurificationState = settingEventChangeValue.getRefrigWorkModeR();
                    this.h.setRefrigWorkModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REFRIG_WORK_MODE_R:" + autoPurificationState);
                    break;
                case 557858125:
                    autoPurificationState = settingEventChangeValue.getDriverSeatVentilating437StateR();
                    this.h.setDriverSeatVentilating437StateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRIVER_SEAT_VENTILATING437_STATE_R:" + autoPurificationState);
                    break;
                case 557858126:
                    autoPurificationState = settingEventChangeValue.getDriverSeatHeating437StateR();
                    this.h.setDriverSeatHeating437StateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRIVER_SEAT_HEATING437_STATE_R:" + autoPurificationState);
                    break;
                case 557858127:
                    autoPurificationState = settingEventChangeValue.getPassengerSeatVentilating3CAStateR();
                    this.h.setPassengerSeatVentilating3caStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PASSENGER_SEAT_VENTILATING3_C_A_STATE_R:" + autoPurificationState);
                    break;
                case 557858128:
                    autoPurificationState = settingEventChangeValue.getPassengerSeatHeating3CAStateR();
                    this.h.setPassengerSeatHeating3caStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PASSENGER_SEAT_HEATING3_C_A_STATE_R:" + autoPurificationState);
                    break;
                case 557858140:
                    autoPurificationState = settingEventChangeValue.getThirdRowSeatWorkModeR();
                    this.h.setThirdRowSeatWorkModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_THIRD_ROW_SEAT_WORK_MODE_R:" + autoPurificationState);
                    break;
                case 557858141:
                    autoPurificationState = settingEventChangeValue.getLeftRearSafebeltStatusR();
                    this.h.setLeftRearSafebeltStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LEFT_REAR_SAFEBELT_STATUS_R:" + autoPurificationState);
                    break;
                case 557858142:
                    autoPurificationState = settingEventChangeValue.getCenterRearSafebeltStatusR();
                    this.h.setCenterRearSafebeltStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CENTER_REAR_SAFEBELT_STATUS_R:" + autoPurificationState);
                    break;
                case 557858143:
                    autoPurificationState = settingEventChangeValue.getRightRearSafebeltStatusR();
                    this.h.setRightRearSafebeltStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RIGHT_REAR_SAFEBELT_STATUS_R:" + autoPurificationState);
                    break;
                case 557858144:
                    autoPurificationState = settingEventChangeValue.getLeftThirdSafebeltStatusR();
                    this.h.setLeftThirdSafebeltStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LEFT_THIRD_SAFEBELT_STATUS_R:" + autoPurificationState);
                    break;
                case 557858145:
                    autoPurificationState = settingEventChangeValue.getRightThirdSafebeltStatusR();
                    this.h.setRightThirdSafebeltStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RIGHT_THIRD_SAFEBELT_STATUS_R:" + autoPurificationState);
                    break;
                case 557858146:
                    autoPurificationState = settingEventChangeValue.getMainSeatHorizontalInitR();
                    this.h.setMainSeatHorizontalInitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MAIN_SEAT_HORIZONTAL_INIT_R:" + autoPurificationState);
                    break;
                case 557858147:
                    autoPurificationState = settingEventChangeValue.getMainSeatBackInitR();
                    this.h.setMainSeatBackInitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MAIN_SEAT_BACK_INIT_R:" + autoPurificationState);
                    break;
                case 557858148:
                    autoPurificationState = settingEventChangeValue.getPassangerSeatHorizontalInitR();
                    this.h.setPassangerSeatHorizontalInitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PASSANGER_SEAT_HORIZONTAL_INIT_R:" + autoPurificationState);
                    break;
                case 557858149:
                    autoPurificationState = settingEventChangeValue.getPassangerSeatBackInitR();
                    this.h.setPassangerSeatBackInitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_PASSANGER_SEAT_BACK_INIT_R:" + autoPurificationState);
                    break;
                case 557858150:
                    autoPurificationState = settingEventChangeValue.getSecondLeftSeatHorizontalInitR();
                    this.h.setSecondLeftSeatHorizontalInitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECOND_LEFT_SEAT_HORIZONTAL_INIT_R:" + autoPurificationState);
                    break;
                case 557858151:
                    autoPurificationState = settingEventChangeValue.getSecondLeftSeatBackInitR();
                    this.h.setSecondLeftSeatBackInitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECOND_LEFT_SEAT_BACK_INIT_R:" + autoPurificationState);
                    break;
                case 557858152:
                    autoPurificationState = settingEventChangeValue.getSecondRightSeatHorizontalInitR();
                    this.h.setSecondRightSeatHorizontalInitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECOND_RIGHT_SEAT_HORIZONTAL_INIT_R:" + autoPurificationState);
                    break;
                case 557858153:
                    autoPurificationState = settingEventChangeValue.getSecondRightSeatBackInitR();
                    this.h.setSecondRightSeatBackInitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECOND_RIGHT_SEAT_BACK_INIT_R:" + autoPurificationState);
                    break;
                case 557858159:
                    autoPurificationState = settingEventChangeValue.getWashElectricPedalR();
                    this.h.setWashElectricPedalR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WASH_ELECTRIC_PEDAL_R:" + autoPurificationState);
                    break;
                case 557858160:
                    autoPurificationState = settingEventChangeValue.getTapDoorStateR();
                    this.h.setTapDoorStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TAP_DOOR_STATE_R:" + autoPurificationState);
                    break;
                case 557858161:
                    autoPurificationState = settingEventChangeValue.getMainSeatHorizontalInitedR();
                    this.h.setMainSeatHorizontalInitedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MAIN_SEAT_HORIZONTAL_INITED_R:" + autoPurificationState);
                    break;
                case 557858162:
                    autoPurificationState = settingEventChangeValue.getMainSeatBackInitedR();
                    this.h.setMainSeatBackInitedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MAIN_SEAT_BACK_INITED_R:" + autoPurificationState);
                    break;
                case 557858163:
                    autoPurificationState = settingEventChangeValue.getMainSeatHightInitedR();
                    this.h.setMainSeatHightInitedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MAIN_SEAT_HIGHT_INITED_R:" + autoPurificationState);
                    break;
                case 557858165:
                    autoPurificationState = settingEventChangeValue.getSecondRightSeatHorizontal2InitR();
                    this.h.setSecondRightSeatHorizontal2InitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECOND_RIGHT_SEAT_HORIZONTAL2_INIT_R:" + autoPurificationState);
                    break;
                case 557858166:
                    autoPurificationState = settingEventChangeValue.getSecondRightSeatBack2InitR();
                    this.h.setSecondRightSeatBack2InitR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECOND_RIGHT_SEAT_BACK2_INIT_R:" + autoPurificationState);
                    break;
                case 557858167:
                    autoPurificationState = settingEventChangeValue.getDragTypeR();
                    this.h.setDragTypeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRAG_TYPE_R:" + autoPurificationState);
                    break;
                case 557858168:
                    autoPurificationState = settingEventChangeValue.getDragConnectionTypeR();
                    this.h.setDragConnectionTypeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRAG_CONNECTION_TYPE_R:" + autoPurificationState);
                    break;
                case 557858173:
                    autoPurificationState = settingEventChangeValue.getTrailerDragTypeR();
                    this.h.setTrailerDragTypeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TRAILER_DRAG_TYPE_R:" + autoPurificationState);
                    break;
                case 557858177:
                    autoPurificationState = settingEventChangeValue.getTowingProhibitedStateR();
                    this.h.setTowingProhibitedStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TOWING_PROHIBITED_STATE_R:" + autoPurificationState);
                    break;
                case 557858190:
                    autoPurificationState = settingEventChangeValue.getUcuMassageModeMainR();
                    this.h.setUcuMassageModeMainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_UCU_MASSAGE_MODE_MAIN_R:" + autoPurificationState);
                    break;
                case 557858191:
                    autoPurificationState = settingEventChangeValue.getUcuMassageModeDeputyR();
                    this.h.setUcuMassageModeDeputyR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_UCU_MASSAGE_MODE_DEPUTY_R:" + autoPurificationState);
                    break;
                case 557858192:
                    autoPurificationState = settingEventChangeValue.getUcuMassageLevelMainR();
                    this.h.setUcuMassageLevelMainR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_UCU_MASSAGE_LEVEL_MAIN_R:" + autoPurificationState);
                    break;
                case 557858193:
                    autoPurificationState = settingEventChangeValue.getUcuMassageLevelDeputyR();
                    this.h.setUcuMassageLevelDeputyR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_UCU_MASSAGE_LEVEL_DEPUTY_R:" + autoPurificationState);
                    break;
                case 557858194:
                    autoPurificationState = settingEventChangeValue.getBdMassageModeLeftRearR();
                    this.h.setBdMassageModeLeftRearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BD_MASSAGE_MODE_LEFT_REAR_R:" + autoPurificationState);
                    break;
                case 557858195:
                    autoPurificationState = settingEventChangeValue.getBdMassageModeRightRearR();
                    this.h.setBdMassageModeRightRearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BD_MASSAGE_MODE_RIGHT_REAR_R:" + autoPurificationState);
                    break;
                case 557858196:
                    autoPurificationState = settingEventChangeValue.getBdMassageLevelLeftRearR();
                    this.h.setBdMassageLevelLeftRearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BD_MASSAGE_LEVEL_LEFT_REAR_R:" + autoPurificationState);
                    break;
                case 557858197:
                    autoPurificationState = settingEventChangeValue.getBdMassageLevelRightRearR();
                    this.h.setBdMassageLevelRightRearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BD_MASSAGE_LEVEL_RIGHT_REAR_R:" + autoPurificationState);
                    break;
                case 557858198:
                    autoPurificationState = settingEventChangeValue.getSecuMassageModeLeftRearR();
                    this.h.setSecuMassageModeLeftRearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECU_MASSAGE_MODE_LEFT_REAR_R:" + autoPurificationState);
                    break;
                case 557858199:
                    autoPurificationState = settingEventChangeValue.getSecuMassageModeRightRearR();
                    this.h.setSecuMassageModeRightRearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECU_MASSAGE_MODE_RIGHT_REAR_R:" + autoPurificationState);
                    break;
                case 557858200:
                    autoPurificationState = settingEventChangeValue.getSecuMassageLevelLeftRearR();
                    this.h.setSecuMassageLevelLeftRearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECU_MASSAGE_LEVEL_LEFT_REAR_R:" + autoPurificationState);
                    break;
                case 557858201:
                    autoPurificationState = settingEventChangeValue.getSecuMassageLevelRightRearR();
                    this.h.setSecuMassageLevelRightRearR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SECU_MASSAGE_LEVEL_RIGHT_REAR_R:" + autoPurificationState);
                    break;
                case 557858202:
                    autoPurificationState = settingEventChangeValue.getRrEcuMassageModeR();
                    this.h.setRrEcuMassageModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RR_ECU_MASSAGE_MODE_R:" + autoPurificationState);
                    break;
                case 557858203:
                    autoPurificationState = settingEventChangeValue.getRrEcuMassageLevelR();
                    this.h.setRrEcuMassageLevelR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RR_ECU_MASSAGE_LEVEL_R:" + autoPurificationState);
                    break;
                case 557858305:
                    autoPurificationState = settingEventChangeValue.getSafeBeltWarnStateR();
                    this.h.setSafeBeltWarnState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAFE_BELT_WARN_STATER :" + autoPurificationState);
                    break;
                case 557858329:
                    autoPurificationState = settingEventChangeValue.getSafeBeltWarnStateR();
                    this.h.setSafeBeltWarnState(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ON_SAFE_BELT_WARN_STATE_CHANGED :" + autoPurificationState);
                    break;
                case 557858330:
                    autoPurificationState = settingEventChangeValue.getPabCodeR();
                    this.h.setPabCodeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_P_A_B_CODE_R :" + autoPurificationState);
                    break;
                case 557858331:
                    autoPurificationState = settingEventChangeValue.getMissPackageCountR();
                    this.h.setMissPackageCountR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MISS_PACKAGE_COUNT_R :" + autoPurificationState);
                    break;
                case 557858334:
                    autoPurificationState = settingEventChangeValue.getScreenAngleR();
                    this.h.setScreenAngleR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SCREEN_ANGLE_R :" + autoPurificationState);
                    break;
                case 557858336:
                    autoPurificationState = settingEventChangeValue.getScreenRotationActionR();
                    this.h.setScreenRotationActionR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SCREEN_ROTATION_ACTION_R :" + autoPurificationState);
                    break;
                case 557858337:
                    autoPurificationState = settingEventChangeValue.getScreenAntiPinchStateR();
                    this.h.setScreenAntiPinchStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SCREEN_ANTI_PINCH_STATE_R :" + autoPurificationState);
                    break;
                case 557858338:
                    autoPurificationState = settingEventChangeValue.getScreenAngleValueR();
                    this.h.setScreenAngleValueR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SCREEN_ANGLE_VALUE_R :" + autoPurificationState);
                    break;
                case 557858340:
                    autoPurificationState = settingEventChangeValue.getScreenRotationOverHotStateR();
                    this.h.setScreenRotationOverHotStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SCREEN_ROTATION_OVER_HOT_STATE_R :" + autoPurificationState);
                    break;
                case 557858345:
                    autoPurificationState = settingEventChangeValue.getPabStateR();
                    this.h.setPabStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_P_A_B_STATE_R :" + autoPurificationState);
                    break;
                case 557858354:
                    autoPurificationState = settingEventChangeValue.getSomethingLeftInRefrigeratorR();
                    this.h.setSomethingLeftInRefrigerator(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SOMETHING_LEFT_INREFRIGERATOR_R :" + autoPurificationState);
                    break;
                case 557858355:
                    autoPurificationState = settingEventChangeValue.getOneLevelSeatHeatingStateR();
                    this.h.setOneLevelSeatHeatingStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ONE_LEVEL_SEAT_HEATING_STATE_R:" + autoPurificationState);
                    break;
                case 557858357:
                    autoPurificationState = settingEventChangeValue.getTrailerModeStateR();
                    this.h.setTrailerModeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TRAILER_MODE_STATE_R:" + autoPurificationState);
                    break;
                case 557858358:
                    autoPurificationState = settingEventChangeValue.getTrailerLightCheckStateR();
                    this.h.setTrailerLightCheckStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TRAILER_LIGHT_CHECK_STATE_R:" + autoPurificationState);
                    break;
                case 557858360:
                    autoPurificationState = settingEventChangeValue.getRearViewEcuMirrorFlipStateR();
                    this.h.setRearViewEcuMirrorFlipStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_VIEW_ECU_MIRROR_FLIP_STATE_R:" + autoPurificationState);
                    break;
                case 557858361:
                    autoPurificationState = settingEventChangeValue.getDyHeightToggleStateR();
                    this.h.setDyHeightToggleStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DY_HEIGHT_TOGGLE_STATE_R:" + autoPurificationState);
                    break;
                case 557858362:
                    autoPurificationState = settingEventChangeValue.getWelcomeStateR();
                    this.h.setWelcomeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WELCOME_STATE_R:" + autoPurificationState);
                    break;
                case 557858363:
                    autoPurificationState = settingEventChangeValue.getWelcomeActiveR();
                    this.h.setWelcomeActiveR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WELCOME_ACTIVE_R:" + autoPurificationState);
                    break;
                case 557858364:
                    autoPurificationState = settingEventChangeValue.getTailBoxTakeStateR();
                    this.h.setTailBoxTakeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TAIL_BOX_TAKE_STATE_R:" + autoPurificationState);
                    break;
                case 557858365:
                    autoPurificationState = settingEventChangeValue.getCampingBlanceStateR();
                    this.h.setCampingBlanceStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CAMPING_BLANCE_STATE_R:" + autoPurificationState);
                    break;
                case 557858366:
                    autoPurificationState = settingEventChangeValue.getCampingBlanceActionR();
                    this.h.setCampingBlanceActionR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CAMPING_BLANCE_ACTION_R:" + autoPurificationState);
                    break;
                case 557858367:
                    autoPurificationState = settingEventChangeValue.getLiftModeStateR();
                    this.h.setLiftModeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LIFT_MODE_STATE_R:" + autoPurificationState);
                    break;
                case 557858368:
                    autoPurificationState = settingEventChangeValue.getFixedStateR();
                    this.h.setFixedStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FIXED_STATE_R:" + autoPurificationState);
                    break;
                case 557858369:
                    autoPurificationState = settingEventChangeValue.getSpeedSupportedTurnR();
                    this.h.setSpeedSupportedTurnR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SPEED_SUPPORTED_TURN_R:" + autoPurificationState);
                    break;
                case 557858370:
                    autoPurificationState = settingEventChangeValue.getModeSupportedHturnR();
                    this.h.setModeSupportedHturnR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MODE_SUPPORTED_HTURN_R:" + autoPurificationState);
                    break;
                case 557858371:
                    autoPurificationState = settingEventChangeValue.getWelcomeSupportedR();
                    this.h.setWelcomeSupportedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_WELCOME_SUPPORTED_R:" + autoPurificationState);
                    break;
                case 557858372:
                    autoPurificationState = settingEventChangeValue.getTailBoxTakeSupportedR();
                    this.h.setTailBoxTakeSupportedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TAIL_BOX_TAKE_SUPPORTED_R:" + autoPurificationState);
                    break;
                case 557858373:
                    autoPurificationState = settingEventChangeValue.getTrailerModeCodeR();
                    this.h.setTrailerModeCodeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TRAILER_MODE_CODE_R:" + autoPurificationState);
                    break;
                case 557858374:
                    autoPurificationState = settingEventChangeValue.getCvcModeR();
                    this.h.setCvcModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_C_V_C_MODE_R:" + autoPurificationState);
                    break;
                case 557858375:
                    autoPurificationState = settingEventChangeValue.getCampingBlanceSupportedR();
                    this.h.setCampingBlanceSupportedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CAMPING_BLANCE_SUPPORTED_R:" + autoPurificationState);
                    break;
                case 557858376:
                    autoPurificationState = settingEventChangeValue.getRearSeatGreetStateR();
                    this.h.setRearSeatGreetStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_SEAT_GREET_STATE_R:" + autoPurificationState);
                    break;
                case 557858377:
                    autoPurificationState = settingEventChangeValue.getDyHeightTurnActionR();
                    this.h.setDyHeightTurnActionR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DY_HEIGHT_TURN_ACTION_R:" + autoPurificationState);
                    break;
                case 557858378:
                    autoPurificationState = settingEventChangeValue.getDampStateR();
                    this.h.setDampStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DAMP_STATE_R:" + autoPurificationState);
                    break;
                case 557858379:
                    autoPurificationState = settingEventChangeValue.getDyHeightLevelR();
                    this.h.setDyHeightLevelR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DY_HEIGHT_LEVEL_R:" + autoPurificationState);
                    break;
                case 557858380:
                    autoPurificationState = settingEventChangeValue.getFdCrcCheckNumR();
                    this.h.setFdCrcCheckNumR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_C_R_C_CHECK_NUM_R:" + autoPurificationState);
                    break;
                case 557858381:
                    autoPurificationState = settingEventChangeValue.getOnFdCrcCheckNumR();
                    this.h.setOnFdCrcCheckNumR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_ON_FD_CRC_CHECK_NUM_R:" + autoPurificationState);
                    break;
                case 557858382:
                    autoPurificationState = settingEventChangeValue.getFdDyHeightTurnActionR();
                    this.h.setFdDyHeightTurnActionR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_DY_HEIGHT_TURN_ACTION_R:" + autoPurificationState);
                    break;
                case 557858383:
                    autoPurificationState = settingEventChangeValue.getFdDampStateR();
                    this.h.setFdDampStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_DAMP_STATE_R:" + autoPurificationState);
                    break;
                case 557858384:
                    autoPurificationState = settingEventChangeValue.getFdDyHeightLevelR();
                    this.h.setFdDyHeightLevelR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_DY_HEIGHT_LEVEL_R:" + autoPurificationState);
                    break;
                case 557858385:
                    autoPurificationState = settingEventChangeValue.getFdDyHeightToggleStateR();
                    this.h.setFdDyHeightToggleStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_DY_HEIGHT_TOGGLE_STATE_R:" + autoPurificationState);
                    break;
                case 557858386:
                    autoPurificationState = settingEventChangeValue.getFdWelcomeStateR();
                    this.h.setFdWelcomeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_WELCOME_STATE_R:" + autoPurificationState);
                    break;
                case 557858387:
                    autoPurificationState = settingEventChangeValue.getFdWelcomeActiveR();
                    this.h.setFdWelcomeActiveR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_WELCOME_ACTIVE_R:" + autoPurificationState);
                    break;
                case 557858388:
                    autoPurificationState = settingEventChangeValue.getFdFrontBoxTakeStateR();
                    this.h.setFdFrontBoxTakeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_FRONT_BOX_TAKE_STATE_R:" + autoPurificationState);
                    break;
                case 557858389:
                    autoPurificationState = settingEventChangeValue.getFdTailBoxTakeStateR();
                    this.h.setFdTailBoxTakeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_TAIL_BOX_TAKE_STATE_R:" + autoPurificationState);
                    break;
                case 557858390:
                    autoPurificationState = settingEventChangeValue.getFdCampingBlanceStateR();
                    this.h.setFdCampingBlanceStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_CAMPING_BLANCE_STATE_R:" + autoPurificationState);
                    break;
                case 557858391:
                    autoPurificationState = settingEventChangeValue.getFdCampingBlanceActionR();
                    this.h.setFdCampingBlanceActionR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_CAMPING_BLANCE_ACTION_R:" + autoPurificationState);
                    break;
                case 557858392:
                    autoPurificationState = settingEventChangeValue.getFdLiftModeStateR();
                    this.h.setFdLiftModeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_LIFT_MODE_STATE_R:" + autoPurificationState);
                    break;
                case 557858393:
                    autoPurificationState = settingEventChangeValue.getFdFixedStateR();
                    this.h.setFdFixedStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_FIXED_STATE_R:" + autoPurificationState);
                    break;
                case 557858394:
                    autoPurificationState = settingEventChangeValue.getFdSpeedSupportedTurnR();
                    this.h.setFdSpeedSupportedTurnR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_SPEED_SUPPORTED_TURN_R:" + autoPurificationState);
                    break;
                case 557858395:
                    autoPurificationState = settingEventChangeValue.getFdModeSupportedHturnR();
                    this.h.setFdModeSupportedHturnR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_MODE_SUPPORTED_HTURN_R:" + autoPurificationState);
                    break;
                case 557858396:
                    autoPurificationState = settingEventChangeValue.getFdWelcomeSupportedR();
                    this.h.setFdWelcomeSupportedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_WELCOME_SUPPORTED_R:" + autoPurificationState);
                    break;
                case 557858397:
                    autoPurificationState = settingEventChangeValue.getFdTailBoxTakeSupportedR();
                    this.h.setFdTailBoxTakeSupportedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_TAIL_BOX_TAKE_SUPPORTED_R:" + autoPurificationState);
                    break;
                case 557858398:
                    autoPurificationState = settingEventChangeValue.getFdTrailerModeCodeR();
                    this.h.setFdTrailerModeCodeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_TRAILER_MODE_CODE_R:" + autoPurificationState);
                    break;
                case 557858399:
                    autoPurificationState = settingEventChangeValue.getFdCampingBlanceSupportedR();
                    this.h.setFdCampingBlanceSupportedR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FD_CAMPING_BLANCE_SUPPORTED_R:" + autoPurificationState);
                    break;
                case 557858411:
                    autoPurificationState = settingEventChangeValue.getCloseDoorLevelR();
                    this.h.setCloseDoorLevelR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_CLOSE_DOOR_LEVEL_R:" + autoPurificationState);
                    break;
                case 557858412:
                    autoPurificationState = settingEventChangeValue.getIntelLockOpenDriverDoorR();
                    this.h.setIntelLockOpenDriverDoorR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_INTEL_LOCK_OPEN_DRIVER_DOOR_R:" + autoPurificationState);
                    break;
                case 557858413:
                    autoPurificationState = settingEventChangeValue.getBrakeCloseDoorR();
                    this.h.setBrakeCloseDoorR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BRAKE_CLOSE_DOOR_R:" + autoPurificationState);
                    break;
                case 557858414:
                    autoPurificationState = settingEventChangeValue.getNfcOpenDriverDoorR();
                    this.h.setNfcOpenDriverDoorR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_NFC_OPEN_DRIVER_DOOR_R:" + autoPurificationState);
                    break;
                case 557858415:
                    autoPurificationState = settingEventChangeValue.getBleOpenDriverDoorStateR();
                    this.h.setBleOpenDriverDoorStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_BLE_OPEN_DRIVER_DOOR_STATE_R:" + autoPurificationState);
                    break;
                case 557858416:
                    autoPurificationState = settingEventChangeValue.getSafeBeltCloseDriveDoorR();
                    this.h.setSafeBeltCloseDriveDoorR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SAFE_BELT_CLOSE_DRIVE_DOOR_R:" + autoPurificationState);
                    break;
                case 557858418:
                    autoPurificationState = settingEventChangeValue.getVspStateR();
                    this.h.setVspStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_V_S_P_STATE_R:" + autoPurificationState);
                    break;
                case 557858429:
                    autoPurificationState = settingEventChangeValue.getDriverSeatAutoReturn();
                    this.h.setDriverSeatAutoReturn(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRIVER_SEAT_AUTO_RETURN_B3_R:" + autoPurificationState);
                    break;
                case 557858441:
                    autoPurificationState = settingEventChangeValue.getLrseMachineSwitchStateR();
                    this.h.setLrseMachineSwitchStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LRSE_MACHINE_SWITCH_STATE_R:" + autoPurificationState);
                    break;
                case 557858442:
                    autoPurificationState = settingEventChangeValue.getRrseMachineSwitchStateR();
                    this.h.setRrseMachineSwitchStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RRSE_MACHINE_SWITCH_STATE_R:" + autoPurificationState);
                    break;
                case 557858449:
                    autoPurificationState = settingEventChangeValue.getLrseChildModeTimeWriteFbR();
                    this.h.setLrseChildModeTimeWriteFbR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LRSE_CHILD_MODE_TIME_WRITE_FB_R:" + autoPurificationState);
                    break;
                case 557858450:
                    autoPurificationState = settingEventChangeValue.getRrseChildModeTimeWriteFbR();
                    this.h.setRrseChildModeTimeWriteFbR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RRSE_CHILD_MODE_TIME_WRITE_FB_R:" + autoPurificationState);
                    break;
                case 557858459:
                    autoPurificationState = settingEventChangeValue.getLrseChildModeCodeWriteFbR();
                    this.h.setLrseChildModeCodeWriteFbR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_LRSE_CHILD_MODE_CODE_WRITE_FB_R:" + autoPurificationState);
                    break;
                case 557858460:
                    autoPurificationState = settingEventChangeValue.getRrseChildModeCodeWriteFbR();
                    this.h.setRrseChildModeCodeWriteFbR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_RRSE_CHILD_MODE_CODE_WRITE_FB_R:" + autoPurificationState);
                    break;
                case 557858461:
                    autoPurificationState = settingEventChangeValue.getDragModeForwardAndBackwardStateR();
                    this.h.setDragModeForwardAndBackwardStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRAG_MODE_FORWARD_AND_BACKWARD_STATE_R:" + autoPurificationState);
                    break;
                case 557858462:
                    autoPurificationState = settingEventChangeValue.getDragAndDropModeTipsStateR();
                    this.h.setDragAndDropModeTipsStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_DRAG_AND_DROP_MODE_TIPS_STATE_R:" + autoPurificationState);
                    break;
                case 557858527:
                    autoPurificationState = settingEventChangeValue.getMemorySetOperationFlagR();
                    this.h.setMemorySetOperationFlagR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MEMORY_SET_OPERATION_FLAG_R:" + autoPurificationState);
                    break;
                case 557858528:
                    autoPurificationState = settingEventChangeValue.getFrontWiperSpeedValueR();
                    this.h.setFrontWiperSpeedValueR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_FRONT_WIPER_SPEED_VALUE_R:" + autoPurificationState);
                    break;
                case 557858529:
                    autoPurificationState = settingEventChangeValue.getTrailerModeMileageR();
                    this.h.setTrailerModeMileageR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_TRAILER_MODE_MILEAGE_R:" + autoPurificationState);
                    break;
                case 557858530:
                    autoPurificationState = settingEventChangeValue.getRearWiperSpeedValueR();
                    this.h.setRearWiperSpeedValueR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_REAR_WIPER_SPEED_VALUE_R:" + autoPurificationState);
                    break;
                case 557859101:
                    autoPurificationState = settingEventChangeValue.getMainHsMassageStateR();
                    this.h.setMainHsMassageStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_MAIN_HS_MASSAGE_STATE_R :" + autoPurificationState);
                    break;
                case 557859103:
                    autoPurificationState = settingEventChangeValue.getHsMassageStatePassengerR();
                    this.h.setHsMassageStatePassengerR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HS_MASSAGE_STATE_PASSENGER_R :" + autoPurificationState);
                    break;
                case 557859104:
                    autoPurificationState = settingEventChangeValue.getHsMassageStateBackLeftR();
                    this.h.setHsMassageStateBackLeftR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HS_MASSAGE_STATE_BACK_LEFT_R :" + autoPurificationState);
                    break;
                case 557859105:
                    autoPurificationState = settingEventChangeValue.getHsMassageStateBackRightR();
                    this.h.setHsMassageStateBackRightR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_HS_MASSAGE_STATE_BACK_RIGHT_R :" + autoPurificationState);
                    break;
                case 557860611:
                    autoPurificationState = settingEventChangeValue.getAmbientMusicModeStateR();
                    this.h.setAmbientMusicModeStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AMBIENT_MUSIC_MODE_STATE_R:" + autoPurificationState);
                    break;
                case 557860613:
                    autoPurificationState = settingEventChangeValue.getAmbientMusicModeR();
                    this.h.setAmbientMusicModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AMBIENT_MUSIC_MODE_R:" + autoPurificationState);
                    break;
                case 557860615:
                    autoPurificationState = settingEventChangeValue.getAmbientMulticolorStateR();
                    this.h.setAmbientMulticolorStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AMBIENT_MULTI_COLOR_STATE_R:" + autoPurificationState);
                    break;
                case 557860617:
                    autoPurificationState = settingEventChangeValue.getAmbientMulticolorModeR();
                    this.h.setAmbientMulticolorModeR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AMBIENT_MULTI_COLOR_MODE_R:" + autoPurificationState);
                    break;
                case 557860631:
                    autoPurificationState = settingEventChangeValue.getAmbientFeedBack();
                    this.h.setAmbientFeedBack(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_AMBIENT_FEED_BACK:" + autoPurificationState);
                    break;
                case 557862926:
                    autoPurificationState = settingEventChangeValue.getScreenVoltageStateR();
                    this.h.setScreenVoltageStateR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SCREEN_VOLTAGE_STATE_R :" + autoPurificationState);
                    break;
                case 557867008:
                    autoPurificationState = settingEventChangeValue.getSwsMediumRelatedKeysS();
                    this.h.setSwsMediumRelatedKeysS(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SWS_MEDIUM_RELATED_KEYS_R:" + autoPurificationState);
                    break;
                case 557875283:
                    autoPurificationState = settingEventChangeValue.getScreenRotationStatus();
                    this.h.setScreenRotationStatusR(autoPurificationState);
                    LogUtil.debug("AutoSettingImpl", "ID_SCREEN_ROTATION_STATUS_R:" + autoPurificationState);
                    break;
                case 559954810:
                    float steeringWheelY = settingEventChangeValue.getSteeringWheelY();
                    this.h.setSteeringWheelY(steeringWheelY);
                    LogUtil.debug("AutoSettingImpl", "ID_ACBT_WIND_R:" + steeringWheelY);
                    break;
                case 559954811:
                    float steeringWheelZ = settingEventChangeValue.getSteeringWheelZ();
                    this.h.setSteeringWheelZ(steeringWheelZ);
                    LogUtil.debug("AutoSettingImpl", "ID_ACBT_WIND_R:" + steeringWheelZ);
                    break;
                case 559954812:
                    float rearviewMirrorYLeft = settingEventChangeValue.getRearviewMirrorYLeft();
                    this.h.setRearviewMirrorYLeft(rearviewMirrorYLeft);
                    LogUtil.debug("AutoSettingImpl", "ID_REARVIEW_MIRROR_Y_LEFT :" + rearviewMirrorYLeft);
                    break;
                case 559954813:
                    float rearviewMirrorYRight = settingEventChangeValue.getRearviewMirrorYRight();
                    this.h.setRearviewMirrorYRight(rearviewMirrorYRight);
                    LogUtil.debug("AutoSettingImpl", "ID_REARVIEW_MIRROR_Y_RIGHT :" + rearviewMirrorYRight);
                    break;
                case 559954814:
                    float rearviewMirrorXLeft = settingEventChangeValue.getRearviewMirrorXLeft();
                    this.h.setRearviewMirrorXLeft(rearviewMirrorXLeft);
                    LogUtil.debug("AutoSettingImpl", "ID_REARVIEW_MIRROR_X_LEFT :" + rearviewMirrorXLeft);
                    break;
                case 559954815:
                    float rearviewMirrorXRight = settingEventChangeValue.getRearviewMirrorXRight();
                    this.h.setRearviewMirrorXRight(rearviewMirrorXRight);
                    LogUtil.debug("AutoSettingImpl", "ID_REARVIEW_MIRROR_X_RIGHT :" + rearviewMirrorXRight);
                    break;
                case 559954820:
                    float seatTurnHeightDriver = settingEventChangeValue.getSeatTurnHeightDriver();
                    this.h.setSeatTurnHeightDriver(seatTurnHeightDriver);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_TURN_HEIGHT_DRIVER :" + seatTurnHeightDriver);
                    break;
                case 559954821:
                    float seatTurnHeightCopilot = settingEventChangeValue.getSeatTurnHeightCopilot();
                    this.h.setSeatTurnHeightCopilot(seatTurnHeightCopilot);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_TURN_HEIGHT_COPILOT :" + seatTurnHeightCopilot);
                    break;
                case 559954822:
                    float seatTurnHeightBackLeft = settingEventChangeValue.getSeatTurnHeightBackLeft();
                    this.h.setSeatTurnHeightBackLeft(seatTurnHeightBackLeft);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_TURN_HEIGHT_BACK_LEFT :" + seatTurnHeightBackLeft);
                    break;
                case 559954823:
                    float seatTurnHeightBackRight = settingEventChangeValue.getSeatTurnHeightBackRight();
                    this.h.setSeatTurnHeightBackRight(seatTurnHeightBackRight);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_TURN_HEIGHT_BACK_RIGHT :" + seatTurnHeightBackRight);
                    break;
                case 559954824:
                    float seatbackrestPostionDriver = settingEventChangeValue.getSeatbackrestPostionDriver();
                    this.h.setSeatbackrestPostionDriver(seatbackrestPostionDriver);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_BACKREST_POSTION_DRIVER :" + seatbackrestPostionDriver);
                    break;
                case 559954825:
                    float seatbackrestPostionCopilot = settingEventChangeValue.getSeatbackrestPostionCopilot();
                    this.h.setSeatbackrestPostionCopilot(seatbackrestPostionCopilot);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_BACKREST_POSTION_COPILOT :" + seatbackrestPostionCopilot);
                    break;
                case 559954826:
                    float seatbackrestPostionBackLeft = settingEventChangeValue.getSeatbackrestPostionBackLeft();
                    this.h.setSeatbackrestPostionBackLeft(seatbackrestPostionBackLeft);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_BACKREST_POSTION_BACK_LEFT :" + seatbackrestPostionBackLeft);
                    break;
                case 559954827:
                    float seatbackrestPostionBackRight = settingEventChangeValue.getSeatbackrestPostionBackRight();
                    this.h.setSeatbackrestPostionBackRight(seatbackrestPostionBackRight);
                    LogUtil.debug("AutoSettingImpl", "ID_SEAT_BACKREST_POSTION_BACK_RIGHT :" + seatbackrestPostionBackRight);
                    break;
                case 559954832:
                    float turnCushionHeightDriver = settingEventChangeValue.getTurnCushionHeightDriver();
                    this.h.setTurnCushionHeightDriver(turnCushionHeightDriver);
                    LogUtil.debug("AutoSettingImpl", "ID_TURN_CUSHION_HEIGHT_DRIVER :" + turnCushionHeightDriver);
                    break;
                case 559954833:
                    float turnCushionHeightCopilot = settingEventChangeValue.getTurnCushionHeightCopilot();
                    this.h.setTurnCushionHeightCopilot(turnCushionHeightCopilot);
                    LogUtil.debug("AutoSettingImpl", "ID_TURN_CUSHION_HEIGHT_COPILOT :" + turnCushionHeightCopilot);
                    break;
                case 559954834:
                    float turnCushionHeightBackLeft = settingEventChangeValue.getTurnCushionHeightBackLeft();
                    this.h.setTurnCushionHeightBackLeft(turnCushionHeightBackLeft);
                    LogUtil.debug("AutoSettingImpl", "ID_TURN_CUSHION_HEIGHT_BACK_LEFT :" + turnCushionHeightBackLeft);
                    break;
                case 559954835:
                    float turnCushionHeightBackRight = settingEventChangeValue.getTurnCushionHeightBackRight();
                    this.h.setTurnCushionHeightBackRight(turnCushionHeightBackRight);
                    LogUtil.debug("AutoSettingImpl", "ID_TURN_CUSHION_HEIGHT_BACK_RIGHT :" + turnCushionHeightBackRight);
                    break;
                case 559954836:
                    float turnCaliperSplintHeightDriver = settingEventChangeValue.getTurnCaliperSplintHeightDriver();
                    this.h.setTurnCaliperSplintHeightDriver(turnCaliperSplintHeightDriver);
                    LogUtil.debug("AutoSettingImpl", "ID_TURN_CALIPER_SPLINT_HEIGHT_DRIVER :" + turnCaliperSplintHeightDriver);
                    break;
                case 559954837:
                    float turnCaliperSplintHeightCopilot = settingEventChangeValue.getTurnCaliperSplintHeightCopilot();
                    this.h.setTurnCaliperSplintHeightCopilot(turnCaliperSplintHeightCopilot);
                    LogUtil.debug("AutoSettingImpl", "ID_TURN_CALIPER_SPLINT_HEIGHT_COPILOT :" + turnCaliperSplintHeightCopilot);
                    break;
                case 559954838:
                    float turnCaliperSplintHeightBackLeft = settingEventChangeValue.getTurnCaliperSplintHeightBackLeft();
                    this.h.setTurnCaliperSplintHeightBackLeft(turnCaliperSplintHeightBackLeft);
                    LogUtil.debug("AutoSettingImpl", "ID_TURN_CALIPER_SPLINT_HEIGHT_BACK_LEFT :" + turnCaliperSplintHeightBackLeft);
                    break;
                case 559954839:
                    float turnCaliperSplintHeightBackRight = settingEventChangeValue.getTurnCaliperSplintHeightBackRight();
                    this.h.setTurnCaliperSplintHeightBackRight(turnCaliperSplintHeightBackRight);
                    LogUtil.debug("AutoSettingImpl", "ID_TURN_CALIPER_SPLINT_HEIGHT_BACK_RIGHT :" + turnCaliperSplintHeightBackRight);
                    break;
                case 559954844:
                    float headrestTurnHeightDriver = settingEventChangeValue.getHeadrestTurnHeightDriver();
                    this.h.setHeadrestTurnHeightDriver(headrestTurnHeightDriver);
                    LogUtil.debug("AutoSettingImpl", "ID_HEADREST_TURN_HEIGHT_DRIVER :" + headrestTurnHeightDriver);
                    break;
                case 559954845:
                    float headrestTurnHeightCopilot = settingEventChangeValue.getHeadrestTurnHeightCopilot();
                    this.h.setHeadrestTurnHeightCopilot(headrestTurnHeightCopilot);
                    LogUtil.debug("AutoSettingImpl", "ID_HEADREST_TURN_HEIGHT_COPILOT :" + headrestTurnHeightCopilot);
                    break;
                case 559955504:
                    float headrestTurnHeightBackLeft = settingEventChangeValue.getHeadrestTurnHeightBackLeft();
                    this.h.setHeadrestTurnHeightBackLeft(headrestTurnHeightBackLeft);
                    LogUtil.debug("AutoSettingImpl", "ID_HEADREST_TURN_HEIGHT_BACK_LEFT :" + headrestTurnHeightBackLeft);
                    break;
                case 559955505:
                    float headrestTurnHeightBackRight = settingEventChangeValue.getHeadrestTurnHeightBackRight();
                    this.h.setHeadrestTurnHeightBackRight(headrestTurnHeightBackRight);
                    LogUtil.debug("AutoSettingImpl", "ID_HEADREST_TURN_HEIGHT_BACK_RIGHT :" + headrestTurnHeightBackRight);
                    break;
            }
            this.f22a.m(i, i2, this.h);
            LogUtil.debug("AutoSettingImpl", "notifySettingEventValue:" + i + " result=" + autoPurificationState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void l() {
        if (this.c != null) {
            a aVar = this.g;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.c.quitSafely();
        }
    }

    private void o() {
        if (this.f != null) {
            Settings.Global.putInt(this.f23b.getContentResolver(), "auto_time", 0);
            LogUtil.debug("AutoSettingImpl", "recovery set rtc time");
            this.f.setRtcTime(new int[]{0, 0, 0, 1, 1, 2017, 7});
        }
    }

    @Override // com.ts.appservice.caradapter.d.w
    public void a(SettingEventChangeValue settingEventChangeValue) {
        if (settingEventChangeValue == null) {
            LogUtil.error("AutoSettingImpl", "onSettingChangeEvent:value=null");
            return;
        }
        int propertyId = settingEventChangeValue.getPropertyId();
        LogUtil.debug("AutoSettingImpl", "onSettingChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = settingEventChangeValue.getAreaId();
        messageObtain.obj = settingEventChangeValue;
        this.g.sendMessage(messageObtain);
    }

    public int autoBackDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "autoBackDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAutoBackDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int backLightDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "backLightDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBackLightDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int carHealthDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "carHealthDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setCarHealthDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int carSettingsDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "carSettingsDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setCarSettingsDefaultResetS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int carSettingsReset(int i) {
        LogUtil.debug("AutoSettingImpl", "carSettingsReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setCarSettingsResetS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int clockDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "clockDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setClockDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void d() {
        l();
        com.ts.appservice.caradapter.a.j jVar = this.f22a;
        if (jVar != null) {
            this.d = false;
            jVar.g();
        }
        this.f23b = null;
    }

    public int doorsAndWindowDefaultResset(int i) {
        LogUtil.debug("AutoSettingImpl", "doorsAndWindowDefaultResset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDoorsAndWindowDefaultRessetS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int driveSettingsReset(int i) {
        LogUtil.debug("AutoSettingImpl", "driveSettingsReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDriveSettingsResetS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int equalizerDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "equalizerDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setEqualizerDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void f(CarVendorSettingManager carVendorSettingManager, CarVendorAssistManager carVendorAssistManager) {
        this.e = carVendorSettingManager;
        this.f = carVendorAssistManager;
        try {
            if (this.g == null) {
                this.g = new a(this.c.getLooper());
                LogUtil.info("AutoSettingImpl", "initSettingManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.j jVar = this.f22a;
        if (jVar != null) {
            this.d = true;
            jVar.l();
        }
    }

    public int factoryResetAll(int i) {
        LogUtil.error("AutoSettingImpl", "factoryResetAll=" + i);
        if (this.e == null) {
            return 0;
        }
        try {
            o();
            this.e.setFactoryResetAll(i);
            this.e.setSysFactoryReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get1LevelSeatHeatingState(int i) {
        LogUtil.debug("AutoSettingImpl", "get1LevelSeatHeatingState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int oneLevelSeatHeatingStateR = carVendorSettingManager.getOneLevelSeatHeatingStateR();
            LogUtil.debug("AutoSettingImpl", "getOneLevelSeatHeatingStateR=" + oneLevelSeatHeatingStateR);
            return oneLevelSeatHeatingStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingLevelDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatHeatingLevelDomainR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingLevelDomainR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingLevelLeft() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatHeatingLevelBackLeftR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingLevelBackLeftR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingLevelPassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatHeatingLevelPassengerR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingLevelPassengerR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingLevelRight() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatHeatingLevelBackRightR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingLevelBackRightR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingModeDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatHeatingModeDomainR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingModeDomainR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingModeLeft() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatHeatingModeBackLeftR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingModeBackLeftR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingModePassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatHeatingModePassengerR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingModePassengerR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingModeRight() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatHeatingModeBackRightR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingModeBackRightR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatHeatingStateColipot() {
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingStateColipot");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857879, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingStateColipot prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int get5LevelSeatHeatingStateDriver() {
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingStateDriver");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857878, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int get5LevelSeatHeatingStateLeft() {
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingStateLeft");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857880, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingStateLeft prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int get5LevelSeatHeatingStateRight() {
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingStateRight");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857881, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatHeatingStateRight prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int get5LevelSeatVentilatingLevelDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatVentilatingLevelDomainR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingLevelDomainR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatVentilatingLevelLeft() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatVentilatingLevelBackLeftR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingLevelBackLeftR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatVentilatingLevelPassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatVentilatingLevelPassengerR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingLevelPassengerR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatVentilatingLevelRight() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatVentilatingLevelBackRightR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingLevelBackRightR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatVentilatingModeDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatVentilatingModeDomainR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingModeDomainR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatVentilatingModeLeft() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatVentilatingModeBackLeftR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingModeBackLeftR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatVentilatingModePassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatVentilatingModePassengerR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingModePassengerR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatVentilatingModeRight() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.get5LevelSeatVentilatingModeBackRightR();
            LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingModeBackRightR=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int get5LevelSeatVentilatingStateColipot() {
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingStateColipot");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857871, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingStateColipot prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int get5LevelSeatVentilatingStateDriver() {
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingStateDriver");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857870, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingStateDriver prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int get5LevelSeatVentilatingStateLeft() {
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingStateLeft");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857872, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingStateLeft prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int get5LevelSeatVentilatingStateRight() {
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingStateRight");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857873, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "get5LevelSeatVentilatingStateRight prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getACAutoAir() {
        LogUtil.verbose("AutoSettingImpl", "getACAutoAir");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857543, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getACAutoAir prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getACAutoAirSupport() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int aCAutoAirR = carVendorSettingManager.getACAutoAirR();
            LogUtil.debug("AutoSettingImpl", "getACAutoAirR=" + aCAutoAirR);
            return aCAutoAirR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getACBTWind() {
        LogUtil.verbose("AutoSettingImpl", "getACBTWind");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int aCBTWindR = carVendorSettingManager.getACBTWindR();
            LogUtil.debug("AutoSettingImpl", "getACBTWindR=" + aCBTWindR);
            return aCBTWindR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getACBTWindSupport() {
        return 0;
    }

    public int getACPauseCycle() {
        LogUtil.verbose("AutoSettingImpl", "getACPauseCycle");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int aCPauseCycleR = carVendorSettingManager.getACPauseCycleR();
            LogUtil.debug("AutoSettingImpl", "getACPauseCycleR=" + aCPauseCycleR);
            return aCPauseCycleR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getACPauseCycleSupport() {
        return 0;
    }

    public int getACTunnelCycle() {
        LogUtil.verbose("AutoSettingImpl", "getACTunnelCycle");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857555, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getACTunnelCycle prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getACTunnelCycleSupport() {
        return 0;
    }

    public int getAcTemperatureUnit() {
        LogUtil.verbose("AutoSettingImpl", "getAcTemperatureUnit");
        return 0;
    }

    public int getAcWarmMode() {
        LogUtil.verbose("AutoSettingImpl", "getAcWarmMode");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int acWarmModeR = carVendorSettingManager.getAcWarmModeR();
            LogUtil.debug("AutoSettingImpl", "getAcWarmModeR=" + acWarmModeR);
            return acWarmModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcWarmSupport() {
        return 0;
    }

    public int getAidedDrivingInd() {
        LogUtil.verbose("AutoSettingImpl", "Service getAidedDrivingInd");
        return 0;
    }

    public int getAlarmPromptState() {
        int alarmPromptStateR;
        LogUtil.verbose("AutoSettingImpl", " getAlarmPromptState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                alarmPromptStateR = carVendorSettingManager.getAlarmPromptStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                alarmPromptStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getAlarmPromptState.out=" + alarmPromptStateR);
            return alarmPromptStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getAlarmPromptState.null == mCarSettingManager");
        alarmPromptStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getAlarmPromptState.out=" + alarmPromptStateR);
        return alarmPromptStateR;
    }

    public int getAmbientAreaSupport() {
        LogUtil.verbose("AutoSettingImpl", "getAmbientAreaSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856186, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getAmbientAreaSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAmbientFeedBack() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int ambientFeedBack = carVendorSettingManager.getAmbientFeedBack();
            LogUtil.debug("AutoSettingImpl", "getAmbientFeedBack=" + ambientFeedBack);
            return ambientFeedBack;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmbientLightColorSupport() {
        LogUtil.verbose("AutoSettingImpl", "getAmbientLightColorSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856184, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getAmbientLightColorSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAmbientLightSupport() {
        LogUtil.verbose("AutoSettingImpl", "getAmbientLightSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856185, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getAmbientLightSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAmbientMulticolorMode(int i) {
        LogUtil.verbose("AutoSettingImpl", "getAmbientMulticolorMode");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int ambientMulticolorModeR = carVendorSettingManager.getAmbientMulticolorModeR();
            LogUtil.debug("AutoSettingImpl", "getAmbientMulticolorModeR=" + ambientMulticolorModeR);
            return ambientMulticolorModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmbientMulticolorState(int i) {
        LogUtil.verbose("AutoSettingImpl", "getAmbientMulticolorState");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int ambientMulticolorStateR = carVendorSettingManager.getAmbientMulticolorStateR();
            LogUtil.debug("AutoSettingImpl", "getAmbientMulticolorStateR=" + ambientMulticolorStateR);
            return ambientMulticolorStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmbientMusicMode(int i) {
        LogUtil.verbose("AutoSettingImpl", "getAmbientMusicMode setting");
        return 0;
    }

    public int getAmbientMusicModeState(int i) {
        LogUtil.verbose("AutoSettingImpl", "getAmbientMusicModeState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557860611, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getAmbientMusicModeState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAppUseLimitState() {
        LogUtil.verbose("AutoSettingImpl", "getAppUseLimitState");
        return 0;
    }

    public int getAppUseLimitSupport() {
        return 0;
    }

    public int getAutoExternalRearMirrorFollowUpSwitch() {
        LogUtil.verbose("AutoSettingImpl", "getAutoExternalRearMirrorFollowUpSwitch");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int autoExternalRearMirrorFollowUpSwitchR = carVendorSettingManager.getAutoExternalRearMirrorFollowUpSwitchR();
            LogUtil.debug("AutoSettingImpl", "getAutoExternalRearMirrorFollowUpSwitchR=" + autoExternalRearMirrorFollowUpSwitchR);
            return autoExternalRearMirrorFollowUpSwitchR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        LogUtil.verbose("AutoSettingImpl", "getAutoExternalRearMirrorFollowUpSwitchSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856053, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getAutoExternalRearMirrorFollowUpSwitchSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAutoPowerOffState() {
        LogUtil.verbose("AutoSettingImpl", "getAutoPowerOffState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857597, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getAutoPowerOffState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAutoPowerOffSupport() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int autoPowerOffStateR = carVendorSettingManager.getAutoPowerOffStateR();
            LogUtil.debug("AutoSettingImpl", "getAutoPowerOffStateR=" + autoPowerOffStateR);
            return autoPowerOffStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoPowerOnState() {
        LogUtil.verbose("AutoSettingImpl", "getAutoPowerOnState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857595, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getAutoPowerOnState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAutoPowerOnStateSupport() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int autoPowerOnStateR = carVendorSettingManager.getAutoPowerOnStateR();
            LogUtil.debug("AutoSettingImpl", "getAutoPowerOnStateR=" + autoPowerOnStateR);
            return autoPowerOnStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoPurificationState() {
        LogUtil.verbose("AutoSettingImpl", "getAutoPurificationState");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int autoPurificationState = carVendorSettingManager.getAutoPurificationState();
            LogUtil.debug("AutoSettingImpl", "getAutoPurificationState=" + autoPurificationState);
            return autoPurificationState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoPurificationSupport() {
        return 0;
    }

    public int getAutoRainWiperState() {
        LogUtil.verbose("AutoSettingImpl", "getAutoRainWiperState");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int i = carVendorSettingManager.getresetMotorPretensionerSeatbelt();
            LogUtil.debug("AutoSettingImpl", "getresetMotorPretensionerSeatbelt=" + i);
            return i;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoWiperSupport() {
        return 0;
    }

    public int getAutomaticParking() {
        return 0;
    }

    public int getBackDoorElectricMode() {
        LogUtil.verbose("AutoSettingImpl", "getBackDoorElectricMode");
        return 0;
    }

    public int getBackDoorOpenedHeight() {
        LogUtil.verbose("AutoSettingImpl", "getBackDoorOpenedHeight");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int backDoorOpenedHeightR = carVendorSettingManager.getBackDoorOpenedHeightR();
            LogUtil.debug("AutoSettingImpl", "getBackDoorOpenedHeightR=" + backDoorOpenedHeightR);
            return backDoorOpenedHeightR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBackHomeLightDelay() {
        LogUtil.verbose("AutoSettingImpl", "getBackHomeLightDelay");
        return 0;
    }

    public int getBackHomeLightDelayValue() {
        LogUtil.verbose("AutoSettingImpl", "getBackHomeLightDelayValue");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857626, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getBackHomeLightDelayValue prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getBdMassageLevelLeftRear() {
        int bdMassageLevelLeftRearR;
        LogUtil.verbose("AutoSettingImpl", " getBdMassageLevelLeftRear.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                bdMassageLevelLeftRearR = carVendorSettingManager.getBdMassageLevelLeftRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                bdMassageLevelLeftRearR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getBdMassageLevelLeftRear.out service =" + bdMassageLevelLeftRearR);
            return bdMassageLevelLeftRearR;
        }
        LogUtil.verbose("AutoSettingImpl", " getBdMassageLevelLeftRear.null");
        bdMassageLevelLeftRearR = -1;
        LogUtil.verbose("AutoSettingImpl", " getBdMassageLevelLeftRear.out service =" + bdMassageLevelLeftRearR);
        return bdMassageLevelLeftRearR;
    }

    public int getBdMassageLevelRightRear() {
        int bdMassageLevelRightRearR;
        LogUtil.verbose("AutoSettingImpl", " getBdMassageLevelRightRear.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                bdMassageLevelRightRearR = carVendorSettingManager.getBdMassageLevelRightRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                bdMassageLevelRightRearR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getBdMassageLevelRightRear.out service =" + bdMassageLevelRightRearR);
            return bdMassageLevelRightRearR;
        }
        LogUtil.verbose("AutoSettingImpl", " getBdMassageLevelRightRear.null");
        bdMassageLevelRightRearR = -1;
        LogUtil.verbose("AutoSettingImpl", " getBdMassageLevelRightRear.out service =" + bdMassageLevelRightRearR);
        return bdMassageLevelRightRearR;
    }

    public int getBdMassageModeLeftRear() {
        int bdMassageModeLeftRearR;
        LogUtil.verbose("AutoSettingImpl", " getBdMassageModeLeftRear.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                bdMassageModeLeftRearR = carVendorSettingManager.getBdMassageModeLeftRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                bdMassageModeLeftRearR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getBdMassageModeLeftRear.out service =" + bdMassageModeLeftRearR);
            return bdMassageModeLeftRearR;
        }
        LogUtil.verbose("AutoSettingImpl", " getBdMassageModeLeftRear.null");
        bdMassageModeLeftRearR = -1;
        LogUtil.verbose("AutoSettingImpl", " getBdMassageModeLeftRear.out service =" + bdMassageModeLeftRearR);
        return bdMassageModeLeftRearR;
    }

    public int getBdMassageModeRightRear() {
        int bdMassageModeRightRearR;
        LogUtil.verbose("AutoSettingImpl", " getBdMassageModeRightRear.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                bdMassageModeRightRearR = carVendorSettingManager.getBdMassageModeRightRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                bdMassageModeRightRearR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getBdMassageModeRightRear.out service =" + bdMassageModeRightRearR);
            return bdMassageModeRightRearR;
        }
        LogUtil.verbose("AutoSettingImpl", " getBdMassageModeRightRear.null");
        bdMassageModeRightRearR = -1;
        LogUtil.verbose("AutoSettingImpl", " getBdMassageModeRightRear.out service =" + bdMassageModeRightRearR);
        return bdMassageModeRightRearR;
    }

    public int getBleOpenDriverDoorState() {
        LogUtil.debug("AutoSettingImpl", "getBleOpenDriverDoorState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int bleOpenDriverDoorStateR = carVendorSettingManager.getBleOpenDriverDoorStateR();
            LogUtil.debug("AutoSettingImpl", "getBleOpenDriverDoorStateR=" + bleOpenDriverDoorStateR);
            return bleOpenDriverDoorStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBrakeCloseDoor() {
        LogUtil.debug("AutoSettingImpl", "getBrakeCloseDoor is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int brakeCloseDoorR = carVendorSettingManager.getBrakeCloseDoorR();
            LogUtil.debug("AutoSettingImpl", "getBrakeCloseDoorR=" + brakeCloseDoorR);
            return brakeCloseDoorR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBrakePedalMode() {
        LogUtil.verbose("AutoSettingImpl", "getBrakePedalMode");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int brakePedalModeR = carVendorSettingManager.getBrakePedalModeR();
            LogUtil.debug("AutoSettingImpl", "getBrakePedalModeR=" + brakePedalModeR);
            return brakePedalModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBrakePedalModeSupport() {
        return 0;
    }

    public int getBsisCollisionStateR() {
        LogUtil.info("AutoSettingImpl", "getBsisCollisionStateR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int bsisCollisionStateR = carVendorSettingManager.getBsisCollisionStateR();
            LogUtil.debug("AutoSettingImpl", "getBsisCollisionStateR=" + bsisCollisionStateR);
            return bsisCollisionStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBsisFuncStatusR() {
        LogUtil.info("AutoSettingImpl", "getBsisFuncStatusR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int bsisFuncStatusR = carVendorSettingManager.getBsisFuncStatusR();
            LogUtil.debug("AutoSettingImpl", "getBsisFuncStatusR=" + bsisFuncStatusR);
            return bsisFuncStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCampingBlanceAction() {
        LogUtil.debug("AutoSettingImpl", "getCampingBlanceAction is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int campingBlanceActionR = carVendorSettingManager.getCampingBlanceActionR();
            LogUtil.debug("AutoSettingImpl", "getCampingBlanceActionR=" + campingBlanceActionR);
            return campingBlanceActionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCampingBlanceState() {
        LogUtil.debug("AutoSettingImpl", "getCampingBlanceState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int campingBlanceStateR = carVendorSettingManager.getCampingBlanceStateR();
            LogUtil.debug("AutoSettingImpl", "getCampingBlanceStateR=" + campingBlanceStateR);
            return campingBlanceStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCampingBlanceSupported() {
        LogUtil.debug("AutoSettingImpl", "getCampingBlanceSupported is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int campingBlanceSupportedR = carVendorSettingManager.getCampingBlanceSupportedR();
            LogUtil.debug("AutoSettingImpl", "getCampingBlanceSupportedR=" + campingBlanceSupportedR);
            return campingBlanceSupportedR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCanopyAutomaticDimming() {
        int canopyAutomaticDimmingR;
        LogUtil.verbose("AutoSettingImpl", " getCanopyAutomaticDimming.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                canopyAutomaticDimmingR = carVendorSettingManager.getCanopyAutomaticDimmingR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                canopyAutomaticDimmingR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCanopyAutomaticDimming.out=" + canopyAutomaticDimmingR);
            return canopyAutomaticDimmingR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCanopyAutomaticDimming.null == mCarSettingManager");
        canopyAutomaticDimmingR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCanopyAutomaticDimming.out=" + canopyAutomaticDimmingR);
        return canopyAutomaticDimmingR;
    }

    public int getCarFridgeType() {
        LogUtil.verbose("AutoSettingImpl", " getCarFridgeType.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859199, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getCarFridgeType prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getCarSeatLayout() {
        int carSeatLayoutR;
        LogUtil.verbose("AutoSettingImpl", " getCarSeatLayout.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carSeatLayoutR = carVendorSettingManager.getCarSeatLayoutR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                carSeatLayoutR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCarSeatLayout.out=" + carSeatLayoutR);
            return carSeatLayoutR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCarSeatLayout.null == mCarSettingManager");
        carSeatLayoutR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCarSeatLayout.out=" + carSeatLayoutR);
        return carSeatLayoutR;
    }

    public int getCarTipsSoundSupport() {
        return 0;
    }

    public int getCarTipsSoundType() {
        LogUtil.verbose("AutoSettingImpl", "getCarTipsSoundType");
        return 0;
    }

    public int getCenterRearSafebeltStatus() {
        int centerRearSafebeltStatusR;
        LogUtil.verbose("AutoSettingImpl", " getCenterRearSafebeltStatus.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                centerRearSafebeltStatusR = carVendorSettingManager.getCenterRearSafebeltStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                centerRearSafebeltStatusR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCenterRearSafebeltStatus.out service =" + centerRearSafebeltStatusR);
            return centerRearSafebeltStatusR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCenterRearSafebeltStatus.null");
        centerRearSafebeltStatusR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCenterRearSafebeltStatus.out service =" + centerRearSafebeltStatusR);
        return centerRearSafebeltStatusR;
    }

    public int getChargingPort() {
        LogUtil.verbose("AutoSettingImpl", "getChargingPort");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int chargingPortR27C = carVendorSettingManager.getChargingPortR27C();
            LogUtil.debug("AutoSettingImpl", "getChargingPortR27C=" + chargingPortR27C);
            return chargingPortR27C;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargingPortR475() {
        LogUtil.verbose("AutoSettingImpl", "getChargingPort");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int chargingPortR475 = carVendorSettingManager.getChargingPortR475();
            LogUtil.debug("AutoSettingImpl", "getChargingPortR475=" + chargingPortR475);
            return chargingPortR475;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCloseDoorLevel() {
        LogUtil.debug("AutoSettingImpl", "getCloseDoorLevel is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int closeDoorLevelR = carVendorSettingManager.getCloseDoorLevelR();
            LogUtil.debug("AutoSettingImpl", "getCloseDoorLevelR=" + closeDoorLevelR);
            return closeDoorLevelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getComSpeedDownState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int comSpeedDownStateR = carVendorSettingManager.getComSpeedDownStateR();
            LogUtil.debug("AutoSettingImpl", "getComSpeedDownStateR=" + comSpeedDownStateR);
            return comSpeedDownStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getComSpeedUpState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int comSpeedUpStateR = carVendorSettingManager.getComSpeedUpStateR();
            LogUtil.debug("AutoSettingImpl", "getComSpeedUpStateR=" + comSpeedUpStateR);
            return comSpeedUpStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getComfortableParkingState() {
        LogUtil.verbose("AutoSettingImpl", "getComfortableParkingState");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int comfortableParkingStateR = carVendorSettingManager.getComfortableParkingStateR();
            LogUtil.debug("AutoSettingImpl", "getComfortableParkingStateR=" + comfortableParkingStateR);
            return comfortableParkingStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getComfortableParkingSupport() {
        return 0;
    }

    public int getControlCallbackState() {
        int controlCallbackStateR;
        LogUtil.verbose("AutoSettingImpl", " getControlCallbackState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                controlCallbackStateR = carVendorSettingManager.getControlCallbackStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                controlCallbackStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getControlCallbackState.out=" + controlCallbackStateR);
            return controlCallbackStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getControlCallbackState.null == mCarSettingManager");
        controlCallbackStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getControlCallbackState.out=" + controlCallbackStateR);
        return controlCallbackStateR;
    }

    public int getCpdImsSwitchState() {
        int cpdImsSwitchStateR;
        LogUtil.verbose("AutoSettingImpl", " getCpdImsSwitchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                cpdImsSwitchStateR = carVendorSettingManager.getCpdImsSwitchStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                cpdImsSwitchStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCpdImsSwitchState.out=" + cpdImsSwitchStateR);
            return cpdImsSwitchStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCpdImsSwitchState.null == mCarSettingManager");
        cpdImsSwitchStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCpdImsSwitchState.out=" + cpdImsSwitchStateR);
        return cpdImsSwitchStateR;
    }

    public int getCuraSwitchState() {
        int curaSwitchStateR;
        LogUtil.verbose("AutoSettingImpl", " getCuraSwitchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                curaSwitchStateR = carVendorSettingManager.getCuraSwitchStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                curaSwitchStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCuraSwitchState.out=" + curaSwitchStateR);
            return curaSwitchStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCuraSwitchState.null == mCarSettingManager");
        curaSwitchStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCuraSwitchState.out=" + curaSwitchStateR);
        return curaSwitchStateR;
    }

    public int getCurrentModeOfTailState() {
        int currentModeOfTailStateR;
        LogUtil.verbose("AutoSettingImpl", " getCurrentModeOfTailState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                currentModeOfTailStateR = carVendorSettingManager.getCurrentModeOfTailStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentModeOfTailStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCurrentModeOfTailState.out=" + currentModeOfTailStateR);
            return currentModeOfTailStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCurrentModeOfTailState.null == mCarSettingManager");
        currentModeOfTailStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCurrentModeOfTailState.out=" + currentModeOfTailStateR);
        return currentModeOfTailStateR;
    }

    public int getCurrentStateOfTheTailState() {
        int currentStateOfTheTailStateR;
        LogUtil.verbose("AutoSettingImpl", " getCurrentStateOfTheTailState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                currentStateOfTheTailStateR = carVendorSettingManager.getCurrentStateOfTheTailStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentStateOfTheTailStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCurrentStateOfTheTailState.out=" + currentStateOfTheTailStateR);
            return currentStateOfTheTailStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCurrentStateOfTheTailState.null == mCarSettingManager");
        currentStateOfTheTailStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCurrentStateOfTheTailState.out=" + currentStateOfTheTailStateR);
        return currentStateOfTheTailStateR;
    }

    public int getCurrentStatusOfDimmingSunroof() {
        int currentStatusOfDimmingSunroofR;
        LogUtil.verbose("AutoSettingImpl", " getCurrentStatusOfDimmingSunroof.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                currentStatusOfDimmingSunroofR = carVendorSettingManager.getCurrentStatusOfDimmingSunroofR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentStatusOfDimmingSunroofR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCurrentStatusOfDimmingSunroof.out=" + currentStatusOfDimmingSunroofR);
            return currentStatusOfDimmingSunroofR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCurrentStatusOfDimmingSunroof.null == mCarSettingManager");
        currentStatusOfDimmingSunroofR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCurrentStatusOfDimmingSunroof.out=" + currentStatusOfDimmingSunroofR);
        return currentStatusOfDimmingSunroofR;
    }

    public int getCurrentTransmittanceOfSkylight() {
        int currentTransmittanceOfSkylightR;
        LogUtil.verbose("AutoSettingImpl", " getCurrentTransmittanceOfSkylight.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                currentTransmittanceOfSkylightR = carVendorSettingManager.getCurrentTransmittanceOfSkylightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTransmittanceOfSkylightR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getCurrentTransmittanceOfSkylight.out=" + currentTransmittanceOfSkylightR);
            return currentTransmittanceOfSkylightR;
        }
        LogUtil.verbose("AutoSettingImpl", " getCurrentTransmittanceOfSkylight.null == mCarSettingManager");
        currentTransmittanceOfSkylightR = -1;
        LogUtil.verbose("AutoSettingImpl", " getCurrentTransmittanceOfSkylight.out=" + currentTransmittanceOfSkylightR);
        return currentTransmittanceOfSkylightR;
    }

    public int getCushionSupport(int i) {
        return 0;
    }

    public int getCvcMode() {
        LogUtil.debug("AutoSettingImpl", "getCvcMode is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int cvcModeR = carVendorSettingManager.getCvcModeR();
            LogUtil.debug("AutoSettingImpl", "getCvcModeR=" + cvcModeR);
            return cvcModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDampState() {
        LogUtil.debug("AutoSettingImpl", "getDampState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int dampStateR = carVendorSettingManager.getDampStateR();
            LogUtil.debug("AutoSettingImpl", "getDampStateR=" + dampStateR);
            return dampStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDelayDownPowerTime() {
        int delayDownPowerTimeR;
        LogUtil.verbose("AutoSettingImpl", " getDelayDownPowerTime.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                delayDownPowerTimeR = carVendorSettingManager.getDelayDownPowerTimeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                delayDownPowerTimeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDelayDownPowerTime.out=" + delayDownPowerTimeR);
            return delayDownPowerTimeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDelayDownPowerTime.null == mCarSettingManager");
        delayDownPowerTimeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDelayDownPowerTime.out=" + delayDownPowerTimeR);
        return delayDownPowerTimeR;
    }

    public int getDelayPowerDownTime() {
        int delayPowerDownTimeR;
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerDownTime.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                delayPowerDownTimeR = carVendorSettingManager.getDelayPowerDownTimeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                delayPowerDownTimeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDelayPowerDownTime.out=" + delayPowerDownTimeR);
            return delayPowerDownTimeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerDownTime.null == mCarSettingManager");
        delayPowerDownTimeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerDownTime.out=" + delayPowerDownTimeR);
        return delayPowerDownTimeR;
    }

    public int getDelayPowerOffState() {
        int delayPowerOffStateR;
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                delayPowerOffStateR = carVendorSettingManager.getDelayPowerOffStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                delayPowerOffStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffState.out=" + delayPowerOffStateR);
            return delayPowerOffStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffState.null == mCarSettingManager");
        delayPowerOffStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffState.out=" + delayPowerOffStateR);
        return delayPowerOffStateR;
    }

    public int getDelayPowerOffStatus() {
        int delayPowerOffStatusR;
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffStatus.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                delayPowerOffStatusR = carVendorSettingManager.getDelayPowerOffStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                delayPowerOffStatusR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffStatus.out=" + delayPowerOffStatusR);
            return delayPowerOffStatusR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffStatus.null == mCarSettingManager");
        delayPowerOffStatusR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffStatus.out=" + delayPowerOffStatusR);
        return delayPowerOffStatusR;
    }

    public int getDelayPowerOffTime() {
        int delayPowerOffTimeR;
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffTime.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                delayPowerOffTimeR = carVendorSettingManager.getDelayPowerOffTimeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                delayPowerOffTimeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffTime.out=" + delayPowerOffTimeR);
            return delayPowerOffTimeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffTime.null == mCarSettingManager");
        delayPowerOffTimeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDelayPowerOffTime.out=" + delayPowerOffTimeR);
        return delayPowerOffTimeR;
    }

    public int getDigtalKeySupport() {
        return 0;
    }

    public int getDimmingCanopyConfiguration() {
        int dimmingCanopyConfigurationR;
        LogUtil.verbose("AutoSettingImpl", " getDimmingCanopyConfiguration.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                dimmingCanopyConfigurationR = carVendorSettingManager.getDimmingCanopyConfigurationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                dimmingCanopyConfigurationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDimmingCanopyConfiguration.out=" + dimmingCanopyConfigurationR);
            return dimmingCanopyConfigurationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDimmingCanopyConfiguration.null == mCarSettingManager");
        dimmingCanopyConfigurationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDimmingCanopyConfiguration.out=" + dimmingCanopyConfigurationR);
        return dimmingCanopyConfigurationR;
    }

    public int getDimmingCanopyLowCode() {
        int dimmingCanopyLowCodeR;
        LogUtil.verbose("AutoSettingImpl", " getDimmingCanopyLowCode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                dimmingCanopyLowCodeR = carVendorSettingManager.getDimmingCanopyLowCodeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                dimmingCanopyLowCodeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDimmingCanopyLowCode.out=" + dimmingCanopyLowCodeR);
            return dimmingCanopyLowCodeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDimmingCanopyLowCode.null == mCarSettingManager");
        dimmingCanopyLowCodeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDimmingCanopyLowCode.out=" + dimmingCanopyLowCodeR);
        return dimmingCanopyLowCodeR;
    }

    public int getDisinfState() {
        LogUtil.verbose("AutoSettingImpl", "getDisinfStateR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857705, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getDisinfState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDlgMessageCode() {
        int dlgMessageCodeR;
        LogUtil.verbose("AutoSettingImpl", " getDlgMessageCode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                dlgMessageCodeR = carVendorSettingManager.getDlgMessageCodeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                dlgMessageCodeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDlgMessageCode.out=" + dlgMessageCodeR);
            return dlgMessageCodeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDlgMessageCode.null == mCarSettingManager");
        dlgMessageCodeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDlgMessageCode.out=" + dlgMessageCodeR);
        return dlgMessageCodeR;
    }

    public int getDoorHeightSupport() {
        LogUtil.verbose("AutoSettingImpl", "getDoorHeightSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856076, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getDoorHeightSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorknobAutoShrinkState() {
        LogUtil.verbose("AutoSettingImpl", "getDoorknobAutoShrinkState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857565, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getDoorknobAutoShrinkState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDoorknobAutoShrinkSupport() {
        return 0;
    }

    public int getDragAndDropModeTipsStateR() {
        LogUtil.info("AutoSettingImpl", "getDragAndDropModeTipsStateR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int dragAndDropModeTipsStateR = carVendorSettingManager.getDragAndDropModeTipsStateR();
            LogUtil.debug("AutoSettingImpl", "getDragAndDropModeTipsStateR=" + dragAndDropModeTipsStateR);
            return dragAndDropModeTipsStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDragConnectionType() {
        int dragConnectionTypeR;
        LogUtil.verbose("AutoSettingImpl", " getDragConnectionType.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                dragConnectionTypeR = carVendorSettingManager.getDragConnectionTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                dragConnectionTypeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDragConnectionType.out service =" + dragConnectionTypeR);
            return dragConnectionTypeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDragConnectionType.null");
        dragConnectionTypeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDragConnectionType.out service =" + dragConnectionTypeR);
        return dragConnectionTypeR;
    }

    public int getDragModeForwardAndBackwardStateR() {
        LogUtil.info("AutoSettingImpl", "getDragModeForwardAndBackwardStateR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int dragModeForwardAndBackwardStateR = carVendorSettingManager.getDragModeForwardAndBackwardStateR();
            LogUtil.debug("AutoSettingImpl", "getDragModeForwardAndBackwardStateR=" + dragModeForwardAndBackwardStateR);
            return dragModeForwardAndBackwardStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDragType() {
        int dragTypeR;
        LogUtil.verbose("AutoSettingImpl", " getDragType.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                dragTypeR = carVendorSettingManager.getDragTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                dragTypeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDragType.out service =" + dragTypeR);
            return dragTypeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDragType.null");
        dragTypeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDragType.out service =" + dragTypeR);
        return dragTypeR;
    }

    public int getDriveDoorCloseInd() {
        return 0;
    }

    public int getDriveMode() {
        int driveModeR;
        LogUtil.verbose("AutoSettingImpl", " getDriveMode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                driveModeR = carVendorSettingManager.getDriveModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driveModeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDriveMode.out=" + driveModeR);
            return driveModeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDriveMode.null == mCarSettingManager");
        driveModeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDriveMode.out=" + driveModeR);
        return driveModeR;
    }

    public int getDriverMasToggleModeConfig() {
        int driverMasToggleModeConfigR;
        LogUtil.verbose("AutoSettingImpl", " getDriverMasToggleModeConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                driverMasToggleModeConfigR = carVendorSettingManager.getDriverMasToggleModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driverMasToggleModeConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDriverMasToggleModeConfig.out service =" + driverMasToggleModeConfigR);
            return driverMasToggleModeConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDriverMasToggleModeConfig.null");
        driverMasToggleModeConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDriverMasToggleModeConfig.out service =" + driverMasToggleModeConfigR);
        return driverMasToggleModeConfigR;
    }

    public int getDriverSeatAutoReturn() {
        LogUtil.verbose("AutoSettingImpl", "getDriverSeatAutoReturn");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857599, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getDriverSeatAutoReturn prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDriverSeatAutoReturnB3() {
        LogUtil.debug("AutoSettingImpl", " getDriverSeatAutoReturnB3.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858429, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getDriverSeatAutoReturnB3 prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDriverSeatHeating437State() {
        int driverSeatHeating437StateR;
        LogUtil.verbose("AutoSettingImpl", " getDriverSeatHeating437State.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                driverSeatHeating437StateR = carVendorSettingManager.getDriverSeatHeating437StateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driverSeatHeating437StateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDriverSeatHeating437State.out=" + driverSeatHeating437StateR);
            return driverSeatHeating437StateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDriverSeatHeating437State.null");
        driverSeatHeating437StateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDriverSeatHeating437State.out=" + driverSeatHeating437StateR);
        return driverSeatHeating437StateR;
    }

    public int getDriverSeatVentilating437State() {
        int driverSeatVentilating437StateR;
        LogUtil.verbose("AutoSettingImpl", " getDriverSeatVentilating437State.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                driverSeatVentilating437StateR = carVendorSettingManager.getDriverSeatVentilating437StateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                driverSeatVentilating437StateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getDriverSeatVentilating437State.out=" + driverSeatVentilating437StateR);
            return driverSeatVentilating437StateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getDriverSeatVentilating437State.null");
        driverSeatVentilating437StateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getDriverSeatVentilating437State.out=" + driverSeatVentilating437StateR);
        return driverSeatVentilating437StateR;
    }

    public int getDyHeightLevel() {
        LogUtil.debug("AutoSettingImpl", "getDyHeightLevel is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int dyHeightLevelR = carVendorSettingManager.getDyHeightLevelR();
            LogUtil.debug("AutoSettingImpl", "getDyHeightLevelR=" + dyHeightLevelR);
            return dyHeightLevelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDyHeightToggleState() {
        LogUtil.debug("AutoSettingImpl", "getDyHeightToggleState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int dyHeightToggleStateR = carVendorSettingManager.getDyHeightToggleStateR();
            LogUtil.debug("AutoSettingImpl", "getDyHeightToggleStateR=" + dyHeightToggleStateR);
            return dyHeightToggleStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDyHeightTurnAction() {
        LogUtil.debug("AutoSettingImpl", "getDyHeightTurnAction is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int dyHeightTurnActionR = carVendorSettingManager.getDyHeightTurnActionR();
            LogUtil.debug("AutoSettingImpl", "getDyHeightTurnActionR=" + dyHeightTurnActionR);
            return dyHeightTurnActionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDynamicSuspensionMode() {
        LogUtil.verbose("AutoSettingImpl", "getDynamicSuspensionMode");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857611, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getDynamicSuspensionMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDynamicSuspensionModeSupport() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int dynamicSuspensionModeR = carVendorSettingManager.getDynamicSuspensionModeR();
            LogUtil.debug("AutoSettingImpl", "getDynamicSuspensionModeR=" + dynamicSuspensionModeR);
            return dynamicSuspensionModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEcuMassageLevelLeft() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int ecuMassageLevelBackLeftR = carVendorSettingManager.getEcuMassageLevelBackLeftR();
            LogUtil.debug("AutoSettingImpl", "getEcuMassageLevelBackLeftR=" + ecuMassageLevelBackLeftR);
            return ecuMassageLevelBackLeftR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEcuMassageLevelRight() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int ecuMassageLevelBackRightR = carVendorSettingManager.getEcuMassageLevelBackRightR();
            LogUtil.debug("AutoSettingImpl", "getEcuMassageLevelBackRightR=" + ecuMassageLevelBackRightR);
            return ecuMassageLevelBackRightR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEcuMassageModeLeft() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int ecuMassageModeBackLeftR = carVendorSettingManager.getEcuMassageModeBackLeftR();
            LogUtil.debug("AutoSettingImpl", "getEcuMassageModeBackLeftR=" + ecuMassageModeBackLeftR);
            return ecuMassageModeBackLeftR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEcuMassageModeRight() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int ecuMassageModeBackRightR = carVendorSettingManager.getEcuMassageModeBackRightR();
            LogUtil.debug("AutoSettingImpl", "getEcuMassageModeBackRightR=" + ecuMassageModeBackRightR);
            return ecuMassageModeBackRightR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getElectricTailConfiguration() {
        int electricTailConfigurationR;
        LogUtil.verbose("AutoSettingImpl", " getElectricTailConfiguration.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                electricTailConfigurationR = carVendorSettingManager.getElectricTailConfigurationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                electricTailConfigurationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getElectricTailConfiguration.out=" + electricTailConfigurationR);
            return electricTailConfigurationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getElectricTailConfiguration.null == mCarSettingManager");
        electricTailConfigurationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getElectricTailConfiguration.out=" + electricTailConfigurationR);
        return electricTailConfigurationR;
    }

    public int getElectricTailMaintenanceFuntatusState() {
        int electricTailMaintenanceFuntatusStateR;
        LogUtil.verbose("AutoSettingImpl", " getElectricTailMaintenanceFuntatusState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                electricTailMaintenanceFuntatusStateR = carVendorSettingManager.getElectricTailMaintenanceFuntatusStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                electricTailMaintenanceFuntatusStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getElectricTailMaintenanceFuntatusState.out=" + electricTailMaintenanceFuntatusStateR);
            return electricTailMaintenanceFuntatusStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getElectricTailMaintenanceFuntatusState == null");
        electricTailMaintenanceFuntatusStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getElectricTailMaintenanceFuntatusState.out=" + electricTailMaintenanceFuntatusStateR);
        return electricTailMaintenanceFuntatusStateR;
    }

    public int getElectroniChildLockConfig() {
        LogUtil.verbose("AutoSettingImpl", "getElectroniChildLockConfig");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856207, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getElectroniChildLockConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getEnergyFeedback() {
        LogUtil.verbose("AutoSettingImpl", "getEnergyFeedback");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857788, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getEnergyFeedback prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getEngineSoundSupport() {
        return 0;
    }

    public int getEngineSoundType() {
        LogUtil.verbose("AutoSettingImpl", "getEngineSoundType");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int engineSoundTypeR = carVendorSettingManager.getEngineSoundTypeR();
            LogUtil.debug("AutoSettingImpl", "getEngineSoundTypeR=" + engineSoundTypeR);
            return engineSoundTypeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getExclusiveSaveFlag() {
        int exclusiveSaveFlagR;
        LogUtil.verbose("AutoSettingImpl", " getExclusiveSaveFlag.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                exclusiveSaveFlagR = carVendorSettingManager.getExclusiveSaveFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                exclusiveSaveFlagR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getExclusiveSaveFlag.out=" + exclusiveSaveFlagR);
            return exclusiveSaveFlagR;
        }
        LogUtil.verbose("AutoSettingImpl", " getExclusiveSaveFlag.null == mCarSettingManager");
        exclusiveSaveFlagR = -1;
        LogUtil.verbose("AutoSettingImpl", " getExclusiveSaveFlag.out=" + exclusiveSaveFlagR);
        return exclusiveSaveFlagR;
    }

    public int getExhibitionMode() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int exhibitionMode = carVendorSettingManager.getExhibitionMode();
            LogUtil.debug("AutoSettingImpl", "getExhibitionMode=" + exhibitionMode);
            return exhibitionMode;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getExhibitionStartEnginePrompt() {
        LogUtil.verbose("AutoSettingImpl", "getExhibitionStartEnginePrompt");
        return 0;
    }

    public int getFdCampingBlanceAction() {
        LogUtil.debug("AutoSettingImpl", "getFdCampingBlanceAction is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdCampingBlanceActionR = carVendorSettingManager.getFdCampingBlanceActionR();
            LogUtil.debug("AutoSettingImpl", "getFdCampingBlanceActionR=" + fdCampingBlanceActionR);
            return fdCampingBlanceActionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdCampingBlanceState() {
        LogUtil.debug("AutoSettingImpl", "getFdCampingBlanceState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdCampingBlanceStateR = carVendorSettingManager.getFdCampingBlanceStateR();
            LogUtil.debug("AutoSettingImpl", "getFdCampingBlanceStateR=" + fdCampingBlanceStateR);
            return fdCampingBlanceStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdCampingBlanceSupported() {
        LogUtil.debug("AutoSettingImpl", "getFdCampingBlanceSupported is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdCampingBlanceSupportedR = carVendorSettingManager.getFdCampingBlanceSupportedR();
            LogUtil.debug("AutoSettingImpl", "getFdCampingBlanceSupportedR=" + fdCampingBlanceSupportedR);
            return fdCampingBlanceSupportedR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdComSpeedUpState() {
        int fdComSpeedUpStateR;
        LogUtil.verbose("AutoSettingImpl", " getFdComSpeedUpState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fdComSpeedUpStateR = carVendorSettingManager.getFdComSpeedUpStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fdComSpeedUpStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFdComSpeedUpState.out=" + fdComSpeedUpStateR);
            return fdComSpeedUpStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFdComSpeedUpState.null == mCarSettingManager");
        fdComSpeedUpStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFdComSpeedUpState.out=" + fdComSpeedUpStateR);
        return fdComSpeedUpStateR;
    }

    public int getFdCrcCheckNum() {
        LogUtil.debug("AutoSettingImpl", "getFdCRCCheckNum is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdCrcCheckNumR = carVendorSettingManager.getFdCrcCheckNumR();
            LogUtil.debug("AutoSettingImpl", "getFdCrcCheckNumR=" + fdCrcCheckNumR);
            return fdCrcCheckNumR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdDampState() {
        LogUtil.debug("AutoSettingImpl", "getFdDampState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdDampStateR = carVendorSettingManager.getFdDampStateR();
            LogUtil.debug("AutoSettingImpl", "getFdDampStateR=" + fdDampStateR);
            return fdDampStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdDyHeightLevel() {
        LogUtil.debug("AutoSettingImpl", "getFdDyHeightLevel is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdDyHeightLevelR = carVendorSettingManager.getFdDyHeightLevelR();
            LogUtil.debug("AutoSettingImpl", "getFdDyHeightLevelR=" + fdDyHeightLevelR);
            return fdDyHeightLevelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdDyHeightToggleState() {
        LogUtil.debug("AutoSettingImpl", "getFdDyHeightToggleState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdDyHeightToggleStateR = carVendorSettingManager.getFdDyHeightToggleStateR();
            LogUtil.debug("AutoSettingImpl", "getFdDyHeightToggleStateR=" + fdDyHeightToggleStateR);
            return fdDyHeightToggleStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdDyHeightTurnAction() {
        LogUtil.debug("AutoSettingImpl", "getFdDyHeightTurnAction is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdDyHeightTurnActionR = carVendorSettingManager.getFdDyHeightTurnActionR();
            LogUtil.debug("AutoSettingImpl", "getFdDyHeightTurnActionR=" + fdDyHeightTurnActionR);
            return fdDyHeightTurnActionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdDynamicSuspensionModeR() {
        LogUtil.info("AutoSettingImpl", "getFdDynamicSuspensionModeR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858383, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getFdDynamicSuspensionMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFdFixedState() {
        LogUtil.debug("AutoSettingImpl", "getFdFixedState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdFixedStateR = carVendorSettingManager.getFdFixedStateR();
            LogUtil.debug("AutoSettingImpl", "getFdFixedStateR=" + fdFixedStateR);
            return fdFixedStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdFrontBoxTakeState() {
        LogUtil.debug("AutoSettingImpl", "getFdFrontBoxTakeState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdFrontBoxTakeStateR = carVendorSettingManager.getFdFrontBoxTakeStateR();
            LogUtil.debug("AutoSettingImpl", "getFdFrontBoxTakeStateR=" + fdFrontBoxTakeStateR);
            return fdFrontBoxTakeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdLiftModeState() {
        LogUtil.debug("AutoSettingImpl", "getFdLiftModeState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdLiftModeStateR = carVendorSettingManager.getFdLiftModeStateR();
            LogUtil.debug("AutoSettingImpl", "getFdLiftModeStateR=" + fdLiftModeStateR);
            return fdLiftModeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdModeSupportedHturn() {
        LogUtil.debug("AutoSettingImpl", "getFdModeSupportedHturn is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdModeSupportedHturnR = carVendorSettingManager.getFdModeSupportedHturnR();
            LogUtil.debug("AutoSettingImpl", "getFdModeSupportedHturnR=" + fdModeSupportedHturnR);
            return fdModeSupportedHturnR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdSpeedSupportedTurn() {
        LogUtil.debug("AutoSettingImpl", "getFdSpeedSupportedTurn is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdSpeedSupportedTurnR = carVendorSettingManager.getFdSpeedSupportedTurnR();
            LogUtil.debug("AutoSettingImpl", "getFdSpeedSupportedTurnR=" + fdSpeedSupportedTurnR);
            return fdSpeedSupportedTurnR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdTailBoxTakeState() {
        LogUtil.debug("AutoSettingImpl", "getFdTailBoxTakeState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdTailBoxTakeStateR = carVendorSettingManager.getFdTailBoxTakeStateR();
            LogUtil.debug("AutoSettingImpl", "getFdTailBoxTakeStateR=" + fdTailBoxTakeStateR);
            return fdTailBoxTakeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdTailBoxTakeSupported() {
        LogUtil.debug("AutoSettingImpl", "getFdTailBoxTakeSupported is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdTailBoxTakeSupportedR = carVendorSettingManager.getFdTailBoxTakeSupportedR();
            LogUtil.debug("AutoSettingImpl", "getFdTailBoxTakeSupportedR=" + fdTailBoxTakeSupportedR);
            return fdTailBoxTakeSupportedR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdTrailerModeCode() {
        LogUtil.debug("AutoSettingImpl", "getFdTrailerModeCode is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdTrailerModeCodeR = carVendorSettingManager.getFdTrailerModeCodeR();
            LogUtil.debug("AutoSettingImpl", "getFdTrailerModeCodeR=" + fdTrailerModeCodeR);
            return fdTrailerModeCodeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdWelcomeActive() {
        LogUtil.debug("AutoSettingImpl", "getFdWelcomeActive is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdWelcomeActiveR = carVendorSettingManager.getFdWelcomeActiveR();
            LogUtil.debug("AutoSettingImpl", "getFdWelcomeActiveR=" + fdWelcomeActiveR);
            return fdWelcomeActiveR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdWelcomeState() {
        LogUtil.debug("AutoSettingImpl", "getFdWelcomeState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdWelcomeStateR = carVendorSettingManager.getFdWelcomeStateR();
            LogUtil.debug("AutoSettingImpl", "getFdWelcomeStateR=" + fdWelcomeStateR);
            return fdWelcomeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFdWelcomeSupported() {
        LogUtil.debug("AutoSettingImpl", "getFdWelcomeSupported is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fdWelcomeSupportedR = carVendorSettingManager.getFdWelcomeSupportedR();
            LogUtil.debug("AutoSettingImpl", "getFdWelcomeSupportedR=" + fdWelcomeSupportedR);
            return fdWelcomeSupportedR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFixedState() {
        LogUtil.debug("AutoSettingImpl", "getFixedState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int fixedStateR = carVendorSettingManager.getFixedStateR();
            LogUtil.debug("AutoSettingImpl", "getFixedStateR=" + fixedStateR);
            return fixedStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFridgeChildLock() {
        LogUtil.verbose("AutoSettingImpl", " getFridgeChildLock.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858063, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getFridgeChildLock prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFridgeChildLockFlag() {
        int fridgeChildLockFlagR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeChildLockFlag.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeChildLockFlagR = carVendorSettingManager.getFridgeChildLockFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeChildLockFlagR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeChildLockFlag.out=" + fridgeChildLockFlagR);
            return fridgeChildLockFlagR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeChildLockFlag.null == mCarSettingManager");
        fridgeChildLockFlagR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeChildLockFlag.out=" + fridgeChildLockFlagR);
        return fridgeChildLockFlagR;
    }

    public int getFridgeDelayedPowerOutage() {
        LogUtil.verbose("AutoSettingImpl", " getFridgeDelayedPowerOutage.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859203, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getFridgeDelayedPowerOutage prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFridgeDoorState() {
        int fridgeDoorStateR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeDoorState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeDoorStateR = carVendorSettingManager.getFridgeDoorStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeDoorStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeDoorState.out=" + fridgeDoorStateR);
            return fridgeDoorStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeDoorState.null == mCarSettingManager");
        fridgeDoorStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeDoorState.out=" + fridgeDoorStateR);
        return fridgeDoorStateR;
    }

    public int getFridgeExpansionAndContractionFunction() {
        LogUtil.verbose("AutoSettingImpl", " getFridgeExpansionAndContractionFunction.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859197, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getSupportFragrance prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFridgeLocation() {
        int fridgeLocationR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeLocation.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeLocationR = carVendorSettingManager.getFridgeLocationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeLocationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeLocation.out=" + fridgeLocationR);
            return fridgeLocationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeLocation.null == mCarSettingManager");
        fridgeLocationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeLocation.out=" + fridgeLocationR);
        return fridgeLocationR;
    }

    public int getFridgeMinWagePowerGeneration() {
        int fridgeMinWagePowerGenerationR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeMinWagePowerGeneration.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeMinWagePowerGenerationR = carVendorSettingManager.getFridgeMinWagePowerGenerationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeMinWagePowerGenerationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeMinWagePowerGeneration.out=" + fridgeMinWagePowerGenerationR);
            return fridgeMinWagePowerGenerationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeMinWagePowerGeneration.null == mCarSettingManager");
        fridgeMinWagePowerGenerationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeMinWagePowerGeneration.out=" + fridgeMinWagePowerGenerationR);
        return fridgeMinWagePowerGenerationR;
    }

    public int getFridgeNum() {
        int fridgeNumR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeNum.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeNumR = carVendorSettingManager.getFridgeNumR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeNumR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeNum.out=" + fridgeNumR);
            return fridgeNumR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeNum.null == mCarSettingManager");
        fridgeNumR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeNum.out=" + fridgeNumR);
        return fridgeNumR;
    }

    public int getFridgeOnePosition() {
        int fridgeOnePositionR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeOnePosition.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeOnePositionR = carVendorSettingManager.getFridgeOnePositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeOnePositionR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeOnePosition.out=" + fridgeOnePositionR);
            return fridgeOnePositionR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeOnePosition.null == mCarSettingManager");
        fridgeOnePositionR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeOnePosition.out=" + fridgeOnePositionR);
        return fridgeOnePositionR;
    }

    public int getFridgeScreenFlag() {
        int fridgeScreenFlagR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeScreenFlag.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeScreenFlagR = carVendorSettingManager.getFridgeScreenFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeScreenFlagR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeScreenFlag.out=" + fridgeScreenFlagR);
            return fridgeScreenFlagR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeScreenFlag.null == mCarSettingManager");
        fridgeScreenFlagR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeScreenFlag.out=" + fridgeScreenFlagR);
        return fridgeScreenFlagR;
    }

    public int getFridgeTemperatureRegulation() {
        LogUtil.verbose("AutoSettingImpl", " getFridgeTemperatureRegulation.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859196, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getFridgeTemperatureRegulation prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFridgeThreePosition() {
        int fridgeThreePositionR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeThreePosition.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeThreePositionR = carVendorSettingManager.getFridgeThreePositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeThreePositionR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeThreePosition.out=" + fridgeThreePositionR);
            return fridgeThreePositionR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeThreePosition.null == mCarSettingManager");
        fridgeThreePositionR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeThreePosition.out=" + fridgeThreePositionR);
        return fridgeThreePositionR;
    }

    public int getFridgeTwoPosition() {
        int fridgeTwoPositionR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeTwoPosition.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeTwoPositionR = carVendorSettingManager.getFridgeTwoPositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeTwoPositionR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeTwoPosition.out=" + fridgeTwoPositionR);
            return fridgeTwoPositionR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeTwoPosition.null == mCarSettingManager");
        fridgeTwoPositionR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeTwoPosition.out=" + fridgeTwoPositionR);
        return fridgeTwoPositionR;
    }

    public int getFridgeWorkColdTemperature() {
        int fridgeWorkColdTemperatureR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkColdTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeWorkColdTemperatureR = carVendorSettingManager.getFridgeWorkColdTemperatureR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeWorkColdTemperatureR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeWorkColdTemperature.out=" + fridgeWorkColdTemperatureR);
            return fridgeWorkColdTemperatureR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkColdTemperature.null == mCarSettingManager");
        fridgeWorkColdTemperatureR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkColdTemperature.out=" + fridgeWorkColdTemperatureR);
        return fridgeWorkColdTemperatureR;
    }

    public int getFridgeWorkHeatTemperature() {
        int fridgeWorkHeatTemperatureR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkHeatTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeWorkHeatTemperatureR = carVendorSettingManager.getFridgeWorkHeatTemperatureR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeWorkHeatTemperatureR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeWorkHeatTemperature.out=" + fridgeWorkHeatTemperatureR);
            return fridgeWorkHeatTemperatureR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkHeatTemperature.null == mCarSettingManager");
        fridgeWorkHeatTemperatureR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkHeatTemperature.out=" + fridgeWorkHeatTemperatureR);
        return fridgeWorkHeatTemperatureR;
    }

    public int getFridgeWorkSaveTemperature() {
        int fridgeWorkSaveTemperatureR;
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkSaveTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                fridgeWorkSaveTemperatureR = carVendorSettingManager.getFridgeWorkSaveTemperatureR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeWorkSaveTemperatureR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getFridgeWorkSaveTemperature.out=" + fridgeWorkSaveTemperatureR);
            return fridgeWorkSaveTemperatureR;
        }
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkSaveTemperature.null == mCarSettingManager");
        fridgeWorkSaveTemperatureR = -1;
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkSaveTemperature.out=" + fridgeWorkSaveTemperatureR);
        return fridgeWorkSaveTemperatureR;
    }

    public int getFridgeWorkingMode() {
        LogUtil.verbose("AutoSettingImpl", " getFridgeWorkingMode.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859195, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getFridgeWorkingMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFrontFridgeDisinfectFlag() {
        LogUtil.verbose("AutoSettingImpl", " getFrontFridgeDisinfectFlag.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859204, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getFrontFridgeDisinfectFlag prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFrontWiperSpeedValue() {
        LogUtil.info("AutoSettingImpl", "getFrontWiperSpeedValue");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int frontWiperSpeedValueR = carVendorSettingManager.getFrontWiperSpeedValueR();
            LogUtil.debug("AutoSettingImpl", "getFrontWiperSpeedValue=" + frontWiperSpeedValueR);
            return frontWiperSpeedValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getGoalRoadMode() {
        int goalRoadModeR;
        LogUtil.verbose("AutoSettingImpl", " getGoalRoadMode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                goalRoadModeR = carVendorSettingManager.getGoalRoadModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                goalRoadModeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getGoalRoadMode.out=" + goalRoadModeR);
            return goalRoadModeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getGoalRoadMode.null == mCarSettingManager");
        goalRoadModeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getGoalRoadMode.out=" + goalRoadModeR);
        return goalRoadModeR;
    }

    public int getHSpeedAutoCloseState() {
        LogUtil.verbose("AutoSettingImpl", "getHSpeedAutoCloseState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857835, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getHSpeedAutoCloseState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHeadLampConfig() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int headLampConfig = carVendorSettingManager.getHeadLampConfig();
            LogUtil.debug("AutoSettingImpl", "getHeadLampConfig=" + headLampConfig);
            return headLampConfig;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeadlampLevel() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int headlampLevelR = carVendorSettingManager.getHeadlampLevelR();
            LogUtil.debug("AutoSettingImpl", "getHeadlampLevelR=" + headlampLevelR);
            return headlampLevelR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeadrestHorizationDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int headrestHorizationDriver = carVendorSettingManager.getHeadrestHorizationDriver();
            LogUtil.debug("AutoSettingImpl", "getHeadrestHorizationDriver=" + headrestHorizationDriver);
            return headrestHorizationDriver;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeadrestHorizationLeftArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int headrestHorizationBackLeft = carVendorSettingManager.getHeadrestHorizationBackLeft();
            LogUtil.debug("AutoSettingImpl", "getHeadrestHorizationBackLeft=" + headrestHorizationBackLeft);
            return headrestHorizationBackLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeadrestHorizationPassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int headrestHorizationCopilot = carVendorSettingManager.getHeadrestHorizationCopilot();
            LogUtil.debug("AutoSettingImpl", "getHeadrestHorizationCopilot=" + headrestHorizationCopilot);
            return headrestHorizationCopilot;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeadrestHorizationRightArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int headrestHorizationBackRight = carVendorSettingManager.getHeadrestHorizationBackRight();
            LogUtil.debug("AutoSettingImpl", "getHeadrestHorizationBackRight=" + headrestHorizationBackRight);
            return headrestHorizationBackRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getHeadrestTurnHeightDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float headrestTurnHeightDriver = carVendorSettingManager.getHeadrestTurnHeightDriver();
            LogUtil.debug("AutoSettingImpl", "getHeadrestTurnHeightDriver=" + headrestTurnHeightDriver);
            return headrestTurnHeightDriver;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getHeadrestTurnHeightLeftArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float headrestTurnHeightBackLeft = carVendorSettingManager.getHeadrestTurnHeightBackLeft();
            LogUtil.debug("AutoSettingImpl", "getHeadrestTurnHeightBackLeft=" + headrestTurnHeightBackLeft);
            return headrestTurnHeightBackLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getHeadrestTurnHeightPassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float headrestTurnHeightCopilot = carVendorSettingManager.getHeadrestTurnHeightCopilot();
            LogUtil.debug("AutoSettingImpl", "getHeadrestTurnHeightCopilot=" + headrestTurnHeightCopilot);
            return headrestTurnHeightCopilot;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getHeadrestTurnHeightRightArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float headrestTurnHeightBackRight = carVendorSettingManager.getHeadrestTurnHeightBackRight();
            LogUtil.debug("AutoSettingImpl", "getHeadrestTurnHeightBackRight=" + headrestTurnHeightBackRight);
            return headrestTurnHeightBackRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getHevChargingPort() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hevChargingPortR = carVendorSettingManager.getHevChargingPortR();
            LogUtil.debug("AutoSettingImpl", "getHevChargingPortR=" + hevChargingPortR);
            return hevChargingPortR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHsMassageStateDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hsMassageStateDomainR = carVendorSettingManager.getHsMassageStateDomainR();
            LogUtil.debug("AutoSettingImpl", "getHsMassageStateDomainR=" + hsMassageStateDomainR);
            return hsMassageStateDomainR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHsMassageStateLeft() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hsMassageStateBackLeftR = carVendorSettingManager.getHsMassageStateBackLeftR();
            LogUtil.debug("AutoSettingImpl", "getHsMassageStateBackLeftR=" + hsMassageStateBackLeftR);
            return hsMassageStateBackLeftR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHsMassageStatePassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hsMassageStatePassengerR = carVendorSettingManager.getHsMassageStatePassengerR();
            LogUtil.debug("AutoSettingImpl", "getHsMassageStatePassengerR=" + hsMassageStatePassengerR);
            return hsMassageStatePassengerR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHsMassageStateRight() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hsMassageStateBackRightR = carVendorSettingManager.getHsMassageStateBackRightR();
            LogUtil.debug("AutoSettingImpl", "getHsMassageStateBackRightR=" + hsMassageStateBackRightR);
            return hsMassageStateBackRightR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudBrightness() {
        LogUtil.verbose("AutoSettingImpl", "getHudBrightness");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudBrightnessR = carVendorSettingManager.getHudBrightnessR();
            LogUtil.debug("AutoSettingImpl", "getHudBrightnessR=" + hudBrightnessR);
            return hudBrightnessR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudDisplayContentDriver() {
        LogUtil.verbose("AutoSettingImpl", "getHudDisplayContentDriver");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudDisplayContentRDrive = carVendorSettingManager.getHudDisplayContentRDrive();
            LogUtil.debug("AutoSettingImpl", "getHudDisplayContentRDrive=" + hudDisplayContentRDrive);
            return hudDisplayContentRDrive;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudDisplayContentNavigation() {
        LogUtil.verbose("AutoSettingImpl", "getHudDisplayContentNavigation");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudDisplayContentRNavigation = carVendorSettingManager.getHudDisplayContentRNavigation();
            LogUtil.debug("AutoSettingImpl", "getHudDisplayContentRNavigation=" + hudDisplayContentRNavigation);
            return hudDisplayContentRNavigation;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudHeight() {
        LogUtil.verbose("AutoSettingImpl", "getHudHeight");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudHeightR = carVendorSettingManager.getHudHeightR();
            LogUtil.debug("AutoSettingImpl", "getHudHeightR=" + hudHeightR);
            return hudHeightR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudMode() {
        LogUtil.verbose("AutoSettingImpl", "getHudMode");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudModeR = carVendorSettingManager.getHudModeR();
            LogUtil.debug("AutoSettingImpl", "getHudModeR=" + hudModeR);
            return hudModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudModeParams() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857586, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHudNavigationState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudNavigationStateR = carVendorSettingManager.getHudNavigationStateR();
            LogUtil.debug("AutoSettingImpl", "getHudNavigationStateR=" + hudNavigationStateR);
            return hudNavigationStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudPattern() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudPatternR = carVendorSettingManager.getHudPatternR();
            LogUtil.debug("AutoSettingImpl", "getHudPatternR=" + hudPatternR);
            return hudPatternR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudRotationAngle() {
        LogUtil.verbose("AutoSettingImpl", "getHudRotationAngle");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudRotationAngleR = carVendorSettingManager.getHudRotationAngleR();
            LogUtil.debug("AutoSettingImpl", "getHudRotationAngleR=" + hudRotationAngleR);
            return hudRotationAngleR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudSafeDriveState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudSafeDriveStateR = carVendorSettingManager.getHudSafeDriveStateR();
            LogUtil.debug("AutoSettingImpl", "getHudSafeDriveStateR=" + hudSafeDriveStateR);
            return hudSafeDriveStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudScreenProjectionState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int hudScreenProjectionStateR = carVendorSettingManager.getHudScreenProjectionStateR();
            LogUtil.debug("AutoSettingImpl", "getHudScreenProjectionStateR=" + hudScreenProjectionStateR);
            return hudScreenProjectionStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudShowParamsSupport() {
        return 0;
    }

    public int getHudState() {
        LogUtil.verbose("AutoSettingImpl", "getHudState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857571, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getHudState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getIALArea() {
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose("AutoSettingImpl", "getIALArea +in");
            int iALAreaR = this.e.getIALAreaR();
            LogUtil.debug("AutoSettingImpl", "getIALAreaR=" + iALAreaR);
            return iALAreaR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIALBrightnessBack() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iALBrightnessBackR = carVendorSettingManager.getIALBrightnessBackR();
            LogUtil.debug("AutoSettingImpl", "getIALBrightnessBackR=" + iALBrightnessBackR);
            return iALBrightnessBackR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIALBrightnessFront() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iALBrightnessFrontR = carVendorSettingManager.getIALBrightnessFrontR();
            LogUtil.debug("AutoSettingImpl", "getIALBrightnessFrontR=" + iALBrightnessFrontR);
            return iALBrightnessFrontR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIALBrightnessStarRing() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iALBrightnessStarRingR = carVendorSettingManager.getIALBrightnessStarRingR();
            LogUtil.debug("AutoSettingImpl", "getIALBrightnessStarRingR=" + iALBrightnessStarRingR);
            return iALBrightnessStarRingR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIALBrightnessStarrySky() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iALBrightnessStarrySkyR = carVendorSettingManager.getIALBrightnessStarrySkyR();
            LogUtil.debug("AutoSettingImpl", "getIALBrightnessStarrySkyR=" + iALBrightnessStarrySkyR);
            return iALBrightnessStarrySkyR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIALColor(int i) {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iALColorBackR = carVendorSettingManager.getIALColorBackR();
            LogUtil.debug("AutoSettingImpl", "getIALColorBackR=" + iALColorBackR);
            return iALColorBackR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIALColorFront() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iALColorFrontR = carVendorSettingManager.getIALColorFrontR();
            LogUtil.debug("AutoSettingImpl", "getIALColorFrontR=" + iALColorFrontR);
            return iALColorFrontR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIALColorStarRing() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iALColorStarRingR = carVendorSettingManager.getIALColorStarRingR();
            LogUtil.debug("AutoSettingImpl", "getIALColorStarRingR=" + iALColorStarRingR);
            return iALColorStarRingR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIALColorStarrySky() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iALColorStarrySkyR = carVendorSettingManager.getIALColorStarrySkyR();
            LogUtil.debug("AutoSettingImpl", "getIALColorStarrySkyR=" + iALColorStarrySkyR);
            return iALColorStarrySkyR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getINSTheme() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int iNSThemeR = carVendorSettingManager.getINSThemeR();
            LogUtil.debug("AutoSettingImpl", "getINSThemeR=" + iNSThemeR);
            return iNSThemeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInsideLightDoorState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int insideLightDoorStateR = carVendorSettingManager.getInsideLightDoorStateR();
            LogUtil.debug("AutoSettingImpl", "getInsideLightDoorStateR=" + insideLightDoorStateR);
            return insideLightDoorStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelLockOpenDriverDoor() {
        LogUtil.debug("AutoSettingImpl", "getIntelLockOpenDriverDoor is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int intelLockOpenDriverDoorR = carVendorSettingManager.getIntelLockOpenDriverDoorR();
            LogUtil.debug("AutoSettingImpl", "getIntelLockOpenDriverDoorR=" + intelLockOpenDriverDoorR);
            return intelLockOpenDriverDoorR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentAcAreaState() {
        LogUtil.verbose("AutoSettingImpl", "getIntelligentAcAreaState");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int intelligentAcAreaStateS = carVendorSettingManager.getIntelligentAcAreaStateS();
            LogUtil.debug("AutoSettingImpl", "getIntelligentAcAreaStateS=" + intelligentAcAreaStateS);
            return intelligentAcAreaStateS;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentAcAreaSupport() {
        return 0;
    }

    public int getIntelligentLockState() {
        LogUtil.verbose("AutoSettingImpl", "getIntelligentLockState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857569, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getIntelligentLockState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getIntelligentLockSupport() {
        return 0;
    }

    public int getIntelligentUnlockState() {
        LogUtil.verbose("AutoSettingImpl", "getIntelligentUnlockState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857567, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getIntelligentUnlockState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getIntelligentUnlockSupport() {
        return 0;
    }

    public int getIviProfileSettingsR() {
        LogUtil.info("AutoSettingImpl", "getIviProfileSettingsR");
        return 0;
    }

    public int getKeyPowerLowInd() {
        return 0;
    }

    public int getKeyVibratorLevel() {
        LogUtil.debug("AutoSettingImpl", "getKeyVibratorLevel");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857945, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getKeyVibratorLevel prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getKeypadToneState() {
        return 0;
    }

    public int getLandAdvanceAtSate() {
        LogUtil.verbose("AutoSettingImpl", "getLandAdvanceAtSate");
        return 0;
    }

    public int getLandAdvanceAtSupport() {
        return 0;
    }

    public int getLanternLanguageMode() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int lanternLanguageModeR = carVendorSettingManager.getLanternLanguageModeR();
            LogUtil.debug("AutoSettingImpl", "getLanternLanguageModeR=" + lanternLanguageModeR);
            return lanternLanguageModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeaveCarPInd() {
        return 0;
    }

    public int getLeftElectricPedal() {
        int leftElectricPedalR;
        LogUtil.verbose("AutoSettingImpl", " getLeftElectricPedal.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                leftElectricPedalR = carVendorSettingManager.getLeftElectricPedalR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftElectricPedalR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLeftElectricPedal.out=" + leftElectricPedalR);
            return leftElectricPedalR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLeftElectricPedal.null == mCarSettingManager");
        leftElectricPedalR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLeftElectricPedal.out=" + leftElectricPedalR);
        return leftElectricPedalR;
    }

    public int getLeftHeadlampLevel() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int leftHeadlampLevel = carVendorSettingManager.getLeftHeadlampLevel();
            LogUtil.debug("AutoSettingImpl", "getLeftHeadlampLevel=" + leftHeadlampLevel);
            return leftHeadlampLevel;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftHomeLightDelay() {
        LogUtil.verbose("AutoSettingImpl", "getLeftHomeLightDelay");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int leftHomeLightDelayValueR = carVendorSettingManager.getLeftHomeLightDelayValueR();
            LogUtil.debug("AutoSettingImpl", "getLeftHomeLightDelayValueR=" + leftHomeLightDelayValueR);
            return leftHomeLightDelayValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftHomeLightDelayValue() {
        LogUtil.verbose("AutoSettingImpl", "getLeftHomeLightDelayValue");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857624, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getLeftHomeLightDelayValue prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftRearSafebeltStatus() {
        int leftRearSafebeltStatusR;
        LogUtil.verbose("AutoSettingImpl", " getLeftRearSafebeltStatus.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                leftRearSafebeltStatusR = carVendorSettingManager.getLeftRearSafebeltStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftRearSafebeltStatusR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLeftRearSafebeltStatus.out service =" + leftRearSafebeltStatusR);
            return leftRearSafebeltStatusR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLeftRearSafebeltStatus.null");
        leftRearSafebeltStatusR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLeftRearSafebeltStatus.out service =" + leftRearSafebeltStatusR);
        return leftRearSafebeltStatusR;
    }

    public int getLeftRseChildModeSwitchStatusR() {
        LogUtil.info("AutoSettingImpl", "getLeftRseChildModeSwitchStatusR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int leftRseChildModeSwitchStatusR = carVendorSettingManager.getLeftRseChildModeSwitchStatusR();
            LogUtil.debug("AutoSettingImpl", "getLeftRseChildModeSwitchStatusR=" + leftRseChildModeSwitchStatusR);
            return leftRseChildModeSwitchStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftThirdSafebeltStatus() {
        int leftThirdSafebeltStatusR;
        LogUtil.verbose("AutoSettingImpl", " getLeftThirdSafebeltStatus.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                leftThirdSafebeltStatusR = carVendorSettingManager.getLeftThirdSafebeltStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                leftThirdSafebeltStatusR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLeftThirdSafebeltStatus.out service =" + leftThirdSafebeltStatusR);
            return leftThirdSafebeltStatusR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLeftThirdSafebeltStatus.null");
        leftThirdSafebeltStatusR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLeftThirdSafebeltStatus.out service =" + leftThirdSafebeltStatusR);
        return leftThirdSafebeltStatusR;
    }

    public int getLiftModeState() {
        LogUtil.debug("AutoSettingImpl", "getLiftModeState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int liftModeStateR = carVendorSettingManager.getLiftModeStateR();
            LogUtil.debug("AutoSettingImpl", "getLiftModeStateR=" + liftModeStateR);
            return liftModeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLockCarRiseWindow() {
        LogUtil.verbose("AutoSettingImpl", "getLockCarRiseWindow");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857621, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getLockCarRiseWindow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLockCarRiseWindowSupport() {
        return 0;
    }

    public int getLockOff() {
        LogUtil.verbose("AutoSettingImpl", "getLockOff");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857814, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getLockOff prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLockOffSupport() {
        return 0;
    }

    public int getLowBatteryInd() {
        return 0;
    }

    public int getLowOilInd() {
        return 0;
    }

    public int getLrHotStone2MassageConfig() {
        int lrHotStone2MassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getLrHotStone2MassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                lrHotStone2MassageConfigR = carVendorSettingManager.getLrHotStone2MassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrHotStone2MassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLrHotStone2MassageConfig.out service =" + lrHotStone2MassageConfigR);
            return lrHotStone2MassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLrHotStone2MassageConfig.null");
        lrHotStone2MassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLrHotStone2MassageConfig.out service =" + lrHotStone2MassageConfigR);
        return lrHotStone2MassageConfigR;
    }

    public int getLrHotStone3MassageConfig() {
        int lrHotStone3MassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getLrHotStone3MassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                lrHotStone3MassageConfigR = carVendorSettingManager.getLrHotStone3MassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrHotStone3MassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLrHotStone3MassageConfig.out service =" + lrHotStone3MassageConfigR);
            return lrHotStone3MassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLrHotStone3MassageConfig.null");
        lrHotStone3MassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLrHotStone3MassageConfig.out service =" + lrHotStone3MassageConfigR);
        return lrHotStone3MassageConfigR;
    }

    public int getLrMas2ToggleModeConfig() {
        int lrMas2ToggleModeConfigR;
        LogUtil.verbose("AutoSettingImpl", " getLrMas2ToggleModeConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                lrMas2ToggleModeConfigR = carVendorSettingManager.getLrMas2ToggleModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrMas2ToggleModeConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLrMas2ToggleModeConfig.out service =" + lrMas2ToggleModeConfigR);
            return lrMas2ToggleModeConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLrMas2ToggleModeConfig.null");
        lrMas2ToggleModeConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLrMas2ToggleModeConfig.out service =" + lrMas2ToggleModeConfigR);
        return lrMas2ToggleModeConfigR;
    }

    public int getLrMasToggleModeConfig() {
        int lrMasToggleModeConfigR;
        LogUtil.verbose("AutoSettingImpl", " getLrMasToggleModeConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                lrMasToggleModeConfigR = carVendorSettingManager.getLrMasToggleModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrMasToggleModeConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLrMasToggleModeConfig.out service =" + lrMasToggleModeConfigR);
            return lrMasToggleModeConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLrMasToggleModeConfig.null");
        lrMasToggleModeConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLrMasToggleModeConfig.out service =" + lrMasToggleModeConfigR);
        return lrMasToggleModeConfigR;
    }

    public int getLrNormal2MassageConfig() {
        int lrNormal2MassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getLrNormal2MassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                lrNormal2MassageConfigR = carVendorSettingManager.getLrNormal2MassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrNormal2MassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLrNormal2MassageConfig.out service =" + lrNormal2MassageConfigR);
            return lrNormal2MassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLrNormal2MassageConfig.null");
        lrNormal2MassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLrNormal2MassageConfig.out service =" + lrNormal2MassageConfigR);
        return lrNormal2MassageConfigR;
    }

    public int getLrNormalMassageConfig() {
        int lrNormalMassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getLrNormalMassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                lrNormalMassageConfigR = carVendorSettingManager.getLrNormalMassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                lrNormalMassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getLrNormalMassageConfig.out service =" + lrNormalMassageConfigR);
            return lrNormalMassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getLrNormalMassageConfig.null");
        lrNormalMassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getLrNormalMassageConfig.out service =" + lrNormalMassageConfigR);
        return lrNormalMassageConfigR;
    }

    public int getLrseChildModeCodeWriteFbR() {
        LogUtil.info("AutoSettingImpl", "getLrseChildModeCodeWriteFbR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int lrseChildModeCodeWriteFbR = carVendorSettingManager.getLrseChildModeCodeWriteFbR();
            LogUtil.debug("AutoSettingImpl", "getLrseChildModeCodeWriteFbR=" + lrseChildModeCodeWriteFbR);
            return lrseChildModeCodeWriteFbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLrseChildModeTimeWriteFbR() {
        LogUtil.info("AutoSettingImpl", "getLrseChildModeTimeWriteFbR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int lrseChildModeTimeWriteFbR = carVendorSettingManager.getLrseChildModeTimeWriteFbR();
            LogUtil.debug("AutoSettingImpl", "getLrseChildModeTimeWriteFbR=" + lrseChildModeTimeWriteFbR);
            return lrseChildModeTimeWriteFbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLrseMachineSwitchStateR() {
        LogUtil.info("AutoSettingImpl", "getLrseMachineSwitchStateR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int lrseMachineSwitchStateR = carVendorSettingManager.getLrseMachineSwitchStateR();
            LogUtil.debug("AutoSettingImpl", "getLrseMachineSwitchStateR=" + lrseMachineSwitchStateR);
            return lrseMachineSwitchStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMain2HotStoneMassageConfig() {
        int main2HotStoneMassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getMain2HotStoneMassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                main2HotStoneMassageConfigR = carVendorSettingManager.getMain2HotStoneMassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                main2HotStoneMassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getMain2HotStoneMassageConfig.out service =" + main2HotStoneMassageConfigR);
            return main2HotStoneMassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getMain2HotStoneMassageConfig.null");
        main2HotStoneMassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getMain2HotStoneMassageConfig.out service =" + main2HotStoneMassageConfigR);
        return main2HotStoneMassageConfigR;
    }

    public int getMain2SaveOrRecoveFailCode() {
        int recoverOrSaveParamsChanged3;
        LogUtil.debug("AutoSettingImpl", " getMain2SaveOrRecoveFailCode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                recoverOrSaveParamsChanged3 = carVendorSettingManager.getRecoverOrSaveParamsChanged3();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                recoverOrSaveParamsChanged3 = -1;
            }
        } else {
            recoverOrSaveParamsChanged3 = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getMain2SaveOrRecoveFailCode.out = " + recoverOrSaveParamsChanged3);
        return recoverOrSaveParamsChanged3;
    }

    public int getMain2SaveOrRecoverState() {
        int recoverOrSaveParamsChanged5;
        LogUtil.debug("AutoSettingImpl", " getMain2SaveOrRecoverState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                recoverOrSaveParamsChanged5 = carVendorSettingManager.getRecoverOrSaveParamsChanged5();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                recoverOrSaveParamsChanged5 = -1;
            }
        } else {
            recoverOrSaveParamsChanged5 = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getMain2SaveOrRecoverState.out = " + recoverOrSaveParamsChanged5);
        return recoverOrSaveParamsChanged5;
    }

    public int getMainHsMassageState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int mainHsMassageStateR = carVendorSettingManager.getMainHsMassageStateR();
            LogUtil.debug("AutoSettingImpl", "getMainHsMassageStateR=" + mainHsMassageStateR);
            return mainHsMassageStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMainNormalMassageConfig() {
        int mainNormalMassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getMainNormalMassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                mainNormalMassageConfigR = carVendorSettingManager.getMainNormalMassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                mainNormalMassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getMainNormalMassageConfig.out service =" + mainNormalMassageConfigR);
            return mainNormalMassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getMainNormalMassageConfig.null");
        mainNormalMassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getMainNormalMassageConfig.out service =" + mainNormalMassageConfigR);
        return mainNormalMassageConfigR;
    }

    public int getMainSaveOrRecoveFailCode() {
        int i;
        LogUtil.debug("AutoSettingImpl", " getMainSaveOrRecoveFailCode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                i = carVendorSettingManager.getonSaveSeatParamsAllChangedPlace5();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
        } else {
            i = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getMainSaveOrRecoveFailCode.out = " + i);
        return i;
    }

    public int getMainSaveOrRecoverState() {
        int i;
        LogUtil.debug("AutoSettingImpl", " getMainSaveOrRecoverState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                i = carVendorSettingManager.getonSaveSeatParamsAllChangedPlace7();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
        } else {
            i = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getMainSaveOrRecoverState.out = " + i);
        return i;
    }

    public int getMainSeatBackInit() {
        int mainSeatBackInitR;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatBackInit.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                mainSeatBackInitR = carVendorSettingManager.getMainSeatBackInitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                mainSeatBackInitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getMainSeatBackInit.out service =" + mainSeatBackInitR);
            return mainSeatBackInitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getMainSeatBackInit.null");
        mainSeatBackInitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatBackInit.out service =" + mainSeatBackInitR);
        return mainSeatBackInitR;
    }

    public int getMainSeatBackInited() {
        int mainSeatBackInitedR;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatBackInited.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                mainSeatBackInitedR = carVendorSettingManager.getMainSeatBackInitedR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                mainSeatBackInitedR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getMainSeatBackInited.out service =" + mainSeatBackInitedR);
            return mainSeatBackInitedR;
        }
        LogUtil.verbose("AutoSettingImpl", " getMainSeatBackInited.null");
        mainSeatBackInitedR = -1;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatBackInited.out service =" + mainSeatBackInitedR);
        return mainSeatBackInitedR;
    }

    public int getMainSeatHightInited() {
        int mainSeatHightInitedR;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHightInited.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                mainSeatHightInitedR = carVendorSettingManager.getMainSeatHightInitedR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                mainSeatHightInitedR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getMainSeatHightInited.out service =" + mainSeatHightInitedR);
            return mainSeatHightInitedR;
        }
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHightInited.null");
        mainSeatHightInitedR = -1;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHightInited.out service =" + mainSeatHightInitedR);
        return mainSeatHightInitedR;
    }

    public int getMainSeatHorizontalInit() {
        int mainSeatHorizontalInitR;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHorizontalInit.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                mainSeatHorizontalInitR = carVendorSettingManager.getMainSeatHorizontalInitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                mainSeatHorizontalInitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getMainSeatHorizontalInit.out service =" + mainSeatHorizontalInitR);
            return mainSeatHorizontalInitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHorizontalInit.null");
        mainSeatHorizontalInitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHorizontalInit.out service =" + mainSeatHorizontalInitR);
        return mainSeatHorizontalInitR;
    }

    public int getMainSeatHorizontalInited() {
        int mainSeatHorizontalInitedR;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHorizontalInited.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                mainSeatHorizontalInitedR = carVendorSettingManager.getMainSeatHorizontalInitedR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                mainSeatHorizontalInitedR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getMainSeatHorizontalInited.out service =" + mainSeatHorizontalInitedR);
            return mainSeatHorizontalInitedR;
        }
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHorizontalInited.null");
        mainSeatHorizontalInitedR = -1;
        LogUtil.verbose("AutoSettingImpl", " getMainSeatHorizontalInited.out service =" + mainSeatHorizontalInitedR);
        return mainSeatHorizontalInitedR;
    }

    public int getMaintainRemindState() {
        LogUtil.verbose("AutoSettingImpl", "getMaintainRemindState");
        return 0;
    }

    public int getMassageLevelDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int massageLevelLeftFront = carVendorSettingManager.getMassageLevelLeftFront();
            LogUtil.debug("AutoSettingImpl", "getMassageLevelLeftFront=" + massageLevelLeftFront);
            return massageLevelLeftFront;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMassageLevelLeftRear() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int massageLevelLeftBack = carVendorSettingManager.getMassageLevelLeftBack();
            LogUtil.debug("AutoSettingImpl", "getMassageLevelLeftBack=" + massageLevelLeftBack);
            return massageLevelLeftBack;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMassageLevelPassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int massageLevelRightFront = carVendorSettingManager.getMassageLevelRightFront();
            LogUtil.debug("AutoSettingImpl", "getMassageLevelRightFront=" + massageLevelRightFront);
            return massageLevelRightFront;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMassageLevelRightRear() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int massageLevelRightBack = carVendorSettingManager.getMassageLevelRightBack();
            LogUtil.debug("AutoSettingImpl", "getMassageLevelRightBack=" + massageLevelRightBack);
            return massageLevelRightBack;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMassageModeDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int massageModeR = carVendorSettingManager.getMassageModeR();
            LogUtil.debug("AutoSettingImpl", "getMassageModeR=" + massageModeR);
            return massageModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMassageModeLeftRear() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int massageModeR4FALeftBack = carVendorSettingManager.getMassageModeR4FALeftBack();
            LogUtil.debug("AutoSettingImpl", "getMassageModeR4FALeftBack=" + massageModeR4FALeftBack);
            return massageModeR4FALeftBack;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMassageModePassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int massageModeR4FA = carVendorSettingManager.getMassageModeR4FA();
            LogUtil.debug("AutoSettingImpl", "getMassageModeR4FA=" + massageModeR4FA);
            return massageModeR4FA;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMassageModeRightRear() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int massageModeR4FARightBack = carVendorSettingManager.getMassageModeR4FARightBack();
            LogUtil.debug("AutoSettingImpl", "getMassageModeR4FARightBack=" + massageModeR4FARightBack);
            return massageModeR4FARightBack;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMemorySetOperationFlag() {
        LogUtil.info("AutoSettingImpl", "getMemorySetOperationFlag");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int memorySetOperationFlagR = carVendorSettingManager.getMemorySetOperationFlagR();
            LogUtil.debug("AutoSettingImpl", "getMemorySetOperationFlag=" + memorySetOperationFlagR);
            return memorySetOperationFlagR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMeterExportArea() {
        int meterExportAreaR;
        LogUtil.verbose("AutoSettingImpl", " getMeterExportArea.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                meterExportAreaR = carVendorSettingManager.getMeterExportAreaR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                meterExportAreaR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getMeterExportArea.out service =" + meterExportAreaR);
            return meterExportAreaR;
        }
        LogUtil.verbose("AutoSettingImpl", " getMeterExportArea.null");
        meterExportAreaR = -1;
        LogUtil.verbose("AutoSettingImpl", " getMeterExportArea.out service =" + meterExportAreaR);
        return meterExportAreaR;
    }

    public int getMicroSwitchLockWindowState() {
        LogUtil.verbose("AutoSettingImpl", "getMicroSwitchLockWindowState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857617, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getMicroSwitchLockWindowState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMicroSwitchLockWindowSupport() {
        return 0;
    }

    public int getMicroSwitchUnlockWindowState() {
        LogUtil.verbose("AutoSettingImpl", "getMicroSwitchUnlockWindowState");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int microSwitchUnlockWindowStateR = carVendorSettingManager.getMicroSwitchUnlockWindowStateR();
            LogUtil.debug("AutoSettingImpl", "getMicroSwitchUnlockWindowStateR=" + microSwitchUnlockWindowStateR);
            return microSwitchUnlockWindowStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMicroSwitchUnlockWindowSupport() {
        return 0;
    }

    public int getMissKeyInd() {
        return 0;
    }

    public int getMissPackageCount() {
        int missPackageCountR;
        LogUtil.debug("AutoSettingImpl", " getMissPackageCount.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                missPackageCountR = carVendorSettingManager.getMissPackageCountR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                missPackageCountR = -1;
            }
        } else {
            missPackageCountR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getMissPackageCount.out = " + missPackageCountR);
        return missPackageCountR;
    }

    public int getModeSupportedHturn() {
        LogUtil.debug("AutoSettingImpl", "getModeSupportedHturn is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int modeSupportedHturnR = carVendorSettingManager.getModeSupportedHturnR();
            LogUtil.debug("AutoSettingImpl", "getModeSupportedHturnR=" + modeSupportedHturnR);
            return modeSupportedHturnR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getModelSupport(int i) {
        return 0;
    }

    public int getMoisAssembleStateR() {
        LogUtil.info("AutoSettingImpl", "getMoisAssembleStateR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int moisAssembleStateR = carVendorSettingManager.getMoisAssembleStateR();
            LogUtil.debug("AutoSettingImpl", "getMoisAssembleStateR=" + moisAssembleStateR);
            return moisAssembleStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMoisFuncStatusR() {
        LogUtil.info("AutoSettingImpl", "getMoisFuncStatusR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int moisFuncStatusR = carVendorSettingManager.getMoisFuncStatusR();
            LogUtil.debug("AutoSettingImpl", "getMoisFuncStatusR=" + moisFuncStatusR);
            return moisFuncStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMotorPretensionerSeatbeltState() {
        LogUtil.verbose("AutoSettingImpl", "getMotorPretensionerSeatbeltState");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int motorPretensionerSeatbeltStateR = carVendorSettingManager.getMotorPretensionerSeatbeltStateR();
            LogUtil.debug("AutoSettingImpl", "getMotorPretensionerSeatbeltStateR=" + motorPretensionerSeatbeltStateR);
            return motorPretensionerSeatbeltStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMotorPretensionerSeatbeltSupport() {
        return 0;
    }

    public int getNavigationMediaState() {
        LogUtil.verbose("AutoSettingImpl", "getNavigationMediaState");
        return 0;
    }

    public int getNavigationMediaSupport() {
        return 0;
    }

    public int getNfcOpenDriverDoor() {
        LogUtil.debug("AutoSettingImpl", "getNfcOpenDriverDoor is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int nfcOpenDriverDoorR = carVendorSettingManager.getNfcOpenDriverDoorR();
            LogUtil.debug("AutoSettingImpl", "getNfcOpenDriverDoorR=" + nfcOpenDriverDoorR);
            return nfcOpenDriverDoorR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getNightDRLightOnInd() {
        return 0;
    }

    public int getOffReminder() {
        return 0;
    }

    public int getOneKeyEnterPitConfig() {
        int oneKeyEnterPitConfigR;
        LogUtil.verbose("AutoSettingImpl", " getOneKeyEnterPitConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                oneKeyEnterPitConfigR = carVendorSettingManager.getOneKeyEnterPitConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                oneKeyEnterPitConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getOneKeyEnterPitConfig.out service =" + oneKeyEnterPitConfigR);
            return oneKeyEnterPitConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getOneKeyEnterPitConfig.null");
        oneKeyEnterPitConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getOneKeyEnterPitConfig.out service =" + oneKeyEnterPitConfigR);
        return oneKeyEnterPitConfigR;
    }

    public int getOverspeedLock() {
        LogUtil.verbose("AutoSettingImpl", "getOverspeedLock");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857799, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getOverspeedLock prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOverspeedLockSupport() {
        return 0;
    }

    public int getPM25Power() {
        LogUtil.verbose("AutoSettingImpl", "getPM25Power");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int pM25PowerR = carVendorSettingManager.getPM25PowerR();
            LogUtil.debug("AutoSettingImpl", "getPM25PowerR=" + pM25PowerR);
            return pM25PowerR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPM25PowerSupport() {
        return 0;
    }

    public int getPM25SwitchCheck() {
        LogUtil.verbose("AutoSettingImpl", "getPM25SwitchCheck");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int pM25SwitchCheckR = carVendorSettingManager.getPM25SwitchCheckR();
            LogUtil.debug("AutoSettingImpl", "getPM25SwitchCheckR=" + pM25SwitchCheckR);
            return pM25SwitchCheckR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPM25SwitchCheckSupport() {
        return 0;
    }

    public int getPM25TimeCheck() {
        LogUtil.verbose("AutoSettingImpl", "getPM25TimeCheck");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int pM25TimeCheckR = carVendorSettingManager.getPM25TimeCheckR();
            LogUtil.debug("AutoSettingImpl", "getPM25TimeCheckR=" + pM25TimeCheckR);
            return pM25TimeCheckR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPM25TimeCkeckSupport() {
        return 0;
    }

    public int getPabCode() {
        int pabCodeR;
        LogUtil.debug("AutoSettingImpl", " getPabCode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                pabCodeR = carVendorSettingManager.getPabCodeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                pabCodeR = -1;
            }
        } else {
            pabCodeR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getPabCode.out = " + pabCodeR);
        return pabCodeR;
    }

    public int getPabState() {
        int pabStateR;
        LogUtil.debug("AutoSettingImpl", " getPabState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                pabStateR = carVendorSettingManager.getPabStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                pabStateR = -1;
            }
        } else {
            pabStateR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getPabState.out = " + pabStateR);
        return pabStateR;
    }

    public int getParkBrakeInd() {
        return 0;
    }

    public int getPas2HotStoneMassageConfig() {
        int pas2HotStoneMassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getPas2HotStoneMassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                pas2HotStoneMassageConfigR = carVendorSettingManager.getPas2HotStoneMassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                pas2HotStoneMassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPas2HotStoneMassageConfig.out service =" + pas2HotStoneMassageConfigR);
            return pas2HotStoneMassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPas2HotStoneMassageConfig.null");
        pas2HotStoneMassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPas2HotStoneMassageConfig.out service =" + pas2HotStoneMassageConfigR);
        return pas2HotStoneMassageConfigR;
    }

    public int getPasNormalMassageConfig() {
        int pasNormalMassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getPasNormalMassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                pasNormalMassageConfigR = carVendorSettingManager.getPasNormalMassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                pasNormalMassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPasNormalMassageConfig.out service =" + pasNormalMassageConfigR);
            return pasNormalMassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPasNormalMassageConfig.null");
        pasNormalMassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPasNormalMassageConfig.out service =" + pasNormalMassageConfigR);
        return pasNormalMassageConfigR;
    }

    public int getPassangerSaveOrRecoverState() {
        int i;
        LogUtil.debug("AutoSettingImpl", " getPassangerSaveOrRecoverState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                i = carVendorSettingManager.getonSaveSeatParamsAllChanged3CA();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
        } else {
            i = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getPassangerSaveOrRecoverState.out = " + i);
        return i;
    }

    public int getPassangerSeatBackInit() {
        int passangerSeatBackInitR;
        LogUtil.verbose("AutoSettingImpl", " getPassangerSeatBackInit.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                passangerSeatBackInitR = carVendorSettingManager.getPassangerSeatBackInitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passangerSeatBackInitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPassangerSeatBackInit.out service =" + passangerSeatBackInitR);
            return passangerSeatBackInitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPassangerSeatBackInit.null");
        passangerSeatBackInitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPassangerSeatBackInit.out service =" + passangerSeatBackInitR);
        return passangerSeatBackInitR;
    }

    public int getPassangerSeatHorizontalInit() {
        int passangerSeatHorizontalInitR;
        LogUtil.verbose("AutoSettingImpl", " getPassangerSeatHorizontalInit.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                passangerSeatHorizontalInitR = carVendorSettingManager.getPassangerSeatHorizontalInitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passangerSeatHorizontalInitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPassangerSeatHorizontalInit.out service =" + passangerSeatHorizontalInitR);
            return passangerSeatHorizontalInitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPassangerSeatHorizontalInit.null");
        passangerSeatHorizontalInitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPassangerSeatHorizontalInit.out service =" + passangerSeatHorizontalInitR);
        return passangerSeatHorizontalInitR;
    }

    public int getPassengerMasToggleModeConfig() {
        int passengerMasToggleModeConfigR;
        LogUtil.verbose("AutoSettingImpl", " getPassengerMasToggleModeConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                passengerMasToggleModeConfigR = carVendorSettingManager.getPassengerMasToggleModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerMasToggleModeConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPassengerMasToggleModeConfig.out service =" + passengerMasToggleModeConfigR);
            return passengerMasToggleModeConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPassengerMasToggleModeConfig.null");
        passengerMasToggleModeConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPassengerMasToggleModeConfig.out service =" + passengerMasToggleModeConfigR);
        return passengerMasToggleModeConfigR;
    }

    public int getPassengerSeatHeating3caState() {
        int passengerSeatHeating3caStateR;
        LogUtil.verbose("AutoSettingImpl", " getPassengerSeatHeating3caState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                passengerSeatHeating3caStateR = carVendorSettingManager.getPassengerSeatHeating3caStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerSeatHeating3caStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPassengerSeatHeating3caState.out=" + passengerSeatHeating3caStateR);
            return passengerSeatHeating3caStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPassengerSeatHeating3caState.null");
        passengerSeatHeating3caStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPassengerSeatHeating3caState.out=" + passengerSeatHeating3caStateR);
        return passengerSeatHeating3caStateR;
    }

    public int getPassengerSeatVentilating3caState() {
        int passengerSeatVentilating3caStateR;
        LogUtil.verbose("AutoSettingImpl", " getPassengerSeatVentilating3caState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                passengerSeatVentilating3caStateR = carVendorSettingManager.getPassengerSeatVentilating3caStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                passengerSeatVentilating3caStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPassengerSeatVentilating3caState.out=" + passengerSeatVentilating3caStateR);
            return passengerSeatVentilating3caStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPassengerSeatVentilating3caState.null");
        passengerSeatVentilating3caStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPassengerSeatVentilating3caState.out=" + passengerSeatVentilating3caStateR);
        return passengerSeatVentilating3caStateR;
    }

    public int getPassengerStateBackLeft() {
        LogUtil.verbose("AutoSettingImpl", "getPassengerStateBackLeft");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int passengerRearLeftStateR = carVendorSettingManager.getPassengerRearLeftStateR();
            LogUtil.debug("AutoSettingImpl", "getPassengerRearLeftStateR=" + passengerRearLeftStateR);
            return passengerRearLeftStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerStateBackMid() {
        LogUtil.verbose("AutoSettingImpl", "getPassengerStateBackMid");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int passengerRearMiddleStateR = carVendorSettingManager.getPassengerRearMiddleStateR();
            LogUtil.debug("AutoSettingImpl", "getPassengerRearMiddleStateR=" + passengerRearMiddleStateR);
            return passengerRearMiddleStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerStateBackRight() {
        LogUtil.verbose("AutoSettingImpl", "getPassengerStateBackRight");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int passengerRearRightStateR = carVendorSettingManager.getPassengerRearRightStateR();
            LogUtil.debug("AutoSettingImpl", "getPassengerRearRightStateR=" + passengerRearRightStateR);
            return passengerRearRightStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerStateFinalBackLeft() {
        LogUtil.verbose("AutoSettingImpl", "getPassengerStateFinalBackLeft");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int passengerLastLeftStateR = carVendorSettingManager.getPassengerLastLeftStateR();
            LogUtil.debug("AutoSettingImpl", "getPassengerLastLeftStateR=" + passengerLastLeftStateR);
            return passengerLastLeftStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerStateFinalBackMid() {
        LogUtil.verbose("AutoSettingImpl", "getPassengerStateFinalBackMid");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int passengerLastMiddleStateR = carVendorSettingManager.getPassengerLastMiddleStateR();
            LogUtil.debug("AutoSettingImpl", "getPassengerLastMiddleStateR=" + passengerLastMiddleStateR);
            return passengerLastMiddleStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerStateFinalBackRight() {
        LogUtil.verbose("AutoSettingImpl", "getPassengerStateFinalBackRight");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int passengerLastRightStateR = carVendorSettingManager.getPassengerLastRightStateR();
            LogUtil.debug("AutoSettingImpl", "getPassengerLastRightStateR=" + passengerLastRightStateR);
            return passengerLastRightStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerStatePassenger() {
        LogUtil.verbose("AutoSettingImpl", "getPassengerStatePassenger");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int passengerStateR = carVendorSettingManager.getPassengerStateR();
            LogUtil.debug("AutoSettingImpl", "getPassengerStateR=" + passengerStateR);
            return passengerStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPedalSuppressionSystemState() {
        int pedalSuppressionSystemStateR;
        LogUtil.verbose("AutoSettingImpl", " getPedalSuppressionSystemState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                pedalSuppressionSystemStateR = carVendorSettingManager.getPedalSuppressionSystemStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                pedalSuppressionSystemStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPedalSuppressionSystemState.out=" + pedalSuppressionSystemStateR);
            return pedalSuppressionSystemStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPedalSuppressionSystemState.null == mCarSettingManager");
        pedalSuppressionSystemStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPedalSuppressionSystemState.out=" + pedalSuppressionSystemStateR);
        return pedalSuppressionSystemStateR;
    }

    public int getPhoneRemindCode(int i) {
        int phoneRemindCodeR;
        LogUtil.verbose("AutoSettingImpl", " getPhoneRemindCode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                phoneRemindCodeR = carVendorSettingManager.getPhoneRemindCodeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                phoneRemindCodeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPhoneRemindCode.out=" + phoneRemindCodeR);
            return phoneRemindCodeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPhoneRemindCode.null == mCarSettingManager");
        phoneRemindCodeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPhoneRemindCode.out=" + phoneRemindCodeR);
        return phoneRemindCodeR;
    }

    public int getPhysicalkeyConfig() {
        int physicalkeyConfigR;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeyConfig.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                physicalkeyConfigR = carVendorSettingManager.getPhysicalkeyConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                physicalkeyConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPhysicalkeyConfig.out=" + physicalkeyConfigR);
            return physicalkeyConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeyConfig.null == mCarSettingManager");
        physicalkeyConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeyConfig.out=" + physicalkeyConfigR);
        return physicalkeyConfigR;
    }

    public int getPhysicalkeySecuConfig() {
        int physicalkeySecuConfigR;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecuConfig.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                physicalkeySecuConfigR = carVendorSettingManager.getPhysicalkeySecuConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                physicalkeySecuConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecuConfig.out=" + physicalkeySecuConfigR);
            return physicalkeySecuConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecuConfig.null == mCarSettingManager");
        physicalkeySecuConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecuConfig.out=" + physicalkeySecuConfigR);
        return physicalkeySecuConfigR;
    }

    public int getPhysicalkeySecupConfig() {
        int physicalkeySecupConfigR;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecupConfig.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                physicalkeySecupConfigR = carVendorSettingManager.getPhysicalkeySecupConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                physicalkeySecupConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecupConfig.out=" + physicalkeySecupConfigR);
            return physicalkeySecupConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecupConfig.null == mCarSettingManager");
        physicalkeySecupConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecupConfig.out=" + physicalkeySecupConfigR);
        return physicalkeySecupConfigR;
    }

    public int getPhysicalkeySecurlConfig() {
        int physicalkeySecurlConfigR;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecurlConfig.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                physicalkeySecurlConfigR = carVendorSettingManager.getPhysicalkeySecurlConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                physicalkeySecurlConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecurlConfig.out=" + physicalkeySecurlConfigR);
            return physicalkeySecurlConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecurlConfig.null == mCarSettingManager");
        physicalkeySecurlConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecurlConfig.out=" + physicalkeySecurlConfigR);
        return physicalkeySecurlConfigR;
    }

    public int getPhysicalkeySecurrConfig() {
        int physicalkeySecurrConfigR;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecurrConfig.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                physicalkeySecurrConfigR = carVendorSettingManager.getPhysicalkeySecurrConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                physicalkeySecurrConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecurrConfig.out=" + physicalkeySecurrConfigR);
            return physicalkeySecurrConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecurrConfig.null == mCarSettingManager");
        physicalkeySecurrConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getPhysicalkeySecurrConfig.out=" + physicalkeySecurrConfigR);
        return physicalkeySecurrConfigR;
    }

    public int getRainAutoLockWindowState() {
        LogUtil.verbose("AutoSettingImpl", "getRainAutoLockWindowState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857563, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRainAutoLockWindowState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRainAutoLockWindowSupport() {
        return 0;
    }

    public int getRearAcOnlineState() {
        LogUtil.verbose("AutoSettingImpl", "getRearAcOnlineState");
        return 0;
    }

    public int getRearAcPanelAutoLockTime() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856073, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRearAcPanelAutoLockTime prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearDoorState() {
        int rearDoorStateR;
        LogUtil.verbose("AutoSettingImpl", " getRearDoorState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearDoorStateR = carVendorSettingManager.getRearDoorStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearDoorStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearDoorState.out=" + rearDoorStateR);
            return rearDoorStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearDoorState.null == mCarSettingManager");
        rearDoorStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearDoorState.out=" + rearDoorStateR);
        return rearDoorStateR;
    }

    public int getRearFridgeDisinfectFlag() {
        int rearFridgeDisinfectFlagR;
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeDisinfectFlag.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearFridgeDisinfectFlagR = carVendorSettingManager.getRearFridgeDisinfectFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearFridgeDisinfectFlagR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearFridgeDisinfectFlag.out=" + rearFridgeDisinfectFlagR);
            return rearFridgeDisinfectFlagR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeDisinfectFlag.null == mCarSettingManager");
        rearFridgeDisinfectFlagR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeDisinfectFlag.out=" + rearFridgeDisinfectFlagR);
        return rearFridgeDisinfectFlagR;
    }

    public int getRearFridgeKeyTypeFlag() {
        int rearFridgeKeyTypeFlagR;
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeKeyTypeFlag.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearFridgeKeyTypeFlagR = carVendorSettingManager.getRearFridgeKeyTypeFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearFridgeKeyTypeFlagR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearFridgeKeyTypeFlag.out=" + rearFridgeKeyTypeFlagR);
            return rearFridgeKeyTypeFlagR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeKeyTypeFlag.null == mCarSettingManager");
        rearFridgeKeyTypeFlagR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeKeyTypeFlag.out=" + rearFridgeKeyTypeFlagR);
        return rearFridgeKeyTypeFlagR;
    }

    public int getRearFridgeWorkTypeFlag() {
        int rearFridgeWorkTypeFlagR;
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeWorkTypeFlag.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearFridgeWorkTypeFlagR = carVendorSettingManager.getRearFridgeWorkTypeFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearFridgeWorkTypeFlagR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearFridgeWorkTypeFlag.out=" + rearFridgeWorkTypeFlagR);
            return rearFridgeWorkTypeFlagR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeWorkTypeFlag.null == mCarSettingManager");
        rearFridgeWorkTypeFlagR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearFridgeWorkTypeFlag.out=" + rearFridgeWorkTypeFlagR);
        return rearFridgeWorkTypeFlagR;
    }

    public int getRearHeatTemp() {
        int rearHeatTempR;
        LogUtil.verbose("AutoSettingImpl", " getRearHeatTemp.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearHeatTempR = carVendorSettingManager.getRearHeatTempR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearHeatTempR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearHeatTemp.out=" + rearHeatTempR);
            return rearHeatTempR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearHeatTemp.null == mCarSettingManager");
        rearHeatTempR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearHeatTemp.out=" + rearHeatTempR);
        return rearHeatTempR;
    }

    public int getRearLeftEcuSaveOrRecoverState() {
        int i;
        LogUtil.debug("AutoSettingImpl", " getRearLeftEcuSaveOrRecoverState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                i = carVendorSettingManager.getonSaveSeatParamsAllChanged402();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
        } else {
            i = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getRearLeftEcuSaveOrRecoverState.out = " + i);
        return i;
    }

    public int getRearLeftMassageSwConfiguration() {
        int rearLeftMassageSwConfigurationR;
        LogUtil.verbose("AutoSettingImpl", " getRearLeftMassageSwConfiguration.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearLeftMassageSwConfigurationR = carVendorSettingManager.getRearLeftMassageSwConfigurationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearLeftMassageSwConfigurationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearLeftMassageSwConfiguration.out=" + rearLeftMassageSwConfigurationR);
            return rearLeftMassageSwConfigurationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearLeftMassageSwConfiguration.null == mCarSettingManager");
        rearLeftMassageSwConfigurationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearLeftMassageSwConfiguration.out=" + rearLeftMassageSwConfigurationR);
        return rearLeftMassageSwConfigurationR;
    }

    public int getRearLeftSaveOrRecoverState() {
        int recoverOrSaveParamsChanged6;
        LogUtil.debug("AutoSettingImpl", " getRearLeftSaveOrRecoverState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                recoverOrSaveParamsChanged6 = carVendorSettingManager.getRecoverOrSaveParamsChanged6();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                recoverOrSaveParamsChanged6 = -1;
            }
        } else {
            recoverOrSaveParamsChanged6 = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getRearLeftSaveOrRecoverState.out = " + recoverOrSaveParamsChanged6);
        return recoverOrSaveParamsChanged6;
    }

    public int getRearPanelLockTimeSupport() {
        return 0;
    }

    public int getRearRightEcuSaveOrRecoverState() {
        int i;
        LogUtil.debug("AutoSettingImpl", " getRearRightEcuSaveOrRecoverState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                i = carVendorSettingManager.getonSaveSeatParamsAllChanged42F();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
        } else {
            i = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getRearRightEcuSaveOrRecoverState.out = " + i);
        return i;
    }

    public int getRearRightFridgeHeatingScheme() {
        int rearRightFridgeHeatingSchemeR;
        LogUtil.verbose("AutoSettingImpl", " getRearRightFridgeHeatingScheme.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearRightFridgeHeatingSchemeR = carVendorSettingManager.getRearRightFridgeHeatingSchemeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearRightFridgeHeatingSchemeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearRightFridgeHeatingScheme.out=" + rearRightFridgeHeatingSchemeR);
            return rearRightFridgeHeatingSchemeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearRightFridgeHeatingScheme.null == mCarSettingManager");
        rearRightFridgeHeatingSchemeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearRightFridgeHeatingScheme.out=" + rearRightFridgeHeatingSchemeR);
        return rearRightFridgeHeatingSchemeR;
    }

    public int getRearRightMassageSwConfiguration() {
        int rearRightMassageSwConfigurationR;
        LogUtil.verbose("AutoSettingImpl", " getRearRightMassageSwConfiguration.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearRightMassageSwConfigurationR = carVendorSettingManager.getRearRightMassageSwConfigurationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearRightMassageSwConfigurationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearRightMassageSwConfiguration.out=" + rearRightMassageSwConfigurationR);
            return rearRightMassageSwConfigurationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearRightMassageSwConfiguration.null == mCarSettingManager");
        rearRightMassageSwConfigurationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearRightMassageSwConfiguration.out=" + rearRightMassageSwConfigurationR);
        return rearRightMassageSwConfigurationR;
    }

    public int getRearRightSaveOrRecoverState() {
        int recoverOrSaveParamsChanged7;
        LogUtil.debug("AutoSettingImpl", " getRearRightSaveOrRecoverState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                recoverOrSaveParamsChanged7 = carVendorSettingManager.getRecoverOrSaveParamsChanged7();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                recoverOrSaveParamsChanged7 = -1;
            }
        } else {
            recoverOrSaveParamsChanged7 = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getRearRightSaveOrRecoverState.out = " + recoverOrSaveParamsChanged7);
        return recoverOrSaveParamsChanged7;
    }

    public int getRearRightSeatGreetState() {
        int rearRightSeatGreetStateR;
        LogUtil.verbose("AutoSettingImpl", " getRearRightSeatGreetState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearRightSeatGreetStateR = carVendorSettingManager.getRearRightSeatGreetStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearRightSeatGreetStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearRightSeatGreetState.out=" + rearRightSeatGreetStateR);
            return rearRightSeatGreetStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearRightSeatGreetState.null == mCarSettingManager");
        rearRightSeatGreetStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearRightSeatGreetState.out=" + rearRightSeatGreetStateR);
        return rearRightSeatGreetStateR;
    }

    public int getRearSaveTemp() {
        int rearSaveTempR;
        LogUtil.verbose("AutoSettingImpl", " getRearSaveTemp.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearSaveTempR = carVendorSettingManager.getRearSaveTempR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearSaveTempR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearSaveTemp.out=" + rearSaveTempR);
            return rearSaveTempR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearSaveTemp.null == mCarSettingManager");
        rearSaveTempR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearSaveTemp.out=" + rearSaveTempR);
        return rearSaveTempR;
    }

    public int getRearSeatGreetState() {
        LogUtil.debug("AutoSettingImpl", "getRearSeatGreetState is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858376, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRearSeatGreetState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearViewEcuMirrorFlipState() {
        LogUtil.debug("AutoSettingImpl", "getRearViewEcuMirrorFlipState is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858360, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRearViewEcuMirrorFlipState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearViewMirrorFlip() {
        LogUtil.verbose("AutoSettingImpl", "getRearViewMirrorFlip");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857591, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRearViewMirrorFlip prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearViewMirrorFlipSet() {
        LogUtil.verbose("AutoSettingImpl", "getRearViewMirrorFlipSet");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857592, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRearViewMirrorFlipSet prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearViewMirrorFlipSupport() {
        return 0;
    }

    public int getRearWiperSpeedValue() {
        int rearWiperSpeedValueR;
        LogUtil.info("AutoSettingImpl", " getRearWiperSpeedValue.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearWiperSpeedValueR = carVendorSettingManager.getRearWiperSpeedValueR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearWiperSpeedValueR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearWiperSpeedValue.out=" + rearWiperSpeedValueR);
            return rearWiperSpeedValueR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearWiperSpeedValue.null == mCarSettingManager");
        rearWiperSpeedValueR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearWiperSpeedValue.out=" + rearWiperSpeedValueR);
        return rearWiperSpeedValueR;
    }

    public int getRearviewMirrorIsAutoLightState() {
        int rearviewMirrorIsAutoLightStateR;
        LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsAutoLightState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearviewMirrorIsAutoLightStateR = carVendorSettingManager.getRearviewMirrorIsAutoLightStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearviewMirrorIsAutoLightStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsAutoLightState.out=" + rearviewMirrorIsAutoLightStateR);
            return rearviewMirrorIsAutoLightStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsAutoLightState.null == mCarSettingManager");
        rearviewMirrorIsAutoLightStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsAutoLightState.out=" + rearviewMirrorIsAutoLightStateR);
        return rearviewMirrorIsAutoLightStateR;
    }

    public int getRearviewMirrorIsState() {
        int rearviewMirrorIsStateR;
        LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rearviewMirrorIsStateR = carVendorSettingManager.getRearviewMirrorIsStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearviewMirrorIsStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsState.out=" + rearviewMirrorIsStateR);
            return rearviewMirrorIsStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsState.null == mCarSettingManager");
        rearviewMirrorIsStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsState.out=" + rearviewMirrorIsStateR);
        return rearviewMirrorIsStateR;
    }

    public int getRearviewMirrorIsTurnedState() {
        LogUtil.verbose("AutoSettingImpl", " getRearviewMirrorIsTurnedState.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857957, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getRearviewMirrorIsTurnedState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public float getRearviewMirrorXLeft() {
        LogUtil.verbose("AutoSettingImpl", "getRearviewMirrorXLeft");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float rearviewMirrorXLeft = carVendorSettingManager.getRearviewMirrorXLeft();
            LogUtil.debug("AutoSettingImpl", "getRearviewMirrorXLeft=" + rearviewMirrorXLeft);
            return rearviewMirrorXLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getRearviewMirrorXRight() {
        LogUtil.verbose("AutoSettingImpl", "getRearviewMirrorXRight");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float rearviewMirrorXRight = carVendorSettingManager.getRearviewMirrorXRight();
            LogUtil.debug("AutoSettingImpl", "getRearviewMirrorXRight=" + rearviewMirrorXRight);
            return rearviewMirrorXRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getRearviewMirrorYLeft() {
        LogUtil.verbose("AutoSettingImpl", "getRearviewMirrorYLeft");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float rearviewMirrorYLeft = carVendorSettingManager.getRearviewMirrorYLeft();
            LogUtil.debug("AutoSettingImpl", "getRearviewMirrorYLeft=" + rearviewMirrorYLeft);
            return rearviewMirrorYLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getRearviewMirrorYRight() {
        LogUtil.verbose("AutoSettingImpl", "getRearviewMirrorYRight");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float rearviewMirrorYRight = carVendorSettingManager.getRearviewMirrorYRight();
            LogUtil.debug("AutoSettingImpl", "getRearviewMirrorYRight=" + rearviewMirrorYRight);
            return rearviewMirrorYRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getRefrig1PositionMarkBit() {
        int refrig1PositionMarkBitR;
        LogUtil.verbose("AutoSettingImpl", " getRefrig1PositionMarkBit.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrig1PositionMarkBitR = carVendorSettingManager.getRefrig1PositionMarkBitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrig1PositionMarkBitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrig1PositionMarkBit.out=" + refrig1PositionMarkBitR);
            return refrig1PositionMarkBitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrig1PositionMarkBit.null == mCarSettingManager");
        refrig1PositionMarkBitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrig1PositionMarkBit.out=" + refrig1PositionMarkBitR);
        return refrig1PositionMarkBitR;
    }

    public int getRefrig2PositionMarkBit() {
        int refrig2PositionMarkBitR;
        LogUtil.verbose("AutoSettingImpl", " getRefrig2PositionMarkBit.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrig2PositionMarkBitR = carVendorSettingManager.getRefrig2PositionMarkBitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrig2PositionMarkBitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrig2PositionMarkBit.out=" + refrig2PositionMarkBitR);
            return refrig2PositionMarkBitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrig2PositionMarkBit.null == mCarSettingManager");
        refrig2PositionMarkBitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrig2PositionMarkBit.out=" + refrig2PositionMarkBitR);
        return refrig2PositionMarkBitR;
    }

    public int getRefrigNumFlag() {
        int refrigNumFlagR;
        LogUtil.verbose("AutoSettingImpl", " getRefrigNumFlag.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrigNumFlagR = carVendorSettingManager.getRefrigNumFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrigNumFlagR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrigNumFlag.out=" + refrigNumFlagR);
            return refrigNumFlagR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrigNumFlag.null == mCarSettingManager");
        refrigNumFlagR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrigNumFlag.out=" + refrigNumFlagR);
        return refrigNumFlagR;
    }

    public int getRefrigWorkFlag() {
        int refrigWorkFlagR;
        LogUtil.verbose("AutoSettingImpl", " getRefrigWorkFlag.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrigWorkFlagR = carVendorSettingManager.getRefrigWorkFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrigWorkFlagR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrigWorkFlag.out=" + refrigWorkFlagR);
            return refrigWorkFlagR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrigWorkFlag.null == mCarSettingManager");
        refrigWorkFlagR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrigWorkFlag.out=" + refrigWorkFlagR);
        return refrigWorkFlagR;
    }

    public int getRefrigWorkMode() {
        int refrigWorkModeR;
        LogUtil.verbose("AutoSettingImpl", " getRefrigWorkMode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrigWorkModeR = carVendorSettingManager.getRefrigWorkModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrigWorkModeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrigWorkMode.out=" + refrigWorkModeR);
            return refrigWorkModeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrigWorkMode.null == mCarSettingManager");
        refrigWorkModeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrigWorkMode.out=" + refrigWorkModeR);
        return refrigWorkModeR;
    }

    public int getRefrigeratorDoorState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int refrigeratorDoorState = carVendorSettingManager.getRefrigeratorDoorState();
            LogUtil.debug("AutoSettingImpl", "getRefrigeratorDoorState=" + refrigeratorDoorState);
            return refrigeratorDoorState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRefrigeratorMobileTerminalPushRe() {
        int refrigeratorMobileTerminalPushReR;
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorMobileTerminalPushRe.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrigeratorMobileTerminalPushReR = carVendorSettingManager.getRefrigeratorMobileTerminalPushReR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrigeratorMobileTerminalPushReR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrigeratorMobileTerminalPushRe.out=" + refrigeratorMobileTerminalPushReR);
            return refrigeratorMobileTerminalPushReR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorMobileTerminalPushRe.null == mCarSettingManager");
        refrigeratorMobileTerminalPushReR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorMobileTerminalPushRe.out=" + refrigeratorMobileTerminalPushReR);
        return refrigeratorMobileTerminalPushReR;
    }

    public int getRefrigeratorMode() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int refrigeratorModeR = carVendorSettingManager.getRefrigeratorModeR();
            LogUtil.debug("AutoSettingImpl", "getRefrigeratorModeR=" + refrigeratorModeR);
            return refrigeratorModeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRefrigeratorState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int refrigeratorState = carVendorSettingManager.getRefrigeratorState();
            LogUtil.debug("AutoSettingImpl", "getRefrigeratorState=" + refrigeratorState);
            return refrigeratorState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRefrigeratorTemperature() {
        int refrigeratorTemperatureR;
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrigeratorTemperatureR = carVendorSettingManager.getRefrigeratorTemperatureR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrigeratorTemperatureR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrigeratorTemperature.out=" + refrigeratorTemperatureR);
            return refrigeratorTemperatureR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorTemperature.null == mCarSettingManager");
        refrigeratorTemperatureR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorTemperature.out=" + refrigeratorTemperatureR);
        return refrigeratorTemperatureR;
    }

    public int getRefrigeratorWorkMode() {
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkMode.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857962, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getRefrigeratorWorkMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRefrigeratorWorkState() {
        int refrigeratorWorkStateR;
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrigeratorWorkStateR = carVendorSettingManager.getRefrigeratorWorkStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrigeratorWorkStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkState.out=" + refrigeratorWorkStateR);
            return refrigeratorWorkStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkState.null == mCarSettingManager");
        refrigeratorWorkStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkState.out=" + refrigeratorWorkStateR);
        return refrigeratorWorkStateR;
    }

    public int getRefrigeratorWorkTemperature() {
        int refrigeratorWorkTemperatureR;
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                refrigeratorWorkTemperatureR = carVendorSettingManager.getRefrigeratorWorkTemperatureR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                refrigeratorWorkTemperatureR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkTemperature.out=" + refrigeratorWorkTemperatureR);
            return refrigeratorWorkTemperatureR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkTemperature.null == mCarSettingManager");
        refrigeratorWorkTemperatureR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRefrigeratorWorkTemperature.out=" + refrigeratorWorkTemperatureR);
        return refrigeratorWorkTemperatureR;
    }

    public int getRemberPowerOffState() {
        int remberPowerOffStateR;
        LogUtil.verbose("AutoSettingImpl", " getRemberPowerOffState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                remberPowerOffStateR = carVendorSettingManager.getRemberPowerOffStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                remberPowerOffStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRemberPowerOffState.out=" + remberPowerOffStateR);
            return remberPowerOffStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRemberPowerOffState.null == mCarSettingManager");
        remberPowerOffStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRemberPowerOffState.out=" + remberPowerOffStateR);
        return remberPowerOffStateR;
    }

    public int getRemenberPowerOffState() {
        LogUtil.debug("AutoSettingImpl", "getRemenberPowerOffState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857701, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRemenberPowerOffState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRemindSpeedSupport() {
        return 0;
    }

    public int getRemoteControlDownwindowState() {
        LogUtil.verbose("AutoSettingImpl", "getRemoteControlDownwindowState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857615, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRemoteControlDownwindowState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRemoteControlLockWindowState() {
        return 0;
    }

    public int getRemoteControlLockWindowSupport() {
        return 0;
    }

    public int getRemoteControlUnlockWindowState() {
        return 0;
    }

    public int getRemoteControlUnlockWindowSupport() {
        return 0;
    }

    public int getRemoteControlUpwindowState() {
        LogUtil.verbose("AutoSettingImpl", "getRemoteControlUpwindowState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857613, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRemoteControlUpwindowState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRemoteKeyStartAcTime() {
        LogUtil.verbose("AutoSettingImpl", "getRemoteKeyStartAcTime");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856063, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getRemoteKeyStartAcTime prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRemoteKeyStartAcTimeSupport() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int remoteKeyStartAcTimeR = carVendorSettingManager.getRemoteKeyStartAcTimeR();
            LogUtil.debug("AutoSettingImpl", "getRemoteKeyStartAcTimeR=" + remoteKeyStartAcTimeR);
            return remoteKeyStartAcTimeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getReportVolume() {
        LogUtil.verbose("AutoSettingImpl", "getReportVolume");
        return 0;
    }

    public int getReportVolumeSupport() {
        return 0;
    }

    public int getRightElectricPedal() {
        int rightElectricPedalR;
        LogUtil.verbose("AutoSettingImpl", " getRightElectricPedal.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rightElectricPedalR = carVendorSettingManager.getRightElectricPedalR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightElectricPedalR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRightElectricPedal.out=" + rightElectricPedalR);
            return rightElectricPedalR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRightElectricPedal.null == mCarSettingManager");
        rightElectricPedalR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRightElectricPedal.out=" + rightElectricPedalR);
        return rightElectricPedalR;
    }

    public int getRightHeadlampLevel() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int rightHeadlampLevel = carVendorSettingManager.getRightHeadlampLevel();
            LogUtil.debug("AutoSettingImpl", "getRightHeadlampLevel=" + rightHeadlampLevel);
            return rightHeadlampLevel;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRightRearSafebeltStatus() {
        int rightRearSafebeltStatusR;
        LogUtil.verbose("AutoSettingImpl", " getRightRearSafebeltStatus.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rightRearSafebeltStatusR = carVendorSettingManager.getRightRearSafebeltStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightRearSafebeltStatusR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRightRearSafebeltStatus.out service =" + rightRearSafebeltStatusR);
            return rightRearSafebeltStatusR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRightRearSafebeltStatus.null");
        rightRearSafebeltStatusR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRightRearSafebeltStatus.out service =" + rightRearSafebeltStatusR);
        return rightRearSafebeltStatusR;
    }

    public int getRightRseChildModeSwitchStatusR() {
        LogUtil.info("AutoSettingImpl", "getRightRseChildModeSwitchStatusR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int rightRseChildModeSwitchStatusR = carVendorSettingManager.getRightRseChildModeSwitchStatusR();
            LogUtil.debug("AutoSettingImpl", "getRightRseChildModeSwitchStatusR=" + rightRseChildModeSwitchStatusR);
            return rightRseChildModeSwitchStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRightThirdSafebeltStatus() {
        int rightThirdSafebeltStatusR;
        LogUtil.verbose("AutoSettingImpl", " getRightThirdSafebeltStatus.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rightThirdSafebeltStatusR = carVendorSettingManager.getRightThirdSafebeltStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rightThirdSafebeltStatusR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRightThirdSafebeltStatus.out service =" + rightThirdSafebeltStatusR);
            return rightThirdSafebeltStatusR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRightThirdSafebeltStatus.null");
        rightThirdSafebeltStatusR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRightThirdSafebeltStatus.out service =" + rightThirdSafebeltStatusR);
        return rightThirdSafebeltStatusR;
    }

    public int getRrEcuMassageLevel() {
        int rrEcuMassageLevelR;
        LogUtil.verbose("AutoSettingImpl", " getRrEcuMassageLevel.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrEcuMassageLevelR = carVendorSettingManager.getRrEcuMassageLevelR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrEcuMassageLevelR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrEcuMassageLevel.out service =" + rrEcuMassageLevelR);
            return rrEcuMassageLevelR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrEcuMassageLevel.null");
        rrEcuMassageLevelR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrEcuMassageLevel.out service =" + rrEcuMassageLevelR);
        return rrEcuMassageLevelR;
    }

    public int getRrEcuMassageMode() {
        int rrEcuMassageModeR;
        LogUtil.verbose("AutoSettingImpl", " getRrEcuMassageMode.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrEcuMassageModeR = carVendorSettingManager.getRrEcuMassageModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrEcuMassageModeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrEcuMassageMode.out service =" + rrEcuMassageModeR);
            return rrEcuMassageModeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrEcuMassageMode.null");
        rrEcuMassageModeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrEcuMassageMode.out service =" + rrEcuMassageModeR);
        return rrEcuMassageModeR;
    }

    public int getRrHotStone2MassageConfig() {
        int rrHotStone2MassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone2MassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrHotStone2MassageConfigR = carVendorSettingManager.getRrHotStone2MassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrHotStone2MassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrHotStone2MassageConfig.out service =" + rrHotStone2MassageConfigR);
            return rrHotStone2MassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone2MassageConfig.null");
        rrHotStone2MassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone2MassageConfig.out service =" + rrHotStone2MassageConfigR);
        return rrHotStone2MassageConfigR;
    }

    public int getRrHotStone3MassageConfig() {
        int rrHotStone3MassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone3MassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrHotStone3MassageConfigR = carVendorSettingManager.getRrHotStone3MassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrHotStone3MassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrHotStone3MassageConfig.out service =" + rrHotStone3MassageConfigR);
            return rrHotStone3MassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone3MassageConfig.null");
        rrHotStone3MassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone3MassageConfig.out service =" + rrHotStone3MassageConfigR);
        return rrHotStone3MassageConfigR;
    }

    public int getRrHotStone4MassageConfig() {
        int rrHotStone4MassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone4MassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrHotStone4MassageConfigR = carVendorSettingManager.getRrHotStone4MassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrHotStone4MassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrHotStone4MassageConfig.out service =" + rrHotStone4MassageConfigR);
            return rrHotStone4MassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone4MassageConfig.null");
        rrHotStone4MassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrHotStone4MassageConfig.out service =" + rrHotStone4MassageConfigR);
        return rrHotStone4MassageConfigR;
    }

    public int getRrMas2ToggleModeConfig() {
        int rrMas2ToggleModeConfigR;
        LogUtil.verbose("AutoSettingImpl", " getRrMas2ToggleModeConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrMas2ToggleModeConfigR = carVendorSettingManager.getRrMas2ToggleModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrMas2ToggleModeConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrMas2ToggleModeConfig.out service =" + rrMas2ToggleModeConfigR);
            return rrMas2ToggleModeConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrMas2ToggleModeConfig.null");
        rrMas2ToggleModeConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrMas2ToggleModeConfig.out service =" + rrMas2ToggleModeConfigR);
        return rrMas2ToggleModeConfigR;
    }

    public int getRrMasToggleModeConfig() {
        int rrMasToggleModeConfigR;
        LogUtil.verbose("AutoSettingImpl", " getRrMasToggleModeConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrMasToggleModeConfigR = carVendorSettingManager.getRrMasToggleModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrMasToggleModeConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrMasToggleModeConfig.out service =" + rrMasToggleModeConfigR);
            return rrMasToggleModeConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrMasToggleModeConfig.null");
        rrMasToggleModeConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrMasToggleModeConfig.out service =" + rrMasToggleModeConfigR);
        return rrMasToggleModeConfigR;
    }

    public int getRrNormal2MassageConfig() {
        int rrNormal2MassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getRrNormal2MassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrNormal2MassageConfigR = carVendorSettingManager.getRrNormal2MassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrNormal2MassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrNormal2MassageConfig.out service =" + rrNormal2MassageConfigR);
            return rrNormal2MassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrNormal2MassageConfig.null");
        rrNormal2MassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrNormal2MassageConfig.out service =" + rrNormal2MassageConfigR);
        return rrNormal2MassageConfigR;
    }

    public int getRrNormal3MassageConfig() {
        int rrNormal3MassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getRrNormal3MassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrNormal3MassageConfigR = carVendorSettingManager.getRrNormal3MassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrNormal3MassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrNormal3MassageConfig.out service =" + rrNormal3MassageConfigR);
            return rrNormal3MassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrNormal3MassageConfig.null");
        rrNormal3MassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrNormal3MassageConfig.out service =" + rrNormal3MassageConfigR);
        return rrNormal3MassageConfigR;
    }

    public int getRrNormalMassageConfig() {
        int rrNormalMassageConfigR;
        LogUtil.verbose("AutoSettingImpl", " getRrNormalMassageConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                rrNormalMassageConfigR = carVendorSettingManager.getRrNormalMassageConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rrNormalMassageConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getRrNormalMassageConfig.out service =" + rrNormalMassageConfigR);
            return rrNormalMassageConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getRrNormalMassageConfig.null");
        rrNormalMassageConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getRrNormalMassageConfig.out service =" + rrNormalMassageConfigR);
        return rrNormalMassageConfigR;
    }

    public int getRrseChildModeCodeWriteFbR() {
        LogUtil.info("AutoSettingImpl", "getRrseChildModeCodeWriteFbR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int rrseChildModeCodeWriteFbR = carVendorSettingManager.getRrseChildModeCodeWriteFbR();
            LogUtil.debug("AutoSettingImpl", "getRrseChildModeCodeWriteFbR=" + rrseChildModeCodeWriteFbR);
            return rrseChildModeCodeWriteFbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRrseChildModeTimeWriteFbR() {
        LogUtil.info("AutoSettingImpl", "getRrseChildModeTimeWriteFbR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int rrseChildModeTimeWriteFbR = carVendorSettingManager.getRrseChildModeTimeWriteFbR();
            LogUtil.debug("AutoSettingImpl", "getRrseChildModeTimeWriteFbR=" + rrseChildModeTimeWriteFbR);
            return rrseChildModeTimeWriteFbR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRrseMachineSwitchStateR() {
        LogUtil.info("AutoSettingImpl", "getRrseMachineSwitchStateR");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int rrseMachineSwitchStateR = carVendorSettingManager.getRrseMachineSwitchStateR();
            LogUtil.debug("AutoSettingImpl", "getRrseMachineSwitchStateR=" + rrseMachineSwitchStateR);
            return rrseMachineSwitchStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRuningCloseDoorState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int runingCloseDoorStateR = carVendorSettingManager.getRuningCloseDoorStateR();
            LogUtil.debug("AutoSettingImpl", "getRuningCloseDoorStateR=" + runingCloseDoorStateR);
            return runingCloseDoorStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSOCTarget() {
        LogUtil.verbose("AutoSettingImpl", "getSOCTarget");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857790, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getSOCTarget prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSafeBeltCloseDriveDoor() {
        LogUtil.debug("AutoSettingImpl", "getSafeBeltCloseDriveDoor is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int safeBeltCloseDriveDoorR = carVendorSettingManager.getSafeBeltCloseDriveDoorR();
            LogUtil.debug("AutoSettingImpl", "getSafeBeltCloseDriveDoorR=" + safeBeltCloseDriveDoorR);
            return safeBeltCloseDriveDoorR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSafeBeltInd() {
        return 0;
    }

    public int getSafeBeltWarnState() {
        LogUtil.verbose("AutoSettingImpl", "getSafeBeltWarnState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858305, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSafeBeltWarnState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSafeWarnState() {
        LogUtil.verbose("AutoSettingImpl", "getSafeWarnState");
        return 0;
    }

    public int getSandAbsModeState() {
        int sandAbsModeStateR;
        LogUtil.verbose("AutoSettingImpl", " getSandAbsModeState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                sandAbsModeStateR = carVendorSettingManager.getSandAbsModeStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                sandAbsModeStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSandAbsModeState.out=" + sandAbsModeStateR);
            return sandAbsModeStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSandAbsModeState.null == mCarSettingManager");
        sandAbsModeStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSandAbsModeState.out=" + sandAbsModeStateR);
        return sandAbsModeStateR;
    }

    public int getSandAbsTipMessage() {
        int sandAbsTipMessageR;
        LogUtil.verbose("AutoSettingImpl", " getSandAbsTipMessage.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                sandAbsTipMessageR = carVendorSettingManager.getSandAbsTipMessageR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                sandAbsTipMessageR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSandAbsTipMessage.out=" + sandAbsTipMessageR);
            return sandAbsTipMessageR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSandAbsTipMessage.null == mCarSettingManager");
        sandAbsTipMessageR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSandAbsTipMessage.out=" + sandAbsTipMessageR);
        return sandAbsTipMessageR;
    }

    public int getSaveOwnerState() {
        int saveOwnerStateR;
        LogUtil.verbose("AutoSettingImpl", " getSaveOwnerState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                saveOwnerStateR = carVendorSettingManager.getSaveOwnerStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                saveOwnerStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSaveOwnerState.out=" + saveOwnerStateR);
            return saveOwnerStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSaveOwnerState.null == mCarSettingManager");
        saveOwnerStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSaveOwnerState.out=" + saveOwnerStateR);
        return saveOwnerStateR;
    }

    public int getScreenAngle() {
        int screenAngleR;
        LogUtil.debug("AutoSettingImpl", " getScreenAngle.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                screenAngleR = carVendorSettingManager.getScreenAngleR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                screenAngleR = -1;
            }
        } else {
            screenAngleR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getScreenAngle.out = " + screenAngleR);
        return screenAngleR;
    }

    public int getScreenAngleValue() {
        int screenAngleValueR;
        LogUtil.debug("AutoSettingImpl", " getScreenAngleValue.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                screenAngleValueR = carVendorSettingManager.getScreenAngleValueR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                screenAngleValueR = -1;
            }
        } else {
            screenAngleValueR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getScreenAngleValue.out = " + screenAngleValueR);
        return screenAngleValueR;
    }

    public int getScreenAntiPinchState() {
        int screenAntiPinchStateR;
        LogUtil.debug("AutoSettingImpl", " getScreenAntiPinchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                screenAntiPinchStateR = carVendorSettingManager.getScreenAntiPinchStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                screenAntiPinchStateR = -1;
            }
        } else {
            screenAntiPinchStateR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getScreenAntiPinchState.out = " + screenAntiPinchStateR);
        return screenAntiPinchStateR;
    }

    public int getScreenCallbackMessage() {
        int screenCallbackMessageR;
        LogUtil.verbose("AutoSettingImpl", " getScreenCallbackMessage.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                screenCallbackMessageR = carVendorSettingManager.getScreenCallbackMessageR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                screenCallbackMessageR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getScreenCallbackMessage.out=" + screenCallbackMessageR);
            return screenCallbackMessageR;
        }
        LogUtil.verbose("AutoSettingImpl", " getScreenCallbackMessage.null == mCarSettingManager");
        screenCallbackMessageR = -1;
        LogUtil.verbose("AutoSettingImpl", " getScreenCallbackMessage.out=" + screenCallbackMessageR);
        return screenCallbackMessageR;
    }

    public int getScreenRotationAction() {
        int screenRotationActionR;
        LogUtil.debug("AutoSettingImpl", " getScreenRotationAction.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                screenRotationActionR = carVendorSettingManager.getScreenRotationActionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                screenRotationActionR = -1;
            }
        } else {
            screenRotationActionR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getScreenRotationAction.out = " + screenRotationActionR);
        return screenRotationActionR;
    }

    public int getScreenRotationOverHotState() {
        int screenRotationOverHotStateR;
        LogUtil.debug("AutoSettingImpl", " getScreenRotationOverHotState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                screenRotationOverHotStateR = carVendorSettingManager.getScreenRotationOverHotStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                screenRotationOverHotStateR = -1;
            }
        } else {
            screenRotationOverHotStateR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getScreenRotationOverHotState.out = " + screenRotationOverHotStateR);
        return screenRotationOverHotStateR;
    }

    public int getScreenVoltageState() {
        int screenVoltageStateR;
        LogUtil.debug("AutoSettingImpl", " getScreenVoltageState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                screenVoltageStateR = carVendorSettingManager.getScreenVoltageStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                screenVoltageStateR = -1;
            }
        } else {
            screenVoltageStateR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getScreenVoltageState.out = " + screenVoltageStateR);
        return screenVoltageStateR;
    }

    public int getSeaModeChargingSocType() {
        int seaModeChargingSocTypeR;
        LogUtil.verbose("AutoSettingImpl", " getSeaModeChargingSocType.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                seaModeChargingSocTypeR = carVendorSettingManager.getSeaModeChargingSocTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seaModeChargingSocTypeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSeaModeChargingSocType.out=" + seaModeChargingSocTypeR);
            return seaModeChargingSocTypeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSeaModeChargingSocType.null == mCarSettingManager");
        seaModeChargingSocTypeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSeaModeChargingSocType.out=" + seaModeChargingSocTypeR);
        return seaModeChargingSocTypeR;
    }

    public int getSeatDistributionFormConfig() {
        int seatDistributionFormConfigR;
        LogUtil.verbose("AutoSettingImpl", " getSeatDistributionFormConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                seatDistributionFormConfigR = carVendorSettingManager.getSeatDistributionFormConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatDistributionFormConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSeatDistributionFormConfig.out service =" + seatDistributionFormConfigR);
            return seatDistributionFormConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSeatDistributionFormConfig.null");
        seatDistributionFormConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSeatDistributionFormConfig.out service =" + seatDistributionFormConfigR);
        return seatDistributionFormConfigR;
    }

    public int getSeatHeatingNotL1PState() {
        LogUtil.verbose("AutoSettingImpl", "getSeatHeatingNotL1PState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857866, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingNotL1PState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHeatingState2RearLeft() {
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingState2RearLeft");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857867, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingState2RearLeft prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHeatingState2RearRight() {
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingState2RearRight");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857868, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingState2RearRight prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHeatingStateDriver() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857723, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingStateDriver prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHeatingStateLeftRear() {
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingStateLeftRear");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857730, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingStateLeftRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHeatingStatePassenger() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857728, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingStatePassenger prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHeatingStateRearLeft() {
        LogUtil.verbose("AutoSettingImpl", "getSeatHeatingStateRearLeft");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857864, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingStateRearLeft prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHeatingStateRearRight() {
        LogUtil.verbose("AutoSettingImpl", "getSeatHeatingStateRearLeft");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857865, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingStateRearLeft prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHeatingStateRightRear() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857731, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatHeatingStateRightRear prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatHorizationDriver() {
        LogUtil.verbose("AutoSettingImpl", "getSeatHorizationDriver");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int seatHorizationDriver = carVendorSettingManager.getSeatHorizationDriver();
            LogUtil.debug("AutoSettingImpl", "getSeatHorizationDriver=" + seatHorizationDriver);
            return seatHorizationDriver;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSeatHorizationLeftArea() {
        LogUtil.verbose("AutoSettingImpl", "getSeatHorizationLeftArea");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int seatHorizationBackLeft = carVendorSettingManager.getSeatHorizationBackLeft();
            LogUtil.debug("AutoSettingImpl", "getSeatHorizationBackLeft=" + seatHorizationBackLeft);
            return seatHorizationBackLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSeatHorizationPassenger() {
        LogUtil.verbose("AutoSettingImpl", "getSeatHorizationPassenger");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int seatHorizationCopilot = carVendorSettingManager.getSeatHorizationCopilot();
            LogUtil.debug("AutoSettingImpl", "getSeatHorizationCopilot=" + seatHorizationCopilot);
            return seatHorizationCopilot;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSeatHorizationRightArea() {
        LogUtil.verbose("AutoSettingImpl", "getSeatHorizationRightArea");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int seatHorizationBackRight = carVendorSettingManager.getSeatHorizationBackRight();
            LogUtil.debug("AutoSettingImpl", "getSeatHorizationBackRight=" + seatHorizationBackRight);
            return seatHorizationBackRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSeatSecullDistributionFormConfig() {
        int seatSecullDistributionFormConfigR;
        LogUtil.verbose("AutoSettingImpl", " getSeatSecullDistributionFormConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                seatSecullDistributionFormConfigR = carVendorSettingManager.getSeatSecullDistributionFormConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatSecullDistributionFormConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSeatSecullDistributionFormConfig.out service =" + seatSecullDistributionFormConfigR);
            return seatSecullDistributionFormConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSeatSecullDistributionFormConfig.null");
        seatSecullDistributionFormConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSeatSecullDistributionFormConfig.out service =" + seatSecullDistributionFormConfigR);
        return seatSecullDistributionFormConfigR;
    }

    public int getSeatSeculrDistributionFormConfig() {
        int seatSeculrDistributionFormConfigR;
        LogUtil.verbose("AutoSettingImpl", " getSeatSeculrDistributionFormConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                seatSeculrDistributionFormConfigR = carVendorSettingManager.getSeatSeculrDistributionFormConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatSeculrDistributionFormConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSeatSeculrDistributionFormConfig.out service =" + seatSeculrDistributionFormConfigR);
            return seatSeculrDistributionFormConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSeatSeculrDistributionFormConfig.null");
        seatSeculrDistributionFormConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSeatSeculrDistributionFormConfig.out service =" + seatSeculrDistributionFormConfigR);
        return seatSeculrDistributionFormConfigR;
    }

    public int getSeatSecurlDistributionFormConfig() {
        int seatSecurlDistributionFormConfigR;
        LogUtil.verbose("AutoSettingImpl", " getSeatSecurlDistributionFormConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                seatSecurlDistributionFormConfigR = carVendorSettingManager.getSeatSecurlDistributionFormConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatSecurlDistributionFormConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSeatSecurlDistributionFormConfig.out service =" + seatSecurlDistributionFormConfigR);
            return seatSecurlDistributionFormConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSeatSecurlDistributionFormConfig.null");
        seatSecurlDistributionFormConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSeatSecurlDistributionFormConfig.out service =" + seatSecurlDistributionFormConfigR);
        return seatSecurlDistributionFormConfigR;
    }

    public int getSeatSecurrDistributionFormConfig() {
        int seatSecurrDistributionFormConfigR;
        LogUtil.verbose("AutoSettingImpl", " getSeatSecurrDistributionFormConfig.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                seatSecurrDistributionFormConfigR = carVendorSettingManager.getSeatSecurrDistributionFormConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                seatSecurrDistributionFormConfigR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSeatSecurrDistributionFormConfig.out service =" + seatSecurrDistributionFormConfigR);
            return seatSecurrDistributionFormConfigR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSeatSecurrDistributionFormConfig.null");
        seatSecurrDistributionFormConfigR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSeatSecurrDistributionFormConfig.out service =" + seatSecurrDistributionFormConfigR);
        return seatSecurrDistributionFormConfigR;
    }

    public float getSeatTurnHeightDriver() {
        LogUtil.verbose("AutoSettingImpl", "getSeatTurnHeightDriver");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float seatTurnHeightDriver = carVendorSettingManager.getSeatTurnHeightDriver();
            LogUtil.debug("AutoSettingImpl", "getSeatTurnHeightDriver=" + seatTurnHeightDriver);
            return seatTurnHeightDriver;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSeatTurnHeightLeftArea() {
        LogUtil.verbose("AutoSettingImpl", "getSeatTurnHeightLeftArea");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float seatTurnHeightBackLeft = carVendorSettingManager.getSeatTurnHeightBackLeft();
            LogUtil.debug("AutoSettingImpl", "getSeatTurnHeightBackLeft=" + seatTurnHeightBackLeft);
            return seatTurnHeightBackLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSeatTurnHeightPassenger() {
        LogUtil.verbose("AutoSettingImpl", "getSeatTurnHeightPassenger");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float seatTurnHeightCopilot = carVendorSettingManager.getSeatTurnHeightCopilot();
            LogUtil.debug("AutoSettingImpl", "getSeatTurnHeightCopilot=" + seatTurnHeightCopilot);
            return seatTurnHeightCopilot;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSeatTurnHeightRightArea() {
        LogUtil.verbose("AutoSettingImpl", "getSeatTurnHeightRightArea");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float seatTurnHeightBackRight = carVendorSettingManager.getSeatTurnHeightBackRight();
            LogUtil.debug("AutoSettingImpl", "getSeatTurnHeightBackRight=" + seatTurnHeightBackRight);
            return seatTurnHeightBackRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getSeatVentilatingNotL1PState() {
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingNotL1PState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857869, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingNotL1PState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatVentilatingState() {
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857733, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatVentilatingStateBackLeft() {
        LogUtil.verbose("AutoSettingImpl", "getSeatVentilatingStateBackLeft");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857740, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingStateBackLeft prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatVentilatingStateBackRight() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857741, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingStateBackRight prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSeatVentilatingStatePassenger() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857738, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSeatVentilatingStatePassenger prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public float getSeatbackrestPostionDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float seatbackrestPostionDriver = carVendorSettingManager.getSeatbackrestPostionDriver();
            LogUtil.debug("AutoSettingImpl", "getSeatbackrestPostionDriver=" + seatbackrestPostionDriver);
            return seatbackrestPostionDriver;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSeatbackrestPostionLeftArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float seatbackrestPostionBackLeft = carVendorSettingManager.getSeatbackrestPostionBackLeft();
            LogUtil.debug("AutoSettingImpl", "getSeatbackrestPostionBackLeft=" + seatbackrestPostionBackLeft);
            return seatbackrestPostionBackLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSeatbackrestPostionPassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float seatbackrestPostionCopilot = carVendorSettingManager.getSeatbackrestPostionCopilot();
            LogUtil.debug("AutoSettingImpl", "getSeatbackrestPostionCopilot=" + seatbackrestPostionCopilot);
            return seatbackrestPostionCopilot;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSeatbackrestPostionRightArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float seatbackrestPostionBackRight = carVendorSettingManager.getSeatbackrestPostionBackRight();
            LogUtil.debug("AutoSettingImpl", "getSeatbackrestPostionBackRight=" + seatbackrestPostionBackRight);
            return seatbackrestPostionBackRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getSecondLeftSeatBackInit() {
        int secondLeftSeatBackInitR;
        LogUtil.verbose("AutoSettingImpl", " getSecondLeftSeatBackInit.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secondLeftSeatBackInitR = carVendorSettingManager.getSecondLeftSeatBackInitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secondLeftSeatBackInitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecondLeftSeatBackInit.out service =" + secondLeftSeatBackInitR);
            return secondLeftSeatBackInitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecondLeftSeatBackInit.null");
        secondLeftSeatBackInitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecondLeftSeatBackInit.out service =" + secondLeftSeatBackInitR);
        return secondLeftSeatBackInitR;
    }

    public int getSecondLeftSeatHorizontalInit() {
        int secondLeftSeatHorizontalInitR;
        LogUtil.verbose("AutoSettingImpl", " getSecondLeftSeatHorizontalInit.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secondLeftSeatHorizontalInitR = carVendorSettingManager.getSecondLeftSeatHorizontalInitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secondLeftSeatHorizontalInitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecondLeftSeatHorizontalInit.out service =" + secondLeftSeatHorizontalInitR);
            return secondLeftSeatHorizontalInitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecondLeftSeatHorizontalInit.null");
        secondLeftSeatHorizontalInitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecondLeftSeatHorizontalInit.out service =" + secondLeftSeatHorizontalInitR);
        return secondLeftSeatHorizontalInitR;
    }

    public int getSecondRightSeatBack2Init() {
        int secondRightSeatBack2InitR;
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatBack2Init.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secondRightSeatBack2InitR = carVendorSettingManager.getSecondRightSeatBack2InitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secondRightSeatBack2InitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatBack2Init.out service =" + secondRightSeatBack2InitR);
            return secondRightSeatBack2InitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatBack2Init.null");
        secondRightSeatBack2InitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatBack2Init.out service =" + secondRightSeatBack2InitR);
        return secondRightSeatBack2InitR;
    }

    public int getSecondRightSeatBackInit() {
        int secondRightSeatBackInitR;
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatBackInit.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secondRightSeatBackInitR = carVendorSettingManager.getSecondRightSeatBackInitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secondRightSeatBackInitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatBackInit.out service =" + secondRightSeatBackInitR);
            return secondRightSeatBackInitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatBackInit.null");
        secondRightSeatBackInitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatBackInit.out service =" + secondRightSeatBackInitR);
        return secondRightSeatBackInitR;
    }

    public int getSecondRightSeatHorizontal2Init() {
        int secondRightSeatHorizontal2InitR;
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatHorizontal2Init.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secondRightSeatHorizontal2InitR = carVendorSettingManager.getSecondRightSeatHorizontal2InitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secondRightSeatHorizontal2InitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatHorizontal2Init.out service =" + secondRightSeatHorizontal2InitR);
            return secondRightSeatHorizontal2InitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatHorizontal2Init.null");
        secondRightSeatHorizontal2InitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatHorizontal2Init.out service =" + secondRightSeatHorizontal2InitR);
        return secondRightSeatHorizontal2InitR;
    }

    public int getSecondRightSeatHorizontalInit() {
        int secondRightSeatHorizontalInitR;
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatHorizontalInit.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secondRightSeatHorizontalInitR = carVendorSettingManager.getSecondRightSeatHorizontalInitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secondRightSeatHorizontalInitR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatHorizontalInit.out service =" + secondRightSeatHorizontalInitR);
            return secondRightSeatHorizontalInitR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatHorizontalInit.null");
        secondRightSeatHorizontalInitR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecondRightSeatHorizontalInit.out service =" + secondRightSeatHorizontalInitR);
        return secondRightSeatHorizontalInitR;
    }

    public int getSecuMassageLevelLeftRear() {
        int secuMassageLevelLeftRearR;
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageLevelLeftRear.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secuMassageLevelLeftRearR = carVendorSettingManager.getSecuMassageLevelLeftRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secuMassageLevelLeftRearR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecuMassageLevelLeftRear.out service =" + secuMassageLevelLeftRearR);
            return secuMassageLevelLeftRearR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageLevelLeftRear.null");
        secuMassageLevelLeftRearR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageLevelLeftRear.out service =" + secuMassageLevelLeftRearR);
        return secuMassageLevelLeftRearR;
    }

    public int getSecuMassageLevelRightRear() {
        int secuMassageLevelRightRearR;
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageLevelRightRear.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secuMassageLevelRightRearR = carVendorSettingManager.getSecuMassageLevelRightRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secuMassageLevelRightRearR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecuMassageLevelRightRear.out service =" + secuMassageLevelRightRearR);
            return secuMassageLevelRightRearR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageLevelRightRear.null");
        secuMassageLevelRightRearR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageLevelRightRear.out service =" + secuMassageLevelRightRearR);
        return secuMassageLevelRightRearR;
    }

    public int getSecuMassageModeLeftRear() {
        int secuMassageModeLeftRearR;
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageModeLeftRear.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secuMassageModeLeftRearR = carVendorSettingManager.getSecuMassageModeLeftRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secuMassageModeLeftRearR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecuMassageModeLeftRear.out service =" + secuMassageModeLeftRearR);
            return secuMassageModeLeftRearR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageModeLeftRear.null");
        secuMassageModeLeftRearR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageModeLeftRear.out service =" + secuMassageModeLeftRearR);
        return secuMassageModeLeftRearR;
    }

    public int getSecuMassageModeRightRear() {
        int secuMassageModeRightRearR;
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageModeRightRear.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                secuMassageModeRightRearR = carVendorSettingManager.getSecuMassageModeRightRearR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                secuMassageModeRightRearR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSecuMassageModeRightRear.out service =" + secuMassageModeRightRearR);
            return secuMassageModeRightRearR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageModeRightRear.null");
        secuMassageModeRightRearR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSecuMassageModeRightRear.out service =" + secuMassageModeRightRearR);
        return secuMassageModeRightRearR;
    }

    public int getServiceControlCallback() {
        int serviceControlCallbackR;
        LogUtil.verbose("AutoSettingImpl", " getServiceControlCallback.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                serviceControlCallbackR = carVendorSettingManager.getServiceControlCallbackR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                serviceControlCallbackR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getServiceControlCallback.out=" + serviceControlCallbackR);
            return serviceControlCallbackR;
        }
        LogUtil.verbose("AutoSettingImpl", " getServiceControlCallback.null == mCarSettingManager");
        serviceControlCallbackR = -1;
        LogUtil.verbose("AutoSettingImpl", " getServiceControlCallback.out=" + serviceControlCallbackR);
        return serviceControlCallbackR;
    }

    public int getSkyCurtainPowerOffAndDim() {
        int skyCurtainPowerOffAndDimR;
        LogUtil.verbose("AutoSettingImpl", " getSkyCurtainPowerOffAndDim.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                skyCurtainPowerOffAndDimR = carVendorSettingManager.getSkyCurtainPowerOffAndDimR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                skyCurtainPowerOffAndDimR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSkyCurtainPowerOffAndDim.out=" + skyCurtainPowerOffAndDimR);
            return skyCurtainPowerOffAndDimR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSkyCurtainPowerOffAndDim.null == mCarSettingManager");
        skyCurtainPowerOffAndDimR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSkyCurtainPowerOffAndDim.out=" + skyCurtainPowerOffAndDimR);
        return skyCurtainPowerOffAndDimR;
    }

    public int getSmartWelcomeLightState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int smartWelcomeLightStateR = carVendorSettingManager.getSmartWelcomeLightStateR();
            LogUtil.debug("AutoSettingImpl", "getSmartWelcomeLightStateR=" + smartWelcomeLightStateR);
            return smartWelcomeLightStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSocTargetLowLimit() {
        LogUtil.verbose("AutoSettingImpl", " getSocTargetLowLimit.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858084, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getSocTargetRange prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSocTargetRange() {
        LogUtil.verbose("AutoSettingImpl", " getSocTargetRange.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858083, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getSocTargetRange prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSpeedReminderValue() {
        LogUtil.verbose("AutoSettingImpl", "getSpeedReminderValue");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int speedReminderValueR = carVendorSettingManager.getSpeedReminderValueR();
            LogUtil.debug("AutoSettingImpl", "getSpeedReminderValueR=" + speedReminderValueR);
            return speedReminderValueR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSpeedSupportedTurn() {
        LogUtil.debug("AutoSettingImpl", "getSpeedSupportedTurn is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int speedSupportedTurnR = carVendorSettingManager.getSpeedSupportedTurnR();
            LogUtil.debug("AutoSettingImpl", "getSpeedSupportedTurnR=" + speedSupportedTurnR);
            return speedSupportedTurnR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getStartOrPowerInd() {
        return 0;
    }

    public int getSteerAssis() {
        LogUtil.verbose("AutoSettingImpl", "getSteerAssis");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int steerAssisR = carVendorSettingManager.getSteerAssisR();
            LogUtil.debug("AutoSettingImpl", "getSteerAssisR=" + steerAssisR);
            return steerAssisR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSteerAssisSupport() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int steerAssisSupported = carVendorSettingManager.getSteerAssisSupported();
            LogUtil.debug("AutoSettingImpl", "getSteerAssisSupported=" + steerAssisSupported);
            return steerAssisSupported;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSteerAssisSupported() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int steerAssisSupported = carVendorSettingManager.getSteerAssisSupported();
            LogUtil.debug("AutoSettingImpl", "getSteerAssisSupported=" + steerAssisSupported);
            return steerAssisSupported;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSteerPositionAutoReturn() {
        LogUtil.verbose("AutoSettingImpl", "getSteerPositionAutoReturn");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856146, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSteerPositionAutoReturn prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSteerPositionAutoReturnSupport() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int steerPositionAutoReturnR = carVendorSettingManager.getSteerPositionAutoReturnR();
            LogUtil.debug("AutoSettingImpl", "getSteerPositionAutoReturnR=" + steerPositionAutoReturnR);
            return steerPositionAutoReturnR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSteeringWheelHeatedPlan() {
        LogUtil.verbose("AutoSettingImpl", " getSteeringWheelHeatedPlan.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557858106, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose("AutoSettingImpl", "getSteeringWheelHeatedPlan prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSteeringWheelHeatingAutoState() {
        LogUtil.debug("AutoSettingImpl", "getSteeringWheelHeatingAutoState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857921, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSteeringWheelHeatingAutoState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSteeringWheelHeatingGear() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int steeringWheelHeatingGearR = carVendorSettingManager.getSteeringWheelHeatingGearR();
            LogUtil.debug("AutoSettingImpl", "getSteeringWheelHeatingGearR=" + steeringWheelHeatingGearR);
            return steeringWheelHeatingGearR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSteeringWheelHeatingState() {
        LogUtil.debug("AutoSettingImpl", "getSteeringWheelHeatingState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857813, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getSteeringWheelHeatingState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public float getSteeringWheelY() {
        LogUtil.verbose("AutoSettingImpl", "getSteeringWheelY");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float steeringWheelY = carVendorSettingManager.getSteeringWheelY();
            LogUtil.debug("AutoSettingImpl", "getSteeringWheelY=" + steeringWheelY);
            return steeringWheelY;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSteeringWheelZ() {
        LogUtil.verbose("AutoSettingImpl", "getSteeringWheelZ");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float steeringWheelZ = carVendorSettingManager.getSteeringWheelZ();
            LogUtil.debug("AutoSettingImpl", "getSteeringWheelZ=" + steeringWheelZ);
            return steeringWheelZ;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getSterilizationState() {
        int sterilizationStateR;
        LogUtil.verbose("AutoSettingImpl", " getSterilizationState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                sterilizationStateR = carVendorSettingManager.getSterilizationStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                sterilizationStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSterilizationState.out=" + sterilizationStateR);
            return sterilizationStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSterilizationState.null == mCarSettingManager");
        sterilizationStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSterilizationState.out=" + sterilizationStateR);
        return sterilizationStateR;
    }

    public int getSterilizationWorkState() {
        int sterilizationWorkStateR;
        LogUtil.verbose("AutoSettingImpl", " getSterilizationWorkState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                sterilizationWorkStateR = carVendorSettingManager.getSterilizationWorkStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                sterilizationWorkStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSterilizationWorkState.out=" + sterilizationWorkStateR);
            return sterilizationWorkStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSterilizationWorkState.null == mCarSettingManager");
        sterilizationWorkStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSterilizationWorkState.out=" + sterilizationWorkStateR);
        return sterilizationWorkStateR;
    }

    public int getStopRemoteCtrlDriveInd() {
        return 0;
    }

    public int getSupportDoorHeight() {
        return 0;
    }

    public int getSupportSeatMassage(int i) {
        CarPropertyValue carPropertyValueA;
        LogUtil.debug("AutoSettingImpl", "getSupportSeatMassage area=" + i);
        int iIntValue = 0;
        if (i == 1) {
            CarPropertyValue carPropertyValueA2 = com.ts.appservice.caradapter.e.c.a(557856019, 0);
            if (carPropertyValueA2 != null) {
                iIntValue = ((Integer) carPropertyValueA2.getValue()).intValue();
            }
        } else if (i == 2) {
            CarPropertyValue carPropertyValueA3 = com.ts.appservice.caradapter.e.c.a(557856020, 0);
            if (carPropertyValueA3 != null) {
                iIntValue = ((Integer) carPropertyValueA3.getValue()).intValue();
            }
        } else if (i == 3) {
            CarPropertyValue carPropertyValueA4 = com.ts.appservice.caradapter.e.c.a(557856021, 0);
            if (carPropertyValueA4 != null) {
                iIntValue = ((Integer) carPropertyValueA4.getValue()).intValue();
            }
        } else if (i == 4 && (carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856022, 0)) != null) {
            iIntValue = ((Integer) carPropertyValueA.getValue()).intValue();
        }
        LogUtil.debug("AutoSettingImpl", "getSupportSeatMassage=" + iIntValue);
        return iIntValue;
    }

    public int getSwcontrolState() {
        int swcontrolStateR;
        LogUtil.verbose("AutoSettingImpl", " getSwcontrolState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                swcontrolStateR = carVendorSettingManager.getSwcontrolStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                swcontrolStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSwcontrolState.out=" + swcontrolStateR);
            return swcontrolStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSwcontrolState.null == mCarSettingManager");
        swcontrolStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSwcontrolState.out=" + swcontrolStateR);
        return swcontrolStateR;
    }

    public int getSwsMediumRelatedKeys() {
        int swsMediumRelatedKeysR;
        LogUtil.verbose("AutoSettingImpl", " getSwsMediumRelatedKeys.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                swsMediumRelatedKeysR = carVendorSettingManager.getSwsMediumRelatedKeysR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                swsMediumRelatedKeysR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getSwsMediumRelatedKeys.out=" + swsMediumRelatedKeysR);
            return swsMediumRelatedKeysR;
        }
        LogUtil.verbose("AutoSettingImpl", " getSwsMediumRelatedKeys.null == mCarSettingManager");
        swsMediumRelatedKeysR = -1;
        LogUtil.verbose("AutoSettingImpl", " getSwsMediumRelatedKeys.out=" + swsMediumRelatedKeysR);
        return swsMediumRelatedKeysR;
    }

    public int getTailBoxTakeState() {
        LogUtil.debug("AutoSettingImpl", "getTailBoxTakeState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int tailBoxTakeStateR = carVendorSettingManager.getTailBoxTakeStateR();
            LogUtil.debug("AutoSettingImpl", "getTailBoxTakeStateR=" + tailBoxTakeStateR);
            return tailBoxTakeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTailBoxTakeSupported() {
        LogUtil.debug("AutoSettingImpl", "getTailBoxTakeSupported is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int tailBoxTakeSupportedR = carVendorSettingManager.getTailBoxTakeSupportedR();
            LogUtil.debug("AutoSettingImpl", "getTailBoxTakeSupportedR=" + tailBoxTakeSupportedR);
            return tailBoxTakeSupportedR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTailInitializationState() {
        int tailInitializationStateR;
        LogUtil.verbose("AutoSettingImpl", " getTailInitializationState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                tailInitializationStateR = carVendorSettingManager.getTailInitializationStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tailInitializationStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTailInitializationState.out=" + tailInitializationStateR);
            return tailInitializationStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTailInitializationState.null == mCarSettingManager");
        tailInitializationStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTailInitializationState.out=" + tailInitializationStateR);
        return tailInitializationStateR;
    }

    public int getTailMotorThermalProtectionState() {
        int tailMotorThermalProtectionStateR;
        LogUtil.verbose("AutoSettingImpl", " getTailMotorThermalProtectionState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                tailMotorThermalProtectionStateR = carVendorSettingManager.getTailMotorThermalProtectionStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tailMotorThermalProtectionStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTailMotorThermalProtectionState.out=" + tailMotorThermalProtectionStateR);
            return tailMotorThermalProtectionStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTailMotorThermalProtectionState.null == mCarSettingManager");
        tailMotorThermalProtectionStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTailMotorThermalProtectionState.out=" + tailMotorThermalProtectionStateR);
        return tailMotorThermalProtectionStateR;
    }

    public int getTailPositionFeedback() {
        int tailPositionFeedbackR;
        LogUtil.verbose("AutoSettingImpl", " getTailPositionFeedback.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                tailPositionFeedbackR = carVendorSettingManager.getTailPositionFeedbackR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tailPositionFeedbackR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTailPositionFeedback.out=" + tailPositionFeedbackR);
            return tailPositionFeedbackR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTailPositionFeedback.null == mCarSettingManager");
        tailPositionFeedbackR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTailPositionFeedback.out=" + tailPositionFeedbackR);
        return tailPositionFeedbackR;
    }

    public int getTailPositionPercentage() {
        int tailPositionPercentageR;
        LogUtil.verbose("AutoSettingImpl", " getTailPositionPercentage.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                tailPositionPercentageR = carVendorSettingManager.getTailPositionPercentageR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tailPositionPercentageR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTailPositionPercentage.out=" + tailPositionPercentageR);
            return tailPositionPercentageR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTailPositionPercentage.null == mCarSettingManager");
        tailPositionPercentageR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTailPositionPercentage.out=" + tailPositionPercentageR);
        return tailPositionPercentageR;
    }

    public int getTapDoorState() {
        int tapDoorStateR;
        LogUtil.verbose("AutoSettingImpl", " getTapDoorState.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                tapDoorStateR = carVendorSettingManager.getTapDoorStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                tapDoorStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTapDoorState.out service =" + tapDoorStateR);
            return tapDoorStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTapDoorState.null");
        tapDoorStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTapDoorState.out service =" + tapDoorStateR);
        return tapDoorStateR;
    }

    public int getThirdRowLeftBackrestDisplay() {
        LogUtil.verbose("AutoSettingImpl", " getThirdRowLeftBackrestDisplay.in service");
        int iIntValue = 0;
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859134, 0);
        if (carPropertyValueA != null) {
            LogUtil.verbose("AutoSettingImpl", "getThirdRowLeftBackrestDisplay prop value " + carPropertyValueA.getValue());
            iIntValue = ((Integer) carPropertyValueA.getValue()).intValue();
        }
        LogUtil.verbose("AutoSettingImpl", " getThirdRowLeftBackrestDisplay.out service =" + iIntValue);
        return iIntValue;
    }

    public int getThirdRowRightBackrestDisplay() {
        LogUtil.verbose("AutoSettingImpl", " getThirdRowRightBackrestDisplay.in service");
        int iIntValue = 0;
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859144, 0);
        if (carPropertyValueA != null) {
            LogUtil.verbose("AutoSettingImpl", "getThirdRowRightBackrestDisplay prop value " + carPropertyValueA.getValue());
            iIntValue = ((Integer) carPropertyValueA.getValue()).intValue();
        }
        LogUtil.verbose("AutoSettingImpl", " getThirdRowRightBackrestDisplay.out service =" + iIntValue);
        return iIntValue;
    }

    public int getThirdRowSeatWorkMode() {
        int thirdRowSeatWorkModeR;
        LogUtil.verbose("AutoSettingImpl", " getThirdRowSeatWorkMode.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                thirdRowSeatWorkModeR = carVendorSettingManager.getThirdRowSeatWorkModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                thirdRowSeatWorkModeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getThirdRowSeatWorkMode.out service =" + thirdRowSeatWorkModeR);
            return thirdRowSeatWorkModeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getThirdRowSeatWorkMode.null");
        thirdRowSeatWorkModeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getThirdRowSeatWorkMode.out service =" + thirdRowSeatWorkModeR);
        return thirdRowSeatWorkModeR;
    }

    public int getTowingProhibitedState() {
        int towingProhibitedStateR;
        LogUtil.verbose("AutoSettingImpl", " getTowingProhibitedState.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                towingProhibitedStateR = carVendorSettingManager.getTowingProhibitedStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                towingProhibitedStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTowingProhibitedState.out service =" + towingProhibitedStateR);
            return towingProhibitedStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTowingProhibitedState.null");
        towingProhibitedStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTowingProhibitedState.out service =" + towingProhibitedStateR);
        return towingProhibitedStateR;
    }

    public int getTrailerDragType() {
        int trailerDragTypeR;
        LogUtil.verbose("AutoSettingImpl", " getTrailerDragType.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                trailerDragTypeR = carVendorSettingManager.getTrailerDragTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trailerDragTypeR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTrailerDragType.out service =" + trailerDragTypeR);
            return trailerDragTypeR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTrailerDragType.null");
        trailerDragTypeR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTrailerDragType.out service =" + trailerDragTypeR);
        return trailerDragTypeR;
    }

    public int getTrailerLargeItemConfiguration() {
        int trailerLargeItemConfigurationR;
        LogUtil.verbose("AutoSettingImpl", " getTrailerLargeItemConfiguration.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                trailerLargeItemConfigurationR = carVendorSettingManager.getTrailerLargeItemConfigurationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trailerLargeItemConfigurationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTrailerLargeItemConfiguration.out service =" + trailerLargeItemConfigurationR);
            return trailerLargeItemConfigurationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTrailerLargeItemConfiguration.null");
        trailerLargeItemConfigurationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTrailerLargeItemConfiguration.out service =" + trailerLargeItemConfigurationR);
        return trailerLargeItemConfigurationR;
    }

    public int getTrailerLargeLimitWeight() {
        int trailerLargeLimitWeightR;
        LogUtil.verbose("AutoSettingImpl", " getTrailerLargeLimitWeight.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                trailerLargeLimitWeightR = carVendorSettingManager.getTrailerLargeLimitWeightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trailerLargeLimitWeightR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTrailerLargeLimitWeight.out service =" + trailerLargeLimitWeightR);
            return trailerLargeLimitWeightR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTrailerLargeLimitWeight.null");
        trailerLargeLimitWeightR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTrailerLargeLimitWeight.out service =" + trailerLargeLimitWeightR);
        return trailerLargeLimitWeightR;
    }

    public int getTrailerLightCheckState() {
        LogUtil.debug("AutoSettingImpl", "getTrailerLightCheckState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int trailerLightCheckStateR = carVendorSettingManager.getTrailerLightCheckStateR();
            LogUtil.debug("AutoSettingImpl", "getTrailerLightCheckStateR=" + trailerLightCheckStateR);
            return trailerLightCheckStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrailerMiddleItemConfiguration() {
        int trailerMiddleItemConfigurationR;
        LogUtil.verbose("AutoSettingImpl", " getTrailerMiddleItemConfiguration.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                trailerMiddleItemConfigurationR = carVendorSettingManager.getTrailerMiddleItemConfigurationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trailerMiddleItemConfigurationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTrailerMiddleItemConfiguration.out service =" + trailerMiddleItemConfigurationR);
            return trailerMiddleItemConfigurationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTrailerMiddleItemConfiguration.null");
        trailerMiddleItemConfigurationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTrailerMiddleItemConfiguration.out service =" + trailerMiddleItemConfigurationR);
        return trailerMiddleItemConfigurationR;
    }

    public int getTrailerMiddleLimitWeight() {
        int trailerMiddleLimitWeightR;
        LogUtil.verbose("AutoSettingImpl", " getTrailerMiddleLimitWeight.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                trailerMiddleLimitWeightR = carVendorSettingManager.getTrailerMiddleLimitWeightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trailerMiddleLimitWeightR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTrailerMiddleLimitWeight.out service =" + trailerMiddleLimitWeightR);
            return trailerMiddleLimitWeightR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTrailerMiddleLimitWeight.null");
        trailerMiddleLimitWeightR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTrailerMiddleLimitWeight.out service =" + trailerMiddleLimitWeightR);
        return trailerMiddleLimitWeightR;
    }

    public int getTrailerModeCode() {
        LogUtil.debug("AutoSettingImpl", "getTrailerModeCode is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int trailerModeCodeR = carVendorSettingManager.getTrailerModeCodeR();
            LogUtil.debug("AutoSettingImpl", "getTrailerModeCodeR=" + trailerModeCodeR);
            return trailerModeCodeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrailerModeMileage() {
        LogUtil.info("AutoSettingImpl", "getTrailerModeMileage");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int trailerModeMileageR = carVendorSettingManager.getTrailerModeMileageR();
            LogUtil.debug("AutoSettingImpl", "getTrailerModeMileage=" + trailerModeMileageR);
            return trailerModeMileageR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrailerModeState() {
        LogUtil.debug("AutoSettingImpl", "getTrailerModeState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int trailerModeStateR = carVendorSettingManager.getTrailerModeStateR();
            LogUtil.debug("AutoSettingImpl", "getTrailerModeStateR=" + trailerModeStateR);
            return trailerModeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrailerSmallItemConfiguration() {
        int trailerSmallItemConfigurationR;
        LogUtil.verbose("AutoSettingImpl", " getTrailerSmallItemConfiguration.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                trailerSmallItemConfigurationR = carVendorSettingManager.getTrailerSmallItemConfigurationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trailerSmallItemConfigurationR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTrailerSmallItemConfiguration.out service =" + trailerSmallItemConfigurationR);
            return trailerSmallItemConfigurationR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTrailerSmallItemConfiguration.null");
        trailerSmallItemConfigurationR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTrailerSmallItemConfiguration.out service =" + trailerSmallItemConfigurationR);
        return trailerSmallItemConfigurationR;
    }

    public int getTrailerSmallLimitWeight() {
        int trailerSmallLimitWeightR;
        LogUtil.verbose("AutoSettingImpl", " getTrailerSmallLimitWeight.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                trailerSmallLimitWeightR = carVendorSettingManager.getTrailerSmallLimitWeightR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trailerSmallLimitWeightR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getTrailerSmallLimitWeight.out service =" + trailerSmallLimitWeightR);
            return trailerSmallLimitWeightR;
        }
        LogUtil.verbose("AutoSettingImpl", " getTrailerSmallLimitWeight.null");
        trailerSmallLimitWeightR = -1;
        LogUtil.verbose("AutoSettingImpl", " getTrailerSmallLimitWeight.out service =" + trailerSmallLimitWeightR);
        return trailerSmallLimitWeightR;
    }

    public int getTrayActionState() {
        int i;
        LogUtil.debug("AutoSettingImpl", " getTrayActionState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                i = carVendorSettingManager.getonTrayStateChanged();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
        } else {
            i = -1;
        }
        LogUtil.debug("AutoSettingImpl", " getTrayActionState.out = " + i);
        return i;
    }

    public int getTrayInitState() {
        LogUtil.verbose("AutoSettingImpl", "getTrayInitState");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557857709, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.debug("AutoSettingImpl", "getTrayInitState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTrayState() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int trayStateR = carVendorSettingManager.getTrayStateR();
            LogUtil.debug("AutoSettingImpl", "getTrayStateR=" + trayStateR);
            return trayStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getTurnCaliperSplintHeightDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float turnCaliperSplintHeightDriver = carVendorSettingManager.getTurnCaliperSplintHeightDriver();
            LogUtil.debug("AutoSettingImpl", "getTurnCaliperSplintHeightDriver=" + turnCaliperSplintHeightDriver);
            return turnCaliperSplintHeightDriver;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTurnCaliperSplintHeightLeftArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float turnCaliperSplintHeightBackLeft = carVendorSettingManager.getTurnCaliperSplintHeightBackLeft();
            LogUtil.debug("AutoSettingImpl", "getTurnCaliperSplintHeightBackLeft=" + turnCaliperSplintHeightBackLeft);
            return turnCaliperSplintHeightBackLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTurnCaliperSplintHeightPassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float turnCaliperSplintHeightCopilot = carVendorSettingManager.getTurnCaliperSplintHeightCopilot();
            LogUtil.debug("AutoSettingImpl", "getTurnCaliperSplintHeightCopilot=" + turnCaliperSplintHeightCopilot);
            return turnCaliperSplintHeightCopilot;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTurnCaliperSplintHeightRightArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float turnCaliperSplintHeightBackRight = carVendorSettingManager.getTurnCaliperSplintHeightBackRight();
            LogUtil.debug("AutoSettingImpl", "getTurnCaliperSplintHeightBackRight=" + turnCaliperSplintHeightBackRight);
            return turnCaliperSplintHeightBackRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTurnCushionHeightDriver() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float turnCushionHeightDriver = carVendorSettingManager.getTurnCushionHeightDriver();
            LogUtil.debug("AutoSettingImpl", "getTurnCushionHeightDriver=" + turnCushionHeightDriver);
            return turnCushionHeightDriver;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTurnCushionHeightLeftArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float turnCushionHeightBackLeft = carVendorSettingManager.getTurnCushionHeightBackLeft();
            LogUtil.debug("AutoSettingImpl", "getTurnCushionHeightBackLeft=" + turnCushionHeightBackLeft);
            return turnCushionHeightBackLeft;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTurnCushionHeightPassenger() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float turnCushionHeightCopilot = carVendorSettingManager.getTurnCushionHeightCopilot();
            LogUtil.debug("AutoSettingImpl", "getTurnCushionHeightCopilot=" + turnCushionHeightCopilot);
            return turnCushionHeightCopilot;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getTurnCushionHeightRightArea() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0.0f;
        }
        try {
            float turnCushionHeightBackRight = carVendorSettingManager.getTurnCushionHeightBackRight();
            LogUtil.debug("AutoSettingImpl", "getTurnCushionHeightBackRight=" + turnCushionHeightBackRight);
            return turnCushionHeightBackRight;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getUcuMassageLevelDeputy() {
        int ucuMassageLevelDeputyR;
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageLevelDeputy.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                ucuMassageLevelDeputyR = carVendorSettingManager.getUcuMassageLevelDeputyR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                ucuMassageLevelDeputyR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getUcuMassageLevelDeputy.out service =" + ucuMassageLevelDeputyR);
            return ucuMassageLevelDeputyR;
        }
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageLevelDeputy.null");
        ucuMassageLevelDeputyR = -1;
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageLevelDeputy.out service =" + ucuMassageLevelDeputyR);
        return ucuMassageLevelDeputyR;
    }

    public int getUcuMassageLevelMain() {
        int ucuMassageLevelMainR;
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageLevelMain.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                ucuMassageLevelMainR = carVendorSettingManager.getUcuMassageLevelMainR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                ucuMassageLevelMainR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getUcuMassageLevelMain.out service =" + ucuMassageLevelMainR);
            return ucuMassageLevelMainR;
        }
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageLevelMain.null");
        ucuMassageLevelMainR = -1;
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageLevelMain.out service =" + ucuMassageLevelMainR);
        return ucuMassageLevelMainR;
    }

    public int getUcuMassageModeDeputy() {
        int ucuMassageModeDeputyR;
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageModeDeputy.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                ucuMassageModeDeputyR = carVendorSettingManager.getUcuMassageModeDeputyR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                ucuMassageModeDeputyR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getUcuMassageModeDeputy.out service =" + ucuMassageModeDeputyR);
            return ucuMassageModeDeputyR;
        }
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageModeDeputy.null");
        ucuMassageModeDeputyR = -1;
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageModeDeputy.out service =" + ucuMassageModeDeputyR);
        return ucuMassageModeDeputyR;
    }

    public int getUcuMassageModeMain() {
        int ucuMassageModeMainR;
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageModeMain.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                ucuMassageModeMainR = carVendorSettingManager.getUcuMassageModeMainR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                ucuMassageModeMainR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getUcuMassageModeMain.out service =" + ucuMassageModeMainR);
            return ucuMassageModeMainR;
        }
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageModeMain.null");
        ucuMassageModeMainR = -1;
        LogUtil.verbose("AutoSettingImpl", " getUcuMassageModeMain.out service =" + ucuMassageModeMainR);
        return ucuMassageModeMainR;
    }

    public int getVoiceTipMessage() {
        int voiceTipMessageR;
        LogUtil.verbose("AutoSettingImpl", " getVoiceTipMessage.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                voiceTipMessageR = carVendorSettingManager.getVoiceTipMessageR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                voiceTipMessageR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getVoiceTipMessage.out=" + voiceTipMessageR);
            return voiceTipMessageR;
        }
        LogUtil.verbose("AutoSettingImpl", " getVoiceTipMessage.null == mCarSettingManager");
        voiceTipMessageR = -1;
        LogUtil.verbose("AutoSettingImpl", " getVoiceTipMessage.out=" + voiceTipMessageR);
        return voiceTipMessageR;
    }

    public int getVoiceTipsMessage() {
        int voiceTipsMessageR;
        LogUtil.verbose("AutoSettingImpl", " getVoiceTipsMessage.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                voiceTipsMessageR = carVendorSettingManager.getVoiceTipsMessageR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                voiceTipsMessageR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getVoiceTipsMessage.out=" + voiceTipsMessageR);
            return voiceTipsMessageR;
        }
        LogUtil.verbose("AutoSettingImpl", " getVoiceTipsMessage.null == mCarSettingManager");
        voiceTipsMessageR = -1;
        LogUtil.verbose("AutoSettingImpl", " getVoiceTipsMessage.out=" + voiceTipsMessageR);
        return voiceTipsMessageR;
    }

    public int getVspState() {
        LogUtil.debug("AutoSettingImpl", "getVSPState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int vspStateR = carVendorSettingManager.getVspStateR();
            LogUtil.debug("AutoSettingImpl", "getVspStateR=" + vspStateR);
            return vspStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWadingPatternSocTips() {
        int wadingPatternSocTipsR;
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternSocTips.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                wadingPatternSocTipsR = carVendorSettingManager.getWadingPatternSocTipsR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                wadingPatternSocTipsR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getWadingPatternSocTips.out=" + wadingPatternSocTipsR);
            return wadingPatternSocTipsR;
        }
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternSocTips.null == mCarSettingManager");
        wadingPatternSocTipsR = -1;
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternSocTips.out=" + wadingPatternSocTipsR);
        return wadingPatternSocTipsR;
    }

    public int getWadingPatternSpeedTips() {
        int wadingPatternSpeedTipsR;
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternSpeedTips.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                wadingPatternSpeedTipsR = carVendorSettingManager.getWadingPatternSpeedTipsR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                wadingPatternSpeedTipsR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getWadingPatternSpeedTips.out=" + wadingPatternSpeedTipsR);
            return wadingPatternSpeedTipsR;
        }
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternSpeedTips.null == mCarSettingManager");
        wadingPatternSpeedTipsR = -1;
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternSpeedTips.out=" + wadingPatternSpeedTipsR);
        return wadingPatternSpeedTipsR;
    }

    public int getWadingPatternStateTips() {
        int wadingPatternStateTipsR;
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternStateTips.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                wadingPatternStateTipsR = carVendorSettingManager.getWadingPatternStateTipsR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                wadingPatternStateTipsR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getWadingPatternStateTips.out=" + wadingPatternStateTipsR);
            return wadingPatternStateTipsR;
        }
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternStateTips.null == mCarSettingManager");
        wadingPatternStateTipsR = -1;
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternStateTips.out=" + wadingPatternStateTipsR);
        return wadingPatternStateTipsR;
    }

    public int getWadingPatternTestTips() {
        int wadingPatternTestTipsR;
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternTestTips.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                wadingPatternTestTipsR = carVendorSettingManager.getWadingPatternTestTipsR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                wadingPatternTestTipsR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getWadingPatternTestTips.out=" + wadingPatternTestTipsR);
            return wadingPatternTestTipsR;
        }
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternTestTips.null == mCarSettingManager");
        wadingPatternTestTipsR = -1;
        LogUtil.verbose("AutoSettingImpl", " getWadingPatternTestTips.out=" + wadingPatternTestTipsR);
        return wadingPatternTestTipsR;
    }

    public int getWashElectricPedal() {
        int washElectricPedalR;
        LogUtil.verbose("AutoSettingImpl", " getWashElectricPedal.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                washElectricPedalR = carVendorSettingManager.getWashElectricPedalR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                washElectricPedalR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getWashElectricPedal.out service =" + washElectricPedalR);
            return washElectricPedalR;
        }
        LogUtil.verbose("AutoSettingImpl", " getWashElectricPedal.null");
        washElectricPedalR = -1;
        LogUtil.verbose("AutoSettingImpl", " getWashElectricPedal.out service =" + washElectricPedalR);
        return washElectricPedalR;
    }

    public int getWelcomeActive() {
        LogUtil.debug("AutoSettingImpl", "getWelcomeActive is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int welcomeActiveR = carVendorSettingManager.getWelcomeActiveR();
            LogUtil.debug("AutoSettingImpl", "getWelcomeActiveR=" + welcomeActiveR);
            return welcomeActiveR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWelcomeState() {
        LogUtil.debug("AutoSettingImpl", "getWelcomeState is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int welcomeStateR = carVendorSettingManager.getWelcomeStateR();
            LogUtil.debug("AutoSettingImpl", "getWelcomeStateR=" + welcomeStateR);
            return welcomeStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWelcomeSupported() {
        LogUtil.debug("AutoSettingImpl", "getWelcomeSupported is execute");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            int welcomeSupportedR = carVendorSettingManager.getWelcomeSupportedR();
            LogUtil.debug("AutoSettingImpl", "getWelcomeSupportedR=" + welcomeSupportedR);
            return welcomeSupportedR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getWorkState() {
        int workStateR;
        LogUtil.verbose("AutoSettingImpl", " getWorkState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                workStateR = carVendorSettingManager.getWorkStateR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                workStateR = -1;
            }
            LogUtil.verbose("AutoSettingImpl", " getWorkState.out=" + workStateR);
            return workStateR;
        }
        LogUtil.verbose("AutoSettingImpl", " getWorkState.null == mCarSettingManager");
        workStateR = -1;
        LogUtil.verbose("AutoSettingImpl", " getWorkState.out=" + workStateR);
        return workStateR;
    }

    public int hasFeature(String str) {
        return 0;
    }

    public int ialDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "ialDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setIalDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int intelligentDriveDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "intelligentDriveDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setIntelligentDriveDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int isSomethingLeftInRefrigerator() {
        int somethingLeftInRefrigeratorR;
        LogUtil.debug("AutoSettingImpl", " isSomethingLeftInRefrigerator.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                somethingLeftInRefrigeratorR = carVendorSettingManager.getSomethingLeftInRefrigeratorR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                somethingLeftInRefrigeratorR = -1;
            }
        } else {
            somethingLeftInRefrigeratorR = -1;
        }
        LogUtil.debug("AutoSettingImpl", " isSomethingLeftInRefrigerator.out = " + somethingLeftInRefrigeratorR);
        return somethingLeftInRefrigeratorR;
    }

    public int laneAssistDriveDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "laneAssistDriveDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLaneAssistDriveDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int languageDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "languageDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLanguageDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int lightDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "lightDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLightDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int locksDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "locksDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLocksDefaultResetS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void n(CarVendorSettingManager carVendorSettingManager, CarVendorAssistManager carVendorAssistManager) {
        this.e = carVendorSettingManager;
        this.f = carVendorAssistManager;
    }

    public int newEnergyDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "newEnergyDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setNewEnergyDefaultResetS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int notifyEnterTunnel(int i) {
        LogUtil.debug("AutoSettingImpl", "notifyEnterTunnel is execute=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setnotifyEnterTunnelS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int notifyTunnelLength(int i) {
        LogUtil.debug("AutoSettingImpl", "notifyTunnelLength is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setNotifyTunnelLengthS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int outChargeDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "outChargeDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setOutChargeDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int rearMirrorDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "rearMirrorDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRearMirrorDefaultResetS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int resetAutoExternalRearMirrorFollowUp() {
        LogUtil.verbose("AutoSettingImpl", "resetAutoExternalRearMirrorFollowUp");
        return 0;
    }

    public int resetAutoPowerOnState() {
        return 0;
    }

    public void resetCaliperSplintHeight(int i, int i2) {
    }

    public void resetCushionHeight(int i, int i2) {
    }

    public int resetHeadrestParms(int i, int i2) {
        return 0;
    }

    public int resetHudBrightness() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetHudState(0);
                LogUtil.debug("AutoSettingImpl", "resetHudBrightness");
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void resetHudDisplayContent() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetHudState(0);
                LogUtil.debug("AutoSettingImpl", "resetHudDisplayContent");
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int resetHudHeight() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetHudState(0);
                LogUtil.debug("AutoSettingImpl", "resetHudHeight");
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int resetHudMode() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetHudState(0);
                LogUtil.debug("AutoSettingImpl", "resetHudMode");
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void resetHudRotationAngle() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetHudState(0);
                LogUtil.debug("AutoSettingImpl", "resetHudRotationAngle");
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int resetHudState() {
        LogUtil.debug("AutoSettingImpl", "resetHudState");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetHudState(0);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int resetMotorPretensionerSeatbelt() {
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetHudState(0);
                LogUtil.debug("AutoSettingImpl", "resetMotorPretensionerSeatbelt");
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int resetRearviewMirrorIsSets(int i) {
        LogUtil.verbose("AutoSettingImpl", " resetRearviewMirrorIsSets.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetRearviewMirrorIsSetS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " resetRearviewMirrorIsSets.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " resetRearviewMirrorIsSets.out=0");
        return 0;
    }

    public void resetRearviewMirrorParams(int i, int i2) {
    }

    public void resetRemenberPowerOff() {
        LogUtil.debug("AutoSettingImpl", "resetRemenberPowerOff");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetHudState(0);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetSeatParamsDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "resetSeatParamsDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetSeatParamsDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetSeatParamsLeftArea(int i) {
        LogUtil.debug("AutoSettingImpl", "resetSeatParamsLeftArea" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetSeatParamsBackLeft(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetSeatParamsPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "resetSeatParamsPassenger" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetSeatParamsCopilot(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetSeatParamsRightArea(int i) {
        LogUtil.debug("AutoSettingImpl", "resetSeatParamsRightArea" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setresetSeatParamsBackRight(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int resetSetting(int[] iArr, int[] iArr2) {
        return 0;
    }

    public void resetSteeringWheelPosition(int i) {
    }

    public int restoreSettingItem(int i) {
        LogUtil.error("AutoSettingImpl", "restoreSettingItem=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setFactoryResetAll(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int safeAssistDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "safeAssistDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSafeAssistDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int saveOwnerState(int i) {
        LogUtil.verbose("AutoSettingImpl", " saveOwnerState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.seteOwnerStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " saveOwnerState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " saveOwnerState.out=0");
        return 0;
    }

    public int saveSeatParamsAllDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "saveSeatParamsAllDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setsaveSeatParamsAllDriver(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int saveSeatParamsAllLeftRear(int i) {
        LogUtil.debug("AutoSettingImpl", "saveSeatParamsAllLeftRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setsaveSeatParamsAllBackLeft(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int saveSeatParamsAllPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "saveSeatParamsAllPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setsaveSeatParamsAllCopilot(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int saveSeatParamsAllRightRear(int i) {
        LogUtil.debug("AutoSettingImpl", "saveSeatParamsAllRightRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setsaveSeatParamsAllBackRight(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sendOffReminder(int i) {
        return 0;
    }

    public int set5LevelSeatHeatingDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingLevelDomainS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingLeft(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingLeft=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingLevelBackLeftS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingModeDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingModeDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingModeDomainS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingModeLeft(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingModeLeft=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingModeBackLeftS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingModePassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingModePassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingModePassengerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingModeRight(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingModeRight=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingModeBackRightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingLevelPassengerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingRight(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingRight=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingLevelBackRightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingStateColipot(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingStateColipot=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingStatePassengerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingStateDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingStateDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingStateDomainS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingStateLeft(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingStateLeft=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingStateBackLeftS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatHeatingStateRight(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatHeatingStateRight=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatHeatingStateBackRightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingLevelDomainS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingLeft(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingLeft=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingLevelBackLeftS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingModeDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingModeDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingModeDomainS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingModeLeft(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingModeLeft=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingModeBackLeftS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingModePassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingModePassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingModePassengerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingModeRight(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingModeRight=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingModeBackRightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingLevelPassengerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingRight(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingRight=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingLevelBackRightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingStateColipot(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingStateColipot=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingStatePassengerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingStateDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingStateDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingStateDomainS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingStateLeft(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingStateLeft=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingStateBackLeftS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int set5LevelSeatVentilatingStateRight(int i) {
        LogUtil.debug("AutoSettingImpl", "set5LevelSeatVentilatingStateRight=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.set5LevelSeatVentilatingStateBackRightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setACAutoAir(int i) {
        LogUtil.debug("AutoSettingImpl", "setACAutoAir" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setACAutoAirS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setACBTWind(int i) {
        LogUtil.debug("AutoSettingImpl", "setACBTWind" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setACBTWindSX31(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setACBTWindS4E3(int i) {
        LogUtil.debug("AutoSettingImpl", "setACBTWindS4E3" + i);
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose("AutoSettingImpl", "setACBTWindS4E3 in");
            this.e.setACBTWindS4E3(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setACBTWindSX28(int i) {
        LogUtil.debug("AutoSettingImpl", "setACBTWindSX28" + i);
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose("AutoSettingImpl", "setACBTWindSX28 in");
            this.e.setACBTWindSX28(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setACPauseCycle(int i) {
        LogUtil.debug("AutoSettingImpl", "setACPauseCycle" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setACPauseCycleS4E3(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setACPauseCycleS4EF(int i) {
        LogUtil.debug("AutoSettingImpl", "setACPauseCycleS4EF" + i);
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose("AutoSettingImpl", "setACPauseCycleS4EF +in");
            this.e.setACPauseCycleS4EF(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setACTunnelCycle(int i) {
        LogUtil.verbose("AutoSettingImpl", "setACTunnelCycle");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setACTunnelCycleS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAcTemperatureUnit(int i) {
        LogUtil.verbose("AutoSettingImpl", "setAcTemperatureUnit");
        return 0;
    }

    public int setAcWarmMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setAcWarmMode" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAcWarmModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAdasRest(int[] iArr) {
        LogUtil.debug("AutoSettingImpl", " setAdasRest.in" + Arrays.toString(iArr));
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            LogUtil.error("AutoSettingImpl", " setAdasRest.null == mCarSettingManager");
            return 0;
        }
        try {
            carVendorSettingManager.setAdasRestS(iArr);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAfsSwitchSate(int i) {
        LogUtil.info("AutoSettingImpl", "setAfsSwitchSate" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAfsSwitchSate(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAidedDrivingInd(int i) {
        LogUtil.verbose("AutoSettingImpl", "Service setAidedDrivingInd");
        return 0;
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        LogUtil.debug("AutoSettingImpl", "setAmbientMulticolorMode=" + i + "," + i2);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAmbientMulticolorModeS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAmbientMulticolorState(int i, int i2) {
        LogUtil.debug("AutoSettingImpl", "setAmbientMulticolorState=" + i + "," + i2);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAmbientMulticolorStateS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAmbientMusicMode(int i, int i2) {
        LogUtil.debug("AutoSettingImpl", "setAmbientMusicMode=" + i + "," + i2);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAmbientMusicModeS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAmbientMusicModeState(int i, int i2) {
        LogUtil.debug("AutoSettingImpl", "setAmbientMusicModeState=" + i + "," + i2);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAmbientMusicModeStateS(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAppUseLimitState(int i) {
        LogUtil.verbose("AutoSettingImpl", "setAppUseLimitState");
        return 0;
    }

    public int setAutoExternalRearMirrorFollowUpSwitch(int i) {
        LogUtil.debug("AutoSettingImpl", "setAutoExternalRearMirrorFollowUpSwitch" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAutoExternalRearMirrorFollowUpSwitchS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAutoPowerOffState(int i) {
        LogUtil.debug("AutoSettingImpl", "setAutoPowerOffState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAutoPowerOffStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAutoPowerOnState(int i) {
        LogUtil.debug("AutoSettingImpl", "setAutoPowerOnState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAutoPowerOnStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAutoPurification(int i) {
        LogUtil.debug("AutoSettingImpl", "setAutoPurification" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAutoPurification(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAutoRainWiperState(int i) {
        LogUtil.debug("AutoSettingImpl", "setAutoRainWiperState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setAutoRainWiperStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setAutomaticParking(int i) {
        return 0;
    }

    public int setBackDoorElectricMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setBackDoorElectricMode" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBackDoorElectricModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBackDoorOpenedHeight(int i) {
        LogUtil.debug("AutoSettingImpl", "setBackDoorOpenedHeight" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBackDoorOpenedHeightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBackHomeLightDelay(int i) {
        LogUtil.debug("AutoSettingImpl", "setBackHomeLightDelay=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBackHomeLightDelayValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBackHomeLightDelayValue(int i) {
        LogUtil.debug("AutoSettingImpl", "setBackHomeLightDelayValue" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBackHomeLightDelayValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBleOpenDriverDoorState(int i) {
        LogUtil.debug("AutoSettingImpl", "setBleOpenDriverDoorState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBleOpenDriverDoorStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBrakeCloseDoor(int i) {
        LogUtil.debug("AutoSettingImpl", "setBrakeCloseDoor is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBrakeCloseDoorS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBrakePedalMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setBrakePedalMode" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBrakePedalModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setBsisCollisionStateS(int i) {
        LogUtil.debug("AutoSettingImpl", "setBsisCollisionStateS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setBsisCollisionStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCallState(int i) {
        LogUtil.info("AutoSettingImpl", " setCallState.in" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setCallStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        LogUtil.debug("AutoSettingImpl", " setCallState.out = " + i);
        return 0;
    }

    public int setCamping(int i) {
        LogUtil.debug("AutoSettingImpl", "setCamping=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setCampingS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCampingBlanceState(int i) {
        LogUtil.debug("AutoSettingImpl", "setCampingBlanceState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setCampingBlanceStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCanopyAutomaticDimming(int i) {
        LogUtil.verbose("AutoSettingImpl", " setCanopyAutomaticDimming.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setCanopyAutomaticDimmingS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setCanopyAutomaticDimming.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setCanopyAutomaticDimming.out=0");
        return 0;
    }

    public int setCarDoorTipsSoundType(int i) {
        LogUtil.verbose("AutoSettingImpl", " setCarDoorTipsSoundType.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setCarDoorTipsSoundTypeS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setCarDoorTipsSoundType.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setCarDoorTipsSoundType.out=0");
        return 0;
    }

    public int setCarTipsSoundType(int i) {
        LogUtil.verbose("AutoSettingImpl", "setCarTipsSoundType");
        return 0;
    }

    public int setCarsettingRest(int[] iArr) {
        LogUtil.debug("AutoSettingImpl", " setCarsettingRest.in" + Arrays.toString(iArr));
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            LogUtil.error("AutoSettingImpl", " setCarsettingRest.null == mCarSettingManager");
            return 0;
        }
        try {
            carVendorSettingManager.setCarsettingRestS(iArr);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setChargingPort(int i) {
        LogUtil.debug("AutoSettingImpl", "setChargingPort" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setChargingPortS4E3(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCheckSum(int i) {
        LogUtil.debug("AutoSettingImpl", "setCheckSum is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setCheckSumS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCloseDoorLevel(int i) {
        LogUtil.debug("AutoSettingImpl", "setCloseDoorLevel is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setCloseDoorLevelS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCloudServiceStatus(int i) {
        int i2;
        LogUtil.verbose("AutoSettingImpl", " setCloudServiceStatus.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            i2 = 1;
            try {
                carVendorSettingManager.setCloudServiceStatusS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setCloudServiceStatus.null");
            i2 = 0;
        }
        LogUtil.verbose("AutoSettingImpl", " setCloudServiceStatus.out service =" + i2);
        return i2;
    }

    public int setComSpeedDownState(int i) {
        LogUtil.debug("AutoSettingImpl", "setComSpeedDownState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setComSpeedDownStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setComSpeedUpState(int i) {
        LogUtil.debug("AutoSettingImpl", "setComSpeedUpState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setComSpeedUpStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setComfortableParkingState(int i) {
        LogUtil.debug("AutoSettingImpl", "setComfortableParkingState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setComfortableParkingStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setCpdImsSwitchState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setCpdImsSwitchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setCpdImsSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setCpdImsSwitchState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setCpdImsSwitchState.out=0");
        return 0;
    }

    public int setCuraSwitchState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setCuraSwitchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        int i2 = 0;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setCuraSwitchStateS(i);
                i2 = 1;
                LogUtil.verbose("AutoSettingImpl", " setCuraSwitchState.null == mCarSettingManager");
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
            LogUtil.verbose("AutoSettingImpl", " setCuraSwitchState.out=" + i2);
        }
        return i2;
    }

    public int setCvcMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setCvcMode is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setCvcModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDampState(int i) {
        LogUtil.debug("AutoSettingImpl", "setDampState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDampStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDelayPowerDownState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setDelayPowerDownStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownState.out=0");
        return 0;
    }

    public int setDelayPowerDownStateTime(int i) {
        LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownStateTime.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setDelayPowerDownStateTimeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownStateTime.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownStateTime.out=0");
        return 0;
    }

    public int setDelayPowerDownTime(int i) {
        LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownTime.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setDelayPowerDownTimeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownTime.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setDelayPowerDownTime.out=0");
        return 0;
    }

    public int setDelayPowerOffTime(int i) {
        LogUtil.verbose("AutoSettingImpl", " setDelayPowerOffTime.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setDelayPowerOffTimeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setDelayPowerOffTime.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setDelayPowerOffTime.out=0");
        return 0;
    }

    public int setDeviceInsideEnable(boolean z, int i, int i2) {
        LogUtil.debug("AutoSettingImpl", "setDeviceInsideEnable=" + z + "," + i + "," + i2);
        return 0;
    }

    public int setDisinfState(int i) {
        LogUtil.debug("AutoSettingImpl", "setDisinfState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDisinfStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDlinkRest(int[] iArr) {
        LogUtil.debug("AutoSettingImpl", " setDlinkRest.in" + Arrays.toString(iArr));
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            LogUtil.error("AutoSettingImpl", " setDlinkRest.null == mCarSettingManager");
            return 0;
        }
        try {
            carVendorSettingManager.setDlinkRestS(iArr);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDoorknobAutoShrinkState(int i) {
        LogUtil.debug("AutoSettingImpl", "setDoorknobAutoShrinkState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDoorknobAutoShrinkStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDriveDoorCloseInd(int i) {
        return 0;
    }

    public int setDriverSeatAutoReturn(int i) {
        LogUtil.debug("AutoSettingImpl", "setDriverSeatAutoReturn" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDriverSeatAutoReturnS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDriverSeatState(int i) {
        LogUtil.info("AutoSettingImpl", "setDriverSeatState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDriverSeatStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDyHeightByVoice(int i) {
        LogUtil.debug("AutoSettingImpl", "setDyHeightByVoice is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDyHeightByVoiceS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDyHeightToggleState(int i) {
        LogUtil.debug("AutoSettingImpl", "setDyHeightToggleState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDyHeightToggleStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDyHeightValue(int i) {
        LogUtil.debug("AutoSettingImpl", "setDyHeightValue is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDyHeightValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setDynamicSuspensionMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setDynamicSuspensionMode=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setDynamicSuspensionModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setElectricTailAutoModeVoiceSwitchState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setElectricTailAutoModeVoiceSwitchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setElectricTailAutoModeVoiceSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setElectricTailAutoModeVoiceSwitchState == null");
        }
        LogUtil.verbose("AutoSettingImpl", " setElectricTailAutoModeVoiceSwitchState.out=0");
        return 0;
    }

    public int setElectricTailAutomaticModeSwitchState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setElectricTailAutomaticModeSwitchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setElectricTailAutomaticModeSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setElectricTailAutomaticModeSwitchState == null");
        }
        LogUtil.verbose("AutoSettingImpl", " setElectricTailAutomaticModeSwitchState.out=0");
        return 0;
    }

    public int setEnergyFeedback(int i) {
        LogUtil.debug("AutoSettingImpl", "setEnergyFeedback" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setEnergyFeedbackS4E3(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setEngineSoundType(int i) {
        LogUtil.debug("AutoSettingImpl", "setEngineSoundType" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setEngineSoundTypeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setExhibitionStartEnginePermit(int i) {
        LogUtil.debug("AutoSettingImpl", "setExhibitionStartEnginePermit=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setExhibitionStartEnginePermitS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setFlSeatBasinSwitchState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setFlSeatBasinSwitchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setflSeatBasinSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setFlSeatBasinSwitchState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setFlSeatBasinSwitchState.out=0");
        return 0;
    }

    public int setFridgeChildLock(int i) {
        LogUtil.verbose("AutoSettingImpl", " setFridgeChildLock.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setFridgeChildLockS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setFridgeChildLock.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setFridgeChildLock.out=0");
        return 0;
    }

    public int setFridgeDoorState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setFridgeDoorState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setFridgeDoorStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setFridgeDoorState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setFridgeDoorState.out=0");
        return 0;
    }

    public int setFrontPassengerSeatSeatPotSwitchState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setFrontPassengerSeatSeatPotSwitchState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setFrontPassengerSeatSeatPotSwitchStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setFrontPassengerSeatSeatPotSwitchState == null");
        }
        LogUtil.verbose("AutoSettingImpl", " setFrontPassengerSeatSeatPotSwitchState.out=0");
        return 0;
    }

    public int setGoalRoadMode(int i) {
        LogUtil.verbose("AutoSettingImpl", " setGoalRoadMode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setGoalRoadModeS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setGoalRoadMode.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setGoalRoadMode.out=0");
        return 0;
    }

    public int setHSpeedAutoCloseState(int i) {
        LogUtil.debug("AutoSettingImpl", "setHSpeedAutoCloseState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHSpeedAutoCloseStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHeadlampLevel(int i) {
        LogUtil.debug("AutoSettingImpl", "setHeadlampLevel=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHeadlampLevelS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHsMassageStateDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "setHsMassageStateDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHsMassageStateDomainS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHsMassageStateLeft(int i) {
        LogUtil.debug("AutoSettingImpl", "setHsMassageStateLeft=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHsMassageStateBackLeftS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHsMassageStatePassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "setHsMassageStatePassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHsMassageStatePassengerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHsMassageStateRight(int i) {
        LogUtil.debug("AutoSettingImpl", "setHsMassageStateRight=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHsMassageStateBackRightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudBrightness(int i) {
        LogUtil.debug("AutoSettingImpl", "setRainAutoLockWindowState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudBrightnessS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudDisplayContentDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudDisplayContentDriver" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudDisplayContentSDrive(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudDisplayContentNavigation(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudDisplayContentNavigation" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudDisplayContentSNavigation(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudHeight(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudHeight=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudHeightS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudMode" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudNavigationState(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudNavigationState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudNavigationStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudParamsEffect() {
        LogUtil.verbose("AutoSettingImpl", "setHudParamsEffect");
        return 0;
    }

    public int setHudPattern(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudPattern=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudPatternS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudRotationAngle(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudRotationAngle" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudRotationAngleS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudSafeDriveState(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudSafeDriveState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudSafeDriveStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudScreenProjectionState(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudScreenProjectionState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudScreenProjectionStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setHudState(int i) {
        LogUtil.debug("AutoSettingImpl", "setHudState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setHudStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIALArea(int i, int i2) {
        LogUtil.debug("AutoSettingImpl", "setIALArea=" + i + "," + i2);
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose("AutoSettingImpl", "setIALArea +in");
            this.e.setIALColorAdjustS(i);
            this.e.setIALAreaDifferentWays(i2);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIALBrightness(int i, int i2, int i3) {
        LogUtil.debug("AutoSettingImpl", "setIALBrightness=" + i + "," + i2 + "," + i3);
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose("AutoSettingImpl", "setIALBrightness +in");
            this.e.setIALColorAdjustS(i);
            this.e.setIALBrightnessS(i2);
            this.e.setIALAreaDifferentWays(i3);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIALColor(int i, int i2, int i3) {
        LogUtil.debug("AutoSettingImpl", "setIALColor=" + i + "," + i2 + "," + i3);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setIALColorAdjustS(i);
            this.e.setIALColorS(i2);
            this.e.setIALAreaDifferentWays(i3);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIALInfo(int i, int i2, int i3, int i4) {
        LogUtil.debug("AutoSettingImpl", "setIALInfo=" + i + "," + i2 + "," + i3 + "," + i4);
        if (this.e == null) {
            return 0;
        }
        try {
            LogUtil.verbose("AutoSettingImpl", "setIALInfo +in");
            this.e.setIALColorS(i2);
            this.e.setIALBrightnessS(i3);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setINSTheme(int i) {
        LogUtil.debug("AutoSettingImpl", "setINSTheme=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setINSThemeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIceBoxTriggerSource(int i) {
        LogUtil.verbose("AutoSettingImpl", " setIceBoxTriggerSource.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setIceBoxTriggerSourceS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setIceBoxTriggerSource.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setIceBoxTriggerSource.out=0");
        return 0;
    }

    public int setInsideLightDoorState(int i) {
        LogUtil.debug("AutoSettingImpl", "setInsideLightDoorState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setInsideLightDoorStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setInsideLightState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setInsideLightState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setInsideLightStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setInsideLightState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setInsideLightState.out=0");
        return 0;
    }

    public int setIntelLockOpenDriverDoor(int i) {
        LogUtil.debug("AutoSettingImpl", "setIntelLockOpenDriverDoor is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setIntelLockOpenDriverDoorS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIntelligentAcAreaState(int i) {
        LogUtil.debug("AutoSettingImpl", "setIntelligentAcAreaState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setIntelligentAcAreaStateR(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIntelligentLockState(int i) {
        LogUtil.debug("AutoSettingImpl", "setIntelligentLockState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setIntelligentLockStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIntelligentUnlockState(int i) {
        LogUtil.debug("AutoSettingImpl", "setIntelligentUnlockState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setIntelligentUnlockStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setInternationalLanguage(int i) {
        LogUtil.debug("AutoSettingImpl", "setInternationalLanguage=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setInternationalLanguageS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setIviProfileSettingsDm(int i) {
        int i2;
        LogUtil.verbose("AutoSettingImpl", " setIviProfileSettingsDm.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            i2 = 1;
            try {
                carVendorSettingManager.setIviProfileSettingsDmS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setIviProfileSettingsDm.null");
            i2 = 0;
        }
        LogUtil.verbose("AutoSettingImpl", " setIviProfileSettingsDm.out service =" + i2);
        return i2;
    }

    public int setIviProfileSettingsR(int i) {
        LogUtil.debug("AutoSettingImpl", "setIviProfileSettingsR=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setIviProfileSettingsR(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setKeyPowerLowInd(int i) {
        return 0;
    }

    public int setKeyVibratorLevel(int i) {
        LogUtil.debug("AutoSettingImpl", "setKeyVibratorLevel=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setKeyVibratorLevelS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setKeypadToneState(int i) {
        return 0;
    }

    public void setKeypadToneStatechanged(int i) {
    }

    public int setLandAdvanceAtState(int i) {
        LogUtil.verbose("AutoSettingImpl", "setLandAdvanceAtState");
        return 0;
    }

    public int setLanguage(int i) {
        LogUtil.debug("AutoSettingImpl", "setLanguage=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLanguageS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLanternLanguageMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setLanternLanguageMode=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLanternLanguageModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLeaveCarPInd(int i) {
        return 0;
    }

    public int setLeftHomeLightDelay(int i) {
        LogUtil.debug("AutoSettingImpl", "setLeftHomeLightDelay" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLeftHomeLightDelayValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLeftHomeLightDelayValue(int i) {
        LogUtil.debug("AutoSettingImpl", "setLeftHomeLightDelayValue=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLeftHomeLightDelayValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLeftRseStateS(int i) {
        LogUtil.debug("AutoSettingImpl", "setLeftRseStateS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLeftRseStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLeftRseSwitchCommandS(int i) {
        LogUtil.debug("AutoSettingImpl", "setLeftRseSwitchCommandS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLeftRseSwitchCommandS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLiftModeState(int i) {
        LogUtil.debug("AutoSettingImpl", "setLiftModeState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLiftModeStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLockCarRiseWindow(int i) {
        LogUtil.debug("AutoSettingImpl", "setLockCarRiseWindow" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLockCarRiseWindowS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLockOff(int i) {
        LogUtil.debug("AutoSettingImpl", "setLockOff" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLockOffS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLowBatteryInd(int i) {
        return 0;
    }

    public int setLowOilInd(int i) {
        return 0;
    }

    public int setLrseChildAreaStateS(int i) {
        LogUtil.debug("AutoSettingImpl", "setLrseChildAreaStateS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLrseChildAreaStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLrseChildModeCode1stByteS(int i) {
        LogUtil.debug("AutoSettingImpl", "setLrseChildModeCode1stByteS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLrseChildModeCode1stByteS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLrseChildModeCode2ndByteS(int i) {
        LogUtil.debug("AutoSettingImpl", "setLrseChildModeCode2ndByteS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLrseChildModeCode2ndByteS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLrseChildModeCode3rdByteS(int i) {
        LogUtil.debug("AutoSettingImpl", "setLrseChildModeCode3rdByteS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLrseChildModeCode3rdByteS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLrseChildModeCode4thByteS(int i) {
        LogUtil.debug("AutoSettingImpl", "setLrseChildModeCode4thByteS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLrseChildModeCode4thByteS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setLrseChildModeOpenTimeS(int i) {
        LogUtil.debug("AutoSettingImpl", "setLrseChildModeOpenTimeS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setLrseChildModeOpenTimeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMaintainRemindState(int i) {
        LogUtil.verbose("AutoSettingImpl", "setMaintainRemindState");
        return 0;
    }

    public int setMassageLevelDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "setMassageLevelDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMassageLevel4C1(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMassageLevelLeftRear(int i) {
        LogUtil.debug("AutoSettingImpl", "setMassageLevelLeftRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMassageLevel4C3(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMassageLevelPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "setMassageLevelPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMassageLevel4C2(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMassageLevelRightRear(int i) {
        LogUtil.debug("AutoSettingImpl", "setMassageLevelRightRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMassageLevel4C4(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMassageModeDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "setMassageModeDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMassageModeS4C1(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMassageModeLeftRear(int i) {
        LogUtil.debug("AutoSettingImpl", "setMassageModeLeftRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMassageModeS4C3(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMassageModePassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "setMassageModePassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMassageModeS4C2(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMassageModeRightRear(int i) {
        LogUtil.debug("AutoSettingImpl", "setMassageModeRightRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMassageModeS4C4(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMeditationMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setMeditationMode=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMeditationModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMessageCounter(int i) {
        LogUtil.debug("AutoSettingImpl", "setMessageCounter is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMessageCounterS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMicroSwitchLockWindowState(int i) {
        LogUtil.debug("AutoSettingImpl", "setMicroSwitchLockWindowState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMicroSwitchLockWindowStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMicroSwitchUnlockWindowState(int i) {
        LogUtil.debug("AutoSettingImpl", "setMicroSwitchUnlockWindowState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMicroSwitchUnlockWindowStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMissKeyInd(int i) {
        return 0;
    }

    public int setMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setMode=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setModeBaby(int i) {
        LogUtil.debug("AutoSettingImpl", "setModeBaby=" + i);
        return 0;
    }

    public int setModeCamping(int i) {
        LogUtil.debug("AutoSettingImpl", "setModeCamping=" + i);
        return 0;
    }

    public int setModePets(int i) {
        LogUtil.debug("AutoSettingImpl", "setModePets=" + i);
        return 0;
    }

    public int setModeRest(int i) {
        LogUtil.debug("AutoSettingImpl", "setModeRest=" + i);
        return 0;
    }

    public int setModeRomantic(int i) {
        LogUtil.debug("AutoSettingImpl", "setModeRomantic=" + i);
        return 0;
    }

    public int setMoisAssembleStateS(int i) {
        LogUtil.debug("AutoSettingImpl", "setMoisAssembleStateS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMoisAssembleStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setMotorPretensionerSeatbeltState(int i) {
        LogUtil.debug("AutoSettingImpl", "setMotorPretensionerSeatbeltState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setMotorPretensionerSeatbeltStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setNavigationMediaState(int i) {
        LogUtil.verbose("AutoSettingImpl", "setNavigationMediaState");
        return 0;
    }

    public int setNetworkSecurityStatus(int i) {
        int i2;
        LogUtil.verbose("AutoSettingImpl", " setNetworkSecurityStatus.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            i2 = 1;
            try {
                carVendorSettingManager.setNetworkSecurityStatusS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setNetworkSecurityStatus.null");
            i2 = 0;
        }
        LogUtil.verbose("AutoSettingImpl", " setNetworkSecurityStatus.out service =" + i2);
        return i2;
    }

    public int setNfcOpenDriverDoor(int i) {
        LogUtil.debug("AutoSettingImpl", "setNfcOpenDriverDoor is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setNfcOpenDriverDoorS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setNightDRLightOnInd(int i) {
        return 0;
    }

    public int setOpeningAngleInformationSetOnTheR(int i) {
        LogUtil.verbose("AutoSettingImpl", " setOpeningAngleInformationSetOnTheR.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setOpeningAngleInformationSetOnTheRS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setOpeningAngleInformationSetOnTheR.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setOpeningAngleInformationSetOnTheR.out=0");
        return 0;
    }

    public int setOptimizationStatus(int i) {
        int i2;
        LogUtil.verbose("AutoSettingImpl", " setOptimizationStatus.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            i2 = 1;
            try {
                carVendorSettingManager.setOptimizationStatusS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setOptimizationStatus.null");
            i2 = 0;
        }
        LogUtil.verbose("AutoSettingImpl", " setOptimizationStatus.out service =" + i2);
        return i2;
    }

    public int setOtaMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setOtaMode is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setOtaModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setOutsideLightStateScene(int i) {
        LogUtil.verbose("AutoSettingImpl", " setOutsideLightStateScene.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setOutsideLightStateSceneS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setOutsideLightStateScene.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setOutsideLightStateScene.out=0");
        return 0;
    }

    public int setOverspeedLock(int i) {
        LogUtil.debug("AutoSettingImpl", "setOverspeedLock" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setOverspeedLockS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPM25Power(int i) {
        LogUtil.debug("AutoSettingImpl", "setPM25Power" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setPM25PowerS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPM25SwitchCheck(int i) {
        LogUtil.debug("AutoSettingImpl", "setPM25SwitchCheck" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setPM25SwitchCheckS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPM25TimeCheck(int i) {
        LogUtil.debug("AutoSettingImpl", "setPM25TimeCheck" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setPM25TimeCheckS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPM25TimeCkeck(int i) {
        LogUtil.debug("AutoSettingImpl", "setPM25TimeCkeck=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setPM25TimeCheckS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPabState(int i) {
        LogUtil.debug("AutoSettingImpl", "setPabState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setPabStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setPadRotation(int i) {
        return 0;
    }

    public int setParkBrakeInd(int i) {
        return 0;
    }

    public int setPedalSuppressionSystemState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setPedalSuppressionSystemState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setPedalSuppressionSystemStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setPedalSuppressionSystemState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setPedalSuppressionSystemState.out=0");
        return 0;
    }

    public int setPetMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setPetMode=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setPetModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRainAutoLockWindowState(int i) {
        LogUtil.debug("AutoSettingImpl", "setRainAutoLockWindowState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRainAutoLockWindowStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearAcPanelAutoLockTime(int i) {
        LogUtil.debug("AutoSettingImpl", "setRearAcPanelAutoLockTime=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRearAcPanelAutoLockTimeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearFridgeDelayState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeDelayState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearFridgeDelayStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearFridgeDelayState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeDelayState.out=0");
        return 0;
    }

    public int setRearFridgeDelayTime(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeDelayTime.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearFridgeDelayTimeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearFridgeDelayTime.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeDelayTime.out=0");
        return 0;
    }

    public int setRearFridgeDoorState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeDoorState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearFridgeDoorStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearFridgeDoorState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeDoorState.out=0");
        return 0;
    }

    public int setRearFridgeSterilizerState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeSterilizerState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearFridgeSterilizerStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearFridgeSterilizerState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeSterilizerState.out=0");
        return 0;
    }

    public int setRearFridgeTemperature(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearFridgeTemperatureS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearFridgeTemperature.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeTemperature.out=0");
        return 0;
    }

    public int setRearFridgeWorkState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeWorkState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearFridgeWorkStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearFridgeWorkState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearFridgeWorkState.out=0");
        return 0;
    }

    public int setRearRightSeatGreetState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearRightSeatGreetState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearRightSeatGreetStateS(i);
                return 1;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearRightSeatGreetState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearRightSeatGreetState.out=0");
        return 0;
    }

    public int setRearSeatGreetState(int i) {
        LogUtil.debug("AutoSettingImpl", "setRearSeatGreetState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRearSeatGreetStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearSeatsFoldOrExpand(int i) {
        LogUtil.debug("AutoSettingImpl", "setRearSeatsFoldOrExpand" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRearSeatsFoldOrExpand(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearViewMirrorFlip(int i) {
        LogUtil.debug("AutoSettingImpl", "setRearViewMirrorFlip" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRearViewMirrorFlipS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRearWorkTemperature(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearWorkTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearWorkTemperatureS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearWorkTemperature.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearWorkTemperature.out=0");
        return 0;
    }

    public int setRearviewMirrorIsAutoLightState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsAutoLightState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearviewMirrorIsAutoLightStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsAutoLightState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsAutoLightState.out=0");
        return 0;
    }

    public int setRearviewMirrorIsScreenState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsScreenState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearviewMirrorIsScreenStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsScreenState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsScreenState.out=0");
        return 0;
    }

    public int setRearviewMirrorIsTurnedState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsTurnedState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRearviewMirrorIsTurnedStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsTurnedState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRearviewMirrorIsTurnedState.out=0");
        return 0;
    }

    public int setRefrigWorkMode(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRefrigWorkMode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRefrigWorkModeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRefrigWorkMode.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRefrigWorkMode.out=0");
        return 0;
    }

    public int setRefrigeratorMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setRainAutoLockWindowState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRefrigeratorModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRefrigeratorWorkMode(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRefrigeratorWorkMode.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRefrigeratorWorkModeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRefrigeratorWorkMode.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRefrigeratorWorkMode.out=0");
        return 0;
    }

    public int setRefrigeratorWorkTemperature(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRefrigeratorWorkTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRefrigeratorWorkTemperatureS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRefrigeratorWorkTemperature.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRefrigeratorWorkTemperature.out=0");
        return 0;
    }

    public int setRemberPowerOffState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRemberPowerOffState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRemberPowerOffStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRemberPowerOffState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRemberPowerOffState.out=0");
        return 0;
    }

    public int setRemenberPowerOffState(int i) {
        LogUtil.debug("AutoSettingImpl", "setRainAutoLockWindowState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRemenberPowerOffStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRemoteControlDownwindowState(int i) {
        LogUtil.debug("AutoSettingImpl", "setRemoteControlDownwindowState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRemoteControlDownwindowStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRemoteControlLockWindowState(int i) {
        return 0;
    }

    public int setRemoteControlUnlockWindowState(int i) {
        return 0;
    }

    public int setRemoteControlUpwindowState(int i) {
        LogUtil.debug("AutoSettingImpl", "setRemoteControlUpwindowState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRemoteControlUpwindowStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRemoteKeyStartAcTime(int i) {
        LogUtil.debug("AutoSettingImpl", "setRemoteKeyStartAcTime" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRemoteKeyStartAcTimeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setReportVolume(int i) {
        LogUtil.verbose("AutoSettingImpl", "setReportVolume");
        return 0;
    }

    public int setRestMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setRestMode=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRestModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRightRseStateS(int i) {
        LogUtil.debug("AutoSettingImpl", "setRightRseStateS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRightRseStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRightRseSwitchCommandS(int i) {
        LogUtil.debug("AutoSettingImpl", "setRightRseSwitchCommandS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRightRseSwitchCommandS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRncState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setRncState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setRncStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setRncState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setRncState.out=0");
        return 0;
    }

    public int setRrseChildAreaStateS(int i) {
        LogUtil.debug("AutoSettingImpl", "setRrseChildAreaStateS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRrseChildAreaStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRrseChildModeCode1stByteS(int i) {
        LogUtil.debug("AutoSettingImpl", "setRrseChildModeCode1stByteS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRrseChildModeCode1stByteS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRrseChildModeCode2ndByteS(int i) {
        LogUtil.debug("AutoSettingImpl", "setRrseChildModeCode2ndByteS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRrseChildModeCode2ndByteS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRrseChildModeCode3rdByteS(int i) {
        LogUtil.debug("AutoSettingImpl", "setRrseChildModeCode3rdByteS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRrseChildModeCode3rdByteS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRrseChildModeCode4thByteS(int i) {
        LogUtil.debug("AutoSettingImpl", "setRrseChildModeCode4thByteS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRrseChildModeCode4thByteS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRrseChildModeOpenTimeS(int i) {
        LogUtil.debug("AutoSettingImpl", "setRrseChildModeOpenTimeS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRrseChildModeOpenTimeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setRuningCloseDoorState(int i) {
        LogUtil.debug("AutoSettingImpl", "setRuningCloseDoorState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setRuningCloseDoorStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSOCTarget(int i) {
        LogUtil.debug("AutoSettingImpl", "setSOCTarget" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSOCTargetS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSafeBeltCloseDriveDoorState(int i) {
        LogUtil.debug("AutoSettingImpl", "setSafeBeltCloseDriveDoorState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSafeBeltCloseDriveDoorStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSafeBeltInd(int i) {
        return 0;
    }

    public int setSafeBeltWarnState(int i) {
        LogUtil.debug("AutoSettingImpl", "setSafeBeltWarnState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSafeBeltWarnStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSafeWarnState(int i) {
        LogUtil.verbose("AutoSettingImpl", "setSafeWarnState");
        return 0;
    }

    public int setSandAbsModeState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setSandAbsModeState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setSandAbsModeStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setSandAbsModeState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setSandAbsModeState.out=0");
        return 0;
    }

    public int setScreenAngleValueS(int i) {
        LogUtil.debug("AutoSettingImpl", "setScreenAngleValueS=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setScreenAngleValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setScreenRotation(int i) {
        LogUtil.debug("AutoSettingImpl", "setScreenRotation=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setScreenRotationS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setScreenRotationLock(int i) {
        LogUtil.debug("AutoSettingImpl", "setScreenRotationLock=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setScreenRotationLockS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatHeatingStateDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "setSeatHeatingStateDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSeatHeatingStateDriverDomainControlS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatHeatingStateLeftRear(int i) {
        LogUtil.debug("AutoSettingImpl", "setSeatHeatingStateLeftRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSeatHeatingStateBackLeftDomainControlS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatHeatingStatePassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "setSeatHeatingStatePassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSeatHeatingStateCopilotDomainControl(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatHeatingStateRightRear(int i) {
        LogUtil.debug("AutoSettingImpl", "setSeatHeatingStateRightRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSeatHeatingStateBackRightDomainControlS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatVentilatingStateDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "setSeatVentilatingStateDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSeatVentilatingStateDriverDomainControl(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatVentilatingStateLeftRear(int i) {
        LogUtil.debug("AutoSettingImpl", "setSeatVentilatingStateLeftRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSeatVentilatingStateBackLeftDomainControlS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatVentilatingStatePassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "setSeatVentilatingStatePassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSeatVentilatingStateCopilotDomainControl(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSeatVentilatingStateRightRear(int i) {
        LogUtil.debug("AutoSettingImpl", "setSeatVentilatingStateRightRear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSeatVentilatingStateBackRightDomainControlS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSkyCurtainPowerOffAndDim(int i) {
        LogUtil.verbose("AutoSettingImpl", " setSkyCurtainPowerOffAndDim.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setSkyCurtainPowerOffAndDimS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setSkyCurtainPowerOffAndDim.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setSkyCurtainPowerOffAndDim.out=0");
        return 0;
    }

    public int setSmartWelcomeLightState(int i) {
        LogUtil.debug("AutoSettingImpl", "setSmartWelcomeLightState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSmartWelcomeLightStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSpeedReminderValue(int i) {
        LogUtil.debug("AutoSettingImpl", "setSpeedReminderValue" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSpeedReminderValueS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setStartOrPowerInd(int i) {
        return 0;
    }

    public int setSteerAssis(int i) {
        LogUtil.debug("AutoSettingImpl", "setSteerAssis" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSteerAssisS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSteerPositionAutoReturn(int i) {
        LogUtil.debug("AutoSettingImpl", "setSteerPositionAutoReturn" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSteerPositionAutoReturnS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSteeringWheelHeatingAutoState(int i) {
        LogUtil.debug("AutoSettingImpl", "setSteeringWheelHeatingAutoState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSteeringWheelHeatingAutoStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSteeringWheelHeatingGear(int i) {
        LogUtil.debug("AutoSettingImpl", "setSteeringWheelHeatingGear=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSteeringWheelHeatingGearS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSteeringWheelHeatingState(int i) {
        LogUtil.debug("AutoSettingImpl", "setSteeringWheelHeatingState=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSteeringWheelHeatingStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setSterilizationState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setSterilizationState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setSterilizationStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setSterilizationState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setSterilizationState.out=0");
        return 0;
    }

    public int setStopRemoteCtrlDriveInd(int i) {
        return 0;
    }

    public int setSwsMediumRelatedKeysS(int i) {
        LogUtil.debug("AutoSettingImpl", "setSwsMediumRelatedKeysS=" + i);
        return 0;
    }

    public int setTailBoxTakeState(int i) {
        LogUtil.debug("AutoSettingImpl", "setTailBoxTakeState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setTailBoxTakeStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setThirdRowSeatLeftBackrest(int i) {
        int i2;
        LogUtil.verbose("AutoSettingImpl", " setThirdRowSeatLeftBackrest.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            i2 = 1;
            try {
                carVendorSettingManager.setThirdRowSeatLeftBackrestS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setThirdRowSeatLeftBackrest.null");
            i2 = 0;
        }
        LogUtil.verbose("AutoSettingImpl", " setThirdRowSeatLeftBackrest.out service =" + i2);
        return i2;
    }

    public int setThirdRowSeatRightBackrest(int i) {
        int i2;
        LogUtil.verbose("AutoSettingImpl", " setThirdRowSeatRightBackrest.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            i2 = 1;
            try {
                carVendorSettingManager.setThirdRowSeatRightBackrestS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setThirdRowSeatRightBackrest.null");
            i2 = 0;
        }
        LogUtil.verbose("AutoSettingImpl", " setThirdRowSeatRightBackrest.out service =" + i2);
        return i2;
    }

    public int setTrailerDragType(int i) {
        int i2;
        LogUtil.verbose("AutoSettingImpl", " setTrailerDragType.in service");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            i2 = 1;
            try {
                carVendorSettingManager.setTrailerDragTypeS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setTrailerDragType.null");
            i2 = 0;
        }
        LogUtil.verbose("AutoSettingImpl", " setTrailerDragType.out service =" + i2);
        return i2;
    }

    public int setTrailerLightCheckState(int i) {
        LogUtil.info("AutoSettingImpl", "setTrailerLightCheckState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setTrailerLightCheckStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTrailerModeState(int i) {
        LogUtil.info("AutoSettingImpl", "setTrailerModeState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setTrailerModeStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setTrayState(int i) {
        LogUtil.debug("AutoSettingImpl", "setTrayState" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setTrayStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setUiVersion(int i) {
        LogUtil.debug("AutoSettingImpl", "setUiVersion=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSetUiVersion(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setVspState(int i) {
        LogUtil.info("AutoSettingImpl", "setVSPState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setVspStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWelcomeState(int i) {
        LogUtil.info("AutoSettingImpl", "setWelcomeState is execute" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setWelcomeStateS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWipeMode(int i) {
        LogUtil.debug("AutoSettingImpl", "setWipeMode=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setWipeModeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int setWorkCommond(int i) {
        LogUtil.verbose("AutoSettingImpl", " setWorkCommond.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setWorkCommondS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setWorkCommond.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setWorkCommond.out=0");
        return 0;
    }

    public int setWorkState(int i) {
        LogUtil.verbose("AutoSettingImpl", " setWorkState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setWorkStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setWorkState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setWorkState.out=0");
        return 0;
    }

    public int setWorkTemperature(int i) {
        LogUtil.verbose("AutoSettingImpl", " setWorkTemperature.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setWorkTemperatureS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " setWorkTemperature.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " setWorkTemperature.out=0");
        return 0;
    }

    public int soundFiledDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "soundFiledDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setSoundFiledDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean subscribeSettingCallback(ISettingAdapterCallback iSettingAdapterCallback) {
        com.ts.appservice.caradapter.a.j jVar = this.f22a;
        if (jVar == null || iSettingAdapterCallback == null) {
            return false;
        }
        jVar.h(iSettingAdapterCallback);
        if (!this.d) {
            return true;
        }
        this.f22a.l();
        return true;
    }

    public void turnCaliperSplintPercentDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnCaliperSplintPercentDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnCaliperSplintPercentDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnCaliperSplintPercentPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnCaliperSplintPercentPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnCaliperSplintPercentPassenger(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnCushionPercentDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnCushionPercentDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnCushionPercentDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestHeightPercentDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestHeightPercentDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestHeightPercentDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestHorizationPercentDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestHorizationPercentDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestHorizationPercentDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestTurnHeightDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestTurnHeightDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestTurnHeightDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestTurnHeightLeftArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestTurnHeightLeftArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestTurnHeightBackLeft(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestTurnHeightPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestTurnHeightPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestTurnHeightCopilot(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestTurnHeightRightArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestTurnHeightRightArea" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestTurnHeightBackRight(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestTurnHorizationDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestTurnHorizationDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestTurnHeightDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestTurnHorizationLeftArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestTurnHorizationLeftArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestTurnHeightBackLeft(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestTurnHorizationPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestTurnHorizationPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestTurnHeightCopilot(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnHeadrestTurnHorizationRightArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnHeadrestTurnHorizationRightArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnHeadrestTurnHeightBackRight(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnRearviewMirror(int i) {
        LogUtil.debug("AutoSettingImpl", "turnRearviewMirror" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnRearviewMirror(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int turnRearviewMirrorIs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnRearviewMirrorIs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.turnRearviewMirrorIS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnRearviewMirrorIs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnRearviewMirrorIs.out=0");
        return 0;
    }

    public int turnSeatBackRearLeftLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatBackRearLeftLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftLeftRcs.out=0");
        return 0;
    }

    public int turnSeatBackRearLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatBackRearLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftRcs.out=0");
        return 0;
    }

    public int turnSeatBackRearLeftRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatBackRearLeftRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearLeftRightRcs.out=0");
        return 0;
    }

    public int turnSeatBackRearRightLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatBackRearRightLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightLeftRcs.out=0");
        return 0;
    }

    public int turnSeatBackRearRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatBackRearRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightRcs.out=0");
        return 0;
    }

    public int turnSeatBackRearRightRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatBackRearRightRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatBackRearRightRightRcs.out=0");
        return 0;
    }

    public void turnSeatCaliperSplintDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatCaliperSplintDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatCaliperSplintLeftArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatCaliperSplintLeftArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintBackLeft(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatCaliperSplintPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatCaliperSplintPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintCopilot(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int turnSeatCaliperSplintRearLeftLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintRearLeftLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftLeftRcs == null");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftLeftRcs.out=0");
        return 0;
    }

    public int turnSeatCaliperSplintRearLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintRearLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftRcs.out=0");
        return 0;
    }

    public int turnSeatCaliperSplintRearLeftRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintRearLeftRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftRightRcs == null");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearLeftRightRcs.out=0");
        return 0;
    }

    public int turnSeatCaliperSplintRearRightLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintRearRightLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightLeftRcs == null");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightLeftRcs.out=0");
        return 0;
    }

    public int turnSeatCaliperSplintRearRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintRearRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightRcs == null");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightRcs.out=0");
        return 0;
    }

    public int turnSeatCaliperSplintRearRightRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintRearRightRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightRightRcs == null");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCaliperSplintRearRightRightRcs.out=0");
        return 0;
    }

    public void turnSeatCaliperSplintRightArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatCaliperSplintRightArea" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCaliperSplintBackRight(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatCushionDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatCushionDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatCushionLeftArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatCushionLeftArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionBackLeft(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatCushionPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatCushionPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionCopilot(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int turnSeatCushionRearLeftLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionRearLeftLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftLeftRcs.out=0");
        return 0;
    }

    public int turnSeatCushionRearLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionRearLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftRcs.out=0");
        return 0;
    }

    public int turnSeatCushionRearLeftRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionRearLeftRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearLeftRightRcs.out=0");
        return 0;
    }

    public int turnSeatCushionRearRightLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionRearRightLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightLeftRcs.out=0");
        return 0;
    }

    public int turnSeatCushionRearRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionRearRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightRcs.out=0");
        return 0;
    }

    public int turnSeatCushionRearRightRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionRearRightRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatCushionRearRightRightRcs.out=0");
        return 0;
    }

    public void turnSeatCushionRightArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatCushionRightArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatCushionBackRight(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHeightDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHeightDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHeightDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHeightLeftArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHeightLeftArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHeightBackLeft(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHeightPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHeightPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHeightCopilot(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHeightPercentDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHeightPercentDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHeightPercentDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHeightPercentLeftArea(int i) {
        LogUtil.verbose("AutoSettingImpl", "turnSeatHeightPercentLeftArea");
    }

    public void turnSeatHeightPercentPassenger(int i) {
        LogUtil.verbose("AutoSettingImpl", "turnSeatHeightPercentPassenger");
    }

    public void turnSeatHeightPercentRightArea(int i) {
        LogUtil.verbose("AutoSettingImpl", "turnSeatHeightPercentRightArea");
    }

    public void turnSeatHeightRightArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHeightRightArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHeightBackRight(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHorizationDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHorizationDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHorizationLeftArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHorizationLeftArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationBackLeft(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHorizationPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHorizationPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationCopilot(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHorizationPercentDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHorizationPercentDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationPercentDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHorizationPercentLeftArea(int i) {
        LogUtil.verbose("AutoSettingImpl", "turnSeatHorizationPercentLeftArea");
    }

    public void turnSeatHorizationPercentPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHorizationPercentPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationPercentPassenger(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatHorizationPercentRightArea(int i) {
        LogUtil.verbose("AutoSettingImpl", "turnSeatHorizationPercentRightArea");
    }

    public int turnSeatHorizationRearLeftLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationRearLeftLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftLeftRcs.out=0");
        return 0;
    }

    public int turnSeatHorizationRearLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationRearLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftRcs.out=0");
        return 0;
    }

    public int turnSeatHorizationRearLeftRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationRearLeftRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearLeftRightRcs.out=0");
        return 0;
    }

    public int turnSeatHorizationRearRightLeftRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightLeftRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationRearRightLeftRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightLeftRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightLeftRcs.out=0");
        return 0;
    }

    public int turnSeatHorizationRearRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationRearRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightRcs.out=0");
        return 0;
    }

    public int turnSeatHorizationRearRightRightRcs(int i) {
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightRightRcs.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationRearRightRightRcS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightRightRcs.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " turnSeatHorizationRearRightRightRcs.out=0");
        return 0;
    }

    public void turnSeatHorizationRightArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatHorizationRightArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHorizationBackRight(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatbackrestDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatbackrestDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatbackrestDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatbackrestLeftArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatbackrestLeftArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatbackrestBackLeft(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatbackrestPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatbackrestPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatHeightPercentCopilot(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatbackrestPercentDriver(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatbackrestPercentDriver=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setTurnSeatbackrestPercentDriver(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatbackrestPercentLeftArea(int i) {
        LogUtil.verbose("AutoSettingImpl", "turnSeatbackrestPercentLeftArea");
    }

    public void turnSeatbackrestPercentPassenger(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatbackrestPercentPassenger=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setTurnSeatbackrestPercentPassenger(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSeatbackrestPercentRightArea(int i) {
        LogUtil.verbose("AutoSettingImpl", "turnSeatbackrestPercentRightArea");
    }

    public void turnSeatbackrestRightArea(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSeatbackrestRightArea=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSeatbackrestBackRight(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnSteeringWheel(int i) {
        LogUtil.debug("AutoSettingImpl", "turnSteeringWheel" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setturnSteeringWheel(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public int unitDefaultReset(int i) {
        LogUtil.debug("AutoSettingImpl", "unitDefaultReset=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setUnitDefaultReset(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean unsubscribeSettingCallback(ISettingAdapterCallback iSettingAdapterCallback) {
        com.ts.appservice.caradapter.a.j jVar = this.f22a;
        if (jVar == null || iSettingAdapterCallback == null) {
            return false;
        }
        jVar.i(iSettingAdapterCallback);
        return true;
    }

    public int voiceCtlBackDoor(int i) {
        LogUtil.debug("AutoSettingImpl", "voiceCtlBackDoor=" + i);
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager == null) {
            return 0;
        }
        try {
            carVendorSettingManager.setVoiceCtlBackDoor(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int voiceOpenMirrorlsTurnedState(int i) {
        LogUtil.verbose("AutoSettingImpl", " voiceOpenMirrorlsTurnedState.in");
        CarVendorSettingManager carVendorSettingManager = this.e;
        if (carVendorSettingManager != null) {
            try {
                carVendorSettingManager.setvoiceOpenMirrorlsTurnedStatS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose("AutoSettingImpl", " voiceOpenMirrorlsTurnedState.null == mCarSettingManager");
        }
        LogUtil.verbose("AutoSettingImpl", " voiceOpenMirrorlsTurnedState.out=0");
        return 0;
    }
}
