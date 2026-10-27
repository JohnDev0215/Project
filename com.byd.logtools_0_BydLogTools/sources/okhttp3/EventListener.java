package okhttp3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: EventListener.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EventListener {
    public static final Companion Companion = new Companion(null);
    public static final EventListener NONE = new EventListener() { // from class: okhttp3.EventListener$Companion$NONE$1
    };

    /* JADX INFO: compiled from: EventListener.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: EventListener.kt */
    public interface Factory {
        EventListener create(Call call);
    }

    public void cacheConditionalHit(Call call, Response response) {
        i.c(call, "call");
        i.c(response, "cachedResponse");
    }

    public void cacheHit(Call call, Response response) {
        i.c(call, "call");
        i.c(response, "response");
    }

    public void cacheMiss(Call call) {
        i.c(call, "call");
    }

    public void callEnd(Call call) {
        i.c(call, "call");
    }

    public void callFailed(Call call, IOException iOException) {
        i.c(call, "call");
        i.c(iOException, "ioe");
    }

    public void callStart(Call call) {
        i.c(call, "call");
    }

    public void canceled(Call call) {
        i.c(call, "call");
    }

    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        i.c(call, "call");
        i.c(inetSocketAddress, "inetSocketAddress");
        i.c(proxy, "proxy");
    }

    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException iOException) {
        i.c(call, "call");
        i.c(inetSocketAddress, "inetSocketAddress");
        i.c(proxy, "proxy");
        i.c(iOException, "ioe");
    }

    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        i.c(call, "call");
        i.c(inetSocketAddress, "inetSocketAddress");
        i.c(proxy, "proxy");
    }

    public void connectionAcquired(Call call, Connection connection) {
        i.c(call, "call");
        i.c(connection, "connection");
    }

    public void connectionReleased(Call call, Connection connection) {
        i.c(call, "call");
        i.c(connection, "connection");
    }

    public void dnsEnd(Call call, String str, List<InetAddress> list) {
        i.c(call, "call");
        i.c(str, "domainName");
        i.c(list, "inetAddressList");
    }

    public void dnsStart(Call call, String str) {
        i.c(call, "call");
        i.c(str, "domainName");
    }

    public void proxySelectEnd(Call call, HttpUrl httpUrl, List<Proxy> list) {
        i.c(call, "call");
        i.c(httpUrl, "url");
        i.c(list, "proxies");
    }

    public void proxySelectStart(Call call, HttpUrl httpUrl) {
        i.c(call, "call");
        i.c(httpUrl, "url");
    }

    public void requestBodyEnd(Call call, long j) {
        i.c(call, "call");
    }

    public void requestBodyStart(Call call) {
        i.c(call, "call");
    }

    public void requestFailed(Call call, IOException iOException) {
        i.c(call, "call");
        i.c(iOException, "ioe");
    }

    public void requestHeadersEnd(Call call, Request request) {
        i.c(call, "call");
        i.c(request, "request");
    }

    public void requestHeadersStart(Call call) {
        i.c(call, "call");
    }

    public void responseBodyEnd(Call call, long j) {
        i.c(call, "call");
    }

    public void responseBodyStart(Call call) {
        i.c(call, "call");
    }

    public void responseFailed(Call call, IOException iOException) {
        i.c(call, "call");
        i.c(iOException, "ioe");
    }

    public void responseHeadersEnd(Call call, Response response) {
        i.c(call, "call");
        i.c(response, "response");
    }

    public void responseHeadersStart(Call call) {
        i.c(call, "call");
    }

    public void satisfactionFailure(Call call, Response response) {
        i.c(call, "call");
        i.c(response, "response");
    }

    public void secureConnectEnd(Call call, Handshake handshake) {
        i.c(call, "call");
    }

    public void secureConnectStart(Call call) {
        i.c(call, "call");
    }
}
