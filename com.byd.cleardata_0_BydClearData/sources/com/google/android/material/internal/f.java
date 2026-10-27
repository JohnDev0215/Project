package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<b> f3137a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f3138b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ValueAnimator f3139c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f3140d = new a();

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f fVar = f.this;
            if (fVar.f3139c == animator) {
                fVar.f3139c = null;
            }
        }
    }

    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f3142a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ValueAnimator f3143b;

        b(int[] iArr, ValueAnimator valueAnimator) {
            this.f3142a = iArr;
            this.f3143b = valueAnimator;
        }
    }

    private void b() {
        ValueAnimator valueAnimator = this.f3139c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f3139c = null;
        }
    }

    private void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f3143b;
        this.f3139c = valueAnimator;
        valueAnimator.start();
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f3140d);
        this.f3137a.add(bVar);
    }

    public void c() {
        ValueAnimator valueAnimator = this.f3139c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f3139c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f3137a.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                bVar = null;
                break;
            }
            bVar = this.f3137a.get(i);
            if (StateSet.stateSetMatches(bVar.f3142a, iArr)) {
                break;
            } else {
                i++;
            }
        }
        b bVar2 = this.f3138b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f3138b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }
}
