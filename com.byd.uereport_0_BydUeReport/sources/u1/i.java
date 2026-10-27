package u1;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: compiled from: AnimatableSplitDimensionPathValue.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements m<PointF, PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f8128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f8129b;

    public i(b bVar, b bVar2) {
        this.f8128a = bVar;
        this.f8129b = bVar2;
    }

    @Override // u1.m
    public r1.a<PointF, PointF> a() {
        return new r1.m(this.f8128a.a(), this.f8129b.a());
    }

    @Override // u1.m
    public List<b2.a<PointF>> b() {
        throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
    }

    @Override // u1.m
    public boolean c() {
        return this.f8128a.c() && this.f8129b.c();
    }
}
