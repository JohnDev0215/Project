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
    private final ArrayList<b> f3733a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f3734b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ValueAnimator f3735c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f3736d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            if (hVar.f3735c == animator) {
                hVar.f3735c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f3738a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ValueAnimator f3739b;

        b(int[] iArr, ValueAnimator valueAnimator) {
            this.f3738a = iArr;
            this.f3739b = valueAnimator;
        }
    }

    private void b() {
        ValueAnimator valueAnimator = this.f3735c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f3735c = null;
        }
    }

    private void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f3739b;
        this.f3735c = valueAnimator;
        valueAnimator.start();
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f3736d);
        this.f3733a.add(bVar);
    }

    public void c() {
        ValueAnimator valueAnimator = this.f3735c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f3735c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f3733a.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                bVar = null;
                break;
            }
            bVar = this.f3733a.get(i);
            if (StateSet.stateSetMatches(bVar.f3738a, iArr)) {
                break;
            } else {
                i++;
            }
        }
        b bVar2 = this.f3734b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f3734b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }
}
