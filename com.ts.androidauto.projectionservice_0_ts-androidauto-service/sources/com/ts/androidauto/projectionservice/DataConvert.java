package com.ts.androidauto.projectionservice;

import android.text.TextUtils;
import com.google.android.projection.proto.Protos;
import com.ts.androidauto.aap.common.UiConfig;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.data.NavigationDestDistanceData;
import com.ts.androidauto.aap.data.NavigationDirectionData;
import com.ts.androidauto.aap.data.NavigationLaneData;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.aap.data.NavigationStepData;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice;
import com.ts.androidauto.sdk.aidl.data.IfNavigationDestDistanceData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationDirectionData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationLaneData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationPositionData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationStateData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationStepData;
import com.ts.androidauto.sdk.aidl.data.MediaMetadata;
import com.ts.androidauto.sdk.aidl.data.UiConfig;
import com.ts.androidauto.sdk.aidl.data.VehicleInfo;
import com.ts.androidauto.sdk.common.VehicleConst;
import com.ts.androidauto.service.common.DeviceConnectionManagerModel;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.dcm.data.ConnectionDevice;

/* JADX INFO: loaded from: classes2.dex */
public class DataConvert {
    private static final String TAG = "AAP" + DataConvert.class.getSimpleName();
    private static boolean USB_HISTORY_CAP_INVALID = true;

    public static VehicleInfo toLocalVehicleInfo(com.ts.androidauto.sdk.aidl.data.VehicleInfo in) {
        if (in == null) {
            return null;
        }
        VehicleInfo dst = new VehicleInfo();
        dst.mAapVideoConfig480p = convertVideoConfig(in.mAapVideoConfig480p);
        dst.mAapVideoConfig720p = convertVideoConfig(in.mAapVideoConfig720p);
        dst.mAapVideoConfig1080p = convertVideoConfig(in.mAapVideoConfig1080p);
        dst.mAapClusterVideoConfig480p = convertVideoConfig(in.mAapClusterVideoConfig480p);
        dst.mAapClusterVideoConfig720p = convertVideoConfig(in.mAapClusterVideoConfig720p);
        dst.mAapClusterVideoConfig1080pSmall = convertVideoConfig(in.mAapClusterVideoConfig1080pSmall);
        dst.mAapClusterVideoConfig1080pFull = convertVideoConfig(in.mAapClusterVideoConfig1080pFull);
        dst.mTouchScreenWidth = in.mTouchScreenWidth;
        dst.mTouchScreenHeight = in.mTouchScreenHeight;
        dst.mAapTouchPointOffsetX = in.mAapTouchPointOffsetX;
        dst.mAapTouchPointOffsetY = in.mAapTouchPointOffsetY;
        dst.mViewingDistance = in.mViewingDistance;
        dst.mClusterViewingDistance = in.mClusterViewingDistance;
        dst.mVehicleMaker = in.mVehicleMaker;
        dst.mVehicleModel = in.mVehicleModel;
        dst.mVehicleYear = in.mVehicleYear;
        dst.mVehicleId = in.mVehicleId;
        dst.mVehicleDriverPosition = convertDrivePosition(in.mVehicleDriverPosition);
        dst.mHeadUnitMaker = in.mHeadUnitMaker;
        dst.mHeadUnitModel = in.mHeadUnitModel;
        dst.mHeadUnitSwBuild = in.mHeadUnitSwBuild;
        dst.mHeadUnitSwVer = in.mHeadUnitSwVer;
        dst.mDisplayName = in.mDisplayName;
        dst.mAoaSerialNumber = in.mAoaSerialNumber;
        dst.setFuelTypes(convertFuelTypeList(in.mVehicleFuelTypeList));
        dst.setEvConnectorTypes(convertEvConnectorTypeList(in.mVehicleEvConnectorTypeList));
        dst.setSupportKeyCodes(convertKeyCodeList(in.mSupportHardKeyList));
        dst.mIsTruck = in.mIsTruck;
        dst.mIsSupportWireless = in.mIsSupportWireless;
        dst.mIsSupportDisplayNaviDataUpdate = in.mIsSupportDisplayNaviDataUpdate;
        dst.mIsNeedShowProjectionOnPhoneCall = in.mIsNeedShowProjectionOnPhoneCall;
        dst.mIsSupportNativeNavigation = in.mIsSupportNativeNavigation;
        dst.mRootCert = in.mRootCert;
        dst.mClientCert = in.mClientCert;
        dst.mPrivateKey = in.mPrivateKey;
        dst.mIsNeedDecryptCert = in.mIsNeedDecryptCert;
        dst.mDisplayActivityPackageName = in.mDisplayActivityPackageName;
        dst.mDisplayActivityClassName = in.mDisplayActivityClassName;
        dst.mIsSupportClusterProjection = in.mIsSupportClusterProjection;
        dst.mIsUpdateClusterScreenSize = in.mIsUpdateClusterScreenSize;
        return dst;
    }

