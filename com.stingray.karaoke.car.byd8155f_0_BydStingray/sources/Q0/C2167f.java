package q0;

import android.graphics.Outline;
import n0.C1880i;
import n0.InterfaceC1859I;

/* JADX INFO: renamed from: q0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2167f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2167f f21159a = new C2167f();

    public final void a(Outline outline, InterfaceC1859I interfaceC1859I) {
        if (!(interfaceC1859I instanceof C1880i)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        outline.setPath(((C1880i) interfaceC1859I).f19436a);
    }
}
