package org.chromium.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import defpackage.pc;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class LoadingView extends ProgressBar {
    public static boolean j;
    public long e;
    public final ArrayList f;
    public final a g;
    public boolean h;
    public final b i;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            LoadingView loadingView = LoadingView.this;
            if (loadingView.h) {
                loadingView.e = SystemClock.elapsedRealtime();
                loadingView.setVisibility(0);
                loadingView.setAlpha(1.0f);
                Iterator it = loadingView.f.iterator();
                while (it.hasNext()) {
                    ((c) it.next()).V0();
                }
            }
        }
    }

    public class b implements Runnable {

        public class a extends AnimatorListenerAdapter {
            public a() {
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                LoadingView loadingView = LoadingView.this;
                boolean z = LoadingView.j;
                loadingView.d();
            }
        }

        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z = LoadingView.j;
            LoadingView loadingView = LoadingView.this;
            if (z) {
                loadingView.d();
            } else {
                loadingView.animate().alpha(0.0f).setInterpolator(pc.c).setListener(new a());
            }
        }
    }

    public interface c {
        void M();

        void V0();
    }

    public LoadingView(Context context) {
        super(context);
        this.e = -1L;
        this.f = new ArrayList();
        this.g = new a();
        this.i = new b();
    }

    public static void setDisableAnimationForTest(boolean z) {
        j = z;
    }

    public final void a(c cVar) {
        this.f.add(cVar);
    }

    public final void b() {
        removeCallbacks(this.g);
        removeCallbacks(this.i);
        this.f.clear();
    }

    public final void c() {
        removeCallbacks(this.g);
        b bVar = this.i;
        removeCallbacks(bVar);
        this.h = false;
        if (getVisibility() == 0) {
            postDelayed(bVar, Math.max(0L, (this.e + 500) - SystemClock.elapsedRealtime()));
        } else {
            d();
        }
    }

    public final void d() {
        setVisibility(8);
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((c) it.next()).M();
        }
    }

    public final void e() {
        a aVar = this.g;
        removeCallbacks(aVar);
        removeCallbacks(this.i);
        this.h = true;
        setVisibility(8);
        postDelayed(aVar, 500L);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = -1L;
        this.f = new ArrayList();
        this.g = new a();
        this.i = new b();
    }
}
