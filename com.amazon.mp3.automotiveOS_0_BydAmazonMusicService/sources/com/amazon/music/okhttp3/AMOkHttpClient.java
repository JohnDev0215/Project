package com.amazon.music.okhttp3;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;
import com.amazon.music.okhttp3.metrics.MetricsInterceptor;
import com.amazon.music.okhttp3.metrics.MetricsManager;
import com.amazon.music.okhttp3.policy.AMPriorityPolicy;
import com.amazon.music.okhttp3.policy.AMRetryPolicy;
import com.amazon.music.okhttp3.policy.NoOpPriority;
import com.amazon.music.okhttp3.policy.NoRetry;
import com.amazon.music.okhttp3.response.ReleaseOnResponseStart;
import com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.net.okhttp3.ExtraEventListener;
import com.amazon.net.okhttp3.PriorityPolicy;
import com.amazon.net.okhttp3.RetryPolicy;
import com.amazon.net.okhttp3.app.UnifiedImplementation;
import com.amazon.net.okhttp3.app.UnifiedOkHttpClientApp;
import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AMOkHttpClient.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0007\u001a\u000e\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000\u001a\u000e\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005\u001a\b\u0010\t\u001a\u00020\bH\u0000\u001a\b\u0010\u000b\u001a\u00020\nH\u0002\u001a\b\u0010\r\u001a\u00020\fH\u0002\u001a\n\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002\u001a\u0012\u0010\u0010\u001a\u00020\b2\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0000\"\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\"\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018\"\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b\"\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e\"\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!\"\u0014\u0010\"\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010\u001b\"\u0014\u0010#\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010\u001e\"\u0014\u0010$\u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010!\"\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'\"\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*\"\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-\"\u0016\u0010\u0010\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010.\"\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101\"\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104¨\u00065"}, d2 = {"Landroid/content/Context;", "context", "", "initializeUnifiedOkHttpClient", "activateUnifiedOkHttpClient", "Lokhttp3/OkHttpClient$Builder;", "builder", "unifyOkHttpClientBuilder", "", "useUnifiedInterceptor", "Lcom/amazon/net/okhttp3/app/UnifiedImplementation;", "unifiedImplementation", "Ljava/net/URLStreamHandlerFactory;", "urlStreamHandlerFactory", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "isDebug", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "", "Lokhttp3/ConnectionSpec;", "CONNECTION_SPECS", "Ljava/util/List;", "Lcom/amazon/music/okhttp3/policy/NoOpPriority;", "HTTP_URL_CONNECTION_PRIORITY_POLICY", "Lcom/amazon/music/okhttp3/policy/NoOpPriority;", "Lcom/amazon/music/okhttp3/policy/NoRetry;", "HTTP_URL_CONNECTION_RETRY_POLICY", "Lcom/amazon/music/okhttp3/policy/NoRetry;", "Lcom/amazon/music/okhttp3/response/ReleaseOnResponseStart;", "HTTP_URL_CONNECTION_RESPONSE_REWRITER", "Lcom/amazon/music/okhttp3/response/ReleaseOnResponseStart;", "OK_HTTP_CLIENT_DEFAULT_PRIORITY_POLICY", "OK_HTTP_CLIENT_DEFAULT_RETRY_POLICY", "OK_HTTP_CLIENT_RESPONSE_REWRITER", "", "APP_START_MS", "J", "Ljava/util/concurrent/atomic/AtomicInteger;", "instanceCounter", "Ljava/util/concurrent/atomic/AtomicInteger;", "Lcom/amazon/music/okhttp3/State;", "state", "Lcom/amazon/music/okhttp3/State;", "Z", "Lokhttp3/ConnectionPool;", "connectionPool", "Lokhttp3/ConnectionPool;", "Lokhttp3/OkHttpClient;", "rootClient", "Lokhttp3/OkHttpClient;", "DMMOkHttpClient_release"}, k = 2, mv = {1, 6, 0})
@JvmName(name = "AMOkHttpClient")
public final class AMOkHttpClient {
    private static final long APP_START_MS;
    private static final List<ConnectionSpec> CONNECTION_SPECS;
    private static final NoOpPriority HTTP_URL_CONNECTION_PRIORITY_POLICY;
    private static final ReleaseOnResponseStart HTTP_URL_CONNECTION_RESPONSE_REWRITER;
    private static final NoRetry HTTP_URL_CONNECTION_RETRY_POLICY;
    private static final Logger LOGGER = LoggerFactory.getLogger("com.amazon.music.okhttp3.AMOkHttpClient");
    private static final NoOpPriority OK_HTTP_CLIENT_DEFAULT_PRIORITY_POLICY;
    private static final NoRetry OK_HTTP_CLIENT_DEFAULT_RETRY_POLICY;
    private static final ReleaseOnResponseStart OK_HTTP_CLIENT_RESPONSE_REWRITER;
    private static final ConnectionPool connectionPool;
    private static final AtomicInteger instanceCounter;
    private static volatile boolean isDebug;
    private static final OkHttpClient rootClient;
    private static State state;

