package com.ts.androidauto.app.common;

import ts.car.service.androidauto.data.UiConfigData;

/* JADX INFO: loaded from: classes2.dex */
public class UiConfigLoader {
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_BOTTOM = 130;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_LEFT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_RIGHT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_CONTENT_INSETS_1080P_TOP = 208;
    private static final int AAP_CLUSTER_UICONFIG_FULL_MARGIN_BOTTOM = 180;
    private static final int AAP_CLUSTER_UICONFIG_FULL_MARGIN_LEFT = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_MARGIN_RIGHT = 0;
    private static final int AAP_CLUSTER_UICONFIG_FULL_MARGIN_TOP = 180;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_1080P_BOTTOM = 130;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_1080P_LEFT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_1080P_RIGHT = 110;
    private static final int AAP_CLUSTER_UICONFIG_FULL_STABLE_CONTENT_INSETS_1080P_TOP = 208;
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
    private static final String TAG = "AAP" + UiConfigLoader.class.getSimpleName();
    private static UiConfigLoader mUiConfigLoaderInstance = null;
    private UiConfigData mUiConfigData;

    public static synchronized UiConfigLoader getInstance() {
        if (mUiConfigLoaderInstance == null) {
            mUiConfigLoaderInstance = new UiConfigLoader();
        }
        return mUiConfigLoaderInstance;
    }

    private UiConfigLoader() {
        LogUtil.info(TAG, "UiConfigLoader constructor");
    }

    public UiConfigData getAapClusterSmallUiConfig1080p() {
        UiConfigData uiConfigData = new UiConfigData();
        UiConfigData.AapClusterUiConfig uiConfig = uiConfigData.mAapClusterUiConfig1080p;
        uiConfig.mAapContentInsetsLeft = 73;
        uiConfig.mAapContentInsetsRight = 73;
        uiConfig.mAapContentInsetsTop = 210;
        uiConfig.mAapContentInsetsBottom = 159;
        uiConfig.mAapStableContentInsetsLeft = 73;
        uiConfig.mAapStableContentInsetsRight = 73;
        uiConfig.mAapStableContentInsetsTop = 210;
        uiConfig.mAapStableContentInsetsBottom = 159;
        uiConfig.mAapMarginLeft = 1320;
        uiConfig.mAapMarginRight = 0;
        uiConfig.mAapMarginTop = 180;
        uiConfig.mAapMarginBottom = 180;
        return uiConfigData;
    }

    public UiConfigData getAapClusterFullUiConfig1080p() {
        UiConfigData uiConfigData = new UiConfigData();
        UiConfigData.AapClusterUiConfig uiConfig = uiConfigData.mAapClusterUiConfig1080p;
        uiConfig.mAapContentInsetsLeft = 110;
        uiConfig.mAapContentInsetsRight = 110;
        uiConfig.mAapContentInsetsTop = 208;
        uiConfig.mAapContentInsetsBottom = 130;
        uiConfig.mAapStableContentInsetsLeft = 110;
        uiConfig.mAapStableContentInsetsRight = 110;
        uiConfig.mAapStableContentInsetsTop = 208;
        uiConfig.mAapStableContentInsetsBottom = 130;
        uiConfig.mAapMarginLeft = 0;
        uiConfig.mAapMarginRight = 0;
        uiConfig.mAapMarginTop = 180;
        uiConfig.mAapMarginBottom = 180;
        return uiConfigData;
    }
}
