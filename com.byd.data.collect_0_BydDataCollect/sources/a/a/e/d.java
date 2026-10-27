package a.a.e;

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
    private AbsBYDAutoDiPilotListener f6a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f7b;

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
        this.f7b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener = this.f6a;
        if (absBYDAutoDiPilotListener != null) {
            int i2 = message.what;
            int i3 = message.arg1;
            switch (i2) {
                case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                    absBYDAutoDiPilotListener.onDrivieRemindStateChanged(i3);
                    break;
                case 5001:
                    absBYDAutoDiPilotListener.onDrivieDepartureStateChanged(i3);
                    break;
                case 5002:
                    absBYDAutoDiPilotListener.onFrontCarStartStateChange(i3);
                    break;
                case 5003:
                    absBYDAutoDiPilotListener.onFaultHandlerStateChanged(i3);
                    break;
                case 5004:
                    absBYDAutoDiPilotListener.onChargerCoverStateChanged(i3);
                    break;
                case 5005:
                    absBYDAutoDiPilotListener.onAiTurnLightStateChanged(i3);
                    break;
                case 5006:
                    absBYDAutoDiPilotListener.onRainOpenFogLightStateChanged(i3);
                    break;
                case 5007:
                    absBYDAutoDiPilotListener.onHighSpeedOpenWindowStateChanged(i3);
                    break;
                case 5008:
                    absBYDAutoDiPilotListener.onDriveLevelChanged(i3);
                    break;
                case 5009:
                    absBYDAutoDiPilotListener.onHighSpeedAINavigationStateChanged(i3);
                    break;
                case 5010:
                    absBYDAutoDiPilotListener.onUrbanAIAssistanceStateChanged(i3);
                    break;
                case 5011:
                    absBYDAutoDiPilotListener.onE2EAIAssistanceStateChanged(i3);
                    break;
                case 5012:
                    absBYDAutoDiPilotListener.onInteractiveLaneChangeAssistanceStateChanged(i3);
                    break;
                case 5013:
                    absBYDAutoDiPilotListener.onAINavigationStateChanged(i3);
                    break;
                case 5015:
                    absBYDAutoDiPilotListener.onAlarmTypeChanged(i3);
                    break;
                case 5016:
                    absBYDAutoDiPilotListener.onTrafficSignRecognizeStateChanged(i3);
                    break;
                case 5017:
                    absBYDAutoDiPilotListener.onAiCareStateChanged(i3);
                    break;
                case 5019:
                    absBYDAutoDiPilotListener.onMistakeGasStateChanged(i3);
                    break;
                case 5020:
                    absBYDAutoDiPilotListener.onAiMatrixLightStateChanged(i3);
                    break;
                case 5021:
                    absBYDAutoDiPilotListener.onAiDistanceLightStateChanged(i3);
                    break;
                case 5022:
                    absBYDAutoDiPilotListener.onTrafficLightRecognizeStateChanged(i3);
                    break;
                case 5023:
                    absBYDAutoDiPilotListener.onEmergencyLaneAssistanceStateChanged(i3);
                    break;
                case 5024:
                    absBYDAutoDiPilotListener.onBlindMonitorStateChanged(i3);
                    break;
                case 5025:
                    absBYDAutoDiPilotListener.onOpenDoorAlertStateChanged(i3);
                    break;
                case 5026:
                    absBYDAutoDiPilotListener.onRearCrashAlertStateChanged(i3);
                    break;
                case 5027:
                    absBYDAutoDiPilotListener.onRearTrafficCrossAlertStateChanged(i3);
                    break;
                case 5028:
                    absBYDAutoDiPilotListener.onRearTrafficBrakeAlertStateChanged(i3);
                    break;
                case 5029:
                    absBYDAutoDiPilotListener.onAiEmergencyBrakeStateChanged(i3);
                    break;
                case 5030:
                    absBYDAutoDiPilotListener.onPredictCrashAlertModeChanged(i3);
                    break;
                case 5031:
                    absBYDAutoDiPilotListener.onFrontTrafficCrossAlertStateChanged(i3);
                    break;
                case 5032:
                    absBYDAutoDiPilotListener.onFrontTrafficBrakeAlertStateChanged(i3);
                    break;
                case 5033:
                    absBYDAutoDiPilotListener.onElecCarBodyStablySysStateChanged(i3);
                    break;
                case 5034:
                    absBYDAutoDiPilotListener.onAutoParkingStateChanged(i3);
                    break;
                case 5035:
                    absBYDAutoDiPilotListener.onSteepDescentSlowDownStateChanged(i3);
                    break;
                case 5036:
                    absBYDAutoDiPilotListener.onFatigueDetectionAidStateChanged(i3);
                    break;
                case 5037:
                    absBYDAutoDiPilotListener.onRelieveFatigueStateChanged(i3);
                    break;
                case 5038:
                    absBYDAutoDiPilotListener.onRecommendRestSpotStateChanged(i3);
                    break;
                case 5039:
                    absBYDAutoDiPilotListener.onTemperatureWhileFatigueChanged(i3);
                    break;
                case 5040:
                    absBYDAutoDiPilotListener.onDistractionDetectionAidStateChanged(i3);
                    break;
                case 5041:
                    absBYDAutoDiPilotListener.onAttentionDetectionStateChanged(i3);
                    break;
                case 5042:
                    absBYDAutoDiPilotListener.onDangerousActionDetectionStateChanged(i3);
                    break;
                case 5043:
                    absBYDAutoDiPilotListener.onGazePerceptionStateChanged(i3);
                    break;
                case 5044:
                    absBYDAutoDiPilotListener.onChildCareStateChanged(i3);
                    break;
                case 5045:
                    absBYDAutoDiPilotListener.onGestureRecognitionStateChanged(i3);
                    break;
                case 5046:
                    absBYDAutoDiPilotListener.onLeftBehindReminderStateChanged(i3);
                    break;
                case 5047:
                    absBYDAutoDiPilotListener.onRequestHUNPClose(i3);
                    break;
                case 5048:
                    absBYDAutoDiPilotListener.onLaneAssistTypeChanged(i3);
                    break;
                case 5049:
                    absBYDAutoDiPilotListener.onVerifyModeChanged(i3);
                    break;
                case 5050:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitControlStateChanged(i3);
                    break;
                case 5051:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitAlertStateChanged(i3);
                    break;
                case 5052:
                    absBYDAutoDiPilotListener.onSpeedLimitRangeChanged(i3);
                    break;
                case 5053:
                    absBYDAutoDiPilotListener.onDriveDepartureStateChanged(i3);
                    break;
                case 5054:
                    absBYDAutoDiPilotListener.onDriveRemindStateChanged(i3);
                    break;
            }
        }
    }

    public void b(int i2, int i3) {
        if (this.f7b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            this.f7b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        this.f6a = absBYDAutoDiPilotListener;
    }
}
