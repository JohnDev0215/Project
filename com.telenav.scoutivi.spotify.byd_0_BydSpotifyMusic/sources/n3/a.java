package n3;

import java.io.IOException;
import java.io.InputStream;
import m2.e;
import m2.g;
import m2.j;
import n1.i;
import n1.k;
import q1.v;

/* JADX INFO: compiled from: SvgDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements k<InputStream, g> {
    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public v<g> b(InputStream inputStream, int i7, int i8, i iVar) throws IOException {
        try {
            g gVarL = g.l(inputStream);
            float fH = gVarL.h();
            float f7 = gVarL.f();
            if (f7 < 0.0f || fH < 0.0f) {
                gVarL.t(gVarL.g().height());
                gVarL.v(gVarL.g().width());
                gVarL.u(e.f8116d);
                h3.i.b("SvgDecoder").g("modify svg size  old size = " + fH + " x " + f7 + "  new size = " + gVarL.h() + " x " + gVarL.f());
            }
            return new w1.b(gVarL);
        } catch (j e7) {
            throw new IOException("Cannot load SVG from stream", e7);
        }
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(InputStream inputStream, i iVar) {
        return true;
    }
}
