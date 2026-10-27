package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.view.View;
import androidx.transition.ChangeTransform;
import defpackage.in3;
import defpackage.om3;
import defpackage.wn3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ChangeTransform.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends AnimatorListenerAdapter {
    public boolean e;
    public final Matrix f = new Matrix();
    public final /* synthetic */ boolean g;
    public final /* synthetic */ Matrix h;
    public final /* synthetic */ View i;
    public final /* synthetic */ ChangeTransform.e j;
    public final /* synthetic */ ChangeTransform.d k;
    public final /* synthetic */ ChangeTransform l;

    public a(ChangeTransform changeTransform, boolean z, Matrix matrix, View view, ChangeTransform.e eVar, ChangeTransform.d dVar) {
        this.l = changeTransform;
        this.g = z;
        this.h = matrix;
        this.i = view;
        this.j = eVar;
        this.k = dVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.e = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        boolean z = this.e;
        ChangeTransform.e eVar = this.j;
        View view = this.i;
        if (!z) {
            if (this.g && this.l.C) {
                Matrix matrix = this.f;
                matrix.set(this.h);
                view.setTag(R$id.transition_transform, matrix);
                eVar.getClass();
                String[] strArr = ChangeTransform.F;
                view.setTranslationX(eVar.a);
                view.setTranslationY(eVar.b);
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.i.o(view, eVar.c);
                view.setScaleX(eVar.d);
                view.setScaleY(eVar.e);
                view.setRotationX(eVar.f);
                view.setRotationY(eVar.g);
                view.setRotation(eVar.h);
            } else {
                view.setTag(R$id.transition_transform, null);
                view.setTag(R$id.parent_matrix, null);
            }
        }
        wn3.a aVar = wn3.a;
        view.setAnimationMatrix(null);
        eVar.getClass();
        String[] strArr2 = ChangeTransform.F;
        view.setTranslationX(eVar.a);
        view.setTranslationY(eVar.b);
        WeakHashMap<View, in3> weakHashMap2 = om3.a;
        om3.i.o(view, eVar.c);
        view.setScaleX(eVar.d);
        view.setScaleY(eVar.e);
        view.setRotationX(eVar.f);
        view.setRotationY(eVar.g);
        view.setRotation(eVar.h);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        Matrix matrix = this.k.a;
        Matrix matrix2 = this.f;
        matrix2.set(matrix);
        int i = R$id.transition_transform;
        View view = this.i;
        view.setTag(i, matrix2);
        ChangeTransform.e eVar = this.j;
        eVar.getClass();
        String[] strArr = ChangeTransform.F;
        view.setTranslationX(eVar.a);
        view.setTranslationY(eVar.b);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.o(view, eVar.c);
        view.setScaleX(eVar.d);
        view.setScaleY(eVar.e);
        view.setRotationX(eVar.f);
        view.setRotationY(eVar.g);
        view.setRotation(eVar.h);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        String[] strArr = ChangeTransform.F;
        View view = this.i;
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.o(view, 0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
    }
}
