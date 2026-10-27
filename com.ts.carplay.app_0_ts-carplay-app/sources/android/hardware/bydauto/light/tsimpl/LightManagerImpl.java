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
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.Arrays;
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

    private LightManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
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
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.light.tsimpl.LightManagerImpl.1
            public void onBodyChanged(int id, int areaId, CarBodyPacket packet) {
                if (!LightManagerImpl.this.mAbsBydAutoLightListeners.isEmpty()) {
                    try {
                        LightManagerImpl.this.lightCallback(id, areaId, packet);
                        return;
                    } catch (Exception e) {
                        Log.e(LightManagerImpl.TAG, "lightCallback is error");
                        return;
                    }
                }
                Log.v(LightManagerImpl.TAG, "mAbsBydAutoLightListeners is null. ");
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(LightManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lightCallback(int id, int areaId, CarBodyPacket packet) {
        Log.i(TAG, "LightCallback propertyId:" + id);
        switch (id) {
            case 557856041:
                caseFeatureIds(2803, Integer.valueOf(packet.getDayLightSupport()));
                break;
            case 557856188:
                caseFeatureIds(4537, Integer.valueOf(packet.getAmbientColorsSupport()));
                break;
            case 557857169:
                for (AbsBYDAutoLightListener listener : this.mAbsBydAutoLightListeners) {
                    listener.onAllWeatherLightSwitchStateChanged(packet.getAllWeatherLightSwitchStateR());
                }
                break;
            case 557857171:
                for (AbsBYDAutoLightListener listener2 : this.mAbsBydAutoLightListeners) {
                    listener2.onHeadlightControlModeChanged(packet.getHeadlightControlModeR());
                }
                break;
            case 557857172:
                for (AbsBYDAutoLightListener listener3 : this.mAbsBydAutoLightListeners) {
                    listener3.onRearFogLightSwitchStateChanged(packet.getRearFogLightSwitchStateR());
                }
                break;
            case 557857710:
                for (AbsBYDAutoLightListener listener4 : this.mAbsBydAutoLightListeners) {
                    listener4.onAmbRingColorChanged(packet.getIalColorStarRingR());
                }
                caseFeatureIds(2845, Integer.valueOf(packet.getIalColorStarRingR()));
                break;
            case 557857711:
                for (AbsBYDAutoLightListener listener5 : this.mAbsBydAutoLightListeners) {
                    listener5.onIALColorChanged(4, packet.getIalColorStarrySkyR());
                }
                caseFeatureIds(2847, Integer.valueOf(packet.getIalColorStarrySkyR()));
                break;
            case 557857712:
                for (AbsBYDAutoLightListener listener6 : this.mAbsBydAutoLightListeners) {
                    listener6.onIALColorChanged(1, packet.getIalColorFrontR());
                }
                caseFeatureIds(1161, Integer.valueOf(packet.getIalColorFrontR()));
                break;
            case 557857713:
                for (AbsBYDAutoLightListener listener7 : this.mAbsBydAutoLightListeners) {
                    listener7.onIALColorChanged(2, packet.getIalColorBackR());
                }
                caseFeatureIds(1157, Integer.valueOf(packet.getIalColorBackR()));
                break;
            case 557857716:
                for (AbsBYDAutoLightListener listener8 : this.mAbsBydAutoLightListeners) {
                    listener8.onAmbRingBrightnessChanged(packet.getIalBrightnessStarRingR());
                }
                caseFeatureIds(2844, Integer.valueOf(packet.getIalBrightnessStarRingR()));
                break;
            case 557857717:
                for (AbsBYDAutoLightListener listener9 : this.mAbsBydAutoLightListeners) {
                    listener9.onIALBrightnessChanged(4, packet.getIalBrightnessStarrySkyR());
                }
                caseFeatureIds(2846, Integer.valueOf(packet.getIalBrightnessStarrySkyR()));
                break;
            case 557857718:
                for (AbsBYDAutoLightListener listener10 : this.mAbsBydAutoLightListeners) {
                    listener10.onIALBrightnessChanged(2, packet.getIalBrightnessBackR());
                }
                caseFeatureIds(1156, Integer.valueOf(packet.getIalBrightnessBackR()));
                break;
            case 557857719:
                for (AbsBYDAutoLightListener listener11 : this.mAbsBydAutoLightListeners) {
                    listener11.onIALBrightnessChanged(1, packet.getIalBrightnessFrontR());
                }
                caseFeatureIds(1160, Integer.valueOf(packet.getIalBrightnessFrontR()));
                break;
            case 557860608:
                int lightOne = packet.getmLightAutoStatusOne();
                for (AbsBYDAutoLightListener listener12 : this.mAbsBydAutoLightListeners) {
                    if (lightOne == 1) {
                        listener12.onLightAutoSwitchOn();
                        listener12.onDoorLightStateChanged(lightOne);
                    } else {
                        listener12.onLightAutoSwitchOff();
                        listener12.onDoorLightStateChanged(lightOne);
                    }
                }
                break;
            case 557860609:
                int lightTwo = packet.getmLightAutoStatusTwo();
                for (AbsBYDAutoLightListener listener13 : this.mAbsBydAutoLightListeners) {
                    if (lightTwo == 1) {
                        listener13.onLightAutoSwitchOn();
                    } else {
                        listener13.onLightAutoSwitchOff();
                    }
                }
                break;
            case 557860610:
                int afsState = packet.getAfsSwitch();
                for (AbsBYDAutoLightListener listener14 : this.mAbsBydAutoLightListeners) {
                    listener14.onAFSSwitchStateChange(afsState);
                }
                caseFeatureIds(890, Integer.valueOf(afsState));
                break;
            case 557860611:
                int musicState = packet.getmAmbientMusicModeStateR();
                int musicId = packet.getAreaId();
                for (AbsBYDAutoLightListener listener15 : this.mAbsBydAutoLightListeners) {
                    listener15.onAmbientMusicModeState(musicId, musicState);
                    Log.v(TAG, "ID_AMBIENT_MUSIC_MODE_STATE_R is onAmbientMusicModeState in " + musicState + " listener hashCode: " + System.identityHashCode(listener15));
                }
                caseFeatureIds(1255, Integer.valueOf(musicState));
                Log.i(TAG, "SET_MUSIC_MODE_STATE musicState is " + musicState);
                break;
            case 557860613:
                int modeState = packet.getmAmbientMusicModeR();
                int modeId = packet.getAreaId();
                for (AbsBYDAutoLightListener listener16 : this.mAbsBydAutoLightListeners) {
                    listener16.onAmbientMusicModechanged(modeId, modeState);
                    Log.v(TAG, "ID_AMBIENT_MUSIC_MODE_R is onAmbientMusicModechanged in" + modeState + " listener hashCode: " + System.identityHashCode(listener16));
                }
                caseFeatureIds(2829, Integer.valueOf(modeState));
                Log.i(TAG, "LIGHT_MUSIC_RHYTHM_MODE value is " + modeState);
                break;
            case 557860615:
                int multiState = packet.getmAmbientMulticolorStateR();
                int multiId = packet.getAreaId();
                for (AbsBYDAutoLightListener listener17 : this.mAbsBydAutoLightListeners) {
                    listener17.onAmbientMulticolorStateChanged(multiId, multiState);
                    Log.v(TAG, " onAmbientMulticolorStateChanged in multiState:" + multiState + ",multiId:" + multiId + " listener hashCode: " + System.identityHashCode(listener17));
                }
                caseFeatureIds(2808, Integer.valueOf(multiState));
                break;
            case 557860617:
                int colorState = packet.getmAmbientMulticolorModeR();
                int colorId = packet.getAreaId();
                for (AbsBYDAutoLightListener listener18 : this.mAbsBydAutoLightListeners) {
                    listener18.onAmbientMulticolorModeChanged(colorId, colorState);
                }
                caseFeatureIds(2805, Integer.valueOf(colorState));
                break;
            case 557860619:
                int timeState = packet.getmDayTimeLightStateR();
                for (AbsBYDAutoLightListener listener19 : this.mAbsBydAutoLightListeners) {
                    listener19.onDayTimeLightChanged(timeState);
                    listener19.onDayTimeRunningLitghtStateChanged(timeState);
                }
                caseFeatureIds(2800, Integer.valueOf(timeState));
                break;
            case 557860629:
                int reversingLight = packet.getReversingLightState();
                for (AbsBYDAutoLightListener listener20 : this.mAbsBydAutoLightListeners) {
                    listener20.onReversingLightStateChanged(reversingLight);
                }
                caseFeatureIds(2797, Integer.valueOf(reversingLight));
                break;
            case 557860630:
                int turnLight = packet.getTurnLightState();
                for (AbsBYDAutoLightListener listener21 : this.mAbsBydAutoLightListeners) {
                    Log.e(TAG, "onTurnLightStateChanged in:" + turnLight + " listener hashCode: " + System.identityHashCode(listener21));
                    listener21.onTurnLightStateChanged(turnLight);
                }
                Log.e(TAG, "onTurnLightStateChanged out:" + turnLight);
                caseFeatureIds(2848, Integer.valueOf(turnLight));
                break;
            case 557860631:
                int feedBackR = packet.getAmbientFeedBack();
                for (AbsBYDAutoLightListener listener22 : this.mAbsBydAutoLightListeners) {
                    listener22.onAmbientFeedBackChanged(feedBackR);
                }
                caseFeatureIds(902, Integer.valueOf(feedBackR));
                break;
            case 557860634:
                int turnLightStateR = packet.getTurnLightStateR();
                for (AbsBYDAutoLightListener listener23 : this.mAbsBydAutoLightListeners) {
                    listener23.onTurnLightStateChanged(turnLightStateR);
                }
                caseFeatureIds(2848, Integer.valueOf(turnLightStateR));
                break;
            case 557860637:
                int lightLowBeam = packet.getLightLowBeamStatusR();
                for (AbsBYDAutoLightListener listener24 : this.mAbsBydAutoLightListeners) {
                    if (lightLowBeam == 1) {
                        listener24.onLightOn(2);
                        Log.e(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(listener24));
                    } else {
                        listener24.onLightOff(2);
                        Log.e(TAG, "ID_LIGHT_LOW_BEAM_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(listener24));
                    }
                }
                caseFeatureIds(2827, Integer.valueOf(lightLowBeam));
                Log.e(TAG, "LIGHT_LOW_BEAM_LIGHT caseFeatureIds is in");
                break;
            case 557860638:
                int lightHigh = packet.getLightHighBeamR();
                for (AbsBYDAutoLightListener listener25 : this.mAbsBydAutoLightListeners) {
                    if (lightHigh == 1) {
                        listener25.onLightOn(3);
                        Log.e(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOn listener hashCode: " + System.identityHashCode(listener25));
                    } else {
                        listener25.onLightOff(3);
                        Log.e(TAG, "ID_LIGHT_HIGH_BEAMR is onLightOff listener hashCode: " + System.identityHashCode(listener25));
                    }
                }
                caseFeatureIds(2820, Integer.valueOf(lightHigh));
                Log.e(TAG, "LIGHT_HIGH_BEAM_LIGHT caseFeatureIds is in");
                break;
            case 557860642:
                int lightRearFog = packet.getLightRearFog();
                for (AbsBYDAutoLightListener listener26 : this.mAbsBydAutoLightListeners) {
                    if (lightRearFog == 1) {
                        listener26.onLightOn(7);
                        Log.e(TAG, "ID_LIGHT_REAR_FOGR is onLightOn listener hashCode: " + System.identityHashCode(listener26));
                    } else {
                        listener26.onLightOff(7);
                        Log.e(TAG, "ID_LIGHT_REAR_FOGR is onLightOff listener hashCode: " + System.identityHashCode(listener26));
                    }
                }
                caseFeatureIds(2832, Integer.valueOf(lightRearFog));
                Log.e(TAG, "LIGHT_REAR_FOG_LAMPS caseFeatureIds is in");
                break;
            case 557860643:
                int dayTimeRunning = packet.getDayTimeRunningLightState();
                for (AbsBYDAutoLightListener listener27 : this.mAbsBydAutoLightListeners) {
                    listener27.onDayTimeRunningLitghtStateChanged(dayTimeRunning);
                }
                caseFeatureIds(2800, Integer.valueOf(packet.getDayTimeRunningLightState()));
                break;
            case 557860644:
                int lightRear = packet.getLightRearPosition();
                for (AbsBYDAutoLightListener listener28 : this.mAbsBydAutoLightListeners) {
                    listener28.onRearLampLightStateChanged(lightRear);
                }
                break;
            case 557860648:
                for (AbsBYDAutoLightListener listener29 : this.mAbsBydAutoLightListeners) {
                    listener29.onBacklightLevelsChanged(packet.getBacklightLevelR());
                }
                break;
            case 557860657:
                for (AbsBYDAutoLightListener listener30 : this.mAbsBydAutoLightListeners) {
                    listener30.onLeftFrontReadingLampStatusChanged(packet.getLeftFrontReadingLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH, Integer.valueOf(packet.getLeftFrontReadingLampStatusR()));
                break;
            case 557860658:
                for (AbsBYDAutoLightListener listener31 : this.mAbsBydAutoLightListeners) {
                    listener31.onRightFrontReadingLampStatusChanged(packet.getRightFrontReadingLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH, Integer.valueOf(packet.getRightFrontReadingLampStatusR()));
                break;
            case 557860659:
                for (AbsBYDAutoLightListener listener32 : this.mAbsBydAutoLightListeners) {
                    listener32.onLeftMiddleRowSideRLightSwitchChanged(packet.getLeftMiddleRowSideRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH, Integer.valueOf(packet.getLeftMiddleRowSideRLightSwitchR()));
                break;
            case 557860660:
                for (AbsBYDAutoLightListener listener33 : this.mAbsBydAutoLightListeners) {
                    listener33.onRightMiddleRowRLightSwitch(packet.getRightMiddleRowRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH, Integer.valueOf(packet.getRightMiddleRowRLightSwitchR()));
                break;
            case 557860661:
                for (AbsBYDAutoLightListener listener34 : this.mAbsBydAutoLightListeners) {
                    listener34.onLeftMiddleRowRLightRotaryStateChanged(packet.getLeftMiddleRowRLightRotaryStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(packet.getLeftMiddleRowRLightRotaryStateR()));
                break;
            case 557860662:
                for (AbsBYDAutoLightListener listener35 : this.mAbsBydAutoLightListeners) {
                    listener35.onRightMiddleRowRLightRotaryChanged(packet.getRightMiddleRowRLightRotaryR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(packet.getRightMiddleRowRLightRotaryR()));
                break;
            case 557860663:
                for (AbsBYDAutoLightListener listener36 : this.mAbsBydAutoLightListeners) {
                    listener36.onLeftRearRLightSwitchChanged(packet.getLeftRearRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH, Integer.valueOf(packet.getLeftRearRLightSwitchR()));
                break;
            case 557860664:
                for (AbsBYDAutoLightListener listener37 : this.mAbsBydAutoLightListeners) {
                    listener37.onRightRearRLightSwitchChanged(packet.getRightRearRLightSwitchR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH, Integer.valueOf(packet.getRightRearRLightSwitchR()));
                break;
            case 557860665:
                for (AbsBYDAutoLightListener listener38 : this.mAbsBydAutoLightListeners) {
                    listener38.onCargoLightSwitchStateChanged(packet.getCargoLightSwitchStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH, Integer.valueOf(packet.getCargoLightSwitchStateR()));
                break;
            case 557860668:
                for (AbsBYDAutoLightListener listener39 : this.mAbsBydAutoLightListeners) {
                    listener39.onLeftCornerLightStatusChanged(packet.getLeftCornerLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS, Integer.valueOf(packet.getLeftCornerLightStatusR()));
                break;
            case 557860669:
                for (AbsBYDAutoLightListener listener40 : this.mAbsBydAutoLightListeners) {
                    listener40.onRightCornerLightStatusChanged(packet.getRightCornerLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS, Integer.valueOf(packet.getRightCornerLightStatusR()));
                break;
            case 557860670:
                for (AbsBYDAutoLightListener listener41 : this.mAbsBydAutoLightListeners) {
                    listener41.onFrontFogLampStatusChanged(packet.getFrontFogLampStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS, Integer.valueOf(packet.getFrontFogLampStatusR()));
                break;
            case 557860671:
                for (AbsBYDAutoLightListener listener42 : this.mAbsBydAutoLightListeners) {
                    listener42.onFrontCenterLightStatusChanged(packet.getFrontCenterLightStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS, Integer.valueOf(packet.getFrontCenterLightStatusR()));
                break;
            case 557860672:
                for (AbsBYDAutoLightListener listener43 : this.mAbsBydAutoLightListeners) {
                    listener43.onPositionLightDisplayFeedCallbackChanged(packet.getPositionLightDisplayFeedCallbackR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK, Integer.valueOf(packet.getPositionLightDisplayFeedCallbackR()));
                break;
            case 557860674:
                for (AbsBYDAutoLightListener listener44 : this.mAbsBydAutoLightListeners) {
                    listener44.onRightDaytimeLightStateChanged(packet.getRightDaytimeLightStateR());
                }
                caseFeatureIds(2836, Integer.valueOf(packet.getRightDaytimeLightStateR()));
                break;
            case 557860675:
                for (AbsBYDAutoLightListener listener45 : this.mAbsBydAutoLightListeners) {
                    listener45.onLeftDaytimeLightStateChanged(packet.getLeftDaytimeLightStateR());
                }
                caseFeatureIds(2823, Integer.valueOf(packet.getLeftDaytimeLightStateR()));
                break;
            case 557860677:
                for (AbsBYDAutoLightListener listener46 : this.mAbsBydAutoLightListeners) {
                    listener46.onHightlightPositionChanged(packet.getHightlightPositionR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT, Integer.valueOf(packet.getHightlightPositionR()));
                break;
            case 557860680:
                for (AbsBYDAutoLightListener listener47 : this.mAbsBydAutoLightListeners) {
                    listener47.onLeftFrontPositionSWLStatusChanged(packet.getLeftFrontPositionSwlStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_LEFT_FRONT_POSITION_SPATIOTEMPORAL_WING_LIGHT_STATUS, Integer.valueOf(packet.getLeftFrontPositionSwlStatusR()));
                break;
            case 557860681:
                for (AbsBYDAutoLightListener listener48 : this.mAbsBydAutoLightListeners) {
                    listener48.onRightFrontPositionSWLStatusChanged(packet.getRightFrontPositionSwlStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_RIGHT_FRONT_POSITION_SPATIOTEMPORAL_WING_LIGHT_STATUS, Integer.valueOf(packet.getRightFrontPositionSwlStatusR()));
                break;
            case 557860682:
                for (AbsBYDAutoLightListener listener49 : this.mAbsBydAutoLightListeners) {
                    listener49.onLeftDaytimeRunLampStateChanged(packet.getLeftDaytimeRunLampStateR());
                }
                caseFeatureIds(2823, Integer.valueOf(packet.getLeftDaytimeRunLampStateR()));
                break;
            case 557860683:
                for (AbsBYDAutoLightListener listener50 : this.mAbsBydAutoLightListeners) {
                    listener50.onRightDaytimeRunLampStateChanged(packet.getRightDaytimeRunLampStateR());
                }
                caseFeatureIds(2836, Integer.valueOf(packet.getRightDaytimeRunLampStateR()));
                break;
            case 557860688:
                for (AbsBYDAutoLightListener listener51 : this.mAbsBydAutoLightListeners) {
                    listener51.onFrontFogLightSwitchStateChanged(packet.getFrontFogLightSwitchStateR());
                }
                break;
            case 557860693:
                for (AbsBYDAutoLightListener listener52 : this.mAbsBydAutoLightListeners) {
                    listener52.onLeftRemoteLightStateChanged(packet.getLeftRemoteLightStateR());
                }
                break;
            case 557860694:
                for (AbsBYDAutoLightListener listener53 : this.mAbsBydAutoLightListeners) {
                    listener53.onRightRemoteLightStateChanged(packet.getRightRemoteLightStateR());
                }
                break;
            case 557860695:
                for (AbsBYDAutoLightListener listener54 : this.mAbsBydAutoLightListeners) {
                    listener54.onMiddleSternLampStateChanged(packet.getMiddleSternLampStateR());
                }
                break;
            case 557860697:
                for (AbsBYDAutoLightListener listener55 : this.mAbsBydAutoLightListeners) {
                    listener55.onAmbientStateChanged(packet.getAmbientStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_MAIN_SWITCH, Integer.valueOf(packet.getAmbientStateR()));
                break;
            case 557860699:
                for (AbsBYDAutoLightListener listener56 : this.mAbsBydAutoLightListeners) {
                    listener56.onLightRearGlowLogoWorkStateChanged(packet.getLightRearGlowLogoWorkStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_REAR_GLOW_LOGO_WORK_STATE, Integer.valueOf(packet.getLightRearGlowLogoWorkStateR()));
                break;
            case 557860700:
                for (AbsBYDAutoLightListener listener57 : this.mAbsBydAutoLightListeners) {
                    listener57.onLightAdasIndicatorStatus(packet.getLightAdasIndicatorStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHTS_ADAS_INDICATOR_LIGHT_STATUS, Integer.valueOf(packet.getLightAdasIndicatorStatusR()));
                break;
            case 557860701:
                for (AbsBYDAutoLightListener listener58 : this.mAbsBydAutoLightListeners) {
                    listener58.onLightFrontGlowLogoWorkState(packet.getLightFrontGlowLogoWorkStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_FRONT_GLOW_LOGO_WORK_STATE, Integer.valueOf(packet.getLightFrontGlowLogoWorkStateR()));
                break;
            case 557860703:
                for (AbsBYDAutoLightListener listener59 : this.mAbsBydAutoLightListeners) {
                    listener59.onAtmospereLightNightWeakenModeChanged(packet.getAtmospereLightNightWeakenModeConfigR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE, Integer.valueOf(packet.getAtmospereLightNightWeakenModeConfigR()));
                break;
            case 557860706:
                for (AbsBYDAutoLightListener listener60 : this.mAbsBydAutoLightListeners) {
                    listener60.onAtmospereLampPopupPrompt(packet.getAtmospereLampPopupPromptR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_BRIGHTNESS_REDUCE_POPUP_PROMPT, Integer.valueOf(packet.getAtmospereLampPopupPromptR()));
                break;
            case 557860707:
                for (AbsBYDAutoLightListener listener61 : this.mAbsBydAutoLightListeners) {
                    listener61.onThemeColorSwitchState(packet.getThemeColorSwitchStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_THEME_COLOR_SWITCH_STATE, Integer.valueOf(packet.getThemeColorSwitchStateR()));
                break;
            case 557860709:
                for (AbsBYDAutoLightListener listener62 : this.mAbsBydAutoLightListeners) {
                    listener62.onAtmospereLampUiDisplayChanged(packet.getAtmospereLampUiDisplayR());
                }
                caseFeatureIds(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_UI_DISPLAY, Integer.valueOf(packet.getAtmospereLampUiDisplayR()));
                break;
            case 557860710:
                for (AbsBYDAutoLightListener listener63 : this.mAbsBydAutoLightListeners) {
                    listener63.onThemeColorFeedBackChanged(packet.getThemeColorFeedBackR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SETTING_THEME_COLOR_FEEDBACK, Integer.valueOf(packet.getAtmospereLampUiDisplayR()));
                break;
            case 557870867:
                int stopLight = packet.getStopLightStateR();
                for (AbsBYDAutoLightListener listener64 : this.mAbsBydAutoLightListeners) {
                    listener64.onStopLightStateChanged(stopLight);
                }
                caseFeatureIds(2799, Integer.valueOf(stopLight));
                break;
            case 557879809:
                int lightSide = packet.getLightSideStatusR();
                for (AbsBYDAutoLightListener listener65 : this.mAbsBydAutoLightListeners) {
                    Log.e(TAG, "mAbsBydAutoLightListeners toString : " + System.identityHashCode(this.mAbsBydAutoLightListeners));
                    Log.e(TAG, "mAbsBydAutoLightListeners is size : " + this.mAbsBydAutoLightListeners.size());
                    if (lightSide == 1) {
                        listener65.onLightOn(1);
                        Log.e(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOn listener hashCode: " + System.identityHashCode(listener65));
                    } else {
                        listener65.onLightOff(1);
                        Log.e(TAG, "ID_LIGHT_SIDE_STATUSR is onLightOff listener hashCode: " + System.identityHashCode(listener65));
                    }
                }
                caseFeatureIds(2842, Integer.valueOf(lightSide));
                Log.e(TAG, "LIGHT_SIDE_LIGHT caseFeatureIds is in lightSide = " + lightSide);
                break;
        }
    }

    public static LightManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (LightManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new LightManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
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

    public int getLightStatus(int type) {
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightStatus", Integer.valueOf(type));
            Log.v(TAG, "getLightStatus mForTestValue is 0 execute return");
            return 0;
        }
        int result = 0;
        switch (type) {
            case 1:
                Log.v(TAG, "getLightSideStatus");
                result = this.mTsManagerImpl.getCarBodyManager().getLightSideStatus();
                break;
            case 2:
                Log.v(TAG, "getLightLowBeamStatus");
                result = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                break;
            case 3:
                Log.v(TAG, "getLightHighBeam");
                result = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                break;
            case 4:
                Log.v(TAG, "getLightLeftTurnSignal");
                result = this.mTsManagerImpl.getCarBodyManager().getLightLeftTurnSignal();
                break;
            case 5:
                Log.v(TAG, "getLightRightTurnSignal");
                result = this.mTsManagerImpl.getCarBodyManager().getLightRightTurnSignal();
                break;
            case 6:
                Log.v(TAG, "getLightFrontFog");
                result = this.mTsManagerImpl.getCarBodyManager().getLightFrontFog();
                break;
            case 7:
                Log.v(TAG, "getLightRearFog");
                result = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                break;
            case 8:
                Log.v(TAG, "getLightFoot");
                result = this.mTsManagerImpl.getCarBodyManager().getLightFoot();
                break;
            case 9:
                Log.v(TAG, "getLightRearPosition");
                result = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                break;
        }
        Log.v(TAG, "getLightStatus Type is:" + type + " And Result is:" + result);
        return result;
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

    public int setAmbientState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAmbientState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientState(state);
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

    public int setAtmospereLightNightWeakenMode(int mode) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAtmospereLightNightWeakenMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAtmospereLightNightWeakenMode(mode);
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

    public int setThemeLinkedAmbienceLightColor(int color) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setThemeLinkedAmbienceLightColor mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setThemeLinkedAmbienceLightColor(color);
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

    public int setThemeLinkedAtmosphereLampSwitch(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setThemeLinkedAtmosphereLampSwitch mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setThemeLinkedAtmosphereLampSwitch(state);
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

    public void registerListener(AbsBYDAutoLightListener l) {
        registerListener(l, new int[0]);
    }

    public void registerListener(AbsBYDAutoLightListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe light listener " + listener + " featureIDs = " + Arrays.toString(featureIDs) + ",HashCode:" + System.identityHashCode(listener));
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoLightListener(listener);
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (!this.mAbsBydAutoLightListeners.contains(listener)) {
            this.mAbsBydAutoLightListeners.add(listener);
            Log.e(TAG, "mAbsBydAutoLightListeners add is:" + listener + ",hashcod:" + System.identityHashCode(listener));
        }
        Log.e(TAG, "javalib registerListener toString:" + System.identityHashCode(this.mAbsBydAutoLightListeners));
        Log.e(TAG, "javalib registerListener size:" + this.mAbsBydAutoLightListeners.size());
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoLightListener listener : this.mAbsBydAutoLightListeners) {
            Log.e(TAG, "onDataEventChanged id:" + id + ",value:" + value + ",hashcod:" + System.identityHashCode(listener));
            listener.onDataEventChanged(id, value);
        }
    }

    public void unregisterListener(IBYDAutoListener listener) {
    }

    public void unregisterListener(AbsBYDAutoLightListener l) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoLightListener(null);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (l == null) {
            return;
        }
        boolean removed = this.mAbsBydAutoLightListeners.remove(l);
        if (removed) {
            Log.e(TAG, "Listener removed: " + l + ", hashcode: " + System.identityHashCode(l));
        } else {
            Log.e(TAG, "Listener not found: " + l + ", hashcode: " + System.identityHashCode(l));
        }
        unregisterConnectListener(l);
    }

    public int getAmbientMusicModeState(int area) {
        Log.v(TAG, "getAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicModeState", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicModeState(area);
    }

    public int setAmbientMusicModeState(int area, int state) {
        Log.v(TAG, "setAmbientMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODESTATE, Integer.valueOf(area), Integer.valueOf(state));
            Log.v(TAG, "setAmbientMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicModeState(area, state);
    }

    public int getAmbientMusicMode(int area) {
        Log.v(TAG, "getAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicMode", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicMode(area);
    }

    public int setAmbientMusicMode(int area, int value) {
        Log.v(TAG, "setAmbientMusicMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMUSICMODE, Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setAmbientMusicMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicMode(area, value);
    }

    public int getAmbientMulticolorState(int area) {
        Log.v(TAG, "getAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorState", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(area);
    }

    public int setAmbientMulticolorState(int area, int value) {
        Log.v(TAG, "setAmbientMulticolorState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE, Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setAmbientMulticolorState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorState(area, value);
    }

    public int getAmbientMulticolorMode(int area) {
        Log.v(TAG, "getAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMulticolorMode", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(area);
    }

    public int setAmbientMulticolorMode(int area, int value) {
        Log.v(TAG, "setAmbientMulticolorMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE, Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setAmbientMulticolorMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMulticolorMode(area, value);
    }

    public int setAmbientMusicRhym(int area, int value) {
        Log.v(TAG, "setAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setAmbientMusicRhym", Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAmbientMusicRhym(area, value);
    }

    public int getAmbientMusicRhym(int area) {
        Log.v(TAG, "getAmbientMusicRhym is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientMusicRhym", Integer.valueOf(area));
            Log.v(TAG, "getAmbientMusicRhym mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientMusicRhym(area);
    }

    public int getLightDelayTime(int mode) {
        Log.v(TAG, "getLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getLightDelayTime", Integer.valueOf(mode));
            Log.v(TAG, "getLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLightDelayTime(mode);
    }

    public int setLightDelayTime(int mode, int value) {
        Log.v(TAG, "setLightDelayTime is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setLightDelayTime", Integer.valueOf(mode), Integer.valueOf(value));
            Log.v(TAG, "setLightDelayTime mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLightDelayTime(mode, value);
    }

    public int getDayTimeLightState() {
        Log.v(TAG, "getDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getDayTimeLightState", new Object[0]);
            Log.v(TAG, "getDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDayTimeLightState();
    }

    public int setDayTimeLightState(int state) {
        Log.v(TAG, "setDayTimeLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETDAYTIMELIGHTSTATE, Integer.valueOf(state));
            Log.v(TAG, "setDayTimeLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDayTimeLightState(state);
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
        return this.mTsManagerImpl.getCarBodyManager().getDoorLightState();
    }

    public int setDoorLightState(int state) {
        Log.v(TAG, "setDoorLightState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETDOORLIGHTSTATE, Integer.valueOf(state));
            Log.v(TAG, "setDoorLightState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDoorLightState(state);
    }

    public void reSetAmbientParams(int area) {
        Log.v(TAG, "reSetAmbientParams is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("reSetAmbientParams", Integer.valueOf(area));
            Log.v(TAG, "reSetAmbientParams mForTestValue is 0 execute return");
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() == null) {
                Log.v(TAG, "adapter is not connect");
            }
            this.mTsManagerImpl.getCarBodyManager().reSetAmbientParams(area);
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

    public int setAFSSwitchSate(int value) {
        Log.v(TAG, "setAFSSwitchSate is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETAFSSWITCHSATE, Integer.valueOf(value));
            Log.v(TAG, "setAFSSwitchSate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAFSSwitchSate(value);
    }

    public int setMusicModeState(int area, int value) {
        Log.v(TAG, "setMusicModeState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setMusicModeState", Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setMusicModeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMusicModeState(area, value);
    }

    public int[] getAmbientColors(int type) {
        Log.v(TAG, "getAmbientColors is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("getAmbientColors", Integer.valueOf(type));
            Log.v(TAG, "getAmbientColors mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return new int[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getAmbientColors(type);
    }

    public int setBrightness(int area, int value) {
        Log.v(TAG, "setBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setBrightness", Integer.valueOf(area), Integer.valueOf(value));
            Log.v(TAG, "setBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setBrightness(area, value);
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

    public int setTurnLightsFlashState(int state) {
        Log.v(TAG, "setTurnLightsFlashState is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestLightManagerImplCallback.SETTURNLIGHTSFLASHSTATE, Integer.valueOf(state));
            Log.v(TAG, "setTurnLightsFlashState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTurnLightsFlashState(state);
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

    public int getIalColor(int area) {
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
        if (area == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColor(area);
        }
        if (area == 1 || area == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorFront();
        }
        if (area == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorStarrySky();
        }
        if (area == 5) {
            return this.mTsManagerImpl.getCarBodyManager().getIalColorStarRing();
        }
        return 0;
    }

    public int setIalColor(int area, int color, int source) {
        Log.i(TAG, "setIALColor is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setIALColor", Integer.valueOf(area), Integer.valueOf(color), Integer.valueOf(source));
            Log.v(TAG, "setIALColor mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setIalColor(area, color, source);
    }

    public int getIalBrightness(int area) {
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
        if (area == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessBack();
        }
        if (area == 1 || area == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessFront();
        }
        if (area == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getIalBrightnessStarrySky();
        }
        if (area == 5) {
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

    public int setIalBrightness(int area, int brightness, int source) {
        Log.i(TAG, "setIALBrightness is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setIALBrightness", Integer.valueOf(area), Integer.valueOf(brightness), Integer.valueOf(source));
            Log.v(TAG, "setIALBrightness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setIalBrightness(area, brightness, source);
    }

    public int setOnSceneMode(int mode) {
        Log.i(TAG, "setOnSceneMode is execute");
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage("setOnSceneMode" + mode, new Object[0]);
            Log.v(TAG, "setOnSceneMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setOnSceneMode(mode);
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

    public int setKtvMode(int color, int brightness) {
        enforcePermission(BYDAutoLightDevice.PERMISSION_BYDAUTO_LIGHT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setKtvMode mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setKtvMode(color, brightness);
    }

    public int getBacklightLevels() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBacklightLevel mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getBacklightLevel();
    }

    public int setLeftFrontReadingLampSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftFrontReadingLampSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftFrontReadingLampSwitchState(state);
    }

    public int setRightFrontReadingLampSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightFrontReadingLampSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightFrontReadingLampSwitchState(state);
    }

    public int setLeftMiddleRowSideRLightSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftMiddleRowSideRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftMiddleRowSideRLightSwitchState(state);
    }

    public int setRightMiddleRowRLightSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightMiddleRowRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightMiddleRowRLightSwitchState(state);
    }

    public int setLeftMiddleRowRLightRotaryState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftMiddleRowRLightRotaryState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftMiddleRowRLightRotaryState(state);
    }

    public int setRightMiddleRowRLightRotaryState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightMiddleRowRLightRotaryState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightMiddleRowRLightRotaryState(state);
    }

    public int setLeftRearRLightSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLeftRearRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftRearRLightSwitchState(state);
    }

    public int setRightRearRLightSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRightRearRLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightRearRLightSwitchState(state);
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

    public int setCargoLightSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCargoLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setCargoLightSwitchState(state);
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

    public int setPositionLightDisplay(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setPositionLightDisplay mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setPositionLightDisplay(state);
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

    public int setHighlightPosition(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setHighlightPosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setHighlightPosition(state);
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

    public int setHeadlightControlMode(int mode) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setHeadlightControlMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setHeadlightControlMode(mode);
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

    public int setRearFogLightSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRearFogLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRearFogLightSwitchState(state);
    }

    public int setAllWeatherLightSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAllWeatherLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAllWeatherLightSwitchState(state);
    }

    public int setFrontFogLightSwitchState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setFrontFogLightSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setFrontFogLightSwitchState(state);
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

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 1156) {
                        bydAutoEventValue.intValue = getIalBrightness(2);
                        sparseArray.put(1156, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 1157) {
                        bydAutoEventValue.intValue = getIalColor(2);
                        sparseArray.put(1157, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2807) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                        sparseArray.put(2807, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2808) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(0);
                        sparseArray.put(2808, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2832) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                        sparseArray.put(2832, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2833) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                        sparseArray.put(2833, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 4663) {
                        bydAutoEventValue.intValue = getCargoLightSwitchState();
                        sparseArray.put(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 4664) {
                        bydAutoEventValue.intValue = getCargoLightSwitchConfig();
                        sparseArray.put(BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 4675) {
                        bydAutoEventValue.intValue = getHightlightPosition();
                        sparseArray.put(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 4676) {
                        switch (eventType) {
                            case 890:
                                bydAutoEventValue.intValue = getAFSSwitch();
                                sparseArray.put(890, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 902:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientFeedBack();
                                sparseArray.put(902, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1171:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLastSetedArea();
                                sparseArray.put(1171, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 1255:
                                bydAutoEventValue.intValue = getAmbientMusicModeState(1);
                                sparseArray.put(1255, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2006:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                                sparseArray.put(2006, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2797:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getReversingLightState();
                                sparseArray.put(2797, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2803:
                                bydAutoEventValue.intValue = getDayLightSupport();
                                sparseArray.put(2803, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2805:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorMode(0);
                                sparseArray.put(2805, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2811:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getAmbientMulticolorState(1);
                                sparseArray.put(2811, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2814:
                                bydAutoEventValue.intValue = getLightStatus(6);
                                sparseArray.put(2814, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2820:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                                sparseArray.put(2820, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2823:
                                int leftLampState = getLeftDaytimeRunLampState();
                                if (leftLampState == 1 || leftLampState == 2) {
                                    bydAutoEventValue.intValue = leftLampState;
                                } else {
                                    bydAutoEventValue.intValue = getLeftDaytimeLightState();
                                }
                                sparseArray.put(2823, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2827:
                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                                sparseArray.put(2827, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2829:
                                bydAutoEventValue.intValue = getAmbientMusicMode(1);
                                sparseArray.put(2829, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2836:
                                int rightLampState = getRightDaytimeRunLampState();
                                if (rightLampState == 1 || rightLampState == 2) {
                                    bydAutoEventValue.intValue = rightLampState;
                                } else {
                                    bydAutoEventValue.intValue = getRightDaytimeLightState();
                                }
                                sparseArray.put(2836, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 2842:
                                bydAutoEventValue.intValue = getLightStatus(1);
                                sparseArray.put(2842, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 4537:
                                bydAutoEventValue.intValue = getAmbientColorsSupport();
                                sparseArray.put(4537, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 4616:
                                bydAutoEventValue.intValue = getBacklightLevels();
                                sparseArray.put(4616, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH /* 4690 */:
                                bydAutoEventValue.intValue = getLeftFrontReadingLampStatus();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH /* 4691 */:
                                bydAutoEventValue.intValue = getRightFrontReadingLampStatus();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH /* 4692 */:
                                bydAutoEventValue.intValue = getLeftMiddleRowSideRLightSwitch();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH /* 4693 */:
                                bydAutoEventValue.intValue = getRightMiddleRowRLightSwitch();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH /* 4694 */:
                                bydAutoEventValue.intValue = getLeftMiddleRowRLightRotaryState();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH /* 4695 */:
                                bydAutoEventValue.intValue = getRightMiddleRowRLightRotary();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH /* 4696 */:
                                bydAutoEventValue.intValue = getLeftRearRLightSwitch();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH /* 4697 */:
                                bydAutoEventValue.intValue = getRightRearRLightSwitch();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_INTELLIGENT_POSITION_LIGHT_SCHEME_FEEDBACK /* 4698 */:
                                bydAutoEventValue.intValue = getIntelligentPostionFeedback();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_INTELLIGENT_POSITION_LIGHT_SCHEME_FEEDBACK, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_REAR_GLOW_LOGO_WORK_STATE /* 4743 */:
                                bydAutoEventValue.intValue = getLightRearGlowLogoWorkState();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_REAR_GLOW_LOGO_WORK_STATE, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHTS_ADAS_INDICATOR_LIGHT_STATUS /* 4744 */:
                                bydAutoEventValue.intValue = getLightAdasIndicatorStatus();
                                sparseArray.put(BYDAutoFeatureIds.LIGHTS_ADAS_INDICATOR_LIGHT_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_FRONT_GLOW_LOGO_WORK_STATE /* 4745 */:
                                bydAutoEventValue.intValue = getLightFrontGlowLogoWorkState();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_FRONT_GLOW_LOGO_WORK_STATE, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_MAIN_SWITCH /* 4747 */:
                                bydAutoEventValue.intValue = getAmbientState();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_MAIN_SWITCH, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_MASTER_SWITCH_FLAG /* 4748 */:
                                bydAutoEventValue.intValue = getAmbientSwitchConfig();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_MASTER_SWITCH_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_AMBIENT_LIGHT_VEHICLE_DISPLAY_FEEDBACK /* 4749 */:
                                bydAutoEventValue.intValue = getAtmosphereLampBodyDisplay();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_AMBIENT_LIGHT_VEHICLE_DISPLAY_FEEDBACK, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE_CONFIG /* 4750 */:
                                bydAutoEventValue.intValue = getAtmospereLightNightWeakenModeConfig();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE /* 4751 */:
                                bydAutoEventValue.intValue = getAtmospereLightNightWeakenMode();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_BRIGHTNESS_REDUCE_POPUP_PROMPT /* 4753 */:
                                bydAutoEventValue.intValue = getAtmospereLampPopupPrompt();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_BRIGHTNESS_REDUCE_POPUP_PROMPT, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_THEME_COLOR_SWITCH_STATE /* 4754 */:
                                bydAutoEventValue.intValue = getThemeColorSwitchState();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_THEME_COLOR_SWITCH_STATE, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_UI_DISPLAY /* 4756 */:
                                bydAutoEventValue.intValue = getAtmospereLampUiDisplay();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LAMP_UI_DISPLAY, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.LIGHT_THEME_COLOR_FEATURE_FLAG /* 4757 */:
                                bydAutoEventValue.intValue = getThemeColorFeatureFlag();
                                sparseArray.put(BYDAutoFeatureIds.LIGHT_THEME_COLOR_FEATURE_FLAG, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case BYDAutoFeatureIds.SETTING_THEME_COLOR_FEEDBACK /* 4758 */:
                                bydAutoEventValue.intValue = getThemeColorFeedBack();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_THEME_COLOR_FEEDBACK, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            default:
                                switch (eventType) {
                                    case 1160:
                                        bydAutoEventValue.intValue = getIalBrightness(1);
                                        sparseArray.put(1160, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    case 1161:
                                        bydAutoEventValue.intValue = getIalColor(1);
                                        sparseArray.put(1161, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    case 1162:
                                        bydAutoEventValue.intValue = getDoorLightState();
                                        sparseArray.put(1162, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    case 1163:
                                        bydAutoEventValue.intValue = getLightAutoStatus();
                                        sparseArray.put(1163, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    default:
                                        switch (eventType) {
                                            case 2799:
                                                bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getStopLightState();
                                                sparseArray.put(2799, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case 2800:
                                                int dayTimeLightState = getDayTimeLightState();
                                                if (dayTimeLightState >= 1 && dayTimeLightState <= 2) {
                                                    bydAutoEventValue.intValue = dayTimeLightState;
                                                    sparseArray.put(2800, Integer.valueOf(bydAutoEventValue.intValue));
                                                } else {
                                                    int dayTimeRunningLightState = getDayTimeRunningLightState();
                                                    if (dayTimeRunningLightState >= 1 && dayTimeRunningLightState <= 2) {
                                                        bydAutoEventValue.intValue = dayTimeRunningLightState;
                                                        sparseArray.put(2800, Integer.valueOf(bydAutoEventValue.intValue));
                                                    } else {
                                                        bydAutoEventValue.intValue = getDayTimeRunningLightState();
                                                        sparseArray.put(2800, Integer.valueOf(bydAutoEventValue.intValue));
                                                    }
                                                }
                                                break;
                                            case 2801:
                                                bydAutoEventValue.intValue = getDayTimeLightState();
                                                sparseArray.put(2801, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            default:
                                                switch (eventType) {
                                                    case 2844:
                                                        bydAutoEventValue.intValue = getIalBrightness(5);
                                                        sparseArray.put(2844, Integer.valueOf(bydAutoEventValue.intValue));
                                                        break;
                                                    case 2845:
                                                        bydAutoEventValue.intValue = getAmbRingColor();
                                                        sparseArray.put(2845, Integer.valueOf(bydAutoEventValue.intValue));
                                                        break;
                                                    case 2846:
                                                        bydAutoEventValue.intValue = getIalBrightness(4);
                                                        sparseArray.put(2846, Integer.valueOf(bydAutoEventValue.intValue));
                                                        break;
                                                    case 2847:
                                                        bydAutoEventValue.intValue = getIalColor(4);
                                                        sparseArray.put(2847, Integer.valueOf(bydAutoEventValue.intValue));
                                                        break;
                                                    case 2848:
                                                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getTurnLightState();
                                                        sparseArray.put(2848, Integer.valueOf(bydAutoEventValue.intValue));
                                                        break;
                                                    default:
                                                        switch (eventType) {
                                                            case BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS /* 4666 */:
                                                                bydAutoEventValue.intValue = getLeftCornerLightStatus();
                                                                sparseArray.put(BYDAutoFeatureIds.LIGHT_LEFT_CORNER_LIGHT_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                                                break;
                                                            case BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS /* 4667 */:
                                                                bydAutoEventValue.intValue = getRightCornerLightStatus();
                                                                sparseArray.put(BYDAutoFeatureIds.LIGHT_RIGHT_CORNER_LIGHT_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                                                break;
                                                            case BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS /* 4668 */:
                                                                bydAutoEventValue.intValue = getFrontFogLampStatus();
                                                                sparseArray.put(BYDAutoFeatureIds.LIGHT_FRONT_FOG_LAMP_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                                                break;
                                                            case BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS /* 4669 */:
                                                                bydAutoEventValue.intValue = getFrontCenterLightStatus();
                                                                sparseArray.put(BYDAutoFeatureIds.LIGHT_FRONT_CENTER_POSITION_LIGHTS_STATUS, Integer.valueOf(bydAutoEventValue.intValue));
                                                                break;
                                                            case BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK /* 4670 */:
                                                                bydAutoEventValue.intValue = getPositionLightDisplayFeedCallback();
                                                                sparseArray.put(BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_FEEDBACK, Integer.valueOf(bydAutoEventValue.intValue));
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
                        bydAutoEventValue.intValue = getHightlightPositionConfig();
                        sparseArray.put(BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
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
        if (eventType == 1156) {
            setIalBrightness(2, intValue, 2);
            Log.v(TAG, "setIALBrightnessBack:" + intValue);
        } else if (eventType == 1157) {
            setIalColor(2, intValue, 1);
            Log.v(TAG, "setIALColorFrontBack:" + intValue);
        } else if (eventType == 1160) {
            setIalBrightness(1, intValue, 1);
            Log.v(TAG, "setIALBrightnessFront:" + intValue);
        } else if (eventType == 1161) {
            setIalColor(1, intValue, 1);
            Log.v(TAG, "setIALColorFront:" + intValue);
        } else if (eventType == 2807) {
            setAmbientMulticolorMode(0, intValue);
            Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE + intValue);
        } else if (eventType == 2808) {
            setAmbientMulticolorState(1, intValue);
            Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE + intValue);
        } else {
            switch (eventType) {
                case 1164:
                    setDoorLightState(intValue);
                    Log.v(TAG, "Setting.SET_INSIDE_LIGHT_DOOR_STATE_SET:");
                    break;
                case 1257:
                    setAmbientMusicModeState(0, intValue);
                    Log.v(TAG, "SET_MUSIC_MODE_STATE_SET: " + intValue);
                    break;
                case 2802:
                    setDayTimeLightState(intValue);
                    Log.v(TAG, TestLightManagerImplCallback.SETDAYTIMELIGHTSTATE + intValue);
                    break;
                case 2805:
                    setAmbientMulticolorMode(1, intValue);
                    Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORMODE + intValue);
                    break;
                case 2811:
                    setAmbientMulticolorState(0, intValue);
                    Log.v(TAG, TestLightManagerImplCallback.SETAMBIENTMULTICOLORSTATE + intValue);
                    break;
                case 2831:
                    setAmbientMusicMode(0, intValue);
                    Log.v(TAG, "Light.LIGHT_MUSIC_RHYTHM_MODE_SET:");
                    break;
                case BYDAutoFeatureIds.LIGHT_CARGO_LIGHT_SWITCH_SET /* 4665 */:
                    setCargoLightSwitchState(intValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_POSITION_LIGHT_DISPLAY_SET /* 4671 */:
                    setPositionLightDisplay(intValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_HIGHLIGHT_POSITION_LIGHT_SET /* 4674 */:
                    setHighlightPosition(intValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_MAIN_SWITCH_SET /* 4746 */:
                    setAmbientState(intValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_ATMOSPHERE_LIGHT_NIGHT_WEAKEN_MODE_SET /* 4752 */:
                    setAtmospereLightNightWeakenMode(intValue);
                    break;
                case BYDAutoFeatureIds.SETTING_THEME_LINKED_AMBIENCE_LIGHT_COLOR_SET /* 4755 */:
                    setThemeLinkedAmbienceLightColor(intValue);
                    break;
                case BYDAutoFeatureIds.LIGHT_THEME_LINKED_AATMOSPHERE_LAMP_SWITCH_SET /* 4759 */:
                    setThemeLinkedAtmosphereLampSwitch(intValue);
                    break;
                default:
                    switch (eventType) {
                        case 2844:
                            setIalBrightness(5, intValue, 1);
                            Log.v(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP_BRIGHTNESS:" + intValue);
                            break;
                        case 2845:
                            setIalColor(5, intValue, 1);
                            Log.v(TAG, "LIGHT_STAR_RING_ATMOSPHERE_LAMP:" + intValue);
                            break;
                        case 2846:
                            setIalBrightness(intValue, 4, 1);
                            Log.v(TAG, "Light.LIGHT_STAR_SKY_ATMOSPHERE_LAMP_BRIGHTNESS:" + intValue);
                            break;
                        case 2847:
                            setIalColor(4, intValue, 1);
                            Log.v(TAG, "LIGHT_STAR_SKY_ATMOSPHERE_LAMP:" + intValue);
                            break;
                        default:
                            switch (eventType) {
                                case BYDAutoFeatureIds.LIGHT_LF_READ_LIGHT_SWITCH_SET /* 4655 */:
                                    setLeftFrontReadingLampSwitchState(intValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_RF_READ_LIGHT_SWITCH_SET /* 4656 */:
                                    setRightFrontReadingLampSwitchState(intValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_SWITCH_SET /* 4657 */:
                                    setLeftMiddleRowSideRLightSwitchState(intValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_SWITCH_SET /* 4658 */:
                                    setRightMiddleRowRLightSwitchState(intValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_LM_READ_LIGHT_ROTATE_SWITCH_SET /* 4659 */:
                                    setLeftMiddleRowRLightRotaryState(intValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_RM_READ_LIGHT_ROTATE_SWITCH_SET /* 4660 */:
                                    setRightMiddleRowRLightRotaryState(intValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_LR_READ_LIGHT_SWITCH_SET /* 4661 */:
                                    setLeftRearRLightSwitchState(intValue);
                                    break;
                                case BYDAutoFeatureIds.LIGHT_RR_READ_LIGHT_SWITCH_SET /* 4662 */:
                                    setRightRearRLightSwitchState(intValue);
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
