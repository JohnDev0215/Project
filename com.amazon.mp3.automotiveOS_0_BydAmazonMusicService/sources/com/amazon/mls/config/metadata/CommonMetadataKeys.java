package com.amazon.mls.config.metadata;

import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;

/* JADX INFO: loaded from: classes3.dex */
public enum CommonMetadataKeys {
    SchemaId("schemaId"),
    ProducerId("producerId"),
    MessageId("messageId"),
    Timestamp(TetheredMessageKey.timestamp),
    DirectedCustomerId("directedCustomerId"),
    SessionId(Splash.PARAMS_SESSION_ID),
    DeviceId(Splash.PARAMS_DEVICE_ID),
    OperatingSystemName("operatingSystemName"),
    OperatingSystemVersion("operatingSystemVersion"),
    ApplicationName("applicationName"),
    ApplicationVersion("applicationVersion"),
    ObfuscatedMarketplaceId("obfuscatedMarketplaceId"),
    UserAgent("userAgent"),
    DeviceType(Splash.PARAMS_DEVICE_TYPE),
    DeviceModel(DeviceInformation.PARAMS_DEVICE_MODEL);

    private final String keyValue;

    public String getKeyValue() {
        return this.keyValue;
    }

    CommonMetadataKeys(String str) {
        this.keyValue = str;
    }
}
