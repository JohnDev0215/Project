package x1;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: TransformationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Paint f9816a = new Paint(6);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Paint f9817b = new Paint(7);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Paint f9818c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Set<String> f9819d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Lock f9820e;

    /* JADX INFO: compiled from: TransformationUtils.java */
    class a implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f9821a;

        a(int i7) {
            this.f9821a = i7;
        }

        @Override // x1.y.b
        public void a(Canvas canvas, Paint paint, RectF rectF) {
            int i7 = this.f9821a;
            canvas.drawRoundRect(rectF, i7, i7, paint);
        }
    }

    /* JADX INFO: compiled from: TransformationUtils.java */
    private interface b {
        void a(Canvas canvas, Paint paint, RectF rectF);
    }

    /* JADX INFO: compiled from: TransformationUtils.java */
    private static final class c implements Lock {
        c() {
        }

        @Override // java.util.concurrent.locks.Lock
        public void lock() {
        }

        @Override // java.util.concurrent.locks.Lock
        public void lockInterruptibly() {
        }

        @Override // java.util.concurrent.locks.Lock
        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock() {
            return true;
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock(long j7, TimeUnit timeUnit) {
            return true;
        }

        @Override // java.util.concurrent.locks.Lock
        public void unlock() {
        }
    }

    static {
        HashSet hashSet = new HashSet(Arrays.asList("XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098", "XT1031", "XT1028", "XT937C", "XT1032", "XT1008", "XT1033", "XT1035", "XT1034", "XT939G", "XT1039", "XT1040", "XT1042", "XT1045", "XT1063", "XT1064", "XT1068", "XT1069", "XT1072", "XT1077", "XT1078", "XT1079"));
        f9819d = hashSet;
        f9820e = hashSet.contains(Build.MODEL) ? new ReentrantLock() : new c();
        Paint paint = new Paint(7);
        f9818c = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    private static void a(Bitmap bitmap, Bitmap bitmap2, Matrix matrix) {
        Lock lock = f9820e;
        lock.lock();
        try {
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawBitmap(bitmap, matrix, f9816a);
            d(canvas);
            lock.unlock();
        } catch (Throwable th) {
            f9820e.unlock();
            throw th;
        }
    }

    public static Bitmap b(r1.d dVar, Bitmap bitmap, int i7, int i8) {
        float width;
        float height;
        if (bitmap.getWidth() == i7 && bitmap.getHeight() == i8) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        float width2 = 0.0f;
        if (bitmap.getWidth() * i8 > bitmap.getHeight() * i7) {
            width = i8 / bitmap.getHeight();
            width2 = (i7 - (bitmap.getWidth() * width)) * 0.5f;
            height = 0.0f;
        } else {
            width = i7 / bitmap.getWidth();
            height = (i8 - (bitmap.getHeight() * width)) * 0.5f;
        }
        matrix.setScale(width, width);
        matrix.postTranslate((int) (width2 + 0.5f), (int) (height + 0.5f));
        Bitmap bitmapC = dVar.c(i7, i8, j(bitmap));
        p(bitmap, bitmapC);
        a(bitmap, bitmapC, matrix);
        return bitmapC;
    }

    public static Bitmap c(r1.d dVar, Bitmap bitmap, int i7, int i8) {
        if (bitmap.getWidth() > i7 || bitmap.getHeight() > i8) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size too big for input, fit centering instead");
            }
            return e(dVar, bitmap, i7, i8);
        }
        if (Log.isLoggable("TransformationUtils", 2)) {
            Log.v("TransformationUtils", "requested target size larger or equal to input, returning input");
        }
        return bitmap;
    }

    private static void d(Canvas canvas) {
        canvas.setBitmap(null);
    }

    public static Bitmap e(r1.d dVar, Bitmap bitmap, int i7, int i8) {
        if (bitmap.getWidth() == i7 && bitmap.getHeight() == i8) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size matches input, returning input");
            }
            return bitmap;
        }
        float fMin = Math.min(i7 / bitmap.getWidth(), i8 / bitmap.getHeight());
        int iRound = Math.round(bitmap.getWidth() * fMin);
        int iRound2 = Math.round(bitmap.getHeight() * fMin);
        if (bitmap.getWidth() == iRound && bitmap.getHeight() == iRound2) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "adjusted target size matches input, returning input");
            }
            return bitmap;
        }
        Bitmap bitmapC = dVar.c((int) (bitmap.getWidth() * fMin), (int) (bitmap.getHeight() * fMin), j(bitmap));
        p(bitmap, bitmapC);
        if (Log.isLoggable("TransformationUtils", 2)) {
            Log.v("TransformationUtils", "request: " + i7 + "x" + i8);
            Log.v("TransformationUtils", "toFit:   " + bitmap.getWidth() + "x" + bitmap.getHeight());
            Log.v("TransformationUtils", "toReuse: " + bitmapC.getWidth() + "x" + bitmapC.getHeight());
            StringBuilder sb = new StringBuilder();
            sb.append("minPct:   ");
            sb.append(fMin);
            Log.v("TransformationUtils", sb.toString());
        }
        Matrix matrix = new Matrix();
        matrix.setScale(fMin, fMin);
        a(bitmap, bitmapC, matrix);
        return bitmapC;
    }

    private static Bitmap f(r1.d dVar, Bitmap bitmap) {
        Bitmap.Config configG = g(bitmap);
        if (configG.equals(bitmap.getConfig())) {
            return bitmap;
        }
        Bitmap bitmapC = dVar.c(bitmap.getWidth(), bitmap.getHeight(), configG);
        new Canvas(bitmapC).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapC;
    }

    private static Bitmap.Config g(Bitmap bitmap) {
        return Bitmap.Config.RGBA_F16.equals(bitmap.getConfig()) ? Bitmap.Config.RGBA_F16 : Bitmap.Config.ARGB_8888;
    }

    public static Lock h() {
        return f9820e;
    }

    public static int i(int i7) {
        switch (i7) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    private static Bitmap.Config j(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    static void k(int i7, Matrix matrix) {
        switch (i7) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                break;
        }
    }

    public static boolean l(int i7) {
        switch (i7) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    public static Bitmap m(r1.d dVar, Bitmap bitmap, int i7) {
        if (!l(i7)) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        k(i7, matrix);
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        matrix.mapRect(rectF);
        Bitmap bitmapC = dVar.c(Math.round(rectF.width()), Math.round(rectF.height()), j(bitmap));
        matrix.postTranslate(-rectF.left, -rectF.top);
        bitmapC.setHasAlpha(bitmap.hasAlpha());
        a(bitmap, bitmapC, matrix);
        return bitmapC;
    }

    public static Bitmap n(r1.d dVar, Bitmap bitmap, int i7) {
        k2.j.a(i7 > 0, "roundingRadius must be greater than 0.");
        return o(dVar, bitmap, new a(i7));
    }

    private static Bitmap o(r1.d dVar, Bitmap bitmap, b bVar) {
        Bitmap.Config configG = g(bitmap);
        Bitmap bitmapF = f(dVar, bitmap);
        Bitmap bitmapC = dVar.c(bitmapF.getWidth(), bitmapF.getHeight(), configG);
        bitmapC.setHasAlpha(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmapF, tileMode, tileMode);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        RectF rectF = new RectF(0.0f, 0.0f, bitmapC.getWidth(), bitmapC.getHeight());
        Lock lock = f9820e;
        lock.lock();
        try {
            Canvas canvas = new Canvas(bitmapC);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            bVar.a(canvas, paint, rectF);
            d(canvas);
            lock.unlock();
            if (!bitmapF.equals(bitmap)) {
                dVar.d(bitmapF);
            }
            return bitmapC;
        } catch (Throwable th) {
            f9820e.unlock();
            throw th;
        }
    }

    public static void p(Bitmap bitmap, Bitmap bitmap2) {
        bitmap2.setHasAlpha(bitmap.hasAlpha());
    }
}
