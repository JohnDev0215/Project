package d0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import c0.c;
import i0.b;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConcurrentHashMap<Long, c.b> f2674a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public class a implements c<b.f> {
        public a() {
        }

        @Override // d0.j.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(b.f fVar) {
            return fVar.d();
        }

        @Override // d0.j.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(b.f fVar) {
            return fVar.e();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public class b implements c<c.C0006c> {
        public b() {
        }

        @Override // d0.j.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(c.C0006c c0006c) {
            return c0006c.e();
        }

        @Override // d0.j.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(c.C0006c c0006c) {
            return c0006c.f();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public interface c<T> {
        int a(T t3);

        boolean b(T t3);
    }

    public static <T> T g(T[] tArr, int i3, c<T> cVar) {
        int i4 = (i3 & 1) == 0 ? 400 : 700;
        boolean z3 = (i3 & 2) != 0;
        T t3 = null;
        int i5 = Integer.MAX_VALUE;
        for (T t4 : tArr) {
            int iAbs = (Math.abs(cVar.a(t4) - i4) * 2) + (cVar.b(t4) == z3 ? 0 : 1);
            if (t3 == null || i5 > iAbs) {
                t3 = t4;
                i5 = iAbs;
            }
        }
        return t3;
    }

    public static long j(Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e3) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e3);
            return 0L;
        } catch (NoSuchFieldException e4) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e4);
            return 0L;
        }
    }

    public final void a(Typeface typeface, c.b bVar) {
        long j3 = j(typeface);
        if (j3 != 0) {
            this.f2674a.put(Long.valueOf(j3), bVar);
        }
    }

    public Typeface b(Context context, c.b bVar, Resources resources, int i3) {
        c.C0006c c0006cF = f(bVar, i3);
        if (c0006cF == null) {
            return null;
        }
        Typeface typefaceD = d.d(context, resources, c0006cF.b(), c0006cF.a(), i3);
        a(typefaceD, bVar);
        return typefaceD;
    }

    public Typeface c(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i3) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (fVarArr.length < 1) {
            return null;
        }
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(h(fVarArr, i3).c());
        } catch (IOException unused) {
            inputStreamOpenInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Typeface typefaceD = d(context, inputStreamOpenInputStream);
            k.a(inputStreamOpenInputStream);
            return typefaceD;
        } catch (IOException unused2) {
            k.a(inputStreamOpenInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = inputStreamOpenInputStream;
            k.a(inputStream);
            throw th;
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

    public Typeface e(Context context, Resources resources, int i3, String str, int i4) {
        File fileE = k.e(context);
        if (fileE == null) {
            return null;
        }
        try {
            if (k.c(fileE, resources, i3)) {
                return Typeface.createFromFile(fileE.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileE.delete();
        }
    }

    public final c.C0006c f(c.b bVar, int i3) {
        return (c.C0006c) g(bVar.a(), i3, new b());
    }

    public b.f h(b.f[] fVarArr, int i3) {
        return (b.f) g(fVarArr, i3, new a());
    }

    public c.b i(Typeface typeface) {
        long j3 = j(typeface);
        if (j3 == 0) {
            return null;
        }
        return this.f2674a.get(Long.valueOf(j3));
    }
}
