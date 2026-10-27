package g0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import f0.c;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import l0.b;

/* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConcurrentHashMap<Long, c.b> f5008a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public class a implements c<b.f> {
        public a() {
        }

        @Override // g0.j.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(b.f fVar) {
            return fVar.d();
        }

        @Override // g0.j.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(b.f fVar) {
            return fVar.e();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public class b implements c<c.C0036c> {
        public b() {
        }

        @Override // g0.j.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(c.C0036c c0036c) {
            return c0036c.e();
        }

        @Override // g0.j.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(c.C0036c c0036c) {
            return c0036c.f();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public interface c<T> {
        int a(T t7);

        boolean b(T t7);
    }

    public static <T> T g(T[] tArr, int i8, c<T> cVar) {
        int i9 = (i8 & 1) == 0 ? 400 : 700;
        boolean z7 = (i8 & 2) != 0;
        T t7 = null;
        int i10 = Integer.MAX_VALUE;
        for (T t8 : tArr) {
            int iAbs = (Math.abs(cVar.a(t8) - i9) * 2) + (cVar.b(t8) == z7 ? 0 : 1);
            if (t7 == null || i10 > iAbs) {
                t7 = t8;
                i10 = iAbs;
            }
        }
        return t7;
    }

    public static long j(Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e8) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e8);
            return 0L;
        } catch (NoSuchFieldException e9) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e9);
            return 0L;
        }
    }

    public final void a(Typeface typeface, c.b bVar) {
        long j8 = j(typeface);
        if (j8 != 0) {
            this.f5008a.put(Long.valueOf(j8), bVar);
        }
    }

    public Typeface b(Context context, c.b bVar, Resources resources, int i8) {
        c.C0036c c0036cF = f(bVar, i8);
        if (c0036cF == null) {
            return null;
        }
        Typeface typefaceD = d.d(context, resources, c0036cF.b(), c0036cF.a(), i8);
        a(typefaceD, bVar);
        return typefaceD;
    }

    public Typeface c(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i8) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (fVarArr.length < 1) {
            return null;
        }
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(h(fVarArr, i8).c());
            try {
                Typeface typefaceD = d(context, inputStreamOpenInputStream);
                k.a(inputStreamOpenInputStream);
                return typefaceD;
            } catch (IOException unused) {
                k.a(inputStreamOpenInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamOpenInputStream;
                k.a(inputStream);
                throw th;
            }
        } catch (IOException unused2) {
            inputStreamOpenInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public Typeface d(Context context, InputStream inputStream) {
        File fileE = k.e(context);
        if (fileE == null) {
            return null;
        }
        try {
            if (k.d(fileE, inputStream)) {
                return Typeface.createFromFile(fileE.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileE.delete();
        }
    }

    public Typeface e(Context context, Resources resources, int i8, String str, int i9) {
        File fileE = k.e(context);
        if (fileE == null) {
            return null;
        }
        try {
            if (k.c(fileE, resources, i8)) {
                return Typeface.createFromFile(fileE.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileE.delete();
        }
    }

    public final c.C0036c f(c.b bVar, int i8) {
        return (c.C0036c) g(bVar.a(), i8, new b());
    }

    public b.f h(b.f[] fVarArr, int i8) {
        return (b.f) g(fVarArr, i8, new a());
    }

    public c.b i(Typeface typeface) {
        long j8 = j(typeface);
        if (j8 == 0) {
            return null;
        }
        return this.f5008a.get(Long.valueOf(j8));
    }
}
