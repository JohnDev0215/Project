package org.chromium.chrome.browser.toolbar.menu_button;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import defpackage.c6;
import defpackage.ef1;
import defpackage.h21;
import defpackage.in3;
import defpackage.n3;
import defpackage.nw2;
import defpackage.om3;
import defpackage.p72;
import defpackage.pc;
import defpackage.ye1;
import defpackage.z6;
import defpackage.ze1;
import java.util.WeakHashMap;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.theme.ThemeColorProvider;

/* JADX INFO: loaded from: classes2.dex */
public class MenuButton extends FrameLayout implements ThemeColorProvider.b {
    public ImageButton e;
    public ImageView f;
    public int g;
    public z6 h;
    public boolean i;
    public p72 j;
    public Drawable k;
    public AnimatorSet l;
    public boolean m;
    public BitmapDrawable n;
    public BitmapDrawable o;
    public nw2<ef1> p;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            MenuButton.this.m = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            MenuButton.this.m = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            MenuButton.this.m = true;
        }
    }

    public class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            MenuButton.this.m = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            MenuButton.this.m = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            MenuButton.this.m = true;
        }
    }

    public MenuButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setUpdateBadgeVisibility(boolean z) {
        ImageView imageView = this.f;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(z ? 0 : 8);
        if (z) {
            c();
        }
    }

    public final void a(boolean z) {
        AnimatorSet animatorSet;
        ImageView imageView = this.f;
        if (imageView != null) {
            if (imageView != null && imageView.getVisibility() == 0) {
                if (!z) {
                    setUpdateBadgeVisibility(false);
                    return;
                }
                if (this.m && (animatorSet = this.l) != null) {
                    animatorSet.cancel();
                }
                this.e.setAlpha(0.0f);
                ImageButton imageButton = this.e;
                ImageView imageView2 = this.f;
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView2, (Property<ImageView, Float>) View.ALPHA, 0.0f);
                objectAnimatorOfFloat.setInterpolator(pc.d);
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(imageButton, (Property<ImageButton, Float>) View.ALPHA, 1.0f);
                objectAnimatorOfFloat2.setInterpolator(pc.e);
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
                animatorSet2.setDuration(200L);
                animatorSet2.addListener(new ze1(imageView2, imageButton));
                this.l = animatorSet2;
                animatorSet2.addListener(new b());
                this.l.start();
            }
        }
    }

    public final void b(boolean z) {
        if (this.f == null || this.e == null) {
            return;
        }
        c();
        if (!z || this.m) {
            setUpdateBadgeVisibility(true);
            return;
        }
        this.f.setAlpha(0.0f);
        this.f.setVisibility(0);
        ImageButton imageButton = this.e;
        ImageView imageView = this.f;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, (Property<ImageView, Float>) View.ALPHA, 1.0f);
        objectAnimatorOfFloat.setInterpolator(pc.e);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(imageView, (Property<ImageView, Float>) View.TRANSLATION_Y, imageView.getResources().getDimensionPixelSize(R$dimen.menu_badge_translation_y), 0.0f);
        objectAnimatorOfFloat2.setInterpolator(pc.c);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(imageButton, (Property<ImageButton, Float>) View.ALPHA, 0.0f);
        objectAnimatorOfFloat3.setInterpolator(h21.f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2, objectAnimatorOfFloat3);
        animatorSet.setDuration(350L);
        animatorSet.addListener(new ye1(imageButton, imageView));
        this.l = animatorSet;
        animatorSet.addListener(new a());
        this.l.start();
    }

    public final void c() {
        ImageView imageView;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.e.getDrawable().getConstantState().newDrawable().mutate();
        this.n = bitmapDrawable;
        bitmapDrawable.setBounds(this.e.getPaddingLeft(), this.e.getPaddingTop(), this.e.getWidth() - this.e.getPaddingRight(), this.e.getHeight() - this.e.getPaddingBottom());
        this.n.setGravity(17);
        this.n.setColorFilter(n3.y0(getContext()).getDefaultColor(), PorterDuff.Mode.SRC_IN);
        ef1 ef1Var = this.p.get();
        if (ef1Var == null || (imageView = this.f) == null) {
            return;
        }
        int i = this.g;
        int i2 = ef1Var.d;
        if (i == 1 || i == 2) {
            i2 = ef1Var.c;
        } else if (i == 0) {
            i2 = ef1Var.b;
        }
        imageView.setImageDrawable(c6.c(getResources(), i2, 0));
        BitmapDrawable bitmapDrawable2 = (BitmapDrawable) this.f.getDrawable().getConstantState().newDrawable().mutate();
        this.o = bitmapDrawable2;
        bitmapDrawable2.setBounds(this.f.getPaddingLeft(), this.f.getPaddingTop(), this.f.getWidth() - this.f.getPaddingRight(), this.f.getHeight() - this.f.getPaddingBottom());
        this.o.setGravity(17);
    }

    public final void d() {
        if (this.e == null) {
            return;
        }
        if (!this.i) {
            setBackground(this.k);
            return;
        }
        if (this.j == null) {
            p72 p72VarB = p72.b(getContext(), new p72.d());
            this.j = p72VarB;
            ImageButton imageButton = this.e;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            p72VarB.d(om3.e.e(imageButton), this.e.getPaddingTop(), om3.e.d(this.e), this.e.getPaddingBottom());
        }
        int i = this.g;
        boolean z = true;
        if (i != 1 && i != 2) {
            z = false;
        }
        this.j.e(getContext(), z);
        setBackground(this.j);
        this.j.start();
    }

    @Override // org.chromium.chrome.browser.theme.ThemeColorProvider.b
    public final void e(int i, ColorStateList colorStateList) {
        this.e.setImageTintList(colorStateList);
        this.g = i;
        c();
        d();
    }

    public int getBrandedColorSchemeForTesting() {
        return this.g;
    }

    public ImageButton getImageButton() {
        return this.e;
    }

    public Drawable getTabSwitcherAnimationDrawable() {
        if (this.o == null && this.n == null) {
            c();
        }
        ImageView imageView = this.f;
        return imageView != null && imageView.getVisibility() == 0 ? this.o : this.n;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (ImageButton) findViewById(R$id.menu_button);
        this.f = (ImageView) findViewById(R$id.menu_badge);
        this.k = getBackground();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            c();
        }
    }

    public void setAppMenuButtonHelper(z6 z6Var) {
        this.h = z6Var;
        this.e.setOnTouchListener(z6Var);
        this.e.setAccessibilityDelegate(this.h.E());
    }

    public void setMenuButtonHighlight(boolean z) {
        this.i = z;
        d();
    }

    @Override // android.view.View
    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        ImageButton imageButton = this.e;
        if (imageButton == null) {
            return;
        }
        imageButton.setOnKeyListener(onKeyListener);
    }

    public void setOriginalBackgroundForTesting(Drawable drawable) {
        this.k = drawable;
        setBackground(drawable);
    }

    public void setStateSupplier(nw2<ef1> nw2Var) {
        this.p = nw2Var;
    }
}
