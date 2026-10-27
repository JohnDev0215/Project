package com.amazon.av.clientdownloadservice;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadState implements NamedEnum {
    COMPLETE("COMPLETE"),
    INCOMPLETE("INCOMPLETE"),
    DELETED("DELETED");

    private final String strValue;

    DownloadState(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static DownloadState forValue(String str) {
        Preconditions.checkNotNull(str);
        for (DownloadState downloadState : values()) {
            if (downloadState.strValue.equals(str)) {
                return downloadState;
            }
        }
        return null;
    }
}
