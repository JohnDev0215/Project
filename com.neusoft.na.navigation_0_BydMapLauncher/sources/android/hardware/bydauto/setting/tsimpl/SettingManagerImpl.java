package android.hardware.bydauto.setting.tsimpl;

import android.content.Context;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto_test.TestLightManagerImplCallback;
import android.hardware.bydauto_test.TestSettingManagerImplCallback;
import android.util.SparseArray;
import com.ts.lib.caradapter.setting.OnSettingChangedListener;
import com.ts.lib.caradapter.setting.SettingPacket;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SettingManagerImpl extends AbsTsMyCar {
    private static final int S_API_BRAKE_PEDAL_STATE_COMFORTABLE = 2;
    private static final int S_API_HIGH_SPEED = 3;
    private static final int S_API_OFF = 1;
    private static final int S_API_REFRIGERATOR_OFF = 0;
    private static final int S_API_STATE_OFF = 2;
    private static final int S_API_STATE_ON = 1;
    private static final int S_API_STATE_RESERVED = 1;
    private static final int S_CAN_BRAKE_PEDAL_STATE_COMFORTABLE = 0;
    private static final int S_CAN_BRAKE_PEDAL_STATE_RESERVED = 1;
    private static final int S_CAN_GET_PRETENSIONER_SEATBELT_ON = 0;
    private static final int S_CAN_HIGH_SPEED = 1;
    private static final int S_CAN_OFF = 3;
    private static final int S_CAN_PRETENSIONER_SEATBELT_OFF = 1;
    private static final int S_CAN_REFRIGERATOR_OFF = 3;
    private static final int S_CAN_SET_PRETENSIONER_SEATBELT_OFF = 1;
    private static final int S_CAN_SET_PRETENSIONER_SEATBELT_ON = 2;
    private static final int S_FIVE = 5;
    private static final int S_FOR_TEST_VALUE = 0;
    private static final int S_FOUR = 4;
    private static final int S_INVALID = 0;
    private static final int S_THREE = 3;
    private static final int S_TWO = 2;
    protected static final String TAG = "SettingManagerImpl";
    private static volatile SettingManagerImpl mTsMangerImpl;
    private Context mContext;
    private TestSettingManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 1;
    private final CopyOnWriteArrayList<AbsBYDAutoSettingListener> mAbsBydAutoSettingListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    public void unregisterListener(IBYDAutoListener iBYDAutoListener) {
    }

    private SettingManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSettingChangedListener(new OnSettingChangedListener() { // from class: android.hardware.bydauto.setting.tsimpl.SettingManagerImpl.1
            public void onBinderSettingChanged(boolean z) {
            }

            public void onSettingChanged(int i, int i2, SettingPacket settingPacket) {
                if (SettingManagerImpl.this.mAbsBydAutoSettingListeners.isEmpty()) {
                    return;
                }
                try {
                    SettingManagerImpl.this.settingCallback(i, i2, settingPacket);
                } catch (Exception unused) {
                    Log.e(SettingManagerImpl.TAG, "settingCallback is error");
                }
            }
        });
    }

    public static SettingManagerImpl getInstance(Context context) {
        if (mTsMangerImpl == null) {
            synchronized (SettingManagerImpl.class) {
                if (mTsMangerImpl == null) {
                    mTsMangerImpl = new SettingManagerImpl(context);
                }
            }
        }
        return mTsMangerImpl;
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        for (AbsBYDAutoSettingListener absBYDAutoSettingListener : this.mAbsBydAutoSettingListeners) {
            Log.d(TAG, "onDataEventChanged  id:" + i + " value:" + bYDAutoEventValue.intValue + " listener:" + absBYDAutoSettingListener);
            absBYDAutoSettingListener.onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void settingCallback(int i, int i2, SettingPacket settingPacket) {
        if (settingPacket == null) {
            Log.e(TAG, "settingPacket is null");
        }
        Log.i(TAG, "settingPacket propertyId:" + i);
        switch (i) {
            case 557846608:
                Iterator<AbsBYDAutoSettingListener> it = this.mAbsBydAutoSettingListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAcAutoPurificationStateChanged(settingPacket.getAutoPurificationState());
                }
                break;
            case 557847553:
                Iterator<AbsBYDAutoSettingListener> it2 = this.mAbsBydAutoSettingListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onGoalRoadModeChanged(settingPacket.getGoalRoadModeR());
                }
                break;
            case SettingHalId.ID_MOTOR_PRETENSIONER_SEATBELT_STATE_S /* 557856058 */:
                for (AbsBYDAutoSettingListener absBYDAutoSettingListener : this.mAbsBydAutoSettingListeners) {
                    absBYDAutoSettingListener.onMotorPretensionerSeatbeltStateChanged(settingPacket.getMotorPretensionerSeatbeltState());
                    absBYDAutoSettingListener.onResetMotorPretensionerSeatbelt(settingPacket.getMotorPretensionerSeatbeltState());
                }
                break;
            case 557856063:
                Iterator<AbsBYDAutoSettingListener> it3 = this.mAbsBydAutoSettingListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onAcKeyStartTimeChanged(settingPacket.getRemoteKeyStartAcTime());
                }
                break;
            case SettingHalId.ID_REAR_AC_PANEL_AUTO_LOCK_TIME_R /* 557856073 */:
                Iterator<AbsBYDAutoSettingListener> it4 = this.mAbsBydAutoSettingListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onRearAcPanelAutoLockTimeChanged(settingPacket.getRearAcPanelAutoLockTime());
                }
                break;
            case SettingHalId.ID_DOOR_HEIGHT_SUPPORT /* 557856076 */:
                caseFeatureIds(953, Integer.valueOf(settingPacket.getDoorHeightSupport()));
                break;
            case SettingHalId.ID_STEER_POSITION_AUTO_RETURNS /* 557856146 */:
                Iterator<AbsBYDAutoSettingListener> it5 = this.mAbsBydAutoSettingListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onSteerPositionAutoReturnSwitchChanged(settingPacket.getSteerPositionAutoReturn());
                }
                break;
            case SettingHalId.ID_AMBIENT_LIGHT_COLOR_SUPPORT /* 557856184 */:
                caseFeatureIds(1159, Integer.valueOf(settingPacket.getAmbientLightColorSupport()));
                break;
            case SettingHalId.ID_AMBIENT_LIGHT_SUPPORT /* 557856185 */:
                caseFeatureIds(1158, Integer.valueOf(settingPacket.getAmbientLightSupport()));
                break;
            case SettingHalId.ID_AMBIENT_AREA_SUPPORT /* 557856186 */:
                caseFeatureIds(1154, Integer.valueOf(settingPacket.getAmbientAreaSupport()));
                break;
            case 557856889:
                Iterator<AbsBYDAutoSettingListener> it6 = this.mAbsBydAutoSettingListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onBSISCollisionStateChanged(settingPacket.getBsisCollisionStateR());
                }
                break;
            case 557856891:
                Iterator<AbsBYDAutoSettingListener> it7 = this.mAbsBydAutoSettingListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onBSISFuncStatusChanged(settingPacket.getBsisFuncStatusR());
                }
                break;
            case 557856892:
                Iterator<AbsBYDAutoSettingListener> it8 = this.mAbsBydAutoSettingListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onMOISAssembleStateChanged(settingPacket.getMoisAssembleStateR());
                }
                break;
            case 557856894:
                Iterator<AbsBYDAutoSettingListener> it9 = this.mAbsBydAutoSettingListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onMOISFuncStatusChanged(settingPacket.getMoisFuncStatusR());
                }
                break;
            case SettingHalId.ID_ACBT_WIND_R /* 557857536 */:
                Iterator<AbsBYDAutoSettingListener> it10 = this.mAbsBydAutoSettingListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onACBTWindSwitchChanged(settingPacket.getACBTWind());
                }
                caseFeatureIds(881, Integer.valueOf(settingPacket.getACBTWind()));
                break;
            case SettingHalId.ID_AC_PAUSE_CYCLE_R /* 557857540 */:
                Iterator<AbsBYDAutoSettingListener> it11 = this.mAbsBydAutoSettingListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onACPauseCycleSwitchChanged(settingPacket.getACPauseCycle());
                }
                caseFeatureIds(883, Integer.valueOf(settingPacket.getACPauseCycle()));
                break;
            case SettingHalId.ID_AC_AUTO_AIR_R /* 557857543 */:
                Iterator<AbsBYDAutoSettingListener> it12 = this.mAbsBydAutoSettingListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onACAutoAirModeChanged(settingPacket.getACAutoAir());
                }
                caseFeatureIds(878, Integer.valueOf(settingPacket.getACAutoAir()));
                break;
            case SettingHalId.ID_PM25_POWER_R /* 557857549 */:
                Iterator<AbsBYDAutoSettingListener> it13 = this.mAbsBydAutoSettingListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onPM25PowerSwitchChanged(settingPacket.getPM25Power());
                }
                caseFeatureIds(410, Integer.valueOf(settingPacket.getPM25Power()));
                break;
            case SettingHalId.ID_PM25_SWITCH_CHECK_R /* 557857551 */:
                Iterator<AbsBYDAutoSettingListener> it14 = this.mAbsBydAutoSettingListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onPM25SwitchCheckChanged(settingPacket.getPM25SwitchCheck());
                }
                caseFeatureIds(412, Integer.valueOf(settingPacket.getPM25SwitchCheck()));
                break;
            case SettingHalId.ID_PM25_TIMECHECK_R /* 557857553 */:
                Iterator<AbsBYDAutoSettingListener> it15 = this.mAbsBydAutoSettingListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onPM25TimeCheckChanged(settingPacket.getPM25TimeCheck());
                }
                caseFeatureIds(414, Integer.valueOf(settingPacket.getPM25TimeCheck()));
                break;
            case SettingHalId.ID_AC_TUNNEL_CYCLE_R /* 557857555 */:
                Iterator<AbsBYDAutoSettingListener> it16 = this.mAbsBydAutoSettingListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onACTunnelCycleSwitchChanged(settingPacket.getACTunnelCycle());
                }
                caseFeatureIds(885, Integer.valueOf(settingPacket.getACTunnelCycle()));
                break;
            case SettingHalId.ID_AC_WARM_MODE_R /* 557857557 */:
                Iterator<AbsBYDAutoSettingListener> it17 = this.mAbsBydAutoSettingListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onAcWarmModeChanged(settingPacket.getAcWarmMode());
                }
                caseFeatureIds(3557, Integer.valueOf(settingPacket.getAcWarmMode()));
                break;
            case SettingHalId.ID_INTELLIGENT_AC_AREASTATE_S /* 557857560 */:
                Iterator<AbsBYDAutoSettingListener> it18 = this.mAbsBydAutoSettingListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onIntelligentAcAreaStateChanged(settingPacket.getIntelligentAcAreaState());
                }
                caseFeatureIds(3522, Integer.valueOf(settingPacket.getIntelligentAcAreaState()));
                break;
            case SettingHalId.ID_BACK_DOOR_OPENED_HEIGHT_R /* 557857561 */:
                Iterator<AbsBYDAutoSettingListener> it19 = this.mAbsBydAutoSettingListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onBackDoorOpenedHeightChanged(settingPacket.getBackDoorOpenedHeight());
                }
                caseFeatureIds(388, Integer.valueOf(settingPacket.getBackDoorOpenedHeight()));
                break;
            case SettingHalId.ID_RAIN_AUTO_LOCK_WINDOW_STATE_R /* 557857563 */:
                Iterator<AbsBYDAutoSettingListener> it20 = this.mAbsBydAutoSettingListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onRainAutoLockWindowStateChanged(settingPacket.getRainAutoLockWindowState());
                }
                break;
            case SettingHalId.ID_DOOR_KNOB_AUTO_SHRINK_STATE_R /* 557857565 */:
                Iterator<AbsBYDAutoSettingListener> it21 = this.mAbsBydAutoSettingListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onDoorknobAutoShrinkStateChanged(settingPacket.getDoorknobAutoShrinkState());
                }
                break;
            case SettingHalId.ID_INTELLIGENT_UNLOCK_STATE_R /* 557857567 */:
                Iterator<AbsBYDAutoSettingListener> it22 = this.mAbsBydAutoSettingListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onIntelligentUnlockStateChanged(settingPacket.getIntelligentUnlockState());
                }
                caseFeatureIds(2662, Integer.valueOf(settingPacket.getIntelligentUnlockState()));
                break;
            case SettingHalId.ID_INTELLIGENT_LOCK_STATE_R /* 557857569 */:
                Iterator<AbsBYDAutoSettingListener> it23 = this.mAbsBydAutoSettingListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onIntelligentLockStateChanged(settingPacket.getIntelligentLockState());
                }
                break;
            case SettingHalId.ID_HUD_STATE_R /* 557857571 */:
                Iterator<AbsBYDAutoSettingListener> it24 = this.mAbsBydAutoSettingListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onHudStateChanged(settingPacket.getHudState());
                }
                break;
            case SettingHalId.ID_HUD_HEIGHT_R /* 557857574 */:
                Iterator<AbsBYDAutoSettingListener> it25 = this.mAbsBydAutoSettingListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onHudHeightChanged(settingPacket.getHudHeight());
                }
                break;
            case SettingHalId.ID_HUD_BRIGHTNESS_R /* 557857576 */:
                Iterator<AbsBYDAutoSettingListener> it26 = this.mAbsBydAutoSettingListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onHudBrightnessChanged(settingPacket.getHudBrightness());
                }
                break;
            case SettingHalId.ID_HUD_ROTATION_ANGLE_R /* 557857578 */:
                Iterator<AbsBYDAutoSettingListener> it27 = this.mAbsBydAutoSettingListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onHudRotationAngleChanged(settingPacket.getHudRotationAngle());
                }
                break;
            case SettingHalId.ID_HUD_MODE_R /* 557857580 */:
                Iterator<AbsBYDAutoSettingListener> it28 = this.mAbsBydAutoSettingListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onHudModeChanged(settingPacket.getHudMode());
                }
                break;
            case SettingHalId.ID_HUD_DISPLAY_CONTENT_R_DRIVE /* 557857582 */:
                Iterator<AbsBYDAutoSettingListener> it29 = this.mAbsBydAutoSettingListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onHudDisplayContentChanged(1, settingPacket.getHudDisplayContentRDrive());
                }
                break;
            case SettingHalId.ID_HUD_DISPLAY_CONTENT_R_NAVIGATION /* 557857583 */:
                Iterator<AbsBYDAutoSettingListener> it30 = this.mAbsBydAutoSettingListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onHudDisplayContentChanged(2, settingPacket.getHudDisplayContentRNavigation());
                }
                break;
            case SettingHalId.ID_HUD_DISPLAY_CONTENT_S_DRIVE /* 557857584 */:
                Iterator<AbsBYDAutoSettingListener> it31 = this.mAbsBydAutoSettingListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onResetHudDisplayContent(settingPacket.getHudDisplayContentSDrive());
                }
                break;
            case SettingHalId.ID_HUD_DISPLAY_CONTENT_S_NAVIGATION /* 557857585 */:
                Iterator<AbsBYDAutoSettingListener> it32 = this.mAbsBydAutoSettingListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onResetHudDisplayContent(settingPacket.getHudDisplayContentSNavigation());
                }
                break;
            case SettingHalId.ID_ENGINE_SOUND_TYPE_R /* 557857587 */:
                Iterator<AbsBYDAutoSettingListener> it33 = this.mAbsBydAutoSettingListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onEngineSoundTypeChanged(settingPacket.getEngineSoundType());
                }
                caseFeatureIds(3586, Integer.valueOf(settingPacket.getEngineSoundType()));
                break;
            case SettingHalId.ID_SPEED_REMINDER_VALUE_R /* 557857589 */:
                Iterator<AbsBYDAutoSettingListener> it34 = this.mAbsBydAutoSettingListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onSpeedReminderValueChanged(settingPacket.getSpeedReminderValue());
                }
                break;
            case SettingHalId.ID_REAR_VIEW_MIRROR_FLIP_R /* 557857591 */:
                Iterator<AbsBYDAutoSettingListener> it35 = this.mAbsBydAutoSettingListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onRearViewMirrorFlipChanged(settingPacket.getRearViewMirrorFlip());
                }
                caseFeatureIds(416, Integer.valueOf(settingPacket.getRearViewMirrorFlip()));
                break;
            case SettingHalId.ID_REAR_VIEW_MIRROR_FLIP_S_WITH_MEMORY /* 557857592 */:
                Iterator<AbsBYDAutoSettingListener> it36 = this.mAbsBydAutoSettingListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onRearViewMirrorFlipSetChanged(settingPacket.getRearViewMirrorFlipSet());
                }
                caseFeatureIds(417, Integer.valueOf(settingPacket.getRearViewMirrorFlipSet()));
                break;
            case SettingHalId.ID_AUTO_POWER_ON_STATE_S /* 557857595 */:
                Iterator<AbsBYDAutoSettingListener> it37 = this.mAbsBydAutoSettingListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onAutoPowerOnStateChanged(settingPacket.getAutoPowerOnState());
                }
                caseFeatureIds(520, Integer.valueOf(settingPacket.getAutoPowerOnState()));
                break;
            case SettingHalId.ID_AUTO_POWER_OFF_STATE_S /* 557857597 */:
                Iterator<AbsBYDAutoSettingListener> it38 = this.mAbsBydAutoSettingListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onAutoPowerOffStateChanged(settingPacket.getAutoPowerOffState());
                }
                break;
            case SettingHalId.ID_DRIVER_SEAT_AUTO_RETURNS /* 557857599 */:
                Iterator<AbsBYDAutoSettingListener> it39 = this.mAbsBydAutoSettingListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onDriverSeatAutoReturnStateChanged(settingPacket.getDriverSeatAutoReturn());
                }
                caseFeatureIds(397, Integer.valueOf(settingPacket.getDriverSeatAutoReturn()));
                break;
            case SettingHalId.ID_BRAKE_PEDAL_MODE_S /* 557857603 */:
                Iterator<AbsBYDAutoSettingListener> it40 = this.mAbsBydAutoSettingListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onBrakePedalModeChanged(settingPacket.getBrakePedalMode());
                }
                break;
            case SettingHalId.ID_COMFORTABLE_PARKING_STATE_S /* 557857605 */:
                Iterator<AbsBYDAutoSettingListener> it41 = this.mAbsBydAutoSettingListeners.iterator();
                while (it41.hasNext()) {
                    it41.next().onComfortableParkingStateChanged(settingPacket.getComfortableParkingState());
                }
                break;
            case SettingHalId.ID_AUTO_RAIN_WIPER_STATE_S /* 557857609 */:
                Iterator<AbsBYDAutoSettingListener> it42 = this.mAbsBydAutoSettingListeners.iterator();
                while (it42.hasNext()) {
                    it42.next().onAutoRainWiperStateChanged(settingPacket.getresetMotorPretensionerSeatbelt());
                }
                break;
            case SettingHalId.ID_DYNAMIC_SUSPENSION_MODE_S /* 557857611 */:
                Iterator<AbsBYDAutoSettingListener> it43 = this.mAbsBydAutoSettingListeners.iterator();
                while (it43.hasNext()) {
                    it43.next().onDynamicSuspensionModeChanged(settingPacket.getDynamicSuspensionMode());
                }
                break;
            case SettingHalId.ID_REMOTE_CONTROL_UPWINDOW_STATE_S /* 557857613 */:
                Iterator<AbsBYDAutoSettingListener> it44 = this.mAbsBydAutoSettingListeners.iterator();
                while (it44.hasNext()) {
                    it44.next().onRemoteControlUpwindowStateChanged(settingPacket.getRemoteControlUpwindowState());
                }
                caseFeatureIds(419, Integer.valueOf(settingPacket.getRemoteControlUpwindowState()));
                break;
            case SettingHalId.ID_REMOTE_CONTROL_DOWNWINDOW_STATE_S_48E /* 557857615 */:
                Iterator<AbsBYDAutoSettingListener> it45 = this.mAbsBydAutoSettingListeners.iterator();
                while (it45.hasNext()) {
                    it45.next().onRemoteControlSwitchChanged(settingPacket.getRemoteControlDownwindowState());
                }
                caseFeatureIds(395, Integer.valueOf(settingPacket.getRemoteControlDownwindowState()));
                break;
            case SettingHalId.ID_MICRO_SWITCH_LOCK_WINDOW_STATE_R /* 557857617 */:
                Iterator<AbsBYDAutoSettingListener> it46 = this.mAbsBydAutoSettingListeners.iterator();
                while (it46.hasNext()) {
                    it46.next().onMicroSwitchLockWindowStateChanged(settingPacket.getMicroSwitchLockWindowState());
                }
                caseFeatureIds(408, Integer.valueOf(settingPacket.getMicroSwitchLockWindowState()));
                break;
            case SettingHalId.ID_MICRO_SWITCH_UNLOCK_WINDOW_STATE_R /* 557857619 */:
                Iterator<AbsBYDAutoSettingListener> it47 = this.mAbsBydAutoSettingListeners.iterator();
                while (it47.hasNext()) {
                    it47.next().onMicroSwitchUnlockWindowStateChanged(settingPacket.getMicroSwitchUnlockWindowState());
                }
                caseFeatureIds(406, Integer.valueOf(settingPacket.getMicroSwitchUnlockWindowState()));
                break;
            case SettingHalId.ID_LOCK_CAR_RISE_WINDOW_R48E /* 557857621 */:
                Iterator<AbsBYDAutoSettingListener> it48 = this.mAbsBydAutoSettingListeners.iterator();
                while (it48.hasNext()) {
                    it48.next().onLockCarRiseWindowStateChange(settingPacket.getLockCarRiseWindow());
                }
                caseFeatureIds(403, Integer.valueOf(settingPacket.getLockCarRiseWindow()));
                break;
            case SettingHalId.ID_LEFT_HOME_LIGHT_DELAY_VALUE_R /* 557857624 */:
                Iterator<AbsBYDAutoSettingListener> it49 = this.mAbsBydAutoSettingListeners.iterator();
                while (it49.hasNext()) {
                    it49.next().onLeftHomeLightDelayValueChanged(settingPacket.getLeftHomeLightDelayValue());
                }
                break;
            case SettingHalId.ID_BACK_HOME_LIGHT_DELAY_VALUE_R /* 557857626 */:
                Iterator<AbsBYDAutoSettingListener> it50 = this.mAbsBydAutoSettingListeners.iterator();
                while (it50.hasNext()) {
                    it50.next().onBackHomeLightDelayValueChanged(settingPacket.getBackHomeLightDelayValue());
                }
                break;
            case SettingHalId.ID_SEAT_HORIZATION_DRIVER /* 557857664 */:
                Iterator<AbsBYDAutoSettingListener> it51 = this.mAbsBydAutoSettingListeners.iterator();
                while (it51.hasNext()) {
                    it51.next().onTurnSeatHorizationChanged(1, 1, settingPacket.getSeatHorizationDriver());
                }
                break;
            case SettingHalId.ID_SEAT_HORIZATION_COPILOT /* 557857665 */:
                Iterator<AbsBYDAutoSettingListener> it52 = this.mAbsBydAutoSettingListeners.iterator();
                while (it52.hasNext()) {
                    it52.next().onTurnSeatHorizationChanged(2, 1, settingPacket.getSeatHorizationCopilot());
                }
                break;
            case SettingHalId.ID_SEAT_HORIZATION_BACK_LEFT /* 557857666 */:
                Iterator<AbsBYDAutoSettingListener> it53 = this.mAbsBydAutoSettingListeners.iterator();
                while (it53.hasNext()) {
                    it53.next().onTurnSeatHorizationChanged(3, 1, settingPacket.getSeatHorizationBackLeft());
                }
                break;
            case SettingHalId.ID_SEAT_HORIZATION_BACK_RIGHT /* 557857667 */:
                Iterator<AbsBYDAutoSettingListener> it54 = this.mAbsBydAutoSettingListeners.iterator();
                while (it54.hasNext()) {
                    it54.next().onTurnSeatHorizationChanged(4, 1, settingPacket.getSeatHorizationBackRight());
                }
                break;
            case SettingHalId.ID_HEADREST_HORIZATION_DRIVER /* 557857688 */:
                Iterator<AbsBYDAutoSettingListener> it55 = this.mAbsBydAutoSettingListeners.iterator();
                while (it55.hasNext()) {
                    it55.next().onHeadrestTurnHorizationChanged(1, 1, settingPacket.getHeadrestHorizationDriver());
                }
                break;
            case SettingHalId.ID_HEADREST_HORIZATION_COPILOT /* 557857689 */:
                Iterator<AbsBYDAutoSettingListener> it56 = this.mAbsBydAutoSettingListeners.iterator();
                while (it56.hasNext()) {
                    it56.next().onHeadrestTurnHorizationChanged(2, 2, settingPacket.getHeadrestHorizationCopilot());
                }
                break;
            case SettingHalId.ID_HEADREST_HORIZATION_BACK_LEFT /* 557857690 */:
                Iterator<AbsBYDAutoSettingListener> it57 = this.mAbsBydAutoSettingListeners.iterator();
                while (it57.hasNext()) {
                    it57.next().onHeadrestTurnHorizationChanged(3, 1, settingPacket.getHeadrestHorizationBackLeft());
                }
                break;
            case SettingHalId.ID_HEADREST_HORIZATION_BACK_RIGHT /* 557857691 */:
                Iterator<AbsBYDAutoSettingListener> it58 = this.mAbsBydAutoSettingListeners.iterator();
                while (it58.hasNext()) {
                    it58.next().onHeadrestTurnHorizationChanged(4, 2, settingPacket.getHeadrestHorizationBackRight());
                }
                break;
            case SettingHalId.ID_REFRIGERATOR_MODE_R /* 557857699 */:
                for (AbsBYDAutoSettingListener absBYDAutoSettingListener2 : this.mAbsBydAutoSettingListeners) {
                    int refrigeratorMode = settingPacket.getRefrigeratorMode();
                    if (refrigeratorMode == 3) {
                        refrigeratorMode = 0;
                    }
                    absBYDAutoSettingListener2.onRefrigeratorModeChanged(refrigeratorMode);
                }
                break;
            case SettingHalId.ID_REMENBER_POWER_OFF_STATER /* 557857701 */:
                Iterator<AbsBYDAutoSettingListener> it59 = this.mAbsBydAutoSettingListeners.iterator();
                while (it59.hasNext()) {
                    it59.next().onRemenberPowerOffStateChanged(settingPacket.getRemenberPowerOffState());
                }
                break;
            case SettingHalId.ID_DISINF_STATE_R /* 557857705 */:
                Iterator<AbsBYDAutoSettingListener> it60 = this.mAbsBydAutoSettingListeners.iterator();
                while (it60.hasNext()) {
                    it60.next().onDisinfStateChanged(settingPacket.getDisinfState());
                }
                break;
            case SettingHalId.ID_TRAY_STATE_R /* 557857706 */:
                Iterator<AbsBYDAutoSettingListener> it61 = this.mAbsBydAutoSettingListeners.iterator();
                while (it61.hasNext()) {
                    it61.next().onTrayActionStateChanged(settingPacket.getTrayState());
                }
                break;
            case SettingHalId.ID_REFRIGERATOR_STATE /* 557857708 */:
                Iterator<AbsBYDAutoSettingListener> it62 = this.mAbsBydAutoSettingListeners.iterator();
                while (it62.hasNext()) {
                    it62.next().onRefrigeratorState(settingPacket.getRefrigeratorState());
                }
                break;
            case 557857710:
                Iterator<AbsBYDAutoSettingListener> it63 = this.mAbsBydAutoSettingListeners.iterator();
                while (it63.hasNext()) {
                    it63.next().onIALColorChanged(5, settingPacket.getIALColorStarRing());
                }
                caseFeatureIds(2845, Integer.valueOf(settingPacket.getIALColorStarRing()));
                break;
            case 557857711:
                Iterator<AbsBYDAutoSettingListener> it64 = this.mAbsBydAutoSettingListeners.iterator();
                while (it64.hasNext()) {
                    it64.next().onIALColorChanged(4, settingPacket.getIALColorStarrySky());
                }
                break;
            case 557857712:
                Iterator<AbsBYDAutoSettingListener> it65 = this.mAbsBydAutoSettingListeners.iterator();
                while (it65.hasNext()) {
                    it65.next().onIALColorChanged(1, settingPacket.getIALColorFront());
                }
                caseFeatureIds(1161, Integer.valueOf(settingPacket.getIALColorFront()));
                break;
            case 557857713:
                Iterator<AbsBYDAutoSettingListener> it66 = this.mAbsBydAutoSettingListeners.iterator();
                while (it66.hasNext()) {
                    it66.next().onIALColorChanged(2, settingPacket.getIALColorBack());
                }
                caseFeatureIds(1157, Integer.valueOf(settingPacket.getIALColorBack()));
                break;
            case 557857716:
                Iterator<AbsBYDAutoSettingListener> it67 = this.mAbsBydAutoSettingListeners.iterator();
                while (it67.hasNext()) {
                    it67.next().onIALBrightnessChanged(5, settingPacket.getIALBrightnessStarRing());
                }
                caseFeatureIds(2844, Integer.valueOf(settingPacket.getIALBrightnessStarRing()));
                break;
            case 557857717:
                Iterator<AbsBYDAutoSettingListener> it68 = this.mAbsBydAutoSettingListeners.iterator();
                while (it68.hasNext()) {
                    it68.next().onIALBrightnessChanged(4, settingPacket.getIALBrightnessStarrySky());
                }
                break;
            case 557857718:
                Iterator<AbsBYDAutoSettingListener> it69 = this.mAbsBydAutoSettingListeners.iterator();
                while (it69.hasNext()) {
                    it69.next().onIALBrightnessChanged(2, settingPacket.getIALBrightnessBack());
                }
                caseFeatureIds(1156, Integer.valueOf(settingPacket.getIALBrightnessBack()));
                break;
            case 557857719:
                Iterator<AbsBYDAutoSettingListener> it70 = this.mAbsBydAutoSettingListeners.iterator();
                while (it70.hasNext()) {
                    it70.next().onIALBrightnessChanged(1, settingPacket.getIALBrightnessFront());
                }
                caseFeatureIds(1160, Integer.valueOf(settingPacket.getIALBrightnessFront()));
                break;
            case SettingHalId.ID_IAL_AREA_R /* 557857721 */:
                Iterator<AbsBYDAutoSettingListener> it71 = this.mAbsBydAutoSettingListeners.iterator();
                while (it71.hasNext()) {
                    it71.next().onIALAreaChanged(settingPacket.getIALArea());
                }
                caseFeatureIds(1171, Integer.valueOf(settingPacket.getIALArea()));
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_DRIVER_DOMAIN_CONTROL /* 557857723 */:
                Iterator<AbsBYDAutoSettingListener> it72 = this.mAbsBydAutoSettingListeners.iterator();
                while (it72.hasNext()) {
                    it72.next().onSeatHeatingStateChanged(1, settingPacket.getSeatHeatingStateDriverDomainControl());
                }
                caseFeatureIds(1040, Integer.valueOf(settingPacket.getSeatHeatingStateDriverDomainControl()));
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_COPILOT_ECU /* 557857725 */:
                Iterator<AbsBYDAutoSettingListener> it73 = this.mAbsBydAutoSettingListeners.iterator();
                while (it73.hasNext()) {
                    it73.next().onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotECU());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_BACKLEFT_ECU /* 557857726 */:
                Iterator<AbsBYDAutoSettingListener> it74 = this.mAbsBydAutoSettingListeners.iterator();
                while (it74.hasNext()) {
                    it74.next().onSeatHeatingStateChanged(3, settingPacket.getSeatHeatingStateBackLeftECU());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_BACKRIGHT_ECU /* 557857727 */:
                Iterator<AbsBYDAutoSettingListener> it75 = this.mAbsBydAutoSettingListeners.iterator();
                while (it75.hasNext()) {
                    it75.next().onSeatHeatingStateChanged(4, settingPacket.getSeatHeatingStateBackRightECU());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_COPILOT_L1P /* 557857728 */:
                Iterator<AbsBYDAutoSettingListener> it76 = this.mAbsBydAutoSettingListeners.iterator();
                while (it76.hasNext()) {
                    it76.next().onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotL1P());
                }
                caseFeatureIds(1269, Integer.valueOf(settingPacket.getSeatHeatingStateCopilotL1P()));
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_COPILOTNON_L1P /* 557857729 */:
                Iterator<AbsBYDAutoSettingListener> it77 = this.mAbsBydAutoSettingListeners.iterator();
                while (it77.hasNext()) {
                    it77.next().onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotNonL1P());
                }
                break;
            case 557857730:
                Iterator<AbsBYDAutoSettingListener> it78 = this.mAbsBydAutoSettingListeners.iterator();
                while (it78.hasNext()) {
                    it78.next().onSeatHeatingStateChanged(3, settingPacket.getSeatHeatingStateBackLeftDomainControl());
                }
                caseFeatureIds(1283, Integer.valueOf(settingPacket.getSeatHeatingStateBackLeftDomainControl()));
                break;
            case 557857731:
                Iterator<AbsBYDAutoSettingListener> it79 = this.mAbsBydAutoSettingListeners.iterator();
                while (it79.hasNext()) {
                    it79.next().onSeatHeatingStateChanged(4, settingPacket.getSeatHeatingStateBackRightDomainControl());
                }
                caseFeatureIds(1294, Integer.valueOf(settingPacket.getSeatHeatingStateBackRightDomainControl()));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_DRIVER_DOMAIN_CONTROL_R /* 557857733 */:
                Iterator<AbsBYDAutoSettingListener> it80 = this.mAbsBydAutoSettingListeners.iterator();
                while (it80.hasNext()) {
                    it80.next().onSeatVentilatingStateChanged(1, settingPacket.getSeatVentilatingStateDriverDomainControl());
                }
                caseFeatureIds(1044, Integer.valueOf(settingPacket.getSeatVentilatingStateDriverDomainControl()));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_BACK_RIGHT_ECU /* 557857737 */:
                Iterator<AbsBYDAutoSettingListener> it81 = this.mAbsBydAutoSettingListeners.iterator();
                while (it81.hasNext()) {
                    it81.next().onSeatVentilatingStateChanged(4, settingPacket.getSeatVentilatingStateBackRightECU());
                }
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_COPILOT_L1P /* 557857738 */:
                Iterator<AbsBYDAutoSettingListener> it82 = this.mAbsBydAutoSettingListeners.iterator();
                while (it82.hasNext()) {
                    it82.next().onSeatVentilatingStateChanged(2, settingPacket.getSeatVentilatingStateCopilotL1P());
                }
                caseFeatureIds(1273, Integer.valueOf(settingPacket.getSeatVentilatingStateCopilotL1P()));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_COPILOTNON_L1P /* 557857739 */:
                Iterator<AbsBYDAutoSettingListener> it83 = this.mAbsBydAutoSettingListeners.iterator();
                while (it83.hasNext()) {
                    it83.next().onSeatVentilatingStateChanged(2, settingPacket.getSeatVentilatingStateCopilotNonL1P());
                }
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_BACK_LEFT_DOMAIN_CONTROL /* 557857740 */:
                Iterator<AbsBYDAutoSettingListener> it84 = this.mAbsBydAutoSettingListeners.iterator();
                while (it84.hasNext()) {
                    it84.next().onSeatVentilatingStateChanged(3, settingPacket.getSeatVentilatingStateBackLeftDomainControl());
                }
                caseFeatureIds(1290, Integer.valueOf(settingPacket.getSeatVentilatingStateBackLeftDomainControl()));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_BACK_RIGHT_DOMAIN_CONTROL /* 557857741 */:
                Iterator<AbsBYDAutoSettingListener> it85 = this.mAbsBydAutoSettingListeners.iterator();
                while (it85.hasNext()) {
                    it85.next().onSeatVentilatingStateChanged(4, settingPacket.getSeatVentilatingStateBackRightDomainControl());
                }
                caseFeatureIds(1301, Integer.valueOf(settingPacket.getSeatVentilatingStateBackRightDomainControl()));
                break;
            case SettingHalId.ID_LANTERN_LANGUAGE_MODE_R /* 557857744 */:
                Iterator<AbsBYDAutoSettingListener> it86 = this.mAbsBydAutoSettingListeners.iterator();
                while (it86.hasNext()) {
                    it86.next().onLanternLanguageModeChanged(settingPacket.getLanternLanguageMode());
                }
                break;
            case SettingHalId.ID_INS_THEME_R /* 557857745 */:
                Iterator<AbsBYDAutoSettingListener> it87 = this.mAbsBydAutoSettingListeners.iterator();
                while (it87.hasNext()) {
                    it87.next().onINSThemeChanged(settingPacket.getINSTheme());
                }
                break;
            case SettingHalId.ID_HEAD_LAMP_LEVEL_R /* 557857747 */:
                Iterator<AbsBYDAutoSettingListener> it88 = this.mAbsBydAutoSettingListeners.iterator();
                while (it88.hasNext()) {
                    it88.next().onHeadlampLevelChanged(settingPacket.getHeadlampLevel());
                }
                break;
            case SettingHalId.ID_LEFT_HEAD_LAMP_LEVEL /* 557857749 */:
                Iterator<AbsBYDAutoSettingListener> it89 = this.mAbsBydAutoSettingListeners.iterator();
                while (it89.hasNext()) {
                    it89.next().onLeftHeadlampLevelChanged(settingPacket.getLeftHeadlampLevel());
                }
                break;
            case SettingHalId.ID_RIGHT_HEAD_LAMP_LEVEL /* 557857750 */:
                Iterator<AbsBYDAutoSettingListener> it90 = this.mAbsBydAutoSettingListeners.iterator();
                while (it90.hasNext()) {
                    it90.next().onRightHeadlampLevelChanged(settingPacket.getRightHeadlampLevel());
                }
                break;
            case SettingHalId.ID_SMART_WELCOME_LIGHT_STATE_R /* 557857752 */:
                Iterator<AbsBYDAutoSettingListener> it91 = this.mAbsBydAutoSettingListeners.iterator();
                while (it91.hasNext()) {
                    it91.next().onSmartWelcomeLightStateChanged(settingPacket.getSmartWelcomeLightState());
                }
                break;
            case SettingHalId.ID_MASSAGE_LEVEL_LEFT_FRONT /* 557857764 */:
                Iterator<AbsBYDAutoSettingListener> it92 = this.mAbsBydAutoSettingListeners.iterator();
                while (it92.hasNext()) {
                    it92.next().onMassageLevelStateChanged(1, settingPacket.getMassageLevelLeftFront());
                }
                caseFeatureIds(1067, Integer.valueOf(settingPacket.getMassageLevelLeftFront()));
                break;
            case SettingHalId.ID_MASSAGE_LEVEL_RIGHT_FRONT /* 557857766 */:
                Iterator<AbsBYDAutoSettingListener> it93 = this.mAbsBydAutoSettingListeners.iterator();
                while (it93.hasNext()) {
                    it93.next().onMassageLevelStateChanged(2, settingPacket.getMassageLevelRightFront());
                }
                caseFeatureIds(1070, Integer.valueOf(settingPacket.getMassageLevelRightFront()));
                break;
            case SettingHalId.ID_MASSAGE_LEVEL_LEFT_BACK /* 557857768 */:
                Iterator<AbsBYDAutoSettingListener> it94 = this.mAbsBydAutoSettingListeners.iterator();
                while (it94.hasNext()) {
                    it94.next().onMassageLevelStateChanged(3, settingPacket.getMassageLevelLeftBack());
                }
                caseFeatureIds(1286, Integer.valueOf(settingPacket.getMassageLevelLeftBack()));
                break;
            case SettingHalId.ID_MASSAGE_LEVEL_RIGHT_BACK /* 557857771 */:
                Iterator<AbsBYDAutoSettingListener> it95 = this.mAbsBydAutoSettingListeners.iterator();
                while (it95.hasNext()) {
                    it95.next().onMassageLevelStateChanged(4, settingPacket.getMassageLevelRightBack());
                }
                caseFeatureIds(1297, Integer.valueOf(settingPacket.getMassageLevelRightBack()));
                break;
            case SettingHalId.ID_MASSAGE_MODE_R /* 557857776 */:
                Iterator<AbsBYDAutoSettingListener> it96 = this.mAbsBydAutoSettingListeners.iterator();
                while (it96.hasNext()) {
                    it96.next().onMassageModeStateChanged(1, settingPacket.getMassageMode());
                }
                caseFeatureIds(1068, Integer.valueOf(settingPacket.getMassageMode()));
                break;
            case SettingHalId.ID_MASSAGE_MODE_R_4FA /* 557857778 */:
                Iterator<AbsBYDAutoSettingListener> it97 = this.mAbsBydAutoSettingListeners.iterator();
                while (it97.hasNext()) {
                    it97.next().onMassageModeStateChanged(2, settingPacket.getMassageModeR4Fa());
                }
                caseFeatureIds(1071, Integer.valueOf(settingPacket.getMassageModeR4Fa()));
                break;
            case SettingHalId.ID_MASSAGE_MODE_R_4FA_LEFT_BACK /* 557857780 */:
                Iterator<AbsBYDAutoSettingListener> it98 = this.mAbsBydAutoSettingListeners.iterator();
                while (it98.hasNext()) {
                    it98.next().onMassageModeStateChanged(3, settingPacket.getMassageModeR4FaLeftBack());
                }
                caseFeatureIds(1288, Integer.valueOf(settingPacket.getMassageModeR4FaLeftBack()));
                break;
            case SettingHalId.ID_MASSAGE_MODE_R_4FA_LEFT_FRONT /* 557857783 */:
                Iterator<AbsBYDAutoSettingListener> it99 = this.mAbsBydAutoSettingListeners.iterator();
                while (it99.hasNext()) {
                    it99.next().onMassageModeStateChanged(4, settingPacket.getMassageModeR4FaLeftFront());
                }
                caseFeatureIds(1299, Integer.valueOf(settingPacket.getMassageModeR4FaLeftFront()));
                break;
            case SettingHalId.ID_ENERGY_FEEDBACK_R_341 /* 557857788 */:
                Iterator<AbsBYDAutoSettingListener> it100 = this.mAbsBydAutoSettingListeners.iterator();
                while (it100.hasNext()) {
                    it100.next().onEnergyFeedbackStrengthChanged(settingPacket.getEnergyFeedbackR341());
                }
                break;
            case SettingHalId.ID_SOC_TARGET_R /* 557857790 */:
                Iterator<AbsBYDAutoSettingListener> it101 = this.mAbsBydAutoSettingListeners.iterator();
                while (it101.hasNext()) {
                    it101.next().onSOCTargetRangeChange(settingPacket.getSOCTarget());
                }
                break;
            case SettingHalId.ID_CHARGING_PORT_R_27C /* 557857792 */:
                Iterator<AbsBYDAutoSettingListener> it102 = this.mAbsBydAutoSettingListeners.iterator();
                while (it102.hasNext()) {
                    it102.next().onChargingPortSwitchChanged(settingPacket.getChargingPort());
                }
                break;
            case SettingHalId.ID_AUTO_EXTERNAL_REAR_MIRROR_FOLLOW_UP_SWITCH_R /* 557857796 */:
                Iterator<AbsBYDAutoSettingListener> it103 = this.mAbsBydAutoSettingListeners.iterator();
                while (it103.hasNext()) {
                    it103.next().onAutoExternalRearMirrorFollowUpStateChanged(settingPacket.getAutoExternalRearMirrorFollowUpSwitch());
                }
                break;
            case SettingHalId.ID_OVERSPEED_LOCK_R /* 557857799 */:
                Iterator<AbsBYDAutoSettingListener> it104 = this.mAbsBydAutoSettingListeners.iterator();
                while (it104.hasNext()) {
                    it104.next().onOverSpeedLockStateChanged(settingPacket.getOverspeedLock());
                }
                break;
            case SettingHalId.ID_STEER_ASSIS_R /* 557857800 */:
                Iterator<AbsBYDAutoSettingListener> it105 = this.mAbsBydAutoSettingListeners.iterator();
                while (it105.hasNext()) {
                    it105.next().onSteerAssisModeChanged(settingPacket.getSteerAssis());
                }
                break;
            case SettingHalId.ID_STEER_ASSIS_SUPPORTED /* 557857802 */:
                Iterator<AbsBYDAutoSettingListener> it106 = this.mAbsBydAutoSettingListeners.iterator();
                while (it106.hasNext()) {
                    it106.next().onSteerAssisSupportChanged(settingPacket.getSteerAssisSupportedR());
                }
                break;
            case SettingHalId.ID_ON_TRAY_STATE_CHANGED /* 557857804 */:
                Iterator<AbsBYDAutoSettingListener> it107 = this.mAbsBydAutoSettingListeners.iterator();
                while (it107.hasNext()) {
                    it107.next().onTrayStateChanged(0, settingPacket.getonTrayStateChanged());
                }
                break;
            case 557857805:
                Iterator<AbsBYDAutoSettingListener> it108 = this.mAbsBydAutoSettingListeners.iterator();
                while (it108.hasNext()) {
                    it108.next().onRecoverOrSaveParamsChanged(0, 1, 1, settingPacket.getonSaveSeatParamsAllChangedPlace5());
                }
                caseFeatureIds(1305, Integer.valueOf(settingPacket.getonSaveSeatParamsAllChangedPlace5()));
                break;
            case 557857806:
                Iterator<AbsBYDAutoSettingListener> it109 = this.mAbsBydAutoSettingListeners.iterator();
                while (it109.hasNext()) {
                    it109.next().onRecoverOrSaveParamsChanged(0, 1, 3, settingPacket.getonSaveSeatParamsAllChangedPlace7());
                }
                break;
            case SettingHalId.ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_3CA /* 557857807 */:
                Iterator<AbsBYDAutoSettingListener> it110 = this.mAbsBydAutoSettingListeners.iterator();
                while (it110.hasNext()) {
                    it110.next().onRecoverOrSaveParamsChanged(0, 2, 3, settingPacket.getonSaveSeatParamsAllChanged3Ca());
                }
                caseFeatureIds(760, Integer.valueOf(settingPacket.getonSaveSeatParamsAllChanged3Ca()));
                break;
            case SettingHalId.ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_402 /* 557857808 */:
                Iterator<AbsBYDAutoSettingListener> it111 = this.mAbsBydAutoSettingListeners.iterator();
                while (it111.hasNext()) {
                    it111.next().onRecoverOrSaveParamsChanged(0, 3, 3, settingPacket.getonSaveSeatParamsAllChanged402());
                }
                caseFeatureIds(733, Integer.valueOf(settingPacket.getonSaveSeatParamsAllChanged402()));
                break;
            case SettingHalId.ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_42F /* 557857809 */:
                Iterator<AbsBYDAutoSettingListener> it112 = this.mAbsBydAutoSettingListeners.iterator();
                while (it112.hasNext()) {
                    it112.next().onRecoverOrSaveParamsChanged(0, 4, 3, settingPacket.getonSaveSeatParamsAllChanged42F());
                }
                caseFeatureIds(777, Integer.valueOf(settingPacket.getonSaveSeatParamsAllChanged42F()));
                break;
            case SettingHalId.ID_STEERING_WHEEL_HEATING_STATE_R /* 557857813 */:
                Iterator<AbsBYDAutoSettingListener> it113 = this.mAbsBydAutoSettingListeners.iterator();
                while (it113.hasNext()) {
                    it113.next().onSteeringWheelHeatingStateChanged(settingPacket.getSteeringWheelHeatingState());
                }
                caseFeatureIds(525, Integer.valueOf(settingPacket.getSteeringWheelHeatingState()));
                break;
            case SettingHalId.ID_LOCK_OFF_R /* 557857814 */:
                Iterator<AbsBYDAutoSettingListener> it114 = this.mAbsBydAutoSettingListeners.iterator();
                while (it114.hasNext()) {
                    it114.next().onLockOffChanged(settingPacket.getLockOff());
                }
                break;
            case SettingHalId.ID_COM_SPEED_DOWN_STATER /* 557857833 */:
                Iterator<AbsBYDAutoSettingListener> it115 = this.mAbsBydAutoSettingListeners.iterator();
                while (it115.hasNext()) {
                    it115.next().onComSpeedDownStateChanged(settingPacket.getComSpeedDownState());
                }
                break;
            case SettingHalId.ID_H_SPEED_AUTO_CLOSE_STATER /* 557857835 */:
                Iterator<AbsBYDAutoSettingListener> it116 = this.mAbsBydAutoSettingListeners.iterator();
                while (it116.hasNext()) {
                    it116.next().onHSpeedAutoCloseStateChanged(settingPacket.getHSpeedAutoCloseState());
                }
                break;
            case SettingHalId.ID_COM_SPEED_UP_STATER /* 557857838 */:
                Iterator<AbsBYDAutoSettingListener> it117 = this.mAbsBydAutoSettingListeners.iterator();
                while (it117.hasNext()) {
                    it117.next().onComSpeedUpStateChanged(settingPacket.getComSpeedUpState());
                }
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED2 /* 557857847 */:
                Iterator<AbsBYDAutoSettingListener> it118 = this.mAbsBydAutoSettingListeners.iterator();
                while (it118.hasNext()) {
                    it118.next().onRecoverOrSaveParamsChanged(0, 1, 2, settingPacket.getRecoverOrSaveParamsChanged2());
                }
                caseFeatureIds(1354, Integer.valueOf(settingPacket.getRecoverOrSaveParamsChanged2()));
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED3 /* 557857848 */:
                Iterator<AbsBYDAutoSettingListener> it119 = this.mAbsBydAutoSettingListeners.iterator();
                while (it119.hasNext()) {
                    it119.next().onRecoverOrSaveParamsChanged(0, 1, 1, settingPacket.getRecoverOrSaveParamsChanged3());
                }
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED4 /* 557857849 */:
                Iterator<AbsBYDAutoSettingListener> it120 = this.mAbsBydAutoSettingListeners.iterator();
                while (it120.hasNext()) {
                    it120.next().onRecoverOrSaveParamsChanged(0, 1, 2, settingPacket.getRecoverOrSaveParamsChanged4());
                }
                break;
            case 557857850:
                Iterator<AbsBYDAutoSettingListener> it121 = this.mAbsBydAutoSettingListeners.iterator();
                while (it121.hasNext()) {
                    it121.next().onRecoverOrSaveParamsChanged(0, 1, 3, settingPacket.getRecoverOrSaveParamsChanged5());
                }
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED6 /* 557857851 */:
                Iterator<AbsBYDAutoSettingListener> it122 = this.mAbsBydAutoSettingListeners.iterator();
                while (it122.hasNext()) {
                    it122.next().onRecoverOrSaveParamsChanged(1, 3, 3, settingPacket.getRecoverOrSaveParamsChanged6());
                }
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED7 /* 557857852 */:
                Iterator<AbsBYDAutoSettingListener> it123 = this.mAbsBydAutoSettingListeners.iterator();
                while (it123.hasNext()) {
                    it123.next().onRecoverOrSaveParamsChanged(1, 4, 3, settingPacket.getRecoverOrSaveParamsChanged7());
                }
                break;
            case SettingHalId.ID_HUD_PATTERN_R /* 557857853 */:
                Iterator<AbsBYDAutoSettingListener> it124 = this.mAbsBydAutoSettingListeners.iterator();
                while (it124.hasNext()) {
                    it124.next().onHudPatternChanged(settingPacket.getHudPatternR());
                }
                break;
            case SettingHalId.ID_HUD_SAFE_DRIVE_STATE_R /* 557857855 */:
                Iterator<AbsBYDAutoSettingListener> it125 = this.mAbsBydAutoSettingListeners.iterator();
                while (it125.hasNext()) {
                    it125.next().onHudSafeDriveStateChanged(settingPacket.getHudSafeDriveStateR());
                }
                break;
            case SettingHalId.ID_HUD_NAVIGATION_STATE_R /* 557857857 */:
                Iterator<AbsBYDAutoSettingListener> it126 = this.mAbsBydAutoSettingListeners.iterator();
                while (it126.hasNext()) {
                    it126.next().onHudNavigationStateChanged(settingPacket.getHudNavigationStateR());
                }
                break;
            case SettingHalId.ID_HUD_SCREEN_PROJECTION_STATE_R /* 557857859 */:
                Iterator<AbsBYDAutoSettingListener> it127 = this.mAbsBydAutoSettingListeners.iterator();
                while (it127.hasNext()) {
                    it127.next().onHudSPStateChanged(settingPacket.getHudScreenProjectionStateR());
                }
                break;
            case SettingHalId.ID_RUNING_CLOSE_DOOR_STATE_R /* 557857862 */:
                Iterator<AbsBYDAutoSettingListener> it128 = this.mAbsBydAutoSettingListeners.iterator();
                while (it128.hasNext()) {
                    it128.next().onRuningCloseDoorStateChanged(settingPacket.getRuningCloseDoorStateR());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE2_BACK_LEFT_R /* 557857864 */:
                Iterator<AbsBYDAutoSettingListener> it129 = this.mAbsBydAutoSettingListeners.iterator();
                while (it129.hasNext()) {
                    it129.next().onSeatHeatingState2Changed(3, settingPacket.getSeatHeatingState2BackLeftR());
                }
                caseFeatureIds(1283, Integer.valueOf(settingPacket.getSeatHeatingState2BackLeftR()));
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE2_BACK_RIGHT_R /* 557857865 */:
                Iterator<AbsBYDAutoSettingListener> it130 = this.mAbsBydAutoSettingListeners.iterator();
                while (it130.hasNext()) {
                    it130.next().onSeatHeatingState2Changed(4, settingPacket.getSeatHeatingState2BackRightR());
                }
                caseFeatureIds(1294, Integer.valueOf(settingPacket.getSeatHeatingState2BackRightR()));
                break;
            case SettingHalId.ID_SEAT_HEATING_NOT_L1_P_STATE_R /* 557857866 */:
                Iterator<AbsBYDAutoSettingListener> it131 = this.mAbsBydAutoSettingListeners.iterator();
                while (it131.hasNext()) {
                    it131.next().onSeatHeatingNotL1PStateChanged(settingPacket.getSeatHeatingNotL1PStateR());
                }
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE2_BACK_LEFT_R /* 557857867 */:
                Iterator<AbsBYDAutoSettingListener> it132 = this.mAbsBydAutoSettingListeners.iterator();
                while (it132.hasNext()) {
                    it132.next().onSeatVentilatingState2Changed(3, settingPacket.getSeatVentilatingState2BackLeftR());
                }
                caseFeatureIds(1290, Integer.valueOf(settingPacket.getSeatVentilatingState2BackLeftR()));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE2_BACK_RIGHT_R /* 557857868 */:
                Iterator<AbsBYDAutoSettingListener> it133 = this.mAbsBydAutoSettingListeners.iterator();
                while (it133.hasNext()) {
                    it133.next().onSeatVentilatingState2Changed(4, settingPacket.getSeatVentilatingState2BackRightR());
                }
                caseFeatureIds(1301, Integer.valueOf(settingPacket.getSeatVentilatingState2BackRightR()));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_NOT_L1_P_STATE_R /* 557857869 */:
                Iterator<AbsBYDAutoSettingListener> it134 = this.mAbsBydAutoSettingListeners.iterator();
                while (it134.hasNext()) {
                    it134.next().onSeatVentilatingNotL1PStateChanged(settingPacket.getSeatVentilatingNotL1PStateR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_STATE_DOMAIN_R /* 557857870 */:
                Iterator<AbsBYDAutoSettingListener> it135 = this.mAbsBydAutoSettingListeners.iterator();
                while (it135.hasNext()) {
                    it135.next().on5LevelSeatVentilatingStateChanged(1, settingPacket.get5LevelSeatVentilatingStateDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_STATE_PASSENGER_R /* 557857871 */:
                Iterator<AbsBYDAutoSettingListener> it136 = this.mAbsBydAutoSettingListeners.iterator();
                while (it136.hasNext()) {
                    it136.next().on5LevelSeatVentilatingStateChanged(2, settingPacket.get5LevelSeatVentilatingStatePassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_LEFT_R /* 557857872 */:
                Iterator<AbsBYDAutoSettingListener> it137 = this.mAbsBydAutoSettingListeners.iterator();
                while (it137.hasNext()) {
                    it137.next().on5LevelSeatVentilatingStateChanged(3, settingPacket.get5LevelSeatVentilatingStateBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_RIGHT_R /* 557857873 */:
                Iterator<AbsBYDAutoSettingListener> it138 = this.mAbsBydAutoSettingListeners.iterator();
                while (it138.hasNext()) {
                    it138.next().on5LevelSeatVentilatingStateChanged(4, settingPacket.get5LevelSeatVentilatingStateBackRightR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_STATE_DOMAIN_R /* 557857878 */:
                Iterator<AbsBYDAutoSettingListener> it139 = this.mAbsBydAutoSettingListeners.iterator();
                while (it139.hasNext()) {
                    it139.next().on5LevelSeatHeatingStateChanged(1, settingPacket.get5LevelSeatHeatingStateDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_STATE_PASSENGER_R /* 557857879 */:
                Iterator<AbsBYDAutoSettingListener> it140 = this.mAbsBydAutoSettingListeners.iterator();
                while (it140.hasNext()) {
                    it140.next().on5LevelSeatHeatingStateChanged(2, settingPacket.get5LevelSeatHeatingStatePassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_STATE_BACK_LEFT_R /* 557857880 */:
                Iterator<AbsBYDAutoSettingListener> it141 = this.mAbsBydAutoSettingListeners.iterator();
                while (it141.hasNext()) {
                    it141.next().on5LevelSeatHeatingStateChanged(3, settingPacket.get5LevelSeatHeatingStateBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_STATE_BACK_RIGHT_R /* 557857881 */:
                Iterator<AbsBYDAutoSettingListener> it142 = this.mAbsBydAutoSettingListeners.iterator();
                while (it142.hasNext()) {
                    it142.next().on5LevelSeatHeatingStateChanged(4, settingPacket.get5LevelSeatHeatingStateBackRightR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_MODE_DOMAIN_R /* 557857886 */:
                Iterator<AbsBYDAutoSettingListener> it143 = this.mAbsBydAutoSettingListeners.iterator();
                while (it143.hasNext()) {
                    it143.next().on5LevelSeatVentilatingModeChanged(1, settingPacket.get5LevelSeatVentilatingModeDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_MODE_PASSENGER_R /* 557857887 */:
                Iterator<AbsBYDAutoSettingListener> it144 = this.mAbsBydAutoSettingListeners.iterator();
                while (it144.hasNext()) {
                    it144.next().on5LevelSeatVentilatingModeChanged(2, settingPacket.get5LevelSeatVentilatingModePassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_LEFT_R /* 557857888 */:
                Iterator<AbsBYDAutoSettingListener> it145 = this.mAbsBydAutoSettingListeners.iterator();
                while (it145.hasNext()) {
                    it145.next().on5LevelSeatVentilatingModeChanged(3, settingPacket.get5LevelSeatVentilatingModeBackLeftR());
                }
                caseFeatureIds(3471, Integer.valueOf(settingPacket.get5LevelSeatVentilatingModeBackLeftR()));
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_RIGHT_R /* 557857889 */:
                Iterator<AbsBYDAutoSettingListener> it146 = this.mAbsBydAutoSettingListeners.iterator();
                while (it146.hasNext()) {
                    it146.next().on5LevelSeatVentilatingModeChanged(4, settingPacket.get5LevelSeatVentilatingModeBackRightR());
                }
                caseFeatureIds(3494, Integer.valueOf(settingPacket.get5LevelSeatVentilatingModeBackRightR()));
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_MODE_DOMAIN_R /* 557857894 */:
                Iterator<AbsBYDAutoSettingListener> it147 = this.mAbsBydAutoSettingListeners.iterator();
                while (it147.hasNext()) {
                    it147.next().on5LevelSeatHeatingModeChanged(1, settingPacket.get5LevelSeatHeatingModeDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_MODE_PASSENGER_R /* 557857895 */:
                Iterator<AbsBYDAutoSettingListener> it148 = this.mAbsBydAutoSettingListeners.iterator();
                while (it148.hasNext()) {
                    it148.next().on5LevelSeatHeatingModeChanged(2, settingPacket.get5LevelSeatHeatingModePassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_MODE_BACK_LEFT_R /* 557857896 */:
                Iterator<AbsBYDAutoSettingListener> it149 = this.mAbsBydAutoSettingListeners.iterator();
                while (it149.hasNext()) {
                    it149.next().on5LevelSeatHeatingModeChanged(3, settingPacket.get5LevelSeatHeatingModeBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_MODE_BACK_RIGHT_R /* 557857897 */:
                Iterator<AbsBYDAutoSettingListener> it150 = this.mAbsBydAutoSettingListeners.iterator();
                while (it150.hasNext()) {
                    it150.next().on5LevelSeatHeatingModeChanged(4, settingPacket.get5LevelSeatHeatingModeBackRightR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_LEVEL_DOMAIN_R /* 557857902 */:
                Iterator<AbsBYDAutoSettingListener> it151 = this.mAbsBydAutoSettingListeners.iterator();
                while (it151.hasNext()) {
                    it151.next().on5LevelSeatVentilatingLevelChanged(1, settingPacket.get5LevelSeatVentilatingLevelDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_LEVEL_PASSENGER_R /* 557857903 */:
                Iterator<AbsBYDAutoSettingListener> it152 = this.mAbsBydAutoSettingListeners.iterator();
                while (it152.hasNext()) {
                    it152.next().on5LevelSeatVentilatingLevelChanged(2, settingPacket.get5LevelSeatVentilatingLevelPassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_LEFT_R /* 557857904 */:
                Iterator<AbsBYDAutoSettingListener> it153 = this.mAbsBydAutoSettingListeners.iterator();
                while (it153.hasNext()) {
                    it153.next().on5LevelSeatVentilatingLevelChanged(3, settingPacket.get5LevelSeatVentilatingLevelBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_RIGHT_R /* 557857905 */:
                Iterator<AbsBYDAutoSettingListener> it154 = this.mAbsBydAutoSettingListeners.iterator();
                while (it154.hasNext()) {
                    it154.next().on5LevelSeatVentilatingLevelChanged(4, settingPacket.get5LevelSeatVentilatingLevelBackRightR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_LEVEL_DOMAIN_R /* 557857910 */:
                Iterator<AbsBYDAutoSettingListener> it155 = this.mAbsBydAutoSettingListeners.iterator();
                while (it155.hasNext()) {
                    it155.next().on5LevelSeatHeatingLevelChanged(1, settingPacket.get5LevelSeatHeatingLevelDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_LEVEL_PASSENGER_R /* 557857911 */:
                Iterator<AbsBYDAutoSettingListener> it156 = this.mAbsBydAutoSettingListeners.iterator();
                while (it156.hasNext()) {
                    it156.next().on5LevelSeatHeatingLevelChanged(2, settingPacket.get5LevelSeatHeatingLevelPassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_LEFT_R /* 557857912 */:
                Iterator<AbsBYDAutoSettingListener> it157 = this.mAbsBydAutoSettingListeners.iterator();
                while (it157.hasNext()) {
                    it157.next().on5LevelSeatHeatingLevelChanged(3, settingPacket.get5LevelSeatHeatingLevelBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_RIGHT_R /* 557857913 */:
                Iterator<AbsBYDAutoSettingListener> it158 = this.mAbsBydAutoSettingListeners.iterator();
                while (it158.hasNext()) {
                    it158.next().on5LevelSeatHeatingLevelChanged(4, settingPacket.get5LevelSeatHeatingLevelBackRightR());
                }
                break;
            case SettingHalId.ID_STEERING_WHEEL_HEATING_GEAR_R /* 557857919 */:
                Iterator<AbsBYDAutoSettingListener> it159 = this.mAbsBydAutoSettingListeners.iterator();
                while (it159.hasNext()) {
                    it159.next().onSteeringWheelHeatingGearChanged(settingPacket.getSteeringWheelHeatingGearR());
                }
                caseFeatureIds(384, Integer.valueOf(settingPacket.getSteeringWheelHeatingGearR()));
                break;
            case SettingHalId.ID_STEERING_WHEEL_HEATING_AUTO_STATE_R /* 557857921 */:
                Iterator<AbsBYDAutoSettingListener> it160 = this.mAbsBydAutoSettingListeners.iterator();
                while (it160.hasNext()) {
                    it160.next().onSteeringWheelHeatingAutoStateChanged(settingPacket.getSteeringWheelHeatingAutoStateR());
                }
                break;
            case SettingHalId.ID_ECU_MASSAGE_LEVEL_BACK_LEFT_R /* 557857922 */:
                Iterator<AbsBYDAutoSettingListener> it161 = this.mAbsBydAutoSettingListeners.iterator();
                while (it161.hasNext()) {
                    it161.next().onMassageLevelStateChanged(3, settingPacket.getEcuMassageLevelBackLeftR());
                }
                caseFeatureIds(1286, Integer.valueOf(settingPacket.getEcuMassageLevelBackLeftR()));
                break;
            case SettingHalId.ID_ECU_MASSAGE_LEVEL_BACK_RIGHT_R /* 557857923 */:
                Iterator<AbsBYDAutoSettingListener> it162 = this.mAbsBydAutoSettingListeners.iterator();
                while (it162.hasNext()) {
                    it162.next().onMassageLevelStateChanged(4, settingPacket.getEcuMassageLevelBackRightR());
                }
                caseFeatureIds(1297, Integer.valueOf(settingPacket.getEcuMassageLevelBackRightR()));
                break;
            case SettingHalId.ID_ECU_MASSAGE_MODE_BACK_LEFT_R /* 557857928 */:
                Iterator<AbsBYDAutoSettingListener> it163 = this.mAbsBydAutoSettingListeners.iterator();
                while (it163.hasNext()) {
                    it163.next().onMassageModeStateChanged(3, settingPacket.getEcuMassageModeBackLeftR());
                }
                caseFeatureIds(1288, Integer.valueOf(settingPacket.getEcuMassageModeBackLeftR()));
                break;
            case SettingHalId.ID_ECU_MASSAGE_MODE_BACK_RIGHT_R /* 557857929 */:
                Iterator<AbsBYDAutoSettingListener> it164 = this.mAbsBydAutoSettingListeners.iterator();
                while (it164.hasNext()) {
                    it164.next().onMassageModeStateChanged(4, settingPacket.getEcuMassageModeBackRightR());
                }
                caseFeatureIds(1299, Integer.valueOf(settingPacket.getEcuMassageModeBackRightR()));
                break;
            case SettingHalId.ID_HS_MASSAGE_STATE_DOMAIN_R /* 557857938 */:
                Iterator<AbsBYDAutoSettingListener> it165 = this.mAbsBydAutoSettingListeners.iterator();
                while (it165.hasNext()) {
                    it165.next().onHsMassageStateChanged(1, settingPacket.getHsMassageStateDomainR());
                }
                break;
            case SettingHalId.ID_HEV_CHARGING_PORT_R /* 557857944 */:
                Iterator<AbsBYDAutoSettingListener> it166 = this.mAbsBydAutoSettingListeners.iterator();
                while (it166.hasNext()) {
                    it166.next().onHevChargingPortSwitchChanged(settingPacket.getHevChargingPortR());
                }
                break;
            case SettingHalId.ID_KEY_VIBRATOR_LEVEL_R /* 557857945 */:
                Iterator<AbsBYDAutoSettingListener> it167 = this.mAbsBydAutoSettingListeners.iterator();
                while (it167.hasNext()) {
                    it167.next().onKeyVibratorLevelChanged(settingPacket.getKeyVibratorLevelR());
                }
                break;
            case 557857947:
                Iterator<AbsBYDAutoSettingListener> it168 = this.mAbsBydAutoSettingListeners.iterator();
                while (it168.hasNext()) {
                    it168.next().onIVIProfileSettingsChanged(settingPacket.getIviProfileSettingsR());
                }
                break;
            case 557857951:
                Iterator<AbsBYDAutoSettingListener> it169 = this.mAbsBydAutoSettingListeners.iterator();
                while (it169.hasNext()) {
                    it169.next().onFdComSpeedUpStateChanged(settingPacket.getFdComSpeedUpStateR());
                }
                break;
            case SettingHalId.ID_REARVIEW_MIRROR_IS_TURNED_STATE_R /* 557857957 */:
                Iterator<AbsBYDAutoSettingListener> it170 = this.mAbsBydAutoSettingListeners.iterator();
                while (it170.hasNext()) {
                    it170.next().onRearviewMirrorIsTurnedStateChanged(settingPacket.getRearviewMirrorIsTurnedStateR());
                }
                break;
            case SettingHalId.ID_REARVIEW_MIRROR_IS_STATE_R /* 557857958 */:
                Iterator<AbsBYDAutoSettingListener> it171 = this.mAbsBydAutoSettingListeners.iterator();
                while (it171.hasNext()) {
                    it171.next().onRearviewMirrorIsStateChanged(settingPacket.getRearviewMirrorIsStateR());
                }
                break;
            case SettingHalId.ID_REARVIEW_MIRROR_IS_TURNED_STATE_AUTO_LIGHT_R /* 557857959 */:
                Iterator<AbsBYDAutoSettingListener> it172 = this.mAbsBydAutoSettingListeners.iterator();
                while (it172.hasNext()) {
                    it172.next().onRearviewMirrorIsAutoLightStateChanged(settingPacket.getRearviewMirrorIsAutoLightStateR());
                }
                break;
            case SettingHalId.ID_REFRIGERATOR_WORK_MODE_R /* 557857962 */:
                Iterator<AbsBYDAutoSettingListener> it173 = this.mAbsBydAutoSettingListeners.iterator();
                while (it173.hasNext()) {
                    it173.next().onRefrigeratorWorkModeChanged(settingPacket.getRefrigeratorWorkModeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORKING_STATUS, Integer.valueOf(settingPacket.getRefrigeratorWorkModeR()));
                break;
            case SettingHalId.ID_REFRIGERATOR_WORK_TEMPERATURE_R /* 557857964 */:
                Iterator<AbsBYDAutoSettingListener> it174 = this.mAbsBydAutoSettingListeners.iterator();
                while (it174.hasNext()) {
                    it174.next().onRefrigeratorWorkTemperatureChanged(settingPacket.getRefrigeratorWorkTemperatureR());
                }
                break;
            case SettingHalId.ID_FRIDGE_WORK_COLD_TEMPERATURE_R /* 557857965 */:
                Iterator<AbsBYDAutoSettingListener> it175 = this.mAbsBydAutoSettingListeners.iterator();
                while (it175.hasNext()) {
                    it175.next().onFridgeWorkColdTemperatureChanged(settingPacket.getFridgeWorkColdTemperatureR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_COLD, Integer.valueOf(settingPacket.getFridgeWorkColdTemperatureR()));
                break;
            case SettingHalId.ID_FRIDGE_WORK_HEAT_TEMPERATURE_R /* 557857966 */:
                Iterator<AbsBYDAutoSettingListener> it176 = this.mAbsBydAutoSettingListeners.iterator();
                while (it176.hasNext()) {
                    it176.next().onFridgeWorkHeatTemperatureChanged(settingPacket.getFridgeWorkHeatTemperatureR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_HEAT, Integer.valueOf(settingPacket.getFridgeWorkHeatTemperatureR()));
                break;
            case SettingHalId.ID_FRIDGE_WORK_SAVE_TEMPERATURE_R /* 557857967 */:
                Iterator<AbsBYDAutoSettingListener> it177 = this.mAbsBydAutoSettingListeners.iterator();
                while (it177.hasNext()) {
                    it177.next().onFridgeWorkSaveTemperatureChanged(settingPacket.getFridgeWorkSaveTemperatureR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_FRESH, Integer.valueOf(settingPacket.getFridgeWorkSaveTemperatureR()));
                break;
            case SettingHalId.ID_DELAY_POWER_OFF_STATE_R /* 557857968 */:
                Iterator<AbsBYDAutoSettingListener> it178 = this.mAbsBydAutoSettingListeners.iterator();
                while (it178.hasNext()) {
                    it178.next().onDelayPowerOffStateChanged(settingPacket.getDelayPowerOffStateR());
                }
                break;
            case SettingHalId.ID_DELAY_POWER_OFF_TIME_R /* 557857970 */:
                Iterator<AbsBYDAutoSettingListener> it179 = this.mAbsBydAutoSettingListeners.iterator();
                while (it179.hasNext()) {
                    it179.next().onDelayPowerOffTimeChanged(settingPacket.getDelayPowerOffTimeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWEROFF_TIME, Integer.valueOf(settingPacket.getDelayPowerOffTimeR()));
                break;
            case SettingHalId.ID_DELAY_POWER_DOWN_TIME_R /* 557857972 */:
                Iterator<AbsBYDAutoSettingListener> it180 = this.mAbsBydAutoSettingListeners.iterator();
                while (it180.hasNext()) {
                    it180.next().onDelayPowerDownTimeChanged(settingPacket.getDelayPowerDownTimeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE, Integer.valueOf(settingPacket.getDelayPowerDownTimeR()));
                break;
            case SettingHalId.ID_REMBER_POWER_OFF_STATE_R /* 557857974 */:
                Iterator<AbsBYDAutoSettingListener> it181 = this.mAbsBydAutoSettingListeners.iterator();
                while (it181.hasNext()) {
                    it181.next().onRemberPowerOffStateChanged(settingPacket.getRemberPowerOffStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POWEROFF_MEMORY, Integer.valueOf(settingPacket.getRemberPowerOffStateR()));
                break;
            case SettingHalId.ID_STERILIZATION_STATE_R /* 557857976 */:
                Iterator<AbsBYDAutoSettingListener> it182 = this.mAbsBydAutoSettingListeners.iterator();
                while (it182.hasNext()) {
                    it182.next().onSterilizationStateChanged(settingPacket.getSterilizationStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS, Integer.valueOf(settingPacket.getSterilizationStateR()));
                break;
            case SettingHalId.ID_VOICE_TIPS_MESSAGE_R /* 557857977 */:
                Iterator<AbsBYDAutoSettingListener> it183 = this.mAbsBydAutoSettingListeners.iterator();
                while (it183.hasNext()) {
                    it183.next().onVoiceTipsMessageChanged(settingPacket.getVoiceTipsMessageR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POPUP_REMIND, Integer.valueOf(settingPacket.getVoiceTipsMessageR()));
                break;
            case SettingHalId.ID_FRIDGE_DOOR_STATE_R /* 557857979 */:
                Iterator<AbsBYDAutoSettingListener> it184 = this.mAbsBydAutoSettingListeners.iterator();
                while (it184.hasNext()) {
                    it184.next().onFridgeDoorStateChanged(settingPacket.getFridgeDoorStateR());
                }
                break;
            case SettingHalId.ID_REFRIGERATOR_WORK_STATE_R /* 557857980 */:
                Iterator<AbsBYDAutoSettingListener> it185 = this.mAbsBydAutoSettingListeners.iterator();
                while (it185.hasNext()) {
                    it185.next().onRefrigeratorWorkStateChanged(settingPacket.getRefrigeratorWorkStateR());
                }
                break;
            case SettingHalId.ID_REFRIGERATOR_TEMPERATURE_R /* 557857981 */:
                Iterator<AbsBYDAutoSettingListener> it186 = this.mAbsBydAutoSettingListeners.iterator();
                while (it186.hasNext()) {
                    it186.next().onRefrigeratorTemperatureChanged(settingPacket.getRefrigeratorTemperatureR());
                }
                break;
            case SettingHalId.ID_DELAY_DOWN_POWER_TIME_R /* 557857982 */:
                Iterator<AbsBYDAutoSettingListener> it187 = this.mAbsBydAutoSettingListeners.iterator();
                while (it187.hasNext()) {
                    it187.next().onDelayDownPowerTimeChanged(settingPacket.getDelayDownPowerTimeR());
                }
                break;
            case SettingHalId.ID_STERILIZATION_WORK_STATE_R /* 557857983 */:
                Iterator<AbsBYDAutoSettingListener> it188 = this.mAbsBydAutoSettingListeners.iterator();
                while (it188.hasNext()) {
                    it188.next().onSterilizationWorkStateChanged(settingPacket.getSterilizationWorkStateR());
                }
                break;
            case SettingHalId.ID_DLG_MESSAGE_CODE_R /* 557857984 */:
                Iterator<AbsBYDAutoSettingListener> it189 = this.mAbsBydAutoSettingListeners.iterator();
                while (it189.hasNext()) {
                    it189.next().onDlgMessageCodeChanged(settingPacket.getDlgMessageCodeR());
                }
                break;
            case SettingHalId.ID_FRIDGE_LOCATION_R /* 557857985 */:
                Iterator<AbsBYDAutoSettingListener> it190 = this.mAbsBydAutoSettingListeners.iterator();
                while (it190.hasNext()) {
                    it190.next().onFridgeLocationChanged(settingPacket.getFridgeLocationR());
                }
                break;
            case SettingHalId.ID_CONTROL_CALLBACK_STATE_R /* 557857990 */:
                Iterator<AbsBYDAutoSettingListener> it191 = this.mAbsBydAutoSettingListeners.iterator();
                while (it191.hasNext()) {
                    it191.next().onControlCallbackStateChanged(settingPacket.getControlCallbackStateR());
                }
                break;
            case SettingHalId.ID_WORK_STATE_R /* 557857991 */:
                Iterator<AbsBYDAutoSettingListener> it192 = this.mAbsBydAutoSettingListeners.iterator();
                while (it192.hasNext()) {
                    it192.next().onWorkStateChanged(settingPacket.getWorkStateR());
                }
                break;
            case SettingHalId.ID_DELAY_POWER_OFF_STATUS_R /* 557857992 */:
                Iterator<AbsBYDAutoSettingListener> it193 = this.mAbsBydAutoSettingListeners.iterator();
                while (it193.hasNext()) {
                    it193.next().onDelayPowerOffStatusChanged(settingPacket.getDelayPowerOffStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_PWOEROFF_SWITCH, Integer.valueOf(settingPacket.getDelayPowerOffStatusR()));
                break;
            case SettingHalId.ID_SCREEN_CALLBACK_MESSAGE_R /* 557857993 */:
                Iterator<AbsBYDAutoSettingListener> it194 = this.mAbsBydAutoSettingListeners.iterator();
                while (it194.hasNext()) {
                    it194.next().onScreenCallbackMessageChanged(settingPacket.getScreenCallbackMessageR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_BOUNCE_SIGNAL, Integer.valueOf(settingPacket.getScreenCallbackMessageR()));
                break;
            case SettingHalId.ID_REAR_DOOR_STATE_R /* 557857994 */:
                Iterator<AbsBYDAutoSettingListener> it195 = this.mAbsBydAutoSettingListeners.iterator();
                while (it195.hasNext()) {
                    it195.next().onRearDoorStateChanged(settingPacket.getRearDoorStateR());
                }
                break;
            case SettingHalId.ID_REAR_HEAT_TEMP_R /* 557857995 */:
                Iterator<AbsBYDAutoSettingListener> it196 = this.mAbsBydAutoSettingListeners.iterator();
                while (it196.hasNext()) {
                    it196.next().onRearHeatTempChanged(settingPacket.getRearHeatTempR());
                }
                break;
            case SettingHalId.ID_REAR_SAVE_TEMP_R /* 557857996 */:
                Iterator<AbsBYDAutoSettingListener> it197 = this.mAbsBydAutoSettingListeners.iterator();
                while (it197.hasNext()) {
                    it197.next().onRearSaveTempChanged(settingPacket.getRearSaveTempR());
                }
                break;
            case SettingHalId.ID_PHONE_REMIND_CODE_R /* 557857997 */:
                Iterator<AbsBYDAutoSettingListener> it198 = this.mAbsBydAutoSettingListeners.iterator();
                while (it198.hasNext()) {
                    it198.next().onPhoneRemindCodeChanged(settingPacket.getPhoneRemindCodeR());
                }
                break;
            case SettingHalId.ID_SERVICE_CONTROL_CALLBACK_R /* 557857998 */:
                Iterator<AbsBYDAutoSettingListener> it199 = this.mAbsBydAutoSettingListeners.iterator();
                while (it199.hasNext()) {
                    it199.next().onServiceControlCallbackChanged(settingPacket.getServiceControlCallbackR());
                }
                break;
            case SettingHalId.ID_CANOPY_AUTOMATIC_DIMMING_R /* 557858010 */:
                Iterator<AbsBYDAutoSettingListener> it200 = this.mAbsBydAutoSettingListeners.iterator();
                while (it200.hasNext()) {
                    it200.next().onCanopyAutomaticDimming(settingPacket.getCanopyAutomaticDimmingR());
                }
                caseFeatureIds(921, Integer.valueOf(settingPacket.getCanopyAutomaticDimmingR()));
                break;
            case SettingHalId.ID_SKY_CURTAIN_POWER_OFF_AND_DIM_R /* 557858011 */:
                Iterator<AbsBYDAutoSettingListener> it201 = this.mAbsBydAutoSettingListeners.iterator();
                while (it201.hasNext()) {
                    it201.next().onSkyCurtainPowerOffAndDim(settingPacket.getSkyCurtainPowerOffAndDimR());
                }
                caseFeatureIds(925, Integer.valueOf(settingPacket.getSkyCurtainPowerOffAndDimR()));
                break;
            case SettingHalId.ID_CURRENT_TRANSMITTANCE_OF_SKYLIGHT_R /* 557858016 */:
                Iterator<AbsBYDAutoSettingListener> it202 = this.mAbsBydAutoSettingListeners.iterator();
                while (it202.hasNext()) {
                    it202.next().onCurrentTransmittanceOfSkylight(settingPacket.getCurrentTransmittanceOfSkylightR());
                }
                caseFeatureIds(924, Integer.valueOf(settingPacket.getCurrentTransmittanceOfSkylightR()));
                break;
            case SettingHalId.ID_CURRENT_STATUS_OF_DIMMING_SUNROOF_R /* 557858017 */:
                Iterator<AbsBYDAutoSettingListener> it203 = this.mAbsBydAutoSettingListeners.iterator();
                while (it203.hasNext()) {
                    it203.next().onCurrentStatusOfDimmingSunroof(settingPacket.getCurrentStatusOfDimmingSunroofR());
                }
                caseFeatureIds(888, Integer.valueOf(settingPacket.getCurrentStatusOfDimmingSunroofR()));
                break;
            case SettingHalId.ID_DIMMING_CANOPY_LOW_CODE_R /* 557858018 */:
                Iterator<AbsBYDAutoSettingListener> it204 = this.mAbsBydAutoSettingListeners.iterator();
                while (it204.hasNext()) {
                    it204.next().onDimmingCanopyLowCode(settingPacket.getDimmingCanopyLowCodeR());
                }
                caseFeatureIds(4625, Integer.valueOf(settingPacket.getDimmingCanopyLowCodeR()));
                break;
            case SettingHalId.ID_TAIL_INITIALIZATION_STATE_R /* 557858020 */:
                Iterator<AbsBYDAutoSettingListener> it205 = this.mAbsBydAutoSettingListeners.iterator();
                while (it205.hasNext()) {
                    it205.next().onTailInitializationStateChanged(settingPacket.getTailInitializationStateR());
                }
                caseFeatureIds(4629, Integer.valueOf(settingPacket.getTailInitializationStateR()));
                break;
            case SettingHalId.ID_CURRENT_STATE_OF_THE_TAIL_STATE_R /* 557858021 */:
                Iterator<AbsBYDAutoSettingListener> it206 = this.mAbsBydAutoSettingListeners.iterator();
                while (it206.hasNext()) {
                    it206.next().onCurrentStateOfTheTailStateChanged(settingPacket.getCurrentStateOfTheTailStateR());
                }
                caseFeatureIds(4630, Integer.valueOf(settingPacket.getCurrentStateOfTheTailStateR()));
                break;
            case SettingHalId.ID_TAIL_POSITION_PERCENTAGE_R /* 557858022 */:
                Iterator<AbsBYDAutoSettingListener> it207 = this.mAbsBydAutoSettingListeners.iterator();
                while (it207.hasNext()) {
                    it207.next().onTailPositionPercentageChanged(settingPacket.getTailPositionPercentageR());
                }
                caseFeatureIds(4631, Integer.valueOf(settingPacket.getTailPositionPercentageR()));
                break;
            case SettingHalId.ID_TAIL_MOTOR_THERMAL_PROTECTION_STATE_R /* 557858023 */:
                Iterator<AbsBYDAutoSettingListener> it208 = this.mAbsBydAutoSettingListeners.iterator();
                while (it208.hasNext()) {
                    it208.next().onTailMotorThermalProtectionStateChanged(settingPacket.getTailMotorThermalProtectionStateR());
                }
                caseFeatureIds(4632, Integer.valueOf(settingPacket.getTailMotorThermalProtectionStateR()));
                break;
            case SettingHalId.ID_TAIL_POSITION_FEEDBACK_R /* 557858024 */:
                Iterator<AbsBYDAutoSettingListener> it209 = this.mAbsBydAutoSettingListeners.iterator();
                while (it209.hasNext()) {
                    it209.next().onTailPositionFeedbackChanged(settingPacket.getTailPositionFeedbackR());
                }
                caseFeatureIds(4633, Integer.valueOf(settingPacket.getTailPositionFeedbackR()));
                break;
            case SettingHalId.ID_ALARM_PROMPT_STATE_R /* 557858025 */:
                Iterator<AbsBYDAutoSettingListener> it210 = this.mAbsBydAutoSettingListeners.iterator();
                while (it210.hasNext()) {
                    it210.next().onAlarmPromptStateChanged(settingPacket.getAlarmPromptStateR());
                }
                caseFeatureIds(4634, Integer.valueOf(settingPacket.getAlarmPromptStateR()));
                break;
            case SettingHalId.ID_CURRENT_MODE_OF_TAIL_STATE_R /* 557858026 */:
                Iterator<AbsBYDAutoSettingListener> it211 = this.mAbsBydAutoSettingListeners.iterator();
                while (it211.hasNext()) {
                    it211.next().onCurrentModeOfTailStateChanged(settingPacket.getCurrentModeOfTailStateR());
                }
                caseFeatureIds(4635, Integer.valueOf(settingPacket.getCurrentModeOfTailStateR()));
                break;
            case SettingHalId.ID_ELECTRIC_TAIL_MAINTENANCE_FUNTATUS_STATE_R /* 557858027 */:
                Iterator<AbsBYDAutoSettingListener> it212 = this.mAbsBydAutoSettingListeners.iterator();
                while (it212.hasNext()) {
                    it212.next().onElectricTailMaintenanceFuntatusStateChanged(settingPacket.getElectricTailMaintenanceFuntatusStateR());
                }
                caseFeatureIds(4636, Integer.valueOf(settingPacket.getElectricTailMaintenanceFuntatusStateR()));
                break;
            case SettingHalId.ID_LEFT_ELECTRIC_PEDAL_R /* 557858058 */:
                Iterator<AbsBYDAutoSettingListener> it213 = this.mAbsBydAutoSettingListeners.iterator();
                while (it213.hasNext()) {
                    it213.next().onElectricPedalStateChanged(1, settingPacket.getLeftElectricPedalR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_LEFT_ELECTRIC_PEDAL_POSITION, Integer.valueOf(settingPacket.getLeftElectricPedalR()));
                break;
            case SettingHalId.ID_RIGHT_ELECTRIC_PEDAL_R /* 557858060 */:
                Iterator<AbsBYDAutoSettingListener> it214 = this.mAbsBydAutoSettingListeners.iterator();
                while (it214.hasNext()) {
                    it214.next().onElectricPedalStateChanged(2, settingPacket.getRightElectricPedalR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_RIGHT_ELECTRIC_PEDAL_POSITION, Integer.valueOf(settingPacket.getRightElectricPedalR()));
                break;
            case SettingHalId.ID_FRIDGE_CHILD_LOCK_R /* 557858063 */:
                Iterator<AbsBYDAutoSettingListener> it215 = this.mAbsBydAutoSettingListeners.iterator();
                while (it215.hasNext()) {
                    it215.next().onFridgeChildLockChanged(settingPacket.getFridgeChildLockR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_STATE, Integer.valueOf(settingPacket.getFridgeChildLockR()));
                break;
            case SettingHalId.ID_S_O_C_TARGET_RANGE_R /* 557858083 */:
                Iterator<AbsBYDAutoSettingListener> it216 = this.mAbsBydAutoSettingListeners.iterator();
                while (it216.hasNext()) {
                    it216.next().onSOCTargetRangeChanged(settingPacket.getsocTargetRangeR());
                }
                break;
            case SettingHalId.ID_S_O_C_TARGET_LOW_LIMIT_R /* 557858084 */:
                Iterator<AbsBYDAutoSettingListener> it217 = this.mAbsBydAutoSettingListeners.iterator();
                while (it217.hasNext()) {
                    it217.next().onSOCTargetLowLimitChanged(settingPacket.getsocTargetLowLimitR());
                }
                break;
            case SettingHalId.ID_CPD_IMS_SWITCH_STATE_R /* 557858089 */:
                Iterator<AbsBYDAutoSettingListener> it218 = this.mAbsBydAutoSettingListeners.iterator();
                while (it218.hasNext()) {
                    it218.next().onCpdImsSwitchStateChanged(settingPacket.getCpdImsSwitchStateR());
                }
                break;
            case SettingHalId.ID_RNC_STATE_R /* 557858090 */:
                Iterator<AbsBYDAutoSettingListener> it219 = this.mAbsBydAutoSettingListeners.iterator();
                while (it219.hasNext()) {
                    it219.next().onRncStateChanged(settingPacket.getRncStateR());
                }
                break;
            case SettingHalId.ID_SWCONTROL_STATE_R /* 557858091 */:
                Iterator<AbsBYDAutoSettingListener> it220 = this.mAbsBydAutoSettingListeners.iterator();
                while (it220.hasNext()) {
                    it220.next().onSwControlStateChanged(settingPacket.getSwcontrolStateR());
                }
                break;
            case SettingHalId.ID_PEDAL_SUPPRESSION_SYSTEM_STATE_R /* 557858093 */:
                Iterator<AbsBYDAutoSettingListener> it221 = this.mAbsBydAutoSettingListeners.iterator();
                while (it221.hasNext()) {
                    it221.next().onPedalSuppressionSystemStateChanged(settingPacket.getPedalSuppressionSystemStateR());
                }
                break;
            case SettingHalId.ID_CURA_SWITCH_STATE_R /* 557858096 */:
                Iterator<AbsBYDAutoSettingListener> it222 = this.mAbsBydAutoSettingListeners.iterator();
                while (it222.hasNext()) {
                    it222.next().onCuraSwitchStateChanged(settingPacket.getCuraSwitchStateR());
                }
                break;
            case SettingHalId.ID_REAR_RIGHT_SEAT_GREET_STATE_R /* 557858102 */:
                Iterator<AbsBYDAutoSettingListener> it223 = this.mAbsBydAutoSettingListeners.iterator();
                while (it223.hasNext()) {
                    it223.next().onRearRightSeatGreetStateChanged(settingPacket.getRearRightSeatGreetStateR());
                }
                break;
            case SettingHalId.ID_WADING_PATTERN_SOC_TIPS_R /* 557858107 */:
                Iterator<AbsBYDAutoSettingListener> it224 = this.mAbsBydAutoSettingListeners.iterator();
                while (it224.hasNext()) {
                    it224.next().onWadingPatternSocTipsChanaged(settingPacket.getWadingPatternSocTipsR());
                }
                break;
            case SettingHalId.ID_WADING_PATTERN_SPEED_TIPS_R /* 557858108 */:
                Iterator<AbsBYDAutoSettingListener> it225 = this.mAbsBydAutoSettingListeners.iterator();
                while (it225.hasNext()) {
                    it225.next().onWadingPatternSpeedTipsChanged(settingPacket.getWadingPatternSpeedTipsR());
                }
                break;
            case SettingHalId.ID_WADING_PATTERN_TEST_TIPS_R /* 557858109 */:
                Iterator<AbsBYDAutoSettingListener> it226 = this.mAbsBydAutoSettingListeners.iterator();
                while (it226.hasNext()) {
                    it226.next().onWadingPatternTestTipsChanged(settingPacket.getWadingPatternTestTipsR());
                }
                break;
            case SettingHalId.ID_WADING_PATTERN_STATE_TIPS_R /* 557858110 */:
                Iterator<AbsBYDAutoSettingListener> it227 = this.mAbsBydAutoSettingListeners.iterator();
                while (it227.hasNext()) {
                    it227.next().onWadingPatternStateTipsChanged(settingPacket.getWadingPatternStateTipsR());
                }
                break;
            case SettingHalId.ID_SEA_MODE_CHARGING_SOC_TYPE_R /* 557858111 */:
                Iterator<AbsBYDAutoSettingListener> it228 = this.mAbsBydAutoSettingListeners.iterator();
                while (it228.hasNext()) {
                    it228.next().onSeaModeChargingSocTypeChanged(settingPacket.getSeaModeChargingSocTypeR());
                }
                break;
            case SettingHalId.ID_DRIVE_MODE_R /* 557858112 */:
                Iterator<AbsBYDAutoSettingListener> it229 = this.mAbsBydAutoSettingListeners.iterator();
                while (it229.hasNext()) {
                    it229.next().onDriveModeChanged(settingPacket.getDriveModeR());
                }
                break;
            case SettingHalId.ID_SAND_ABS_MODE_STATE_R /* 557858114 */:
                Iterator<AbsBYDAutoSettingListener> it230 = this.mAbsBydAutoSettingListeners.iterator();
                while (it230.hasNext()) {
                    it230.next().onSandAbsModeStateChanged(settingPacket.getSandAbsModeStateR());
                }
                break;
            case SettingHalId.ID_SAND_ABS_TIP_MESSAGE_R /* 557858115 */:
                Iterator<AbsBYDAutoSettingListener> it231 = this.mAbsBydAutoSettingListeners.iterator();
                while (it231.hasNext()) {
                    it231.next().onSandAbsTipMessageChanged(settingPacket.getSandAbsTipMessageR());
                }
                break;
            case SettingHalId.ID_SAVE_OWNER_STATE_R /* 557858117 */:
                Iterator<AbsBYDAutoSettingListener> it232 = this.mAbsBydAutoSettingListeners.iterator();
                while (it232.hasNext()) {
                    it232.next().onSaveOwnerStateChanged(settingPacket.getSaveOwnerStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_EXCLUSIVE_SAVE_SHORTCUT_KEY, Integer.valueOf(settingPacket.getSaveOwnerStateR()));
                break;
            case SettingHalId.ID_REFRIGERATOR_MOBILE_TERMINAL_PUSH_RE_R /* 557858119 */:
                Iterator<AbsBYDAutoSettingListener> it233 = this.mAbsBydAutoSettingListeners.iterator();
                while (it233.hasNext()) {
                    it233.next().onRefrigeratorMobileTerminalPushReChanged(settingPacket.getRefrigeratorMobileTerminalPushReR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_MOBINE_PUSH_REMIND, Integer.valueOf(settingPacket.getRefrigeratorMobileTerminalPushReR()));
                break;
            case SettingHalId.ID_REFRIG_WORK_MODE_R /* 557858123 */:
                Iterator<AbsBYDAutoSettingListener> it234 = this.mAbsBydAutoSettingListeners.iterator();
                while (it234.hasNext()) {
                    it234.next().onRefrigWorkModeChanged(settingPacket.getRefrigWorkModeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE, Integer.valueOf(settingPacket.getRefrigWorkModeR()));
                break;
            case SettingHalId.ID_DRIVER_SEAT_VENTILATING437_STATE_R /* 557858125 */:
                Iterator<AbsBYDAutoSettingListener> it235 = this.mAbsBydAutoSettingListeners.iterator();
                while (it235.hasNext()) {
                    it235.next().onDriverSeatVentilating437StateChanged(settingPacket.getDriverSeatVentilating437StateR());
                }
                break;
            case SettingHalId.ID_DRIVER_SEAT_HEATING437_STATE_R /* 557858126 */:
                Iterator<AbsBYDAutoSettingListener> it236 = this.mAbsBydAutoSettingListeners.iterator();
                while (it236.hasNext()) {
                    it236.next().onDriverSeatHeating437StateChanged(settingPacket.getDriverSeatHeating437StateR());
                }
                break;
            case SettingHalId.ID_PASSENGER_SEAT_VENTILATING3_C_A_STATE_R /* 557858127 */:
                Iterator<AbsBYDAutoSettingListener> it237 = this.mAbsBydAutoSettingListeners.iterator();
                while (it237.hasNext()) {
                    it237.next().onPassengerSeatVentilating3CAStateChanged(settingPacket.getPassengerSeatVentilating3caStateR());
                }
                break;
            case SettingHalId.ID_PASSENGER_SEAT_HEATING3_C_A_STATE_R /* 557858128 */:
                Iterator<AbsBYDAutoSettingListener> it238 = this.mAbsBydAutoSettingListeners.iterator();
                while (it238.hasNext()) {
                    it238.next().onPassengerSeatHeating3CAStateChanged(settingPacket.getPassengerSeatHeating3caStateR());
                }
                break;
            case SettingHalId.ID_SAFE_BELT_WARN_STATER /* 557858305 */:
                Iterator<AbsBYDAutoSettingListener> it239 = this.mAbsBydAutoSettingListeners.iterator();
                while (it239.hasNext()) {
                    it239.next().onSafeBeltWarnStateChanged(settingPacket.getSafeBeltWarnState());
                }
                break;
            case SettingHalId.ID_P_A_B_CODE_R /* 557858330 */:
                Iterator<AbsBYDAutoSettingListener> it240 = this.mAbsBydAutoSettingListeners.iterator();
                while (it240.hasNext()) {
                    it240.next().onPABStateCodeChanged(settingPacket.getPabCodeR());
                }
                break;
            case SettingHalId.ID_MISS_PACKAGE_COUNT_R /* 557858331 */:
                Iterator<AbsBYDAutoSettingListener> it241 = this.mAbsBydAutoSettingListeners.iterator();
                while (it241.hasNext()) {
                    it241.next().onMissPackageCountChanged(settingPacket.getMissPackageCountR());
                }
                break;
            case SettingHalId.ID_SCREEN_ANGLE_R /* 557858334 */:
                Iterator<AbsBYDAutoSettingListener> it242 = this.mAbsBydAutoSettingListeners.iterator();
                while (it242.hasNext()) {
                    it242.next().onScreenAngleChanged(settingPacket.getScreenAngleR());
                }
                break;
            case SettingHalId.ID_SCREEN_ROTATION_ACTION_R /* 557858336 */:
                Iterator<AbsBYDAutoSettingListener> it243 = this.mAbsBydAutoSettingListeners.iterator();
                while (it243.hasNext()) {
                    it243.next().onScreenRotationActionChanged(settingPacket.getScreenRotationActionR());
                }
                break;
            case SettingHalId.ID_SCREEN_ANTI_PINCH_STATE_R /* 557858337 */:
                Iterator<AbsBYDAutoSettingListener> it244 = this.mAbsBydAutoSettingListeners.iterator();
                while (it244.hasNext()) {
                    it244.next().onScreenAntiPinchStateChanged(settingPacket.getScreenAntiPinchStateR());
                }
                break;
            case SettingHalId.ID_SCREEN_ANGLE_VALUE_R /* 557858338 */:
                Iterator<AbsBYDAutoSettingListener> it245 = this.mAbsBydAutoSettingListeners.iterator();
                while (it245.hasNext()) {
                    it245.next().onScreenAngleValueChanged(settingPacket.getScreenAngleValueR());
                }
                break;
            case SettingHalId.ID_SCREEN_ROTATION_OVER_HOT_STATE_R /* 557858340 */:
                Iterator<AbsBYDAutoSettingListener> it246 = this.mAbsBydAutoSettingListeners.iterator();
                while (it246.hasNext()) {
                    it246.next().onScreenRotationOverHotStateChanged(settingPacket.getScreenRotationOverHotStateR());
                }
                break;
            case SettingHalId.ID_P_A_B_STATE_R /* 557858345 */:
                Iterator<AbsBYDAutoSettingListener> it247 = this.mAbsBydAutoSettingListeners.iterator();
                while (it247.hasNext()) {
                    it247.next().onPABStateChanged(settingPacket.getPabStateR());
                }
                break;
            case SettingHalId.ID_SOMETHING_LEFT_INREFRIGERATOR_R /* 557858354 */:
                Iterator<AbsBYDAutoSettingListener> it248 = this.mAbsBydAutoSettingListeners.iterator();
                while (it248.hasNext()) {
                    it248.next().onSomethingLeftInRefrigeratorNotice(settingPacket.getSomethingLeftInRefrigerator());
                }
                break;
            case SettingHalId.ID_ONE_LEVEL_SEAT_HEATING_STATE_R /* 557858355 */:
                Iterator<AbsBYDAutoSettingListener> it249 = this.mAbsBydAutoSettingListeners.iterator();
                while (it249.hasNext()) {
                    it249.next().on1LevelSeatHeatingStateChanged(1, settingPacket.getOneLevelSeatHeatingStateR());
                }
                break;
            case SettingHalId.ID_TRAILER_MODE_STATE_R /* 557858357 */:
                Iterator<AbsBYDAutoSettingListener> it250 = this.mAbsBydAutoSettingListeners.iterator();
                while (it250.hasNext()) {
                    it250.next().onTrailerModeStateChanged(settingPacket.getTrailerModeStateR());
                }
                break;
            case SettingHalId.ID_TRAILER_LIGHT_CHECK_STATE_R /* 557858358 */:
                Iterator<AbsBYDAutoSettingListener> it251 = this.mAbsBydAutoSettingListeners.iterator();
                while (it251.hasNext()) {
                    it251.next().onTrailerLightCheckStateChanged(settingPacket.getTrailerLightCheckStateR());
                }
                break;
            case SettingHalId.ID_REAR_VIEW_ECU_MIRROR_FLIP_STATE_R /* 557858360 */:
                Iterator<AbsBYDAutoSettingListener> it252 = this.mAbsBydAutoSettingListeners.iterator();
                while (it252.hasNext()) {
                    it252.next().onRearViewEcuMirrorFlipStateChanged(settingPacket.getRearViewEcuMirrorFlipStateR());
                }
                break;
            case SettingHalId.ID_DY_HEIGHT_TOGGLE_STATE_R /* 557858361 */:
                Iterator<AbsBYDAutoSettingListener> it253 = this.mAbsBydAutoSettingListeners.iterator();
                while (it253.hasNext()) {
                    it253.next().onDyHeightToggleStateChanged(settingPacket.getDyHeightToggleStateR());
                }
                break;
            case SettingHalId.ID_WELCOME_STATE_R /* 557858362 */:
                Iterator<AbsBYDAutoSettingListener> it254 = this.mAbsBydAutoSettingListeners.iterator();
                while (it254.hasNext()) {
                    it254.next().onWelcomeStateChanged(settingPacket.getWelcomeStateR());
                }
                break;
            case SettingHalId.ID_WELCOME_ACTIVE_R /* 557858363 */:
                Iterator<AbsBYDAutoSettingListener> it255 = this.mAbsBydAutoSettingListeners.iterator();
                while (it255.hasNext()) {
                    it255.next().onWelcomeActiveChanged(settingPacket.getWelcomeActiveR());
                }
                break;
            case SettingHalId.ID_TAIL_BOX_TAKE_STATE_R /* 557858364 */:
                Iterator<AbsBYDAutoSettingListener> it256 = this.mAbsBydAutoSettingListeners.iterator();
                while (it256.hasNext()) {
                    it256.next().onTailBoxTakeStateChanged(settingPacket.getTailBoxTakeStateR());
                }
                break;
            case SettingHalId.ID_CAMPING_BLANCE_STATE_R /* 557858365 */:
                Iterator<AbsBYDAutoSettingListener> it257 = this.mAbsBydAutoSettingListeners.iterator();
                while (it257.hasNext()) {
                    it257.next().onCampintBlacneStateChanged(settingPacket.getCampingBlanceStateR());
                }
                break;
            case SettingHalId.ID_CAMPING_BLANCE_ACTION_R /* 557858366 */:
                Iterator<AbsBYDAutoSettingListener> it258 = this.mAbsBydAutoSettingListeners.iterator();
                while (it258.hasNext()) {
                    it258.next().onCampingBlanceActionChanged(settingPacket.getCampingBlanceActionR());
                }
                break;
            case SettingHalId.ID_LIFT_MODE_STATE_R /* 557858367 */:
                Iterator<AbsBYDAutoSettingListener> it259 = this.mAbsBydAutoSettingListeners.iterator();
                while (it259.hasNext()) {
                    it259.next().onLiftModeStateChanged(settingPacket.getLiftModeStateR());
                }
                break;
            case SettingHalId.ID_FIXED_STATE_R /* 557858368 */:
                Iterator<AbsBYDAutoSettingListener> it260 = this.mAbsBydAutoSettingListeners.iterator();
                while (it260.hasNext()) {
                    it260.next().onFixedStateChanged(settingPacket.getFixedStateR());
                }
                break;
            case SettingHalId.ID_SPEED_SUPPORTED_TURN_R /* 557858369 */:
                Iterator<AbsBYDAutoSettingListener> it261 = this.mAbsBydAutoSettingListeners.iterator();
                while (it261.hasNext()) {
                    it261.next().onSpeedSupportedTurn(settingPacket.getSpeedSupportedTurnR());
                }
                break;
            case SettingHalId.ID_MODE_SUPPORTED_HTURN_R /* 557858370 */:
                Iterator<AbsBYDAutoSettingListener> it262 = this.mAbsBydAutoSettingListeners.iterator();
                while (it262.hasNext()) {
                    it262.next().onModeSupportedHturnChanged(settingPacket.getModeSupportedHturnR());
                }
                break;
            case SettingHalId.ID_WELCOME_SUPPORTED_R /* 557858371 */:
                Iterator<AbsBYDAutoSettingListener> it263 = this.mAbsBydAutoSettingListeners.iterator();
                while (it263.hasNext()) {
                    it263.next().onWelcomeSupportedChanged(settingPacket.getWelcomeSupportedR());
                }
                break;
            case SettingHalId.ID_TAIL_BOX_TAKE_SUPPORTED_R /* 557858372 */:
                Iterator<AbsBYDAutoSettingListener> it264 = this.mAbsBydAutoSettingListeners.iterator();
                while (it264.hasNext()) {
                    it264.next().onTailBoxTakeSupportedChanged(settingPacket.getTailBoxTakeSupportedR());
                }
                break;
            case SettingHalId.ID_TRAILER_MODE_CODE_R /* 557858373 */:
                Iterator<AbsBYDAutoSettingListener> it265 = this.mAbsBydAutoSettingListeners.iterator();
                while (it265.hasNext()) {
                    it265.next().onTrailerModeCodeChanged(settingPacket.getTrailerModeCodeR());
                }
                break;
            case SettingHalId.ID_C_V_C_MODE_R /* 557858374 */:
                Iterator<AbsBYDAutoSettingListener> it266 = this.mAbsBydAutoSettingListeners.iterator();
                while (it266.hasNext()) {
                    it266.next().onCVCModeChanged(settingPacket.getCvcModeR());
                }
                break;
            case SettingHalId.ID_CAMPING_BLANCE_SUPPORTED_R /* 557858375 */:
                Iterator<AbsBYDAutoSettingListener> it267 = this.mAbsBydAutoSettingListeners.iterator();
                while (it267.hasNext()) {
                    it267.next().onCampingBlanceSupportedChanged(settingPacket.getCampingBlanceSupportedR());
                }
                break;
            case SettingHalId.ID_REAR_SEAT_GREET_STATE_R /* 557858376 */:
                Iterator<AbsBYDAutoSettingListener> it268 = this.mAbsBydAutoSettingListeners.iterator();
                while (it268.hasNext()) {
                    it268.next().onRearSeatGreetStateChanged(settingPacket.getRearSeatGreetStateR());
                }
                break;
            case SettingHalId.ID_DY_HEIGHT_TURN_ACTION_R /* 557858377 */:
                Iterator<AbsBYDAutoSettingListener> it269 = this.mAbsBydAutoSettingListeners.iterator();
                while (it269.hasNext()) {
                    it269.next().onDyHeightTurnActionChanged(settingPacket.getDyHeightTurnActionR());
                }
                break;
            case SettingHalId.ID_DAMP_STATE_R /* 557858378 */:
                Iterator<AbsBYDAutoSettingListener> it270 = this.mAbsBydAutoSettingListeners.iterator();
                while (it270.hasNext()) {
                    it270.next().onDampStateChanged(settingPacket.getDampStateR());
                }
                break;
            case SettingHalId.ID_DY_HEIGHT_LEVEL_R /* 557858379 */:
                Iterator<AbsBYDAutoSettingListener> it271 = this.mAbsBydAutoSettingListeners.iterator();
                while (it271.hasNext()) {
                    it271.next().onDyHeightLevelChanged(settingPacket.getDyHeightLevelR());
                }
                break;
            case SettingHalId.ID_FD_C_R_C_CHECK_NUM_R /* 557858380 */:
                Iterator<AbsBYDAutoSettingListener> it272 = this.mAbsBydAutoSettingListeners.iterator();
                while (it272.hasNext()) {
                    it272.next().onFdCampingBlanceActionChanged(settingPacket.getFdCrcCheckNumR());
                }
                break;
            case SettingHalId.ID_ON_FD_CRC_CHECK_NUM_R /* 557858381 */:
                Iterator<AbsBYDAutoSettingListener> it273 = this.mAbsBydAutoSettingListeners.iterator();
                while (it273.hasNext()) {
                    it273.next().onFdCRCCheckNum(settingPacket.getOnFdCrcCheckNumR());
                }
                break;
            case SettingHalId.ID_FD_DY_HEIGHT_TURN_ACTION_R /* 557858382 */:
                Iterator<AbsBYDAutoSettingListener> it274 = this.mAbsBydAutoSettingListeners.iterator();
                while (it274.hasNext()) {
                    it274.next().onFdDyHeightTurnActionChanged(settingPacket.getFdDyHeightTurnActionR());
                }
                break;
            case 557858383:
                Iterator<AbsBYDAutoSettingListener> it275 = this.mAbsBydAutoSettingListeners.iterator();
                while (it275.hasNext()) {
                    it275.next().onFdDynamicSuspensionMode(settingPacket.getFdDampStateR());
                }
                break;
            case SettingHalId.ID_FD_DY_HEIGHT_LEVEL_R /* 557858384 */:
                Iterator<AbsBYDAutoSettingListener> it276 = this.mAbsBydAutoSettingListeners.iterator();
                while (it276.hasNext()) {
                    it276.next().onFdDyHeightLevelChanged(settingPacket.getFdDyHeightLevelR());
                }
                break;
            case SettingHalId.ID_FD_DY_HEIGHT_TOGGLE_STATE_R /* 557858385 */:
                Iterator<AbsBYDAutoSettingListener> it277 = this.mAbsBydAutoSettingListeners.iterator();
                while (it277.hasNext()) {
                    it277.next().onFdDyHeightToggleStateChanged(settingPacket.getFdDyHeightToggleStateR());
                }
                break;
            case SettingHalId.ID_FD_WELCOME_STATE_R /* 557858386 */:
                Iterator<AbsBYDAutoSettingListener> it278 = this.mAbsBydAutoSettingListeners.iterator();
                while (it278.hasNext()) {
                    it278.next().onFdWelcomeStateChanged(settingPacket.getFdWelcomeStateR());
                }
                break;
            case SettingHalId.ID_FD_WELCOME_ACTIVE_R /* 557858387 */:
                Iterator<AbsBYDAutoSettingListener> it279 = this.mAbsBydAutoSettingListeners.iterator();
                while (it279.hasNext()) {
                    it279.next().onFdWelcomeActiveChanged(settingPacket.getFdWelcomeActiveR());
                }
                break;
            case SettingHalId.ID_FD_FRONT_BOX_TAKE_STATE_R /* 557858388 */:
                Iterator<AbsBYDAutoSettingListener> it280 = this.mAbsBydAutoSettingListeners.iterator();
                while (it280.hasNext()) {
                    it280.next().onFdFrontBoxTakeStateChanged(settingPacket.getFdFrontBoxTakeStateR());
                }
                break;
            case SettingHalId.ID_FD_TAIL_BOX_TAKE_STATE_R /* 557858389 */:
                Iterator<AbsBYDAutoSettingListener> it281 = this.mAbsBydAutoSettingListeners.iterator();
                while (it281.hasNext()) {
                    it281.next().onFdTailBoxTakeStateChanged(settingPacket.getFdTailBoxTakeStateR());
                }
                break;
            case SettingHalId.ID_FD_CAMPING_BLANCE_STATE_R /* 557858390 */:
                Iterator<AbsBYDAutoSettingListener> it282 = this.mAbsBydAutoSettingListeners.iterator();
                while (it282.hasNext()) {
                    it282.next().onFdCampintBlacneStateChanged(settingPacket.getFdCampingBlanceStateR());
                }
                break;
            case SettingHalId.ID_FD_CAMPING_BLANCE_ACTION_R /* 557858391 */:
                Iterator<AbsBYDAutoSettingListener> it283 = this.mAbsBydAutoSettingListeners.iterator();
                while (it283.hasNext()) {
                    it283.next().onFdCampingBlanceActionChanged(settingPacket.getFdCampingBlanceActionR());
                }
                break;
            case SettingHalId.ID_FD_LIFT_MODE_STATE_R /* 557858392 */:
                Iterator<AbsBYDAutoSettingListener> it284 = this.mAbsBydAutoSettingListeners.iterator();
                while (it284.hasNext()) {
                    it284.next().onFdLiftModeStateChanged(settingPacket.getFdLiftModeStateR());
                }
                break;
            case SettingHalId.ID_FD_FIXED_STATE_R /* 557858393 */:
                Iterator<AbsBYDAutoSettingListener> it285 = this.mAbsBydAutoSettingListeners.iterator();
                while (it285.hasNext()) {
                    it285.next().onFdFixedStateChanged(settingPacket.getFdFixedStateR());
                }
                break;
            case SettingHalId.ID_FD_SPEED_SUPPORTED_TURN_R /* 557858394 */:
                Iterator<AbsBYDAutoSettingListener> it286 = this.mAbsBydAutoSettingListeners.iterator();
                while (it286.hasNext()) {
                    it286.next().onFdSpeedSupportedTurn(settingPacket.getFdSpeedSupportedTurnR());
                }
                break;
            case SettingHalId.ID_FD_MODE_SUPPORTED_HTURN_R /* 557858395 */:
                Iterator<AbsBYDAutoSettingListener> it287 = this.mAbsBydAutoSettingListeners.iterator();
                while (it287.hasNext()) {
                    it287.next().onFdModeSupportedHturnChanged(settingPacket.getFdModeSupportedHturnR());
                }
                break;
            case SettingHalId.ID_FD_WELCOME_SUPPORTED_R /* 557858396 */:
                Iterator<AbsBYDAutoSettingListener> it288 = this.mAbsBydAutoSettingListeners.iterator();
                while (it288.hasNext()) {
                    it288.next().onFdWelcomeSupportedChanged(settingPacket.getFdWelcomeSupportedR());
                }
                break;
            case SettingHalId.ID_FD_TAIL_BOX_TAKE_SUPPORTED_R /* 557858397 */:
                Iterator<AbsBYDAutoSettingListener> it289 = this.mAbsBydAutoSettingListeners.iterator();
                while (it289.hasNext()) {
                    it289.next().onFdTailBoxTakeSupportedChanged(settingPacket.getFdTailBoxTakeSupportedR());
                }
                break;
            case SettingHalId.ID_FD_TRAILER_MODE_CODE_R /* 557858398 */:
                Iterator<AbsBYDAutoSettingListener> it290 = this.mAbsBydAutoSettingListeners.iterator();
                while (it290.hasNext()) {
                    it290.next().onFdTrailerModeCodeChanged(settingPacket.getFdTrailerModeCodeR());
                }
                break;
            case SettingHalId.ID_FD_CAMPING_BLANCE_SUPPORTED_R /* 557858399 */:
                Iterator<AbsBYDAutoSettingListener> it291 = this.mAbsBydAutoSettingListeners.iterator();
                while (it291.hasNext()) {
                    it291.next().onFdCampingBlanceSupportedChanged(settingPacket.getFdCampingBlanceSupportedR());
                }
                break;
            case SettingHalId.ID_CLOSE_DOOR_LEVEL_R /* 557858411 */:
                Iterator<AbsBYDAutoSettingListener> it292 = this.mAbsBydAutoSettingListeners.iterator();
                while (it292.hasNext()) {
                    it292.next().onCloseDoorLevelChanged(settingPacket.getCloseDoorLevelR());
                }
                break;
            case SettingHalId.ID_INTEL_LOCK_OPEN_DRIVER_DOOR_R /* 557858412 */:
                Iterator<AbsBYDAutoSettingListener> it293 = this.mAbsBydAutoSettingListeners.iterator();
                while (it293.hasNext()) {
                    it293.next().onIntelLockOpenDriverDoorChanged(settingPacket.getIntelLockOpenDriverDoorR());
                }
                break;
            case SettingHalId.ID_BRAKE_CLOSE_DOOR_R /* 557858413 */:
                Iterator<AbsBYDAutoSettingListener> it294 = this.mAbsBydAutoSettingListeners.iterator();
                while (it294.hasNext()) {
                    it294.next().onBrakeCloseDoorChanged(settingPacket.getBrakeCloseDoorR());
                }
                break;
            case SettingHalId.ID_NFC_OPEN_DRIVER_DOOR_R /* 557858414 */:
                Iterator<AbsBYDAutoSettingListener> it295 = this.mAbsBydAutoSettingListeners.iterator();
                while (it295.hasNext()) {
                    it295.next().onNfcOpenDriverDoorChanged(settingPacket.getNfcOpenDriverDoorR());
                }
                break;
            case SettingHalId.ID_BLE_OPEN_DRIVER_DOOR_STATE_R /* 557858415 */:
                Iterator<AbsBYDAutoSettingListener> it296 = this.mAbsBydAutoSettingListeners.iterator();
                while (it296.hasNext()) {
                    it296.next().onBleOpenDriverDoorStateChanged(settingPacket.getBleOpenDriverDoorStateR());
                }
                break;
            case SettingHalId.ID_SAFE_BELT_CLOSE_DRIVE_DOOR_R /* 557858416 */:
                Iterator<AbsBYDAutoSettingListener> it297 = this.mAbsBydAutoSettingListeners.iterator();
                while (it297.hasNext()) {
                    it297.next().onSafeBeltCloseDriveDoorStateChanged(settingPacket.getSafeBeltCloseDriveDoorR());
                }
                break;
            case SettingHalId.ID_V_S_P_STATE_R /* 557858418 */:
                Iterator<AbsBYDAutoSettingListener> it298 = this.mAbsBydAutoSettingListeners.iterator();
                while (it298.hasNext()) {
                    it298.next().onVSPStateChanged(settingPacket.getVspStateR());
                }
                break;
            case SettingHalId.ID_DRIVER_SEAT_AUTO_RETURN_B3_R /* 557858429 */:
                Iterator<AbsBYDAutoSettingListener> it299 = this.mAbsBydAutoSettingListeners.iterator();
                while (it299.hasNext()) {
                    it299.next().onDriverSeatAutoReturnB3Changded(settingPacket.getDriverSeatAutoReturn());
                }
                break;
            case SettingHalId.ID_LRSE_MACHINE_SWITCH_STATE_R /* 557858441 */:
                Iterator<AbsBYDAutoSettingListener> it300 = this.mAbsBydAutoSettingListeners.iterator();
                while (it300.hasNext()) {
                    it300.next().onLRSEMachineSwitchStateChanged(settingPacket.getLrseMachineSwitchStateR());
                }
                break;
            case SettingHalId.ID_RRSE_MACHINE_SWITCH_STATE_R /* 557858442 */:
                Iterator<AbsBYDAutoSettingListener> it301 = this.mAbsBydAutoSettingListeners.iterator();
                while (it301.hasNext()) {
                    it301.next().onRRSEMachineSwitchStateChanged(settingPacket.getRrseMachineSwitchStateR());
                }
                break;
            case SettingHalId.ID_LRSE_CHILD_MODE_TIME_WRITE_FB_R /* 557858449 */:
                Iterator<AbsBYDAutoSettingListener> it302 = this.mAbsBydAutoSettingListeners.iterator();
                while (it302.hasNext()) {
                    it302.next().onLRSEChildModeTimeWriteFb(settingPacket.getLrseChildModeTimeWriteFbR());
                }
                break;
            case SettingHalId.ID_RRSE_CHILD_MODE_TIME_WRITE_FB_R /* 557858450 */:
                Iterator<AbsBYDAutoSettingListener> it303 = this.mAbsBydAutoSettingListeners.iterator();
                while (it303.hasNext()) {
                    it303.next().onRRSEChildModeTimeWriteFb(settingPacket.getRrseChildModeTimeWriteFbR());
                }
                break;
            case SettingHalId.ID_LRSE_CHILD_MODE_CODE_WRITE_FB_R /* 557858459 */:
                Iterator<AbsBYDAutoSettingListener> it304 = this.mAbsBydAutoSettingListeners.iterator();
                while (it304.hasNext()) {
                    it304.next().onLRSEChildModeCodeWriteFb(settingPacket.getLrseChildModeCodeWriteFbR());
                }
                break;
            case SettingHalId.ID_RRSE_CHILD_MODE_CODE_WRITE_FB_R /* 557858460 */:
                Iterator<AbsBYDAutoSettingListener> it305 = this.mAbsBydAutoSettingListeners.iterator();
                while (it305.hasNext()) {
                    it305.next().onRRSEChildModeCodeWriteFb(settingPacket.getRrseChildModeCodeWriteFbR());
                }
                break;
            case SettingHalId.ID_DRAG_MODE_FORWARD_AND_BACKWARD_STATE_R /* 557858461 */:
                Iterator<AbsBYDAutoSettingListener> it306 = this.mAbsBydAutoSettingListeners.iterator();
                while (it306.hasNext()) {
                    it306.next().onDragModeForwardAndBackwardChanged(settingPacket.getDragModeForwardAndBackwardStateR());
                }
                break;
            case SettingHalId.ID_DRAG_AND_DROP_MODE_TIPS_STATE_R /* 557858462 */:
                Iterator<AbsBYDAutoSettingListener> it307 = this.mAbsBydAutoSettingListeners.iterator();
                while (it307.hasNext()) {
                    it307.next().onDragAndDropModeTipsChanged(settingPacket.getDragAndDropModeTipsStateR());
                }
                break;
            case SettingHalId.ID_MEMORY_SET_OPERATION_FLAG_R /* 557858527 */:
                Iterator<AbsBYDAutoSettingListener> it308 = this.mAbsBydAutoSettingListeners.iterator();
                while (it308.hasNext()) {
                    it308.next().onMemorySetOperationFlagChanged(settingPacket.getMemorySetOperationFlagR());
                }
                caseFeatureIds(1220, Integer.valueOf(settingPacket.getMemorySetOperationFlagR()));
                break;
            case SettingHalId.ID_FRONT_WIPER_SPEED_VALUE_R /* 557858528 */:
                Iterator<AbsBYDAutoSettingListener> it309 = this.mAbsBydAutoSettingListeners.iterator();
                while (it309.hasNext()) {
                    it309.next().onFrontWiperSpeedValueChanged(settingPacket.getFrontWiperSpeedValueR());
                }
                caseFeatureIds(679, Integer.valueOf(settingPacket.getFrontWiperSpeedValueR()));
                break;
            case SettingHalId.ID_TRAILER_MODE_MILEAGE_R /* 557858529 */:
                Iterator<AbsBYDAutoSettingListener> it310 = this.mAbsBydAutoSettingListeners.iterator();
                while (it310.hasNext()) {
                    it310.next().onTrailerModeMileageChanged(settingPacket.getTrailerModeMileageR());
                }
                break;
            case SettingHalId.ID_REAR_WIPER_SPEED_VALUE_R /* 557858530 */:
                Iterator<AbsBYDAutoSettingListener> it311 = this.mAbsBydAutoSettingListeners.iterator();
                while (it311.hasNext()) {
                    it311.next().onRearWiperSpeedValueChanged(settingPacket.getRearWiperSpeedValueR());
                }
                break;
            case SettingHalId.ID_MAIN_HS_MASSAGE_STATE_R /* 557859101 */:
                Iterator<AbsBYDAutoSettingListener> it312 = this.mAbsBydAutoSettingListeners.iterator();
                while (it312.hasNext()) {
                    it312.next().onHsMassageStateChanged(1, settingPacket.getMainHsMassageStateR());
                }
                break;
            case SettingHalId.ID_HS_MASSAGE_STATE_PASSENGER_R /* 557859103 */:
                Iterator<AbsBYDAutoSettingListener> it313 = this.mAbsBydAutoSettingListeners.iterator();
                while (it313.hasNext()) {
                    it313.next().onHsMassageStateChanged(2, settingPacket.getHsMassageStatePassengerR());
                }
                break;
            case SettingHalId.ID_HS_MASSAGE_STATE_BACK_LEFT_R /* 557859104 */:
                Iterator<AbsBYDAutoSettingListener> it314 = this.mAbsBydAutoSettingListeners.iterator();
                while (it314.hasNext()) {
                    it314.next().onHsMassageStateChanged(3, settingPacket.getHsMassageStateBackLeftR());
                }
                break;
            case SettingHalId.ID_HS_MASSAGE_STATE_BACK_RIGHT_R /* 557859105 */:
                Iterator<AbsBYDAutoSettingListener> it315 = this.mAbsBydAutoSettingListeners.iterator();
                while (it315.hasNext()) {
                    it315.next().onHsMassageStateChanged(4, settingPacket.getHsMassageStateBackRightR());
                }
                break;
            case 557860611:
                int ambientMusicModeStateR = settingPacket.getAmbientMusicModeStateR();
                int areaId = settingPacket.getAreaId();
                for (AbsBYDAutoSettingListener absBYDAutoSettingListener3 : this.mAbsBydAutoSettingListeners) {
                    absBYDAutoSettingListener3.onAmbientMusicModeState(areaId, ambientMusicModeStateR);
                    Log.v(TAG, " onAmbientMusicModeState in musicState:" + ambientMusicModeStateR + ",musicId:" + areaId + " listener hashCode: " + System.identityHashCode(absBYDAutoSettingListener3));
                }
                caseFeatureIds(1255, Integer.valueOf(ambientMusicModeStateR));
                break;
            case 557860631:
                int ambientFeedBack = settingPacket.getAmbientFeedBack();
                Iterator<AbsBYDAutoSettingListener> it316 = this.mAbsBydAutoSettingListeners.iterator();
                while (it316.hasNext()) {
                    it316.next().onAmbientFeedBackChanged(ambientFeedBack);
                }
                caseFeatureIds(902, Integer.valueOf(ambientFeedBack));
                break;
            case 557862926:
                Iterator<AbsBYDAutoSettingListener> it317 = this.mAbsBydAutoSettingListeners.iterator();
                while (it317.hasNext()) {
                    it317.next().onScreenVoltageStateChanged(settingPacket.getScreenVoltageStateR());
                }
                break;
            case SettingHalId.ID_SWS_MEDIUM_RELATED_KEYS_R /* 557867008 */:
                Iterator<AbsBYDAutoSettingListener> it318 = this.mAbsBydAutoSettingListeners.iterator();
                while (it318.hasNext()) {
                    it318.next().onSwsMediumRelatedKeys(settingPacket.getSwsMediumRelatedKeysS());
                }
                break;
            case SettingHalId.ID_SCREEN_ROTATION_STATUS_R /* 557875283 */:
                Iterator<AbsBYDAutoSettingListener> it319 = this.mAbsBydAutoSettingListeners.iterator();
                while (it319.hasNext()) {
                    it319.next().onScreenRotationStatus(settingPacket.getScreenRotationStatusR());
                }
                break;
            case 559954810:
                Iterator<AbsBYDAutoSettingListener> it320 = this.mAbsBydAutoSettingListeners.iterator();
                while (it320.hasNext()) {
                    it320.next().onTurnSteeringWheelChanged(3, settingPacket.getSteeringWheelY(), settingPacket.getSteeringWheelZ());
                }
                break;
            case 559954811:
                Iterator<AbsBYDAutoSettingListener> it321 = this.mAbsBydAutoSettingListeners.iterator();
                while (it321.hasNext()) {
                    it321.next().onTurnSteeringWheelChanged(1, settingPacket.getSteeringWheelY(), settingPacket.getSteeringWheelZ());
                }
                break;
            case 559954812:
                Iterator<AbsBYDAutoSettingListener> it322 = this.mAbsBydAutoSettingListeners.iterator();
                while (it322.hasNext()) {
                    it322.next().onTurnRearviewMirrorChanged(3, settingPacket.getRearviewMirrorXLeft(), settingPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954813:
                Iterator<AbsBYDAutoSettingListener> it323 = this.mAbsBydAutoSettingListeners.iterator();
                while (it323.hasNext()) {
                    it323.next().onTurnRearviewMirrorChanged(7, settingPacket.getRearviewMirrorXRight(), settingPacket.getRearviewMirrorYRight());
                }
                break;
            case 559954814:
                Iterator<AbsBYDAutoSettingListener> it324 = this.mAbsBydAutoSettingListeners.iterator();
                while (it324.hasNext()) {
                    it324.next().onTurnRearviewMirrorChanged(1, settingPacket.getRearviewMirrorXLeft(), settingPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954815:
                Iterator<AbsBYDAutoSettingListener> it325 = this.mAbsBydAutoSettingListeners.iterator();
                while (it325.hasNext()) {
                    it325.next().onTurnRearviewMirrorChanged(6, settingPacket.getRearviewMirrorXRight(), settingPacket.getRearviewMirrorYRight());
                }
                break;
            case SettingHalId.ID_SEAT_TURN_HEIGHT_DRIVER /* 559954820 */:
                Iterator<AbsBYDAutoSettingListener> it326 = this.mAbsBydAutoSettingListeners.iterator();
                while (it326.hasNext()) {
                    it326.next().onTurnSeatHeightChanged(1, 1, settingPacket.getSeatTurnHeightDriver());
                }
                break;
            case SettingHalId.ID_SEAT_TURN_HEIGHT_COPILOT /* 559954821 */:
                Iterator<AbsBYDAutoSettingListener> it327 = this.mAbsBydAutoSettingListeners.iterator();
                while (it327.hasNext()) {
                    it327.next().onTurnSeatHeightChanged(2, 1, settingPacket.getSeatTurnHeightCopilot());
                }
                break;
            case SettingHalId.ID_SEAT_TURN_HEIGHT_BACK_LEFT /* 559954822 */:
                Iterator<AbsBYDAutoSettingListener> it328 = this.mAbsBydAutoSettingListeners.iterator();
                while (it328.hasNext()) {
                    it328.next().onTurnSeatHeightChanged(3, 1, settingPacket.getSeatTurnHeightBackLeft());
                }
                break;
            case SettingHalId.ID_SEAT_TURN_HEIGHT_BACK_RIGHT /* 559954823 */:
                Iterator<AbsBYDAutoSettingListener> it329 = this.mAbsBydAutoSettingListeners.iterator();
                while (it329.hasNext()) {
                    it329.next().onTurnSeatHeightChanged(4, 1, settingPacket.getSeatTurnHeightBackRight());
                }
                break;
            case SettingHalId.ID_SEAT_BACKREST_POSTION_DRIVER /* 559954824 */:
                Iterator<AbsBYDAutoSettingListener> it330 = this.mAbsBydAutoSettingListeners.iterator();
                while (it330.hasNext()) {
                    it330.next().onTurnSeatbackrestChanged(1, 1, settingPacket.getSeatbackrestPostionDriver());
                }
                break;
            case SettingHalId.ID_SEAT_BACKREST_POSTION_COPILOT /* 559954825 */:
                Iterator<AbsBYDAutoSettingListener> it331 = this.mAbsBydAutoSettingListeners.iterator();
                while (it331.hasNext()) {
                    it331.next().onTurnSeatbackrestChanged(2, 1, settingPacket.getSeatbackrestPostionCopilot());
                }
                break;
            case SettingHalId.ID_SEAT_BACKREST_POSTION_BACK_LEFT /* 559954826 */:
                Iterator<AbsBYDAutoSettingListener> it332 = this.mAbsBydAutoSettingListeners.iterator();
                while (it332.hasNext()) {
                    it332.next().onTurnSeatbackrestChanged(3, 1, settingPacket.getSeatbackrestPostionBackLeft());
                }
                break;
            case SettingHalId.ID_SEAT_BACKREST_POSTION_BACK_RIGHT /* 559954827 */:
                Iterator<AbsBYDAutoSettingListener> it333 = this.mAbsBydAutoSettingListeners.iterator();
                while (it333.hasNext()) {
                    it333.next().onTurnSeatbackrestChanged(4, 1, settingPacket.getSeatbackrestPostionBackRight());
                }
                break;
            case SettingHalId.ID_TURN_CUSHION_HEIGHT_DRIVER /* 559954832 */:
                Iterator<AbsBYDAutoSettingListener> it334 = this.mAbsBydAutoSettingListeners.iterator();
                while (it334.hasNext()) {
                    it334.next().onTurnSeatCushionChanged(1, 1, settingPacket.getTurnCushionHeightDriver());
                }
                break;
            case SettingHalId.ID_TURN_CUSHION_HEIGHT_COPILOT /* 559954833 */:
                Iterator<AbsBYDAutoSettingListener> it335 = this.mAbsBydAutoSettingListeners.iterator();
                while (it335.hasNext()) {
                    it335.next().onTurnSeatCushionChanged(2, 1, settingPacket.getTurnCushionHeightCopilot());
                }
                break;
            case SettingHalId.ID_TURN_CUSHION_HEIGHT_BACK_LEFT /* 559954834 */:
                Iterator<AbsBYDAutoSettingListener> it336 = this.mAbsBydAutoSettingListeners.iterator();
                while (it336.hasNext()) {
                    it336.next().onTurnSeatCushionChanged(3, 1, settingPacket.getTurnCushionHeightBackLeft());
                }
                break;
            case SettingHalId.ID_TURN_CUSHION_HEIGHT_BACK_RIGHT /* 559954835 */:
                Iterator<AbsBYDAutoSettingListener> it337 = this.mAbsBydAutoSettingListeners.iterator();
                while (it337.hasNext()) {
                    it337.next().onTurnSeatCushionChanged(4, 1, settingPacket.getTurnCushionHeightBackRight());
                }
                break;
            case SettingHalId.ID_TURN_CALIPER_SPLINT_HEIGHT_DRIVER /* 559954836 */:
                Iterator<AbsBYDAutoSettingListener> it338 = this.mAbsBydAutoSettingListeners.iterator();
                while (it338.hasNext()) {
                    it338.next().onTurnSeatCaliperSplintChanged(1, 1, settingPacket.getTurnCaliperSplintHeightDriver());
                }
                break;
            case SettingHalId.ID_TURN_CALIPER_SPLINT_HEIGHT_COPILOT /* 559954837 */:
                Iterator<AbsBYDAutoSettingListener> it339 = this.mAbsBydAutoSettingListeners.iterator();
                while (it339.hasNext()) {
                    it339.next().onTurnSeatCaliperSplintChanged(2, 1, settingPacket.getTurnCaliperSplintHeightCopilot());
                }
                break;
            case SettingHalId.ID_TURN_CALIPER_SPLINT_HEIGHT_BACK_LEFT /* 559954838 */:
                Iterator<AbsBYDAutoSettingListener> it340 = this.mAbsBydAutoSettingListeners.iterator();
                while (it340.hasNext()) {
                    it340.next().onTurnSeatCaliperSplintChanged(3, 1, settingPacket.getTurnCaliperSplintHeightBackLeft());
                }
                break;
            case SettingHalId.ID_TURN_CALIPER_SPLINT_HEIGHT_BACK_RIGHT /* 559954839 */:
                Iterator<AbsBYDAutoSettingListener> it341 = this.mAbsBydAutoSettingListeners.iterator();
                while (it341.hasNext()) {
                    it341.next().onTurnSeatCaliperSplintChanged(4, 1, settingPacket.getTurnCaliperSplintHeightBackRight());
                }
                break;
            case SettingHalId.ID_HEADREST_TURN_HEIGHT_DRIVER /* 559954844 */:
                Iterator<AbsBYDAutoSettingListener> it342 = this.mAbsBydAutoSettingListeners.iterator();
                while (it342.hasNext()) {
                    it342.next().onHeadrestTurnHeightChanged(1, 1, settingPacket.getHeadrestTurnHeightDriver());
                }
                break;
            case SettingHalId.ID_HEADREST_TURN_HEIGHT_COPILOT /* 559954845 */:
                Iterator<AbsBYDAutoSettingListener> it343 = this.mAbsBydAutoSettingListeners.iterator();
                while (it343.hasNext()) {
                    it343.next().onHeadrestTurnHeightChanged(2, 2, settingPacket.getHeadrestTurnHeightCopilot());
                }
                break;
            case SettingHalId.ID_HEADREST_TURN_HEIGHT_BACK_LEFT /* 559955504 */:
                Iterator<AbsBYDAutoSettingListener> it344 = this.mAbsBydAutoSettingListeners.iterator();
                while (it344.hasNext()) {
                    it344.next().onHeadrestTurnHeightChanged(3, 1, settingPacket.getHeadrestTurnHeightBackLeft());
                }
                break;
            case SettingHalId.ID_HEADREST_TURN_HEIGHT_BACK_RIGHT /* 559955505 */:
                Iterator<AbsBYDAutoSettingListener> it345 = this.mAbsBydAutoSettingListeners.iterator();
                while (it345.hasNext()) {
                    it345.next().onHeadrestTurnHeightChanged(4, 2, settingPacket.getHeadrestTurnHeightBackRight());
                }
                break;
        }
    }

    public int getACBTWind() {
        Log.i(TAG, "getACBTWind is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getACBTWind", new Object[0]);
            Log.v(TAG, "getACBTWind mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getACBTWind();
    }

    public int setACBTWind(int i) {
        Log.i(TAG, "setACBTWind is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACBTWIND, Integer.valueOf(i));
            Log.v(TAG, "setACBTWind mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setACBTWindS4E3(i);
    }

    public int getACTunnelCycle() {
        Log.i(TAG, "getACTunnelCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getACTunnelCycle", new Object[0]);
            Log.v(TAG, "getACTunnelCycle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getACTunnelCycle();
    }

    public int setACTunnelCycle(int i) {
        Log.i(TAG, "setACTunnelCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACTUNNELCYCLE, Integer.valueOf(i));
            Log.v(TAG, "setACTunnelCycle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setACTunnelCycle(i);
    }

    public int getACPauseCycle() {
        Log.i(TAG, "getACPauseCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getACPauseCycle", new Object[0]);
            Log.v(TAG, "getACPauseCycle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getACPauseCycle();
    }

    public int setACPauseCycle(int i) {
        Log.i(TAG, "setACPauseCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACPAUSECYCLE, Integer.valueOf(i));
            Log.v(TAG, "setACPauseCycle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setACPauseCycle(i);
    }

    public int getACAutoAir() {
        Log.i(TAG, "getACAutoAir is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getACAutoAir", new Object[0]);
            Log.v(TAG, "getACAutoAir mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getACAutoAir();
    }

    public int setACAutoAir(int i) {
        Log.i(TAG, "setACAutoAir is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACAUTOAIR, Integer.valueOf(i));
            Log.v(TAG, "setACAutoAir mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setACAutoAir(i);
    }

    public int getPM25Power() {
        Log.i(TAG, "getPM25Power is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getPM25Power", new Object[0]);
            Log.v(TAG, "getPM25Power mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPM25Power();
    }

    public int setPM25Power(int i) {
        Log.i(TAG, "setPM25Power is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETPM25POWER, Integer.valueOf(i));
            Log.v(TAG, "setPM25Power mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPM25Power(i);
    }

    public int getPM25SwitchCheck() {
        Log.i(TAG, "getPM25SwitchCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getPM25SwitchCheck", new Object[0]);
            Log.v(TAG, "getPM25SwitchCheck mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPM25SwitchCheck();
    }

    public int setPM25SwitchCheck(int i) {
        Log.i(TAG, "setPM25SwitchCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETPM25SWITCHCHECK, Integer.valueOf(i));
            Log.v(TAG, "setPM25SwitchCheck mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPM25SwitchCheck(i);
    }

    public int getPM25TimeCheck() {
        Log.i(TAG, "getPM25TimeCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getPM25TimeCheck", new Object[0]);
            Log.v(TAG, "getPM25TimeCheck mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPM25TimeCheck();
    }

    public int setPM25TimeCheck(int i) {
        Log.i(TAG, "setPM25TimeCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETPM25TIMECHECK, Integer.valueOf(i));
            Log.v(TAG, "setPM25TimeCheck mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPM25TimeCheck(i);
    }

    public int getEnergyFeedback() {
        Log.i(TAG, "getEnergyFeedback is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getEnergyFeedback", new Object[0]);
            Log.v(TAG, "getEnergyFeedback mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getEnergyFeedback();
    }

    public int setEnergyFeedback(int i) {
        Log.i(TAG, "setEnergyFeedback is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETENERGYFEEDBACK, Integer.valueOf(i));
            Log.v(TAG, "setEnergyFeedback mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setEnergyFeedback(i);
    }

    public int getSOCTarget() {
        Log.i(TAG, "getSOCTarget is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSOCTarget", new Object[0]);
            Log.v(TAG, "getSOCTarget mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSOCTarget();
    }

    public int setSOCTarget(int i) {
        Log.i(TAG, "setSOCTarget is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETSOCTARGET, Integer.valueOf(i));
            Log.v(TAG, "setSOCTarget mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSOCTarget(i);
    }

    public int getChargingPort() {
        Log.i(TAG, "getChargingPort is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getChargingPort", new Object[0]);
            Log.v(TAG, "getChargingPort mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getChargingPort();
    }

    public int setChargingPort(int i) {
        Log.i(TAG, "setChargingPort is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETCHARGINGPORT, Integer.valueOf(i));
            Log.v(TAG, "setChargingPort mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setChargingPort(i);
    }

    public int getAutoExternalRearMirrorFollowUpSwitch() {
        Log.i(TAG, "getAutoExternalRearMirrorFollowUpSwitch is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAutoExternalRearMirrorFollowUpSwitch", new Object[0]);
            Log.v(TAG, "getAutoExternalRearMirrorFollowUpSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAutoExternalRearMirrorFollowUpSwitch();
    }

    public int setAutoExternalRearMirrorFollowUpSwitch(int i) {
        Log.i(TAG, "setAutoExternalRearMirrorFollowUpSwitch is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAUTOEXTERNALREARMIRRORFOLLOWUPSWITCH, Integer.valueOf(i));
            Log.v(TAG, "setAutoExternalRearMirrorFollowUpSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoExternalRearMirrorFollowUpSwitch(i);
    }

    public int setRearSeatsFoldOrExpand(int i) {
        Log.i(TAG, "setRearSeatsFoldOrExpand is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRearSeatsFoldOrExpand", new Object[0]);
            Log.v(TAG, "setRearSeatsFoldOrExpand mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearSeatsFoldOrExpand(i);
    }

    public int getLockOff() {
        Log.i(TAG, "getLockOff is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getLockOff", new Object[0]);
            Log.v(TAG, "getLockOff mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLockOff();
    }

    public int getPassengerState(int i) {
        Log.i(TAG, "getPassengerState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getPassengerState", new Object[0]);
            Log.v(TAG, "getPassengerState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStatePassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateBackLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateBackRight();
        }
        if (i == 5) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateBackMid();
        }
        if (i == 6) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateFinalBackLeft();
        }
        if (i == 7) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateFinalBackRight();
        }
        if (i == 8) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateFinalBackMid();
        }
        return 0;
    }

    public int setLockOff(int i) {
        Log.i(TAG, "setLockOff is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLockOff", Integer.valueOf(i));
            Log.v(TAG, "setLockOff mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLockOff(i);
    }

    public int getOverspeedLock() {
        Log.i(TAG, "getOverspeedLock is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getOverspeedLock", new Object[0]);
            Log.v(TAG, "getOverspeedLock mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getOverspeedLock();
    }

    public int setOverspeedLock(int i) {
        Log.i(TAG, "setOverspeedLock is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETOVERSPEEDLOCK, Integer.valueOf(i));
            Log.v(TAG, "setOverspeedLock mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOverspeedLock(i);
    }

    public int getSafeWarnState() {
        Log.i(TAG, "getSafeWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSafeWarnState", new Object[0]);
            Log.v(TAG, "getSafeWarnState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSafeWarnState();
    }

    public int getMaintainRemindState() {
        Log.i(TAG, "getMaintainRemindState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getMaintainRemindState", new Object[0]);
            Log.v(TAG, "getMaintainRemindState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMaintainRemindState();
    }

    public int getSteerAssis() {
        Log.i(TAG, "getSteerAssis is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSteerAssis", new Object[0]);
            Log.v(TAG, "getSteerAssis mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteerAssis();
    }

    public int setSteerAssis(int i) {
        Log.i(TAG, "setSteerAssis is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETSTEERASSIS, Integer.valueOf(i));
            Log.v(TAG, "setSteerAssis mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteerAssis(i);
    }

    public int getRearViewMirrorFlip() {
        Log.i(TAG, "getRearViewMirrorFlip is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRearViewMirrorFlip", new Object[0]);
            Log.v(TAG, "getRearViewMirrorFlip mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearViewMirrorFlip();
    }

    public int setRearViewMirrorFlip(int i) {
        Log.i(TAG, "setRearViewMirrorFlip is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREARVIEWMIRRORFLIP, Integer.valueOf(i));
            Log.v(TAG, "setRearViewMirrorFlip mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearViewMirrorFlip(i);
    }

    public int getDriverSeatAutoReturn() {
        Log.i(TAG, "getDriverSeatAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getDriverSeatAutoReturn", new Object[0]);
            Log.v(TAG, "getDriverSeatAutoReturn mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDriverSeatAutoReturn();
    }

    public int setDriverSeatAutoReturn(int i) {
        Log.i(TAG, "setDriverSeatAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETDRIVERSEATAUTORETURN, Integer.valueOf(i));
            Log.v(TAG, "setDriverSeatAutoReturn mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDriverSeatAutoReturn(i);
    }

    public int getSteerPositionAutoReturn() {
        Log.i(TAG, "getSteerPositionAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSteerPositionAutoReturn", new Object[0]);
            Log.v(TAG, "getSteerPositionAutoReturn mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteerPositionAutoReturn();
    }

    public int setSteerPositionAutoReturn(int i) {
        Log.i(TAG, "setSteerPositionAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETSTEERPOSITIONAUTORETURN, Integer.valueOf(i));
            Log.v(TAG, "setSteerPositionAutoReturn mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteerPositionAutoReturn(i);
    }

    public int getRemoteControlUpwindowState() {
        Log.i(TAG, "getRemoteControlUpwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRemoteControlUpwindowState", new Object[0]);
            Log.v(TAG, "getRemoteControlUpwindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRemoteControlUpwindowState();
    }

    public int setRemoteControlUpwindowState(int i) {
        Log.i(TAG, "setRemoteControlUpwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRemoteControlUpwindowState", Integer.valueOf(i));
            Log.v(TAG, "setRemoteControlUpwindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlUpwindowState(i);
    }

    public int getRemoteControlDownwindowState() {
        Log.i(TAG, "getRemoteControlDownwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRemoteControlDownwindowState", new Object[0]);
            Log.v(TAG, "getRemoteControlDownwindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRemoteControlDownwindowState();
    }

    public int setRemoteControlDownwindowState(int i) {
        Log.i(TAG, "setRemoteControlDownwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRemoteControlDownwindowState", Integer.valueOf(i));
            Log.v(TAG, "setRemoteControlDownwindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlDownwindowState(i);
    }

    public int getLockCarRiseWindow() {
        Log.i(TAG, "getLockCarRiseWindow is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getLockCarRiseWindow", new Object[0]);
            Log.v(TAG, "getLockCarRiseWindow mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLockCarRiseWindow();
    }

    public int setLockCarRiseWindow(int i) {
        Log.i(TAG, "setLockCarRiseWindow is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETLOCKCARRISEWINDOW, Integer.valueOf(i));
            Log.v(TAG, "setLockCarRiseWindow mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLockCarRiseWindow(i);
    }

    public int getMicroSwitchLockWindowState() {
        Log.i(TAG, "getMicroSwitchLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getMicroSwitchLockWindowState", new Object[0]);
            Log.v(TAG, "getMicroSwitchLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMicroSwitchLockWindowState();
    }

    public int setMicroSwitchLockWindowState(int i) {
        Log.i(TAG, "setMicroSwitchLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETMICROSWITCHLOCKWINDOWSTATE, Integer.valueOf(i));
            Log.v(TAG, "setMicroSwitchLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMicroSwitchLockWindowState(i);
    }

    public int getMicroSwitchUnlockWindowState() {
        Log.i(TAG, "getMicroSwitchUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getMicroSwitchUnlockWindowState", new Object[0]);
            Log.v(TAG, "getMicroSwitchUnlockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMicroSwitchUnlockWindowState();
    }

    public int setMicroSwitchUnlockWindowState(int i) {
        Log.i(TAG, "setMicroSwitchUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETMICROSWITCHUNLOCKWINDOWSTATE, Integer.valueOf(i));
            Log.v(TAG, "setMicroSwitchUnlockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMicroSwitchUnlockWindowState(i);
    }

    public int getBackHomeLightDelayValue() {
        Log.i(TAG, "getBackHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getBackHomeLightDelayValue", new Object[0]);
            Log.v(TAG, "getBackHomeLightDelayValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getBackHomeLightDelayValue();
    }

    public int setBackHomeLightDelayValue(int i) {
        Log.i(TAG, "setBackHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setBackHomeLightDelayValue", Integer.valueOf(i));
            Log.v(TAG, "setBackHomeLightDelayValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBackHomeLightDelayValue(i);
    }

    public int getLeftHomeLightDelay() {
        Log.i(TAG, "getLeftHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getLeftHomeLightDelay", new Object[0]);
            Log.v(TAG, "getLeftHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLeftHomeLightDelay();
    }

    public int setLeftHomeLightDelay(int i) {
        Log.i(TAG, "setLeftHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLeftHomeLightDelay", Integer.valueOf(i));
            Log.v(TAG, "setLeftHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeftHomeLightDelay(i);
    }

    public int getBackDoorElectricMode() {
        Log.i(TAG, "getBackDoorElectricMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getBackDoorElectricMode", new Object[0]);
            Log.v(TAG, "getBackDoorElectricMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getBackDoorElectricMode();
    }

    public int setBackDoorElectricMode(int i) {
        Log.i(TAG, "setBackDoorElectricMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETBACKDOORELECTRICMODE, Integer.valueOf(i));
            Log.v(TAG, "setBackDoorElectricMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBackDoorElectricMode(i);
    }

    public void registerListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        registerListener(absBYDAutoSettingListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoSettingListener absBYDAutoSettingListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe setting listener " + absBYDAutoSettingListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoSettingListener == null) {
            return;
        }
        if (this.mForTest == 0) {
            this.mTestCallback.setAbsBYDAutoStatisticListener(absBYDAutoSettingListener);
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (!this.mAbsBydAutoSettingListeners.contains(absBYDAutoSettingListener)) {
            this.mAbsBydAutoSettingListeners.add(absBYDAutoSettingListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoSettingListener);
        TsManagerImpl.getInstance(this.mContext).registerSettingCallback();
    }

    public void unregisterListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.setAbsBYDAutoStatisticListener(null);
            Log.d(TAG, "unregisterListener mForTestValue is 0 execute return");
            return;
        }
        Log.d(TAG, "unsubscribeSettingCallback: " + absBYDAutoSettingListener);
        if (absBYDAutoSettingListener == null) {
            return;
        }
        this.mAbsBydAutoSettingListeners.remove(absBYDAutoSettingListener);
        unregisterConnectListener(absBYDAutoSettingListener);
    }

    public int getAutoPurificationState() {
        Log.i(TAG, "getAutoPurificationState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAutoPurificationState", new Object[0]);
            Log.v(TAG, "getAutoPurificationState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAutoPurificationState();
    }

    public int setAutoPurification(int i) {
        Log.i(TAG, "setAutoPurification is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAUTOPURIFICATION, Integer.valueOf(i));
            Log.v(TAG, "setAutoPurification mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoPurification(i);
    }

    public int getRemoteKeyStartAcTime() {
        Log.i(TAG, "getRemoteKeyStartAcTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRemoteKeyStartAcTime", new Object[0]);
            Log.v(TAG, "getRemoteKeyStartAcTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRemoteKeyStartAcTime();
    }

    public int setRemoteKeyStartAcTime(int i) {
        Log.i(TAG, "setRemoteKeyStartAcTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREMOTEKEYSTARTACTIME, Integer.valueOf(i));
            Log.v(TAG, "setRemoteKeyStartAcTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteKeyStartAcTime(i);
    }

    public int getAcTemperatureUnit() {
        Log.i(TAG, "getAcTemperatureUnit is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAcTemperatureUnit", new Object[0]);
            Log.v(TAG, "getAcTemperatureUnit mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAcTemperatureUnit();
    }

    public int setAcTemperatureUnit(int i) {
        Log.i(TAG, "setAcTemperatureUnit is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACTEMPERATUREUNIT, Integer.valueOf(i));
            Log.v(TAG, "setAcTemperatureUnit mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAcTemperatureUnit(i);
    }

    public int getAcWarmMode() {
        Log.i(TAG, "getAcWarmMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAcWarmMode", new Object[0]);
            Log.v(TAG, "getAcWarmMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAcWarmMode();
    }

    public int setAcWarmMode(int i) {
        Log.i(TAG, "setAcWarmMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACWARMMODE, Integer.valueOf(i));
            Log.v(TAG, "setAcWarmMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAcWarmMode(i);
    }

    public int setIntelligentAcAreaState(int i) {
        Log.i(TAG, "setIntelligentAcAreaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETINTELLIGENTACAREASTATE, Integer.valueOf(i));
            Log.v(TAG, "setIntelligentAcAreaState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentAcAreaState(i);
    }

    public int getIntelligentAcAreaState() {
        Log.i(TAG, "getIntelligentAcAreaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getIntelligentAcAreaState", new Object[0]);
            Log.v(TAG, "getIntelligentAcAreaState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getIntelligentAcAreaState();
    }

    public int getRearAcOnlineState() {
        Log.i(TAG, "getRearAcOnlineState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRearAcOnlineState", new Object[0]);
            Log.v(TAG, "getRearAcOnlineState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearAcOnlineState();
    }

    public int getBackDoorOpenedHeight() {
        Log.i(TAG, "getBackDoorOpenedHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getBackDoorOpenedHeight", new Object[0]);
            Log.v(TAG, "getBackDoorOpenedHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getBackDoorOpenedHeight();
    }

    public int setBackDoorOpenedHeight(int i) {
        Log.i(TAG, "setBackDoorOpenedHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETBACKDOOROPENEDHEIGHT, Integer.valueOf(i));
            Log.v(TAG, "setBackDoorOpenedHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBackDoorOpenedHeight(i);
    }

    public int getSupportDoorHeight() {
        Log.i(TAG, "getSupportDoorHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSupportDoorHeight", new Object[0]);
            Log.v(TAG, "getSupportDoorHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSupportDoorHeight();
    }

    @Deprecated
    public int getRemoteControlLockWindowState() {
        Log.i(TAG, "getRemoteControlLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRemoteControlLockWindowState", new Object[0]);
            Log.v(TAG, "getRemoteControlLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRemoteControlLockWindowState();
    }

    @Deprecated
    public int setRemoteControlLockWindowState(int i) {
        Log.i(TAG, "setRemoteControlLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREMOTECONTROLLOCKWINDOWSTATE, Integer.valueOf(i));
            Log.v(TAG, "setRemoteControlLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlLockWindowState(i);
    }

    @Deprecated
    public int getRemoteControlUnlockWindowState() {
        Log.i(TAG, "getRemoteControlUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRemoteControlUnlockWindowState", new Object[0]);
            Log.v(TAG, "getRemoteControlUnlockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRemoteControlUnlockWindowState();
    }

    @Deprecated
    public int setRemoteControlUnlockWindowState(int i) {
        Log.i(TAG, "setRemoteControlUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREMOTECONTROLUNLOCKWINDOWSTATE, Integer.valueOf(i));
            Log.v(TAG, "setRemoteControlUnlockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlUnlockWindowState(i);
    }

    public int getRainAutoLockWindowState() {
        Log.i(TAG, "getRainAutoLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRainAutoLockWindowState", new Object[0]);
            Log.v(TAG, "getRainAutoLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRainAutoLockWindowState();
    }

    public int setRainAutoLockWindowState(int i) {
        Log.i(TAG, "setRainAutoLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETRAINAUTOLOCKWINDOWSTATE, Integer.valueOf(i));
            Log.v(TAG, "setRainAutoLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRainAutoLockWindowState(i);
    }

    public int getDoorknobAutoShrinkState() {
        Log.i(TAG, "getDoorknobAutoShrinkState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getDoorknobAutoShrinkState", new Object[0]);
            Log.v(TAG, "getDoorknobAutoShrinkState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDoorknobAutoShrinkState();
    }

    public int setDoorknobAutoShrinkState(int i) {
        Log.i(TAG, "setDoorknobAutoShrinkState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETDOORKNOBAUTOSHRINKSTATE, Integer.valueOf(i));
            Log.v(TAG, "setDoorknobAutoShrinkState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDoorknobAutoShrinkState(i);
    }

    public int getIntelligentUnlockState() {
        Log.i(TAG, "getIntelligentUnlockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getIntelligentUnlockState", new Object[0]);
            Log.v(TAG, "getIntelligentUnlockState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getIntelligentUnlockState();
    }

    public int setIntelligentUnlockState(int i) {
        Log.i(TAG, "setIntelligentUnlockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIntelligentUnlockState", Integer.valueOf(i));
            Log.v(TAG, "setIntelligentUnlockState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentUnlockState(i);
    }

    public int getIntelligentLockState() {
        Log.i(TAG, "getIntelligentLockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getIntelligentLockState", new Object[0]);
            Log.v(TAG, "getIntelligentLockState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getIntelligentLockState();
    }

    public int setIntelligentLockState(int i) {
        Log.i(TAG, "setIntelligentLockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIntelligentLockState", Integer.valueOf(i));
            Log.v(TAG, "setIntelligentLockState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentLockState(i);
    }

    public int setHudParamsEffect() {
        Log.i(TAG, "setHudParamsEffect is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDPARAMSEFFECT, new Object[0]);
            Log.v(TAG, "setHudParamsEffect mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudParamsEffect();
    }

    public int getHudState() {
        Log.i(TAG, "getHudState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHudState", new Object[0]);
            Log.v(TAG, "getHudState mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudState();
    }

    public int setHudState(int i) {
        Log.i(TAG, "setHudState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDSTATE, Integer.valueOf(i));
            Log.v(TAG, "setHudState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudState(i);
    }

    public int getHudHeight() {
        Log.i(TAG, "getHudHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHudHeight", new Object[0]);
            Log.v(TAG, "getHudHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudHeight();
    }

    public int setHudHeight(int i) {
        Log.i(TAG, "setHudHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDHEIGHT, Integer.valueOf(i));
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.ONHUDRESTARTANDEFFECT, Integer.valueOf(i));
            Log.v(TAG, "setHudHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudHeight(i);
    }

    @Deprecated
    public int resetHudHeight() {
        Log.i(TAG, "resetHudHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.RESETHUDHEIGHT, new Object[0]);
            Log.v(TAG, "resetHudHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHudHeight();
    }

    public int getHudBrightness() {
        Log.i(TAG, "getHudBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHudBrightness", new Object[0]);
            Log.v(TAG, "getHudBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudBrightness();
    }

    public int setHudBrightness(int i) {
        Log.i(TAG, "setHudBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDBRIGHTNESS, Integer.valueOf(i));
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.ONHUDRESTARTANDEFFECT, Integer.valueOf(i));
            Log.v(TAG, "setHudBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudBrightness(i);
    }

    @Deprecated
    public int resetHudBrightness() {
        Log.i(TAG, "resetHudBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.RESETHUDBRIGHTNESS, new Object[0]);
            Log.v(TAG, "resetHudBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHudBrightness();
    }

    public int getHudRotationAngle() {
        Log.i(TAG, "getHudRotationAngle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHudRotationAngle", new Object[0]);
            Log.v(TAG, "getHudRotationAngle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudRotationAngle();
    }

    public int setHudRotationAngle(int i) {
        Log.i(TAG, "setHudRotationAngle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDROTATIONANGLE, Integer.valueOf(i));
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.ONHUDRESTARTANDEFFECT, Integer.valueOf(i));
            Log.v(TAG, "setHudRotationAngle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudRotationAngle(i);
    }

    @Deprecated
    public void resetHudRotationAngle() {
        Log.i(TAG, "resetHudRotationAngle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.RESETHUDROTATIONANGLE, new Object[0]);
            Log.v(TAG, "resetHudRotationAngle mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetHudRotationAngle();
        }
    }

    public int getHudMode() {
        Log.i(TAG, "getHudMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHudMode", new Object[0]);
            Log.v(TAG, "getHudMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudMode();
    }

    public int setHudMode(int i) {
        Log.i(TAG, "setHudMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDMODE, Integer.valueOf(i));
            Log.v(TAG, "setHudMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudMode(i);
    }

    @Deprecated
    public int resetHudMode() {
        Log.i(TAG, "resetHudMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.RESETHUDMODE, new Object[0]);
            Log.v(TAG, "resetHudMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHudMode();
    }

    public int getHudDisplayContent(int i) {
        Log.i(TAG, "getHudDisplayContent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHudDisplayContent", Integer.valueOf(i));
            Log.v(TAG, "getHudDisplayContent mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudDisplayContentDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudDisplayContentNavigation();
        }
        return 0;
    }

    public int setHudDisplayContent(int i, int i2) {
        Log.i(TAG, "setHudDisplayContent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDDISPLAYCONTENT, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setHudDisplayContent mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudDisplayContentDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudDisplayContentNavigation(i2);
        }
        return 0;
    }

    @Deprecated
    public void resetHudDisplayContent() {
        Log.i(TAG, "resetHudDisplayContent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.RESETHUDDISPLAYCONTENT, new Object[0]);
            Log.v(TAG, "resetHudDisplayContent mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetHudDisplayContent();
        }
    }

    public int getEngineSoundType() {
        Log.i(TAG, "getEngineSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getEngineSoundType", new Object[0]);
            Log.v(TAG, "getEngineSoundType mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getEngineSoundType();
    }

    public int setEngineSoundType(int i) {
        Log.i(TAG, "setEngineSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETENGINESOUNDTYPE, Integer.valueOf(i));
            Log.v(TAG, "setEngineSoundType mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setEngineSoundType(i);
    }

    public int setCarTipsSoundType(int i) {
        Log.i(TAG, "setCarTipsSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETCARTIPSSOUNDTYPE, Integer.valueOf(i));
            Log.v(TAG, "setCarTipsSoundType mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCarTipsSoundType(i);
    }

    public int getCarTipsSoundType() {
        Log.i(TAG, "getCarTipsSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getCarTipsSoundType", new Object[0]);
            Log.v(TAG, "getCarTipsSoundType mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCarTipsSoundType();
    }

    public int getReportVolume() {
        Log.i(TAG, "getReportVolume is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getReportVolume", new Object[0]);
            Log.v(TAG, "getReportVolume mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getReportVolume();
    }

    public int setReportVolume(int i) {
        Log.i(TAG, "setReportVolume is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETGETREPORTVOLUME, Integer.valueOf(i));
            Log.v(TAG, "setReportVolume mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setReportVolume(i);
    }

    public int getSpeedReminderValue() {
        Log.i(TAG, "getSpeedReminderValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSpeedReminderValue", new Object[0]);
            Log.v(TAG, "getSpeedReminderValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSpeedReminderValue();
    }

    public int setSpeedReminderValue(int i) {
        Log.i(TAG, "setSpeedReminderValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETSPEEDREMINDERVALUE, Integer.valueOf(i));
            Log.v(TAG, "setSpeedReminderValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSpeedReminderValue(i);
    }

    public int getAppUseLimitState() {
        Log.i(TAG, "getAppUseLimitState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAppUseLimitState", new Object[0]);
            Log.v(TAG, "getAppUseLimitState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAppUseLimitState();
    }

    public int setAppUseLimitState(int i) {
        Log.i(TAG, "setAppUseLimitState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAPPUSELIMITSTATE, Integer.valueOf(i));
            Log.v(TAG, "setAppUseLimitState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAppUseLimitState(i);
    }

    public int getNavigationMediaState() {
        Log.i(TAG, "getNavigationMediaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getNavigationMediaState", new Object[0]);
            Log.v(TAG, "getNavigationMediaState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getNavigationMediaState();
    }

    public int setNavigationMediaState(int i) {
        Log.i(TAG, "setNavigationMediaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETNAVIGATIONMEDIASTATE, Integer.valueOf(i));
            Log.v(TAG, "setNavigationMediaState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setNavigationMediaState(i);
    }

    public int getAutoPowerOnState() {
        Log.i(TAG, "getAutoPowerOnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAutoPowerOnState", new Object[0]);
            Log.v(TAG, "getAutoPowerOnState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAutoPowerOnState();
    }

    public int setAutoPowerOnState(int i) {
        Log.i(TAG, "setAutoPowerOnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAUTOPOWERONSTATE, Integer.valueOf(i));
            Log.v(TAG, "setAutoPowerOnState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoPowerOnState(i);
    }

    public int getAutoPowerOffState() {
        Log.i(TAG, "getAutoPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAutoPowerOffState", new Object[0]);
            Log.v(TAG, "getAutoPowerOffState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAutoPowerOffState();
    }

    public int setAutoPowerOffState(int i) {
        Log.i(TAG, "setAutoPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setAutoPowerOffState", Integer.valueOf(i));
            Log.v(TAG, "setAutoPowerOffState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoPowerOffState(i);
    }

    public int resetAutoPowerOnState() {
        Log.i(TAG, "resetAutoPowerOnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.RESETAUTOPOWERONSTATE, new Object[0]);
            Log.v(TAG, "resetAutoPowerOnState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetAutoPowerOnState();
    }

    @Deprecated
    public int resetAutoExternalRearMirrorFollowUp() {
        Log.i(TAG, "resetAutoExternalRearMirrorFollowUp is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.RESETAUTOEXTERNALREARMIRRORFOLLOWUP, new Object[0]);
            Log.v(TAG, "resetAutoExternalRearMirrorFollowUp mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetAutoExternalRearMirrorFollowUp();
    }

    public int getBrakePedalMode() {
        Log.i(TAG, "getBrakePedalMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getBrakePedalMode", new Object[0]);
            Log.v(TAG, "getBrakePedalMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int brakePedalMode = this.mTsManagerImpl.getSettingAdapterManager().getBrakePedalMode();
        if (brakePedalMode == 0) {
            return 2;
        }
        if (brakePedalMode == 1) {
            return 1;
        }
        return brakePedalMode;
    }

    public int setBrakePedalMode(int i) {
        Log.i(TAG, "setBrakePedalMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETBRAKEPEDALMODE, Integer.valueOf(i));
            Log.v(TAG, "setBrakePedalMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBrakePedalMode(i);
    }

    public int getComfortableParkingState() {
        Log.i(TAG, "getComfortableParkingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getComfortableParkingState", new Object[0]);
            Log.v(TAG, "getComfortableParkingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getComfortableParkingState();
    }

    public int setComfortableParkingState(int i) {
        Log.i(TAG, "setComfortableParkingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETCOMFORTABLEPARKINGSTATE, Integer.valueOf(i));
            Log.v(TAG, "setComfortableParkingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setComfortableParkingState(i);
    }

    public int getMotorPretensionerSeatbeltState() {
        Log.i(TAG, "getMotorPretensionerSeatbeltState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getMotorPretensionerSeatbeltState", new Object[0]);
            Log.v(TAG, "getMotorPretensionerSeatbeltState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMotorPretensionerSeatbeltState();
    }

    public int setMotorPretensionerSeatbeltState(int i) {
        Log.i(TAG, "setMotorPretensionerSeatbeltState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETMOTORPRETENSIONERSEATBELTSTATE, Integer.valueOf(i));
            Log.v(TAG, "setMotorPretensionerSeatbeltState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            i = 2;
        } else if (i == 2) {
            i = 1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMotorPretensionerSeatbeltState(i);
    }

    @Deprecated
    public int resetMotorPretensionerSeatbelt() {
        Log.i(TAG, "resetMotorPretensionerSeatbelt is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.RESETMOTORPRETENSIONERSEATBELT, new Object[0]);
            Log.v(TAG, "resetMotorPretensionerSeatbelt mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetMotorPretensionerSeatbelt();
    }

    public int getAutoRainWiperState() {
        Log.i(TAG, "getAutoRainWiperState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAutoRainWiperState", new Object[0]);
            Log.v(TAG, "getAutoRainWiperState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAutoRainWiperState();
    }

    public int setAutoRainWiperState(int i) {
        Log.i(TAG, "setAutoRainWiperState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAUTORAINWIPERSTATE, Integer.valueOf(i));
            Log.v(TAG, "setAutoRainWiperState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoRainWiperState(i);
    }

    public int getDynamicSuspensionMode() {
        Log.i(TAG, "getDynamicSuspensionMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getDynamicSuspensionMode", new Object[0]);
            Log.v(TAG, "getDynamicSuspensionMode mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDynamicSuspensionMode();
    }

    public int setDynamicSuspensionMode(int i) {
        Log.i(TAG, "setDynamicSuspensionMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETDYNAMICSUSPENSIONMODE, Integer.valueOf(i));
            Log.v(TAG, "setDynamicSuspensionMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDynamicSuspensionMode(i);
    }

    public int getLandAdvanceAtSate() {
        Log.i(TAG, "getLandAdvanceAtSate is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getLandAdvanceAtSate", new Object[0]);
            Log.v(TAG, "getLandAdvanceAtSate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLandAdvanceAtSate();
    }

    public int setLandAdvanceAtState(int i) {
        Log.i(TAG, "setLandAdvanceAtState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLandAdvanceAtState", Integer.valueOf(i));
            Log.v(TAG, "setLandAdvanceAtState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLandAdvanceAtState(i);
    }

    public float getSteeringWheelY() {
        Log.i(TAG, "getSteeringWheelY is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSteeringWheelY", new Object[0]);
            Log.v(TAG, "getSteeringWheelY mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelY();
    }

    public float getSteeringWheelZ() {
        Log.i(TAG, "getSteeringWheelZ is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSteeringWheelZ", new Object[0]);
            Log.v(TAG, "getSteeringWheelZ mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelZ();
    }

    public void resetSteeringWheelPosition(int i) {
        Log.i(TAG, "resetSteeringWheelPosition is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetSteeringWheelPosition", Integer.valueOf(i));
            Log.v(TAG, "resetSteeringWheelPosition mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetSteeringWheelPosition(i);
        }
    }

    public float getRearviewMirrorY(int i) {
        Log.i(TAG, "getRearviewMirrorY is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRearviewMirrorY", Integer.valueOf(i));
            Log.v(TAG, "getRearviewMirrorY mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorYLeft();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorYRight();
        }
        return 0.0f;
    }

    public float getRearviewMirrorX(int i) {
        Log.i(TAG, "getRearviewMirrorX is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRearviewMirrorX", Integer.valueOf(i));
            Log.v(TAG, "getRearviewMirrorX mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorXLeft();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorXRight();
        }
        return 0.0f;
    }

    public void resetRearviewMirrorParams(int i, int i2) {
        Log.i(TAG, "resetRearviewMirrorParams is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetRearviewMirrorParams", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "resetRearviewMirrorParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetRearviewMirrorParams(i, i2);
        }
    }

    public int getSeatHorization(int i) {
        Log.i(TAG, "getSeatHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSeatHorization", Integer.valueOf(i));
            Log.v(TAG, "getSeatHorization mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHorizationDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHorizationPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHorizationLeftArea();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHorizationRightArea();
        }
        return 0;
    }

    public float getSeatTurnHeight(int i) {
        Log.i(TAG, "getSeatTurnHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSeatTurnHeight", Integer.valueOf(i));
            Log.v(TAG, "getSeatTurnHeight mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatTurnHeightDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatTurnHeightPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatTurnHeightLeftArea();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatTurnHeightRightArea();
        }
        return 0.0f;
    }

    public float getSeatbackrestPostion(int i) {
        Log.i(TAG, "getSeatbackrestPostion is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSeatbackrestPostion", Integer.valueOf(i));
            Log.v(TAG, "getSeatbackrestPostion mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatbackrestPostionDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatbackrestPostionPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatbackrestPostionLeftArea();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatbackrestPostionRightArea();
        }
        return 0.0f;
    }

    public void resetSeatParams(int i, int i2) {
        Log.i(TAG, "resetSeatParams is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetSeatParams", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "resetSeatParams mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        if (i2 == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().resetSeatParamsDriver(i);
            return;
        }
        if (i2 == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().resetSeatParamsPassenger(i);
        } else if (i2 == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().resetSeatParamsLeftArea(i);
        } else if (i2 == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().resetSeatParamsRightArea(i);
        }
    }

    public int getCushionSupport(int i) {
        Log.i(TAG, "getCushionSupport is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getCushionSupport", Integer.valueOf(i));
            Log.v(TAG, "getCushionSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCushionSupport(i);
    }

    public float getTurnCushionHeight(int i) {
        Log.i(TAG, "getTurnCushionHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getTurnCushionHeight", Integer.valueOf(i));
            Log.v(TAG, "getTurnCushionHeight mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCushionHeightDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCushionHeightPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCushionHeightLeftArea();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCushionHeightRightArea();
        }
        return 0.0f;
    }

    public void resetCushionHeight(int i, int i2) {
        Log.i(TAG, "resetCushionHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetCushionHeight", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "resetCushionHeight mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetCushionHeight(i, i2);
        }
    }

    public float getTurnCaliperSplintHeight(int i) {
        Log.i(TAG, "getTurnCaliperSplintHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getTurnCaliperSplintHeight", Integer.valueOf(i));
            Log.v(TAG, "getTurnCaliperSplintHeight mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCaliperSplintHeightDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCaliperSplintHeightPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCaliperSplintHeightLeftArea();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCaliperSplintHeightRightArea();
        }
        return 0.0f;
    }

    public void resetCaliperSplintHeight(int i, int i2) {
        Log.i(TAG, "resetCaliperSplintHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetCaliperSplintHeight", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "resetCaliperSplintHeight mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetCaliperSplintHeight(i, i2);
        }
    }

    public int getHeadrestHorization(int i) {
        Log.i(TAG, "getHeadrestHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHeadrestHorization", Integer.valueOf(i));
            Log.v(TAG, "getHeadrestHorization mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestHorizationDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestHorizationPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestHorizationLeftArea();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestHorizationRightArea();
        }
        return 0;
    }

    public float getHeadrestTurnHeight(int i) {
        Log.i(TAG, "getHeadrestTurnHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHeadrestTurnHeight", Integer.valueOf(i));
            Log.v(TAG, "getHeadrestTurnHeight mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestTurnHeightDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestTurnHeightPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestTurnHeightLeftArea();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestTurnHeightRightArea();
        }
        return 0.0f;
    }

    public int resetHeadrestParms(int i, int i2) {
        Log.i(TAG, "resetHeadrestParms is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetHeadrestParms", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "resetHeadrestParms mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHeadrestParms(i, i2);
    }

    public int setRefrigeratorMode(int i) {
        Log.i(TAG, "setRefrigeratorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREFRIGERATORMODE, Integer.valueOf(i));
            Log.v(TAG, "setRefrigeratorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 0) {
            i = 3;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRefrigeratorMode(i);
    }

    public int getRemenberPowerOffState() {
        Log.i(TAG, "getRemenberPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRemenberPowerOffState", new Object[0]);
            Log.v(TAG, "getRemenberPowerOffState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRemenberPowerOffState();
    }

    public int setRemenberPowerOffState(int i) {
        Log.i(TAG, "setRemenberPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREMENBERPOWEROFFSTATE, Integer.valueOf(i));
            Log.v(TAG, "setRemenberPowerOffState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemenberPowerOffState(i);
    }

    public void resetRemenberPowerOff() {
        Log.i(TAG, "resetRemenberPowerOff is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetRemenberPowerOff", new Object[0]);
            Log.v(TAG, "resetRemenberPowerOff mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetRemenberPowerOff();
        }
    }

    public int getRefrigeratorDoorState() {
        Log.i(TAG, "getRefrigeratorDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRefrigeratorDoorState", new Object[0]);
            Log.v(TAG, "getRefrigeratorDoorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorDoorState();
    }

    public int setDisinfState(int i) {
        Log.i(TAG, "setDisinfState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETDISINFSTATE, Integer.valueOf(i));
            Log.v(TAG, "setDisinfState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDisinfState(i);
    }

    public int getDisinfState() {
        Log.i(TAG, "getDisinfState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getDisinfState", new Object[0]);
            Log.v(TAG, "getDisinfState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDisinfState();
    }

    public int getTrayState() {
        Log.i(TAG, "getTrayState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getTrayState", new Object[0]);
            Log.v(TAG, "getTrayState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrayState();
    }

    public int setTrayState(int i) {
        Log.i(TAG, "setTrayState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETTRAYSTATE, Integer.valueOf(i));
            Log.v(TAG, "setTrayState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTrayState(i);
    }

    public int getRefrigeratorMode() {
        Log.i(TAG, "getRefrigeratorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRefrigeratorMode", new Object[0]);
            Log.v(TAG, "getRefrigeratorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        int refrigeratorMode = this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorMode();
        if (refrigeratorMode == 3) {
            return 0;
        }
        return refrigeratorMode;
    }

    public int getModelSupport(int i) {
        Log.i(TAG, "getModelSupport is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getModelSupport", Integer.valueOf(i));
            Log.v(TAG, "getModelSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getModelSupport(i);
    }

    public int hasFeature(String str) {
        Log.i(TAG, "hasFeature is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("hasFeature", 0);
            Log.v(TAG, "hasFeature mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().hasFeature(str);
    }

    public int getHudModeParams() {
        Log.i(TAG, "getHudModeParams is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHudModeParams", new Object[0]);
            Log.v(TAG, "getHudModeParams mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudModeParams();
    }

    public void turnHeadrestTurnHeight(int i, int i2) {
        Log.i(TAG, "turnHeadrestTurnHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnHeadrestTurnHeight", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnHeadrestTurnHeight mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHeightDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHeightPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHeightLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHeightRightArea(i2);
        }
    }

    public void turnHeadrestTurnHorization(int i, int i2) {
        Log.i(TAG, "turnHeadrestTurnHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnHeadrestTurnHorization", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnHeadrestTurnHorization mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHorizationDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHorizationPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHorizationLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHorizationRightArea(i2);
        }
    }

    public void turnSeatCaliperSplint(int i, int i2) {
        Log.i(TAG, "turnSeatCaliperSplint is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatCaliperSplint", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnSeatCaliperSplint mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRightArea(i2);
        }
    }

    public void turnSeatCushion(int i, int i2) {
        Log.i(TAG, "turnSeatCushion is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatCushion", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnSeatCushion mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRightArea(i2);
        }
    }

    public void turnSeatbackrest(int i, int i2) {
        Log.i(TAG, "turnSeatbackrest is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatbackrest", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnSeatbackrest mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestRightArea(i2);
        }
    }

    public void turnSeatHeight(int i, int i2) {
        Log.i(TAG, "turnSeatHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatHeight", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnSeatHeight mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightRightArea(i2);
        }
    }

    public void turnSeatHorization(int i, int i2) {
        Log.i(TAG, "turnSeatHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatHorization", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnSeatHorization mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRightArea(i2);
        }
    }

    public void turnRearviewMirror(int i) {
        Log.i(TAG, "turnRearviewMirror is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnRearviewMirror", Integer.valueOf(i));
            Log.v(TAG, "turnRearviewMirror mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getSettingAdapterManager().turnRearviewMirror(i);
        }
    }

    public void turnSteeringWheel(int i) {
        Log.i(TAG, "turnSteeringWheel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSteeringWheel", Integer.valueOf(i));
            Log.v(TAG, "turnSteeringWheel mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getSettingAdapterManager().turnSteeringWheel(i);
        }
    }

    public int getRefrigeratorState() {
        Log.i(TAG, "getRefrigeratorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getRefrigeratorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorState();
    }

    public int getTrayInitState() {
        Log.i(TAG, "getTrayInitState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getTrayInitState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrayInitState();
    }

    public int setSafeWarnState(int i) {
        Log.i(TAG, "setSafeWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSafeWarnState", Integer.valueOf(i));
            Log.v(TAG, "setSafeWarnState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeWarnState(i);
    }

    public int setMaintainRemindState(int i) {
        Log.i(TAG, "setMaintainRemindState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMaintainRemindState", Integer.valueOf(i));
            Log.v(TAG, "setMaintainRemindState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMaintainRemindState(i);
    }

    @Deprecated
    public int resetHudState() {
        Log.i(TAG, "resetHudState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetHudState", new Object[0]);
            Log.v(TAG, "resetHudState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHudState();
    }

    public int getSteerAssisSupported() {
        Log.i(TAG, "getSteerAssisSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSteerAssisSupported mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteerAssisSupported();
    }

    public int setBackHomeLightDelay(int i) {
        Log.i(TAG, "setBackHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBackHomeLightDelay(i);
    }

    public int getBackHomeLightDelay() {
        Log.i(TAG, "getBackHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getBackHomeLightDelay();
    }

    public int getLeftHomeLightDelayValue() {
        Log.i(TAG, "getLeftHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLeftHomeLightDelayValue();
    }

    public int setLeftHomeLightDelayValue(int i) {
        Log.i(TAG, "setLeftHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETLEFTHOMELIGHTDELAYVALUE, Integer.valueOf(i));
            Log.v(TAG, "getBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeftHomeLightDelayValue(i);
    }

    public void turnSeatbackrestPercent(int i, int i2) {
        Log.i(TAG, "turnSeatbackrestPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatbackrestPercent", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnSeatbackrestPercent mForTestValue is 0 execute return");
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPercentDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPercentPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPercentLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPercentRightArea(i2);
        }
    }

    public void turnSeatHorizationPercent(int i, int i2) {
        Log.i(TAG, "turnSeatHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatHorizationPercent", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnSeatHorizationPercent mForTestValue is 0 execute return");
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPercentDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPercentPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPercentLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPercentRightArea(i2);
        }
    }

    public void turnSeatHeightPercent(int i, int i2) {
        Log.i(TAG, "turnSeatHeightPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatHeightPercent", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnSeatHeightPercent mForTestValue is 0 execute return");
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return;
        }
        if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPercentDriver(i2);
            return;
        }
        if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPercentPassenger(i2);
        } else if (i == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPercentLeftArea(i2);
        } else if (i == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPercentRightArea(i2);
        }
    }

    public int setStartOrPowerInd(int i) {
        Log.i(TAG, "setStartOrPowerInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setStartOrPowerInd", Integer.valueOf(i));
            Log.v(TAG, "setStartOrPowerInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setStartOrPowerInd(i);
    }

    public int getStartOrPowerInd() {
        Log.i(TAG, "getStartOrPowerInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getStartOrPowerInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getStartOrPowerInd();
    }

    public int setParkBrakeInd(int i) {
        Log.i(TAG, "setParkBrakeInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setParkBrakeInd", Integer.valueOf(i));
            Log.v(TAG, "setParkBrakeInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setParkBrakeInd(i);
    }

    public int getParkBrakeInd() {
        Log.i(TAG, "getParkBrakeInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getParkBrakeInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getParkBrakeInd();
    }

    public int setDriveDoorCloseInd(int i) {
        Log.i(TAG, "setDriveDoorCloseInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setDriveDoorCloseInd", Integer.valueOf(i));
            Log.v(TAG, "setDriveDoorCloseInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDriveDoorCloseInd(i);
    }

    public int getDriveDoorCloseInd() {
        Log.i(TAG, "getDriveDoorCloseInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getDriveDoorCloseInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDriveDoorCloseInd();
    }

    public int setSafeBeltInd(int i) {
        Log.i(TAG, "setSafeBeltInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSafeBeltInd", Integer.valueOf(i));
            Log.v(TAG, "setSafeBeltInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltInd(i);
    }

    public int getSafeBeltInd() {
        Log.i(TAG, "getSafeBeltInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSafeBeltInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSafeBeltInd();
    }

    public int setLeaveCarPInd(int i) {
        Log.i(TAG, "setLeaveCarPInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLeaveCarPInd", Integer.valueOf(i));
            Log.v(TAG, "setLeaveCarPInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeaveCarPInd(i);
    }

    public int getLeaveCarPInd() {
        Log.i(TAG, "getLeaveCarPInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getLeaveCarPInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLeaveCarPInd();
    }

    public int setKeyPowerLowInd(int i) {
        Log.i(TAG, "setKeyPowerLowInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setKeyPowerLowInd", Integer.valueOf(i));
            Log.v(TAG, "setKeyPowerLowInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setKeyPowerLowInd(i);
    }

    public int getKeyPowerLowInd() {
        Log.i(TAG, "getKeyPowerLowInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getKeyPowerLowInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getKeyPowerLowInd();
    }

    public int setMissKeyInd(int i) {
        Log.i(TAG, "setMissKeyInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMissKeyInd", Integer.valueOf(i));
            Log.v(TAG, "setMissKeyInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMissKeyInd(i);
    }

    public int getMissKeyInd() {
        Log.i(TAG, "getMissKeyInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getMissKeyInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMissKeyInd();
    }

    public int setNightDRLightOnInd(int i) {
        Log.i(TAG, "setNightDRLightOnInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setNightDRLightOnInd", Integer.valueOf(i));
            Log.v(TAG, "setNightDRLightOnInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setNightDRLightOnInd(i);
    }

    public int getNightDRLightOnInd() {
        Log.i(TAG, "getNightDRLightOnInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getNightDRLightOnInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getNightDRLightOnInd();
    }

    public int setLowOilInd(int i) {
        Log.i(TAG, "setLowOilInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLowOilInd", Integer.valueOf(i));
            Log.v(TAG, "setLowOilInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLowOilInd(i);
    }

    public int getLowOilInd() {
        Log.i(TAG, "getLowOilInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getLowOilInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLowOilInd();
    }

    public int setLowBatteryInd(int i) {
        Log.i(TAG, "setLowBatteryInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLowBatteryInd", Integer.valueOf(i));
            Log.v(TAG, "setLowBatteryInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLowBatteryInd(i);
    }

    public int getLowBatteryInd() {
        Log.i(TAG, "getLowBatteryInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getLowBatteryInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLowBatteryInd();
    }

    public int setStopRemoteCtrlDriveInd(int i) {
        Log.i(TAG, "setStopRemoteCtrlDriveInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setStopRemoteCtrlDriveInd", Integer.valueOf(i));
            Log.v(TAG, "setStopRemoteCtrlDriveInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setStopRemoteCtrlDriveInd(i);
    }

    public int getStopRemoteCtrlDriveInd() {
        Log.i(TAG, "getStopRemoteCtrlDriveInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getStopRemoteCtrlDriveInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getStopRemoteCtrlDriveInd();
    }

    public int getAutomaticParking() {
        Log.i(TAG, "getAutomaticParking is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAutomaticParking mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAutomaticParking();
    }

    public int setAutomaticParking(int i) {
        Log.i(TAG, "setAutomaticParking is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setAutomaticParking", Integer.valueOf(i));
            Log.v(TAG, "setAutomaticParking mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutomaticParking(i);
    }

    public int getIALColor(int i) {
        Log.i(TAG, "getIALColor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIALColor(i);
        }
        if (i == 1 || i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalColorFront();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalColorStarrySky();
        }
        if (i == 5) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalColorStarRing();
        }
        return 0;
    }

    public int setIALColor(int i, int i2, int i3) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIALColor", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            Log.v(TAG, "setIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIALColor(i, i2, i3);
    }

    public int getIALBrightness(int i) {
        Log.i(TAG, "getIALBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalBrightnessBack();
        }
        if (i == 1 || i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalBrightnessFront();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalBrightnessStarrySky();
        }
        if (i == 5) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalBrightnessStarRing();
        }
        return 0;
    }

    public int setIALBrightness(int i, int i2, int i3) {
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIALBrightness", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            Log.v(TAG, "setIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIALBrightness(i, i2, i3);
    }

    public int getIALArea() {
        Log.i(TAG, "getIALArea is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getIALArea mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getIALArea();
    }

    public int setIALArea(int i, int i2) {
        Log.i(TAG, "setIALArea is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIALArea", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setIALArea mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIALArea(i, i2);
    }

    public int setIALInfo(int i, int i2, int i3, int i4) {
        Log.i(TAG, "setIALInfo is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIALInfo", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            Log.v(TAG, "setIALInfo mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIALInfo(i, i2, i3, i4);
    }

    public int getSeatHeatingState(int i) {
        Log.i(TAG, "getSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSeatHeatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStatePassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateLeftRear();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateRightRear();
        }
        return 0;
    }

    public int setSeatHeatingState(int i, int i2) {
        Log.i(TAG, "setSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSeatHeatingState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setSeatHeatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i2 == 3) {
            i2 = 1;
        } else if (i2 == 1) {
            i2 = 3;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatHeatingStateDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatHeatingStatePassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatHeatingStateLeftRear(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatHeatingStateRightRear(i2);
        }
        return 0;
    }

    public int getSeatVentilatingState(int i) {
        Log.i(TAG, "getSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSeatVentilatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingState();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingStatePassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingStateBackLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingStateBackRight();
        }
        return 0;
    }

    public int setSeatVentilatingState(int i, int i2) {
        Log.i(TAG, "setSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSeatVentilatingState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setSeatVentilatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i2 == 3) {
            i2 = 1;
        } else if (i2 == 1) {
            i2 = 3;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatVentilatingStateDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatVentilatingStatePassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatVentilatingStateLeftRear(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatVentilatingStateRightRear(i2);
        }
        return 0;
    }

    public int getLanternLanguageMode() {
        Log.i(TAG, "getLanternLanguageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getLanternLanguageMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLanternLanguageMode();
    }

    public int setLanternLanguageMode(int i) {
        Log.i(TAG, "setLanternLanguageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLanternLanguageMode", Integer.valueOf(i));
            Log.v(TAG, "setLanternLanguageMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLanternLanguageMode(i);
    }

    public int getINSTheme() {
        Log.i(TAG, "getINSTheme is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getINSTheme mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getINSTheme();
    }

    public int setINSTheme(int i) {
        Log.i(TAG, "setINSTheme is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setINSTheme", Integer.valueOf(i));
            Log.v(TAG, "setINSTheme mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setINSTheme(i);
    }

    public int resetSystemSetting(int i) {
        Log.i(TAG, "restoreSettingItem is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("factoryResetAll", Integer.valueOf(i));
            Log.v(TAG, "restoreSettingItem mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().restoreSettingItem(i);
    }

    public int factoryResetAll(int i) {
        Log.i(TAG, "factoryResetAll is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("factoryResetAll", Integer.valueOf(i));
            Log.v(TAG, "factoryResetAll mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().factoryResetAll(i);
    }

    public int setHeadlampLevel(int i) {
        Log.i(TAG, "setHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setHeadlampLevel", Integer.valueOf(i));
            Log.v(TAG, "setHeadlampLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHeadlampLevel(i);
    }

    public int getLeftHeadlampLevel() {
        Log.i(TAG, "getLeftHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getLeftHeadlampLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLeftHeadlampLevel();
    }

    public int getRightHeadlampLevel() {
        Log.i(TAG, "getRightHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getRightHeadlampLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRightHeadlampLevel();
    }

    public int getHeadlampLevel() {
        Log.i(TAG, "getHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getHeadlampLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHeadlampLevel();
    }

    public int setSmartWelcomeLightState(int i) {
        Log.i(TAG, "setSmartWelcomeLightState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSmartWelcomeLightState", Integer.valueOf(i));
            Log.v(TAG, "setSmartWelcomeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSmartWelcomeLightState(i);
    }

    public int getSmartWelcomeLightState() {
        Log.i(TAG, "getSmartWelcomeLightState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSmartWelcomeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSmartWelcomeLightState();
    }

    public int sendOffReminder(int i) {
        Log.i(TAG, "sendOffReminder is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("sendOffReminder", Integer.valueOf(i));
            Log.v(TAG, "sendOffReminder mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().sendOffReminder(i);
    }

    public int getOffReminder() {
        Log.i(TAG, "getOffReminder is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getOffReminder mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getOffReminder();
    }

    public int getRearAcPanelAutoLockTime() {
        Log.i(TAG, "getRearAcPanelAutoLockTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearAcPanelAutoLockTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearAcPanelAutoLockTime();
    }

    public int setRearAcPanelAutoLockTime(int i) {
        Log.i(TAG, "setRearAcPanelAutoLockTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRearAcPanelAutoLockTime", Integer.valueOf(i));
            Log.v(TAG, "setRearAcPanelAutoLockTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearAcPanelAutoLockTime(i);
    }

    public int getExhibitionMode() {
        Log.i(TAG, "getExhibitionMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getExhibitionMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getExhibitionMode();
    }

    public int getExhibitionStartEnginePrompt() {
        Log.i(TAG, "getExhibitionStartEnginePrompt is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getExhibitionStartEnginePrompt();
    }

    public int setExhibitionStartEnginePermit(int i) {
        Log.i(TAG, "setExhibitionStartEnginePermit is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setExhibitionStartEnginePermit", Integer.valueOf(i));
            Log.v(TAG, "setExhibitionStartEnginePermit mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setExhibitionStartEnginePermit(i);
    }

    public int getSteeringWheelHeatingState() {
        Log.i(TAG, "getSteeringWheelHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSteeringWheelHeatingState mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatingState();
    }

    public int setSteeringWheelHeatingState(int i) {
        Log.i(TAG, "setSteeringWheelHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSteeringWheelHeatingState", Integer.valueOf(i));
            Log.v(TAG, "setSteeringWheelHeatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingState(i);
    }

    public int getInsideLightDoorState() {
        Log.i(TAG, "getInsideLightDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getInsideLightDoorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getInsideLightDoorState();
    }

    public int setInsideLightDoorState(int i) {
        Log.i(TAG, "setInsideLightDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setInsideLightDoorState", Integer.valueOf(i));
            Log.v(TAG, "setInsideLightDoorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setInsideLightDoorState(i);
    }

    public int setPadRotation(int i) {
        Log.i(TAG, "setPadRotation is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setPadRotation", Integer.valueOf(i));
            Log.v(TAG, "setPadRotation mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPadRotation(i);
    }

    public int resetSetting(int[] iArr, int[] iArr2) {
        Log.i(TAG, "resetSetting is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetSetting", iArr, iArr2);
            Log.v(TAG, "resetSetting mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetSetting(iArr, iArr2);
    }

    public int setMassageMode(int i, int i2) {
        Log.i(TAG, "setMassageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMassageMode", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setMassageMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageModeDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageModePassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageModeLeftRear(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageModeRightRear(i2);
        }
        return 0;
    }

    public int getMassageMode(int i) {
        Log.i(TAG, "getMassageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getMassageMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageModeDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageModePassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageModeLeftRear();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageModeRightRear();
        }
        return 0;
    }

    public int getMassageLevel(int i) {
        Log.i(TAG, "getMassageLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getMassageLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageLevelDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageLevelPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageLevelLeftRear();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageLevelRightRear();
        }
        return 0;
    }

    public int setMassageLevel(int i, int i2) {
        Log.i(TAG, "setMassageLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMassageLevel", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setMassageLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageLevelDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageLevelPassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageLevelLeftRear(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageLevelRightRear(i2);
        }
        return 0;
    }

    public int saveSeatParamsAll(int i, int i2) {
        Log.i(TAG, "saveSeatParamsAll is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("saveSeatParamsAll", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "saveSeatParamsAll mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i2 == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().saveSeatParamsAllDriver(i);
        }
        if (i2 == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().saveSeatParamsAllPassenger(i);
        }
        if (i2 == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().saveSeatParamsAllLeftRear(i);
        }
        if (i2 == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().saveSeatParamsAllRightRear(i);
        }
        return 0;
    }

    public int getKeypadToneState() {
        Log.v(TAG, "getKeypadToneState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getKeypadToneState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getKeypadToneState();
    }

    public int setKeypadToneState(int i) {
        Log.v(TAG, "setKeypadToneState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setKeypadToneState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setKeypadToneState(i);
    }

    public void setKeypadToneStatechanged(int i) {
        Log.v(TAG, "setKeypadToneStatechanged is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setKeypadToneStatechanged mForTestValue is" + this.mForTest + " execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getSettingAdapterManager().setKeypadToneStatechanged(i);
        }
    }

    public int getComSpeedUpState() {
        Log.v(TAG, "getComSpeedUpState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getComSpeedUpState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getComSpeedUpState();
    }

    public int setComSpeedUpState(int i) {
        Log.v(TAG, "setComSpeedUpState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setComSpeedUpState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setComSpeedUpState(i);
    }

    public int getComSpeedDownState() {
        Log.v(TAG, "getComSpeedDownState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getComSpeedDownState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getComSpeedDownState();
    }

    public int setComSpeedDownState(int i) {
        Log.v(TAG, "setComSpeedDownState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setComSpeedDownState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setComSpeedDownState(i);
    }

    public int getHSpeedAutoCloseState() {
        Log.v(TAG, "getHSpeedAutoCloseState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getHSpeedAutoCloseState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHSpeedAutoCloseState();
    }

    public int setHSpeedAutoCloseState(int i) {
        Log.v(TAG, "setHSpeedAutoCloseState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setHSpeedAutoCloseState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHSpeedAutoCloseState(i);
    }

    public int getRuningCloseDoorState() {
        Log.v(TAG, "getRuningCloseDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getRuningCloseDoorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRuningCloseDoorState();
    }

    public int setRuningCloseDoorState(int i) {
        Log.v(TAG, "setRuningCloseDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setRuningCloseDoorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRuningCloseDoorState(i);
    }

    public int getRearViewMirrorFlipSet() {
        Log.v(TAG, "getRearViewMirrorFlipSet is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearViewMirrorFlipSet mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearViewMirrorFlipSet();
    }

    public int voiceCtlBackDoor(int i) {
        Log.v(TAG, "voiceCtlBackDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "voiceCtlBackDoor mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().voiceCtlBackDoor(i);
    }

    public int getAidedDrivingInd() {
        Log.v(TAG, "getAidedDrivingInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAidedDrivingInd mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAidedDrivingInd();
    }

    public int setAidedDrivingInd(int i) {
        Log.v(TAG, "setAidedDrivingInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setAidedDrivingInd mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAidedDrivingInd(i);
    }

    public int getHeadLampConfig() {
        Log.v(TAG, "getHeadLampConfig is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getHeadLampConfig mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHeadLampConfig();
    }

    public void turnHeadrestHorizationPercent(int i, int i2) {
        Log.v(TAG, "turnHeadrestHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnHeadrestHorizationPercent", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnHeadrestHorizationPercent mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestHorizationPercentDriver(i2);
        }
    }

    public void turnHeadrestHeightPercent(int i, int i2) {
        Log.v(TAG, "turnHeadrestHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnHeadrestHeightPercent", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnHeadrestHeightPercent mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestHeightPercentDriver(i2);
        }
    }

    public void turnCaliperSplintPercent(int i, int i2) {
        Log.v(TAG, "turnCaliperSplintPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnCaliperSplintPercent", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnCaliperSplintPercent mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnCaliperSplintPercentDriver(i2);
        } else if (i == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnCaliperSplintPercentPassenger(i2);
        }
    }

    public void turnCushionPercent(int i, int i2) {
        Log.v(TAG, "turnCushionPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnCushionPercent", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "turnCushionPercent mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnCushionPercentDriver(i2);
        }
    }

    public int setAadsSwitchSate(int i) {
        Log.v(TAG, "setAadsSwitchSate is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setAadsSwitchSate", Integer.valueOf(i));
            Log.v(TAG, "setAadsSwitchSate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAfsSwitchSate(i);
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 873) {
                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftMemorySwitchConfigurationByECU();
                        sparseArray.put(873, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 874) {
                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightMemorySwitchConfigurationByECU();
                        sparseArray.put(874, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else {
                        switch (i) {
                            case 384:
                                bYDAutoEventValue.intValue = getSteeringWheelHeatingGear();
                                sparseArray.put(384, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 386:
                                bYDAutoEventValue.intValue = getSteeringWheelHeatingAutoState();
                                sparseArray.put(386, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 525:
                                bYDAutoEventValue.intValue = getSteeringWheelHeatingState();
                                sparseArray.put(525, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 539:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
                                sparseArray.put(539, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 541:
                                bYDAutoEventValue.intValue = getAutoExternalRearMirrorFollowUpSwitchSupport();
                                sparseArray.put(541, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 565:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCanByECU();
                                sparseArray.put(565, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 566:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByGCtrl();
                                sparseArray.put(566, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 567:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByGCtrl();
                                sparseArray.put(567, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 568:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
                                sparseArray.put(568, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 569:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByGCtrl();
                                sparseArray.put(569, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 570:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
                                sparseArray.put(570, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 571:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftBackrestAdjustmentConfigurationByECU();
                                sparseArray.put(571, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 572:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
                                sparseArray.put(572, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 573:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftLevelAdjustmentConfigurationByECU();
                                sparseArray.put(573, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 574:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftBidetAdjustmentConfigurationByECU();
                                sparseArray.put(574, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 575:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
                                sparseArray.put(575, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 576:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
                                sparseArray.put(576, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 577:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeightAdjustmentConfigurationByECU();
                                sparseArray.put(577, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 578:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
                                sparseArray.put(578, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 579:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftLegSupportAdjustmentConfigurationByECU();
                                sparseArray.put(579, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 580:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMemoryConfiguration();
                                sparseArray.put(580, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 581:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfigurationByECU();
                                sparseArray.put(581, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 679:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getFrontWiperSpeedValue();
                                sparseArray.put(679, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 711:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
                                sparseArray.put(711, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 751:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getRearWiperSpeedValue();
                                sparseArray.put(751, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 760:
                                bYDAutoEventValue.intValue = getPassangerSaveOrRecoverState();
                                sparseArray.put(760, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 877:
                                bYDAutoEventValue.intValue = getACAutoAir();
                                sparseArray.put(877, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 888:
                                bYDAutoEventValue.intValue = getCurrentStatusOfDimmingSunroof();
                                sparseArray.put(888, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 902:
                                bYDAutoEventValue.intValue = getAmbientFeedBack();
                                sparseArray.put(902, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 921:
                                bYDAutoEventValue.intValue = getCanopyAutomaticDimming();
                                sparseArray.put(921, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 953:
                                bYDAutoEventValue.intValue = getDoorHeightSupport();
                                sparseArray.put(953, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 987:
                                bYDAutoEventValue.intValue = getElectroniChildLockConfig();
                                sparseArray.put(987, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1040:
                                bYDAutoEventValue.intValue = getSeatHeatingState(1);
                                sparseArray.put(1040, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1044:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingState();
                                sparseArray.put(1044, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1146:
                                bYDAutoEventValue.intValue = getAutoExternalRearMirrorFollowUpSwitchSupport();
                                sparseArray.put(1146, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1154:
                                bYDAutoEventValue.intValue = getAmbientAreaSupport();
                                sparseArray.put(1154, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1155:
                                bYDAutoEventValue.intValue = getAmbientAreaSupport();
                                sparseArray.put(1155, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1156:
                                bYDAutoEventValue.intValue = getIALBrightness(2);
                                sparseArray.put(1156, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1157:
                                bYDAutoEventValue.intValue = getIALColor(2);
                                sparseArray.put(1157, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1158:
                                bYDAutoEventValue.intValue = getAmbientLightSupport();
                                sparseArray.put(1158, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1159:
                                bYDAutoEventValue.intValue = getAmbientLightColorSupport();
                                sparseArray.put(1159, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1160:
                                bYDAutoEventValue.intValue = getIALBrightness(1);
                                sparseArray.put(1160, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1161:
                                bYDAutoEventValue.intValue = getIALColor(1);
                                sparseArray.put(1161, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1171:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getIALArea();
                                sparseArray.put(1171, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1220:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getMemorySetOperationFlag();
                                sparseArray.put(1220, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1255:
                                bYDAutoEventValue.intValue = getAmbientMusicModeState(1);
                                sparseArray.put(1255, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1268:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
                                sparseArray.put(1268, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1269:
                                bYDAutoEventValue.intValue = getSeatHeatingState(2);
                                sparseArray.put(1269, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1273:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingStatePassenger();
                                sparseArray.put(1273, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1283:
                                bYDAutoEventValue.intValue = getSeatHeatingState(3);
                                if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                    sparseArray.put(1283, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = getSeatHeatingState2(3);
                                    if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                        sparseArray.put(1283, Integer.valueOf(bYDAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1283, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1285:
                                bYDAutoEventValue.intValue = getSupportSeatMassage(3);
                                if (bYDAutoEventValue.intValue == 1) {
                                    sparseArray.put(1285, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
                                    if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 0) {
                                        sparseArray.put(1285, Integer.valueOf(bYDAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1285, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1286:
                                bYDAutoEventValue.intValue = getMassageLevel(3);
                                if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                    sparseArray.put(1286, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = getEcuMassageLevel(3);
                                    if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                        sparseArray.put(1286, Integer.valueOf(bYDAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1286, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1288:
                                bYDAutoEventValue.intValue = getMassageMode(3);
                                if (bYDAutoEventValue.intValue >= 1 && bYDAutoEventValue.intValue <= 7) {
                                    sparseArray.put(1288, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = getEcuMassageMode(3);
                                    if (bYDAutoEventValue.intValue < 1 || bYDAutoEventValue.intValue <= 7) {
                                        sparseArray.put(1288, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1290:
                                bYDAutoEventValue.intValue = getSeatVentilatingState(3);
                                if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                    sparseArray.put(1290, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = getSeatVentilatingState2(3);
                                    if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                        sparseArray.put(1290, Integer.valueOf(bYDAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1290, 0);
                                    }
                                }
                                break;
                            case 1294:
                                bYDAutoEventValue.intValue = getSeatHeatingState(4);
                                if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                    sparseArray.put(1294, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = getSeatHeatingState2(4);
                                    if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                        sparseArray.put(1294, Integer.valueOf(bYDAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1294, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1296:
                                bYDAutoEventValue.intValue = getSupportSeatMassage(4);
                                if (bYDAutoEventValue.intValue == 1) {
                                    sparseArray.put(1296, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
                                    if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 0) {
                                        sparseArray.put(1296, Integer.valueOf(bYDAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1296, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1297:
                                bYDAutoEventValue.intValue = getMassageLevel(4);
                                if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                    sparseArray.put(1297, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = getEcuMassageLevel(4);
                                    if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                        sparseArray.put(1297, Integer.valueOf(bYDAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1297, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1299:
                                bYDAutoEventValue.intValue = getMassageMode(4);
                                if (bYDAutoEventValue.intValue >= 1 && bYDAutoEventValue.intValue <= 7) {
                                    sparseArray.put(1299, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = getEcuMassageMode(4);
                                    if (bYDAutoEventValue.intValue < 1 || bYDAutoEventValue.intValue <= 7) {
                                        sparseArray.put(1299, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1301:
                                bYDAutoEventValue.intValue = getSeatVentilatingState(4);
                                if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                    sparseArray.put(1301, Integer.valueOf(bYDAutoEventValue.intValue));
                                } else {
                                    bYDAutoEventValue.intValue = getSeatVentilatingState2(4);
                                    if (bYDAutoEventValue.intValue == 1 || bYDAutoEventValue.intValue == 2 || bYDAutoEventValue.intValue == 3) {
                                        sparseArray.put(1301, Integer.valueOf(bYDAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1301, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1305:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getMainSaveOrRecoveFailCode();
                                sparseArray.put(1305, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1396:
                                bYDAutoEventValue.intValue = getScreenAngleValue();
                                sparseArray.put(1396, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1410:
                                bYDAutoEventValue.intValue = getSafeBeltWarnState();
                                sparseArray.put(1410, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1414:
                                bYDAutoEventValue.intValue = getPassengerState(2);
                                sparseArray.put(1414, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1415:
                                bYDAutoEventValue.intValue = getPassengerState(3);
                                sparseArray.put(1415, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1416:
                                bYDAutoEventValue.intValue = getPassengerState(5);
                                sparseArray.put(1416, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1417:
                                bYDAutoEventValue.intValue = getPassengerState(4);
                                sparseArray.put(1417, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1698:
                                bYDAutoEventValue.intValue = getComSpeedUpState();
                                sparseArray.put(1698, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1703:
                                bYDAutoEventValue.intValue = getComSpeedDownState();
                                sparseArray.put(1703, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1707:
                                bYDAutoEventValue.intValue = getRainAutoLockWindowState();
                                sparseArray.put(1707, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1711:
                                bYDAutoEventValue.intValue = getTrayState();
                                sparseArray.put(1711, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1723:
                                bYDAutoEventValue.intValue = getDisinfState();
                                sparseArray.put(1723, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1725:
                                bYDAutoEventValue.intValue = getRefrigeratorDoorState();
                                sparseArray.put(1725, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1726:
                                bYDAutoEventValue.intValue = getRefrigeratorState();
                                sparseArray.put(1726, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1727:
                                bYDAutoEventValue.intValue = getRemenberPowerOffState();
                                sparseArray.put(1727, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1729:
                                bYDAutoEventValue.intValue = getRefrigeratorMode();
                                sparseArray.put(1729, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1732:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
                                sparseArray.put(1732, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 1739:
                                bYDAutoEventValue.intValue = getHSpeedAutoCloseState();
                                sparseArray.put(1739, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2086:
                                bYDAutoEventValue.intValue = getTrayActionState();
                                sparseArray.put(2086, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2088:
                                bYDAutoEventValue.intValue = getTrayInitState();
                                sparseArray.put(2088, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2098:
                                bYDAutoEventValue.floatValue = getSteeringWheelY();
                                sparseArray.put(2098, Float.valueOf(bYDAutoEventValue.floatValue));
                                break;
                            case 2100:
                                bYDAutoEventValue.floatValue = getSteeringWheelZ();
                                sparseArray.put(2100, Float.valueOf(bYDAutoEventValue.floatValue));
                                break;
                            case 2120:
                                bYDAutoEventValue.floatValue = getRearviewMirrorX(1);
                                sparseArray.put(2120, Float.valueOf(bYDAutoEventValue.floatValue));
                                break;
                            case 2122:
                                bYDAutoEventValue.floatValue = getRearviewMirrorY(1);
                                sparseArray.put(2122, Float.valueOf(bYDAutoEventValue.floatValue));
                                break;
                            case 2130:
                                bYDAutoEventValue.floatValue = getRearviewMirrorX(2);
                                sparseArray.put(2130, Float.valueOf(bYDAutoEventValue.floatValue));
                                break;
                            case 2132:
                                bYDAutoEventValue.floatValue = getRearviewMirrorY(2);
                                sparseArray.put(2132, Float.valueOf(bYDAutoEventValue.floatValue));
                                break;
                            case 2134:
                                bYDAutoEventValue.intValue = getMainSaveOrRecoverState();
                                sparseArray.put(2134, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2654:
                                bYDAutoEventValue.intValue = getIntelligentLockState();
                                sparseArray.put(2654, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2662:
                                bYDAutoEventValue.intValue = getIntelligentUnlockState();
                                sparseArray.put(2662, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2805:
                                bYDAutoEventValue.intValue = getAmbientMulticolorMode(0);
                                sparseArray.put(2805, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2808:
                                bYDAutoEventValue.intValue = getAmbientMulticolorState(0);
                                sparseArray.put(2808, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2829:
                                bYDAutoEventValue.intValue = getAmbientMusicMode(1);
                                sparseArray.put(2829, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2844:
                                bYDAutoEventValue.intValue = getIALBrightness(5);
                                sparseArray.put(2844, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 2845:
                                bYDAutoEventValue.intValue = getIALColor(5);
                                sparseArray.put(2845, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3158:
                                bYDAutoEventValue.intValue = getBrakePedalMode();
                                sparseArray.put(3158, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3161:
                                bYDAutoEventValue.intValue = getComfortableParkingState();
                                sparseArray.put(3161, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3371:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingMode(1);
                                sparseArray.put(3371, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3372:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingLevel(1);
                                sparseArray.put(3372, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3374:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingState(1);
                                sparseArray.put(3374, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3376:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingMode(1);
                                sparseArray.put(3376, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3378:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingLevel(1);
                                sparseArray.put(3378, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3380:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingState(1);
                                sparseArray.put(3380, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3425:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingMode(2);
                                sparseArray.put(3425, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3426:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingLevel(2);
                                sparseArray.put(3426, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3428:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingState(2);
                                sparseArray.put(3428, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3430:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingMode(2);
                                sparseArray.put(3430, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3432:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingLevel(2);
                                sparseArray.put(3432, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3434:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingState(2);
                                sparseArray.put(3434, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3466:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingMode(3);
                                sparseArray.put(3466, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3467:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingLevel(3);
                                sparseArray.put(3467, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3469:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingState(3);
                                sparseArray.put(3469, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3473:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingLevel(3);
                                sparseArray.put(3473, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3475:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingState(3);
                                sparseArray.put(3475, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3489:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingMode(4);
                                sparseArray.put(3489, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3490:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingLevel(4);
                                sparseArray.put(3490, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3492:
                                bYDAutoEventValue.intValue = get5LevelSeatHeatingState(4);
                                sparseArray.put(3492, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3496:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingLevel(4);
                                sparseArray.put(3496, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3498:
                                bYDAutoEventValue.intValue = get5LevelSeatVentilatingState(4);
                                sparseArray.put(3498, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3522:
                                bYDAutoEventValue.intValue = getIntelligentAcAreaState();
                                sparseArray.put(3522, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3557:
                                bYDAutoEventValue.intValue = getAcWarmMode();
                                sparseArray.put(3557, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3586:
                                bYDAutoEventValue.intValue = getEngineSoundType();
                                sparseArray.put(3586, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4581:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
                                sparseArray.put(4581, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4582:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
                                sparseArray.put(4582, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4583:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
                                sparseArray.put(4583, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4584:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
                                sparseArray.put(4584, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4625:
                                bYDAutoEventValue.intValue = getDimmingCanopyLowCode();
                                sparseArray.put(4625, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4626:
                                bYDAutoEventValue.intValue = getRearLeftMassageSwConfiguration();
                                sparseArray.put(4626, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4627:
                                bYDAutoEventValue.intValue = getRearRightMassageSwConfiguration();
                                sparseArray.put(4627, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4628:
                                bYDAutoEventValue.intValue = getElectricTailConfiguration();
                                sparseArray.put(4628, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4629:
                                bYDAutoEventValue.intValue = getTailInitializationState();
                                sparseArray.put(4629, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4630:
                                bYDAutoEventValue.intValue = getCurrentStateOfTheTailState();
                                sparseArray.put(4630, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4631:
                                bYDAutoEventValue.intValue = getTailPositionPercentage();
                                sparseArray.put(4631, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4632:
                                bYDAutoEventValue.intValue = getTailMotorThermalProtectionState();
                                sparseArray.put(4632, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4633:
                                bYDAutoEventValue.intValue = getTailPositionFeedback();
                                sparseArray.put(4633, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4634:
                                bYDAutoEventValue.intValue = getAlarmPromptState();
                                sparseArray.put(4634, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4635:
                                bYDAutoEventValue.intValue = getCurrentModeOfTailState();
                                sparseArray.put(4635, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4636:
                                bYDAutoEventValue.intValue = getElectricTailMaintenanceFuntatusState();
                                sparseArray.put(4636, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_LEFT_ELECTRIC_PEDAL_POSITION /* 4684 */:
                                bYDAutoEventValue.intValue = getLeftElectricPedal();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_LEFT_ELECTRIC_PEDAL_POSITION, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_RIGHT_ELECTRIC_PEDAL_POSITION /* 4685 */:
                                bYDAutoEventValue.intValue = getRightElectricPedal();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_RIGHT_ELECTRIC_PEDAL_POSITION, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORKING_STATUS /* 4686 */:
                                bYDAutoEventValue.intValue = getRefrigeratorWorkMode();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORKING_STATUS, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POWEROFF_MEMORY /* 4687 */:
                                bYDAutoEventValue.intValue = getRemberPowerOffState();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POWEROFF_MEMORY, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS /* 4688 */:
                                bYDAutoEventValue.intValue = getSterilizationState();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POPUP_REMIND /* 4689 */:
                                bYDAutoEventValue.intValue = getVoiceTipsMessage();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POPUP_REMIND, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_REFRIGERATION_TEMP_RANGE_FLAG /* 4699 */:
                                bYDAutoEventValue.intValue = getFridgeTemperatureRegulation();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_REFRIGERATION_TEMP_RANGE_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TELESCOPIC_CONTROL_FLAG /* 4700 */:
                                bYDAutoEventValue.intValue = getFridgeExpansionAndContractionFunction();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TELESCOPIC_CONTROL_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TYPE /* 4701 */:
                                bYDAutoEventValue.intValue = getCarFridgeType();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TYPE, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_PWOEROFF_SWITCH /* 4702 */:
                                bYDAutoEventValue.intValue = getDelayPowerOffStatus();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_PWOEROFF_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWEROFF_TIME /* 4703 */:
                                bYDAutoEventValue.intValue = getDelayPowerOffTime();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWEROFF_TIME, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_COFING_FLAG /* 4704 */:
                                bYDAutoEventValue.intValue = getFridgeWorkingMode();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_COFING_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_FLAG /* 4705 */:
                                bYDAutoEventValue.intValue = getFridgeChildLockFlag();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_STATE /* 4706 */:
                                bYDAutoEventValue.intValue = getFridgeChildLock();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_STATE, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_COLD /* 4707 */:
                                bYDAutoEventValue.intValue = getFridgeWorkColdTemperature();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_COLD, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_HEAT /* 4708 */:
                                bYDAutoEventValue.intValue = getFridgeWorkHeatTemperature();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_HEAT, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_FRESH /* 4709 */:
                                bYDAutoEventValue.intValue = getFridgeWorkSaveTemperature();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_FRESH, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_INTERFACE_FLAG /* 4710 */:
                                bYDAutoEventValue.intValue = getFridgeScreenFlag();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_INTERFACE_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE /* 4711 */:
                                bYDAutoEventValue.intValue = getDelayPowerDownTime();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE_CONFIG /* 4712 */:
                                bYDAutoEventValue.intValue = getFridgeDelayedPowerOutage();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_BOUNCE_SIGNAL /* 4713 */:
                                bYDAutoEventValue.intValue = getScreenCallbackMessage();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_BOUNCE_SIGNAL, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.ENGINE_SLIDING_RAIL_MOTOR_PERCENTAGE /* 4714 */:
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getSlideRailMotorElecPercent();
                                sparseArray.put(BYDAutoFeatureIds.ENGINE_SLIDING_RAIL_MOTOR_PERCENTAGE, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SET_MASSAGE_PHYSICAL_BUTTONS_CONFIG /* 4730 */:
                                int physicalkeyConfig = getPhysicalkeyConfig();
                                int physicalkeySecuConfig = getPhysicalkeySecuConfig();
                                int physicalkeySecupConfig = getPhysicalkeySecupConfig();
                                int physicalkeySecurlConfig = getPhysicalkeySecurlConfig();
                                int physicalkeySecurrConfig = getPhysicalkeySecurrConfig();
                                if (physicalkeyConfig == 2 || physicalkeySecuConfig == 2 || physicalkeySecupConfig == 2 || physicalkeySecurlConfig == 2 || physicalkeySecurrConfig == 2) {
                                    bYDAutoEventValue.intValue = 2;
                                }
                                sparseArray.put(BYDAutoFeatureIds.SET_MASSAGE_PHYSICAL_BUTTONS_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_AUTO_STEER_WHEEL_HEATING_CONFIG /* 4731 */:
                                bYDAutoEventValue.intValue = getSteeringWheelHeatedPlan();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_AUTO_STEER_WHEEL_HEATING_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_SEAT_LAYOUT /* 4734 */:
                                bYDAutoEventValue.intValue = getCarSeatLayout();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SEAT_LAYOUT, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_EXCLUSIVE_SAVE_SHORTCUT_KEY /* 4735 */:
                                bYDAutoEventValue.intValue = getSaveOwnerState();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_EXCLUSIVE_SAVE_SHORTCUT_KEY, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_MOBINE_PUSH_REMIND /* 4736 */:
                                bYDAutoEventValue.intValue = getRefrigeratorMobileTerminalPushRe();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_MOBINE_PUSH_REMIND, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_FIRST_REFRIGERATOR_LOCATION_FLAG /* 4737 */:
                                bYDAutoEventValue.intValue = getRefrig1PositionMarkBit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_FIRST_REFRIGERATOR_LOCATION_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_SECOND_REFRIGERATOR_LOCATION_FLAG /* 4738 */:
                                bYDAutoEventValue.intValue = getRefrig2PositionMarkBit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SECOND_REFRIGERATOR_LOCATION_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE_FLAG /* 4739 */:
                                bYDAutoEventValue.intValue = getRefrigWorkFlag();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE /* 4740 */:
                                bYDAutoEventValue.intValue = getRefrigWorkMode();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_QUANTITY_FLAG /* 4742 */:
                                bYDAutoEventValue.intValue = getRefrigNumFlag();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_QUANTITY_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            default:
                                switch (i) {
                                    case 489:
                                        bYDAutoEventValue.intValue = getEcuMassageLevel(3);
                                        sparseArray.put(489, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    case 490:
                                        bYDAutoEventValue.intValue = getEcuMassageLevel(4);
                                        sparseArray.put(490, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    case 491:
                                        bYDAutoEventValue.intValue = getMassageMode(3);
                                        sparseArray.put(491, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    case 492:
                                        bYDAutoEventValue.intValue = getEcuMassageMode(4);
                                        sparseArray.put(492, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    default:
                                        switch (i) {
                                            case 556:
                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
                                                sparseArray.put(556, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case 557:
                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
                                                sparseArray.put(557, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case 558:
                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
                                                sparseArray.put(558, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case 559:
                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
                                                sparseArray.put(559, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            default:
                                                switch (i) {
                                                    case 584:
                                                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearSeatReclineConfiguration();
                                                        sparseArray.put(584, Integer.valueOf(bYDAutoEventValue.intValue));
                                                        break;
                                                    case 585:
                                                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
                                                        sparseArray.put(585, Integer.valueOf(bYDAutoEventValue.intValue));
                                                        break;
                                                    case 586:
                                                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByECU();
                                                        sparseArray.put(586, Integer.valueOf(bYDAutoEventValue.intValue));
                                                        break;
                                                    case 587:
                                                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByGCtrl();
                                                        sparseArray.put(587, Integer.valueOf(bYDAutoEventValue.intValue));
                                                        break;
                                                    case 588:
                                                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByECU();
                                                        sparseArray.put(588, Integer.valueOf(bYDAutoEventValue.intValue));
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 590:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightBackrestAdjustmentConfigurationByECU();
                                                                sparseArray.put(590, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 591:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
                                                                sparseArray.put(591, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 592:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightLevelAdjustmentConfigurationByECU();
                                                                sparseArray.put(592, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 593:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightBidetAdjustmentConfigurationByECU();
                                                                sparseArray.put(593, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 594:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestHeightAdjustmentConfigurationByECU();
                                                                sparseArray.put(594, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 595:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestLevelAdjustmentConfigurationByECU();
                                                                sparseArray.put(595, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 596:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeightAdjustmentConfigurationByECU();
                                                                sparseArray.put(596, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 597:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
                                                                sparseArray.put(597, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 598:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightLegSupportAdjustmentConfigurationByECU();
                                                                sparseArray.put(598, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 599:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearMemoryConfiguration();
                                                                sparseArray.put(599, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            case 600:
                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfigurationByEUC();
                                                                sparseArray.put(600, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case 731:
                                                                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
                                                                        sparseArray.put(731, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                        break;
                                                                    case 732:
                                                                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
                                                                        sparseArray.put(732, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                        break;
                                                                    case 733:
                                                                        bYDAutoEventValue.intValue = getRearLeftEcuSaveOrRecoverState();
                                                                        sparseArray.put(733, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case 775:
                                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
                                                                                sparseArray.put(775, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                break;
                                                                            case 776:
                                                                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
                                                                                sparseArray.put(776, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                break;
                                                                            case 777:
                                                                                bYDAutoEventValue.intValue = getRearRightEcuSaveOrRecoverState();
                                                                                sparseArray.put(777, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case 923:
                                                                                        bYDAutoEventValue.intValue = getDimmingCanopyConfiguration();
                                                                                        sparseArray.put(923, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                        break;
                                                                                    case 924:
                                                                                        bYDAutoEventValue.intValue = getCurrentTransmittanceOfSkylight();
                                                                                        sparseArray.put(924, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                        break;
                                                                                    case 925:
                                                                                        bYDAutoEventValue.intValue = getSkyCurtainPowerOffAndDim();
                                                                                        sparseArray.put(925, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                        break;
                                                                                    default:
                                                                                        switch (i) {
                                                                                            case 1066:
                                                                                                bYDAutoEventValue.intValue = getSupportSeatMassage(1);
                                                                                                sparseArray.put(1066, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                break;
                                                                                            case 1067:
                                                                                                bYDAutoEventValue.intValue = getMassageLevel(1);
                                                                                                sparseArray.put(1067, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                break;
                                                                                            case 1068:
                                                                                                bYDAutoEventValue.intValue = getMassageMode(1);
                                                                                                sparseArray.put(1068, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                break;
                                                                                            case 1069:
                                                                                                bYDAutoEventValue.intValue = getSupportSeatMassage(2);
                                                                                                sparseArray.put(1069, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                break;
                                                                                            case 1070:
                                                                                                bYDAutoEventValue.intValue = getMassageLevel(2);
                                                                                                sparseArray.put(1070, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                break;
                                                                                            case 1071:
                                                                                                bYDAutoEventValue.intValue = getMassageMode(2);
                                                                                                sparseArray.put(1071, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                break;
                                                                                            default:
                                                                                                Log.v(TAG, "case fail ! please check out");
                                                                                                break;
                                                                                        }
                                                                                        break;
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i2++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
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
        if (i == 377) {
            setHudScreenProjectionState(iIntValue);
            Log.v(TAG, "Setting.SET_INTELLIGENT_PROIECTION_SWITCH_SET:");
        } else if (i == 378) {
            setAadsSwitchSate(iIntValue);
            Log.v(TAG, "Setting.SET_AFS_STATE_SET:");
        } else if (i == 391) {
            setHudBrightness(iIntValue);
            Log.v(TAG, "Setting.SET_BRIGHTNESS_GEAR_SET:");
        } else if (i == 392) {
            setBackDoorElectricMode(iIntValue);
            Log.v(TAG, "Setting.SET_CAR_BACK_DOOR_ELECTRIC_MODE_SET:");
        } else if (i == 446) {
            setExhibitionStartEnginePermit(iIntValue);
            Log.v(TAG, "Setting.SET_EXHIBITION_START_ENGINE_PERMIT_SET:");
        } else if (i == 447) {
            factoryResetAll(iIntValue);
            Log.v(TAG, "SET_FACTORY_RESET_ALL_SET:" + iIntValue);
        } else if (i == 451) {
            setHudHeight(iIntValue);
            Log.v(TAG, "Setting.SET_HEIGHT_GEAR_SET:");
        } else if (i == 452) {
            setHudPattern(iIntValue);
            Log.v(TAG, "Setting.SET_HUD_MODE_CHOICE_SET:");
        } else if (i == 454) {
            setHudMode(iIntValue);
            Log.v(TAG, "Setting.SET_HUD_MODE_SET:");
        } else if (i == 455) {
            setHudState(iIntValue);
            Log.v(TAG, "Setting.SET_HUD_SWITCH_SET:");
        } else if (i == 477) {
            setRestMode(iIntValue);
            Log.v(TAG, "Setting.SET_NAP_MODE_SET:");
        } else if (i != 478) {
            switch (i) {
                case 380:
                    setHudRotationAngle(iIntValue);
                    Log.v(TAG, "Setting.SET_ANGEL_GERA_SET:");
                    break;
                case 383:
                    setAutoRainWiperState(iIntValue);
                    Log.v(TAG, "Setting.SET_AUTO_RAIN_WIPER_SWITCH_SET:");
                    break;
                case 385:
                    setSteeringWheelHeatingGear(iIntValue);
                    Log.v(TAG, "Setting.SET_AUTO_STEER_WHEEL_HEATING_GEAR_SET:");
                    break;
                case 387:
                    setSteeringWheelHeatingAutoState(iIntValue);
                    Log.v(TAG, "Setting.SET_AUTO_STEER_WHEEL_HEATING_MODE_SET:");
                    break;
                case 389:
                    backLightDefaultReset(iIntValue);
                    Log.v(TAG, "SET_BACK_LIGHT_RESET_SET:" + iIntValue);
                    break;
                case 394:
                    setBackHomeLightDelayValue(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_BACK_HOME_LIGHT_DELAY_VALUE_SET:");
                    break;
                case 396:
                    setRemoteControlDownwindowState(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_CTRL_WINDOW_SET:");
                    break;
                case 398:
                    setDriverSeatAutoReturn(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_DV_AUTO_RETURN_SET:");
                    break;
                case 400:
                    setAutoExternalRearMirrorFollowUpSwitch(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_EXTREARMIR_FOLLOWUP_SWITCH_SET:");
                    break;
                case 402:
                    setLeftHomeLightDelayValue(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_LEFT_HOME_LIGHT_DELAY_VALUE_SET:");
                    break;
                case 404:
                    setLockCarRiseWindow(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_LOCK_CLOSEWINDOW_SET:");
                    break;
                case 407:
                    setMicroSwitchUnlockWindowState(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_LPUNLOCKWINDOW_SET:");
                    break;
                case 409:
                    setMicroSwitchLockWindowState(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_MICRO_SWITCH_LOCK_WINDOW_SET:");
                    break;
                case 411:
                    setPM25Power(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_PM25_POWER_SET:");
                    break;
                case 413:
                    setPM25SwitchCheck(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_PM25_SW_CHECK_SET:");
                    break;
                case 415:
                    setPM25TimeCheck(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_PM25_TIME_SET:");
                    break;
                case 418:
                    setRearViewMirrorFlip(iIntValue);
                    Log.v(TAG, "Setting.SET_CAR_REAR_VIEW_SET:");
                    break;
                case 426:
                    setDynamicSuspensionMode(iIntValue);
                    Log.v(TAG, "Setting.SET_COMFORT_MODE_CONTROL_SWITCH_SIGNAL_SET:");
                    break;
                case 428:
                    doorsAndWindowDefaultResset(iIntValue);
                    Log.v(TAG, "SET_DOOR_WINDOW_RESET_SET:" + iIntValue);
                    break;
                case 430:
                    setChargingPort(iIntValue);
                    Log.v(TAG, "Setting.SET_DR_CHARGER_PORT_SET:");
                    break;
                case 432:
                    setEnergyFeedback(iIntValue);
                    Log.v(TAG, "Setting.SET_DR_ENERGY_FB_SET:");
                    break;
                case 434:
                    setSOCTarget(iIntValue);
                    Log.v(TAG, "Setting.SET_DR_SOC_TARGET_SET:");
                    break;
                case 442:
                    setHudDisplayContent(2, iIntValue);
                    Log.v(TAG, "Setting.SET_DYNAMIC_NAVI_FUNCTION_STATUS_SET:");
                    break;
                case 449:
                    setDoorknobAutoShrinkState(iIntValue);
                    Log.v(TAG, "Setting.SET_HANDLE_AUTO_RESET_SET:");
                    break;
                case 464:
                    turnSeatbackrest(3, iIntValue);
                    Log.v(TAG, "SET_LR_BACKREST_SIGNAL_SET:" + iIntValue);
                    break;
                case 465:
                    turnSeatHorization(3, iIntValue);
                    Log.v(TAG, "SET_LR_BALANCE_SIGNAL_SET:" + iIntValue);
                    break;
                case 466:
                    turnSeatCushion(3, iIntValue);
                    Log.v(TAG, "SET_LR_BIDET_SIGNAL_SET:" + iIntValue);
                    break;
                case 467:
                    turnHeadrestTurnHeight(3, iIntValue);
                    Log.v(TAG, "SET_LR_HEADREST_SIGNAL_SET:" + iIntValue);
                    break;
                case 468:
                    turnSeatHeight(3, iIntValue);
                    Log.v(TAG, "SET_LR_HEIGHT_SIGNAL_SET:" + iIntValue);
                    break;
                case 469:
                    turnSeatCaliperSplint(3, iIntValue);
                    Log.v(TAG, "SET_LR_LEG_REST_SIGNAL_SET:" + iIntValue);
                    break;
                case 470:
                    saveSeatParamsAll(iIntValue, 3);
                    Log.v(TAG, "Setting.SET_LR_MEMORY_LOCATION_SET:");
                    break;
                case 471:
                    resetSeatParams(iIntValue, 3);
                    Log.v(TAG, "SET_LR_MEMORY_LOCATION_WAKE_SET:" + iIntValue);
                    break;
                case 480:
                    setOverspeedLock(iIntValue);
                    Log.v(TAG, "Setting.SET_OVERSPEED_LOCKING_STATE_SET:");
                    break;
                case 481:
                    setScreenRotation(iIntValue);
                    Log.v(TAG, "Setting.SET_PAD_ROTATION_SET:");
                    break;
                case 484:
                    setPetMode(iIntValue);
                    Log.v(TAG, "Setting.SET_PET_MODE_SET:");
                    break;
                case 485:
                    lightDefaultReset(iIntValue);
                    Log.v(TAG, "SET_READING_LIGHT_RESET_SET:" + iIntValue);
                    break;
                case 487:
                    setRearAcPanelAutoLockTime(iIntValue);
                    Log.v(TAG, "Setting.SET_REAR_AC_PANEL_LOCK_TIME_SET:");
                    break;
                case 494:
                    rearMirrorDefaultReset(iIntValue);
                    Log.v(TAG, "SET_REAR_MIRROR_RESET_SET:" + iIntValue);
                    break;
                case 497:
                    setLockOff(iIntValue);
                    Log.v(TAG, "Setting.SET_REMOTE_CONTROL_UNLOCKING_SET:");
                    break;
                case 498:
                    carSettingsReset(iIntValue);
                    Log.v(TAG, "SET_RESTORE_CAR_DEFAULT_VALUES_SET:" + iIntValue);
                    break;
                case 499:
                    driveSettingsReset(iIntValue);
                    Log.v(TAG, "SET_RESTORE_DR_DEFAULT_VALUES_SET:" + iIntValue);
                    break;
                case 500:
                    turnSeatbackrest(2, iIntValue);
                    Log.v(TAG, "SET_RF_BACKREST_SIGNAL_SET:" + iIntValue);
                    break;
                case 501:
                    turnSeatHorization(2, iIntValue);
                    Log.v(TAG, "SET_RF_BALANCE_SIGNAL_SET:" + iIntValue);
                    break;
                case 502:
                    turnSeatCushion(2, iIntValue);
                    Log.v(TAG, "SET_RF_BIDET_SIGNAL_SET:" + iIntValue);
                    break;
                case 503:
                    turnHeadrestTurnHeight(2, iIntValue);
                    Log.v(TAG, "SET_RF_HEADREST_SIGNAL_SET:" + iIntValue);
                    break;
                case 504:
                    turnSeatHeight(2, iIntValue);
                    Log.v(TAG, "SET_RF_HEIGHT_SIGNAL_SET:" + iIntValue);
                    break;
                case 505:
                    turnSeatCaliperSplint(2, iIntValue);
                    Log.v(TAG, "SET_RF_LEG_REST_SIGNAL_SET:" + iIntValue);
                    break;
                case 506:
                    saveSeatParamsAll(iIntValue, 2);
                    Log.v(TAG, "SET_RF_MEMORY_LOCATION_SET:" + iIntValue);
                    break;
                case 507:
                    resetSeatParams(iIntValue, 2);
                    Log.v(TAG, "SET_RF_MEMORY_LOCATION_WAKE_SET:" + iIntValue);
                    break;
                case 508:
                    turnSeatbackrest(4, iIntValue);
                    Log.v(TAG, "SET_RR_BACKREST_SIGNAL_SET:" + iIntValue);
                    break;
                case 509:
                    turnSeatHorization(4, iIntValue);
                    Log.v(TAG, "SET_RR_BALANCE_SIGNAL_SET:" + iIntValue);
                    break;
                case 510:
                    turnSeatCushion(4, iIntValue);
                    Log.v(TAG, "SET_RR_BIDET_SIGNAL_SET:" + iIntValue);
                    break;
                case 511:
                    turnHeadrestTurnHeight(4, iIntValue);
                    Log.v(TAG, "SET_RR_HEADREST_SIGNAL_SET:" + iIntValue);
                    break;
                case 512:
                    turnSeatHeight(4, iIntValue);
                    Log.v(TAG, "SET_RR_HEIGHT_SIGNAL_SET:" + iIntValue);
                    break;
                case 513:
                    turnSeatCaliperSplint(4, iIntValue);
                    Log.v(TAG, "SET_RR_LEG_REST_SIGNAL_SET:" + iIntValue);
                    break;
                case 514:
                    saveSeatParamsAll(iIntValue, 4);
                    Log.v(TAG, "SET_RR_MEMORY_LOCATION_SET:" + iIntValue);
                    break;
                case 515:
                    resetSeatParams(iIntValue, 4);
                    Log.v(TAG, "SET_RR_MEMORY_LOCATION_WAKE_SET:" + iIntValue);
                    break;
                case 517:
                    setHudDisplayContent(1, iIntValue);
                    Log.v(TAG, "Setting.SET_SAFE_DRIVING_ASSIST_STATUS_SET:");
                    break;
                case 518:
                    setHudSafeDriveState(iIntValue);
                    Log.v(TAG, "Setting.SET_SAFETY_DRIVING_AID_FUSION_SWITCH_SET:");
                    break;
                case 519:
                    autoBackDefaultReset(iIntValue);
                    Log.v(TAG, "SET_SEAT_SELF_RETURN_RESET_SET:" + iIntValue);
                    break;
                case 521:
                    setAutoPowerOnState(iIntValue);
                    Log.v(TAG, "Setting.SET_SMART_ELECTRICITY_SET:");
                    break;
                case 524:
                    setSpeedReminderValue(iIntValue);
                    Log.v(TAG, "Setting.SET_SPEED_REMINDER_SET:");
                    break;
                case 526:
                    setSteeringWheelHeatingState(iIntValue);
                    Log.v(TAG, "Setting.SET_STEERING_WHEEL_HEAT_STATE_SET:");
                    break;
                case 527:
                    clockDefaultReset(iIntValue);
                    Log.v(TAG, "SET_TIME_RESET_SET:" + iIntValue);
                    break;
                case 528:
                    setKeyVibratorLevel(iIntValue);
                    Log.v(TAG, "Setting.SET_VIBRATION_SENSING_GEAR_SIGNAL:");
                    break;
                case 529:
                    setKeyVibratorLevel(iIntValue);
                    Log.v(TAG, "Setting.SET_VIBRATION_SENSING_GEAR_SIGNAL_SET:");
                    break;
                case 530:
                    voiceCtlBackDoor(iIntValue);
                    Log.v(TAG, "SET_VOICE_CTRL_BACK_DOOR_SET:" + iIntValue);
                    break;
                case 531:
                    outChargeDefaultReset(iIntValue);
                    Log.v(TAG, "SET_VTO_RESET_SET:" + iIntValue);
                    break;
                case 532:
                    setWipeMode(iIntValue);
                    Log.v(TAG, "Setting.SET_WIPE_SCREEN_MODE_SET:");
                    break;
                case 688:
                    setINSTheme(iIntValue);
                    Log.v(TAG, "Setting.SETTING_INSTRUMENT_THEME_SET:");
                    break;
                case 752:
                    setRearSeatsFoldOrExpand(iIntValue);
                    Log.v(TAG, "Setting.SETTING_REAR_SEAT_ONE_KEY_SET:");
                    break;
                case 838:
                    setUiVersion(iIntValue);
                    Log.v(TAG, "Setting.SETTING_UI_PLATFORM_SET:");
                    break;
                case 878:
                    setACAutoAir(iIntValue);
                    Log.v(TAG, "Setting.SET_AC_AUTO_AIR_SET:");
                    break;
                case 882:
                    setACBTWind(iIntValue);
                    Log.v(TAG, "Setting.SET_AC_BT_SET:");
                    break;
                case 887:
                    setACTunnelCycle(iIntValue);
                    Log.v(TAG, "Setting.SET_AC_TUNNEL_CYCLE_SET:");
                    break;
                case 922:
                    setCanopyAutomaticDimming(iIntValue);
                    break;
                case 926:
                    setSkyCurtainPowerOffAndDim(iIntValue);
                    break;
                case 927:
                    setOpeningAngleInformationSetOnTheR(iIntValue);
                    break;
                case 933:
                    setBackDoorOpenedHeight(iIntValue);
                    break;
                case 1029:
                    setMassageMode(2, iIntValue);
                    Log.v(TAG, "setMassageMode2:" + iIntValue);
                    break;
                case 1030:
                    setMassageLevel(2, iIntValue);
                    Log.v(TAG, "setMassageLevel2:" + iIntValue);
                    break;
                case 1042:
                    setSeatHeatingState(1, iIntValue);
                    Log.v(TAG, "setSeatHeatingStateDriver:" + iIntValue);
                    break;
                case 1045:
                    setSeatVentilatingState(1, iIntValue);
                    Log.v(TAG, "setSeatVentilatingStateDriver:" + iIntValue);
                    break;
                case 1157:
                    setIALColor(2, iIntValue, 1);
                    Log.v(TAG, "setIALColorFrontBack:" + iIntValue);
                    break;
                case 1161:
                    setIALColor(1, iIntValue, 1);
                    Log.v(TAG, "setIALColorFront:" + iIntValue);
                    break;
                case 1172:
                    setIALArea(iIntValue, 0);
                    Log.v(TAG, "Setting.SET_INTERIOR_ATMOSPHERE_LAMP_AREA_SET:");
                    break;
                case 1174:
                    setIALBrightness(5, iIntValue, 1);
                    Log.v(TAG, "SET_INTERIOR_ATMOSPHERE_LAMP_BRIGHTNESS_SET:" + iIntValue);
                    break;
                case 1177:
                    setIALColor(this.mTsManagerImpl.getCarBodyManager().getLastSetedArea(), iIntValue, 0);
                    Log.v(TAG, "setSeatVentilatingStateDriver:" + iIntValue);
                    break;
                case 1178:
                    setIALArea(0, iIntValue);
                    Log.v(TAG, "Setting.SET_INTERIOR_ATMOSPHERE_LAMP_SOURCE_SET:");
                    break;
                case 1184:
                    setMode(iIntValue);
                    Log.v(TAG, "Setting.SET_IVI_SCENE_MODE_SET:");
                    break;
                case 1188:
                    languageDefaultReset(iIntValue);
                    Log.v(TAG, "SET_LANGUAGE_RESET_SET:" + iIntValue);
                    break;
                case 1190:
                    setLanguage(iIntValue);
                    Log.v(TAG, "Setting.SET_LANGUAGE_TYPE_SET:");
                    break;
                case 1201:
                    setMassageMode(3, iIntValue);
                    Log.v(TAG, "setMassageLevel3:" + iIntValue);
                    break;
                case 1209:
                    setMassageMode(1, iIntValue);
                    Log.v(TAG, "setMassageMode1:" + iIntValue);
                    break;
                case 1210:
                    setMassageLevel(1, iIntValue);
                    Log.v(TAG, "setMassageLevel1:" + iIntValue);
                    break;
                case 1257:
                    setAmbientMusicModeState(0, iIntValue);
                    Log.v(TAG, "SET_MUSIC_MODE_STATE_SET: " + iIntValue);
                    break;
                case 1271:
                    setSeatHeatingState(2, iIntValue);
                    Log.v(TAG, "setSeatHeatingStatePassenger:" + iIntValue);
                    break;
                case 1274:
                    setSeatVentilatingState(2, iIntValue);
                    Log.v(TAG, "setSeatVentilatingStatePassenger:" + iIntValue);
                    break;
                case 1284:
                    setSeatHeatingState(3, iIntValue);
                    Log.v(TAG, "setSeatHeatingStateLeft:" + iIntValue);
                    break;
                case 1291:
                    setSeatVentilatingState(3, iIntValue);
                    Log.v(TAG, "setSeatVentilatingStateLeft:" + iIntValue);
                    break;
                case 1295:
                    setSeatHeatingState(4, iIntValue);
                    Log.v(TAG, "setSeatHeatingStateRight:" + iIntValue);
                    break;
                case 1302:
                    setSeatVentilatingState(4, iIntValue);
                    Log.v(TAG, "setSeatVentilatingStateRight:" + iIntValue);
                    break;
                case 1314:
                    setMassageMode(4, iIntValue);
                    Log.v(TAG, "setMassageLevel4:" + iIntValue);
                    break;
                case 1386:
                    setMassageLevel(3, iIntValue);
                    Log.v(TAG, "setMassageMode3:" + iIntValue);
                    break;
                case 1387:
                    setMassageLevel(4, iIntValue);
                    Log.v(TAG, "setMassageMode4:" + iIntValue);
                    break;
                case 1411:
                    setSafeBeltWarnState(iIntValue);
                    Log.v(TAG, "Safety.SAFETY_BELT_ALARM_SWITCH_SET:");
                    break;
                case 1412:
                    setComfortableParkingState(iIntValue);
                    Log.v(TAG, "Safety.SAFETY_BELT_MSR_STATE:");
                    break;
                case 1413:
                    setMotorPretensionerSeatbeltState(iIntValue);
                    Log.v(TAG, "Safety.SAFETY_BELT_MSR_STATE_SET:");
                    break;
                case 1699:
                    setComSpeedUpState(iIntValue);
                    break;
                case 1704:
                    setComSpeedDownState(iIntValue);
                    break;
                case 1709:
                    setRainAutoLockWindowState(iIntValue);
                    break;
                case 1724:
                    setDisinfState(iIntValue);
                    break;
                case 1728:
                    setRemenberPowerOffState(iIntValue);
                    break;
                case 1740:
                    setHSpeedAutoCloseState(iIntValue);
                    break;
                case 2004:
                    turnRearviewMirror(iIntValue);
                    Log.v(TAG, "turnRearviewMirror:" + iIntValue);
                    break;
                case 2087:
                    setTrayState(iIntValue);
                    break;
                case 2135:
                    turnSteeringWheel(iIntValue);
                    Log.v(TAG, "turnSteeringWheel:" + iIntValue);
                    break;
                case 2548:
                    newEnergyDefaultReset(iIntValue);
                    break;
                case 2658:
                    setAutoPowerOffState(iIntValue);
                    break;
                case 2811:
                    setAmbientMulticolorState(0, iIntValue);
                    break;
                case 2844:
                    setIALBrightness(5, iIntValue, 1);
                    Log.v(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP_BRIGHTNESS:" + iIntValue);
                    break;
                case 2845:
                    setIALColor(5, iIntValue, 1);
                    Log.v(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP:" + iIntValue);
                    break;
                case 2846:
                    setIALBrightness(4, iIntValue, 1);
                    Log.v(TAG, "Light.LIGHT_STAR_SKY_ATMOSPHERE_LAMP_BRIGHTNESS:" + iIntValue);
                    break;
                case 2847:
                    setIALColor(4, iIntValue, 1);
                    Log.v(TAG, "LIGHT_STAR_SKY_ATMOSPHERE_LAMP:" + iIntValue);
                    break;
                case 3159:
                case 3233:
                    setBrakePedalMode(iIntValue);
                    break;
                case 3162:
                    setComfortableParkingState(iIntValue);
                    break;
                case 3172:
                    intelligentDriveDefaultReset(iIntValue);
                    break;
                case 3254:
                    laneAssistDriveDefaultReset(iIntValue);
                    break;
                case 3280:
                    safeAssistDefaultReset(iIntValue);
                    break;
                case 3373:
                    set5LevelSeatHeatingLevel(1, iIntValue);
                    break;
                case 3375:
                    set5LevelSeatHeatingState(1, iIntValue);
                    break;
                case 3377:
                    set5LevelSeatVentilatingMode(1, iIntValue);
                    break;
                case 3379:
                    set5LevelSeatVentilatingLevel(1, iIntValue);
                    break;
                case 3381:
                    set5LevelSeatVentilatingState(1, iIntValue);
                    break;
                case 3427:
                    set5LevelSeatHeatingLevel(2, iIntValue);
                    break;
                case 3429:
                    set5LevelSeatHeatingState(2, iIntValue);
                    break;
                case 3431:
                    set5LevelSeatVentilatingMode(2, iIntValue);
                    break;
                case 3433:
                    set5LevelSeatVentilatingLevel(2, iIntValue);
                    break;
                case 3435:
                    set5LevelSeatVentilatingState(2, iIntValue);
                    break;
                case 3468:
                    set5LevelSeatHeatingLevel(3, iIntValue);
                    break;
                case 3470:
                    set5LevelSeatHeatingState(3, iIntValue);
                    break;
                case 3472:
                    set5LevelSeatVentilatingMode(3, iIntValue);
                    break;
                case 3474:
                    set5LevelSeatVentilatingLevel(3, iIntValue);
                    break;
                case 3476:
                    set5LevelSeatVentilatingState(3, iIntValue);
                    break;
                case 3491:
                    set5LevelSeatHeatingLevel(4, iIntValue);
                    break;
                case 3493:
                    set5LevelSeatHeatingState(4, iIntValue);
                    break;
                case 3495:
                    set5LevelSeatVentilatingMode(4, iIntValue);
                    break;
                case 3497:
                    set5LevelSeatVentilatingLevel(4, iIntValue);
                    break;
                case 3499:
                    set5LevelSeatVentilatingState(4, iIntValue);
                    break;
                case 3508:
                    carSettingsDefaultReset(iIntValue);
                    break;
                case 3521:
                    setIntelligentAcAreaState(iIntValue);
                    break;
                case 3558:
                    setAcWarmMode(iIntValue);
                    break;
                case 3588:
                    setEngineSoundType(iIntValue);
                    break;
                case 4637:
                    setElectricTailAutomaticModeSwitchState(iIntValue);
                    break;
                case 4638:
                    setElectricTailAutoModeVoiceSwitchState(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORKING_STATUS_SET /* 4715 */:
                    setRefrigeratorWorkMode(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POWEROFF_MEMORY_SET /* 4716 */:
                    setRemberPowerOffState(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS_SET /* 4717 */:
                    setSterilizationState(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_SET /* 4718 */:
                    setRefrigeratorWorkTemperature(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_PWOEROFF_SWITCH_SET /* 4719 */:
                    setDelayPowerDownState(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWEROFF_TIME_SET /* 4720 */:
                    setDelayPowerDownStateTime(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_STATE_SET /* 4721 */:
                    setFridgeChildLock(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DOOR_STATUS_SET /* 4722 */:
                    setFridgeDoorState(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE_SET /* 4723 */:
                    setDelayPowerDownTime(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_SCREEN_TRIGGER_SOURCE_SET /* 4724 */:
                    setIceBoxTriggerSource(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE_SET /* 4741 */:
                    setRefrigWorkMode(iIntValue);
                    break;
                default:
                    switch (i) {
                        case 420:
                            setRemoteControlUpwindowState(iIntValue);
                            Log.v(TAG, "Setting.SET_CAR_REMOTE_CTRL_UPWINDOW_SET:");
                            break;
                        case 421:
                            setSteerPositionAutoReturn(iIntValue);
                            Log.v(TAG, "Setting.SET_CAR_ST_AUTO_RETURN_SET:");
                            break;
                        case 422:
                            setHeadlampLevel(iIntValue);
                            Log.v(TAG, "Setting.SET_CMD_HEADLAMP_HEIGHT_LEVEL_SET:");
                            break;
                        default:
                            switch (i) {
                                case 436:
                                    setSteerAssis(iIntValue);
                                    Log.v(TAG, "Setting.SET_DR_ST_ASSIS_SET:");
                                    break;
                                case 437:
                                    turnSeatbackrest(1, iIntValue);
                                    Log.v(TAG, "SET_DRIVER_SEAT_BACKREST_SIGNAL_SET:" + iIntValue);
                                    break;
                                case 438:
                                    turnSeatHorization(1, iIntValue);
                                    Log.v(TAG, "SET_DRIVER_SEAT_BALANCE_SIGNAL_SET:" + iIntValue);
                                    break;
                                case 439:
                                    turnSeatCushion(1, iIntValue);
                                    Log.v(TAG, "SET_DRIVER_SEAT_BIDET_SIGNAL_SET:" + iIntValue);
                                    break;
                                case 440:
                                    turnSeatHeight(1, iIntValue);
                                    Log.v(TAG, "SET_DRIVER_SEAT_HEIGHT_SIGNAL_SET:" + iIntValue);
                                    break;
                                default:
                                    switch (i) {
                                        case 457:
                                            setInsideLightDoorState(iIntValue);
                                            Log.v(TAG, "Setting.SET_INSIDE_LIGHT_STATE_SET:");
                                            break;
                                        case 458:
                                            setLanternLanguageMode(iIntValue);
                                            Log.v(TAG, "Setting.SET_LANTERN_LANGUAGE_MODE_SET:");
                                            break;
                                        case 459:
                                            turnHeadrestTurnHeight(1, iIntValue);
                                            Log.v(TAG, "SET_LF_HEADREST_SIGNAL_SET:" + iIntValue);
                                            break;
                                        case 460:
                                            turnSeatCaliperSplint(1, iIntValue);
                                            Log.v(TAG, "SET_LF_LEG_REST_SIGNAL_SET:" + iIntValue);
                                            break;
                                        case 461:
                                            saveSeatParamsAll(iIntValue, 1);
                                            Log.v(TAG, "Setting.SET_LF_MEMORY_LOCATION_SET:");
                                            break;
                                        case 462:
                                            resetSeatParams(iIntValue, 1);
                                            Log.v(TAG, "SET_LF_LEG_REST_SIGNAL_SET:" + iIntValue);
                                            break;
                                        default:
                                            Log.v(TAG, "case fail ! please check out");
                                            return -1;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            setHudNavigationState(iIntValue);
            Log.v(TAG, "Setting.SET_NAVIGATION_FUSION_SWITCH_SET:");
        }
        return 1;
    }

    public int getHudPattern() {
        Log.v(TAG, "getHudPattern is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudPattern();
    }

    public int setHudPattern(int i) {
        Log.v(TAG, "setHudPattern is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudPattern(i);
    }

    public int getHudSafeDriveState() {
        Log.v(TAG, "getHudSafeDriveState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudSafeDriveState();
    }

    public int setHudSafeDriveState(int i) {
        Log.v(TAG, "setHudSafeDriveState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudSafeDriveState(i);
    }

    public int getHudNavigationState() {
        Log.v(TAG, "getHudNavigationState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudNavigationState();
    }

    public int setHudNavigationState(int i) {
        Log.v(TAG, "setHudNavigationState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudNavigationState(i);
    }

    public int getHudScreenProjectionState() {
        Log.v(TAG, "getHudScreenProjectionState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudScreenProjectionState();
    }

    public int setHudScreenProjectionState(int i) {
        Log.v(TAG, "setHudScreenProjectionState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudScreenProjectionState(i);
    }

    public int getSeatHeatingState2(int i) {
        Log.v(TAG, "getSeatHeatingState2 is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateRearLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateRearRight();
        }
        return 0;
    }

    public int getSeatHeatingNotL1PState() {
        Log.v(TAG, "getSeatHeatingNotL1PState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingNotL1PState();
    }

    public int getSeatVentilatingState2(int i) {
        Log.v(TAG, "getSeatVentilatingState2 is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingState2RearLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingState2RearRight();
        }
        return 0;
    }

    public int getSeatVentilatingNotL1PState() {
        Log.v(TAG, "getSeatVentilatingNotL1PState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingNotL1PState();
    }

    public int get5LevelSeatVentilatingState(int i) {
        Log.v(TAG, "get5LevelSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingStateDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingStateColipot();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingStateLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingStateRight();
        }
        return 0;
    }

    public int set5LevelSeatVentilatingState(int i, int i2) {
        Log.v(TAG, "set5LevelSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingStateDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingStateColipot(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingStateLeft(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingStateRight(i2);
        }
        return 0;
    }

    public int get5LevelSeatHeatingState(int i) {
        Log.v(TAG, "get5LevelSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingStateDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingStateColipot();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingStateLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingStateRight();
        }
        return 0;
    }

    public int set5LevelSeatHeatingState(int i, int i2) {
        Log.v(TAG, "set5LevelSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingStateDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingStateColipot(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingStateLeft(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingStateRight(i2);
        }
        return 0;
    }

    public int get5LevelSeatVentilatingMode(int i) {
        Log.v(TAG, "get5LevelSeatVentilatingMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingModeDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingModePassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingModeLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingModeRight();
        }
        return 0;
    }

    public int set5LevelSeatVentilatingMode(int i, int i2) {
        Log.v(TAG, "set5LevelSeatVentilatingMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingModeDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingModePassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingModeLeft(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingModeRight(i2);
        }
        return 0;
    }

    public int get5LevelSeatHeatingMode(int i) {
        Log.v(TAG, "get5LevelSeatHeatingMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingModeDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingModePassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingModeLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingModeRight();
        }
        return 0;
    }

    public int set5LevelSeatHeatingMode(int i, int i2) {
        Log.v(TAG, "set5LevelSeatHeatingMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingModeDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingModePassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingModeLeft(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingModeRight(i2);
        }
        return 0;
    }

    public int get5LevelSeatVentilatingLevel(int i) {
        Log.v(TAG, "get5LevelSeatVentilatingLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingLevelDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingLevelPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingLevelLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingLevelRight();
        }
        return 0;
    }

    public int set5LevelSeatVentilatingLevel(int i, int i2) {
        Log.v(TAG, "set5LevelSeatVentilatingLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingPassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingLeft(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingRight(i2);
        }
        return 0;
    }

    public int get5LevelSeatHeatingLevel(int i) {
        Log.v(TAG, "get5LevelSeatHeatingLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingLevelDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingLevelPassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingLevelLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingLevelRight();
        }
        return 0;
    }

    public int set5LevelSeatHeatingLevel(int i, int i2) {
        Log.v(TAG, "set5LevelSeatHeatingLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingPassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingLeft(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingRight(i2);
        }
        return 0;
    }

    public int setSteeringWheelHeatingGear(int i) {
        Log.v(TAG, "setSteeringWheelHeatingGear is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingGear(i);
    }

    public int getSteeringWheelHeatingGear() {
        Log.v(TAG, "getSteeringWheelHeatingGear is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatingGear();
    }

    public int setSteeringWheelHeatingAutoState(int i) {
        Log.v(TAG, "setSteeringWheelHeatingAutoState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingAutoState(i);
    }

    public int getSteeringWheelHeatingAutoState() {
        Log.v(TAG, "getSteeringWheelHeatingAutoState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatingAutoState();
    }

    public int getEcuMassageLevel(int i) {
        Log.v(TAG, "getEcuMassageLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEcuMassageLevelLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEcuMassageLevelRight();
        }
        return 0;
    }

    public int getEcuMassageMode(int i) {
        Log.v(TAG, "getEcuMassageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEcuMassageModeLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEcuMassageModeRight();
        }
        return 0;
    }

    public int setHsMassageState(int i, int i2) {
        Log.v(TAG, "setHsMassageState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHsMassageStateDriver(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHsMassageStatePassenger(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHsMassageStateLeft(i2);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHsMassageStateRight(i2);
        }
        return 0;
    }

    public int getHsMassageState(int i) {
        Log.v(TAG, "getHsMassageState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHsMassageStateDriver();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHsMassageStatePassenger();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHsMassageStateLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHsMassageStateRight();
        }
        return 0;
    }

    public int getMainHsMassageState() {
        Log.v(TAG, "getMainHsMassageState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainHsMassageState();
    }

    public int getHevChargingPort() {
        Log.v(TAG, "getHevChargingPort is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHevChargingPort();
    }

    public int getKeyVibratorLevel() {
        Log.v(TAG, "getKeyVibratorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getKeyVibratorLevel();
    }

    public int setKeyVibratorLevel(int i) {
        Log.v(TAG, " setKeyVibratorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setKeyVibratorLevel(i);
    }

    public int setMode(int i) {
        Log.v(TAG, "setMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMode(i);
    }

    public int setRestMode(int i) {
        Log.v(TAG, "setRestMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRestMode(i);
    }

    public int setPetMode(int i) {
        Log.v(TAG, "setPetMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPetMode(i);
    }

    public int setWipeMode(int i) {
        Log.v(TAG, "setWipeMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWipeMode(i);
    }

    public int setMeditationMode(int i) {
        Log.v(TAG, "setMeditationMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMeditationMode(i);
    }

    public int setCamping(int i) {
        Log.v(TAG, "setCamping is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCamping(i);
    }

    public int getSafeBeltWarnState() {
        Log.v(TAG, "getSafeBeltWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSafeBeltWarnState();
    }

    public int setSafeBeltWarnState(int i) {
        Log.v(TAG, "setSafeBeltWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltWarnState(i);
    }

    public int carSettingsReset(int i) {
        Log.v(TAG, "carSettingsReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int driveSettingsReset(int i) {
        Log.v(TAG, "driveSettingsReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().driveSettingsReset(i);
    }

    public int carSettingsDefaultReset(int i) {
        Log.v(TAG, "carSettingsDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int newEnergyDefaultReset(int i) {
        Log.v(TAG, "newEnergyDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().newEnergyDefaultReset(i);
    }

    public int doorsAndWindowDefaultResset(int i) {
        Log.v(TAG, "doorsAndWindowDefaultResset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int locksDefaultReset(int i) {
        Log.v(TAG, "locksDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int rearMirrorDefaultReset(int i) {
        Log.v(TAG, "rearMirrorDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int autoBackDefaultReset(int i) {
        Log.v(TAG, "autoBackDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int carHealthDefaultReset(int i) {
        Log.v(TAG, "carHealthDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().carHealthDefaultReset(i);
    }

    public int ialDefaultReset(int i) {
        Log.v(TAG, "ialDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int lightDefaultReset(int i) {
        Log.v(TAG, "lightDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int backLightDefaultReset(int i) {
        Log.v(TAG, "backLightDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int safeAssistDefaultReset(int i) {
        Log.v(TAG, "safeAssistDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int intelligentDriveDefaultReset(int i) {
        Log.v(TAG, "intelligentDriveDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int laneAssistDriveDefaultReset(int i) {
        Log.v(TAG, "laneAssistDriveDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int equalizerDefaultReset(int i) {
        Log.v(TAG, "equalizerDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int soundFiledDefaultReset(int i) {
        Log.v(TAG, "soundFiledDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int clockDefaultReset(int i) {
        Log.v(TAG, "clockDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().clockDefaultReset(i);
    }

    public int languageDefaultReset(int i) {
        Log.v(TAG, "languageDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().languageDefaultReset(i);
    }

    public int unitDefaultReset(int i) {
        Log.v(TAG, "unitDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int outChargeDefaultReset(int i) {
        Log.v(TAG, "outChargeDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().outChargeDefaultReset(i);
    }

    public int setUiVersion(int i) {
        Log.v(TAG, "setUiVersion is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setUiVersion(i);
    }

    public int getPabCode() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getPABCode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPabCode();
    }

    public int getMissPackageCount() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getMissPackageCount mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMissPackageCount();
    }

    public int getTrayActionState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getTrayActionState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrayActionState();
    }

    public int setScreenRotation(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setScreenRotation mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setScreenRotation(i);
    }

    public int getScreenAngle() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getScreenAngle mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getScreenAngle();
    }

    public int setScreenRotationLock(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setScreenRotationLock mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setScreenRotationLock(i);
    }

    public int getScreenRotationAction() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getScreenRotationAction mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getScreenRotationAction();
    }

    public int getScreenAntiPinchState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getScreenAntiPinchState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getScreenAntiPinchState();
    }

    public int getScreenAngleValue() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getScreenAngleValue mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getScreenAngleValue();
    }

    public int getScreenVoltageState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getScreenVoltageState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getScreenVoltageState();
    }

    public int getScreenRotationOverHotState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getScreenRotationOverHotState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getScreenRotationOverHotState();
    }

    public int isSomethingLeftInRefrigerator() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " isSomethingLeftInRefrigerator mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().isSomethingLeftInRefrigerator();
    }

    public int setLanguage(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setLanguage mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLanguage(i);
    }

    public int setInternationalLanguage(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setInternationalLanguage mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setInternationalLanguage(i);
    }

    public int setPabState(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setPabState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPabState(i);
    }

    public int getPabState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getPabState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPabState();
    }

    public int getMainSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainSaveOrRecoverState();
    }

    public int getMainSaveOrRecoveFailCode() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainSaveOrRecoveFailCode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainSaveOrRecoveFailCode();
    }

    public int getMain2SaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getMain2SaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMain2SaveOrRecoverState();
    }

    public int getMain2SaveOrRecoveFailCode() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getMain2SaveOrRecoveFailCode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMain2SaveOrRecoveFailCode();
    }

    public int getPassangerSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassangerSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPassangerSaveOrRecoverState();
    }

    public int getRearLeftSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearLeftSaveOrRecoverState();
    }

    public int getRearLeftEcuSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftEcuSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearLeftEcuSaveOrRecoverState();
    }

    public int getRearRightSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearRightSaveOrRecoverState();
    }

    public int getRearRightEcuSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightEcuSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearRightEcuSaveOrRecoverState();
    }

    public int getDoorHeightSupport() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getDoorHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDoorHeightSupport();
    }

    public int getElectroniChildLockConfig() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getElectroniChildLockConfig mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getElectroniChildLockConfig();
    }

    public int getAmbientAreaSupport() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAmbientAreaSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientAreaSupport();
    }

    public int getAmbientLightColorSupport() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAmbientLightColorSupport mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientLightColorSupport();
    }

    public int getAmbientLightSupport() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAmbientLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientLightSupport();
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAutoExternalRearMirrorFollowUpSwitchSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAutoExternalRearMirrorFollowUpSwitchSupport();
    }

    public int getSupportSeatMassage(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSupportSeatMassage mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSupportSeatMassage(i);
    }

    public int getAmbientFeedBack() {
        Log.v(TAG, "getAmbientFeedBack is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        Log.v(TAG, "getAmbientFeedBack is execute");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAmbientFeedBack mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientFeedBack();
    }

    public int getAmbientMusicModeState(int i) {
        Log.v(TAG, "getAmbientMusicModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAmbientMusicModeState", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMusicModeState(i);
    }

    public int setAmbientMusicModeState(int i, int i2) {
        Log.v(TAG, "setAmbientMusicModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODESTATE, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMusicModeState(i, i2);
    }

    public int getAmbientMusicMode(int i) {
        Log.v(TAG, "getAmbientMusicMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAmbientMusicMode", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMusicMode(i);
    }

    public int setAmbientMusicMode(int i, int i2) {
        Log.v(TAG, "setAmbientMusicMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODE, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMusicMode(i, i2);
    }

    public int getAmbientMulticolorState(int i) {
        Log.v(TAG, "getAmbientMulticolorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorState", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMulticolorState(i);
    }

    public int setAmbientMulticolorState(int i, int i2) {
        Log.v(TAG, "setAmbientMulticolorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMulticolorState(i, i2);
    }

    public int getAmbientMulticolorMode(int i) {
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorMode", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMulticolorMode(i);
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        Log.v(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMulticolorMode(i, i2);
    }

    public int get1LevelSeatHeatingState(int i) {
        Log.v(TAG, "get1LevelSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().get1LevelSeatHeatingState(i);
    }

    public int getBleOpenDriverDoorState() {
        Log.v(TAG, "getBleOpenDriverDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getBleOpenDriverDoorState();
    }

    public int getBrakeCloseDoor() {
        Log.v(TAG, "getBrakeCloseDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getBrakeCloseDoor();
    }

    public int getCampingBlanceAction() {
        Log.v(TAG, "getCampingBlanceAction is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCampingBlanceAction();
    }

    public int getCampingBlanceState() {
        Log.v(TAG, "getCampingBlanceState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCampingBlanceState();
    }

    public int getCampingBlanceSupported() {
        Log.v(TAG, "getCampingBlanceSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCampingBlanceSupported();
    }

    public int getCloseDoorLevel() {
        Log.v(TAG, "getCloseDoorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCloseDoorLevel();
    }

    public int getCvcMode() {
        Log.v(TAG, "getCvcMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCvcMode();
    }

    public int getDampState() {
        Log.v(TAG, "getDampState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDampState();
    }

    public int getDyHeightLevel() {
        Log.v(TAG, "getDyHeightLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDyHeightLevel();
    }

    public int getDyHeightToggleState() {
        Log.v(TAG, "getDyHeightToggleState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDyHeightToggleState();
    }

    public int getDyHeightTurnAction() {
        Log.v(TAG, "getDyHeightTurnAction is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDyHeightTurnAction();
    }

    public int getFdCampingBlanceAction() {
        Log.v(TAG, "getFdCampingBlanceAction is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdCampingBlanceAction();
    }

    public int getFdCampingBlanceState() {
        Log.v(TAG, "getFdCampingBlanceState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdCampingBlanceState();
    }

    public int getFdCampingBlanceSupported() {
        Log.v(TAG, "getFdCampingBlanceSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdCampingBlanceSupported();
    }

    public int getFdCrcCheckNum() {
        Log.v(TAG, "getFdCRCCheckNum is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdCrcCheckNum();
    }

    public int getFdDampState() {
        Log.v(TAG, "getFdDampState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdDampState();
    }

    public int getFdDyHeightLevel() {
        Log.v(TAG, "getFdDyHeightLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdDyHeightLevel();
    }

    public int getFdDyHeightToggleState() {
        Log.v(TAG, "getFdDyHeightToggleState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdDyHeightToggleState();
    }

    public int getFdDyHeightTurnAction() {
        Log.v(TAG, "getFdDyHeightTurnAction is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdDyHeightTurnAction();
    }

    public int getFdFixedState() {
        Log.v(TAG, "getFdFixedState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdFixedState();
    }

    public int getFdFrontBoxTakeState() {
        Log.v(TAG, "getFdFrontBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdFrontBoxTakeState();
    }

    public int getFdLiftModeState() {
        Log.v(TAG, "getFdLiftModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdLiftModeState();
    }

    public int getFdModeSupportedHturn() {
        Log.v(TAG, "getFdModeSupportedHturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdModeSupportedHturn();
    }

    public int getFdSpeedSupportedTurn() {
        Log.v(TAG, "getFdSpeedSupportedTurn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdSpeedSupportedTurn();
    }

    public int getFdTrailerModeCode() {
        Log.v(TAG, "getFdTrailerModeCode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdTrailerModeCode();
    }

    public int getFdWelcomeActive() {
        Log.v(TAG, "getFdWelcomeActive is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdWelcomeActive();
    }

    public int getFdWelcomeState() {
        Log.v(TAG, "getFdWelcomeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdWelcomeState();
    }

    public int getFdWelcomeSupported() {
        Log.v(TAG, "getFdWelcomeSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdWelcomeSupported();
    }

    public int getFixedState() {
        Log.v(TAG, "getFixedState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFixedState();
    }

    public int getIntelLockOpenDriverDoor() {
        Log.v(TAG, "getIntelLockOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getIntelLockOpenDriverDoor();
    }

    public int getLiftModeState() {
        Log.v(TAG, "getLiftModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLiftModeState();
    }

    public int getModeSupportedHturn() {
        Log.v(TAG, "getModeSupportedHturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getModeSupportedHturn();
    }

    public int getNfcOpenDriverDoor() {
        Log.v(TAG, "getNfcOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getNfcOpenDriverDoor();
    }

    public int getRearSeatGreetState() {
        Log.v(TAG, "getRearSeatGreetState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearSeatGreetState();
    }

    public int getRearViewEcuMirrorFlipState() {
        Log.v(TAG, "getRearViewEcuMirrorFlipState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearViewEcuMirrorFlipState();
    }

    public int getSafeBeltCloseDriveDoor() {
        Log.v(TAG, "getSafeBeltCloseDriveDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSafeBeltCloseDriveDoor();
    }

    public int getSpeedSupportedTurn() {
        Log.v(TAG, "getSpeedSupportedTurn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSpeedSupportedTurn();
    }

    public int getTailBoxTakeState() {
        Log.v(TAG, "getTailBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTailBoxTakeState();
    }

    public int getFdTailBoxTakeState() {
        Log.v(TAG, "getFdTailBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdTailBoxTakeState();
    }

    public int getFdTailBoxTakeSupported() {
        Log.v(TAG, "getFdTailBoxTakeSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdTailBoxTakeSupported();
    }

    public int getTailBoxTakeSupported() {
        Log.v(TAG, "getTailBoxTakeSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTailBoxTakeSupported();
    }

    public int getTrailerLightCheckState() {
        Log.v(TAG, "getTrailerLightCheckState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerLightCheckState();
    }

    public int getTrailerModeCode() {
        Log.v(TAG, "getTrailerModeCode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerModeCode();
    }

    public int getTrailerModeState() {
        Log.v(TAG, "getTrailerModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerModeState();
    }

    public int getVspState() {
        Log.v(TAG, "getVSPState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getVspState();
    }

    public int getWelcomeActive() {
        Log.v(TAG, "getWelcomeActive is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWelcomeActive();
    }

    public int getWelcomeState() {
        Log.v(TAG, "getWelcomeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWelcomeState();
    }

    public int getWelcomeSupported() {
        Log.v(TAG, "getWelcomeSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWelcomeSupported();
    }

    public int notifyEnterTunnel(int i) {
        Log.v(TAG, "notifyEnterTunnel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().notifyEnterTunnel(i);
    }

    public int notifyTunnelLength(int i) {
        Log.v(TAG, "notifyTunnelLength is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().notifyTunnelLength(i);
    }

    public int setBleOpenDriverDoorState(int i) {
        Log.v(TAG, "setBleOpenDriverDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBleOpenDriverDoorState(i);
    }

    public int setBrakeCloseDoor(int i) {
        Log.v(TAG, "setBrakeCloseDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBrakeCloseDoor(i);
    }

    public int setCampingBlanceState(int i) {
        Log.v(TAG, "setCampingBlanceState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCampingBlanceState(i);
    }

    public int setCheckSum(int i) {
        Log.v(TAG, "setCheckSum is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCheckSum(i);
    }

    public int setCloseDoorLevel(int i) {
        Log.v(TAG, "setCloseDoorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCloseDoorLevel(i);
    }

    public int setCvcMode(int i) {
        Log.v(TAG, "setCvcMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCvcMode(i);
    }

    public int setDampState(int i) {
        Log.v(TAG, "setDampState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDampState(i);
    }

    public int setDyHeightByVoice(int i) {
        Log.v(TAG, "setDyHeightByVoice is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightByVoice(i);
    }

    public int setDyHeightToggleState(int i) {
        Log.v(TAG, "setDyHeightToggleState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightToggleState(i);
    }

    public int setDyHeightValue(int i) {
        Log.v(TAG, "setDyHeightValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightValue(i);
    }

    public int setIntelLockOpenDriverDoor(int i) {
        Log.v(TAG, "setIntelLockOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIntelLockOpenDriverDoor(i);
    }

    public int setLiftModeState(int i) {
        Log.v(TAG, "setLiftModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLiftModeState(i);
    }

    public int setMessageCounter(int i) {
        Log.v(TAG, "setMessageCounter is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMessageCounter(i);
    }

    public int setNfcOpenDriverDoor(int i) {
        Log.v(TAG, "setNfcOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setNfcOpenDriverDoor(i);
    }

    public int setOtaMode(int i) {
        Log.v(TAG, "setOtaMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOtaMode(i);
    }

    public int setRearSeatGreetState(int i) {
        Log.v(TAG, "setRearSeatGreetState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearSeatGreetState(i);
    }

    public int setSafeBeltCloseDriveDoorState(int i) {
        Log.v(TAG, "setSafeBeltCloseDriveDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltCloseDriveDoorState(i);
    }

    public int setTailBoxTakeState(int i) {
        Log.v(TAG, "setTailBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTailBoxTakeState(i);
    }

    public int setTrailerLightCheckState(int i) {
        Log.v(TAG, "setTrailerLightCheckState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTrailerLightCheckState(i);
    }

    public int setTrailerModeState(int i) {
        Log.v(TAG, "setTrailerModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTrailerModeState(i);
    }

    public int setVspState(int i) {
        Log.v(TAG, "setVSPState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setVspState(i);
    }

    public int setWelcomeState(int i) {
        Log.v(TAG, "setWelcomeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWelcomeState(i);
    }

    public int getDriverSeatAutoReturnB3() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriverSeatAutoReturnB3 mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDriverSeatAutoReturnB3();
    }

    public int setCallState(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setCallState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCallState(i);
    }

    public int setScreenAngleValue(int i) {
        Log.i(TAG, "setScreenAngleValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setScreenAngleValue", Integer.valueOf(i));
            Log.v(TAG, "setScreenAngleValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setScreenAngleValueS(i);
    }

    public int setBsisCollisionStateS(int i) {
        Log.i(TAG, "setBsisCollisionStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setBsisCollisionStateS", Integer.valueOf(i));
            Log.v(TAG, "setBsisCollisionStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBsisCollisionStateS(i);
    }

    public int setLeftRseStateS(int i) {
        Log.i(TAG, "setLeftRseStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLeftRseStateS", Integer.valueOf(i));
            Log.v(TAG, "setLeftRseStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeftRseStateS(i);
    }

    public int setLeftRseSwitchCommandS(int i) {
        Log.i(TAG, "setLeftRseSwitchCommandS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLeftRseSwitchCommandS", Integer.valueOf(i));
            Log.v(TAG, "setLeftRseSwitchCommandS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeftRseSwitchCommandS(i);
    }

    public int setLrseChildAreaStateS(int i) {
        Log.i(TAG, "setLrseChildAreaStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildAreaStateS", Integer.valueOf(i));
            Log.v(TAG, "setLrseChildAreaStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildAreaStateS(i);
    }

    public int setLrseChildModeCode1stByteS(int i) {
        Log.i(TAG, "setLrseChildModeCode1stByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeCode1stByteS", Integer.valueOf(i));
            Log.v(TAG, "setLrseChildModeCode1stByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode1stByteS(i);
    }

    public int setLrseChildModeCode2ndByteS(int i) {
        Log.i(TAG, "setLrseChildModeCode2ndByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeCode2ndByteS", Integer.valueOf(i));
            Log.v(TAG, "setLrseChildModeCode2ndByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode2ndByteS(i);
    }

    public int setLrseChildModeCode3rdByteS(int i) {
        Log.i(TAG, "setLrseChildModeCode3rdByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeCode3rdByteS", Integer.valueOf(i));
            Log.v(TAG, "setLrseChildModeCode3rdByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode3rdByteS(i);
    }

    public int setLrseChildModeCode4thByteS(int i) {
        Log.i(TAG, "setLrseChildModeCode4thByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeCode4thByteS", Integer.valueOf(i));
            Log.v(TAG, "setLrseChildModeCode4thByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode4thByteS(i);
    }

    public int setLrseChildModeOpenTimeS(int i) {
        Log.i(TAG, "setLrseChildModeOpenTimeS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeOpenTimeS", Integer.valueOf(i));
            Log.v(TAG, "setLrseChildModeOpenTimeS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeOpenTimeS(i);
    }

    public int setMoisAssembleStateS(int i) {
        Log.i(TAG, "setMoisAssembleStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMoisAssembleStateS", Integer.valueOf(i));
            Log.v(TAG, "setMoisAssembleStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMoisAssembleStateS(i);
    }

    public int setRightRseStateS(int i) {
        Log.i(TAG, "setRightRseStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRightRseStateS", Integer.valueOf(i));
            Log.v(TAG, "setRightRseStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRightRseStateS(i);
    }

    public int setRightRseSwitchCommandS(int i) {
        Log.i(TAG, "setRightRseSwitchCommandS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRightRseSwitchCommandS", Integer.valueOf(i));
            Log.v(TAG, "setRightRseSwitchCommandS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRightRseSwitchCommandS(i);
    }

    public int setRrseChildAreaStateS(int i) {
        Log.i(TAG, "setRrseChildAreaStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildAreaStateS", Integer.valueOf(i));
            Log.v(TAG, "setRrseChildAreaStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildAreaStateS(i);
    }

    public int setRrseChildModeCode1stByteS(int i) {
        Log.i(TAG, "setRrseChildModeCode1stByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeCode1stByteS", Integer.valueOf(i));
            Log.v(TAG, "setRrseChildModeCode1stByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode1stByteS(i);
    }

    public int setRrseChildModeCode2ndByteS(int i) {
        Log.i(TAG, "setRrseChildModeCode2ndByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeCode2ndByteS", Integer.valueOf(i));
            Log.v(TAG, "setRrseChildModeCode2ndByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode2ndByteS(i);
    }

    public int setRrseChildModeCode3rdByteS(int i) {
        Log.i(TAG, "setRrseChildModeCode3rdByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeCode3rdByteS", Integer.valueOf(i));
            Log.v(TAG, "setRrseChildModeCode3rdByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode3rdByteS(i);
    }

    public int setRrseChildModeCode4thByteS(int i) {
        Log.i(TAG, "setRrseChildModeCode4thByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeCode4thByteS", Integer.valueOf(i));
            Log.v(TAG, "setRrseChildModeCode4thByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode4thByteS(i);
    }

    public int setRrseChildModeOpenTimeS(int i) {
        Log.i(TAG, "setRrseChildModeOpenTimeS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeOpenTimeS", Integer.valueOf(i));
            Log.v(TAG, "setRrseChildModeOpenTimeS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeOpenTimeS(i);
    }

    public int setSwsMediumRelatedKeysS(int i) {
        Log.i(TAG, "setSwsMediumRelatedKeysS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSwsMediumRelatedKeysS", Integer.valueOf(i));
            Log.v(TAG, "setSwsMediumRelatedKeysS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSwsMediumRelatedKeysS(i);
    }

    public int getBsisCollisionStateR() {
        Log.i(TAG, "getBsisCollisionStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("BsisCollisionStateR", new Object[0]);
            Log.v(TAG, "BsisCollisionStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getBsisCollisionStateR();
    }

    public int getBsisFuncStatusR() {
        Log.i(TAG, "getBsisFuncStatusR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("BsisFuncStatusR", new Object[0]);
            Log.v(TAG, "BsisFuncStatusR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getBsisFuncStatusR();
    }

    public int getIviProfileSettingsR() {
        Log.i(TAG, "getIviProfileSettingsR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("IviProfileSettingsR", new Object[0]);
            Log.v(TAG, "IviProfileSettingsR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getIviProfileSettingsR();
    }

    public int getLeftRseChildModeSwitchStatusR() {
        Log.i(TAG, "getLeftRseChildModeSwitchStatusR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("LeftRseChildModeSwitchStatusR", new Object[0]);
            Log.v(TAG, "LeftRseChildModeSwitchStatusR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLeftRseChildModeSwitchStatusR();
    }

    public int getLrseChildModeCodeWriteFbR() {
        Log.i(TAG, "getLrseChildModeCodeWriteFbR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("LrseChildModeCodeWriteFbR", new Object[0]);
            Log.v(TAG, "LrseChildModeCodeWriteFbR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrseChildModeCodeWriteFbR();
    }

    public int getLrseChildModeTimeWriteFbR() {
        Log.i(TAG, "getLrseChildModeTimeWriteFbR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("LrseChildModeTimeWriteFbR", new Object[0]);
            Log.v(TAG, "LrseChildModeTimeWriteFbR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrseChildModeTimeWriteFbR();
    }

    public int getLrseMachineSwitchStateR() {
        Log.i(TAG, "getLrseMachineSwitchStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("LrseMachineSwitchStateR", new Object[0]);
            Log.v(TAG, "LrseMachineSwitchStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrseMachineSwitchStateR();
    }

    public int getMoisAssembleStateR() {
        Log.i(TAG, "getMoisAssembleStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("MoisAssembleStateR", new Object[0]);
            Log.v(TAG, "MoisAssembleStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMoisAssembleStateR();
    }

    public int getMoisFuncStatusR() {
        Log.i(TAG, "getMoisFuncStatusR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("MoisFuncStatusR", new Object[0]);
            Log.v(TAG, "MoisFuncStatusR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMoisFuncStatusR();
    }

    public int getRightRseChildModeSwitchStatusR() {
        Log.i(TAG, "getRightRseChildModeSwitchStatusR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("RightRseChildModeSwitchStatusR", new Object[0]);
            Log.v(TAG, "RightRseChildModeSwitchStatusR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRightRseChildModeSwitchStatusR();
    }

    public int getRrseChildModeCodeWriteFbR() {
        Log.i(TAG, "getRrseChildModeCodeWriteFbR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("RrseChildModeCodeWriteFbR", new Object[0]);
            Log.v(TAG, "RrseChildModeCodeWriteFbR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrseChildModeCodeWriteFbR();
    }

    public int getRrseChildModeTimeWriteFbR() {
        Log.i(TAG, "getRrseChildModeTimeWriteFbR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("RrseChildModeTimeWriteFbR", new Object[0]);
            Log.v(TAG, "RrseChildModeTimeWriteFbR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrseChildModeTimeWriteFbR();
    }

    public int getRrseMachineSwitchStateR() {
        Log.i(TAG, "getRrseMachineSwitchStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("RrseMachineSwitchStateR", new Object[0]);
            Log.v(TAG, "RrseMachineSwitchStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrseMachineSwitchStateR();
    }

    public int getLeftRearMassageChairConfigurationForEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLeftRearMassageChairConfigurationForECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
    }

    public int getRightRearMassageChairConfigurationForEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightRearMassageChairConfigurationForECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        if (this.mForTest == 0) {
            Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl  is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainDriverSeatAdjustmentConfigurationByGCtrl is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByGCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainDriveElectricLegSupportAdjustmentByGCtrl is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByGCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainDriveElectricHeadrestAdjustmentByGCtrl mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByGCtrl();
    }

    public int getPassengerSeatMemoryFunctionCanByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatMemoryFunctionCanByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCanByECU();
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatElectricAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatLegSupportAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatHeadrestConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByECU();
    }

    public int getRearLeftBidetAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftBidetAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftBidetAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeightAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftHeightAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftHeadrestLevelAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftHeadrestHeightAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftMemorySwitchConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftMemorySwitchConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftMemorySwitchConfigurationByECU();
    }

    public int getRearLeftLevelAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftLevelAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftBackrestAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftBackrestAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftBackrestAdjustmentConfigurationByECU();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftLegSupportAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftLegSupportAdjustmentConfigurationByECU();
    }

    public int getLeftRearSeatBackAdjustmentConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLeftRearSeatBackAdjustmentConfiguration is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
    }

    public int getLeftRearSeatHorizontalAdjustmentConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLeftRearSeatHorizontalAdjustmentConfiguration mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getLeftRearSeatLegBracket() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLeftRearSeatLegBracket mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
    }

    public int getLeftRearMemoryConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLeftRearMemoryConfiguration mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMemoryConfiguration();
    }

    public int getRearSeatReclineConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearSeatReclineConfiguration mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearSeatReclineConfiguration();
    }

    public int getRearRightLevelAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightLevelAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearRightLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightBackrestAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightBackrestAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearRightBackrestAdjustmentConfigurationByECU();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightLegSupportAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearRightLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearRightBidetAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightBidetAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearRightBidetAdjustmentConfigurationByECU();
    }

    public int getRearRightHeightAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightHeightAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightHeadrestLevelAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightHeadrestHeightAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightMemorySwitchConfigurationByEcu() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightMemorySwitchConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRearRightMemorySwitchConfigurationByECU();
    }

    public int getRightRearSeatReclineConfigurationByEuc() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightRearSeatReclineConfigurationByEUC mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfigurationByEUC();
    }

    public int getRightRearSeatBackAdjustmentConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightRearSeatBackAdjustmentConfiguration mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
    }

    public int getRightRearSeatHorizontalAdjustmentConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightRearSeatHorizontalAdjustmentConfiguration is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getRightRearSeatLegBracket() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightRearSeatLegBracket mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
    }

    public int getRightRearMemoryConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightRearMemoryConfiguration mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRightRearMemoryConfiguration();
    }

    public int setIviProfileSettingsR(int i) {
        Log.i(TAG, "setIviProfileSettingsR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setIviProfileSettingsR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIviProfileSettingsR(i);
    }

    public int getDragModeForwardAndBackwardStateR() {
        Log.i(TAG, "getDragModeForwardAndBackwardStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "DragModeForwardAndBackwardStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDragModeForwardAndBackwardStateR();
    }

    public int getDragAndDropModeTipsStateR() {
        Log.i(TAG, "getDragAndDropModeTipsStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "DragAndDropModeTipsStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDragAndDropModeTipsStateR();
    }

    public int getFdDynamicSuspensionModeR() {
        Log.i(TAG, "getFdDynamicSuspensionModeR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "FdDynamicSuspensionModeR mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdDynamicSuspensionModeR();
    }

    public int setDriverSeatState(int i) {
        Log.i(TAG, "setDriverSeatState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setDriverSeatState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDriverSeatState(i);
    }

    public int setAdasRest(int[] iArr) {
        Log.v(TAG, " setAdasRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAdasRest(iArr);
    }

    public int setDlinkRest(int[] iArr) {
        Log.v(TAG, " setDlinkRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDlinkRest(iArr);
    }

    public int setCarsettingRest(int[] iArr) {
        Log.v(TAG, " setCarsettingRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCarsettingRest(iArr);
    }

    public int getMemorySetOperationFlag() {
        Log.v(TAG, " getMemorySetOperationFlag.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMemorySetOperationFlag();
    }

    public int getFrontWiperSpeedValue() {
        Log.v(TAG, " getFrontWiperSpeedValue.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFrontWiperSpeedValue();
    }

    public int getTrailerModeMileage() {
        Log.v(TAG, " getTrailerModeMileage.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerModeMileage();
    }

    public int getRearWiperSpeedValue() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearWiperSpeedValue mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearWiperSpeedValue();
    }

    public int setInsideLightState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setInsideLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setInsideLightState(i);
    }

    public int setOutsideLightStateScene(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setOutsideLightStateScene mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOutsideLightStateScene(i);
    }

    public int getSwsMediumRelatedKeys() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSwsMediumRelatedKeys mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSwsMediumRelatedKeys();
    }

    public int getFdComSpeedUpState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFdComSpeedUpState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFdComSpeedUpState();
    }

    public int setRearviewMirrorIsTurnedState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearviewMirrorIsTurnedState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearviewMirrorIsTurnedState(i);
    }

    public int voiceOpenMirrorlsTurnedState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " voiceOpenMirrorlsTurnedState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().voiceOpenMirrorlsTurnedState(i);
    }

    public int setRearviewMirrorIsAutoLightState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearviewMirrorIsAutoLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearviewMirrorIsAutoLightState(i);
    }

    public int setRearviewMirrorIsScreenState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearviewMirrorIsScreenState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearviewMirrorIsScreenState(i);
    }

    public int turnRearviewMirrorIs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnRearviewMirrorIs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnRearviewMirrorIs(i);
    }

    public int getRearviewMirrorIsTurnedState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearviewMirrorIsTurnedState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorIsTurnedState();
    }

    public int getRearviewMirrorIsState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearviewMirrorIsState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorIsState();
    }

    public int getRearviewMirrorIsAutoLightState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearviewMirrorIsAutoLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorIsAutoLightState();
    }

    public int resetRearviewMirrorIsSets(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " resetRearviewMirrorIsSets mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetRearviewMirrorIsSets(i);
    }

    public int setRefrigeratorWorkMode(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRefrigeratorWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRefrigeratorWorkMode(i);
    }

    public int getRefrigeratorWorkMode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigeratorWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorWorkMode();
    }

    public int setRefrigeratorWorkTemperature(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRefrigeratorWorkTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRefrigeratorWorkTemperature(i);
    }

    public int getRefrigeratorWorkTemperature() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigeratorWorkTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorWorkTemperature();
    }

    public int getFridgeWorkColdTemperature() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeWorkColdTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeWorkColdTemperature();
    }

    public int getFridgeWorkHeatTemperature() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeWorkHeatTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeWorkHeatTemperature();
    }

    public int getFridgeWorkSaveTemperature() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeWorkSaveTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeWorkSaveTemperature();
    }

    public int getDelayPowerOffState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDelayPowerOffState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDelayPowerOffState();
    }

    public int setDelayPowerOffTime(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setDelayPowerOffTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDelayPowerOffTime(i);
    }

    public int getDelayPowerOffTime() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDelayPowerOffTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDelayPowerOffTime();
    }

    public int setDelayPowerDownTime(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setDelayPowerDownTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDelayPowerDownTime(i);
    }

    public int getDelayPowerDownTime() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDelayPowerDownTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDelayPowerDownTime();
    }

    public int setRemberPowerOffState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRemberPowerOffState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemberPowerOffState(i);
    }

    public int getRemberPowerOffState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRemberPowerOffState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRemberPowerOffState();
    }

    public int setSterilizationState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setSterilizationState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSterilizationState(i);
    }

    public int getSterilizationState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSterilizationState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSterilizationState();
    }

    public int getVoiceTipMessage() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getVoiceTipMessage mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getVoiceTipMessage();
    }

    public int setFridgeDoorState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setFridgeDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setFridgeDoorState(i);
    }

    public int getFridgeDoorState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeDoorState();
    }

    public int getRefrigeratorWorkState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigeratorWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorWorkState();
    }

    public int getRefrigeratorTemperature() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigeratorTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorTemperature();
    }

    public int getDelayDownPowerTime() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDelayDownPowerTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDelayDownPowerTime();
    }

    public int getSterilizationWorkState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSterilizationWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSterilizationWorkState();
    }

    public int getDlgMessageCode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDlgMessageCode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDlgMessageCode();
    }

    public int getFridgeLocation() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeLocation mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeLocation();
    }

    public int setWorkState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWorkState(i);
    }

    public int setWorkTemperature(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setWorkTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWorkTemperature(i);
    }

    public int setWorkCommond(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setWorkCommond mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWorkCommond(i);
    }

    public int setRearWorkTemperature(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearWorkTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearWorkTemperature(i);
    }

    public int getControlCallbackState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getControlCallbackState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getControlCallbackState();
    }

    public int getWorkState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWorkState();
    }

    public int getDelayPowerOffStatus() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDelayPowerOffStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDelayPowerOffStatus();
    }

    public int getScreenCallbackMessage() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getScreenCallbackMessage mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getScreenCallbackMessage();
    }

    public int getRearDoorState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearDoorState();
    }

    public int getRearHeatTemp() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearHeatTemp mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearHeatTemp();
    }

    public int getRearSaveTemp() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearSaveTemp mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearSaveTemp();
    }

    public int getPhoneRemindCode(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPhoneRemindCode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPhoneRemindCode(i);
    }

    public int getServiceControlCallback() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getServiceControlCallback mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getServiceControlCallback();
    }

    public int setDelayPowerDownState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setDelayPowerDownState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDelayPowerDownState(i);
    }

    public int setDelayPowerDownStateTime(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setDelayPowerDownStateTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDelayPowerDownStateTime(i);
    }

    public int setFridgeChildLock(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setFridgeChildLock mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setFridgeChildLock(i);
    }

    public int saveOwnerState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " saveOwnerState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().saveOwnerState(i);
    }

    public int setRearFridgeWorkState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeWorkState(i);
    }

    public int setRearFridgeSterilizerState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeSterilizerState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeSterilizerState(i);
    }

    public int setRearFridgeTemperature(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeTemperature(i);
    }

    public int setRearFridgeDelayState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeDelayState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeDelayState(i);
    }

    public int setRearFridgeDelayTime(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeDelayTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeDelayTime(i);
    }

    public int setRearFridgeDoorState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeDoorState(i);
    }

    public int getCanopyAutomaticDimming() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCanopyAutomaticDimming mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCanopyAutomaticDimming();
    }

    public int getSkyCurtainPowerOffAndDim() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSkyCurtainPowerOffAndDim mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSkyCurtainPowerOffAndDim();
    }

    public int setCanopyAutomaticDimming(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCanopyAutomaticDimming mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCanopyAutomaticDimming(i);
    }

    public int setSkyCurtainPowerOffAndDim(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setSkyCurtainPowerOffAndDim mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSkyCurtainPowerOffAndDim(i);
    }

    public int getDimmingCanopyConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDimmingCanopyConfiguration mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDimmingCanopyConfiguration();
    }

    public int setOpeningAngleInformationSetOnTheR(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setOpeningAngleInformationSetOnTheR mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOpeningAngleInformationSetOnTheR(i);
    }

    public int getCurrentTransmittanceOfSkylight() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCurrentTransmittanceOfSkylight mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCurrentTransmittanceOfSkylight();
    }

    public int getCurrentStatusOfDimmingSunroof() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCurrentStatusOfDimmingSunroof mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCurrentStatusOfDimmingSunroof();
    }

    public int getDimmingCanopyLowCode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDimmingCanopyLowCode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDimmingCanopyLowCode();
    }

    public int getElectricTailConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getElectricTailConfiguration mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getElectricTailConfiguration();
    }

    public int getTailInitializationState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTailInitializationState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTailInitializationState();
    }

    public int getCurrentStateOfTheTailState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCurrentStateOfTheTailState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCurrentStateOfTheTailState();
    }

    public int getTailPositionPercentage() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTailPositionPercentage mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTailPositionPercentage();
    }

    public int getTailMotorThermalProtectionState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTailMotorThermalProtectionState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTailMotorThermalProtectionState();
    }

    public int getTailPositionFeedback() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTailPositionFeedback mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTailPositionFeedback();
    }

    public int getAlarmPromptState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getAlarmPromptState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAlarmPromptState();
    }

    public int getCurrentModeOfTailState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCurrentModeOfTailState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCurrentModeOfTailState();
    }

    public int getElectricTailMaintenanceFuntatusState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getElectricTailMaintenanceFuntatusState is 0  return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getElectricTailMaintenanceFuntatusState();
    }

    public int setElectricTailAutomaticModeSwitchState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setElectricTailAutomaticModeSwitchState mForTestValue is 0 ");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setElectricTailAutomaticModeSwitchState(i);
    }

    public int setElectricTailAutoModeVoiceSwitchState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setElectricTailAutoModeVoiceSwitchState mForTestValue is 0 ");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setElectricTailAutoModeVoiceSwitchState(i);
    }

    public int getRearRightMassageSwConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightMassageSwConfiguration mForTestValue is 0 ");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearRightMassageSwConfiguration();
    }

    public int getRearLeftMassageSwConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearLeftMassageSwConfiguration mForTestValue is 0 ");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearLeftMassageSwConfiguration();
    }

    public int turnSeatHorizationRearLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearLeftRcs(i);
    }

    public int turnSeatHorizationRearLeftLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearLeftLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearLeftLeftRcs(i);
    }

    public int turnSeatHorizationRearLeftRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearLeftRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearLeftRightRcs(i);
    }

    public int turnSeatHorizationRearRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearRightRcs(i);
    }

    public int turnSeatHorizationRearRightLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearRightLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearRightLeftRcs(i);
    }

    public int turnSeatHorizationRearRightRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearRightRightRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearRightRightRcs(i);
    }

    public int setFlSeatBasinSwitchState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setFlSeatBasinSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setFlSeatBasinSwitchState(i);
    }

    public int setFrontPassengerSeatSeatPotSwitchState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setFrontPassengerSeatSeatPotSwitchState mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setFrontPassengerSeatSeatPotSwitchState(i);
    }

    public int turnSeatCushionRearLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearLeftRcs(i);
    }

    public int turnSeatCushionRearLeftLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearLeftLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearLeftLeftRcs(i);
    }

    public int turnSeatCushionRearLeftRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearLeftRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearLeftRightRcs(i);
    }

    public int turnSeatCushionRearRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearRightRcs(i);
    }

    public int turnSeatCushionRearRightLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearRightLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearRightLeftRcs(i);
    }

    public int turnSeatCushionRearRightRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearRightRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearRightRightRcs(i);
    }

    public int turnSeatBackRearLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearLeftRcs(i);
    }

    public int turnSeatBackRearLeftLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearLeftLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearLeftLeftRcs(i);
    }

    public int turnSeatBackRearLeftRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearLeftRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearLeftRightRcs(i);
    }

    public int turnSeatBackRearRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearRightRcs(i);
    }

    public int turnSeatBackRearRightLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearRightLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearRightLeftRcs(i);
    }

    public int turnSeatBackRearRightRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearRightRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearRightRightRcs(i);
    }

    public int turnSeatCaliperSplintRearLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearLeftRcs(i);
    }

    public int turnSeatCaliperSplintRearLeftLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearLeftLeftRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearLeftLeftRcs(i);
    }

    public int turnSeatCaliperSplintRearLeftRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearLeftRightRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearLeftRightRcs(i);
    }

    public int turnSeatCaliperSplintRearRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearRightRcs(i);
    }

    public int turnSeatCaliperSplintRearRightLeftRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearRightLeftRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearRightLeftRcs(i);
    }

    public int turnSeatCaliperSplintRearRightRightRcs(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearRightRightRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearRightRightRcs(i);
    }

    public int getLeftElectricPedal() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLeftElectricPedal mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLeftElectricPedal();
    }

    public int getRightElectricPedal() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightElectricPedal mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRightElectricPedal();
    }

    public int getVoiceTipsMessage() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getVoiceTipsMessage mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getVoiceTipsMessage();
    }

    public int getFridgeChildLock() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeChildLock mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeChildLock();
    }

    public int getFridgeWorkingMode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeWorkingMode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeWorkingMode();
    }

    public int getFridgeTemperatureRegulation() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeTemperatureRegulation mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeTemperatureRegulation();
    }

    public int getFridgeExpansionAndContractionFunction() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeExpansionAndContractionFunction mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeExpansionAndContractionFunction();
    }

    public int getFridgeMinWagePowerGeneration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeMinWagePowerGeneration mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeMinWagePowerGeneration();
    }

    public int getCarFridgeType() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCarFridgeType mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCarFridgeType();
    }

    public int getExclusiveSaveFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getExclusiveSaveFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getExclusiveSaveFlag();
    }

    public int getFridgeChildLockFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeChildLockFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeChildLockFlag();
    }

    public int getFridgeScreenFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeScreenFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeScreenFlag();
    }

    public int getFridgeDelayedPowerOutage() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeDelayedPowerOutage mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeDelayedPowerOutage();
    }

    public int getFrontFridgeDisinfectFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFrontFridgeDisinfectFlag mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFrontFridgeDisinfectFlag();
    }

    public int getFridgeNum() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeNum mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeNum();
    }

    public int getRearFridgeDisinfectFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearFridgeDisinfectFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearFridgeDisinfectFlag();
    }

    public int getRearFridgeKeyTypeFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearFridgeKeyTypeFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearFridgeKeyTypeFlag();
    }

    public int getRearFridgeWorkTypeFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearFridgeWorkTypeFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearFridgeWorkTypeFlag();
    }

    public int getRearRightFridgeHeatingScheme() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightFridgeHeatingScheme mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearRightFridgeHeatingScheme();
    }

    public int getFridgeOnePosition() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeOnePosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeOnePosition();
    }

    public int getFridgeTwoPosition() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeTwoPosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeTwoPosition();
    }

    public int getFridgeThreePosition() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getFridgeThreePosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getFridgeThreePosition();
    }

    public int setIceBoxTriggerSource(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setIceBoxTriggerSource mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIceBoxTriggerSource(i);
    }

    public int getSocTargetRange() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSocTargetRange mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSocTargetRange();
    }

    public int getSocTargetLowLimit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSocTargetLowLimit mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSocTargetLowLimit();
    }

    public int setCpdImsSwitchState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCpdImsSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCpdImsSwitchState(i);
    }

    public int getCpdImsSwitchState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCpdImsSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCpdImsSwitchState();
    }

    public int setRncState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRncState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRncState(i);
    }

    public int getSwcontrolState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSwcontrolState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSwcontrolState();
    }

    public int setPedalSuppressionSystemState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setPedalSuppressionSystemState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPedalSuppressionSystemState(i);
    }

    public int getPedalSuppressionSystemState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPedalSuppressionSystemState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPedalSuppressionSystemState();
    }

    public int setCarDoorTipsSoundType(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCarDoorTipsSoundType mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCarDoorTipsSoundType(i);
    }

    public int getCuraSwitchState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCuraSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCuraSwitchState();
    }

    public int getPhysicalkeyConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPhysicalkeyConfig mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPhysicalkeyConfig();
    }

    public int getPhysicalkeySecuConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPhysicalkeySecuConfig mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPhysicalkeySecuConfig();
    }

    public int getPhysicalkeySecupConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPhysicalkeySecupConfig mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPhysicalkeySecupConfig();
    }

    public int getPhysicalkeySecurlConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPhysicalkeySecurlConfig mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPhysicalkeySecurlConfig();
    }

    public int getPhysicalkeySecurrConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPhysicalkeySecurrConfig mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPhysicalkeySecurrConfig();
    }

    public int getRearRightSeatGreetState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRearRightSeatGreetState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRearRightSeatGreetState();
    }

    public int setRearRightSeatGreetState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearRightSeatGreetState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearRightSeatGreetState(i);
    }

    public int setCuraSwitchState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCuraSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCuraSwitchState(i);
    }

    public int getWadingPatternSocTips() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getWadingPatternSocTips mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWadingPatternSocTips();
    }

    public int getWadingPatternSpeedTips() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getWadingPatternSpeedTips mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWadingPatternSpeedTips();
    }

    public int getWadingPatternTestTips() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getWadingPatternTestTips mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWadingPatternTestTips();
    }

    public int getWadingPatternStateTips() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getWadingPatternStateTips mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWadingPatternStateTips();
    }

    public int getSteeringWheelHeatedPlan() {
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatedPlan();
    }

    public int getGoalRoadMode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getGoalRoadMode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getGoalRoadMode();
    }

    public int setGoalRoadMode(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setGoalRoadMode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setGoalRoadMode(i);
    }

    public int getSeaModeChargingSocType() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSeaModeChargingSocType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSeaModeChargingSocType();
    }

    public int getDriveMode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriveMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDriveMode();
    }

    public int setSandAbsModeState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setSandAbsModeState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSandAbsModeState(i);
    }

    public int getSandAbsModeState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSandAbsModeState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSandAbsModeState();
    }

    public int getSandAbsTipMessage() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSandAbsTipMessage mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSandAbsTipMessage();
    }

    public int getCarSeatLayout() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCarSeatLayout mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCarSeatLayout();
    }

    public int getSaveOwnerState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSaveOwnerState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSaveOwnerState();
    }

    public int getRefrigeratorMobileTerminalPushRe() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigeratorMobileTerminalPushRe mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorMobileTerminalPushRe();
    }

    public int getRefrig1PositionMarkBit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrig1PositionMarkBit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrig1PositionMarkBit();
    }

    public int getRefrig2PositionMarkBit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrig2PositionMarkBit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrig2PositionMarkBit();
    }

    public int getRefrigWorkFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigWorkFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigWorkFlag();
    }

    public int getRefrigWorkMode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigWorkMode();
    }

    public int setRefrigWorkMode(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRefrigWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRefrigWorkMode(i);
    }

    public int getRefrigNumFlag() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigNumFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigNumFlag();
    }

    public int getDriverSeatVentilating437State() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriverSeatVentilating437State mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDriverSeatVentilating437State();
    }

    public int getDriverSeatHeating437State() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriverSeatHeating437State mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDriverSeatHeating437State();
    }

    public int getPassengerSeatVentilating3caState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatVentilating3caState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPassengerSeatVentilating3caState();
    }

    public int getPassengerSeatHeating3caState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatHeating3caState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPassengerSeatHeating3caState();
    }
}
