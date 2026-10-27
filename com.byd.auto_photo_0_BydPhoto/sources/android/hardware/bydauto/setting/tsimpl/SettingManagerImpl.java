package android.hardware.bydauto.setting.tsimpl;

import a.b.d;
import a.b.e.o;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
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
    protected static final String TAG = "SettingManagerImpl";
    private static volatile SettingManagerImpl mTsMangerImpl;
    private Context mContext;
    private o mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 1;
    private final CopyOnWriteArrayList<AbsBYDAutoSettingListener> mAbsBydAutoSettingListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

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

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void settingCallback(int i, int i2, SettingPacket settingPacket) {
        int i3;
        int doorHeightSupport;
        int ambientMusicModeStateR;
        int i4;
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
            case SettingHalId.ID_MOTOR_PRETENSIONER_SEATBELT_STATE_S /* 557856058 */:
                for (AbsBYDAutoSettingListener absBYDAutoSettingListener : this.mAbsBydAutoSettingListeners) {
                    absBYDAutoSettingListener.onMotorPretensionerSeatbeltStateChanged(settingPacket.getMotorPretensionerSeatbeltState());
                    absBYDAutoSettingListener.onResetMotorPretensionerSeatbelt(settingPacket.getMotorPretensionerSeatbeltState());
                }
                break;
            case 557856063:
                Iterator<AbsBYDAutoSettingListener> it2 = this.mAbsBydAutoSettingListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onAcKeyStartTimeChanged(settingPacket.getRemoteKeyStartAcTime());
                }
                break;
            case SettingHalId.ID_REAR_AC_PANEL_AUTO_LOCK_TIME_R /* 557856073 */:
                Iterator<AbsBYDAutoSettingListener> it3 = this.mAbsBydAutoSettingListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onRearAcPanelAutoLockTimeChanged(settingPacket.getRearAcPanelAutoLockTime());
                }
                break;
            case SettingHalId.ID_DOOR_HEIGHT_SUPPORT /* 557856076 */:
                i3 = 953;
                doorHeightSupport = settingPacket.getDoorHeightSupport();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_STEER_POSITION_AUTO_RETURNS /* 557856146 */:
                Iterator<AbsBYDAutoSettingListener> it4 = this.mAbsBydAutoSettingListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onSteerPositionAutoReturnSwitchChanged(settingPacket.getSteerPositionAutoReturn());
                }
                break;
            case SettingHalId.ID_AMBIENT_LIGHT_COLOR_SUPPORT /* 557856184 */:
                i3 = 1159;
                doorHeightSupport = settingPacket.getAmbientLightColorSupport();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_AMBIENT_LIGHT_SUPPORT /* 557856185 */:
                i3 = 1158;
                doorHeightSupport = settingPacket.getAmbientLightSupport();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_AMBIENT_AREA_SUPPORT /* 557856186 */:
                i3 = 1154;
                doorHeightSupport = settingPacket.getAmbientAreaSupport();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case 557856889:
                Iterator<AbsBYDAutoSettingListener> it5 = this.mAbsBydAutoSettingListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onBSISCollisionStateChanged(settingPacket.getBsisCollisionStateR());
                }
                break;
            case 557856891:
                Iterator<AbsBYDAutoSettingListener> it6 = this.mAbsBydAutoSettingListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onBSISFuncStatusChanged(settingPacket.getBsisFuncStatusR());
                }
                break;
            case 557856894:
                Iterator<AbsBYDAutoSettingListener> it7 = this.mAbsBydAutoSettingListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onMOISFuncStatusChanged(settingPacket.getMoisFuncStatusR());
                }
                break;
            case SettingHalId.ID_ACBT_WIND_R /* 557857536 */:
                Iterator<AbsBYDAutoSettingListener> it8 = this.mAbsBydAutoSettingListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onACBTWindSwitchChanged(settingPacket.getACBTWind());
                }
                i3 = 881;
                doorHeightSupport = settingPacket.getACBTWind();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_AC_PAUSE_CYCLE_R /* 557857540 */:
                Iterator<AbsBYDAutoSettingListener> it9 = this.mAbsBydAutoSettingListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onACPauseCycleSwitchChanged(settingPacket.getACPauseCycle());
                }
                i3 = 883;
                doorHeightSupport = settingPacket.getACPauseCycle();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_AC_AUTO_AIR_R /* 557857543 */:
                Iterator<AbsBYDAutoSettingListener> it10 = this.mAbsBydAutoSettingListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onACAutoAirModeChanged(settingPacket.getACAutoAir());
                }
                i3 = 878;
                doorHeightSupport = settingPacket.getACAutoAir();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_PM25_POWER_R /* 557857549 */:
                Iterator<AbsBYDAutoSettingListener> it11 = this.mAbsBydAutoSettingListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onPM25PowerSwitchChanged(settingPacket.getPM25Power());
                }
                i3 = 410;
                doorHeightSupport = settingPacket.getPM25Power();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_PM25_SWITCH_CHECK_R /* 557857551 */:
                Iterator<AbsBYDAutoSettingListener> it12 = this.mAbsBydAutoSettingListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onPM25SwitchCheckChanged(settingPacket.getPM25SwitchCheck());
                }
                i3 = 412;
                doorHeightSupport = settingPacket.getPM25SwitchCheck();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_PM25_TIMECHECK_R /* 557857553 */:
                Iterator<AbsBYDAutoSettingListener> it13 = this.mAbsBydAutoSettingListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onPM25TimeCheckChanged(settingPacket.getPM25TimeCheck());
                }
                i3 = 414;
                doorHeightSupport = settingPacket.getPM25TimeCheck();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_AC_TUNNEL_CYCLE_R /* 557857555 */:
                Iterator<AbsBYDAutoSettingListener> it14 = this.mAbsBydAutoSettingListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onACTunnelCycleSwitchChanged(settingPacket.getACTunnelCycle());
                }
                i3 = 885;
                doorHeightSupport = settingPacket.getACTunnelCycle();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_AC_WARM_MODE_R /* 557857557 */:
                Iterator<AbsBYDAutoSettingListener> it15 = this.mAbsBydAutoSettingListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onAcWarmModeChanged(settingPacket.getAcWarmMode());
                }
                i3 = 3557;
                doorHeightSupport = settingPacket.getAcWarmMode();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_INTELLIGENT_AC_AREASTATE_S /* 557857560 */:
                Iterator<AbsBYDAutoSettingListener> it16 = this.mAbsBydAutoSettingListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onIntelligentAcAreaStateChanged(settingPacket.getIntelligentAcAreaState());
                }
                i3 = 3522;
                doorHeightSupport = settingPacket.getIntelligentAcAreaState();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_BACK_DOOR_OPENED_HEIGHT_R /* 557857561 */:
                Iterator<AbsBYDAutoSettingListener> it17 = this.mAbsBydAutoSettingListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onBackDoorOpenedHeightChanged(settingPacket.getBackDoorOpenedHeight());
                }
                i3 = 388;
                doorHeightSupport = settingPacket.getBackDoorOpenedHeight();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_RAIN_AUTO_LOCK_WINDOW_STATE_R /* 557857563 */:
                Iterator<AbsBYDAutoSettingListener> it18 = this.mAbsBydAutoSettingListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onRainAutoLockWindowStateChanged(settingPacket.getRainAutoLockWindowState());
                }
                break;
            case SettingHalId.ID_DOOR_KNOB_AUTO_SHRINK_STATE_R /* 557857565 */:
                Iterator<AbsBYDAutoSettingListener> it19 = this.mAbsBydAutoSettingListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onDoorknobAutoShrinkStateChanged(settingPacket.getDoorknobAutoShrinkState());
                }
                break;
            case SettingHalId.ID_INTELLIGENT_UNLOCK_STATE_R /* 557857567 */:
                Iterator<AbsBYDAutoSettingListener> it20 = this.mAbsBydAutoSettingListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onIntelligentUnlockStateChanged(settingPacket.getIntelligentUnlockState());
                }
                i3 = 2662;
                doorHeightSupport = settingPacket.getIntelligentUnlockState();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_INTELLIGENT_LOCK_STATE_R /* 557857569 */:
                Iterator<AbsBYDAutoSettingListener> it21 = this.mAbsBydAutoSettingListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onIntelligentLockStateChanged(settingPacket.getIntelligentLockState());
                }
                break;
            case SettingHalId.ID_HUD_STATE_R /* 557857571 */:
                Iterator<AbsBYDAutoSettingListener> it22 = this.mAbsBydAutoSettingListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onHudStateChanged(settingPacket.getHudState());
                }
                break;
            case SettingHalId.ID_HUD_HEIGHT_R /* 557857574 */:
                Iterator<AbsBYDAutoSettingListener> it23 = this.mAbsBydAutoSettingListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onHudHeightChanged(settingPacket.getHudHeight());
                }
                break;
            case SettingHalId.ID_HUD_BRIGHTNESS_R /* 557857576 */:
                Iterator<AbsBYDAutoSettingListener> it24 = this.mAbsBydAutoSettingListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onHudBrightnessChanged(settingPacket.getHudBrightness());
                }
                break;
            case SettingHalId.ID_HUD_ROTATION_ANGLE_R /* 557857578 */:
                Iterator<AbsBYDAutoSettingListener> it25 = this.mAbsBydAutoSettingListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onHudRotationAngleChanged(settingPacket.getHudRotationAngle());
                }
                break;
            case SettingHalId.ID_HUD_MODE_R /* 557857580 */:
                Iterator<AbsBYDAutoSettingListener> it26 = this.mAbsBydAutoSettingListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onHudModeChanged(settingPacket.getHudMode());
                }
                break;
            case SettingHalId.ID_HUD_DISPLAY_CONTENT_R_DRIVE /* 557857582 */:
                Iterator<AbsBYDAutoSettingListener> it27 = this.mAbsBydAutoSettingListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onHudDisplayContentChanged(1, settingPacket.getHudDisplayContentRDrive());
                }
                break;
            case SettingHalId.ID_HUD_DISPLAY_CONTENT_R_NAVIGATION /* 557857583 */:
                Iterator<AbsBYDAutoSettingListener> it28 = this.mAbsBydAutoSettingListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onHudDisplayContentChanged(2, settingPacket.getHudDisplayContentRNavigation());
                }
                break;
            case SettingHalId.ID_HUD_DISPLAY_CONTENT_S_DRIVE /* 557857584 */:
                Iterator<AbsBYDAutoSettingListener> it29 = this.mAbsBydAutoSettingListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onResetHudDisplayContent(settingPacket.getHudDisplayContentSDrive());
                }
                break;
            case SettingHalId.ID_HUD_DISPLAY_CONTENT_S_NAVIGATION /* 557857585 */:
                Iterator<AbsBYDAutoSettingListener> it30 = this.mAbsBydAutoSettingListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onResetHudDisplayContent(settingPacket.getHudDisplayContentSNavigation());
                }
                break;
            case SettingHalId.ID_ENGINE_SOUND_TYPE_R /* 557857587 */:
                Iterator<AbsBYDAutoSettingListener> it31 = this.mAbsBydAutoSettingListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onEngineSoundTypeChanged(settingPacket.getEngineSoundType());
                }
                i3 = 3586;
                doorHeightSupport = settingPacket.getEngineSoundType();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_SPEED_REMINDER_VALUE_R /* 557857589 */:
                Iterator<AbsBYDAutoSettingListener> it32 = this.mAbsBydAutoSettingListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onSpeedReminderValueChanged(settingPacket.getSpeedReminderValue());
                }
                break;
            case SettingHalId.ID_REAR_VIEW_MIRROR_FLIP_R /* 557857591 */:
                Iterator<AbsBYDAutoSettingListener> it33 = this.mAbsBydAutoSettingListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onRearViewMirrorFlipChanged(settingPacket.getRearViewMirrorFlip());
                }
                i3 = 416;
                doorHeightSupport = settingPacket.getRearViewMirrorFlip();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_REAR_VIEW_MIRROR_FLIP_S_WITH_MEMORY /* 557857592 */:
                Iterator<AbsBYDAutoSettingListener> it34 = this.mAbsBydAutoSettingListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onRearViewMirrorFlipSetChanged(settingPacket.getRearViewMirrorFlipSet());
                }
                i3 = 417;
                doorHeightSupport = settingPacket.getRearViewMirrorFlipSet();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_AUTO_POWER_ON_STATE_S /* 557857595 */:
                Iterator<AbsBYDAutoSettingListener> it35 = this.mAbsBydAutoSettingListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onAutoPowerOnStateChanged(settingPacket.getAutoPowerOnState());
                }
                i3 = 520;
                doorHeightSupport = settingPacket.getAutoPowerOnState();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_AUTO_POWER_OFF_STATE_S /* 557857597 */:
                Iterator<AbsBYDAutoSettingListener> it36 = this.mAbsBydAutoSettingListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onAutoPowerOffStateChanged(settingPacket.getAutoPowerOffState());
                }
                break;
            case SettingHalId.ID_DRIVER_SEAT_AUTO_RETURNS /* 557857599 */:
                Iterator<AbsBYDAutoSettingListener> it37 = this.mAbsBydAutoSettingListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onDriverSeatAutoReturnStateChanged(settingPacket.getDriverSeatAutoReturn());
                }
                i3 = 397;
                doorHeightSupport = settingPacket.getDriverSeatAutoReturn();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_BRAKE_PEDAL_MODE_S /* 557857603 */:
                Iterator<AbsBYDAutoSettingListener> it38 = this.mAbsBydAutoSettingListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onBrakePedalModeChanged(settingPacket.getBrakePedalMode());
                }
                break;
            case SettingHalId.ID_COMFORTABLE_PARKING_STATE_S /* 557857605 */:
                Iterator<AbsBYDAutoSettingListener> it39 = this.mAbsBydAutoSettingListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onComfortableParkingStateChanged(settingPacket.getComfortableParkingState());
                }
                break;
            case SettingHalId.ID_AUTO_RAIN_WIPER_STATE_S /* 557857609 */:
                Iterator<AbsBYDAutoSettingListener> it40 = this.mAbsBydAutoSettingListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onAutoRainWiperStateChanged(settingPacket.getresetMotorPretensionerSeatbelt());
                }
                break;
            case SettingHalId.ID_DYNAMIC_SUSPENSION_MODE_S /* 557857611 */:
                Iterator<AbsBYDAutoSettingListener> it41 = this.mAbsBydAutoSettingListeners.iterator();
                while (it41.hasNext()) {
                    it41.next().onDynamicSuspensionModeChanged(settingPacket.getDynamicSuspensionMode());
                }
                break;
            case SettingHalId.ID_REMOTE_CONTROL_UPWINDOW_STATE_S /* 557857613 */:
                Iterator<AbsBYDAutoSettingListener> it42 = this.mAbsBydAutoSettingListeners.iterator();
                while (it42.hasNext()) {
                    it42.next().onRemoteControlUpwindowStateChanged(settingPacket.getRemoteControlUpwindowState());
                }
                i3 = 419;
                doorHeightSupport = settingPacket.getRemoteControlUpwindowState();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_REMOTE_CONTROL_DOWNWINDOW_STATE_S_48E /* 557857615 */:
                Iterator<AbsBYDAutoSettingListener> it43 = this.mAbsBydAutoSettingListeners.iterator();
                while (it43.hasNext()) {
                    it43.next().onRemoteControlSwitchChanged(settingPacket.getRemoteControlDownwindowState());
                }
                i3 = 395;
                doorHeightSupport = settingPacket.getRemoteControlDownwindowState();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MICRO_SWITCH_LOCK_WINDOW_STATE_R /* 557857617 */:
                Iterator<AbsBYDAutoSettingListener> it44 = this.mAbsBydAutoSettingListeners.iterator();
                while (it44.hasNext()) {
                    it44.next().onMicroSwitchLockWindowStateChanged(settingPacket.getMicroSwitchLockWindowState());
                }
                i3 = 408;
                doorHeightSupport = settingPacket.getMicroSwitchLockWindowState();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MICRO_SWITCH_UNLOCK_WINDOW_STATE_R /* 557857619 */:
                Iterator<AbsBYDAutoSettingListener> it45 = this.mAbsBydAutoSettingListeners.iterator();
                while (it45.hasNext()) {
                    it45.next().onMicroSwitchUnlockWindowStateChanged(settingPacket.getMicroSwitchUnlockWindowState());
                }
                i3 = 406;
                doorHeightSupport = settingPacket.getMicroSwitchUnlockWindowState();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_LOCK_CAR_RISE_WINDOW_R48E /* 557857621 */:
                Iterator<AbsBYDAutoSettingListener> it46 = this.mAbsBydAutoSettingListeners.iterator();
                while (it46.hasNext()) {
                    it46.next().onLockCarRiseWindowStateChange(settingPacket.getLockCarRiseWindow());
                }
                i3 = 403;
                doorHeightSupport = settingPacket.getLockCarRiseWindow();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_LEFT_HOME_LIGHT_DELAY_VALUE_R /* 557857624 */:
                Iterator<AbsBYDAutoSettingListener> it47 = this.mAbsBydAutoSettingListeners.iterator();
                while (it47.hasNext()) {
                    it47.next().onLeftHomeLightDelayValueChanged(settingPacket.getLeftHomeLightDelayValue());
                }
                break;
            case SettingHalId.ID_BACK_HOME_LIGHT_DELAY_VALUE_R /* 557857626 */:
                Iterator<AbsBYDAutoSettingListener> it48 = this.mAbsBydAutoSettingListeners.iterator();
                while (it48.hasNext()) {
                    it48.next().onBackHomeLightDelayValueChanged(settingPacket.getBackHomeLightDelayValue());
                }
                break;
            case SettingHalId.ID_SEAT_HORIZATION_DRIVER /* 557857664 */:
                Iterator<AbsBYDAutoSettingListener> it49 = this.mAbsBydAutoSettingListeners.iterator();
                while (it49.hasNext()) {
                    it49.next().onTurnSeatHorizationChanged(1, 1, settingPacket.getSeatHorizationDriver());
                }
                break;
            case SettingHalId.ID_SEAT_HORIZATION_COPILOT /* 557857665 */:
                Iterator<AbsBYDAutoSettingListener> it50 = this.mAbsBydAutoSettingListeners.iterator();
                while (it50.hasNext()) {
                    it50.next().onTurnSeatHorizationChanged(2, 1, settingPacket.getSeatHorizationCopilot());
                }
                break;
            case SettingHalId.ID_SEAT_HORIZATION_BACK_LEFT /* 557857666 */:
                Iterator<AbsBYDAutoSettingListener> it51 = this.mAbsBydAutoSettingListeners.iterator();
                while (it51.hasNext()) {
                    it51.next().onTurnSeatHorizationChanged(3, 1, settingPacket.getSeatHorizationBackLeft());
                }
                break;
            case SettingHalId.ID_SEAT_HORIZATION_BACK_RIGHT /* 557857667 */:
                Iterator<AbsBYDAutoSettingListener> it52 = this.mAbsBydAutoSettingListeners.iterator();
                while (it52.hasNext()) {
                    it52.next().onTurnSeatHorizationChanged(4, 1, settingPacket.getSeatHorizationBackRight());
                }
                break;
            case SettingHalId.ID_HEADREST_HORIZATION_DRIVER /* 557857688 */:
                Iterator<AbsBYDAutoSettingListener> it53 = this.mAbsBydAutoSettingListeners.iterator();
                while (it53.hasNext()) {
                    it53.next().onHeadrestTurnHorizationChanged(1, 1, settingPacket.getHeadrestHorizationDriver());
                }
                break;
            case SettingHalId.ID_HEADREST_HORIZATION_COPILOT /* 557857689 */:
                Iterator<AbsBYDAutoSettingListener> it54 = this.mAbsBydAutoSettingListeners.iterator();
                while (it54.hasNext()) {
                    it54.next().onHeadrestTurnHorizationChanged(2, 2, settingPacket.getHeadrestHorizationCopilot());
                }
                break;
            case SettingHalId.ID_HEADREST_HORIZATION_BACK_LEFT /* 557857690 */:
                Iterator<AbsBYDAutoSettingListener> it55 = this.mAbsBydAutoSettingListeners.iterator();
                while (it55.hasNext()) {
                    it55.next().onHeadrestTurnHorizationChanged(3, 1, settingPacket.getHeadrestHorizationBackLeft());
                }
                break;
            case SettingHalId.ID_HEADREST_HORIZATION_BACK_RIGHT /* 557857691 */:
                Iterator<AbsBYDAutoSettingListener> it56 = this.mAbsBydAutoSettingListeners.iterator();
                while (it56.hasNext()) {
                    it56.next().onHeadrestTurnHorizationChanged(4, 2, settingPacket.getHeadrestHorizationBackRight());
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
                Iterator<AbsBYDAutoSettingListener> it57 = this.mAbsBydAutoSettingListeners.iterator();
                while (it57.hasNext()) {
                    it57.next().onRemenberPowerOffStateChanged(settingPacket.getRemenberPowerOffState());
                }
                break;
            case SettingHalId.ID_DISINF_STATE_R /* 557857705 */:
                Iterator<AbsBYDAutoSettingListener> it58 = this.mAbsBydAutoSettingListeners.iterator();
                while (it58.hasNext()) {
                    it58.next().onDisinfStateChanged(settingPacket.getDisinfState());
                }
                break;
            case SettingHalId.ID_TRAY_STATE_R /* 557857706 */:
                Iterator<AbsBYDAutoSettingListener> it59 = this.mAbsBydAutoSettingListeners.iterator();
                while (it59.hasNext()) {
                    it59.next().onTrayActionStateChanged(settingPacket.getTrayState());
                }
                break;
            case SettingHalId.ID_REFRIGERATOR_STATE /* 557857708 */:
                Iterator<AbsBYDAutoSettingListener> it60 = this.mAbsBydAutoSettingListeners.iterator();
                while (it60.hasNext()) {
                    it60.next().onRefrigeratorState(settingPacket.getRefrigeratorState());
                }
                break;
            case 557857710:
                Iterator<AbsBYDAutoSettingListener> it61 = this.mAbsBydAutoSettingListeners.iterator();
                while (it61.hasNext()) {
                    it61.next().onIALColorChanged(5, settingPacket.getIALColorStarRing());
                }
                i3 = 2845;
                doorHeightSupport = settingPacket.getIALColorStarRing();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case 557857711:
                Iterator<AbsBYDAutoSettingListener> it62 = this.mAbsBydAutoSettingListeners.iterator();
                while (it62.hasNext()) {
                    it62.next().onIALColorChanged(4, settingPacket.getIALColorStarrySky());
                }
                break;
            case 557857712:
                Iterator<AbsBYDAutoSettingListener> it63 = this.mAbsBydAutoSettingListeners.iterator();
                while (it63.hasNext()) {
                    it63.next().onIALColorChanged(1, settingPacket.getIALColorFront());
                }
                i3 = 1161;
                doorHeightSupport = settingPacket.getIALColorFront();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case 557857713:
                Iterator<AbsBYDAutoSettingListener> it64 = this.mAbsBydAutoSettingListeners.iterator();
                while (it64.hasNext()) {
                    it64.next().onIALColorChanged(2, settingPacket.getIALColorBack());
                }
                i3 = 1157;
                doorHeightSupport = settingPacket.getIALColorBack();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case 557857716:
                Iterator<AbsBYDAutoSettingListener> it65 = this.mAbsBydAutoSettingListeners.iterator();
                while (it65.hasNext()) {
                    it65.next().onIALBrightnessChanged(5, settingPacket.getIALBrightnessStarRing());
                }
                i3 = 2844;
                doorHeightSupport = settingPacket.getIALBrightnessStarRing();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case 557857717:
                Iterator<AbsBYDAutoSettingListener> it66 = this.mAbsBydAutoSettingListeners.iterator();
                while (it66.hasNext()) {
                    it66.next().onIALBrightnessChanged(4, settingPacket.getIALBrightnessStarrySky());
                }
                break;
            case 557857718:
                Iterator<AbsBYDAutoSettingListener> it67 = this.mAbsBydAutoSettingListeners.iterator();
                while (it67.hasNext()) {
                    it67.next().onIALBrightnessChanged(2, settingPacket.getIALBrightnessBack());
                }
                i3 = 1156;
                doorHeightSupport = settingPacket.getIALBrightnessBack();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case 557857719:
                Iterator<AbsBYDAutoSettingListener> it68 = this.mAbsBydAutoSettingListeners.iterator();
                while (it68.hasNext()) {
                    it68.next().onIALBrightnessChanged(1, settingPacket.getIALBrightnessFront());
                }
                i3 = 1160;
                doorHeightSupport = settingPacket.getIALBrightnessFront();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_IAL_AREA_R /* 557857721 */:
                Iterator<AbsBYDAutoSettingListener> it69 = this.mAbsBydAutoSettingListeners.iterator();
                while (it69.hasNext()) {
                    it69.next().onIALAreaChanged(settingPacket.getIALArea());
                }
                i3 = 1171;
                doorHeightSupport = settingPacket.getIALArea();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_DRIVER_DOMAIN_CONTROL /* 557857723 */:
                Iterator<AbsBYDAutoSettingListener> it70 = this.mAbsBydAutoSettingListeners.iterator();
                while (it70.hasNext()) {
                    it70.next().onSeatHeatingStateChanged(1, settingPacket.getSeatHeatingStateDriverDomainControl());
                }
                i3 = 1040;
                doorHeightSupport = settingPacket.getSeatHeatingStateDriverDomainControl();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_COPILOT_ECU /* 557857725 */:
                Iterator<AbsBYDAutoSettingListener> it71 = this.mAbsBydAutoSettingListeners.iterator();
                while (it71.hasNext()) {
                    it71.next().onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotECU());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_BACKLEFT_ECU /* 557857726 */:
                Iterator<AbsBYDAutoSettingListener> it72 = this.mAbsBydAutoSettingListeners.iterator();
                while (it72.hasNext()) {
                    it72.next().onSeatHeatingStateChanged(3, settingPacket.getSeatHeatingStateBackLeftECU());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_BACKRIGHT_ECU /* 557857727 */:
                Iterator<AbsBYDAutoSettingListener> it73 = this.mAbsBydAutoSettingListeners.iterator();
                while (it73.hasNext()) {
                    it73.next().onSeatHeatingStateChanged(4, settingPacket.getSeatHeatingStateBackRightECU());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_COPILOT_L1P /* 557857728 */:
                Iterator<AbsBYDAutoSettingListener> it74 = this.mAbsBydAutoSettingListeners.iterator();
                while (it74.hasNext()) {
                    it74.next().onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotL1P());
                }
                i3 = 1269;
                doorHeightSupport = settingPacket.getSeatHeatingStateCopilotL1P();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE_COPILOTNON_L1P /* 557857729 */:
                Iterator<AbsBYDAutoSettingListener> it75 = this.mAbsBydAutoSettingListeners.iterator();
                while (it75.hasNext()) {
                    it75.next().onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotNonL1P());
                }
                break;
            case 557857730:
                Iterator<AbsBYDAutoSettingListener> it76 = this.mAbsBydAutoSettingListeners.iterator();
                while (it76.hasNext()) {
                    it76.next().onSeatHeatingStateChanged(3, settingPacket.getSeatHeatingStateBackLeftDomainControl());
                }
                i3 = 1283;
                doorHeightSupport = settingPacket.getSeatHeatingStateBackLeftDomainControl();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case 557857731:
                Iterator<AbsBYDAutoSettingListener> it77 = this.mAbsBydAutoSettingListeners.iterator();
                while (it77.hasNext()) {
                    it77.next().onSeatHeatingStateChanged(4, settingPacket.getSeatHeatingStateBackRightDomainControl());
                }
                i3 = 1294;
                doorHeightSupport = settingPacket.getSeatHeatingStateBackRightDomainControl();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_DRIVER_DOMAIN_CONTROL_R /* 557857733 */:
                Iterator<AbsBYDAutoSettingListener> it78 = this.mAbsBydAutoSettingListeners.iterator();
                while (it78.hasNext()) {
                    it78.next().onSeatVentilatingStateChanged(1, settingPacket.getSeatVentilatingStateDriverDomainControl());
                }
                i3 = 1044;
                doorHeightSupport = settingPacket.getSeatVentilatingStateDriverDomainControl();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_BACK_RIGHT_ECU /* 557857737 */:
                Iterator<AbsBYDAutoSettingListener> it79 = this.mAbsBydAutoSettingListeners.iterator();
                while (it79.hasNext()) {
                    it79.next().onSeatVentilatingStateChanged(4, settingPacket.getSeatVentilatingStateBackRightECU());
                }
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_COPILOT_L1P /* 557857738 */:
                Iterator<AbsBYDAutoSettingListener> it80 = this.mAbsBydAutoSettingListeners.iterator();
                while (it80.hasNext()) {
                    it80.next().onSeatVentilatingStateChanged(2, settingPacket.getSeatVentilatingStateCopilotL1P());
                }
                i3 = 1273;
                doorHeightSupport = settingPacket.getSeatVentilatingStateCopilotL1P();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_COPILOTNON_L1P /* 557857739 */:
                Iterator<AbsBYDAutoSettingListener> it81 = this.mAbsBydAutoSettingListeners.iterator();
                while (it81.hasNext()) {
                    it81.next().onSeatVentilatingStateChanged(2, settingPacket.getSeatVentilatingStateCopilotNonL1P());
                }
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_BACK_LEFT_DOMAIN_CONTROL /* 557857740 */:
                Iterator<AbsBYDAutoSettingListener> it82 = this.mAbsBydAutoSettingListeners.iterator();
                while (it82.hasNext()) {
                    it82.next().onSeatVentilatingStateChanged(3, settingPacket.getSeatVentilatingStateBackLeftDomainControl());
                }
                i3 = 1290;
                doorHeightSupport = settingPacket.getSeatVentilatingStateBackLeftDomainControl();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE_BACK_RIGHT_DOMAIN_CONTROL /* 557857741 */:
                Iterator<AbsBYDAutoSettingListener> it83 = this.mAbsBydAutoSettingListeners.iterator();
                while (it83.hasNext()) {
                    it83.next().onSeatVentilatingStateChanged(4, settingPacket.getSeatVentilatingStateBackRightDomainControl());
                }
                i3 = 1301;
                doorHeightSupport = settingPacket.getSeatVentilatingStateBackRightDomainControl();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_LANTERN_LANGUAGE_MODE_R /* 557857744 */:
                Iterator<AbsBYDAutoSettingListener> it84 = this.mAbsBydAutoSettingListeners.iterator();
                while (it84.hasNext()) {
                    it84.next().onLanternLanguageModeChanged(settingPacket.getLanternLanguageMode());
                }
                break;
            case SettingHalId.ID_INS_THEME_R /* 557857745 */:
                Iterator<AbsBYDAutoSettingListener> it85 = this.mAbsBydAutoSettingListeners.iterator();
                while (it85.hasNext()) {
                    it85.next().onINSThemeChanged(settingPacket.getINSTheme());
                }
                break;
            case SettingHalId.ID_HEAD_LAMP_LEVEL_R /* 557857747 */:
                Iterator<AbsBYDAutoSettingListener> it86 = this.mAbsBydAutoSettingListeners.iterator();
                while (it86.hasNext()) {
                    it86.next().onHeadlampLevelChanged(settingPacket.getHeadlampLevel());
                }
                break;
            case SettingHalId.ID_LEFT_HEAD_LAMP_LEVEL /* 557857749 */:
                Iterator<AbsBYDAutoSettingListener> it87 = this.mAbsBydAutoSettingListeners.iterator();
                while (it87.hasNext()) {
                    it87.next().onLeftHeadlampLevelChanged(settingPacket.getLeftHeadlampLevel());
                }
                break;
            case SettingHalId.ID_RIGHT_HEAD_LAMP_LEVEL /* 557857750 */:
                Iterator<AbsBYDAutoSettingListener> it88 = this.mAbsBydAutoSettingListeners.iterator();
                while (it88.hasNext()) {
                    it88.next().onRightHeadlampLevelChanged(settingPacket.getRightHeadlampLevel());
                }
                break;
            case SettingHalId.ID_SMART_WELCOME_LIGHT_STATE_R /* 557857752 */:
                Iterator<AbsBYDAutoSettingListener> it89 = this.mAbsBydAutoSettingListeners.iterator();
                while (it89.hasNext()) {
                    it89.next().onSmartWelcomeLightStateChanged(settingPacket.getSmartWelcomeLightState());
                }
                break;
            case SettingHalId.ID_MASSAGE_LEVEL_LEFT_FRONT /* 557857764 */:
                Iterator<AbsBYDAutoSettingListener> it90 = this.mAbsBydAutoSettingListeners.iterator();
                while (it90.hasNext()) {
                    it90.next().onMassageLevelStateChanged(1, settingPacket.getMassageLevelLeftFront());
                }
                i3 = 1067;
                doorHeightSupport = settingPacket.getMassageLevelLeftFront();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MASSAGE_LEVEL_RIGHT_FRONT /* 557857766 */:
                Iterator<AbsBYDAutoSettingListener> it91 = this.mAbsBydAutoSettingListeners.iterator();
                while (it91.hasNext()) {
                    it91.next().onMassageLevelStateChanged(2, settingPacket.getMassageLevelRightFront());
                }
                i3 = 1070;
                doorHeightSupport = settingPacket.getMassageLevelRightFront();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MASSAGE_LEVEL_LEFT_BACK /* 557857768 */:
                Iterator<AbsBYDAutoSettingListener> it92 = this.mAbsBydAutoSettingListeners.iterator();
                while (it92.hasNext()) {
                    it92.next().onMassageLevelStateChanged(3, settingPacket.getMassageLevelLeftBack());
                }
                i3 = 1286;
                doorHeightSupport = settingPacket.getMassageLevelLeftBack();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MASSAGE_LEVEL_RIGHT_BACK /* 557857771 */:
                Iterator<AbsBYDAutoSettingListener> it93 = this.mAbsBydAutoSettingListeners.iterator();
                while (it93.hasNext()) {
                    it93.next().onMassageLevelStateChanged(4, settingPacket.getMassageLevelRightBack());
                }
                i3 = 1297;
                doorHeightSupport = settingPacket.getMassageLevelRightBack();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MASSAGE_MODE_R /* 557857776 */:
                Iterator<AbsBYDAutoSettingListener> it94 = this.mAbsBydAutoSettingListeners.iterator();
                while (it94.hasNext()) {
                    it94.next().onMassageModeStateChanged(1, settingPacket.getMassageMode());
                }
                i3 = 1068;
                doorHeightSupport = settingPacket.getMassageMode();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MASSAGE_MODE_R_4FA /* 557857778 */:
                Iterator<AbsBYDAutoSettingListener> it95 = this.mAbsBydAutoSettingListeners.iterator();
                while (it95.hasNext()) {
                    it95.next().onMassageModeStateChanged(2, settingPacket.getMassageModeR4Fa());
                }
                i3 = 1071;
                doorHeightSupport = settingPacket.getMassageModeR4Fa();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MASSAGE_MODE_R_4FA_LEFT_BACK /* 557857780 */:
                Iterator<AbsBYDAutoSettingListener> it96 = this.mAbsBydAutoSettingListeners.iterator();
                while (it96.hasNext()) {
                    it96.next().onMassageModeStateChanged(3, settingPacket.getMassageModeR4FaLeftBack());
                }
                i3 = 1288;
                doorHeightSupport = settingPacket.getMassageModeR4FaLeftBack();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_MASSAGE_MODE_R_4FA_LEFT_FRONT /* 557857783 */:
                Iterator<AbsBYDAutoSettingListener> it97 = this.mAbsBydAutoSettingListeners.iterator();
                while (it97.hasNext()) {
                    it97.next().onMassageModeStateChanged(4, settingPacket.getMassageModeR4FaLeftFront());
                }
                i3 = 1299;
                doorHeightSupport = settingPacket.getMassageModeR4FaLeftFront();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_ENERGY_FEEDBACK_R_341 /* 557857788 */:
                Iterator<AbsBYDAutoSettingListener> it98 = this.mAbsBydAutoSettingListeners.iterator();
                while (it98.hasNext()) {
                    it98.next().onEnergyFeedbackStrengthChanged(settingPacket.getEnergyFeedbackR341());
                }
                break;
            case SettingHalId.ID_SOC_TARGET_R /* 557857790 */:
                Iterator<AbsBYDAutoSettingListener> it99 = this.mAbsBydAutoSettingListeners.iterator();
                while (it99.hasNext()) {
                    it99.next().onSOCTargetRangeChange(settingPacket.getSOCTarget());
                }
                break;
            case SettingHalId.ID_CHARGING_PORT_R_27C /* 557857792 */:
                Iterator<AbsBYDAutoSettingListener> it100 = this.mAbsBydAutoSettingListeners.iterator();
                while (it100.hasNext()) {
                    it100.next().onChargingPortSwitchChanged(settingPacket.getChargingPort());
                }
                break;
            case SettingHalId.ID_AUTO_EXTERNAL_REAR_MIRROR_FOLLOW_UP_SWITCH_R /* 557857796 */:
                Iterator<AbsBYDAutoSettingListener> it101 = this.mAbsBydAutoSettingListeners.iterator();
                while (it101.hasNext()) {
                    it101.next().onAutoExternalRearMirrorFollowUpStateChanged(settingPacket.getAutoExternalRearMirrorFollowUpSwitch());
                }
                break;
            case SettingHalId.ID_OVERSPEED_LOCK_R /* 557857799 */:
                Iterator<AbsBYDAutoSettingListener> it102 = this.mAbsBydAutoSettingListeners.iterator();
                while (it102.hasNext()) {
                    it102.next().onOverSpeedLockStateChanged(settingPacket.getOverspeedLock());
                }
                break;
            case SettingHalId.ID_STEER_ASSIS_R /* 557857800 */:
                Iterator<AbsBYDAutoSettingListener> it103 = this.mAbsBydAutoSettingListeners.iterator();
                while (it103.hasNext()) {
                    it103.next().onSteerAssisModeChanged(settingPacket.getSteerAssis());
                }
                break;
            case SettingHalId.ID_STEER_ASSIS_SUPPORTED /* 557857802 */:
                Iterator<AbsBYDAutoSettingListener> it104 = this.mAbsBydAutoSettingListeners.iterator();
                while (it104.hasNext()) {
                    it104.next().onSteerAssisSupportChanged(settingPacket.getSteerAssisSupportedR());
                }
                break;
            case SettingHalId.ID_ON_TRAY_STATE_CHANGED /* 557857804 */:
                Iterator<AbsBYDAutoSettingListener> it105 = this.mAbsBydAutoSettingListeners.iterator();
                while (it105.hasNext()) {
                    it105.next().onTrayStateChanged(0, settingPacket.getonTrayStateChanged());
                }
                break;
            case 557857805:
                Iterator<AbsBYDAutoSettingListener> it106 = this.mAbsBydAutoSettingListeners.iterator();
                while (it106.hasNext()) {
                    it106.next().onRecoverOrSaveParamsChanged(0, 1, 1, settingPacket.getonSaveSeatParamsAllChangedPlace5());
                }
                i3 = 1305;
                doorHeightSupport = settingPacket.getonSaveSeatParamsAllChangedPlace5();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case 557857806:
                Iterator<AbsBYDAutoSettingListener> it107 = this.mAbsBydAutoSettingListeners.iterator();
                while (it107.hasNext()) {
                    it107.next().onRecoverOrSaveParamsChanged(0, 1, 3, settingPacket.getonSaveSeatParamsAllChangedPlace7());
                }
                break;
            case SettingHalId.ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_3CA /* 557857807 */:
                Iterator<AbsBYDAutoSettingListener> it108 = this.mAbsBydAutoSettingListeners.iterator();
                while (it108.hasNext()) {
                    it108.next().onRecoverOrSaveParamsChanged(0, 2, 3, settingPacket.getonSaveSeatParamsAllChanged3Ca());
                }
                i3 = 760;
                doorHeightSupport = settingPacket.getonSaveSeatParamsAllChanged3Ca();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_402 /* 557857808 */:
                Iterator<AbsBYDAutoSettingListener> it109 = this.mAbsBydAutoSettingListeners.iterator();
                while (it109.hasNext()) {
                    it109.next().onRecoverOrSaveParamsChanged(0, 3, 3, settingPacket.getonSaveSeatParamsAllChanged402());
                }
                i3 = 733;
                doorHeightSupport = settingPacket.getonSaveSeatParamsAllChanged402();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_42F /* 557857809 */:
                Iterator<AbsBYDAutoSettingListener> it110 = this.mAbsBydAutoSettingListeners.iterator();
                while (it110.hasNext()) {
                    it110.next().onRecoverOrSaveParamsChanged(0, 4, 3, settingPacket.getonSaveSeatParamsAllChanged42F());
                }
                i3 = 777;
                doorHeightSupport = settingPacket.getonSaveSeatParamsAllChanged42F();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_STEERING_WHEEL_HEATING_STATE_R /* 557857813 */:
                Iterator<AbsBYDAutoSettingListener> it111 = this.mAbsBydAutoSettingListeners.iterator();
                while (it111.hasNext()) {
                    it111.next().onSteeringWheelHeatingStateChanged(settingPacket.getSteeringWheelHeatingState());
                }
                break;
            case SettingHalId.ID_LOCK_OFF_R /* 557857814 */:
                Iterator<AbsBYDAutoSettingListener> it112 = this.mAbsBydAutoSettingListeners.iterator();
                while (it112.hasNext()) {
                    it112.next().onLockOffChanged(settingPacket.getLockOff());
                }
                break;
            case SettingHalId.ID_COM_SPEED_DOWN_STATER /* 557857833 */:
                Iterator<AbsBYDAutoSettingListener> it113 = this.mAbsBydAutoSettingListeners.iterator();
                while (it113.hasNext()) {
                    it113.next().onComSpeedDownStateChanged(settingPacket.getComSpeedDownState());
                }
                break;
            case SettingHalId.ID_H_SPEED_AUTO_CLOSE_STATER /* 557857835 */:
                Iterator<AbsBYDAutoSettingListener> it114 = this.mAbsBydAutoSettingListeners.iterator();
                while (it114.hasNext()) {
                    it114.next().onHSpeedAutoCloseStateChanged(settingPacket.getHSpeedAutoCloseState());
                }
                break;
            case SettingHalId.ID_COM_SPEED_UP_STATER /* 557857838 */:
                Iterator<AbsBYDAutoSettingListener> it115 = this.mAbsBydAutoSettingListeners.iterator();
                while (it115.hasNext()) {
                    it115.next().onComSpeedUpStateChanged(settingPacket.getComSpeedUpState());
                }
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED2 /* 557857847 */:
                Iterator<AbsBYDAutoSettingListener> it116 = this.mAbsBydAutoSettingListeners.iterator();
                while (it116.hasNext()) {
                    it116.next().onRecoverOrSaveParamsChanged(0, 1, 2, settingPacket.getRecoverOrSaveParamsChanged2());
                }
                i3 = 1354;
                doorHeightSupport = settingPacket.getRecoverOrSaveParamsChanged2();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED3 /* 557857848 */:
                Iterator<AbsBYDAutoSettingListener> it117 = this.mAbsBydAutoSettingListeners.iterator();
                while (it117.hasNext()) {
                    it117.next().onRecoverOrSaveParamsChanged(0, 1, 1, settingPacket.getRecoverOrSaveParamsChanged3());
                }
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED4 /* 557857849 */:
                Iterator<AbsBYDAutoSettingListener> it118 = this.mAbsBydAutoSettingListeners.iterator();
                while (it118.hasNext()) {
                    it118.next().onRecoverOrSaveParamsChanged(0, 1, 2, settingPacket.getRecoverOrSaveParamsChanged4());
                }
                break;
            case 557857850:
                Iterator<AbsBYDAutoSettingListener> it119 = this.mAbsBydAutoSettingListeners.iterator();
                while (it119.hasNext()) {
                    it119.next().onRecoverOrSaveParamsChanged(0, 1, 3, settingPacket.getRecoverOrSaveParamsChanged5());
                }
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED6 /* 557857851 */:
                Iterator<AbsBYDAutoSettingListener> it120 = this.mAbsBydAutoSettingListeners.iterator();
                while (it120.hasNext()) {
                    it120.next().onRecoverOrSaveParamsChanged(1, 3, 3, settingPacket.getRecoverOrSaveParamsChanged6());
                }
                break;
            case SettingHalId.ID_RECOVERORSAVEPARAMSCHANGED7 /* 557857852 */:
                Iterator<AbsBYDAutoSettingListener> it121 = this.mAbsBydAutoSettingListeners.iterator();
                while (it121.hasNext()) {
                    it121.next().onRecoverOrSaveParamsChanged(1, 4, 3, settingPacket.getRecoverOrSaveParamsChanged7());
                }
                break;
            case SettingHalId.ID_HUD_PATTERN_R /* 557857853 */:
                Iterator<AbsBYDAutoSettingListener> it122 = this.mAbsBydAutoSettingListeners.iterator();
                while (it122.hasNext()) {
                    it122.next().onHudPatternChanged(settingPacket.getHudPatternR());
                }
                break;
            case SettingHalId.ID_HUD_SAFE_DRIVE_STATE_R /* 557857855 */:
                Iterator<AbsBYDAutoSettingListener> it123 = this.mAbsBydAutoSettingListeners.iterator();
                while (it123.hasNext()) {
                    it123.next().onHudSafeDriveStateChanged(settingPacket.getHudSafeDriveStateR());
                }
                break;
            case SettingHalId.ID_HUD_NAVIGATION_STATE_R /* 557857857 */:
                Iterator<AbsBYDAutoSettingListener> it124 = this.mAbsBydAutoSettingListeners.iterator();
                while (it124.hasNext()) {
                    it124.next().onHudNavigationStateChanged(settingPacket.getHudNavigationStateR());
                }
                break;
            case SettingHalId.ID_HUD_SCREEN_PROJECTION_STATE_R /* 557857859 */:
                Iterator<AbsBYDAutoSettingListener> it125 = this.mAbsBydAutoSettingListeners.iterator();
                while (it125.hasNext()) {
                    it125.next().onHudSPStateChanged(settingPacket.getHudScreenProjectionStateR());
                }
                break;
            case SettingHalId.ID_RUNING_CLOSE_DOOR_STATE_R /* 557857862 */:
                Iterator<AbsBYDAutoSettingListener> it126 = this.mAbsBydAutoSettingListeners.iterator();
                while (it126.hasNext()) {
                    it126.next().onRuningCloseDoorStateChanged(settingPacket.getRuningCloseDoorStateR());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE2_BACK_LEFT_R /* 557857864 */:
                Iterator<AbsBYDAutoSettingListener> it127 = this.mAbsBydAutoSettingListeners.iterator();
                while (it127.hasNext()) {
                    it127.next().onSeatHeatingState2Changed(3, settingPacket.getSeatHeatingState2BackLeftR());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_STATE2_BACK_RIGHT_R /* 557857865 */:
                Iterator<AbsBYDAutoSettingListener> it128 = this.mAbsBydAutoSettingListeners.iterator();
                while (it128.hasNext()) {
                    it128.next().onSeatHeatingState2Changed(4, settingPacket.getSeatHeatingState2BackRightR());
                }
                break;
            case SettingHalId.ID_SEAT_HEATING_NOT_L1_P_STATE_R /* 557857866 */:
                Iterator<AbsBYDAutoSettingListener> it129 = this.mAbsBydAutoSettingListeners.iterator();
                while (it129.hasNext()) {
                    it129.next().onSeatHeatingNotL1PStateChanged(settingPacket.getSeatHeatingNotL1PStateR());
                }
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE2_BACK_LEFT_R /* 557857867 */:
                Iterator<AbsBYDAutoSettingListener> it130 = this.mAbsBydAutoSettingListeners.iterator();
                while (it130.hasNext()) {
                    it130.next().onSeatVentilatingState2Changed(3, settingPacket.getSeatVentilatingState2BackLeftR());
                }
                break;
            case SettingHalId.ID_SEAT_VENTILATING_STATE2_BACK_RIGHT_R /* 557857868 */:
                Iterator<AbsBYDAutoSettingListener> it131 = this.mAbsBydAutoSettingListeners.iterator();
                while (it131.hasNext()) {
                    it131.next().onSeatVentilatingState2Changed(4, settingPacket.getSeatVentilatingState2BackRightR());
                }
                break;
            case SettingHalId.ID_SEAT_VENTILATING_NOT_L1_P_STATE_R /* 557857869 */:
                Iterator<AbsBYDAutoSettingListener> it132 = this.mAbsBydAutoSettingListeners.iterator();
                while (it132.hasNext()) {
                    it132.next().onSeatVentilatingNotL1PStateChanged(settingPacket.getSeatVentilatingNotL1PStateR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_STATE_DOMAIN_R /* 557857870 */:
                Iterator<AbsBYDAutoSettingListener> it133 = this.mAbsBydAutoSettingListeners.iterator();
                while (it133.hasNext()) {
                    it133.next().on5LevelSeatVentilatingStateChanged(1, settingPacket.get5LevelSeatVentilatingStateDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_STATE_PASSENGER_R /* 557857871 */:
                Iterator<AbsBYDAutoSettingListener> it134 = this.mAbsBydAutoSettingListeners.iterator();
                while (it134.hasNext()) {
                    it134.next().on5LevelSeatVentilatingStateChanged(2, settingPacket.get5LevelSeatVentilatingStatePassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_LEFT_R /* 557857872 */:
                Iterator<AbsBYDAutoSettingListener> it135 = this.mAbsBydAutoSettingListeners.iterator();
                while (it135.hasNext()) {
                    it135.next().on5LevelSeatVentilatingStateChanged(3, settingPacket.get5LevelSeatVentilatingStateBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_RIGHT_R /* 557857873 */:
                Iterator<AbsBYDAutoSettingListener> it136 = this.mAbsBydAutoSettingListeners.iterator();
                while (it136.hasNext()) {
                    it136.next().on5LevelSeatVentilatingStateChanged(4, settingPacket.get5LevelSeatVentilatingStateBackRightR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_STATE_DOMAIN_R /* 557857878 */:
                Iterator<AbsBYDAutoSettingListener> it137 = this.mAbsBydAutoSettingListeners.iterator();
                while (it137.hasNext()) {
                    it137.next().on5LevelSeatHeatingStateChanged(1, settingPacket.get5LevelSeatHeatingStateDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_STATE_PASSENGER_R /* 557857879 */:
                Iterator<AbsBYDAutoSettingListener> it138 = this.mAbsBydAutoSettingListeners.iterator();
                while (it138.hasNext()) {
                    it138.next().on5LevelSeatHeatingStateChanged(2, settingPacket.get5LevelSeatHeatingStatePassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_STATE_BACK_LEFT_R /* 557857880 */:
                Iterator<AbsBYDAutoSettingListener> it139 = this.mAbsBydAutoSettingListeners.iterator();
                while (it139.hasNext()) {
                    it139.next().on5LevelSeatHeatingStateChanged(3, settingPacket.get5LevelSeatHeatingStateBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_STATE_BACK_RIGHT_R /* 557857881 */:
                Iterator<AbsBYDAutoSettingListener> it140 = this.mAbsBydAutoSettingListeners.iterator();
                while (it140.hasNext()) {
                    it140.next().on5LevelSeatHeatingStateChanged(4, settingPacket.get5LevelSeatHeatingStateBackRightR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_MODE_DOMAIN_R /* 557857886 */:
                Iterator<AbsBYDAutoSettingListener> it141 = this.mAbsBydAutoSettingListeners.iterator();
                while (it141.hasNext()) {
                    it141.next().on5LevelSeatVentilatingModeChanged(1, settingPacket.get5LevelSeatVentilatingModeDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_MODE_PASSENGER_R /* 557857887 */:
                Iterator<AbsBYDAutoSettingListener> it142 = this.mAbsBydAutoSettingListeners.iterator();
                while (it142.hasNext()) {
                    it142.next().on5LevelSeatVentilatingModeChanged(2, settingPacket.get5LevelSeatVentilatingModePassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_LEFT_R /* 557857888 */:
                Iterator<AbsBYDAutoSettingListener> it143 = this.mAbsBydAutoSettingListeners.iterator();
                while (it143.hasNext()) {
                    it143.next().on5LevelSeatVentilatingModeChanged(3, settingPacket.get5LevelSeatVentilatingModeBackLeftR());
                }
                i3 = 3471;
                doorHeightSupport = settingPacket.get5LevelSeatVentilatingModeBackLeftR();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_RIGHT_R /* 557857889 */:
                Iterator<AbsBYDAutoSettingListener> it144 = this.mAbsBydAutoSettingListeners.iterator();
                while (it144.hasNext()) {
                    it144.next().on5LevelSeatVentilatingModeChanged(4, settingPacket.get5LevelSeatVentilatingModeBackRightR());
                }
                i3 = 3494;
                doorHeightSupport = settingPacket.get5LevelSeatVentilatingModeBackRightR();
                caseFeatureIds(i3, Integer.valueOf(doorHeightSupport));
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_MODE_DOMAIN_R /* 557857894 */:
                Iterator<AbsBYDAutoSettingListener> it145 = this.mAbsBydAutoSettingListeners.iterator();
                while (it145.hasNext()) {
                    it145.next().on5LevelSeatHeatingModeChanged(1, settingPacket.get5LevelSeatHeatingModeDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_MODE_PASSENGER_R /* 557857895 */:
                Iterator<AbsBYDAutoSettingListener> it146 = this.mAbsBydAutoSettingListeners.iterator();
                while (it146.hasNext()) {
                    it146.next().on5LevelSeatHeatingModeChanged(2, settingPacket.get5LevelSeatHeatingModePassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_MODE_BACK_LEFT_R /* 557857896 */:
                Iterator<AbsBYDAutoSettingListener> it147 = this.mAbsBydAutoSettingListeners.iterator();
                while (it147.hasNext()) {
                    it147.next().on5LevelSeatHeatingModeChanged(3, settingPacket.get5LevelSeatHeatingModeBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_MODE_BACK_RIGHT_R /* 557857897 */:
                Iterator<AbsBYDAutoSettingListener> it148 = this.mAbsBydAutoSettingListeners.iterator();
                while (it148.hasNext()) {
                    it148.next().on5LevelSeatHeatingModeChanged(4, settingPacket.get5LevelSeatHeatingModeBackRightR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_LEVEL_DOMAIN_R /* 557857902 */:
                Iterator<AbsBYDAutoSettingListener> it149 = this.mAbsBydAutoSettingListeners.iterator();
                while (it149.hasNext()) {
                    it149.next().on5LevelSeatVentilatingLevelChanged(1, settingPacket.get5LevelSeatVentilatingLevelDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_LEVEL_PASSENGER_R /* 557857903 */:
                Iterator<AbsBYDAutoSettingListener> it150 = this.mAbsBydAutoSettingListeners.iterator();
                while (it150.hasNext()) {
                    it150.next().on5LevelSeatVentilatingLevelChanged(2, settingPacket.get5LevelSeatVentilatingLevelPassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_LEFT_R /* 557857904 */:
                Iterator<AbsBYDAutoSettingListener> it151 = this.mAbsBydAutoSettingListeners.iterator();
                while (it151.hasNext()) {
                    it151.next().on5LevelSeatVentilatingLevelChanged(3, settingPacket.get5LevelSeatVentilatingLevelBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_RIGHT_R /* 557857905 */:
                Iterator<AbsBYDAutoSettingListener> it152 = this.mAbsBydAutoSettingListeners.iterator();
                while (it152.hasNext()) {
                    it152.next().on5LevelSeatVentilatingLevelChanged(4, settingPacket.get5LevelSeatVentilatingLevelBackRightR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_LEVEL_DOMAIN_R /* 557857910 */:
                Iterator<AbsBYDAutoSettingListener> it153 = this.mAbsBydAutoSettingListeners.iterator();
                while (it153.hasNext()) {
                    it153.next().on5LevelSeatHeatingLevelChanged(1, settingPacket.get5LevelSeatHeatingLevelDomainR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_LEVEL_PASSENGER_R /* 557857911 */:
                Iterator<AbsBYDAutoSettingListener> it154 = this.mAbsBydAutoSettingListeners.iterator();
                while (it154.hasNext()) {
                    it154.next().on5LevelSeatHeatingLevelChanged(2, settingPacket.get5LevelSeatHeatingLevelPassengerR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_LEFT_R /* 557857912 */:
                Iterator<AbsBYDAutoSettingListener> it155 = this.mAbsBydAutoSettingListeners.iterator();
                while (it155.hasNext()) {
                    it155.next().on5LevelSeatHeatingLevelChanged(3, settingPacket.get5LevelSeatHeatingLevelBackLeftR());
                }
                break;
            case SettingHalId.ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_RIGHT_R /* 557857913 */:
                Iterator<AbsBYDAutoSettingListener> it156 = this.mAbsBydAutoSettingListeners.iterator();
                while (it156.hasNext()) {
                    it156.next().on5LevelSeatHeatingLevelChanged(4, settingPacket.get5LevelSeatHeatingLevelBackRightR());
                }
                break;
            case SettingHalId.ID_STEERING_WHEEL_HEATING_GEAR_R /* 557857919 */:
                Iterator<AbsBYDAutoSettingListener> it157 = this.mAbsBydAutoSettingListeners.iterator();
                while (it157.hasNext()) {
                    it157.next().onSteeringWheelHeatingGearChanged(settingPacket.getSteeringWheelHeatingGearR());
                }
                break;
            case SettingHalId.ID_STEERING_WHEEL_HEATING_AUTO_STATE_R /* 557857921 */:
                Iterator<AbsBYDAutoSettingListener> it158 = this.mAbsBydAutoSettingListeners.iterator();
                while (it158.hasNext()) {
                    it158.next().onSteeringWheelHeatingAutoStateChanged(settingPacket.getSteeringWheelHeatingAutoStateR());
                }
                break;
            case SettingHalId.ID_ECU_MASSAGE_LEVEL_BACK_LEFT_R /* 557857922 */:
                Iterator<AbsBYDAutoSettingListener> it159 = this.mAbsBydAutoSettingListeners.iterator();
                while (it159.hasNext()) {
                    it159.next().onMassageLevelStateChanged(3, settingPacket.getEcuMassageLevelBackLeftR());
                }
                break;
            case SettingHalId.ID_ECU_MASSAGE_LEVEL_BACK_RIGHT_R /* 557857923 */:
                Iterator<AbsBYDAutoSettingListener> it160 = this.mAbsBydAutoSettingListeners.iterator();
                while (it160.hasNext()) {
                    it160.next().onMassageLevelStateChanged(4, settingPacket.getEcuMassageLevelBackRightR());
                }
                break;
            case SettingHalId.ID_ECU_MASSAGE_MODE_BACK_LEFT_R /* 557857928 */:
                Iterator<AbsBYDAutoSettingListener> it161 = this.mAbsBydAutoSettingListeners.iterator();
                while (it161.hasNext()) {
                    it161.next().onMassageModeStateChanged(3, settingPacket.getEcuMassageModeBackLeftR());
                }
                break;
            case SettingHalId.ID_ECU_MASSAGE_MODE_BACK_RIGHT_R /* 557857929 */:
                Iterator<AbsBYDAutoSettingListener> it162 = this.mAbsBydAutoSettingListeners.iterator();
                while (it162.hasNext()) {
                    it162.next().onMassageModeStateChanged(4, settingPacket.getEcuMassageModeBackRightR());
                }
                break;
            case SettingHalId.ID_HS_MASSAGE_STATE_DOMAIN_R /* 557857938 */:
                Iterator<AbsBYDAutoSettingListener> it163 = this.mAbsBydAutoSettingListeners.iterator();
                while (it163.hasNext()) {
                    it163.next().onHsMassageStateChanged(1, settingPacket.getHsMassageStateDomainR());
                }
                break;
            case SettingHalId.ID_HEV_CHARGING_PORT_R /* 557857944 */:
                Iterator<AbsBYDAutoSettingListener> it164 = this.mAbsBydAutoSettingListeners.iterator();
                while (it164.hasNext()) {
                    it164.next().onHevChargingPortSwitchChanged(settingPacket.getHevChargingPortR());
                }
                break;
            case SettingHalId.ID_KEY_VIBRATOR_LEVEL_R /* 557857945 */:
                Iterator<AbsBYDAutoSettingListener> it165 = this.mAbsBydAutoSettingListeners.iterator();
                while (it165.hasNext()) {
                    it165.next().onKeyVibratorLevelChanged(settingPacket.getKeyVibratorLevelR());
                }
                break;
            case 557857947:
                Iterator<AbsBYDAutoSettingListener> it166 = this.mAbsBydAutoSettingListeners.iterator();
                while (it166.hasNext()) {
                    it166.next().onIVIProfileSettingsChanged(settingPacket.getIviProfileSettingsR());
                }
                break;
            case SettingHalId.ID_SAFE_BELT_WARN_STATER /* 557858305 */:
                Iterator<AbsBYDAutoSettingListener> it167 = this.mAbsBydAutoSettingListeners.iterator();
                while (it167.hasNext()) {
                    it167.next().onSafeBeltWarnStateChanged(settingPacket.getSafeBeltWarnState());
                }
                break;
            case SettingHalId.ID_P_A_B_CODE_R /* 557858330 */:
                Iterator<AbsBYDAutoSettingListener> it168 = this.mAbsBydAutoSettingListeners.iterator();
                while (it168.hasNext()) {
                    it168.next().onPABStateCodeChanged(settingPacket.getPabCodeR());
                }
                break;
            case SettingHalId.ID_MISS_PACKAGE_COUNT_R /* 557858331 */:
                Iterator<AbsBYDAutoSettingListener> it169 = this.mAbsBydAutoSettingListeners.iterator();
                while (it169.hasNext()) {
                    it169.next().onMissPackageCountChanged(settingPacket.getMissPackageCountR());
                }
                break;
            case SettingHalId.ID_SCREEN_ANGLE_R /* 557858334 */:
                Iterator<AbsBYDAutoSettingListener> it170 = this.mAbsBydAutoSettingListeners.iterator();
                while (it170.hasNext()) {
                    it170.next().onScreenAngleChanged(settingPacket.getScreenAngleR());
                }
                break;
            case SettingHalId.ID_SCREEN_ROTATION_ACTION_R /* 557858336 */:
                Iterator<AbsBYDAutoSettingListener> it171 = this.mAbsBydAutoSettingListeners.iterator();
                while (it171.hasNext()) {
                    it171.next().onScreenRotationActionChanged(settingPacket.getScreenRotationActionR());
                }
                break;
            case SettingHalId.ID_SCREEN_ANTI_PINCH_STATE_R /* 557858337 */:
                Iterator<AbsBYDAutoSettingListener> it172 = this.mAbsBydAutoSettingListeners.iterator();
                while (it172.hasNext()) {
                    it172.next().onScreenAntiPinchStateChanged(settingPacket.getScreenAntiPinchStateR());
                }
                break;
            case SettingHalId.ID_SCREEN_ANGLE_VALUE_R /* 557858338 */:
                Iterator<AbsBYDAutoSettingListener> it173 = this.mAbsBydAutoSettingListeners.iterator();
                while (it173.hasNext()) {
                    it173.next().onScreenAngleValueChanged(settingPacket.getScreenAngleValueR());
                }
                break;
            case SettingHalId.ID_SCREEN_ROTATION_OVER_HOT_STATE_R /* 557858340 */:
                Iterator<AbsBYDAutoSettingListener> it174 = this.mAbsBydAutoSettingListeners.iterator();
                while (it174.hasNext()) {
                    it174.next().onScreenRotationOverHotStateChanged(settingPacket.getScreenRotationOverHotStateR());
                }
                break;
            case SettingHalId.ID_P_A_B_STATE_R /* 557858345 */:
                Iterator<AbsBYDAutoSettingListener> it175 = this.mAbsBydAutoSettingListeners.iterator();
                while (it175.hasNext()) {
                    it175.next().onPABStateChanged(settingPacket.getPabStateR());
                }
                break;
            case SettingHalId.ID_SOMETHING_LEFT_INREFRIGERATOR_R /* 557858354 */:
                Iterator<AbsBYDAutoSettingListener> it176 = this.mAbsBydAutoSettingListeners.iterator();
                while (it176.hasNext()) {
                    it176.next().onSomethingLeftInRefrigeratorNotice(settingPacket.getSomethingLeftInRefrigerator());
                }
                break;
            case SettingHalId.ID_ONE_LEVEL_SEAT_HEATING_STATE_R /* 557858355 */:
                Iterator<AbsBYDAutoSettingListener> it177 = this.mAbsBydAutoSettingListeners.iterator();
                while (it177.hasNext()) {
                    it177.next().on1LevelSeatHeatingStateChanged(1, settingPacket.getOneLevelSeatHeatingStateR());
                }
                break;
            case SettingHalId.ID_TRAILER_MODE_STATE_R /* 557858357 */:
                Iterator<AbsBYDAutoSettingListener> it178 = this.mAbsBydAutoSettingListeners.iterator();
                while (it178.hasNext()) {
                    it178.next().onTrailerModeStateChanged(settingPacket.getTrailerModeStateR());
                }
                break;
            case SettingHalId.ID_TRAILER_LIGHT_CHECK_STATE_R /* 557858358 */:
                Iterator<AbsBYDAutoSettingListener> it179 = this.mAbsBydAutoSettingListeners.iterator();
                while (it179.hasNext()) {
                    it179.next().onTrailerLightCheckStateChanged(settingPacket.getTrailerLightCheckStateR());
                }
                break;
            case SettingHalId.ID_REAR_VIEW_ECU_MIRROR_FLIP_STATE_R /* 557858360 */:
                Iterator<AbsBYDAutoSettingListener> it180 = this.mAbsBydAutoSettingListeners.iterator();
                while (it180.hasNext()) {
                    it180.next().onRearViewEcuMirrorFlipStateChanged(settingPacket.getRearViewEcuMirrorFlipStateR());
                }
                break;
            case SettingHalId.ID_DY_HEIGHT_TOGGLE_STATE_R /* 557858361 */:
                Iterator<AbsBYDAutoSettingListener> it181 = this.mAbsBydAutoSettingListeners.iterator();
                while (it181.hasNext()) {
                    it181.next().onDyHeightToggleStateChanged(settingPacket.getDyHeightToggleStateR());
                }
                break;
            case SettingHalId.ID_WELCOME_STATE_R /* 557858362 */:
                Iterator<AbsBYDAutoSettingListener> it182 = this.mAbsBydAutoSettingListeners.iterator();
                while (it182.hasNext()) {
                    it182.next().onWelcomeStateChanged(settingPacket.getWelcomeStateR());
                }
                break;
            case SettingHalId.ID_WELCOME_ACTIVE_R /* 557858363 */:
                Iterator<AbsBYDAutoSettingListener> it183 = this.mAbsBydAutoSettingListeners.iterator();
                while (it183.hasNext()) {
                    it183.next().onWelcomeActiveChanged(settingPacket.getWelcomeActiveR());
                }
                break;
            case SettingHalId.ID_TAIL_BOX_TAKE_STATE_R /* 557858364 */:
                Iterator<AbsBYDAutoSettingListener> it184 = this.mAbsBydAutoSettingListeners.iterator();
                while (it184.hasNext()) {
                    it184.next().onTailBoxTakeStateChanged(settingPacket.getTailBoxTakeStateR());
                }
                break;
            case SettingHalId.ID_CAMPING_BLANCE_STATE_R /* 557858365 */:
                Iterator<AbsBYDAutoSettingListener> it185 = this.mAbsBydAutoSettingListeners.iterator();
                while (it185.hasNext()) {
                    it185.next().onCampintBlacneStateChanged(settingPacket.getCampingBlanceStateR());
                }
                break;
            case SettingHalId.ID_CAMPING_BLANCE_ACTION_R /* 557858366 */:
                Iterator<AbsBYDAutoSettingListener> it186 = this.mAbsBydAutoSettingListeners.iterator();
                while (it186.hasNext()) {
                    it186.next().onCampingBlanceActionChanged(settingPacket.getCampingBlanceActionR());
                }
                break;
            case SettingHalId.ID_LIFT_MODE_STATE_R /* 557858367 */:
                Iterator<AbsBYDAutoSettingListener> it187 = this.mAbsBydAutoSettingListeners.iterator();
                while (it187.hasNext()) {
                    it187.next().onLiftModeStateChanged(settingPacket.getLiftModeStateR());
                }
                break;
            case SettingHalId.ID_FIXED_STATE_R /* 557858368 */:
                Iterator<AbsBYDAutoSettingListener> it188 = this.mAbsBydAutoSettingListeners.iterator();
                while (it188.hasNext()) {
                    it188.next().onFixedStateChanged(settingPacket.getFixedStateR());
                }
                break;
            case SettingHalId.ID_SPEED_SUPPORTED_TURN_R /* 557858369 */:
                Iterator<AbsBYDAutoSettingListener> it189 = this.mAbsBydAutoSettingListeners.iterator();
                while (it189.hasNext()) {
                    it189.next().onSpeedSupportedTurn(settingPacket.getSpeedSupportedTurnR());
                }
                break;
            case SettingHalId.ID_MODE_SUPPORTED_HTURN_R /* 557858370 */:
                Iterator<AbsBYDAutoSettingListener> it190 = this.mAbsBydAutoSettingListeners.iterator();
                while (it190.hasNext()) {
                    it190.next().onModeSupportedHturnChanged(settingPacket.getModeSupportedHturnR());
                }
                break;
            case SettingHalId.ID_WELCOME_SUPPORTED_R /* 557858371 */:
                Iterator<AbsBYDAutoSettingListener> it191 = this.mAbsBydAutoSettingListeners.iterator();
                while (it191.hasNext()) {
                    it191.next().onWelcomeSupportedChanged(settingPacket.getWelcomeSupportedR());
                }
                break;
            case SettingHalId.ID_TAIL_BOX_TAKE_SUPPORTED_R /* 557858372 */:
                Iterator<AbsBYDAutoSettingListener> it192 = this.mAbsBydAutoSettingListeners.iterator();
                while (it192.hasNext()) {
                    it192.next().onTailBoxTakeSupportedChanged(settingPacket.getTailBoxTakeSupportedR());
                }
                break;
            case SettingHalId.ID_TRAILER_MODE_CODE_R /* 557858373 */:
                Iterator<AbsBYDAutoSettingListener> it193 = this.mAbsBydAutoSettingListeners.iterator();
                while (it193.hasNext()) {
                    it193.next().onTrailerModeCodeChanged(settingPacket.getTrailerModeCodeR());
                }
                break;
            case SettingHalId.ID_C_V_C_MODE_R /* 557858374 */:
                Iterator<AbsBYDAutoSettingListener> it194 = this.mAbsBydAutoSettingListeners.iterator();
                while (it194.hasNext()) {
                    it194.next().onCVCModeChanged(settingPacket.getCvcModeR());
                }
                break;
            case SettingHalId.ID_CAMPING_BLANCE_SUPPORTED_R /* 557858375 */:
                Iterator<AbsBYDAutoSettingListener> it195 = this.mAbsBydAutoSettingListeners.iterator();
                while (it195.hasNext()) {
                    it195.next().onCampingBlanceSupportedChanged(settingPacket.getCampingBlanceSupportedR());
                }
                break;
            case SettingHalId.ID_REAR_SEAT_GREET_STATE_R /* 557858376 */:
                Iterator<AbsBYDAutoSettingListener> it196 = this.mAbsBydAutoSettingListeners.iterator();
                while (it196.hasNext()) {
                    it196.next().onRearSeatGreetStateChanged(settingPacket.getRearSeatGreetStateR());
                }
                break;
            case SettingHalId.ID_DY_HEIGHT_TURN_ACTION_R /* 557858377 */:
                Iterator<AbsBYDAutoSettingListener> it197 = this.mAbsBydAutoSettingListeners.iterator();
                while (it197.hasNext()) {
                    it197.next().onDyHeightTurnActionChanged(settingPacket.getDyHeightTurnActionR());
                }
                break;
            case SettingHalId.ID_DAMP_STATE_R /* 557858378 */:
                Iterator<AbsBYDAutoSettingListener> it198 = this.mAbsBydAutoSettingListeners.iterator();
                while (it198.hasNext()) {
                    it198.next().onDampStateChanged(settingPacket.getDampStateR());
                }
                break;
            case SettingHalId.ID_DY_HEIGHT_LEVEL_R /* 557858379 */:
                Iterator<AbsBYDAutoSettingListener> it199 = this.mAbsBydAutoSettingListeners.iterator();
                while (it199.hasNext()) {
                    it199.next().onDyHeightLevelChanged(settingPacket.getDyHeightLevelR());
                }
                break;
            case SettingHalId.ID_FD_C_R_C_CHECK_NUM_R /* 557858380 */:
                Iterator<AbsBYDAutoSettingListener> it200 = this.mAbsBydAutoSettingListeners.iterator();
                while (it200.hasNext()) {
                    it200.next().onFdCampingBlanceActionChanged(settingPacket.getFdCrcCheckNumR());
                }
                break;
            case SettingHalId.ID_ON_FD_CRC_CHECK_NUM_R /* 557858381 */:
                Iterator<AbsBYDAutoSettingListener> it201 = this.mAbsBydAutoSettingListeners.iterator();
                while (it201.hasNext()) {
                    it201.next().onFdCRCCheckNum(settingPacket.getOnFdCrcCheckNumR());
                }
                break;
            case SettingHalId.ID_FD_DY_HEIGHT_TURN_ACTION_R /* 557858382 */:
                Iterator<AbsBYDAutoSettingListener> it202 = this.mAbsBydAutoSettingListeners.iterator();
                while (it202.hasNext()) {
                    it202.next().onFdDyHeightTurnActionChanged(settingPacket.getFdDyHeightTurnActionR());
                }
                break;
            case 557858383:
                Iterator<AbsBYDAutoSettingListener> it203 = this.mAbsBydAutoSettingListeners.iterator();
                while (it203.hasNext()) {
                    it203.next().onFdDynamicSuspensionMode(settingPacket.getFdDampStateR());
                }
                break;
            case SettingHalId.ID_FD_DY_HEIGHT_LEVEL_R /* 557858384 */:
                Iterator<AbsBYDAutoSettingListener> it204 = this.mAbsBydAutoSettingListeners.iterator();
                while (it204.hasNext()) {
                    it204.next().onFdDyHeightLevelChanged(settingPacket.getFdDyHeightLevelR());
                }
                break;
            case SettingHalId.ID_FD_DY_HEIGHT_TOGGLE_STATE_R /* 557858385 */:
                Iterator<AbsBYDAutoSettingListener> it205 = this.mAbsBydAutoSettingListeners.iterator();
                while (it205.hasNext()) {
                    it205.next().onFdDyHeightToggleStateChanged(settingPacket.getFdDyHeightToggleStateR());
                }
                break;
            case SettingHalId.ID_FD_WELCOME_STATE_R /* 557858386 */:
                Iterator<AbsBYDAutoSettingListener> it206 = this.mAbsBydAutoSettingListeners.iterator();
                while (it206.hasNext()) {
                    it206.next().onFdWelcomeStateChanged(settingPacket.getFdWelcomeStateR());
                }
                break;
            case SettingHalId.ID_FD_WELCOME_ACTIVE_R /* 557858387 */:
                Iterator<AbsBYDAutoSettingListener> it207 = this.mAbsBydAutoSettingListeners.iterator();
                while (it207.hasNext()) {
                    it207.next().onFdWelcomeActiveChanged(settingPacket.getFdWelcomeActiveR());
                }
                break;
            case SettingHalId.ID_FD_FRONT_BOX_TAKE_STATE_R /* 557858388 */:
                Iterator<AbsBYDAutoSettingListener> it208 = this.mAbsBydAutoSettingListeners.iterator();
                while (it208.hasNext()) {
                    it208.next().onFdFrontBoxTakeStateChanged(settingPacket.getFdFrontBoxTakeStateR());
                }
                break;
            case SettingHalId.ID_FD_TAIL_BOX_TAKE_STATE_R /* 557858389 */:
                Iterator<AbsBYDAutoSettingListener> it209 = this.mAbsBydAutoSettingListeners.iterator();
                while (it209.hasNext()) {
                    it209.next().onFdTailBoxTakeStateChanged(settingPacket.getFdTailBoxTakeStateR());
                }
                break;
            case SettingHalId.ID_FD_CAMPING_BLANCE_STATE_R /* 557858390 */:
                Iterator<AbsBYDAutoSettingListener> it210 = this.mAbsBydAutoSettingListeners.iterator();
                while (it210.hasNext()) {
                    it210.next().onFdCampintBlacneStateChanged(settingPacket.getFdCampingBlanceStateR());
                }
                break;
            case SettingHalId.ID_FD_CAMPING_BLANCE_ACTION_R /* 557858391 */:
                Iterator<AbsBYDAutoSettingListener> it211 = this.mAbsBydAutoSettingListeners.iterator();
                while (it211.hasNext()) {
                    it211.next().onFdCampingBlanceActionChanged(settingPacket.getFdCampingBlanceActionR());
                }
                break;
            case SettingHalId.ID_FD_LIFT_MODE_STATE_R /* 557858392 */:
                Iterator<AbsBYDAutoSettingListener> it212 = this.mAbsBydAutoSettingListeners.iterator();
                while (it212.hasNext()) {
                    it212.next().onFdLiftModeStateChanged(settingPacket.getFdLiftModeStateR());
                }
                break;
            case SettingHalId.ID_FD_FIXED_STATE_R /* 557858393 */:
                Iterator<AbsBYDAutoSettingListener> it213 = this.mAbsBydAutoSettingListeners.iterator();
                while (it213.hasNext()) {
                    it213.next().onFdFixedStateChanged(settingPacket.getFdFixedStateR());
                }
                break;
            case SettingHalId.ID_FD_SPEED_SUPPORTED_TURN_R /* 557858394 */:
                Iterator<AbsBYDAutoSettingListener> it214 = this.mAbsBydAutoSettingListeners.iterator();
                while (it214.hasNext()) {
                    it214.next().onFdSpeedSupportedTurn(settingPacket.getFdSpeedSupportedTurnR());
                }
                break;
            case SettingHalId.ID_FD_MODE_SUPPORTED_HTURN_R /* 557858395 */:
                Iterator<AbsBYDAutoSettingListener> it215 = this.mAbsBydAutoSettingListeners.iterator();
                while (it215.hasNext()) {
                    it215.next().onFdModeSupportedHturnChanged(settingPacket.getFdModeSupportedHturnR());
                }
                break;
            case SettingHalId.ID_FD_WELCOME_SUPPORTED_R /* 557858396 */:
                Iterator<AbsBYDAutoSettingListener> it216 = this.mAbsBydAutoSettingListeners.iterator();
                while (it216.hasNext()) {
                    it216.next().onFdWelcomeSupportedChanged(settingPacket.getFdWelcomeSupportedR());
                }
                break;
            case SettingHalId.ID_FD_TAIL_BOX_TAKE_SUPPORTED_R /* 557858397 */:
                Iterator<AbsBYDAutoSettingListener> it217 = this.mAbsBydAutoSettingListeners.iterator();
                while (it217.hasNext()) {
                    it217.next().onFdTailBoxTakeSupportedChanged(settingPacket.getFdTailBoxTakeSupportedR());
                }
                break;
            case SettingHalId.ID_FD_TRAILER_MODE_CODE_R /* 557858398 */:
                Iterator<AbsBYDAutoSettingListener> it218 = this.mAbsBydAutoSettingListeners.iterator();
                while (it218.hasNext()) {
                    it218.next().onFdTrailerModeCodeChanged(settingPacket.getFdTrailerModeCodeR());
                }
                break;
            case SettingHalId.ID_FD_CAMPING_BLANCE_SUPPORTED_R /* 557858399 */:
                Iterator<AbsBYDAutoSettingListener> it219 = this.mAbsBydAutoSettingListeners.iterator();
                while (it219.hasNext()) {
                    it219.next().onFdCampingBlanceSupportedChanged(settingPacket.getFdCampingBlanceSupportedR());
                }
                break;
            case SettingHalId.ID_CLOSE_DOOR_LEVEL_R /* 557858411 */:
                Iterator<AbsBYDAutoSettingListener> it220 = this.mAbsBydAutoSettingListeners.iterator();
                while (it220.hasNext()) {
                    it220.next().onCloseDoorLevelChanged(settingPacket.getCloseDoorLevelR());
                }
                break;
            case SettingHalId.ID_INTEL_LOCK_OPEN_DRIVER_DOOR_R /* 557858412 */:
                Iterator<AbsBYDAutoSettingListener> it221 = this.mAbsBydAutoSettingListeners.iterator();
                while (it221.hasNext()) {
                    it221.next().onIntelLockOpenDriverDoorChanged(settingPacket.getIntelLockOpenDriverDoorR());
                }
                break;
            case SettingHalId.ID_BRAKE_CLOSE_DOOR_R /* 557858413 */:
                Iterator<AbsBYDAutoSettingListener> it222 = this.mAbsBydAutoSettingListeners.iterator();
                while (it222.hasNext()) {
                    it222.next().onBrakeCloseDoorChanged(settingPacket.getBrakeCloseDoorR());
                }
                break;
            case SettingHalId.ID_NFC_OPEN_DRIVER_DOOR_R /* 557858414 */:
                Iterator<AbsBYDAutoSettingListener> it223 = this.mAbsBydAutoSettingListeners.iterator();
                while (it223.hasNext()) {
                    it223.next().onNfcOpenDriverDoorChanged(settingPacket.getNfcOpenDriverDoorR());
                }
                break;
            case SettingHalId.ID_BLE_OPEN_DRIVER_DOOR_STATE_R /* 557858415 */:
                Iterator<AbsBYDAutoSettingListener> it224 = this.mAbsBydAutoSettingListeners.iterator();
                while (it224.hasNext()) {
                    it224.next().onBleOpenDriverDoorStateChanged(settingPacket.getBleOpenDriverDoorStateR());
                }
                break;
            case SettingHalId.ID_SAFE_BELT_CLOSE_DRIVE_DOOR_R /* 557858416 */:
                Iterator<AbsBYDAutoSettingListener> it225 = this.mAbsBydAutoSettingListeners.iterator();
                while (it225.hasNext()) {
                    it225.next().onSafeBeltCloseDriveDoorStateChanged(settingPacket.getSafeBeltCloseDriveDoorR());
                }
                break;
            case SettingHalId.ID_V_S_P_STATE_R /* 557858418 */:
                Iterator<AbsBYDAutoSettingListener> it226 = this.mAbsBydAutoSettingListeners.iterator();
                while (it226.hasNext()) {
                    it226.next().onVSPStateChanged(settingPacket.getVspStateR());
                }
                break;
            case SettingHalId.ID_DRIVER_SEAT_AUTO_RETURN_B3_R /* 557858429 */:
                Iterator<AbsBYDAutoSettingListener> it227 = this.mAbsBydAutoSettingListeners.iterator();
                while (it227.hasNext()) {
                    it227.next().onDriverSeatAutoReturnB3Changded(settingPacket.getWelcomeSupportedR());
                }
                break;
            case SettingHalId.ID_MOIS_ASSEMBLE_STATE_R /* 557858435 */:
                Iterator<AbsBYDAutoSettingListener> it228 = this.mAbsBydAutoSettingListeners.iterator();
                while (it228.hasNext()) {
                    it228.next().onMOISAssembleStateChanged(settingPacket.getMoisAssembleStateR());
                }
                break;
            case SettingHalId.ID_LRSE_MACHINE_SWITCH_STATE_R /* 557858441 */:
                Iterator<AbsBYDAutoSettingListener> it229 = this.mAbsBydAutoSettingListeners.iterator();
                while (it229.hasNext()) {
                    it229.next().onLRSEMachineSwitchStateChanged(settingPacket.getLrseMachineSwitchStateR());
                }
                break;
            case SettingHalId.ID_RRSE_MACHINE_SWITCH_STATE_R /* 557858442 */:
                Iterator<AbsBYDAutoSettingListener> it230 = this.mAbsBydAutoSettingListeners.iterator();
                while (it230.hasNext()) {
                    it230.next().onRRSEMachineSwitchStateChanged(settingPacket.getRrseMachineSwitchStateR());
                }
                break;
            case SettingHalId.ID_LRSE_CHILD_MODE_TIME_WRITE_FB_R /* 557858449 */:
                Iterator<AbsBYDAutoSettingListener> it231 = this.mAbsBydAutoSettingListeners.iterator();
                while (it231.hasNext()) {
                    it231.next().onLRSEChildModeTimeWriteFb(settingPacket.getLrseChildModeTimeWriteFbR());
                }
                break;
            case SettingHalId.ID_RRSE_CHILD_MODE_TIME_WRITE_FB_R /* 557858450 */:
                Iterator<AbsBYDAutoSettingListener> it232 = this.mAbsBydAutoSettingListeners.iterator();
                while (it232.hasNext()) {
                    it232.next().onRRSEChildModeTimeWriteFb(settingPacket.getRrseChildModeTimeWriteFbR());
                }
                break;
            case SettingHalId.ID_LRSE_CHILD_MODE_CODE_WRITE_FB_R /* 557858459 */:
                Iterator<AbsBYDAutoSettingListener> it233 = this.mAbsBydAutoSettingListeners.iterator();
                while (it233.hasNext()) {
                    it233.next().onLRSEChildModeCodeWriteFb(settingPacket.getLrseChildModeCodeWriteFbR());
                }
                break;
            case SettingHalId.ID_RRSE_CHILD_MODE_CODE_WRITE_FB_R /* 557858460 */:
                Iterator<AbsBYDAutoSettingListener> it234 = this.mAbsBydAutoSettingListeners.iterator();
                while (it234.hasNext()) {
                    it234.next().onRRSEChildModeCodeWriteFb(settingPacket.getRrseChildModeCodeWriteFbR());
                }
                break;
            case SettingHalId.ID_DRAG_MODE_FORWARD_AND_BACKWARD_STATE_R /* 557858461 */:
                Iterator<AbsBYDAutoSettingListener> it235 = this.mAbsBydAutoSettingListeners.iterator();
                while (it235.hasNext()) {
                    it235.next().onDragModeForwardAndBackwardChanged(settingPacket.getDragModeForwardAndBackwardStateR());
                }
                break;
            case SettingHalId.ID_DRAG_AND_DROP_MODE_TIPS_STATE_R /* 557858462 */:
                Iterator<AbsBYDAutoSettingListener> it236 = this.mAbsBydAutoSettingListeners.iterator();
                while (it236.hasNext()) {
                    it236.next().onDragAndDropModeTipsChanged(settingPacket.getDragAndDropModeTipsStateR());
                }
                break;
            case SettingHalId.ID_TRAILER_MODE_MILEAGE_R /* 557858529 */:
                Iterator<AbsBYDAutoSettingListener> it237 = this.mAbsBydAutoSettingListeners.iterator();
                while (it237.hasNext()) {
                    it237.next().onTrailerModeMileageChanged(settingPacket.getTrailerModeMileageR());
                }
                break;
            case SettingHalId.ID_MAIN_HS_MASSAGE_STATE_R /* 557859101 */:
                Iterator<AbsBYDAutoSettingListener> it238 = this.mAbsBydAutoSettingListeners.iterator();
                while (it238.hasNext()) {
                    it238.next().onHsMassageStateChanged(1, settingPacket.getMainHsMassageStateR());
                }
                break;
            case SettingHalId.ID_HS_MASSAGE_STATE_PASSENGER_R /* 557859103 */:
                Iterator<AbsBYDAutoSettingListener> it239 = this.mAbsBydAutoSettingListeners.iterator();
                while (it239.hasNext()) {
                    it239.next().onHsMassageStateChanged(2, settingPacket.getHsMassageStatePassengerR());
                }
                break;
            case SettingHalId.ID_HS_MASSAGE_STATE_BACK_LEFT_R /* 557859104 */:
                Iterator<AbsBYDAutoSettingListener> it240 = this.mAbsBydAutoSettingListeners.iterator();
                while (it240.hasNext()) {
                    it240.next().onHsMassageStateChanged(3, settingPacket.getHsMassageStateBackLeftR());
                }
                break;
            case SettingHalId.ID_HS_MASSAGE_STATE_BACK_RIGHT_R /* 557859105 */:
                Iterator<AbsBYDAutoSettingListener> it241 = this.mAbsBydAutoSettingListeners.iterator();
                while (it241.hasNext()) {
                    it241.next().onHsMassageStateChanged(4, settingPacket.getHsMassageStateBackRightR());
                }
                break;
            case 557860611:
                ambientMusicModeStateR = settingPacket.getAmbientMusicModeStateR();
                int areaId = settingPacket.getAreaId();
                Iterator<AbsBYDAutoSettingListener> it242 = this.mAbsBydAutoSettingListeners.iterator();
                while (it242.hasNext()) {
                    it242.next().onAmbientMusicModeState(areaId, ambientMusicModeStateR);
                }
                i4 = 1255;
                caseFeatureIds(i4, Integer.valueOf(ambientMusicModeStateR));
                break;
            case 557860613:
                ambientMusicModeStateR = settingPacket.getAmbientMusicModeR();
                int areaId2 = settingPacket.getAreaId();
                Iterator<AbsBYDAutoSettingListener> it243 = this.mAbsBydAutoSettingListeners.iterator();
                while (it243.hasNext()) {
                    it243.next().onAmbientMusicModechanged(areaId2, ambientMusicModeStateR);
                }
                i4 = 2829;
                caseFeatureIds(i4, Integer.valueOf(ambientMusicModeStateR));
                break;
            case 557860615:
                ambientMusicModeStateR = settingPacket.getAmbientMulticolorStateR();
                int areaId3 = settingPacket.getAreaId();
                Iterator<AbsBYDAutoSettingListener> it244 = this.mAbsBydAutoSettingListeners.iterator();
                while (it244.hasNext()) {
                    it244.next().onAmbientMulticolorStateChanged(areaId3, ambientMusicModeStateR);
                }
                i4 = 2808;
                caseFeatureIds(i4, Integer.valueOf(ambientMusicModeStateR));
                break;
            case 557860617:
                ambientMusicModeStateR = settingPacket.getAmbientMulticolorModeR();
                int areaId4 = settingPacket.getAreaId();
                Iterator<AbsBYDAutoSettingListener> it245 = this.mAbsBydAutoSettingListeners.iterator();
                while (it245.hasNext()) {
                    it245.next().onAmbientMulticolorModeChanged(areaId4, ambientMusicModeStateR);
                }
                i4 = 2805;
                caseFeatureIds(i4, Integer.valueOf(ambientMusicModeStateR));
                break;
            case 557860631:
                ambientMusicModeStateR = settingPacket.getAmbientFeedBack();
                Iterator<AbsBYDAutoSettingListener> it246 = this.mAbsBydAutoSettingListeners.iterator();
                while (it246.hasNext()) {
                    it246.next().onAmbientFeedBackChanged(ambientMusicModeStateR);
                }
                i4 = 902;
                caseFeatureIds(i4, Integer.valueOf(ambientMusicModeStateR));
                break;
            case 557862926:
                Iterator<AbsBYDAutoSettingListener> it247 = this.mAbsBydAutoSettingListeners.iterator();
                while (it247.hasNext()) {
                    it247.next().onScreenVoltageStateChanged(settingPacket.getScreenVoltageStateR());
                }
                break;
            case 559954810:
                Iterator<AbsBYDAutoSettingListener> it248 = this.mAbsBydAutoSettingListeners.iterator();
                while (it248.hasNext()) {
                    it248.next().onTurnSteeringWheelChanged(3, settingPacket.getSteeringWheelY(), settingPacket.getSteeringWheelZ());
                }
                break;
            case 559954811:
                Iterator<AbsBYDAutoSettingListener> it249 = this.mAbsBydAutoSettingListeners.iterator();
                while (it249.hasNext()) {
                    it249.next().onTurnSteeringWheelChanged(1, settingPacket.getSteeringWheelY(), settingPacket.getSteeringWheelZ());
                }
                break;
            case 559954812:
                Iterator<AbsBYDAutoSettingListener> it250 = this.mAbsBydAutoSettingListeners.iterator();
                while (it250.hasNext()) {
                    it250.next().onTurnRearviewMirrorChanged(3, settingPacket.getRearviewMirrorXLeft(), settingPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954813:
                Iterator<AbsBYDAutoSettingListener> it251 = this.mAbsBydAutoSettingListeners.iterator();
                while (it251.hasNext()) {
                    it251.next().onTurnRearviewMirrorChanged(7, settingPacket.getRearviewMirrorXRight(), settingPacket.getRearviewMirrorYRight());
                }
                break;
            case 559954814:
                Iterator<AbsBYDAutoSettingListener> it252 = this.mAbsBydAutoSettingListeners.iterator();
                while (it252.hasNext()) {
                    it252.next().onTurnRearviewMirrorChanged(1, settingPacket.getRearviewMirrorXLeft(), settingPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954815:
                Iterator<AbsBYDAutoSettingListener> it253 = this.mAbsBydAutoSettingListeners.iterator();
                while (it253.hasNext()) {
                    it253.next().onTurnRearviewMirrorChanged(6, settingPacket.getRearviewMirrorXRight(), settingPacket.getRearviewMirrorYRight());
                }
                break;
            case SettingHalId.ID_SEAT_TURN_HEIGHT_DRIVER /* 559954820 */:
                Iterator<AbsBYDAutoSettingListener> it254 = this.mAbsBydAutoSettingListeners.iterator();
                while (it254.hasNext()) {
                    it254.next().onTurnSeatHeightChanged(1, 1, settingPacket.getSeatTurnHeightDriver());
                }
                break;
            case SettingHalId.ID_SEAT_TURN_HEIGHT_COPILOT /* 559954821 */:
                Iterator<AbsBYDAutoSettingListener> it255 = this.mAbsBydAutoSettingListeners.iterator();
                while (it255.hasNext()) {
                    it255.next().onTurnSeatHeightChanged(2, 1, settingPacket.getSeatTurnHeightCopilot());
                }
                break;
            case SettingHalId.ID_SEAT_TURN_HEIGHT_BACK_LEFT /* 559954822 */:
                Iterator<AbsBYDAutoSettingListener> it256 = this.mAbsBydAutoSettingListeners.iterator();
                while (it256.hasNext()) {
                    it256.next().onTurnSeatHeightChanged(3, 1, settingPacket.getSeatTurnHeightBackLeft());
                }
                break;
            case SettingHalId.ID_SEAT_TURN_HEIGHT_BACK_RIGHT /* 559954823 */:
                Iterator<AbsBYDAutoSettingListener> it257 = this.mAbsBydAutoSettingListeners.iterator();
                while (it257.hasNext()) {
                    it257.next().onTurnSeatHeightChanged(4, 1, settingPacket.getSeatTurnHeightBackRight());
                }
                break;
            case SettingHalId.ID_SEAT_BACKREST_POSTION_DRIVER /* 559954824 */:
                Iterator<AbsBYDAutoSettingListener> it258 = this.mAbsBydAutoSettingListeners.iterator();
                while (it258.hasNext()) {
                    it258.next().onTurnSeatbackrestChanged(1, 1, settingPacket.getSeatbackrestPostionDriver());
                }
                break;
            case SettingHalId.ID_SEAT_BACKREST_POSTION_COPILOT /* 559954825 */:
                Iterator<AbsBYDAutoSettingListener> it259 = this.mAbsBydAutoSettingListeners.iterator();
                while (it259.hasNext()) {
                    it259.next().onTurnSeatbackrestChanged(2, 1, settingPacket.getSeatbackrestPostionCopilot());
                }
                break;
            case SettingHalId.ID_SEAT_BACKREST_POSTION_BACK_LEFT /* 559954826 */:
                Iterator<AbsBYDAutoSettingListener> it260 = this.mAbsBydAutoSettingListeners.iterator();
                while (it260.hasNext()) {
                    it260.next().onTurnSeatbackrestChanged(3, 1, settingPacket.getSeatbackrestPostionBackLeft());
                }
                break;
            case SettingHalId.ID_SEAT_BACKREST_POSTION_BACK_RIGHT /* 559954827 */:
                Iterator<AbsBYDAutoSettingListener> it261 = this.mAbsBydAutoSettingListeners.iterator();
                while (it261.hasNext()) {
                    it261.next().onTurnSeatbackrestChanged(4, 1, settingPacket.getSeatbackrestPostionBackRight());
                }
                break;
            case SettingHalId.ID_TURN_CUSHION_HEIGHT_DRIVER /* 559954832 */:
                Iterator<AbsBYDAutoSettingListener> it262 = this.mAbsBydAutoSettingListeners.iterator();
                while (it262.hasNext()) {
                    it262.next().onTurnSeatCushionChanged(1, 1, settingPacket.getTurnCushionHeightDriver());
                }
                break;
            case SettingHalId.ID_TURN_CUSHION_HEIGHT_COPILOT /* 559954833 */:
                Iterator<AbsBYDAutoSettingListener> it263 = this.mAbsBydAutoSettingListeners.iterator();
                while (it263.hasNext()) {
                    it263.next().onTurnSeatCushionChanged(2, 1, settingPacket.getTurnCushionHeightCopilot());
                }
                break;
            case SettingHalId.ID_TURN_CUSHION_HEIGHT_BACK_LEFT /* 559954834 */:
                Iterator<AbsBYDAutoSettingListener> it264 = this.mAbsBydAutoSettingListeners.iterator();
                while (it264.hasNext()) {
                    it264.next().onTurnSeatCushionChanged(3, 1, settingPacket.getTurnCushionHeightBackLeft());
                }
                break;
            case SettingHalId.ID_TURN_CUSHION_HEIGHT_BACK_RIGHT /* 559954835 */:
                Iterator<AbsBYDAutoSettingListener> it265 = this.mAbsBydAutoSettingListeners.iterator();
                while (it265.hasNext()) {
                    it265.next().onTurnSeatCushionChanged(4, 1, settingPacket.getTurnCushionHeightBackRight());
                }
                break;
            case SettingHalId.ID_TURN_CALIPER_SPLINT_HEIGHT_DRIVER /* 559954836 */:
                Iterator<AbsBYDAutoSettingListener> it266 = this.mAbsBydAutoSettingListeners.iterator();
                while (it266.hasNext()) {
                    it266.next().onTurnSeatCaliperSplintChanged(1, 1, settingPacket.getTurnCaliperSplintHeightDriver());
                }
                break;
            case SettingHalId.ID_TURN_CALIPER_SPLINT_HEIGHT_COPILOT /* 559954837 */:
                Iterator<AbsBYDAutoSettingListener> it267 = this.mAbsBydAutoSettingListeners.iterator();
                while (it267.hasNext()) {
                    it267.next().onTurnSeatCaliperSplintChanged(2, 1, settingPacket.getTurnCaliperSplintHeightCopilot());
                }
                break;
            case SettingHalId.ID_TURN_CALIPER_SPLINT_HEIGHT_BACK_LEFT /* 559954838 */:
                Iterator<AbsBYDAutoSettingListener> it268 = this.mAbsBydAutoSettingListeners.iterator();
                while (it268.hasNext()) {
                    it268.next().onTurnSeatCaliperSplintChanged(3, 1, settingPacket.getTurnCaliperSplintHeightBackLeft());
                }
                break;
            case SettingHalId.ID_TURN_CALIPER_SPLINT_HEIGHT_BACK_RIGHT /* 559954839 */:
                Iterator<AbsBYDAutoSettingListener> it269 = this.mAbsBydAutoSettingListeners.iterator();
                while (it269.hasNext()) {
                    it269.next().onTurnSeatCaliperSplintChanged(4, 1, settingPacket.getTurnCaliperSplintHeightBackRight());
                }
                break;
            case SettingHalId.ID_HEADREST_TURN_HEIGHT_DRIVER /* 559954844 */:
                Iterator<AbsBYDAutoSettingListener> it270 = this.mAbsBydAutoSettingListeners.iterator();
                while (it270.hasNext()) {
                    it270.next().onHeadrestTurnHeightChanged(1, 1, settingPacket.getHeadrestTurnHeightDriver());
                }
                break;
            case SettingHalId.ID_HEADREST_TURN_HEIGHT_COPILOT /* 559954845 */:
                Iterator<AbsBYDAutoSettingListener> it271 = this.mAbsBydAutoSettingListeners.iterator();
                while (it271.hasNext()) {
                    it271.next().onHeadrestTurnHeightChanged(2, 2, settingPacket.getHeadrestTurnHeightCopilot());
                }
                break;
            case SettingHalId.ID_HEADREST_TURN_HEIGHT_BACK_LEFT /* 559955504 */:
                Iterator<AbsBYDAutoSettingListener> it272 = this.mAbsBydAutoSettingListeners.iterator();
                while (it272.hasNext()) {
                    it272.next().onHeadrestTurnHeightChanged(3, 1, settingPacket.getHeadrestTurnHeightBackLeft());
                }
                break;
            case SettingHalId.ID_HEADREST_TURN_HEIGHT_BACK_RIGHT /* 559955505 */:
                Iterator<AbsBYDAutoSettingListener> it273 = this.mAbsBydAutoSettingListeners.iterator();
                while (it273.hasNext()) {
                    it273.next().onHeadrestTurnHeightChanged(4, 2, settingPacket.getHeadrestTurnHeightBackRight());
                }
                break;
        }
    }

    public int autoBackDefaultReset(int i) {
        Log.v(TAG, "autoBackDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int backLightDefaultReset(int i) {
        Log.v(TAG, "backLightDefaultReset is execute");
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

    public int carSettingsDefaultReset(int i) {
        Log.v(TAG, "carSettingsDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int carSettingsReset(int i) {
        Log.v(TAG, "carSettingsReset is execute");
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

    public int doorsAndWindowDefaultResset(int i) {
        Log.v(TAG, "doorsAndWindowDefaultResset is execute");
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

    public int equalizerDefaultReset(int i) {
        Log.v(TAG, "equalizerDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int factoryResetAll(int i) {
        Log.i(TAG, "factoryResetAll is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().factoryResetAll(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:186:0x07aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r13, java.lang.Class<?> r14) {
        /*
            Method dump skipped, instruction units count: 2554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.setting.tsimpl.SettingManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    public int get1LevelSeatHeatingState(int i) {
        Log.v(TAG, "get1LevelSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().get1LevelSeatHeatingState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
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

    public int get5LevelSeatHeatingState(int i) {
        Log.v(TAG, "get5LevelSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
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

    public int get5LevelSeatVentilatingState(int i) {
        Log.v(TAG, "get5LevelSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
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

    public int getACAutoAir() {
        Log.i(TAG, "getACAutoAir is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getACAutoAir();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getACBTWind() {
        Log.i(TAG, "getACBTWind is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getACBTWind();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getACPauseCycle() {
        Log.i(TAG, "getACPauseCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getACPauseCycle();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getACTunnelCycle() {
        Log.i(TAG, "getACTunnelCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getACTunnelCycle();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAcTemperatureUnit() {
        Log.i(TAG, "getAcTemperatureUnit is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAcTemperatureUnit();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAcWarmMode() {
        Log.i(TAG, "getAcWarmMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAcWarmMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAidedDrivingInd() {
        Log.v(TAG, "getAidedDrivingInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getAidedDrivingInd();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getAidedDrivingInd mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAmbientAreaSupport() {
        String str;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getAmbientAreaSupport mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getAmbientAreaSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientFeedBack() {
        Log.v(TAG, "getAmbientFeedBack is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        Log.v(TAG, "getAmbientFeedBack is execute");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getAmbientFeedBack();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getAmbientFeedBack mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAmbientLightColorSupport() {
        String str;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getAmbientLightColorSupport mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getAmbientLightColorSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientLightSupport() {
        String str;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getAmbientLightSupport mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getAmbientLightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientMulticolorMode(int i) {
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMulticolorMode(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMulticolorState(int i) {
        Log.v(TAG, "getAmbientMulticolorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMulticolorState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicMode(int i) {
        Log.v(TAG, "getAmbientMusicMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMusicMode(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicModeState(int i) {
        Log.v(TAG, "getAmbientMusicModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMusicModeState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAppUseLimitState() {
        Log.i(TAG, "getAppUseLimitState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAppUseLimitState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAutoExternalRearMirrorFollowUpSwitch() {
        Log.i(TAG, "getAutoExternalRearMirrorFollowUpSwitch is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAutoExternalRearMirrorFollowUpSwitch();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        String str;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getAutoExternalRearMirrorFollowUpSwitchSupport mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getAutoExternalRearMirrorFollowUpSwitchSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAutoPowerOffState() {
        Log.i(TAG, "getAutoPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAutoPowerOffState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAutoPowerOnState() {
        Log.i(TAG, "getAutoPowerOnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAutoPowerOnState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAutoPurificationState() {
        Log.i(TAG, "getAutoPurificationState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAutoPurificationState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAutoRainWiperState() {
        Log.i(TAG, "getAutoRainWiperState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getAutoRainWiperState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAutomaticParking() {
        String str;
        Log.i(TAG, "getAutomaticParking is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getAutomaticParking mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getAutomaticParking();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getBackDoorElectricMode() {
        Log.i(TAG, "getBackDoorElectricMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBackDoorElectricMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getBackDoorOpenedHeight() {
        Log.i(TAG, "getBackDoorOpenedHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBackDoorOpenedHeight();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getBackHomeLightDelay() {
        Log.i(TAG, "getBackHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBackHomeLightDelay();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getBackHomeLightDelayValue() {
        Log.i(TAG, "getBackHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBackHomeLightDelayValue();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getBleOpenDriverDoorState() {
        Log.v(TAG, "getBleOpenDriverDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBleOpenDriverDoorState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getBrakeCloseDoor() {
        Log.v(TAG, "getBrakeCloseDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBrakeCloseDoor();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getBrakePedalMode() {
        Log.i(TAG, "getBrakePedalMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public int getBsisCollisionStateR() {
        Log.i(TAG, "getBsisCollisionStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBsisCollisionStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getBsisFuncStatusR() {
        Log.i(TAG, "getBsisFuncStatusR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBsisFuncStatusR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCampingBlanceAction() {
        Log.v(TAG, "getCampingBlanceAction is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getCampingBlanceAction();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCampingBlanceState() {
        Log.v(TAG, "getCampingBlanceState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getCampingBlanceState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCampingBlanceSupported() {
        Log.v(TAG, "getCampingBlanceSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getCampingBlanceSupported();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCarTipsSoundType() {
        Log.i(TAG, "getCarTipsSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getCarTipsSoundType();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getChargingPort() {
        Log.i(TAG, "getChargingPort is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getChargingPort();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCloseDoorLevel() {
        Log.v(TAG, "getCloseDoorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getCloseDoorLevel();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getComSpeedDownState() {
        Log.v(TAG, "getComSpeedDownState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getComSpeedDownState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getComSpeedDownState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getComSpeedUpState() {
        Log.v(TAG, "getComSpeedUpState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getComSpeedUpState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getComSpeedUpState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getComfortableParkingState() {
        Log.i(TAG, "getComfortableParkingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getComfortableParkingState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCushionSupport(int i) {
        Log.i(TAG, "getCushionSupport is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getCushionSupport(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCvcMode() {
        Log.v(TAG, "getCvcMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getCvcMode();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDampState() {
        Log.v(TAG, "getDampState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDampState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDisinfState() {
        Log.i(TAG, "getDisinfState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDisinfState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDoorHeightSupport() {
        String str;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getDoorHeightSupport mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getDoorHeightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDoorknobAutoShrinkState() {
        Log.i(TAG, "getDoorknobAutoShrinkState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDoorknobAutoShrinkState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDragAndDropModeTipsStateR() {
        Log.i(TAG, "getDragAndDropModeTipsStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "DragAndDropModeTipsStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDragAndDropModeTipsStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDragModeForwardAndBackwardStateR() {
        Log.i(TAG, "getDragModeForwardAndBackwardStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "DragModeForwardAndBackwardStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDragModeForwardAndBackwardStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDriveDoorCloseInd() {
        String str;
        Log.i(TAG, "getDriveDoorCloseInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getDriveDoorCloseInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getDriveDoorCloseInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDriverSeatAutoReturn() {
        Log.i(TAG, "getDriverSeatAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDriverSeatAutoReturn();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDriverSeatAutoReturnB3() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getDriverSeatAutoReturnB3();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getDriverSeatAutoReturnB3 mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getDyHeightLevel() {
        Log.v(TAG, "getDyHeightLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDyHeightLevel();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDyHeightToggleState() {
        Log.v(TAG, "getDyHeightToggleState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDyHeightToggleState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDyHeightTurnAction() {
        Log.v(TAG, "getDyHeightTurnAction is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDyHeightTurnAction();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDynamicSuspensionMode() {
        Log.i(TAG, "getDynamicSuspensionMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getDynamicSuspensionMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
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

    public int getElectroniChildLockConfig() {
        String str;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getElectroniChildLockConfig mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getElectroniChildLockConfig();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getEnergyFeedback() {
        Log.i(TAG, "getEnergyFeedback is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEnergyFeedback();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getEngineSoundType() {
        Log.i(TAG, "getEngineSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEngineSoundType();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getExhibitionMode() {
        String str;
        Log.i(TAG, "getExhibitionMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getExhibitionMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getExhibitionMode();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getExhibitionStartEnginePrompt() {
        String str;
        Log.i(TAG, "getExhibitionStartEnginePrompt is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getBackHomeLightDelay mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getExhibitionStartEnginePrompt();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getFdCampingBlanceAction() {
        Log.v(TAG, "getFdCampingBlanceAction is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdCampingBlanceAction();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdCampingBlanceState() {
        Log.v(TAG, "getFdCampingBlanceState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdCampingBlanceState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdCampingBlanceSupported() {
        Log.v(TAG, "getFdCampingBlanceSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdCampingBlanceSupported();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdCrcCheckNum() {
        Log.v(TAG, "getFdCRCCheckNum is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdCrcCheckNum();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdDampState() {
        Log.v(TAG, "getFdDampState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdDampState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdDyHeightLevel() {
        Log.v(TAG, "getFdDyHeightLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdDyHeightLevel();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdDyHeightToggleState() {
        Log.v(TAG, "getFdDyHeightToggleState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdDyHeightToggleState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdDyHeightTurnAction() {
        Log.v(TAG, "getFdDyHeightTurnAction is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdDyHeightTurnAction();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdDynamicSuspensionModeR() {
        Log.i(TAG, "getFdDynamicSuspensionModeR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "FdDynamicSuspensionModeR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdDynamicSuspensionModeR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdFixedState() {
        Log.v(TAG, "getFdFixedState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdFixedState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdFrontBoxTakeState() {
        Log.v(TAG, "getFdFrontBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdFrontBoxTakeState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdLiftModeState() {
        Log.v(TAG, "getFdLiftModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdLiftModeState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdModeSupportedHturn() {
        Log.v(TAG, "getFdModeSupportedHturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdModeSupportedHturn();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdSpeedSupportedTurn() {
        Log.v(TAG, "getFdSpeedSupportedTurn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdSpeedSupportedTurn();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdTailBoxTakeState() {
        Log.v(TAG, "getFdTailBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdTailBoxTakeState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdTailBoxTakeSupported() {
        Log.v(TAG, "getFdTailBoxTakeSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdTailBoxTakeSupported();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdTrailerModeCode() {
        Log.v(TAG, "getFdTrailerModeCode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdTrailerModeCode();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdWelcomeActive() {
        Log.v(TAG, "getFdWelcomeActive is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdWelcomeActive();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdWelcomeState() {
        Log.v(TAG, "getFdWelcomeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdWelcomeState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFdWelcomeSupported() {
        Log.v(TAG, "getFdWelcomeSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFdWelcomeSupported();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFixedState() {
        Log.v(TAG, "getFixedState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getFixedState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getHSpeedAutoCloseState() {
        Log.v(TAG, "getHSpeedAutoCloseState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getHSpeedAutoCloseState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getHSpeedAutoCloseState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getHeadLampConfig() {
        Log.v(TAG, "getHeadLampConfig is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getHeadLampConfig();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getHeadLampConfig mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getHeadlampLevel() {
        String str;
        Log.i(TAG, "getHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getHeadlampLevel mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getHeadlampLevel();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getHeadrestHorization(int i) {
        Log.i(TAG, "getHeadrestHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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
            throw null;
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

    public int getHevChargingPort() {
        Log.v(TAG, "getHevChargingPort is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHevChargingPort();
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

    public int getHudBrightness() {
        Log.i(TAG, "getHudBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudBrightness();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getHudDisplayContent(int i) {
        Log.i(TAG, "getHudDisplayContent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public int getHudHeight() {
        Log.i(TAG, "getHudHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudHeight();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getHudMode() {
        Log.i(TAG, "getHudMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getHudModeParams() {
        Log.i(TAG, "getHudModeParams is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudModeParams();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getHudNavigationState() {
        Log.v(TAG, "getHudNavigationState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudNavigationState();
    }

    public int getHudPattern() {
        Log.v(TAG, "getHudPattern is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudPattern();
    }

    public int getHudRotationAngle() {
        Log.i(TAG, "getHudRotationAngle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudRotationAngle();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getHudSafeDriveState() {
        Log.v(TAG, "getHudSafeDriveState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudSafeDriveState();
    }

    public int getHudScreenProjectionState() {
        Log.v(TAG, "getHudScreenProjectionState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudScreenProjectionState();
    }

    public int getHudState() {
        Log.i(TAG, "getHudState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getIALArea() {
        String str;
        Log.i(TAG, "getIALArea is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getIALArea mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getIALArea();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getIALBrightness(int i) {
        String str;
        Log.i(TAG, "getIALBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getIALBrightness mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getIALColor(int i) {
        String str;
        Log.i(TAG, "getIALColor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getIALColor mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getINSTheme() {
        String str;
        Log.i(TAG, "getINSTheme is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getINSTheme mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getINSTheme();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getInsideLightDoorState() {
        String str;
        Log.i(TAG, "getInsideLightDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getInsideLightDoorState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getInsideLightDoorState();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getIntelLockOpenDriverDoor() {
        Log.v(TAG, "getIntelLockOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIntelLockOpenDriverDoor();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getIntelligentAcAreaState() {
        Log.i(TAG, "getIntelligentAcAreaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIntelligentAcAreaState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getIntelligentLockState() {
        Log.i(TAG, "getIntelligentLockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIntelligentLockState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getIntelligentUnlockState() {
        Log.i(TAG, "getIntelligentUnlockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIntelligentUnlockState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getIviProfileSettingsR() {
        Log.i(TAG, "getIviProfileSettingsR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIviProfileSettingsR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getKeyPowerLowInd() {
        String str;
        Log.i(TAG, "getKeyPowerLowInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getKeyPowerLowInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getKeyPowerLowInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getKeyVibratorLevel() {
        Log.v(TAG, "getKeyVibratorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getKeyVibratorLevel();
    }

    public int getKeypadToneState() {
        Log.v(TAG, "getKeypadToneState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getKeypadToneState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getKeypadToneState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getLandAdvanceAtSate() {
        Log.i(TAG, "getLandAdvanceAtSate is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLandAdvanceAtSate();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLanternLanguageMode() {
        String str;
        Log.i(TAG, "getLanternLanguageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getLanternLanguageMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getLanternLanguageMode();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLeaveCarPInd() {
        String str;
        Log.i(TAG, "getLeaveCarPInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getLeaveCarPInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getLeaveCarPInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLeftHeadlampLevel() {
        String str;
        Log.i(TAG, "getLeftHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getLeftHeadlampLevel mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getLeftHeadlampLevel();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLeftHomeLightDelay() {
        Log.i(TAG, "getLeftHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLeftHomeLightDelay();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLeftHomeLightDelayValue() {
        Log.i(TAG, "getLeftHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLeftHomeLightDelayValue();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftRearMassageChairConfigurationForEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getLeftRearMassageChairConfigurationForECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getLeftRearMemoryConfiguration() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMemoryConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getLeftRearMemoryConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getLeftRearSeatBackAdjustmentConfiguration() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getLeftRearSeatBackAdjustmentConfiguration is " + this.mForTest + " execute return");
        return -1;
    }

    public int getLeftRearSeatHorizontalAdjustmentConfiguration() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getLeftRearSeatHorizontalAdjustmentConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getLeftRearSeatLegBracket() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getLeftRearSeatLegBracket mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getLeftRseChildModeSwitchStatusR() {
        Log.i(TAG, "getLeftRseChildModeSwitchStatusR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLeftRseChildModeSwitchStatusR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLiftModeState() {
        Log.v(TAG, "getLiftModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLiftModeState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLockCarRiseWindow() {
        Log.i(TAG, "getLockCarRiseWindow is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLockCarRiseWindow();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLockOff() {
        Log.i(TAG, "getLockOff is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLockOff();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLowBatteryInd() {
        String str;
        Log.i(TAG, "getLowBatteryInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getLowBatteryInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getLowBatteryInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLowOilInd() {
        String str;
        Log.i(TAG, "getLowOilInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getLowOilInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getLowOilInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLrseChildModeCodeWriteFbR() {
        Log.i(TAG, "getLrseChildModeCodeWriteFbR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLrseChildModeCodeWriteFbR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLrseChildModeTimeWriteFbR() {
        Log.i(TAG, "getLrseChildModeTimeWriteFbR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLrseChildModeTimeWriteFbR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLrseMachineSwitchStateR() {
        Log.i(TAG, "getLrseMachineSwitchStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getLrseMachineSwitchStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMain2SaveOrRecoveFailCode() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getMain2SaveOrRecoveFailCode();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMain2SaveOrRecoveFailCode mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMain2SaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getMain2SaveOrRecoverState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMain2SaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByGCtrl();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMainDriveElectricHeadrestAdjustmentByGCtrl mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByGCtrl();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMainDriveElectricLegSupportAdjustmentByGCtrl is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByGCtrl();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMainDriverSeatAdjustmentConfigurationByGCtrl is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl  is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMainHsMassageState() {
        Log.v(TAG, "getMainHsMassageState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainHsMassageState();
    }

    public int getMainSaveOrRecoveFailCode() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getMainSaveOrRecoveFailCode();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMainSaveOrRecoveFailCode mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMainSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getMainSaveOrRecoverState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMainSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getMaintainRemindState() {
        Log.i(TAG, "getMaintainRemindState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMaintainRemindState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMassageLevel(int i) {
        String str;
        Log.i(TAG, "getMassageLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getMassageLevel mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getMassageMode(int i) {
        String str;
        Log.i(TAG, "getMassageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getMassageMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getMemorySetOperationFlag() {
        Log.v(TAG, " getMemorySetOperationFlag.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMemorySetOperationFlag();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMicroSwitchLockWindowState() {
        Log.i(TAG, "getMicroSwitchLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMicroSwitchLockWindowState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMicroSwitchUnlockWindowState() {
        Log.i(TAG, "getMicroSwitchUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMicroSwitchUnlockWindowState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMissKeyInd() {
        String str;
        Log.i(TAG, "getMissKeyInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getMissKeyInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getMissKeyInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getMissPackageCount() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getMissPackageCount();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getMissPackageCount mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getModeSupportedHturn() {
        Log.v(TAG, "getModeSupportedHturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getModeSupportedHturn();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getModelSupport(int i) {
        Log.i(TAG, "getModelSupport is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getModelSupport(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getMoisAssembleStateR() {
        Log.i(TAG, "getMoisAssembleStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMoisAssembleStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMoisFuncStatusR() {
        Log.i(TAG, "getMoisFuncStatusR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMoisFuncStatusR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMotorPretensionerSeatbeltState() {
        Log.i(TAG, "getMotorPretensionerSeatbeltState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int motorPretensionerSeatbeltState = this.mTsManagerImpl.getSettingAdapterManager().getMotorPretensionerSeatbeltState();
        if (motorPretensionerSeatbeltState == 0) {
            return 1;
        }
        if (motorPretensionerSeatbeltState == 1) {
            return 2;
        }
        return motorPretensionerSeatbeltState;
    }

    public int getNavigationMediaState() {
        Log.i(TAG, "getNavigationMediaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getNavigationMediaState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getNfcOpenDriverDoor() {
        Log.v(TAG, "getNfcOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getNfcOpenDriverDoor();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getNightDRLightOnInd() {
        String str;
        Log.i(TAG, "getNightDRLightOnInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getNightDRLightOnInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getNightDRLightOnInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getOffReminder() {
        String str;
        Log.i(TAG, "getOffReminder is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getOffReminder mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getOffReminder();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getOverspeedLock() {
        Log.i(TAG, "getOverspeedLock is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getOverspeedLock();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPM25Power() {
        Log.i(TAG, "getPM25Power is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPM25Power();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPM25SwitchCheck() {
        Log.i(TAG, "getPM25SwitchCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPM25SwitchCheck();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPM25TimeCheck() {
        Log.i(TAG, "getPM25TimeCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPM25TimeCheck();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPabCode() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getPabCode();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPABCode mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getPabState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getPabState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPabState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getParkBrakeInd() {
        String str;
        Log.i(TAG, "getParkBrakeInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getParkBrakeInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getParkBrakeInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getPassangerSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getPassangerSaveOrRecoverState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPassangerSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPassengerSeatElectricAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
        return -1;
    }

    public int getPassengerSeatHeadrestConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPassengerSeatHeadrestConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPassengerSeatLegSupportAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
        return -1;
    }

    public int getPassengerSeatMemoryFunctionCanByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCanByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPassengerSeatMemoryFunctionCanByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getPassengerState(int i) {
        Log.i(TAG, "getPassengerState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public int getRainAutoLockWindowState() {
        Log.i(TAG, "getRainAutoLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRainAutoLockWindowState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRearAcOnlineState() {
        Log.i(TAG, "getRearAcOnlineState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearAcOnlineState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRearAcPanelAutoLockTime() {
        String str;
        Log.i(TAG, "getRearAcPanelAutoLockTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getRearAcPanelAutoLockTime mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getRearAcPanelAutoLockTime();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getRearLeftBackrestAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftBackrestAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftBackrestAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftBidetAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftBidetAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftBidetAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftEcuSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getRearLeftEcuSaveOrRecoverState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftEcuSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftHeadrestHeightAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftHeadrestLevelAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftHeightAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeightAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftHeightAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftLegSupportAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftLegSupportAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftLevelAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftLevelAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftLevelAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftMemorySwitchConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearLeftMemorySwitchConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftMemorySwitchConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearLeftSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getRearLeftSaveOrRecoverState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearLeftSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightBackrestAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearRightBackrestAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightBackrestAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightBidetAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearRightBidetAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightBidetAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightEcuSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getRearRightEcuSaveOrRecoverState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightEcuSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestHeightAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightHeadrestHeightAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestLevelAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightHeadrestLevelAdjustmentConfigurationByECU is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightHeightAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeightAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightHeightAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightLegSupportAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearRightLegSupportAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightLegSupportAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightLevelAdjustmentConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearRightLevelAdjustmentConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightLevelAdjustmentConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightMemorySwitchConfigurationByEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearRightMemorySwitchConfigurationByECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightMemorySwitchConfigurationByECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearRightSaveOrRecoverState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getRearRightSaveOrRecoverState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearRightSaveOrRecoverState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearSeatGreetState() {
        Log.v(TAG, "getRearSeatGreetState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearSeatGreetState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRearSeatReclineConfiguration() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRearSeatReclineConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRearSeatReclineConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRearViewEcuMirrorFlipState() {
        Log.v(TAG, "getRearViewEcuMirrorFlipState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearViewEcuMirrorFlipState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRearViewMirrorFlip() {
        Log.i(TAG, "getRearViewMirrorFlip is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearViewMirrorFlip();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRearViewMirrorFlipSet() {
        Log.v(TAG, "getRearViewMirrorFlipSet is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getRearViewMirrorFlipSet();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getRearViewMirrorFlipSet mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public float getRearviewMirrorX(int i) {
        Log.i(TAG, "getRearviewMirrorX is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public float getRearviewMirrorY(int i) {
        Log.i(TAG, "getRearviewMirrorY is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public int getRefrigeratorDoorState() {
        Log.i(TAG, "getRefrigeratorDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorDoorState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRefrigeratorMode() {
        Log.i(TAG, "getRefrigeratorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public int getRefrigeratorState() {
        Log.i(TAG, "getRefrigeratorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getRefrigeratorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getRemenberPowerOffState() {
        Log.i(TAG, "getRemenberPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRemenberPowerOffState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRemoteControlDownwindowState() {
        Log.i(TAG, "getRemoteControlDownwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRemoteControlDownwindowState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Deprecated
    public int getRemoteControlLockWindowState() {
        Log.i(TAG, "getRemoteControlLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRemoteControlLockWindowState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Deprecated
    public int getRemoteControlUnlockWindowState() {
        Log.i(TAG, "getRemoteControlUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRemoteControlUnlockWindowState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRemoteControlUpwindowState() {
        Log.i(TAG, "getRemoteControlUpwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRemoteControlUpwindowState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRemoteKeyStartAcTime() {
        Log.i(TAG, "getRemoteKeyStartAcTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRemoteKeyStartAcTime();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getReportVolume() {
        Log.i(TAG, "getReportVolume is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getReportVolume();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRightHeadlampLevel() {
        String str;
        Log.i(TAG, "getRightHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getRightHeadlampLevel mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getRightHeadlampLevel();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getRightRearMassageChairConfigurationForEcu() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRightRearMassageChairConfigurationForECU mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRightRearMemoryConfiguration() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRightRearMemoryConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRightRearMemoryConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRightRearSeatBackAdjustmentConfiguration() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRightRearSeatBackAdjustmentConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRightRearSeatHorizontalAdjustmentConfiguration() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRightRearSeatHorizontalAdjustmentConfiguration is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRightRearSeatLegBracket() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRightRearSeatLegBracket mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRightRearSeatReclineConfigurationByEuc() {
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
                return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfigurationByEUC();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getRightRearSeatReclineConfigurationByEUC mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getRightRseChildModeSwitchStatusR() {
        Log.i(TAG, "getRightRseChildModeSwitchStatusR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRightRseChildModeSwitchStatusR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRrseChildModeCodeWriteFbR() {
        Log.i(TAG, "getRrseChildModeCodeWriteFbR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRrseChildModeCodeWriteFbR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRrseChildModeTimeWriteFbR() {
        Log.i(TAG, "getRrseChildModeTimeWriteFbR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRrseChildModeTimeWriteFbR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRrseMachineSwitchStateR() {
        Log.i(TAG, "getRrseMachineSwitchStateR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRrseMachineSwitchStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRuningCloseDoorState() {
        Log.v(TAG, "getRuningCloseDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getRuningCloseDoorState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getRuningCloseDoorState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSOCTarget() {
        Log.i(TAG, "getSOCTarget is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSOCTarget();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSafeBeltCloseDriveDoor() {
        Log.v(TAG, "getSafeBeltCloseDriveDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSafeBeltCloseDriveDoor();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSafeBeltInd() {
        String str;
        Log.i(TAG, "getSafeBeltInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getSafeBeltInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getSafeBeltInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSafeBeltWarnState() {
        Log.v(TAG, "getSafeBeltWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSafeBeltWarnState();
    }

    public int getSafeWarnState() {
        Log.i(TAG, "getSafeWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSafeWarnState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getScreenAngle() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getScreenAngle();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getScreenAngle mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getScreenAngleValue() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getScreenAngleValue();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getScreenAngleValue mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getScreenAntiPinchState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getScreenAntiPinchState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getScreenAntiPinchState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getScreenRotationAction() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getScreenRotationAction();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getScreenRotationAction mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getScreenRotationOverHotState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getScreenRotationOverHotState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getScreenRotationOverHotState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getScreenVoltageState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getScreenVoltageState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getScreenVoltageState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSeatHeatingNotL1PState() {
        Log.v(TAG, "getSeatHeatingNotL1PState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingNotL1PState();
    }

    public int getSeatHeatingState(int i) {
        String str;
        Log.i(TAG, "getSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getSeatHeatingState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSeatHeatingState2(int i) {
        Log.v(TAG, "getSeatHeatingState2 is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateRearLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateRearRight();
        }
        return 0;
    }

    public int getSeatHorization(int i) {
        Log.i(TAG, "getSeatHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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
            throw null;
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

    public int getSeatVentilatingNotL1PState() {
        Log.v(TAG, "getSeatVentilatingNotL1PState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingNotL1PState();
    }

    public int getSeatVentilatingState(int i) {
        String str;
        Log.i(TAG, "getSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getSeatVentilatingState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSeatVentilatingState2(int i) {
        Log.v(TAG, "getSeatVentilatingState2 is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (i == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingState2RearLeft();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingState2RearRight();
        }
        return 0;
    }

    public float getSeatbackrestPostion(int i) {
        Log.i(TAG, "getSeatbackrestPostion is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public int getSmartWelcomeLightState() {
        String str;
        Log.i(TAG, "getSmartWelcomeLightState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getSmartWelcomeLightState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getSmartWelcomeLightState();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSpeedReminderValue() {
        Log.i(TAG, "getSpeedReminderValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSpeedReminderValue();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSpeedSupportedTurn() {
        Log.v(TAG, "getSpeedSupportedTurn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSpeedSupportedTurn();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getStartOrPowerInd() {
        String str;
        Log.i(TAG, "getStartOrPowerInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getStartOrPowerInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getStartOrPowerInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSteerAssis() {
        Log.i(TAG, "getSteerAssis is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSteerAssis();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSteerAssisSupported() {
        Log.i(TAG, "getSteerAssisSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSteerAssisSupported mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSteerAssisSupported();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSteerPositionAutoReturn() {
        Log.i(TAG, "getSteerPositionAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSteerPositionAutoReturn();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSteeringWheelHeatingAutoState() {
        Log.v(TAG, "getSteeringWheelHeatingAutoState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatingAutoState();
    }

    public int getSteeringWheelHeatingGear() {
        Log.v(TAG, "getSteeringWheelHeatingGear is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatingGear();
    }

    public int getSteeringWheelHeatingState() {
        String str;
        Log.i(TAG, "getSteeringWheelHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getSteeringWheelHeatingState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatingState();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public float getSteeringWheelY() {
        Log.i(TAG, "getSteeringWheelY is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelY();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getSteeringWheelZ() {
        Log.i(TAG, "getSteeringWheelZ is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelZ();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public int getStopRemoteCtrlDriveInd() {
        String str;
        Log.i(TAG, "getStopRemoteCtrlDriveInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getStopRemoteCtrlDriveInd mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getStopRemoteCtrlDriveInd();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportDoorHeight() {
        Log.i(TAG, "getSupportDoorHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSupportDoorHeight();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSupportSeatMassage(int i) {
        String str;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            str = "getSupportSeatMassage mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getSupportSeatMassage(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTailBoxTakeState() {
        Log.v(TAG, "getTailBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTailBoxTakeState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTailBoxTakeSupported() {
        Log.v(TAG, "getTailBoxTakeSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTailBoxTakeSupported();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTrailerLightCheckState() {
        Log.v(TAG, "getTrailerLightCheckState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTrailerLightCheckState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTrailerModeCode() {
        Log.v(TAG, "getTrailerModeCode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTrailerModeCode();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTrailerModeMileage() {
        Log.v(TAG, " getTrailerModeMileage.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTrailerModeMileage();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTrailerModeState() {
        Log.v(TAG, "getTrailerModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTrailerModeState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTrayActionState() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().getTrayActionState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTrayActionState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getTrayInitState() {
        Log.i(TAG, "getTrayInitState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getTrayInitState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTrayInitState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getTrayState() {
        Log.i(TAG, "getTrayState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTrayState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public float getTurnCaliperSplintHeight(int i) {
        Log.i(TAG, "getTurnCaliperSplintHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public float getTurnCushionHeight(int i) {
        Log.i(TAG, "getTurnCushionHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            throw null;
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

    public int getVspState() {
        Log.v(TAG, "getVSPState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getVspState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getWelcomeActive() {
        Log.v(TAG, "getWelcomeActive is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getWelcomeActive();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getWelcomeState() {
        Log.v(TAG, "getWelcomeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getWelcomeState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getWelcomeSupported() {
        Log.v(TAG, "getWelcomeSupported is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().getWelcomeSupported();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int hasFeature(String str) {
        Log.i(TAG, "hasFeature is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().hasFeature(str);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int ialDefaultReset(int i) {
        Log.v(TAG, "ialDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int intelligentDriveDefaultReset(int i) {
        Log.v(TAG, "intelligentDriveDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int isSomethingLeftInRefrigerator() {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().isSomethingLeftInRefrigerator();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " isSomethingLeftInRefrigerator mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int laneAssistDriveDefaultReset(int i) {
        Log.v(TAG, "laneAssistDriveDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int languageDefaultReset(int i) {
        Log.v(TAG, "languageDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().languageDefaultReset(i);
    }

    public int lightDefaultReset(int i) {
        Log.v(TAG, "lightDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int locksDefaultReset(int i) {
        Log.v(TAG, "locksDefaultReset is execute");
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

    public int notifyEnterTunnel(int i) {
        Log.v(TAG, "notifyEnterTunnel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().notifyEnterTunnel(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int notifyTunnelLength(int i) {
        Log.v(TAG, "notifyTunnelLength is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().notifyTunnelLength(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoSettingListener> it = this.mAbsBydAutoSettingListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public int outChargeDefaultReset(int i) {
        Log.v(TAG, "outChargeDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().outChargeDefaultReset(i);
    }

    public int rearMirrorDefaultReset(int i) {
        Log.v(TAG, "rearMirrorDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
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
            throw null;
        }
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (!this.mAbsBydAutoSettingListeners.contains(absBYDAutoSettingListener)) {
            this.mAbsBydAutoSettingListeners.add(absBYDAutoSettingListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoSettingListener);
        TsManagerImpl.getInstance(this.mContext).registerSettingCallback();
    }

    @Deprecated
    public int resetAutoExternalRearMirrorFollowUp() {
        Log.i(TAG, "resetAutoExternalRearMirrorFollowUp is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().resetAutoExternalRearMirrorFollowUp();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int resetAutoPowerOnState() {
        Log.i(TAG, "resetAutoPowerOnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().resetAutoPowerOnState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public void resetCaliperSplintHeight(int i, int i2) {
        Log.i(TAG, "resetCaliperSplintHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        this.mTsManagerImpl.getSettingAdapterManager().resetCaliperSplintHeight(i, i2);
    }

    public void resetCushionHeight(int i, int i2) {
        Log.i(TAG, "resetCushionHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        this.mTsManagerImpl.getSettingAdapterManager().resetCushionHeight(i, i2);
    }

    public int resetHeadrestParms(int i, int i2) {
        Log.i(TAG, "resetHeadrestParms is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().resetHeadrestParms(i, i2);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Deprecated
    public int resetHudBrightness() {
        Log.i(TAG, "resetHudBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHudBrightness();
    }

    @Deprecated
    public void resetHudDisplayContent() {
        Log.i(TAG, "resetHudDisplayContent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        this.mTsManagerImpl.getSettingAdapterManager().resetHudDisplayContent();
    }

    @Deprecated
    public int resetHudHeight() {
        Log.i(TAG, "resetHudHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHudHeight();
    }

    @Deprecated
    public int resetHudMode() {
        Log.i(TAG, "resetHudMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHudMode();
    }

    @Deprecated
    public void resetHudRotationAngle() {
        Log.i(TAG, "resetHudRotationAngle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        this.mTsManagerImpl.getSettingAdapterManager().resetHudRotationAngle();
    }

    @Deprecated
    public int resetHudState() {
        Log.i(TAG, "resetHudState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().resetHudState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int resetMotorPretensionerSeatbelt() {
        Log.i(TAG, "resetMotorPretensionerSeatbelt is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().resetMotorPretensionerSeatbelt();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public void resetRearviewMirrorParams(int i, int i2) {
        Log.i(TAG, "resetRearviewMirrorParams is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        this.mTsManagerImpl.getSettingAdapterManager().resetRearviewMirrorParams(i, i2);
    }

    public void resetRemenberPowerOff() {
        Log.i(TAG, "resetRemenberPowerOff is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        this.mTsManagerImpl.getSettingAdapterManager().resetRemenberPowerOff();
    }

    public void resetSeatParams(int i, int i2) {
        Log.i(TAG, "resetSeatParams is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
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

    public int resetSetting(int[] iArr, int[] iArr2) {
        Log.i(TAG, "resetSetting is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().resetSetting(iArr, iArr2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public void resetSteeringWheelPosition(int i) {
        Log.i(TAG, "resetSteeringWheelPosition is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        this.mTsManagerImpl.getSettingAdapterManager().resetSteeringWheelPosition(i);
    }

    public int resetSystemSetting(int i) {
        Log.i(TAG, "restoreSettingItem is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().restoreSettingItem(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int safeAssistDefaultReset(int i) {
        Log.v(TAG, "safeAssistDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int saveSeatParamsAll(int i, int i2) {
        Log.i(TAG, "saveSeatParamsAll is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public int sendOffReminder(int i) {
        Log.i(TAG, "sendOffReminder is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().sendOffReminder(i);
        }
        Log.v(TAG, "adapter is not connect");
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

    public int setACAutoAir(int i) {
        Log.i(TAG, "setACAutoAir is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setACAutoAir(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setACBTWind(int i) {
        Log.i(TAG, "setACBTWind is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setACBTWindS4E3(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setACPauseCycle(int i) {
        Log.i(TAG, "setACPauseCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setACPauseCycle(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setACTunnelCycle(int i) {
        Log.i(TAG, "setACTunnelCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setACTunnelCycle(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAadsSwitchSate(int i) {
        Log.v(TAG, "setAadsSwitchSate is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAfsSwitchSate(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAcTemperatureUnit(int i) {
        Log.i(TAG, "setAcTemperatureUnit is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAcTemperatureUnit(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAcWarmMode(int i) {
        Log.i(TAG, "setAcWarmMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAcWarmMode(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAdasRest(int[] iArr) {
        Log.v(TAG, " setAdasRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAdasRest(iArr);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAidedDrivingInd(int i) {
        Log.v(TAG, "setAidedDrivingInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setAidedDrivingInd(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setAidedDrivingInd mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        Log.v(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMulticolorMode(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMulticolorState(int i, int i2) {
        Log.v(TAG, "setAmbientMulticolorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMulticolorState(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicMode(int i, int i2) {
        Log.v(TAG, "setAmbientMusicMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMusicMode(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicModeState(int i, int i2) {
        Log.v(TAG, "setAmbientMusicModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMusicModeState(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAppUseLimitState(int i) {
        Log.i(TAG, "setAppUseLimitState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAppUseLimitState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAutoExternalRearMirrorFollowUpSwitch(int i) {
        Log.i(TAG, "setAutoExternalRearMirrorFollowUpSwitch is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAutoExternalRearMirrorFollowUpSwitch(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAutoPowerOffState(int i) {
        Log.i(TAG, "setAutoPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAutoPowerOffState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAutoPowerOnState(int i) {
        Log.i(TAG, "setAutoPowerOnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAutoPowerOnState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAutoPurification(int i) {
        Log.i(TAG, "setAutoPurification is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAutoPurification(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAutoRainWiperState(int i) {
        Log.i(TAG, "setAutoRainWiperState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAutoRainWiperState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAutomaticParking(int i) {
        Log.i(TAG, "setAutomaticParking is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setAutomaticParking(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setBackDoorElectricMode(int i) {
        Log.i(TAG, "setBackDoorElectricMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setBackDoorElectricMode(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setBackDoorOpenedHeight(int i) {
        Log.i(TAG, "setBackDoorOpenedHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setBackDoorOpenedHeight(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setBackHomeLightDelay(int i) {
        Log.i(TAG, "setBackHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setBackHomeLightDelay(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setBackHomeLightDelayValue(int i) {
        Log.i(TAG, "setBackHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setBackHomeLightDelayValue(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setBleOpenDriverDoorState(int i) {
        Log.v(TAG, "setBleOpenDriverDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setBleOpenDriverDoorState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setBrakeCloseDoor(int i) {
        Log.v(TAG, "setBrakeCloseDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setBrakeCloseDoor(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setBrakePedalMode(int i) {
        Log.i(TAG, "setBrakePedalMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setBrakePedalMode(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setBsisCollisionStateS(int i) {
        Log.i(TAG, "setBsisCollisionStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setBsisCollisionStateS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCallState(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setCallState(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setCallState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setCamping(int i) {
        Log.v(TAG, "setCamping is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCamping(i);
    }

    public int setCampingBlanceState(int i) {
        Log.v(TAG, "setCampingBlanceState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setCampingBlanceState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCarTipsSoundType(int i) {
        Log.i(TAG, "setCarTipsSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setCarTipsSoundType(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCarsettingRest(int[] iArr) {
        Log.v(TAG, " setCarsettingRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setCarsettingRest(iArr);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setChargingPort(int i) {
        Log.i(TAG, "setChargingPort is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setChargingPort(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCheckSum(int i) {
        Log.v(TAG, "setCheckSum is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setCheckSum(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCloseDoorLevel(int i) {
        Log.v(TAG, "setCloseDoorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setCloseDoorLevel(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setComSpeedDownState(int i) {
        Log.v(TAG, "setComSpeedDownState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setComSpeedDownState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setComSpeedDownState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setComSpeedUpState(int i) {
        Log.v(TAG, "setComSpeedUpState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setComSpeedUpState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setComSpeedUpState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setComfortableParkingState(int i) {
        Log.i(TAG, "setComfortableParkingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setComfortableParkingState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCvcMode(int i) {
        Log.v(TAG, "setCvcMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setCvcMode(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDampState(int i) {
        Log.v(TAG, "setDampState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDampState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDisinfState(int i) {
        Log.i(TAG, "setDisinfState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDisinfState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDlinkRest(int[] iArr) {
        Log.v(TAG, " setDlinkRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDlinkRest(iArr);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDoorknobAutoShrinkState(int i) {
        Log.i(TAG, "setDoorknobAutoShrinkState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDoorknobAutoShrinkState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveDoorCloseInd(int i) {
        Log.i(TAG, "setDriveDoorCloseInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDriveDoorCloseInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setDriverSeatAutoReturn(int i) {
        Log.i(TAG, "setDriverSeatAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDriverSeatAutoReturn(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriverSeatState(int i) {
        Log.i(TAG, "setDriverSeatState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setDriverSeatState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDriverSeatState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDyHeightByVoice(int i) {
        Log.v(TAG, "setDyHeightByVoice is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightByVoice(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDyHeightToggleState(int i) {
        Log.v(TAG, "setDyHeightToggleState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightToggleState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDyHeightValue(int i) {
        Log.v(TAG, "setDyHeightValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightValue(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDynamicSuspensionMode(int i) {
        Log.i(TAG, "setDynamicSuspensionMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setDynamicSuspensionMode(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setEnergyFeedback(int i) {
        Log.i(TAG, "setEnergyFeedback is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setEnergyFeedback(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setEngineSoundType(int i) {
        Log.i(TAG, "setEngineSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setEngineSoundType(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setExhibitionStartEnginePermit(int i) {
        Log.i(TAG, "setExhibitionStartEnginePermit is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setExhibitionStartEnginePermit(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        String str;
        int iIntValue;
        String string;
        StringBuilder sb;
        String str2;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    str = "the type of value is not a suitable type";
                    Log.v(TAG, str);
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 377) {
            setHudScreenProjectionState(iIntValue);
            string = "Setting.SET_INTELLIGENT_PROIECTION_SWITCH_SET:";
        } else if (i == 378) {
            setAadsSwitchSate(iIntValue);
            string = "Setting.SET_AFS_STATE_SET:";
        } else if (i == 391) {
            setHudBrightness(iIntValue);
            string = "Setting.SET_BRIGHTNESS_GEAR_SET:";
        } else if (i == 392) {
            setBackDoorElectricMode(iIntValue);
            string = "Setting.SET_CAR_BACK_DOOR_ELECTRIC_MODE_SET:";
        } else if (i != 446) {
            if (i == 447) {
                factoryResetAll(iIntValue);
                sb = new StringBuilder();
                str2 = "SET_FACTORY_RESET_ALL_SET:";
            } else if (i == 451) {
                setHudHeight(iIntValue);
                string = "Setting.SET_HEIGHT_GEAR_SET:";
            } else if (i == 452) {
                setHudPattern(iIntValue);
                string = "Setting.SET_HUD_MODE_CHOICE_SET:";
            } else if (i == 454) {
                setHudMode(iIntValue);
                string = "Setting.SET_HUD_MODE_SET:";
            } else if (i == 455) {
                setHudState(iIntValue);
                string = "Setting.SET_HUD_SWITCH_SET:";
            } else if (i == 477) {
                setRestMode(iIntValue);
                string = "Setting.SET_NAP_MODE_SET:";
            } else if (i != 478) {
                str2 = "setSeatVentilatingStateDriver:";
                switch (i) {
                    case 380:
                        setHudRotationAngle(iIntValue);
                        string = "Setting.SET_ANGEL_GERA_SET:";
                        break;
                    case 383:
                        setAutoRainWiperState(iIntValue);
                        string = "Setting.SET_AUTO_RAIN_WIPER_SWITCH_SET:";
                        break;
                    case 385:
                        setSteeringWheelHeatingGear(iIntValue);
                        string = "Setting.SET_AUTO_STEER_WHEEL_HEATING_GEAR_SET:";
                        break;
                    case 387:
                        setSteeringWheelHeatingAutoState(iIntValue);
                        string = "Setting.SET_AUTO_STEER_WHEEL_HEATING_MODE_SET:";
                        break;
                    case 389:
                        backLightDefaultReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_BACK_LIGHT_RESET_SET:";
                        break;
                    case 394:
                        setBackHomeLightDelayValue(iIntValue);
                        string = "Setting.SET_CAR_BACK_HOME_LIGHT_DELAY_VALUE_SET:";
                        break;
                    case 396:
                        setRemoteControlDownwindowState(iIntValue);
                        string = "Setting.SET_CAR_CTRL_WINDOW_SET:";
                        break;
                    case 398:
                        setDriverSeatAutoReturn(iIntValue);
                        string = "Setting.SET_CAR_DV_AUTO_RETURN_SET:";
                        break;
                    case 400:
                        setAutoExternalRearMirrorFollowUpSwitch(iIntValue);
                        string = "Setting.SET_CAR_EXTREARMIR_FOLLOWUP_SWITCH_SET:";
                        break;
                    case 402:
                        setLeftHomeLightDelayValue(iIntValue);
                        string = "Setting.SET_CAR_LEFT_HOME_LIGHT_DELAY_VALUE_SET:";
                        break;
                    case 404:
                        setLockCarRiseWindow(iIntValue);
                        string = "Setting.SET_CAR_LOCK_CLOSEWINDOW_SET:";
                        break;
                    case 407:
                        setMicroSwitchUnlockWindowState(iIntValue);
                        string = "Setting.SET_CAR_LPUNLOCKWINDOW_SET:";
                        break;
                    case 409:
                        setMicroSwitchLockWindowState(iIntValue);
                        string = "Setting.SET_CAR_MICRO_SWITCH_LOCK_WINDOW_SET:";
                        break;
                    case 411:
                        setPM25Power(iIntValue);
                        string = "Setting.SET_CAR_PM25_POWER_SET:";
                        break;
                    case 413:
                        setPM25SwitchCheck(iIntValue);
                        string = "Setting.SET_CAR_PM25_SW_CHECK_SET:";
                        break;
                    case 415:
                        setPM25TimeCheck(iIntValue);
                        string = "Setting.SET_CAR_PM25_TIME_SET:";
                        break;
                    case 418:
                        setRearViewMirrorFlip(iIntValue);
                        string = "Setting.SET_CAR_REAR_VIEW_SET:";
                        break;
                    case 426:
                        setDynamicSuspensionMode(iIntValue);
                        string = "Setting.SET_COMFORT_MODE_CONTROL_SWITCH_SIGNAL_SET:";
                        break;
                    case 428:
                        doorsAndWindowDefaultResset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_DOOR_WINDOW_RESET_SET:";
                        break;
                    case 430:
                        setChargingPort(iIntValue);
                        string = "Setting.SET_DR_CHARGER_PORT_SET:";
                        break;
                    case 432:
                        setEnergyFeedback(iIntValue);
                        string = "Setting.SET_DR_ENERGY_FB_SET:";
                        break;
                    case 434:
                        setSOCTarget(iIntValue);
                        string = "Setting.SET_DR_SOC_TARGET_SET:";
                        break;
                    case 442:
                        setHudDisplayContent(2, iIntValue);
                        string = "Setting.SET_DYNAMIC_NAVI_FUNCTION_STATUS_SET:";
                        break;
                    case 449:
                        setDoorknobAutoShrinkState(iIntValue);
                        string = "Setting.SET_HANDLE_AUTO_RESET_SET:";
                        break;
                    case 464:
                        turnSeatbackrest(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_LR_BACKREST_SIGNAL_SET:";
                        break;
                    case 465:
                        turnSeatHorization(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_LR_BALANCE_SIGNAL_SET:";
                        break;
                    case 466:
                        turnSeatCushion(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_LR_BIDET_SIGNAL_SET:";
                        break;
                    case 467:
                        turnHeadrestTurnHeight(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_LR_HEADREST_SIGNAL_SET:";
                        break;
                    case 468:
                        turnSeatHeight(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_LR_HEIGHT_SIGNAL_SET:";
                        break;
                    case 469:
                        turnSeatCaliperSplint(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_LR_LEG_REST_SIGNAL_SET:";
                        break;
                    case 470:
                        saveSeatParamsAll(iIntValue, 3);
                        string = "Setting.SET_LR_MEMORY_LOCATION_SET:";
                        break;
                    case 471:
                        resetSeatParams(iIntValue, 3);
                        sb = new StringBuilder();
                        str2 = "SET_LR_MEMORY_LOCATION_WAKE_SET:";
                        break;
                    case 480:
                        setOverspeedLock(iIntValue);
                        string = "Setting.SET_OVERSPEED_LOCKING_STATE_SET:";
                        break;
                    case 481:
                        setScreenRotation(iIntValue);
                        string = "Setting.SET_PAD_ROTATION_SET:";
                        break;
                    case 484:
                        setPetMode(iIntValue);
                        string = "Setting.SET_PET_MODE_SET:";
                        break;
                    case 485:
                        lightDefaultReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_READING_LIGHT_RESET_SET:";
                        break;
                    case 487:
                        setRearAcPanelAutoLockTime(iIntValue);
                        string = "Setting.SET_REAR_AC_PANEL_LOCK_TIME_SET:";
                        break;
                    case 494:
                        rearMirrorDefaultReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_REAR_MIRROR_RESET_SET:";
                        break;
                    case 497:
                        setLockOff(iIntValue);
                        string = "Setting.SET_REMOTE_CONTROL_UNLOCKING_SET:";
                        break;
                    case 498:
                        carSettingsReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RESTORE_CAR_DEFAULT_VALUES_SET:";
                        break;
                    case 499:
                        driveSettingsReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RESTORE_DR_DEFAULT_VALUES_SET:";
                        break;
                    case 500:
                        turnSeatbackrest(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RF_BACKREST_SIGNAL_SET:";
                        break;
                    case 501:
                        turnSeatHorization(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RF_BALANCE_SIGNAL_SET:";
                        break;
                    case 502:
                        turnSeatCushion(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RF_BIDET_SIGNAL_SET:";
                        break;
                    case 503:
                        turnHeadrestTurnHeight(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RF_HEADREST_SIGNAL_SET:";
                        break;
                    case 504:
                        turnSeatHeight(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RF_HEIGHT_SIGNAL_SET:";
                        break;
                    case 505:
                        turnSeatCaliperSplint(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RF_LEG_REST_SIGNAL_SET:";
                        break;
                    case 506:
                        saveSeatParamsAll(iIntValue, 2);
                        sb = new StringBuilder();
                        str2 = "SET_RF_MEMORY_LOCATION_SET:";
                        break;
                    case 507:
                        resetSeatParams(iIntValue, 2);
                        sb = new StringBuilder();
                        str2 = "SET_RF_MEMORY_LOCATION_WAKE_SET:";
                        break;
                    case 508:
                        turnSeatbackrest(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RR_BACKREST_SIGNAL_SET:";
                        break;
                    case 509:
                        turnSeatHorization(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RR_BALANCE_SIGNAL_SET:";
                        break;
                    case 510:
                        turnSeatCushion(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RR_BIDET_SIGNAL_SET:";
                        break;
                    case 511:
                        turnHeadrestTurnHeight(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RR_HEADREST_SIGNAL_SET:";
                        break;
                    case 512:
                        turnSeatHeight(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RR_HEIGHT_SIGNAL_SET:";
                        break;
                    case 513:
                        turnSeatCaliperSplint(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_RR_LEG_REST_SIGNAL_SET:";
                        break;
                    case 514:
                        saveSeatParamsAll(iIntValue, 4);
                        sb = new StringBuilder();
                        str2 = "SET_RR_MEMORY_LOCATION_SET:";
                        break;
                    case 515:
                        resetSeatParams(iIntValue, 4);
                        sb = new StringBuilder();
                        str2 = "SET_RR_MEMORY_LOCATION_WAKE_SET:";
                        break;
                    case 517:
                        setHudDisplayContent(1, iIntValue);
                        string = "Setting.SET_SAFE_DRIVING_ASSIST_STATUS_SET:";
                        break;
                    case 518:
                        setHudSafeDriveState(iIntValue);
                        string = "Setting.SET_SAFETY_DRIVING_AID_FUSION_SWITCH_SET:";
                        break;
                    case 519:
                        autoBackDefaultReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_SEAT_SELF_RETURN_RESET_SET:";
                        break;
                    case 521:
                        setAutoPowerOnState(iIntValue);
                        string = "Setting.SET_SMART_ELECTRICITY_SET:";
                        break;
                    case 524:
                        setSpeedReminderValue(iIntValue);
                        string = "Setting.SET_SPEED_REMINDER_SET:";
                        break;
                    case 526:
                        setSteeringWheelHeatingState(iIntValue);
                        string = "Setting.SET_STEERING_WHEEL_HEAT_STATE_SET:";
                        break;
                    case 527:
                        clockDefaultReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_TIME_RESET_SET:";
                        break;
                    case 528:
                        setKeyVibratorLevel(iIntValue);
                        string = "Setting.SET_VIBRATION_SENSING_GEAR_SIGNAL:";
                        break;
                    case 529:
                        setKeyVibratorLevel(iIntValue);
                        string = "Setting.SET_VIBRATION_SENSING_GEAR_SIGNAL_SET:";
                        break;
                    case 530:
                        voiceCtlBackDoor(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_VOICE_CTRL_BACK_DOOR_SET:";
                        break;
                    case 531:
                        outChargeDefaultReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_VTO_RESET_SET:";
                        break;
                    case 532:
                        setWipeMode(iIntValue);
                        string = "Setting.SET_WIPE_SCREEN_MODE_SET:";
                        break;
                    case 688:
                        setINSTheme(iIntValue);
                        string = "Setting.SETTING_INSTRUMENT_THEME_SET:";
                        break;
                    case 752:
                        setRearSeatsFoldOrExpand(iIntValue);
                        string = "Setting.SETTING_REAR_SEAT_ONE_KEY_SET:";
                        break;
                    case 838:
                        setUiVersion(iIntValue);
                        string = "Setting.SETTING_UI_PLATFORM_SET:";
                        break;
                    case 878:
                        setACAutoAir(iIntValue);
                        string = "Setting.SET_AC_AUTO_AIR_SET:";
                        break;
                    case 882:
                        setACBTWind(iIntValue);
                        string = "Setting.SET_AC_BT_SET:";
                        break;
                    case 887:
                        setACTunnelCycle(iIntValue);
                        string = "Setting.SET_AC_TUNNEL_CYCLE_SET:";
                        break;
                    case 933:
                        setBackDoorOpenedHeight(iIntValue);
                        return 1;
                    case 1029:
                        setMassageMode(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setMassageMode2:";
                        break;
                    case 1030:
                        setMassageLevel(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setMassageLevel2:";
                        break;
                    case 1042:
                        setSeatHeatingState(1, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setSeatHeatingStateDriver:";
                        break;
                    case 1045:
                        setSeatVentilatingState(1, iIntValue);
                        sb = new StringBuilder();
                        break;
                    case 1156:
                        setIALBrightness(2, iIntValue, 2);
                        sb = new StringBuilder();
                        str2 = "setIALBrightnessBack:";
                        break;
                    case 1157:
                        setIALColor(2, iIntValue, 1);
                        sb = new StringBuilder();
                        str2 = "setIALColorFrontBack:";
                        break;
                    case 1160:
                        setIALBrightness(1, iIntValue, 1);
                        sb = new StringBuilder();
                        str2 = "setIALBrightnessFront:";
                        break;
                    case 1161:
                        setIALColor(1, iIntValue, 1);
                        sb = new StringBuilder();
                        str2 = "setIALColorFront:";
                        break;
                    case 1172:
                        setIALArea(iIntValue, 0);
                        string = "Setting.SET_INTERIOR_ATMOSPHERE_LAMP_AREA_SET:";
                        break;
                    case 1174:
                        setIALBrightness(5, iIntValue, 1);
                        sb = new StringBuilder();
                        str2 = "SET_INTERIOR_ATMOSPHERE_LAMP_BRIGHTNESS_SET:";
                        break;
                    case 1177:
                        setIALColor(this.mTsManagerImpl.getCarBodyManager().getLastSetedArea(), iIntValue, 0);
                        sb = new StringBuilder();
                        break;
                    case 1178:
                        setIALArea(0, iIntValue);
                        string = "Setting.SET_INTERIOR_ATMOSPHERE_LAMP_SOURCE_SET:";
                        break;
                    case 1184:
                        setMode(iIntValue);
                        string = "Setting.SET_IVI_SCENE_MODE_SET:";
                        break;
                    case 1188:
                        languageDefaultReset(iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_LANGUAGE_RESET_SET:";
                        break;
                    case 1190:
                        setLanguage(iIntValue);
                        string = "Setting.SET_LANGUAGE_TYPE_SET:";
                        break;
                    case 1201:
                        setMassageMode(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setMassageLevel3:";
                        break;
                    case 1209:
                        setMassageMode(1, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setMassageMode1:";
                        break;
                    case 1210:
                        setMassageLevel(1, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setMassageLevel1:";
                        break;
                    case 1255:
                        setAmbientMusicModeState(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_MUSIC_MODE_STATE: ";
                        break;
                    case 1257:
                        setAmbientMusicModeState(0, iIntValue);
                        sb = new StringBuilder();
                        str2 = "SET_MUSIC_MODE_STATE_SET: ";
                        break;
                    case 1271:
                        setSeatHeatingState(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setSeatHeatingStatePassenger:";
                        break;
                    case 1274:
                        setSeatVentilatingState(2, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setSeatVentilatingStatePassenger:";
                        break;
                    case 1284:
                        setSeatHeatingState(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setSeatHeatingStateLeft:";
                        break;
                    case 1291:
                        setSeatVentilatingState(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setSeatVentilatingStateLeft:";
                        break;
                    case 1295:
                        setSeatHeatingState(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setSeatHeatingStateRight:";
                        break;
                    case 1302:
                        setSeatVentilatingState(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setSeatVentilatingStateRight:";
                        break;
                    case 1314:
                        setMassageMode(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setMassageLevel4:";
                        break;
                    case 1386:
                        setMassageLevel(3, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setMassageMode3:";
                        break;
                    case 1387:
                        setMassageLevel(4, iIntValue);
                        sb = new StringBuilder();
                        str2 = "setMassageMode4:";
                        break;
                    case 1411:
                        setSafeBeltWarnState(iIntValue);
                        string = "Safety.SAFETY_BELT_ALARM_SWITCH_SET:";
                        break;
                    case 1412:
                        setComfortableParkingState(iIntValue);
                        string = "Safety.SAFETY_BELT_MSR_STATE:";
                        break;
                    case 1413:
                        setMotorPretensionerSeatbeltState(iIntValue);
                        string = "Safety.SAFETY_BELT_MSR_STATE_SET:";
                        break;
                    case 1699:
                        setComSpeedUpState(iIntValue);
                        return 1;
                    case 1704:
                        setComSpeedDownState(iIntValue);
                        return 1;
                    case 1709:
                        setRainAutoLockWindowState(iIntValue);
                        return 1;
                    case 1724:
                        setDisinfState(iIntValue);
                        return 1;
                    case 1728:
                        setRemenberPowerOffState(iIntValue);
                        return 1;
                    case 1740:
                        setHSpeedAutoCloseState(iIntValue);
                        return 1;
                    case 2004:
                        turnRearviewMirror(iIntValue);
                        sb = new StringBuilder();
                        str2 = "turnRearviewMirror:";
                        break;
                    case 2087:
                        setTrayState(iIntValue);
                        return 1;
                    case 2135:
                        turnSteeringWheel(iIntValue);
                        sb = new StringBuilder();
                        str2 = "turnSteeringWheel:";
                        break;
                    case 2548:
                        newEnergyDefaultReset(iIntValue);
                        return 1;
                    case 2658:
                        setAutoPowerOffState(iIntValue);
                        return 1;
                    case 2811:
                        setAmbientMulticolorState(0, iIntValue);
                        return 1;
                    case 2844:
                        setIALBrightness(5, iIntValue, 1);
                        sb = new StringBuilder();
                        str2 = "LIGHT_STAR_RING_ATMOSPHERE_LAMP_BRIGHTNESS:";
                        break;
                    case 2845:
                        setIALColor(5, iIntValue, 1);
                        sb = new StringBuilder();
                        str2 = "LIGHT_STAR_RING_ATMOSPHERE_LAMP:";
                        break;
                    case 2846:
                        setIALBrightness(4, iIntValue, 1);
                        sb = new StringBuilder();
                        str2 = "Light.LIGHT_STAR_SKY_ATMOSPHERE_LAMP_BRIGHTNESS:";
                        break;
                    case 2847:
                        setIALColor(4, iIntValue, 1);
                        sb = new StringBuilder();
                        str2 = "LIGHT_STAR_SKY_ATMOSPHERE_LAMP:";
                        break;
                    case 3159:
                    case 3233:
                        setBrakePedalMode(iIntValue);
                        return 1;
                    case 3162:
                        setComfortableParkingState(iIntValue);
                        return 1;
                    case 3172:
                        intelligentDriveDefaultReset(iIntValue);
                        return 1;
                    case 3254:
                        laneAssistDriveDefaultReset(iIntValue);
                        return 1;
                    case 3280:
                        safeAssistDefaultReset(iIntValue);
                        return 1;
                    case 3373:
                        set5LevelSeatHeatingLevel(1, iIntValue);
                        return 1;
                    case 3375:
                        set5LevelSeatHeatingState(1, iIntValue);
                        return 1;
                    case 3377:
                        set5LevelSeatVentilatingMode(1, iIntValue);
                        return 1;
                    case 3379:
                        set5LevelSeatVentilatingLevel(1, iIntValue);
                        return 1;
                    case 3381:
                        set5LevelSeatVentilatingState(1, iIntValue);
                        return 1;
                    case 3427:
                        set5LevelSeatHeatingLevel(2, iIntValue);
                        return 1;
                    case 3429:
                        set5LevelSeatHeatingState(2, iIntValue);
                        return 1;
                    case 3431:
                        set5LevelSeatVentilatingMode(2, iIntValue);
                        return 1;
                    case 3433:
                        set5LevelSeatVentilatingLevel(2, iIntValue);
                        return 1;
                    case 3435:
                        set5LevelSeatVentilatingState(2, iIntValue);
                        return 1;
                    case 3468:
                        set5LevelSeatHeatingLevel(3, iIntValue);
                        return 1;
                    case 3470:
                        set5LevelSeatHeatingState(3, iIntValue);
                        return 1;
                    case 3472:
                        set5LevelSeatVentilatingMode(3, iIntValue);
                        return 1;
                    case 3474:
                        set5LevelSeatVentilatingLevel(3, iIntValue);
                        return 1;
                    case 3476:
                        set5LevelSeatVentilatingState(3, iIntValue);
                        return 1;
                    case 3491:
                        set5LevelSeatHeatingLevel(4, iIntValue);
                        return 1;
                    case 3493:
                        set5LevelSeatHeatingState(4, iIntValue);
                        return 1;
                    case 3495:
                        set5LevelSeatVentilatingMode(4, iIntValue);
                        return 1;
                    case 3497:
                        set5LevelSeatVentilatingLevel(4, iIntValue);
                        return 1;
                    case 3499:
                        set5LevelSeatVentilatingState(4, iIntValue);
                        return 1;
                    case 3508:
                        carSettingsDefaultReset(iIntValue);
                        return 1;
                    case 3521:
                        setIntelligentAcAreaState(iIntValue);
                        return 1;
                    case 3558:
                        setAcWarmMode(iIntValue);
                        return 1;
                    case 3588:
                        setEngineSoundType(iIntValue);
                        return 1;
                    default:
                        switch (i) {
                            case 420:
                                setRemoteControlUpwindowState(iIntValue);
                                string = "Setting.SET_CAR_REMOTE_CTRL_UPWINDOW_SET:";
                                break;
                            case 421:
                                setSteerPositionAutoReturn(iIntValue);
                                string = "Setting.SET_CAR_ST_AUTO_RETURN_SET:";
                                break;
                            case 422:
                                setHeadlampLevel(iIntValue);
                                string = "Setting.SET_CMD_HEADLAMP_HEIGHT_LEVEL_SET:";
                                break;
                            default:
                                switch (i) {
                                    case 436:
                                        setSteerAssis(iIntValue);
                                        string = "Setting.SET_DR_ST_ASSIS_SET:";
                                        break;
                                    case 437:
                                        turnSeatbackrest(1, iIntValue);
                                        sb = new StringBuilder();
                                        str2 = "SET_DRIVER_SEAT_BACKREST_SIGNAL_SET:";
                                        break;
                                    case 438:
                                        turnSeatHorization(1, iIntValue);
                                        sb = new StringBuilder();
                                        str2 = "SET_DRIVER_SEAT_BALANCE_SIGNAL_SET:";
                                        break;
                                    case 439:
                                        turnSeatCushion(1, iIntValue);
                                        sb = new StringBuilder();
                                        str2 = "SET_DRIVER_SEAT_BIDET_SIGNAL_SET:";
                                        break;
                                    case 440:
                                        turnSeatHeight(1, iIntValue);
                                        sb = new StringBuilder();
                                        str2 = "SET_DRIVER_SEAT_HEIGHT_SIGNAL_SET:";
                                        break;
                                    default:
                                        str2 = "SET_LF_LEG_REST_SIGNAL_SET:";
                                        switch (i) {
                                            case 457:
                                                setInsideLightDoorState(iIntValue);
                                                string = "Setting.SET_INSIDE_LIGHT_STATE_SET:";
                                                break;
                                            case 458:
                                                setLanternLanguageMode(iIntValue);
                                                string = "Setting.SET_LANTERN_LANGUAGE_MODE_SET:";
                                                break;
                                            case 459:
                                                turnHeadrestTurnHeight(1, iIntValue);
                                                sb = new StringBuilder();
                                                str2 = "SET_LF_HEADREST_SIGNAL_SET:";
                                                break;
                                            case 460:
                                                turnSeatCaliperSplint(1, iIntValue);
                                                sb = new StringBuilder();
                                                break;
                                            case 461:
                                                saveSeatParamsAll(iIntValue, 1);
                                                string = "Setting.SET_LF_MEMORY_LOCATION_SET:";
                                                break;
                                            case 462:
                                                resetSeatParams(iIntValue, 1);
                                                sb = new StringBuilder();
                                                break;
                                            default:
                                                str = "case fail ! please check out";
                                                Log.v(TAG, str);
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
                string = "Setting.SET_NAVIGATION_FUSION_SWITCH_SET:";
            }
            sb.append(str2);
            sb.append(iIntValue);
            string = sb.toString();
        } else {
            setExhibitionStartEnginePermit(iIntValue);
            string = "Setting.SET_EXHIBITION_START_ENGINE_PERMIT_SET:";
        }
        Log.v(TAG, string);
        return 1;
    }

    public int setHSpeedAutoCloseState(int i) {
        Log.v(TAG, "setHSpeedAutoCloseState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setHSpeedAutoCloseState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setHSpeedAutoCloseState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setHeadlampLevel(int i) {
        Log.i(TAG, "setHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHeadlampLevel(i);
        }
        Log.v(TAG, "adapter is not connect");
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

    public int setHudBrightness(int i) {
        Log.i(TAG, "setHudBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudBrightness(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setHudDisplayContent(int i, int i2) {
        Log.i(TAG, "setHudDisplayContent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public int setHudHeight(int i) {
        Log.i(TAG, "setHudHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudHeight(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setHudMode(int i) {
        Log.i(TAG, "setHudMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudMode(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setHudNavigationState(int i) {
        Log.v(TAG, "setHudNavigationState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudNavigationState(i);
    }

    public int setHudParamsEffect() {
        Log.i(TAG, "setHudParamsEffect is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudParamsEffect();
    }

    public int setHudPattern(int i) {
        Log.v(TAG, "setHudPattern is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudPattern(i);
    }

    public int setHudRotationAngle(int i) {
        Log.i(TAG, "setHudRotationAngle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudRotationAngle(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setHudSafeDriveState(int i) {
        Log.v(TAG, "setHudSafeDriveState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudSafeDriveState(i);
    }

    public int setHudScreenProjectionState(int i) {
        Log.v(TAG, "setHudScreenProjectionState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudScreenProjectionState(i);
    }

    public int setHudState(int i) {
        Log.i(TAG, "setHudState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setIALArea(int i, int i2) {
        Log.i(TAG, "setIALArea is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIALArea(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setIALBrightness(int i, int i2, int i3) {
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIALBrightness(i, i2, i3);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setIALColor(int i, int i2, int i3) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIALColor(i, i2, i3);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setIALInfo(int i, int i2, int i3, int i4) {
        Log.i(TAG, "setIALInfo is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIALInfo(i, i2, i3, i4);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setINSTheme(int i) {
        Log.i(TAG, "setINSTheme is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setINSTheme(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setInsideLightDoorState(int i) {
        Log.i(TAG, "setInsideLightDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setInsideLightDoorState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setIntelLockOpenDriverDoor(int i) {
        Log.v(TAG, "setIntelLockOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIntelLockOpenDriverDoor(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setIntelligentAcAreaState(int i) {
        Log.i(TAG, "setIntelligentAcAreaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentAcAreaState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setIntelligentLockState(int i) {
        Log.i(TAG, "setIntelligentLockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentLockState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setIntelligentUnlockState(int i) {
        Log.i(TAG, "setIntelligentUnlockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentUnlockState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setInternationalLanguage(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setInternationalLanguage(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setInternationalLanguage mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setIviProfileSettingsR(int i) {
        Log.i(TAG, "setIviProfileSettingsR is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setIviProfileSettingsR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setIviProfileSettingsR(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setKeyPowerLowInd(int i) {
        Log.i(TAG, "setKeyPowerLowInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setKeyPowerLowInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setKeyVibratorLevel(int i) {
        Log.v(TAG, " setKeyVibratorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setKeyVibratorLevel(i);
    }

    public int setKeypadToneState(int i) {
        Log.v(TAG, "setKeypadToneState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setKeypadToneState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setKeypadToneState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void setKeypadToneStatechanged(int i) {
        Log.v(TAG, "setKeypadToneStatechanged is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getSettingAdapterManager().setKeypadToneStatechanged(i);
                return;
            }
        }
        Log.v(TAG, "setKeypadToneStatechanged mForTestValue is" + this.mForTest + " execute return");
    }

    public int setLandAdvanceAtState(int i) {
        Log.i(TAG, "setLandAdvanceAtState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLandAdvanceAtState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLanguage(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setLanguage(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setLanguage mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setLanternLanguageMode(int i) {
        Log.i(TAG, "setLanternLanguageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLanternLanguageMode(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLeaveCarPInd(int i) {
        Log.i(TAG, "setLeaveCarPInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLeaveCarPInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLeftHomeLightDelay(int i) {
        Log.i(TAG, "setLeftHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLeftHomeLightDelay(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLeftHomeLightDelayValue(int i) {
        Log.i(TAG, "setLeftHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLeftHomeLightDelayValue(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setLeftRseStateS(int i) {
        Log.i(TAG, "setLeftRseStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLeftRseStateS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLeftRseSwitchCommandS(int i) {
        Log.i(TAG, "setLeftRseSwitchCommandS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLeftRseSwitchCommandS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLiftModeState(int i) {
        Log.v(TAG, "setLiftModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLiftModeState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLockCarRiseWindow(int i) {
        Log.i(TAG, "setLockCarRiseWindow is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLockCarRiseWindow(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLockOff(int i) {
        Log.i(TAG, "setLockOff is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLockOff(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLowBatteryInd(int i) {
        Log.i(TAG, "setLowBatteryInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLowBatteryInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLowOilInd(int i) {
        Log.i(TAG, "setLowOilInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLowOilInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLrseChildAreaStateS(int i) {
        Log.i(TAG, "setLrseChildAreaStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildAreaStateS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLrseChildModeCode1stByteS(int i) {
        Log.i(TAG, "setLrseChildModeCode1stByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode1stByteS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLrseChildModeCode2ndByteS(int i) {
        Log.i(TAG, "setLrseChildModeCode2ndByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode2ndByteS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLrseChildModeCode3rdByteS(int i) {
        Log.i(TAG, "setLrseChildModeCode3rdByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode3rdByteS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLrseChildModeCode4thByteS(int i) {
        Log.i(TAG, "setLrseChildModeCode4thByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode4thByteS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setLrseChildModeOpenTimeS(int i) {
        Log.i(TAG, "setLrseChildModeOpenTimeS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeOpenTimeS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMaintainRemindState(int i) {
        Log.i(TAG, "setMaintainRemindState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMaintainRemindState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setMassageLevel(int i, int i2) {
        Log.i(TAG, "setMassageLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public int setMassageMode(int i, int i2) {
        Log.i(TAG, "setMassageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public int setMeditationMode(int i) {
        Log.v(TAG, "setMeditationMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMeditationMode(i);
    }

    public int setMessageCounter(int i) {
        Log.v(TAG, "setMessageCounter is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMessageCounter(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMicroSwitchLockWindowState(int i) {
        Log.i(TAG, "setMicroSwitchLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMicroSwitchLockWindowState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMicroSwitchUnlockWindowState(int i) {
        Log.i(TAG, "setMicroSwitchUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMicroSwitchUnlockWindowState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMissKeyInd(int i) {
        Log.i(TAG, "setMissKeyInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMissKeyInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMode(int i) {
        Log.v(TAG, "setMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMode(i);
    }

    public int setMoisAssembleStateS(int i) {
        Log.i(TAG, "setMoisAssembleStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMoisAssembleStateS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMotorPretensionerSeatbeltState(int i) {
        Log.i(TAG, "setMotorPretensionerSeatbeltState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public int setNavigationMediaState(int i) {
        Log.i(TAG, "setNavigationMediaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setNavigationMediaState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNfcOpenDriverDoor(int i) {
        Log.v(TAG, "setNfcOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setNfcOpenDriverDoor(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNightDRLightOnInd(int i) {
        Log.i(TAG, "setNightDRLightOnInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setNightDRLightOnInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setOtaMode(int i) {
        Log.v(TAG, "setOtaMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setOtaMode(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setOverspeedLock(int i) {
        Log.i(TAG, "setOverspeedLock is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setOverspeedLock(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setPM25Power(int i) {
        Log.i(TAG, "setPM25Power is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setPM25Power(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setPM25SwitchCheck(int i) {
        Log.i(TAG, "setPM25SwitchCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setPM25SwitchCheck(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setPM25TimeCheck(int i) {
        Log.i(TAG, "setPM25TimeCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setPM25TimeCheck(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setPabState(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setPabState(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setPabState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setPadRotation(int i) {
        Log.i(TAG, "setPadRotation is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setPadRotation(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setParkBrakeInd(int i) {
        Log.i(TAG, "setParkBrakeInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setParkBrakeInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setPetMode(int i) {
        Log.v(TAG, "setPetMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPetMode(i);
    }

    public int setRainAutoLockWindowState(int i) {
        Log.i(TAG, "setRainAutoLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRainAutoLockWindowState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRearAcPanelAutoLockTime(int i) {
        Log.i(TAG, "setRearAcPanelAutoLockTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRearAcPanelAutoLockTime(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setRearSeatGreetState(int i) {
        Log.v(TAG, "setRearSeatGreetState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRearSeatGreetState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRearSeatsFoldOrExpand(int i) {
        Log.i(TAG, "setRearSeatsFoldOrExpand is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRearSeatsFoldOrExpand(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRearViewMirrorFlip(int i) {
        Log.i(TAG, "setRearViewMirrorFlip is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRearViewMirrorFlip(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRefrigeratorMode(int i) {
        Log.i(TAG, "setRefrigeratorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public int setRemenberPowerOffState(int i) {
        Log.i(TAG, "setRemenberPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRemenberPowerOffState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRemoteControlDownwindowState(int i) {
        Log.i(TAG, "setRemoteControlDownwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlDownwindowState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Deprecated
    public int setRemoteControlLockWindowState(int i) {
        Log.i(TAG, "setRemoteControlLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlLockWindowState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Deprecated
    public int setRemoteControlUnlockWindowState(int i) {
        Log.i(TAG, "setRemoteControlUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlUnlockWindowState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRemoteControlUpwindowState(int i) {
        Log.i(TAG, "setRemoteControlUpwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlUpwindowState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRemoteKeyStartAcTime(int i) {
        Log.i(TAG, "setRemoteKeyStartAcTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRemoteKeyStartAcTime(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setReportVolume(int i) {
        Log.i(TAG, "setReportVolume is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setReportVolume(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRestMode(int i) {
        Log.v(TAG, "setRestMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRestMode(i);
    }

    public int setRightRseStateS(int i) {
        Log.i(TAG, "setRightRseStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRightRseStateS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRightRseSwitchCommandS(int i) {
        Log.i(TAG, "setRightRseSwitchCommandS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRightRseSwitchCommandS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRrseChildAreaStateS(int i) {
        Log.i(TAG, "setRrseChildAreaStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildAreaStateS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRrseChildModeCode1stByteS(int i) {
        Log.i(TAG, "setRrseChildModeCode1stByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode1stByteS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRrseChildModeCode2ndByteS(int i) {
        Log.i(TAG, "setRrseChildModeCode2ndByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode2ndByteS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRrseChildModeCode3rdByteS(int i) {
        Log.i(TAG, "setRrseChildModeCode3rdByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode3rdByteS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRrseChildModeCode4thByteS(int i) {
        Log.i(TAG, "setRrseChildModeCode4thByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode4thByteS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRrseChildModeOpenTimeS(int i) {
        Log.i(TAG, "setRrseChildModeOpenTimeS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeOpenTimeS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRuningCloseDoorState(int i) {
        Log.v(TAG, "setRuningCloseDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setRuningCloseDoorState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setRuningCloseDoorState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setSOCTarget(int i) {
        Log.i(TAG, "setSOCTarget is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSOCTarget(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setSafeBeltCloseDriveDoorState(int i) {
        Log.v(TAG, "setSafeBeltCloseDriveDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltCloseDriveDoorState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setSafeBeltInd(int i) {
        Log.i(TAG, "setSafeBeltInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setSafeBeltWarnState(int i) {
        Log.v(TAG, "setSafeBeltWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltWarnState(i);
    }

    public int setSafeWarnState(int i) {
        Log.i(TAG, "setSafeWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSafeWarnState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setScreenAngleValue(int i) {
        Log.i(TAG, "setScreenAngleValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setScreenAngleValueS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setScreenRotation(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setScreenRotation(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setScreenRotation mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setScreenRotationLock(int i) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().setScreenRotationLock(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setScreenRotationLock mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setSeatHeatingState(int i, int i2) {
        Log.i(TAG, "setSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public int setSeatVentilatingState(int i, int i2) {
        Log.i(TAG, "setSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public int setSmartWelcomeLightState(int i) {
        Log.i(TAG, "setSmartWelcomeLightState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSmartWelcomeLightState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setSpeedReminderValue(int i) {
        Log.i(TAG, "setSpeedReminderValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSpeedReminderValue(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setStartOrPowerInd(int i) {
        Log.i(TAG, "setStartOrPowerInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setStartOrPowerInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setSteerAssis(int i) {
        Log.i(TAG, "setSteerAssis is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSteerAssis(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setSteerPositionAutoReturn(int i) {
        Log.i(TAG, "setSteerPositionAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSteerPositionAutoReturn(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setSteeringWheelHeatingAutoState(int i) {
        Log.v(TAG, "setSteeringWheelHeatingAutoState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingAutoState(i);
    }

    public int setSteeringWheelHeatingGear(int i) {
        Log.v(TAG, "setSteeringWheelHeatingGear is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingGear(i);
    }

    public int setSteeringWheelHeatingState(int i) {
        Log.i(TAG, "setSteeringWheelHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setStopRemoteCtrlDriveInd(int i) {
        Log.i(TAG, "setStopRemoteCtrlDriveInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setStopRemoteCtrlDriveInd(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setSwsMediumRelatedKeysS(int i) {
        Log.i(TAG, "setSwsMediumRelatedKeysS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSwsMediumRelatedKeysS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setTailBoxTakeState(int i) {
        Log.v(TAG, "setTailBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setTailBoxTakeState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setTrailerLightCheckState(int i) {
        Log.v(TAG, "setTrailerLightCheckState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setTrailerLightCheckState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setTrailerModeState(int i) {
        Log.v(TAG, "setTrailerModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setTrailerModeState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setTrayState(int i) {
        Log.i(TAG, "setTrayState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setTrayState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setUiVersion(int i) {
        Log.v(TAG, "setUiVersion is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setUiVersion(i);
    }

    public int setVspState(int i) {
        Log.v(TAG, "setVSPState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setVspState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setWelcomeState(int i) {
        Log.v(TAG, "setWelcomeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getSettingAdapterManager().setWelcomeState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setWipeMode(int i) {
        Log.v(TAG, "setWipeMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWipeMode(i);
    }

    public int soundFiledDefaultReset(int i) {
        Log.v(TAG, "soundFiledDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public void turnCaliperSplintPercent(int i, int i2) {
        Log.v(TAG, "turnCaliperSplintPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
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
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnCushionPercentDriver(i2);
        }
    }

    public void turnHeadrestHeightPercent(int i, int i2) {
        Log.v(TAG, "turnHeadrestHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestHeightPercentDriver(i2);
        }
    }

    public void turnHeadrestHorizationPercent(int i, int i2) {
        Log.v(TAG, "turnHeadrestHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (i == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestHorizationPercentDriver(i2);
        }
    }

    public void turnHeadrestTurnHeight(int i, int i2) {
        Log.i(TAG, "turnHeadrestTurnHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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
            throw null;
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

    public void turnRearviewMirror(int i) {
        Log.i(TAG, "turnRearviewMirror is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getSettingAdapterManager().turnRearviewMirror(i);
        }
    }

    public void turnSeatCaliperSplint(int i, int i2) {
        Log.i(TAG, "turnSeatCaliperSplint is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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
            throw null;
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

    public void turnSeatHeight(int i, int i2) {
        Log.i(TAG, "turnSeatHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public void turnSeatHeightPercent(int i, int i2) {
        Log.i(TAG, "turnSeatHeightPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public void turnSeatHorization(int i, int i2) {
        Log.i(TAG, "turnSeatHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public void turnSeatHorizationPercent(int i, int i2) {
        Log.i(TAG, "turnSeatHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public void turnSeatbackrest(int i, int i2) {
        Log.i(TAG, "turnSeatbackrest is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public void turnSeatbackrestPercent(int i, int i2) {
        Log.i(TAG, "turnSeatbackrestPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
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

    public void turnSteeringWheel(int i) {
        Log.i(TAG, "turnSteeringWheel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getSettingAdapterManager().turnSteeringWheel(i);
        }
    }

    public int unitDefaultReset(int i) {
        Log.v(TAG, "unitDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public void unregisterListener(d dVar) {
    }

    public void unregisterListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        Log.d(TAG, "unsubscribeSettingCallback: " + absBYDAutoSettingListener);
        if (absBYDAutoSettingListener == null) {
            return;
        }
        this.mAbsBydAutoSettingListeners.remove(absBYDAutoSettingListener);
        unregisterConnectListener(absBYDAutoSettingListener);
    }

    public int voiceCtlBackDoor(int i) {
        Log.v(TAG, "voiceCtlBackDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getSettingAdapterManager().voiceCtlBackDoor(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "voiceCtlBackDoor mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }
}
