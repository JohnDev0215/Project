package com.amazon.urlvending.types;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum Drm implements NamedEnum {
    WIDEVINE("WIDEVINE"),
    CENC("CENC"),
    FAIRPLAY("FAIRPLAY"),
    PLAYREADY("PLAYREADY"),
    NONE(PlaybackMode.NONE);

    private final String strValue;

    Drm(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Drm forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Drm drm : values()) {
            if (drm.strValue.equals(str)) {
                return drm;
            }
        }
        return null;
    }
}
