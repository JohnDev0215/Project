package defpackage;

import android.graphics.Bitmap;
import android.graphics.HardwareRenderer;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.view.Surface;
import android.view.View;
import defpackage.du0;
import defpackage.pn3;
import java.nio.ByteBuffer;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: HardwareDraw.java */
/* JADX INFO: loaded from: classes3.dex */
public final class du0 implements pn3.a {
    public static Handler e;
    public final ThreadUtils.a a = new ThreadUtils.a();
    public a b;
    public boolean c;
    public b d;

    /* JADX INFO: compiled from: HardwareDraw.java */
    public class a implements ImageReader.OnImageAvailableListener {
        public int a;
        public ImageReader b;
        public C0056a d;
        public int c = 0;
        public final oi2 e = PostTask.a(d73.h);

        /* JADX INFO: renamed from: du0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: HardwareDraw.java */
        public class C0056a {
            public int a;
            public Bitmap b;
            public boolean c = true;

            public C0056a(int i, int i2, int i3, Bitmap bitmap) {
                this.a = i3;
                this.b = bitmap;
            }
        }

        public a(int i, int i2) {
            a(i, i2);
        }

        public final void a(int i, int i2) {
            TraceEvent traceEventH0 = TraceEvent.H0("AcceleratedImageReader::init", null);
            try {
                ImageReader imageReader = this.b;
                if (imageReader != null) {
                    imageReader.close();
                }
                if (i != 0 && i2 != 0) {
                    ImageReader imageReaderNewInstance = ImageReader.newInstance(i, i2, 1, 3);
                    this.b = imageReaderNewInstance;
                    imageReaderNewInstance.setOnImageAvailableListener(this, du0.e);
                    this.d = new C0056a(0, 0, 0, null);
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public final void onImageAvailable(ImageReader imageReader) {
            TraceEvent traceEventH0 = TraceEvent.H0("AcceleratedImageReader::onImageAvailable", null);
            try {
                final Image imageAcquireLatestImage = imageReader.acquireLatestImage();
                if (imageAcquireLatestImage == null) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                }
                final b bVar = du0.this.d;
                int i = bVar.a;
                if (i == this.a) {
                    imageAcquireLatestImage.close();
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                }
                this.a = i;
                final Image.Plane[] planes = imageAcquireLatestImage.getPlanes();
                final ByteBuffer buffer = planes[0].getBuffer();
                this.e.b(new Runnable() { // from class: bu0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Image image = imageAcquireLatestImage;
                        Image.Plane[] planeArr = planes;
                        ByteBuffer byteBuffer = buffer;
                        du0.b bVar2 = bVar;
                        du0.a aVar = this.e;
                        aVar.getClass();
                        TraceEvent traceEventH02 = TraceEvent.H0("AcceleratedImageReader::onImageAvailable::postTask", null);
                        try {
                            int width = image.getWidth();
                            int height = image.getHeight();
                            int pixelStride = planeArr[0].getPixelStride();
                            int rowStride = (planeArr[0].getRowStride() - (pixelStride * width)) / pixelStride;
                            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width + rowStride, height, Bitmap.Config.ARGB_8888);
                            bitmapCreateBitmap.setHasAlpha(true);
                            bitmapCreateBitmap.copyPixelsFromBuffer(byteBuffer);
                            image.close();
                            bVar2.b.getHandler().post(new gt2(aVar, width, height, rowStride, bitmapCreateBitmap, bVar2));
                            if (traceEventH02 != null) {
                                traceEventH02.close();
                            }
                        } catch (Throwable th) {
                            if (traceEventH02 != null) {
                                try {
                                    traceEventH02.close();
                                } catch (Throwable unused) {
                                }
                            }
                            throw th;
                        }
                    }
                });
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: HardwareDraw.java */
    public static class b {
        public final int a;
        public final View b;
        public final float c;
        public final Callback<Bitmap> d;

        public b(int i, View view, float f, Callback<Bitmap> callback) {
            this.a = i;
            this.b = view;
            this.c = f;
            this.d = callback;
        }
    }

    public du0() {
        if (e == null) {
            e = new Handler(C0173a.h("HardwareDrawThread").getLooper());
        }
    }

    @Override // pn3.a
    public final boolean a() {
        return true;
    }

    @Override // pn3.a
    public final void b() {
        a.C0056a c0056a;
        a aVar = this.b;
        if (aVar == null || (c0056a = aVar.d) == null) {
            return;
        }
        c0056a.b = null;
    }

    @Override // pn3.a
    public final void c(View view, float f) {
        final int width = (int) (view.getWidth() * f);
        final int height = (int) (view.getHeight() * f);
        final a aVar = this.b;
        if (aVar == null) {
            this.b = new a(width, height);
        } else {
            aVar.e.b(new Runnable() { // from class: au0
                @Override // java.lang.Runnable
                public final void run() {
                    aVar.a(width, height);
                }
            });
        }
    }

    @Override // pn3.a
    public final boolean d(View view, Rect rect, float f, fm fmVar, l51 l51Var) {
        TraceEvent traceEventH0 = TraceEvent.H0("HardwareDraw:syncCaptureBitmap", null);
        try {
            boolean zE = e(view, rect, f, fmVar, l51Var);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return zE;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final boolean e(final View view, Rect rect, final float f, fm fmVar, final l51 l51Var) {
        boolean z;
        TraceEvent traceEventH0 = TraceEvent.H0("HardwareDraw:captureWithHardwareDraw", null);
        try {
            if (view.getWidth() != 0 && view.getHeight() != 0) {
                a.C0056a c0056a = this.b.d;
                if (!c0056a.c || rect.isEmpty()) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    return false;
                }
                final RenderNode renderNode = new RenderNode("bitmapRenderNode");
                renderNode.setPosition(0, 0, view.getWidth(), view.getHeight());
                if (hm.a(renderNode.beginRecording(), view, rect, f, false, fmVar)) {
                    z = true;
                } else {
                    TraceEvent.R("HardwareDraw::DrawAttemptedWhileDetached");
                    if (!this.c) {
                        this.c = true;
                        view.addOnAttachStateChangeListener(new zt0(this));
                    }
                    z = false;
                }
                renderNode.endRecording();
                if (z) {
                    c0056a.c = false;
                    final a aVar = this.b;
                    du0.this.a.getClass();
                    int i = aVar.c;
                    if (i == 0) {
                        aVar.c = 1;
                    } else if (i != 1) {
                        if (i == 2) {
                            aVar.c = 3;
                        } else if (i != 3) {
                        }
                    }
                    aVar.e.b(new Runnable() { // from class: cu0
                        @Override // java.lang.Runnable
                        public final void run() {
                            View view2 = view;
                            float f2 = f;
                            Callback callback = l51Var;
                            RenderNode renderNode2 = renderNode;
                            du0.a aVar2 = aVar;
                            aVar2.getClass();
                            HardwareRenderer hardwareRenderer = new HardwareRenderer();
                            TraceEvent traceEventH02 = TraceEvent.H0("AcceleratedImageReader::requestDraw", null);
                            try {
                                du0 du0Var = du0.this;
                                du0.b bVar = du0Var.d;
                                int i2 = 1;
                                if (bVar != null) {
                                    i2 = 1 + bVar.a;
                                }
                                du0Var.d = new du0.b(i2, view2, f2, callback);
                                Surface surface = aVar2.b.getSurface();
                                hardwareRenderer.setContentRoot(renderNode2);
                                hardwareRenderer.setSurface(surface);
                                hardwareRenderer.createRenderRequest().syncAndDraw();
                                hardwareRenderer.destroy();
                                if (traceEventH02 != null) {
                                    traceEventH02.close();
                                }
                            } catch (Throwable th) {
                                if (traceEventH02 != null) {
                                    try {
                                        traceEventH02.close();
                                    } catch (Throwable unused) {
                                    }
                                }
                                throw th;
                            }
                        }
                    });
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return z;
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return false;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
