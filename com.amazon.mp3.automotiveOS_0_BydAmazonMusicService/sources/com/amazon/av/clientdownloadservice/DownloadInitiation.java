package com.amazon.av.clientdownloadservice;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadInitiation implements NamedEnum {
    USER_INITIATED("USER_INITIATED"),
    AUTODOWNLOAD("AUTODOWNLOAD");

    private final String strValue;

    DownloadInitiation(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static DownloadInitiation forValue(String str) {
        Preconditions.checkNotNull(str);
        for (DownloadInitiation downloadInitiation : values()) {
            if (downloadInitiation.strValue.equals(str)) {
                return downloadInitiation;
            }
        }
        return null;
    }
}
