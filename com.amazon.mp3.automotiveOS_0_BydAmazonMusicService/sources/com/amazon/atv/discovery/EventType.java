package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum EventType implements NamedEnum {
    CURRENTTERRITORY("currentTerritory"),
    RECORDTERRITORY("recordTerritory"),
    WATCHLIST("watchlist"),
    PARENTALCONTROL("parentalControl"),
    SUBSCRIPTION("subscription"),
    DOWNLOAD("download"),
    MARKETPLACE(IntlConfiguration.MARKETPLACE),
    PREFERREDLANGUAGECHANGE("preferredLanguageChange"),
    OWNERSHIP("ownership"),
    PLAYBACK("playback"),
    LISTREMOVE("listRemove");

    private final String strValue;

    EventType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static EventType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (EventType eventType : values()) {
            if (eventType.strValue.equals(str)) {
                return eventType;
            }
        }
        return null;
    }
}
