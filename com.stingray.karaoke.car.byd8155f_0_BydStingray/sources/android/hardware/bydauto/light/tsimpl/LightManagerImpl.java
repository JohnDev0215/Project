package android.hardware.bydauto.light.tsimpl;

import B5.H;
import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.SparseArray;
import b.d;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import e.C1148k;
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
    private C1148k mTestCallback;
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
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (1 == i7) {
                C1148k c1148k = new C1148k();
                HandlerThread handlerThread = new HandlerThread("TestLightManagerImplCallback");
                handlerThread.start();
                c1148k.f13669b = new H(c1148k, handlerThread.getLooper(), 11);
                this.mTestCallback = c1148k;
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.light.tsimpl.LightManagerImpl.1
            public void onBinderStateChanged(boolean z6) {
                Log.v(LightManagerImpl.TAG, "onBinderStateChanged is " + z6);
            }

            public void onBodyChanged(int i10, int i11, CarBodyPacket carBodyPacket) {
                if (LightManagerImpl.this.mAbsBydAutoLightListeners.isEmpty()) {
                    Log.v(LightManagerImpl.TAG, "mAbsBydAutoLightListeners is null. ");
                } else {
                    LightManagerImpl.this.lightCallback(i10, i11, carBodyPacket);
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static LightManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LightManagerImpl.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new LightManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lightCallback(int i7, int i10, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "LightCallback propertyId:" + i7);
        switch (i7) {
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
                int i11 = carBodyPacket.getmLightAutoStatusOne();
                for (AbsBYDAutoLightListener absBYDAutoLightListener : this.mAbsBydAutoLightListeners) {
                    if (i11 == 1) {
                        absBYDAutoLightListener.onLightAutoSwitchOn();
                        absBYDAutoLightListener.onDoorLightStateChanged(i11);
                    } else {
                        absBYDAutoLightListener.onLightAutoSwitchOff();
                        absBYDAutoLightListener.onDoorLightStateChanged(i11);
                    }
                }
                break;
            case LightHalId.ID_LIGHT_AUTO_STATUS_TWO /* 557860609 */:
                int i12 = carBodyPacket.getmLightAutoStatusTwo();
                for (AbsBYDAutoLightListener absBYDAutoLightListener2 : this.mAbsBydAutoLightListeners) {
                    if (i12 == 1) {
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
                int i13 = carBodyPacket.getmAmbientMusicModeStateR();
                int areaId = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener3 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener3.onAmbientMusicModeState(areaId, i13);
                    Log.v(TAG, "ID_AMBIENT_MUSIC_MODE_STATE_R is onAmbientMusicModeState in " + i13 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener3));
                }
                caseFeatureIds(1255, Integer.valueOf(i13));
                Log.i(TAG, "SET_MUSIC_MODE_STATE musicState is " + i13);
                break;
            case 557860613:
                int i14 = carBodyPacket.getmAmbientMusicModeR();
                int areaId2 = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener4 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener4.onAmbientMusicModechanged(areaId2, i14);
                    Log.v(TAG, "ID_AMBIENT_MUSIC_MODE_R is onAmbientMusicModechanged in" + i14 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener4));
                }
                caseFeatureIds(2829, Integer.valueOf(i14));
                Log.i(TAG, "LIGHT_MUSIC_RHYTHM_MODE value is " + i14);
                break;
            case 557860615:
                int i15 = carBodyPacket.getmAmbientMulticolorStateR();
                int areaId3 = carBodyPacket.getAreaId();
                for (AbsBYDAutoLightListener absBYDAutoLightListener5 : this.mAbsBydAutoLightListeners) {
                    absBYDAutoLightListener5.onAmbientMulticolorStateChanged(areaId3, i15);
                    Log.v(TAG, " onAmbientMulticolorStateChanged in multiState:" + i15 + ",multiId:" + areaId3 + " listener hashCode: " + System.identityHashCode(absBYDAutoLightListener5));
                }
                caseFeatureIds(2808, Integer.valueOf(i15));
                break;
            case 557860617:
                int i16 = carBodyPacket.getmAmbientMulticolorModeR();
                int areaId4 = carBodyPacket.getAreaId();
                Iterator<AbsBYDAutoLightListener> it10 = this.mAbsBydAutoLightListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onAmbientMulticolorModeChanged(areaId4, i16);
                }
                caseFeatureIds(2805, Integer.valueOf(i16));
                break;
            case LightHalId.ID_DAY_TIME_LIGHT_STATE_R /* 557860619 */:
                int i17 = carBodyPacket.getmDayTimeLightStateR();
                Iterator<AbsBYDAutoLightListener> it11 = this.mAbsBydAutoLightListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onDayTimeLightChanged(i17);
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
                    StringBuilder sbJ = b.j("onTurnLightStateChanged in:", turnLightState, " listener hashCode: ");
                    sbJ.append(System.identityHashCode(absBYDAutoLightListener6));
                    Log.e(TAG, sbJ.toString());
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
            case LightHalId.ID_STOP_LIGHT_STATE /* 557870867 */:
                int stopLightStateR = carBodyPacket.getStopLightStateR();
                Iterator<AbsBYDAutoLightListener> it17 = this.mAbsBydAutoLightListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onStopLightStateChanged(stopLightStateR);
                }
                caseFeatureIds(2799, Integer.valueOf(stopLightStateR));
                break;
            case LightHalId.ID_LIGHT_SIDE_STATUSR /* 557879809 */:
                int lightSideStatusR = carBodyPacket.getLightSideStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener10 : this.mAbsBydAutoLightListeners) {
                    if (lightSideStatusR == 1) {
                        absBYDAutoLightListener10.onLightOn(1);
                        Log.v(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    } else {
                        absBYDAutoLightListener10.onLightOff(1);
                        Log.v(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
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
            for (int i7 : iArr) {
                if (i7 <= 0) {
                    bYDAutoEventValue.intValue = -2147482648;
                } else if (i7 == 1156) {
                    int ialBrightness = getIalBrightness(2);
                    bYDAutoEventValue.intValue = ialBrightness;
                    sparseArray.put(1156, Integer.valueOf(ialBrightness));
                } else if (i7 == 1157) {
                    int ialColor = getIalColor(2);
                    bYDAutoEventValue.intValue = ialColor;
                    sparseArray.put(1157, Integer.valueOf(ialColor));
                } else if (i7 == 2807) {
                    int ambientMulticolorMode = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                    bYDAutoEventValue.intValue = ambientMulticolorMode;
                    sparseArray.put(2807, Integer.valueOf(ambientMulticolorMode));
                } else if (i7 == 2808) {
                    int ambientMulticolorState = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(0);
                    bYDAutoEventValue.intValue = ambientMulticolorState;
                    sparseArray.put(2808, Integer.valueOf(ambientMulticolorState));
                } else if (i7 == 2832) {
                    int lightRearFog = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                    bYDAutoEventValue.intValue = lightRearFog;
                    sparseArray.put(2832, Integer.valueOf(lightRearFog));
                } else if (i7 != 2833) {
                    switch (i7) {
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
                            switch (i7) {
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
                                    switch (i7) {
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
                                            switch (i7) {
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
                                                    Log.v(TAG, "case fail ! please check out");
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
        Log.v(TAG, "getAFSSwitch is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getAFSSwitch", new Object[0]);
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

    public int[] getAmbientColors(int i7) {
        Log.v(TAG, "getAmbientColors is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getAmbientColors", Integer.valueOf(i7));
            Log.v(TAG, "getAmbientColors mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientColors(i7);
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
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getAmbientFeedBack mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMulticolorMode(int i7) {
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getAmbientMulticolorMode", Integer.valueOf(i7));
            Log.v(TAG, "getAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMulticolorState(int i7) {
        Log.v(TAG, "getAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getAmbientMulticolorState", Integer.valueOf(i7));
            Log.v(TAG, "getAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicMode(int i7) {
        Log.v(TAG, "getAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getAmbientMusicMode", Integer.valueOf(i7));
            Log.v(TAG, "getAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicMode(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicModeState(int i7) {
        Log.v(TAG, "getAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getAmbientMusicModeState", Integer.valueOf(i7));
            Log.v(TAG, "getAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientMusicRhym(int i7) {
        Log.v(TAG, "getAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getAmbientMusicRhym", Integer.valueOf(i7));
            Log.v(TAG, "getAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicRhym(i7);
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
            this.mTestCallback.a("getDayTimeLightState", new Object[0]);
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
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" getDayTimeRunningLightState mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDayTimeRunningLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDoorLightState() {
        Log.v(TAG, "getDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getDoorLightState", new Object[0]);
            Log.v(TAG, "getDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getIalBrightness(int i7) {
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
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessBack();
        }
        if (i7 == 1 || i7 == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessFront();
        }
        if (i7 == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarrySky();
        }
        if (i7 == 5) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarRing();
        }
        return 0;
    }

    public int getIalColor(int i7) {
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
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColor(i7);
        }
        if (i7 == 1 || i7 == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorFront();
        }
        if (i7 == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorStarrySky();
        }
        if (i7 == 5) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorStarRing();
        }
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

    public int getLightAutoStatus() {
        Log.v(TAG, "getLightAutoStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getLightAutoStatus", new Object[0]);
            Log.v(TAG, "getLightAutoStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLightAutoStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightDelayTime(int i7) {
        Log.v(TAG, "getLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("getLightDelayTime", Integer.valueOf(i7));
            Log.v(TAG, "getLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLightDelayTime(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightStatus(int i7) {
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        int lightSideStatus = 0;
        if (1 == this.mForTest) {
            this.mTestCallback.a("getLightStatus", Integer.valueOf(i7));
            Log.v(TAG, "getLightStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        switch (i7) {
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
        Log.v(TAG, "getLightStatus Type is:" + i7 + " And Result is:" + lightSideStatus);
        return lightSideStatus;
    }

    public int getReversingLightState() {
        Log.v(TAG, "getReversingLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.v(TAG, "getReversingLightState is execute");
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getReversingLightState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getStopLightState() {
        Log.v(TAG, "getStopLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        Log.v(TAG, "getStopLightState is execute");
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getStopLightState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getStopLightState();
        }
        Log.e(TAG, "adapter is not connect");
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
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getTurnLightState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        for (AbsBYDAutoLightListener absBYDAutoLightListener : this.mAbsBydAutoLightListeners) {
            Log.e(TAG, "onDataEventChanged id:" + i7 + ",value:" + bYDAutoEventValue + ",hashcod:" + System.identityHashCode(absBYDAutoLightListener));
            absBYDAutoLightListener.onDataEventChanged(i7, bYDAutoEventValue);
        }
    }

    public void reSetAmbientParams(int i7) {
        Log.v(TAG, "reSetAmbientParams is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.a("reSetAmbientParams", Integer.valueOf(i7));
            Log.v(TAG, "reSetAmbientParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() == null) {
                Log.v(TAG, "adapter is not connect");
            }
            this.mTsManagerImpl.getCarBodyManager().reSetAmbientParams(i7);
        }
    }

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        registerListener(absBYDAutoLightListener, new int[0]);
    }

    public void resetDelayLightParams() {
        Log.v(TAG, "resetDelayLightParams is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.a("resetDelayLightParams", new Object[0]);
            Log.v(TAG, "resetDelayLightParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() == null) {
                Log.v(TAG, "adapter is not connect");
            }
            this.mTsManagerImpl.getCarBodyManager().resetDelayLightParams();
        }
    }

    public int setAFSSwitchSate(int i7) {
        Log.v(TAG, "setAFSSwitchSate is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setAFSSwitchSate", Integer.valueOf(i7));
            Log.v(TAG, "setAFSSwitchSate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAFSSwitchSate(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMulticolorMode(int i7, int i10) {
        Log.v(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setAmbientMulticolorMode", Integer.valueOf(i7), Integer.valueOf(i10));
            Log.v(TAG, "setAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorMode(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMulticolorState(int i7, int i10) {
        Log.v(TAG, "setAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setAmbientMulticolorState", Integer.valueOf(i7), Integer.valueOf(i10));
            Log.v(TAG, "setAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorState(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicMode(int i7, int i10) {
        Log.v(TAG, "setAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setAmbientMusicMode", Integer.valueOf(i7), Integer.valueOf(i10));
            Log.v(TAG, "setAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicMode(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicModeState(int i7, int i10) {
        Log.v(TAG, "setAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setAmbientMusicModeState", Integer.valueOf(i7), Integer.valueOf(i10));
            Log.v(TAG, "setAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicModeState(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAmbientMusicRhym(int i7, int i10) {
        Log.v(TAG, "setAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setAmbientMusicRhym", Integer.valueOf(i7), Integer.valueOf(i10));
            Log.v(TAG, "setAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicRhym(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setBrightness(int i7, int i10) {
        Log.v(TAG, "setBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setBrightness", Integer.valueOf(i7), Integer.valueOf(i10));
            Log.v(TAG, "setBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setBrightness(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setDayTimeLightState(int i7) {
        Log.v(TAG, "setDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setDayTimeLightState", Integer.valueOf(i7));
            Log.v(TAG, "setDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setDayTimeLightState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setDoorLightState(int i7) {
        Log.v(TAG, "setDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setDoorLightState", Integer.valueOf(i7));
            Log.v(TAG, "setDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setDoorLightState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i7, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).getClass();
            } else if (obj instanceof Double) {
                ((Double) obj).getClass();
            } else if (!(obj instanceof byte[])) {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        if (i7 == 1156) {
            setIalBrightness(2, iIntValue, 2);
            a.p(new StringBuilder("setIALBrightnessBack:"), iIntValue, TAG);
        } else if (i7 == 1157) {
            setIalColor(2, iIntValue, 1);
            a.p(new StringBuilder("setIALColorFrontBack:"), iIntValue, TAG);
        } else if (i7 == 1160) {
            setIalBrightness(1, iIntValue, 1);
            a.p(new StringBuilder("setIALBrightnessFront:"), iIntValue, TAG);
        } else if (i7 == 1161) {
            setIalColor(1, iIntValue, 1);
            a.p(new StringBuilder("setIALColorFront:"), iIntValue, TAG);
        } else if (i7 == 1164) {
            setDoorLightState(iIntValue);
            Log.v(TAG, "Setting.SET_INSIDE_LIGHT_DOOR_STATE_SET:");
        } else if (i7 == 1257) {
            setAmbientMusicModeState(0, iIntValue);
            Log.v(TAG, "SET_MUSIC_MODE_STATE_SET: " + iIntValue);
        } else if (i7 == 2802) {
            setDayTimeLightState(iIntValue);
            Log.v(TAG, "setDayTimeLightState" + iIntValue);
        } else if (i7 == 2805) {
            setAmbientMulticolorMode(1, iIntValue);
            Log.v(TAG, "setAmbientMulticolorMode" + iIntValue);
        } else if (i7 == 2811) {
            setAmbientMulticolorState(0, iIntValue);
            Log.v(TAG, "setAmbientMulticolorState" + iIntValue);
        } else if (i7 == 2831) {
            setAmbientMusicMode(0, iIntValue);
            Log.v(TAG, "Light.LIGHT_MUSIC_RHYTHM_MODE_SET:");
        } else if (i7 == 2807) {
            setAmbientMulticolorMode(0, iIntValue);
            Log.v(TAG, "setAmbientMulticolorMode" + iIntValue);
        } else if (i7 != 2808) {
            switch (i7) {
                case 2844:
                    setIalBrightness(5, iIntValue, 1);
                    a.p(new StringBuilder("LIGHT_STAR_RING_ATMOSPHERE_LAMP_BRIGHTNESS:"), iIntValue, TAG);
                    break;
                case 2845:
                    setIalColor(5, iIntValue, 1);
                    a.p(new StringBuilder("LIGHT_STAR_RING_ATMOSPHERE_LAMP:"), iIntValue, TAG);
                    break;
                case 2846:
                    setIalBrightness(iIntValue, 4, 1);
                    a.p(new StringBuilder("Light.LIGHT_STAR_SKY_ATMOSPHERE_LAMP_BRIGHTNESS:"), iIntValue, TAG);
                    break;
                case 2847:
                    setIalColor(4, iIntValue, 1);
                    a.p(new StringBuilder("LIGHT_STAR_SKY_ATMOSPHERE_LAMP:"), iIntValue, TAG);
                    break;
                default:
                    Log.v(TAG, "case fail ! please check out");
                    return -1;
            }
        } else {
            setAmbientMulticolorState(1, iIntValue);
            Log.v(TAG, "setAmbientMulticolorState" + iIntValue);
        }
        return 1;
    }

    public int setIalBrightness(int i7, int i10, int i11) {
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setIALBrightness", Integer.valueOf(i7), Integer.valueOf(i10), Integer.valueOf(i11));
            Log.v(TAG, "setIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setIalBrightness(i7, i10, i11);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setIalColor(int i7, int i10, int i11) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setIALColor", Integer.valueOf(i7), Integer.valueOf(i10), Integer.valueOf(i11));
            Log.v(TAG, "setIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setIalColor(i7, i10, i11);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setKtvMode(int i7, int i10) {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setKtvMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setKtvMode(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setLightDelayTime(int i7, int i10) {
        Log.v(TAG, "setLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setLightDelayTime", Integer.valueOf(i7), Integer.valueOf(i10));
            Log.v(TAG, "setLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setLightDelayTime(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMusicModeState(int i7, int i10) {
        Log.v(TAG, "setMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setMusicModeState", Integer.valueOf(i7), Integer.valueOf(i10));
            Log.v(TAG, "setMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setMusicModeState(i7, i10);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setOnSceneMode(int i7) {
        Log.i(TAG, "setOnSceneMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(a.e(i7, "setOnSceneMode"), new Object[0]);
            Log.v(TAG, "setOnSceneMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setOnSceneMode(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setTurnLightsFlashState(int i7) {
        Log.v(TAG, "setTurnLightsFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a("setTurnLightsFlashState", Integer.valueOf(i7));
            Log.v(TAG, "setTurnLightsFlashState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setTurnLightsFlashState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(d dVar) {
    }

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe light listener " + absBYDAutoLightListener + " featureIDs = " + Arrays.toString(iArr) + ",HashCode:" + System.identityHashCode(absBYDAutoLightListener));
        if (1 == this.mForTest) {
            C1148k c1148k = this.mTestCallback;
            if (absBYDAutoLightListener == null) {
                c1148k.getClass();
            } else {
                c1148k.f13668a = absBYDAutoLightListener;
            }
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

    public void unregisterListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.getClass();
            a.t(new StringBuilder("unregisterListener mForTestValue is"), this.mForTest, " execute return", TAG);
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
}
