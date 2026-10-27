package defpackage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: compiled from: CustomTabColorProviderImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class i30 implements cv {
    public final boolean a;
    public final int b;
    public final int c;
    public final Integer d;
    public final Integer e;
    public final int f;

    public i30(int i, Context context, Intent intent) {
        j30 j30Var;
        try {
            j30Var = g50.a(intent, i == 0 ? 1 : i);
        } catch (Throwable unused) {
            Log.e("cr_CustomTabColorPrvdr", "Failed to parse CustomTabColorSchemeParams");
            j30Var = new j30(null, null, null, null);
        }
        Integer num = j30Var.a;
        boolean z = num != null;
        this.a = z;
        int iA = z7.A(z ? num.intValue() : yp.a(context, false));
        this.b = iA;
        Integer num2 = j30Var.b;
        this.c = z7.A(num2 != null ? num2.intValue() : iA);
        Integer num3 = j30Var.c;
        this.d = num3 != null ? Integer.valueOf(z7.A(num3.intValue())) : null;
        this.e = j30Var.d;
        int iL = q11.l(intent, "org.chromium.chrome.browser.customtabs.EXTRA_INITIAL_BACKGROUND_COLOR", 0);
        this.f = iL != 0 ? z7.A(iL) : iL;
    }

    @Override // defpackage.cv
    public final Integer a() {
        return this.d;
    }

    @Override // defpackage.cv
    public final Integer b() {
        return this.e;
    }

    @Override // defpackage.cv
    public final int c() {
        return this.c;
    }

    @Override // defpackage.cv
    public final int d() {
        return this.b;
    }

    @Override // defpackage.cv
    public final int e() {
        return this.f;
    }

    @Override // defpackage.cv
    public final boolean f() {
        return this.a;
    }
}
