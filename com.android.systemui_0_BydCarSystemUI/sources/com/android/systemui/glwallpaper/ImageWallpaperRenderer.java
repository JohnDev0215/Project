package com.android.systemui.glwallpaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.util.Log;
import android.util.Size;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.util.SharedPreferencesUtil;
import com.byd.card.utils.BitmapUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class ImageWallpaperRenderer implements GLWallpaperRenderer {
    private static final String BITMAP_NAME = "wallpaper.jpg";
    private static final boolean DEBUG = false;
    private static final String TAG = "ImageWallpaperRenderer";
    private final ImageGLProgram mProgram;
    private final Rect mSurfaceSize = new Rect();
    private final WallpaperTexture mTexture;
    private final ImageGLWallpaper mWallpaper;

    @Override // com.android.systemui.glwallpaper.GLWallpaperRenderer
    public void finish() {
    }

    public ImageWallpaperRenderer(Context context) {
        WallpaperManager wallpaperManager = (WallpaperManager) context.getSystemService(WallpaperManager.class);
        if (wallpaperManager == null) {
            Log.w(TAG, "WallpaperManager not available");
        }
        this.mTexture = new WallpaperTexture(wallpaperManager);
        ImageGLProgram imageGLProgram = new ImageGLProgram(context);
        this.mProgram = imageGLProgram;
        this.mWallpaper = new ImageGLWallpaper(imageGLProgram);
    }

    @Override // com.android.systemui.glwallpaper.GLWallpaperRenderer
    public boolean isWcgContent() {
        return this.mTexture.isWcgContent();
    }

    @Override // com.android.systemui.glwallpaper.GLWallpaperRenderer
    public void onSurfaceCreated() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.mProgram.useGLProgram(R.raw.image_wallpaper_vertex_shader, R.raw.image_wallpaper_fragment_shader);
        this.mTexture.use(new Consumer() { // from class: com.android.systemui.glwallpaper.-$$Lambda$ImageWallpaperRenderer$EyZ4gELnMt-qXIwebKkGU3N7Mto
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onSurfaceCreated$0$ImageWallpaperRenderer((Bitmap) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onSurfaceCreated$0$ImageWallpaperRenderer(Bitmap bitmap) {
        if (bitmap == null) {
            Log.w(TAG, "reload texture failed!");
        }
        this.mWallpaper.setup(bitmap);
    }

    @Override // com.android.systemui.glwallpaper.GLWallpaperRenderer
    public void onSurfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override // com.android.systemui.glwallpaper.GLWallpaperRenderer
    public void onDrawFrame() {
        GLES20.glClear(16384);
        GLES20.glViewport(0, 0, this.mSurfaceSize.width(), this.mSurfaceSize.height());
        this.mWallpaper.useTexture();
        this.mWallpaper.draw();
    }

    @Override // com.android.systemui.glwallpaper.GLWallpaperRenderer
    public Size reportSurfaceSize() {
        this.mTexture.use(null);
        this.mSurfaceSize.set(this.mTexture.getTextureDimensions());
        return new Size(this.mSurfaceSize.width(), this.mSurfaceSize.height());
    }

    @Override // com.android.systemui.glwallpaper.GLWallpaperRenderer
    public void dump(String prefix, FileDescriptor fd, PrintWriter out, String[] args) {
        out.print(prefix);
        out.print("mSurfaceSize=");
        out.print(this.mSurfaceSize);
        out.print(prefix);
        out.print("mWcgContent=");
        out.print(isWcgContent());
        this.mWallpaper.dump(prefix, fd, out, args);
    }

    static class WallpaperTexture {
        private Bitmap mBitmap;
        private final Rect mDimensions;
        private final AtomicInteger mRefCount;
        private final WallpaperManager mWallpaperManager;
        private boolean mWcgContent;

        private WallpaperTexture(WallpaperManager wallpaperManager) {
            this.mWallpaperManager = wallpaperManager;
            this.mRefCount = new AtomicInteger();
            this.mDimensions = new Rect();
        }

        public void use(Consumer<Bitmap> consumer) {
            Bitmap bitmap;
            this.mRefCount.incrementAndGet();
            synchronized (this.mRefCount) {
                if (this.mBitmap == null) {
                    try {
                        int i = SharedPreferencesUtil.getInt(SystemUIApplication.getInstance(), Constants.STATE_WALLPAPER, 0);
                        Log.w(ImageWallpaperRenderer.TAG, "state = " + i);
                        if (i == 0) {
                            this.mBitmap = this.mWallpaperManager.getBitmap(false);
                            BitmapUtils.writeBitmapToFile(SystemUIApplication.getInstance().getFilesDir() + File.separator + ImageWallpaperRenderer.BITMAP_NAME, this.mBitmap, 50);
                            SharedPreferencesUtil.putInt(SystemUIApplication.getInstance(), Constants.STATE_WALLPAPER, 1);
                        } else {
                            this.mBitmap = BitmapFactory.decodeFile(SystemUIApplication.getInstance().getFilesDir() + File.separator + ImageWallpaperRenderer.BITMAP_NAME);
                        }
                        this.mWcgContent = this.mWallpaperManager.wallpaperSupportsWcg(1);
                        this.mWallpaperManager.forgetLoadedWallpaper();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap2 = this.mBitmap;
                    if (bitmap2 == null) {
                        Log.w(ImageWallpaperRenderer.TAG, "Can't get bitmap");
                    } else {
                        this.mDimensions.set(0, 0, bitmap2.getWidth(), this.mBitmap.getHeight());
                    }
                }
            }
            if (consumer != null) {
                consumer.accept(this.mBitmap);
            }
            synchronized (this.mRefCount) {
                if (this.mRefCount.decrementAndGet() == 0 && (bitmap = this.mBitmap) != null) {
                    bitmap.recycle();
                    this.mBitmap = null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isWcgContent() {
            return this.mWcgContent;
        }

        private String getHash() {
            Bitmap bitmap = this.mBitmap;
            return bitmap != null ? Integer.toHexString(bitmap.hashCode()) : "null";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Rect getTextureDimensions() {
            return this.mDimensions;
        }

        public String toString() {
            return "{" + getHash() + ", " + this.mRefCount.get() + "}";
        }
    }
}
