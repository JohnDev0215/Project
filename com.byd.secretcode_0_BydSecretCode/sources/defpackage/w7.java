package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import defpackage.i7;
import defpackage.k8;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class w7 {
    public ConcurrentHashMap<Long, i7.b> a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public class a implements c<k8.f> {
        public a() {
        }

        @Override // w7.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(k8.f fVar) {
            return fVar.d();
        }

        @Override // w7.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(k8.f fVar) {
            return fVar.e();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public class b implements c<i7.c> {
        public b() {
        }

        @Override // w7.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public int a(i7.c cVar) {
            return cVar.e();
        }

        @Override // w7.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean b(i7.c cVar) {
            return cVar.f();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public interface c<T> {
        int a(T t);

        boolean b(T t);
    }

    public static <T> T g(T[] tArr, int i, c<T> cVar) {
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

    public static long j(Typeface typeface) {
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

    public final void a(Typeface typeface, i7.b bVar) {
        long j = j(typeface);
        if (j != 0) {
            this.a.put(Long.valueOf(j), bVar);
        }
    }

    public Typeface b(Context context, i7.b bVar, Resources resources, int i) {
        i7.c cVarF = f(bVar, i);
        if (cVarF == null) {
            return null;
        }
        Typeface typefaceD = q7.d(context, resources, cVarF.b(), cVarF.a(), i);
        a(typefaceD, bVar);
        return typefaceD;
    }

    public Typeface c(Context context, CancellationSignal cancellationSignal, k8.f[] fVarArr, int i) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (fVarArr.length < 1) {
            return null;
        }
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(h(fVarArr, i).c());
            try {
                Typeface typefaceD = d(context, inputStreamOpenInputStream);
                x7.a(inputStreamOpenInputStream);
                return typefaceD;
            } catch (IOException unused) {
                x7.a(inputStreamOpenInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamOpenInputStream;
                x7.a(inputStream);
                throw th;
            }
        } catch (IOException unused2) {
            inputStreamOpenInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public Typeface d(Context context, InputStream inputStream) {
        File fileE = x7.e(context);
        if (fileE == null) {
            return null;
        }
        try {
            if (x7.d(fileE, inputStream)) {
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
        File fileE = x7.e(context);
        if (fileE == null) {
            return null;
        }
        try {
            if (x7.c(fileE, resources, i)) {
                return Typeface.createFromFile(fileE.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileE.delete();
        }
    }

    public final i7.c f(i7.b bVar, int i) {
        return (i7.c) g(bVar.a(), i, new b());
    }

    public k8.f h(k8.f[] fVarArr, int i) {
        return (k8.f) g(fVarArr, i, new a());
    }

    public i7.b i(Typeface typeface) {
        long j = j(typeface);
        if (j == 0) {
            return null;
        }
        return this.a.get(Long.valueOf(j));
    }
}
