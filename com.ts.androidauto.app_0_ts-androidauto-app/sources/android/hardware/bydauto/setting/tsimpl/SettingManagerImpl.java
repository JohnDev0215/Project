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

    private SettingManagerImpl(Context con) {
        this.mContext = con;
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSettingChangedListener(new OnSettingChangedListener() { // from class: android.hardware.bydauto.setting.tsimpl.SettingManagerImpl.1
            public void onSettingChanged(int id, int areaId, SettingPacket packet) {
                if (!SettingManagerImpl.this.mAbsBydAutoSettingListeners.isEmpty()) {
                    try {
                        SettingManagerImpl.this.settingCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(SettingManagerImpl.TAG, "settingCallback is error");
                    }
                }
            }

            public void onBinderSettingChanged(boolean binderState) {
            }
        });
    }

    public static SettingManagerImpl getInstance(Context con) {
        if (mTsMangerImpl == null) {
            synchronized (SettingManagerImpl.class) {
                if (mTsMangerImpl == null) {
                    mTsMangerImpl = new SettingManagerImpl(con);
                }
            }
        }
        return mTsMangerImpl;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoSettingListener listener : this.mAbsBydAutoSettingListeners) {
            Log.d(TAG, "onDataEventChanged  id:" + id + " value:" + value.intValue + " listener:" + listener);
            listener.onDataEventChanged(id, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void settingCallback(int id, int areaId, SettingPacket settingPacket) {
        if (settingPacket == null) {
            Log.e(TAG, "settingPacket is null");
        }
        Log.i(TAG, "settingPacket propertyId:" + id);
        switch (id) {
            case 557846608:
                for (AbsBYDAutoSettingListener listener : this.mAbsBydAutoSettingListeners) {
                    listener.onAcAutoPurificationStateChanged(settingPacket.getAutoPurificationState());
                }
                break;
            case 557847553:
                for (AbsBYDAutoSettingListener listener2 : this.mAbsBydAutoSettingListeners) {
                    listener2.onGoalRoadModeChanged(settingPacket.getGoalRoadModeR());
                }
                break;
            case 557856058:
                for (AbsBYDAutoSettingListener listener3 : this.mAbsBydAutoSettingListeners) {
                    listener3.onMotorPretensionerSeatbeltStateChanged(settingPacket.getMotorPretensionerSeatbeltState());
                    listener3.onResetMotorPretensionerSeatbelt(settingPacket.getMotorPretensionerSeatbeltState());
                }
                break;
            case 557856063:
                for (AbsBYDAutoSettingListener listener4 : this.mAbsBydAutoSettingListeners) {
                    listener4.onAcKeyStartTimeChanged(settingPacket.getRemoteKeyStartAcTime());
                }
                break;
            case 557856073:
                for (AbsBYDAutoSettingListener listener5 : this.mAbsBydAutoSettingListeners) {
                    listener5.onRearAcPanelAutoLockTimeChanged(settingPacket.getRearAcPanelAutoLockTime());
                }
                break;
            case 557856076:
                caseFeatureIds(953, Integer.valueOf(settingPacket.getDoorHeightSupport()));
                break;
            case 557856146:
                for (AbsBYDAutoSettingListener listener6 : this.mAbsBydAutoSettingListeners) {
                    listener6.onSteerPositionAutoReturnSwitchChanged(settingPacket.getSteerPositionAutoReturn());
                }
                break;
            case 557856184:
                caseFeatureIds(1159, Integer.valueOf(settingPacket.getAmbientLightColorSupport()));
                break;
            case 557856185:
                caseFeatureIds(1158, Integer.valueOf(settingPacket.getAmbientLightSupport()));
                break;
            case 557856186:
                caseFeatureIds(1154, Integer.valueOf(settingPacket.getAmbientAreaSupport()));
                break;
            case 557856889:
                for (AbsBYDAutoSettingListener listener7 : this.mAbsBydAutoSettingListeners) {
                    listener7.onBSISCollisionStateChanged(settingPacket.getBsisCollisionStateR());
                }
                break;
            case 557856891:
                for (AbsBYDAutoSettingListener listener8 : this.mAbsBydAutoSettingListeners) {
                    listener8.onBSISFuncStatusChanged(settingPacket.getBsisFuncStatusR());
                }
                break;
            case 557856892:
                for (AbsBYDAutoSettingListener listener9 : this.mAbsBydAutoSettingListeners) {
                    listener9.onMOISAssembleStateChanged(settingPacket.getMoisAssembleStateR());
                }
                break;
            case 557856894:
                for (AbsBYDAutoSettingListener listener10 : this.mAbsBydAutoSettingListeners) {
                    listener10.onMOISFuncStatusChanged(settingPacket.getMoisFuncStatusR());
                }
                break;
            case 557857536:
                for (AbsBYDAutoSettingListener listener11 : this.mAbsBydAutoSettingListeners) {
                    listener11.onACBTWindSwitchChanged(settingPacket.getACBTWind());
                }
                caseFeatureIds(881, Integer.valueOf(settingPacket.getACBTWind()));
                break;
            case 557857540:
                for (AbsBYDAutoSettingListener listener12 : this.mAbsBydAutoSettingListeners) {
                    listener12.onACPauseCycleSwitchChanged(settingPacket.getACPauseCycle());
                }
                caseFeatureIds(883, Integer.valueOf(settingPacket.getACPauseCycle()));
                break;
            case 557857543:
                for (AbsBYDAutoSettingListener listener13 : this.mAbsBydAutoSettingListeners) {
                    listener13.onACAutoAirModeChanged(settingPacket.getACAutoAir());
                }
                caseFeatureIds(878, Integer.valueOf(settingPacket.getACAutoAir()));
                break;
            case 557857549:
                for (AbsBYDAutoSettingListener listener14 : this.mAbsBydAutoSettingListeners) {
                    listener14.onPM25PowerSwitchChanged(settingPacket.getPM25Power());
                }
                caseFeatureIds(410, Integer.valueOf(settingPacket.getPM25Power()));
                break;
            case 557857551:
                for (AbsBYDAutoSettingListener listener15 : this.mAbsBydAutoSettingListeners) {
                    listener15.onPM25SwitchCheckChanged(settingPacket.getPM25SwitchCheck());
                }
                caseFeatureIds(412, Integer.valueOf(settingPacket.getPM25SwitchCheck()));
                break;
            case 557857553:
                for (AbsBYDAutoSettingListener listener16 : this.mAbsBydAutoSettingListeners) {
                    listener16.onPM25TimeCheckChanged(settingPacket.getPM25TimeCheck());
                }
                caseFeatureIds(414, Integer.valueOf(settingPacket.getPM25TimeCheck()));
                break;
            case 557857555:
                for (AbsBYDAutoSettingListener listener17 : this.mAbsBydAutoSettingListeners) {
                    listener17.onACTunnelCycleSwitchChanged(settingPacket.getACTunnelCycle());
                }
                caseFeatureIds(885, Integer.valueOf(settingPacket.getACTunnelCycle()));
                break;
            case 557857557:
                for (AbsBYDAutoSettingListener listener18 : this.mAbsBydAutoSettingListeners) {
                    listener18.onAcWarmModeChanged(settingPacket.getAcWarmMode());
                }
                caseFeatureIds(3557, Integer.valueOf(settingPacket.getAcWarmMode()));
                break;
            case 557857560:
                for (AbsBYDAutoSettingListener listener19 : this.mAbsBydAutoSettingListeners) {
                    listener19.onIntelligentAcAreaStateChanged(settingPacket.getIntelligentAcAreaState());
                }
                caseFeatureIds(3522, Integer.valueOf(settingPacket.getIntelligentAcAreaState()));
                break;
            case 557857561:
                for (AbsBYDAutoSettingListener listener20 : this.mAbsBydAutoSettingListeners) {
                    listener20.onBackDoorOpenedHeightChanged(settingPacket.getBackDoorOpenedHeight());
                }
                caseFeatureIds(388, Integer.valueOf(settingPacket.getBackDoorOpenedHeight()));
                break;
            case 557857563:
                for (AbsBYDAutoSettingListener listener21 : this.mAbsBydAutoSettingListeners) {
                    listener21.onRainAutoLockWindowStateChanged(settingPacket.getRainAutoLockWindowState());
                }
                break;
            case 557857565:
                for (AbsBYDAutoSettingListener listener22 : this.mAbsBydAutoSettingListeners) {
                    listener22.onDoorknobAutoShrinkStateChanged(settingPacket.getDoorknobAutoShrinkState());
                }
                break;
            case 557857567:
                for (AbsBYDAutoSettingListener listener23 : this.mAbsBydAutoSettingListeners) {
                    listener23.onIntelligentUnlockStateChanged(settingPacket.getIntelligentUnlockState());
                }
                caseFeatureIds(2662, Integer.valueOf(settingPacket.getIntelligentUnlockState()));
                break;
            case 557857569:
                for (AbsBYDAutoSettingListener listener24 : this.mAbsBydAutoSettingListeners) {
                    listener24.onIntelligentLockStateChanged(settingPacket.getIntelligentLockState());
                }
                break;
            case 557857571:
                for (AbsBYDAutoSettingListener listener25 : this.mAbsBydAutoSettingListeners) {
                    listener25.onHudStateChanged(settingPacket.getHudState());
                }
                break;
            case 557857574:
                for (AbsBYDAutoSettingListener listener26 : this.mAbsBydAutoSettingListeners) {
                    listener26.onHudHeightChanged(settingPacket.getHudHeight());
                }
                break;
            case 557857576:
                for (AbsBYDAutoSettingListener listener27 : this.mAbsBydAutoSettingListeners) {
                    listener27.onHudBrightnessChanged(settingPacket.getHudBrightness());
                }
                break;
            case 557857578:
                for (AbsBYDAutoSettingListener listener28 : this.mAbsBydAutoSettingListeners) {
                    listener28.onHudRotationAngleChanged(settingPacket.getHudRotationAngle());
                }
                break;
            case 557857580:
                for (AbsBYDAutoSettingListener listener29 : this.mAbsBydAutoSettingListeners) {
                    listener29.onHudModeChanged(settingPacket.getHudMode());
                }
                break;
            case 557857582:
                for (AbsBYDAutoSettingListener listener30 : this.mAbsBydAutoSettingListeners) {
                    listener30.onHudDisplayContentChanged(1, settingPacket.getHudDisplayContentRDrive());
                }
                break;
            case 557857583:
                for (AbsBYDAutoSettingListener listener31 : this.mAbsBydAutoSettingListeners) {
                    listener31.onHudDisplayContentChanged(2, settingPacket.getHudDisplayContentRNavigation());
                }
                break;
            case 557857584:
                for (AbsBYDAutoSettingListener listener32 : this.mAbsBydAutoSettingListeners) {
                    listener32.onResetHudDisplayContent(settingPacket.getHudDisplayContentSDrive());
                }
                break;
            case 557857585:
                for (AbsBYDAutoSettingListener listener33 : this.mAbsBydAutoSettingListeners) {
                    listener33.onResetHudDisplayContent(settingPacket.getHudDisplayContentSNavigation());
                }
                break;
            case 557857587:
                for (AbsBYDAutoSettingListener listener34 : this.mAbsBydAutoSettingListeners) {
                    listener34.onEngineSoundTypeChanged(settingPacket.getEngineSoundType());
                }
                caseFeatureIds(3586, Integer.valueOf(settingPacket.getEngineSoundType()));
                break;
            case 557857589:
                for (AbsBYDAutoSettingListener listener35 : this.mAbsBydAutoSettingListeners) {
                    listener35.onSpeedReminderValueChanged(settingPacket.getSpeedReminderValue());
                }
                break;
            case 557857591:
                for (AbsBYDAutoSettingListener listener36 : this.mAbsBydAutoSettingListeners) {
                    listener36.onRearViewMirrorFlipChanged(settingPacket.getRearViewMirrorFlip());
                }
                caseFeatureIds(416, Integer.valueOf(settingPacket.getRearViewMirrorFlip()));
                break;
            case 557857592:
                for (AbsBYDAutoSettingListener listener37 : this.mAbsBydAutoSettingListeners) {
                    listener37.onRearViewMirrorFlipSetChanged(settingPacket.getRearViewMirrorFlipSet());
                }
                caseFeatureIds(417, Integer.valueOf(settingPacket.getRearViewMirrorFlipSet()));
                break;
            case 557857595:
                for (AbsBYDAutoSettingListener listener38 : this.mAbsBydAutoSettingListeners) {
                    listener38.onAutoPowerOnStateChanged(settingPacket.getAutoPowerOnState());
                }
                caseFeatureIds(520, Integer.valueOf(settingPacket.getAutoPowerOnState()));
                break;
            case 557857597:
                for (AbsBYDAutoSettingListener listener39 : this.mAbsBydAutoSettingListeners) {
                    listener39.onAutoPowerOffStateChanged(settingPacket.getAutoPowerOffState());
                }
                break;
            case 557857599:
                for (AbsBYDAutoSettingListener listener40 : this.mAbsBydAutoSettingListeners) {
                    listener40.onDriverSeatAutoReturnStateChanged(settingPacket.getDriverSeatAutoReturn());
                }
                caseFeatureIds(397, Integer.valueOf(settingPacket.getDriverSeatAutoReturn()));
                break;
            case 557857603:
                for (AbsBYDAutoSettingListener listener41 : this.mAbsBydAutoSettingListeners) {
                    listener41.onBrakePedalModeChanged(settingPacket.getBrakePedalMode());
                }
                break;
            case 557857605:
                for (AbsBYDAutoSettingListener listener42 : this.mAbsBydAutoSettingListeners) {
                    listener42.onComfortableParkingStateChanged(settingPacket.getComfortableParkingState());
                }
                break;
            case 557857609:
                for (AbsBYDAutoSettingListener listener43 : this.mAbsBydAutoSettingListeners) {
                    listener43.onAutoRainWiperStateChanged(settingPacket.getresetMotorPretensionerSeatbelt());
                }
                break;
            case 557857611:
                for (AbsBYDAutoSettingListener listener44 : this.mAbsBydAutoSettingListeners) {
                    listener44.onDynamicSuspensionModeChanged(settingPacket.getDynamicSuspensionMode());
                }
                break;
            case 557857613:
                for (AbsBYDAutoSettingListener listener45 : this.mAbsBydAutoSettingListeners) {
                    listener45.onRemoteControlUpwindowStateChanged(settingPacket.getRemoteControlUpwindowState());
                }
                caseFeatureIds(419, Integer.valueOf(settingPacket.getRemoteControlUpwindowState()));
                break;
            case 557857615:
                for (AbsBYDAutoSettingListener listener46 : this.mAbsBydAutoSettingListeners) {
                    listener46.onRemoteControlSwitchChanged(settingPacket.getRemoteControlDownwindowState());
                }
                caseFeatureIds(395, Integer.valueOf(settingPacket.getRemoteControlDownwindowState()));
                break;
            case 557857617:
                for (AbsBYDAutoSettingListener listener47 : this.mAbsBydAutoSettingListeners) {
                    listener47.onMicroSwitchLockWindowStateChanged(settingPacket.getMicroSwitchLockWindowState());
                }
                caseFeatureIds(408, Integer.valueOf(settingPacket.getMicroSwitchLockWindowState()));
                break;
            case 557857619:
                for (AbsBYDAutoSettingListener listener48 : this.mAbsBydAutoSettingListeners) {
                    listener48.onMicroSwitchUnlockWindowStateChanged(settingPacket.getMicroSwitchUnlockWindowState());
                }
                caseFeatureIds(406, Integer.valueOf(settingPacket.getMicroSwitchUnlockWindowState()));
                break;
            case 557857621:
                for (AbsBYDAutoSettingListener listener49 : this.mAbsBydAutoSettingListeners) {
                    listener49.onLockCarRiseWindowStateChange(settingPacket.getLockCarRiseWindow());
                }
                caseFeatureIds(403, Integer.valueOf(settingPacket.getLockCarRiseWindow()));
                break;
            case 557857624:
                for (AbsBYDAutoSettingListener listener50 : this.mAbsBydAutoSettingListeners) {
                    listener50.onLeftHomeLightDelayValueChanged(settingPacket.getLeftHomeLightDelayValue());
                }
                break;
            case 557857626:
                for (AbsBYDAutoSettingListener listener51 : this.mAbsBydAutoSettingListeners) {
                    listener51.onBackHomeLightDelayValueChanged(settingPacket.getBackHomeLightDelayValue());
                }
                break;
            case 557857664:
                for (AbsBYDAutoSettingListener listener52 : this.mAbsBydAutoSettingListeners) {
                    listener52.onTurnSeatHorizationChanged(1, 1, settingPacket.getSeatHorizationDriver());
                }
                break;
            case 557857665:
                for (AbsBYDAutoSettingListener listener53 : this.mAbsBydAutoSettingListeners) {
                    listener53.onTurnSeatHorizationChanged(2, 1, settingPacket.getSeatHorizationCopilot());
                }
                break;
            case 557857666:
                for (AbsBYDAutoSettingListener listener54 : this.mAbsBydAutoSettingListeners) {
                    listener54.onTurnSeatHorizationChanged(3, 1, settingPacket.getSeatHorizationBackLeft());
                }
                break;
            case 557857667:
                for (AbsBYDAutoSettingListener listener55 : this.mAbsBydAutoSettingListeners) {
                    listener55.onTurnSeatHorizationChanged(4, 1, settingPacket.getSeatHorizationBackRight());
                }
                break;
            case 557857688:
                for (AbsBYDAutoSettingListener listener56 : this.mAbsBydAutoSettingListeners) {
                    listener56.onHeadrestTurnHorizationChanged(1, 1, settingPacket.getHeadrestHorizationDriver());
                }
                break;
            case 557857689:
                for (AbsBYDAutoSettingListener listener57 : this.mAbsBydAutoSettingListeners) {
                    listener57.onHeadrestTurnHorizationChanged(2, 2, settingPacket.getHeadrestHorizationCopilot());
                }
                break;
            case 557857690:
                for (AbsBYDAutoSettingListener listener58 : this.mAbsBydAutoSettingListeners) {
                    listener58.onHeadrestTurnHorizationChanged(3, 1, settingPacket.getHeadrestHorizationBackLeft());
                }
                break;
            case 557857691:
                for (AbsBYDAutoSettingListener listener59 : this.mAbsBydAutoSettingListeners) {
                    listener59.onHeadrestTurnHorizationChanged(4, 2, settingPacket.getHeadrestHorizationBackRight());
                }
                break;
            case 557857699:
                for (AbsBYDAutoSettingListener listener60 : this.mAbsBydAutoSettingListeners) {
                    int val = settingPacket.getRefrigeratorMode();
                    if (val == 3) {
                        val = 0;
                    }
                    listener60.onRefrigeratorModeChanged(val);
                }
                break;
            case 557857701:
                for (AbsBYDAutoSettingListener listener61 : this.mAbsBydAutoSettingListeners) {
                    listener61.onRemenberPowerOffStateChanged(settingPacket.getRemenberPowerOffState());
                }
                break;
            case 557857705:
                for (AbsBYDAutoSettingListener listener62 : this.mAbsBydAutoSettingListeners) {
                    listener62.onDisinfStateChanged(settingPacket.getDisinfState());
                }
                break;
            case 557857706:
                for (AbsBYDAutoSettingListener listener63 : this.mAbsBydAutoSettingListeners) {
                    listener63.onTrayActionStateChanged(settingPacket.getTrayState());
                }
                break;
            case 557857708:
                for (AbsBYDAutoSettingListener listener64 : this.mAbsBydAutoSettingListeners) {
                    listener64.onRefrigeratorState(settingPacket.getRefrigeratorState());
                }
                break;
            case 557857710:
                for (AbsBYDAutoSettingListener listener65 : this.mAbsBydAutoSettingListeners) {
                    listener65.onIALColorChanged(5, settingPacket.getIALColorStarRing());
                }
                caseFeatureIds(2845, Integer.valueOf(settingPacket.getIALColorStarRing()));
                break;
            case 557857711:
                for (AbsBYDAutoSettingListener listener66 : this.mAbsBydAutoSettingListeners) {
                    listener66.onIALColorChanged(4, settingPacket.getIALColorStarrySky());
                }
                break;
            case 557857712:
                for (AbsBYDAutoSettingListener listener67 : this.mAbsBydAutoSettingListeners) {
                    listener67.onIALColorChanged(1, settingPacket.getIALColorFront());
                }
                caseFeatureIds(1161, Integer.valueOf(settingPacket.getIALColorFront()));
                break;
            case 557857713:
                for (AbsBYDAutoSettingListener listener68 : this.mAbsBydAutoSettingListeners) {
                    listener68.onIALColorChanged(2, settingPacket.getIALColorBack());
                }
                caseFeatureIds(1157, Integer.valueOf(settingPacket.getIALColorBack()));
                break;
            case 557857716:
                for (AbsBYDAutoSettingListener listener69 : this.mAbsBydAutoSettingListeners) {
                    listener69.onIALBrightnessChanged(5, settingPacket.getIALBrightnessStarRing());
                }
                caseFeatureIds(2844, Integer.valueOf(settingPacket.getIALBrightnessStarRing()));
                break;
            case 557857717:
                for (AbsBYDAutoSettingListener listener70 : this.mAbsBydAutoSettingListeners) {
                    listener70.onIALBrightnessChanged(4, settingPacket.getIALBrightnessStarrySky());
                }
                break;
            case 557857718:
                for (AbsBYDAutoSettingListener listener71 : this.mAbsBydAutoSettingListeners) {
                    listener71.onIALBrightnessChanged(2, settingPacket.getIALBrightnessBack());
                }
                caseFeatureIds(1156, Integer.valueOf(settingPacket.getIALBrightnessBack()));
                break;
            case 557857719:
                for (AbsBYDAutoSettingListener listener72 : this.mAbsBydAutoSettingListeners) {
                    listener72.onIALBrightnessChanged(1, settingPacket.getIALBrightnessFront());
                }
                caseFeatureIds(1160, Integer.valueOf(settingPacket.getIALBrightnessFront()));
                break;
            case 557857721:
                for (AbsBYDAutoSettingListener listener73 : this.mAbsBydAutoSettingListeners) {
                    listener73.onIALAreaChanged(settingPacket.getIALArea());
                }
                caseFeatureIds(1171, Integer.valueOf(settingPacket.getIALArea()));
                break;
            case 557857723:
                for (AbsBYDAutoSettingListener listener74 : this.mAbsBydAutoSettingListeners) {
                    listener74.onSeatHeatingStateChanged(1, settingPacket.getSeatHeatingStateDriverDomainControl());
                }
                caseFeatureIds(1040, Integer.valueOf(settingPacket.getSeatHeatingStateDriverDomainControl()));
                break;
            case 557857725:
                for (AbsBYDAutoSettingListener listener75 : this.mAbsBydAutoSettingListeners) {
                    listener75.onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotECU());
                }
                break;
            case 557857726:
                for (AbsBYDAutoSettingListener listener76 : this.mAbsBydAutoSettingListeners) {
                    listener76.onSeatHeatingStateChanged(3, settingPacket.getSeatHeatingStateBackLeftECU());
                }
                break;
            case 557857727:
                for (AbsBYDAutoSettingListener listener77 : this.mAbsBydAutoSettingListeners) {
                    listener77.onSeatHeatingStateChanged(4, settingPacket.getSeatHeatingStateBackRightECU());
                }
                break;
            case 557857728:
                for (AbsBYDAutoSettingListener listener78 : this.mAbsBydAutoSettingListeners) {
                    listener78.onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotL1P());
                }
                caseFeatureIds(1269, Integer.valueOf(settingPacket.getSeatHeatingStateCopilotL1P()));
                break;
            case 557857729:
                for (AbsBYDAutoSettingListener listener79 : this.mAbsBydAutoSettingListeners) {
                    listener79.onSeatHeatingStateChanged(2, settingPacket.getSeatHeatingStateCopilotNonL1P());
                }
                break;
            case 557857730:
                for (AbsBYDAutoSettingListener listener80 : this.mAbsBydAutoSettingListeners) {
                    listener80.onSeatHeatingStateChanged(3, settingPacket.getSeatHeatingStateBackLeftDomainControl());
                }
                caseFeatureIds(1283, Integer.valueOf(settingPacket.getSeatHeatingStateBackLeftDomainControl()));
                break;
            case 557857731:
                for (AbsBYDAutoSettingListener listener81 : this.mAbsBydAutoSettingListeners) {
                    listener81.onSeatHeatingStateChanged(4, settingPacket.getSeatHeatingStateBackRightDomainControl());
                }
                caseFeatureIds(1294, Integer.valueOf(settingPacket.getSeatHeatingStateBackRightDomainControl()));
                break;
            case 557857733:
                for (AbsBYDAutoSettingListener listener82 : this.mAbsBydAutoSettingListeners) {
                    listener82.onSeatVentilatingStateChanged(1, settingPacket.getSeatVentilatingStateDriverDomainControl());
                }
                caseFeatureIds(1044, Integer.valueOf(settingPacket.getSeatVentilatingStateDriverDomainControl()));
                break;
            case 557857737:
                for (AbsBYDAutoSettingListener listener83 : this.mAbsBydAutoSettingListeners) {
                    listener83.onSeatVentilatingStateChanged(4, settingPacket.getSeatVentilatingStateBackRightECU());
                }
                break;
            case 557857738:
                for (AbsBYDAutoSettingListener listener84 : this.mAbsBydAutoSettingListeners) {
                    listener84.onSeatVentilatingStateChanged(2, settingPacket.getSeatVentilatingStateCopilotL1P());
                }
                caseFeatureIds(1273, Integer.valueOf(settingPacket.getSeatVentilatingStateCopilotL1P()));
                break;
            case 557857739:
                for (AbsBYDAutoSettingListener listener85 : this.mAbsBydAutoSettingListeners) {
                    listener85.onSeatVentilatingStateChanged(2, settingPacket.getSeatVentilatingStateCopilotNonL1P());
                }
                break;
            case 557857740:
                for (AbsBYDAutoSettingListener listener86 : this.mAbsBydAutoSettingListeners) {
                    listener86.onSeatVentilatingStateChanged(3, settingPacket.getSeatVentilatingStateBackLeftDomainControl());
                }
                caseFeatureIds(1290, Integer.valueOf(settingPacket.getSeatVentilatingStateBackLeftDomainControl()));
                break;
            case 557857741:
                for (AbsBYDAutoSettingListener listener87 : this.mAbsBydAutoSettingListeners) {
                    listener87.onSeatVentilatingStateChanged(4, settingPacket.getSeatVentilatingStateBackRightDomainControl());
                }
                caseFeatureIds(1301, Integer.valueOf(settingPacket.getSeatVentilatingStateBackRightDomainControl()));
                break;
            case 557857744:
                for (AbsBYDAutoSettingListener listener88 : this.mAbsBydAutoSettingListeners) {
                    listener88.onLanternLanguageModeChanged(settingPacket.getLanternLanguageMode());
                }
                break;
            case 557857745:
                for (AbsBYDAutoSettingListener listener89 : this.mAbsBydAutoSettingListeners) {
                    listener89.onINSThemeChanged(settingPacket.getINSTheme());
                }
                break;
            case 557857747:
                for (AbsBYDAutoSettingListener listener90 : this.mAbsBydAutoSettingListeners) {
                    listener90.onHeadlampLevelChanged(settingPacket.getHeadlampLevel());
                }
                break;
            case 557857749:
                for (AbsBYDAutoSettingListener listener91 : this.mAbsBydAutoSettingListeners) {
                    listener91.onLeftHeadlampLevelChanged(settingPacket.getLeftHeadlampLevel());
                }
                break;
            case 557857750:
                for (AbsBYDAutoSettingListener listener92 : this.mAbsBydAutoSettingListeners) {
                    listener92.onRightHeadlampLevelChanged(settingPacket.getRightHeadlampLevel());
                }
                break;
            case 557857752:
                for (AbsBYDAutoSettingListener listener93 : this.mAbsBydAutoSettingListeners) {
                    listener93.onSmartWelcomeLightStateChanged(settingPacket.getSmartWelcomeLightState());
                }
                break;
            case 557857764:
                for (AbsBYDAutoSettingListener listener94 : this.mAbsBydAutoSettingListeners) {
                    listener94.onMassageLevelStateChanged(1, settingPacket.getMassageLevelLeftFront());
                }
                caseFeatureIds(1067, Integer.valueOf(settingPacket.getMassageLevelLeftFront()));
                break;
            case 557857766:
                for (AbsBYDAutoSettingListener listener95 : this.mAbsBydAutoSettingListeners) {
                    listener95.onMassageLevelStateChanged(2, settingPacket.getMassageLevelRightFront());
                }
                caseFeatureIds(1070, Integer.valueOf(settingPacket.getMassageLevelRightFront()));
                break;
            case 557857768:
                for (AbsBYDAutoSettingListener listener96 : this.mAbsBydAutoSettingListeners) {
                    listener96.onMassageLevelStateChanged(3, settingPacket.getMassageLevelLeftBack());
                }
                caseFeatureIds(1286, Integer.valueOf(settingPacket.getMassageLevelLeftBack()));
                break;
            case 557857771:
                for (AbsBYDAutoSettingListener listener97 : this.mAbsBydAutoSettingListeners) {
                    listener97.onMassageLevelStateChanged(4, settingPacket.getMassageLevelRightBack());
                }
                caseFeatureIds(1297, Integer.valueOf(settingPacket.getMassageLevelRightBack()));
                break;
            case 557857776:
                for (AbsBYDAutoSettingListener listener98 : this.mAbsBydAutoSettingListeners) {
                    listener98.onMassageModeStateChanged(1, settingPacket.getMassageMode());
                }
                caseFeatureIds(1068, Integer.valueOf(settingPacket.getMassageMode()));
                break;
            case 557857778:
                for (AbsBYDAutoSettingListener listener99 : this.mAbsBydAutoSettingListeners) {
                    listener99.onMassageModeStateChanged(2, settingPacket.getMassageModeR4Fa());
                }
                caseFeatureIds(1071, Integer.valueOf(settingPacket.getMassageModeR4Fa()));
                break;
            case 557857780:
                for (AbsBYDAutoSettingListener listener100 : this.mAbsBydAutoSettingListeners) {
                    listener100.onMassageModeStateChanged(3, settingPacket.getMassageModeR4FaLeftBack());
                }
                caseFeatureIds(1288, Integer.valueOf(settingPacket.getMassageModeR4FaLeftBack()));
                break;
            case 557857783:
                for (AbsBYDAutoSettingListener listener101 : this.mAbsBydAutoSettingListeners) {
                    listener101.onMassageModeStateChanged(4, settingPacket.getMassageModeR4FaLeftFront());
                }
                caseFeatureIds(1299, Integer.valueOf(settingPacket.getMassageModeR4FaLeftFront()));
                break;
            case 557857788:
                for (AbsBYDAutoSettingListener listener102 : this.mAbsBydAutoSettingListeners) {
                    listener102.onEnergyFeedbackStrengthChanged(settingPacket.getEnergyFeedbackR341());
                }
                break;
            case 557857790:
                for (AbsBYDAutoSettingListener listener103 : this.mAbsBydAutoSettingListeners) {
                    listener103.onSOCTargetRangeChange(settingPacket.getSOCTarget());
                }
                break;
            case 557857792:
                for (AbsBYDAutoSettingListener listener104 : this.mAbsBydAutoSettingListeners) {
                    listener104.onChargingPortSwitchChanged(settingPacket.getChargingPort());
                }
                break;
            case 557857796:
                for (AbsBYDAutoSettingListener listener105 : this.mAbsBydAutoSettingListeners) {
                    listener105.onAutoExternalRearMirrorFollowUpStateChanged(settingPacket.getAutoExternalRearMirrorFollowUpSwitch());
                }
                break;
            case 557857799:
                for (AbsBYDAutoSettingListener listener106 : this.mAbsBydAutoSettingListeners) {
                    listener106.onOverSpeedLockStateChanged(settingPacket.getOverspeedLock());
                }
                break;
            case 557857800:
                for (AbsBYDAutoSettingListener listener107 : this.mAbsBydAutoSettingListeners) {
                    listener107.onSteerAssisModeChanged(settingPacket.getSteerAssis());
                }
                break;
            case 557857802:
                for (AbsBYDAutoSettingListener listener108 : this.mAbsBydAutoSettingListeners) {
                    listener108.onSteerAssisSupportChanged(settingPacket.getSteerAssisSupportedR());
                }
                break;
            case 557857804:
                for (AbsBYDAutoSettingListener listener109 : this.mAbsBydAutoSettingListeners) {
                    listener109.onTrayStateChanged(0, settingPacket.getonTrayStateChanged());
                }
                break;
            case 557857805:
                for (AbsBYDAutoSettingListener listener110 : this.mAbsBydAutoSettingListeners) {
                    listener110.onRecoverOrSaveParamsChanged(0, 1, 1, settingPacket.getonSaveSeatParamsAllChangedPlace5());
                }
                caseFeatureIds(1305, Integer.valueOf(settingPacket.getonSaveSeatParamsAllChangedPlace5()));
                break;
            case 557857806:
                for (AbsBYDAutoSettingListener listener111 : this.mAbsBydAutoSettingListeners) {
                    listener111.onRecoverOrSaveParamsChanged(0, 1, 3, settingPacket.getonSaveSeatParamsAllChangedPlace7());
                }
                break;
            case 557857807:
                for (AbsBYDAutoSettingListener listener112 : this.mAbsBydAutoSettingListeners) {
                    listener112.onRecoverOrSaveParamsChanged(0, 2, 3, settingPacket.getonSaveSeatParamsAllChanged3Ca());
                }
                caseFeatureIds(760, Integer.valueOf(settingPacket.getonSaveSeatParamsAllChanged3Ca()));
                break;
            case 557857808:
                for (AbsBYDAutoSettingListener listener113 : this.mAbsBydAutoSettingListeners) {
                    listener113.onRecoverOrSaveParamsChanged(0, 3, 3, settingPacket.getonSaveSeatParamsAllChanged402());
                }
                caseFeatureIds(733, Integer.valueOf(settingPacket.getonSaveSeatParamsAllChanged402()));
                break;
            case 557857809:
                for (AbsBYDAutoSettingListener listener114 : this.mAbsBydAutoSettingListeners) {
                    listener114.onRecoverOrSaveParamsChanged(0, 4, 3, settingPacket.getonSaveSeatParamsAllChanged42F());
                }
                caseFeatureIds(777, Integer.valueOf(settingPacket.getonSaveSeatParamsAllChanged42F()));
                break;
            case 557857813:
                for (AbsBYDAutoSettingListener listener115 : this.mAbsBydAutoSettingListeners) {
                    listener115.onSteeringWheelHeatingStateChanged(settingPacket.getSteeringWheelHeatingState());
                }
                caseFeatureIds(525, Integer.valueOf(settingPacket.getSteeringWheelHeatingState()));
                break;
            case 557857814:
                for (AbsBYDAutoSettingListener listener116 : this.mAbsBydAutoSettingListeners) {
                    listener116.onLockOffChanged(settingPacket.getLockOff());
                }
                break;
            case 557857833:
                for (AbsBYDAutoSettingListener listener117 : this.mAbsBydAutoSettingListeners) {
                    listener117.onComSpeedDownStateChanged(settingPacket.getComSpeedDownState());
                }
                break;
            case 557857835:
                for (AbsBYDAutoSettingListener listener118 : this.mAbsBydAutoSettingListeners) {
                    listener118.onHSpeedAutoCloseStateChanged(settingPacket.getHSpeedAutoCloseState());
                }
                break;
            case 557857838:
                for (AbsBYDAutoSettingListener listener119 : this.mAbsBydAutoSettingListeners) {
                    listener119.onComSpeedUpStateChanged(settingPacket.getComSpeedUpState());
                }
                break;
            case 557857840:
                for (AbsBYDAutoSettingListener listener120 : this.mAbsBydAutoSettingListeners) {
                    listener120.onPassengerStateChanged(settingPacket.getPassengerStateR());
                }
                caseFeatureIds(1414, Integer.valueOf(settingPacket.getPassengerStateR()));
                break;
            case 557857841:
                for (AbsBYDAutoSettingListener listener121 : this.mAbsBydAutoSettingListeners) {
                    listener121.onPassengerStateChanged(settingPacket.getPassengerRearLeftStateR());
                }
                caseFeatureIds(1415, Integer.valueOf(settingPacket.getPassengerRearLeftStateR()));
                break;
            case 557857842:
                for (AbsBYDAutoSettingListener listener122 : this.mAbsBydAutoSettingListeners) {
                    listener122.onPassengerStateChanged(settingPacket.getPassengerRearMiddleStateR());
                }
                caseFeatureIds(1416, Integer.valueOf(settingPacket.getPassengerRearMiddleStateR()));
                break;
            case 557857843:
                for (AbsBYDAutoSettingListener listener123 : this.mAbsBydAutoSettingListeners) {
                    listener123.onPassengerStateChanged(settingPacket.getPassengerRearRightStateR());
                }
                caseFeatureIds(1417, Integer.valueOf(settingPacket.getPassengerRearRightStateR()));
                break;
            case 557857844:
                for (AbsBYDAutoSettingListener listener124 : this.mAbsBydAutoSettingListeners) {
                    listener124.onPassengerStateChanged(settingPacket.getPassengerLastLeftStateR());
                }
                caseFeatureIds(535, Integer.valueOf(settingPacket.getPassengerLastLeftStateR()));
                break;
            case 557857845:
                for (AbsBYDAutoSettingListener listener125 : this.mAbsBydAutoSettingListeners) {
                    listener125.onPassengerStateChanged(settingPacket.getPassengerLastMiddleStateR());
                }
                caseFeatureIds(536, Integer.valueOf(settingPacket.getPassengerLastMiddleStateR()));
                break;
            case 557857846:
                for (AbsBYDAutoSettingListener listener126 : this.mAbsBydAutoSettingListeners) {
                    listener126.onPassengerStateChanged(settingPacket.getPassengerLastRightStateR());
                }
                caseFeatureIds(537, Integer.valueOf(settingPacket.getPassengerLastRightStateR()));
                break;
            case 557857847:
                for (AbsBYDAutoSettingListener listener127 : this.mAbsBydAutoSettingListeners) {
                    listener127.onRecoverOrSaveParamsChanged(0, 1, 2, settingPacket.getRecoverOrSaveParamsChanged2());
                }
                caseFeatureIds(1354, Integer.valueOf(settingPacket.getRecoverOrSaveParamsChanged2()));
                break;
            case 557857848:
                for (AbsBYDAutoSettingListener listener128 : this.mAbsBydAutoSettingListeners) {
                    listener128.onRecoverOrSaveParamsChanged(0, 1, 1, settingPacket.getRecoverOrSaveParamsChanged3());
                }
                break;
            case 557857849:
                for (AbsBYDAutoSettingListener listener129 : this.mAbsBydAutoSettingListeners) {
                    listener129.onRecoverOrSaveParamsChanged(0, 1, 2, settingPacket.getRecoverOrSaveParamsChanged4());
                }
                break;
            case 557857850:
                for (AbsBYDAutoSettingListener listener130 : this.mAbsBydAutoSettingListeners) {
                    listener130.onRecoverOrSaveParamsChanged(0, 1, 3, settingPacket.getRecoverOrSaveParamsChanged5());
                }
                break;
            case 557857851:
                for (AbsBYDAutoSettingListener listener131 : this.mAbsBydAutoSettingListeners) {
                    listener131.onRecoverOrSaveParamsChanged(1, 3, 3, settingPacket.getRecoverOrSaveParamsChanged6());
                }
                break;
            case 557857852:
                for (AbsBYDAutoSettingListener listener132 : this.mAbsBydAutoSettingListeners) {
                    listener132.onRecoverOrSaveParamsChanged(1, 4, 3, settingPacket.getRecoverOrSaveParamsChanged7());
                }
                break;
            case 557857853:
                for (AbsBYDAutoSettingListener listener133 : this.mAbsBydAutoSettingListeners) {
                    listener133.onHudPatternChanged(settingPacket.getHudPatternR());
                }
                break;
            case 557857855:
                for (AbsBYDAutoSettingListener listener134 : this.mAbsBydAutoSettingListeners) {
                    listener134.onHudSafeDriveStateChanged(settingPacket.getHudSafeDriveStateR());
                }
                break;
            case 557857857:
                for (AbsBYDAutoSettingListener listener135 : this.mAbsBydAutoSettingListeners) {
                    listener135.onHudNavigationStateChanged(settingPacket.getHudNavigationStateR());
                }
                break;
            case 557857859:
                for (AbsBYDAutoSettingListener listener136 : this.mAbsBydAutoSettingListeners) {
                    listener136.onHudSPStateChanged(settingPacket.getHudScreenProjectionStateR());
                }
                break;
            case 557857862:
                for (AbsBYDAutoSettingListener listener137 : this.mAbsBydAutoSettingListeners) {
                    listener137.onRuningCloseDoorStateChanged(settingPacket.getRuningCloseDoorStateR());
                }
                break;
            case 557857864:
                for (AbsBYDAutoSettingListener listener138 : this.mAbsBydAutoSettingListeners) {
                    listener138.onSeatHeatingState2Changed(3, settingPacket.getSeatHeatingState2BackLeftR());
                }
                caseFeatureIds(488, Integer.valueOf(settingPacket.getSeatHeatingState2BackLeftR()));
                break;
            case 557857865:
                for (AbsBYDAutoSettingListener listener139 : this.mAbsBydAutoSettingListeners) {
                    listener139.onSeatHeatingState2Changed(4, settingPacket.getSeatHeatingState2BackRightR());
                }
                caseFeatureIds(495, Integer.valueOf(settingPacket.getSeatHeatingState2BackRightR()));
                break;
            case 557857866:
                for (AbsBYDAutoSettingListener listener140 : this.mAbsBydAutoSettingListeners) {
                    listener140.onSeatHeatingNotL1PStateChanged(settingPacket.getSeatHeatingNotL1PStateR());
                }
                caseFeatureIds(482, Integer.valueOf(settingPacket.getSeatHeatingNotL1PStateR()));
                break;
            case 557857867:
                for (AbsBYDAutoSettingListener listener141 : this.mAbsBydAutoSettingListeners) {
                    listener141.onSeatVentilatingState2Changed(3, settingPacket.getSeatVentilatingState2BackLeftR());
                }
                caseFeatureIds(493, Integer.valueOf(settingPacket.getSeatVentilatingState2BackLeftR()));
                break;
            case 557857868:
                for (AbsBYDAutoSettingListener listener142 : this.mAbsBydAutoSettingListeners) {
                    listener142.onSeatVentilatingState2Changed(4, settingPacket.getSeatVentilatingState2BackRightR());
                }
                caseFeatureIds(496, Integer.valueOf(settingPacket.getSeatVentilatingState2BackRightR()));
                break;
            case 557857869:
                for (AbsBYDAutoSettingListener listener143 : this.mAbsBydAutoSettingListeners) {
                    listener143.onSeatVentilatingNotL1PStateChanged(settingPacket.getSeatVentilatingNotL1PStateR());
                }
                caseFeatureIds(483, Integer.valueOf(settingPacket.getSeatVentilatingNotL1PStateR()));
                break;
            case 557857870:
                for (AbsBYDAutoSettingListener listener144 : this.mAbsBydAutoSettingListeners) {
                    listener144.on5LevelSeatVentilatingStateChanged(1, settingPacket.get5LevelSeatVentilatingStateDomainR());
                }
                break;
            case 557857871:
                for (AbsBYDAutoSettingListener listener145 : this.mAbsBydAutoSettingListeners) {
                    listener145.on5LevelSeatVentilatingStateChanged(2, settingPacket.get5LevelSeatVentilatingStatePassengerR());
                }
                break;
            case 557857872:
                for (AbsBYDAutoSettingListener listener146 : this.mAbsBydAutoSettingListeners) {
                    listener146.on5LevelSeatVentilatingStateChanged(3, settingPacket.get5LevelSeatVentilatingStateBackLeftR());
                }
                break;
            case 557857873:
                for (AbsBYDAutoSettingListener listener147 : this.mAbsBydAutoSettingListeners) {
                    listener147.on5LevelSeatVentilatingStateChanged(4, settingPacket.get5LevelSeatVentilatingStateBackRightR());
                }
                break;
            case 557857878:
                for (AbsBYDAutoSettingListener listener148 : this.mAbsBydAutoSettingListeners) {
                    listener148.on5LevelSeatHeatingStateChanged(1, settingPacket.get5LevelSeatHeatingStateDomainR());
                }
                break;
            case 557857879:
                for (AbsBYDAutoSettingListener listener149 : this.mAbsBydAutoSettingListeners) {
                    listener149.on5LevelSeatHeatingStateChanged(2, settingPacket.get5LevelSeatHeatingStatePassengerR());
                }
                break;
            case 557857880:
                for (AbsBYDAutoSettingListener listener150 : this.mAbsBydAutoSettingListeners) {
                    listener150.on5LevelSeatHeatingStateChanged(3, settingPacket.get5LevelSeatHeatingStateBackLeftR());
                }
                break;
            case 557857881:
                for (AbsBYDAutoSettingListener listener151 : this.mAbsBydAutoSettingListeners) {
                    listener151.on5LevelSeatHeatingStateChanged(4, settingPacket.get5LevelSeatHeatingStateBackRightR());
                }
                break;
            case 557857886:
                for (AbsBYDAutoSettingListener listener152 : this.mAbsBydAutoSettingListeners) {
                    listener152.on5LevelSeatVentilatingModeChanged(1, settingPacket.get5LevelSeatVentilatingModeDomainR());
                }
                break;
            case 557857887:
                for (AbsBYDAutoSettingListener listener153 : this.mAbsBydAutoSettingListeners) {
                    listener153.on5LevelSeatVentilatingModeChanged(2, settingPacket.get5LevelSeatVentilatingModePassengerR());
                }
                break;
            case 557857888:
                for (AbsBYDAutoSettingListener listener154 : this.mAbsBydAutoSettingListeners) {
                    listener154.on5LevelSeatVentilatingModeChanged(3, settingPacket.get5LevelSeatVentilatingModeBackLeftR());
                }
                caseFeatureIds(3471, Integer.valueOf(settingPacket.get5LevelSeatVentilatingModeBackLeftR()));
                break;
            case 557857889:
                for (AbsBYDAutoSettingListener listener155 : this.mAbsBydAutoSettingListeners) {
                    listener155.on5LevelSeatVentilatingModeChanged(4, settingPacket.get5LevelSeatVentilatingModeBackRightR());
                }
                caseFeatureIds(3494, Integer.valueOf(settingPacket.get5LevelSeatVentilatingModeBackRightR()));
                break;
            case 557857894:
                for (AbsBYDAutoSettingListener listener156 : this.mAbsBydAutoSettingListeners) {
                    listener156.on5LevelSeatHeatingModeChanged(1, settingPacket.get5LevelSeatHeatingModeDomainR());
                }
                break;
            case 557857895:
                for (AbsBYDAutoSettingListener listener157 : this.mAbsBydAutoSettingListeners) {
                    listener157.on5LevelSeatHeatingModeChanged(2, settingPacket.get5LevelSeatHeatingModePassengerR());
                }
                break;
            case 557857896:
                for (AbsBYDAutoSettingListener listener158 : this.mAbsBydAutoSettingListeners) {
                    listener158.on5LevelSeatHeatingModeChanged(3, settingPacket.get5LevelSeatHeatingModeBackLeftR());
                }
                break;
            case 557857897:
                for (AbsBYDAutoSettingListener listener159 : this.mAbsBydAutoSettingListeners) {
                    listener159.on5LevelSeatHeatingModeChanged(4, settingPacket.get5LevelSeatHeatingModeBackRightR());
                }
                break;
            case 557857902:
                for (AbsBYDAutoSettingListener listener160 : this.mAbsBydAutoSettingListeners) {
                    listener160.on5LevelSeatVentilatingLevelChanged(1, settingPacket.get5LevelSeatVentilatingLevelDomainR());
                }
                break;
            case 557857903:
                for (AbsBYDAutoSettingListener listener161 : this.mAbsBydAutoSettingListeners) {
                    listener161.on5LevelSeatVentilatingLevelChanged(2, settingPacket.get5LevelSeatVentilatingLevelPassengerR());
                }
                break;
            case 557857904:
                for (AbsBYDAutoSettingListener listener162 : this.mAbsBydAutoSettingListeners) {
                    listener162.on5LevelSeatVentilatingLevelChanged(3, settingPacket.get5LevelSeatVentilatingLevelBackLeftR());
                }
                break;
            case 557857905:
                for (AbsBYDAutoSettingListener listener163 : this.mAbsBydAutoSettingListeners) {
                    listener163.on5LevelSeatVentilatingLevelChanged(4, settingPacket.get5LevelSeatVentilatingLevelBackRightR());
                }
                break;
            case 557857910:
                for (AbsBYDAutoSettingListener listener164 : this.mAbsBydAutoSettingListeners) {
                    listener164.on5LevelSeatHeatingLevelChanged(1, settingPacket.get5LevelSeatHeatingLevelDomainR());
                }
                break;
            case 557857911:
                for (AbsBYDAutoSettingListener listener165 : this.mAbsBydAutoSettingListeners) {
                    listener165.on5LevelSeatHeatingLevelChanged(2, settingPacket.get5LevelSeatHeatingLevelPassengerR());
                }
                break;
            case 557857912:
                for (AbsBYDAutoSettingListener listener166 : this.mAbsBydAutoSettingListeners) {
                    listener166.on5LevelSeatHeatingLevelChanged(3, settingPacket.get5LevelSeatHeatingLevelBackLeftR());
                }
                break;
            case 557857913:
                for (AbsBYDAutoSettingListener listener167 : this.mAbsBydAutoSettingListeners) {
                    listener167.on5LevelSeatHeatingLevelChanged(4, settingPacket.get5LevelSeatHeatingLevelBackRightR());
                }
                break;
            case 557857919:
                for (AbsBYDAutoSettingListener listener168 : this.mAbsBydAutoSettingListeners) {
                    listener168.onSteeringWheelHeatingGearChanged(settingPacket.getSteeringWheelHeatingGearR());
                }
                caseFeatureIds(384, Integer.valueOf(settingPacket.getSteeringWheelHeatingGearR()));
                break;
            case 557857921:
                for (AbsBYDAutoSettingListener listener169 : this.mAbsBydAutoSettingListeners) {
                    listener169.onSteeringWheelHeatingAutoStateChanged(settingPacket.getSteeringWheelHeatingAutoStateR());
                }
                break;
            case 557857922:
                for (AbsBYDAutoSettingListener listener170 : this.mAbsBydAutoSettingListeners) {
                    listener170.onMassageLevelStateChanged(3, settingPacket.getEcuMassageLevelBackLeftR());
                }
                caseFeatureIds(1286, Integer.valueOf(settingPacket.getEcuMassageLevelBackLeftR()));
                break;
            case 557857923:
                for (AbsBYDAutoSettingListener listener171 : this.mAbsBydAutoSettingListeners) {
                    listener171.onMassageLevelStateChanged(4, settingPacket.getEcuMassageLevelBackRightR());
                }
                caseFeatureIds(1297, Integer.valueOf(settingPacket.getEcuMassageLevelBackRightR()));
                break;
            case 557857928:
                for (AbsBYDAutoSettingListener listener172 : this.mAbsBydAutoSettingListeners) {
                    listener172.onMassageModeStateChanged(3, settingPacket.getEcuMassageModeBackLeftR());
                }
                caseFeatureIds(1288, Integer.valueOf(settingPacket.getEcuMassageModeBackLeftR()));
                break;
            case 557857929:
                for (AbsBYDAutoSettingListener listener173 : this.mAbsBydAutoSettingListeners) {
                    listener173.onMassageModeStateChanged(4, settingPacket.getEcuMassageModeBackRightR());
                }
                caseFeatureIds(1299, Integer.valueOf(settingPacket.getEcuMassageModeBackRightR()));
                break;
            case 557857938:
                for (AbsBYDAutoSettingListener listener174 : this.mAbsBydAutoSettingListeners) {
                    listener174.onHsMassageStateChanged(1, settingPacket.getHsMassageStateDomainR());
                }
                break;
            case 557857944:
                for (AbsBYDAutoSettingListener listener175 : this.mAbsBydAutoSettingListeners) {
                    listener175.onHevChargingPortSwitchChanged(settingPacket.getHevChargingPortR());
                }
                break;
            case 557857945:
                for (AbsBYDAutoSettingListener listener176 : this.mAbsBydAutoSettingListeners) {
                    listener176.onKeyVibratorLevelChanged(settingPacket.getKeyVibratorLevelR());
                }
                break;
            case 557857947:
                for (AbsBYDAutoSettingListener listener177 : this.mAbsBydAutoSettingListeners) {
                    listener177.onIVIProfileSettingsChanged(settingPacket.getIviProfileSettingsR());
                }
                break;
            case 557857951:
                for (AbsBYDAutoSettingListener listener178 : this.mAbsBydAutoSettingListeners) {
                    listener178.onFdComSpeedUpStateChanged(settingPacket.getFdComSpeedUpStateR());
                }
                break;
            case 557857957:
                for (AbsBYDAutoSettingListener listener179 : this.mAbsBydAutoSettingListeners) {
                    listener179.onRearviewMirrorIsTurnedStateChanged(settingPacket.getRearviewMirrorIsTurnedStateR());
                }
                break;
            case 557857958:
                for (AbsBYDAutoSettingListener listener180 : this.mAbsBydAutoSettingListeners) {
                    listener180.onRearviewMirrorIsStateChanged(settingPacket.getRearviewMirrorIsStateR());
                }
                break;
            case 557857959:
                for (AbsBYDAutoSettingListener listener181 : this.mAbsBydAutoSettingListeners) {
                    listener181.onRearviewMirrorIsAutoLightStateChanged(settingPacket.getRearviewMirrorIsAutoLightStateR());
                }
                break;
            case 557857962:
                for (AbsBYDAutoSettingListener listener182 : this.mAbsBydAutoSettingListeners) {
                    listener182.onRefrigeratorWorkModeChanged(settingPacket.getRefrigeratorWorkModeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORKING_STATUS, Integer.valueOf(settingPacket.getRefrigeratorWorkModeR()));
                break;
            case 557857964:
                for (AbsBYDAutoSettingListener listener183 : this.mAbsBydAutoSettingListeners) {
                    listener183.onRefrigeratorWorkTemperatureChanged(settingPacket.getRefrigeratorWorkTemperatureR());
                }
                break;
            case 557857965:
                for (AbsBYDAutoSettingListener listener184 : this.mAbsBydAutoSettingListeners) {
                    listener184.onFridgeWorkColdTemperatureChanged(settingPacket.getFridgeWorkColdTemperatureR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_COLD, Integer.valueOf(settingPacket.getFridgeWorkColdTemperatureR()));
                break;
            case 557857966:
                for (AbsBYDAutoSettingListener listener185 : this.mAbsBydAutoSettingListeners) {
                    listener185.onFridgeWorkHeatTemperatureChanged(settingPacket.getFridgeWorkHeatTemperatureR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_HEAT, Integer.valueOf(settingPacket.getFridgeWorkHeatTemperatureR()));
                break;
            case 557857967:
                for (AbsBYDAutoSettingListener listener186 : this.mAbsBydAutoSettingListeners) {
                    listener186.onFridgeWorkSaveTemperatureChanged(settingPacket.getFridgeWorkSaveTemperatureR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_FRESH, Integer.valueOf(settingPacket.getFridgeWorkSaveTemperatureR()));
                break;
            case 557857968:
                for (AbsBYDAutoSettingListener listener187 : this.mAbsBydAutoSettingListeners) {
                    listener187.onDelayPowerOffStateChanged(settingPacket.getDelayPowerOffStateR());
                }
                break;
            case 557857970:
                for (AbsBYDAutoSettingListener listener188 : this.mAbsBydAutoSettingListeners) {
                    listener188.onDelayPowerOffTimeChanged(settingPacket.getDelayPowerOffTimeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWEROFF_TIME, Integer.valueOf(settingPacket.getDelayPowerOffTimeR()));
                break;
            case 557857972:
                for (AbsBYDAutoSettingListener listener189 : this.mAbsBydAutoSettingListeners) {
                    listener189.onDelayPowerDownTimeChanged(settingPacket.getDelayPowerDownTimeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE, Integer.valueOf(settingPacket.getDelayPowerDownTimeR()));
                break;
            case 557857974:
                for (AbsBYDAutoSettingListener listener190 : this.mAbsBydAutoSettingListeners) {
                    listener190.onRemberPowerOffStateChanged(settingPacket.getRemberPowerOffStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POWEROFF_MEMORY, Integer.valueOf(settingPacket.getRemberPowerOffStateR()));
                break;
            case 557857976:
                for (AbsBYDAutoSettingListener listener191 : this.mAbsBydAutoSettingListeners) {
                    listener191.onSterilizationStateChanged(settingPacket.getSterilizationStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS, Integer.valueOf(settingPacket.getSterilizationStateR()));
                break;
            case 557857977:
                for (AbsBYDAutoSettingListener listener192 : this.mAbsBydAutoSettingListeners) {
                    listener192.onVoiceTipsMessageChanged(settingPacket.getVoiceTipsMessageR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POPUP_REMIND, Integer.valueOf(settingPacket.getVoiceTipsMessageR()));
                break;
            case 557857979:
                for (AbsBYDAutoSettingListener listener193 : this.mAbsBydAutoSettingListeners) {
                    listener193.onFridgeDoorStateChanged(settingPacket.getFridgeDoorStateR());
                }
                break;
            case 557857980:
                for (AbsBYDAutoSettingListener listener194 : this.mAbsBydAutoSettingListeners) {
                    listener194.onRefrigeratorWorkStateChanged(settingPacket.getRefrigeratorWorkStateR());
                }
                break;
            case 557857981:
                for (AbsBYDAutoSettingListener listener195 : this.mAbsBydAutoSettingListeners) {
                    listener195.onRefrigeratorTemperatureChanged(settingPacket.getRefrigeratorTemperatureR());
                }
                break;
            case 557857982:
                for (AbsBYDAutoSettingListener listener196 : this.mAbsBydAutoSettingListeners) {
                    listener196.onDelayDownPowerTimeChanged(settingPacket.getDelayDownPowerTimeR());
                }
                break;
            case 557857983:
                for (AbsBYDAutoSettingListener listener197 : this.mAbsBydAutoSettingListeners) {
                    listener197.onSterilizationWorkStateChanged(settingPacket.getSterilizationWorkStateR());
                }
                break;
            case 557857984:
                for (AbsBYDAutoSettingListener listener198 : this.mAbsBydAutoSettingListeners) {
                    listener198.onDlgMessageCodeChanged(settingPacket.getDlgMessageCodeR());
                }
                break;
            case 557857985:
                for (AbsBYDAutoSettingListener listener199 : this.mAbsBydAutoSettingListeners) {
                    listener199.onFridgeLocationChanged(settingPacket.getFridgeLocationR());
                }
                break;
            case 557857990:
                for (AbsBYDAutoSettingListener listener200 : this.mAbsBydAutoSettingListeners) {
                    listener200.onControlCallbackStateChanged(settingPacket.getControlCallbackStateR());
                }
                break;
            case 557857991:
                for (AbsBYDAutoSettingListener listener201 : this.mAbsBydAutoSettingListeners) {
                    listener201.onWorkStateChanged(settingPacket.getWorkStateR());
                }
                break;
            case 557857992:
                for (AbsBYDAutoSettingListener listener202 : this.mAbsBydAutoSettingListeners) {
                    listener202.onDelayPowerOffStatusChanged(settingPacket.getDelayPowerOffStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_PWOEROFF_SWITCH, Integer.valueOf(settingPacket.getDelayPowerOffStatusR()));
                break;
            case 557857993:
                for (AbsBYDAutoSettingListener listener203 : this.mAbsBydAutoSettingListeners) {
                    listener203.onScreenCallbackMessageChanged(settingPacket.getScreenCallbackMessageR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_BOUNCE_SIGNAL, Integer.valueOf(settingPacket.getScreenCallbackMessageR()));
                break;
            case 557857994:
                for (AbsBYDAutoSettingListener listener204 : this.mAbsBydAutoSettingListeners) {
                    listener204.onRearDoorStateChanged(settingPacket.getRearDoorStateR());
                }
                break;
            case 557857995:
                for (AbsBYDAutoSettingListener listener205 : this.mAbsBydAutoSettingListeners) {
                    listener205.onRearHeatTempChanged(settingPacket.getRearHeatTempR());
                }
                break;
            case 557857996:
                for (AbsBYDAutoSettingListener listener206 : this.mAbsBydAutoSettingListeners) {
                    listener206.onRearSaveTempChanged(settingPacket.getRearSaveTempR());
                }
                break;
            case 557857997:
                for (AbsBYDAutoSettingListener listener207 : this.mAbsBydAutoSettingListeners) {
                    listener207.onPhoneRemindCodeChanged(settingPacket.getPhoneRemindCodeR());
                }
                break;
            case 557857998:
                for (AbsBYDAutoSettingListener listener208 : this.mAbsBydAutoSettingListeners) {
                    listener208.onServiceControlCallbackChanged(settingPacket.getServiceControlCallbackR());
                }
                break;
            case 557858010:
                for (AbsBYDAutoSettingListener listener209 : this.mAbsBydAutoSettingListeners) {
                    listener209.onCanopyAutomaticDimming(settingPacket.getCanopyAutomaticDimmingR());
                }
                caseFeatureIds(921, Integer.valueOf(settingPacket.getCanopyAutomaticDimmingR()));
                break;
            case 557858011:
                for (AbsBYDAutoSettingListener listener210 : this.mAbsBydAutoSettingListeners) {
                    listener210.onSkyCurtainPowerOffAndDim(settingPacket.getSkyCurtainPowerOffAndDimR());
                }
                caseFeatureIds(925, Integer.valueOf(settingPacket.getSkyCurtainPowerOffAndDimR()));
                break;
            case 557858016:
                for (AbsBYDAutoSettingListener listener211 : this.mAbsBydAutoSettingListeners) {
                    listener211.onCurrentTransmittanceOfSkylight(settingPacket.getCurrentTransmittanceOfSkylightR());
                }
                caseFeatureIds(924, Integer.valueOf(settingPacket.getCurrentTransmittanceOfSkylightR()));
                break;
            case 557858017:
                for (AbsBYDAutoSettingListener listener212 : this.mAbsBydAutoSettingListeners) {
                    listener212.onCurrentStatusOfDimmingSunroof(settingPacket.getCurrentStatusOfDimmingSunroofR());
                }
                caseFeatureIds(888, Integer.valueOf(settingPacket.getCurrentStatusOfDimmingSunroofR()));
                break;
            case 557858018:
                for (AbsBYDAutoSettingListener listener213 : this.mAbsBydAutoSettingListeners) {
                    listener213.onDimmingCanopyLowCode(settingPacket.getDimmingCanopyLowCodeR());
                }
                caseFeatureIds(4625, Integer.valueOf(settingPacket.getDimmingCanopyLowCodeR()));
                break;
            case 557858020:
                for (AbsBYDAutoSettingListener listener214 : this.mAbsBydAutoSettingListeners) {
                    listener214.onTailInitializationStateChanged(settingPacket.getTailInitializationStateR());
                }
                caseFeatureIds(4629, Integer.valueOf(settingPacket.getTailInitializationStateR()));
                break;
            case 557858021:
                for (AbsBYDAutoSettingListener listener215 : this.mAbsBydAutoSettingListeners) {
                    listener215.onCurrentStateOfTheTailStateChanged(settingPacket.getCurrentStateOfTheTailStateR());
                }
                caseFeatureIds(4630, Integer.valueOf(settingPacket.getCurrentStateOfTheTailStateR()));
                break;
            case 557858022:
                for (AbsBYDAutoSettingListener listener216 : this.mAbsBydAutoSettingListeners) {
                    listener216.onTailPositionPercentageChanged(settingPacket.getTailPositionPercentageR());
                }
                caseFeatureIds(4631, Integer.valueOf(settingPacket.getTailPositionPercentageR()));
                break;
            case 557858023:
                for (AbsBYDAutoSettingListener listener217 : this.mAbsBydAutoSettingListeners) {
                    listener217.onTailMotorThermalProtectionStateChanged(settingPacket.getTailMotorThermalProtectionStateR());
                }
                caseFeatureIds(4632, Integer.valueOf(settingPacket.getTailMotorThermalProtectionStateR()));
                break;
            case 557858024:
                for (AbsBYDAutoSettingListener listener218 : this.mAbsBydAutoSettingListeners) {
                    listener218.onTailPositionFeedbackChanged(settingPacket.getTailPositionFeedbackR());
                }
                caseFeatureIds(4633, Integer.valueOf(settingPacket.getTailPositionFeedbackR()));
                break;
            case 557858025:
                for (AbsBYDAutoSettingListener listener219 : this.mAbsBydAutoSettingListeners) {
                    listener219.onAlarmPromptStateChanged(settingPacket.getAlarmPromptStateR());
                }
                caseFeatureIds(4634, Integer.valueOf(settingPacket.getAlarmPromptStateR()));
                break;
            case 557858026:
                for (AbsBYDAutoSettingListener listener220 : this.mAbsBydAutoSettingListeners) {
                    listener220.onCurrentModeOfTailStateChanged(settingPacket.getCurrentModeOfTailStateR());
                }
                caseFeatureIds(4635, Integer.valueOf(settingPacket.getCurrentModeOfTailStateR()));
                break;
            case 557858027:
                for (AbsBYDAutoSettingListener listener221 : this.mAbsBydAutoSettingListeners) {
                    listener221.onElectricTailMaintenanceFuntatusStateChanged(settingPacket.getElectricTailMaintenanceFuntatusStateR());
                }
                caseFeatureIds(4636, Integer.valueOf(settingPacket.getElectricTailMaintenanceFuntatusStateR()));
                break;
            case 557858058:
                for (AbsBYDAutoSettingListener listener222 : this.mAbsBydAutoSettingListeners) {
                    listener222.onElectricPedalStateChanged(1, settingPacket.getLeftElectricPedalR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_LEFT_ELECTRIC_PEDAL_POSITION, Integer.valueOf(settingPacket.getLeftElectricPedalR()));
                break;
            case 557858060:
                for (AbsBYDAutoSettingListener listener223 : this.mAbsBydAutoSettingListeners) {
                    listener223.onElectricPedalStateChanged(2, settingPacket.getRightElectricPedalR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_RIGHT_ELECTRIC_PEDAL_POSITION, Integer.valueOf(settingPacket.getRightElectricPedalR()));
                break;
            case 557858063:
                for (AbsBYDAutoSettingListener listener224 : this.mAbsBydAutoSettingListeners) {
                    listener224.onFridgeChildLockChanged(settingPacket.getFridgeChildLockR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_STATE, Integer.valueOf(settingPacket.getFridgeChildLockR()));
                break;
            case 557858083:
                for (AbsBYDAutoSettingListener listener225 : this.mAbsBydAutoSettingListeners) {
                    listener225.onSOCTargetRangeChanged(settingPacket.getsocTargetRangeR());
                }
                break;
            case 557858084:
                for (AbsBYDAutoSettingListener listener226 : this.mAbsBydAutoSettingListeners) {
                    listener226.onSOCTargetLowLimitChanged(settingPacket.getsocTargetLowLimitR());
                }
                break;
            case 557858089:
                for (AbsBYDAutoSettingListener listener227 : this.mAbsBydAutoSettingListeners) {
                    listener227.onCpdImsSwitchStateChanged(settingPacket.getCpdImsSwitchStateR());
                }
                break;
            case 557858090:
                for (AbsBYDAutoSettingListener listener228 : this.mAbsBydAutoSettingListeners) {
                    listener228.onRncStateChanged(settingPacket.getRncStateR());
                }
                break;
            case 557858091:
                for (AbsBYDAutoSettingListener listener229 : this.mAbsBydAutoSettingListeners) {
                    listener229.onSwControlStateChanged(settingPacket.getSwcontrolStateR());
                }
                break;
            case 557858093:
                for (AbsBYDAutoSettingListener listener230 : this.mAbsBydAutoSettingListeners) {
                    listener230.onPedalSuppressionSystemStateChanged(settingPacket.getPedalSuppressionSystemStateR());
                }
                break;
            case 557858096:
                for (AbsBYDAutoSettingListener listener231 : this.mAbsBydAutoSettingListeners) {
                    listener231.onCuraSwitchStateChanged(settingPacket.getCuraSwitchStateR());
                }
                break;
            case 557858102:
                for (AbsBYDAutoSettingListener listener232 : this.mAbsBydAutoSettingListeners) {
                    listener232.onRearRightSeatGreetStateChanged(settingPacket.getRearRightSeatGreetStateR());
                }
                break;
            case 557858107:
                for (AbsBYDAutoSettingListener listener233 : this.mAbsBydAutoSettingListeners) {
                    listener233.onWadingPatternSocTipsChanaged(settingPacket.getWadingPatternSocTipsR());
                }
                break;
            case 557858108:
                for (AbsBYDAutoSettingListener listener234 : this.mAbsBydAutoSettingListeners) {
                    listener234.onWadingPatternSpeedTipsChanged(settingPacket.getWadingPatternSpeedTipsR());
                }
                break;
            case 557858109:
                for (AbsBYDAutoSettingListener listener235 : this.mAbsBydAutoSettingListeners) {
                    listener235.onWadingPatternTestTipsChanged(settingPacket.getWadingPatternTestTipsR());
                }
                break;
            case 557858110:
                for (AbsBYDAutoSettingListener listener236 : this.mAbsBydAutoSettingListeners) {
                    listener236.onWadingPatternStateTipsChanged(settingPacket.getWadingPatternStateTipsR());
                }
                break;
            case 557858111:
                for (AbsBYDAutoSettingListener listener237 : this.mAbsBydAutoSettingListeners) {
                    listener237.onSeaModeChargingSocTypeChanged(settingPacket.getSeaModeChargingSocTypeR());
                }
                break;
            case 557858112:
                for (AbsBYDAutoSettingListener listener238 : this.mAbsBydAutoSettingListeners) {
                    listener238.onDriveModeChanged(settingPacket.getDriveModeR());
                }
                break;
            case 557858114:
                for (AbsBYDAutoSettingListener listener239 : this.mAbsBydAutoSettingListeners) {
                    listener239.onSandAbsModeStateChanged(settingPacket.getSandAbsModeStateR());
                }
                break;
            case 557858115:
                for (AbsBYDAutoSettingListener listener240 : this.mAbsBydAutoSettingListeners) {
                    listener240.onSandAbsTipMessageChanged(settingPacket.getSandAbsTipMessageR());
                }
                break;
            case 557858117:
                for (AbsBYDAutoSettingListener listener241 : this.mAbsBydAutoSettingListeners) {
                    listener241.onSaveOwnerStateChanged(settingPacket.getSaveOwnerStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_EXCLUSIVE_SAVE_SHORTCUT_KEY, Integer.valueOf(settingPacket.getSaveOwnerStateR()));
                break;
            case 557858119:
                for (AbsBYDAutoSettingListener listener242 : this.mAbsBydAutoSettingListeners) {
                    listener242.onRefrigeratorMobileTerminalPushReChanged(settingPacket.getRefrigeratorMobileTerminalPushReR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_MOBINE_PUSH_REMIND, Integer.valueOf(settingPacket.getRefrigeratorMobileTerminalPushReR()));
                break;
            case 557858123:
                for (AbsBYDAutoSettingListener listener243 : this.mAbsBydAutoSettingListeners) {
                    listener243.onRefrigWorkModeChanged(settingPacket.getRefrigWorkModeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE, Integer.valueOf(settingPacket.getRefrigWorkModeR()));
                break;
            case 557858125:
                for (AbsBYDAutoSettingListener listener244 : this.mAbsBydAutoSettingListeners) {
                    listener244.onDriverSeatVentilating437StateChanged(settingPacket.getDriverSeatVentilating437StateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SET_DRIVER_SEAT_VENTILATING_STATE_hal_only, Integer.valueOf(settingPacket.getDriverSeatVentilating437StateR()));
                break;
            case 557858126:
                for (AbsBYDAutoSettingListener listener245 : this.mAbsBydAutoSettingListeners) {
                    listener245.onDriverSeatHeating437StateChanged(settingPacket.getDriverSeatHeating437StateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SET_DRIVER_SEAT_HEATING_STATE_hal_only, Integer.valueOf(settingPacket.getDriverSeatHeating437StateR()));
                break;
            case 557858127:
                for (AbsBYDAutoSettingListener listener246 : this.mAbsBydAutoSettingListeners) {
                    listener246.onPassengerSeatVentilating3CAStateChanged(settingPacket.getPassengerSeatVentilating3caStateR());
                }
                break;
            case 557858128:
                for (AbsBYDAutoSettingListener listener247 : this.mAbsBydAutoSettingListeners) {
                    listener247.onPassengerSeatHeating3CAStateChanged(settingPacket.getPassengerSeatHeating3caStateR());
                }
                break;
            case 557858140:
                for (AbsBYDAutoSettingListener listener248 : this.mAbsBydAutoSettingListeners) {
                    listener248.onThirdRowSeatWorkModeChanged(settingPacket.getThirdRowSeatWorkModeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_WORKING_MODE, Integer.valueOf(settingPacket.getThirdRowSeatWorkModeR()));
                break;
            case 557858141:
                for (AbsBYDAutoSettingListener listener249 : this.mAbsBydAutoSettingListeners) {
                    listener249.onLeftRearSafebeltStatusChanged(settingPacket.getLeftRearSafebeltStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_LEFT_REAR_ROW_SAFETYBELT_STATUS, Integer.valueOf(settingPacket.getLeftRearSafebeltStatusR()));
                break;
            case 557858142:
                for (AbsBYDAutoSettingListener listener250 : this.mAbsBydAutoSettingListeners) {
                    listener250.onCenterRearSafebeltStatusChanged(settingPacket.getCenterRearSafebeltStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_CENTER_REAR_ROW_SAFETYBELT_STATUS, Integer.valueOf(settingPacket.getCenterRearSafebeltStatusR()));
                break;
            case 557858143:
                for (AbsBYDAutoSettingListener listener251 : this.mAbsBydAutoSettingListeners) {
                    listener251.onRightRearSafebeltStatusChanged(settingPacket.getRightRearSafebeltStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_RIGHT_REAR_ROW_SAFETYBELT_STATUS, Integer.valueOf(settingPacket.getRightRearSafebeltStatusR()));
                break;
            case 557858144:
                for (AbsBYDAutoSettingListener listener252 : this.mAbsBydAutoSettingListeners) {
                    listener252.onLeftThirdSafebeltStatusChanged(settingPacket.getLeftThirdSafebeltStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_THIRD_ROW_LEFT_SAFETYBELT_STATUS, Integer.valueOf(settingPacket.getLeftThirdSafebeltStatusR()));
                break;
            case 557858145:
                for (AbsBYDAutoSettingListener listener253 : this.mAbsBydAutoSettingListeners) {
                    listener253.onRightThirdSafebeltStatusChanged(settingPacket.getRightThirdSafebeltStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_THIRD_ROW_RIGHT_SAFETYBELT_STATUS, Integer.valueOf(settingPacket.getRightThirdSafebeltStatusR()));
                break;
            case 557858146:
                for (AbsBYDAutoSettingListener listener254 : this.mAbsBydAutoSettingListeners) {
                    listener254.onMainSeatHorizontalInitChanged(settingPacket.getMainSeatHorizontalInitR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_LF_SEAT_HORIZONTAL_UNINITIALIZED, Integer.valueOf(settingPacket.getMainSeatHorizontalInitR()));
                break;
            case 557858147:
                for (AbsBYDAutoSettingListener listener255 : this.mAbsBydAutoSettingListeners) {
                    listener255.onMainSeatBackInitChanged(settingPacket.getMainSeatBackInitR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_LF_SEAT_BACKREST_UNINITIALIZED, Integer.valueOf(settingPacket.getMainSeatBackInitR()));
                break;
            case 557858148:
                for (AbsBYDAutoSettingListener listener256 : this.mAbsBydAutoSettingListeners) {
                    listener256.onPassangerSeatHorizontalInitChanged(settingPacket.getPassangerSeatHorizontalInitR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_RF_SEAT_HORIZONTAL_UNINITIALIZED, Integer.valueOf(settingPacket.getPassangerSeatHorizontalInitR()));
                break;
            case 557858149:
                for (AbsBYDAutoSettingListener listener257 : this.mAbsBydAutoSettingListeners) {
                    listener257.onPassangerSeatBackInitChanged(settingPacket.getPassangerSeatBackInitR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_RF_SEAT_BACKREST_UNINITIALIZED, Integer.valueOf(settingPacket.getPassangerSeatBackInitR()));
                break;
            case 557858150:
                for (AbsBYDAutoSettingListener listener258 : this.mAbsBydAutoSettingListeners) {
                    listener258.onSecondLeftSeatHorizontalInitChanged(settingPacket.getSecondLeftSeatHorizontalInitR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_SECOND_ROW_LEFT_SEAT_HORIZONTAL_DIRECTION_INITIALIZATION_STATUS, Integer.valueOf(settingPacket.getSecondLeftSeatHorizontalInitR()));
                break;
            case 557858151:
                for (AbsBYDAutoSettingListener listener259 : this.mAbsBydAutoSettingListeners) {
                    listener259.onSecondLeftSeatBackInitChanged(settingPacket.getSecondLeftSeatBackInitR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_SECOND_ROW_LEFT_SEAT_BACKREST_DIRECTION_INITIALIZATION_STATUS, Integer.valueOf(settingPacket.getSecondLeftSeatBackInitR()));
                break;
            case 557858152:
                for (AbsBYDAutoSettingListener listener260 : this.mAbsBydAutoSettingListeners) {
                    listener260.onSecondRightSeatHorizontalInitChanged(settingPacket.getSecondRightSeatHorizontalInitR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_SECOND_ROW_RIGHT_SEAT_HORIZONTAL_DIRECTION_INITIALIZATION_STATUS, Integer.valueOf(settingPacket.getSecondRightSeatHorizontalInitR()));
                break;
            case 557858153:
                for (AbsBYDAutoSettingListener listener261 : this.mAbsBydAutoSettingListeners) {
                    listener261.onSecondRightSeatBackInitChanged(settingPacket.getSecondRightSeatBackInitR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_SECOND_ROW_RIGHT_SEAT_BACKREST_DIRECTION_INITIALIZATION_STATUS, Integer.valueOf(settingPacket.getSecondRightSeatBackInitR()));
                break;
            case 557858159:
                for (AbsBYDAutoSettingListener listener262 : this.mAbsBydAutoSettingListeners) {
                    listener262.onWashElectricPedalChanged(settingPacket.getWashElectricPedalR());
                }
                break;
            case 557858160:
                for (AbsBYDAutoSettingListener listener263 : this.mAbsBydAutoSettingListeners) {
                    listener263.onTapDoorStateChanged(settingPacket.getTapDoorStateR());
                }
                break;
            case 557858161:
                for (AbsBYDAutoSettingListener listener264 : this.mAbsBydAutoSettingListeners) {
                    listener264.onMainSeatHorizontalInited(settingPacket.getMainSeatHorizontalInitedR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_LF_SEAT_HORIZONTAL_UNINITIALIZED_437_hal_only, Integer.valueOf(settingPacket.getMainSeatHorizontalInitedR()));
                break;
            case 557858162:
                for (AbsBYDAutoSettingListener listener265 : this.mAbsBydAutoSettingListeners) {
                    listener265.onMainSeatBackInited(settingPacket.getMainSeatBackInitedR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_LF_SEAT_BACKREST_UNINITIALIZED_437_hal_only, Integer.valueOf(settingPacket.getMainSeatBackInitedR()));
                break;
            case 557858163:
                for (AbsBYDAutoSettingListener listener266 : this.mAbsBydAutoSettingListeners) {
                    listener266.onMainSeatHightInited(settingPacket.getMainSeatHightInitedR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_LF_SEAT_HEIGHT_UNINITIALIZED_437_hal_only, Integer.valueOf(settingPacket.getMainSeatHightInitedR()));
                break;
            case 557858165:
                for (AbsBYDAutoSettingListener listener267 : this.mAbsBydAutoSettingListeners) {
                    listener267.onSecondRightSeatBack2InitChanged(settingPacket.getSecondRightSeatHorizontal2InitR());
                }
                break;
            case 557858166:
                for (AbsBYDAutoSettingListener listener268 : this.mAbsBydAutoSettingListeners) {
                    listener268.onSecondRightSeatHorizontal2InitChanged(settingPacket.getSecondRightSeatBack2InitR());
                }
                break;
            case 557858167:
                for (AbsBYDAutoSettingListener listener269 : this.mAbsBydAutoSettingListeners) {
                    listener269.onDragTypeChanged(settingPacket.getDragTypeR());
                }
                break;
            case 557858168:
                for (AbsBYDAutoSettingListener listener270 : this.mAbsBydAutoSettingListeners) {
                    listener270.onDragConnectionTypeChanged(settingPacket.getDragConnectionTypeR());
                }
                break;
            case 557858173:
                for (AbsBYDAutoSettingListener listener271 : this.mAbsBydAutoSettingListeners) {
                    listener271.onTrailerDragTypeChanged(settingPacket.getTrailerDragTypeR());
                }
                break;
            case 557858177:
                for (AbsBYDAutoSettingListener listener272 : this.mAbsBydAutoSettingListeners) {
                    listener272.onTowingProhibitedStateChanged(settingPacket.getTowingProhibitedStateR());
                }
                break;
            case 557858190:
                for (AbsBYDAutoSettingListener listener273 : this.mAbsBydAutoSettingListeners) {
                    listener273.onUcuMassageModeChanged(1, settingPacket.getUcuMassageModeMainR());
                }
                caseFeatureIds(1068, Integer.valueOf(settingPacket.getUcuMassageModeMainR()));
                break;
            case 557858191:
                for (AbsBYDAutoSettingListener listener274 : this.mAbsBydAutoSettingListeners) {
                    listener274.onUcuMassageModeChanged(2, settingPacket.getUcuMassageModeDeputyR());
                }
                caseFeatureIds(1071, Integer.valueOf(settingPacket.getUcuMassageModeDeputyR()));
                break;
            case 557858192:
                for (AbsBYDAutoSettingListener listener275 : this.mAbsBydAutoSettingListeners) {
                    listener275.onUcuMassageLevelChanged(1, settingPacket.getUcuMassageLevelMainR());
                }
                caseFeatureIds(1067, Integer.valueOf(settingPacket.getUcuMassageLevelMainR()));
                break;
            case 557858193:
                for (AbsBYDAutoSettingListener listener276 : this.mAbsBydAutoSettingListeners) {
                    listener276.onUcuMassageLevelChanged(2, settingPacket.getUcuMassageLevelDeputyR());
                }
                caseFeatureIds(1070, Integer.valueOf(settingPacket.getUcuMassageLevelDeputyR()));
                break;
            case 557858194:
                for (AbsBYDAutoSettingListener listener277 : this.mAbsBydAutoSettingListeners) {
                    listener277.onBdMassageModeChanged(3, settingPacket.getBdMassageModeLeftRearR());
                }
                caseFeatureIds(1288, Integer.valueOf(settingPacket.getBdMassageModeLeftRearR()));
                break;
            case 557858195:
                for (AbsBYDAutoSettingListener listener278 : this.mAbsBydAutoSettingListeners) {
                    listener278.onBdMassageModeChanged(4, settingPacket.getBdMassageModeRightRearR());
                }
                caseFeatureIds(1299, Integer.valueOf(settingPacket.getBdMassageModeRightRearR()));
                break;
            case 557858196:
                for (AbsBYDAutoSettingListener listener279 : this.mAbsBydAutoSettingListeners) {
                    listener279.onBdMassageLevelChanged(3, settingPacket.getBdMassageLevelLeftRearR());
                }
                caseFeatureIds(1286, Integer.valueOf(settingPacket.getBdMassageLevelLeftRearR()));
                break;
            case 557858197:
                for (AbsBYDAutoSettingListener listener280 : this.mAbsBydAutoSettingListeners) {
                    listener280.onBdMassageLevelChanged(4, settingPacket.getBdMassageLevelRightRearR());
                }
                caseFeatureIds(1297, Integer.valueOf(settingPacket.getSecuMassageLevelRightRearR()));
                break;
            case 557858198:
                for (AbsBYDAutoSettingListener listener281 : this.mAbsBydAutoSettingListeners) {
                    listener281.onSecuMassageModeChanged(3, settingPacket.getSecuMassageModeLeftRearR());
                }
                caseFeatureIds(1288, Integer.valueOf(settingPacket.getSecuMassageModeLeftRearR()));
                break;
            case 557858199:
                for (AbsBYDAutoSettingListener listener282 : this.mAbsBydAutoSettingListeners) {
                    listener282.onSecuMassageModeChanged(4, settingPacket.getSecuMassageModeRightRearR());
                }
                caseFeatureIds(1299, Integer.valueOf(settingPacket.getSecuMassageModeRightRearR()));
                break;
            case 557858200:
                for (AbsBYDAutoSettingListener listener283 : this.mAbsBydAutoSettingListeners) {
                    listener283.onSecuMassageLevelChanged(3, settingPacket.getSecuMassageLevelLeftRearR());
                }
                caseFeatureIds(1286, Integer.valueOf(settingPacket.getSecuMassageLevelLeftRearR()));
                break;
            case 557858201:
                for (AbsBYDAutoSettingListener listener284 : this.mAbsBydAutoSettingListeners) {
                    listener284.onSecuMassageLevelChanged(4, settingPacket.getSecuMassageLevelRightRearR());
                }
                caseFeatureIds(1297, Integer.valueOf(settingPacket.getSecuMassageLevelRightRearR()));
                break;
            case 557858202:
                for (AbsBYDAutoSettingListener listener285 : this.mAbsBydAutoSettingListeners) {
                    listener285.onRrEcuMassageMode(settingPacket.getRrEcuMassageModeR());
                }
                break;
            case 557858203:
                for (AbsBYDAutoSettingListener listener286 : this.mAbsBydAutoSettingListeners) {
                    listener286.onRrEcuMassageLevel(settingPacket.getRrEcuMassageLevelR());
                }
                break;
            case 557858305:
                for (AbsBYDAutoSettingListener listener287 : this.mAbsBydAutoSettingListeners) {
                    listener287.onSafeBeltWarnStateChanged(settingPacket.getSafeBeltWarnState());
                }
                break;
            case 557858330:
                for (AbsBYDAutoSettingListener listener288 : this.mAbsBydAutoSettingListeners) {
                    listener288.onPABStateCodeChanged(settingPacket.getPabCodeR());
                }
                break;
            case 557858331:
                for (AbsBYDAutoSettingListener listener289 : this.mAbsBydAutoSettingListeners) {
                    listener289.onMissPackageCountChanged(settingPacket.getMissPackageCountR());
                }
                break;
            case 557858334:
                for (AbsBYDAutoSettingListener listener290 : this.mAbsBydAutoSettingListeners) {
                    listener290.onScreenAngleChanged(settingPacket.getScreenAngleR());
                }
                break;
            case 557858336:
                for (AbsBYDAutoSettingListener listener291 : this.mAbsBydAutoSettingListeners) {
                    listener291.onScreenRotationActionChanged(settingPacket.getScreenRotationActionR());
                }
                break;
            case 557858337:
                for (AbsBYDAutoSettingListener listener292 : this.mAbsBydAutoSettingListeners) {
                    listener292.onScreenAntiPinchStateChanged(settingPacket.getScreenAntiPinchStateR());
                }
                break;
            case 557858338:
                for (AbsBYDAutoSettingListener listener293 : this.mAbsBydAutoSettingListeners) {
                    listener293.onScreenAngleValueChanged(settingPacket.getScreenAngleValueR());
                }
                break;
            case 557858340:
                for (AbsBYDAutoSettingListener listener294 : this.mAbsBydAutoSettingListeners) {
                    listener294.onScreenRotationOverHotStateChanged(settingPacket.getScreenRotationOverHotStateR());
                }
                break;
            case 557858345:
                for (AbsBYDAutoSettingListener listener295 : this.mAbsBydAutoSettingListeners) {
                    listener295.onPABStateChanged(settingPacket.getPabStateR());
                }
                break;
            case 557858354:
                for (AbsBYDAutoSettingListener listener296 : this.mAbsBydAutoSettingListeners) {
                    listener296.onSomethingLeftInRefrigeratorNotice(settingPacket.getSomethingLeftInRefrigerator());
                }
                break;
            case 557858355:
                for (AbsBYDAutoSettingListener listener297 : this.mAbsBydAutoSettingListeners) {
                    listener297.on1LevelSeatHeatingStateChanged(1, settingPacket.getOneLevelSeatHeatingStateR());
                }
                break;
            case 557858357:
                for (AbsBYDAutoSettingListener listener298 : this.mAbsBydAutoSettingListeners) {
                    listener298.onTrailerModeStateChanged(settingPacket.getTrailerModeStateR());
                }
                break;
            case 557858358:
                for (AbsBYDAutoSettingListener listener299 : this.mAbsBydAutoSettingListeners) {
                    listener299.onTrailerLightCheckStateChanged(settingPacket.getTrailerLightCheckStateR());
                }
                break;
            case 557858360:
                for (AbsBYDAutoSettingListener listener300 : this.mAbsBydAutoSettingListeners) {
                    listener300.onRearViewEcuMirrorFlipStateChanged(settingPacket.getRearViewEcuMirrorFlipStateR());
                }
                break;
            case 557858361:
                for (AbsBYDAutoSettingListener listener301 : this.mAbsBydAutoSettingListeners) {
                    listener301.onDyHeightToggleStateChanged(settingPacket.getDyHeightToggleStateR());
                }
                break;
            case 557858362:
                for (AbsBYDAutoSettingListener listener302 : this.mAbsBydAutoSettingListeners) {
                    listener302.onWelcomeStateChanged(settingPacket.getWelcomeStateR());
                }
                break;
            case 557858363:
                for (AbsBYDAutoSettingListener listener303 : this.mAbsBydAutoSettingListeners) {
                    listener303.onWelcomeActiveChanged(settingPacket.getWelcomeActiveR());
                }
                break;
            case 557858364:
                for (AbsBYDAutoSettingListener listener304 : this.mAbsBydAutoSettingListeners) {
                    listener304.onTailBoxTakeStateChanged(settingPacket.getTailBoxTakeStateR());
                }
                break;
            case 557858365:
                for (AbsBYDAutoSettingListener listener305 : this.mAbsBydAutoSettingListeners) {
                    listener305.onCampintBlacneStateChanged(settingPacket.getCampingBlanceStateR());
                }
                break;
            case 557858366:
                for (AbsBYDAutoSettingListener listener306 : this.mAbsBydAutoSettingListeners) {
                    listener306.onCampingBlanceActionChanged(settingPacket.getCampingBlanceActionR());
                }
                break;
            case 557858367:
                for (AbsBYDAutoSettingListener listener307 : this.mAbsBydAutoSettingListeners) {
                    listener307.onLiftModeStateChanged(settingPacket.getLiftModeStateR());
                }
                break;
            case 557858368:
                for (AbsBYDAutoSettingListener listener308 : this.mAbsBydAutoSettingListeners) {
                    listener308.onFixedStateChanged(settingPacket.getFixedStateR());
                }
                break;
            case 557858369:
                for (AbsBYDAutoSettingListener listener309 : this.mAbsBydAutoSettingListeners) {
                    listener309.onSpeedSupportedTurn(settingPacket.getSpeedSupportedTurnR());
                }
                break;
            case 557858370:
                for (AbsBYDAutoSettingListener listener310 : this.mAbsBydAutoSettingListeners) {
                    listener310.onModeSupportedHturnChanged(settingPacket.getModeSupportedHturnR());
                }
                break;
            case 557858371:
                for (AbsBYDAutoSettingListener listener311 : this.mAbsBydAutoSettingListeners) {
                    listener311.onWelcomeSupportedChanged(settingPacket.getWelcomeSupportedR());
                }
                break;
            case 557858372:
                for (AbsBYDAutoSettingListener listener312 : this.mAbsBydAutoSettingListeners) {
                    listener312.onTailBoxTakeSupportedChanged(settingPacket.getTailBoxTakeSupportedR());
                }
                break;
            case 557858373:
                for (AbsBYDAutoSettingListener listener313 : this.mAbsBydAutoSettingListeners) {
                    listener313.onTrailerModeCodeChanged(settingPacket.getTrailerModeCodeR());
                }
                break;
            case 557858374:
                for (AbsBYDAutoSettingListener listener314 : this.mAbsBydAutoSettingListeners) {
                    listener314.onCVCModeChanged(settingPacket.getCvcModeR());
                }
                break;
            case 557858375:
                for (AbsBYDAutoSettingListener listener315 : this.mAbsBydAutoSettingListeners) {
                    listener315.onCampingBlanceSupportedChanged(settingPacket.getCampingBlanceSupportedR());
                }
                break;
            case 557858376:
                for (AbsBYDAutoSettingListener listener316 : this.mAbsBydAutoSettingListeners) {
                    listener316.onRearSeatGreetStateChanged(settingPacket.getRearSeatGreetStateR());
                }
                break;
            case 557858377:
                for (AbsBYDAutoSettingListener listener317 : this.mAbsBydAutoSettingListeners) {
                    listener317.onDyHeightTurnActionChanged(settingPacket.getDyHeightTurnActionR());
                }
                break;
            case 557858378:
                for (AbsBYDAutoSettingListener listener318 : this.mAbsBydAutoSettingListeners) {
                    listener318.onDampStateChanged(settingPacket.getDampStateR());
                }
                break;
            case 557858379:
                for (AbsBYDAutoSettingListener listener319 : this.mAbsBydAutoSettingListeners) {
                    listener319.onDyHeightLevelChanged(settingPacket.getDyHeightLevelR());
                }
                break;
            case 557858380:
                for (AbsBYDAutoSettingListener listener320 : this.mAbsBydAutoSettingListeners) {
                    listener320.onFdCampingBlanceActionChanged(settingPacket.getFdCrcCheckNumR());
                }
                break;
            case 557858381:
                for (AbsBYDAutoSettingListener listener321 : this.mAbsBydAutoSettingListeners) {
                    listener321.onFdCRCCheckNum(settingPacket.getOnFdCrcCheckNumR());
                }
                break;
            case 557858382:
                for (AbsBYDAutoSettingListener listener322 : this.mAbsBydAutoSettingListeners) {
                    listener322.onFdDyHeightTurnActionChanged(settingPacket.getFdDyHeightTurnActionR());
                }
                break;
            case 557858383:
                for (AbsBYDAutoSettingListener listener323 : this.mAbsBydAutoSettingListeners) {
                    listener323.onFdDynamicSuspensionMode(settingPacket.getFdDampStateR());
                }
                break;
            case 557858384:
                for (AbsBYDAutoSettingListener listener324 : this.mAbsBydAutoSettingListeners) {
                    listener324.onFdDyHeightLevelChanged(settingPacket.getFdDyHeightLevelR());
                }
                break;
            case 557858385:
                for (AbsBYDAutoSettingListener listener325 : this.mAbsBydAutoSettingListeners) {
                    listener325.onFdDyHeightToggleStateChanged(settingPacket.getFdDyHeightToggleStateR());
                }
                break;
            case 557858386:
                for (AbsBYDAutoSettingListener listener326 : this.mAbsBydAutoSettingListeners) {
                    listener326.onFdWelcomeStateChanged(settingPacket.getFdWelcomeStateR());
                }
                break;
            case 557858387:
                for (AbsBYDAutoSettingListener listener327 : this.mAbsBydAutoSettingListeners) {
                    listener327.onFdWelcomeActiveChanged(settingPacket.getFdWelcomeActiveR());
                }
                break;
            case 557858388:
                for (AbsBYDAutoSettingListener listener328 : this.mAbsBydAutoSettingListeners) {
                    listener328.onFdFrontBoxTakeStateChanged(settingPacket.getFdFrontBoxTakeStateR());
                }
                break;
            case 557858389:
                for (AbsBYDAutoSettingListener listener329 : this.mAbsBydAutoSettingListeners) {
                    listener329.onFdTailBoxTakeStateChanged(settingPacket.getFdTailBoxTakeStateR());
                }
                break;
            case 557858390:
                for (AbsBYDAutoSettingListener listener330 : this.mAbsBydAutoSettingListeners) {
                    listener330.onFdCampintBlacneStateChanged(settingPacket.getFdCampingBlanceStateR());
                }
                break;
            case 557858391:
                for (AbsBYDAutoSettingListener listener331 : this.mAbsBydAutoSettingListeners) {
                    listener331.onFdCampingBlanceActionChanged(settingPacket.getFdCampingBlanceActionR());
                }
                break;
            case 557858392:
                for (AbsBYDAutoSettingListener listener332 : this.mAbsBydAutoSettingListeners) {
                    listener332.onFdLiftModeStateChanged(settingPacket.getFdLiftModeStateR());
                }
                break;
            case 557858393:
                for (AbsBYDAutoSettingListener listener333 : this.mAbsBydAutoSettingListeners) {
                    listener333.onFdFixedStateChanged(settingPacket.getFdFixedStateR());
                }
                break;
            case 557858394:
                for (AbsBYDAutoSettingListener listener334 : this.mAbsBydAutoSettingListeners) {
                    listener334.onFdSpeedSupportedTurn(settingPacket.getFdSpeedSupportedTurnR());
                }
                break;
            case 557858395:
                for (AbsBYDAutoSettingListener listener335 : this.mAbsBydAutoSettingListeners) {
                    listener335.onFdModeSupportedHturnChanged(settingPacket.getFdModeSupportedHturnR());
                }
                break;
            case 557858396:
                for (AbsBYDAutoSettingListener listener336 : this.mAbsBydAutoSettingListeners) {
                    listener336.onFdWelcomeSupportedChanged(settingPacket.getFdWelcomeSupportedR());
                }
                break;
            case 557858397:
                for (AbsBYDAutoSettingListener listener337 : this.mAbsBydAutoSettingListeners) {
                    listener337.onFdTailBoxTakeSupportedChanged(settingPacket.getFdTailBoxTakeSupportedR());
                }
                break;
            case 557858398:
                for (AbsBYDAutoSettingListener listener338 : this.mAbsBydAutoSettingListeners) {
                    listener338.onFdTrailerModeCodeChanged(settingPacket.getFdTrailerModeCodeR());
                }
                break;
            case 557858399:
                for (AbsBYDAutoSettingListener listener339 : this.mAbsBydAutoSettingListeners) {
                    listener339.onFdCampingBlanceSupportedChanged(settingPacket.getFdCampingBlanceSupportedR());
                }
                break;
            case 557858411:
                for (AbsBYDAutoSettingListener listener340 : this.mAbsBydAutoSettingListeners) {
                    listener340.onCloseDoorLevelChanged(settingPacket.getCloseDoorLevelR());
                }
                break;
            case 557858412:
                for (AbsBYDAutoSettingListener listener341 : this.mAbsBydAutoSettingListeners) {
                    listener341.onIntelLockOpenDriverDoorChanged(settingPacket.getIntelLockOpenDriverDoorR());
                }
                break;
            case 557858413:
                for (AbsBYDAutoSettingListener listener342 : this.mAbsBydAutoSettingListeners) {
                    listener342.onBrakeCloseDoorChanged(settingPacket.getBrakeCloseDoorR());
                }
                break;
            case 557858414:
                for (AbsBYDAutoSettingListener listener343 : this.mAbsBydAutoSettingListeners) {
                    listener343.onNfcOpenDriverDoorChanged(settingPacket.getNfcOpenDriverDoorR());
                }
                break;
            case 557858415:
                for (AbsBYDAutoSettingListener listener344 : this.mAbsBydAutoSettingListeners) {
                    listener344.onBleOpenDriverDoorStateChanged(settingPacket.getBleOpenDriverDoorStateR());
                }
                break;
            case 557858416:
                for (AbsBYDAutoSettingListener listener345 : this.mAbsBydAutoSettingListeners) {
                    listener345.onSafeBeltCloseDriveDoorStateChanged(settingPacket.getSafeBeltCloseDriveDoorR());
                }
                break;
            case 557858418:
                for (AbsBYDAutoSettingListener listener346 : this.mAbsBydAutoSettingListeners) {
                    listener346.onVSPStateChanged(settingPacket.getVspStateR());
                }
                break;
            case 557858429:
                for (AbsBYDAutoSettingListener listener347 : this.mAbsBydAutoSettingListeners) {
                    listener347.onDriverSeatAutoReturnB3Changded(settingPacket.getDriverSeatAutoReturn());
                }
                break;
            case 557858441:
                for (AbsBYDAutoSettingListener listener348 : this.mAbsBydAutoSettingListeners) {
                    listener348.onLRSEMachineSwitchStateChanged(settingPacket.getLrseMachineSwitchStateR());
                }
                break;
            case 557858442:
                for (AbsBYDAutoSettingListener listener349 : this.mAbsBydAutoSettingListeners) {
                    listener349.onRRSEMachineSwitchStateChanged(settingPacket.getRrseMachineSwitchStateR());
                }
                break;
            case 557858449:
                for (AbsBYDAutoSettingListener listener350 : this.mAbsBydAutoSettingListeners) {
                    listener350.onLRSEChildModeTimeWriteFb(settingPacket.getLrseChildModeTimeWriteFbR());
                }
                break;
            case 557858450:
                for (AbsBYDAutoSettingListener listener351 : this.mAbsBydAutoSettingListeners) {
                    listener351.onRRSEChildModeTimeWriteFb(settingPacket.getRrseChildModeTimeWriteFbR());
                }
                break;
            case 557858459:
                for (AbsBYDAutoSettingListener listener352 : this.mAbsBydAutoSettingListeners) {
                    listener352.onLRSEChildModeCodeWriteFb(settingPacket.getLrseChildModeCodeWriteFbR());
                }
                break;
            case 557858460:
                for (AbsBYDAutoSettingListener listener353 : this.mAbsBydAutoSettingListeners) {
                    listener353.onRRSEChildModeCodeWriteFb(settingPacket.getRrseChildModeCodeWriteFbR());
                }
                break;
            case 557858461:
                for (AbsBYDAutoSettingListener listener354 : this.mAbsBydAutoSettingListeners) {
                    listener354.onDragModeForwardAndBackwardChanged(settingPacket.getDragModeForwardAndBackwardStateR());
                }
                break;
            case 557858462:
                for (AbsBYDAutoSettingListener listener355 : this.mAbsBydAutoSettingListeners) {
                    listener355.onDragAndDropModeTipsChanged(settingPacket.getDragAndDropModeTipsStateR());
                }
                break;
            case 557858527:
                for (AbsBYDAutoSettingListener listener356 : this.mAbsBydAutoSettingListeners) {
                    listener356.onMemorySetOperationFlagChanged(settingPacket.getMemorySetOperationFlagR());
                }
                caseFeatureIds(1220, Integer.valueOf(settingPacket.getMemorySetOperationFlagR()));
                break;
            case 557858528:
                for (AbsBYDAutoSettingListener listener357 : this.mAbsBydAutoSettingListeners) {
                    listener357.onFrontWiperSpeedValueChanged(settingPacket.getFrontWiperSpeedValueR());
                }
                caseFeatureIds(679, Integer.valueOf(settingPacket.getFrontWiperSpeedValueR()));
                break;
            case 557858529:
                for (AbsBYDAutoSettingListener listener358 : this.mAbsBydAutoSettingListeners) {
                    listener358.onTrailerModeMileageChanged(settingPacket.getTrailerModeMileageR());
                }
                break;
            case 557858530:
                for (AbsBYDAutoSettingListener listener359 : this.mAbsBydAutoSettingListeners) {
                    listener359.onRearWiperSpeedValueChanged(settingPacket.getRearWiperSpeedValueR());
                }
                break;
            case 557859101:
                for (AbsBYDAutoSettingListener listener360 : this.mAbsBydAutoSettingListeners) {
                    listener360.onHsMassageStateChanged(1, settingPacket.getMainHsMassageStateR());
                }
                break;
            case 557859103:
                for (AbsBYDAutoSettingListener listener361 : this.mAbsBydAutoSettingListeners) {
                    listener361.onHsMassageStateChanged(2, settingPacket.getHsMassageStatePassengerR());
                }
                break;
            case 557859104:
                for (AbsBYDAutoSettingListener listener362 : this.mAbsBydAutoSettingListeners) {
                    listener362.onHsMassageStateChanged(3, settingPacket.getHsMassageStateBackLeftR());
                }
                break;
            case 557859105:
                for (AbsBYDAutoSettingListener listener363 : this.mAbsBydAutoSettingListeners) {
                    listener363.onHsMassageStateChanged(4, settingPacket.getHsMassageStateBackRightR());
                }
                break;
            case 557860611:
                int musicState = settingPacket.getAmbientMusicModeStateR();
                int musicId = settingPacket.getAreaId();
                for (AbsBYDAutoSettingListener listener364 : this.mAbsBydAutoSettingListeners) {
                    listener364.onAmbientMusicModeState(musicId, musicState);
                    Log.v(TAG, " onAmbientMusicModeState in musicState:" + musicState + ",musicId:" + musicId + " listener hashCode: " + System.identityHashCode(listener364));
                }
                caseFeatureIds(1255, Integer.valueOf(musicState));
                break;
            case 557860631:
                int feedBackR = settingPacket.getAmbientFeedBack();
                for (AbsBYDAutoSettingListener listener365 : this.mAbsBydAutoSettingListeners) {
                    listener365.onAmbientFeedBackChanged(feedBackR);
                }
                caseFeatureIds(902, Integer.valueOf(feedBackR));
                break;
            case 557862926:
                for (AbsBYDAutoSettingListener listener366 : this.mAbsBydAutoSettingListeners) {
                    listener366.onScreenVoltageStateChanged(settingPacket.getScreenVoltageStateR());
                }
                break;
            case 557867008:
                for (AbsBYDAutoSettingListener listener367 : this.mAbsBydAutoSettingListeners) {
                    listener367.onSwsMediumRelatedKeys(settingPacket.getSwsMediumRelatedKeysS());
                }
                break;
            case 557875283:
                for (AbsBYDAutoSettingListener listener368 : this.mAbsBydAutoSettingListeners) {
                    listener368.onScreenRotationStatus(settingPacket.getScreenRotationStatusR());
                }
                break;
            case 559954810:
                for (AbsBYDAutoSettingListener listener369 : this.mAbsBydAutoSettingListeners) {
                    listener369.onTurnSteeringWheelChanged(3, settingPacket.getSteeringWheelY(), settingPacket.getSteeringWheelZ());
                }
                break;
            case 559954811:
                for (AbsBYDAutoSettingListener listener370 : this.mAbsBydAutoSettingListeners) {
                    listener370.onTurnSteeringWheelChanged(1, settingPacket.getSteeringWheelY(), settingPacket.getSteeringWheelZ());
                }
                break;
            case 559954812:
                for (AbsBYDAutoSettingListener listener371 : this.mAbsBydAutoSettingListeners) {
                    listener371.onTurnRearviewMirrorChanged(3, settingPacket.getRearviewMirrorXLeft(), settingPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954813:
                for (AbsBYDAutoSettingListener listener372 : this.mAbsBydAutoSettingListeners) {
                    listener372.onTurnRearviewMirrorChanged(7, settingPacket.getRearviewMirrorXRight(), settingPacket.getRearviewMirrorYRight());
                }
                break;
            case 559954814:
                for (AbsBYDAutoSettingListener listener373 : this.mAbsBydAutoSettingListeners) {
                    listener373.onTurnRearviewMirrorChanged(1, settingPacket.getRearviewMirrorXLeft(), settingPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954815:
                for (AbsBYDAutoSettingListener listener374 : this.mAbsBydAutoSettingListeners) {
                    listener374.onTurnRearviewMirrorChanged(6, settingPacket.getRearviewMirrorXRight(), settingPacket.getRearviewMirrorYRight());
                }
                break;
            case 559954820:
                for (AbsBYDAutoSettingListener listener375 : this.mAbsBydAutoSettingListeners) {
                    listener375.onTurnSeatHeightChanged(1, 1, settingPacket.getSeatTurnHeightDriver());
                }
                break;
            case 559954821:
                for (AbsBYDAutoSettingListener listener376 : this.mAbsBydAutoSettingListeners) {
                    listener376.onTurnSeatHeightChanged(2, 1, settingPacket.getSeatTurnHeightCopilot());
                }
                break;
            case 559954822:
                for (AbsBYDAutoSettingListener listener377 : this.mAbsBydAutoSettingListeners) {
                    listener377.onTurnSeatHeightChanged(3, 1, settingPacket.getSeatTurnHeightBackLeft());
                }
                break;
            case 559954823:
                for (AbsBYDAutoSettingListener listener378 : this.mAbsBydAutoSettingListeners) {
                    listener378.onTurnSeatHeightChanged(4, 1, settingPacket.getSeatTurnHeightBackRight());
                }
                break;
            case 559954824:
                for (AbsBYDAutoSettingListener listener379 : this.mAbsBydAutoSettingListeners) {
                    listener379.onTurnSeatbackrestChanged(1, 1, settingPacket.getSeatbackrestPostionDriver());
                }
                break;
            case 559954825:
                for (AbsBYDAutoSettingListener listener380 : this.mAbsBydAutoSettingListeners) {
                    listener380.onTurnSeatbackrestChanged(2, 1, settingPacket.getSeatbackrestPostionCopilot());
                }
                break;
            case 559954826:
                for (AbsBYDAutoSettingListener listener381 : this.mAbsBydAutoSettingListeners) {
                    listener381.onTurnSeatbackrestChanged(3, 1, settingPacket.getSeatbackrestPostionBackLeft());
                }
                break;
            case 559954827:
                for (AbsBYDAutoSettingListener listener382 : this.mAbsBydAutoSettingListeners) {
                    listener382.onTurnSeatbackrestChanged(4, 1, settingPacket.getSeatbackrestPostionBackRight());
                }
                break;
            case 559954832:
                for (AbsBYDAutoSettingListener listener383 : this.mAbsBydAutoSettingListeners) {
                    listener383.onTurnSeatCushionChanged(1, 1, settingPacket.getTurnCushionHeightDriver());
                }
                break;
            case 559954833:
                for (AbsBYDAutoSettingListener listener384 : this.mAbsBydAutoSettingListeners) {
                    listener384.onTurnSeatCushionChanged(2, 1, settingPacket.getTurnCushionHeightCopilot());
                }
                break;
            case 559954834:
                for (AbsBYDAutoSettingListener listener385 : this.mAbsBydAutoSettingListeners) {
                    listener385.onTurnSeatCushionChanged(3, 1, settingPacket.getTurnCushionHeightBackLeft());
                }
                break;
            case 559954835:
                for (AbsBYDAutoSettingListener listener386 : this.mAbsBydAutoSettingListeners) {
                    listener386.onTurnSeatCushionChanged(4, 1, settingPacket.getTurnCushionHeightBackRight());
                }
                break;
            case 559954836:
                for (AbsBYDAutoSettingListener listener387 : this.mAbsBydAutoSettingListeners) {
                    listener387.onTurnSeatCaliperSplintChanged(1, 1, settingPacket.getTurnCaliperSplintHeightDriver());
                }
                break;
            case 559954837:
                for (AbsBYDAutoSettingListener listener388 : this.mAbsBydAutoSettingListeners) {
                    listener388.onTurnSeatCaliperSplintChanged(2, 1, settingPacket.getTurnCaliperSplintHeightCopilot());
                }
                break;
            case 559954838:
                for (AbsBYDAutoSettingListener listener389 : this.mAbsBydAutoSettingListeners) {
                    listener389.onTurnSeatCaliperSplintChanged(3, 1, settingPacket.getTurnCaliperSplintHeightBackLeft());
                }
                break;
            case 559954839:
                for (AbsBYDAutoSettingListener listener390 : this.mAbsBydAutoSettingListeners) {
                    listener390.onTurnSeatCaliperSplintChanged(4, 1, settingPacket.getTurnCaliperSplintHeightBackRight());
                }
                break;
            case 559954844:
                for (AbsBYDAutoSettingListener listener391 : this.mAbsBydAutoSettingListeners) {
                    listener391.onHeadrestTurnHeightChanged(1, 1, settingPacket.getHeadrestTurnHeightDriver());
                }
                break;
            case 559954845:
                for (AbsBYDAutoSettingListener listener392 : this.mAbsBydAutoSettingListeners) {
                    listener392.onHeadrestTurnHeightChanged(2, 2, settingPacket.getHeadrestTurnHeightCopilot());
                }
                break;
            case 559955504:
                for (AbsBYDAutoSettingListener listener393 : this.mAbsBydAutoSettingListeners) {
                    listener393.onHeadrestTurnHeightChanged(3, 1, settingPacket.getHeadrestTurnHeightBackLeft());
                }
                break;
            case 559955505:
                for (AbsBYDAutoSettingListener listener394 : this.mAbsBydAutoSettingListeners) {
                    listener394.onHeadrestTurnHeightChanged(4, 2, settingPacket.getHeadrestTurnHeightBackRight());
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

    public int setACBTWind(int value) {
        Log.i(TAG, "setACBTWind is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACBTWIND, Integer.valueOf(value));
            Log.v(TAG, "setACBTWind mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setACBTWindS4E3(value);
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

    public int setACTunnelCycle(int value) {
        Log.i(TAG, "setACTunnelCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACTUNNELCYCLE, Integer.valueOf(value));
            Log.v(TAG, "setACTunnelCycle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setACTunnelCycle(value);
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

    public int setACPauseCycle(int value) {
        Log.i(TAG, "setACPauseCycle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACPAUSECYCLE, Integer.valueOf(value));
            Log.v(TAG, "setACPauseCycle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setACPauseCycle(value);
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

    public int setACAutoAir(int value) {
        Log.i(TAG, "setACAutoAir is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACAUTOAIR, Integer.valueOf(value));
            Log.v(TAG, "setACAutoAir mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setACAutoAir(value);
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

    public int setPM25Power(int value) {
        Log.i(TAG, "setPM25Power is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETPM25POWER, Integer.valueOf(value));
            Log.v(TAG, "setPM25Power mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPM25Power(value);
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

    public int setPM25SwitchCheck(int value) {
        Log.i(TAG, "setPM25SwitchCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETPM25SWITCHCHECK, Integer.valueOf(value));
            Log.v(TAG, "setPM25SwitchCheck mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPM25SwitchCheck(value);
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

    public int setPM25TimeCheck(int value) {
        Log.i(TAG, "setPM25TimeCheck is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETPM25TIMECHECK, Integer.valueOf(value));
            Log.v(TAG, "setPM25TimeCheck mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPM25TimeCheck(value);
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

    public int setEnergyFeedback(int value) {
        Log.i(TAG, "setEnergyFeedback is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETENERGYFEEDBACK, Integer.valueOf(value));
            Log.v(TAG, "setEnergyFeedback mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setEnergyFeedback(value);
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

    public int setSOCTarget(int value) {
        Log.i(TAG, "setSOCTarget is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETSOCTARGET, Integer.valueOf(value));
            Log.v(TAG, "setSOCTarget mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSOCTarget(value);
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

    public int setChargingPort(int value) {
        Log.i(TAG, "setChargingPort is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETCHARGINGPORT, Integer.valueOf(value));
            Log.v(TAG, "setChargingPort mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setChargingPort(value);
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

    public int setAutoExternalRearMirrorFollowUpSwitch(int value) {
        Log.i(TAG, "setAutoExternalRearMirrorFollowUpSwitch is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAUTOEXTERNALREARMIRRORFOLLOWUPSWITCH, Integer.valueOf(value));
            Log.v(TAG, "setAutoExternalRearMirrorFollowUpSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoExternalRearMirrorFollowUpSwitch(value);
    }

    public int setRearSeatsFoldOrExpand(int value) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setRearSeatsFoldOrExpand(value);
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

    public int getPassengerState(int location) {
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
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStatePassenger();
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateBackLeft();
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateBackRight();
        }
        if (location == 5) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateBackMid();
        }
        if (location == 6) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateFinalBackLeft();
        }
        if (location == 7) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateFinalBackRight();
        }
        if (location == 8) {
            return this.mTsManagerImpl.getSettingAdapterManager().getPassengerStateFinalBackMid();
        }
        return 0;
    }

    public int setLockOff(int value) {
        Log.i(TAG, "setLockOff is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLockOff", Integer.valueOf(value));
            Log.v(TAG, "setLockOff mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLockOff(value);
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

    public int setOverspeedLock(int value) {
        Log.i(TAG, "setOverspeedLock is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETOVERSPEEDLOCK, Integer.valueOf(value));
            Log.v(TAG, "setOverspeedLock mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOverspeedLock(value);
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

    public int setSteerAssis(int value) {
        Log.i(TAG, "setSteerAssis is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETSTEERASSIS, Integer.valueOf(value));
            Log.v(TAG, "setSteerAssis mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteerAssis(value);
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

    public int setRearViewMirrorFlip(int value) {
        Log.i(TAG, "setRearViewMirrorFlip is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREARVIEWMIRRORFLIP, Integer.valueOf(value));
            Log.v(TAG, "setRearViewMirrorFlip mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearViewMirrorFlip(value);
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

    public int setDriverSeatAutoReturn(int value) {
        Log.i(TAG, "setDriverSeatAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETDRIVERSEATAUTORETURN, Integer.valueOf(value));
            Log.v(TAG, "setDriverSeatAutoReturn mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDriverSeatAutoReturn(value);
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

    public int setSteerPositionAutoReturn(int value) {
        Log.i(TAG, "setSteerPositionAutoReturn is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETSTEERPOSITIONAUTORETURN, Integer.valueOf(value));
            Log.v(TAG, "setSteerPositionAutoReturn mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteerPositionAutoReturn(value);
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

    public int setRemoteControlUpwindowState(int value) {
        Log.i(TAG, "setRemoteControlUpwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRemoteControlUpwindowState", Integer.valueOf(value));
            Log.v(TAG, "setRemoteControlUpwindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlUpwindowState(value);
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

    public int setRemoteControlDownwindowState(int value) {
        Log.i(TAG, "setRemoteControlDownwindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRemoteControlDownwindowState", Integer.valueOf(value));
            Log.v(TAG, "setRemoteControlDownwindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlDownwindowState(value);
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

    public int setLockCarRiseWindow(int value) {
        Log.i(TAG, "setLockCarRiseWindow is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETLOCKCARRISEWINDOW, Integer.valueOf(value));
            Log.v(TAG, "setLockCarRiseWindow mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLockCarRiseWindow(value);
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

    public int setMicroSwitchLockWindowState(int value) {
        Log.i(TAG, "setMicroSwitchLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETMICROSWITCHLOCKWINDOWSTATE, Integer.valueOf(value));
            Log.v(TAG, "setMicroSwitchLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMicroSwitchLockWindowState(value);
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

    public int setMicroSwitchUnlockWindowState(int value) {
        Log.i(TAG, "setMicroSwitchUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETMICROSWITCHUNLOCKWINDOWSTATE, Integer.valueOf(value));
            Log.v(TAG, "setMicroSwitchUnlockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMicroSwitchUnlockWindowState(value);
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

    public int setBackHomeLightDelayValue(int value) {
        Log.i(TAG, "setBackHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setBackHomeLightDelayValue", Integer.valueOf(value));
            Log.v(TAG, "setBackHomeLightDelayValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBackHomeLightDelayValue(value);
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

    public int setLeftHomeLightDelay(int value) {
        Log.i(TAG, "setLeftHomeLightDelay is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLeftHomeLightDelay", Integer.valueOf(value));
            Log.v(TAG, "setLeftHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeftHomeLightDelay(value);
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

    public int setBackDoorElectricMode(int value) {
        Log.i(TAG, "setBackDoorElectricMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETBACKDOORELECTRICMODE, Integer.valueOf(value));
            Log.v(TAG, "setBackDoorElectricMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBackDoorElectricMode(value);
    }

    public void registerListener(AbsBYDAutoSettingListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoSettingListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe setting listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (this.mForTest == 0) {
            this.mTestCallback.setAbsBYDAutoStatisticListener(listener);
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (!this.mAbsBydAutoSettingListeners.contains(listener)) {
            this.mAbsBydAutoSettingListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerSettingCallback();
    }

    public void unregisterListener(IBYDAutoListener listener) {
    }

    public void unregisterListener(AbsBYDAutoSettingListener listener) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.setAbsBYDAutoStatisticListener(null);
            Log.d(TAG, "unregisterListener mForTestValue is 0 execute return");
            return;
        }
        Log.d(TAG, "unsubscribeSettingCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoSettingListeners.remove(listener);
        unregisterConnectListener(listener);
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

    public int setAutoPurification(int state) {
        Log.i(TAG, "setAutoPurification is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAUTOPURIFICATION, Integer.valueOf(state));
            Log.v(TAG, "setAutoPurification mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoPurification(state);
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

    public int setRemoteKeyStartAcTime(int value) {
        Log.i(TAG, "setRemoteKeyStartAcTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREMOTEKEYSTARTACTIME, Integer.valueOf(value));
            Log.v(TAG, "setRemoteKeyStartAcTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteKeyStartAcTime(value);
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

    public int setAcTemperatureUnit(int value) {
        Log.i(TAG, "setAcTemperatureUnit is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACTEMPERATUREUNIT, Integer.valueOf(value));
            Log.v(TAG, "setAcTemperatureUnit mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAcTemperatureUnit(value);
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

    public int setAcWarmMode(int mode) {
        Log.i(TAG, "setAcWarmMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETACWARMMODE, Integer.valueOf(mode));
            Log.v(TAG, "setAcWarmMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAcWarmMode(mode);
    }

    public int setIntelligentAcAreaState(int value) {
        Log.i(TAG, "setIntelligentAcAreaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETINTELLIGENTACAREASTATE, Integer.valueOf(value));
            Log.v(TAG, "setIntelligentAcAreaState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentAcAreaState(value);
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

    public int setBackDoorOpenedHeight(int value) {
        Log.i(TAG, "setBackDoorOpenedHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETBACKDOOROPENEDHEIGHT, Integer.valueOf(value));
            Log.v(TAG, "setBackDoorOpenedHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBackDoorOpenedHeight(value);
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
    public int setRemoteControlLockWindowState(int value) {
        Log.i(TAG, "setRemoteControlLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREMOTECONTROLLOCKWINDOWSTATE, Integer.valueOf(value));
            Log.v(TAG, "setRemoteControlLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlLockWindowState(value);
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
    public int setRemoteControlUnlockWindowState(int value) {
        Log.i(TAG, "setRemoteControlUnlockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREMOTECONTROLUNLOCKWINDOWSTATE, Integer.valueOf(value));
            Log.v(TAG, "setRemoteControlUnlockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemoteControlUnlockWindowState(value);
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

    public int setRainAutoLockWindowState(int value) {
        Log.i(TAG, "setRainAutoLockWindowState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETRAINAUTOLOCKWINDOWSTATE, Integer.valueOf(value));
            Log.v(TAG, "setRainAutoLockWindowState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRainAutoLockWindowState(value);
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

    public int setDoorknobAutoShrinkState(int value) {
        Log.i(TAG, "setDoorknobAutoShrinkState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETDOORKNOBAUTOSHRINKSTATE, Integer.valueOf(value));
            Log.v(TAG, "setDoorknobAutoShrinkState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDoorknobAutoShrinkState(value);
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

    public int setIntelligentUnlockState(int value) {
        Log.i(TAG, "setIntelligentUnlockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIntelligentUnlockState", Integer.valueOf(value));
            Log.v(TAG, "setIntelligentUnlockState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentUnlockState(value);
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

    public int setIntelligentLockState(int value) {
        Log.i(TAG, "setIntelligentLockState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIntelligentLockState", Integer.valueOf(value));
            Log.v(TAG, "setIntelligentLockState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIntelligentLockState(value);
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

    public int setHudState(int value) {
        Log.i(TAG, "setHudState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDSTATE, Integer.valueOf(value));
            Log.v(TAG, "setHudState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudState(value);
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

    public int setHudHeight(int value) {
        Log.i(TAG, "setHudHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDHEIGHT, Integer.valueOf(value));
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.ONHUDRESTARTANDEFFECT, Integer.valueOf(value));
            Log.v(TAG, "setHudHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudHeight(value);
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

    public int setHudBrightness(int value) {
        Log.i(TAG, "setHudBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDBRIGHTNESS, Integer.valueOf(value));
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.ONHUDRESTARTANDEFFECT, Integer.valueOf(value));
            Log.v(TAG, "setHudBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudBrightness(value);
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

    public int setHudRotationAngle(int value) {
        Log.i(TAG, "setHudRotationAngle is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDROTATIONANGLE, Integer.valueOf(value));
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.ONHUDRESTARTANDEFFECT, Integer.valueOf(value));
            Log.v(TAG, "setHudRotationAngle mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudRotationAngle(value);
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

    public int setHudMode(int value) {
        Log.i(TAG, "setHudMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDMODE, Integer.valueOf(value));
            Log.v(TAG, "setHudMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudMode(value);
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

    public int getHudDisplayContent(int setSource) {
        Log.i(TAG, "getHudDisplayContent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHudDisplayContent", Integer.valueOf(setSource));
            Log.v(TAG, "getHudDisplayContent mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (setSource == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudDisplayContentDriver();
        }
        if (setSource == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHudDisplayContentNavigation();
        }
        return 0;
    }

    public int setHudDisplayContent(int setSource, int value) {
        Log.i(TAG, "setHudDisplayContent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETHUDDISPLAYCONTENT, Integer.valueOf(setSource), Integer.valueOf(value));
            Log.v(TAG, "setHudDisplayContent mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (setSource == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudDisplayContentDriver(value);
        }
        if (setSource == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHudDisplayContentNavigation(value);
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

    public int setEngineSoundType(int value) {
        Log.i(TAG, "setEngineSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETENGINESOUNDTYPE, Integer.valueOf(value));
            Log.v(TAG, "setEngineSoundType mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setEngineSoundType(value);
    }

    public int setCarTipsSoundType(int value) {
        Log.i(TAG, "setCarTipsSoundType is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETCARTIPSSOUNDTYPE, Integer.valueOf(value));
            Log.v(TAG, "setCarTipsSoundType mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCarTipsSoundType(value);
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

    public int setReportVolume(int value) {
        Log.i(TAG, "setReportVolume is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETGETREPORTVOLUME, Integer.valueOf(value));
            Log.v(TAG, "setReportVolume mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setReportVolume(value);
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

    public int setSpeedReminderValue(int value) {
        Log.i(TAG, "setSpeedReminderValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETSPEEDREMINDERVALUE, Integer.valueOf(value));
            Log.v(TAG, "setSpeedReminderValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSpeedReminderValue(value);
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

    public int setAppUseLimitState(int value) {
        Log.i(TAG, "setAppUseLimitState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAPPUSELIMITSTATE, Integer.valueOf(value));
            Log.v(TAG, "setAppUseLimitState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAppUseLimitState(value);
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

    public int setNavigationMediaState(int value) {
        Log.i(TAG, "setNavigationMediaState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETNAVIGATIONMEDIASTATE, Integer.valueOf(value));
            Log.v(TAG, "setNavigationMediaState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setNavigationMediaState(value);
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

    public int setAutoPowerOnState(int value) {
        Log.i(TAG, "setAutoPowerOnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAUTOPOWERONSTATE, Integer.valueOf(value));
            Log.v(TAG, "setAutoPowerOnState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoPowerOnState(value);
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

    public int setAutoPowerOffState(int value) {
        Log.i(TAG, "setAutoPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setAutoPowerOffState", Integer.valueOf(value));
            Log.v(TAG, "setAutoPowerOffState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoPowerOffState(value);
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
        int value = this.mTsManagerImpl.getSettingAdapterManager().getBrakePedalMode();
        if (value == 0) {
            return 2;
        }
        if (value == 1) {
            return 1;
        }
        return value;
    }

    public int setBrakePedalMode(int value) {
        Log.i(TAG, "setBrakePedalMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETBRAKEPEDALMODE, Integer.valueOf(value));
            Log.v(TAG, "setBrakePedalMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBrakePedalMode(value);
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

    public int setComfortableParkingState(int value) {
        Log.i(TAG, "setComfortableParkingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETCOMFORTABLEPARKINGSTATE, Integer.valueOf(value));
            Log.v(TAG, "setComfortableParkingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setComfortableParkingState(value);
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

    public int setMotorPretensionerSeatbeltState(int value) {
        Log.i(TAG, "setMotorPretensionerSeatbeltState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETMOTORPRETENSIONERSEATBELTSTATE, Integer.valueOf(value));
            Log.v(TAG, "setMotorPretensionerSeatbeltState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (value == 1) {
            value = 2;
        } else if (value == 2) {
            value = 1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMotorPretensionerSeatbeltState(value);
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

    public int setAutoRainWiperState(int value) {
        Log.i(TAG, "setAutoRainWiperState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETAUTORAINWIPERSTATE, Integer.valueOf(value));
            Log.v(TAG, "setAutoRainWiperState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutoRainWiperState(value);
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

    public int setDynamicSuspensionMode(int value) {
        Log.i(TAG, "setDynamicSuspensionMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETDYNAMICSUSPENSIONMODE, Integer.valueOf(value));
            Log.v(TAG, "setDynamicSuspensionMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDynamicSuspensionMode(value);
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

    public int setLandAdvanceAtState(int state) {
        Log.i(TAG, "setLandAdvanceAtState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLandAdvanceAtState", Integer.valueOf(state));
            Log.v(TAG, "setLandAdvanceAtState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLandAdvanceAtState(state);
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

    public void resetSteeringWheelPosition(int postion) {
        Log.i(TAG, "resetSteeringWheelPosition is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetSteeringWheelPosition", Integer.valueOf(postion));
            Log.v(TAG, "resetSteeringWheelPosition mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetSteeringWheelPosition(postion);
        }
    }

    public float getRearviewMirrorY(int orientation) {
        Log.i(TAG, "getRearviewMirrorY is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRearviewMirrorY", Integer.valueOf(orientation));
            Log.v(TAG, "getRearviewMirrorY mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (orientation == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorYLeft();
        }
        if (orientation == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorYRight();
        }
        return 0.0f;
    }

    public float getRearviewMirrorX(int orientation) {
        Log.i(TAG, "getRearviewMirrorX is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getRearviewMirrorX", Integer.valueOf(orientation));
            Log.v(TAG, "getRearviewMirrorX mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (orientation == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorXLeft();
        }
        if (orientation == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getRearviewMirrorXRight();
        }
        return 0.0f;
    }

    public void resetRearviewMirrorParams(int position, int orientation) {
        Log.i(TAG, "resetRearviewMirrorParams is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetRearviewMirrorParams", Integer.valueOf(position), Integer.valueOf(orientation));
            Log.v(TAG, "resetRearviewMirrorParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetRearviewMirrorParams(position, orientation);
        }
    }

    public int getSeatHorization(int location) {
        Log.i(TAG, "getSeatHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSeatHorization", Integer.valueOf(location));
            Log.v(TAG, "getSeatHorization mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (location == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHorizationDriver();
        }
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHorizationPassenger();
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHorizationLeftArea();
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHorizationRightArea();
        }
        return 0;
    }

    public float getSeatTurnHeight(int location) {
        Log.i(TAG, "getSeatTurnHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSeatTurnHeight", Integer.valueOf(location));
            Log.v(TAG, "getSeatTurnHeight mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (location == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatTurnHeightDriver();
        }
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatTurnHeightPassenger();
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatTurnHeightLeftArea();
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatTurnHeightRightArea();
        }
        return 0.0f;
    }

    public float getSeatbackrestPostion(int location) {
        Log.i(TAG, "getSeatbackrestPostion is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getSeatbackrestPostion", Integer.valueOf(location));
            Log.v(TAG, "getSeatbackrestPostion mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (location == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatbackrestPostionDriver();
        }
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatbackrestPostionPassenger();
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatbackrestPostionLeftArea();
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatbackrestPostionRightArea();
        }
        return 0.0f;
    }

    public void resetSeatParams(int postion, int location) {
        Log.i(TAG, "resetSeatParams is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetSeatParams", Integer.valueOf(postion), Integer.valueOf(location));
            Log.v(TAG, "resetSeatParams mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().resetSeatParamsDriver(postion);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().resetSeatParamsPassenger(postion);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().resetSeatParamsLeftArea(postion);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().resetSeatParamsRightArea(postion);
        }
    }

    public int getCushionSupport(int position) {
        Log.i(TAG, "getCushionSupport is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getCushionSupport", Integer.valueOf(position));
            Log.v(TAG, "getCushionSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCushionSupport(position);
    }

    public float getTurnCushionHeight(int location) {
        Log.i(TAG, "getTurnCushionHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getTurnCushionHeight", Integer.valueOf(location));
            Log.v(TAG, "getTurnCushionHeight mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (location == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCushionHeightDriver();
        }
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCushionHeightPassenger();
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCushionHeightLeftArea();
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCushionHeightRightArea();
        }
        return 0.0f;
    }

    public void resetCushionHeight(int postion, int location) {
        Log.i(TAG, "resetCushionHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetCushionHeight", Integer.valueOf(postion), Integer.valueOf(location));
            Log.v(TAG, "resetCushionHeight mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetCushionHeight(postion, location);
        }
    }

    public float getTurnCaliperSplintHeight(int location) {
        Log.i(TAG, "getTurnCaliperSplintHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getTurnCaliperSplintHeight", Integer.valueOf(location));
            Log.v(TAG, "getTurnCaliperSplintHeight mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (location == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCaliperSplintHeightDriver();
        }
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCaliperSplintHeightPassenger();
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCaliperSplintHeightLeftArea();
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getTurnCaliperSplintHeightRightArea();
        }
        return 0.0f;
    }

    public void resetCaliperSplintHeight(int postion, int location) {
        Log.i(TAG, "resetCaliperSplintHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetCaliperSplintHeight", Integer.valueOf(postion), Integer.valueOf(location));
            Log.v(TAG, "resetCaliperSplintHeight mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
            }
            this.mTsManagerImpl.getSettingAdapterManager().resetCaliperSplintHeight(postion, location);
        }
    }

    public int getHeadrestHorization(int location) {
        Log.i(TAG, "getHeadrestHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHeadrestHorization", Integer.valueOf(location));
            Log.v(TAG, "getHeadrestHorization mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (location == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestHorizationDriver();
        }
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestHorizationPassenger();
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestHorizationLeftArea();
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestHorizationRightArea();
        }
        return 0;
    }

    public float getHeadrestTurnHeight(int location) {
        Log.i(TAG, "getHeadrestTurnHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getHeadrestTurnHeight", Integer.valueOf(location));
            Log.v(TAG, "getHeadrestTurnHeight mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (location == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestTurnHeightDriver();
        }
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestTurnHeightPassenger();
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestTurnHeightLeftArea();
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHeadrestTurnHeightRightArea();
        }
        return 0.0f;
    }

    public int resetHeadrestParms(int position, int location) {
        Log.i(TAG, "resetHeadrestParms is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetHeadrestParms", Integer.valueOf(position), Integer.valueOf(location));
            Log.v(TAG, "resetHeadrestParms mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetHeadrestParms(position, location);
    }

    public int setRefrigeratorMode(int state) {
        Log.i(TAG, "setRefrigeratorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREFRIGERATORMODE, Integer.valueOf(state));
            Log.v(TAG, "setRefrigeratorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (state == 0) {
            state = 3;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRefrigeratorMode(state);
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

    public int setRemenberPowerOffState(int state) {
        Log.i(TAG, "setRemenberPowerOffState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETREMENBERPOWEROFFSTATE, Integer.valueOf(state));
            Log.v(TAG, "setRemenberPowerOffState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemenberPowerOffState(state);
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

    public int setDisinfState(int value) {
        Log.i(TAG, "setDisinfState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETDISINFSTATE, Integer.valueOf(value));
            Log.v(TAG, "setDisinfState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDisinfState(value);
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

    public int setTrayState(int state) {
        Log.i(TAG, "setTrayState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETTRAYSTATE, Integer.valueOf(state));
            Log.v(TAG, "setTrayState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTrayState(state);
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
        int value = this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorMode();
        if (value == 3) {
            return 0;
        }
        return value;
    }

    public int getModelSupport(int model) {
        Log.i(TAG, "getModelSupport is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getModelSupport", Integer.valueOf(model));
            Log.v(TAG, "getModelSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getModelSupport(model);
    }

    public int hasFeature(String feature) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().hasFeature(feature);
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

    public void turnHeadrestTurnHeight(int location, int orientation) {
        Log.i(TAG, "turnHeadrestTurnHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnHeadrestTurnHeight", Integer.valueOf(location), Integer.valueOf(orientation));
            Log.v(TAG, "turnHeadrestTurnHeight mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHeightDriver(orientation);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHeightPassenger(orientation);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHeightLeftArea(orientation);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHeightRightArea(orientation);
        }
    }

    public void turnHeadrestTurnHorization(int location, int orientation) {
        Log.i(TAG, "turnHeadrestTurnHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnHeadrestTurnHorization", Integer.valueOf(location), Integer.valueOf(orientation));
            Log.v(TAG, "turnHeadrestTurnHorization mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHorizationDriver(orientation);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHorizationPassenger(orientation);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHorizationLeftArea(orientation);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestTurnHorizationRightArea(orientation);
        }
    }

    public void turnSeatCaliperSplint(int location, int orientation) {
        Log.i(TAG, "turnSeatCaliperSplint is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatCaliperSplint", Integer.valueOf(location), Integer.valueOf(orientation));
            Log.v(TAG, "turnSeatCaliperSplint mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintDriver(orientation);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintPassenger(orientation);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintLeftArea(orientation);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRightArea(orientation);
        }
    }

    public void turnSeatCushion(int location, int orientation) {
        Log.i(TAG, "turnSeatCushion is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatCushion", Integer.valueOf(location), Integer.valueOf(orientation));
            Log.v(TAG, "turnSeatCushion mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionDriver(orientation);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionPassenger(orientation);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionLeftArea(orientation);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRightArea(orientation);
        }
    }

    public void turnSeatbackrest(int location, int orientation) {
        Log.i(TAG, "turnSeatbackrest is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatbackrest", Integer.valueOf(location), Integer.valueOf(orientation));
            Log.v(TAG, "turnSeatbackrest mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestDriver(orientation);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPassenger(orientation);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestLeftArea(orientation);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestRightArea(orientation);
        }
    }

    public void turnSeatHeight(int location, int orientation) {
        Log.i(TAG, "turnSeatHeight is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatHeight", Integer.valueOf(location), Integer.valueOf(orientation));
            Log.v(TAG, "turnSeatHeight mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightDriver(orientation);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPassenger(orientation);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightLeftArea(orientation);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightRightArea(orientation);
        }
    }

    public void turnSeatHorization(int location, int orientation) {
        Log.i(TAG, "turnSeatHorization is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatHorization", Integer.valueOf(location), Integer.valueOf(orientation));
            Log.v(TAG, "turnSeatHorization mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationDriver(orientation);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPassenger(orientation);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationLeftArea(orientation);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRightArea(orientation);
        }
    }

    public void turnRearviewMirror(int action) {
        Log.i(TAG, "turnRearviewMirror is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnRearviewMirror", Integer.valueOf(action));
            Log.v(TAG, "turnRearviewMirror mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getSettingAdapterManager().turnRearviewMirror(action);
        }
    }

    public void turnSteeringWheel(int action) {
        Log.i(TAG, "turnSteeringWheel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSteeringWheel", Integer.valueOf(action));
            Log.v(TAG, "turnSteeringWheel mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getSettingAdapterManager().turnSteeringWheel(action);
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

    public int setSafeWarnState(int state) {
        Log.i(TAG, "setSafeWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSafeWarnState", Integer.valueOf(state));
            Log.v(TAG, "setSafeWarnState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeWarnState(state);
    }

    public int setMaintainRemindState(int state) {
        Log.i(TAG, "setMaintainRemindState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMaintainRemindState", Integer.valueOf(state));
            Log.v(TAG, "setMaintainRemindState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMaintainRemindState(state);
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

    public int setBackHomeLightDelay(int value) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setBackHomeLightDelay(value);
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

    public int setLeftHomeLightDelayValue(int value) {
        Log.i(TAG, "setLeftHomeLightDelayValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestSettingManagerImplCallback.SETLEFTHOMELIGHTDELAYVALUE, Integer.valueOf(value));
            Log.v(TAG, "getBackHomeLightDelay mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeftHomeLightDelayValue(value);
    }

    public void turnSeatbackrestPercent(int location, int percent) {
        Log.i(TAG, "turnSeatbackrestPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatbackrestPercent", Integer.valueOf(location), Integer.valueOf(percent));
            Log.v(TAG, "turnSeatbackrestPercent mForTestValue is 0 execute return");
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPercentDriver(percent);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPercentPassenger(percent);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPercentLeftArea(percent);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatbackrestPercentRightArea(percent);
        }
    }

    public void turnSeatHorizationPercent(int location, int percent) {
        Log.i(TAG, "turnSeatHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatHorizationPercent", Integer.valueOf(location), Integer.valueOf(percent));
            Log.v(TAG, "turnSeatHorizationPercent mForTestValue is 0 execute return");
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPercentDriver(percent);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPercentPassenger(percent);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPercentLeftArea(percent);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationPercentRightArea(percent);
        }
    }

    public void turnSeatHeightPercent(int location, int percent) {
        Log.i(TAG, "turnSeatHeightPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnSeatHeightPercent", Integer.valueOf(location), Integer.valueOf(percent));
            Log.v(TAG, "turnSeatHeightPercent mForTestValue is 0 execute return");
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return;
        }
        if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPercentDriver(percent);
            return;
        }
        if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPercentPassenger(percent);
        } else if (location == 3) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPercentLeftArea(percent);
        } else if (location == 4) {
            this.mTsManagerImpl.getSettingAdapterManager().turnSeatHeightPercentRightArea(percent);
        }
    }

    public int setStartOrPowerInd(int value) {
        Log.i(TAG, "setStartOrPowerInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setStartOrPowerInd", Integer.valueOf(value));
            Log.v(TAG, "setStartOrPowerInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setStartOrPowerInd(value);
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

    public int setParkBrakeInd(int value) {
        Log.i(TAG, "setParkBrakeInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setParkBrakeInd", Integer.valueOf(value));
            Log.v(TAG, "setParkBrakeInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setParkBrakeInd(value);
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

    public int setDriveDoorCloseInd(int value) {
        Log.i(TAG, "setDriveDoorCloseInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setDriveDoorCloseInd", Integer.valueOf(value));
            Log.v(TAG, "setDriveDoorCloseInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDriveDoorCloseInd(value);
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

    public int setSafeBeltInd(int value) {
        Log.i(TAG, "setSafeBeltInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSafeBeltInd", Integer.valueOf(value));
            Log.v(TAG, "setSafeBeltInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltInd(value);
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

    public int setLeaveCarPInd(int value) {
        Log.i(TAG, "setLeaveCarPInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLeaveCarPInd", Integer.valueOf(value));
            Log.v(TAG, "setLeaveCarPInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeaveCarPInd(value);
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

    public int setKeyPowerLowInd(int value) {
        Log.i(TAG, "setKeyPowerLowInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setKeyPowerLowInd", Integer.valueOf(value));
            Log.v(TAG, "setKeyPowerLowInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setKeyPowerLowInd(value);
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

    public int setMissKeyInd(int value) {
        Log.i(TAG, "setMissKeyInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMissKeyInd", Integer.valueOf(value));
            Log.v(TAG, "setMissKeyInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMissKeyInd(value);
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

    public int setNightDRLightOnInd(int value) {
        Log.i(TAG, "setNightDRLightOnInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setNightDRLightOnInd", Integer.valueOf(value));
            Log.v(TAG, "setNightDRLightOnInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setNightDRLightOnInd(value);
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

    public int setLowOilInd(int value) {
        Log.i(TAG, "setLowOilInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLowOilInd", Integer.valueOf(value));
            Log.v(TAG, "setLowOilInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLowOilInd(value);
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

    public int setLowBatteryInd(int value) {
        Log.i(TAG, "setLowBatteryInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLowBatteryInd", Integer.valueOf(value));
            Log.v(TAG, "setLowBatteryInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLowBatteryInd(value);
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

    public int setStopRemoteCtrlDriveInd(int value) {
        Log.i(TAG, "setStopRemoteCtrlDriveInd is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setStopRemoteCtrlDriveInd", Integer.valueOf(value));
            Log.v(TAG, "setStopRemoteCtrlDriveInd mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setStopRemoteCtrlDriveInd(value);
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

    public int setAutomaticParking(int value) {
        Log.i(TAG, "setAutomaticParking is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setAutomaticParking", Integer.valueOf(value));
            Log.v(TAG, "setAutomaticParking mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAutomaticParking(value);
    }

    public int getIALColor(int area) {
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
        if (area == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIALColor(area);
        }
        if (area == 1 || area == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalColorFront();
        }
        if (area == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalColorStarrySky();
        }
        if (area == 5) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalColorStarRing();
        }
        return 0;
    }

    public int setIALColor(int area, int color, int source) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIALColor", Integer.valueOf(area), Integer.valueOf(color), Integer.valueOf(source));
            Log.v(TAG, "setIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIALColor(area, color, source);
    }

    public int getIALBrightness(int area) {
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
        if (area == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalBrightnessBack();
        }
        if (area == 1 || area == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalBrightnessFront();
        }
        if (area == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalBrightnessStarrySky();
        }
        if (area == 5) {
            return this.mTsManagerImpl.getSettingAdapterManager().getIalBrightnessStarRing();
        }
        return 0;
    }

    public int setIALBrightness(int area, int brightness, int source) {
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIALBrightness", Integer.valueOf(area), Integer.valueOf(brightness), Integer.valueOf(source));
            Log.v(TAG, "setIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIALBrightness(area, brightness, source);
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

    public int setIALArea(int area, int source) {
        Log.i(TAG, "setIALArea is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIALArea", Integer.valueOf(area), Integer.valueOf(source));
            Log.v(TAG, "setIALArea mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIALArea(area, source);
    }

    public int setIALInfo(int area, int color, int brightness, int source) {
        Log.i(TAG, "setIALInfo is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setIALInfo", Integer.valueOf(area), Integer.valueOf(color), Integer.valueOf(brightness), Integer.valueOf(source));
            Log.v(TAG, "setIALInfo mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIALInfo(area, color, brightness, source);
    }

    public int getSeatHeatingState(int seatId) {
        Log.i(TAG, "getSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSeatHeatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (seatId == 1) {
            int valueDriver = this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateDriver();
            int valueDriver437 = this.mTsManagerImpl.getSettingAdapterManager().getDriverSeatHeating437State();
            Log.v(TAG, "getSeatHeatingState valueDriver : " + valueDriver + " , value437 : " + valueDriver437);
            if (valueDriver437 > 0 && valueDriver437 < 8) {
                return valueDriver437;
            }
            return valueDriver;
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStatePassenger();
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateLeftRear();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateRightRear();
        }
        return 0;
    }

    public int setSeatHeatingState(int seatId, int state) {
        Log.i(TAG, "setSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSeatHeatingState", Integer.valueOf(seatId), Integer.valueOf(state));
            Log.v(TAG, "setSeatHeatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (state == 3) {
            state = 1;
        } else if (state == 1) {
            state = 3;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatHeatingStateDriver(state);
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatHeatingStatePassenger(state);
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatHeatingStateLeftRear(state);
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatHeatingStateRightRear(state);
        }
        return 0;
    }

    public int getSeatVentilatingState() {
        Log.i(TAG, "getSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSeatVentilatingState mForTestValue is 0 execute return");
            return 0;
        }
        int value = this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingState();
        int value437 = this.mTsManagerImpl.getSettingAdapterManager().getDriverSeatVentilating437State();
        Log.v(TAG, "getSeatVentilatingState value : " + value + " , value437 : " + value437);
        if (value437 > 0 && value437 < 8) {
            return value437;
        }
        return value;
    }

    public int getSeatVentilatingState(int seatId) {
        Log.i(TAG, "getSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSeatVentilatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingState();
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingStatePassenger();
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingStateBackLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingStateBackRight();
        }
        return 0;
    }

    public int setSeatVentilatingState(int seatId, int state) {
        Log.i(TAG, "setSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSeatVentilatingState", Integer.valueOf(seatId), Integer.valueOf(state));
            Log.v(TAG, "setSeatVentilatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (state == 3) {
            state = 1;
        } else if (state == 1) {
            state = 3;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatVentilatingStateDriver(state);
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatVentilatingStatePassenger(state);
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatVentilatingStateLeftRear(state);
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setSeatVentilatingStateRightRear(state);
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

    public int setLanternLanguageMode(int mode) {
        Log.i(TAG, "setLanternLanguageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLanternLanguageMode", Integer.valueOf(mode));
            Log.v(TAG, "setLanternLanguageMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLanternLanguageMode(mode);
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

    public int setINSTheme(int value) {
        Log.i(TAG, "setINSTheme is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setINSTheme", Integer.valueOf(value));
            Log.v(TAG, "setINSTheme mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setINSTheme(value);
    }

    public int resetSystemSetting(int state) {
        Log.i(TAG, "restoreSettingItem is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("factoryResetAll", Integer.valueOf(state));
            Log.v(TAG, "restoreSettingItem mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().restoreSettingItem(state);
    }

    public int factoryResetAll(int state) {
        Log.i(TAG, "factoryResetAll is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("factoryResetAll", Integer.valueOf(state));
            Log.v(TAG, "factoryResetAll mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().factoryResetAll(state);
    }

    public int setHeadlampLevel(int level) {
        Log.i(TAG, "setHeadlampLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setHeadlampLevel", Integer.valueOf(level));
            Log.v(TAG, "setHeadlampLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHeadlampLevel(level);
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

    public int setSmartWelcomeLightState(int state) {
        Log.i(TAG, "setSmartWelcomeLightState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSmartWelcomeLightState", Integer.valueOf(state));
            Log.v(TAG, "setSmartWelcomeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSmartWelcomeLightState(state);
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

    public int sendOffReminder(int value) {
        Log.i(TAG, "sendOffReminder is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("sendOffReminder", Integer.valueOf(value));
            Log.v(TAG, "sendOffReminder mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().sendOffReminder(value);
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

    public int setRearAcPanelAutoLockTime(int time) {
        Log.i(TAG, "setRearAcPanelAutoLockTime is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRearAcPanelAutoLockTime", Integer.valueOf(time));
            Log.v(TAG, "setRearAcPanelAutoLockTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearAcPanelAutoLockTime(time);
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

    public int setExhibitionStartEnginePermit(int value) {
        Log.i(TAG, "setExhibitionStartEnginePermit is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setExhibitionStartEnginePermit", Integer.valueOf(value));
            Log.v(TAG, "setExhibitionStartEnginePermit mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setExhibitionStartEnginePermit(value);
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

    public int setSteeringWheelHeatingState(int state) {
        Log.i(TAG, "setSteeringWheelHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSteeringWheelHeatingState", Integer.valueOf(state));
            Log.v(TAG, "setSteeringWheelHeatingState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingState(state);
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

    public int setInsideLightDoorState(int state) {
        Log.i(TAG, "setInsideLightDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setInsideLightDoorState", Integer.valueOf(state));
            Log.v(TAG, "setInsideLightDoorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setInsideLightDoorState(state);
    }

    public int setPadRotation(int rotation) {
        Log.i(TAG, "setPadRotation is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setPadRotation", Integer.valueOf(rotation));
            Log.v(TAG, "setPadRotation mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPadRotation(rotation);
    }

    public int resetSetting(int[] featureIds, int[] values) {
        Log.i(TAG, "resetSetting is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("resetSetting", featureIds, values);
            Log.v(TAG, "resetSetting mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetSetting(featureIds, values);
    }

    public int setMassageMode(int type, int state) {
        Log.i(TAG, "setMassageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMassageMode", Integer.valueOf(type), Integer.valueOf(state));
            Log.v(TAG, "setMassageMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageModeDriver(state);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageModePassenger(state);
        }
        if (type == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageModeLeftRear(state);
        }
        if (type == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageModeRightRear(state);
        }
        return 0;
    }

    public int getMassageMode(int type) {
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
        if (type == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageModeDriver();
        }
        if (type == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageModePassenger();
        }
        if (type == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageModeLeftRear();
        }
        if (type == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageModeRightRear();
        }
        return 0;
    }

    public int getMassageModeCombine(int type) {
        Log.d(TAG, "getMassageModeCombine, type:" + type);
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getMassageModeCombine mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        int massageMode = getMassageMode(type);
        int ucuMassageMode = getUcuMassageMode(type);
        Log.d(TAG, "getMassageModeCombine, type:" + type + ", massageMode:" + massageMode + ", ucuMassageMode:" + ucuMassageMode);
        if (massageMode > 0) {
            return massageMode;
        }
        if (ucuMassageMode > 0) {
            return ucuMassageMode;
        }
        if (type == 3 || type == 4) {
            int bdMassageMode = getBdMassageMode(type);
            int secuMassageMode = getSecuMassageMode(type);
            Log.d(TAG, "getMassageModeCombine, type:" + type + ", bdMassageMode:" + bdMassageMode + ", secuMassageMode:" + secuMassageMode);
            if (secuMassageMode > 0) {
                return secuMassageMode;
            }
            if (bdMassageMode > 0) {
                return bdMassageMode;
            }
        }
        return 0;
    }

    public int getMassageLevel(int type) {
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
        if (type == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageLevelDriver();
        }
        if (type == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageLevelPassenger();
        }
        if (type == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageLevelLeftRear();
        }
        if (type == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getMassageLevelRightRear();
        }
        return 0;
    }

    public int getMassageLevelCombine(int type) {
        Log.d(TAG, "getMassageLevelCombine, type:" + type);
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getMassageLevelCombine mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        int massageLevel = getMassageLevel(type);
        int ucuMassageLevel = getUcuMassageLevel(type);
        Log.d(TAG, "getMassageLevelCombine, type:" + type + ", massageLevel:" + massageLevel + ", ucuMassageLevel:" + ucuMassageLevel);
        if (massageLevel > 0) {
            return massageLevel;
        }
        if (ucuMassageLevel > 0) {
            return ucuMassageLevel;
        }
        if (type == 3 || type == 4) {
            int bdMassageLevel = getBdMassageLevel(type);
            int secuMassageLevel = getSecuMassageLevel(type);
            Log.d(TAG, "getMassageLevelCombine, type:" + type + ", bdMassageLevel:" + bdMassageLevel + ", secuMassageLevel:" + secuMassageLevel);
            if (bdMassageLevel > 0) {
                return bdMassageLevel;
            }
            if (secuMassageLevel > 0) {
                return secuMassageLevel;
            }
        }
        return 0;
    }

    public int setMassageLevel(int type, int state) {
        Log.i(TAG, "setMassageLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMassageLevel", Integer.valueOf(type), Integer.valueOf(state));
            Log.v(TAG, "setMassageLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageLevelDriver(state);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageLevelPassenger(state);
        }
        if (type == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageLevelLeftRear(state);
        }
        if (type == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setMassageLevelRightRear(state);
        }
        return 0;
    }

    public int saveSeatParamsAll(int postion, int location) {
        Log.i(TAG, "saveSeatParamsAll is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("saveSeatParamsAll", Integer.valueOf(postion), Integer.valueOf(location));
            Log.v(TAG, "saveSeatParamsAll mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (location == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().saveSeatParamsAllDriver(postion);
        }
        if (location == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().saveSeatParamsAllPassenger(postion);
        }
        if (location == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().saveSeatParamsAllLeftRear(postion);
        }
        if (location == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().saveSeatParamsAllRightRear(postion);
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

    public int setKeypadToneState(int state) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setKeypadToneState(state);
    }

    public void setKeypadToneStatechanged(int state) {
        Log.v(TAG, "setKeypadToneStatechanged is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, "setKeypadToneStatechanged mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getSettingAdapterManager().setKeypadToneStatechanged(state);
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

    public int setComSpeedUpState(int value) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setComSpeedUpState(value);
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

    public int setComSpeedDownState(int value) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setComSpeedDownState(value);
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

    public int setHSpeedAutoCloseState(int value) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setHSpeedAutoCloseState(value);
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

    public int setRuningCloseDoorState(int value) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setRuningCloseDoorState(value);
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

    public int voiceCtlBackDoor(int cmd) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().voiceCtlBackDoor(cmd);
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

    public int setAidedDrivingInd(int value) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setAidedDrivingInd(value);
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

    public void turnHeadrestHorizationPercent(int location, int percent) {
        Log.v(TAG, "turnHeadrestHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnHeadrestHorizationPercent", Integer.valueOf(location), Integer.valueOf(percent));
            Log.v(TAG, "turnHeadrestHorizationPercent mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestHorizationPercentDriver(percent);
        }
    }

    public void turnHeadrestHeightPercent(int location, int percent) {
        Log.v(TAG, "turnHeadrestHorizationPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnHeadrestHeightPercent", Integer.valueOf(location), Integer.valueOf(percent));
            Log.v(TAG, "turnHeadrestHeightPercent mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnHeadrestHeightPercentDriver(percent);
        }
    }

    public void turnCaliperSplintPercent(int location, int percent) {
        Log.v(TAG, "turnCaliperSplintPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnCaliperSplintPercent", Integer.valueOf(location), Integer.valueOf(percent));
            Log.v(TAG, "turnCaliperSplintPercent mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnCaliperSplintPercentDriver(percent);
        } else if (location == 2) {
            this.mTsManagerImpl.getSettingAdapterManager().turnCaliperSplintPercentPassenger(percent);
        }
    }

    public void turnCushionPercent(int location, int percent) {
        Log.v(TAG, "turnCushionPercent is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("turnCushionPercent", Integer.valueOf(location), Integer.valueOf(percent));
            Log.v(TAG, "turnCushionPercent mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else if (location == 1) {
            this.mTsManagerImpl.getSettingAdapterManager().turnCushionPercentDriver(percent);
        }
    }

    public int setAadsSwitchSate(int value) {
        Log.v(TAG, "setAadsSwitchSate is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setAadsSwitchSate", Integer.valueOf(value));
            Log.v(TAG, "setAadsSwitchSate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAfsSwitchSate(value);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        int i;
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 482) {
                        i = 0;
                        bydAutoEventValue.intValue = getSeatHeatingNotL1PState();
                        sparseArray.put(482, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 483) {
                        i = 0;
                        bydAutoEventValue.intValue = getSeatVentilatingNotL1PState();
                        sparseArray.put(483, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 495) {
                        i = 0;
                        bydAutoEventValue.intValue = getSeatHeatingState2(4);
                        sparseArray.put(495, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 496) {
                        switch (eventType) {
                            case 384:
                                i = 0;
                                bydAutoEventValue.intValue = getSteeringWheelHeatingGear();
                                sparseArray.put(384, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 386:
                                i = 0;
                                bydAutoEventValue.intValue = getSteeringWheelHeatingAutoState();
                                sparseArray.put(386, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 525:
                                i = 0;
                                bydAutoEventValue.intValue = getSteeringWheelHeatingState();
                                sparseArray.put(525, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 539:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
                                sparseArray.put(539, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 541:
                                i = 0;
                                bydAutoEventValue.intValue = getAutoExternalRearMirrorFollowUpSwitchSupport();
                                sparseArray.put(541, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 565:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCanByECU();
                                sparseArray.put(565, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 566:
                                i = 0;
                                bydAutoEventValue.intValue = getMainDriverSeatAdjustmentConfigurationByGCtrl();
                                sparseArray.put(566, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 567:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByGCtrl();
                                sparseArray.put(567, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 568:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
                                sparseArray.put(568, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 569:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByGCtrl();
                                sparseArray.put(569, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 570:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
                                sparseArray.put(570, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 571:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftBackrestAdjustmentConfigurationByECU();
                                sparseArray.put(571, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 572:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
                                sparseArray.put(572, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 573:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftLevelAdjustmentConfigurationByECU();
                                sparseArray.put(573, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 574:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftBidetAdjustmentConfigurationByECU();
                                sparseArray.put(574, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 575:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
                                sparseArray.put(575, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 576:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
                                sparseArray.put(576, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 577:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftHeightAdjustmentConfigurationByECU();
                                sparseArray.put(577, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 578:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
                                sparseArray.put(578, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 579:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftLegSupportAdjustmentConfigurationByECU();
                                sparseArray.put(579, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 580:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMemoryConfiguration();
                                sparseArray.put(580, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 581:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfigurationByECU();
                                sparseArray.put(581, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 679:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getFrontWiperSpeedValue();
                                sparseArray.put(679, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 711:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
                                sparseArray.put(711, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 751:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getRearWiperSpeedValue();
                                sparseArray.put(751, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 760:
                                i = 0;
                                bydAutoEventValue.intValue = getPassangerSaveOrRecoverState();
                                sparseArray.put(760, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 873:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearLeftMemorySwitchConfigurationByECU();
                                sparseArray.put(873, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 874:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightMemorySwitchConfigurationByECU();
                                sparseArray.put(874, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 877:
                                i = 0;
                                bydAutoEventValue.intValue = getACAutoAir();
                                sparseArray.put(877, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 888:
                                i = 0;
                                bydAutoEventValue.intValue = getCurrentStatusOfDimmingSunroof();
                                sparseArray.put(888, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 902:
                                i = 0;
                                bydAutoEventValue.intValue = getAmbientFeedBack();
                                sparseArray.put(902, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 921:
                                i = 0;
                                bydAutoEventValue.intValue = getCanopyAutomaticDimming();
                                sparseArray.put(921, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 923:
                                i = 0;
                                bydAutoEventValue.intValue = getDimmingCanopyConfiguration();
                                sparseArray.put(923, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 924:
                                i = 0;
                                bydAutoEventValue.intValue = getCurrentTransmittanceOfSkylight();
                                sparseArray.put(924, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 925:
                                i = 0;
                                bydAutoEventValue.intValue = getSkyCurtainPowerOffAndDim();
                                sparseArray.put(925, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 953:
                                i = 0;
                                bydAutoEventValue.intValue = getDoorHeightSupport();
                                sparseArray.put(953, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 987:
                                i = 0;
                                bydAutoEventValue.intValue = getElectroniChildLockConfig();
                                sparseArray.put(987, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1040:
                                i = 0;
                                bydAutoEventValue.intValue = getSeatHeatingState(1);
                                sparseArray.put(1040, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1044:
                                i = 0;
                                bydAutoEventValue.intValue = getSeatVentilatingState();
                                sparseArray.put(1044, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1066:
                                i = 0;
                                bydAutoEventValue.intValue = getSupportSeatMassageCombine(1);
                                sparseArray.put(1066, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1067:
                                i = 0;
                                bydAutoEventValue.intValue = getMassageLevelCombine(1);
                                sparseArray.put(1067, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1068:
                                i = 0;
                                bydAutoEventValue.intValue = getMassageModeCombine(1);
                                sparseArray.put(1068, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1069:
                                i = 0;
                                bydAutoEventValue.intValue = getSupportSeatMassageCombine(2);
                                sparseArray.put(1069, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1070:
                                i = 0;
                                bydAutoEventValue.intValue = getMassageLevelCombine(2);
                                sparseArray.put(1070, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1071:
                                i = 0;
                                bydAutoEventValue.intValue = getMassageModeCombine(2);
                                sparseArray.put(1071, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1146:
                                i = 0;
                                bydAutoEventValue.intValue = getAutoExternalRearMirrorFollowUpSwitchSupport();
                                sparseArray.put(1146, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1154:
                                i = 0;
                                bydAutoEventValue.intValue = getAmbientAreaSupport();
                                sparseArray.put(1154, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1155:
                                i = 0;
                                bydAutoEventValue.intValue = getAmbientAreaSupport();
                                sparseArray.put(1155, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1156:
                                i = 0;
                                bydAutoEventValue.intValue = getIALBrightness(2);
                                sparseArray.put(1156, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1157:
                                i = 0;
                                bydAutoEventValue.intValue = getIALColor(2);
                                sparseArray.put(1157, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1158:
                                i = 0;
                                bydAutoEventValue.intValue = getAmbientLightSupport();
                                sparseArray.put(1158, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1159:
                                i = 0;
                                bydAutoEventValue.intValue = getAmbientLightColorSupport();
                                sparseArray.put(1159, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1160:
                                i = 0;
                                bydAutoEventValue.intValue = getIALBrightness(1);
                                sparseArray.put(1160, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1161:
                                i = 0;
                                bydAutoEventValue.intValue = getIALColor(1);
                                sparseArray.put(1161, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1171:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getIALArea();
                                sparseArray.put(1171, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1220:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getMemorySetOperationFlag();
                                sparseArray.put(1220, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1255:
                                i = 0;
                                bydAutoEventValue.intValue = getAmbientMusicModeState(1);
                                sparseArray.put(1255, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1268:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
                                sparseArray.put(1268, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1269:
                                i = 0;
                                bydAutoEventValue.intValue = getSeatHeatingState(2);
                                sparseArray.put(1269, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1273:
                                i = 0;
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingStatePassenger();
                                sparseArray.put(1273, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1283:
                                i = 0;
                                bydAutoEventValue.intValue = getSeatHeatingState(3);
                                if (bydAutoEventValue.intValue == 1 || bydAutoEventValue.intValue == 2 || bydAutoEventValue.intValue == 3 || bydAutoEventValue.intValue == 4) {
                                    sparseArray.put(1283, Integer.valueOf(bydAutoEventValue.intValue));
                                } else {
                                    bydAutoEventValue.intValue = getSeatHeatingState2(3);
                                    if (bydAutoEventValue.intValue == 1 || bydAutoEventValue.intValue == 2 || bydAutoEventValue.intValue == 3 || bydAutoEventValue.intValue == 4) {
                                        sparseArray.put(1283, Integer.valueOf(bydAutoEventValue.intValue));
                                    } else {
                                        sparseArray.put(1283, Integer.valueOf(bydAutoEventValue.intValue));
                                    }
                                }
                                break;
                            case 1285:
                                i = 0;
                                bydAutoEventValue.intValue = getSupportSeatMassageCombine(3);
                                sparseArray.put(1285, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1286:
                                i = 0;
                                bydAutoEventValue.intValue = getMassageLevelCombine(3);
                                sparseArray.put(1286, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1288:
                                i = 0;
                                bydAutoEventValue.intValue = getMassageModeCombine(3);
                                sparseArray.put(1288, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1290:
                                bydAutoEventValue.intValue = getSeatVentilatingState(3);
                                if (bydAutoEventValue.intValue == 1 || bydAutoEventValue.intValue == 2 || bydAutoEventValue.intValue == 3 || bydAutoEventValue.intValue == 4) {
                                    i = 0;
                                    sparseArray.put(1290, Integer.valueOf(bydAutoEventValue.intValue));
                                } else {
                                    bydAutoEventValue.intValue = getSeatVentilatingState2(3);
                                    if (bydAutoEventValue.intValue == 1 || bydAutoEventValue.intValue == 2 || bydAutoEventValue.intValue == 3 || bydAutoEventValue.intValue == 4) {
                                        i = 0;
                                        sparseArray.put(1290, Integer.valueOf(bydAutoEventValue.intValue));
                                    } else {
                                        i = 0;
                                        sparseArray.put(1290, 0);
                                    }
                                }
                                break;
                            case 1294:
                                bydAutoEventValue.intValue = getSeatHeatingState(4);
                                if (bydAutoEventValue.intValue == 1 || bydAutoEventValue.intValue == 2 || bydAutoEventValue.intValue == 3 || bydAutoEventValue.intValue == 4) {
                                    sparseArray.put(1294, Integer.valueOf(bydAutoEventValue.intValue));
                                    i = 0;
                                } else {
                                    bydAutoEventValue.intValue = getSeatHeatingState2(4);
                                    if (bydAutoEventValue.intValue == 1 || bydAutoEventValue.intValue == 2 || bydAutoEventValue.intValue == 3 || bydAutoEventValue.intValue == 4) {
                                        sparseArray.put(1294, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                    } else {
                                        sparseArray.put(1294, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                    }
                                }
                                break;
                            case 1296:
                                bydAutoEventValue.intValue = getSupportSeatMassageCombine(4);
                                sparseArray.put(1296, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1297:
                                bydAutoEventValue.intValue = getMassageLevelCombine(4);
                                sparseArray.put(1297, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1299:
                                bydAutoEventValue.intValue = getMassageModeCombine(4);
                                sparseArray.put(1299, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1301:
                                bydAutoEventValue.intValue = getSeatVentilatingState(4);
                                if (bydAutoEventValue.intValue == 1 || bydAutoEventValue.intValue == 2 || bydAutoEventValue.intValue == 3 || bydAutoEventValue.intValue == 4) {
                                    sparseArray.put(1301, Integer.valueOf(bydAutoEventValue.intValue));
                                    i = 0;
                                } else {
                                    bydAutoEventValue.intValue = getSeatVentilatingState2(4);
                                    if (bydAutoEventValue.intValue == 1 || bydAutoEventValue.intValue == 2 || bydAutoEventValue.intValue == 3 || bydAutoEventValue.intValue == 4) {
                                        sparseArray.put(1301, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                    } else {
                                        sparseArray.put(1301, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                    }
                                }
                                break;
                            case 1305:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getSettingAdapterManager().getMainSaveOrRecoveFailCode();
                                sparseArray.put(1305, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1396:
                                bydAutoEventValue.intValue = getScreenAngleValue();
                                sparseArray.put(1396, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1410:
                                bydAutoEventValue.intValue = getSafeBeltWarnState();
                                sparseArray.put(1410, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1414:
                                bydAutoEventValue.intValue = getPassengerState(2);
                                sparseArray.put(1414, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1415:
                                bydAutoEventValue.intValue = getPassengerState(3);
                                sparseArray.put(1415, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1416:
                                bydAutoEventValue.intValue = getPassengerState(5);
                                sparseArray.put(1416, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1417:
                                bydAutoEventValue.intValue = getPassengerState(4);
                                sparseArray.put(1417, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1698:
                                bydAutoEventValue.intValue = getComSpeedUpState();
                                sparseArray.put(1698, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1703:
                                bydAutoEventValue.intValue = getComSpeedDownState();
                                sparseArray.put(1703, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1707:
                                bydAutoEventValue.intValue = getRainAutoLockWindowState();
                                sparseArray.put(1707, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1711:
                                bydAutoEventValue.intValue = getTrayState();
                                sparseArray.put(1711, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1723:
                                bydAutoEventValue.intValue = getDisinfState();
                                sparseArray.put(1723, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1725:
                                bydAutoEventValue.intValue = getRefrigeratorDoorState();
                                sparseArray.put(1725, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1726:
                                bydAutoEventValue.intValue = getRefrigeratorState();
                                sparseArray.put(1726, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1727:
                                bydAutoEventValue.intValue = getRemenberPowerOffState();
                                sparseArray.put(1727, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1729:
                                bydAutoEventValue.intValue = getRefrigeratorMode();
                                sparseArray.put(1729, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1732:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
                                sparseArray.put(1732, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 1739:
                                bydAutoEventValue.intValue = getHSpeedAutoCloseState();
                                sparseArray.put(1739, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2086:
                                bydAutoEventValue.intValue = getTrayActionState();
                                sparseArray.put(2086, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2088:
                                bydAutoEventValue.intValue = getTrayInitState();
                                sparseArray.put(2088, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2098:
                                bydAutoEventValue.floatValue = getSteeringWheelY();
                                sparseArray.put(2098, Float.valueOf(bydAutoEventValue.floatValue));
                                i = 0;
                                break;
                            case 2100:
                                bydAutoEventValue.floatValue = getSteeringWheelZ();
                                sparseArray.put(2100, Float.valueOf(bydAutoEventValue.floatValue));
                                i = 0;
                                break;
                            case 2120:
                                bydAutoEventValue.floatValue = getRearviewMirrorX(1);
                                sparseArray.put(2120, Float.valueOf(bydAutoEventValue.floatValue));
                                i = 0;
                                break;
                            case 2122:
                                bydAutoEventValue.floatValue = getRearviewMirrorY(1);
                                sparseArray.put(2122, Float.valueOf(bydAutoEventValue.floatValue));
                                i = 0;
                                break;
                            case 2130:
                                bydAutoEventValue.floatValue = getRearviewMirrorX(2);
                                sparseArray.put(2130, Float.valueOf(bydAutoEventValue.floatValue));
                                i = 0;
                                break;
                            case 2132:
                                bydAutoEventValue.floatValue = getRearviewMirrorY(2);
                                sparseArray.put(2132, Float.valueOf(bydAutoEventValue.floatValue));
                                i = 0;
                                break;
                            case 2134:
                                bydAutoEventValue.intValue = getMainSaveOrRecoverState();
                                sparseArray.put(2134, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2654:
                                bydAutoEventValue.intValue = getIntelligentLockState();
                                sparseArray.put(2654, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2662:
                                bydAutoEventValue.intValue = getIntelligentUnlockState();
                                sparseArray.put(2662, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2805:
                                bydAutoEventValue.intValue = getAmbientMulticolorMode(0);
                                sparseArray.put(2805, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2808:
                                bydAutoEventValue.intValue = getAmbientMulticolorState(0);
                                sparseArray.put(2808, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2829:
                                bydAutoEventValue.intValue = getAmbientMusicMode(1);
                                sparseArray.put(2829, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2844:
                                bydAutoEventValue.intValue = getIALBrightness(5);
                                sparseArray.put(2844, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 2845:
                                bydAutoEventValue.intValue = getIALColor(5);
                                sparseArray.put(2845, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3158:
                                bydAutoEventValue.intValue = getBrakePedalMode();
                                sparseArray.put(3158, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3161:
                                bydAutoEventValue.intValue = getComfortableParkingState();
                                sparseArray.put(3161, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3371:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingMode(1);
                                sparseArray.put(3371, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3372:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingLevel(1);
                                sparseArray.put(3372, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3374:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingState(1);
                                sparseArray.put(3374, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3376:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingMode(1);
                                sparseArray.put(3376, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3378:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingLevel(1);
                                sparseArray.put(3378, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3380:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingState(1);
                                sparseArray.put(3380, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3425:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingMode(2);
                                sparseArray.put(3425, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3426:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingLevel(2);
                                sparseArray.put(3426, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3428:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingState(2);
                                sparseArray.put(3428, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3430:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingMode(2);
                                sparseArray.put(3430, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3432:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingLevel(2);
                                sparseArray.put(3432, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3434:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingState(2);
                                sparseArray.put(3434, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3466:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingMode(3);
                                sparseArray.put(3466, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3467:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingLevel(3);
                                sparseArray.put(3467, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3469:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingState(3);
                                sparseArray.put(3469, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3473:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingLevel(3);
                                sparseArray.put(3473, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3475:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingState(3);
                                sparseArray.put(3475, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3489:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingMode(4);
                                sparseArray.put(3489, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3490:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingLevel(4);
                                sparseArray.put(3490, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3492:
                                bydAutoEventValue.intValue = get5LevelSeatHeatingState(4);
                                sparseArray.put(3492, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3496:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingLevel(4);
                                sparseArray.put(3496, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3498:
                                bydAutoEventValue.intValue = get5LevelSeatVentilatingState(4);
                                sparseArray.put(3498, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3522:
                                bydAutoEventValue.intValue = getIntelligentAcAreaState();
                                sparseArray.put(3522, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3557:
                                bydAutoEventValue.intValue = getAcWarmMode();
                                sparseArray.put(3557, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 3586:
                                bydAutoEventValue.intValue = getEngineSoundType();
                                sparseArray.put(3586, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4581:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
                                sparseArray.put(4581, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4582:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
                                sparseArray.put(4582, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4583:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
                                sparseArray.put(4583, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4584:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
                                sparseArray.put(4584, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4625:
                                bydAutoEventValue.intValue = getDimmingCanopyLowCode();
                                sparseArray.put(4625, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4626:
                                bydAutoEventValue.intValue = getRearLeftMassageSwConfiguration();
                                sparseArray.put(4626, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4627:
                                bydAutoEventValue.intValue = getRearRightMassageSwConfiguration();
                                sparseArray.put(4627, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4628:
                                bydAutoEventValue.intValue = getElectricTailConfiguration();
                                sparseArray.put(4628, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4629:
                                bydAutoEventValue.intValue = getTailInitializationState();
                                sparseArray.put(4629, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4630:
                                bydAutoEventValue.intValue = getCurrentStateOfTheTailState();
                                sparseArray.put(4630, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4631:
                                bydAutoEventValue.intValue = getTailPositionPercentage();
                                sparseArray.put(4631, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4632:
                                bydAutoEventValue.intValue = getTailMotorThermalProtectionState();
                                sparseArray.put(4632, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4633:
                                bydAutoEventValue.intValue = getTailPositionFeedback();
                                sparseArray.put(4633, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4634:
                                bydAutoEventValue.intValue = getAlarmPromptState();
                                sparseArray.put(4634, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4635:
                                bydAutoEventValue.intValue = getCurrentModeOfTailState();
                                sparseArray.put(4635, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case 4636:
                                bydAutoEventValue.intValue = getElectricTailMaintenanceFuntatusState();
                                sparseArray.put(4636, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_LEFT_ELECTRIC_PEDAL_POSITION /* 4684 */:
                                bydAutoEventValue.intValue = getLeftElectricPedal();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_LEFT_ELECTRIC_PEDAL_POSITION, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_RIGHT_ELECTRIC_PEDAL_POSITION /* 4685 */:
                                bydAutoEventValue.intValue = getRightElectricPedal();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_RIGHT_ELECTRIC_PEDAL_POSITION, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORKING_STATUS /* 4686 */:
                                bydAutoEventValue.intValue = getRefrigeratorWorkMode();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORKING_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POWEROFF_MEMORY /* 4687 */:
                                bydAutoEventValue.intValue = getRemberPowerOffState();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POWEROFF_MEMORY, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS /* 4688 */:
                                bydAutoEventValue.intValue = getSterilizationState();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POPUP_REMIND /* 4689 */:
                                bydAutoEventValue.intValue = getVoiceTipsMessage();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POPUP_REMIND, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_REFRIGERATION_TEMP_RANGE_FLAG /* 4699 */:
                                bydAutoEventValue.intValue = getFridgeTemperatureRegulation();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_REFRIGERATION_TEMP_RANGE_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TELESCOPIC_CONTROL_FLAG /* 4700 */:
                                bydAutoEventValue.intValue = getFridgeExpansionAndContractionFunction();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TELESCOPIC_CONTROL_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TYPE /* 4701 */:
                                bydAutoEventValue.intValue = getCarFridgeType();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TYPE, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_PWOEROFF_SWITCH /* 4702 */:
                                bydAutoEventValue.intValue = getDelayPowerOffStatus();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_PWOEROFF_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWEROFF_TIME /* 4703 */:
                                bydAutoEventValue.intValue = getDelayPowerOffTime();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWEROFF_TIME, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_COFING_FLAG /* 4704 */:
                                bydAutoEventValue.intValue = getFridgeWorkingMode();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_COFING_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_FLAG /* 4705 */:
                                bydAutoEventValue.intValue = getFridgeChildLockFlag();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_STATE /* 4706 */:
                                bydAutoEventValue.intValue = getFridgeChildLock();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_STATE, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_COLD /* 4707 */:
                                bydAutoEventValue.intValue = getFridgeWorkColdTemperature();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_COLD, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_HEAT /* 4708 */:
                                bydAutoEventValue.intValue = getFridgeWorkHeatTemperature();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_HEAT, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_FRESH /* 4709 */:
                                bydAutoEventValue.intValue = getFridgeWorkSaveTemperature();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_FRESH, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_INTERFACE_FLAG /* 4710 */:
                                bydAutoEventValue.intValue = getFridgeScreenFlag();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_INTERFACE_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE /* 4711 */:
                                bydAutoEventValue.intValue = getDelayPowerDownTime();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE_CONFIG /* 4712 */:
                                bydAutoEventValue.intValue = getFridgeDelayedPowerOutage();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_BOUNCE_SIGNAL /* 4713 */:
                                bydAutoEventValue.intValue = getScreenCallbackMessage();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_BOUNCE_SIGNAL, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.ENGINE_SLIDING_RAIL_MOTOR_PERCENTAGE /* 4714 */:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getSlideRailMotorElecPercent();
                                sparseArray.put(BYDAutoFeatureIds.ENGINE_SLIDING_RAIL_MOTOR_PERCENTAGE, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SET_MASSAGE_PHYSICAL_BUTTONS_CONFIG /* 4730 */:
                                int keyConfig = getPhysicalkeyConfig();
                                int secuConfig = getPhysicalkeySecuConfig();
                                int secupConfig = getPhysicalkeySecupConfig();
                                int securlConfig = getPhysicalkeySecurlConfig();
                                int securrConfig = getPhysicalkeySecurrConfig();
                                if (keyConfig == 2 || secuConfig == 2 || secupConfig == 2 || securlConfig == 2 || securrConfig == 2) {
                                    bydAutoEventValue.intValue = 2;
                                }
                                sparseArray.put(BYDAutoFeatureIds.SET_MASSAGE_PHYSICAL_BUTTONS_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_AUTO_STEER_WHEEL_HEATING_CONFIG /* 4731 */:
                                bydAutoEventValue.intValue = getSteeringWheelHeatedPlan();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_AUTO_STEER_WHEEL_HEATING_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_SEAT_LAYOUT /* 4734 */:
                                bydAutoEventValue.intValue = getCarSeatLayout();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SEAT_LAYOUT, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_EXCLUSIVE_SAVE_SHORTCUT_KEY /* 4735 */:
                                bydAutoEventValue.intValue = getSaveOwnerState();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_EXCLUSIVE_SAVE_SHORTCUT_KEY, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_MOBINE_PUSH_REMIND /* 4736 */:
                                bydAutoEventValue.intValue = getRefrigeratorMobileTerminalPushRe();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_MOBINE_PUSH_REMIND, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_FIRST_REFRIGERATOR_LOCATION_FLAG /* 4737 */:
                                bydAutoEventValue.intValue = getRefrig1PositionMarkBit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_FIRST_REFRIGERATOR_LOCATION_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_SECOND_REFRIGERATOR_LOCATION_FLAG /* 4738 */:
                                bydAutoEventValue.intValue = getRefrig2PositionMarkBit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SECOND_REFRIGERATOR_LOCATION_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE_FLAG /* 4739 */:
                                bydAutoEventValue.intValue = getRefrigWorkFlag();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE /* 4740 */:
                                bydAutoEventValue.intValue = getRefrigWorkMode();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_QUANTITY_FLAG /* 4742 */:
                                bydAutoEventValue.intValue = getRefrigNumFlag();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_QUANTITY_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SET_SEAT_DISTRIBUTION_FORM /* 4779 */:
                                bydAutoEventValue.intValue = getSeatDistribution();
                                sparseArray.put(BYDAutoFeatureIds.SET_SEAT_DISTRIBUTION_FORM, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_WORKING_MODE /* 4780 */:
                                bydAutoEventValue.intValue = getThirdRowSeatWorkMode();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_WORKING_MODE, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_LEFT_REAR_ROW_SAFETYBELT_STATUS /* 4781 */:
                                bydAutoEventValue.intValue = getLeftRearSafebeltStatus();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_LEFT_REAR_ROW_SAFETYBELT_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_CENTER_REAR_ROW_SAFETYBELT_STATUS /* 4782 */:
                                bydAutoEventValue.intValue = getCenterRearSafebeltStatus();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_CENTER_REAR_ROW_SAFETYBELT_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_RIGHT_REAR_ROW_SAFETYBELT_STATUS /* 4783 */:
                                bydAutoEventValue.intValue = getRightRearSafebeltStatus();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_RIGHT_REAR_ROW_SAFETYBELT_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_THIRD_ROW_LEFT_SAFETYBELT_STATUS /* 4784 */:
                                bydAutoEventValue.intValue = getLeftThirdSafebeltStatus();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_THIRD_ROW_LEFT_SAFETYBELT_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_THIRD_ROW_RIGHT_SAFETYBELT_STATUS /* 4785 */:
                                bydAutoEventValue.intValue = getRightThirdSafebeltStatus();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_THIRD_ROW_RIGHT_SAFETYBELT_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_LF_SEAT_HORIZONTAL_UNINITIALIZED /* 4786 */:
                                bydAutoEventValue.intValue = getMainSeatHorizontalInit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_LF_SEAT_HORIZONTAL_UNINITIALIZED, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_LF_SEAT_BACKREST_UNINITIALIZED /* 4787 */:
                                bydAutoEventValue.intValue = getMainSeatBackInit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_LF_SEAT_BACKREST_UNINITIALIZED, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_RF_SEAT_HORIZONTAL_UNINITIALIZED /* 4788 */:
                                bydAutoEventValue.intValue = getPassangerSeatHorizontalInit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_RF_SEAT_HORIZONTAL_UNINITIALIZED, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_RF_SEAT_BACKREST_UNINITIALIZED /* 4789 */:
                                bydAutoEventValue.intValue = getPassangerSeatBackInit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_RF_SEAT_BACKREST_UNINITIALIZED, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_SECOND_ROW_LEFT_SEAT_HORIZONTAL_DIRECTION_INITIALIZATION_STATUS /* 4790 */:
                                bydAutoEventValue.intValue = getSecondLeftSeatHorizontalInit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SECOND_ROW_LEFT_SEAT_HORIZONTAL_DIRECTION_INITIALIZATION_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_SECOND_ROW_LEFT_SEAT_BACKREST_DIRECTION_INITIALIZATION_STATUS /* 4791 */:
                                bydAutoEventValue.intValue = getSecondLeftSeatBackInit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SECOND_ROW_LEFT_SEAT_BACKREST_DIRECTION_INITIALIZATION_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_ONEKEY_ENTER_PIT_CONFIG /* 4792 */:
                                bydAutoEventValue.intValue = getOneKeyEnterPitConfig();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_ONEKEY_ENTER_PIT_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SET_DRIVER_SEAT_VENTILATING_STATE_hal_only /* 4817 */:
                                bydAutoEventValue.intValue = getDriverSeatVentilating437State();
                                sparseArray.put(BYDAutoFeatureIds.SET_DRIVER_SEAT_VENTILATING_STATE_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SET_DRIVER_SEAT_HEATING_STATE_hal_only /* 4818 */:
                                bydAutoEventValue.intValue = getDriverSeatHeating437State();
                                sparseArray.put(BYDAutoFeatureIds.SET_DRIVER_SEAT_HEATING_STATE_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_SECOND_ROW_RIGHT_SEAT_HORIZONTAL_DIRECTION_INITIALIZATION_STATUS /* 4819 */:
                                bydAutoEventValue.intValue = getSecondRightSeatHorizontalInit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SECOND_ROW_RIGHT_SEAT_HORIZONTAL_DIRECTION_INITIALIZATION_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_SECOND_ROW_RIGHT_SEAT_BACKREST_DIRECTION_INITIALIZATION_STATUS /* 4820 */:
                                bydAutoEventValue.intValue = getSecondRightSeatBackInit();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SECOND_ROW_RIGHT_SEAT_BACKREST_DIRECTION_INITIALIZATION_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SAFETY_BELT_PASSENGER_COMMAND_FRONT_ROW_SEAT_LEFT /* 4823 */:
                                bydAutoEventValue.intValue = getMainSeatState();
                                sparseArray.put(BYDAutoFeatureIds.SAFETY_BELT_PASSENGER_COMMAND_FRONT_ROW_SEAT_LEFT, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_LF_SEAT_HORIZONTAL_UNINITIALIZED_437_hal_only /* 4824 */:
                                bydAutoEventValue.intValue = getMainSeatHorizontalInited();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_LF_SEAT_HORIZONTAL_UNINITIALIZED_437_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_LF_SEAT_BACKREST_UNINITIALIZED_437_hal_only /* 4825 */:
                                bydAutoEventValue.intValue = getMainSeatBackInited();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_LF_SEAT_BACKREST_UNINITIALIZED_437_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_LF_SEAT_HEIGHT_UNINITIALIZED_437_hal_only /* 4826 */:
                                bydAutoEventValue.intValue = getMainSeatHightInited();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_LF_SEAT_HEIGHT_UNINITIALIZED_437_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_LEFT_BACKREST_CONFIG /* 4827 */:
                                bydAutoEventValue.intValue = getThirdRowLeftBackrestDisplay();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_LEFT_BACKREST_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            case BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_RIGHT_BACKREST_CONFIG /* 4828 */:
                                bydAutoEventValue.intValue = getThirdRowRightBackrestDisplay();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_RIGHT_BACKREST_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                i = 0;
                                break;
                            default:
                                switch (eventType) {
                                    case 488:
                                        bydAutoEventValue.intValue = getSeatHeatingState2(3);
                                        sparseArray.put(488, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                        break;
                                    case 489:
                                        bydAutoEventValue.intValue = getEcuMassageLevel(3);
                                        sparseArray.put(489, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                        break;
                                    case 490:
                                        bydAutoEventValue.intValue = getEcuMassageLevel(4);
                                        sparseArray.put(490, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                        break;
                                    case 491:
                                        bydAutoEventValue.intValue = getMassageMode(3);
                                        sparseArray.put(491, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                        break;
                                    case 492:
                                        bydAutoEventValue.intValue = getEcuMassageMode(4);
                                        sparseArray.put(492, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                        break;
                                    case 493:
                                        bydAutoEventValue.intValue = getSeatVentilatingState2(3);
                                        sparseArray.put(493, Integer.valueOf(bydAutoEventValue.intValue));
                                        i = 0;
                                        break;
                                    default:
                                        switch (eventType) {
                                            case 535:
                                                bydAutoEventValue.intValue = getPassengerState(6);
                                                sparseArray.put(535, Integer.valueOf(bydAutoEventValue.intValue));
                                                i = 0;
                                                break;
                                            case 536:
                                                bydAutoEventValue.intValue = getPassengerState(8);
                                                sparseArray.put(536, Integer.valueOf(bydAutoEventValue.intValue));
                                                i = 0;
                                                break;
                                            case 537:
                                                bydAutoEventValue.intValue = getPassengerState(7);
                                                sparseArray.put(537, Integer.valueOf(bydAutoEventValue.intValue));
                                                i = 0;
                                                break;
                                            default:
                                                switch (eventType) {
                                                    case 556:
                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
                                                        sparseArray.put(556, Integer.valueOf(bydAutoEventValue.intValue));
                                                        i = 0;
                                                        break;
                                                    case 557:
                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
                                                        sparseArray.put(557, Integer.valueOf(bydAutoEventValue.intValue));
                                                        i = 0;
                                                        break;
                                                    case 558:
                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
                                                        sparseArray.put(558, Integer.valueOf(bydAutoEventValue.intValue));
                                                        i = 0;
                                                        break;
                                                    case 559:
                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
                                                        sparseArray.put(559, Integer.valueOf(bydAutoEventValue.intValue));
                                                        i = 0;
                                                        break;
                                                    default:
                                                        switch (eventType) {
                                                            case 584:
                                                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearSeatReclineConfiguration();
                                                                sparseArray.put(584, Integer.valueOf(bydAutoEventValue.intValue));
                                                                i = 0;
                                                                break;
                                                            case 585:
                                                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
                                                                sparseArray.put(585, Integer.valueOf(bydAutoEventValue.intValue));
                                                                i = 0;
                                                                break;
                                                            case 586:
                                                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByECU();
                                                                sparseArray.put(586, Integer.valueOf(bydAutoEventValue.intValue));
                                                                i = 0;
                                                                break;
                                                            case 587:
                                                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByGCtrl();
                                                                sparseArray.put(587, Integer.valueOf(bydAutoEventValue.intValue));
                                                                i = 0;
                                                                break;
                                                            case 588:
                                                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByECU();
                                                                sparseArray.put(588, Integer.valueOf(bydAutoEventValue.intValue));
                                                                i = 0;
                                                                break;
                                                            default:
                                                                switch (eventType) {
                                                                    case 590:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightBackrestAdjustmentConfigurationByECU();
                                                                        sparseArray.put(590, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 591:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
                                                                        sparseArray.put(591, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 592:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightLevelAdjustmentConfigurationByECU();
                                                                        sparseArray.put(592, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 593:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightBidetAdjustmentConfigurationByECU();
                                                                        sparseArray.put(593, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 594:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestHeightAdjustmentConfigurationByECU();
                                                                        sparseArray.put(594, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 595:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestLevelAdjustmentConfigurationByECU();
                                                                        sparseArray.put(595, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 596:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightHeightAdjustmentConfigurationByECU();
                                                                        sparseArray.put(596, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 597:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
                                                                        sparseArray.put(597, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 598:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRearRightLegSupportAdjustmentConfigurationByECU();
                                                                        sparseArray.put(598, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 599:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearMemoryConfiguration();
                                                                        sparseArray.put(599, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    case 600:
                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfigurationByEUC();
                                                                        sparseArray.put(600, Integer.valueOf(bydAutoEventValue.intValue));
                                                                        i = 0;
                                                                        break;
                                                                    default:
                                                                        switch (eventType) {
                                                                            case 731:
                                                                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
                                                                                sparseArray.put(731, Integer.valueOf(bydAutoEventValue.intValue));
                                                                                i = 0;
                                                                                break;
                                                                            case 732:
                                                                                bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
                                                                                sparseArray.put(732, Integer.valueOf(bydAutoEventValue.intValue));
                                                                                i = 0;
                                                                                break;
                                                                            case 733:
                                                                                bydAutoEventValue.intValue = getRearLeftEcuSaveOrRecoverState();
                                                                                sparseArray.put(733, Integer.valueOf(bydAutoEventValue.intValue));
                                                                                i = 0;
                                                                                break;
                                                                            default:
                                                                                switch (eventType) {
                                                                                    case 775:
                                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
                                                                                        sparseArray.put(775, Integer.valueOf(bydAutoEventValue.intValue));
                                                                                        i = 0;
                                                                                        break;
                                                                                    case 776:
                                                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
                                                                                        sparseArray.put(776, Integer.valueOf(bydAutoEventValue.intValue));
                                                                                        i = 0;
                                                                                        break;
                                                                                    case 777:
                                                                                        bydAutoEventValue.intValue = getRearRightEcuSaveOrRecoverState();
                                                                                        sparseArray.put(777, Integer.valueOf(bydAutoEventValue.intValue));
                                                                                        i = 0;
                                                                                        break;
                                                                                    default:
                                                                                        Log.v(TAG, "case fail ! please check out");
                                                                                        i = 0;
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
                    } else {
                        i = 0;
                        bydAutoEventValue.intValue = getSeatVentilatingState2(4);
                        sparseArray.put(496, Integer.valueOf(bydAutoEventValue.intValue));
                    }
                    int length = eventTypes.length;
                    int index = 0;
                    for (int i2 = i; i2 < length; i2++) {
                        int i3 = eventTypes[i2];
                        try {
                            int getNum = ((Integer) sparseArray.get(i3)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
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
        if (eventType == 377) {
            setHudScreenProjectionState(intValue);
            Log.v(TAG, "Setting.SET_INTELLIGENT_PROIECTION_SWITCH_SET:");
        } else if (eventType == 378) {
            setAadsSwitchSate(intValue);
            Log.v(TAG, "Setting.SET_AFS_STATE_SET:");
        } else if (eventType == 391) {
            setHudBrightness(intValue);
            Log.v(TAG, "Setting.SET_BRIGHTNESS_GEAR_SET:");
        } else if (eventType == 392) {
            setBackDoorElectricMode(intValue);
            Log.v(TAG, "Setting.SET_CAR_BACK_DOOR_ELECTRIC_MODE_SET:");
        } else if (eventType == 446) {
            setExhibitionStartEnginePermit(intValue);
            Log.v(TAG, "Setting.SET_EXHIBITION_START_ENGINE_PERMIT_SET:");
        } else if (eventType == 447) {
            factoryResetAll(intValue);
            Log.v(TAG, "SET_FACTORY_RESET_ALL_SET:" + intValue);
        } else if (eventType == 451) {
            setHudHeight(intValue);
            Log.v(TAG, "Setting.SET_HEIGHT_GEAR_SET:");
        } else if (eventType == 452) {
            setHudPattern(intValue);
            Log.v(TAG, "Setting.SET_HUD_MODE_CHOICE_SET:");
        } else if (eventType == 454) {
            setHudMode(intValue);
            Log.v(TAG, "Setting.SET_HUD_MODE_SET:");
        } else if (eventType == 455) {
            setHudState(intValue);
            Log.v(TAG, "Setting.SET_HUD_SWITCH_SET:");
        } else if (eventType == 477) {
            setRestMode(intValue);
            Log.v(TAG, "Setting.SET_NAP_MODE_SET:");
        } else if (eventType != 478) {
            switch (eventType) {
                case 380:
                    setHudRotationAngle(intValue);
                    Log.v(TAG, "Setting.SET_ANGEL_GERA_SET:");
                    break;
                case 383:
                    setAutoRainWiperState(intValue);
                    Log.v(TAG, "Setting.SET_AUTO_RAIN_WIPER_SWITCH_SET:");
                    break;
                case 385:
                    setSteeringWheelHeatingGear(intValue);
                    Log.v(TAG, "Setting.SET_AUTO_STEER_WHEEL_HEATING_GEAR_SET:");
                    break;
                case 387:
                    setSteeringWheelHeatingAutoState(intValue);
                    Log.v(TAG, "Setting.SET_AUTO_STEER_WHEEL_HEATING_MODE_SET:");
                    break;
                case 389:
                    backLightDefaultReset(intValue);
                    Log.v(TAG, "SET_BACK_LIGHT_RESET_SET:" + intValue);
                    break;
                case 394:
                    setBackHomeLightDelayValue(intValue);
                    Log.v(TAG, "Setting.SET_CAR_BACK_HOME_LIGHT_DELAY_VALUE_SET:");
                    break;
                case 396:
                    setRemoteControlDownwindowState(intValue);
                    Log.v(TAG, "Setting.SET_CAR_CTRL_WINDOW_SET:");
                    break;
                case 398:
                    setDriverSeatAutoReturn(intValue);
                    Log.v(TAG, "Setting.SET_CAR_DV_AUTO_RETURN_SET:");
                    break;
                case 400:
                    setAutoExternalRearMirrorFollowUpSwitch(intValue);
                    Log.v(TAG, "Setting.SET_CAR_EXTREARMIR_FOLLOWUP_SWITCH_SET:");
                    break;
                case 402:
                    setLeftHomeLightDelayValue(intValue);
                    Log.v(TAG, "Setting.SET_CAR_LEFT_HOME_LIGHT_DELAY_VALUE_SET:");
                    break;
                case 404:
                    setLockCarRiseWindow(intValue);
                    Log.v(TAG, "Setting.SET_CAR_LOCK_CLOSEWINDOW_SET:");
                    break;
                case 407:
                    setMicroSwitchUnlockWindowState(intValue);
                    Log.v(TAG, "Setting.SET_CAR_LPUNLOCKWINDOW_SET:");
                    break;
                case 409:
                    setMicroSwitchLockWindowState(intValue);
                    Log.v(TAG, "Setting.SET_CAR_MICRO_SWITCH_LOCK_WINDOW_SET:");
                    break;
                case 411:
                    setPM25Power(intValue);
                    Log.v(TAG, "Setting.SET_CAR_PM25_POWER_SET:");
                    break;
                case 413:
                    setPM25SwitchCheck(intValue);
                    Log.v(TAG, "Setting.SET_CAR_PM25_SW_CHECK_SET:");
                    break;
                case 415:
                    setPM25TimeCheck(intValue);
                    Log.v(TAG, "Setting.SET_CAR_PM25_TIME_SET:");
                    break;
                case 418:
                    setRearViewMirrorFlip(intValue);
                    Log.v(TAG, "Setting.SET_CAR_REAR_VIEW_SET:");
                    break;
                case 426:
                    setDynamicSuspensionMode(intValue);
                    Log.v(TAG, "Setting.SET_COMFORT_MODE_CONTROL_SWITCH_SIGNAL_SET:");
                    break;
                case 428:
                    doorsAndWindowDefaultResset(intValue);
                    Log.v(TAG, "SET_DOOR_WINDOW_RESET_SET:" + intValue);
                    break;
                case 430:
                    setChargingPort(intValue);
                    Log.v(TAG, "Setting.SET_DR_CHARGER_PORT_SET:");
                    break;
                case 432:
                    setEnergyFeedback(intValue);
                    Log.v(TAG, "Setting.SET_DR_ENERGY_FB_SET:");
                    break;
                case 434:
                    setSOCTarget(intValue);
                    Log.v(TAG, "Setting.SET_DR_SOC_TARGET_SET:");
                    break;
                case 442:
                    setHudDisplayContent(2, intValue);
                    Log.v(TAG, "Setting.SET_DYNAMIC_NAVI_FUNCTION_STATUS_SET:");
                    break;
                case 449:
                    setDoorknobAutoShrinkState(intValue);
                    Log.v(TAG, "Setting.SET_HANDLE_AUTO_RESET_SET:");
                    break;
                case 464:
                    turnSeatbackrest(3, intValue);
                    Log.v(TAG, "SET_LR_BACKREST_SIGNAL_SET:" + intValue);
                    break;
                case 465:
                    turnSeatHorization(3, intValue);
                    Log.v(TAG, "SET_LR_BALANCE_SIGNAL_SET:" + intValue);
                    break;
                case 466:
                    turnSeatCushion(3, intValue);
                    Log.v(TAG, "SET_LR_BIDET_SIGNAL_SET:" + intValue);
                    break;
                case 467:
                    turnHeadrestTurnHeight(3, intValue);
                    Log.v(TAG, "SET_LR_HEADREST_SIGNAL_SET:" + intValue);
                    break;
                case 468:
                    turnSeatHeight(3, intValue);
                    Log.v(TAG, "SET_LR_HEIGHT_SIGNAL_SET:" + intValue);
                    break;
                case 469:
                    turnSeatCaliperSplint(3, intValue);
                    Log.v(TAG, "SET_LR_LEG_REST_SIGNAL_SET:" + intValue);
                    break;
                case 470:
                    saveSeatParamsAll(intValue, 3);
                    Log.v(TAG, "Setting.SET_LR_MEMORY_LOCATION_SET:");
                    break;
                case 471:
                    resetSeatParams(intValue, 3);
                    Log.v(TAG, "SET_LR_MEMORY_LOCATION_WAKE_SET:" + intValue);
                    break;
                case 480:
                    setOverspeedLock(intValue);
                    Log.v(TAG, "Setting.SET_OVERSPEED_LOCKING_STATE_SET:");
                    break;
                case 481:
                    setScreenRotation(intValue);
                    Log.v(TAG, "Setting.SET_PAD_ROTATION_SET:");
                    break;
                case 484:
                    setPetMode(intValue);
                    Log.v(TAG, "Setting.SET_PET_MODE_SET:");
                    break;
                case 485:
                    lightDefaultReset(intValue);
                    Log.v(TAG, "SET_READING_LIGHT_RESET_SET:" + intValue);
                    break;
                case 487:
                    setRearAcPanelAutoLockTime(intValue);
                    Log.v(TAG, "Setting.SET_REAR_AC_PANEL_LOCK_TIME_SET:");
                    break;
                case 494:
                    rearMirrorDefaultReset(intValue);
                    Log.v(TAG, "SET_REAR_MIRROR_RESET_SET:" + intValue);
                    break;
                case 497:
                    setLockOff(intValue);
                    Log.v(TAG, "Setting.SET_REMOTE_CONTROL_UNLOCKING_SET:");
                    break;
                case 498:
                    carSettingsReset(intValue);
                    Log.v(TAG, "SET_RESTORE_CAR_DEFAULT_VALUES_SET:" + intValue);
                    break;
                case 499:
                    driveSettingsReset(intValue);
                    Log.v(TAG, "SET_RESTORE_DR_DEFAULT_VALUES_SET:" + intValue);
                    break;
                case 500:
                    turnSeatbackrest(2, intValue);
                    Log.v(TAG, "SET_RF_BACKREST_SIGNAL_SET:" + intValue);
                    break;
                case 501:
                    turnSeatHorization(2, intValue);
                    Log.v(TAG, "SET_RF_BALANCE_SIGNAL_SET:" + intValue);
                    break;
                case 502:
                    turnSeatCushion(2, intValue);
                    Log.v(TAG, "SET_RF_BIDET_SIGNAL_SET:" + intValue);
                    break;
                case 503:
                    turnHeadrestTurnHeight(2, intValue);
                    Log.v(TAG, "SET_RF_HEADREST_SIGNAL_SET:" + intValue);
                    break;
                case 504:
                    turnSeatHeight(2, intValue);
                    Log.v(TAG, "SET_RF_HEIGHT_SIGNAL_SET:" + intValue);
                    break;
                case 505:
                    turnSeatCaliperSplint(2, intValue);
                    Log.v(TAG, "SET_RF_LEG_REST_SIGNAL_SET:" + intValue);
                    break;
                case 506:
                    saveSeatParamsAll(intValue, 2);
                    Log.v(TAG, "SET_RF_MEMORY_LOCATION_SET:" + intValue);
                    break;
                case 507:
                    resetSeatParams(intValue, 2);
                    Log.v(TAG, "SET_RF_MEMORY_LOCATION_WAKE_SET:" + intValue);
                    break;
                case 508:
                    turnSeatbackrest(4, intValue);
                    Log.v(TAG, "SET_RR_BACKREST_SIGNAL_SET:" + intValue);
                    break;
                case 509:
                    turnSeatHorization(4, intValue);
                    Log.v(TAG, "SET_RR_BALANCE_SIGNAL_SET:" + intValue);
                    break;
                case 510:
                    turnSeatCushion(4, intValue);
                    Log.v(TAG, "SET_RR_BIDET_SIGNAL_SET:" + intValue);
                    break;
                case 511:
                    turnHeadrestTurnHeight(4, intValue);
                    Log.v(TAG, "SET_RR_HEADREST_SIGNAL_SET:" + intValue);
                    break;
                case 512:
                    turnSeatHeight(4, intValue);
                    Log.v(TAG, "SET_RR_HEIGHT_SIGNAL_SET:" + intValue);
                    break;
                case 513:
                    turnSeatCaliperSplint(4, intValue);
                    Log.v(TAG, "SET_RR_LEG_REST_SIGNAL_SET:" + intValue);
                    break;
                case 514:
                    saveSeatParamsAll(intValue, 4);
                    Log.v(TAG, "SET_RR_MEMORY_LOCATION_SET:" + intValue);
                    break;
                case 515:
                    resetSeatParams(intValue, 4);
                    Log.v(TAG, "SET_RR_MEMORY_LOCATION_WAKE_SET:" + intValue);
                    break;
                case 517:
                    setHudDisplayContent(1, intValue);
                    Log.v(TAG, "Setting.SET_SAFE_DRIVING_ASSIST_STATUS_SET:");
                    break;
                case 518:
                    setHudSafeDriveState(intValue);
                    Log.v(TAG, "Setting.SET_SAFETY_DRIVING_AID_FUSION_SWITCH_SET:");
                    break;
                case 519:
                    autoBackDefaultReset(intValue);
                    Log.v(TAG, "SET_SEAT_SELF_RETURN_RESET_SET:" + intValue);
                    break;
                case 521:
                    setAutoPowerOnState(intValue);
                    Log.v(TAG, "Setting.SET_SMART_ELECTRICITY_SET:");
                    break;
                case 524:
                    setSpeedReminderValue(intValue);
                    Log.v(TAG, "Setting.SET_SPEED_REMINDER_SET:");
                    break;
                case 526:
                    setSteeringWheelHeatingState(intValue);
                    Log.v(TAG, "Setting.SET_STEERING_WHEEL_HEAT_STATE_SET:");
                    break;
                case 527:
                    clockDefaultReset(intValue);
                    Log.v(TAG, "SET_TIME_RESET_SET:" + intValue);
                    break;
                case 528:
                    setKeyVibratorLevel(intValue);
                    Log.v(TAG, "Setting.SET_VIBRATION_SENSING_GEAR_SIGNAL:");
                    break;
                case 529:
                    setKeyVibratorLevel(intValue);
                    Log.v(TAG, "Setting.SET_VIBRATION_SENSING_GEAR_SIGNAL_SET:");
                    break;
                case 530:
                    voiceCtlBackDoor(intValue);
                    Log.v(TAG, "SET_VOICE_CTRL_BACK_DOOR_SET:" + intValue);
                    break;
                case 531:
                    outChargeDefaultReset(intValue);
                    Log.v(TAG, "SET_VTO_RESET_SET:" + intValue);
                    break;
                case 532:
                    setWipeMode(intValue);
                    Log.v(TAG, "Setting.SET_WIPE_SCREEN_MODE_SET:");
                    break;
                case 688:
                    setINSTheme(intValue);
                    Log.v(TAG, "Setting.SETTING_INSTRUMENT_THEME_SET:");
                    break;
                case 752:
                    setRearSeatsFoldOrExpand(intValue);
                    Log.v(TAG, "Setting.SETTING_REAR_SEAT_ONE_KEY_SET:");
                    break;
                case 838:
                    setUiVersion(intValue);
                    Log.v(TAG, "Setting.SETTING_UI_PLATFORM_SET:");
                    break;
                case 878:
                    setACAutoAir(intValue);
                    Log.v(TAG, "Setting.SET_AC_AUTO_AIR_SET:");
                    break;
                case 882:
                    setACBTWind(intValue);
                    Log.v(TAG, "Setting.SET_AC_BT_SET:");
                    break;
                case 887:
                    setACTunnelCycle(intValue);
                    Log.v(TAG, "Setting.SET_AC_TUNNEL_CYCLE_SET:");
                    break;
                case 922:
                    setCanopyAutomaticDimming(intValue);
                    break;
                case 926:
                    setSkyCurtainPowerOffAndDim(intValue);
                    break;
                case 927:
                    setOpeningAngleInformationSetOnTheR(intValue);
                    break;
                case 933:
                    setBackDoorOpenedHeight(intValue);
                    break;
                case 1029:
                    setMassageMode(2, intValue);
                    Log.v(TAG, "setMassageMode2:" + intValue);
                    break;
                case 1030:
                    setMassageLevel(2, intValue);
                    Log.v(TAG, "setMassageLevel2:" + intValue);
                    break;
                case 1042:
                    setSeatHeatingState(1, intValue);
                    Log.v(TAG, "setSeatHeatingStateDriver:" + intValue);
                    break;
                case 1045:
                    setSeatVentilatingState(1, intValue);
                    Log.v(TAG, "setSeatVentilatingStateDriver:" + intValue);
                    break;
                case 1157:
                    setIALColor(2, intValue, 1);
                    Log.v(TAG, "setIALColorFrontBack:" + intValue);
                    break;
                case 1161:
                    setIALColor(1, intValue, 1);
                    Log.v(TAG, "setIALColorFront:" + intValue);
                    break;
                case 1172:
                    setIALArea(intValue, 0);
                    Log.v(TAG, "Setting.SET_INTERIOR_ATMOSPHERE_LAMP_AREA_SET:");
                    break;
                case 1174:
                    setIALBrightness(5, intValue, 1);
                    Log.v(TAG, "SET_INTERIOR_ATMOSPHERE_LAMP_BRIGHTNESS_SET:" + intValue);
                    break;
                case 1177:
                    int lastArea = this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
                    setIALColor(lastArea, intValue, 0);
                    Log.v(TAG, "setSeatVentilatingStateDriver:" + intValue);
                    break;
                case 1178:
                    setIALArea(0, intValue);
                    Log.v(TAG, "Setting.SET_INTERIOR_ATMOSPHERE_LAMP_SOURCE_SET:");
                    break;
                case 1184:
                    setMode(intValue);
                    Log.v(TAG, "Setting.SET_IVI_SCENE_MODE_SET:");
                    break;
                case 1188:
                    languageDefaultReset(intValue);
                    Log.v(TAG, "SET_LANGUAGE_RESET_SET:" + intValue);
                    break;
                case 1190:
                    setLanguage(intValue);
                    Log.v(TAG, "Setting.SET_LANGUAGE_TYPE_SET:");
                    break;
                case 1201:
                    setMassageMode(3, intValue);
                    Log.v(TAG, "setMassageLevel3:" + intValue);
                    break;
                case 1209:
                    setMassageMode(1, intValue);
                    Log.v(TAG, "setMassageMode1:" + intValue);
                    break;
                case 1210:
                    setMassageLevel(1, intValue);
                    Log.v(TAG, "setMassageLevel1:" + intValue);
                    break;
                case 1257:
                    setAmbientMusicModeState(0, intValue);
                    Log.v(TAG, "SET_MUSIC_MODE_STATE_SET: " + intValue);
                    break;
                case 1271:
                    setSeatHeatingState(2, intValue);
                    Log.v(TAG, "setSeatHeatingStatePassenger:" + intValue);
                    break;
                case 1274:
                    setSeatVentilatingState(2, intValue);
                    Log.v(TAG, "setSeatVentilatingStatePassenger:" + intValue);
                    break;
                case 1284:
                    setSeatHeatingState(3, intValue);
                    Log.v(TAG, "setSeatHeatingStateLeft:" + intValue);
                    break;
                case 1291:
                    setSeatVentilatingState(3, intValue);
                    Log.v(TAG, "setSeatVentilatingStateLeft:" + intValue);
                    break;
                case 1295:
                    setSeatHeatingState(4, intValue);
                    Log.v(TAG, "setSeatHeatingStateRight:" + intValue);
                    break;
                case 1302:
                    setSeatVentilatingState(4, intValue);
                    Log.v(TAG, "setSeatVentilatingStateRight:" + intValue);
                    break;
                case 1314:
                    setMassageMode(4, intValue);
                    Log.v(TAG, "setMassageLevel4:" + intValue);
                    break;
                case 1386:
                    setMassageLevel(3, intValue);
                    Log.v(TAG, "setMassageMode3:" + intValue);
                    break;
                case 1387:
                    setMassageLevel(4, intValue);
                    Log.v(TAG, "setMassageMode4:" + intValue);
                    break;
                case 1411:
                    setSafeBeltWarnState(intValue);
                    Log.v(TAG, "Safety.SAFETY_BELT_ALARM_SWITCH_SET:");
                    break;
                case 1412:
                    setComfortableParkingState(intValue);
                    Log.v(TAG, "Safety.SAFETY_BELT_MSR_STATE:");
                    break;
                case 1413:
                    setMotorPretensionerSeatbeltState(intValue);
                    Log.v(TAG, "Safety.SAFETY_BELT_MSR_STATE_SET:");
                    break;
                case 1699:
                    setComSpeedUpState(intValue);
                    break;
                case 1704:
                    setComSpeedDownState(intValue);
                    break;
                case 1709:
                    setRainAutoLockWindowState(intValue);
                    break;
                case 1724:
                    setDisinfState(intValue);
                    break;
                case 1728:
                    setRemenberPowerOffState(intValue);
                    break;
                case 1740:
                    setHSpeedAutoCloseState(intValue);
                    break;
                case 2004:
                    turnRearviewMirror(intValue);
                    Log.v(TAG, "turnRearviewMirror:" + intValue);
                    break;
                case 2087:
                    setTrayState(intValue);
                    break;
                case 2135:
                    turnSteeringWheel(intValue);
                    Log.v(TAG, "turnSteeringWheel:" + intValue);
                    break;
                case 2548:
                    newEnergyDefaultReset(intValue);
                    break;
                case 2658:
                    setAutoPowerOffState(intValue);
                    break;
                case 2811:
                    setAmbientMulticolorState(0, intValue);
                    break;
                case 2844:
                    setIALBrightness(5, intValue, 1);
                    Log.v(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP_BRIGHTNESS:" + intValue);
                    break;
                case 2845:
                    setIALColor(5, intValue, 1);
                    Log.v(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP:" + intValue);
                    break;
                case 2846:
                    setIALBrightness(4, intValue, 1);
                    Log.v(TAG, "Light.LIGHT_STAR_SKY_ATMOSPHERE_LAMP_BRIGHTNESS:" + intValue);
                    break;
                case 2847:
                    setIALColor(4, intValue, 1);
                    Log.v(TAG, "LIGHT_STAR_SKY_ATMOSPHERE_LAMP:" + intValue);
                    break;
                case 3159:
                case 3233:
                    setBrakePedalMode(intValue);
                    break;
                case 3162:
                    setComfortableParkingState(intValue);
                    break;
                case 3172:
                    intelligentDriveDefaultReset(intValue);
                    break;
                case 3254:
                    laneAssistDriveDefaultReset(intValue);
                    break;
                case 3280:
                    safeAssistDefaultReset(intValue);
                    break;
                case 3373:
                    set5LevelSeatHeatingLevel(1, intValue);
                    break;
                case 3375:
                    set5LevelSeatHeatingState(1, intValue);
                    break;
                case 3377:
                    set5LevelSeatVentilatingMode(1, intValue);
                    break;
                case 3379:
                    set5LevelSeatVentilatingLevel(1, intValue);
                    break;
                case 3381:
                    set5LevelSeatVentilatingState(1, intValue);
                    break;
                case 3427:
                    set5LevelSeatHeatingLevel(2, intValue);
                    break;
                case 3429:
                    set5LevelSeatHeatingState(2, intValue);
                    break;
                case 3431:
                    set5LevelSeatVentilatingMode(2, intValue);
                    break;
                case 3433:
                    set5LevelSeatVentilatingLevel(2, intValue);
                    break;
                case 3435:
                    set5LevelSeatVentilatingState(2, intValue);
                    break;
                case 3468:
                    set5LevelSeatHeatingLevel(3, intValue);
                    break;
                case 3470:
                    set5LevelSeatHeatingState(3, intValue);
                    break;
                case 3472:
                    set5LevelSeatVentilatingMode(3, intValue);
                    break;
                case 3474:
                    set5LevelSeatVentilatingLevel(3, intValue);
                    break;
                case 3476:
                    set5LevelSeatVentilatingState(3, intValue);
                    break;
                case 3491:
                    set5LevelSeatHeatingLevel(4, intValue);
                    break;
                case 3493:
                    set5LevelSeatHeatingState(4, intValue);
                    break;
                case 3495:
                    set5LevelSeatVentilatingMode(4, intValue);
                    break;
                case 3497:
                    set5LevelSeatVentilatingLevel(4, intValue);
                    break;
                case 3499:
                    set5LevelSeatVentilatingState(4, intValue);
                    break;
                case 3508:
                    carSettingsDefaultReset(intValue);
                    break;
                case 3521:
                    setIntelligentAcAreaState(intValue);
                    break;
                case 3558:
                    setAcWarmMode(intValue);
                    break;
                case 3588:
                    setEngineSoundType(intValue);
                    break;
                case 4637:
                    setElectricTailAutomaticModeSwitchState(intValue);
                    break;
                case 4638:
                    setElectricTailAutoModeVoiceSwitchState(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORKING_STATUS_SET /* 4715 */:
                    setRefrigeratorWorkMode(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_POWEROFF_MEMORY_SET /* 4716 */:
                    setRemberPowerOffState(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS_SET /* 4717 */:
                    setSterilizationState(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_TEMP_REGULATION_SET /* 4718 */:
                    setRefrigeratorWorkTemperature(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_PWOEROFF_SWITCH_SET /* 4719 */:
                    setDelayPowerDownState(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWEROFF_TIME_SET /* 4720 */:
                    setDelayPowerDownStateTime(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_REFRIGERATOR_KID_LOCK_STATE_SET /* 4721 */:
                    setFridgeChildLock(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DOOR_STATUS_SET /* 4722 */:
                    setFridgeDoorState(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE_SET /* 4723 */:
                    setDelayPowerDownTime(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_SCREEN_TRIGGER_SOURCE_SET /* 4724 */:
                    setIceBoxTriggerSource(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_WORK_MODE_SET /* 4741 */:
                    setRefrigWorkMode(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_LEFT_BACKREST_SIGNAL_SET /* 4821 */:
                    setThirdRowSeatLeftBackrest(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_THIRD_ROW_SEAT_RIGHT_BACKREST_SIGNAL_SET /* 4822 */:
                    setThirdRowSeatRightBackrest(intValue);
                    break;
                default:
                    switch (eventType) {
                        case 420:
                            setRemoteControlUpwindowState(intValue);
                            Log.v(TAG, "Setting.SET_CAR_REMOTE_CTRL_UPWINDOW_SET:");
                            break;
                        case 421:
                            setSteerPositionAutoReturn(intValue);
                            Log.v(TAG, "Setting.SET_CAR_ST_AUTO_RETURN_SET:");
                            break;
                        case 422:
                            setHeadlampLevel(intValue);
                            Log.v(TAG, "Setting.SET_CMD_HEADLAMP_HEIGHT_LEVEL_SET:");
                            break;
                        default:
                            switch (eventType) {
                                case 436:
                                    setSteerAssis(intValue);
                                    Log.v(TAG, "Setting.SET_DR_ST_ASSIS_SET:");
                                    break;
                                case 437:
                                    turnSeatbackrest(1, intValue);
                                    Log.v(TAG, "SET_DRIVER_SEAT_BACKREST_SIGNAL_SET:" + intValue);
                                    break;
                                case 438:
                                    turnSeatHorization(1, intValue);
                                    Log.v(TAG, "SET_DRIVER_SEAT_BALANCE_SIGNAL_SET:" + intValue);
                                    break;
                                case 439:
                                    turnSeatCushion(1, intValue);
                                    Log.v(TAG, "SET_DRIVER_SEAT_BIDET_SIGNAL_SET:" + intValue);
                                    break;
                                case 440:
                                    turnSeatHeight(1, intValue);
                                    Log.v(TAG, "SET_DRIVER_SEAT_HEIGHT_SIGNAL_SET:" + intValue);
                                    break;
                                default:
                                    switch (eventType) {
                                        case 457:
                                            setInsideLightDoorState(intValue);
                                            Log.v(TAG, "Setting.SET_INSIDE_LIGHT_STATE_SET:");
                                            break;
                                        case 458:
                                            setLanternLanguageMode(intValue);
                                            Log.v(TAG, "Setting.SET_LANTERN_LANGUAGE_MODE_SET:");
                                            break;
                                        case 459:
                                            turnHeadrestTurnHeight(1, intValue);
                                            Log.v(TAG, "SET_LF_HEADREST_SIGNAL_SET:" + intValue);
                                            break;
                                        case 460:
                                            turnSeatCaliperSplint(1, intValue);
                                            Log.v(TAG, "SET_LF_LEG_REST_SIGNAL_SET:" + intValue);
                                            break;
                                        case 461:
                                            saveSeatParamsAll(intValue, 1);
                                            Log.v(TAG, "Setting.SET_LF_MEMORY_LOCATION_SET:");
                                            break;
                                        case 462:
                                            resetSeatParams(intValue, 1);
                                            Log.v(TAG, "SET_LF_LEG_REST_SIGNAL_SET:" + intValue);
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
            setHudNavigationState(intValue);
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

    public int setHudPattern(int mode) {
        Log.v(TAG, "setHudPattern is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudPattern(mode);
    }

    public int getHudSafeDriveState() {
        Log.v(TAG, "getHudSafeDriveState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudSafeDriveState();
    }

    public int setHudSafeDriveState(int state) {
        Log.v(TAG, "setHudSafeDriveState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudSafeDriveState(state);
    }

    public int getHudNavigationState() {
        Log.v(TAG, "getHudNavigationState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudNavigationState();
    }

    public int setHudNavigationState(int state) {
        Log.v(TAG, "setHudNavigationState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudNavigationState(state);
    }

    public int getHudScreenProjectionState() {
        Log.v(TAG, "getHudScreenProjectionState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getHudScreenProjectionState();
    }

    public int setHudScreenProjectionState(int state) {
        Log.v(TAG, "setHudScreenProjectionState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setHudScreenProjectionState(state);
    }

    public int getSeatHeatingState2(int seatId) {
        Log.v(TAG, "getSeatHeatingState2 is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateRearLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingStateRearRight();
        }
        return 0;
    }

    public int getSeatHeatingNotL1PState() {
        Log.v(TAG, "getSeatHeatingNotL1PState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingNotL1PState();
    }

    public int getSeatVentilatingState2(int seatId) {
        Log.v(TAG, "getSeatVentilatingState2 is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingState2RearLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSeatHeatingState2RearRight();
        }
        return 0;
    }

    public int getSeatVentilatingNotL1PState() {
        Log.v(TAG, "getSeatVentilatingNotL1PState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatVentilatingNotL1PState();
    }

    public int get5LevelSeatVentilatingState(int seatId) {
        Log.v(TAG, "get5LevelSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingStateDriver();
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingStateColipot();
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingStateLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingStateRight();
        }
        return 0;
    }

    public int set5LevelSeatVentilatingState(int seatId, int state) {
        Log.v(TAG, "set5LevelSeatVentilatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingStateDriver(state);
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingStateColipot(state);
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingStateLeft(state);
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingStateRight(state);
        }
        return 0;
    }

    public int get5LevelSeatHeatingState(int seatId) {
        Log.v(TAG, "get5LevelSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingStateDriver();
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingStateColipot();
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingStateLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingStateRight();
        }
        return 0;
    }

    public int set5LevelSeatHeatingState(int seatId, int state) {
        Log.v(TAG, "set5LevelSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingStateDriver(state);
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingStateColipot(state);
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingStateLeft(state);
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingStateRight(state);
        }
        return 0;
    }

    public int get5LevelSeatVentilatingMode(int seatId) {
        Log.v(TAG, "get5LevelSeatVentilatingMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingModeDriver();
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingModePassenger();
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingModeLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingModeRight();
        }
        return 0;
    }

    public int set5LevelSeatVentilatingMode(int seatId, int state) {
        Log.v(TAG, "set5LevelSeatVentilatingMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingModeDriver(state);
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingModePassenger(state);
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingModeLeft(state);
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingModeRight(state);
        }
        return 0;
    }

    public int get5LevelSeatHeatingMode(int seatId) {
        Log.v(TAG, "get5LevelSeatHeatingMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingModeDriver();
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingModePassenger();
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingModeLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingModeRight();
        }
        return 0;
    }

    public int set5LevelSeatHeatingMode(int seatId, int state) {
        Log.v(TAG, "set5LevelSeatHeatingMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingModeDriver(state);
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingModePassenger(state);
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingModeLeft(state);
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingModeRight(state);
        }
        return 0;
    }

    public int get5LevelSeatVentilatingLevel(int seatId) {
        Log.v(TAG, "get5LevelSeatVentilatingLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingLevelDriver();
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingLevelPassenger();
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingLevelLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatVentilatingLevelRight();
        }
        return 0;
    }

    public int set5LevelSeatVentilatingLevel(int seatId, int value) {
        Log.v(TAG, "set5LevelSeatVentilatingLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingDriver(value);
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingPassenger(value);
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingLeft(value);
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatVentilatingRight(value);
        }
        return 0;
    }

    public int get5LevelSeatHeatingLevel(int seatId) {
        Log.v(TAG, "get5LevelSeatHeatingLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingLevelDriver();
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingLevelPassenger();
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingLevelLeft();
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().get5LevelSeatHeatingLevelRight();
        }
        return 0;
    }

    public int set5LevelSeatHeatingLevel(int seatId, int value) {
        Log.v(TAG, "set5LevelSeatHeatingLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (seatId == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingDriver(value);
        }
        if (seatId == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingPassenger(value);
        }
        if (seatId == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingLeft(value);
        }
        if (seatId == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().set5LevelSeatHeatingRight(value);
        }
        return 0;
    }

    public int setSteeringWheelHeatingGear(int value) {
        Log.v(TAG, "setSteeringWheelHeatingGear is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingGear(value);
    }

    public int getSteeringWheelHeatingGear() {
        Log.v(TAG, "getSteeringWheelHeatingGear is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatingGear();
    }

    public int setSteeringWheelHeatingAutoState(int state) {
        Log.v(TAG, "setSteeringWheelHeatingAutoState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSteeringWheelHeatingAutoState(state);
    }

    public int getSteeringWheelHeatingAutoState() {
        Log.v(TAG, "getSteeringWheelHeatingAutoState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        return this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelHeatingAutoState();
    }

    public int getEcuMassageLevel(int type) {
        Log.v(TAG, "getEcuMassageLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (type == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEcuMassageLevelLeft();
        }
        if (type == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEcuMassageLevelRight();
        }
        return 0;
    }

    public int getEcuMassageMode(int type) {
        Log.v(TAG, "getEcuMassageMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (type == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEcuMassageModeLeft();
        }
        if (type == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getEcuMassageModeRight();
        }
        return 0;
    }

    public int setHsMassageState(int type, int state) {
        Log.v(TAG, "setHsMassageState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHsMassageStateDriver(state);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHsMassageStatePassenger(state);
        }
        if (type == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHsMassageStateLeft(state);
        }
        if (type == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().setHsMassageStateRight(state);
        }
        return 0;
    }

    public int getHsMassageState(int type) {
        Log.v(TAG, "getHsMassageState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHsMassageStateDriver();
        }
        if (type == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHsMassageStatePassenger();
        }
        if (type == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getHsMassageStateLeft();
        }
        if (type == 4) {
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

    public int setKeyVibratorLevel(int level) {
        Log.v(TAG, " setKeyVibratorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setKeyVibratorLevel(level);
    }

    public int setMode(int mode) {
        Log.v(TAG, "setMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMode(mode);
    }

    public int setRestMode(int state) {
        Log.v(TAG, "setRestMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRestMode(state);
    }

    public int setPetMode(int state) {
        Log.v(TAG, "setPetMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPetMode(state);
    }

    public int setWipeMode(int state) {
        Log.v(TAG, "setWipeMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWipeMode(state);
    }

    public int setMeditationMode(int state) {
        Log.v(TAG, "setMeditationMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMeditationMode(state);
    }

    public int setCamping(int state) {
        Log.v(TAG, "setCamping is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCamping(state);
    }

    public int getSafeBeltWarnState() {
        Log.v(TAG, "getSafeBeltWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSafeBeltWarnState();
    }

    public int getMainSeatState() {
        Log.v(TAG, "getMainSeatState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getMainSeatState();
    }

    public int setSafeBeltWarnState(int state) {
        Log.v(TAG, "setSafeBeltWarnState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltWarnState(state);
    }

    public int carSettingsReset(int state) {
        Log.v(TAG, "carSettingsReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int driveSettingsReset(int state) {
        Log.v(TAG, "driveSettingsReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().driveSettingsReset(state);
    }

    public int carSettingsDefaultReset(int state) {
        Log.v(TAG, "carSettingsDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int newEnergyDefaultReset(int state) {
        Log.v(TAG, "newEnergyDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().newEnergyDefaultReset(state);
    }

    public int doorsAndWindowDefaultResset(int state) {
        Log.v(TAG, "doorsAndWindowDefaultResset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int locksDefaultReset(int state) {
        Log.v(TAG, "locksDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int rearMirrorDefaultReset(int state) {
        Log.v(TAG, "rearMirrorDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int autoBackDefaultReset(int state) {
        Log.v(TAG, "autoBackDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int carHealthDefaultReset(int state) {
        Log.v(TAG, "carHealthDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().carHealthDefaultReset(state);
    }

    public int ialDefaultReset(int state) {
        Log.v(TAG, "ialDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int lightDefaultReset(int state) {
        Log.v(TAG, "lightDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int backLightDefaultReset(int state) {
        Log.v(TAG, "backLightDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int safeAssistDefaultReset(int state) {
        Log.v(TAG, "safeAssistDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int intelligentDriveDefaultReset(int state) {
        Log.v(TAG, "intelligentDriveDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int laneAssistDriveDefaultReset(int state) {
        Log.v(TAG, "laneAssistDriveDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int equalizerDefaultReset(int state) {
        Log.v(TAG, "equalizerDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int soundFiledDefaultReset(int state) {
        Log.v(TAG, "soundFiledDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int clockDefaultReset(int state) {
        Log.v(TAG, "clockDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().clockDefaultReset(state);
    }

    public int languageDefaultReset(int state) {
        Log.v(TAG, "languageDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().languageDefaultReset(state);
    }

    public int unitDefaultReset(int state) {
        Log.v(TAG, "unitDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        return 0;
    }

    public int outChargeDefaultReset(int state) {
        Log.v(TAG, "outChargeDefaultReset is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().outChargeDefaultReset(state);
    }

    public int setUiVersion(int state) {
        Log.v(TAG, "setUiVersion is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setUiVersion(state);
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

    public int setScreenRotation(int orientation) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setScreenRotation mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setScreenRotation(orientation);
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

    public int setScreenRotationLock(int state) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setScreenRotationLock mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setScreenRotationLock(state);
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

    public int setLanguage(int value) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setLanguage mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLanguage(value);
    }

    public int setInternationalLanguage(int value) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setInternationalLanguage mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setInternationalLanguage(value);
    }

    public int setPabState(int state) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setPabState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPabState(state);
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

    public int getSupportSeatMassage(int area) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSupportSeatMassage mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSupportSeatMassage(area);
    }

    public int getSupportSeatMassageCombine(int area) {
        Log.d(TAG, "getSupportSeatMassageCombine, area:" + area);
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSupportSeatMassageCombine mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        int supportSeatMassage = getSupportSeatMassage(area);
        int massageConfigForECU = getMassageConfigurationForECU(area);
        Log.d(TAG, "getSupportSeatMassageCombine, area:" + area + ", supportSeatMassage:" + supportSeatMassage + ", massageConfigForECU:" + massageConfigForECU);
        if (supportSeatMassage > 0) {
            return supportSeatMassage;
        }
        if (massageConfigForECU > 0) {
            return massageConfigForECU;
        }
        if (area == 3) {
            int value486 = getLrNormalMassageConfig();
            int value4e2 = getLrNormal2MassageConfig();
            Log.d(TAG, "getSupportSeatMassageCombine, area:" + area + ", value486:" + value486 + ", value4e2:" + value4e2);
            if (value486 > 0) {
                return value486;
            }
            if (value4e2 > 0) {
                return value4e2;
            }
        } else if (area == 4) {
            int value4862 = getRrNormalMassageConfig();
            int value4ec = getRrNormal2MassageConfig();
            Log.d(TAG, "getSupportSeatMassageCombine, area:" + area + ", value486:" + value4862 + ", value4ec:" + value4ec);
            if (value4862 > 0) {
                return value4862;
            }
            if (value4ec > 0) {
                return value4ec;
            }
        }
        return 0;
    }

    public int getMassageConfigurationForECU(int area) {
        Log.d(TAG, "getMassageConfigurationForECU, area:" + area);
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getMassageConfigurationForECU mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 1) {
            return getMainNormalMassageConfig();
        }
        if (area == 2) {
            return getPasNormalMassageConfig();
        }
        if (area == 3) {
            return getLeftRearMassageChairConfigurationForEcu();
        }
        if (area == 4) {
            return getRightRearMassageChairConfigurationForEcu();
        }
        return 0;
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

    public int getAmbientMusicModeState(int area) {
        Log.v(TAG, "getAmbientMusicModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAmbientMusicModeState", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMusicModeState(area);
    }

    public int setAmbientMusicModeState(int area, int state) {
        Log.v(TAG, "setAmbientMusicModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODESTATE, Integer.valueOf(area), Integer.valueOf(state));
            Log.v(TAG, "setAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMusicModeState(area, state);
    }

    public int getAmbientMusicMode(int area) {
        Log.v(TAG, "getAmbientMusicMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAmbientMusicMode", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMusicMode(area);
    }

    public int setAmbientMusicMode(int area, int value) {
        Log.v(TAG, "setAmbientMusicMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODE, Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMusicMode(area, value);
    }

    public int getAmbientMulticolorState(int area) {
        Log.v(TAG, "getAmbientMulticolorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorState", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMulticolorState(area);
    }

    public int setAmbientMulticolorState(int area, int value) {
        Log.v(TAG, "setAmbientMulticolorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE, Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMulticolorState(area, value);
    }

    public int getAmbientMulticolorMode(int area) {
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorMode", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getAmbientMulticolorMode(area);
    }

    public int setAmbientMulticolorMode(int area, int value) {
        Log.v(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE, Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAmbientMulticolorMode(area, value);
    }

    public int get1LevelSeatHeatingState(int seatId) {
        Log.v(TAG, "get1LevelSeatHeatingState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().get1LevelSeatHeatingState(seatId);
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

    public int notifyEnterTunnel(int value) {
        Log.v(TAG, "notifyEnterTunnel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().notifyEnterTunnel(value);
    }

    public int notifyTunnelLength(int value) {
        Log.v(TAG, "notifyTunnelLength is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().notifyTunnelLength(value);
    }

    public int setBleOpenDriverDoorState(int state) {
        Log.v(TAG, "setBleOpenDriverDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBleOpenDriverDoorState(state);
    }

    public int setBrakeCloseDoor(int state) {
        Log.v(TAG, "setBrakeCloseDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBrakeCloseDoor(state);
    }

    public int setCampingBlanceState(int state) {
        Log.v(TAG, "setCampingBlanceState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCampingBlanceState(state);
    }

    public int setCheckSum(int sum) {
        Log.v(TAG, "setCheckSum is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCheckSum(sum);
    }

    public int setCloseDoorLevel(int level) {
        Log.v(TAG, "setCloseDoorLevel is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCloseDoorLevel(level);
    }

    public int setCvcMode(int mode) {
        Log.v(TAG, "setCvcMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCvcMode(mode);
    }

    public int setDampState(int state) {
        Log.v(TAG, "setDampState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDampState(state);
    }

    public int setDyHeightByVoice(int state) {
        Log.v(TAG, "setDyHeightByVoice is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightByVoice(state);
    }

    public int setDyHeightToggleState(int state) {
        Log.v(TAG, "setDyHeightToggleState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightToggleState(state);
    }

    public int setDyHeightValue(int value) {
        Log.v(TAG, "setDyHeightValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDyHeightValue(value);
    }

    public int setIntelLockOpenDriverDoor(int state) {
        Log.v(TAG, "setIntelLockOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIntelLockOpenDriverDoor(state);
    }

    public int setLiftModeState(int state) {
        Log.v(TAG, "setLiftModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLiftModeState(state);
    }

    public int setMessageCounter(int count) {
        Log.v(TAG, "setMessageCounter is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMessageCounter(count);
    }

    public int setNfcOpenDriverDoor(int state) {
        Log.v(TAG, "setNfcOpenDriverDoor is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setNfcOpenDriverDoor(state);
    }

    public int setOtaMode(int mode) {
        Log.v(TAG, "setOtaMode is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOtaMode(mode);
    }

    public int setRearSeatGreetState(int state) {
        Log.v(TAG, "setRearSeatGreetState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearSeatGreetState(state);
    }

    public int setSafeBeltCloseDriveDoorState(int state) {
        Log.v(TAG, "setSafeBeltCloseDriveDoorState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSafeBeltCloseDriveDoorState(state);
    }

    public int setTailBoxTakeState(int state) {
        Log.v(TAG, "setTailBoxTakeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTailBoxTakeState(state);
    }

    public int setTrailerLightCheckState(int state) {
        Log.v(TAG, "setTrailerLightCheckState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTrailerLightCheckState(state);
    }

    public int setTrailerModeState(int state) {
        Log.v(TAG, "setTrailerModeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTrailerModeState(state);
    }

    public int setVspState(int state) {
        Log.v(TAG, "setVSPState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setVspState(state);
    }

    public int setWelcomeState(int state) {
        Log.v(TAG, "setWelcomeState is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWelcomeState(state);
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

    public int setCallState(int state) {
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            Log.v(TAG, " setCallState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCallState(state);
    }

    public int setScreenAngleValue(int arg0) {
        Log.i(TAG, "setScreenAngleValue is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setScreenAngleValue", Integer.valueOf(arg0));
            Log.v(TAG, "setScreenAngleValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setScreenAngleValueS(arg0);
    }

    public int setBsisCollisionStateS(int arg0) {
        Log.i(TAG, "setBsisCollisionStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setBsisCollisionStateS", Integer.valueOf(arg0));
            Log.v(TAG, "setBsisCollisionStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setBsisCollisionStateS(arg0);
    }

    public int setLeftRseStateS(int arg0) {
        Log.i(TAG, "setLeftRseStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLeftRseStateS", Integer.valueOf(arg0));
            Log.v(TAG, "setLeftRseStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeftRseStateS(arg0);
    }

    public int setLeftRseSwitchCommandS(int arg0) {
        Log.i(TAG, "setLeftRseSwitchCommandS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLeftRseSwitchCommandS", Integer.valueOf(arg0));
            Log.v(TAG, "setLeftRseSwitchCommandS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLeftRseSwitchCommandS(arg0);
    }

    public int setLrseChildAreaStateS(int arg0) {
        Log.i(TAG, "setLrseChildAreaStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildAreaStateS", Integer.valueOf(arg0));
            Log.v(TAG, "setLrseChildAreaStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildAreaStateS(arg0);
    }

    public int setLrseChildModeCode1stByteS(int arg0) {
        Log.i(TAG, "setLrseChildModeCode1stByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeCode1stByteS", Integer.valueOf(arg0));
            Log.v(TAG, "setLrseChildModeCode1stByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode1stByteS(arg0);
    }

    public int setLrseChildModeCode2ndByteS(int arg0) {
        Log.i(TAG, "setLrseChildModeCode2ndByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeCode2ndByteS", Integer.valueOf(arg0));
            Log.v(TAG, "setLrseChildModeCode2ndByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode2ndByteS(arg0);
    }

    public int setLrseChildModeCode3rdByteS(int arg0) {
        Log.i(TAG, "setLrseChildModeCode3rdByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeCode3rdByteS", Integer.valueOf(arg0));
            Log.v(TAG, "setLrseChildModeCode3rdByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode3rdByteS(arg0);
    }

    public int setLrseChildModeCode4thByteS(int arg0) {
        Log.i(TAG, "setLrseChildModeCode4thByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeCode4thByteS", Integer.valueOf(arg0));
            Log.v(TAG, "setLrseChildModeCode4thByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeCode4thByteS(arg0);
    }

    public int setLrseChildModeOpenTimeS(int arg0) {
        Log.i(TAG, "setLrseChildModeOpenTimeS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setLrseChildModeOpenTimeS", Integer.valueOf(arg0));
            Log.v(TAG, "setLrseChildModeOpenTimeS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setLrseChildModeOpenTimeS(arg0);
    }

    public int setMoisAssembleStateS(int arg0) {
        Log.i(TAG, "setMoisAssembleStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setMoisAssembleStateS", Integer.valueOf(arg0));
            Log.v(TAG, "setMoisAssembleStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setMoisAssembleStateS(arg0);
    }

    public int setRightRseStateS(int arg0) {
        Log.i(TAG, "setRightRseStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRightRseStateS", Integer.valueOf(arg0));
            Log.v(TAG, "setRightRseStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRightRseStateS(arg0);
    }

    public int setRightRseSwitchCommandS(int arg0) {
        Log.i(TAG, "setRightRseSwitchCommandS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRightRseSwitchCommandS", Integer.valueOf(arg0));
            Log.v(TAG, "setRightRseSwitchCommandS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRightRseSwitchCommandS(arg0);
    }

    public int setRrseChildAreaStateS(int arg0) {
        Log.i(TAG, "setRrseChildAreaStateS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildAreaStateS", Integer.valueOf(arg0));
            Log.v(TAG, "setRrseChildAreaStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildAreaStateS(arg0);
    }

    public int setRrseChildModeCode1stByteS(int arg0) {
        Log.i(TAG, "setRrseChildModeCode1stByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeCode1stByteS", Integer.valueOf(arg0));
            Log.v(TAG, "setRrseChildModeCode1stByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode1stByteS(arg0);
    }

    public int setRrseChildModeCode2ndByteS(int arg0) {
        Log.i(TAG, "setRrseChildModeCode2ndByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeCode2ndByteS", Integer.valueOf(arg0));
            Log.v(TAG, "setRrseChildModeCode2ndByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode2ndByteS(arg0);
    }

    public int setRrseChildModeCode3rdByteS(int arg0) {
        Log.i(TAG, "setRrseChildModeCode3rdByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeCode3rdByteS", Integer.valueOf(arg0));
            Log.v(TAG, "setRrseChildModeCode3rdByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode3rdByteS(arg0);
    }

    public int setRrseChildModeCode4thByteS(int arg0) {
        Log.i(TAG, "setRrseChildModeCode4thByteS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeCode4thByteS", Integer.valueOf(arg0));
            Log.v(TAG, "setRrseChildModeCode4thByteS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeCode4thByteS(arg0);
    }

    public int setRrseChildModeOpenTimeS(int arg0) {
        Log.i(TAG, "setRrseChildModeOpenTimeS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setRrseChildModeOpenTimeS", Integer.valueOf(arg0));
            Log.v(TAG, "setRrseChildModeOpenTimeS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRrseChildModeOpenTimeS(arg0);
    }

    public int setSwsMediumRelatedKeysS(int arg0) {
        Log.i(TAG, "setSwsMediumRelatedKeysS is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage("setSwsMediumRelatedKeysS", Integer.valueOf(arg0));
            Log.v(TAG, "setSwsMediumRelatedKeysS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSwsMediumRelatedKeysS(arg0);
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
        int value437 = this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByGCtrl();
        int value494 = this.mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
        Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByGCtrl, value437:" + value437 + ", value494:" + value494);
        if (value494 > 0 && value494 < 4) {
            return value494;
        }
        return value437;
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

    public int setIviProfileSettingsR(int arg0) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setIviProfileSettingsR(arg0);
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

    public int setDriverSeatState(int value) {
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
        return this.mTsManagerImpl.getSettingAdapterManager().setDriverSeatState(value);
    }

    public int setAdasRest(int[] state) {
        Log.v(TAG, " setAdasRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setAdasRest(state);
    }

    public int setDlinkRest(int[] state) {
        Log.v(TAG, " setDlinkRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDlinkRest(state);
    }

    public int setCarsettingRest(int[] state) {
        Log.v(TAG, " setCarsettingRest.in");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCarsettingRest(state);
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

    public int setInsideLightState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setInsideLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setInsideLightState(state);
    }

    public int setOutsideLightStateScene(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setOutsideLightStateScene mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOutsideLightStateScene(state);
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

    public int setRearviewMirrorIsTurnedState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearviewMirrorIsTurnedState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearviewMirrorIsTurnedState(state);
    }

    public int voiceOpenMirrorlsTurnedState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " voiceOpenMirrorlsTurnedState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().voiceOpenMirrorlsTurnedState(state);
    }

    public int setRearviewMirrorIsAutoLightState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearviewMirrorIsAutoLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearviewMirrorIsAutoLightState(state);
    }

    public int setRearviewMirrorIsScreenState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearviewMirrorIsScreenState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearviewMirrorIsScreenState(state);
    }

    public int turnRearviewMirrorIs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnRearviewMirrorIs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnRearviewMirrorIs(action);
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

    public int resetRearviewMirrorIsSets(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " resetRearviewMirrorIsSets mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().resetRearviewMirrorIsSets(state);
    }

    public int setRefrigeratorWorkMode(int mode) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRefrigeratorWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRefrigeratorWorkMode(mode);
    }

    public int getRefrigeratorWorkMode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRefrigeratorWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRefrigeratorWorkMode();
    }

    public int setRefrigeratorWorkTemperature(int temperature) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRefrigeratorWorkTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRefrigeratorWorkTemperature(temperature);
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

    public int setDelayPowerOffTime(int time) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setDelayPowerOffTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDelayPowerOffTime(time);
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

    public int setDelayPowerDownTime(int time) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setDelayPowerDownTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDelayPowerDownTime(time);
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

    public int setRemberPowerOffState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRemberPowerOffState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRemberPowerOffState(state);
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

    public int setSterilizationState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setSterilizationState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSterilizationState(state);
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

    public int setFridgeDoorState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setFridgeDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setFridgeDoorState(state);
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

    public int setWorkState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWorkState(state);
    }

    public int setWorkTemperature(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setWorkTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWorkTemperature(state);
    }

    public int setWorkCommond(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setWorkCommond mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setWorkCommond(state);
    }

    public int setRearWorkTemperature(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearWorkTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearWorkTemperature(state);
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

    public int getPhoneRemindCode(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPhoneRemindCode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPhoneRemindCode(state);
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

    public int setDelayPowerDownState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setDelayPowerDownState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDelayPowerDownState(state);
    }

    public int setDelayPowerDownStateTime(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setDelayPowerDownStateTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setDelayPowerDownStateTime(state);
    }

    public int setFridgeChildLock(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setFridgeChildLock mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setFridgeChildLock(state);
    }

    public int saveOwnerState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " saveOwnerState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().saveOwnerState(state);
    }

    public int setRearFridgeWorkState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeWorkState(state);
    }

    public int setRearFridgeSterilizerState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeSterilizerState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeSterilizerState(state);
    }

    public int setRearFridgeTemperature(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeTemperature mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeTemperature(state);
    }

    public int setRearFridgeDelayState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeDelayState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeDelayState(state);
    }

    public int setRearFridgeDelayTime(int time) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeDelayTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeDelayTime(time);
    }

    public int setRearFridgeDoorState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearFridgeDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearFridgeDoorState(state);
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

    public int setCanopyAutomaticDimming(int value) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCanopyAutomaticDimming mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCanopyAutomaticDimming(value);
    }

    public int setSkyCurtainPowerOffAndDim(int value) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setSkyCurtainPowerOffAndDim mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSkyCurtainPowerOffAndDim(value);
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

    public int setOpeningAngleInformationSetOnTheR(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setOpeningAngleInformationSetOnTheR mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOpeningAngleInformationSetOnTheR(state);
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

    public int setElectricTailAutomaticModeSwitchState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setElectricTailAutomaticModeSwitchState mForTestValue is 0 ");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setElectricTailAutomaticModeSwitchState(state);
    }

    public int setElectricTailAutoModeVoiceSwitchState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setElectricTailAutoModeVoiceSwitchState mForTestValue is 0 ");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setElectricTailAutoModeVoiceSwitchState(state);
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

    public int turnSeatHorizationRearLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearLeftRcs(action);
    }

    public int turnSeatHorizationRearLeftLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearLeftLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearLeftLeftRcs(action);
    }

    public int turnSeatHorizationRearLeftRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearLeftRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearLeftRightRcs(action);
    }

    public int turnSeatHorizationRearRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearRightRcs(action);
    }

    public int turnSeatHorizationRearRightLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearRightLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearRightLeftRcs(action);
    }

    public int turnSeatHorizationRearRightRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatHorizationRearRightRightRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatHorizationRearRightRightRcs(action);
    }

    public int setFlSeatBasinSwitchState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setFlSeatBasinSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setFlSeatBasinSwitchState(state);
    }

    public int setFrontPassengerSeatSeatPotSwitchState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setFrontPassengerSeatSeatPotSwitchState mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setFrontPassengerSeatSeatPotSwitchState(state);
    }

    public int turnSeatCushionRearLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearLeftRcs(action);
    }

    public int turnSeatCushionRearLeftLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearLeftLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearLeftLeftRcs(action);
    }

    public int turnSeatCushionRearLeftRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearLeftRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearLeftRightRcs(action);
    }

    public int turnSeatCushionRearRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearRightRcs(action);
    }

    public int turnSeatCushionRearRightLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearRightLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearRightLeftRcs(action);
    }

    public int turnSeatCushionRearRightRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCushionRearRightRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCushionRearRightRightRcs(action);
    }

    public int turnSeatBackRearLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearLeftRcs(action);
    }

    public int turnSeatBackRearLeftLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearLeftLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearLeftLeftRcs(action);
    }

    public int turnSeatBackRearLeftRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearLeftRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearLeftRightRcs(action);
    }

    public int turnSeatBackRearRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearRightRcs(action);
    }

    public int turnSeatBackRearRightLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearRightLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearRightLeftRcs(action);
    }

    public int turnSeatBackRearRightRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatBackRearRightRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatBackRearRightRightRcs(action);
    }

    public int turnSeatCaliperSplintRearLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearLeftRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearLeftRcs(action);
    }

    public int turnSeatCaliperSplintRearLeftLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearLeftLeftRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearLeftLeftRcs(action);
    }

    public int turnSeatCaliperSplintRearLeftRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearLeftRightRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearLeftRightRcs(action);
    }

    public int turnSeatCaliperSplintRearRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearRightRcs mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearRightRcs(action);
    }

    public int turnSeatCaliperSplintRearRightLeftRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearRightLeftRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearRightLeftRcs(action);
    }

    public int turnSeatCaliperSplintRearRightRightRcs(int action) {
        if (this.mForTest == 0) {
            Log.v(TAG, " turnSeatCaliperSplintRearRightRightRcs mForTestValue is 0");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().turnSeatCaliperSplintRearRightRightRcs(action);
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

    public int setIceBoxTriggerSource(int value) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setIceBoxTriggerSource mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIceBoxTriggerSource(value);
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

    public int setCpdImsSwitchState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCpdImsSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCpdImsSwitchState(state);
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

    public int setRncState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRncState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRncState(state);
    }

    public int getSwcontrolState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSwcontrolState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSwcontrolState();
    }

    public int setPedalSuppressionSystemState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setPedalSuppressionSystemState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setPedalSuppressionSystemState(state);
    }

    public int getPedalSuppressionSystemState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPedalSuppressionSystemState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPedalSuppressionSystemState();
    }

    public int setCarDoorTipsSoundType(int type) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCarDoorTipsSoundType mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCarDoorTipsSoundType(type);
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

    public int setRearRightSeatGreetState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRearRightSeatGreetState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRearRightSeatGreetState(state);
    }

    public int setCuraSwitchState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCuraSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCuraSwitchState(state);
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

    public int setGoalRoadMode(int mode) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setGoalRoadMode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setGoalRoadMode(mode);
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

    public int setSandAbsModeState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setSandAbsModeState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setSandAbsModeState(state);
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

    public int setRefrigWorkMode(int mode) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setRefrigWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setRefrigWorkMode(mode);
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

    public int setIviProfileSettingsDm(int mode) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setIviProfileSettingsDm mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setIviProfileSettingsDm(mode);
    }

    public int setCloudServiceStatus(int status) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setCloudServiceStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setCloudServiceStatus(status);
    }

    public int setOptimizationStatus(int status) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setOptimizationStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setOptimizationStatus(status);
    }

    public int setNetworkSecurityStatus(int status) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setNetworkSecurityStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setNetworkSecurityStatus(status);
    }

    public int getThirdRowSeatWorkMode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getThirdRowSeatWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getThirdRowSeatWorkMode();
    }

    public int getLeftRearSafebeltStatus() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLeftRearSafebeltStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLeftRearSafebeltStatus();
    }

    public int getCenterRearSafebeltStatus() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getCenterRearSafebeltStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getCenterRearSafebeltStatus();
    }

    public int getRightRearSafebeltStatus() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightRearSafebeltStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRightRearSafebeltStatus();
    }

    public int getLeftThirdSafebeltStatus() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLeftThirdSafebeltStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLeftThirdSafebeltStatus();
    }

    public int getRightThirdSafebeltStatus() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRightThirdSafebeltStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRightThirdSafebeltStatus();
    }

    public int getMainSeatHorizontalInit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainSeatHorizontalInit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainSeatHorizontalInit();
    }

    public int getMainSeatBackInit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainSeatBackInit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainSeatBackInit();
    }

    public int getPassangerSeatHorizontalInit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassangerSeatHorizontalInit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPassangerSeatHorizontalInit();
    }

    public int getPassangerSeatBackInit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassangerSeatBackInit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPassangerSeatBackInit();
    }

    public int getSecondLeftSeatHorizontalInit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSecondLeftSeatHorizontalInit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSecondLeftSeatHorizontalInit();
    }

    public int getSecondLeftSeatBackInit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSecondLeftSeatBackInit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSecondLeftSeatBackInit();
    }

    public int getSecondRightSeatHorizontalInit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSecondRightSeatHorizontalInit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSecondRightSeatHorizontalInit();
    }

    public int getSecondRightSeatBackInit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSecondRightSeatBackInit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSecondRightSeatBackInit();
    }

    public int getOneKeyEnterPitConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getOneKeyEnterPitConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getOneKeyEnterPitConfig();
    }

    public int setThirdRowSeatLeftBackrest(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setThirdRowSeatLeftBackrest mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setThirdRowSeatLeftBackrest(state);
    }

    public int setThirdRowSeatRightBackrest(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setThirdRowSeatRightBackrest mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setThirdRowSeatRightBackrest(state);
    }

    public int getSeatDistribution() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSeatDistributionFormConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value = getSeatDistributionFormConfig();
        if (value > 0 && value < 8) {
            return value;
        }
        int value2 = getSeatSecullDistributionFormConfig();
        if (value2 > 0 && value2 < 8) {
            return value2;
        }
        int value3 = getSeatSeculrDistributionFormConfig();
        if (value3 > 0 && value3 < 8) {
            return value3;
        }
        int value4 = getSeatSecurlDistributionFormConfig();
        if (value4 > 0 && value4 < 8) {
            return value4;
        }
        int value5 = getSeatSecurrDistributionFormConfig();
        if (value5 > 0 && value5 < 8) {
            return value5;
        }
        Log.w(TAG, "getSeatDistribution no value, return -1");
        return -1;
    }

    public int getSeatDistributionFormConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSeatDistributionFormConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatDistributionFormConfig();
    }

    public int getMeterExportArea() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMeterExportArea mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMeterExportArea();
    }

    public int getWashElectricPedal() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getWashElectricPedal mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getWashElectricPedal();
    }

    public int getTapDoorState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTapDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTapDoorState();
    }

    public int getMainSeatHorizontalInited() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainSeatHorizontalInited mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainSeatHorizontalInited();
    }

    public int getMainSeatBackInited() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainSeatBackInited mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainSeatBackInited();
    }

    public int getMainSeatHightInited() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainSeatHightInited mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainSeatHightInited();
    }

    public int getThirdRowLeftBackrestDisplay() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getThirdRowLeftBackrestDisplay mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getThirdRowLeftBackrestDisplay();
    }

    public int getThirdRowRightBackrestDisplay() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getThirdRowRightBackrestDisplay mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getThirdRowRightBackrestDisplay();
    }

    public int getSecondRightSeatHorizontal2Init() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSecondRightSeatHorizontal2Init mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSecondRightSeatHorizontal2Init();
    }

    public int getSecondRightSeatBack2Init() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSecondRightSeatBack2Init mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSecondRightSeatBack2Init();
    }

    public int getDragType() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDragType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDragType();
    }

    public int getDragConnectionType() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDragConnectionType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDragConnectionType();
    }

    public int getTrailerSmallItemConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTrailerSmallItemConfiguration mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerSmallItemConfiguration();
    }

    public int getTrailerMiddleItemConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTrailerMiddleItemConfiguration mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerMiddleItemConfiguration();
    }

    public int getTrailerLargeItemConfiguration() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTrailerLargeItemConfiguration mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerLargeItemConfiguration();
    }

    public int setTrailerDragType(int type) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setTrailerDragType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().setTrailerDragType(type);
    }

    public int getTrailerDragType() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTrailerDragType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerDragType();
    }

    public int getTrailerSmallLimitWeight() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTrailerSmallLimitWeight mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerSmallLimitWeight();
    }

    public int getTrailerMiddleLimitWeight() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTrailerMiddleLimitWeight mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerMiddleLimitWeight();
    }

    public int getTrailerLargeLimitWeight() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTrailerLargeLimitWeight mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTrailerLargeLimitWeight();
    }

    public int getTowingProhibitedState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTowingProhibitedState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getTowingProhibitedState();
    }

    public int getUcuMassageMode(int position) {
        if (this.mForTest == 0) {
            Log.v(TAG, " getUcuMassageMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (position == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getUcuMassageModeMain();
        }
        if (position == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getUcuMassageModeDeputy();
        }
        Log.d(TAG, "getUcuMassageMode, invalid postion:" + position);
        return 0;
    }

    public int getUcuMassageLevel(int position) {
        if (this.mForTest == 0) {
            Log.v(TAG, " getUcuMassageLevel mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (position == 1) {
            return this.mTsManagerImpl.getSettingAdapterManager().getUcuMassageLevelMain();
        }
        if (position == 2) {
            return this.mTsManagerImpl.getSettingAdapterManager().getUcuMassageLevelDeputy();
        }
        Log.d(TAG, "getUcuMassageLevel, invalid postion:" + position);
        return 0;
    }

    public int getBdMassageMode(int position) {
        if (this.mForTest == 0) {
            Log.v(TAG, " getBdMassageMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (position == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBdMassageModeLeftRear();
        }
        if (position == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBdMassageModeRightRear();
        }
        Log.d(TAG, "getBdMassageMode, invalid postion:" + position);
        return 0;
    }

    public int getBdMassageLevel(int position) {
        if (this.mForTest == 0) {
            Log.v(TAG, " getBdMassageLevel mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (position == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBdMassageLevelLeftRear();
        }
        if (position == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getBdMassageLevelRightRear();
        }
        Log.d(TAG, "getBdMassageLevel, invalid postion:" + position);
        return 0;
    }

    public int getSecuMassageMode(int position) {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSecuMassageMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (position == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSecuMassageModeLeftRear();
        }
        if (position == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSecuMassageModeRightRear();
        }
        Log.d(TAG, "getSecuMassageMode, invalid postion:" + position);
        return 0;
    }

    public int getSecuMassageLevel(int position) {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSecuMassageLevel mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (position == 3) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSecuMassageLevelLeftRear();
        }
        if (position == 4) {
            return this.mTsManagerImpl.getSettingAdapterManager().getSecuMassageLevelRightRear();
        }
        Log.d(TAG, "getSecuMassageLevel, invalid postion:" + position);
        return 0;
    }

    public int getRrEcuMassageMode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrEcuMassageMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrEcuMassageMode();
    }

    public int getRrEcuMassageLevel() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrEcuMassageLevel mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrEcuMassageLevel();
    }

    public int getDriverMasToggleModeConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriverMasToggleModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getDriverMasToggleModeConfig();
    }

    public int getPassengerMasToggleModeConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerMasToggleModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPassengerMasToggleModeConfig();
    }

    public int getLrMasToggleModeConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrMasToggleModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrMasToggleModeConfig();
    }

    public int getLrMas2ToggleModeConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrMas2ToggleModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrMas2ToggleModeConfig();
    }

    public int getRrMasToggleModeConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrMasToggleModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrMasToggleModeConfig();
    }

    public int getRrMas2ToggleModeConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrMas2ToggleModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrMas2ToggleModeConfig();
    }

    public int getMainNormalMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainNormalMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMainNormalMassageConfig();
    }

    public int getMainHotStoneMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMainHotStoneMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByEcu();
    }

    public int getMain2HotStoneMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getMain2HotStoneMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getMain2HotStoneMassageConfig();
    }

    public int getPasNormalMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPasNormalMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPasNormalMassageConfig();
    }

    public int getPasHotStoneMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPasHotStoneMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHotStoneMassageConfigurationByECU();
    }

    public int getPas2HotStoneMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPas2HotStoneMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getPas2HotStoneMassageConfig();
    }

    public int getLrNormalMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrNormalMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrNormalMassageConfig();
    }

    public int getLrNormal2MassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrNormal2MassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrNormal2MassageConfig();
    }

    public int getLrHotStoneMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrHotStoneMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getLrHotStone2MassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrHotStone2MassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrHotStone2MassageConfig();
    }

    public int getLrHotStone3MassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrHotStone3MassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getLrHotStone3MassageConfig();
    }

    public int getRrNormalMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrNormalMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrNormalMassageConfig();
    }

    public int getRrNormal2MassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrNormal2MassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrNormal2MassageConfig();
    }

    public int getRrNormal3MassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrNormal3MassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrNormal3MassageConfig();
    }

    public int getRrHotStoneMassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrHotStoneMassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getRrHotStone2MassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrHotStone2MassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrHotStone2MassageConfig();
    }

    public int getRrHotStone3MassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrHotStone3MassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrHotStone3MassageConfig();
    }

    public int getRrHotStone4MassageConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrHotStone4MassageConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getRrHotStone4MassageConfig();
    }

    public int getSeatSecullDistributionFormConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSeatSecullDistributionFormConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatSecullDistributionFormConfig();
    }

    public int getSeatSeculrDistributionFormConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSeatSeculrDistributionFormConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatSeculrDistributionFormConfig();
    }

    public int getSeatSecurlDistributionFormConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSeatSecurlDistributionFormConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatSecurlDistributionFormConfig();
    }

    public int getSeatSecurrDistributionFormConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getSeatSecurrDistributionFormConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getSettingAdapterManager().getSeatSecurrDistributionFormConfig();
    }
}
