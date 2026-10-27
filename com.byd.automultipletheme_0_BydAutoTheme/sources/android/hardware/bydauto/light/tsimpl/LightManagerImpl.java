package android.hardware.bydauto.light.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.hardware.bydauto.light.BYDAutoLightDevice;
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
            case LightHalId.ID_TURN_LIGHT_STATER /* 557860634 */:
                int turnLightStateR = carBodyPacket.getTurnLightStateR();
                Iterator<AbsBYDAutoLightListener> it14 = this.mAbsBydAutoLightListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onTurnLightStateChanged(turnLightStateR);
                }
                caseFeatureIds(2848, Integer.valueOf(turnLightStateR));
                break;
            case LightHalId.ID_LIGHT_LOW_BEAM_STATUSR /* 557860637 */:
                int lightLowBeamStatusR = carBodyPacket.getLightLowBeamStatusR();
                for (AbsBYDAutoLightListener absBYDAutoLightListener7 : this.mAbsBydAutoLightListeners) {
                    if (lightLowBeamStatusR == 1) {
                        absBYDAutoLightListener7.onLightOn(2);
                        Log.e(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener7));
                    } else {
                        absBYDAutoLightListener7.onLightOff(2);
                        Log.e(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener7));
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
                        Log.e(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener8));
                    } else {
                        absBYDAutoLightListener8.onLightOff(3);
                        Log.e(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener8));
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
                        Log.e(TAG, "ID_LIGHT_REAR_FOGR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener9));
                    } else {
                        absBYDAutoLightListener9.onLightOff(7);
                        Log.e(TAG, "ID_LIGHT_REAR_FOGR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener9));
                    }
                }
                caseFeatureIds(2832, Integer.valueOf(lightRearFog));
                Log.e(TAG, "LIGHT_REAR_FOG_LAMPS caseFeatureIds is in");
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
                        Log.e(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    } else {
                        absBYDAutoLightListener10.onLightOff(1);
                        Log.e(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(absBYDAutoLightListener10));
                    }
                }
                caseFeatureIds(2842, Integer.valueOf(lightSideStatusR));
                Log.e(TAG, "LIGHT_SIDE_LIGHT caseFeatureIds is in");
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        int lightSideStatus = 0;
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightStatus", Integer.valueOf(i));
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

    public int getAFSSwitch() {
        Log.v(TAG, "getAFSSwitch is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
            this.mTestCallback.setAbsBYDAutoLightListener(absBYDAutoLightListener);
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        for (AbsBYDAutoLightListener absBYDAutoLightListener : this.mAbsBydAutoLightListeners) {
            Log.e(TAG, "onDataEventChanged id:" + i + ",value:" + bYDAutoEventValue + ",hashcod:" + System.identityHashCode(absBYDAutoLightListener));
            absBYDAutoLightListener.onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void unregisterListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getDayTimeLightState", new Object[0]);
            Log.v(TAG, "getDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDayTimeLightState();
    }

    public int setDayTimeLightState(int i) {
        Log.v(TAG, "setDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getDoorLightState", new Object[0]);
            Log.v(TAG, "getDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDoorLightState();
    }

    public int setDoorLightState(int i) {
        Log.v(TAG, "setDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        Log.v(TAG, "setAFSSwitchSate is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, " getDayTimeRunningLightState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getSettingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDayTimeRunningLightState();
    }

    public int getAmbientColorsSupport() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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

    public int setIalColor(int i, int i2, int i3) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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

    public int getAmbRingBrightness() {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i != 1156) {
                        if (i != 1157) {
                            if (i != 2807) {
                                if (i != 2808) {
                                    if (i != 2832) {
                                        if (i == 2833) {
                                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                                            sparseArray.put(2833, Integer.valueOf(bYDAutoEventValue.intValue));
                                        } else {
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
                                                case 1257:
                                                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(1);
                                                    sparseArray.put(1257, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 2006:
                                                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                                                    sparseArray.put(2006, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 2797:
                                                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
                                                    sparseArray.put(2797, Integer.valueOf(bYDAutoEventValue.intValue));
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
                                                case 2827:
                                                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                                                    sparseArray.put(2827, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 2829:
                                                    bYDAutoEventValue.intValue = getAmbientMusicMode(1);
                                                    sparseArray.put(2829, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 2842:
                                                    bYDAutoEventValue.intValue = getLightStatus(1);
                                                    sparseArray.put(2842, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    break;
                                                case 4537:
                                                    bYDAutoEventValue.intValue = getAmbientColorsSupport();
                                                    sparseArray.put(4537, Integer.valueOf(bYDAutoEventValue.intValue));
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
                                                                    bYDAutoEventValue.intValue = getDayLightSupport();
                                                                    sparseArray.put(2800, Integer.valueOf(bYDAutoEventValue.intValue));
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
                                                                            Log.v(TAG, "case fail ! please check out");
                                                                            break;
                                                                    }
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                        }
                                    } else {
                                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                                        sparseArray.put(2832, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                } else {
                                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(0);
                                    sparseArray.put(2808, Integer.valueOf(bYDAutoEventValue.intValue));
                                }
                            } else {
                                bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                                sparseArray.put(2807, Integer.valueOf(bYDAutoEventValue.intValue));
                            }
                        } else {
                            bYDAutoEventValue.intValue = getIalColor(2);
                            sparseArray.put(1157, Integer.valueOf(bYDAutoEventValue.intValue));
                        }
                    } else {
                        bYDAutoEventValue.intValue = getIalBrightness(2);
                        sparseArray.put(1156, Integer.valueOf(bYDAutoEventValue.intValue));
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
            Log.v(TAG, TestLightManagerImplCallback.SETDAYTIMELIGHTSTATE + iIntValue);
        } else if (i == 2805) {
            setAmbientMulticolorMode(1, iIntValue);
            Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE + iIntValue);
        } else if (i == 2811) {
            setAmbientMulticolorState(0, iIntValue);
            Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE + iIntValue);
        } else if (i == 2831) {
            setAmbientMusicMode(0, iIntValue);
            Log.v(TAG, "Light.LIGHT_MUSIC_RHYTHM_MODE_SET:");
        } else if (i == 2807) {
            setAmbientMulticolorMode(0, iIntValue);
            Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE + iIntValue);
        } else if (i == 2808) {
            setAmbientMulticolorState(1, iIntValue);
            Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE + iIntValue);
        } else {
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
                    Log.v(TAG, "case fail ! please check out");
                    return -1;
            }
        }
        return 1;
    }
}
