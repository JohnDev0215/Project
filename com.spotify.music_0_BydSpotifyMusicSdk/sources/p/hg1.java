package p;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class hg1 implements kg1 {
    public final Object a;
    public final Object b;

    public hg1(Bundle bundle, String str) {
        if (str == null) {
            throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead");
        }
        this.a = str;
        this.b = bundle;
    }
}
