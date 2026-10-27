package org.chromium.chrome.browser.accessibility_tab_switcher;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.bz;
import defpackage.c6;
import defpackage.ii2;
import defpackage.nh0;
import defpackage.yp;
import defpackage.z7;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.accessibility_tab_switcher.a;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabFavicon;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.browser_ui.styles.R$color;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityTabModelListItem extends FrameLayout implements View.OnClickListener {
    public static final /* synthetic */ int L = 0;
    public boolean A;
    public e B;
    public final GestureDetector C;
    public final int D;
    public AccessibilityTabModelListView E;
    public boolean F;
    public final a G;
    public final Handler H;
    public final b I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final c f54J;
    public final d K;
    public final int e;
    public final int f;
    public final int g;
    public AnimatorSet h;
    public final float i;
    public final float j;
    public final int k;
    public final int l;
    public final ColorStateList m;
    public final ColorStateList n;
    public final ColorStateList o;
    public final ColorStateList p;
    public float q;
    public LinearLayout r;
    public TextView s;
    public TextView t;
    public ImageView u;
    public ImageButton v;
    public LinearLayout w;
    public Button x;
    public Tab y;
    public boolean z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = AccessibilityTabModelListItem.L;
            AccessibilityTabModelListItem.this.b();
        }
    }

    public class b extends AnimatorListenerAdapter {
        public boolean e;

        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.e = true;
            AccessibilityTabModelListItem.this.F = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.e) {
                return;
            }
            AccessibilityTabModelListItem accessibilityTabModelListItem = AccessibilityTabModelListItem.this;
            e eVar = accessibilityTabModelListItem.B;
            int id = accessibilityTabModelListItem.y.getId();
            org.chromium.chrome.browser.accessibility_tab_switcher.a aVar = org.chromium.chrome.browser.accessibility_tab_switcher.a.this;
            TabModel tabModel = aVar.g;
            tabModel.m(z7.F(tabModel, id), true, true);
            aVar.notifyDataSetChanged();
            accessibilityTabModelListItem.setTranslationX(0.0f);
            accessibilityTabModelListItem.setScaleX(1.0f);
            accessibilityTabModelListItem.setScaleY(1.0f);
            accessibilityTabModelListItem.setAlpha(0.0f);
            accessibilityTabModelListItem.e(true);
            accessibilityTabModelListItem.c(false);
            accessibilityTabModelListItem.H.postDelayed(accessibilityTabModelListItem.G, accessibilityTabModelListItem.g);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            this.e = false;
        }
    }

    public class c extends AnimatorListenerAdapter {
        public boolean e;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.e = true;
            AccessibilityTabModelListItem.this.F = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.e) {
                return;
            }
            int i = AccessibilityTabModelListItem.L;
            AccessibilityTabModelListItem accessibilityTabModelListItem = AccessibilityTabModelListItem.this;
            accessibilityTabModelListItem.e(false);
            accessibilityTabModelListItem.setAlpha(1.0f);
            accessibilityTabModelListItem.r.setAlpha(1.0f);
            accessibilityTabModelListItem.w.setAlpha(1.0f);
            accessibilityTabModelListItem.a();
            e eVar = accessibilityTabModelListItem.B;
            int id = accessibilityTabModelListItem.y.getId();
            org.chromium.chrome.browser.accessibility_tab_switcher.a aVar = org.chromium.chrome.browser.accessibility_tab_switcher.a.this;
            if (aVar.g.w(id)) {
                aVar.g.x(id);
            } else {
                TabModel tabModel = aVar.g;
                Tab tabF = z7.F(tabModel, id);
                if (tabF != null) {
                    tabModel.m(tabF, true, false);
                }
            }
            aVar.notifyDataSetChanged();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            this.e = false;
        }
    }

    public class d extends nh0 {
        public d() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void D0(Tab tab, boolean z) {
            e eVar;
            if (z) {
                AccessibilityTabModelListItem accessibilityTabModelListItem = AccessibilityTabModelListItem.this;
                if (accessibilityTabModelListItem.F || (eVar = accessibilityTabModelListItem.B) == null) {
                    return;
                }
                tab.getId();
                org.chromium.chrome.browser.accessibility_tab_switcher.a.this.notifyDataSetChanged();
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void X(Tab tab, Bitmap bitmap, GURL gurl) {
            int i = AccessibilityTabModelListItem.L;
            AccessibilityTabModelListItem accessibilityTabModelListItem = AccessibilityTabModelListItem.this;
            accessibilityTabModelListItem.f();
            e eVar = accessibilityTabModelListItem.B;
            if (eVar != null) {
                tab.getId();
                org.chromium.chrome.browser.accessibility_tab_switcher.a.this.notifyDataSetChanged();
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void r1(Tab tab) {
            int i = AccessibilityTabModelListItem.L;
            AccessibilityTabModelListItem accessibilityTabModelListItem = AccessibilityTabModelListItem.this;
            accessibilityTabModelListItem.g();
            e eVar = accessibilityTabModelListItem.B;
            if (eVar != null) {
                tab.getId();
                org.chromium.chrome.browser.accessibility_tab_switcher.a.this.notifyDataSetChanged();
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void x0(TabImpl tabImpl) {
            int i = AccessibilityTabModelListItem.L;
            AccessibilityTabModelListItem accessibilityTabModelListItem = AccessibilityTabModelListItem.this;
            accessibilityTabModelListItem.g();
            e eVar = accessibilityTabModelListItem.B;
            if (eVar != null) {
                tabImpl.getId();
                org.chromium.chrome.browser.accessibility_tab_switcher.a.this.notifyDataSetChanged();
            }
        }
    }

    public interface e {
    }

    public class f extends GestureDetector.SimpleOnGestureListener {
        public f() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AccessibilityTabModelListItem accessibilityTabModelListItem = AccessibilityTabModelListItem.this;
            if (Math.abs(accessibilityTabModelListItem.getTranslationX()) < accessibilityTabModelListItem.j) {
                return false;
            }
            accessibilityTabModelListItem.d(Math.min(((long) Math.abs(((double) accessibilityTabModelListItem.getWidth()) / Math.sqrt((f2 * f2) + (f * f)))) * 150, accessibilityTabModelListItem.f));
            accessibilityTabModelListItem.E.setCanScroll(true);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AccessibilityTabModelListItem accessibilityTabModelListItem = AccessibilityTabModelListItem.this;
            if (org.chromium.chrome.browser.accessibility_tab_switcher.a.this.g.w(accessibilityTabModelListItem.y.getId())) {
                return false;
            }
            accessibilityTabModelListItem.E.setCanScroll(false);
            accessibilityTabModelListItem.setTranslationX(accessibilityTabModelListItem.getTranslationX() + (motionEvent2.getX() - motionEvent.getX()));
            accessibilityTabModelListItem.setAlpha(1.0f - Math.abs(accessibilityTabModelListItem.getTranslationX() / accessibilityTabModelListItem.getWidth()));
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            AccessibilityTabModelListItem.this.performClick();
            return true;
        }
    }

    public AccessibilityTabModelListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.G = new a();
        this.H = new Handler();
        this.I = new b();
        this.f54J = new c();
        this.K = new d();
        this.C = new GestureDetector(context, new f());
        float dimension = context.getResources().getDimension(R$dimen.swipe_commit_distance);
        this.i = dimension;
        this.j = dimension / 3.0f;
        this.D = context.getResources().getDimensionPixelOffset(R$dimen.accessibility_tab_height);
        String str = yp.a;
        this.m = bz.b(context, R$color.default_icon_color_tint_list);
        this.n = bz.b(context, org.chromium.chrome.R$color.default_icon_color_dark);
        this.o = bz.b(context, org.chromium.chrome.R$color.default_icon_color_secondary_tint_list);
        this.p = bz.b(context, org.chromium.chrome.R$color.white_alpha_70);
        this.k = getResources().getInteger(R$integer.list_item_level_default);
        this.l = getResources().getInteger(R$integer.list_item_level_incognito);
        this.e = 100;
        this.f = 300;
        this.g = 4000;
        setFocusableInTouchMode(true);
    }

    public final void a() {
        AnimatorSet animatorSet = this.h;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.h.cancel();
        }
        this.h = null;
    }

    public final void b() {
        a();
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, "height", 0);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.SCALE_Y, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfInt, objectAnimatorOfFloat);
        animatorSet.addListener(this.f54J);
        animatorSet.setDuration(this.f);
        animatorSet.start();
        this.h = animatorSet;
    }

    public final void c(boolean z) {
        a();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.TRANSLATION_X, 0.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.ALPHA, 1.0f);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.SCALE_X, 1.0f);
        ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.SCALE_Y, 1.0f);
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, "height", this.D);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2, objectAnimatorOfFloat3, objectAnimatorOfFloat4, objectAnimatorOfInt);
        animatorSet.setDuration(z ? this.e : this.f);
        animatorSet.start();
        this.h = animatorSet;
    }

    public final void d(long j) {
        a();
        this.q = getTranslationX();
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        fArr[0] = getTranslationX() > 0.0f ? getWidth() : -getWidth();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) property, fArr);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.ALPHA, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat2, objectAnimatorOfFloat);
        animatorSet.addListener(this.I);
        animatorSet.setDuration(Math.min(j, this.f));
        animatorSet.start();
        this.h = animatorSet;
    }

    public final void e(boolean z) {
        if (z && this.z) {
            this.w.setVisibility(0);
            this.r.setVisibility(4);
            this.w.requestFocus();
        } else {
            this.r.setVisibility(0);
            this.w.setVisibility(4);
            g();
            f();
        }
    }

    public final void f() {
        Tab tab = this.y;
        if (tab != null) {
            Bitmap bitmapD = TabFavicon.d(tab);
            if (bitmapD != null) {
                this.u.setImageTintList(null);
                this.u.setImageBitmap(bitmapD);
            } else {
                this.u.setImageResource(R$drawable.ic_globe_24dp);
                this.u.setImageTintList(this.y.isIncognito() ? this.n : this.m);
            }
        }
    }

    public final void g() {
        String string;
        String strJ;
        Tab tab = this.y;
        if (tab == null || !tab.isInitialized()) {
            string = null;
            strJ = null;
        } else {
            string = this.y.getTitle();
            strJ = this.y.getUrl().j();
            if (TextUtils.isEmpty(string)) {
                string = strJ;
            }
        }
        if (TextUtils.isEmpty(string)) {
            string = getContext().getResources().getString(R$string.tab_loading_default_title);
        }
        if (string != null && !string.equals(this.s.getText())) {
            this.s.setText(string);
        }
        String string2 = this.A ? getContext().getString(R$string.accessibility_tabstrip_tab_selected, string) : getContext().getString(R$string.accessibility_tabstrip_tab, string);
        if (!string2.equals(getContentDescription())) {
            setContentDescription(string2);
            this.v.setContentDescription(getContext().getString(R$string.accessibility_tabstrip_btn_close_tab, string));
        }
        if (this.y.isIncognito()) {
            setBackgroundResource(org.chromium.chrome.R$color.default_bg_color_dark);
            this.u.getBackground().setLevel(this.l);
            c6.f(this.s, R$style.TextAppearance_TextLarge_Primary_Baseline_Light);
            c6.f(this.t, R$style.TextAppearance_TextMedium_Primary_Baseline_Light);
            this.v.setImageTintList(this.p);
        } else {
            setBackgroundColor(ii2.a(getContext()));
            this.u.getBackground().setLevel(this.k);
            c6.f(this.s, R$style.TextAppearance_TextLarge_Primary);
            c6.f(this.t, R$style.TextAppearance_TextMedium_Secondary);
            this.v.setImageTintList(this.o);
        }
        if (TextUtils.isEmpty(strJ)) {
            this.t.setVisibility(8);
        } else {
            this.t.setText(strJ);
            this.t.setVisibility(0);
        }
    }

    public View getCloseButtonForTests() {
        return this.v;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.y != null) {
            f();
            g();
            this.y.x(this.K);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.B == null) {
            return;
        }
        int id = this.y.getId();
        if (view == this && !org.chromium.chrome.browser.accessibility_tab_switcher.a.this.g.w(id)) {
            org.chromium.chrome.browser.accessibility_tab_switcher.a aVar = org.chromium.chrome.browser.accessibility_tab_switcher.a.this;
            a.c cVar = aVar.h;
            if (cVar != null) {
                ((org.chromium.chrome.browser.accessibility_tab_switcher.b) cVar).I(id, true);
            }
            TabModel tabModel = aVar.g;
            tabModel.q(z7.G(tabModel, id), 3, false);
            aVar.notifyDataSetChanged();
            return;
        }
        if (view == this.v) {
            this.F = true;
            if (!this.z) {
                b();
                return;
            }
            a();
            this.q = 0.0f;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.SCALE_X, 1.2f);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.SCALE_Y, 0.0f);
            ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this, (Property<AccessibilityTabModelListItem, Float>) View.ALPHA, 0.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorOfFloat3, objectAnimatorOfFloat2, objectAnimatorOfFloat);
            animatorSet.addListener(this.I);
            animatorSet.setDuration(this.e);
            animatorSet.start();
            this.h = animatorSet;
            return;
        }
        Button button = this.x;
        if (view == button) {
            this.x.announceForAccessibility(button.getContext().getString(R$string.accessibility_undo_closed_tab_announcement_message, this.y.getTitle()));
            this.H.removeCallbacks(this.G);
            org.chromium.chrome.browser.accessibility_tab_switcher.a aVar2 = org.chromium.chrome.browser.accessibility_tab_switcher.a.this;
            aVar2.g.n(id);
            aVar2.notifyDataSetChanged();
            e(false);
            setAlpha(0.0f);
            float f2 = this.q;
            if (f2 > 0.0f) {
                setTranslationX(getWidth());
                c(false);
            } else if (f2 < 0.0f) {
                setTranslationX(-getWidth());
                c(false);
            } else {
                setScaleX(1.2f);
                setScaleY(0.0f);
                c(true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Tab tab = this.y;
        if (tab != null) {
            tab.t(this.K);
        }
        a();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.content);
        this.r = linearLayout;
        this.s = (TextView) linearLayout.findViewById(R$id.title);
        this.t = (TextView) this.r.findViewById(R$id.description);
        this.u = (ImageView) this.r.findViewById(R$id.start_icon);
        this.v = (ImageButton) this.r.findViewById(R$id.end_button);
        this.u.setBackgroundResource(R$drawable.list_item_icon_modern_bg);
        this.w = (LinearLayout) findViewById(R$id.undo_contents);
        this.x = (Button) findViewById(R$id.undo_button);
        setClickable(true);
        setFocusable(true);
        this.v.setOnClickListener(this);
        this.x.setOnClickListener(this);
        setOnClickListener(this);
        this.v.setVisibility(0);
        this.v.setImageResource(R$drawable.btn_delete_24dp);
        this.v.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.v.setPaddingRelative(getResources().getDimensionPixelSize(R$dimen.accessibility_tab_switcher_item_close_button_padding_start), getPaddingTop(), getResources().getDimensionPixelSize(R$dimen.accessibility_tab_switcher_item_close_button_padding_end), getPaddingBottom());
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        this.H.removeCallbacks(this.G);
        if (this.C.onTouchEvent(motionEvent)) {
            return true;
        }
        if (motionEvent.getActionMasked() != 1) {
            return super.onTouchEvent(motionEvent);
        }
        if (Math.abs(getTranslationX()) > this.i) {
            d(300L);
        } else {
            c(false);
        }
        this.E.setCanScroll(true);
        return true;
    }

    @UsedByReflection
    @SuppressLint({"AnimatorKeep"})
    public void setHeight(int i) {
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, i);
        } else if (layoutParams.height == i) {
            return;
        } else {
            layoutParams.height = i;
        }
        setLayoutParams(layoutParams);
    }
}
