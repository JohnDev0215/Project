package com.bumptech.glide.load.p.d;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.p.d.o;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.bumptech.glide.load.h<com.bumptech.glide.load.b> f2338a = com.bumptech.glide.load.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", com.bumptech.glide.load.b.f1965d);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.bumptech.glide.load.h<com.bumptech.glide.load.j> f2339b = com.bumptech.glide.load.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace", com.bumptech.glide.load.j.SRGB);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public static final com.bumptech.glide.load.h<i> f2340c = i.h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.bumptech.glide.load.h<Boolean> f2341d;
    public static final com.bumptech.glide.load.h<Boolean> e;
    private static final Set<String> f;
    private static final b g;
    private static final Set<ImageHeaderParser.ImageType> h;
    private static final Queue<BitmapFactory.Options> i;
    private final com.bumptech.glide.load.n.a0.e j;
    private final DisplayMetrics k;
    private final com.bumptech.glide.load.n.a0.b l;
    private final List<ImageHeaderParser> m;
    private final n n = n.a();

    class a implements b {
        a() {
        }

        @Override // com.bumptech.glide.load.p.d.j.b
        public void a(com.bumptech.glide.load.n.a0.e eVar, Bitmap bitmap) {
        }

        @Override // com.bumptech.glide.load.p.d.j.b
        public void b() {
        }
    }

    public interface b {
        void a(com.bumptech.glide.load.n.a0.e eVar, Bitmap bitmap);

        void b();
    }

    static {
        Boolean bool = Boolean.FALSE;
        f2341d = com.bumptech.glide.load.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", bool);
        e = com.bumptech.glide.load.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", bool);
        f = Collections.unmodifiableSet(new HashSet(Arrays.asList("image/vnd.wap.wbmp", "image/x-ico")));
        g = new a();
        h = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
        i = com.bumptech.glide.t.k.e(0);
    }

    public j(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, com.bumptech.glide.load.n.a0.e eVar, com.bumptech.glide.load.n.a0.b bVar) {
        this.m = list;
        this.k = (DisplayMetrics) com.bumptech.glide.t.j.d(displayMetrics);
        this.j = (com.bumptech.glide.load.n.a0.e) com.bumptech.glide.t.j.d(eVar);
        this.l = (com.bumptech.glide.load.n.a0.b) com.bumptech.glide.t.j.d(bVar);
    }

    private static int a(double d2) {
        int iL = l(d2);
        int iX = x(((double) iL) * d2);
        return x((d2 / ((double) (iX / iL))) * ((double) iX));
    }

    private void b(o oVar, com.bumptech.glide.load.b bVar, boolean z, boolean z2, BitmapFactory.Options options, int i2, int i3) {
        if (this.n.e(i2, i3, options, z, z2)) {
            return;
        }
        if (bVar == com.bumptech.glide.load.b.PREFER_ARGB_8888 || Build.VERSION.SDK_INT == 16) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return;
        }
        boolean zHasAlpha = false;
        try {
            zHasAlpha = oVar.b().hasAlpha();
        } catch (IOException e2) {
            if (Log.isLoggable("Downsampler", 3)) {
                Log.d("Downsampler", "Cannot determine whether the image has alpha or not from header, format " + bVar, e2);
            }
        }
        Bitmap.Config config = zHasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        options.inPreferredConfig = config;
        if (config == Bitmap.Config.RGB_565) {
            options.inDither = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void c(com.bumptech.glide.load.ImageHeaderParser.ImageType r18, com.bumptech.glide.load.p.d.o r19, com.bumptech.glide.load.p.d.j.b r20, com.bumptech.glide.load.n.a0.e r21, com.bumptech.glide.load.p.d.i r22, int r23, int r24, int r25, int r26, int r27, android.graphics.BitmapFactory.Options r28) {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.p.d.j.c(com.bumptech.glide.load.ImageHeaderParser$ImageType, com.bumptech.glide.load.p.d.o, com.bumptech.glide.load.p.d.j$b, com.bumptech.glide.load.n.a0.e, com.bumptech.glide.load.p.d.i, int, int, int, int, int, android.graphics.BitmapFactory$Options):void");
    }

    private com.bumptech.glide.load.n.v<Bitmap> e(o oVar, int i2, int i3, com.bumptech.glide.load.i iVar, b bVar) {
        byte[] bArr = (byte[]) this.l.e(65536, byte[].class);
        BitmapFactory.Options optionsK = k();
        optionsK.inTempStorage = bArr;
        com.bumptech.glide.load.b bVar2 = (com.bumptech.glide.load.b) iVar.c(f2338a);
        com.bumptech.glide.load.j jVar = (com.bumptech.glide.load.j) iVar.c(f2339b);
        i iVar2 = (i) iVar.c(i.h);
        boolean zBooleanValue = ((Boolean) iVar.c(f2341d)).booleanValue();
        com.bumptech.glide.load.h<Boolean> hVar = e;
        try {
            return e.g(h(oVar, optionsK, iVar2, bVar2, jVar, iVar.c(hVar) != null && ((Boolean) iVar.c(hVar)).booleanValue(), i2, i3, zBooleanValue, bVar), this.j);
        } finally {
            v(optionsK);
            this.l.d(bArr);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0170, code lost:
    
        if (r0 >= 26) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Bitmap h(com.bumptech.glide.load.p.d.o r28, android.graphics.BitmapFactory.Options r29, com.bumptech.glide.load.p.d.i r30, com.bumptech.glide.load.b r31, com.bumptech.glide.load.j r32, boolean r33, int r34, int r35, boolean r36, com.bumptech.glide.load.p.d.j.b r37) {
        /*
            Method dump skipped, instruction units count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.p.d.j.h(com.bumptech.glide.load.p.d.o, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.p.d.i, com.bumptech.glide.load.b, com.bumptech.glide.load.j, boolean, int, int, boolean, com.bumptech.glide.load.p.d.j$b):android.graphics.Bitmap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
    
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap i(com.bumptech.glide.load.p.d.o r5, android.graphics.BitmapFactory.Options r6, com.bumptech.glide.load.p.d.j.b r7, com.bumptech.glide.load.n.a0.e r8) {
        /*
            java.lang.String r0 = "Downsampler"
            boolean r1 = r6.inJustDecodeBounds
            if (r1 != 0) goto Lc
            r7.b()
            r5.c()
        Lc:
            int r1 = r6.outWidth
            int r2 = r6.outHeight
            java.lang.String r3 = r6.outMimeType
            java.util.concurrent.locks.Lock r4 = com.bumptech.glide.load.p.d.v.c()
            r4.lock()
            android.graphics.Bitmap r5 = r5.a(r6)     // Catch: java.lang.Throwable -> L25 java.lang.IllegalArgumentException -> L27
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.p.d.v.c()
            r6.unlock()
            return r5
        L25:
            r5 = move-exception
            goto L50
        L27:
            r4 = move-exception
            java.io.IOException r1 = u(r4, r1, r2, r3, r6)     // Catch: java.lang.Throwable -> L25
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch: java.lang.Throwable -> L25
            if (r2 == 0) goto L38
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r0, r2, r1)     // Catch: java.lang.Throwable -> L25
        L38:
            android.graphics.Bitmap r0 = r6.inBitmap     // Catch: java.lang.Throwable -> L25
            if (r0 == 0) goto L4f
            r8.d(r0)     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L4e
            r0 = 0
            r6.inBitmap = r0     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L4e
            android.graphics.Bitmap r5 = i(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L4e
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.p.d.v.c()
            r6.unlock()
            return r5
        L4e:
            throw r1     // Catch: java.lang.Throwable -> L25
        L4f:
            throw r1     // Catch: java.lang.Throwable -> L25
        L50:
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.p.d.v.c()
            r6.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.p.d.j.i(com.bumptech.glide.load.p.d.o, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.p.d.j$b, com.bumptech.glide.load.n.a0.e):android.graphics.Bitmap");
    }

    @TargetApi(19)
    private static String j(Bitmap bitmap) {
        String str;
        if (bitmap == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            str = " (" + bitmap.getAllocationByteCount() + ")";
        } else {
            str = "";
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + str;
    }

    private static synchronized BitmapFactory.Options k() {
        BitmapFactory.Options optionsPoll;
        Queue<BitmapFactory.Options> queue = i;
        synchronized (queue) {
            optionsPoll = queue.poll();
        }
        if (optionsPoll == null) {
            optionsPoll = new BitmapFactory.Options();
            w(optionsPoll);
        }
        return optionsPoll;
    }

    private static int l(double d2) {
        if (d2 > 1.0d) {
            d2 = 1.0d / d2;
        }
        return (int) Math.round(d2 * 2.147483647E9d);
    }

    private static int[] m(o oVar, BitmapFactory.Options options, b bVar, com.bumptech.glide.load.n.a0.e eVar) {
        options.inJustDecodeBounds = true;
        i(oVar, options, bVar, eVar);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static String n(BitmapFactory.Options options) {
        return j(options.inBitmap);
    }

    private static boolean r(int i2) {
        return i2 == 90 || i2 == 270;
    }

    private static boolean s(BitmapFactory.Options options) {
        int i2;
        int i3 = options.inTargetDensity;
        return i3 > 0 && (i2 = options.inDensity) > 0 && i3 != i2;
    }

    private static void t(int i2, int i3, String str, BitmapFactory.Options options, Bitmap bitmap, int i4, int i5, long j) {
        Log.v("Downsampler", "Decoded " + j(bitmap) + " from [" + i2 + "x" + i3 + "] " + str + " with inBitmap " + n(options) + " for [" + i4 + "x" + i5 + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + com.bumptech.glide.t.f.a(j));
    }

    private static IOException u(IllegalArgumentException illegalArgumentException, int i2, int i3, String str, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + i2 + ", outHeight: " + i3 + ", outMimeType: " + str + ", inBitmap: " + n(options), illegalArgumentException);
    }

    private static void v(BitmapFactory.Options options) {
        w(options);
        Queue<BitmapFactory.Options> queue = i;
        synchronized (queue) {
            queue.offer(options);
        }
    }

    private static void w(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            options.inPreferredColorSpace = null;
            options.outColorSpace = null;
            options.outConfig = null;
        }
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    private static int x(double d2) {
        return (int) (d2 + 0.5d);
    }

    @TargetApi(26)
    private static void y(BitmapFactory.Options options, com.bumptech.glide.load.n.a0.e eVar, int i2, int i3) {
        Bitmap.Config config;
        if (Build.VERSION.SDK_INT < 26) {
            config = null;
        } else if (options.inPreferredConfig == Bitmap.Config.HARDWARE) {
            return;
        } else {
            config = options.outConfig;
        }
        if (config == null) {
            config = options.inPreferredConfig;
        }
        options.inBitmap = eVar.e(i2, i3, config);
    }

    private boolean z(ImageHeaderParser.ImageType imageType) {
        if (Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        return h.contains(imageType);
    }

    public com.bumptech.glide.load.n.v<Bitmap> d(ParcelFileDescriptor parcelFileDescriptor, int i2, int i3, com.bumptech.glide.load.i iVar) {
        return e(new o.b(parcelFileDescriptor, this.m, this.l), i2, i3, iVar, g);
    }

    public com.bumptech.glide.load.n.v<Bitmap> f(InputStream inputStream, int i2, int i3, com.bumptech.glide.load.i iVar) {
        return g(inputStream, i2, i3, iVar, g);
    }

    public com.bumptech.glide.load.n.v<Bitmap> g(InputStream inputStream, int i2, int i3, com.bumptech.glide.load.i iVar, b bVar) {
        return e(new o.a(inputStream, this.m, this.l), i2, i3, iVar, bVar);
    }

    public boolean o(ParcelFileDescriptor parcelFileDescriptor) {
        return ParcelFileDescriptorRewinder.c();
    }

    public boolean p(InputStream inputStream) {
        return true;
    }

    public boolean q(ByteBuffer byteBuffer) {
        return true;
    }
}
