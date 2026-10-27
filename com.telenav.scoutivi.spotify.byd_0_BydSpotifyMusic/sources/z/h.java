package z;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import e0.f;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import y.a;

/* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @SuppressLint({"BanConcurrentHashMap"})
    private ConcurrentHashMap<Long, a.b> f10041a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    class a implements b<f.b> {
        a() {
        }

        @Override // z.h.b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(f.b bVar) {
            return bVar.e();
        }

        @Override // z.h.b
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(f.b bVar) {
            return bVar.f();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    private interface b<T> {
        int a(T t6);

        boolean b(T t6);
    }

    h() {
    }

    private static <T> T d(T[] tArr, int i7, b<T> bVar) {
        int i8 = (i7 & 1) == 0 ? 400 : 700;
        boolean z6 = (i7 & 2) != 0;
        T t6 = null;
        int i9 = Integer.MAX_VALUE;
        for (T t7 : tArr) {
            int iAbs = (Math.abs(bVar.a(t7) - i8) * 2) + (bVar.b(t7) == z6 ? 0 : 1);
            if (t6 == null || i9 > iAbs) {
                t6 = t7;
                i9 = iAbs;
            }
        }
        return t6;
    }

    public Typeface a(Context context, a.b bVar, Resources resources, int i7) {
        throw null;
    }

    public Typeface b(Context context, CancellationSignal cancellationSignal, f.b[] bVarArr, int i7) {
        throw null;
    }

    public Typeface c(Context context, Resources resources, int i7, String str, int i8) {
        File fileD = i.d(context);
        if (fileD == null) {
            return null;
        }
        try {
            if (i.b(fileD, resources, i7)) {
                return Typeface.createFromFile(fileD.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileD.delete();
        }
    }

    protected f.b e(f.b[] bVarArr, int i7) {
        return (f.b) d(bVarArr, i7, new a());
    }
}
