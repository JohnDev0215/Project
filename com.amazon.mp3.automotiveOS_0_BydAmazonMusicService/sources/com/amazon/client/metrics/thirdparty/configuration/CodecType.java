package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public enum CodecType {
    PROTOCOL_BUFFERS("ProtocolBuffers"),
    STRING("String");

    private final String mName;

    CodecType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public static CodecType fromString(String str) throws MetricsConfigurationException {
        for (CodecType codecType : values()) {
            if (codecType.getName().equalsIgnoreCase(str)) {
                return codecType;
            }
        }
        throw new MetricsConfigurationException(str + " is not a valid Codec");
    }
}
