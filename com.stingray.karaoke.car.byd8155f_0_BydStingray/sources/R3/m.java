package R3;

import android.graphics.PointF;
import b4.C0995a;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class m extends e {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final PointF f7411h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final PointF f7412i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final f f7413j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final f f7414k;

    public m(f fVar, f fVar2) {
        super(Collections.emptyList());
        this.f7411h = new PointF();
        this.f7412i = new PointF();
        this.f7413j = fVar;
        this.f7414k = fVar2;
        g(this.d);
    }

    @Override // R3.e
    public final Object d() {
        PointF pointF = this.f7411h;
        float f10 = pointF.x;
        PointF pointF2 = this.f7412i;
        pointF2.set(f10, 0.0f);
        pointF2.set(pointF2.x, pointF.y);
        return pointF2;
    }

    @Override // R3.e
    public final Object e(C0995a c0995a, float f10) {
        PointF pointF = this.f7411h;
        float f11 = pointF.x;
        PointF pointF2 = this.f7412i;
        pointF2.set(f11, 0.0f);
        pointF2.set(pointF2.x, pointF.y);
        return pointF2;
    }

    @Override // R3.e
    public final void g(float f10) {
        f fVar = this.f7413j;
        fVar.g(f10);
        f fVar2 = this.f7414k;
        fVar2.g(f10);
        this.f7411h.set(((Float) fVar.d()).floatValue(), ((Float) fVar2.d()).floatValue());
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f7388a;
            if (i7 >= arrayList.size()) {
                return;
            }
            ((a) arrayList.get(i7)).b();
            i7++;
        }
    }
}
