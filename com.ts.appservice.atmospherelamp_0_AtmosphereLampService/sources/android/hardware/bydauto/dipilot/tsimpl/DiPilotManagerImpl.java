package android.hardware.bydauto.dipilot.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto_test.TestDiPilotManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DiPilotManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final int S_INVALID = -1;
    private static final String S_KEY_FOR_TEST = "key_for_test_DiPilotManagerImpl";
    protected static final String TAG = "DiPilotManagerImpl";
    private static DiPilotManagerImpl sInstance;
    private int keepAssistanceNum;
    private AbsBYDAutoDiPilotListener mAbsBydAutoDiPilotListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private int mGetNumChange;
    private int mNumChange;
    private TestDiPilotManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoDiPilotListener> mAbsBYDAutoDiPilotListeners = new CopyOnWriteArrayList<>();
    private int mStatus = 0;
    private int mOnline = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    DiPilotManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        ContentResolver contentResolver = con.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "DiPilotManagerImpl mForTestValue is" + this.mForTest + " execute return");
                this.mTestCallback = new TestDiPilotManagerImplCallback();
                return;
            }
        }
        this.mTsManagerImpl = TsManagerImpl.getInstance(con);
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_COMMON);
        this.mTsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.dipilot.tsimpl.DiPilotManagerImpl.1
            public void onAssistChanged(int id, int statusId, CarAssistPacket packet) {
                if (!DiPilotManagerImpl.this.mAbsBYDAutoDiPilotListeners.isEmpty()) {
                    try {
                        DiPilotManagerImpl.this.assistCallback(id, statusId, packet);
                    } catch (Exception e) {
                        Log.e(DiPilotManagerImpl.TAG, "assistCallback is error");
                    }
                }
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(DiPilotManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int id, int statusId, CarAssistPacket packet) {
        Log.i(TAG, "AssistCallback propertyId:" + id);
        switch (id) {
            case 557845261:
                int onRemote = packet.getRemoteControlDriveCloseTops();
                for (AbsBYDAutoDiPilotListener listener : this.mAbsBYDAutoDiPilotListeners) {
                    listener.onRemoteControlDriveCloseTops(onRemote);
                }
                break;
            case 557845271:
                for (AbsBYDAutoDiPilotListener listener2 : this.mAbsBYDAutoDiPilotListeners) {
                    listener2.onLowHODToastChanged(packet.getLowHodToastR());
                }
                break;
            case 557856077:
            case 557856078:
            case 557856079:
            case 557856080:
            case 557856081:
            case 557856082:
            case 557856083:
            case 557856084:
            case 557856085:
            case 557856086:
            case 557856087:
            case 557856088:
            case 557856089:
            case 557856090:
            case 557856094:
            case 557856095:
            case 557856097:
            case 557856098:
            case 557856106:
            case 557870336:
                if (statusId == 2) {
                    this.mOnline = 0;
                } else {
                    this.mOnline = 1;
                }
                break;
            case 557856768:
                int hightState = packet.getmHighSpeedAINavigationStateR();
                for (AbsBYDAutoDiPilotListener listener3 : this.mAbsBYDAutoDiPilotListeners) {
                    listener3.onHighSpeedAINavigationStateChanged(hightState);
                }
                break;
            case 557856770:
                int urbanState = packet.getmUrbanAIAssistanceStateR();
                for (AbsBYDAutoDiPilotListener listener4 : this.mAbsBYDAutoDiPilotListeners) {
                    listener4.onUrbanAIAssistanceStateChanged(urbanState);
                }
                break;
            case 557856772:
                int e2State = packet.getmE2EAIAssistanceStateR();
                for (AbsBYDAutoDiPilotListener listener5 : this.mAbsBYDAutoDiPilotListeners) {
                    listener5.onE2EAIAssistanceStateChanged(e2State);
                }
                break;
            case 557856774:
                int interState = packet.getmInteractiveLaneChangeAssistanceStateR();
                for (AbsBYDAutoDiPilotListener listener6 : this.mAbsBYDAutoDiPilotListeners) {
                    listener6.onInteractiveLaneChangeAssistanceStateChanged(interState);
                }
                break;
            case 557856776:
                int nagaValue = packet.getmAINavigationStateR();
                for (AbsBYDAutoDiPilotListener listener7 : this.mAbsBYDAutoDiPilotListeners) {
                    listener7.onAINavigationStateChanged(nagaValue);
                }
                break;
            case 557856778:
                int laneValue = packet.getmLaneAssistTypeR();
                for (AbsBYDAutoDiPilotListener listener8 : this.mAbsBYDAutoDiPilotListeners) {
                    listener8.onLaneAssistTypeChanged(laneValue);
                }
                break;
            case 557856780:
                int alarmState = packet.getmAlarmTypeR();
                for (AbsBYDAutoDiPilotListener listener9 : this.mAbsBYDAutoDiPilotListeners) {
                    listener9.onAlarmTypeChanged(alarmState);
                }
                break;
            case 557856782:
                int traFirstState = packet.getmTrafficSignRecognizeStateFirst();
                for (AbsBYDAutoDiPilotListener listener10 : this.mAbsBYDAutoDiPilotListeners) {
                    listener10.onTrafficSignRecognize2D5StateChanged(traFirstState);
                }
                break;
            case 557856783:
                int traSecondState = packet.getmTrafficSignRecognizeStateSecond();
                for (AbsBYDAutoDiPilotListener listener11 : this.mAbsBYDAutoDiPilotListeners) {
                    listener11.onTrafficSignRecognize2B4StateChanged(traSecondState);
                }
                break;
            case 557856784:
                int thirdSpeedLimitState = packet.getmTrafficSignRecognizeStateR();
                for (AbsBYDAutoDiPilotListener listener12 : this.mAbsBYDAutoDiPilotListeners) {
                    listener12.onTrafficSignRecognize316StateChanged(thirdSpeedLimitState);
                }
                break;
            case 557856786:
                int firstSpeedLimitState = packet.getmSpeedLimitRangeFirst();
                for (AbsBYDAutoDiPilotListener listener13 : this.mAbsBYDAutoDiPilotListeners) {
                    listener13.onSpeedLimitRange2D5Changed(firstSpeedLimitState);
                }
                break;
            case 557856787:
                int secondSpeedLimitState = packet.getmSpeedLimitRangeSecond();
                for (AbsBYDAutoDiPilotListener listener14 : this.mAbsBYDAutoDiPilotListeners) {
                    listener14.onSpeedLimitRange2B4Changed(secondSpeedLimitState);
                }
                break;
            case 557856789:
                int limitAlertStateFirst = packet.getmIntelligentSpeedLimitAlertStateFirst();
                for (AbsBYDAutoDiPilotListener listener15 : this.mAbsBYDAutoDiPilotListeners) {
                    listener15.onIntelligentSpeedLimitAlert2D5StateChanged(limitAlertStateFirst);
                }
                break;
            case 557856790:
                int limitAlertStateSecond = packet.getmIntelligentSpeedLimitAlertStateSecond();
                for (AbsBYDAutoDiPilotListener listener16 : this.mAbsBYDAutoDiPilotListeners) {
                    listener16.onIntelligentSpeedLimitAlert2B4StateChanged(limitAlertStateSecond);
                }
                break;
            case 557856792:
                int secondIntelligentSpeed = packet.getmIntelligentSpeedLimitControlStateSecond();
                for (AbsBYDAutoDiPilotListener listener17 : this.mAbsBYDAutoDiPilotListeners) {
                    listener17.onIntelligentSpeedLimitControl32FStateChanged(secondIntelligentSpeed);
                }
                break;
            case 557856794:
                int verifyMode = packet.getmVerifyModeR();
                for (AbsBYDAutoDiPilotListener listener18 : this.mAbsBYDAutoDiPilotListeners) {
                    listener18.onVerifyModeChanged(verifyMode);
                }
                break;
            case 557856796:
                int careState = packet.getmAiCareStateR();
                for (AbsBYDAutoDiPilotListener listener19 : this.mAbsBYDAutoDiPilotListeners) {
                    listener19.onAiCareStateChanged(careState);
                }
                break;
            case 557856798:
                int mistakeState = packet.getmMistakeGasStateR();
                for (AbsBYDAutoDiPilotListener listener20 : this.mAbsBYDAutoDiPilotListeners) {
                    listener20.onMistakeGasStateChanged(mistakeState);
                }
                break;
            case 557856800:
                int firstMatState = packet.getmAiMatrixLightStateFirst();
                for (AbsBYDAutoDiPilotListener listener21 : this.mAbsBYDAutoDiPilotListeners) {
                    listener21.onAiMatrixLight3ACStateChanged(firstMatState);
                }
                break;
            case 557856801:
                int secondMatState = packet.getmAiMatrixLightStateSecond();
                for (AbsBYDAutoDiPilotListener listener22 : this.mAbsBYDAutoDiPilotListeners) {
                    listener22.onAiMatrixLight3ADStateChanged(secondMatState);
                }
                break;
            case 557856804:
                int secondDisState = packet.getmAiDistanceLightStateSecond();
                for (AbsBYDAutoDiPilotListener listener23 : this.mAbsBYDAutoDiPilotListeners) {
                    listener23.onAiDistanceLight316StateChanged(secondDisState);
                }
                break;
            case 557856806:
                int firstTraLight = packet.getmTrafficLightRecognizeStateFirst();
                for (AbsBYDAutoDiPilotListener listener24 : this.mAbsBYDAutoDiPilotListeners) {
                    listener24.onTrafficLightRecognize2D5StateChanged(firstTraLight);
                }
                break;
            case 557856809:
                int firstAssistState = packet.getmEmergencyLaneAssistanceStateFirst();
                for (AbsBYDAutoDiPilotListener listener25 : this.mAbsBYDAutoDiPilotListeners) {
                    listener25.onEmergencyLaneAssistance32FStateChanged(firstAssistState);
                }
                break;
            case 557856810:
                int secondAssistState = packet.getmEmergencyLaneAssistanceStateSecond();
                for (AbsBYDAutoDiPilotListener listener26 : this.mAbsBYDAutoDiPilotListeners) {
                    listener26.onEmergencyLaneAssistance2F0StateChanged(secondAssistState);
                }
                break;
            case 557856812:
                int firstBlind = packet.getmBlindMonitorStateFirst();
                for (AbsBYDAutoDiPilotListener listener27 : this.mAbsBYDAutoDiPilotListeners) {
                    listener27.onBlindMonitorStateChanged1(firstBlind);
                }
                break;
            case 557856813:
                int secondBlind = packet.getmBlindMonitorStateSecond();
                for (AbsBYDAutoDiPilotListener listener28 : this.mAbsBYDAutoDiPilotListeners) {
                    listener28.onBlindMonitorStateChanged2(secondBlind);
                }
                break;
            case 557856815:
                int firstOpen = packet.getmOpenDoorAlertStateR();
                for (AbsBYDAutoDiPilotListener listener29 : this.mAbsBYDAutoDiPilotListeners) {
                    listener29.onOpenDoorAlertStateChanged(firstOpen);
                }
                break;
            case 557856817:
                int rearState = packet.getmRearCrashAlertStateR();
                for (AbsBYDAutoDiPilotListener listener30 : this.mAbsBYDAutoDiPilotListeners) {
                    listener30.onRearCrashAlertStateChanged(rearState);
                }
                break;
            case 557856819:
                int crossState = packet.getmRearTrafficCrossAlertStateR();
                for (AbsBYDAutoDiPilotListener listener31 : this.mAbsBYDAutoDiPilotListeners) {
                    listener31.onRearTrafficCrossAlertStateChanged(crossState);
                }
                break;
            case 557856821:
                int brakeState = packet.getmRearTrafficBrakeAlertStateR();
                for (AbsBYDAutoDiPilotListener listener32 : this.mAbsBYDAutoDiPilotListeners) {
                    listener32.onRearTrafficBrakeAlertStateChanged(brakeState);
                }
                break;
            case 557856823:
                int emState = packet.getmAiEmergencyBrakeStateR();
                for (AbsBYDAutoDiPilotListener listener33 : this.mAbsBYDAutoDiPilotListeners) {
                    listener33.onAiEmergencyBrakeStateChanged(emState);
                }
                break;
            case 557856825:
                int preState = packet.getmPredictCrashAlertStateR();
                for (AbsBYDAutoDiPilotListener listener34 : this.mAbsBYDAutoDiPilotListeners) {
                    listener34.onPredictCrashAlertStateChange(preState);
                }
                break;
            case 557856827:
                int firstPre = packet.getmPredictCrashAlertModeRFirst();
                for (AbsBYDAutoDiPilotListener listener35 : this.mAbsBYDAutoDiPilotListeners) {
                    listener35.onPredictCrashAlert138ModeChanged(firstPre);
                }
                break;
            case 557856828:
                int secondPre = packet.getmPredictCrashAlertModeRSecond();
                for (AbsBYDAutoDiPilotListener listener36 : this.mAbsBYDAutoDiPilotListeners) {
                    listener36.onPredictCrashAlert32DModeChanged(secondPre);
                }
                break;
            case 557856830:
                int frontState = packet.getmFrontTrafficCrossAlertStateR();
                for (AbsBYDAutoDiPilotListener listener37 : this.mAbsBYDAutoDiPilotListeners) {
                    listener37.onFrontTrafficCrossAlertStateChanged(frontState);
                }
                break;
            case 557856832:
                for (AbsBYDAutoDiPilotListener listener38 : this.mAbsBYDAutoDiPilotListeners) {
                    listener38.onFrontTrafficBrakeAlertStateChanged(packet.getmFrontTrafficBrakeAlertStateR());
                }
                break;
            case 557856835:
                int steepState = packet.getmSteepDescentSlowDownStateR();
                for (AbsBYDAutoDiPilotListener listener39 : this.mAbsBYDAutoDiPilotListeners) {
                    listener39.onSteepDescentSlowDownStateChanged(steepState);
                }
                break;
            case 557856838:
                int elecCarBody = packet.getElecCarBodyStablySysStateChanged();
                for (AbsBYDAutoDiPilotListener listener40 : this.mAbsBYDAutoDiPilotListeners) {
                    listener40.onElecCarBodyStablySysStateChanged(elecCarBody);
                }
                break;
            case 557856839:
                int firstControl = packet.getIntelligentSpeedLimitControlStateFirst();
                for (AbsBYDAutoDiPilotListener listener41 : this.mAbsBYDAutoDiPilotListeners) {
                    listener41.onIntelligentSpeedLimitControl2D5StateChanged(firstControl);
                }
                break;
            case 557856840:
                int cpdStatus = packet.getCpdDelayStatusR();
                Log.v(TAG, "cpdStatus:" + cpdStatus);
                for (AbsBYDAutoDiPilotListener listener42 : this.mAbsBYDAutoDiPilotListeners) {
                    listener42.onCpdStatusChanged(cpdStatus);
                }
                break;
            case 557856842:
                int delayStatus = packet.getCpdDelayStatusR();
                Log.v(TAG, "delayStatus:" + delayStatus);
                for (AbsBYDAutoDiPilotListener listener43 : this.mAbsBYDAutoDiPilotListeners) {
                    listener43.onCpdDelayStatusChanged(delayStatus);
                }
                break;
            case 557856843:
                int dmsSwitchStatus = packet.getDmsSwitchStatusR();
                Log.v(TAG, "dmsSwitchStatus:" + dmsSwitchStatus);
                for (AbsBYDAutoDiPilotListener listener44 : this.mAbsBYDAutoDiPilotListeners) {
                    listener44.onDmsStatusChanged(dmsSwitchStatus);
                }
                break;
            case 557856845:
                for (AbsBYDAutoDiPilotListener listener45 : this.mAbsBYDAutoDiPilotListeners) {
                    listener45.onADASSoftStateChanged(packet.getmAdasSoftVersionR());
                }
                break;
            case 557856846:
                for (AbsBYDAutoDiPilotListener listener46 : this.mAbsBYDAutoDiPilotListeners) {
                    listener46.onTSRLitSpdChgSwitchStateChanged(packet.getmTsrLitSpdChgSwitchStateR());
                }
                break;
            case 557856848:
                for (AbsBYDAutoDiPilotListener listener47 : this.mAbsBYDAutoDiPilotListeners) {
                    listener47.onELKTakeOverStateChanged(packet.getmElkTakeOverStateR());
                }
                break;
            case 557856849:
                for (AbsBYDAutoDiPilotListener listener48 : this.mAbsBYDAutoDiPilotListeners) {
                    listener48.onTSRLitSpdChgInformStateChanged(packet.getmTsrLitSpdChgInformStateR());
                }
                break;
            case 557856850:
                for (AbsBYDAutoDiPilotListener listener49 : this.mAbsBYDAutoDiPilotListeners) {
                    listener49.onISLIWarningStateChanged(packet.getmIsliWarningStateR());
                }
                break;
            case 557856851:
                for (AbsBYDAutoDiPilotListener listener50 : this.mAbsBYDAutoDiPilotListeners) {
                    listener50.onLowSpeedEmergencyBrakingStateChanged(packet.getLowSpeedEmergencyBrakingStateR());
                }
                break;
            case 557856853:
                for (AbsBYDAutoDiPilotListener listener51 : this.mAbsBYDAutoDiPilotListeners) {
                    listener51.onSideDistanceWarningStateChanged(packet.getSideDistanceWarningStateR());
                }
                break;
            case 557856855:
                for (AbsBYDAutoDiPilotListener listener52 : this.mAbsBYDAutoDiPilotListeners) {
                    listener52.onCollisionWarningStateChanged(packet.getCollisionWarningStateR());
                }
                break;
            case 557856857:
                for (AbsBYDAutoDiPilotListener listener53 : this.mAbsBYDAutoDiPilotListeners) {
                    listener53.onIntersectionCollisionAlertStateChanged(packet.getIntersectionCollisionAlertStateR());
                }
                break;
            case 557856859:
                for (AbsBYDAutoDiPilotListener listener54 : this.mAbsBYDAutoDiPilotListeners) {
                    listener54.onDoNotPassWarningStateChanged(packet.getDoNotPassWarningStateR());
                }
                break;
            case 557856861:
                for (AbsBYDAutoDiPilotListener listener55 : this.mAbsBYDAutoDiPilotListeners) {
                    listener55.onEmergencyBrakeWarningStateChanged(packet.getEmergencyBrakeWarningStateR());
                }
                break;
            case 557856863:
                for (AbsBYDAutoDiPilotListener listener56 : this.mAbsBYDAutoDiPilotListeners) {
                    listener56.onTrafficIncidentWarningStateChanged(packet.getTrafficIncidentWarningStateR());
                }
                break;
            case 557856865:
                for (AbsBYDAutoDiPilotListener listener57 : this.mAbsBYDAutoDiPilotListeners) {
                    listener57.onAbnormalVehicleWarningStateChanged(packet.getAbnormalVehicleWarningStateR());
                }
                break;
            case 557856867:
                for (AbsBYDAutoDiPilotListener listener58 : this.mAbsBYDAutoDiPilotListeners) {
                    listener58.onControlLossWarningStateChanged(packet.getControlLossWarningStateR());
                }
                break;
            case 557856869:
                for (AbsBYDAutoDiPilotListener listener59 : this.mAbsBYDAutoDiPilotListeners) {
                    listener59.onTrafficJamWarningStateChanged(packet.getTrafficJamWarningStateR());
                }
                break;
            case 557856871:
                for (AbsBYDAutoDiPilotListener listener60 : this.mAbsBYDAutoDiPilotListeners) {
                    listener60.onEmergencyVehicleWarningStateChanged(packet.getEmergencyVehicleWarningStateR());
                }
                break;
            case 557856873:
                for (AbsBYDAutoDiPilotListener listener61 : this.mAbsBYDAutoDiPilotListeners) {
                    listener61.onTrafficSignalWarningStateChanged(packet.getTrafficSignalWarningStateR());
                }
                break;
            case 557856875:
                for (AbsBYDAutoDiPilotListener listener62 : this.mAbsBYDAutoDiPilotListeners) {
                    listener62.onTrafficLightInfoPushStateChanged(packet.getTrafficLightInfoPushStateR());
                }
                break;
            case 557856877:
                for (AbsBYDAutoDiPilotListener listener63 : this.mAbsBYDAutoDiPilotListeners) {
                    listener63.onRedLightViolationWarningStateChanged(packet.getRedLightViolationWarningStateR());
                }
                break;
            case 557856879:
                for (AbsBYDAutoDiPilotListener listener64 : this.mAbsBYDAutoDiPilotListeners) {
                    listener64.onGreenLightOptimalSpeedAdvisoryStateChanged(packet.getGreenLightOptimalSpeedAdvisoryStateR());
                }
                break;
            case 557856884:
                for (AbsBYDAutoDiPilotListener listener65 : this.mAbsBYDAutoDiPilotListeners) {
                    listener65.onMechanismPositionChanged(packet.getMechanismPositionR());
                }
                break;
            case 557856888:
                for (AbsBYDAutoDiPilotListener listener66 : this.mAbsBYDAutoDiPilotListeners) {
                    listener66.onFatigueMonitorChange(packet.getFatigueMonitorChangedR());
                }
                break;
            case 557856889:
                for (AbsBYDAutoDiPilotListener listener67 : this.mAbsBYDAutoDiPilotListeners) {
                    listener67.onBSISSwitchChange(packet.getBsisEnableR());
                }
                break;
            case 557856891:
                for (AbsBYDAutoDiPilotListener listener68 : this.mAbsBYDAutoDiPilotListeners) {
                    listener68.onBSISSystemChange(packet.getBsisFuncStatusR());
                }
                break;
            case 557856892:
                for (AbsBYDAutoDiPilotListener listener69 : this.mAbsBYDAutoDiPilotListeners) {
                    listener69.onMOISSwitchChange(packet.getMoisEnableR());
                }
                break;
            case 557856894:
                for (AbsBYDAutoDiPilotListener listener70 : this.mAbsBYDAutoDiPilotListeners) {
                    listener70.onMOISSystemChange(packet.getMoisFuncStateR());
                }
                break;
            case 557856896:
                int ncpdStatus = packet.getCpdStatusNewR();
                Log.v(TAG, "cpdStatus:" + ncpdStatus);
                for (AbsBYDAutoDiPilotListener listener71 : this.mAbsBYDAutoDiPilotListeners) {
                    listener71.onCpdStatusChanged(ncpdStatus);
                }
                break;
            case 557856897:
                for (AbsBYDAutoDiPilotListener listener72 : this.mAbsBYDAutoDiPilotListeners) {
                    listener72.onAdativeCruiseControlFault((byte) packet.getAdativeCruiseControlFaultR());
                }
                break;
            case 557856898:
                for (AbsBYDAutoDiPilotListener listener73 : this.mAbsBYDAutoDiPilotListeners) {
                    listener73.onLaneCenteringControlFault((byte) packet.getLaneCenteringControlFaultR());
                }
                break;
            case 557856899:
                for (AbsBYDAutoDiPilotListener listener74 : this.mAbsBYDAutoDiPilotListeners) {
                    listener74.onBlindFault((byte) packet.getBlindFaultR());
                }
                break;
            case 557856900:
                for (AbsBYDAutoDiPilotListener listener75 : this.mAbsBYDAutoDiPilotListeners) {
                    listener75.onDoorOpenFault((byte) packet.getDoorOpenFaultR());
                }
                break;
            case 557856901:
                for (AbsBYDAutoDiPilotListener listener76 : this.mAbsBYDAutoDiPilotListeners) {
                    listener76.onRearCrossAlartFault((byte) packet.getRearCrossAlartFaultR());
                }
                break;
            case 557856902:
                for (AbsBYDAutoDiPilotListener listener77 : this.mAbsBYDAutoDiPilotListeners) {
                    listener77.onRearCrossBrakeFault((byte) packet.getRearCrossBrakeFaultR());
                }
                break;
            case 557856903:
                for (AbsBYDAutoDiPilotListener listener78 : this.mAbsBYDAutoDiPilotListeners) {
                    listener78.onRearCollisionWarnFault((byte) packet.getRearCollisionWarnFaultR());
                }
                break;
            case 557856904:
                for (AbsBYDAutoDiPilotListener listener79 : this.mAbsBYDAutoDiPilotListeners) {
                    listener79.onFrontCrossAlarkFault((byte) packet.getFrontCrossAlarkFaultR());
                }
                break;
            case 557856905:
                for (AbsBYDAutoDiPilotListener listener80 : this.mAbsBYDAutoDiPilotListeners) {
                    listener80.onFrontCrossBrakeFault((byte) packet.getFrontCrossBrakeFaultR());
                }
                break;
            case 557856906:
                for (AbsBYDAutoDiPilotListener listener81 : this.mAbsBYDAutoDiPilotListeners) {
                    listener81.onInteractiveLaneChangeFault((byte) packet.getInteractiveLaneChangeFaultR());
                }
                break;
            case 557856907:
                for (AbsBYDAutoDiPilotListener listener82 : this.mAbsBYDAutoDiPilotListeners) {
                    listener82.onNrbanNaviFault((byte) packet.getNrbanNaviFaultR());
                }
                break;
            case 557856908:
                for (AbsBYDAutoDiPilotListener listener83 : this.mAbsBYDAutoDiPilotListeners) {
                    listener83.onHighNaviFault((byte) packet.getHighNaviFaultR());
                }
                break;
            case 557856909:
                for (AbsBYDAutoDiPilotListener listener84 : this.mAbsBYDAutoDiPilotListeners) {
                    listener84.onNavigationPilotFault((byte) packet.getNavigationPilotFaultR());
                }
                break;
            case 557856910:
                for (AbsBYDAutoDiPilotListener listener85 : this.mAbsBYDAutoDiPilotListeners) {
                    listener85.onAutoEmergencyFault((byte) packet.getAutoEmergencyFaultR());
                }
                break;
            case 557856911:
                for (AbsBYDAutoDiPilotListener listener86 : this.mAbsBYDAutoDiPilotListeners) {
                    listener86.onEmergencyLaneKeepFault((byte) packet.getEmergencyLaneKeepFaultR());
                }
                break;
            case 557856912:
                for (AbsBYDAutoDiPilotListener listener87 : this.mAbsBYDAutoDiPilotListeners) {
                    listener87.onIntelligentLimitFaultController((byte) packet.getIntelligentLimitFaultControllerR());
                }
                break;
            case 557856913:
                for (AbsBYDAutoDiPilotListener listener88 : this.mAbsBYDAutoDiPilotListeners) {
                    listener88.onLaneDeparturePreventionFault((byte) packet.getLaneDeparturePreventionFaultR());
                }
                break;
            case 557856914:
                for (AbsBYDAutoDiPilotListener listener89 : this.mAbsBYDAutoDiPilotListeners) {
                    listener89.onLaneDepartureWarnFault((byte) packet.getLaneDepartureWarnFaultR());
                }
                break;
            case 557856915:
                for (AbsBYDAutoDiPilotListener listener90 : this.mAbsBYDAutoDiPilotListeners) {
                    listener90.onLaneKeepFault((byte) packet.getLaneKeepFaultR());
                }
                break;
            case 557856916:
                for (AbsBYDAutoDiPilotListener listener91 : this.mAbsBYDAutoDiPilotListeners) {
                    listener91.onForwardCollisionWarnFault((byte) packet.getForwardCollisionWarnFaultR());
                }
                break;
            case 557856917:
                for (AbsBYDAutoDiPilotListener listener92 : this.mAbsBYDAutoDiPilotListeners) {
                    listener92.onAdaptiveFrontLightFault((byte) packet.getAdaptiveFrontLightFaultR());
                }
                break;
            case 557856918:
                for (AbsBYDAutoDiPilotListener listener93 : this.mAbsBYDAutoDiPilotListeners) {
                    listener93.onTrafficAttentionFault((byte) packet.getTrafficAttentionFaultR());
                }
                break;
            case 557856919:
                for (AbsBYDAutoDiPilotListener listener94 : this.mAbsBYDAutoDiPilotListeners) {
                    listener94.onTsrFault((byte) packet.getTsrFaultR());
                }
                break;
            case 557856920:
                for (AbsBYDAutoDiPilotListener listener95 : this.mAbsBYDAutoDiPilotListeners) {
                    listener95.onIntelligentLimitFaultCall((byte) packet.getIntelligentLimitFaultCallR());
                }
                break;
            case 557856921:
                for (AbsBYDAutoDiPilotListener listener96 : this.mAbsBYDAutoDiPilotListeners) {
                    listener96.onLimitSpeedChangeFault((byte) packet.getLimitSpeedChangeFaultR());
                }
                break;
            case 557856922:
                for (AbsBYDAutoDiPilotListener listener97 : this.mAbsBYDAutoDiPilotListeners) {
                    listener97.onCurveWarnFault((byte) packet.getCurveWarnFaultR());
                }
                break;
            case 557856923:
                for (AbsBYDAutoDiPilotListener listener98 : this.mAbsBYDAutoDiPilotListeners) {
                    listener98.onBSISFault((byte) packet.getBsisFaultR());
                }
                break;
            case 557856924:
                for (AbsBYDAutoDiPilotListener listener99 : this.mAbsBYDAutoDiPilotListeners) {
                    listener99.onMOISFault((byte) packet.getMoisFaultR());
                }
                break;
            case 557856929:
                for (AbsBYDAutoDiPilotListener listener100 : this.mAbsBYDAutoDiPilotListeners) {
                    listener100.onISLIWarningLevelSwitchStatusChanged(packet.getIsliWarningLevelSwitchStatusR());
                }
                break;
            case 557856931:
                for (AbsBYDAutoDiPilotListener listener101 : this.mAbsBYDAutoDiPilotListeners) {
                    listener101.onAutoParkingStateChanged(packet.getAutoParkingStateR());
                }
                break;
            case 557856933:
                for (AbsBYDAutoDiPilotListener listener102 : this.mAbsBYDAutoDiPilotListeners) {
                    listener102.onCpdWarningStateChanged(packet.getCpdWarningStateR());
                }
                break;
            case 557856934:
                for (AbsBYDAutoDiPilotListener listener103 : this.mAbsBYDAutoDiPilotListeners) {
                    listener103.onCpdWorkingStatusChanged(packet.getCpdWorkingStatusR());
                }
                break;
            case 557856935:
                for (AbsBYDAutoDiPilotListener listener104 : this.mAbsBYDAutoDiPilotListeners) {
                    listener104.onEspOffKeyStatusChanged(packet.getEspOffKeyStatusR());
                }
                break;
            case 557856936:
                for (AbsBYDAutoDiPilotListener listener105 : this.mAbsBYDAutoDiPilotListeners) {
                    listener105.onIOPStateChanged(packet.getIopStateR());
                }
                break;
            case 557856938:
                for (AbsBYDAutoDiPilotListener listener106 : this.mAbsBYDAutoDiPilotListeners) {
                    listener106.onSAOCStateChanged(packet.getSaocStateR());
                }
                break;
            case 557856940:
                for (AbsBYDAutoDiPilotListener listener107 : this.mAbsBYDAutoDiPilotListeners) {
                    listener107.onSLIFStateChanged(packet.getSlifStateR());
                }
                break;
            case 557856944:
                for (AbsBYDAutoDiPilotListener listener108 : this.mAbsBYDAutoDiPilotListeners) {
                    listener108.onLowIOPToastChanged(packet.getLowIopToastR());
                }
                break;
            case 557856945:
                for (AbsBYDAutoDiPilotListener listener109 : this.mAbsBYDAutoDiPilotListeners) {
                    listener109.onHighIOPToastChanged(packet.getHighIopToastR());
                }
                break;
            case 557856946:
                for (AbsBYDAutoDiPilotListener listener110 : this.mAbsBYDAutoDiPilotListeners) {
                    listener110.onACCStateChanged(packet.getAccStateR());
                }
                break;
            case 557856948:
                for (AbsBYDAutoDiPilotListener listener111 : this.mAbsBYDAutoDiPilotListeners) {
                    listener111.onFCSRStateChanged(packet.getFcsrStateR());
                }
                break;
            case 557856950:
                for (AbsBYDAutoDiPilotListener listener112 : this.mAbsBYDAutoDiPilotListeners) {
                    listener112.onFCSRFault(packet.getFcsrFaultR());
                }
                break;
            case 557870400:
                for (AbsBYDAutoDiPilotListener listener113 : this.mAbsBYDAutoDiPilotListeners) {
                    listener113.onHighHODToastChanged(packet.getHighHodToastR());
                }
                break;
            case 557870413:
                for (AbsBYDAutoDiPilotListener listener114 : this.mAbsBYDAutoDiPilotListeners) {
                    listener114.onAEBDecActiveChanged(packet.getAebDecActiveR());
                }
                break;
            case 557870415:
                for (AbsBYDAutoDiPilotListener listener115 : this.mAbsBYDAutoDiPilotListeners) {
                    listener115.onLKSFaultStateStateChanged(packet.getLksFaultStateR());
                }
                break;
            case 557870416:
                for (AbsBYDAutoDiPilotListener listener116 : this.mAbsBYDAutoDiPilotListeners) {
                    listener116.onLDWFaultStateChanged(packet.getLdwFaultStateR());
                }
                break;
            case 557870437:
                for (AbsBYDAutoDiPilotListener listener117 : this.mAbsBYDAutoDiPilotListeners) {
                    listener117.onDnpStatusChanged(packet.getDnpStatusR());
                }
                break;
            case 557870469:
                for (AbsBYDAutoDiPilotListener listener118 : this.mAbsBYDAutoDiPilotListeners) {
                    listener118.onAEBFaultStateStateChanged(packet.getAebFaultStateR());
                }
                break;
            case 557870470:
                for (AbsBYDAutoDiPilotListener listener119 : this.mAbsBYDAutoDiPilotListeners) {
                    listener119.onFCWFaultStateChanged(packet.getFcwFaultStateR());
                }
                break;
            case 557870471:
                for (AbsBYDAutoDiPilotListener listener120 : this.mAbsBYDAutoDiPilotListeners) {
                    listener120.onVRUFaultStateStateChanged(packet.getVruFaultStateR());
                }
                break;
            case 557870472:
                int firstDisState = packet.getmAiDistanceLightStateFirst();
                for (AbsBYDAutoDiPilotListener listener121 : this.mAbsBYDAutoDiPilotListeners) {
                    listener121.onAiDistanceLight2B4StateChanged(firstDisState);
                }
                break;
            case 557870505:
                int secondTraLight = packet.getmTrafficLightRecognizeStateSecond();
                for (AbsBYDAutoDiPilotListener listener122 : this.mAbsBYDAutoDiPilotListeners) {
                    listener122.onTrafficLightRecognize2B4StateChanged(secondTraLight);
                }
                break;
            case 557872897:
                for (AbsBYDAutoDiPilotListener listener123 : this.mAbsBYDAutoDiPilotListeners) {
                    listener123.onLDWSensitivityChange(packet.getLdwSensitivityR());
                }
                break;
        }
    }

    public static DiPilotManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (DiPilotManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new DiPilotManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is run. ");
        if (1 != this.mForTest) {
            return "";
        }
        Log.v(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is run. ");
        if (1 != this.mForTest) {
            return "";
        }
        Log.v(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getType() {
        Log.v(TAG, "getType is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFeatureList mForTestValue is" + this.mForTest + " execute return");
            return new int[0];
        }
        return new int[0];
    }

    public int getDrivieRemindState() {
        Log.v(TAG, "getDrivieRemindState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDrivieRemindState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setDrivieRemindState(int state) {
        Log.v(TAG, "setDrivieRemindState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setDrivieRemindState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(5000, state);
        return 0;
    }

    public int getDrivieDepartureState() {
        Log.v(TAG, "getDrivieDepartureState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDrivieDepartureState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setDrivieDeparturState(int state) {
        Log.v(TAG, "setDrivieDeparturState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setDrivieDeparturState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(5001, state);
        return 0;
    }

    public int getFrontCarStartState() {
        Log.v(TAG, "getFrontCarStartState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getFrontCarStartState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setFrontCarStartState(int state) {
        Log.v(TAG, "setFrontCarStartState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setFrontCarStartState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(5002, state);
        return 0;
    }

    public int getFaultHandlerState() {
        Log.v(TAG, "getFaultHandlerState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getFaultHandlerState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setFaultHandlerState(int state) {
        Log.v(TAG, "setFaultHandlerState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setFaultHandlerState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(5003, state);
        return 0;
    }

    public int getChargerCoverState() {
        Log.v(TAG, "getChargerCoverState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getChargerCoverState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setChargerCoverState(int state) {
        Log.v(TAG, "setChargerCoverState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setChargerCoverState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(5004, state);
        return 0;
    }

    public int getAiTurnLightState() {
        Log.v(TAG, "getAiTurnLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getAiTurnLightState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setAiTurnLightState(int state) {
        Log.v(TAG, "setAiTurnLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setAiTurnLightState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_TURN_LIGHT_STATE, state);
        return 0;
    }

    public int getRainOpenFogLightState() {
        Log.v(TAG, "getRainOpenFogLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getRainOpenFogLightState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setRainOpenFogLightState(int state) {
        Log.v(TAG, "setRainOpenFogLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setRainOpenFogLightState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_RAIN_OPEN_FOG_LIGHT_STATE, state);
        return 0;
    }

    public int getHighSpeedOpenWindowState() {
        Log.v(TAG, "getHighSpeedOpenWindowState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getHighSpeedOpenWindowState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setHighSpeedOpenWindowState(int state) {
        Log.v(TAG, "setHighSpeedOpenWindowState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setHighSpeedOpenWindowState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_HIGH_SPEED_OPEN_WINDOW_STATE, state);
        return 0;
    }

    public int getDriveLevel() {
        Log.v(TAG, "getDriveLevel is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDriveLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setDriveLevel(int value) {
        Log.v(TAG, "setDriveLevel is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setDriveLevel mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DRIVE_LEVEL, value);
        return 0;
    }

    public int getHighSpeedAINavigationState() {
        Log.v(TAG, "getHighSpeedAINavigationState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getHighSpeedAINavigationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getHighSpeedAINavigationState();
    }

    public int setHighSpeedAINavigationState(int state) {
        Log.v(TAG, "setHighSpeedAINavigationState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setHighSpeedAINavigationState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_HIGH_SPEED_AI_NAVIGATION_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setHighSpeedAINavigationState(state);
    }

    public int getUrbanAIAssistanceState() {
        Log.v(TAG, "getUrbanAIAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getUrbanAIAssistanceState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getUrbanAIAssistanceState();
    }

    public int setUrbanAIAssistanceState(int state) {
        Log.v(TAG, "setUrbanAIAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setUrbanAIAssistanceState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_URBAN_AI_ASSISTANCE_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setUrbanAIAssistanceState(state);
    }

    public int getE2EAIAssistanceState() {
        Log.v(TAG, "getE2EAIAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getE2EAIAssistanceState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getE2EAIAssistanceState();
    }

    public int setE2EAIAssistanceState(int state) {
        Log.v(TAG, "setE2EAIAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setE2EAIAssistanceState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_E2E_AI_ASSISTANCE_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setE2EAIAssistanceState(state);
    }

    public int getInteractiveLaneChangeAssistanceState() {
        Log.v(TAG, "getInteractiveLaneChangeAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getInteractiveLaneChangeAssistanceState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getInteractiveLaneChangeAssistanceState();
    }

    public int setInteractiveLaneChangeAssistanceState(int state) {
        Log.v(TAG, "setInteractiveLaneChangeAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setInteractiveLaneChangeAssistanceState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setInteractiveLaneChangeAssistanceState(state);
    }

    public int getAINavigationState() {
        Log.v(TAG, "getAINavigationState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAINavigationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAINavigationState();
    }

    public int setAINavigationState(int state) {
        Log.v(TAG, "setAINavigationState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAINavigationState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_NAVIGATION_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAINavigationState(state);
    }

    public int getLaneAssistType() {
        Log.v(TAG, "getLaneAssistType is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getLaneAssistType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLaneAssistType();
    }

    public int setLaneAssistType(int value) {
        Log.v(TAG, "setLaneAssistType is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_LANE_ASSIST_TYPE, value);
            Log.v(TAG, "setLaneAssistType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setLaneAssistType(value);
    }

    public int getAlarmType() {
        Log.v(TAG, "getAlarmType is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAlarmType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAlarmType();
    }

    public int setAlarmType(int value) {
        Log.v(TAG, "setAlarmType is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAlarmType mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_ALARM_TYPE, value);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAlarmType(value);
    }

    @Deprecated
    public int getTrafficSignRecognizeState() {
        Log.v(TAG, "getTrafficSignRecognizeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTrafficSignRecognizeState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficSignRecognizeState();
    }

    public int setTrafficSignRecognizeState(int state) {
        Log.v(TAG, "setTrafficSignRecognizeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setTrafficSignRecognizeState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_TRAFFIC_SIGN_RECOGNIZE_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficSignRecognizeState(state);
    }

    public int getAiCareState() {
        Log.v(TAG, "getAiCareState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAiCareState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAiCareState();
    }

    public int setAiCareState(int state) {
        Log.v(TAG, "setAiCareState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAiCareState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_CARE_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAiCareState(state);
    }

    public int getMistakeGasState() {
        Log.v(TAG, "getMistakeGasState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMistakeGasState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getMistakeGasState();
    }

    public int setMistakeGasState(int state) {
        Log.v(TAG, "setMistakeGasState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setMistakeGasState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_MISTAKE_GAS_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setMistakeGasState(state);
    }

    public int getAiMatrixLightState() {
        Log.v(TAG, "getAiMatrixLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAiMatrixLightState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAiMatrixLightState();
    }

    public int setAiMatrixLightState(int state) {
        Log.v(TAG, "setAiMatrixLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAiMatrixLightState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_MATRIX_LIGHT_STATE, 0);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAiMatrixLightState(state);
    }

    @Deprecated
    public int getAiDistanceLightState() {
        Log.v(TAG, "getAiDistanceLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAiDistanceLightState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return 0;
        }
        Log.e(TAG, "carAssistAdapter still connecting, please wait");
        return 0;
    }

    public int setAiDistanceLightState(int state) {
        Log.v(TAG, "setAiDistanceLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAiDistanceLightState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_DISTANCE_LIGHT_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAiDistanceLightState(state);
    }

    public int getTrafficLightRecognizeState() {
        Log.v(TAG, "getTrafficLightRecognizeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTrafficLightRecognizeState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return 0;
        }
        Log.e(TAG, "carAssistAdapter still connecting, please wait");
        return 0;
    }

    public int setTrafficLightRecognizeState(int state) {
        Log.v(TAG, "setTrafficLightRecognizeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setTrafficLightRecognizeState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_TRAFFIC_LIGHT_RECOGNIZE_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficLightRecognizeState(state);
    }

    @Deprecated
    public int getEmergencyLaneAssistanceState() {
        Log.v(TAG, "getEmergencyLaneAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEmergencyLaneAssistanceState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return 0;
        }
        Log.e(TAG, "carAssistAdapter still connecting, please wait");
        return 0;
    }

    public int setEmergencyLaneAssistanceState(int state) {
        Log.v(TAG, "setEmergencyLaneAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setEmergencyLaneAssistanceState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_EMERGENCY_LANE_ASSISTANCE_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setEmergencyLaneAssistanceState(state);
    }

    public int getBlindMonitorState() {
        Log.v(TAG, "getBlindMonitorState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBlindMonitorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getBlindMonitorState();
    }

    public int setBlindMonitorState(int state) {
        Log.v(TAG, "setBlindMonitorState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBlindMonitorState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_BLIND_MONITOR_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setBlindMonitorState(state);
    }

    public int getOpenDoorAlertState() {
        Log.v(TAG, "getOpenDoorAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getOpenDoorAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOpenDoorAlertState();
    }

    public int setOpenDoorAlertState(int state) {
        Log.v(TAG, "setOpenDoorAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setOpenDoorAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_OPEN_DOOR_ALERT_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setOpenDoorAlertState(state);
    }

    public int getRearCrashAlertState() {
        Log.v(TAG, "getRearCrashAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearCrashAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getRearCrashAlertState();
    }

    public int setRearCrashAlertState(int state) {
        Log.v(TAG, "setRearCrashAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearCrashAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_REAR_CRASH_ALERT_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setRearCrashAlertState(state);
    }

    public int getRearTrafficCrossAlertState() {
        Log.v(TAG, "getRearTrafficCrossAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearTrafficCrossAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getRearTrafficCrossAlertState();
    }

    public int setRearTrafficCrossAlertState(int state) {
        Log.v(TAG, "setRearTrafficCrossAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearTrafficCrossAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_REAR_TRAFFIC_CROSS_ALERT_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setRearTrafficCrossAlertState(state);
    }

    public int getRearTrafficBrakeAlertState() {
        Log.v(TAG, "getRearTrafficBrakeAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearTrafficBrakeAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getRearTrafficBrakeAlertState();
    }

    public int setRearTrafficBrakeAlertState(int state) {
        Log.v(TAG, "setRearTrafficBrakeAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearTrafficBrakeAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_REAR_TRAFFIC_BRAKE_ALERT_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setRearTrafficBrakeAlertState(state);
    }

    public int getAiEmergencyBrakeState() {
        Log.v(TAG, "getAiEmergencyBrakeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAiEmergencyBrakeState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAiEmergencyBrakeState();
    }

    public int setAiEmergencyBrakeState(int state) {
        Log.v(TAG, "setAiEmergencyBrakeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAiEmergencyBrakeState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_EMERGENCY_BRAKE_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAiEmergencyBrakeState(state);
    }

    public int getFrontTrafficCrossAlertState() {
        Log.v(TAG, "getFrontTrafficCrossAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFrontTrafficCrossAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getFrontTrafficCrossAlertState();
    }

    public int setFrontTrafficCrossAlertState(int state) {
        Log.v(TAG, "setFrontTrafficCrossAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setFrontTrafficCrossAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_FRONT_TRAFFIC_CROSS_ALERT_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFrontTrafficCrossAlertState(state);
    }

    public int getFrontTrafficBrakeAlertState() {
        Log.v(TAG, "getFrontTrafficBrakeAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFrontTrafficBrakeAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getFrontTrafficBrakeAlertState();
    }

    public int setFrontTrafficBrakeAlertState(int state) {
        Log.v(TAG, "setFrontTrafficBrakeAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setFrontTrafficBrakeAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_FRONT_TRAFFIC_BRAKE_ALERT_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFrontTrafficBrakeAlertState(state);
    }

    public int getElecCarBodyStablySysState() {
        Log.v(TAG, "getElecCarBodyStablySysState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElecCarBodyStablySysState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getElecCarBodyStablySysState();
    }

    public int setElecCarBodyStablySysState(int state) {
        Log.v(TAG, "setElecCarBodyStablySysState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setElecCarBodyStablySysState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_ELEC_CAR_BODY_STABLY_SYS_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setElecCarBodyStablySysState(state);
    }

    public int getAutoParkingState() {
        Log.v(TAG, "getAutoParkingState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoParkingState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAutoParkingState();
    }

    public int setAutoParkingState(int state) {
        Log.v(TAG, "setAutoParkingState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAutoParkingState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AUTO_PARKING_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAutoParkingState(state);
    }

    public int getSteepDescentSlowDownState() {
        Log.v(TAG, "getSteepDescentSlowDownState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSteepDescentSlowDownState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getSteepDescentSlowDownState();
    }

    public int setSteepDescentSlowDownState(int state) {
        Log.v(TAG, "setSteepDescentSlowDownState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setSteepDescentSlowDownState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_STEEP_DESCENT_SLOW_DOWN_STATE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSteepDescentSlowDownState(state);
    }

    public int getFatigueDetectionAidState() {
        Log.v(TAG, "getFatigueDetectionAidState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getFatigueDetectionAidState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setFatigueDetectionAidState(int state) {
        Log.v(TAG, "setFatigueDetectionAidState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setFatigueDetectionAidState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_FATIGUE_DETECTION_AID_STATE, state);
        return 0;
    }

    public int getRelieveFatigueState() {
        Log.v(TAG, "getRelieveFatigueState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getRelieveFatigueState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setRelieveFatigueState(int state) {
        Log.v(TAG, "setRelieveFatigueState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setRelieveFatigueState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_RELIEVE_FATIGUE_STATE, state);
        return 0;
    }

    public int getRecommendRestSpotState() {
        Log.v(TAG, "getRecommendRestSpotState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getRecommendRestSpotState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setRecommendRestSpotState(int state) {
        Log.v(TAG, "setRecommendRestSpotState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setRecommendRestSpotState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_RECOMMEND_REST_SPOT_STATE, state);
        return 0;
    }

    public int getTemperatureWhileFatigue() {
        Log.v(TAG, "getTemperatureWhileFatigue is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getTemperatureWhileFatigue mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setTemperatureWhileFatigue(int value) {
        Log.v(TAG, "setTemperatureWhileFatigue is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setTemperatureWhileFatigue mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_TEMPERATURE_WHILE_FATIGUE, value);
        return 0;
    }

    public int getDistractionDetectionAidState() {
        Log.v(TAG, "getDistractionDetectionAidState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDistractionDetectionAidState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setDistractionDetectionAidState(int state) {
        Log.v(TAG, "setDistractionDetectionAidState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setDistractionDetectionAidState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DISTRACTION_DETECTION_AID_STATE, state);
        return 0;
    }

    public int getAttentionDetectionState() {
        Log.v(TAG, "getAttentionDetectionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getAttentionDetectionState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setAttentionDetectionState(int state) {
        Log.v(TAG, "setAttentionDetectionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setAttentionDetectionState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_ATTENTION_DETECTION_STATE, state);
        return 0;
    }

    public int getDangerousActionDetectionState() {
        Log.v(TAG, "getDangerousActionDetectionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDangerousActionDetectionState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setDangerousActionDetectionState(int value) {
        Log.v(TAG, "setDangerousActionDetectionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setDangerousActionDetectionState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DANGEROUS_ACTION_DETECTION_STATE, value);
        return 0;
    }

    public int getGazePerceptionState() {
        Log.v(TAG, "getGazePerceptionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getGazePerceptionState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setGazePerceptionState(int state) {
        Log.v(TAG, "setGazePerceptionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setGazePerceptionState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_GAZE_PERCEPTION_STATE, state);
        return 0;
    }

    public int getChildCareState() {
        Log.v(TAG, "getChildCareState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getChildCareState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setChildCareState(int state) {
        Log.v(TAG, "setChildCareState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setChildCareState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_CHILD_CARE_STATE, state);
        return 0;
    }

    public int getGestureRecognitionState() {
        Log.v(TAG, "getGestureRecognitionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getGestureRecognitionState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setGestureRecognitionState(int state) {
        Log.v(TAG, "setGestureRecognitionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setGestureRecognitionState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_GESTURE_RECOGNITION_STATE, state);
        return 0;
    }

    public int getLeftBehindReminderState() {
        Log.v(TAG, "getLeftBehindReminderState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getLeftBehindReminderState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setLeftBehindReminderState(int state) {
        Log.v(TAG, "setLeftBehindReminderState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setLeftBehindReminderState mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_LEFT_BEHIND_REMINDER_STATE, state);
        return 0;
    }

    public int getDNPState() {
        Log.v(TAG, "getDNPState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDNPState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int requestHUNPClose() {
        Log.v(TAG, "requestHUNPClose is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_COMMON);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "requestHUNPClose mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.REQUEST_HUNP_CLOSE, 0);
        return 0;
    }

    public int getSupportIntelligentNavigation() {
        Log.v(TAG, "getSupportIntelligentNavigation is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportIntelligentNavigation mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportLaneAssistMode() {
        Log.v(TAG, "getSupportLaneAssistMode is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportLaneAssistMode mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportShakeAlert() {
        Log.v(TAG, "getSupportShakeAlert is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportShakeAlert mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportTrafficSignsRecognition() {
        Log.v(TAG, "getSupportTrafficSignsRecognition is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportTrafficSignsRecognition mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportPredictCrashAlert() {
        Log.v(TAG, "getSupportPredictCrashAlert is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportPredictCrashAlert mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportAiEmergencyBrake() {
        Log.v(TAG, "getSupportAiEmergencyBrake is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportAiEmergencyBrake mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportAiCare() {
        Log.v(TAG, "getSupportAiCare is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportAiCare mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportMistakeGas() {
        Log.v(TAG, "getSupportMistakeGas is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportMistakeGas mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportAutoParking() {
        Log.v(TAG, "getSupportAutoParking is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportAutoParking mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportBlindSpotAssist() {
        Log.v(TAG, "getSupportBlindSpotAssist is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportBlindSpotAssist mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportRearCrossBrake() {
        Log.v(TAG, "getSupportRearCrossBrake is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportRearCrossBrake mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportAiDistanceLight() {
        Log.v(TAG, "getSupportAiDistanceLight is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportAiDistanceLight mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportAiMatrixLight() {
        Log.v(TAG, "getSupportAiMatrixLight is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportAiMatrixLight mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportPanoramaImage() {
        Log.v(TAG, "getSupportPanoramaImage is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportPanoramaImage mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSupportESP() {
        Log.v(TAG, "getSupportESP is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getSupportESP mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void registerListener(AbsBYDAutoDiPilotListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoDiPilotListener listener, int[] featureIds) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe dipilot listener " + listener + " featureIDs = " + Arrays.toString(featureIds));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.v(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoDiPilotListener(listener);
        }
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_COMMON);
        if (!this.mAbsBYDAutoDiPilotListeners.contains(listener)) {
            this.mAbsBYDAutoDiPilotListeners.add(listener);
        }
        setFeatureIds(featureIds);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }

    public void unregisterListener(AbsBYDAutoDiPilotListener listener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_COMMON);
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoDiPilotListener(null);
        }
        if (listener == null) {
            return;
        }
        this.mAbsBYDAutoDiPilotListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getDriveRemindState() {
        Log.v(TAG, "getDriveRemindState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDriveRemindState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setDriveRemindState(int state) {
        Log.v(TAG, "setDriveRemindState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DRIVE_REMIND_STATE, state);
        Log.v(TAG, "setDriveRemindState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getDriveDepartureState() {
        Log.v(TAG, "getDriveDepartureState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDriveDepartureState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setDriveDeparturState(int state) {
        Log.v(TAG, "setDriveDeparturState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 != this.mForTest) {
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DRIVE_DEPARTUR_STATE, state);
        Log.v(TAG, "setDriveDeparturState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Deprecated
    public int getSpeedLimitRange() {
        Log.v(TAG, "getSpeedLimitRange is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSpeedLimitRange mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getSpeedLimitRangeFirst();
    }

    public int setSpeedLimitRange(int value) {
        Log.v(TAG, "setSpeedLimitRange is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_SPEED_LIMIT_RANGE, value);
            Log.v(TAG, "setSpeedLimitRange mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSpeedLimitRange(value);
    }

    @Deprecated
    public int getIntelligentSpeedLimitAlertState() {
        Log.v(TAG, "getIntelligentSpeedLimitAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentSpeedLimitAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return 0;
        }
        Log.e(TAG, "carAssistAdapter still connecting, please wait");
        return 0;
    }

    public int setIntelligentSpeedLimitAlertState(int state) {
        Log.v(TAG, "setIntelligentSpeedLimitAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_INTELLIGENT_SPEED_LIMIT_ALERT_STATE, state);
            Log.v(TAG, "setIntelligentSpeedLimitAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIntelligentSpeedLimitAlertState(state);
    }

    @Deprecated
    public int getIntelligentSpeedLimitControlState() {
        Log.v(TAG, "getIntelligentSpeedLimitControlState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentSpeedLimitControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return 0;
        }
        Log.e(TAG, "carAssistAdapter still connecting, please wait");
        return 0;
    }

    public int setIntelligentSpeedLimitControlState(int state) {
        Log.v(TAG, "setIntelligentSpeedLimitControlState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE, state);
            Log.v(TAG, "setIntelligentSpeedLimitControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIntelligentSpeedLimitControlState(state);
    }

    public int getVerifyMode() {
        Log.v(TAG, "getVerifyMode is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getVerifyMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getVerifyMode();
    }

    public int setVerifyMode(int mode) {
        Log.v(TAG, "setVerifyMode is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_VERIFY_MODE, mode);
            Log.v(TAG, "setVerifyMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setVerifyMode(mode);
    }

    public int getPredictCrashAlertState() {
        Log.v(TAG, "getPredictCrashAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getPredictCrashAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getPredictCrashAlertState();
    }

    public int setPredictCrashAlertState(int state) {
        Log.v(TAG, "setPredictCrashAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_PREDICT_CRASH_ALERT_STATE, state);
            Log.v(TAG, "setPredictCrashAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setPredictCrashAlertState(state);
    }

    public int setPredictCrashAlertMode(int state) {
        Log.v(TAG, "setPredictCrashAlertMode is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_PREDICT_CRASH_ALERT_STATE, state);
            Log.v(TAG, "setPredictCrashAlertMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setPredictCrashAlertMode(state);
    }

    public int getTrafficSignRecognize2D5State() {
        Log.v(TAG, "getTrafficSignRecognize2D5State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficSignRecognize2D5State();
    }

    public int getTrafficSignRecognize2B4State() {
        Log.v(TAG, "getTrafficSignRecognize2B4State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficSignRecognize2B4State();
    }

    public int getTrafficSignRecognize316State() {
        Log.v(TAG, "getTrafficSignRecognize316State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficSignRecognize316State();
    }

    public int getSpeedLimitRange2D5() {
        Log.v(TAG, "getSpeedLimitRange2D5 is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getSpeedLimitRange2D5();
    }

    public int getSpeedLimitRange2B4() {
        Log.v(TAG, "getSpeedLimitRange2B4 is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getSpeedLimitRange2B4();
    }

    public int getIntelligentSpeedLimitAlert2D5State() {
        Log.v(TAG, "getIntelligentSpeedLimitAlert2D5State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIntelligentSpeedLimitAlert2D5State();
    }

    public int getIntelligentSpeedLimitAlert2B4State() {
        Log.v(TAG, "getIntelligentSpeedLimitAlert2B4State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIntelligentSpeedLimitAlert2B4State();
    }

    public int getIntelligentSpeedLimitControl2D5State() {
        Log.v(TAG, "getIntelligentSpeedLimitControl2D5State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIntelligentSpeedLimitControl2D5State();
    }

    public int getIntelligentSpeedLimitControl32FState() {
        Log.v(TAG, "getIntelligentSpeedLimitControl32FState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIntelligentSpeedLimitControl32FState();
    }

    public int getAiDistanceLight2B4State() {
        Log.v(TAG, "getAiDistanceLight2B4State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAiDistanceLight2B4State();
    }

    public int getAiDistanceLight316State() {
        Log.v(TAG, "getAiDistanceLight316State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAiDistanceLight316State();
    }

    public int getTrafficLightRecognize2D5State() {
        Log.v(TAG, "getTrafficLightRecognize2D5State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficLightRecognize2D5State();
    }

    public int getTrafficLightRecognize2B4State() {
        Log.v(TAG, "getTrafficLightRecognize2B4State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficLightRecognize2B4State();
    }

    public int getEmergencyLaneAssistance32FState() {
        Log.v(TAG, "getEmergencyLaneAssistance32FState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getEmergencyLaneAssistance32FState();
    }

    public int getEmergencyLaneAssistance2F0State() {
        Log.v(TAG, "getEmergencyLaneAssistance2F0State is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getEmergencyLaneAssistance2F0State();
    }

    public int getPredictCrashAlert138Mode() {
        Log.v(TAG, "getPredictCrashAlert138Mode is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getPredictCrashAlert138Mode();
    }

    public int getPredictCrashAlert32DMode() {
        Log.v(TAG, "getPredictCrashAlert32DMode is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getPredictCrashAlert32DMode();
    }

    public int getAiMatrixLight3ACState() {
        Log.v(TAG, "getAiMatrixLight3ACState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAiMatrixLight3ACState();
    }

    public int getAiMatrixLight3ADState() {
        Log.v(TAG, "getAiMatrixLight3ADState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAiMatrixLight3ADState();
    }

    public int getBlindMonitorState1() {
        Log.v(TAG, "getBlindMonitorState1 is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        return this.mTsManagerImpl.getCarAssistAdapterManager().getBlindMonitorState1();
    }

    public int getBlindMonitorState2() {
        Log.v(TAG, "getBlindMonitorState2 is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        return this.mTsManagerImpl.getCarAssistAdapterManager().getBlindMonitorState2();
    }

    public int setCpdStatus(int status) {
        Log.v(TAG, "setCpdStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setCpdStatus(status);
    }

    public int setDmsSwitchStatus(int status) {
        Log.v(TAG, "setDmsSwitchStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDmsSwitchStatus(status);
    }

    public int getCpdStatus() {
        Log.v(TAG, "getCpdStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getCpdStatus();
    }

    public int getCpdDelayStatus() {
        Log.v(TAG, "getCpdDelayStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getCpdDelayStatus();
    }

    public int getDmsSwitchStatus() {
        Log.v(TAG, "getDmsSwitchStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getDmsSwitchStatus();
    }

    public int getADASOnlineState() {
        Log.v(TAG, "getADASOnlineState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mOnline;
    }

    public int getTsrLitSpdChgSwitchState() {
        Log.v(TAG, "getTsrLitSpdChgSwitchState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTsrLitSpdChgSwitchState();
    }

    public int setTsrLitSpdChgSwitchState(int state) {
        Log.v(TAG, "setTsrLitSpdChgSwitchState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTsrLitSpdChgSwitchState(state);
    }

    public int getElkTakeOverState() {
        Log.v(TAG, "getElkTakeOverState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getElkTakeOverState();
    }

    public int getTsrLitSpdChgInformState() {
        Log.v(TAG, "getTsrLitSpdChgInformState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTsrLitSpdChgInformState();
    }

    public int getIsliWarningState() {
        Log.v(TAG, "getIsliWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIsliWarningState();
    }

    public int getAdasSoftVersion() {
        Log.v(TAG, "getAdasSoftVersion is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAdasSoftVersion();
    }

    public int getLksFaultState() {
        Log.v(TAG, "getLksFaultState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLksFaultState();
    }

    public int getLdwFaultState() {
        Log.v(TAG, "getLdwFaultState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLdwFaultState();
    }

    public int getAebFaultState() {
        Log.v(TAG, "getAebFaultState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAebFaultState();
    }

    public int getFcwFaultState() {
        Log.v(TAG, "getFcwFaultState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getFcwFaultState();
    }

    public int getVruFaultState() {
        Log.v(TAG, "getVruFaultState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getVruFaultState();
    }

    public int getLowSpeedEmergencyBrakingState() {
        Log.v(TAG, "getLowSpeedEmergencyBrakingState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLowSpeedEmergencyBrakingState();
    }

    public int setLowSpeedEmergencyBrakingState(int state) {
        Log.v(TAG, "setLowSpeedEmergencyBrakingState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setLowSpeedEmergencyBrakingState(state);
    }

    public int getSideDistanceWarningState() {
        Log.v(TAG, "getSideDistanceWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getSideDistanceWarningState();
    }

    public int setSideDistanceWarningState(int state) {
        Log.v(TAG, "setSideDistanceWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSideDistanceWarningState(state);
    }

    public int getCollisionWarningState() {
        Log.v(TAG, "getCollisionWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getCollisionWarningState();
    }

    public int setCollisionWarningState(int state) {
        Log.v(TAG, "setCollisionWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setCollisionWarningState1(state);
    }

    public int getIntersectionCollisionAlertState() {
        Log.v(TAG, "getIntersectionCollisionAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIntersectionCollisionAlertState();
    }

    public int setIntersectionCollisionAlertState(int state) {
        Log.v(TAG, "setIntersectionCollisionAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIntersectionCollisionAlertState(state);
    }

    public int getDoNotPassWarningState() {
        Log.v(TAG, "getDoNotPassWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getDoNotPassWarningState();
    }

    public int setDoNotPassWarningState(int state) {
        Log.v(TAG, "setDoNotPassWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDoNotPassWarningState(state);
    }

    public int getEmergencyBrakeWarningState() {
        Log.v(TAG, "getEmergencyBrakeWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getEmergencyBrakeWarningState();
    }

    public int setEmergencyBrakeWarningState(int state) {
        Log.v(TAG, "setEmergencyBrakeWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setEmergencyBrakeWarningState(state);
    }

    public int getTrafficIncidentWarningState() {
        Log.v(TAG, "getTrafficIncidentWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficIncidentWarningState();
    }

    public int setTrafficIncidentWarningState(int state) {
        Log.v(TAG, "setTrafficIncidentWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficIncidentWarningState(state);
    }

    public int getAbnormalVehicleWarningState() {
        Log.v(TAG, "getAbnormalVehicleWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAbnormalVehicleWarningState();
    }

    public int setAbnormalVehicleWarningState(int state) {
        Log.v(TAG, "setAbnormalVehicleWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAbnormalVehicleWarningState(state);
    }

    public int getControlLossWarningState() {
        Log.v(TAG, "getControlLossWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getControlLossWarningState();
    }

    public int setControlLossWarningState(int state) {
        Log.v(TAG, "setControlLossWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setControlLossWarningState(state);
    }

    public int getTrafficJamWarningState() {
        Log.v(TAG, "getTrafficJamWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficJamWarningState();
    }

    public int setTrafficJamWarningState(int state) {
        Log.v(TAG, "setTrafficJamWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficJamWarningState(state);
    }

    public int getEmergencyVehicleWarningState() {
        Log.v(TAG, "getEmergencyVehicleWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getEmergencyVehicleWarningState();
    }

    public int setEmergencyVehicleWarningState(int state) {
        Log.v(TAG, "setEmergencyVehicleWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setEmergencyVehicleWarningState(state);
    }

    public int getTrafficSignalWarningState() {
        Log.v(TAG, "getTrafficSignalWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficSignalWarningState();
    }

    public int setTrafficSignalWarningState(int state) {
        Log.v(TAG, "setTrafficSignalWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficSignalWarningState(state);
    }

    public int getTrafficLightInfoPushState() {
        Log.v(TAG, "getTrafficLightInfoPushState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficLightInfoPushState();
    }

    public int setTrafficLightInfoPushState(int state) {
        Log.v(TAG, "setTrafficLightInfoPushState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficLightInfoPushState(state);
    }

    public int getRedLightViolationWarningState() {
        Log.v(TAG, "getRedLightViolationWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getRedLightViolationWarningState();
    }

    public int setRedLightViolationWarningState(int state) {
        Log.v(TAG, "setRedLightViolationWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setRedLightViolationWarningState(state);
    }

    public int getGreenLightOptimalSpeedAdvisoryState() {
        Log.v(TAG, "getGreenLightOptimalSpeedAdvisoryState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getGreenLightOptimalSpeedAdvisoryState();
    }

    public int setGreenLightOptimalSpeedAdvisoryState(int state) {
        Log.v(TAG, "setGreenLightOptimalSpeedAdvisoryState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setGreenLightOptimalSpeedAdvisoryState(state);
    }

    public int setFatigueMonitoringStatus(int status) {
        Log.v(TAG, "setFatigueMonitoringStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFatigueMonitoringStatus(status);
    }

    public int setDmsSelfCheckStatus(int status) {
        Log.v(TAG, "setDmsSelfCheckStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDmsSelfCheckStatus(status);
    }

    public int setDistractionMonitoringAttention(int status) {
        Log.v(TAG, "setDistractionMonitoringAttention is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDistractionMonitoringAttention(status);
    }

    public int getMechanismPosition() {
        Log.v(TAG, "getMechanismPosition is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getMechanismPosition();
    }

    public int getDnpStatus() {
        Log.v(TAG, "getDnpStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getDnpStatus();
    }

    public int setFatigueReliefSwitch(int state) {
        Log.v(TAG, "setFatigueReliefSwitch is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFatigueReliefSwitch(state);
    }

    public int setDangerousActionfSwitch(int state) {
        Log.v(TAG, "setDangerousActionfSwitch is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDangerousActionfSwitch(state);
    }

    public int setLdwStaticCalibration(int value) {
        Log.v(TAG, "setLdwStaticCalibration is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        this.mTsManagerImpl.getCarAssistAdapterManager().setLdwStaticCalibration(value);
        return 0;
    }

    public int getBsisEnableR() {
        Log.i(TAG, "getBsisEnableR is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "BsisEnableR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getBsisEnableR();
    }

    public int getBsisFuncStatusR() {
        Log.i(TAG, "getBsisFuncStatusR is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "BsisFuncStatusR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getBsisFuncStatusR();
    }

    public int getMoisEnableR() {
        Log.i(TAG, "getMoisEnableR is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "MoisEnableR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getMoisEnableR();
    }

    public int getMoisFuncStateR() {
        Log.i(TAG, "getMoisFuncStateR is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "MoisFuncStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getMoisFuncStateR();
    }

    public int getSteepDescentSlowDownState4A9R() {
        Log.i(TAG, "getSteepDescentSlowDownState4A9R is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "SteepDescentSlowDownState4A9R mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getSteepDescentSlowDownState4A9R();
    }

    public int setBsisCollicionAlarmSwitchS(int arg0) {
        Log.i(TAG, "setBsisCollicionAlarmSwitchS is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBsisCollicionAlarmSwitchS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setBsisCollicionAlarmSwitchS(arg0);
    }

    public int setMoisFunctionSwitchS(int arg0) {
        Log.i(TAG, "setMoisFunctionSwitchS is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setMoisFunctionSwitchS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setMoisFunctionSwitchS(arg0);
    }

    public int getLdwSensitivity() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLdwSensitivity mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLdwSensitivity();
    }

    public byte getAdativeCruiseControlFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAdativeCruiseControlFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAdativeCruiseControlFault();
    }

    public byte getLaneCenteringControlFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLaneCenteringControlFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLaneCenteringControlFault();
    }

    public byte getBlindFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBlindFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getBlindFault();
    }

    public byte getDoorOpenFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDoorOpenFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getDoorOpenFault();
    }

    public byte getRearCrossAlartFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRearCrossAlartFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getRearCrossAlartFault();
    }

    public byte getRearCrossBrakeFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRearCrossBrakeFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getRearCrossBrakeFault();
    }

    public byte getRearCollisionWarnFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRearCollisionWarnFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getRearCollisionWarnFault();
    }

    public byte getFrontCrossAlarkFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontCrossAlarkFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getFrontCrossAlarkFault();
    }

    public byte getFrontCrossBrakeFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontCrossBrakeFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getFrontCrossBrakeFault();
    }

    public byte getInteractiveLaneChangeFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getInteractiveLaneChangeFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getInteractiveLaneChangeFault();
    }

    public byte getNrbanNaviFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getNrbanNaviFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getNrbanNaviFault();
    }

    public byte getHighNaviFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHighNaviFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getHighNaviFault();
    }

    public byte getNavigationPilotFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getNavigationPilotFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getNavigationPilotFault();
    }

    public byte getAutoEmergencyFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAutoEmergencyFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAutoEmergencyFault();
    }

    public byte getEmergencyLaneKeepFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getEmergencyLaneKeepFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getEmergencyLaneKeepFault();
    }

    public byte getIntelligentLimitFaultController() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIntelligentLimitFaultController mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIntelligentLimitFaultController();
    }

    public byte getLaneDeparturePreventionFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLaneDeparturePreventionFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLaneDeparturePreventionFault();
    }

    public byte getLaneDepartureWarnFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLaneDepartureWarnFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLaneDepartureWarnFault();
    }

    public byte getLaneKeepFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLaneKeepFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLaneKeepFault();
    }

    public byte getForwardCollisionWarnFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getForwardCollisionWarnFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getForwardCollisionWarnFault();
    }

    public byte getAdaptiveFrontLightFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAdaptiveFrontLightFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAdaptiveFrontLightFault();
    }

    public byte getTrafficAttentionFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTrafficAttentionFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTrafficAttentionFault();
    }

    public byte getTsrFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTsrFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getTsrFault();
    }

    public byte getIntelligentLimitFaultCall() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIntelligentLimitFaultCall mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIntelligentLimitFaultCall();
    }

    public byte getLimitSpeedChangeFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLimitSpeedChangeFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getLimitSpeedChangeFault();
    }

    public byte getCurveWarnFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCurveWarnFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getCurveWarnFault();
    }

    public byte getBsisFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBsisFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getBsisFault();
    }

    public byte getMoisFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMoisFault mForTestValue is 0 execute return");
            return (byte) -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getMoisFault();
    }

    public int getAutoParkigAssistMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAutoParkigAssistMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAutoParkigAssistMode();
    }

    public int getAutoParkigAssistStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAutoParkigAssistStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAutoParkigAssistStatus();
    }

    public int getHomeZoneParkingAssistStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHomeZoneParkingAssistStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getHomeZoneParkingAssistStatus();
    }

    public int getAutomatedValetParkingStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAutomatedValetParkingStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAutomatedValetParkingStatus();
    }

    public int getIsliWarningLevelSwitchStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIsliWarningLevelSwitchStatus mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIsliWarningLevelSwitchStatus();
    }

    public int setIsliWarningLevelSwitchStatus(int status) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setIsliWarningLevelSwitchStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIsliWarningLevelSwitchStatus(status);
    }

    public int getCpdWarningState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCpdWarningState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getCpdWarningState();
    }

    public int getCpdWorkingStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCpdWorkingStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getCpdWorkingStatus();
    }

    public int getEspOffKeyStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getEspOffKeyStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getEspOffKeyStatus();
    }

    public int getAebDecActive() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAebDecActive mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAebDecActive();
    }

    public int getIopState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIopState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIopState();
    }

    public int setIopState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setIopState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIopState(state);
    }

    public int getSaocState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSaocState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getSaocState();
    }

    public int setSaocState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSaocState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSaocState(state);
    }

    public int getSlifState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSlifState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getSlifState();
    }

    public int setSlifState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSlifState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSlifState(state);
    }

    public int getAccState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAccState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getAccState();
    }

    public int setAccState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAccState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAccState(state);
    }

    public int getFcsrState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFcsrState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getFcsrState();
    }

    public int setFcsrState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setFcsrState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFcsrState(state);
    }

    public int getFcsrFault() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFcsrFault mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getFcsrFault();
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    switch (eventType) {
                        case 632:
                            bydAutoEventValue.intValue = getDmsSwitchStatus();
                            sparseArray.put(632, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 633:
                            bydAutoEventValue.intValue = getMistakeGasState();
                            sparseArray.put(633, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2826:
                            bydAutoEventValue.intValue = getAiMatrixLight3ACState();
                            sparseArray.put(2826, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2835:
                            bydAutoEventValue.intValue = getAiMatrixLight3ADState();
                            sparseArray.put(2835, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3133:
                            bydAutoEventValue.intValue = getAiEmergencyBrakeState();
                            sparseArray.put(3133, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3151:
                            bydAutoEventValue.intValue = getBlindMonitorState2();
                            sparseArray.put(3151, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3152:
                            bydAutoEventValue.intValue = getBlindMonitorState1();
                            sparseArray.put(3152, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3164:
                            bydAutoEventValue.intValue = getSteepDescentSlowDownState();
                            sparseArray.put(3164, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3169:
                            bydAutoEventValue.intValue = getOpenDoorAlertState();
                            sparseArray.put(3169, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3182:
                            bydAutoEventValue.intValue = getEmergencyLaneAssistance32FState();
                            sparseArray.put(3182, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3189:
                            bydAutoEventValue.intValue = getElecCarBodyStablySysState();
                            sparseArray.put(3189, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3194:
                            bydAutoEventValue.intValue = getFrontTrafficCrossAlertState();
                            sparseArray.put(3194, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3197:
                            bydAutoEventValue.intValue = getFrontTrafficBrakeAlertState();
                            sparseArray.put(3197, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3202:
                            bydAutoEventValue.intValue = getPredictCrashAlert138Mode();
                            sparseArray.put(3202, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3203:
                            bydAutoEventValue.intValue = getPredictCrashAlert32DMode();
                            sparseArray.put(3203, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3226:
                            bydAutoEventValue.intValue = getAiDistanceLight316State();
                            sparseArray.put(3226, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3231:
                            bydAutoEventValue.intValue = getHighSpeedAINavigationState();
                            sparseArray.put(3231, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3236:
                            bydAutoEventValue.intValue = getInteractiveLaneChangeAssistanceState();
                            sparseArray.put(3236, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3239:
                            bydAutoEventValue.intValue = getIntelligentSpeedLimitAlert2D5State();
                            sparseArray.put(3239, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3243:
                            bydAutoEventValue.intValue = getIntelligentSpeedLimitControl2D5State();
                            sparseArray.put(3243, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3244:
                            bydAutoEventValue.intValue = getIntelligentSpeedLimitControl32FState();
                            sparseArray.put(3244, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3247:
                            bydAutoEventValue.intValue = getAlarmType();
                            sparseArray.put(3247, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3255:
                            bydAutoEventValue.intValue = getLaneAssistType();
                            sparseArray.put(3255, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3259:
                            bydAutoEventValue.intValue = getVerifyMode();
                            sparseArray.put(3259, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3264:
                            bydAutoEventValue.intValue = getPredictCrashAlertState();
                            sparseArray.put(3264, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3271:
                            bydAutoEventValue.intValue = getRearCrashAlertState();
                            sparseArray.put(3271, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3277:
                            bydAutoEventValue.intValue = getRearTrafficCrossAlertState();
                            sparseArray.put(3277, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3279:
                            bydAutoEventValue.intValue = getRearTrafficBrakeAlertState();
                            sparseArray.put(3279, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3283:
                            bydAutoEventValue.intValue = getTrafficSignRecognize316State();
                            sparseArray.put(3283, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3285:
                            bydAutoEventValue.intValue = getTrafficSignRecognize2D5State();
                            sparseArray.put(3285, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3289:
                            bydAutoEventValue.intValue = getSpeedLimitRange2D5();
                            sparseArray.put(3289, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3297:
                            bydAutoEventValue.intValue = getAINavigationState();
                            sparseArray.put(3297, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3305:
                            bydAutoEventValue.intValue = getTrafficLightRecognize2D5State();
                            sparseArray.put(3305, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3306:
                            bydAutoEventValue.intValue = getTrafficLightRecognize2B4State();
                            sparseArray.put(3306, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3309:
                            bydAutoEventValue.intValue = getUrbanAIAssistanceState();
                            sparseArray.put(3309, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4554:
                            bydAutoEventValue.intValue = getEmergencyLaneAssistance32FState();
                            sparseArray.put(4554, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoDiPilotListener listener : this.mAbsBYDAutoDiPilotListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue = 0;
        if (value instanceof Integer) {
            intValue = ((Integer) value).intValue();
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
        } else if (value instanceof byte[]) {
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        switch (eventType) {
            case 633:
                setTsrLitSpdChgSwitchState(intValue);
                return 1;
            case 1195:
                setAiCareState(intValue);
                return 1;
            case 1196:
                setMistakeGasState(intValue);
                return 1;
            case 2792:
                setAiMatrixLightState(intValue);
                return 1;
            case 3134:
                setAiEmergencyBrakeState(intValue);
                return 1;
            case 3153:
                setBlindMonitorState(intValue);
                return 1;
            case 3165:
                setSteepDescentSlowDownState(intValue);
                return 1;
            case 3168:
                setOpenDoorAlertState(intValue);
                return 1;
            case 3176:
                setE2EAIAssistanceState(intValue);
                return 1;
            case 3179:
                setRearTrafficBrakeAlertState(intValue);
                return 1;
            case 3181:
                setEmergencyLaneAssistanceState(intValue);
                return 1;
            case 3190:
                setElecCarBodyStablySysState(intValue);
                return 1;
            case 3193:
                setFrontTrafficCrossAlertState(intValue);
                return 1;
            case 3196:
                setFrontTrafficBrakeAlertState(intValue);
                return 1;
            case 3201:
                setPredictCrashAlertMode(intValue);
                return 1;
            case 3228:
                setAiDistanceLightState(intValue);
                return 1;
            case 3230:
                setHighSpeedAINavigationState(intValue);
                return 1;
            case 3235:
                setInteractiveLaneChangeAssistanceState(intValue);
                return 1;
            case 3238:
                setIntelligentSpeedLimitAlertState(intValue);
                return 1;
            case 3242:
                setIntelligentSpeedLimitControlState(intValue);
                return 1;
            case 3248:
                setAlarmType(intValue);
                return 1;
            case 3256:
                setLaneAssistType(intValue);
                return 1;
            case 3260:
                setVerifyMode(intValue);
                return 1;
            case 3265:
                setPredictCrashAlertState(intValue);
                return 1;
            case 3268:
                setRearTrafficCrossAlertState(intValue);
                return 1;
            case 3270:
                setRearCrashAlertState(intValue);
                return 1;
            case 3286:
                setTrafficSignRecognizeState(intValue);
                return 1;
            case 3291:
                setSpeedLimitRange(intValue);
                return 1;
            case 3299:
                setAINavigationState(intValue);
                return 1;
            case 3304:
                setTrafficLightRecognizeState(intValue);
                return 1;
            case 3308:
                setUrbanAIAssistanceState(intValue);
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }
}
