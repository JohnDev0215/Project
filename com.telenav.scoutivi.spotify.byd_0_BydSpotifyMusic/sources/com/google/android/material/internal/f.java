package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import java.util.ArrayList;

/* JADX INFO: compiled from: StateListAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<b> f4636a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f4637b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ValueAnimator f4638c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f4639d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f fVar = f.this;
            if (fVar.f4638c == animator) {
                fVar.f4638c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f4641a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ValueAnimator f4642b;

        b(int[] iArr, ValueAnimator valueAnimator) {
            this.f4641a = iArr;
            this.f4642b = valueAnimator;
        }
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f4639d);
        this.f4636a.add(bVar);
    }
}
