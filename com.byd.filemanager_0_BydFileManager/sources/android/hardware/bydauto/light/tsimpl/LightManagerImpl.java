package android.hardware.bydauto.light.tsimpl;

import a.b.d;
import a.b.e.k;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class LightManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_LightManagerImpl";
    private static final String TAG = "LightManagerImpl";
    private static volatile LightManagerImpl sInstance;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private k mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoLightListener> mAbsBydAutoLightListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    private LightManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new k();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.light.tsimpl.LightManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                Log.v(LightManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (LightManagerImpl.this.mAbsBydAutoLightListeners.isEmpty()) {
                    Log.v(LightManagerImpl.TAG, "mAbsBydAutoLightListeners is null. ");
                } else {
                    LightManagerImpl.this.lightCallback(i2, i3, carBodyPacket);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void lightCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "LightCallback propertyId:" + i);
        switch (i) {
            case LightHalId.ID_DAY_LIGHT_SUPPORT /* 557856041 */:
                caseFeatureIds(2800, Integer.valueOf(carBodyPacket.getDayLightSupport()));
                break;
            case LightHalId.ID_AMBIENT_COLORS_SUPPORT /* 557856188 */:
                caseFeatureIds(4537, Integer.valueOf(carBodyPacket.getAmbientColorsSupport()));
                break;
            case 557857710:
                Iterator<AbsBYDAutoLightListener> it = this.mAbsBydAutoLightListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAmbRingColorChanged(carBodyPacket.getIalColorStarRingR());
                }
                caseFeatureIds(2845, Integer.valueOf(carBodyPacket.getIalColorStarRingR()));
                break;
            case 557857711:
                Iterator<AbsBYDAutoLightListener> it2 = this.mAbsBydAutoLightListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onIALColorChanged(4, carBodyPacket.getIalColorStarrySkyR());
                }
                caseFeatureIds(2847, Integer.valueOf(carBodyPacket.getIalColorStarrySkyR()));
                break;
            case 557857712:
                Iterator<AbsBYDAutoLightListener> it3 = this.mAbsBydAutoLightListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onIALColorChanged(1, carBodyPacket.getIalColorFrontR());
                }
                caseFeatureIds(1161, Integer.valueOf(carBodyPacket.getIalColorFrontR()));
                break;
            case 557857713:
                Iterator<AbsBYDAutoLightListener> it4 = this.mAbsBydAutoLightListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onIALColorChanged(2, carBodyPacket.getIalColorBackR());
                }
                caseFeatureIds(1157, Integer.valueOf(carBodyPacket.getIalColorBackR()));
                break;
            case 557857716:
                Iterator<AbsBYDAutoLightListener> it5 = this.mAbsBydAutoLightListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onAmbRingBrightnessChanged(carBodyPacket.getIalBrightnessStarRingR());
                }
                caseFeatureIds(2844, Integer.valueOf(carBodyPacket.getIalBrightnessStarRingR()));
                break;
            case 557857717:
                Iterator<AbsBYDAutoLightListener> it6 = this.mAbsBydAutoLightListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onIALBrightnessChanged(4, carBodyPacket.getIalBrightnessStarrySkyR());
                }
                caseFeatureIds(2846, Integer.valueOf(carBodyPacket.getIalBrightnessStarrySkyR()));
                break;
            case 557857718:
                Iterator<AbsBYDAutoLightListener> it7 = this.mAbsBydAutoLightListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onIALBrightnessChanged(2, carBodyPacket.getIalBrightnessBackR());
                }
                caseFeatureIds(1156, Integer.valueOf(carBodyPacket.getIalBrightnessBackR()));
                break;
            case 557857719:
                Iterator<AbsBYDAutoLightListener> it8 = this.mAbsBydAutoLightListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onIALBrightnessChanged(1, carBodyPacket.getIalBrightnessFrontR());
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
                Iterator<AbsBYDAutoLightListener> it9 = this.mAbsBydAutoLightListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onAFSSwitchStateChange(afsSwitch);
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
                Iterator<AbsBYDAutoLightListener> it10 = this.mAbsBydAutoLightListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onAmbientMulticolorModeChanged(areaId4, i8);
                }
                caseFeatureIds(2805, Integer.valueOf(i8));
                break;
            case LightHalId.ID_DAY_TIME_LIGHT_STATE_R /* 557860619 */:
                int i9 = carBodyPacket.getmDayTimeLightStateR();
                Iterator<AbsBYDAutoLightListener> it11 = this.mAbsBydAutoLightListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onDayTimeLightChanged(i9);
                }
                break;
            case LightHalId.ID_REVERSING_LIGHT_STATE /* 557860629 */:
                int reversingLightState = carBodyPacket.getReversingLightState();
                Iterator<AbsBYDAutoLightListener> it12 = this.mAbsBydAutoLightListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onReversingLightStateChanged(reversingLightState);
                }
                caseFeatureIds(2797, Integer.valueOf(reversingLightState));
                break;
            case LightHalId.ID_TURN_LIGHT_STATE /* 557860630 */:
                int turnLightState = carBodyPacket.getTurnLightState();
                for (AbsBYDAutoLightListener absBYDAutoLightListener6 : this.mAbsBydAutoLightListeners) {
                    Log.e(TAG, "onTurnLightStateChanged in:" + turnLightState + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener6));
                    absBYDAutoLightListener6.onTurnLightStateChanged(turnLightState);
                }
                Log.e(TAG, "onTurnLightStateChanged out:" + turnLightState);
                caseFeatureIds(2848, Integer.valueOf(turnLightState));
                break;
            case 557860631:
                int ambientFeedBack = carBodyPacket.getAmbientFeedBack();
                Iterator<AbsBYDAutoLightListener> it13 = this.mAbsBydAutoLightListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onAmbientFeedBackChanged(ambientFeedBack);
                }
                caseFeatureIds(902, Integer.valueOf(ambientFeedBack));
                break;
            case LightHalId.ID_LIGHT_LOW_BEAM_STATUSR /* 557860637 */:
                int lightLowBeamStatusR = carBodyPacket.getLightLowBeamStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener7 : this.mAbsBydAutoLightListeners) {
                    if (lightLowBeamStatusR == 1) {
                        absBYDAutoLightListener7.onLightOn(2);
                        Log.v(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener7));
                    } else {
                        absBYDAutoLightListener7.onLightOff(2);
                        Log.v(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener7));
                    }
                }
                caseFeatureIds(2827, Integer.valueOf(lightLowBeamStatusR));
                Log.e(TAG, "LIGHT_LOW_BEAM_LIGHT caseFeatureIds is in");
                break;
            case LightHalId.ID_LIGHT_HIGH_BEAMR /* 557860638 */:
                int lightHighBeamR = carBodyPacket.getLightHighBeamR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener8 : this.mAbsBydAutoLightListeners) {
                    if (lightHighBeamR == 1) {
                        absBYDAutoLightListener8.onLightOn(3);
                        Log.v(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener8));
                    } else {
                        absBYDAutoLightListener8.onLightOff(3);
                        Log.v(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener8));
                    }
                }
                caseFeatureIds(2820, Integer.valueOf(lightHighBeamR));
                Log.e(TAG, "LIGHT_HIGH_BEAM_LIGHT caseFeatureIds is in");
                break;
            case LightHalId.ID_LIGHT_REAR_FOGR /* 557860642 */:
                int lightRearFog = carBodyPacket.getLightRearFog();
                for (AbsBYDAutoLightListener absBYDAutoLightListener9 : this.mAbsBydAutoLightListeners) {
                    if (lightRearFog == 1) {
                        absBYDAutoLightListener9.onLightOn(7);
                        Log.v(TAG, "ID_LIGHT_REAR_FOGR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener9));
                    } else {
                        absBYDAutoLightListener9.onLightOff(7);
                        Log.v(TAG, "ID_LIGHT_REAR_FOGR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener9));
                    }
                }
                caseFeatureIds(2832, Integer.valueOf(lightRearFog));
                Log.e(TAG, "LIGHT_REAR_FOG_LAMPS caseFeatureIds is in");
                break;
            case LightHalId.ID_DAY_TIME_RUNNING_LIGHT_STATE_R /* 557860643 */:
                int dayTimeRunningLightState = carBodyPacket.getDayTimeRunningLightState();
                Iterator<AbsBYDAutoLightListener> it14 = this.mAbsBydAutoLightListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onDayTimeRunningLitghtStateChanged(dayTimeRunningLightState);
                }
                break;
            case 557860644:
                int lightRearPosition = carBodyPacket.getLightRearPosition();
                Iterator<AbsBYDAutoLightListener> it15 = this.mAbsBydAutoLightListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onRearLampLightStateChanged(lightRearPosition);
                }
                break;
            case LightHalId.ID_BACKLIGHT_LEVEL_R /* 557860648 */:
                Iterator<AbsBYDAutoLightListener> it16 = this.mAbsBydAutoLightListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onBacklightLevelsChanged(carBodyPacket.getBacklightLevelR());
                }
                break;
            case LightHalId.ID_LEFT_FRONT_READING_LAMP_STATUS_R /* 557860657 */:
                Iterator<AbsBYDAutoLightListener> it17 = this.mAbsBydAutoLightListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onLeftFrontReadingLampStatusChanged(carBodyPacket.getLeftFrontReadingLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getLeftFrontReadingLampStatusR()));
                break;
            case LightHalId.ID_RIGHT_FRONT_READING_LAMP_STATUS_R /* 557860658 */:
                Iterator<AbsBYDAutoLightListener> it18 = this.mAbsBydAutoLightListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onRightFrontReadingLampStatusChanged(carBodyPacket.getRightFrontReadingLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getRightFrontReadingLampStatusR()));
                break;
            case LightHalId.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R /* 557860659 */:
                Iterator<AbsBYDAutoLightListener> it19 = this.mAbsBydAutoLightListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onLeftMiddleRowSideRLightSwitchChanged(carBodyPacket.getLeftMiddleRowSideRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getLeftMiddleRowSideRLightSwitchR()));
                break;
            case LightHalId.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R /* 557860660 */:
                Iterator<AbsBYDAutoLightListener> it20 = this.mAbsBydAutoLightListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onRightMiddleRowRLightSwitch(carBodyPacket.getRightMiddleRowRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getRightMiddleRowRLightSwitchR()));
                break;
            case LightHalId.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R /* 557860661 */:
                Iterator<AbsBYDAutoLightListener> it21 = this.mAbsBydAutoLightListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onLeftMiddleRowRLightRotaryStateChanged(carBodyPacket.getLeftMiddleRowRLightRotaryStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(carBodyPacket.getLeftMiddleRowRLightRotaryStateR()));
                break;
            case LightHalId.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R /* 557860662 */:
                Iterator<AbsBYDAutoLightListener> it22 = this.mAbsBydAutoLightListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onRightMiddleRowRLightRotaryChanged(carBodyPacket.getRightMiddleRowRLightRotaryR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(carBodyPacket.getRightMiddleRowRLightRotaryR()));
                break;
            case LightHalId.ID_LEFT_REAR_R_LIGHT_SWITCH_R /* 557860663 */:
                Iterator<AbsBYDAutoLightListener> it23 = this.mAbsBydAutoLightListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onLeftRearRLightSwitchChanged(carBodyPacket.getLeftRearRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getLeftRearRLightSwitchR()));
                break;
            case LightHalId.ID_RIGHT_REAR_R_LIGHT_SWITCH_R /* 557860664 */:
                Iterator<AbsBYDAutoLightListener> it24 = this.mAbsBydAutoLightListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onRightRearRLightSwitchChanged(carBodyPacket.getRightRearRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getRightRearRLightSwitchR()));
                break;
            case LightHalId.ID_CARGO_LIGHT_SWITCH_STATE_R /* 557860665 */:
                Iterator<AbsBYDAutoLightListener> it25 = this.mAbsBydAutoLightListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onCargoLightSwitchStateChanged(carBodyPacket.getCargoLightSwitchStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH, Integer.valueOf(carBodyPacket.getCargoLightSwitchStateR()));
                break;
            case LightHalId.ID_LEFT_CORNER_LIGHT_STATUS_R /* 557860668 */:
                Iterator<AbsBYDAutoLightListener> it26 = this.mAbsBydAutoLightListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onLeftCornerLightStatusChanged(carBodyPacket.getLeftCornerLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getLeftCornerLightStatusR()));
                break;
            case LightHalId.ID_RIGHT_CORNER_LIGHT_STATUS_R /* 557860669 */:
                Iterator<AbsBYDAutoLightListener> it27 = this.mAbsBydAutoLightListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onRightCornerLightStatusChanged(carBodyPacket.getRightCornerLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getRightCornerLightStatusR()));
                break;
            case LightHalId.ID_FRONT_FOG_LAMP_STATUS_R /* 557860670 */:
                Iterator<AbsBYDAutoLightListener> it28 = this.mAbsBydAutoLightListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onFrontFogLampStatusChanged(carBodyPacket.getFrontFogLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS, Integer.valueOf(carBodyPacket.getFrontFogLampStatusR()));
                break;
            case LightHalId.ID_FRONT_CENTER_LIGHT_STATUS_R /* 557860671 */:
                Iterator<AbsBYDAutoLightListener> it29 = this.mAbsBydAutoLightListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onFrontCenterLightStatusChanged(carBodyPacket.getFrontCenterLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS, Integer.valueOf(carBodyPacket.getFrontCenterLightStatusR()));
                break;
            case LightHalId.ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R /* 557860672 */:
                Iterator<AbsBYDAutoLightListener> it30 = this.mAbsBydAutoLightListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onPositionLightDisplayFeedCallbackChanged(carBodyPacket.getPositionLightDisplayFeedCallbackR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK, Integer.valueOf(carBodyPacket.getPositionLightDisplayFeedCallbackR()));
                break;
            case LightHalId.ID_RIGHT_DAYTIME_LIGHT_STATE_R /* 557860674 */:
                Iterator<AbsBYDAutoLightListener> it31 = this.mAbsBydAutoLightListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onRightDaytimeLightStateChanged(carBodyPacket.getRightDaytimeLightStateR());
                }
                caseFeatureIds(2836, Integer.valueOf(carBodyPacket.getRightDaytimeLightStateR()));
                break;
            case LightHalId.ID_LEFT_DAYTIME_LIGHT_STATE_R /* 557860675 */:
                Iterator<AbsBYDAutoLightListener> it32 = this.mAbsBydAutoLightListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onLeftDaytimeLightStateChanged(carBodyPacket.getLeftDaytimeLightStateR());
                }
                caseFeatureIds(2823, Integer.valueOf(carBodyPacket.getLeftDaytimeLightStateR()));
                break;
            case LightHalId.ID_HIGHTLIGHT_POSITION_R /* 557860677 */:
                Iterator<AbsBYDAutoLightListener> it33 = this.mAbsBydAutoLightListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onHightlightPositionChanged(carBodyPacket.getHightlightPositionR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT, Integer.valueOf(carBodyPacket.getHightlightPositionR()));
                break;
            case LightHalId.ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R /* 557860680 */:
                Iterator<AbsBYDAutoLightListener> it34 = this.mAbsBydAutoLightListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onLeftFrontPositionSWLStatusChanged(carBodyPacket.getLeftFrontPositionSwlStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LEFT_FRONT_POSITION_SPATIOTEMPORAL_WING_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getLeftFrontPositionSwlStatusR()));
                break;
            case LightHalId.ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R /* 557860681 */:
                Iterator<AbsBYDAutoLightListener> it35 = this.mAbsBydAutoLightListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onRightFrontPositionSWLStatusChanged(carBodyPacket.getRightFrontPositionSwlStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RIGHT_FRONT_POSITION_SPATIOTEMPORAL_WING_LIGHT_STATUS, Integer.valueOf(carBodyPacket.getRightFrontPositionSwlStatusR()));
                break;
            case LightHalId.ID_LEFT_REMOTE_LIGHT_STATE_R /* 557860688 */:
                Iterator<AbsBYDAutoLightListener> it36 = this.mAbsBydAutoLightListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onLeftRemoteLightStateChanged(carBodyPacket.getLeftRemoteLightStateR());
                }
                break;
            case LightHalId.ID_RIGHT_REMOTE_LIGHT_STATE_R /* 557860689 */:
                Iterator<AbsBYDAutoLightListener> it37 = this.mAbsBydAutoLightListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onRightRemoteLightStateChanged(carBodyPacket.getRightRemoteLightStateR());
                }
                break;
            case LightHalId.ID_STOP_LIGHT_STATE /* 557870867 */:
                int stopLightStateR = carBodyPacket.getStopLightStateR();
                Iterator<AbsBYDAutoLightListener> it38 = this.mAbsBydAutoLightListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onStopLightStateChanged(stopLightStateR);
                }
                caseFeatureIds(2799, Integer.valueOf(stopLightStateR));
                break;
            case LightHalId.ID_LIGHT_SIDE_STATUSR /* 557879809 */:
                int lightSideStatusR = carBodyPacket.getLightSideStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener10 : this.mAbsBydAutoLightListeners) {
                    Log.e(TAG, "mAbsBydAutoLightListeners toString : " + System.identityHashCode(this.mAbsBydAutoLightListeners));
                    Log.e(TAG, "mAbsBydAutoLightListeners is size : " + this.mAbsBydAutoLightListeners.size());
                    if (lightSideStatusR == 1) {
                        absBYDAutoLightListener10.onLightOn(1);
                        Log.e(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    } else {
                        absBYDAutoLightListener10.onLightOff(1);
                        Log.e(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    }
                }
                caseFeatureIds(2842, Integer.valueOf(lightSideStatusR));
                Log.e(TAG, "LIGHT_SIDE_LIGHT caseFeatureIds is in lightSide = " + lightSideStatusR);
                break;
        }
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
                    int ialBrightness = getIalBrightness(2);
                    bYDAutoEventValue.intValue = ialBrightness;
                    sparseArray.put(1156, Integer.valueOf(ialBrightness));
                } else if (i == 1157) {
                    int ialColor = getIalColor(2);
                    bYDAutoEventValue.intValue = ialColor;
                    sparseArray.put(1157, Integer.valueOf(ialColor));
                } else if (i == 2807) {
                    int ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                    sparseArray.put(2807, Integer.valueOf(ambientMulticolorMode));
                } else if (i == 2808) {
                    int ambientMulticolorState = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(0);
                    bYDAutoEventValue.intValue = ambientMulticolorState;
                    sparseArray.put(2808, Integer.valueOf(ambientMulticolorState));
                } else if (i == 2832) {
                    int lightRearFog = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                    bYDAutoEventValue.intValue = lightRearFog;
                    sparseArray.put(2832, Integer.valueOf(lightRearFog));
                } else if (i == 2833) {
                    int lightRearFog2 = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                    bYDAutoEventValue.intValue = lightRearFog2;
                    sparseArray.put(2833, Integer.valueOf(lightRearFog2));
                } else if (i == 4663) {
                    int cargoLightSwitchState = getCargoLightSwitchState();
                    bYDAutoEventValue.intValue = cargoLightSwitchState;
                    sparseArray.put(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH, Integer.valueOf(cargoLightSwitchState));
                } else if (i == 4664) {
                    int cargoLightSwitchConfig = getCargoLightSwitchConfig();
                    bYDAutoEventValue.intValue = cargoLightSwitchConfig;
                    sparseArray.put(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH_CONFIG, Integer.valueOf(cargoLightSwitchConfig));
                } else if (i == 4675) {
                    int hightlightPosition = getHightlightPosition();
                    bYDAutoEventValue.intValue = hightlightPosition;
                    sparseArray.put(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT, Integer.valueOf(hightlightPosition));
                } else if (i != 4676) {
                    switch (i) {
                        case 890:
                            int aFSSwitch = getAFSSwitch();
                            bYDAutoEventValue.intValue = aFSSwitch;
                            sparseArray.put(890, Integer.valueOf(aFSSwitch));
                            break;
                        case 902:
                            int ambientFeedBack = this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
                            bYDAutoEventValue.intValue = ambientFeedBack;
                            sparseArray.put(902, Integer.valueOf(ambientFeedBack));
                            break;
                        case 1171:
                            int lastSetedArea = this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
                            bYDAutoEventValue.intValue = lastSetedArea;
                            sparseArray.put(1171, Integer.valueOf(lastSetedArea));
                            break;
                        case 1255:
                            int ambientMusicModeState = getAmbientMusicModeState(1);
                            bYDAutoEventValue.intValue = ambientMusicModeState;
                            sparseArray.put(1255, Integer.valueOf(ambientMusicModeState));
                            break;
                        case 2006:
                            int lightRearPosition = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                            bYDAutoEventValue.intValue = lightRearPosition;
                            sparseArray.put(2006, Integer.valueOf(lightRearPosition));
                            break;
                        case 2797:
                            int reversingLightState = this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
                            bYDAutoEventValue.intValue = reversingLightState;
                            sparseArray.put(2797, Integer.valueOf(reversingLightState));
                            break;
                        case 2805:
                            int ambientMulticolorMode2 = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                            bYDAutoEventValue.intValue = ambientMulticolorMode2;
                            sparseArray.put(2805, Integer.valueOf(ambientMulticolorMode2));
                            break;
                        case 2811:
                            int ambientMulticolorState2 = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(1);
                            bYDAutoEventValue.intValue = ambientMulticolorState2;
                            sparseArray.put(2811, Integer.valueOf(ambientMulticolorState2));
                            break;
                        case 2814:
                            int lightStatus = getLightStatus(6);
                            bYDAutoEventValue.intValue = lightStatus;
                            sparseArray.put(2814, Integer.valueOf(lightStatus));
                            break;
                        case 2820:
                            int lightHighBeam = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                            bYDAutoEventValue.intValue = lightHighBeam;
                            sparseArray.put(2820, Integer.valueOf(lightHighBeam));
                            break;
                        case 2823:
                            int leftDaytimeLightState = getLeftDaytimeLightState();
                            bYDAutoEventValue.intValue = leftDaytimeLightState;
                            sparseArray.put(2823, Integer.valueOf(leftDaytimeLightState));
                            break;
                        case 2827:
                            int lightLowBeamStatus = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                            bYDAutoEventValue.intValue = lightLowBeamStatus;
                            sparseArray.put(2827, Integer.valueOf(lightLowBeamStatus));
                            break;
                        case 2829:
                            int ambientMusicMode = getAmbientMusicMode(1);
                            bYDAutoEventValue.intValue = ambientMusicMode;
                            sparseArray.put(2829, Integer.valueOf(ambientMusicMode));
                            break;
                        case 2836:
                            int rightDaytimeLightState = getRightDaytimeLightState();
                            bYDAutoEventValue.intValue = rightDaytimeLightState;
                            sparseArray.put(2836, Integer.valueOf(rightDaytimeLightState));
                            break;
                        case 2842:
                            int lightStatus2 = getLightStatus(1);
                            bYDAutoEventValue.intValue = lightStatus2;
                            sparseArray.put(2842, Integer.valueOf(lightStatus2));
                            break;
                        case 4537:
                            int ambientColorsSupport = getAmbientColorsSupport();
                            bYDAutoEventValue.intValue = ambientColorsSupport;
                            sparseArray.put(4537, Integer.valueOf(ambientColorsSupport));
                            break;
                        case 4616:
                            int backlightLevels = getBacklightLevels();
                            bYDAutoEventValue.intValue = backlightLevels;
                            sparseArray.put(4616, Integer.valueOf(backlightLevels));
                            break;
                        case BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH /* 4690 */:
                            int leftFrontReadingLampStatus = getLeftFrontReadingLampStatus();
                            bYDAutoEventValue.intValue = leftFrontReadingLampStatus;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH, Integer.valueOf(leftFrontReadingLampStatus));
                            break;
                        case BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH /* 4691 */:
                            int rightFrontReadingLampStatus = getRightFrontReadingLampStatus();
                            bYDAutoEventValue.intValue = rightFrontReadingLampStatus;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH, Integer.valueOf(rightFrontReadingLampStatus));
                            break;
                        case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH /* 4692 */:
                            int leftMiddleRowSideRLightSwitch = getLeftMiddleRowSideRLightSwitch();
                            bYDAutoEventValue.intValue = leftMiddleRowSideRLightSwitch;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH, Integer.valueOf(leftMiddleRowSideRLightSwitch));
                            break;
                        case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH /* 4693 */:
                            int rightMiddleRowRLightSwitch = getRightMiddleRowRLightSwitch();
                            bYDAutoEventValue.intValue = rightMiddleRowRLightSwitch;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH, Integer.valueOf(rightMiddleRowRLightSwitch));
                            break;
                        case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH /* 4694 */:
                            int leftMiddleRowRLightRotaryState = getLeftMiddleRowRLightRotaryState();
                            bYDAutoEventValue.intValue = leftMiddleRowRLightRotaryState;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(leftMiddleRowRLightRotaryState));
                            break;
                        case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH /* 4695 */:
                            int rightMiddleRowRLightRotary = getRightMiddleRowRLightRotary();
                            bYDAutoEventValue.intValue = rightMiddleRowRLightRotary;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(rightMiddleRowRLightRotary));
                            break;
                        case BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH /* 4696 */:
                            int leftRearRLightSwitch = getLeftRearRLightSwitch();
                            bYDAutoEventValue.intValue = leftRearRLightSwitch;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH, Integer.valueOf(leftRearRLightSwitch));
                            break;
                        case BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH /* 4697 */:
                            int rightRearRLightSwitch = getRightRearRLightSwitch();
                            bYDAutoEventValue.intValue = rightRearRLightSwitch;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH, Integer.valueOf(rightRearRLightSwitch));
                            break;
                        case BYDAutoFeatureIds.LIGHT_INTELLIGENT_POSITION_LIGHT_SCHEME_FEEDBACK /* 4698 */:
                            int intelligentPostionFeedback = getIntelligentPostionFeedback();
                            bYDAutoEventValue.intValue = intelligentPostionFeedback;
                            sparseArray.put(BYDAutoFeatureIds.LIGHT_INTELLIGENT_POSITION_LIGHT_SCHEME_FEEDBACK, Integer.valueOf(intelligentPostionFeedback));
                            break;
                        default:
                            switch (i) {
                                case 1160:
                                    int ialBrightness2 = getIalBrightness(1);
                                    bYDAutoEventValue.intValue = ialBrightness2;
                                    sparseArray.put(1160, Integer.valueOf(ialBrightness2));
                                    break;
                                case 1161:
                                    int ialColor2 = getIalColor(1);
                                    bYDAutoEventValue.intValue = ialColor2;
                                    sparseArray.put(1161, Integer.valueOf(ialColor2));
                                    break;
                                case 1162:
                                    int doorLightState = getDoorLightState();
                                    bYDAutoEventValue.intValue = doorLightState;
                                    sparseArray.put(1162, Integer.valueOf(doorLightState));
                                    break;
                                case 1163:
                                    int lightAutoStatus = getLightAutoStatus();
                                    bYDAutoEventValue.intValue = lightAutoStatus;
                                    sparseArray.put(1163, Integer.valueOf(lightAutoStatus));
                                    break;
                                default:
                                    switch (i) {
                                        case 2799:
                                            int stopLightState = this.mTsManagerImpl.getCarBodyManager().getStopLightState();
                                            bYDAutoEventValue.intValue = stopLightState;
                                            sparseArray.put(2799, Integer.valueOf(stopLightState));
                                            break;
                                        case 2800:
                                            int dayLightSupport = getDayLightSupport();
                                            bYDAutoEventValue.intValue = dayLightSupport;
                                            sparseArray.put(2800, Integer.valueOf(dayLightSupport));
                                            break;
                                        case 2801:
                                            int dayTimeLightState = getDayTimeLightState();
                                            bYDAutoEventValue.intValue = dayTimeLightState;
                                            sparseArray.put(2801, Integer.valueOf(dayTimeLightState));
                                            break;
                                        default:
                                            switch (i) {
                                                case 2844:
                                                    int ialBrightness3 = getIalBrightness(5);
                                                    bYDAutoEventValue.intValue = ialBrightness3;
                                                    sparseArray.put(2844, Integer.valueOf(ialBrightness3));
                                                    break;
                                                case 2845:
                                                    int ambRingColor = getAmbRingColor();
                                                    bYDAutoEventValue.intValue = ambRingColor;
                                                    sparseArray.put(2845, Integer.valueOf(ambRingColor));
                                                    break;
                                                case 2846:
                                                    int ialBrightness4 = getIalBrightness(4);
                                                    bYDAutoEventValue.intValue = ialBrightness4;
                                                    sparseArray.put(2846, Integer.valueOf(ialBrightness4));
                                                    break;
                                                case 2847:
                                                    int ialColor3 = getIalColor(4);
                                                    bYDAutoEventValue.intValue = ialColor3;
                                                    sparseArray.put(2847, Integer.valueOf(ialColor3));
                                                    break;
                                                case 2848:
                                                    int turnLightState = this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
                                                    bYDAutoEventValue.intValue = turnLightState;
                                                    sparseArray.put(2848, Integer.valueOf(turnLightState));
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS /* 4666 */:
                                                            int leftCornerLightStatus = getLeftCornerLightStatus();
                                                            bYDAutoEventValue.intValue = leftCornerLightStatus;
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS, Integer.valueOf(leftCornerLightStatus));
                                                            break;
                                                        case BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS /* 4667 */:
                                                            int rightCornerLightStatus = getRightCornerLightStatus();
                                                            bYDAutoEventValue.intValue = rightCornerLightStatus;
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS, Integer.valueOf(rightCornerLightStatus));
                                                            break;
                                                        case BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS /* 4668 */:
                                                            int frontFogLampStatus = getFrontFogLampStatus();
                                                            bYDAutoEventValue.intValue = frontFogLampStatus;
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS, Integer.valueOf(frontFogLampStatus));
                                                            break;
                                                        case BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS /* 4669 */:
                                                            int frontCenterLightStatus = getFrontCenterLightStatus();
                                                            bYDAutoEventValue.intValue = frontCenterLightStatus;
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS, Integer.valueOf(frontCenterLightStatus));
                                                            break;
                                                        case BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK /* 4670 */:
                                                            int positionLightDisplayFeedCallback = getPositionLightDisplayFeedCallback();
                                                            bYDAutoEventValue.intValue = positionLightDisplayFeedCallback;
                                                            sparseArray.put(BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK, Integer.valueOf(positionLightDisplayFeedCallback));
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
                    int hightlightPositionConfig = getHightlightPositionConfig();
                    bYDAutoEventValue.intValue = hightlightPositionConfig;
                    sparseArray.put(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT_CONFIG, Integer.valueOf(hightlightPositionConfig));
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getAFSSwitch() {
        Log.v(TAG, "getAFSSwitch is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAFSSwitch", new Object[0]);
            Log.v(TAG, "getAFSSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAFSSwitch();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbRingBrightness() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbRingBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarRing();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbRingColor() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbRingColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorStarRing();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int[] getAmbientColors(int i) {
        Log.v(TAG, "getAmbientColors is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientColors", Integer.valueOf(i));
            Log.v(TAG, "getAmbientColors mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientColors(i);
        }
        Log.v(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getAmbientColorsSupport() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientColorsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientColorsSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientFeedBack() {
        Log.v(TAG, "getAmbientFeedBack is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.v(TAG, "getAmbientFeedBack is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getAmbientFeedBack mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAmbientMulticolorMode(int i) {
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMulticolorMode", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMulticolorState(int i) {
        Log.v(TAG, "getAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMulticolorState", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicMode(int i) {
        Log.v(TAG, "getAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMusicMode", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicMode(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicModeState(int i) {
        Log.v(TAG, "getAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMusicModeState", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicRhym(int i) {
        Log.v(TAG, "getAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMusicRhym", Integer.valueOf(i));
            Log.v(TAG, "getAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicRhym(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getBacklightLevels() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBacklightLevel mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getBacklightLevel();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getCargoLightSwitchConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCargoLightSwitchConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getCargoLightSwitchConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getCargoLightSwitchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCargoLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getCargoLightSwitchState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDayLightSupport() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDayLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDayLightSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDayTimeLightState() {
        Log.v(TAG, "getDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getDayTimeLightState", new Object[0]);
            Log.v(TAG, "getDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDayTimeLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDayTimeRunningLightState() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getDayTimeRunningLightState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getDayTimeRunningLightState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    @Deprecated
    public int getDoorLightState() {
        Log.v(TAG, "getDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getDoorLightState", new Object[0]);
            Log.v(TAG, "getDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getFrontCenterLightStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontCenterLightStatus（） mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getFrontCenterLightStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getFrontFogLampStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontFogLampStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getFrontFogLampStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHightlightPosition() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHightlightPosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getHightlightPosition();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHightlightPositionConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHightlightPositionConfig（） mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getHightlightPositionConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getIalBrightness(int i) {
        Log.i(TAG, "getIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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

    public int getIalColor(int i) {
        Log.i(TAG, "getIALColor is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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

    public int getIntelligentPostionFeedback() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIntelligentPostionFeedback mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getIntelligentPostionFeedback();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLastSetedArea() {
        Log.v(TAG, "getLastSetedArea is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getLastSetedArea mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftCornerLightStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftCornerLightStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftCornerLightStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftDaytimeLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftDaytimeLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftDaytimeLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftFrontPositionSwlStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftFrontPositionSwlStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftFrontPositionSwlStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftFrontReadingLampStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftFrontReadingLampStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftFrontReadingLampStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftMiddleRowRLightRotaryState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftMiddleRowRLightRotaryState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftMiddleRowRLightRotaryState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftMiddleRowSideRLightSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftMiddleRowSideRLightSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftMiddleRowSideRLightSwitch();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftRearRLightSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftRearRLightSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftRearRLightSwitch();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftRemoteLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftRemoteLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftRemoteLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightAutoStatus() {
        Log.v(TAG, "getLightAutoStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getLightAutoStatus", new Object[0]);
            Log.v(TAG, "getLightAutoStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLightAutoStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightDelayTime(int i) {
        Log.v(TAG, "getLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getLightDelayTime", Integer.valueOf(i));
            Log.v(TAG, "getLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLightDelayTime(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightStatus(int i) {
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        int lightSideStatus = 0;
        if (1 == this.mForTest) {
            this.mTestCallback.c("getLightStatus", Integer.valueOf(i));
            Log.v(TAG, "getLightStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
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

    public int getPositionLightDisplayFeedCallback() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getPositionLightDisplayFeedCallback mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getPositionLightDisplayFeedCallback();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getReversingLightState() {
        Log.v(TAG, "getReversingLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.v(TAG, "getReversingLightState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getReversingLightState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getRightCornerLightStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightCornerLightStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightCornerLightStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightDaytimeLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightDaytimeLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightDaytimeLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightFrontPositionSwlStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightFrontPositionSwlStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightFrontPositionSwlStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightFrontReadingLampStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightFrontReadingLampStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightFrontReadingLampStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightMiddleRowRLightRotary() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightMiddleRowRLightRotary mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightMiddleRowRLightRotary();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightMiddleRowRLightSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightMiddleRowRLightSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightMiddleRowRLightSwitch();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightRearRLightSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightRearRLightSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightRearRLightSwitch();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightRemoteLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightRemoteLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightRemoteLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getStopLightState() {
        Log.v(TAG, "getStopLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.v(TAG, "getStopLightState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getStopLightState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getStopLightState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getTurnLightFlashState() {
        Log.v(TAG, "getTurnLightFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTurnLightFlashState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getTurnLightFlashState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getTurnLightState() {
        Log.v(TAG, "getTurnLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.v(TAG, "getTurnLightState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getTurnLightState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        for (AbsBYDAutoLightListener absBYDAutoLightListener : this.mAbsBydAutoLightListeners) {
            Log.e(TAG, "onDataEventChanged id:" + i + ",value:" + bYDAutoEventValue + ",hashcod:" + System.identityHashCode(absBYDAutoLightListener));
            absBYDAutoLightListener.onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void reSetAmbientParams(int i) {
        Log.v(TAG, "reSetAmbientParams is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.c("reSetAmbientParams", Integer.valueOf(i));
            Log.v(TAG, "reSetAmbientParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() == null) {
                Log.v(TAG, "adapter is not connect");
            }
            this.mTsManagerImpl.getCarBodyManager().reSetAmbientParams(i);
        }
    }

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        registerListener(absBYDAutoLightListener, new int[0]);
    }

    public void resetDelayLightParams() {
        Log.v(TAG, "resetDelayLightParams is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.c("resetDelayLightParams", new Object[0]);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAFSSwitchSate", Integer.valueOf(i));
            Log.v(TAG, "setAFSSwitchSate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAFSSwitchSate(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        Log.v(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMulticolorMode", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorMode(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMulticolorState(int i, int i2) {
        Log.v(TAG, "setAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMulticolorState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorState(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicMode(int i, int i2) {
        Log.v(TAG, "setAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMusicMode", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicMode(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicModeState(int i, int i2) {
        Log.v(TAG, "setAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMusicModeState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicModeState(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicRhym(int i, int i2) {
        Log.v(TAG, "setAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMusicRhym", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicRhym(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setBrightness(int i, int i2) {
        Log.v(TAG, "setBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setBrightness", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setBrightness(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setCargoLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCargoLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setCargoLightSwitchState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setDayTimeLightState(int i) {
        Log.v(TAG, "setDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setDayTimeLightState", Integer.valueOf(i));
            Log.v(TAG, "setDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setDayTimeLightState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setDoorLightState(int i) {
        Log.v(TAG, "setDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setDoorLightState", Integer.valueOf(i));
            Log.v(TAG, "setDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setDoorLightState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
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
            } else {
                if (!(obj instanceof byte[])) {
                    Log.v(TAG, "the type of value is not a suitable type");
                    return -1;
                }
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
        } else if (i == 1164) {
            setDoorLightState(iIntValue);
            Log.v(TAG, "Setting.SET_INSIDE_LIGHT_DOOR_STATE_SET:");
        } else if (i == 1257) {
            setAmbientMusicModeState(0, iIntValue);
            Log.v(TAG, "SET_MUSIC_MODE_STATE_SET: " + iIntValue);
        } else if (i == 2802) {
            setDayTimeLightState(iIntValue);
            Log.v(TAG, "setDayTimeLightState" + iIntValue);
        } else if (i == 2805) {
            setAmbientMulticolorMode(1, iIntValue);
            Log.v(TAG, "setAmbientMulticolorMode" + iIntValue);
        } else if (i == 2811) {
            setAmbientMulticolorState(0, iIntValue);
            Log.v(TAG, "setAmbientMulticolorState" + iIntValue);
        } else if (i == 2831) {
            setAmbientMusicMode(0, iIntValue);
            Log.v(TAG, "Light.LIGHT_MUSIC_RHYTHM_MODE_SET:");
        } else if (i == 4665) {
            setCargoLightSwitchState(iIntValue);
        } else if (i == 4671) {
            setPositionLightDisplay(iIntValue);
        } else if (i == 4674) {
            setHighlightPosition(iIntValue);
        } else if (i == 2807) {
            setAmbientMulticolorMode(0, iIntValue);
            Log.v(TAG, "setAmbientMulticolorMode" + iIntValue);
        } else if (i != 2808) {
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
        } else {
            setAmbientMulticolorState(1, iIntValue);
            Log.v(TAG, "setAmbientMulticolorState" + iIntValue);
        }
        return 1;
    }

    public int setHighlightPosition(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setHighlightPosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setHighlightPosition(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setIalBrightness(int i, int i2, int i3) {
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setIALBrightness", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            Log.v(TAG, "setIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setIalBrightness(i, i2, i3);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setIalColor(int i, int i2, int i3) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setIALColor", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            Log.v(TAG, "setIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setIalColor(i, i2, i3);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setKtvMode(int i, int i2) {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setKtvMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setKtvMode(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLeftFrontReadingLampSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftFrontReadingLampSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setLeftFrontReadingLampSwitchState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLeftMiddleRowRLightRotaryState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftMiddleRowRLightRotaryState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setLeftMiddleRowRLightRotaryState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLeftMiddleRowSideRLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftMiddleRowSideRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setLeftMiddleRowSideRLightSwitchState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLeftRearRLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftRearRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setLeftRearRLightSwitchState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLightDelayTime(int i, int i2) {
        Log.v(TAG, "setLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setLightDelayTime", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setLightDelayTime(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMusicModeState(int i, int i2) {
        Log.v(TAG, "setMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setMusicModeState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.v(TAG, "setMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setMusicModeState(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setOnSceneMode(int i) {
        Log.i(TAG, "setOnSceneMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setOnSceneMode(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.c("setOnSceneMode" + i, new Object[0]);
        Log.v(TAG, "setOnSceneMode mForTestValue is 0 execute return");
        return 0;
    }

    public int setPositionLightDisplay(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setPositionLightDisplay mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setPositionLightDisplay(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setRightFrontReadingLampSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightFrontReadingLampSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setRightFrontReadingLampSwitchState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setRightMiddleRowRLightRotaryState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightMiddleRowRLightRotaryState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setRightMiddleRowRLightRotaryState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setRightMiddleRowRLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightMiddleRowRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setRightMiddleRowRLightSwitchState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setRightRearRLightSwitchState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightRearRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setRightRearRLightSwitchState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setTurnLightsFlashState(int i) {
        Log.v(TAG, "setTurnLightsFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setTurnLightsFlashState", Integer.valueOf(i));
            Log.v(TAG, "setTurnLightsFlashState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setTurnLightsFlashState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(d dVar) {
    }

    public void unregisterListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.e(null);
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

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe light listener " + absBYDAutoLightListener + " featureIDs = " + Arrays.toString(iArr) + ",HashCode:" + System.identityHashCode(absBYDAutoLightListener));
        if (1 == this.mForTest) {
            this.mTestCallback.e(absBYDAutoLightListener);
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
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
}
