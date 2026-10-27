package org.chromium.chrome.browser.gesturenav;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.ii2;
import defpackage.yp;
import defpackage.z7;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.styles.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class NavigationBubble extends LinearLayout {
    public final ValueAnimator e;
    public final int f;
    public final int g;
    public final String h;
    public final String i;
    public final a j;
    public TextView k;
    public ImageView l;
    public Animation.AnimationListener m;
    public boolean n;
    public int o;

    public class a implements ValueAnimator.AnimatorUpdateListener {
        public int e;
        public int f;

        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            NavigationBubble.this.l.setImageTintList(ColorStateList.valueOf(z7.o(this.e, this.f, ((Float) valueAnimator.getAnimatedValue()).floatValue(), false)));
        }
    }

    public NavigationBubble(Context context) {
        this(context, null);
    }

    private void setCloseIndicator(int i) {
        if (this.o == i) {
            return;
        }
        this.o = i;
        getTextView().setText(i == 2 ? this.h : this.i);
    }

    public final void a() {
        if (this.n) {
            animate().alpha(1.0f).setDuration(0L);
            this.n = false;
        }
    }

    public final void b(int i) {
        if (i != 0) {
            if (!(getTextView().getVisibility() == 0)) {
                setCloseIndicator(i);
                getTextView().setVisibility(0);
                measure(0, 0);
                return;
            }
        }
        if (i == 0) {
            if (getTextView().getVisibility() == 0) {
                getTextView().setVisibility(8);
            }
        }
    }

    public TextView getTextView() {
        return this.k;
    }

    @Override // android.view.View
    public final void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.m;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public final void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.m;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.l = (ImageView) findViewById(R$id.navigation_bubble_arrow);
        this.k = (TextView) findViewById(R$id.navigation_bubble_text);
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.m = animationListener;
    }

    public void setIcon(int i) {
        this.l.setVisibility(0);
        this.l.setImageResource(i);
        setImageTint(false);
    }

    public void setImageTint(boolean z) {
        int i = this.g;
        int i2 = this.f;
        int i3 = z ? i : i2;
        if (z) {
            i = i2;
        }
        a aVar = this.j;
        aVar.e = i3;
        aVar.f = i;
        this.e.start();
    }

    public NavigationBubble(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = getContext().getColor(R$color.navigation_bubble_arrow);
        this.f = ii2.c(getContext());
        a aVar = new a();
        this.j = aVar;
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(250L);
        this.e = duration;
        duration.addUpdateListener(aVar);
        getBackground().setColorFilter(yp.c(context, R$dimen.default_elevation_2), PorterDuff.Mode.MULTIPLY);
        this.h = getResources().getString(R$string.overscroll_navigation_close_chrome, getContext().getString(R$string.app_name));
        this.i = getResources().getString(R$string.overscroll_navigation_close_tab);
        this.o = 0;
    }
}
