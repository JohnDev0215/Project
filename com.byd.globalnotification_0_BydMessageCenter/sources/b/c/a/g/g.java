package b.c.a.g;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f1765b = new g();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f1766a;

    public g() {
        d eVar;
        if (b.f1762a == 0) {
            eVar = new f();
        } else {
            if (b.f1762a == 2) {
                eVar = new h();
            } else {
                if (b.f1762a != 1) {
                }
                eVar = new e();
            }
        }
        this.f1766a = eVar;
    }

    public int a(Context context, int i) {
        return this.f1766a.a(context, i);
    }

    public int b(Context context, int i) {
        return this.f1766a.d(context, i);
    }
}
