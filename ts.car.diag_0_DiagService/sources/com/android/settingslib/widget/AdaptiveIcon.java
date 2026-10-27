package com.android.settingslib.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class AdaptiveIcon extends LayerDrawable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AdaptiveConstantState f1381c;

    @VisibleForTesting(otherwise = 5)
    int mBackgroundColor;

    /* JADX INFO: compiled from: caradapter-bug */
    @VisibleForTesting
    static class AdaptiveConstantState extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Context f1382a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Drawable f1383b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1384c;

        AdaptiveConstantState(Context context, Drawable drawable) {
            this.f1382a = context;
            this.f1383b = drawable;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            AdaptiveIcon adaptiveIcon = new AdaptiveIcon(this.f1382a, this.f1383b);
            adaptiveIcon.a(this.f1384c);
            return adaptiveIcon;
        }
    }

    public AdaptiveIcon(Context context, Drawable drawable) {
        super(new Drawable[]{new b(context.getResources()), drawable});
        this.mBackgroundColor = -1;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.dashboard_tile_foreground_image_inset);
        setLayerInset(1, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        this.f1381c = new AdaptiveConstantState(context, drawable);
    }

    public void a(int i) {
        this.mBackgroundColor = i;
        getDrawable(0).setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        Log.d("AdaptiveHomepageIcon", "Setting background color " + this.mBackgroundColor);
        this.f1381c.f1384c = i;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f1381c;
    }
}