    public static UiConfig toLocalUiConfig(com.ts.androidauto.sdk.aidl.data.UiConfig in) {
        if (in == null) {
            return null;
        }
        UiConfig dst = new UiConfig();
        dst.mAapClusterUiConfig480p = convertClusterUiConfig(in.mAapClusterUiConfig480p);
        dst.mAapClusterUiConfig720p = convertClusterUiConfig(in.mAapClusterUiConfig720p);
        dst.mAapClusterUiConfig1080p = convertClusterUiConfig(in.mAapClusterUiConfig1080p);
        return dst;
    }

    private static VehicleInfo.AapVideoConfig convertVideoConfig(VehicleInfo.AapVideoConfig in) {
        VehicleInfo.AapVideoConfig dst = new VehicleInfo.AapVideoConfig();
        dst.mAapDisplayAreaWidth = in.mAapDisplayAreaWidth;
        dst.mAapDisplayAreaHeight = in.mAapDisplayAreaHeight;
        dst.mAapDisplayAreaMarginLeft = in.mAapDisplayAreaMarginLeft;
        dst.mAapDisplayAreaMarginTop = in.mAapDisplayAreaMarginTop;
        dst.mAapResolutionWidth = in.mAapResolutionWidth;
        dst.mAapResolutionHeight = in.mAapResolutionHeight;
        dst.mAapResolutionReportDpi = in.mAapResolutionReportDpi;
        dst.mAapResolutionRealDpi = in.mAapResolutionRealDpi;
        dst.mAapResolutionPixelAspect = in.mAapResolutionPixelAspect;
        dst.mAapSurfaceViewWidth = in.mAapSurfaceViewWidth;
        dst.mAapSurfaceViewHeight = in.mAapSurfaceViewHeight;
        dst.mAapSurfaceViewScrollX = in.mAapSurfaceViewScrollX;
        dst.mAapSurfaceViewScrollY = in.mAapSurfaceViewScrollY;
        dst.mTouchScreenWidth = in.mTouchScreenWidth;
        dst.mTouchScreenHeight = in.mTouchScreenHeight;
        dst.mAapTouchPointOffsetX = in.mAapTouchPointOffsetX;
        dst.mAapTouchPointOffsetY = in.mAapTouchPointOffsetY;
        dst.mAapContentInsetsLeft = in.mAapContentInsetsLeft;
        dst.mAapContentInsetsRight = in.mAapContentInsetsRight;
        dst.mAapContentInsetsTop = in.mAapContentInsetsTop;
        dst.mAapContentInsetsBottom = in.mAapContentInsetsBottom;
        dst.mAapStableContentInsetsLeft = in.mAapStableContentInsetsLeft;
        dst.mAapStableContentInsetsRight = in.mAapStableContentInsetsRight;
        dst.mAapStableContentInsetsTop = in.mAapStableContentInsetsTop;
        dst.mAapStableContentInsetsBottom = in.mAapStableContentInsetsBottom;
        dst.mAapMarginLeft = in.mAapMarginLeft;
        dst.mAapMarginTop = in.mAapMarginTop;
        dst.mAapMarginRight = in.mAapMarginRight;
        dst.mAapMarginBottom = in.mAapMarginBottom;
        return dst;
    }

