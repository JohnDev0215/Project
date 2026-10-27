package com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator;

/* JADX INFO: loaded from: classes5.dex */
public class SystemPropertiesWrapper {
    private static final String BUILD_TYPE = "ro.build.type";
    private static final String PRODUCT_NAME = "ro.product.name";
    private static final String VERSION_NUMBER = "ro.build.version.number";
    private final String mBuildType;
    private final String mProductName;
    private final ReflectionHelper mReflectionHelper;
    private final Class mSystemProperties;
    private final Long mVersionNumber;

    public SystemPropertiesWrapper() {
        this(new ReflectionHelper());
    }

    SystemPropertiesWrapper(ReflectionHelper reflectionHelper) {
        this.mReflectionHelper = reflectionHelper;
        this.mSystemProperties = getSystemProperties();
        if (isSystemPropertiesPresent()) {
            this.mBuildType = getString(BUILD_TYPE, null);
            this.mVersionNumber = parseLongFromString(VERSION_NUMBER, null);
            this.mProductName = getString(PRODUCT_NAME, null);
        } else {
            this.mBuildType = null;
            this.mVersionNumber = null;
            this.mProductName = null;
        }
    }

    public String getBuildType() {
        return this.mBuildType;
    }

    public Long getVersionNumber() {
        return this.mVersionNumber;
    }

    public String getProductName() {
        return this.mProductName;
    }

    public boolean isSystemPropertiesPresent() {
        return this.mSystemProperties != null;
    }

    private Class getSystemProperties() {
        return this.mReflectionHelper.getHiddenClass("android.os.SystemProperties");
    }

    private String getString(String str, String str2) {
        return (String) this.mReflectionHelper.invokeHiddenMethodWithDefault(this.mSystemProperties, "get", str, str2);
    }

    private Long parseLongFromString(String str, Long l) {
        try {
            String str2 = (String) this.mReflectionHelper.invokeHiddenMethodWithDefault(this.mSystemProperties, "get", str, null);
            if (str2 != null) {
                return Long.valueOf(str2);
            }
        } catch (Exception unused) {
        }
        return l;
    }
}
