package defpackage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import defpackage.zf3;
import org.chromium.base.SysUtils;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$style;

/* JADX INFO: compiled from: WebappIntentDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ms3 extends oj {
    public final za3 a;
    public final zf3 b;
    public final bk2 c;
    public final js3 d;
    public final cp3 e;
    public final int f;
    public final Intent g;
    public final a h;

    /* JADX INFO: compiled from: WebappIntentDataProvider.java */
    public static final class a implements cv {
        public final int a;
        public final boolean b;

        public a(int i, boolean z) {
            this.a = i;
            this.b = z;
        }

        @Override // defpackage.cv
        public final Integer a() {
            return null;
        }

        @Override // defpackage.cv
        public final Integer b() {
            return null;
        }

        @Override // defpackage.cv
        public final int c() {
            return this.a;
        }

        @Override // defpackage.cv
        public final int d() {
            return this.a;
        }

        @Override // defpackage.cv
        public final int e() {
            return 0;
        }

        @Override // defpackage.cv
        public final boolean f() {
            return this.b;
        }
    }

    public ms3(Intent intent, int i, boolean z, bk2 bk2Var, js3 js3Var, cp3 cp3Var) {
        this.g = intent;
        this.h = new a(i, z);
        this.a = za3.a(new ContextThemeWrapper(xz.a, SysUtils.isLowEndDevice() ? R$style.Theme_Chromium_WithWindowAnimation_LowEnd : R$style.Theme_Chromium_WithWindowAnimation), R$drawable.btn_close);
        this.b = js3Var.g == 4 ? new zf3.b(0, false) : new zf3.a();
        this.c = bk2Var;
        this.d = js3Var;
        this.e = cp3Var;
        this.f = cp3Var == null ? 3 : 4;
    }

    @Override // defpackage.oj
    public final bk2 A() {
        return this.c;
    }

    @Override // defpackage.oj
    public final int E() {
        return 1;
    }

    @Override // defpackage.oj
    public final zf3 I() {
        return this.b;
    }

    @Override // defpackage.oj
    public final int J() {
        return 4;
    }

    @Override // defpackage.oj
    public final String K() {
        return this.d.b;
    }

    @Override // defpackage.oj
    public final cp3 L() {
        return this.e;
    }

    @Override // defpackage.oj
    public final js3 M() {
        return this.d;
    }

    @Override // defpackage.oj
    public final boolean Z() {
        return false;
    }

    @Override // defpackage.oj
    public final int d() {
        return this.f;
    }

    @Override // defpackage.oj
    public final String j() {
        cp3 cp3Var = this.e;
        if (cp3Var != null) {
            return cp3Var.a;
        }
        return null;
    }

    @Override // defpackage.oj
    public final Drawable k() {
        return this.a;
    }

    @Override // defpackage.oj
    public final cv m() {
        return this.h;
    }

    @Override // defpackage.oj
    public final int p() {
        return this.d.h;
    }

    @Override // defpackage.oj
    public final Intent t() {
        return this.g;
    }
}
