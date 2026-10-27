package com.amazon.mp3.download;

import com.amazon.digitalmusicplayback.QualitySetting;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.find.api.RemoteSearchApi;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadQualityPreferences.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/download/DownloadQualityPreferences;", "", "entryValue", "", "metricEvent", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getEntryValue", "()Ljava/lang/String;", "getMetricEvent", "convertToQualitySetting", "Lcom/amazon/digitalmusicplayback/QualitySetting;", "getEntryResourceDescription", "", "getEntryResourceId", "AUTO", RemoteSearchApi.HD_ASSET_QUALITY, "HIGH", "LOW", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum DownloadQualityPreferences {
    AUTO("KATANA", "selectDownloadQualityAuto"),
    HD(RemoteSearchApi.HD_ASSET_QUALITY, "selectDownloadQualityHD"),
    HIGH("HIGH", "selectDownloadQualityStandard"),
    LOW("LOW", "selectDownloadQualityDataSaver");


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String entryValue;
    private final String metricEvent;

    /* JADX INFO: compiled from: DownloadQualityPreferences.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadQualityPreferences.values().length];
            iArr[DownloadQualityPreferences.AUTO.ordinal()] = 1;
            iArr[DownloadQualityPreferences.HD.ordinal()] = 2;
            iArr[DownloadQualityPreferences.HIGH.ordinal()] = 3;
            iArr[DownloadQualityPreferences.LOW.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final DownloadQualityPreferences fromEntryValue(String str) {
        return INSTANCE.fromEntryValue(str);
    }

    DownloadQualityPreferences(String str, String str2) {
        this.entryValue = str;
        this.metricEvent = str2;
    }

    public final String getEntryValue() {
        return this.entryValue;
    }

    public final String getMetricEvent() {
        return this.metricEvent;
    }

    /* JADX INFO: compiled from: DownloadQualityPreferences.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/download/DownloadQualityPreferences$Companion;", "", "()V", "fromEntryValue", "Lcom/amazon/mp3/download/DownloadQualityPreferences;", "entryValue", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DownloadQualityPreferences fromEntryValue(String entryValue) {
            DownloadQualityPreferences[] downloadQualityPreferencesArrValues = DownloadQualityPreferences.values();
            int length = downloadQualityPreferencesArrValues.length;
            int i = 0;
            while (i < length) {
                DownloadQualityPreferences downloadQualityPreferences = downloadQualityPreferencesArrValues[i];
                i++;
                if (Intrinsics.areEqual(downloadQualityPreferences.getEntryValue(), entryValue)) {
                    return downloadQualityPreferences;
                }
            }
            return DownloadQualityPreferences.HIGH;
        }
    }

    public final int getEntryResourceId() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return R.string.dmusic_download_preferences_auto;
        }
        if (i == 2) {
            return R.string.dmusic_download_preferences_HD;
        }
        if (i == 3) {
            return R.string.dmusic_download_preferences_high;
        }
        if (i == 4) {
            return R.string.dmusic_download_preferences_low;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final int getEntryResourceDescription() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return R.string.dmusic_setting_download_katana_description;
        }
        if (i == 2) {
            return R.string.dmusic_download_preferences_hd_description;
        }
        if (i == 3) {
            return R.string.dmusic_download_preferences_high_description;
        }
        if (i != 4) {
            return -1;
        }
        return R.string.dmusic_download_preferences_low_description;
    }

    public final QualitySetting convertToQualitySetting() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return QualitySetting.BESTAVAILABLE;
        }
        if (i == 2) {
            return QualitySetting.HD;
        }
        if (i == 3) {
            return QualitySetting.STANDARD;
        }
        if (i == 4) {
            return QualitySetting.SAVER;
        }
        throw new NoWhenBranchMatchedException();
    }
}
