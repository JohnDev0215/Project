package io.sentry;

import android.hardware.bydauto.TsNormalNum;
import h3.C1350a;
import io.sentry.compose.viewhierarchy.ComposeViewHierarchyExporter;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
public class F1 {
    static final EnumC1495p1 DEFAULT_DIAGNOSTIC_LEVEL = EnumC1495p1.DEBUG;
    private static final String DEFAULT_ENVIRONMENT = "production";
    public static final String DEFAULT_PROPAGATION_TARGETS = ".*";
    private boolean attachServerName;
    private boolean attachStacktrace;
    private boolean attachThreads;
    private io.sentry.backpressure.a backpressureMonitor;
    private InterfaceC1516u1 beforeBreadcrumb;
    private InterfaceC1519v1 beforeEmitMetricCallback;
    private InterfaceC1522w1 beforeEnvelopeCallback;
    private InterfaceC1525x1 beforeSend;
    private InterfaceC1528y1 beforeSendReplay;
    private InterfaceC1531z1 beforeSendTransaction;
    private final Set<String> bundleIds;
    private String cacheDirPath;
    io.sentry.clientreport.f clientReportRecorder;
    private H connectionStatusProvider;
    private int connectionTimeoutMillis;
    private final List<String> contextTags;
    private A1 cron;
    private final io.sentry.util.d dateProvider;
    private boolean debug;
    private io.sentry.internal.debugmeta.a debugMetaLoader;
    private final List<String> defaultTracePropagationTargets;
    private EnumC1495p1 diagnosticLevel;
    private String dist;
    private String distinctId;
    private String dsn;
    private String dsnHash;
    private boolean enableAppStartProfiling;
    private boolean enableAutoSessionTracking;
    private boolean enableBackpressureHandling;
    private boolean enableDeduplication;
    private boolean enableDefaultTagsForMetrics;
    private boolean enableExternalConfiguration;
    private boolean enableMetrics;
    private boolean enablePrettySerializationOutput;
    private boolean enableScopePersistence;
    private boolean enableScreenTracking;
    private boolean enableShutdownHook;
    private boolean enableSpanLocalMetricAggregation;
    private boolean enableSpotlight;
    private boolean enableTimeToFullDisplayTracing;
    private Boolean enableTracing;
    private boolean enableUncaughtExceptionHandler;
    private boolean enableUserInteractionBreadcrumbs;
    private boolean enableUserInteractionTracing;
    private boolean enabled;
    private io.sentry.cache.d envelopeDiskCache;
    private final io.sentry.util.d envelopeReader;
    private String environment;
    private final List<InterfaceC1511t> eventProcessors;
    private S executorService;
    private final C1514u experimental;
    private long flushTimeoutMillis;
    private C1520w fullyDisplayedReporter;
    private final List<io.sentry.internal.gestures.a> gestureTargetLocators;
    private Long idleTimeout;
    private List<String> ignoredCheckIns;
    private final Set<Class<? extends Throwable>> ignoredExceptionsForType;
    private final List<String> inAppExcludes;
    private final List<String> inAppIncludes;
    private Y instrumenter;
    private final List<Z> integrations;
    private K logger;
    private io.sentry.util.thread.a mainThreadChecker;
    private long maxAttachmentSize;
    private int maxBreadcrumbs;
    private int maxCacheItems;
    private int maxDepth;
    private int maxQueueSize;
    private D1 maxRequestBodySize;
    private int maxSpans;
    private long maxTraceFileSize;
    private io.sentry.internal.modules.a modulesLoader;
    private final List<Q> observers;
    private final List<M> optionsObservers;
    private final io.sentry.util.d parsedDsn;
    private final List<N> performanceCollectors;
    private boolean printUncaughtStackTrace;
    private Double profilesSampleRate;
    private B1 profilesSampler;
    private int profilingTracesHz;
    private String proguardUuid;
    private C1 proxy;
    private int readTimeoutMillis;
    private String release;
    private J0 replayController;
    private Double sampleRate;
    private io.sentry.protocol.r sdkVersion;
    private boolean sendClientReports;
    private boolean sendDefaultPii;
    private boolean sendModules;
    private String sentryClientName;
    private final io.sentry.util.d serializer;
    private String serverName;
    private long sessionFlushTimeoutMillis;
    private long sessionTrackingIntervalMillis;
    private long shutdownTimeoutMillis;
    private String spotlightConnectionUrl;
    private SSLSocketFactory sslSocketFactory;
    private final Map<String, String> tags;
    private boolean traceOptionsRequests;
    private List<String> tracePropagationTargets;
    private boolean traceSampling;
    private Double tracesSampleRate;
    private E1 tracesSampler;
    private d2 transactionPerformanceCollector;
    private W transactionProfiler;
    private X transportFactory;
    private io.sentry.transport.g transportGate;
    private final List<ComposeViewHierarchyExporter> viewHierarchyExporters;

