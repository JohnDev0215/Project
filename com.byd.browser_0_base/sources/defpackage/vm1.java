package defpackage;

import android.net.Uri;

/* JADX INFO: compiled from: NewTabPageUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vm1 {
    public static int a(String str) {
        String queryParameter;
        return (bk3.k(str) && (queryParameter = Uri.parse(str).getQueryParameter("origin")) != null && queryParameter.equals("web-feed")) ? 1 : 0;
    }

    public static String b() {
        Uri.Builder builderBuildUpon = Uri.parse("chrome-native://newtab/").buildUpon();
        builderBuildUpon.appendQueryParameter("origin", "web-feed");
        return builderBuildUpon.build().toString();
    }
}
