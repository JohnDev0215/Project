package com.amazon.music.metrics.mts.event.definition.clientinfo;

import com.amazon.music.metrics.mts.event.types.BrowseSubscriptionModeType;

/* JADX INFO: loaded from: classes4.dex */
public interface ClientInfoProvider {
    String getAppStage();

    String getAppVersion();

    String getAssociateTag();

    BrowseSubscriptionModeType getBrowseSubscriptionMode();

    String getBuild();

    String getDeviceBuild();

    String getEndpoint();

    String getFirmwareVersion();

    String getHardwareVersion();

    boolean getIsAppColdStart();

    String getKernelVersion();

    String getModel();

    String getRemoteSource();

    int getScreenHeight();

    int getScreenWidth();

    boolean isOtaEnabled();
}
