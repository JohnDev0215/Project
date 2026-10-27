package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class F7 extends Drawable.ConstantState {
    public final Drawable.ConstantState a;

    public F7(Drawable.ConstantState constantState) {
        this.a = constantState;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public boolean canApplyTheme() {
        return this.a.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.a.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        G7 g7 = new G7(null, null, null);
        Drawable drawableNewDrawable = this.a.newDrawable();
        g7.s = drawableNewDrawable;
        drawableNewDrawable.setCallback(g7.w);
        return g7;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        G7 g7 = new G7(null, null, null);
        Drawable drawableNewDrawable = this.a.newDrawable(resources);
        g7.s = drawableNewDrawable;
        drawableNewDrawable.setCallback(g7.w);
        return g7;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources, Resources.Theme theme) {
        G7 g7 = new G7(null, null, null);
        Drawable drawableNewDrawable = this.a.newDrawable(resources, theme);
        g7.s = drawableNewDrawable;
        drawableNewDrawable.setCallback(g7.w);
        return g7;
    }
}
