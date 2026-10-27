package defpackage;

import android.view.WindowInsets;

/* JADX INFO: renamed from: q3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1361q3 {
    public final Object a;

    public C1361q3(Object obj) {
        this.a = obj;
    }

    public int a() {
        return ((WindowInsets) this.a).getSystemWindowInsetTop();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1361q3.class != obj.getClass()) {
            return false;
        }
        Object obj2 = this.a;
        Object obj3 = ((C1361q3) obj).a;
        return obj2 == null ? obj3 == null : obj2.equals(obj3);
    }

    public int hashCode() {
        Object obj = this.a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
