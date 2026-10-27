package org.chromium.chrome.browser.feed;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import defpackage.a8;
import defpackage.bq;
import defpackage.iv;
import defpackage.on3;
import defpackage.qh3;
import defpackage.sh;
import defpackage.yp;
import java.util.ArrayList;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes.dex */
public class FeedPlaceholderLayout extends LinearLayout {
    public static final sh q = new sh("FeedLoadingPlaceholder", "enable-animation-on-instant-start", false);
    public static final PathInterpolator r = new PathInterpolator(0.17f, 0.17f, 0.85f, 1.0f);
    public static final PathInterpolator s = new PathInterpolator(0.33f, 0.0f, 0.83f, 0.83f);
    public static final PathInterpolator t = new PathInterpolator(0.17f, 0.17f, 0.0f, 1.0f);
    public final Context e;
    public final Resources f;
    public long g;
    public final int h;
    public boolean i;
    public qh3 j;
    public final ArrayList k;
    public final ArrayList l;
    public AnimatorSet m;
    public final AnimatorSet n;
    public final AnimatorSet o;
    public boolean p;

    public FeedPlaceholderLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.m = new AnimatorSet();
        this.n = new AnimatorSet();
        this.o = new AnimatorSet();
        this.e = context;
        Resources resources = context.getResources();
        this.f = resources;
        this.h = resources.getConfiguration().screenWidthDp;
    }

    private LayerDrawable getLargeImageDrawable() {
        return new LayerDrawable(c(1, b(this.h), b(207)));
    }

    private LayerDrawable getSmallImageDrawable() {
        int iB = b(92);
        int iB2 = b(15);
        LayerDrawable layerDrawable = new LayerDrawable(c(1, iB, iB));
        layerDrawable.setLayerInset(0, 0, iB2, 0, this.i ? b(48) : b(72));
        return layerDrawable;
    }

    public final View a(AppCompatImageView appCompatImageView, int i) {
        if (!iv.e().g("disable-feed-placeholder-animation") && e()) {
            appCompatImageView.setAlpha(0.0f);
            appCompatImageView.setVisibility(0);
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(appCompatImageView, "alpha", 0.0f, 1.0f);
            long j = i;
            objectAnimatorOfFloat.setStartDelay(j);
            objectAnimatorOfFloat.setDuration(620L);
            objectAnimatorOfFloat.setInterpolator(r);
            ArrayList arrayList = this.k;
            arrayList.add(objectAnimatorOfFloat);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(appCompatImageView, "translationY", b(33), 0.0f);
            objectAnimatorOfFloat2.setDuration(1283L);
            objectAnimatorOfFloat2.setInterpolator(t);
            arrayList.add(objectAnimatorOfFloat2);
            ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(appCompatImageView, "alpha", 1.0f, 0.6f);
            objectAnimatorOfFloat3.setStartDelay(j);
            objectAnimatorOfFloat3.setDuration(620L);
            objectAnimatorOfFloat3.setInterpolator(s);
            objectAnimatorOfFloat3.setRepeatCount(-1);
            objectAnimatorOfFloat3.setRepeatMode(2);
            this.l.add(objectAnimatorOfFloat3);
        }
        return appCompatImageView;
    }

    public final int b(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public final GradientDrawable[] c(int i, int i2, int i3) {
        GradientDrawable[] gradientDrawableArr = new GradientDrawable[i];
        int iB = b(12);
        for (int i4 = 0; i4 < i; i4++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawableArr[i4] = gradientDrawable;
            gradientDrawable.setShape(0);
            gradientDrawableArr[i4].setSize(i2, i3);
            gradientDrawableArr[i4].setCornerRadius(iB);
            gradientDrawableArr[i4].setColor(yp.c(this.e, R$dimen.default_elevation_1));
        }
        return gradientDrawableArr;
    }

    public final int d(LinearLayout linearLayout, boolean z, LinearLayout.LayoutParams layoutParams, int i) {
        LayerDrawable layerDrawable;
        View view;
        Context context = this.e;
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setOrientation(!z ? 1 : 0);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        AppCompatImageView appCompatImageView = new AppCompatImageView(context);
        appCompatImageView.setImageDrawable(z ? getSmallImageDrawable() : getLargeImageDrawable());
        appCompatImageView.setLayoutParams(layoutParams2);
        appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int iB = b(15);
        int i2 = iB / 2;
        int iB2 = b(20);
        int iB3 = b(this.h);
        int iB4 = b(80);
        ViewGroup.LayoutParams layoutParams3 = z ? new LinearLayout.LayoutParams(0, -1, 1.0f) : new LinearLayout.LayoutParams(-2, -2);
        if (z) {
            GradientDrawable[] gradientDrawableArrC = c(4, iB3, iB2);
            int iB5 = b(15) + b(92);
            int iB6 = this.i ? b(48) : b(72);
            layerDrawable = new LayerDrawable(gradientDrawableArrC);
            int i3 = (iB5 + iB6) - iB;
            int i4 = i3 - iB2;
            layerDrawable.setLayerInset(0, 0, iB, iB, i4);
            layerDrawable.setLayerInset(1, 0, ((iB4 - iB2) / 2) + iB, iB, i3 - ((iB2 + iB4) / 2));
            layerDrawable.setLayerInset(2, 0, (iB + iB4) - iB2, iB, i3 - iB4);
            layerDrawable.setLayerInset(3, 0, i4, iB * 7, iB);
        } else {
            int i5 = (iB * 2) + iB4;
            LayerDrawable layerDrawable2 = new LayerDrawable(c(3, iB3, iB2));
            int i6 = (i5 - iB) - iB2;
            layerDrawable2.setLayerInset(0, i2, iB, iB, i6);
            int i7 = (i5 - iB2) / 2;
            layerDrawable2.setLayerInset(1, i2, i7, iB, i7);
            layerDrawable2.setLayerInset(2, i2, i6, iB, iB);
            layerDrawable = layerDrawable2;
        }
        AppCompatImageView appCompatImageView2 = new AppCompatImageView(context);
        appCompatImageView2.setImageDrawable(layerDrawable);
        appCompatImageView2.setLayoutParams(layoutParams3);
        appCompatImageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        if (z) {
            a(appCompatImageView2, i);
            view = appCompatImageView2;
        } else {
            a(appCompatImageView, i);
            view = appCompatImageView;
        }
        linearLayout2.addView(view);
        int i8 = i + 83;
        if (z) {
            a(appCompatImageView, i8);
        } else {
            a(appCompatImageView2, i8);
            appCompatImageView = appCompatImageView2;
        }
        linearLayout2.addView(appCompatImageView);
        int i9 = i8 + 83;
        linearLayout.addView(linearLayout2);
        return i9;
    }

    public final boolean e() {
        boolean z = this.p;
        return !z || (z && bq.z.a() && q.c());
    }

    public final void f(boolean z) {
        if (this.m == null) {
            return;
        }
        boolean z2 = isShown() && z;
        if (this.m.isStarted() && !z2) {
            this.m.cancel();
        } else if (!this.m.isStarted() && z2 && e()) {
            this.m.start();
        }
    }

    public long getLayoutInflationCompleteMs() {
        return this.g;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        f(true);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.j.c();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        f(false);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.j = new qh3(this);
        boolean z = !a8.Q();
        this.p = z;
        if (z) {
            int i = R$dimen.content_suggestions_card_modern_margin;
            Resources resources = this.f;
            new on3(this, this.j, resources.getDimensionPixelSize(i), resources.getDimensionPixelSize(R$dimen.ntp_wide_card_lateral_margins)).a();
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.placeholders_layout);
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = b(12);
        this.i = getResources().getConfiguration().orientation == 2;
        d(linearLayout, false, layoutParams, d(linearLayout, false, layoutParams, d(linearLayout, true, layoutParams, 0)));
        AnimatorSet animatorSet = this.n;
        animatorSet.setStartDelay(0L);
        animatorSet.playTogether(this.k);
        ArrayList arrayList = this.l;
        AnimatorSet animatorSet2 = this.o;
        animatorSet2.playTogether(arrayList);
        if (!this.p) {
            this.m.play(animatorSet).before(animatorSet2);
        } else if (e()) {
            this.m.playSequentially(animatorSet2);
        }
        this.g = SystemClock.elapsedRealtime();
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        f(isAttachedToWindow());
    }

    public void setAnimatorSetForTesting(AnimatorSet animatorSet) {
        this.m = animatorSet;
    }

    public void setBlankHeaderHeight(int i) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.feed_placeholder_header);
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.height = i;
        linearLayout.setLayoutParams(layoutParams);
    }
}
