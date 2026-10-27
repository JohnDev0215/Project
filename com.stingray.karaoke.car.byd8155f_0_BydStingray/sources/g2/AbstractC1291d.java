package g2;

import android.os.Bundle;
import android.text.Spanned;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: g2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1291d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f14803a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f14804b;
    public static final String c;
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f14805e;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14803a = Integer.toString(0, 36);
        f14804b = Integer.toString(1, 36);
        c = Integer.toString(2, 36);
        d = Integer.toString(3, 36);
        f14805e = Integer.toString(4, 36);
    }

    public static Bundle a(Spanned spanned, Object obj, int i7, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt(f14803a, spanned.getSpanStart(obj));
        bundle2.putInt(f14804b, spanned.getSpanEnd(obj));
        bundle2.putInt(c, spanned.getSpanFlags(obj));
        bundle2.putInt(d, i7);
        if (bundle != null) {
            bundle2.putBundle(f14805e, bundle);
        }
        return bundle2;
    }
}
