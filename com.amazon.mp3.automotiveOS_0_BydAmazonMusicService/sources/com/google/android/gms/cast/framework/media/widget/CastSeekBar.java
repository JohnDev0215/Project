package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class CastSeekBar extends View {

    @VisibleForTesting
    public zze zza;

    @Nullable
    public zzc zzb;

    @Nullable
    @VisibleForTesting
    public List zzc;

    @VisibleForTesting
    public zzd zzd;
    private boolean zze;

    @Nullable
    private Integer zzf;
    private final float zzg;
    private final float zzh;
    private final float zzi;
    private final float zzj;
    private final float zzk;
    private final Paint zzl;

    @ColorInt
    private final int zzm;

    @ColorInt
    private final int zzn;

    @ColorInt
    private final int zzo;

    @ColorInt
    private final int zzp;
    private int[] zzq;
    private Point zzr;
    private Runnable zzs;

    public CastSeekBar(@NonNull Context context) {
        this(context, null);
    }

    private final int zzf(int i) {
        return (int) ((((double) i) / ((double) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()))) * ((double) this.zza.zzb));
    }

    private final void zzg(@NonNull Canvas canvas, int i, int i2, int i3, int i4, int i5) {
        this.zzl.setColor(i5);
        float f = i3;
        float f2 = i4;
        float f3 = this.zzi;
        canvas.drawRect((i / f) * f2, -f3, (i2 / f) * f2, f3, this.zzl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzh(int i) {
        zze zzeVar = this.zza;
        if (zzeVar.zzf) {
            this.zzf = Integer.valueOf(CastUtils.zza(i, zzeVar.zzd, zzeVar.zze));
            zzd zzdVar = this.zzd;
            if (zzdVar != null) {
                zzdVar.zza(this, getProgress(), true);
            }
            Runnable runnable = this.zzs;
            if (runnable == null) {
                this.zzs = new Runnable() { // from class: com.google.android.gms.cast.framework.media.widget.zza
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zza.sendAccessibilityEvent(4);
                    }
                };
            } else {
                removeCallbacks(runnable);
            }
            postDelayed(this.zzs, 200L);
            postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzi() {
        this.zze = true;
        zzd zzdVar = this.zzd;
        if (zzdVar != null) {
            zzdVar.zzb(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzj() {
        this.zze = false;
        zzd zzdVar = this.zzd;
        if (zzdVar != null) {
            zzdVar.zzc(this);
        }
    }

    public int getMaxProgress() {
        return this.zza.zzb;
    }

    public int getProgress() {
        Integer num = this.zzf;
        return num != null ? num.intValue() : this.zza.zza;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        Runnable runnable = this.zzs;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(@NonNull Canvas canvas) {
        int iSave = canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        zzc zzcVar = this.zzb;
        if (zzcVar == null) {
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int measuredHeight = getMeasuredHeight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int progress = getProgress();
            int iSave2 = canvas.save();
            canvas.translate(0.0f, ((measuredHeight - paddingTop) - paddingBottom) / 2);
            zze zzeVar = this.zza;
            if (zzeVar.zzf) {
                int i = zzeVar.zzd;
                if (i > 0) {
                    zzg(canvas, 0, i, zzeVar.zzb, measuredWidth, this.zzo);
                }
                zze zzeVar2 = this.zza;
                int i2 = zzeVar2.zzd;
                if (progress > i2) {
                    zzg(canvas, i2, progress, zzeVar2.zzb, measuredWidth, this.zzm);
                }
                zze zzeVar3 = this.zza;
                int i3 = zzeVar3.zze;
                if (i3 > progress) {
                    zzg(canvas, progress, i3, zzeVar3.zzb, measuredWidth, this.zzn);
                }
                zze zzeVar4 = this.zza;
                int i4 = zzeVar4.zzb;
                int i5 = zzeVar4.zze;
                if (i4 > i5) {
                    zzg(canvas, i5, i4, i4, measuredWidth, this.zzo);
                }
            } else {
                int iMax = Math.max(zzeVar.zzc, 0);
                if (iMax > 0) {
                    zzg(canvas, 0, iMax, this.zza.zzb, measuredWidth, this.zzo);
                }
                if (progress > iMax) {
                    zzg(canvas, iMax, progress, this.zza.zzb, measuredWidth, this.zzm);
                }
                int i6 = this.zza.zzb;
                if (i6 > progress) {
                    zzg(canvas, progress, i6, i6, measuredWidth, this.zzo);
                }
            }
            canvas.restoreToCount(iSave2);
            List<zzb> list = this.zzc;
            if (list != null && !list.isEmpty()) {
                this.zzl.setColor(this.zzp);
                int measuredWidth2 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
                int measuredHeight2 = getMeasuredHeight();
                int paddingTop2 = getPaddingTop();
                int paddingBottom2 = getPaddingBottom();
                int iSave3 = canvas.save();
                canvas.translate(0.0f, ((measuredHeight2 - paddingTop2) - paddingBottom2) / 2);
                for (zzb zzbVar : list) {
                    if (zzbVar != null) {
                        int iMin = Math.min(zzbVar.zza, this.zza.zzb);
                        int i7 = zzbVar.zzc ? zzbVar.zzb : 1;
                        float f = measuredWidth2;
                        float f2 = this.zza.zzb;
                        float f3 = (iMin * f) / f2;
                        float f4 = ((iMin + i7) * f) / f2;
                        float f5 = this.zzk;
                        if (f4 - f3 < f5) {
                            f4 = f3 + f5;
                        }
                        float f6 = f4 > f ? f : f4;
                        float f7 = f6 - f3 < f5 ? f6 - f5 : f3;
                        float f8 = this.zzi;
                        canvas.drawRect(f7, -f8, f6, f8, this.zzl);
                    }
                }
                canvas.restoreToCount(iSave3);
            }
            if (isEnabled() && this.zza.zzf) {
                this.zzl.setColor(this.zzm);
                int measuredWidth3 = getMeasuredWidth();
                int paddingLeft = getPaddingLeft();
                int paddingRight = getPaddingRight();
                int measuredHeight3 = getMeasuredHeight();
                int paddingTop3 = getPaddingTop();
                int paddingBottom3 = getPaddingBottom();
                int progress2 = getProgress();
                int i8 = this.zza.zzb;
                int iSave4 = canvas.save();
                canvas.drawCircle((int) ((((double) progress2) / ((double) i8)) * ((double) ((measuredWidth3 - paddingLeft) - paddingRight))), ((measuredHeight3 - paddingTop3) - paddingBottom3) / 2.0f, this.zzj, this.zzl);
                canvas.restoreToCount(iSave4);
            }
        } else {
            int measuredWidth4 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int measuredHeight4 = getMeasuredHeight();
            int paddingTop4 = getPaddingTop();
            int paddingBottom4 = getPaddingBottom();
            int iSave5 = canvas.save();
            canvas.translate(0.0f, ((measuredHeight4 - paddingTop4) - paddingBottom4) / 2);
            zzg(canvas, 0, zzcVar.zza, zzcVar.zzb, measuredWidth4, this.zzp);
            int i9 = zzcVar.zza;
            int i10 = zzcVar.zzb;
            zzg(canvas, i9, i10, i10, measuredWidth4, this.zzo);
            canvas.restoreToCount(iSave5);
        }
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        setMeasuredDimension(View.resolveSizeAndState((int) (this.zzg + getPaddingLeft() + getPaddingRight()), i, 0), View.resolveSizeAndState((int) (this.zzh + getPaddingTop() + getPaddingBottom()), i2, 0));
    }

    @Override // android.view.View
    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (!isEnabled() || !this.zza.zzf) {
            return false;
        }
        if (this.zzr == null) {
            this.zzr = new Point();
        }
        if (this.zzq == null) {
            this.zzq = new int[2];
        }
        getLocationOnScreen(this.zzq);
        this.zzr.set((((int) motionEvent.getRawX()) - this.zzq[0]) - getPaddingLeft(), ((int) motionEvent.getRawY()) - this.zzq[1]);
        int action = motionEvent.getAction();
        if (action == 0) {
            zzi();
            zzh(zzf(this.zzr.x));
            return true;
        }
        if (action == 1) {
            zzh(zzf(this.zzr.x));
            zzj();
            return true;
        }
        if (action == 2) {
            zzh(zzf(this.zzr.x));
            return true;
        }
        if (action != 3) {
            return false;
        }
        this.zze = false;
        this.zzf = null;
        zzd zzdVar = this.zzd;
        if (zzdVar != null) {
            zzdVar.zza(this, getProgress(), true);
            this.zzd.zzc(this);
        }
        postInvalidate();
        return true;
    }

    public final void zzd(@Nullable List list) {
        if (Objects.equal(this.zzc, list)) {
            return;
        }
        this.zzc = list == null ? null : new ArrayList(list);
        postInvalidate();
    }

    public final void zze(@NonNull zze zzeVar) {
        if (this.zze) {
            return;
        }
        zze zzeVar2 = new zze();
        zzeVar2.zza = zzeVar.zza;
        zzeVar2.zzb = zzeVar.zzb;
        zzeVar2.zzc = zzeVar.zzc;
        zzeVar2.zzd = zzeVar.zzd;
        zzeVar2.zze = zzeVar.zze;
        zzeVar2.zzf = zzeVar.zzf;
        this.zza = zzeVar2;
        this.zzf = null;
        zzd zzdVar = this.zzd;
        if (zzdVar != null) {
            zzdVar.zza(this, getProgress(), false);
        }
        postInvalidate();
    }

    public CastSeekBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CastSeekBar(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzc = new ArrayList();
        setAccessibilityDelegate(new zzg(this, null));
        Paint paint = new Paint(1);
        this.zzl = paint;
        paint.setStyle(Paint.Style.FILL);
        this.zzg = context.getResources().getDimension(R.dimen.cast_seek_bar_minimum_width);
        this.zzh = context.getResources().getDimension(R.dimen.cast_seek_bar_minimum_height);
        this.zzi = context.getResources().getDimension(R.dimen.cast_seek_bar_progress_height) / 2.0f;
        this.zzj = context.getResources().getDimension(R.dimen.cast_seek_bar_thumb_size) / 2.0f;
        this.zzk = context.getResources().getDimension(R.dimen.cast_seek_bar_ad_break_minimum_width);
        zze zzeVar = new zze();
        this.zza = zzeVar;
        zzeVar.zzb = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastExpandedController_castSeekBarProgressAndThumbColor, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastExpandedController_castSeekBarSecondaryProgressColor, 0);
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastExpandedController_castSeekBarUnseekableProgressColor, 0);
        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastExpandedController_castAdBreakMarkerColor, 0);
        this.zzm = context.getResources().getColor(resourceId);
        this.zzn = context.getResources().getColor(resourceId2);
        this.zzo = context.getResources().getColor(resourceId3);
        this.zzp = context.getResources().getColor(resourceId4);
        typedArrayObtainStyledAttributes.recycle();
    }
}
