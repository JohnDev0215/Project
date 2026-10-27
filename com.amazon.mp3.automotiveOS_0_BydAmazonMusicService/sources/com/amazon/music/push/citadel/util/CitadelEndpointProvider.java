package com.amazon.music.push.citadel.util;

import com.amazon.music.push.config.ConfigProvider;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelEndpointProvider {
    public static String getEndpoint(String str, boolean z) {
        return ConfigProvider.getString("citadel_endpoint_" + str.toLowerCase() + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + "prod");
    }
}
