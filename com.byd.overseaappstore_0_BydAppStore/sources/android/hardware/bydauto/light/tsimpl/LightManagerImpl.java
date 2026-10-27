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
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.hardware.bydauto_test.TestLightManagerImplCallback;
import android.provider.Settings;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

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
                this.mTestCallback = new TestLightManagerImplCallback();
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
                    return;
                }
                try {
                    LightManagerImpl.this.lightCallback(i2, i3, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(LightManagerImpl.TAG, "lightCallback is error");
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
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

    /* JADX INFO: Access modifiers changed from: private */
    public void lightCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        int i3;
        int dayLightSupport;
        int afsSwitch;
        int i4;
        StringBuilder sb;
        String str;
        int dayTimeRunningLightState;
        int turnLightState;
        String str2;
        StringBuilder sb2;
        String str3;
        StringBuilder sb3;
        String str4;
        StringBuilder sb4;
        String str5;
        int rightDaytimeLightStateR;
        StringBuilder sb5;
        String str6;
        e.t("LightCallback propertyId:", i, TAG);
        int i5 = 2823;
        switch (i) {
            case LightHalId.ID_DAY_LIGHT_SUPPORT /* 557856041 */:
                i3 = 2803;
                dayLightSupport = carBodyPacket.getDayLightSupport();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_AMBIENT_COLORS_SUPPORT /* 557856188 */:
                i3 = 4537;
                dayLightSupport = carBodyPacket.getAmbientColorsSupport();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case 557857710:
                Iterator<AbsBYDAutoLightListener> it = this.mAbsBydAutoLightListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAmbRingColorChanged(carBodyPacket.getIalColorStarRingR());
                }
                i3 = 2845;
                dayLightSupport = carBodyPacket.getIalColorStarRingR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case 557857711:
                Iterator<AbsBYDAutoLightListener> it2 = this.mAbsBydAutoLightListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onIALColorChanged(4, carBodyPacket.getIalColorStarrySkyR());
                }
                i3 = 2847;
                dayLightSupport = carBodyPacket.getIalColorStarrySkyR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case 557857712:
                Iterator<AbsBYDAutoLightListener> it3 = this.mAbsBydAutoLightListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onIALColorChanged(1, carBodyPacket.getIalColorFrontR());
                }
                i3 = 1161;
                dayLightSupport = carBodyPacket.getIalColorFrontR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case 557857713:
                Iterator<AbsBYDAutoLightListener> it4 = this.mAbsBydAutoLightListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onIALColorChanged(2, carBodyPacket.getIalColorBackR());
                }
                i3 = 1157;
                dayLightSupport = carBodyPacket.getIalColorBackR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case 557857716:
                Iterator<AbsBYDAutoLightListener> it5 = this.mAbsBydAutoLightListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onAmbRingBrightnessChanged(carBodyPacket.getIalBrightnessStarRingR());
                }
                i3 = 2844;
                dayLightSupport = carBodyPacket.getIalBrightnessStarRingR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case 557857717:
                Iterator<AbsBYDAutoLightListener> it6 = this.mAbsBydAutoLightListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onIALBrightnessChanged(4, carBodyPacket.getIalBrightnessStarrySkyR());
                }
                i3 = 2846;
                dayLightSupport = carBodyPacket.getIalBrightnessStarrySkyR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case 557857718:
                Iterator<AbsBYDAutoLightListener> it7 = this.mAbsBydAutoLightListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onIALBrightnessChanged(2, carBodyPacket.getIalBrightnessBackR());
                }
                i3 = 1156;
                dayLightSupport = carBodyPacket.getIalBrightnessBackR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case 557857719:
                Iterator<AbsBYDAutoLightListener> it8 = this.mAbsBydAutoLightListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onIALBrightnessChanged(1, carBodyPacket.getIalBrightnessFrontR());
                }
                i3 = 1160;
                dayLightSupport = carBodyPacket.getIalBrightnessFrontR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_LIGHT_AUTO_STATUS_ONE /* 557860608 */:
                int i6 = carBodyPacket.getmLightAutoStatusOne();
                for (AbsBYDAutoLightListener absBYDAutoLightListener : this.mAbsBydAutoLightListeners) {
                    if (i6 == 1) {
                        absBYDAutoLightListener.onLightAutoSwitchOn();
                    } else {
                        absBYDAutoLightListener.onLightAutoSwitchOff();
                    }
                    absBYDAutoLightListener.onDoorLightStateChanged(i6);
                }
                break;
            case LightHalId.ID_LIGHT_AUTO_STATUS_TWO /* 557860609 */:
                int i7 = carBodyPacket.getmLightAutoStatusTwo();
                for (AbsBYDAutoLightListener absBYDAutoLightListener2 : this.mAbsBydAutoLightListeners) {
                    if (i7 == 1) {
                        absBYDAutoLightListener2.onLightAutoSwitchOn();
                    } else {
                        absBYDAutoLightListener2.onLightAutoSwitchOff();
                    }
                }
                break;
            case LightHalId.ID_AFS_SWITCH /* 557860610 */:
                afsSwitch = carBodyPacket.getAfsSwitch();
                Iterator<AbsBYDAutoLightListener> it9 = this.mAbsBydAutoLightListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onAFSSwitchStateChange(afsSwitch);
                }
                i5 = 890;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case 557860611:
                i4 = carBodyPacket.getmAmbientMusicModeStateR();
                int areaId = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener3 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener3.onAmbientMusicModeState(areaId, i4);
                    Log.v(TAG, "ID_AMBIENT_MUSIC_MODE_STATE_R is onAmbientMusicModeState in " + i4 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener3));
                }
                caseFeatureIds(1255, Integer.valueOf(i4));
                sb = new StringBuilder();
                str = "SET_MUSIC_MODE_STATE musicState is ";
                sb.append(str);
                sb.append(i4);
                Log.i(TAG, sb.toString());
                break;
            case 557860613:
                i4 = carBodyPacket.getmAmbientMusicModeR();
                int areaId2 = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener4 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener4.onAmbientMusicModechanged(areaId2, i4);
                    Log.v(TAG, "ID_AMBIENT_MUSIC_MODE_R is onAmbientMusicModechanged in" + i4 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener4));
                }
                caseFeatureIds(2829, Integer.valueOf(i4));
                sb = new StringBuilder();
                str = "LIGHT_MUSIC_RHYTHM_MODE value is ";
                sb.append(str);
                sb.append(i4);
                Log.i(TAG, sb.toString());
                break;
            case 557860615:
                afsSwitch = carBodyPacket.getmAmbientMulticolorStateR();
                int areaId3 = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener5 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener5.onAmbientMulticolorStateChanged(areaId3, afsSwitch);
                    Log.v(TAG, " onAmbientMulticolorStateChanged in multiState:" + afsSwitch + ",multiId:" + areaId3 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener5));
                }
                i5 = 2808;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case 557860617:
                afsSwitch = carBodyPacket.getmAmbientMulticolorModeR();
                int areaId4 = carBodyPacket.getAreaId();
                Iterator<AbsBYDAutoLightListener> it10 = this.mAbsBydAutoLightListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onAmbientMulticolorModeChanged(areaId4, afsSwitch);
                }
                i5 = 2805;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_DAY_TIME_LIGHT_STATE_R /* 557860619 */:
                dayTimeRunningLightState = carBodyPacket.getmDayTimeLightStateR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener6 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener6.onDayTimeLightChanged(dayTimeRunningLightState);
                    absBYDAutoLightListener6.onDayTimeRunningLitghtStateChanged(dayTimeRunningLightState);
                }
                caseFeatureIds(2800, Integer.valueOf(dayTimeRunningLightState));
                break;
            case LightHalId.ID_REVERSING_LIGHT_STATE /* 557860629 */:
                afsSwitch = carBodyPacket.getReversingLightState();
                Iterator<AbsBYDAutoLightListener> it11 = this.mAbsBydAutoLightListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onReversingLightStateChanged(afsSwitch);
                }
                i5 = 2797;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_TURN_LIGHT_STATE /* 557860630 */:
                turnLightState = carBodyPacket.getTurnLightState();
                for (AbsBYDAutoLightListener absBYDAutoLightListener7 : this.mAbsBydAutoLightListeners) {
                    StringBuilder sbJ = d.j("onTurnLightStateChanged in:", turnLightState, " listener hashCode: ");
                    sbJ.append(System.identityHashCode(absBYDAutoLightListener7));
                    Log.e(TAG, sbJ.toString());
                    absBYDAutoLightListener7.onTurnLightStateChanged(turnLightState);
                }
                Log.e(TAG, "onTurnLightStateChanged out:" + turnLightState);
                caseFeatureIds(2848, Integer.valueOf(turnLightState));
                break;
            case 557860631:
                afsSwitch = carBodyPacket.getAmbientFeedBack();
                Iterator<AbsBYDAutoLightListener> it12 = this.mAbsBydAutoLightListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onAmbientFeedBackChanged(afsSwitch);
                }
                i5 = 902;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_TURN_LIGHT_STATER /* 557860634 */:
                turnLightState = carBodyPacket.getTurnLightStateR();
                Iterator<AbsBYDAutoLightListener> it13 = this.mAbsBydAutoLightListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onTurnLightStateChanged(turnLightState);
                }
                caseFeatureIds(2848, Integer.valueOf(turnLightState));
                break;
            case LightHalId.ID_LIGHT_LOW_BEAM_STATUSR /* 557860637 */:
                int lightLowBeamStatusR = carBodyPacket.getLightLowBeamStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener8 : this.mAbsBydAutoLightListeners) {
                    if (lightLowBeamStatusR == 1) {
                        absBYDAutoLightListener8.onLightOn(2);
                        sb2 = new StringBuilder();
                        str3 = "ID_LIGHT_LOW_BEAM_STATUSR is onLightOn listener hashCode: ";
                    } else {
                        absBYDAutoLightListener8.onLightOff(2);
                        sb2 = new StringBuilder();
                        str3 = "ID_LIGHT_LOW_BEAM_STATUSR is onLightOff listener hashCode: ";
                    }
                    sb2.append(str3);
                    sb2.append(System.identityHashCode(absBYDAutoLightListener8));
                    Log.e(TAG, sb2.toString());
                }
                caseFeatureIds(2827, Integer.valueOf(lightLowBeamStatusR));
                str2 = "LIGHT_LOW_BEAM_LIGHT caseFeatureIds is in";
                Log.e(TAG, str2);
                break;
            case LightHalId.ID_LIGHT_HIGH_BEAMR /* 557860638 */:
                int lightHighBeamR = carBodyPacket.getLightHighBeamR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener9 : this.mAbsBydAutoLightListeners) {
                    if (lightHighBeamR == 1) {
                        absBYDAutoLightListener9.onLightOn(3);
                        sb3 = new StringBuilder();
                        str4 = "ID_LIGHT_HIGH_BEAMR is onLightOn listener hashCode: ";
                    } else {
                        absBYDAutoLightListener9.onLightOff(3);
                        sb3 = new StringBuilder();
                        str4 = "ID_LIGHT_HIGH_BEAMR is onLightOff listener hashCode: ";
                    }
                    sb3.append(str4);
                    sb3.append(System.identityHashCode(absBYDAutoLightListener9));
                    Log.e(TAG, sb3.toString());
                }
                caseFeatureIds(2820, Integer.valueOf(lightHighBeamR));
                str2 = "LIGHT_HIGH_BEAM_LIGHT caseFeatureIds is in";
                Log.e(TAG, str2);
                break;
            case LightHalId.ID_LIGHT_REAR_FOGR /* 557860642 */:
                int lightRearFog = carBodyPacket.getLightRearFog();
                for (AbsBYDAutoLightListener absBYDAutoLightListener10 : this.mAbsBydAutoLightListeners) {
                    if (lightRearFog == 1) {
                        absBYDAutoLightListener10.onLightOn(7);
                        sb4 = new StringBuilder();
                        str5 = "ID_LIGHT_REAR_FOGR is onLightOn listener hashCode: ";
                    } else {
                        absBYDAutoLightListener10.onLightOff(7);
                        sb4 = new StringBuilder();
                        str5 = "ID_LIGHT_REAR_FOGR is onLightOff listener hashCode: ";
                    }
                    sb4.append(str5);
                    sb4.append(System.identityHashCode(absBYDAutoLightListener10));
                    Log.e(TAG, sb4.toString());
                }
                caseFeatureIds(2832, Integer.valueOf(lightRearFog));
                str2 = "LIGHT_REAR_FOG_LAMPS caseFeatureIds is in";
                Log.e(TAG, str2);
                break;
            case LightHalId.ID_DAY_TIME_RUNNING_LIGHT_STATE_R /* 557860643 */:
                int dayTimeRunningLightState2 = carBodyPacket.getDayTimeRunningLightState();
                Iterator<AbsBYDAutoLightListener> it14 = this.mAbsBydAutoLightListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onDayTimeRunningLitghtStateChanged(dayTimeRunningLightState2);
                }
                dayTimeRunningLightState = carBodyPacket.getDayTimeRunningLightState();
                caseFeatureIds(2800, Integer.valueOf(dayTimeRunningLightState));
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
                i3 = BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH;
                dayLightSupport = carBodyPacket.getLeftFrontReadingLampStatusR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_RIGHT_FRONT_READING_LAMP_STATUS_R /* 557860658 */:
                Iterator<AbsBYDAutoLightListener> it18 = this.mAbsBydAutoLightListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onRightFrontReadingLampStatusChanged(carBodyPacket.getRightFrontReadingLampStatusR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH;
                dayLightSupport = carBodyPacket.getRightFrontReadingLampStatusR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R /* 557860659 */:
                Iterator<AbsBYDAutoLightListener> it19 = this.mAbsBydAutoLightListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onLeftMiddleRowSideRLightSwitchChanged(carBodyPacket.getLeftMiddleRowSideRLightSwitchR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH;
                dayLightSupport = carBodyPacket.getLeftMiddleRowSideRLightSwitchR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R /* 557860660 */:
                Iterator<AbsBYDAutoLightListener> it20 = this.mAbsBydAutoLightListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onRightMiddleRowRLightSwitch(carBodyPacket.getRightMiddleRowRLightSwitchR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH;
                dayLightSupport = carBodyPacket.getRightMiddleRowRLightSwitchR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R /* 557860661 */:
                Iterator<AbsBYDAutoLightListener> it21 = this.mAbsBydAutoLightListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onLeftMiddleRowRLightRotaryStateChanged(carBodyPacket.getLeftMiddleRowRLightRotaryStateR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH;
                dayLightSupport = carBodyPacket.getLeftMiddleRowRLightRotaryStateR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R /* 557860662 */:
                Iterator<AbsBYDAutoLightListener> it22 = this.mAbsBydAutoLightListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onRightMiddleRowRLightRotaryChanged(carBodyPacket.getRightMiddleRowRLightRotaryR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH;
                dayLightSupport = carBodyPacket.getRightMiddleRowRLightRotaryR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_LEFT_REAR_R_LIGHT_SWITCH_R /* 557860663 */:
                Iterator<AbsBYDAutoLightListener> it23 = this.mAbsBydAutoLightListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onLeftRearRLightSwitchChanged(carBodyPacket.getLeftRearRLightSwitchR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH;
                dayLightSupport = carBodyPacket.getLeftRearRLightSwitchR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_RIGHT_REAR_R_LIGHT_SWITCH_R /* 557860664 */:
                Iterator<AbsBYDAutoLightListener> it24 = this.mAbsBydAutoLightListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onRightRearRLightSwitchChanged(carBodyPacket.getRightRearRLightSwitchR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH;
                dayLightSupport = carBodyPacket.getRightRearRLightSwitchR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_CARGO_LIGHT_SWITCH_STATE_R /* 557860665 */:
                Iterator<AbsBYDAutoLightListener> it25 = this.mAbsBydAutoLightListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onCargoLightSwitchStateChanged(carBodyPacket.getCargoLightSwitchStateR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH;
                dayLightSupport = carBodyPacket.getCargoLightSwitchStateR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_LEFT_CORNER_LIGHT_STATUS_R /* 557860668 */:
                Iterator<AbsBYDAutoLightListener> it26 = this.mAbsBydAutoLightListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onLeftCornerLightStatusChanged(carBodyPacket.getLeftCornerLightStatusR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS;
                dayLightSupport = carBodyPacket.getLeftCornerLightStatusR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_RIGHT_CORNER_LIGHT_STATUS_R /* 557860669 */:
                Iterator<AbsBYDAutoLightListener> it27 = this.mAbsBydAutoLightListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onRightCornerLightStatusChanged(carBodyPacket.getRightCornerLightStatusR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS;
                dayLightSupport = carBodyPacket.getRightCornerLightStatusR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_FRONT_FOG_LAMP_STATUS_R /* 557860670 */:
                Iterator<AbsBYDAutoLightListener> it28 = this.mAbsBydAutoLightListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onFrontFogLampStatusChanged(carBodyPacket.getFrontFogLampStatusR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS;
                dayLightSupport = carBodyPacket.getFrontFogLampStatusR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_FRONT_CENTER_LIGHT_STATUS_R /* 557860671 */:
                Iterator<AbsBYDAutoLightListener> it29 = this.mAbsBydAutoLightListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onFrontCenterLightStatusChanged(carBodyPacket.getFrontCenterLightStatusR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS;
                dayLightSupport = carBodyPacket.getFrontCenterLightStatusR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R /* 557860672 */:
                Iterator<AbsBYDAutoLightListener> it30 = this.mAbsBydAutoLightListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onPositionLightDisplayFeedCallbackChanged(carBodyPacket.getPositionLightDisplayFeedCallbackR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK;
                dayLightSupport = carBodyPacket.getPositionLightDisplayFeedCallbackR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_RIGHT_DAYTIME_LIGHT_STATE_R /* 557860674 */:
                Iterator<AbsBYDAutoLightListener> it31 = this.mAbsBydAutoLightListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onRightDaytimeLightStateChanged(carBodyPacket.getRightDaytimeLightStateR());
                }
                rightDaytimeLightStateR = carBodyPacket.getRightDaytimeLightStateR();
                caseFeatureIds(2836, Integer.valueOf(rightDaytimeLightStateR));
                break;
            case LightHalId.ID_LEFT_DAYTIME_LIGHT_STATE_R /* 557860675 */:
                Iterator<AbsBYDAutoLightListener> it32 = this.mAbsBydAutoLightListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onLeftDaytimeLightStateChanged(carBodyPacket.getLeftDaytimeLightStateR());
                }
                afsSwitch = carBodyPacket.getLeftDaytimeLightStateR();
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_HIGHTLIGHT_POSITION_R /* 557860677 */:
                Iterator<AbsBYDAutoLightListener> it33 = this.mAbsBydAutoLightListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onHightlightPositionChanged(carBodyPacket.getHightlightPositionR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT;
                dayLightSupport = carBodyPacket.getHightlightPositionR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R /* 557860680 */:
                Iterator<AbsBYDAutoLightListener> it34 = this.mAbsBydAutoLightListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onLeftFrontPositionSWLStatusChanged(carBodyPacket.getLeftFrontPositionSwlStatusR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_LEFT_FRONT_POSITION_SPATIOTEMPORAL_WING_LIGHT_STATUS;
                dayLightSupport = carBodyPacket.getLeftFrontPositionSwlStatusR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R /* 557860681 */:
                Iterator<AbsBYDAutoLightListener> it35 = this.mAbsBydAutoLightListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onRightFrontPositionSWLStatusChanged(carBodyPacket.getRightFrontPositionSwlStatusR());
                }
                i3 = BYDAutoFeatureIds.LIGHT_RIGHT_FRONT_POSITION_SPATIOTEMPORAL_WING_LIGHT_STATUS;
                dayLightSupport = carBodyPacket.getRightFrontPositionSwlStatusR();
                caseFeatureIds(i3, Integer.valueOf(dayLightSupport));
                break;
            case LightHalId.ID_LEFT_DAYTIME_RUN_LAMP_STATE_R /* 557860682 */:
                Iterator<AbsBYDAutoLightListener> it36 = this.mAbsBydAutoLightListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onLeftDaytimeRunLampStateChanged(carBodyPacket.getLeftDaytimeRunLampStateR());
                }
                afsSwitch = carBodyPacket.getLeftDaytimeRunLampStateR();
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_RIGHT_DAYTIME_RUN_LAMP_STATE_R /* 557860683 */:
                Iterator<AbsBYDAutoLightListener> it37 = this.mAbsBydAutoLightListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onRightDaytimeRunLampStateChanged(carBodyPacket.getRightDaytimeRunLampStateR());
                }
                rightDaytimeLightStateR = carBodyPacket.getRightDaytimeRunLampStateR();
                caseFeatureIds(2836, Integer.valueOf(rightDaytimeLightStateR));
                break;
            case LightHalId.ID_LEFT_REMOTE_LIGHT_STATE_R /* 557860688 */:
                Iterator<AbsBYDAutoLightListener> it38 = this.mAbsBydAutoLightListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onLeftRemoteLightStateChanged(carBodyPacket.getLeftRemoteLightStateR());
                }
                break;
            case LightHalId.ID_RIGHT_REMOTE_LIGHT_STATE_R /* 557860689 */:
                Iterator<AbsBYDAutoLightListener> it39 = this.mAbsBydAutoLightListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onRightRemoteLightStateChanged(carBodyPacket.getRightRemoteLightStateR());
                }
                break;
            case LightHalId.ID_STOP_LIGHT_STATE /* 557870867 */:
                afsSwitch = carBodyPacket.getStopLightStateR();
                Iterator<AbsBYDAutoLightListener> it40 = this.mAbsBydAutoLightListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onStopLightStateChanged(afsSwitch);
                }
                i5 = 2799;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_LIGHT_SIDE_STATUSR /* 557879809 */:
                int lightSideStatusR = carBodyPacket.getLightSideStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener11 : this.mAbsBydAutoLightListeners) {
                    StringBuilder sbK = e.k("mAbsBydAutoLightListeners toString : ");
                    sbK.append(System.identityHashCode(this.mAbsBydAutoLightListeners));
                    Log.e(TAG, sbK.toString());
                    Log.e(TAG, "mAbsBydAutoLightListeners is size : " + this.mAbsBydAutoLightListeners.size());
                    if (lightSideStatusR == 1) {
                        absBYDAutoLightListener11.onLightOn(1);
                        sb5 = new StringBuilder();
                        str6 = "ID_LIGHT_SIDE_STATUSR is onLightOn listener hashCode: ";
                    } else {
                        absBYDAutoLightListener11.onLightOff(1);
                        sb5 = new StringBuilder();
                        str6 = "ID_LIGHT_SIDE_STATUSR is onLightOff listener hashCode: ";
                    }
                    sb5.append(str6);
                    sb5.append(System.identityHashCode(absBYDAutoLightListener11));
                    Log.e(TAG, sb5.toString());
                }
                caseFeatureIds(2842, Integer.valueOf(lightSideStatusR));
                str2 = "LIGHT_SIDE_LIGHT caseFeatureIds is in lightSide = " + lightSideStatusR;
                Log.e(TAG, str2);
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0293 A[PHI: r7
  0x0293: PHI (r7v7 int) = (r7v6 int), (r7v8 int) binds: [B:18:0x0031, B:20:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r10, java.lang.Class<?> r11) {
        /*
            Method dump skipped, instruction units count: 872
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.light.tsimpl.LightManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    public int getAFSSwitch() {
        String str;
        Log.v(TAG, "getAFSSwitch is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAFSSwitch", new Object[0]);
            str = "getAFSSwitch mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAFSSwitch();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbRingBrightness() {
        String str;
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "getAmbRingBrightness mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarRing();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbRingColor() {
        String str;
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "getAmbRingColor mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getIalColorStarRing();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int[] getAmbientColors(int i) {
        Log.v(TAG, "getAmbientColors is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientColors", Integer.valueOf(i));
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
        String str;
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "getAmbientColorsSupport mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAmbientColorsSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientFeedBack() {
        Log.v(TAG, "getAmbientFeedBack is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.v(TAG, "getAmbientFeedBack is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getAmbientFeedBack mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMulticolorMode(int i) {
        String str;
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorMode", Integer.valueOf(i));
            str = "getAmbientMulticolorMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientMulticolorState(int i) {
        String str;
        Log.v(TAG, "getAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorState", Integer.valueOf(i));
            str = "getAmbientMulticolorState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientMusicMode(int i) {
        String str;
        Log.v(TAG, "getAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicMode", Integer.valueOf(i));
            str = "getAmbientMusicMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicMode(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientMusicModeState(int i) {
        String str;
        Log.v(TAG, "getAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicModeState", Integer.valueOf(i));
            str = "getAmbientMusicModeState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientMusicRhym(int i) {
        String str;
        Log.v(TAG, "getAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicRhym", Integer.valueOf(i));
            str = "getAmbientMusicRhym mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicRhym(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getBacklightLevels() {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getBacklightLevel();
        }
        Log.v(TAG, " getBacklightLevel mForTestValue is 0 execute return");
        return -1;
    }

    public int getCargoLightSwitchConfig() {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getCargoLightSwitchConfig();
        }
        Log.v(TAG, " getCargoLightSwitchConfig mForTestValue is 0 execute return");
        return -1;
    }

    public int getCargoLightSwitchState() {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getCargoLightSwitchState();
        }
        Log.v(TAG, " getCargoLightSwitchState mForTestValue is 0 execute return");
        return -1;
    }

    public int getDayLightSupport() {
        String str;
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "getDayLightSupport mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getDayLightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDayTimeLightState() {
        Log.v(TAG, "getDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getDayTimeLightState();
        }
        this.mTestCallback.enqueueMessage("getDayTimeLightState", new Object[0]);
        Log.v(TAG, "getDayTimeLightState mForTestValue is 0 execute return");
        return 0;
    }

    public int getDayTimeRunningLightState() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getDayTimeRunningLightState();
        }
        e.s(e.k(" getDayTimeRunningLightState mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    @Deprecated
    public int getDoorLightState() {
        Log.v(TAG, "getDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLightState();
        }
        this.mTestCallback.enqueueMessage("getDoorLightState", new Object[0]);
        Log.v(TAG, "getDoorLightState mForTestValue is 0 execute return");
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
        String str;
        Log.i(TAG, "getIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "getIALBrightness mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getIalColor(int i) {
        String str;
        Log.i(TAG, "getIALColor is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "getIALColor mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        Log.v(TAG, "getLastSetedArea is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "getLastSetedArea mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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

    public int getLeftDaytimeRunLampState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftDaytimeRunLampState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftDaytimeRunLampState();
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
        String str;
        Log.v(TAG, "getLightAutoStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightAutoStatus", new Object[0]);
            str = "getLightAutoStatus mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getLightAutoStatus();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLightDelayTime(int i) {
        String str;
        Log.v(TAG, "getLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightDelayTime", Integer.valueOf(i));
            str = "getLightDelayTime mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getLightDelayTime(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLightStatus(int i) {
        String str;
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        int lightSideStatus = 0;
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightStatus", Integer.valueOf(i));
            str = "getLightStatus mForTestValue is 0 execute return";
        } else {
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
            str = "getLightStatus Type is:" + i + " And Result is:" + lightSideStatus;
        }
        Log.v(TAG, str);
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
        if (1 == this.mForTest) {
            e.s(e.k("getReversingLightState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
        }
        Log.e(TAG, "adapter is not connect");
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

    public int getRightDaytimeRunLampState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightDaytimeRunLampState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightDaytimeRunLampState();
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
        if (1 == this.mForTest) {
            e.s(e.k("getStopLightState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getStopLightState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getTurnLightFlashState() {
        String str;
        Log.v(TAG, "getTurnLightFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "getTurnLightFlashState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTurnLightFlashState();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTurnLightState() {
        Log.v(TAG, "getTurnLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.v(TAG, "getTurnLightState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getTurnLightState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
        }
        Log.e(TAG, "adapter is not connect");
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
            this.mTestCallback.enqueueMessage("reSetAmbientParams", Integer.valueOf(i));
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

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe light listener " + absBYDAutoLightListener + " featureIDs = " + Arrays.toString(iArr) + ",HashCode:" + System.identityHashCode(absBYDAutoLightListener));
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoLightListener(absBYDAutoLightListener);
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (!this.mAbsBydAutoLightListeners.contains(absBYDAutoLightListener)) {
            this.mAbsBydAutoLightListeners.add(absBYDAutoLightListener);
            Log.e(TAG, "mAbsBydAutoLightListeners add is:" + absBYDAutoLightListener + ",hashcod:" + System.identityHashCode(absBYDAutoLightListener));
        }
        StringBuilder sbK = e.k("javalib registerListener toString:");
        sbK.append(System.identityHashCode(this.mAbsBydAutoLightListeners));
        Log.e(TAG, sbK.toString());
        Log.e(TAG, "javalib registerListener size:" + this.mAbsBydAutoLightListeners.size());
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoLightListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void resetDelayLightParams() {
        Log.v(TAG, "resetDelayLightParams is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
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
        String str;
        Log.v(TAG, "setAFSSwitchSate is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAFSSWITCHSATE, Integer.valueOf(i));
            str = "setAFSSwitchSate mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setAFSSwitchSate(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        String str;
        Log.v(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE, Integer.valueOf(i), Integer.valueOf(i2));
            str = "setAmbientMulticolorMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorMode(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setAmbientMulticolorState(int i, int i2) {
        String str;
        Log.v(TAG, "setAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE, Integer.valueOf(i), Integer.valueOf(i2));
            str = "setAmbientMulticolorState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorState(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setAmbientMusicMode(int i, int i2) {
        String str;
        Log.v(TAG, "setAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODE, Integer.valueOf(i), Integer.valueOf(i2));
            str = "setAmbientMusicMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicMode(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setAmbientMusicModeState(int i, int i2) {
        String str;
        Log.v(TAG, "setAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODESTATE, Integer.valueOf(i), Integer.valueOf(i2));
            str = "setAmbientMusicModeState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicModeState(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setAmbientMusicRhym(int i, int i2) {
        String str;
        Log.v(TAG, "setAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setAmbientMusicRhym", Integer.valueOf(i), Integer.valueOf(i2));
            str = "setAmbientMusicRhym mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicRhym(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setBrightness(int i, int i2) {
        String str;
        Log.v(TAG, "setBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setBrightness", Integer.valueOf(i), Integer.valueOf(i2));
            str = "setBrightness mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setBrightness(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        Log.v(TAG, "setDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETDAYTIMELIGHTSTATE, Integer.valueOf(i));
            str = "setDayTimeLightState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setDayTimeLightState(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setDoorLightState(int i) {
        String str;
        Log.v(TAG, "setDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETDOORLIGHTSTATE, Integer.valueOf(i));
            str = "setDoorLightState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setDoorLightState(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        String str;
        int iIntValue;
        StringBuilder sb;
        String str2;
        String string;
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
        if (i == 1156) {
            setIalBrightness(2, iIntValue, 2);
            sb = new StringBuilder();
            str2 = "setIALBrightnessBack:";
        } else if (i == 1157) {
            setIalColor(2, iIntValue, 1);
            sb = new StringBuilder();
            str2 = "setIALColorFrontBack:";
        } else if (i == 1160) {
            setIalBrightness(1, iIntValue, 1);
            sb = new StringBuilder();
            str2 = "setIALBrightnessFront:";
        } else {
            if (i != 1161) {
                if (i == 1164) {
                    setDoorLightState(iIntValue);
                    string = "Setting.SET_INSIDE_LIGHT_DOOR_STATE_SET:";
                } else if (i == 1257) {
                    setAmbientMusicModeState(0, iIntValue);
                    sb = new StringBuilder();
                    str2 = "SET_MUSIC_MODE_STATE_SET: ";
                } else if (i != 2802) {
                    if (i != 2805) {
                        if (i == 2811) {
                            setAmbientMulticolorState(0, iIntValue);
                            sb = new StringBuilder();
                        } else {
                            if (i != 2831) {
                                if (i == 4665) {
                                    setCargoLightSwitchState(iIntValue);
                                } else if (i == 4671) {
                                    setPositionLightDisplay(iIntValue);
                                } else if (i == 4674) {
                                    setHighlightPosition(iIntValue);
                                } else if (i == 2807) {
                                    setAmbientMulticolorMode(0, iIntValue);
                                    sb = new StringBuilder();
                                } else if (i != 2808) {
                                    switch (i) {
                                        case 2844:
                                            setIalBrightness(5, iIntValue, 1);
                                            sb = new StringBuilder();
                                            str2 = "LIGHT_STAR_RING_ATMOSPHERE_LAMP_BRIGHTNESS:";
                                            break;
                                        case 2845:
                                            setIalColor(5, iIntValue, 1);
                                            sb = new StringBuilder();
                                            str2 = "LIGHT_STAR_RING_ATMOSPHERE_LAMP:";
                                            break;
                                        case 2846:
                                            setIalBrightness(iIntValue, 4, 1);
                                            sb = new StringBuilder();
                                            str2 = "Light.LIGHT_STAR_SKY_ATMOSPHERE_LAMP_BRIGHTNESS:";
                                            break;
                                        case 2847:
                                            setIalColor(4, iIntValue, 1);
                                            sb = new StringBuilder();
                                            str2 = "LIGHT_STAR_SKY_ATMOSPHERE_LAMP:";
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
                                                    str = "case fail ! please check out";
                                                    Log.v(TAG, str);
                                                    return -1;
                                            }
                                            break;
                                    }
                                } else {
                                    setAmbientMulticolorState(1, iIntValue);
                                    sb = new StringBuilder();
                                }
                                return 1;
                            }
                            setAmbientMusicMode(0, iIntValue);
                            string = "Light.LIGHT_MUSIC_RHYTHM_MODE_SET:";
                        }
                        sb.append(TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE);
                        sb.append(iIntValue);
                        string = sb.toString();
                    } else {
                        setAmbientMulticolorMode(1, iIntValue);
                        sb = new StringBuilder();
                    }
                    sb.append(TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE);
                    sb.append(iIntValue);
                    string = sb.toString();
                } else {
                    setDayTimeLightState(iIntValue);
                    sb = new StringBuilder();
                    str2 = TestLightManagerImplCallback.SETDAYTIMELIGHTSTATE;
                }
                Log.v(TAG, string);
                return 1;
            }
            setIalColor(1, iIntValue, 1);
            sb = new StringBuilder();
            str2 = "setIALColorFront:";
        }
        sb.append(str2);
        sb.append(iIntValue);
        string = sb.toString();
        Log.v(TAG, string);
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
        String str;
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setIALBrightness", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            str = "setIALBrightness mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setIalBrightness(i, i2, i3);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setIalColor(int i, int i2, int i3) {
        String str;
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setIALColor", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            str = "setIALColor mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setIalColor(i, i2, i3);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setKtvMode(int i, int i2) {
        String str;
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            str = "setKtvMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setKtvMode(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        Log.v(TAG, "setLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setLightDelayTime", Integer.valueOf(i), Integer.valueOf(i2));
            str = "setLightDelayTime mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setLightDelayTime(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setMusicModeState(int i, int i2) {
        String str;
        Log.v(TAG, "setMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setMusicModeState", Integer.valueOf(i), Integer.valueOf(i2));
            str = "setMusicModeState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setMusicModeState(i, i2);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int setOnSceneMode(int i) {
        String str;
        Log.i(TAG, "setOnSceneMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(e.c("setOnSceneMode", i), new Object[0]);
            str = "setOnSceneMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setOnSceneMode(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        Log.v(TAG, "setTurnLightsFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETTURNLIGHTSFLASHSTATE, Integer.valueOf(i));
            str = "setTurnLightsFlashState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTurnLightsFlashState(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public void unregisterListener(IBYDAutoListener iBYDAutoListener) {
    }

    public void unregisterListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        StringBuilder sb;
        String str;
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoLightListener(null);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("unregisterListener mForTestValue is");
            e.u(sb2, this.mForTest, " execute return", TAG);
            return;
        }
        if (absBYDAutoLightListener == null) {
            return;
        }
        if (this.mAbsBydAutoLightListeners.remove(absBYDAutoLightListener)) {
            sb = new StringBuilder();
            str = "Listener removed: ";
        } else {
            sb = new StringBuilder();
            str = "Listener not found: ";
        }
        sb.append(str);
        sb.append(absBYDAutoLightListener);
        sb.append(", hashcode: ");
        sb.append(System.identityHashCode(absBYDAutoLightListener));
        Log.e(TAG, sb.toString());
        unregisterConnectListener(absBYDAutoLightListener);
    }
}
