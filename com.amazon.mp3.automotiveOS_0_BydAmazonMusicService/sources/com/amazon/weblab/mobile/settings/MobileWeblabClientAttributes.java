package com.amazon.weblab.mobile.settings;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.amazon.weblab.mobile.TooManyRegisteredWeblabsException;
import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import com.amazon.weblab.mobile.service.MobileWeblabDomain;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class MobileWeblabClientAttributes implements IMobileWeblabClientAttributes {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Pattern VERSION_REGEX_PATTERN = Pattern.compile("^(\\d+\\.)*\\d+$");
    private String mAppName;
    private String mAppVersion;
    private final Map<String, String> mClientAttr;
    private String mIdentifier;
    private MobileWeblabOS mOsName;
    private String mOsVersion;
    private int mPlatformWeblabsCount;
    private MobileWeblabDomain mWeblabDomain;
    private final Map<String, String> mWeblabs;

    public MobileWeblabClientAttributes(String str, String str2, String str3, MobileWeblabOS mobileWeblabOS, String str4, MobileWeblabDomain mobileWeblabDomain) {
        this.mPlatformWeblabsCount = 0;
        this.mWeblabs = new ConcurrentHashMap();
        this.mClientAttr = new HashMap();
        setApplicationVersion(str3);
        setApplicationName(str2);
        setOSName(mobileWeblabOS);
        setOSVersion(str4);
        setIdentifier(str);
        this.mWeblabDomain = mobileWeblabDomain;
    }

    public MobileWeblabClientAttributes(String str, String str2, String str3, MobileWeblabOS mobileWeblabOS, String str4) {
        this(str, str2, str3, mobileWeblabOS, str4, MobileWeblabDomain.PROD);
    }

    public MobileWeblabClientAttributes(String str, String str2, String str3, MobileWeblabOS mobileWeblabOS, String str4, Map<String, String> map, MobileWeblabDomain mobileWeblabDomain, Map<String, String> map2) {
        this(str, str2, str3, mobileWeblabOS, str4, mobileWeblabDomain);
        this.mClientAttr.putAll(map);
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            addWeblab(entry.getKey(), entry.getValue());
        }
    }

    public MobileWeblabClientAttributes(MobileWeblabClientAttributes mobileWeblabClientAttributes, PlatformWeblabs[] platformWeblabsArr) {
        this(mobileWeblabClientAttributes);
        for (PlatformWeblabs platformWeblabs : platformWeblabsArr) {
            addWeblab(platformWeblabs.getWeblabName(), platformWeblabs.getDefaultTreatment());
        }
    }

    public MobileWeblabClientAttributes(MobileWeblabClientAttributes mobileWeblabClientAttributes) {
        this(mobileWeblabClientAttributes.mIdentifier, mobileWeblabClientAttributes.mAppName, mobileWeblabClientAttributes.mAppVersion, mobileWeblabClientAttributes.mOsName, mobileWeblabClientAttributes.mOsVersion, mobileWeblabClientAttributes.getDomain());
        this.mWeblabs.putAll(mobileWeblabClientAttributes.mWeblabs);
        this.mClientAttr.putAll(mobileWeblabClientAttributes.mClientAttr);
        this.mPlatformWeblabsCount = mobileWeblabClientAttributes.mPlatformWeblabsCount;
    }

    private boolean validateVersion(String str) {
        return VERSION_REGEX_PATTERN.matcher(str).matches();
    }

    private void setIdentifier(String str) {
        if (str == null) {
            throw new IllegalArgumentException("identifier cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("identifier cannot be empty");
        }
        this.mIdentifier = str;
    }

    private void setApplicationVersion(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("appVersion cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("appVersion cannot be empty");
        }
        if (!validateVersion(str)) {
            throw new IllegalArgumentException("appVersion must be in the form of w.x.y.z");
        }
        this.mAppVersion = str;
    }

    private void setApplicationName(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("appName cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("appName cannot be empty");
        }
        this.mAppName = str;
    }

    private void setOSVersion(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("osVersion cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("osVersion cannot be empty");
        }
        if (!validateVersion(str)) {
            throw new IllegalArgumentException("osVersion must be in the form of w.x.y.z");
        }
        this.mOsVersion = str;
    }

    private void setOSName(MobileWeblabOS mobileWeblabOS) throws IllegalArgumentException {
        if (mobileWeblabOS == null) {
            throw new IllegalArgumentException("osName cannot be null");
        }
        this.mOsName = mobileWeblabOS;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public void addWeblab(String str, String str2) throws TooManyRegisteredWeblabsException, IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        if (str2.isEmpty()) {
            throw new IllegalArgumentException("value cannot be empty");
        }
        if (this.mWeblabs.containsKey(str)) {
            this.mWeblabs.put(str, str2);
            return;
        }
        synchronized (this) {
            if (isPlatformWeblab(str)) {
                this.mPlatformWeblabsCount++;
            }
            if (this.mWeblabs.size() >= this.mPlatformWeblabsCount + PathInterpolatorCompat.MAX_NUM_POINTS) {
                throw new TooManyRegisteredWeblabsException("Maximum number of weblabs has been reached at 3000. No aditional weblabs can be added");
            }
            this.mWeblabs.put(str, str2);
        }
    }

    private boolean isPlatformWeblab(String str) {
        for (PlatformWeblabs platformWeblabs : PlatformWeblabs.values()) {
            if (platformWeblabs.getWeblabName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public String getApplicationVersion() {
        return this.mAppVersion;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public String getApplicationName() {
        return this.mAppName;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public String getOSVersion() {
        return this.mOsVersion;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public MobileWeblabOS getOSName() {
        return this.mOsName;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public Map<String, String> getClientAttributes() {
        return Collections.unmodifiableMap(this.mClientAttr);
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public Map<String, String> getWeblabs() {
        return Collections.unmodifiableMap(this.mWeblabs);
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public String getIdentifier() {
        return this.mIdentifier;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes
    public MobileWeblabDomain getDomain() {
        return this.mWeblabDomain;
    }

    public int hashCode() {
        String str = this.mAppName;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.mAppVersion;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, String> map = this.mClientAttr;
        int iHashCode3 = (iHashCode2 + (map == null ? 0 : map.hashCode())) * 31;
        String str3 = this.mIdentifier;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        MobileWeblabOS mobileWeblabOS = this.mOsName;
        int iHashCode5 = (iHashCode4 + (mobileWeblabOS == null ? 0 : mobileWeblabOS.hashCode())) * 31;
        String str4 = this.mOsVersion;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Map<String, String> map2 = this.mWeblabs;
        return ((((iHashCode6 + (map2 != null ? map2.hashCode() : 0)) * 31) + this.mPlatformWeblabsCount) * 31) + this.mWeblabDomain.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MobileWeblabClientAttributes)) {
            return false;
        }
        MobileWeblabClientAttributes mobileWeblabClientAttributes = (MobileWeblabClientAttributes) obj;
        return mobileWeblabClientAttributes.mAppName.equals(this.mAppName) && mobileWeblabClientAttributes.mAppVersion.equals(this.mAppVersion) && mobileWeblabClientAttributes.mClientAttr.equals(this.mClientAttr) && mobileWeblabClientAttributes.mIdentifier.equals(this.mIdentifier) && mobileWeblabClientAttributes.mOsName.equals(this.mOsName) && mobileWeblabClientAttributes.mOsVersion.equals(this.mOsVersion) && mobileWeblabClientAttributes.mWeblabs.equals(this.mWeblabs) && mobileWeblabClientAttributes.mPlatformWeblabsCount == this.mPlatformWeblabsCount && mobileWeblabClientAttributes.mWeblabDomain == this.mWeblabDomain;
    }
}
