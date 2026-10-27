package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.t.t;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: compiled from: LottieCompositionFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<String, m<com.airbnb.lottie.d>> f2444a = new HashMap();

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class a implements h<com.airbnb.lottie.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2445a;

        a(String str) {
            this.f2445a = str;
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(com.airbnb.lottie.d dVar) {
            e.f2444a.remove(this.f2445a);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class b implements h<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2446a;

        b(String str) {
            this.f2446a = str;
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            e.f2444a.remove(this.f2446a);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class c implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f2447b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f2448c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f2449d;

        c(Context context, String str, String str2) {
            this.f2447b = context;
            this.f2448c = str;
            this.f2449d = str2;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return com.airbnb.lottie.network.b.e(this.f2447b, this.f2448c, this.f2449d);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class d implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f2450b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f2451c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f2452d;

        d(Context context, String str, String str2) {
            this.f2450b = context;
            this.f2451c = str;
            this.f2452d = str2;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return e.f(this.f2450b, this.f2451c, this.f2452d);
        }
    }

    /* JADX INFO: renamed from: com.airbnb.lottie.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class CallableC0054e implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ WeakReference f2453b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Context f2454c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f2455d;

        CallableC0054e(WeakReference weakReference, Context context, int i) {
            this.f2453b = weakReference;
            this.f2454c = context;
            this.f2455d = i;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            Context context = (Context) this.f2453b.get();
            if (context == null) {
                context = this.f2454c;
            }
            return e.n(context, this.f2455d);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class f implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ InputStream f2456b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f2457c;

        f(InputStream inputStream, String str) {
            this.f2456b = inputStream;
            this.f2457c = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return e.h(this.f2456b, this.f2457c);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class g implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.airbnb.lottie.d f2458b;

        g(com.airbnb.lottie.d dVar) {
            this.f2458b = dVar;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return new l<>(this.f2458b);
        }
    }

    private static m<com.airbnb.lottie.d> b(String str, Callable<l<com.airbnb.lottie.d>> callable) {
        com.airbnb.lottie.d dVarA = str == null ? null : com.airbnb.lottie.model.f.b().a(str);
        if (dVarA != null) {
            return new m<>(new g(dVarA));
        }
        if (str != null) {
            Map<String, m<com.airbnb.lottie.d>> map = f2444a;
            if (map.containsKey(str)) {
                return map.get(str);
            }
        }
        m<com.airbnb.lottie.d> mVar = new m<>(callable);
        if (str != null) {
            mVar.f(new a(str));
            mVar.e(new b(str));
            f2444a.put(str, mVar);
        }
        return mVar;
    }

    private static com.airbnb.lottie.g c(com.airbnb.lottie.d dVar, String str) {
        for (com.airbnb.lottie.g gVar : dVar.i().values()) {
            if (gVar.b().equals(str)) {
                return gVar;
            }
        }
        return null;
    }

    public static m<com.airbnb.lottie.d> d(Context context, String str) {
        return e(context, str, "asset_" + str);
    }

    public static m<com.airbnb.lottie.d> e(Context context, String str, String str2) {
        return b(str2, new d(context.getApplicationContext(), str, str2));
    }

    public static l<com.airbnb.lottie.d> f(Context context, String str, String str2) {
        try {
            return str.endsWith(".zip") ? r(new ZipInputStream(context.getAssets().open(str)), str2) : h(context.getAssets().open(str), str2);
        } catch (IOException e2) {
            return new l<>((Throwable) e2);
        }
    }

    public static m<com.airbnb.lottie.d> g(InputStream inputStream, String str) {
        return b(str, new f(inputStream, str));
    }

    public static l<com.airbnb.lottie.d> h(InputStream inputStream, String str) {
        return i(inputStream, str, true);
    }

    private static l<com.airbnb.lottie.d> i(InputStream inputStream, String str, boolean z) {
        try {
            return j(JsonReader.v(okio.e.a(okio.e.c(inputStream))), str);
        } finally {
            if (z) {
                com.airbnb.lottie.u.h.c(inputStream);
            }
        }
    }

    public static l<com.airbnb.lottie.d> j(JsonReader jsonReader, String str) {
        return k(jsonReader, str, true);
    }

    private static l<com.airbnb.lottie.d> k(JsonReader jsonReader, String str, boolean z) {
        try {
            try {
                com.airbnb.lottie.d dVarA = t.a(jsonReader);
                if (str != null) {
                    com.airbnb.lottie.model.f.b().c(str, dVarA);
                }
                l<com.airbnb.lottie.d> lVar = new l<>(dVarA);
                if (z) {
                    com.airbnb.lottie.u.h.c(jsonReader);
                }
                return lVar;
            } catch (Exception e2) {
                l<com.airbnb.lottie.d> lVar2 = new l<>(e2);
                if (z) {
                    com.airbnb.lottie.u.h.c(jsonReader);
                }
                return lVar2;
            }
        } catch (Throwable th) {
            if (z) {
                com.airbnb.lottie.u.h.c(jsonReader);
            }
            throw th;
        }
    }

    public static m<com.airbnb.lottie.d> l(Context context, int i) {
        return m(context, i, u(context, i));
    }

    public static m<com.airbnb.lottie.d> m(Context context, int i, String str) {
        return b(str, new CallableC0054e(new WeakReference(context), context.getApplicationContext(), i));
    }

    public static l<com.airbnb.lottie.d> n(Context context, int i) {
        return o(context, i, u(context, i));
    }

    public static l<com.airbnb.lottie.d> o(Context context, int i, String str) {
        try {
            return h(context.getResources().openRawResource(i), str);
        } catch (Resources.NotFoundException e2) {
            return new l<>((Throwable) e2);
        }
    }

    public static m<com.airbnb.lottie.d> p(Context context, String str) {
        return q(context, str, "url_" + str);
    }

    public static m<com.airbnb.lottie.d> q(Context context, String str, String str2) {
        return b(str2, new c(context, str, str2));
    }

    public static l<com.airbnb.lottie.d> r(ZipInputStream zipInputStream, String str) {
        try {
            return s(zipInputStream, str);
        } finally {
            com.airbnb.lottie.u.h.c(zipInputStream);
        }
    }

    private static l<com.airbnb.lottie.d> s(ZipInputStream zipInputStream, String str) {
        HashMap map = new HashMap();
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            com.airbnb.lottie.d dVarB = null;
            while (nextEntry != null) {
                String name = nextEntry.getName();
                if (name.contains("__MACOSX")) {
                    zipInputStream.closeEntry();
                } else if (nextEntry.getName().contains(".json")) {
                    dVarB = k(JsonReader.v(okio.e.a(okio.e.c(zipInputStream))), null, false).b();
                } else if (name.contains(".png") || name.contains(".webp")) {
                    map.put(name.split("/")[r1.length - 1], BitmapFactory.decodeStream(zipInputStream));
                } else {
                    zipInputStream.closeEntry();
                }
                nextEntry = zipInputStream.getNextEntry();
            }
            if (dVarB == null) {
                return new l<>((Throwable) new IllegalArgumentException("Unable to parse composition"));
            }
            for (Map.Entry entry : map.entrySet()) {
                com.airbnb.lottie.g gVarC = c(dVarB, (String) entry.getKey());
                if (gVarC != null) {
                    gVarC.f(com.airbnb.lottie.u.h.l((Bitmap) entry.getValue(), gVarC.e(), gVarC.c()));
                }
            }
            for (Map.Entry<String, com.airbnb.lottie.g> entry2 : dVarB.i().entrySet()) {
                if (entry2.getValue().a() == null) {
                    return new l<>((Throwable) new IllegalStateException("There is no image for " + entry2.getValue().b()));
                }
            }
            if (str != null) {
                com.airbnb.lottie.model.f.b().c(str, dVarB);
            }
            return new l<>(dVarB);
        } catch (IOException e2) {
            return new l<>((Throwable) e2);
        }
    }

    private static boolean t(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    private static String u(Context context, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes");
        sb.append(t(context) ? "_night_" : "_day_");
        sb.append(i);
        return sb.toString();
    }
}
