package R3;

import a4.AbstractC0925f;
import android.graphics.PointF;
import b4.C0995a;
import b4.C0996b;
import h2.AbstractC1347d;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class h extends i {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f7400h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f7401i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(int i7, List list) {
        super(list);
        this.f7400h = i7;
        switch (i7) {
            case 1:
                super(list);
                this.f7401i = new PointF();
                break;
            case 2:
                super(list);
                this.f7401i = new C0996b(1.0f, 1.0f);
                break;
            default:
                int iMax = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    W3.c cVar = (W3.c) ((C0995a) list.get(i10)).f12212b;
                    if (cVar != null) {
                        iMax = Math.max(iMax, cVar.f9761b.length);
                    }
                }
                this.f7401i = new W3.c(new float[iMax], new int[iMax]);
                break;
        }
    }

    @Override // R3.e
    public final Object e(C0995a c0995a, float f10) {
        Object obj;
        switch (this.f7400h) {
            case 0:
                W3.c cVar = (W3.c) c0995a.f12212b;
                W3.c cVar2 = (W3.c) c0995a.c;
                W3.c cVar3 = (W3.c) this.f7401i;
                cVar3.getClass();
                if (cVar.equals(cVar2) || f10 <= 0.0f) {
                    cVar3.a(cVar);
                } else if (f10 >= 1.0f) {
                    cVar3.a(cVar2);
                } else {
                    int[] iArr = cVar.f9761b;
                    int length = iArr.length;
                    int[] iArr2 = cVar2.f9761b;
                    if (length != iArr2.length) {
                        StringBuilder sb = new StringBuilder("Cannot interpolate between gradients. Lengths vary (");
                        sb.append(iArr.length);
                        sb.append(" vs ");
                        throw new IllegalArgumentException(M5.b.h(sb, iArr2.length, ")"));
                    }
                    int i7 = 0;
                    while (true) {
                        int length2 = iArr.length;
                        int[] iArr3 = cVar3.f9761b;
                        float[] fArr = cVar3.f9760a;
                        if (i7 < length2) {
                            fArr[i7] = AbstractC0925f.e(cVar.f9760a[i7], cVar2.f9760a[i7], f10);
                            iArr3[i7] = com.bumptech.glide.d.A(iArr[i7], iArr2[i7], f10);
                            i7++;
                        } else {
                            for (int length3 = iArr.length; length3 < fArr.length; length3++) {
                                fArr[length3] = fArr[iArr.length - 1];
                                iArr3[length3] = iArr3[iArr.length - 1];
                            }
                        }
                    }
                }
                return cVar3;
            case 1:
                return i(c0995a, f10, f10);
            default:
                Object obj2 = c0995a.f12212b;
                if (obj2 == null || (obj = c0995a.c) == null) {
                    throw new IllegalStateException("Missing values for keyframe.");
                }
                C0996b c0996b = (C0996b) obj2;
                C0996b c0996b2 = (C0996b) obj;
                float fE = AbstractC0925f.e(c0996b.f12223a, c0996b2.f12223a, f10);
                float fE2 = AbstractC0925f.e(c0996b.f12224b, c0996b2.f12224b, f10);
                C0996b c0996b3 = (C0996b) this.f7401i;
                c0996b3.f12223a = fE;
                c0996b3.f12224b = fE2;
                return c0996b3;
        }
    }

    @Override // R3.e
    public /* bridge */ /* synthetic */ Object f(C0995a c0995a, float f10, float f11, float f12) {
        switch (this.f7400h) {
            case 1:
                return i(c0995a, f11, f12);
            default:
                return super.f(c0995a, f10, f11, f12);
        }
    }

    public PointF i(C0995a c0995a, float f10, float f11) {
        Object obj;
        Object obj2 = c0995a.f12212b;
        if (obj2 == null || (obj = c0995a.c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF = (PointF) obj2;
        PointF pointF2 = (PointF) obj;
        PointF pointF3 = (PointF) this.f7401i;
        float f12 = pointF.x;
        float fA = AbstractC1347d.a(pointF2.x, f12, f10, f12);
        float f13 = pointF.y;
        pointF3.set(fA, AbstractC1347d.a(pointF2.y, f13, f11, f13));
        return pointF3;
    }
}
