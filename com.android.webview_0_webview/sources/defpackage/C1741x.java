package defpackage;

import android.graphics.drawable.Animatable;

/* JADX INFO: renamed from: x, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1741x extends C {
    public final Animatable a;

    public C1741x(Animatable animatable) {
        super(null);
        this.a = animatable;
    }

    @Override // defpackage.C
    public void c() {
        this.a.start();
    }

    @Override // defpackage.C
    public void d() {
        this.a.stop();
    }
}
