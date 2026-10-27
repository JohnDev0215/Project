package L;

import A7.o;
import C0.U;
import C0.V;
import N0.C0406a;
import N0.r;
import O0.x;
import android.graphics.Matrix;
import android.graphics.Path;
import m7.q;
import n0.C1880i;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class c extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3869m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3870n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f3871p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(int i7, int i10, int i11, Object obj) {
        super(1);
        this.f3869m = i11;
        this.o = obj;
        this.f3870n = i7;
        this.f3871p = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f3869m) {
            case 0:
                U.d((U) obj, (V) this.o, -this.f3870n, -this.f3871p);
                return q.f19267a;
            case 1:
                U.d((U) obj, (V) this.o, C7.a.W((this.f3870n - r0.l) / 2.0f), C7.a.W((this.f3871p - r0.f871m) / 2.0f));
                return q.f19267a;
            default:
                r rVar = (r) obj;
                C0406a c0406a = rVar.f4402a;
                int iB = rVar.b(this.f3870n);
                int iB2 = rVar.b(this.f3871p);
                CharSequence charSequence = c0406a.f4364e;
                if (iB < 0 || iB > iB2 || iB2 > charSequence.length()) {
                    StringBuilder sbL = M5.b.l("start(", ") or end(", iB, iB2, ") is out of range [0..");
                    sbL.append(charSequence.length());
                    sbL.append("], or start > end!");
                    throw new IllegalArgumentException(sbL.toString().toString());
                }
                Path path = new Path();
                x xVar = c0406a.d;
                xVar.f5050f.getSelectionPath(iB, iB2, path);
                int i7 = xVar.f5052h;
                if (i7 != 0 && !path.isEmpty()) {
                    path.offset(0.0f, i7);
                }
                long jA = h6.e.a(0.0f, rVar.f4405f);
                Matrix matrix = new Matrix();
                matrix.setTranslate(m0.c.d(jA), m0.c.e(jA));
                path.transform(matrix);
                ((C1880i) this.o).f19436a.addPath(path, m0.c.d(0L), m0.c.e(0L));
                return q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(int i7, int i10, V v10) {
        super(1);
        this.f3869m = 1;
        this.f3870n = i7;
        this.o = v10;
        this.f3871p = i10;
    }
}
