package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.PreferredColorSpace;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import spi.e;
import spi.he;
import spi.jk;
import spi.lk;
import spi.m0;
import spi.tu;
import spi.w1;
import spi.wm;
import spi.y1;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final jk<DecodeFormat> f = jk.c("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    public static final jk<PreferredColorSpace> g = jk.b("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace");
    public static final jk<Boolean> h;
    public static final jk<Boolean> i;
    public static final C0018a j;
    public static final Queue<BitmapFactory.Options> k;
    public final w1 a;
    public final DisplayMetrics b;
    public final m0 c;
    public final List<ImageHeaderParser> d;
    public final he e = he.a();

    /* JADX INFO: renamed from: com.bumptech.glide.load.resource.bitmap.a$a, reason: collision with other inner class name */
    public class C0018a implements b {
        @Override // com.bumptech.glide.load.resource.bitmap.a.b
        public final void a() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.a.b
        public final void b(w1 w1Var, Bitmap bitmap) {
        }
    }

    public interface b {
        void a();

        void b(w1 w1Var, Bitmap bitmap) throws IOException;
    }

    static {
        jk<DownsampleStrategy> jkVar = DownsampleStrategy.f;
        Boolean bool = Boolean.FALSE;
        h = jk.c("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", bool);
        i = jk.c("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", bool);
        Collections.unmodifiableSet(new HashSet(Arrays.asList("image/vnd.wap.wbmp", "image/x-ico")));
        j = new C0018a();
        Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
        char[] cArr = tu.a;
        k = new ArrayDeque(0);
    }

    public a(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, w1 w1Var, m0 m0Var) {
        this.d = list;
        Objects.requireNonNull(displayMetrics, "Argument must not be null");
        this.b = displayMetrics;
        Objects.requireNonNull(w1Var, "Argument must not be null");
        this.a = w1Var;
        Objects.requireNonNull(m0Var, "Argument must not be null");
        this.c = m0Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
    
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap c(com.bumptech.glide.load.resource.bitmap.b r5, android.graphics.BitmapFactory.Options r6, com.bumptech.glide.load.resource.bitmap.a.b r7, spi.w1 r8) throws java.io.IOException {
        /*
            java.lang.String r0 = "Downsampler"
            boolean r1 = r6.inJustDecodeBounds
            if (r1 != 0) goto Lc
            r7.a()
            r5.c()
        Lc:
            int r1 = r6.outWidth
            int r2 = r6.outHeight
            java.lang.String r3 = r6.outMimeType
            java.util.concurrent.locks.Lock r4 = spi.st.c
            r4.lock()
            android.graphics.Bitmap r5 = r5.b(r6)     // Catch: java.lang.Throwable -> L1f java.lang.IllegalArgumentException -> L21
            r4.unlock()
            return r5
        L1f:
            r5 = move-exception
            goto L48
        L21:
            r4 = move-exception
            java.io.IOException r1 = h(r4, r1, r2, r3, r6)     // Catch: java.lang.Throwable -> L1f
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch: java.lang.Throwable -> L1f
            if (r2 == 0) goto L32
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r0, r2, r1)     // Catch: java.lang.Throwable -> L1f
        L32:
            android.graphics.Bitmap r0 = r6.inBitmap     // Catch: java.lang.Throwable -> L1f
            if (r0 == 0) goto L47
            r8.c(r0)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L46
            r0 = 0
            r6.inBitmap = r0     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L46
            android.graphics.Bitmap r5 = c(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L46
            java.util.concurrent.locks.Lock r6 = spi.st.c
            r6.unlock()
            return r5
        L46:
            throw r1     // Catch: java.lang.Throwable -> L1f
        L47:
            throw r1     // Catch: java.lang.Throwable -> L1f
        L48:
            java.util.concurrent.locks.Lock r6 = spi.st.c
            r6.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.a.c(com.bumptech.glide.load.resource.bitmap.b, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.a$b, spi.w1):android.graphics.Bitmap");
    }

    @Nullable
    @TargetApi(19)
    public static String d(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        StringBuilder sbK = e.k(" (");
        sbK.append(bitmap.getAllocationByteCount());
        sbK.append(")");
        String string = sbK.toString();
        StringBuilder sbK2 = e.k("[");
        sbK2.append(bitmap.getWidth());
        sbK2.append("x");
        sbK2.append(bitmap.getHeight());
        sbK2.append("] ");
        sbK2.append(bitmap.getConfig());
        sbK2.append(string);
        return sbK2.toString();
    }

    public static int e(double d) {
        if (d > 1.0d) {
            d = 1.0d / d;
        }
        return (int) Math.round(d * 2.147483647E9d);
    }

    public static int[] f(com.bumptech.glide.load.resource.bitmap.b bVar, BitmapFactory.Options options, b bVar2, w1 w1Var) throws IOException {
        options.inJustDecodeBounds = true;
        c(bVar, options, bVar2, w1Var);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    public static boolean g(int i2) {
        return i2 == 90 || i2 == 270;
    }

    public static IOException h(IllegalArgumentException illegalArgumentException, int i2, int i3, String str, BitmapFactory.Options options) {
        StringBuilder sbL = e.l("Exception decoding bitmap, outWidth: ", i2, ", outHeight: ", i3, ", outMimeType: ");
        sbL.append(str);
        sbL.append(", inBitmap: ");
        sbL.append(d(options.inBitmap));
        return new IOException(sbL.toString(), illegalArgumentException);
    }

    public static void i(BitmapFactory.Options options) {
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

    /* JADX WARN: Type inference failed for: r14v0, types: [java.util.ArrayDeque, java.util.Queue<android.graphics.BitmapFactory$Options>] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.ArrayDeque, java.util.Queue<android.graphics.BitmapFactory$Options>] */
    public final wm<Bitmap> a(com.bumptech.glide.load.resource.bitmap.b bVar, int i2, int i3, lk lkVar, b bVar2) throws IOException {
        ?? r14;
        BitmapFactory.Options options;
        BitmapFactory.Options options2;
        byte[] bArr = (byte[]) this.c.d(65536, byte[].class);
        synchronized (a.class) {
            r14 = k;
            synchronized (r14) {
                options = (BitmapFactory.Options) r14.poll();
            }
            if (options == null) {
                options = new BitmapFactory.Options();
                i(options);
            }
            options2 = options;
        }
        options2.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) lkVar.c(f);
        PreferredColorSpace preferredColorSpace = (PreferredColorSpace) lkVar.c(g);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) lkVar.c(DownsampleStrategy.f);
        boolean zBooleanValue = ((Boolean) lkVar.c(h)).booleanValue();
        jk<Boolean> jkVar = i;
        try {
            y1 y1VarB = y1.b(b(bVar, options2, downsampleStrategy, decodeFormat, preferredColorSpace, lkVar.c(jkVar) != null && ((Boolean) lkVar.c(jkVar)).booleanValue(), i2, i3, zBooleanValue, bVar2), this.a);
            i(options2);
            synchronized (r14) {
                r14.offer(options2);
            }
            this.c.c(bArr);
            return y1VarB;
        } catch (Throwable th) {
            i(options2);
            ?? r2 = k;
            synchronized (r2) {
                r2.offer(options2);
                this.c.c(bArr);
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x02cc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x033e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0283  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap b(com.bumptech.glide.load.resource.bitmap.b r33, android.graphics.BitmapFactory.Options r34, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r35, com.bumptech.glide.load.DecodeFormat r36, com.bumptech.glide.load.PreferredColorSpace r37, boolean r38, int r39, int r40, boolean r41, com.bumptech.glide.load.resource.bitmap.a.b r42) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.a.b(com.bumptech.glide.load.resource.bitmap.b, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.DecodeFormat, com.bumptech.glide.load.PreferredColorSpace, boolean, int, int, boolean, com.bumptech.glide.load.resource.bitmap.a$b):android.graphics.Bitmap");
    }
}
