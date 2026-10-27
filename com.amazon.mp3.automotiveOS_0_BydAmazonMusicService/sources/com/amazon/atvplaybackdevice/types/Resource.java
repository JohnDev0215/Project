package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Resource implements NamedEnum {
    CatalogMetadata("CatalogMetadata"),
    CuepointPlaylist("CuepointPlaylist"),
    DevicePlaybackConfiguration("DevicePlaybackConfiguration"),
    LiveSchedule("LiveSchedule"),
    XRayMetadata("XRayMetadata"),
    SyeUrlsV2("SyeUrlsV2"),
    AudioVideoUrls("AudioVideoUrls"),
    PlaybackUrls("PlaybackUrls"),
    LegacyMetadata("LegacyMetadata"),
    Widevine2License("Widevine2License"),
    ForcedNarratives("ForcedNarratives"),
    FairPlayApplicationCertificate("FairPlayApplicationCertificate"),
    SubtitlePresets("SubtitlePresets"),
    TrickplayUrls("TrickplayUrls"),
    FairPlayLicense("FairPlayLicense"),
    SubtitleUrls("SubtitleUrls"),
    SyeUrls("SyeUrls"),
    PlayReadyLicense("PlayReadyLicense"),
    TransitionTimecodes("TransitionTimecodes"),
    LinearRestrictions("LinearRestrictions"),
    PlaybackSettings("PlaybackSettings"),
    WhisperSyncBookmark("WhisperSyncBookmark");

    private final String strValue;

    Resource(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Resource forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Resource resource : values()) {
            if (resource.strValue.equals(str)) {
                return resource;
            }
        }
        return null;
    }
}
