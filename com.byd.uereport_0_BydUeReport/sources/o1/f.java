package o1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import y1.t;

/* JADX INFO: compiled from: LottieCompositionFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, n<o1.e>> f6756a = new HashMap();

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    public class a implements i<o1.e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6757a;

        public a(String str) {
            this.f6757a = str;
        }

        @Override // o1.i
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(o1.e eVar) {
            f.f6756a.remove(this.f6757a);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    public class b implements i<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6758a;

        public b(String str) {
            this.f6758a = str;
        }

        @Override // o1.i
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            f.f6756a.remove(this.f6758a);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    public class c implements Callable<m<o1.e>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6759a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6760b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6761c;

        public c(Context context, String str, String str2) {
            this.f6759a = context;
            this.f6760b = str;
            this.f6761c = str2;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public m<o1.e> call() {
            return x1.b.e(this.f6759a, this.f6760b, this.f6761c);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    public class d implements Callable<m<o1.e>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6762a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6763b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6764c;

        public d(Context context, String str, String str2) {
            this.f6762a = context;
            this.f6763b = str;
            this.f6764c = str2;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public m<o1.e> call() {
            return f.f(this.f6762a, this.f6763b, this.f6764c);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    public class e implements Callable<m<o1.e>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WeakReference f6765a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f6766b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f6767c;

        public e(WeakReference weakReference, Context context, int i8) {
            this.f6765a = weakReference;
            this.f6766b = context;
            this.f6767c = i8;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public m<o1.e> call() {
            Context context = (Context) this.f6765a.get();
            if (context == null) {
                context = this.f6766b;
            }
            return f.n(context, this.f6767c);
        }
    }

    /* JADX INFO: renamed from: o1.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    public class CallableC0099f implements Callable<m<o1.e>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InputStream f6768a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6769b;

        public CallableC0099f(InputStream inputStream, String str) {
            this.f6768a = inputStream;
            this.f6769b = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public m<o1.e> call() {
            return f.h(this.f6768a, this.f6769b);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    public class g implements Callable<m<o1.e>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o1.e f6770a;

        public g(o1.e eVar) {
            this.f6770a = eVar;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public m<o1.e> call() {
            return new m<>(this.f6770a);
        }
    }

    public static n<o1.e> b(String str, Callable<m<o1.e>> callable) {
        o1.e eVarA = str == null ? null : t1.f.b().a(str);
        if (eVarA != null) {
            return new n<>(new g(eVarA));
        }
        if (str != null) {
            Map<String, n<o1.e>> map = f6756a;
            if (map.containsKey(str)) {
                return map.get(str);
            }
        }
        n<o1.e> nVar = new n<>(callable);
        if (str != null) {
            nVar.f(new a(str));
            nVar.e(new b(str));
            f6756a.put(str, nVar);
        }
        return nVar;
    }

    public static h c(o1.e eVar, String str) {
        for (h hVar : eVar.i().values()) {
            if (hVar.b().equals(str)) {
                return hVar;
            }
        }
        return null;
    }

    public static n<o1.e> d(Context context, String str) {
        return e(context, str, "asset_" + str);
    }

    public static n<o1.e> e(Context context, String str, String str2) {
        return b(str2, new d(context.getApplicationContext(), str, str2));
    }

    public static m<o1.e> f(Context context, String str, String str2) {
        try {
            return str.endsWith(".zip") ? r(new ZipInputStream(context.getAssets().open(str)), str2) : h(context.getAssets().open(str), str2);
        } catch (IOException e8) {
            return new m<>((Throwable) e8);
        }
    }

    public static n<o1.e> g(InputStream inputStream, String str) {
        return b(str, new CallableC0099f(inputStream, str));
    }

    public static m<o1.e> h(InputStream inputStream, String str) {
        return i(inputStream, str, true);
    }

    public static m<o1.e> i(InputStream inputStream, String str, boolean z7) {
        try {
            return j(JsonReader.w(i7.e.a(i7.e.c(inputStream))), str);
        } finally {
            if (z7) {
                a2.h.c(inputStream);
            }
        }
    }

    public static m<o1.e> j(JsonReader jsonReader, String str) {
        return k(jsonReader, str, true);
    }

    public static m<o1.e> k(JsonReader jsonReader, String str, boolean z7) {
        try {
            try {
                o1.e eVarA = t.a(jsonReader);
                if (str != null) {
                    t1.f.b().c(str, eVarA);
                }
                m<o1.e> mVar = new m<>(eVarA);
                if (z7) {
                    a2.h.c(jsonReader);
                }
                return mVar;
            } catch (Exception e8) {
                m<o1.e> mVar2 = new m<>(e8);
                if (z7) {
                    a2.h.c(jsonReader);
                }
                return mVar2;
            }
        } catch (Throwable th) {
            if (z7) {
                a2.h.c(jsonReader);
            }
            throw th;
        }
    }

    public static n<o1.e> l(Context context, int i8) {
        return m(context, i8, u(context, i8));
    }

    public static n<o1.e> m(Context context, int i8, String str) {
        return b(str, new e(new WeakReference(context), context.getApplicationContext(), i8));
    }

    public static m<o1.e> n(Context context, int i8) {
        return o(context, i8, u(context, i8));
    }

    public static m<o1.e> o(Context context, int i8, String str) {
        try {
            return h(context.getResources().openRawResource(i8), str);
        } catch (Resources.NotFoundException e8) {
            return new m<>((Throwable) e8);
        }
    }

    public static n<o1.e> p(Context context, String str) {
        return q(context, str, "url_" + str);
    }

    public static n<o1.e> q(Context context, String str, String str2) {
        return b(str2, new c(context, str, str2));
    }

    public static m<o1.e> r(ZipInputStream zipInputStream, String str) {
        try {
            return s(zipInputStream, str);
        } finally {
            a2.h.c(zipInputStream);
        }
    }

    public static m<o1.e> s(ZipInputStream zipInputStream, String str) {
        HashMap map = new HashMap();
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            o1.e eVarB = null;
            while (nextEntry != null) {
                String name = nextEntry.getName();
                if (name.contains("__MACOSX")) {
                    zipInputStream.closeEntry();
                } else if (nextEntry.getName().contains(".json")) {
                    eVarB = k(JsonReader.w(i7.e.a(i7.e.c(zipInputStream))), null, false).b();
                } else if (name.contains(".png") || name.contains(".webp")) {
                    map.put(name.split("/")[r1.length - 1], BitmapFactory.decodeStream(zipInputStream));
                } else {
                    zipInputStream.closeEntry();
                }
                nextEntry = zipInputStream.getNextEntry();
            }
            if (eVarB == null) {
                return new m<>((Throwable) new IllegalArgumentException("Unable to parse composition"));
            }
            for (Map.Entry entry : map.entrySet()) {
                h hVarC = c(eVarB, (String) entry.getKey());
                if (hVarC != null) {
                    hVarC.f(a2.h.l((Bitmap) entry.getValue(), hVarC.e(), hVarC.c()));
                }
            }
            for (Map.Entry<String, h> entry2 : eVarB.i().entrySet()) {
                if (entry2.getValue().a() == null) {
                    return new m<>((Throwable) new IllegalStateException("There is no image for " + entry2.getValue().b()));
                }
            }
            if (str != null) {
                t1.f.b().c(str, eVarB);
            }
            return new m<>(eVarB);
        } catch (IOException e8) {
            return new m<>((Throwable) e8);
        }
    }

    public static boolean t(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static String u(Context context, int i8) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes");
        sb.append(t(context) ? "_night_" : "_day_");
        sb.append(i8);
        return sb.toString();
    }
}
