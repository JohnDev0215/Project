package n4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import e4.EnumC1225a;
import h4.InterfaceC1359a;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e4.g f19953f = e4.g.a(EnumC1225a.f14428n, "com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e4.g f19954g = new e4.g("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace", null, e4.g.f14431e);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e4.g f19955h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e4.g f19956i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final m6.b f19957j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final ArrayDeque f19958k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC1359a f19959a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DisplayMetrics f19960b;
    public final F.l c;
    public final ArrayList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final v f19961e = v.a();

    static {
        n nVar = n.f19947b;
        Boolean bool = Boolean.FALSE;
        f19955h = e4.g.a(bool, "com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize");
        f19956i = e4.g.a(bool, "com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode");
        Collections.unmodifiableSet(new HashSet(Arrays.asList("image/vnd.wap.wbmp", "image/x-ico")));
        f19957j = new m6.b();
        Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser$ImageType.JPEG, ImageHeaderParser$ImageType.PNG_A, ImageHeaderParser$ImageType.PNG));
        char[] cArr = A4.q.f432a;
        f19958k = new ArrayDeque(0);
    }

    public p(ArrayList arrayList, DisplayMetrics displayMetrics, InterfaceC1359a interfaceC1359a, F.l lVar) {
        this.d = arrayList;
        A4.h.c(displayMetrics, "Argument must not be null");
        this.f19960b = displayMetrics;
        A4.h.c(interfaceC1359a, "Argument must not be null");
        this.f19959a = interfaceC1359a;
        A4.h.c(lVar, "Argument must not be null");
        this.c = lVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:?, code lost:
    
        throw r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap c(h3.y r9, android.graphics.BitmapFactory.Options r10, n4.o r11, h4.InterfaceC1359a r12) {
        /*
            java.lang.String r0 = "Downsampler"
            boolean r1 = r10.inJustDecodeBounds
            if (r1 != 0) goto L22
            r11.p0()
            int r1 = r9.l
            switch(r1) {
                case 11: goto L22;
                case 12: goto Lf;
                default: goto Le;
            }
        Le:
            goto L22
        Lf:
            java.lang.Object r1 = r9.f15499m
            com.bumptech.glide.load.data.i r1 = (com.bumptech.glide.load.data.i) r1
            java.lang.Object r1 = r1.f12725m
            n4.w r1 = (n4.w) r1
            monitor-enter(r1)
            byte[] r2 = r1.l     // Catch: java.lang.Throwable -> L1f
            int r2 = r2.length     // Catch: java.lang.Throwable -> L1f
            r1.f19969n = r2     // Catch: java.lang.Throwable -> L1f
            monitor-exit(r1)
            goto L22
        L1f:
            r9 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L1f
            throw r9
        L22:
            int r1 = r10.outWidth
            int r2 = r10.outHeight
            java.lang.String r3 = r10.outMimeType
            java.util.concurrent.locks.Lock r4 = n4.y.f19973b
            r4.lock()
            android.graphics.Bitmap r9 = r9.i(r10)     // Catch: java.lang.Throwable -> L35 java.lang.IllegalArgumentException -> L37
            r4.unlock()
            return r9
        L35:
            r9 = move-exception
            goto L7e
        L37:
            r4 = move-exception
            java.io.IOException r5 = new java.io.IOException     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = "Exception decoding bitmap, outWidth: "
            java.lang.String r7 = ", outHeight: "
            java.lang.String r8 = ", outMimeType: "
            java.lang.StringBuilder r1 = M5.b.l(r6, r7, r1, r2, r8)     // Catch: java.lang.Throwable -> L35
            r1.append(r3)     // Catch: java.lang.Throwable -> L35
            java.lang.String r2 = ", inBitmap: "
            r1.append(r2)     // Catch: java.lang.Throwable -> L35
            android.graphics.Bitmap r2 = r10.inBitmap     // Catch: java.lang.Throwable -> L35
            java.lang.String r2 = d(r2)     // Catch: java.lang.Throwable -> L35
            r1.append(r2)     // Catch: java.lang.Throwable -> L35
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L35
            r5.<init>(r1, r4)     // Catch: java.lang.Throwable -> L35
            r1 = 3
            boolean r1 = android.util.Log.isLoggable(r0, r1)     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L68
            java.lang.String r1 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r0, r1, r5)     // Catch: java.lang.Throwable -> L35
        L68:
            android.graphics.Bitmap r0 = r10.inBitmap     // Catch: java.lang.Throwable -> L35
            if (r0 == 0) goto L7d
            r12.k(r0)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L7c
            r0 = 0
            r10.inBitmap = r0     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L7c
            android.graphics.Bitmap r9 = c(r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L7c
            java.util.concurrent.locks.Lock r10 = n4.y.f19973b
            r10.unlock()
            return r9
        L7c:
            throw r5     // Catch: java.lang.Throwable -> L35
        L7d:
            throw r5     // Catch: java.lang.Throwable -> L35
        L7e:
            java.util.concurrent.locks.Lock r10 = n4.y.f19973b
            r10.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: n4.p.c(h3.y, android.graphics.BitmapFactory$Options, n4.o, h4.a):android.graphics.Bitmap");
    }

    public static String d(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + (" (" + bitmap.getAllocationByteCount() + ")");
    }

    public static void e(BitmapFactory.Options options) {
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

    public final C1958d a(h3.y yVar, int i7, int i10, e4.h hVar, o oVar) {
        ArrayDeque arrayDeque;
        BitmapFactory.Options options;
        BitmapFactory.Options options2;
        byte[] bArr = (byte[]) this.c.d(65536, byte[].class);
        synchronized (p.class) {
            arrayDeque = f19958k;
            synchronized (arrayDeque) {
                options = (BitmapFactory.Options) arrayDeque.poll();
            }
            if (options == null) {
                options = new BitmapFactory.Options();
                e(options);
            }
            options2 = options;
        }
        options2.inTempStorage = bArr;
        EnumC1225a enumC1225a = (EnumC1225a) hVar.c(f19953f);
        e4.i iVar = (e4.i) hVar.c(f19954g);
        n nVar = (n) hVar.c(n.f19950g);
        boolean zBooleanValue = ((Boolean) hVar.c(f19955h)).booleanValue();
        e4.g gVar = f19956i;
        try {
            C1958d c1958dC = C1958d.c(b(yVar, options2, nVar, enumC1225a, iVar, hVar.c(gVar) != null && ((Boolean) hVar.c(gVar)).booleanValue(), i7, i10, zBooleanValue, oVar), this.f19959a);
            e(options2);
            synchronized (arrayDeque) {
                arrayDeque.offer(options2);
            }
            this.c.i(bArr);
            return c1958dC;
        } catch (Throwable th) {
            e(options2);
            ArrayDeque arrayDeque2 = f19958k;
            synchronized (arrayDeque2) {
                arrayDeque2.offer(options2);
                this.c.i(bArr);
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03ed A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0476 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x04bd  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x05d6 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap b(h3.y r40, android.graphics.BitmapFactory.Options r41, n4.n r42, e4.EnumC1225a r43, e4.i r44, boolean r45, int r46, int r47, boolean r48, n4.o r49) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1574
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n4.p.b(h3.y, android.graphics.BitmapFactory$Options, n4.n, e4.a, e4.i, boolean, int, int, boolean, n4.o):android.graphics.Bitmap");
    }
}
