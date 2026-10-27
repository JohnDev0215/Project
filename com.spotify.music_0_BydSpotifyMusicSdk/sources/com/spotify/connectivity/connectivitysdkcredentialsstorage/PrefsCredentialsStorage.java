package com.spotify.connectivity.connectivitysdkcredentialsstorage;

import com.spotify.connectivity.auth.NativeCredentialsStorage;
import com.spotify.prefs.prefs.Prefs;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class PrefsCredentialsStorage {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeCredentialsStorage create(Prefs prefs, String str) {
            return PrefsCredentialsStorage.create(prefs, str);
        }
    }

    public static final native NativeCredentialsStorage create(Prefs prefs, String str);
}
