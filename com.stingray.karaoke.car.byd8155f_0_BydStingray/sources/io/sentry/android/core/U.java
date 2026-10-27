package io.sentry.android.core;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import h3.C1350a;
import io.sentry.C1472i;
import io.sentry.C1478k;
import io.sentry.C1489n1;
import io.sentry.C1512t0;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.J1;
import io.sentry.R0;
import io.sentry.android.core.EnvelopeFileObserverIntegration;
import io.sentry.android.fragment.FragmentLifecycleIntegration;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.timber.SentryTimberIntegration;
import io.sentry.compose.gestures.ComposeGestureTargetLocator;
import io.sentry.compose.viewhierarchy.ComposeViewHierarchyExporter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f16117a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f16118b;

    public /* synthetic */ U(AnrIntegration anrIntegration, SentryAndroidOptions sentryAndroidOptions) {
        this.f16117a = anrIntegration;
        this.f16118b = sentryAndroidOptions;
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    public void a(F1 f12) {
        boolean z6;
        boolean z10;
        boolean z11;
        B b10;
        boolean z12;
        P p10 = (P) this.f16117a;
        Context context = (Context) this.f16118b;
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) f12;
        boolean zM = h6.j.m(sentryAndroidOptions, "timber.log.Timber");
        boolean z13 = h6.j.m(sentryAndroidOptions, "androidx.fragment.app.FragmentManager$FragmentLifecycleCallbacks") && h6.j.m(sentryAndroidOptions, "io.sentry.android.fragment.FragmentLifecycleIntegration");
        boolean z14 = zM && h6.j.m(sentryAndroidOptions, "io.sentry.android.timber.SentryTimberIntegration");
        boolean zM2 = h6.j.m(sentryAndroidOptions, "io.sentry.android.replay.ReplayIntegration");
        B b11 = new B(p10);
        N0.q qVar = new N0.q(sentryAndroidOptions);
        Context applicationContext = context.getApplicationContext();
        Context context2 = applicationContext != null ? applicationContext : context;
        sentryAndroidOptions.setLogger(p10);
        sentryAndroidOptions.setDateProvider(new W());
        sentryAndroidOptions.setFlushTimeoutMillis(4000L);
        sentryAndroidOptions.setFrameMetricsCollector(new io.sentry.android.core.internal.util.l(context2, p10, b11));
        try {
            Bundle bundleP = h6.m.p(context2, sentryAndroidOptions.getLogger(), b11);
            io.sentry.K logger = sentryAndroidOptions.getLogger();
            try {
                if (bundleP != null) {
                    sentryAndroidOptions.setDebug(h6.m.y(bundleP, logger, "io.sentry.debug", sentryAndroidOptions.isDebug()));
                    if (sentryAndroidOptions.isDebug()) {
                        String strName = sentryAndroidOptions.getDiagnosticLevel().name();
                        Locale locale = Locale.ROOT;
                        String strD = h6.m.D(bundleP, logger, "io.sentry.debug.level", strName.toLowerCase(locale));
                        if (strD != null) {
                            sentryAndroidOptions.setDiagnosticLevel(EnumC1495p1.valueOf(strD.toUpperCase(locale)));
                        }
                    }
                    sentryAndroidOptions.setAnrEnabled(h6.m.y(bundleP, logger, "io.sentry.anr.enable", sentryAndroidOptions.isAnrEnabled()));
                    sentryAndroidOptions.setEnableAutoSessionTracking(h6.m.y(bundleP, logger, "io.sentry.auto-session-tracking.enable", h6.m.y(bundleP, logger, "io.sentry.session-tracking.enable", sentryAndroidOptions.isEnableAutoSessionTracking())));
                    if (sentryAndroidOptions.getSampleRate() == null) {
                        Double dA = h6.m.A(bundleP, logger, "io.sentry.sample-rate");
                        if (dA.doubleValue() != -1.0d) {
                            sentryAndroidOptions.setSampleRate(dA);
                        }
                    }
                    sentryAndroidOptions.setAnrReportInDebug(h6.m.y(bundleP, logger, "io.sentry.anr.report-debug", sentryAndroidOptions.isAnrReportInDebug()));
                    sentryAndroidOptions.setAnrTimeoutIntervalMillis(h6.m.C(bundleP, logger, "io.sentry.anr.timeout-interval-millis", sentryAndroidOptions.getAnrTimeoutIntervalMillis()));
                    sentryAndroidOptions.setAttachAnrThreadDump(h6.m.y(bundleP, logger, "io.sentry.anr.attach-thread-dumps", sentryAndroidOptions.isAttachAnrThreadDump()));
                    String strD2 = h6.m.D(bundleP, logger, "io.sentry.dsn", sentryAndroidOptions.getDsn());
                    boolean zY = h6.m.y(bundleP, logger, "io.sentry.enabled", sentryAndroidOptions.isEnabled());
                    if (!zY || (strD2 != null && strD2.isEmpty())) {
                        z6 = zM2;
                        z10 = z14;
                        z11 = z13;
                        sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "Sentry enabled flag set to false or DSN is empty: disabling sentry-android", new Object[0]);
                    } else if (strD2 == null) {
                        z6 = zM2;
                        z10 = z14;
                        z11 = z13;
                        sentryAndroidOptions.getLogger().k(EnumC1495p1.FATAL, "DSN is required. Use empty string to disable SDK.", new Object[0]);
                    } else {
                        z6 = zM2;
                        z10 = z14;
                        z11 = z13;
                    }
                    sentryAndroidOptions.setEnabled(zY);
                    sentryAndroidOptions.setDsn(strD2);
                    sentryAndroidOptions.setEnableNdk(h6.m.y(bundleP, logger, "io.sentry.ndk.enable", sentryAndroidOptions.isEnableNdk()));
                    sentryAndroidOptions.setEnableScopeSync(h6.m.y(bundleP, logger, "io.sentry.ndk.scope-sync.enable", sentryAndroidOptions.isEnableScopeSync()));
                    sentryAndroidOptions.setRelease(h6.m.D(bundleP, logger, "io.sentry.release", sentryAndroidOptions.getRelease()));
                    sentryAndroidOptions.setEnvironment(h6.m.D(bundleP, logger, "io.sentry.environment", sentryAndroidOptions.getEnvironment()));
                    sentryAndroidOptions.setSessionTrackingIntervalMillis(h6.m.C(bundleP, logger, "io.sentry.session-tracking.timeout-interval-millis", sentryAndroidOptions.getSessionTrackingIntervalMillis()));
                    sentryAndroidOptions.setMaxBreadcrumbs((int) h6.m.C(bundleP, logger, "io.sentry.max-breadcrumbs", sentryAndroidOptions.getMaxBreadcrumbs()));
                    sentryAndroidOptions.setEnableActivityLifecycleBreadcrumbs(h6.m.y(bundleP, logger, "io.sentry.breadcrumbs.activity-lifecycle", sentryAndroidOptions.isEnableActivityLifecycleBreadcrumbs()));
                    sentryAndroidOptions.setEnableAppLifecycleBreadcrumbs(h6.m.y(bundleP, logger, "io.sentry.breadcrumbs.app-lifecycle", sentryAndroidOptions.isEnableAppLifecycleBreadcrumbs()));
                    sentryAndroidOptions.setEnableSystemEventBreadcrumbs(h6.m.y(bundleP, logger, "io.sentry.breadcrumbs.system-events", sentryAndroidOptions.isEnableSystemEventBreadcrumbs()));
                    sentryAndroidOptions.setEnableAppComponentBreadcrumbs(h6.m.y(bundleP, logger, "io.sentry.breadcrumbs.app-components", sentryAndroidOptions.isEnableAppComponentBreadcrumbs()));
                    sentryAndroidOptions.setEnableUserInteractionBreadcrumbs(h6.m.y(bundleP, logger, "io.sentry.breadcrumbs.user-interaction", sentryAndroidOptions.isEnableUserInteractionBreadcrumbs()));
                    sentryAndroidOptions.setEnableNetworkEventBreadcrumbs(h6.m.y(bundleP, logger, "io.sentry.breadcrumbs.network-events", sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()));
                    sentryAndroidOptions.setEnableUncaughtExceptionHandler(h6.m.y(bundleP, logger, "io.sentry.uncaught-exception-handler.enable", sentryAndroidOptions.isEnableUncaughtExceptionHandler()));
                    sentryAndroidOptions.setAttachThreads(h6.m.y(bundleP, logger, "io.sentry.attach-threads", sentryAndroidOptions.isAttachThreads()));
                    sentryAndroidOptions.setAttachScreenshot(h6.m.y(bundleP, logger, "io.sentry.attach-screenshot", sentryAndroidOptions.isAttachScreenshot()));
                    sentryAndroidOptions.setAttachViewHierarchy(h6.m.y(bundleP, logger, "io.sentry.attach-view-hierarchy", sentryAndroidOptions.isAttachViewHierarchy()));
                    sentryAndroidOptions.setSendClientReports(h6.m.y(bundleP, logger, "io.sentry.send-client-reports", sentryAndroidOptions.isSendClientReports()));
                    sentryAndroidOptions.setCollectAdditionalContext(h6.m.y(bundleP, logger, "io.sentry.additional-context", sentryAndroidOptions.isCollectAdditionalContext()));
                    if (sentryAndroidOptions.getEnableTracing() == null) {
                        sentryAndroidOptions.setEnableTracing(h6.m.z(bundleP, logger));
                    }
                    if (sentryAndroidOptions.getTracesSampleRate() == null) {
                        Double dA2 = h6.m.A(bundleP, logger, "io.sentry.traces.sample-rate");
                        if (dA2.doubleValue() != -1.0d) {
                            sentryAndroidOptions.setTracesSampleRate(dA2);
                        }
                    }
                    sentryAndroidOptions.setTraceSampling(h6.m.y(bundleP, logger, "io.sentry.traces.trace-sampling", sentryAndroidOptions.isTraceSampling()));
                    sentryAndroidOptions.setEnableAutoActivityLifecycleTracing(h6.m.y(bundleP, logger, "io.sentry.traces.activity.enable", sentryAndroidOptions.isEnableAutoActivityLifecycleTracing()));
                    sentryAndroidOptions.setEnableActivityLifecycleTracingAutoFinish(h6.m.y(bundleP, logger, "io.sentry.traces.activity.auto-finish.enable", sentryAndroidOptions.isEnableActivityLifecycleTracingAutoFinish()));
                    sentryAndroidOptions.setProfilingEnabled(h6.m.y(bundleP, logger, "io.sentry.traces.profiling.enable", sentryAndroidOptions.isProfilingEnabled()));
                    if (sentryAndroidOptions.getProfilesSampleRate() == null) {
                        Double dA3 = h6.m.A(bundleP, logger, "io.sentry.traces.profiling.sample-rate");
                        if (dA3.doubleValue() != -1.0d) {
                            sentryAndroidOptions.setProfilesSampleRate(dA3);
                        }
                    }
                    sentryAndroidOptions.setEnableUserInteractionTracing(h6.m.y(bundleP, logger, "io.sentry.traces.user-interaction.enable", sentryAndroidOptions.isEnableUserInteractionTracing()));
                    sentryAndroidOptions.setEnableTimeToFullDisplayTracing(h6.m.y(bundleP, logger, "io.sentry.traces.time-to-full-display.enable", sentryAndroidOptions.isEnableTimeToFullDisplayTracing()));
                    long jC = h6.m.C(bundleP, logger, "io.sentry.traces.idle-timeout", -1L);
                    if (jC != -1) {
                        sentryAndroidOptions.setIdleTimeout(Long.valueOf(jC));
                    }
                    List<String> listB = h6.m.B(bundleP, logger, "io.sentry.traces.trace-propagation-targets");
                    if (!bundleP.containsKey("io.sentry.traces.trace-propagation-targets") && (listB == null || listB.isEmpty())) {
                        listB = h6.m.B(bundleP, logger, "io.sentry.traces.tracing-origins");
                    }
                    if ((bundleP.containsKey("io.sentry.traces.trace-propagation-targets") || bundleP.containsKey("io.sentry.traces.tracing-origins")) && listB == null) {
                        sentryAndroidOptions.setTracePropagationTargets(Collections.emptyList());
                    } else if (listB != null) {
                        sentryAndroidOptions.setTracePropagationTargets(listB);
                    }
                    sentryAndroidOptions.setEnableFramesTracking(h6.m.y(bundleP, logger, "io.sentry.traces.frames-tracking", true));
                    sentryAndroidOptions.setProguardUuid(h6.m.D(bundleP, logger, "io.sentry.proguard-uuid", sentryAndroidOptions.getProguardUuid()));
                    io.sentry.protocol.r sdkVersion = sentryAndroidOptions.getSdkVersion();
                    if (sdkVersion == null) {
                        sdkVersion = new io.sentry.protocol.r("", "");
                    }
                    String strE = h6.m.E(bundleP, logger, "io.sentry.sdk.name", sdkVersion.l);
                    h6.e.q(strE, "name is required.");
                    sdkVersion.l = strE;
                    String strE2 = h6.m.E(bundleP, logger, "io.sentry.sdk.version", sdkVersion.f16766m);
                    h6.e.q(strE2, "version is required.");
                    sdkVersion.f16766m = strE2;
                    sentryAndroidOptions.setSdkVersion(sdkVersion);
                    sentryAndroidOptions.setSendDefaultPii(h6.m.y(bundleP, logger, "io.sentry.send-default-pii", sentryAndroidOptions.isSendDefaultPii()));
                    List listB2 = h6.m.B(bundleP, logger, "io.sentry.gradle-plugin-integrations");
                    if (listB2 != null) {
                        Iterator it = listB2.iterator();
                        while (it.hasNext()) {
                            C1489n1.s().f((String) it.next());
                        }
                    }
                    sentryAndroidOptions.setEnableRootCheck(h6.m.y(bundleP, logger, "io.sentry.enable-root-check", sentryAndroidOptions.isEnableRootCheck()));
                    sentryAndroidOptions.setSendModules(h6.m.y(bundleP, logger, "io.sentry.send-modules", sentryAndroidOptions.isSendModules()));
                    sentryAndroidOptions.setEnablePerformanceV2(h6.m.y(bundleP, logger, "io.sentry.performance-v2.enable", sentryAndroidOptions.isEnablePerformanceV2()));
                    sentryAndroidOptions.setEnableAppStartProfiling(h6.m.y(bundleP, logger, "io.sentry.profiling.enable-app-start", sentryAndroidOptions.isEnableAppStartProfiling()));
                    sentryAndroidOptions.setEnableScopePersistence(h6.m.y(bundleP, logger, "io.sentry.enable-scope-persistence", sentryAndroidOptions.isEnableScopePersistence()));
                    sentryAndroidOptions.setEnableMetrics(h6.m.y(bundleP, logger, "io.sentry.enable-metrics", sentryAndroidOptions.isEnableMetrics()));
                    if (sentryAndroidOptions.getExperimental().f16913a.f15880a == null) {
                        Double dA4 = h6.m.A(bundleP, logger, "io.sentry.session-replay.session-sample-rate");
                        if (dA4.doubleValue() != -1.0d) {
                            J1 j12 = sentryAndroidOptions.getExperimental().f16913a;
                            j12.getClass();
                            if (!h6.i.w(dA4, true)) {
                                throw new IllegalArgumentException("The value " + dA4 + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
                            }
                            j12.f15880a = dA4;
                        }
                    }
                    if (sentryAndroidOptions.getExperimental().f16913a.f15881b == null) {
                        Double dA5 = h6.m.A(bundleP, logger, "io.sentry.session-replay.on-error-sample-rate");
                        if (dA5.doubleValue() != -1.0d) {
                            J1 j13 = sentryAndroidOptions.getExperimental().f16913a;
                            j13.getClass();
                            if (!h6.i.w(dA5, true)) {
                                throw new IllegalArgumentException("The value " + dA5 + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
                            }
                            j13.f15881b = dA5;
                        }
                    }
                    J1 j14 = sentryAndroidOptions.getExperimental().f16913a;
                    if (h6.m.y(bundleP, logger, "io.sentry.session-replay.mask-all-text", true)) {
                        j14.c.add("android.widget.TextView");
                        j14.d.remove("android.widget.TextView");
                    } else {
                        j14.d.add("android.widget.TextView");
                        j14.c.remove("android.widget.TextView");
                    }
                    J1 j15 = sentryAndroidOptions.getExperimental().f16913a;
                    if (h6.m.y(bundleP, logger, "io.sentry.session-replay.mask-all-images", true)) {
                        j15.c.add("android.widget.ImageView");
                        j15.d.remove("android.widget.ImageView");
                    } else {
                        j15.d.add("android.widget.ImageView");
                        j15.c.remove("android.widget.ImageView");
                    }
                } else {
                    z6 = zM2;
                    z10 = z14;
                    z11 = z13;
                }
                sentryAndroidOptions.getLogger().k(EnumC1495p1.INFO, "Retrieving configuration from AndroidManifest.xml", new Object[0]);
            } catch (Throwable th) {
                th = th;
                sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Failed to read configuration from android manifest metadata.", th);
            }
        } catch (Throwable th2) {
            th = th2;
            z6 = zM2;
            z10 = z14;
            z11 = z13;
        }
        sentryAndroidOptions.setCacheDirPath(new File(context2.getCacheDir(), "sentry").getAbsolutePath());
        PackageInfo packageInfoO = h6.i.o(context2, 0, sentryAndroidOptions.getLogger(), b11);
        if (packageInfoO != null) {
            if (sentryAndroidOptions.getRelease() == null) {
                sentryAndroidOptions.setRelease(packageInfoO.packageName + "@" + packageInfoO.versionName + "+" + Long.toString(packageInfoO.getLongVersionCode()));
            }
            String str = packageInfoO.packageName;
            if (str != null && !str.startsWith("android.")) {
                sentryAndroidOptions.addInAppInclude(str);
            }
        }
        if (sentryAndroidOptions.getDistinctId() == null) {
            try {
                sentryAndroidOptions.setDistinctId(K.a(context2));
            } catch (RuntimeException e10) {
                sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Could not generate distinct Id.", e10);
            }
        }
        io.sentry.util.d dVar = new io.sentry.util.d(new C1436l(sentryAndroidOptions));
        sentryAndroidOptions.addIntegration(new SendCachedEnvelopeIntegration(new R0(new C1436l(sentryAndroidOptions), 0), dVar));
        sentryAndroidOptions.addIntegration(new NdkIntegration(h6.j.n("io.sentry.android.ndk.SentryNdk", sentryAndroidOptions.getLogger())));
        sentryAndroidOptions.addIntegration(new EnvelopeFileObserverIntegration.OutboxEnvelopeFileObserverIntegration(0));
        sentryAndroidOptions.addIntegration(new SendCachedEnvelopeIntegration(new R0(new C1436l(sentryAndroidOptions), 1), dVar));
        sentryAndroidOptions.addIntegration(new AppLifecycleIntegration());
        sentryAndroidOptions.addIntegration(Build.VERSION.SDK_INT >= 30 ? new AnrV2Integration(context) : new AnrIntegration(context));
        if (context instanceof Application) {
            Application application = (Application) context;
            sentryAndroidOptions.addIntegration(new ActivityLifecycleIntegration(application, b11, qVar));
            sentryAndroidOptions.addIntegration(new ActivityBreadcrumbsIntegration(application));
            sentryAndroidOptions.addIntegration(new CurrentActivityIntegration(application));
            sentryAndroidOptions.addIntegration(new UserInteractionIntegration(application));
            if (z11) {
                sentryAndroidOptions.addIntegration(new FragmentLifecycleIntegration(application, true, true));
            }
        } else {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.WARNING, "ActivityLifecycle, FragmentLifecycle and UserInteraction Integrations need an Application class to be installed.", new Object[0]);
        }
        if (z10) {
            sentryAndroidOptions.addIntegration(new SentryTimberIntegration());
        }
        sentryAndroidOptions.addIntegration(new AppComponentsBreadcrumbsIntegration(context));
        sentryAndroidOptions.addIntegration(new SystemEventsBreadcrumbsIntegration(context));
        sentryAndroidOptions.addIntegration(new NetworkBreadcrumbsIntegration(context, sentryAndroidOptions.getLogger(), b11));
        sentryAndroidOptions.addIntegration(new TempSensorBreadcrumbsIntegration(context));
        sentryAndroidOptions.addIntegration(new PhoneStateBreadcrumbsIntegration(context));
        if (z6) {
            ReplayIntegration replayIntegration = new ReplayIntegration(context);
            replayIntegration.f16325w = new io.sentry.android.replay.c();
            sentryAndroidOptions.addIntegration(replayIntegration);
            sentryAndroidOptions.setReplayController(replayIntegration);
        }
        try {
            int i7 = V.f16122b;
        } catch (Throwable th3) {
            sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Error in the 'OptionsConfiguration.configure' callback.", th3);
        }
        io.sentry.android.core.performance.e eVarB = io.sentry.android.core.performance.e.b();
        if (sentryAndroidOptions.isEnablePerformanceV2()) {
            io.sentry.android.core.performance.f fVar = eVarB.f16278n;
            if (fVar.f16288n == 0) {
                fVar.d(Process.getStartUptimeMillis());
            }
        }
        if (context.getApplicationContext() instanceof Application) {
            eVarB.f((Application) context.getApplicationContext());
        }
        io.sentry.android.core.performance.f fVar2 = eVarB.o;
        if (fVar2.f16288n == 0) {
            fVar2.d(V.f16121a);
        }
        if (sentryAndroidOptions.getCacheDirPath() != null && (sentryAndroidOptions.getEnvelopeDiskCache() instanceof io.sentry.transport.h)) {
            sentryAndroidOptions.setEnvelopeDiskCache(new io.sentry.android.core.cache.a(sentryAndroidOptions));
        }
        if (sentryAndroidOptions.getConnectionStatusProvider() instanceof C1512t0) {
            sentryAndroidOptions.setConnectionStatusProvider(new B8.t(context, sentryAndroidOptions.getLogger(), b11));
        }
        sentryAndroidOptions.addEventProcessor(new C1472i(sentryAndroidOptions, 0));
        sentryAndroidOptions.addEventProcessor(new D(context, b11, sentryAndroidOptions));
        sentryAndroidOptions.addEventProcessor(new Q(sentryAndroidOptions, qVar));
        sentryAndroidOptions.addEventProcessor(new ScreenshotEventProcessor(sentryAndroidOptions, b11));
        sentryAndroidOptions.addEventProcessor(new ViewHierarchyEventProcessor(sentryAndroidOptions));
        sentryAndroidOptions.addEventProcessor(new C1443t(context, b11, sentryAndroidOptions));
        sentryAndroidOptions.setTransportGate(new B(sentryAndroidOptions));
        synchronized (io.sentry.android.core.performance.e.b()) {
            try {
                C1440p c1440p = io.sentry.android.core.performance.e.b().f16282s;
                if (c1440p != null) {
                    sentryAndroidOptions.setTransactionProfiler(c1440p);
                    io.sentry.android.core.performance.e.b().f16282s = null;
                    b10 = b11;
                    z12 = z10;
                } else {
                    io.sentry.android.core.internal.util.l frameMetricsCollector = sentryAndroidOptions.getFrameMetricsCollector();
                    h6.e.q(frameMetricsCollector, "options.getFrameMetricsCollector is required");
                    b10 = b11;
                    z12 = z10;
                    sentryAndroidOptions.setTransactionProfiler(new C1440p(context, b11, frameMetricsCollector, sentryAndroidOptions.getLogger(), sentryAndroidOptions.getProfilingTracesDirPath(), sentryAndroidOptions.isProfilingEnabled(), sentryAndroidOptions.getProfilingTracesHz(), sentryAndroidOptions.getExecutorService()));
                }
            } finally {
            }
        }
        sentryAndroidOptions.setModulesLoader(new io.sentry.internal.modules.f(context, sentryAndroidOptions.getLogger()));
        sentryAndroidOptions.setDebugMetaLoader(new io.sentry.internal.debugmeta.c(context, sentryAndroidOptions.getLogger()));
        boolean zM3 = h6.j.m(sentryAndroidOptions, "androidx.core.view.ScrollingView");
        boolean zM4 = h6.j.m(sentryAndroidOptions, "androidx.compose.ui.node.Owner");
        if (sentryAndroidOptions.getGestureTargetLocators().isEmpty()) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(new io.sentry.android.core.internal.gestures.a(zM3));
            if (zM4 && h6.j.m(sentryAndroidOptions, "io.sentry.compose.gestures.ComposeGestureTargetLocator")) {
                arrayList.add(new ComposeGestureTargetLocator(sentryAndroidOptions.getLogger()));
            }
            sentryAndroidOptions.setGestureTargetLocators(arrayList);
        }
        if (sentryAndroidOptions.getViewHierarchyExporters().isEmpty() && zM4 && h6.j.m(sentryAndroidOptions, "io.sentry.compose.viewhierarchy.ComposeViewHierarchyExporter")) {
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(new ComposeViewHierarchyExporter(sentryAndroidOptions.getLogger()));
            sentryAndroidOptions.setViewHierarchyExporters(arrayList2);
        }
        sentryAndroidOptions.setMainThreadChecker(io.sentry.android.core.internal.util.b.f16210b);
        if (sentryAndroidOptions.getPerformanceCollectors().isEmpty()) {
            sentryAndroidOptions.addPerformanceCollector(new C1435k());
            sentryAndroidOptions.addPerformanceCollector(new C1432h(sentryAndroidOptions.getLogger(), b10));
            if (sentryAndroidOptions.isEnablePerformanceV2()) {
                io.sentry.android.core.internal.util.l frameMetricsCollector2 = sentryAndroidOptions.getFrameMetricsCollector();
                h6.e.q(frameMetricsCollector2, "options.getFrameMetricsCollector is required");
                sentryAndroidOptions.addPerformanceCollector(new a0(sentryAndroidOptions, frameMetricsCollector2));
            }
        }
        sentryAndroidOptions.setTransactionPerformanceCollector(new C1478k(sentryAndroidOptions));
        if (sentryAndroidOptions.getCacheDirPath() != null) {
            if (sentryAndroidOptions.isEnableScopePersistence()) {
                sentryAndroidOptions.addScopeObserver(new io.sentry.cache.f(sentryAndroidOptions));
            }
            sentryAndroidOptions.addOptionsObserver(new io.sentry.cache.e(sentryAndroidOptions));
        }
        V.a(sentryAndroidOptions, z11, z12);
    }

    public /* synthetic */ U(P p10, Context context, C1350a c1350a) {
        this.f16117a = p10;
        this.f16118b = context;
    }
}
