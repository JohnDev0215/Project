package com.amazon.digitalmusicplayback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class HttpResponseHandlerWrapper {
    public static final String BADURL = "kBadUrl";
    public static final String CANCELLED = "kHttpRequestCancelled";
    public static final String CONNECTIONLOST = "kHttpConnectionLost";
    public static final String TIMEOUT = "kHttpRequestTimeout";
    public static final String UNKNOWNHOST = "kUnknownHost";

    public abstract int getDesiredOnBodyDataBufferSize();

    public abstract void onBodyData(byte[] bArr, int i);

    public abstract void onComplete();

    public abstract void onError(String str);

    public abstract void onResponseHeaders(HashMap<String, ArrayList<String>> map);

    public abstract void onResponseStatus(int i);

    public abstract void onStartBody();

    private static final class CppProxy extends HttpResponseHandlerWrapper {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        private native void nativeDestroy(long j);

        private native int native_getDesiredOnBodyDataBufferSize(long j);

        private native void native_onBodyData(long j, byte[] bArr, int i);

        private native void native_onComplete(long j);

        private native void native_onError(long j, String str);

        private native void native_onResponseHeaders(long j, HashMap<String, ArrayList<String>> map);

        private native void native_onResponseStatus(long j, int i);

        private native void native_onStartBody(long j);

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

        @Override // com.amazon.digitalmusicplayback.HttpResponseHandlerWrapper
        public void onResponseStatus(int i) {
            native_onResponseStatus(this.nativeRef, i);
        }

        @Override // com.amazon.digitalmusicplayback.HttpResponseHandlerWrapper
        public void onResponseHeaders(HashMap<String, ArrayList<String>> map) {
            native_onResponseHeaders(this.nativeRef, map);
        }

        @Override // com.amazon.digitalmusicplayback.HttpResponseHandlerWrapper
        public void onStartBody() {
            native_onStartBody(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.HttpResponseHandlerWrapper
        public void onBodyData(byte[] bArr, int i) {
            native_onBodyData(this.nativeRef, bArr, i);
        }

        @Override // com.amazon.digitalmusicplayback.HttpResponseHandlerWrapper
        public void onComplete() {
            native_onComplete(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.HttpResponseHandlerWrapper
        public void onError(String str) {
            native_onError(this.nativeRef, str);
        }

        @Override // com.amazon.digitalmusicplayback.HttpResponseHandlerWrapper
        public int getDesiredOnBodyDataBufferSize() {
            return native_getDesiredOnBodyDataBufferSize(this.nativeRef);
        }
    }
}
