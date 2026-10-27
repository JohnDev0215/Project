package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class G2 extends E2 {
    public G2(E2 e2, Resources resources) {
        super(e2);
    }

    @Override // defpackage.E2, android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        return new H2(this, resources);
    }
}
