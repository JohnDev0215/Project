package h.q.a;

import c.b.a.q;
import e.u;
import e.z;
import h.e;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: GsonRequestBodyConverter.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> implements e<T, z> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u f5880a = u.c("application/json; charset=UTF-8");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f5881b = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.a.e f5882c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q<T> f5883d;

    public b(c.b.a.e eVar, q<T> qVar) {
        this.f5882c = eVar;
        this.f5883d = qVar;
    }

    @Override // h.e
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public z a(T t) throws IOException {
        f.c cVar = new f.c();
        c.b.a.v.b bVarR = this.f5882c.r(new OutputStreamWriter(cVar.O(), f5881b));
        this.f5883d.d(bVarR, t);
        bVarR.close();
        return z.e(f5880a, cVar.R());
    }
}
