package com.ts.lib.car.hardware.dipilot;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes3.dex */
public class DipilotEventChangeValue extends CarBaseProperty {
    private int mAINavigationStateR;
    private int mAINavigationStateS;
    private int mAbnormalVehicleWarningStateR;
    private int mAbnormalVehicleWarningStateS;
    private int mAccStateR;
    private int mAdaptiveFrontLightFaultR;
    private int mAdasSoftVersionR;
    private int mAdativeCruiseControlFaultR;
    private int mAebDecActiveR;
    private int mAebFaultStateR;
    private int mAiCareStateR;
    private int mAiCareStateS;
    private int mAiDistanceLightStateFirst;
    private int mAiDistanceLightStateS;
    private int mAiDistanceLightStateSecond;
    private int mAiEmergencyBrakeStateR;
    private int mAiEmergencyBrakeStateS;
    private int mAiMatrixLightStateFirst;
    private int mAiMatrixLightStateS;
    private int mAiMatrixLightStateSecond;
    private int mAlarmTypeR;
    private int mAlarmTypeS;
    private int mAutoEmergencyFaultR;
    private int mAutoParkigAssistModeR;
    private int mAutoParkigAssistStatusR;
    private int mAutoParkingStateR;
    private int mAutomatedValetParkingStatusR;
    private int mBlindFaultR;
    private int mBlindMonitorStateFirst;
    private int mBlindMonitorStateS;
    private int mBlindMonitorStateSecond;
    private int mBsisCollicionAlarmSwitchS;
    private int mBsisEnableR;
    private int mBsisFaultR;
    private int mBsisFuncStatusR;
    private int mCollisionWarningStateR;
    private int mCollisionWarningStateS;
    private int mControlLossWarningStateR;
    private int mControlLossWarningStateS;
    private int mCpdDelayStatusR;
    private int mCpdStatusNewR;
    private int mCpdStatusR;
    private int mCpdStatusS;
    private int mCpdWarningStateR;
    private int mCpdWorkingStatusR;
    private int mCurveWarnFaultR;
    private int mDangerousActionfSwitchS;
    private int mDistractionMonitoringAttentionS;
    private int mDmsSelfCheckStatusS;
    private int mDmsSwitchStatusR;
    private int mDmsSwitchStatusS;
    private int mDnpStatusR;
    private int mDoNotPassWarningStateR;
    private int mDoNotPassWarningStateS;
    private int mDoorOpenFaultR;
    private int mDrivingVoiceBroadcastSwitchConfigTwoR;
    private int mE2EAIAssistanceStateR;
    private int mE2EAIAssistanceStateS;
    private int mElecCarBodyStablySysStateChanged;
    private int mElecCarBodyStablySysStateS;
    private int mElkTakeOverStateR;
    private int mEmergencyBrakeWarningStateR;
    private int mEmergencyBrakeWarningStateS;
    private int mEmergencyLaneAssistanceStateFirst;
    private int mEmergencyLaneAssistanceStateS;
    private int mEmergencyLaneAssistanceStateSecond;
    private int mEmergencyLaneKeepFaultR;
    private int mEmergencyVehicleWarningStateR;
    private int mEmergencyVehicleWarningStateS;
    private int mEspOffKeyStatusR;
    private int mFatigueMonitorChangedR;
    private int mFatigueMonitoringStatusS;
    private int mFatigueReliefSwitchS;
    private int mFcsrFaultR;
    private int mFcsrStateR;
    private int mFcsrStateS;
    private int mFcwFaultStateR;
    private int mForwardCollisionWarnFaultR;
    private int mFrontCrossAlarkFaultR;
    private int mFrontCrossBrakeFaultR;
    private int mFrontTrafficBrakeAlertStateR;
    private int mFrontTrafficBrakeAlertStateS;
    private int mFrontTrafficCrossAlertStateR;
    private int mFrontTrafficCrossAlertStateS;
    private int mGreenLightOptimalSpeedAdvisoryStateR;
    private int mGreenLightOptimalSpeedAdvisoryStateS;
    private int mHighHODToastR;
    private int mHighIOPToastR;
    private int mHighNaviFaultR;
    private int mHighSpeedAINavigationStateR;
    private int mHighSpeedAINavigationStateS;
    private int mHomeZoneParkingAssistStatusR;
    private int mIOPStateR;
    private int mIOPStateS;
    private int mIntelligentLimitFaultCallR;
    private int mIntelligentLimitFaultControllerR;
    private int mIntelligentSpeedLimitAlertStateFirst;
    private int mIntelligentSpeedLimitAlertStateS;
    private int mIntelligentSpeedLimitAlertStateSecond;
    private int mIntelligentSpeedLimitControlStateFirst;
    private int mIntelligentSpeedLimitControlStateS;
    private int mIntelligentSpeedLimitControlStateSecond;
    private int mInteractiveLaneChangeAssistanceStateR;
    private int mInteractiveLaneChangeAssistanceStateS;
    private int mInteractiveLaneChangeFaultR;
    private int mIntersectionCollisionAlertStateR;
    private int mIntersectionCollisionAlertStateS;
    private int mIsliWarningLevelSwitchStatusR;
    private int mIsliWarningStateR;
    private int mLaneAssistTypeR;
    private int mLaneAssistTypeS;
    private int mLaneCenteringControlFaultR;
    private int mLaneDeparturePreventionFaultR;
    private int mLaneDepartureWarnFaultR;
    private int mLaneKeepFaultR;
    private int mLdwFaultStateR;
    private int mLdwSensitivityR;
    private int mLdwStaticCalibrationS;
    private int mLimitSpeedChangeFaultR;
    private int mLksFaultStateR;
    private int mLowHODToastR;
    private int mLowIOPToastR;
    private int mLowSpeedEmergencyBrakingStateR;
    private int mLowSpeedEmergencyBrakingStateS;
    private int mMechanismPositionR;
    private int mMistakeGasStateR;
    private int mMistakeGasStateS;
    private int mMoisEnableR;
    private int mMoisFaultR;
    private int mMoisFuncStateR;
    private int mMoisFunctionSwitchS;
    private int mNavigationPilotFaultR;
    private int mNrbanNaviFaultR;
    private int mOpenDoorAlertStateR;
    private int mOpenDoorAlertStateS;
    private int mPredictCrashAlertModeRFirst;
    private int mPredictCrashAlertModeRSecond;
    private int mPredictCrashAlertModeS;
    private int mPredictCrashAlertStateR;
    private int mPredictCrashAlertStateS;
    private int mRearCollisionWarnFaultR;
    private int mRearCrashAlertStateR;
    private int mRearCrashAlertStateS;
    private int mRearCrossAlartFaultR;
    private int mRearCrossBrakeFaultR;
    private int mRearTrafficBrakeAlertStateR;
    private int mRearTrafficBrakeAlertStateS;
    private int mRearTrafficCrossAlertStateR;
    private int mRearTrafficCrossAlertStateS;
    private int mRedLightViolationWarningStateR;
    private int mRedLightViolationWarningStateS;
    private int mRemoteControlDriveCloseTops;
    private int mSAOCStateR;
    private int mSAOCStateS;
    private int mSLIFStateR;
    private int mSLIFStateS;
    private int mSideDistanceWarningStateR;
    private int mSideDistanceWarningStateS;
    private int mSpeedLimitRangeFirst;
    private int mSpeedLimitRangeS;
    private int mSpeedLimitRangeSecond;
    private int mSteepDescentSlowDownState4A9R;
    private int mSteepDescentSlowDownStateR;
    private int mSteepDescentSlowDownStateS;
    private int mSupportAiDistanceLight;
    private int mSupportAiEmergencyBrake;
    private int mSupportEmergencyLaneKeepAssist;
    private int mSupportEndToEndNavigation;
    private int mSupportFrontCrossingTrafficAlert;
    private int mSupportFrontCrossingTrafficBrake;
    private int mSupportHighwayNavigation;
    private int mSupportIntelligentNavigationFirst;
    private int mSupportIntelligentNavigationSecond;
    private int mSupportIntellignetSpeedLimitAlert;
    private int mSupportIntellignetSpeedLimitControl;
    private int mSupportLaneChangeAssist;
    private int mSupportLaneDepartureStop;
    private int mSupportLaneDepartureWarning;
    private int mSupportLaneKeep;
    private int mSupportPredictCrashAlertModeFirst;
    private int mSupportPredictCrashAlertModeSecond;
    private int mSupportTrafficLightAttention;
    private int mSupportTrafficSignRecogniton;
    private int mSupportUrbanNavigation;
    private int mTrafficAttentionFaultR;
    private int mTrafficIncidentWarningStateR;
    private int mTrafficIncidentWarningStateS;
    private int mTrafficJamWarningStateR;
    private int mTrafficJamWarningStateS;
    private int mTrafficLightInfoPushStateR;
    private int mTrafficLightInfoPushStateS;
    private int mTrafficLightRecognizeStateFirst;
    private int mTrafficLightRecognizeStateS;
    private int mTrafficLightRecognizeStateSecond;
    private int mTrafficSignRecognizeStateFirst;
    private int mTrafficSignRecognizeStateR;
    private int mTrafficSignRecognizeStateS;
    private int mTrafficSignRecognizeStateSecond;
    private int mTrafficSignalWarningStateR;
    private int mTrafficSignalWarningStateS;
    private int mTsrFaultR;
    private int mTsrLitSpdChgInformStateR;
    private int mTsrLitSpdChgSwitchStateR;
    private int mTsrLitSpdChgSwitchStateS;
    private int mUrbanAIAssistanceStateR;
    private int mUrbanAIAssistanceStateS;
    private int mVerifyModeR;
    private int mVerifyModeS;
    private int mVruFaultStateR;

