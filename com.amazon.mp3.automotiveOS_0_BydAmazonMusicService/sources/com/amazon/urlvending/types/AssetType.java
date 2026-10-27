package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum AssetType implements NamedEnum {
    Poppin("Poppin"),
    CsaiAdUrls("CsaiAdUrls"),
    FullVideo("FullVideo"),
    TrickPlay("TrickPlay"),
    TrailerVideo("TrailerVideo"),
    VideoRoll("VideoRoll"),
    PreviewVideo("PreviewVideo"),
    TimedText("TimedText"),
    ForcedNarrative("ForcedNarrative"),
    LiveStreaming("LiveStreaming");

    private final String strValue;

    AssetType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AssetType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AssetType assetType : values()) {
            if (assetType.strValue.equals(str)) {
                return assetType;
            }
        }
        return null;
    }
}
