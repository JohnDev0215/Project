package com.ts.androidauto.app.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import java.security.SecureRandom;
import java.util.Locale;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleInfoLoader {
    private static final int AAP_CLUSTER_DISPLAY_AREA_1080P_HEIGHT_FULL = 720;
    private static final int AAP_CLUSTER_DISPLAY_AREA_1080P_HEIGHT_SMALL = 720;
    private static final int AAP_CLUSTER_DISPLAY_AREA_1080P_MARGIN_LEFT_FULL = 0;
    private static final int AAP_CLUSTER_DISPLAY_AREA_1080P_MARGIN_LEFT_SMALL = 0;
    private static final int AAP_CLUSTER_DISPLAY_AREA_1080P_MARGIN_TOP_FULL = 0;
    private static final int AAP_CLUSTER_DISPLAY_AREA_1080P_MARGIN_TOP_SMALL = 0;
    private static final int AAP_CLUSTER_DISPLAY_AREA_1080P_WIDTH_FULL = 1920;
    private static final int AAP_CLUSTER_DISPLAY_AREA_1080P_WIDTH_SMALL = 1920;
    private static final int AAP_CLUSTER_DISPLAY_AREA_480P_HEIGHT = 480;
    private static final int AAP_CLUSTER_DISPLAY_AREA_480P_MARGIN_LEFT = 0;
    private static final int AAP_CLUSTER_DISPLAY_AREA_480P_MARGIN_TOP = 0;
    private static final int AAP_CLUSTER_DISPLAY_AREA_480P_WIDTH = 800;
    private static final int AAP_CLUSTER_RESOLUTION_1080P_HEIGHT_FULL = 720;
    private static final int AAP_CLUSTER_RESOLUTION_1080P_HEIGHT_SMALL = 720;
    private static final float AAP_CLUSTER_RESOLUTION_1080P_PIXEL_ASPECT_FULL = 1.0f;
    private static final float AAP_CLUSTER_RESOLUTION_1080P_PIXEL_ASPECT_SMALL = 1.0f;
    private static final int AAP_CLUSTER_RESOLUTION_1080P_REAL_DPI_FULL = 200;
    private static final int AAP_CLUSTER_RESOLUTION_1080P_REAL_DPI_SAMLL = 200;
    private static final int AAP_CLUSTER_RESOLUTION_1080P_REPORT_DPI_FULL = 140;
    private static final int AAP_CLUSTER_RESOLUTION_1080P_REPORT_DPI_SMALL = 140;
    private static final int AAP_CLUSTER_RESOLUTION_1080P_WIDTH_FULL = 1920;
    private static final int AAP_CLUSTER_RESOLUTION_1080P_WIDTH_SMALL = 1920;
    private static final int AAP_CLUSTER_RESOLUTION_480P_HEIGHT = 372;
    private static final float AAP_CLUSTER_RESOLUTION_480P_PIXEL_ASPECT = 1.0f;
    private static final int AAP_CLUSTER_RESOLUTION_480P_REAL_DPI = 167;
    private static final int AAP_CLUSTER_RESOLUTION_480P_REPORT_DPI = 148;
    private static final int AAP_CLUSTER_RESOLUTION_480P_WIDTH = 660;
    private static final int AAP_CLUSTER_SURFACEVIEW_1080P_HEIGHT_FULL = 720;
    private static final int AAP_CLUSTER_SURFACEVIEW_1080P_HEIGHT_SMALL = 720;
    private static final int AAP_CLUSTER_SURFACEVIEW_1080P_SCROLL_X_FULL = 0;
    private static final int AAP_CLUSTER_SURFACEVIEW_1080P_SCROLL_X_SMALL = 1320;
    private static final int AAP_CLUSTER_SURFACEVIEW_1080P_SCROLL_Y_FULL = 180;
    private static final int AAP_CLUSTER_SURFACEVIEW_1080P_SCROLL_Y_SMALL = 180;
    private static final int AAP_CLUSTER_SURFACEVIEW_1080P_WIDTH_FULL = 1920;
    private static final int AAP_CLUSTER_SURFACEVIEW_1080P_WIDTH_SMALL = 1920;
    private static final int AAP_CLUSTER_SURFACEVIEW_480P_HEIGHT = 480;
    private static final int AAP_CLUSTER_SURFACEVIEW_480P_SCROLL_X = 0;
    private static final int AAP_CLUSTER_SURFACEVIEW_480P_SCROLL_Y = 0;
    private static final int AAP_CLUSTER_SURFACEVIEW_480P_WIDTH = 800;
    private static final int AAP_CLUSTER_TOUCH_OFFSET_X = 0;
    private static final int AAP_CLUSTER_TOUCH_OFFSET_X_UKE = 0;
    private static final int AAP_CLUSTER_TOUCH_OFFSET_Y = 0;
    private static final int AAP_CLUSTER_TOUCH_OFFSET_Y_UKE = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_BOTTOM = 130;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_LEFT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_RIGHT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_TOP = 208;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_480P_BOTTOM = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_480P_LEFT = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_480P_RIGHT = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_480P_TOP = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_MARGIN_BOTTOM = 180;
    private static final int AAP_CLUSTER_UICONFIG_FULL_MARGIN_LEFT = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_MARGIN_RIGHT = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_MARGIN_TOP = 180;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_1080P_BOTTOM = 130;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_1080P_LEFT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_1080P_RIGHT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_1080P_TOP = 208;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_480P_BOTTOM = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_480P_LEFT = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_480P_RIGHT = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_480P_TOP = 0;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_CONTENT_INSETS_1080P_BOTTOM = 159;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_CONTENT_INSETS_1080P_LEFT = 73;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_CONTENT_INSETS_1080P_RIGHT = 73;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_CONTENT_INSETS_1080P_TOP = 210;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_MARGIN_BOTTOM = 180;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_MARGIN_LEFT = 1320;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_MARGIN_RIGHT = 0;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_MARGIN_TOP = 180;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_STABLE_CONTENT_INSETS_1080P_BOTTOM = 159;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_STABLE_CONTENT_INSETS_1080P_LEFT = 73;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_STABLE_CONTENT_INSETS_1080P_RIGHT = 73;
    private static final int AAP_CLUSTER_UICONFIG_SMALL_STABLE_CONTENT_INSETS_1080P_TOP = 210;
    private static final int AAP_CLUSTER_VIEWING_DISTANCE = 738;
    private static final int AAP_CLUSTER_VIEWING_DISTANCE_UKE = 604;
    private static final int AAP_DISPLAY_AREA_1080P_HEIGHT = 906;
    private static final int AAP_DISPLAY_AREA_1080P_MARGIN_LEFT = 0;
    private static final int AAP_DISPLAY_AREA_1080P_MARGIN_TOP = 0;
    private static final int AAP_DISPLAY_AREA_1080P_WIDTH = 1920;
    private static final int AAP_DISPLAY_AREA_480P_HEIGHT = 905;
    private static final int AAP_DISPLAY_AREA_480P_MARGIN_LEFT = 0;
    private static final int AAP_DISPLAY_AREA_480P_MARGIN_TOP = 0;
    private static final int AAP_DISPLAY_AREA_480P_WIDTH = 1920;
    private static final int AAP_DISPLAY_AREA_720P_HEIGHT = 906;
    private static final int AAP_DISPLAY_AREA_720P_MARGIN_LEFT = 0;
    private static final int AAP_DISPLAY_AREA_720P_MARGIN_TOP = 0;
    private static final int AAP_DISPLAY_AREA_720P_WIDTH = 1920;
    private static final String AAP_HEADUNIT_DISPLAY_NAME = "BYD";
    private static final String AAP_HEADUNIT_MAKER = "Autolink Information Technology Co.,Ltd";
    private static final String AAP_HEADUNIT_MODEL = "BYD 5.0F_01";
    private static final String AAP_HEADUNIT_MODEL_UKE = "BYD 5.0F_02";
    private static final String AAP_HEADUNIT_SW_BUILD = "multi";
    private static final String AAP_HEADUNIT_SW_VER = "00.01";
    private static final int AAP_RESOLUTION_1080P_HEIGHT = 906;
    private static final float AAP_RESOLUTION_1080P_PIXEL_ASPECT = 1.0f;
    private static final int AAP_RESOLUTION_1080P_REAL_DPI = 141;
    private static final int AAP_RESOLUTION_1080P_REPORT_DPI = 213;
    private static final int AAP_RESOLUTION_1080P_WIDTH = 1920;
    private static final int AAP_RESOLUTION_480P_HEIGHT = 377;
    private static final float AAP_RESOLUTION_480P_PIXEL_ASPECT = 1.0f;
    private static final int AAP_RESOLUTION_480P_REAL_DPI = 59;
    private static final int AAP_RESOLUTION_480P_REPORT_DPI = 100;
    private static final int AAP_RESOLUTION_480P_WIDTH = 800;
    private static final int AAP_RESOLUTION_720P_HEIGHT = 604;
    private static final float AAP_RESOLUTION_720P_PIXEL_ASPECT = 1.0f;
    private static final int AAP_RESOLUTION_720P_REAL_DPI = 94;
    private static final int AAP_RESOLUTION_720P_REPORT_DPI = 142;
    private static final int AAP_RESOLUTION_720P_WIDTH = 1280;
    private static final int AAP_SURFACEVIEW_1080P_HEIGHT = 1080;
    private static final int AAP_SURFACEVIEW_1080P_SCROLL_X = 0;
    private static final int AAP_SURFACEVIEW_1080P_SCROLL_Y = 84;
    private static final int AAP_SURFACEVIEW_1080P_WIDTH = 1920;
    private static final int AAP_SURFACEVIEW_480P_HEIGHT = 1151;
    private static final int AAP_SURFACEVIEW_480P_SCROLL_X = 0;
    private static final int AAP_SURFACEVIEW_480P_SCROLL_Y = 117;
    private static final int AAP_SURFACEVIEW_480P_WIDTH = 1920;
    private static final int AAP_SURFACEVIEW_720P_HEIGHT = 1080;
    private static final int AAP_SURFACEVIEW_720P_SCROLL_X = 0;
    private static final int AAP_SURFACEVIEW_720P_SCROLL_Y = 84;
    private static final int AAP_SURFACEVIEW_720P_WIDTH = 1920;
    private static final int AAP_TOUCH_OFFSET_X = 0;
    private static final int AAP_TOUCH_OFFSET_Y = 0;
    private static final String AAP_VEHICLE_MAKER = "BYD";
    private static final String AAP_VEHICLE_MODEL = "BYD 5.0F";
    private static final String AAP_VEHICLE_YEAR = "2023";
    private static final String AAP_VEHICLE_YEAR_UKE = "2024";
    private static final int AAP_VIEWING_DISTANCE = 950;
    public static final String APPLICATION_CLASS_NAME = "com.ts.androidauto.app.display.AapActivity";
    public static final String APPLICATION_PACKAGE_NAME = "com.ts.androidauto.app";
    private static final int MAX_VALUE_FOR_HEX = 16;
    private static final int NUM_ZERO = 0;
    private static final String RIGHT_RUDDER_KEY = "persist.vendor.byd.cfg.huc.steering.wheel.position";
    private static final String RIGHT_RUDDER_VALUE = "1";
    private static final String SW_VERSION_SPLIT = "[.]";
    private static final String SYSTEM_VERSION_NAME = "ro.product.version.soc.name";
    private static final int TOUCH_CLUSTER_SCREEN_HEIGHT = 194;
    private static final int TOUCH_CLUSTER_SCREEN_HEIGHT_UKE = 91;
    private static final int TOUCH_CLUSTER_SCREEN_WIDTH = 345;
    private static final int TOUCH_CLUSTER_SCREEN_WIDTH_UKE = 244;
    private static final int TOUCH_SCREEN_HEIGHT = 163;
    private static final int TOUCH_SCREEN_WIDTH = 345;
    private static final String VEHICLE_ID = "vehicle_id";
    private static final int VEHICLE_ID_LENGTH = 16;
    private static final String VEHICLE_MODEL_AUTOLINK = "51";
    private static final String VEHICLE_MODEL_TANG = "1";
    private static final String VEHICLE_MODEL_TANG_SOP = "2";
    private static final String VEHICLE_MODEL_UKE = "3";
    private static final String VEHICLE_MODEL_UKE_SOP = "4";
    private static final String VEHICLE_SN_VERSION_NAME = "persist.vendor.bosch.cfg.diag.sn";
    private String mCurrentVehicleModel = "1";
    private VehicleData mVehicleInfo;
    private BYDAutoSysPropertiesDevice mbydAutoSysPropertiesDevice;
    private static final String TAG = "AAP" + VehicleInfoLoader.class.getSimpleName();
    private static final VehicleConst.AapVehicleFuelType[] FUEL_TYPE_GROUP = {VehicleConst.AapVehicleFuelType.AAP_FUEL_ELECTRIC};
    private static final VehicleConst.AapEvConnectorType[] EV_CONNECTOR_TYPE_GROUP = {VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_MENNEKES};
    private static VehicleInfoLoader mVehicleInfoLoaderInstance = null;

    public static synchronized VehicleInfoLoader getInstance() {
        if (mVehicleInfoLoaderInstance == null) {
            mVehicleInfoLoaderInstance = new VehicleInfoLoader();
        }
        return mVehicleInfoLoaderInstance;
    }

    private VehicleInfoLoader() {
        LogUtil.info(TAG, "VehicleInfoLoader constructor");
    }

    public synchronized VehicleData getVehicleInfo(Context context) {
        LogUtil.info(TAG, "getVehicleInfo context=" + context);
        this.mbydAutoSysPropertiesDevice = BYDAutoSysPropertiesDevice.getInstance(context);
        if (this.mVehicleInfo == null) {
            this.mVehicleInfo = createVehicleInfo(context);
        }
        return this.mVehicleInfo;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private VehicleData createVehicleInfo(Context context) {
        LogUtil.info(TAG, "createVehicleInfo");
        VehicleData vehicleInfo = new VehicleData();
        initAapVideoConfig480p(vehicleInfo.mAapVideoConfig480p);
        initAapVideoConfig720p(vehicleInfo.mAapVideoConfig720p);
        initAapVideoConfig1080p(vehicleInfo.mAapVideoConfig1080p);
        String currentSwVersion = getSoftwareVersion();
        String[] headUnitSwVerArray = currentSwVersion.split(SW_VERSION_SPLIT);
        vehicleInfo.mHeadUnitSwVer = currentSwVersion;
        LogUtil.debug(TAG, "headUnitSwVerArray length: " + headUnitSwVerArray.length);
        if (headUnitSwVerArray.length > 0 && VEHICLE_MODEL_AUTOLINK.equals(headUnitSwVerArray[0])) {
            this.mCurrentVehicleModel = headUnitSwVerArray[2];
        }
        LogUtil.debug(TAG, "currentSwVersion: " + currentSwVersion + "mCurrentVehicleModel: " + this.mCurrentVehicleModel);
        String str = this.mCurrentVehicleModel;
        byte b = -1;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    b = 2;
                }
                break;
            case 50:
                if (str.equals(VEHICLE_MODEL_TANG_SOP)) {
                    b = 3;
                }
                break;
            case 51:
                if (str.equals(VEHICLE_MODEL_UKE)) {
                    b = 0;
                }
                break;
            case 52:
                if (str.equals(VEHICLE_MODEL_UKE_SOP)) {
                    b = 1;
                }
                break;
        }
        if (b == 0 || b == 1) {
            vehicleInfo.mVehicleYear = AAP_VEHICLE_YEAR_UKE;
            vehicleInfo.mHeadUnitModel = AAP_HEADUNIT_MODEL_UKE;
            vehicleInfo.mClusterViewingDistance = 604;
            initAapClusterVideoConfig1080pFull(vehicleInfo.mAapClusterVideoConfig1080pFull);
            initAapClusterVideoConfig1080pSmall(vehicleInfo.mAapClusterVideoConfig1080pSmall);
        } else {
            vehicleInfo.mVehicleYear = AAP_VEHICLE_YEAR;
            vehicleInfo.mHeadUnitModel = AAP_HEADUNIT_MODEL;
            vehicleInfo.mClusterViewingDistance = 738;
            initAapClusterVideoConfig480p(vehicleInfo.mAapClusterVideoConfig480p);
        }
        vehicleInfo.mIsSupportClusterProjection = true;
        vehicleInfo.mIsUpdateClusterScreenSize = false;
        vehicleInfo.mTouchScreenWidth = 345;
        vehicleInfo.mTouchScreenHeight = 163;
        vehicleInfo.mAapTouchPointOffsetX = 0;
        vehicleInfo.mAapTouchPointOffsetY = 0;
        vehicleInfo.mViewingDistance = 950;
        vehicleInfo.mVehicleMaker = "BYD";
        vehicleInfo.mVehicleModel = AAP_VEHICLE_MODEL;
        vehicleInfo.mVehicleId = getVehicleId(context);
        vehicleInfo.mVehicleDriverPosition = getDriverPosition(RIGHT_RUDDER_KEY);
        vehicleInfo.mHeadUnitMaker = AAP_HEADUNIT_MAKER;
        vehicleInfo.mHeadUnitSwBuild = AAP_HEADUNIT_SW_BUILD;
        vehicleInfo.mDisplayName = "BYD";
        vehicleInfo.mVehicleFuelTypeList = FUEL_TYPE_GROUP;
        vehicleInfo.mAoaSerialNumber = getSerialNumber();
        vehicleInfo.mVehicleEvConnectorTypeList = EV_CONNECTOR_TYPE_GROUP;
        vehicleInfo.mSupportHardKeyList = new VehicleConst.AapHardkeyEvent[]{VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH, VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA, VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PREVIOUS, VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_NEXT, VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY, VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PAUSE, VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY_PAUSE};
        vehicleInfo.mRootCert = "vqxn9rCI_kGsWmUfFqkggifUUF2QHYt-AavjJIvgZNtvff2gXgW43mewMpv7OjaQKMgUXJT0MgmF\nBmEDUsjY0bvUbcB7NZmvNkMsLM8pEbOeYj-TRrLXGji4HFNqRXjk5leSNL545tfQeJytMq0Hm5rS\nmTkPM7mM-bfAWoOH5WmFWVTq7dxYUl8rIPgcawDHXUfpWaVHSvH7C7vMgRfLaNbwI7z4MV_Cl-O1\niSp1oJav0uNIBTQMjT_SqKYW5z_DXDYTg21-XGHtj8sktZFUDiRWpTX5NeoaFOww8b2PJsqkwguR\ndvr7UZ7qerKj6gAdvMsjeQjIXAx8YPyYzXDqwSkj1bUdkkVLwHL3EdWaUtVP1FBe4gwK-iHhRONt\ngsFYI7DOGsm3kMrAPzWjL86YK-90PCSQp6iMo3Vpn76vG5DVSlFVolfwSySY1to9nyMJsuZ2jxHA\nTaU6D7gLXZRbskTSO6hC3UfcGqwQD81kec4AlI5caUI4RqaZUlF_u4AWyABsD0EWM8MLvajpAIs_\nc0tbtejt_XJG-eAQOnqmMelb8Se1hAETQRG78CCwf-tkQXXkNtWmsCeC72dmliQnNPPMMWTKWcKa\ntD08-Xq4JooKpBNzAPlbaaa37zJri-yzMUQzKhzh2LTQV1TEAAV9nLm7i7SUt4ocZclI4Cjokoil\nqGT0-ubkf9weqyksRSmr71xGYkaKVtr8GanHNQmbPBnaVzXthZ2XpiPREDn1buVK9HTP-UralxkG\nRHFBqH3dNmvy9tWJYoMLbcSaVV8wlIW6kvDjbG8rZNC_CtPv4F5Q5SoHUpo8H6vk0DLRRX6xFxnh\nboRyE1ssjDL-Jgz7XBsVMIbrpDfCnJhwSIzfNsbnau-0GQHkYkL4AbJqnnsauXqKJcIeWycjLK3v\nPDY7KXQBThuxZA_mqRXZaZVNShEdZ57cNZoLdiQ2CiwFjhOxKUbWN12BVuSEz7IgT45yx1pI22ll\nmDxXBMBAmjo69REyTD9TjEiDvDIlvZJdsusNlbbMoGzI4sUcmvmIw9HHfhSzRq_sp81F8EO3Q1O-\nchW8Osqvxvf_whhKvCPjpdpLRkwAnsADIQEdJ4Mzta8s419PE_2soONNVTOvvI5VmaJHnlx-Vh7_\nvjvVLxcI7CH_lMRPTFcEcIW35RI2ncGV39NuAFe9tQvY9msPq5ShB361eF1eMIuxIt9j8tLAXYql\nE-pJHJZee0L6WiB5ahXqnaECRo3_Zyzo48uNVqhWaEPVHIXijVp1terKDd3DlDgfUoQ0U1GSJZ-C\nxRIbeNBiDDwCSEXsqOeCcog0oLPB1m_mCPR126vgr2Cn9GED_moisjdKUQM991H-ihHkFGMwVAe7\n7D5o3tOBE6gHKb0HfFK2yaeoBjrjVjtPLSBh5bqMulJtSYfrfxMakX_xIqzCx9SR6VRrN5W3CQPT\nhNhc42fODRUq2ZD_LFECcMItB-DS1SkWl_pAyXQ5MyuOtTLc6wlaIlIXZYUaoSAFD0L_o9vwYZea\ntNdkjdds48sR0e_tBHNu7_gVF8vkLsv_QTxXchSKEaPRIDXBVyJEzCdlIRpMe3dRtMdDK9eqWLGQ\nHI6xtNDMfpHXeHdm9pX4qeK96PPtH9HocJCB5dvgEkFyVN0vOsfVdzFimhvhhn1tO-HJEPsWK1jC\nTrWwJTU3cwkArI1wl-I0nr17SZ3VQVYksFqKeHE9cleRF7TOwqyLfu5L\n";
        vehicleInfo.mClientCert = "vqxn9rCI_kGsWmUfFqkggifUUF2QHYt-AavjJIvgZNsZCrFrGYmMFgPfZUw7TebEqJsJ29Oa0uKe\n3XecDvPxRllvks18UbwV6S3SkptzzPiJi80PMU3KMJ9uG-diN0bs3ULMJbHzJEO4eMbBZGY2ynJC\nf0CmP1d3tvZDU8iQW4bqAu14groCkuZkJX2jZkHKyzntVNtF0k8EeDvVANVdtZ4uIW2IhC9QfO-6\nx-Xol_Y2J9ZWMubjIzb78AMd5JRbUguKO6D1Hrc43nt7CMVNAXLFCjCETgnwstxxwwhZjk28cJFQ\nc3QJGJh-7BUQmEyy5SXNlj6dDWvDEsBjAANgIg-Iqrxzmwc-wsSv6L4jF8QA0E13LfK-bHlb-hVQ\nyFchmDNeCxVELdlG2WWmz2rVepk05_765NT4GAPZxHxQdKEjU3j83yuB-nkeqIGcROA0VSvAWvxv\nWBngP2pUn3vtixHN0fI0gpWYDYhn1Cq3gN0lfxVXaaBDdF7Ca1ikBCv2FT8QqWBjTf165pGSlgxc\n9KmmVigh0mCP2L9EG8660sBm8Ho9l81eFK6i7sgbkohfI1oNZfz5S0STc3UbQiCX6lnjA5MeakoY\nvq0ZeY9jV_CELhpnfXqL9B1P3kb_GX6JB1ugpy31MvlI-UOG4MW6DLvA1R0tzTH5DNSNFWniXwim\nYSkceX7jJ8A7ikw8b4-S-8nDVBsP9cPc1NkohAgkDK5gkiX7AvefLWyza6n8OqRuQVvssx8SY9ul\nE0q90_wnlQBwQSe7RNrwwwQBmWcrRmFHgCcz6n5AwMpoLj1HAHoXXpne5b6I6Kd_9pHIqxhLS0Ex\nobwkEt9QOkHxcnkhUenL1X801Hsk2pM6pZhY_1JK15kzdb7XAfK7mJlZnYohlX5bfP3ZfPcT2ZME\nNPnxdg4Htw2zEOYhvlrHVvSr4LSHbKlArsxEa5uBegDpnu3rwNUa5B6UoEg-jkRuH7xZgQrfB3HT\nEBYOcIpTSI9vDE1gSmUdMx8dE6Adxp7DX1Pp49uvguEMPH4edwuRDwOIayJVJILK8LFWmn3djmk_\nFFbmv3xSzUvg8OXLisW3PGQK6Dbr3urobWeBzcGQmoiKOqFu-_dscAg2obkqVDc7qsCiTFw9qcuy\nKE4g2y3Y7zS4SiY-xCJdRTHHRE6S_5_RyedQlLznzjLFoFGfkTqerT4YXruneRp_N_iSQayCWcvf\np6CqPZTlLESSb4lC5_krNCxJbXoJdeY7n4pi9v6dFFmD7GbIzu2vLo1g1KL3PikwoI1ZDdKFjMlo\nuZnSwGczo6BBeY0B1QgTmHke_FBQmJff1anbdzkCms7ho5F6YSau-_QZ34MhS6VCf0rWzPkWLZVL\nMjJYM70oaOcr_36g24KxTfo4NqLfm4C4PAi64hXmR83l3PfVobT94PJO8lfdsZKnSnVJAs99LA9x\nv44izN2xkCuCxrgTl8TcAggZxw77MGU88MQdqeh9k5M0-sqM7vV6HW_Jg7FJJDSDb-NSl1Jrtf3t\nrWnX2a0u6BtlSuLqglR88bnneIg1VUySo4HKAO5I2V_wDLU-knqxym37WaBu3CTtf5DyOovSEriA\ny1xHxpmSIoFsQcdPABkGfyvvHekR_wRj9a9mxJH9-YPGaQU64CH4J1REY7UyhQdtWNPhHTt6aed3\n7VYy3nbsegqRCUSnnIc6nt_YLbo6hL7sRUoR5yhMeNfuuf7h--yogN2c\n";
        vehicleInfo.mPrivateKey = "PXwU4mCiAyq8tnHetw_C45-4uQ9ZKaxQm78v_v3cD8ZvpF84c9eX7ftvrYa91m3pdXAIQCUCe7Tm\nruj4P73s2R3I51kCkjTjXDOeeXgK9746F0DJMJKH_qKOpCFzFLtZqaZWKCHSYI_Yv0QbzrrSwLpl\nJz8xIRctALeWuABKOa8pqH87zniMYjrBBhYcdq1iQP2eiQHTMDzO2UFSkVo3ioQuGmd9eov0HU_e\nRv8Zfok0Ti_OUgJ9aDE1DBJs4UeDtWjUOestQsTF3SKtHy_L9Qet_OEunvwrzrfHl3eFaTT7ycNU\nGw_1w9zU2SiECCQM8MdECVOS9XgKXE2qZi3Ye6JzUjoHAp4iS745_8BWcUIuJKzemycol-CbY-O0\nyOSNYUeAJzPqfkDAymguPUcAerWo-jCGbvgfix-UurrmRd0ex-0CE5T8bqYaBWRKsHD3yYKjHU7a\nGwF2Bq3wEs31XRPjjF7iNowd79dpAmG57tRc5PqoOm5QctqDndGVdBXJKFT0VUxi-JQHj1HF59h9\nLsTGXw1EjxZqAbKoKfuyODDvZYwWvhfiHbneqAyVHf4RmWv1vHHpuCgEYPfAcOhtMo3iWcblwK_B\ndesGsUB1lKRFOWfAA5GY-P3QooiV3XvwMCwtJjr6FPJPST3lsxYcGLlH-LgFmKTTsSJ_lE_JNzA9\nihMx7hOUpvZRCd-W7IL9ITNrYL82lCbepEkm7H4kRF_Gu5KSaJCTC6jMunS5KLYkxFkuGPQqaNBY\n82XvST90cnnCFKT7pQfjzm_EAwuJgh8aQtvKP-f3MceHfsi61t2wQwTyHdhslDNZe5VID9oJZ35l\n5W4cRIQN09Kg305POE7193uCmjJ8riRh1kRfNllfe6KEslC-gBnhO9Nay45a2-C6EBnD4RYNaPXQ\nGpHFC7-9NOEKFJeewARyIeBuK9iZyXBXCcYVHEVqakkKlzHMUyx-L66cLbXWLA2N884BrdCv_ydh\n7IkQ0CY4KQUfR4vEnckM77lTexRkBex8kOuOhxhTwKY4depBKbReAHWVuINHRkHIisM3ENCeybZE\nz0h8Sgj6gqZsTkuFHm_FNQ1q9XoCioNUp6iZMMXGDZfqmw6HEZKnKDMIUNAP--kv38g-G7ndi05w\nO0QoZdvQtIFgxKTIBDbs522wlVlUVKnPGQLvQNNyX_WGJNpJEv3dYb0sPYJvjx73Y5xF0xazhxbV\nu0P4LZKa99cDmx54nIs7fANBGRT27sZinNdkS3QDYaefyGr2wmA6HTfjnlbOrsr5hSOdIehBK4MD\n01QDT4MMkKyROKzJXzyF98H3Fh4nEH_h2avB2GPNrblOZMR-9pBvDUPvNiZd-TEy0gR5VVvcsL5Y\nU5GeywC3wnM6kdk2ZbDUsajlc74uSmqXSTSIR2T6Sd0pRhhIuMoLXjQtom87b3k_c6GB4NpCF88m\n6zEpv12YoID2dj-x2cZjnKUkDo5DX_Ezuw2Ak4x8X1XQsDoM7qsHWK50xwG-PvwxqoDJJ4AAA1y5\nVIOo8hWEcZ4sXqcSTVykXUHejsnIakTysKThhOU2oAdqJgQv3APKZtoqzX6IDx5f4UNgDzNYJ1wE\n9fuZ_k_msTAMlS-_FfeNMGnv0EfJIRuhb7JrQA4pLg7lO4LdlZeitf8rwKvW3zW0vqViX0UPmz07\nu-q-bbInFpIgVH96yivd3mUaQIntAx6NGOBSXPT_pIGDsY6h5WpzkLL6GlvNYu1VVyHzHFAnns15\nSLpW58L3EjwwmdqZxy-YZo7oT77gogD9yxM8axaapkT1LXNJbN2W7xhxNlGf3Fun2Bg7TDC8QR6M\n9wIKE_5wNff2F5LFA91pffqaGt5nFxkzWbobXkvzZqSgFlMDPrsJNql4KveMn2u9VmTWp9py0jY0\ncJ-5iqI-8-ajR87jkBQ0HsE3tlgN6uzbfi_pxr4-6In_WLJeqwK16_vTD3h9CmFiXKn_bSyA8MfT\npb-FRJ3RiH4ZAXhnofdkCrGFpUkGCX18bitU5TPhgwjgaGbNSpyjzITnjBmXNMMM49rTs47sD6FV\nfWry6ztJgtYsUnj3xsgqsWs53Gfc3TNEddNICU6oJfOVEXJCPKI2dzlSy_8ELPkZqx2oBvFpUjwB\n5v5EqqCUgoF-HCD36WaHLcRxGJ2-ZdsqqgMJRagzQdV7ZW9czLbNpT5RtQ6xBLkb7FJDSraD2fdA\nWK0Unm0u_8uwsH0NpJvb9aryrBH9kt0If18RjsamFtu-dY-ToGOcG08xlzQ8Nq60eXOKc2A2tWl1\n76k=\n";
        vehicleInfo.mIsNeedDecryptCert = true;
        vehicleInfo.mIsTruck = false;
        vehicleInfo.mIsSupportWireless = true;
        vehicleInfo.mIsSupportNativeNavigation = true;
        vehicleInfo.mIsSupportDisplayNaviDataUpdate = true;
        vehicleInfo.mIsNeedShowProjectionOnPhoneCall = false;
        vehicleInfo.mDisplayActivityPackageName = "com.ts.androidauto.app";
        vehicleInfo.mDisplayActivityClassName = "com.ts.androidauto.app.display.AapActivity";
        return vehicleInfo;
    }

    private void initAapVideoConfig480p(VehicleData.AapVideoConfig vehicleInfo) {
        vehicleInfo.mAapDisplayAreaWidth = 1920;
        vehicleInfo.mAapDisplayAreaHeight = 905;
        vehicleInfo.mAapDisplayAreaMarginLeft = 0;
        vehicleInfo.mAapDisplayAreaMarginTop = 0;
        vehicleInfo.mAapResolutionWidth = 800;
        vehicleInfo.mAapResolutionHeight = 377;
        vehicleInfo.mAapResolutionReportDpi = 100;
        vehicleInfo.mAapResolutionRealDpi = 59;
        vehicleInfo.mAapResolutionPixelAspect = 1.0f;
        vehicleInfo.mAapSurfaceViewWidth = 1920;
        vehicleInfo.mAapSurfaceViewHeight = 1151;
        vehicleInfo.mAapSurfaceViewScrollX = 0;
        vehicleInfo.mAapSurfaceViewScrollY = 117;
    }

    private void initAapVideoConfig720p(VehicleData.AapVideoConfig vehicleInfo) {
        vehicleInfo.mAapDisplayAreaWidth = 1920;
        vehicleInfo.mAapDisplayAreaHeight = 906;
        vehicleInfo.mAapDisplayAreaMarginLeft = 0;
        vehicleInfo.mAapDisplayAreaMarginTop = 0;
        vehicleInfo.mAapResolutionWidth = 1280;
        vehicleInfo.mAapResolutionHeight = 604;
        vehicleInfo.mAapResolutionReportDpi = 142;
        vehicleInfo.mAapResolutionRealDpi = 94;
        vehicleInfo.mAapResolutionPixelAspect = 1.0f;
        vehicleInfo.mAapSurfaceViewWidth = 1920;
        vehicleInfo.mAapSurfaceViewHeight = 1080;
        vehicleInfo.mAapSurfaceViewScrollX = 0;
        vehicleInfo.mAapSurfaceViewScrollY = 84;
    }

    private void initAapVideoConfig1080p(VehicleData.AapVideoConfig vehicleInfo) {
        vehicleInfo.mAapDisplayAreaWidth = 1920;
        vehicleInfo.mAapDisplayAreaHeight = 906;
        vehicleInfo.mAapDisplayAreaMarginLeft = 0;
        vehicleInfo.mAapDisplayAreaMarginTop = 0;
        vehicleInfo.mAapResolutionWidth = 1920;
        vehicleInfo.mAapResolutionHeight = 906;
        vehicleInfo.mAapResolutionReportDpi = 213;
        vehicleInfo.mAapResolutionRealDpi = 141;
        vehicleInfo.mAapResolutionPixelAspect = 1.0f;
        vehicleInfo.mAapSurfaceViewWidth = 1920;
        vehicleInfo.mAapSurfaceViewHeight = 1080;
        vehicleInfo.mAapSurfaceViewScrollX = 0;
        vehicleInfo.mAapSurfaceViewScrollY = 84;
    }

    private void initAapClusterVideoConfig480p(VehicleData.AapVideoConfig vehicleInfo) {
        vehicleInfo.mAapDisplayAreaWidth = 800;
        vehicleInfo.mAapDisplayAreaHeight = 480;
        vehicleInfo.mAapDisplayAreaMarginLeft = 0;
        vehicleInfo.mAapDisplayAreaMarginTop = 0;
        vehicleInfo.mAapResolutionWidth = 660;
        vehicleInfo.mAapResolutionHeight = 372;
        vehicleInfo.mAapResolutionReportDpi = 148;
        vehicleInfo.mAapResolutionRealDpi = 167;
        vehicleInfo.mAapResolutionPixelAspect = 1.0f;
        vehicleInfo.mAapSurfaceViewWidth = 800;
        vehicleInfo.mAapSurfaceViewHeight = 480;
        vehicleInfo.mAapSurfaceViewScrollX = 0;
        vehicleInfo.mAapSurfaceViewScrollY = 0;
        vehicleInfo.mTouchScreenWidth = 345;
        vehicleInfo.mTouchScreenHeight = 194;
        vehicleInfo.mAapTouchPointOffsetX = 0;
        vehicleInfo.mAapTouchPointOffsetY = 0;
        vehicleInfo.mAapContentInsetsLeft = 0;
        vehicleInfo.mAapContentInsetsRight = 0;
        vehicleInfo.mAapContentInsetsTop = 0;
        vehicleInfo.mAapContentInsetsBottom = 0;
        vehicleInfo.mAapStableContentInsetsLeft = 0;
        vehicleInfo.mAapStableContentInsetsRight = 0;
        vehicleInfo.mAapStableContentInsetsTop = 0;
        vehicleInfo.mAapStableContentInsetsBottom = 0;
    }

    private void initAapClusterVideoConfig1080pFull(VehicleData.AapVideoConfig vehicleInfo) {
        vehicleInfo.mAapDisplayAreaWidth = 1920;
        vehicleInfo.mAapDisplayAreaHeight = 720;
        vehicleInfo.mAapDisplayAreaMarginLeft = 0;
        vehicleInfo.mAapDisplayAreaMarginTop = 0;
        vehicleInfo.mAapResolutionWidth = 1920;
        vehicleInfo.mAapResolutionHeight = 720;
        vehicleInfo.mAapResolutionReportDpi = 140;
        vehicleInfo.mAapResolutionRealDpi = 200;
        vehicleInfo.mAapResolutionPixelAspect = 1.0f;
        vehicleInfo.mAapSurfaceViewWidth = 1920;
        vehicleInfo.mAapSurfaceViewHeight = 720;
        vehicleInfo.mAapSurfaceViewScrollX = 0;
        vehicleInfo.mAapSurfaceViewScrollY = 180;
        vehicleInfo.mTouchScreenWidth = 244;
        vehicleInfo.mTouchScreenHeight = 91;
        vehicleInfo.mAapTouchPointOffsetX = 0;
        vehicleInfo.mAapTouchPointOffsetY = 0;
        vehicleInfo.mAapContentInsetsLeft = 110;
        vehicleInfo.mAapContentInsetsRight = 110;
        vehicleInfo.mAapContentInsetsTop = 208;
        vehicleInfo.mAapContentInsetsBottom = 130;
        vehicleInfo.mAapStableContentInsetsLeft = 110;
        vehicleInfo.mAapStableContentInsetsRight = 110;
        vehicleInfo.mAapStableContentInsetsTop = 208;
        vehicleInfo.mAapStableContentInsetsBottom = 130;
        vehicleInfo.mAapMarginLeft = 0;
        vehicleInfo.mAapMarginRight = 0;
        vehicleInfo.mAapMarginTop = 180;
        vehicleInfo.mAapMarginBottom = 180;
    }

    public void initAapClusterVideoConfig1080pSmall(VehicleData.AapVideoConfig vehicleInfo) {
        vehicleInfo.mAapDisplayAreaWidth = 1920;
        vehicleInfo.mAapDisplayAreaHeight = 720;
        vehicleInfo.mAapDisplayAreaMarginLeft = 0;
        vehicleInfo.mAapDisplayAreaMarginTop = 0;
        vehicleInfo.mAapResolutionWidth = 1920;
        vehicleInfo.mAapResolutionHeight = 720;
        vehicleInfo.mAapResolutionReportDpi = 140;
        vehicleInfo.mAapResolutionRealDpi = 200;
        vehicleInfo.mAapResolutionPixelAspect = 1.0f;
        vehicleInfo.mAapSurfaceViewWidth = 1920;
        vehicleInfo.mAapSurfaceViewHeight = 720;
        vehicleInfo.mAapSurfaceViewScrollX = 1320;
        vehicleInfo.mAapSurfaceViewScrollY = 180;
        vehicleInfo.mTouchScreenWidth = 345;
        vehicleInfo.mTouchScreenHeight = 194;
        vehicleInfo.mAapTouchPointOffsetX = 0;
        vehicleInfo.mAapTouchPointOffsetY = 0;
        vehicleInfo.mAapContentInsetsLeft = 73;
        vehicleInfo.mAapContentInsetsRight = 73;
        vehicleInfo.mAapContentInsetsTop = 210;
        vehicleInfo.mAapContentInsetsBottom = 159;
        vehicleInfo.mAapStableContentInsetsLeft = 73;
        vehicleInfo.mAapStableContentInsetsRight = 73;
        vehicleInfo.mAapStableContentInsetsTop = 210;
        vehicleInfo.mAapStableContentInsetsBottom = 159;
        vehicleInfo.mAapMarginLeft = 1320;
        vehicleInfo.mAapMarginRight = 0;
        vehicleInfo.mAapMarginTop = 180;
        vehicleInfo.mAapMarginBottom = 180;
    }

    private String getVehicleId(Context context) {
        String vehicleIdSuffix = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), 0);
            try {
                vehicleIdSuffix = sharedPreferences.getString(VEHICLE_ID, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                LogUtil.debug(TAG, "VehicleId preferences is :" + vehicleIdSuffix);
            } catch (ClassCastException e) {
                LogUtil.debug(TAG, "Regenerate VehicleId Suffix");
            }
            if (AndroidAutoConst.DEFAULT_SCREEN_NAME.equals(vehicleIdSuffix)) {
                LogUtil.debug(TAG, "Regenerate VehicleId Suffix");
                try {
                    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                    StringBuilder idBuilder = new StringBuilder();
                    for (int i = 0; i < 16; i++) {
                        idBuilder.append(Integer.toHexString(random.nextInt(16)));
                    }
                    vehicleIdSuffix = idBuilder.toString().toUpperCase(Locale.US);
                } catch (Exception e2) {
                    LogUtil.error(TAG, "No such provider SHA1PRNG");
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(VEHICLE_ID, vehicleIdSuffix);
                editor.apply();
            }
        }
        LogUtil.info(TAG, "VehicleId Suffix is " + vehicleIdSuffix);
        return vehicleIdSuffix;
    }

    private String getSoftwareVersion() {
        String softWareVersion = SystemProperties.get(SYSTEM_VERSION_NAME, AndroidAutoConst.DEFAULT_SCREEN_NAME);
        LogUtil.info(TAG, "getSoftwareVersion softWareVersion:" + softWareVersion);
        if (TextUtils.isEmpty(softWareVersion)) {
            return AAP_HEADUNIT_SW_VER;
        }
        return softWareVersion;
    }

    private String getSerialNumber() {
        String serialNumber = SystemProperties.get(VEHICLE_SN_VERSION_NAME, AndroidAutoConst.DEFAULT_SCREEN_NAME);
        LogUtil.info(TAG, "getSerialNumber serialNumber" + LogEncrypt.MacAddressEncrypt(serialNumber));
        if (TextUtils.isEmpty(serialNumber)) {
            return "00000000";
        }
        return serialNumber;
    }

    private VehicleConst.AapVehicleDrivePosition getDriverPosition(String key) {
        VehicleConst.AapVehicleDrivePosition driverPosition = VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_LEFT;
        String valueOfRudder = SystemProperties.get(key, AndroidAutoConst.DEFAULT_SCREEN_NAME);
        LogUtil.info(TAG, "getDriverPosition  valueOfRudder:" + valueOfRudder);
        if (!TextUtils.isEmpty(valueOfRudder) && "1".equals(valueOfRudder)) {
            return VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_RIGHT;
        }
        return driverPosition;
    }
}
