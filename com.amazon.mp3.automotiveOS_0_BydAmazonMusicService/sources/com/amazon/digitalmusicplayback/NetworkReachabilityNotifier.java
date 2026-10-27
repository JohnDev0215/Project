package com.amazon.digitalmusicplayback;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NetworkReachabilityNotifier {
    public abstract void onNetworkReachabilityChanged(boolean z);

    public abstract void onNetworkSourceChanged(String str);

    private static final class CppProxy extends NetworkReachabilityNotifier {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        private native void nativeDestroy(long j);

        private native void native_onNetworkReachabilityChanged(long j, boolean z);

        private native void native_onNetworkSourceChanged(long j, String str);

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

        @Override // com.amazon.digitalmusicplayback.NetworkReachabilityNotifier
        public void onNetworkReachabilityChanged(boolean z) {
            native_onNetworkReachabilityChanged(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.NetworkReachabilityNotifier
        public void onNetworkSourceChanged(String str) {
            native_onNetworkSourceChanged(this.nativeRef, str);
        }
    }
}
