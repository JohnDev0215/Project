package android.app;

import android.annotation.SystemApi;
import android.app.IWallpaperManagerCallback;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadSystemException;
import android.os.FileUtils;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManagerGlobal;
import com.android.internal.R;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes.dex */
public class WallpaperManager {
    public static final String ACTION_CHANGE_LIVE_WALLPAPER = "android.service.wallpaper.CHANGE_LIVE_WALLPAPER";
    public static final String ACTION_CROP_AND_SET_WALLPAPER = "android.service.wallpaper.CROP_AND_SET_WALLPAPER";
    public static final String ACTION_LIVE_WALLPAPER_CHOOSER = "android.service.wallpaper.LIVE_WALLPAPER_CHOOSER";
    public static final String COMMAND_DROP = "android.home.drop";
    public static final String COMMAND_REAPPLY = "android.wallpaper.reapply";
    public static final String COMMAND_SECONDARY_TAP = "android.wallpaper.secondaryTap";
    public static final String COMMAND_TAP = "android.wallpaper.tap";
    private static boolean DEBUG = false;
    public static final String EXTRA_LIVE_WALLPAPER_COMPONENT = "android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT";
    public static final String EXTRA_NEW_WALLPAPER_ID = "android.service.wallpaper.extra.ID";
    public static final int FLAG_LOCK = 2;
    public static final int FLAG_SYSTEM = 1;
    private static final String PROP_LOCK_WALLPAPER = "ro.config.lock_wallpaper";
    private static final String PROP_WALLPAPER = "ro.config.wallpaper";
    private static final String PROP_WALLPAPER_COMPONENT = "ro.config.wallpaper_component";
    private static String TAG = "WallpaperManager";
    public static final String WALLPAPER_PREVIEW_META_DATA = "android.wallpaper.preview";
    private static Globals sGlobals;
    private static final Object sSync = new Object[0];
    private final ColorManagementProxy mCmProxy;
    private final Context mContext;
    private float mWallpaperXStep;
    private float mWallpaperYStep;
    private final boolean mWcgEnabled;

    public interface OnWallpaperChangedListener {
        void onWallpaperChanged();

        void onWallpaperListChanged();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SetWallpaperFlags {
    }

    public enum ORIENTATION {
        LAND(0),
        PORT(1);

        private int value;

        ORIENTATION(int i) {
            this.value = i;
        }

        public int toInt() {
            return this.value;
        }
    }

    public enum MODE {
        NORMAL(0),
        TIME(1),
        CAROUSEL(2);

        private int value;

        MODE(int i) {
            this.value = i;
        }

        public int toInt() {
            return this.value;
        }
    }

    static class FastBitmapDrawable extends Drawable {
        private final Bitmap mBitmap;
        private int mDrawLeft;
        private int mDrawTop;
        private final int mHeight;
        private final Paint mPaint;
        private final int mWidth;

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -1;
        }

