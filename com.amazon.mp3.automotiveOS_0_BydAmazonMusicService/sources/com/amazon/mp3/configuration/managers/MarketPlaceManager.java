package com.amazon.mp3.configuration.managers;

import com.amazon.mp3.configuration.maps.MarketPlaceMap;
import com.amazon.mp3.configuration.models.MarketPlace;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class MarketPlaceManager {
    private static final String TAG = "MarketPlaceManager";
    private MarketPlaceMap mMarketPlaces;

    public static class UnknownMarketplaceException extends Exception {
    }

    public MarketPlaceManager(MarketPlaceMap marketPlaces) {
        this.mMarketPlaces = marketPlaces;
    }

    public MarketPlace obfuscatedId(String obfuscatedId) throws UnknownMarketplaceException {
        MarketPlace marketPlaceObfuscatedId = this.mMarketPlaces.obfuscatedId(obfuscatedId);
        if (marketPlaceObfuscatedId != null) {
            return marketPlaceObfuscatedId;
        }
        Log.error(TAG, "%s obfuscatedId not defined in the environment file.", obfuscatedId);
        throw new UnknownMarketplaceException();
    }
}
