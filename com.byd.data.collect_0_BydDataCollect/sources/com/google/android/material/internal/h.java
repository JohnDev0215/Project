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
    private final ArrayList<b> f4144a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f4145b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ValueAnimator f4146c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f4147d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            if (hVar.f4146c == animator) {
                hVar.f4146c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f4149a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ValueAnimator f4150b;

        b(int[] iArr, ValueAnimator valueAnimator) {
            this.f4149a = iArr;
            this.f4150b = valueAnimator;
        }
    }

    private void b() {
        ValueAnimator valueAnimator = this.f4146c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f4146c = null;
        }
    }

    private void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f4150b;
        this.f4146c = valueAnimator;
        valueAnimator.start();
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f4147d);
        this.f4144a.add(bVar);
    }

    public void c() {
        ValueAnimator valueAnimator = this.f4146c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f4146c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f4144a.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                bVar = null;
                break;
            }
            bVar = this.f4144a.get(i2);
            if (StateSet.stateSetMatches(bVar.f4149a, iArr)) {
                break;
            } else {
                i2++;
            }
        }
        b bVar2 = this.f4145b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f4145b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }
}
