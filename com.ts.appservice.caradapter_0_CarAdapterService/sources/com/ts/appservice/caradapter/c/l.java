package com.ts.appservice.caradapter.c;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager;
import com.ts.lib.car.hardware.uistyle.UistyleEventChangeValue;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.selfstudy.ISelfStudyCallback;
import com.ts.lib.caradapter.selfstudy.ISelfStudyService;
import com.ts.lib.caradapter.selfstudy.SelfStudyPacket;

/* JADX INFO: compiled from: SelfStudyServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends ISelfStudyService.Stub {
    private static final String g = l.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f81b;
    private final com.ts.appservice.caradapter.a.i e;
    private CarVendorUistyleManager f;
    private SelfStudyPacket c = new SelfStudyPacket();
    private boolean d = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HandlerThread f80a = com.ts.appservice.caradapter.e.b.a("VEHICLE-SELFSTUDY");

    /* JADX INFO: compiled from: SelfStudyServiceImpl.java */
    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                l.this.n(message.arg1, message.arg2, (UistyleEventChangeValue) message.obj);
                return;
            }
            LogUtil.error(l.g, "Unexpected message: " + message.what);
        }
    }

    public l(Context context) {
        this.e = new com.ts.appservice.caradapter.a.i(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(int i, int i2, UistyleEventChangeValue uistyleEventChangeValue) {
        String str = g;
        try {
            switch (i) {
                case 555782656:
                    int instrumentHeightSupport = uistyleEventChangeValue.getInstrumentHeightSupport();
                    this.c.setInstrumentHeightSupport(instrumentHeightSupport);
                    LogUtil.info(str, "ID_INSTRUMENT_HEIGHT_SUPPORT-" + instrumentHeightSupport);
                    break;
                case 557856010:
                    int supportRearAcAutoControl = uistyleEventChangeValue.getSupportRearAcAutoControl();
                    this.c.setSupportRearAcAutoControl(supportRearAcAutoControl);
                    LogUtil.info(str, "ID_SUPPORT_REAR_AC_AUTO_CONTROL-" + supportRearAcAutoControl);
                    break;
                case 557856014:
                    int supportSingleDoubleTempArea = uistyleEventChangeValue.getSupportSingleDoubleTempArea();
                    this.c.setSupportSingleDoubleTempArea(supportSingleDoubleTempArea);
                    LogUtil.info(str, "ID_SUPPORT_SINGLE_DOUBLE_TEMP_AREA-" + supportSingleDoubleTempArea);
                    break;
                case 557856016:
                    int supportAcAutoKeyCanClose = uistyleEventChangeValue.getSupportAcAutoKeyCanClose();
                    this.c.setSupportAcAutoKeyCanClose(supportAcAutoKeyCanClose);
                    LogUtil.info(str, "ID_SUPPORT_AC_AUTO_KEY_CAN_CLOSE-" + supportAcAutoKeyCanClose);
                    break;
                case 557856037:
                    int backHomeDelaySupport = uistyleEventChangeValue.getBackHomeDelaySupport();
                    this.c.setBackHomeDelaySupport(backHomeDelaySupport);
                    LogUtil.info(str, "ID_BACK_HOME_DELAY_SUPPORT-" + backHomeDelaySupport);
                    break;
                case 557856053:
                    int autoExternalRearMirrorFollowUpSwitchSupport = uistyleEventChangeValue.getAutoExternalRearMirrorFollowUpSwitchSupport();
                    this.c.setAutoExternalRearMirrorFollowUpSwitchSupport(autoExternalRearMirrorFollowUpSwitchSupport);
                    LogUtil.info(str, "ID_AUTO_EXTERNAL_REAR_MIRROR_FOLLOW_UP_SWITCH_SUPPORT-" + autoExternalRearMirrorFollowUpSwitchSupport);
                    break;
                case 557856054:
                    int driveSeatAutoReturnSupport = uistyleEventChangeValue.getDriveSeatAutoReturnSupport();
                    this.c.setDriveSeatAutoReturnSupport(driveSeatAutoReturnSupport);
                    LogUtil.info(str, "ID_DRIVE_SEAT_AUTO_RETURN_SUPPORT-" + driveSeatAutoReturnSupport);
                    break;
                case 557856055:
                    int steerPositionAutoReturnSupport = uistyleEventChangeValue.getSteerPositionAutoReturnSupport();
                    this.c.setSteerPositionAutoReturnSupport(steerPositionAutoReturnSupport);
                    LogUtil.info(str, "ID_STEER_POSITION_AUTO_RETURN_SUPPORT-" + steerPositionAutoReturnSupport);
                    break;
                case 557856056:
                    int brakePedalModeSupport = uistyleEventChangeValue.getBrakePedalModeSupport();
                    this.c.setBrakePedalModeSupport(brakePedalModeSupport);
                    LogUtil.info(str, "ID_BRAKE_PEDAL_MODE_SUPPORT-" + brakePedalModeSupport);
                    break;
                case 557856057:
                    int comfortableParkingSupport = uistyleEventChangeValue.getComfortableParkingSupport();
                    this.c.setComfortableParkingSupport(comfortableParkingSupport);
                    LogUtil.info(str, "ID_COMFORTABLE_PARKING_SUPPORT-" + comfortableParkingSupport);
                    break;
                case 557856058:
                    int motorPretensionerSeatbeltSupport = uistyleEventChangeValue.getMotorPretensionerSeatbeltSupport();
                    this.c.setMotorPretensionerSeatbeltSupport(motorPretensionerSeatbeltSupport);
                    LogUtil.info(str, "ID_MOTOR_PRETENSIONER_SEATBELT_SUPPORT-" + motorPretensionerSeatbeltSupport);
                    break;
                case 557856059:
                    int steerAssisSupport = uistyleEventChangeValue.getSteerAssisSupport();
                    this.c.setSteerAssisSupport(steerAssisSupport);
                    LogUtil.info(str, "ID_STEER_ASSIS_SUPPORT-" + steerAssisSupport);
                    break;
                case 557856060:
                    int autoWiperSupport = uistyleEventChangeValue.getAutoWiperSupport();
                    this.c.setAutoWiperSupport(autoWiperSupport);
                    LogUtil.info(str, "ID_AUTO_WIPER_SUPPORT-" + autoWiperSupport);
                    break;
                case 557856061:
                    int dynamicSuspensionModeSupport = uistyleEventChangeValue.getDynamicSuspensionModeSupport();
                    this.c.setDynamicSuspensionModeSupport(dynamicSuspensionModeSupport);
                    LogUtil.info(str, "ID_DYNAMIC_SUSPENSION_MODE_SUPPORT-" + dynamicSuspensionModeSupport);
                    break;
                case 557856062:
                    int landAdvanceAtSupport = uistyleEventChangeValue.getLandAdvanceAtSupport();
                    this.c.setLandAdvanceAtSupport(landAdvanceAtSupport);
                    LogUtil.info(str, "ID_LAND_ADVANCE_AT_SUPPORT-" + landAdvanceAtSupport);
                    break;
                case 557856063:
                    int remoteKeyStartAcTimeSupport = uistyleEventChangeValue.getRemoteKeyStartAcTimeSupport();
                    this.c.setRemoteKeyStartAcTimeSupport(remoteKeyStartAcTimeSupport);
                    LogUtil.info(str, "ID_REMOTE_KEY_START_AC_TIME_SUPPORT-" + remoteKeyStartAcTimeSupport);
                    break;
                case 557856064:
                    int aCAutoAirSupport = uistyleEventChangeValue.getACAutoAirSupport();
                    this.c.setACAutoAirSupport(aCAutoAirSupport);
                    LogUtil.info(str, "ID_A_C_AUTO_AIR_SUPPORT-" + aCAutoAirSupport);
                    break;
                case 557856065:
                    int aCBTWindSupport = uistyleEventChangeValue.getACBTWindSupport();
                    this.c.setACBTWindSupport(aCBTWindSupport);
                    LogUtil.info(str, "ID_A_C_B_T_WIND_SUPPORT-" + aCBTWindSupport);
                    break;
                case 557856066:
                    int aCPauseCycleSupport = uistyleEventChangeValue.getACPauseCycleSupport();
                    this.c.setACPauseCycleSupport(aCPauseCycleSupport);
                    LogUtil.info(str, "ID_A_C_PAUSE_CYCLE_SUPPORT-" + aCPauseCycleSupport);
                    break;
                case 557856067:
                    int autoPurificationSupport = uistyleEventChangeValue.getAutoPurificationSupport();
                    this.c.setAutoPurificationSupport(autoPurificationSupport);
                    LogUtil.info(str, "ID_AUTO_PURIFICATION_SUPPORT-" + autoPurificationSupport);
                    break;
                case 557856068:
                    int pM25PowerSupport = uistyleEventChangeValue.getPM25PowerSupport();
                    this.c.setPM25PowerSupport(pM25PowerSupport);
                    LogUtil.info(str, "ID_P_M25_POWER_SUPPORT-" + pM25PowerSupport);
                    break;
                case 557856069:
                    int pM25SwitchCheckSupport = uistyleEventChangeValue.getPM25SwitchCheckSupport();
                    this.c.setPM25SwitchCheckSupport(pM25SwitchCheckSupport);
                    LogUtil.info(str, "ID_P_M25_SWITCH_CHECK_SUPPORT-" + pM25SwitchCheckSupport);
                    break;
                case 557856070:
                    int pM25TimeCkeckSupport = uistyleEventChangeValue.getPM25TimeCkeckSupport();
                    this.c.setPM25TimeCkeckSupport(pM25TimeCkeckSupport);
                    LogUtil.info(str, "ID_P_M25_TIME_CKECK_SUPPORT-" + pM25TimeCkeckSupport);
                    break;
                case 557856071:
                    int aCTunnelCycleSupport = uistyleEventChangeValue.getACTunnelCycleSupport();
                    this.c.setACTunnelCycleSupport(aCTunnelCycleSupport);
                    LogUtil.info(str, "ID_A_C_TUNNEL_CYCLE_SUPPORT-" + aCTunnelCycleSupport);
                    break;
                case 557856072:
                    int hudShowParamsSupport = uistyleEventChangeValue.getHudShowParamsSupport();
                    this.c.setHudShowParamsSupport(hudShowParamsSupport);
                    LogUtil.info(str, "ID_HUD_SHOW_PARAMS_SUPPORT-" + hudShowParamsSupport);
                    break;
                case 557856073:
                    int rearPanelLockTimeSupport = uistyleEventChangeValue.getRearPanelLockTimeSupport();
                    this.c.setRearPanelLockTimeSupport(rearPanelLockTimeSupport);
                    LogUtil.info(str, "ID_REAR_PANEL_LOCK_TIME_SUPPORT-" + rearPanelLockTimeSupport);
                    break;
                case 557856074:
                    int acWarmSupport = uistyleEventChangeValue.getAcWarmSupport();
                    this.c.setAcWarmSupport(acWarmSupport);
                    LogUtil.info(str, "ID_AC_WARM_SUPPORT-" + acWarmSupport);
                    break;
                case 557856075:
                    int intelligentAcAreaSupport = uistyleEventChangeValue.getIntelligentAcAreaSupport();
                    this.c.setIntelligentAcAreaSupport(intelligentAcAreaSupport);
                    LogUtil.info(str, "ID_INTELLIGENT_AC_AREA_SUPPORT-" + intelligentAcAreaSupport);
                    break;
                case 557856076:
                    int doorHeightSupport = uistyleEventChangeValue.getDoorHeightSupport();
                    this.c.setDoorHeightSupport(doorHeightSupport);
                    LogUtil.info(str, "ID_DOOR_HEIGHT_SUPPORT-" + doorHeightSupport);
                    break;
                case 557856077:
                    int supportIntelligentNavigationFirst = uistyleEventChangeValue.getSupportIntelligentNavigationFirst();
                    this.c.setSupportIntelligentNavigationFirst(supportIntelligentNavigationFirst);
                    LogUtil.info(str, "ID_SUPPORT_INTELLIGENT_NAVIGATION_FIRST-" + supportIntelligentNavigationFirst);
                    break;
                case 557856078:
                    int supportIntelligentNavigationSecond = uistyleEventChangeValue.getSupportIntelligentNavigationSecond();
                    this.c.setSupportIntelligentNavigationSecond(supportIntelligentNavigationSecond);
                    LogUtil.info(str, "ID_SUPPORT_INTELLIGENT_NAVIGATION_SECOND-" + supportIntelligentNavigationSecond);
                    break;
                case 557856079:
                    int supportHighwayNavigation = uistyleEventChangeValue.getSupportHighwayNavigation();
                    this.c.setSupportHighwayNavigation(supportHighwayNavigation);
                    LogUtil.info(str, "ID_SUPPORT_HIGHWAY_NAVIGATION-" + supportHighwayNavigation);
                    break;
                case 557856105:
                    int supportRearCrossBrake = uistyleEventChangeValue.getSupportRearCrossBrake();
                    this.c.setSupportRearCrossBrake(supportRearCrossBrake);
                    LogUtil.info(str, "ID_SUPPORT_REAR_CROSS_BRAKE-" + supportRearCrossBrake);
                    break;
                case 557856106:
                    int supportAiDistanceLight = uistyleEventChangeValue.getSupportAiDistanceLight();
                    this.c.setSupportAiDistanceLight(supportAiDistanceLight);
                    LogUtil.info(str, "ID_SUPPORT_AI_DISTANCE_LIGHT-" + supportAiDistanceLight);
                    break;
                case 557856107:
                    int supportAiMatrixLightFirst = uistyleEventChangeValue.getSupportAiMatrixLightFirst();
                    this.c.setSupportAiMatrixLightFirst(supportAiMatrixLightFirst);
                    LogUtil.info(str, "ID_SUPPORT_AI_MATRIX_LIGHT_FIRST-" + supportAiMatrixLightFirst);
                    break;
                case 557856108:
                    int supportAiMatrixLightSecond = uistyleEventChangeValue.getSupportAiMatrixLightSecond();
                    this.c.setSupportAiMatrixLightSecond(supportAiMatrixLightSecond);
                    LogUtil.info(str, "ID_SUPPORT_AI_MATRIX_LIGHT_SECOND-" + supportAiMatrixLightSecond);
                    break;
                case 557856109:
                    int supportPanoramaImage = uistyleEventChangeValue.getSupportPanoramaImage();
                    this.c.setSupportPanoramaImage(supportPanoramaImage);
                    LogUtil.info(str, "ID_SUPPORT_PANORAMA_IMAGE-" + supportPanoramaImage);
                    break;
                case 557856110:
                    int supportESP = uistyleEventChangeValue.getSupportESP();
                    this.c.setSupportESP(supportESP);
                    LogUtil.info(str, "ID_SUPPORT_E_S_P-" + supportESP);
                    break;
                case 557856111:
                    int supportShowCarModel = uistyleEventChangeValue.getSupportShowCarModel();
                    this.c.setSupportShowCarModel(supportShowCarModel);
                    LogUtil.info(str, "ID_SUPPORT_SHOW_CAR_MODEL-" + supportShowCarModel);
                    break;
                case 557856121:
                    int digtalKeySupport = uistyleEventChangeValue.getDigtalKeySupport();
                    this.c.setDigtalKeySupport(digtalKeySupport);
                    LogUtil.info(str, "ID_DIGTAL_KEY_SUPPORT-" + digtalKeySupport);
                    break;
                case 557856124:
                    int appUseLimitSupport = uistyleEventChangeValue.getAppUseLimitSupport();
                    this.c.setAppUseLimitSupport(appUseLimitSupport);
                    LogUtil.info(str, "ID_APP_USE_LIMIT_SUPPORT-" + appUseLimitSupport);
                    break;
                case 557856125:
                    int remindSpeedSupport = uistyleEventChangeValue.getRemindSpeedSupport();
                    this.c.setRemindSpeedSupport(remindSpeedSupport);
                    LogUtil.info(str, "ID_REMIND_SPEED_SUPPORT-" + remindSpeedSupport);
                    break;
                case 557856126:
                    int reportVolumeSupport = uistyleEventChangeValue.getReportVolumeSupport();
                    this.c.setReportVolumeSupport(reportVolumeSupport);
                    LogUtil.info(str, "ID_REPORT_VOLUME_SUPPORT-" + reportVolumeSupport);
                    break;
                case 557856127:
                    int iD = d(uistyleEventChangeValue.getEngineSoundSupport());
                    this.c.setEngineSoundSupport(iD);
                    LogUtil.info(str, "ID_ENGINE_SOUND_SUPPORT-" + iD);
                    break;
                case 557856128:
                    int carTipsSoundSupport = uistyleEventChangeValue.getCarTipsSoundSupport();
                    this.c.setCarTipsSoundSupport(carTipsSoundSupport);
                    LogUtil.info(str, "ID_CAR_TIPS_SOUND_SUPPORT-" + carTipsSoundSupport);
                    break;
                case 557856129:
                    int reportContentSetSupport = uistyleEventChangeValue.getReportContentSetSupport();
                    this.c.setReportContentSetSupport(reportContentSetSupport);
                    LogUtil.info(str, "ID_REPORT_CONTENT_SET_SUPPORT-" + reportContentSetSupport);
                    break;
                case 557856130:
                    int reportItemsSetSupportPower = uistyleEventChangeValue.getReportItemsSetSupportPower();
                    this.c.setReportItemsSetSupportPower(reportItemsSetSupportPower);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_POWER-" + reportItemsSetSupportPower);
                    break;
                case 557856131:
                    int reportItemsSetSupportBrake = uistyleEventChangeValue.getReportItemsSetSupportBrake();
                    this.c.setReportItemsSetSupportBrake(reportItemsSetSupportBrake);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_BRAKE-" + reportItemsSetSupportBrake);
                    break;
                case 557856132:
                    int reportItemsSetSupportClose = uistyleEventChangeValue.getReportItemsSetSupportClose();
                    this.c.setReportItemsSetSupportClose(reportItemsSetSupportClose);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_CLOSE-" + reportItemsSetSupportClose);
                    break;
                case 557856133:
                    int reportItemsSetSupportBelt = uistyleEventChangeValue.getReportItemsSetSupportBelt();
                    this.c.setReportItemsSetSupportBelt(reportItemsSetSupportBelt);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_BELT-" + reportItemsSetSupportBelt);
                    break;
                case 557856134:
                    int reportItemsSetSupportCar = uistyleEventChangeValue.getReportItemsSetSupportCar();
                    this.c.setReportItemsSetSupportCar(reportItemsSetSupportCar);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_CAR-" + reportItemsSetSupportCar);
                    break;
                case 557856135:
                    int reportItemsSetSupportLow = uistyleEventChangeValue.getReportItemsSetSupportLow();
                    this.c.setReportItemsSetSupportLow(reportItemsSetSupportLow);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_LOW-" + reportItemsSetSupportLow);
                    break;
                case 557856136:
                    int reportItemsSetSupportKey = uistyleEventChangeValue.getReportItemsSetSupportKey();
                    this.c.setReportItemsSetSupportKey(reportItemsSetSupportKey);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_KEY-" + reportItemsSetSupportKey);
                    break;
                case 557856137:
                    int reportItemsSetSupportOn = uistyleEventChangeValue.getReportItemsSetSupportOn();
                    this.c.setReportItemsSetSupportOn(reportItemsSetSupportOn);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_ON-" + reportItemsSetSupportOn);
                    break;
                case 557856138:
                    int reportItemsSetSupportOil = uistyleEventChangeValue.getReportItemsSetSupportOil();
                    this.c.setReportItemsSetSupportOil(reportItemsSetSupportOil);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_OIL-" + reportItemsSetSupportOil);
                    break;
                case 557856139:
                    int reportItemsSetSupportMode = uistyleEventChangeValue.getReportItemsSetSupportMode();
                    this.c.setReportItemsSetSupportMode(reportItemsSetSupportMode);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_MODE-" + reportItemsSetSupportMode);
                    break;
                case 557856140:
                    int reportItemsSetSupportPark = uistyleEventChangeValue.getReportItemsSetSupportPark();
                    this.c.setReportItemsSetSupportPark(reportItemsSetSupportPark);
                    LogUtil.info(str, "ID_REPORT_ITEMS_SET_SUPPORT_PARK-" + reportItemsSetSupportPark);
                    break;
                case 557856141:
                    int seatSetSupportFirst = uistyleEventChangeValue.getSeatSetSupportFirst();
                    this.c.setSeatSetSupportFirst(seatSetSupportFirst);
                    LogUtil.info(str, "ID_SEAT_SET_SUPPORT_FIRST-" + seatSetSupportFirst);
                    break;
                case 557856142:
                    int seatSetSupportSecond = uistyleEventChangeValue.getSeatSetSupportSecond();
                    this.c.setSeatSetSupportSecond(seatSetSupportSecond);
                    LogUtil.info(str, "ID_SEAT_SET_SUPPORT_SECOND-" + seatSetSupportSecond);
                    break;
                case 557856143:
                    int seatSetSupportCopilot = uistyleEventChangeValue.getSeatSetSupportCopilot();
                    this.c.setSeatSetSupportCopilot(seatSetSupportCopilot);
                    LogUtil.info(str, "ID_SEAT_SET_SUPPORT_COPILOT-" + seatSetSupportCopilot);
                    break;
                case 557856144:
                    int seatSetSupportBackL = uistyleEventChangeValue.getSeatSetSupportBackL();
                    this.c.setSeatSetSupportBackL(seatSetSupportBackL);
                    LogUtil.info(str, "ID_SEAT_SET_SUPPORT_BACK_L-" + seatSetSupportBackL);
                    break;
                case 557856145:
                    int seatSetSupportBackR = uistyleEventChangeValue.getSeatSetSupportBackR();
                    this.c.setSeatSetSupportBackR(seatSetSupportBackR);
                    LogUtil.info(str, "ID_SEAT_SET_SUPPORT_BACK_R-" + seatSetSupportBackR);
                    break;
                case 557856146:
                    int steeringWheelSupport = uistyleEventChangeValue.getSteeringWheelSupport();
                    this.c.setSteeringWheelSupport(steeringWheelSupport);
                    LogUtil.info(str, "ID_STEERING_WHEEL_SUPPORT-" + steeringWheelSupport);
                    break;
                case 557856147:
                    int rearviewMirrorSupport = uistyleEventChangeValue.getRearviewMirrorSupport();
                    this.c.setRearviewMirrorSupport(rearviewMirrorSupport);
                    LogUtil.info(str, "ID_REARVIEW_MIRROR_SUPPORT-" + rearviewMirrorSupport);
                    break;
                case 557856148:
                    int seatTurnHorizatialSupportSecond = uistyleEventChangeValue.getSeatTurnHorizatialSupportSecond();
                    this.c.setSeatTurnHorizatialSupportSecond(seatTurnHorizatialSupportSecond);
                    LogUtil.info(str, "ID_SEAT_TURN_HORIZATIAL_SUPPORT_SECOND-" + seatTurnHorizatialSupportSecond);
                    break;
                case 557856149:
                    int seatTurnHorizatialSupportCopilot = uistyleEventChangeValue.getSeatTurnHorizatialSupportCopilot();
                    this.c.setSeatTurnHorizatialSupportCopilot(seatTurnHorizatialSupportCopilot);
                    LogUtil.info(str, "ID_SEAT_TURN_HORIZATIAL_SUPPORT_COPILOT-" + seatTurnHorizatialSupportCopilot);
                    break;
                case 557856150:
                    int seatTurnHorizatialSupportBackL = uistyleEventChangeValue.getSeatTurnHorizatialSupportBackL();
                    this.c.setSeatTurnHorizatialSupportBackL(seatTurnHorizatialSupportBackL);
                    LogUtil.info(str, "ID_SEAT_TURN_HORIZATIAL_SUPPORT_BACK_L-" + seatTurnHorizatialSupportBackL);
                    break;
                case 557856151:
                    int seatTurnHorizatialSupportBackR = uistyleEventChangeValue.getSeatTurnHorizatialSupportBackR();
                    this.c.setSeatTurnHorizatialSupportBackR(seatTurnHorizatialSupportBackR);
                    LogUtil.info(str, "ID_SEAT_TURN_HORIZATIAL_SUPPORT_BACK_R-" + seatTurnHorizatialSupportBackR);
                    break;
                case 557856152:
                    int seatTurnHeightSupportSecond = uistyleEventChangeValue.getSeatTurnHeightSupportSecond();
                    this.c.setSeatTurnHeightSupportSecond(seatTurnHeightSupportSecond);
                    LogUtil.info(str, "ID_SEAT_TURN_HEIGHT_SUPPORT_SECOND-" + seatTurnHeightSupportSecond);
                    break;
                case 557856153:
                    int seatTurnHeightSupportCopilot = uistyleEventChangeValue.getSeatTurnHeightSupportCopilot();
                    this.c.setSeatTurnHeightSupportCopilot(seatTurnHeightSupportCopilot);
                    LogUtil.info(str, "ID_SEAT_TURN_HEIGHT_SUPPORT_COPILOT-" + seatTurnHeightSupportCopilot);
                    break;
                case 557856154:
                    int seatTurnHeightSupportBackL = uistyleEventChangeValue.getSeatTurnHeightSupportBackL();
                    this.c.setSeatTurnHeightSupportBackL(seatTurnHeightSupportBackL);
                    LogUtil.info(str, "ID_SEAT_TURN_HEIGHT_SUPPORT_BACK_L-" + seatTurnHeightSupportBackL);
                    break;
                case 557856155:
                    int seatTurnHeightSupportBackR = uistyleEventChangeValue.getSeatTurnHeightSupportBackR();
                    this.c.setSeatTurnHeightSupportBackR(seatTurnHeightSupportBackR);
                    LogUtil.info(str, "ID_SEAT_TURN_HEIGHT_SUPPORT_BACK_R-" + seatTurnHeightSupportBackR);
                    break;
                case 557856156:
                    int headrestTurnHorizatialSupportFirst = uistyleEventChangeValue.getHeadrestTurnHorizatialSupportFirst();
                    this.c.setHeadrestTurnHorizatialSupportFirst(headrestTurnHorizatialSupportFirst);
                    LogUtil.info(str, "ID_HEADREST_TURN_HORIZATIAL_SUPPORT_FIRST-" + headrestTurnHorizatialSupportFirst);
                    break;
                case 557856157:
                    int headrestTurnHorizatialSupportSecond = uistyleEventChangeValue.getHeadrestTurnHorizatialSupportSecond();
                    this.c.setHeadrestTurnHorizatialSupportSecond(headrestTurnHorizatialSupportSecond);
                    LogUtil.info(str, "ID_HEADREST_TURN_HORIZATIAL_SUPPORT_SECOND-" + headrestTurnHorizatialSupportSecond);
                    break;
                case 557856158:
                    int headrestTurnHorizatialSupportCopilot = uistyleEventChangeValue.getHeadrestTurnHorizatialSupportCopilot();
                    this.c.setHeadrestTurnHorizatialSupportCopilot(headrestTurnHorizatialSupportCopilot);
                    LogUtil.info(str, "ID_HEADREST_TURN_HORIZATIAL_SUPPORT_COPILOT-" + headrestTurnHorizatialSupportCopilot);
                    break;
                case 557856159:
                    int headrestTurnHorizatialSupportBackL = uistyleEventChangeValue.getHeadrestTurnHorizatialSupportBackL();
                    this.c.setHeadrestTurnHorizatialSupportBackL(headrestTurnHorizatialSupportBackL);
                    LogUtil.info(str, "ID_HEADREST_TURN_HORIZATIAL_SUPPORT_BACK_L-" + headrestTurnHorizatialSupportBackL);
                    break;
                case 557856160:
                    int headrestTurnHorizatialSupportBackR = uistyleEventChangeValue.getHeadrestTurnHorizatialSupportBackR();
                    this.c.setHeadrestTurnHorizatialSupportBackR(headrestTurnHorizatialSupportBackR);
                    LogUtil.info(str, "ID_HEADREST_TURN_HORIZATIAL_SUPPORT_BACK_R-" + headrestTurnHorizatialSupportBackR);
                    break;
                case 557856161:
                    int seatbackrestSupportSecond = uistyleEventChangeValue.getSeatbackrestSupportSecond();
                    this.c.setSeatbackrestSupportSecond(seatbackrestSupportSecond);
                    LogUtil.info(str, "ID_SEATBACKREST_SUPPORT_SECOND-" + seatbackrestSupportSecond);
                    break;
                case 557856162:
                    int seatbackrestSupportThird = uistyleEventChangeValue.getSeatbackrestSupportThird();
                    this.c.setSeatbackrestSupportThird(seatbackrestSupportThird);
                    LogUtil.info(str, "ID_SEATBACKREST_SUPPORT_THIRD-" + seatbackrestSupportThird);
                    break;
                case 557856163:
                    int seatbackrestSupportBackL = uistyleEventChangeValue.getSeatbackrestSupportBackL();
                    this.c.setSeatbackrestSupportBackL(seatbackrestSupportBackL);
                    LogUtil.info(str, "ID_SEATBACKREST_SUPPORT_BACK_L-" + seatbackrestSupportBackL);
                    break;
                case 557856164:
                    int seatbackrestSupportBackR = uistyleEventChangeValue.getSeatbackrestSupportBackR();
                    this.c.setSeatbackrestSupportBackR(seatbackrestSupportBackR);
                    LogUtil.info(str, "ID_SEATBACKREST_SUPPORT_BACK_R-" + seatbackrestSupportBackR);
                    break;
                case 557856165:
                    int headrestTurnHeightSupportSecond = uistyleEventChangeValue.getHeadrestTurnHeightSupportSecond();
                    this.c.setHeadrestTurnHeightSupportSecond(headrestTurnHeightSupportSecond);
                    LogUtil.info(str, "ID_HEADREST_TURN_HEIGHT_SUPPORT_SECOND-" + headrestTurnHeightSupportSecond);
                    break;
                case 557856166:
                    int headrestTurnHeightSupportThird = uistyleEventChangeValue.getHeadrestTurnHeightSupportThird();
                    this.c.setHeadrestTurnHeightSupportThird(headrestTurnHeightSupportThird);
                    LogUtil.info(str, "ID_HEADREST_TURN_HEIGHT_SUPPORT_THIRD-" + headrestTurnHeightSupportThird);
                    break;
                case 557856167:
                    int headrestTurnHeightSupportCopilot = uistyleEventChangeValue.getHeadrestTurnHeightSupportCopilot();
                    this.c.setHeadrestTurnHeightSupportCopilot(headrestTurnHeightSupportCopilot);
                    LogUtil.info(str, "ID_HEADREST_TURN_HEIGHT_SUPPORT_COPILOT-" + headrestTurnHeightSupportCopilot);
                    break;
                case 557856168:
                    int headrestTurnHeightSupportBackL = uistyleEventChangeValue.getHeadrestTurnHeightSupportBackL();
                    this.c.setHeadrestTurnHeightSupportBackL(headrestTurnHeightSupportBackL);
                    LogUtil.info(str, "ID_HEADREST_TURN_HEIGHT_SUPPORT_BACK_L-" + headrestTurnHeightSupportBackL);
                    break;
                case 557856169:
                    int headrestTurnHeightSupportBackR = uistyleEventChangeValue.getHeadrestTurnHeightSupportBackR();
                    this.c.setHeadrestTurnHeightSupportBackR(headrestTurnHeightSupportBackR);
                    LogUtil.info(str, "ID_HEADREST_TURN_HEIGHT_SUPPORT_BACK_R-" + headrestTurnHeightSupportBackR);
                    break;
                case 557856170:
                    int refrigeratorModeSupport = uistyleEventChangeValue.getRefrigeratorModeSupport();
                    this.c.setRefrigeratorModeSupport(refrigeratorModeSupport);
                    LogUtil.info(str, "ID_REFRIGERATOR_MODE_SUPPORT-" + refrigeratorModeSupport);
                    break;
                case 557856174:
                    int cushionSupportSecond = uistyleEventChangeValue.getCushionSupportSecond();
                    this.c.setCushionSupportSecond(cushionSupportSecond);
                    LogUtil.info(str, "ID_CUSHION_SUPPORT_SECOND-" + cushionSupportSecond);
                    break;
                case 557856175:
                    int cushionSupportCopilot = uistyleEventChangeValue.getCushionSupportCopilot();
                    this.c.setCushionSupportCopilot(cushionSupportCopilot);
                    LogUtil.info(str, "ID_CUSHION_SUPPORT_COPILOT-" + cushionSupportCopilot);
                    break;
                case 557856176:
                    int cushionSupportBackL = uistyleEventChangeValue.getCushionSupportBackL();
                    this.c.setCushionSupportBackL(cushionSupportBackL);
                    LogUtil.info(str, "ID_CUSHION_SUPPORT_BACK_L-" + cushionSupportBackL);
                    break;
                case 557856177:
                    int cushionSupportBackR = uistyleEventChangeValue.getCushionSupportBackR();
                    this.c.setCushionSupportBackR(cushionSupportBackR);
                    LogUtil.info(str, "ID_CUSHION_SUPPORT_BACK_R-" + cushionSupportBackR);
                    break;
                case 557856178:
                    int caliperSplintSupportFirst = uistyleEventChangeValue.getCaliperSplintSupportFirst();
                    this.c.setCaliperSplintSupportFirst(caliperSplintSupportFirst);
                    LogUtil.info(str, "ID_CALIPER_SPLINT_SUPPORT_FIRST-" + caliperSplintSupportFirst);
                    break;
                case 557856179:
                    int caliperSplintSupportSecond = uistyleEventChangeValue.getCaliperSplintSupportSecond();
                    this.c.setCaliperSplintSupportSecond(caliperSplintSupportSecond);
                    LogUtil.info(str, "ID_CALIPER_SPLINT_SUPPORT_SECOND-" + caliperSplintSupportSecond);
                    break;
                case 557856180:
                    int caliperSplintSupportThird = uistyleEventChangeValue.getCaliperSplintSupportThird();
                    this.c.setCaliperSplintSupportThird(caliperSplintSupportThird);
                    LogUtil.info(str, "ID_CALIPER_SPLINT_SUPPORT_THIRD-" + caliperSplintSupportThird);
                    break;
                case 557856181:
                    int caliperSplintSupportCopilot = uistyleEventChangeValue.getCaliperSplintSupportCopilot();
                    this.c.setCaliperSplintSupportCopilot(caliperSplintSupportCopilot);
                    LogUtil.info(str, "ID_CALIPER_SPLINT_SUPPORT_COPILOT-" + caliperSplintSupportCopilot);
                    break;
                case 557856182:
                    int caliperSplintSupportBackL = uistyleEventChangeValue.getCaliperSplintSupportBackL();
                    this.c.setCaliperSplintSupportBackL(caliperSplintSupportBackL);
                    LogUtil.info(str, "ID_CALIPER_SPLINT_SUPPORT_BACK_L-" + caliperSplintSupportBackL);
                    break;
                case 557856183:
                    int caliperSplintSupportBackR = uistyleEventChangeValue.getCaliperSplintSupportBackR();
                    this.c.setCaliperSplintSupportBackR(caliperSplintSupportBackR);
                    LogUtil.info(str, "ID_CALIPER_SPLINT_SUPPORT_BACK_R-" + caliperSplintSupportBackR);
                    break;
                case 557856184:
                    int ambientLightColorSupport = uistyleEventChangeValue.getAmbientLightColorSupport();
                    this.c.setAmbientLightColorSupport(ambientLightColorSupport);
                    LogUtil.info(str, "ID_AMBIENT_LIGHT_COLOR_SUPPORT-" + ambientLightColorSupport);
                    break;
                case 557856185:
                    int ambientLightSupport = uistyleEventChangeValue.getAmbientLightSupport();
                    this.c.setAmbientLightSupport(ambientLightSupport);
                    LogUtil.info(str, "ID_AMBIENT_LIGHT_SUPPORT-" + ambientLightSupport);
                    break;
                case 557856186:
                    int ambientAreaSupport = uistyleEventChangeValue.getAmbientAreaSupport();
                    this.c.setAmbientAreaSupport(ambientAreaSupport);
                    LogUtil.info(str, "ID_AMBIENT_AREA_SUPPORT-" + ambientAreaSupport);
                    break;
                case 557856188:
                    int ambientColorsSupport = uistyleEventChangeValue.getAmbientColorsSupport();
                    this.c.setAmbientColorsSupport(ambientColorsSupport);
                    LogUtil.info(str, "ID_AMBIENT_COLORS_SUPPORT-" + ambientColorsSupport);
                    break;
                case 557856189:
                    int resetAmbientSettings = uistyleEventChangeValue.getResetAmbientSettings();
                    this.c.setResetAmbientSettings(resetAmbientSettings);
                    LogUtil.info(str, "ID_ET_AMBIENT_SETTINGS-" + resetAmbientSettings);
                    break;
                case 557856191:
                    int hudHeightAdjustSupport = uistyleEventChangeValue.getHudHeightAdjustSupport();
                    this.c.setHudHeightAdjustSupport(hudHeightAdjustSupport);
                    LogUtil.info(str, "ID_HUD_HEIGHT_ADJUST_SUPPORT-" + hudHeightAdjustSupport);
                    break;
                case 557856192:
                    int hudLightSdjustSupport = uistyleEventChangeValue.getHudLightSdjustSupport();
                    this.c.setHudLightSdjustSupport(hudLightSdjustSupport);
                    LogUtil.info(str, "ID_HUD_LIGHT_SDJUST_SUPPORT-" + hudLightSdjustSupport);
                    break;
                case 557856193:
                    int instrumentThemeSupport = uistyleEventChangeValue.getInstrumentThemeSupport();
                    this.c.setInstrumentThemeSupport(instrumentThemeSupport);
                    LogUtil.info(str, "ID_INSTRUMENT_THEME_SUPPORT-" + instrumentThemeSupport);
                    break;
                case 557856195:
                    int instrumentContentSupport = uistyleEventChangeValue.getInstrumentContentSupport();
                    this.c.setInstrumentContentSupport(instrumentContentSupport);
                    LogUtil.info(str, "ID_INSTRUMENT_CONTENT_SUPPORT-" + instrumentContentSupport);
                    break;
                case 557856196:
                    int dischargeSupport = uistyleEventChangeValue.getDischargeSupport();
                    this.c.setDischargeSupport(dischargeSupport);
                    LogUtil.info(str, "ID_DISCHARGE_SUPPORT-" + dischargeSupport);
                    break;
                case 557856197:
                    int driveDistanceShowStandardSupport = uistyleEventChangeValue.getDriveDistanceShowStandardSupport();
                    this.c.setDriveDistanceShowStandardSupport(driveDistanceShowStandardSupport);
                    LogUtil.info(str, "ID_DRIVE_DISTANCE_SHOW_STANDARD_SUPPORT-" + driveDistanceShowStandardSupport);
                    break;
                case 557856198:
                    int ePedalStateSupport = uistyleEventChangeValue.getEPedalStateSupport();
                    this.c.setEPedalStateSupport(ePedalStateSupport);
                    LogUtil.info(str, "ID_E_PEDAL_STATE_SUPPORT-" + ePedalStateSupport);
                    break;
                case 557856199:
                    int driveDistanceShowModeSupport = uistyleEventChangeValue.getDriveDistanceShowModeSupport();
                    this.c.setDriveDistanceShowModeSupport(driveDistanceShowModeSupport);
                    LogUtil.info(str, "ID_DRIVE_DISTANCE_SHOW_MODE_SUPPORT-" + driveDistanceShowModeSupport);
                    break;
                case 557856201:
                    int secondSeatRememberSupport = uistyleEventChangeValue.getSecondSeatRememberSupport();
                    this.c.setSecondSeatRememberSupport(secondSeatRememberSupport);
                    LogUtil.info(str, "ID_SECOND_SEAT_REMEMBER_SUPPORT-" + secondSeatRememberSupport);
                    break;
                case 557856202:
                    int guestLeftSeatRememeSupportFirstL = uistyleEventChangeValue.getGuestLeftSeatRememeSupportFirstL();
                    this.c.setGuestLeftSeatRememeSupportFirstL(guestLeftSeatRememeSupportFirstL);
                    LogUtil.info(str, "ID_GUEST_LEFT_SEAT_REMEME_SUPPORT_FIRST_L-" + guestLeftSeatRememeSupportFirstL);
                    break;
                case 557856204:
                    int guestRightSeatRememeSupportFirstR = uistyleEventChangeValue.getGuestRightSeatRememeSupportFirstR();
                    this.c.setGuestRightSeatRememeSupportFirstR(guestRightSeatRememeSupportFirstR);
                    LogUtil.info(str, "ID_GUEST_RIGHT_SEAT_REMEME_SUPPORT_FIRST_R-" + guestRightSeatRememeSupportFirstR);
                    break;
                case 557856205:
                    int guestRightSeatRememeSupportSecondR = uistyleEventChangeValue.getGuestRightSeatRememeSupportSecondR();
                    this.c.setGuestRightSeatRememeSupportSecondR(guestRightSeatRememeSupportSecondR);
                    LogUtil.info(str, "ID_GUEST_RIGHT_SEAT_REMEME_SUPPORT_SECOND_R-" + guestRightSeatRememeSupportSecondR);
                    break;
                case 557856206:
                    int electroniChildLockConfigFirst = uistyleEventChangeValue.getElectroniChildLockConfigFirst();
                    this.c.setElectroniChildLockConfigFirst(electroniChildLockConfigFirst);
                    LogUtil.info(str, "ID_ELECTRONI_CHILD_LOCK_CONFIG_FIRST-" + electroniChildLockConfigFirst);
                    break;
                case 557856207:
                    int electroniChildLockConfigSecond = uistyleEventChangeValue.getElectroniChildLockConfigSecond();
                    this.c.setElectroniChildLockConfigSecond(electroniChildLockConfigSecond);
                    LogUtil.info(str, "ID_ELECTRONI_CHILD_LOCK_CONFIG_SECOND" + electroniChildLockConfigSecond);
                    break;
                case 557856209:
                    int windowAntiPinchConfigSecond = uistyleEventChangeValue.getWindowAntiPinchConfigSecond();
                    this.c.setWindowAntiPinchConfigSecond(windowAntiPinchConfigSecond);
                    LogUtil.info(str, "ID_WINDOW_ANTI_PINCH_CONFIG_SECOND-" + windowAntiPinchConfigSecond);
                    break;
                case 557859117:
                    int supportAiCare = uistyleEventChangeValue.getSupportAiCare();
                    this.c.setSupportAiCare(supportAiCare);
                    LogUtil.info(str, "ID_SUPPORT_AI_CARE-" + supportAiCare);
                    break;
                case 557859118:
                    int supportUrbanNavigation = uistyleEventChangeValue.getSupportUrbanNavigation();
                    this.c.setSupportUrbanNavigation(supportUrbanNavigation);
                    LogUtil.info(str, "ID_SUPPORT_URBAN_NAVIGATION-" + supportUrbanNavigation);
                    break;
                case 557870367:
                    int supportEndToEndNavigation = uistyleEventChangeValue.getSupportEndToEndNavigation();
                    this.c.setSupportEndToEndNavigation(supportEndToEndNavigation);
                    LogUtil.info(str, "ID_SUPPORT_END_TO_END_NAVIGATION-" + supportEndToEndNavigation);
                    break;
                default:
                    switch (i) {
                        case 557846530:
                            int supportRearAc = uistyleEventChangeValue.getSupportRearAc();
                            this.c.setSupportRearAc(supportRearAc);
                            LogUtil.info(str, "ID_SUPPORT_REAR_AC-" + supportRearAc);
                            break;
                        case 557846531:
                            int supportRearAcSwitch = uistyleEventChangeValue.getSupportRearAcSwitch();
                            this.c.setSupportRearAcSwitch(supportRearAcSwitch);
                            LogUtil.info(str, "ID_SUPPORT_REAR_AC_SWITCH-" + supportRearAcSwitch);
                            break;
                        default:
                            switch (i) {
                                case 557846533:
                                    int supportRearAcKeyLock = uistyleEventChangeValue.getSupportRearAcKeyLock();
                                    this.c.setSupportRearAcKeyLock(supportRearAcKeyLock);
                                    LogUtil.info(str, "ID_SUPPORT_REAR_AC_KEY_LOCK-" + supportRearAcKeyLock);
                                    break;
                                case 557846534:
                                    int supportRearAcWindModeControl = uistyleEventChangeValue.getSupportRearAcWindModeControl();
                                    this.c.setSupportRearAcWindModeControl(supportRearAcWindModeControl);
                                    LogUtil.info(str, "ID_SUPPORT_REAR_AC_WIND_MODE_CONTROL-" + supportRearAcWindModeControl);
                                    break;
                                default:
                                    switch (i) {
                                        case 557846536:
                                            int supportRearAcCompressor = uistyleEventChangeValue.getSupportRearAcCompressor();
                                            this.c.setSupportRearAcCompressor(supportRearAcCompressor);
                                            LogUtil.info(str, "ID_SUPPORT_REAR_AC_COMPRESSOR-" + supportRearAcCompressor);
                                            break;
                                        case 557846537:
                                            int supportRearAcTempertureControl = uistyleEventChangeValue.getSupportRearAcTempertureControl();
                                            this.c.setSupportRearAcTempertureControl(supportRearAcTempertureControl);
                                            LogUtil.info(str, "ID_SUPPORT_REAR_AC_TEMPERTURE_CONTROL-" + supportRearAcTempertureControl);
                                            break;
                                        case 557846538:
                                            int supportRearAcWindLevelControl = uistyleEventChangeValue.getSupportRearAcWindLevelControl();
                                            this.c.setSupportRearAcWindLevelControl(supportRearAcWindLevelControl);
                                            LogUtil.info(str, "ID_SUPPORT_REAR_AC_WIND_LEVEL_CONTROL-" + supportRearAcWindLevelControl);
                                            break;
                                        default:
                                            switch (i) {
                                                case 557856000:
                                                    int preSaleModelSupport = uistyleEventChangeValue.getPreSaleModelSupport();
                                                    this.c.setPreSaleModelSupport(preSaleModelSupport);
                                                    LogUtil.info(str, "ID_PRE_SALE_MODEL_SUPPORT-" + preSaleModelSupport);
                                                    break;
                                                case 557856001:
                                                    int carNum = uistyleEventChangeValue.getCarNum();
                                                    this.c.setCarNum(carNum);
                                                    LogUtil.info(str, "ID_CAR_NUM-" + carNum);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 557856003:
                                                            int driveConfig = uistyleEventChangeValue.getDriveConfig();
                                                            this.c.setDriveConfig(driveConfig);
                                                            LogUtil.info(str, "ID_DRIVE_CONFIG-" + driveConfig);
                                                            break;
                                                        case 557856004:
                                                            int dMType = uistyleEventChangeValue.getDMType();
                                                            this.c.setDMType(dMType);
                                                            LogUtil.info(str, "ID_D_M_TYPE-" + dMType);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 557856019:
                                                                    int supportSeatMassageMain = uistyleEventChangeValue.getSupportSeatMassageMain();
                                                                    this.c.setSupportSeatMassageMain(supportSeatMassageMain);
                                                                    LogUtil.info(str, "ID_SUPPORT_SEAT_MASSAGE_MAIN-" + supportSeatMassageMain);
                                                                    break;
                                                                case 557856020:
                                                                    int supportSeatMassageDeputy = uistyleEventChangeValue.getSupportSeatMassageDeputy();
                                                                    this.c.setSupportSeatMassageDeputy(supportSeatMassageDeputy);
                                                                    LogUtil.info(str, "ID_SUPPORT_SEAT_MASSAGE_DEPUTY-" + supportSeatMassageDeputy);
                                                                    break;
                                                                case 557856021:
                                                                    int supportSeatMassageLeftRear = uistyleEventChangeValue.getSupportSeatMassageLeftRear();
                                                                    this.c.setSupportSeatMassageLeftRear(supportSeatMassageLeftRear);
                                                                    LogUtil.info(str, "ID_SUPPORT_SEAT_MASSAGE_LEFT_REAR-" + supportSeatMassageLeftRear);
                                                                    break;
                                                                case 557856022:
                                                                    int supportSeatMassageRightRear = uistyleEventChangeValue.getSupportSeatMassageRightRear();
                                                                    this.c.setSupportSeatMassageRightRear(supportSeatMassageRightRear);
                                                                    LogUtil.info(str, "ID_SUPPORT_SEAT_MASSAGE_RIGHT_REAR-" + supportSeatMassageRightRear);
                                                                    break;
                                                                case 557856023:
                                                                    int leftPlatformConfigs = uistyleEventChangeValue.getLeftPlatformConfigs();
                                                                    this.c.setLeftPlatformConfigs(leftPlatformConfigs);
                                                                    LogUtil.info(str, "ID_LEFT_PLATFORM_CONFIGS-" + leftPlatformConfigs);
                                                                    break;
                                                                case 557856024:
                                                                    int supportPM25Mode = uistyleEventChangeValue.getSupportPM25Mode();
                                                                    this.c.setSupportPM25Mode(supportPM25Mode);
                                                                    LogUtil.info(str, "ID_SUPPORT_P_M25_MODE-" + supportPM25Mode);
                                                                    break;
                                                                case 557856025:
                                                                    int supportNegativeIon = uistyleEventChangeValue.getSupportNegativeIon();
                                                                    this.c.setSupportNegativeIon(supportNegativeIon);
                                                                    LogUtil.info(str, "ID_SUPPORT_NEGATIVE_ION-" + supportNegativeIon);
                                                                    break;
                                                                case 557856026:
                                                                    int supportHighTemperatureSterilize = uistyleEventChangeValue.getSupportHighTemperatureSterilize();
                                                                    this.c.setSupportHighTemperatureSterilize(supportHighTemperatureSterilize);
                                                                    LogUtil.info(str, "ID_SUPPORT_HIGH_TEMPERATURE_STERILIZE-" + supportHighTemperatureSterilize);
                                                                    break;
                                                                case 557856027:
                                                                    int supportFragrance = uistyleEventChangeValue.getSupportFragrance();
                                                                    this.c.setSupportFragrance(supportFragrance);
                                                                    LogUtil.info(str, "ID_SUPPORT_FRAGRANCE-" + supportFragrance);
                                                                    break;
                                                                case 557856028:
                                                                    int supportFragranceTypeOne = uistyleEventChangeValue.getSupportFragranceTypeOne();
                                                                    this.c.setSupportFragranceTypeOne(supportFragranceTypeOne);
                                                                    LogUtil.info(str, "ID_SUPPORT_FRAGRANCE_TYPE_ONE-" + supportFragranceTypeOne);
                                                                    break;
                                                                case 557856029:
                                                                    int supportFragranceTypeTwo = uistyleEventChangeValue.getSupportFragranceTypeTwo();
                                                                    this.c.setSupportFragranceTypeTwo(supportFragranceTypeTwo);
                                                                    LogUtil.info(str, "ID_SUPPORT_FRAGRANCE_TYPE_TWO-" + supportFragranceTypeTwo);
                                                                    break;
                                                                case 557856030:
                                                                    int supportFragranceTypeThree = uistyleEventChangeValue.getSupportFragranceTypeThree();
                                                                    this.c.setSupportFragranceTypeThree(supportFragranceTypeThree);
                                                                    LogUtil.info(str, "ID_SUPPORT_FRAGRANCE_TYPE_THREE-" + supportFragranceTypeThree);
                                                                    break;
                                                                case 557856031:
                                                                    int carType = uistyleEventChangeValue.getCarType();
                                                                    this.c.setCarType(carType);
                                                                    LogUtil.info(str, "ID_CAR_TYPE-" + carType);
                                                                    break;
                                                                case 557856032:
                                                                    int supportLampSingnal = uistyleEventChangeValue.getSupportLampSingnal();
                                                                    this.c.setSupportLampSingnal(supportLampSingnal);
                                                                    LogUtil.info(str, "ID_SUPPORT_LAMP_SINGNAL-" + supportLampSingnal);
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 557856034:
                                                                            int headlampSupport = uistyleEventChangeValue.getHeadlampSupport();
                                                                            this.c.setHeadlampSupport(headlampSupport);
                                                                            LogUtil.info(str, "ID_HEADLAMP_SUPPORT-" + headlampSupport);
                                                                            break;
                                                                        case 557856035:
                                                                            int headlampLevelConfig = uistyleEventChangeValue.getHeadlampLevelConfig();
                                                                            this.c.setHeadlampLevelConfig(headlampLevelConfig);
                                                                            LogUtil.info(str, "ID_HEADLAMP_LEVEL_CONFIG-" + headlampLevelConfig);
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case 557856039:
                                                                                    int resetBackAndLeaveDelaySupport = uistyleEventChangeValue.getResetBackAndLeaveDelaySupport();
                                                                                    this.c.setResetBackAndLeaveDelaySupport(resetBackAndLeaveDelaySupport);
                                                                                    LogUtil.info(str, "ID_RESET_BACK_AND_LEAVE_DELAY_SUPPORT-" + resetBackAndLeaveDelaySupport);
                                                                                    break;
                                                                                case 557856040:
                                                                                    int usherLightSupport = uistyleEventChangeValue.getUsherLightSupport();
                                                                                    this.c.setUsherLightSupport(usherLightSupport);
                                                                                    LogUtil.info(str, "ID_USHER_LIGHT_SUPPORT-" + usherLightSupport);
                                                                                    break;
                                                                                case 557856041:
                                                                                    int dayLightSupport = uistyleEventChangeValue.getDayLightSupport();
                                                                                    this.c.setDayLightSupport(dayLightSupport);
                                                                                    LogUtil.info(str, "ID_DAY_LIGHT_SUPPORT-" + dayLightSupport);
                                                                                    break;
                                                                                case 557856042:
                                                                                    int resetDayLight = uistyleEventChangeValue.getResetDayLight();
                                                                                    this.c.setResetDayLight(resetDayLight);
                                                                                    LogUtil.info(str, "ID_RESET_DAY_LIGHT-" + resetDayLight);
                                                                                    break;
                                                                                case 557856043:
                                                                                    int doorLightSupport = uistyleEventChangeValue.getDoorLightSupport();
                                                                                    this.c.setDoorLightSupport(doorLightSupport);
                                                                                    LogUtil.info(str, "ID_DOOR_LIGHT_SUPPORT-" + doorLightSupport);
                                                                                    break;
                                                                                case 557856044:
                                                                                    int supportReverseRadar = uistyleEventChangeValue.getSupportReverseRadar();
                                                                                    this.c.setSupportReverseRadar(supportReverseRadar);
                                                                                    LogUtil.info(str, "ID_SUPPORT_REVERSE_RADAR-" + supportReverseRadar);
                                                                                    break;
                                                                                case 557856045:
                                                                                    int iF = f(uistyleEventChangeValue.getIntelligentKeepTemSupport());
                                                                                    this.c.setIntelligentKeepTemSupport(iF);
                                                                                    LogUtil.info(str, "ID_INTELLIGENT_KEEP_TEM_SUPPORT-" + iF);
                                                                                    break;
                                                                                case 557856046:
                                                                                    int chargeToaggleGuardSupport = uistyleEventChangeValue.getChargeToaggleGuardSupport();
                                                                                    this.c.setChargeToaggleGuardSupport(chargeToaggleGuardSupport);
                                                                                    LogUtil.info(str, "ID_CHARGE_TOAGGLE_GUARD_SUPPORT-" + chargeToaggleGuardSupport);
                                                                                    break;
                                                                                case 557856047:
                                                                                    int chargeLimitSupport = uistyleEventChangeValue.getChargeLimitSupport();
                                                                                    this.c.setChargeLimitSupport(chargeLimitSupport);
                                                                                    LogUtil.info(str, "ID_CHARGE_LIMIT_SUPPORT-" + chargeLimitSupport);
                                                                                    break;
                                                                                case 557856048:
                                                                                    int oBCType = uistyleEventChangeValue.getOBCType();
                                                                                    this.c.setOBCType(oBCType);
                                                                                    LogUtil.info(str, "ID_O_B_C_TYPE-" + oBCType);
                                                                                    break;
                                                                                default:
                                                                                    switch (i) {
                                                                                        case 557856082:
                                                                                            int supportLaneChangeAssist = uistyleEventChangeValue.getSupportLaneChangeAssist();
                                                                                            this.c.setSupportLaneChangeAssist(supportLaneChangeAssist);
                                                                                            LogUtil.info(str, "ID_SUPPORT_LANE_CHANGE_ASSIST-" + supportLaneChangeAssist);
                                                                                            break;
                                                                                        case 557856083:
                                                                                            int supportLaneDepartureWarning = uistyleEventChangeValue.getSupportLaneDepartureWarning();
                                                                                            this.c.setSupportLaneDepartureWarning(supportLaneDepartureWarning);
                                                                                            LogUtil.info(str, "ID_SUPPORT_LANE_DEPARTURE_WARNING-" + supportLaneDepartureWarning);
                                                                                            break;
                                                                                        case 557856084:
                                                                                            int supportLaneKeep = uistyleEventChangeValue.getSupportLaneKeep();
                                                                                            this.c.setSupportLaneKeep(supportLaneKeep);
                                                                                            LogUtil.info(str, "ID_SUPPORT_LANE_KEEP-" + supportLaneKeep);
                                                                                            break;
                                                                                        case 557856085:
                                                                                            int supportLaneDepartureStop = uistyleEventChangeValue.getSupportLaneDepartureStop();
                                                                                            this.c.setSupportLaneDepartureStop(supportLaneDepartureStop);
                                                                                            LogUtil.info(str, "ID_SUPPORT_LANE_DEPARTURE_STOP-" + supportLaneDepartureStop);
                                                                                            break;
                                                                                        case 557856086:
                                                                                            int supportEmergencyLaneKeepAssist = uistyleEventChangeValue.getSupportEmergencyLaneKeepAssist();
                                                                                            this.c.setSupportEmergencyLaneKeepAssist(supportEmergencyLaneKeepAssist);
                                                                                            LogUtil.info(str, "ID_SUPPORT_EMERGENCY_LANE_KEEP_ASSIST-" + supportEmergencyLaneKeepAssist);
                                                                                            break;
                                                                                        case 557856087:
                                                                                            int supportTrafficSignRecogniton = uistyleEventChangeValue.getSupportTrafficSignRecogniton();
                                                                                            this.c.setSupportTrafficSignRecogniton(supportTrafficSignRecogniton);
                                                                                            LogUtil.info(str, "ID_SUPPORT_TRAFFIC_SIGN_RECOGNITON-" + supportTrafficSignRecogniton);
                                                                                            break;
                                                                                        case 557856088:
                                                                                            int supportIntellignetSpeedLimitAlert = uistyleEventChangeValue.getSupportIntellignetSpeedLimitAlert();
                                                                                            this.c.setSupportIntellignetSpeedLimitAlert(supportIntellignetSpeedLimitAlert);
                                                                                            LogUtil.info(str, "ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_ALERT-" + supportIntellignetSpeedLimitAlert);
                                                                                            break;
                                                                                        case 557856089:
                                                                                            int supportIntellignetSpeedLimitControl = uistyleEventChangeValue.getSupportIntellignetSpeedLimitControl();
                                                                                            this.c.setSupportIntellignetSpeedLimitControl(supportIntellignetSpeedLimitControl);
                                                                                            LogUtil.info(str, "ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_CONTROL-" + supportIntellignetSpeedLimitControl);
                                                                                            break;
                                                                                        case 557856090:
                                                                                            int supportTrafficLightAttention = uistyleEventChangeValue.getSupportTrafficLightAttention();
                                                                                            this.c.setSupportTrafficLightAttention(supportTrafficLightAttention);
                                                                                            LogUtil.info(str, "ID_SUPPORT_TRAFFIC_LIGHT_ATTENTION-" + supportTrafficLightAttention);
                                                                                            break;
                                                                                        case 557856091:
                                                                                            int supportDoorOpenWarning = uistyleEventChangeValue.getSupportDoorOpenWarning();
                                                                                            this.c.setSupportDoorOpenWarning(supportDoorOpenWarning);
                                                                                            LogUtil.info(str, "ID_SUPPORT_DOOR_OPEN_WARNING-" + supportDoorOpenWarning);
                                                                                            break;
                                                                                        case 557856092:
                                                                                            int supportRearCollisionWarning = uistyleEventChangeValue.getSupportRearCollisionWarning();
                                                                                            this.c.setSupportRearCollisionWarning(supportRearCollisionWarning);
                                                                                            LogUtil.info(str, "ID_SUPPORT_REAR_COLLISION_WARNING-" + supportRearCollisionWarning);
                                                                                            break;
                                                                                        case 557856093:
                                                                                            int supportRearTrafficCrossingAlert = uistyleEventChangeValue.getSupportRearTrafficCrossingAlert();
                                                                                            this.c.setSupportRearTrafficCrossingAlert(supportRearTrafficCrossingAlert);
                                                                                            LogUtil.info(str, "ID_SUPPORT_REAR_TRAFFIC_CROSSING_ALERT-" + supportRearTrafficCrossingAlert);
                                                                                            break;
                                                                                        case 557856094:
                                                                                            int supportFrontCrossingTrafficAlert = uistyleEventChangeValue.getSupportFrontCrossingTrafficAlert();
                                                                                            this.c.setSupportFrontCrossingTrafficAlert(supportFrontCrossingTrafficAlert);
                                                                                            LogUtil.info(str, "ID_SUPPORT_FRONT_CROSSING_TRAFFIC_ALERT-" + supportFrontCrossingTrafficAlert);
                                                                                            break;
                                                                                        case 557856095:
                                                                                            int supportFrontCrossingTrafficBrake = uistyleEventChangeValue.getSupportFrontCrossingTrafficBrake();
                                                                                            this.c.setSupportFrontCrossingTrafficBrake(supportFrontCrossingTrafficBrake);
                                                                                            LogUtil.info(str, "ID_SUPPORT_FRONT_CROSSING_TRAFFIC_BRAKE-" + supportFrontCrossingTrafficBrake);
                                                                                            break;
                                                                                        case 557856096:
                                                                                            int supportShakeAlertSecond = uistyleEventChangeValue.getSupportShakeAlertSecond();
                                                                                            this.c.setSupportShakeAlertSecond(supportShakeAlertSecond);
                                                                                            LogUtil.info(str, "ID_SUPPORT_SHAKE_ALERT_SECOND-" + supportShakeAlertSecond);
                                                                                            break;
                                                                                        case 557856097:
                                                                                            int supportPredictCrashAlertModeFirst = uistyleEventChangeValue.getSupportPredictCrashAlertModeFirst();
                                                                                            this.c.setSupportPredictCrashAlertModeFirst(supportPredictCrashAlertModeFirst);
                                                                                            LogUtil.info(str, "ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_FIRST-" + supportPredictCrashAlertModeFirst);
                                                                                            break;
                                                                                        case 557856098:
                                                                                            int supportPredictCrashAlertModeSecond = uistyleEventChangeValue.getSupportPredictCrashAlertModeSecond();
                                                                                            this.c.setSupportPredictCrashAlertModeSecond(supportPredictCrashAlertModeSecond);
                                                                                            LogUtil.info(str, "ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_SECOND-" + supportPredictCrashAlertModeSecond);
                                                                                            break;
                                                                                        case 557856099:
                                                                                            int supportAiEmergencyBrake = uistyleEventChangeValue.getSupportAiEmergencyBrake();
                                                                                            this.c.setSupportAiEmergencyBrake(supportAiEmergencyBrake);
                                                                                            LogUtil.info(str, "ID_SUPPORT_AI_EMERGENCY_BRAKE-" + supportAiEmergencyBrake);
                                                                                            break;
                                                                                        case 557856100:
                                                                                            int supportBlindSpotAssistSecond = uistyleEventChangeValue.getSupportBlindSpotAssistSecond();
                                                                                            this.c.setSupportBlindSpotAssistSecond(supportBlindSpotAssistSecond);
                                                                                            LogUtil.info(str, "ID_SUPPORT_BLIND_SPOT_ASSIST_SECOND-" + supportBlindSpotAssistSecond);
                                                                                            break;
                                                                                        case 557856101:
                                                                                            int supportMistakeGas = uistyleEventChangeValue.getSupportMistakeGas();
                                                                                            this.c.setSupportMistakeGas(supportMistakeGas);
                                                                                            LogUtil.info(str, "ID_SUPPORT_MISTAKE_GAS-" + supportMistakeGas);
                                                                                            break;
                                                                                        case 557856102:
                                                                                            int supportAutoParking = uistyleEventChangeValue.getSupportAutoParking();
                                                                                            this.c.setSupportAutoParking(supportAutoParking);
                                                                                            LogUtil.info(str, "ID_SUPPORT_AUTO_PARKING-" + supportAutoParking);
                                                                                            break;
                                                                                        case 557856103:
                                                                                            int supportBlindSpotAssistFirst = uistyleEventChangeValue.getSupportBlindSpotAssistFirst();
                                                                                            this.c.setSupportBlindSpotAssistFirst(supportBlindSpotAssistFirst);
                                                                                            LogUtil.info(str, "ID_SUPPORT_BLIND_SPOT_ASSIST_FIRST-" + supportBlindSpotAssistFirst);
                                                                                            break;
                                                                                        default:
                                                                                            Log.verbose(str, "break other properties ,except uistyle");
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
                    break;
            }
            this.e.m(i, i2, this.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void o() {
        LogUtil.info(g, "quit");
        if (this.f80a != null) {
            a aVar = this.f81b;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.f80a.quitSafely();
        }
    }

    @Deprecated
    public int ambientFactoryMode(int i) {
        LogUtil.verbose(g, "ambientFactoryMode" + i);
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            return carVendorUistyleManager.getresetHeadlamp();
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i) {
        LogUtil.verbose(g, "backAndLeaveDelayFactoryMode" + i);
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            carVendorUistyleManager.setResetBackAndLeaveDelaySupport(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int d(int i) {
        return i != 0 ? -1 : 2;
    }

    @Deprecated
    public int dayLightFactory(int i) {
        LogUtil.verbose(g, "dayLightFactory");
        return 0;
    }

    public int f(int i) {
        if (i != 1) {
            return i != 2 ? -1 : 1;
        }
        return 2;
    }

    @Deprecated
    public int getACAutoAirSupport() {
        String str = g;
        LogUtil.verbose(str, "getACAutoAirSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int aCAutoAirSupport = carVendorUistyleManager.getACAutoAirSupport();
            LogUtil.verbose(str, "getACAutoAirSupport=" + aCAutoAirSupport);
            return aCAutoAirSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getACBTWindSupport() {
        String str = g;
        LogUtil.verbose(str, "getACBTWindSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int aCBTWindSupport = carVendorUistyleManager.getACBTWindSupport();
            LogUtil.verbose(str, "getACBTWindSupport=" + aCBTWindSupport);
            return aCBTWindSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getACPauseCycleSupport() {
        String str = g;
        LogUtil.verbose(str, "getACPauseCycleSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int aCPauseCycleSupport = carVendorUistyleManager.getACPauseCycleSupport();
            LogUtil.verbose(str, "getACPauseCycleSupport=" + aCPauseCycleSupport);
            return aCPauseCycleSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getACTunnelCycleSupport() {
        String str = g;
        LogUtil.verbose(str, "getACTunnelCycleSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int aCTunnelCycleSupport = carVendorUistyleManager.getACTunnelCycleSupport();
            LogUtil.verbose(str, "getACTunnelCycleSupport=" + aCTunnelCycleSupport);
            return aCTunnelCycleSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getADASSystemState() {
        String str = g;
        LogUtil.verbose(str, "getADASSystemState is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557870364, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getADASSystemState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getAcFaultNumShownState() {
        String str = g;
        LogUtil.verbose(str, "getAcFaultNumShownState");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int acFaultNumShownState = carVendorUistyleManager.getAcFaultNumShownState();
            LogUtil.verbose(str, "getAcFaultNumShownState=" + acFaultNumShownState);
            return acFaultNumShownState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getAcType() {
        String str = g;
        LogUtil.verbose(str, "getAcType");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int acType = carVendorUistyleManager.getAcType();
            LogUtil.verbose(str, "getAcType=" + acType);
            return acType;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcWarmSupport() {
        String str = g;
        LogUtil.verbose(str, "getAcWarmSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int acWarmSupport = carVendorUistyleManager.getAcWarmSupport();
            LogUtil.verbose(str, "getAcWarmSupport=" + acWarmSupport);
            return acWarmSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        String str = g;
        LogUtil.verbose(str, "getAcWarmTypeOnlineState");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int acWarmTypeOnlineState = carVendorUistyleManager.getAcWarmTypeOnlineState();
            LogUtil.verbose(str, "getAcWarmTypeOnlineState=" + acWarmTypeOnlineState);
            return acWarmTypeOnlineState;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getAcWindModeNum() {
        String str = g;
        LogUtil.verbose(str, "getAcWindModeNum");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int acWindModeNum = carVendorUistyleManager.getAcWindModeNum();
            LogUtil.verbose(str, "getAcWindModeNum=" + acWindModeNum);
            return acWindModeNum;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAcceleratorPedalSuppressionSystem() {
        int acceleratorPedalSuppressionSystemR;
        String str = g;
        LogUtil.verbose(str, " getAcceleratorPedalSuppressionSystem.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                acceleratorPedalSuppressionSystemR = carVendorUistyleManager.getAcceleratorPedalSuppressionSystemR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                acceleratorPedalSuppressionSystemR = -1;
            }
            LogUtil.verbose(str, " getAcceleratorPedalSuppressionSystem.out=" + acceleratorPedalSuppressionSystemR);
            return acceleratorPedalSuppressionSystemR;
        }
        LogUtil.verbose(str, " getAcceleratorPedalSuppressionSystem == null");
        acceleratorPedalSuppressionSystemR = -1;
        LogUtil.verbose(str, " getAcceleratorPedalSuppressionSystem.out=" + acceleratorPedalSuppressionSystemR);
        return acceleratorPedalSuppressionSystemR;
    }

    public int getAdaptiveHeadlightsSupport() {
        LogUtil.verbose(g, "getAdaptiveHeadlightsSupport");
        return 0;
    }

    public int getAebDoubleConfirm() {
        int aebDoubleConfirmR;
        String str = g;
        LogUtil.verbose(str, " getAebDoubleConfirm.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                aebDoubleConfirmR = carVendorUistyleManager.getAebDoubleConfirmR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                aebDoubleConfirmR = -1;
            }
            LogUtil.verbose(str, " getAebDoubleConfirm.out service =" + aebDoubleConfirmR);
            return aebDoubleConfirmR;
        }
        LogUtil.verbose(str, " getAebDoubleConfirm.null");
        aebDoubleConfirmR = -1;
        LogUtil.verbose(str, " getAebDoubleConfirm.out service =" + aebDoubleConfirmR);
        return aebDoubleConfirmR;
    }

    public int getAllRearSeatOneButtonDownSwitchDisplayConfig() {
        String str = g;
        LogUtil.verbose(str, "getAllRearSeatOneButtonDownSwitchDisplayConfig is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859142, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAllRearSeatOneButtonDownSwitchDisplayConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAmbientAreaSupport() {
        String str = g;
        LogUtil.verbose(str, "getAmbientAreaSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int ambientAreaSupport = carVendorUistyleManager.getAmbientAreaSupport();
            LogUtil.verbose(str, "getAmbientAreaSupport=" + ambientAreaSupport);
            return ambientAreaSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmbientColorsSupport() {
        String str = g;
        LogUtil.verbose(str, "getAmbientColorsSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int ambientColorsSupport = carVendorUistyleManager.getAmbientColorsSupport();
            LogUtil.verbose(str, "getAmbientColorsSupport=" + ambientColorsSupport);
            return ambientColorsSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmbientLightColorSupport() {
        String str = g;
        LogUtil.verbose(str, "getAmbientLightColorSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856184, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAmbientLightColorSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAmbientLightSupport() {
        String str = g;
        LogUtil.verbose(str, "getAmbientLightSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int ambientLightSupport = carVendorUistyleManager.getAmbientLightSupport();
            LogUtil.verbose(str, "getAmbientLightSupport=" + ambientLightSupport);
            return ambientLightSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmbientModeSwitchSettings() {
        String str = g;
        LogUtil.verbose(str, "getAmbientModeSwitchSettings is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859125, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAmbientModeSwitchSettings prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getAmbientMusicModeSupport() {
        String str = g;
        LogUtil.verbose(str, "getAmbientMusicModeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int ambientMusicModeSupport = carVendorUistyleManager.getAmbientMusicModeSupport();
            LogUtil.verbose(str, "getAmbientMusicModeSupport=" + ambientMusicModeSupport);
            return ambientMusicModeSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmplifierConfiguration() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557879608, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(g, "getAmplifierConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getAppUseLimitSupport() {
        String str = g;
        LogUtil.verbose(str, "getAppUseLimitSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int appUseLimitSupport = carVendorUistyleManager.getAppUseLimitSupport();
            LogUtil.verbose(str, "getAppUseLimitSupport=" + appUseLimitSupport);
            return appUseLimitSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        String str = g;
        LogUtil.verbose(str, "getAutoExternalRearMirrorFollowUpSwitchSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int autoExternalRearMirrorFollowUpSwitchSupport = carVendorUistyleManager.getAutoExternalRearMirrorFollowUpSwitchSupport();
            LogUtil.verbose(str, "getAutoExternalRearMirrorFollowUpSwitchSupport=" + autoExternalRearMirrorFollowUpSwitchSupport);
            return autoExternalRearMirrorFollowUpSwitchSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        String str = g;
        LogUtil.verbose(str, "getAutoPowerOffSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int autoPowerOffSupport = carVendorUistyleManager.getAutoPowerOffSupport();
            LogUtil.verbose(str, "getAutoPowerOffSupport=" + autoPowerOffSupport);
            return autoPowerOffSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        String str = g;
        LogUtil.verbose(str, "getAutoPowerOnStateSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int autoPowerOnStateSupport = carVendorUistyleManager.getAutoPowerOnStateSupport();
            LogUtil.verbose(str, "getAutoPowerOnStateSupport=" + autoPowerOnStateSupport);
            return autoPowerOnStateSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAutoPurificationSupport() {
        String str = g;
        LogUtil.verbose(str, "getAutoPurificationSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int supportPM25Mode = carVendorUistyleManager.getSupportPM25Mode();
            LogUtil.verbose(str, "getSupportPM25Mode=" + supportPM25Mode);
            return supportPM25Mode;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getAutoReportContentSupport(int i) {
        LogUtil.verbose(g, "getAutoReportContentSupport");
        return 0;
    }

    public int getAutoWiperSupport() {
        String str = g;
        LogUtil.verbose(str, "getAutoWiperSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int autoWiperSupport = carVendorUistyleManager.getAutoWiperSupport();
            LogUtil.verbose(str, "getAutoWiperSupport=" + autoWiperSupport);
            return autoWiperSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAverageSpeedInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getAverageSpeedInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875218, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getAverageSpeedInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getBackHomeDelaySupport() {
        String str = g;
        LogUtil.verbose(str, "getBackHomeDelaySupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int backHomeDelaySupport = carVendorUistyleManager.getBackHomeDelaySupport();
            LogUtil.verbose(str, "getBackHomeDelaySupport=" + backHomeDelaySupport);
            return backHomeDelaySupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859134, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859144, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getBatteryHealthConfig() {
        int batteryHealthConfigR;
        String str = g;
        LogUtil.verbose(str, " getBatteryHealthConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                batteryHealthConfigR = carVendorUistyleManager.getBatteryHealthConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                batteryHealthConfigR = -1;
            }
            LogUtil.verbose(str, " getBatteryHealthConfig.out=" + batteryHealthConfigR);
            return batteryHealthConfigR;
        }
        LogUtil.verbose(str, " getBatteryHealthConfig.null == mCarVendorUiStyleManager");
        batteryHealthConfigR = -1;
        LogUtil.verbose(str, " getBatteryHealthConfig.out=" + batteryHealthConfigR);
        return batteryHealthConfigR;
    }

    public int getBrakePedalModeSupport() {
        String str = g;
        LogUtil.verbose(str, "getBrakePedalModeSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856056, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getBrakePedalModeSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getBsisSupportR() {
        String str = g;
        LogUtil.info(str, "getBsisSupportR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859167, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getBsisSupportR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getCabinMovementMonitoringFunction() {
        int cabinMovementMonitoringFunctionR;
        String str = g;
        LogUtil.verbose(str, " getCabinMovementMonitoringFunction.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                cabinMovementMonitoringFunctionR = carVendorUistyleManager.getCabinMovementMonitoringFunctionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                cabinMovementMonitoringFunctionR = -1;
            }
            LogUtil.verbose(str, " getCabinMovementMonitoringFunction.out=" + cabinMovementMonitoringFunctionR);
            return cabinMovementMonitoringFunctionR;
        }
        LogUtil.verbose(str, " getCabinMovementMonitoringFunction == null");
        cabinMovementMonitoringFunctionR = -1;
        LogUtil.verbose(str, " getCabinMovementMonitoringFunction.out=" + cabinMovementMonitoringFunctionR);
        return cabinMovementMonitoringFunctionR;
    }

    public int getCaliperSplintSupport(int i) {
        String str = g;
        LogUtil.verbose(str, "getCaliperSplintSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        int caliperSplintSupportFirst = -1;
        try {
            if (i == 1) {
                caliperSplintSupportFirst = carVendorUistyleManager.getCaliperSplintSupportFirst();
                LogUtil.verbose(str, "getCaliperSplintSupportFirst=" + caliperSplintSupportFirst);
            } else if (i == 2) {
                caliperSplintSupportFirst = carVendorUistyleManager.getCaliperSplintSupportCopilot();
                LogUtil.verbose(str, "getCaliperSplintSupportCopilot=" + caliperSplintSupportFirst);
            } else {
                if (i != 3) {
                    if (i == 4) {
                        caliperSplintSupportFirst = carVendorUistyleManager.getCaliperSplintSupportBackR();
                        LogUtil.verbose(str, "getCaliperSplintSupportBackR=" + caliperSplintSupportFirst);
                    }
                    return caliperSplintSupportFirst;
                }
                caliperSplintSupportFirst = carVendorUistyleManager.getCaliperSplintSupportBackL();
                LogUtil.verbose(str, "getCaliperSplintSupportBackL=" + caliperSplintSupportFirst);
            }
            return caliperSplintSupportFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCameraEerRomDeviceAddress() {
        String str = g;
        LogUtil.verbose(str, "getCameraEerRomDeviceAddress is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cameraEerRomDeviceAddressR = carVendorUistyleManager.getCameraEerRomDeviceAddressR();
            LogUtil.verbose(str, "getCameraEerRomDeviceAddressR=" + cameraEerRomDeviceAddressR);
            return cameraEerRomDeviceAddressR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCameraIspId() {
        String str = g;
        LogUtil.verbose(str, "getCameraIspId is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cameraIspIdR = carVendorUistyleManager.getCameraIspIdR();
            LogUtil.verbose(str, "getCameraIspIdR=" + cameraIspIdR);
            return cameraIspIdR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCameraLensId() {
        String str = g;
        LogUtil.verbose(str, "getCameraLensId is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cameraLensIdR = carVendorUistyleManager.getCameraLensIdR();
            LogUtil.verbose(str, "getCameraLensIdR=" + cameraLensIdR);
            return cameraLensIdR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCameraSerializerId() {
        String str = g;
        LogUtil.verbose(str, "getCameraSerializerId is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cameraSerializerIdR = carVendorUistyleManager.getCameraSerializerIdR();
            LogUtil.verbose(str, "getCameraSerializerIdR=" + cameraSerializerIdR);
            return cameraSerializerIdR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCameraSupplierAndTestBoardInfo() {
        String str = g;
        LogUtil.verbose(str, "getCameraSupplierAndTestBoardInfo is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cameraSupplierAndTestBoardInfoR = carVendorUistyleManager.getCameraSupplierAndTestBoardInfoR();
            LogUtil.verbose(str, "getCameraSupplierAndTestBoardInfoR=" + cameraSupplierAndTestBoardInfoR);
            return cameraSupplierAndTestBoardInfoR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCameraVideoFormat() {
        String str = g;
        LogUtil.verbose(str, "getCameraVideoFormat is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cameraVideoFormatR = carVendorUistyleManager.getCameraVideoFormatR();
            LogUtil.verbose(str, "getCameraVideoFormatR=" + cameraVideoFormatR);
            return cameraVideoFormatR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCameraVideoFrameRate() {
        String str = g;
        LogUtil.verbose(str, "getCameraVideoFrameRate is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cameraVideoFrameRateR = carVendorUistyleManager.getCameraVideoFrameRateR();
            LogUtil.verbose(str, "getCameraVideoFrameRateR=" + cameraVideoFrameRateR);
            return cameraVideoFrameRateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCameraVideoResolution() {
        String str = g;
        LogUtil.verbose(str, "getCameraVideoResolution is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cameraVideoResolutionR = carVendorUistyleManager.getCameraVideoResolutionR();
            LogUtil.verbose(str, "getCameraVideoResolutionR=" + cameraVideoResolutionR);
            return cameraVideoResolutionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCarDoorConfig() {
        int carDoorConfigR;
        String str = g;
        LogUtil.verbose(str, " getCarDoorConfig.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                carDoorConfigR = carVendorUistyleManager.getCarDoorConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                carDoorConfigR = -1;
            }
            LogUtil.verbose(str, " getCarDoorConfig.out service =" + carDoorConfigR);
            return carDoorConfigR;
        }
        LogUtil.verbose(str, " getCarDoorConfig.null");
        carDoorConfigR = -1;
        LogUtil.verbose(str, " getCarDoorConfig.out service =" + carDoorConfigR);
        return carDoorConfigR;
    }

    public int getCarFridgeType() {
        int carFridgeTypeR;
        String str = g;
        LogUtil.verbose(str, " getCarFridgeType.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                carFridgeTypeR = carVendorUistyleManager.getCarFridgeTypeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                carFridgeTypeR = -1;
            }
            LogUtil.verbose(str, " getCarFridgeType.out=" + carFridgeTypeR);
            return carFridgeTypeR;
        }
        LogUtil.verbose(str, " getCarFridgeType == null");
        carFridgeTypeR = -1;
        LogUtil.verbose(str, " getCarFridgeType.out=" + carFridgeTypeR);
        return carFridgeTypeR;
    }

    public int getCarNum() {
        String str = g;
        LogUtil.verbose(str, "getCarNum");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int carNum = carVendorUistyleManager.getCarNum();
            LogUtil.verbose(str, "getCarNum=" + carNum);
            return carNum;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCarPlanSupport() {
        String str = g;
        LogUtil.verbose(str, "getCarPlanSupport is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859081, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCarPlanSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getCarTipsSoundSupport() {
        String str = g;
        LogUtil.verbose(str, "getCarTipsSoundSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int carTipsSoundSupport = carVendorUistyleManager.getCarTipsSoundSupport();
            LogUtil.verbose(str, "getCarTipsSoundSupport=" + carTipsSoundSupport);
            return carTipsSoundSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCarType() {
        String str = g;
        LogUtil.verbose(str, "getCarType");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856031, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCarType prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getChargeLimitSupport() {
        String str = g;
        LogUtil.verbose(str, "getChargeLimitSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int chargeLimitSupport = carVendorUistyleManager.getChargeLimitSupport();
            LogUtil.verbose(str, "getChargeLimitSupport=" + chargeLimitSupport);
            return chargeLimitSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getChargeLimitSupportNotLi() {
        int chargeLimitSupportNotLiR;
        String str = g;
        LogUtil.verbose(str, " getChargeLimitSupportNotLi.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                chargeLimitSupportNotLiR = carVendorUistyleManager.getChargeLimitSupportNotLiR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                chargeLimitSupportNotLiR = -1;
            }
            LogUtil.verbose(str, " getChargeLimitSupportNotLi.out service =" + chargeLimitSupportNotLiR);
            return chargeLimitSupportNotLiR;
        }
        LogUtil.verbose(str, " getChargeLimitSupportNotLi.null");
        chargeLimitSupportNotLiR = -1;
        LogUtil.verbose(str, " getChargeLimitSupportNotLi.out service =" + chargeLimitSupportNotLiR);
        return chargeLimitSupportNotLiR;
    }

    public int getChargePreheatSupport() {
        String str = g;
        LogUtil.verbose(str, "getChargePreheatSupport is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859084, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getChargePreheatSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getChargeToaggleGuardSupport() {
        String str = g;
        LogUtil.verbose(str, "getChargeToaggleGuardSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int chargeToaggleGuardSupport = carVendorUistyleManager.getChargeToaggleGuardSupport();
            LogUtil.verbose(str, "getChargeToaggleGuardSupport=" + chargeToaggleGuardSupport);
            return chargeToaggleGuardSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getComfortableParkingBy37F() {
        String str = g;
        LogUtil.verbose(str, "getComfortableParkingBy37F is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859127, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getComfortableParkingBy37F prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getComfortableParkingSupport() {
        String str = g;
        LogUtil.verbose(str, "getComfortableParkingSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int comfortableParkingSupport = carVendorUistyleManager.getComfortableParkingSupport();
            LogUtil.verbose(str, "getComfortableParkingSupport=" + comfortableParkingSupport);
            return comfortableParkingSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getComfortableSpeedDown() {
        LogUtil.verbose(g, "Service getComfortableSpeedDown");
        return 0;
    }

    public int getComfortableSpeedUp() {
        String str = g;
        LogUtil.verbose(str, "Service getComfortableSpeedUp");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int comfortableSpeedUp = carVendorUistyleManager.getComfortableSpeedUp();
            LogUtil.verbose(str, "getComfortableSpeedUp=" + comfortableSpeedUp);
            return comfortableSpeedUp;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCpdSupportR() {
        String str = g;
        LogUtil.info(str, "getCpdSupportR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859169, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCpdSupportR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getCpdSwMemStatus() {
        int cpdSwMemStatusR;
        String str = g;
        LogUtil.verbose(str, " getCpdSwMemStatus.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                cpdSwMemStatusR = carVendorUistyleManager.getCpdSwMemStatusR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                cpdSwMemStatusR = -1;
            }
            LogUtil.verbose(str, " getCpdSwMemStatus.out=" + cpdSwMemStatusR);
            return cpdSwMemStatusR;
        }
        LogUtil.verbose(str, " getCpdSwMemStatus == null");
        cpdSwMemStatusR = -1;
        LogUtil.verbose(str, " getCpdSwMemStatus.out=" + cpdSwMemStatusR);
        return cpdSwMemStatusR;
    }

    public int getCrossDomainPlatformConfigs() {
        int crossDomainPlatformConfigsR;
        String str = g;
        LogUtil.verbose(str, " getCrossDomainPlatformConfigs.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                crossDomainPlatformConfigsR = carVendorUistyleManager.getCrossDomainPlatformConfigsR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                crossDomainPlatformConfigsR = -1;
            }
            LogUtil.verbose(str, " getCrossDomainPlatformConfigs.out=" + crossDomainPlatformConfigsR);
            return crossDomainPlatformConfigsR;
        }
        LogUtil.verbose(str, " getCrossDomainPlatformConfigs.null");
        crossDomainPlatformConfigsR = -1;
        LogUtil.verbose(str, " getCrossDomainPlatformConfigs.out=" + crossDomainPlatformConfigsR);
        return crossDomainPlatformConfigsR;
    }

    public int getCumulativeAverageEnergyConsumptionInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getCumulativeAverageEnergyConsumptionInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875208, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCumulativeAverageEnergyConsumptionInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getCumulativeAverageFuelConsumptionInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getCumulativeAverageFuelConsumptionInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875210, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCumulativeAverageFuelConsumptionInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getCumulativeAveragePowerConsumptionInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getCumulativeAveragePowerConsumptionInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875209, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCumulativeAveragePowerConsumptionInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getCuraAssembleStatusFunction() {
        int curaAssembleStatusFunctionR;
        String str = g;
        LogUtil.verbose(str, " getCuraAssembleStatusFunction.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                curaAssembleStatusFunctionR = carVendorUistyleManager.getCuraAssembleStatusFunctionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                curaAssembleStatusFunctionR = -1;
            }
            LogUtil.verbose(str, " getCuraAssembleStatusFunction.out=" + curaAssembleStatusFunctionR);
            return curaAssembleStatusFunctionR;
        }
        LogUtil.verbose(str, " getCuraAssembleStatusFunction.null");
        curaAssembleStatusFunctionR = -1;
        LogUtil.verbose(str, " getCuraAssembleStatusFunction.out=" + curaAssembleStatusFunctionR);
        return curaAssembleStatusFunctionR;
    }

    public int getCurrentTripConfig() {
        int currentTripConfigR;
        String str = g;
        LogUtil.verbose(str, " getCurrentTripConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                currentTripConfigR = carVendorUistyleManager.getCurrentTripConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                currentTripConfigR = -1;
            }
            LogUtil.verbose(str, " getCurrentTripConfig.out=" + currentTripConfigR);
            return currentTripConfigR;
        }
        LogUtil.verbose(str, " getCurrentTripConfig.null");
        currentTripConfigR = -1;
        LogUtil.verbose(str, " getCurrentTripConfig.out=" + currentTripConfigR);
        return currentTripConfigR;
    }

    public int getCushionSupportBackL() {
        String str = g;
        LogUtil.verbose(str, "getCushionSupportBackL");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cushionSupportBackL = carVendorUistyleManager.getCushionSupportBackL();
            LogUtil.verbose(str, "getCushionSupportBackL=" + cushionSupportBackL);
            return cushionSupportBackL;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCushionSupportBackR() {
        String str = g;
        LogUtil.verbose(str, "getCushionSupportBackR");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cushionSupportBackR = carVendorUistyleManager.getCushionSupportBackR();
            LogUtil.verbose(str, "getCushionSupportBackR=" + cushionSupportBackR);
            return cushionSupportBackR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCushionSupportCopilot() {
        String str = g;
        LogUtil.verbose(str, "getCushionSupportCopilot");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cushionSupportCopilot = carVendorUistyleManager.getCushionSupportCopilot();
            LogUtil.verbose(str, "getCushionSupportCopilot=" + cushionSupportCopilot);
            return cushionSupportCopilot;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCushionSupportSecond() {
        String str = g;
        LogUtil.verbose(str, "getCushionSupportSecond");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int cushionSupportSecond = carVendorUistyleManager.getCushionSupportSecond();
            LogUtil.verbose(str, "getCushionSupportSecond=" + cushionSupportSecond);
            return cushionSupportSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCvcConfig() {
        String str = g;
        LogUtil.verbose(str, "getCvcConfig is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859120, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getCvcConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDMSAssembleStatus() {
        String str = g;
        LogUtil.verbose(str, "getDMSAssembleStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557870369, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDMSAssembleStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDMType() {
        String str = g;
        LogUtil.verbose(str, "getDMType");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856004, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDMType prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDayLightSupport() {
        String str = g;
        LogUtil.verbose(str, "getDayLightSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int dayLightSupport = carVendorUistyleManager.getDayLightSupport();
            LogUtil.verbose(str, "getDayLightSupport=" + dayLightSupport);
            return dayLightSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getDefrostRearConfig() {
        String str = g;
        LogUtil.verbose(str, "getDefrostRearConfig");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int defrostRearConfig = carVendorUistyleManager.getDefrostRearConfig();
            LogUtil.verbose(str, "getDefrostRearConfig=" + defrostRearConfig);
            return defrostRearConfig;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDiDynaConfig() {
        int diDynaConfigR;
        String str = g;
        LogUtil.verbose(str, " getDiDynaConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                diDynaConfigR = carVendorUistyleManager.getDiDynaConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                diDynaConfigR = -1;
            }
            LogUtil.verbose(str, " getDiDynaConfig.out=" + diDynaConfigR);
            return diDynaConfigR;
        }
        LogUtil.verbose(str, " getDiDynaConfig.null == mCarVendorUiStyleManager");
        diDynaConfigR = -1;
        LogUtil.verbose(str, " getDiDynaConfig.out=" + diDynaConfigR);
        return diDynaConfigR;
    }

    public int getDiSusShowStateByCan() {
        String str = g;
        LogUtil.verbose(str, "getDiSusShowStateByCan is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859121, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDiSusShowStateByCan prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDiSusShowStateByCanFd() {
        String str = g;
        LogUtil.verbose(str, "getDiSusShowStateByCanFd is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859122, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDiSusShowStateByCanFd prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDigtalKeySupport() {
        String str = g;
        LogUtil.verbose(str, "getDigtalKeySupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int digtalKeySupport = carVendorUistyleManager.getDigtalKeySupport();
            LogUtil.verbose(str, "getDigtalKeySupport=" + digtalKeySupport);
            return digtalKeySupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDischargeSupport() {
        String str = g;
        LogUtil.verbose(str, "getDischargeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int dischargeSupport = carVendorUistyleManager.getDischargeSupport();
            LogUtil.verbose(str, "getDischargeSupport=" + dischargeSupport);
            return dischargeSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getDisinfSupport() {
        String str = g;
        LogUtil.verbose(str, "getDisinfSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int disinfSupport = carVendorUistyleManager.getDisinfSupport();
            LogUtil.verbose(str, "getDisinfSupport=" + disinfSupport);
            return disinfSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDoorHeightSupport() {
        String str = g;
        LogUtil.verbose(str, "getDoorHeightSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int doorHeightSupport = carVendorUistyleManager.getDoorHeightSupport();
            LogUtil.verbose(str, "getDoorHeightSupport=" + doorHeightSupport);
            return doorHeightSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDoorLightSupport() {
        String str = g;
        LogUtil.verbose(str, "getDoorLightSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859116, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDoorLightSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        String str = g;
        LogUtil.verbose(str, "getDoorknobAutoShrinkSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int doorknobAutoShrinkSupport = carVendorUistyleManager.getDoorknobAutoShrinkSupport();
            LogUtil.verbose(str, "getDoorknobAutoShrinkSupport=" + doorknobAutoShrinkSupport);
            return doorknobAutoShrinkSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriveConfig() {
        String str = g;
        LogUtil.verbose(str, "getDriveConfig");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856003, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDriveConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDriveDistanceShowModeSupport() {
        String str = g;
        LogUtil.verbose(str, "getDriveDistanceShowModeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int driveDistanceShowModeSupport = carVendorUistyleManager.getDriveDistanceShowModeSupport();
            LogUtil.verbose(str, "getDriveDistanceShowModeSupport=" + driveDistanceShowModeSupport);
            return driveDistanceShowModeSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriveDistanceShowStandardSupport() {
        String str = g;
        LogUtil.verbose(str, "getDriveDistanceShowStandardSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int driveDistanceShowStandardSupport = carVendorUistyleManager.getDriveDistanceShowStandardSupport();
            LogUtil.verbose(str, "getDriveDistanceShowStandardSupport=" + driveDistanceShowStandardSupport);
            return driveDistanceShowStandardSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriveSeatAutoReturnSupport() {
        String str = g;
        LogUtil.verbose(str, "getDriveSeatAutoReturnSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int driveSeatAutoReturnSupport = carVendorUistyleManager.getDriveSeatAutoReturnSupport();
            LogUtil.verbose(str, "getDriveSeatAutoReturnSupport=" + driveSeatAutoReturnSupport);
            return driveSeatAutoReturnSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriveTimeInterfaceStatusForSingleTrip() {
        String str = g;
        LogUtil.verbose(str, "getDriveTimeInterfaceStatusForSingleTrip is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856219, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDriveTimeInterfaceStatusForSingleTrip prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDriverSeatHotStoneMassageConfigurationByEcu() {
        String str = g;
        LogUtil.verbose(str, "getDriverSeatHotStoneMassageConfigurationByEcu is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859151, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDriverSeatHotStoneMassageConfigurationByEcu prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDriverSeatHotStoneMassageConfigurationByLeftCtrl() {
        String str = g;
        LogUtil.verbose(str, "getDriverSeatHotStoneMassageConfigurationByLeftCtrl is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int driverSeatHotStoneMassageConfigurationByLeftCtrlR = carVendorUistyleManager.getDriverSeatHotStoneMassageConfigurationByLeftCtrlR();
            LogUtil.verbose(str, "getDriverSeatHotStoneMassageConfigurationByLeftCtrl=" + driverSeatHotStoneMassageConfigurationByLeftCtrlR);
            return driverSeatHotStoneMassageConfigurationByLeftCtrlR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriverSeatHotStoneMassageConfigurationByUniversalCtrl() {
        String str = g;
        LogUtil.verbose(str, "getDriverSeatHotStoneMassageConfigurationByUniversalCtrl is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int driverSeatHotStoneMassageConfigurationByUniversalCtrlR = carVendorUistyleManager.getDriverSeatHotStoneMassageConfigurationByUniversalCtrlR();
            LogUtil.verbose(str, "SeatHotStoneMassageConfigurationByUniversalCtrl=" + driverSeatHotStoneMassageConfigurationByUniversalCtrlR);
            return driverSeatHotStoneMassageConfigurationByUniversalCtrlR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDrivingRangeInterfaceStatusForFuel() {
        String str = g;
        LogUtil.verbose(str, "getDrivingRangeInterfaceStatusForFuel is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875214, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDrivingRangeInterfaceStatusForFuel prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDrivingVoiceBroadcastSwitchConfig() {
        String str = g;
        LogUtil.verbose(str, "getDrivingVoiceBroadcastSwitchConfig is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557870336, 0);
        CarPropertyValue carPropertyValueA2 = com.ts.appservice.caradapter.e.c.a(557856077, 0);
        CarPropertyValue carPropertyValueA3 = com.ts.appservice.caradapter.e.c.a(557856082, 0);
        int iIntValue = carPropertyValueA != null ? ((Integer) carPropertyValueA.getValue()).intValue() : 0;
        int iIntValue2 = carPropertyValueA2 != null ? ((Integer) carPropertyValueA2.getValue()).intValue() : 0;
        int iIntValue3 = carPropertyValueA3 != null ? ((Integer) carPropertyValueA3.getValue()).intValue() : 0;
        if (iIntValue != 1 && iIntValue2 != 1 && iIntValue3 != 1) {
            return 0;
        }
        LogUtil.verbose(str, "getDrivingVoiceBroadcastSwitchConfig prop value " + iIntValue + "," + iIntValue2 + "," + iIntValue3);
        return 1;
    }

    public int getDynamicSuspensionModeByCanFdR() {
        String str = g;
        LogUtil.info(str, "getDynamicSuspensionModeByCanFdR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859173, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDynamicSuspensionModeByCanFdR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDynamicSuspensionModeByCanR() {
        String str = g;
        LogUtil.info(str, "getDynamicSuspensionModeByCanR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859172, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getDynamicSuspensionModeByCanR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getDynamicSuspensionModeSupport() {
        String str = g;
        LogUtil.verbose(str, "getDynamicSuspensionModeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int dynamicSuspensionModeSupport = carVendorUistyleManager.getDynamicSuspensionModeSupport();
            LogUtil.verbose(str, "getDynamicSuspensionModeSupport=" + dynamicSuspensionModeSupport);
            return dynamicSuspensionModeSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEPedalStateSupport() {
        String str = g;
        LogUtil.verbose(str, "getEPedalStateSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856198, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getEPedalStateSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getElecWhereConfig() {
        int elecWhereConfigR;
        String str = g;
        LogUtil.verbose(str, " getElecWhereConfig.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                elecWhereConfigR = carVendorUistyleManager.getElecWhereConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                elecWhereConfigR = -1;
            }
            LogUtil.verbose(str, " getElecWhereConfig.out service =" + elecWhereConfigR);
            return elecWhereConfigR;
        }
        LogUtil.verbose(str, " getElecWhereConfig.null");
        elecWhereConfigR = -1;
        LogUtil.verbose(str, " getElecWhereConfig.out service =" + elecWhereConfigR);
        return elecWhereConfigR;
    }

    public int getElectricFrontHatchConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getElectricFrontHatchConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859083, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getElectricFrontHatchConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getElectroniChildLockConfig() {
        String str = g;
        LogUtil.verbose(str, "getElectroniChildLockConfig");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int electroniChildLockConfigSecond = carVendorUistyleManager.getElectroniChildLockConfigSecond();
            LogUtil.verbose(str, "getElectroniChildLockConfigSecond=" + electroniChildLockConfigSecond);
            return electroniChildLockConfigSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getElectronicParkingBrakeConfigutarion() {
        String str = g;
        LogUtil.verbose(str, "getElectronicParkingBrakeConfigutarion is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int electronicParkingBrakeConfigutarionR = carVendorUistyleManager.getElectronicParkingBrakeConfigutarionR();
            LogUtil.verbose(str, "getElectronicParkingBrakeConfigutarionR=" + electronicParkingBrakeConfigutarionR);
            return electronicParkingBrakeConfigutarionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getElkaDoubleConfirm() {
        int elkaDoubleConfirmR;
        String str = g;
        LogUtil.verbose(str, " getElkaDoubleConfirm.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                elkaDoubleConfirmR = carVendorUistyleManager.getElkaDoubleConfirmR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                elkaDoubleConfirmR = -1;
            }
            LogUtil.verbose(str, " getElkaDoubleConfirm.out service =" + elkaDoubleConfirmR);
            return elkaDoubleConfirmR;
        }
        LogUtil.verbose(str, " getElkaDoubleConfirm.null");
        elkaDoubleConfirmR = -1;
        LogUtil.verbose(str, " getElkaDoubleConfirm.out service =" + elkaDoubleConfirmR);
        return elkaDoubleConfirmR;
    }

    public int getEnergyContrastConfig() {
        int energyContrastConfigR;
        String str = g;
        LogUtil.verbose(str, " getEnergyContrastConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                energyContrastConfigR = carVendorUistyleManager.getEnergyContrastConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                energyContrastConfigR = -1;
            }
            LogUtil.verbose(str, " getEnergyContrastConfig.out=" + energyContrastConfigR);
            return energyContrastConfigR;
        }
        LogUtil.verbose(str, " getEnergyContrastConfig.null");
        energyContrastConfigR = -1;
        LogUtil.verbose(str, " getEnergyContrastConfig.out=" + energyContrastConfigR);
        return energyContrastConfigR;
    }

    public int getEnergyFeedbackSupport() {
        String str = g;
        LogUtil.verbose(str, " getEnergyFeedbackSupport.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859218, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getEnergyFeedbackSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getEnergyFlowDiagramInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875223, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getEnergyFlowDiagramInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getEnergyShowConfig() {
        int energyShowConfigR;
        String str = g;
        LogUtil.verbose(str, " getEnergyShowConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                energyShowConfigR = carVendorUistyleManager.getEnergyShowConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                energyShowConfigR = -1;
            }
            LogUtil.verbose(str, " getEnergyShowConfig.out=" + energyShowConfigR);
            return energyShowConfigR;
        }
        LogUtil.verbose(str, " getEnergyShowConfig.null");
        energyShowConfigR = -1;
        LogUtil.verbose(str, " getEnergyShowConfig.out=" + energyShowConfigR);
        return energyShowConfigR;
    }

    public int getEnergyShowSupport() {
        int energyShowSupportR;
        String str = g;
        LogUtil.verbose(str, " getEnergyShowSupport.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                energyShowSupportR = carVendorUistyleManager.getEnergyShowSupportR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                energyShowSupportR = -1;
            }
            LogUtil.verbose(str, " getEnergyShowSupport.out=" + energyShowSupportR);
            return energyShowSupportR;
        }
        LogUtil.verbose(str, " getEnergyShowSupport.null == mCarVendorUiStyleManager");
        energyShowSupportR = -1;
        LogUtil.verbose(str, " getEnergyShowSupport.out=" + energyShowSupportR);
        return energyShowSupportR;
    }

    @Deprecated
    public int getEnergyStatus() {
        String str = g;
        LogUtil.verbose(str, "getEnergyStatus");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int energyStatus = carVendorUistyleManager.getEnergyStatus();
            LogUtil.verbose(str, "getEnergyStatus=" + energyStatus);
            return energyStatus;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getEngineSoundSupport() {
        String str = g;
        LogUtil.verbose(str, "getEngineSoundSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856127, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getEngineSoundSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getExclusiveSaveFlag() {
        int exclusiveSaveFlagR;
        String str = g;
        LogUtil.verbose(str, " getExclusiveSaveFlag.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                exclusiveSaveFlagR = carVendorUistyleManager.getExclusiveSaveFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                exclusiveSaveFlagR = -1;
            }
            LogUtil.verbose(str, " getExclusiveSaveFlag.out=" + exclusiveSaveFlagR);
            return exclusiveSaveFlagR;
        }
        LogUtil.verbose(str, " getExclusiveSaveFlag == null");
        exclusiveSaveFlagR = -1;
        LogUtil.verbose(str, " getExclusiveSaveFlag.out=" + exclusiveSaveFlagR);
        return exclusiveSaveFlagR;
    }

    public int getExternalChargingPowerInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getExternalChargingPowerInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875220, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getExternalChargingPowerInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFcwDoubleConfirm() {
        int fcwDoubleConfirmR;
        String str = g;
        LogUtil.verbose(str, " getFcwDoubleConfirm.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fcwDoubleConfirmR = carVendorUistyleManager.getFcwDoubleConfirmR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fcwDoubleConfirmR = -1;
            }
            LogUtil.verbose(str, " getFcwDoubleConfirm.out service =" + fcwDoubleConfirmR);
            return fcwDoubleConfirmR;
        }
        LogUtil.verbose(str, " getFcwDoubleConfirm.null");
        fcwDoubleConfirmR = -1;
        LogUtil.verbose(str, " getFcwDoubleConfirm.out service =" + fcwDoubleConfirmR);
        return fcwDoubleConfirmR;
    }

    public int getFeaturesStateR() {
        String str = g;
        LogUtil.info(str, "getFeaturesStateR");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int featuresStateR = carVendorUistyleManager.getFeaturesStateR();
            LogUtil.verbose(str, "getFeaturesStateR=" + featuresStateR);
            return featuresStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFoldDownTheSecondRowOfSeatsConfig() {
        String str = g;
        LogUtil.verbose(str, "getFoldDownTheSecondRowOfSeatsConfig is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859132, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getFoldDownTheSecondRowOfSeatsConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFragranceInstallState(int i) {
        int supportFragranceTypeOne;
        String str = g;
        LogUtil.verbose(str, "getSupportFragranceType");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        int i2 = 0;
        if (carVendorUistyleManager != null) {
            try {
                if (i == 1) {
                    supportFragranceTypeOne = carVendorUistyleManager.getSupportFragranceTypeOne();
                    LogUtil.verbose(str, "getSupportFragranceTypeOne=" + supportFragranceTypeOne);
                } else if (i == 2) {
                    supportFragranceTypeOne = carVendorUistyleManager.getSupportFragranceTypeTwo();
                    LogUtil.verbose(str, "getSupportFragranceTypeTwo=" + supportFragranceTypeOne);
                } else {
                    if (i != 3) {
                        return 0;
                    }
                    supportFragranceTypeOne = carVendorUistyleManager.getSupportFragranceTypeThree();
                    LogUtil.verbose(str, "getSupportFragranceTypeThree=" + supportFragranceTypeOne);
                }
                i2 = supportFragranceTypeOne;
                return i2;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return i2;
    }

    public int getFridgeChildLockFlag() {
        int fridgeChildLockFlagR;
        String str = g;
        LogUtil.verbose(str, " getFridgeChildLockFlag.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeChildLockFlagR = carVendorUistyleManager.getFridgeChildLockFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeChildLockFlagR = -1;
            }
            LogUtil.verbose(str, " getFridgeChildLockFlag.out=" + fridgeChildLockFlagR);
            return fridgeChildLockFlagR;
        }
        LogUtil.verbose(str, " getFridgeChildLockFlag == null");
        fridgeChildLockFlagR = -1;
        LogUtil.verbose(str, " getFridgeChildLockFlag.out=" + fridgeChildLockFlagR);
        return fridgeChildLockFlagR;
    }

    public int getFridgeDelayedPowerOutage() {
        int fridgeDelayedPowerOutageR;
        String str = g;
        LogUtil.verbose(str, " getFridgeDelayedPowerOutage.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeDelayedPowerOutageR = carVendorUistyleManager.getFridgeDelayedPowerOutageR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeDelayedPowerOutageR = -1;
            }
            LogUtil.verbose(str, " getFridgeDelayedPowerOutage.out=" + fridgeDelayedPowerOutageR);
            return fridgeDelayedPowerOutageR;
        }
        LogUtil.verbose(str, " getFridgeDelayedPowerOutage == null");
        fridgeDelayedPowerOutageR = -1;
        LogUtil.verbose(str, " getFridgeDelayedPowerOutage.out=" + fridgeDelayedPowerOutageR);
        return fridgeDelayedPowerOutageR;
    }

    public int getFridgeExpansionAndContractionFunction() {
        int fridgeExpansionAndContractionFunctionR;
        String str = g;
        LogUtil.verbose(str, " getFridgeExpansionAndContractionFunction.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeExpansionAndContractionFunctionR = carVendorUistyleManager.getFridgeExpansionAndContractionFunctionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeExpansionAndContractionFunctionR = -1;
            }
            LogUtil.verbose(str, " getFridgeExpansionAndContractionFunction.out=" + fridgeExpansionAndContractionFunctionR);
            return fridgeExpansionAndContractionFunctionR;
        }
        LogUtil.verbose(str, " getFridgeExpansionAndContractionFunction == null");
        fridgeExpansionAndContractionFunctionR = -1;
        LogUtil.verbose(str, " getFridgeExpansionAndContractionFunction.out=" + fridgeExpansionAndContractionFunctionR);
        return fridgeExpansionAndContractionFunctionR;
    }

    public int getFridgeMinWagePowerGeneration() {
        int fridgeMinWagePowerGenerationR;
        String str = g;
        LogUtil.verbose(str, " getFridgeMinWagePowerGeneration.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeMinWagePowerGenerationR = carVendorUistyleManager.getFridgeMinWagePowerGenerationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeMinWagePowerGenerationR = -1;
            }
            LogUtil.verbose(str, " getFridgeMinWagePowerGeneration.out=" + fridgeMinWagePowerGenerationR);
            return fridgeMinWagePowerGenerationR;
        }
        LogUtil.verbose(str, " getFridgeMinWagePowerGeneration == null");
        fridgeMinWagePowerGenerationR = -1;
        LogUtil.verbose(str, " getFridgeMinWagePowerGeneration.out=" + fridgeMinWagePowerGenerationR);
        return fridgeMinWagePowerGenerationR;
    }

    public int getFridgeNum() {
        int fridgeNumR;
        String str = g;
        LogUtil.verbose(str, " getFridgeNum.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeNumR = carVendorUistyleManager.getFridgeNumR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeNumR = -1;
            }
            LogUtil.verbose(str, " getFridgeNum.out=" + fridgeNumR);
            return fridgeNumR;
        }
        LogUtil.verbose(str, " getFridgeNum == null");
        fridgeNumR = -1;
        LogUtil.verbose(str, " getFridgeNum.out=" + fridgeNumR);
        return fridgeNumR;
    }

    public int getFridgeOnePosition() {
        int fridgeOnePositionR;
        String str = g;
        LogUtil.verbose(str, " getFridgeOnePosition.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeOnePositionR = carVendorUistyleManager.getFridgeOnePositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeOnePositionR = -1;
            }
            LogUtil.verbose(str, " getFridgeOnePosition.out=" + fridgeOnePositionR);
            return fridgeOnePositionR;
        }
        LogUtil.verbose(str, " getFridgeOnePosition == null");
        fridgeOnePositionR = -1;
        LogUtil.verbose(str, " getFridgeOnePosition.out=" + fridgeOnePositionR);
        return fridgeOnePositionR;
    }

    public int getFridgeScreenFlag() {
        int fridgeScreenFlagR;
        String str = g;
        LogUtil.verbose(str, " getFridgeScreenFlag.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeScreenFlagR = carVendorUistyleManager.getFridgeScreenFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeScreenFlagR = -1;
            }
            LogUtil.verbose(str, " getFridgeScreenFlag.out=" + fridgeScreenFlagR);
            return fridgeScreenFlagR;
        }
        LogUtil.verbose(str, " getFridgeScreenFlag == null");
        fridgeScreenFlagR = -1;
        LogUtil.verbose(str, " getFridgeScreenFlag.out=" + fridgeScreenFlagR);
        return fridgeScreenFlagR;
    }

    public int getFridgeTemperatureRegulation() {
        int fridgeTemperatureRegulationR;
        String str = g;
        LogUtil.verbose(str, " getFridgeTemperatureRegulation.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeTemperatureRegulationR = carVendorUistyleManager.getFridgeTemperatureRegulationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeTemperatureRegulationR = -1;
            }
            LogUtil.verbose(str, " getFridgeTemperatureRegulation.out=" + fridgeTemperatureRegulationR);
            return fridgeTemperatureRegulationR;
        }
        LogUtil.verbose(str, " getFridgeTemperatureRegulation == null");
        fridgeTemperatureRegulationR = -1;
        LogUtil.verbose(str, " getFridgeTemperatureRegulation.out=" + fridgeTemperatureRegulationR);
        return fridgeTemperatureRegulationR;
    }

    public int getFridgeThreePosition() {
        int fridgeThreePositionR;
        String str = g;
        LogUtil.verbose(str, " getFridgeThreePosition.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeThreePositionR = carVendorUistyleManager.getFridgeThreePositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeThreePositionR = -1;
            }
            LogUtil.verbose(str, " getFridgeThreePosition.out=" + fridgeThreePositionR);
            return fridgeThreePositionR;
        }
        LogUtil.verbose(str, " getFridgeThreePosition == null");
        fridgeThreePositionR = -1;
        LogUtil.verbose(str, " getFridgeThreePosition.out=" + fridgeThreePositionR);
        return fridgeThreePositionR;
    }

    public int getFridgeTwoPosition() {
        int fridgeTwoPositionR;
        String str = g;
        LogUtil.verbose(str, " getFridgeTwoPosition.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeTwoPositionR = carVendorUistyleManager.getFridgeTwoPositionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeTwoPositionR = -1;
            }
            LogUtil.verbose(str, " getFridgeTwoPosition.out=" + fridgeTwoPositionR);
            return fridgeTwoPositionR;
        }
        LogUtil.verbose(str, " getFridgeTwoPosition == null");
        fridgeTwoPositionR = -1;
        LogUtil.verbose(str, " getFridgeTwoPosition.out=" + fridgeTwoPositionR);
        return fridgeTwoPositionR;
    }

    public int getFridgeWorkingMode() {
        int fridgeWorkingModeR;
        String str = g;
        LogUtil.verbose(str, " getFridgeWorkingMode.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                fridgeWorkingModeR = carVendorUistyleManager.getFridgeWorkingModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                fridgeWorkingModeR = -1;
            }
            LogUtil.verbose(str, " getFridgeWorkingMode.out=" + fridgeWorkingModeR);
            return fridgeWorkingModeR;
        }
        LogUtil.verbose(str, " getFridgeWorkingMode == null");
        fridgeWorkingModeR = -1;
        LogUtil.verbose(str, " getFridgeWorkingMode.out=" + fridgeWorkingModeR);
        return fridgeWorkingModeR;
    }

    public int getFrontFridgeDisinfectFlag() {
        int frontFridgeDisinfectFlagR;
        String str = g;
        LogUtil.verbose(str, " getFrontFridgeDisinfectFlag.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                frontFridgeDisinfectFlagR = carVendorUistyleManager.getFrontFridgeDisinfectFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                frontFridgeDisinfectFlagR = -1;
            }
            LogUtil.verbose(str, " getFrontFridgeDisinfectFlag.out=" + frontFridgeDisinfectFlagR);
            return frontFridgeDisinfectFlagR;
        }
        LogUtil.verbose(str, " getFrontFridgeDisinfectFlag == null");
        frontFridgeDisinfectFlagR = -1;
        LogUtil.verbose(str, " getFrontFridgeDisinfectFlag.out=" + frontFridgeDisinfectFlagR);
        return frontFridgeDisinfectFlagR;
    }

    public int getFrontLightDimmingCtrlConfigBy3ac() {
        String str = g;
        LogUtil.verbose(str, " getFrontLightDimmingCtrlConfigBy3ac.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859113, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getFrontLightDimmingCtrlConfigBy3ac prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFrontLightDimmingCtrlConfigBy3ad() {
        String str = g;
        LogUtil.verbose(str, " getFrontLightDimmingCtrlConfigBy3ad.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859114, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getFrontLightDimmingCtrlConfigBy3ad prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getFuelConsumptionInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getFuelConsumptionInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875216, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getFuelConsumptionInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getGuestLeftSeatRememeSupport() {
        String str = g;
        LogUtil.verbose(str, "getGuestLeftSeatRememeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int guestLeftSeatRememeSupportSecondL = carVendorUistyleManager.getGuestLeftSeatRememeSupportSecondL();
            LogUtil.verbose(str, "getGuestLeftSeatRememeSupportSecondL=" + guestLeftSeatRememeSupportSecondL);
            return guestLeftSeatRememeSupportSecondL;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getGuestRightSeatRememeSupport() {
        String str = g;
        LogUtil.verbose(str, "getGuestRightSeatRememeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int guestRightSeatRememeSupportFirstR = carVendorUistyleManager.getGuestRightSeatRememeSupportFirstR();
            LogUtil.verbose(str, "getGuestRightSeatRememeSupportFirstR=" + guestRightSeatRememeSupportFirstR);
            return guestRightSeatRememeSupportFirstR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHUDModeConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getHUDModeConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859092, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getHUDModeConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHasPredictCrashAlertMode() {
        String str = g;
        LogUtil.verbose(str, " getHasPredictCrashAlertMode.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856098, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getHasPredictCrashAlertMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHeadlampLevelConfig() {
        String str = g;
        LogUtil.verbose(str, "getHeadlampLevelConfig");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int headlampLevelConfig = carVendorUistyleManager.getHeadlampLevelConfig();
            LogUtil.verbose(str, "getHeadlampLevelConfig=" + headlampLevelConfig);
            return headlampLevelConfig;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeadlampSupport() {
        String str = g;
        LogUtil.verbose(str, "getHeadlampSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856034, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "HeadlampSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859149, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859139, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAc prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859138, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859148, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHeadrestTurnHeightSupport(int i) {
        String str = g;
        LogUtil.verbose(str, "getHeadrestTurnHeightSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        int headrestTurnHeightSupportSecond = -1;
        try {
            if (i == 1) {
                headrestTurnHeightSupportSecond = carVendorUistyleManager.getHeadrestTurnHeightSupportSecond();
                LogUtil.verbose(str, "getHeadrestTurnHeightSupportSecond=" + headrestTurnHeightSupportSecond);
            } else if (i == 2) {
                headrestTurnHeightSupportSecond = carVendorUistyleManager.getHeadrestTurnHeightSupportCopilot();
                LogUtil.verbose(str, "getHeadrestTurnHeightSupportCopilot=" + headrestTurnHeightSupportSecond);
            } else {
                if (i != 3) {
                    if (i == 4) {
                        headrestTurnHeightSupportSecond = carVendorUistyleManager.getHeadrestTurnHeightSupportBackR();
                        LogUtil.verbose(str, "getHeadrestTurnHeightSupportBackR=" + headrestTurnHeightSupportSecond);
                    }
                    return headrestTurnHeightSupportSecond;
                }
                headrestTurnHeightSupportSecond = carVendorUistyleManager.getHeadrestTurnHeightSupportBackL();
                LogUtil.verbose(str, "getHeadrestTurnHeightSupportBackL=" + headrestTurnHeightSupportSecond);
            }
            return headrestTurnHeightSupportSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeadrestTurnHorizatialSupport(int i) {
        String str = g;
        LogUtil.verbose(str, "getHeadrestTurnHorizatialSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        int headrestTurnHorizatialSupportFirst = -1;
        try {
            if (i == 1) {
                headrestTurnHorizatialSupportFirst = carVendorUistyleManager.getHeadrestTurnHorizatialSupportFirst();
                LogUtil.verbose(str, "getHeadrestTurnHorizatialSupportFirst=" + headrestTurnHorizatialSupportFirst);
            } else if (i == 2) {
                headrestTurnHorizatialSupportFirst = carVendorUistyleManager.getHeadrestTurnHorizatialSupportCopilot();
                LogUtil.verbose(str, "getHeadrestTurnHorizatialSupportCopilot=" + headrestTurnHorizatialSupportFirst);
            } else {
                if (i != 3) {
                    if (i == 4) {
                        headrestTurnHorizatialSupportFirst = carVendorUistyleManager.getHeadrestTurnHorizatialSupportBackR();
                        LogUtil.verbose(str, "getHeadrestTurnHorizatialSupportBackR=" + headrestTurnHorizatialSupportFirst);
                    }
                    return headrestTurnHorizatialSupportFirst;
                }
                headrestTurnHorizatialSupportFirst = carVendorUistyleManager.getHeadrestTurnHorizatialSupportBackL();
                LogUtil.verbose(str, "getHeadrestTurnHorizatialSupportBackL=" + headrestTurnHorizatialSupportFirst);
            }
            return headrestTurnHorizatialSupportFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859137, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859147, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getHevCarPlanSupport() {
        int i;
        String str = g;
        LogUtil.verbose(str, " getHevCarPlanSupport.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                i = carVendorUistyleManager.gethevCarPlanSupportR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getHevCarPlanSupport.out=" + i);
            return i;
        }
        LogUtil.verbose(str, " getHevCarPlanSupport == null");
        i = -1;
        LogUtil.verbose(str, " getHevCarPlanSupport.out=" + i);
        return i;
    }

    public int getHistoryEnergyConfig() {
        int historyEnergyConfigR;
        String str = g;
        LogUtil.verbose(str, " getHistoryEnergyConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                historyEnergyConfigR = carVendorUistyleManager.getHistoryEnergyConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                historyEnergyConfigR = -1;
            }
            LogUtil.verbose(str, " getHistoryEnergyConfig.out=" + historyEnergyConfigR);
            return historyEnergyConfigR;
        }
        LogUtil.verbose(str, " getHistoryEnergyConfig.null");
        historyEnergyConfigR = -1;
        LogUtil.verbose(str, " getHistoryEnergyConfig.out=" + historyEnergyConfigR);
        return historyEnergyConfigR;
    }

    public int getHistoryEnergyStatisticsConfig() {
        int historyEnergyStatisticsConfigR;
        String str = g;
        LogUtil.verbose(str, " getHistoryEnergyStatisticsConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                historyEnergyStatisticsConfigR = carVendorUistyleManager.getHistoryEnergyStatisticsConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                historyEnergyStatisticsConfigR = -1;
            }
            LogUtil.verbose(str, " getHistoryEnergyStatisticsConfig.out=" + historyEnergyStatisticsConfigR);
            return historyEnergyStatisticsConfigR;
        }
        LogUtil.verbose(str, " getHistoryEnergyStatisticsConfig.null");
        historyEnergyStatisticsConfigR = -1;
        LogUtil.verbose(str, " getHistoryEnergyStatisticsConfig.out=" + historyEnergyStatisticsConfigR);
        return historyEnergyStatisticsConfigR;
    }

    public int getHudHeightAdjustSupport() {
        String str = g;
        LogUtil.verbose(str, "getHudHeightAdjustSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int hudHeightAdjustSupport = carVendorUistyleManager.getHudHeightAdjustSupport();
            LogUtil.verbose(str, "getHudHeightAdjustSupport=" + hudHeightAdjustSupport);
            return hudHeightAdjustSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudLightSdjustSupport() {
        String str = g;
        LogUtil.verbose(str, "getHudLightSdjustSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int hudLightSdjustSupport = carVendorUistyleManager.getHudLightSdjustSupport();
            LogUtil.verbose(str, "getHudLightSdjustSupport=" + hudLightSdjustSupport);
            return hudLightSdjustSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        String str = g;
        LogUtil.verbose(str, "getHudParamsStateSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int hudParamsStateSupport = carVendorUistyleManager.getHudParamsStateSupport();
            LogUtil.verbose(str, "getHudParamsStateSupport=" + hudParamsStateSupport);
            return hudParamsStateSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getHudShowParamsSupport() {
        String str = g;
        LogUtil.verbose(str, "getHudShowParamsSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int hudShowParamsSupport = carVendorUistyleManager.getHudShowParamsSupport();
            LogUtil.verbose(str, "getHudShowParamsSupport=" + hudShowParamsSupport);
            return hudShowParamsSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIndoorLightSupport() {
        String str = g;
        LogUtil.verbose(str, "getIndoorLightSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856043, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getIndoorLightSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getInstantaneousFuelConsumptionInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getInstantaneousFuelConsumptionInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875211, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getInstantaneousFuelConsumptionInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getInstrumentContentSupport() {
        String str = g;
        LogUtil.verbose(str, "getInstrumentContentSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int instrumentContentSupport = carVendorUistyleManager.getInstrumentContentSupport();
            LogUtil.verbose(str, "getInstrumentContentSupport=" + instrumentContentSupport);
            return instrumentContentSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInstrumentHeightSupport() {
        String str = g;
        LogUtil.verbose(str, "getInstrumentHeightSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(555782656, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getInstrumentHeightSupport prop value " + carPropertyValueA.getValue());
        return ((Boolean) carPropertyValueA.getValue()).booleanValue() ? 1 : 0;
    }

    public int getInstrumentThemeSupport() {
        String str = g;
        LogUtil.verbose(str, "getInstrumentThemeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int instrumentThemeSupport = carVendorUistyleManager.getInstrumentThemeSupport();
            LogUtil.verbose(str, "getInstrumentThemeSupport=" + instrumentThemeSupport);
            return instrumentThemeSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentAcAreaSupport() {
        String str = g;
        LogUtil.verbose(str, "getIntelligentAcAreaSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int intelligentAcAreaSupport = carVendorUistyleManager.getIntelligentAcAreaSupport();
            LogUtil.verbose(str, "getIntelligentAcAreaSupport=" + intelligentAcAreaSupport);
            return intelligentAcAreaSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentKeepTemSupport() {
        String str = g;
        LogUtil.verbose(str, "getIntelligentKeepTemSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int intelligentKeepTemSupport = carVendorUistyleManager.getIntelligentKeepTemSupport();
            LogUtil.verbose(str, "getIntelligentKeepTemSupport=" + intelligentKeepTemSupport);
            return intelligentKeepTemSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        String str = g;
        LogUtil.verbose(str, "getIntelligentLockSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int intelligentLockSupport = carVendorUistyleManager.getIntelligentLockSupport();
            LogUtil.verbose(str, "getIntelligentLockSupport=" + intelligentLockSupport);
            return intelligentLockSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getIntelligentScreenCastingSwitchConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getIntelligentScreenCastingSwitchConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859093, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getIntelligentScreenCastingSwitchConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getIntelligentSpeedLimitMapVersionDisplay() {
        String str = g;
        LogUtil.verbose(str, "getIntelligentSpeedLimitMapVersionDisplay is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856845, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getIntelligentSpeedLimitMapVersionDisplay prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        String str = g;
        LogUtil.verbose(str, "getIntelligentUnlockSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int intelligentUnlockSupport = carVendorUistyleManager.getIntelligentUnlockSupport();
            LogUtil.verbose(str, "getIntelligentUnlockSupport=" + intelligentUnlockSupport);
            return intelligentUnlockSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLandAdvanceAtSupport() {
        String str = g;
        LogUtil.verbose(str, "getLandAdvanceAtSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int landAdvanceAtSupport = carVendorUistyleManager.getLandAdvanceAtSupport();
            LogUtil.verbose(str, "getLandAdvanceAtSupport=" + landAdvanceAtSupport);
            return landAdvanceAtSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLdaDoubleConfirm() {
        int ldaDoubleConfirmR;
        String str = g;
        LogUtil.verbose(str, " getLdaDoubleConfirm.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                ldaDoubleConfirmR = carVendorUistyleManager.getLdaDoubleConfirmR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                ldaDoubleConfirmR = -1;
            }
            LogUtil.verbose(str, " getLdaDoubleConfirm.out service =" + ldaDoubleConfirmR);
            return ldaDoubleConfirmR;
        }
        LogUtil.verbose(str, " getLdaDoubleConfirm.null");
        ldaDoubleConfirmR = -1;
        LogUtil.verbose(str, " getLdaDoubleConfirm.out service =" + ldaDoubleConfirmR);
        return ldaDoubleConfirmR;
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        String str = g;
        LogUtil.verbose(str, "getLeaveHomeDelaySupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int leaveHomeDelaySupport = carVendorUistyleManager.getLeaveHomeDelaySupport();
            LogUtil.verbose(str, "getLeaveHomeDelaySupport=" + leaveHomeDelaySupport);
            return leaveHomeDelaySupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftPlatformConfigs() {
        String str = g;
        LogUtil.verbose(str, "getLeftPlatformConfigs");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856023, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftPlatformConfigs prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftRESConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getLeftRESConfiguration is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int leftRESConfigurationR = carVendorUistyleManager.getLeftRESConfigurationR();
            LogUtil.verbose(str, "getLeftRESConfigurationR=" + leftRESConfigurationR);
            return leftRESConfigurationR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftRearMassageChairConfigurationForECU() {
        String str = g;
        LogUtil.verbose(str, "getLeftRearMassageChairConfigurationForECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856212, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftRearMassageChairConfigurationForECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftRearMemoryConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getLeftRearMemoryConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856203, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftRearMemoryConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftRearSeatBackAdjustmentConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getLeftRearSeatBackAdjustmentConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856246, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftRearSeatBackAdjustmentConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftRearSeatHorizontalAdjustmentConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getLeftRearSeatHorizontalAdjustmentConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856247, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftRearSeatHorizontalAdjustmentConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftRearSeatHotStoneMassageConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getLeftRearSeatHotStoneMassageConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859153, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftRearSeatHotStoneMassageConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftRearSeatLegBracket() {
        String str = g;
        LogUtil.verbose(str, "getLeftRearSeatLegBracket is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856248, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLeftRearSeatLegBracket prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLeftRearSeatReclineConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getLeftRearSeatReclineConfiguration is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int leftRearSeatReclineConfigurationR = carVendorUistyleManager.getLeftRearSeatReclineConfigurationR();
            LogUtil.verbose(str, "getLeftRearSeatReclineConfigurationR=" + leftRearSeatReclineConfigurationR);
            return leftRearSeatReclineConfigurationR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLeftRearSeatReclineConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getLeftRearSeatReclineConfigurationByECU is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int leftRearSeatReclineConfigurationR = carVendorUistyleManager.getLeftRearSeatReclineConfigurationR();
            LogUtil.verbose(str, "getLeftRearSeatReclineConfigurationR=" + leftRearSeatReclineConfigurationR);
            return leftRearSeatReclineConfigurationR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859135, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859145, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859133, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859143, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getLockCarRiseWindowSupport() {
        String str = g;
        LogUtil.verbose(str, "getLockCarRiseWindowSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int lockCarRiseWindowSupport = carVendorUistyleManager.getLockCarRiseWindowSupport();
            LogUtil.verbose(str, "getLockCarRiseWindowSupport=" + lockCarRiseWindowSupport);
            return lockCarRiseWindowSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getLockOffSupport() {
        String str = g;
        LogUtil.verbose(str, "getLockOffSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int lockOffSupport = carVendorUistyleManager.getLockOffSupport();
            LogUtil.verbose(str, "getLockOffSupport=" + lockOffSupport);
            return lockOffSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLowSpeedEmergencyBrakingConfig() {
        String str = g;
        LogUtil.verbose(str, "getLowSpeedEmergencyBrakingConfig is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859128, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getLowSpeedEmergencyBrakingConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        String str = g;
        LogUtil.verbose(str, "getMainDriveElectricHeadrestAdjustmentByGCtrl is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856230, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMainDriveElectricHeadrestAdjustmentByGCtrl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        String str = g;
        LogUtil.verbose(str, "getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856227, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        String str = g;
        LogUtil.verbose(str, "getMainDriveElectricLegSupportAdjustmentByGCtrl is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856229, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMainDriveElectricLegSupportAdjustmentByGCtrl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        String str = g;
        LogUtil.verbose(str, "getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int mainDriveElectricLegSupportAdjustmentByLeftBodyCtrlR = carVendorUistyleManager.getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrlR();
            LogUtil.verbose(str, "MainDriveElectricLegSupportAdjustmentByLeftBodyCtrl=" + mainDriveElectricLegSupportAdjustmentByLeftBodyCtrlR);
            return mainDriveElectricLegSupportAdjustmentByLeftBodyCtrlR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        String str = g;
        LogUtil.verbose(str, "getMainDriverSeatAdjustmentConfigurationByGCtrl is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856228, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMainDriverSeatAdjustmentConfigurationByGCtrl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        String str = g;
        LogUtil.verbose(str, "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856225, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getMainSeatRememberSupport() {
        String str = g;
        LogUtil.verbose(str, "getMainSeatRememberSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int mainSeatRememberSupportSecond = carVendorUistyleManager.getMainSeatRememberSupportSecond();
            LogUtil.verbose(str, "getMainSeatRememberSupportSecond=" + mainSeatRememberSupportSecond);
            return mainSeatRememberSupportSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMemoryParking() {
        String str = g;
        LogUtil.verbose(str, "getMemoryParking is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856217, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMemoryParking prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getMicroSwitchLockWindowSupport() {
        String str = g;
        LogUtil.verbose(str, "getMicroSwitchLockWindowSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int microSwitchLockWindowSupport = carVendorUistyleManager.getMicroSwitchLockWindowSupport();
            LogUtil.verbose(str, "getMicroSwitchLockWindowSupport=" + microSwitchLockWindowSupport);
            return microSwitchLockWindowSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        String str = g;
        LogUtil.verbose(str, "getMicroSwitchUnlockWindowSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int microSwitchUnlockWindowSupport = carVendorUistyleManager.getMicroSwitchUnlockWindowSupport();
            LogUtil.verbose(str, "getMicroSwitchUnlockWindowSupport=" + microSwitchUnlockWindowSupport);
            return microSwitchUnlockWindowSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMileageResetConfig() {
        int mileageResetConfigR;
        String str = g;
        LogUtil.verbose(str, " getMileageResetConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                mileageResetConfigR = carVendorUistyleManager.getMileageResetConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                mileageResetConfigR = -1;
            }
            LogUtil.verbose(str, " getMileageResetConfig.out=" + mileageResetConfigR);
            return mileageResetConfigR;
        }
        LogUtil.verbose(str, " getMileageResetConfig.null == mCarVendorUiStyleManager");
        mileageResetConfigR = -1;
        LogUtil.verbose(str, " getMileageResetConfig.out=" + mileageResetConfigR);
        return mileageResetConfigR;
    }

    public int getMoisSupportR() {
        String str = g;
        LogUtil.info(str, "getMoisSupportR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859168, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getMoisSupportR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getMoonRoofConfig() {
        LogUtil.verbose(g, "getMoonRoofConfig");
        return 0;
    }

    public int getMotorPretensionerSeatbeltSupport() {
        String str = g;
        LogUtil.verbose(str, "getMotorPretensionerSeatbeltSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int motorPretensionerSeatbeltSupport = carVendorUistyleManager.getMotorPretensionerSeatbeltSupport();
            LogUtil.verbose(str, "getMotorPretensionerSeatbeltSupport=" + motorPretensionerSeatbeltSupport);
            return motorPretensionerSeatbeltSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getNoSenseChargeSupport() {
        String str = g;
        LogUtil.verbose(str, "getNoSenseChargeSupport is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859082, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getNoSenseChargeSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getNonElecLeftPortConfig() {
        int nonElecLeftPortConfigR;
        String str = g;
        LogUtil.verbose(str, " getNonElecLeftPortConfig.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                nonElecLeftPortConfigR = carVendorUistyleManager.getNonElecLeftPortConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                nonElecLeftPortConfigR = -1;
            }
            LogUtil.verbose(str, " getNonElecLeftPortConfig.out service =" + nonElecLeftPortConfigR);
            return nonElecLeftPortConfigR;
        }
        LogUtil.verbose(str, " getNonElecLeftPortConfig.null");
        nonElecLeftPortConfigR = -1;
        LogUtil.verbose(str, " getNonElecLeftPortConfig.out service =" + nonElecLeftPortConfigR);
        return nonElecLeftPortConfigR;
    }

    public int getNonElecRightPortConfig() {
        int nonElecRightPortConfigR;
        String str = g;
        LogUtil.verbose(str, " getNonElecRightPortConfig.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                nonElecRightPortConfigR = carVendorUistyleManager.getNonElecRightPortConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                nonElecRightPortConfigR = -1;
            }
            LogUtil.verbose(str, " getNonElecRightPortConfig.out service =" + nonElecRightPortConfigR);
            return nonElecRightPortConfigR;
        }
        LogUtil.verbose(str, " getNonElecRightPortConfig.null");
        nonElecRightPortConfigR = -1;
        LogUtil.verbose(str, " getNonElecRightPortConfig.out service =" + nonElecRightPortConfigR);
        return nonElecRightPortConfigR;
    }

    public int getOBCType() {
        String str = g;
        LogUtil.verbose(str, "getOBCType");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int oBCType = carVendorUistyleManager.getOBCType();
            LogUtil.verbose(str, "getOBCType=" + oBCType);
            return oBCType;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat() {
        String str = g;
        LogUtil.verbose(str, "getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859140, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOneButtonDownConfigurationOfTheThirdRowRightRearSeat() {
        String str = g;
        LogUtil.verbose(str, "getOneButtonDownConfigurationOfTheThirdRowRightRearSeat is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859150, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getOneButtonDownConfigurationOfTheThirdRowRightRearSeat prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getOneButtonDownSwitchDisplayForTheThirdRowOfSeats() {
        String str = g;
        LogUtil.verbose(str, "getOneButtonDownSwitchDisplayForTheThirdRowOfSeats is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859141, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getOneButtonDownSwitchDisplayForTheThirdRowOfSeats prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getOverspeedLockSupport() {
        String str = g;
        LogUtil.verbose(str, "getOverspeedLockSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int overspeedLockSupport = carVendorUistyleManager.getOverspeedLockSupport();
            LogUtil.verbose(str, "getOverspeedLockSupport=" + overspeedLockSupport);
            return overspeedLockSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getPM25PowerSupport() {
        String str = g;
        LogUtil.verbose(str, "getPM25PowerSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int pM25PowerSupport = carVendorUistyleManager.getPM25PowerSupport();
            LogUtil.verbose(str, "getPM25PowerSupport=" + pM25PowerSupport);
            return pM25PowerSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getPM25SwitchCheckSupport() {
        String str = g;
        LogUtil.verbose(str, "getPM25SwitchCheckSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int pM25SwitchCheckSupport = carVendorUistyleManager.getPM25SwitchCheckSupport();
            LogUtil.verbose(str, "getPM25SwitchCheckSupport=" + pM25SwitchCheckSupport);
            return pM25SwitchCheckSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getPM25TimeCkeckSupport() {
        String str = g;
        LogUtil.verbose(str, "getPM25TimeCkeckSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int pM25TimeCkeckSupport = carVendorUistyleManager.getPM25TimeCkeckSupport();
            LogUtil.verbose(str, "getPM25TimeCkeckSupport=" + pM25TimeCkeckSupport);
            return pM25TimeCkeckSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPabSwitchState() {
        String str = g;
        LogUtil.verbose(str, " getPabSwitchState.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859112, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPabSwitchState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPanoramicSunroofConfigutarion() {
        String str = g;
        LogUtil.verbose(str, "getPanoramicSunroofConfigutarion is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int panoramicSunroofConfigutarionR = carVendorUistyleManager.getPanoramicSunroofConfigutarionR();
            LogUtil.verbose(str, "getPanoramicSunroofConfigutarionR=" + panoramicSunroofConfigutarionR);
            return panoramicSunroofConfigutarionR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getPassengerSeatElectricAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856232, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPassengerSeatElectricAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPassengerSeatHeadrestConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getPassengerSeatHeadrestConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856234, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPassengerSeatHeadrestConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPassengerSeatHeadrestConfigurationByGCtrl() {
        String str = g;
        LogUtil.verbose(str, "getPassengerSeatHeadrestConfigurationByGCtrl is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856236, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPassengerSeatHeadrestConfigurationByGCtrl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPassengerSeatHotStoneMassageConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getPassengerSeatHotStoneMassageConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859152, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPassengerSeatHotStoneMassageConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getPassengerSeatLegSupportAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856233, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPassengerSeatLegSupportAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl() {
        String str = g;
        LogUtil.verbose(str, "getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856235, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPassengerSeatMemoryFunctionCan() {
        String str = g;
        LogUtil.verbose(str, "getPassengerSeatMemoryFunctionCan is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int passengerSeatMemoryFunctionCanR = carVendorUistyleManager.getPassengerSeatMemoryFunctionCanR();
            LogUtil.verbose(str, "getPassengerSeatMemoryFunctionCanR=" + passengerSeatMemoryFunctionCanR);
            return passengerSeatMemoryFunctionCanR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPassengerSeatMemoryFunctionCanByECU() {
        String str = g;
        LogUtil.verbose(str, "getPassengerSeatMemoryFunctionCanByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856231, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPassengerSeatMemoryFunctionCanByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859136, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        String str = g;
        LogUtil.verbose(str, "getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859146, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getPreSaleModelSupport() {
        String str = g;
        LogUtil.verbose(str, "getPreSaleModelSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856000, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getPreSaleModelSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        String str = g;
        LogUtil.verbose(str, "getRainAutoLockWindowSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int rainAutoLockWindowSupport = carVendorUistyleManager.getRainAutoLockWindowSupport();
            LogUtil.verbose(str, "getRainAutoLockWindowSupport=" + rainAutoLockWindowSupport);
            return rainAutoLockWindowSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        String str = g;
        LogUtil.verbose(str, "getRearAcMaxWindLevel");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int rearAcMaxWindLevel = carVendorUistyleManager.getRearAcMaxWindLevel();
            LogUtil.verbose(str, "getRearAcMaxWindLevel=" + rearAcMaxWindLevel);
            return rearAcMaxWindLevel;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearFridgeDisinfectFlag() {
        int rearFridgeDisinfectFlagR;
        String str = g;
        LogUtil.verbose(str, " getRearFridgeDisinfectFlag.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                rearFridgeDisinfectFlagR = carVendorUistyleManager.getRearFridgeDisinfectFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearFridgeDisinfectFlagR = -1;
            }
            LogUtil.verbose(str, " getRearFridgeDisinfectFlag.out=" + rearFridgeDisinfectFlagR);
            return rearFridgeDisinfectFlagR;
        }
        LogUtil.verbose(str, " getRearFridgeDisinfectFlag == null");
        rearFridgeDisinfectFlagR = -1;
        LogUtil.verbose(str, " getRearFridgeDisinfectFlag.out=" + rearFridgeDisinfectFlagR);
        return rearFridgeDisinfectFlagR;
    }

    public int getRearFridgeKeyTypeFlag() {
        int rearFridgeKeyTypeFlagR;
        String str = g;
        LogUtil.verbose(str, " getRearFridgeKeyTypeFlag.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                rearFridgeKeyTypeFlagR = carVendorUistyleManager.getRearFridgeKeyTypeFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearFridgeKeyTypeFlagR = -1;
            }
            LogUtil.verbose(str, " getRearFridgeKeyTypeFlag.out=" + rearFridgeKeyTypeFlagR);
            return rearFridgeKeyTypeFlagR;
        }
        LogUtil.verbose(str, " getRearFridgeKeyTypeFlag == null");
        rearFridgeKeyTypeFlagR = -1;
        LogUtil.verbose(str, " getRearFridgeKeyTypeFlag.out=" + rearFridgeKeyTypeFlagR);
        return rearFridgeKeyTypeFlagR;
    }

    public int getRearFridgeWorkTypeFlag() {
        int rearFridgeWorkTypeFlagR;
        String str = g;
        LogUtil.verbose(str, " getRearFridgeWorkTypeFlag.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                rearFridgeWorkTypeFlagR = carVendorUistyleManager.getRearFridgeWorkTypeFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearFridgeWorkTypeFlagR = -1;
            }
            LogUtil.verbose(str, " getRearFridgeWorkTypeFlag.out=" + rearFridgeWorkTypeFlagR);
            return rearFridgeWorkTypeFlagR;
        }
        LogUtil.verbose(str, " getRearFridgeWorkTypeFlag == null");
        rearFridgeWorkTypeFlagR = -1;
        LogUtil.verbose(str, " getRearFridgeWorkTypeFlag.out=" + rearFridgeWorkTypeFlagR);
        return rearFridgeWorkTypeFlagR;
    }

    public int getRearLeftBackrestAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearLeftBackrestAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856238, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearLeftBackrestAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearLeftBidetAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearLeftBidetAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856240, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearLeftBidetAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearLeftHeadrestHeightAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856243, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearLeftHeadrestHeightAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearLeftHeadrestLevelAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856242, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearLeftHeadrestLevelAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearLeftHeightAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearLeftHeightAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856241, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearLeftHeightAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearLeftLegSupportAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856239, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearLeftLegSupportAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearLeftLevelAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearLeftLevelAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856237, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearLeftLevelAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearLeftMemorySwitchConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearLeftMemorySwitchConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856244, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearLeftMemorySwitchConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearPanelLockTimeSupport() {
        String str = g;
        LogUtil.verbose(str, "getRearPanelLockTimeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int rearPanelLockTimeSupport = carVendorUistyleManager.getRearPanelLockTimeSupport();
            LogUtil.verbose(str, "getRearPanelLockTimeSupport=" + rearPanelLockTimeSupport);
            return rearPanelLockTimeSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRearRightBackrestAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearRightBackrestAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856252, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearRightBackrestAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearRightBidetAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearRightBidetAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856254, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearRightBidetAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearRightFridgeHeatingScheme() {
        int rearRightFridgeHeatingSchemeR;
        String str = g;
        LogUtil.verbose(str, " getRearRightFridgeHeatingScheme.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                rearRightFridgeHeatingSchemeR = carVendorUistyleManager.getRearRightFridgeHeatingSchemeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                rearRightFridgeHeatingSchemeR = -1;
            }
            LogUtil.verbose(str, " getRearRightFridgeHeatingScheme.out=" + rearRightFridgeHeatingSchemeR);
            return rearRightFridgeHeatingSchemeR;
        }
        LogUtil.verbose(str, " getRearRightFridgeHeatingScheme == null");
        rearRightFridgeHeatingSchemeR = -1;
        LogUtil.verbose(str, " getRearRightFridgeHeatingScheme.out=" + rearRightFridgeHeatingSchemeR);
        return rearRightFridgeHeatingSchemeR;
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearRightHeadrestHeightAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859073, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearRightHeadrestHeightAdjustmentConfigurationByECU value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearRightHeadrestLevelAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859072, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearRightHeadrestLevelAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearRightHeightAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearRightHeightAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856255, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearRightHeightAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearRightLegSupportAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856253, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearRightLegSupportAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearRightLevelAdjustmentConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearRightLevelAdjustmentConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856251, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearRightLevelAdjustmentConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearRightMemorySwitchConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRearRightMemorySwitchConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856204, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearRightMemorySwitchConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRearSeatReclineConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getRearSeatReclineConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856250, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRearSeatReclineConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getRearViewMirrorFlipSupport() {
        LogUtil.verbose(g, "getRearViewMirrorFlipSupport");
        return 0;
    }

    public int getRearviewMirrorSupport() {
        String str = g;
        LogUtil.verbose(str, "getRearviewMirrorSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int rearviewMirrorSupport = carVendorUistyleManager.getRearviewMirrorSupport();
            LogUtil.verbose(str, "getRearviewMirrorSupport=" + rearviewMirrorSupport);
            return rearviewMirrorSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRecently50kmConfig() {
        int recently50kmConfigR;
        String str = g;
        LogUtil.verbose(str, " getRecently50kmConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                recently50kmConfigR = carVendorUistyleManager.getRecently50kmConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                recently50kmConfigR = -1;
            }
            LogUtil.verbose(str, " getRecently50kmConfig.out=" + recently50kmConfigR);
            return recently50kmConfigR;
        }
        LogUtil.verbose(str, " getRecently50kmConfig.null");
        recently50kmConfigR = -1;
        LogUtil.verbose(str, " getRecently50kmConfig.out=" + recently50kmConfigR);
        return recently50kmConfigR;
    }

    public int getRefrigeratorModeSupport() {
        String str = g;
        LogUtil.verbose(str, "getRefrigeratorModeSupport");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856170, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRefrigeratorModeSupport prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRemindSpeedSupport() {
        String str = g;
        LogUtil.verbose(str, "getRemindSpeedSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int remindSpeedSupportR = carVendorUistyleManager.getRemindSpeedSupportR();
            LogUtil.verbose(str, "getRemindSpeedSupportR=" + remindSpeedSupportR);
            return remindSpeedSupportR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getRemoteControlLockWindowSupport() {
        LogUtil.verbose(g, "getRemoteControlLockWindowSupport");
        return 0;
    }

    @Deprecated
    public int getRemoteControlUnlockWindowSupport() {
        String str = g;
        LogUtil.verbose(str, "getRemoteControlUnlockWindowSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int remoteControlUnlockWindowSupport = carVendorUistyleManager.getRemoteControlUnlockWindowSupport();
            LogUtil.verbose(str, "getRemoteControlUnlockWindowSupport=" + remoteControlUnlockWindowSupport);
            return remoteControlUnlockWindowSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int getRemoteKeyStartAcTimeSupport() {
        String str = g;
        LogUtil.verbose(str, "getRemoteKeyStartAcTimeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int remoteKeyStartAcTimeSupport = carVendorUistyleManager.getRemoteKeyStartAcTimeSupport();
            LogUtil.verbose(str, "getRemoteKeyStartAcTimeSupport=" + remoteKeyStartAcTimeSupport);
            return remoteKeyStartAcTimeSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getReportContentSetSupport() {
        String str = g;
        LogUtil.verbose(str, "getReportContentSetSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int reportContentSetSupport = carVendorUistyleManager.getReportContentSetSupport();
            LogUtil.verbose(str, "getReportContentSetSupport=" + reportContentSetSupport);
            return reportContentSetSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getReportItemsSetSupport(int i) {
        int reportItemsSetSupportBrake;
        String str = g;
        LogUtil.verbose(str, "getReportItemsSetSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        int i2 = 0;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 5:
                case 6:
                case 7:
                    return 1;
                case 1:
                    reportItemsSetSupportBrake = carVendorUistyleManager.getReportItemsSetSupportBrake();
                    LogUtil.verbose(str, "getReportItemsSetSupportBrake=" + reportItemsSetSupportBrake);
                    break;
                case 4:
                    reportItemsSetSupportBrake = carVendorUistyleManager.getReportItemsSetSupportCar();
                    LogUtil.verbose(str, "getReportItemsSetSupportCar=" + reportItemsSetSupportBrake);
                    break;
                case 8:
                    reportItemsSetSupportBrake = carVendorUistyleManager.getReportItemsSetSupportOil();
                    LogUtil.verbose(str, "getReportItemsSetSupportOil=" + reportItemsSetSupportBrake);
                    break;
                case 9:
                    reportItemsSetSupportBrake = carVendorUistyleManager.getReportItemsSetSupportMode();
                    LogUtil.verbose(str, "getReportItemsSetSupportMode=" + reportItemsSetSupportBrake);
                    break;
                case 10:
                    reportItemsSetSupportBrake = carVendorUistyleManager.getReportItemsSetSupportPark();
                    LogUtil.verbose(str, "getReportItemsSetSupportPark=" + reportItemsSetSupportBrake);
                    break;
                default:
                    return 0;
            }
            i2 = reportItemsSetSupportBrake;
            return i2;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return i2;
        }
    }

    public int getReportVolumeSupport() {
        String str = g;
        LogUtil.verbose(str, "getReportVolumeSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int reportVolumeSupport = carVendorUistyleManager.getReportVolumeSupport();
            LogUtil.verbose(str, "getReportVolumeSupport=" + reportVolumeSupport);
            return reportVolumeSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRestModeConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getRestModeConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859089, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRestModeConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightRESConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getRightRESConfiguration is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int rightRESConfigurationR = carVendorUistyleManager.getRightRESConfigurationR();
            LogUtil.verbose(str, "getRightRESConfigurationR=" + rightRESConfigurationR);
            return rightRESConfigurationR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRightRearMassageChairConfigurationForECU() {
        String str = g;
        LogUtil.verbose(str, "getRightRearMassageChairConfigurationForECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856213, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightRearMassageChairConfigurationForECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightRearMemoryConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getRightRearMemoryConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859079, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightRearMemoryConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightRearSeatBackAdjustmentConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getRightRearSeatBackAdjustmentConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859076, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightRearSeatBackAdjustmentConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightRearSeatHorizontalAdjustmentConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getRightRearSeatHorizontalAdjustmentConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859077, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightRearSeatHorizontalAdjustmentConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightRearSeatHotStoneMassageConfigurationByECU() {
        String str = g;
        LogUtil.verbose(str, "getRightRearSeatHotStoneMassageConfigurationByECU is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859154, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightRearSeatHotStoneMassageConfigurationByECU prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightRearSeatLegBracket() {
        String str = g;
        LogUtil.verbose(str, "getRightRearSeatLegBracket is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859078, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightRearSeatLegBracket prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRightRearSeatReclineConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getRightRearSeatReclineConfiguration is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int rightRearSeatReclineConfigurationR = carVendorUistyleManager.getRightRearSeatReclineConfigurationR();
            LogUtil.verbose(str, "getRightRearSeatReclineConfigurationR=" + rightRearSeatReclineConfigurationR);
            return rightRearSeatReclineConfigurationR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRightRearSeatReclineConfigurationByEUC() {
        String str = g;
        LogUtil.verbose(str, "getRightRearSeatReclineConfigurationByEUC is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856220, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getRightRearSeatReclineConfigurationByEUC prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getRuningCloseDoorSupport() {
        String str = g;
        LogUtil.verbose(str, "getRuningCloseDoorSupport is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int runningCloseDoorSupportR = carVendorUistyleManager.getRunningCloseDoorSupportR();
            LogUtil.verbose(str, "getRuningCloseDoorSupport=" + runningCloseDoorSupportR);
            return runningCloseDoorSupportR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRunningCodingConfig() {
        int runningCodingConfigR;
        String str = g;
        LogUtil.verbose(str, " getRunningCodingConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                runningCodingConfigR = carVendorUistyleManager.getRunningCodingConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                runningCodingConfigR = -1;
            }
            LogUtil.verbose(str, " getRunningCodingConfig.out=" + runningCodingConfigR);
            return runningCodingConfigR;
        }
        LogUtil.verbose(str, " getRunningCodingConfig.null == mCarVendorUiStyleManager");
        runningCodingConfigR = -1;
        LogUtil.verbose(str, " getRunningCodingConfig.out=" + runningCodingConfigR);
        return runningCodingConfigR;
    }

    public int getSandModeAbsConfig() {
        int sandModeAbsConfigR;
        String str = g;
        LogUtil.verbose(str, " getSandModeAbsConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                sandModeAbsConfigR = carVendorUistyleManager.getSandModeAbsConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                sandModeAbsConfigR = -1;
            }
            LogUtil.verbose(str, " getSandModeAbsConfig.out=" + sandModeAbsConfigR);
            return sandModeAbsConfigR;
        }
        LogUtil.verbose(str, " getSandModeAbsConfig.null == mCarVendorUiStyleManager");
        sandModeAbsConfigR = -1;
        LogUtil.verbose(str, " getSandModeAbsConfig.out=" + sandModeAbsConfigR);
        return sandModeAbsConfigR;
    }

    public int getSeatSetSupport(int i) {
        String str = g;
        LogUtil.verbose(str, "getSeatSetSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        int seatSetSupportFirst = -1;
        try {
            if (i == 1) {
                seatSetSupportFirst = carVendorUistyleManager.getSeatSetSupportFirst();
                LogUtil.verbose(str, "getSeatSetSupportFirst=" + seatSetSupportFirst);
            } else if (i == 2) {
                seatSetSupportFirst = carVendorUistyleManager.getSeatSetSupportCopilot();
                LogUtil.verbose(str, "getSeatSetSupportCopilot=" + seatSetSupportFirst);
            } else {
                if (i != 3) {
                    if (i == 4) {
                        seatSetSupportFirst = carVendorUistyleManager.getSeatSetSupportBackR();
                        LogUtil.verbose(str, "getSeatSetSupportBackR=" + seatSetSupportFirst);
                    }
                    return seatSetSupportFirst;
                }
                seatSetSupportFirst = carVendorUistyleManager.getSeatSetSupportBackL();
                LogUtil.verbose(str, "getSeatSetSupportBackL=" + seatSetSupportFirst);
            }
            return seatSetSupportFirst;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSeatTurnHeightSupport(int i) {
        String str = g;
        LogUtil.verbose(str, "getSeatTurnHeightSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        int seatTurnHeightSupportSecond = -1;
        try {
            if (i == 1) {
                seatTurnHeightSupportSecond = carVendorUistyleManager.getSeatTurnHeightSupportSecond();
                LogUtil.verbose(str, "getSeatTurnHeightSupportSecond=" + seatTurnHeightSupportSecond);
            } else if (i == 2) {
                seatTurnHeightSupportSecond = carVendorUistyleManager.getSeatTurnHeightSupportCopilot();
                LogUtil.verbose(str, "getSeatTurnHeightSupportCopilot=" + seatTurnHeightSupportSecond);
            } else {
                if (i != 3) {
                    if (i == 4) {
                        seatTurnHeightSupportSecond = carVendorUistyleManager.getSeatTurnHeightSupportBackR();
                        LogUtil.verbose(str, "getSeatTurnHeightSupportBackR=" + seatTurnHeightSupportSecond);
                    }
                    return seatTurnHeightSupportSecond;
                }
                seatTurnHeightSupportSecond = carVendorUistyleManager.getSeatTurnHeightSupportBackL();
                LogUtil.verbose(str, "getSeatTurnHeightSupportBackL=" + seatTurnHeightSupportSecond);
            }
            return seatTurnHeightSupportSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSeatTurnHorizatialSupport(int i) {
        String str = g;
        LogUtil.verbose(str, "getSeatTurnHorizatialSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        int seatTurnHorizatialSupportSecond = -1;
        try {
            if (i == 1) {
                seatTurnHorizatialSupportSecond = carVendorUistyleManager.getSeatTurnHorizatialSupportSecond();
                LogUtil.verbose(str, "getSeatTurnHorizatialSupportSecond=" + seatTurnHorizatialSupportSecond);
            } else if (i == 2) {
                seatTurnHorizatialSupportSecond = carVendorUistyleManager.getSeatTurnHorizatialSupportCopilot();
                LogUtil.verbose(str, "getSeatTurnHorizatialSupportCopilot=" + seatTurnHorizatialSupportSecond);
            } else {
                if (i != 3) {
                    if (i == 4) {
                        seatTurnHorizatialSupportSecond = carVendorUistyleManager.getSeatTurnHorizatialSupportBackR();
                        LogUtil.verbose(str, "getSeatTurnHorizatialSupportBackR=" + seatTurnHorizatialSupportSecond);
                    }
                    return seatTurnHorizatialSupportSecond;
                }
                seatTurnHorizatialSupportSecond = carVendorUistyleManager.getSeatTurnHorizatialSupportBackL();
                LogUtil.verbose(str, "getSeatTurnHorizatialSupportBackL=" + seatTurnHorizatialSupportSecond);
            }
            return seatTurnHorizatialSupportSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSeatbackrestSupport(int i) {
        String str = g;
        LogUtil.verbose(str, "getSeatbackrestSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        int seatbackrestSupportSecond = -1;
        try {
            if (i == 1) {
                seatbackrestSupportSecond = carVendorUistyleManager.getSeatbackrestSupportSecond();
                LogUtil.verbose(str, "getSeatbackrestSupportSecond=" + seatbackrestSupportSecond);
            } else if (i == 2) {
                seatbackrestSupportSecond = carVendorUistyleManager.getSeatbackrestSupportThird();
                LogUtil.verbose(str, "getSeatbackrestSupportThird=" + seatbackrestSupportSecond);
            } else {
                if (i != 3) {
                    if (i == 4) {
                        seatbackrestSupportSecond = carVendorUistyleManager.getSeatbackrestSupportBackR();
                        LogUtil.verbose(str, "getSeatbackrestSupportBackR=" + seatbackrestSupportSecond);
                    }
                    return seatbackrestSupportSecond;
                }
                seatbackrestSupportSecond = carVendorUistyleManager.getSeatbackrestSupportBackL();
                LogUtil.verbose(str, "getSeatbackrestSupportBackL=" + seatbackrestSupportSecond);
            }
            return seatbackrestSupportSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSecondSeatRememberSupport() {
        String str = g;
        LogUtil.verbose(str, "getSecondSeatRememberSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int secondSeatRememberSupport = carVendorUistyleManager.getSecondSeatRememberSupport();
            LogUtil.verbose(str, "getSecondSeatRememberSupport=" + secondSeatRememberSupport);
            return secondSeatRememberSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSideDistanceWarningConfig() {
        String str = g;
        LogUtil.verbose(str, "getSideDistanceWarningConfig is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859129, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSideDistanceWarningConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSocTargetConfig() {
        int i;
        String str = g;
        LogUtil.verbose(str, " getSocTargetConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                i = carVendorUistyleManager.getsocTargetConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                i = -1;
            }
            LogUtil.verbose(str, " getSocTargetConfig.out=" + i);
            return i;
        }
        LogUtil.verbose(str, " getSocTargetConfig.null == mCarVendorUiStyleManager");
        i = -1;
        LogUtil.verbose(str, " getSocTargetConfig.out=" + i);
        return i;
    }

    public int getStarTopSwitchSetting() {
        String str = g;
        LogUtil.verbose(str, "getStarTopSwitchSetting is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859126, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getStarTopSwitchSetting prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSteerAssisSupport() {
        String str = g;
        LogUtil.verbose(str, "getSteerAssisSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int steerAssisSupport = carVendorUistyleManager.getSteerAssisSupport();
            LogUtil.verbose(str, "getSteerAssisSupport=" + steerAssisSupport);
            return steerAssisSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSteerPositionAutoReturnSupport() {
        String str = g;
        LogUtil.verbose(str, "getSteerPositionAutoReturnSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int steerPositionAutoReturnSupport = carVendorUistyleManager.getSteerPositionAutoReturnSupport();
            LogUtil.verbose(str, "getSteerPositionAutoReturnSupport=" + steerPositionAutoReturnSupport);
            return steerPositionAutoReturnSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSteeringWheelHeatingConfiguration() {
        String str = g;
        LogUtil.verbose(str, "getSteeringWheelHeatingConfiguration is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856215, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSteeringWheelHeatingConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSteeringWheelSupport() {
        String str = g;
        LogUtil.verbose(str, "getSteeringWheelSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int steeringWheelSupport = carVendorUistyleManager.getSteeringWheelSupport();
            LogUtil.verbose(str, "getSteeringWheelSupport=" + steeringWheelSupport);
            return steeringWheelSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSupportAFS() {
        String str = g;
        LogUtil.verbose(str, "getSupportAFS");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859108, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "SupportAFSR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportAVH() {
        String str = g;
        LogUtil.verbose(str, " getSupportAVH.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859163, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportAVH prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportAcAutoKeyCanClose() {
        String str = g;
        LogUtil.verbose(str, "getSupportAcAutoKeyCanClose");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856016, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportAcAutoKeyCanClose prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportAcChargingLimit() {
        int supportAcChargingLimitR;
        String str = g;
        LogUtil.verbose(str, " getSupportAcChargingLimit.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportAcChargingLimitR = carVendorUistyleManager.getSupportAcChargingLimitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportAcChargingLimitR = -1;
            }
            LogUtil.verbose(str, " getSupportAcChargingLimit.out=" + supportAcChargingLimitR);
            return supportAcChargingLimitR;
        }
        LogUtil.verbose(str, " getSupportAcChargingLimit == null");
        supportAcChargingLimitR = -1;
        LogUtil.verbose(str, " getSupportAcChargingLimit.out=" + supportAcChargingLimitR);
        return supportAcChargingLimitR;
    }

    public int getSupportAcc() {
        int supportAccR;
        String str = g;
        LogUtil.verbose(str, " getSupportAcc.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportAccR = carVendorUistyleManager.getSupportAccR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportAccR = -1;
            }
            LogUtil.verbose(str, " getSupportAcc.out service =" + supportAccR);
            return supportAccR;
        }
        LogUtil.verbose(str, " getSupportAcc.null");
        supportAccR = -1;
        LogUtil.verbose(str, " getSupportAcc.out service =" + supportAccR);
        return supportAccR;
    }

    public int getSupportAiCare() {
        String str = g;
        LogUtil.verbose(str, "getSupportAiCare");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859117, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportAiCare prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportAiDistanceLight() {
        String str = g;
        LogUtil.verbose(str, "getSupportAiDistanceLight");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856106, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportAiDistanceLight prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportAiEmergencyBrake() {
        String str = g;
        LogUtil.verbose(str, "getSupportAiEmergencyBrake");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856099, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportAiEmergencyBrake prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportAiMatrixLight() {
        String str = g;
        LogUtil.verbose(str, "getSupportAiMatrixLight");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856107, 0);
        CarPropertyValue carPropertyValueA2 = com.ts.appservice.caradapter.e.c.a(557856108, 0);
        if (carPropertyValueA == null && carPropertyValueA2 == null) {
            return 0;
        }
        int iIntValue = carPropertyValueA != null ? ((Integer) carPropertyValueA.getValue()).intValue() : 0;
        int iIntValue2 = carPropertyValueA2 != null ? ((Integer) carPropertyValueA2.getValue()).intValue() : 0;
        LogUtil.verbose(str, "getSupportAiMatrixLight prop value " + iIntValue + ", " + iIntValue2);
        return (iIntValue == 1 || iIntValue2 == 1) ? 1 : 0;
    }

    public int getSupportAutoParking() {
        String str = g;
        LogUtil.verbose(str, "getSupportAutoParking");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856102, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportAutoParking prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportBabyModeConfig() {
        int supportBabyModeConfigR;
        String str = g;
        LogUtil.verbose(str, " getSupportBabyModeConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportBabyModeConfigR = carVendorUistyleManager.getSupportBabyModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportBabyModeConfigR = -1;
            }
            LogUtil.verbose(str, " getSupportBabyModeConfig.out=" + supportBabyModeConfigR);
            return supportBabyModeConfigR;
        }
        LogUtil.verbose(str, " getSupportBabyModeConfig.null");
        supportBabyModeConfigR = -1;
        LogUtil.verbose(str, " getSupportBabyModeConfig.out=" + supportBabyModeConfigR);
        return supportBabyModeConfigR;
    }

    public int getSupportBlindSpotAssist() {
        String str = g;
        LogUtil.verbose(str, "getSupportBlindSpotAssist");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856103, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportBlindSpotAssist prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportBlindSpotAssistBy418() {
        String str = g;
        LogUtil.verbose(str, "getSupportBlindSpotAssistBy418");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int supportBlindSpotAssistSecond = carVendorUistyleManager.getSupportBlindSpotAssistSecond();
            LogUtil.verbose(str, "getSupportBlindSpotAssistSecond=" + supportBlindSpotAssistSecond);
            return supportBlindSpotAssistSecond;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSupportCampModeConfig() {
        int supportCampModeConfigR;
        String str = g;
        LogUtil.verbose(str, " getSupportCampModeConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportCampModeConfigR = carVendorUistyleManager.getSupportCampModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportCampModeConfigR = -1;
            }
            LogUtil.verbose(str, " getSupportCampModeConfig.out=" + supportCampModeConfigR);
            return supportCampModeConfigR;
        }
        LogUtil.verbose(str, " getSupportCampModeConfig.null == mCarVendorUiStyleManager");
        supportCampModeConfigR = -1;
        LogUtil.verbose(str, " getSupportCampModeConfig.out=" + supportCampModeConfigR);
        return supportCampModeConfigR;
    }

    public int getSupportChargeHevThree() {
        int supportChargeHEVThreeR;
        String str = g;
        LogUtil.verbose(str, " getSupportChargeHevThree.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportChargeHEVThreeR = carVendorUistyleManager.getSupportChargeHEVThreeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportChargeHEVThreeR = -1;
            }
            LogUtil.verbose(str, " getSupportChargeHevThree.out=" + supportChargeHEVThreeR);
            return supportChargeHEVThreeR;
        }
        LogUtil.verbose(str, " getSupportChargeHevThree.null == mCarVendorUiStyleManager");
        supportChargeHEVThreeR = -1;
        LogUtil.verbose(str, " getSupportChargeHevThree.out=" + supportChargeHEVThreeR);
        return supportChargeHEVThreeR;
    }

    public int getSupportChargeHevThreeVcu() {
        int supportChargeHEVThreeVCUR;
        String str = g;
        LogUtil.verbose(str, " getSupportChargeHevThreeVcu.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportChargeHEVThreeVCUR = carVendorUistyleManager.getSupportChargeHEVThreeVCUR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportChargeHEVThreeVCUR = -1;
            }
            LogUtil.verbose(str, " getSupportChargeHevThreeVcu.out=" + supportChargeHEVThreeVCUR);
            return supportChargeHEVThreeVCUR;
        }
        LogUtil.verbose(str, " getSupportChargeHevThreeVcu.null == mCarVendorUiStyleManager");
        supportChargeHEVThreeVCUR = -1;
        LogUtil.verbose(str, " getSupportChargeHevThreeVcu.out=" + supportChargeHEVThreeVCUR);
        return supportChargeHEVThreeVCUR;
    }

    public int getSupportDigitalKey() {
        LogUtil.verbose(g, "getSupportDigitalKey");
        return 0;
    }

    public int getSupportDimmingCanopyConfiguration() {
        String str = g;
        LogUtil.info(str, " getSupportDimmingCanopyConfiguration.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859165, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportDimmingCanopyConfiguration prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportDoorOpenWarning() {
        String str = g;
        LogUtil.verbose(str, "getSupportDoorOpenWarning");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856091, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportDoorOpenWarning prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportDoubleConfirm() {
        String str = g;
        LogUtil.verbose(str, " getSupportDoubleConfirm.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859177, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportDoubleConfirm prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportESP() {
        String str = g;
        LogUtil.verbose(str, "getSupportESP");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859107, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportESP prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportEmergencyLaneKeepAssist() {
        String str = g;
        LogUtil.verbose(str, "getSupportEmergencyLaneKeepAssist");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856086, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportEmergencyLaneKeepAssist prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportEndToEndNavigation() {
        String str = g;
        LogUtil.verbose(str, "getSupportEndToEndNavigation");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557870367, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportEndToEndNavigation prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportExtreme() {
        String str = g;
        LogUtil.verbose(str, "getSupportExtreme is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856223, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportExtreme prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportFRElectricAirOutlet() {
        String str = g;
        LogUtil.verbose(str, "getSupportFRElectricAirOutlet is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859094, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportFRElectricAirOutlet prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportFcsr() {
        int supportFcsrR;
        String str = g;
        LogUtil.verbose(str, " getSupportFcsr.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportFcsrR = carVendorUistyleManager.getSupportFcsrR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportFcsrR = -1;
            }
            LogUtil.verbose(str, " getSupportFcsr.out service =" + supportFcsrR);
            return supportFcsrR;
        }
        LogUtil.verbose(str, " getSupportFcsr.null");
        supportFcsrR = -1;
        LogUtil.verbose(str, " getSupportFcsr.out service =" + supportFcsrR);
        return supportFcsrR;
    }

    public int getSupportFragrance() {
        String str = g;
        LogUtil.verbose(str, "getSupportFragrance");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856027, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportFragrance prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportFrontCrossingTrafficAlert() {
        String str = g;
        LogUtil.verbose(str, "getSupportFrontCrossingTrafficAlert");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856094, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportFrontCrossingTrafficAlert prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportFrontCrossingTrafficBrake() {
        String str = g;
        LogUtil.verbose(str, "getSupportFrontCrossingTrafficBrake");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856095, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportFrontCrossingTrafficBrake prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportHighTemperatureSterilize() {
        String str = g;
        LogUtil.verbose(str, "getSupportHighTemperatureSterilize");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856026, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportHighTemperatureSterilize prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportHighwayNavigation() {
        String str = g;
        LogUtil.verbose(str, "getSupportHighwayNavigation");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856079, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportHighwayNavigation prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportInsUiMode() {
        int supportInsUIModeR;
        String str = g;
        LogUtil.verbose(str, " getSupportInsUiMode.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportInsUIModeR = carVendorUistyleManager.getSupportInsUIModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportInsUIModeR = -1;
            }
            LogUtil.verbose(str, " getSupportInsUiMode.out=" + supportInsUIModeR);
            return supportInsUIModeR;
        }
        LogUtil.verbose(str, " getSupportInsUiMode == null");
        supportInsUIModeR = -1;
        LogUtil.verbose(str, " getSupportInsUiMode.out=" + supportInsUIModeR);
        return supportInsUIModeR;
    }

    public int getSupportInsUiShape() {
        int supportInsUIShapeR;
        String str = g;
        LogUtil.verbose(str, " getSupportInsUiShape.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportInsUIShapeR = carVendorUistyleManager.getSupportInsUIShapeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportInsUIShapeR = -1;
            }
            LogUtil.verbose(str, " getSupportInsUiShape.out=" + supportInsUIShapeR);
            return supportInsUIShapeR;
        }
        LogUtil.verbose(str, " getSupportInsUiShape == null");
        supportInsUIShapeR = -1;
        LogUtil.verbose(str, " getSupportInsUiShape.out=" + supportInsUIShapeR);
        return supportInsUIShapeR;
    }

    public int getSupportInsUiSize() {
        int supportInsUISizeR;
        String str = g;
        LogUtil.verbose(str, " getSupportInsUiSize.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportInsUISizeR = carVendorUistyleManager.getSupportInsUISizeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportInsUISizeR = -1;
            }
            LogUtil.verbose(str, " getSupportInsUiSize.out=" + supportInsUISizeR);
            return supportInsUISizeR;
        }
        LogUtil.verbose(str, " getSupportInsUiSize == null");
        supportInsUISizeR = -1;
        LogUtil.verbose(str, " getSupportInsUiSize.out=" + supportInsUISizeR);
        return supportInsUISizeR;
    }

    public int getSupportIntelligentNavigation() {
        String str = g;
        LogUtil.verbose(str, "getSupportIntelligentNavigation");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856077, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportIntelligentNavigation prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportIntelligentNavigationBy432() {
        String str = g;
        LogUtil.verbose(str, "getSupportIntelligentNavigation");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856078, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportIntelligentNavigationBy432 prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        String str = g;
        LogUtil.verbose(str, "getSupportIntellignetSpeedLimitAlert");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856088, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportIntellignetSpeedLimitAlert prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportIntellignetSpeedLimitControl() {
        String str = g;
        LogUtil.verbose(str, "getSupportIntellignetSpeedLimitControl");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856089, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportIntellignetSpeedLimitControl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportIop() {
        int supportIopR;
        String str = g;
        LogUtil.verbose(str, " getSupportIop.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportIopR = carVendorUistyleManager.getSupportIopR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportIopR = -1;
            }
            LogUtil.verbose(str, " getSupportIop.out=" + supportIopR);
            return supportIopR;
        }
        LogUtil.verbose(str, " getSupportIop.null");
        supportIopR = -1;
        LogUtil.verbose(str, " getSupportIop.out=" + supportIopR);
        return supportIopR;
    }

    public int getSupportLampSingnal() {
        String str = g;
        LogUtil.verbose(str, "getSupportLampSingnal");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856032, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "SupportLampSingnal prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportLaneChangeAssist() {
        String str = g;
        LogUtil.verbose(str, "getSupportLaneChangeAssist");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856082, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportLaneChangeAssist prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportLaneDepartureStop() {
        String str = g;
        LogUtil.verbose(str, "getSupportLaneDepartureStop");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856085, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportLaneDepartureStop prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportLaneDepartureWarning() {
        String str = g;
        LogUtil.verbose(str, "getSupportLaneDepartureWarning");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856083, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportLaneDepartureWarning prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportLaneKeep() {
        String str = g;
        LogUtil.verbose(str, "getSupportLaneKeep");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856084, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportLaneKeep prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportManualModelR() {
        String str = g;
        LogUtil.info(str, "getSupportManualModelR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859174, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportManualModelR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportMistakeGas() {
        String str = g;
        LogUtil.verbose(str, "getSupportMistakeGas");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856101, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportMistakeGas prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportNegativeIon() {
        String str = g;
        LogUtil.verbose(str, "getSupportNegativeIon");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856025, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportNegativeIon prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportOilBlade() {
        int supportOilBladeR;
        String str = g;
        LogUtil.verbose(str, " getSupportOilBlade.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportOilBladeR = carVendorUistyleManager.getSupportOilBladeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportOilBladeR = -1;
            }
            LogUtil.verbose(str, " getSupportOilBlade.out=" + supportOilBladeR);
            return supportOilBladeR;
        }
        LogUtil.verbose(str, " getSupportOilBlade == null");
        supportOilBladeR = -1;
        LogUtil.verbose(str, " getSupportOilBlade.out=" + supportOilBladeR);
        return supportOilBladeR;
    }

    public int getSupportOilBrake() {
        int supportOilBrakeR;
        String str = g;
        LogUtil.verbose(str, " getSupportOilBrake.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportOilBrakeR = carVendorUistyleManager.getSupportOilBrakeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportOilBrakeR = -1;
            }
            LogUtil.verbose(str, " getSupportOilBrake.out=" + supportOilBrakeR);
            return supportOilBrakeR;
        }
        LogUtil.verbose(str, " getSupportOilBrake == null");
        supportOilBrakeR = -1;
        LogUtil.verbose(str, " getSupportOilBrake.out=" + supportOilBrakeR);
        return supportOilBrakeR;
    }

    public int getSupportOilCool() {
        int supportOilCoolR;
        String str = g;
        LogUtil.verbose(str, " getSupportOilCool.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportOilCoolR = carVendorUistyleManager.getSupportOilCoolR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportOilCoolR = -1;
            }
            LogUtil.verbose(str, " getSupportOilCool.out=" + supportOilCoolR);
            return supportOilCoolR;
        }
        LogUtil.verbose(str, " getSupportOilCool == null");
        supportOilCoolR = -1;
        LogUtil.verbose(str, " getSupportOilCool.out=" + supportOilCoolR);
        return supportOilCoolR;
    }

    public int getSupportOilEngine() {
        int supportOilEngineR;
        String str = g;
        LogUtil.verbose(str, " getSupportOilEngine.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportOilEngineR = carVendorUistyleManager.getSupportOilEngineR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportOilEngineR = -1;
            }
            LogUtil.verbose(str, " getSupportOilEngine.out=" + supportOilEngineR);
            return supportOilEngineR;
        }
        LogUtil.verbose(str, " getSupportOilEngine == null");
        supportOilEngineR = -1;
        LogUtil.verbose(str, " getSupportOilEngine.out=" + supportOilEngineR);
        return supportOilEngineR;
    }

    public int getSupportOilTransmission() {
        int supportOilTransmissionR;
        String str = g;
        LogUtil.verbose(str, " getSupportOilTransmission.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportOilTransmissionR = carVendorUistyleManager.getSupportOilTransmissionR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportOilTransmissionR = -1;
            }
            LogUtil.verbose(str, " getSupportOilTransmission.out=" + supportOilTransmissionR);
            return supportOilTransmissionR;
        }
        LogUtil.verbose(str, " getSupportOilTransmission == null");
        supportOilTransmissionR = -1;
        LogUtil.verbose(str, " getSupportOilTransmission.out=" + supportOilTransmissionR);
        return supportOilTransmissionR;
    }

    public int getSupportPM25Mode() {
        String str = g;
        LogUtil.verbose(str, "getSupportPM25Mode");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856024, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "SupportPM25Mode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportPanoramaImage() {
        String str = g;
        LogUtil.verbose(str, "getSupportPanoramaImage");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856109, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportPanoramaImage prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportPredictCrashAlertMode() {
        String str = g;
        LogUtil.verbose(str, "getSupportPredictCrashAlertMode");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856097, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportPredictCrashAlertMode prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearAc() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearAc");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846530, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAc prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearAcAutoControl() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearAcAutoControl");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856010, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAcAutoControl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearAcCompressor() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearAcCompressor");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846536, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAcCompressor prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearAcKeyLock() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearAcKeyLock");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846533, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAcKeyLock prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearAcSwitch() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearAcSwitch");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846531, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAcSwitch prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearAcTempertureControl() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearAcTempertureControl");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846537, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAcTempertureControl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearAcWindLevelControl() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearAcWindLevelControl");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846538, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAcWindLevelControl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearAcWindModeControl() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearAcWindModeControl");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557846534, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearAcWindModeControl prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearCollisionWarning() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearCollisionWarning");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856092, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearCollisionWarning prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearControlPanelSign() {
        String str = g;
        LogUtil.info(str, " getSupportRearControlPanelSign.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859164, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearControlPanelSign prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearCrossBrake() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearCrossBrake");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856105, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearCrossBrake prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportRearTrafficCrossingAlert() {
        String str = g;
        LogUtil.verbose(str, "getSupportRearTrafficCrossingAlert");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856093, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportRearTrafficCrossingAlert prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        String str = g;
        LogUtil.verbose(str, "getSupportRemenberPowerOff");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int supportRemenberPowerOff = carVendorUistyleManager.getSupportRemenberPowerOff();
            LogUtil.verbose(str, "getSupportRemenberPowerOff=" + supportRemenberPowerOff);
            return supportRemenberPowerOff;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSupportRestModeConfig() {
        int supportRestModeConfigR;
        String str = g;
        LogUtil.verbose(str, " getSupportRestModeConfig.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportRestModeConfigR = carVendorUistyleManager.getSupportRestModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportRestModeConfigR = -1;
            }
            LogUtil.verbose(str, " getSupportRestModeConfig.out=" + supportRestModeConfigR);
            return supportRestModeConfigR;
        }
        LogUtil.verbose(str, " getSupportRestModeConfig.null == mCarVendorUiStyleManager");
        supportRestModeConfigR = -1;
        LogUtil.verbose(str, " getSupportRestModeConfig.out=" + supportRestModeConfigR);
        return supportRestModeConfigR;
    }

    public int getSupportReverseRadar() {
        String str = g;
        LogUtil.verbose(str, "getSupportReverseRadar");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int supportReverseRadar = carVendorUistyleManager.getSupportReverseRadar();
            LogUtil.verbose(str, "getSupportReverseRadar=" + supportReverseRadar);
            return supportReverseRadar;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSupportRnc() {
        int supportRNCR;
        String str = g;
        LogUtil.verbose(str, " getSupportRnc.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportRNCR = carVendorUistyleManager.getSupportRNCR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportRNCR = -1;
            }
            LogUtil.verbose(str, " getSupportRnc.out=" + supportRNCR);
            return supportRNCR;
        }
        LogUtil.verbose(str, " getSupportRnc == null");
        supportRNCR = -1;
        LogUtil.verbose(str, " getSupportRnc.out=" + supportRNCR);
        return supportRNCR;
    }

    public int getSupportRoadMode() {
        int supportRoadModeR;
        String str = g;
        LogUtil.verbose(str, " getSupportRoadMode.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportRoadModeR = carVendorUistyleManager.getSupportRoadModeR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportRoadModeR = -1;
            }
            LogUtil.verbose(str, " getSupportRoadMode.out=" + supportRoadModeR);
            return supportRoadModeR;
        }
        LogUtil.verbose(str, " getSupportRoadMode == null");
        supportRoadModeR = -1;
        LogUtil.verbose(str, " getSupportRoadMode.out=" + supportRoadModeR);
        return supportRoadModeR;
    }

    public int getSupportSaoc() {
        int supportSaocR;
        String str = g;
        LogUtil.verbose(str, " getSupportSaoc.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportSaocR = carVendorUistyleManager.getSupportSaocR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportSaocR = -1;
            }
            LogUtil.verbose(str, " getSupportSaoc.out=" + supportSaocR);
            return supportSaocR;
        }
        LogUtil.verbose(str, " getSupportSaoc.null");
        supportSaocR = -1;
        LogUtil.verbose(str, " getSupportSaoc.out=" + supportSaocR);
        return supportSaocR;
    }

    public int[] getSupportSeatAdjust(int i) {
        LogUtil.verbose(g, "getSupportSeatAdjust");
        return new int[1];
    }

    public int getSupportSeatMassage(int i) {
        CarPropertyValue carPropertyValueA;
        String str = g;
        LogUtil.verbose(str, "getSupportSeatMassage");
        if (i == 1) {
            CarPropertyValue carPropertyValueA2 = com.ts.appservice.caradapter.e.c.a(557856019, 0);
            if (carPropertyValueA2 == null) {
                return 0;
            }
            LogUtil.verbose(str, "SupportSeatMassageMain prop value " + carPropertyValueA2.getValue());
            return ((Integer) carPropertyValueA2.getValue()).intValue();
        }
        if (i == 2) {
            CarPropertyValue carPropertyValueA3 = com.ts.appservice.caradapter.e.c.a(557856020, 0);
            if (carPropertyValueA3 == null) {
                return 0;
            }
            LogUtil.verbose(str, "SupportSeatMassageDeputy prop value " + carPropertyValueA3.getValue());
            return ((Integer) carPropertyValueA3.getValue()).intValue();
        }
        if (i != 3) {
            if (i != 4 || (carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856022, 0)) == null) {
                return 0;
            }
            LogUtil.verbose(str, "SupportSeatMassageRightRear prop value " + carPropertyValueA.getValue());
            return ((Integer) carPropertyValueA.getValue()).intValue();
        }
        CarPropertyValue carPropertyValueA4 = com.ts.appservice.caradapter.e.c.a(557856021, 0);
        if (carPropertyValueA4 == null) {
            return 0;
        }
        LogUtil.verbose(str, "SupportSeatMassageLeftRear prop value " + carPropertyValueA4.getValue());
        return ((Integer) carPropertyValueA4.getValue()).intValue();
    }

    public int getSupportShakeAlert() {
        String str = g;
        LogUtil.verbose(str, "getSupportShakeAlert");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856096, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportShakeAlert prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportShakeAlertR() {
        String str = g;
        LogUtil.info(str, "getSupportShakeAlertR");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856083, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportShakeAlertR prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportShowCarModel() {
        String str = g;
        LogUtil.verbose(str, "getSupportShowCarModel");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int supportShowCarModel = carVendorUistyleManager.getSupportShowCarModel();
            LogUtil.verbose(str, "getSupportShowCarModel=" + supportShowCarModel);
            return supportShowCarModel;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSupportSingleDoubleTempArea() {
        String str = g;
        LogUtil.verbose(str, "getSupportSingleDoubleTempArea");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int supportSingleDoubleTempArea = carVendorUistyleManager.getSupportSingleDoubleTempArea();
            LogUtil.verbose(str, "getSupportSingleDoubleTempArea=" + supportSingleDoubleTempArea);
            return supportSingleDoubleTempArea;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSupportSlif() {
        int supportSlifR;
        String str = g;
        LogUtil.verbose(str, " getSupportSlif.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                supportSlifR = carVendorUistyleManager.getSupportSlifR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                supportSlifR = -1;
            }
            LogUtil.verbose(str, " getSupportSlif.out=" + supportSlifR);
            return supportSlifR;
        }
        LogUtil.verbose(str, " getSupportSlif.null");
        supportSlifR = -1;
        LogUtil.verbose(str, " getSupportSlif.out=" + supportSlifR);
        return supportSlifR;
    }

    public int getSupportSlw() {
        String str = g;
        LogUtil.verbose(str, " getSupportSlw.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856112, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportSlw prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportSmartChargingPHEV() {
        String str = g;
        LogUtil.verbose(str, "getSupportSmartChargingPHEV is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856224, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportSmartChargingPHEV prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportSmartChargingPhevPhase2R() {
        String str = g;
        LogUtil.info(str, "getSupportSmartChargingPhevPhase2R");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859170, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportSmartChargingPhevPhase2R prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportSmrm() {
        String str = g;
        LogUtil.verbose(str, " getSupportSmrm.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859194, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportSmrm prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getSupportSteeringWheelWarm() {
        String str = g;
        LogUtil.verbose(str, "getSupportSteeringWheelWarm");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int supportSteeringWheelWarm = carVendorUistyleManager.getSupportSteeringWheelWarm();
            LogUtil.verbose(str, "getSupportSteeringWheelWarm=" + supportSteeringWheelWarm);
            return supportSteeringWheelWarm;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSupportTrafficLightAttention() {
        String str = g;
        LogUtil.verbose(str, "getSupportTrafficLightAttention");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856090, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportTrafficLightAttention prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportTrafficSignRecogniton() {
        String str = g;
        LogUtil.verbose(str, "getSupportTrafficSignRecogniton");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856087, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportTrafficSignRecogniton prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportTrafficSignsRecognition() {
        String str = g;
        LogUtil.verbose(str, "getSupportTrafficSignsRecognition");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856087, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportTrafficSignsRecognition prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportTsrFlow() {
        String str = g;
        LogUtil.verbose(str, " getSupportTsrFlow.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859176, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportTsrFlow prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportUrbanNavigation() {
        String str = g;
        LogUtil.verbose(str, "getSupportUrbanNavigation");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859118, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportUrbanNavigation prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportiTac() {
        String str = g;
        LogUtil.verbose(str, " getSupportiTac.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859186, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportiTac prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getSupportiTacConfig() {
        String str = g;
        LogUtil.verbose(str, " getSupportiTacConfig.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859187, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getSupportiTacConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTailgateTypeMarking() {
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859175, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(g, "getTailgateTypeMarking prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTheLast50KMAverageEnergyConsumptionInterfaceState() {
        String str = g;
        LogUtil.verbose(str, "getTheLast50KMAverageEnergyConsumptionInterfaceState is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875206, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTheLast50KMAverageEnergyConsumptionInterfaceState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTheLast50KMAverageFuelConsumptionInterfaceState() {
        String str = g;
        LogUtil.verbose(str, "getTheLast50KMAverageFuelConsumptionInterfaceState is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875203, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTheLast50KMAverageFuelConsumptionInterfaceState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTheLast50KMAveragePowerConsumptionInterfaceState() {
        String str = g;
        LogUtil.verbose(str, "getTheLast50KMAveragePowerConsumptionInterfaceState is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875200, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTheLast50KMAveragePowerConsumptionInterfaceState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTirePressureInterfaceStatus() {
        String str = g;
        LogUtil.verbose(str, "getTirePressureInterfaceStatus is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557875228, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTirePressureInterfaceStatus prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTowingCruiseConfiguration() {
        int towingCruiseConfigurationR;
        String str = g;
        LogUtil.verbose(str, " getTowingCruiseConfiguration.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                towingCruiseConfigurationR = carVendorUistyleManager.getTowingCruiseConfigurationR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                towingCruiseConfigurationR = -1;
            }
            LogUtil.verbose(str, " getTowingCruiseConfiguration.out service =" + towingCruiseConfigurationR);
            return towingCruiseConfigurationR;
        }
        LogUtil.verbose(str, " getTowingCruiseConfiguration.null");
        towingCruiseConfigurationR = -1;
        LogUtil.verbose(str, " getTowingCruiseConfiguration.out service =" + towingCruiseConfigurationR);
        return towingCruiseConfigurationR;
    }

    public int getTrailerDrModeConfig() {
        int trailerDrModeConfigR;
        String str = g;
        LogUtil.verbose(str, " getTrailerDrModeConfig.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                trailerDrModeConfigR = carVendorUistyleManager.getTrailerDrModeConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trailerDrModeConfigR = -1;
            }
            LogUtil.verbose(str, " getTrailerDrModeConfig.out service =" + trailerDrModeConfigR);
            return trailerDrModeConfigR;
        }
        LogUtil.verbose(str, " getTrailerDrModeConfig.null");
        trailerDrModeConfigR = -1;
        LogUtil.verbose(str, " getTrailerDrModeConfig.out service =" + trailerDrModeConfigR);
        return trailerDrModeConfigR;
    }

    public int getTrailerModeConfig() {
        String str = g;
        LogUtil.verbose(str, " getTrailerModeConfig is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859131, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTrailerModeConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    @Deprecated
    public int getTrayControlSupport() {
        String str = g;
        LogUtil.verbose(str, "getTrayControlSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int trayControlSupport = carVendorUistyleManager.getTrayControlSupport();
            LogUtil.verbose(str, "getTrayControlSupport=" + trayControlSupport);
            return trayControlSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTrlrCrsConfig() {
        int trlrCrsConfigR;
        String str = g;
        LogUtil.verbose(str, " getTrlrCrsConfig.in service");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                trlrCrsConfigR = carVendorUistyleManager.getTrlrCrsConfigR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                trlrCrsConfigR = -1;
            }
            LogUtil.verbose(str, " getTrlrCrsConfig.out service =" + trlrCrsConfigR);
            return trlrCrsConfigR;
        }
        LogUtil.verbose(str, " getTrlrCrsConfig.null");
        trlrCrsConfigR = -1;
        LogUtil.verbose(str, " getTrlrCrsConfig.out service =" + trlrCrsConfigR);
        return trlrCrsConfigR;
    }

    public int getTrunkConfigutarion() {
        String str = g;
        LogUtil.verbose(str, "getTrunkConfigutarion is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856076, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTrunkConfigutarion prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getTsrLitSpdChgSwitchState() {
        String str = g;
        LogUtil.verbose(str, " getTsrLitSpdChgSwitchState.in");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859115, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getTsrLitSpdChgSwitchState prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getUsherLightSupport() {
        String str = g;
        LogUtil.verbose(str, "getUsherLightSupport");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int usherLightSupport = carVendorUistyleManager.getUsherLightSupport();
            LogUtil.verbose(str, "getUsherLightSupport=" + usherLightSupport);
            return usherLightSupport;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getV2xConfig() {
        String str = g;
        LogUtil.verbose(str, "getV2xConfig is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859130, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getV2xConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getValetParking() {
        String str = g;
        LogUtil.verbose(str, "getValetParking is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856216, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getValetParking prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public byte[] getVcuEncoded() {
        String str = g;
        byte[] vcuEncodedR = new byte[0];
        LogUtil.verbose(str, " getVcuEncoded.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                vcuEncodedR = carVendorUistyleManager.getVcuEncodedR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " getVcuEncoded.null");
        }
        LogUtil.verbose(str, " getVcuEncoded.out=" + vcuEncodedR);
        return vcuEncodedR;
    }

    public int getWadingModeSetFlag() {
        int wadingModeSetFlagR;
        String str = g;
        LogUtil.verbose(str, " getWadingModeSetFlag.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                wadingModeSetFlagR = carVendorUistyleManager.getWadingModeSetFlagR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                wadingModeSetFlagR = -1;
            }
            LogUtil.verbose(str, " getWadingModeSetFlag.out=" + wadingModeSetFlagR);
            return wadingModeSetFlagR;
        }
        LogUtil.verbose(str, " getWadingModeSetFlag.null == mCarVendorUiStyleManager");
        wadingModeSetFlagR = -1;
        LogUtil.verbose(str, " getWadingModeSetFlag.out=" + wadingModeSetFlagR);
        return wadingModeSetFlagR;
    }

    public int getWarningAuxiliarySwitchSetting() {
        String str = g;
        LogUtil.verbose(str, "getWarningAuxiliarySwitchSetting is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859123, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWarningAuxiliarySwitchSetting prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getWelcomeCeremonySwitchSetting() {
        String str = g;
        LogUtil.verbose(str, "getWelcomeCeremonySwitchSetting is execute");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557859124, 0);
        if (carPropertyValueA == null) {
            return 0;
        }
        LogUtil.verbose(str, "getWelcomeCeremonySwitchSetting prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public int getWindowAntiPinchConfig() {
        String str = g;
        LogUtil.verbose(str, "getWindowAntiPinchConfig");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557856209, 0);
        if (carPropertyValueA == null) {
            return -1;
        }
        LogUtil.verbose(str, "getWindowAntiPinchConfig prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).intValue();
    }

    public void i() {
        o();
        com.ts.appservice.caradapter.a.i iVar = this.e;
        if (iVar != null) {
            this.d = false;
            iVar.g();
        }
    }

    public void l(CarVendorUistyleManager carVendorUistyleManager) {
        this.f = carVendorUistyleManager;
        try {
            if (this.f81b == null) {
                this.f81b = new a(this.f80a.getLooper());
                LogUtil.info(g, "initSelfStudyManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.i iVar = this.e;
        if (iVar != null) {
            this.d = true;
            iVar.l();
        }
    }

    public void p(CarVendorUistyleManager carVendorUistyleManager) {
        this.f = carVendorUistyleManager;
    }

    public int resetAmbientSettings(int i) {
        LogUtil.verbose(g, "resetAmbientSettings" + i);
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            carVendorUistyleManager.setResetAmbientSettings(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i) {
        LogUtil.verbose(g, "resetAutoPurificationFactoryMode");
        return 0;
    }

    public int resetBackAndLeaveDelaySupport(int i) {
        LogUtil.verbose(g, "resetBackAndLeaveDelaySupport" + i);
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            carVendorUistyleManager.setResetBackAndLeaveDelaySupport(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int resetDayLight(int i) {
        LogUtil.verbose(g, "resetDayLight" + i);
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            carVendorUistyleManager.setResetDayLight(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int resetDoorAndIndoor(int i) {
        LogUtil.verbose(g, "resetDoorAndIndoor");
        return 0;
    }

    public int resetHeadlamp(int i) {
        String str = g;
        LogUtil.verbose(str, "resetHeadlamp");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            int i2 = carVendorUistyleManager.getresetHeadlamp();
            LogUtil.verbose(str, "getresetHeadlamp=" + i2);
            return i2;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int restoreFactorySettings() {
        LogUtil.verbose(g, "restoreFactorySettings is execute");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            carVendorUistyleManager.setrestoreFactorySettings(1);
            this.f.setSysFactoryReset(1);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sendDLinkCountryCode(int i) {
        LogUtil.verbose(g, "sendDLinkCountryCode countryCode=" + i);
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager == null) {
            return 0;
        }
        try {
            carVendorUistyleManager.sendDLinkCountryCodeS(i);
            return 0;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public int setAcWarmFactoryMode(int i) {
        LogUtil.verbose(g, "setAcWarmFactoryMode");
        return 0;
    }

    public int setRncState(int i) {
        String str = g;
        LogUtil.verbose(str, " setRncState.in");
        CarVendorUistyleManager carVendorUistyleManager = this.f;
        if (carVendorUistyleManager != null) {
            try {
                carVendorUistyleManager.setRncStateS(i);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.verbose(str, " setRncState == null");
        }
        LogUtil.verbose(str, " setRncState.out=0");
        return 0;
    }

    public boolean subscribeSelfStudyCallback(ISelfStudyCallback iSelfStudyCallback) {
        com.ts.appservice.caradapter.a.i iVar = this.e;
        if (iVar == null || iSelfStudyCallback == null) {
            return false;
        }
        iVar.h(iSelfStudyCallback);
        if (!this.d) {
            return true;
        }
        this.e.l();
        return true;
    }

    public boolean unsubscribeSelfStudyCallback(ISelfStudyCallback iSelfStudyCallback) {
        com.ts.appservice.caradapter.a.i iVar = this.e;
        if (iVar == null || iSelfStudyCallback == null) {
            return false;
        }
        iVar.i(iSelfStudyCallback);
        return true;
    }
}
