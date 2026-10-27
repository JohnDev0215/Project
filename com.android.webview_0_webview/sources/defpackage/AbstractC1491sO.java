package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: renamed from: sO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1491sO {
    public Callback a;
    public JO b;

    public static void a(AbstractC1491sO abstractC1491sO, JO jo, Callback callback) {
        if (jo == null) {
            abstractC1491sO.e(abstractC1491sO.b);
            abstractC1491sO.b = null;
            abstractC1491sO.a = null;
        } else {
            abstractC1491sO.b = jo;
            abstractC1491sO.a = callback;
            abstractC1491sO.b(jo);
        }
    }

    public static String d(JO jo) {
        String str = (String) jo.a(AbstractC1711wO.b);
        return str == null ? (String) jo.a(AbstractC1711wO.c) : str;
    }

    public abstract void b(JO jo);

    public final void c(int i) {
        Callback callback = this.a;
        if (callback == null) {
            return;
        }
        this.a = null;
        callback.a(Integer.valueOf(i));
    }

    public abstract void e(JO jo);
}
