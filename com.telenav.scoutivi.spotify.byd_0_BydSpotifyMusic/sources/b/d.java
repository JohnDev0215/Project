package b;

import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestDiPilotManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AbsBYDAutoDiPilotListener f3432a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3433b;

    /* JADX INFO: compiled from: TestDiPilotManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            d.this.a(message);
        }
    }

    public d() {
        HandlerThread handlerThread = new HandlerThread("TestDiPilotManagerImplCallback");
        handlerThread.start();
        this.f3433b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener = this.f3432a;
        if (absBYDAutoDiPilotListener != null) {
            int i7 = message.what;
            int i8 = message.arg1;
            switch (i7) {
                case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                    absBYDAutoDiPilotListener.onDrivieRemindStateChanged(i8);
                    break;
                case 5001:
                    absBYDAutoDiPilotListener.onDrivieDepartureStateChanged(i8);
                    break;
                case 5002:
                    absBYDAutoDiPilotListener.onFrontCarStartStateChange(i8);
                    break;
                case 5003:
                    absBYDAutoDiPilotListener.onFaultHandlerStateChanged(i8);
                    break;
                case 5004:
                    absBYDAutoDiPilotListener.onChargerCoverStateChanged(i8);
                    break;
                case 5005:
                    absBYDAutoDiPilotListener.onAiTurnLightStateChanged(i8);
                    break;
                case 5006:
                    absBYDAutoDiPilotListener.onRainOpenFogLightStateChanged(i8);
                    break;
                case 5007:
                    absBYDAutoDiPilotListener.onHighSpeedOpenWindowStateChanged(i8);
                    break;
                case 5008:
                    absBYDAutoDiPilotListener.onDriveLevelChanged(i8);
                    break;
                case 5009:
                    absBYDAutoDiPilotListener.onHighSpeedAINavigationStateChanged(i8);
                    break;
                case 5010:
                    absBYDAutoDiPilotListener.onUrbanAIAssistanceStateChanged(i8);
                    break;
                case 5011:
                    absBYDAutoDiPilotListener.onE2EAIAssistanceStateChanged(i8);
                    break;
                case 5012:
                    absBYDAutoDiPilotListener.onInteractiveLaneChangeAssistanceStateChanged(i8);
                    break;
                case 5013:
                    absBYDAutoDiPilotListener.onAINavigationStateChanged(i8);
                    break;
                case 5015:
                    absBYDAutoDiPilotListener.onAlarmTypeChanged(i8);
                    break;
                case 5016:
                    absBYDAutoDiPilotListener.onTrafficSignRecognizeStateChanged(i8);
                    break;
                case 5017:
                    absBYDAutoDiPilotListener.onAiCareStateChanged(i8);
                    break;
                case 5019:
                    absBYDAutoDiPilotListener.onMistakeGasStateChanged(i8);
                    break;
                case 5020:
                    absBYDAutoDiPilotListener.onAiMatrixLightStateChanged(i8);
                    break;
                case 5021:
                    absBYDAutoDiPilotListener.onAiDistanceLightStateChanged(i8);
                    break;
                case 5022:
                    absBYDAutoDiPilotListener.onTrafficLightRecognizeStateChanged(i8);
                    break;
                case 5023:
                    absBYDAutoDiPilotListener.onEmergencyLaneAssistanceStateChanged(i8);
                    break;
                case 5024:
                    absBYDAutoDiPilotListener.onBlindMonitorStateChanged(i8);
                    break;
                case 5025:
                    absBYDAutoDiPilotListener.onOpenDoorAlertStateChanged(i8);
                    break;
                case 5026:
                    absBYDAutoDiPilotListener.onRearCrashAlertStateChanged(i8);
                    break;
                case 5027:
                    absBYDAutoDiPilotListener.onRearTrafficCrossAlertStateChanged(i8);
                    break;
                case 5028:
                    absBYDAutoDiPilotListener.onRearTrafficBrakeAlertStateChanged(i8);
                    break;
                case 5029:
                    absBYDAutoDiPilotListener.onAiEmergencyBrakeStateChanged(i8);
                    break;
                case 5030:
                    absBYDAutoDiPilotListener.onPredictCrashAlertModeChanged(i8);
                    break;
                case 5031:
                    absBYDAutoDiPilotListener.onFrontTrafficCrossAlertStateChanged(i8);
                    break;
                case 5032:
                    absBYDAutoDiPilotListener.onFrontTrafficBrakeAlertStateChanged(i8);
                    break;
                case 5033:
                    absBYDAutoDiPilotListener.onElecCarBodyStablySysStateChanged(i8);
                    break;
                case 5034:
                    absBYDAutoDiPilotListener.onAutoParkingStateChanged(i8);
                    break;
                case 5035:
                    absBYDAutoDiPilotListener.onSteepDescentSlowDownStateChanged(i8);
                    break;
                case 5036:
                    absBYDAutoDiPilotListener.onFatigueDetectionAidStateChanged(i8);
                    break;
                case 5037:
                    absBYDAutoDiPilotListener.onRelieveFatigueStateChanged(i8);
                    break;
                case 5038:
                    absBYDAutoDiPilotListener.onRecommendRestSpotStateChanged(i8);
                    break;
                case 5039:
                    absBYDAutoDiPilotListener.onTemperatureWhileFatigueChanged(i8);
                    break;
                case 5040:
                    absBYDAutoDiPilotListener.onDistractionDetectionAidStateChanged(i8);
                    break;
                case 5041:
                    absBYDAutoDiPilotListener.onAttentionDetectionStateChanged(i8);
                    break;
                case 5042:
                    absBYDAutoDiPilotListener.onDangerousActionDetectionStateChanged(i8);
                    break;
                case 5043:
                    absBYDAutoDiPilotListener.onGazePerceptionStateChanged(i8);
                    break;
                case 5044:
                    absBYDAutoDiPilotListener.onChildCareStateChanged(i8);
                    break;
                case 5045:
                    absBYDAutoDiPilotListener.onGestureRecognitionStateChanged(i8);
                    break;
                case 5046:
                    absBYDAutoDiPilotListener.onLeftBehindReminderStateChanged(i8);
                    break;
                case 5047:
                    absBYDAutoDiPilotListener.onRequestHUNPClose(i8);
                    break;
                case 5048:
                    absBYDAutoDiPilotListener.onLaneAssistTypeChanged(i8);
                    break;
                case 5049:
                    absBYDAutoDiPilotListener.onVerifyModeChanged(i8);
                    break;
                case 5050:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitControlStateChanged(i8);
                    break;
                case 5051:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitAlertStateChanged(i8);
                    break;
                case 5052:
                    absBYDAutoDiPilotListener.onSpeedLimitRangeChanged(i8);
                    break;
                case 5053:
                    absBYDAutoDiPilotListener.onDriveDepartureStateChanged(i8);
                    break;
                case 5054:
                    absBYDAutoDiPilotListener.onDriveRemindStateChanged(i8);
                    break;
            }
        }
    }

    public void b(int i7, int i8) {
        if (this.f3433b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            this.f3433b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        this.f3432a = absBYDAutoDiPilotListener;
    }
}
