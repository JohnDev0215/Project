package com.bugsnag.android;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmField;

/* JADX INFO: compiled from: ConfigInternal.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\"\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u0000 \u00ad\u00012\u00020\u00012\u00020\u00012\u00020\u00012\u00020\u0001:\u0002\u00ad\u0001B\u0011\u0012\u0007\u0010¦\u0001\u001a\u00020\u0004¢\u0006\u0005\b¬\u0001\u0010\u001eJ\u0018\u0010\u0005\u001a\u00020\u00042\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bJ\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\fR\u0016\u0010\u000e\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010&\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010\u001a\u001a\u0004\b'\u0010\u001c\"\u0004\b(\u0010\u001eR\"\u0010*\u001a\u00020)8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00101\u001a\u0002008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\"\u00108\u001a\u0002078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010>\u001a\u0002008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b>\u00102\u001a\u0004\b?\u00104\"\u0004\b@\u00106R\"\u0010A\u001a\u0002008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bA\u00102\u001a\u0004\bB\u00104\"\u0004\bC\u00106R\"\u0010E\u001a\u00020D8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\"\u0010K\u001a\u0002008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u00102\u001a\u0004\bL\u00104\"\u0004\bM\u00106R$\u0010N\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u0010\u001a\u001a\u0004\bO\u0010\u001c\"\u0004\bP\u0010\u001eR.\u0010S\u001a\u0004\u0018\u00010Q2\b\u0010R\u001a\u0004\u0018\u00010Q8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bS\u0010T\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR$\u0010Z\u001a\u0004\u0018\u00010Y8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\"\u0010a\u001a\u00020`8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\ba\u0010b\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\"\u0010g\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\"\u0010m\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bm\u0010h\u001a\u0004\bn\u0010j\"\u0004\bo\u0010lR\"\u0010p\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bp\u0010h\u001a\u0004\bq\u0010j\"\u0004\br\u0010lR\"\u0010s\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bs\u0010h\u001a\u0004\bt\u0010j\"\u0004\bu\u0010lR\"\u0010v\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bv\u0010h\u001a\u0004\bw\u0010j\"\u0004\bx\u0010lR$\u0010y\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\by\u0010\u001a\u001a\u0004\bz\u0010\u001c\"\u0004\b{\u0010\u001eR+\u0010}\u001a\b\u0012\u0004\u0012\u00020\u00040|8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0004\b}\u0010~\u001a\u0005\b\u007f\u0010\u0080\u0001\"\u0006\b\u0081\u0001\u0010\u0082\u0001R0\u0010\u0083\u0001\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010|8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u0083\u0001\u0010~\u001a\u0006\b\u0084\u0001\u0010\u0080\u0001\"\u0006\b\u0085\u0001\u0010\u0082\u0001R1\u0010\u0087\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0086\u0001\u0018\u00010|8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u0087\u0001\u0010~\u001a\u0006\b\u0088\u0001\u0010\u0080\u0001\"\u0006\b\u0089\u0001\u0010\u0082\u0001R/\u0010\u008b\u0001\u001a\t\u0012\u0005\u0012\u00030\u008a\u00010|8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u008b\u0001\u0010~\u001a\u0006\b\u008c\u0001\u0010\u0080\u0001\"\u0006\b\u008d\u0001\u0010\u0082\u0001R.\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040|8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u008e\u0001\u0010~\u001a\u0006\b\u008f\u0001\u0010\u0080\u0001\"\u0006\b\u0090\u0001\u0010\u0082\u0001R,\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u0091\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0092\u0001\u0010\u0093\u0001\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0006\b\u0096\u0001\u0010\u0097\u0001R&\u0010\u0098\u0001\u001a\u0002008\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0098\u0001\u00102\u001a\u0005\b\u0099\u0001\u00104\"\u0005\b\u009a\u0001\u00106R\u001d\u0010\u009c\u0001\u001a\u00030\u009b\u00018\u0006¢\u0006\u0010\n\u0006\b\u009c\u0001\u0010\u009d\u0001\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R1\u0010¢\u0001\u001a\u0014\u0012\u0004\u0012\u00020\b0 \u0001j\t\u0012\u0004\u0012\u00020\b`¡\u00018\u0004X\u0084\u0004¢\u0006\u0010\n\u0006\b¢\u0001\u0010£\u0001\u001a\u0006\b¤\u0001\u0010¥\u0001R&\u0010¦\u0001\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b¦\u0001\u0010\u001a\u001a\u0005\b§\u0001\u0010\u001c\"\u0005\b¨\u0001\u0010\u001eR5\u0010«\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040|2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00040|8F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b©\u0001\u0010\u0080\u0001\"\u0006\bª\u0001\u0010\u0082\u0001¨\u0006®\u0001"}, d2 = {"Lcom/bugsnag/android/ConfigInternal;", "", "", "coll", "", "toCommaSeparated", "Lcom/bugsnag/android/User;", "getUser", "Lcom/bugsnag/android/Plugin;", "plugin", "", "addPlugin", "", "getConfigDifferences", "user", "Lcom/bugsnag/android/User;", "Lcom/bugsnag/android/CallbackState;", "callbackState", "Lcom/bugsnag/android/CallbackState;", "Lcom/bugsnag/android/MetadataState;", "metadataState", "Lcom/bugsnag/android/MetadataState;", "Lcom/bugsnag/android/FeatureFlagState;", "featureFlagState", "Lcom/bugsnag/android/FeatureFlagState;", Splash.PARAMS_APP_VERSION, "Ljava/lang/String;", "getAppVersion", "()Ljava/lang/String;", "setAppVersion", "(Ljava/lang/String;)V", "", "versionCode", "Ljava/lang/Integer;", "getVersionCode", "()Ljava/lang/Integer;", "setVersionCode", "(Ljava/lang/Integer;)V", "releaseStage", "getReleaseStage", "setReleaseStage", "Lcom/bugsnag/android/ThreadSendPolicy;", "sendThreads", "Lcom/bugsnag/android/ThreadSendPolicy;", "getSendThreads", "()Lcom/bugsnag/android/ThreadSendPolicy;", "setSendThreads", "(Lcom/bugsnag/android/ThreadSendPolicy;)V", "", "persistUser", "Z", "getPersistUser", "()Z", "setPersistUser", "(Z)V", "", "launchDurationMillis", "J", "getLaunchDurationMillis", "()J", "setLaunchDurationMillis", "(J)V", "autoTrackSessions", "getAutoTrackSessions", "setAutoTrackSessions", "sendLaunchCrashesSynchronously", "getSendLaunchCrashesSynchronously", "setSendLaunchCrashesSynchronously", "Lcom/bugsnag/android/ErrorTypes;", "enabledErrorTypes", "Lcom/bugsnag/android/ErrorTypes;", "getEnabledErrorTypes", "()Lcom/bugsnag/android/ErrorTypes;", "setEnabledErrorTypes", "(Lcom/bugsnag/android/ErrorTypes;)V", "autoDetectErrors", "getAutoDetectErrors", "setAutoDetectErrors", "appType", "getAppType", "setAppType", "Lcom/bugsnag/android/Logger;", "value", "logger", "Lcom/bugsnag/android/Logger;", "getLogger", "()Lcom/bugsnag/android/Logger;", "setLogger", "(Lcom/bugsnag/android/Logger;)V", "Lcom/bugsnag/android/Delivery;", "delivery", "Lcom/bugsnag/android/Delivery;", "getDelivery", "()Lcom/bugsnag/android/Delivery;", "setDelivery", "(Lcom/bugsnag/android/Delivery;)V", "Lcom/bugsnag/android/EndpointConfiguration;", "endpoints", "Lcom/bugsnag/android/EndpointConfiguration;", "getEndpoints", "()Lcom/bugsnag/android/EndpointConfiguration;", "setEndpoints", "(Lcom/bugsnag/android/EndpointConfiguration;)V", "maxBreadcrumbs", "I", "getMaxBreadcrumbs", "()I", "setMaxBreadcrumbs", "(I)V", "maxPersistedEvents", "getMaxPersistedEvents", "setMaxPersistedEvents", "maxPersistedSessions", "getMaxPersistedSessions", "setMaxPersistedSessions", "maxReportedThreads", "getMaxReportedThreads", "setMaxReportedThreads", "maxStringValueLength", "getMaxStringValueLength", "setMaxStringValueLength", "context", "getContext", "setContext", "", "discardClasses", "Ljava/util/Set;", "getDiscardClasses", "()Ljava/util/Set;", "setDiscardClasses", "(Ljava/util/Set;)V", "enabledReleaseStages", "getEnabledReleaseStages", "setEnabledReleaseStages", "Lcom/bugsnag/android/BreadcrumbType;", "enabledBreadcrumbTypes", "getEnabledBreadcrumbTypes", "setEnabledBreadcrumbTypes", "Lcom/bugsnag/android/Telemetry;", "telemetry", "getTelemetry", "setTelemetry", "projectPackages", "getProjectPackages", "setProjectPackages", "Ljava/io/File;", "persistenceDirectory", "Ljava/io/File;", "getPersistenceDirectory", "()Ljava/io/File;", "setPersistenceDirectory", "(Ljava/io/File;)V", "attemptDeliveryOnCrash", "getAttemptDeliveryOnCrash", "setAttemptDeliveryOnCrash", "Lcom/bugsnag/android/Notifier;", "notifier", "Lcom/bugsnag/android/Notifier;", "getNotifier", "()Lcom/bugsnag/android/Notifier;", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "plugins", "Ljava/util/HashSet;", "getPlugins", "()Ljava/util/HashSet;", "apiKey", "getApiKey", "setApiKey", "getRedactedKeys", "setRedactedKeys", "redactedKeys", "<init>", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class ConfigInternal {
    private String apiKey;
    private String appVersion;
    private boolean attemptDeliveryOnCrash;
    private String context;
    private Delivery delivery;
    private Set<? extends BreadcrumbType> enabledBreadcrumbTypes;
    private Set<String> enabledReleaseStages;
    private final Notifier notifier;
    private boolean persistUser;
    private File persistenceDirectory;
    private final HashSet<Plugin> plugins;
    private Set<String> projectPackages;
    private String releaseStage;
    private Set<? extends Telemetry> telemetry;
    private User user = new User(null, null, null, 7, null);

    @JvmField
    public final CallbackState callbackState = new CallbackState(null, null, null, null, 15, null);

    @JvmField
    public final MetadataState metadataState = new MetadataState(null, 1, null);

    @JvmField
    public final FeatureFlagState featureFlagState = new FeatureFlagState(null, 1, null);
    private Integer versionCode = 0;
    private ThreadSendPolicy sendThreads = ThreadSendPolicy.ALWAYS;
    private long launchDurationMillis = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
    private boolean autoTrackSessions = true;
    private boolean sendLaunchCrashesSynchronously = true;
    private ErrorTypes enabledErrorTypes = new ErrorTypes(false, false, false, false, 15, null);
    private boolean autoDetectErrors = true;
    private String appType = "android";
    private Logger logger = DebugLogger.INSTANCE;
    private EndpointConfiguration endpoints = new EndpointConfiguration(null, null, 3, null);
    private int maxBreadcrumbs = 100;
    private int maxPersistedEvents = 32;
    private int maxPersistedSessions = 128;
    private int maxReportedThreads = 200;
    private int maxStringValueLength = 10000;
    private Set<String> discardClasses = SetsKt__SetsKt.emptySet();

    public ConfigInternal(String str) {
        this.apiKey = str;
        EnumSet enumSetOf = EnumSet.of(Telemetry.INTERNAL_ERRORS, Telemetry.USAGE);
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(enumSetOf, "EnumSet.of(Telemetry.INT…_ERRORS, Telemetry.USAGE)");
        this.telemetry = enumSetOf;
        this.projectPackages = SetsKt__SetsKt.emptySet();
        this.notifier = new Notifier(null, null, null, 7, null);
        this.plugins = new HashSet<>();
    }

    public final String getApiKey() {
        return this.apiKey;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final Integer getVersionCode() {
        return this.versionCode;
    }

    public final void setVersionCode(Integer num) {
        this.versionCode = num;
    }

    public final String getReleaseStage() {
        return this.releaseStage;
    }

    public final void setReleaseStage(String str) {
        this.releaseStage = str;
    }

    public final ThreadSendPolicy getSendThreads() {
        return this.sendThreads;
    }

    public final boolean getPersistUser() {
        return this.persistUser;
    }

    public final long getLaunchDurationMillis() {
        return this.launchDurationMillis;
    }

    public final boolean getAutoTrackSessions() {
        return this.autoTrackSessions;
    }

    public final boolean getSendLaunchCrashesSynchronously() {
        return this.sendLaunchCrashesSynchronously;
    }

    public final ErrorTypes getEnabledErrorTypes() {
        return this.enabledErrorTypes;
    }

    public final boolean getAutoDetectErrors() {
        return this.autoDetectErrors;
    }

    public final String getAppType() {
        return this.appType;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final void setLogger(Logger logger) {
        if (logger == null) {
            logger = NoopLogger.INSTANCE;
        }
        this.logger = logger;
    }

    public final Delivery getDelivery() {
        return this.delivery;
    }

    public final void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public final EndpointConfiguration getEndpoints() {
        return this.endpoints;
    }

    public final void setEndpoints(EndpointConfiguration endpointConfiguration) {
        this.endpoints = endpointConfiguration;
    }

    public final int getMaxBreadcrumbs() {
        return this.maxBreadcrumbs;
    }

    public final void setMaxBreadcrumbs(int i) {
        this.maxBreadcrumbs = i;
    }

    public final int getMaxPersistedEvents() {
        return this.maxPersistedEvents;
    }

    public final void setMaxPersistedEvents(int i) {
        this.maxPersistedEvents = i;
    }

    public final int getMaxPersistedSessions() {
        return this.maxPersistedSessions;
    }

    public final void setMaxPersistedSessions(int i) {
        this.maxPersistedSessions = i;
    }

    public final int getMaxReportedThreads() {
        return this.maxReportedThreads;
    }

    public final void setMaxReportedThreads(int i) {
        this.maxReportedThreads = i;
    }

    public final int getMaxStringValueLength() {
        return this.maxStringValueLength;
    }

    public final void setMaxStringValueLength(int i) {
        this.maxStringValueLength = i;
    }

    public final String getContext() {
        return this.context;
    }

    public final Set<String> getRedactedKeys() {
        return this.metadataState.getMetadata().getRedactedKeys();
    }

    public final Set<String> getDiscardClasses() {
        return this.discardClasses;
    }

    public final Set<String> getEnabledReleaseStages() {
        return this.enabledReleaseStages;
    }

    public final Set<BreadcrumbType> getEnabledBreadcrumbTypes() {
        return this.enabledBreadcrumbTypes;
    }

    public final void setEnabledBreadcrumbTypes(Set<? extends BreadcrumbType> set) {
        this.enabledBreadcrumbTypes = set;
    }

    public final Set<Telemetry> getTelemetry() {
        return this.telemetry;
    }

    public final Set<String> getProjectPackages() {
        return this.projectPackages;
    }

    public final void setProjectPackages(Set<String> set) {
        this.projectPackages = set;
    }

    public final File getPersistenceDirectory() {
        return this.persistenceDirectory;
    }

    public final boolean getAttemptDeliveryOnCrash() {
        return this.attemptDeliveryOnCrash;
    }

    public final Notifier getNotifier() {
        return this.notifier;
    }

    protected final HashSet<Plugin> getPlugins() {
        return this.plugins;
    }

    public User getUser() {
        return this.user;
    }

    public final void addPlugin(Plugin plugin) {
        this.plugins.add(plugin);
    }

    public final Map<String, Object> getConfigDifferences() {
        Pair pair;
        ConfigInternal configInternal = new ConfigInternal("");
        Pair[] pairArr = new Pair[15];
        pairArr[0] = this.plugins.size() > 0 ? TuplesKt.to("pluginCount", Integer.valueOf(this.plugins.size())) : null;
        boolean z = this.autoDetectErrors;
        pairArr[1] = z != configInternal.autoDetectErrors ? TuplesKt.to("autoDetectErrors", Boolean.valueOf(z)) : null;
        boolean z2 = this.autoTrackSessions;
        pairArr[2] = z2 != configInternal.autoTrackSessions ? TuplesKt.to("autoTrackSessions", Boolean.valueOf(z2)) : null;
        pairArr[3] = this.discardClasses.size() > 0 ? TuplesKt.to("discardClassesCount", Integer.valueOf(this.discardClasses.size())) : null;
        pairArr[4] = kotlin.jvm.internal.Intrinsics.areEqual(this.enabledBreadcrumbTypes, configInternal.enabledBreadcrumbTypes) ^ true ? TuplesKt.to("enabledBreadcrumbTypes", toCommaSeparated(this.enabledBreadcrumbTypes)) : null;
        if (!kotlin.jvm.internal.Intrinsics.areEqual(this.enabledErrorTypes, configInternal.enabledErrorTypes)) {
            String[] strArr = new String[4];
            strArr[0] = this.enabledErrorTypes.getAnrs() ? "anrs" : null;
            strArr[1] = this.enabledErrorTypes.getNdkCrashes() ? "ndkCrashes" : null;
            strArr[2] = this.enabledErrorTypes.getUnhandledExceptions() ? "unhandledExceptions" : null;
            strArr[3] = this.enabledErrorTypes.getUnhandledRejections() ? "unhandledRejections" : null;
            pair = TuplesKt.to("enabledErrorTypes", toCommaSeparated(CollectionsKt__CollectionsKt.listOfNotNull((Object[]) strArr)));
        } else {
            pair = null;
        }
        pairArr[5] = pair;
        long j = this.launchDurationMillis;
        pairArr[6] = j != 0 ? TuplesKt.to("launchDurationMillis", Long.valueOf(j)) : null;
        pairArr[7] = kotlin.jvm.internal.Intrinsics.areEqual(this.logger, NoopLogger.INSTANCE) ^ true ? TuplesKt.to("logger", Boolean.TRUE) : null;
        int i = this.maxBreadcrumbs;
        pairArr[8] = i != configInternal.maxBreadcrumbs ? TuplesKt.to("maxBreadcrumbs", Integer.valueOf(i)) : null;
        int i2 = this.maxPersistedEvents;
        pairArr[9] = i2 != configInternal.maxPersistedEvents ? TuplesKt.to("maxPersistedEvents", Integer.valueOf(i2)) : null;
        int i3 = this.maxPersistedSessions;
        pairArr[10] = i3 != configInternal.maxPersistedSessions ? TuplesKt.to("maxPersistedSessions", Integer.valueOf(i3)) : null;
        int i4 = this.maxReportedThreads;
        pairArr[11] = i4 != configInternal.maxReportedThreads ? TuplesKt.to("maxReportedThreads", Integer.valueOf(i4)) : null;
        pairArr[12] = this.persistenceDirectory != null ? TuplesKt.to("persistenceDirectorySet", Boolean.TRUE) : null;
        ThreadSendPolicy threadSendPolicy = this.sendThreads;
        pairArr[13] = threadSendPolicy != configInternal.sendThreads ? TuplesKt.to("sendThreads", threadSendPolicy) : null;
        boolean z3 = this.attemptDeliveryOnCrash;
        pairArr[14] = z3 != configInternal.attemptDeliveryOnCrash ? TuplesKt.to("attemptDeliveryOnCrash", Boolean.valueOf(z3)) : null;
        return MapsKt__MapsKt.toMap(CollectionsKt__CollectionsKt.listOfNotNull((Object[]) pairArr));
    }

    private final String toCommaSeparated(Collection<? extends Object> coll) {
        String strJoinToString$default;
        if (coll != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(coll, 10));
            Iterator<T> it = coll.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toString());
            }
            List listSorted = CollectionsKt___CollectionsKt.sorted(arrayList);
            if (listSorted != null && (strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(listSorted, ",", null, null, 0, null, null, 62, null)) != null) {
                return strJoinToString$default;
            }
        }
        return "";
    }
}
