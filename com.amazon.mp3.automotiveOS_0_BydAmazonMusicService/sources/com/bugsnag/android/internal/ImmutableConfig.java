package com.bugsnag.android.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.bugsnag.android.BreadcrumbType;
import com.bugsnag.android.Delivery;
import com.bugsnag.android.DeliveryHeadersKt;
import com.bugsnag.android.DeliveryParams;
import com.bugsnag.android.EndpointConfiguration;
import com.bugsnag.android.ErrorTypes;
import com.bugsnag.android.EventPayload;
import com.bugsnag.android.Logger;
import com.bugsnag.android.Telemetry;
import com.bugsnag.android.ThreadSendPolicy;
import com.bugsnag.android.ThrowableUtils;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImmutableConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B¶\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u000b\u0012\u0006\u0010 \u001a\u00020\t\u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010)\u001a\u00020\t\u0012\u0006\u0010,\u001a\u00020+\u0012\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000b00\u0012\u000e\u00105\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u000100\u0012\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000b00\u0012\u000e\u0010:\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u000109\u0012\f\u0010?\u001a\b\u0012\u0004\u0012\u00020>09\u0012\b\u0010A\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010C\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010E\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010G\u001a\u0004\u0018\u00010\u0018\u0012\b\u0010K\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010N\u001a\u00020M\u0012\u0006\u0010S\u001a\u00020R\u0012\u0006\u0010W\u001a\u00020\t\u0012\u0006\u0010Z\u001a\u00020Y\u0012\u0006\u0010_\u001a\u00020^\u0012\u0006\u0010c\u001a\u00020\u0018\u0012\u0006\u0010g\u001a\u00020\u0018\u0012\u0006\u0010i\u001a\u00020\u0018\u0012\u0006\u0010k\u001a\u00020\u0018\u0012\f\u0010o\u001a\b\u0012\u0004\u0012\u00020n0m\u0012\u0006\u0010s\u001a\u00020\t\u0012\u0006\u0010u\u001a\u00020\t\u0012\b\u0010x\u001a\u0004\u0018\u00010w\u0012\b\u0010}\u001a\u0004\u0018\u00010|\u0012\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u000b00¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0001J\b\u0010\u0006\u001a\u00020\u0004H\u0001J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\tJ\u0019\u0010\u0015\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u0013\u0010\u0016J\t\u0010\u0017\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0019\u001a\u00020\u0018HÖ\u0001J\u0013\u0010\u001b\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u001c\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010)\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b)\u0010!\u001a\u0004\b*\u0010#R\u0017\u0010,\u001a\u00020+8\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u001d\u00101\u001a\b\u0012\u0004\u0012\u00020\u000b008\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u001f\u00105\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001008\u0006¢\u0006\f\n\u0004\b5\u00102\u001a\u0004\b6\u00104R\u001d\u00107\u001a\b\u0012\u0004\u0012\u00020\u000b008\u0006¢\u0006\f\n\u0004\b7\u00102\u001a\u0004\b8\u00104R\u001f\u0010:\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001098\u0006¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u001d\u0010?\u001a\b\u0012\u0004\u0012\u00020>098\u0006¢\u0006\f\n\u0004\b?\u0010;\u001a\u0004\b@\u0010=R\u0019\u0010A\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\bA\u0010\u001d\u001a\u0004\bB\u0010\u001fR\u0019\u0010C\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\bC\u0010\u001d\u001a\u0004\bD\u0010\u001fR\u0019\u0010E\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\bE\u0010\u001d\u001a\u0004\bF\u0010\u001fR\u0019\u0010G\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u0019\u0010K\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\bK\u0010\u001d\u001a\u0004\bL\u0010\u001fR\u0017\u0010N\u001a\u00020M8\u0006¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010QR\u0017\u0010S\u001a\u00020R8\u0006¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bU\u0010VR\u0017\u0010W\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\bW\u0010!\u001a\u0004\bX\u0010#R\u0017\u0010Z\u001a\u00020Y8\u0006¢\u0006\f\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010]R\u0017\u0010_\u001a\u00020^8\u0006¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\ba\u0010bR\u0017\u0010c\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\bc\u0010d\u001a\u0004\be\u0010fR\u0017\u0010g\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\bg\u0010d\u001a\u0004\bh\u0010fR\u0017\u0010i\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\bi\u0010d\u001a\u0004\bj\u0010fR\u0017\u0010k\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\bk\u0010d\u001a\u0004\bl\u0010fR\u001d\u0010o\u001a\b\u0012\u0004\u0012\u00020n0m8\u0006¢\u0006\f\n\u0004\bo\u0010p\u001a\u0004\bq\u0010rR\u0017\u0010s\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\bs\u0010!\u001a\u0004\bt\u0010#R\u0017\u0010u\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\bu\u0010!\u001a\u0004\bv\u0010#R\u0019\u0010x\u001a\u0004\u0018\u00010w8\u0006¢\u0006\f\n\u0004\bx\u0010y\u001a\u0004\bz\u0010{R\u001a\u0010}\u001a\u0004\u0018\u00010|8\u0006¢\u0006\r\n\u0004\b}\u0010~\u001a\u0005\b\u007f\u0010\u0080\u0001R \u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u000b008\u0006¢\u0006\u000e\n\u0005\b\u0081\u0001\u00102\u001a\u0005\b\u0082\u0001\u00104¨\u0006\u0085\u0001"}, d2 = {"Lcom/bugsnag/android/internal/ImmutableConfig;", "", "Lcom/bugsnag/android/EventPayload;", "payload", "Lcom/bugsnag/android/DeliveryParams;", "getErrorApiDeliveryParams", "getSessionApiDeliveryParams", "", "exc", "", "shouldDiscardError", "", "errorClass", "autoCaptured", "shouldDiscardSession", "Lcom/bugsnag/android/BreadcrumbType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "shouldDiscardBreadcrumb", "shouldDiscardByReleaseStage", "shouldDiscardByErrorClass$bugsnag_android_core_release", "(Ljava/lang/String;)Z", "shouldDiscardByErrorClass", "(Ljava/lang/Throwable;)Z", "toString", "", "hashCode", "other", "equals", "apiKey", "Ljava/lang/String;", "getApiKey", "()Ljava/lang/String;", "autoDetectErrors", "Z", "getAutoDetectErrors", "()Z", "Lcom/bugsnag/android/ErrorTypes;", "enabledErrorTypes", "Lcom/bugsnag/android/ErrorTypes;", "getEnabledErrorTypes", "()Lcom/bugsnag/android/ErrorTypes;", "autoTrackSessions", "getAutoTrackSessions", "Lcom/bugsnag/android/ThreadSendPolicy;", "sendThreads", "Lcom/bugsnag/android/ThreadSendPolicy;", "getSendThreads", "()Lcom/bugsnag/android/ThreadSendPolicy;", "", "discardClasses", "Ljava/util/Collection;", "getDiscardClasses", "()Ljava/util/Collection;", "enabledReleaseStages", "getEnabledReleaseStages", "projectPackages", "getProjectPackages", "", "enabledBreadcrumbTypes", "Ljava/util/Set;", "getEnabledBreadcrumbTypes", "()Ljava/util/Set;", "Lcom/bugsnag/android/Telemetry;", "telemetry", "getTelemetry", "releaseStage", "getReleaseStage", "buildUuid", "getBuildUuid", Splash.PARAMS_APP_VERSION, "getAppVersion", "versionCode", "Ljava/lang/Integer;", "getVersionCode", "()Ljava/lang/Integer;", "appType", "getAppType", "Lcom/bugsnag/android/Delivery;", "delivery", "Lcom/bugsnag/android/Delivery;", "getDelivery", "()Lcom/bugsnag/android/Delivery;", "Lcom/bugsnag/android/EndpointConfiguration;", "endpoints", "Lcom/bugsnag/android/EndpointConfiguration;", "getEndpoints", "()Lcom/bugsnag/android/EndpointConfiguration;", "persistUser", "getPersistUser", "", "launchDurationMillis", "J", "getLaunchDurationMillis", "()J", "Lcom/bugsnag/android/Logger;", "logger", "Lcom/bugsnag/android/Logger;", "getLogger", "()Lcom/bugsnag/android/Logger;", "maxBreadcrumbs", "I", "getMaxBreadcrumbs", "()I", "maxPersistedEvents", "getMaxPersistedEvents", "maxPersistedSessions", "getMaxPersistedSessions", "maxReportedThreads", "getMaxReportedThreads", "Lkotlin/Lazy;", "Ljava/io/File;", "persistenceDirectory", "Lkotlin/Lazy;", "getPersistenceDirectory", "()Lkotlin/Lazy;", "sendLaunchCrashesSynchronously", "getSendLaunchCrashesSynchronously", "attemptDeliveryOnCrash", "getAttemptDeliveryOnCrash", "Landroid/content/pm/PackageInfo;", "packageInfo", "Landroid/content/pm/PackageInfo;", "getPackageInfo", "()Landroid/content/pm/PackageInfo;", "Landroid/content/pm/ApplicationInfo;", "appInfo", "Landroid/content/pm/ApplicationInfo;", "getAppInfo", "()Landroid/content/pm/ApplicationInfo;", "redactedKeys", "getRedactedKeys", "<init>", "(Ljava/lang/String;ZLcom/bugsnag/android/ErrorTypes;ZLcom/bugsnag/android/ThreadSendPolicy;Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Set;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/bugsnag/android/Delivery;Lcom/bugsnag/android/EndpointConfiguration;ZJLcom/bugsnag/android/Logger;IIIILkotlin/Lazy;ZZLandroid/content/pm/PackageInfo;Landroid/content/pm/ApplicationInfo;Ljava/util/Collection;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final /* data */ class ImmutableConfig {
    private final String apiKey;
    private final ApplicationInfo appInfo;
    private final String appType;
    private final String appVersion;
    private final boolean attemptDeliveryOnCrash;
    private final boolean autoDetectErrors;
    private final boolean autoTrackSessions;
    private final String buildUuid;
    private final Delivery delivery;
    private final Collection<String> discardClasses;
    private final Set<BreadcrumbType> enabledBreadcrumbTypes;
    private final ErrorTypes enabledErrorTypes;
    private final Collection<String> enabledReleaseStages;
    private final EndpointConfiguration endpoints;
    private final long launchDurationMillis;
    private final Logger logger;
    private final int maxBreadcrumbs;
    private final int maxPersistedEvents;
    private final int maxPersistedSessions;
    private final int maxReportedThreads;
    private final PackageInfo packageInfo;
    private final boolean persistUser;
    private final Lazy<File> persistenceDirectory;
    private final Collection<String> projectPackages;
    private final Collection<String> redactedKeys;
    private final String releaseStage;
    private final boolean sendLaunchCrashesSynchronously;
    private final ThreadSendPolicy sendThreads;
    private final Set<Telemetry> telemetry;
    private final Integer versionCode;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImmutableConfig)) {
            return false;
        }
        ImmutableConfig immutableConfig = (ImmutableConfig) other;
        return Intrinsics.areEqual(this.apiKey, immutableConfig.apiKey) && this.autoDetectErrors == immutableConfig.autoDetectErrors && Intrinsics.areEqual(this.enabledErrorTypes, immutableConfig.enabledErrorTypes) && this.autoTrackSessions == immutableConfig.autoTrackSessions && Intrinsics.areEqual(this.sendThreads, immutableConfig.sendThreads) && Intrinsics.areEqual(this.discardClasses, immutableConfig.discardClasses) && Intrinsics.areEqual(this.enabledReleaseStages, immutableConfig.enabledReleaseStages) && Intrinsics.areEqual(this.projectPackages, immutableConfig.projectPackages) && Intrinsics.areEqual(this.enabledBreadcrumbTypes, immutableConfig.enabledBreadcrumbTypes) && Intrinsics.areEqual(this.telemetry, immutableConfig.telemetry) && Intrinsics.areEqual(this.releaseStage, immutableConfig.releaseStage) && Intrinsics.areEqual(this.buildUuid, immutableConfig.buildUuid) && Intrinsics.areEqual(this.appVersion, immutableConfig.appVersion) && Intrinsics.areEqual(this.versionCode, immutableConfig.versionCode) && Intrinsics.areEqual(this.appType, immutableConfig.appType) && Intrinsics.areEqual(this.delivery, immutableConfig.delivery) && Intrinsics.areEqual(this.endpoints, immutableConfig.endpoints) && this.persistUser == immutableConfig.persistUser && this.launchDurationMillis == immutableConfig.launchDurationMillis && Intrinsics.areEqual(this.logger, immutableConfig.logger) && this.maxBreadcrumbs == immutableConfig.maxBreadcrumbs && this.maxPersistedEvents == immutableConfig.maxPersistedEvents && this.maxPersistedSessions == immutableConfig.maxPersistedSessions && this.maxReportedThreads == immutableConfig.maxReportedThreads && Intrinsics.areEqual(this.persistenceDirectory, immutableConfig.persistenceDirectory) && this.sendLaunchCrashesSynchronously == immutableConfig.sendLaunchCrashesSynchronously && this.attemptDeliveryOnCrash == immutableConfig.attemptDeliveryOnCrash && Intrinsics.areEqual(this.packageInfo, immutableConfig.packageInfo) && Intrinsics.areEqual(this.appInfo, immutableConfig.appInfo) && Intrinsics.areEqual(this.redactedKeys, immutableConfig.redactedKeys);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v47, types: [int] */
    /* JADX WARN: Type inference failed for: r2v6, types: [int] */
    /* JADX WARN: Type inference failed for: r2v61, types: [int] */
    /* JADX WARN: Type inference failed for: r2v72 */
    /* JADX WARN: Type inference failed for: r2v75 */
    /* JADX WARN: Type inference failed for: r2v89 */
    /* JADX WARN: Type inference failed for: r2v91 */
    /* JADX WARN: Type inference failed for: r2v92 */
    /* JADX WARN: Type inference failed for: r2v93 */
    /* JADX WARN: Type inference failed for: r2v94 */
    /* JADX WARN: Type inference failed for: r2v95 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    public int hashCode() {
        String str = this.apiKey;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.autoDetectErrors;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        ErrorTypes errorTypes = this.enabledErrorTypes;
        int iHashCode2 = (i + (errorTypes != null ? errorTypes.hashCode() : 0)) * 31;
        boolean z2 = this.autoTrackSessions;
        ?? r22 = z2;
        if (z2) {
            r22 = 1;
        }
        int i2 = (iHashCode2 + r22) * 31;
        ThreadSendPolicy threadSendPolicy = this.sendThreads;
        int iHashCode3 = (i2 + (threadSendPolicy != null ? threadSendPolicy.hashCode() : 0)) * 31;
        Collection<String> collection = this.discardClasses;
        int iHashCode4 = (iHashCode3 + (collection != null ? collection.hashCode() : 0)) * 31;
        Collection<String> collection2 = this.enabledReleaseStages;
        int iHashCode5 = (iHashCode4 + (collection2 != null ? collection2.hashCode() : 0)) * 31;
        Collection<String> collection3 = this.projectPackages;
        int iHashCode6 = (iHashCode5 + (collection3 != null ? collection3.hashCode() : 0)) * 31;
        Set<BreadcrumbType> set = this.enabledBreadcrumbTypes;
        int iHashCode7 = (iHashCode6 + (set != null ? set.hashCode() : 0)) * 31;
        Set<Telemetry> set2 = this.telemetry;
        int iHashCode8 = (iHashCode7 + (set2 != null ? set2.hashCode() : 0)) * 31;
        String str2 = this.releaseStage;
        int iHashCode9 = (iHashCode8 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.buildUuid;
        int iHashCode10 = (iHashCode9 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.appVersion;
        int iHashCode11 = (iHashCode10 + (str4 != null ? str4.hashCode() : 0)) * 31;
        Integer num = this.versionCode;
        int iHashCode12 = (iHashCode11 + (num != null ? num.hashCode() : 0)) * 31;
        String str5 = this.appType;
        int iHashCode13 = (iHashCode12 + (str5 != null ? str5.hashCode() : 0)) * 31;
        Delivery delivery = this.delivery;
        int iHashCode14 = (iHashCode13 + (delivery != null ? delivery.hashCode() : 0)) * 31;
        EndpointConfiguration endpointConfiguration = this.endpoints;
        int iHashCode15 = (iHashCode14 + (endpointConfiguration != null ? endpointConfiguration.hashCode() : 0)) * 31;
        boolean z3 = this.persistUser;
        ?? r23 = z3;
        if (z3) {
            r23 = 1;
        }
        long j = this.launchDurationMillis;
        int i3 = (((iHashCode15 + r23) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        Logger logger = this.logger;
        int iHashCode16 = (((((((((i3 + (logger != null ? logger.hashCode() : 0)) * 31) + this.maxBreadcrumbs) * 31) + this.maxPersistedEvents) * 31) + this.maxPersistedSessions) * 31) + this.maxReportedThreads) * 31;
        Lazy<File> lazy = this.persistenceDirectory;
        int iHashCode17 = (iHashCode16 + (lazy != null ? lazy.hashCode() : 0)) * 31;
        boolean z4 = this.sendLaunchCrashesSynchronously;
        ?? r24 = z4;
        if (z4) {
            r24 = 1;
        }
        int i4 = (iHashCode17 + r24) * 31;
        boolean z5 = this.attemptDeliveryOnCrash;
        int i5 = (i4 + (z5 ? 1 : z5)) * 31;
        PackageInfo packageInfo = this.packageInfo;
        int iHashCode18 = (i5 + (packageInfo != null ? packageInfo.hashCode() : 0)) * 31;
        ApplicationInfo applicationInfo = this.appInfo;
        int iHashCode19 = (iHashCode18 + (applicationInfo != null ? applicationInfo.hashCode() : 0)) * 31;
        Collection<String> collection4 = this.redactedKeys;
        return iHashCode19 + (collection4 != null ? collection4.hashCode() : 0);
    }

    public String toString() {
        return "ImmutableConfig(apiKey=" + this.apiKey + ", autoDetectErrors=" + this.autoDetectErrors + ", enabledErrorTypes=" + this.enabledErrorTypes + ", autoTrackSessions=" + this.autoTrackSessions + ", sendThreads=" + this.sendThreads + ", discardClasses=" + this.discardClasses + ", enabledReleaseStages=" + this.enabledReleaseStages + ", projectPackages=" + this.projectPackages + ", enabledBreadcrumbTypes=" + this.enabledBreadcrumbTypes + ", telemetry=" + this.telemetry + ", releaseStage=" + this.releaseStage + ", buildUuid=" + this.buildUuid + ", appVersion=" + this.appVersion + ", versionCode=" + this.versionCode + ", appType=" + this.appType + ", delivery=" + this.delivery + ", endpoints=" + this.endpoints + ", persistUser=" + this.persistUser + ", launchDurationMillis=" + this.launchDurationMillis + ", logger=" + this.logger + ", maxBreadcrumbs=" + this.maxBreadcrumbs + ", maxPersistedEvents=" + this.maxPersistedEvents + ", maxPersistedSessions=" + this.maxPersistedSessions + ", maxReportedThreads=" + this.maxReportedThreads + ", persistenceDirectory=" + this.persistenceDirectory + ", sendLaunchCrashesSynchronously=" + this.sendLaunchCrashesSynchronously + ", attemptDeliveryOnCrash=" + this.attemptDeliveryOnCrash + ", packageInfo=" + this.packageInfo + ", appInfo=" + this.appInfo + ", redactedKeys=" + this.redactedKeys + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImmutableConfig(String str, boolean z, ErrorTypes errorTypes, boolean z2, ThreadSendPolicy threadSendPolicy, Collection<String> collection, Collection<String> collection2, Collection<String> collection3, Set<? extends BreadcrumbType> set, Set<? extends Telemetry> set2, String str2, String str3, String str4, Integer num, String str5, Delivery delivery, EndpointConfiguration endpointConfiguration, boolean z3, long j, Logger logger, int i, int i2, int i3, int i4, Lazy<? extends File> lazy, boolean z4, boolean z5, PackageInfo packageInfo, ApplicationInfo applicationInfo, Collection<String> collection4) {
        this.apiKey = str;
        this.autoDetectErrors = z;
        this.enabledErrorTypes = errorTypes;
        this.autoTrackSessions = z2;
        this.sendThreads = threadSendPolicy;
        this.discardClasses = collection;
        this.enabledReleaseStages = collection2;
        this.projectPackages = collection3;
        this.enabledBreadcrumbTypes = set;
        this.telemetry = set2;
        this.releaseStage = str2;
        this.buildUuid = str3;
        this.appVersion = str4;
        this.versionCode = num;
        this.appType = str5;
        this.delivery = delivery;
        this.endpoints = endpointConfiguration;
        this.persistUser = z3;
        this.launchDurationMillis = j;
        this.logger = logger;
        this.maxBreadcrumbs = i;
        this.maxPersistedEvents = i2;
        this.maxPersistedSessions = i3;
        this.maxReportedThreads = i4;
        this.persistenceDirectory = lazy;
        this.sendLaunchCrashesSynchronously = z4;
        this.attemptDeliveryOnCrash = z5;
        this.packageInfo = packageInfo;
        this.appInfo = applicationInfo;
        this.redactedKeys = collection4;
    }

    public final String getApiKey() {
        return this.apiKey;
    }

    public final ErrorTypes getEnabledErrorTypes() {
        return this.enabledErrorTypes;
    }

    public final boolean getAutoTrackSessions() {
        return this.autoTrackSessions;
    }

    public final ThreadSendPolicy getSendThreads() {
        return this.sendThreads;
    }

    public final Collection<String> getDiscardClasses() {
        return this.discardClasses;
    }

    public final Collection<String> getEnabledReleaseStages() {
        return this.enabledReleaseStages;
    }

    public final Collection<String> getProjectPackages() {
        return this.projectPackages;
    }

    public final Set<Telemetry> getTelemetry() {
        return this.telemetry;
    }

    public final String getReleaseStage() {
        return this.releaseStage;
    }

    public final String getBuildUuid() {
        return this.buildUuid;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final Integer getVersionCode() {
        return this.versionCode;
    }

    public final String getAppType() {
        return this.appType;
    }

    public final Delivery getDelivery() {
        return this.delivery;
    }

    public final EndpointConfiguration getEndpoints() {
        return this.endpoints;
    }

    public final boolean getPersistUser() {
        return this.persistUser;
    }

    public final long getLaunchDurationMillis() {
        return this.launchDurationMillis;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final int getMaxBreadcrumbs() {
        return this.maxBreadcrumbs;
    }

    public final int getMaxPersistedEvents() {
        return this.maxPersistedEvents;
    }

    public final int getMaxPersistedSessions() {
        return this.maxPersistedSessions;
    }

    public final int getMaxReportedThreads() {
        return this.maxReportedThreads;
    }

    public final Lazy<File> getPersistenceDirectory() {
        return this.persistenceDirectory;
    }

    public final boolean getSendLaunchCrashesSynchronously() {
        return this.sendLaunchCrashesSynchronously;
    }

    public final boolean getAttemptDeliveryOnCrash() {
        return this.attemptDeliveryOnCrash;
    }

    public final PackageInfo getPackageInfo() {
        return this.packageInfo;
    }

    public final ApplicationInfo getAppInfo() {
        return this.appInfo;
    }

    public final Collection<String> getRedactedKeys() {
        return this.redactedKeys;
    }

    @JvmName(name = "getErrorApiDeliveryParams")
    public final DeliveryParams getErrorApiDeliveryParams(EventPayload payload) {
        return new DeliveryParams(this.endpoints.getNotify(), DeliveryHeadersKt.errorApiHeaders(payload));
    }

    @JvmName(name = "getSessionApiDeliveryParams")
    public final DeliveryParams getSessionApiDeliveryParams() {
        return new DeliveryParams(this.endpoints.getSessions(), DeliveryHeadersKt.sessionApiHeaders(this.apiKey));
    }

    public final boolean shouldDiscardError(Throwable exc) {
        return shouldDiscardByReleaseStage() || shouldDiscardByErrorClass$bugsnag_android_core_release(exc);
    }

    public final boolean shouldDiscardError(String errorClass) {
        return shouldDiscardByReleaseStage() || shouldDiscardByErrorClass$bugsnag_android_core_release(errorClass);
    }

    public final boolean shouldDiscardSession(boolean autoCaptured) {
        return shouldDiscardByReleaseStage() || (autoCaptured && !this.autoTrackSessions);
    }

    public final boolean shouldDiscardBreadcrumb(BreadcrumbType type) {
        Set<BreadcrumbType> set = this.enabledBreadcrumbTypes;
        return (set == null || set.contains(type)) ? false : true;
    }

    public final boolean shouldDiscardByReleaseStage() {
        Collection<String> collection = this.enabledReleaseStages;
        return (collection == null || CollectionsKt___CollectionsKt.contains(collection, this.releaseStage)) ? false : true;
    }

    @VisibleForTesting
    public final boolean shouldDiscardByErrorClass$bugsnag_android_core_release(String errorClass) {
        return CollectionsKt___CollectionsKt.contains(this.discardClasses, errorClass);
    }

    @VisibleForTesting
    public final boolean shouldDiscardByErrorClass$bugsnag_android_core_release(Throwable exc) {
        List<Throwable> listSafeUnrollCauses = ThrowableUtils.safeUnrollCauses(exc);
        if ((listSafeUnrollCauses instanceof Collection) && listSafeUnrollCauses.isEmpty()) {
            return false;
        }
        Iterator<T> it = listSafeUnrollCauses.iterator();
        while (it.hasNext()) {
            if (shouldDiscardByErrorClass$bugsnag_android_core_release(((Throwable) it.next()).getClass().getName())) {
                return true;
            }
        }
        return false;
    }
}
