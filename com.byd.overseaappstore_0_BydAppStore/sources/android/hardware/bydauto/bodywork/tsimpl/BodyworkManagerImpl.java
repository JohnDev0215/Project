package android.hardware.bydauto.bodywork.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto_test.TestBodyworkManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import com.ts.lib.engineeringmode.EngineeringSdkManager;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class BodyworkManagerImpl extends AbsTsMyCar {
    private static final int C_MALFUNCTION_1 = 1;
    private static final int C_MALFUNCTION_2 = 2;
    private static final int C_MALFUNCTION_3 = 3;
    private static final int C_MALFUNCTION_4 = 4;
    private static final int C_MALFUNCTION_5 = 5;
    private static final int C_MALFUNCTION_6 = 6;
    private static final int C_MALFUNCTION_7 = 7;
    private static final int C_MALFUNCTION_8 = 8;
    private static final String DEFAULT_VIN = "tes0000000000000000";
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_BodyworkManagerImpl";
    private static final int S_NUMBER_FOUR = 4;
    private static final int S_NUMBER_THREE = 3;
    private static final String TAG = "BodyworkManagerImpl";
    private static final int WINDOW_CONTROL_STATE_LEFT_FRONT_S = 1;
    private static final int WINDOW_CONTROL_STATE_LEFT_REAR_S = 3;
    private static final int WINDOW_CONTROL_STATE_RIGHT_FRONT_S = 2;
    private static final int WINDOW_CONTROL_STATE_RIGHT_REAR_S = 4;
    private static volatile BodyworkManagerImpl sInstance;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private OtaSdkManager mOtaSdkManager;
    private EngineeringSdkManager mSdkManager;
    private TestBodyworkManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoBodyworkListener> mAbsBydAutoBodyworkListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    public BodyworkManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                StringBuilder sbK = e.k("BodyworkManagerImpl mForTestValue is");
                sbK.append(this.mForTest);
                sbK.append(" execute return");
                Log.v(TAG, sbK.toString());
                this.mTestCallback = new TestBodyworkManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.bodywork.tsimpl.BodyworkManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                Log.v(BodyworkManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                try {
                    BodyworkManagerImpl.this.bodyworkCallback(i2, i3, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(BodyworkManagerImpl.TAG, "bodyworkCallback is error");
                }
            }
        });
        this.mSdkManager = EngineeringSdkManager.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bodyworkCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        int i3;
        int recoverOrSaveParamsChanged5;
        int windowInitStateFL;
        Integer numValueOf;
        int i4;
        int i5;
        float steeringWheelY;
        StringBuilder sb;
        int i6 = 2009;
        int i7 = -1;
        switch (i) {
            case 557845265:
                Iterator<AbsBYDAutoBodyworkListener> it = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it.hasNext()) {
                    it.next().onFuelElecLowPowerChanged(carBodyPacket.getFuelElecLowPowerR());
                }
                break;
            case 557857850:
                Iterator<AbsBYDAutoBodyworkListener> it2 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onMainSaveOrRecoverStateChanged(2, carBodyPacket.getRecoverOrSaveParamsChanged5());
                }
                i3 = 4614;
                recoverOrSaveParamsChanged5 = carBodyPacket.getRecoverOrSaveParamsChanged5();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case 557860644:
                int lightRearPosition = carBodyPacket.getLightRearPosition();
                Iterator<AbsBYDAutoBodyworkListener> it3 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onRearLampLightStateChanged(lightRearPosition);
                }
                i3 = 2006;
                recoverOrSaveParamsChanged5 = carBodyPacket.getLightRearPosition();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_LEFT_FRONT_R /* 557862936 */:
                Iterator<AbsBYDAutoBodyworkListener> it4 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onWindowOpenPercentChanged(1, carBodyPacket.getmWindowOpenPercentLeftFrontR());
                }
                windowInitStateFL = carBodyPacket.getmWindowOpenPercentLeftFrontR();
                i6 = 2090;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_LEFT_REAR_R /* 557862937 */:
                Iterator<AbsBYDAutoBodyworkListener> it5 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onWindowOpenPercentChanged(3, carBodyPacket.getmWindowOpenPercentLeftRearR());
                }
                windowInitStateFL = carBodyPacket.getmWindowOpenPercentLeftRearR();
                i6 = 2091;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_RIGHT_FRONT_R /* 557862938 */:
                Iterator<AbsBYDAutoBodyworkListener> it6 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onWindowOpenPercentChanged(2, carBodyPacket.getmWindowOpenPercentRightFrontR());
                }
                windowInitStateFL = carBodyPacket.getmWindowOpenPercentRightFrontR();
                i6 = 2092;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_RIGHT_REAR_R /* 557862939 */:
                Iterator<AbsBYDAutoBodyworkListener> it7 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onWindowOpenPercentChanged(4, carBodyPacket.getmWindowOpenPercentRightRearR());
                }
                windowInitStateFL = carBodyPacket.getmWindowOpenPercentRightRearR();
                i6 = 2093;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_MOON_R /* 557862940 */:
                Iterator<AbsBYDAutoBodyworkListener> it8 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onWindowOpenPercentChanged(5, carBodyPacket.getmWindowOpenPercentMoonR());
                }
                windowInitStateFL = carBodyPacket.getmWindowOpenPercentMoonR();
                i6 = 1789;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_SUN_R /* 557862941 */:
                Iterator<AbsBYDAutoBodyworkListener> it9 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onWindowOpenPercentChanged(6, carBodyPacket.getmWindowOpenPercentSunR());
                }
                windowInitStateFL = carBodyPacket.getmWindowOpenPercentSunR();
                i6 = 2084;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_MOON_ROOF_CONFIG /* 557862942 */:
                Iterator<AbsBYDAutoBodyworkListener> it10 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onMoonRoofConfigChanged(carBodyPacket.getmMoonRoofConfig());
                }
                i3 = 1786;
                recoverOrSaveParamsChanged5 = carBodyPacket.getmMoonRoofConfig();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case 557862943:
                int i8 = carBodyPacket.getmDoorStateLeftFront();
                if (i8 == 1) {
                    i7 = 0;
                } else if (i8 != 0) {
                    i7 = i8 == 2 ? 1 : i8;
                }
                Iterator<AbsBYDAutoBodyworkListener> it11 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onDoorStateChanged(1, i7);
                }
                Log.i(TAG, "onDoorStateChanged: id " + i + ", doorStateLeftFront " + i7);
                i3 = 1749;
                numValueOf = Integer.valueOf(i7);
                caseFeatureIds(i3, numValueOf);
                break;
            case 557862944:
                int i9 = carBodyPacket.getmDoorStateRightFront();
                if (i9 == 1) {
                    i7 = 0;
                } else if (i9 != 0) {
                    i7 = i9 == 2 ? 1 : i9;
                }
                Iterator<AbsBYDAutoBodyworkListener> it12 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onDoorStateChanged(2, i7);
                }
                Log.i(TAG, "onDoorStateChanged: id " + i + ", doorStateRightFront " + i7);
                i3 = 2040;
                numValueOf = Integer.valueOf(i7);
                caseFeatureIds(i3, numValueOf);
                break;
            case 557862945:
                int i10 = carBodyPacket.getmDoorStateLeftRear();
                if (i10 == 1) {
                    i7 = 0;
                } else if (i10 != 0) {
                    i7 = i10 == 2 ? 1 : i10;
                }
                Iterator<AbsBYDAutoBodyworkListener> it13 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onDoorStateChanged(3, i7);
                }
                Log.i(TAG, "onDoorStateChanged: id " + i + ", doorStateLeftRear " + i7);
                i3 = 1750;
                numValueOf = Integer.valueOf(i7);
                caseFeatureIds(i3, numValueOf);
                break;
            case 557862946:
                int i11 = carBodyPacket.getmDoorStateRightRear();
                if (i11 == 1) {
                    i7 = 0;
                } else if (i11 != 0) {
                    i7 = i11 == 2 ? 1 : i11;
                }
                Iterator<AbsBYDAutoBodyworkListener> it14 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onDoorStateChanged(4, i7);
                }
                Log.i(TAG, "onDoorStateChanged: id " + i + ", doorStateRightRear " + i7);
                i3 = 2041;
                numValueOf = Integer.valueOf(i7);
                caseFeatureIds(i3, numValueOf);
                break;
            case 557862947:
                int i12 = carBodyPacket.getmDoorStateLuggage();
                if (i12 == 1) {
                    i7 = 0;
                } else if (i12 != 0) {
                    i7 = i12 == 2 ? 1 : i12;
                }
                Iterator<AbsBYDAutoBodyworkListener> it15 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onDoorStateChanged(6, i7);
                }
                i3 = 1782;
                numValueOf = Integer.valueOf(i7);
                caseFeatureIds(i3, numValueOf);
                break;
            case 557862948:
                int i13 = carBodyPacket.getmDoorStateHood();
                if (i13 == 1) {
                    i7 = 0;
                } else if (i13 != 0) {
                    i7 = i13 == 2 ? 1 : i13;
                }
                Iterator<AbsBYDAutoBodyworkListener> it16 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onDoorStateChanged(5, i7);
                }
                i3 = 1742;
                numValueOf = Integer.valueOf(i7);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_STATE_MOON_ROOFR /* 557862949 */:
                Iterator<AbsBYDAutoBodyworkListener> it17 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onWindowStateChanged(5, carBodyPacket.getmWindowStateMoonRoofR());
                }
                break;
            case BodyworkHalId.ID_WINDOW_STATE_SUNSHADER /* 557862950 */:
                Iterator<AbsBYDAutoBodyworkListener> it18 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onWindowblindStateChanged(carBodyPacket.getmWindowStateSunshadeR());
                }
                i3 = 2082;
                recoverOrSaveParamsChanged5 = carBodyPacket.getmWindowStateSunshadeR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_ENERGY_TYPER /* 557862951 */:
                Iterator<AbsBYDAutoBodyworkListener> it19 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onEnergyTypeChanged(carBodyPacket.getmEnergyTypeR());
                }
                break;
            case BodyworkHalId.ID_SUN_ROOF_STATER /* 557862952 */:
                Iterator<AbsBYDAutoBodyworkListener> it20 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onSunroofStateChanged(carBodyPacket.getmSunRoofStateR());
                }
                windowInitStateFL = carBodyPacket.getmSunRoofStateR();
                i6 = 2079;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case 557862953:
                Iterator<AbsBYDAutoBodyworkListener> it21 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onLeftStrutMorotRunningStateChanged(carBodyPacket.getLeftStrutMorotRunningState());
                }
                i3 = 1759;
                recoverOrSaveParamsChanged5 = carBodyPacket.getLeftStrutMorotRunningState();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_INIT_STATE_FL /* 557862954 */:
                Iterator<AbsBYDAutoBodyworkListener> it22 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onWindowInitStateChanged(1, carBodyPacket.getWindowInitStateFL());
                }
                windowInitStateFL = carBodyPacket.getWindowInitStateFL();
                i6 = 1765;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_INIT_STATE_RL /* 557862955 */:
                Iterator<AbsBYDAutoBodyworkListener> it23 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onWindowInitStateChanged(3, carBodyPacket.getWindowInitStateRL());
                }
                windowInitStateFL = carBodyPacket.getWindowInitStateRL();
                i6 = 2036;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_INIT_STATE_FR /* 557862956 */:
                Iterator<AbsBYDAutoBodyworkListener> it24 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onWindowInitStateChanged(2, carBodyPacket.getWindowInitStateFR());
                }
                windowInitStateFL = carBodyPacket.getWindowInitStateFR();
                i6 = 1780;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_INIT_STATE_RR /* 557862957 */:
                Iterator<AbsBYDAutoBodyworkListener> it25 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onWindowInitStateChanged(4, carBodyPacket.getWindowInitStateRR());
                }
                windowInitStateFL = carBodyPacket.getWindowInitStateRR();
                i6 = 2062;
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_RL /* 557862958 */:
                Iterator<AbsBYDAutoBodyworkListener> it26 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onWindowThermalProtectionStateChanged(1, carBodyPacket.getWindowThermalProtectionStateFl());
                }
                i3 = 1748;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowThermalProtectionStateFl();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FL /* 557862959 */:
                Iterator<AbsBYDAutoBodyworkListener> it27 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onWindowThermalProtectionStateChanged(3, carBodyPacket.getWindowThermalProtectionStateRl());
                }
                i3 = 1752;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowThermalProtectionStateRl();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FR /* 557862960 */:
                Iterator<AbsBYDAutoBodyworkListener> it28 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onWindowThermalProtectionStateChanged(2, carBodyPacket.getWindowThermalProtectionStateFr());
                }
                i3 = 2039;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowThermalProtectionStateFr();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_RR /* 557862961 */:
                Iterator<AbsBYDAutoBodyworkListener> it29 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onWindowThermalProtectionStateChanged(4, carBodyPacket.getWindowThermalProtectionStateRr());
                }
                i3 = 2043;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowThermalProtectionStateRr();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_SKYLIGHT /* 557862962 */:
                Iterator<AbsBYDAutoBodyworkListener> it30 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onWindowThermalProtectionStateChanged(5, carBodyPacket.getWindowThermalProtectionStateSkylight());
                }
                i3 = 2068;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowThermalProtectionStateSkylight();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FRONT_SUNSHADE /* 557862963 */:
                Iterator<AbsBYDAutoBodyworkListener> it31 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onWindowThermalProtectionStateChanged(6, carBodyPacket.getWindowThermalProtectionStateFrontSunshade());
                }
                i3 = 1735;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowThermalProtectionStateFrontSunshade();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_REAR_SUNSHADE /* 557862964 */:
                Iterator<AbsBYDAutoBodyworkListener> it32 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onWindowThermalProtectionStateChanged(7, carBodyPacket.getWindowThermalProtectionStateRearSunshade());
                }
                i3 = 2014;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowThermalProtectionStateRearSunshade();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_PERMIT_STATER /* 557862965 */:
                Iterator<AbsBYDAutoBodyworkListener> it33 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onWindowPermitStateChanged(carBodyPacket.getWindowPermitState());
                }
                i3 = 1747;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowPermitState();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOWBLIND_INIT_STATER /* 557862966 */:
                Iterator<AbsBYDAutoBodyworkListener> it34 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onWindoblindInitStateChanged(carBodyPacket.getWindowblindInitStateR());
                }
                i3 = 2080;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowblindInitStateR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_SUNROOF_INIT_STATER /* 557862967 */:
                Iterator<AbsBYDAutoBodyworkListener> it35 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onSunroofInitStateChanged(carBodyPacket.getSunroofInitStateR());
                }
                i3 = 2077;
                recoverOrSaveParamsChanged5 = carBodyPacket.getSunroofInitStateR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_SUNROOF_CLOSE_NOTICER /* 557862968 */:
                Iterator<AbsBYDAutoBodyworkListener> it36 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onSunroofCloseNoticeChanged(carBodyPacket.getSunroofCloseNoticeR());
                }
                i3 = 2076;
                recoverOrSaveParamsChanged5 = carBodyPacket.getSunroofCloseNoticeR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_SUNROOF_WINDOWBLIND_POSITIONR /* 557862969 */:
                Iterator<AbsBYDAutoBodyworkListener> it37 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onSunroofWindowblindPositionChanged(carBodyPacket.getSunroofWindowblindPositionR());
                }
                i3 = 2081;
                recoverOrSaveParamsChanged5 = carBodyPacket.getSunroofWindowblindPositionR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_SUNROOF_POSITIONR /* 557862970 */:
                Iterator<AbsBYDAutoBodyworkListener> it38 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onSunroofPositionChanged(carBodyPacket.getSunroofPositionR());
                }
                i3 = 2078;
                recoverOrSaveParamsChanged5 = carBodyPacket.getSunroofPositionR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_REAR_PANELR /* 557862991 */:
                Iterator<AbsBYDAutoBodyworkListener> it39 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onWindowOpenPercentChanged(7, carBodyPacket.getWindowOpenPercentRearPanel());
                }
                i3 = 2012;
                recoverOrSaveParamsChanged5 = carBodyPacket.getWindowOpenPercentRearPanel();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_READY_INDICATOR_LIGHT_STATER /* 557863013 */:
                Iterator<AbsBYDAutoBodyworkListener> it40 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onReadyIndicatorLightStateChanged(carBodyPacket.getReadyIndicatorLightStateR());
                }
                break;
            case BodyworkHalId.ID_REAR_WINDOWBLIND_INIT_STATE_NEW_R /* 557863019 */:
                Iterator<AbsBYDAutoBodyworkListener> it41 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it41.hasNext()) {
                    it41.next().onRearWindowblindInitStateChanged(carBodyPacket.getRearWindowblindInitStateNewR());
                }
                windowInitStateFL = carBodyPacket.getRearWindowblindInitStateNewR();
                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_LEFT_LEFT_R /* 557863049 */:
                Iterator<AbsBYDAutoBodyworkListener> it42 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it42.hasNext()) {
                    it42.next().onScreenControlSlideDoorChanged(1, carBodyPacket.getScreenControlSlideDoorLeftLeftR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_RIGHT_LEFT_R /* 557863050 */:
                Iterator<AbsBYDAutoBodyworkListener> it43 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it43.hasNext()) {
                    it43.next().onScreenControlSlideDoorChanged(2, carBodyPacket.getScreenControlSlideDoorRightLeftR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_L_LEFT_RIGHT_R /* 557863051 */:
                Iterator<AbsBYDAutoBodyworkListener> it44 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it44.hasNext()) {
                    it44.next().onScreenControlSlideDoorChanged(3, carBodyPacket.getScreenControlSlideDoorLLeftRightR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_RIGHT_RIGHT_R /* 557863052 */:
                Iterator<AbsBYDAutoBodyworkListener> it45 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it45.hasNext()) {
                    it45.next().onScreenControlSlideDoorChanged(4, carBodyPacket.getScreenControlSlideDoorRightRightR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_LEFT_LEFT_R /* 557863053 */:
                Iterator<AbsBYDAutoBodyworkListener> it46 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it46.hasNext()) {
                    it46.next().onScreenControlSlideDoorChanged(5, carBodyPacket.getScreenControlSlideDoorSlidingLeftLeftR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_RIGHT_LEFT_R /* 557863054 */:
                Iterator<AbsBYDAutoBodyworkListener> it47 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it47.hasNext()) {
                    it47.next().onScreenControlSlideDoorChanged(6, carBodyPacket.getScreenControlSlideDoorSlidingRightLeftR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_LEFT_RIGHT_R /* 557863055 */:
                Iterator<AbsBYDAutoBodyworkListener> it48 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it48.hasNext()) {
                    it48.next().onScreenControlSlideDoorChanged(7, carBodyPacket.getScreenControlSlideDoorSlidingLeftRightR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_RIGHT_RIGHT_R /* 557863056 */:
                Iterator<AbsBYDAutoBodyworkListener> it49 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it49.hasNext()) {
                    it49.next().onScreenControlSlideDoorChanged(8, carBodyPacket.getScreenControlSlideDoorSlidingRightRightR());
                }
                break;
            case BodyworkHalId.ID_EV_READY_INDICATOR_LIGHT_STATE_R /* 557863057 */:
                Iterator<AbsBYDAutoBodyworkListener> it50 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it50.hasNext()) {
                    it50.next().onEvReadyIndicatorLightStateChanged(carBodyPacket.getEvReadyIndicatorLightStateR());
                }
                break;
            case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_L_F_R /* 557863061 */:
                Iterator<AbsBYDAutoBodyworkListener> it51 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it51.hasNext()) {
                    it51.next().onInternalDoorHandleStateByRearBCMChanged(1, carBodyPacket.getInternalDoorHandleStateByRearBCMLFR());
                }
                i3 = BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only;
                recoverOrSaveParamsChanged5 = carBodyPacket.getInternalDoorHandleStateByRearBCMLFR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_L_R_R /* 557863062 */:
                Iterator<AbsBYDAutoBodyworkListener> it52 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it52.hasNext()) {
                    it52.next().onInternalDoorHandleStateByRearBCMChanged(3, carBodyPacket.getInternalDoorHandleStateByRearBCMLRR());
                }
                i3 = BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only;
                recoverOrSaveParamsChanged5 = carBodyPacket.getInternalDoorHandleStateByRearBCMLRR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_R_F_R /* 557863063 */:
                Iterator<AbsBYDAutoBodyworkListener> it53 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it53.hasNext()) {
                    it53.next().onInternalDoorHandleStateByRearBCMChanged(2, carBodyPacket.getInternalDoorHandleStateByRearBCMRFR());
                }
                i3 = BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only;
                recoverOrSaveParamsChanged5 = carBodyPacket.getInternalDoorHandleStateByRearBCMRFR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_R_R_R /* 557863064 */:
                Iterator<AbsBYDAutoBodyworkListener> it54 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it54.hasNext()) {
                    it54.next().onInternalDoorHandleStateByRearBCMChanged(4, carBodyPacket.getInternalDoorHandleStateByRearBCMRRR());
                }
                i3 = BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only;
                recoverOrSaveParamsChanged5 = carBodyPacket.getInternalDoorHandleStateByRearBCMRRR();
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case BodyworkHalId.ID_SHIFT_MODE_R /* 557868039 */:
                Iterator<AbsBYDAutoBodyworkListener> it55 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it55.hasNext()) {
                    it55.next().onShiftModeChanged(carBodyPacket.getShiftModeR());
                }
                break;
            case 557875227:
                Iterator<AbsBYDAutoBodyworkListener> it56 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it56.hasNext()) {
                    it56.next().onBatteryVoltageLevelLowTips(carBodyPacket.getBatteryVoltageLevelLowTips());
                }
                break;
            case BodyworkHalId.ID_CAN_055_STATE /* 557945600 */:
                i4 = carBodyPacket.getCan055R()[0];
                i5 = carBodyPacket.getCan055R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_0D5_STATE /* 557945603 */:
                i4 = carBodyPacket.getCan0d5()[0];
                i5 = carBodyPacket.getCan0d5()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_10D_STATE /* 557945605 */:
                i4 = carBodyPacket.getCan10d()[0];
                i5 = carBodyPacket.getCan10d()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_121_STATE /* 557945606 */:
                i4 = carBodyPacket.getCan121()[0];
                i5 = carBodyPacket.getCan121()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_123_STATE /* 557945607 */:
                i4 = carBodyPacket.getCan123()[0];
                i5 = carBodyPacket.getCan123()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_12D_STATE /* 557945608 */:
                i4 = carBodyPacket.getCan12d()[0];
                i5 = carBodyPacket.getCan12d()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_12F_STATE /* 557945609 */:
                i4 = carBodyPacket.getCan12f()[0];
                i5 = carBodyPacket.getCan12f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_133_STATE /* 557945610 */:
                i4 = carBodyPacket.getCan133()[0];
                i5 = carBodyPacket.getCan133()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_138_STATE /* 557945611 */:
                i4 = carBodyPacket.getCan138()[0];
                i5 = carBodyPacket.getCan138()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_13F_STATE /* 557945612 */:
                i4 = carBodyPacket.getCan13f()[0];
                i5 = carBodyPacket.getCan13f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_144_STATE /* 557945613 */:
                i4 = carBodyPacket.getCan144()[0];
                i5 = carBodyPacket.getCan144()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_151_STATE /* 557945614 */:
                i4 = carBodyPacket.getCan151()[0];
                i5 = carBodyPacket.getCan151()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_167_STATE /* 557945615 */:
                i4 = carBodyPacket.getCan167()[0];
                i5 = carBodyPacket.getCan167()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_18A_STATE /* 557945616 */:
                i4 = carBodyPacket.getCan18a()[0];
                i5 = carBodyPacket.getCan18a()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_19F_STATE /* 557945617 */:
                i4 = carBodyPacket.getCan19f()[0];
                i5 = carBodyPacket.getCan19f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_1C0_STATE /* 557945618 */:
                i4 = carBodyPacket.getCan1c0()[0];
                i5 = carBodyPacket.getCan1c0()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_1D1_STATE /* 557945619 */:
                i4 = carBodyPacket.getCan1d1()[0];
                i5 = carBodyPacket.getCan1d1()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_218_STATE /* 557945620 */:
                i4 = carBodyPacket.getCan218()[0];
                i5 = carBodyPacket.getCan218()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_223_STATE /* 557945621 */:
                i4 = carBodyPacket.getCan223()[0];
                i5 = carBodyPacket.getCan223()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_233_STATE /* 557945622 */:
                i4 = carBodyPacket.getCan233()[0];
                i5 = carBodyPacket.getCan233()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_235_STATE /* 557945623 */:
                i4 = carBodyPacket.getCan235()[0];
                i5 = carBodyPacket.getCan235()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_23A_STATE /* 557945624 */:
                i4 = carBodyPacket.getCan23a()[0];
                i5 = carBodyPacket.getCan23a()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_242_STATE /* 557945625 */:
                i4 = carBodyPacket.getCan242()[0];
                i5 = carBodyPacket.getCan242()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_245_STATE /* 557945626 */:
                i4 = carBodyPacket.getCan245()[0];
                i5 = carBodyPacket.getCan245()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_248_STATE /* 557945627 */:
                i4 = carBodyPacket.getCan248()[0];
                i5 = carBodyPacket.getCan248()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_24A_STATE /* 557945628 */:
                i4 = carBodyPacket.getCan24a()[0];
                i5 = carBodyPacket.getCan24a()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_24C_STATE /* 557945629 */:
                i4 = carBodyPacket.getCan24c()[0];
                i5 = carBodyPacket.getCan24c()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_25D_STATE /* 557945630 */:
                i4 = carBodyPacket.getCan25d()[0];
                i5 = carBodyPacket.getCan25d()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_261_STATE /* 557945631 */:
                i4 = carBodyPacket.getCan261()[0];
                i5 = carBodyPacket.getCan261()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_26F_STATE /* 557945632 */:
                i4 = carBodyPacket.getCan26f()[0];
                i5 = carBodyPacket.getCan26f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_271_STATE /* 557945633 */:
                i4 = carBodyPacket.getCan271()[0];
                i5 = carBodyPacket.getCan271()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_272_STATE /* 557945634 */:
                i4 = carBodyPacket.getCan272()[0];
                i5 = carBodyPacket.getCan272()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_275_STATE /* 557945635 */:
                i4 = carBodyPacket.getCan275()[0];
                i5 = carBodyPacket.getCan275()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_27A_STATE /* 557945636 */:
                i4 = carBodyPacket.getCan27a()[0];
                i5 = carBodyPacket.getCan27a()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_287_STATE /* 557945637 */:
                i4 = carBodyPacket.getCan287()[0];
                i5 = carBodyPacket.getCan287()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_288_STATE /* 557945638 */:
                i4 = carBodyPacket.getCan288()[0];
                i5 = carBodyPacket.getCan288()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_28F_STATE /* 557945639 */:
                i4 = carBodyPacket.getCan28f()[0];
                i5 = carBodyPacket.getCan28f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_294_STATE /* 557945640 */:
                i4 = carBodyPacket.getCan294()[0];
                i5 = carBodyPacket.getCan294()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_296_STATE /* 557945641 */:
                i4 = carBodyPacket.getCan296()[0];
                i5 = carBodyPacket.getCan296()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_29C_STATE /* 557945642 */:
                i4 = carBodyPacket.getCan29c()[0];
                i5 = carBodyPacket.getCan29c()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_29E_STATE /* 557945643 */:
                i4 = carBodyPacket.getCan29e()[0];
                i5 = carBodyPacket.getCan29e()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2A4_STATE /* 557945644 */:
                i4 = carBodyPacket.getCan2a4()[0];
                i5 = carBodyPacket.getCan2a4()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2A6_STATE /* 557945645 */:
                i4 = carBodyPacket.getCan2a6()[0];
                i5 = carBodyPacket.getCan2a6()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2A7_STATE /* 557945646 */:
                i4 = carBodyPacket.getCan2a7()[0];
                i5 = carBodyPacket.getCan2a7()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2B0_STATE /* 557945647 */:
                i4 = carBodyPacket.getCan2b0()[0];
                i5 = carBodyPacket.getCan2b0()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2B4_STATE /* 557945648 */:
                i4 = carBodyPacket.getCan2b4()[0];
                i5 = carBodyPacket.getCan2b4()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2B6_STATE /* 557945649 */:
                i4 = carBodyPacket.getCan2b6()[0];
                i5 = carBodyPacket.getCan2b6()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2BA_STATE /* 557945650 */:
                i4 = carBodyPacket.getCan2ba()[0];
                i5 = carBodyPacket.getCan2ba()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2BC_STATE /* 557945651 */:
                i4 = carBodyPacket.getCan2bc()[0];
                i5 = carBodyPacket.getCan2bc()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2C0_STATE /* 557945652 */:
                i4 = carBodyPacket.getCan2c0()[0];
                i5 = carBodyPacket.getCan2c0()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2D5_STATE /* 557945653 */:
                i4 = carBodyPacket.getCan2d5()[0];
                i5 = carBodyPacket.getCan2d5()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2EB_STATE /* 557945654 */:
                i4 = carBodyPacket.getCan2eb()[0];
                i5 = carBodyPacket.getCan2eb()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_2F0_STATE /* 557945655 */:
                i4 = carBodyPacket.getCan2f0()[0];
                i5 = carBodyPacket.getCan2f0()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_30D_STATE /* 557945656 */:
                i4 = carBodyPacket.getCan30d()[0];
                i5 = carBodyPacket.getCan30d()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_31E_STATE /* 557945657 */:
                i4 = carBodyPacket.getCan31e()[0];
                i5 = carBodyPacket.getCan31e()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_321_STATE /* 557945658 */:
                i4 = carBodyPacket.getCan321()[0];
                i5 = carBodyPacket.getCan321()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_323_STATE /* 557945659 */:
                i4 = carBodyPacket.getCan323()[0];
                i5 = carBodyPacket.getCan323()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_32D_STATE /* 557945660 */:
                i4 = carBodyPacket.getCan32d()[0];
                i5 = carBodyPacket.getCan32d()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_334_STATE /* 557945661 */:
                i4 = carBodyPacket.getCan334()[0];
                i5 = carBodyPacket.getCan334()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_337_STATE /* 557945662 */:
                i4 = carBodyPacket.getCan337()[0];
                i5 = carBodyPacket.getCan337()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_341_STATE /* 557945663 */:
                i4 = carBodyPacket.getCan341()[0];
                i5 = carBodyPacket.getCan341()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_342_STATE /* 557945664 */:
                i4 = carBodyPacket.getCan342()[0];
                i5 = carBodyPacket.getCan342()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_346_STATE /* 557945665 */:
                i4 = carBodyPacket.getCan346()[0];
                i5 = carBodyPacket.getCan346()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_347_STATE /* 557945666 */:
                i4 = carBodyPacket.getCan347()[0];
                i5 = carBodyPacket.getCan347()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_34A_STATE /* 557945667 */:
                i4 = carBodyPacket.getCan34a()[0];
                i5 = carBodyPacket.getCan34a()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_34F_STATE /* 557945668 */:
                i4 = carBodyPacket.getCan34f()[0];
                i5 = carBodyPacket.getCan34f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_36B_STATE /* 557945669 */:
                i4 = carBodyPacket.getCan36b()[0];
                i5 = carBodyPacket.getCan36b()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_36D_STATE /* 557945670 */:
                i4 = carBodyPacket.getCan36d()[0];
                i5 = carBodyPacket.getCan36d()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_36E_STATE /* 557945671 */:
                i4 = carBodyPacket.getCan36e()[0];
                i5 = carBodyPacket.getCan36e()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_37F_STATE /* 557945672 */:
                i4 = carBodyPacket.getCan37f()[0];
                i5 = carBodyPacket.getCan37f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_38A_STATE /* 557945673 */:
                i4 = carBodyPacket.getCan38a()[0];
                i5 = carBodyPacket.getCan38a()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_38B_STATE /* 557945674 */:
                i4 = carBodyPacket.getCan38b()[0];
                i5 = carBodyPacket.getCan38b()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_394_STATE /* 557945675 */:
                i4 = carBodyPacket.getCan394()[0];
                i5 = carBodyPacket.getCan394()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_396_STATE /* 557945676 */:
                i4 = carBodyPacket.getCan396()[0];
                i5 = carBodyPacket.getCan396()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3A0_STATE /* 557945677 */:
                i4 = carBodyPacket.getCan3a0()[0];
                i5 = carBodyPacket.getCan3a0()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3A7_STATE /* 557945678 */:
                i4 = carBodyPacket.getCan3a7()[0];
                i5 = carBodyPacket.getCan3a7()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3AC_STATE /* 557945679 */:
                i4 = carBodyPacket.getCan3ac()[0];
                i5 = carBodyPacket.getCan3ac()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3AD_STATE /* 557945680 */:
                i4 = carBodyPacket.getCan3ad()[0];
                i5 = carBodyPacket.getCan3ad()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3B0_STATE /* 557945681 */:
                i4 = carBodyPacket.getCan3b0()[0];
                i5 = carBodyPacket.getCan3b0()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3B3_STATE /* 557945682 */:
                i4 = carBodyPacket.getCan3b3()[0];
                i5 = carBodyPacket.getCan3b3()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3B4_STATE /* 557945683 */:
                i4 = carBodyPacket.getCan3b4()[0];
                i5 = carBodyPacket.getCan3b4()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3BB_STATE /* 557945684 */:
                i4 = carBodyPacket.getCan3bb()[0];
                i5 = carBodyPacket.getCan3bb()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3BD_STATE /* 557945685 */:
                i4 = carBodyPacket.getCan3bd()[0];
                i5 = carBodyPacket.getCan3bd()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3C1_STATE /* 557945686 */:
                i4 = carBodyPacket.getCan3c1()[0];
                i5 = carBodyPacket.getCan3c1()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3C8_STATE /* 557945687 */:
                i4 = carBodyPacket.getCan3c8()[0];
                i5 = carBodyPacket.getCan3c8()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3CA_STATE /* 557945688 */:
                i4 = carBodyPacket.getCan3ca()[0];
                i5 = carBodyPacket.getCan3ca()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3CD_STATE /* 557945689 */:
                i4 = carBodyPacket.getCan3cd()[0];
                i5 = carBodyPacket.getCan3cd()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3D9_STATE /* 557945690 */:
                i4 = carBodyPacket.getCan3d9()[0];
                i5 = carBodyPacket.getCan3d9()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3DA_STATE /* 557945691 */:
                i4 = carBodyPacket.getCan3da()[0];
                i5 = carBodyPacket.getCan3da()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3F1_STATE /* 557945692 */:
                i4 = carBodyPacket.getCan3f1()[0];
                i5 = carBodyPacket.getCan3f1()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3F3_STATE /* 557945693 */:
                i4 = carBodyPacket.getCan3f3()[0];
                i5 = carBodyPacket.getCan3f3()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3FE_STATE /* 557945694 */:
                i4 = carBodyPacket.getCan3fe()[0];
                i5 = carBodyPacket.getCan3fe()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3FF_STATE /* 557945695 */:
                i4 = carBodyPacket.getCan3ff()[0];
                i5 = carBodyPacket.getCan3ff()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_401_STATE /* 557945696 */:
                i4 = carBodyPacket.getCan401()[0];
                i5 = carBodyPacket.getCan401()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_402_STATE /* 557945697 */:
                i4 = carBodyPacket.getCan402()[0];
                i5 = carBodyPacket.getCan402()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_404_STATE /* 557945698 */:
                i4 = carBodyPacket.getCan404()[0];
                i5 = carBodyPacket.getCan404()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_407_STATE /* 557945699 */:
                i4 = carBodyPacket.getCan407()[0];
                i5 = carBodyPacket.getCan407()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_40D_STATE /* 557945700 */:
                i4 = carBodyPacket.getCan40d()[0];
                i5 = carBodyPacket.getCan40d()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_417_STATE /* 557945701 */:
                i4 = carBodyPacket.getCan417()[0];
                i5 = carBodyPacket.getCan417()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_418_STATE /* 557945702 */:
                i4 = carBodyPacket.getCan418()[0];
                i5 = carBodyPacket.getCan418()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_41A_STATE /* 557945703 */:
                i4 = carBodyPacket.getCan41a()[0];
                i5 = carBodyPacket.getCan41a()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_41C_STATE /* 557945704 */:
                i4 = carBodyPacket.getCan41c()[0];
                i5 = carBodyPacket.getCan41c()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_41E_STATE /* 557945705 */:
                i4 = carBodyPacket.getCan41e()[0];
                i5 = carBodyPacket.getCan41e()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_41F_STATE /* 557945706 */:
                i4 = carBodyPacket.getCan41f()[0];
                i5 = carBodyPacket.getCan41f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_429_STATE /* 557945707 */:
                i4 = carBodyPacket.getCan429()[0];
                i5 = carBodyPacket.getCan429()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_42E_STATE /* 557945708 */:
                i4 = carBodyPacket.getCan42e()[0];
                i5 = carBodyPacket.getCan42e()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_42F_STATE /* 557945709 */:
                i4 = carBodyPacket.getCan42f()[0];
                i5 = carBodyPacket.getCan42f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_432_STATE /* 557945710 */:
                i4 = carBodyPacket.getCan432()[0];
                i5 = carBodyPacket.getCan432()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_433_STATE /* 557945711 */:
                i4 = carBodyPacket.getCan433()[0];
                i5 = carBodyPacket.getCan433()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_434_STATE /* 557945712 */:
                i4 = carBodyPacket.getCan434()[0];
                i5 = carBodyPacket.getCan434()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_436_STATE /* 557945713 */:
                i4 = carBodyPacket.getCan436()[0];
                i5 = carBodyPacket.getCan436()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_437_STATE /* 557945714 */:
                i4 = carBodyPacket.getCan437()[0];
                i5 = carBodyPacket.getCan437()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_438_STATE /* 557945715 */:
                i4 = carBodyPacket.getCan438()[0];
                i5 = carBodyPacket.getCan438()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_43E_STATE /* 557945716 */:
                i4 = carBodyPacket.getCan43e()[0];
                i5 = carBodyPacket.getCan43e()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_43F_STATE /* 557945717 */:
                i4 = carBodyPacket.getCan43f()[0];
                i5 = carBodyPacket.getCan43f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_443_STATE /* 557945718 */:
                i4 = carBodyPacket.getCan443()[0];
                i5 = carBodyPacket.getCan443()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_444_STATE /* 557945719 */:
                i4 = carBodyPacket.getCan444()[0];
                i5 = carBodyPacket.getCan444()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_447_STATE /* 557945720 */:
                i4 = carBodyPacket.getCan447()[0];
                i5 = carBodyPacket.getCan447()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_449_STATE /* 557945721 */:
                i4 = carBodyPacket.getCan449()[0];
                i5 = carBodyPacket.getCan449()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_453_STATE /* 557945722 */:
                i4 = carBodyPacket.getCan453()[0];
                i5 = carBodyPacket.getCan453()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_454_STATE /* 557945723 */:
                i4 = carBodyPacket.getCan454()[0];
                i5 = carBodyPacket.getCan454()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_461_STATE /* 557945724 */:
                i4 = carBodyPacket.getCan461()[0];
                i5 = carBodyPacket.getCan461()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_46C_STATE /* 557945725 */:
                i4 = carBodyPacket.getCan46c()[0];
                i5 = carBodyPacket.getCan46c()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_470_STATE /* 557945726 */:
                i4 = carBodyPacket.getCan470()[0];
                i5 = carBodyPacket.getCan470()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_475_STATE /* 557945727 */:
                i4 = carBodyPacket.getCan475()[0];
                i5 = carBodyPacket.getCan475()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_480_STATE /* 557945728 */:
                i4 = carBodyPacket.getCan480()[0];
                i5 = carBodyPacket.getCan480()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_481_STATE /* 557945729 */:
                i4 = carBodyPacket.getCan481()[0];
                i5 = carBodyPacket.getCan481()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_486_STATE /* 557945730 */:
                i4 = carBodyPacket.getCan486()[0];
                i5 = carBodyPacket.getCan486()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_487_STATE /* 557945731 */:
                i4 = carBodyPacket.getCan487()[0];
                i5 = carBodyPacket.getCan487()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_488_STATE /* 557945732 */:
                i4 = carBodyPacket.getCan488()[0];
                i5 = carBodyPacket.getCan488()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_489_STATE /* 557945733 */:
                i4 = carBodyPacket.getCan489()[0];
                i5 = carBodyPacket.getCan489()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_48A_STATE /* 557945734 */:
                i4 = carBodyPacket.getCan48a()[0];
                i5 = carBodyPacket.getCan48a()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_48E_STATE /* 557945735 */:
                i4 = carBodyPacket.getCan48e()[0];
                i5 = carBodyPacket.getCan48e()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_48F_STATE /* 557945736 */:
                i4 = carBodyPacket.getCan48f()[0];
                i5 = carBodyPacket.getCan48f()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_494_STATE /* 557945737 */:
                i4 = carBodyPacket.getCan494()[0];
                i5 = carBodyPacket.getCan494()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_49C_STATE /* 557945738 */:
                i4 = carBodyPacket.getCan49c()[0];
                i5 = carBodyPacket.getCan49c()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4A1_STATE /* 557945739 */:
                i4 = carBodyPacket.getCan4a1()[0];
                i5 = carBodyPacket.getCan4a1()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4A4_STATE /* 557945740 */:
                i4 = carBodyPacket.getCan4a4()[0];
                i5 = carBodyPacket.getCan4a4()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4A5_STATE /* 557945741 */:
                i4 = carBodyPacket.getCan4a5()[0];
                i5 = carBodyPacket.getCan4a5()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4A7_STATE /* 557945742 */:
                i4 = carBodyPacket.getCan4a7()[0];
                i5 = carBodyPacket.getCan4a7()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4B1_STATE /* 557945743 */:
                i4 = carBodyPacket.getCan4b1()[0];
                i5 = carBodyPacket.getCan4b1()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4B2_STATE /* 557945744 */:
                i4 = carBodyPacket.getCan4b2()[0];
                i5 = carBodyPacket.getCan4b2()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4B3_STATE /* 557945745 */:
                i4 = carBodyPacket.getCan4b3()[0];
                i5 = carBodyPacket.getCan4b3()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4B6_STATE /* 557945746 */:
                i4 = carBodyPacket.getCan4b6()[0];
                i5 = carBodyPacket.getCan4b6()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4BB_STATE /* 557945747 */:
                i4 = carBodyPacket.getCan4bb()[0];
                i5 = carBodyPacket.getCan4bb()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4BD_STATE /* 557945748 */:
                i4 = carBodyPacket.getCan4bd()[0];
                i5 = carBodyPacket.getCan4bd()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4BF_STATE /* 557945749 */:
                i4 = carBodyPacket.getCan4bf()[0];
                i5 = carBodyPacket.getCan4bf()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4C2_STATE /* 557945750 */:
                i4 = carBodyPacket.getCan4c2()[0];
                i5 = carBodyPacket.getCan4c2()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4C8_STATE /* 557945751 */:
                i4 = carBodyPacket.getCan4c8()[0];
                i5 = carBodyPacket.getCan4c8()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4D9_STATE /* 557945752 */:
                i4 = carBodyPacket.getCan4d9()[0];
                i5 = carBodyPacket.getCan4d9()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4E5_STATE /* 557945753 */:
                i4 = carBodyPacket.getCan4e5()[0];
                i5 = carBodyPacket.getCan4e5()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4E6_STATE /* 557945754 */:
                i4 = carBodyPacket.getCan4e6()[0];
                i5 = carBodyPacket.getCan4e6()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4F2_STATE /* 557945755 */:
                i4 = carBodyPacket.getCan4f2()[0];
                i5 = carBodyPacket.getCan4f2()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4F4_STATE /* 557945756 */:
                i4 = carBodyPacket.getCan4f4()[0];
                i5 = carBodyPacket.getCan4f4()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4F6_STATE /* 557945757 */:
                i4 = carBodyPacket.getCan4f6()[0];
                i5 = carBodyPacket.getCan4f6()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4F7_STATE /* 557945758 */:
                i4 = carBodyPacket.getCan4f7()[0];
                i5 = carBodyPacket.getCan4f7()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4FA_STATE /* 557945759 */:
                i4 = carBodyPacket.getCan4fa()[0];
                i5 = carBodyPacket.getCan4fa()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4FB_STATE /* 557945760 */:
                i4 = carBodyPacket.getCan4fb()[0];
                i5 = carBodyPacket.getCan4fb()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4FD_STATE /* 557945761 */:
                i4 = carBodyPacket.getCan4fd()[0];
                i5 = carBodyPacket.getCan4fd()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4FE_STATE /* 557945762 */:
                i4 = carBodyPacket.getCan4fe()[0];
                i5 = carBodyPacket.getCan4fe()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_344_STATE /* 557945763 */:
                i4 = carBodyPacket.getCan344()[0];
                i5 = carBodyPacket.getCan344()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_319_STATE /* 557945764 */:
                i4 = carBodyPacket.getCan319()[0];
                i5 = carBodyPacket.getCan319()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_258_STATE /* 557945765 */:
                i4 = carBodyPacket.getCan258()[0];
                i5 = carBodyPacket.getCan258()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_351_STATE /* 557945766 */:
                i4 = carBodyPacket.getCan351R()[0];
                i5 = carBodyPacket.getCan351R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4E8_STATE /* 557945767 */:
                i4 = carBodyPacket.getCan4e8R()[0];
                i5 = carBodyPacket.getCan4e8R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_4E9_STATE /* 557945768 */:
                i4 = carBodyPacket.getCan4e9R()[0];
                i5 = carBodyPacket.getCan4e9R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_257_STATE /* 557945769 */:
                i4 = carBodyPacket.getCan257R()[0];
                i5 = carBodyPacket.getCan257R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_48C_STATE /* 557945770 */:
                i4 = carBodyPacket.getCan48cR()[0];
                i5 = carBodyPacket.getCan48cR()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_3D6_STATE /* 557945771 */:
                i4 = carBodyPacket.getCan3d6R()[0];
                i5 = carBodyPacket.getCan3d6R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_38F_STATE /* 557945772 */:
                i4 = carBodyPacket.getCan38fR()[0];
                i5 = carBodyPacket.getCan38fR()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_464_STATE /* 557945773 */:
                i4 = carBodyPacket.getCan464R()[0];
                i5 = carBodyPacket.getCan464R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_338_STATE /* 557945774 */:
                i4 = carBodyPacket.getCan338R()[0];
                i5 = carBodyPacket.getCan338R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case BodyworkHalId.ID_CAN_145_STATE /* 557945775 */:
                i4 = carBodyPacket.getCan145R()[0];
                i5 = carBodyPacket.getCan145R()[1];
                hasMessageListener(CanIds.getRealCanId(i4), i4, i5);
                break;
            case 559954810:
                Iterator<AbsBYDAutoBodyworkListener> it57 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it57.hasNext()) {
                    it57.next().turnSteeringWheelChanged(3, carBodyPacket.getSteeringWheelY(), carBodyPacket.getSteeringWheelZ());
                }
                i3 = 2100;
                steeringWheelY = carBodyPacket.getSteeringWheelY();
                recoverOrSaveParamsChanged5 = (int) steeringWheelY;
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case 559954811:
                Iterator<AbsBYDAutoBodyworkListener> it58 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it58.hasNext()) {
                    it58.next().turnSteeringWheelChanged(1, carBodyPacket.getSteeringWheelY(), carBodyPacket.getSteeringWheelZ());
                }
                i3 = 2098;
                steeringWheelY = carBodyPacket.getSteeringWheelZ();
                recoverOrSaveParamsChanged5 = (int) steeringWheelY;
                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                caseFeatureIds(i3, numValueOf);
                break;
            case 559954812:
                Iterator<AbsBYDAutoBodyworkListener> it59 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it59.hasNext()) {
                    it59.next().onTurnRearviewMirrorChanged(3, carBodyPacket.getRearviewMirrorXLeft(), carBodyPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954813:
                Iterator<AbsBYDAutoBodyworkListener> it60 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it60.hasNext()) {
                    it60.next().onTurnRearviewMirrorChanged(7, carBodyPacket.getRearviewMirrorXRight(), carBodyPacket.getRearviewMirrorYRight());
                }
                break;
            case 559954814:
                Iterator<AbsBYDAutoBodyworkListener> it61 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it61.hasNext()) {
                    it61.next().onTurnRearviewMirrorChanged(1, carBodyPacket.getRearviewMirrorXLeft(), carBodyPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954815:
                Iterator<AbsBYDAutoBodyworkListener> it62 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it62.hasNext()) {
                    it62.next().onTurnRearviewMirrorChanged(6, carBodyPacket.getRearviewMirrorXRight(), carBodyPacket.getRearviewMirrorYRight());
                }
                break;
            default:
                switch (i) {
                    case 557857805:
                        Iterator<AbsBYDAutoBodyworkListener> it63 = this.mAbsBydAutoBodyworkListeners.iterator();
                        while (it63.hasNext()) {
                            it63.next().onRecoverOrSaveParamsChanged(0, 1, 1, carBodyPacket.getonSaveSeatParamsAllChangedPlace5());
                        }
                        i3 = 1305;
                        recoverOrSaveParamsChanged5 = carBodyPacket.getonSaveSeatParamsAllChangedPlace5();
                        numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                        caseFeatureIds(i3, numValueOf);
                        break;
                    case 557857806:
                        Iterator<AbsBYDAutoBodyworkListener> it64 = this.mAbsBydAutoBodyworkListeners.iterator();
                        while (it64.hasNext()) {
                            it64.next().onMainSaveOrRecoverStateChanged(1, carBodyPacket.getonSaveSeatParamsAllChangedPlace7());
                        }
                        i3 = 2134;
                        recoverOrSaveParamsChanged5 = carBodyPacket.getonSaveSeatParamsAllChangedPlace7();
                        numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                        caseFeatureIds(i3, numValueOf);
                        break;
                    default:
                        switch (i) {
                            case 557858048:
                                windowInitStateFL = carBodyPacket.getAutoExternalRearMirrorFoldActionStateR();
                                Iterator<AbsBYDAutoBodyworkListener> it65 = this.mAbsBydAutoBodyworkListeners.iterator();
                                while (it65.hasNext()) {
                                    it65.next().onAutoExternalRearMirrorFoldActionStateChanged(windowInitStateFL);
                                }
                                i6 = 2169;
                                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                                break;
                            case 557858049:
                                Iterator<AbsBYDAutoBodyworkListener> it66 = this.mAbsBydAutoBodyworkListeners.iterator();
                                while (it66.hasNext()) {
                                    it66.next().onAutoExternalRearMirrorFoldedState(carBodyPacket.getAutoExternalRearMirrorFoldedStateR());
                                }
                                break;
                            default:
                                switch (i) {
                                    case BodyworkHalId.ID_WINDOW_STATE_LEFT_FRONT /* 557862912 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it67 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it67.hasNext()) {
                                            it67.next().onWindowStateChanged(1, carBodyPacket.getmWindowStateLeftFront());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_LEFT_REAR /* 557862913 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it68 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it68.hasNext()) {
                                            it68.next().onWindowStateChanged(3, carBodyPacket.getmWindowStateLeftRear());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_RIGHT_FRONT /* 557862914 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it69 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it69.hasNext()) {
                                            it69.next().onWindowStateChanged(2, carBodyPacket.getmWindowStateRightFront());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_RIGHT_REAR /* 557862915 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it70 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it70.hasNext()) {
                                            it70.next().onWindowStateChanged(4, carBodyPacket.getmWindowStateRightRear());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_LEFT_FRONT_R /* 557862916 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it71 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it71.hasNext()) {
                                            it71.next().onWindowControlStateChanged(1, carBodyPacket.getWindowControlStateLeftFrontR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_RIGHT_FRONT_R /* 557862917 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it72 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it72.hasNext()) {
                                            it72.next().onWindowControlStateChanged(2, carBodyPacket.getWindowControlStateRightFrontR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_LEFT_REAR_R /* 557862918 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it73 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it73.hasNext()) {
                                            it73.next().onWindowControlStateChanged(3, carBodyPacket.getWindowControlStateLeftRearR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_RIGHT_REAR_R /* 557862919 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it74 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it74.hasNext()) {
                                            it74.next().onWindowControlStateChanged(4, carBodyPacket.getWindowControlStateRightRearR());
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_MOON_S /* 557862924 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it75 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it75.hasNext()) {
                                                    it75.next().onWindowOpenPercentChanged(5, carBodyPacket.getmWindowOpenPercentMoonS());
                                                }
                                                break;
                                            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_SUN_S /* 557862925 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it76 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it76.hasNext()) {
                                                    it76.next().onWindowOpenPercentChanged(6, carBodyPacket.getmWindowOpenPercentSunS());
                                                }
                                                break;
                                            case 557862926:
                                                for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener : this.mAbsBydAutoBodyworkListeners) {
                                                    if (carBodyPacket.getmBatteryVoltageLevel() == 1) {
                                                        absBYDAutoBodyworkListener.onBatteryVoltageLevelChanged(1);
                                                    } else if (carBodyPacket.getmBatteryVoltageLevel() == 2) {
                                                        absBYDAutoBodyworkListener.onBatteryVoltageLevelChanged(0);
                                                    }
                                                }
                                                break;
                                            case 557862927:
                                                for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener2 : this.mAbsBydAutoBodyworkListeners) {
                                                    if (carBodyPacket.getmPowerLevel() == 0) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(255);
                                                        sb = new StringBuilder();
                                                        sb.append("TS-Manager -->");
                                                        sb.append(this.mContext.getPackageName());
                                                        sb.append(" value is ");
                                                        sb.append(255);
                                                    } else if (carBodyPacket.getmPowerLevel() == 1) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(0);
                                                        sb = new StringBuilder();
                                                        sb.append("TS-Manager -->");
                                                        sb.append(this.mContext.getPackageName());
                                                        sb.append(" value is ");
                                                        sb.append(0);
                                                    } else if (carBodyPacket.getmPowerLevel() == 2) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(1);
                                                        sb = new StringBuilder();
                                                        sb.append("TS-Manager -->");
                                                        sb.append(this.mContext.getPackageName());
                                                        sb.append(" value is ");
                                                        sb.append(1);
                                                    } else if (carBodyPacket.getmPowerLevel() == 3) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(2);
                                                        sb = new StringBuilder();
                                                        sb.append("TS-Manager -->");
                                                        sb.append(this.mContext.getPackageName());
                                                        sb.append(" value is ");
                                                        sb.append(2);
                                                    }
                                                    Log.w(TAG, sb.toString());
                                                }
                                                windowInitStateFL = carBodyPacket.getmPowerLevel();
                                                i6 = 2002;
                                                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                                                break;
                                            case BodyworkHalId.ID_STEERING_WHEEL_VALUE_ANGEL /* 557862928 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it77 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it77.hasNext()) {
                                                    it77.next().onSteeringWheelValueChanged(1, carBodyPacket.getmSteeringWheelValueAngel());
                                                }
                                                break;
                                            case BodyworkHalId.ID_STEERING_WHEEL_VALUE_SPEED /* 557862929 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it78 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it78.hasNext()) {
                                                    it78.next().onSteeringWheelValueChanged(2, carBodyPacket.getmSteeringWheelValueSpeed());
                                                }
                                                break;
                                            case BodyworkHalId.ID_AUTO_TYPE /* 557862930 */:
                                                i3 = 1682;
                                                recoverOrSaveParamsChanged5 = carBodyPacket.getmAutoType();
                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                caseFeatureIds(i3, numValueOf);
                                                break;
                                            case BodyworkHalId.ID_AUTO_SYSTEM_STATE /* 557862931 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it79 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it79.hasNext()) {
                                                    it79.next().onAutoSystemStateChanged(carBodyPacket.getmAutoSystemState());
                                                }
                                                break;
                                            default:
                                                switch (i) {
                                                    case BodyworkHalId.ID_REAR_WINDOWBLIND_STATER /* 557862972 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it80 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it80.hasNext()) {
                                                            it80.next().onRearWindowblindStateChanged(carBodyPacket.getRearWindowblindStateR());
                                                        }
                                                        i3 = 2007;
                                                        recoverOrSaveParamsChanged5 = carBodyPacket.getRearWindowblindStateR();
                                                        numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                        caseFeatureIds(i3, numValueOf);
                                                        break;
                                                    case BodyworkHalId.ID_REAR_WINDOWBLIND_INIT_STATER /* 557862973 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it81 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it81.hasNext()) {
                                                            it81.next().onRearWindowblindInitStateChanged(carBodyPacket.getRearWindowblindInitStateR());
                                                        }
                                                        windowInitStateFL = carBodyPacket.getRearWindowblindInitStateR();
                                                        caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                                                        break;
                                                    case BodyworkHalId.ID_REAR_WINDOWBLIND_POSITIONR /* 557862974 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it82 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it82.hasNext()) {
                                                            it82.next().onRearWindowblindPositionChanged(carBodyPacket.getRearWindowblindPositionR());
                                                        }
                                                        i3 = 2008;
                                                        recoverOrSaveParamsChanged5 = carBodyPacket.getRearWindowblindPositionR();
                                                        numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                        caseFeatureIds(i3, numValueOf);
                                                        break;
                                                    case BodyworkHalId.ID_BACK_DOOR_INIT_STATER /* 557862975 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it83 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it83.hasNext()) {
                                                            it83.next().onBackDoorInitStateChanged(carBodyPacket.getBackDoorInitStateR());
                                                        }
                                                        i3 = 1686;
                                                        recoverOrSaveParamsChanged5 = carBodyPacket.getBackDoorInitStateR();
                                                        numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                        caseFeatureIds(i3, numValueOf);
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case BodyworkHalId.ID_BACK_DOOR_CUR_STATER /* 557862978 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it84 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it84.hasNext()) {
                                                                    it84.next().onBackDoorCurStateChanged(carBodyPacket.getBackDoorCurStateR());
                                                                }
                                                                i3 = 1685;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getBackDoorCurStateR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_RIGHT_STRUT_MOROT_RUNNING_STATER /* 557862979 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it85 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it85.hasNext()) {
                                                                    it85.next().onRightStrutMorotRunningStateChanged(carBodyPacket.getRightStrutMorotRunningStateR());
                                                                }
                                                                i3 = 2049;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getRightStrutMorotRunningStateR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_BACK_DOOR_POSITIONR /* 557862980 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it86 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it86.hasNext()) {
                                                                    it86.next().onBackDoorPositionChanged(carBodyPacket.getBackDoorPositionR());
                                                                }
                                                                windowInitStateFL = carBodyPacket.getBackDoorPositionR();
                                                                i6 = 1687;
                                                                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
                                                                break;
                                                            case BodyworkHalId.ID_BACK_DOOR_ANTI_PLAY_MODER /* 557862981 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it87 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it87.hasNext()) {
                                                                    it87.next().onBackDoorAntiPlayModeChanged(carBodyPacket.getBackDoorAntiPlayModeR());
                                                                }
                                                                i3 = 1689;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getElecSlideDoorStateR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_ELEC_SLIDE_DOOR_STATER /* 557862982 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it88 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it88.hasNext()) {
                                                                    it88.next().onElecSlideDoorStateChanged(carBodyPacket.getElecSlideDoorStateR());
                                                                }
                                                                i3 = 1717;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getElecSlideDoorStateR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_LEFT_SLIDE_DOOR_STATER /* 557862983 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it89 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it89.hasNext()) {
                                                                    it89.next().onLeftSlideDoorStateChanged(carBodyPacket.getLeftSlideDoorStateR());
                                                                }
                                                                i3 = 1757;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getLeftSlideDoorStateR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_STATER /* 557862984 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it90 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it90.hasNext()) {
                                                                    it90.next().onRightSlideDoorStateChanged(carBodyPacket.getRightSlideDoorStateR());
                                                                }
                                                                i3 = 2047;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getRightSlideDoorStateR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_LEFT_SLIDE_DOOR_ACTIONR /* 557862985 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it91 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it91.hasNext()) {
                                                                    it91.next().onLeftSlideDoorActionChanged(carBodyPacket.getLeftSlideDoorActionR());
                                                                }
                                                                i3 = 1753;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getLeftSlideDoorActionR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_ACTIONR /* 557862986 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it92 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it92.hasNext()) {
                                                                    it92.next().onRightSlideDoorActionChanged(carBodyPacket.getRightSlideDoorActionR());
                                                                }
                                                                i3 = 2044;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getRightSlideDoorActionR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_LEFT_SLIDE_DOOR_PERCENTR /* 557862987 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it93 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it93.hasNext()) {
                                                                    it93.next().onLeftSlideDoorPercentChanged(carBodyPacket.getLeftSlideDoorPercentR());
                                                                }
                                                                i3 = 1756;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getLeftSlideDoorPercentR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_PERCENTR /* 557862988 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it94 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it94.hasNext()) {
                                                                    it94.next().onRightSlideDoorPercentChanged(carBodyPacket.getRightSlideDoorPercentR());
                                                                }
                                                                i3 = 2046;
                                                                recoverOrSaveParamsChanged5 = carBodyPacket.getRightSlideDoorPercentR();
                                                                numValueOf = Integer.valueOf(recoverOrSaveParamsChanged5);
                                                                caseFeatureIds(i3, numValueOf);
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case BodyworkHalId.ID_SMART_VOICE_LIMIT_R /* 557863016 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it95 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it95.hasNext()) {
                                                                            it95.next().onSmartVoiceLimitChanged(carBodyPacket.getSmartVoiceLimit());
                                                                        }
                                                                        break;
                                                                    case BodyworkHalId.ID_B_C_M_VECHILE_STATUS_R /* 557863017 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it96 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it96.hasNext()) {
                                                                            it96.next().onBCMVechileStatusChanged(carBodyPacket.getBcmVechileStatus());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case BodyworkHalId.ID_DOOR_LOCK_AREA_RIGHT_FRONT /* 557863022 */:
                                                                                Iterator<AbsBYDAutoBodyworkListener> it97 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                while (it97.hasNext()) {
                                                                                    it97.next().onDoorLockStatusChanged(3, carBodyPacket.getDoorLockStatusRightFront());
                                                                                }
                                                                                windowInitStateFL = carBodyPacket.getDoorLockStatusRightFront();
                                                                                i6 = 2025;
                                                                                break;
                                                                            case BodyworkHalId.ID_DOOR_LOCK_AREA_LEFT_REAR /* 557863023 */:
                                                                                Iterator<AbsBYDAutoBodyworkListener> it98 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                while (it98.hasNext()) {
                                                                                    it98.next().onDoorLockStatusChanged(2, carBodyPacket.getDoorLockStatusLeftRear());
                                                                                }
                                                                                windowInitStateFL = carBodyPacket.getDoorLockStatusLeftRear();
                                                                                i6 = 1768;
                                                                                break;
                                                                            case BodyworkHalId.ID_DOOR_LOCK_AREA_RIGHT_REAR /* 557863024 */:
                                                                                Iterator<AbsBYDAutoBodyworkListener> it99 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                while (it99.hasNext()) {
                                                                                    it99.next().onDoorLockStatusChanged(4, carBodyPacket.getDoorLockStatusAreaRightRear());
                                                                                }
                                                                                windowInitStateFL = carBodyPacket.getDoorLockStatusAreaRightRear();
                                                                                i6 = 2050;
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case BodyworkHalId.ID_SEAT_MODE_WAKEUP_FEEDBACK_STATE_R /* 557863041 */:
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it100 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it100.hasNext()) {
                                                                                            it100.next().onSeatModeWakeupFeedbackStateChanged(carBodyPacket.getSeatModeWakeupFeedbackStateR());
                                                                                        }
                                                                                        break;
                                                                                    case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_LEFT_FRONT_R /* 557863042 */:
                                                                                        windowInitStateFL = carBodyPacket.getInternalDoorHandleStateLeftFrontR();
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it101 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it101.hasNext()) {
                                                                                            it101.next().onDoorHandleStateChanged(1, windowInitStateFL);
                                                                                        }
                                                                                        i6 = 4647;
                                                                                        break;
                                                                                    case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_RIGHT_FRONT_R /* 557863043 */:
                                                                                        windowInitStateFL = carBodyPacket.getInternalDoorHandleStateRightFrontR();
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it102 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it102.hasNext()) {
                                                                                            it102.next().onDoorHandleStateChanged(2, windowInitStateFL);
                                                                                        }
                                                                                        i6 = 4648;
                                                                                        break;
                                                                                    case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_LEFT_BACK_R /* 557863044 */:
                                                                                        windowInitStateFL = carBodyPacket.getInternalDoorHandleStateLeftBackR();
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it103 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it103.hasNext()) {
                                                                                            it103.next().onDoorHandleStateChanged(3, windowInitStateFL);
                                                                                        }
                                                                                        i6 = 4646;
                                                                                        break;
                                                                                    case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_RIGHT_BACK_R /* 557863045 */:
                                                                                        windowInitStateFL = carBodyPacket.getInternalDoorHandleStateRightBackR();
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it104 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it104.hasNext()) {
                                                                                            it104.next().onDoorHandleStateChanged(4, windowInitStateFL);
                                                                                        }
                                                                                        i6 = 4649;
                                                                                        break;
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                caseFeatureIds(i6, Integer.valueOf(windowInitStateFL));
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
    }

    private boolean createWindowStateData(int i, int i2, int[] iArr) {
        if (i == 1763) {
            iArr[0] = i2;
        } else if (i == 1778) {
            iArr[2] = i2;
        } else if (i == 2034) {
            iArr[1] = i2;
        } else {
            if (i != 2060) {
                Log.v(TAG, "no have control window feature_id.");
                return false;
            }
            iArr[3] = i2;
        }
        return true;
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
        }
    }

    public static synchronized BodyworkManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BodyworkManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new BodyworkManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    private void hasMessageListener(int i, int i2, int i3) {
        Log.v(TAG, "hasMessageListener realId is " + i + ",hasMessageId" + i2);
        int i4 = i3 == 0 ? 1 : 0;
        for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener : this.mAbsBydAutoBodyworkListeners) {
            if (i2 != 315) {
                if (i2 == 985) {
                    absBYDAutoBodyworkListener.onMessage5sOnlineStateChanged(i, i4);
                } else if (i2 == 1028 || i2 == 1054 || i2 == 1092 || i2 == 1268 || i2 == 1270) {
                }
                absBYDAutoBodyworkListener.onHasMessageChanged(i, i4);
            }
            absBYDAutoBodyworkListener.onMessage5sOnlineStateChanged(i, i4);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0032. Please report as an issue. */
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        int antiTheftStatus;
        int i;
        double steeringWheelValue;
        int i2;
        float steeringWheelZ;
        Object objValueOf;
        int internalDoorHandleState;
        int internalDoorHandleState2;
        int internalDoorHandleState3;
        Integer numValueOf;
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        int i3 = -2147482648;
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i4 : iArr) {
                if (i4 > 0) {
                    switch (i4) {
                        case 1681:
                            antiTheftStatus = getAntiTheftStatus();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1681;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1682:
                            antiTheftStatus = getAutoType();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1682;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1685:
                            antiTheftStatus = getBackDoorCurState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1685;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1686:
                            antiTheftStatus = getBackDoorInitState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1686;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1687:
                            antiTheftStatus = getBackDoorPosition();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1687;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1689:
                            antiTheftStatus = getBackDoorAntiPlayMode();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1689;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1717:
                            antiTheftStatus = getElecSlideDoorState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1717;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1731:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getDoorStateHood();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1731;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1732:
                            antiTheftStatus = this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1732;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1735:
                            antiTheftStatus = getWindowThermalProtectionState(6);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1735;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1742:
                            antiTheftStatus = getDoorState(5);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1742;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1746:
                            antiTheftStatus = getWindowState(1);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1746;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1747:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowPermitState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1747;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1748:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1748;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1749:
                            antiTheftStatus = getDoorState(1);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1749;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1750:
                            antiTheftStatus = getDoorState(3);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1750;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1751:
                            antiTheftStatus = getWindowState(3);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1751;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1752:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1752;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1753:
                            antiTheftStatus = getLeftSlideDoorAction();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1753;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1756:
                            antiTheftStatus = getLeftSlideDoorPercent();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1756;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1757:
                            antiTheftStatus = getLeftSlideDoorState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1757;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1759:
                            antiTheftStatus = getLeftStrutMorotRunningState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1759;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1765:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1765;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1768:
                            antiTheftStatus = getDoorLockStatus(2);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1768;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1778:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(3);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1778;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1780:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1780;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1782:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getDoorStateLuggage();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1782;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1785:
                            antiTheftStatus = getWindowState(5);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1785;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1786:
                            antiTheftStatus = getMoonRoofConfig();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1786;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1788:
                            antiTheftStatus = getMoonRoofConfig();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1788;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1789:
                            antiTheftStatus = getWindowOpenPercent(5);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1789;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1896:
                            antiTheftStatus = getReadyIndicatorLightState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 1896;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2002:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getPowerLevel();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2002;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2005:
                            antiTheftStatus = getRearviewMirrorSupport();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2005;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2006:
                            antiTheftStatus = getRearLampLightState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2006;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2007:
                            antiTheftStatus = getRearWindowblindState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2007;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2008:
                            antiTheftStatus = getRearWindowblindPosition();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2008;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2009:
                            antiTheftStatus = getRearWindowblindInitState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2009;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2012:
                            antiTheftStatus = getWindowOpenPercent(7);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2012;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2014:
                            antiTheftStatus = getWindowThermalProtectionState(7);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2014;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2025:
                            antiTheftStatus = getDoorLockStatus(3);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2025;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2034:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(2);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2034;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2036:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2036;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2039:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2039;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2040:
                            antiTheftStatus = getDoorState(2);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2040;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2041:
                            antiTheftStatus = getDoorState(4);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2041;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2043:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2043;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2044:
                            antiTheftStatus = getRightSlideDoorAction();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2044;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2046:
                            antiTheftStatus = getRightSlideDoorPercent();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2046;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2047:
                            antiTheftStatus = getRightSlideDoorState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2047;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2049:
                            antiTheftStatus = getRightStrutMorotRunningState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2049;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2050:
                            antiTheftStatus = getDoorLockStatus(4);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2050;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2060:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(4);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2060;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2062:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2062;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2068:
                            antiTheftStatus = getWindowThermalProtectionState(5);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2068;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2072:
                            steeringWheelValue = getSteeringWheelValue(1);
                            bYDAutoEventValue.doubleValue = steeringWheelValue;
                            i2 = 2072;
                            sparseArray.put(i2, Double.valueOf(steeringWheelValue));
                            break;
                        case 2075:
                            steeringWheelValue = getSteeringWheelValue(2);
                            bYDAutoEventValue.doubleValue = steeringWheelValue;
                            i2 = 2075;
                            sparseArray.put(i2, Double.valueOf(steeringWheelValue));
                            break;
                        case 2076:
                            antiTheftStatus = getSunroofCloseNotice();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2076;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2077:
                            antiTheftStatus = getSunroofInitState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2077;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2078:
                            antiTheftStatus = getSunroofPosition();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2078;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2079:
                            antiTheftStatus = getSunroofState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2079;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2080:
                            antiTheftStatus = getWindowblindInitState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2080;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2081:
                            antiTheftStatus = getSunroofWindowblindPosition();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2081;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2082:
                            antiTheftStatus = getWindowblindState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2082;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2084:
                            antiTheftStatus = getWindowOpenPercent(6);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2084;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2089:
                            antiTheftStatus = getWindowAntiPinchConfig();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2089;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2090:
                            antiTheftStatus = getWindowOpenPercent(1);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2090;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2091:
                            antiTheftStatus = getWindowOpenPercent(3);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2091;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2092:
                            antiTheftStatus = getWindowOpenPercent(2);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2092;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2093:
                            antiTheftStatus = getWindowOpenPercent(4);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2093;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2098:
                            steeringWheelZ = this.mTsManagerImpl.getCarBodyManager().getSteeringWheelZ();
                            bYDAutoEventValue.floatValue = steeringWheelZ;
                            i = 2098;
                            objValueOf = Float.valueOf(steeringWheelZ);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2100:
                            steeringWheelZ = getSteeringWheelY();
                            bYDAutoEventValue.floatValue = steeringWheelZ;
                            i = 2100;
                            objValueOf = Float.valueOf(steeringWheelZ);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2120:
                            steeringWheelZ = getRearviewMirrorX(1);
                            bYDAutoEventValue.floatValue = steeringWheelZ;
                            i = 2120;
                            objValueOf = Float.valueOf(steeringWheelZ);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2122:
                            steeringWheelZ = getRearviewMirrorY(1);
                            bYDAutoEventValue.floatValue = steeringWheelZ;
                            i = 2122;
                            objValueOf = Float.valueOf(steeringWheelZ);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2130:
                            steeringWheelZ = getRearviewMirrorX(2);
                            bYDAutoEventValue.floatValue = steeringWheelZ;
                            i = 2130;
                            objValueOf = Float.valueOf(steeringWheelZ);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2132:
                            steeringWheelZ = getRearviewMirrorY(2);
                            bYDAutoEventValue.floatValue = steeringWheelZ;
                            i = 2132;
                            objValueOf = Float.valueOf(steeringWheelZ);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2134:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getMainSaveOrRecoverState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2134;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2169:
                            antiTheftStatus = getAutoExternalRearMirrorFoldActionState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2169;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2170:
                            antiTheftStatus = getAutoExternalRearMirrorFoldedState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2170;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2445:
                            antiTheftStatus = getEnergyType();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 2445;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 3648:
                            antiTheftStatus = getReadyIndicatorLightState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 3648;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4614:
                            antiTheftStatus = this.mTsManagerImpl.getCarBodyManager().getMain2SaveOrRecoverState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4614;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4615:
                            antiTheftStatus = getMoonRoofPercentControlFlag();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4615;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4639:
                            antiTheftStatus = getSeatLumbarSupportConfig(1);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4639;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4640:
                            antiTheftStatus = getSeatLumbarSupportConfig(2);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4640;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4643:
                            antiTheftStatus = getFourPeopleEnjoyConfig();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4643;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4645:
                            antiTheftStatus = getSeatModeWakeupFeedbackState();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4645;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4646:
                            internalDoorHandleState = getInternalDoorHandleState(3);
                            bYDAutoEventValue.intValue = internalDoorHandleState;
                            sparseArray.put(4646, Integer.valueOf(internalDoorHandleState));
                            break;
                        case 4647:
                            internalDoorHandleState2 = getInternalDoorHandleState(1);
                            bYDAutoEventValue.intValue = internalDoorHandleState2;
                            sparseArray.put(4647, Integer.valueOf(internalDoorHandleState2));
                            break;
                        case 4648:
                            internalDoorHandleState3 = getInternalDoorHandleState(2);
                            bYDAutoEventValue.intValue = internalDoorHandleState3;
                            sparseArray.put(4648, Integer.valueOf(internalDoorHandleState3));
                            break;
                        case 4649:
                            int internalDoorHandleState4 = getInternalDoorHandleState(4);
                            bYDAutoEventValue.intValue = internalDoorHandleState4;
                            numValueOf = Integer.valueOf(internalDoorHandleState4);
                            sparseArray.put(4649, numValueOf);
                            break;
                        case 4650:
                            antiTheftStatus = getElectricPedalConfig();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4650;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4651:
                            antiTheftStatus = getTailDoorTypeConfig();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4651;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4653:
                            antiTheftStatus = getWindowPercentageControlConfig();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4653;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4654:
                            antiTheftStatus = getShiftMode();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4654;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SYSTEM_CONFIG /* 4725 */:
                            antiTheftStatus = getSystemConfig();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = BYDAutoFeatureIds.BODYWORK_SYSTEM_CONFIG;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4726 */:
                            internalDoorHandleState2 = getSystemConfig();
                            bYDAutoEventValue.intValue = internalDoorHandleState2;
                            if (internalDoorHandleState2 == 2) {
                                antiTheftStatus = getInternalDoorHandleStateByRearBcm(1);
                                bYDAutoEventValue.intValue = antiTheftStatus;
                                i = BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only;
                                objValueOf = Integer.valueOf(antiTheftStatus);
                                sparseArray.put(i, objValueOf);
                            }
                            sparseArray.put(4647, Integer.valueOf(internalDoorHandleState2));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4727 */:
                            internalDoorHandleState = getSystemConfig();
                            bYDAutoEventValue.intValue = internalDoorHandleState;
                            if (internalDoorHandleState == 2) {
                                antiTheftStatus = getInternalDoorHandleStateByRearBcm(3);
                                bYDAutoEventValue.intValue = antiTheftStatus;
                                i = BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only;
                                objValueOf = Integer.valueOf(antiTheftStatus);
                                sparseArray.put(i, objValueOf);
                            }
                            sparseArray.put(4646, Integer.valueOf(internalDoorHandleState));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4728 */:
                            internalDoorHandleState3 = getSystemConfig();
                            bYDAutoEventValue.intValue = internalDoorHandleState3;
                            if (internalDoorHandleState3 == 2) {
                                antiTheftStatus = getInternalDoorHandleStateByRearBcm(2);
                                bYDAutoEventValue.intValue = antiTheftStatus;
                                i = BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only;
                                objValueOf = Integer.valueOf(antiTheftStatus);
                                sparseArray.put(i, objValueOf);
                            }
                            sparseArray.put(4648, Integer.valueOf(internalDoorHandleState3));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4729 */:
                            int systemConfig = getSystemConfig();
                            bYDAutoEventValue.intValue = systemConfig;
                            if (systemConfig == 2) {
                                antiTheftStatus = getInternalDoorHandleStateByRearBcm(4);
                                bYDAutoEventValue.intValue = antiTheftStatus;
                                i = BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only;
                                objValueOf = Integer.valueOf(antiTheftStatus);
                                sparseArray.put(i, objValueOf);
                            } else {
                                numValueOf = Integer.valueOf(systemConfig);
                                sparseArray.put(4649, numValueOf);
                            }
                            break;
                        case 4732:
                            antiTheftStatus = hasMessage(219);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4732;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4733:
                            antiTheftStatus = hasMessage(220);
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            i = 4733;
                            objValueOf = Integer.valueOf(antiTheftStatus);
                            sparseArray.put(i, objValueOf);
                            break;
                    }
                    int i5 = 0;
                    for (int i6 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i5] = ((Integer) sparseArray.get(i6)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i5++;
                    }
                    i3 = -2147482648;
                } else {
                    bYDAutoEventValue.intValue = i3;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getAlarmState() {
        Log.v(TAG, "getAlarmState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getAlarmState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAlarmState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAntiTheftStatus() {
        Log.v(TAG, "getAntiTheftStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getAntiTheftStatus();
        }
        e.s(e.k("getAntiTheftStatus mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        Log.v(TAG, "getAutoExternalRearMirrorFoldActionState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().getAutoExternalRearMirrorFoldActionState();
    }

    public int getAutoExternalRearMirrorFoldedState() {
        Log.v(TAG, "getAutoExternalRearMirrorFoldedState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().getAutoExternalRearMirrorFoldedState();
    }

    public int getAutoModelName() {
        Log.v(TAG, "getAutoModelName is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getAutoModelName mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAutoModelName();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAutoSystemState() {
        Log.v(TAG, "getAutoSystemState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getAutoSystemState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int autoSystemState = this.mTsManagerImpl.getCarBodyManager().getAutoSystemState();
        if (autoSystemState == 1) {
            return 0;
        }
        if (autoSystemState == 2) {
            return 1;
        }
        return autoSystemState;
    }

    public int getAutoType() {
        Log.v(TAG, "getAutoType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getAutoType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        try {
            int autoTypeTwo = this.mTsManagerImpl.getCarBodyManager().getAutoTypeTwo();
            if (autoTypeTwo > 0) {
                return autoTypeTwo;
            }
            int autoType = this.mTsManagerImpl.getCarBodyManager().getAutoType();
            if (autoType != 0) {
                return autoType;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getAutoVIN() {
        Log.v(TAG, "getAutoVIN is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getAutoVIN mForTestValue is"), this.mForTest, " execute return", TAG);
            return "";
        }
        String autoVin = this.mOtaSdkManager.getAutoVin();
        return (autoVin == null || autoVin.length() == 0) ? DEFAULT_VIN : autoVin;
    }

    public int getAwningConfigutation() {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getAwningConfigutation();
        }
        Log.v(TAG, " getAwningConfigutation mForTestValue is 0 execute return");
        return -1;
    }

    public int getBackDoorAntiPlayMode() {
        Log.v(TAG, "getBackDoorAntiPlayMode is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getBackDoorAntiPlayMode();
        }
        e.s(e.k("getBackDoorAntiPlayMode mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getBackDoorCurState() {
        Log.v(TAG, "getBackDoorCurState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getBackDoorCurState();
        }
        e.s(e.k("getBackDoorCurState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getBackDoorInitState() {
        Log.v(TAG, "getBackDoorInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getBackDoorInitState();
        }
        e.s(e.k("getBackDoorInitState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getBackDoorPosition() {
        Log.v(TAG, "getBackDoorPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getBackDoorPosition();
        }
        e.s(e.k("getBackDoorPosition mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getBatteryVoltageLevel() {
        Log.v(TAG, "getBatteryVoltageLevel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getBatteryVoltageLevel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int batteryVoltageLevel = this.mTsManagerImpl.getCarBodyManager().getBatteryVoltageLevel();
        if (batteryVoltageLevel == 0) {
            return 255;
        }
        if (batteryVoltageLevel == 1) {
            return 1;
        }
        if (batteryVoltageLevel == 2) {
            return 0;
        }
        return batteryVoltageLevel;
    }

    public int getBcmVechileStatus() {
        if (1 == this.mForTest) {
            e.s(e.k(" getBCMVechileStatus mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getBcmVechileStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDoorLockStatus(int i) {
        Log.v(TAG, "getDoorLockStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getDoorLockStatus mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusLeftRear();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusRightFront();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusAreaRightRear();
    }

    public int getDoorState(int i) {
        int doorStateLeftFront;
        Log.v(TAG, "getDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getDoorState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        switch (i) {
            case 1:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLeftFront();
                break;
            case 2:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateRightFront();
                break;
            case 3:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLeftRear();
                break;
            case 4:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateRightRear();
                break;
            case 5:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateHood();
                break;
            case 6:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLuggage();
                break;
            default:
                doorStateLeftFront = 0;
                break;
        }
        if (doorStateLeftFront == 1) {
            return 0;
        }
        if (doorStateLeftFront == 0) {
            return -1;
        }
        if (doorStateLeftFront == 2) {
            return 1;
        }
        return doorStateLeftFront;
    }

    public int getElecSlideDoorState() {
        Log.v(TAG, "getElecSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getElecSlideDoorState();
        }
        e.s(e.k("getElecSlideDoorState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getElectricFrontHatchConfiguration() {
        Log.i(TAG, "getElectricFrontHatchConfiguration is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getElectricFrontHatchConfiguration mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
            return this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getElectricPedalConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getElectricPedalConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getElectricPedalConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getEnergyType() {
        Log.v(TAG, "getEnergyType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyType();
        }
        e.s(e.k("getEnergyType mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getEvReadyIndicatorLightState() {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getEvReadyIndicatorLightState();
        }
        Log.v(TAG, " getEvReadyIndicatorLightState mForTestValue is 0 execute return");
        return -1;
    }

    public int getFourPeopleEnjoyConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFourPeopleEnjoyConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getFourPeopleEnjoyConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getFuelElecLowPower() {
        Log.v(TAG, "getFuelElecLowPower is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getFuelElecLowPower mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getFuelElecLowPower();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getInternalDoorHandleState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getInternalDoorHandleState mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateLeftFront(i);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateRightFront(i);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateLeftBack(i);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateRightBack(i);
        }
        return 0;
    }

    public int getInternalDoorHandleStateByRearBcm(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getInternalDoorHandleStateByRearBcm is 0 execute return");
            return -1;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmlfr();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmlrr();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmrfr();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmrrr();
        }
        return -1;
    }

    public int getLeftSlideDoorAction() {
        Log.v(TAG, "getLeftSlideDoorAction is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorAction();
        }
        e.s(e.k("getLeftSlideDoorAction mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getLeftSlideDoorPercent() {
        Log.v(TAG, "getLeftSlideDoorPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorPercent();
        }
        e.s(e.k("getLeftSlideDoorPercent mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getLeftSlideDoorState() {
        Log.v(TAG, "getLeftSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorState();
        }
        e.s(e.k("getLeftSlideDoorState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getLeftStrutMorotRunningState() {
        Log.v(TAG, "getLeftStrutMorotRunningState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftStrutMorotRunningState();
        }
        e.s(e.k("getLeftStrutMorotRunningState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getLightStatus(int i) {
        String strC;
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k(" getBCMVechileStatus mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        int lightSideStatus = 0;
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            strC = "adapter is not connect";
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
            strC = d.c("getLightStatus Type is:", i, " And Result is:", lightSideStatus);
        }
        Log.v(TAG, strC);
        return lightSideStatus;
    }

    public int getMain2SaveOrRecoverState() {
        Log.i(TAG, "getMain2SaveOrRecoverState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getMain2SaveOrRecoverState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMain2SaveOrRecoverState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMainSaveOrRecoverState() {
        Log.i(TAG, "getMainSaveOrRecoverState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getMainSaveOrRecoverState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMainSaveOrRecoverState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMoonRoofConfig() {
        Log.v(TAG, "getMoonRoofConfig is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getMoonRoofConfig mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        int moonRoofConfigTwo = this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfigTwo();
        if (moonRoofConfigTwo != 0 && (moonRoofConfigTwo == 1 || moonRoofConfigTwo == 2 || moonRoofConfigTwo == 3 || moonRoofConfigTwo == 6 || moonRoofConfigTwo == 7 || moonRoofConfigTwo == 8 || moonRoofConfigTwo == 9)) {
            return moonRoofConfigTwo;
        }
        int moonRoofConfig = this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfig();
        if (moonRoofConfig != 0) {
            return moonRoofConfig;
        }
        return 0;
    }

    public int getMoonRoofConfigTwo() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMoonRoofConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfigTwo();
    }

    public int getMoonRoofPercentControlFlag() {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getMoonRoofPercentControlFlag();
        }
        Log.v(TAG, " getMoonRoofPercentControlFlag mForTestValue is 0 execute return");
        return -1;
    }

    public int getMoonRoofType() {
        Log.v(TAG, "getMoonRoofType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getMoonRoofType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMoonRoofType();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPowerLevel() {
        StringBuilder sbK = e.k("getPowerLevel is execute and current process ");
        sbK.append(this.mContext.getPackageName());
        Log.i(TAG, sbK.toString());
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getPowerLevel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        int powerLevel = this.mTsManagerImpl.getCarBodyManager().getPowerLevel();
        if (powerLevel == 0) {
            return 255;
        }
        if (powerLevel == 1) {
            return 0;
        }
        if (powerLevel == 2) {
            return 1;
        }
        if (powerLevel == 3) {
            return 2;
        }
        return powerLevel;
    }

    public int getReadyIndicatorLightState() {
        Log.v(TAG, "getReadyIndicatorLightState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().getReadyIndicatorLightState();
    }

    public String getRealAutoVIN() {
        Log.v(TAG, "getRealAutoVIN is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getRealAutoVin();
        }
        e.s(e.k("getRealAutoVIN mForTestValue is"), this.mForTest, " execute return", TAG);
        return "";
    }

    public int getRearLampLightState() {
        Log.v(TAG, "getRearLampLightState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRearLampLightState();
        }
        e.s(e.k("getRearLampLightState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getRearWindowblindInitState() {
        Log.v(TAG, "getRearWindowblindInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindInitState();
        }
        e.s(e.k("getRearWindowblindInitState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getRearWindowblindPosition() {
        Log.v(TAG, "getRearWindowblindPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindPosition();
        }
        e.s(e.k("getRearWindowblindPosition mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getRearWindowblindState() {
        Log.v(TAG, "getRearWindowblindState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindState();
        }
        e.s(e.k("getRearWindowblindState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getRearviewMirrorSupport() {
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorSupport();
        }
        Log.v(TAG, "getRearviewMirrorSupport mForTestValue is 0 execute return");
        return 0;
    }

    public float getRearviewMirrorX(int i) {
        Log.i(TAG, "getRearviewMirrorX is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorXLeft();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorXRight();
        }
        return 0.0f;
    }

    public float getRearviewMirrorY(int i) {
        Log.i(TAG, "getRearviewMirrorY is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorYLeft();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorYRight();
        }
        return 0.0f;
    }

    public int getRightSlideDoorAction() {
        Log.v(TAG, "getRightSlideDoorAction is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorAction();
        }
        e.s(e.k("getRightSlideDoorAction mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getRightSlideDoorPercent() {
        Log.v(TAG, "getRightSlideDoorPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorPercent();
        }
        e.s(e.k("getRightSlideDoorPercent mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getRightSlideDoorState() {
        Log.v(TAG, "getRightSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorState();
        }
        e.s(e.k("getRightSlideDoorState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getRightStrutMorotRunningState() {
        Log.v(TAG, "getRightStrutMorotRunningState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getRightStrutMorotRunningState();
        }
        e.s(e.k("getRightStrutMorotRunningState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getSeatLumbarSupportConfig(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            int seatLumbarSupportConfigMainRight = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainRight(i);
            int seatLumbarSupportConfigMainLeft = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainLeft(i);
            if (seatLumbarSupportConfigMainRight == 1 || seatLumbarSupportConfigMainLeft == 1) {
                return 1;
            }
        } else if (i == 2) {
            int seatLumbarSupportConfigCoPilotRight = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotRight(i);
            int seatLumbarSupportConfigCoPilotLeft = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotLeft(i);
            if (seatLumbarSupportConfigCoPilotRight == 1 || seatLumbarSupportConfigCoPilotLeft == 1) {
                return 1;
            }
        }
        return 0;
    }

    public int getSeatLumbarSupportConfigCoPilotLeft(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigCoPilotLeft mForTestValue is 0");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotLeft(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatLumbarSupportConfigCoPilotRight(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigCoPilotRight mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotRight(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatLumbarSupportConfigMainLeft(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigMainLeft mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainLeft(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatModeWakeupFeedbackState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatModeWakeupFeedbackState mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSeatModeWakeupFeedbackState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getShiftMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getShiftMode mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getShiftMode();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSmartVoiceLimit() {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getSmartVoiceLimit();
        }
        e.s(e.k(" getSmartVoiceLimit mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public double getSteeringWheelValue(int i) {
        Log.v(TAG, "getSteeringWheelValue is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getSteeringWheelValue mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0d;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelValue(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0d;
    }

    public float getSteeringWheelY() {
        Log.i(TAG, "getSteeringWheelY is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelY();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getSteeringWheelZ() {
        Log.i(TAG, "getSteeringWheelZ is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelZ();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public int getSunroofCloseNotice() {
        Log.v(TAG, "getSunroofCloseNotice is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofCloseNotice();
        }
        e.s(e.k("getSunroofCloseNotice mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getSunroofInitState() {
        Log.v(TAG, "getSunroofInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofInitState();
        }
        e.s(e.k("getSunroofInitState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getSunroofPosition() {
        Log.v(TAG, "getSunroofPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofPosition();
        }
        e.s(e.k("getSunroofPosition mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getSunroofState() {
        Log.v(TAG, "getSunroofState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofState();
        }
        e.s(e.k("getSunroofState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getSunroofWindowblindPosition() {
        Log.v(TAG, "getSunroofWindowblindPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofWindowblindPosition();
        }
        e.s(e.k("getSunroofWindowblindPosition mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getSystemConfig() {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getSystemConfig();
        }
        Log.v(TAG, " getSystemConfig mForTestValue is 0 execute return");
        return -1;
    }

    public int getTailDoorTypeConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTailDoorTypeConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getTailDoorTypeConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowAntiPinchConfig() {
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowAntiPinchConfig();
        }
        Log.v(TAG, "getWindowAntiPinchConfig mForTestValue is 0 execute return");
        return 0;
    }

    public int getWindowControlState(int i) {
        Log.v(TAG, "getWindowControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowControlState(i);
        }
        e.s(e.k("getWindowControlState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowInitState(int i) {
        Log.v(TAG, "getWindowInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getWindowThermalProtectionStateRearPanel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
    }

    public int getWindowInitStateLeftFront() {
        Log.v(TAG, "getWindowInitStateLeftFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
        }
        e.s(e.k("getWindowInitStateLeftFront mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowInitStateLeftRear() {
        Log.v(TAG, "getWindowInitStateLeftRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
        }
        e.s(e.k("getWindowInitStateLeftRear mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowInitStateRightFront() {
        Log.v(TAG, "getWindowInitStateRightFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
        }
        e.s(e.k("getWindowInitStateRightFront mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowInitStateRightRear() {
        Log.v(TAG, "getWindowInitStateRightRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
        }
        e.s(e.k("getWindowInitStateRightRear mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowLockState(int i) {
        Log.v(TAG, "getWindowLockState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowLockState(i);
        }
        e.s(e.k("getWindowLockState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowOpenPercent(int i) {
        Log.v(TAG, "getWindowOpenPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getWindowOpenPercent mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        switch (i) {
            case 1:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentLeftFront();
            case 2:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRightFront();
            case 3:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentLeftRear();
            case 4:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRightRear();
            case 5:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercenMoonRoof();
            case 6:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentPanel();
            case 7:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRearPanel();
            default:
                return 0;
        }
    }

    public int getWindowPercentageControlConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getWindowPercentageControlConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowPercentageControlConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowPermitState() {
        Log.v(TAG, "getWindowPermitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowPermitState();
        }
        e.s(e.k("getWindowPermitState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowState(int i) {
        Log.v(TAG, "getWindowState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getWindowState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i) {
        }
        return 0;
    }

    public int getWindowThermalProtectionState(int i) {
        Log.v(TAG, "getWindowThermalProtectionState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getWindowThermalProtectionStateRearPanel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        switch (i) {
            case 1:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
            case 2:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
            case 3:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
            case 4:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
            case 5:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateMoonRoof();
            case 6:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateSunshadePanel();
            case 7:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRearSunshadePanel();
            default:
                return 0;
        }
    }

    public int getWindowThermalProtectionStateLeftFront() {
        Log.v(TAG, "getWindowThermalProtectionStateLeftFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
        }
        e.s(e.k("getWindowThermalProtectionStateLeftFront mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowThermalProtectionStateLeftRear() {
        Log.v(TAG, "getWindowThermalProtectionStateLeftRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
        }
        e.s(e.k("getWindowThermalProtectionStateLeftRear mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowThermalProtectionStateMoonRoof() {
        Log.v(TAG, "getWindowThermalProtectionStateMoonRoof is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateMoonRoof();
        }
        e.s(e.k("getWindowThermalProtectionStateMoonRoof mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowThermalProtectionStatePanel() {
        Log.v(TAG, "getWindowThermalProtectionStatePanel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStatePanel();
        }
        e.s(e.k("getWindowThermalProtectionStatePanel mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowThermalProtectionStateRearPanel() {
        Log.v(TAG, "getWindowThermalProtectionStateRearPanel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRearPanel();
        }
        e.s(e.k("getWindowThermalProtectionStateRearPanel mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowThermalProtectionStateRightFront() {
        Log.v(TAG, "getWindowThermalProtectionStateRightFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
        }
        e.s(e.k("getWindowThermalProtectionStateRightFront mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowThermalProtectionStateRightRear() {
        Log.v(TAG, "getWindowThermalProtectionStateRightRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
        }
        e.s(e.k("getWindowThermalProtectionStateRightRear mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowblindInitState() {
        Log.v(TAG, "getWindowblindInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowblindInitState();
        }
        e.s(e.k("getWindowblindInitState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getWindowblindState() {
        Log.v(TAG, "getWindowblindState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowblindState();
        }
        e.s(e.k("getWindowblindState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int hasMessage(int i) {
        d.r("hasMessage is execute id ", i, TAG);
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().hasMessage(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public boolean isConnectSuccess() {
        return TsManagerImpl.isConnectSuccess();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoBodyworkListener> it = this.mAbsBydAutoBodyworkListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        registerListener(absBYDAutoBodyworkListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe bodywork listener ");
        sb.append(absBYDAutoBodyworkListener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
        if (absBYDAutoBodyworkListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoBodyworkListener(absBYDAutoBodyworkListener);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("registerListener mForTestValue is");
            e.s(sb2, this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (!this.mAbsBydAutoBodyworkListeners.contains(absBYDAutoBodyworkListener)) {
            this.mAbsBydAutoBodyworkListeners.add(absBYDAutoBodyworkListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoBodyworkListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void remoteWhistle() {
        Log.v(TAG, "remoteWhistle is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (1 == this.mForTest) {
            e.s(e.k("getSunroofState mForTestValue is"), this.mForTest, " execute return", TAG);
        } else if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().remoteWhistle();
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        int[] iArr2;
        if (iArr != null && (iArr2 = bYDAutoEventValue.intArrayValue) != null && iArr.length == iArr2.length) {
            d.v(iArr, e.k("more param event type="), TAG);
            boolean zCreateWindowStateData = true;
            if (iArr.length == 1) {
                if (iArr[0] == 1763 || iArr[0] == 1778 || iArr[0] == 2034 || iArr[0] == 2060) {
                    int i = iArr[0];
                    if (i == 1763) {
                        return setWindowControlState(1, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i == 1778) {
                        return setWindowControlState(3, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i == 2034) {
                        return setWindowControlState(2, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i == 2060) {
                        return setWindowControlState(4, bYDAutoEventValue.intArrayValue[0]);
                    }
                    Log.v(TAG, "no have control window event.");
                    return -2147482648;
                }
            } else if (iArr.length > 1) {
                int[] iArr3 = {0, 0, 0, 0};
                for (int i2 = 0; i2 < iArr.length && (zCreateWindowStateData = createWindowStateData(iArr[i2], bYDAutoEventValue.intArrayValue[i2], iArr3)); i2++) {
                }
                if (zCreateWindowStateData) {
                    return setWindowControlState(iArr3);
                }
            }
        }
        return super.set(iArr, bYDAutoEventValue);
    }

    public int setAllDoorsLock(int i) {
        Log.v(TAG, "setAllDoorsLock is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setAllDoorsLock(i);
        }
        e.s(e.k("setAllDoorsLock mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setAllDoorsLockScene(int i) {
        if (1 == this.mForTest) {
            e.s(e.k(" setAllDoorsLockScene mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAllDoorsLockScene(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAntiTheftStatus(int i) {
        Log.v(TAG, "setAntiTheftStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setAntiTheftStatus(i);
        }
        e.s(e.k("setAntiTheftStatus mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setAutoExternalRearMirrorFoldState(int i) {
        Log.v(TAG, "setAutoExternalRearMirrorFoldState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        return this.mTsManagerImpl.getCarBodyManager().setAutoExternalRearMirrorFoldState(i);
    }

    public int setBackDoorHeightPosition(int i) {
        Log.v(TAG, "setBackDoorHeightPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setBackDoorHeightPosition(i);
        }
        e.s(e.k("setBackDoorHeightPosition mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setBackDoorHeightPositionMyCar(int i) {
        Log.v(TAG, "setBackDoorHeightPositionMyCar is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setBackDoorHeightPositionMyCar(i);
        }
        e.s(e.k("setBackDoorHeightPositionMyCar mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setBackDoorOpenedHeight(int i) {
        Log.i(TAG, "setBackDoorOpenedHeight is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBackDoorOpenedHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setBackDoorOpenedHeight(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveDistanceShowMode(int i) {
        Log.v(TAG, "setDriveDistanceShowMode is execute");
        if (1 == this.mForTest) {
            e.s(e.k("setDriveDistanceShowMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowMode(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setElecBackDoorControlState(int i) {
        Log.v(TAG, "setElecBackDoorControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setElecBackDoorControlState(i);
        }
        e.s(e.k("setElecBackDoorControlState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setElecSlideDoorState(int i) {
        String str;
        if (1 == this.mForTest) {
            str = " setElecSlideDoorState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setElecSlideDoorState(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return -1;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        String str;
        int iIntValue;
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
        switch (i) {
            case 1688:
                setBackDoorHeightPosition(iIntValue);
                sb = new StringBuilder();
                sb.append("setElecBackDoorControlState");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 1715:
                setElecBackDoorControlState(iIntValue);
                sb = new StringBuilder();
                sb.append("setElecBackDoorControlState");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 1721:
                setAllDoorsLock(iIntValue);
                sb = new StringBuilder();
                str2 = "setDoorLockStatusAll";
                sb.append(str2);
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 1766:
                setWindowOpenPercent(1, iIntValue);
                sb = new StringBuilder();
                sb.append("setWindowOpenPercent");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 1781:
                setWindowOpenPercent(3, iIntValue);
                sb = new StringBuilder();
                sb.append("setWindowOpenPercent");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 1783:
                settingLuggage(iIntValue);
                sb = new StringBuilder();
                str2 = "settingLuggage";
                sb.append(str2);
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 1784:
                setLeftSlideDoorVoiceControlState(iIntValue);
                sb = new StringBuilder();
                str2 = "setLeftSlideDoorVoiceControlState";
                sb.append(str2);
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 1790:
                setWindowOpenPercent(5, iIntValue);
                sb = new StringBuilder();
                sb.append("setWindowOpenPercent");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 2004:
                turnRearviewMirror(iIntValue);
                sb = new StringBuilder();
                str2 = "turnRearviewMirror:";
                sb.append(str2);
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 2013:
                setWindowOpenPercent(7, iIntValue);
                sb = new StringBuilder();
                sb.append("setWindowOpenPercent");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 2015:
                setAutoExternalRearMirrorFoldState(iIntValue);
                sb = new StringBuilder();
                str2 = "setAutoExternalRearMirrorFoldS";
                sb.append(str2);
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 2037:
                setWindowOpenPercent(2, iIntValue);
                sb = new StringBuilder();
                sb.append("setWindowOpenPercent");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 2063:
                setWindowOpenPercent(4, iIntValue);
                sb = new StringBuilder();
                sb.append("setWindowOpenPercent");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 2067:
                setRightSlideDoorVoiceControlState(iIntValue);
                sb = new StringBuilder();
                str2 = "setRightSlideDoorVoiceControlState";
                sb.append(str2);
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 2085:
                setWindowOpenPercent(6, iIntValue);
                sb = new StringBuilder();
                sb.append("setWindowOpenPercent");
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 2135:
                turnSteeringWheel(iIntValue);
                sb = new StringBuilder();
                str2 = "turnSteeringWheel:";
                sb.append(str2);
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 4600:
                setBackDoorHeightPositionMyCar(iIntValue);
                sb = new StringBuilder();
                str2 = "setBackDoorHeightPosition";
                sb.append(str2);
                sb.append(iIntValue);
                Log.v(TAG, sb.toString());
                return 0;
            case 4641:
                setWaistSupportAdjustment(1, iIntValue);
                return 0;
            case 4642:
                setWaistSupportAdjustment(2, iIntValue);
                return 0;
            case 4644:
                setSeatModeControl(iIntValue);
                return 0;
            default:
                str = "case fail ! please check out";
                Log.v(TAG, str);
                return -1;
        }
    }

    public int setLeftSlideDoorVoiceControlState(int i) {
        Log.v(TAG, "setLeftSlideDoorVoiceControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setLeftSlideDoorVoiceControlState(i);
        }
        e.s(e.k("setLeftSlideDoorVoiceControlState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setModeCommand(int[] iArr) {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setModeCommand(iArr);
        }
        Log.v(TAG, " setModeCommand mForTestValue is 0 execute return");
        return -1;
    }

    public int setMoonRoofType(int i) {
        Log.v(TAG, "setMoonRoofType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            e.s(e.k("setMoonRoofType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setMoonRoofType(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRightSlideDoorVoiceControlState(int i) {
        Log.v(TAG, "setRightSlideDoorVoiceControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setRightSlideDoorVoiceControlState(i);
        }
        e.s(e.k("setRightSlideDoorVoiceControlState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setSeatModeControl(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSeatModeControl mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setSeatModeControl(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setSideWindowShadeState(int i) {
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setSideWindowShadeState(i);
        }
        Log.v(TAG, " setSideWindowShadeState mForTestValue is 0 execute return");
        return -1;
    }

    public int setSunroofAndSunshade(int i) {
        if (1 == this.mForTest) {
            e.s(e.k(" setSunroofAndSunshade mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setSunroofAndSunshade(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setVoiceCtlMoonRoofS(int i) {
        Log.i(TAG, "setVoiceCtlMoonRoofS is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setVoiceCtlMoonRoofS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setVoiceCtlMoonRoofS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setVoiceCtlSunshadePanelS(int i) {
        Log.i(TAG, "setVoiceCtlSunshadePanelS is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setVoiceCtlSunshadePanelS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setVoiceCtlSunshadePanelS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setWaistSupportAdjustment(int i, int i2) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setWaistSupportAdjustment mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentMain(i, i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentCoPilot(i, i2);
        }
        return 0;
    }

    public int setWaistSupportAdjustmentCoPilot(int i, int i2) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setWaistSupportAdjustmentCoPilot mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentCoPilot(i, i2);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setWindowControlState(int i, int i2) {
        int[] iArr = {0, 0, 0, 0};
        if (i == 1) {
            iArr[0] = i2;
        } else if (i == 2) {
            iArr[1] = i2;
        } else if (i == 3) {
            iArr[2] = i2;
        } else if (i != 4) {
            Log.v(TAG, "no have area.");
        } else {
            iArr[3] = i2;
        }
        return setWindowControlState(iArr);
    }

    public int setWindowControlState(int[] iArr) {
        Log.v(TAG, "setWindowControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setWindowControlState(iArr);
        }
        e.s(e.k("setWindowControlState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setWindowLockState(int i, int i2) {
        Log.v(TAG, "setWindowLockState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().setWindowLockState(i, i2);
        }
        this.mTestCallback.enqueueMessage(4001, i2);
        StringBuilder sb = new StringBuilder();
        sb.append("setWindowLockState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setWindowOpenPercent(int i, int i2) {
        Log.v(TAG, "setWindowOpenPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(4000, i, i2);
            StringBuilder sb = new StringBuilder();
            sb.append("setWindowOpenPercent mForTestValue is");
            e.s(sb, this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i) {
        }
        return 0;
    }

    public int setWindowsMode(int i) {
        if (1 == this.mForTest) {
            e.s(e.k(" setWindowsMode mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setWindowsMode(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public void settingLuggage(int i) {
        Log.v(TAG, "settingLuggage is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            e.s(e.k("settingLuggage mForTestValue is"), this.mForTest, " execute return", TAG);
        } else if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().settingLuggage(i);
        }
    }

    public void turnRearviewMirror(int i) {
        Log.i(TAG, "turnRearviewMirror is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().turnRearviewMirror(i);
        }
    }

    public void turnSteeringWheel(int i) {
        Log.i(TAG, "turnSteeringWheel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().turnSteeringWheel(i);
        }
    }

    public void unregisterListener(IBYDAutoListener iBYDAutoListener) {
    }

    public void unregisterListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        Log.v(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(absBYDAutoBodyworkListener);
            StringBuilder sb = new StringBuilder();
            sb.append("unregisterListener mForTestValue is");
            e.s(sb, this.mForTest, " execute return", TAG);
            return;
        }
        Log.v(TAG, "unsubscribeBodyworkCallback: " + absBYDAutoBodyworkListener);
        if (absBYDAutoBodyworkListener == null) {
            return;
        }
        this.mAbsBydAutoBodyworkListeners.remove(absBYDAutoBodyworkListener);
        unregisterConnectListener(absBYDAutoBodyworkListener);
    }
}
