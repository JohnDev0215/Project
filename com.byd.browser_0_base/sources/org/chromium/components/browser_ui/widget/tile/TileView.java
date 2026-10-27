package org.chromium.components.browser_ui.widget.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.sc2;
import org.chromium.components.browser_ui.widget.R$id;

/* JADX INFO: loaded from: classes2.dex */
public class TileView extends FrameLayout {
    public ImageView e;
    public TextView f;
    public Runnable g;
    public ImageView h;
    public sc2 i;

    public TileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Deprecated
    public ImageView getIconView() {
        return this.h;
    }

    public int getRoundingRadiusForTesting() {
        return this.i.a;
    }

    public TextView getTitleView() {
        return this.f;
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return super.isFocused() || (isSelected() && !isInTouchMode());
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.h = (ImageView) findViewById(R$id.tile_view_icon);
        this.e = (ImageView) findViewById(R$id.offline_badge);
        this.f = (TextView) findViewById(R$id.tile_view_title);
        sc2 sc2Var = new sc2(0);
        this.i = sc2Var;
        this.h.setOutlineProvider(sc2Var);
        this.h.setClipToOutline(true);
    }

    public void setIconDrawable(Drawable drawable) {
        this.h.setImageDrawable(drawable);
    }

    public void setIconTint(ColorStateList colorStateList) {
        this.h.setImageTintList(colorStateList);
    }

    public void setOfflineBadgeVisibility(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void setOnFocusViaSelectionListener(Runnable runnable) {
        this.g = runnable;
    }

    public void setRoundingRadius(int i) {
        this.i.a = i;
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        Runnable runnable;
        super.setSelected(z);
        if (!z || (runnable = this.g) == null) {
            return;
        }
        runnable.run();
    }
}
