package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class R8 implements Runnable {
    public final /* synthetic */ V8 s;
    public final /* synthetic */ T8 t;

    public R8(T8 t8, V8 v8) {
        this.t = t8;
        this.s = v8;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.i(this.s);
    }
}
