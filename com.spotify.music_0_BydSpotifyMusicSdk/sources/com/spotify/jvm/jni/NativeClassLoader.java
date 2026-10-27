package com.spotify.jvm.jni;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NativeClassLoader {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void setClassLoader(ClassLoader classLoader) {
            NativeClassLoader.setClassLoader(classLoader);
        }

        public final void load() {
            ClassLoader classLoader = NativeClassLoader.class.getClassLoader();
            v03.i(classLoader, "NativeClassLoader::class.java.getClassLoader()");
            setClassLoader(classLoader);
        }
    }

    public static final void load() {
        Companion.load();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void setClassLoader(ClassLoader classLoader);
}
