package e0;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import e0.f;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: FontRequestWorker.java */
/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final n.e<String, Typeface> f6012a = new n.e<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ExecutorService f6013b = g.a("fonts-androidx", 10, 10000);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f6014c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final n.g<String, ArrayList<h0.a<C0066e>>> f6015d = new n.g<>();

    /* JADX INFO: compiled from: FontRequestWorker.java */
    class a implements Callable<C0066e> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f6016e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Context f6017g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ e0.d f6018h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ int f6019i;

        a(String str, Context context, e0.d dVar, int i7) {
            this.f6016e = str;
            this.f6017g = context;
            this.f6018h = dVar;
            this.f6019i = i7;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0066e call() {
            return e.c(this.f6016e, this.f6017g, this.f6018h, this.f6019i);
        }
    }

    /* JADX INFO: compiled from: FontRequestWorker.java */
    class b implements h0.a<C0066e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e0.a f6020a;

        b(e0.a aVar) {
            this.f6020a = aVar;
        }

        @Override // h0.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(C0066e c0066e) {
            this.f6020a.b(c0066e);
        }
    }

    /* JADX INFO: compiled from: FontRequestWorker.java */
    class c implements Callable<C0066e> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f6021e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Context f6022g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ e0.d f6023h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ int f6024i;

        c(String str, Context context, e0.d dVar, int i7) {
            this.f6021e = str;
            this.f6022g = context;
            this.f6023h = dVar;
            this.f6024i = i7;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0066e call() {
            return e.c(this.f6021e, this.f6022g, this.f6023h, this.f6024i);
        }
    }

    /* JADX INFO: compiled from: FontRequestWorker.java */
    class d implements h0.a<C0066e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f6025a;

        d(String str) {
            this.f6025a = str;
        }

        @Override // h0.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(C0066e c0066e) {
            synchronized (e.f6014c) {
                n.g<String, ArrayList<h0.a<C0066e>>> gVar = e.f6015d;
                ArrayList<h0.a<C0066e>> arrayList = gVar.get(this.f6025a);
                if (arrayList == null) {
                    return;
                }
                gVar.remove(this.f6025a);
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    arrayList.get(i7).accept(c0066e);
                }
            }
        }
    }

    private static String a(e0.d dVar, int i7) {
        return dVar.d() + "-" + i7;
    }

    @SuppressLint({"WrongConstant"})
    private static int b(f.a aVar) {
        int i7 = 1;
        if (aVar.c() != 0) {
            return aVar.c() != 1 ? -3 : -2;
        }
        f.b[] bVarArrB = aVar.b();
        if (bVarArrB != null && bVarArrB.length != 0) {
            i7 = 0;
            for (f.b bVar : bVarArrB) {
                int iB = bVar.b();
                if (iB != 0) {
                    if (iB < 0) {
                        return -3;
                    }
                    return iB;
                }
            }
        }
        return i7;
    }

    static C0066e c(String str, Context context, e0.d dVar, int i7) {
        n.e<String, Typeface> eVar = f6012a;
        Typeface typefaceC = eVar.c(str);
        if (typefaceC != null) {
            return new C0066e(typefaceC);
        }
        try {
            f.a aVarD = e0.c.d(context, dVar, null);
            int iB = b(aVarD);
            if (iB != 0) {
                return new C0066e(iB);
            }
            Typeface typefaceB = z.c.b(context, null, aVarD.b(), i7);
            if (typefaceB == null) {
                return new C0066e(-3);
            }
            eVar.d(str, typefaceB);
            return new C0066e(typefaceB);
        } catch (PackageManager.NameNotFoundException unused) {
            return new C0066e(-1);
        }
    }

    static Typeface d(Context context, e0.d dVar, int i7, Executor executor, e0.a aVar) {
        String strA = a(dVar, i7);
        Typeface typefaceC = f6012a.c(strA);
        if (typefaceC != null) {
            aVar.b(new C0066e(typefaceC));
            return typefaceC;
        }
        b bVar = new b(aVar);
        synchronized (f6014c) {
            n.g<String, ArrayList<h0.a<C0066e>>> gVar = f6015d;
            ArrayList<h0.a<C0066e>> arrayList = gVar.get(strA);
            if (arrayList != null) {
                arrayList.add(bVar);
                return null;
            }
            ArrayList<h0.a<C0066e>> arrayList2 = new ArrayList<>();
            arrayList2.add(bVar);
            gVar.put(strA, arrayList2);
            c cVar = new c(strA, context, dVar, i7);
            if (executor == null) {
                executor = f6013b;
            }
            g.b(executor, cVar, new d(strA));
            return null;
        }
    }

    static Typeface e(Context context, e0.d dVar, e0.a aVar, int i7, int i8) {
        String strA = a(dVar, i7);
        Typeface typefaceC = f6012a.c(strA);
        if (typefaceC != null) {
            aVar.b(new C0066e(typefaceC));
            return typefaceC;
        }
        if (i8 == -1) {
            C0066e c0066eC = c(strA, context, dVar, i7);
            aVar.b(c0066eC);
            return c0066eC.f6026a;
        }
        try {
            C0066e c0066e = (C0066e) g.c(f6013b, new a(strA, context, dVar, i7), i8);
            aVar.b(c0066e);
            return c0066e.f6026a;
        } catch (InterruptedException unused) {
            aVar.b(new C0066e(-3));
            return null;
        }
    }

    /* JADX INFO: renamed from: e0.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontRequestWorker.java */
    static final class C0066e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Typeface f6026a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f6027b;

        C0066e(int i7) {
            this.f6026a = null;
            this.f6027b = i7;
        }

        @SuppressLint({"WrongConstant"})
        boolean a() {
            return this.f6027b == 0;
        }

        @SuppressLint({"WrongConstant"})
        C0066e(Typeface typeface) {
            this.f6026a = typeface;
            this.f6027b = 0;
        }
    }
}
