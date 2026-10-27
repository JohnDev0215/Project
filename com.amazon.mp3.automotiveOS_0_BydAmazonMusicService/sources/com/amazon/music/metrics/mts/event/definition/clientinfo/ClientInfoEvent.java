package com.amazon.music.metrics.mts.event.definition.clientinfo;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class ClientInfoEvent extends MTSEvent {
    public ClientInfoEvent(ClientInfoProvider clientInfoProvider, String str, String str2, Boolean bool) {
        super("clientInfo");
        addClientInfo(clientInfoProvider, str, str2, bool);
    }

    private void addClientInfo(ClientInfoProvider clientInfoProvider, String str, String str2, Boolean bool) {
        addAttribute(Splash.PARAMS_APP_VERSION, clientInfoProvider.getAppVersion());
        addAttribute("appBuild", clientInfoProvider.getBuild());
        addAttribute(DeviceInformation.PARAMS_DEVICE_MODEL, clientInfoProvider.getModel());
        addAttribute("firmwareVersion", clientInfoProvider.getFirmwareVersion());
        addAttribute("deviceBuildVersion", clientInfoProvider.getDeviceBuild());
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, clientInfoProvider.getRemoteSource());
        addAttribute("appEndpoint", clientInfoProvider.getEndpoint());
        addAttribute("appStage", clientInfoProvider.getAppStage());
        addAttribute("associateTag", clientInfoProvider.getAssociateTag());
        addAttribute("appOtaState", getOtaStateString(clientInfoProvider));
        addAttribute("kernelVersion", clientInfoProvider.getKernelVersion());
        addAttribute("hardwareVersion", clientInfoProvider.getHardwareVersion());
        addAttribute("displayHeightPixels", clientInfoProvider.getScreenHeight());
        addAttribute("displayWidthPixels", clientInfoProvider.getScreenWidth());
        if (clientInfoProvider.getBrowseSubscriptionMode() != null) {
            addAttribute("browseSubscriptionMode", clientInfoProvider.getBrowseSubscriptionMode().getMetricValue());
        }
        addAttribute("isAppColdStart", clientInfoProvider.getIsAppColdStart());
        addAttribute("appIdentity", str);
        addAttribute("subDeviceId", str2);
        if (bool != null) {
            addAttribute("isPushNotificationEnabled", bool);
        }
    }

    private String getOtaStateString(ClientInfoProvider clientInfoProvider) {
        return clientInfoProvider.isOtaEnabled() ? "ota" : "no ota";
    }
}
