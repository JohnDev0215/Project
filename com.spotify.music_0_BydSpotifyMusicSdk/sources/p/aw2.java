package p;

import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public final class aw2 implements zv2 {
    public static final HttpUrl a;

    static {
        HttpUrl httpUrlC;
        HttpUrl.k.getClass();
        try {
            httpUrlC = HttpUrl.Companion.c("https://api.spotify.com/");
        } catch (IllegalArgumentException unused) {
            httpUrlC = null;
        }
        a = httpUrlC;
    }
}
