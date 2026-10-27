package com.google.android.gles_jni;

import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: classes5.dex */
public class EGLDisplayImpl extends EGLDisplay {
    long mEGLDisplay;

    public EGLDisplayImpl(long j) {
        this.mEGLDisplay = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mEGLDisplay == ((EGLDisplayImpl) obj).mEGLDisplay;
    }

    public int hashCode() {
        long j = this.mEGLDisplay;
        return 527 + ((int) (j ^ (j >>> 32)));
    }
}