    public F1(boolean z6) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.eventProcessors = copyOnWriteArrayList;
        this.ignoredExceptionsForType = new CopyOnWriteArraySet();
        CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
        this.integrations = copyOnWriteArrayList2;
        this.bundleIds = new CopyOnWriteArraySet();
        final int i7 = 0;
        this.parsedDsn = new io.sentry.util.d(new io.sentry.util.c(this) { // from class: io.sentry.t1

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ F1 f16886m;

            {
                this.f16886m = this;
            }

            @Override // io.sentry.util.c
            public final Object g() {
                int i10 = i7;
                F1 f12 = this.f16886m;
                switch (i10) {
                    case 0:
                        return F1.a(f12);
                    case 1:
                        f12.getClass();
                        return new C1488n0(f12);
                    default:
                        return F1.b(f12);
                }
            }
        });
        this.shutdownTimeoutMillis = 2000L;
        this.flushTimeoutMillis = 15000L;
        this.sessionFlushTimeoutMillis = 15000L;
        this.logger = C1521w0.l;
        this.diagnosticLevel = DEFAULT_DIAGNOSTIC_LEVEL;
        final int i10 = 1;
        this.serializer = new io.sentry.util.d(new io.sentry.util.c(this) { // from class: io.sentry.t1

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ F1 f16886m;

            {
                this.f16886m = this;
            }

            @Override // io.sentry.util.c
            public final Object g() {
                int i102 = i10;
                F1 f12 = this.f16886m;
                switch (i102) {
                    case 0:
                        return F1.a(f12);
                    case 1:
                        f12.getClass();
                        return new C1488n0(f12);
                    default:
                        return F1.b(f12);
                }
            }
        });
        final int i11 = 2;
        this.envelopeReader = new io.sentry.util.d(new io.sentry.util.c(this) { // from class: io.sentry.t1

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ F1 f16886m;

            {
                this.f16886m = this;
            }

            @Override // io.sentry.util.c
            public final Object g() {
                int i102 = i11;
                F1 f12 = this.f16886m;
                switch (i102) {
                    case 0:
                        return F1.a(f12);
                    case 1:
                        f12.getClass();
                        return new C1488n0(f12);
                    default:
                        return F1.b(f12);
                }
            }
        });
        this.maxDepth = 100;
        this.maxCacheItems = 30;
        this.maxQueueSize = 30;
        this.maxBreadcrumbs = 100;
        this.inAppExcludes = new CopyOnWriteArrayList();
        this.inAppIncludes = new CopyOnWriteArrayList();
        this.transportFactory = B0.l;
        this.transportGate = io.sentry.transport.d.f16898b;
        this.attachStacktrace = true;
        this.enableAutoSessionTracking = true;
        this.sessionTrackingIntervalMillis = 30000L;
        this.attachServerName = true;
        this.enableUncaughtExceptionHandler = true;
        this.printUncaughtStackTrace = false;
        this.executorService = C1524x0.o;
        this.connectionTimeoutMillis = TsNormalNum.THOUSAND_FIVE;
        this.readTimeoutMillis = TsNormalNum.THOUSAND_FIVE;
        this.envelopeDiskCache = io.sentry.transport.h.l;
        this.sendDefaultPii = false;
        this.observers = new CopyOnWriteArrayList();
        this.optionsObservers = new CopyOnWriteArrayList();
        this.tags = new ConcurrentHashMap();
        this.maxAttachmentSize = 20971520L;
        this.enableDeduplication = true;
        this.maxSpans = 1000;
        this.enableShutdownHook = true;
        this.maxRequestBodySize = D1.NONE;
        this.traceSampling = true;
        this.maxTraceFileSize = 5242880L;
        this.transactionProfiler = C1524x0.f16989q;
        this.tracePropagationTargets = null;
        this.defaultTracePropagationTargets = Collections.singletonList(DEFAULT_PROPAGATION_TARGETS);
        this.idleTimeout = 3000L;
        this.contextTags = new CopyOnWriteArrayList();
        this.sendClientReports = true;
        this.clientReportRecorder = new M.t(this);
        this.modulesLoader = io.sentry.internal.modules.e.f16580a;
        this.debugMetaLoader = io.sentry.internal.debugmeta.b.l;
        this.enableUserInteractionTracing = false;
        this.enableUserInteractionBreadcrumbs = true;
        this.instrumenter = Y.SENTRY;
        this.gestureTargetLocators = new ArrayList();
        this.viewHierarchyExporters = new ArrayList();
        this.mainThreadChecker = io.sentry.util.thread.c.f16930a;
        this.traceOptionsRequests = true;
        this.dateProvider = new io.sentry.util.d(new C1350a(8));
        this.performanceCollectors = new ArrayList();
        this.transactionPerformanceCollector = C1524x0.f16988p;
        this.enableTimeToFullDisplayTracing = false;
        this.fullyDisplayedReporter = C1520w.f16979b;
        this.connectionStatusProvider = new C1512t0();
        this.enabled = true;
        this.enablePrettySerializationOutput = true;
        this.sendModules = true;
        this.enableSpotlight = false;
        this.enableScopePersistence = true;
        this.ignoredCheckIns = null;
        this.backpressureMonitor = io.sentry.backpressure.b.l;
        this.enableBackpressureHandling = true;
        this.enableAppStartProfiling = false;
        this.enableMetrics = false;
        this.enableDefaultTagsForMetrics = true;
        this.enableSpanLocalMetricAggregation = true;
        this.profilingTracesHz = 101;
        this.cron = null;
        this.replayController = C1524x0.f16987n;
        this.enableScreenTracking = true;
        C1514u c1514u = new C1514u();
        J1 j12 = new J1();
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        j12.c = copyOnWriteArraySet;
        j12.d = new CopyOnWriteArraySet();
        j12.f15882e = I1.MEDIUM;
        j12.f15883f = 1;
        j12.f15884g = 30000L;
        j12.f15885h = 5000L;
        j12.f15886i = 3600000L;
        if (!z6) {
            j12.c.add("android.widget.TextView");
            j12.d.remove("android.widget.TextView");
            j12.c.add("android.widget.ImageView");
            j12.d.remove("android.widget.ImageView");
            copyOnWriteArraySet.add("android.webkit.WebView");
            copyOnWriteArraySet.add("android.widget.VideoView");
            copyOnWriteArraySet.add("androidx.media3.ui.PlayerView");
            copyOnWriteArraySet.add("com.google.android.exoplayer2.ui.PlayerView");
            copyOnWriteArraySet.add("com.google.android.exoplayer2.ui.StyledPlayerView");
        }
        c1514u.f16913a = j12;
        this.experimental = c1514u;
        if (z6) {
            return;
        }
        this.executorService = new e7.f(13, (byte) 0);
        copyOnWriteArrayList2.add(new UncaughtExceptionHandlerIntegration());
        copyOnWriteArrayList2.add(new ShutdownHookIntegration());
        copyOnWriteArrayList2.add(new SpotlightIntegration());
        copyOnWriteArrayList.add(new C1491o0(this));
        copyOnWriteArrayList.add(new C1472i(this, 1));
        if (!io.sentry.util.f.f16921a) {
            copyOnWriteArrayList.add(new K1());
        }
        setSentryClientName("sentry.java/7.18.0");
        io.sentry.protocol.r rVar = new io.sentry.protocol.r("sentry.java", "7.18.0");
        rVar.f16766m = "7.18.0";
        setSdkVersion(rVar);
        C1489n1.s().g("maven:io.sentry:sentry");
    }

    public static /* synthetic */ C1503q a(F1 f12) {
        return new C1503q(f12.dsn);
    }

    public static /* synthetic */ r b(F1 f12) {
        f12.getClass();
        return new r((T) f12.serializer.a());
    }

    public static F1 empty() {
        return new F1(true);
    }

    public void addBundleId(String str) {
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            this.bundleIds.add(strTrim);
        }
    }

    public void addContextTag(String str) {
        this.contextTags.add(str);
    }

    public void addEventProcessor(InterfaceC1511t interfaceC1511t) {
        this.eventProcessors.add(interfaceC1511t);
    }

    public void addIgnoredExceptionForType(Class<? extends Throwable> cls) {
        this.ignoredExceptionsForType.add(cls);
    }

    public void addInAppExclude(String str) {
        this.inAppExcludes.add(str);
    }

    public void addInAppInclude(String str) {
        this.inAppIncludes.add(str);
    }

    public void addIntegration(Z z6) {
        this.integrations.add(z6);
    }

    public void addOptionsObserver(M m10) {
        this.optionsObservers.add(m10);
    }

    public void addPerformanceCollector(N n10) {
        this.performanceCollectors.add(n10);
    }

    public void addScopeObserver(Q q4) {
        this.observers.add(q4);
    }

    @Deprecated
    public void addTracingOrigin(String str) {
        if (this.tracePropagationTargets == null) {
            this.tracePropagationTargets = new CopyOnWriteArrayList();
        }
        if (str.isEmpty()) {
            return;
        }
        this.tracePropagationTargets.add(str);
    }

    public boolean containsIgnoredExceptionForType(Throwable th) {
        return this.ignoredExceptionsForType.contains(th.getClass());
    }

    public io.sentry.backpressure.a getBackpressureMonitor() {
        return this.backpressureMonitor;
    }

    public InterfaceC1516u1 getBeforeBreadcrumb() {
        return null;
    }

    public InterfaceC1519v1 getBeforeEmitMetricCallback() {
        return null;
    }

    public InterfaceC1522w1 getBeforeEnvelopeCallback() {
        return this.beforeEnvelopeCallback;
    }

    public InterfaceC1525x1 getBeforeSend() {
        return null;
    }

    public InterfaceC1528y1 getBeforeSendReplay() {
        return null;
    }

    public InterfaceC1531z1 getBeforeSendTransaction() {
        return null;
    }

    public Set<String> getBundleIds() {
        return this.bundleIds;
    }

    public String getCacheDirPath() {
        String str = this.cacheDirPath;
        if (str == null || str.isEmpty()) {
            return null;
        }
        return this.dsnHash != null ? new File(this.cacheDirPath, this.dsnHash).getAbsolutePath() : this.cacheDirPath;
    }

    public String getCacheDirPathWithoutDsn() {
        String str = this.cacheDirPath;
        if (str == null || str.isEmpty()) {
            return null;
        }
        return this.cacheDirPath;
    }

    public io.sentry.clientreport.f getClientReportRecorder() {
        return this.clientReportRecorder;
    }

    public H getConnectionStatusProvider() {
        return this.connectionStatusProvider;
    }

    public int getConnectionTimeoutMillis() {
        return this.connectionTimeoutMillis;
    }

    public List<String> getContextTags() {
        return this.contextTags;
    }

    public A1 getCron() {
        return this.cron;
    }

    public InterfaceC1456c1 getDateProvider() {
        return (InterfaceC1456c1) this.dateProvider.a();
    }

    public io.sentry.internal.debugmeta.a getDebugMetaLoader() {
        return this.debugMetaLoader;
    }

    public EnumC1495p1 getDiagnosticLevel() {
        return this.diagnosticLevel;
    }

    public String getDist() {
        return this.dist;
    }

    public String getDistinctId() {
        return this.distinctId;
    }

    public String getDsn() {
        return this.dsn;
    }

    @Deprecated
    public Boolean getEnableTracing() {
        return this.enableTracing;
    }

    public io.sentry.cache.d getEnvelopeDiskCache() {
        return this.envelopeDiskCache;
    }

    public I getEnvelopeReader() {
        return (I) this.envelopeReader.a();
    }

    public String getEnvironment() {
        String str = this.environment;
        return str != null ? str : DEFAULT_ENVIRONMENT;
    }

    public List<InterfaceC1511t> getEventProcessors() {
        return this.eventProcessors;
    }

    public S getExecutorService() {
        return this.executorService;
    }

    public C1514u getExperimental() {
        return this.experimental;
    }

    public long getFlushTimeoutMillis() {
        return this.flushTimeoutMillis;
    }

    public C1520w getFullyDisplayedReporter() {
        return this.fullyDisplayedReporter;
    }

    public List<io.sentry.internal.gestures.a> getGestureTargetLocators() {
        return this.gestureTargetLocators;
    }

    public Long getIdleTimeout() {
        return this.idleTimeout;
    }

    public List<String> getIgnoredCheckIns() {
        return this.ignoredCheckIns;
    }

    public Set<Class<? extends Throwable>> getIgnoredExceptionsForType() {
        return this.ignoredExceptionsForType;
    }

    public List<String> getInAppExcludes() {
        return this.inAppExcludes;
    }

    public List<String> getInAppIncludes() {
        return this.inAppIncludes;
    }

    public Y getInstrumenter() {
        return this.instrumenter;
    }

    public List<Z> getIntegrations() {
        return this.integrations;
    }

    public K getLogger() {
        return this.logger;
    }

    public io.sentry.util.thread.a getMainThreadChecker() {
        return this.mainThreadChecker;
    }

    public long getMaxAttachmentSize() {
        return this.maxAttachmentSize;
    }

    public int getMaxBreadcrumbs() {
        return this.maxBreadcrumbs;
    }

    public int getMaxCacheItems() {
        return this.maxCacheItems;
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }

    public int getMaxQueueSize() {
        return this.maxQueueSize;
    }

    public D1 getMaxRequestBodySize() {
        return this.maxRequestBodySize;
    }

    public int getMaxSpans() {
        return this.maxSpans;
    }

    public long getMaxTraceFileSize() {
        return this.maxTraceFileSize;
    }

    public io.sentry.internal.modules.a getModulesLoader() {
        return this.modulesLoader;
    }

    public List<M> getOptionsObservers() {
        return this.optionsObservers;
    }

    public String getOutboxPath() {
        String cacheDirPath = getCacheDirPath();
        if (cacheDirPath == null) {
            return null;
        }
        return new File(cacheDirPath, "outbox").getAbsolutePath();
    }

    public List<N> getPerformanceCollectors() {
        return this.performanceCollectors;
    }

    public Double getProfilesSampleRate() {
        return this.profilesSampleRate;
    }

    public B1 getProfilesSampler() {
        return null;
    }

    public String getProfilingTracesDirPath() {
        String cacheDirPath = getCacheDirPath();
        if (cacheDirPath == null) {
            return null;
        }
        return new File(cacheDirPath, "profiling_traces").getAbsolutePath();
    }

    public int getProfilingTracesHz() {
        return this.profilingTracesHz;
    }

    public String getProguardUuid() {
        return this.proguardUuid;
    }

    public C1 getProxy() {
        return this.proxy;
    }

    public int getReadTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    public String getRelease() {
        return this.release;
    }

    public J0 getReplayController() {
        return this.replayController;
    }

    public Double getSampleRate() {
        return this.sampleRate;
    }

    public List<Q> getScopeObservers() {
        return this.observers;
    }

    public io.sentry.protocol.r getSdkVersion() {
        return this.sdkVersion;
    }

    public String getSentryClientName() {
        return this.sentryClientName;
    }

    public T getSerializer() {
        return (T) this.serializer.a();
    }

    public String getServerName() {
        return this.serverName;
    }

    public long getSessionFlushTimeoutMillis() {
        return this.sessionFlushTimeoutMillis;
    }

    public long getSessionTrackingIntervalMillis() {
        return this.sessionTrackingIntervalMillis;
    }

    @Deprecated
    public long getShutdownTimeout() {
        return this.shutdownTimeoutMillis;
    }

    public long getShutdownTimeoutMillis() {
        return this.shutdownTimeoutMillis;
    }

    public String getSpotlightConnectionUrl() {
        return this.spotlightConnectionUrl;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public List<String> getTracePropagationTargets() {
        List<String> list = this.tracePropagationTargets;
        return list == null ? this.defaultTracePropagationTargets : list;
    }

    public Double getTracesSampleRate() {
        return this.tracesSampleRate;
    }

    public E1 getTracesSampler() {
        return null;
    }

    @Deprecated
    public List<String> getTracingOrigins() {
        return getTracePropagationTargets();
    }

    public d2 getTransactionPerformanceCollector() {
        return this.transactionPerformanceCollector;
    }

    public W getTransactionProfiler() {
        return this.transactionProfiler;
    }

    public X getTransportFactory() {
        return this.transportFactory;
    }

    public io.sentry.transport.g getTransportGate() {
        return this.transportGate;
    }

    public final List<ComposeViewHierarchyExporter> getViewHierarchyExporters() {
        return this.viewHierarchyExporters;
    }

    public boolean isAttachServerName() {
        return this.attachServerName;
    }

    public boolean isAttachStacktrace() {
        return this.attachStacktrace;
    }

    public boolean isAttachThreads() {
        return this.attachThreads;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public boolean isEnableAppStartProfiling() {
        return isProfilingEnabled() && this.enableAppStartProfiling;
    }

    public boolean isEnableAutoSessionTracking() {
        return this.enableAutoSessionTracking;
    }

    public boolean isEnableBackpressureHandling() {
        return this.enableBackpressureHandling;
    }

    public boolean isEnableDeduplication() {
        return this.enableDeduplication;
    }

    public boolean isEnableDefaultTagsForMetrics() {
        return isEnableMetrics() && this.enableDefaultTagsForMetrics;
    }

    public boolean isEnableExternalConfiguration() {
        return this.enableExternalConfiguration;
    }

    public boolean isEnableMetrics() {
        return this.enableMetrics;
    }

    public boolean isEnablePrettySerializationOutput() {
        return this.enablePrettySerializationOutput;
    }

    public boolean isEnableScopePersistence() {
        return this.enableScopePersistence;
    }

    public boolean isEnableScreenTracking() {
        return this.enableScreenTracking;
    }

    public boolean isEnableShutdownHook() {
        return this.enableShutdownHook;
    }

    public boolean isEnableSpanLocalMetricAggregation() {
        return isEnableMetrics() && this.enableSpanLocalMetricAggregation;
    }

    public boolean isEnableSpotlight() {
        return this.enableSpotlight;
    }

    public boolean isEnableTimeToFullDisplayTracing() {
        return this.enableTimeToFullDisplayTracing;
    }

    public boolean isEnableUncaughtExceptionHandler() {
        return this.enableUncaughtExceptionHandler;
    }

    public boolean isEnableUserInteractionBreadcrumbs() {
        return this.enableUserInteractionBreadcrumbs;
    }

    public boolean isEnableUserInteractionTracing() {
        return this.enableUserInteractionTracing;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isPrintUncaughtStackTrace() {
        return this.printUncaughtStackTrace;
    }

    public boolean isProfilingEnabled() {
        if (getProfilesSampleRate() != null && getProfilesSampleRate().doubleValue() > 0.0d) {
            return true;
        }
        getProfilesSampler();
        return false;
    }

    public boolean isSendClientReports() {
        return this.sendClientReports;
    }

    public boolean isSendDefaultPii() {
        return this.sendDefaultPii;
    }

    public boolean isSendModules() {
        return this.sendModules;
    }

    public boolean isTraceOptionsRequests() {
        return this.traceOptionsRequests;
    }

    public boolean isTraceSampling() {
        return this.traceSampling;
    }

    public boolean isTracingEnabled() {
        Boolean bool = this.enableTracing;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (getTracesSampleRate() != null) {
            return true;
        }
        getTracesSampler();
        return false;
    }

    public void loadLazyFields() {
        getSerializer();
        retrieveParsedDsn();
        getEnvelopeReader();
        getDateProvider();
    }

    public void merge(C1517v c1517v) {
        String str = c1517v.f16933a;
        if (str != null) {
            setDsn(str);
        }
        String str2 = c1517v.f16934b;
        if (str2 != null) {
            setEnvironment(str2);
        }
        String str3 = c1517v.c;
        if (str3 != null) {
            setRelease(str3);
        }
        String str4 = c1517v.d;
        if (str4 != null) {
            setDist(str4);
        }
        String str5 = c1517v.f16935e;
        if (str5 != null) {
            setServerName(str5);
        }
        C1 c1 = c1517v.f16942m;
        if (c1 != null) {
            setProxy(c1);
        }
        Boolean bool = c1517v.f16936f;
        if (bool != null) {
            setEnableUncaughtExceptionHandler(bool.booleanValue());
        }
        Boolean bool2 = c1517v.f16949u;
        if (bool2 != null) {
            setPrintUncaughtStackTrace(bool2.booleanValue());
        }
        Boolean bool3 = c1517v.f16939i;
        if (bool3 != null) {
            setEnableTracing(bool3);
        }
        Double d = c1517v.f16940j;
        if (d != null) {
            setTracesSampleRate(d);
        }
        Double d10 = c1517v.f16941k;
        if (d10 != null) {
            setProfilesSampleRate(d10);
        }
        Boolean bool4 = c1517v.f16937g;
        if (bool4 != null) {
            setDebug(bool4.booleanValue());
        }
        Boolean bool5 = c1517v.f16938h;
        if (bool5 != null) {
            setEnableDeduplication(bool5.booleanValue());
        }
        Boolean bool6 = c1517v.f16950v;
        if (bool6 != null) {
            setSendClientReports(bool6.booleanValue());
        }
        for (Map.Entry entry : new HashMap(c1517v.l).entrySet()) {
            this.tags.put((String) entry.getKey(), (String) entry.getValue());
        }
        Iterator it = new ArrayList(c1517v.o).iterator();
        while (it.hasNext()) {
            addInAppInclude((String) it.next());
        }
        Iterator it2 = new ArrayList(c1517v.f16943n).iterator();
        while (it2.hasNext()) {
            addInAppExclude((String) it2.next());
        }
        Iterator it3 = new HashSet(c1517v.f16948t).iterator();
        while (it3.hasNext()) {
            addIgnoredExceptionForType((Class) it3.next());
        }
        if (c1517v.f16944p != null) {
            setTracePropagationTargets(new ArrayList(c1517v.f16944p));
        }
        Iterator it4 = new ArrayList(c1517v.f16945q).iterator();
        while (it4.hasNext()) {
            addContextTag((String) it4.next());
        }
        String str6 = c1517v.f16946r;
        if (str6 != null) {
            setProguardUuid(str6);
        }
        Long l = c1517v.f16947s;
        if (l != null) {
            setIdleTimeout(l);
        }
        Iterator it5 = c1517v.f16951w.iterator();
        while (it5.hasNext()) {
            addBundleId((String) it5.next());
        }
        Boolean bool7 = c1517v.f16952x;
        if (bool7 != null) {
            setEnabled(bool7.booleanValue());
        }
        Boolean bool8 = c1517v.f16953y;
        if (bool8 != null) {
            setEnablePrettySerializationOutput(bool8.booleanValue());
        }
        Boolean bool9 = c1517v.f16931A;
        if (bool9 != null) {
            setSendModules(bool9.booleanValue());
        }
        if (c1517v.f16954z != null) {
            setIgnoredCheckIns(new ArrayList(c1517v.f16954z));
        }
        Boolean bool10 = c1517v.B;
        if (bool10 != null) {
            setEnableBackpressureHandling(bool10.booleanValue());
        }
        if (c1517v.f16932C != null) {
            if (getCron() == null) {
                setCron(c1517v.f16932C);
                return;
            }
            if (c1517v.f16932C.f15813a != null) {
                getCron().f15813a = c1517v.f16932C.f15813a;
            }
            if (c1517v.f16932C.f15814b != null) {
                getCron().f15814b = c1517v.f16932C.f15814b;
            }
            if (c1517v.f16932C.c != null) {
                getCron().c = c1517v.f16932C.c;
            }
            if (c1517v.f16932C.d != null) {
                getCron().d = c1517v.f16932C.d;
            }
            if (c1517v.f16932C.f15815e != null) {
                getCron().f15815e = c1517v.f16932C.f15815e;
            }
        }
    }

    public C1503q retrieveParsedDsn() {
        return (C1503q) this.parsedDsn.a();
    }

    public void setAttachServerName(boolean z6) {
        this.attachServerName = z6;
    }

    public void setAttachStacktrace(boolean z6) {
        this.attachStacktrace = z6;
    }

    public void setAttachThreads(boolean z6) {
        this.attachThreads = z6;
    }

    public void setBackpressureMonitor(io.sentry.backpressure.a aVar) {
        this.backpressureMonitor = aVar;
    }

    public void setBeforeBreadcrumb(InterfaceC1516u1 interfaceC1516u1) {
    }

    public void setBeforeEmitMetricCallback(InterfaceC1519v1 interfaceC1519v1) {
    }

    public void setBeforeEnvelopeCallback(InterfaceC1522w1 interfaceC1522w1) {
        this.beforeEnvelopeCallback = interfaceC1522w1;
    }

    public void setBeforeSend(InterfaceC1525x1 interfaceC1525x1) {
    }

    public void setBeforeSendReplay(InterfaceC1528y1 interfaceC1528y1) {
    }

    public void setBeforeSendTransaction(InterfaceC1531z1 interfaceC1531z1) {
    }

    public void setCacheDirPath(String str) {
        this.cacheDirPath = str;
    }

    public void setConnectionStatusProvider(H h10) {
        this.connectionStatusProvider = h10;
    }

    public void setConnectionTimeoutMillis(int i7) {
        this.connectionTimeoutMillis = i7;
    }

    public void setCron(A1 a12) {
        this.cron = a12;
    }

    public void setDateProvider(InterfaceC1456c1 interfaceC1456c1) {
        io.sentry.util.d dVar = this.dateProvider;
        synchronized (dVar) {
            dVar.f16917a = interfaceC1456c1;
        }
    }

    public void setDebug(boolean z6) {
        this.debug = z6;
    }

    public void setDebugMetaLoader(io.sentry.internal.debugmeta.a aVar) {
        if (aVar == null) {
            aVar = io.sentry.internal.debugmeta.b.l;
        }
        this.debugMetaLoader = aVar;
    }

    public void setDiagnosticLevel(EnumC1495p1 enumC1495p1) {
        if (enumC1495p1 == null) {
            enumC1495p1 = DEFAULT_DIAGNOSTIC_LEVEL;
        }
        this.diagnosticLevel = enumC1495p1;
    }

    public void setDist(String str) {
        this.dist = str;
    }

    public void setDistinctId(String str) {
        this.distinctId = str;
    }

    public void setDsn(String str) {
        String string;
        this.dsn = str;
        io.sentry.util.d dVar = this.parsedDsn;
        synchronized (dVar) {
            string = null;
            dVar.f16917a = null;
        }
        String str2 = this.dsn;
        K k10 = this.logger;
        Charset charset = io.sentry.util.i.f16925a;
        if (str2 != null && !str2.isEmpty()) {
            try {
                string = new StringBuilder(new BigInteger(1, MessageDigest.getInstance("SHA-1").digest(str2.getBytes(io.sentry.util.i.f16925a))).toString(16)).toString();
            } catch (NoSuchAlgorithmException e10) {
                k10.s(EnumC1495p1.INFO, "SHA-1 isn't available to calculate the hash.", e10);
            } catch (Throwable th) {
                k10.k(EnumC1495p1.INFO, "string: %s could not calculate its hash", th, str2);
            }
        }
        this.dsnHash = string;
    }

    public void setEnableAppStartProfiling(boolean z6) {
        this.enableAppStartProfiling = z6;
    }

    public void setEnableAutoSessionTracking(boolean z6) {
        this.enableAutoSessionTracking = z6;
    }

    public void setEnableBackpressureHandling(boolean z6) {
        this.enableBackpressureHandling = z6;
    }

    public void setEnableDeduplication(boolean z6) {
        this.enableDeduplication = z6;
    }

    public void setEnableDefaultTagsForMetrics(boolean z6) {
        this.enableDefaultTagsForMetrics = z6;
    }

    public void setEnableExternalConfiguration(boolean z6) {
        this.enableExternalConfiguration = z6;
    }

    public void setEnableMetrics(boolean z6) {
        this.enableMetrics = z6;
    }

    public void setEnablePrettySerializationOutput(boolean z6) {
        this.enablePrettySerializationOutput = z6;
    }

    public void setEnableScopePersistence(boolean z6) {
        this.enableScopePersistence = z6;
    }

    public void setEnableScreenTracking(boolean z6) {
        this.enableScreenTracking = z6;
    }

    public void setEnableShutdownHook(boolean z6) {
        this.enableShutdownHook = z6;
    }

    public void setEnableSpanLocalMetricAggregation(boolean z6) {
        this.enableSpanLocalMetricAggregation = z6;
    }

    public void setEnableSpotlight(boolean z6) {
        this.enableSpotlight = z6;
    }

    public void setEnableTimeToFullDisplayTracing(boolean z6) {
        this.enableTimeToFullDisplayTracing = z6;
    }

    @Deprecated
    public void setEnableTracing(Boolean bool) {
        this.enableTracing = bool;
    }

    public void setEnableUncaughtExceptionHandler(boolean z6) {
        this.enableUncaughtExceptionHandler = z6;
    }

    public void setEnableUserInteractionBreadcrumbs(boolean z6) {
        this.enableUserInteractionBreadcrumbs = z6;
    }

    public void setEnableUserInteractionTracing(boolean z6) {
        this.enableUserInteractionTracing = z6;
    }

    public void setEnabled(boolean z6) {
        this.enabled = z6;
    }

    public void setEnvelopeDiskCache(io.sentry.cache.d dVar) {
        if (dVar == null) {
            dVar = io.sentry.transport.h.l;
        }
        this.envelopeDiskCache = dVar;
    }

    public void setEnvelopeReader(I i7) {
        io.sentry.util.d dVar = this.envelopeReader;
        if (i7 == null) {
            i7 = C1515u0.f16914a;
        }
        synchronized (dVar) {
            dVar.f16917a = i7;
        }
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public void setExecutorService(S s6) {
        if (s6 != null) {
            this.executorService = s6;
        }
    }

    public void setFlushTimeoutMillis(long j3) {
        this.flushTimeoutMillis = j3;
    }

    public void setFullyDisplayedReporter(C1520w c1520w) {
        this.fullyDisplayedReporter = c1520w;
    }

    public void setGestureTargetLocators(List<io.sentry.internal.gestures.a> list) {
        this.gestureTargetLocators.clear();
        this.gestureTargetLocators.addAll(list);
    }

    public void setIdleTimeout(Long l) {
        this.idleTimeout = l;
    }

    public void setIgnoredCheckIns(List<String> list) {
        if (list == null) {
            this.ignoredCheckIns = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.isEmpty()) {
                arrayList.add(str);
            }
        }
        this.ignoredCheckIns = arrayList;
    }

    public void setInstrumenter(Y y10) {
        this.instrumenter = y10;
    }

    public void setLogger(K k10) {
        K k11;
        if (k10 == null) {
            k11 = C1521w0.l;
        } else {
            M.t tVar = new M.t();
            h6.e.q(this, "SentryOptions is required.");
            tVar.l = this;
            tVar.f4151m = k10;
            k11 = tVar;
        }
        this.logger = k11;
    }

    public void setMainThreadChecker(io.sentry.util.thread.a aVar) {
        this.mainThreadChecker = aVar;
    }

    public void setMaxAttachmentSize(long j3) {
        this.maxAttachmentSize = j3;
    }

    public void setMaxBreadcrumbs(int i7) {
        this.maxBreadcrumbs = i7;
    }

    public void setMaxCacheItems(int i7) {
        this.maxCacheItems = i7;
    }

    public void setMaxDepth(int i7) {
        this.maxDepth = i7;
    }

    public void setMaxQueueSize(int i7) {
        if (i7 > 0) {
            this.maxQueueSize = i7;
        }
    }

    public void setMaxRequestBodySize(D1 d12) {
        this.maxRequestBodySize = d12;
    }

    public void setMaxSpans(int i7) {
        this.maxSpans = i7;
    }

    public void setMaxTraceFileSize(long j3) {
        this.maxTraceFileSize = j3;
    }

    public void setModulesLoader(io.sentry.internal.modules.a aVar) {
        if (aVar == null) {
            aVar = io.sentry.internal.modules.e.f16580a;
        }
        this.modulesLoader = aVar;
    }

    public void setPrintUncaughtStackTrace(boolean z6) {
        this.printUncaughtStackTrace = z6;
    }

    public void setProfilesSampleRate(Double d) {
        if (h6.i.w(d, true)) {
            this.profilesSampleRate = d;
            return;
        }
        throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values between 0.0 and 1.0.");
    }

    public void setProfilesSampler(B1 b12) {
    }

    @Deprecated
    public void setProfilingEnabled(boolean z6) {
        if (getProfilesSampleRate() == null) {
            setProfilesSampleRate(z6 ? Double.valueOf(1.0d) : null);
        }
    }

    public void setProfilingTracesHz(int i7) {
        this.profilingTracesHz = i7;
    }

    public void setProguardUuid(String str) {
        this.proguardUuid = str;
    }

    public void setProxy(C1 c1) {
        this.proxy = c1;
    }

    public void setReadTimeoutMillis(int i7) {
        this.readTimeoutMillis = i7;
    }

    public void setRelease(String str) {
        this.release = str;
    }

    public void setReplayController(J0 j02) {
        if (j02 == null) {
            j02 = C1524x0.f16987n;
        }
        this.replayController = j02;
    }

    public void setSampleRate(Double d) {
        if (h6.i.w(d, true)) {
            this.sampleRate = d;
            return;
        }
        throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
    }

    public void setSdkVersion(io.sentry.protocol.r rVar) {
        this.sdkVersion = rVar;
    }

    public void setSendClientReports(boolean z6) {
        this.sendClientReports = z6;
        if (z6) {
            this.clientReportRecorder = new M.t(this);
        } else {
            this.clientReportRecorder = new Y4.e(24);
        }
    }

    public void setSendDefaultPii(boolean z6) {
        this.sendDefaultPii = z6;
    }

    public void setSendModules(boolean z6) {
        this.sendModules = z6;
    }

    public void setSentryClientName(String str) {
        this.sentryClientName = str;
    }

    public void setSerializer(T t10) {
        io.sentry.util.d dVar = this.serializer;
        if (t10 == null) {
            t10 = C1527y0.f16991a;
        }
        synchronized (dVar) {
            dVar.f16917a = t10;
        }
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public void setSessionFlushTimeoutMillis(long j3) {
        this.sessionFlushTimeoutMillis = j3;
    }

    public void setSessionTrackingIntervalMillis(long j3) {
        this.sessionTrackingIntervalMillis = j3;
    }

    @Deprecated
    public void setShutdownTimeout(long j3) {
        this.shutdownTimeoutMillis = j3;
    }

    public void setShutdownTimeoutMillis(long j3) {
        this.shutdownTimeoutMillis = j3;
    }

    public void setSpotlightConnectionUrl(String str) {
        this.spotlightConnectionUrl = str;
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
    }

    public void setTag(String str, String str2) {
        this.tags.put(str, str2);
    }

    public void setTraceOptionsRequests(boolean z6) {
        this.traceOptionsRequests = z6;
    }

    public void setTracePropagationTargets(List<String> list) {
        if (list == null) {
            this.tracePropagationTargets = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.isEmpty()) {
                arrayList.add(str);
            }
        }
        this.tracePropagationTargets = arrayList;
    }

    @Deprecated
    public void setTraceSampling(boolean z6) {
        this.traceSampling = z6;
    }

    public void setTracesSampleRate(Double d) {
        if (h6.i.w(d, true)) {
            this.tracesSampleRate = d;
            return;
        }
        throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values between 0.0 and 1.0.");
    }

    public void setTracesSampler(E1 e12) {
    }

    @Deprecated
    public void setTracingOrigins(List<String> list) {
        setTracePropagationTargets(list);
    }

    public void setTransactionPerformanceCollector(d2 d2Var) {
        this.transactionPerformanceCollector = d2Var;
    }

    public void setTransactionProfiler(W w10) {
        if (this.transactionProfiler != C1524x0.f16989q || w10 == null) {
            return;
        }
        this.transactionProfiler = w10;
    }

    public void setTransportFactory(X x8) {
        if (x8 == null) {
            x8 = B0.l;
        }
        this.transportFactory = x8;
    }

    public void setTransportGate(io.sentry.transport.g gVar) {
        if (gVar == null) {
            gVar = io.sentry.transport.d.f16898b;
        }
        this.transportGate = gVar;
    }

    public void setViewHierarchyExporters(List<ComposeViewHierarchyExporter> list) {
        this.viewHierarchyExporters.clear();
        this.viewHierarchyExporters.addAll(list);
    }
}