    private static UiConfig.AapClusterUiConfig convertClusterUiConfig(UiConfig.AapClusterUiConfig in) {
        UiConfig.AapClusterUiConfig dst = new UiConfig.AapClusterUiConfig();
        dst.mAapContentInsetsLeft = in.mAapContentInsetsLeft;
        dst.mAapContentInsetsRight = in.mAapContentInsetsRight;
        dst.mAapContentInsetsTop = in.mAapContentInsetsTop;
        dst.mAapContentInsetsBottom = in.mAapContentInsetsBottom;
        dst.mAapStableContentInsetsLeft = in.mAapStableContentInsetsLeft;
        dst.mAapStableContentInsetsRight = in.mAapStableContentInsetsRight;
        dst.mAapStableContentInsetsTop = in.mAapStableContentInsetsTop;
        dst.mAapStableContentInsetsBottom = in.mAapStableContentInsetsBottom;
        dst.mAapMarginLeft = in.mAapMarginLeft;
        dst.mAapMarginTop = in.mAapMarginTop;
        dst.mAapMarginRight = in.mAapMarginRight;
        dst.mAapMarginBottom = in.mAapMarginBottom;
        return dst;
    }

    private static Protos.DriverPosition convertDrivePosition(VehicleConst.AapVehicleDrivePosition in) {
        Protos.DriverPosition dst = Protos.DriverPosition.DRIVER_POSITION_UNKNOWN;
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleDrivePosition[in.ordinal()];
        if (i == 1) {
            Protos.DriverPosition dst2 = Protos.DriverPosition.DRIVER_POSITION_LEFT;
            return dst2;
        }
        if (i == 2) {
            Protos.DriverPosition dst3 = Protos.DriverPosition.DRIVER_POSITION_RIGHT;
            return dst3;
        }
        if (i == 3) {
            Protos.DriverPosition dst4 = Protos.DriverPosition.DRIVER_POSITION_CENTER;
            return dst4;
        }
        return dst;
    }

    private static Protos.FuelType[] convertFuelTypeList(VehicleConst.AapVehicleFuelType[] in) {
        if (in == null) {
            return null;
        }
        Protos.FuelType[] dst = new Protos.FuelType[in.length];
        for (int i = 0; i < in.length; i++) {
            dst[i] = convertFuelType(in[i]);
        }
        return dst;
    }

    private static Protos.FuelType convertFuelType(VehicleConst.AapVehicleFuelType in) {
        Protos.FuelType dst = Protos.FuelType.FUEL_TYPE_UNKNOWN;
        switch (AnonymousClass1.$SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[in.ordinal()]) {
            case 1:
                Protos.FuelType dst2 = Protos.FuelType.FUEL_TYPE_UNLEADED;
                return dst2;
            case 2:
                Protos.FuelType dst3 = Protos.FuelType.FUEL_TYPE_LEADED;
                return dst3;
            case 3:
                Protos.FuelType dst4 = Protos.FuelType.FUEL_TYPE_DIESEL_1;
                return dst4;
            case 4:
                Protos.FuelType dst5 = Protos.FuelType.FUEL_TYPE_DIESEL_2;
                return dst5;
            case 5:
                Protos.FuelType dst6 = Protos.FuelType.FUEL_TYPE_BIODIESEL;
                return dst6;
            case 6:
                Protos.FuelType dst7 = Protos.FuelType.FUEL_TYPE_E85;
                return dst7;
            case 7:
                Protos.FuelType dst8 = Protos.FuelType.FUEL_TYPE_LPG;
                return dst8;
            case 8:
                Protos.FuelType dst9 = Protos.FuelType.FUEL_TYPE_CNG;
                return dst9;
            case 9:
                Protos.FuelType dst10 = Protos.FuelType.FUEL_TYPE_LNG;
                return dst10;
            case 10:
                Protos.FuelType dst11 = Protos.FuelType.FUEL_TYPE_ELECTRIC;
                return dst11;
            case 11:
                Protos.FuelType dst12 = Protos.FuelType.FUEL_TYPE_HYDROGEN;
                return dst12;
            case 12:
                Protos.FuelType dst13 = Protos.FuelType.FUEL_TYPE_OTHER;
                return dst13;
            default:
                return dst;
        }
    }

    private static Protos.EvConnectorType[] convertEvConnectorTypeList(VehicleConst.AapEvConnectorType[] in) {
        if (in == null) {
            return null;
        }
        Protos.EvConnectorType[] dst = new Protos.EvConnectorType[in.length];
        for (int i = 0; i < in.length; i++) {
            dst[i] = convertEvConnectorType(in[i]);
        }
        return dst;
    }

