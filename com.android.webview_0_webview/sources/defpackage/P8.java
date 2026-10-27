package defpackage;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class P8 implements Callable {
    public final /* synthetic */ T8 a;

    public P8(T8 t8) {
        this.a = t8;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.a.c();
    }
}
