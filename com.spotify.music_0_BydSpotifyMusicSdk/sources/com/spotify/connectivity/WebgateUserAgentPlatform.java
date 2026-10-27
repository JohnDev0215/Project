package com.spotify.connectivity;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class WebgateUserAgentPlatform {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String android() {
            return WebgateUserAgentPlatform.android();
        }

        public final String ios() {
            return WebgateUserAgentPlatform.ios();
        }

        public final String linux() {
            return WebgateUserAgentPlatform.linux();
        }

        public final String osxArm64() {
            return WebgateUserAgentPlatform.osxArm64();
        }

        public final String osxX64() {
            return WebgateUserAgentPlatform.osxX64();
        }

        public final String windowsArm64() {
            return WebgateUserAgentPlatform.windowsArm64();
        }

        public final String windowsX86() {
            return WebgateUserAgentPlatform.windowsX86();
        }

        public final String windowsX86_64() {
            return WebgateUserAgentPlatform.windowsX86_64();
        }
    }

    public static final native String android();

    public static final native String ios();

    public static final native String linux();

    public static final native String osxArm64();

    public static final native String osxX64();

    public static final native String windowsArm64();

    public static final native String windowsX86();

    public static final native String windowsX86_64();
}
