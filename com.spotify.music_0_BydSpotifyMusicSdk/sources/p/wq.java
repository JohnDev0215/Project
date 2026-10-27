package p;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class wq extends wn2 {
    public static final String[] F = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final sq G;
    public static final sq H;
    public static final sq I;
    public static final sq J;
    public static final sq K;

    static {
        new rq(PointF.class, "boundsOrigin").a = new Rect();
        G = new sq(PointF.class, "topLeft", 0);
        H = new sq(PointF.class, "bottomRight", 1);
        I = new sq(PointF.class, "bottomRight", 2);
        J = new sq(PointF.class, "topLeft", 3);
        K = new sq(PointF.class, "position", 4);
    }

    public static void N(do2 do2Var) {
        View view = do2Var.b;
        WeakHashMap weakHashMap = zt2.a;
        if (!lt2.c(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        HashMap map = do2Var.a;
        map.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        map.put("android:changeBounds:parent", do2Var.b.getParent());
    }

    @Override // p.wn2
    public final void f(do2 do2Var) {
        N(do2Var);
    }

    @Override // p.wn2
    public final void j(do2 do2Var) {
        N(do2Var);
    }

    @Override // p.wn2
    public final Animator n(ViewGroup viewGroup, do2 do2Var, do2 do2Var2) {
        int i;
        wq wqVar;
        Animator animatorOfObject;
        if (do2Var == null || do2Var2 == null) {
            return null;
        }
        HashMap map = do2Var.a;
        HashMap map2 = do2Var2.a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        Rect rect = (Rect) map.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) map2.get("android:changeBounds:bounds");
        int i2 = rect.left;
        int i3 = rect2.left;
        int i4 = rect.top;
        int i5 = rect2.top;
        int i6 = rect.right;
        int i7 = rect2.right;
        int i8 = rect.bottom;
        int i9 = rect2.bottom;
        int i10 = i6 - i2;
        int i11 = i8 - i4;
        int i12 = i7 - i3;
        int i13 = i9 - i5;
        Rect rect3 = (Rect) map.get("android:changeBounds:clip");
        Rect rect4 = (Rect) map2.get("android:changeBounds:clip");
        if ((i10 == 0 || i11 == 0) && (i12 == 0 || i13 == 0)) {
            i = 0;
        } else {
            i = (i2 == i3 && i4 == i5) ? 0 : 1;
            if (i6 != i7 || i8 != i9) {
                i++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i++;
        }
        int i14 = i;
        if (i14 <= 0) {
            return null;
        }
        View view = do2Var2.b;
        dv2.a(view, i2, i4, i6, i8);
        if (i14 != 2) {
            wqVar = this;
            if (i2 == i3 && i4 == i5) {
                wqVar.B.getClass();
                animatorOfObject = ObjectAnimator.ofObject(view, I, (TypeConverter) null, zb0.x(i6, i8, i7, i9));
            } else {
                wqVar.B.getClass();
                animatorOfObject = ObjectAnimator.ofObject(view, J, (TypeConverter) null, zb0.x(i2, i4, i3, i5));
            }
        } else if (i10 == i12 && i11 == i13) {
            wqVar = this;
            wqVar.B.getClass();
            animatorOfObject = ObjectAnimator.ofObject(view, K, (TypeConverter) null, zb0.x(i2, i4, i3, i5));
        } else {
            wqVar = this;
            vq vqVar = new vq();
            vqVar.e = view;
            wqVar.B.getClass();
            ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(vqVar, G, (TypeConverter) null, zb0.x(i2, i4, i3, i5));
            wqVar.B.getClass();
            ObjectAnimator objectAnimatorOfObject2 = ObjectAnimator.ofObject(vqVar, H, (TypeConverter) null, zb0.x(i6, i8, i7, i9));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorOfObject, objectAnimatorOfObject2);
            animatorSet.addListener(new tq(vqVar));
            animatorOfObject = animatorSet;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            ci1.D(viewGroup4, true);
            wqVar.a(new uq(viewGroup4));
        }
        return animatorOfObject;
    }

    @Override // p.wn2
    public final String[] t() {
        return F;
    }
}