    private static Protos.EvConnectorType convertEvConnectorType(VehicleConst.AapEvConnectorType in) {
        Protos.EvConnectorType dst = Protos.EvConnectorType.EV_CONNECTOR_TYPE_UNKNOWN;
        switch (AnonymousClass1.$SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[in.ordinal()]) {
            case 1:
                Protos.EvConnectorType dst2 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_J1772;
                return dst2;
            case 2:
                Protos.EvConnectorType dst3 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_MENNEKES;
                return dst3;
            case 3:
                Protos.EvConnectorType dst4 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_CHADEMO;
                return dst4;
            case 4:
                Protos.EvConnectorType dst5 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_COMBO_1;
                return dst5;
            case 5:
                Protos.EvConnectorType dst6 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_COMBO_2;
                return dst6;
            case 6:
                Protos.EvConnectorType dst7 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_TESLA_ROADSTER;
                return dst7;
            case 7:
                Protos.EvConnectorType dst8 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_TESLA_HPWC;
                return dst8;
            case 8:
                Protos.EvConnectorType dst9 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_TESLA_SUPERCHARGER;
                return dst9;
            case 9:
                Protos.EvConnectorType dst10 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_GBT;
                return dst10;
            case 10:
                Protos.EvConnectorType dst11 = Protos.EvConnectorType.EV_CONNECTOR_TYPE_OTHER;
                return dst11;
            default:
                return dst;
        }
    }