    public int getFcsrFaultR() {
        return this.mFcsrFaultR;
    }

    public void setFcsrFaultR(int fcsrFaultR) {
        this.mFcsrFaultR = fcsrFaultR;
    }

    public int getFcsrStateR() {
        return this.mFcsrStateR;
    }

    public void setFcsrStateR(int fcsrStateR) {
        this.mFcsrStateR = fcsrStateR;
    }

    public int getFcsrStateS() {
        return this.mFcsrStateS;
    }

    public void setFcsrStateS(int fcsrStateS) {
        this.mFcsrStateS = fcsrStateS;
    }

    public int getAccStateR() {
        return this.mAccStateR;
    }

    public void setAccStateR(int accStateR) {
        this.mAccStateR = accStateR;
    }

    public int getHighHODToastR() {
        return this.mHighHODToastR;
    }

    public void setHighHODToastR(int highHODToastR) {
        this.mHighHODToastR = highHODToastR;
    }

    public int getHighIOPToastR() {
        return this.mHighIOPToastR;
    }

    public void setHighIOPToastR(int highIOPToastR) {
        this.mHighIOPToastR = highIOPToastR;
    }

    public int getIOPStateR() {
        return this.mIOPStateR;
    }

    public void setIOPStateR(int iOPStateR) {
        this.mIOPStateR = iOPStateR;
    }

    public int getIOPStateS() {
        return this.mIOPStateS;
    }

    public void setIOPStateS(int iOPStateS) {
        this.mIOPStateS = iOPStateS;
    }

    public int getLowHODToastR() {
        return this.mLowHODToastR;
    }

    public void setLowHODToastR(int lowHODToastR) {
        this.mLowHODToastR = lowHODToastR;
    }

    public int getLowIOPToastR() {
        return this.mLowIOPToastR;
    }

    public void setLowIOPToastR(int lowIOPToastR) {
        this.mLowIOPToastR = lowIOPToastR;
    }

    public int getSAOCStateR() {
        return this.mSAOCStateR;
    }

    public void setSAOCStateR(int sAOCStateR) {
        this.mSAOCStateR = sAOCStateR;
    }

    public int getSAOCStateS() {
        return this.mSAOCStateS;
    }

    public void setSAOCStateS(int sAOCStateS) {
        this.mSAOCStateS = sAOCStateS;
    }

    public int getSLIFStateR() {
        return this.mSLIFStateR;
    }

    public void setSLIFStateR(int sLIFStateR) {
        this.mSLIFStateR = sLIFStateR;
    }

    public int getSLIFStateS() {
        return this.mSLIFStateS;
    }

    public void setSLIFStateS(int sLIFStateS) {
        this.mSLIFStateS = sLIFStateS;
    }

    public int getAebDecActiveR() {
        return this.mAebDecActiveR;
    }

    public void setAebDecActiveR(int aebDecActiveR) {
        this.mAebDecActiveR = aebDecActiveR;
    }

    public int getEspOffKeyStatusR() {
        return this.mEspOffKeyStatusR;
    }

    public void setEspOffKeyStatusR(int result) {
        this.mEspOffKeyStatusR = result;
    }

    public int getAutoParkingStateR() {
        return this.mAutoParkingStateR;
    }

    public void setAutoParkingStateR(int result) {
        this.mAutoParkingStateR = result;
    }

    public int getCpdWarningStateR() {
        return this.mCpdWarningStateR;
    }

    public void setCpdWarningStateR(int cpdWarningStateR) {
        this.mCpdWarningStateR = cpdWarningStateR;
    }

    public int getCpdWorkingStatusR() {
        return this.mCpdWorkingStatusR;
    }

    public void setCpdWorkingStatusR(int cpdWorkingStatusR) {
        this.mCpdWorkingStatusR = cpdWorkingStatusR;
    }

    public int getIsliWarningLevelSwitchStatusR() {
        return this.mIsliWarningLevelSwitchStatusR;
    }

    public void setIsliWarningLevelSwitchStatusR(int value) {
        this.mIsliWarningLevelSwitchStatusR = value;
    }

    public int getAutomatedValetParkingStatusR() {
        return this.mAutomatedValetParkingStatusR;
    }

    public void setAutomatedValetParkingStatusR(int result) {
        this.mAutomatedValetParkingStatusR = result;
    }

    public int getAutoParkigAssistModeR() {
        return this.mAutoParkigAssistModeR;
    }

    public void setAutoParkigAssistModeR(int autoParkigAssistModeR) {
        this.mAutoParkigAssistModeR = autoParkigAssistModeR;
    }

    public int getAutoParkigAssistStatusR() {
        return this.mAutoParkigAssistStatusR;
    }

    public void setAutoParkigAssistStatusR(int autoParkigAssistStatusR) {
        this.mAutoParkigAssistStatusR = autoParkigAssistStatusR;
    }

    public int getHomeZoneParkingAssistStatusR() {
        return this.mHomeZoneParkingAssistStatusR;
    }

    public void setHomeZoneParkingAssistStatusR(int homeZoneParkingAssistStatusR) {
        this.mHomeZoneParkingAssistStatusR = homeZoneParkingAssistStatusR;
    }

    public int getAdaptiveFrontLightFaultR() {
        return this.mAdaptiveFrontLightFaultR;
    }

    public void setAdaptiveFrontLightFaultR(int adaptiveFrontLightFaultR) {
        this.mAdaptiveFrontLightFaultR = adaptiveFrontLightFaultR;
    }

    public int getAdativeCruiseControlFaultR() {
        return this.mAdativeCruiseControlFaultR;
    }

    public void setAdativeCruiseControlFaultR(int adativeCruiseControlFaultR) {
        this.mAdativeCruiseControlFaultR = adativeCruiseControlFaultR;
    }

    public int getAutoEmergencyFaultR() {
        return this.mAutoEmergencyFaultR;
    }

    public void setAutoEmergencyFaultR(int autoEmergencyFaultR) {
        this.mAutoEmergencyFaultR = autoEmergencyFaultR;
    }

    public int getBsisFaultR() {
        return this.mBsisFaultR;
    }

    public void setBsisFaultR(int result) {
        this.mBsisFaultR = result;
    }

    public int getBlindFaultR() {
        return this.mBlindFaultR;
    }

    public void setBlindFaultR(int blindFaultR) {
        this.mBlindFaultR = blindFaultR;
    }

    public int getCurveWarnFaultR() {
        return this.mCurveWarnFaultR;
    }

    public void setCurveWarnFaultR(int curveWarnFaultR) {
        this.mCurveWarnFaultR = curveWarnFaultR;
    }

    public int getDoorOpenFaultR() {
        return this.mDoorOpenFaultR;
    }

    public void setDoorOpenFaultR(int doorOpenFaultR) {
        this.mDoorOpenFaultR = doorOpenFaultR;
    }

