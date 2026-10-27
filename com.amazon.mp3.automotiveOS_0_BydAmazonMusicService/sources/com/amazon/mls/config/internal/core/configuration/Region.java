package com.amazon.mls.config.internal.core.configuration;

import com.amazon.mls.config.ConfigurationApi;
import com.amazon.mls.config.internal.core.exceptions.ConfigurationException;
import com.amazon.mls.config.internal.core.metrics.InternalMetrics;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public enum Region {
    NA,
    EU,
    FE,
    CN;

    private static final Region DEFAULT_REGION;
    private static final String TAG;

    static {
        Region region = NA;
        TAG = Region.class.getSimpleName();
        DEFAULT_REGION = region;
    }

    public static Region getRegionFromMarketplace(String str) {
        if ("ATVPDKIKX0DER".equals(str) || "A2EUQ1WTGCTBG2".equals(str) || "A2Q3Y263D00KWC".equals(str) || "AZXD3QD5B39HD".equals(str) || "A1AM78C64UM0Y8".equals(str) || "A3P3J5A7D2ZVXI".equals(str)) {
            return NA;
        }
        if ("A1F83G8C2ARO7P".equals(str) || "A1PA6795UKMFR9".equals(str) || "A13V1IB3VIYZZH".equals(str) || "APJ6JRA9NG5V4".equals(str) || "A3HOBANJMCMD83".equals(str) || "A1RKKUPIHCS9HS".equals(str) || "AJZF8LZ1EJVJN".equals(str) || "A1805IZSGTT6HS".equals(str) || "A1M3WC0SJ3A38T".equals(str) || "A21TJRUUN4KGV".equals(str) || "A2XZLSVIQ0F4JT".equals(str) || "ARBP9OOSHTCHU".equals(str) || "AUJPM9XGFJRC7".equals(str) || "A2VIGQ35RCS4UG".equals(str) || "A34GYYCZVDBSIK".equals(str) || "A17E79C6D8DWNP".equals(str) || "A1MQPSGJ6U9Q54".equals(str) || "A33AVAJ2PDY3EV".equals(str) || "A3CQBQD3RGPJR8".equals(str) || "A2NODRKZP88ZB9".equals(str) || "AT614YYYNOC1S".equals(str) || "A1C3SOZRARQ6R3".equals(str) || "AKY4K4WKH21YQ".equals(str)) {
            return EU;
        }
        if ("A1VC38T7YXB528".equals(str) || "A39IBJ37TRP1C6".equals(str) || "A1RNPCQ4K8U27I".equals(str) || "AD2EMQ3L3PG8S".equals(str) || "A38NPJYVS5YHNH".equals(str) || "A1DQ6JV7I20JSG".equals(str) || "A19VAU5U5O7RUS".equals(str)) {
            return FE;
        }
        if ("AAHKV2X7AFYLW".equals(str)) {
            return CN;
        }
        if (ConfigurationApi.getInstance().getRuntimeSettings().isDebug()) {
            throw new ConfigurationException(String.format(Locale.US, "Mls was configured with an unrecognized marketplace id: %s. In prod we'd use the default region: %s.", str, DEFAULT_REGION));
        }
        InternalMetrics.logCounter("core_unrecognized_marketplace_w", 1L);
        return NA;
    }

    public static Region getRegionFromString(String str) {
        Region region = DEFAULT_REGION;
        if (str != null) {
            String upperCase = str.toUpperCase(Locale.US);
            upperCase.hashCode();
            switch (upperCase) {
                case "CN":
                    break;
                case "EU":
                    break;
                case "FE":
                    break;
                case "NA":
                    break;
                default:
                    InternalMetrics.logCounter("core_deserialize_default_region_e", 1L);
                    break;
            }
            return region;
        }
        InternalMetrics.logCounter("core_deserialize_default_region_e", 1L);
        return region;
    }
}
