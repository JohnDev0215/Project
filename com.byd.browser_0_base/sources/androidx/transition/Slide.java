package androidx.transition;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import defpackage.df3;
import defpackage.dm2;
import defpackage.fh3;
import defpackage.in3;
import defpackage.om3;
import defpackage.sv2;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class Slide extends Visibility {
    public static final DecelerateInterpolator F = new DecelerateInterpolator();
    public static final AccelerateInterpolator G = new AccelerateInterpolator();
    public static final a H = new a();
    public static final b I = new b();

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final c f23J = new c();
    public static final d K = new d();
    public static final e L = new e();
    public static final f M = new f();
    public g E;

    public class a extends h {
        @Override // androidx.transition.Slide.g
        public final float a(View view, ViewGroup viewGroup) {
            return view.getTranslationX() - viewGroup.getWidth();
        }
    }

    public class b extends h {
        @Override // androidx.transition.Slide.g
        public final float a(View view, ViewGroup viewGroup) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            return om3.e.c(viewGroup) == 1 ? view.getTranslationX() + viewGroup.getWidth() : view.getTranslationX() - viewGroup.getWidth();
        }
    }

    public class c extends i {
        @Override // androidx.transition.Slide.g
        public final float b(View view, ViewGroup viewGroup) {
            return view.getTranslationY() - viewGroup.getHeight();
        }
    }

    public class d extends h {
        @Override // androidx.transition.Slide.g
        public final float a(View view, ViewGroup viewGroup) {
            return view.getTranslationX() + viewGroup.getWidth();
        }
    }

    public class e extends h {
        @Override // androidx.transition.Slide.g
        public final float a(View view, ViewGroup viewGroup) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            return om3.e.c(viewGroup) == 1 ? view.getTranslationX() - viewGroup.getWidth() : view.getTranslationX() + viewGroup.getWidth();
        }
    }

    public class f extends i {
        @Override // androidx.transition.Slide.g
        public final float b(View view, ViewGroup viewGroup) {
            return view.getTranslationY() + viewGroup.getHeight();
        }
    }

    public interface g {
        float a(View view, ViewGroup viewGroup);

        float b(View view, ViewGroup viewGroup);
    }

    public static abstract class h implements g {
        @Override // androidx.transition.Slide.g
        public final float b(View view, ViewGroup viewGroup) {
            return view.getTranslationY();
        }
    }

    public static abstract class i implements g {
        @Override // androidx.transition.Slide.g
        public final float a(View view, ViewGroup viewGroup) {
            return view.getTranslationX();
        }
    }

    public Slide() {
        this.E = M;
        V(80);
    }

    @Override // androidx.transition.Visibility
    public final Animator T(ViewGroup viewGroup, View view, df3 df3Var, df3 df3Var2) {
        if (df3Var2 == null) {
            return null;
        }
        int[] iArr = (int[]) df3Var2.a.get("android:slide:screenPosition");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return androidx.transition.i.a(view, df3Var2, iArr[0], iArr[1], this.E.a(view, viewGroup), this.E.b(view, viewGroup), translationX, translationY, F, this);
    }

    @Override // androidx.transition.Visibility
    public final Animator U(ViewGroup viewGroup, View view, df3 df3Var) {
        if (df3Var == null) {
            return null;
        }
        int[] iArr = (int[]) df3Var.a.get("android:slide:screenPosition");
        return androidx.transition.i.a(view, df3Var, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.E.a(view, viewGroup), this.E.b(view, viewGroup), G, this);
    }

    public final void V(int i2) {
        if (i2 == 3) {
            this.E = H;
        } else if (i2 == 5) {
            this.E = K;
        } else if (i2 == 48) {
            this.E = f23J;
        } else if (i2 == 80) {
            this.E = M;
        } else if (i2 == 8388611) {
            this.E = I;
        } else {
            if (i2 != 8388613) {
                throw new IllegalArgumentException("Invalid slide direction");
            }
            this.E = L;
        }
        dm2 dm2Var = new dm2();
        dm2Var.l = i2;
        this.w = dm2Var;
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void i(df3 df3Var) {
        Q(df3Var);
        int[] iArr = new int[2];
        df3Var.b.getLocationOnScreen(iArr);
        df3Var.a.put("android:slide:screenPosition", iArr);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void l(df3 df3Var) {
        Q(df3Var);
        int[] iArr = new int[2];
        df3Var.b.getLocationOnScreen(iArr);
        df3Var.a.put("android:slide:screenPosition", iArr);
    }

    @SuppressLint({"RestrictedApi"})
    public Slide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = M;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sv2.f);
        int iD = fh3.d(typedArrayObtainStyledAttributes, (XmlPullParser) attributeSet, "slideEdge", 0, 80);
        typedArrayObtainStyledAttributes.recycle();
        V(iD);
    }
}
