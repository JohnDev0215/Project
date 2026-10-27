package com.android.systemui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.os.Trace;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceHolder;
import com.android.systemui.glwallpaper.EglHelper;
import com.android.systemui.glwallpaper.GLWallpaperRenderer;
import com.android.systemui.glwallpaper.ImageWallpaperRenderer;
import com.android.systemui.statusbar.phone.CollapsedStatusBarFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.function.Supplier;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class ImageWallpaper extends WallpaperService {
    private static final boolean DEBUG = false;
    private static final int DELAY_FINISH_RENDERING = 1000;
    private static final String TAG = "ImageWallpaper";
    private boolean mIsFirstFrame = true;
    private HandlerThread mWorker;

    @Inject
    public ImageWallpaper() {
    }

    @Override // android.service.wallpaper.WallpaperService, android.app.Service
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logV();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mWorker = handlerThread;
        handlerThread.start();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        BydLogHelper.logV();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.service.wallpaper.WallpaperService
    public WallpaperService.Engine onCreateEngine() {
        BydLogHelper.logV();
        return new GLEngine();
    }

    @Override // android.service.wallpaper.WallpaperService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        BydLogHelper.logV();
        this.mWorker.quitSafely();
        this.mWorker = null;
    }

    class GLEngine extends WallpaperService.Engine {
        static final int MIN_SURFACE_HEIGHT = 64;
        static final int MIN_SURFACE_WIDTH = 64;
        private EglHelper mEglHelper;
        private final Runnable mFinishRenderingTask;
        private boolean mNeedRedraw;
        private GLWallpaperRenderer mRenderer;

        public boolean shouldZoomOutWallpaper() {
            return true;
        }

        GLEngine() {
            super(ImageWallpaper.this);
            this.mFinishRenderingTask = new Runnable() { // from class: com.android.systemui.-$$Lambda$ImageWallpaper$GLEngine$89dxFU8sWSFIfynevrdTeLm68JA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.finishRendering();
                }
            };
        }

        GLEngine(Handler handler) {
            super(ImageWallpaper.this, new Supplier() { // from class: com.android.systemui.-$$Lambda$ImageWallpaper$GLEngine$OJ01g4C3-dIy7DJeZXaYLthdjxE
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Long.valueOf(SystemClock.elapsedRealtime());
                }
            }, handler);
            this.mFinishRenderingTask = new Runnable() { // from class: com.android.systemui.-$$Lambda$ImageWallpaper$GLEngine$89dxFU8sWSFIfynevrdTeLm68JA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.finishRendering();
                }
            };
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onCreate(SurfaceHolder surfaceHolder) {
            this.mEglHelper = getEglHelperInstance();
            this.mRenderer = getRendererInstance();
            setFixedSizeAllowed(true);
            setOffsetNotificationsEnabled(false);
            updateSurfaceSize();
        }

        EglHelper getEglHelperInstance() {
            return new EglHelper();
        }

        ImageWallpaperRenderer getRendererInstance() {
            return new ImageWallpaperRenderer(getDisplayContext());
        }

        private void updateSurfaceSize() {
            SurfaceHolder surfaceHolder = getSurfaceHolder();
            Size sizeReportSurfaceSize = this.mRenderer.reportSurfaceSize();
            surfaceHolder.setFixedSize(Math.max(64, sizeReportSurfaceSize.getWidth()), Math.max(64, sizeReportSurfaceSize.getHeight()));
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onDestroy() {
            ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable() { // from class: com.android.systemui.-$$Lambda$ImageWallpaper$GLEngine$10HKUvLUIAadVFoRCtID_en63ew
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDestroy$0$ImageWallpaper$GLEngine();
                }
            });
        }

        public /* synthetic */ void lambda$onDestroy$0$ImageWallpaper$GLEngine() {
            this.mRenderer.finish();
            this.mRenderer = null;
            this.mEglHelper.finish();
            this.mEglHelper = null;
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceCreated(final SurfaceHolder holder) {
            if (ImageWallpaper.this.mWorker == null) {
                return;
            }
            ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable() { // from class: com.android.systemui.-$$Lambda$ImageWallpaper$GLEngine$vW0L2Hq08IJJ4-3iI0T1LkL4Xqg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSurfaceCreated$1$ImageWallpaper$GLEngine(holder);
                }
            });
        }

        public /* synthetic */ void lambda$onSurfaceCreated$1$ImageWallpaper$GLEngine(SurfaceHolder surfaceHolder) {
            this.mEglHelper.init(surfaceHolder, needSupportWideColorGamut());
            this.mRenderer.onSurfaceCreated();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceChanged(SurfaceHolder holder, int format, final int width, final int height) {
            if (ImageWallpaper.this.mWorker == null) {
                return;
            }
            ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable() { // from class: com.android.systemui.-$$Lambda$ImageWallpaper$GLEngine$Oh5Kw_zR2mnb7kxAbpFdwbFHIRc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSurfaceChanged$2$ImageWallpaper$GLEngine(width, height);
                }
            });
        }

        public /* synthetic */ void lambda$onSurfaceChanged$2$ImageWallpaper$GLEngine(int i, int i2) {
            this.mRenderer.onSurfaceChanged(i, i2);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceRedrawNeeded(SurfaceHolder holder) {
            if (ImageWallpaper.this.mWorker == null) {
                return;
            }
            ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable() { // from class: com.android.systemui.-$$Lambda$ImageWallpaper$GLEngine$pbsssQPX5ut7HYUmX6nzVEdoVgo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.drawFrame();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawFrame() {
            if (ImageWallpaper.this.mIsFirstFrame) {
                EventBus.getDefault().post(new CollapsedStatusBarFragment.StatusBarEvent());
                ImageWallpaper.this.mIsFirstFrame = false;
            }
            preRender();
            requestRender();
            postRender();
        }

        public void preRender() {
            Trace.beginSection("ImageWallpaper#preRender");
            preRenderInternal();
            Trace.endSection();
        }

        private void preRenderInternal() {
            boolean z;
            Rect surfaceFrame = getSurfaceHolder().getSurfaceFrame();
            cancelFinishRenderingTask();
            if (this.mEglHelper.hasEglContext()) {
                z = false;
            } else {
                this.mEglHelper.destroyEglSurface();
                if (this.mEglHelper.createEglContext()) {
                    z = true;
                } else {
                    Log.w(ImageWallpaper.TAG, "recreate egl context failed!");
                    z = false;
                }
            }
            if (this.mEglHelper.hasEglContext() && !this.mEglHelper.hasEglSurface() && !this.mEglHelper.createEglSurface(getSurfaceHolder(), needSupportWideColorGamut())) {
                Log.w(ImageWallpaper.TAG, "recreate egl surface failed!");
            }
            if (this.mEglHelper.hasEglContext() && this.mEglHelper.hasEglSurface() && z) {
                this.mRenderer.onSurfaceCreated();
                this.mRenderer.onSurfaceChanged(surfaceFrame.width(), surfaceFrame.height());
            }
        }

        public void requestRender() {
            Trace.beginSection("ImageWallpaper#requestRender");
            requestRenderInternal();
            Trace.endSection();
        }

        private void requestRenderInternal() {
            Rect surfaceFrame = getSurfaceHolder().getSurfaceFrame();
            if (this.mEglHelper.hasEglContext() && this.mEglHelper.hasEglSurface() && surfaceFrame.width() > 0 && surfaceFrame.height() > 0) {
                this.mRenderer.onDrawFrame();
                if (this.mEglHelper.swapBuffer()) {
                    return;
                }
                Log.e(ImageWallpaper.TAG, "drawFrame failed!");
                return;
            }
            Log.e(ImageWallpaper.TAG, "requestRender: not ready, has context=" + this.mEglHelper.hasEglContext() + ", has surface=" + this.mEglHelper.hasEglSurface() + ", frame=" + surfaceFrame);
        }

        public void postRender() {
            Trace.beginSection("ImageWallpaper#postRender");
            scheduleFinishRendering();
            Trace.endSection();
        }

        private void cancelFinishRenderingTask() {
            if (ImageWallpaper.this.mWorker == null) {
                return;
            }
            ImageWallpaper.this.mWorker.getThreadHandler().removeCallbacks(this.mFinishRenderingTask);
        }

        private void scheduleFinishRendering() {
            if (ImageWallpaper.this.mWorker == null) {
                return;
            }
            cancelFinishRenderingTask();
            ImageWallpaper.this.mWorker.getThreadHandler().postDelayed(this.mFinishRenderingTask, 1000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void finishRendering() {
            Trace.beginSection("ImageWallpaper#finishRendering");
            EglHelper eglHelper = this.mEglHelper;
            if (eglHelper != null) {
                eglHelper.destroyEglSurface();
                this.mEglHelper.destroyEglContext();
            }
            Trace.endSection();
        }

        private boolean needSupportWideColorGamut() {
            return this.mRenderer.isWcgContent();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        protected void dump(String prefix, FileDescriptor fd, PrintWriter out, String[] args) {
            super.dump(prefix, fd, out, args);
            out.print(prefix);
            out.print("Engine=");
            out.println(this);
            out.print(prefix);
            out.print("valid surface=");
            out.println((getSurfaceHolder() == null || getSurfaceHolder().getSurface() == null) ? "null" : Boolean.valueOf(getSurfaceHolder().getSurface().isValid()));
            out.print(prefix);
            out.print("surface frame=");
            out.println(getSurfaceHolder() != null ? getSurfaceHolder().getSurfaceFrame() : "null");
            this.mEglHelper.dump(prefix, fd, out, args);
            this.mRenderer.dump(prefix, fd, out, args);
        }
    }
}
