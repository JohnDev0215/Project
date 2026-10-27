package p;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class sq extends Property {
    public final /* synthetic */ int a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ sq(Class cls, String str, int i) {
        super(cls, str);
        this.a = i;
    }

    public final Float a(View view) {
        switch (this.a) {
            case 5:
                return Float.valueOf(dv2.a.L(view));
            case 6:
            default:
                WeakHashMap weakHashMap = zt2.a;
                return Float.valueOf(jt2.e(view));
            case 7:
                return Float.valueOf(view.getLayoutParams().width);
            case 8:
                return Float.valueOf(view.getLayoutParams().height);
            case 9:
                WeakHashMap weakHashMap2 = zt2.a;
                return Float.valueOf(jt2.f(view));
        }
    }

    public final void b(View view, PointF pointF) {
        switch (this.a) {
            case 2:
                dv2.a(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
                break;
            case 3:
                dv2.a(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
                break;
            default:
                int iRound = Math.round(pointF.x);
                int iRound2 = Math.round(pointF.y);
                dv2.a(view, iRound, iRound2, view.getWidth() + iRound, view.getHeight() + iRound2);
                break;
        }
    }

    public final void c(View view, Float f) {
        switch (this.a) {
            case 5:
                dv2.a.M(view, f.floatValue());
                break;
            case 6:
            default:
                WeakHashMap weakHashMap = zt2.a;
                jt2.k(view, jt2.f(view), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
                break;
            case 7:
                view.getLayoutParams().width = f.intValue();
                view.requestLayout();
                break;
            case 8:
                view.getLayoutParams().height = f.intValue();
                view.requestLayout();
                break;
            case 9:
                int iIntValue = f.intValue();
                int paddingTop = view.getPaddingTop();
                WeakHashMap weakHashMap2 = zt2.a;
                jt2.k(view, iIntValue, paddingTop, jt2.e(view), view.getPaddingBottom());
                break;
        }
    }

    public final void d(vq vqVar, PointF pointF) {
        switch (this.a) {
            case 0:
                vqVar.getClass();
                vqVar.a = Math.round(pointF.x);
                int iRound = Math.round(pointF.y);
                vqVar.b = iRound;
                int i = vqVar.f + 1;
                vqVar.f = i;
                if (i == vqVar.g) {
                    dv2.a(vqVar.e, vqVar.a, iRound, vqVar.c, vqVar.d);
                    vqVar.f = 0;
                    vqVar.g = 0;
                }
                break;
            default:
                vqVar.getClass();
                vqVar.c = Math.round(pointF.x);
                int iRound2 = Math.round(pointF.y);
                vqVar.d = iRound2;
                int i2 = vqVar.g + 1;
                vqVar.g = i2;
                if (vqVar.f == i2) {
                    dv2.a(vqVar.e, vqVar.a, vqVar.b, vqVar.c, iRound2);
                    vqVar.f = 0;
                    vqVar.g = 0;
                }
                break;
        }
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        switch (this.a) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 6:
                WeakHashMap weakHashMap = zt2.a;
                break;
        }
        return a((View) obj);
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                d((vq) obj, (PointF) obj2);
                break;
            case 1:
                d((vq) obj, (PointF) obj2);
                break;
            case 2:
                b((View) obj, (PointF) obj2);
                break;
            case 3:
                b((View) obj, (PointF) obj2);
                break;
            case 4:
                b((View) obj, (PointF) obj2);
                break;
            case 5:
                c((View) obj, (Float) obj2);
                break;
            case 6:
                WeakHashMap weakHashMap = zt2.a;
                kt2.c((View) obj, (Rect) obj2);
                break;
            case 7:
                c((View) obj, (Float) obj2);
                break;
            case 8:
                c((View) obj, (Float) obj2);
                break;
            case 9:
                c((View) obj, (Float) obj2);
                break;
            default:
                c((View) obj, (Float) obj2);
                break;
        }
    }
}
