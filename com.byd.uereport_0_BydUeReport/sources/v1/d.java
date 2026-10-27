package v1;

import android.graphics.Path;
import com.airbnb.lottie.model.content.GradientType;

/* JADX INFO: compiled from: GradientFill.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final GradientType f8519a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path.FillType f8520b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u1.c f8521c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u1.d f8522d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u1.f f8523e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u1.f f8524f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f8525g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final u1.b f8526h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u1.b f8527i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f8528j;

    public d(String str, GradientType gradientType, Path.FillType fillType, u1.c cVar, u1.d dVar, u1.f fVar, u1.f fVar2, u1.b bVar, u1.b bVar2, boolean z7) {
        this.f8519a = gradientType;
        this.f8520b = fillType;
        this.f8521c = cVar;
        this.f8522d = dVar;
        this.f8523e = fVar;
        this.f8524f = fVar2;
        this.f8525g = str;
        this.f8526h = bVar;
        this.f8527i = bVar2;
        this.f8528j = z7;
    }

    @Override // v1.b
    public q1.c a(o1.g gVar, w1.a aVar) {
        return new q1.h(gVar, aVar, this);
    }

    public u1.f b() {
        return this.f8524f;
    }

    public Path.FillType c() {
        return this.f8520b;
    }

    public u1.c d() {
        return this.f8521c;
    }

    public GradientType e() {
        return this.f8519a;
    }

    public String f() {
        return this.f8525g;
    }

    public u1.d g() {
        return this.f8522d;
    }

    public u1.f h() {
        return this.f8523e;
    }

    public boolean i() {
        return this.f8528j;
    }
}
