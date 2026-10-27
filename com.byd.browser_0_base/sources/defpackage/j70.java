package defpackage;

import java.util.concurrent.ExecutionException;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: DefaultBrowserInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class j70 {
    public static final Object a = new Object();
    public static h70 b;

    /* JADX INFO: compiled from: DefaultBrowserInfo.java */
    public static class a {
        public boolean a;
        public boolean b;
        public boolean c;
        public boolean d;
        public int e;
        public int f;
    }

    public static String a(boolean z) {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    h70 h70Var = new h70();
                    b = h70Var;
                    h70Var.c(i9.e);
                }
            }
        }
        try {
            return z ? b.g().get(0) : b.g().get(1);
        } catch (InterruptedException | ExecutionException unused) {
            return xz.a.getString(R$string.menu_open_in_product_default);
        }
    }
}
