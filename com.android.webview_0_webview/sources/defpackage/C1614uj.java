package defpackage;

import java.util.Objects;

/* JADX INFO: renamed from: uj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1614uj {
    public final C0027Bj a = new C0027Bj();

    public void a(Exception exc) {
        C0027Bj c0027Bj = this.a;
        Objects.requireNonNull(c0027Bj);
        Cif.g(exc, "Exception must not be null");
        synchronized (c0027Bj.a) {
            Cif.i(!c0027Bj.c, "Task is already complete");
            c0027Bj.c = true;
            c0027Bj.e = exc;
        }
        c0027Bj.b.a(c0027Bj);
    }

    public void b(Object obj) {
        C0027Bj c0027Bj = this.a;
        synchronized (c0027Bj.a) {
            Cif.i(!c0027Bj.c, "Task is already complete");
            c0027Bj.c = true;
            c0027Bj.d = obj;
        }
        c0027Bj.b.a(c0027Bj);
    }

    public boolean c(Exception exc) {
        C0027Bj c0027Bj = this.a;
        Objects.requireNonNull(c0027Bj);
        Cif.g(exc, "Exception must not be null");
        synchronized (c0027Bj.a) {
            if (c0027Bj.c) {
                return false;
            }
            c0027Bj.c = true;
            c0027Bj.e = exc;
            c0027Bj.b.a(c0027Bj);
            return true;
        }
    }
}
