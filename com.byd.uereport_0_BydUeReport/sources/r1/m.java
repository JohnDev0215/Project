package r1;

import android.graphics.PointF;
import java.util.Collections;

/* JADX INFO: compiled from: SplitDimensionPathKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends a<PointF, PointF> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final PointF f7769i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final a<Float, Float> f7770j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a<Float, Float> f7771k;

    public m(a<Float, Float> aVar, a<Float, Float> aVar2) {
        super(Collections.emptyList());
        this.f7769i = new PointF();
        this.f7770j = aVar;
        this.f7771k = aVar2;
        l(f());
    }

    @Override // r1.a
    public void l(float f8) {
        this.f7770j.l(f8);
        this.f7771k.l(f8);
        this.f7769i.set(this.f7770j.h().floatValue(), this.f7771k.h().floatValue());
        for (int i8 = 0; i8 < this.f7741a.size(); i8++) {
            this.f7741a.get(i8).c();
        }
    }

    @Override // r1.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public PointF h() {
        return i(null, 0.0f);
    }

    @Override // r1.a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public PointF i(b2.a<PointF> aVar, float f8) {
        return this.f7769i;
    }
}
