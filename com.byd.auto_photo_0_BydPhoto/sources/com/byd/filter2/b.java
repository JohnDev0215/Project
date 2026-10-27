package com.byd.filter2;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static void a(int i, int i2) {
        GLES20.glBindFramebuffer(36160, i);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i2, 0);
    }

    public static void b(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            Log.i("m_tag", str + ": photo GL code : 0x" + Integer.toHexString(iGlGetError));
        }
    }

    public static void c(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2) {
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenRenderbuffers(1, iArr3, 0);
        GLES20.glGenTextures(iArr2.length, iArr2, 0);
        for (int i3 : iArr2) {
            GLES20.glBindTexture(3553, i3);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10241, 9729);
        }
        GLES20.glBindFramebuffer(36160, iArr[0]);
        GLES20.glBindRenderbuffer(36161, iArr3[0]);
        GLES20.glRenderbufferStorage(36161, 33189, i, i2);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr2[0], 0);
        GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, iArr3[0]);
        f();
    }

    public static void d(int i, int[] iArr, int i2, int i3, int i4, int i5) {
        GLES20.glGenTextures(i, iArr, i2);
        for (int i6 = 0; i6 < i; i6++) {
            GLES20.glBindTexture(3553, iArr[i6]);
            GLES20.glTexImage2D(3553, 0, i3, i4, i5, 0, i3, 5121, null);
            h();
        }
        GLES20.glBindTexture(3553, 0);
    }

    public static int e(Bitmap bitmap, int[] iArr, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (iArr[0] == 0) {
            GLES30.glGenTextures(1, iArr, 0);
            if (iArr[0] == 0) {
                Log.e("m_tag", "loadBitmapTexture: glGenTextures is 0");
                return -1;
            }
        }
        GLES30.glBindTexture(3553, iArr[0]);
        GLES30.glTexParameterf(3553, 10241, 9728.0f);
        GLES30.glTexParameterf(3553, 10240, 9729.0f);
        GLES30.glTexParameterf(3553, 10242, 33071.0f);
        GLES30.glTexParameterf(3553, 10243, 33071.0f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        if (z) {
            bitmap.recycle();
        }
        GLES30.glGenerateMipmap(3553);
        GLES30.glBindTexture(3553, 0);
        return iArr[0];
    }

    public static void f() {
        GLES20.glBindRenderbuffer(36161, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    public static void g() {
        GLES20.glBindFramebuffer(36160, 0);
    }

    public static void h() {
        GLES20.glTexParameterf(3553, 10241, 9728.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
    }
}
