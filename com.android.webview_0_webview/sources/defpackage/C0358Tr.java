package defpackage;

import J.N;
import android.graphics.Canvas;
import android.graphics.Picture;

/* JADX INFO: renamed from: Tr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0358Tr extends Picture {
    public long a;

    public C0358Tr(long j) {
        this.a = j;
        new C0248Np(this, new RunnableC0340Sr(j, null));
    }

    @Override // android.graphics.Picture
    public Canvas beginRecording(int i, int i2) {
        throw new IllegalStateException("Unsupported in AwPicture");
    }

    @Override // android.graphics.Picture
    public void draw(Canvas canvas) {
        N.MU5yPjvq(this.a, this, canvas);
    }

    @Override // android.graphics.Picture
    public void endRecording() {
    }

    @Override // android.graphics.Picture
    public int getHeight() {
        return N.MAAuagRx(this.a, this);
    }

    @Override // android.graphics.Picture
    public int getWidth() {
        return N.MxvjfQIM(this.a, this);
    }
}
