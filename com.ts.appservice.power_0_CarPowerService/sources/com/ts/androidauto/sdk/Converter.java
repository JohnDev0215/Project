package com.ts.androidauto.sdk;

import com.ts.androidauto.sdk.aidl.data.IfNavigationDestDistanceData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationDirectionData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationLaneData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationPositionData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationStateData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationStepData;
import com.ts.androidauto.sdk.aidl.data.UiConfig;
import com.ts.androidauto.sdk.aidl.data.VehicleInfo;
import com.ts.androidauto.sdk.common.VehicleConst;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.androidauto.data.MediaMetadata;
import ts.car.service.androidauto.data.NavigationData;
import ts.car.service.androidauto.data.NavigationDestDistanceData;
import ts.car.service.androidauto.data.NavigationDirectionData;
import ts.car.service.androidauto.data.NavigationLaneData;
import ts.car.service.androidauto.data.NavigationPositionData;
import ts.car.service.androidauto.data.NavigationStateData;
import ts.car.service.androidauto.data.NavigationStepData;
import ts.car.service.androidauto.data.UiConfigData;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes.dex */
public abstract class Converter {
    public static VehicleInfo dataToVehicleInfo(VehicleData in) {
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
        dst.mVehicleFuelTypeList = convertFuelTypeList(in.mVehicleFuelTypeList);
        dst.mVehicleEvConnectorTypeList = convertEvConnectorTypeList(in.mVehicleEvConnectorTypeList);
        dst.mSupportHardKeyList = convertKeyCodeList(in.mSupportHardKeyList);
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

    public static UiConfig dataToUiConfig(UiConfigData in) {
        if (in == null) {
            return null;
        }
        UiConfig dst = new UiConfig();
        dst.mAapClusterUiConfig480p = convertClusterUiConfig(in.mAapClusterUiConfig480p);
        dst.mAapClusterUiConfig720p = convertClusterUiConfig(in.mAapClusterUiConfig720p);
        dst.mAapClusterUiConfig1080p = convertClusterUiConfig(in.mAapClusterUiConfig1080p);
        return dst;
    }

    private static VehicleInfo.AapVideoConfig convertVideoConfig(VehicleData.AapVideoConfig in) {
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

    private static UiConfig.AapClusterUiConfig convertClusterUiConfig(UiConfigData.AapClusterUiConfig in) {
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

    private static VehicleConst.AapVehicleDrivePosition convertDrivePosition(VehicleConst.AapVehicleDrivePosition in) {
        VehicleConst.AapVehicleDrivePosition dst = VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_UNKNOWN;
        int i = AnonymousClass1.$SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleDrivePosition[in.ordinal()];
        if (i == 1) {
            VehicleConst.AapVehicleDrivePosition dst2 = VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_LEFT;
            return dst2;
        }
        if (i == 2) {
            VehicleConst.AapVehicleDrivePosition dst3 = VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_RIGHT;
            return dst3;
        }
        if (i == 3) {
            VehicleConst.AapVehicleDrivePosition dst4 = VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_CENTER;
            return dst4;
        }
        return dst;
    }

    private static VehicleConst.AapVehicleFuelType[] convertFuelTypeList(VehicleConst.AapVehicleFuelType[] in) {
        if (in == null) {
            return null;
        }
        VehicleConst.AapVehicleFuelType[] dst = new VehicleConst.AapVehicleFuelType[in.length];
        for (int i = 0; i < in.length; i++) {
            dst[i] = convertFuelType(in[i]);
        }
        return dst;
    }

    private static VehicleConst.AapVehicleFuelType convertFuelType(VehicleConst.AapVehicleFuelType in) {
        VehicleConst.AapVehicleFuelType dst = VehicleConst.AapVehicleFuelType.AAP_FUEL_UNKNOWN;
        switch (AnonymousClass1.$SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[in.ordinal()]) {
            case 1:
                VehicleConst.AapVehicleFuelType dst2 = VehicleConst.AapVehicleFuelType.AAP_FUEL_UNLEADED_GASOLINE;
                return dst2;
            case 2:
                VehicleConst.AapVehicleFuelType dst3 = VehicleConst.AapVehicleFuelType.AAP_FUEL_LEADED_GASOLINE;
                return dst3;
            case 3:
                VehicleConst.AapVehicleFuelType dst4 = VehicleConst.AapVehicleFuelType.AAP_FUEL_DIESEL_1;
                return dst4;
            case 4:
                VehicleConst.AapVehicleFuelType dst5 = VehicleConst.AapVehicleFuelType.AAP_FUEL_DIESEL_2;
                return dst5;
            case 5:
                VehicleConst.AapVehicleFuelType dst6 = VehicleConst.AapVehicleFuelType.AAP_FUEL_BIODIESEL;
                return dst6;
            case 6:
                VehicleConst.AapVehicleFuelType dst7 = VehicleConst.AapVehicleFuelType.AAP_FUEL_E85;
                return dst7;
            case 7:
                VehicleConst.AapVehicleFuelType dst8 = VehicleConst.AapVehicleFuelType.AAP_FUEL_LPG;
                return dst8;
            case 8:
                VehicleConst.AapVehicleFuelType dst9 = VehicleConst.AapVehicleFuelType.AAP_FUEL_CNG;
                return dst9;
            case 9:
                VehicleConst.AapVehicleFuelType dst10 = VehicleConst.AapVehicleFuelType.AAP_FUEL_LNG;
                return dst10;
            case 10:
                VehicleConst.AapVehicleFuelType dst11 = VehicleConst.AapVehicleFuelType.AAP_FUEL_ELECTRIC;
                return dst11;
            case 11:
                VehicleConst.AapVehicleFuelType dst12 = VehicleConst.AapVehicleFuelType.AAP_FUEL_HYDROGEN;
                return dst12;
            case 12:
                VehicleConst.AapVehicleFuelType dst13 = VehicleConst.AapVehicleFuelType.AAP_FUEL_OTHER;
                return dst13;
            default:
                return dst;
        }
    }

    private static VehicleConst.AapEvConnectorType[] convertEvConnectorTypeList(VehicleConst.AapEvConnectorType[] in) {
        if (in == null) {
            return null;
        }
        VehicleConst.AapEvConnectorType[] dst = new VehicleConst.AapEvConnectorType[in.length];
        for (int i = 0; i < in.length; i++) {
            dst[i] = convertEvConnectorType(in[i]);
        }
        return dst;
    }

    private static VehicleConst.AapEvConnectorType convertEvConnectorType(VehicleConst.AapEvConnectorType in) {
        VehicleConst.AapEvConnectorType dst = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_UNKNOWN;
        switch (AnonymousClass1.$SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[in.ordinal()]) {
            case 1:
                VehicleConst.AapEvConnectorType dst2 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_J1772;
                return dst2;
            case 2:
                VehicleConst.AapEvConnectorType dst3 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_MENNEKES;
                return dst3;
            case 3:
                VehicleConst.AapEvConnectorType dst4 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_CHADEMO;
                return dst4;
            case 4:
                VehicleConst.AapEvConnectorType dst5 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_COMBO_1;
                return dst5;
            case 5:
                VehicleConst.AapEvConnectorType dst6 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_COMBO_2;
                return dst6;
            case 6:
                VehicleConst.AapEvConnectorType dst7 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_ROADSTER;
                return dst7;
            case 7:
                VehicleConst.AapEvConnectorType dst8 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_HPWC;
                return dst8;
            case 8:
                VehicleConst.AapEvConnectorType dst9 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_SUPERCHARGER;
                return dst9;
            case 9:
                VehicleConst.AapEvConnectorType dst10 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_GBT;
                return dst10;
            case 10:
                VehicleConst.AapEvConnectorType dst11 = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_OTHER;
                return dst11;
            default:
                return dst;
        }
    }

    private static VehicleConst.AapHardkeyEvent[] convertKeyCodeList(VehicleConst.AapHardkeyEvent[] in) {
        if (in == null) {
            return null;
        }
        VehicleConst.AapHardkeyEvent[] dst = new VehicleConst.AapHardkeyEvent[in.length];
        for (int i = 0; i < in.length; i++) {
            dst[i] = convertKeyCode(in[i]);
        }
        return dst;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.sdk.Converter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType;
        static final /* synthetic */ int[] $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent;
        static final /* synthetic */ int[] $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleDrivePosition;
        static final /* synthetic */ int[] $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType;

        static {
            int[] iArr = new int[VehicleConst.AapHardkeyEvent.values().length];
            $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent = iArr;
            try {
                iArr[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_TEL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_NAVIGATION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_HOME.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_BACK.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY_PAUSE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PAUSE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PREVIOUS.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_NEXT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_RIGHT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_LEFT.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_UP.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_DOWN.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_CENTER.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[VehicleConst.AapHardkeyEvent.AAP_KEYCODE_ROTARY_CONTROLLER.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            int[] iArr2 = new int[VehicleConst.AapEvConnectorType.values().length];
            $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType = iArr2;
            try {
                iArr2[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_J1772.ordinal()] = 1;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_MENNEKES.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_CHADEMO.ordinal()] = 3;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_COMBO_1.ordinal()] = 4;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_COMBO_2.ordinal()] = 5;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_ROADSTER.ordinal()] = 6;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_HPWC.ordinal()] = 7;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_TESLA_SUPERCHARGER.ordinal()] = 8;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_GBT.ordinal()] = 9;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapEvConnectorType[VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_OTHER.ordinal()] = 10;
            } catch (NoSuchFieldError e27) {
            }
            int[] iArr3 = new int[VehicleConst.AapVehicleFuelType.values().length];
            $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType = iArr3;
            try {
                iArr3[VehicleConst.AapVehicleFuelType.AAP_FUEL_UNLEADED_GASOLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e28) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_LEADED_GASOLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e29) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_DIESEL_1.ordinal()] = 3;
            } catch (NoSuchFieldError e30) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_DIESEL_2.ordinal()] = 4;
            } catch (NoSuchFieldError e31) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_BIODIESEL.ordinal()] = 5;
            } catch (NoSuchFieldError e32) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_E85.ordinal()] = 6;
            } catch (NoSuchFieldError e33) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_LPG.ordinal()] = 7;
            } catch (NoSuchFieldError e34) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_CNG.ordinal()] = 8;
            } catch (NoSuchFieldError e35) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_LNG.ordinal()] = 9;
            } catch (NoSuchFieldError e36) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_ELECTRIC.ordinal()] = 10;
            } catch (NoSuchFieldError e37) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_HYDROGEN.ordinal()] = 11;
            } catch (NoSuchFieldError e38) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleFuelType[VehicleConst.AapVehicleFuelType.AAP_FUEL_OTHER.ordinal()] = 12;
            } catch (NoSuchFieldError e39) {
            }
            int[] iArr4 = new int[VehicleConst.AapVehicleDrivePosition.values().length];
            $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleDrivePosition = iArr4;
            try {
                iArr4[VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e40) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleDrivePosition[VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e41) {
            }
            try {
                $SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapVehicleDrivePosition[VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e42) {
            }
        }
    }

    public static VehicleConst.AapHardkeyEvent convertKeyCode(VehicleConst.AapHardkeyEvent in) {
        VehicleConst.AapHardkeyEvent dst = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_HOME;
        switch (AnonymousClass1.$SwitchMap$ts$car$service$androidauto$common$VehicleConst$AapHardkeyEvent[in.ordinal()]) {
            case 1:
                VehicleConst.AapHardkeyEvent dst2 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA;
                return dst2;
            case 2:
                VehicleConst.AapHardkeyEvent dst3 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_TEL;
                return dst3;
            case 3:
                VehicleConst.AapHardkeyEvent dst4 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_NAVIGATION;
                return dst4;
            case 4:
                VehicleConst.AapHardkeyEvent dst5 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_HOME;
                return dst5;
            case 5:
                VehicleConst.AapHardkeyEvent dst6 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH;
                return dst6;
            case 6:
                VehicleConst.AapHardkeyEvent dst7 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_BACK;
                return dst7;
            case 7:
                VehicleConst.AapHardkeyEvent dst8 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY_PAUSE;
                return dst8;
            case 8:
                VehicleConst.AapHardkeyEvent dst9 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY;
                return dst9;
            case 9:
                VehicleConst.AapHardkeyEvent dst10 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PAUSE;
                return dst10;
            case 10:
                VehicleConst.AapHardkeyEvent dst11 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PREVIOUS;
                return dst11;
            case 11:
                VehicleConst.AapHardkeyEvent dst12 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_NEXT;
                return dst12;
            case 12:
                VehicleConst.AapHardkeyEvent dst13 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_RIGHT;
                return dst13;
            case 13:
                VehicleConst.AapHardkeyEvent dst14 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_LEFT;
                return dst14;
            case 14:
                VehicleConst.AapHardkeyEvent dst15 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_UP;
                return dst15;
            case 15:
                VehicleConst.AapHardkeyEvent dst16 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_DOWN;
                return dst16;
            case 16:
                VehicleConst.AapHardkeyEvent dst17 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_DPAD_CENTER;
                return dst17;
            case 17:
                VehicleConst.AapHardkeyEvent dst18 = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_ROTARY_CONTROLLER;
                return dst18;
            default:
                return dst;
        }
    }

    public static NavigationData dataToNavigationData(com.ts.androidauto.sdk.aidl.data.NavigationData navigationData) {
        if (navigationData == null) {
            return null;
        }
        NavigationData retNavigationData = new NavigationData();
        retNavigationData.setEvent(navigationData.getEvent());
        retNavigationData.setImage(navigationData.getImage());
        retNavigationData.setRoad(navigationData.getRoad());
        retNavigationData.setTurnAngle(navigationData.getTurnAngle());
        retNavigationData.setTurnNumber(navigationData.getTurnNumber());
        retNavigationData.setTurnSide(navigationData.getTurnSide());
        return retNavigationData;
    }

    public static AndroidAutoDevice dataToAndroidAutoDevice(com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice dev) {
        if (dev == null) {
            return null;
        }
        AndroidAutoDevice device = new AndroidAutoDevice();
        device.setDeviceName(dev.getDeviceName());
        device.setUsbSerialNumber(dev.getUsbSerialNumber());
        device.setBtAddr(dev.getBtAddr());
        device.setWifiAddr(dev.getWifiAddr());
        device.setDeviceStatus(dev.getDeviceStatus());
        device.setUsbDevice(dev.isUsbDevice());
        device.setProjectionConnectType(dev.getProjectionConnectType());
        device.setProjectionConnectedTimeMillis(dev.getProjectionConnectedTimeMillis());
        device.setIsFirstConntect(dev.isFirstConntect());
        return device;
    }

    public static MediaMetadata dataToMediaMetadata(com.ts.androidauto.sdk.aidl.data.MediaMetadata mediaMetadata) {
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

    public static NavigationStateData dataToNavigationStateData(IfNavigationStateData ifNavigationStateData) {
        if (ifNavigationStateData == null) {
            return null;
        }
        NavigationStateData retNavigationStateData = new NavigationStateData();
        retNavigationStateData.setStepData(convertNavigationStepData(ifNavigationStateData.getStepData()));
        retNavigationStateData.setNavigationDestinations(ifNavigationStateData.getNavigationDestinations());
        return retNavigationStateData;
    }

    public static List<NavigationStepData> convertNavigationStepData(List<IfNavigationStepData> ifStepDataList) {
        List<NavigationStepData> stepDataList = new ArrayList<>();
        if (ifStepDataList != null) {
            for (int i = 0; i < ifStepDataList.size(); i++) {
                IfNavigationStepData ifStepData = ifStepDataList.get(i);
                NavigationStepData stepData = new NavigationStepData();
                stepData.setLaneData(convertNavigationLaneData(ifStepData.getLaneData()));
                stepData.setNavigationCue(ifStepData.getNavigationCue());
                stepData.setRoad(ifStepData.getRoad());
                stepData.setRoundaboutExitAngle(ifStepData.getTurnAngle());
                stepData.setRoundaboutExitNumber(ifStepData.getTurnNumber());
                stepData.setManeuverType(ifStepData.getEvent());
                stepDataList.add(stepData);
            }
        }
        return stepDataList;
    }

    public static List<NavigationLaneData> convertNavigationLaneData(List<IfNavigationLaneData> ifLaneDataList) {
        List<NavigationLaneData> laneDataList = new ArrayList<>();
        if (ifLaneDataList != null) {
            for (int i = 0; i < ifLaneDataList.size(); i++) {
                IfNavigationLaneData ifLaneData = ifLaneDataList.get(i);
                NavigationLaneData laneData = new NavigationLaneData();
                laneData.setNavigationDirectionData(convertNavigationDirectionData(ifLaneData.getIfNavigationDirectionData()));
                laneDataList.add(laneData);
            }
        }
        return laneDataList;
    }

    public static List<NavigationDirectionData> convertNavigationDirectionData(List<IfNavigationDirectionData> ifDirectionDataList) {
        List<NavigationDirectionData> directionDataList = new ArrayList<>();
        if (ifDirectionDataList != null) {
            for (int i = 0; i < ifDirectionDataList.size(); i++) {
                IfNavigationDirectionData ifDirectionData = ifDirectionDataList.get(i);
                NavigationDirectionData directionData = new NavigationDirectionData();
                directionData.setHighlighted(ifDirectionData.getHighlighted());
                directionData.setShape(ifDirectionData.getShape());
                directionDataList.add(directionData);
            }
        }
        return directionDataList;
    }

    public static NavigationPositionData dataToNavigationPositionData(IfNavigationPositionData ifNavigationPositionData) {
        if (ifNavigationPositionData == null) {
            return null;
        }
        NavigationPositionData retNavigationPositionData = new NavigationPositionData();
        retNavigationPositionData.setNavigationDestDistanceData(convertNavigationDestDistanceData(ifNavigationPositionData.getIfNavigationDestDistanceData()));
        retNavigationPositionData.setCurrentRoad(ifNavigationPositionData.getCurrentRoad());
        retNavigationPositionData.setDisplayUnits(ifNavigationPositionData.getDisplayUnits());
        retNavigationPositionData.setMeters(ifNavigationPositionData.getMeters());
        retNavigationPositionData.setDisplayValue(ifNavigationPositionData.getDisplayValue());
        retNavigationPositionData.setTimeToStepSeconds(ifNavigationPositionData.getTimeSeconds());
        return retNavigationPositionData;
    }

    public static List<NavigationDestDistanceData> convertNavigationDestDistanceData(List<IfNavigationDestDistanceData> ifDestDistanceDataList) {
        List<NavigationDestDistanceData> destDistanceDataList = new ArrayList<>();
        if (ifDestDistanceDataList != null) {
            for (int i = 0; i < ifDestDistanceDataList.size(); i++) {
                IfNavigationDestDistanceData ifDestDistanceData = ifDestDistanceDataList.get(i);
                NavigationDestDistanceData destDistanceData = new NavigationDestDistanceData();
                destDistanceData.setDisplayUnits(ifDestDistanceData.getDisplayUnits());
                destDistanceData.setDisplayValue(ifDestDistanceData.getDisplayValue());
                destDistanceData.setMeters(ifDestDistanceData.getMeters());
                destDistanceData.setEstimatedTime(ifDestDistanceData.getEstimatedTime());
                destDistanceData.setTimeSeconds(ifDestDistanceData.getTimeSeconds());
                destDistanceDataList.add(destDistanceData);
            }
        }
        return destDistanceDataList;
    }
}
