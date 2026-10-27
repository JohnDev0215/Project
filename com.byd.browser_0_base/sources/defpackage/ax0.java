package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: ImageFetcher.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ax0 {
    public final cx0 a;

    /* JADX INFO: compiled from: ImageFetcher.java */
    public static class a {
        public final String a;
        public final String b;
        public final int c;
        public final int d;
        public final boolean e;
        public final int f;

        public a(int i, int i2, int i3, String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = i2;
            this.e = z;
            this.f = i3;
        }

        @Deprecated
        public static a a(int i, int i2, String str, String str2) {
            return new a(i, i2, 0, str, str2, i > 0 && i2 > 0);
        }

        @Deprecated
        public static a b(String str, String str2) {
            return new a(0, 0, 0, str, str2, false);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a.equals(aVar.a) && this.b.equals(aVar.b) && this.c == aVar.c && this.d == aVar.d && this.e == aVar.e && this.f == aVar.f;
        }

        public final int hashCode() {
            String str = this.a;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.b;
            return ((((((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.c) * 31) + this.d) * 2) + (this.e ? 1 : 0)) * 31) + this.f;
        }
    }

    public ax0() {
    }

    public ax0(ax0 ax0Var) {
        this.a = ax0Var.a;
    }

    public abstract void a();

    public abstract void b();

    public abstract void c(a aVar, Callback<od> callback);

    public abstract void d(a aVar, Callback<Bitmap> callback);

    public abstract int e();

    public ax0(cx0 cx0Var) {
        this.a = cx0Var;
    }
}
