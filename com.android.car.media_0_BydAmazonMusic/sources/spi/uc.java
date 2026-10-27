package spi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class uc {

    /* JADX INFO: compiled from: ViewUtils.java */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ Animator.AnimatorListener a;
        public final /* synthetic */ d b;
        public final /* synthetic */ View c;

        public a(Animator.AnimatorListener animatorListener, d dVar, View view) {
            this.a = animatorListener;
            this.b = dVar;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            this.a.onAnimationEnd(animator);
            d dVar = this.b;
            if (dVar != null) {
                ((q6) dVar).a(this.c);
            }
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ View a;
        public final /* synthetic */ d b;

        public b(View view, d dVar) {
            this.a = view;
            this.b = dVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            d dVar = this.b;
            if (dVar != null) {
                ((q6) dVar).a(this.a);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            this.a.setVisibility(0);
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface c {
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface d {
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<android.view.View> a(@androidx.annotation.NonNull android.view.View r5, @androidx.annotation.NonNull android.content.res.Resources r6, int r7, @androidx.annotation.Nullable spi.uc.c r8) {
        /*
            android.content.res.TypedArray r6 = r6.obtainTypedArray(r7)
            java.util.ArrayList r7 = new java.util.ArrayList
            int r0 = r6.length()
            r7.<init>(r0)
            r0 = 0
            r1 = r0
        Lf:
            int r2 = r6.length()
            if (r1 >= r2) goto L3c
            int r2 = r6.getResourceId(r1, r0)
            if (r2 == 0) goto L39
            android.view.View r2 = r5.findViewById(r2)
            if (r2 == 0) goto L39
            if (r8 == 0) goto L36
            r3 = r8
            spi.n8 r3 = (spi.n8) r3
            com.android.car.media.PlaybackFragment r3 = r3.a
            boolean r4 = r3.C
            if (r4 != 0) goto L33
            android.view.ViewGroup r3 = r3.k
            if (r2 == r3) goto L31
            goto L33
        L31:
            r3 = r0
            goto L34
        L33:
            r3 = 1
        L34:
            if (r3 == 0) goto L39
        L36:
            r7.add(r2)
        L39:
            int r1 = r1 + 1
            goto Lf
        L3c:
            r6.recycle()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.uc.a(android.view.View, android.content.res.Resources, int, spi.uc$c):java.util.List");
    }

    public static void b(@NonNull View view, int i, @Nullable d dVar) {
        view.animate().cancel();
        if (!view.isLaidOut()) {
            view.setVisibility(8);
        } else {
            view.animate().setDuration(i).setListener(new a(new vc(view), dVar, view)).alpha(0.0f);
        }
    }

    public static void c(@Nullable List<View> list, int i) {
        if (list == null) {
            return;
        }
        for (View view : list) {
            if (view != null) {
                b(view, i, null);
            }
        }
    }

    public static void d(@Nullable View view, FrameLayout frameLayout) {
        if (view == null) {
            if (frameLayout.getChildCount() != 0) {
                frameLayout.removeAllViews();
            }
            frameLayout.setVisibility(4);
        } else {
            if (frameLayout.getChildCount() == 1 && frameLayout.getChildAt(0) == view) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
            frameLayout.removeAllViews();
            frameLayout.addView(view);
            frameLayout.setVisibility(0);
        }
    }

    public static void e(@Nullable View view, boolean z) {
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    public static void f(@Nullable List<View> list, boolean z) {
        if (list == null) {
            return;
        }
        Iterator<View> it = list.iterator();
        while (it.hasNext()) {
            e(it.next(), z);
        }
    }

    public static void g(@NonNull View view, int i, @Nullable d dVar) {
        view.animate().cancel();
        view.animate().setDuration(i).setListener(new b(view, dVar)).alpha(1.0f);
    }

    public static void h(@Nullable List<View> list, int i) {
        if (list == null) {
            return;
        }
        for (View view : list) {
            if (view != null) {
                g(view, i, null);
            }
        }
    }
}
