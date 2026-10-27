package com.amazon.atv.title.v1.fragment.playback.v1;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.avod.util.json.NamedEnum;
import com.amazon.music.find.api.RemoteSearchApi;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum DisplayTreatment implements NamedEnum {
    UHD(RemoteSearchApi.UHD_ASSET_QUALITY),
    SD("SD"),
    PRIME(UpsellType.PRIME),
    RENTAL("RENTAL"),
    HD(RemoteSearchApi.HD_ASSET_QUALITY),
    PURCHASE("PURCHASE");

    private final String strValue;

    DisplayTreatment(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static DisplayTreatment forValue(String str) {
        Preconditions.checkNotNull(str);
        for (DisplayTreatment displayTreatment : values()) {
            if (displayTreatment.strValue.equals(str)) {
                return displayTreatment;
            }
        }
        return null;
    }
}
