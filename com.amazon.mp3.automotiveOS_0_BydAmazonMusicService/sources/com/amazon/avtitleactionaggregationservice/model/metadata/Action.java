package com.amazon.avtitleactionaggregationservice.model.metadata;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Action implements NamedEnum {
    FUTURE_PLAYBACK("FUTURE_PLAYBACK"),
    OFFER("OFFER"),
    PLAYBACK(Contexts.Upsell.playback);

    private final String strValue;

    Action(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Action forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Action action : values()) {
            if (action.strValue.equals(str)) {
                return action;
            }
        }
        return null;
    }
}
