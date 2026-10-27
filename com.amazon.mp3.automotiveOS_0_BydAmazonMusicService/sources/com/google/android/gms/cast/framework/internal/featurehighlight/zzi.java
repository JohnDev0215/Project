package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.cast.framework.R;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzi {
    private final Rect zza = new Rect();
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final zzh zzf;

    zzi(zzh zzhVar) {
        this.zzf = zzhVar;
        Resources resources = zzhVar.getResources();
        this.zzb = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zzc = resources.getDimensionPixelOffset(R.dimen.cast_libraries_material_featurehighlight_inner_margin);
        this.zzd = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_max_width);
        this.zze = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_horizontal_offset);
    }

    private final int zzb(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = i3 / 2;
        int i6 = i4 - i <= i2 - i4 ? (i4 - i5) + this.zze : (i4 - i5) - this.zze;
        int i7 = marginLayoutParams.leftMargin;
        if (i6 - i7 < i) {
            return i + i7;
        }
        int i8 = marginLayoutParams.rightMargin;
        return (i6 + i3) + i8 > i2 ? (i2 - i3) - i8 : i6;
    }

    private final void zzc(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(Math.min((i - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, this.zzd), 1073741824), View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE));
    }

    final void zza(Rect rect, Rect rect2) {
        View viewZzc = this.zzf.zzc();
        if (rect.isEmpty() || rect2.isEmpty()) {
            viewZzc.layout(0, 0, 0, 0);
        } else {
            int iCenterY = rect.centerY();
            int iCenterX = rect.centerX();
            int iCenterY2 = rect2.centerY();
            int iHeight = rect.height();
            int i = this.zzb;
            int iMax = Math.max(i + i, iHeight) / 2;
            int i2 = this.zzc;
            int i3 = iCenterY + iMax + i2;
            if (iCenterY < iCenterY2) {
                zzc(viewZzc, rect2.width(), rect2.bottom - i3);
                int iZzb = zzb(viewZzc, rect2.left, rect2.right, viewZzc.getMeasuredWidth(), iCenterX);
                viewZzc.layout(iZzb, i3, viewZzc.getMeasuredWidth() + iZzb, viewZzc.getMeasuredHeight() + i3);
            } else {
                int i4 = (iCenterY - iMax) - i2;
                zzc(viewZzc, rect2.width(), i4 - rect2.top);
                int iZzb2 = zzb(viewZzc, rect2.left, rect2.right, viewZzc.getMeasuredWidth(), iCenterX);
                viewZzc.layout(iZzb2, i4 - viewZzc.getMeasuredHeight(), viewZzc.getMeasuredWidth() + iZzb2, i4);
            }
        }
        this.zza.set(viewZzc.getLeft(), viewZzc.getTop(), viewZzc.getRight(), viewZzc.getBottom());
        this.zzf.zzg().zzf(rect, this.zza);
        this.zzf.zze().zzb(rect);
    }
}
