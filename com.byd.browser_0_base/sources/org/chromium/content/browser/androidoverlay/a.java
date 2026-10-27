package org.chromium.content.browser.androidoverlay;

import J.N;
import android.app.Dialog;
import android.os.IBinder;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import org.chromium.base.ThreadUtils;
import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: DialogOverlayCore.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a {
    public b a;
    public Dialog b;
    public SurfaceHolderCallback2C0149a c;
    public WindowManager.LayoutParams d;
    public boolean e;

    /* JADX INFO: compiled from: DialogOverlayCore.java */
    public interface b {
    }

    public final boolean a(Rect rect) {
        WindowManager.LayoutParams layoutParams = this.d;
        int i = layoutParams.x;
        int i2 = rect.b;
        if (i == i2 && layoutParams.y == rect.c && layoutParams.width == rect.d && layoutParams.height == rect.e) {
            return false;
        }
        layoutParams.x = i2;
        layoutParams.y = rect.c;
        layoutParams.width = rect.d;
        layoutParams.height = rect.e;
        return true;
    }

    public final void b() {
        Dialog dialog = this.b;
        if (dialog != null && dialog.isShowing()) {
            try {
                this.b.dismiss();
            } catch (Exception unused) {
                Log.w("cr_DSCore", "Failed to dismiss overlay dialog.  \"WindowLeaked\" is ignorable.");
            }
        }
        this.b = null;
        this.c = null;
    }

    public final void c(IBinder iBinder) {
        b bVar;
        WindowManager.LayoutParams layoutParams;
        IBinder iBinder2;
        Dialog dialog = this.b;
        if (dialog == null || (bVar = this.a) == null) {
            return;
        }
        if (iBinder == null || !((iBinder2 = (layoutParams = this.d).token) == null || iBinder == iBinder2)) {
            DialogOverlayImpl dialogOverlayImpl = (DialogOverlayImpl) bVar;
            Object obj = ThreadUtils.a;
            if (dialogOverlayImpl.g != null) {
                dialogOverlayImpl.C();
                dialogOverlayImpl.t();
            }
            this.a = null;
            b();
            return;
        }
        if (iBinder2 == iBinder) {
            return;
        }
        layoutParams.token = iBinder;
        dialog.getWindow().setAttributes(this.d);
        this.c = new SurfaceHolderCallback2C0149a();
        this.b.getWindow().takeSurface(this.c);
        this.b.show();
    }

    /* JADX INFO: renamed from: org.chromium.content.browser.androidoverlay.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DialogOverlayCore.java */
    public class SurfaceHolderCallback2C0149a implements SurfaceHolder.Callback2 {
        public SurfaceHolderCallback2C0149a() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceCreated(SurfaceHolder surfaceHolder) {
            b bVar;
            a aVar = a.this;
            if (aVar.b == null || (bVar = aVar.a) == null) {
                return;
            }
            Surface surface = surfaceHolder.getSurface();
            DialogOverlayImpl dialogOverlayImpl = (DialogOverlayImpl) bVar;
            dialogOverlayImpl.getClass();
            Object obj = ThreadUtils.a;
            if (dialogOverlayImpl.g == null || dialogOverlayImpl.e == null) {
                return;
            }
            int iMpcpmTlm = N.MpcpmTlm(surface);
            dialogOverlayImpl.i = iMpcpmTlm;
            dialogOverlayImpl.e.o(iMpcpmTlm);
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            b bVar;
            a aVar = a.this;
            if (aVar.b == null || (bVar = aVar.a) == null) {
                return;
            }
            DialogOverlayImpl dialogOverlayImpl = (DialogOverlayImpl) bVar;
            Object obj = ThreadUtils.a;
            if (dialogOverlayImpl.g != null) {
                dialogOverlayImpl.C();
                dialogOverlayImpl.t();
            }
            aVar.a = null;
        }

        @Override // android.view.SurfaceHolder.Callback2
        public final void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }
    }
}
