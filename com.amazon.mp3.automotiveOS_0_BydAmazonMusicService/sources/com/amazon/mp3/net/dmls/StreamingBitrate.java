package com.amazon.mp3.net.dmls;

import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.media.playback.config.BitRateSelection;

/* JADX INFO: loaded from: classes3.dex */
public enum StreamingBitrate {
    AUTO("ALL", "selectAudioQualityAuto", "selectAudioQualityOnWiFiAuto"),
    KATANA("KATANA", "selectAudioQualityHD", "selectAudioQualityOnWiFiHD"),
    HIGH("HIGH", "selectAudioQualityStandard", "selectAudioQualityOnWiFiStandard"),
    LOW("LOW", "selectAudioQualityDataSaver", "selectAudioQualityOnWiFiDataSaver");

    private String mEntryValue;
    private String metricEventForOTA;
    private String metricEventForWiFi;

    StreamingBitrate(String value, String mtsEventForOTA, String mtsEventForWiFi) {
        this.mEntryValue = value;
        this.metricEventForOTA = mtsEventForOTA;
        this.metricEventForWiFi = mtsEventForWiFi;
    }

    public String getEntryValue() {
        return this.mEntryValue;
    }

    public String getMetricEventForOTA() {
        return this.metricEventForOTA;
    }

    public String getMetricEventForWIFI() {
        return this.metricEventForWiFi;
    }

    public int getEntryResourceId() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$net$dmls$StreamingBitrate[ordinal()];
        if (i == 1) {
            return R.string.dmusic_multi_bitrate_streaming_auto;
        }
        if (i == 2) {
            return R.string.dmusic_multi_bitrate_streaming_HD;
        }
        if (i == 3) {
            return R.string.dmusic_multi_bitrate_streaming_high;
        }
        if (i != 4) {
            return -1;
        }
        return R.string.dmusic_multi_bitrate_streaming_low;
    }

    public int getEntryResourcedescription() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$net$dmls$StreamingBitrate[ordinal()];
        if (i == 1) {
            return R.string.dmusic_multi_bitrate_streaming_auto_description;
        }
        if (i == 2) {
            return R.string.dmusic_multi_bitrate_streaming_HD_description;
        }
        if (i == 3) {
            return R.string.dmusic_multi_bitrate_streaming_high_description;
        }
        if (i != 4) {
            return -1;
        }
        return R.string.dmusic_multi_bitrate_streaming_low_description;
    }

    public static StreamingBitrate fromEntryValue(Object entryValue) {
        for (StreamingBitrate streamingBitrate : values()) {
            if (streamingBitrate.getEntryValue().equals(entryValue)) {
                return streamingBitrate;
            }
        }
        return AUTO;
    }

    public static StreamingBitrate fromEntryValueForDMLS(Object entryValue) {
        StreamingBitrate streamingBitrate = AUTO;
        for (StreamingBitrate streamingBitrate2 : values()) {
            if (streamingBitrate2.getEntryValue().equals(entryValue)) {
                streamingBitrate = streamingBitrate2;
            }
        }
        return streamingBitrate == KATANA ? HIGH : streamingBitrate;
    }

    public BitRateSelection convertToBitRateSelection() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$net$dmls$StreamingBitrate[ordinal()];
        if (i == 2) {
            return BitRateSelection.KATANA;
        }
        if (i == 3) {
            return BitRateSelection.HIGH;
        }
        if (i == 4) {
            return BitRateSelection.LOW;
        }
        return BitRateSelection.AUTO;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.net.dmls.StreamingBitrate$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$net$dmls$StreamingBitrate;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection;

        static {
            int[] iArr = new int[BitRateSelection.values().length];
            $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection = iArr;
            try {
                iArr[BitRateSelection.KATANA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.HIGH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.LOW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[StreamingBitrate.values().length];
            $SwitchMap$com$amazon$mp3$net$dmls$StreamingBitrate = iArr2;
            try {
                iArr2[StreamingBitrate.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$net$dmls$StreamingBitrate[StreamingBitrate.KATANA.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$net$dmls$StreamingBitrate[StreamingBitrate.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$net$dmls$StreamingBitrate[StreamingBitrate.LOW.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static StreamingBitrate fromBitRateSelection(final BitRateSelection bitRateSelection) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[bitRateSelection.ordinal()];
        if (i == 1) {
            return KATANA;
        }
        if (i == 2 || i == 3) {
            return HIGH;
        }
        if (i == 4) {
            return LOW;
        }
        return AUTO;
    }
}