    public int getEmergencyLaneKeepFaultR() {
        return this.mEmergencyLaneKeepFaultR;
    }

    public void setEmergencyLaneKeepFaultR(int emergencyLaneKeepFaultR) {
        this.mEmergencyLaneKeepFaultR = emergencyLaneKeepFaultR;
    }

    public int getForwardCollisionWarnFaultR() {
        return this.mForwardCollisionWarnFaultR;
    }

    public void setForwardCollisionWarnFaultR(int forwardCollisionWarnFaultR) {
        this.mForwardCollisionWarnFaultR = forwardCollisionWarnFaultR;
    }

    public int getFrontCrossAlarkFaultR() {
        return this.mFrontCrossAlarkFaultR;
    }

    public void setFrontCrossAlarkFaultR(int frontCrossAlarkFaultR) {
        this.mFrontCrossAlarkFaultR = frontCrossAlarkFaultR;
    }

    public int getFrontCrossBrakeFaultR() {
        return this.mFrontCrossBrakeFaultR;
    }

    public void setFrontCrossBrakeFaultR(int frontCrossBrakeFaultR) {
        this.mFrontCrossBrakeFaultR = frontCrossBrakeFaultR;
    }

    public int getHighNaviFaultR() {
        return this.mHighNaviFaultR;
    }

    public void setHighNaviFaultR(int highNaviFaultR) {
        this.mHighNaviFaultR = highNaviFaultR;
    }

    public int getIntelligentLimitFaultCallR() {
        return this.mIntelligentLimitFaultCallR;
    }

    public void setIntelligentLimitFaultCallR(int intelligentLimitFaultCallR) {
        this.mIntelligentLimitFaultCallR = intelligentLimitFaultCallR;
    }

    public int getIntelligentLimitFaultControllerR() {
        return this.mIntelligentLimitFaultControllerR;
    }

    public void setIntelligentLimitFaultControllerR(int intelligentLimitFaultControllerR) {
        this.mIntelligentLimitFaultControllerR = intelligentLimitFaultControllerR;
    }

    public int getInteractiveLaneChangeFaultR() {
        return this.mInteractiveLaneChangeFaultR;
    }

    public void setInteractiveLaneChangeFaultR(int interactiveLaneChangeFaultR) {
        this.mInteractiveLaneChangeFaultR = interactiveLaneChangeFaultR;
    }

    public int getLaneCenteringControlFaultR() {
        return this.mLaneCenteringControlFaultR;
    }

    public void setLaneCenteringControlFaultR(int laneCenteringControlFaultR) {
        this.mLaneCenteringControlFaultR = laneCenteringControlFaultR;
    }

    public int getLaneDeparturePreventionFaultR() {
        return this.mLaneDeparturePreventionFaultR;
    }

    public void setLaneDeparturePreventionFaultR(int laneDeparturePreventionFaultR) {
        this.mLaneDeparturePreventionFaultR = laneDeparturePreventionFaultR;
    }

    public int getLaneDepartureWarnFaultR() {
        return this.mLaneDepartureWarnFaultR;
    }

    public void setLaneDepartureWarnFaultR(int laneDepartureWarnFaultR) {
        this.mLaneDepartureWarnFaultR = laneDepartureWarnFaultR;
    }

    public int getLaneKeepFaultR() {
        return this.mLaneKeepFaultR;
    }

    public void setLaneKeepFaultR(int laneKeepFaultR) {
        this.mLaneKeepFaultR = laneKeepFaultR;
    }

    public int getLimitSpeedChangeFaultR() {
        return this.mLimitSpeedChangeFaultR;
    }

    public void setLimitSpeedChangeFaultR(int limitSpeedChangeFaultR) {
        this.mLimitSpeedChangeFaultR = limitSpeedChangeFaultR;
    }

    public int getMoisFaultR() {
        return this.mMoisFaultR;
    }

    public void setMoisFaultR(int result) {
        this.mMoisFaultR = result;
    }

    public int getNavigationPilotFaultR() {
        return this.mNavigationPilotFaultR;
    }

    public void setNavigationPilotFaultR(int navigationPilotFaultR) {
        this.mNavigationPilotFaultR = navigationPilotFaultR;
    }

    public int getNrbanNaviFaultR() {
        return this.mNrbanNaviFaultR;
    }

    public void setNrbanNaviFaultR(int nrbanNaviFaultR) {
        this.mNrbanNaviFaultR = nrbanNaviFaultR;
    }

    public int getRearCollisionWarnFaultR() {
        return this.mRearCollisionWarnFaultR;
    }

    public void setRearCollisionWarnFaultR(int rearCollisionWarnFaultR) {
        this.mRearCollisionWarnFaultR = rearCollisionWarnFaultR;
    }

    public int getRearCrossAlartFaultR() {
        return this.mRearCrossAlartFaultR;
    }

    public void setRearCrossAlartFaultR(int rearCrossAlartFaultR) {
        this.mRearCrossAlartFaultR = rearCrossAlartFaultR;
    }

    public int getRearCrossBrakeFaultR() {
        return this.mRearCrossBrakeFaultR;
    }

    public void setRearCrossBrakeFaultR(int rearCrossBrakeFaultR) {
        this.mRearCrossBrakeFaultR = rearCrossBrakeFaultR;
    }

    public int getTrafficAttentionFaultR() {
        return this.mTrafficAttentionFaultR;
    }

    public void setTrafficAttentionFaultR(int trafficAttentionFaultR) {
        this.mTrafficAttentionFaultR = trafficAttentionFaultR;
    }

    public int getTsrFaultR() {
        return this.mTsrFaultR;
    }

    public void setTsrFaultR(int tsrFaultR) {
        this.mTsrFaultR = tsrFaultR;
    }

    public int getCpdStatusNewR() {
        return this.mCpdStatusNewR;
    }

    public void setCpdStatusNewR(int cpdStatusNewR) {
        this.mCpdStatusNewR = cpdStatusNewR;
    }

    public int getBsisCollicionAlarmSwitchS() {
        return this.mBsisCollicionAlarmSwitchS;
    }

    public void setBsisCollicionAlarmSwitchS(int bsisCollicionAlarmSwitchS) {
        this.mBsisCollicionAlarmSwitchS = bsisCollicionAlarmSwitchS;
    }

    public int getBsisEnableR() {
        return this.mBsisEnableR;
    }

    public void setBsisEnableR(int bsisEnableR) {
        this.mBsisEnableR = bsisEnableR;
    }

    public int getBsisFuncStatusR() {
        return this.mBsisFuncStatusR;
    }

    public void setBsisFuncStatusR(int bsisFuncStatusR) {
        this.mBsisFuncStatusR = bsisFuncStatusR;
    }

    public int getMoisEnableR() {
        return this.mMoisEnableR;
    }

    public void setMoisEnableR(int moisEnableR) {
        this.mMoisEnableR = moisEnableR;
    }

    public int getMoisFuncStateR() {
        return this.mMoisFuncStateR;
    }

    public void setMoisFuncStateR(int moisFuncStateR) {
        this.mMoisFuncStateR = moisFuncStateR;
    }

    public int getMoisFunctionSwitchS() {
        return this.mMoisFunctionSwitchS;
    }

    public void setMoisFunctionSwitchS(int moisFunctionSwitchS) {
        this.mMoisFunctionSwitchS = moisFunctionSwitchS;
    }

    public int getSteepDescentSlowDownState4A9R() {
        return this.mSteepDescentSlowDownState4A9R;
    }

    public void setSteepDescentSlowDownState4A9R(int steepDescentSlowDownState4A9R) {
        this.mSteepDescentSlowDownState4A9R = steepDescentSlowDownState4A9R;
    }

    public int getFatigueMonitorChangedR() {
        return this.mFatigueMonitorChangedR;
    }

    public void setFatigueMonitorChangedR(int value) {
        this.mFatigueMonitorChangedR = value;
    }

    public int getAbnormalVehicleWarningStateR() {
        return this.mAbnormalVehicleWarningStateR;
    }

    public void setAbnormalVehicleWarningStateR(int abnormalVehicleWarningStateR) {
        this.mAbnormalVehicleWarningStateR = abnormalVehicleWarningStateR;
    }

    public int getLdwStaticCalibrationS() {
        return this.mLdwStaticCalibrationS;
    }

