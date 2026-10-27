package android.hardware.bydauto.light.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.hardware.bydauto_test.TestLightManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class LightManagerImpl extends AbsTsMyCar {
    private static final int HAS_0X038A00 = 57;
    private static final int HAS_0X03AC00 = 37;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_LightManagerImpl";
    private static final String TAG = "LightManagerImpl";
    private static volatile LightManagerImpl sInstance;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private TestLightManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoLightListener> mAbsBydAutoLightListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    public void unregisterListener(IBYDAutoListener iBYDAutoListener) {
    }

    private LightManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission("android.permission.BYDAUTO_LIGHT_COMMON");
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestLightManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.light.tsimpl.LightManagerImpl.1
            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (!LightManagerImpl.this.mAbsBydAutoLightListeners.isEmpty()) {
                    try {
                        LightManagerImpl.this.lightCallback(i2, i3, carBodyPacket);
                        return;
                    } catch (Exception unused) {
                        Log.e(LightManagerImpl.TAG, "lightCallback is error");
                        return;
                    }
                }
                Log.v(LightManagerImpl.TAG, "mAbsBydAutoLightListeners is null. ");
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(LightManagerImpl.TAG, "onBinderStateChanged is " + z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lightCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "LightCallback propertyId:" + i);
        switch (i) {
            case LightHalId.ID_DAY_LIGHT_SUPPORT /* 557856041 */:
                caseFeatureIds(2803, Integer.valueOf(carBodyPacket.getDayLightSupport()));
                break;
            case LightHalId.ID_AMBIENT_COLORS_SUPPORT /* 557856188 */:
                caseFeatureIds(4537, Integer.valueOf(carBodyPacket.getAmbientColorsSupport()));
                break;
            case 557857169:
                Iterator<AbsBYDAutoLightListener> it = this.mAbsBydAutoLightListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAllWeatherLightSwitchStateChanged(carBodyPacket.getAllWeatherLightSwitchStateR());
                }
                break;
            case 557857171:
                Iterator<AbsBYDAutoLightListener> it2 = this.mAbsBydAutoLightListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onHeadlightControlModeChanged(carBodyPacket.getHeadlightControlModeR());
                }
                break;
            case 557857172:
                Iterator<AbsBYDAutoLightListener> it3 = this.mAbsBydAutoLightListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onRearFogLightSwitchStateChanged(carBodyPacket.getRearFogLightSwitchStateR());
                }
                break;
            case 557857710:
                Iterator<AbsBYDAutoLightListener> it4 = this.mAbsBydAutoLightListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onAmbRingColorChanged(carBodyPacket.getIalColorStarRingR());
                }
                caseFeatureIds(2845, Integer.valueOf(carBodyPacket.getIalColorStarRingR()));
                break;
            case 557857711:
                Iterator<AbsBYDAutoLightListener> it5 = this.mAbsBydAutoLightListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onIALColorChanged(4, carBodyPacket.getIalColorStarrySkyR());
                }
                caseFeatureIds(2847, Integer.valueOf(carBodyPacket.getIalColorStarrySkyR()));
                break;
            case 557857712:
                Iterator<AbsBYDAutoLightListener> it6 = this.mAbsBydAutoLightListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onIALColorChanged(1, carBodyPacket.getIalColorFrontR());
                }
                caseFeatureIds(1161, Integer.valueOf(carBodyPacket.getIalColorFrontR()));
                break;
            case 557857713:
                Iterator<AbsBYDAutoLightListener> it7 = this.mAbsBydAutoLightListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onIALColorChanged(2, carBodyPacket.getIalColorBackR());
                }
                caseFeatureIds(1157, Integer.valueOf(carBodyPacket.getIalColorBackR()));
                break;
            case 557857716:
                Iterator<AbsBYDAutoLightListener> it8 = this.mAbsBydAutoLightListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onAmbRingBrightnessChanged(carBodyPacket.getIalBrightnessStarRingR());
                }
                caseFeatureIds(2844, Integer.valueOf(carBodyPacket.getIalBrightnessStarRingR()));
                break;
            case 557857717:
                Iterator<AbsBYDAutoLightListener> it9 = this.mAbsBydAutoLightListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onIALBrightnessChanged(4, carBodyPacket.getIalBrightnessStarrySkyR());
                }
                caseFeatureIds(2846, Integer.valueOf(carBodyPacket.getIalBrightnessStarrySkyR()));
                break;
            case 557857718:
                Iterator<AbsBYDAutoLightListener> it10 = this.mAbsBydAutoLightListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onIALBrightnessChanged(2, carBodyPacket.getIalBrightnessBackR());
                }
                caseFeatureIds(1156, Integer.valueOf(carBodyPacket.getIalBrightnessBackR()));
                break;
            case 557857719:
                Iterator<AbsBYDAutoLightListener> it11 = this.mAbsBydAutoLightListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onIALBrightnessChanged(1, carBodyPacket.getIalBrightnessFrontR());
                }
                caseFeatureIds(1160, Integer.valueOf(carBodyPacket.getIalBrightnessFrontR()));
                break;
            case LightHalId.ID_LIGHT_AUTO_STATUS_ONE /* 557860608 */:
                int i3 = carBodyPacket.getmLightAutoStatusOne();
                for (AbsBYDAutoLightListener absBYDAutoLightListener : this.mAbsBydAutoLightListeners) {
                    if (i3 == 1) {
                        absBYDAutoLightListener.onLightAutoSwitchOn();
                        absBYDAutoLightListener.onDoorLightStateChanged(i3);
                    } else {
                        absBYDAutoLightListener.onLightAutoSwitchOff();
                        absBYDAutoLightListener.onDoorLightStateChanged(i3);
                    }
                }
                break;
            case LightHalId.ID_LIGHT_AUTO_STATUS_TWO /* 557860609 */:
                int i4 = carBodyPacket.getmLightAutoStatusTwo();
                for (AbsBYDAutoLightListener absBYDAutoLightListener2 : this.mAbsBydAutoLightListeners) {
                    if (i4 == 1) {
                        absBYDAutoLightListener2.onLightAutoSwitchOn();
                    } else {
                        absBYDAutoLightListener2.onLightAutoSwitchOff();
                    }
                }
                break;
            case LightHalId.ID_AFS_SWITCH /* 557860610 */:
                int afsSwitch = carBodyPacket.getAfsSwitch();
                Iterator<AbsBYDAutoLightListener> it12 = this.mAbsBydAutoLightListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onAFSSwitchStateChange(afsSwitch);
                }
                caseFeatureIds(890, Integer.valueOf(afsSwitch));
                break;
            case 557860611:
                int i5 = carBodyPacket.getmAmbientMusicModeStateR();
                int areaId = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener3 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener3.onAmbientMusicModeState(areaId, i5);
                    Log.v(TAG, "ID_AMBIENT_MUSIC_MODE_STATE_R is onAmbientMusicModeState in " + i5 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener3));
                }
                caseFeatureIds(1255, Integer.valueOf(i5));
                Log.i(TAG, "SET_MUSIC_MODE_STATE musicState is " + i5);
                break;
            case 557860613:
                int i6 = carBodyPacket.getmAmbientMusicModeR();
                int areaId2 = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener4 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener4.onAmbientMusicModechanged(areaId2, i6);
                    Log.v(TAG, "ID_AMBIENT_MUSIC_MODE_R is onAmbientMusicModechanged in" + i6 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener4));
                }
                caseFeatureIds(2829, Integer.valueOf(i6));
                Log.i(TAG, "LIGHT_MUSIC_RHYTHM_MODE value is " + i6);
                break;
            case 557860615:
                int i7 = carBodyPacket.getmAmbientMulticolorStateR();
                int areaId3 = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener5 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener5.onAmbientMulticolorStateChanged(areaId3, i7);
                    Log.v(TAG, " onAmbientMulticolorStateChanged in multiState:" + i7 + ",multiId:" + areaId3 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener5));
                }
                caseFeatureIds(2808, Integer.valueOf(i7));
                break;
            case 557860617:
                int i8 = carBodyPacket.getmAmbientMulticolorModeR();
                int areaId4 = carBodyPacket.getAreaId();
                Iterator<AbsBYDAutoLightListener> it13 = this.mAbsBydAutoLightListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onAmbientMulticolorModeChanged(areaId4, i8);
                }
                caseFeatureIds(2805, Integer.valueOf(i8));
                break;
            case LightHalId.ID_DAY_TIME_LIGHT_STATE_R /* 557860619 */:
                int i9 = carBodyPacket.getmDayTimeLightStateR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener6 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener6.onDayTimeLightChanged(i9);
                    absBYDAutoLightListener6.onDayTimeRunningLitghtStateChanged(i9);
                }
                caseFeatureIds(2800, Integer.valueOf(i9));
                break;
            case LightHalId.ID_REVERSING_LIGHT_STATE /* 557860629 */:
                int reversingLightState = carBodyPacket.getReversingLightState();
                Iterator<AbsBYDAutoLightListener> it14 = this.mAbsBydAutoLightListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onReversingLightStateChanged(reversingLightState);
                }
                caseFeatureIds(2797, Integer.valueOf(reversingLightState));
                break;
            case LightHalId.ID_TURN_LIGHT_STATE /* 557860630 */:
                int turnLightState = carBodyPacket.getTurnLightState();
                for (AbsBYDAutoLightListener absBYDAutoLightListener7 : this.mAbsBydAutoLightListeners) {
                    Log.e(TAG, "onTurnLightStateChanged in:" + turnLightState + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener7));
                    absBYDAutoLightListener7.onTurnLightStateChanged(turnLightState);
                }
                Log.e(TAG, "onTurnLightStateChanged out:" + turnLightState);
                caseFeatureIds(2848, Integer.valueOf(turnLightState));
                break;
            case 557860631:
                int ambientFeedBack = carBodyPacket.getAmbientFeedBack();
                Iterator<AbsBYDAutoLightListener> it15 = this.mAbsBydAutoLightListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onAmbientFeedBackChanged(ambientFeedBack);
                }
                caseFeatureIds(902, Integer.valueOf(ambientFeedBack));
                break;
            case LightHalId.ID_TURN_LIGHT_STATER /* 557860634 */:
                int turnLightStateR = carBodyPacket.getTurnLightStateR();
                Iterator<AbsBYDAutoLightListener> it16 = this.mAbsBydAutoLightListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onTurnLightStateChanged(turnLightStateR);
                }
                caseFeatureIds(2848, Integer.valueOf(turnLightStateR));
                break;
            case LightHalId.ID_LIGHT_LOW_BEAM_STATUSR /* 557860637 */:
                int lightLowBeamStatusR = carBodyPacket.getLightLowBeamStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener8 : this.mAbsBydAutoLightListeners) {
                    if (lightLowBeamStatusR == 1) {
                        absBYDAutoLightListener8.onLightOn(2);
                        Log.e(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener8));
                    } else {
                        absBYDAutoLightListener8.onLightOff(2);
                        Log.e(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener8));
                    }
                }
                caseFeatureIds(2827, Integer.valueOf(lightLowBeamStatusR));
                Log.e(TAG, "LIGHT_LOW_BEAM_LIGHT caseFeatureIds is in");
                break;
            case LightHalId.ID_LIGHT_HIGH_BEAMR /* 557860638 */:
                int lightHighBeamR = carBodyPacket.getLightHighBeamR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener9 : this.mAbsBydAutoLightListeners) {
                    if (lightHighBeamR == 1) {
                        absBYDAutoLightListener9.onLightOn(3);
                        Log.e(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener9));
                    } else {
                        absBYDAutoLightListener9.onLightOff(3);
                        Log.e(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener9));
                    }
                }
                caseFeatureIds(2820, Integer.valueOf(lightHighBeamR));
                Log.e(TAG, "LIGHT_HIGH_BEAM_LIGHT caseFeatureIds is in");
                break;
            case LightHalId.ID_LIGHT_REAR_FOGR /* 557860642 */:
                int lightRearFog = carBodyPacket.getLightRearFog();
                for (AbsBYDAutoLightListener absBYDAutoLightListener10 : this.mAbsBydAutoLightListeners) {
                    if (lightRearFog == 1) {
                        absBYDAutoLightListener10.onLightOn(7);
                        Log.e(TAG, "ID_LIGHT_REAR_FOGR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    } else {
                        absBYDAutoLightListener10.onLightOff(7);
                        Log.e(TAG, "ID_LIGHT_REAR_FOGR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    }
                }
                caseFeatureIds(2832, Integer.valueOf(lightRearFog));
                Log.e(TAG, "LIGHT_REAR_FOG_LAMPS caseFeatureIds is in");
                break;
            case LightHalId.ID_DAY_TIME_RUNNING_LIGHT_STATE_R /* 557860643 */:
                int dayTimeRunningLightState = carBodyPacket.getDayTimeRunningLightState();
                Iterator<AbsBYDAutoLightListener> it17 = this.mAbsBydAutoLightListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onDayTimeRunningLitghtStateChanged(dayTimeRunningLightState);
                }
                caseFeatureIds(2800, Integer.valueOf(carBodyPacket.getDayTimeRunningLightState()));
                break;
            case 557860644:
                int lightRearPosition = carBodyPacket.getLightRearPosition();
                Iterator<AbsBYDAutoLightListener> it18 = this.mAbsBydAutoLightListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onRearLampLightStateChanged(lightRearPosition);
                }
                break;
            case LightHalId.ID_BACKLIGHT_LEVEL_R /* 557860648 */:
                Iterator<AbsBYDAutoLightListener> it19 = this.mAbsBydAutoLightListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onBacklightLevelsChanged(carBodyPacket.getBacklightLevelR());
                }
                break;
            case LightHalId.ID_LEFT_FRONT_READING_LAMP_STATUS_R /* 557860657 */:
                Iterator<AbsBYDAutoLightListener> it20 = this.mAbsBydAutoLightListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onLeftFrontReadingLampStatusChanged(carBodyPacket.getLeftFrontReadingLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getLeftFrontReadingLampStatusR()));
                break;
            case LightHalId.ID_RIGHT_FRONT_READING_LAMP_STATUS_R /* 557860658 */:
                Iterator<AbsBYDAutoLightListener> it21 = this.mAbsBydAutoLightListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onRightFrontReadingLampStatusChanged(carBodyPacket.getRightFrontReadingLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getRightFrontReadingLampStatusR()));
                break;
            case LightHalId.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R /* 557860659 */:
                Iterator<AbsBYDAutoLightListener> it22 = this.mAbsBydAutoLightListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onLeftMiddleRowSideRLightSwitchChanged(carBodyPacket.getLeftMiddleRowSideRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getLeftMiddleRowSideRLightSwitchR()));
                break;
            case LightHalId.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R /* 557860660 */:
                Iterator<AbsBYDAutoLightListener> it23 = this.mAbsBydAutoLightListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onRightMiddleRowRLightSwitch(carBodyPacket.getRightMiddleRowRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getRightMiddleRowRLightSwitchR()));
                break;
            case LightHalId.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R /* 557860661 */:
                Iterator<AbsBYDAutoLightListener> it24 = this.mAbsBydAutoLightListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onLeftMiddleRowRLightRotaryStateChanged(carBodyPacket.getLeftMiddleRowRLightRotaryStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(carBodyPacket.getLeftMiddleRowRLightRotaryStateR()));
                break;
            case LightHalId.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R /* 557860662 */:
                Iterator<AbsBYDAutoLightListener> it25 = this.mAbsBydAutoLightListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onRightMiddleRowRLightRotaryChanged(carBodyPacket.getRightMiddleRowRLightRotaryR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(carBodyPacket.getRightMiddleRowRLightRotaryR()));
                break;
            case LightHalId.ID_LEFT_REAR_R_LIGHT_SWITCH_R /* 557860663 */:
                Iterator<AbsBYDAutoLightListener> it26 = this.mAbsBydAutoLightListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onLeftRearRLightSwitchChanged(carBodyPacket.getLeftRearRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getLeftRearRLightSwitchR()));
                break;
            case LightHalId.ID_RIGHT_REAR_R_LIGHT_SWITCH_R /* 557860664 */:
                Iterator<AbsBYDAutoLightListener> it27 = this.mAbsBydAutoLightListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onRightRearRLightSwitchChanged(carBodyPacket.getRightRearRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getRightRearRLightSwitchR()));
                break;
            case LightHalId.ID_CARGO_LIGHT_SWITCH_STATE_R /* 557860665 */:
                Iterator<AbsBYDAutoLightListener> it28 = this.mAbsBydAutoLightListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onCargoLightSwitchStateChanged(carBodyPacket.getCargoLightSwitchStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getCargoLightSwitchStateR()));
                break;
            case LightHalId.ID_LEFT_CORNER_LIGHT_STATUS_R /* 557860668 */:
                Iterator<AbsBYDAutoLightListener> it29 = this.mAbsBydAutoLightListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onLeftCornerLightStatusChanged(carBodyPacket.getLeftCornerLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getLeftCornerLightStatusR()));
                break;
            case LightHalId.ID_RIGHT_CORNER_LIGHT_STATUS_R /* 557860669 */:
                Iterator<AbsBYDAutoLightListener> it30 = this.mAbsBydAutoLightListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onRightCornerLightStatusChanged(carBodyPacket.getRightCornerLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getRightCornerLightStatusR()));
                break;
            case LightHalId.ID_FRONT_FOG_LAMP_STATUS_R /* 557860670 */:
                Iterator<AbsBYDAutoLightListener> it31 = this.mAbsBydAutoLightListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onFrontFogLampStatusChanged(carBodyPacket.getFrontFogLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS, Integer.valueOf(carBodyPacket.getFrontFogLampStatusR()));
                break;
            case LightHalId.ID_FRONT_CENTER_LIGHT_STATUS_R /* 557860671 */:
                Iterator<AbsBYDAutoLightListener> it32 = this.mAbsBydAutoLightListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onFrontCenterLightStatusChanged(carBodyPacket.getFrontCenterLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS, Integer.valueOf(carBodyPacket.getFrontCenterLightStatusR()));
                break;
            case LightHalId.ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R /* 557860672 */:
                Iterator<AbsBYDAutoLightListener> it33 = this.mAbsBydAutoLightListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onPositionLightDisplayFeedCallbackChanged(carBodyPacket.getPositionLightDisplayFeedCallbackR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK, Integer.valueOf(carBodyPacket.getPositionLightDisplayFeedCallbackR()));
                break;
            case LightHalId.ID_RIGHT_DAYTIME_LIGHT_STATE_R /* 557860674 */:
                Iterator<AbsBYDAutoLightListener> it34 = this.mAbsBydAutoLightListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onRightDaytimeLightStateChanged(carBodyPacket.getRightDaytimeLightStateR());
                }
                caseFeatureIds(2836, Integer.valueOf(carBodyPacket.getRightDaytimeLightStateR()));
                break;
            case LightHalId.ID_LEFT_DAYTIME_LIGHT_STATE_R /* 557860675 */:
                Iterator<AbsBYDAutoLightListener> it35 = this.mAbsBydAutoLightListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onLeftDaytimeLightStateChanged(carBodyPacket.getLeftDaytimeLightStateR());
                }
                caseFeatureIds(2823, Integer.valueOf(carBodyPacket.getLeftDaytimeLightStateR()));
                break;
            case LightHalId.ID_HIGHTLIGHT_POSITION_R /* 557860677 */:
                Iterator<AbsBYDAutoLightListener> it36 = this.mAbsBydAutoLightListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onHightlightPositionChanged(carBodyPacket.getHightlightPositionR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT, Integer.valueOf(carBodyPacket.getHightlightPositionR()));
                break;
            case LightHalId.ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R /* 557860680 */:
                Iterator<AbsBYDAutoLightListener> it37 = this.mAbsBydAutoLightListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onLeftFrontPositionSWLStatusChanged(carBodyPacket.getLeftFrontPositionSwlStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LEFT_FRONT_POSITION_SPATIOTEMPORAL_WING_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getLeftFrontPositionSwlStatusR()));
                break;
            case LightHalId.ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R /* 557860681 */:
                Iterator<AbsBYDAutoLightListener> it38 = this.mAbsBydAutoLightListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onRightFrontPositionSWLStatusChanged(carBodyPacket.getRightFrontPositionSwlStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RIGHT_FRONT_POSITION_SPATIOTEMPORAL_WING_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getRightFrontPositionSwlStatusR()));
                break;
            case LightHalId.ID_LEFT_DAYTIME_RUN_LAMP_STATE_R /* 557860682 */:
                Iterator<AbsBYDAutoLightListener> it39 = this.mAbsBydAutoLightListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onLeftDaytimeRunLampStateChanged(carBodyPacket.getLeftDaytimeRunLampStateR());
                }
                caseFeatureIds(2823, Integer.valueOf(carBodyPacket.getLeftDaytimeRunLampStateR()));
                break;
            case LightHalId.ID_RIGHT_DAYTIME_RUN_LAMP_STATE_R /* 557860683 */:
                Iterator<AbsBYDAutoLightListener> it40 = this.mAbsBydAutoLightListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onRightDaytimeRunLampStateChanged(carBodyPacket.getRightDaytimeRunLampStateR());
                }
                caseFeatureIds(2836, Integer.valueOf(carBodyPacket.getRightDaytimeRunLampStateR()));
                break;
            case LightHalId.ID_FRONT_FOG_LIGHT_SWITCH_STATE_R /* 557860688 */:
                Iterator<AbsBYDAutoLightListener> it41 = this.mAbsBydAutoLightListeners.iterator();
                while (it41.hasNext()) {
                    it41.next().onFrontFogLightSwitchStateChanged(carBodyPacket.getFrontFogLightSwitchStateR());
                }
                break;
            case LightHalId.ID_LEFT_REMOTE_LIGHT_STATE_R /* 557860693 */:
                Iterator<AbsBYDAutoLightListener> it42 = this.mAbsBydAutoLightListeners.iterator();
                while (it42.hasNext()) {
                    it42.next().onLeftRemoteLightStateChanged(carBodyPacket.getLeftRemoteLightStateR());
                }
                break;
            case LightHalId.ID_RIGHT_REMOTE_LIGHT_STATE_R /* 557860694 */:
                Iterator<AbsBYDAutoLightListener> it43 = this.mAbsBydAutoLightListeners.iterator();
                while (it43.hasNext()) {
                    it43.next().onRightRemoteLightStateChanged(carBodyPacket.getRightRemoteLightStateR());
                }
                break;
            case LightHalId.ID_MIDDLE_STERN_LAMP_STATE_R /* 557860695 */:
                Iterator<AbsBYDAutoLightListener> it44 = this.mAbsBydAutoLightListeners.iterator();
                while (it44.hasNext()) {
                    it44.next().onMiddleSternLampStateChanged(carBodyPacket.getMiddleSternLampStateR());
                }
                break;
            case LightHalId.ID_AMBIENT_STATE_R /* 557860697 */:
                Iterator<AbsBYDAutoLightListener> it45 = this.mAbsBydAutoLightListeners.iterator();
                while (it45.hasNext()) {
                    it45.next().onAmbientStateChanged(carBodyPacket.getAmbientStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_MAIN_SWITCH, Integer.valueOf(carBodyPacket.getAmbientStateR()));
                break;
            case LightHalId.ID_LIGHT_REAR_GLOW_LOGO_WORK_STATE_R /* 557860699 */:
                Iterator<AbsBYDAutoLightListener> it46 = this.mAbsBydAutoLightListeners.iterator();
                while (it46.hasNext()) {
                    it46.next().onLightRearGlowLogoWorkStateChanged(carBodyPacket.getLightRearGlowLogoWorkStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_REAR_GLOW_LOGO_WORK_STATE, Integer.valueOf(carBodyPacket.getLightRearGlowLogoWorkStateR()));
                break;
            case LightHalId.ID_LIGHT_ADAS_INDICATOR_STATUS_R /* 557860700 */:
                Iterator<AbsBYDAutoLightListener> it47 = this.mAbsBydAutoLightListeners.iterator();
                while (it47.hasNext()) {
                    it47.next().onLightAdasIndicatorStatus(carBodyPacket.getLightAdasIndicatorStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHTS_ADAS_INDICATOR_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getLightAdasIndicatorStatusR()));
                break;
            case LightHalId.ID_LIGHT_FRONT_GLOW_LOGO_WORK_STATE_R /* 557860701 */:
                Iterator<AbsBYDAutoLightListener> it48 = this.mAbsBydAutoLightListeners.iterator();
                while (it48.hasNext()) {
                    it48.next().onLightFrontGlowLogoWorkState(carBodyPacket.getLightFrontGlowLogoWorkStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_FRONT_GLOW_LOGO_WORK_STATE, Integer.valueOf(carBodyPacket.getLightFrontGlowLogoWorkStateR()));
                break;
            case LightHalId.ID_ATMOSPERE_LIGHT_NIGHT_WEAKEN_MODE_CONFIG_R /* 557860703 */:
                Iterator<AbsBYDAutoLightListener> it49 = this.mAbsBydAutoLightListeners.iterator();
                while (it49.hasNext()) {
                    it49.next().onAtmospereLightNightWeakenModeChanged(carBodyPacket.getAtmospereLightNightWeakenModeConfigR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE, Integer.valueOf(carBodyPacket.getAtmospereLightNightWeakenModeConfigR()));
                break;
            case LightHalId.ID_ATMOSPERE_LAMP_POPUP_PROMPT_R /* 557860706 */:
                Iterator<AbsBYDAutoLightListener> it50 = this.mAbsBydAutoLightListeners.iterator();
                while (it50.hasNext()) {
                    it50.next().onAtmospereLampPopupPrompt(carBodyPacket.getAtmospereLampPopupPromptR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_BRIGHTNESS_REDUCE_POPUP_PROMPT, Integer.valueOf(carBodyPacket.getAtmospereLampPopupPromptR()));
                break;
            case LightHalId.ID_THEME_COLOR_SWITCH_STATE_R /* 557860707 */:
                Iterator<AbsBYDAutoLightListener> it51 = this.mAbsBydAutoLightListeners.iterator();
                while (it51.hasNext()) {
                    it51.next().onThemeColorSwitchState(carBodyPacket.getThemeColorSwitchStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_THEME_COLOR_SWITCH_STATE, Integer.valueOf(carBodyPacket.getThemeColorSwitchStateR()));
                break;
            case LightHalId.ID_ATMOSPERE_LAMP_UI_DISPLAY_R /* 557860709 */:
                Iterator<AbsBYDAutoLightListener> it52 = this.mAbsBydAutoLightListeners.iterator();
                while (it52.hasNext()) {
                    it52.next().onAtmospereLampUiDisplayChanged(carBodyPacket.getAtmospereLampUiDisplayR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_UI_DISPLAY, Integer.valueOf(carBodyPacket.getAtmospereLampUiDisplayR()));
                break;
            case LightHalId.ID_THEME_COLOR_FEED_BACK_R /* 557860710 */:
                Iterator<AbsBYDAutoLightListener> it53 = this.mAbsBydAutoLightListeners.iterator();
                while (it53.hasNext()) {
                    it53.next().onThemeColorFeedBackChanged(carBodyPacket.getThemeColorFeedBackR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_THEME_COLOR_FEEDBACK, Integer.valueOf(carBodyPacket.getAtmospereLampUiDisplayR()));
                break;
            case LightHalId.ID_STOP_LIGHT_STATE /* 557870867 */:
                int stopLightStateR = carBodyPacket.getStopLightStateR();
                Iterator<AbsBYDAutoLightListener> it54 = this.mAbsBydAutoLightListeners.iterator();
                while (it54.hasNext()) {
                    it54.next().onStopLightStateChanged(stopLightStateR);
                }
                caseFeatureIds(2799, Integer.valueOf(stopLightStateR));
                break;
            case LightHalId.ID_LIGHT_SIDE_STATUSR /* 557879809 */:
                int lightSideStatusR = carBodyPacket.getLightSideStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener11 : this.mAbsBydAutoLightListeners) {
                    Log.e(TAG, "mAbsBydAutoLightListeners toString : " + System.identityHashCode(this.mAbsBydAutoLightListeners));
                    Log.e(TAG, "mAbsBydAutoLightListeners is size : " + this.mAbsBydAutoLightListeners.size());
                    if (lightSideStatusR == 1) {
                        absBYDAutoLightListener11.onLightOn(1);
                        Log.e(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener11));
                    } else {
                        absBYDAutoLightListener11.onLightOff(1);
                        Log.e(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener11));
                    }
                }
                caseFeatureIds(2842, Integer.valueOf(lightSideStatusR));
                Log.e(TAG, "LIGHT_SIDE_LIGHT caseFeatureIds is in lightSide = " + lightSideStatusR);
                break;
        }
    }

    public static LightManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LightManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new LightManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public int getLightAutoStatus() {
        Log.v(TAG, "getLightAutoStatus is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightAutoStatus", new Object[0]);
            Log.v(TAG, "getLightAutoStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLightAutoStatus();
    }

    public int getLightStatus(int i) {
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        int lightSideStatus = 0;
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightStatus", Integer.valueOf(i));
            Log.v(TAG, "getLightStatus mForTestValue is 0 execute return");
            return 0;
        }
        switch (i) {
            case 1:
                Log.v(TAG, "getLightSideStatus");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightSideStatus();
                break;
            case 2:
                Log.v(TAG, "getLightLowBeamStatus");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                break;
            case 3:
                Log.v(TAG, "getLightHighBeam");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                break;
            case 4:
                Log.v(TAG, "getLightLeftTurnSignal");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightLeftTurnSignal();
                break;
            case 5:
                Log.v(TAG, "getLightRightTurnSignal");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRightTurnSignal();
                break;
            case 6:
                Log.v(TAG, "getLightFrontFog");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightFrontFog();
                break;
            case 7:
                Log.v(TAG, "getLightRearFog");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                break;
            case 8:
                Log.v(TAG, "getLightFoot");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightFoot();
                break;
            case 9:
                Log.v(TAG, "getLightRearPosition");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                break;
        }
        Log.v(TAG, "getLightStatus Type is:" + i + " And Result is:" + lightSideStatus);
        return lightSideStatus;
    }

    public int getAFSSwitch() {
        Log.v(TAG, "getAFSSwitch is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAFSSwitch", new Object[0]);
            Log.v(TAG, "getAFSSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAFSSwitch();
    }

    public int setAmbientState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAmbientState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientState(i);
    }

    public int getAmbientState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAmbientState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientState();
    }

    public int getAmbientSwitchConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAmbientSwitchConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientSwitchConfig();
    }

    public int getLightRearGlowLogoWorkState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLightRearGlowLogoWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLightRearGlowLogoWorkState();
    }

    public int getLightAdasIndicatorStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLightAdasIndicatorStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLightAdasIndicatorStatus();
    }

    public int getLightFrontGlowLogoWorkState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLightFrontGlowLogoWorkState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLightFrontGlowLogoWorkState();
    }

    public int getAtmosphereLampBodyDisplay() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAtmosphereLampBodyDisplay mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAtmosphereLampBodyDisplay();
    }

    public int getAtmospereLightNightWeakenModeConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAtmospereLightNightWeakenModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAtmospereLightNightWeakenModeConfig();
    }

    public int getAtmospereLightNightWeakenMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAtmospereLightNightWeakenMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAtmospereLightNightWeakenMode();
    }

    public int setAtmospereLightNightWeakenMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAtmospereLightNightWeakenMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAtmospereLightNightWeakenMode(i);
    }

    public int getAtmospereLampPopupPrompt() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAtmospereLampPopupPrompt mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAtmospereLampPopupPrompt();
    }

    public int getThemeColorSwitchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getThemeColorSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getThemeColorSwitchState();
    }

    public int setThemeLinkedAmbienceLightColor(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setThemeLinkedAmbienceLightColor mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setThemeLinkedAmbienceLightColor(i);
    }

    public int getAtmospereLampUiDisplay() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAtmospereLampUiDisplay mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAtmospereLampUiDisplay();
    }

    public int getThemeColorFeedBack() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getThemeColorFeedBack mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getThemeColorFeedBack();
    }

    public int setThemeLinkedAtmosphereLampSwitch(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setThemeLinkedAtmosphereLampSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setThemeLinkedAtmosphereLampSwitch(i);
    }

    public int getThemeColorFeatureFlag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getThemeColorFeatureFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getThemeColorFeatureFlag();
    }

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        registerListener(absBYDAutoLightListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe light listener " + absBYDAutoLightListener + " featureIDs = " + Arrays.toString(iArr) + ",HashCode:" + System.identityHashCode(absBYDAutoLightListener));
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoLightListener(absBYDAutoLightListener);
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        enforcePermission("android.permission.BYDAUTO_LIGHT_COMMON");
        if (!this.mAbsBydAutoLightListeners.contains(absBYDAutoLightListener)) {
            this.mAbsBydAutoLightListeners.add(absBYDAutoLightListener);
            Log.e(TAG, "mAbsBydAutoLightListeners add is:" + absBYDAutoLightListener + ",hashcod:" + System.identityHashCode(absBYDAutoLightListener));
        }
        Log.e(TAG, "javalib registerListener toString:" + System.identityHashCode(this.mAbsBydAutoLightListeners));
        Log.e(TAG, "javalib registerListener size:" + this.mAbsBydAutoLightListeners.size());
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoLightListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        for (AbsBYDAutoLightListener absBYDAutoLightListener : this.mAbsBydAutoLightListeners) {
            Log.e(TAG, "onDataEventChanged id:" + i + ",value:" + bYDAutoEventValue + ",hashcod:" + System.identityHashCode(absBYDAutoLightListener));
            absBYDAutoLightListener.onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void unregisterListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_COMMON");
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoLightListener(null);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (absBYDAutoLightListener == null) {
            return;
        }
        if (this.mAbsBydAutoLightListeners.remove(absBYDAutoLightListener)) {
            Log.e(TAG, "Listener removed: " + absBYDAutoLightListener + ", hashcode: " + System.identityHashCode(absBYDAutoLightListener));
        } else {
            Log.e(TAG, "Listener not found: " + absBYDAutoLightListener + ", hashcode: " + System.identityHashCode(absBYDAutoLightListener));
        }
        unregisterConnectListener(absBYDAutoLightListener);
    }

    public int getAmbientMusicModeState(int i) {
        Log.v(TAG, "getAmbientMusicModeState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicModeState", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(i);
    }

    public int setAmbientMusicModeState(int i, int i2) {
        Log.v(TAG, "setAmbientMusicModeState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODESTATE, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicModeState(i, i2);
    }

    public int getAmbientMusicMode(int i) {
        Log.v(TAG, "getAmbientMusicMode is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicMode", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicMode(i);
    }

    public int setAmbientMusicMode(int i, int i2) {
        Log.v(TAG, "setAmbientMusicMode is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODE, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicMode(i, i2);
    }

    public int getAmbientMulticolorState(int i) {
        Log.v(TAG, "getAmbientMulticolorState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorState", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(i);
    }

    public int setAmbientMulticolorState(int i, int i2) {
        Log.v(TAG, "setAmbientMulticolorState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorState(i, i2);
    }

    public int getAmbientMulticolorMode(int i) {
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorMode", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(i);
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        Log.v(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE, Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorMode(i, i2);
    }

    public int setAmbientMusicRhym(int i, int i2) {
        Log.v(TAG, "setAmbientMusicRhym is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setAmbientMusicRhym", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicRhym(i, i2);
    }

    public int getAmbientMusicRhym(int i) {
        Log.v(TAG, "getAmbientMusicRhym is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicRhym", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicRhym(i);
    }

    public int getLightDelayTime(int i) {
        Log.v(TAG, "getLightDelayTime is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightDelayTime", Integer.valueOf(i));
            Log.v(TAG, "getLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLightDelayTime(i);
    }

    public int setLightDelayTime(int i, int i2) {
        Log.v(TAG, "setLightDelayTime is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setLightDelayTime", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLightDelayTime(i, i2);
    }

    public int getDayTimeLightState() {
        Log.v(TAG, "getDayTimeLightState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getDayTimeLightState", new Object[0]);
            Log.v(TAG, "getDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDayTimeLightState();
    }

    public int setDayTimeLightState(int i) {
        Log.v(TAG, "setDayTimeLightState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETDAYTIMELIGHTSTATE, Integer.valueOf(i));
            Log.v(TAG, "setDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDayTimeLightState(i);
    }

    @Deprecated
    public int getDoorLightState() {
        Log.v(TAG, "getDoorLightState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getDoorLightState", new Object[0]);
            Log.v(TAG, "getDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDoorLightState();
    }

    public int setDoorLightState(int i) {
        Log.v(TAG, "setDoorLightState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETDOORLIGHTSTATE, Integer.valueOf(i));
            Log.v(TAG, "setDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDoorLightState(i);
    }

    public void reSetAmbientParams(int i) {
        Log.v(TAG, "reSetAmbientParams is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_COMMON");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("reSetAmbientParams", Integer.valueOf(i));
            Log.v(TAG, "reSetAmbientParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() == null) {
                Log.v(TAG, "adapter is not connect");
            }
            this.mTsManagerImpl.getCarBodyManager().reSetAmbientParams(i);
        }
    }

    public void resetDelayLightParams() {
        Log.v(TAG, "resetDelayLightParams is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_COMMON");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("resetDelayLightParams", new Object[0]);
            Log.v(TAG, "resetDelayLightParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() == null) {
                Log.v(TAG, "adapter is not connect");
            }
            this.mTsManagerImpl.getCarBodyManager().resetDelayLightParams();
        }
    }

    public int setAFSSwitchSate(int i) {
        Log.v(TAG, "setAFSSwitchSate is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAFSSWITCHSATE, Integer.valueOf(i));
            Log.v(TAG, "setAFSSwitchSate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAFSSwitchSate(i);
    }

    public int setMusicModeState(int i, int i2) {
        Log.v(TAG, "setMusicModeState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setMusicModeState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMusicModeState(i, i2);
    }

    public int[] getAmbientColors(int i) {
        Log.v(TAG, "getAmbientColors is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientColors", Integer.valueOf(i));
            Log.v(TAG, "getAmbientColors mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return new int[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientColors(i);
    }

    public int setBrightness(int i, int i2) {
        Log.v(TAG, "setBrightness is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setBrightness", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setBrightness(i, i2);
    }

    public int getLastSetedArea() {
        Log.v(TAG, "getLastSetedArea is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getLastSetedArea mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
    }

    public int getTurnLightFlashState() {
        Log.v(TAG, "getTurnLightFlashState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTurnLightFlashState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTurnLightFlashState();
    }

    public int setTurnLightsFlashState(int i) {
        Log.v(TAG, "setTurnLightsFlashState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETTURNLIGHTSFLASHSTATE, Integer.valueOf(i));
            Log.v(TAG, "setTurnLightsFlashState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTurnLightsFlashState(i);
    }

    public int getStopLightState() {
        Log.v(TAG, "getStopLightState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        Log.v(TAG, "getStopLightState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getStopLightState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getStopLightState();
    }

    public int getReversingLightState() {
        Log.v(TAG, "getReversingLightState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        Log.v(TAG, "getReversingLightState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getReversingLightState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
    }

    public int getTurnLightState() {
        Log.v(TAG, "getTurnLightState is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        Log.v(TAG, "getTurnLightState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTurnLightState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
    }

    public int getAmbientFeedBack() {
        Log.v(TAG, "getAmbientFeedBack is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        Log.v(TAG, "getAmbientFeedBack is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientFeedBack mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
    }

    public int getDayTimeRunningLightState() {
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, " getDayTimeRunningLightState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDayTimeRunningLightState();
    }

    public int getAmbientColorsSupport() {
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientColorsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientColorsSupport();
    }

    public int getIalColor(int i) {
        Log.i(TAG, "getIALColor is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColor(i);
        }
        if (i == 1 || i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorFront();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorStarrySky();
        }
        if (i == 5) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorStarRing();
        }
        return 0;
    }

    public int setIalColor(int i, int i2, int i3) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setIALColor", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            Log.v(TAG, "setIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setIalColor(i, i2, i3);
    }

    public int getIalBrightness(int i) {
        Log.i(TAG, "getIALBrightness is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessBack();
        }
        if (i == 1 || i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessFront();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarrySky();
        }
        if (i == 5) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarRing();
        }
        return 0;
    }

    public int getAmbRingBrightness() {
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbRingBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarRing();
    }

    public int getAmbRingColor() {
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbRingColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getIalColorStarRing();
    }

    public int setIalBrightness(int i, int i2, int i3) {
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setIALBrightness", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            Log.v(TAG, "setIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setIalBrightness(i, i2, i3);
    }

    public int setOnSceneMode(int i) {
        Log.i(TAG, "setOnSceneMode is execute");
        enforcePermission("android.permission.BYDAUTO_LIGHT_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setOnSceneMode" + i, new Object[0]);
            Log.v(TAG, "setOnSceneMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setOnSceneMode(i);
    }

    public int getDayLightSupport() {
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getDayLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDayLightSupport();
    }

    public int setKtvMode(int i, int i2) {
        enforcePermission("android.permission.BYDAUTO_LIGHT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "setKtvMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setKtvMode(i, i2);
    }

    public int getBacklightLevels() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBacklightLevel mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getBacklightLevel();
    }

    public int setLeftFrontReadingLampSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftFrontReadingLampSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftFrontReadingLampSwitchState(i);
    }

    public int setRightFrontReadingLampSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightFrontReadingLampSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightFrontReadingLampSwitchState(i);
    }

    public int setLeftMiddleRowSideRLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftMiddleRowSideRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftMiddleRowSideRLightSwitchState(i);
    }

    public int setRightMiddleRowRLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightMiddleRowRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightMiddleRowRLightSwitchState(i);
    }

    public int setLeftMiddleRowRLightRotaryState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftMiddleRowRLightRotaryState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftMiddleRowRLightRotaryState(i);
    }

    public int setRightMiddleRowRLightRotaryState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightMiddleRowRLightRotaryState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightMiddleRowRLightRotaryState(i);
    }

    public int setLeftRearRLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftRearRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftRearRLightSwitchState(i);
    }

    public int setRightRearRLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightRearRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightRearRLightSwitchState(i);
    }

    public int getLeftFrontReadingLampStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftFrontReadingLampStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftFrontReadingLampStatus();
    }

    public int getRightFrontReadingLampStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightFrontReadingLampStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightFrontReadingLampStatus();
    }

    public int getLeftMiddleRowSideRLightSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftMiddleRowSideRLightSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftMiddleRowSideRLightSwitch();
    }

    public int getRightMiddleRowRLightSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightMiddleRowRLightSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightMiddleRowRLightSwitch();
    }

    public int getLeftMiddleRowRLightRotaryState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftMiddleRowRLightRotaryState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftMiddleRowRLightRotaryState();
    }

    public int getRightMiddleRowRLightRotary() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightMiddleRowRLightRotary mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightMiddleRowRLightRotary();
    }

    public int getLeftRearRLightSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftRearRLightSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftRearRLightSwitch();
    }

    public int getRightRearRLightSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightRearRLightSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightRearRLightSwitch();
    }

    public int getCargoLightSwitchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCargoLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getCargoLightSwitchState();
    }

    public int getCargoLightSwitchConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCargoLightSwitchConfig mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getCargoLightSwitchConfig();
    }

    public int setCargoLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCargoLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setCargoLightSwitchState(i);
    }

    public int getLeftCornerLightStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftCornerLightStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftCornerLightStatus();
    }

    public int getRightCornerLightStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightCornerLightStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightCornerLightStatus();
    }

    public int getFrontFogLampStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontFogLampStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFrontFogLampStatus();
    }

    public int getFrontCenterLightStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontCenterLightStatus（） mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFrontCenterLightStatus();
    }

    public int getPositionLightDisplayFeedCallback() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getPositionLightDisplayFeedCallback mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getPositionLightDisplayFeedCallback();
    }

    public int setPositionLightDisplay(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setPositionLightDisplay mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setPositionLightDisplay(i);
    }

    public int getRightDaytimeLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightDaytimeLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightDaytimeLightState();
    }

    public int getLeftDaytimeLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftDaytimeLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftDaytimeLightState();
    }

    public int setHighlightPosition(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setHighlightPosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setHighlightPosition(i);
    }

    public int getHightlightPosition() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHightlightPosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getHightlightPosition();
    }

    public int getHightlightPositionConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHightlightPositionConfig（） mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getHightlightPositionConfig();
    }

    public int getIntelligentPostionFeedback() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIntelligentPostionFeedback mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getIntelligentPostionFeedback();
    }

    public int getLeftFrontPositionSwlStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftFrontPositionSwlStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftFrontPositionSwlStatus();
    }

    public int getRightFrontPositionSwlStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightFrontPositionSwlStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightFrontPositionSwlStatus();
    }

    public int getLeftRemoteLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftRemoteLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftRemoteLightState();
    }

    public int getRightRemoteLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightRemoteLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightRemoteLightState();
    }

    public int getLeftDaytimeRunLampState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftDaytimeRunLampState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftDaytimeRunLampState();
    }

    public int getRightDaytimeRunLampState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightDaytimeRunLampState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightDaytimeRunLampState();
    }

    public int setHeadlightControlMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setHeadlightControlMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setHeadlightControlMode(i);
    }

    public int getHeadlightControlMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHeadlightControlMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getHeadlightControlMode();
    }

    public int getRearFogLightSwitchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRearFogLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRearFogLightSwitchState();
    }

    public int getAllWeatherLightSwitchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAllWeatherLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAllWeatherLightSwitchState();
    }

    public int getFrontFogLightSwitchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontFogLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFrontFogLightSwitchState();
    }

    public int getFrontFogLightConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontFogLightConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFrontFogLightConfig();
    }

    public int setRearFogLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRearFogLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRearFogLightSwitchState(i);
    }

    public int setAllWeatherLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAllWeatherLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAllWeatherLightSwitchState(i);
    }

    public int setFrontFogLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setFrontFogLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setFrontFogLightSwitchState(i);
    }

    public int getMiddleSternLampState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMiddleSternLampState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMiddleSternLampState();
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i <= 0) {
                    bYDAutoEventValue.intValue = -2147482648;
                } else if (i == 1156) {
                    bYDAutoEventValue.intValue = getIalBrightness(2);
                    sparseArray.put(1156, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i == 1157) {
                    bYDAutoEventValue.intValue = getIalColor(2);
                    sparseArray.put(1157, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i == 2807) {
                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                    sparseArray.put(2807, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i == 2808) {
                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(0);
                    sparseArray.put(2808, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i == 2832) {
                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                    sparseArray.put(2832, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i == 2833) {
                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                    sparseArray.put(2833, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i == 4663) {
                    bYDAutoEventValue.intValue = getCargoLightSwitchState();
                    sparseArray.put(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i == 4664) {
                    bYDAutoEventValue.intValue = getCargoLightSwitchConfig();
                    sparseArray.put(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i == 4675) {
                    bYDAutoEventValue.intValue = getHightlightPosition();
                    sparseArray.put(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i != 4676) {
                    switch (i) {
                        case 890:
                            bYDAutoEventValue.intValue = getAFSSwitch();
                            sparseArray.put(890, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 902:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
                            sparseArray.put(902, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1171:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
                            sparseArray.put(1171, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1255:
                            bYDAutoEventValue.intValue = getAmbientMusicModeState(1);
                            sparseArray.put(1255, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2006:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                            sparseArray.put(2006, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2797:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
                            sparseArray.put(2797, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2803:
                            bYDAutoEventValue.intValue = getDayLightSupport();
                            sparseArray.put(2803, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2805:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                            sparseArray.put(2805, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2811:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(1);
                            sparseArray.put(2811, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2814:
                            bYDAutoEventValue.intValue = getLightStatus(6);
                            sparseArray.put(2814, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2820:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                            sparseArray.put(2820, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2823:
                            int leftDaytimeRunLampState = getLeftDaytimeRunLampState();
                            if (leftDaytimeRunLampState == 1 || leftDaytimeRunLampState == 2) {
                                bYDAutoEventValue.intValue = leftDaytimeRunLampState;
                            } else {
                                bYDAutoEventValue.intValue = getLeftDaytimeLightState();
                            }
                            sparseArray.put(2823, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2827:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                            sparseArray.put(2827, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2829:
                            bYDAutoEventValue.intValue = getAmbientMusicMode(1);
                            sparseArray.put(2829, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2836:
                            int rightDaytimeRunLampState = getRightDaytimeRunLampState();
                            if (rightDaytimeRunLampState == 1 || rightDaytimeRunLampState == 2) {
                                bYDAutoEventValue.intValue = rightDaytimeRunLampState;
                            } else {
                                bYDAutoEventValue.intValue = getRightDaytimeLightState();
                            }
                            sparseArray.put(2836, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2842:
                            bYDAutoEventValue.intValue = getLightStatus(1);
                            sparseArray.put(2842, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4537:
                            bYDAutoEventValue.intValue = getAmbientColorsSupport();
                            sparseArray.put(4537, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4616:
                            bYDAutoEventValue.intValue = getBacklightLevels();
                            sparseArray.put(4616, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH /* 4690 */:
                            bYDAutoEventValue.intValue = getLeftFrontReadingLampStatus();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH /* 4691 */:
                            bYDAutoEventValue.intValue = getRightFrontReadingLampStatus();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH /* 4692 */:
                            bYDAutoEventValue.intValue = getLeftMiddleRowSideRLightSwitch();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH /* 4693 */:
                            bYDAutoEventValue.intValue = getRightMiddleRowRLightSwitch();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH /* 4694 */:
                            bYDAutoEventValue.intValue = getLeftMiddleRowRLightRotaryState();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH /* 4695 */:
                            bYDAutoEventValue.intValue = getRightMiddleRowRLightRotary();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH /* 4696 */:
                            bYDAutoEventValue.intValue = getLeftRearRLightSwitch();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH /* 4697 */:
                            bYDAutoEventValue.intValue = getRightRearRLightSwitch();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_INTELLIGENT_POSITION_LIGHT_SCHEME_FEEDBACK /* 4698 */:
                            bYDAutoEventValue.intValue = getIntelligentPostionFeedback();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_INTELLIGENT_POSITION_LIGHT_SCHEME_FEEDBACK, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_REAR_GLOW_LOGO_WORK_STATE /* 4743 */:
                            bYDAutoEventValue.intValue = getLightRearGlowLogoWorkState();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_REAR_GLOW_LOGO_WORK_STATE, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHTS_ADAS_INDICATOR_LIGHT_STATUS /* 4744 */:
                            bYDAutoEventValue.intValue = getLightAdasIndicatorStatus();
                            sparseArray.put(BYDAutoFeatureIds.LIGHTS_ADAS_INDICATOR_LIGHT_STATUS, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_FRONT_GLOW_LOGO_WORK_STATE /* 4745 */:
                            bYDAutoEventValue.intValue = getLightFrontGlowLogoWorkState();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_FRONT_GLOW_LOGO_WORK_STATE, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_MAIN_SWITCH /* 4747 */:
                            bYDAutoEventValue.intValue = getAmbientState();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_MAIN_SWITCH, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_MASTER_SWITCH_FLAG /* 4748 */:
                            bYDAutoEventValue.intValue = getAmbientSwitchConfig();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_MASTER_SWITCH_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_AMBIENT_LIGHT_VEHICLE_DISPLAY_FEEDBACK /* 4749 */:
                            bYDAutoEventValue.intValue = getAtmosphereLampBodyDisplay();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_AMBIENT_LIGHT_VEHICLE_DISPLAY_FEEDBACK, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE_CONFIG /* 4750 */:
                            bYDAutoEventValue.intValue = getAtmospereLightNightWeakenModeConfig();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE /* 4751 */:
                            bYDAutoEventValue.intValue = getAtmospereLightNightWeakenMode();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_BRIGHTNESS_REDUCE_POPUP_PROMPT /* 4753 */:
                            bYDAutoEventValue.intValue = getAtmospereLampPopupPrompt();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_BRIGHTNESS_REDUCE_POPUP_PROMPT, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.SETTING_THEME_COLOR_SWITCH_STATE /* 4754 */:
                            bYDAutoEventValue.intValue = getThemeColorSwitchState();
                            sparseArray.put(BYDAutoFeatureIds.SETTING_THEME_COLOR_SWITCH_STATE, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_UI_DISPLAY /* 4756 */:
                            bYDAutoEventValue.intValue = getAtmospereLampUiDisplay();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_UI_DISPLAY, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.LIGHT_THEME_COLOR_FEATURE_FLAG /* 4757 */:
                            bYDAutoEventValue.intValue = getThemeColorFeatureFlag();
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_THEME_COLOR_FEATURE_FLAG, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.SETTING_THEME_COLOR_FEEDBACK /* 4758 */:
                            bYDAutoEventValue.intValue = getThemeColorFeedBack();
                            sparseArray.put(BYDAutoFeatureIds.SETTING_THEME_COLOR_FEEDBACK, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        default:
                            switch (i) {
                                case 1160:
                                    bYDAutoEventValue.intValue = getIalBrightness(1);
                                    sparseArray.put(1160, Integer.valueOf(bYDAutoEventValue.intValue));
                                    break;
                                case 1161:
                                    bYDAutoEventValue.intValue = getIalColor(1);
                                    sparseArray.put(1161, Integer.valueOf(bYDAutoEventValue.intValue));
                                    break;
                                case 1162:
                                    bYDAutoEventValue.intValue = getDoorLightState();
                                    sparseArray.put(1162, Integer.valueOf(bYDAutoEventValue.intValue));
                                    break;
                                case 1163:
                                    bYDAutoEventValue.intValue = getLightAutoStatus();
                                    sparseArray.put(1163, Integer.valueOf(bYDAutoEventValue.intValue));
                                    break;
                                default:
                                    switch (i) {
                                        case 2799:
                                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getStopLightState();
                                            sparseArray.put(2799, Integer.valueOf(bYDAutoEventValue.intValue));
                                            break;
                                        case 2800:
                                            int dayTimeLightState = getDayTimeLightState();
                                            if (dayTimeLightState >= 1 && dayTimeLightState <= 2) {
                                                bYDAutoEventValue.intValue = dayTimeLightState;
                                                sparseArray.put(2800, Integer.valueOf(bYDAutoEventValue.intValue));
                                            } else {
                                                int dayTimeRunningLightState = getDayTimeRunningLightState();
                                                if (dayTimeRunningLightState >= 1 && dayTimeRunningLightState <= 2) {
                                                    bYDAutoEventValue.intValue = dayTimeRunningLightState;
                                                    sparseArray.put(2800, Integer.valueOf(bYDAutoEventValue.intValue));
                                                } else {
                                                    bYDAutoEventValue.intValue = getDayTimeRunningLightState();
                                                    sparseArray.put(2800, Integer.valueOf(bYDAutoEventValue.intValue));
                                                }
                                            }
                                            break;
                                        case 2801:
                                            bYDAutoEventValue.intValue = getDayTimeLightState();
                                            sparseArray.put(2801, Integer.valueOf(bYDAutoEventValue.intValue));
                                            break;
                                        default:
                                            switch (i) {
                                                case 2844:
                                                    bYDAutoEventValue.intValue = getIalBrightness(5);
                                                    sparseArray.put(2844, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 2845:
                                                    bYDAutoEventValue.intValue = getAmbRingColor();
                                                    sparseArray.put(2845, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 2846:
                                                    bYDAutoEventValue.intValue = getIalBrightness(4);
                                                    sparseArray.put(2846, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 2847:
                                                    bYDAutoEventValue.intValue = getIalColor(4);
                                                    sparseArray.put(2847, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 2848:
                                                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
                                                    sparseArray.put(2848, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS /* 4666 */:
                                                            bYDAutoEventValue.intValue = getLeftCornerLightStatus();
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS, Integer.valueOf(bYDAutoEventValue.intValue));
                                                            break;
                                                        case BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS /* 4667 */:
                                                            bYDAutoEventValue.intValue = getRightCornerLightStatus();
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS, Integer.valueOf(bYDAutoEventValue.intValue));
                                                            break;
                                                        case BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS /* 4668 */:
                                                            bYDAutoEventValue.intValue = getFrontFogLampStatus();
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS, Integer.valueOf(bYDAutoEventValue.intValue));
                                                            break;
                                                        case BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS /* 4669 */:
                                                            bYDAutoEventValue.intValue = getFrontCenterLightStatus();
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS, Integer.valueOf(bYDAutoEventValue.intValue));
                                                            break;
                                                        case BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK /* 4670 */:
                                                            bYDAutoEventValue.intValue = getPositionLightDisplayFeedCallback();
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK, Integer.valueOf(bYDAutoEventValue.intValue));
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
                } else {
                    bYDAutoEventValue.intValue = getHightlightPositionConfig();
                    sparseArray.put(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
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
        if (i == 1156) {
            setIalBrightness(2, iIntValue, 2);
            Log.v(TAG, "setIALBrightnessBack:" + iIntValue);
        } else if (i == 1157) {
            setIalColor(2, iIntValue, 1);
            Log.v(TAG, "setIALColorFrontBack:" + iIntValue);
        } else if (i == 1160) {
            setIalBrightness(1, iIntValue, 1);
            Log.v(TAG, "setIALBrightnessFront:" + iIntValue);
        } else if (i == 1161) {
            setIalColor(1, iIntValue, 1);
            Log.v(TAG, "setIALColorFront:" + iIntValue);
        } else if (i == 2807) {
            setAmbientMulticolorMode(0, iIntValue);
            Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE + iIntValue);
        } else if (i == 2808) {
            setAmbientMulticolorState(1, iIntValue);
            Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE + iIntValue);
        } else {
            switch (i) {
                case 1164:
                    setDoorLightState(iIntValue);
                    Log.v(TAG, "Setting.SET_INSIDE_LIGHT_DOOR_STATE_SET:");
                    break;
                case 1257:
                    setAmbientMusicModeState(0, iIntValue);
                    Log.v(TAG, "SET_MUSIC_MODE_STATE_SET: " + iIntValue);
                    break;
                case 2802:
                    setDayTimeLightState(iIntValue);
                    Log.v(TAG, TestLightManagerImplCallback.SETDAYTIMELIGHTSTATE + iIntValue);
                    break;
                case 2805:
                    setAmbientMulticolorMode(1, iIntValue);
                    Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE + iIntValue);
                    break;
                case 2811:
                    setAmbientMulticolorState(0, iIntValue);
                    Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE + iIntValue);
                    break;
                case 2831:
                    setAmbientMusicMode(0, iIntValue);
                    Log.v(TAG, "Light.LIGHT_MUSIC_RHYTHM_MODE_SET:");
                    break;
                case BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH_SET /* 4665 */:
                    setCargoLightSwitchState(iIntValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_SET /* 4671 */:
                    setPositionLightDisplay(iIntValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT_SET /* 4674 */:
                    setHighlightPosition(iIntValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_MAIN_SWITCH_SET /* 4746 */:
                    setAmbientState(iIntValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE_SET /* 4752 */:
                    setAtmospereLightNightWeakenMode(iIntValue);
                    break;
                case BYDAutoFeatureIds.SETTING_THEME_LINKED_AMBIENCE_LIGHT_COLOR_SET /* 4755 */:
                    setThemeLinkedAmbienceLightColor(iIntValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_THEME_LINKED_AATMOSPHERE_LAMP_SWITCH_SET /* 4759 */:
                    setThemeLinkedAtmosphereLampSwitch(iIntValue);
                    break;
                default:
                    switch (i) {
                        case 2844:
                            setIalBrightness(5, iIntValue, 1);
                            Log.v(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP_BRIGHTNESS:" + iIntValue);
                            break;
                        case 2845:
                            setIalColor(5, iIntValue, 1);
                            Log.v(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP:" + iIntValue);
                            break;
                        case 2846:
                            setIalBrightness(iIntValue, 4, 1);
                            Log.v(TAG, "Light.LIGHT_STAR_SKY_ATMOSPHERE_LAMP_BRIGHTNESS:" + iIntValue);
                            break;
                        case 2847:
                            setIalColor(4, iIntValue, 1);
                            Log.v(TAG, "LIGHT_STAR_SKY_ATMOSPHERE_LAMP:" + iIntValue);
                            break;
                        default:
                            switch (i) {
                                case BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH_SET /* 4655 */:
                                    setLeftFrontReadingLampSwitchState(iIntValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH_SET /* 4656 */:
                                    setRightFrontReadingLampSwitchState(iIntValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH_SET /* 4657 */:
                                    setLeftMiddleRowSideRLightSwitchState(iIntValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH_SET /* 4658 */:
                                    setRightMiddleRowRLightSwitchState(iIntValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH_SET /* 4659 */:
                                    setLeftMiddleRowRLightRotaryState(iIntValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH_SET /* 4660 */:
                                    setRightMiddleRowRLightRotaryState(iIntValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH_SET /* 4661 */:
                                    setLeftRearRLightSwitchState(iIntValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH_SET /* 4662 */:
                                    setRightRearRLightSwitchState(iIntValue);
                                    break;
                                default:
                                    Log.v(TAG, "case fail ! please check out");
                                    return -1;
                            }
                            break;
                    }
                    break;
            }
        }
        return 1;
    }
}
