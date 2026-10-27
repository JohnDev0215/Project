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
import java.util.Iterator;
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

    DiPilotManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
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
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_COMMON);
        this.mTsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.dipilot.tsimpl.DiPilotManagerImpl.1
            public void onAssistChanged(int i2, int i3, CarAssistPacket carAssistPacket) {
                if (DiPilotManagerImpl.this.mAbsBYDAutoDiPilotListeners.isEmpty()) {
                    return;
                }
                try {
                    DiPilotManagerImpl.this.assistCallback(i2, i3, carAssistPacket);
                } catch (Exception unused) {
                    Log.e(DiPilotManagerImpl.TAG, "assistCallback is error");
                }
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(DiPilotManagerImpl.TAG, "onBinderStateChanged is " + z);
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int i, int i2, CarAssistPacket carAssistPacket) {
        Log.i(TAG, "AssistCallback propertyId:" + i);
        switch (i) {
            case 557845261:
                int remoteControlDriveCloseTops = carAssistPacket.getRemoteControlDriveCloseTops();
                Iterator<AbsBYDAutoDiPilotListener> it = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it.hasNext()) {
                    it.next().onRemoteControlDriveCloseTops(remoteControlDriveCloseTops);
                }
                break;
            case 557845271:
                Iterator<AbsBYDAutoDiPilotListener> it2 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onLowHODToastChanged(carAssistPacket.getLowHodToastR());
                }
                break;
            case DipilotHalId.ID_SUPPORT_INTELLIGENT_NAVIGATION_FIRST /* 557856077 */:
            case DipilotHalId.ID_SUPPORT_INTELLIGENT_NAVIGATION_SECOND /* 557856078 */:
            case DipilotHalId.ID_SUPPORT_HIGHWAY_NAVIGATION /* 557856079 */:
            case DipilotHalId.ID_SUPPORT_URBAN_NAVIGATION /* 557856080 */:
            case DipilotHalId.ID_SUPPORT_END_TO_END_NAVIGATION /* 557856081 */:
            case DipilotHalId.ID_SUPPORT_LANE_CHANGE_ASSIST /* 557856082 */:
            case DipilotHalId.ID_SUPPORT_LANE_DEPARTURE_WARNING /* 557856083 */:
            case DipilotHalId.ID_SUPPORT_LANE_KEEP /* 557856084 */:
            case DipilotHalId.ID_SUPPORT_LANE_DEPARTURE_STOP /* 557856085 */:
            case DipilotHalId.ID_SUPPORT_EMERGENCY_LANE_KEEP_ASSIST /* 557856086 */:
            case DipilotHalId.ID_SUPPORT_TRAFFIC_SIGN_RECOGNITON /* 557856087 */:
            case DipilotHalId.ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_ALERT /* 557856088 */:
            case DipilotHalId.ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_CONTROL /* 557856089 */:
            case DipilotHalId.ID_SUPPORT_TRAFFIC_LIGHT_ATTENTION /* 557856090 */:
            case DipilotHalId.ID_SUPPORT_FRONT_CROSSING_TRAFFIC_ALERT /* 557856094 */:
            case DipilotHalId.ID_SUPPORT_FRONT_CROSSING_TRAFFIC_BRAKE /* 557856095 */:
            case DipilotHalId.ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_FIRST /* 557856097 */:
            case DipilotHalId.ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_SECOND /* 557856098 */:
            case DipilotHalId.ID_SUPPORT_AI_DISTANCE_LIGHT /* 557856106 */:
            case DipilotHalId.ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_TWOR /* 557870336 */:
                if (i2 == 2) {
                    this.mOnline = 0;
                } else {
                    this.mOnline = 1;
                }
                break;
            case DipilotHalId.ID_HIGH_SPEED_A_I_NAVIGATION_STATE_R /* 557856768 */:
                int i3 = carAssistPacket.getmHighSpeedAINavigationStateR();
                Iterator<AbsBYDAutoDiPilotListener> it3 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onHighSpeedAINavigationStateChanged(i3);
                }
                break;
            case DipilotHalId.ID_URBAN_A_I_ASSISTANCE_STATE_R /* 557856770 */:
                int i4 = carAssistPacket.getmUrbanAIAssistanceStateR();
                Iterator<AbsBYDAutoDiPilotListener> it4 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onUrbanAIAssistanceStateChanged(i4);
                }
                break;
            case DipilotHalId.ID_E_E_A_I_ASSISTANCE_STATE_R /* 557856772 */:
                int i5 = carAssistPacket.getmE2EAIAssistanceStateR();
                Iterator<AbsBYDAutoDiPilotListener> it5 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onE2EAIAssistanceStateChanged(i5);
                }
                break;
            case 557856774:
                int i6 = carAssistPacket.getmInteractiveLaneChangeAssistanceStateR();
                Iterator<AbsBYDAutoDiPilotListener> it6 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onInteractiveLaneChangeAssistanceStateChanged(i6);
                }
                break;
            case 557856776:
                int i7 = carAssistPacket.getmAINavigationStateR();
                Iterator<AbsBYDAutoDiPilotListener> it7 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onAINavigationStateChanged(i7);
                }
                break;
            case DipilotHalId.ID_LANE_ASSIST_TYPE_R /* 557856778 */:
                int i8 = carAssistPacket.getmLaneAssistTypeR();
                Iterator<AbsBYDAutoDiPilotListener> it8 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onLaneAssistTypeChanged(i8);
                }
                break;
            case DipilotHalId.ID_ALARM_TYPE_R /* 557856780 */:
                int i9 = carAssistPacket.getmAlarmTypeR();
                Iterator<AbsBYDAutoDiPilotListener> it9 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onAlarmTypeChanged(i9);
                }
                break;
            case DipilotHalId.ID_TRAFFIC_SIGN_RECOGNIZE_STATE_FIRST /* 557856782 */:
                int i10 = carAssistPacket.getmTrafficSignRecognizeStateFirst();
                Iterator<AbsBYDAutoDiPilotListener> it10 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onTrafficSignRecognize2D5StateChanged(i10);
                }
                break;
            case DipilotHalId.ID_TRAFFIC_SIGN_RECOGNIZE_STATE_SECOND /* 557856783 */:
                int i11 = carAssistPacket.getmTrafficSignRecognizeStateSecond();
                Iterator<AbsBYDAutoDiPilotListener> it11 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onTrafficSignRecognize2B4StateChanged(i11);
                }
                break;
            case DipilotHalId.ID_TRAFFIC_SIGN_RECOGNIZE_STATE_R /* 557856784 */:
                int i12 = carAssistPacket.getmTrafficSignRecognizeStateR();
                Iterator<AbsBYDAutoDiPilotListener> it12 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onTrafficSignRecognize316StateChanged(i12);
                }
                break;
            case DipilotHalId.ID_SPEED_LIMIT_RANGE_FIRST /* 557856786 */:
                int i13 = carAssistPacket.getmSpeedLimitRangeFirst();
                Iterator<AbsBYDAutoDiPilotListener> it13 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onSpeedLimitRange2D5Changed(i13);
                }
                break;
            case DipilotHalId.ID_SPEED_LIMIT_RANGE_SECOND /* 557856787 */:
                int i14 = carAssistPacket.getmSpeedLimitRangeSecond();
                Iterator<AbsBYDAutoDiPilotListener> it14 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onSpeedLimitRange2B4Changed(i14);
                }
                break;
            case DipilotHalId.ID_INTELLIGENT_SPEED_LIMIT_ALERT_STATE_FIRST /* 557856789 */:
                int i15 = carAssistPacket.getmIntelligentSpeedLimitAlertStateFirst();
                Iterator<AbsBYDAutoDiPilotListener> it15 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onIntelligentSpeedLimitAlert2D5StateChanged(i15);
                }
                break;
            case DipilotHalId.ID_INTELLIGENT_SPEED_LIMIT_ALERT_STATE_SECOND /* 557856790 */:
                int i16 = carAssistPacket.getmIntelligentSpeedLimitAlertStateSecond();
                Iterator<AbsBYDAutoDiPilotListener> it16 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onIntelligentSpeedLimitAlert2B4StateChanged(i16);
                }
                break;
            case DipilotHalId.ID_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE_SECOND /* 557856792 */:
                int i17 = carAssistPacket.getmIntelligentSpeedLimitControlStateSecond();
                Iterator<AbsBYDAutoDiPilotListener> it17 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onIntelligentSpeedLimitControl32FStateChanged(i17);
                }
                break;
            case DipilotHalId.ID_VERIFY_MODE_R /* 557856794 */:
                int i18 = carAssistPacket.getmVerifyModeR();
                Iterator<AbsBYDAutoDiPilotListener> it18 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onVerifyModeChanged(i18);
                }
                break;
            case DipilotHalId.ID_AI_CARE_STATE_R /* 557856796 */:
                int i19 = carAssistPacket.getmAiCareStateR();
                Iterator<AbsBYDAutoDiPilotListener> it19 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onAiCareStateChanged(i19);
                }
                break;
            case DipilotHalId.ID_MISTAKE_GAS_STATE_R /* 557856798 */:
                int i20 = carAssistPacket.getmMistakeGasStateR();
                Iterator<AbsBYDAutoDiPilotListener> it20 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onMistakeGasStateChanged(i20);
                }
                break;
            case DipilotHalId.ID_AI_MATRIX_LIGHT_STATE_FIRST /* 557856800 */:
                int i21 = carAssistPacket.getmAiMatrixLightStateFirst();
                Iterator<AbsBYDAutoDiPilotListener> it21 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onAiMatrixLight3ACStateChanged(i21);
                }
                break;
            case DipilotHalId.ID_AI_MATRIX_LIGHT_STATE_SECOND /* 557856801 */:
                int i22 = carAssistPacket.getmAiMatrixLightStateSecond();
                Iterator<AbsBYDAutoDiPilotListener> it22 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onAiMatrixLight3ADStateChanged(i22);
                }
                break;
            case DipilotHalId.ID_AI_DISTANCE_LIGHT_STATER_SECOND /* 557856804 */:
                int i23 = carAssistPacket.getmAiDistanceLightStateSecond();
                Iterator<AbsBYDAutoDiPilotListener> it23 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onAiDistanceLight316StateChanged(i23);
                }
                break;
            case DipilotHalId.ID_TRAFFIC_LIGHT_RECOGNIZE_STATE_FIRST /* 557856806 */:
                int i24 = carAssistPacket.getmTrafficLightRecognizeStateFirst();
                Iterator<AbsBYDAutoDiPilotListener> it24 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onTrafficLightRecognize2D5StateChanged(i24);
                }
                break;
            case DipilotHalId.ID_EMERGENCY_LANE_ASSISTANCE_STATE_FIRST /* 557856809 */:
                int i25 = carAssistPacket.getmEmergencyLaneAssistanceStateFirst();
                Iterator<AbsBYDAutoDiPilotListener> it25 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onEmergencyLaneAssistance32FStateChanged(i25);
                }
                break;
            case DipilotHalId.ID_EMERGENCY_LANE_ASSISTANCE_STATE_SECOND /* 557856810 */:
                int i26 = carAssistPacket.getmEmergencyLaneAssistanceStateSecond();
                Iterator<AbsBYDAutoDiPilotListener> it26 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onEmergencyLaneAssistance2F0StateChanged(i26);
                }
                break;
            case DipilotHalId.ID_BLIND_MONITOR_STATE_FIRST /* 557856812 */:
                int i27 = carAssistPacket.getmBlindMonitorStateFirst();
                Iterator<AbsBYDAutoDiPilotListener> it27 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onBlindMonitorStateChanged1(i27);
                }
                break;
            case DipilotHalId.ID_BLIND_MONITOR_STATE_SECOND /* 557856813 */:
                int i28 = carAssistPacket.getmBlindMonitorStateSecond();
                Iterator<AbsBYDAutoDiPilotListener> it28 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onBlindMonitorStateChanged2(i28);
                }
                break;
            case DipilotHalId.ID_OPEN_DOOR_ALERT_STATE_R /* 557856815 */:
                int i29 = carAssistPacket.getmOpenDoorAlertStateR();
                Iterator<AbsBYDAutoDiPilotListener> it29 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onOpenDoorAlertStateChanged(i29);
                }
                break;
            case DipilotHalId.ID_REAR_CRASH_ALERT_STATE_R /* 557856817 */:
                int i30 = carAssistPacket.getmRearCrashAlertStateR();
                Iterator<AbsBYDAutoDiPilotListener> it30 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onRearCrashAlertStateChanged(i30);
                }
                break;
            case DipilotHalId.ID_REAR_TRAFFIC_CROSS_ALERT_STATE_R /* 557856819 */:
                int i31 = carAssistPacket.getmRearTrafficCrossAlertStateR();
                Iterator<AbsBYDAutoDiPilotListener> it31 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onRearTrafficCrossAlertStateChanged(i31);
                }
                break;
            case DipilotHalId.ID_REAR_TRAFFIC_BRAKE_ALERT_STATE_R /* 557856821 */:
                int i32 = carAssistPacket.getmRearTrafficBrakeAlertStateR();
                Iterator<AbsBYDAutoDiPilotListener> it32 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onRearTrafficBrakeAlertStateChanged(i32);
                }
                break;
            case DipilotHalId.ID_AI_EMERGENCY_BRAKE_STATE_R /* 557856823 */:
                int i33 = carAssistPacket.getmAiEmergencyBrakeStateR();
                Iterator<AbsBYDAutoDiPilotListener> it33 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onAiEmergencyBrakeStateChanged(i33);
                }
                break;
            case DipilotHalId.ID_PREDICT_CRASH_ALERT_STATE_R /* 557856825 */:
                int i34 = carAssistPacket.getmPredictCrashAlertStateR();
                Iterator<AbsBYDAutoDiPilotListener> it34 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onPredictCrashAlertStateChange(i34);
                }
                break;
            case DipilotHalId.ID_PREDICT_CRASH_ALERT_MODE_FIRST /* 557856827 */:
                int i35 = carAssistPacket.getmPredictCrashAlertModeRFirst();
                Iterator<AbsBYDAutoDiPilotListener> it35 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onPredictCrashAlert138ModeChanged(i35);
                }
                break;
            case DipilotHalId.ID_PREDICT_CRASH_ALERT_MODE_SECOND /* 557856828 */:
                int i36 = carAssistPacket.getmPredictCrashAlertModeRSecond();
                Iterator<AbsBYDAutoDiPilotListener> it36 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onPredictCrashAlert32DModeChanged(i36);
                }
                break;
            case DipilotHalId.ID_FRONT_TRAFFIC_CROSS_ALERT_STATE_R /* 557856830 */:
                int i37 = carAssistPacket.getmFrontTrafficCrossAlertStateR();
                Iterator<AbsBYDAutoDiPilotListener> it37 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onFrontTrafficCrossAlertStateChanged(i37);
                }
                break;
            case DipilotHalId.ID_FRONT_TRAFFIC_BRAKE_ALERT_STATE_R /* 557856832 */:
                Iterator<AbsBYDAutoDiPilotListener> it38 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onFrontTrafficBrakeAlertStateChanged(carAssistPacket.getmFrontTrafficBrakeAlertStateR());
                }
                break;
            case DipilotHalId.ID_STEEP_DESCENT_SLOW_DOWN_STATE_R /* 557856835 */:
                int i38 = carAssistPacket.getmSteepDescentSlowDownStateR();
                Iterator<AbsBYDAutoDiPilotListener> it39 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onSteepDescentSlowDownStateChanged(i38);
                }
                break;
            case DipilotHalId.ID_ELEC_CARBODY_STABLY_SYS_STATE_CHANGED /* 557856838 */:
                int elecCarBodyStablySysStateChanged = carAssistPacket.getElecCarBodyStablySysStateChanged();
                Iterator<AbsBYDAutoDiPilotListener> it40 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onElecCarBodyStablySysStateChanged(elecCarBodyStablySysStateChanged);
                }
                break;
            case DipilotHalId.ID_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE_FIRST /* 557856839 */:
                int intelligentSpeedLimitControlStateFirst = carAssistPacket.getIntelligentSpeedLimitControlStateFirst();
                Iterator<AbsBYDAutoDiPilotListener> it41 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it41.hasNext()) {
                    it41.next().onIntelligentSpeedLimitControl2D5StateChanged(intelligentSpeedLimitControlStateFirst);
                }
                break;
            case DipilotHalId.ID_CPD_STATUS_R /* 557856840 */:
                int cpdDelayStatusR = carAssistPacket.getCpdDelayStatusR();
                Log.v(TAG, "cpdStatus:" + cpdDelayStatusR);
                Iterator<AbsBYDAutoDiPilotListener> it42 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it42.hasNext()) {
                    it42.next().onCpdStatusChanged(cpdDelayStatusR);
                }
                break;
            case DipilotHalId.ID_CPD_DELAY_STATUS_R /* 557856842 */:
                int cpdDelayStatusR2 = carAssistPacket.getCpdDelayStatusR();
                Log.v(TAG, "delayStatus:" + cpdDelayStatusR2);
                Iterator<AbsBYDAutoDiPilotListener> it43 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it43.hasNext()) {
                    it43.next().onCpdDelayStatusChanged(cpdDelayStatusR2);
                }
                break;
            case DipilotHalId.ID_DMS_SWITCH_STATUS_R /* 557856843 */:
                int dmsSwitchStatusR = carAssistPacket.getDmsSwitchStatusR();
                Log.v(TAG, "dmsSwitchStatus:" + dmsSwitchStatusR);
                Iterator<AbsBYDAutoDiPilotListener> it44 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it44.hasNext()) {
                    it44.next().onDmsStatusChanged(dmsSwitchStatusR);
                }
                break;
            case DipilotHalId.ID_ADAS_SOFT_VERSION_R /* 557856845 */:
                Iterator<AbsBYDAutoDiPilotListener> it45 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it45.hasNext()) {
                    it45.next().onADASSoftStateChanged(carAssistPacket.getmAdasSoftVersionR());
                }
                break;
            case DipilotHalId.ID_TSR_LIT_SPD_CHG_SWITCH_STATE_R /* 557856846 */:
                Iterator<AbsBYDAutoDiPilotListener> it46 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it46.hasNext()) {
                    it46.next().onTSRLitSpdChgSwitchStateChanged(carAssistPacket.getmTsrLitSpdChgSwitchStateR());
                }
                break;
            case DipilotHalId.ID_ELK_TAKE_OVER_STATE_R /* 557856848 */:
                Iterator<AbsBYDAutoDiPilotListener> it47 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it47.hasNext()) {
                    it47.next().onELKTakeOverStateChanged(carAssistPacket.getmElkTakeOverStateR());
                }
                break;
            case DipilotHalId.ID_TSR_LIT_SPD_CHG_INFORM_STATE_R /* 557856849 */:
                Iterator<AbsBYDAutoDiPilotListener> it48 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it48.hasNext()) {
                    it48.next().onTSRLitSpdChgInformStateChanged(carAssistPacket.getmTsrLitSpdChgInformStateR());
                }
                break;
            case DipilotHalId.ID_ISLI_WARNING_STATE_R /* 557856850 */:
                Iterator<AbsBYDAutoDiPilotListener> it49 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it49.hasNext()) {
                    it49.next().onISLIWarningStateChanged(carAssistPacket.getmIsliWarningStateR());
                }
                break;
            case DipilotHalId.ID_LOW_SPEED_EMERGENCY_BRAKING_STATER /* 557856851 */:
                Iterator<AbsBYDAutoDiPilotListener> it50 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it50.hasNext()) {
                    it50.next().onLowSpeedEmergencyBrakingStateChanged(carAssistPacket.getLowSpeedEmergencyBrakingStateR());
                }
                break;
            case DipilotHalId.ID_SIDE_DISTANCE_WARNING_STATER /* 557856853 */:
                Iterator<AbsBYDAutoDiPilotListener> it51 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it51.hasNext()) {
                    it51.next().onSideDistanceWarningStateChanged(carAssistPacket.getSideDistanceWarningStateR());
                }
                break;
            case DipilotHalId.ID_COLLISION_WARNING_STATER /* 557856855 */:
                Iterator<AbsBYDAutoDiPilotListener> it52 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it52.hasNext()) {
                    it52.next().onCollisionWarningStateChanged(carAssistPacket.getCollisionWarningStateR());
                }
                break;
            case DipilotHalId.ID_INTERSECTION_COLLISION_ALERT_STATER /* 557856857 */:
                Iterator<AbsBYDAutoDiPilotListener> it53 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it53.hasNext()) {
                    it53.next().onIntersectionCollisionAlertStateChanged(carAssistPacket.getIntersectionCollisionAlertStateR());
                }
                break;
            case DipilotHalId.ID_DO_NOT_PASS_WARNING_STATER /* 557856859 */:
                Iterator<AbsBYDAutoDiPilotListener> it54 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it54.hasNext()) {
                    it54.next().onDoNotPassWarningStateChanged(carAssistPacket.getDoNotPassWarningStateR());
                }
                break;
            case DipilotHalId.ID_EMERGENCY_BRAKE_WARNING_STATER /* 557856861 */:
                Iterator<AbsBYDAutoDiPilotListener> it55 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it55.hasNext()) {
                    it55.next().onEmergencyBrakeWarningStateChanged(carAssistPacket.getEmergencyBrakeWarningStateR());
                }
                break;
            case DipilotHalId.ID_TRAFFIC_INCIDENT_WARNING_STATER /* 557856863 */:
                Iterator<AbsBYDAutoDiPilotListener> it56 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it56.hasNext()) {
                    it56.next().onTrafficIncidentWarningStateChanged(carAssistPacket.getTrafficIncidentWarningStateR());
                }
                break;
            case DipilotHalId.ID_ABNORMAL_VEHICLE_WARNING_STATER /* 557856865 */:
                Iterator<AbsBYDAutoDiPilotListener> it57 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it57.hasNext()) {
                    it57.next().onAbnormalVehicleWarningStateChanged(carAssistPacket.getAbnormalVehicleWarningStateR());
                }
                break;
            case DipilotHalId.ID_CONTROL_LOSS_WARNING_STATER /* 557856867 */:
                Iterator<AbsBYDAutoDiPilotListener> it58 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it58.hasNext()) {
                    it58.next().onControlLossWarningStateChanged(carAssistPacket.getControlLossWarningStateR());
                }
                break;
            case DipilotHalId.ID_TRAFFIC_JAM_WARNING_STATER /* 557856869 */:
                Iterator<AbsBYDAutoDiPilotListener> it59 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it59.hasNext()) {
                    it59.next().onTrafficJamWarningStateChanged(carAssistPacket.getTrafficJamWarningStateR());
                }
                break;
            case DipilotHalId.ID_EMERGENCY_VEHICLE_WARNING_STATER /* 557856871 */:
                Iterator<AbsBYDAutoDiPilotListener> it60 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it60.hasNext()) {
                    it60.next().onEmergencyVehicleWarningStateChanged(carAssistPacket.getEmergencyVehicleWarningStateR());
                }
                break;
            case DipilotHalId.ID_TRAFFIC_SIGNAL_WARNING_STATER /* 557856873 */:
                Iterator<AbsBYDAutoDiPilotListener> it61 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it61.hasNext()) {
                    it61.next().onTrafficSignalWarningStateChanged(carAssistPacket.getTrafficSignalWarningStateR());
                }
                break;
            case DipilotHalId.ID_TRAFFIC_LIGHT_INFO_PUSH_STATER /* 557856875 */:
                Iterator<AbsBYDAutoDiPilotListener> it62 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it62.hasNext()) {
                    it62.next().onTrafficLightInfoPushStateChanged(carAssistPacket.getTrafficLightInfoPushStateR());
                }
                break;
            case DipilotHalId.ID_RED_LIGHT_VIOLATION_WARNING_STATER /* 557856877 */:
                Iterator<AbsBYDAutoDiPilotListener> it63 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it63.hasNext()) {
                    it63.next().onRedLightViolationWarningStateChanged(carAssistPacket.getRedLightViolationWarningStateR());
                }
                break;
            case DipilotHalId.ID_GREEN_LIGHT_OPTIMAL_SPEED_ADVISORY_STATER /* 557856879 */:
                Iterator<AbsBYDAutoDiPilotListener> it64 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it64.hasNext()) {
                    it64.next().onGreenLightOptimalSpeedAdvisoryStateChanged(carAssistPacket.getGreenLightOptimalSpeedAdvisoryStateR());
                }
                break;
            case DipilotHalId.ID_MECHANISM_POSITIONR /* 557856884 */:
                Iterator<AbsBYDAutoDiPilotListener> it65 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it65.hasNext()) {
                    it65.next().onMechanismPositionChanged(carAssistPacket.getMechanismPositionR());
                }
                break;
            case DipilotHalId.ID_FATIGUE_MONITOR_CHANGED_R /* 557856888 */:
                Iterator<AbsBYDAutoDiPilotListener> it66 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it66.hasNext()) {
                    it66.next().onFatigueMonitorChange(carAssistPacket.getFatigueMonitorChangedR());
                }
                break;
            case 557856889:
                Iterator<AbsBYDAutoDiPilotListener> it67 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it67.hasNext()) {
                    it67.next().onBSISSwitchChange(carAssistPacket.getBsisEnableR());
                }
                break;
            case 557856891:
                Iterator<AbsBYDAutoDiPilotListener> it68 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it68.hasNext()) {
                    it68.next().onBSISSystemChange(carAssistPacket.getBsisFuncStatusR());
                }
                break;
            case 557856892:
                Iterator<AbsBYDAutoDiPilotListener> it69 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it69.hasNext()) {
                    it69.next().onMOISSwitchChange(carAssistPacket.getMoisEnableR());
                }
                break;
            case 557856894:
                Iterator<AbsBYDAutoDiPilotListener> it70 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it70.hasNext()) {
                    it70.next().onMOISSystemChange(carAssistPacket.getMoisFuncStateR());
                }
                break;
            case DipilotHalId.ID_CPD_STATUS_NEW_R /* 557856896 */:
                int cpdStatusNewR = carAssistPacket.getCpdStatusNewR();
                Log.v(TAG, "cpdStatus:" + cpdStatusNewR);
                Iterator<AbsBYDAutoDiPilotListener> it71 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it71.hasNext()) {
                    it71.next().onCpdStatusChanged(cpdStatusNewR);
                }
                break;
            case DipilotHalId.ID_ADATIVE_CRUISE_CONTROL_FAULT_R /* 557856897 */:
                Iterator<AbsBYDAutoDiPilotListener> it72 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it72.hasNext()) {
                    it72.next().onAdativeCruiseControlFault((byte) carAssistPacket.getAdativeCruiseControlFaultR());
                }
                break;
            case DipilotHalId.ID_LANE_CENTERING_CONTROL_FAULT_R /* 557856898 */:
                Iterator<AbsBYDAutoDiPilotListener> it73 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it73.hasNext()) {
                    it73.next().onLaneCenteringControlFault((byte) carAssistPacket.getLaneCenteringControlFaultR());
                }
                break;
            case DipilotHalId.ID_BLIND_FAULT_R /* 557856899 */:
                Iterator<AbsBYDAutoDiPilotListener> it74 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it74.hasNext()) {
                    it74.next().onBlindFault((byte) carAssistPacket.getBlindFaultR());
                }
                break;
            case DipilotHalId.ID_DOOR_OPEN_FAULT_R /* 557856900 */:
                Iterator<AbsBYDAutoDiPilotListener> it75 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it75.hasNext()) {
                    it75.next().onDoorOpenFault((byte) carAssistPacket.getDoorOpenFaultR());
                }
                break;
            case DipilotHalId.ID_REAR_CROSS_ALART_FAULT_R /* 557856901 */:
                Iterator<AbsBYDAutoDiPilotListener> it76 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it76.hasNext()) {
                    it76.next().onRearCrossAlartFault((byte) carAssistPacket.getRearCrossAlartFaultR());
                }
                break;
            case DipilotHalId.ID_REAR_CROSS_BRAKE_FAULT_R /* 557856902 */:
                Iterator<AbsBYDAutoDiPilotListener> it77 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it77.hasNext()) {
                    it77.next().onRearCrossBrakeFault((byte) carAssistPacket.getRearCrossBrakeFaultR());
                }
                break;
            case DipilotHalId.ID_REAR_COLLISION_WARN_FAULT_R /* 557856903 */:
                Iterator<AbsBYDAutoDiPilotListener> it78 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it78.hasNext()) {
                    it78.next().onRearCollisionWarnFault((byte) carAssistPacket.getRearCollisionWarnFaultR());
                }
                break;
            case DipilotHalId.ID_FRONT_CROSS_ALARK_FAULT_R /* 557856904 */:
                Iterator<AbsBYDAutoDiPilotListener> it79 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it79.hasNext()) {
                    it79.next().onFrontCrossAlarkFault((byte) carAssistPacket.getFrontCrossAlarkFaultR());
                }
                break;
            case DipilotHalId.ID_FRONT_CROSS_BRAKE_FAULT_R /* 557856905 */:
                Iterator<AbsBYDAutoDiPilotListener> it80 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it80.hasNext()) {
                    it80.next().onFrontCrossBrakeFault((byte) carAssistPacket.getFrontCrossBrakeFaultR());
                }
                break;
            case DipilotHalId.ID_INTERACTIVE_LANE_CHANGE_FAULT_R /* 557856906 */:
                Iterator<AbsBYDAutoDiPilotListener> it81 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it81.hasNext()) {
                    it81.next().onInteractiveLaneChangeFault((byte) carAssistPacket.getInteractiveLaneChangeFaultR());
                }
                break;
            case DipilotHalId.ID_NRBAN_NAVI_FAULT_R /* 557856907 */:
                Iterator<AbsBYDAutoDiPilotListener> it82 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it82.hasNext()) {
                    it82.next().onNrbanNaviFault((byte) carAssistPacket.getNrbanNaviFaultR());
                }
                break;
            case DipilotHalId.ID_HIGH_NAVI_FAULT_R /* 557856908 */:
                Iterator<AbsBYDAutoDiPilotListener> it83 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it83.hasNext()) {
                    it83.next().onHighNaviFault((byte) carAssistPacket.getHighNaviFaultR());
                }
                break;
            case DipilotHalId.ID_NAVIGATION_PILOT_FAULT_R /* 557856909 */:
                Iterator<AbsBYDAutoDiPilotListener> it84 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it84.hasNext()) {
                    it84.next().onNavigationPilotFault((byte) carAssistPacket.getNavigationPilotFaultR());
                }
                break;
            case DipilotHalId.ID_AUTO_EMERGENCY_FAULT_R /* 557856910 */:
                Iterator<AbsBYDAutoDiPilotListener> it85 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it85.hasNext()) {
                    it85.next().onAutoEmergencyFault((byte) carAssistPacket.getAutoEmergencyFaultR());
                }
                break;
            case DipilotHalId.ID_EMERGENCY_LANE_KEEP_FAULT_R /* 557856911 */:
                Iterator<AbsBYDAutoDiPilotListener> it86 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it86.hasNext()) {
                    it86.next().onEmergencyLaneKeepFault((byte) carAssistPacket.getEmergencyLaneKeepFaultR());
                }
                break;
            case DipilotHalId.ID_INTELLIGENT_LIMIT_FAULT_CONTROLLER_R /* 557856912 */:
                Iterator<AbsBYDAutoDiPilotListener> it87 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it87.hasNext()) {
                    it87.next().onIntelligentLimitFaultController((byte) carAssistPacket.getIntelligentLimitFaultControllerR());
                }
                break;
            case DipilotHalId.ID_LANE_DEPARTURE_PREVENTION_FAULT_R /* 557856913 */:
                Iterator<AbsBYDAutoDiPilotListener> it88 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it88.hasNext()) {
                    it88.next().onLaneDeparturePreventionFault((byte) carAssistPacket.getLaneDeparturePreventionFaultR());
                }
                break;
            case DipilotHalId.ID_LANE_DEPARTURE_WARN_FAULT_R /* 557856914 */:
                Iterator<AbsBYDAutoDiPilotListener> it89 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it89.hasNext()) {
                    it89.next().onLaneDepartureWarnFault((byte) carAssistPacket.getLaneDepartureWarnFaultR());
                }
                break;
            case DipilotHalId.ID_LANE_KEEP_FAULT_R /* 557856915 */:
                Iterator<AbsBYDAutoDiPilotListener> it90 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it90.hasNext()) {
                    it90.next().onLaneKeepFault((byte) carAssistPacket.getLaneKeepFaultR());
                }
                break;
            case DipilotHalId.ID_FORWARD_COLLISION_WARN_FAULT_R /* 557856916 */:
                Iterator<AbsBYDAutoDiPilotListener> it91 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it91.hasNext()) {
                    it91.next().onForwardCollisionWarnFault((byte) carAssistPacket.getForwardCollisionWarnFaultR());
                }
                break;
            case DipilotHalId.ID_ADAPTIVE_FRONT_LIGHT_FAULT_R /* 557856917 */:
                Iterator<AbsBYDAutoDiPilotListener> it92 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it92.hasNext()) {
                    it92.next().onAdaptiveFrontLightFault((byte) carAssistPacket.getAdaptiveFrontLightFaultR());
                }
                break;
            case DipilotHalId.ID_TRAFFIC_ATTENTION_FAULT_R /* 557856918 */:
                Iterator<AbsBYDAutoDiPilotListener> it93 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it93.hasNext()) {
                    it93.next().onTrafficAttentionFault((byte) carAssistPacket.getTrafficAttentionFaultR());
                }
                break;
            case DipilotHalId.ID_TSR_FAULT_R /* 557856919 */:
                Iterator<AbsBYDAutoDiPilotListener> it94 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it94.hasNext()) {
                    it94.next().onTsrFault((byte) carAssistPacket.getTsrFaultR());
                }
                break;
            case DipilotHalId.ID_INTELLIGENT_LIMIT_FAULT_CALL_R /* 557856920 */:
                Iterator<AbsBYDAutoDiPilotListener> it95 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it95.hasNext()) {
                    it95.next().onIntelligentLimitFaultCall((byte) carAssistPacket.getIntelligentLimitFaultCallR());
                }
                break;
            case DipilotHalId.ID_LIMIT_SPEED_CHANGE_FAULT_R /* 557856921 */:
                Iterator<AbsBYDAutoDiPilotListener> it96 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it96.hasNext()) {
                    it96.next().onLimitSpeedChangeFault((byte) carAssistPacket.getLimitSpeedChangeFaultR());
                }
                break;
            case DipilotHalId.ID_CURVE_WARN_FAULT_R /* 557856922 */:
                Iterator<AbsBYDAutoDiPilotListener> it97 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it97.hasNext()) {
                    it97.next().onCurveWarnFault((byte) carAssistPacket.getCurveWarnFaultR());
                }
                break;
            case DipilotHalId.ID_BSIS_FAULT_R /* 557856923 */:
                Iterator<AbsBYDAutoDiPilotListener> it98 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it98.hasNext()) {
                    it98.next().onBSISFault((byte) carAssistPacket.getBsisFaultR());
                }
                break;
            case DipilotHalId.ID_MOIS_FAULT_R /* 557856924 */:
                Iterator<AbsBYDAutoDiPilotListener> it99 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it99.hasNext()) {
                    it99.next().onMOISFault((byte) carAssistPacket.getMoisFaultR());
                }
                break;
            case DipilotHalId.ID_I_S_L_I_WARNING_LEVEL_SWITCH_STATUS_R /* 557856929 */:
                Iterator<AbsBYDAutoDiPilotListener> it100 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it100.hasNext()) {
                    it100.next().onISLIWarningLevelSwitchStatusChanged(carAssistPacket.getIsliWarningLevelSwitchStatusR());
                }
                break;
            case DipilotHalId.ID_AUTO_PARKING_STATE_R /* 557856931 */:
                Iterator<AbsBYDAutoDiPilotListener> it101 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it101.hasNext()) {
                    it101.next().onAutoParkingStateChanged(carAssistPacket.getAutoParkingStateR());
                }
                break;
            case DipilotHalId.ID_CPD_WARNING_STATE_R /* 557856933 */:
                Iterator<AbsBYDAutoDiPilotListener> it102 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it102.hasNext()) {
                    it102.next().onCpdWarningStateChanged(carAssistPacket.getCpdWarningStateR());
                }
                break;
            case DipilotHalId.ID_CPD_WORKING_STATUS_R /* 557856934 */:
                Iterator<AbsBYDAutoDiPilotListener> it103 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it103.hasNext()) {
                    it103.next().onCpdWorkingStatusChanged(carAssistPacket.getCpdWorkingStatusR());
                }
                break;
            case DipilotHalId.ID_ESP_OFF_KEY_STATUS_R /* 557856935 */:
                Iterator<AbsBYDAutoDiPilotListener> it104 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it104.hasNext()) {
                    it104.next().onEspOffKeyStatusChanged(carAssistPacket.getEspOffKeyStatusR());
                }
                break;
            case DipilotHalId.ID_I_O_P_STATE_R /* 557856936 */:
                Iterator<AbsBYDAutoDiPilotListener> it105 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it105.hasNext()) {
                    it105.next().onIOPStateChanged(carAssistPacket.getIopStateR());
                }
                break;
            case DipilotHalId.ID_S_A_O_C_STATE_R /* 557856938 */:
                Iterator<AbsBYDAutoDiPilotListener> it106 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it106.hasNext()) {
                    it106.next().onSAOCStateChanged(carAssistPacket.getSaocStateR());
                }
                break;
            case DipilotHalId.ID_S_L_I_F_STATE_R /* 557856940 */:
                Iterator<AbsBYDAutoDiPilotListener> it107 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it107.hasNext()) {
                    it107.next().onSLIFStateChanged(carAssistPacket.getSlifStateR());
                }
                break;
            case DipilotHalId.ID_LOW_I_O_P_TOAST_CHANGED_R /* 557856944 */:
                Iterator<AbsBYDAutoDiPilotListener> it108 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it108.hasNext()) {
                    it108.next().onLowIOPToastChanged(carAssistPacket.getLowIopToastR());
                }
                break;
            case DipilotHalId.ID_HIGH_I_O_P_TOAST_CHANGED_R /* 557856945 */:
                Iterator<AbsBYDAutoDiPilotListener> it109 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it109.hasNext()) {
                    it109.next().onHighIOPToastChanged(carAssistPacket.getHighIopToastR());
                }
                break;
            case DipilotHalId.ID_A_C_C_STATE_R /* 557856946 */:
                Iterator<AbsBYDAutoDiPilotListener> it110 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it110.hasNext()) {
                    it110.next().onACCStateChanged(carAssistPacket.getAccStateR());
                }
                break;
            case DipilotHalId.ID_F_C_S_R_STATE_R /* 557856948 */:
                Iterator<AbsBYDAutoDiPilotListener> it111 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it111.hasNext()) {
                    it111.next().onFCSRStateChanged(carAssistPacket.getFcsrStateR());
                }
                break;
            case DipilotHalId.ID_F_C_S_R_FAULT_R /* 557856950 */:
                Iterator<AbsBYDAutoDiPilotListener> it112 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it112.hasNext()) {
                    it112.next().onFCSRFault(carAssistPacket.getFcsrFaultR());
                }
                break;
            case 557870400:
                Iterator<AbsBYDAutoDiPilotListener> it113 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it113.hasNext()) {
                    it113.next().onHighHODToastChanged(carAssistPacket.getHighHodToastR());
                }
                break;
            case DipilotHalId.ID_A_E_B_DEC_ACTIVE_R /* 557870413 */:
                Iterator<AbsBYDAutoDiPilotListener> it114 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it114.hasNext()) {
                    it114.next().onAEBDecActiveChanged(carAssistPacket.getAebDecActiveR());
                }
                break;
            case DipilotHalId.ID_LKS_FAULT_STATER /* 557870415 */:
                Iterator<AbsBYDAutoDiPilotListener> it115 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it115.hasNext()) {
                    it115.next().onLKSFaultStateStateChanged(carAssistPacket.getLksFaultStateR());
                }
                break;
            case DipilotHalId.ID_LDW_FAULT_STATER /* 557870416 */:
                Iterator<AbsBYDAutoDiPilotListener> it116 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it116.hasNext()) {
                    it116.next().onLDWFaultStateChanged(carAssistPacket.getLdwFaultStateR());
                }
                break;
            case DipilotHalId.ID_DNP_STATUSR /* 557870437 */:
                Iterator<AbsBYDAutoDiPilotListener> it117 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it117.hasNext()) {
                    it117.next().onDnpStatusChanged(carAssistPacket.getDnpStatusR());
                }
                break;
            case DipilotHalId.ID_AEB_FAULT_STATER /* 557870469 */:
                Iterator<AbsBYDAutoDiPilotListener> it118 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it118.hasNext()) {
                    it118.next().onAEBFaultStateStateChanged(carAssistPacket.getAebFaultStateR());
                }
                break;
            case DipilotHalId.ID_FCW_FAULT_STATER /* 557870470 */:
                Iterator<AbsBYDAutoDiPilotListener> it119 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it119.hasNext()) {
                    it119.next().onFCWFaultStateChanged(carAssistPacket.getFcwFaultStateR());
                }
                break;
            case DipilotHalId.ID_VRU_FAULT_STATER /* 557870471 */:
                Iterator<AbsBYDAutoDiPilotListener> it120 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it120.hasNext()) {
                    it120.next().onVRUFaultStateStateChanged(carAssistPacket.getVruFaultStateR());
                }
                break;
            case DipilotHalId.ID_AI_DISTANCE_LIGHT_STATER_FIRST /* 557870472 */:
                int i39 = carAssistPacket.getmAiDistanceLightStateFirst();
                Iterator<AbsBYDAutoDiPilotListener> it121 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it121.hasNext()) {
                    it121.next().onAiDistanceLight2B4StateChanged(i39);
                }
                break;
            case DipilotHalId.ID_TRAFFIC_LIGHT_RECOGNIZE_STATE_SECOND /* 557870505 */:
                int i40 = carAssistPacket.getmTrafficLightRecognizeStateSecond();
                Iterator<AbsBYDAutoDiPilotListener> it122 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it122.hasNext()) {
                    it122.next().onTrafficLightRecognize2B4StateChanged(i40);
                }
                break;
            case DipilotHalId.ID_L_D_W_SENSITIVITY_R /* 557872897 */:
                Iterator<AbsBYDAutoDiPilotListener> it123 = this.mAbsBYDAutoDiPilotListeners.iterator();
                while (it123.hasNext()) {
                    it123.next().onLDWSensitivityChange(carAssistPacket.getLdwSensitivityR());
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
        if (1 == this.mForTest) {
            Log.v(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getType() {
        Log.v(TAG, "getType is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getType mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 != this.mForTest) {
            return new int[0];
        }
        Log.v(TAG, "getFeatureList mForTestValue is" + this.mForTest + " execute return");
        return new int[0];
    }

    public int getDrivieRemindState() {
        Log.v(TAG, "getDrivieRemindState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDrivieRemindState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setDrivieRemindState(int i) {
        Log.v(TAG, "setDrivieRemindState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setDrivieRemindState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(5000, i);
        }
        return 0;
    }

    public int getDrivieDepartureState() {
        Log.v(TAG, "getDrivieDepartureState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDrivieDepartureState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setDrivieDeparturState(int i) {
        Log.v(TAG, "setDrivieDeparturState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setDrivieDeparturState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(5001, i);
        }
        return 0;
    }

    public int getFrontCarStartState() {
        Log.v(TAG, "getFrontCarStartState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFrontCarStartState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setFrontCarStartState(int i) {
        Log.v(TAG, "setFrontCarStartState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setFrontCarStartState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(5002, i);
        }
        return 0;
    }

    public int getFaultHandlerState() {
        Log.v(TAG, "getFaultHandlerState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFaultHandlerState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setFaultHandlerState(int i) {
        Log.v(TAG, "setFaultHandlerState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setFaultHandlerState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(5003, i);
        }
        return 0;
    }

    public int getChargerCoverState() {
        Log.v(TAG, "getChargerCoverState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getChargerCoverState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setChargerCoverState(int i) {
        Log.v(TAG, "setChargerCoverState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setChargerCoverState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(5004, i);
        }
        return 0;
    }

    public int getAiTurnLightState() {
        Log.v(TAG, "getAiTurnLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAiTurnLightState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setAiTurnLightState(int i) {
        Log.v(TAG, "setAiTurnLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAiTurnLightState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_TURN_LIGHT_STATE, i);
        }
        return 0;
    }

    public int getRainOpenFogLightState() {
        Log.v(TAG, "getRainOpenFogLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRainOpenFogLightState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setRainOpenFogLightState(int i) {
        Log.v(TAG, "setRainOpenFogLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRainOpenFogLightState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_RAIN_OPEN_FOG_LIGHT_STATE, i);
        }
        return 0;
    }

    public int getHighSpeedOpenWindowState() {
        Log.v(TAG, "getHighSpeedOpenWindowState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getHighSpeedOpenWindowState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setHighSpeedOpenWindowState(int i) {
        Log.v(TAG, "setHighSpeedOpenWindowState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setHighSpeedOpenWindowState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_HIGH_SPEED_OPEN_WINDOW_STATE, i);
        }
        return 0;
    }

    public int getDriveLevel() {
        Log.v(TAG, "getDriveLevel is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveLevel mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setDriveLevel(int i) {
        Log.v(TAG, "setDriveLevel is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setDriveLevel mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DRIVE_LEVEL, i);
        }
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

    public int setHighSpeedAINavigationState(int i) {
        Log.v(TAG, "setHighSpeedAINavigationState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setHighSpeedAINavigationState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_HIGH_SPEED_AI_NAVIGATION_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setHighSpeedAINavigationState(i);
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

    public int setUrbanAIAssistanceState(int i) {
        Log.v(TAG, "setUrbanAIAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setUrbanAIAssistanceState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_URBAN_AI_ASSISTANCE_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setUrbanAIAssistanceState(i);
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

    public int setE2EAIAssistanceState(int i) {
        Log.v(TAG, "setE2EAIAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setE2EAIAssistanceState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_E2E_AI_ASSISTANCE_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setE2EAIAssistanceState(i);
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

    public int setInteractiveLaneChangeAssistanceState(int i) {
        Log.v(TAG, "setInteractiveLaneChangeAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setInteractiveLaneChangeAssistanceState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setInteractiveLaneChangeAssistanceState(i);
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

    public int setAINavigationState(int i) {
        Log.v(TAG, "setAINavigationState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAINavigationState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_NAVIGATION_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAINavigationState(i);
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

    public int setLaneAssistType(int i) {
        Log.v(TAG, "setLaneAssistType is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_LANE_ASSIST_TYPE, i);
            Log.v(TAG, "setLaneAssistType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setLaneAssistType(i);
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

    public int setAlarmType(int i) {
        Log.v(TAG, "setAlarmType is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAlarmType mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_ALARM_TYPE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAlarmType(i);
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

    public int setTrafficSignRecognizeState(int i) {
        Log.v(TAG, "setTrafficSignRecognizeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setTrafficSignRecognizeState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_TRAFFIC_SIGN_RECOGNIZE_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficSignRecognizeState(i);
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

    public int setAiCareState(int i) {
        Log.v(TAG, "setAiCareState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAiCareState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_CARE_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAiCareState(i);
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

    public int setMistakeGasState(int i) {
        Log.v(TAG, "setMistakeGasState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setMistakeGasState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_MISTAKE_GAS_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setMistakeGasState(i);
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

    public int setAiMatrixLightState(int i) {
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
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAiMatrixLightState(i);
    }

    @Deprecated
    public int getAiDistanceLightState() {
        Log.v(TAG, "getAiDistanceLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAiDistanceLightState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
        }
        return 0;
    }

    public int setAiDistanceLightState(int i) {
        Log.v(TAG, "setAiDistanceLightState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAiDistanceLightState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_DISTANCE_LIGHT_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAiDistanceLightState(i);
    }

    public int getTrafficLightRecognizeState() {
        Log.v(TAG, "getTrafficLightRecognizeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTrafficLightRecognizeState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
        }
        return 0;
    }

    public int setTrafficLightRecognizeState(int i) {
        Log.v(TAG, "setTrafficLightRecognizeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setTrafficLightRecognizeState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_TRAFFIC_LIGHT_RECOGNIZE_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficLightRecognizeState(i);
    }

    @Deprecated
    public int getEmergencyLaneAssistanceState() {
        Log.v(TAG, "getEmergencyLaneAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEmergencyLaneAssistanceState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
        }
        return 0;
    }

    public int setEmergencyLaneAssistanceState(int i) {
        Log.v(TAG, "setEmergencyLaneAssistanceState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setEmergencyLaneAssistanceState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_EMERGENCY_LANE_ASSISTANCE_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setEmergencyLaneAssistanceState(i);
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

    public int setBlindMonitorState(int i) {
        Log.v(TAG, "setBlindMonitorState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBlindMonitorState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_BLIND_MONITOR_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setBlindMonitorState(i);
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

    public int setOpenDoorAlertState(int i) {
        Log.v(TAG, "setOpenDoorAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setOpenDoorAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_OPEN_DOOR_ALERT_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setOpenDoorAlertState(i);
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

    public int setRearCrashAlertState(int i) {
        Log.v(TAG, "setRearCrashAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearCrashAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_REAR_CRASH_ALERT_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setRearCrashAlertState(i);
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

    public int setRearTrafficCrossAlertState(int i) {
        Log.v(TAG, "setRearTrafficCrossAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearTrafficCrossAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_REAR_TRAFFIC_CROSS_ALERT_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setRearTrafficCrossAlertState(i);
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

    public int setRearTrafficBrakeAlertState(int i) {
        Log.v(TAG, "setRearTrafficBrakeAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearTrafficBrakeAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_REAR_TRAFFIC_BRAKE_ALERT_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setRearTrafficBrakeAlertState(i);
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

    public int setAiEmergencyBrakeState(int i) {
        Log.v(TAG, "setAiEmergencyBrakeState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAiEmergencyBrakeState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AI_EMERGENCY_BRAKE_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAiEmergencyBrakeState(i);
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

    public int setFrontTrafficCrossAlertState(int i) {
        Log.v(TAG, "setFrontTrafficCrossAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setFrontTrafficCrossAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_FRONT_TRAFFIC_CROSS_ALERT_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFrontTrafficCrossAlertState(i);
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

    public int setFrontTrafficBrakeAlertState(int i) {
        Log.v(TAG, "setFrontTrafficBrakeAlertState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setFrontTrafficBrakeAlertState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_FRONT_TRAFFIC_BRAKE_ALERT_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFrontTrafficBrakeAlertState(i);
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

    public int setElecCarBodyStablySysState(int i) {
        Log.v(TAG, "setElecCarBodyStablySysState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setElecCarBodyStablySysState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_ELEC_CAR_BODY_STABLY_SYS_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setElecCarBodyStablySysState(i);
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

    public int setAutoParkingState(int i) {
        Log.v(TAG, "setAutoParkingState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAutoParkingState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_AUTO_PARKING_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAutoParkingState(i);
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

    public int setSteepDescentSlowDownState(int i) {
        Log.v(TAG, "setSteepDescentSlowDownState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setSteepDescentSlowDownState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_STEEP_DESCENT_SLOW_DOWN_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSteepDescentSlowDownState(i);
    }

    public int getFatigueDetectionAidState() {
        Log.v(TAG, "getFatigueDetectionAidState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFatigueDetectionAidState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setFatigueDetectionAidState(int i) {
        Log.v(TAG, "setFatigueDetectionAidState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setFatigueDetectionAidState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_FATIGUE_DETECTION_AID_STATE, i);
        }
        return 0;
    }

    public int getRelieveFatigueState() {
        Log.v(TAG, "getRelieveFatigueState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRelieveFatigueState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setRelieveFatigueState(int i) {
        Log.v(TAG, "setRelieveFatigueState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRelieveFatigueState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_RELIEVE_FATIGUE_STATE, i);
        }
        return 0;
    }

    public int getRecommendRestSpotState() {
        Log.v(TAG, "getRecommendRestSpotState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRecommendRestSpotState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setRecommendRestSpotState(int i) {
        Log.v(TAG, "setRecommendRestSpotState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRecommendRestSpotState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_RECOMMEND_REST_SPOT_STATE, i);
        }
        return 0;
    }

    public int getTemperatureWhileFatigue() {
        Log.v(TAG, "getTemperatureWhileFatigue is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTemperatureWhileFatigue mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setTemperatureWhileFatigue(int i) {
        Log.v(TAG, "setTemperatureWhileFatigue is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setTemperatureWhileFatigue mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_TEMPERATURE_WHILE_FATIGUE, i);
        }
        return 0;
    }

    public int getDistractionDetectionAidState() {
        Log.v(TAG, "getDistractionDetectionAidState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDistractionDetectionAidState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setDistractionDetectionAidState(int i) {
        Log.v(TAG, "setDistractionDetectionAidState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setDistractionDetectionAidState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DISTRACTION_DETECTION_AID_STATE, i);
        }
        return 0;
    }

    public int getAttentionDetectionState() {
        Log.v(TAG, "getAttentionDetectionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAttentionDetectionState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setAttentionDetectionState(int i) {
        Log.v(TAG, "setAttentionDetectionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAttentionDetectionState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_ATTENTION_DETECTION_STATE, i);
        }
        return 0;
    }

    public int getDangerousActionDetectionState() {
        Log.v(TAG, "getDangerousActionDetectionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDangerousActionDetectionState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setDangerousActionDetectionState(int i) {
        Log.v(TAG, "setDangerousActionDetectionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setDangerousActionDetectionState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DANGEROUS_ACTION_DETECTION_STATE, i);
        }
        return 0;
    }

    public int getGazePerceptionState() {
        Log.v(TAG, "getGazePerceptionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getGazePerceptionState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setGazePerceptionState(int i) {
        Log.v(TAG, "setGazePerceptionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setGazePerceptionState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_GAZE_PERCEPTION_STATE, i);
        }
        return 0;
    }

    public int getChildCareState() {
        Log.v(TAG, "getChildCareState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getChildCareState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setChildCareState(int i) {
        Log.v(TAG, "setChildCareState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setChildCareState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_CHILD_CARE_STATE, i);
        }
        return 0;
    }

    public int getGestureRecognitionState() {
        Log.v(TAG, "getGestureRecognitionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getGestureRecognitionState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setGestureRecognitionState(int i) {
        Log.v(TAG, "setGestureRecognitionState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setGestureRecognitionState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_GESTURE_RECOGNITION_STATE, i);
        }
        return 0;
    }

    public int getLeftBehindReminderState() {
        Log.v(TAG, "getLeftBehindReminderState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeftBehindReminderState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setLeftBehindReminderState(int i) {
        Log.v(TAG, "setLeftBehindReminderState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setLeftBehindReminderState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_LEFT_BEHIND_REMINDER_STATE, i);
        }
        return 0;
    }

    public int getDNPState() {
        Log.v(TAG, "getDNPState is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDNPState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int requestHUNPClose() {
        Log.v(TAG, "requestHUNPClose is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "requestHUNPClose mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.REQUEST_HUNP_CLOSE, 0);
        }
        return 0;
    }

    public int getSupportIntelligentNavigation() {
        Log.v(TAG, "getSupportIntelligentNavigation is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntelligentNavigation mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportLaneAssistMode() {
        Log.v(TAG, "getSupportLaneAssistMode is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneAssistMode mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportShakeAlert() {
        Log.v(TAG, "getSupportShakeAlert is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportShakeAlert mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportTrafficSignsRecognition() {
        Log.v(TAG, "getSupportTrafficSignsRecognition is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportTrafficSignsRecognition mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportPredictCrashAlert() {
        Log.v(TAG, "getSupportPredictCrashAlert is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportPredictCrashAlert mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportAiEmergencyBrake() {
        Log.v(TAG, "getSupportAiEmergencyBrake is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiEmergencyBrake mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportAiCare() {
        Log.v(TAG, "getSupportAiCare is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiCare mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportMistakeGas() {
        Log.v(TAG, "getSupportMistakeGas is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportMistakeGas mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportAutoParking() {
        Log.v(TAG, "getSupportAutoParking is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAutoParking mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportBlindSpotAssist() {
        Log.v(TAG, "getSupportBlindSpotAssist is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportBlindSpotAssist mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportRearCrossBrake() {
        Log.v(TAG, "getSupportRearCrossBrake is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearCrossBrake mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportAiDistanceLight() {
        Log.v(TAG, "getSupportAiDistanceLight is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiDistanceLight mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportAiMatrixLight() {
        Log.v(TAG, "getSupportAiMatrixLight is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiMatrixLight mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportPanoramaImage() {
        Log.v(TAG, "getSupportPanoramaImage is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportPanoramaImage mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSupportESP() {
        Log.v(TAG, "getSupportESP is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportESP mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public void registerListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        registerListener(absBYDAutoDiPilotListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe dipilot listener " + absBYDAutoDiPilotListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.v(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoDiPilotListener(absBYDAutoDiPilotListener);
        }
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_COMMON);
        if (!this.mAbsBYDAutoDiPilotListeners.contains(absBYDAutoDiPilotListener)) {
            this.mAbsBYDAutoDiPilotListeners.add(absBYDAutoDiPilotListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoDiPilotListener);
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }

    public void unregisterListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_COMMON);
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoDiPilotListener(null);
        }
        if (absBYDAutoDiPilotListener == null) {
            return;
        }
        this.mAbsBYDAutoDiPilotListeners.remove(absBYDAutoDiPilotListener);
        unregisterConnectListener(absBYDAutoDiPilotListener);
    }

    public int getDriveRemindState() {
        Log.v(TAG, "getDriveRemindState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveRemindState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setDriveRemindState(int i) {
        Log.v(TAG, "setDriveRemindState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DRIVE_REMIND_STATE, i);
            Log.v(TAG, "setDriveRemindState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getDriveDepartureState() {
        Log.v(TAG, "getDriveDepartureState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveDepartureState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int setDriveDeparturState(int i) {
        Log.v(TAG, "setDriveDeparturState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_DRIVE_DEPARTUR_STATE, i);
            Log.v(TAG, "setDriveDeparturState mForTestValue is" + this.mForTest + " execute return");
        }
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

    public int setSpeedLimitRange(int i) {
        Log.v(TAG, "setSpeedLimitRange is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_SPEED_LIMIT_RANGE, i);
            Log.v(TAG, "setSpeedLimitRange mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSpeedLimitRange(i);
    }

    @Deprecated
    public int getIntelligentSpeedLimitAlertState() {
        Log.v(TAG, "getIntelligentSpeedLimitAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentSpeedLimitAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
        }
        return 0;
    }

    public int setIntelligentSpeedLimitAlertState(int i) {
        Log.v(TAG, "setIntelligentSpeedLimitAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_INTELLIGENT_SPEED_LIMIT_ALERT_STATE, i);
            Log.v(TAG, "setIntelligentSpeedLimitAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIntelligentSpeedLimitAlertState(i);
    }

    @Deprecated
    public int getIntelligentSpeedLimitControlState() {
        Log.v(TAG, "getIntelligentSpeedLimitControlState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentSpeedLimitControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
        }
        return 0;
    }

    public int setIntelligentSpeedLimitControlState(int i) {
        Log.v(TAG, "setIntelligentSpeedLimitControlState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE, i);
            Log.v(TAG, "setIntelligentSpeedLimitControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIntelligentSpeedLimitControlState(i);
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

    public int setVerifyMode(int i) {
        Log.v(TAG, "setVerifyMode is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_VERIFY_MODE, i);
            Log.v(TAG, "setVerifyMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setVerifyMode(i);
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

    public int setPredictCrashAlertState(int i) {
        Log.v(TAG, "setPredictCrashAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_PREDICT_CRASH_ALERT_STATE, i);
            Log.v(TAG, "setPredictCrashAlertState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setPredictCrashAlertState(i);
    }

    public int setPredictCrashAlertMode(int i) {
        Log.v(TAG, "setPredictCrashAlertMode is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDiPilotManagerImplCallback.SET_PREDICT_CRASH_ALERT_STATE, i);
            Log.v(TAG, "setPredictCrashAlertMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "carAssistAdapter still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setPredictCrashAlertMode(i);
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

    public int setCpdStatus(int i) {
        Log.v(TAG, "setCpdStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setCpdStatus(i);
    }

    public int setDmsSwitchStatus(int i) {
        Log.v(TAG, "setDmsSwitchStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDmsSwitchStatus(i);
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

    public int setTsrLitSpdChgSwitchState(int i) {
        Log.v(TAG, "setTsrLitSpdChgSwitchState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTsrLitSpdChgSwitchState(i);
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

    public int setLowSpeedEmergencyBrakingState(int i) {
        Log.v(TAG, "setLowSpeedEmergencyBrakingState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setLowSpeedEmergencyBrakingState(i);
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

    public int setSideDistanceWarningState(int i) {
        Log.v(TAG, "setSideDistanceWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSideDistanceWarningState(i);
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

    public int setCollisionWarningState(int i) {
        Log.v(TAG, "setCollisionWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setCollisionWarningState1(i);
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

    public int setIntersectionCollisionAlertState(int i) {
        Log.v(TAG, "setIntersectionCollisionAlertState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIntersectionCollisionAlertState(i);
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

    public int setDoNotPassWarningState(int i) {
        Log.v(TAG, "setDoNotPassWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDoNotPassWarningState(i);
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

    public int setEmergencyBrakeWarningState(int i) {
        Log.v(TAG, "setEmergencyBrakeWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setEmergencyBrakeWarningState(i);
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

    public int setTrafficIncidentWarningState(int i) {
        Log.v(TAG, "setTrafficIncidentWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficIncidentWarningState(i);
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

    public int setAbnormalVehicleWarningState(int i) {
        Log.v(TAG, "setAbnormalVehicleWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAbnormalVehicleWarningState(i);
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

    public int setControlLossWarningState(int i) {
        Log.v(TAG, "setControlLossWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setControlLossWarningState(i);
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

    public int setTrafficJamWarningState(int i) {
        Log.v(TAG, "setTrafficJamWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficJamWarningState(i);
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

    public int setEmergencyVehicleWarningState(int i) {
        Log.v(TAG, "setEmergencyVehicleWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setEmergencyVehicleWarningState(i);
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

    public int setTrafficSignalWarningState(int i) {
        Log.v(TAG, "setTrafficSignalWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficSignalWarningState(i);
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

    public int setTrafficLightInfoPushState(int i) {
        Log.v(TAG, "setTrafficLightInfoPushState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setTrafficLightInfoPushState(i);
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

    public int setRedLightViolationWarningState(int i) {
        Log.v(TAG, "setRedLightViolationWarningState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setRedLightViolationWarningState(i);
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

    public int setGreenLightOptimalSpeedAdvisoryState(int i) {
        Log.v(TAG, "setGreenLightOptimalSpeedAdvisoryState is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setGreenLightOptimalSpeedAdvisoryState(i);
    }

    public int setFatigueMonitoringStatus(int i) {
        Log.v(TAG, "setFatigueMonitoringStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFatigueMonitoringStatus(i);
    }

    public int setDmsSelfCheckStatus(int i) {
        Log.v(TAG, "setDmsSelfCheckStatus is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDmsSelfCheckStatus(i);
    }

    public int setDistractionMonitoringAttention(int i) {
        Log.v(TAG, "setDistractionMonitoringAttention is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDistractionMonitoringAttention(i);
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

    public int setFatigueReliefSwitch(int i) {
        Log.v(TAG, "setFatigueReliefSwitch is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFatigueReliefSwitch(i);
    }

    public int setDangerousActionfSwitch(int i) {
        Log.v(TAG, "setDangerousActionfSwitch is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setDangerousActionfSwitch(i);
    }

    public int setLdwStaticCalibration(int i) {
        Log.v(TAG, "setLdwStaticCalibration is execute");
        enforcePermission(BYDAutoDiPilotDevice.PERMISSION_BYDAUTO_ADAS_SET);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        this.mTsManagerImpl.getCarAssistAdapterManager().setLdwStaticCalibration(i);
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

    public int setBsisCollicionAlarmSwitchS(int i) {
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
        return this.mTsManagerImpl.getCarAssistAdapterManager().setBsisCollicionAlarmSwitchS(i);
    }

    public int setMoisFunctionSwitchS(int i) {
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
        return this.mTsManagerImpl.getCarAssistAdapterManager().setMoisFunctionSwitchS(i);
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

    public int setIsliWarningLevelSwitchStatus(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setIsliWarningLevelSwitchStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIsliWarningLevelSwitchStatus(i);
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

    public int setIopState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setIopState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setIopState(i);
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

    public int setSaocState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSaocState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSaocState(i);
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

    public int setSlifState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSlifState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setSlifState(i);
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

    public int setAccState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAccState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setAccState(i);
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

    public int setFcsrState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setFcsrState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().setFcsrState(i);
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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    switch (i) {
                        case 632:
                            bYDAutoEventValue.intValue = getDmsSwitchStatus();
                            sparseArray.put(632, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 633:
                            bYDAutoEventValue.intValue = getMistakeGasState();
                            sparseArray.put(633, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2826:
                            bYDAutoEventValue.intValue = getAiMatrixLight3ACState();
                            sparseArray.put(2826, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2835:
                            bYDAutoEventValue.intValue = getAiMatrixLight3ADState();
                            sparseArray.put(2835, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3133:
                            bYDAutoEventValue.intValue = getAiEmergencyBrakeState();
                            sparseArray.put(3133, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3151:
                            bYDAutoEventValue.intValue = getBlindMonitorState2();
                            sparseArray.put(3151, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3152:
                            bYDAutoEventValue.intValue = getBlindMonitorState1();
                            sparseArray.put(3152, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3164:
                            bYDAutoEventValue.intValue = getSteepDescentSlowDownState();
                            sparseArray.put(3164, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3169:
                            bYDAutoEventValue.intValue = getOpenDoorAlertState();
                            sparseArray.put(3169, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3182:
                            bYDAutoEventValue.intValue = getEmergencyLaneAssistance32FState();
                            sparseArray.put(3182, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3189:
                            bYDAutoEventValue.intValue = getElecCarBodyStablySysState();
                            sparseArray.put(3189, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3194:
                            bYDAutoEventValue.intValue = getFrontTrafficCrossAlertState();
                            sparseArray.put(3194, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3197:
                            bYDAutoEventValue.intValue = getFrontTrafficBrakeAlertState();
                            sparseArray.put(3197, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3202:
                            bYDAutoEventValue.intValue = getPredictCrashAlert138Mode();
                            sparseArray.put(3202, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3203:
                            bYDAutoEventValue.intValue = getPredictCrashAlert32DMode();
                            sparseArray.put(3203, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3226:
                            bYDAutoEventValue.intValue = getAiDistanceLight316State();
                            sparseArray.put(3226, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3231:
                            bYDAutoEventValue.intValue = getHighSpeedAINavigationState();
                            sparseArray.put(3231, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3236:
                            bYDAutoEventValue.intValue = getInteractiveLaneChangeAssistanceState();
                            sparseArray.put(3236, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3239:
                            bYDAutoEventValue.intValue = getIntelligentSpeedLimitAlert2D5State();
                            sparseArray.put(3239, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3243:
                            bYDAutoEventValue.intValue = getIntelligentSpeedLimitControl2D5State();
                            sparseArray.put(3243, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3244:
                            bYDAutoEventValue.intValue = getIntelligentSpeedLimitControl32FState();
                            sparseArray.put(3244, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3247:
                            bYDAutoEventValue.intValue = getAlarmType();
                            sparseArray.put(3247, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3255:
                            bYDAutoEventValue.intValue = getLaneAssistType();
                            sparseArray.put(3255, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3259:
                            bYDAutoEventValue.intValue = getVerifyMode();
                            sparseArray.put(3259, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3264:
                            bYDAutoEventValue.intValue = getPredictCrashAlertState();
                            sparseArray.put(3264, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3271:
                            bYDAutoEventValue.intValue = getRearCrashAlertState();
                            sparseArray.put(3271, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3277:
                            bYDAutoEventValue.intValue = getRearTrafficCrossAlertState();
                            sparseArray.put(3277, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3279:
                            bYDAutoEventValue.intValue = getRearTrafficBrakeAlertState();
                            sparseArray.put(3279, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3283:
                            bYDAutoEventValue.intValue = getTrafficSignRecognize316State();
                            sparseArray.put(3283, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3285:
                            bYDAutoEventValue.intValue = getTrafficSignRecognize2D5State();
                            sparseArray.put(3285, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3289:
                            bYDAutoEventValue.intValue = getSpeedLimitRange2D5();
                            sparseArray.put(3289, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3297:
                            bYDAutoEventValue.intValue = getAINavigationState();
                            sparseArray.put(3297, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3305:
                            bYDAutoEventValue.intValue = getTrafficLightRecognize2D5State();
                            sparseArray.put(3305, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3306:
                            bYDAutoEventValue.intValue = getTrafficLightRecognize2B4State();
                            sparseArray.put(3306, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3309:
                            bYDAutoEventValue.intValue = getUrbanAIAssistanceState();
                            sparseArray.put(3309, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4554:
                            bYDAutoEventValue.intValue = getEmergencyLaneAssistance32FState();
                            sparseArray.put(4554, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoDiPilotListener> it = this.mAbsBYDAutoDiPilotListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else if (obj instanceof byte[]) {
            } else {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        switch (i) {
            case 633:
                setTsrLitSpdChgSwitchState(iIntValue);
                return 1;
            case 1195:
                setAiCareState(iIntValue);
                return 1;
            case 1196:
                setMistakeGasState(iIntValue);
                return 1;
            case 2792:
                setAiMatrixLightState(iIntValue);
                return 1;
            case 3134:
                setAiEmergencyBrakeState(iIntValue);
                return 1;
            case 3153:
                setBlindMonitorState(iIntValue);
                return 1;
            case 3165:
                setSteepDescentSlowDownState(iIntValue);
                return 1;
            case 3168:
                setOpenDoorAlertState(iIntValue);
                return 1;
            case 3176:
                setE2EAIAssistanceState(iIntValue);
                return 1;
            case 3179:
                setRearTrafficBrakeAlertState(iIntValue);
                return 1;
            case 3181:
                setEmergencyLaneAssistanceState(iIntValue);
                return 1;
            case 3190:
                setElecCarBodyStablySysState(iIntValue);
                return 1;
            case 3193:
                setFrontTrafficCrossAlertState(iIntValue);
                return 1;
            case 3196:
                setFrontTrafficBrakeAlertState(iIntValue);
                return 1;
            case 3201:
                setPredictCrashAlertMode(iIntValue);
                return 1;
            case 3228:
                setAiDistanceLightState(iIntValue);
                return 1;
            case 3230:
                setHighSpeedAINavigationState(iIntValue);
                return 1;
            case 3235:
                setInteractiveLaneChangeAssistanceState(iIntValue);
                return 1;
            case 3238:
                setIntelligentSpeedLimitAlertState(iIntValue);
                return 1;
            case 3242:
                setIntelligentSpeedLimitControlState(iIntValue);
                return 1;
            case 3248:
                setAlarmType(iIntValue);
                return 1;
            case 3256:
                setLaneAssistType(iIntValue);
                return 1;
            case 3260:
                setVerifyMode(iIntValue);
                return 1;
            case 3265:
                setPredictCrashAlertState(iIntValue);
                return 1;
            case 3268:
                setRearTrafficCrossAlertState(iIntValue);
                return 1;
            case 3270:
                setRearCrashAlertState(iIntValue);
                return 1;
            case 3286:
                setTrafficSignRecognizeState(iIntValue);
                return 1;
            case 3291:
                setSpeedLimitRange(iIntValue);
                return 1;
            case 3299:
                setAINavigationState(iIntValue);
                return 1;
            case 3304:
                setTrafficLightRecognizeState(iIntValue);
                return 1;
            case 3308:
                setUrbanAIAssistanceState(iIntValue);
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }
}
