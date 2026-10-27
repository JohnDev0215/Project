package n0;

import android.graphics.Shader;

/* JADX INFO: renamed from: n0.N, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1864N extends AbstractC1887p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Shader f19400a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f19401b = 9205357640488583168L;

    @Override // n0.AbstractC1887p
    public final void a(float f10, long j3, C1878g c1878g) {
        Shader shaderB = this.f19400a;
        if (shaderB == null || !m0.f.a(this.f19401b, j3)) {
            if (m0.f.e(j3)) {
                shaderB = null;
                this.f19400a = null;
                this.f19401b = 9205357640488583168L;
            } else {
                shaderB = b(j3);
                this.f19400a = shaderB;
                this.f19401b = j3;
            }
        }
        long jC = AbstractC1861K.c(c1878g.f19432a.getColor());
        long j10 = C1891t.f19445b;
        if (!C1891t.c(jC, j10)) {
            c1878g.e(j10);
        }
        if (!A7.m.a(c1878g.c, shaderB)) {
            c1878g.h(shaderB);
        }
        if (c1878g.f19432a.getAlpha() / 255.0f == f10) {
            return;
        }
        c1878g.c(f10);
    }

    public abstract Shader b(long j3);
}
