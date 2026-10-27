package com.spotify.connectivity.sessionstate;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class SessionHelper {
    private SessionHelper() {
    }

    public static boolean isReady(SessionState sessionState) {
        return (!sessionState.loggedIn() || TextUtils.isEmpty(sessionState.currentUser()) || TextUtils.isEmpty(sessionState.countryCode()) || TextUtils.isEmpty(sessionState.productType())) ? false : true;
    }
}
