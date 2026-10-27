package com.spotify.connectivity.connectivityapi;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class StoredCredentials {

    public static final class Authenticated extends StoredCredentials {
        private final String canonicalUsername;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Authenticated(String str) {
            super(null);
            v03.j(str, "canonicalUsername");
            this.canonicalUsername = str;
        }

        public final String getCanonicalUsername() {
            return this.canonicalUsername;
        }
    }

    public static final class NonAuthenticated extends StoredCredentials {
        public static final NonAuthenticated INSTANCE = new NonAuthenticated();

        private NonAuthenticated() {
            super(null);
        }
    }

    private StoredCredentials() {
    }

    public /* synthetic */ StoredCredentials(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
