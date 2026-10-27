package android.hardware.bydauto_test;

import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
public class TestDiPilotManagerImplCallback {
    public static final int REQUEST_HUNP_CLOSE = 5047;
    public static final int SET_AI_CARE_STATE = 5017;
    public static final int SET_AI_DISTANCE_LIGHT_STATE = 5021;
    public static final int SET_AI_EMERGENCY_BRAKE_STATE = 5029;
    public static final int SET_AI_MATRIX_LIGHT_STATE = 5020;
    public static final int SET_AI_NAVIGATION_STATE = 5013;
    public static final int SET_AI_TURN_LIGHT_STATE = 5005;
    public static final int SET_ALARM_TYPE = 5015;
    public static final int SET_ATTENTION_DETECTION_STATE = 5041;
    public static final int SET_AUTO_PARKING_STATE = 5034;
    public static final int SET_BLIND_MONITOR_STATE = 5024;
    public static final int SET_CHARGER_COVER_STATE = 5004;
    public static final int SET_CHILD_CARE_STATE = 5044;
    public static final int SET_DANGEROUS_ACTION_DETECTION_STATE = 5042;
    public static final int SET_DISTRACTION_DETECTION_AID_STATE = 5040;
    public static final int SET_DRIVE_DEPARTUR_STATE = 5053;
    public static final int SET_DRIVE_LEVEL = 5008;
    public static final int SET_DRIVE_REMIND_STATE = 5054;
    public static final int SET_DRIVIE_DEPARTUR_STATE = 5001;
    public static final int SET_DRIVIE_REMIND_STATE = 5000;
    public static final int SET_E2E_AI_ASSISTANCE_STATE = 5011;
    public static final int SET_ELEC_CAR_BODY_STABLY_SYS_STATE = 5033;
    public static final int SET_EMERGENCY_LANE_ASSISTANCE_STATE = 5023;
    public static final int SET_FATIGUE_DETECTION_AID_STATE = 5036;
    public static final int SET_FAULT_HANDLER_STATE = 5003;
    public static final int SET_FRONT_CAR_START_STATE = 5002;
    public static final int SET_FRONT_TRAFFIC_BRAKE_ALERT_STATE = 5032;
    public static final int SET_FRONT_TRAFFIC_CROSS_ALERT_STATE = 5031;
    public static final int SET_GAZE_PERCEPTION_STATE = 5043;
    public static final int SET_GESTURE_RECOGNITION_STATE = 5045;
    public static final int SET_HIGH_SPEED_AI_NAVIGATION_STATE = 5009;
    public static final int SET_HIGH_SPEED_OPEN_WINDOW_STATE = 5007;
    public static final int SET_INTELLIGENT_SPEED_LIMIT_ALERT_STATE = 5051;
    public static final int SET_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE = 5050;
    public static final int SET_INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE = 5012;
    public static final int SET_LANE_ASSIST_TYPE = 5048;
    public static final int SET_LEFT_BEHIND_REMINDER_STATE = 5046;
    public static final int SET_MISTAKE_GAS_STATE = 5019;
    public static final int SET_OPEN_DOOR_ALERT_STATE = 5025;
    public static final int SET_PRACTICE_CARE_MODE_STATE = 5018;
    public static final int SET_PREDICT_CRASH_ALERT_STATE = 5030;
    public static final int SET_RAIN_OPEN_FOG_LIGHT_STATE = 5006;
    public static final int SET_REAR_CRASH_ALERT_STATE = 5026;
    public static final int SET_REAR_TRAFFIC_BRAKE_ALERT_STATE = 5028;
    public static final int SET_REAR_TRAFFIC_CROSS_ALERT_STATE = 5027;
    public static final int SET_RECOMMEND_REST_SPOT_STATE = 5038;
    public static final int SET_RELIEVE_FATIGUE_STATE = 5037;
    public static final int SET_SPEED_LIMIT_RANGE = 5052;
    public static final int SET_STEEP_DESCENT_SLOW_DOWN_STATE = 5035;
    public static final int SET_TEMPERATURE_WHILE_FATIGUE = 5039;
    public static final int SET_TRAFFIC_LIGHT_RECOGNIZE_STATE = 5022;
    public static final int SET_TRAFFIC_SIGN_RECOGNIZE_STATE = 5016;
    public static final int SET_URBAN_AI_ASSISTANCE_STATE = 5010;
    public static final int SET_VERIFY_MODE = 5049;
    private AbsBYDAutoDiPilotListener mAbsBYDAutoDiPilotListener;
    private Handler mHandler;

    public void setAbsBYDAutoDiPilotListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        this.mAbsBYDAutoDiPilotListener = absBYDAutoDiPilotListener;
    }

    public TestDiPilotManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestDiPilotManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestDiPilotManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestDiPilotManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void enqueueMessage(int i, int i2) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBYDAutoDiPilotListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            switch (i) {
                case 5000:
                    this.mAbsBYDAutoDiPilotListener.onDrivieRemindStateChanged(i2);
                    break;
                case 5001:
                    this.mAbsBYDAutoDiPilotListener.onDrivieDepartureStateChanged(i2);
                    break;
                case 5002:
                    this.mAbsBYDAutoDiPilotListener.onFrontCarStartStateChange(i2);
                    break;
                case 5003:
                    this.mAbsBYDAutoDiPilotListener.onFaultHandlerStateChanged(i2);
                    break;
                case 5004:
                    this.mAbsBYDAutoDiPilotListener.onChargerCoverStateChanged(i2);
                    break;
                case SET_AI_TURN_LIGHT_STATE /* 5005 */:
                    this.mAbsBYDAutoDiPilotListener.onAiTurnLightStateChanged(i2);
                    break;
                case SET_RAIN_OPEN_FOG_LIGHT_STATE /* 5006 */:
                    this.mAbsBYDAutoDiPilotListener.onRainOpenFogLightStateChanged(i2);
                    break;
                case SET_HIGH_SPEED_OPEN_WINDOW_STATE /* 5007 */:
                    this.mAbsBYDAutoDiPilotListener.onHighSpeedOpenWindowStateChanged(i2);
                    break;
                case SET_DRIVE_LEVEL /* 5008 */:
                    this.mAbsBYDAutoDiPilotListener.onDriveLevelChanged(i2);
                    break;
                case SET_HIGH_SPEED_AI_NAVIGATION_STATE /* 5009 */:
                    this.mAbsBYDAutoDiPilotListener.onHighSpeedAINavigationStateChanged(i2);
                    break;
                case SET_URBAN_AI_ASSISTANCE_STATE /* 5010 */:
                    this.mAbsBYDAutoDiPilotListener.onUrbanAIAssistanceStateChanged(i2);
                    break;
                case SET_E2E_AI_ASSISTANCE_STATE /* 5011 */:
                    this.mAbsBYDAutoDiPilotListener.onE2EAIAssistanceStateChanged(i2);
                    break;
                case SET_INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE /* 5012 */:
                    this.mAbsBYDAutoDiPilotListener.onInteractiveLaneChangeAssistanceStateChanged(i2);
                    break;
                case SET_AI_NAVIGATION_STATE /* 5013 */:
                    this.mAbsBYDAutoDiPilotListener.onAINavigationStateChanged(i2);
                    break;
                case SET_ALARM_TYPE /* 5015 */:
                    this.mAbsBYDAutoDiPilotListener.onAlarmTypeChanged(i2);
                    break;
                case SET_TRAFFIC_SIGN_RECOGNIZE_STATE /* 5016 */:
                    this.mAbsBYDAutoDiPilotListener.onTrafficSignRecognizeStateChanged(i2);
                    break;
                case SET_AI_CARE_STATE /* 5017 */:
                    this.mAbsBYDAutoDiPilotListener.onAiCareStateChanged(i2);
                    break;
                case SET_MISTAKE_GAS_STATE /* 5019 */:
                    this.mAbsBYDAutoDiPilotListener.onMistakeGasStateChanged(i2);
                    break;
                case SET_AI_MATRIX_LIGHT_STATE /* 5020 */:
                    this.mAbsBYDAutoDiPilotListener.onAiMatrixLightStateChanged(i2);
                    break;
                case SET_AI_DISTANCE_LIGHT_STATE /* 5021 */:
                    this.mAbsBYDAutoDiPilotListener.onAiDistanceLightStateChanged(i2);
                    break;
                case SET_TRAFFIC_LIGHT_RECOGNIZE_STATE /* 5022 */:
                    this.mAbsBYDAutoDiPilotListener.onTrafficLightRecognizeStateChanged(i2);
                    break;
                case SET_EMERGENCY_LANE_ASSISTANCE_STATE /* 5023 */:
                    this.mAbsBYDAutoDiPilotListener.onEmergencyLaneAssistanceStateChanged(i2);
                    break;
                case SET_BLIND_MONITOR_STATE /* 5024 */:
                    this.mAbsBYDAutoDiPilotListener.onBlindMonitorStateChanged(i2);
                    break;
                case SET_OPEN_DOOR_ALERT_STATE /* 5025 */:
                    this.mAbsBYDAutoDiPilotListener.onOpenDoorAlertStateChanged(i2);
                    break;
                case SET_REAR_CRASH_ALERT_STATE /* 5026 */:
                    this.mAbsBYDAutoDiPilotListener.onRearCrashAlertStateChanged(i2);
                    break;
                case SET_REAR_TRAFFIC_CROSS_ALERT_STATE /* 5027 */:
                    this.mAbsBYDAutoDiPilotListener.onRearTrafficCrossAlertStateChanged(i2);
                    break;
                case SET_REAR_TRAFFIC_BRAKE_ALERT_STATE /* 5028 */:
                    this.mAbsBYDAutoDiPilotListener.onRearTrafficBrakeAlertStateChanged(i2);
                    break;
                case SET_AI_EMERGENCY_BRAKE_STATE /* 5029 */:
                    this.mAbsBYDAutoDiPilotListener.onAiEmergencyBrakeStateChanged(i2);
                    break;
                case SET_PREDICT_CRASH_ALERT_STATE /* 5030 */:
                    this.mAbsBYDAutoDiPilotListener.onPredictCrashAlertModeChanged(i2);
                    break;
                case SET_FRONT_TRAFFIC_CROSS_ALERT_STATE /* 5031 */:
                    this.mAbsBYDAutoDiPilotListener.onFrontTrafficCrossAlertStateChanged(i2);
                    break;
                case SET_FRONT_TRAFFIC_BRAKE_ALERT_STATE /* 5032 */:
                    this.mAbsBYDAutoDiPilotListener.onFrontTrafficBrakeAlertStateChanged(i2);
                    break;
                case SET_ELEC_CAR_BODY_STABLY_SYS_STATE /* 5033 */:
                    this.mAbsBYDAutoDiPilotListener.onElecCarBodyStablySysStateChanged(i2);
                    break;
                case SET_AUTO_PARKING_STATE /* 5034 */:
                    this.mAbsBYDAutoDiPilotListener.onAutoParkingStateChanged(i2);
                    break;
                case SET_STEEP_DESCENT_SLOW_DOWN_STATE /* 5035 */:
                    this.mAbsBYDAutoDiPilotListener.onSteepDescentSlowDownStateChanged(i2);
                    break;
                case SET_FATIGUE_DETECTION_AID_STATE /* 5036 */:
                    this.mAbsBYDAutoDiPilotListener.onFatigueDetectionAidStateChanged(i2);
                    break;
                case SET_RELIEVE_FATIGUE_STATE /* 5037 */:
                    this.mAbsBYDAutoDiPilotListener.onRelieveFatigueStateChanged(i2);
                    break;
                case SET_RECOMMEND_REST_SPOT_STATE /* 5038 */:
                    this.mAbsBYDAutoDiPilotListener.onRecommendRestSpotStateChanged(i2);
                    break;
                case SET_TEMPERATURE_WHILE_FATIGUE /* 5039 */:
                    this.mAbsBYDAutoDiPilotListener.onTemperatureWhileFatigueChanged(i2);
                    break;
                case SET_DISTRACTION_DETECTION_AID_STATE /* 5040 */:
                    this.mAbsBYDAutoDiPilotListener.onDistractionDetectionAidStateChanged(i2);
                    break;
                case SET_ATTENTION_DETECTION_STATE /* 5041 */:
                    this.mAbsBYDAutoDiPilotListener.onAttentionDetectionStateChanged(i2);
                    break;
                case SET_DANGEROUS_ACTION_DETECTION_STATE /* 5042 */:
                    this.mAbsBYDAutoDiPilotListener.onDangerousActionDetectionStateChanged(i2);
                    break;
                case SET_GAZE_PERCEPTION_STATE /* 5043 */:
                    this.mAbsBYDAutoDiPilotListener.onGazePerceptionStateChanged(i2);
                    break;
                case SET_CHILD_CARE_STATE /* 5044 */:
                    this.mAbsBYDAutoDiPilotListener.onChildCareStateChanged(i2);
                    break;
                case SET_GESTURE_RECOGNITION_STATE /* 5045 */:
                    this.mAbsBYDAutoDiPilotListener.onGestureRecognitionStateChanged(i2);
                    break;
                case SET_LEFT_BEHIND_REMINDER_STATE /* 5046 */:
                    this.mAbsBYDAutoDiPilotListener.onLeftBehindReminderStateChanged(i2);
                    break;
                case REQUEST_HUNP_CLOSE /* 5047 */:
                    this.mAbsBYDAutoDiPilotListener.onRequestHUNPClose(i2);
                    break;
                case SET_LANE_ASSIST_TYPE /* 5048 */:
                    this.mAbsBYDAutoDiPilotListener.onLaneAssistTypeChanged(i2);
                    break;
                case SET_VERIFY_MODE /* 5049 */:
                    this.mAbsBYDAutoDiPilotListener.onVerifyModeChanged(i2);
                    break;
                case SET_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE /* 5050 */:
                    this.mAbsBYDAutoDiPilotListener.onIntelligentSpeedLimitControlStateChanged(i2);
                    break;
                case SET_INTELLIGENT_SPEED_LIMIT_ALERT_STATE /* 5051 */:
                    this.mAbsBYDAutoDiPilotListener.onIntelligentSpeedLimitAlertStateChanged(i2);
                    break;
                case SET_SPEED_LIMIT_RANGE /* 5052 */:
                    this.mAbsBYDAutoDiPilotListener.onSpeedLimitRangeChanged(i2);
                    break;
                case SET_DRIVE_DEPARTUR_STATE /* 5053 */:
                    this.mAbsBYDAutoDiPilotListener.onDriveDepartureStateChanged(i2);
                    break;
                case SET_DRIVE_REMIND_STATE /* 5054 */:
                    this.mAbsBYDAutoDiPilotListener.onDriveRemindStateChanged(i2);
                    break;
            }
        }
    }
}
