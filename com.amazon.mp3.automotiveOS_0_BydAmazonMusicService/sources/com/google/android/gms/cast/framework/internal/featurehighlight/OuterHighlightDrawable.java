package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.R;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzdh;
import com.google.android.gms.internal.cast.zzdi;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
class OuterHighlightDrawable extends Drawable {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final Rect zzd = new Rect();
    private final Rect zze = new Rect();
    private final Paint zzf;
    private float zzg;
    private float zzh;
    private float zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private int zzm;

    public OuterHighlightDrawable(Context context) {
        int color;
        Paint paint = new Paint();
        this.zzf = paint;
        this.zzh = 1.0f;
        this.zzk = 0.0f;
        this.zzl = 0.0f;
        this.zzm = 244;
        if (PlatformVersion.isAtLeastLollipop()) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            color = ColorUtils.setAlphaComponent(typedValue.data, 244);
        } else {
            color = context.getResources().getColor(com.google.android.gms.cast.framework.R.color.cast_libraries_material_featurehighlight_outer_highlight_default_color);
        }
        paint.setColor(color);
        this.zzm = paint.getAlpha();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Resources resources = context.getResources();
        this.zza = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_center_threshold);
        this.zzb = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_center_horizontal_offset);
        this.zzc = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_outer_padding);
    }

    private static final float zzh(float f, float f2, Rect rect) {
        float f3 = rect.left;
        float f4 = rect.top;
        float f5 = rect.right;
        float f6 = rect.bottom;
        float fZza = zzdi.zza(f, f2, f3, f4);
        float fZza2 = zzdi.zza(f, f2, f5, f4);
        float fZza3 = zzdi.zza(f, f2, f5, f6);
        float fZza4 = zzdi.zza(f, f2, f3, f6);
        if (fZza <= fZza2 || fZza <= fZza3 || fZza <= fZza4) {
            fZza = (fZza2 <= fZza3 || fZza2 <= fZza4) ? fZza3 > fZza4 ? fZza3 : fZza4 : fZza2;
        }
        return (float) Math.ceil(fZza);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.drawCircle(this.zzi + this.zzk, this.zzj + this.zzl, this.zzg * this.zzh, this.zzf);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.zzf.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.zzf.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.zzf.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Keep
    public void setScale(float f) {
        this.zzh = f;
        invalidateSelf();
    }

    @Keep
    public void setTranslationX(float f) {
        this.zzk = f;
        invalidateSelf();
    }

    @Keep
    public void setTranslationY(float f) {
        this.zzl = f;
        invalidateSelf();
    }

    public final float zza() {
        return this.zzi;
    }

    public final float zzb() {
        return this.zzj;
    }

    @ColorInt
    public final int zzc() {
        return this.zzf.getColor();
    }

    public final Animator zzd(float f, float f2) {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("scale", 0.0f, 1.0f), PropertyValuesHolder.ofFloat("translationX", f, 0.0f), PropertyValuesHolder.ofFloat("translationY", f2, 0.0f), PropertyValuesHolder.ofInt("alpha", 0, this.zzm));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzdh.zzc());
        return objectAnimatorOfPropertyValuesHolder.setDuration(350L);
    }

    public final void zze(@ColorInt int i) {
        this.zzf.setColor(i);
        this.zzm = this.zzf.getAlpha();
        invalidateSelf();
    }

    public final void zzf(Rect rect, Rect rect2) {
        this.zzd.set(rect);
        this.zze.set(rect2);
        float fExactCenterX = rect.exactCenterX();
        float fExactCenterY = rect.exactCenterY();
        Rect bounds = getBounds();
        if (Math.min(fExactCenterY - bounds.top, bounds.bottom - fExactCenterY) < this.zza) {
            this.zzi = fExactCenterX;
            this.zzj = fExactCenterY;
        } else {
            this.zzi = fExactCenterX <= bounds.exactCenterX() ? rect2.exactCenterX() + this.zzb : rect2.exactCenterX() - this.zzb;
            fExactCenterY = rect2.exactCenterY();
            this.zzj = fExactCenterY;
        }
        this.zzg = this.zzc + Math.max(zzh(this.zzi, fExactCenterY, rect), zzh(this.zzi, this.zzj, rect2));
        invalidateSelf();
    }

    public final boolean zzg(float f, float f2) {
        return zzdi.zza(f, f2, this.zzi, this.zzj) < this.zzg;
    }
}
