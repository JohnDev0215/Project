package com.bugsnag.android;

import androidx.core.app.NotificationCompat;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.InternalMetrics;
import com.bugsnag.android.internal.InternalMetricsNoop;
import com.bugsnag.android.internal.JsonHelper;
import com.bugsnag.android.internal.TrimMetrics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: EventInternal.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00012\u00020\u0001B>\b\u0011\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010/\u0012\b\u0010\u009b\u0001\u001a\u00030\u009a\u0001\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\t\b\u0002\u0010\u009c\u0001\u001a\u00020=\u0012\b\b\u0002\u0010C\u001a\u00020B¢\u0006\u0006\b\u009d\u0001\u0010\u009e\u0001B´\u0001\b\u0010\u0012\u0006\u0010V\u001a\u00020\u001c\u0012\u0006\u00109\u001a\u000208\u0012\u000e\b\u0002\u0010l\u001a\b\u0012\u0004\u0012\u00020k0j\u0012\u000e\b\u0002\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001c0\f\u0012\u000e\b\u0002\u0010s\u001a\b\u0012\u0004\u0012\u00020r0j\u0012\b\b\u0002\u0010>\u001a\u00020=\u0012\b\b\u0002\u0010C\u001a\u00020B\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010/\u0012\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020\u001c0I\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\u000e\b\u0002\u0010w\u001a\b\u0012\u0004\u0012\u00020v0j\u0012\n\b\u0002\u0010\u009f\u0001\u001a\u00030\u0087\u0001\u0012\u0011\b\u0002\u0010 \u0001\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\f¢\u0006\u0006\b\u009d\u0001\u0010¡\u0001J\u0006\u0010\u0004\u001a\u00020\u0003J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0004J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0019H\u0004J\u0006\u0010\u001d\u001a\u00020\u001cJ\u000e\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001eJ\u000e\u0010#\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u001eJ&\u0010'\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u001c2\b\u0010%\u001a\u0004\u0018\u00010\u001c2\b\u0010&\u001a\u0004\u0018\u00010\u001cH\u0016J&\u0010+\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u001c2\u0014\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u00010)H\u0016J\"\u0010+\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u0016J\u001a\u0010.\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u001c2\b\u0010-\u001a\u0004\u0018\u00010\u001cH\u0016R\u0019\u00100\u001a\u0004\u0018\u00010/8\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\"\u0010\u0015\u001a\u00020\u00148\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0015\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u0010\u0017R\u0017\u00109\u001a\u0002088\u0006¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u0017\u0010>\u001a\u00020=8\u0006¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010AR\u0017\u0010C\u001a\u00020B8\u0006¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010FR\u001a\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001c0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010HR(\u0010J\u001a\b\u0012\u0004\u0012\u00020\u001c0I8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010RR\u0018\u0010T\u001a\u0004\u0018\u00010S8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\"\u0010V\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bV\u0010W\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\"\u0010]\u001a\u00020\\8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b]\u0010^\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\"\u0010d\u001a\u00020c8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\bd\u0010e\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR(\u0010l\u001a\b\u0012\u0004\u0012\u00020k0j8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bl\u0010m\u001a\u0004\bn\u0010o\"\u0004\bp\u0010qR(\u0010s\u001a\b\u0012\u0004\u0012\u00020r0j8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bs\u0010m\u001a\u0004\bt\u0010o\"\u0004\bu\u0010qR(\u0010w\u001a\b\u0012\u0004\u0012\u00020v0j8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bw\u0010m\u001a\u0004\bx\u0010o\"\u0004\by\u0010qR$\u0010z\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bz\u0010W\u001a\u0004\b{\u0010Y\"\u0004\b|\u0010[R$\u0010}\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b}\u0010W\u001a\u0004\b~\u0010Y\"\u0004\b\u007f\u0010[R*\u0010\u0081\u0001\u001a\u00030\u0080\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0081\u0001\u0010\u0082\u0001\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"\u0006\b\u0085\u0001\u0010\u0086\u0001R*\u0010\u0088\u0001\u001a\u00030\u0087\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u0088\u0001\u0010\u0089\u0001\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001R(\u0010\u001a\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00198F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001\"\u0006\b\u0090\u0001\u0010\u0091\u0001R)\u0010\u0096\u0001\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001\"\u0006\b\u0094\u0001\u0010\u0095\u0001R3\u0010\u0099\u0001\u001a\b\u0012\u0004\u0012\u00020\u001c0I2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001c0I8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0097\u0001\u0010M\"\u0005\b\u0098\u0001\u0010O¨\u0006¢\u0001"}, d2 = {"Lcom/bugsnag/android/EventInternal;", "", "Lcom/bugsnag/android/JsonStream$Streamable;", "", "getOriginalUnhandled", "Lcom/bugsnag/android/Event;", NotificationCompat.CATEGORY_EVENT, "isAnr", "Lcom/bugsnag/android/JsonStream;", "parentWriter", "", "toStream", "", "Lcom/bugsnag/android/ErrorType;", "getErrorTypesFromStackframes$bugsnag_android_core_release", "()Ljava/util/Set;", "getErrorTypesFromStackframes", "normalizeStackframeErrorTypes$bugsnag_android_core_release", "()V", "normalizeStackframeErrorTypes", "Lcom/bugsnag/android/SeverityReason;", "severityReason", "updateSeverityReasonInternal$bugsnag_android_core_release", "(Lcom/bugsnag/android/SeverityReason;)V", "updateSeverityReasonInternal", "Lcom/bugsnag/android/Severity;", "severity", "updateSeverityInternal", "", "getSeverityReasonType", "", "maxLength", "Lcom/bugsnag/android/internal/TrimMetrics;", "trimMetadataStringsTo", "byteCount", "trimBreadcrumbsBy", "id", "email", "name", "setUser", "section", "", "value", "addMetadata", "key", "variant", "addFeatureFlag", "", "originalError", "Ljava/lang/Throwable;", "getOriginalError", "()Ljava/lang/Throwable;", "Lcom/bugsnag/android/SeverityReason;", "getSeverityReason$bugsnag_android_core_release", "()Lcom/bugsnag/android/SeverityReason;", "setSeverityReason$bugsnag_android_core_release", "Lcom/bugsnag/android/Logger;", "logger", "Lcom/bugsnag/android/Logger;", "getLogger", "()Lcom/bugsnag/android/Logger;", "Lcom/bugsnag/android/Metadata;", "metadata", "Lcom/bugsnag/android/Metadata;", "getMetadata", "()Lcom/bugsnag/android/Metadata;", "Lcom/bugsnag/android/FeatureFlags;", "featureFlags", "Lcom/bugsnag/android/FeatureFlags;", "getFeatureFlags", "()Lcom/bugsnag/android/FeatureFlags;", "discardClasses", "Ljava/util/Set;", "", "projectPackages", "Ljava/util/Collection;", "getProjectPackages$bugsnag_android_core_release", "()Ljava/util/Collection;", "setProjectPackages$bugsnag_android_core_release", "(Ljava/util/Collection;)V", "Lcom/bugsnag/android/ObjectJsonStreamer;", "jsonStreamer", "Lcom/bugsnag/android/ObjectJsonStreamer;", "Lcom/bugsnag/android/Session;", "session", "Lcom/bugsnag/android/Session;", "apiKey", "Ljava/lang/String;", "getApiKey", "()Ljava/lang/String;", "setApiKey", "(Ljava/lang/String;)V", "Lcom/bugsnag/android/AppWithState;", "app", "Lcom/bugsnag/android/AppWithState;", "getApp", "()Lcom/bugsnag/android/AppWithState;", "setApp", "(Lcom/bugsnag/android/AppWithState;)V", "Lcom/bugsnag/android/DeviceWithState;", "device", "Lcom/bugsnag/android/DeviceWithState;", "getDevice", "()Lcom/bugsnag/android/DeviceWithState;", "setDevice", "(Lcom/bugsnag/android/DeviceWithState;)V", "", "Lcom/bugsnag/android/Breadcrumb;", "breadcrumbs", "Ljava/util/List;", "getBreadcrumbs", "()Ljava/util/List;", "setBreadcrumbs", "(Ljava/util/List;)V", "Lcom/bugsnag/android/Error;", MAPWebViewEventHelper.KEY_ERRORS, "getErrors", "setErrors", "Lcom/bugsnag/android/Thread;", "threads", "getThreads", "setThreads", "groupingHash", "getGroupingHash", "setGroupingHash", "context", "getContext", "setContext", "Lcom/bugsnag/android/internal/InternalMetrics;", "internalMetrics", "Lcom/bugsnag/android/internal/InternalMetrics;", "getInternalMetrics", "()Lcom/bugsnag/android/internal/InternalMetrics;", "setInternalMetrics", "(Lcom/bugsnag/android/internal/InternalMetrics;)V", "Lcom/bugsnag/android/User;", "userImpl", "Lcom/bugsnag/android/User;", "getUserImpl$bugsnag_android_core_release", "()Lcom/bugsnag/android/User;", "setUserImpl$bugsnag_android_core_release", "(Lcom/bugsnag/android/User;)V", "getSeverity", "()Lcom/bugsnag/android/Severity;", "setSeverity", "(Lcom/bugsnag/android/Severity;)V", "getUnhandled", "()Z", "setUnhandled", "(Z)V", "unhandled", "getRedactedKeys", "setRedactedKeys", "redactedKeys", "Lcom/bugsnag/android/internal/ImmutableConfig;", "config", "data", "<init>", "(Ljava/lang/Throwable;Lcom/bugsnag/android/internal/ImmutableConfig;Lcom/bugsnag/android/SeverityReason;Lcom/bugsnag/android/Metadata;Lcom/bugsnag/android/FeatureFlags;)V", "user", "redactionKeys", "(Ljava/lang/String;Lcom/bugsnag/android/Logger;Ljava/util/List;Ljava/util/Set;Ljava/util/List;Lcom/bugsnag/android/Metadata;Lcom/bugsnag/android/FeatureFlags;Ljava/lang/Throwable;Ljava/util/Collection;Lcom/bugsnag/android/SeverityReason;Ljava/util/List;Lcom/bugsnag/android/User;Ljava/util/Set;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class EventInternal implements JsonStream.Streamable {
    private String apiKey;
    public AppWithState app;
    private List<Breadcrumb> breadcrumbs;
    private String context;
    public DeviceWithState device;
    private final Set<String> discardClasses;
    private List<Error> errors;
    private final FeatureFlags featureFlags;
    private String groupingHash;
    private InternalMetrics internalMetrics;
    private final ObjectJsonStreamer jsonStreamer;
    private final Logger logger;
    private final Metadata metadata;
    private final Throwable originalError;
    private Collection<String> projectPackages;

    @JvmField
    public Session session;
    private SeverityReason severityReason;
    private List<Thread> threads;
    private User userImpl;

    @JvmOverloads
    public EventInternal(Throwable th, ImmutableConfig immutableConfig, SeverityReason severityReason, Metadata metadata) {
        this(th, immutableConfig, severityReason, metadata, null, 16, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ EventInternal(Throwable th, ImmutableConfig immutableConfig, SeverityReason severityReason, Metadata metadata, FeatureFlags featureFlags, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = 1;
        this((i & 1) != 0 ? null : th, immutableConfig, severityReason, (i & 8) != 0 ? new Metadata(null, i2, 0 == true ? 1 : 0) : metadata, (i & 16) != 0 ? new FeatureFlags(0 == true ? 1 : 0, i2, 0 == true ? 1 : 0) : featureFlags);
    }

    @JvmOverloads
    public EventInternal(Throwable th, ImmutableConfig immutableConfig, SeverityReason severityReason, Metadata metadata, FeatureFlags featureFlags) {
        List listCreateError;
        String apiKey = immutableConfig.getApiKey();
        Logger logger = immutableConfig.getLogger();
        ArrayList arrayList = new ArrayList();
        Set set = CollectionsKt___CollectionsKt.toSet(immutableConfig.getDiscardClasses());
        if (th == null) {
            listCreateError = new ArrayList();
        } else {
            listCreateError = Error.createError(th, immutableConfig.getProjectPackages(), immutableConfig.getLogger());
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(listCreateError, "Error.createError(origin…tPackages, config.logger)");
        }
        this(apiKey, logger, arrayList, set, listCreateError, metadata.copy(), featureFlags.copy(), th, immutableConfig.getProjectPackages(), severityReason, new ThreadState(th, severityReason.getUnhandled(), immutableConfig).getThreads(), new User(null, null, null, 7, null), CollectionsKt___CollectionsKt.toSet(immutableConfig.getRedactedKeys()));
    }

    public /* synthetic */ EventInternal(String str, Logger logger, List list, Set set, List list2, Metadata metadata, FeatureFlags featureFlags, Throwable th, Collection collection, SeverityReason severityReason, List list3, User user, Set set2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Metadata metadata2;
        FeatureFlags featureFlags2;
        SeverityReason severityReason2;
        List arrayList = (i & 4) != 0 ? new ArrayList() : list;
        Set setEmptySet = (i & 8) != 0 ? SetsKt__SetsKt.emptySet() : set;
        List arrayList2 = (i & 16) != 0 ? new ArrayList() : list2;
        int i2 = 1;
        Map map = null;
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        if ((i & 32) != 0) {
            metadata2 = new Metadata(map, i2, b3 == true ? 1 : 0);
        } else {
            metadata2 = metadata;
        }
        if ((i & 64) != 0) {
            featureFlags2 = new FeatureFlags(b2 == true ? 1 : 0, i2, b == true ? 1 : 0);
        } else {
            featureFlags2 = featureFlags;
        }
        Throwable th2 = (i & 128) != 0 ? null : th;
        Collection collectionEmptySet = (i & 256) != 0 ? SetsKt__SetsKt.emptySet() : collection;
        if ((i & 512) != 0) {
            SeverityReason severityReasonNewInstance = SeverityReason.newInstance("handledException");
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(severityReasonNewInstance, "SeverityReason.newInstan…REASON_HANDLED_EXCEPTION)");
            severityReason2 = severityReasonNewInstance;
        } else {
            severityReason2 = severityReason;
        }
        this(str, logger, arrayList, setEmptySet, arrayList2, metadata2, featureFlags2, th2, collectionEmptySet, severityReason2, (i & 1024) != 0 ? new ArrayList() : list3, (i & 2048) != 0 ? new User(null, null, null, 7, null) : user, (i & 4096) != 0 ? null : set2);
    }

    public EventInternal(String str, Logger logger, List<Breadcrumb> list, Set<String> set, List<Error> list2, Metadata metadata, FeatureFlags featureFlags, Throwable th, Collection<String> collection, SeverityReason severityReason, List<Thread> list3, User user, Set<String> set2) {
        ObjectJsonStreamer objectJsonStreamer = new ObjectJsonStreamer();
        objectJsonStreamer.setRedactedKeys(CollectionsKt___CollectionsKt.toSet(objectJsonStreamer.getRedactedKeys()));
        Unit unit = Unit.INSTANCE;
        this.jsonStreamer = objectJsonStreamer;
        this.internalMetrics = new InternalMetricsNoop();
        this.logger = logger;
        this.apiKey = str;
        this.breadcrumbs = list;
        this.discardClasses = set;
        this.errors = list2;
        this.metadata = metadata;
        this.featureFlags = featureFlags;
        this.originalError = th;
        this.projectPackages = collection;
        this.severityReason = severityReason;
        this.threads = list3;
        this.userImpl = user;
        if (set2 != null) {
            setRedactedKeys(set2);
        }
    }

    public final void setProjectPackages$bugsnag_android_core_release(Collection<String> collection) {
        this.projectPackages = collection;
    }

    public final Severity getSeverity() {
        Severity currentSeverity = this.severityReason.getCurrentSeverity();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(currentSeverity, "severityReason.currentSeverity");
        return currentSeverity;
    }

    public final String getApiKey() {
        return this.apiKey;
    }

    public final AppWithState getApp() {
        AppWithState appWithState = this.app;
        if (appWithState == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("app");
        }
        return appWithState;
    }

    public final void setApp(AppWithState appWithState) {
        this.app = appWithState;
    }

    public final void setDevice(DeviceWithState deviceWithState) {
        this.device = deviceWithState;
    }

    public final boolean getUnhandled() {
        return this.severityReason.getUnhandled();
    }

    public final List<Breadcrumb> getBreadcrumbs() {
        return this.breadcrumbs;
    }

    public final void setBreadcrumbs(List<Breadcrumb> list) {
        this.breadcrumbs = list;
    }

    public final List<Error> getErrors() {
        return this.errors;
    }

    public final List<Thread> getThreads() {
        return this.threads;
    }

    public final void setGroupingHash(String str) {
        this.groupingHash = str;
    }

    public final void setContext(String str) {
        this.context = str;
    }

    public final void setRedactedKeys(Collection<String> collection) {
        this.jsonStreamer.setRedactedKeys(CollectionsKt___CollectionsKt.toSet(collection));
        this.metadata.setRedactedKeys(CollectionsKt___CollectionsKt.toSet(collection));
    }

    public final InternalMetrics getInternalMetrics() {
        return this.internalMetrics;
    }

    public final void setInternalMetrics(InternalMetrics internalMetrics) {
        this.internalMetrics = internalMetrics;
    }

    public final void setUserImpl$bugsnag_android_core_release(User user) {
        this.userImpl = user;
    }

    public final boolean getOriginalUnhandled() {
        return this.severityReason.originalUnhandled;
    }

    protected final boolean isAnr(Event event) {
        String errorClass;
        List<Error> errors = event.getErrors();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(errors, "event.errors");
        if (!errors.isEmpty()) {
            Error error = errors.get(0);
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(error, "error");
            errorClass = error.getErrorClass();
        } else {
            errorClass = null;
        }
        return kotlin.jvm.internal.Intrinsics.areEqual(CrashManagerActions.ANR, errorClass);
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream parentWriter) throws IOException {
        JsonStream jsonStream = new JsonStream(parentWriter, this.jsonStreamer);
        jsonStream.beginObject();
        jsonStream.name("context").value(this.context);
        jsonStream.name("metaData").value(this.metadata);
        jsonStream.name("severity").value(getSeverity());
        jsonStream.name("severityReason").value(this.severityReason);
        jsonStream.name("unhandled").value(this.severityReason.getUnhandled());
        jsonStream.name("exceptions");
        jsonStream.beginArray();
        Iterator<T> it = this.errors.iterator();
        while (it.hasNext()) {
            jsonStream.value((Error) it.next());
        }
        jsonStream.endArray();
        jsonStream.name("projectPackages");
        jsonStream.beginArray();
        Iterator<T> it2 = this.projectPackages.iterator();
        while (it2.hasNext()) {
            jsonStream.value((String) it2.next());
        }
        jsonStream.endArray();
        jsonStream.name("user").value(this.userImpl);
        JsonStream jsonStreamName = jsonStream.name("app");
        AppWithState appWithState = this.app;
        if (appWithState == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("app");
        }
        jsonStreamName.value(appWithState);
        JsonStream jsonStreamName2 = jsonStream.name("device");
        DeviceWithState deviceWithState = this.device;
        if (deviceWithState == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("device");
        }
        jsonStreamName2.value(deviceWithState);
        jsonStream.name("breadcrumbs").value(this.breadcrumbs);
        jsonStream.name("groupingHash").value(this.groupingHash);
        Map<String, Object> jsonableMap = this.internalMetrics.toJsonableMap();
        if (!jsonableMap.isEmpty()) {
            jsonStream.name("usage");
            jsonStream.beginObject();
            for (Map.Entry<String, Object> entry : jsonableMap.entrySet()) {
                jsonStream.name(entry.getKey()).value(entry.getValue());
            }
            jsonStream.endObject();
        }
        jsonStream.name("threads");
        jsonStream.beginArray();
        Iterator<T> it3 = this.threads.iterator();
        while (it3.hasNext()) {
            jsonStream.value((Thread) it3.next());
        }
        jsonStream.endArray();
        jsonStream.name("featureFlags").value(this.featureFlags);
        Session session = this.session;
        if (session != null) {
            Session copy = Session.copySession(session);
            jsonStream.name("session").beginObject();
            JsonStream jsonStreamName3 = jsonStream.name("id");
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(copy, "copy");
            jsonStreamName3.value(copy.getId());
            jsonStream.name("startedAt").value(copy.getStartedAt());
            jsonStream.name("events").beginObject();
            jsonStream.name("handled").value(copy.getHandledCount());
            jsonStream.name("unhandled").value(copy.getUnhandledCount());
            jsonStream.endObject();
            jsonStream.endObject();
        }
        jsonStream.endObject();
    }

    public final Set<ErrorType> getErrorTypesFromStackframes$bugsnag_android_core_release() {
        List<Error> list = this.errors;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ErrorType type = ((Error) it.next()).getType();
            if (type != null) {
                arrayList.add(type);
            }
        }
        Set set = CollectionsKt___CollectionsKt.toSet(arrayList);
        List<Error> list2 = this.errors;
        ArrayList<List> arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((Error) it2.next()).getStacktrace());
        }
        ArrayList arrayList3 = new ArrayList();
        for (List it3 : arrayList2) {
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            ArrayList arrayList4 = new ArrayList();
            Iterator it4 = it3.iterator();
            while (it4.hasNext()) {
                ErrorType type2 = ((Stackframe) it4.next()).getType();
                if (type2 != null) {
                    arrayList4.add(type2);
                }
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList3, arrayList4);
        }
        return SetsKt___SetsKt.plus(set, (Iterable) arrayList3);
    }

    public final void normalizeStackframeErrorTypes$bugsnag_android_core_release() {
        if (getErrorTypesFromStackframes$bugsnag_android_core_release().size() == 1) {
            List<Error> list = this.errors;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                List<Stackframe> stacktrace = ((Error) it.next()).getStacktrace();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(stacktrace, "it.stacktrace");
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, stacktrace);
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((Stackframe) it2.next()).setType(null);
            }
        }
    }

    public final void updateSeverityReasonInternal$bugsnag_android_core_release(SeverityReason severityReason) {
        this.severityReason = severityReason;
    }

    protected final void updateSeverityInternal(Severity severity) {
        this.severityReason = new SeverityReason(this.severityReason.getSeverityReasonType(), severity, this.severityReason.getUnhandled(), this.severityReason.getUnhandledOverridden(), this.severityReason.getAttributeValue(), this.severityReason.getAttributeKey());
    }

    public final String getSeverityReasonType() {
        String severityReasonType = this.severityReason.getSeverityReasonType();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(severityReasonType, "severityReason.severityReasonType");
        return severityReasonType;
    }

    public final TrimMetrics trimMetadataStringsTo(int maxLength) {
        TrimMetrics trimMetricsTrimMetadataStringsTo = this.metadata.trimMetadataStringsTo(maxLength);
        int itemsTrimmed = trimMetricsTrimMetadataStringsTo.getItemsTrimmed() + 0;
        int dataTrimmed = trimMetricsTrimMetadataStringsTo.getDataTrimmed() + 0;
        Iterator<Breadcrumb> it = this.breadcrumbs.iterator();
        while (it.hasNext()) {
            TrimMetrics trimMetricsTrimMetadataStringsTo$bugsnag_android_core_release = it.next().impl.trimMetadataStringsTo$bugsnag_android_core_release(maxLength);
            itemsTrimmed += trimMetricsTrimMetadataStringsTo$bugsnag_android_core_release.getItemsTrimmed();
            dataTrimmed += trimMetricsTrimMetadataStringsTo$bugsnag_android_core_release.getDataTrimmed();
        }
        return new TrimMetrics(itemsTrimmed, dataTrimmed);
    }

    public final TrimMetrics trimBreadcrumbsBy(int byteCount) {
        int length = 0;
        int i = 0;
        while (length < byteCount && (!this.breadcrumbs.isEmpty())) {
            length += JsonHelper.INSTANCE.serialize(this.breadcrumbs.remove(0)).length;
            i++;
        }
        if (i == 1) {
            this.breadcrumbs.add(new Breadcrumb("Removed to reduce payload size", this.logger));
        } else {
            List<Breadcrumb> list = this.breadcrumbs;
            StringBuilder sb = new StringBuilder();
            sb.append("Removed, along with ");
            sb.append(i - 1);
            sb.append(" older breadcrumbs, to reduce payload size");
            list.add(new Breadcrumb(sb.toString(), this.logger));
        }
        return new TrimMetrics(i, length);
    }

    public void setUser(String id, String email, String name) {
        this.userImpl = new User(id, email, name);
    }

    public void addMetadata(String section, Map<String, ? extends Object> value) {
        this.metadata.addMetadata(section, value);
    }

    public void addMetadata(String section, String key, Object value) {
        this.metadata.addMetadata(section, key, value);
    }

    public void addFeatureFlag(String name, String variant) {
        this.featureFlags.addFeatureFlag(name, variant);
    }
}
