package com.spotify.localfiles.localfilesimpl;

import com.spotify.localfiles.localfiles.LocalFilesDelegate;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeLocalFilesDelegate implements LocalFilesDelegate {
    public static final Companion Companion = new Companion(null);
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeLocalFilesDelegate create(Object obj, Object obj2, Object obj3) {
            return NativeLocalFilesDelegate.create(obj, obj2, obj3);
        }

        public final NativeLocalFilesDelegate noop() {
            return NativeLocalFilesDelegate.noop();
        }
    }

    private NativeLocalFilesDelegate() {
    }

    public static final native NativeLocalFilesDelegate create(Object obj, Object obj2, Object obj3);

    public static final native NativeLocalFilesDelegate noop();

    public final native void destroy();
}
