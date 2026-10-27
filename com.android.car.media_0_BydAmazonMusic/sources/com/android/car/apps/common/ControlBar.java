package com.android.car.apps.common;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.android.car.apps.common.ControlBar;
import com.android.car.media.PlaybackFragment;
import com.android.car.media.main.R$integer;
import com.android.car.ui.R$drawable;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$string;
import com.android.car.ui.R$styleable;
import spi.a3;
import spi.c1;
import spi.n8;
import spi.uc;
import spi.z2;

/* JADX INFO: loaded from: classes.dex */
public class ControlBar extends RelativeLayout implements c1 {
    public static final /* synthetic */ int q = 0;
    public ViewGroup a;
    public FrameLayout[] b;
    public FrameLayout c;
    public final SparseArray<View> d;

    @Nullable
    public View e;
    public ImageButton f;
    public int g;
    public boolean h;

    @Nullable
    public View[] i;
    public int j;
    public int k;
    public boolean l;
    public n8 m;
    public ViewGroup n;
    public boolean o;
    public final a3 p;

    public ControlBar(Context context) {
        super(context);
        this.d = new SparseArray<>();
        this.p = new a3(this, 3);
        g(context, null, 0, 0);
    }

    public static /* synthetic */ void d(ControlBar controlBar) {
        boolean zHasFocus = controlBar.hasFocus();
        if (controlBar.o && !zHasFocus && controlBar.h) {
            controlBar.h();
        }
        controlBar.o = zHasFocus;
    }

    private View getExpandCollapseView() {
        View view = this.e;
        return view != null ? view : this.f;
    }

    @Override // spi.c1
    public final void a(@Nullable View view, int i) {
        if (view != null) {
            this.d.put(i, view);
        } else {
            this.d.remove(i);
        }
        i();
    }

    @Override // spi.c1
    public final ImageButton b(Drawable drawable) {
        return e(drawable, R$layout.control_bar_button);
    }

    public final ImageButton e(Drawable drawable, int i) {
        ImageButton imageButton = (ImageButton) LayoutInflater.from(this.c.getContext()).inflate(i, (ViewGroup) this.c, false);
        imageButton.setImageDrawable(drawable);
        return imageButton;
    }

    public final int f(int i) {
        return c1.c(i, this.j);
    }