        private FastBitmapDrawable(Bitmap bitmap) {
            this.mBitmap = bitmap;
            this.mWidth = bitmap.getWidth();
            int height = bitmap.getHeight();
            this.mHeight = height;
            setBounds(0, 0, this.mWidth, height);
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.drawBitmap(this.mBitmap, this.mDrawLeft, this.mDrawTop, this.mPaint);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int i, int i2, int i3, int i4) {
            this.mDrawLeft = i + (((i3 - i) - this.mWidth) / 2);
            this.mDrawTop = i2 + (((i4 - i2) - this.mHeight) / 2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        @Override // android.graphics.drawable.Drawable
        public void setDither(boolean z) {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        @Override // android.graphics.drawable.Drawable
        public void setFilterBitmap(boolean z) {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.mWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.mHeight;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return this.mWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return this.mHeight;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Globals extends IWallpaperManagerCallback.Stub {
        private Bitmap mCachedWallpaper;
        private int mCachedWallpaperUserId;
        private boolean mColorCallbackRegistered;
        private Bitmap mDefaultWallpaper;
        private Handler mMainLooperHandler;
        private final IWallpaperManager mService;
        private final ArrayList<Pair<OnColorsChangedListener, Handler>> mColorListeners = new ArrayList<>();
        private final ArrayList<Pair<OnWallpaperChangedListener, Handler>> mWallpaperListeners = new ArrayList<>();

        Globals(IWallpaperManager iWallpaperManager, Looper looper) {
            this.mService = iWallpaperManager;
            this.mMainLooperHandler = new Handler(looper);
            forgetLoadedWallpaper();
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperChanged() {
            forgetLoadedWallpaper();
            synchronized (this) {
                for (final Pair<OnWallpaperChangedListener, Handler> pair : this.mWallpaperListeners) {
                    Handler handler = pair.second;
                    if (pair.second == null) {
                        handler = this.mMainLooperHandler;
                    }
                    handler.post(new Runnable() { // from class: android.app.-$$Lambda$WallpaperManager$Globals$EMO2Wye3NSFtSrd6fGvTyufQUGE
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onWallpaperChanged$0$WallpaperManager$Globals(pair);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onWallpaperChanged$0$WallpaperManager$Globals(Pair pair) {
            boolean zContains;
            synchronized (WallpaperManager.sGlobals) {
                zContains = this.mWallpaperListeners.contains(pair);
            }
            if (zContains) {
                ((OnWallpaperChangedListener) pair.first).onWallpaperChanged();
            }
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperListChanged() {
            synchronized (this) {
                for (final Pair<OnWallpaperChangedListener, Handler> pair : this.mWallpaperListeners) {
                    Handler handler = pair.second;
                    if (pair.second == null) {
                        handler = this.mMainLooperHandler;
                    }
                    handler.post(new Runnable() { // from class: android.app.-$$Lambda$WallpaperManager$Globals$sdu39NKE-pKEESsY5Q-0MlB1-Xg
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onWallpaperListChanged$1$WallpaperManager$Globals(pair);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onWallpaperListChanged$1$WallpaperManager$Globals(Pair pair) {
            boolean zContains;
            synchronized (WallpaperManager.sGlobals) {
                zContains = this.mWallpaperListeners.contains(pair);
            }
            if (zContains) {
                ((OnWallpaperChangedListener) pair.first).onWallpaperListChanged();
            }
        }

        public void addOnWallpaperChangedListener(OnWallpaperChangedListener onWallpaperChangedListener, Handler handler, int i, int i2) {
            synchronized (this) {
                addOnColorsChangedListener(null, handler, i, i2);
                this.mWallpaperListeners.add(new Pair<>(onWallpaperChangedListener, handler));
            }
        }

        public void removeOnWallpaperChangedListener(final OnWallpaperChangedListener onWallpaperChangedListener, int i, int i2) {
            synchronized (this) {
                this.mWallpaperListeners.removeIf(new Predicate() { // from class: android.app.-$$Lambda$WallpaperManager$Globals$1wMz7QyVs4IgrgvOEVlTKe7zFGU
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return WallpaperManager.Globals.lambda$removeOnWallpaperChangedListener$2(onWallpaperChangedListener, (Pair) obj);
                    }
                });
                removeOnColorsChangedListener(null, i, i2);
            }
        }

        static /* synthetic */ boolean lambda$removeOnWallpaperChangedListener$2(OnWallpaperChangedListener onWallpaperChangedListener, Pair pair) {
            return pair.first == onWallpaperChangedListener;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001a A[Catch: all -> 0x0026, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:10:0x001a, B:11:0x0024, B:8:0x000f), top: B:16:0x0001, inners: #1 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void addOnColorsChangedListener(android.app.WallpaperManager.OnColorsChangedListener r2, android.os.Handler r3, int r4, int r5) {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.mColorCallbackRegistered     // Catch: java.lang.Throwable -> L26
                if (r0 != 0) goto L18
                android.app.IWallpaperManager r0 = r1.mService     // Catch: android.os.RemoteException -> Le java.lang.Throwable -> L26
                r0.registerWallpaperColorsCallback(r1, r4, r5)     // Catch: android.os.RemoteException -> Le java.lang.Throwable -> L26
                r4 = 1
                r1.mColorCallbackRegistered = r4     // Catch: android.os.RemoteException -> Le java.lang.Throwable -> L26
                goto L18
            Le:
                r4 = move-exception
                java.lang.String r5 = android.app.WallpaperManager.access$000()     // Catch: java.lang.Throwable -> L26
                java.lang.String r0 = "Can't register for color updates"
                android.util.Log.w(r5, r0, r4)     // Catch: java.lang.Throwable -> L26
            L18:
                if (r2 == 0) goto L24
                java.util.ArrayList<android.util.Pair<android.app.WallpaperManager$OnColorsChangedListener, android.os.Handler>> r4 = r1.mColorListeners     // Catch: java.lang.Throwable -> L26
                android.util.Pair r5 = new android.util.Pair     // Catch: java.lang.Throwable -> L26
                r5.<init>(r2, r3)     // Catch: java.lang.Throwable -> L26
                r4.add(r5)     // Catch: java.lang.Throwable -> L26
            L24:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L26
                return
            L26:
                r2 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L26
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.WallpaperManager.Globals.addOnColorsChangedListener(android.app.WallpaperManager$OnColorsChangedListener, android.os.Handler, int, int):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0034 A[Catch: all -> 0x0036, DONT_GENERATE, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x000d, B:7:0x0015, B:9:0x0019, B:11:0x0021, B:12:0x0024, B:15:0x002b, B:16:0x0034), top: B:21:0x0003, inners: #1 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void removeOnColorsChangedListener(final android.app.WallpaperManager.OnColorsChangedListener r3, int r4, int r5) {
            /*
                r2 = this;
                monitor-enter(r2)
                if (r3 == 0) goto Ld
                java.util.ArrayList<android.util.Pair<android.app.WallpaperManager$OnColorsChangedListener, android.os.Handler>> r0 = r2.mColorListeners     // Catch: java.lang.Throwable -> L36
                android.app.-$$Lambda$WallpaperManager$Globals$4c1V09WkSxW64UJgWDiGBy_VVlE r1 = new android.app.-$$Lambda$WallpaperManager$Globals$4c1V09WkSxW64UJgWDiGBy_VVlE     // Catch: java.lang.Throwable -> L36
                r1.<init>()     // Catch: java.lang.Throwable -> L36
                r0.removeIf(r1)     // Catch: java.lang.Throwable -> L36
            Ld:
                java.util.ArrayList<android.util.Pair<android.app.WallpaperManager$OnColorsChangedListener, android.os.Handler>> r3 = r2.mColorListeners     // Catch: java.lang.Throwable -> L36
                int r3 = r3.size()     // Catch: java.lang.Throwable -> L36
                if (r3 != 0) goto L34
                boolean r3 = r2.mColorCallbackRegistered     // Catch: java.lang.Throwable -> L36
                if (r3 == 0) goto L34
                java.util.ArrayList<android.util.Pair<android.app.WallpaperManager$OnWallpaperChangedListener, android.os.Handler>> r3 = r2.mWallpaperListeners     // Catch: java.lang.Throwable -> L36
                int r3 = r3.size()     // Catch: java.lang.Throwable -> L36
                if (r3 != 0) goto L34
                r3 = 0
                r2.mColorCallbackRegistered = r3     // Catch: java.lang.Throwable -> L36
                android.app.IWallpaperManager r3 = r2.mService     // Catch: android.os.RemoteException -> L2a java.lang.Throwable -> L36
                r3.unregisterWallpaperColorsCallback(r2, r4, r5)     // Catch: android.os.RemoteException -> L2a java.lang.Throwable -> L36
                goto L34
            L2a:
                r3 = move-exception
                java.lang.String r4 = android.app.WallpaperManager.access$000()     // Catch: java.lang.Throwable -> L36
                java.lang.String r5 = "Can't unregister color updates"
                android.util.Log.w(r4, r5, r3)     // Catch: java.lang.Throwable -> L36
            L34:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L36
                return
            L36:
                r3 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L36
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.WallpaperManager.Globals.removeOnColorsChangedListener(android.app.WallpaperManager$OnColorsChangedListener, int, int):void");
        }

        static /* synthetic */ boolean lambda$removeOnColorsChangedListener$3(OnColorsChangedListener onColorsChangedListener, Pair pair) {
            return pair.first == onColorsChangedListener;
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperColorsChanged(final WallpaperColors wallpaperColors, final int i, final int i2) {
            synchronized (this) {
                for (final Pair<OnColorsChangedListener, Handler> pair : this.mColorListeners) {
                    Handler handler = pair.second;
                    if (pair.second == null) {
                        handler = this.mMainLooperHandler;
                    }
                    handler.post(new Runnable() { // from class: android.app.-$$Lambda$WallpaperManager$Globals$DatcsOT_aU3NuEbRk4gEeI00bE4
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onWallpaperColorsChanged$4$WallpaperManager$Globals(pair, wallpaperColors, i, i2);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onWallpaperColorsChanged$4$WallpaperManager$Globals(Pair pair, WallpaperColors wallpaperColors, int i, int i2) {
            boolean zContains;
            synchronized (WallpaperManager.sGlobals) {
                zContains = this.mColorListeners.contains(pair);
            }
            if (zContains) {
                ((OnColorsChangedListener) pair.first).onColorsChanged(wallpaperColors, i, i2);
            }
        }

        WallpaperColors getWallpaperColors(int i, int i2, int i3) {
            if (i != 2 && i != 1) {
                throw new IllegalArgumentException("Must request colors for exactly one kind of wallpaper");
            }
            try {
                return this.mService.getWallpaperColors(i, i2, i3);
            } catch (RemoteException unused) {
                return null;
            }
        }

        public Bitmap peekWallpaperBitmap(Context context, boolean z, int i, ColorManagementProxy colorManagementProxy) {
            return peekWallpaperBitmap(context, z, i, context.getUserId(), false, colorManagementProxy);
        }

        public Bitmap peekWallpaperBitmap(Context context, boolean z, int i, int i2, boolean z2, ColorManagementProxy colorManagementProxy) {
            IWallpaperManager iWallpaperManager = this.mService;
            if (iWallpaperManager != null) {
                try {
                    if (!iWallpaperManager.isWallpaperSupported(context.getOpPackageName())) {
                        return null;
                    }
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            synchronized (this) {
                if (this.mCachedWallpaper != null && this.mCachedWallpaperUserId == i2 && !this.mCachedWallpaper.isRecycled()) {
                    return this.mCachedWallpaper;
                }
                this.mCachedWallpaper = null;
                this.mCachedWallpaperUserId = 0;
                try {
                    try {
                        this.mCachedWallpaper = getCurrentWallpaperLocked(context, i2, z2, colorManagementProxy);
                        this.mCachedWallpaperUserId = i2;
                    } catch (OutOfMemoryError e2) {
                        Log.w(WallpaperManager.TAG, "Out of memory loading the current wallpaper: " + e2);
                    }
                } catch (SecurityException e3) {
                    if (context.getApplicationInfo().targetSdkVersion < 27) {
                        Log.w(WallpaperManager.TAG, "No permission to access wallpaper, suppressing exception to avoid crashing legacy app.");
                    } else {
                        throw e3;
                    }
                }
                if (this.mCachedWallpaper != null) {
                    return this.mCachedWallpaper;
                }
                if (!z) {
                    return null;
                }
                Bitmap defaultWallpaper = this.mDefaultWallpaper;
                if (defaultWallpaper == null) {
                    defaultWallpaper = getDefaultWallpaper(context, i);
                    synchronized (this) {
                        this.mDefaultWallpaper = defaultWallpaper;
                    }
                }
                return defaultWallpaper;
            }
        }

        public Map<String, Bitmap> peekWallpaperBitmapList(int i, int i2, boolean z, Context context, boolean z2, int i3, ColorManagementProxy colorManagementProxy) {
            return peekWallpaperBitmapList(i, i2, z, context, z2, i3, context.getUserId(), false, colorManagementProxy);
        }

        public Map<String, Bitmap> peekWallpaperBitmapList(int i, int i2, boolean z, Context context, boolean z2, int i3, int i4, boolean z3, ColorManagementProxy colorManagementProxy) {
            Map<String, Bitmap> wallpaperListLocked;
            IWallpaperManager iWallpaperManager = this.mService;
            if (iWallpaperManager != null) {
                try {
                    if (!iWallpaperManager.isWallpaperSupported(context.getOpPackageName())) {
                        return null;
                    }
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            synchronized (this) {
                this.mCachedWallpaperUserId = 0;
                try {
                    wallpaperListLocked = getWallpaperListLocked(i, i2, z, context, i4, z3, colorManagementProxy);
                    try {
                        this.mCachedWallpaperUserId = i4;
                    } catch (OutOfMemoryError e2) {
                        e = e2;
                        Log.w(WallpaperManager.TAG, "Out of memory loading the current wallpaper: ", e);
                    } catch (SecurityException e3) {
                        e = e3;
                        if (context.getApplicationInfo().targetSdkVersion < 27) {
                            Log.w(WallpaperManager.TAG, "No permission to access wallpaper, suppressing exception to avoid crashing legacy app.");
                        } else {
                            throw e;
                        }
                    }
                } catch (OutOfMemoryError e4) {
                    e = e4;
                    wallpaperListLocked = null;
                } catch (SecurityException e5) {
                    e = e5;
                    wallpaperListLocked = null;
                }
                if (wallpaperListLocked != null) {
                    return wallpaperListLocked;
                }
                return null;
            }
        }

        void forgetLoadedWallpaper() {
            synchronized (this) {
                this.mCachedWallpaper = null;
                this.mCachedWallpaperUserId = 0;
                this.mDefaultWallpaper = null;
            }
        }

        private Bitmap getCurrentWallpaperLocked(Context context, int i, final boolean z, final ColorManagementProxy colorManagementProxy) {
            if (this.mService == null) {
                Log.w(WallpaperManager.TAG, "WallpaperService not running");
                return null;
            }
            try {
                ParcelFileDescriptor wallpaperWithFeature = this.mService.getWallpaperWithFeature(context.getOpPackageName(), context.getAttributionTag(), this, 1, new Bundle(), i);
                if (wallpaperWithFeature != null) {
                    try {
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(wallpaperWithFeature));
                        try {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                int i2 = bufferedInputStream.read();
                                if (i2 != -1) {
                                    byteArrayOutputStream.write(i2);
                                } else {
                                    Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(byteArrayOutputStream.toByteArray()), new ImageDecoder.OnHeaderDecodedListener() { // from class: android.app.-$$Lambda$WallpaperManager$Globals$3HEO_zvarr7zynj9n3lODZf6i2E
                                        @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                                        public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                                            WallpaperManager.Globals.lambda$getCurrentWallpaperLocked$5(z, colorManagementProxy, imageDecoder, imageInfo, source);
                                        }
                                    });
                                    bufferedInputStream.close();
                                    return bitmapDecodeBitmap;
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (IOException | OutOfMemoryError e) {
                        Log.w(WallpaperManager.TAG, "Can't decode file", e);
                    }
                }
                return null;
            } catch (RemoteException e2) {
                throw e2.rethrowFromSystemServer();
            }
        }

        static /* synthetic */ void lambda$getCurrentWallpaperLocked$5(boolean z, ColorManagementProxy colorManagementProxy, ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
            imageDecoder.setMutableRequired(!z);
            if (colorManagementProxy != null) {
                colorManagementProxy.doColorManagement(imageDecoder, imageInfo);
            }
        }

        private Map<String, Bitmap> getWallpaperListLocked(int i, int i2, boolean z, Context context, int i3, final boolean z2, final ColorManagementProxy colorManagementProxy) throws Throwable {
            BufferedInputStream bufferedInputStream;
            ByteArrayOutputStream byteArrayOutputStream;
            HashMap map = null;
            if (this.mService == null) {
                Log.w(WallpaperManager.TAG, "WallpaperService not running");
                return null;
            }
            try {
                ParcelFileDescriptor[] parcelFileDescriptorArr = new ParcelFileDescriptor[10];
                String[] strArr = new String[10];
                new Bundle();
                int wallpapers = this.mService.getWallpapers(context.getOpPackageName(), context.getAttributionTag(), 1, i3, parcelFileDescriptorArr, strArr, i, i2, z);
                Log.e(WallpaperManager.TAG, "getWallpaperListLocked ret = " + wallpapers);
                if (wallpapers > 0) {
                    map = new HashMap();
                    for (int i4 = 0; i4 < wallpapers; i4++) {
                        try {
                            bufferedInputStream = new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptorArr[i4]));
                            try {
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                while (true) {
                                    int i5 = bufferedInputStream.read();
                                    if (i5 == -1) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(i5);
                                }
                            } catch (Throwable th) {
                                th = th;
                            }
                        } catch (IOException | OutOfMemoryError e) {
                            e = e;
                        }
                        try {
                            map.put(strArr[i4], ImageDecoder.decodeBitmap(ImageDecoder.createSource(byteArrayOutputStream.toByteArray()), new ImageDecoder.OnHeaderDecodedListener() { // from class: android.app.-$$Lambda$WallpaperManager$Globals$f-62ImpQXBUI61-JwsVRvB8qlJ8
                                @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                                public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                                    WallpaperManager.Globals.lambda$getWallpaperListLocked$6(z2, colorManagementProxy, imageDecoder, imageInfo, source);
                                }
                            }));
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e2) {
                                e = e2;
                                Log.w(WallpaperManager.TAG, "Can't decode file", e);
                            } catch (OutOfMemoryError e3) {
                                e = e3;
                                Log.w(WallpaperManager.TAG, "Can't decode file", e);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            Throwable th3 = th;
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                            }
                            throw th3;
                        }
                    }
                }
                return map;
            } catch (RemoteException e4) {
                throw e4.rethrowFromSystemServer();
            }
        }

        static /* synthetic */ void lambda$getWallpaperListLocked$6(boolean z, ColorManagementProxy colorManagementProxy, ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
            imageDecoder.setMutableRequired(!z);
            if (colorManagementProxy != null) {
                colorManagementProxy.doColorManagement(imageDecoder, imageInfo);
            }
        }

        private Bitmap getDefaultWallpaper(Context context, int i) {
            InputStream inputStreamOpenDefaultWallpaper = WallpaperManager.openDefaultWallpaper(context, i);
            if (inputStreamOpenDefaultWallpaper != null) {
                try {
                    return BitmapFactory.decodeStream(inputStreamOpenDefaultWallpaper, null, new BitmapFactory.Options());
                } catch (OutOfMemoryError e) {
                    Log.w(WallpaperManager.TAG, "Can't decode stream", e);
                    return null;
                } finally {
                    IoUtils.closeQuietly(inputStreamOpenDefaultWallpaper);
                }
            }
            return null;
        }
    }

    static void initGlobals(IWallpaperManager iWallpaperManager, Looper looper) {
        synchronized (sSync) {
            if (sGlobals == null) {
                sGlobals = new Globals(iWallpaperManager, looper);
            }
        }
    }

    WallpaperManager(IWallpaperManager iWallpaperManager, Context context, Handler handler) {
        this.mWallpaperXStep = -1.0f;
        this.mWallpaperYStep = -1.0f;
        this.mContext = context;
        if (iWallpaperManager != null) {
            initGlobals(iWallpaperManager, context.getMainLooper());
        }
        this.mWcgEnabled = context.getResources().getConfiguration().isScreenWideColorGamut() && context.getResources().getBoolean(R.bool.config_enableWcgMode);
        this.mCmProxy = new ColorManagementProxy(context);
    }

    WallpaperManager() {
        this.mWallpaperXStep = -1.0f;
        this.mWallpaperYStep = -1.0f;
        this.mContext = null;
        this.mCmProxy = null;
        this.mWcgEnabled = false;
    }

    public static WallpaperManager getInstance(Context context) {
        return (WallpaperManager) context.getSystemService(Context.WALLPAPER_SERVICE);
    }

    public IWallpaperManager getIWallpaperManager() {
        return sGlobals.mService;
    }

    public boolean shouldEnableWideColorGamut() {
        return this.mWcgEnabled;
    }

    public Drawable getDrawable() {
        Bitmap bitmapPeekWallpaperBitmap = sGlobals.peekWallpaperBitmap(this.mContext, true, 1, getColorManagementProxy());
        if (bitmapPeekWallpaperBitmap == null) {
            return null;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmapPeekWallpaperBitmap);
        bitmapDrawable.setDither(false);
        return bitmapDrawable;
    }

    public Drawable getBuiltInDrawable() {
        return getBuiltInDrawable(0, 0, false, 0.0f, 0.0f, 1);
    }

    public Drawable getBuiltInDrawable(int i) {
        return getBuiltInDrawable(0, 0, false, 0.0f, 0.0f, i);
    }

    public Drawable getBuiltInDrawable(int i, int i2, boolean z, float f, float f2) {
        return getBuiltInDrawable(i, i2, z, f, f2, 1);
    }

    public Drawable getBuiltInDrawable(int i, int i2, boolean z, float f, float f2, int i3) {
        RectF rectF;
        BitmapRegionDecoder bitmapRegionDecoderNewInstance;
        Bitmap bitmapCreateBitmap;
        if (sGlobals.mService == null) {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        if (i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("Must request exactly one kind of wallpaper");
        }
        Resources resources = this.mContext.getResources();
        float fMax = Math.max(0.0f, Math.min(1.0f, f));
        float fMax2 = Math.max(0.0f, Math.min(1.0f, f2));
        InputStream inputStreamOpenDefaultWallpaper = openDefaultWallpaper(this.mContext, i3);
        if (inputStreamOpenDefaultWallpaper == null) {
            if (DEBUG) {
                Log.w(TAG, "default wallpaper stream " + i3 + " is null");
            }
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamOpenDefaultWallpaper);
        if (i <= 0 || i2 <= 0) {
            return new BitmapDrawable(resources, BitmapFactory.decodeStream(bufferedInputStream, null, null));
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(bufferedInputStream, null, options);
        if (options.outWidth != 0 && options.outHeight != 0) {
            int i4 = options.outWidth;
            int i5 = options.outHeight;
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(openDefaultWallpaper(this.mContext, i3));
            int iMin = Math.min(i4, i);
            int iMin2 = Math.min(i5, i2);
            if (z) {
                rectF = getMaxCropRect(i4, i5, iMin, iMin2, fMax, fMax2);
            } else {
                float f3 = (i4 - iMin) * fMax;
                float f4 = (i5 - iMin2) * fMax2;
                rectF = new RectF(f3, f4, iMin + f3, iMin2 + f4);
            }
            Rect rect = new Rect();
            rectF.roundOut(rect);
            if (rect.width() <= 0 || rect.height() <= 0) {
                Log.w(TAG, "crop has bad values for full size image");
                return null;
            }
            int iMin3 = Math.min(rect.width() / iMin, rect.height() / iMin2);
            try {
                bitmapRegionDecoderNewInstance = BitmapRegionDecoder.newInstance((InputStream) bufferedInputStream2, true);
            } catch (IOException unused) {
                Log.w(TAG, "cannot open region decoder for default wallpaper");
                bitmapRegionDecoderNewInstance = null;
            }
            if (bitmapRegionDecoderNewInstance != null) {
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                if (iMin3 > 1) {
                    options2.inSampleSize = iMin3;
                }
                bitmapCreateBitmap = bitmapRegionDecoderNewInstance.decodeRegion(rect, options2);
                bitmapRegionDecoderNewInstance.recycle();
            } else {
                bitmapCreateBitmap = null;
            }
            if (bitmapCreateBitmap == null) {
                BufferedInputStream bufferedInputStream3 = new BufferedInputStream(openDefaultWallpaper(this.mContext, i3));
                BitmapFactory.Options options3 = new BitmapFactory.Options();
                if (iMin3 > 1) {
                    options3.inSampleSize = iMin3;
                }
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(bufferedInputStream3, null, options3);
                if (bitmapDecodeStream != null) {
                    bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeStream, rect.left, rect.top, rect.width(), rect.height());
                }
            }
            if (bitmapCreateBitmap == null) {
                Log.w(TAG, "cannot decode default wallpaper");
                return null;
            }
            if (iMin > 0 && iMin2 > 0 && (bitmapCreateBitmap.getWidth() != iMin || bitmapCreateBitmap.getHeight() != iMin2)) {
                Matrix matrix = new Matrix();
                RectF rectF2 = new RectF(0.0f, 0.0f, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight());
                RectF rectF3 = new RectF(0.0f, 0.0f, iMin, iMin2);
                matrix.setRectToRect(rectF2, rectF3, Matrix.ScaleToFit.FILL);
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap((int) rectF3.width(), (int) rectF3.height(), Bitmap.Config.ARGB_8888);
                if (bitmapCreateBitmap2 != null) {
                    Canvas canvas = new Canvas(bitmapCreateBitmap2);
                    Paint paint = new Paint();
                    paint.setFilterBitmap(true);
                    canvas.drawBitmap(bitmapCreateBitmap, matrix, paint);
                    bitmapCreateBitmap = bitmapCreateBitmap2;
                }
            }
            return new BitmapDrawable(resources, bitmapCreateBitmap);
        }
        Log.e(TAG, "default wallpaper dimensions are 0");
        return null;
    }

    private static RectF getMaxCropRect(int i, int i2, int i3, int i4, float f, float f2) {
        RectF rectF = new RectF();
        float f3 = i;
        float f4 = i2;
        float f5 = i3;
        float f6 = i4;
        if (f3 / f4 > f5 / f6) {
            rectF.top = 0.0f;
            rectF.bottom = f4;
            float f7 = f5 * (f4 / f6);
            rectF.left = (f3 - f7) * f;
            rectF.right = rectF.left + f7;
        } else {
            rectF.left = 0.0f;
            rectF.right = f3;
            float f8 = f6 * (f3 / f5);
            rectF.top = (f4 - f8) * f2;
            rectF.bottom = rectF.top + f8;
        }
        return rectF;
    }

    public Drawable peekDrawable() {
        Bitmap bitmapPeekWallpaperBitmap = sGlobals.peekWallpaperBitmap(this.mContext, false, 1, getColorManagementProxy());
        if (bitmapPeekWallpaperBitmap == null) {
            return null;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmapPeekWallpaperBitmap);
        bitmapDrawable.setDither(false);
        return bitmapDrawable;
    }

    public Drawable getFastDrawable() {
        Bitmap bitmapPeekWallpaperBitmap = sGlobals.peekWallpaperBitmap(this.mContext, true, 1, getColorManagementProxy());
        if (bitmapPeekWallpaperBitmap != null) {
            return new FastBitmapDrawable(bitmapPeekWallpaperBitmap);
        }
        return null;
    }

    public Drawable peekFastDrawable() {
        Bitmap bitmapPeekWallpaperBitmap = sGlobals.peekWallpaperBitmap(this.mContext, false, 1, getColorManagementProxy());
        if (bitmapPeekWallpaperBitmap != null) {
            return new FastBitmapDrawable(bitmapPeekWallpaperBitmap);
        }
        return null;
    }

    public boolean wallpaperSupportsWcg(int i) {
        ColorManagementProxy colorManagementProxy;
        Bitmap bitmapPeekWallpaperBitmap;
        return (!shouldEnableWideColorGamut() || (bitmapPeekWallpaperBitmap = sGlobals.peekWallpaperBitmap(this.mContext, false, i, (colorManagementProxy = getColorManagementProxy()))) == null || bitmapPeekWallpaperBitmap.getColorSpace() == null || bitmapPeekWallpaperBitmap.getColorSpace() == ColorSpace.get(ColorSpace.Named.SRGB) || !colorManagementProxy.isSupportedColorSpace(bitmapPeekWallpaperBitmap.getColorSpace())) ? false : true;
    }

    public Bitmap getBitmap() {
        return getBitmap(false);
    }

    public Bitmap getBitmap(boolean z) {
        return getBitmapAsUser(this.mContext.getUserId(), z);
    }

    public Bitmap getBitmapAsUser(int i, boolean z) {
        return sGlobals.peekWallpaperBitmap(this.mContext, true, 1, i, z, getColorManagementProxy());
    }

    public Map<String, Bitmap> getBitmapList(ORIENTATION orientation, MODE mode, boolean z) {
        return getBitmapList(orientation.toInt(), mode.toInt(), z, false);
    }

    public Map<String, Bitmap> getBitmapList(int i, int i2, boolean z, boolean z2) {
        return getBitmapListAsUser(i, i2, z, this.mContext.getUserId(), z2);
    }

    public Map<String, Bitmap> getBitmapListAsUser(int i, int i2, boolean z, int i3, boolean z2) {
        return sGlobals.peekWallpaperBitmapList(i, i2, z, this.mContext, true, 1, i3, z2, getColorManagementProxy());
    }

    public ParcelFileDescriptor getWallpaperFile(int i) {
        return getWallpaperFile(i, this.mContext.getUserId());
    }

    public void addOnColorsChangedListener(OnColorsChangedListener onColorsChangedListener, Handler handler) {
        addOnColorsChangedListener(onColorsChangedListener, handler, this.mContext.getUserId());
    }

    public void addOnColorsChangedListener(OnColorsChangedListener onColorsChangedListener, Handler handler, int i) {
        sGlobals.addOnColorsChangedListener(onColorsChangedListener, handler, i, this.mContext.getDisplayId());
    }

    public void removeOnColorsChangedListener(OnColorsChangedListener onColorsChangedListener) {
        removeOnColorsChangedListener(onColorsChangedListener, this.mContext.getUserId());
    }

    public void removeOnColorsChangedListener(OnColorsChangedListener onColorsChangedListener, int i) {
        sGlobals.removeOnColorsChangedListener(onColorsChangedListener, i, this.mContext.getDisplayId());
    }

    public void addOnWallpaperChangedListener(OnWallpaperChangedListener onWallpaperChangedListener, Handler handler) {
        sGlobals.addOnWallpaperChangedListener(onWallpaperChangedListener, handler, this.mContext.getUserId(), this.mContext.getDisplayId());
    }

    public void removeOnWallpaperChangedListener(OnWallpaperChangedListener onWallpaperChangedListener) {
        sGlobals.removeOnWallpaperChangedListener(onWallpaperChangedListener, this.mContext.getUserId(), this.mContext.getDisplayId());
    }

    public WallpaperColors getWallpaperColors(int i) {
        return getWallpaperColors(i, this.mContext.getUserId());
    }

    public WallpaperColors getWallpaperColors(int i, int i2) {
        return sGlobals.getWallpaperColors(i, i2, this.mContext.getDisplayId());
    }

    public ParcelFileDescriptor getWallpaperFile(int i, int i2) {
        if (i == 1 || i == 2) {
            if (sGlobals.mService == null) {
                Log.w(TAG, "WallpaperService not running");
                throw new RuntimeException(new DeadSystemException());
            }
            try {
                return sGlobals.mService.getWallpaperWithFeature(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), null, i, new Bundle(), i2);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (SecurityException e2) {
                if (this.mContext.getApplicationInfo().targetSdkVersion < 27) {
                    Log.w(TAG, "No permission to access wallpaper, suppressing exception to avoid crashing legacy app.");
                    return null;
                }
                throw e2;
            }
        }
        throw new IllegalArgumentException("Must request exactly one kind of wallpaper");
    }

    public void forgetLoadedWallpaper() {
        sGlobals.forgetLoadedWallpaper();
    }

    public WallpaperInfo getWallpaperInfo() {
        return getWallpaperInfo(this.mContext.getUserId());
    }

    public WallpaperInfo getWallpaperInfo(int i) {
        try {
            if (sGlobals.mService != null) {
                return sGlobals.mService.getWallpaperInfo(i);
            }
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getWallpaperId(int i) {
        return getWallpaperIdForUser(i, this.mContext.getUserId());
    }

    public int getWallpaperIdForUser(int i, int i2) {
        try {
            if (sGlobals.mService != null) {
                return sGlobals.mService.getWallpaperIdForUser(i, i2);
            }
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Intent getCropAndSetWallpaperIntent(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Image URI must not be null");
        }
        if (!"content".equals(uri.getScheme())) {
            throw new IllegalArgumentException("Image URI must be of the content scheme type");
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent intent = new Intent(ACTION_CROP_AND_SET_WALLPAPER, uri);
        intent.addFlags(1);
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 65536);
        if (resolveInfoResolveActivity != null) {
            intent.setPackage(resolveInfoResolveActivity.activityInfo.packageName);
            if (packageManager.queryIntentActivities(intent, 0).size() > 0) {
                return intent;
            }
        }
        intent.setPackage(this.mContext.getString(R.string.config_wallpaperCropperPackage));
        if (packageManager.queryIntentActivities(intent, 0).size() > 0) {
            return intent;
        }
        throw new IllegalArgumentException("Cannot use passed URI to set wallpaper; check that the type returned by ContentProvider matches image/*");
    }

    public void setResource(int i) throws Throwable {
        setResource(i, 3);
    }

    public int setResource(int i, int i2) throws Throwable {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        if (sGlobals.mService == null) {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        Bundle bundle = new Bundle();
        WallpaperSetCompletion wallpaperSetCompletion = new WallpaperSetCompletion();
        try {
            Resources resources = this.mContext.getResources();
            ParcelFileDescriptor wallpaper = sGlobals.mService.setWallpaper("res:" + resources.getResourceName(i), this.mContext.getOpPackageName(), null, false, bundle, i2, wallpaperSetCompletion, this.mContext.getUserId());
            if (wallpaper != null) {
                ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream2 = null;
                try {
                    autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(wallpaper);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    copyStreamToWallpaperFile(resources.openRawResource(i), autoCloseOutputStream);
                    autoCloseOutputStream.close();
                    wallpaperSetCompletion.waitForCompletion();
                    IoUtils.closeQuietly(autoCloseOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    autoCloseOutputStream2 = autoCloseOutputStream;
                    IoUtils.closeQuietly(autoCloseOutputStream2);
                    throw th;
                }
            }
            return bundle.getInt(EXTRA_NEW_WALLPAPER_ID, 0);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setBitmap(Bitmap bitmap) throws IOException {
        setBitmap(bitmap, null, true);
    }

    public int setBitmap(Bitmap bitmap, Rect rect, boolean z) throws IOException {
        return setBitmap(bitmap, rect, z, 3);
    }

    public int setBitmap(Bitmap bitmap, Rect rect, boolean z, int i) throws IOException {
        return setBitmap(bitmap, rect, z, i, this.mContext.getUserId());
    }

    public int setBitmap(Bitmap bitmap, Rect rect, boolean z, int i, int i2) throws Throwable {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        validateRect(rect);
        if (sGlobals.mService == null) {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        Bundle bundle = new Bundle();
        WallpaperSetCompletion wallpaperSetCompletion = new WallpaperSetCompletion();
        try {
            ParcelFileDescriptor wallpaper = sGlobals.mService.setWallpaper(null, this.mContext.getOpPackageName(), rect, z, bundle, i, wallpaperSetCompletion, i2);
            if (wallpaper != null) {
                ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream2 = null;
                try {
                    autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(wallpaper);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, autoCloseOutputStream);
                    autoCloseOutputStream.close();
                    wallpaperSetCompletion.waitForCompletion();
                    IoUtils.closeQuietly(autoCloseOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    autoCloseOutputStream2 = autoCloseOutputStream;
                    IoUtils.closeQuietly(autoCloseOutputStream2);
                    throw th;
                }
            }
            return bundle.getInt(EXTRA_NEW_WALLPAPER_ID, 0);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int deleteBitmaps(String[] strArr, ORIENTATION orientation, MODE mode, boolean z) throws IOException {
        return deleteBitmaps(strArr, orientation, mode, z, 3);
    }

    public int deleteBitmaps(String[] strArr, ORIENTATION orientation, MODE mode, boolean z, int i) throws IOException {
        try {
            return sGlobals.mService.removeWallpaperFiles(strArr, i, this.mContext.getUserId(), orientation.toInt(), mode.toInt(), z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int addBitmaps(Map<String, Bitmap> map, ORIENTATION orientation, MODE mode, boolean z) throws IOException {
        return addBitmaps(map, orientation, mode, z, 3);
    }

    public int addBitmaps(Map<String, Bitmap> map, ORIENTATION orientation, MODE mode, boolean z, int i) throws IOException {
        return setBitmapList(map, orientation.toInt(), mode.toInt(), z, i, this.mContext.getUserId(), false);
    }

    public int setBitmapList(Map<String, Bitmap> map, ORIENTATION orientation, MODE mode, boolean z) throws IOException {
        return setBitmapList(map, orientation, mode, z, 3);
    }

    public int setBitmapList(Map<String, Bitmap> map, ORIENTATION orientation, MODE mode, boolean z, int i) throws IOException {
        return setBitmapList(map, orientation.toInt(), mode.toInt(), z, i, this.mContext.getUserId(), true);
    }

    public int setBitmapList(Map<String, Bitmap> map, int i, int i2, boolean z, int i3, int i4, boolean z2) throws IOException {
        final ParcelFileDescriptor[] parcelFileDescriptorArr;
        final String[] strArr;
        final Bitmap[] bitmapArr;
        if (sGlobals.mService == null) {
            Log.w(TAG, "setBitmapList WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        try {
            parcelFileDescriptorArr = new ParcelFileDescriptor[map.size()];
            strArr = new String[map.size()];
            bitmapArr = new Bitmap[map.size()];
            map.keySet();
            Iterator<String> it = map.keySet().iterator();
            for (int i5 = 0; i5 < map.size() && it.hasNext(); i5++) {
                strArr[i5] = it.next();
                bitmapArr[i5] = map.get(strArr[i5]);
            }
            if (z2) {
                sGlobals.mService.removeAllWallpaperFiles(i3, i4, i, i2, z);
            }
        } catch (RemoteException e) {
            e = e;
        }
        try {
            int wallpapers = sGlobals.mService.setWallpapers(null, this.mContext.getOpPackageName(), i3, i4, strArr, parcelFileDescriptorArr, i, i2, z);
            ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(4);
            Log.e(TAG, "setWallpapers ret = " + wallpapers);
            for (int i6 = 0; i6 < wallpapers; i6++) {
                final int i7 = i6;
                executorServiceNewFixedThreadPool.submit(new Runnable() { // from class: android.app.WallpaperManager.1
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
                        Throwable th;
                        Exception e2;
                        String str;
                        StringBuilder sb;
                        String str2;
                        String string = "compress end fileNames : ";
                        try {
                            try {
                                autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptorArr[i7]);
                            } catch (Exception e3) {
                                autoCloseOutputStream = null;
                                e2 = e3;
                            } catch (Throwable th2) {
                                autoCloseOutputStream = null;
                                th = th2;
                                Log.i(WallpaperManager.TAG, string + strArr[i7]);
                                IoUtils.closeQuietly(autoCloseOutputStream);
                                throw th;
                            }
                            try {
                                bitmapArr[i7].compress(Bitmap.CompressFormat.WEBP, 90, autoCloseOutputStream);
                                autoCloseOutputStream.close();
                                str = WallpaperManager.TAG;
                                sb = new StringBuilder();
                                sb.append("compress end fileNames : ");
                                str2 = strArr[i7];
                            } catch (Exception e4) {
                                e2 = e4;
                                Log.e(WallpaperManager.TAG, "setBitmapList ex ", e2);
                                str = WallpaperManager.TAG;
                                sb = new StringBuilder();
                                sb.append("compress end fileNames : ");
                                str2 = strArr[i7];
                            }
                            sb.append(str2);
                            string = sb.toString();
                            Log.i(str, string);
                            IoUtils.closeQuietly(autoCloseOutputStream);
                        } catch (Throwable th3) {
                            th = th3;
                            Log.i(WallpaperManager.TAG, string + strArr[i7]);
                            IoUtils.closeQuietly(autoCloseOutputStream);
                            throw th;
                        }
                    }
                });
            }
            return 0;
        } catch (RemoteException e2) {
            e = e2;
            throw e.rethrowFromSystemServer();
        }
    }

    private final void validateRect(Rect rect) {
        if (rect != null && rect.isEmpty()) {
            throw new IllegalArgumentException("visibleCrop rectangle must be valid and non-empty");
        }
    }

    public void setStream(InputStream inputStream) throws IOException {
        setStream(inputStream, null, true);
    }

    private void copyStreamToWallpaperFile(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        FileUtils.copy(inputStream, fileOutputStream);
    }

    public int setStream(InputStream inputStream, Rect rect, boolean z) throws IOException {
        return setStream(inputStream, rect, z, 3);
    }

    public int setStream(InputStream inputStream, Rect rect, boolean z, int i) throws Throwable {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        validateRect(rect);
        if (sGlobals.mService == null) {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        Bundle bundle = new Bundle();
        WallpaperSetCompletion wallpaperSetCompletion = new WallpaperSetCompletion();
        try {
            ParcelFileDescriptor wallpaper = sGlobals.mService.setWallpaper(null, this.mContext.getOpPackageName(), rect, z, bundle, i, wallpaperSetCompletion, this.mContext.getUserId());
            if (wallpaper != null) {
                ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream2 = null;
                try {
                    autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(wallpaper);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    copyStreamToWallpaperFile(inputStream, autoCloseOutputStream);
                    autoCloseOutputStream.close();
                    wallpaperSetCompletion.waitForCompletion();
                    IoUtils.closeQuietly(autoCloseOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    autoCloseOutputStream2 = autoCloseOutputStream;
                    IoUtils.closeQuietly(autoCloseOutputStream2);
                    throw th;
                }
            }
            return bundle.getInt(EXTRA_NEW_WALLPAPER_ID, 0);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean hasResourceWallpaper(int i) {
        if (sGlobals.mService == null) {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        try {
            return sGlobals.mService.hasNamedWallpaper("res:" + this.mContext.getResources().getResourceName(i));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getDesiredMinimumWidth() {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.getWidthHint(this.mContext.getDisplayId());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "WallpaperService not running");
        throw new RuntimeException(new DeadSystemException());
    }

    public int getDesiredMinimumHeight() {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.getHeightHint(this.mContext.getDisplayId());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "WallpaperService not running");
        throw new RuntimeException(new DeadSystemException());
    }

    public void suggestDesiredDimensions(int i, int i2) {
        int i3 = 0;
        try {
            try {
                i3 = SystemProperties.getInt("sys.max_texture_size", 0);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } catch (Exception unused) {
        }
        if (i3 > 0 && (i > i3 || i2 > i3)) {
            float f = i2 / i;
            if (i > i2) {
                i2 = (int) (((double) (i3 * f)) + 0.5d);
                i = i3;
            } else {
                i = (int) (((double) (i3 / f)) + 0.5d);
                i2 = i3;
            }
        }
        if (sGlobals.mService == null) {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        sGlobals.mService.setDimensionHints(i, i2, this.mContext.getOpPackageName(), this.mContext.getDisplayId());
    }

    public void setDisplayPadding(Rect rect) {
        try {
            if (sGlobals.mService == null) {
                Log.w(TAG, "WallpaperService not running");
                throw new RuntimeException(new DeadSystemException());
            }
            sGlobals.mService.setDisplayPadding(rect, this.mContext.getOpPackageName(), this.mContext.getDisplayId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setDisplayOffset(IBinder iBinder, int i, int i2) {
        try {
            WindowManagerGlobal.getWindowSession().setWallpaperDisplayOffset(iBinder, i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void clearWallpaper() {
        clearWallpaper(2, this.mContext.getUserId());
        clearWallpaper(1, this.mContext.getUserId());
    }

    @SystemApi
    public void clearWallpaper(int i, int i2) {
        if (sGlobals.mService != null) {
            try {
                sGlobals.mService.clearWallpaper(this.mContext.getOpPackageName(), i, i2);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "WallpaperService not running");
        throw new RuntimeException(new DeadSystemException());
    }

    @SystemApi
    public boolean setWallpaperComponent(ComponentName componentName) {
        return setWallpaperComponent(componentName, this.mContext.getUserId());
    }

    public boolean setWallpaperComponent(ComponentName componentName, int i) {
        if (sGlobals.mService != null) {
            try {
                sGlobals.mService.setWallpaperComponentChecked(componentName, this.mContext.getOpPackageName(), i);
                return true;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "WallpaperService not running");
        throw new RuntimeException(new DeadSystemException());
    }

    public void setWallpaperOffsets(IBinder iBinder, float f, float f2) {
        try {
            WindowManagerGlobal.getWindowSession().setWallpaperPosition(iBinder, f, f2, this.mWallpaperXStep, this.mWallpaperYStep);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setWallpaperOffsetSteps(float f, float f2) {
        this.mWallpaperXStep = f;
        this.mWallpaperYStep = f2;
    }

    public void sendWallpaperCommand(IBinder iBinder, String str, int i, int i2, int i3, Bundle bundle) {
        try {
            WindowManagerGlobal.getWindowSession().sendWallpaperCommand(iBinder, str, i, i2, i3, bundle, false);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setWallpaperZoomOut(IBinder iBinder, float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("zoom must be between 0 and one: " + f);
        }
        try {
            WindowManagerGlobal.getWindowSession().setWallpaperZoomOut(iBinder, f);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isWallpaperSupported() {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.isWallpaperSupported(this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "WallpaperService not running");
        throw new RuntimeException(new DeadSystemException());
    }

    public boolean isSetWallpaperAllowed() {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.isSetWallpaperAllowed(this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "WallpaperService not running");
        throw new RuntimeException(new DeadSystemException());
    }

    public void clearWallpaperOffsets(IBinder iBinder) {
        try {
            WindowManagerGlobal.getWindowSession().setWallpaperPosition(iBinder, -1.0f, -1.0f, -1.0f, -1.0f);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void clear() throws IOException {
        setStream(openDefaultWallpaper(this.mContext, 1), null, false);
    }

    public void clear(int i) throws IOException {
        if ((i & 1) != 0) {
            clear();
        }
        if ((i & 2) != 0) {
            clearWallpaper(2, this.mContext.getUserId());
        }
    }

    public static InputStream openDefaultWallpaper(Context context, int i) {
        if (i == 2) {
            return null;
        }
        String str = SystemProperties.get(PROP_WALLPAPER);
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists()) {
                try {
                    return new FileInputStream(file);
                } catch (IOException unused) {
                }
            }
        }
        try {
            return context.getResources().openRawResource(R.drawable.default_wallpaper);
        } catch (Resources.NotFoundException unused2) {
            return null;
        }
    }

    public static ComponentName getDefaultWallpaperComponent(Context context) {
        String str = SystemProperties.get(PROP_WALLPAPER_COMPONENT);
        ComponentName componentNameUnflattenFromString = !TextUtils.isEmpty(str) ? ComponentName.unflattenFromString(str) : null;
        if (componentNameUnflattenFromString == null) {
            String string = context.getString(R.string.default_wallpaper_component);
            if (!TextUtils.isEmpty(string)) {
                componentNameUnflattenFromString = ComponentName.unflattenFromString(string);
            }
        }
        if (componentNameUnflattenFromString != null) {
            try {
                context.getPackageManager().getPackageInfo(componentNameUnflattenFromString.getPackageName(), 786432);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        return componentNameUnflattenFromString;
    }

    public boolean setLockWallpaperCallback(IWallpaperManagerCallback iWallpaperManagerCallback) {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.setLockWallpaperCallback(iWallpaperManagerCallback);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "WallpaperService not running");
        throw new RuntimeException(new DeadSystemException());
    }

    public boolean isWallpaperBackupEligible(int i) {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.isWallpaperBackupEligible(i, this.mContext.getUserId());
            } catch (RemoteException e) {
                Log.e(TAG, "Exception querying wallpaper backup eligibility: " + e.getMessage());
                return false;
            }
        }
        Log.w(TAG, "WallpaperService not running");
        throw new RuntimeException(new DeadSystemException());
    }

    public ColorManagementProxy getColorManagementProxy() {
        return this.mCmProxy;
    }

    public static class ColorManagementProxy {
        private final Set<ColorSpace> mSupportedColorSpaces = new HashSet();

        public ColorManagementProxy(Context context) {
            Display displayNoVerify = context.getDisplayNoVerify();
            if (displayNoVerify != null) {
                this.mSupportedColorSpaces.addAll(Arrays.asList(displayNoVerify.getSupportedWideColorGamut()));
            }
        }

        public Set<ColorSpace> getSupportedColorSpaces() {
            return this.mSupportedColorSpaces;
        }

        boolean isSupportedColorSpace(ColorSpace colorSpace) {
            return colorSpace != null && (colorSpace == ColorSpace.get(ColorSpace.Named.SRGB) || getSupportedColorSpaces().contains(colorSpace));
        }

        void doColorManagement(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo) {
            if (isSupportedColorSpace(imageInfo.getColorSpace())) {
                return;
            }
            imageDecoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
            Log.w(WallpaperManager.TAG, "Not supported color space: " + imageInfo.getColorSpace());
        }
    }

    private class WallpaperSetCompletion extends IWallpaperManagerCallback.Stub {
        final CountDownLatch mLatch = new CountDownLatch(1);

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperListChanged() {
        }

        public WallpaperSetCompletion() {
        }

        public void waitForCompletion() {
            try {
                this.mLatch.await(30L, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperChanged() throws RemoteException {
            this.mLatch.countDown();
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperColorsChanged(WallpaperColors wallpaperColors, int i, int i2) throws RemoteException {
            WallpaperManager.sGlobals.onWallpaperColorsChanged(wallpaperColors, i, i2);
        }
    }

    public interface OnColorsChangedListener {
        void onColorsChanged(WallpaperColors wallpaperColors, int i);

        default void onColorsChanged(WallpaperColors wallpaperColors, int i, int i2) {
            onColorsChanged(wallpaperColors, i);
        }
    }
}
