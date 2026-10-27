package c.a.g.f;

import c.a.g.l.h;
import com.byd.repository.exception.ApiException;
import com.byd.repository.exception.InvalidTokenException;
import com.byd.repository.exception.LatestVersionException;
import com.byd.repository.exception.NoRegisterException;
import com.byd.repository.exception.ParamsErrorException;
import d.a.o.e;
import e.b0;

/* JADX INFO: compiled from: CheckStatusCodeFunction.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements e<b0, String> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f2679c = "b";

    /* JADX INFO: renamed from: c.a.g.f.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CheckStatusCodeFunction.java */
    public static class C0051b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static b f2680a = new b();
    }

    public static b b() {
        return C0051b.f2680a;
    }

    @Override // d.a.o.e
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String apply(b0 b0Var) throws Exception {
        if (b0Var == null) {
            c.a.b.d.e.b(f2679c, "apply responseBody is null!");
            return "";
        }
        String strG = b0Var.K().G();
        String strB = h.b(strG, "status");
        if (c.a.g.l.b.f(strB)) {
            return strG;
        }
        if (c.a.g.l.b.b(strB)) {
            throw new InvalidTokenException(strB, c.a.g.l.b.a(strB));
        }
        if (c.a.g.l.b.e(strB)) {
            throw new ParamsErrorException(strB, c.a.g.l.b.a(strB));
        }
        if (c.a.g.l.b.c(strB)) {
            throw new LatestVersionException(strB, c.a.g.l.b.a(strB));
        }
        if (c.a.g.l.b.d(strB)) {
            throw new NoRegisterException(strB, c.a.g.l.b.a(strB));
        }
        throw new ApiException(strB, c.a.g.l.b.a(strB));
    }

    public b() {
    }
}
