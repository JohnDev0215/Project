package android.hardware.bydauto.light.tsimpl;

import a.a.d;
import a.a.e.k;
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
                Log.d(LightManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (LightManagerImpl.this.mAbsBydAutoLightListeners.isEmpty()) {
                    Log.d(LightManagerImpl.TAG, "mAbsBydAutoLightListeners is null. ");
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
                    Log.d(TAG, "ID_AMBIENT_MUSIC_MODE_STATE_R is onAmbientMusicModeState in " + i5 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener3));
                }
                caseFeatureIds(1255, Integer.valueOf(i5));
                Log.i(TAG, "SET_MUSIC_MODE_STATE musicState is " + i5);
                break;
            case 557860613:
                int i6 = carBodyPacket.getmAmbientMusicModeR();
                int areaId2 = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener4 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener4.onAmbientMusicModechanged(areaId2, i6);
                    Log.d(TAG, "ID_AMBIENT_MUSIC_MODE_R is onAmbientMusicModechanged in" + i6 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener4));
                }
                caseFeatureIds(2829, Integer.valueOf(i6));
                Log.i(TAG, "LIGHT_MUSIC_RHYTHM_MODE value is " + i6);
                break;
            case 557860615:
                int i7 = carBodyPacket.getmAmbientMulticolorStateR();
                int areaId3 = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener5 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener5.onAmbientMulticolorStateChanged(areaId3, i7);
                    Log.d(TAG, " onAmbientMulticolorStateChanged in multiState:" + i7 + ",multiId:" + areaId3 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener5));
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
                    absBYDAutoLightListener6.onTurnLightFlashStateChanged(turnLightState);
                    Log.d(TAG, "onTurnLightFlashStateChanged in:" + turnLightState + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener6));
                }
                Log.e(TAG, "onTurnLightFlashStateChanged out:" + turnLightState);
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
                        Log.d(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener7));
                    } else {
                        absBYDAutoLightListener7.onLightOff(2);
                        Log.d(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener7));
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
                        Log.d(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener8));
                    } else {
                        absBYDAutoLightListener8.onLightOff(3);
                        Log.d(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener8));
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
                        Log.d(TAG, "ID_LIGHT_REAR_FOGR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener9));
                    } else {
                        absBYDAutoLightListener9.onLightOff(7);
                        Log.d(TAG, "ID_LIGHT_REAR_FOGR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener9));
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
            case LightHalId.ID_STOP_LIGHT_STATE /* 557870867 */:
                int stopLightStateR = carBodyPacket.getStopLightStateR();
                Iterator<AbsBYDAutoLightListener> it16 = this.mAbsBydAutoLightListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onStopLightStateChanged(stopLightStateR);
                }
                caseFeatureIds(2799, Integer.valueOf(stopLightStateR));
                break;
            case LightHalId.ID_LIGHT_SIDE_STATUSR /* 557879809 */:
                int lightSideStatusR = carBodyPacket.getLightSideStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener10 : this.mAbsBydAutoLightListeners) {
                    if (lightSideStatusR == 1) {
                        absBYDAutoLightListener10.onLightOn(1);
                        Log.d(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    } else {
                        absBYDAutoLightListener10.onLightOff(1);
                        Log.d(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    }
                }
                caseFeatureIds(2842, Integer.valueOf(lightSideStatusR));
                Log.e(TAG, "LIGHT_SIDE_LIGHT caseFeatureIds is in");
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
                } else if (i != 2833) {
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
                        case 1257:
                            int ambientMusicModeState2 = this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(1);
                            bYDAutoEventValue.intValue = ambientMusicModeState2;
                            sparseArray.put(1257, Integer.valueOf(ambientMusicModeState2));
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
                                                    Log.d(TAG, "case fail ! please check out");
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    int lightRearFog2 = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                    bYDAutoEventValue.intValue = lightRearFog2;
                    sparseArray.put(2833, Integer.valueOf(lightRearFog2));
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getAFSSwitch() {
        Log.d(TAG, "getAFSSwitch is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAFSSwitch", new Object[0]);
            Log.d(TAG, "getAFSSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAFSSwitch();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbRingBrightness() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getAmbRingBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarRing();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbRingColor() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getAmbRingColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorStarRing();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int[] getAmbientColors(int i) {
        Log.d(TAG, "getAmbientColors is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientColors", Integer.valueOf(i));
            Log.d(TAG, "getAmbientColors mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientColors(i);
        }
        Log.d(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getAmbientColorsSupport() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getAmbientColorsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientColorsSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientFeedBack() {
        Log.d(TAG, "getAmbientFeedBack is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.d(TAG, "getAmbientFeedBack is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAmbientFeedBack mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAmbientMulticolorMode(int i) {
        Log.d(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMulticolorMode", Integer.valueOf(i));
            Log.d(TAG, "getAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMulticolorState(int i) {
        Log.d(TAG, "getAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMulticolorState", Integer.valueOf(i));
            Log.d(TAG, "getAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicMode(int i) {
        Log.d(TAG, "getAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMusicMode", Integer.valueOf(i));
            Log.d(TAG, "getAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicMode(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicModeState(int i) {
        Log.d(TAG, "getAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMusicModeState", Integer.valueOf(i));
            Log.d(TAG, "getAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicRhym(int i) {
        Log.d(TAG, "getAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getAmbientMusicRhym", Integer.valueOf(i));
            Log.d(TAG, "getAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicRhym(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDayLightSupport() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getDayLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDayLightSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDayTimeLightState() {
        Log.d(TAG, "getDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getDayTimeLightState", new Object[0]);
            Log.d(TAG, "getDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDayTimeLightState();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDayTimeRunningLightState() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getDayTimeRunningLightState();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getDayTimeRunningLightState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    @Deprecated
    public int getDoorLightState() {
        Log.d(TAG, "getDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getDoorLightState", new Object[0]);
            Log.d(TAG, "getDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLightState();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getIalBrightness(int i) {
        Log.i(TAG, "getIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.d(TAG, "adapter is not connect");
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
            Log.d(TAG, "getIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.d(TAG, "adapter is not connect");
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

    public int getLastSetedArea() {
        Log.d(TAG, "getLastSetedArea is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getLastSetedArea mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightAutoStatus() {
        Log.d(TAG, "getLightAutoStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getLightAutoStatus", new Object[0]);
            Log.d(TAG, "getLightAutoStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLightAutoStatus();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightDelayTime(int i) {
        Log.d(TAG, "getLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("getLightDelayTime", Integer.valueOf(i));
            Log.d(TAG, "getLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLightDelayTime(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightStatus(int i) {
        Log.d(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        int lightSideStatus = 0;
        if (1 == this.mForTest) {
            this.mTestCallback.c("getLightStatus", Integer.valueOf(i));
            Log.d(TAG, "getLightStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        switch (i) {
            case 1:
                Log.d(TAG, "getLightSideStatus");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightSideStatus();
                break;
            case 2:
                Log.d(TAG, "getLightLowBeamStatus");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                break;
            case 3:
                Log.d(TAG, "getLightHighBeam");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                break;
            case 4:
                Log.d(TAG, "getLightLeftTurnSignal");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightLeftTurnSignal();
                break;
            case 5:
                Log.d(TAG, "getLightRightTurnSignal");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRightTurnSignal();
                break;
            case 6:
                Log.d(TAG, "getLightFrontFog");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightFrontFog();
                break;
            case 7:
                Log.d(TAG, "getLightRearFog");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                break;
            case 8:
                Log.d(TAG, "getLightFoot");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightFoot();
                break;
            case 9:
                Log.d(TAG, "getLightRearPosition");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                break;
        }
        Log.d(TAG, "getLightStatus Type is:" + i + " And Result is:" + lightSideStatus);
        return lightSideStatus;
    }

    public int getReversingLightState() {
        Log.d(TAG, "getReversingLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.d(TAG, "getReversingLightState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getReversingLightState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getStopLightState() {
        Log.d(TAG, "getStopLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.d(TAG, "getStopLightState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getStopLightState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getStopLightState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getTurnLightFlashState() {
        Log.d(TAG, "getTurnLightFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getTurnLightFlashState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getTurnLightFlashState();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getTurnLightState() {
        Log.d(TAG, "getTurnLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.d(TAG, "getTurnLightState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getTurnLightState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        for (AbsBYDAutoLightListener absBYDAutoLightListener : this.mAbsBydAutoLightListeners) {
            absBYDAutoLightListener.onDataEventChanged(i, bYDAutoEventValue);
            Log.d(TAG, "onDataEventChanged id:" + i + ",value:" + bYDAutoEventValue + ",hashcod:" + System.identityHashCode(absBYDAutoLightListener));
        }
    }

    public void reSetAmbientParams(int i) {
        Log.d(TAG, "reSetAmbientParams is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.c("reSetAmbientParams", Integer.valueOf(i));
            Log.d(TAG, "reSetAmbientParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() == null) {
                Log.d(TAG, "adapter is not connect");
            }
            this.mTsManagerImpl.getCarBodyManager().reSetAmbientParams(i);
        }
    }

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        registerListener(absBYDAutoLightListener, new int[0]);
    }

    public void resetDelayLightParams() {
        Log.d(TAG, "resetDelayLightParams is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.c("resetDelayLightParams", new Object[0]);
            Log.d(TAG, "resetDelayLightParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() == null) {
                Log.d(TAG, "adapter is not connect");
            }
            this.mTsManagerImpl.getCarBodyManager().resetDelayLightParams();
        }
    }

    public int setAFSSwitchSate(int i) {
        Log.d(TAG, "setAFSSwitchSate is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAFSSwitchSate", Integer.valueOf(i));
            Log.d(TAG, "setAFSSwitchSate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAFSSwitchSate(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        Log.d(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMulticolorMode", Integer.valueOf(i), Integer.valueOf(i2));
            Log.d(TAG, "setAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorMode(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMulticolorState(int i, int i2) {
        Log.d(TAG, "setAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMulticolorState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.d(TAG, "setAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorState(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicMode(int i, int i2) {
        Log.d(TAG, "setAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMusicMode", Integer.valueOf(i), Integer.valueOf(i2));
            Log.d(TAG, "setAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicMode(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicModeState(int i, int i2) {
        Log.d(TAG, "setAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMusicModeState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.d(TAG, "setAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicModeState(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicRhym(int i, int i2) {
        Log.d(TAG, "setAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setAmbientMusicRhym", Integer.valueOf(i), Integer.valueOf(i2));
            Log.d(TAG, "setAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicRhym(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setBrightness(int i, int i2) {
        Log.d(TAG, "setBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setBrightness", Integer.valueOf(i), Integer.valueOf(i2));
            Log.d(TAG, "setBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setBrightness(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setDayTimeLightState(int i) {
        Log.d(TAG, "setDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setDayTimeLightState", Integer.valueOf(i));
            Log.d(TAG, "setDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setDayTimeLightState(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setDoorLightState(int i) {
        Log.d(TAG, "setDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setDoorLightState", Integer.valueOf(i));
            Log.d(TAG, "setDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setDoorLightState(i);
        }
        Log.d(TAG, "adapter is not connect");
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
                    Log.d(TAG, "the type of value is not a suitable type");
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 1156) {
            setIalBrightness(2, iIntValue, 2);
            Log.d(TAG, "setIALBrightnessBack:" + iIntValue);
        } else if (i == 1157) {
            setIalColor(2, iIntValue, 1);
            Log.d(TAG, "setIALColorFrontBack:" + iIntValue);
        } else if (i == 1160) {
            setIalBrightness(1, iIntValue, 1);
            Log.d(TAG, "setIALBrightnessFront:" + iIntValue);
        } else if (i == 1161) {
            setIalColor(1, iIntValue, 1);
            Log.d(TAG, "setIALColorFront:" + iIntValue);
        } else if (i == 1164) {
            setDoorLightState(iIntValue);
            Log.d(TAG, "Setting.SET_INSIDE_LIGHT_DOOR_STATE_SET:");
        } else if (i == 1257) {
            setAmbientMusicModeState(0, iIntValue);
            Log.d(TAG, "SET_MUSIC_MODE_STATE_SET: " + iIntValue);
        } else if (i == 2802) {
            setDayTimeLightState(iIntValue);
            Log.d(TAG, "setDayTimeLightState" + iIntValue);
        } else if (i == 2805) {
            setAmbientMulticolorMode(1, iIntValue);
            Log.d(TAG, "setAmbientMulticolorMode" + iIntValue);
        } else if (i == 2811) {
            setAmbientMulticolorState(0, iIntValue);
            Log.d(TAG, "setAmbientMulticolorState" + iIntValue);
        } else if (i == 2831) {
            setAmbientMusicMode(0, iIntValue);
            Log.d(TAG, "Light.LIGHT_MUSIC_RHYTHM_MODE_SET:");
        } else if (i == 2807) {
            setAmbientMulticolorMode(0, iIntValue);
            Log.d(TAG, "setAmbientMulticolorMode" + iIntValue);
        } else if (i != 2808) {
            switch (i) {
                case 2844:
                    setIalBrightness(5, iIntValue, 1);
                    Log.d(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP_BRIGHTNESS:" + iIntValue);
                    break;
                case 2845:
                    setIalColor(5, iIntValue, 1);
                    Log.d(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP:" + iIntValue);
                    break;
                case 2846:
                    setIalBrightness(iIntValue, 4, 1);
                    Log.d(TAG, "Light.LIGHT_STAR_SKY_ATMOSPHERE_LAMP_BRIGHTNESS:" + iIntValue);
                    break;
                case 2847:
                    setIalColor(4, iIntValue, 1);
                    Log.d(TAG, "LIGHT_STAR_SKY_ATMOSPHERE_LAMP:" + iIntValue);
                    break;
                default:
                    Log.d(TAG, "case fail ! please check out");
                    return -1;
            }
        } else {
            setAmbientMulticolorState(1, iIntValue);
            Log.d(TAG, "setAmbientMulticolorState" + iIntValue);
        }
        return 1;
    }

    public int setIalBrightness(int i, int i2, int i3) {
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setIALBrightness", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            Log.d(TAG, "setIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setIalBrightness(i, i2, i3);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setIalColor(int i, int i2, int i3) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setIALColor", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            Log.d(TAG, "setIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setIalColor(i, i2, i3);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setKtvMode(int i, int i2) {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "setKtvMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setKtvMode(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setLightDelayTime(int i, int i2) {
        Log.d(TAG, "setLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setLightDelayTime", Integer.valueOf(i), Integer.valueOf(i2));
            Log.d(TAG, "setLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setLightDelayTime(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setMusicModeState(int i, int i2) {
        Log.d(TAG, "setMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setMusicModeState", Integer.valueOf(i), Integer.valueOf(i2));
            Log.d(TAG, "setMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setMusicModeState(i, i2);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int setOnSceneMode(int i) {
        Log.i(TAG, "setOnSceneMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setOnSceneMode(i);
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.c("setOnSceneMode" + i, new Object[0]);
        Log.d(TAG, "setOnSceneMode mForTestValue is 0 execute return");
        return 0;
    }

    public int setTurnLightsFlashState(int i) {
        Log.d(TAG, "setTurnLightsFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.c("setTurnLightsFlashState", Integer.valueOf(i));
            Log.d(TAG, "setTurnLightsFlashState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setTurnLightsFlashState(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(d dVar) {
    }

    public void unregisterListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 != this.mForTest) {
            if (absBYDAutoLightListener == null) {
                return;
            }
            this.mAbsBydAutoLightListeners.remove(absBYDAutoLightListener);
            unregisterConnectListener(absBYDAutoLightListener);
            return;
        }
        this.mTestCallback.e(null);
        Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
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
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoLightListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }
}
