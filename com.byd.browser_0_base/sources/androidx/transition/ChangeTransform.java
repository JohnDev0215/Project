package androidx.transition;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import defpackage.df3;
import defpackage.fh3;
import defpackage.in3;
import defpackage.om3;
import defpackage.sv2;
import defpackage.us0;
import defpackage.vs0;
import defpackage.wn3;
import defpackage.ws0;
import java.util.HashMap;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ChangeTransform extends Transition {
    public static final String[] F = {"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    public static final a G = new a();
    public static final b H = new b();
    public static final boolean I = true;
    public final boolean C;
    public final boolean D;
    public final Matrix E;

    public class a extends Property<d, float[]> {
        public a() {
            super(float[].class, "nonTranslations");
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ float[] get(d dVar) {
            return null;
        }

        @Override // android.util.Property
        public final void set(d dVar, float[] fArr) {
            d dVar2 = dVar;
            float[] fArr2 = fArr;
            dVar2.getClass();
            System.arraycopy(fArr2, 0, dVar2.c, 0, fArr2.length);
            dVar2.a();
        }
    }

    public class b extends Property<d, PointF> {
        public b() {
            super(PointF.class, "translations");
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ PointF get(d dVar) {
            return null;
        }

        @Override // android.util.Property
        public final void set(d dVar, PointF pointF) {
            d dVar2 = dVar;
            PointF pointF2 = pointF;
            dVar2.getClass();
            dVar2.d = pointF2.x;
            dVar2.e = pointF2.y;
            dVar2.a();
        }
    }

    public static class c extends g {
        public final View e;
        public final us0 f;

        public c(View view, ws0 ws0Var) {
            this.e = view;
            this.f = ws0Var;
        }

        @Override // androidx.transition.g, androidx.transition.Transition.d
        public final void a() {
            this.f.setVisibility(4);
        }

        @Override // androidx.transition.Transition.d
        public final void d(Transition transition) {
            transition.C(this);
            int i = ws0.k;
            int i2 = R$id.ghost_view;
            View view = this.e;
            ws0 ws0Var = (ws0) view.getTag(i2);
            if (ws0Var != null) {
                int i3 = ws0Var.h - 1;
                ws0Var.h = i3;
                if (i3 <= 0) {
                    ((vs0) ws0Var.getParent()).removeView(ws0Var);
                }
            }
            view.setTag(R$id.transition_transform, null);
            view.setTag(R$id.parent_matrix, null);
        }

        @Override // androidx.transition.g, androidx.transition.Transition.d
        public final void e() {
            this.f.setVisibility(0);
        }
    }

    public static class d {
        public final Matrix a = new Matrix();
        public final View b;
        public final float[] c;
        public float d;
        public float e;

        public d(View view, float[] fArr) {
            this.b = view;
            float[] fArr2 = (float[]) fArr.clone();
            this.c = fArr2;
            this.d = fArr2[2];
            this.e = fArr2[5];
            a();
        }

        public final void a() {
            float f = this.d;
            float[] fArr = this.c;
            fArr[2] = f;
            fArr[5] = this.e;
            Matrix matrix = this.a;
            matrix.setValues(fArr);
            wn3.a aVar = wn3.a;
            this.b.setAnimationMatrix(matrix);
        }
    }

    public static class e {
        public final float a;
        public final float b;
        public final float c;
        public final float d;
        public final float e;
        public final float f;
        public final float g;
        public final float h;

        public e(View view) {
            this.a = view.getTranslationX();
            this.b = view.getTranslationY();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            this.c = om3.i.g(view);
            this.d = view.getScaleX();
            this.e = view.getScaleY();
            this.f = view.getRotationX();
            this.g = view.getRotationY();
            this.h = view.getRotation();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof e)) {
                return false;
            }
            e eVar = (e) obj;
            return eVar.a == this.a && eVar.b == this.b && eVar.c == this.c && eVar.d == this.d && eVar.e == this.e && eVar.f == this.f && eVar.g == this.g && eVar.h == this.h;
        }

        public final int hashCode() {
            float f = this.a;
            int iFloatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
            float f2 = this.b;
            int iFloatToIntBits2 = (iFloatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
            float f3 = this.c;
            int iFloatToIntBits3 = (iFloatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
            float f4 = this.d;
            int iFloatToIntBits4 = (iFloatToIntBits3 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0)) * 31;
            float f5 = this.e;
            int iFloatToIntBits5 = (iFloatToIntBits4 + (f5 != 0.0f ? Float.floatToIntBits(f5) : 0)) * 31;
            float f6 = this.f;
            int iFloatToIntBits6 = (iFloatToIntBits5 + (f6 != 0.0f ? Float.floatToIntBits(f6) : 0)) * 31;
            float f7 = this.g;
            int iFloatToIntBits7 = (iFloatToIntBits6 + (f7 != 0.0f ? Float.floatToIntBits(f7) : 0)) * 31;
            float f8 = this.h;
            return iFloatToIntBits7 + (f8 != 0.0f ? Float.floatToIntBits(f8) : 0);
        }
    }

    public ChangeTransform() {
        this.C = true;
        this.D = true;
        this.E = new Matrix();
    }

    public final void Q(df3 df3Var) {
        View view = df3Var.b;
        if (view.getVisibility() == 8) {
            return;
        }
        HashMap map = df3Var.a;
        map.put("android:changeTransform:parent", view.getParent());
        map.put("android:changeTransform:transforms", new e(view));
        Matrix matrix = view.getMatrix();
        map.put("android:changeTransform:matrix", (matrix == null || matrix.isIdentity()) ? null : new Matrix(matrix));
        if (this.D) {
            Matrix matrix2 = new Matrix();
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            wn3.a aVar = wn3.a;
            viewGroup.transformMatrixToGlobal(matrix2);
            matrix2.preTranslate(-viewGroup.getScrollX(), -viewGroup.getScrollY());
            map.put("android:changeTransform:parentMatrix", matrix2);
            map.put("android:changeTransform:intermediateMatrix", view.getTag(R$id.transition_transform));
            map.put("android:changeTransform:intermediateParentMatrix", view.getTag(R$id.parent_matrix));
        }
    }

    @Override // androidx.transition.Transition
    public final void i(df3 df3Var) {
        Q(df3Var);
    }

    @Override // androidx.transition.Transition
    public final void l(df3 df3Var) {
        Q(df3Var);
        if (I) {
            return;
        }
        View view = df3Var.b;
        ((ViewGroup) view.getParent()).startViewTransition(view);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.Animator p(android.view.ViewGroup r26, defpackage.df3 r27, defpackage.df3 r28) {
        /*
            Method dump skipped, instruction units count: 788
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ChangeTransform.p(android.view.ViewGroup, df3, df3):android.animation.Animator");
    }

    @Override // androidx.transition.Transition
    public final String[] v() {
        return F;
    }

    @SuppressLint({"RestrictedApi"})
    public ChangeTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = true;
        this.D = true;
        this.E = new Matrix();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sv2.e);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        this.C = !fh3.g(xmlPullParser, "reparentWithOverlay") ? true : typedArrayObtainStyledAttributes.getBoolean(1, true);
        this.D = fh3.g(xmlPullParser, "reparent") ? typedArrayObtainStyledAttributes.getBoolean(0, true) : true;
        typedArrayObtainStyledAttributes.recycle();
    }
}
