package com.amazon.mp3.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public enum Market {
    US("ATVPDKIKX0DER", "ATVPDKIKX0DER", "Amazon.com", "US", new String[]{"US"}),
    UK("A1F83G8C2ARO7P", "A1F83G8C2ARO7P", "Amazon.co.uk", "UK", new String[]{"UK", "GB"}),
    GERMANY("A1PA6795UKMFR9", "A1PA6795UKMFR9", "Amazon.de", "DE", new String[]{"DE", "AT", "CH"}),
    FRANCE("A13V1IB3VIYZZH", "A13V1IB3VIYZZH", "Amazon.fr", "FR", new String[]{"FR"}),
    SPAIN("A1RKKUPIHCS9HS", "AJZF8LZ1EJVJN", "Amazon.es", "ES", new String[]{"ES"}),
    ITALY("APJ6JRA9NG5V4", "A3HOBANJMCMD83", "Amazon.it", "IT", new String[]{"IT"}),
    JAPAN("A1VC38T7YXB528", "A1VC38T7YXB528", "Amazon.co.jp", "JP", new String[]{"JP"}),
    CANADA("A2EUQ1WTGCTBG2", "A2EUQ1WTGCTBG2", "Amazon.ca", "CA", new String[]{"CA"}),
    CHINA("AAHKV2X7AFYLW", "AAHKV2X7AFYLW", "Amazon.cn", "CN", new String[]{"CN"}),
    BRAZIL("A2Q3Y263D00KWC", "A2Q3Y263D00KWC", "Amazon.com.br", "BR", new String[]{"BR"}),
    INDIA("A21TJRUUN4KGV", "A2XZLSVIQ0F4JT", "Amazon.in", "IN", new String[]{"IN"}),
    MEXICO("A1AM78C64UM0Y8", "A3P3J5A7D2ZVXI", "Amazon.com.mx", "MX", new String[]{"MX"}),
    AUSTRALIA("A39IBJ37TRP1C6", "A1RNPCQ4K8U27I", "Amazon.com.au", "AU", new String[]{"AU"}),
    NETHERLANDS("A1805IZSGTT6HS", "A1M3WC0SJ3A38T", "Amazon.nl", "NL", new String[]{"NL"});

    private static final Map<String, Market> COUNTRY_TO_MARKET;
    private static final Map<String, Market> OBFUSCATED_MARKET_TO_MARKET;
    private static final String SUBDOMAIN_MUSIC = "music";
    private final String mCountryCode;
    private final String mDevoObfuscatedMarket;
    private final String mOfficialDomain;
    private final String mProdObfuscatedMarket;
    private final String[] mSupportedCountryCodes;
    private static final Market DEFAULT_MARKET = US;

    static {
        Market[] marketArrValues = values();
        OBFUSCATED_MARKET_TO_MARKET = new HashMap((int) (((marketArrValues.length * 2) / 0.75f) + 0.5f));
        COUNTRY_TO_MARKET = new HashMap((int) ((marketArrValues.length / 0.75f) + 0.5f));
        for (Market market : marketArrValues) {
            Map<String, Market> map = OBFUSCATED_MARKET_TO_MARKET;
            map.put(market.mProdObfuscatedMarket, market);
            map.put(market.mDevoObfuscatedMarket, market);
            for (String str : market.mSupportedCountryCodes) {
                COUNTRY_TO_MARKET.put(str.toUpperCase(Locale.ROOT), market);
            }
        }
    }

    Market(String prodObfuscatedMarket, String devoObfuscatedMarket, String officialDomain, String countryCode, String[] supportedCountryCodes) {
        this.mProdObfuscatedMarket = prodObfuscatedMarket;
        this.mDevoObfuscatedMarket = devoObfuscatedMarket;
        this.mOfficialDomain = officialDomain;
        this.mCountryCode = countryCode;
        this.mSupportedCountryCodes = supportedCountryCodes;
    }

    public String getPrimaryCountryCode() {
        return this.mCountryCode;
    }

    public String getProdObfuscatedMarket() {
        return this.mProdObfuscatedMarket;
    }

    public static Market fromObfuscatedMarket(final String obfuscatedMarket) {
        Market market = OBFUSCATED_MARKET_TO_MARKET.get(obfuscatedMarket);
        return market == null ? DEFAULT_MARKET : market;
    }
}
