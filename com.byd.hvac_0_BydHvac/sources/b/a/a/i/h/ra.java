package b.a.a.i.h;

import android.content.Context;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class ra {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile ra f736a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static BYDAutoDiPilotDevice f737b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f738c = -1;

    public static ra d0() {
        if (f736a == null) {
            synchronized (ra.class) {
                if (f736a == null) {
                    f736a = new ra();
                }
            }
        }
        return f736a;
    }

    public int A() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int dNPState = bYDAutoDiPilotDevice.getDNPState();
        BydLogHelper.logD("getDNPState " + dNPState);
        return dNPState;
    }

    public int A0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearTrafficBrakeAlertState = bYDAutoDiPilotDevice.getRearTrafficBrakeAlertState();
        BydLogHelper.logD("getRearTrafficBrakeAlertState " + rearTrafficBrakeAlertState);
        return rearTrafficBrakeAlertState;
    }

    public int A1(int i) {
        BydLogHelper.logD("setDoNotPassWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int doNotPassWarningState = bYDAutoDiPilotDevice.setDoNotPassWarningState(i);
        BydLogHelper.logD("setDoNotPassWarningState result " + doNotPassWarningState);
        return doNotPassWarningState;
    }

    public void A2(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice != null) {
            bYDAutoDiPilotDevice.unregisterListener(absBYDAutoDiPilotListener);
        }
    }

    public int B() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int dangerousActionDetectionState = bYDAutoDiPilotDevice.getDangerousActionDetectionState();
        BydLogHelper.logD("getDangerousActionDetectionState " + dangerousActionDetectionState);
        return dangerousActionDetectionState;
    }

    public int B0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearTrafficCrossAlertState = bYDAutoDiPilotDevice.getRearTrafficCrossAlertState();
        BydLogHelper.logD("getRearTrafficCrossAlertState " + rearTrafficCrossAlertState);
        return rearTrafficCrossAlertState;
    }

    public int B1(int i) {
        BydLogHelper.logD("setDriveDeparturState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        bYDAutoDiPilotDevice.setDrivieDeparturState(i);
        BydLogHelper.logD("setDriveDeparturState result  " + i);
        return i;
    }

    public int C() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int distractionDetectionAidState = bYDAutoDiPilotDevice.getDistractionDetectionAidState();
        BydLogHelper.logD("getDistractionDetectionAidState " + distractionDetectionAidState);
        return distractionDetectionAidState;
    }

    public int C0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int recommendRestSpotState = bYDAutoDiPilotDevice.getRecommendRestSpotState();
        BydLogHelper.logD("getRecommendRestSpotState " + recommendRestSpotState);
        return recommendRestSpotState;
    }

    public int C1(int i) {
        BydLogHelper.logD("setDriveLevel " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int driveLevel = bYDAutoDiPilotDevice.setDriveLevel(i);
        BydLogHelper.logD("setDriveLevel result  " + driveLevel);
        return driveLevel;
    }

    public int D() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int dmsSwitchStatus = bYDAutoDiPilotDevice.getDmsSwitchStatus();
        BydLogHelper.logD("getDmsSwitchStatus " + dmsSwitchStatus);
        return dmsSwitchStatus;
    }

    public int D0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int redLightViolationWarningState = bYDAutoDiPilotDevice.getRedLightViolationWarningState();
        BydLogHelper.logD("getRedLightViolationWarningState " + redLightViolationWarningState);
        return redLightViolationWarningState;
    }

    public int D1(int i) {
        BydLogHelper.logD("setDriveRemindState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int drivieRemindState = bYDAutoDiPilotDevice.setDrivieRemindState(i);
        BydLogHelper.logD("setDriveRemindState result  " + drivieRemindState);
        return drivieRemindState;
    }

    public int E() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int dnpStatus = bYDAutoDiPilotDevice.getDnpStatus();
        BydLogHelper.logD("getDnpStatus " + dnpStatus);
        return dnpStatus;
    }

    public int E0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int relieveFatigueState = bYDAutoDiPilotDevice.getRelieveFatigueState();
        BydLogHelper.logD("getRelieveFatigueState " + relieveFatigueState);
        return relieveFatigueState;
    }

    public int E1(int i) {
        BydLogHelper.logD("setE2EAIAssistanceState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int e2EAIAssistanceState = bYDAutoDiPilotDevice.setE2EAIAssistanceState(i);
        BydLogHelper.logD("setE2EAIAssistanceState result  " + e2EAIAssistanceState);
        return e2EAIAssistanceState;
    }

    public int F() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int doNotPassWarningState = bYDAutoDiPilotDevice.getDoNotPassWarningState();
        BydLogHelper.logD("getDoNotPassWarningState " + doNotPassWarningState);
        return doNotPassWarningState;
    }

    public int F0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int sideDistanceWarningState = bYDAutoDiPilotDevice.getSideDistanceWarningState();
        BydLogHelper.logD("getSideDistanceWarningState " + sideDistanceWarningState);
        return sideDistanceWarningState;
    }

    public int F1(int i) {
        BydLogHelper.logD("setElecCarBodyStablySysState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int elecCarBodyStablySysState = bYDAutoDiPilotDevice.setElecCarBodyStablySysState(i);
        BydLogHelper.logD("setElecCarBodyStablySysState result  " + elecCarBodyStablySysState);
        return elecCarBodyStablySysState;
    }

    public int G() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int drivieDepartureState = bYDAutoDiPilotDevice.getDrivieDepartureState();
        BydLogHelper.logD("getDriveDepartureState " + drivieDepartureState);
        return drivieDepartureState;
    }

    public int G0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int speedLimitRange = bYDAutoDiPilotDevice.getSpeedLimitRange();
        BydLogHelper.logD("getSpeedLimitRange " + speedLimitRange);
        return speedLimitRange;
    }

    public int G1(int i) {
        BydLogHelper.logD("setEmergencyBrakeWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyBrakeWarningState = bYDAutoDiPilotDevice.setEmergencyBrakeWarningState(i);
        BydLogHelper.logD("setEmergencyBrakeWarningState result " + emergencyBrakeWarningState);
        return emergencyBrakeWarningState;
    }

    public int H() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int driveLevel = bYDAutoDiPilotDevice.getDriveLevel();
        BydLogHelper.logD("getDriveLevel " + driveLevel);
        return driveLevel;
    }

    public int H0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int speedLimitRange2B4 = bYDAutoDiPilotDevice.getSpeedLimitRange2B4();
        BydLogHelper.logD("getSpeedLimitRange2B4 " + speedLimitRange2B4);
        return speedLimitRange2B4;
    }

    public int H1(int i) {
        BydLogHelper.logD("setEmergencyLaneAssistanceState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyLaneAssistanceState = bYDAutoDiPilotDevice.setEmergencyLaneAssistanceState(i);
        BydLogHelper.logD("setEmergencyLaneAssistanceState result  " + emergencyLaneAssistanceState);
        return emergencyLaneAssistanceState;
    }

    public int I() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int drivieRemindState = bYDAutoDiPilotDevice.getDrivieRemindState();
        BydLogHelper.logD("getDriveRemindState " + drivieRemindState);
        return drivieRemindState;
    }

    public int I0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int speedLimitRange2D5 = bYDAutoDiPilotDevice.getSpeedLimitRange2D5();
        BydLogHelper.logD("getSpeedLimitRange2D5 " + speedLimitRange2D5);
        return speedLimitRange2D5;
    }

    public int I1(int i) {
        BydLogHelper.logD("setEmergencyVehicleWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyVehicleWarningState = bYDAutoDiPilotDevice.setEmergencyVehicleWarningState(i);
        BydLogHelper.logD("setEmergencyVehicleWarningState result " + emergencyVehicleWarningState);
        return emergencyVehicleWarningState;
    }

    public int J() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int e2EAIAssistanceState = bYDAutoDiPilotDevice.getE2EAIAssistanceState();
        BydLogHelper.logD("getE2EAIAssistanceState " + e2EAIAssistanceState);
        return e2EAIAssistanceState;
    }

    public int J0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int steepDescentSlowDownState = bYDAutoDiPilotDevice.getSteepDescentSlowDownState();
        BydLogHelper.logD("getSteepDescentSlowDownState " + steepDescentSlowDownState);
        return steepDescentSlowDownState;
    }

    public int J1(int i) {
        BydLogHelper.logD("setFatigueDetectionAidState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int fatigueDetectionAidState = bYDAutoDiPilotDevice.setFatigueDetectionAidState(i);
        BydLogHelper.logD("setFatigueDetectionAidState result  " + fatigueDetectionAidState);
        return fatigueDetectionAidState;
    }

    public int K() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int eLKTakeOverState = bYDAutoDiPilotDevice.getELKTakeOverState();
        BydLogHelper.logD("getELKTakeOverState " + eLKTakeOverState);
        return eLKTakeOverState;
    }

    public int K0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int tSRLitSpdChgInformState = bYDAutoDiPilotDevice.getTSRLitSpdChgInformState();
        BydLogHelper.logD("getTSRLitSpdChgInformState " + tSRLitSpdChgInformState);
        return tSRLitSpdChgInformState;
    }

    public int K1(int i) {
        BydLogHelper.logD("setFatigueMonitoringStatus " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int fatigueMonitoringStatus = bYDAutoDiPilotDevice.setFatigueMonitoringStatus(i);
        BydLogHelper.logD("setFatigueMonitoringStatus result " + fatigueMonitoringStatus);
        return fatigueMonitoringStatus;
    }

    public int L() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int elecCarBodyStablySysState = bYDAutoDiPilotDevice.getElecCarBodyStablySysState();
        BydLogHelper.logD("getElecCarBodyStablySysState " + elecCarBodyStablySysState);
        return elecCarBodyStablySysState;
    }

    public int L0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int tSRLitSpdChgSwitchState = bYDAutoDiPilotDevice.getTSRLitSpdChgSwitchState();
        BydLogHelper.logD("getTSRLitSpdChgSwitchState " + tSRLitSpdChgSwitchState);
        return tSRLitSpdChgSwitchState;
    }

    public int L1(int i) {
        BydLogHelper.logD("setFatigueReliefSwitch " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int fatigueReliefSwitch = bYDAutoDiPilotDevice.setFatigueReliefSwitch(i);
        BydLogHelper.logD("setFatigueReliefSwitch result " + fatigueReliefSwitch);
        return fatigueReliefSwitch;
    }

    public int M() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyBrakeWarningState = bYDAutoDiPilotDevice.getEmergencyBrakeWarningState();
        BydLogHelper.logD("getEmergencyBrakeWarningState " + emergencyBrakeWarningState);
        return emergencyBrakeWarningState;
    }

    public int M0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int temperatureWhileFatigue = bYDAutoDiPilotDevice.getTemperatureWhileFatigue();
        BydLogHelper.logD("getTemperatureWhileFatigue " + temperatureWhileFatigue);
        return temperatureWhileFatigue;
    }

    public int M1(int i) {
        BydLogHelper.logD("setFaultHandlerState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int faultHandlerState = bYDAutoDiPilotDevice.setFaultHandlerState(i);
        BydLogHelper.logD("setFaultHandlerState result  " + faultHandlerState);
        return faultHandlerState;
    }

    public int N() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyLaneAssistance2F0State = bYDAutoDiPilotDevice.getEmergencyLaneAssistance2F0State();
        BydLogHelper.logD("getEmergencyLaneAssistance2F0State " + emergencyLaneAssistance2F0State);
        return emergencyLaneAssistance2F0State;
    }

    public int N0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficIncidentWarningState = bYDAutoDiPilotDevice.getTrafficIncidentWarningState();
        BydLogHelper.logD("getTrafficIncidentWarningState " + trafficIncidentWarningState);
        return trafficIncidentWarningState;
    }

    public int N1(int i) {
        BydLogHelper.logD("setFrontCarStartState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontCarStartState = bYDAutoDiPilotDevice.setFrontCarStartState(i);
        BydLogHelper.logD("setFrontCarStartState result  " + frontCarStartState);
        return frontCarStartState;
    }

    public int O() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyLaneAssistance32FState = bYDAutoDiPilotDevice.getEmergencyLaneAssistance32FState();
        BydLogHelper.logD("getEmergencyLaneAssistance32FState " + emergencyLaneAssistance32FState);
        return emergencyLaneAssistance32FState;
    }

    public int O0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficJamWarningState = bYDAutoDiPilotDevice.getTrafficJamWarningState();
        BydLogHelper.logD("getTrafficJamWarningState " + trafficJamWarningState);
        return trafficJamWarningState;
    }

    public int O1(int i) {
        BydLogHelper.logD("setFrontTrafficBrakeAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontTrafficBrakeAlertState = bYDAutoDiPilotDevice.setFrontTrafficBrakeAlertState(i);
        BydLogHelper.logD("setFrontTrafficBrakeAlertState result  " + frontTrafficBrakeAlertState);
        return frontTrafficBrakeAlertState;
    }

    public int P() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyLaneAssistanceState = bYDAutoDiPilotDevice.getEmergencyLaneAssistanceState();
        BydLogHelper.logD("getEmergencyLaneAssistanceState " + emergencyLaneAssistanceState);
        return emergencyLaneAssistanceState;
    }

    public int P0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightInfoPushState = bYDAutoDiPilotDevice.getTrafficLightInfoPushState();
        BydLogHelper.logD("getTrafficLightInfoPushState " + trafficLightInfoPushState);
        return trafficLightInfoPushState;
    }

    public int P1(int i) {
        BydLogHelper.logD("setFrontTrafficCrossAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontTrafficCrossAlertState = bYDAutoDiPilotDevice.setFrontTrafficCrossAlertState(i);
        BydLogHelper.logD("setFrontTrafficCrossAlertState result  " + frontTrafficCrossAlertState);
        return frontTrafficCrossAlertState;
    }

    public int Q() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int emergencyVehicleWarningState = bYDAutoDiPilotDevice.getEmergencyVehicleWarningState();
        BydLogHelper.logD("getEmergencyVehicleWarningState " + emergencyVehicleWarningState);
        return emergencyVehicleWarningState;
    }

    public int Q0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightRecognize2B4State = bYDAutoDiPilotDevice.getTrafficLightRecognize2B4State();
        BydLogHelper.logD("getTrafficLightRecognize2B4State " + trafficLightRecognize2B4State);
        return trafficLightRecognize2B4State;
    }

    public int Q1(int i) {
        BydLogHelper.logD("setGazePerceptionState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int gazePerceptionState = bYDAutoDiPilotDevice.setGazePerceptionState(i);
        BydLogHelper.logD("setGazePerceptionState result  " + gazePerceptionState);
        return gazePerceptionState;
    }

    public int R() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int fCWFaultState = bYDAutoDiPilotDevice.getFCWFaultState();
        BydLogHelper.logD("getFCWFaultState " + fCWFaultState);
        return fCWFaultState;
    }

    public int R0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightRecognize2D5State = bYDAutoDiPilotDevice.getTrafficLightRecognize2D5State();
        BydLogHelper.logD("getTrafficLightRecognize2D5State " + trafficLightRecognize2D5State);
        return trafficLightRecognize2D5State;
    }

    public int R1(int i) {
        BydLogHelper.logD("setGestureRecognitionState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int gestureRecognitionState = bYDAutoDiPilotDevice.setGestureRecognitionState(i);
        BydLogHelper.logD("setGestureRecognitionState result  " + gestureRecognitionState);
        return gestureRecognitionState;
    }

    public int S() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int fatigueDetectionAidState = bYDAutoDiPilotDevice.getFatigueDetectionAidState();
        BydLogHelper.logD("getFatigueDetectionAidState " + fatigueDetectionAidState);
        return fatigueDetectionAidState;
    }

    public int S0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightRecognizeState = bYDAutoDiPilotDevice.getTrafficLightRecognizeState();
        BydLogHelper.logD("getTrafficLightRecognizeState " + trafficLightRecognizeState);
        return trafficLightRecognizeState;
    }

    public int S1(int i) {
        BydLogHelper.logD("setGreenLightOptimalSpeedAdvisoryState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int greenLightOptimalSpeedAdvisoryState = bYDAutoDiPilotDevice.setGreenLightOptimalSpeedAdvisoryState(i);
        BydLogHelper.logD("setGreenLightOptimalSpeedAdvisoryState result " + greenLightOptimalSpeedAdvisoryState);
        return greenLightOptimalSpeedAdvisoryState;
    }

    public int T() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int faultHandlerState = bYDAutoDiPilotDevice.getFaultHandlerState();
        BydLogHelper.logD("getFaultHandlerState " + faultHandlerState);
        return faultHandlerState;
    }

    public int T0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognize2B4State = bYDAutoDiPilotDevice.getTrafficSignRecognize2B4State();
        BydLogHelper.logD("getTrafficSignRecognize2B4State " + trafficSignRecognize2B4State);
        return trafficSignRecognize2B4State;
    }

    public int T1(int i) {
        BydLogHelper.logD("setHighSpeedAINavigationState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int highSpeedAINavigationState = bYDAutoDiPilotDevice.setHighSpeedAINavigationState(i);
        BydLogHelper.logD("setHighSpeedAINavigationState result  " + highSpeedAINavigationState);
        return highSpeedAINavigationState;
    }

    public int U() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontCarStartState = bYDAutoDiPilotDevice.getFrontCarStartState();
        BydLogHelper.logD("getFrontCarStartState " + frontCarStartState);
        return frontCarStartState;
    }

    public int U0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognize2D5State = bYDAutoDiPilotDevice.getTrafficSignRecognize2D5State();
        BydLogHelper.logD("getTrafficSignRecognize2D5State " + trafficSignRecognize2D5State);
        return trafficSignRecognize2D5State;
    }

    public int U1(int i) {
        BydLogHelper.logD("setHighSpeedOpenWindowState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int highSpeedOpenWindowState = bYDAutoDiPilotDevice.setHighSpeedOpenWindowState(i);
        BydLogHelper.logD("setHighSpeedOpenWindowState result  " + highSpeedOpenWindowState);
        return highSpeedOpenWindowState;
    }

    public int V() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontTrafficBrakeAlertState = bYDAutoDiPilotDevice.getFrontTrafficBrakeAlertState();
        BydLogHelper.logD("getFrontTrafficBrakeAlertState " + frontTrafficBrakeAlertState);
        return frontTrafficBrakeAlertState;
    }

    public int V0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognize316State = bYDAutoDiPilotDevice.getTrafficSignRecognize316State();
        BydLogHelper.logD("getTrafficSignRecognize316State " + trafficSignRecognize316State);
        return trafficSignRecognize316State;
    }

    public int V1(int i) {
        BydLogHelper.logD("setIntelligentSpeedLimitAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitAlertState = bYDAutoDiPilotDevice.setIntelligentSpeedLimitAlertState(i);
        BydLogHelper.logD("setIntelligentSpeedLimitAlertState result " + intelligentSpeedLimitAlertState);
        return intelligentSpeedLimitAlertState;
    }

    public int W() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int frontTrafficCrossAlertState = bYDAutoDiPilotDevice.getFrontTrafficCrossAlertState();
        BydLogHelper.logD("getFrontTrafficCrossAlertState " + frontTrafficCrossAlertState);
        return frontTrafficCrossAlertState;
    }

    public int W0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognizeState = bYDAutoDiPilotDevice.getTrafficSignRecognizeState();
        BydLogHelper.logD("getTrafficSignRecognizeState " + trafficSignRecognizeState);
        return trafficSignRecognizeState;
    }

    public int W1(int i) {
        BydLogHelper.logD("setIntelligentSpeedLimitControlState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitControlState = bYDAutoDiPilotDevice.setIntelligentSpeedLimitControlState(i);
        BydLogHelper.logD("setIntelligentSpeedLimitControlState reslut " + intelligentSpeedLimitControlState);
        return intelligentSpeedLimitControlState;
    }

    public int X() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int gazePerceptionState = bYDAutoDiPilotDevice.getGazePerceptionState();
        BydLogHelper.logD("getGazePerceptionState " + gazePerceptionState);
        return gazePerceptionState;
    }

    public int X0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignalWarningState = bYDAutoDiPilotDevice.getTrafficSignalWarningState();
        BydLogHelper.logD("getTrafficSignalWarningState " + trafficSignalWarningState);
        return trafficSignalWarningState;
    }

    public int X1(int i) {
        BydLogHelper.logD("setInteractiveLaneChangeAssistanceState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int interactiveLaneChangeAssistanceState = bYDAutoDiPilotDevice.setInteractiveLaneChangeAssistanceState(i);
        BydLogHelper.logD("setInteractiveLaneChangeAssistanceState result  " + interactiveLaneChangeAssistanceState);
        return interactiveLaneChangeAssistanceState;
    }

    public int Y() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int gestureRecognitionState = bYDAutoDiPilotDevice.getGestureRecognitionState();
        BydLogHelper.logD("getGestureRecognitionState " + gestureRecognitionState);
        return gestureRecognitionState;
    }

    public int Y0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int urbanAIAssistanceState = bYDAutoDiPilotDevice.getUrbanAIAssistanceState();
        BydLogHelper.logD("getUrbanAIAssistanceState " + urbanAIAssistanceState);
        return urbanAIAssistanceState;
    }

    public int Y1(int i) {
        BydLogHelper.logD("setIntersectionCollisionAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intersectionCollisionAlertState = bYDAutoDiPilotDevice.setIntersectionCollisionAlertState(i);
        BydLogHelper.logD("setIntersectionCollisionAlertState result " + intersectionCollisionAlertState);
        return intersectionCollisionAlertState;
    }

    public int Z() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int greenLightOptimalSpeedAdvisoryState = bYDAutoDiPilotDevice.getGreenLightOptimalSpeedAdvisoryState();
        BydLogHelper.logD("getGreenLightOptimalSpeedAdvisoryState " + greenLightOptimalSpeedAdvisoryState);
        return greenLightOptimalSpeedAdvisoryState;
    }

    public int Z0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int vRUFaultState = bYDAutoDiPilotDevice.getVRUFaultState();
        BydLogHelper.logD("getVRUFaultState " + vRUFaultState);
        return vRUFaultState;
    }

    public int Z1(int i) {
        BydLogHelper.logD("setLaneAssistType " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int laneAssistType = bYDAutoDiPilotDevice.setLaneAssistType(i);
        BydLogHelper.logD("setLaneAssistType result  " + laneAssistType);
        return laneAssistType;
    }

    public int a() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aDASOnlineState = bYDAutoDiPilotDevice.getADASOnlineState();
        BydLogHelper.logD("getADASOnlineState " + aDASOnlineState);
        return aDASOnlineState;
    }

    public int a0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int highSpeedAINavigationState = bYDAutoDiPilotDevice.getHighSpeedAINavigationState();
        BydLogHelper.logD("getHighSpeedAINavigationState " + highSpeedAINavigationState);
        return highSpeedAINavigationState;
    }

    public int a1() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int verifyMode = bYDAutoDiPilotDevice.getVerifyMode();
        BydLogHelper.logD("getVerifyMode " + verifyMode);
        return verifyMode;
    }

    public int a2(int i) {
        BydLogHelper.logD("setLeftBehindReminderState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int leftBehindReminderState = bYDAutoDiPilotDevice.setLeftBehindReminderState(i);
        BydLogHelper.logD("setLeftBehindReminderState result  " + leftBehindReminderState);
        return leftBehindReminderState;
    }

    public int b() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aDASSoftVersion = bYDAutoDiPilotDevice.getADASSoftVersion();
        BydLogHelper.logD("getADASSoftVersion " + aDASSoftVersion);
        return aDASSoftVersion;
    }

    public int b0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int highSpeedOpenWindowState = bYDAutoDiPilotDevice.getHighSpeedOpenWindowState();
        BydLogHelper.logD("getHighSpeedOpenWindowState " + highSpeedOpenWindowState);
        return highSpeedOpenWindowState;
    }

    public void b1(Context context) {
        f737b = BYDAutoDiPilotDevice.getInstance(context);
        BydLogHelper.logD("init  sDiPilotDevice:" + f737b);
    }

    public int b2(int i) {
        BydLogHelper.logD("setLowSpeedEmergencyBrakingState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int lowSpeedEmergencyBrakingState = bYDAutoDiPilotDevice.setLowSpeedEmergencyBrakingState(i);
        BydLogHelper.logD("setLowSpeedEmergencyBrakingState result " + lowSpeedEmergencyBrakingState);
        return lowSpeedEmergencyBrakingState;
    }

    public int c() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aEBFaultState = bYDAutoDiPilotDevice.getAEBFaultState();
        BydLogHelper.logD("getAEBFaultState " + aEBFaultState);
        return aEBFaultState;
    }

    public int c0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int iSLIWarningState = bYDAutoDiPilotDevice.getISLIWarningState();
        BydLogHelper.logD("getISLIWarningState " + iSLIWarningState);
        return iSLIWarningState;
    }

    public void c1(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        BydLogHelper.logD("init  sDiPilotDevice:" + f737b);
        BydLogHelper.logD("init  listener:" + absBYDAutoDiPilotListener);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice != null) {
            bYDAutoDiPilotDevice.registerListener(absBYDAutoDiPilotListener);
        }
    }

    public int c2(int i) {
        BydLogHelper.logD("setMistakeGasState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int mistakeGasState = bYDAutoDiPilotDevice.setMistakeGasState(i);
        BydLogHelper.logD("setMistakeGasState result  " + mistakeGasState);
        return mistakeGasState;
    }

    public int d() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aINavigationState = bYDAutoDiPilotDevice.getAINavigationState();
        BydLogHelper.logD("getAINavigationState " + aINavigationState);
        return aINavigationState;
    }

    public int d1() {
        BydLogHelper.logD("requestHUNPClose ");
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int iRequestHUNPClose = bYDAutoDiPilotDevice.requestHUNPClose();
        BydLogHelper.logD("requestHUNPClose result  " + iRequestHUNPClose);
        return iRequestHUNPClose;
    }

    public int d2(int i) {
        BydLogHelper.logD("setOpenDoorAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int openDoorAlertState = bYDAutoDiPilotDevice.setOpenDoorAlertState(i);
        BydLogHelper.logD("setOpenDoorAlertState result  " + openDoorAlertState);
        return openDoorAlertState;
    }

    public int e() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int abnormalVehicleWarningState = bYDAutoDiPilotDevice.getAbnormalVehicleWarningState();
        BydLogHelper.logD("getAbnormalVehicleWarningState " + abnormalVehicleWarningState);
        return abnormalVehicleWarningState;
    }

    public int e0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitAlert2B4State = bYDAutoDiPilotDevice.getIntelligentSpeedLimitAlert2B4State();
        BydLogHelper.logD("getIntelligentSpeedLimitAlert2B4State " + intelligentSpeedLimitAlert2B4State);
        return intelligentSpeedLimitAlert2B4State;
    }

    public int e1(int i) {
        BydLogHelper.logD("setAINavigationState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aINavigationState = bYDAutoDiPilotDevice.setAINavigationState(i);
        BydLogHelper.logD("setAINavigationState result  " + aINavigationState);
        return aINavigationState;
    }

    public int e2(int i) {
        BydLogHelper.logD("setPredictCrashAlertMode " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlertMode = bYDAutoDiPilotDevice.setPredictCrashAlertMode(i);
        BydLogHelper.logD("setPredictCrashAlertMode result  " + predictCrashAlertMode);
        return predictCrashAlertMode;
    }

    public int f() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiCareState = bYDAutoDiPilotDevice.getAiCareState();
        BydLogHelper.logD("getAiCareState " + aiCareState);
        return aiCareState;
    }

    public int f0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitAlert2D5State = bYDAutoDiPilotDevice.getIntelligentSpeedLimitAlert2D5State();
        BydLogHelper.logD("getIntelligentSpeedLimitAlert2D5State " + intelligentSpeedLimitAlert2D5State);
        return intelligentSpeedLimitAlert2D5State;
    }

    public int f1(int i) {
        BydLogHelper.logD("setAbnormalVehicleWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int abnormalVehicleWarningState = bYDAutoDiPilotDevice.setAbnormalVehicleWarningState(i);
        BydLogHelper.logD("setAbnormalVehicleWarningState result " + abnormalVehicleWarningState);
        return abnormalVehicleWarningState;
    }

    public int f2(int i) {
        BydLogHelper.logD("setPredictCrashAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlertState = bYDAutoDiPilotDevice.setPredictCrashAlertState(i);
        BydLogHelper.logD("setPredictCrashAlertState result  " + predictCrashAlertState);
        return predictCrashAlertState;
    }

    public int g() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiDistanceLight2B4State = bYDAutoDiPilotDevice.getAiDistanceLight2B4State();
        BydLogHelper.logD("getAiDistanceLight2B4State " + aiDistanceLight2B4State);
        return aiDistanceLight2B4State;
    }

    public int g0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitAlertState = bYDAutoDiPilotDevice.getIntelligentSpeedLimitAlertState();
        BydLogHelper.logD("getIntelligentSpeedLimitAlertState " + intelligentSpeedLimitAlertState);
        return intelligentSpeedLimitAlertState;
    }

    public int g1(int i) {
        BydLogHelper.logD("setAiCareState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiCareState = bYDAutoDiPilotDevice.setAiCareState(i);
        BydLogHelper.logD("setAiCareState result  " + aiCareState);
        return aiCareState;
    }

    public int g2(int i) {
        BydLogHelper.logD("setRainOpenFogLightState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rainOpenFogLightState = bYDAutoDiPilotDevice.setRainOpenFogLightState(i);
        BydLogHelper.logD("setRainOpenFogLightState result  " + rainOpenFogLightState);
        return rainOpenFogLightState;
    }

    public int h() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiDistanceLight316State = bYDAutoDiPilotDevice.getAiDistanceLight316State();
        BydLogHelper.logD("getAiDistanceLight316State " + aiDistanceLight316State);
        return aiDistanceLight316State;
    }

    public int h0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitControl2D5State = bYDAutoDiPilotDevice.getIntelligentSpeedLimitControl2D5State();
        BydLogHelper.logD("getIntelligentSpeedLimitControl2D5State " + intelligentSpeedLimitControl2D5State);
        return intelligentSpeedLimitControl2D5State;
    }

    public int h1(int i) {
        BydLogHelper.logD("setAiDistanceLightState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiDistanceLightState = bYDAutoDiPilotDevice.setAiDistanceLightState(i);
        BydLogHelper.logD("setAiDistanceLightState result  " + aiDistanceLightState);
        return aiDistanceLightState;
    }

    public int h2(int i) {
        BydLogHelper.logD("setRearCrashAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearCrashAlertState = bYDAutoDiPilotDevice.setRearCrashAlertState(i);
        BydLogHelper.logD("setRearCrashAlertState result  " + rearCrashAlertState);
        return rearCrashAlertState;
    }

    public int i() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiDistanceLightState = bYDAutoDiPilotDevice.getAiDistanceLightState();
        BydLogHelper.logD("getAiDistanceLightState " + aiDistanceLightState);
        return aiDistanceLightState;
    }

    public int i0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitControl32FState = bYDAutoDiPilotDevice.getIntelligentSpeedLimitControl32FState();
        BydLogHelper.logD("getIntelligentSpeedLimitControl32FState " + intelligentSpeedLimitControl32FState);
        return intelligentSpeedLimitControl32FState;
    }

    public int i1(int i) {
        BydLogHelper.logD("setAiEmergencyBrakeState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiEmergencyBrakeState = bYDAutoDiPilotDevice.setAiEmergencyBrakeState(i);
        BydLogHelper.logD("setAiEmergencyBrakeState result  " + aiEmergencyBrakeState);
        return aiEmergencyBrakeState;
    }

    public int i2(int i) {
        BydLogHelper.logD("setRearTrafficBrakeAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearTrafficBrakeAlertState = bYDAutoDiPilotDevice.setRearTrafficBrakeAlertState(i);
        BydLogHelper.logD("setRearTrafficBrakeAlertState result  " + rearTrafficBrakeAlertState);
        return rearTrafficBrakeAlertState;
    }

    public int j() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiEmergencyBrakeState = bYDAutoDiPilotDevice.getAiEmergencyBrakeState();
        BydLogHelper.logD("getAiEmergencyBrakeState " + aiEmergencyBrakeState);
        return aiEmergencyBrakeState;
    }

    public int j0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intelligentSpeedLimitControlState = bYDAutoDiPilotDevice.getIntelligentSpeedLimitControlState();
        BydLogHelper.logD("getIntelligentSpeedLimitControlState " + intelligentSpeedLimitControlState);
        return intelligentSpeedLimitControlState;
    }

    public int j1(int i) {
        BydLogHelper.logD("setAiMatrixLightState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiMatrixLightState = bYDAutoDiPilotDevice.setAiMatrixLightState(i);
        BydLogHelper.logD("setAiMatrixLightState result  " + aiMatrixLightState);
        return aiMatrixLightState;
    }

    public int j2(int i) {
        BydLogHelper.logD("setRearTrafficCrossAlertState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearTrafficCrossAlertState = bYDAutoDiPilotDevice.setRearTrafficCrossAlertState(i);
        BydLogHelper.logD("setRearTrafficCrossAlertState result  " + rearTrafficCrossAlertState);
        return rearTrafficCrossAlertState;
    }

    public int k() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiMatrixLight3ACState = bYDAutoDiPilotDevice.getAiMatrixLight3ACState();
        BydLogHelper.logD("getAiMatrixLight3ACState " + aiMatrixLight3ACState);
        return aiMatrixLight3ACState;
    }

    public int k0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int interactiveLaneChangeAssistanceState = bYDAutoDiPilotDevice.getInteractiveLaneChangeAssistanceState();
        BydLogHelper.logD("getInteractiveLaneChangeAssistanceState " + interactiveLaneChangeAssistanceState);
        return interactiveLaneChangeAssistanceState;
    }

    public int k1(int i) {
        BydLogHelper.logD("setAiTurnLightState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiTurnLightState = bYDAutoDiPilotDevice.setAiTurnLightState(i);
        BydLogHelper.logD("setAiTurnLightState result  " + aiTurnLightState);
        return aiTurnLightState;
    }

    public int k2(int i) {
        BydLogHelper.logD("setRecommendRestSpotState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int recommendRestSpotState = bYDAutoDiPilotDevice.setRecommendRestSpotState(i);
        BydLogHelper.logD("setRecommendRestSpotState result  " + recommendRestSpotState);
        return recommendRestSpotState;
    }

    public int l() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiMatrixLight3ADState = bYDAutoDiPilotDevice.getAiMatrixLight3ADState();
        BydLogHelper.logD("getAiMatrixLight3ADState " + aiMatrixLight3ADState);
        return aiMatrixLight3ADState;
    }

    public int l0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int intersectionCollisionAlertState = bYDAutoDiPilotDevice.getIntersectionCollisionAlertState();
        BydLogHelper.logD("getIntersectionCollisionAlertState " + intersectionCollisionAlertState);
        return intersectionCollisionAlertState;
    }

    public int l1(int i) {
        BydLogHelper.logD("setAlarmType " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int alarmType = bYDAutoDiPilotDevice.setAlarmType(i);
        BydLogHelper.logD("setAlarmType result  " + alarmType);
        return alarmType;
    }

    public int l2(int i) {
        BydLogHelper.logD("setRedLightViolationWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int redLightViolationWarningState = bYDAutoDiPilotDevice.setRedLightViolationWarningState(i);
        BydLogHelper.logD("setRedLightViolationWarningState result " + redLightViolationWarningState);
        return redLightViolationWarningState;
    }

    public int m() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiMatrixLightState = bYDAutoDiPilotDevice.getAiMatrixLightState();
        BydLogHelper.logD("getAiMatrixLightState " + aiMatrixLightState);
        return aiMatrixLightState;
    }

    public int m0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int lDWFaultState = bYDAutoDiPilotDevice.getLDWFaultState();
        BydLogHelper.logD("getLDWFaultState " + lDWFaultState);
        return lDWFaultState;
    }

    public int m1(int i) {
        BydLogHelper.logD("setAttentionDetectionState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int attentionDetectionState = bYDAutoDiPilotDevice.setAttentionDetectionState(i);
        BydLogHelper.logD("setAttentionDetectionState result  " + attentionDetectionState);
        return attentionDetectionState;
    }

    public int m2(int i) {
        BydLogHelper.logD("setRelieveFatigueState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int relieveFatigueState = bYDAutoDiPilotDevice.setRelieveFatigueState(i);
        BydLogHelper.logD("setRelieveFatigueState result  " + relieveFatigueState);
        return relieveFatigueState;
    }

    public int n() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int aiTurnLightState = bYDAutoDiPilotDevice.getAiTurnLightState();
        BydLogHelper.logD("getAiTurnLightState " + aiTurnLightState);
        return aiTurnLightState;
    }

    public int n0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int lKSFaultState = bYDAutoDiPilotDevice.getLKSFaultState();
        BydLogHelper.logD("getLKSFaultState " + lKSFaultState);
        return lKSFaultState;
    }

    public int n1(int i) {
        BydLogHelper.logD("setAutoParkingState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int autoParkingState = bYDAutoDiPilotDevice.setAutoParkingState(i);
        BydLogHelper.logD("setAutoParkingState  result " + autoParkingState);
        return autoParkingState;
    }

    public int n2(int i) {
        BydLogHelper.logD("setSideDistanceWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int sideDistanceWarningState = bYDAutoDiPilotDevice.setSideDistanceWarningState(i);
        BydLogHelper.logD("setSideDistanceWarningState result " + sideDistanceWarningState);
        return sideDistanceWarningState;
    }

    public int o() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int alarmType = bYDAutoDiPilotDevice.getAlarmType();
        BydLogHelper.logD("getAlarmType " + alarmType);
        return alarmType;
    }

    public int o0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int laneAssistType = bYDAutoDiPilotDevice.getLaneAssistType();
        BydLogHelper.logD("getLaneAssistType " + laneAssistType);
        return laneAssistType;
    }

    public int o1(int i) {
        BydLogHelper.logD("setBlindMonitorState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int blindMonitorState = bYDAutoDiPilotDevice.setBlindMonitorState(i);
        BydLogHelper.logD("setBlindMonitorState result  " + blindMonitorState);
        return blindMonitorState;
    }

    public int o2(int i) {
        BydLogHelper.logD("setSpeedLimitRange " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int speedLimitRange = bYDAutoDiPilotDevice.setSpeedLimitRange(i);
        BydLogHelper.logD("setSpeedLimitRange result " + speedLimitRange);
        return speedLimitRange;
    }

    public int p() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int attentionDetectionState = bYDAutoDiPilotDevice.getAttentionDetectionState();
        BydLogHelper.logD("getAttentionDetectionState " + attentionDetectionState);
        return attentionDetectionState;
    }

    public int p0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int leftBehindReminderState = bYDAutoDiPilotDevice.getLeftBehindReminderState();
        BydLogHelper.logD("getLeftBehindReminderState " + leftBehindReminderState);
        return leftBehindReminderState;
    }

    public int p1(int i) {
        BydLogHelper.logD("setChargerCoverState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int chargerCoverState = bYDAutoDiPilotDevice.setChargerCoverState(i);
        BydLogHelper.logD("setChargerCoverState result  " + chargerCoverState);
        return chargerCoverState;
    }

    public int p2(int i) {
        BydLogHelper.logD("setSteepDescentSlowDownState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int steepDescentSlowDownState = bYDAutoDiPilotDevice.setSteepDescentSlowDownState(i);
        BydLogHelper.logD("setSteepDescentSlowDownState result  " + steepDescentSlowDownState);
        return steepDescentSlowDownState;
    }

    public int q() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int autoParkingState = bYDAutoDiPilotDevice.getAutoParkingState();
        BydLogHelper.logD("getAutoParkingState " + autoParkingState);
        return autoParkingState;
    }

    public int q0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int lowSpeedEmergencyBrakingState = bYDAutoDiPilotDevice.getLowSpeedEmergencyBrakingState();
        BydLogHelper.logD("getLowSpeedEmergencyBrakingState " + lowSpeedEmergencyBrakingState);
        return lowSpeedEmergencyBrakingState;
    }

    public int q1(int i) {
        BydLogHelper.logD("setChildCareState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int childCareState = bYDAutoDiPilotDevice.setChildCareState(i);
        BydLogHelper.logD("setChildCareState  result " + childCareState);
        return childCareState;
    }

    public int q2(int i) {
        BydLogHelper.logD("setTSRLitSpdChgSwitchState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        BydLogHelper.logD("setTSRLitSpdChgSwitchState result " + bYDAutoDiPilotDevice.setTSRLitSpdChgSwitchState(i));
        return i;
    }

    public int r() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int blindMonitorState = bYDAutoDiPilotDevice.getBlindMonitorState();
        BydLogHelper.logD("getBlindMonitorState " + blindMonitorState);
        return blindMonitorState;
    }

    public int r0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int mechanismPosition = bYDAutoDiPilotDevice.getMechanismPosition();
        BydLogHelper.logD("getMechanismPosition " + mechanismPosition);
        return mechanismPosition;
    }

    public int r1(int i) {
        BydLogHelper.logD("setCollisionWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int collisionWarningState = bYDAutoDiPilotDevice.setCollisionWarningState(i);
        BydLogHelper.logD("setCollisionWarningState result " + collisionWarningState);
        return collisionWarningState;
    }

    public int r2(int i) {
        BydLogHelper.logD("setTemperatureWhileFatigue " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int temperatureWhileFatigue = bYDAutoDiPilotDevice.setTemperatureWhileFatigue(i);
        BydLogHelper.logD("setTemperatureWhileFatigue result  " + temperatureWhileFatigue);
        return temperatureWhileFatigue;
    }

    public int s() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int blindMonitorState1 = bYDAutoDiPilotDevice.getBlindMonitorState1();
        BydLogHelper.logD("getBlindMonitorState1 " + blindMonitorState1);
        return blindMonitorState1;
    }

    public int s0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int mistakeGasState = bYDAutoDiPilotDevice.getMistakeGasState();
        BydLogHelper.logD("getMistakeGasState " + mistakeGasState);
        return mistakeGasState;
    }

    public int s1(int i) {
        BydLogHelper.logD("setControlLossWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int controlLossWarningState = bYDAutoDiPilotDevice.setControlLossWarningState(i);
        BydLogHelper.logD("setControlLossWarningState result " + controlLossWarningState);
        return controlLossWarningState;
    }

    public int s2(int i) {
        BydLogHelper.logD("setTrafficIncidentWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficIncidentWarningState = bYDAutoDiPilotDevice.setTrafficIncidentWarningState(i);
        BydLogHelper.logD("setTrafficIncidentWarningState result " + trafficIncidentWarningState);
        return trafficIncidentWarningState;
    }

    public int t() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int blindMonitorState2 = bYDAutoDiPilotDevice.getBlindMonitorState2();
        BydLogHelper.logD("getBlindMonitorState2 " + blindMonitorState2);
        return blindMonitorState2;
    }

    public int t0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int openDoorAlertState = bYDAutoDiPilotDevice.getOpenDoorAlertState();
        BydLogHelper.logD("getOpenDoorAlertState " + openDoorAlertState);
        return openDoorAlertState;
    }

    public int t1(int i) {
        BydLogHelper.logD("setCpdStatus " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int cpdStatus = bYDAutoDiPilotDevice.setCpdStatus(i);
        BydLogHelper.logD("setCpdStatus result " + cpdStatus);
        return cpdStatus;
    }

    public int t2(int i) {
        BydLogHelper.logD("setTrafficJamWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficJamWarningState = bYDAutoDiPilotDevice.setTrafficJamWarningState(i);
        BydLogHelper.logD("setTrafficJamWarningState result " + trafficJamWarningState);
        return trafficJamWarningState;
    }

    public int u() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int chargerCoverState = bYDAutoDiPilotDevice.getChargerCoverState();
        BydLogHelper.logD("getChargerCoverState " + chargerCoverState);
        return chargerCoverState;
    }

    public int u0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlert138Mode = bYDAutoDiPilotDevice.getPredictCrashAlert138Mode();
        BydLogHelper.logD("getPredictCrashAlert138Mode " + predictCrashAlert138Mode);
        return predictCrashAlert138Mode;
    }

    public int u1(int i) {
        BydLogHelper.logD("setDangerousActionDetectionState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int dangerousActionDetectionState = bYDAutoDiPilotDevice.setDangerousActionDetectionState(i);
        BydLogHelper.logD("setDangerousActionDetectionState result  " + dangerousActionDetectionState);
        return dangerousActionDetectionState;
    }

    public int u2(int i) {
        BydLogHelper.logD("setTrafficLightInfoPushState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightInfoPushState = bYDAutoDiPilotDevice.setTrafficLightInfoPushState(i);
        BydLogHelper.logD("setTrafficLightInfoPushState result " + trafficLightInfoPushState);
        return trafficLightInfoPushState;
    }

    public int v() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int childCareState = bYDAutoDiPilotDevice.getChildCareState();
        BydLogHelper.logD("getChildCareState " + childCareState);
        return childCareState;
    }

    public int v0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlert32DMode = bYDAutoDiPilotDevice.getPredictCrashAlert32DMode();
        BydLogHelper.logD("getPredictCrashAlert32DMode " + predictCrashAlert32DMode);
        return predictCrashAlert32DMode;
    }

    public int v1(int i) {
        BydLogHelper.logD("setDangerousActionfSwitch " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int dangerousActionfSwitch = bYDAutoDiPilotDevice.setDangerousActionfSwitch(i);
        BydLogHelper.logD("setDangerousActionfSwitch result " + dangerousActionfSwitch);
        return dangerousActionfSwitch;
    }

    public int v2(int i) {
        BydLogHelper.logD("setTrafficLightRecognizeState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficLightRecognizeState = bYDAutoDiPilotDevice.setTrafficLightRecognizeState(i);
        BydLogHelper.logD("setTrafficLightRecognizeState result  " + trafficLightRecognizeState);
        return trafficLightRecognizeState;
    }

    public int w() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int collisionWarningState = bYDAutoDiPilotDevice.getCollisionWarningState();
        BydLogHelper.logD("getCollisionWarningState " + collisionWarningState);
        return collisionWarningState;
    }

    public int w0() {
        if (f737b != null) {
            BydLogHelper.logD("getPredictCrashAlertMode 0");
        }
        return -1;
    }

    public int w1(int i) {
        BydLogHelper.logD("setDistractionDetectionAidState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int distractionDetectionAidState = bYDAutoDiPilotDevice.setDistractionDetectionAidState(i);
        BydLogHelper.logD("setDistractionDetectionAidState result  " + distractionDetectionAidState);
        return distractionDetectionAidState;
    }

    public int w2(int i) {
        BydLogHelper.logD("setTrafficSignRecognizeState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignRecognizeState = bYDAutoDiPilotDevice.setTrafficSignRecognizeState(i);
        BydLogHelper.logD("setTrafficSignRecognizeState result  " + trafficSignRecognizeState);
        return trafficSignRecognizeState;
    }

    public int x() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int controlLossWarningState = bYDAutoDiPilotDevice.getControlLossWarningState();
        BydLogHelper.logD("getControlLossWarningState " + controlLossWarningState);
        return controlLossWarningState;
    }

    public int x0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int predictCrashAlertState = bYDAutoDiPilotDevice.getPredictCrashAlertState();
        BydLogHelper.logD("getPredictCrashAlertState " + predictCrashAlertState);
        return predictCrashAlertState;
    }

    public int x1(int i) {
        BydLogHelper.logD("setDistractionMonitoringAttention " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int distractionMonitoringAttention = bYDAutoDiPilotDevice.setDistractionMonitoringAttention(i);
        BydLogHelper.logD("setDistractionMonitoringAttention result " + distractionMonitoringAttention);
        return distractionMonitoringAttention;
    }

    public int x2(int i) {
        BydLogHelper.logD("setTrafficSignalWarningState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int trafficSignalWarningState = bYDAutoDiPilotDevice.setTrafficSignalWarningState(i);
        BydLogHelper.logD("setTrafficSignalWarningState result " + trafficSignalWarningState);
        return trafficSignalWarningState;
    }

    public int y() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int cpdDelayStatus = bYDAutoDiPilotDevice.getCpdDelayStatus();
        BydLogHelper.logD("getCpdDelayStatus " + cpdDelayStatus);
        return cpdDelayStatus;
    }

    public int y0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rainOpenFogLightState = bYDAutoDiPilotDevice.getRainOpenFogLightState();
        BydLogHelper.logD("getRainOpenFogLightState " + rainOpenFogLightState);
        return rainOpenFogLightState;
    }

    public int y1(int i) {
        BydLogHelper.logD("setDmsSelfCheckStatus " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int dmsSelfCheckStatus = bYDAutoDiPilotDevice.setDmsSelfCheckStatus(i);
        BydLogHelper.logD("setDmsSelfCheckStatus result " + dmsSelfCheckStatus);
        return dmsSelfCheckStatus;
    }

    public int y2(int i) {
        BydLogHelper.logD("setUrbanAIAssistanceState " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int urbanAIAssistanceState = bYDAutoDiPilotDevice.setUrbanAIAssistanceState(i);
        BydLogHelper.logD("setUrbanAIAssistanceState result  " + urbanAIAssistanceState);
        return urbanAIAssistanceState;
    }

    public int z() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int cpdStatus = bYDAutoDiPilotDevice.getCpdStatus();
        BydLogHelper.logD("getCpdStatus " + cpdStatus);
        return cpdStatus;
    }

    public int z0() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int rearCrashAlertState = bYDAutoDiPilotDevice.getRearCrashAlertState();
        BydLogHelper.logD("getRearCrashAlertState " + rearCrashAlertState);
        return rearCrashAlertState;
    }

    public int z1(int i) {
        BydLogHelper.logD("setDmsSwitchStatus " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int dmsSwitchStatus = bYDAutoDiPilotDevice.setDmsSwitchStatus(i);
        BydLogHelper.logD("setDmsSwitchStatus result " + dmsSwitchStatus);
        return dmsSwitchStatus;
    }

    public int z2(int i) {
        BydLogHelper.logD("setVerifyMode " + i);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = f737b;
        if (bYDAutoDiPilotDevice == null) {
            return -1;
        }
        int verifyMode = bYDAutoDiPilotDevice.setVerifyMode(i);
        BydLogHelper.logD("setVerifyMode result " + verifyMode);
        return verifyMode;
    }
}
