package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.R$id;
import defpackage.om3;
import defpackage.pt3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.components.browser_ui.widget.InsetObserverView;

/* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ot3 {
    public final e a;

    /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
    public static abstract class b {
        public WindowInsets a;
        public final int b = 0;
    }

    /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
    public static class c extends e {
        public static final PathInterpolator e = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);
        public static final ik0 f = new ik0();
        public static final DecelerateInterpolator g = new DecelerateInterpolator();

        /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
        public static class a implements View.OnApplyWindowInsetsListener {
            public final b a;
            public pt3 b;

            /* JADX INFO: renamed from: ot3$c$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
            public class C0159a implements ValueAnimator.AnimatorUpdateListener {
                public final /* synthetic */ ot3 e;
                public final /* synthetic */ pt3 f;
                public final /* synthetic */ pt3 g;
                public final /* synthetic */ int h;
                public final /* synthetic */ View i;

                public C0159a(ot3 ot3Var, pt3 pt3Var, pt3 pt3Var2, int i, View view) {
                    this.e = ot3Var;
                    this.f = pt3Var;
                    this.g = pt3Var2;
                    this.h = i;
                    this.i = view;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    ot3 ot3Var = this.e;
                    ot3Var.a.d(animatedFraction);
                    float fB = ot3Var.a.b();
                    PathInterpolator pathInterpolator = c.e;
                    int i = Build.VERSION.SDK_INT;
                    pt3 pt3Var = this.f;
                    pt3.c bVar = i >= 30 ? new pt3.b(pt3Var) : new pt3.a(pt3Var);
                    for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                        if ((this.h & i2) == 0) {
                            bVar.c(i2, pt3Var.a(i2));
                        } else {
                            n01 n01VarA = pt3Var.a(i2);
                            n01 n01VarA2 = this.g.a(i2);
                            float f = 1.0f - fB;
                            bVar.c(i2, pt3.f(n01VarA, (int) (((double) ((n01VarA.a - n01VarA2.a) * f)) + 0.5d), (int) (((double) ((n01VarA.b - n01VarA2.b) * f)) + 0.5d), (int) (((double) ((n01VarA.c - n01VarA2.c) * f)) + 0.5d), (int) (((double) ((n01VarA.d - n01VarA2.d) * f)) + 0.5d)));
                        }
                    }
                    c.g(this.i, bVar.b(), Collections.singletonList(ot3Var));
                }
            }

            /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
            public class b extends AnimatorListenerAdapter {
                public final /* synthetic */ ot3 e;
                public final /* synthetic */ View f;

                public b(ot3 ot3Var, View view) {
                    this.e = ot3Var;
                    this.f = view;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    ot3 ot3Var = this.e;
                    ot3Var.a.d(1.0f);
                    c.e(this.f, ot3Var);
                }
            }

            /* JADX INFO: renamed from: ot3$c$a$c, reason: collision with other inner class name */
            /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
            public class RunnableC0160c implements Runnable {
                public final /* synthetic */ View e;
                public final /* synthetic */ ot3 f;
                public final /* synthetic */ a g;
                public final /* synthetic */ ValueAnimator h;

                public RunnableC0160c(View view, ot3 ot3Var, a aVar, ValueAnimator valueAnimator) {
                    this.e = view;
                    this.f = ot3Var;
                    this.g = aVar;
                    this.h = valueAnimator;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    c.h(this.e, this.f, this.g);
                    this.h.start();
                }
            }

            public a(View view, InsetObserverView.a aVar) {
                pt3 pt3VarB;
                this.a = aVar;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                pt3 pt3VarA = om3.j.a(view);
                if (pt3VarA != null) {
                    pt3VarB = (Build.VERSION.SDK_INT >= 30 ? new pt3.b(pt3VarA) : new pt3.a(pt3VarA)).b();
                } else {
                    pt3VarB = null;
                }
                this.b = pt3VarB;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                if (!view.isLaidOut()) {
                    this.b = pt3.i(view, windowInsets);
                    return c.i(view, windowInsets);
                }
                pt3 pt3VarI = pt3.i(view, windowInsets);
                if (this.b == null) {
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    this.b = om3.j.a(view);
                }
                if (this.b == null) {
                    this.b = pt3VarI;
                    return c.i(view, windowInsets);
                }
                b bVarJ = c.j(view);
                if (bVarJ != null && Objects.equals(bVarJ.a, windowInsets)) {
                    return c.i(view, windowInsets);
                }
                pt3 pt3Var = this.b;
                int i = 0;
                for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                    if (!pt3VarI.a(i2).equals(pt3Var.a(i2))) {
                        i |= i2;
                    }
                }
                if (i == 0) {
                    return c.i(view, windowInsets);
                }
                pt3 pt3Var2 = this.b;
                ot3 ot3Var = new ot3(i, (i & 8) != 0 ? pt3VarI.a(8).d > pt3Var2.a(8).d ? c.e : c.f : c.g, 160L);
                e eVar = ot3Var.a;
                eVar.d(0.0f);
                ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(eVar.a());
                n01 n01VarA = pt3VarI.a(i);
                n01 n01VarA2 = pt3Var2.a(i);
                int iMin = Math.min(n01VarA.a, n01VarA2.a);
                int i3 = n01VarA.b;
                int i4 = n01VarA2.b;
                int iMin2 = Math.min(i3, i4);
                int i5 = n01VarA.c;
                int i6 = n01VarA2.c;
                int iMin3 = Math.min(i5, i6);
                int i7 = n01VarA.d;
                int i8 = i;
                int i9 = n01VarA2.d;
                a aVar = new a(n01.b(iMin, iMin2, iMin3, Math.min(i7, i9)), n01.b(Math.max(n01VarA.a, n01VarA2.a), Math.max(i3, i4), Math.max(i5, i6), Math.max(i7, i9)));
                c.f(view, ot3Var, windowInsets, false);
                duration.addUpdateListener(new C0159a(ot3Var, pt3VarI, pt3Var2, i8, view));
                duration.addListener(new b(ot3Var, view));
                os1.a(view, new RunnableC0160c(view, ot3Var, aVar, duration));
                this.b = pt3VarI;
                return c.i(view, windowInsets);
            }
        }

        public c(int i, Interpolator interpolator, long j) {
            super(i, interpolator, j);
        }

        public static void e(View view, ot3 ot3Var) {
            b bVarJ = j(view);
            if (bVarJ != null) {
                Iterator it = InsetObserverView.this.i.iterator();
                while (it.hasNext()) {
                    ((InsetObserverView.e) it.next()).c(ot3Var);
                }
                if (bVarJ.b == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    e(viewGroup.getChildAt(i), ot3Var);
                }
            }
        }

        public static void f(View view, ot3 ot3Var, WindowInsets windowInsets, boolean z) {
            b bVarJ = j(view);
            if (bVarJ != null) {
                bVarJ.a = windowInsets;
                if (!z) {
                    Iterator it = InsetObserverView.this.i.iterator();
                    while (it.hasNext()) {
                        ((InsetObserverView.e) it.next()).d(ot3Var);
                    }
                    z = bVarJ.b == 0;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    f(viewGroup.getChildAt(i), ot3Var, windowInsets, z);
                }
            }
        }

        public static void g(View view, pt3 pt3Var, List<ot3> list) {
            b bVarJ = j(view);
            if (bVarJ != null) {
                Iterator it = InsetObserverView.this.i.iterator();
                while (it.hasNext()) {
                    ((InsetObserverView.e) it.next()).b();
                }
                if (bVarJ.b == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    g(viewGroup.getChildAt(i), pt3Var, list);
                }
            }
        }

        public static void h(View view, ot3 ot3Var, a aVar) {
            b bVarJ = j(view);
            if (bVarJ != null) {
                Iterator it = InsetObserverView.this.i.iterator();
                while (it.hasNext()) {
                    ((InsetObserverView.e) it.next()).a();
                }
                if (bVarJ.b == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    h(viewGroup.getChildAt(i), ot3Var, aVar);
                }
            }
        }

        public static WindowInsets i(View view, WindowInsets windowInsets) {
            return view.getTag(R$id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
        }

        public static b j(View view) {
            Object tag = view.getTag(R$id.tag_window_insets_animation_callback);
            if (tag instanceof a) {
                return ((a) tag).a;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
    public static class e {
        public final int a;
        public float b;
        public final Interpolator c;
        public final long d;

        public e(int i, Interpolator interpolator, long j) {
            this.a = i;
            this.c = interpolator;
            this.d = j;
        }

        public long a() {
            return this.d;
        }

        public float b() {
            Interpolator interpolator = this.c;
            return interpolator != null ? interpolator.getInterpolation(this.b) : this.b;
        }

        public int c() {
            return this.a;
        }

        public void d(float f) {
            this.b = f;
        }
    }

    public ot3(int i, Interpolator interpolator, long j) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.a = new d(i, interpolator, j);
        } else {
            this.a = new c(i, interpolator, j);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
    public static class d extends e {
        public final WindowInsetsAnimation e;

        /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
        public static class a extends WindowInsetsAnimation.Callback {
            public final b a;
            public List<ot3> b;
            public ArrayList<ot3> c;
            public final HashMap<WindowInsetsAnimation, ot3> d;

            public a(InsetObserverView.a aVar) {
                super(aVar.b);
                this.d = new HashMap<>();
                this.a = aVar;
            }

            public final ot3 a(WindowInsetsAnimation windowInsetsAnimation) {
                ot3 ot3Var = this.d.get(windowInsetsAnimation);
                if (ot3Var != null) {
                    return ot3Var;
                }
                ot3 ot3Var2 = new ot3(windowInsetsAnimation);
                this.d.put(windowInsetsAnimation, ot3Var2);
                return ot3Var2;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                b bVar = this.a;
                ot3 ot3VarA = a(windowInsetsAnimation);
                Iterator it = InsetObserverView.this.i.iterator();
                while (it.hasNext()) {
                    ((InsetObserverView.e) it.next()).c(ot3VarA);
                }
                this.d.remove(windowInsetsAnimation);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                b bVar = this.a;
                ot3 ot3VarA = a(windowInsetsAnimation);
                Iterator it = InsetObserverView.this.i.iterator();
                while (it.hasNext()) {
                    ((InsetObserverView.e) it.next()).d(ot3VarA);
                }
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final WindowInsets onProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list) {
                ArrayList<ot3> arrayList = this.c;
                if (arrayList == null) {
                    ArrayList<ot3> arrayList2 = new ArrayList<>(list.size());
                    this.c = arrayList2;
                    this.b = Collections.unmodifiableList(arrayList2);
                } else {
                    arrayList.clear();
                }
                int size = list.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    WindowInsetsAnimation windowInsetsAnimation = list.get(size);
                    ot3 ot3VarA = a(windowInsetsAnimation);
                    ot3VarA.a.d(windowInsetsAnimation.getFraction());
                    this.c.add(ot3VarA);
                }
                b bVar = this.a;
                pt3 pt3VarI = pt3.i(null, windowInsets);
                Iterator it = InsetObserverView.this.i.iterator();
                while (it.hasNext()) {
                    ((InsetObserverView.e) it.next()).b();
                }
                return pt3VarI.h();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
                b bVar = this.a;
                a(windowInsetsAnimation);
                a aVar = new a(bounds);
                Iterator it = InsetObserverView.this.i.iterator();
                while (it.hasNext()) {
                    ((InsetObserverView.e) it.next()).a();
                }
                return d.e(aVar);
            }
        }

        public d(WindowInsetsAnimation windowInsetsAnimation) {
            super(0, null, 0L);
            this.e = windowInsetsAnimation;
        }

        public static WindowInsetsAnimation.Bounds e(a aVar) {
            return new WindowInsetsAnimation.Bounds(aVar.a.e(), aVar.b.e());
        }

        @Override // ot3.e
        public final long a() {
            return this.e.getDurationMillis();
        }

        @Override // ot3.e
        public final float b() {
            return this.e.getInterpolatedFraction();
        }

        @Override // ot3.e
        public final int c() {
            return this.e.getTypeMask();
        }

        @Override // ot3.e
        public final void d(float f) {
            this.e.setFraction(f);
        }

        public d(int i, Interpolator interpolator, long j) {
            this(new WindowInsetsAnimation(i, interpolator, j));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsAnimationCompat.java */
    public static final class a {
        public final n01 a;
        public final n01 b;

        public a(n01 n01Var, n01 n01Var2) {
            this.a = n01Var;
            this.b = n01Var2;
        }

        public final String toString() {
            return "Bounds{lower=" + this.a + " upper=" + this.b + "}";
        }

        public a(WindowInsetsAnimation.Bounds bounds) {
            this.a = n01.d(bounds.getLowerBound());
            this.b = n01.d(bounds.getUpperBound());
        }
    }

    public ot3(WindowInsetsAnimation windowInsetsAnimation) {
        this(0, null, 0L);
        if (Build.VERSION.SDK_INT >= 30) {
            this.a = new d(windowInsetsAnimation);
        }
    }
}
