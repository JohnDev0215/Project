package com.spotify.prefs.prefsimpl;

import com.spotify.prefs.prefs.Prefs;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativePrefs implements Prefs {
    public static final Companion Companion = new Companion(null);
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativePrefs create() {
            return NativePrefs.create();
        }
    }

    private NativePrefs() {
    }

    public static final native NativePrefs create();

    public final native void destroy();
}
