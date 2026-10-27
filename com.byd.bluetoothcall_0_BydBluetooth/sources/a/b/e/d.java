package a.b.e;

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
    private AbsBYDAutoDiPilotListener f8a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f9b;

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
        this.f9b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener = this.f8a;
        if (absBYDAutoDiPilotListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            switch (i) {
                case TsNormalNum.THOUSAND_FIVE /* 5000 */:
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
                case 5005:
                    absBYDAutoDiPilotListener.onAiTurnLightStateChanged(i2);
                    break;
                case 5006:
                    absBYDAutoDiPilotListener.onRainOpenFogLightStateChanged(i2);
                    break;
                case 5007:
                    absBYDAutoDiPilotListener.onHighSpeedOpenWindowStateChanged(i2);
                    break;
                case 5008:
                    absBYDAutoDiPilotListener.onDriveLevelChanged(i2);
                    break;
                case 5009:
                    absBYDAutoDiPilotListener.onHighSpeedAINavigationStateChanged(i2);
                    break;
                case 5010:
                    absBYDAutoDiPilotListener.onUrbanAIAssistanceStateChanged(i2);
                    break;
                case 5011:
                    absBYDAutoDiPilotListener.onE2EAIAssistanceStateChanged(i2);
                    break;
                case 5012:
                    absBYDAutoDiPilotListener.onInteractiveLaneChangeAssistanceStateChanged(i2);
                    break;
                case 5013:
                    absBYDAutoDiPilotListener.onAINavigationStateChanged(i2);
                    break;
                case 5015:
                    absBYDAutoDiPilotListener.onAlarmTypeChanged(i2);
                    break;
                case 5016:
                    absBYDAutoDiPilotListener.onTrafficSignRecognizeStateChanged(i2);
                    break;
                case 5017:
                    absBYDAutoDiPilotListener.onAiCareStateChanged(i2);
                    break;
                case 5019:
                    absBYDAutoDiPilotListener.onMistakeGasStateChanged(i2);
                    break;
                case 5020:
                    absBYDAutoDiPilotListener.onAiMatrixLightStateChanged(i2);
                    break;
                case 5021:
                    absBYDAutoDiPilotListener.onAiDistanceLightStateChanged(i2);
                    break;
                case 5022:
                    absBYDAutoDiPilotListener.onTrafficLightRecognizeStateChanged(i2);
                    break;
                case 5023:
                    absBYDAutoDiPilotListener.onEmergencyLaneAssistanceStateChanged(i2);
                    break;
                case 5024:
                    absBYDAutoDiPilotListener.onBlindMonitorStateChanged(i2);
                    break;
                case 5025:
                    absBYDAutoDiPilotListener.onOpenDoorAlertStateChanged(i2);
                    break;
                case 5026:
                    absBYDAutoDiPilotListener.onRearCrashAlertStateChanged(i2);
                    break;
                case 5027:
                    absBYDAutoDiPilotListener.onRearTrafficCrossAlertStateChanged(i2);
                    break;
                case 5028:
                    absBYDAutoDiPilotListener.onRearTrafficBrakeAlertStateChanged(i2);
                    break;
                case 5029:
                    absBYDAutoDiPilotListener.onAiEmergencyBrakeStateChanged(i2);
                    break;
                case 5030:
                    absBYDAutoDiPilotListener.onPredictCrashAlertModeChanged(i2);
                    break;
                case 5031:
                    absBYDAutoDiPilotListener.onFrontTrafficCrossAlertStateChanged(i2);
                    break;
                case 5032:
                    absBYDAutoDiPilotListener.onFrontTrafficBrakeAlertStateChanged(i2);
                    break;
                case 5033:
                    absBYDAutoDiPilotListener.onElecCarBodyStablySysStateChanged(i2);
                    break;
                case 5034:
                    absBYDAutoDiPilotListener.onAutoParkingStateChanged(i2);
                    break;
                case 5035:
                    absBYDAutoDiPilotListener.onSteepDescentSlowDownStateChanged(i2);
                    break;
                case 5036:
                    absBYDAutoDiPilotListener.onFatigueDetectionAidStateChanged(i2);
                    break;
                case 5037:
                    absBYDAutoDiPilotListener.onRelieveFatigueStateChanged(i2);
                    break;
                case 5038:
                    absBYDAutoDiPilotListener.onRecommendRestSpotStateChanged(i2);
                    break;
                case 5039:
                    absBYDAutoDiPilotListener.onTemperatureWhileFatigueChanged(i2);
                    break;
                case 5040:
                    absBYDAutoDiPilotListener.onDistractionDetectionAidStateChanged(i2);
                    break;
                case 5041:
                    absBYDAutoDiPilotListener.onAttentionDetectionStateChanged(i2);
                    break;
                case 5042:
                    absBYDAutoDiPilotListener.onDangerousActionDetectionStateChanged(i2);
                    break;
                case 5043:
                    absBYDAutoDiPilotListener.onGazePerceptionStateChanged(i2);
                    break;
                case 5044:
                    absBYDAutoDiPilotListener.onChildCareStateChanged(i2);
                    break;
                case 5045:
                    absBYDAutoDiPilotListener.onGestureRecognitionStateChanged(i2);
                    break;
                case 5046:
                    absBYDAutoDiPilotListener.onLeftBehindReminderStateChanged(i2);
                    break;
                case 5047:
                    absBYDAutoDiPilotListener.onRequestHUNPClose(i2);
                    break;
                case 5048:
                    absBYDAutoDiPilotListener.onLaneAssistTypeChanged(i2);
                    break;
                case 5049:
                    absBYDAutoDiPilotListener.onVerifyModeChanged(i2);
                    break;
                case 5050:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitControlStateChanged(i2);
                    break;
                case 5051:
                    absBYDAutoDiPilotListener.onIntelligentSpeedLimitAlertStateChanged(i2);
                    break;
                case 5052:
                    absBYDAutoDiPilotListener.onSpeedLimitRangeChanged(i2);
                    break;
                case 5053:
                    absBYDAutoDiPilotListener.onDriveDepartureStateChanged(i2);
                    break;
                case 5054:
                    absBYDAutoDiPilotListener.onDriveRemindStateChanged(i2);
                    break;
            }
        }
    }

    public void b(int i, int i2) {
        if (this.f9b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.f9b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        this.f8a = absBYDAutoDiPilotListener;
    }
}