    public void setLdwStaticCalibrationS(int ldwStaticCalibrationS) {
        this.mLdwStaticCalibrationS = ldwStaticCalibrationS;
    }

    public int getAbnormalVehicleWarningStateS() {
        return this.mAbnormalVehicleWarningStateS;
    }

    public void setAbnormalVehicleWarningStateS(int abnormalVehicleWarningStateS) {
        this.mAbnormalVehicleWarningStateS = abnormalVehicleWarningStateS;
    }

    public int getAebFaultStateR() {
        return this.mAebFaultStateR;
    }

    public void setAebFaultStateR(int aebFaultStateR) {
        this.mAebFaultStateR = aebFaultStateR;
    }

    public int getCollisionWarningStateR() {
        return this.mCollisionWarningStateR;
    }

    public void setCollisionWarningStateR(int collisionWarningStateR) {
        this.mCollisionWarningStateR = collisionWarningStateR;
    }

    public int getCollisionWarningStateS() {
        return this.mCollisionWarningStateS;
    }

    public void setCollisionWarningStateS(int collisionWarningStateS) {
        this.mCollisionWarningStateS = collisionWarningStateS;
    }

    public int getControlLossWarningStateR() {
        return this.mControlLossWarningStateR;
    }

    public void setControlLossWarningStateR(int controlLossWarningStateR) {
        this.mControlLossWarningStateR = controlLossWarningStateR;
    }

    public int getControlLossWarningStateS() {
        return this.mControlLossWarningStateS;
    }

    public void setControlLossWarningStateS(int controlLossWarningStateS) {
        this.mControlLossWarningStateS = controlLossWarningStateS;
    }

    public int getDangerousActionfSwitchS() {
        return this.mDangerousActionfSwitchS;
    }

    public void setDangerousActionfSwitchS(int dangerousActionfSwitchS) {
        this.mDangerousActionfSwitchS = dangerousActionfSwitchS;
    }

    public int getDistractionMonitoringAttentionS() {
        return this.mDistractionMonitoringAttentionS;
    }

    public void setDistractionMonitoringAttentionS(int distractionMonitoringAttentionS) {
        this.mDistractionMonitoringAttentionS = distractionMonitoringAttentionS;
    }

    public int getDmsSelfCheckStatusS() {
        return this.mDmsSelfCheckStatusS;
    }

    public void setDmsSelfCheckStatusS(int dmsSelfCheckStatusS) {
        this.mDmsSelfCheckStatusS = dmsSelfCheckStatusS;
    }

    public int getDnpStatusR() {
        return this.mDnpStatusR;
    }

    public void setDnpStatusR(int dnpStatusR) {
        this.mDnpStatusR = dnpStatusR;
    }

    public int getDoNotPassWarningStateR() {
        return this.mDoNotPassWarningStateR;
    }

    public void setDoNotPassWarningStateR(int doNotPassWarningStateR) {
        this.mDoNotPassWarningStateR = doNotPassWarningStateR;
    }

    public int getDoNotPassWarningStateS() {
        return this.mDoNotPassWarningStateS;
    }

    public void setDoNotPassWarningStateS(int doNotPassWarningStateS) {
        this.mDoNotPassWarningStateS = doNotPassWarningStateS;
    }

    public int getEmergencyBrakeWarningStateR() {
        return this.mEmergencyBrakeWarningStateR;
    }

    public void setEmergencyBrakeWarningStateR(int emergencyBrakeWarningStateR) {
        this.mEmergencyBrakeWarningStateR = emergencyBrakeWarningStateR;
    }

    public int getEmergencyBrakeWarningStateS() {
        return this.mEmergencyBrakeWarningStateS;
    }

    public void setEmergencyBrakeWarningStateS(int emergencyBrakeWarningStateS) {
        this.mEmergencyBrakeWarningStateS = emergencyBrakeWarningStateS;
    }

    public int getEmergencyVehicleWarningStateR() {
        return this.mEmergencyVehicleWarningStateR;
    }

    public void setEmergencyVehicleWarningStateR(int emergencyVehicleWarningStateR) {
        this.mEmergencyVehicleWarningStateR = emergencyVehicleWarningStateR;
    }

    public int getEmergencyVehicleWarningStateS() {
        return this.mEmergencyVehicleWarningStateS;
    }

    public void setEmergencyVehicleWarningStateS(int emergencyVehicleWarningStateS) {
        this.mEmergencyVehicleWarningStateS = emergencyVehicleWarningStateS;
    }

    public int getFatigueMonitoringStatusS() {
        return this.mFatigueMonitoringStatusS;
    }

    public void setFatigueMonitoringStatusS(int fatigueMonitoringStatusS) {
        this.mFatigueMonitoringStatusS = fatigueMonitoringStatusS;
    }

    public int getFatigueReliefSwitchS() {
        return this.mFatigueReliefSwitchS;
    }

    public void setFatigueReliefSwitchS(int fatigueReliefSwitchS) {
        this.mFatigueReliefSwitchS = fatigueReliefSwitchS;
    }

    public int getFcwFaultStateR() {
        return this.mFcwFaultStateR;
    }

    public void setFcwFaultStateR(int fcwFaultStateR) {
        this.mFcwFaultStateR = fcwFaultStateR;
    }

    public int getGreenLightOptimalSpeedAdvisoryStateR() {
        return this.mGreenLightOptimalSpeedAdvisoryStateR;
    }

    public void setGreenLightOptimalSpeedAdvisoryStateR(int greenLightOptimalSpeedAdvisoryStateR) {
        this.mGreenLightOptimalSpeedAdvisoryStateR = greenLightOptimalSpeedAdvisoryStateR;
    }

    public int getGreenLightOptimalSpeedAdvisoryStateS() {
        return this.mGreenLightOptimalSpeedAdvisoryStateS;
    }

    public void setGreenLightOptimalSpeedAdvisoryStateS(int greenLightOptimalSpeedAdvisoryStateS) {
        this.mGreenLightOptimalSpeedAdvisoryStateS = greenLightOptimalSpeedAdvisoryStateS;
    }

    public int getIntersectionCollisionAlertStateR() {
        return this.mIntersectionCollisionAlertStateR;
    }

    public void setIntersectionCollisionAlertStateR(int intersectionCollisionAlertStateR) {
        this.mIntersectionCollisionAlertStateR = intersectionCollisionAlertStateR;
    }

    public int getIntersectionCollisionAlertStateS() {
        return this.mIntersectionCollisionAlertStateS;
    }

    public void setIntersectionCollisionAlertStateS(int intersectionCollisionAlertStateS) {
        this.mIntersectionCollisionAlertStateS = intersectionCollisionAlertStateS;
    }

    public int getLdwFaultStateR() {
        return this.mLdwFaultStateR;
    }

    public void setLdwFaultStateR(int ldwFaultStateR) {
        this.mLdwFaultStateR = ldwFaultStateR;
    }

    public int getLksFaultStateR() {
        return this.mLksFaultStateR;
    }

    public void setLksFaultStateR(int lksFaultStateR) {
        this.mLksFaultStateR = lksFaultStateR;
    }

    public int getLowSpeedEmergencyBrakingStateR() {
        return this.mLowSpeedEmergencyBrakingStateR;
    }

    public void setLowSpeedEmergencyBrakingStateR(int lowSpeedEmergencyBrakingStateR) {
        this.mLowSpeedEmergencyBrakingStateR = lowSpeedEmergencyBrakingStateR;
    }

    public int getLowSpeedEmergencyBrakingStateS() {
        return this.mLowSpeedEmergencyBrakingStateS;
    }

    public void setLowSpeedEmergencyBrakingStateS(int lowSpeedEmergencyBrakingStateS) {
        this.mLowSpeedEmergencyBrakingStateS = lowSpeedEmergencyBrakingStateS;
    }

    public int getMechanismPositionR() {
        return this.mMechanismPositionR;
    }

    public void setMechanismPositionR(int mechanismPositionR) {
        this.mMechanismPositionR = mechanismPositionR;
    }

    public int getRedLightViolationWarningStateR() {
        return this.mRedLightViolationWarningStateR;
    }

    public void setRedLightViolationWarningStateR(int redLightViolationWarningStateR) {
        this.mRedLightViolationWarningStateR = redLightViolationWarningStateR;
    }

    public int getRedLightViolationWarningStateS() {
        return this.mRedLightViolationWarningStateS;
    }

