package com.amazon.atv.playbackauthority.service;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum VideoFeature implements NamedEnum {
    HDR("HDR");

    private final String strValue;

    VideoFeature(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static VideoFeature forValue(String str) {
        Preconditions.checkNotNull(str);
        for (VideoFeature videoFeature : values()) {
            if (videoFeature.strValue.equals(str)) {
                return videoFeature;
            }
        }
        return null;
    }
}
