package s1;

import android.annotation.SuppressLint;
import q1.v;
import s1.h;

/* JADX INFO: compiled from: LruResourceCache.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends k2.g<n1.f, v<?>> implements h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private h.a f9478e;

    public g(long j7) {
        super(j7);
    }

    @Override // s1.h
    @SuppressLint({"InlinedApi"})
    public void a(int i7) {
        if (i7 >= 40) {
            b();
        } else if (i7 >= 20 || i7 == 15) {
            m(h() / 2);
        }
    }

    @Override // s1.h
    public /* bridge */ /* synthetic */ v c(n1.f fVar) {
        return (v) super.l(fVar);
    }

    @Override // s1.h
    public /* bridge */ /* synthetic */ v d(n1.f fVar, v vVar) {
        return (v) super.k(fVar, vVar);
    }

    @Override // s1.h
    public void e(h.a aVar) {
        this.f9478e = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k2.g
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public int i(v<?> vVar) {
        return vVar == null ? super.i(null) : vVar.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k2.g
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public void j(n1.f fVar, v<?> vVar) {
        h.a aVar = this.f9478e;
        if (aVar == null || vVar == null) {
            return;
        }
        aVar.d(vVar);
    }
}
