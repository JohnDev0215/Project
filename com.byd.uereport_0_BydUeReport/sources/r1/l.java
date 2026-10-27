package r1;

import android.graphics.Path;
import java.util.List;

/* JADX INFO: compiled from: ShapeKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends a<v1.h, Path> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v1.h f7767i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Path f7768j;

    public l(List<b2.a<v1.h>> list) {
        super(list);
        this.f7767i = new v1.h();
        this.f7768j = new Path();
    }

    @Override // r1.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public Path i(b2.a<v1.h> aVar, float f8) {
        this.f7767i.c(aVar.f2045b, aVar.f2046c, f8);
        a2.g.h(this.f7767i, this.f7768j);
        return this.f7768j;
    }
}
