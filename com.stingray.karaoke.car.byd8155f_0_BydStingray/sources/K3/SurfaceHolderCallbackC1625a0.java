package k3;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;

/* JADX INFO: renamed from: k3.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class SurfaceHolderCallbackC1625a0 implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener {
    public final /* synthetic */ C1628b0 l;

    public SurfaceHolderCallbackC1625a0(C1628b0 c1628b0) {
        this.l = c1628b0;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i7, int i10) {
        C1628b0 c1628b0 = this.l;
        TextureView textureView = c1628b0.f17924y;
        if (textureView == null || textureView.getSurfaceTexture() != surfaceTexture) {
            return;
        }
        c1628b0.f17922w = new Surface(surfaceTexture);
        c1628b0.b1(new Z(this, 2));
        c1628b0.l1(i7, i10);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        C1628b0 c1628b0 = this.l;
        TextureView textureView = c1628b0.f17924y;
        if (textureView != null && textureView.getSurfaceTexture() == surfaceTexture) {
            c1628b0.f17922w = null;
            c1628b0.b1(new Z(this, 3));
            c1628b0.l1(0, 0);
        }
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i7, int i10) {
        C1628b0 c1628b0 = this.l;
        TextureView textureView = c1628b0.f17924y;
        if (textureView == null || textureView.getSurfaceTexture() != surfaceTexture) {
            return;
        }
        c1628b0.l1(i7, i10);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i7, int i10, int i11) {
        C1628b0 c1628b0 = this.l;
        if (c1628b0.f17923x != surfaceHolder) {
            return;
        }
        c1628b0.l1(i10, i11);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        C1628b0 c1628b0 = this.l;
        if (c1628b0.f17923x != surfaceHolder) {
            return;
        }
        c1628b0.f17922w = surfaceHolder.getSurface();
        c1628b0.b1(new Z(this, 0));
        Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
        c1628b0.l1(surfaceFrame.width(), surfaceFrame.height());
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        C1628b0 c1628b0 = this.l;
        if (c1628b0.f17923x != surfaceHolder) {
            return;
        }
        c1628b0.f17922w = null;
        c1628b0.b1(new Z(this, 1));
        c1628b0.l1(0, 0);
    }
}
