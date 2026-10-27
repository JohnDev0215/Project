package com.amazon.mp3.configuration.models;

/* JADX INFO: loaded from: classes3.dex */
public final class MarketPlace {
    private String mCountryCode;
    private String mId;
    private String[] mLocales;
    private String mObfuscatedId;

    public MarketPlace(String country, String id, String obfuscated, String[] locales) {
        this.mCountryCode = country;
        this.mId = id;
        this.mObfuscatedId = obfuscated;
        this.mLocales = locales;
    }

    public String countryCode() {
        return this.mCountryCode;
    }

    public String id() {
        return this.mId;
    }

    public String obfuscatedId() {
        return this.mObfuscatedId;
    }

    public String[] locales() {
        return this.mLocales;
    }
}
