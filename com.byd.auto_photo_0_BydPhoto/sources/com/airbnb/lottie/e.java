package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.WorkerThread;
import com.airbnb.lottie.v.t;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<String, m<com.airbnb.lottie.d>> f663a = new HashMap();

    class a implements h<com.airbnb.lottie.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f664a;

        a(String str) {
            this.f664a = str;
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(com.airbnb.lottie.d dVar) {
            e.f663a.remove(this.f664a);
        }
    }

    class b implements h<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f665a;

        b(String str) {
            this.f665a = str;
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th) {
            e.f663a.remove(this.f665a);
        }
    }

    class c implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f666a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f667b;

        c(Context context, String str) {
            this.f666a = context;
            this.f667b = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return com.airbnb.lottie.u.c.e(this.f666a, this.f667b);
        }
    }

    class d implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f668a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f669b;

        d(Context context, String str) {
            this.f668a = context;
            this.f669b = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return e.e(this.f668a, this.f669b);
        }
    }

    /* JADX INFO: renamed from: com.airbnb.lottie.e$e, reason: collision with other inner class name */
    class CallableC0022e implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ WeakReference f670a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f671b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f672c;

        CallableC0022e(WeakReference weakReference, Context context, int i) {
            this.f670a = weakReference;
            this.f671b = context;
            this.f672c = i;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            Context context = (Context) this.f670a.get();
            if (context == null) {
                context = this.f671b;
            }
            return e.l(context, this.f672c);
        }
    }

    class f implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ InputStream f673a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f674b;

        f(InputStream inputStream, String str) {
            this.f673a = inputStream;
            this.f674b = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return e.g(this.f673a, this.f674b);
        }
    }

    class g implements Callable<l<com.airbnb.lottie.d>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.airbnb.lottie.d f675a;

        g(com.airbnb.lottie.d dVar) {
            this.f675a = dVar;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l<com.airbnb.lottie.d> call() {
            return new l<>(this.f675a);
        }
    }

    private static m<com.airbnb.lottie.d> b(@Nullable String str, Callable<l<com.airbnb.lottie.d>> callable) {
        com.airbnb.lottie.d dVarA = str == null ? null : com.airbnb.lottie.t.g.b().a(str);
        if (dVarA != null) {
            return new m<>(new g(dVarA));
        }
        if (str != null) {
            Map<String, m<com.airbnb.lottie.d>> map = f663a;
            if (map.containsKey(str)) {
                return map.get(str);
            }
        }
        m<com.airbnb.lottie.d> mVar = new m<>(callable);
        mVar.f(new a(str));
        mVar.e(new b(str));
        f663a.put(str, mVar);
        return mVar;
    }

    @Nullable
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

    @WorkerThread
    public static l<com.airbnb.lottie.d> e(Context context, String str) {
        try {
            String str2 = "asset_" + str;
            return str.endsWith(".zip") ? n(new ZipInputStream(context.getAssets().open(str)), str2) : g(context.getAssets().open(str), str2);
        } catch (IOException e) {
            return new l<>((Throwable) e);
        }
    }

    public static m<com.airbnb.lottie.d> f(InputStream inputStream, @Nullable String str) {
        return b(str, new f(inputStream, str));
    }

    @WorkerThread
    public static l<com.airbnb.lottie.d> g(InputStream inputStream, @Nullable String str) {
        return h(inputStream, str, true);
    }

    @WorkerThread
    private static l<com.airbnb.lottie.d> h(InputStream inputStream, @Nullable String str, boolean z) {
        try {
            return i(com.airbnb.lottie.v.k0.c.v(c.e.a(c.e.c(inputStream))), str);
        } finally {
            if (z) {
                com.airbnb.lottie.w.h.c(inputStream);
            }
        }
    }

    @WorkerThread
    public static l<com.airbnb.lottie.d> i(com.airbnb.lottie.v.k0.c cVar, @Nullable String str) {
        return j(cVar, str, true);
    }

    private static l<com.airbnb.lottie.d> j(com.airbnb.lottie.v.k0.c cVar, @Nullable String str, boolean z) {
        try {
            try {
                com.airbnb.lottie.d dVarA = t.a(cVar);
                if (str != null) {
                    com.airbnb.lottie.t.g.b().c(str, dVarA);
                }
                l<com.airbnb.lottie.d> lVar = new l<>(dVarA);
                if (z) {
                    com.airbnb.lottie.w.h.c(cVar);
                }
                return lVar;
            } catch (Exception e) {
                l<com.airbnb.lottie.d> lVar2 = new l<>(e);
                if (z) {
                    com.airbnb.lottie.w.h.c(cVar);
                }
                return lVar2;
            }
        } catch (Throwable th) {
            if (z) {
                com.airbnb.lottie.w.h.c(cVar);
            }
            throw th;
        }
    }

    public static m<com.airbnb.lottie.d> k(Context context, @RawRes int i) {
        return b(q(context, i), new CallableC0022e(new WeakReference(context), context.getApplicationContext(), i));
    }

    @WorkerThread
    public static l<com.airbnb.lottie.d> l(Context context, @RawRes int i) {
        try {
            return g(context.getResources().openRawResource(i), q(context, i));
        } catch (Resources.NotFoundException e) {
            return new l<>((Throwable) e);
        }
    }

    public static m<com.airbnb.lottie.d> m(Context context, String str) {
        return b("url_" + str, new c(context, str));
    }

    @WorkerThread
    public static l<com.airbnb.lottie.d> n(ZipInputStream zipInputStream, @Nullable String str) {
        try {
            return o(zipInputStream, str);
        } finally {
            com.airbnb.lottie.w.h.c(zipInputStream);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0019 A[Catch: IOException -> 0x0107, TryCatch #0 {IOException -> 0x0107, blocks: (B:3:0x0005, B:5:0x000d, B:7:0x0019, B:16:0x0064, B:8:0x001d, B:10:0x0029, B:11:0x0042, B:13:0x004a, B:15:0x0052), top: B:41:0x0005 }] */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.airbnb.lottie.l<com.airbnb.lottie.d> o(java.util.zip.ZipInputStream r6, @androidx.annotation.Nullable java.lang.String r7) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.e.o(java.util.zip.ZipInputStream, java.lang.String):com.airbnb.lottie.l");
    }

    private static boolean p(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    private static String q(Context context, @RawRes int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes");
        sb.append(p(context) ? "_night_" : "_day_");
        sb.append(i);
        return sb.toString();
    }
}
