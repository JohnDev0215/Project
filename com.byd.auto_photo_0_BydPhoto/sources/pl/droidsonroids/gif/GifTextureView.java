package pl.droidsonroids.gif;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Parcelable;
import android.view.Surface;
import android.view.TextureView;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.lang.ref.WeakReference;
import pl.droidsonroids.gif.f;

/* JADX INFO: loaded from: classes2.dex */
public class GifTextureView extends TextureView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ImageView.ScaleType[] f3177a = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ImageView.ScaleType f3178b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f3179c;
    private g d;
    private c e;
    private float f;
    private f.b g;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3180a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            f3180a = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3180a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3180a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3180a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3180a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3180a[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3180a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3180a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public interface b {
        void a(Canvas canvas);
    }

    private static class c extends Thread implements TextureView.SurfaceTextureListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final pl.droidsonroids.gif.b f3181a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private GifInfoHandle f3182b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private IOException f3183c;
        long[] d;
        private final WeakReference<GifTextureView> e;

        c(GifTextureView gifTextureView) {
            super("GifRenderThread");
            this.f3181a = new pl.droidsonroids.gif.b();
            this.f3182b = new GifInfoHandle();
            this.e = new WeakReference<>(gifTextureView);
        }

        void c(@NonNull GifTextureView gifTextureView, @Nullable b bVar) {
            this.f3181a.a();
            gifTextureView.setSuperSurfaceTextureListener(bVar != null ? new j(bVar) : null);
            this.f3182b.p();
            interrupt();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            GifTextureView gifTextureView = this.e.get();
            if (gifTextureView != null) {
                gifTextureView.f(this.f3182b);
            }
            this.f3181a.b();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.f3181a.a();
            this.f3182b.p();
            interrupt();
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                GifTextureView gifTextureView = this.e.get();
                if (gifTextureView == null) {
                    return;
                }
                g unused = gifTextureView.d;
                throw null;
            } catch (IOException e) {
                this.f3183c = e;
            }
        }
    }

    private void d() {
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (surfaceTexture != null) {
            Surface surface = new Surface(surfaceTexture);
            try {
                surface.unlockCanvasAndPost(surface.lockCanvas(null));
            } finally {
                surface.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void f(GifInfoHandle gifInfoHandle) {
        Matrix.ScaleToFit scaleToFit;
        Matrix matrix = new Matrix();
        float width = getWidth();
        float height = getHeight();
        float fK = gifInfoHandle.k() / width;
        float fE = gifInfoHandle.e() / height;
        RectF rectF = new RectF(0.0f, 0.0f, gifInfoHandle.k(), gifInfoHandle.e());
        RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
        float fMin = 1.0f;
        switch (a.f3180a[this.f3178b.ordinal()]) {
            case 1:
                matrix.setScale(fK, fE, width / 2.0f, height / 2.0f);
                super.setTransform(matrix);
                break;
            case 2:
                fMin = 1.0f / Math.min(fK, fE);
                matrix.setScale(fK * fMin, fMin * fE, width / 2.0f, height / 2.0f);
                super.setTransform(matrix);
                break;
            case 3:
                if (gifInfoHandle.k() > width || gifInfoHandle.e() > height) {
                    fMin = Math.min(1.0f / fK, 1.0f / fE);
                }
                matrix.setScale(fK * fMin, fMin * fE, width / 2.0f, height / 2.0f);
                super.setTransform(matrix);
                break;
            case 4:
                scaleToFit = Matrix.ScaleToFit.CENTER;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(fK, fE);
                super.setTransform(matrix);
                break;
            case 5:
                scaleToFit = Matrix.ScaleToFit.END;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(fK, fE);
                super.setTransform(matrix);
                break;
            case 6:
                scaleToFit = Matrix.ScaleToFit.START;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(fK, fE);
                super.setTransform(matrix);
                break;
            case 7:
                break;
            case 8:
                matrix.set(this.f3179c);
                matrix.preScale(fK, fE);
                super.setTransform(matrix);
                break;
            default:
                super.setTransform(matrix);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSuperSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        super.setSurfaceTextureListener(surfaceTextureListener);
    }

    public synchronized void e(@Nullable g gVar, @Nullable b bVar) {
        this.e.c(this, bVar);
        try {
            this.e.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c cVar = new c(this);
        this.e = cVar;
        if (gVar != null) {
            cVar.start();
        } else {
            d();
        }
    }

    @Nullable
    public IOException getIOException() {
        return this.e.f3183c != null ? this.e.f3183c : GifIOException.a(this.e.f3182b.g());
    }

    public ImageView.ScaleType getScaleType() {
        return this.f3178b;
    }

    @Override // android.view.TextureView
    public TextureView.SurfaceTextureListener getSurfaceTextureListener() {
        return null;
    }

    @Override // android.view.TextureView
    public Matrix getTransform(Matrix matrix) {
        if (matrix == null) {
            matrix = new Matrix();
        }
        matrix.set(this.f3179c);
        return matrix;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.e.c(this, null);
        super.onDetachedFromWindow();
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        GifViewSavedState gifViewSavedState = (GifViewSavedState) parcelable;
        super.onRestoreInstanceState(gifViewSavedState.getSuperState());
        this.e.d = gifViewSavedState.f3184a[0];
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        c cVar = this.e;
        cVar.d = cVar.f3182b.j();
        return new GifViewSavedState(super.onSaveInstanceState(), this.g.f3198a ? this.e.d : null);
    }

    public void setFreezesAnimation(boolean z) {
        this.g.f3198a = z;
    }

    public void setImageMatrix(Matrix matrix) {
        setTransform(matrix);
    }

    public synchronized void setInputSource(@Nullable g gVar) {
        e(gVar, null);
    }

    @Override // android.view.TextureView
    public void setOpaque(boolean z) {
        if (z != isOpaque()) {
            super.setOpaque(z);
            setInputSource(this.d);
        }
    }

    public void setScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.f3178b = scaleType;
        f(this.e.f3182b);
    }

    public void setSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        this.f = f;
        this.e.f3182b.y(f);
    }

    @Override // android.view.TextureView
    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        throw new UnsupportedOperationException("Changing SurfaceTexture is not supported");
    }

    @Override // android.view.TextureView
    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        throw new UnsupportedOperationException("Changing SurfaceTextureListener is not supported");
    }

    @Override // android.view.TextureView
    public void setTransform(Matrix matrix) {
        this.f3179c.set(matrix);
        f(this.e.f3182b);
    }
}
