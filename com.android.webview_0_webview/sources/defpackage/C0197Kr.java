package defpackage;

import java.io.File;

/* JADX INFO: renamed from: Kr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0197Kr {
    public final AbstractC0125Gr[] a;

    public C0197Kr() {
        Px px = new Px(AbstractC0267Oq.a());
        this.a = new AbstractC0125Gr[]{new C0161Ir(new File(px.e(), "uploads.log")), new C0143Hr(px), new Lr(new File(AbstractC0267Oq.b(), "crash_logs"))};
    }

    public static Object a(Object obj, Object obj2) {
        return obj != null ? obj : obj2;
    }
}
