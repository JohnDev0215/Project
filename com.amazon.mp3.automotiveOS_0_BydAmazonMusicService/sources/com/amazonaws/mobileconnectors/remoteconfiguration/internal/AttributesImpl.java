package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.RemoteConfigurationException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes5.dex */
public class AttributesImpl implements Attributes {
    private static final String KEY_APP_IDENTIFIER = "_applicationIdentifier";
    public static final String KEY_APP_VERSION = "_applicationVersion";
    private static final String KEY_COUNTRY = "_localeCountryCode";
    private static final String KEY_LANGUAGE = "_localeLanguage";
    private static final String KEY_PLATFORM = "_platform";
    private static final int MAX_ALLOWED_CUSTOM_ATTRIBUTES = 100;
    private static final String PLATFORM_ANDROID = "Android";
    private static final String TAG = AttributesImpl.class.getSimpleName();
    private final ConcurrentMap<String, Object> mCustomAttributes;
    private final String mPackageName;
    private final Integer mPackageVersion;

    protected String getPlatform() {
        return PLATFORM_ANDROID;
    }

    public AttributesImpl(Context context) {
        this.mCustomAttributes = new ConcurrentHashMap(5, 0.9f, 1);
        this.mPackageName = context.getPackageName();
        this.mPackageVersion = getPackageVersionCode(context);
    }

    private AttributesImpl(String str, Integer num, Map<String, Object> map) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(5, 0.9f, 1);
        this.mCustomAttributes = concurrentHashMap;
        this.mPackageName = str;
        this.mPackageVersion = num;
        concurrentHashMap.putAll(map);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, String str2) {
        addAttributePrivate(str, str2);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, Integer num) {
        addAttributePrivate(str, num);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, Long l) {
        addAttributePrivate(str, l);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, Double d) {
        if (d != null && (d.isNaN() || d.isInfinite())) {
            throw new IllegalArgumentException("Invalid attribute value: " + d);
        }
        addAttributePrivate(str, d);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, Boolean bool) {
        addAttributePrivate(str, bool);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public String getString(String str) {
        return (String) getObjectPrivate(str, String.class);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public Integer getInt(String str) {
        return (Integer) getObjectPrivate(str, Integer.class);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public Long getLong(String str) {
        return (Long) getObjectPrivate(str, Long.class);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public Double getDouble(String str) {
        return (Double) getObjectPrivate(str, Double.class);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public Boolean getBoolean(String str) {
        return (Boolean) getObjectPrivate(str, Boolean.class);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public synchronized Map<String, Object> getAllAttributes() {
        HashMap map;
        map = new HashMap();
        addStandardAttributesTo(map);
        map.putAll(this.mCustomAttributes);
        return Collections.unmodifiableMap(map);
    }

    private void addStandardAttributesTo(Map<String, Object> map) {
        map.put(KEY_PLATFORM, getPlatform());
        map.put(KEY_LANGUAGE, getLanguage());
        map.put(KEY_COUNTRY, getCountry());
        map.put(KEY_APP_IDENTIFIER, getPackageName());
        map.put(KEY_APP_VERSION, getPackageVersionCode());
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public synchronized Object remove(String str) {
        Checks.checkNotNull(str, "attrKey cannot be null");
        Checks.checkArgument(!str.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX), "Unable to delete attributes with _ prefix");
        if (this.mCustomAttributes.containsKey(str)) {
            return this.mCustomAttributes.remove(str);
        }
        return null;
    }

    public synchronized void addAttributePrivate(String str, Object obj) {
        Checks.checkNotNull(str, "attrKey cannot be null");
        Checks.checkNotNull(obj, "attrValue cannot be null");
        Checks.checkArgument(!str.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX), "Custom attributes cannot begin with _");
        if (this.mCustomAttributes.size() >= 100) {
            throw new RemoteConfigurationException("Custom attributes limit 100 reached");
        }
        this.mCustomAttributes.put(str, obj);
    }

    private synchronized <T> T getObjectPrivate(String str, Class<T> cls) {
        Checks.checkNotNull(str, "attrKey cannot be null");
        Checks.checkNotNull(cls, "The class cannot be null");
        Object standardAttribute = str.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX) ? getStandardAttribute(str) : this.mCustomAttributes.get(str);
        if (standardAttribute == null) {
            return null;
        }
        if (cls.isAssignableFrom(standardAttribute.getClass())) {
            return cls.cast(standardAttribute);
        }
        throw new RemoteConfigurationException("Unable to retrieve value associated with attrKey = " + str + " as a " + cls.getSimpleName() + ".");
    }

    private Object getStandardAttribute(String str) {
        str.hashCode();
        switch (str) {
            case "_applicationVersion":
                return getPackageVersionCode();
            case "_localeLanguage":
                return getLanguage();
            case "_localeCountryCode":
                return getCountry();
            case "_applicationIdentifier":
                return getPackageName();
            case "_platform":
                return getPlatform();
            default:
                Log.w(TAG, "Unrecognized standard attribute: " + str);
                return null;
        }
    }

    protected String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    protected String getCountry() {
        return Locale.getDefault().getCountry();
    }

    protected String getPackageName() {
        return this.mPackageName;
    }

    protected Integer getPackageVersionCode() {
        return this.mPackageVersion;
    }

    protected Integer getPackageVersionCode(Context context) {
        try {
            return Integer.valueOf(context.getPackageManager().getPackageInfo(this.mPackageName, 0).versionCode);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            Log.wtf(TAG, "Package not found for: " + this.mPackageName, e);
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AttributesImpl attributesImpl = (AttributesImpl) obj;
        if (nullSafeEquals(this.mCustomAttributes, attributesImpl.mCustomAttributes) && nullSafeEquals(getCountry(), attributesImpl.getCountry()) && nullSafeEquals(getLanguage(), attributesImpl.getLanguage()) && nullSafeEquals(getPlatform(), attributesImpl.getPlatform()) && nullSafeEquals(getPackageName(), attributesImpl.getPackageName())) {
            return nullSafeEquals(getPackageVersionCode(), attributesImpl.getPackageVersionCode());
        }
        return false;
    }

    protected static boolean nullSafeEquals(Object obj, Object obj2) {
        if (obj == null) {
            return false;
        }
        if (obj == obj2) {
            return true;
        }
        return obj.equals(obj2);
    }

    public int hashCode() {
        return (((((((((nullSafeHash(this.mCustomAttributes) * 31) + nullSafeHash(getCountry())) * 31) + nullSafeHash(getLanguage())) * 31) + nullSafeHash(getPlatform())) * 31) + nullSafeHash(getPackageName())) * 31) + nullSafeHash(getPackageVersionCode());
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public synchronized AttributesImpl m2501clone() {
        return new AttributesImpl(this.mPackageName, this.mPackageVersion, this.mCustomAttributes);
    }

    protected static int nullSafeHash(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
