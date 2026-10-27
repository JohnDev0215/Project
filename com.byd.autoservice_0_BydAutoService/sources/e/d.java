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
    public AbsBYDAutoDiPilotListener f2752a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2753b;

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
        this.f2753b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener = this.f2752a;
        if (absBYDAutoDiPilotListener != null) {
            int i3 = message.what;
            int i4 = message.arg1;
            switch (i3) {
                case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                    absBYDAutoDiPilotListener.onDrivieRemindStateChanged(i4);
                    break;
                case 5001:
                    absBYDAutoDiPilotListener.onDrivieDepartureStateChanged(i4);
                    break;
                case 5002:
                    absBYDAutoDiPilotListener.onFrontCarStartStateChange(i4);
                    break;
                case 5003:
                    absBYDAutoDiPilotListener.onFaultHandlerStateChanged(i4);
                    break;
                case 5004:
                    absBYDAutoDiPilotListener.onChargerCoverStateChanged(i4);
                    break;
                case 5005:
                    absBYDAutoDiPilotListener.onAiTurnLightStateChanged(i4);
                    break;
                case 5006:
                    absBYDAutoDiPilotListener.onRainOpenFogLightStateChanged(i4);
                    break;
                case 5007:
                    absBYDAutoDiPilotListener.onHighSpeedOpenWindowStateChanged(i4);
                    break;
                case 5008:
                    absBYDAutoDiPilotListener.onDriveLevelChanged(i4);
                    break;
                case 5009:
                    absBYDAutoDiPilotListener.onHighSpeedAINavigationStateChanged(i4);
                    break;
                case 5010:
                    absBYDAutoDiPilotListener.onUrbanAIAssistanceStateChanged(i4);
                    break;
                case 5011:
                    absBYDAutoDiPilotListener.onE2EAIAssistanceStateChanged(i4);
                    break;
                case 5012:
                    absBYDAutoDiPilotListener.onInteractiveLaneChangeAssistanceStateChanged(i4);
                    break;
                case 5013:
                    absBYDAutoDiPilotListener.onAINavigationStateChanged(i4);
                    break;
                case 5015:
                    absBYDAutoDiPilotListener.onAlarmTypeChanged(i4);
                    break;
                case 5016:
                    absBYDAutoDiPilotListener.onTrafficSignRecognizeStateChanged(i4);
                    break;
                case 5017:
                    absBYDAutoDiPilotListener.onAiCareStateChanged(i4);
                    break;
                case 5019:
                    absBYDAutoDiPilotListener.onMistakeGasStateChanged(i4);
                    break;
                case 5020:
                    absBYDAutoDiPilotListener.onAiMatrixLightStateChanged(i4);
                    break;
                case 5021:
                    absBYDAutoDiPilotListener.onAiDistanceLightStateChanged(i4);
                    break;
                case 5022:
                    absBYDAutoDiPilotListener.onTrafficLightRecognizeStateChanged(i4);
                    break;
                case 5023:
                    absBYDAutoDiPilotListener.onEmergencyLaneAssistanceStateChanged(i4);
                    break;
                case 5024:
                    absBYDAutoDiPilotListener.onBlindMonitorStateChanged(i4);
                    break;
                case 5025:
                    absBYDAutoDiPilotListener.onOpenDoorAlertStateChanged(i4);
                    break;
                case 5026:
                    absBYDAutoDiPilotListener.onRearCrashAlertStateChanged(i4);
                    break;
                case 5027:
                    absBYDAutoDiPilotListener.onRearTrafficCrossAlertStateChanged(i4);
                    break;
                case 5028:
                    absBYDAutoDiPilotListener.onRearTrafficBrakeAlertStateChanged(i4);
                    break;
                case 5029:
                    absBYDAutoDiPilotListener.onAiEmergencyBrakeStateChanged(i4);
                    break;
                case 5030:
                    absBYDAutoDiPilotListener.onPredictCrashAlertModeChanged(i4);
                    break;
                case 5031:
                    absBYDAutoDiPilotListener.onFrontTrafficCrossAlertStateChanged(i4);
                    break;
                case 5032:
                    absBYDAutoDiPilotListener.onFrontTrafficBrakeAlertStateChanged(i4);
                    break;
                case 5033:
                    absBYDAutoDiPilotListener.onElecCarBodyStablySysStateChanged(i4);
                    break;
                case 5034:
                    absBYDAutoDiPilotListener.onAutoParkingStateChanged(i4);
                    break;
                case 5035:
                    absBYDAutoDiPilotListener.onSteepDescentSlowDownStateChanged(i4);
                    break;
                case 5036:
                    absBYDAutoDiPilotListener.onFatigueDetectionAidStateChanged(i4);
                    break;
                case 5037:
                    absBYDAutoDiPilotListener.onRelieveFatigueStateChanged(i4);
                    break;
                case 5038:
                    absBYDAutoDiPilotListener.onRecommendRestSpotStateChanged(i4);
                    break;
                case 5039:
                    absBYDAutoDiPilotListener.onTemperatureWhileFatigueChanged(i4);
                    break;
                case 5040:
                    absBYDAutoDiPilotListener.onDistractionDetectionAidStateChanged(i4);
                    break;
                case 5041:
                    absBYDAutoDiPilotListener.onAttentionDetectionStateChanged(i4);
                    break;
                case 5042:
                    absBYDAutoDiPilotListener.onDangerousActionDetectionStateChanged(i4);
                    break;
                case 5043:
                    absBYDAutoDiPilotListener.onGazePerceptionStateChanged(i4);
                    break;
                case 5044:
                    absBYDAutoDiPilotListener.onChildCareStateChanged(i4);
                    break;
                case 5045:
                    absBYDAutoDiPilotListener.onGestureRecognitionStateChanged(i4);
                    break;
                case 5046:
                    absBYDAutoDiPilotListener.onLeftBehindReminderStateChanged(i4);
                    break;
                case 5047:
                    absBYDAutoDiPilotListener.onRequestHUNPClose(i4);
                    break;
                case 5048:
                    absBYDAutoDiPilotListener.onLaneAssistTypeChanged(i4);
                    break;
                case 5049:
                    absBYDAutoDiPilotListener.onVerifyModeChanged(i4);
                    break;
                case 5050:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitControlStateChanged(i4);
                    break;
                case 5051:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitAlertStateChanged(i4);
                    break;
                case 5052:
                    absBYDAutoDiPilotListener.onSpeedLimitRangeChanged(i4);
                    break;
                case 5053:
                    absBYDAutoDiPilotListener.onDriveDepartureStateChanged(i4);
                    break;
                case 5054:
                    absBYDAutoDiPilotListener.onDriveRemindStateChanged(i4);
                    break;
            }
        }
    }

    public void b(int i3, int i4) {
        if (this.f2753b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            this.f2753b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        this.f2752a = absBYDAutoDiPilotListener;
    }
}
