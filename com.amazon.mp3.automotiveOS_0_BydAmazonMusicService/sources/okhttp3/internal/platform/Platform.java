package okhttp3.internal.platform;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.platform.android.AndroidLog;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* JADX INFO: compiled from: Platform.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0016J-\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0011\u0010\u0010\u001a\r\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u00130\u0011H\u0016J \u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0006\u0010\u001b\u001a\u00020\u000fJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001e\u001a\u00020\u000fH\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J&\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010#\u001a\u00020\u001a2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001a\u0010&\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000f2\b\u0010'\u001a\u0004\u0018\u00010\u0001H\u0016J\b\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020+2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010,\u001a\u00020\nH\u0016J\b\u0010-\u001a\u00020\u000fH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020+H\u0016¨\u00060"}, d2 = {"Lokhttp3/internal/platform/Platform;", "", "()V", "afterHandshake", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "buildTrustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "configureTlsExtensions", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "connectSocket", "socket", "Ljava/net/Socket;", "address", "Ljava/net/InetSocketAddress;", "connectTimeout", "", "getPrefix", "getSelectedProtocol", "getStackTraceForCloseable", "closer", "isCleartextTrafficPermitted", "", "log", "message", "level", "t", "", "logCloseableLeak", "stackTrace", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "newSslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "platformTrustManager", "toString", "sslSocketFactory", "Companion", "okhttp"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class Platform {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final Logger logger;
    private static volatile Platform platform;

    @JvmStatic
    public static final Platform get() {
        return INSTANCE.get();
    }

    public void afterHandshake(SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    }

    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
    }

    public final String getPrefix() {
        return "OkHttp";
    }

    public String getSelectedProtocol(SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return null;
    }

    public boolean isCleartextTrafficPermitted(String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return true;
    }

    public SSLContext newSSLContext() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
        Intrinsics.checkNotNullExpressionValue(sSLContext, "getInstance(\"TLS\")");
        return sSLContext;
    }

    public X509TrustManager platformTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        Intrinsics.checkNotNull(trustManagers);
        if (!(trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager))) {
            String string = Arrays.toString(trustManagers);
            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
            throw new IllegalStateException(Intrinsics.stringPlus("Unexpected default trust managers: ", string).toString());
        }
        TrustManager trustManager = trustManagers[0];
        if (trustManager != null) {
            return (X509TrustManager) trustManager;
        }
        throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
    }

    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) throws IllegalAccessException {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        try {
            Class<?> sslContextClass = Class.forName("sun.security.ssl.SSLContextImpl");
            Intrinsics.checkNotNullExpressionValue(sslContextClass, "sslContextClass");
            Object fieldOrNull = Util.readFieldOrNull(sslSocketFactory, sslContextClass, "context");
            if (fieldOrNull == null) {
                return null;
            }
            return (X509TrustManager) Util.readFieldOrNull(fieldOrNull, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (RuntimeException e) {
            if (Intrinsics.areEqual(e.getClass().getName(), "java.lang.reflect.InaccessibleObjectException")) {
                return null;
            }
            throw e;
        }
    }

    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "socket");
        Intrinsics.checkNotNullParameter(address, "address");
        socket.connect(address, connectTimeout);
    }

    public static /* synthetic */ void log$default(Platform platform2, String str, int i, Throwable th, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
        if ((i2 & 2) != 0) {
            i = 4;
        }
        if ((i2 & 4) != 0) {
            th = null;
        }
        platform2.log(str, i, th);
    }

    public void log(String message, int level, Throwable t) {
        Intrinsics.checkNotNullParameter(message, "message");
        logger.log(level == 5 ? Level.WARNING : Level.INFO, message, t);
    }

    public Object getStackTraceForCloseable(String closer) {
        Intrinsics.checkNotNullParameter(closer, "closer");
        if (logger.isLoggable(Level.FINE)) {
            return new Throwable(closer);
        }
        return null;
    }

    public void logCloseableLeak(String message, Object stackTrace) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (stackTrace == null) {
            message = Intrinsics.stringPlus(message, " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        log(message, 5, (Throwable) stackTrace);
    }

    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        return new BasicCertificateChainCleaner(buildTrustRootIndex(trustManager));
    }

    public TrustRootIndex buildTrustRootIndex(X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        X509Certificate[] acceptedIssuers = trustManager.getAcceptedIssuers();
        Intrinsics.checkNotNullExpressionValue(acceptedIssuers, "trustManager.acceptedIssuers");
        return new BasicTrustRootIndex((X509Certificate[]) Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }

    public SSLSocketFactory newSslSocketFactory(X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        try {
            SSLContext sSLContextNewSSLContext = newSSLContext();
            sSLContextNewSSLContext.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory socketFactory = sSLContextNewSSLContext.getSocketFactory();
            Intrinsics.checkNotNullExpressionValue(socketFactory, "newSSLContext().apply {\n…ll)\n      }.socketFactory");
            return socketFactory;
        } catch (GeneralSecurityException e) {
            throw new AssertionError(Intrinsics.stringPlus("No System TLS: ", e), e);
        }
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        return simpleName;
    }

    /* JADX INFO: compiled from: Platform.kt */
    @Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0007J\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0014\u0010\r\u001a\u00020\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u0014\u0010\u000f\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u00148\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u001c\u0010\u001a\u001a\n \u0019*\u0004\u0018\u00010\u00180\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lokhttp3/internal/platform/Platform$Companion;", "", "Lokhttp3/internal/platform/Platform;", "findPlatform", "findAndroidPlatform", "findJvmPlatform", "get", "", "Lokhttp3/Protocol;", "protocols", "", "alpnProtocolNames", "", "concatLengthPrefixed", "", "isConscryptPreferred", "()Z", "isOpenJSSEPreferred", "isBouncyCastlePreferred", "isAndroid", "", "INFO", "I", "WARN", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "logger", "Ljava/util/logging/Logger;", "platform", "Lokhttp3/internal/platform/Platform;", "<init>", "()V", "okhttp"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Platform get() {
            return Platform.platform;
        }

        public final boolean isAndroid() {
            return Intrinsics.areEqual("Dalvik", System.getProperty("java.vm.name"));
        }

        private final boolean isConscryptPreferred() {
            return Intrinsics.areEqual("Conscrypt", Security.getProviders()[0].getName());
        }

        private final boolean isOpenJSSEPreferred() {
            return Intrinsics.areEqual("OpenJSSE", Security.getProviders()[0].getName());
        }

        private final boolean isBouncyCastlePreferred() {
            return Intrinsics.areEqual("BC", Security.getProviders()[0].getName());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Platform findPlatform() {
            if (isAndroid()) {
                return findAndroidPlatform();
            }
            return findJvmPlatform();
        }

        private final Platform findAndroidPlatform() {
            AndroidLog.INSTANCE.enable();
            Platform platformBuildIfSupported = Android10Platform.INSTANCE.buildIfSupported();
            if (platformBuildIfSupported != null) {
                return platformBuildIfSupported;
            }
            Platform platformBuildIfSupported2 = AndroidPlatform.Companion.buildIfSupported();
            Intrinsics.checkNotNull(platformBuildIfSupported2);
            return platformBuildIfSupported2;
        }

        private final Platform findJvmPlatform() {
            OpenJSSEPlatform openJSSEPlatformBuildIfSupported;
            BouncyCastlePlatform bouncyCastlePlatformBuildIfSupported;
            ConscryptPlatform conscryptPlatformBuildIfSupported;
            if (isConscryptPreferred() && (conscryptPlatformBuildIfSupported = ConscryptPlatform.INSTANCE.buildIfSupported()) != null) {
                return conscryptPlatformBuildIfSupported;
            }
            if (isBouncyCastlePreferred() && (bouncyCastlePlatformBuildIfSupported = BouncyCastlePlatform.INSTANCE.buildIfSupported()) != null) {
                return bouncyCastlePlatformBuildIfSupported;
            }
            if (isOpenJSSEPreferred() && (openJSSEPlatformBuildIfSupported = OpenJSSEPlatform.INSTANCE.buildIfSupported()) != null) {
                return openJSSEPlatformBuildIfSupported;
            }
            Jdk9Platform jdk9PlatformBuildIfSupported = Jdk9Platform.INSTANCE.buildIfSupported();
            if (jdk9PlatformBuildIfSupported != null) {
                return jdk9PlatformBuildIfSupported;
            }
            Platform platformBuildIfSupported = Jdk8WithJettyBootPlatform.INSTANCE.buildIfSupported();
            return platformBuildIfSupported != null ? platformBuildIfSupported : new Platform();
        }

        public final byte[] concatLengthPrefixed(List<? extends Protocol> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            Buffer buffer = new Buffer();
            for (String str : alpnProtocolNames(protocols)) {
                buffer.writeByte(str.length());
                buffer.writeUtf8(str);
            }
            return buffer.readByteArray();
        }

        public final List<String> alpnProtocolNames(List<? extends Protocol> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            ArrayList arrayList = new ArrayList();
            for (Object obj : protocols) {
                if (((Protocol) obj) != Protocol.HTTP_1_0) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((Protocol) it.next()).getProtocol());
            }
            return arrayList2;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        platform = companion.findPlatform();
        logger = Logger.getLogger(OkHttpClient.class.getName());
    }
}
