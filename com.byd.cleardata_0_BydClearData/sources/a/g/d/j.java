package a.g.d;

import a.g.h.b;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.core.content.c.c;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Long, c.b> f287a = new ConcurrentHashMap<>();

    class a implements c<b.f> {
        a() {
        }

        @Override // a.g.d.j.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(b.f fVar) {
            return fVar.d();
        }

        @Override // a.g.d.j.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(b.f fVar) {
            return fVar.e();
        }
    }

    class b implements c<c.C0038c> {
        b() {
        }

        @Override // a.g.d.j.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(c.C0038c c0038c) {
            return c0038c.e();
        }

        @Override // a.g.d.j.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(c.C0038c c0038c) {
            return c0038c.f();
        }
    }

    private interface c<T> {
        int a(T t);

        boolean b(T t);
    }

    j() {
    }

    private void a(Typeface typeface, c.b bVar) {
        long j = j(typeface);
        if (j != 0) {
            this.f287a.put(Long.valueOf(j), bVar);
        }
    }

    private c.C0038c f(c.b bVar, int i) {
        return (c.C0038c) g(bVar.a(), i, new b());
    }

    private static <T> T g(T[] tArr, int i, c<T> cVar) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int iAbs = (Math.abs(cVar.a(t2) - i2) * 2) + (cVar.b(t2) == z ? 0 : 1);
            if (t == null || i3 > iAbs) {
                t = t2;
                i3 = iAbs;
            }
        }
        return t;
    }

    private static long j(Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e);
            return 0L;
        } catch (NoSuchFieldException e2) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
            return 0L;
        }
    }

    public Typeface b(Context context, c.b bVar, Resources resources, int i) {
        c.C0038c c0038cF = f(bVar, i);
        if (c0038cF == null) {
            return null;
        }
        Typeface typefaceD = d.d(context, resources, c0038cF.b(), c0038cF.a(), i);
        a(typefaceD, bVar);
        return typefaceD;
    }

    public Typeface c(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (fVarArr.length < 1) {
            return null;
        }
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(h(fVarArr, i).c());
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

    protected Typeface d(Context context, InputStream inputStream) {
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

    public Typeface e(Context context, Resources resources, int i, String str, int i2) {
        File fileE = k.e(context);
        if (fileE == null) {
            return null;
        }
        try {
            if (k.c(fileE, resources, i)) {
                return Typeface.createFromFile(fileE.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileE.delete();
        }
    }

    protected b.f h(b.f[] fVarArr, int i) {
        return (b.f) g(fVarArr, i, new a());
    }

    c.b i(Typeface typeface) {
        long j = j(typeface);
        if (j == 0) {
            return null;
        }
        return this.f287a.get(Long.valueOf(j));
    }
}
