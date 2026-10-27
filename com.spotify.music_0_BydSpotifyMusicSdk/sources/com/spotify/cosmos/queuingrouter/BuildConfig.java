package com.spotify.cosmos.queuingrouter;

/* JADX INFO: loaded from: classes.dex */
public final class BuildConfig {

    @Deprecated
    public static final boolean DEBUG = internal();
    public static final boolean INTERNAL = internal();
    public static final String BUILD_TYPE = buildType();
    public static final String BUILD_TAG = buildTag();
    public static final String LIBRARY_PACKAGE_NAME = libraryPackageName();
    public static final boolean DYNAMIC_FEATURE_ENABLED = dynamicFeatureEnabled();
    public static final boolean ABORT_ON_ASSERTION = abortOnAssertion();
    public static final boolean CRASH_REPORTING_ENABLED = crashReportingEnabled();
    public static final boolean CORE_COMPRESSION_ENABLED = coreCompressionEnabled();
    public static final boolean END_TO_END = endToEnd();

    private static final boolean abortOnAssertion() {
        return false;
    }

    private static final String buildTag() {
        return "gradle";
    }

    private static final String buildType() {
        return "release";
    }

    private static final boolean coreCompressionEnabled() {
        return false;
    }

    private static final boolean crashReportingEnabled() {
        return true;
    }

    private static final boolean dynamicFeatureEnabled() {
        return false;
    }

    private static final boolean endToEnd() {
        return false;
    }

    private static final boolean internal() {
        return false;
    }

    private static final String libraryPackageName() {
        return "com.spotify.cosmos.queuingrouter";
    }
}
