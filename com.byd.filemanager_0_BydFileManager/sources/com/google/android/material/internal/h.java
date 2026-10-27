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
    private final ArrayList<b> f4319a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f4320b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ValueAnimator f4321c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f4322d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            if (hVar.f4321c == animator) {
                hVar.f4321c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f4324a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ValueAnimator f4325b;

        b(int[] iArr, ValueAnimator valueAnimator) {
            this.f4324a = iArr;
            this.f4325b = valueAnimator;
        }
    }

    private void b() {
        ValueAnimator valueAnimator = this.f4321c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f4321c = null;
        }
    }

    private void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f4325b;
        this.f4321c = valueAnimator;
        valueAnimator.start();
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f4322d);
        this.f4319a.add(bVar);
    }

    public void c() {
        ValueAnimator valueAnimator = this.f4321c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f4321c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f4319a.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                bVar = null;
                break;
            }
            bVar = this.f4319a.get(i);
            if (StateSet.stateSetMatches(bVar.f4324a, iArr)) {
                break;
            } else {
                i++;
            }
        }
        b bVar2 = this.f4320b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f4320b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }
}
