package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import defpackage.a03;
import defpackage.ao3;
import defpackage.bz;
import defpackage.c6;
import defpackage.ef2;
import defpackage.h21;
import defpackage.jf2;
import defpackage.jk0;
import defpackage.n3;
import defpackage.pc;
import defpackage.vz2;
import defpackage.wz2;
import defpackage.xz2;
import defpackage.yz2;
import defpackage.zz2;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$style;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class TabGridDialogView extends FrameLayout {
    public static Callback<RectF> R;
    public ObjectAnimator A;
    public AnimatorSet B;
    public AnimatorSet C;
    public yz2 D;
    public n E;
    public final HashMap F;
    public int G;
    public int H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f78J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public final Context e;
    public final int f;
    public final float g;
    public View h;
    public View i;
    public View j;
    public View k;
    public ViewGroup l;
    public ViewGroup m;
    public TextView n;
    public RelativeLayout o;
    public PropertyModel p;
    public ef2 q;
    public FrameLayout.LayoutParams r;
    public vz2 s;
    public f t;
    public Animator u;
    public Animator v;
    public AnimatorSet w;
    public AnimatorSet x;
    public ObjectAnimator y;
    public ObjectAnimator z;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            TabGridDialogView tabGridDialogView = TabGridDialogView.this;
            tabGridDialogView.h.setAlpha(0.0f);
            tabGridDialogView.i.setAlpha(0.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            TabGridDialogView tabGridDialogView = TabGridDialogView.this;
            tabGridDialogView.h.bringToFront();
            tabGridDialogView.i.bringToFront();
        }
    }

    public class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            TabGridDialogView.this.h.setAlpha(1.0f);
        }
    }

    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            TabGridDialogView tabGridDialogView = TabGridDialogView.this;
            tabGridDialogView.h.setAlpha(0.0f);
            tabGridDialogView.i.setAlpha(0.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            TabGridDialogView tabGridDialogView = TabGridDialogView.this;
            tabGridDialogView.h.bringToFront();
            tabGridDialogView.i.bringToFront();
            tabGridDialogView.o.setAlpha(0.0f);
            tabGridDialogView.h.setAlpha(1.0f);
            tabGridDialogView.i.setAlpha(1.0f);
        }
    }

    public class d extends AnimatorListenerAdapter {
        public d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            TabGridDialogView.this.o.bringToFront();
        }
    }

    public class e extends AnimatorListenerAdapter {
        public e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            TabGridDialogView tabGridDialogView = TabGridDialogView.this;
            tabGridDialogView.o.setTranslationX(0.0f);
            tabGridDialogView.o.setTranslationY(0.0f);
            tabGridDialogView.o.setScaleX(1.0f);
            tabGridDialogView.o.setScaleY(1.0f);
        }
    }

    public interface f {
        void a();
    }

    public TabGridDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = new HashMap();
        this.M = 1;
        this.e = context;
        this.g = context.getResources().getDimension(R$dimen.tab_grid_card_margin);
        this.f = (int) context.getResources().getDimension(R$dimen.tab_group_toolbar_height);
        int i = R$color.tab_grid_dialog_background_color;
        Object obj = bz.a;
        this.L = context.getColor(i);
        this.P = n3.s0(context, false, false);
        this.Q = n3.s0(context, false, true);
        this.N = n3.r0(context, false, false);
        this.O = n3.r0(context, false, true);
    }

    public static void setSourceRectCallbackForTesting(Callback<RectF> callback) {
        R = callback;
    }

    public final void a(View view) {
        if (view == null) {
            ((ImageView) this.i.findViewById(R$id.tab_favicon)).setImageDrawable(null);
            ((TextView) this.i.findViewById(R$id.tab_title)).setText("");
            ((ImageView) this.i.findViewById(R$id.tab_thumbnail)).setImageDrawable(null);
            ((ImageView) this.i.findViewById(R$id.action_button)).setImageDrawable(null);
            this.i.findViewById(R$id.background_view).setBackground(null);
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams.width = view.getWidth();
        layoutParams.height = view.getHeight();
        int i = R$id.tab_title;
        if (view.findViewById(i) == null) {
            return;
        }
        View view2 = this.i;
        int i2 = R$id.card_view;
        view2.findViewById(i2).setBackground(view.findViewById(i2).getBackground());
        int i3 = R$id.tab_favicon;
        ImageView imageView = (ImageView) view.findViewById(i3);
        ImageView imageView2 = (ImageView) this.i.findViewById(i3);
        if (imageView.getDrawable() != null) {
            int dimension = (int) this.e.getResources().getDimension(R$dimen.tab_grid_card_favicon_padding);
            imageView2.setPadding(dimension, dimension, dimension, dimension);
            imageView2.setImageDrawable(imageView.getDrawable());
        } else {
            imageView2.setImageDrawable(null);
        }
        ((TextView) this.i.findViewById(i)).setText(((TextView) view.findViewById(i)).getText());
        c6.f((TextView) this.i.findViewById(i), R$style.TextAppearance_TextMediumThick_Primary);
        ((TextView) this.i.findViewById(i)).setTextColor(((TextView) view.findViewById(i)).getTextColors());
        View view3 = this.i;
        int i4 = R$id.tab_thumbnail;
        ((ImageView) view3.findViewById(i4)).setImageDrawable(((ImageView) view.findViewById(i4)).getDrawable());
        View view4 = this.i;
        int i5 = R$id.action_button;
        ImageView imageView3 = (ImageView) view4.findViewById(i5);
        imageView3.setImageDrawable(((ImageView) view.findViewById(i5)).getDrawable());
        imageView3.setImageTintList(((ImageView) view.findViewById(i5)).getImageTintList());
        this.i.findViewById(R$id.background_view).setBackground(null);
    }

    public final void b(int i) {
        Context context = this.e;
        if (i == 1) {
            this.G = (int) context.getResources().getDimension(R$dimen.tab_grid_dialog_side_margin);
            this.H = (int) context.getResources().getDimension(R$dimen.tab_grid_dialog_top_margin);
        } else {
            this.G = (int) context.getResources().getDimension(R$dimen.tab_grid_dialog_top_margin);
            this.H = (int) context.getResources().getDimension(R$dimen.tab_grid_dialog_side_margin);
        }
        FrameLayout.LayoutParams layoutParams = this.r;
        int i2 = this.G;
        int i3 = this.H;
        layoutParams.setMargins(i2, i3, i2, i3);
        this.I = i;
    }

    public final void c(boolean z) {
        this.k.bringToFront();
        ((GradientDrawable) this.n.getBackground()).setColor(z ? this.O : this.N);
        this.n.setTextColor(z ? this.Q : this.P);
    }

    public int getBackgroundColorForTesting() {
        return this.L;
    }

    public Animator getCurrentDialogAnimatorForTesting() {
        return this.u;
    }

    public Animator getCurrentUngroupBarAnimatorForTesting() {
        return this.v;
    }

    public ef2 getScrimCoordinatorForTesting() {
        return this.q;
    }

    public AnimatorSet getShowDialogAnimationForTesting() {
        return this.B;
    }

    public ViewGroup getSnackBarContainer() {
        return this.l;
    }

    public int getUngroupBarBackgroundColorForTesting() {
        return this.N;
    }

    public int getUngroupBarHoveredBackgroundColorForTesting() {
        return this.O;
    }

    public int getUngroupBarHoveredTextColorForTesting() {
        return this.Q;
    }

    public int getUngroupBarStatusForTesting() {
        return this.M;
    }

    public int getUngroupBarTextColorForTesting() {
        return this.P;
    }

    public f getVisibilityListenerForTesting() {
        return this.t;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup viewGroup = (ViewGroup) getParent();
        this.m = viewGroup;
        this.f78J = viewGroup.getHeight();
        this.K = this.m.getWidth();
        this.s = new vz2(0, this);
        this.m.getViewTreeObserver().addOnGlobalLayoutListener(this.s);
        setVisibility(8);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        b(configuration.orientation);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewGroup viewGroup = this.m;
        if (viewGroup != null) {
            viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this.s);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.r = new FrameLayout.LayoutParams(-1, -1);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R$id.dialog_container_view);
        this.o = relativeLayout;
        relativeLayout.setLayoutParams(this.r);
        View viewFindViewById = findViewById(R$id.dialog_ungroup_bar);
        this.k = viewFindViewById;
        this.n = (TextView) viewFindViewById.findViewById(R$id.dialog_ungroup_bar_text);
        View viewFindViewById2 = findViewById(R$id.dialog_frame);
        this.h = viewFindViewById2;
        viewFindViewById2.setLayoutParams(this.r);
        this.i = findViewById(R$id.dialog_animation_card_view);
        this.l = (ViewGroup) findViewById(R$id.dialog_snack_bar_container_view);
        b(this.e.getResources().getConfiguration().orientation);
        this.B = new AnimatorSet();
        this.C = new AnimatorSet();
        this.w = new AnimatorSet();
        this.w.play(ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.ALPHA, 0.0f, 1.0f));
        this.w.setInterpolator(pc.e);
        this.w.setDuration(300L);
        this.x = new AnimatorSet();
        this.x.play(ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.ALPHA, 1.0f, 0.0f));
        this.x.setInterpolator(pc.d);
        this.x.setDuration(300L);
        this.x.addListener(new wz2(this));
        int iA = ao3.a(getContext(), getContext().getResources().getConfiguration().screenHeightDp);
        float y = this.o.getY();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.TRANSLATION_Y, y, iA);
        this.y = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setInterpolator(h21.b);
        this.y.setDuration(300L);
        this.y.addListener(new xz2(this, y));
        this.D = new yz2(this);
        this.E = new n(this);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.k, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
        this.z = objectAnimatorOfFloat2;
        objectAnimatorOfFloat2.setDuration(200L);
        this.z.setInterpolator(h21.g);
        this.z.addListener(new zz2(this));
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.k, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f);
        this.A = objectAnimatorOfFloat3;
        objectAnimatorOfFloat3.setDuration(200L);
        this.A.setInterpolator(h21.d);
        this.A.addListener(new a03(this));
        this.o.setClipToOutline(true);
    }

    public void setScrimClickRunnable(Runnable runnable) {
        PropertyModel.b bVar = new PropertyModel.b(jf2.m);
        bVar.e(jf2.c, this.o);
        bVar.b(jf2.d, false);
        bVar.b(jf2.b, true);
        bVar.c(jf2.a, 0);
        bVar.e(jf2.f, runnable);
        bVar.b(jf2.k, true);
        this.p = bVar.a();
    }

    public void setVisibilityListener(f fVar) {
        this.t = fVar;
    }

    public void setupDialogAnimation(View view) {
        if (view == null) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.B = animatorSet;
            animatorSet.play(this.w);
            this.B.removeAllListeners();
            this.B.addListener(this.D);
            this.C = new AnimatorSet();
            getContext();
            this.C.play(this.x);
            this.C.removeAllListeners();
            this.C.addListener(this.E);
            a(null);
            return;
        }
        this.j = view;
        Rect rect = new Rect();
        this.j.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        this.m.getGlobalVisibleRect(rect2);
        rect.offset(0, -rect2.top);
        a(this.j);
        int i = this.f78J - (this.H * 2);
        int i2 = this.K - (this.G * 2);
        float f2 = rect.left;
        float f3 = this.g;
        float f4 = f2 + f3;
        float f5 = rect.top + f3;
        float f6 = f3 * 2.0f;
        float fHeight = rect.height() - f6;
        float fWidth = rect.width() - f6;
        Callback<RectF> callback = R;
        if (callback != null) {
            callback.onResult(new RectF(f4, f5, f4 + fWidth, f5 + fHeight));
        }
        float f7 = -((((i / 2) + this.H) - (fHeight / 2.0f)) - f5);
        float f8 = -((((i2 / 2) + this.G) - (fWidth / 2.0f)) - f4);
        float f9 = i;
        float f10 = fHeight / f9;
        float f11 = i2;
        float f12 = fWidth / f11;
        float fWidth2 = this.I == 1 ? f11 / rect.width() : f9 / rect.height();
        float f13 = (fWidth2 - 1.0f) / 2.0f;
        float f14 = (f13 * fHeight) + this.H;
        float f15 = (f13 * fWidth) + this.G;
        float f16 = f5 - f3;
        float f17 = f4 - f3;
        float f18 = f7 - ((fHeight - (f9 * f12)) / 2.0f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.TRANSLATION_Y, f16, f14);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.TRANSLATION_X, f17, f15);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.SCALE_X, 1.0f, fWidth2);
        ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.SCALE_Y, 1.0f, fWidth2);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(300L);
        jk0 jk0Var = h21.e;
        animatorSet2.setInterpolator(jk0Var);
        animatorSet2.play(objectAnimatorOfFloat).with(objectAnimatorOfFloat2).with(objectAnimatorOfFloat4).with(objectAnimatorOfFloat3);
        ObjectAnimator objectAnimatorOfFloat5 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f);
        objectAnimatorOfFloat5.setDuration(150L);
        LinearInterpolator linearInterpolator = h21.f;
        objectAnimatorOfFloat5.setInterpolator(linearInterpolator);
        ObjectAnimator objectAnimatorOfFloat6 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.TRANSLATION_Y, f18, 0.0f);
        float f19 = fWidth2;
        ObjectAnimator objectAnimatorOfFloat7 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.TRANSLATION_X, f8, 0.0f);
        ObjectAnimator objectAnimatorOfFloat8 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.SCALE_Y, f12, 1.0f);
        ObjectAnimator objectAnimatorOfFloat9 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.SCALE_X, f12, 1.0f);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.setDuration(300L);
        animatorSet3.setInterpolator(jk0Var);
        animatorSet3.play(objectAnimatorOfFloat6).with(objectAnimatorOfFloat7).with(objectAnimatorOfFloat8).with(objectAnimatorOfFloat9);
        ObjectAnimator objectAnimatorOfFloat10 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.ALPHA, 0.0f, 1.0f);
        objectAnimatorOfFloat10.setDuration(150L);
        objectAnimatorOfFloat10.setStartDelay(150L);
        objectAnimatorOfFloat10.setInterpolator(linearInterpolator);
        ObjectAnimator objectAnimatorOfFloat11 = ObjectAnimator.ofFloat(this.h, (Property<View, Float>) View.TRANSLATION_Y, f7, 0.0f);
        ObjectAnimator objectAnimatorOfFloat12 = ObjectAnimator.ofFloat(this.h, (Property<View, Float>) View.TRANSLATION_X, f8, 0.0f);
        ObjectAnimator objectAnimatorOfFloat13 = ObjectAnimator.ofFloat(this.h, (Property<View, Float>) View.SCALE_Y, f10, 1.0f);
        ObjectAnimator objectAnimatorOfFloat14 = ObjectAnimator.ofFloat(this.h, (Property<View, Float>) View.SCALE_X, f12, 1.0f);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.setDuration(300L);
        animatorSet4.setInterpolator(jk0Var);
        animatorSet4.play(objectAnimatorOfFloat11).with(objectAnimatorOfFloat12).with(objectAnimatorOfFloat13).with(objectAnimatorOfFloat14);
        ObjectAnimator objectAnimatorOfFloat15 = ObjectAnimator.ofFloat(this.j, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f);
        objectAnimatorOfFloat15.setDuration(50L);
        animatorSet3.addListener(new c());
        objectAnimatorOfFloat10.addListener(new d());
        AnimatorSet animatorSet5 = new AnimatorSet();
        this.B = animatorSet5;
        animatorSet5.play(animatorSet2).with(objectAnimatorOfFloat5).with(animatorSet4).with(animatorSet3).with(objectAnimatorOfFloat10).with(objectAnimatorOfFloat15);
        this.B.addListener(this.D);
        ObjectAnimator objectAnimatorOfFloat16 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.TRANSLATION_Y, 0.0f, f18);
        ObjectAnimator objectAnimatorOfFloat17 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.TRANSLATION_X, 0.0f, f8);
        ObjectAnimator objectAnimatorOfFloat18 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.SCALE_Y, 1.0f, f12);
        ObjectAnimator objectAnimatorOfFloat19 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.SCALE_X, 1.0f, f12);
        AnimatorSet animatorSet6 = new AnimatorSet();
        animatorSet6.play(objectAnimatorOfFloat16).with(objectAnimatorOfFloat17).with(objectAnimatorOfFloat18).with(objectAnimatorOfFloat19);
        animatorSet6.setDuration(300L);
        animatorSet6.setInterpolator(jk0Var);
        animatorSet6.addListener(new e());
        ObjectAnimator objectAnimatorOfFloat20 = ObjectAnimator.ofFloat(this.o, (Property<RelativeLayout, Float>) View.ALPHA, 1.0f, 0.0f);
        objectAnimatorOfFloat20.setDuration(150L);
        objectAnimatorOfFloat20.setInterpolator(linearInterpolator);
        ObjectAnimator objectAnimatorOfFloat21 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.TRANSLATION_Y, f14, f16);
        ObjectAnimator objectAnimatorOfFloat22 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.TRANSLATION_X, f15, f17);
        ObjectAnimator objectAnimatorOfFloat23 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.SCALE_X, f19, 1.0f);
        ObjectAnimator objectAnimatorOfFloat24 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.SCALE_Y, f19, 1.0f);
        AnimatorSet animatorSet7 = new AnimatorSet();
        animatorSet7.play(objectAnimatorOfFloat21).with(objectAnimatorOfFloat22).with(objectAnimatorOfFloat23).with(objectAnimatorOfFloat24);
        animatorSet7.setDuration(300L);
        animatorSet7.setInterpolator(jk0Var);
        ObjectAnimator objectAnimatorOfFloat25 = ObjectAnimator.ofFloat(this.i, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
        objectAnimatorOfFloat25.setDuration(150L);
        objectAnimatorOfFloat25.setStartDelay(150L);
        objectAnimatorOfFloat25.setInterpolator(linearInterpolator);
        objectAnimatorOfFloat25.addListener(new a());
        ObjectAnimator objectAnimatorOfFloat26 = ObjectAnimator.ofFloat(this.h, (Property<View, Float>) View.TRANSLATION_Y, 0.0f, f7);
        ObjectAnimator objectAnimatorOfFloat27 = ObjectAnimator.ofFloat(this.h, (Property<View, Float>) View.TRANSLATION_X, 0.0f, f8);
        ObjectAnimator objectAnimatorOfFloat28 = ObjectAnimator.ofFloat(this.h, (Property<View, Float>) View.SCALE_Y, 1.0f, f10);
        ObjectAnimator objectAnimatorOfFloat29 = ObjectAnimator.ofFloat(this.h, (Property<View, Float>) View.SCALE_X, 1.0f, f12);
        AnimatorSet animatorSet8 = new AnimatorSet();
        animatorSet8.play(objectAnimatorOfFloat26).with(objectAnimatorOfFloat27).with(objectAnimatorOfFloat28).with(objectAnimatorOfFloat29);
        animatorSet8.setDuration(300L);
        animatorSet8.setInterpolator(jk0Var);
        animatorSet8.addListener(new b());
        ObjectAnimator objectAnimatorOfFloat30 = ObjectAnimator.ofFloat(this.j, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
        objectAnimatorOfFloat30.setDuration(50L);
        objectAnimatorOfFloat30.setStartDelay(250L);
        AnimatorSet animatorSet9 = new AnimatorSet();
        this.C = animatorSet9;
        animatorSet9.play(animatorSet6).with(objectAnimatorOfFloat20).with(animatorSet8).with(animatorSet7).with(objectAnimatorOfFloat25).with(objectAnimatorOfFloat30);
        this.C.addListener(this.E);
    }

    public void setupScrimCoordinator(ef2 ef2Var) {
        this.q = ef2Var;
    }
}
