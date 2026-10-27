package spi;

import android.graphics.drawable.Drawable;
import java.util.Objects;

/* JADX INFO: compiled from: MenuItemOEMV1.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e7 {
    public final int a;
    public final String b;
    public final Drawable c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final int j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final Runnable o;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e7)) {
            return false;
        }
        e7 e7Var = (e7) obj;
        return this.a == e7Var.a && this.d == e7Var.d && this.e == e7Var.e && this.f == e7Var.f && this.g == e7Var.g && this.h == e7Var.h && this.i == e7Var.i && this.j == e7Var.j && this.k == e7Var.k && this.l == e7Var.l && this.m == e7Var.m && this.n == e7Var.n && Objects.equals(this.b, e7Var.b) && Objects.equals(this.c, e7Var.c) && this.o == e7Var.o;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.a), this.b, this.c, Boolean.valueOf(this.d), Boolean.valueOf(this.e), Boolean.valueOf(this.f), Boolean.valueOf(this.g), Boolean.valueOf(this.h), Boolean.valueOf(this.i), Integer.valueOf(this.j), Boolean.valueOf(this.k), Boolean.valueOf(this.l), Boolean.valueOf(this.m), Boolean.valueOf(this.n));
    }

    /* JADX INFO: compiled from: MenuItemOEMV1.java */
    public static final class b {
        public int a;
        public String b;
        public Drawable c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public int j;
        public boolean k;
        public boolean l;
        public boolean m;
        public boolean n;
        public Runnable o;

        private b() {
            this.a = 0;
            this.d = true;
            this.e = true;
            this.f = false;
            this.g = true;
            this.h = false;
            this.i = false;
            this.j = 0;
            this.k = false;
            this.l = false;
            this.m = false;
            this.n = false;
        }

        private b(e7 e7Var) {
            this.a = 0;
            this.d = true;
            this.e = true;
            this.f = false;
            this.g = true;
            this.h = false;
            this.i = false;
            this.j = 0;
            this.k = false;
            this.l = false;
            this.m = false;
            this.n = false;
            this.a = e7Var.a;
            this.b = e7Var.b;
            this.c = e7Var.c;
            this.d = e7Var.d;
            this.e = e7Var.e;
            this.f = e7Var.f;
            this.g = e7Var.g;
            this.h = e7Var.h;
            this.i = e7Var.i;
            this.j = e7Var.j;
            this.k = e7Var.k;
            this.l = e7Var.l;
            this.m = e7Var.m;
            this.n = e7Var.n;
            this.o = e7Var.o;
        }
    }

    private e7(b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
        this.e = bVar.e;
        this.f = bVar.f;
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.k = bVar.k;
        this.l = bVar.l;
        this.m = bVar.m;
        this.n = bVar.n;
        this.o = bVar.o;
    }
}
