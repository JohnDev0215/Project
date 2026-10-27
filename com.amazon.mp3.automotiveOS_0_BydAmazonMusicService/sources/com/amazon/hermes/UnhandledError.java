package com.amazon.hermes;

import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes2.dex */
public class UnhandledError extends VolleyError {
    public UnhandledError(String str, Throwable th) {
        super(str, th);
    }

    public UnhandledError(Throwable th) {
        super(th);
    }

    public UnhandledError(String str) {
        super(str);
    }
}
