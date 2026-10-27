package defpackage;

import android.graphics.Bitmap;
import org.chromium.components.browser_ui.media.R$drawable;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: compiled from: MediaSessionHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ge1 implements LargeIconBridge.LargeIconCallback {
    public final /* synthetic */ de1 e;

    public ge1(de1 de1Var) {
        this.e = de1Var;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        de1 de1Var = this.e;
        if (de1Var.g()) {
            return;
        }
        if (bitmap != null) {
            de1Var.j(bitmap);
            return;
        }
        de1Var.k.h = R$drawable.audio_playing_square;
        de1Var.i();
    }
}
