package defpackage;

import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Tz {
    public static final InterfaceC1297ov b = new Sz(null);
    public static final C1242nv c = C1242nv.i.e(b, new Tz());
    public static final C1242nv d;
    public static final C1242nv e;
    public static final C1242nv f;
    public static final C1242nv g;
    public int a = 0;

    static {
        C1242nv c1242nv = C1242nv.i;
        InterfaceC1297ov interfaceC1297ov = b;
        Tz tz = new Tz();
        tz.a = 1;
        d = c1242nv.e(interfaceC1297ov, tz);
        e = c.b(0);
        f = c.b(1);
        g = c.b(2);
        if (FD.b) {
            return;
        }
        FD.b = true;
        PostTask.e.set(1, new FD());
    }
}
