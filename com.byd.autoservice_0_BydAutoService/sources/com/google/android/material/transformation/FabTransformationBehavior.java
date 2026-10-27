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
import android.os.Build;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b2.c;
import com.bydux.api.common.views.skin.entity.SkinAttr;
import com.google.android.material.R$id;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import l0.t;
import s1.h;
import s1.i;
import s1.j;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f2598c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RectF f2599d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f2600e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f2601f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2602g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f2603h;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f2604a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2605b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f2606c;

        public a(boolean z3, View view, View view2) {
            this.f2604a = z3;
            this.f2605b = view;
            this.f2606c = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f2604a) {
                return;
            }
            this.f2605b.setVisibility(4);
            this.f2606c.setAlpha(1.0f);
            this.f2606c.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.f2604a) {
                this.f2605b.setVisibility(0);
                this.f2606c.setAlpha(0.0f);
                this.f2606c.setVisibility(4);
            }
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f2608a;

        public b(View view) {
            this.f2608a = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f2608a.invalidate();
        }
    }

    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b2.c f2610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Drawable f2611b;

        public c(b2.c cVar, Drawable drawable) {
            this.f2610a = cVar;
            this.f2611b = drawable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2610a.setCircularRevealOverlayDrawable(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2610a.setCircularRevealOverlayDrawable(this.f2611b);
        }
    }

    public class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b2.c f2613a;

        public d(b2.c cVar) {
            this.f2613a = cVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c.e revealInfo = this.f2613a.getRevealInfo();
            revealInfo.f1777c = Float.MAX_VALUE;
            this.f2613a.setRevealInfo(revealInfo);
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f2615a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public j f2616b;
    }

    public FabTransformationBehavior() {
        this.f2598c = new Rect();
        this.f2599d = new RectF();
        this.f2600e = new RectF();
        this.f2601f = new int[2];
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public AnimatorSet J(View view, View view2, boolean z3, boolean z4) {
        e eVarE0 = e0(view2.getContext(), z3);
        if (z3) {
            this.f2602g = view.getTranslationX();
            this.f2603h = view.getTranslationY();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (Build.VERSION.SDK_INT >= 21) {
            X(view, view2, z3, z4, eVarE0, arrayList, arrayList2);
        }
        RectF rectF = this.f2599d;
        c0(view, view2, z3, z4, eVarE0, arrayList, arrayList2, rectF);
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        W(view, view2, z3, eVarE0, arrayList);
        Z(view, view2, z3, z4, eVarE0, arrayList, arrayList2);
        Y(view, view2, z3, z4, eVarE0, fWidth, fHeight, arrayList, arrayList2);
        V(view, view2, z3, z4, eVarE0, arrayList, arrayList2);
        U(view, view2, z3, z4, eVarE0, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        s1.b.a(animatorSet, arrayList);
        animatorSet.addListener(new a(z3, view2, view));
        int size = arrayList2.size();
        for (int i3 = 0; i3 < size; i3++) {
            animatorSet.addListener(arrayList2.get(i3));
        }
        return animatorSet;
    }

    public final ViewGroup K(View view) {
        View viewFindViewById = view.findViewById(R$id.mtrl_child_content_container);
        return viewFindViewById != null ? f0(viewFindViewById) : ((view instanceof TransformationChildLayout) || (view instanceof TransformationChildCard)) ? f0(((ViewGroup) view).getChildAt(0)) : f0(view);
    }

    public final void L(View view, e eVar, i iVar, i iVar2, float f3, float f4, float f5, float f6, RectF rectF) {
        float fS = S(eVar, iVar, f3, f5);
        float fS2 = S(eVar, iVar2, f4, f6);
        Rect rect = this.f2598c;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.f2599d;
        rectF2.set(rect);
        RectF rectF3 = this.f2600e;
        T(view, rectF3);
        rectF3.offset(fS, fS2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    public final void M(View view, RectF rectF) {
        T(view, rectF);
        rectF.offset(this.f2602g, this.f2603h);
    }

    public final Pair<i, i> N(float f3, float f4, boolean z3, e eVar) {
        i iVarH;
        i iVarH2;
        if (f3 == 0.0f || f4 == 0.0f) {
            iVarH = eVar.f2615a.h("translationXLinear");
            iVarH2 = eVar.f2615a.h("translationYLinear");
        } else if ((!z3 || f4 >= 0.0f) && (z3 || f4 <= 0.0f)) {
            iVarH = eVar.f2615a.h("translationXCurveDownwards");
            iVarH2 = eVar.f2615a.h("translationYCurveDownwards");
        } else {
            iVarH = eVar.f2615a.h("translationXCurveUpwards");
            iVarH2 = eVar.f2615a.h("translationYCurveUpwards");
        }
        return new Pair<>(iVarH, iVarH2);
    }

    public final float O(View view, View view2, j jVar) {
        RectF rectF = this.f2599d;
        RectF rectF2 = this.f2600e;
        M(view, rectF);
        T(view2, rectF2);
        rectF2.offset(-Q(view, view2, jVar), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    public final float P(View view, View view2, j jVar) {
        RectF rectF = this.f2599d;
        RectF rectF2 = this.f2600e;
        M(view, rectF);
        T(view2, rectF2);
        rectF2.offset(0.0f, -R(view, view2, jVar));
        return rectF.centerY() - rectF2.top;
    }

    public final float Q(View view, View view2, j jVar) {
        float fCenterX;
        float fCenterX2;
        float f3;
        RectF rectF = this.f2599d;
        RectF rectF2 = this.f2600e;
        M(view, rectF);
        T(view2, rectF2);
        int i3 = jVar.f4921a & 7;
        if (i3 == 1) {
            fCenterX = rectF2.centerX();
            fCenterX2 = rectF.centerX();
        } else if (i3 == 3) {
            fCenterX = rectF2.left;
            fCenterX2 = rectF.left;
        } else {
            if (i3 != 5) {
                f3 = 0.0f;
                return f3 + jVar.f4922b;
            }
            fCenterX = rectF2.right;
            fCenterX2 = rectF.right;
        }
        f3 = fCenterX - fCenterX2;
        return f3 + jVar.f4922b;
    }

    public final float R(View view, View view2, j jVar) {
        float fCenterY;
        float fCenterY2;
        float f3;
        RectF rectF = this.f2599d;
        RectF rectF2 = this.f2600e;
        M(view, rectF);
        T(view2, rectF2);
        int i3 = jVar.f4921a & 112;
        if (i3 == 16) {
            fCenterY = rectF2.centerY();
            fCenterY2 = rectF.centerY();
        } else if (i3 == 48) {
            fCenterY = rectF2.top;
            fCenterY2 = rectF.top;
        } else {
            if (i3 != 80) {
                f3 = 0.0f;
                return f3 + jVar.f4923c;
            }
            fCenterY = rectF2.bottom;
            fCenterY2 = rectF.bottom;
        }
        f3 = fCenterY - fCenterY2;
        return f3 + jVar.f4923c;
    }

    public final float S(e eVar, i iVar, float f3, float f4) {
        long jC = iVar.c();
        long jD = iVar.d();
        i iVarH = eVar.f2615a.h("expansion");
        return s1.a.a(f3, f4, iVar.e().getInterpolation((((iVarH.c() + iVarH.d()) + 17) - jC) / jD));
    }

    public final void T(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f2601f);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    public final void U(View view, View view2, boolean z3, boolean z4, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ViewGroup viewGroupK;
        ObjectAnimator objectAnimatorOfFloat;
        if (view2 instanceof ViewGroup) {
            if (((view2 instanceof b2.c) && b2.b.f1761a == 0) || (viewGroupK = K(view2)) == null) {
                return;
            }
            if (z3) {
                if (!z4) {
                    s1.d.f4907a.set(viewGroupK, Float.valueOf(0.0f));
                }
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupK, s1.d.f4907a, 1.0f);
            } else {
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupK, s1.d.f4907a, 0.0f);
            }
            eVar.f2615a.h("contentFade").a(objectAnimatorOfFloat);
            list.add(objectAnimatorOfFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V(View view, View view2, boolean z3, boolean z4, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if (view2 instanceof b2.c) {
            b2.c cVar = (b2.c) view2;
            int iD0 = d0(view);
            int i3 = 16777215 & iD0;
            if (z3) {
                if (!z4) {
                    cVar.setCircularRevealScrimColor(iD0);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(cVar, c.d.f1774a, i3);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(cVar, c.d.f1774a, iD0);
            }
            objectAnimatorOfInt.setEvaluator(s1.c.b());
            eVar.f2615a.h(SkinAttr.RES_TYPE_NAME_COLOR).a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
        }
    }

    public final void W(View view, View view2, boolean z3, e eVar, List<Animator> list) {
        float fQ = Q(view, view2, eVar.f2616b);
        float fR = R(view, view2, eVar.f2616b);
        Pair<i, i> pairN = N(fQ, fR, z3, eVar);
        i iVar = (i) pairN.first;
        i iVar2 = (i) pairN.second;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        if (!z3) {
            fQ = this.f2602g;
        }
        fArr[0] = fQ;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
        Property property2 = View.TRANSLATION_Y;
        float[] fArr2 = new float[1];
        if (!z3) {
            fR = this.f2603h;
        }
        fArr2[0] = fR;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fArr2);
        iVar.a(objectAnimatorOfFloat);
        iVar2.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    @TargetApi(21)
    public final void X(View view, View view2, boolean z3, boolean z4, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        float fU = t.u(view2) - t.u(view);
        if (z3) {
            if (!z4) {
                view2.setTranslationZ(-fU);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -fU);
        }
        eVar.f2615a.h("elevation").a(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Y(View view, View view2, boolean z3, boolean z4, e eVar, float f3, float f4, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animatorA;
        if (view2 instanceof b2.c) {
            b2.c cVar = (b2.c) view2;
            float fO = O(view, view2, eVar.f2616b);
            float fP = P(view, view2, eVar.f2616b);
            ((FloatingActionButton) view).i(this.f2598c);
            float fWidth = this.f2598c.width() / 2.0f;
            i iVarH = eVar.f2615a.h("expansion");
            if (z3) {
                if (!z4) {
                    cVar.setRevealInfo(new c.e(fO, fP, fWidth));
                }
                if (z4) {
                    fWidth = cVar.getRevealInfo().f1777c;
                }
                animatorA = b2.a.a(cVar, fO, fP, j2.a.b(fO, fP, 0.0f, 0.0f, f3, f4));
                animatorA.addListener(new d(cVar));
                b0(view2, iVarH.c(), (int) fO, (int) fP, fWidth, list);
            } else {
                float f5 = cVar.getRevealInfo().f1777c;
                Animator animatorA2 = b2.a.a(cVar, fO, fP, fWidth);
                int i3 = (int) fO;
                int i4 = (int) fP;
                b0(view2, iVarH.c(), i3, i4, f5, list);
                a0(view2, iVarH.c(), iVarH.d(), eVar.f2615a.i(), i3, i4, fWidth, list);
                animatorA = animatorA2;
            }
            iVarH.a(animatorA);
            list.add(animatorA);
            list2.add(b2.a.b(cVar));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Z(View view, View view2, boolean z3, boolean z4, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if ((view2 instanceof b2.c) && (view instanceof ImageView)) {
            b2.c cVar = (b2.c) view2;
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z3) {
                if (!z4) {
                    drawable.setAlpha(255);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, s1.e.f4908a, 0);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, s1.e.f4908a, 255);
            }
            objectAnimatorOfInt.addUpdateListener(new b(view2));
            eVar.f2615a.h("iconFade").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
            list2.add(new c(cVar, drawable));
        }
    }

    public final void a0(View view, long j3, long j4, long j5, int i3, int i4, float f3, List<Animator> list) {
        if (Build.VERSION.SDK_INT >= 21) {
            long j6 = j3 + j4;
            if (j6 < j5) {
                Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i3, i4, f3, f3);
                animatorCreateCircularReveal.setStartDelay(j6);
                animatorCreateCircularReveal.setDuration(j5 - j6);
                list.add(animatorCreateCircularReveal);
            }
        }
    }

    public final void b0(View view, long j3, int i3, int i4, float f3, List<Animator> list) {
        if (Build.VERSION.SDK_INT < 21 || j3 <= 0) {
            return;
        }
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i3, i4, f3, f3);
        animatorCreateCircularReveal.setStartDelay(0L);
        animatorCreateCircularReveal.setDuration(j3);
        list.add(animatorCreateCircularReveal);
    }

    public final void c0(View view, View view2, boolean z3, boolean z4, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        float fQ = Q(view, view2, eVar.f2616b);
        float fR = R(view, view2, eVar.f2616b);
        Pair<i, i> pairN = N(fQ, fR, z3, eVar);
        i iVar = (i) pairN.first;
        i iVar2 = (i) pairN.second;
        if (z3) {
            if (!z4) {
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

    public final int d0(View view) {
        ColorStateList colorStateListQ = t.q(view);
        if (colorStateListQ != null) {
            return colorStateListQ.getColorForState(view.getDrawableState(), colorStateListQ.getDefaultColor());
        }
        return 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
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

    public abstract e e0(Context context, boolean z3);

    public final ViewGroup f0(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void g(CoordinatorLayout.f fVar) {
        if (fVar.f1104h == 0) {
            fVar.f1104h = 80;
        }
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2598c = new Rect();
        this.f2599d = new RectF();
        this.f2600e = new RectF();
        this.f2601f = new int[2];
    }
}
