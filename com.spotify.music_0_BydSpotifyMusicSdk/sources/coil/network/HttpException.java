package coil.network;

import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class HttpException extends RuntimeException {
    private final Response response;

    public HttpException(Response response) {
        super("HTTP " + response.l + ": " + response.k);
        this.response = response;
    }
}
