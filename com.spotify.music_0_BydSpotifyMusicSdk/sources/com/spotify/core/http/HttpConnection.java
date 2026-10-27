package com.spotify.core.http;

/* JADX INFO: loaded from: classes.dex */
public interface HttpConnection {
    public static final int kErrorSuccess = 0;
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int kErrorClassHttp = 300;
    public static final int kErrorHttpConnectTimeout = 300 + 1;
    public static final int kErrorHttpConnectFail = 300 + 2;
    public static final int kErrorHttpInvalidUrl = 300 + 3;
    public static final int kErrorHttpBadReply = 300 + 4;
    public static final int kErrorHttpBadRange = 300 + 5;
    public static final int kErrorHttpTooManyRedirects = 300 + 6;
    public static final int kErrorHttpBadTransferEncoding = 300 + 7;
    public static final int kErrorHttpTimeout = 300 + 8;
    public static final int kErrorHttpFail = 300 + 9;
    public static final int kErrorHttpGzipDecode = 300 + 10;
    public static final int kErrorHttpAborted = 300 + 11;
    public static final String kDefaultContentType = "text/plain";

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    void abort();

    boolean isRequestStarted();

    void onBytesAvailable(byte[] bArr, int i);

    void onComplete();

    void onError(int i);

    void onHeaders(HttpResponse httpResponse);

    void onRedirect();

    void send(HttpRequest httpRequest, HttpOptions httpOptions);
}
