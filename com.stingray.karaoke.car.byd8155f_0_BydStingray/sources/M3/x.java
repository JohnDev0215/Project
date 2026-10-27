package m3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f19155A;
    public boolean B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s f19157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f19158b;
    public final ViewGroup c;
    public final ViewGroup d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ViewGroup f19159e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ViewGroup f19160f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ViewGroup f19161g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ViewGroup f19162h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ViewGroup f19163i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final View f19164j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final View f19165k;
    public final AnimatorSet l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AnimatorSet f19166m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AnimatorSet f19167n;
    public final AnimatorSet o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AnimatorSet f19168p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ValueAnimator f19169q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ValueAnimator f19170r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final t f19171s = new t(this, 0);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final t f19172t = new t(this, 3);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final t f19173u = new t(this, 4);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final t f19174v = new t(this, 5);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final t f19175w = new t(this, 6);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final ViewOnLayoutChangeListenerC1806f f19176x = new ViewOnLayoutChangeListenerC1806f(this, 1);

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f19156C = true;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f19178z = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final ArrayList f19177y = new ArrayList();

    public x(s sVar) {
        final int i7 = 2;
        this.f19157a = sVar;
        final int i10 = 0;
        final int i11 = 3;
        int i12 = 5;
        final int i13 = 1;
        this.f19158b = sVar.findViewById(R.id.exo_controls_background);
        this.c = (ViewGroup) sVar.findViewById(R.id.exo_center_controls);
        this.f19159e = (ViewGroup) sVar.findViewById(R.id.exo_minimal_controls);
        ViewGroup viewGroup = (ViewGroup) sVar.findViewById(R.id.exo_bottom_bar);
        this.d = viewGroup;
        this.f19163i = (ViewGroup) sVar.findViewById(R.id.exo_time);
        View viewFindViewById = sVar.findViewById(R.id.exo_progress);
        this.f19164j = viewFindViewById;
        this.f19160f = (ViewGroup) sVar.findViewById(R.id.exo_basic_controls);
        this.f19161g = (ViewGroup) sVar.findViewById(R.id.exo_extra_controls);
        this.f19162h = (ViewGroup) sVar.findViewById(R.id.exo_extra_controls_scroll_view);
        View viewFindViewById2 = sVar.findViewById(R.id.exo_overflow_show);
        this.f19165k = viewFindViewById2;
        View viewFindViewById3 = sVar.findViewById(R.id.exo_overflow_hide);
        if (viewFindViewById2 != null && viewFindViewById3 != null) {
            viewFindViewById2.setOnClickListener(new K6.e(this, i12));
            viewFindViewById3.setOnClickListener(new K6.e(this, i12));
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: m3.u

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ x f19150b;

            {
                this.f19150b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i14 = i11;
                x xVar = this.f19150b;
                xVar.getClass();
                switch (i14) {
                    case 0:
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view = xVar.f19158b;
                        if (view != null) {
                            view.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup2 = xVar.c;
                        if (viewGroup2 != null) {
                            viewGroup2.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup3 = xVar.f19159e;
                        if (viewGroup3 != null) {
                            viewGroup3.setAlpha(fFloatValue);
                        }
                        break;
                    case 1:
                        xVar.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    case 2:
                        xVar.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view2 = xVar.f19158b;
                        if (view2 != null) {
                            view2.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup4 = xVar.c;
                        if (viewGroup4 != null) {
                            viewGroup4.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup5 = xVar.f19159e;
                        if (viewGroup5 != null) {
                            viewGroup5.setAlpha(fFloatValue2);
                        }
                        break;
                }
            }
        });
        valueAnimatorOfFloat.addListener(new v(this, i10));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat2.setInterpolator(new LinearInterpolator());
        valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: m3.u

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ x f19150b;

            {
                this.f19150b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i14 = i10;
                x xVar = this.f19150b;
                xVar.getClass();
                switch (i14) {
                    case 0:
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view = xVar.f19158b;
                        if (view != null) {
                            view.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup2 = xVar.c;
                        if (viewGroup2 != null) {
                            viewGroup2.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup3 = xVar.f19159e;
                        if (viewGroup3 != null) {
                            viewGroup3.setAlpha(fFloatValue);
                        }
                        break;
                    case 1:
                        xVar.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    case 2:
                        xVar.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view2 = xVar.f19158b;
                        if (view2 != null) {
                            view2.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup4 = xVar.c;
                        if (viewGroup4 != null) {
                            viewGroup4.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup5 = xVar.f19159e;
                        if (viewGroup5 != null) {
                            viewGroup5.setAlpha(fFloatValue2);
                        }
                        break;
                }
            }
        });
        valueAnimatorOfFloat2.addListener(new v(this, i13));
        Resources resources = sVar.getResources();
        float dimension = resources.getDimension(R.dimen.exo_styled_bottom_bar_height) - resources.getDimension(R.dimen.exo_styled_progress_bar_height);
        float dimension2 = resources.getDimension(R.dimen.exo_styled_bottom_bar_height);
        AnimatorSet animatorSet = new AnimatorSet();
        this.l = animatorSet;
        animatorSet.setDuration(250L);
        animatorSet.addListener(new w(this, sVar, i10));
        animatorSet.play(valueAnimatorOfFloat).with(d(viewFindViewById, 0.0f, dimension)).with(d(viewGroup, 0.0f, dimension));
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.f19166m = animatorSet2;
        animatorSet2.setDuration(250L);
        animatorSet2.addListener(new w(this, sVar, i13));
        animatorSet2.play(d(viewFindViewById, dimension, dimension2)).with(d(viewGroup, dimension, dimension2));
        AnimatorSet animatorSet3 = new AnimatorSet();
        this.f19167n = animatorSet3;
        animatorSet3.setDuration(250L);
        animatorSet3.addListener(new w(this, sVar, i7));
        animatorSet3.play(valueAnimatorOfFloat).with(d(viewFindViewById, 0.0f, dimension2)).with(d(viewGroup, 0.0f, dimension2));
        AnimatorSet animatorSet4 = new AnimatorSet();
        this.o = animatorSet4;
        animatorSet4.setDuration(250L);
        animatorSet4.addListener(new v(this, i7));
        animatorSet4.play(valueAnimatorOfFloat2).with(d(viewFindViewById, dimension, 0.0f)).with(d(viewGroup, dimension, 0.0f));
        AnimatorSet animatorSet5 = new AnimatorSet();
        this.f19168p = animatorSet5;
        animatorSet5.setDuration(250L);
        animatorSet5.addListener(new v(this, 3));
        animatorSet5.play(valueAnimatorOfFloat2).with(d(viewFindViewById, dimension2, 0.0f)).with(d(viewGroup, dimension2, 0.0f));
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f19169q = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.setDuration(250L);
        valueAnimatorOfFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: m3.u

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ x f19150b;

            {
                this.f19150b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i14 = i13;
                x xVar = this.f19150b;
                xVar.getClass();
                switch (i14) {
                    case 0:
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view = xVar.f19158b;
                        if (view != null) {
                            view.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup2 = xVar.c;
                        if (viewGroup2 != null) {
                            viewGroup2.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup3 = xVar.f19159e;
                        if (viewGroup3 != null) {
                            viewGroup3.setAlpha(fFloatValue);
                        }
                        break;
                    case 1:
                        xVar.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    case 2:
                        xVar.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view2 = xVar.f19158b;
                        if (view2 != null) {
                            view2.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup4 = xVar.c;
                        if (viewGroup4 != null) {
                            viewGroup4.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup5 = xVar.f19159e;
                        if (viewGroup5 != null) {
                            viewGroup5.setAlpha(fFloatValue2);
                        }
                        break;
                }
            }
        });
        valueAnimatorOfFloat3.addListener(new v(this, 4));
        ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.f19170r = valueAnimatorOfFloat4;
        valueAnimatorOfFloat4.setDuration(250L);
        valueAnimatorOfFloat4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: m3.u

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ x f19150b;

            {
                this.f19150b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i14 = i7;
                x xVar = this.f19150b;
                xVar.getClass();
                switch (i14) {
                    case 0:
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view = xVar.f19158b;
                        if (view != null) {
                            view.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup2 = xVar.c;
                        if (viewGroup2 != null) {
                            viewGroup2.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup3 = xVar.f19159e;
                        if (viewGroup3 != null) {
                            viewGroup3.setAlpha(fFloatValue);
                        }
                        break;
                    case 1:
                        xVar.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    case 2:
                        xVar.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view2 = xVar.f19158b;
                        if (view2 != null) {
                            view2.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup4 = xVar.c;
                        if (viewGroup4 != null) {
                            viewGroup4.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup5 = xVar.f19159e;
                        if (viewGroup5 != null) {
                            viewGroup5.setAlpha(fFloatValue2);
                        }
                        break;
                }
            }
        });
        valueAnimatorOfFloat4.addListener(new v(this, 5));
    }

    public static int c(View view) {
        if (view == null) {
            return 0;
        }
        int width = view.getWidth();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return width;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return width + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
    }

    public static ObjectAnimator d(View view, float f10, float f11) {
        return ObjectAnimator.ofFloat(view, "translationY", f10, f11);
    }

    public static boolean j(View view) {
        int id = view.getId();
        return id == R.id.exo_bottom_bar || id == R.id.exo_prev || id == R.id.exo_next || id == R.id.exo_rew || id == R.id.exo_rew_with_amount || id == R.id.exo_ffwd || id == R.id.exo_ffwd_with_amount;
    }

    public final void a(float f10) {
        ViewGroup viewGroup = this.f19162h;
        if (viewGroup != null) {
            viewGroup.setTranslationX((int) ((1.0f - f10) * viewGroup.getWidth()));
        }
        ViewGroup viewGroup2 = this.f19163i;
        if (viewGroup2 != null) {
            viewGroup2.setAlpha(1.0f - f10);
        }
        ViewGroup viewGroup3 = this.f19160f;
        if (viewGroup3 != null) {
            viewGroup3.setAlpha(1.0f - f10);
        }
    }

    public final boolean b(View view) {
        return view != null && this.f19177y.contains(view);
    }

    public final void e(Runnable runnable, long j3) {
        if (j3 >= 0) {
            this.f19157a.postDelayed(runnable, j3);
        }
    }

    public final void f() {
        t tVar = this.f19175w;
        s sVar = this.f19157a;
        sVar.removeCallbacks(tVar);
        sVar.removeCallbacks(this.f19172t);
        sVar.removeCallbacks(this.f19174v);
        sVar.removeCallbacks(this.f19173u);
    }

    public final void g() {
        if (this.f19178z == 3) {
            return;
        }
        f();
        int showTimeoutMs = this.f19157a.getShowTimeoutMs();
        if (showTimeoutMs > 0) {
            if (!this.f19156C) {
                e(this.f19175w, showTimeoutMs);
            } else if (this.f19178z == 1) {
                e(this.f19173u, 2000L);
            } else {
                e(this.f19174v, showTimeoutMs);
            }
        }
    }

    public final void h(View view, boolean z6) {
        if (view == null) {
            return;
        }
        ArrayList arrayList = this.f19177y;
        if (!z6) {
            view.setVisibility(8);
            arrayList.remove(view);
            return;
        }
        if (this.f19155A && j(view)) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
        arrayList.add(view);
    }

    public final void i(int i7) {
        int i10 = this.f19178z;
        this.f19178z = i7;
        s sVar = this.f19157a;
        if (i7 == 2) {
            sVar.setVisibility(8);
        } else if (i10 == 2) {
            sVar.setVisibility(0);
        }
        if (i10 != i7) {
            for (r rVar : sVar.o) {
                sVar.getVisibility();
                z zVar = (z) rVar;
                zVar.getClass();
                zVar.f19181n.l();
            }
        }
    }

    public final void k() {
        if (!this.f19156C) {
            i(0);
            g();
            return;
        }
        int i7 = this.f19178z;
        if (i7 == 1) {
            this.o.start();
        } else if (i7 == 2) {
            this.f19168p.start();
        } else if (i7 == 3) {
            this.B = true;
        } else if (i7 == 4) {
            return;
        }
        g();
    }
}
