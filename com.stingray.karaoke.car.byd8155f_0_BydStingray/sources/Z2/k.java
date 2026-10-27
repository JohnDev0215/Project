package z2;

import e2.o0;
import e2.p0;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f24329a;

    public k(j jVar) {
        this.f24329a = jVar;
    }

    public final void a() throws o0 {
        try {
            ((k) Class.forName("androidx.media3.effect.PreviewingSingleInputVideoGraph$Factory").getConstructor(p0.class).newInstance(this.f24329a)).a();
        } catch (Exception e10) {
            int i7 = o0.l;
            if (!(e10 instanceof o0)) {
                throw new o0(e10);
            }
        }
    }
}
