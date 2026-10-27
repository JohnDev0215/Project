package q1;

import com.airbnb.lottie.model.content.ShapeTrimPath;
import java.util.ArrayList;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: TrimPathContent.java */
/* JADX INFO: loaded from: classes.dex */
public class s implements c, a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f7369b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<a.b> f7370c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ShapeTrimPath.Type f7371d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r1.a<?, Float> f7372e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r1.a<?, Float> f7373f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r1.a<?, Float> f7374g;

    public s(w1.a aVar, ShapeTrimPath shapeTrimPath) {
        this.f7368a = shapeTrimPath.c();
        this.f7369b = shapeTrimPath.g();
        this.f7371d = shapeTrimPath.f();
        r1.a<Float, Float> aVarA = shapeTrimPath.e().a();
        this.f7372e = aVarA;
        r1.a<Float, Float> aVarA2 = shapeTrimPath.b().a();
        this.f7373f = aVarA2;
        r1.a<Float, Float> aVarA3 = shapeTrimPath.d().a();
        this.f7374g = aVarA3;
        aVar.k(aVarA);
        aVar.k(aVarA2);
        aVar.k(aVarA3);
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
    }

    @Override // r1.a.b
    public void c() {
        for (int i8 = 0; i8 < this.f7370c.size(); i8++) {
            this.f7370c.get(i8).c();
        }
    }

    @Override // q1.c
    public void d(List<c> list, List<c> list2) {
    }

    public void e(a.b bVar) {
        this.f7370c.add(bVar);
    }

    public r1.a<?, Float> f() {
        return this.f7373f;
    }

    public r1.a<?, Float> i() {
        return this.f7374g;
    }

    public r1.a<?, Float> j() {
        return this.f7372e;
    }

    public ShapeTrimPath.Type k() {
        return this.f7371d;
    }

    public boolean l() {
        return this.f7369b;
    }
}
