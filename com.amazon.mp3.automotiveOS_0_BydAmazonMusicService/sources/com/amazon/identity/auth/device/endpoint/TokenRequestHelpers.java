package com.amazon.identity.auth.device.endpoint;

import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class TokenRequestHelpers {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f305a = "com.amazon.identity.auth.device.endpoint.TokenRequestHelpers";

    /* JADX INFO: compiled from: DCP */
    public enum PROTOCOL {
        HTTPS;

        @Override // java.lang.Enum
        public String toString() {
            return super.toString().toLowerCase(Locale.US) + "://";
        }
    }
}
