package a.b.a.a.o.e;

import a.b.a.a.n.b;
import a.b.a.a.o.e.b;
import a.b.a.a.o.g.b;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class g implements a.b.a.a.o.e.b {
    public Surface b;
    public ISyeDisplaySettings c;
    public final TextureView e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a.b.a.a.i.b f135a = a.b.a.a.i.b.d.a("TextureViewDisplaySurf", b.a.Video);
    public final h d = new h(0, 0);

    public abstract class a implements TextureView.SurfaceTextureListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextureView.SurfaceTextureListener f136a;
        public final TextureView b;

        public a(g gVar, TextureView textureView) {
            this.b = textureView;
            this.f136a = textureView.getSurfaceTextureListener();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            c cVar = (c) this;
            cVar.d.a(g.this.a(surfaceTexture));
            this.b.setSurfaceTextureListener(this.f136a);
            TextureView.SurfaceTextureListener surfaceTextureListener = this.f136a;
            if (surfaceTextureListener != null) {
                surfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, i, i2);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            TextureView.SurfaceTextureListener surfaceTextureListener = this.f136a;
            if (surfaceTextureListener != null) {
                return surfaceTextureListener.onSurfaceTextureDestroyed(surfaceTexture);
            }
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            TextureView.SurfaceTextureListener surfaceTextureListener = this.f136a;
            if (surfaceTextureListener != null) {
                surfaceTextureListener.onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            TextureView.SurfaceTextureListener surfaceTextureListener = this.f136a;
            if (surfaceTextureListener != null) {
                surfaceTextureListener.onSurfaceTextureUpdated(surfaceTexture);
            }
        }
    }

    public static final class b implements TextureView.SurfaceTextureListener {
        public b() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            g gVar = g.this;
            ISyeDisplaySettings.VideoGravity gravity = gVar.c.getGravity();
            Handler handler = gVar.e.getHandler();
            if (handler != null) {
                handler.post(gVar.new d(gravity));
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    public static final class c extends a {
        public final /* synthetic */ b.a d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(b.a aVar, TextureView textureView) {
            super(g.this, textureView);
            this.d = aVar;
        }
    }

    public static final class d implements Runnable {
        public final /* synthetic */ ISyeDisplaySettings.VideoGravity b;

        public d(ISyeDisplaySettings.VideoGravity videoGravity) {
            this.b = videoGravity;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i;
            int i2;
            a.b.a.a.o.e.d dVar;
            float fMin;
            ISyeDisplaySettings.VideoGravity videoGravity = this.b;
            int width = g.this.e.getWidth();
            int height = g.this.e.getHeight();
            h hVar = g.this.d;
            synchronized (hVar) {
                i = hVar.f139a;
            }
            h hVar2 = g.this.d;
            synchronized (hVar2) {
                i2 = hVar2.b;
            }
            if (width == 0 || height == 0 || i == 0 || i2 == 0) {
                dVar = null;
            } else {
                float f = i;
                float f2 = i2;
                float f3 = width / f;
                float f4 = height / f2;
                int iOrdinal = videoGravity.ordinal();
                if (iOrdinal == 0) {
                    fMin = Math.min(f3, f4);
                } else {
                    if (iOrdinal != 1) {
                        throw new NoWhenBranchMatchedException();
                    }
                    fMin = Math.max(f3, f4);
                }
                dVar = new a.b.a.a.o.e.d(fMin / f3, fMin / f4, (f * (f3 - fMin)) / 2.0f, (f2 * (f4 - fMin)) / 2.0f);
            }
            if (dVar != null) {
                Matrix matrix = new Matrix();
                matrix.setScale(dVar.f131a, dVar.b);
                matrix.postTranslate(dVar.c, dVar.d);
                g.this.e.setTransform(matrix);
                a.b.a.a.i.b bVar = g.this.f135a;
                StringBuilder sb = new StringBuilder();
                sb.append("resizeSurfaceView ");
                sb.append(this.b == ISyeDisplaySettings.VideoGravity.ASPECT_FILL ? "aspect fill" : "aspect fit");
                sb.append(":\n");
                sb.append("view=");
                sb.append(g.this.e.getWidth());
                sb.append('x');
                sb.append(g.this.e.getHeight());
                sb.append('\n');
                sb.append("video=");
                sb.append(g.this.d);
                sb.append('\n');
                sb.append("newViewScale=");
                sb.append(dVar.f131a);
                sb.append('x');
                sb.append(dVar.b);
                sb.append('\n');
                sb.append("newViewTranslate=");
                sb.append(dVar.c);
                sb.append('x');
                sb.append(dVar.d);
                bVar.d("TextureViewDisplaySurf", sb.toString());
            }
        }
    }

    public g(TextureView textureView, ISyeDisplaySettings iSyeDisplaySettings) {
        this.e = textureView;
        this.c = iSyeDisplaySettings;
    }

    public final Surface a(SurfaceTexture surfaceTexture) {
        Surface surface;
        Surface surface2 = this.b;
        if (surface2 != null && surface2.isValid() && (surface = this.b) != null) {
            surface.release();
        }
        Surface surface3 = new Surface(surfaceTexture);
        this.b = surface3;
        return surface3;
    }

    public final void a(ISyeDisplaySettings.VideoGravity videoGravity) {
        Handler handler = this.e.getHandler();
        if (handler != null) {
            handler.post(new d(videoGravity));
        }
    }

    @Override // a.b.a.a.o.e.b
    public View getContainingView() {
        return this.e;
    }

    @Override // a.b.a.a.o.e.b
    public ISyeDisplaySettings getDisplaySettings() {
        return this.c;
    }

    @Override // a.b.a.a.o.e.b
    public a.b.a.a.o.e.c getResolution() {
        return new h(this.e.getWidth(), this.e.getHeight());
    }

    @Override // a.b.a.a.o.e.b
    public void onVideoStreamSizeChanged(int i, int i2) {
        this.f135a.a("TextureViewDisplaySurf", "onVideoStreamSizeChanged: " + i + " x " + i2);
        h hVar = this.d;
        synchronized (hVar) {
            hVar.f139a = i;
            hVar.b = i2;
        }
        a(this.c.getGravity());
    }

    @Override // a.b.a.a.o.e.b
    public void setDisplaySettings(ISyeDisplaySettings iSyeDisplaySettings) {
        this.c = iSyeDisplaySettings;
        a(iSyeDisplaySettings.getGravity());
    }

    @Override // a.b.a.a.o.e.b
    public void setSurfaceReceiver(b.a aVar) {
        if (aVar != null) {
            this.e.setSurfaceTextureListener(new b());
            if (!this.e.isAvailable()) {
                TextureView textureView = this.e;
                textureView.setSurfaceTextureListener(new c(aVar, textureView));
            } else {
                SurfaceTexture surfaceTexture = this.e.getSurfaceTexture();
                Intrinsics.checkExpressionValueIsNotNull(surfaceTexture, "textureView.surfaceTexture");
                ((b.a) aVar).a(a(surfaceTexture));
            }
        }
    }

    @Override // a.b.a.a.o.e.b
    public void teardown() {
    }
}
