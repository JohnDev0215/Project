package x1;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
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
import x1.l;

/* JADX INFO: compiled from: Downsampler.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n1.h<n1.b> f9774f = n1.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", n1.b.DEFAULT);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final n1.h<n1.j> f9775g = n1.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace", n1.j.SRGB);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @Deprecated
    public static final n1.h<l> f9776h = l.f9771h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final n1.h<Boolean> f9777i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final n1.h<Boolean> f9778j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final Set<String> f9779k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final b f9780l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final Set<ImageHeaderParser.ImageType> f9781m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final Queue<BitmapFactory.Options> f9782n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r1.d f9783a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DisplayMetrics f9784b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final r1.b f9785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<ImageHeaderParser> f9786d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final r f9787e = r.a();

    /* JADX INFO: compiled from: Downsampler.java */
    class a implements b {
        a() {
        }

        @Override // x1.m.b
        public void a(r1.d dVar, Bitmap bitmap) {
        }

        @Override // x1.m.b
        public void b() {
        }
    }

    /* JADX INFO: compiled from: Downsampler.java */
    public interface b {
        void a(r1.d dVar, Bitmap bitmap);

        void b();
    }

    static {
        Boolean bool = Boolean.FALSE;
        f9777i = n1.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", bool);
        f9778j = n1.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", bool);
        f9779k = Collections.unmodifiableSet(new HashSet(Arrays.asList("image/vnd.wap.wbmp", "image/x-ico")));
        f9780l = new a();
        f9781m = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
        f9782n = k2.k.e(0);
    }

    public m(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, r1.d dVar, r1.b bVar) {
        this.f9786d = list;
        this.f9784b = (DisplayMetrics) k2.j.d(displayMetrics);
        this.f9783a = (r1.d) k2.j.d(dVar);
        this.f9785c = (r1.b) k2.j.d(bVar);
    }

    private static int a(double d7) {
        int iJ = j(d7);
        int iU = u(((double) iJ) * d7);
        return u((d7 / ((double) (iU / iJ))) * ((double) iU));
    }

    private void b(InputStream inputStream, n1.b bVar, boolean z6, boolean z7, BitmapFactory.Options options, int i7, int i8) {
        if (this.f9787e.e(i7, i8, options, z6, z7)) {
            return;
        }
        if (bVar == n1.b.PREFER_ARGB_8888) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return;
        }
        boolean zHasAlpha = false;
        try {
            zHasAlpha = com.bumptech.glide.load.a.b(this.f9786d, inputStream, this.f9785c).hasAlpha();
        } catch (IOException e7) {
            if (Log.isLoggable("Downsampler", 3)) {
                Log.d("Downsampler", "Cannot determine whether the image has alpha or not from header, format " + bVar, e7);
            }
        }
        Bitmap.Config config = zHasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        options.inPreferredConfig = config;
        if (config == Bitmap.Config.RGB_565) {
            options.inDither = true;
        }
    }

    private static void c(ImageHeaderParser.ImageType imageType, InputStream inputStream, b bVar, r1.d dVar, l lVar, int i7, int i8, int i9, int i10, int i11, BitmapFactory.Options options) throws IOException {
        int i12;
        int i13;
        int iFloor;
        int iFloor2;
        if (i8 <= 0 || i9 <= 0) {
            if (Log.isLoggable("Downsampler", 3)) {
                Log.d("Downsampler", "Unable to determine dimensions for: " + imageType + " with target [" + i10 + "x" + i11 + "]");
                return;
            }
            return;
        }
        if (o(i7)) {
            i13 = i8;
            i12 = i9;
        } else {
            i12 = i8;
            i13 = i9;
        }
        float fB = lVar.b(i12, i13, i10, i11);
        if (fB <= 0.0f) {
            throw new IllegalArgumentException("Cannot scale with factor: " + fB + " from: " + lVar + ", source: [" + i8 + "x" + i9 + "], target: [" + i10 + "x" + i11 + "]");
        }
        l.g gVarA = lVar.a(i12, i13, i10, i11);
        if (gVarA == null) {
            throw new IllegalArgumentException("Cannot round with null rounding");
        }
        float f7 = i12;
        float f8 = i13;
        int iU = i12 / u(fB * f7);
        int iU2 = i13 / u(fB * f8);
        l.g gVar = l.g.MEMORY;
        int iMax = Math.max(1, Integer.highestOneBit(gVarA == gVar ? Math.max(iU, iU2) : Math.min(iU, iU2)));
        if (gVarA == gVar && iMax < 1.0f / fB) {
            iMax <<= 1;
        }
        options.inSampleSize = iMax;
        if (imageType == ImageHeaderParser.ImageType.JPEG) {
            float fMin = Math.min(iMax, 8);
            iFloor = (int) Math.ceil(f7 / fMin);
            iFloor2 = (int) Math.ceil(f8 / fMin);
            int i14 = iMax / 8;
            if (i14 > 0) {
                iFloor /= i14;
                iFloor2 /= i14;
            }
        } else if (imageType == ImageHeaderParser.ImageType.PNG || imageType == ImageHeaderParser.ImageType.PNG_A) {
            float f9 = iMax;
            iFloor = (int) Math.floor(f7 / f9);
            iFloor2 = (int) Math.floor(f8 / f9);
        } else if (imageType == ImageHeaderParser.ImageType.WEBP || imageType == ImageHeaderParser.ImageType.WEBP_A) {
            float f10 = iMax;
            iFloor = Math.round(f7 / f10);
            iFloor2 = Math.round(f8 / f10);
        } else if (i12 % iMax == 0 && i13 % iMax == 0) {
            iFloor = i12 / iMax;
            iFloor2 = i13 / iMax;
        } else {
            int[] iArrK = k(inputStream, options, bVar, dVar);
            iFloor = iArrK[0];
            iFloor2 = iArrK[1];
        }
        double dB = lVar.b(iFloor, iFloor2, i10, i11);
        options.inTargetDensity = a(dB);
        options.inDensity = j(dB);
        if (p(options)) {
            options.inScaled = true;
        } else {
            options.inTargetDensity = 0;
            options.inDensity = 0;
        }
        if (Log.isLoggable("Downsampler", 2)) {
            Log.v("Downsampler", "Calculate scaling, source: [" + i8 + "x" + i9 + "], degreesToRotate: " + i7 + ", target: [" + i10 + "x" + i11 + "], power of two scaled: [" + iFloor + "x" + iFloor2 + "], exact scale factor: " + fB + ", power of 2 sample size: " + iMax + ", adjusted scale factor: " + dB + ", target density: " + options.inTargetDensity + ", density: " + options.inDensity);
        }
    }

    private Bitmap f(InputStream inputStream, BitmapFactory.Options options, l lVar, n1.b bVar, n1.j jVar, boolean z6, int i7, int i8, boolean z7, b bVar2) throws IOException {
        int i9;
        int i10;
        int i11;
        ColorSpace colorSpace;
        int iRound;
        int iRound2;
        long jB = k2.f.b();
        int[] iArrK = k(inputStream, options, bVar2, this.f9783a);
        boolean z8 = false;
        int i12 = iArrK[0];
        int i13 = iArrK[1];
        String str = options.outMimeType;
        boolean z9 = (i12 == -1 || i13 == -1) ? false : z6;
        int iA = com.bumptech.glide.load.a.a(this.f9786d, inputStream, this.f9785c);
        int i14 = y.i(iA);
        boolean zL = y.l(iA);
        if (i7 == Integer.MIN_VALUE) {
            i9 = i8;
            i10 = o(i14) ? i13 : i12;
        } else {
            i9 = i8;
            i10 = i7;
        }
        int i15 = i9 == Integer.MIN_VALUE ? o(i14) ? i12 : i13 : i9;
        ImageHeaderParser.ImageType imageTypeB = com.bumptech.glide.load.a.b(this.f9786d, inputStream, this.f9785c);
        c(imageTypeB, inputStream, bVar2, this.f9783a, lVar, i14, i12, i13, i10, i15, options);
        b(inputStream, bVar, z9, zL, options, i10, i15);
        int i16 = options.inSampleSize;
        if (w(imageTypeB)) {
            if (i12 < 0 || i13 < 0 || !z7) {
                float f7 = p(options) ? options.inTargetDensity / options.inDensity : 1.0f;
                int i17 = options.inSampleSize;
                float f8 = i17;
                int iCeil = (int) Math.ceil(i12 / f8);
                int iCeil2 = (int) Math.ceil(i13 / f8);
                iRound = Math.round(iCeil * f7);
                iRound2 = Math.round(iCeil2 * f7);
                if (Log.isLoggable("Downsampler", 2)) {
                    Log.v("Downsampler", "Calculated target [" + iRound + "x" + iRound2 + "] for source [" + i12 + "x" + i13 + "], sampleSize: " + i17 + ", targetDensity: " + options.inTargetDensity + ", density: " + options.inDensity + ", density multiplier: " + f7);
                }
            } else {
                iRound = i10;
                iRound2 = i15;
            }
            if (iRound > 0 && iRound2 > 0) {
                v(options, this.f9783a, iRound, iRound2);
            }
        }
        if (jVar == n1.j.DISPLAY_P3 && (colorSpace = options.outColorSpace) != null && colorSpace.isWideGamut()) {
            z8 = true;
        }
        options.inPreferredColorSpace = ColorSpace.get(z8 ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB);
        Bitmap bitmapG = g(inputStream, options, bVar2, this.f9783a);
        bVar2.a(this.f9783a, bitmapG);
        if (Log.isLoggable("Downsampler", 2)) {
            i11 = iA;
            q(i12, i13, str, options, bitmapG, i7, i8, jB);
        } else {
            i11 = iA;
        }
        Bitmap bitmapM = null;
        if (bitmapG != null) {
            bitmapG.setDensity(this.f9784b.densityDpi);
            bitmapM = y.m(this.f9783a, bitmapG, i11);
            if (!bitmapG.equals(bitmapM)) {
                this.f9783a.d(bitmapG);
            }
        }
        return bitmapM;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap g(java.io.InputStream r6, android.graphics.BitmapFactory.Options r7, x1.m.b r8, r1.d r9) throws java.io.IOException {
        /*
            java.lang.String r0 = "Downsampler"
            boolean r1 = r7.inJustDecodeBounds
            if (r1 == 0) goto Lc
            r1 = 10485760(0xa00000, float:1.469368E-38)
            r6.mark(r1)
            goto Lf
        Lc:
            r8.b()
        Lf:
            int r1 = r7.outWidth
            int r2 = r7.outHeight
            java.lang.String r3 = r7.outMimeType
            java.util.concurrent.locks.Lock r4 = x1.y.h()
            r4.lock()
            r4 = 0
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r6, r4, r7)     // Catch: java.lang.Throwable -> L30 java.lang.IllegalArgumentException -> L32
            java.util.concurrent.locks.Lock r9 = x1.y.h()
            r9.unlock()
            boolean r7 = r7.inJustDecodeBounds
            if (r7 == 0) goto L2f
            r6.reset()
        L2f:
            return r8
        L30:
            r6 = move-exception
            goto L5f
        L32:
            r5 = move-exception
            java.io.IOException r1 = r(r5, r1, r2, r3, r7)     // Catch: java.lang.Throwable -> L30
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L43
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r0, r2, r1)     // Catch: java.lang.Throwable -> L30
        L43:
            android.graphics.Bitmap r0 = r7.inBitmap     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L5e
            r6.reset()     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            android.graphics.Bitmap r0 = r7.inBitmap     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            r9.d(r0)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            r7.inBitmap = r4     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            android.graphics.Bitmap r6 = g(r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            java.util.concurrent.locks.Lock r7 = x1.y.h()
            r7.unlock()
            return r6
        L5d:
            throw r1     // Catch: java.lang.Throwable -> L30
        L5e:
            throw r1     // Catch: java.lang.Throwable -> L30
        L5f:
            java.util.concurrent.locks.Lock r7 = x1.y.h()
            r7.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: x1.m.g(java.io.InputStream, android.graphics.BitmapFactory$Options, x1.m$b, r1.d):android.graphics.Bitmap");
    }

    @TargetApi(19)
    private static String h(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + (" (" + bitmap.getAllocationByteCount() + ")");
    }

    private static synchronized BitmapFactory.Options i() {
        BitmapFactory.Options optionsPoll;
        Queue<BitmapFactory.Options> queue = f9782n;
        synchronized (queue) {
            optionsPoll = queue.poll();
        }
        if (optionsPoll == null) {
            optionsPoll = new BitmapFactory.Options();
            t(optionsPoll);
        }
        return optionsPoll;
    }

    private static int j(double d7) {
        if (d7 > 1.0d) {
            d7 = 1.0d / d7;
        }
        return (int) Math.round(d7 * 2.147483647E9d);
    }

    private static int[] k(InputStream inputStream, BitmapFactory.Options options, b bVar, r1.d dVar) throws IOException {
        options.inJustDecodeBounds = true;
        g(inputStream, options, bVar, dVar);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static String l(BitmapFactory.Options options) {
        return h(options.inBitmap);
    }

    private static boolean o(int i7) {
        return i7 == 90 || i7 == 270;
    }

    private static boolean p(BitmapFactory.Options options) {
        int i7;
        int i8 = options.inTargetDensity;
        return i8 > 0 && (i7 = options.inDensity) > 0 && i8 != i7;
    }

    private static void q(int i7, int i8, String str, BitmapFactory.Options options, Bitmap bitmap, int i9, int i10, long j7) {
        Log.v("Downsampler", "Decoded " + h(bitmap) + " from [" + i7 + "x" + i8 + "] " + str + " with inBitmap " + l(options) + " for [" + i9 + "x" + i10 + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + k2.f.a(j7));
    }

    private static IOException r(IllegalArgumentException illegalArgumentException, int i7, int i8, String str, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + i7 + ", outHeight: " + i8 + ", outMimeType: " + str + ", inBitmap: " + l(options), illegalArgumentException);
    }

    private static void s(BitmapFactory.Options options) {
        t(options);
        Queue<BitmapFactory.Options> queue = f9782n;
        synchronized (queue) {
            queue.offer(options);
        }
    }

    private static void t(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.inPreferredColorSpace = null;
        options.outColorSpace = null;
        options.outConfig = null;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    private static int u(double d7) {
        return (int) (d7 + 0.5d);
    }

    @TargetApi(26)
    private static void v(BitmapFactory.Options options, r1.d dVar, int i7, int i8) {
        Bitmap.Config config = options.inPreferredConfig;
        if (config == Bitmap.Config.HARDWARE) {
            return;
        }
        Bitmap.Config config2 = options.outConfig;
        if (config2 != null) {
            config = config2;
        }
        options.inBitmap = dVar.e(i7, i8, config);
    }

    private boolean w(ImageHeaderParser.ImageType imageType) {
        return true;
    }

    public q1.v<Bitmap> d(InputStream inputStream, int i7, int i8, n1.i iVar) {
        return e(inputStream, i7, i8, iVar, f9780l);
    }

    public q1.v<Bitmap> e(InputStream inputStream, int i7, int i8, n1.i iVar, b bVar) {
        k2.j.a(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bArr = (byte[]) this.f9785c.e(65536, byte[].class);
        BitmapFactory.Options optionsI = i();
        optionsI.inTempStorage = bArr;
        n1.b bVar2 = (n1.b) iVar.c(f9774f);
        n1.j jVar = (n1.j) iVar.c(f9775g);
        l lVar = (l) iVar.c(l.f9771h);
        boolean zBooleanValue = ((Boolean) iVar.c(f9777i)).booleanValue();
        n1.h<Boolean> hVar = f9778j;
        try {
            return e.f(f(inputStream, optionsI, lVar, bVar2, jVar, iVar.c(hVar) != null && ((Boolean) iVar.c(hVar)).booleanValue(), i7, i8, zBooleanValue, bVar), this.f9783a);
        } finally {
            s(optionsI);
            this.f9785c.d(bArr);
        }
    }

    public boolean m(InputStream inputStream) {
        return true;
    }

    public boolean n(ByteBuffer byteBuffer) {
        return true;
    }
}
