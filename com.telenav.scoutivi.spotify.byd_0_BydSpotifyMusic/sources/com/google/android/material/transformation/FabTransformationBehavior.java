package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import i0.u;
import java.util.ArrayList;
import java.util.List;
import o2.f;
import p2.h;
import p2.i;
import p2.j;
import s2.d;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Rect f4984c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final RectF f4985d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final RectF f4986e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int[] f4987f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f4988g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f4989h;

    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f4990a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f4991b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f4992c;

        a(boolean z6, View view, View view2) {
            this.f4990a = z6;
            this.f4991b = view;
            this.f4992c = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f4990a) {
                return;
            }
            this.f4991b.setVisibility(4);
            this.f4992c.setAlpha(1.0f);
            this.f4992c.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.f4990a) {
                this.f4991b.setVisibility(0);
                this.f4992c.setAlpha(0.0f);
                this.f4992c.setVisibility(4);
            }
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f4994a;

        b(View view) {
            this.f4994a = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f4994a.invalidate();
        }
    }

    class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ s2.d f4996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Drawable f4997b;

        c(s2.d dVar, Drawable drawable) {
            this.f4996a = dVar;
            this.f4997b = drawable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f4996a.setCircularRevealOverlayDrawable(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f4996a.setCircularRevealOverlayDrawable(this.f4997b);
        }
    }

    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ s2.d f4999a;

        d(s2.d dVar) {
            this.f4999a = dVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.e revealInfo = this.f4999a.getRevealInfo();
            revealInfo.f9506c = Float.MAX_VALUE;
            this.f4999a.setRevealInfo(revealInfo);
        }
    }

    protected static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f5001a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public j f5002b;

        protected e() {
        }
    }

    public FabTransformationBehavior() {
        this.f4984c = new Rect();
        this.f4985d = new RectF();
        this.f4986e = new RectF();
        this.f4987f = new int[2];
    }

    private ViewGroup K(View view) {
        View viewFindViewById = view.findViewById(f.mtrl_child_content_container);
        return viewFindViewById != null ? f0(viewFindViewById) : ((view instanceof g3.b) || (view instanceof g3.a)) ? f0(((ViewGroup) view).getChildAt(0)) : f0(view);
    }

    private void L(View view, e eVar, i iVar, i iVar2, float f7, float f8, float f9, float f10, RectF rectF) {
        float fS = S(eVar, iVar, f7, f9);
        float fS2 = S(eVar, iVar2, f8, f10);
        Rect rect = this.f4984c;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.f4985d;
        rectF2.set(rect);
        RectF rectF3 = this.f4986e;
        T(view, rectF3);
        rectF3.offset(fS, fS2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private void M(View view, RectF rectF) {
        T(view, rectF);
        rectF.offset(this.f4988g, this.f4989h);
    }

    private Pair<i, i> N(float f7, float f8, boolean z6, e eVar) {
        i iVarE;
        i iVarE2;
        if (f7 == 0.0f || f8 == 0.0f) {
            iVarE = eVar.f5001a.e("translationXLinear");
            iVarE2 = eVar.f5001a.e("translationYLinear");
        } else if ((!z6 || f8 >= 0.0f) && (z6 || f8 <= 0.0f)) {
            iVarE = eVar.f5001a.e("translationXCurveDownwards");
            iVarE2 = eVar.f5001a.e("translationYCurveDownwards");
        } else {
            iVarE = eVar.f5001a.e("translationXCurveUpwards");
            iVarE2 = eVar.f5001a.e("translationYCurveUpwards");
        }
        return new Pair<>(iVarE, iVarE2);
    }

    private float O(View view, View view2, j jVar) {
        RectF rectF = this.f4985d;
        RectF rectF2 = this.f4986e;
        M(view, rectF);
        T(view2, rectF2);
        rectF2.offset(-Q(view, view2, jVar), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    private float P(View view, View view2, j jVar) {
        RectF rectF = this.f4985d;
        RectF rectF2 = this.f4986e;
        M(view, rectF);
        T(view2, rectF2);
        rectF2.offset(0.0f, -R(view, view2, jVar));
        return rectF.centerY() - rectF2.top;
    }

    private float Q(View view, View view2, j jVar) {
        float fCenterX;
        float fCenterX2;
        float f7;
        RectF rectF = this.f4985d;
        RectF rectF2 = this.f4986e;
        M(view, rectF);
        T(view2, rectF2);
        int i7 = jVar.f8840a & 7;
        if (i7 == 1) {
            fCenterX = rectF2.centerX();
            fCenterX2 = rectF.centerX();
        } else if (i7 == 3) {
            fCenterX = rectF2.left;
            fCenterX2 = rectF.left;
        } else {
            if (i7 != 5) {
                f7 = 0.0f;
                return f7 + jVar.f8841b;
            }
            fCenterX = rectF2.right;
            fCenterX2 = rectF.right;
        }
        f7 = fCenterX - fCenterX2;
        return f7 + jVar.f8841b;
    }

    private float R(View view, View view2, j jVar) {
        float fCenterY;
        float fCenterY2;
        float f7;
        RectF rectF = this.f4985d;
        RectF rectF2 = this.f4986e;
        M(view, rectF);
        T(view2, rectF2);
        int i7 = jVar.f8840a & 112;
        if (i7 == 16) {
            fCenterY = rectF2.centerY();
            fCenterY2 = rectF.centerY();
        } else if (i7 == 48) {
            fCenterY = rectF2.top;
            fCenterY2 = rectF.top;
        } else {
            if (i7 != 80) {
                f7 = 0.0f;
                return f7 + jVar.f8842c;
            }
            fCenterY = rectF2.bottom;
            fCenterY2 = rectF.bottom;
        }
        f7 = fCenterY - fCenterY2;
        return f7 + jVar.f8842c;
    }

    private float S(e eVar, i iVar, float f7, float f8) {
        long jC = iVar.c();
        long jD = iVar.d();
        i iVarE = eVar.f5001a.e("expansion");
        return p2.a.a(f7, f8, iVar.e().getInterpolation((((iVarE.c() + iVarE.d()) + 17) - jC) / jD));
    }

    private void T(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f4987f);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    private void U(View view, View view2, boolean z6, boolean z7, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ViewGroup viewGroupK;
        ObjectAnimator objectAnimatorOfFloat;
        if (view2 instanceof ViewGroup) {
            if (((view2 instanceof s2.d) && s2.c.f9499a == 0) || (viewGroupK = K(view2)) == null) {
                return;
            }
            if (z6) {
                if (!z7) {
                    p2.d.f8826a.set(viewGroupK, Float.valueOf(0.0f));
                }
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupK, p2.d.f8826a, 1.0f);
            } else {
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupK, p2.d.f8826a, 0.0f);
            }
            eVar.f5001a.e("contentFade").a(objectAnimatorOfFloat);
            list.add(objectAnimatorOfFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void V(View view, View view2, boolean z6, boolean z7, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if (view2 instanceof s2.d) {
            s2.d dVar = (s2.d) view2;
            int iD0 = d0(view);
            int i7 = 16777215 & iD0;
            if (z6) {
                if (!z7) {
                    dVar.setCircularRevealScrimColor(iD0);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(dVar, d.C0146d.f9503a, i7);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(dVar, d.C0146d.f9503a, iD0);
            }
            objectAnimatorOfInt.setEvaluator(p2.c.b());
            eVar.f5001a.e("color").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
        }
    }

    private void W(View view, View view2, boolean z6, e eVar, List<Animator> list) {
        float fQ = Q(view, view2, eVar.f5002b);
        float fR = R(view, view2, eVar.f5002b);
        Pair<i, i> pairN = N(fQ, fR, z6, eVar);
        i iVar = (i) pairN.first;
        i iVar2 = (i) pairN.second;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        if (!z6) {
            fQ = this.f4988g;
        }
        fArr[0] = fQ;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
        Property property2 = View.TRANSLATION_Y;
        float[] fArr2 = new float[1];
        if (!z6) {
            fR = this.f4989h;
        }
        fArr2[0] = fR;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fArr2);
        iVar.a(objectAnimatorOfFloat);
        iVar2.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    @TargetApi(21)
    private void X(View view, View view2, boolean z6, boolean z7, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        float fT = u.t(view2) - u.t(view);
        if (z6) {
            if (!z7) {
                view2.setTranslationZ(-fT);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -fT);
        }
        eVar.f5001a.e("elevation").a(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void Y(View view, View view2, boolean z6, boolean z7, e eVar, float f7, float f8, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animatorA;
        if (view2 instanceof s2.d) {
            s2.d dVar = (s2.d) view2;
            float fO = O(view, view2, eVar.f5002b);
            float fP = P(view, view2, eVar.f5002b);
            ((FloatingActionButton) view).i(this.f4984c);
            float fWidth = this.f4984c.width() / 2.0f;
            i iVarE = eVar.f5001a.e("expansion");
            if (z6) {
                if (!z7) {
                    dVar.setRevealInfo(new d.e(fO, fP, fWidth));
                }
                if (z7) {
                    fWidth = dVar.getRevealInfo().f9506c;
                }
                animatorA = s2.a.a(dVar, fO, fP, z2.a.b(fO, fP, 0.0f, 0.0f, f7, f8));
                animatorA.addListener(new d(dVar));
                b0(view2, iVarE.c(), (int) fO, (int) fP, fWidth, list);
            } else {
                float f9 = dVar.getRevealInfo().f9506c;
                Animator animatorA2 = s2.a.a(dVar, fO, fP, fWidth);
                int i7 = (int) fO;
                int i8 = (int) fP;
                b0(view2, iVarE.c(), i7, i8, f9, list);
                a0(view2, iVarE.c(), iVarE.d(), eVar.f5001a.f(), i7, i8, fWidth, list);
                animatorA = animatorA2;
            }
            iVarE.a(animatorA);
            list.add(animatorA);
            list2.add(s2.a.b(dVar));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void Z(View view, View view2, boolean z6, boolean z7, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if ((view2 instanceof s2.d) && (view instanceof ImageView)) {
            s2.d dVar = (s2.d) view2;
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z6) {
                if (!z7) {
                    drawable.setAlpha(255);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, p2.e.f8827b, 0);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, p2.e.f8827b, 255);
            }
            objectAnimatorOfInt.addUpdateListener(new b(view2));
            eVar.f5001a.e("iconFade").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
            list2.add(new c(dVar, drawable));
        }
    }

    private void a0(View view, long j7, long j8, long j9, int i7, int i8, float f7, List<Animator> list) {
        long j10 = j7 + j8;
        if (j10 < j9) {
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i7, i8, f7, f7);
            animatorCreateCircularReveal.setStartDelay(j10);
            animatorCreateCircularReveal.setDuration(j9 - j10);
            list.add(animatorCreateCircularReveal);
        }
    }

    private void b0(View view, long j7, int i7, int i8, float f7, List<Animator> list) {
        if (j7 > 0) {
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i7, i8, f7, f7);
            animatorCreateCircularReveal.setStartDelay(0L);
            animatorCreateCircularReveal.setDuration(j7);
            list.add(animatorCreateCircularReveal);
        }
    }

    private void c0(View view, View view2, boolean z6, boolean z7, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        float fQ = Q(view, view2, eVar.f5002b);
        float fR = R(view, view2, eVar.f5002b);
        Pair<i, i> pairN = N(fQ, fR, z6, eVar);
        i iVar = (i) pairN.first;
        i iVar2 = (i) pairN.second;
        if (z6) {
            if (!z7) {
                view2.setTranslationX(-fQ);
                view2.setTranslationY(-fR);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, 0.0f);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, 0.0f);
            L(view2, eVar, iVar, iVar2, -fQ, -fR, 0.0f, 0.0f, rectF);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, -fQ);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, -fR);
        }
        iVar.a(objectAnimatorOfFloat);
        iVar2.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    private int d0(View view) {
        ColorStateList colorStateListP = u.p(view);
        if (colorStateListP != null) {
            return colorStateListP.getColorForState(view.getDrawableState(), colorStateListP.getDefaultColor());
        }
        return 0;
    }

    private ViewGroup f0(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected AnimatorSet J(View view, View view2, boolean z6, boolean z7) {
        e eVarE0 = e0(view2.getContext(), z6);
        if (z6) {
            this.f4988g = view.getTranslationX();
            this.f4989h = view.getTranslationY();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        X(view, view2, z6, z7, eVarE0, arrayList, arrayList2);
        RectF rectF = this.f4985d;
        c0(view, view2, z6, z7, eVarE0, arrayList, arrayList2, rectF);
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        W(view, view2, z6, eVarE0, arrayList);
        Z(view, view2, z6, z7, eVarE0, arrayList, arrayList2);
        Y(view, view2, z6, z7, eVarE0, fWidth, fHeight, arrayList, arrayList2);
        V(view, view2, z6, z7, eVarE0, arrayList, arrayList2);
        U(view, view2, z6, z7, eVarE0, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        p2.b.a(animatorSet, arrayList);
        animatorSet.addListener(new a(z6, view2, view));
        int size = arrayList2.size();
        for (int i7 = 0; i7 < size; i7++) {
            animatorSet.addListener(arrayList2.get(i7));
        }
        return animatorSet;
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean e(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    protected abstract e e0(Context context, boolean z6);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void g(CoordinatorLayout.f fVar) {
        if (fVar.f1931h == 0) {
            fVar.f1931h = 80;
        }
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4984c = new Rect();
        this.f4985d = new RectF();
        this.f4986e = new RectF();
        this.f4987f = new int[2];
    }
}
