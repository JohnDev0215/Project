package R3;

import a4.AbstractC0925f;
import android.graphics.PointF;
import b4.C0995a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f extends i {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f7393h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(int i7, List list) {
        super(list);
        this.f7393h = i7;
    }

    public static float j(C0995a c0995a, float f10) {
        Object obj = c0995a.f12212b;
        if (obj == null || c0995a.c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        if (c0995a.f12217i == -3987645.8f) {
            c0995a.f12217i = ((Float) obj).floatValue();
        }
        float f11 = c0995a.f12217i;
        if (c0995a.f12218j == -3987645.8f) {
            c0995a.f12218j = ((Float) c0995a.c).floatValue();
        }
        return AbstractC0925f.e(f11, c0995a.f12218j, f10);
    }

    @Override // R3.e
    public final Object e(C0995a c0995a, float f10) {
        int i7;
        Object obj;
        switch (this.f7393h) {
            case 0:
                return Integer.valueOf(k(c0995a, f10));
            case 1:
                return Float.valueOf(j(c0995a, f10));
            case 2:
                Object obj2 = c0995a.f12212b;
                if (obj2 == null) {
                    throw new IllegalStateException("Missing values for keyframe.");
                }
                Object obj3 = c0995a.c;
                if (obj3 == null) {
                    if (c0995a.f12219k == 784923401) {
                        c0995a.f12219k = ((Integer) obj2).intValue();
                    }
                    i7 = c0995a.f12219k;
                } else {
                    if (c0995a.l == 784923401) {
                        c0995a.l = ((Integer) obj3).intValue();
                    }
                    i7 = c0995a.l;
                }
                if (c0995a.f12219k == 784923401) {
                    c0995a.f12219k = ((Integer) obj2).intValue();
                }
                int i10 = c0995a.f12219k;
                PointF pointF = AbstractC0925f.f11108a;
                return Integer.valueOf((int) ((f10 * (i7 - i10)) + i10));
            default:
                return (f10 != 1.0f || (obj = c0995a.c) == null) ? (U3.b) c0995a.f12212b : (U3.b) obj;
        }
    }

    public float i() {
        return j(this.c.t(), b());
    }

    public int k(C0995a c0995a, float f10) {
        if (c0995a.f12212b == null || c0995a.c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        return com.bumptech.glide.d.A(((Integer) c0995a.f12212b).intValue(), ((Integer) c0995a.c).intValue(), AbstractC0925f.b(f10, 0.0f, 1.0f));
    }
}
