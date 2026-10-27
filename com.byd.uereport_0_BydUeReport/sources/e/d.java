package e;

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
    public AbsBYDAutoDiPilotListener f4480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4481b;

    /* JADX INFO: compiled from: TestDiPilotManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f4481b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener = this.f4480a;
        if (absBYDAutoDiPilotListener != null) {
            int i8 = message.what;
            int i9 = message.arg1;
            switch (i8) {
                case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                    absBYDAutoDiPilotListener.onDrivieRemindStateChanged(i9);
                    break;
                case 5001:
                    absBYDAutoDiPilotListener.onDrivieDepartureStateChanged(i9);
                    break;
                case 5002:
                    absBYDAutoDiPilotListener.onFrontCarStartStateChange(i9);
                    break;
                case 5003:
                    absBYDAutoDiPilotListener.onFaultHandlerStateChanged(i9);
                    break;
                case 5004:
                    absBYDAutoDiPilotListener.onChargerCoverStateChanged(i9);
                    break;
                case 5005:
                    absBYDAutoDiPilotListener.onAiTurnLightStateChanged(i9);
                    break;
                case 5006:
                    absBYDAutoDiPilotListener.onRainOpenFogLightStateChanged(i9);
                    break;
                case 5007:
                    absBYDAutoDiPilotListener.onHighSpeedOpenWindowStateChanged(i9);
                    break;
                case 5008:
                    absBYDAutoDiPilotListener.onDriveLevelChanged(i9);
                    break;
                case 5009:
                    absBYDAutoDiPilotListener.onHighSpeedAINavigationStateChanged(i9);
                    break;
                case 5010:
                    absBYDAutoDiPilotListener.onUrbanAIAssistanceStateChanged(i9);
                    break;
                case 5011:
                    absBYDAutoDiPilotListener.onE2EAIAssistanceStateChanged(i9);
                    break;
                case 5012:
                    absBYDAutoDiPilotListener.onInteractiveLaneChangeAssistanceStateChanged(i9);
                    break;
                case 5013:
                    absBYDAutoDiPilotListener.onAINavigationStateChanged(i9);
                    break;
                case 5015:
                    absBYDAutoDiPilotListener.onAlarmTypeChanged(i9);
                    break;
                case 5016:
                    absBYDAutoDiPilotListener.onTrafficSignRecognizeStateChanged(i9);
                    break;
                case 5017:
                    absBYDAutoDiPilotListener.onAiCareStateChanged(i9);
                    break;
                case 5019:
                    absBYDAutoDiPilotListener.onMistakeGasStateChanged(i9);
                    break;
                case 5020:
                    absBYDAutoDiPilotListener.onAiMatrixLightStateChanged(i9);
                    break;
                case 5021:
                    absBYDAutoDiPilotListener.onAiDistanceLightStateChanged(i9);
                    break;
                case 5022:
                    absBYDAutoDiPilotListener.onTrafficLightRecognizeStateChanged(i9);
                    break;
                case 5023:
                    absBYDAutoDiPilotListener.onEmergencyLaneAssistanceStateChanged(i9);
                    break;
                case 5024:
                    absBYDAutoDiPilotListener.onBlindMonitorStateChanged(i9);
                    break;
                case 5025:
                    absBYDAutoDiPilotListener.onOpenDoorAlertStateChanged(i9);
                    break;
                case 5026:
                    absBYDAutoDiPilotListener.onRearCrashAlertStateChanged(i9);
                    break;
                case 5027:
                    absBYDAutoDiPilotListener.onRearTrafficCrossAlertStateChanged(i9);
                    break;
                case 5028:
                    absBYDAutoDiPilotListener.onRearTrafficBrakeAlertStateChanged(i9);
                    break;
                case 5029:
                    absBYDAutoDiPilotListener.onAiEmergencyBrakeStateChanged(i9);
                    break;
                case 5030:
                    absBYDAutoDiPilotListener.onPredictCrashAlertModeChanged(i9);
                    break;
                case 5031:
                    absBYDAutoDiPilotListener.onFrontTrafficCrossAlertStateChanged(i9);
                    break;
                case 5032:
                    absBYDAutoDiPilotListener.onFrontTrafficBrakeAlertStateChanged(i9);
                    break;
                case 5033:
                    absBYDAutoDiPilotListener.onElecCarBodyStablySysStateChanged(i9);
                    break;
                case 5034:
                    absBYDAutoDiPilotListener.onAutoParkingStateChanged(i9);
                    break;
                case 5035:
                    absBYDAutoDiPilotListener.onSteepDescentSlowDownStateChanged(i9);
                    break;
                case 5036:
                    absBYDAutoDiPilotListener.onFatigueDetectionAidStateChanged(i9);
                    break;
                case 5037:
                    absBYDAutoDiPilotListener.onRelieveFatigueStateChanged(i9);
                    break;
                case 5038:
                    absBYDAutoDiPilotListener.onRecommendRestSpotStateChanged(i9);
                    break;
                case 5039:
                    absBYDAutoDiPilotListener.onTemperatureWhileFatigueChanged(i9);
                    break;
                case 5040:
                    absBYDAutoDiPilotListener.onDistractionDetectionAidStateChanged(i9);
                    break;
                case 5041:
                    absBYDAutoDiPilotListener.onAttentionDetectionStateChanged(i9);
                    break;
                case 5042:
                    absBYDAutoDiPilotListener.onDangerousActionDetectionStateChanged(i9);
                    break;
                case 5043:
                    absBYDAutoDiPilotListener.onGazePerceptionStateChanged(i9);
                    break;
                case 5044:
                    absBYDAutoDiPilotListener.onChildCareStateChanged(i9);
                    break;
                case 5045:
                    absBYDAutoDiPilotListener.onGestureRecognitionStateChanged(i9);
                    break;
                case 5046:
                    absBYDAutoDiPilotListener.onLeftBehindReminderStateChanged(i9);
                    break;
                case 5047:
                    absBYDAutoDiPilotListener.onRequestHUNPClose(i9);
                    break;
                case 5048:
                    absBYDAutoDiPilotListener.onLaneAssistTypeChanged(i9);
                    break;
                case 5049:
                    absBYDAutoDiPilotListener.onVerifyModeChanged(i9);
                    break;
                case 5050:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitControlStateChanged(i9);
                    break;
                case 5051:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitAlertStateChanged(i9);
                    break;
                case 5052:
                    absBYDAutoDiPilotListener.onSpeedLimitRangeChanged(i9);
                    break;
                case 5053:
                    absBYDAutoDiPilotListener.onDriveDepartureStateChanged(i9);
                    break;
                case 5054:
                    absBYDAutoDiPilotListener.onDriveRemindStateChanged(i9);
                    break;
            }
        }
    }

    public void b(int i8, int i9) {
        if (this.f4481b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            this.f4481b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        this.f4480a = absBYDAutoDiPilotListener;
    }
}
