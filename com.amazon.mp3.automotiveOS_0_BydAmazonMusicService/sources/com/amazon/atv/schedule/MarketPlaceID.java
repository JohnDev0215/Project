package com.amazon.atv.schedule;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum MarketPlaceID implements NamedEnum {
    ROE_EU("A3K6Y4MI8GDYMT"),
    DE("A1PA6795UKMFR9"),
    ROE_EU_DEVO("A1MJEQWJOUEPEQ"),
    ROW_EU_DEVO("A1D7Z662KHSYZY"),
    JP("A1VC38T7YXB528"),
    ROW_NA("ART4WZ8MWBX2Y"),
    ROW_NA_DEVO("A3U49GLEJ1PS4Y"),
    ROW_EU("A2MFUE2XK8ZSSY"),
    GB("A1F83G8C2ARO7P"),
    ROW_FE("A15PK738MTQHSO"),
    ROW_FE_DEVO("A9QSUUOYRWVOG"),
    US("ATVPDKIKX0DER");

    private final String strValue;

    MarketPlaceID(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static MarketPlaceID forValue(String str) {
        Preconditions.checkNotNull(str);
        for (MarketPlaceID marketPlaceID : values()) {
            if (marketPlaceID.strValue.equals(str)) {
                return marketPlaceID;
            }
        }
        return null;
    }
}
