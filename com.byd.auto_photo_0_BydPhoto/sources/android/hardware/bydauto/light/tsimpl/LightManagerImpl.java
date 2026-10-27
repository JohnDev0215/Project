package android.hardware.bydauto.light.tsimpl;

import a.b.d;
import a.b.e.k;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
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
        int i3;
        int dayLightSupport;
        int afsSwitch;
        int i4;
        StringBuilder sb;
        String str;
        String str2;
        StringBuilder sb2;
        String str3;
        StringBuilder sb3;
        String str4;
        StringBuilder sb4;
        String str5;
        StringBuilder sb5;
        String str6;
        Log.i(TAG, "LightCallback propertyId:" + i);
        int i5 = 2848;
        switch (i) {
            case LightHalId.ID_DAY_LIGHT_SUPPORT /* 557856041 */:
                i3 = 2800;
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
                int i8 = carBodyPacket.getmDayTimeLightStateR();
                Iterator<AbsBYDAutoLightListener> it11 = this.mAbsBydAutoLightListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onDayTimeLightChanged(i8);
                }
                break;
            case LightHalId.ID_REVERSING_LIGHT_STATE /* 557860629 */:
                afsSwitch = carBodyPacket.getReversingLightState();
                Iterator<AbsBYDAutoLightListener> it12 = this.mAbsBydAutoLightListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onReversingLightStateChanged(afsSwitch);
                }
                i5 = 2797;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_TURN_LIGHT_STATE /* 557860630 */:
                afsSwitch = carBodyPacket.getTurnLightState();
                for (AbsBYDAutoLightListener absBYDAutoLightListener6 : this.mAbsBydAutoLightListeners) {
                    Log.e(TAG, "onTurnLightStateChanged in:" + afsSwitch + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener6));
                    absBYDAutoLightListener6.onTurnLightStateChanged(afsSwitch);
                }
                Log.e(TAG, "onTurnLightStateChanged out:" + afsSwitch);
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case 557860631:
                afsSwitch = carBodyPacket.getAmbientFeedBack();
                Iterator<AbsBYDAutoLightListener> it13 = this.mAbsBydAutoLightListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onAmbientFeedBackChanged(afsSwitch);
                }
                i5 = 902;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_TURN_LIGHT_STATER /* 557860634 */:
                afsSwitch = carBodyPacket.getTurnLightStateR();
                Iterator<AbsBYDAutoLightListener> it14 = this.mAbsBydAutoLightListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onTurnLightStateChanged(afsSwitch);
                }
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_LIGHT_LOW_BEAM_STATUSR /* 557860637 */:
                int lightLowBeamStatusR = carBodyPacket.getLightLowBeamStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener7 : this.mAbsBydAutoLightListeners) {
                    if (lightLowBeamStatusR == 1) {
                        absBYDAutoLightListener7.onLightOn(2);
                        sb2 = new StringBuilder();
                        str3 = "ID_LIGHT_LOW_BEAM_STATUSR is onLightOn listener hashCode: ";
                    } else {
                        absBYDAutoLightListener7.onLightOff(2);
                        sb2 = new StringBuilder();
                        str3 = "ID_LIGHT_LOW_BEAM_STATUSR is onLightOff listener hashCode: ";
                    }
                    sb2.append(str3);
                    sb2.append(System.identityHashCode(absBYDAutoLightListener7));
                    Log.e(TAG, sb2.toString());
                }
                caseFeatureIds(2827, Integer.valueOf(lightLowBeamStatusR));
                str2 = "LIGHT_LOW_BEAM_LIGHT caseFeatureIds is in";
                Log.e(TAG, str2);
                break;
            case LightHalId.ID_LIGHT_HIGH_BEAMR /* 557860638 */:
                int lightHighBeamR = carBodyPacket.getLightHighBeamR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener8 : this.mAbsBydAutoLightListeners) {
                    if (lightHighBeamR == 1) {
                        absBYDAutoLightListener8.onLightOn(3);
                        sb3 = new StringBuilder();
                        str4 = "ID_LIGHT_HIGH_BEAMR is onLightOn listener hashCode: ";
                    } else {
                        absBYDAutoLightListener8.onLightOff(3);
                        sb3 = new StringBuilder();
                        str4 = "ID_LIGHT_HIGH_BEAMR is onLightOff listener hashCode: ";
                    }
                    sb3.append(str4);
                    sb3.append(System.identityHashCode(absBYDAutoLightListener8));
                    Log.e(TAG, sb3.toString());
                }
                caseFeatureIds(2820, Integer.valueOf(lightHighBeamR));
                str2 = "LIGHT_HIGH_BEAM_LIGHT caseFeatureIds is in";
                Log.e(TAG, str2);
                break;
            case LightHalId.ID_LIGHT_REAR_FOGR /* 557860642 */:
                int lightRearFog = carBodyPacket.getLightRearFog();
                for (AbsBYDAutoLightListener absBYDAutoLightListener9 : this.mAbsBydAutoLightListeners) {
                    if (lightRearFog == 1) {
                        absBYDAutoLightListener9.onLightOn(7);
                        sb4 = new StringBuilder();
                        str5 = "ID_LIGHT_REAR_FOGR is onLightOn listener hashCode: ";
                    } else {
                        absBYDAutoLightListener9.onLightOff(7);
                        sb4 = new StringBuilder();
                        str5 = "ID_LIGHT_REAR_FOGR is onLightOff listener hashCode: ";
                    }
                    sb4.append(str5);
                    sb4.append(System.identityHashCode(absBYDAutoLightListener9));
                    Log.e(TAG, sb4.toString());
                }
                caseFeatureIds(2832, Integer.valueOf(lightRearFog));
                str2 = "LIGHT_REAR_FOG_LAMPS caseFeatureIds is in";
                Log.e(TAG, str2);
                break;
            case LightHalId.ID_DAY_TIME_RUNNING_LIGHT_STATE_R /* 557860643 */:
                int dayTimeRunningLightState = carBodyPacket.getDayTimeRunningLightState();
                Iterator<AbsBYDAutoLightListener> it15 = this.mAbsBydAutoLightListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onDayTimeRunningLitghtStateChanged(dayTimeRunningLightState);
                }
                break;
            case 557860644:
                int lightRearPosition = carBodyPacket.getLightRearPosition();
                Iterator<AbsBYDAutoLightListener> it16 = this.mAbsBydAutoLightListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onRearLampLightStateChanged(lightRearPosition);
                }
                break;
            case LightHalId.ID_STOP_LIGHT_STATE /* 557870867 */:
                afsSwitch = carBodyPacket.getStopLightStateR();
                Iterator<AbsBYDAutoLightListener> it17 = this.mAbsBydAutoLightListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onStopLightStateChanged(afsSwitch);
                }
                i5 = 2799;
                caseFeatureIds(i5, Integer.valueOf(afsSwitch));
                break;
            case LightHalId.ID_LIGHT_SIDE_STATUSR /* 557879809 */:
                int lightSideStatusR = carBodyPacket.getLightSideStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener10 : this.mAbsBydAutoLightListeners) {
                    if (lightSideStatusR == 1) {
                        absBYDAutoLightListener10.onLightOn(1);
                        sb5 = new StringBuilder();
                        str6 = "ID_LIGHT_SIDE_STATUSR is onLightOn listener hashCode: ";
                    } else {
                        absBYDAutoLightListener10.onLightOff(1);
                        sb5 = new StringBuilder();
                        str6 = "ID_LIGHT_SIDE_STATUSR is onLightOff listener hashCode: ";
                    }
                    sb5.append(str6);
                    sb5.append(System.identityHashCode(absBYDAutoLightListener10));
                    Log.e(TAG, sb5.toString());
                }
                caseFeatureIds(2842, Integer.valueOf(lightSideStatusR));
                str2 = "LIGHT_SIDE_LIGHT caseFeatureIds is in";
                Log.e(TAG, str2);
                break;
        }
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        int ialBrightness;
        int ambientMulticolorMode;
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    int i2 = 1156;
                    if (i != 1156) {
                        i2 = 1157;
                        if (i != 1157) {
                            int i3 = 2807;
                            if (i != 2807) {
                                i3 = 2808;
                                if (i != 2808) {
                                    i3 = 2832;
                                    if (i != 2832) {
                                        i3 = 2833;
                                        if (i != 2833) {
                                            switch (i) {
                                                case 890:
                                                    ambientMulticolorMode = getAFSSwitch();
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 890;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 902:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 902;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 1171:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 1171;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 1255:
                                                    ambientMulticolorMode = getAmbientMusicModeState(1);
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 1255;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 1257:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(1);
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 1257;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2006:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2006;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2797:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2797;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2805:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2805;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2811:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(1);
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2811;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2814:
                                                    ambientMulticolorMode = getLightStatus(6);
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2814;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2820:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2820;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2827:
                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2827;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2829:
                                                    ambientMulticolorMode = getAmbientMusicMode(1);
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2829;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 2842:
                                                    ambientMulticolorMode = getLightStatus(1);
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 2842;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                case 4537:
                                                    ambientMulticolorMode = getAmbientColorsSupport();
                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                    i3 = 4537;
                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 1160:
                                                            ambientMulticolorMode = getIalBrightness(1);
                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                            i3 = 1160;
                                                            sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                            break;
                                                        case 1161:
                                                            ambientMulticolorMode = getIalColor(1);
                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                            i3 = 1161;
                                                            sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                            break;
                                                        case 1162:
                                                            ambientMulticolorMode = getDoorLightState();
                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                            i3 = 1162;
                                                            sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                            break;
                                                        case 1163:
                                                            ambientMulticolorMode = getLightAutoStatus();
                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                            i3 = 1163;
                                                            sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 2799:
                                                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getStopLightState();
                                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                                    i3 = 2799;
                                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                                    break;
                                                                case 2800:
                                                                    ambientMulticolorMode = getDayLightSupport();
                                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                                    i3 = 2800;
                                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                                    break;
                                                                case 2801:
                                                                    ambientMulticolorMode = getDayTimeLightState();
                                                                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                                    i3 = 2801;
                                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case 2844:
                                                                            ambientMulticolorMode = getIalBrightness(5);
                                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                                            i3 = 2844;
                                                                            break;
                                                                        case 2845:
                                                                            ambientMulticolorMode = getAmbRingColor();
                                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                                            i3 = 2845;
                                                                            break;
                                                                        case 2846:
                                                                            ambientMulticolorMode = getIalBrightness(4);
                                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                                            i3 = 2846;
                                                                            break;
                                                                        case 2847:
                                                                            ambientMulticolorMode = getIalColor(4);
                                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                                            i3 = 2847;
                                                                            break;
                                                                        case 2848:
                                                                            ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
                                                                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                                                                            i3 = 2848;
                                                                            break;
                                                                        default:
                                                                            Log.v(TAG, "case fail ! please check out");
                                                                            continue;
                                                                    }
                                                                    sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                                } else {
                                    ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(0);
                                }
                            } else {
                                ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                            }
                            bYDAutoEventValue.intValue = ambientMulticolorMode;
                            sparseArray.put(i3, Integer.valueOf(ambientMulticolorMode));
                        } else {
                            ialBrightness = getIalColor(2);
                        }
                    } else {
                        ialBrightness = getIalBrightness(2);
                    }
                    bYDAutoEventValue.intValue = ialBrightness;
                    sparseArray.put(i2, Integer.valueOf(ialBrightness));
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getAFSSwitch() {
        String str;
        Log.v(TAG, "getAFSSwitch is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAFSSwitch", new Object[0]);
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
        String str;
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMulticolorMode", Integer.valueOf(i));
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
            this.mTestCallback.c("getAmbientMulticolorState", Integer.valueOf(i));
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
            this.mTestCallback.c("getAmbientMusicMode", Integer.valueOf(i));
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
            this.mTestCallback.c("getAmbientMusicModeState", Integer.valueOf(i));
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
            this.mTestCallback.c("getAmbientMusicRhym", Integer.valueOf(i));
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
        String str;
        Log.v(TAG, "getDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getDayTimeLightState", new Object[0]);
            str = "getDayTimeLightState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getDayTimeLightState();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDayTimeRunningLightState() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
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
        String str;
        Log.v(TAG, "getDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getDoorLightState", new Object[0]);
            str = "getDoorLightState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getDoorLightState();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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

    public int getLightAutoStatus() {
        String str;
        Log.v(TAG, "getLightAutoStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getLightAutoStatus", new Object[0]);
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
            this.mTestCallback.c("getLightDelayTime", Integer.valueOf(i));
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
            this.mTestCallback.c("getLightStatus", Integer.valueOf(i));
            str = "getLightStatus mForTestValue is 0 execute return";
        } else if (this.mTsManagerImpl.getCarBodyManager() == null) {
            str = "adapter is not connect";
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

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe light listener " + absBYDAutoLightListener + " featureIDs = " + Arrays.toString(iArr) + ",HashCode:" + System.identityHashCode(absBYDAutoLightListener));
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
            return;
        }
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
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoLightListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
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
        String str;
        Log.v(TAG, "setAFSSwitchSate is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAFSSwitchSate", Integer.valueOf(i));
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
            this.mTestCallback.c("setAmbientMulticolorMode", Integer.valueOf(i), Integer.valueOf(i2));
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
            this.mTestCallback.c("setAmbientMulticolorState", Integer.valueOf(i), Integer.valueOf(i2));
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
            this.mTestCallback.c("setAmbientMusicMode", Integer.valueOf(i), Integer.valueOf(i2));
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
            this.mTestCallback.c("setAmbientMusicModeState", Integer.valueOf(i), Integer.valueOf(i2));
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
            this.mTestCallback.c("setAmbientMusicRhym", Integer.valueOf(i), Integer.valueOf(i2));
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
            this.mTestCallback.c("setBrightness", Integer.valueOf(i), Integer.valueOf(i2));
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

    public int setDayTimeLightState(int i) {
        String str;
        Log.v(TAG, "setDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setDayTimeLightState", Integer.valueOf(i));
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
            this.mTestCallback.c("setDoorLightState", Integer.valueOf(i));
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
    protected int setForOneFeatureId(int i, Object obj) {
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
                        } else if (i == 2831) {
                            setAmbientMusicMode(0, iIntValue);
                            string = "Light.LIGHT_MUSIC_RHYTHM_MODE_SET:";
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
                                    str = "case fail ! please check out";
                                    Log.v(TAG, str);
                                    return -1;
                            }
                        } else {
                            setAmbientMulticolorState(1, iIntValue);
                            sb = new StringBuilder();
                        }
                        sb.append("setAmbientMulticolorState");
                        sb.append(iIntValue);
                        string = sb.toString();
                    } else {
                        setAmbientMulticolorMode(1, iIntValue);
                        sb = new StringBuilder();
                    }
                    sb.append("setAmbientMulticolorMode");
                    sb.append(iIntValue);
                    string = sb.toString();
                } else {
                    setDayTimeLightState(iIntValue);
                    sb = new StringBuilder();
                    str2 = "setDayTimeLightState";
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

    public int setIalBrightness(int i, int i2, int i3) {
        String str;
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setIALBrightness", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
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
            this.mTestCallback.c("setIALColor", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
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

    public int setLightDelayTime(int i, int i2) {
        String str;
        Log.v(TAG, "setLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setLightDelayTime", Integer.valueOf(i), Integer.valueOf(i2));
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
            this.mTestCallback.c("setMusicModeState", Integer.valueOf(i), Integer.valueOf(i2));
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
            this.mTestCallback.c("setOnSceneMode" + i, new Object[0]);
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

    public int setTurnLightsFlashState(int i) {
        String str;
        Log.v(TAG, "setTurnLightsFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setTurnLightsFlashState", Integer.valueOf(i));
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

    public void unregisterListener(d dVar) {
    }

    public void unregisterListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        StringBuilder sb;
        String str;
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
