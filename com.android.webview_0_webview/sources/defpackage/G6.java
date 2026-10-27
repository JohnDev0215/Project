package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class G6 extends Z6 {
    public static final String[] d0 = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property e0 = new C1748x6(PointF.class, "boundsOrigin");
    public static final Property f0 = new C1803y6(PointF.class, "topLeft");
    public static final Property g0 = new C1858z6(PointF.class, "bottomRight");
    public static final Property h0 = new A6(PointF.class, "bottomRight");
    public static final Property i0 = new B6(PointF.class, "topLeft");
    public static final Property j0 = new C6(PointF.class, "position");
    public static R6 k0 = new R6();
    public int[] a0 = new int[2];
    public boolean b0 = false;
    public boolean c0 = false;

    public final void H(C0977j7 c0977j7) {
        View view = c0977j7.b;
        if (!AbstractC1251o3.u(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        c0977j7.a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        c0977j7.a.put("android:changeBounds:parent", c0977j7.b.getParent());
    }

    @Override // defpackage.Z6
    public void d(C0977j7 c0977j7) {
        H(c0977j7);
    }

    @Override // defpackage.Z6
    public void g(C0977j7 c0977j7) {
        H(c0977j7);
    }

    @Override // defpackage.Z6
    public Animator k(ViewGroup viewGroup, C0977j7 c0977j7, C0977j7 c0977j72) {
        int i;
        G6 g6;
        Animator animatorA;
        if (c0977j7 == null || c0977j72 == null) {
            return null;
        }
        Map map = c0977j7.a;
        Map map2 = c0977j72.a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view = c0977j72.b;
        Rect rect = (Rect) c0977j7.a.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) c0977j72.a.get("android:changeBounds:bounds");
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
        Rect rect3 = (Rect) c0977j7.a.get("android:changeBounds:clip");
        Rect rect4 = (Rect) c0977j72.a.get("android:changeBounds:clip");
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
        AbstractC1419r7.c(view, i2, i4, i6, i8);
        if (i14 != 2) {
            g6 = this;
            animatorA = (i2 == i3 && i4 == i5) ? P6.a(view, h0, g6.W.a(i6, i8, i7, i9)) : P6.a(view, i0, g6.W.a(i2, i4, i3, i5));
        } else if (i10 == i12 && i11 == i13) {
            g6 = this;
            animatorA = P6.a(view, j0, g6.W.a(i2, i4, i3, i5));
        } else {
            g6 = this;
            F6 f6 = new F6(view);
            ObjectAnimator objectAnimatorA = P6.a(f6, f0, g6.W.a(i2, i4, i3, i5));
            ObjectAnimator objectAnimatorA2 = P6.a(f6, g0, g6.W.a(i6, i8, i7, i9));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorA, objectAnimatorA2);
            animatorSet.addListener(new D6(g6, f6));
            animatorA = animatorSet;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            AbstractC1200n7.a(viewGroup4, true);
            g6.a(new E6(g6, viewGroup4));
        }
        return animatorA;
    }

    @Override // defpackage.Z6
    public String[] p() {
        return d0;
    }
}
