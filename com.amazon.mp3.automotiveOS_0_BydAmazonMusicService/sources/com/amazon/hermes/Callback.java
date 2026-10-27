package com.amazon.hermes;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Callback<T> {
    public void onCoralError(CoralError coralError) {
    }

    public void onNetworkError(NetworkError networkError) {
    }

    public void onNoConnectionError(NoConnectionError noConnectionError) {
    }

    public void onParseError(ParseError parseError) {
    }

    public void onServerError(ServerError serverError) {
    }

    public abstract void onSuccess(T t);

    public void onTimeoutError(TimeoutError timeoutError) {
    }

    public void onUnhandledError(UnhandledError unhandledError) {
    }

    public void onError(VolleyError volleyError) {
        if (volleyError instanceof NoConnectionError) {
            onNoConnectionError((NoConnectionError) volleyError);
            return;
        }
        if (volleyError instanceof NetworkError) {
            onNetworkError((NetworkError) volleyError);
            return;
        }
        if (volleyError instanceof ParseError) {
            onParseError((ParseError) volleyError);
            return;
        }
        if (volleyError instanceof TimeoutError) {
            onTimeoutError((TimeoutError) volleyError);
            return;
        }
        if (volleyError instanceof CoralError) {
            onCoralError((CoralError) volleyError);
        } else if (volleyError instanceof ServerError) {
            onServerError((ServerError) volleyError);
        } else if (volleyError instanceof UnhandledError) {
            onUnhandledError((UnhandledError) volleyError);
        }
    }
}
