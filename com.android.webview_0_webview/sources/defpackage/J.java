package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class J extends H {
    public I E;
    public boolean F;

    public J(I i) {
    }

    @Override // defpackage.H, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // defpackage.H
    public void e(G g) {
        super.e(g);
        if (g instanceof I) {
            this.E = (I) g;
        }
    }

    @Override // defpackage.H, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.F) {
            super.mutate();
            if (this == this) {
                this.E.e();
                this.F = true;
            }
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public abstract boolean onStateChange(int[] iArr);
}