    public void setRedLightViolationWarningStateS(int redLightViolationWarningStateS) {
        this.mRedLightViolationWarningStateS = redLightViolationWarningStateS;
    }

    public int getSideDistanceWarningStateR() {
        return this.mSideDistanceWarningStateR;
    }

    public void setSideDistanceWarningStateR(int sideDistanceWarningStateR) {
        this.mSideDistanceWarningStateR = sideDistanceWarningStateR;
    }

    public int getSideDistanceWarningStateS() {
        return this.mSideDistanceWarningStateS;
    }

    public void setSideDistanceWarningStateS(int sideDistanceWarningStateS) {
        this.mSideDistanceWarningStateS = sideDistanceWarningStateS;
    }

    public int getTrafficIncidentWarningStateR() {
        return this.mTrafficIncidentWarningStateR;
    }

    public void setTrafficIncidentWarningStateR(int trafficIncidentWarningStateR) {
        this.mTrafficIncidentWarningStateR = trafficIncidentWarningStateR;
    }

    public int getTrafficIncidentWarningStateS() {
        return this.mTrafficIncidentWarningStateS;
    }

    public void setTrafficIncidentWarningStateS(int trafficIncidentWarningStateS) {
        this.mTrafficIncidentWarningStateS = trafficIncidentWarningStateS;
    }

    public int getTrafficJamWarningStateR() {
        return this.mTrafficJamWarningStateR;
    }

    public void setTrafficJamWarningStateR(int trafficJamWarningStateR) {
        this.mTrafficJamWarningStateR = trafficJamWarningStateR;
    }

    public int getTrafficJamWarningStateS() {
        return this.mTrafficJamWarningStateS;
    }

    public void setTrafficJamWarningStateS(int trafficJamWarningStateS) {
        this.mTrafficJamWarningStateS = trafficJamWarningStateS;
    }

    public int getTrafficLightInfoPushStateR() {
        return this.mTrafficLightInfoPushStateR;
    }

    public void setTrafficLightInfoPushStateR(int trafficLightInfoPushStateR) {
        this.mTrafficLightInfoPushStateR = trafficLightInfoPushStateR;
    }

    public int getTrafficLightInfoPushStateS() {
        return this.mTrafficLightInfoPushStateS;
    }

    public void setTrafficLightInfoPushStateS(int trafficLightInfoPushStateS) {
        this.mTrafficLightInfoPushStateS = trafficLightInfoPushStateS;
    }

    public int getTrafficSignalWarningStateR() {
        return this.mTrafficSignalWarningStateR;
    }

    public void setTrafficSignalWarningStateR(int trafficSignalWarningStateR) {
        this.mTrafficSignalWarningStateR = trafficSignalWarningStateR;
    }

    public int getTrafficSignalWarningStateS() {
        return this.mTrafficSignalWarningStateS;
    }

    public void setTrafficSignalWarningStateS(int trafficSignalWarningStateS) {
        this.mTrafficSignalWarningStateS = trafficSignalWarningStateS;
    }

    public int getVruFaultStateR() {
        return this.mVruFaultStateR;
    }

    public void setVruFaultStateR(int vruFaultStateR) {
        this.mVruFaultStateR = vruFaultStateR;
    }

    public int getAdasSoftVersionR() {
        return this.mAdasSoftVersionR;
    }

    public void setAdasSoftVersionR(int adasSoftVersionR) {
        this.mAdasSoftVersionR = adasSoftVersionR;
    }

    public int getElkTakeOverStateR() {
        return this.mElkTakeOverStateR;
    }

    public void setElkTakeOverStateR(int elkTakeOverStateR) {
        this.mElkTakeOverStateR = elkTakeOverStateR;
    }

    public int getIsliWarningStateR() {
        return this.mIsliWarningStateR;
    }

    public void setIsliWarningStateR(int isliWarningStateR) {
        this.mIsliWarningStateR = isliWarningStateR;
    }

    public int getTsrLitSpdChgInformStateR() {
        return this.mTsrLitSpdChgInformStateR;
    }

    public void setTsrLitSpdChgInformStateR(int tsrLitSpdChgInformStateR) {
        this.mTsrLitSpdChgInformStateR = tsrLitSpdChgInformStateR;
    }

    public int getTsrLitSpdChgSwitchStateR() {
        return this.mTsrLitSpdChgSwitchStateR;
    }

    public void setTsrLitSpdChgSwitchStateR(int tsrLitSpdChgSwitchStateR) {
        this.mTsrLitSpdChgSwitchStateR = tsrLitSpdChgSwitchStateR;
    }

    public int getTsrLitSpdChgSwitchStateS() {
        return this.mTsrLitSpdChgSwitchStateS;
    }

    public void setTsrLitSpdChgSwitchStateS(int tsrLitSpdChgSwitchStateS) {
        this.mTsrLitSpdChgSwitchStateS = tsrLitSpdChgSwitchStateS;
    }

    public void setSupportIntelligentNavigationFirst(int supportIntelligentNavigationFirst) {
        this.mSupportIntelligentNavigationFirst = supportIntelligentNavigationFirst;
    }

    public int getSupportIntelligentNavigationFirst() {
        return this.mSupportIntelligentNavigationFirst;
    }

    public void setSupportIntelligentNavigationSecond(int supportIntelligentNavigationSecond) {
        this.mSupportIntelligentNavigationSecond = supportIntelligentNavigationSecond;
    }

    public int getSupportIntelligentNavigationSecond() {
        return this.mSupportIntelligentNavigationSecond;
    }

    public void setSupportHighwayNavigation(int supportHighwayNavigation) {
        this.mSupportHighwayNavigation = supportHighwayNavigation;
    }

    public int getSupportHighwayNavigation() {
        return this.mSupportHighwayNavigation;
    }

    public void setSupportUrbanNavigation(int supportUrbanNavigation) {
        this.mSupportUrbanNavigation = supportUrbanNavigation;
    }

    public int getSupportUrbanNavigation() {
        return this.mSupportUrbanNavigation;
    }

    public void setSupportEndToEndNavigation(int supportEndToEndNavigation) {
        this.mSupportEndToEndNavigation = supportEndToEndNavigation;
    }

    public int getSupportEndToEndNavigation() {
        return this.mSupportEndToEndNavigation;
    }

    public void setSupportLaneChangeAssist(int supportLaneChangeAssist) {
        this.mSupportLaneChangeAssist = supportLaneChangeAssist;
    }

    public int getSupportLaneChangeAssist() {
        return this.mSupportLaneChangeAssist;
    }

    public void setSupportLaneDepartureWarning(int supportLaneDepartureWarning) {
        this.mSupportLaneDepartureWarning = supportLaneDepartureWarning;
    }

    public int getSupportLaneDepartureWarning() {
        return this.mSupportLaneDepartureWarning;
    }

    public void setSupportLaneKeep(int supportLaneKeep) {
        this.mSupportLaneKeep = supportLaneKeep;
    }

    public int getSupportLaneKeep() {
        return this.mSupportLaneKeep;
    }

    public void setSupportLaneDepartureStop(int supportLaneDepartureStop) {
        this.mSupportLaneDepartureStop = supportLaneDepartureStop;
    }

    public int getSupportLaneDepartureStop() {
        return this.mSupportLaneDepartureStop;
    }

    public void setSupportEmergencyLaneKeepAssist(int supportEmergencyLaneKeepAssist) {
        this.mSupportEmergencyLaneKeepAssist = supportEmergencyLaneKeepAssist;
    }

    public int getSupportEmergencyLaneKeepAssist() {
        return this.mSupportEmergencyLaneKeepAssist;
    }

    public void setSupportTrafficSignRecogniton(int supportTrafficSignRecogniton) {
        this.mSupportTrafficSignRecogniton = supportTrafficSignRecogniton;
    }

    public int getSupportTrafficSignRecogniton() {
        return this.mSupportTrafficSignRecogniton;
    }

    public void setSupportIntellignetSpeedLimitAlert(int supportIntellignetSpeedLimitAlert) {
        this.mSupportIntellignetSpeedLimitAlert = supportIntellignetSpeedLimitAlert;
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        return this.mSupportIntellignetSpeedLimitAlert;
    }

