package com.android.volley;

import android.content.Intent;

/* JADX INFO: loaded from: classes5.dex */
public class AuthFailureError extends VolleyError {
    private Intent mResolutionIntent;

    public AuthFailureError() {
    }

    public AuthFailureError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.mResolutionIntent != null ? "User needs to (re)enter credentials." : super.getMessage();
    }
}
