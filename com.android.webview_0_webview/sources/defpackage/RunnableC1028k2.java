package defpackage;

import android.graphics.Typeface;

/* JADX INFO: renamed from: k2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1028k2 implements Runnable {
    public final /* synthetic */ Typeface s;
    public final /* synthetic */ AbstractC1140m2 t;

    public RunnableC1028k2(AbstractC1140m2 abstractC1140m2, Typeface typeface) {
        this.t = abstractC1140m2;
        this.s = typeface;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.c(this.s);
    }
}