    public void setSupportIntellignetSpeedLimitControl(int supportIntellignetSpeedLimitControl) {
        this.mSupportIntellignetSpeedLimitControl = supportIntellignetSpeedLimitControl;
    }

    public int getSupportIntellignetSpeedLimitControl() {
        return this.mSupportIntellignetSpeedLimitControl;
    }

    public void setSupportTrafficLightAttention(int supportTrafficLightAttention) {
        this.mSupportTrafficLightAttention = supportTrafficLightAttention;
    }

    public int getSupportTrafficLightAttention() {
        return this.mSupportTrafficLightAttention;
    }

    public void setSupportFrontCrossingTrafficAlert(int supportFrontCrossingTrafficAlert) {
        this.mSupportFrontCrossingTrafficAlert = supportFrontCrossingTrafficAlert;
    }

    public int getSupportFrontCrossingTrafficAlert() {
        return this.mSupportFrontCrossingTrafficAlert;
    }

    public void setSupportFrontCrossingTrafficBrake(int supportFrontCrossingTrafficBrake) {
        this.mSupportFrontCrossingTrafficBrake = supportFrontCrossingTrafficBrake;
    }

    public int getSupportFrontCrossingTrafficBrake() {
        return this.mSupportFrontCrossingTrafficBrake;
    }

    public void setSupportPredictCrashAlertModeFirst(int supportPredictCrashAlertModeFirst) {
        this.mSupportPredictCrashAlertModeFirst = supportPredictCrashAlertModeFirst;
    }

    public int getSupportPredictCrashAlertModeFirst() {
        return this.mSupportPredictCrashAlertModeFirst;
    }

    public void setSupportPredictCrashAlertModeSecond(int supportPredictCrashAlertModeSecond) {
        this.mSupportPredictCrashAlertModeSecond = supportPredictCrashAlertModeSecond;
    }

    public int getSupportPredictCrashAlertModeSecond() {
        return this.mSupportPredictCrashAlertModeSecond;
    }

    public void setSupportAiEmergencyBrake(int supportAiEmergencyBrake) {
        this.mSupportAiEmergencyBrake = supportAiEmergencyBrake;
    }

    public int getSupportAiEmergencyBrake() {
        return this.mSupportAiEmergencyBrake;
    }

    public void setSupportAiDistanceLight(int supportAiDistanceLight) {
        this.mSupportAiDistanceLight = supportAiDistanceLight;
    }

    public int getSupportAiDistanceLight() {
        return this.mSupportAiDistanceLight;
    }

    public void setDrivingVoiceBroadcastSwitchConfigTwoR(int drivingVoiceBroadcastSwitchConfigTwoR) {
        this.mDrivingVoiceBroadcastSwitchConfigTwoR = drivingVoiceBroadcastSwitchConfigTwoR;
    }

    public int getDrivingVoiceBroadcastSwitchConfigTwoR() {
        return this.mDrivingVoiceBroadcastSwitchConfigTwoR;
    }

    public int getElecCarBodyStablySysStateChanged() {
        return this.mElecCarBodyStablySysStateChanged;
    }

    public void setElecCarBodyStablySysStateChanged(int elecCarBodyStablySysStateChanged) {
        this.mElecCarBodyStablySysStateChanged = elecCarBodyStablySysStateChanged;
    }

    public int getRemoteControlDriveCloseTops() {
        return this.mRemoteControlDriveCloseTops;
    }

    public void setRemoteControlDriveCloseTops(int remoteControlDriveCloseTops) {
        this.mRemoteControlDriveCloseTops = remoteControlDriveCloseTops;
    }

    public int getAINavigationStateR() {
        return this.mAINavigationStateR;
    }

    public void setAINavigationStateR(int aINavigationStateR) {
        this.mAINavigationStateR = aINavigationStateR;
    }

    public int getAINavigationStateS() {
        return this.mAINavigationStateS;
    }

    public void setAINavigationStateS(int aINavigationStateS) {
        this.mAINavigationStateS = aINavigationStateS;
    }

    public int getAiCareStateR() {
        return this.mAiCareStateR;
    }

    public void setAiCareStateR(int aiCareStateR) {
        this.mAiCareStateR = aiCareStateR;
    }

    public int getAiCareStateS() {
        return this.mAiCareStateS;
    }

    public void setAiCareStateS(int aiCareStateS) {
        this.mAiCareStateS = aiCareStateS;
    }

    public int getAiDistanceLightStateFirst() {
        return this.mAiDistanceLightStateFirst;
    }

    public void setAiDistanceLightStateFirst(int aiDistanceLightStateFirst) {
        this.mAiDistanceLightStateFirst = aiDistanceLightStateFirst;
    }

    public int getAiDistanceLightStateS() {
        return this.mAiDistanceLightStateS;
    }

    public void setAiDistanceLightStateS(int aiDistanceLightStateS) {
        this.mAiDistanceLightStateS = aiDistanceLightStateS;
    }

    public int getAiDistanceLightStateSecond() {
        return this.mAiDistanceLightStateSecond;
    }

    public void setAiDistanceLightStateSecond(int aiDistanceLightStateSecond) {
        this.mAiDistanceLightStateSecond = aiDistanceLightStateSecond;
    }

    public int getAiEmergencyBrakeStateR() {
        return this.mAiEmergencyBrakeStateR;
    }

    public void setAiEmergencyBrakeStateR(int aiEmergencyBrakeStateR) {
        this.mAiEmergencyBrakeStateR = aiEmergencyBrakeStateR;
    }

    public int getAiEmergencyBrakeStateS() {
        return this.mAiEmergencyBrakeStateS;
    }

    public void setAiEmergencyBrakeStateS(int aiEmergencyBrakeStateS) {
        this.mAiEmergencyBrakeStateS = aiEmergencyBrakeStateS;
    }

    public int getAiMatrixLightStateFirst() {
        return this.mAiMatrixLightStateFirst;
    }

    public void setAiMatrixLightStateFirst(int aiMatrixLightStateFirst) {
        this.mAiMatrixLightStateFirst = aiMatrixLightStateFirst;
    }

    public int getAiMatrixLightStateS() {
        return this.mAiMatrixLightStateS;
    }

    public void setAiMatrixLightStateS(int aiMatrixLightStateS) {
        this.mAiMatrixLightStateS = aiMatrixLightStateS;
    }

    public int getAiMatrixLightStateSecond() {
        return this.mAiMatrixLightStateSecond;
    }

    public void setAiMatrixLightStateSecond(int aiMatrixLightStateSecond) {
        this.mAiMatrixLightStateSecond = aiMatrixLightStateSecond;
    }

    public int getAlarmTypeR() {
        return this.mAlarmTypeR;
    }

    public void setAlarmTypeR(int alarmTypeR) {
        this.mAlarmTypeR = alarmTypeR;
    }

    public int getAlarmTypeS() {
        return this.mAlarmTypeS;
    }

    public void setAlarmTypeS(int alarmTypeS) {
        this.mAlarmTypeS = alarmTypeS;
    }

    public int getBlindMonitorStateFirst() {
        return this.mBlindMonitorStateFirst;
    }

    public void setBlindMonitorStateFirst(int blindMonitorStateFirst) {
        this.mBlindMonitorStateFirst = blindMonitorStateFirst;
    }

    public int getBlindMonitorStateS() {
        return this.mBlindMonitorStateS;
    }

    public void setBlindMonitorStateS(int blindMonitorStateS) {
        this.mBlindMonitorStateS = blindMonitorStateS;
    }

    public int getBlindMonitorStateSecond() {
        return this.mBlindMonitorStateSecond;
    }

    public void setBlindMonitorStateSecond(int blindMonitorStateSecond) {
        this.mBlindMonitorStateSecond = blindMonitorStateSecond;
    }

    public int getE2EAIAssistanceStateR() {
        return this.mE2EAIAssistanceStateR;
    }

    public void setE2EAIAssistanceStateR(int e2EAIAssistanceStateR) {
        this.mE2EAIAssistanceStateR = e2EAIAssistanceStateR;
    }

    public int getE2EAIAssistanceStateS() {
        return this.mE2EAIAssistanceStateS;
    }

    public void setE2EAIAssistanceStateS(int e2EAIAssistanceStateS) {
        this.mE2EAIAssistanceStateS = e2EAIAssistanceStateS;
    }

    public int getElecCarBodyStablySysStateS() {
        return this.mElecCarBodyStablySysStateS;
    }

