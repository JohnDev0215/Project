package r1;

import java.util.List;

/* JADX INFO: compiled from: GradientColorKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends f<v1.c> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v1.c f7755i;

    public d(List<b2.a<v1.c>> list) {
        super(list);
        v1.c cVar = list.get(0).f2045b;
        int iC = cVar != null ? cVar.c() : 0;
        this.f7755i = new v1.c(new float[iC], new int[iC]);
    }

    @Override // r1.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public v1.c i(b2.a<v1.c> aVar, float f8) {
        this.f7755i.d(aVar.f2045b, aVar.f2046c, f8);
        return this.f7755i;
    }
}
