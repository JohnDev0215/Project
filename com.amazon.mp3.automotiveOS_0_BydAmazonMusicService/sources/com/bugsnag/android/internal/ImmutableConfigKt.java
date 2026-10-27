package com.bugsnag.android.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.bugsnag.android.BreadcrumbType;
import com.bugsnag.android.Configuration;
import com.bugsnag.android.Connectivity;
import com.bugsnag.android.DebugLogger;
import com.bugsnag.android.DefaultDelivery;
import com.bugsnag.android.Delivery;
import com.bugsnag.android.EndpointConfiguration;
import com.bugsnag.android.ErrorTypes;
import com.bugsnag.android.Logger;
import com.bugsnag.android.NoopLogger;
import com.bugsnag.android.Telemetry;
import com.bugsnag.android.ThreadSendPolicy;
import java.io.File;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImmutableConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aD\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0001\u001a \u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010H\u0000\u001a\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\u0014"}, d2 = {"Lcom/bugsnag/android/Configuration;", "config", "", "buildUuid", "Landroid/content/pm/PackageInfo;", "packageInfo", "Landroid/content/pm/ApplicationInfo;", "appInfo", "Lkotlin/Lazy;", "Ljava/io/File;", "persistenceDir", "Lcom/bugsnag/android/internal/ImmutableConfig;", "convertToImmutableConfig", "Landroid/content/Context;", "appContext", "configuration", "Lcom/bugsnag/android/Connectivity;", "connectivity", "sanitiseConfiguration", "populateBuildUuid", "bugsnag-android-core_release"}, k = 2, mv = {1, 4, 2})
public final class ImmutableConfigKt {
    @JvmOverloads
    public static final ImmutableConfig convertToImmutableConfig(Configuration configuration, String str, PackageInfo packageInfo, ApplicationInfo applicationInfo, Lazy<? extends File> lazy) {
        ErrorTypes errorTypesCopy$bugsnag_android_core_release = configuration.getAutoDetectErrors() ? configuration.getEnabledErrorTypes().copy$bugsnag_android_core_release() : new ErrorTypes(false);
        String apiKey = configuration.getApiKey();
        Intrinsics.checkExpressionValueIsNotNull(apiKey, "config.apiKey");
        boolean autoDetectErrors = configuration.getAutoDetectErrors();
        boolean autoTrackSessions = configuration.getAutoTrackSessions();
        ThreadSendPolicy sendThreads = configuration.getSendThreads();
        Intrinsics.checkExpressionValueIsNotNull(sendThreads, "config.sendThreads");
        Set<String> discardClasses = configuration.getDiscardClasses();
        Intrinsics.checkExpressionValueIsNotNull(discardClasses, "config.discardClasses");
        Set set = CollectionsKt___CollectionsKt.toSet(discardClasses);
        Set<String> enabledReleaseStages = configuration.getEnabledReleaseStages();
        Set set2 = enabledReleaseStages != null ? CollectionsKt___CollectionsKt.toSet(enabledReleaseStages) : null;
        Set<String> projectPackages = configuration.getProjectPackages();
        Intrinsics.checkExpressionValueIsNotNull(projectPackages, "config.projectPackages");
        Set set3 = CollectionsKt___CollectionsKt.toSet(projectPackages);
        String releaseStage = configuration.getReleaseStage();
        String appVersion = configuration.getAppVersion();
        Integer versionCode = configuration.getVersionCode();
        String appType = configuration.getAppType();
        Delivery delivery = configuration.getDelivery();
        Intrinsics.checkExpressionValueIsNotNull(delivery, "config.delivery");
        EndpointConfiguration endpoints = configuration.getEndpoints();
        Intrinsics.checkExpressionValueIsNotNull(endpoints, "config.endpoints");
        boolean persistUser = configuration.getPersistUser();
        long launchDurationMillis = configuration.getLaunchDurationMillis();
        Logger logger = configuration.getLogger();
        if (logger == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(logger, "config.logger!!");
        int maxBreadcrumbs = configuration.getMaxBreadcrumbs();
        int maxPersistedEvents = configuration.getMaxPersistedEvents();
        int maxPersistedSessions = configuration.getMaxPersistedSessions();
        int maxReportedThreads = configuration.getMaxReportedThreads();
        Set<BreadcrumbType> enabledBreadcrumbTypes = configuration.getEnabledBreadcrumbTypes();
        Set set4 = enabledBreadcrumbTypes != null ? CollectionsKt___CollectionsKt.toSet(enabledBreadcrumbTypes) : null;
        Set<Telemetry> telemetry = configuration.getTelemetry();
        Intrinsics.checkExpressionValueIsNotNull(telemetry, "config.telemetry");
        Set set5 = CollectionsKt___CollectionsKt.toSet(telemetry);
        boolean sendLaunchCrashesSynchronously = configuration.getSendLaunchCrashesSynchronously();
        boolean zIsAttemptDeliveryOnCrash = configuration.isAttemptDeliveryOnCrash();
        Set<String> redactedKeys = configuration.getRedactedKeys();
        Intrinsics.checkExpressionValueIsNotNull(redactedKeys, "config.redactedKeys");
        return new ImmutableConfig(apiKey, autoDetectErrors, errorTypesCopy$bugsnag_android_core_release, autoTrackSessions, sendThreads, set, set2, set3, set4, set5, releaseStage, str, appVersion, versionCode, appType, delivery, endpoints, persistUser, launchDurationMillis, logger, maxBreadcrumbs, maxPersistedEvents, maxPersistedSessions, maxReportedThreads, lazy, sendLaunchCrashesSynchronously, zIsAttemptDeliveryOnCrash, packageInfo, applicationInfo, CollectionsKt___CollectionsKt.toSet(redactedKeys));
    }

    public static final ImmutableConfig sanitiseConfiguration(final Context context, final Configuration configuration, Connectivity connectivity) {
        Object objM2810constructorimpl;
        Object objM2810constructorimpl2;
        Integer versionCode;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        try {
            Result.Companion companion = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(packageManager.getPackageInfo(packageName, 0));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2816isFailureimpl(objM2810constructorimpl)) {
            objM2810constructorimpl = null;
        }
        PackageInfo packageInfo = (PackageInfo) objM2810constructorimpl;
        try {
            objM2810constructorimpl2 = Result.m2810constructorimpl(packageManager.getApplicationInfo(packageName, 128));
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.INSTANCE;
            objM2810constructorimpl2 = Result.m2810constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m2816isFailureimpl(objM2810constructorimpl2)) {
            objM2810constructorimpl2 = null;
        }
        ApplicationInfo applicationInfo = (ApplicationInfo) objM2810constructorimpl2;
        if (configuration.getReleaseStage() == null) {
            configuration.setReleaseStage((applicationInfo == null || (applicationInfo.flags & 2) == 0) ? "production" : "development");
        }
        if (configuration.getLogger() == null || Intrinsics.areEqual(configuration.getLogger(), DebugLogger.INSTANCE)) {
            if (!Intrinsics.areEqual("production", configuration.getReleaseStage())) {
                configuration.setLogger(DebugLogger.INSTANCE);
            } else {
                configuration.setLogger(NoopLogger.INSTANCE);
            }
        }
        if (configuration.getVersionCode() == null || ((versionCode = configuration.getVersionCode()) != null && versionCode.intValue() == 0)) {
            configuration.setVersionCode(packageInfo != null ? Integer.valueOf(packageInfo.versionCode) : null);
        }
        if (configuration.getProjectPackages().isEmpty()) {
            Intrinsics.checkExpressionValueIsNotNull(packageName, "packageName");
            configuration.setProjectPackages(SetsKt__SetsJVMKt.setOf(packageName));
        }
        String strPopulateBuildUuid = populateBuildUuid(applicationInfo);
        if (configuration.getDelivery() == null) {
            String apiKey = configuration.getApiKey();
            Intrinsics.checkExpressionValueIsNotNull(apiKey, "configuration.apiKey");
            int maxStringValueLength = configuration.getMaxStringValueLength();
            Logger logger = configuration.getLogger();
            if (logger == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(logger, "configuration.logger!!");
            configuration.setDelivery(new DefaultDelivery(connectivity, apiKey, maxStringValueLength, logger));
        }
        return convertToImmutableConfig(configuration, strPopulateBuildUuid, packageInfo, applicationInfo, LazyKt__LazyJVMKt.lazy(new Function0<File>() { // from class: com.bugsnag.android.internal.ImmutableConfigKt.sanitiseConfiguration.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File persistenceDirectory = configuration.getPersistenceDirectory();
                return persistenceDirectory != null ? persistenceDirectory : context.getCacheDir();
            }
        }));
    }

    private static final String populateBuildUuid(ApplicationInfo applicationInfo) {
        Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
        if (bundle == null || !bundle.containsKey("com.bugsnag.android.BUILD_UUID")) {
            return null;
        }
        String string = bundle.getString("com.bugsnag.android.BUILD_UUID");
        return string != null ? string : String.valueOf(bundle.getInt("com.bugsnag.android.BUILD_UUID"));
    }
}
