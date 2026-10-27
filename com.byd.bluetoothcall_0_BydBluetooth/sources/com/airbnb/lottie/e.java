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
    private static final Map<String, m<com.airbnb.lottie.d>> f2830a = new HashMap();

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class a implements h<com.airbnb.lottie.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2831a;

        a(String str) {
            this.f2831a = str;
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(com.airbnb.lottie.d dVar) {
            e.f2830a.remove(this.f2831a);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class b implements h<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2832a;

        b(String str) {
            this.f2832a = str;
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            e.f2830a.remove(this.f2832a);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class c implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f2833b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f2834c;

        c(Context context, String str) {
            this.f2833b = context;
            this.f2834c = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return com.airbnb.lottie.network.b.e(this.f2833b, this.f2834c);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class d implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f2835b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f2836c;

        d(Context context, String str) {
            this.f2835b = context;
            this.f2836c = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return e.e(this.f2835b, this.f2836c);
        }
    }

    /* JADX INFO: renamed from: com.airbnb.lottie.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class CallableC0073e implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ WeakReference f2837b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Context f2838c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f2839d;

        CallableC0073e(WeakReference weakReference, Context context, int i) {
            this.f2837b = weakReference;
            this.f2838c = context;
            this.f2839d = i;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            Context context = (Context) this.f2837b.get();
            if (context == null) {
                context = this.f2838c;
            }
            return e.l(context, this.f2839d);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class f implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ InputStream f2840b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f2841c;

        f(InputStream inputStream, String str) {
            this.f2840b = inputStream;
            this.f2841c = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return e.g(this.f2840b, this.f2841c);
        }
    }

    /* JADX INFO: compiled from: LottieCompositionFactory.java */
    class g implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.airbnb.lottie.d f2842b;

        g(com.airbnb.lottie.d dVar) {
            this.f2842b = dVar;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return new l<>(this.f2842b);
        }
    }

    private static m<com.airbnb.lottie.d> b(String str, Callable<l<com.airbnb.lottie.d>> callable) {
        com.airbnb.lottie.d dVarA = str == null ? null : com.airbnb.lottie.model.f.b().a(str);
        if (dVarA != null) {
            return new m<>(new g(dVarA));
        }
        if (str != null) {
            Map<String, m<com.airbnb.lottie.d>> map = f2830a;
            if (map.containsKey(str)) {
                return map.get(str);
            }
        }
        m<com.airbnb.lottie.d> mVar = new m<>(callable);
        mVar.f(new a(str));
        mVar.e(new b(str));
        f2830a.put(str, mVar);
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
        return b(str, new d(context.getApplicationContext(), str));
    }

    public static l<com.airbnb.lottie.d> e(Context context, String str) {
        try {
            String str2 = "asset_" + str;
            return str.endsWith(".zip") ? n(new ZipInputStream(context.getAssets().open(str)), str2) : g(context.getAssets().open(str), str2);
        } catch (IOException e2) {
            return new l<>((Throwable) e2);
        }
    }

    public static m<com.airbnb.lottie.d> f(InputStream inputStream, String str) {
        return b(str, new f(inputStream, str));
    }

    public static l<com.airbnb.lottie.d> g(InputStream inputStream, String str) {
        return h(inputStream, str, true);
    }

    private static l<com.airbnb.lottie.d> h(InputStream inputStream, String str, boolean z) {
        try {
            return i(JsonReader.w(okio.e.a(okio.e.c(inputStream))), str);
        } finally {
            if (z) {
                com.airbnb.lottie.u.h.c(inputStream);
            }
        }
    }

    public static l<com.airbnb.lottie.d> i(JsonReader jsonReader, String str) {
        return j(jsonReader, str, true);
    }

    private static l<com.airbnb.lottie.d> j(JsonReader jsonReader, String str, boolean z) {
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

    public static m<com.airbnb.lottie.d> k(Context context, int i) {
        return b(q(context, i), new CallableC0073e(new WeakReference(context), context.getApplicationContext(), i));
    }

    public static l<com.airbnb.lottie.d> l(Context context, int i) {
        try {
            return g(context.getResources().openRawResource(i), q(context, i));
        } catch (Resources.NotFoundException e2) {
            return new l<>((Throwable) e2);
        }
    }

    public static m<com.airbnb.lottie.d> m(Context context, String str) {
        return b("url_" + str, new c(context, str));
    }

    public static l<com.airbnb.lottie.d> n(ZipInputStream zipInputStream, String str) {
        try {
            return o(zipInputStream, str);
        } finally {
            com.airbnb.lottie.u.h.c(zipInputStream);
        }
    }

    private static l<com.airbnb.lottie.d> o(ZipInputStream zipInputStream, String str) {
        HashMap map = new HashMap();
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            com.airbnb.lottie.d dVarB = null;
            while (nextEntry != null) {
                String name = nextEntry.getName();
                if (name.contains("__MACOSX")) {
                    zipInputStream.closeEntry();
                } else if (nextEntry.getName().contains(".json")) {
                    dVarB = j(JsonReader.w(okio.e.a(okio.e.c(zipInputStream))), null, false).b();
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

    private static boolean p(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    private static String q(Context context, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes");
        sb.append(p(context) ? "_night_" : "_day_");
        sb.append(i);
        return sb.toString();
    }
}
