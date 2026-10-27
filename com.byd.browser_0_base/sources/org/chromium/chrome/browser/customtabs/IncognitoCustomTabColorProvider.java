package org.chromium.chrome.browser.customtabs;

import android.content.Context;
import defpackage.cv;
import defpackage.yp;

/* JADX INFO: loaded from: classes.dex */
public final class IncognitoCustomTabColorProvider implements cv {
    public final int a;
    public final int b;
    public final int c;

    public IncognitoCustomTabColorProvider(Context context) {
        int iA = yp.a(context, true);
        this.c = iA;
        this.b = iA;
        this.a = iA;
    }

    @Override // defpackage.cv
    public final Integer a() {
        return Integer.valueOf(this.c);
    }

    @Override // defpackage.cv
    public final Integer b() {
        return null;
    }

    @Override // defpackage.cv
    public final int c() {
        return this.b;
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
        return false;
    }
}
