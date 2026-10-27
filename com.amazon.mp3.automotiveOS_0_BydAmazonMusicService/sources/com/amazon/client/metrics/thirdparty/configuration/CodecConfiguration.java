package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public class CodecConfiguration {
    final CodecType mCodecType;
    final String mCodecVersion;

    public CodecConfiguration(CodecType codecType, String str) throws MetricsConfigurationException {
        if (codecType == null) {
            throw new MetricsConfigurationException("CodecType is null in configuration");
        }
        if (str == null || str.equals("")) {
            throw new MetricsConfigurationException("Codec version is null in configuration");
        }
        this.mCodecType = codecType;
        this.mCodecVersion = str;
    }

    public CodecType getCodecType() {
        return this.mCodecType;
    }

    public String getCodecVersion() {
        return this.mCodecVersion;
    }
}