    static {
        List<ConnectionSpec> listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new ConnectionSpec[]{ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS});
        CONNECTION_SPECS = listListOf;
        NoOpPriority noOpPriority = NoOpPriority.INSTANCE;
        HTTP_URL_CONNECTION_PRIORITY_POLICY = noOpPriority;
        NoRetry noRetry = NoRetry.INSTANCE;
        HTTP_URL_CONNECTION_RETRY_POLICY = noRetry;
        ReleaseOnResponseStart releaseOnResponseStart = ReleaseOnResponseStart.INSTANCE;
        HTTP_URL_CONNECTION_RESPONSE_REWRITER = releaseOnResponseStart;
        OK_HTTP_CLIENT_DEFAULT_PRIORITY_POLICY = noOpPriority;
        OK_HTTP_CLIENT_DEFAULT_RETRY_POLICY = noRetry;
        OK_HTTP_CLIENT_RESPONSE_REWRITER = releaseOnResponseStart;
        APP_START_MS = SystemClock.elapsedRealtime();
        instanceCounter = new AtomicInteger();
        state = State.NOT_INITIALIZED;
        ConnectionPool connectionPool2 = new ConnectionPool(20, 5L, TimeUnit.MINUTES);
        connectionPool = connectionPool2;
        rootClient = new OkHttpClient.Builder().connectionSpecs(listListOf).connectionPool(connectionPool2).build();
    }

    @JvmOverloads
    public static final void initializeUnifiedOkHttpClient(Context context) {
        isDebug = isDebug(context);
        if (state == State.NOT_INITIALIZED) {
            state = State.NOT_ACTIVATED;
            LOGGER.info("Initializing unified OkHttpClient");
            UnifiedOkHttpClientApp.injectImplementation(unifiedImplementation());
            return;
        }
        LOGGER.error(Intrinsics.stringPlus("Illegal call to initializeUnifiedOkHttpClient() in state ", state), (Throwable) new Exception("Stack trace"));
    }

    public static final void activateUnifiedOkHttpClient(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (state == State.NOT_ACTIVATED) {
            if (FeatureGates.INSTRUMENTED.isEnabled()) {
                LOGGER.info("Instrumenting unified OkHttpClient");
                MetricsManager metricsManager = new MetricsManager(context, APP_START_MS, AMOkHttpClient$activateUnifiedOkHttpClient$metricsManager$1.INSTANCE);
                metricsManager.enableIntervalMetrics$DMMOkHttpClient_release();
                MetricsInterceptor.INSTANCE.useMetricsManager$DMMOkHttpClient_release(metricsManager);
            }
            state = State.INTERCEPTOR_ON;
            LOGGER.info("Activating unified OkHttpClient");
            URL.setURLStreamHandlerFactory(urlStreamHandlerFactory());
            return;
        }
        if (state == State.INTERCEPTOR_ON) {
            LOGGER.info("Unified OkHttpClient is active (repeat call to activateUnifiedOkHttpClient() is ignored)");
        } else if (state == State.INTERCEPTOR_OFF) {
            LOGGER.info("Unified OkHttpClient is not active (repeat call to activateUnifiedOkHttpClient() is ignored)");
        } else {
            LOGGER.error(Intrinsics.stringPlus("Illegal call to activateUnifiedOkHttpClient() in state ", state), (Throwable) new Exception("Stack trace"));
        }
    }

    public static final void unifyOkHttpClientBuilder(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        for (Interceptor interceptor : builder.interceptors()) {
            if ((interceptor instanceof AMInterceptor) || (interceptor instanceof MetricsInterceptor)) {
                throw new IllegalArgumentException("The Builder passed to unifyOkHttpClientBuilder() already has unified Interceptors");
            }
        }
        builder.connectionSpecs(CONNECTION_SPECS).connectionPool(connectionPool).addInterceptor(new AMInterceptor(ExtraEventListener.EMPTY_LISTENER, OK_HTTP_CLIENT_DEFAULT_PRIORITY_POLICY, OK_HTTP_CLIENT_DEFAULT_RETRY_POLICY, OK_HTTP_CLIENT_RESPONSE_REWRITER, Intrinsics.stringPlus("UNI-", Integer.valueOf(instanceCounter.incrementAndGet())))).addInterceptor(new MetricsInterceptor());
        if (isDebug) {
            builder.addNetworkInterceptor(new AMNetworkInterceptor());
        }
    }

    public static final boolean useUnifiedInterceptor() {
        return state == State.INTERCEPTOR_ON;
    }

    private static final UnifiedImplementation unifiedImplementation() {
        return new UnifiedImplementation(new Function3<ExtraEventListener, PriorityPolicy, RetryPolicy, OkHttpClient.Builder>() { // from class: com.amazon.music.okhttp3.AMOkHttpClient.unifiedImplementation.1
            @Override // kotlin.jvm.functions.Function3
            public final OkHttpClient.Builder invoke(ExtraEventListener eventListener, PriorityPolicy priorityPolicy, RetryPolicy retryPolicy) {
                Intrinsics.checkNotNullParameter(eventListener, "eventListener");
                if (priorityPolicy == null) {
                    priorityPolicy = AMOkHttpClient.OK_HTTP_CLIENT_DEFAULT_PRIORITY_POLICY;
                }
                AMPriorityPolicy aMPriorityPolicy = (AMPriorityPolicy) priorityPolicy;
                if (retryPolicy == null) {
                    retryPolicy = AMOkHttpClient.OK_HTTP_CLIENT_DEFAULT_RETRY_POLICY;
                }
                OkHttpClient.Builder builderAddInterceptor = AMOkHttpClient.rootClient.newBuilder().addInterceptor(new AMInterceptor(eventListener, aMPriorityPolicy, (AMRetryPolicy) retryPolicy, AMOkHttpClient.OK_HTTP_CLIENT_RESPONSE_REWRITER, Intrinsics.stringPlus("OK-", Integer.valueOf(AMOkHttpClient.instanceCounter.incrementAndGet())))).addInterceptor(new MetricsInterceptor());
                if (AMOkHttpClient.isDebug) {
                    builderAddInterceptor.addNetworkInterceptor(new AMNetworkInterceptor());
                }
                return builderAddInterceptor;
            }
        }, new Function1<PriorityPolicy, Boolean>() { // from class: com.amazon.music.okhttp3.AMOkHttpClient.unifiedImplementation.2
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PriorityPolicy policy) {
                Intrinsics.checkNotNullParameter(policy, "policy");
                return Boolean.valueOf(policy instanceof AMPriorityPolicy);
            }
        }, OK_HTTP_CLIENT_DEFAULT_PRIORITY_POLICY, new Function1<RetryPolicy, Boolean>() { // from class: com.amazon.music.okhttp3.AMOkHttpClient.unifiedImplementation.3
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(RetryPolicy policy) {
                Intrinsics.checkNotNullParameter(policy, "policy");
                return Boolean.valueOf(policy instanceof AMRetryPolicy);
            }
        }, OK_HTTP_CLIENT_DEFAULT_RETRY_POLICY);
    }

    private static final URLStreamHandlerFactory urlStreamHandlerFactory() {
        OkHttpClient.Builder builderAddInterceptor = rootClient.newBuilder().addInterceptor(new AMInterceptor(ExtraEventListener.EMPTY_LISTENER, HTTP_URL_CONNECTION_PRIORITY_POLICY, HTTP_URL_CONNECTION_RETRY_POLICY, HTTP_URL_CONNECTION_RESPONSE_REWRITER, "URL")).addInterceptor(new MetricsInterceptor());
        if (isDebug) {
            builderAddInterceptor.addNetworkInterceptor(new AMNetworkInterceptor());
        }
        return new ObsoleteUrlFactory(builderAddInterceptor.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MetricsProvider metricsProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof MetricsProvider)) {
            interfaceProvider = null;
        }
        return (MetricsProvider) interfaceProvider;
    }

    public static final boolean isDebug(Context context) {
        ApplicationInfo applicationInfo;
        return (((context != null && (applicationInfo = context.getApplicationInfo()) != null) ? applicationInfo.flags : 0) & 2) != 0;
    }
}
