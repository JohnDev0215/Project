package i2;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.material.R$styleable;
import l0.b0;
import l0.t;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3379a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f3380b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f3381c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f3382d;

        public a(boolean z3, boolean z4, boolean z5, d dVar) {
            this.f3379a = z3;
            this.f3380b = z4;
            this.f3381c = z5;
            this.f3382d = dVar;
        }

        @Override // i2.p.d
        public b0 a(View view, b0 b0Var, e eVar) {
            if (this.f3379a) {
                eVar.f3388d += b0Var.e();
            }
            boolean zH = p.h(view);
            if (this.f3380b) {
                if (zH) {
                    eVar.f3387c += b0Var.f();
                } else {
                    eVar.f3385a += b0Var.f();
                }
            }
            if (this.f3381c) {
                if (zH) {
                    eVar.f3385a += b0Var.g();
                } else {
                    eVar.f3387c += b0Var.g();
                }
            }
            eVar.a(view);
            d dVar = this.f3382d;
            return dVar != null ? dVar.a(view, b0Var, eVar) : b0Var;
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class b implements l0.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f3383a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ e f3384b;

        public b(d dVar, e eVar) {
            this.f3383a = dVar;
            this.f3384b = eVar;
        }

        @Override // l0.p
        public b0 a(View view, b0 b0Var) {
            return this.f3383a.a(view, b0Var, new e(this.f3384b));
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class c implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            t.g0(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface d {
        b0 a(View view, b0 b0Var, e eVar);
    }

    public static void a(View view, AttributeSet attributeSet, int i3, int i4, d dVar) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R$styleable.Insets, i3, i4);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingBottomSystemWindowInsets, false);
        boolean z4 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingLeftSystemWindowInsets, false);
        boolean z5 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        b(view, new a(z3, z4, z5, dVar));
    }

    public static void b(View view, d dVar) {
        t.v0(view, new b(dVar, new e(t.E(view), view.getPaddingTop(), t.D(view), view.getPaddingBottom())));
        j(view);
    }

    public static float c(Context context, int i3) {
        return TypedValue.applyDimension(1, i3, context.getResources().getDisplayMetrics());
    }

    public static ViewGroup d(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static o e(View view) {
        return f(d(view));
    }

    public static o f(View view) {
        if (view == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 18 ? new n(view) : m.c(view);
    }

    public static float g(View view) {
        float fU = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            fU += t.u((View) parent);
        }
        return fU;
    }

    public static boolean h(View view) {
        return t.z(view) == 1;
    }

    public static PorterDuff.Mode i(int i3, PorterDuff.Mode mode) {
        if (i3 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i3 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i3 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i3) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static void j(View view) {
        if (t.O(view)) {
            t.g0(view);
        } else {
            view.addOnAttachStateChangeListener(new c());
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3385a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3386b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3387c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3388d;

        public e(int i3, int i4, int i5, int i6) {
            this.f3385a = i3;
            this.f3386b = i4;
            this.f3387c = i5;
            this.f3388d = i6;
        }

        public void a(View view) {
            t.w0(view, this.f3385a, this.f3386b, this.f3387c, this.f3388d);
        }

        public e(e eVar) {
            this.f3385a = eVar.f3385a;
            this.f3386b = eVar.f3386b;
            this.f3387c = eVar.f3387c;
            this.f3388d = eVar.f3388d;
        }
    }
}
