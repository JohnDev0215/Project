package defpackage;

import android.os.Bundle;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class P1 {
    public static final Object a = new Object();

    public static Bundle a(K1 k1) {
        Bundle bundle = new Bundle();
        bundle.putInt("icon", k1.g);
        bundle.putCharSequence("title", k1.h);
        bundle.putParcelable("actionIntent", k1.i);
        Bundle bundle2 = k1.a != null ? new Bundle(k1.a) : new Bundle();
        bundle2.putBoolean("android.support.allowGeneratedReplies", k1.d);
        bundle.putBundle("extras", bundle2);
        bundle.putParcelableArray("remoteInputs", b(null));
        bundle.putBoolean("showsUserInterface", k1.e);
        bundle.putInt("semanticAction", 0);
        return bundle;
    }

    public static Bundle[] b(W1[] w1Arr) {
        if (w1Arr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[w1Arr.length];
        if (w1Arr.length <= 0) {
            return bundleArr;
        }
        W1 w1 = w1Arr[0];
        new Bundle();
        throw null;
    }
}
