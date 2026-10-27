package com.amazon.music.marketplace;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class Marketplace {
    private final String countryCode;
    private final String id;
    private final List<String> locale;
    private final String obfuscatedId;
    private final String region;
    public static final Marketplace NONE = new Marketplace("none", "none", "none", "none");
    public static final Marketplace USA = new Marketplace("1", "ATVPDKIKX0DER", "US", "en_US");
    public static final Marketplace UK = new Marketplace("3", "A1F83G8C2ARO7P", "UK", "en_UK");
    public static final Marketplace GERMANY = new Marketplace("4", "A1PA6795UKMFR9", "DE", "de_DE");
    public static final Marketplace FRANCE = new Marketplace("5", "A13V1IB3VIYZZH", "FR", "fr_FR");
    public static final Marketplace JAPAN = new Marketplace("6", "A1VC38T7YXB528", "JP", "ja_JP");
    public static final Marketplace SPAIN = new Marketplace("44551", "A1RKKUPIHCS9HS", "ES", "es_ES");
    public static final Marketplace ITALY = new Marketplace("35691", "APJ6JRA9NG5V4", "IT", "it_IT");
    public static final Marketplace ROW_NA = new Marketplace("1367890", "ART4WZ8MWBX2Y", "", "");
    public static final Marketplace ROE_EU = new Marketplace("331051", "A3K6Y4MI8GDYMT", "", "");
    public static final Marketplace ROW_FE = new Marketplace("151232", "A15PK738MTQHSO", "", "");

    private Marketplace(String str, String str2, String str3, String... strArr) {
        Validate.notEmpty(str, "id can't be null", new Object[0]);
        Validate.notEmpty(str2, "obfuscatedId can't be null", new Object[0]);
        Validate.notNull(str3, "country code can't be null", new Object[0]);
        Validate.notEmpty(strArr, "locale can't have null element", new Object[0]);
        this.id = str;
        this.obfuscatedId = str2;
        this.countryCode = str3;
        this.locale = Arrays.asList(strArr);
        this.region = RegionResolver.get(str2);
    }

    public String getId() {
        return this.id;
    }

    public String getObfuscatedId() {
        return this.obfuscatedId;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getRegion() {
        return this.region;
    }

    public Iterable<String> getLocale() {
        return this.locale;
    }
}