    private static Protos.KeyCode[] convertKeyCodeList(VehicleConst.AapHardkeyEvent[] in) {
        if (in == null) {
            return null;
        }
        Protos.KeyCode[] dst = new Protos.KeyCode[in.length];
        for (int i = 0; i < in.length; i++) {
            dst[i] = convertKeyCode(in[i]);
        }
        return dst;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.projectionservice.DataConvert$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType;
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent;
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleDrivePosition;
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType;

        static {
            int[] iArr = new int[VehicleConst.AapHardkeyEvent.values().length];
            $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent = iArr;
            try {
                iArr[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_TEL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_NAVIGATION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_HOME.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_BACK.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY_PAUSE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PAUSE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PREVIOUS.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_NEXT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_RIGHT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_LEFT.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_UP.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_DOWN.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_CENTER.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_ROTARY_CONTROLLER.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            int[] iArr2 = new int[VehicleConst.AapEvConnectorType.values().length];
            $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType = iArr2;
            try {
                iArr2[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_J1772.ordinal()] = 1;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_MENNEKES.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_CHADEMO.ordinal()] = 3;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_COMBO_1.ordinal()] = 4;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_COMBO_2.ordinal()] = 5;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_ROADSTER.ordinal()] = 6;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_HPWC.ordinal()] = 7;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_SUPERCHARGER.ordinal()] = 8;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_GBT.ordinal()] = 9;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_OTHER.ordinal()] = 10;
            } catch (NoSuchFieldError e27) {
            }
            int[] iArr3 = new int[VehicleConst.AapVehicleFuelType.values().length];
            $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType = iArr3;
            try {
                iArr3[VehicleConst.AapVehicleFuelType.AAP_FUEL_UNLEADED_GASOLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e28) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_LEADED_GASOLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e29) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_DIESEL_1.ordinal()] = 3;
            } catch (NoSuchFieldError e30) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_DIESEL_2.ordinal()] = 4;
            } catch (NoSuchFieldError e31) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_BIODIESEL.ordinal()] = 5;
            } catch (NoSuchFieldError e32) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_E85.ordinal()] = 6;
            } catch (NoSuchFieldError e33) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_LPG.ordinal()] = 7;
            } catch (NoSuchFieldError e34) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_CNG.ordinal()] = 8;
            } catch (NoSuchFieldError e35) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_LNG.ordinal()] = 9;
            } catch (NoSuchFieldError e36) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_ELECTRIC.ordinal()] = 10;
            } catch (NoSuchFieldError e37) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_HYDROGEN.ordinal()] = 11;
            } catch (NoSuchFieldError e38) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_OTHER.ordinal()] = 12;
            } catch (NoSuchFieldError e39) {
            }
            int[] iArr4 = new int[VehicleConst.AapVehicleDrivePosition.values().length];
            $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleDrivePosition = iArr4;
            try {
                iArr4[VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e40) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleDrivePosition[VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e41) {
            }
            try {
                $SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapVehicleDrivePosition[VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e42) {
            }
        }
    }

    private static Protos.KeyCode convertKeyCode(VehicleConst.AapHardkeyEvent in) {
        Protos.KeyCode dst = Protos.KeyCode.KEYCODE_UNKNOWN;
        switch (AnonymousClass1.$SwitchMap$com$ts$androidauto$sdk$common$VehicleConst$AapHardkeyEvent[in.ordinal()]) {
            case 1:
                Protos.KeyCode dst2 = Protos.KeyCode.KEYCODE_MEDIA;
                return dst2;
            case 2:
                Protos.KeyCode dst3 = Protos.KeyCode.KEYCODE_TEL;
                return dst3;
            case 3:
                Protos.KeyCode dst4 = Protos.KeyCode.KEYCODE_NAVIGATION;
                return dst4;
            case 4:
                Protos.KeyCode dst5 = Protos.KeyCode.KEYCODE_HOME;
                return dst5;
            case 5:
                Protos.KeyCode dst6 = Protos.KeyCode.KEYCODE_SEARCH;
                return dst6;
            case 6:
                Protos.KeyCode dst7 = Protos.KeyCode.KEYCODE_BACK;
                return dst7;
            case 7:
                Protos.KeyCode dst8 = Protos.KeyCode.KEYCODE_MEDIA_PLAY_PAUSE;
                return dst8;
            case 8:
                Protos.KeyCode dst9 = Protos.KeyCode.KEYCODE_MEDIA_PLAY;
                return dst9;
            case 9:
                Protos.KeyCode dst10 = Protos.KeyCode.KEYCODE_MEDIA_PAUSE;
                return dst10;
            case 10:
                Protos.KeyCode dst11 = Protos.KeyCode.KEYCODE_MEDIA_PREVIOUS;
                return dst11;
            case 11:
                Protos.KeyCode dst12 = Protos.KeyCode.KEYCODE_MEDIA_NEXT;
                return dst12;
            case 12:
                Protos.KeyCode dst13 = Protos.KeyCode.KEYCODE_DPAD_RIGHT;
                return dst13;
            case 13:
                Protos.KeyCode dst14 = Protos.KeyCode.KEYCODE_DPAD_LEFT;
                return dst14;
            case 14:
                Protos.KeyCode dst15 = Protos.KeyCode.KEYCODE_DPAD_UP;
                return dst15;
            case 15:
                Protos.KeyCode dst16 = Protos.KeyCode.KEYCODE_DPAD_DOWN;
                return dst16;
            case 16:
                Protos.KeyCode dst17 = Protos.KeyCode.KEYCODE_DPAD_CENTER;
                return dst17;
            case 17:
                Protos.KeyCode dst18 = Protos.KeyCode.KEYCODE_ROTARY_CONTROLLER;
                return dst18;
            default:
                return dst;
        }
    }

    public static int convertGalKeyCode(int in) {
        if (in >= 0 && in < VehicleConst.AapHardkeyEvent.values().length) {
            VehicleConst.AapHardkeyEvent key = VehicleConst.AapHardkeyEvent.values()[in];
            return convertKeyCode(key).ordinal();
        }
        LogUtil.info(TAG, "convertGalKeyCode invalid keycode for AapHardkeyEvent. key=" + in);
        return Protos.KeyCode.KEYCODE_UNKNOWN.ordinal();
    }

    public static IfNavigationStateData toSdkNavigationStateData(NavigationStateData navigationStateData) {
        if (navigationStateData == null) {
            return null;
        }
        IfNavigationStateData retNavigationStateData = new IfNavigationStateData();
        retNavigationStateData.setStepData(convertNavigationStepData(navigationStateData.getStepData()));
        retNavigationStateData.setNavigationDestinations(navigationStateData.getNavigationDestinations());
        return retNavigationStateData;
    }

    private static List<IfNavigationStepData> convertNavigationStepData(List<NavigationStepData> stepDataList) {
        List<IfNavigationStepData> ifStepDataList = new ArrayList<>();
        if (stepDataList != null) {
            for (int i = 0; i < stepDataList.size(); i++) {
                NavigationStepData stepData = stepDataList.get(i);
                IfNavigationStepData ifStepData = new IfNavigationStepData();
                ifStepData.setLaneData(convertNavigationLaneData(stepData.getLaneData()));
                ifStepData.setNavigationCue(stepData.getNavigationCue());
                ifStepData.setRoad(stepData.getRoad());
                ifStepData.setTurnAngle(stepData.getTurnAngle());
                ifStepData.setTurnNumber(stepData.getTurnNumber());
                ifStepData.setEvent(stepData.getEvent());
                ifStepDataList.add(ifStepData);
            }
        }
        return ifStepDataList;
    }

    private static List<IfNavigationLaneData> convertNavigationLaneData(List<NavigationLaneData> laneDataList) {
        List<IfNavigationLaneData> ifLaneDataList = new ArrayList<>();
        if (laneDataList != null) {
            for (int i = 0; i < laneDataList.size(); i++) {
                NavigationLaneData laneData = laneDataList.get(i);
                IfNavigationLaneData ifLaneData = new IfNavigationLaneData();
                ifLaneData.setIfNavigationDirectionData(convertNavigationDirectionData(laneData.getNavigationDirectionData()));
                ifLaneDataList.add(ifLaneData);
            }
        }
        return ifLaneDataList;
    }

    private static List<IfNavigationDirectionData> convertNavigationDirectionData(List<NavigationDirectionData> directionDataList) {
        List<IfNavigationDirectionData> ifDirectionDataList = new ArrayList<>();
        if (directionDataList != null) {
            for (int i = 0; i < directionDataList.size(); i++) {
                NavigationDirectionData directionData = directionDataList.get(i);
                IfNavigationDirectionData ifDirectionData = new IfNavigationDirectionData();
                ifDirectionData.setHighlighted(directionData.getHighlighted());
                ifDirectionData.setShape(directionData.getShape());
                ifDirectionDataList.add(ifDirectionData);
            }
        }
        return ifDirectionDataList;
    }

    public static IfNavigationPositionData toSdkNavigationPositionData(NavigationPositionData navigationPositionData) {
        if (navigationPositionData == null) {
            return null;
        }
        IfNavigationPositionData retNavigationPositionData = new IfNavigationPositionData();
        retNavigationPositionData.setIfNavigationDestDistanceData(convertNavigationDestDistanceData(navigationPositionData.getNavigationDestDistanceData()));
        retNavigationPositionData.setCurrentRoad(navigationPositionData.getCurrentRoad());
        retNavigationPositionData.setDisplayUnits(navigationPositionData.getDisplayUnits());
        retNavigationPositionData.setMeters(navigationPositionData.getMeters());
        retNavigationPositionData.setDisplayValue(navigationPositionData.getDisplayValue());
        retNavigationPositionData.setTimeSeconds(navigationPositionData.getTimeSeconds());
        return retNavigationPositionData;
    }

    private static List<IfNavigationDestDistanceData> convertNavigationDestDistanceData(List<NavigationDestDistanceData> destDistanceDataList) {
        List<IfNavigationDestDistanceData> ifDestDistanceDataList = new ArrayList<>();
        if (destDistanceDataList != null) {
            for (int i = 0; i < destDistanceDataList.size(); i++) {
                NavigationDestDistanceData destDistanceData = destDistanceDataList.get(i);
                IfNavigationDestDistanceData ifDestDistanceData = new IfNavigationDestDistanceData();
                ifDestDistanceData.setDisplayUnits(destDistanceData.getDisplayUnits());
                ifDestDistanceData.setDisplayValue(destDistanceData.getDisplayValue());
                ifDestDistanceData.setMeters(destDistanceData.getMeters());
                ifDestDistanceData.setEstimatedTime(destDistanceData.getEstimatedTime());
                ifDestDistanceData.setTimeSeconds(destDistanceData.getTimeSeconds());
                ifDestDistanceDataList.add(ifDestDistanceData);
            }
        }
        return ifDestDistanceDataList;
    }

    public static MediaMetadata dataToMediaMetadata(com.ts.androidauto.aap.data.MediaMetadata mediaMetadata) {
        if (mediaMetadata == null) {
            return null;
        }
        MediaMetadata retMediaMetadata = new MediaMetadata();
        retMediaMetadata.setSong(mediaMetadata.getSong());
        retMediaMetadata.setAlbum(mediaMetadata.getAlbum());
        retMediaMetadata.setArtist(mediaMetadata.getArtist());
        retMediaMetadata.setDurationSeconds(mediaMetadata.getDurationSeconds());
        retMediaMetadata.setArtworkPath(mediaMetadata.getArtworkPath());
        retMediaMetadata.setAlbumArt(mediaMetadata.getAlbumArt());
        retMediaMetadata.setComposer(mediaMetadata.getComposer());
        retMediaMetadata.setGenrel(mediaMetadata.getGenrel());
        retMediaMetadata.setYear(mediaMetadata.getYear());
        retMediaMetadata.setTrackNumber(mediaMetadata.getTrackNumber());
        retMediaMetadata.setTotalTracks(mediaMetadata.getTotalTracks());
        retMediaMetadata.setShuffleMode(mediaMetadata.getShuffleMode());
        retMediaMetadata.setRepeatMode(mediaMetadata.getRepeatMode());
        return retMediaMetadata;
    }

    public static AndroidAutoDevice convertAndroidAutoDevice(ConnectionDevice device) {
        if (device == null) {
            return null;
        }
        AndroidAutoDevice dev = new AndroidAutoDevice();
        dev.setDeviceName(device.getFriendlyName());
        dev.setUsbSerialNumber(device.getUsbSerialNumber());
        dev.setBtAddr(device.getBtAddr());
        dev.setWifiAddr(device.getWifiAddr());
        dev.setProjectionConnectedTimeMillis(device.getUsbAndroidAutoConnectedTime() > device.getWirelessAndroidAutoConnectedTime() ? device.getUsbAndroidAutoConnectedTime() : device.getWirelessAndroidAutoConnectedTime());
        if (device.getConnectedTypeState(1) == 3 && device.getActiveCapability() == 128) {
            dev.setUsbDevice(true);
        }
        dev.setIsFirstConntect(device.isAndroidAutoFirstConn());
        String deviceId = DeviceConnectionManagerModel.getInstance().getAutoConnectDeviceId();
        if (!TextUtils.isEmpty(deviceId) && !TextUtils.isEmpty(device.getDeviceId()) && deviceId.equals(device.getDeviceId())) {
            LogUtil.info(TAG, "autoconnect, so not convert connectting deviceId!" + deviceId);
        }
        int usbProjectionState = device.getActiveCapabilityState(128);
        int wirelessProjectionState = device.getActiveCapabilityState(1024);
        if (device.getDeviceConnectedState() == 2) {
            dev.setDeviceStatus(-1);
        } else if (usbProjectionState == 4) {
            dev.setDeviceStatus(4);
            dev.setProjectionConnectType(1);
        } else if (wirelessProjectionState == 4) {
            dev.setDeviceStatus(4);
            dev.setProjectionConnectType(2);
        } else if (usbProjectionState == 3) {
            dev.setDeviceStatus(3);
            dev.setProjectionConnectType(1);
        } else if (wirelessProjectionState == 3) {
            dev.setDeviceStatus(3);
            dev.setProjectionConnectType(2);
        } else if (usbProjectionState == 2) {
            dev.setDeviceStatus(2);
            dev.setProjectionConnectType(1);
        } else if (wirelessProjectionState == 2) {
            dev.setDeviceStatus(2);
            dev.setProjectionConnectType(2);
        } else if (!device.isAvailableCapability(128) && !device.isAvailableCapability(1024)) {
            if ((device.getCapability() & 1024) > 0) {
                dev.setDeviceStatus(0);
            } else if ((device.getCapability() & 128) <= 0 || USB_HISTORY_CAP_INVALID) {
                dev.setDeviceStatus(-1);
            } else {
                dev.setDeviceStatus(0);
            }
        } else {
            dev.setDeviceStatus(1);
        }
        return dev;
    }
}
