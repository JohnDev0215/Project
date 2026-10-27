package u1;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: compiled from: AnimatablePathValue.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements m<PointF, PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<b2.a<PointF>> f8127a;

    public e(List<b2.a<PointF>> list) {
        this.f8127a = list;
    }

    @Override // u1.m
    public r1.a<PointF, PointF> a() {
        return this.f8127a.get(0).h() ? new r1.j(this.f8127a) : new r1.i(this.f8127a);
    }

    @Override // u1.m
    public List<b2.a<PointF>> b() {
        return this.f8127a;
    }

    @Override // u1.m
    public boolean c() {
        return this.f8127a.size() == 1 && this.f8127a.get(0).h();
    }
}
