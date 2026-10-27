package android.hardware.bydauto_test;

import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
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

    public void dispatchMessageCallBack(Message message) {
        AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener = this.mAbsBYDAutoDiPilotListener;
        if (absBYDAutoDiPilotListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            switch (i) {
                case 5000:
                    absBYDAutoDiPilotListener.onDrivieRemindStateChanged(i2);
                    break;
                case 5001:
                    absBYDAutoDiPilotListener.onDrivieDepartureStateChanged(i2);
                    break;
                case 5002:
                    absBYDAutoDiPilotListener.onFrontCarStartStateChange(i2);
                    break;
                case 5003:
                    absBYDAutoDiPilotListener.onFaultHandlerStateChanged(i2);
                    break;
                case 5004:
                    absBYDAutoDiPilotListener.onChargerCoverStateChanged(i2);
                    break;
                case SET_AI_TURN_LIGHT_STATE /* 5005 */:
                    absBYDAutoDiPilotListener.onAiTurnLightStateChanged(i2);
                    break;
                case SET_RAIN_OPEN_FOG_LIGHT_STATE /* 5006 */:
                    absBYDAutoDiPilotListener.onRainOpenFogLightStateChanged(i2);
                    break;
                case SET_HIGH_SPEED_OPEN_WINDOW_STATE /* 5007 */:
                    absBYDAutoDiPilotListener.onHighSpeedOpenWindowStateChanged(i2);
                    break;
                case SET_DRIVE_LEVEL /* 5008 */:
                    absBYDAutoDiPilotListener.onDriveLevelChanged(i2);
                    break;
                case SET_HIGH_SPEED_AI_NAVIGATION_STATE /* 5009 */:
                    absBYDAutoDiPilotListener.onHighSpeedAINavigationStateChanged(i2);
                    break;
                case SET_URBAN_AI_ASSISTANCE_STATE /* 5010 */:
                    absBYDAutoDiPilotListener.onUrbanAIAssistanceStateChanged(i2);
                    break;
                case SET_E2E_AI_ASSISTANCE_STATE /* 5011 */:
                    absBYDAutoDiPilotListener.onE2EAIAssistanceStateChanged(i2);
                    break;
                case SET_INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE /* 5012 */:
                    absBYDAutoDiPilotListener.onInteractiveLaneChangeAssistanceStateChanged(i2);
                    break;
                case SET_AI_NAVIGATION_STATE /* 5013 */:
                    absBYDAutoDiPilotListener.onAINavigationStateChanged(i2);
                    break;
                case SET_ALARM_TYPE /* 5015 */:
                    absBYDAutoDiPilotListener.onAlarmTypeChanged(i2);
                    break;
                case SET_TRAFFIC_SIGN_RECOGNIZE_STATE /* 5016 */:
                    absBYDAutoDiPilotListener.onTrafficSignRecognizeStateChanged(i2);
                    break;
                case SET_AI_CARE_STATE /* 5017 */:
                    absBYDAutoDiPilotListener.onAiCareStateChanged(i2);
                    break;
                case SET_MISTAKE_GAS_STATE /* 5019 */:
                    absBYDAutoDiPilotListener.onMistakeGasStateChanged(i2);
                    break;
                case SET_AI_MATRIX_LIGHT_STATE /* 5020 */:
                    absBYDAutoDiPilotListener.onAiMatrixLightStateChanged(i2);
                    break;
                case SET_AI_DISTANCE_LIGHT_STATE /* 5021 */:
                    absBYDAutoDiPilotListener.onAiDistanceLightStateChanged(i2);
                    break;
                case SET_TRAFFIC_LIGHT_RECOGNIZE_STATE /* 5022 */:
                    absBYDAutoDiPilotListener.onTrafficLightRecognizeStateChanged(i2);
                    break;
                case SET_EMERGENCY_LANE_ASSISTANCE_STATE /* 5023 */:
                    absBYDAutoDiPilotListener.onEmergencyLaneAssistanceStateChanged(i2);
                    break;
                case SET_BLIND_MONITOR_STATE /* 5024 */:
                    absBYDAutoDiPilotListener.onBlindMonitorStateChanged(i2);
                    break;
                case SET_OPEN_DOOR_ALERT_STATE /* 5025 */:
                    absBYDAutoDiPilotListener.onOpenDoorAlertStateChanged(i2);
                    break;
                case SET_REAR_CRASH_ALERT_STATE /* 5026 */:
                    absBYDAutoDiPilotListener.onRearCrashAlertStateChanged(i2);
                    break;
                case SET_REAR_TRAFFIC_CROSS_ALERT_STATE /* 5027 */:
                    absBYDAutoDiPilotListener.onRearTrafficCrossAlertStateChanged(i2);
                    break;
                case SET_REAR_TRAFFIC_BRAKE_ALERT_STATE /* 5028 */:
                    absBYDAutoDiPilotListener.onRearTrafficBrakeAlertStateChanged(i2);
                    break;
                case SET_AI_EMERGENCY_BRAKE_STATE /* 5029 */:
                    absBYDAutoDiPilotListener.onAiEmergencyBrakeStateChanged(i2);
                    break;
                case SET_PREDICT_CRASH_ALERT_STATE /* 5030 */:
                    absBYDAutoDiPilotListener.onPredictCrashAlertModeChanged(i2);
                    break;
                case SET_FRONT_TRAFFIC_CROSS_ALERT_STATE /* 5031 */:
                    absBYDAutoDiPilotListener.onFrontTrafficCrossAlertStateChanged(i2);
                    break;
                case SET_FRONT_TRAFFIC_BRAKE_ALERT_STATE /* 5032 */:
                    absBYDAutoDiPilotListener.onFrontTrafficBrakeAlertStateChanged(i2);
                    break;
                case SET_ELEC_CAR_BODY_STABLY_SYS_STATE /* 5033 */:
                    absBYDAutoDiPilotListener.onElecCarBodyStablySysStateChanged(i2);
                    break;
                case SET_AUTO_PARKING_STATE /* 5034 */:
                    absBYDAutoDiPilotListener.onAutoParkingStateChanged(i2);
                    break;
                case SET_STEEP_DESCENT_SLOW_DOWN_STATE /* 5035 */:
                    absBYDAutoDiPilotListener.onSteepDescentSlowDownStateChanged(i2);
                    break;
                case SET_FATIGUE_DETECTION_AID_STATE /* 5036 */:
                    absBYDAutoDiPilotListener.onFatigueDetectionAidStateChanged(i2);
                    break;
                case SET_RELIEVE_FATIGUE_STATE /* 5037 */:
                    absBYDAutoDiPilotListener.onRelieveFatigueStateChanged(i2);
                    break;
                case SET_RECOMMEND_REST_SPOT_STATE /* 5038 */:
                    absBYDAutoDiPilotListener.onRecommendRestSpotStateChanged(i2);
                    break;
                case SET_TEMPERATURE_WHILE_FATIGUE /* 5039 */:
                    absBYDAutoDiPilotListener.onTemperatureWhileFatigueChanged(i2);
                    break;
                case SET_DISTRACTION_DETECTION_AID_STATE /* 5040 */:
                    absBYDAutoDiPilotListener.onDistractionDetectionAidStateChanged(i2);
                    break;
                case SET_ATTENTION_DETECTION_STATE /* 5041 */:
                    absBYDAutoDiPilotListener.onAttentionDetectionStateChanged(i2);
                    break;
                case SET_DANGEROUS_ACTION_DETECTION_STATE /* 5042 */:
                    absBYDAutoDiPilotListener.onDangerousActionDetectionStateChanged(i2);
                    break;
                case SET_GAZE_PERCEPTION_STATE /* 5043 */:
                    absBYDAutoDiPilotListener.onGazePerceptionStateChanged(i2);
                    break;
                case SET_CHILD_CARE_STATE /* 5044 */:
                    absBYDAutoDiPilotListener.onChildCareStateChanged(i2);
                    break;
                case SET_GESTURE_RECOGNITION_STATE /* 5045 */:
                    absBYDAutoDiPilotListener.onGestureRecognitionStateChanged(i2);
                    break;
                case SET_LEFT_BEHIND_REMINDER_STATE /* 5046 */:
                    absBYDAutoDiPilotListener.onLeftBehindReminderStateChanged(i2);
                    break;
                case REQUEST_HUNP_CLOSE /* 5047 */:
                    absBYDAutoDiPilotListener.onRequestHUNPClose(i2);
                    break;
                case SET_LANE_ASSIST_TYPE /* 5048 */:
                    absBYDAutoDiPilotListener.onLaneAssistTypeChanged(i2);
                    break;
                case SET_VERIFY_MODE /* 5049 */:
                    absBYDAutoDiPilotListener.onVerifyModeChanged(i2);
                    break;
                case SET_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE /* 5050 */:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitControlStateChanged(i2);
                    break;
                case SET_INTELLIGENT_SPEED_LIMIT_ALERT_STATE /* 5051 */:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitAlertStateChanged(i2);
                    break;
                case SET_SPEED_LIMIT_RANGE /* 5052 */:
                    absBYDAutoDiPilotListener.onSpeedLimitRangeChanged(i2);
                    break;
                case SET_DRIVE_DEPARTUR_STATE /* 5053 */:
                    absBYDAutoDiPilotListener.onDriveDepartureStateChanged(i2);
                    break;
                case SET_DRIVE_REMIND_STATE /* 5054 */:
                    absBYDAutoDiPilotListener.onDriveRemindStateChanged(i2);
                    break;
            }
        }
    }

    public void enqueueMessage(int i, int i2) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void setAbsBYDAutoDiPilotListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        this.mAbsBYDAutoDiPilotListener = absBYDAutoDiPilotListener;
    }
}
