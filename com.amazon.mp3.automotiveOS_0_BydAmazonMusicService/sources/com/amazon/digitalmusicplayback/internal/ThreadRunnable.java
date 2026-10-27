package com.amazon.digitalmusicplayback.internal;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ThreadRunnable {
    public abstract void run();

    public static String currentThreadLoggingTag() {
        return CppProxy.currentThreadLoggingTag();
    }

    private static final class CppProxy extends ThreadRunnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        public static native String currentThreadLoggingTag();

        private native void nativeDestroy(long j);

        private native void native_run(long j);

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
        }

        public void _djinni_private_destroy() {
            if (this.destroyed.getAndSet(true)) {
                return;
            }
            nativeDestroy(this.nativeRef);
        }

        protected void finalize() throws Throwable {
            _djinni_private_destroy();
            super.finalize();
        }

        @Override // com.amazon.digitalmusicplayback.internal.ThreadRunnable
        public void run() {
            native_run(this.nativeRef);
        }
    }
}
