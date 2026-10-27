package com.amazon.mp3.configuration.maps;

import com.amazon.mp3.configuration.models.MarketPlace;
import com.amazon.music.inappmessaging.internal.model.Splash;

/* JADX INFO: loaded from: classes3.dex */
public class MarketPlaceMap extends MultiKeyMap<String, String, MarketPlace> {
    public MarketPlaceMap() {
        super("countryCode", "id", "obfuscatedId", Splash.PARAMS_LOCALE);
    }

    public MarketPlace obfuscatedId(String obfuscatedId) {
        return get("obfuscatedId", obfuscatedId);
    }

    public MarketPlace put(MarketPlace value) {
        put("countryCode", value.countryCode(), value);
        put("id", value.id(), value);
        put("obfuscatedId", value.obfuscatedId(), value);
        put(Splash.PARAMS_LOCALE, (Object[]) value.locales(), value);
        return null;
    }
}
