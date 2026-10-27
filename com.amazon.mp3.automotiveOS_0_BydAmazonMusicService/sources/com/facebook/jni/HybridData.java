package com.facebook.jni;

import com.facebook.jni.DestructorThread;
import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

/* JADX INFO: loaded from: classes5.dex */
@DoNotStrip
public class HybridData {

    @DoNotStrip
    private Destructor mDestructor = new Destructor(this);

    static {
        NativeLoader.loadLibrary("fbjni");
    }

    public synchronized void resetNative() {
        this.mDestructor.destruct();
    }

    public boolean isValid() {
        return this.mDestructor.mNativePointer != 0;
    }

    public static class Destructor extends DestructorThread.Destructor {

        @DoNotStrip
        private volatile long mNativePointer;

        static native void deleteNative(long j);

        Destructor(Object obj) {
            super(obj);
        }

        @Override // com.facebook.jni.DestructorThread.Destructor
        protected final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0L;
        }
    }
}
