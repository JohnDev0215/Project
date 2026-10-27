package io.sentry.android.core;

import android.util.Log;
import io.sentry.EnumC1495p1;

/* JADX INFO: loaded from: classes.dex */
public final class P implements J, io.sentry.K {
    public static final P l = new P();

    @Override // io.sentry.K
    public void k(EnumC1495p1 enumC1495p1, String str, Object... objArr) {
        int i7 = 5;
        if (objArr.length == 0) {
            int i10 = AbstractC1434j.f16242a[enumC1495p1.ordinal()];
            if (i10 == 1) {
                i7 = 4;
            } else if (i10 != 2) {
                i7 = i10 != 4 ? 3 : 7;
            }
            Log.println(i7, "Sentry", str);
            return;
        }
        int i11 = AbstractC1434j.f16242a[enumC1495p1.ordinal()];
        if (i11 == 1) {
            i7 = 4;
        } else if (i11 != 2) {
            i7 = i11 != 4 ? 3 : 7;
        }
        Log.println(i7, "Sentry", String.format(str, objArr));
    }

    @Override // io.sentry.K
    public boolean m(EnumC1495p1 enumC1495p1) {
        return true;
    }

    @Override // io.sentry.K
    public void p(EnumC1495p1 enumC1495p1, Throwable th, String str, Object... objArr) {
        if (objArr.length == 0) {
            s(enumC1495p1, str, th);
        } else {
            s(enumC1495p1, String.format(str, objArr), th);
        }
    }

    @Override // io.sentry.K
    public void s(EnumC1495p1 enumC1495p1, String str, Throwable th) {
        int i7 = AbstractC1434j.f16242a[enumC1495p1.ordinal()];
        if (i7 == 1) {
            Log.i("Sentry", str, th);
            return;
        }
        if (i7 == 2) {
            Log.w("Sentry", str, th);
            return;
        }
        if (i7 == 3) {
            Log.e("Sentry", str, th);
        } else if (i7 != 4) {
            Log.d("Sentry", str, th);
        } else {
            Log.wtf("Sentry", str, th);
        }
    }
}