    public final void g(Context context, AttributeSet attributeSet, int i, int i2) {
        View.inflate(context, R$layout.control_bar, this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ControlBar, i, i2);
        this.j = typedArrayObtainStyledAttributes.getInteger(R$styleable.ControlBar_columns, 3);
        this.l = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ControlBar_enableOverflow, true);
        typedArrayObtainStyledAttributes.recycle();
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.rows_container);
        this.a = viewGroup;
        int childCount = viewGroup.getChildCount();
        this.k = childCount;
        Preconditions.checkState(childCount > 0, "Must have at least 1 row");
        this.b = new FrameLayout[this.j * this.k];
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i3 = 0;
        while (true) {
            int i4 = this.k;
            if (i3 >= i4) {
                ImageButton imageButtonE = e(context.getDrawable(R$drawable.ic_overflow_button), R$layout.control_bar_button);
                this.f = imageButtonE;
                imageButtonE.setContentDescription(context.getString(R$string.control_bar_expand_collapse_button));
                this.f.setOnClickListener(new z2(this, 1));
                return;
            }
            ViewGroup viewGroup2 = (ViewGroup) this.a.getChildAt((i4 - i3) - 1);
            int i5 = 0;
            while (true) {
                int i6 = this.j;
                if (i5 < i6) {
                    int i7 = (i6 * i3) + i5;
                    this.b[i7] = (FrameLayout) layoutInflaterFrom.inflate(R$layout.control_bar_slot, viewGroup2, false);
                    if (this.c == null) {
                        this.c = this.b[i7];
                    }
                    if (i5 > 0) {
                        Space space = new Space(context);
                        viewGroup2.addView(space);
                        float f = 1.0f;
                        if (this.j == 5 && (i5 == 1 || i5 == 4)) {
                            f = 3.8f;
                        }
                        space.setLayoutParams(new LinearLayout.LayoutParams(0, -1, f));
                    }
                    viewGroup2.addView(this.b[i7]);
                    i5++;
                }
            }
            i3++;
        }
    }

    @Override // spi.c1
    public int getFocusedViewIndex() {
        int i = 0;
        while (true) {
            FrameLayout[] frameLayoutArr = this.b;
            if (i >= frameLayoutArr.length) {
                return -1;
            }
            FrameLayout frameLayout = frameLayoutArr[i];
            if (frameLayout != null && frameLayout.hasFocus()) {
                return i;
            }
            i++;
        }
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [java.util.ArrayList, java.util.List<android.view.View>] */
    public final void h() {
        boolean z = !this.h;
        this.h = z;
        View view = this.e;
        if (view != null) {
            view.setSelected(z);
        }
        n8 n8Var = this.m;
        if (n8Var != null) {
            boolean z2 = this.h;
            PlaybackFragment playbackFragment = n8Var.a;
            int i = PlaybackFragment.J;
            Resources resources = playbackFragment.requireContext().getResources();
            int integer = z2 ? resources.getInteger(R$integer.control_bar_expand_anim_duration) : resources.getInteger(R$integer.control_bar_collapse_anim_duration);
            View view2 = playbackFragment.e;
            if (view2 != null) {
                view2.setClickable(z2);
            }
            if (z2) {
                View view3 = playbackFragment.e;
                if (view3 != null) {
                    uc.g(view3, integer, null);
                }
            } else {
                View view4 = playbackFragment.e;
                if (view4 != null) {
                    uc.b(view4, integer, null);
                }
            }
            if (!playbackFragment.x) {
                for (View view5 : playbackFragment.m) {
                    if (z2) {
                        uc.b(view5, integer, null);
                    } else {
                        uc.g(view5, integer, null);
                    }
                }
            }
        }
        this.b[f(3)].setActivated(this.h);
        TransitionSet interpolator = new TransitionSet().addTransition(new ChangeBounds()).addTransition(new Fade()).setDuration(getContext().getResources().getInteger(this.h ? com.android.car.ui.R$integer.control_bar_expand_anim_duration : com.android.car.ui.R$integer.control_bar_collapse_anim_duration)).setInterpolator((TimeInterpolator) new FastOutSlowInInterpolator());
        if (this.n == null) {
            this.n = this;
            ViewParent parent = getParent();
            while (parent != null && (parent instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) parent;
                if (viewGroup.getLayoutParams().height != -2) {
                    break;
                }
                this.n = viewGroup;
                parent = viewGroup.getParent();
            }
        }
        TransitionManager.beginDelayedTransition(this.n, interpolator);
        for (int i2 = 0; i2 < this.g; i2++) {
            this.a.getChildAt(i2).setVisibility(this.h ? 0 : 8);
        }
    }

    public final void i() {
        View[] viewArr;
        int length = this.b.length;
        View[] viewArr2 = new View[length];
        for (int i = 0; i < this.d.size(); i++) {
            int iF = f(this.d.keyAt(i));
            if (iF >= 0 && iF < length) {
                viewArr2[iF] = this.d.valueAt(i);
            }
        }
        int iF2 = f(3);
        final int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            View expandCollapseView = null;
            if (viewArr2[i4] != null) {
                expandCollapseView = viewArr2[i4];
            } else if (!this.l || i4 != iF2 || (viewArr = this.i) == null || i3 >= viewArr.length - 1) {
                View[] viewArr3 = this.i;
                if (viewArr3 != null && i3 < viewArr3.length) {
                    expandCollapseView = viewArr3[i3];
                    i3++;
                }
            } else {
                expandCollapseView = getExpandCollapseView();
                Log.d("ControlBar", "" + this + "Setting expand control");
            }
            uc.d(expandCollapseView, this.b[i4]);
            if (expandCollapseView != null) {
                i2 = i4;
            }
        }
        this.g = i2 / this.j;
        if (this.k > 1) {
            this.a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: spi.b3
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    ControlBar controlBar = this.a;
                    int i5 = i2;
                    int i6 = 1;
                    while (true) {
                        int i7 = controlBar.k;
                        if (i6 >= i7) {
                            return;
                        }
                        int i8 = (i7 - 1) - i6;
                        i6++;
                        if (i5 < controlBar.j * i6) {
                            controlBar.a.getChildAt(i8).setTranslationX((controlBar.b[controlBar.j - 1].getX() - controlBar.b[i5].getX()) / 2.0f);
                        } else {
                            controlBar.a.getChildAt(i8).setTranslationX(0.0f);
                        }
                    }
                }
            });
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.p);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.p);
        super.onDetachedFromWindow();
    }

    public void setExpandCollapseView(@NonNull View view) {
        this.e = view;
        view.setOnClickListener(new z2(this, 0));
        i();
    }

    @Override // spi.c1
    public void setFocusAtViewIndex(int i) {
        if (-1 < i) {
            FrameLayout[] frameLayoutArr = this.b;
            if (i >= frameLayoutArr.length || frameLayoutArr[i] == null) {
                return;
            }
            frameLayoutArr[i].requestFocus();
        }
    }

    @Override // spi.c1
    public void setViews(@Nullable View[] viewArr) {
        this.i = viewArr;
        i();
    }

    public ControlBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new SparseArray<>();
        this.p = new a3(this, 0);
        g(context, attributeSet, 0, 0);
    }

    public ControlBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new SparseArray<>();
        this.p = new a3(this, 2);
        g(context, attributeSet, i, 0);
    }

    public ControlBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.d = new SparseArray<>();
        this.p = new a3(this, 1);
        g(context, attributeSet, i, i2);
    }
}
