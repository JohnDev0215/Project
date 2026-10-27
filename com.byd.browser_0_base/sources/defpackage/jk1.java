package defpackage;

import android.net.Uri;
import android.view.View;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: NativePage.java */
/* JADX INFO: loaded from: classes2.dex */
public interface jk1 {
    static boolean h(GURL gurl, boolean z) {
        return (gurl == null || q(gurl.j(), null, z) == 0) ? false : true;
    }

    @Deprecated
    static boolean p(String str, boolean z) {
        return q(str, null, z) != 0;
    }

    static int q(String str, jk1 jk1Var, boolean z) {
        if (str == null) {
            return 0;
        }
        Uri uri = Uri.parse(str);
        if (!"chrome-native".equals(uri.getScheme()) && !"chrome".equals(uri.getScheme())) {
            return 0;
        }
        String host = uri.getHost();
        if (jk1Var != null && jk1Var.k().equals(host)) {
            return 1;
        }
        if ("newtab".equals(host)) {
            return 2;
        }
        if ("bookmarks".equals(host)) {
            return 3;
        }
        if ("csacsacascasc@vsdvdsvdvdv%^*dsvdsvvdsvdsv".equals(host)) {
            return 5;
        }
        if ("history".equals(host)) {
            return 6;
        }
        if ("recent-tabs".equals(host) && !z) {
            return 4;
        }
        if ("explore".equals(host)) {
            return 7;
        }
        return "management".equals(host) ? 8 : 0;
    }

    void destroy();

    void e(String str);

    String getTitle();

    String getUrl();

    View getView();

    default boolean isFrozen() {
        return false;
    }

    String k();

    int r();

    default void a() {
    }

    default void o() {
    }

    default int d(int i) {
        return i;
    }

    default float f(float f) {
        return f;
    }

    default int g(int i) {
        return i;
    }
}
