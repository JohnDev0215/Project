package com.amazon.device.ads;

import com.amazon.device.ads.JSONUtils;
import java.util.Properties;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class DebugProperties {
    private final Properties debugProperties;
    private final JSONUtils.JSONUtilities jsonUtilities;
    private final MobileAdsLogger logger;
    private static final DebugProperties instance = new DebugProperties();
    private static final String LOGTAG = DebugProperties.class.getSimpleName();

    public DebugProperties() {
        this(new JSONUtils.JSONUtilities(), new MobileAdsLoggerFactory());
    }

    DebugProperties(JSONUtils.JSONUtilities jSONUtilities, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.debugProperties = new Properties();
        this.jsonUtilities = jSONUtilities;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public static DebugProperties getInstance() {
        return instance;
    }

    public String getDebugPropertyAsString(String str, String str2) {
        return this.debugProperties.getProperty(str, str2);
    }

    public Integer getDebugPropertyAsInteger(String str, Integer num) {
        String property = this.debugProperties.getProperty(str);
        if (property == null) {
            return num;
        }
        try {
            return Integer.valueOf(Integer.parseInt(property));
        } catch (NumberFormatException unused) {
            this.logger.e("Unable to parse integer debug property - property: %s, value: %s", str, property);
            return num;
        }
    }

    public Boolean getDebugPropertyAsBoolean(String str, Boolean bool) {
        String property = this.debugProperties.getProperty(str);
        if (property == null) {
            return bool;
        }
        try {
            return Boolean.valueOf(Boolean.parseBoolean(property));
        } catch (NumberFormatException unused) {
            this.logger.e("Unable to parse boolean debug property - property: %s, value: %s", str, property);
            return bool;
        }
    }

    public Long getDebugPropertyAsLong(String str, Long l) {
        String property = this.debugProperties.getProperty(str);
        if (property == null) {
            return l;
        }
        try {
            return Long.valueOf(Long.parseLong(property));
        } catch (NumberFormatException unused) {
            this.logger.e("Unable to parse long debug property - property: %s, value: %s", str, property);
            return l;
        }
    }

    public void clearDebugProperties() {
        this.debugProperties.clear();
    }

    public void overwriteDebugProperties(JSONObject jSONObject) {
        clearDebugProperties();
        this.debugProperties.putAll(this.jsonUtilities.createMapFromJSON(jSONObject));
    }
}
