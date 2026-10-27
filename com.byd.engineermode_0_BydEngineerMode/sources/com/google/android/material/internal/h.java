package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* JADX INFO: compiled from: StateListAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<b> f2821a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f2822b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ValueAnimator f2823c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f2824d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            if (hVar.f2823c == animator) {
                hVar.f2823c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f2826a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ValueAnimator f2827b;

        b(int[] iArr, ValueAnimator valueAnimator) {
            this.f2826a = iArr;
            this.f2827b = valueAnimator;
        }
    }

    private void b() {
        ValueAnimator valueAnimator = this.f2823c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f2823c = null;
        }
    }

    private void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f2827b;
        this.f2823c = valueAnimator;
        valueAnimator.start();
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f2824d);
        this.f2821a.add(bVar);
    }

    public void c() {
        ValueAnimator valueAnimator = this.f2823c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f2823c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f2821a.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                bVar = null;
                break;
            }
            bVar = this.f2821a.get(i);
            if (StateSet.stateSetMatches(bVar.f2826a, iArr)) {
                break;
            } else {
                i++;
            }
        }
        b bVar2 = this.f2822b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f2822b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }
}
