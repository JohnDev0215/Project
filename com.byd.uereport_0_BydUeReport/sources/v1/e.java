package v1;

import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.ShapeStroke;
import java.util.List;

/* JADX INFO: compiled from: GradientStroke.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final GradientType f8530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u1.c f8531c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u1.d f8532d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u1.f f8533e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u1.f f8534f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u1.b f8535g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ShapeStroke.LineCapType f8536h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ShapeStroke.LineJoinType f8537i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f8538j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final List<u1.b> f8539k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final u1.b f8540l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f8541m;

    public e(String str, GradientType gradientType, u1.c cVar, u1.d dVar, u1.f fVar, u1.f fVar2, u1.b bVar, ShapeStroke.LineCapType lineCapType, ShapeStroke.LineJoinType lineJoinType, float f8, List<u1.b> list, u1.b bVar2, boolean z7) {
        this.f8529a = str;
        this.f8530b = gradientType;
        this.f8531c = cVar;
        this.f8532d = dVar;
        this.f8533e = fVar;
        this.f8534f = fVar2;
        this.f8535g = bVar;
        this.f8536h = lineCapType;
        this.f8537i = lineJoinType;
        this.f8538j = f8;
        this.f8539k = list;
        this.f8540l = bVar2;
        this.f8541m = z7;
    }

    @Override // v1.b
    public q1.c a(o1.g gVar, w1.a aVar) {
        return new q1.i(gVar, aVar, this);
    }

    public ShapeStroke.LineCapType b() {
        return this.f8536h;
    }

    public u1.b c() {
        return this.f8540l;
    }

    public u1.f d() {
        return this.f8534f;
    }

    public u1.c e() {
        return this.f8531c;
    }

    public GradientType f() {
        return this.f8530b;
    }

    public ShapeStroke.LineJoinType g() {
        return this.f8537i;
    }

    public List<u1.b> h() {
        return this.f8539k;
    }

    public float i() {
        return this.f8538j;
    }

    public String j() {
        return this.f8529a;
    }

    public u1.d k() {
        return this.f8532d;
    }

    public u1.f l() {
        return this.f8533e;
    }

    public u1.b m() {
        return this.f8535g;
    }

    public boolean n() {
        return this.f8541m;
    }
}
