package e0;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import h0.h;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f6028a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b[] f6029b;

        @Deprecated
        public a(int i7, b[] bVarArr) {
            this.f6028a = i7;
            this.f6029b = bVarArr;
        }

        static a a(int i7, b[] bVarArr) {
            return new a(i7, bVarArr);
        }

        public b[] b() {
            return this.f6029b;
        }

        public int c() {
            return this.f6028a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Uri f6030a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f6031b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f6032c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f6033d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f6034e;

        @Deprecated
        public b(Uri uri, int i7, int i8, boolean z6, int i9) {
            this.f6030a = (Uri) h.f(uri);
            this.f6031b = i7;
            this.f6032c = i8;
            this.f6033d = z6;
            this.f6034e = i9;
        }

        static b a(Uri uri, int i7, int i8, boolean z6, int i9) {
            return new b(uri, i7, i8, z6, i9);
        }

        public int b() {
            return this.f6034e;
        }

        public int c() {
            return this.f6031b;
        }

        public Uri d() {
            return this.f6030a;
        }

        public int e() {
            return this.f6032c;
        }

        public boolean f() {
            return this.f6033d;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class c {
        public void a(int i7) {
            throw null;
        }

        public void b(Typeface typeface) {
            throw null;
        }
    }

    public static Typeface a(Context context, d dVar, int i7, boolean z6, int i8, Handler handler, c cVar) {
        e0.a aVar = new e0.a(cVar, handler);
        return z6 ? e.e(context, dVar, aVar, i7, i8) : e.d(context, dVar, i7, null, aVar);
    }
}