    public void setElecCarBodyStablySysStateS(int elecCarBodyStablySysStateS) {
        this.mElecCarBodyStablySysStateS = elecCarBodyStablySysStateS;
    }

    public int getEmergencyLaneAssistanceStateFirst() {
        return this.mEmergencyLaneAssistanceStateFirst;
    }

    public void setEmergencyLaneAssistanceStateFirst(int emergencyLaneAssistanceStateFirst) {
        this.mEmergencyLaneAssistanceStateFirst = emergencyLaneAssistanceStateFirst;
    }

    public int getEmergencyLaneAssistanceStateS() {
        return this.mEmergencyLaneAssistanceStateS;
    }

    public void setEmergencyLaneAssistanceStateS(int emergencyLaneAssistanceStateS) {
        this.mEmergencyLaneAssistanceStateS = emergencyLaneAssistanceStateS;
    }

    public int getEmergencyLaneAssistanceStateSecond() {
        return this.mEmergencyLaneAssistanceStateSecond;
    }

    public void setEmergencyLaneAssistanceStateSecond(int emergencyLaneAssistanceStateSecond) {
        this.mEmergencyLaneAssistanceStateSecond = emergencyLaneAssistanceStateSecond;
    }

    public int getFrontTrafficBrakeAlertStateR() {
        return this.mFrontTrafficBrakeAlertStateR;
    }

    public void setFrontTrafficBrakeAlertStateR(int frontTrafficBrakeAlertStateR) {
        this.mFrontTrafficBrakeAlertStateR = frontTrafficBrakeAlertStateR;
    }

    public int getFrontTrafficBrakeAlertStateS() {
        return this.mFrontTrafficBrakeAlertStateS;
    }

    public void setFrontTrafficBrakeAlertStateS(int frontTrafficBrakeAlertStateS) {
        this.mFrontTrafficBrakeAlertStateS = frontTrafficBrakeAlertStateS;
    }

    public int getFrontTrafficCrossAlertStateR() {
        return this.mFrontTrafficCrossAlertStateR;
    }

    public void setFrontTrafficCrossAlertStateR(int frontTrafficCrossAlertStateR) {
        this.mFrontTrafficCrossAlertStateR = frontTrafficCrossAlertStateR;
    }

    public int getFrontTrafficCrossAlertStateS() {
        return this.mFrontTrafficCrossAlertStateS;
    }

    public void setFrontTrafficCrossAlertStateS(int frontTrafficCrossAlertStateS) {
        this.mFrontTrafficCrossAlertStateS = frontTrafficCrossAlertStateS;
    }

    public int getHighSpeedAINavigationStateR() {
        return this.mHighSpeedAINavigationStateR;
    }

    public void setHighSpeedAINavigationStateR(int highSpeedAINavigationStateR) {
        this.mHighSpeedAINavigationStateR = highSpeedAINavigationStateR;
    }

    public int getHighSpeedAINavigationStateS() {
        return this.mHighSpeedAINavigationStateS;
    }

    public void setHighSpeedAINavigationStateS(int highSpeedAINavigationStateS) {
        this.mHighSpeedAINavigationStateS = highSpeedAINavigationStateS;
    }

    public int getIntelligentSpeedLimitAlertStateFirst() {
        return this.mIntelligentSpeedLimitAlertStateFirst;
    }

    public void setIntelligentSpeedLimitAlertStateFirst(int intelligentSpeedLimitAlertStateFirst) {
        this.mIntelligentSpeedLimitAlertStateFirst = intelligentSpeedLimitAlertStateFirst;
    }

    public int getIntelligentSpeedLimitAlertStateS() {
        return this.mIntelligentSpeedLimitAlertStateS;
    }

    public void setIntelligentSpeedLimitAlertStateS(int intelligentSpeedLimitAlertStateS) {
        this.mIntelligentSpeedLimitAlertStateS = intelligentSpeedLimitAlertStateS;
    }

    public int getIntelligentSpeedLimitAlertStateSecond() {
        return this.mIntelligentSpeedLimitAlertStateSecond;
    }

    public void setIntelligentSpeedLimitAlertStateSecond(int intelligentSpeedLimitAlertStateSecond) {
        this.mIntelligentSpeedLimitAlertStateSecond = intelligentSpeedLimitAlertStateSecond;
    }

    public int getIntelligentSpeedLimitControlStateS() {
        return this.mIntelligentSpeedLimitControlStateS;
    }

    public void setIntelligentSpeedLimitControlStateS(int intelligentSpeedLimitControlStateS) {
        this.mIntelligentSpeedLimitControlStateS = intelligentSpeedLimitControlStateS;
    }

    public int getIntelligentSpeedLimitControlStateFirst() {
        return this.mIntelligentSpeedLimitControlStateFirst;
    }

    public void setIntelligentSpeedLimitControlStateFirst(int IntelligentSpeedLimitControlStateFirst) {
        this.mIntelligentSpeedLimitControlStateFirst = IntelligentSpeedLimitControlStateFirst;
    }

    public int getIntelligentSpeedLimitControlStateSecond() {
        return this.mIntelligentSpeedLimitControlStateSecond;
    }

    public void setIntelligentSpeedLimitControlStateSecond(int intelligentSpeedLimitControlStateSecond) {
        this.mIntelligentSpeedLimitControlStateSecond = intelligentSpeedLimitControlStateSecond;
    }

    public int getInteractiveLaneChangeAssistanceStateR() {
        return this.mInteractiveLaneChangeAssistanceStateR;
    }

    public void setInteractiveLaneChangeAssistanceStateR(int interactiveLaneChangeAssistanceStateR) {
        this.mInteractiveLaneChangeAssistanceStateR = interactiveLaneChangeAssistanceStateR;
    }

    public int getInteractiveLaneChangeAssistanceStateS() {
        return this.mInteractiveLaneChangeAssistanceStateS;
    }

    public void setInteractiveLaneChangeAssistanceStateS(int interactiveLaneChangeAssistanceStateS) {
        this.mInteractiveLaneChangeAssistanceStateS = interactiveLaneChangeAssistanceStateS;
    }

    public int getLaneAssistTypeR() {
        return this.mLaneAssistTypeR;
    }

    public void setLaneAssistTypeR(int laneAssistTypeR) {
        this.mLaneAssistTypeR = laneAssistTypeR;
    }

    public int getLaneAssistTypeS() {
        return this.mLaneAssistTypeS;
    }

    public void setLaneAssistTypeS(int laneAssistTypeS) {
        this.mLaneAssistTypeS = laneAssistTypeS;
    }

    public int getMistakeGasStateR() {
        return this.mMistakeGasStateR;
    }

    public void setMistakeGasStateR(int mistakeGasStateR) {
        this.mMistakeGasStateR = mistakeGasStateR;
    }

    public int getMistakeGasStateS() {
        return this.mMistakeGasStateS;
    }

    public void setMistakeGasStateS(int mistakeGasStateS) {
        this.mMistakeGasStateS = mistakeGasStateS;
    }

    public int getOpenDoorAlertStateR() {
        return this.mOpenDoorAlertStateR;
    }

    public void setOpenDoorAlertStateR(int openDoorAlertStateR) {
        this.mOpenDoorAlertStateR = openDoorAlertStateR;
    }

    public int getOpenDoorAlertStateS() {
        return this.mOpenDoorAlertStateS;
    }

    public void setOpenDoorAlertStateS(int openDoorAlertStateS) {
        this.mOpenDoorAlertStateS = openDoorAlertStateS;
    }

    public int getPredictCrashAlertModeRFirst() {
        return this.mPredictCrashAlertModeRFirst;
    }

    public void setPredictCrashAlertModeRFirst(int predictCrashAlertModeRFirst) {
        this.mPredictCrashAlertModeRFirst = predictCrashAlertModeRFirst;
    }

    public int getPredictCrashAlertModeRSecond() {
        return this.mPredictCrashAlertModeRSecond;
    }

    public void setPredictCrashAlertModeRSecond(int predictCrashAlertModeRSecond) {
        this.mPredictCrashAlertModeRSecond = predictCrashAlertModeRSecond;
    }

    public int getPredictCrashAlertModeS() {
        return this.mPredictCrashAlertModeS;
    }

