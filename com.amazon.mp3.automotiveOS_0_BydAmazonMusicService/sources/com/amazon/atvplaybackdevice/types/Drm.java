package com.amazon.atvplaybackdevice.types;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Drm implements NamedEnum {
    Widevine2("Widevine2"),
    FairPlay("FairPlay"),
    Marlin("Marlin"),
    PlayReady("PlayReady"),
    WidevineAbr("WidevineAbr"),
    WidevinePdl("WidevinePdl"),
    CENC("CENC"),
    WMRM("WMRM"),
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
