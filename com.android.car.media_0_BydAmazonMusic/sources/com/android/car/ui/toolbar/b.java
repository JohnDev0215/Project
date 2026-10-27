package com.android.car.ui.toolbar;

import android.annotation.TargetApi;
import android.car.drivingstate.XCarUxRestrictions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.android.car.ui.R$drawable;
import com.android.car.ui.R$string;
import com.blankj.utilcode.util.Utils;
import java.lang.ref.WeakReference;
import spi.e2;
import spi.mc;
import spi.n;
import spi.ya;

/* JADX INFO: compiled from: MenuItem.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class b {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public int f;
    public XCarUxRestrictions g;
    public WeakReference<d> h;
    public CharSequence i;
    public Drawable j;
    public e k;
    public final c l;
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public final mc r;

    /* JADX INFO: renamed from: com.android.car.ui.toolbar.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MenuItem.java */
    public static final class C0013b {
        public final Context a;
        public String b;
        public String c;
        public Drawable d;
        public Drawable e;
        public CharSequence g;
        public Drawable h;
        public e i;
        public int f = -1;
        public c j = c.ALWAYS;
        public boolean k = true;
        public boolean l = false;
        public boolean m = true;
        public boolean n = false;
        public boolean o = false;
        public boolean p = true;
        public boolean q = false;
        public boolean r = false;
        public boolean s = false;
        public boolean t = false;
        public int u = 0;

        public C0013b(Context context) {
            this.a = context.getApplicationContext();
        }

        public final b a() {
            boolean z = this.q;
            if (z && (this.l || this.h == null)) {
                throw new IllegalStateException("Only simple icons can be activatable");
            }
            if (this.n && (this.l || z)) {
                throw new IllegalStateException("Unsupported options for a checkable MenuItem");
            }
            boolean z2 = this.s;
            if (z2 && this.t) {
                throw new IllegalStateException("Can't have both a search and settings MenuItem");
            }
            if (z && this.j == c.NEVER) {
                throw new IllegalStateException("Activatable MenuItems not supported as Overflow");
            }
            if (z2 && (!this.b.contentEquals(this.g) || !this.d.equals(this.h) || this.n || this.q || !this.k || this.l || this.j != c.ALWAYS)) {
                throw new IllegalStateException("Invalid search MenuItem");
            }
            if (!this.t || (this.c.contentEquals(this.g) && this.e.equals(this.h) && !this.n && !this.q && this.k && !this.l && this.j == c.ALWAYS)) {
                return new b(this);
            }
            throw new IllegalStateException("Invalid settings MenuItem");
        }

        public final C0013b b(int i) {
            this.h = i == 0 ? null : this.a.getDrawable(i);
            return this;
        }

        public final C0013b c(int i) {
            this.g = this.a.getString(i);
            return this;
        }

        public final C0013b d() {
            this.b = this.a.getString(R$string.car_ui_toolbar_menu_item_search_title);
            Drawable drawable = this.a.getDrawable(R$drawable.car_ui_icon_search);
            this.d = drawable;
            this.s = true;
            this.g = this.b;
            this.h = drawable;
            return this;
        }

        public final C0013b e() {
            this.c = this.a.getString(R$string.car_ui_toolbar_menu_item_settings_title);
            Drawable drawable = this.a.getDrawable(R$drawable.car_ui_icon_settings);
            this.e = drawable;
            this.t = true;
            this.g = this.c;
            this.h = drawable;
            this.u = 64;
            return this;
        }
    }

    /* JADX INFO: compiled from: MenuItem.java */
    public enum c {
        ALWAYS,
        NEVER
    }

    /* JADX INFO: compiled from: MenuItem.java */
    public interface d {
        void a(b bVar);
    }

    /* JADX INFO: compiled from: MenuItem.java */
    public interface e {
        void b(b bVar);
    }

    public final boolean a() {
        return e2.b(this.m, this.g);
    }

    public final void b() {
        if (this.n && this.p) {
            if (!this.c && a()) {
                n.b.a.execute(new ya(Utils.getApp().getResources().getString(R$string.car_ui_restricted_while_driving), 1));
                return;
            }
            if (this.b) {
                c(!this.q);
            }
            boolean z = this.a;
            if (z) {
                boolean z2 = true ^ this.o;
                if (!z) {
                    throw new IllegalStateException("Cannot call setChecked() on a non-checkable MenuItem");
                }
                this.o = z2;
                f();
            }
            e eVar = this.k;
            if (eVar != null) {
                eVar.b(this);
            }
        }
    }

    public final void c(boolean z) {
        if (!this.b) {
            throw new IllegalStateException("Cannot call setActivated() on a non-activatable MenuItem");
        }
        this.q = z;
        f();
    }

    public final void d(@Nullable d dVar) {
        this.h = new WeakReference<>(dVar);
    }

    public final void e(boolean z) {
        this.p = z;
        f();
    }

    public final void f() {
        d dVar = this.h.get();
        if (dVar != null) {
            dVar.a(this);
        }
    }

    public void setOnClickListener(e eVar) {
        this.k = eVar;
        f();
    }

    private b(C0013b c0013b) {
        this.h = new WeakReference<>(null);
        mc mcVar = new mc(this, 1);
        this.r = mcVar;
        Context context = c0013b.a;
        this.f = c0013b.f;
        this.a = c0013b.n;
        this.b = c0013b.q;
        this.i = c0013b.g;
        this.j = c0013b.h;
        this.k = c0013b.i;
        this.l = c0013b.j;
        this.n = c0013b.m;
        this.o = c0013b.o;
        this.p = c0013b.p;
        this.q = c0013b.r;
        this.c = c0013b.s;
        this.d = c0013b.l;
        this.e = c0013b.k;
        this.m = c0013b.u;
        e2.a(context).register(mcVar);
    }
}