    public void setPredictCrashAlertModeS(int predictCrashAlertModeS) {
        this.mPredictCrashAlertModeS = predictCrashAlertModeS;
    }

    public int getPredictCrashAlertStateR() {
        return this.mPredictCrashAlertStateR;
    }

    public void setPredictCrashAlertStateR(int predictCrashAlertStateR) {
        this.mPredictCrashAlertStateR = predictCrashAlertStateR;
    }

    public int getPredictCrashAlertStateS() {
        return this.mPredictCrashAlertStateS;
    }

    public void setPredictCrashAlertStateS(int predictCrashAlertStateS) {
        this.mPredictCrashAlertStateS = predictCrashAlertStateS;
    }

    public int getRearCrashAlertStateR() {
        return this.mRearCrashAlertStateR;
    }

    public void setRearCrashAlertStateR(int rearCrashAlertStateR) {
        this.mRearCrashAlertStateR = rearCrashAlertStateR;
    }

    public int getRearCrashAlertStateS() {
        return this.mRearCrashAlertStateS;
    }

    public void setRearCrashAlertStateS(int rearCrashAlertStateS) {
        this.mRearCrashAlertStateS = rearCrashAlertStateS;
    }

    public int getRearTrafficBrakeAlertStateR() {
        return this.mRearTrafficBrakeAlertStateR;
    }

    public void setRearTrafficBrakeAlertStateR(int rearTrafficBrakeAlertStateR) {
        this.mRearTrafficBrakeAlertStateR = rearTrafficBrakeAlertStateR;
    }

    public int getRearTrafficBrakeAlertStateS() {
        return this.mRearTrafficBrakeAlertStateS;
    }

    public void setRearTrafficBrakeAlertStateS(int rearTrafficBrakeAlertStateS) {
        this.mRearTrafficBrakeAlertStateS = rearTrafficBrakeAlertStateS;
    }

    public int getRearTrafficCrossAlertStateR() {
        return this.mRearTrafficCrossAlertStateR;
    }

    public void setRearTrafficCrossAlertStateR(int rearTrafficCrossAlertStateR) {
        this.mRearTrafficCrossAlertStateR = rearTrafficCrossAlertStateR;
    }

    public int getRearTrafficCrossAlertStateS() {
        return this.mRearTrafficCrossAlertStateS;
    }

    public void setRearTrafficCrossAlertStateS(int rearTrafficCrossAlertStateS) {
        this.mRearTrafficCrossAlertStateS = rearTrafficCrossAlertStateS;
    }

    public int getSpeedLimitRangeFirst() {
        return this.mSpeedLimitRangeFirst;
    }

    public void setSpeedLimitRangeFirst(int speedLimitRangeFirst) {
        this.mSpeedLimitRangeFirst = speedLimitRangeFirst;
    }

    public int getSpeedLimitRangeS() {
        return this.mSpeedLimitRangeS;
    }

    public void setSpeedLimitRangeS(int speedLimitRangeS) {
        this.mSpeedLimitRangeS = speedLimitRangeS;
    }

    public int getSpeedLimitRangeSecond() {
        return this.mSpeedLimitRangeSecond;
    }

    public void setSpeedLimitRangeSecond(int speedLimitRangeSecond) {
        this.mSpeedLimitRangeSecond = speedLimitRangeSecond;
    }

    public int getSteepDescentSlowDownStateR() {
        return this.mSteepDescentSlowDownStateR;
    }

    public void setSteepDescentSlowDownStateR(int steepDescentSlowDownStateR) {
        this.mSteepDescentSlowDownStateR = steepDescentSlowDownStateR;
    }

    public int getSteepDescentSlowDownStateS() {
        return this.mSteepDescentSlowDownStateS;
    }

    public void setSteepDescentSlowDownStateS(int steepDescentSlowDownStateS) {
        this.mSteepDescentSlowDownStateS = steepDescentSlowDownStateS;
    }

    public int getTrafficLightRecognizeStateFirst() {
        return this.mTrafficLightRecognizeStateFirst;
    }

    public void setTrafficLightRecognizeStateFirst(int trafficLightRecognizeStateFirst) {
        this.mTrafficLightRecognizeStateFirst = trafficLightRecognizeStateFirst;
    }

    public int getTrafficLightRecognizeStateS() {
        return this.mTrafficLightRecognizeStateS;
    }

    public void setTrafficLightRecognizeStateS(int trafficLightRecognizeStateS) {
        this.mTrafficLightRecognizeStateS = trafficLightRecognizeStateS;
    }

    public int getTrafficLightRecognizeStateSecond() {
        return this.mTrafficLightRecognizeStateSecond;
    }

    public void setTrafficLightRecognizeStateSecond(int trafficLightRecognizeStateSecond) {
        this.mTrafficLightRecognizeStateSecond = trafficLightRecognizeStateSecond;
    }

    public int getTrafficSignRecognizeStateFirst() {
        return this.mTrafficSignRecognizeStateFirst;
    }

    public void setTrafficSignRecognizeStateFirst(int trafficSignRecognizeStateFirst) {
        this.mTrafficSignRecognizeStateFirst = trafficSignRecognizeStateFirst;
    }

    public int getTrafficSignRecognizeStateR() {
        return this.mTrafficSignRecognizeStateR;
    }

    public void setTrafficSignRecognizeStateR(int trafficSignRecognizeStateR) {
        this.mTrafficSignRecognizeStateR = trafficSignRecognizeStateR;
    }

    public int getTrafficSignRecognizeStateS() {
        return this.mTrafficSignRecognizeStateS;
    }

    public void setTrafficSignRecognizeStateS(int trafficSignRecognizeStateS) {
        this.mTrafficSignRecognizeStateS = trafficSignRecognizeStateS;
    }

    public int getTrafficSignRecognizeStateSecond() {
        return this.mTrafficSignRecognizeStateSecond;
    }

    public void setTrafficSignRecognizeStateSecond(int trafficSignRecognizeStateSecond) {
        this.mTrafficSignRecognizeStateSecond = trafficSignRecognizeStateSecond;
    }

    public int getUrbanAIAssistanceStateR() {
        return this.mUrbanAIAssistanceStateR;
    }

    public void setUrbanAIAssistanceStateR(int urbanAIAssistanceStateR) {
        this.mUrbanAIAssistanceStateR = urbanAIAssistanceStateR;
    }

    public int getUrbanAIAssistanceStateS() {
        return this.mUrbanAIAssistanceStateS;
    }

    public void setUrbanAIAssistanceStateS(int urbanAIAssistanceStateS) {
        this.mUrbanAIAssistanceStateS = urbanAIAssistanceStateS;
    }

    public int getVerifyModeR() {
        return this.mVerifyModeR;
    }

    public void setVerifyModeR(int verifyModeR) {
        this.mVerifyModeR = verifyModeR;
    }

    public int getVerifyModeS() {
        return this.mVerifyModeS;
    }

    public void setVerifyModeS(int verifyModeS) {
        this.mVerifyModeS = verifyModeS;
    }

    public int getCpdDelayStatusR() {
        return this.mCpdDelayStatusR;
    }

    public void setCpdDelayStatusR(int cpdDelayStatusR) {
        this.mCpdDelayStatusR = cpdDelayStatusR;
    }

    public int getCpdStatusR() {
        return this.mCpdStatusR;
    }

    public void setCpdStatusR(int cpdStatusR) {
        this.mCpdStatusR = cpdStatusR;
    }

    public int getCpdStatusS() {
        return this.mCpdStatusS;
    }

    public void setCpdStatusS(int cpdStatusS) {
        this.mCpdStatusS = cpdStatusS;
    }

    public int getDmsSwitchStatusR() {
        return this.mDmsSwitchStatusR;
    }

    public void setDmsSwitchStatusR(int dmsSwitchStatusR) {
        this.mDmsSwitchStatusR = dmsSwitchStatusR;
    }

    public int getDmsSwitchStatusS() {
        return this.mDmsSwitchStatusS;
    }

    public void setDmsSwitchStatusS(int dmsSwitchStatusS) {
        this.mDmsSwitchStatusS = dmsSwitchStatusS;
    }

    public int getLdwSensitivityR() {
        return this.mLdwSensitivityR;
    }

    public void setLdwSensitivityR(int ldwSensitivityR) {
        this.mLdwSensitivityR = ldwSensitivityR;
    }
}
