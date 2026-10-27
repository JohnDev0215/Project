package z2;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: z2.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2761g extends Surface {
    public static int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static boolean f24314p;
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HandlerThreadC2760f f24315m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f24316n;

    public C2761g(HandlerThreadC2760f handlerThreadC2760f, SurfaceTexture surfaceTexture, boolean z6) {
        super(surfaceTexture);
        this.f24315m = handlerThreadC2760f;
        this.l = z6;
    }

    public static synchronized boolean a(Context context) {
        String strEglQueryString;
        int i7;
        try {
            if (!f24314p) {
                int i10 = AbstractC1343A.f15195a;
                if (i10 >= 24 && ((i10 >= 26 || !("samsung".equals(AbstractC1343A.c) || "XT1650".equals(AbstractC1343A.d))) && ((i10 >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) && (strEglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) != null && strEglQueryString.contains("EGL_EXT_protected_content")))) {
                    String strEglQueryString2 = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
                    i7 = strEglQueryString2 != null && strEglQueryString2.contains("EGL_KHR_surfaceless_context") ? 1 : 2;
                } else {
                    i7 = 0;
                }
                o = i7;
                f24314p = true;
            }
        } catch (Throwable th) {
            throw th;
        }
        return o != 0;
    }

    @Override // android.view.Surface
    public final void release() {
        super.release();
        synchronized (this.f24315m) {
            try {
                if (!this.f24316n) {
                    HandlerThreadC2760f handlerThreadC2760f = this.f24315m;
                    handlerThreadC2760f.f24311m.getClass();
                    handlerThreadC2760f.f24311m.sendEmptyMessage(2);
                    this.f24316n = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
