package com.spotify.cosmos.routercallback;

import android.os.Handler;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.parsers.ParserException;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class ParsingCallbackReceiver<T> extends ResolverCallbackReceiver<Response> {

    public enum ErrorCause {
        RESOLVING,
        PARSING,
        UNKNOWN
    }

    public ParsingCallbackReceiver(Handler handler) {
        super(handler);
    }

    private final void postError(final Throwable th, final ErrorCause errorCause) {
        Handler handler = getHandler();
        if (handler != null) {
            handler.post(new Runnable(this) { // from class: com.spotify.cosmos.routercallback.ParsingCallbackReceiver.postError.1
                final /* synthetic */ ParsingCallbackReceiver<T> this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.onError(th, errorCause);
                }
            });
        } else {
            onError(th, errorCause);
        }
    }

    private final void postResult(final Response response, final T t) {
        Handler handler = getHandler();
        if (handler != null) {
            handler.post(new Runnable(this) { // from class: com.spotify.cosmos.routercallback.ParsingCallbackReceiver.postResult.1
                final /* synthetic */ ParsingCallbackReceiver<T> this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.onResolved(response, t);
                }
            });
        } else {
            onResolved(response, t);
        }
    }

    @Override // com.spotify.cosmos.routercallback.ResolverCallbackReceiver
    public void onError(Throwable th) {
        v03.j(th, "error");
        postError(th, ErrorCause.RESOLVING);
    }

    public abstract void onError(Throwable th, ErrorCause errorCause);

    public abstract void onResolved(Response response, T t);

    public abstract T parseResponse(Response response);

    public void verifyResponse(Response response) {
        v03.j(response, "response");
    }

    @Override // com.spotify.cosmos.routercallback.ResolverCallbackReceiver
    public void onResolved(Response response) {
        v03.j(response, "response");
        try {
            verifyResponse(response);
            postResult(response, parseResponse(response));
        } catch (ParserException e) {
            postError(e, ErrorCause.PARSING);
        }
    }

    @Override // com.spotify.cosmos.routercallback.ResolverCallbackReceiver
    public void sendOnResolved(Response response) {
        v03.j(response, "response");
        onResolved(response);
    }
}
