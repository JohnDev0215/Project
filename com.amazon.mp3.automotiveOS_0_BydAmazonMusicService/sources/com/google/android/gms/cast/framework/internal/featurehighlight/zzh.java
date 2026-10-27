package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.internal.cast.zzcy;
import com.google.android.gms.internal.cast.zzdh;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzh extends ViewGroup {
    private final int[] zza;
    private final Rect zzb;
    private final Rect zzc;
    private final OuterHighlightDrawable zzd;
    private final InnerZoneDrawable zze;
    private View zzf;

    @Nullable
    private Animator zzg;
    private final zzi zzh;
    private final GestureDetectorCompat zzi;

    @Nullable
    private GestureDetectorCompat zzj;
    private zzg zzk;
    private boolean zzl;
    private HelpTextView zzm;

    public zzh(Context context) {
        super(context);
        this.zza = new int[2];
        this.zzb = new Rect();
        this.zzc = new Rect();
        setId(R.id.cast_featurehighlight_view);
        setWillNotDraw(false);
        InnerZoneDrawable innerZoneDrawable = new InnerZoneDrawable(context);
        this.zze = innerZoneDrawable;
        innerZoneDrawable.setCallback(this);
        OuterHighlightDrawable outerHighlightDrawable = new OuterHighlightDrawable(context);
        this.zzd = outerHighlightDrawable;
        outerHighlightDrawable.setCallback(this);
        this.zzh = new zzi(this);
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(context, new zza(this));
        this.zzi = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
        setVisibility(8);
    }

    static /* synthetic */ Animator zzb(zzh zzhVar) {
        InnerZoneDrawable innerZoneDrawable = zzhVar.zze;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator duration = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", 1.0f, 1.1f).setDuration(500L);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", 1.1f, 1.0f).setDuration(500L);
        ObjectAnimator duration3 = ObjectAnimator.ofPropertyValuesHolder(innerZoneDrawable, PropertyValuesHolder.ofFloat("pulseScale", 1.1f, 2.0f), PropertyValuesHolder.ofFloat("pulseAlpha", 1.0f, 0.0f)).setDuration(500L);
        animatorSet.play(duration);
        animatorSet.play(duration2).with(duration3).after(duration);
        animatorSet.setInterpolator(zzdh.zzb());
        animatorSet.setStartDelay(500L);
        zzcy.zzd(animatorSet, -1, null);
        return animatorSet;
    }

    private final void zzq(Animator animator) {
        Animator animator2 = this.zzg;
        if (animator2 != null) {
            animator2.cancel();
        }
        this.zzg = animator;
        animator.start();
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        canvas.save();
        this.zzd.draw(canvas);
        this.zze.draw(canvas);
        View view = this.zzf;
        if (view == null) {
            throw new IllegalStateException("Neither target view nor drawable was set");
        }
        if (view.getParent() != null) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.zzf.getWidth(), this.zzf.getHeight(), Bitmap.Config.ARGB_8888);
            this.zzf.draw(new Canvas(bitmapCreateBitmap));
            int iZzc = this.zzd.zzc();
            int iRed = Color.red(iZzc);
            int iGreen = Color.green(iZzc);
            int iBlue = Color.blue(iZzc);
            for (int i = 0; i < bitmapCreateBitmap.getHeight(); i++) {
                for (int i2 = 0; i2 < bitmapCreateBitmap.getWidth(); i2++) {
                    int pixel = bitmapCreateBitmap.getPixel(i2, i);
                    if (Color.alpha(pixel) != 0) {
                        bitmapCreateBitmap.setPixel(i2, i, Color.argb(Color.alpha(pixel), iRed, iGreen, iBlue));
                    }
                }
            }
            Rect rect = this.zzb;
            canvas.drawBitmap(bitmapCreateBitmap, rect.left, rect.top, (Paint) null);
        }
        canvas.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.zzf;
        if (view == null) {
            throw new IllegalStateException("Target view must be set before layout");
        }
        if (view.getParent() != null) {
            int[] iArr = this.zza;
            View view2 = this.zzf;
            getLocationInWindow(iArr);
            int i5 = iArr[0];
            int i6 = iArr[1];
            view2.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i5;
            iArr[1] = iArr[1] - i6;
        }
        Rect rect = this.zzb;
        int[] iArr2 = this.zza;
        int i7 = iArr2[0];
        rect.set(i7, iArr2[1], this.zzf.getWidth() + i7, this.zza[1] + this.zzf.getHeight());
        this.zzc.set(i, i2, i3, i4);
        this.zzd.setBounds(this.zzc);
        this.zze.setBounds(this.zzc);
        this.zzh.zza(this.zzb, this.zzc);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        setMeasuredDimension(View.resolveSize(View.MeasureSpec.getSize(i), i), View.resolveSize(View.MeasureSpec.getSize(i2), i2));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.zzl = this.zzb.contains((int) motionEvent.getX(), (int) motionEvent.getY());
            actionMasked = 0;
        }
        if (this.zzl) {
            GestureDetectorCompat gestureDetectorCompat = this.zzj;
            if (gestureDetectorCompat != null) {
                gestureDetectorCompat.onTouchEvent(motionEvent);
                if (actionMasked == 1) {
                    motionEvent = MotionEvent.obtain(motionEvent);
                    motionEvent.setAction(3);
                }
            }
            if (this.zzf.getParent() != null) {
                this.zzf.onTouchEvent(motionEvent);
            }
        } else {
            this.zzi.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    protected final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.zzd || drawable == this.zze || drawable == null;
    }

    final View zzc() {
        return this.zzm.asView();
    }

    final InnerZoneDrawable zze() {
        return this.zze;
    }

    final OuterHighlightDrawable zzg() {
        return this.zzd;
    }

    public final void zzi(@Nullable Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", 0.0f).setDuration(200L);
        duration.setInterpolator(zzdh.zza());
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzd, PropertyValuesHolder.ofFloat("scale", 1.125f), PropertyValuesHolder.ofInt("alpha", 0));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzdh.zza());
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(200L);
        Animator animatorZza = this.zze.zza();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, duration2, animatorZza);
        animatorSet.addListener(new zze(this, runnable));
        zzq(animatorSet);
    }

    public final void zzj(@Nullable Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", 0.0f).setDuration(200L);
        duration.setInterpolator(zzdh.zza());
        float fExactCenterX = this.zzb.exactCenterX();
        float fZza = this.zzd.zza();
        float fExactCenterY = this.zzb.exactCenterY();
        float fZzb = this.zzd.zzb();
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzd, PropertyValuesHolder.ofFloat("scale", 0.0f), PropertyValuesHolder.ofFloat("translationX", 0.0f, fExactCenterX - fZza), PropertyValuesHolder.ofFloat("translationY", 0.0f, fExactCenterY - fZzb), PropertyValuesHolder.ofInt("alpha", 0));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzdh.zza());
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(200L);
        Animator animatorZza = this.zze.zza();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, duration2, animatorZza);
        animatorSet.addListener(new zzf(this, runnable));
        zzq(animatorSet);
    }

    public final void zzl(@ColorInt int i) {
        this.zzd.zze(i);
    }

    public final void zzm() {
        if (this.zzf == null) {
            throw new IllegalStateException("Target view must be set before animation");
        }
        setVisibility(0);
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", 0.0f, 1.0f).setDuration(350L);
        duration.setInterpolator(zzdh.zzc());
        Animator animatorZzd = this.zzd.zzd(this.zzb.exactCenterX() - this.zzd.zza(), this.zzb.exactCenterY() - this.zzd.zzb());
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zze, PropertyValuesHolder.ofFloat("scale", 0.0f, 1.0f), PropertyValuesHolder.ofInt("alpha", 0, 255));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(zzdh.zzc());
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(350L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, animatorZzd, duration2);
        animatorSet.addListener(new zzd(this));
        zzq(animatorSet);
    }

    public final void zzn(@Nullable Runnable runnable) {
        addOnLayoutChangeListener(new zzc(this, null));
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    public final void zzp(HelpTextView helpTextView) {
        helpTextView.getClass();
        this.zzm = helpTextView;
        addView(helpTextView.asView(), 0);
    }

    public final void zzk(View view, @Nullable View view2, boolean z, zzg zzgVar) {
        view.getClass();
        this.zzf = view;
        this.zzk = zzgVar;
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(getContext(), new zzb(this, view, true, zzgVar));
        this.zzj = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
        setVisibility(4);
    }
}
