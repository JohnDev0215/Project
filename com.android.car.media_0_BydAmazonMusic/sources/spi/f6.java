package spi;

import android.car.utils.Logs;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import androidx.annotation.UiThread;
import com.android.car.ui.R$color;
import com.android.car.ui.R$integer;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

/* JADX INFO: compiled from: LocalImageFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class f6 {
    public static final boolean g = Log.isLoggable("LocalImageFetcher", 5);
    public static final boolean h = Log.isLoggable("LocalImageFetcher", 3);
    public static f6 i;
    public final int a;
    public final LruCache<String, Executor> b;
    public final Map<w4, HashSet<BiConsumer<w4, Drawable>>> c = new HashMap(20);
    public final Map<w4, b> d = new HashMap(20);
    public final LruCache<w4, Drawable> e;
    public final boolean f;

    /* JADX INFO: compiled from: LocalImageFetcher.java */
    public class a extends LruCache<w4, Drawable> {
        public final /* synthetic */ int a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(f6 f6Var, int i, int i2) {
            super(i);
            this.a = i2;
        }

        @Override // android.util.LruCache
        public final int sizeOf(w4 w4Var, Drawable drawable) {
            Drawable drawable2 = drawable;
            return drawable2 instanceof BitmapDrawable ? ((BitmapDrawable) drawable2).getBitmap().getAllocationByteCount() : this.a * 1024;
        }
    }

    /* JADX INFO: compiled from: LocalImageFetcher.java */
    public static class b extends AsyncTask<Void, Void, Drawable> {
        public final WeakReference<Context> a;
        public final w4 b;
        public final boolean c;
        public final a d = new a();
        public int e = 1;

        /* JADX INFO: compiled from: LocalImageFetcher.java */
        public class a implements ImageDecoder.OnHeaderDecodedListener {
            public a() {
            }

            @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
            public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                if (b.this.isCancelled()) {
                    throw new CancellationException();
                }
                imageDecoder.setAllocator(b.this.e);
                int width = b.this.b.b.getWidth();
                int height = b.this.b.b.getHeight();
                int width2 = imageInfo.getSize().getWidth();
                int height2 = imageInfo.getSize().getHeight();
                if (width2 > width || height2 > height) {
                    float f = width2;
                    float f2 = height2;
                    float fMin = Math.min(width / f, height / f2);
                    imageDecoder.setTargetSize(Math.round(f * fMin), Math.round(fMin * f2));
                }
            }
        }

        @UiThread
        public b(Context context, w4 w4Var, boolean z) {
            this.a = new WeakReference<>(context.getApplicationContext());
            this.b = w4Var;
            this.c = z;
        }

        @Override // android.os.AsyncTask
        public final Drawable doInBackground(Void[] voidArr) {
            ByteArrayOutputStream byteArrayOutputStream;
            byte[] bArr;
            Resources resourcesForApplication;
            try {
                if (isCancelled()) {
                    return null;
                }
                Uri uri = this.b.a;
                Context context = this.a.get();
                if (context == null) {
                    return null;
                }
                Logs.d("LocalImageFetcher", "imageUri: " + uri.toString());
                if ("android.resource".equals(uri.getScheme())) {
                    Intent.ShortcutIconResource shortcutIconResourceA = dc.a(context, uri);
                    try {
                        resourcesForApplication = context.getPackageManager().getResourcesForApplication(shortcutIconResourceA.packageName);
                    } catch (PackageManager.NameNotFoundException unused) {
                        resourcesForApplication = null;
                    }
                    if (resourcesForApplication == null) {
                        return null;
                    }
                    return resourcesForApplication.getDrawable(resourcesForApplication.getIdentifier(shortcutIconResourceA.resourceName, null, null), null);
                }
                if ("content".equals(uri.getScheme()) || "file".equals(uri.getScheme())) {
                    return ImageDecoder.decodeDrawable(ImageDecoder.createSource(context.getContentResolver(), uri), this.d);
                }
                if (!this.c) {
                    return null;
                }
                this.e = 1;
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(uri.toString()).openStream());
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        bArr = new byte[4096];
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
                while (true) {
                    int i = bufferedInputStream.read(bArr);
                    if (-1 == i) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), x.a(ImageDecoder.decodeBitmap(ImageDecoder.createSource(ByteBuffer.wrap(byteArrayOutputStream.toByteArray())), this.d), context.getColor(R$color.improper_image_refs_tint_color)));
                        byteArrayOutputStream.close();
                        bufferedInputStream.close();
                        return bitmapDrawable;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                    bufferedInputStream.close();
                    throw th;
                }
            } catch (IOException e) {
                Log.e("LocalImageFetcher", "ImageLoadingTask#doInBackground: " + e);
                return null;
            } catch (CancellationException unused2) {
                return null;
            }
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [java.util.HashMap, java.util.Map<spi.w4, spi.f6$b>] */
        /* JADX WARN: Type inference failed for: r3v0, types: [java.util.HashMap, java.util.Map<spi.w4, spi.f6$b>] */
        /* JADX WARN: Type inference failed for: r4v4, types: [java.util.HashMap, java.util.Map<spi.w4, java.util.HashSet<java.util.function.BiConsumer<spi.w4, android.graphics.drawable.Drawable>>>] */
        @Override // android.os.AsyncTask
        @UiThread
        public final void onPostExecute(Drawable drawable) {
            Drawable drawable2 = drawable;
            if (f6.h) {
                StringBuilder sbK = c.k("onPostExecute canceled:  ");
                sbK.append(isCancelled());
                sbK.append(" drawable: ");
                sbK.append(drawable2);
                sbK.append(" ");
                sbK.append(this.b.a);
                Log.d("LocalImageFetcher", sbK.toString());
            }
            if (isCancelled()) {
                return;
            }
            f6 f6Var = f6.i;
            if (f6Var == null) {
                Log.e("LocalImageFetcher", "ImageLoadingTask#onPostExecute: LocalImageFetcher was reset !");
                return;
            }
            w4 w4Var = this.b;
            if (((b) f6Var.d.get(w4Var)) != this) {
                if (f6.g) {
                    StringBuilder sbK2 = c.k("A new task already started for: ");
                    sbK2.append(this.b);
                    Log.w("LocalImageFetcher", sbK2.toString());
                    return;
                }
                return;
            }
            if (drawable2 != null) {
                f6Var.e.put(w4Var, drawable2);
            }
            HashSet hashSet = (HashSet) f6Var.c.remove(w4Var);
            f6Var.d.remove(w4Var);
            if (hashSet != null) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    ((BiConsumer) it.next()).accept(w4Var, drawable2);
                }
            }
        }
    }

    @UiThread
    private f6(Context context) {
        Resources resources = context.getResources();
        int integer = resources.getInteger(R$integer.image_fetcher_thread_pools_max_count);
        this.a = resources.getInteger(R$integer.image_fetcher_thread_pool_size);
        this.b = new LruCache<>(integer);
        this.e = new a(this, resources.getInteger(R$integer.bitmap_memory_cache_max_size_mb) * 1048576, resources.getInteger(R$integer.drawable_default_weight_kb));
        this.f = h2.a(context).b();
    }

    public static f6 a(Context context) {
        if (i == null) {
            i = new f6(context);
        }
        return i;
    }
}
