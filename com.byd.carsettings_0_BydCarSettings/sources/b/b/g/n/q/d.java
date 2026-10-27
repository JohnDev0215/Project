package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoDiPilotDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile d f1267a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static BYDAutoDiPilotDevice f1268b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1269c = -1;

    public static d x() {
        if (f1267a == null) {
            synchronized (d.class) {
                if (f1267a == null) {
                    f1267a = new d();
                }
            }
        }
        return f1267a;
    }

    public int A() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitControl2D5State = bYDAutoDiPilotDevice.getIntelligentSpeedLimitControl2D5State();
        BydLogHelper.logD("getIntelligentSpeedLimitControl2D5State " + intelligentSpeedLimitControl2D5State);
        return intelligentSpeedLimitControl2D5State;
    }

    public int A0(int i) {
        BydLogHelper.logD("setIntelligentSpeedLimitAlertState " + i);
        b.b.g.r.e.i(210056);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitAlertState = bYDAutoDiPilotDevice.setIntelligentSpeedLimitAlertState(i);
        BydLogHelper.logD("setIntelligentSpeedLimitAlertState result " + intelligentSpeedLimitAlertState);
        return intelligentSpeedLimitAlertState;
    }

    public int B() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitControl32FState = bYDAutoDiPilotDevice.getIntelligentSpeedLimitControl32FState();
        BydLogHelper.logD("getIntelligentSpeedLimitControl32FState " + intelligentSpeedLimitControl32FState);
        return intelligentSpeedLimitControl32FState;
    }

    public int B0(int i) {
        BydLogHelper.logD("setIntelligentSpeedLimitControlState " + i);
        b.b.g.r.e.i(210058);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitControlState = bYDAutoDiPilotDevice.setIntelligentSpeedLimitControlState(i);
        BydLogHelper.logD("setIntelligentSpeedLimitControlState reslut " + intelligentSpeedLimitControlState);
        return intelligentSpeedLimitControlState;
    }

    public int C() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int interactiveLaneChangeAssistanceState = bYDAutoDiPilotDevice.getInteractiveLaneChangeAssistanceState();
        BydLogHelper.logD("getInteractiveLaneChangeAssistanceState " + interactiveLaneChangeAssistanceState);
        return interactiveLaneChangeAssistanceState;
    }

    public int C0(int i) {
        BydLogHelper.logD("setInteractiveLaneChangeAssistanceState " + i);
        b.b.g.r.e.i(210012);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int interactiveLaneChangeAssistanceState = bYDAutoDiPilotDevice.setInteractiveLaneChangeAssistanceState(i);
        BydLogHelper.logD("setInteractiveLaneChangeAssistanceState result  " + interactiveLaneChangeAssistanceState);
        return interactiveLaneChangeAssistanceState;
    }

    public int D() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int iopState = bYDAutoDiPilotDevice.getIopState();
        BydLogHelper.logD("getIopState " + iopState);
        return iopState;
    }

    public int D0(int i) {
        BydLogHelper.logD("setIopState " + i);
        b.b.g.r.e.i(210110);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int iopState = bYDAutoDiPilotDevice.setIopState(i);
        BydLogHelper.logD("setIopState result  " + iopState);
        return iopState;
    }

    public int E() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int laneAssistType = bYDAutoDiPilotDevice.getLaneAssistType();
        BydLogHelper.logD("getLaneAssistType " + laneAssistType);
        return laneAssistType;
    }

    public void E0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return;
        }
        BydLogHelper.logD("setLDWStaticCalibration result = " + bYDAutoDiPilotDevice.setLDWStaticCalibration(1));
    }

    public int F() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int mistakeGasState = bYDAutoDiPilotDevice.getMistakeGasState();
        BydLogHelper.logD("getMistakeGasState " + mistakeGasState);
        return mistakeGasState;
    }

    public int F0(int i) {
        BydLogHelper.logD("setLaneAssistType " + i);
        b.b.g.r.e.i(210047);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int laneAssistType = bYDAutoDiPilotDevice.setLaneAssistType(i);
        BydLogHelper.logD("setLaneAssistType result  " + laneAssistType);
        return laneAssistType;
    }

    public boolean G() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        return (bYDAutoDiPilotDevice == null || bYDAutoDiPilotDevice.getMOISFuncStatus() == 3) ? false : true;
    }

    public int G0(int i) {
        BydLogHelper.logD("setMistakeGasState " + i);
        b.b.g.r.e.i(210018);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int mistakeGasState = bYDAutoDiPilotDevice.setMistakeGasState(i);
        BydLogHelper.logD("setMistakeGasState result  " + mistakeGasState);
        return mistakeGasState;
    }

    public int H() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice != null) {
            return bYDAutoDiPilotDevice.getMOISEnable();
        }
        return 0;
    }

    public void H0(int i) {
        b.b.g.r.e.i(210077);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice != null) {
            bYDAutoDiPilotDevice.setMOISFunctionSwitch(i);
        }
    }

    public int I() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int openDoorAlertState = bYDAutoDiPilotDevice.getOpenDoorAlertState();
        BydLogHelper.logD("getOpenDoorAlertState " + openDoorAlertState);
        return openDoorAlertState;
    }

    public int I0(int i) {
        BydLogHelper.logD("setOpenDoorAlertState " + i);
        b.b.g.r.e.i(210024);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int openDoorAlertState = bYDAutoDiPilotDevice.setOpenDoorAlertState(i);
        BydLogHelper.logD("setOpenDoorAlertState result  " + openDoorAlertState);
        return openDoorAlertState;
    }

    public int J() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlert138Mode = bYDAutoDiPilotDevice.getPredictCrashAlert138Mode();
        BydLogHelper.logD("getPredictCrashAlert138Mode " + predictCrashAlert138Mode);
        return predictCrashAlert138Mode;
    }

    public int J0(int i) {
        BydLogHelper.logD("setPredictCrashAlertMode " + i);
        b.b.g.r.e.i(210069);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlertMode = bYDAutoDiPilotDevice.setPredictCrashAlertMode(i);
        BydLogHelper.logD("setFatigueDetectionAidState result  " + predictCrashAlertMode);
        return predictCrashAlertMode;
    }

    public int K() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlert32DMode = bYDAutoDiPilotDevice.getPredictCrashAlert32DMode();
        BydLogHelper.logD("getPredictCrashAlert32DMode " + predictCrashAlert32DMode);
        return predictCrashAlert32DMode;
    }

    public int K0(int i) {
        BydLogHelper.logD("setRainOpenFogLightState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rainOpenFogLightState = bYDAutoDiPilotDevice.setRainOpenFogLightState(i);
        BydLogHelper.logD("setRainOpenFogLightState result  " + rainOpenFogLightState);
        return rainOpenFogLightState;
    }

    public int L() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlertState = bYDAutoDiPilotDevice.getPredictCrashAlertState();
        BydLogHelper.logD("getPredictCrashAlertState " + predictCrashAlertState);
        return predictCrashAlertState;
    }

    public int L0(int i) {
        BydLogHelper.logD("setRearCrashAlertState " + i);
        b.b.g.r.e.i(210025);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearCrashAlertState = bYDAutoDiPilotDevice.setRearCrashAlertState(i);
        BydLogHelper.logD("setRearCrashAlertState result  " + rearCrashAlertState);
        return rearCrashAlertState;
    }

    public int M() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearCrashAlertState = bYDAutoDiPilotDevice.getRearCrashAlertState();
        BydLogHelper.logD("getRearCrashAlertState " + rearCrashAlertState);
        return rearCrashAlertState;
    }

    public int M0(int i) {
        BydLogHelper.logD("setRearTrafficBrakeAlertState " + i);
        b.b.g.r.e.i(210027);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearTrafficBrakeAlertState = bYDAutoDiPilotDevice.setRearTrafficBrakeAlertState(i);
        BydLogHelper.logD("setRearTrafficBrakeAlertState result  " + rearTrafficBrakeAlertState);
        return rearTrafficBrakeAlertState;
    }

    public int N() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearTrafficBrakeAlertState = bYDAutoDiPilotDevice.getRearTrafficBrakeAlertState();
        BydLogHelper.logD("getRearTrafficBrakeAlertState " + rearTrafficBrakeAlertState);
        return rearTrafficBrakeAlertState;
    }

    public int N0(int i) {
        BydLogHelper.logD("setRearTrafficCrossAlertState " + i);
        b.b.g.r.e.i(210026);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearTrafficCrossAlertState = bYDAutoDiPilotDevice.setRearTrafficCrossAlertState(i);
        BydLogHelper.logD("setRearTrafficCrossAlertState result  " + rearTrafficCrossAlertState);
        return rearTrafficCrossAlertState;
    }

    public int O() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearTrafficCrossAlertState = bYDAutoDiPilotDevice.getRearTrafficCrossAlertState();
        BydLogHelper.logD("getRearTrafficCrossAlertState " + rearTrafficCrossAlertState);
        return rearTrafficCrossAlertState;
    }

    public void O0(int i) {
        if (f1268b == null) {
            return;
        }
        b.b.g.r.e.i(210112);
        BydLogHelper.logD("setSaocState result  " + f1268b.setSaocState(i));
    }

    public int P() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int saocState = bYDAutoDiPilotDevice.getSaocState();
        BydLogHelper.logD("getSaocState result  " + saocState);
        return saocState;
    }

    public void P0(int i) {
        if (f1268b == null) {
            return;
        }
        b.b.g.r.e.i(210111);
        BydLogHelper.logD("setSlifState result  " + f1268b.setSlifState(i));
    }

    public int Q() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int slifState = bYDAutoDiPilotDevice.getSlifState();
        BydLogHelper.logD("getSlifState result  " + slifState);
        return slifState;
    }

    public int Q0(int i) {
        BydLogHelper.logD("setSpeedLimitRange " + i);
        b.b.g.r.e.i(210054);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int speedLimitRange = bYDAutoDiPilotDevice.setSpeedLimitRange(i);
        BydLogHelper.logD("setSpeedLimitRange result " + speedLimitRange);
        return speedLimitRange;
    }

    public int R() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int speedLimitRange2B4 = bYDAutoDiPilotDevice.getSpeedLimitRange2B4();
        BydLogHelper.logD("getSpeedLimitRange2B4 " + speedLimitRange2B4);
        return speedLimitRange2B4;
    }

    public int R0(int i) {
        BydLogHelper.logD("setSteepDescentSlowDownState " + i);
        b.b.g.r.e.i(210034);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int steepDescentSlowDownState = bYDAutoDiPilotDevice.setSteepDescentSlowDownState(i);
        BydLogHelper.logD("setSteepDescentSlowDownState result  " + steepDescentSlowDownState);
        return steepDescentSlowDownState;
    }

    public int S() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int speedLimitRange2D5 = bYDAutoDiPilotDevice.getSpeedLimitRange2D5();
        BydLogHelper.logD("getSpeedLimitRange2D5 " + speedLimitRange2D5);
        return speedLimitRange2D5;
    }

    public void S0(boolean z) {
        if (f1268b == null) {
            return;
        }
        b.b.g.r.e.i(R.id.ll_speed_control_alarm_sensitivity_container);
        BydLogHelper.logD("setTSRLitSpdChgSwitchState result  " + f1268b.setTSRLitSpdChgSwitchState(z ? 2 : 1));
    }

    public int T() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int steepDescentSlowDownState = bYDAutoDiPilotDevice.getSteepDescentSlowDownState();
        BydLogHelper.logD("getSteepDescentSlowDownState " + steepDescentSlowDownState);
        return steepDescentSlowDownState;
    }

    public int T0(int i) {
        BydLogHelper.logD("setTrafficLightRecognizeState " + i);
        b.b.g.r.e.i(210065);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightRecognizeState = bYDAutoDiPilotDevice.setTrafficLightRecognizeState(i);
        BydLogHelper.logD("setTrafficLightRecognizeState result  " + trafficLightRecognizeState);
        return trafficLightRecognizeState;
    }

    public boolean U() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return false;
        }
        int tSRLitSpdChgSwitchState = bYDAutoDiPilotDevice.getTSRLitSpdChgSwitchState();
        BydLogHelper.logD("getTSRLitSpdChgSwitchState result  " + tSRLitSpdChgSwitchState);
        return tSRLitSpdChgSwitchState == 0;
    }

    public int U0(int i) {
        BydLogHelper.logD("setTrafficSignRecognizeState " + i);
        b.b.g.r.e.i(210015);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognizeState = bYDAutoDiPilotDevice.setTrafficSignRecognizeState(i);
        BydLogHelper.logD("setTrafficSignRecognizeState result  " + trafficSignRecognizeState);
        return trafficSignRecognizeState;
    }

    public int V() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightRecognize2B4State = bYDAutoDiPilotDevice.getTrafficLightRecognize2B4State();
        BydLogHelper.logD("getTrafficLightRecognize2B4State " + trafficLightRecognize2B4State);
        return trafficLightRecognize2B4State;
    }

    public int V0(int i) {
        BydLogHelper.logD("setUrbanAIAssistanceState " + i);
        b.b.g.r.e.i(210010);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int urbanAIAssistanceState = bYDAutoDiPilotDevice.setUrbanAIAssistanceState(i);
        BydLogHelper.logD("setUrbanAIAssistanceState result  " + urbanAIAssistanceState);
        return urbanAIAssistanceState;
    }

    public int W() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightRecognize2D5State = bYDAutoDiPilotDevice.getTrafficLightRecognize2D5State();
        BydLogHelper.logD("getTrafficLightRecognize2D5State " + trafficLightRecognize2D5State);
        return trafficLightRecognize2D5State;
    }

    public int W0(int i) {
        BydLogHelper.logD("setVerifyMode " + i);
        b.b.g.r.e.i(210051);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int verifyMode = bYDAutoDiPilotDevice.setVerifyMode(i);
        BydLogHelper.logD("setVerifyMode result " + verifyMode);
        return verifyMode;
    }

    public int X() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognize2B4State = bYDAutoDiPilotDevice.getTrafficSignRecognize2B4State();
        BydLogHelper.logD("getTrafficSignRecognize2B4State " + trafficSignRecognize2B4State);
        return trafficSignRecognize2B4State;
    }

    public void X0(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice != null) {
            bYDAutoDiPilotDevice.unregisterListener(absBYDAutoDiPilotListener);
        }
    }

    public int Y() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognize2D5State = bYDAutoDiPilotDevice.getTrafficSignRecognize2D5State();
        BydLogHelper.logD("getTrafficSignRecognize2D5State " + trafficSignRecognize2D5State);
        return trafficSignRecognize2D5State;
    }

    public int Z() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognize316State = bYDAutoDiPilotDevice.getTrafficSignRecognize316State();
        BydLogHelper.logD("getTrafficSignRecognize316State " + trafficSignRecognize316State);
        return trafficSignRecognize316State;
    }

    public int a(int i) {
        BydLogHelper.logD("changeComb " + i);
        return -1;
    }

    public int a0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int verifyMode = bYDAutoDiPilotDevice.getVerifyMode();
        BydLogHelper.logD("getVerifyMode " + verifyMode);
        return verifyMode;
    }

    public int b() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aINavigationState = bYDAutoDiPilotDevice.getAINavigationState();
        BydLogHelper.logD("getAINavigationState " + aINavigationState);
        return aINavigationState;
    }

    public void b0(Context context) {
        f1268b = BYDAutoDiPilotDevice.getInstance(context);
        BydLogHelper.logD("init  sDiPilotDevice:" + f1268b);
    }

    public int c() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aCCState = bYDAutoDiPilotDevice.getACCState();
        BydLogHelper.logD("getAccState " + aCCState);
        return aCCState;
    }

    public void c0(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        BydLogHelper.logD("init  sDiPilotDevice:" + f1268b);
        BydLogHelper.logD("init  listener:" + absBYDAutoDiPilotListener);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice != null) {
            bYDAutoDiPilotDevice.registerListener(absBYDAutoDiPilotListener);
        }
    }

    public int d() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiDistanceLight2B4State = bYDAutoDiPilotDevice.getAiDistanceLight2B4State();
        BydLogHelper.logD("getAiDistanceLight2B4State " + aiDistanceLight2B4State);
        return aiDistanceLight2B4State;
    }

    public int d0(int i) {
        BydLogHelper.logD("resetComb " + i);
        return -1;
    }

    public int e() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiDistanceLight316State = bYDAutoDiPilotDevice.getAiDistanceLight316State();
        BydLogHelper.logD("getAiDistanceLight316State " + aiDistanceLight316State);
        return aiDistanceLight316State;
    }

    public int e0(int i) {
        BydLogHelper.logD("setAINavigationState " + i);
        b.b.g.r.e.i(210013);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aINavigationState = bYDAutoDiPilotDevice.setAINavigationState(i);
        BydLogHelper.logD("setAINavigationState result  " + aINavigationState);
        return aINavigationState;
    }

    public int f() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiEmergencyBrakeState = bYDAutoDiPilotDevice.getAiEmergencyBrakeState();
        BydLogHelper.logD("getAiEmergencyBrakeState " + aiEmergencyBrakeState);
        return aiEmergencyBrakeState;
    }

    public int f0(int i) {
        BydLogHelper.logD("setAccState " + i);
        b.b.g.r.e.i(210113);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aCCState = bYDAutoDiPilotDevice.setACCState(i);
        BydLogHelper.logD("setAccState result  " + aCCState);
        return aCCState;
    }

    public int g() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiMatrixLight3ACState = bYDAutoDiPilotDevice.getAiMatrixLight3ACState();
        BydLogHelper.logD("getAiMatrixLight3ACState " + aiMatrixLight3ACState);
        return aiMatrixLight3ACState;
    }

    public int g0(int i) {
        BydLogHelper.logD("setAiDistanceLightState " + i);
        b.b.g.r.e.i(210020);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiDistanceLightState = bYDAutoDiPilotDevice.setAiDistanceLightState(i);
        BydLogHelper.logD("setAiDistanceLightState result  " + aiDistanceLightState);
        return aiDistanceLightState;
    }

    public int h() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiMatrixLight3ADState = bYDAutoDiPilotDevice.getAiMatrixLight3ADState();
        BydLogHelper.logD("getAiMatrixLight3ADState " + aiMatrixLight3ADState);
        return aiMatrixLight3ADState;
    }

    public int h0(int i) {
        BydLogHelper.logD("setAiEmergencyBrakeState " + i);
        b.b.g.r.e.i(210028);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiEmergencyBrakeState = bYDAutoDiPilotDevice.setAiEmergencyBrakeState(i);
        BydLogHelper.logD("setAiEmergencyBrakeState result  " + aiEmergencyBrakeState);
        return aiEmergencyBrakeState;
    }

    public int i() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int alarmType = bYDAutoDiPilotDevice.getAlarmType();
        BydLogHelper.logD("getAlarmType " + alarmType);
        return alarmType;
    }

    public int i0(int i) {
        BydLogHelper.logD("setAiMatrixLightState " + i);
        b.b.g.r.e.i(210071);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiMatrixLightState = bYDAutoDiPilotDevice.setAiMatrixLightState(i);
        BydLogHelper.logD("setAiMatrixLightState result  " + aiMatrixLightState);
        return aiMatrixLightState;
    }

    public int j() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int autoParkingState = bYDAutoDiPilotDevice.getAutoParkingState();
        BydLogHelper.logD("getAutoParkingState " + autoParkingState);
        return autoParkingState;
    }

    public int j0(int i) {
        BydLogHelper.logD("setAiTurnLightState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiTurnLightState = bYDAutoDiPilotDevice.setAiTurnLightState(i);
        BydLogHelper.logD("setAiTurnLightState result  " + aiTurnLightState);
        return aiTurnLightState;
    }

    public int k() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int blindMonitorState1 = bYDAutoDiPilotDevice.getBlindMonitorState1();
        BydLogHelper.logD("getBlindMonitorState1 " + blindMonitorState1);
        return blindMonitorState1;
    }

    public int k0(int i) {
        BydLogHelper.logD("setAlarmType " + i);
        b.b.g.r.e.i(210014);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int alarmType = bYDAutoDiPilotDevice.setAlarmType(i);
        BydLogHelper.logD("setAlarmType result  " + alarmType);
        return alarmType;
    }

    public int l() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int blindMonitorState2 = bYDAutoDiPilotDevice.getBlindMonitorState2();
        BydLogHelper.logD("getBlindMonitorState2 " + blindMonitorState2);
        return blindMonitorState2;
    }

    public int l0(int i) {
        BydLogHelper.logD("setAutoParkingState " + i);
        b.b.g.r.e.i(210033);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int autoParkingState = bYDAutoDiPilotDevice.setAutoParkingState(i);
        BydLogHelper.logD("setAutoParkingState  result " + autoParkingState);
        return autoParkingState;
    }

    public boolean m() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        return (bYDAutoDiPilotDevice == null || bYDAutoDiPilotDevice.getBSISFuncStatus() == 3) ? false : true;
    }

    public int m0(int i) {
        BydLogHelper.logD("setBlindMonitorState " + i);
        b.b.g.r.e.i(210023);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int blindMonitorState = bYDAutoDiPilotDevice.setBlindMonitorState(i);
        BydLogHelper.logD("setBlindMonitorState result  " + blindMonitorState);
        return blindMonitorState;
    }

    public int n() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice != null) {
            return bYDAutoDiPilotDevice.getBSISEnable();
        }
        return 0;
    }

    public void n0(int i) {
        b.b.g.r.e.i(210076);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice != null) {
            bYDAutoDiPilotDevice.setBSISCollisionAlarmSwitch(i);
        }
    }

    public int o() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int cpdStatus = bYDAutoDiPilotDevice.getCpdStatus();
        BydLogHelper.logD("getCpdStatus " + cpdStatus);
        return cpdStatus;
    }

    public int o0(int i) {
        BydLogHelper.logD("setChargerCoverState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int chargerCoverState = bYDAutoDiPilotDevice.setChargerCoverState(i);
        BydLogHelper.logD("setChargerCoverState result  " + chargerCoverState);
        return chargerCoverState;
    }

    public BYDAutoDiPilotDevice p() {
        return f1268b;
    }

    public int p0(int i) {
        BydLogHelper.logD("setCpdStatus " + i);
        b.b.g.r.e.i(210075);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int cpdStatus = bYDAutoDiPilotDevice.setCpdStatus(i);
        BydLogHelper.logD("setCpdStatus result " + cpdStatus);
        return cpdStatus;
    }

    public int q() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int e2EAIAssistanceState = bYDAutoDiPilotDevice.getE2EAIAssistanceState();
        BydLogHelper.logD("getE2EAIAssistanceState " + e2EAIAssistanceState);
        return e2EAIAssistanceState;
    }

    public int q0(int i) {
        BydLogHelper.logD("setDriveDeparturState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        bYDAutoDiPilotDevice.setDrivieDeparturState(i);
        BydLogHelper.logD("setDriveDeparturState result  " + i);
        return i;
    }

    public int r() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int elecCarBodyStablySysState = bYDAutoDiPilotDevice.getElecCarBodyStablySysState();
        BydLogHelper.logD("getElecCarBodyStablySysState " + elecCarBodyStablySysState);
        return elecCarBodyStablySysState;
    }

    public int r0(int i) {
        BydLogHelper.logD("setE2EAIAssistanceState " + i);
        b.b.g.r.e.i(210011);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int e2EAIAssistanceState = bYDAutoDiPilotDevice.setE2EAIAssistanceState(i);
        BydLogHelper.logD("setE2EAIAssistanceState result  " + e2EAIAssistanceState);
        return e2EAIAssistanceState;
    }

    public int s() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyLaneAssistance2F0State = bYDAutoDiPilotDevice.getEmergencyLaneAssistance2F0State();
        BydLogHelper.logD("getEmergencyLaneAssistance2F0State " + emergencyLaneAssistance2F0State);
        return emergencyLaneAssistance2F0State;
    }

    public int s0(int i) {
        BydLogHelper.logD("setElecCarBodyStablySysState " + i);
        b.b.g.r.e.i(210032);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int elecCarBodyStablySysState = bYDAutoDiPilotDevice.setElecCarBodyStablySysState(i);
        BydLogHelper.logD("setElecCarBodyStablySysState result  " + elecCarBodyStablySysState);
        return elecCarBodyStablySysState;
    }

    public int t() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyLaneAssistance32FState = bYDAutoDiPilotDevice.getEmergencyLaneAssistance32FState();
        BydLogHelper.logD("getEmergencyLaneAssistance32FState " + emergencyLaneAssistance32FState);
        return emergencyLaneAssistance32FState;
    }

    public int t0(int i) {
        BydLogHelper.logD("setEmergencyLaneAssistanceState " + i);
        b.b.g.r.e.i(210067);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyLaneAssistanceState = bYDAutoDiPilotDevice.setEmergencyLaneAssistanceState(i);
        BydLogHelper.logD("setEmergencyLaneAssistanceState result  " + emergencyLaneAssistanceState);
        return emergencyLaneAssistanceState;
    }

    public int u() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontTrafficBrakeAlertState = bYDAutoDiPilotDevice.getFrontTrafficBrakeAlertState();
        BydLogHelper.logD("getFrontTrafficBrakeAlertState " + frontTrafficBrakeAlertState);
        return frontTrafficBrakeAlertState;
    }

    public int u0(int i) {
        BydLogHelper.logD("setFaultHandlerState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int faultHandlerState = bYDAutoDiPilotDevice.setFaultHandlerState(i);
        BydLogHelper.logD("setFaultHandlerState result  " + faultHandlerState);
        return faultHandlerState;
    }

    public int v() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontTrafficCrossAlertState = bYDAutoDiPilotDevice.getFrontTrafficCrossAlertState();
        BydLogHelper.logD("getFrontTrafficCrossAlertState " + frontTrafficCrossAlertState);
        return frontTrafficCrossAlertState;
    }

    public int v0(int i) {
        BydLogHelper.logD("setFrontCarStartState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontCarStartState = bYDAutoDiPilotDevice.setFrontCarStartState(i);
        BydLogHelper.logD("setFrontCarStartState result  " + frontCarStartState);
        return frontCarStartState;
    }

    public int w() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int highSpeedAINavigationState = bYDAutoDiPilotDevice.getHighSpeedAINavigationState();
        BydLogHelper.logD("getHighSpeedAINavigationState " + highSpeedAINavigationState);
        return highSpeedAINavigationState;
    }

    public int w0(int i) {
        BydLogHelper.logD("setFrontTrafficBrakeAlertState " + i);
        b.b.g.r.e.i(210031);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontTrafficBrakeAlertState = bYDAutoDiPilotDevice.setFrontTrafficBrakeAlertState(i);
        BydLogHelper.logD("setFrontTrafficBrakeAlertState result  " + frontTrafficBrakeAlertState);
        return frontTrafficBrakeAlertState;
    }

    public int x0(int i) {
        BydLogHelper.logD("setFrontTrafficCrossAlertState " + i);
        b.b.g.r.e.i(210030);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontTrafficCrossAlertState = bYDAutoDiPilotDevice.setFrontTrafficCrossAlertState(i);
        BydLogHelper.logD("setFrontTrafficCrossAlertState result  " + frontTrafficCrossAlertState);
        return frontTrafficCrossAlertState;
    }

    public int y() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitAlert2B4State = bYDAutoDiPilotDevice.getIntelligentSpeedLimitAlert2B4State();
        BydLogHelper.logD("getIntelligentSpeedLimitAlert2B4State " + intelligentSpeedLimitAlert2B4State);
        return intelligentSpeedLimitAlert2B4State;
    }

    public int y0(int i) {
        BydLogHelper.logD("setHighSpeedAINavigationState " + i);
        b.b.g.r.e.i(210009);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int highSpeedAINavigationState = bYDAutoDiPilotDevice.setHighSpeedAINavigationState(i);
        BydLogHelper.logD("setHighSpeedAINavigationState result  " + highSpeedAINavigationState);
        return highSpeedAINavigationState;
    }

    public int z() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitAlert2D5State = bYDAutoDiPilotDevice.getIntelligentSpeedLimitAlert2D5State();
        BydLogHelper.logD("getIntelligentSpeedLimitAlert2D5State " + intelligentSpeedLimitAlert2D5State);
        return intelligentSpeedLimitAlert2D5State;
    }

    public int z0(int i) {
        BydLogHelper.logD("setHighSpeedOpenWindowState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f1268b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int highSpeedOpenWindowState = bYDAutoDiPilotDevice.setHighSpeedOpenWindowState(i);
        BydLogHelper.logD("setHighSpeedOpenWindowState result  " + highSpeedOpenWindowState);
        return highSpeedOpenWindowState;
    }
}
